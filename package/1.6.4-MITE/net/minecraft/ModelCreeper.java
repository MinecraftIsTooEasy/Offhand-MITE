/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelCreeper
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer field_78133_b;
/*    */   public ModelRenderer body;
/*    */   
/*    */   public ModelCreeper() {
/* 11 */     this(0.0F);
/*    */   }
/*    */   public ModelRenderer leg1; public ModelRenderer leg2; public ModelRenderer leg3; public ModelRenderer leg4;
/*    */   public ModelCreeper(float f) {
/* 15 */     byte b = 4;
/*    */     
/* 17 */     this.head = new ModelRenderer(this, 0, 0);
/* 18 */     this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
/* 19 */     this.head.setRotationPoint(0.0F, b, 0.0F);
/*    */     
/* 21 */     this.field_78133_b = new ModelRenderer(this, 32, 0);
/* 22 */     this.field_78133_b.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f + 0.5F);
/* 23 */     this.field_78133_b.setRotationPoint(0.0F, b, 0.0F);
/*    */     
/* 25 */     this.body = new ModelRenderer(this, 16, 16);
/* 26 */     this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, f);
/* 27 */     this.body.setRotationPoint(0.0F, b, 0.0F);
/*    */     
/* 29 */     this.leg1 = new ModelRenderer(this, 0, 16);
/* 30 */     this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
/* 31 */     this.leg1.setRotationPoint(-2.0F, (12 + b), 4.0F);
/*    */     
/* 33 */     this.leg2 = new ModelRenderer(this, 0, 16);
/* 34 */     this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
/* 35 */     this.leg2.setRotationPoint(2.0F, (12 + b), 4.0F);
/*    */     
/* 37 */     this.leg3 = new ModelRenderer(this, 0, 16);
/* 38 */     this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
/* 39 */     this.leg3.setRotationPoint(-2.0F, (12 + b), -4.0F);
/*    */     
/* 41 */     this.leg4 = new ModelRenderer(this, 0, 16);
/* 42 */     this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
/* 43 */     this.leg4.setRotationPoint(2.0F, (12 + b), -4.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 48 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 50 */     this.head.render(k);
/* 51 */     this.body.render(k);
/* 52 */     this.leg1.render(k);
/* 53 */     this.leg2.render(k);
/* 54 */     this.leg3.render(k);
/* 55 */     this.leg4.render(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 60 */     this.head.rotateAngleY = i / 57.295776F;
/* 61 */     this.head.rotateAngleX = j / 57.295776F;
/*    */     
/* 63 */     this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
/* 64 */     this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
/* 65 */     this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
/* 66 */     this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelCreeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */