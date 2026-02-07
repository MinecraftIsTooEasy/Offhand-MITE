/*    */ package net.minecraft;
/*    */ 
/*    */ public class PotionHealth
/*    */   extends Potion {
/*    */   public PotionHealth(int i, boolean bl, int j) {
/*  6 */     super(i, bl, j);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInstant() {
/* 11 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReady(int i, int j) {
/* 16 */     return (i >= 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PotionHealth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */