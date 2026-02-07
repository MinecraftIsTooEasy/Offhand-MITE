/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class DecompressionResult
/*    */ {
/*    */   private final byte[] output;
/*    */   private final int size;
/*    */   private final boolean decompression_occured_and_matched_expected_size;
/*    */   
/*    */   public DecompressionResult(byte[] output, int size, boolean decompression_occured_and_matched_expected_size) {
/* 13 */     this.output = output;
/* 14 */     this.size = size;
/* 15 */     this.decompression_occured_and_matched_expected_size = decompression_occured_and_matched_expected_size;
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
/*    */   public boolean decompressionOccurredAndMatchedExpectedSize() {
/* 30 */     return this.decompression_occured_and_matched_expected_size;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DecompressionResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */