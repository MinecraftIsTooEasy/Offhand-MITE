/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelSnowMan
/*    */   extends ModelBase {
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer bottomBody;
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer rightHand;
/*    */   public ModelRenderer leftHand;
/*    */   
/*    */   public ModelSnowMan() {
/* 12 */     float f1 = 4.0F;
/* 13 */     float f2 = 0.0F;
/*    */     
/* 15 */     this.head = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
/* 16 */     this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f2 - 0.5F);
/* 17 */     this.head.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/*    */     
/* 19 */     this.rightHand = (new ModelRenderer(this, 32, 0)).setTextureSize(64, 64);
/* 20 */     this.rightHand.addBox(-1.0F, 0.0F, -1.0F, 12, 2, 2, f2 - 0.5F);
/* 21 */     this.rightHand.setRotationPoint(0.0F, 0.0F + f1 + 9.0F - 7.0F, 0.0F);
/*    */     
/* 23 */     this.leftHand = (new ModelRenderer(this, 32, 0)).setTextureSize(64, 64);
/* 24 */     this.leftHand.addBox(-1.0F, 0.0F, -1.0F, 12, 2, 2, f2 - 0.5F);
/* 25 */     this.leftHand.setRotationPoint(0.0F, 0.0F + f1 + 9.0F - 7.0F, 0.0F);
/*    */     
/* 27 */     this.body = (new ModelRenderer(this, 0, 16)).setTextureSize(64, 64);
/* 28 */     this.body.addBox(-5.0F, -10.0F, -5.0F, 10, 10, 10, f2 - 0.5F);
/* 29 */     this.body.setRotationPoint(0.0F, 0.0F + f1 + 9.0F, 0.0F);
/*    */     
/* 31 */     this.bottomBody = (new ModelRenderer(this, 0, 36)).setTextureSize(64, 64);
/* 32 */     this.bottomBody.addBox(-6.0F, -12.0F, -6.0F, 12, 12, 12, f2 - 0.5F);
/* 33 */     this.bottomBody.setRotationPoint(0.0F, 0.0F + f1 + 20.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 38 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/* 39 */     this.head.rotateAngleY = i / 57.295776F;
/* 40 */     this.head.rotateAngleX = j / 57.295776F;
/* 41 */     this.body.rotateAngleY = i / 57.295776F * 0.25F;
/*    */     
/* 43 */     float f1 = MathHelper.sin(this.body.rotateAngleY);
/* 44 */     float f2 = MathHelper.cos(this.body.rotateAngleY);
/*    */     
/* 46 */     this.rightHand.rotateAngleZ = 1.0F;
/* 47 */     this.leftHand.rotateAngleZ = -1.0F;
/* 48 */     this.rightHand.rotateAngleY = 0.0F + this.body.rotateAngleY;
/* 49 */     this.leftHand.rotateAngleY = 3.1415927F + this.body.rotateAngleY;
/*    */     
/* 51 */     this.rightHand.rotationPointX = f2 * 5.0F;
/* 52 */     this.rightHand.rotationPointZ = -f1 * 5.0F;
/*    */     
/* 54 */     this.leftHand.rotationPointX = -f2 * 5.0F;
/* 55 */     this.leftHand.rotationPointZ = f1 * 5.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 60 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 62 */     this.body.render(k);
/* 63 */     this.bottomBody.render(k);
/* 64 */     this.head.render(k);
/* 65 */     this.rightHand.render(k);
/* 66 */     this.leftHand.render(k);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSnowMan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */