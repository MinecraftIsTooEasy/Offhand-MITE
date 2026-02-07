/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderGhast
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   public static final int body_texture_shooting = 1;
/*    */   
/*    */   public RenderGhast() {
/* 15 */     super(new ModelGhast(), 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 20 */     setTexture(0, "textures/entity/ghast/ghast");
/* 21 */     setTexture(1, "textures/entity/ghast/ghast_shooting");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110867_a(EntityGhast par1EntityGhast) {
/* 27 */     return par1EntityGhast.func_110182_bF() ? this.textures[1] : this.textures[0];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderGhast(EntityGhast par1EntityGhast, float par2) {
/* 35 */     float var4 = (par1EntityGhast.prevAttackCounter + (par1EntityGhast.attackCounter - par1EntityGhast.prevAttackCounter) * par2) / 20.0F;
/*    */     
/* 37 */     if (var4 < 0.0F)
/*    */     {
/* 39 */       var4 = 0.0F;
/*    */     }
/*    */     
/* 42 */     var4 = 1.0F / (var4 * var4 * var4 * var4 * var4 * 2.0F + 1.0F);
/* 43 */     float var5 = (8.0F + var4) / 2.0F;
/* 44 */     float var6 = (8.0F + 1.0F / var4) / 2.0F;
/* 45 */     GL11.glScalef(var6, var5, var6);
/* 46 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 55 */     preRenderGhast((EntityGhast)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 63 */     return func_110867_a((EntityGhast)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderGhast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */