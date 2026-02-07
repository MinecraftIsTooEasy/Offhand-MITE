/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class ScoreHealthCriteria
/*    */   extends ScoreDummyCriteria
/*    */ {
/*    */   public ScoreHealthCriteria(String string) {
/* 10 */     super(string);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_96635_a(List list) {
/* 15 */     float f = 0.0F;
/*    */     
/* 17 */     for (EntityPlayer entityPlayer : list) {
/* 18 */       f += entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount();
/*    */     }
/*    */     
/* 21 */     if (list.size() > 0) f /= list.size();
/*    */     
/* 23 */     return MathHelper.ceiling_float_int(f);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReadOnly() {
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScoreHealthCriteria.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */