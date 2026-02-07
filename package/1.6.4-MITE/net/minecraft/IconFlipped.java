/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public final class IconFlipped
/*    */   implements Icon
/*    */ {
/*    */   private final Icon baseIcon;
/*    */   private final boolean flipU;
/*    */   private final boolean flipV;
/*    */   
/*    */   public IconFlipped(Icon par1Icon, boolean par2, boolean par3) {
/* 12 */     this.baseIcon = par1Icon;
/* 13 */     this.flipU = par2;
/* 14 */     this.flipV = par3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIconWidth() {
/* 22 */     return this.baseIcon.getIconWidth();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIconHeight() {
/* 30 */     return this.baseIcon.getIconHeight();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getMinU() {
/* 38 */     return this.flipU ? this.baseIcon.getMaxU() : this.baseIcon.getMinU();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getMaxU() {
/* 46 */     return this.flipU ? this.baseIcon.getMinU() : this.baseIcon.getMaxU();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getInterpolatedU(double par1) {
/* 54 */     float var3 = getMaxU() - getMinU();
/* 55 */     return getMinU() + var3 * (float)par1 / 16.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getMinV() {
/* 63 */     return this.flipV ? this.baseIcon.getMinV() : this.baseIcon.getMinV();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getMaxV() {
/* 71 */     return this.flipV ? this.baseIcon.getMinV() : this.baseIcon.getMaxV();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getInterpolatedV(double par1) {
/* 79 */     float var3 = getMaxV() - getMinV();
/* 80 */     return getMinV() + var3 * (float)par1 / 16.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getIconName() {
/* 85 */     return this.baseIcon.getIconName();
/*    */   }
/*    */ 
/*    */   
/*    */   public final boolean isGreenGrassSide() {
/* 90 */     return this.baseIcon.isGreenGrassSide();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IconFlipped.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */