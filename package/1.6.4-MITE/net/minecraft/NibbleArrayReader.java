/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NibbleArrayReader
/*    */ {
/*    */   public final byte[] data;
/*    */   private final int depthBits;
/*    */   private final int depthBitsPlusFour;
/*    */   
/*    */   public NibbleArrayReader(byte[] bs, int i) {
/* 15 */     this.data = bs;
/* 16 */     this.depthBits = i;
/* 17 */     this.depthBitsPlusFour = i + 4;
/*    */   }
/*    */   
/*    */   public int get(int i, int j, int k) {
/* 21 */     int m = i << this.depthBitsPlusFour | k << this.depthBits | j;
/* 22 */     int n = m >> 1;
/* 23 */     int i1 = m & 0x1;
/*    */     
/* 25 */     if (i1 == 0) {
/* 26 */       return this.data[n] & 0xF;
/*    */     }
/* 28 */     return this.data[n] >> 4 & 0xF;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NibbleArrayReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */