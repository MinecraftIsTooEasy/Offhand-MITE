/*    */ package net.minecraft;
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
/*    */ public final class BitHelper
/*    */ {
/*    */   public static int clearBit(int data, int bit_value) {
/* 16 */     return data & (bit_value ^ 0xFFFFFFFF);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int flipBit(int data, int bit_value) {
/* 22 */     return data ^ bit_value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isBitSet(int data, int bit_value) {
/* 28 */     return ((data & bit_value) == bit_value);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isAnyBitSet(int data, int bit_values) {
/* 34 */     return ((data & bit_values) != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getBitValue(int bit_position) {
/* 40 */     return 1 << bit_position;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BitHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */