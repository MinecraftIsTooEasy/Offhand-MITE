package com.m.offhand.mixin;

import com.m.offhand.client.OffhandRenderHelper;
import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiInventory.class)
public abstract class MixinGuiInventory extends GuiContainer {

    public MixinGuiInventory(Container par1Container) {
        super(par1Container);
    }

    @Inject(method = "drawGuiContainerBackgroundLayer", at = @At("TAIL"))
    protected void offhand$drawOffhandSlot(float partialTicks, int mouseX, int mouseY, CallbackInfo ci) {
        // 背包界面副手槽绘制
        OffhandRenderHelper.drawOffhandSlotBackground(guiLeft + 78, guiTop + 63, 20);
    }
}
