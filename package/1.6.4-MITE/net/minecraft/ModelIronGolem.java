/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelIronGolem
/*    */   extends ModelBase {
/*    */   public ModelRenderer ironGolemHead;
/*    */   public ModelRenderer ironGolemBody;
/*    */   public ModelRenderer ironGolemRightArm;
/*    */   public ModelRenderer ironGolemLeftArm;
/*    */   public ModelRenderer ironGolemLeftLeg;
/*    */   public ModelRenderer ironGolemRightLeg;
/*    */   
/*    */   public ModelIronGolem() {
/* 13 */     this(0.0F);
/*    */   }
/*    */   
/*    */   public ModelIronGolem(float f) {
/* 17 */     this(f, -7.0F);
/*    */   }
/*    */   
/*    */   public ModelIronGolem(float f, float g) {
/* 21 */     char c1 = '';
/* 22 */     char c2 = '';
/*    */     
/* 24 */     this.ironGolemHead = (new ModelRenderer(this)).setTextureSize(c1, c2);
/* 25 */     this.ironGolemHead.setRotationPoint(0.0F, 0.0F + g, -2.0F);
/* 26 */     this.ironGolemHead.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, f);
/* 27 */     this.ironGolemHead.setTextureOffset(24, 0).addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2, f);
/*    */     
/* 29 */     this.ironGolemBody = (new ModelRenderer(this)).setTextureSize(c1, c2);
/* 30 */     this.ironGolemBody.setRotationPoint(0.0F, 0.0F + g, 0.0F);
/* 31 */     this.ironGolemBody.setTextureOffset(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, f);
/* 32 */     this.ironGolemBody.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9, 5, 6, f + 0.5F);
/*    */     
/* 34 */     this.ironGolemRightArm = (new ModelRenderer(this)).setTextureSize(c1, c2);
/* 35 */     this.ironGolemRightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
/* 36 */     this.ironGolemRightArm.setTextureOffset(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4, 30, 6, f);
/*    */     
/* 38 */     this.ironGolemLeftArm = (new ModelRenderer(this)).setTextureSize(c1, c2);
/* 39 */     this.ironGolemLeftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
/* 40 */     this.ironGolemLeftArm.setTextureOffset(60, 58).addBox(9.0F, -2.5F, -3.0F, 4, 30, 6, f);
/*    */     
/* 42 */     this.ironGolemLeftLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(c1, c2);
/* 43 */     this.ironGolemLeftLeg.setRotationPoint(-4.0F, 18.0F + g, 0.0F);
/* 44 */     this.ironGolemLeftLeg.setTextureOffset(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, f);
/*    */     
/* 46 */     this.ironGolemRightLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(c1, c2);
/* 47 */     this.ironGolemRightLeg.mirror = true;
/* 48 */     this.ironGolemRightLeg.setTextureOffset(60, 0).setRotationPoint(5.0F, 18.0F + g, 0.0F);
/* 49 */     this.ironGolemRightLeg.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 54 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 56 */     this.ironGolemHead.render(k);
/* 57 */     this.ironGolemBody.render(k);
/* 58 */     this.ironGolemLeftLeg.render(k);
/* 59 */     this.ironGolemRightLeg.render(k);
/* 60 */     this.ironGolemRightArm.render(k);
/* 61 */     this.ironGolemLeftArm.render(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 66 */     this.ironGolemHead.rotateAngleY = i / 57.295776F;
/* 67 */     this.ironGolemHead.rotateAngleX = j / 57.295776F;
/*    */     
/* 69 */     this.ironGolemLeftLeg.rotateAngleX = -1.5F * func_78172_a(f, 13.0F) * g;
/* 70 */     this.ironGolemRightLeg.rotateAngleX = 1.5F * func_78172_a(f, 13.0F) * g;
/* 71 */     this.ironGolemLeftLeg.rotateAngleY = 0.0F;
/* 72 */     this.ironGolemRightLeg.rotateAngleY = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase entityLivingBase, float f, float g, float h) {
/* 77 */     EntityIronGolem entityIronGolem = (EntityIronGolem)entityLivingBase;
/*    */     
/* 79 */     int i = entityIronGolem.getAttackTimer();
/* 80 */     if (i > 0) {
/* 81 */       this.ironGolemRightArm.rotateAngleX = -2.0F + 1.5F * func_78172_a(i - h, 10.0F);
/* 82 */       this.ironGolemLeftArm.rotateAngleX = -2.0F + 1.5F * func_78172_a(i - h, 10.0F);
/*    */     } else {
/* 84 */       int j = entityIronGolem.getHoldRoseTick();
/* 85 */       if (j > 0) {
/* 86 */         this.ironGolemRightArm.rotateAngleX = -0.8F + 0.025F * func_78172_a(j, 70.0F);
/* 87 */         this.ironGolemLeftArm.rotateAngleX = 0.0F;
/*    */       } else {
/* 89 */         this.ironGolemRightArm.rotateAngleX = (-0.2F + 1.5F * func_78172_a(f, 13.0F)) * g;
/* 90 */         this.ironGolemLeftArm.rotateAngleX = (-0.2F - 1.5F * func_78172_a(f, 13.0F)) * g;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private float func_78172_a(float f, float g) {
/* 96 */     return (Math.abs(f % g - g * 0.5F) - g * 0.25F) / g * 0.25F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelIronGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */