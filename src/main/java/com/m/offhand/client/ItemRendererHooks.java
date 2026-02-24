package com.m.offhand.client;

import net.minecraft.*;

import org.lwjgl.opengl.GL11;

import com.m.offhand.api.compat.OffhandCompatRegistry;
import com.m.offhand.api.core.IOffhandPlayer;
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
            IOffhandPlayer offhandPlayer = (IOffhandPlayer) (Object) player;
            if (offhandPlayer.getOffSwingProgress(frame) == 0) {
                return;
            }
        }

        OffhandRenderHelper.firstPersonFrame = frame;
        
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_FRONT);
        GL11.glPushMatrix();
        
        GL11.glScalef(-1, 1, 1);
        
        float f3 = player.prevRenderArmPitch + (player.renderArmPitch - player.prevRenderArmPitch) * frame;
        float f4 = player.prevRenderArmYaw + (player.renderArmYaw - player.prevRenderArmYaw) * frame;
        GL11.glRotatef((player.rotationPitch - f3) * -0.1F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef((player.rotationYaw - f4) * -0.1F, 0.0F, 1.0F, 0.0F);

        IOffhandPlayer offhandPlayer = (IOffhandPlayer) (Object) player;
        float originalPrevSwing = player.prevSwingProgress;
        float originalSwing = player.swingProgress;
        float offhandSwing = offhandPlayer.getOffSwingProgress(frame);

        // ItemRenderer uses getSwingProgress() for first-person swing transforms.
        // Mirror offhand swing into these two fields during offhand render only.
        player.prevSwingProgress = offhandSwing;
        player.swingProgress = offhandSwing;

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
