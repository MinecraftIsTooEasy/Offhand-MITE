package com.m.offhand.client;

import net.minecraft.*;

import org.lwjgl.opengl.GL11;

import com.m.offhand.api.compat.OffhandCompatRegistry;
import com.m.offhand.api.core.OffhandUtils;

public class ItemRendererHooks {

    public static void renderOffhandReturn(float frame) {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if (player == null) {
            return;
        }

        if (!OffhandCompatRegistry.getRenderPolicy().shouldRenderFirstPersonOffhand(player)) {
            return;
        }
        
        if (OffhandUtils.isUsingOffhand(player)) return;

        ItemStack offhandItem = OffhandUtils.getOffhandItem(player);
        
        if (offhandItem == null) {
            return;
        }
        
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_FRONT);
        GL11.glPushMatrix();
        
        GL11.glScalef(-1, 1, 1);
        
        float f3 = player.prevRenderArmPitch + (player.renderArmPitch - player.prevRenderArmPitch) * frame;
        float f4 = player.prevRenderArmYaw + (player.renderArmYaw - player.prevRenderArmYaw) * frame;
        GL11.glRotatef((player.rotationPitch - f3) * -0.1F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef((player.rotationYaw - f4) * -0.1F, 0.0F, 1.0F, 0.0F);

        boolean animateAction = OffhandRenderHelper.shouldUseClientOffhandActionSwing(player);
        float originalPrevSwing = player.prevSwingProgress;
        float originalSwing = player.swingProgress;

        // Keep the offhand item steady except when it should mirror the main-hand action.
        if (!animateAction) {
            player.prevSwingProgress = 0.0F;
            player.swingProgress = 0.0F;
        }

        try {
            OffhandUtils.beginClientOffhandRenderContext();
            try {
                OffhandUtils.useOffhandItem(player, false, () -> OffhandRenderHelper.itemRenderer.renderItemInFirstPerson(frame));
            } finally {
                OffhandUtils.endClientOffhandRenderContext();
            }
        } finally {
            player.prevSwingProgress = originalPrevSwing;
            player.swingProgress = originalSwing;
        }
        
        GL11.glPopMatrix();
        GL11.glCullFace(GL11.GL_BACK);
    }
}
