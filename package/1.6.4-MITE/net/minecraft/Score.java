/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class Score
/*    */ {
/*  9 */   public static final Comparator field_96658_a = new ScoreComparator();
/*    */ 
/*    */ 
/*    */   
/*    */   private final Scoreboard theScoreboard;
/*    */ 
/*    */ 
/*    */   
/*    */   private final ScoreObjective theScoreObjective;
/*    */ 
/*    */   
/*    */   private final String field_96654_d;
/*    */ 
/*    */   
/*    */   private int field_96655_e;
/*    */ 
/*    */ 
/*    */   
/*    */   public Score(Scoreboard scoreboard, ScoreObjective scoreObjective, String string) {
/* 28 */     this.theScoreboard = scoreboard;
/* 29 */     this.theScoreObjective = scoreObjective;
/* 30 */     this.field_96654_d = string;
/*    */   }
/*    */   
/*    */   public void func_96649_a(int i) {
/* 34 */     if (this.theScoreObjective.getCriteria().isReadOnly()) throw new IllegalStateException("Cannot modify read-only score"); 
/* 35 */     func_96647_c(getScorePoints() + i);
/*    */   }
/*    */   
/*    */   public void func_96646_b(int i) {
/* 39 */     if (this.theScoreObjective.getCriteria().isReadOnly()) throw new IllegalStateException("Cannot modify read-only score"); 
/* 40 */     func_96647_c(getScorePoints() - i);
/*    */   }
/*    */   
/*    */   public void func_96648_a() {
/* 44 */     if (this.theScoreObjective.getCriteria().isReadOnly()) throw new IllegalStateException("Cannot modify read-only score"); 
/* 45 */     func_96649_a(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getScorePoints() {
/* 54 */     return this.field_96655_e;
/*    */   }
/*    */   
/*    */   public void func_96647_c(int i) {
/* 58 */     int j = this.field_96655_e;
/* 59 */     this.field_96655_e = i;
/* 60 */     if (j != i) func_96650_f().func_96536_a(this); 
/*    */   }
/*    */   
/*    */   public ScoreObjective func_96645_d() {
/* 64 */     return this.theScoreObjective;
/*    */   }
/*    */   
/*    */   public String getPlayerName() {
/* 68 */     return this.field_96654_d;
/*    */   }
/*    */   
/*    */   public Scoreboard func_96650_f() {
/* 72 */     return this.theScoreboard;
/*    */   }
/*    */   
/*    */   public void func_96651_a(List list) {
/* 76 */     func_96647_c(this.theScoreObjective.getCriteria().func_96635_a(list));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Score.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */