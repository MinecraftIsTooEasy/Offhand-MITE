/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderGiantZombie
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   private float scale;
/*    */   
/*    */   public RenderGiantZombie(ModelBase par1ModelBase, float par2, float par3) {
/* 15 */     super(par1ModelBase, par2 * par3);
/* 16 */     this.scale = par3;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 21 */     setTexture(0, "textures/entity/zombie/zombie");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderScale(EntityGiantZombie par1EntityGiantZombie, float par2) {
/* 29 */     GL11.glScalef(this.scale, this.scale, this.scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getZombieTextures(EntityGiantZombie par1EntityGiantZombie) {
/* 35 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 44 */     preRenderScale((EntityGiantZombie)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 52 */     return getZombieTextures((EntityGiantZombie)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderGiantZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */