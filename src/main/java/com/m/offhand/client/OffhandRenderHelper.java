package com.m.offhand.client;

import com.m.offhand.Offhand;
import com.m.offhand.OffhandManyLibConfig;
import net.minecraft.*;

import org.lwjgl.opengl.GL11;

import com.m.offhand.api.core.IOffhandPlayer;
import com.m.offhand.api.core.OffhandActionHelper;
import com.m.offhand.api.core.OffhandUtils;

public final class OffhandRenderHelper {

    public static final ItemRenderer itemRenderer = new ItemRenderer(Minecraft.getMinecraft());

    public static final ResourceLocation OFFHAND_WIDGET_TEXTURE = new ResourceLocation(Offhand.MODID, "textures/gui/offhand_widget.png");
    public static final ResourceLocation OFFHAND_SLOT_TEXTURE = new ResourceLocation(Offhand.MODID, "textures/gui/offhand_slot.png");
    public static final ResourceLocation OFFHAND_SLOT_FILLED_TEXTURE = new ResourceLocation(Offhand.MODID, "textures/gui/offhand_slot_1.png");

    public static void applyOffhandBowAim(Entity entity, ModelBiped biped, float ageInTicks) {
        if (!(entity instanceof EntityPlayer) || biped == null) {
            return;
        }

        EntityPlayer player = (EntityPlayer) entity;
        ItemStack offhandItem = OffhandUtils.getOffhandItem(player);
        if (!isBow(offhandItem) || !((IOffhandPlayer) player).isOffhandItemInUse()) {
            return;
        }

        applyLeftHandBowPose(biped, ageInTicks);
    }

    public static void applyOffhandAttackSwing(Entity entity, ModelBiped biped, float swingProgress) {
        if (!(entity instanceof EntityPlayer) || biped == null || swingProgress <= 0.0F) {
            return;
        }

        EntityPlayer player = (EntityPlayer) entity;
        if (!shouldAnimateOffhandActionSwingModel(player)) {
            return;
        }

        float easedSwing = 1.0F - swingProgress;
        easedSwing = 1.0F - easedSwing * easedSwing * easedSwing * easedSwing;
        float armSwing = MathHelper.sin(easedSwing * (float) Math.PI);
        float headSwing = MathHelper.sin(swingProgress * (float) Math.PI)
            * -(biped.bipedHead.rotateAngleX - 0.7F)
            * 0.75F;

        biped.bipedLeftArm.rotateAngleX -= armSwing * 1.2F + headSwing;
        biped.bipedLeftArm.rotateAngleY -= biped.bipedBody.rotateAngleY * 2.0F;
        biped.bipedLeftArm.rotateAngleZ = MathHelper.sin(swingProgress * (float) Math.PI) * 0.4F;
    }

    public static boolean shouldAnimateOffhandAttack(EntityPlayer player) {
        if (player == null
            || player.inventory == null
            || !OffhandManyLibConfig.OFFHAND_ATTACK.getBooleanValue()
            || ((IOffhandPlayer) player).isOffhandItemInUse()) {
            return false;
        }

        ItemStack mainhand = player.inventory.getCurrentItemStack();
        ItemStack offhand = OffhandUtils.getOffhandItem(player);
        return OffhandActionHelper.canOffhandAttack(player, mainhand, offhand);
    }

    public static boolean shouldAnimateOffhandMining(EntityPlayer player, int x, int y, int z) {
        if (player == null
            || player.worldObj == null
            || player.inventory == null
            || !OffhandManyLibConfig.OFFHAND_BREAK_BLOCKS.getBooleanValue()
            || ((IOffhandPlayer) player).isOffhandItemInUse()) {
            return false;
        }

        Block block = player.worldObj.getBlock(x, y, z);
        if (block == null) {
            return false;
        }

        int metadata = player.worldObj.getBlockMetadata(x, y, z);
        ItemStack mainhand = player.inventory.getCurrentItemStack();
        ItemStack offhand = OffhandUtils.getOffhandItem(player);
        return OffhandActionHelper.canDualMineWithTools(player, block, metadata, mainhand, offhand);
    }

