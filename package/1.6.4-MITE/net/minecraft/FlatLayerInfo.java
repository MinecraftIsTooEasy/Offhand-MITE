/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlatLayerInfo
/*    */ {
/*    */   private int layerCount;
/*    */   private int layerFillBlock;
/*    */   private int layerFillBlockMeta;
/*    */   private int layerMinimumY;
/*    */   
/*    */   public FlatLayerInfo(int par1, int par2) {
/* 17 */     this.layerCount = 1;
/* 18 */     this.layerCount = par1;
/* 19 */     this.layerFillBlock = par2;
/*    */   }
/*    */ 
/*    */   
/*    */   public FlatLayerInfo(int par1, int par2, int par3) {
/* 24 */     this(par1, par2);
/* 25 */     this.layerFillBlockMeta = par3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLayerCount() {
/* 33 */     return this.layerCount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFillBlock() {
/* 41 */     return this.layerFillBlock;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFillBlockMeta() {
/* 49 */     return this.layerFillBlockMeta;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMinY() {
/* 57 */     return this.layerMinimumY;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMinY(int par1) {
/* 65 */     this.layerMinimumY = par1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String var1 = Integer.toString(this.layerFillBlock);
/*    */     
/* 72 */     if (this.layerCount > 1)
/*    */     {
/* 74 */       var1 = this.layerCount + "x" + var1;
/*    */     }
/*    */     
/* 77 */     if (this.layerFillBlockMeta > 0)
/*    */     {
/* 79 */       var1 = var1 + ":" + this.layerFillBlockMeta;
/*    */     }
/*    */     
/* 82 */     return var1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getInt(String field) {
/*    */     try {
/* 89 */       return getClass().getDeclaredField(field).getInt(this);
/*    */     }
/* 91 */     catch (Exception e) {
/*    */       
/* 93 */       return 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FlatLayerInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */