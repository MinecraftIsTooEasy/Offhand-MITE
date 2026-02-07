/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class RenderSilverfish
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   public static final int texture_netherspawn = 1;
/*    */   public static final int texture_copperspine = 2;
/*    */   public static final int texture_hoary_silverfish = 3;
/*    */   
/*    */   public RenderSilverfish() {
/* 13 */     super(new ModelSilverfish(), 0.3F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 18 */     setTexture(0, "textures/entity/silverfish/silverfish");
/* 19 */     setTexture(1, "textures/entity/silverfish/netherspawn");
/* 20 */     setTexture(2, "textures/entity/silverfish/copperspine");
/* 21 */     setTexture(3, "textures/entity/silverfish/hoary");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float getSilverfishDeathRotation(EntitySilverfish par1EntitySilverfish) {
/* 29 */     return 180.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderSilverfish(EntitySilverfish par1EntitySilverfish, double par2, double par4, double par6, float par8, float par9) {
/* 37 */     super.doRenderLiving(par1EntitySilverfish, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getSilverfishTextures(EntitySilverfish par1EntitySilverfish) {
/* 44 */     return this.textures[par1EntitySilverfish.isNetherspawn() ? 1 : (par1EntitySilverfish.isCopperspine() ? 2 : (par1EntitySilverfish.isHoarySilverfish() ? 3 : 0))];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int shouldSilverfishRenderPass(EntitySilverfish par1EntitySilverfish, int par2, float par3) {
/* 52 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 57 */     renderSilverfish((EntitySilverfish)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getDeathMaxRotation(EntityLivingBase par1EntityLivingBase) {
/* 62 */     return getSilverfishDeathRotation((EntitySilverfish)par1EntityLivingBase);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 70 */     return shouldSilverfishRenderPass((EntitySilverfish)par1EntityLivingBase, par2, par3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 75 */     renderSilverfish((EntitySilverfish)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 83 */     return getSilverfishTextures((EntitySilverfish)par1Entity);
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
/* 94 */     renderSilverfish((EntitySilverfish)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderSilverfish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */