package com.m.offhand.mixin;

import net.minecraft.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FontRenderer.class)
public class OffhandFontRendererMixin {

    @Inject(method = "renderDefaultChar", at = @At("HEAD"), cancellable = true, remap = false)
    private void miteassistant$fixJava17CharRendering(int index, boolean flag, CallbackInfoReturnable<Float> cir) {
        if (index < 0 || index >= 256) {
            cir.setReturnValue(0.0f);
        }
    }
}
