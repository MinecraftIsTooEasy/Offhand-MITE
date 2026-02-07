/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelBoat
/*    */   extends ModelBase
/*    */ {
/*  8 */   public ModelRenderer[] boatSides = new ModelRenderer[5];
/*    */   
/*    */   public ModelBoat() {
/* 11 */     this.boatSides[0] = new ModelRenderer(this, 0, 8);
/* 12 */     this.boatSides[1] = new ModelRenderer(this, 0, 0);
/* 13 */     this.boatSides[2] = new ModelRenderer(this, 0, 0);
/* 14 */     this.boatSides[3] = new ModelRenderer(this, 0, 0);
/* 15 */     this.boatSides[4] = new ModelRenderer(this, 0, 0);
/*    */     
/* 17 */     byte b1 = 24;
/* 18 */     byte b2 = 6;
/* 19 */     byte b3 = 20;
/* 20 */     byte b4 = 4;
/*    */     
/* 22 */     this.boatSides[0].addBox((-b1 / 2), (-b3 / 2 + 2), -3.0F, b1, b3 - 4, 4, 0.0F);
/* 23 */     this.boatSides[0].setRotationPoint(0.0F, b4, 0.0F);
/*    */     
/* 25 */     this.boatSides[1].addBox((-b1 / 2 + 2), (-b2 - 1), -1.0F, b1 - 4, b2, 2, 0.0F);
/* 26 */     this.boatSides[1].setRotationPoint((-b1 / 2 + 1), b4, 0.0F);
/*    */     
/* 28 */     this.boatSides[2].addBox((-b1 / 2 + 2), (-b2 - 1), -1.0F, b1 - 4, b2, 2, 0.0F);
/* 29 */     this.boatSides[2].setRotationPoint((b1 / 2 - 1), b4, 0.0F);
/*    */     
/* 31 */     this.boatSides[3].addBox((-b1 / 2 + 2), (-b2 - 1), -1.0F, b1 - 4, b2, 2, 0.0F);
/* 32 */     this.boatSides[3].setRotationPoint(0.0F, b4, (-b3 / 2 + 1));
/*    */     
/* 34 */     this.boatSides[4].addBox((-b1 / 2 + 2), (-b2 - 1), -1.0F, b1 - 4, b2, 2, 0.0F);
/* 35 */     this.boatSides[4].setRotationPoint(0.0F, b4, (b3 / 2 - 1));
/*    */     
/* 37 */     (this.boatSides[0]).rotateAngleX = 1.5707964F;
/* 38 */     (this.boatSides[1]).rotateAngleY = 4.712389F;
/* 39 */     (this.boatSides[2]).rotateAngleY = 1.5707964F;
/* 40 */     (this.boatSides[3]).rotateAngleY = 3.1415927F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 45 */     for (byte b = 0; b < 5; b++)
/* 46 */       this.boatSides[b].render(k); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelBoat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */