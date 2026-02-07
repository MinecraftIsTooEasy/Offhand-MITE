/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelSheep1
/*    */   extends ModelQuadruped
/*    */ {
/*    */   private float field_78152_i;
/*    */   
/*    */   public ModelSheep1() {
/* 13 */     super(12, 0.0F);
/*    */     
/* 15 */     this.head = new ModelRenderer(this, 0, 0);
/* 16 */     this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
/* 17 */     this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
/*    */     
/* 19 */     this.body = new ModelRenderer(this, 28, 8);
/* 20 */     this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
/* 21 */     this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
/*    */     
/* 23 */     float f = 0.5F;
/* 24 */     this.leg1 = new ModelRenderer(this, 0, 16);
/* 25 */     this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
/* 26 */     this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
/*    */     
/* 28 */     this.leg2 = new ModelRenderer(this, 0, 16);
/* 29 */     this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
/* 30 */     this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
/*    */     
/* 32 */     this.leg3 = new ModelRenderer(this, 0, 16);
/* 33 */     this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
/* 34 */     this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
/*    */     
/* 36 */     this.leg4 = new ModelRenderer(this, 0, 16);
/* 37 */     this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
/* 38 */     this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase entityLivingBase, float f, float g, float h) {
/* 43 */     super.setLivingAnimations(entityLivingBase, f, g, h);
/*    */     
/* 45 */     this.head.rotationPointY = 6.0F + ((EntitySheep)entityLivingBase).func_70894_j(h) * 9.0F;
/* 46 */     this.field_78152_i = ((EntitySheep)entityLivingBase).func_70890_k(h);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 51 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 53 */     this.head.rotateAngleX = this.field_78152_i;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSheep1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */