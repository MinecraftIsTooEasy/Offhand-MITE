package com.m.offhand.renderer;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.util.OffhandUtils;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public final class OffhandRenderer {
    
    private static final ResourceLocation WIDGETS_TEX_PATH = new ResourceLocation("textures/gui/widgets.png");
    private static final RenderItem ITEM_RENDERER = new RenderItem();
    
    private OffhandRenderer() {
    }
    
    public static void renderOffhandInFirstPerson(Minecraft mc, float partialTicks) {
        if (mc == null || mc.thePlayer == null) return;
        
        Object playerObj = mc.thePlayer;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;
        
        EntityClientPlayerMP player = mc.thePlayer;
        boolean isUsingOffhand = offhandAccess.miteassistant$isUsingOffhand();
        
        ItemStack itemToRenderInOffhand;
        if (isUsingOffhand) {
            itemToRenderInOffhand = player.inventory.getCurrentItemStack();
        } else {
            itemToRenderInOffhand = offhandAccess.miteassistant$getOffhandStack();
        }
        
        if (!OffhandUtils.isValidOffhand(itemToRenderInOffhand)) return;
        
        if (isUsingOffhand) {
            renderOffhandItemInUse(mc, partialTicks, itemToRenderInOffhand, player);
        } else {
            renderOffhandItemStatic(mc, partialTicks, itemToRenderInOffhand, player);
        }
    }
    
    private static void renderOffhandItemStatic(Minecraft mc, float partialTicks, ItemStack offhand, EntityClientPlayerMP player) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        
        try {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            int lightValue = mc.theWorld.getLightBrightnessForSkyBlocks(
                MathHelper.floor_double(player.posX),
                MathHelper.floor_double(player.posY),
                MathHelper.floor_double(player.posZ), 0
            );
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 
                (lightValue % 65536) / 1.0F, (lightValue / 65536) / 1.0F);
            
            int itemColor = Item.itemsList[offhand.itemID].getColorFromItemStack(offhand, 0);
            float colorR = (itemColor >> 16 & 0xFF) / 255.0F;
            float colorG = (itemColor >> 8 & 0xFF) / 255.0F;
            float colorB = (itemColor & 0xFF) / 255.0F;
            GL11.glColor4f(colorR, colorG, colorB, 1.0F);

            GL11.glScalef(-1.0F, 1.0F, 1.0F);
            GL11.glTranslatef(0.56F, -0.52F, -0.72F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(0.40F, 0.40F, 0.40F);
            GL11.glCullFace(GL11.GL_FRONT);

            if (offhand.getItem().requiresMultipleRenderPasses()) {
                RenderManager.instance.itemRenderer.renderItem((EntityLivingBase) player, offhand, 0);
                int pass1Color = Item.itemsList[offhand.itemID].getColorFromItemStack(offhand, 1);
                float pass1R = (pass1Color >> 16 & 0xFF) / 255.0F;
                float pass1G = (pass1Color >> 8 & 0xFF) / 255.0F;
                float pass1B = (pass1Color & 0xFF) / 255.0F;
                GL11.glColor4f(pass1R, pass1G, pass1B, 1.0F);
                RenderManager.instance.itemRenderer.renderItem((EntityLivingBase) player, offhand, 1);
            } else {
                RenderManager.instance.itemRenderer.renderItem((EntityLivingBase) player, offhand, 0);
            }
        } finally {
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }
    
    private static void renderOffhandItemInUse(Minecraft mc, float partialTicks, ItemStack itemInUse, EntityClientPlayerMP player) {
        int maxDuration = itemInUse.getMaxItemUseDuration();
        int fakeCount = maxDuration > 0 ? (maxDuration - (player.ticksExisted % maxDuration)) : 1;
        
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        
        try {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            int lightValue = mc.theWorld.getLightBrightnessForSkyBlocks(
                MathHelper.floor_double(player.posX),
                MathHelper.floor_double(player.posY),
                MathHelper.floor_double(player.posZ), 0
            );
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 
                (lightValue % 65536) / 1.0F, (lightValue / 65536) / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            int itemColor = Item.itemsList[itemInUse.itemID].getColorFromItemStack(itemInUse, 0);
            float colorR = (itemColor >> 16 & 0xFF) / 255.0F;
            float colorG = (itemColor >> 8 & 0xFF) / 255.0F;
            float colorB = (itemColor & 0xFF) / 255.0F;
            GL11.glColor4f(colorR, colorG, colorB, 1.0F);

            GL11.glScalef(-1.0F, 1.0F, 1.0F);
            GL11.glCullFace(GL11.GL_FRONT);

            EnumItemInUseAction useAction = itemInUse.getItemInUseAction(player);

            if (useAction == EnumItemInUseAction.EAT || useAction == EnumItemInUseAction.DRINK) {
                float f2 = fakeCount - partialTicks + 1.0F;
                float f1 = 1.0F - f2 / (float) maxDuration;
                float f3 = 1.0F - f1;
                f3 = f3 * f3 * f3;
                f3 = f3 * f3 * f3;
                f3 = f3 * f3 * f3;
                float progress = 1.0F - f3;
                GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(f2 / 4.0F * 3.1415927F) * 0.1F) * (f1 > 0.2F ? 1 : 0), 0.0F);
                GL11.glTranslatef(progress * 0.6F, -progress * 0.5F, 0.0F);
                GL11.glRotatef(progress * 90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(progress * 10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(progress * 30.0F, 0.0F, 0.0F, 1.0F);
            } else if (useAction == EnumItemInUseAction.BOW) {
                GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-8.0F, 1.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-0.4F, 0.2F, 0.0F);
                
                float ticksUsed = itemInUse.getMaxItemUseDuration() - fakeCount - partialTicks + 1.0F;
                float pullProgress = ticksUsed / ItemBow.getTicksForMaxPull(itemInUse);
                pullProgress = (pullProgress * pullProgress + pullProgress * 2.0F) / 3.0F;
                if (pullProgress > 1.0F) pullProgress = 1.0F;
                
                if (pullProgress > 0.1F) {
                    GL11.glTranslatef(0.0F, MathHelper.sin((ticksUsed - 0.1F) * 1.3F) * 0.01F * (pullProgress - 0.1F), 0.0F);
                }
                
                GL11.glTranslatef(0.0F, 0.0F, pullProgress * 0.1F);
                GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(0.0F, 0.5F, 0.0F);
                float bowScale = 1.0F + pullProgress * 0.2F;
                GL11.glScalef(1.0F, 1.0F, bowScale);
                GL11.glTranslatef(0.0F, -0.5F, 0.0F);
                GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            } else if (useAction == EnumItemInUseAction.BLOCK) {
                GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-80.0F, 1.0F, 0.0F, 1.0F);
                GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
            }

            GL11.glTranslatef(0.56F, -0.52F - (1.0F - 0.4F) * 0.6F, -0.72F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
            GL11.glEnable(32826);
            
            float itemScale = 0.4F;
            GL11.glScalef(itemScale, itemScale, itemScale);

            if (itemInUse.getItem().requiresMultipleRenderPasses()) {
                RenderManager.instance.itemRenderer.renderItem((EntityLivingBase) player, itemInUse, 0);
                int pass1Color = Item.itemsList[itemInUse.itemID].getColorFromItemStack(itemInUse, 1);
                float pass1R = (pass1Color >> 16 & 0xFF) / 255.0F;
                float pass1G = (pass1Color >> 8 & 0xFF) / 255.0F;
                float pass1B = (pass1Color & 0xFF) / 255.0F;
                GL11.glColor4f(pass1R, pass1G, pass1B, 1.0F);
                RenderManager.instance.itemRenderer.renderItem((EntityLivingBase) player, itemInUse, 1);
            } else {
                RenderManager.instance.itemRenderer.renderItem((EntityLivingBase) player, itemInUse, 0);
            }

            GL11.glDisable(32826);
        } finally {
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }
    
    public static void renderOffhandSlot(Minecraft mc, float partialTicks) {
        if (mc == null || mc.thePlayer == null) return;
        if (mc.theWorld == null) return;
        
        Object playerObj = mc.thePlayer;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;
        
        ItemStack offhand;
        if (offhandAccess.miteassistant$isUsingOffhand()) {
            offhand = mc.thePlayer.inventory.getCurrentItemStack();
        } else {
            offhand = offhandAccess.miteassistant$getOffhandStack();
        }
        
        if (!OffhandUtils.isValidOffhand(offhand)) return;
        
        ScaledResolution sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int screenWidth = sr.getScaledWidth();
        int screenHeight = sr.getScaledHeight();
        
        int hotbarX = screenWidth / 2 - 91;
        int hotbarY = screenHeight - 22;
        int offhandSlotX = hotbarX - 29;
        int offhandSlotY = hotbarY;
        
        mc.getTextureManager().bindTexture(WIDGETS_TEX_PATH);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        Gui.drawRect(offhandSlotX, offhandSlotY, offhandSlotX + 22, offhandSlotY + 22, 0x80000000);
        mc.getTextureManager().bindTexture(WIDGETS_TEX_PATH);
        
        int itemX = offhandSlotX + 3;
        int itemY = offhandSlotY + 3;
        
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.enableGUIStandardItemLighting();
        
        ITEM_RENDERER.renderItemAndEffectIntoGUI(
            mc.fontRenderer,
            mc.getTextureManager(),
            offhand,
            itemX,
            itemY
        );
        
        ITEM_RENDERER.renderItemOverlayIntoGUI(
            mc.fontRenderer,
            mc.getTextureManager(),
            offhand,
            itemX,
            itemY
        );
        
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }
}
