/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class RenderSquid
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   
/*    */   public RenderSquid(ModelBase par1ModelBase, float par2) {
/* 12 */     super(par1ModelBase, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 17 */     setTexture(0, "textures/entity/squid");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderLivingSquid(EntitySquid par1EntitySquid, double par2, double par4, double par6, float par8, float par9) {
/* 25 */     super.doRenderLiving(par1EntitySquid, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getSquidTextures(EntitySquid par1EntitySquid) {
/* 31 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void rotateSquidsCorpse(EntitySquid par1EntitySquid, float par2, float par3, float par4) {
/* 39 */     float var5 = par1EntitySquid.prevSquidPitch + (par1EntitySquid.squidPitch - par1EntitySquid.prevSquidPitch) * par4;
/* 40 */     float var6 = par1EntitySquid.prevSquidYaw + (par1EntitySquid.squidYaw - par1EntitySquid.prevSquidYaw) * par4;
/* 41 */     GL11.glTranslatef(0.0F, 0.5F, 0.0F);
/* 42 */     GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);
/* 43 */     GL11.glRotatef(var5, 1.0F, 0.0F, 0.0F);
/* 44 */     GL11.glRotatef(var6, 0.0F, 1.0F, 0.0F);
/* 45 */     GL11.glTranslatef(0.0F, -1.2F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected float handleRotationFloat(EntitySquid par1EntitySquid, float par2) {
/* 50 */     return par1EntitySquid.prevTentacleAngle + (par1EntitySquid.tentacleAngle - par1EntitySquid.prevTentacleAngle) * par2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 55 */     renderLivingSquid((EntitySquid)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2) {
/* 63 */     return handleRotationFloat((EntitySquid)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 68 */     rotateSquidsCorpse((EntitySquid)par1EntityLivingBase, par2, par3, par4);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 73 */     renderLivingSquid((EntitySquid)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 81 */     return getSquidTextures((EntitySquid)par1Entity);
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
/* 92 */     renderLivingSquid((EntitySquid)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderSquid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */