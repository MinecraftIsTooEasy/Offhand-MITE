package com.m.offhand.mixin;

import com.m.offhand.api.compat.OffhandCompatRegistry;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityTrackerEntry;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTrackerEntry.class)
public class MixinEntityTrackerEntry {

    @Shadow
    public Entity myEntity;

    @Inject(
        method = "tryStartWachingThis",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/Set;add(Ljava/lang/Object;)Z",
            shift = At.Shift.AFTER))
    private void offhand$syncWhenTrackingStarts(ServerPlayer trackingPlayer, CallbackInfo ci) {
        if (trackingPlayer == null || !(this.myEntity instanceof EntityPlayer trackedPlayer) || trackedPlayer == trackingPlayer) {
            return;
        }

        OffhandCompatRegistry.getSyncStrategy().syncOffhandItemToPlayer(trackingPlayer, trackedPlayer);
    }
}
