/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelMinecart
/*    */   extends ModelBase
/*    */ {
/*  8 */   public ModelRenderer[] sideModels = new ModelRenderer[7];
/*    */   
/*    */   public ModelMinecart() {
/* 11 */     this.sideModels[0] = new ModelRenderer(this, 0, 10);
/* 12 */     this.sideModels[1] = new ModelRenderer(this, 0, 0);
/* 13 */     this.sideModels[2] = new ModelRenderer(this, 0, 0);
/* 14 */     this.sideModels[3] = new ModelRenderer(this, 0, 0);
/* 15 */     this.sideModels[4] = new ModelRenderer(this, 0, 0);
/* 16 */     this.sideModels[5] = new ModelRenderer(this, 44, 10);
/*    */     
/* 18 */     byte b1 = 20;
/* 19 */     byte b2 = 8;
/* 20 */     byte b3 = 16;
/* 21 */     byte b4 = 4;
/*    */     
/* 23 */     this.sideModels[0].addBox((-b1 / 2), (-b3 / 2), -1.0F, b1, b3, 2, 0.0F);
/* 24 */     this.sideModels[0].setRotationPoint(0.0F, b4, 0.0F);
/*    */     
/* 26 */     this.sideModels[5].addBox((-b1 / 2 + 1), (-b3 / 2 + 1), -1.0F, b1 - 2, b3 - 2, 1, 0.0F);
/* 27 */     this.sideModels[5].setRotationPoint(0.0F, b4, 0.0F);
/*    */     
/* 29 */     this.sideModels[1].addBox((-b1 / 2 + 2), (-b2 - 1), -1.0F, b1 - 4, b2, 2, 0.0F);
/* 30 */     this.sideModels[1].setRotationPoint((-b1 / 2 + 1), b4, 0.0F);
/*    */     
/* 32 */     this.sideModels[2].addBox((-b1 / 2 + 2), (-b2 - 1), -1.0F, b1 - 4, b2, 2, 0.0F);
/* 33 */     this.sideModels[2].setRotationPoint((b1 / 2 - 1), b4, 0.0F);
/*    */     
/* 35 */     this.sideModels[3].addBox((-b1 / 2 + 2), (-b2 - 1), -1.0F, b1 - 4, b2, 2, 0.0F);
/* 36 */     this.sideModels[3].setRotationPoint(0.0F, b4, (-b3 / 2 + 1));
/*    */     
/* 38 */     this.sideModels[4].addBox((-b1 / 2 + 2), (-b2 - 1), -1.0F, b1 - 4, b2, 2, 0.0F);
/* 39 */     this.sideModels[4].setRotationPoint(0.0F, b4, (b3 / 2 - 1));
/*    */     
/* 41 */     (this.sideModels[0]).rotateAngleX = 1.5707964F;
/* 42 */     (this.sideModels[1]).rotateAngleY = 4.712389F;
/* 43 */     (this.sideModels[2]).rotateAngleY = 1.5707964F;
/* 44 */     (this.sideModels[3]).rotateAngleY = 3.1415927F;
/* 45 */     (this.sideModels[5]).rotateAngleX = -1.5707964F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 50 */     (this.sideModels[5]).rotationPointY = 4.0F - h;
/* 51 */     for (byte b = 0; b < 6; b++)
/* 52 */       this.sideModels[b].render(k); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelMinecart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */