/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CompressionResult
/*    */ {
/*    */   private final byte[] output;
/*    */   private final int size;
/*    */   private final boolean compression_occured;
/*    */   
/*    */   public CompressionResult(byte[] output, int size, boolean compression_occured) {
/* 13 */     this.output = output;
/* 14 */     this.size = size;
/* 15 */     this.compression_occured = compression_occured;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getOutput() {
/* 20 */     return this.output;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getOutputSize() {
/* 25 */     return this.size;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compressionOccurred() {
/* 30 */     return this.compression_occured;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CompressionResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */