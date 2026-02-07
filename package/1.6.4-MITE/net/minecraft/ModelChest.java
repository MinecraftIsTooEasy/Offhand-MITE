/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelChest
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer chestLid;
/*    */   public ModelRenderer chestBelow;
/*    */   public ModelRenderer chestKnob;
/*    */   
/*    */   public ModelChest() {
/* 11 */     this.chestLid = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
/* 12 */     this.chestLid.addBox(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
/* 13 */     this.chestLid.rotationPointX = 1.0F;
/* 14 */     this.chestLid.rotationPointY = 7.0F;
/* 15 */     this.chestLid.rotationPointZ = 15.0F;
/*    */     
/* 17 */     this.chestKnob = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
/* 18 */     this.chestKnob.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
/* 19 */     this.chestKnob.rotationPointX = 8.0F;
/* 20 */     this.chestKnob.rotationPointY = 7.0F;
/* 21 */     this.chestKnob.rotationPointZ = 15.0F;
/*    */     
/* 23 */     this.chestBelow = (new ModelRenderer(this, 0, 19)).setTextureSize(64, 64);
/* 24 */     this.chestBelow.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
/* 25 */     this.chestBelow.rotationPointX = 1.0F;
/* 26 */     this.chestBelow.rotationPointY = 6.0F;
/* 27 */     this.chestBelow.rotationPointZ = 1.0F;
/*    */   }
/*    */   
/*    */   public void renderAll() {
/* 31 */     this.chestKnob.rotateAngleX = this.chestLid.rotateAngleX;
/*    */     
/* 33 */     this.chestLid.render(0.0625F);
/* 34 */     this.chestKnob.render(0.0625F);
/* 35 */     this.chestBelow.render(0.0625F);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */