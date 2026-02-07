/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelBook
/*    */   extends ModelBase {
/*    */   public ModelRenderer coverRight;
/*    */   public ModelRenderer coverLeft;
/*    */   public ModelRenderer pagesRight;
/*    */   public ModelRenderer pagesLeft;
/*    */   public ModelRenderer flippingPageRight;
/*    */   public ModelRenderer flippingPageLeft;
/*    */   public ModelRenderer bookSpine;
/*    */   
/*    */   public ModelBook() {
/* 14 */     this.coverRight = (new ModelRenderer(this)).setTextureOffset(0, 0).addBox(-6.0F, -5.0F, 0.0F, 6, 10, 0);
/* 15 */     this.coverLeft = (new ModelRenderer(this)).setTextureOffset(16, 0).addBox(0.0F, -5.0F, 0.0F, 6, 10, 0);
/*    */     
/* 17 */     this.bookSpine = (new ModelRenderer(this)).setTextureOffset(12, 0).addBox(-1.0F, -5.0F, 0.0F, 2, 10, 0);
/*    */     
/* 19 */     this.pagesRight = (new ModelRenderer(this)).setTextureOffset(0, 10).addBox(0.0F, -4.0F, -0.99F, 5, 8, 1);
/* 20 */     this.pagesLeft = (new ModelRenderer(this)).setTextureOffset(12, 10).addBox(0.0F, -4.0F, -0.01F, 5, 8, 1);
/*    */     
/* 22 */     this.flippingPageRight = (new ModelRenderer(this)).setTextureOffset(24, 10).addBox(0.0F, -4.0F, 0.0F, 5, 8, 0);
/* 23 */     this.flippingPageLeft = (new ModelRenderer(this)).setTextureOffset(24, 10).addBox(0.0F, -4.0F, 0.0F, 5, 8, 0);
/*    */     
/* 25 */     this.coverRight.setRotationPoint(0.0F, 0.0F, -1.0F);
/* 26 */     this.coverLeft.setRotationPoint(0.0F, 0.0F, 1.0F);
/*    */     
/* 28 */     this.bookSpine.rotateAngleY = 1.5707964F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 33 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 35 */     this.coverRight.render(k);
/* 36 */     this.coverLeft.render(k);
/* 37 */     this.bookSpine.render(k);
/*    */     
/* 39 */     this.pagesRight.render(k);
/* 40 */     this.pagesLeft.render(k);
/*    */     
/* 42 */     this.flippingPageRight.render(k);
/* 43 */     this.flippingPageLeft.render(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 48 */     float f1 = (MathHelper.sin(f * 0.02F) * 0.1F + 1.25F) * i;
/*    */     
/* 50 */     this.coverRight.rotateAngleY = 3.1415927F + f1;
/* 51 */     this.coverLeft.rotateAngleY = -f1;
/* 52 */     this.pagesRight.rotateAngleY = f1;
/* 53 */     this.pagesLeft.rotateAngleY = -f1;
/*    */     
/* 55 */     this.flippingPageRight.rotateAngleY = f1 - f1 * 2.0F * g;
/* 56 */     this.flippingPageLeft.rotateAngleY = f1 - f1 * 2.0F * h;
/*    */     
/* 58 */     this.pagesRight.rotationPointX = MathHelper.sin(f1);
/* 59 */     this.pagesLeft.rotationPointX = MathHelper.sin(f1);
/* 60 */     this.flippingPageRight.rotationPointX = MathHelper.sin(f1);
/* 61 */     this.flippingPageLeft.rotationPointX = MathHelper.sin(f1);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */