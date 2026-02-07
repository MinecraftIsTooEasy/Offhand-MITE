/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ScoreObjectiveCriteria
/*    */ {
/* 11 */   public static final Map field_96643_a = new HashMap<Object, Object>();
/*    */   
/* 13 */   public static final ScoreObjectiveCriteria field_96641_b = new ScoreDummyCriteria("dummy");
/* 14 */   public static final ScoreObjectiveCriteria deathCount = new ScoreDummyCriteria("deathCount");
/* 15 */   public static final ScoreObjectiveCriteria playerKillCount = new ScoreDummyCriteria("playerKillCount");
/* 16 */   public static final ScoreObjectiveCriteria totalKillCount = new ScoreDummyCriteria("totalKillCount");
/* 17 */   public static final ScoreObjectiveCriteria health = new ScoreHealthCriteria("health");
/*    */   
/*    */   String func_96636_a();
/*    */   
/*    */   int func_96635_a(List paramList);
/*    */   
/*    */   boolean isReadOnly();
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScoreObjectiveCriteria.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */