/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class ScoreComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int func_96659_a(Score score, Score score2) {
/* 12 */     if (score.getScorePoints() > score2.getScorePoints())
/* 13 */       return 1; 
/* 14 */     if (score.getScorePoints() < score2.getScorePoints()) {
/* 15 */       return -1;
/*    */     }
/* 17 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScoreComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */