package moddedMite.offhand.client;

import net.minecraft.*;

import org.lwjgl.opengl.GL11;

import moddedMite.offhand.api.compat.OffhandCompatRegistry;
import moddedMite.offhand.api.core.IOffhandPlayer;
import moddedMite.offhand.api.core.OffhandUtils;

public class ItemRendererHooks {

    public static void renderOffhandReturn(float frame) {
        renderOffhandFirstPerson(frame);
    }

    public static boolean renderOffhandFirstPerson(float frame) {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if (player == null) {
            return false;
        }

        if (!OffhandCompatRegistry.getRenderPolicy().shouldRenderFirstPersonOffhand(player)) {
            return false;
        }

        if (OffhandUtils.isUsingOffhand(player)) {
            return false;
        }

        ItemStack mainhandItem = player.inventory.getCurrentItemStack();
        if (OffhandRenderHelper.usesBothHands(mainhandItem)) {
            return false;
        }

        IOffhandPlayer offhandPlayer = (IOffhandPlayer) (Object) player;
        float placementSwing = offhandPlayer.getOffSwingProgress(frame);
        ItemStack offhandItem = OffhandUtils.getOffhandItem(player);

        if (offhandItem == null && placementSwing <= 0.0F) {
            return false;
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_FRONT);
        GL11.glPushMatrix();

        GL11.glScalef(-1, 1, 1);

        float f3 = player.prevRenderArmPitch + (player.renderArmPitch - player.prevRenderArmPitch) * frame;
        float f4 = player.prevRenderArmYaw + (player.renderArmYaw - player.prevRenderArmYaw) * frame;
        GL11.glRotatef((player.rotationPitch - f3) * -0.1F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef((player.rotationYaw - f4) * -0.1F, 0.0F, 1.0F, 0.0F);

        boolean animateAction = placementSwing > 0.0F
            || OffhandRenderHelper.shouldUseClientOffhandActionSwing(player);
        float originalPrevSwing = player.prevSwingProgress;
        float originalSwing = player.swingProgress;

        if (placementSwing > 0.0F) {
            // ItemRenderer reads the main swing fields, so expose only the placement swing during offhand rendering.
            player.prevSwingProgress = placementSwing;
            player.swingProgress = placementSwing;
        } else if (!animateAction) {
            player.prevSwingProgress = 0.0F;
            player.swingProgress = 0.0F;
        }

        try {
            OffhandUtils.beginClientOffhandRenderContext();
            try {
                int previousSlot = OffhandUtils.swapToOffhand(player);
                try {
                    OffhandRenderHelper.itemRenderer.renderItemInFirstPerson(frame);
                } finally {
                    OffhandUtils.swapBack(player, previousSlot);
                }
            } finally {
                OffhandUtils.endClientOffhandRenderContext();
            }
        } finally {
            player.prevSwingProgress = originalPrevSwing;
            player.swingProgress = originalSwing;
        }

        GL11.glPopMatrix();
        GL11.glCullFace(GL11.GL_BACK);
        return true;
    }
}
