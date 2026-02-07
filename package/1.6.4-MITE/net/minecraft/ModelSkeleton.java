/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelSkeleton
/*    */   extends ModelZombie
/*    */ {
/*    */   public ModelSkeleton() {
/* 10 */     this(0.0F);
/*    */   }
/*    */   
/*    */   public ModelSkeleton(float f) {
/* 14 */     super(f, 0.0F, 64, 32);
/* 15 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/* 16 */     this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
/* 17 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/*    */     
/* 19 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/* 20 */     this.bipedLeftArm.mirror = true;
/* 21 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
/* 22 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/*    */     
/* 24 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/* 25 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, f);
/* 26 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/*    */     
/* 28 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/* 29 */     this.bipedLeftLeg.mirror = true;
/* 30 */     this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, f);
/* 31 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase entityLivingBase, float f, float g, float h) {
/* 36 */     this.aimedBow = (((EntitySkeleton)entityLivingBase).getSkeletonType() == 1);
/* 37 */     super.setLivingAnimations(entityLivingBase, f, g, h);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 42 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSkeleton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */