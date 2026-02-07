/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class IntCache {
/*  7 */   private static int intCacheSize = 256;
/*    */   
/*  9 */   private static List freeSmallArrays = new ArrayList();
/* 10 */   private static List inUseSmallArrays = new ArrayList();
/*    */   
/* 12 */   private static List freeLargeArrays = new ArrayList();
/* 13 */   private static List inUseLargeArrays = new ArrayList();
/*    */   
/*    */   public static synchronized int[] getIntCache(int i) {
/* 16 */     if (i <= 256) {
/* 17 */       if (freeSmallArrays.isEmpty()) {
/* 18 */         int[] arrayOfInt2 = new int[256];
/* 19 */         inUseSmallArrays.add(arrayOfInt2);
/* 20 */         return arrayOfInt2;
/*    */       } 
/* 22 */       int[] arrayOfInt1 = freeSmallArrays.remove(freeSmallArrays.size() - 1);
/* 23 */       inUseSmallArrays.add(arrayOfInt1);
/* 24 */       return arrayOfInt1;
/*    */     } 
/*    */ 
/*    */     
/* 28 */     if (i > intCacheSize) {
/* 29 */       intCacheSize = i;
/*    */       
/* 31 */       freeLargeArrays.clear();
/* 32 */       inUseLargeArrays.clear();
/*    */       
/* 34 */       int[] arrayOfInt1 = new int[intCacheSize];
/* 35 */       inUseLargeArrays.add(arrayOfInt1);
/* 36 */       return arrayOfInt1;
/*    */     } 
/* 38 */     if (freeLargeArrays.isEmpty()) {
/* 39 */       int[] arrayOfInt1 = new int[intCacheSize];
/* 40 */       inUseLargeArrays.add(arrayOfInt1);
/* 41 */       return arrayOfInt1;
/*    */     } 
/* 43 */     int[] arrayOfInt = freeLargeArrays.remove(freeLargeArrays.size() - 1);
/* 44 */     inUseLargeArrays.add(arrayOfInt);
/* 45 */     return arrayOfInt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void resetIntCache() {
/* 51 */     if (!freeLargeArrays.isEmpty()) freeLargeArrays.remove(freeLargeArrays.size() - 1); 
/* 52 */     if (!freeSmallArrays.isEmpty()) freeSmallArrays.remove(freeSmallArrays.size() - 1);
/*    */     
/* 54 */     freeLargeArrays.addAll(inUseLargeArrays);
/* 55 */     freeSmallArrays.addAll(inUseSmallArrays);
/*    */     
/* 57 */     inUseLargeArrays.clear();
/* 58 */     inUseSmallArrays.clear();
/*    */   }
/*    */   
/*    */   public static synchronized String func_85144_b() {
/* 62 */     return "cache: " + freeLargeArrays.size() + ", tcache: " + freeSmallArrays.size() + ", allocated: " + inUseLargeArrays.size() + ", tallocated: " + inUseSmallArrays.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IntCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */