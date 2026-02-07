/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScoreObjective
/*    */ {
/*    */   private final Scoreboard theScoreboard;
/*    */   private final String name;
/*    */   private final ScoreObjectiveCriteria objectiveCriteria;
/*    */   private String displayName;
/*    */   
/*    */   public ScoreObjective(Scoreboard scoreboard, String string, ScoreObjectiveCriteria scoreObjectiveCriteria) {
/* 15 */     this.theScoreboard = scoreboard;
/* 16 */     this.name = string;
/* 17 */     this.objectiveCriteria = scoreObjectiveCriteria;
/*    */     
/* 19 */     this.displayName = string;
/*    */   }
/*    */   
/*    */   public Scoreboard getScoreboard() {
/* 23 */     return this.theScoreboard;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 27 */     return this.name;
/*    */   }
/*    */   
/*    */   public ScoreObjectiveCriteria getCriteria() {
/* 31 */     return this.objectiveCriteria;
/*    */   }
/*    */   
/*    */   public String getDisplayName() {
/* 35 */     return this.displayName;
/*    */   }
/*    */   
/*    */   public void setDisplayName(String string) {
/* 39 */     this.displayName = string;
/* 40 */     this.theScoreboard.func_96532_b(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScoreObjective.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */