package com.m.offhand.mixin;

import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.client.ItemRendererHooks;
import net.minecraft.EntityClientPlayerMP;
import net.minecraft.ItemRenderer;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Unique
    private boolean offhand$suppressedMainhandSwing = false;

    @Unique
    private float offhand$prevSwingBackup = 0.0F;

    @Unique
    private float offhand$swingBackup = 0.0F;

    @Inject(method = "renderItemInFirstPerson", at = @At("HEAD"))
    private void offhand$beforeRenderItemInFirstPerson(float frame, CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null) {
            return;
        }

        EntityClientPlayerMP player = mc.thePlayer;
        if (player == null) {
            return;
        }

        if (OffhandUtils.isClientOffhandRenderContext()) {
            return;
        }

        if (!OffhandUtils.shouldSuppressClientMainhandRender(player)) {
            return;
        }

        this.offhand$suppressedMainhandSwing = true;
        this.offhand$prevSwingBackup = player.prevSwingProgress;
        this.offhand$swingBackup = player.swingProgress;
        player.prevSwingProgress = 0.0F;
        player.swingProgress = 0.0F;
    }

    @Inject(method = "renderItemInFirstPerson", at = @At("RETURN"))
    private void offhand$afterRenderItemInFirstPerson(float frame, CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc != null && this.offhand$suppressedMainhandSwing && mc.thePlayer != null) {
            mc.thePlayer.prevSwingProgress = this.offhand$prevSwingBackup;
            mc.thePlayer.swingProgress = this.offhand$swingBackup;
        }
        this.offhand$suppressedMainhandSwing = false;

        ItemRendererHooks.renderOffhandReturn(frame);
    }
}

