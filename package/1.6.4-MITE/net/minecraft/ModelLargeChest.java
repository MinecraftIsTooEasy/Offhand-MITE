/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelLargeChest
/*    */   extends ModelChest
/*    */ {
/*    */   public ModelLargeChest() {
/*  7 */     this.chestLid = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
/*  8 */     this.chestLid.addBox(0.0F, -5.0F, -14.0F, 30, 5, 14, 0.0F);
/*  9 */     this.chestLid.rotationPointX = 1.0F;
/* 10 */     this.chestLid.rotationPointY = 7.0F;
/* 11 */     this.chestLid.rotationPointZ = 15.0F;
/*    */     
/* 13 */     this.chestKnob = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
/* 14 */     this.chestKnob.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
/* 15 */     this.chestKnob.rotationPointX = 16.0F;
/* 16 */     this.chestKnob.rotationPointY = 7.0F;
/* 17 */     this.chestKnob.rotationPointZ = 15.0F;
/*    */     
/* 19 */     this.chestBelow = (new ModelRenderer(this, 0, 19)).setTextureSize(128, 64);
/* 20 */     this.chestBelow.addBox(0.0F, 0.0F, 0.0F, 30, 10, 14, 0.0F);
/* 21 */     this.chestBelow.rotationPointX = 1.0F;
/* 22 */     this.chestBelow.rotationPointY = 6.0F;
/* 23 */     this.chestBelow.rotationPointZ = 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelLargeChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */