package com.m.offhand.client;

import com.m.offhand.Offhand;
import net.minecraft.*;

import org.lwjgl.opengl.GL11;

import com.m.offhand.api.core.IOffhandPlayer;
import com.m.offhand.api.core.OffhandUtils;

public final class OffhandRenderHelper {

    public static final ItemRenderer itemRenderer = new ItemRenderer(Minecraft.getMinecraft());
    
    public static float firstPersonFrame;
    
    public static final ResourceLocation OFFHAND_WIDGET_TEXTURE = new ResourceLocation(Offhand.MODID, "textures/gui/offhand_widget.png");
    public static final ResourceLocation OFFHAND_SLOT_TEXTURE = new ResourceLocation(Offhand.MODID, "textures/gui/offhand_slot.png");

    public static void moveOffHandArm(Entity entity, ModelBiped biped, float frame) {
        if (entity instanceof EntityPlayer) {
            IOffhandPlayer player = (IOffhandPlayer) entity;
            EntityClientPlayerMP clientPlayer = Minecraft.getMinecraft().thePlayer;
            boolean isClientPlayer = entity == clientPlayer;
            
            if (!isClientPlayer || player.getOffSwingProgress(OffhandRenderHelper.firstPersonFrame) != 0) {
                float offhandSwing = player.getOffSwingProgress(frame);

                if (offhandSwing > 0.0F) {
                    if (biped.bipedBody.rotateAngleY != 0.0F) {
                        biped.bipedLeftArm.rotateAngleY -= biped.bipedBody.rotateAngleY;
                        biped.bipedLeftArm.rotateAngleX -= biped.bipedBody.rotateAngleY;
                    }
                    
                    biped.bipedBody.rotateAngleY = -MathHelper
                        .sin(MathHelper.sqrt_float(offhandSwing) * (float) Math.PI * 2.0F) * 0.2F;

                    biped.bipedLeftArm.rotationPointZ = -MathHelper.sin(biped.bipedBody.rotateAngleY) * 5.0F;
                    biped.bipedLeftArm.rotationPointX = MathHelper.cos(biped.bipedBody.rotateAngleY) * 5.0F;
                    
                    float f6 = 1.0F - offhandSwing;
                    f6 = 1.0F - f6 * f6 * f6;
                    double f8 = MathHelper.sin(f6 * (float) Math.PI) * 1.2D;
                    double f10 = MathHelper.sin(offhandSwing * (float) Math.PI) * -(biped.bipedHead.rotateAngleX - 0.7F)
                        * 0.75F;
                    
                    biped.bipedLeftArm.rotateAngleX -= f8 + f10;
                    biped.bipedLeftArm.rotateAngleY += biped.bipedBody.rotateAngleY * 3.0F;
                    biped.bipedLeftArm.rotateAngleZ = MathHelper.sin(offhandSwing * (float) Math.PI) * -0.4F;
                }
            }
        }
    }

    public static void renderOffhandItemIn3rdPerson(EntityPlayer player, ModelBiped modelBipedMain, float frame) {
        ItemStack offhandItem = OffhandUtils.getOffhandItem(player);
        if (offhandItem == null) return;

        GL11.glPushMatrix();
        
        modelBipedMain.bipedLeftArm.postRender(0.0625F);
        
        GL11.glTranslatef(
            -modelBipedMain.bipedLeftArm.rotationPointX * 0.0625F,
            -modelBipedMain.bipedLeftArm.rotationPointY * 0.0625F,
            -modelBipedMain.bipedLeftArm.rotationPointZ * 0.0625F);
        
        GL11.glScalef(-1, 1, 1);
        GL11.glTranslatef(
            -modelBipedMain.bipedLeftArm.rotationPointX * 0.0625F,
            modelBipedMain.bipedLeftArm.rotationPointY * 0.0625F,
            -modelBipedMain.bipedLeftArm.rotationPointZ * 0.0625F);

        GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

        GL11.glFrontFace(GL11.GL_CW);

        renderItemByType(player, offhandItem);

        GL11.glFrontFace(GL11.GL_CCW);
        GL11.glPopMatrix();
    }

    private static void renderItemByType(EntityPlayer player, ItemStack stack) {
        float scale;
        
        boolean is3D = stack.getItemSpriteNumber() == 0 
            && stack.itemID < Block.blocksList.length 
            && Block.blocksList[stack.itemID] != null 
            && RenderBlocks.renderItemIn3d(Block.blocksList[stack.itemID].getRenderType());

        if (is3D) {
            scale = 0.375F;
            GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(-scale, -scale, scale);
        } else if (stack.getItem() instanceof ItemFishingRod) {
            scale = 0.625F;
            GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(scale, -scale, scale);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        } else if (stack.getItem().isFull3D()) {
            scale = 0.625F;

            if (stack.getItem().shouldRotateAroundWhenRendering()) {
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.0F, -0.125F, 0.0F);
            }

            GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
            GL11.glScalef(scale, -scale, scale);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        } else {
            scale = 0.375F;
            GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
            GL11.glScalef(scale, scale, scale);
            GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
        }

        int color = stack.getItem().getColorFromItemStack(stack, 0);
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        GL11.glColor4f(r, g, b, 1.0F);
        itemRenderer.renderItem(player, stack, 0);
    }

    public static void drawOffhandSlotBackground(int x, int y) {
        drawOffhandSlotBackground(x, y, 22, OFFHAND_WIDGET_TEXTURE);
    }

    public static void drawOffhandSlotBackground(int x, int y, int size, ResourceLocation texture) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + size, 0, 0.0, 1.0);
        tessellator.addVertexWithUV(x + size, y + size, 0, 1.0, 1.0);
        tessellator.addVertexWithUV(x + size, y, 0, 1.0, 0.0);
        tessellator.addVertexWithUV(x, y, 0, 0.0, 0.0);
        tessellator.draw();
    }

    public static boolean usesBothHands(ItemStack stack) {
        if (stack == null) return false;
        return stack.getItem() instanceof ItemMap;
    }
}
