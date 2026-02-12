package com.m.offhand.mixin;

import com.m.offhand.api.Hand;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.util.OffhandNetworkHelper;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class OffhandEntityPlayerUseMixin {
    
    @Shadow
    public InventoryPlayer inventory;
    
    @Inject(method = "onItemUseFinish", at = @At("TAIL"))
    protected void miteassistant$afterItemUseFinish(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player.worldObj.isRemote) return;
        
        OffhandAccess access = (OffhandAccess) player;
        if (!access.miteassistant$isUsingOffhand()) return;
        
        restoreAfterOffhandUse(player, access);
    }
    
    @Inject(method = "stopUsingItem(Z)V", at = @At("TAIL"))
    protected void miteassistant$afterStopUsingItem(boolean inform_server, CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player.worldObj.isRemote) return;
        
        OffhandAccess access = (OffhandAccess) player;
        if (!access.miteassistant$isUsingOffhand()) return;
        
        restoreAfterOffhandUse(player, access);
    }
    
    private void restoreAfterOffhandUse(EntityPlayer player, OffhandAccess access) {
        int originalSlot = access.miteassistant$getOriginalSlot();
        int currentSlot = this.inventory.currentItem;
        
        if (originalSlot < 0 || originalSlot >= this.inventory.mainInventory.length) {
            originalSlot = currentSlot;
        }
        
        ItemStack usedItem = this.inventory.mainInventory[originalSlot];
        ItemStack originalMainhand = access.miteassistant$getOriginalMainhand();
        
        ItemStack newOffhand = (usedItem == null || usedItem.stackSize <= 0) ? null : usedItem;
        
        if (originalSlot != currentSlot) {
            access.miteassistant$setOffhandStack(newOffhand);
            this.inventory.mainInventory[currentSlot] = originalMainhand;
            this.inventory.mainInventory[originalSlot] = null;
            
            if (player instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncOffhandToClient(serverPlayer, newOffhand);
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, originalMainhand);
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, originalSlot, null);
            }
        } else {
            this.inventory.mainInventory[originalSlot] = originalMainhand;
            access.miteassistant$setOffhandStack(newOffhand);
            
            if (player instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncOffhandToClient(serverPlayer, newOffhand);
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, originalSlot, originalMainhand);
            }
        }
        
        clearOffhandState(access);
    }
    
    private void clearOffhandState(OffhandAccess access) {
        access.miteassistant$setUsingOffhand(false);
        access.miteassistant$setActiveHand(Hand.MAIN_HAND);
        access.miteassistant$setOriginalMainhand(null);
        access.miteassistant$setOriginalSlot(-1);
    }
}
