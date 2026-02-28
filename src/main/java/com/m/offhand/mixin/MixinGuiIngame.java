package com.m.offhand.mixin;

import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.client.OffhandRenderHelper;
import net.minecraft.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame extends Gui {

    @Shadow
    @Final
    private Minecraft mc;

    @Shadow
    private static RenderItem itemRenderer;

    @Inject(method = "renderGameOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/RenderHelper;disableStandardItemLighting()V", shift = At.Shift.BEFORE))
    private void offhand$renderOffhandSlot(float par1, boolean par2, int par3, int par4, CallbackInfo ci) {
        if (this.mc.thePlayer == null) return;

        ItemStack offhandStack = OffhandUtils.getOffhandItem(this.mc.thePlayer);

        ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int width = scaledRes.getScaledWidth();
        int height = scaledRes.getScaledHeight();

        int slotX = width / 2 - 91 - 24;
        int slotY = height - 16 - 6;

        if (offhandStack != null) {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            
            OffhandRenderHelper.drawOffhandSlotBackground(slotX, slotY);
            
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.enableGUIStandardItemLighting();

            float animProgress = offhandStack.animationsToGo - par1;

            if (animProgress > 0.0F) {
                GL11.glPushMatrix();
                float scale = 1.0F + animProgress / 5.0F;
                GL11.glTranslatef((slotX + 11), (slotY + 11), 0.0F);
                GL11.glScalef(1.0F / scale, (scale + 1.0F) / 2.0F, 1.0F);
                GL11.glTranslatef(-(slotX + 11), -(slotY + 11), 0.0F);
            }

            itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), offhandStack, slotX + 3, slotY + 3);

            if (animProgress > 0.0F) {
                GL11.glPopMatrix();
            }

            itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), offhandStack, slotX + 3, slotY + 3);
        }

        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        this.mc.getTextureManager().bindTexture(Gui.icons);
    }
}
