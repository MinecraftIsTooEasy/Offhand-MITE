/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderPig
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   public static final int body_texture_sick = 1;
/*    */   public static final int saddle_texture = 2;
/*    */   
/*    */   public RenderPig(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
/* 15 */     super(par1ModelBase, par3);
/* 16 */     setRenderPassModel(par2ModelBase);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 21 */     setTexture(0, "textures/entity/pig/pig");
/* 22 */     setTexture(1, "textures/entity/pig/sick");
/* 23 */     setTexture(2, "textures/entity/pig/pig_saddle");
/*    */   }
/*    */ 
/*    */   
/*    */   protected int renderSaddledPig(EntityPig par1EntityPig, int par2, float par3) {
/* 28 */     if (par2 == 0 && par1EntityPig.getSaddled()) {
/*    */ 
/*    */       
/* 31 */       bindTexture(this.textures[2]);
/* 32 */       return 1;
/*    */     } 
/*    */ 
/*    */     
/* 36 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getPigTextures(EntityPig par1EntityPig) {
/* 47 */     if (par1EntityPig.isWell()) {
/* 48 */       return this.textures[0];
/*    */     }
/* 50 */     return this.textures[1];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 58 */     return renderSaddledPig((EntityPig)par1EntityLivingBase, par2, par3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 66 */     return getPigTextures((EntityPig)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderPig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */