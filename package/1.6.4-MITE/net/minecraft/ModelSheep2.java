/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelSheep2
/*    */   extends ModelQuadruped
/*    */ {
/*    */   private float field_78153_i;
/*    */   
/*    */   public ModelSheep2() {
/* 13 */     super(12, 0.0F);
/*    */     
/* 15 */     this.head = new ModelRenderer(this, 0, 0);
/* 16 */     this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
/* 17 */     this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
/*    */     
/* 19 */     this.body = new ModelRenderer(this, 28, 8);
/* 20 */     this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
/* 21 */     this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase entityLivingBase, float f, float g, float h) {
/* 26 */     super.setLivingAnimations(entityLivingBase, f, g, h);
/*    */     
/* 28 */     this.head.rotationPointY = 6.0F + ((EntitySheep)entityLivingBase).func_70894_j(h) * 9.0F;
/* 29 */     this.field_78153_i = ((EntitySheep)entityLivingBase).func_70890_k(h);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 34 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 36 */     this.head.rotateAngleX = this.field_78153_i;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSheep2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */