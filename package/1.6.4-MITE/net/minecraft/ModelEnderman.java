/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ModelEnderman
/*    */   extends ModelBiped
/*    */ {
/*    */   public boolean isCarrying;
/*    */   public boolean isAttacking;
/*    */   
/*    */   public ModelEnderman() {
/* 11 */     super(0.0F, -14.0F, 64, 32);
/* 12 */     float f1 = -14.0F;
/* 13 */     float f2 = 0.0F;
/*    */     
/* 15 */     this.bipedHeadwear = new ModelRenderer(this, 0, 16);
/* 16 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f2 - 0.5F);
/* 17 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/*    */     
/* 19 */     this.bipedBody = new ModelRenderer(this, 32, 16);
/* 20 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, f2);
/* 21 */     this.bipedBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/*    */     
/* 23 */     this.bipedRightArm = new ModelRenderer(this, 56, 0);
/* 24 */     this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, f2);
/* 25 */     this.bipedRightArm.setRotationPoint(-3.0F, 2.0F + f1, 0.0F);
/*    */     
/* 27 */     this.bipedLeftArm = new ModelRenderer(this, 56, 0);
/* 28 */     this.bipedLeftArm.mirror = true;
/* 29 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, f2);
/* 30 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + f1, 0.0F);
/*    */     
/* 32 */     this.bipedRightLeg = new ModelRenderer(this, 56, 0);
/* 33 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, f2);
/* 34 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + f1, 0.0F);
/*    */     
/* 36 */     this.bipedLeftLeg = new ModelRenderer(this, 56, 0);
/* 37 */     this.bipedLeftLeg.mirror = true;
/* 38 */     this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, f2);
/* 39 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + f1, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 44 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 46 */     this.bipedHead.showModel = true;
/*    */     
/* 48 */     float f1 = -14.0F;
/* 49 */     this.bipedBody.rotateAngleX = 0.0F;
/* 50 */     this.bipedBody.rotationPointY = f1;
/* 51 */     this.bipedBody.rotationPointZ = -0.0F;
/*    */     
/* 53 */     this.bipedRightLeg.rotateAngleX -= 0.0F;
/* 54 */     this.bipedLeftLeg.rotateAngleX -= 0.0F;
/*    */     
/* 56 */     this.bipedRightArm.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX * 0.5D);
/* 57 */     this.bipedLeftArm.rotateAngleX = (float)(this.bipedLeftArm.rotateAngleX * 0.5D);
/* 58 */     this.bipedRightLeg.rotateAngleX = (float)(this.bipedRightLeg.rotateAngleX * 0.5D);
/* 59 */     this.bipedLeftLeg.rotateAngleX = (float)(this.bipedLeftLeg.rotateAngleX * 0.5D);
/*    */     
/* 61 */     float f2 = 0.4F;
/* 62 */     if (this.bipedRightArm.rotateAngleX > f2) this.bipedRightArm.rotateAngleX = f2; 
/* 63 */     if (this.bipedLeftArm.rotateAngleX > f2) this.bipedLeftArm.rotateAngleX = f2; 
/* 64 */     if (this.bipedRightArm.rotateAngleX < -f2) this.bipedRightArm.rotateAngleX = -f2; 
/* 65 */     if (this.bipedLeftArm.rotateAngleX < -f2) this.bipedLeftArm.rotateAngleX = -f2; 
/* 66 */     if (this.bipedRightLeg.rotateAngleX > f2) this.bipedRightLeg.rotateAngleX = f2; 
/* 67 */     if (this.bipedLeftLeg.rotateAngleX > f2) this.bipedLeftLeg.rotateAngleX = f2; 
/* 68 */     if (this.bipedRightLeg.rotateAngleX < -f2) this.bipedRightLeg.rotateAngleX = -f2; 
/* 69 */     if (this.bipedLeftLeg.rotateAngleX < -f2) this.bipedLeftLeg.rotateAngleX = -f2;
/*    */     
/* 71 */     if (this.isCarrying) {
/* 72 */       this.bipedRightArm.rotateAngleX = -0.5F;
/* 73 */       this.bipedLeftArm.rotateAngleX = -0.5F;
/* 74 */       this.bipedRightArm.rotateAngleZ = 0.05F;
/* 75 */       this.bipedLeftArm.rotateAngleZ = -0.05F;
/*    */     } 
/*    */     
/* 78 */     this.bipedRightArm.rotationPointZ = 0.0F;
/* 79 */     this.bipedLeftArm.rotationPointZ = 0.0F;
/* 80 */     this.bipedRightLeg.rotationPointZ = 0.0F;
/* 81 */     this.bipedLeftLeg.rotationPointZ = 0.0F;
/*    */     
/* 83 */     this.bipedRightLeg.rotationPointY = 9.0F + f1;
/* 84 */     this.bipedLeftLeg.rotationPointY = 9.0F + f1;
/*    */     
/* 86 */     this.bipedHead.rotationPointZ = -0.0F;
/* 87 */     this.bipedHead.rotationPointY = f1 + 1.0F;
/*    */     
/* 89 */     this.bipedHeadwear.rotationPointX = this.bipedHead.rotationPointX;
/* 90 */     this.bipedHeadwear.rotationPointY = this.bipedHead.rotationPointY;
/* 91 */     this.bipedHeadwear.rotationPointZ = this.bipedHead.rotationPointZ;
/* 92 */     this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
/* 93 */     this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
/* 94 */     this.bipedHeadwear.rotateAngleZ = this.bipedHead.rotateAngleZ;
/*    */     
/* 96 */     if (this.isAttacking) {
/* 97 */       float f3 = 1.0F;
/* 98 */       this.bipedHead.rotationPointY -= f3 * 5.0F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelEnderman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */