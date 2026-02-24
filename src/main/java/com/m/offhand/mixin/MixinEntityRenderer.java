package com.m.offhand.mixin;

import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.client.OffhandRenderHelper;
import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Inject(
        method = "updateRenderer",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemRenderer;updateEquippedItem()V"))
    private void offhand$updateOffhandItem(CallbackInfo ci) {
        OffhandUtils.useOffhandItem(
            Minecraft.getMinecraft().thePlayer,
            false,
            OffhandRenderHelper.itemRenderer::updateEquippedItem);
    }
}
