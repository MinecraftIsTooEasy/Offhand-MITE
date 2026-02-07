/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ 
/*    */ 
/*    */ public class RConUtils
/*    */ {
/*  8 */   public static char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getBytesAsString(byte[] bs, int i, int j) {
/* 17 */     int k = j - 1;
/* 18 */     int m = (i > k) ? k : i;
/* 19 */     while (0 != bs[m] && m < k) {
/* 20 */       m++;
/*    */     }
/*    */     
/*    */     try {
/* 24 */       return new String(bs, i, m - i, "UTF-8");
/* 25 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 26 */       unsupportedEncodingException.printStackTrace();
/* 27 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int getRemainingBytesAsLEInt(byte[] bs, int i) {
/* 32 */     return getBytesAsLEInt(bs, i, bs.length);
/*    */   }
/*    */   
/*    */   public static int getBytesAsLEInt(byte[] bs, int i, int j) {
/* 36 */     if (0 > j - i - 4)
/*    */     {
/*    */       
/* 39 */       return 0;
/*    */     }
/* 41 */     return bs[i + 3] << 24 | (bs[i + 2] & 0xFF) << 16 | (bs[i + 1] & 0xFF) << 8 | bs[i] & 0xFF;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getBytesAsBEint(byte[] bs, int i, int j) {
/* 49 */     if (0 > j - i - 4)
/*    */     {
/*    */       
/* 52 */       return 0;
/*    */     }
/* 54 */     return bs[i] << 24 | (bs[i + 1] & 0xFF) << 16 | (bs[i + 2] & 0xFF) << 8 | bs[i + 3] & 0xFF;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getByteAsHexString(byte b) {
/* 68 */     return "" + hexDigits[(b & 0xF0) >>> 4] + hexDigits[b & 0xF];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RConUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */