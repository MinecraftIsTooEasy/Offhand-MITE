package com.m.offhand.mixin;

import com.m.offhand.api.core.IOffhandPlayer;
import net.minecraft.Damage;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Damage.class)
public abstract class MixinDamage {

    @Redirect(
        method = "applyTargetDefenseModifiers",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;getHeldItemStack()Lnet/minecraft/ItemStack;"))
    private ItemStack offhand$useOffhandBlockingStackForDurability(EntityPlayer player) {
        IOffhandPlayer offhandPlayer = (IOffhandPlayer) player;
        if (offhandPlayer.isOffhandBlocking()) {
            ItemStack offhandStack = offhandPlayer.getOffhandItemInUse();
            if (offhandStack != null && offhandStack.stackSize > 0) {
                return offhandStack;
            }
        }
        return player.getHeldItemStack();
    }
}

