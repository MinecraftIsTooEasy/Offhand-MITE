/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelCow
/*    */   extends ModelQuadruped
/*    */ {
/*    */   public ModelCow() {
/*  7 */     super(12, 0.0F);
/*    */     
/*  9 */     this.head = new ModelRenderer(this, 0, 0);
/* 10 */     this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
/* 11 */     this.head.setRotationPoint(0.0F, 4.0F, -8.0F);
/*    */     
/* 13 */     this.head.setTextureOffset(22, 0).addBox(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
/* 14 */     this.head.setTextureOffset(22, 0).addBox(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
/*    */     
/* 16 */     this.body = new ModelRenderer(this, 18, 4);
/* 17 */     this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
/* 18 */     this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
/* 19 */     this.body.setTextureOffset(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1);
/*    */     
/* 21 */     this.leg1.rotationPointX--;
/* 22 */     this.leg2.rotationPointX++;
/* 23 */     this.leg1.rotationPointZ += 0.0F;
/* 24 */     this.leg2.rotationPointZ += 0.0F;
/* 25 */     this.leg3.rotationPointX--;
/* 26 */     this.leg4.rotationPointX++;
/* 27 */     this.leg3.rotationPointZ--;
/* 28 */     this.leg4.rotationPointZ--;
/*    */     
/* 30 */     this.field_78151_h += 2.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelCow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */