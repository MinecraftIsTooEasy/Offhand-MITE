package com.m.offhand.mixin;

import com.m.offhand.api.core.IOffhandPlayer;
import com.m.offhand.api.core.OffhandUtils;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumFace;
import net.minecraft.Minecraft;
import net.minecraft.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {

    @Shadow
    private int currentPlayerItem;

    @Shadow
    public abstract void syncCurrentPlayItem();

    @Inject(method = "clickBlock", at = @At("HEAD"))
    private void offhand$syncSlotBeforeClickBlock(int x, int y, int z, EnumFace face, CallbackInfo ci) {
        this.syncCurrentPlayItem();
    }

    @Inject(
        method = "syncCurrentPlayItem",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/NetClientHandler;addToSendQueue(Lnet/minecraft/Packet;)V"))
    private void offhand$updateMainhandSlot(CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft == null) {
            return;
        }

        EntityPlayer player = minecraft.thePlayer;
        if (player == null) {
            return;
        }

        if (currentPlayerItem != OffhandUtils.getOffhandSlot(player)) {
            ((IOffhandPlayer) player).setMainhandSlot(currentPlayerItem);
        }
    }
}
