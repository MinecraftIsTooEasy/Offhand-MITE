/*    */ package net.minecraft;
/*    */ 
/*    */ public class ColorizerGrass {
/*  4 */   private static int[] grassBuffer = new int[65536];
/*    */   
/*    */   public static void setGrassBiomeColorizer(int[] is) {
/*  7 */     grassBuffer = is;
/*    */   }
/*    */   
/*    */   public static int getGrassColor(double d, double e) {
/* 11 */     e *= d;
/* 12 */     int i = (int)((1.0D - d) * 255.0D);
/* 13 */     int j = (int)((1.0D - e) * 255.0D);
/* 14 */     return grassBuffer[j << 8 | i];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ColorizerGrass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */