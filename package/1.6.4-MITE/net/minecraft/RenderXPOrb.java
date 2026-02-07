/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderXPOrb
/*    */   extends Render
/*    */ {
/* 13 */   private static final ResourceLocation experienceOrbTextures = new ResourceLocation("textures/entity/experience_orb.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderTheXPOrb(EntityXPOrb entityXPOrb, double d, double e, double f, float g, float h) {
/* 22 */     GL11.glPushMatrix();
/* 23 */     GL11.glTranslatef((float)d, (float)e, (float)f);
/*    */     
/* 25 */     bindEntityTexture(entityXPOrb);
/*    */     
/* 27 */     int i = entityXPOrb.getTextureByXP();
/* 28 */     float f1 = (i % 4 * 16 + 0) / 64.0F;
/* 29 */     float f2 = (i % 4 * 16 + 16) / 64.0F;
/* 30 */     float f3 = (i / 4 * 16 + 0) / 64.0F;
/* 31 */     float f4 = (i / 4 * 16 + 16) / 64.0F;
/*    */     
/* 33 */     float f5 = 1.0F;
/* 34 */     float f6 = 0.5F;
/* 35 */     float f7 = 0.25F;
/*    */ 
/*    */     
/* 38 */     int j = entityXPOrb.getBrightnessForRender(h);
/* 39 */     int k = j % 65536;
/* 40 */     int m = j / 65536;
/* 41 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k / 1.0F, m / 1.0F);
/* 42 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     float f8 = 255.0F;
/* 48 */     float f9 = (entityXPOrb.xpColor + h) / 2.0F;
/* 49 */     m = (int)((MathHelper.sin(f9 + 0.0F) + 1.0F) * 0.5F * f8);
/* 50 */     int n = (int)f8;
/* 51 */     int i1 = (int)((MathHelper.sin(f9 + 4.1887903F) + 1.0F) * 0.1F * f8);
/* 52 */     int i2 = m << 16 | n << 8 | i1;
/* 53 */     GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 54 */     GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 55 */     float f10 = 0.3F;
/* 56 */     GL11.glScalef(f10, f10, f10);
/* 57 */     Tessellator tessellator = Tessellator.instance;
/* 58 */     tessellator.startDrawingQuads();
/* 59 */     tessellator.setColorRGBA_I(i2, 128);
/* 60 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 61 */     tessellator.addVertexWithUV((0.0F - f6), (0.0F - f7), 0.0D, f1, f4);
/* 62 */     tessellator.addVertexWithUV((f5 - f6), (0.0F - f7), 0.0D, f2, f4);
/* 63 */     tessellator.addVertexWithUV((f5 - f6), (1.0F - f7), 0.0D, f2, f3);
/* 64 */     tessellator.addVertexWithUV((0.0F - f6), (1.0F - f7), 0.0D, f1, f3);
/* 65 */     tessellator.draw();
/*    */     
/* 67 */     GL11.glDisable(3042);
/* 68 */     GL11.glDisable(32826);
/* 69 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getExperienceOrbTextures(EntityXPOrb entityXPOrb) {
/* 74 */     return experienceOrbTextures;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderXPOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */