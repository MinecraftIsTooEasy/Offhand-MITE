package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.renderer.OffhandRenderer;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class OffhandItemRendererMixin {
    @Shadow
    private Minecraft mc;

    @Shadow
    private ItemStack itemToRender;

    @Shadow
    private float equippedProgress;

    @Shadow
    private float prevEquippedProgress;

    @Unique
    private ItemStack miteassistant$savedItemToRender = null;
    @Unique
    private float miteassistant$savedEquippedProgress = -1;
    @Unique
    private float miteassistant$savedPrevEquippedProgress = -1;

    @Inject(method = "renderItemInFirstPerson(F)V", at = @At("HEAD"))
    private void miteassistant$beforeRender(float partialTicks, CallbackInfo ci) {
        miteassistant$savedItemToRender = null;
        miteassistant$savedEquippedProgress = -1;
        miteassistant$savedPrevEquippedProgress = -1;
        
        if (this.mc == null || this.mc.thePlayer == null) return;
        
        Object playerObj = this.mc.thePlayer;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;

        if (offhandAccess.miteassistant$isUsingOffhand()) {
            miteassistant$savedItemToRender = this.itemToRender;
            miteassistant$savedEquippedProgress = this.equippedProgress;
            miteassistant$savedPrevEquippedProgress = this.prevEquippedProgress;
            
            this.itemToRender = offhandAccess.miteassistant$getOriginalMainhand();
            this.equippedProgress = 1.0F;
            this.prevEquippedProgress = 1.0F;
        }
    }

    @Inject(method = "renderItemInFirstPerson(F)V", at = @At("TAIL"))
    private void miteassistant$renderOffhandFirstPerson(float partialTicks, CallbackInfo ci) {
        if (this.mc == null || this.mc.thePlayer == null) return;
        
        Object playerObj = this.mc.thePlayer;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;

        if (offhandAccess.miteassistant$isUsingOffhand()) {
            if (miteassistant$savedItemToRender != null || miteassistant$savedEquippedProgress >= 0) {
                this.itemToRender = miteassistant$savedItemToRender;
                this.equippedProgress = miteassistant$savedEquippedProgress;
                this.prevEquippedProgress = miteassistant$savedPrevEquippedProgress;
                miteassistant$savedItemToRender = null;
                miteassistant$savedEquippedProgress = -1;
                miteassistant$savedPrevEquippedProgress = -1;
            }
        }

        OffhandRenderer.renderOffhandInFirstPerson(this.mc, partialTicks);
    }
}
