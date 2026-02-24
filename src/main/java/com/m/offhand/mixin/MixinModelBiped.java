package com.m.offhand.mixin;

import com.m.offhand.client.OffhandRenderHelper;
import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBiped.class)
public abstract class MixinModelBiped extends ModelBase {

    @Shadow
    public ModelRenderer bipedHead;

    @Shadow
    public ModelRenderer bipedRightArm;

    @Shadow
    public ModelRenderer bipedLeftArm;

    @Inject(
        method = "setRotationAngles",
        at = @At(value = "FIELD", target = "Lnet/minecraft/ModelBiped;isSneak:Z", shift = At.Shift.BEFORE))
    private void offhand$moveOffHandArm(float f1, float f2, float f3, float f4, float f5, float f6, Entity entity, CallbackInfo ci) {
        OffhandRenderHelper.moveOffHandArm(entity, (ModelBiped) (Object) this, f6);
    }
}
