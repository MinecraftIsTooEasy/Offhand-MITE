/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BossStatus
/*    */ {
/*    */   public static float healthScale;
/*    */   public static int statusBarLength;
/*    */   public static String bossName;
/*    */   public static boolean field_82825_d;
/*    */   
/*    */   public static void setBossStatus(IBossDisplayData iBossDisplayData, boolean bl) {
/* 14 */     healthScale = iBossDisplayData.getHealth() / iBossDisplayData.getMaxHealth();
/* 15 */     statusBarLength = 100;
/* 16 */     bossName = iBossDisplayData.getEntityName();
/* 17 */     field_82825_d = bl;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BossStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */