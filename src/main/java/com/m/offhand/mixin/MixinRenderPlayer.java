package com.m.offhand.mixin;

import com.m.offhand.api.compat.OffhandCompatRegistry;
import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.client.OffhandRenderHelper;
import net.minecraft.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public class MixinRenderPlayer {

    @Shadow
    private ModelBiped modelBipedMain;

    @Shadow
    private ModelBiped modelArmorChestplate;

    @Shadow
    private ModelBiped modelArmor;

    @Inject(method = "renderSpecials", at = @At(value = "TAIL"))
    private void offhand$render3rdPersonOffhand(AbstractClientPlayer par1AbstractClientPlayer, float par2, CallbackInfo ci) {
        if (!OffhandCompatRegistry.getRenderPolicy().shouldRenderThirdPersonOffhand(par1AbstractClientPlayer)) {
            return;
        }

        ItemStack offhand = OffhandUtils.getOffhandItem(par1AbstractClientPlayer);
        if (!offhand$hasRenderableStack(offhand)) {
            return;
        }

        GL11.glPushMatrix();

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_CULL_FACE);
        
        OffhandRenderHelper.renderOffhandItemIn3rdPerson(par1AbstractClientPlayer, this.modelBipedMain, par2);
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_CULL_FACE);

        GL11.glPopMatrix();
    }

    @Inject(method = "renderSpecials", at = @At(value = "HEAD"))
    private void offhand$setLeftHandItem(AbstractClientPlayer par1AbstractClientPlayer, float par2, CallbackInfo ci) {
        if (!OffhandCompatRegistry.getRenderPolicy().shouldRenderThirdPersonOffhand(par1AbstractClientPlayer)) {
            this.modelArmorChestplate.heldItemLeft = this.modelArmor.heldItemLeft = this.modelBipedMain.heldItemLeft = 0;
            return;
        }

        ItemStack offhand = OffhandUtils.getOffhandItem(par1AbstractClientPlayer);
        if (offhand$hasRenderableStack(offhand)) {
            this.modelArmorChestplate.heldItemLeft = this.modelArmor.heldItemLeft = this.modelBipedMain.heldItemLeft = 1;
        } else {
            this.modelArmorChestplate.heldItemLeft = this.modelArmor.heldItemLeft = this.modelBipedMain.heldItemLeft = 0;
        }
    }

    @Unique
    private static boolean offhand$hasRenderableStack(ItemStack stack) {
        return stack != null && stack.stackSize > 0 && stack.getItem() != null;
    }
}
