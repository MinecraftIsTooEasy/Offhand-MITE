/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ModelZombie
/*    */   extends ModelBiped
/*    */ {
/*    */   public ModelZombie() {
/*  8 */     this(0.0F, false);
/*    */   }
/*    */   
/*    */   protected ModelZombie(float f, float g, int i, int j) {
/* 12 */     super(f, g, i, j);
/*    */   }
/*    */   
/*    */   public ModelZombie(float f, boolean bl) {
/* 16 */     super(f, 0.0F, 64, bl ? 32 : 64);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 21 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 23 */     float f1 = MathHelper.sin(this.onGround * 3.1415927F);
/* 24 */     float f2 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * 3.1415927F);
/* 25 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 26 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 27 */     this.bipedRightArm.rotateAngleY = -(0.1F - f1 * 0.6F);
/* 28 */     this.bipedLeftArm.rotateAngleY = 0.1F - f1 * 0.6F;
/* 29 */     this.bipedRightArm.rotateAngleX = -1.5707964F;
/* 30 */     this.bipedLeftArm.rotateAngleX = -1.5707964F;
/* 31 */     this.bipedRightArm.rotateAngleX -= f1 * 1.2F - f2 * 0.4F;
/* 32 */     this.bipedLeftArm.rotateAngleX -= f1 * 1.2F - f2 * 0.4F;
/*    */     
/* 34 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(h * 0.09F) * 0.05F + 0.05F;
/* 35 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(h * 0.09F) * 0.05F + 0.05F;
/* 36 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(h * 0.067F) * 0.05F;
/* 37 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(h * 0.067F) * 0.05F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */