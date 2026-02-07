/*    */ package net.minecraft;
/*    */ 
/*    */ public class ColorizerFoliage {
/*  4 */   private static int[] foliageBuffer = new int[65536];
/*    */   
/*    */   public static void setFoliageBiomeColorizer(int[] is) {
/*  7 */     foliageBuffer = is;
/*    */   }
/*    */   
/*    */   public static int getFoliageColor(double d, double e) {
/* 11 */     e *= d;
/* 12 */     int i = (int)((1.0D - d) * 255.0D);
/* 13 */     int j = (int)((1.0D - e) * 255.0D);
/* 14 */     return foliageBuffer[j << 8 | i];
/*    */   }
/*    */   
/*    */   public static int getFoliageColorPine() {
/* 18 */     return 6396257;
/*    */   }
/*    */   
/*    */   public static int getFoliageColorBirch() {
/* 22 */     return 8431445;
/*    */   }
/*    */   
/*    */   public static int getFoliageColorBasic() {
/* 26 */     return 4764952;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ColorizerFoliage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */