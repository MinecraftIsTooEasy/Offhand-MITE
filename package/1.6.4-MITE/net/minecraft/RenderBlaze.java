/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class RenderBlaze
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   private int field_77068_a;
/*    */   
/*    */   public RenderBlaze() {
/* 11 */     super(new ModelBlaze(), 0.5F);
/* 12 */     this.field_77068_a = ((ModelBlaze)this.mainModel).func_78104_a();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 17 */     setTexture(0, "textures/entity/blaze");
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderBlaze(EntityBlaze par1EntityBlaze, double par2, double par4, double par6, float par8, float par9) {
/* 22 */     int var10 = ((ModelBlaze)this.mainModel).func_78104_a();
/*    */     
/* 24 */     if (var10 != this.field_77068_a) {
/*    */       
/* 26 */       this.field_77068_a = var10;
/* 27 */       this.mainModel = new ModelBlaze();
/*    */     } 
/*    */     
/* 30 */     super.doRenderLiving(par1EntityBlaze, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getBlazeTextures(EntityBlaze par1EntityBlaze) {
/* 36 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 41 */     renderBlaze((EntityBlaze)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 46 */     renderBlaze((EntityBlaze)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 54 */     return getBlazeTextures((EntityBlaze)par1Entity);
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
/* 65 */     renderBlaze((EntityBlaze)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderBlaze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */