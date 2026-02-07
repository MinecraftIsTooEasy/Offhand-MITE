/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderHellhound
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   
/*    */   public RenderHellhound(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
/* 13 */     super(par1ModelBase, par3);
/* 14 */     setRenderPassModel(par2ModelBase);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 19 */     setTexture(0, "textures/entity/hellhound/hellhound");
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getTailRotation(EntityWolf par1EntityWolf, float par2) {
/* 24 */     return par1EntityWolf.getTailRotation();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 32 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2) {
/* 40 */     return getTailRotation((EntityWolf)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 48 */     return this.textures[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderHellhound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */