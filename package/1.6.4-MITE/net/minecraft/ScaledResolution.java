/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScaledResolution
/*    */ {
/*    */   private int scaledWidth;
/*    */   private int scaledHeight;
/*    */   private double scaledWidthD;
/*    */   private double scaledHeightD;
/*    */   private int scaleFactor;
/*    */   
/*    */   public ScaledResolution(GameSettings gameSettings, int i, int j) {
/* 14 */     this.scaledWidth = i;
/* 15 */     this.scaledHeight = j;
/* 16 */     this.scaleFactor = 1;
/*    */     
/* 18 */     int k = gameSettings.guiScale;
/* 19 */     if (k == 0) k = 1000; 
/* 20 */     while (this.scaleFactor < k && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
/* 21 */       this.scaleFactor++;
/*    */     }
/* 23 */     this.scaledWidthD = this.scaledWidth / this.scaleFactor;
/* 24 */     this.scaledHeightD = this.scaledHeight / this.scaleFactor;
/* 25 */     this.scaledWidth = MathHelper.ceiling_double_int(this.scaledWidthD);
/* 26 */     this.scaledHeight = MathHelper.ceiling_double_int(this.scaledHeightD);
/*    */   }
/*    */   
/*    */   public int getScaledWidth() {
/* 30 */     return this.scaledWidth;
/*    */   }
/*    */   
/*    */   public int getScaledHeight() {
/* 34 */     return this.scaledHeight;
/*    */   }
/*    */   
/*    */   public double getScaledWidth_double() {
/* 38 */     return this.scaledWidthD;
/*    */   }
/*    */   
/*    */   public double getScaledHeight_double() {
/* 42 */     return this.scaledHeightD;
/*    */   }
/*    */   
/*    */   public int getScaleFactor() {
/* 46 */     return this.scaleFactor;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScaledResolution.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */