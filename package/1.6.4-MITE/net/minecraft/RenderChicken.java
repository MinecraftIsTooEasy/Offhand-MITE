/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderChicken
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   public static final int body_texture_sick = 1;
/*    */   
/*    */   public RenderChicken(ModelBase par1ModelBase, float par2) {
/* 13 */     super(par1ModelBase, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 18 */     setTexture(0, "textures/entity/chicken");
/* 19 */     setTexture(1, "textures/entity/chicken/sick");
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderChicken(EntityChicken par1EntityChicken, double par2, double par4, double par6, float par8, float par9) {
/* 24 */     super.doRenderLiving(par1EntityChicken, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getChickenTextures(EntityChicken par1EntityChicken) {
/* 34 */     if (par1EntityChicken.isWell()) {
/* 35 */       return this.textures[0];
/*    */     }
/* 37 */     return this.textures[1];
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getWingRotation(EntityChicken par1EntityChicken, float par2) {
/* 42 */     float var3 = par1EntityChicken.field_70888_h + (par1EntityChicken.field_70886_e - par1EntityChicken.field_70888_h) * par2;
/* 43 */     float var4 = par1EntityChicken.field_70884_g + (par1EntityChicken.destPos - par1EntityChicken.field_70884_g) * par2;
/* 44 */     return (MathHelper.sin(var3) + 1.0F) * var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 49 */     renderChicken((EntityChicken)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2) {
/* 57 */     return getWingRotation((EntityChicken)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 62 */     renderChicken((EntityChicken)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 70 */     return getChickenTextures((EntityChicken)par1Entity);
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
/* 81 */     renderChicken((EntityChicken)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderChicken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */