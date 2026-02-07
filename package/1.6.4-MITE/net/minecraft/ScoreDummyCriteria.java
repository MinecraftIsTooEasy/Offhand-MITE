/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class ScoreDummyCriteria
/*    */   implements ScoreObjectiveCriteria
/*    */ {
/*    */   private final String field_96644_g;
/*    */   
/*    */   public ScoreDummyCriteria(String string) {
/* 11 */     this.field_96644_g = string;
/* 12 */     ScoreObjectiveCriteria.field_96643_a.put(string, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_96636_a() {
/* 17 */     return this.field_96644_g;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_96635_a(List list) {
/* 22 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReadOnly() {
/* 27 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScoreDummyCriteria.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */