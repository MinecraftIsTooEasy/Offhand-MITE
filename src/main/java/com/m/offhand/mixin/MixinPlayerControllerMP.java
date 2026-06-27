package com.m.offhand.mixin;

import com.m.offhand.api.core.IOffhandPlayer;
import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.client.OffhandRenderHelper;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumFace;
import net.minecraft.Minecraft;
import net.minecraft.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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
        this.offhand$markOffhandMiningAnimation(x, y, z);
    }

    @Inject(method = "onPlayerDamageBlock", at = @At("HEAD"))
    private void offhand$markOffhandMiningAnimationWhileBreaking(int x, int y, int z, EnumFace face, CallbackInfo ci) {
        this.offhand$markOffhandMiningAnimation(x, y, z);
    }

    @Inject(method = "leftClickEntity", at = @At("HEAD"))
    private void offhand$markOffhandAttackAnimation(Entity entity, CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft == null || minecraft.thePlayer == null) {
            return;
        }

        if (OffhandRenderHelper.shouldAnimateOffhandAttack(minecraft.thePlayer)) {
            OffhandUtils.markClientOffhandAttackAnimation(minecraft.thePlayer, 4);
        }
    }

    @Unique
    private void offhand$markOffhandMiningAnimation(int x, int y, int z) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft == null || minecraft.thePlayer == null) {
            return;
        }

        if (OffhandRenderHelper.shouldAnimateOffhandMining(minecraft.thePlayer, x, y, z)) {
            OffhandUtils.markClientOffhandMiningAnimation(minecraft.thePlayer, 2);
        }
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