    public static boolean shouldUseClientOffhandActionSwing(EntityPlayer player) {
        return (shouldAnimateOffhandAttack(player) && OffhandUtils.shouldAnimateClientOffhandAttack(player))
            || OffhandUtils.shouldAnimateClientOffhandMining(player);
    }

    private static boolean shouldAnimateOffhandActionSwingModel(EntityPlayer player) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft != null && player == minecraft.thePlayer) {
            return shouldUseClientOffhandActionSwing(player);
        }

        return shouldAnimateOffhandAttack(player);
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
        } else if (isBow(stack) || stack.getItem() instanceof ItemFishingRod) {
            applyHeldToolTransform();
        } else if (stack.getItem().isFull3D()) {
            if (stack.getItem().shouldRotateAroundWhenRendering()) {
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.0F, -0.125F, 0.0F);
            }

            if (isBlockingOffhand(player, stack)) {
                applyBlockingTransform();
            }
            applyFull3DTransform();
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

    private static boolean isBow(ItemStack stack) {
        return stack != null && stack.getItem() instanceof ItemBow;
    }

    private static boolean isBlockingOffhand(EntityPlayer player, ItemStack stack) {
        return player != null
            && stack != null
            && ((IOffhandPlayer) player).isOffhandItemInUse()
            && stack.getItemInUseAction(player) == EnumItemInUseAction.BLOCK;
    }

    private static void applyHeldToolTransform() {
        float scale = 0.625F;
        GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, -scale, scale);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
    }

    private static void applyFull3DTransform() {
        float scale = 0.625F;
        GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
        GL11.glScalef(scale, -scale, scale);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
    }

    private static void applyBlockingTransform() {
        GL11.glTranslatef(0.05F, 0.0F, -0.1F);
        GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
    }

    private static void applyLeftHandBowPose(ModelBiped biped, float ageInTicks) {
        float bowOffset = 0.0F;
        float bowPull = 0.0F;
        biped.bipedRightArm.rotateAngleZ = 0.0F;
        biped.bipedLeftArm.rotateAngleZ = 0.0F;
        biped.bipedLeftArm.rotateAngleY = 0.1F - bowOffset * 0.6F + biped.bipedHead.rotateAngleY;
        biped.bipedRightArm.rotateAngleY = -(0.1F - bowOffset * 0.6F) + biped.bipedHead.rotateAngleY - 0.4F;
        biped.bipedLeftArm.rotateAngleX = -((float) Math.PI / 2.0F) + biped.bipedHead.rotateAngleX;
        biped.bipedRightArm.rotateAngleX = -((float) Math.PI / 2.0F) + biped.bipedHead.rotateAngleX;
        biped.bipedLeftArm.rotateAngleX -= bowOffset * 1.2F - bowPull * 0.4F;
        biped.bipedRightArm.rotateAngleX -= bowOffset * 1.2F - bowPull * 0.4F;

        float zBob = MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        float xBob = MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        biped.bipedLeftArm.rotateAngleZ -= zBob;
        biped.bipedRightArm.rotateAngleZ += zBob;
        biped.bipedLeftArm.rotateAngleX += xBob;
        biped.bipedRightArm.rotateAngleX -= xBob;
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

    public static ResourceLocation getInventoryOffhandSlotTexture(EntityPlayer player) {
        if (player == null) {
            return OFFHAND_SLOT_TEXTURE;
        }

        ItemStack offhandStack = OffhandUtils.getOffhandItem(player);
        if (offhandStack != null && offhandStack.stackSize > 0 && offhandStack.getItem() != null) {
            return OFFHAND_SLOT_FILLED_TEXTURE;
        }

        return OFFHAND_SLOT_TEXTURE;
    }

    public static boolean usesBothHands(ItemStack stack) {
        if (stack == null) return false;
        return stack.getItem() instanceof ItemMap;
    }
}
