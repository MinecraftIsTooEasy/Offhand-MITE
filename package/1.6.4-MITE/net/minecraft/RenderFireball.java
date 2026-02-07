/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class RenderFireball
/*    */   extends Render
/*    */ {
/*    */   private float field_77002_a;
/*    */   
/*    */   public RenderFireball(float par1) {
/* 12 */     this.field_77002_a = par1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderFireball(EntityFireball par1EntityFireball, double par2, double par4, double par6, float par8, float par9) {
/* 17 */     GL11.glPushMatrix();
/* 18 */     bindEntityTexture(par1EntityFireball);
/* 19 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 20 */     GL11.glEnable(32826);
/* 21 */     float var10 = this.field_77002_a;
/* 22 */     GL11.glScalef(var10 / 1.0F, var10 / 1.0F, var10 / 1.0F);
/* 23 */     Icon var11 = Item.fireballCharge.getIconFromSubtype(0);
/* 24 */     Tessellator var12 = Tessellator.instance;
/* 25 */     float var13 = var11.getMinU();
/* 26 */     float var14 = var11.getMaxU();
/* 27 */     float var15 = var11.getMinV();
/* 28 */     float var16 = var11.getMaxV();
/* 29 */     float var17 = 1.0F;
/* 30 */     float var18 = 0.5F;
/* 31 */     float var19 = 0.25F;
/* 32 */     GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 33 */     GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 34 */     var12.startDrawingQuads();
/* 35 */     var12.setNormal(0.0F, 1.0F, 0.0F);
/* 36 */     var12.addVertexWithUV((0.0F - var18), (0.0F - var19), 0.0D, var13, var16);
/* 37 */     var12.addVertexWithUV((var17 - var18), (0.0F - var19), 0.0D, var14, var16);
/* 38 */     var12.addVertexWithUV((var17 - var18), (1.0F - var19), 0.0D, var14, var15);
/* 39 */     var12.addVertexWithUV((0.0F - var18), (1.0F - var19), 0.0D, var13, var15);
/* 40 */     var12.draw();
/* 41 */     GL11.glDisable(32826);
/* 42 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getFireballTextures(EntityFireball par1EntityFireball) {
/* 47 */     return TextureMap.locationItemsTexture;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 55 */     return getFireballTextures((EntityFireball)par1Entity);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 66 */     doRenderFireball((EntityFireball)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderFireball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */