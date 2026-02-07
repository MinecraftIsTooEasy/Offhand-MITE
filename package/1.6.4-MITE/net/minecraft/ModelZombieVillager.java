/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelZombieVillager
/*    */   extends ModelBiped
/*    */ {
/*    */   public ModelZombieVillager() {
/*  9 */     this(0.0F, 0.0F, false);
/*    */   }
/*    */   
/*    */   public ModelZombieVillager(float f, float g, boolean bl) {
/* 13 */     super(f, 0.0F, 64, bl ? 32 : 64);
/*    */     
/* 15 */     if (bl) {
/* 16 */       this.bipedHead = new ModelRenderer(this, 0, 0);
/* 17 */       this.bipedHead.addBox(-4.0F, -10.0F, -4.0F, 8, 6, 8, f);
/* 18 */       this.bipedHead.setRotationPoint(0.0F, 0.0F + g, 0.0F);
/*    */     } else {
/* 20 */       this.bipedHead = new ModelRenderer(this);
/* 21 */       this.bipedHead.setRotationPoint(0.0F, 0.0F + g, 0.0F);
/* 22 */       this.bipedHead.setTextureOffset(0, 32).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, f);
/* 23 */       this.bipedHead.setTextureOffset(24, 32).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, f);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_82897_a() {
/* 29 */     return 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 34 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 36 */     float f1 = MathHelper.sin(this.onGround * 3.1415927F);
/* 37 */     float f2 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * 3.1415927F);
/* 38 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 39 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 40 */     this.bipedRightArm.rotateAngleY = -(0.1F - f1 * 0.6F);
/* 41 */     this.bipedLeftArm.rotateAngleY = 0.1F - f1 * 0.6F;
/* 42 */     this.bipedRightArm.rotateAngleX = -1.5707964F;
/* 43 */     this.bipedLeftArm.rotateAngleX = -1.5707964F;
/* 44 */     this.bipedRightArm.rotateAngleX -= f1 * 1.2F - f2 * 0.4F;
/* 45 */     this.bipedLeftArm.rotateAngleX -= f1 * 1.2F - f2 * 0.4F;
/*    */     
/* 47 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(h * 0.09F) * 0.05F + 0.05F;
/* 48 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(h * 0.09F) * 0.05F + 0.05F;
/* 49 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(h * 0.067F) * 0.05F;
/* 50 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(h * 0.067F) * 0.05F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelZombieVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */