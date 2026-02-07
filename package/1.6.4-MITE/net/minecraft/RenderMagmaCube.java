/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class RenderMagmaCube
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   
/*    */   public RenderMagmaCube() {
/* 12 */     super(new ModelMagmaCube(), 0.25F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 17 */     setTexture(0, "textures/entity/slime/magmacube");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getMagmaCubeTextures(EntityMagmaCube par1EntityMagmaCube) {
/* 23 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */   
/*    */   protected void scaleMagmaCube(EntityMagmaCube par1EntityMagmaCube, float par2) {
/* 28 */     int var3 = par1EntityMagmaCube.getSize();
/* 29 */     float var4 = (par1EntityMagmaCube.prevSquishFactor + (par1EntityMagmaCube.squishFactor - par1EntityMagmaCube.prevSquishFactor) * par2) / (var3 * 0.5F + 1.0F);
/* 30 */     float var5 = 1.0F / (var4 + 1.0F);
/* 31 */     float var6 = var3;
/* 32 */     GL11.glScalef(var5 * var6, 1.0F / var5 * var6, var5 * var6);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 41 */     scaleMagmaCube((EntityMagmaCube)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 49 */     return getMagmaCubeTextures((EntityMagmaCube)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderMagmaCube.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */