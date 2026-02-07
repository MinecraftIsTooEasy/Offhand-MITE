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
/*    */ 
/*    */ 
/*    */ final class StatTypeDistance
/*    */   implements IStatType
/*    */ {
/*    */   public String format(long par1) {
/* 17 */     double var2 = par1 / 100.0D;
/* 18 */     double var4 = var2 / 1000.0D;
/* 19 */     return (var4 > 0.5D) ? (StatBase.getDecimalFormat().format(var4) + " km") : ((var2 > 0.5D) ? (StatBase.getDecimalFormat().format(var2) + " m") : (par1 + " cm"));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatTypeDistance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */