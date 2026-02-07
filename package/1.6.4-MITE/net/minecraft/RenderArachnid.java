/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class RenderArachnid
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   
/*    */   public RenderArachnid(ModelArachnid base_model, ModelArachnid render_pass_model, float scale) {
/* 13 */     super(base_model, scale);
/* 14 */     setRenderPassModel(render_pass_model);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 19 */     setTexture(0, "textures/entity/spider/" + getSubtypeName());
/*    */   }
/*    */ 
/*    */   
/*    */   protected float setArachnidDeathMaxRotation(EntityArachnid par1EntityArachnid) {
/* 24 */     return 180.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getArachnidTextures(EntityArachnid par1EntityArachnid) {
/* 65 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getDeathMaxRotation(EntityLivingBase par1EntityLivingBase) {
/* 70 */     return setArachnidDeathMaxRotation((EntityArachnid)par1EntityLivingBase);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 86 */     return getArachnidTextures((EntityArachnid)par1Entity);
/*    */   }
/*    */   
/*    */   public abstract String getSubtypeName();
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderArachnid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */