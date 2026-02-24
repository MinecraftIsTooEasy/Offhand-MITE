package com.m.offhand.mixin;

import com.m.offhand.api.compat.OffhandCompatRegistry;
import com.m.offhand.api.core.IOffhandInventory;
import com.m.offhand.api.core.IOffhandPlayer;
import com.m.offhand.api.core.OffhandUtils;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public class MixinEntityLivingBase {

    @Unique
    private ItemStack offhand$previousOffhandStack = null;

    @Inject(method = "swingArm", at = @At("HEAD"), cancellable = true)
    private void offhand$globalCancelMainhandSwing(CallbackInfo ci) {
        EntityLivingBase self = (EntityLivingBase) (Object) this;
        if (!(self instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) self;
        IOffhandPlayer offhandPlayer = (IOffhandPlayer) player;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();

        // Global interception mode: when offhand right-click/use is active, block mainhand swing.
        if (OffhandUtils.isClientOffhandUseContext()
            || (offhandPlayer.isOffhandItemInUse() && player.inventory.currentItem != offhandSlot)
            || OffhandUtils.shouldSuppressClientMainhandRender(player)) {
            ci.cancel();
        }
    }

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void offhand$updateOffhandItem(CallbackInfo ci) {
        EntityLivingBase self = (EntityLivingBase) (Object) this;
        if (!(self instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) self;
        ItemStack current = OffhandUtils.getOffhandItem(player);
        boolean changed = !ItemStack.areItemStacksEqual(this.offhand$previousOffhandStack, current);
        if (!changed) {
            return;
        }

        this.offhand$previousOffhandStack = current == null ? null : current.copy();
        if (!player.worldObj.isRemote && player instanceof ServerPlayer) {
            OffhandCompatRegistry.getSyncStrategy().syncOffhandItem(player);
        }
    }

    @Inject(method = "clearActivePotions", at = @At("TAIL"))
    private void offhand$clearOffhandUse(CallbackInfo ci) {
        EntityLivingBase self = (EntityLivingBase) (Object) this;
        if (!(self instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) self;
        IOffhandPlayer offhandPlayer = (IOffhandPlayer) player;
        if (!offhandPlayer.isOffhandItemInUse()) {
            return;
        }

        // Milk and similar flows may clear potions during active item finishing.
        // Do not clear offhand-use state before convertOneOfHeldItem finishes.
        if (player.isUsingItem()) {
            return;
        }

        offhandPlayer.setOffhandItemInUse(false);
        if (!player.worldObj.isRemote && player instanceof ServerPlayer) {
            OffhandCompatRegistry.getSyncStrategy().syncOffhandUseState(player, false);
        }
    }
}
