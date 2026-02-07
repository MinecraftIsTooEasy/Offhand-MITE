/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class StatTypeFloat
/*    */   implements IStatType
/*    */ {
/*    */   public String format(long par1) {
/* 15 */     return StatBase.getDecimalFormat().format(par1 * 0.1D);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatTypeFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */