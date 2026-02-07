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
/*    */ 
/*    */ 
/*    */ 
/*    */ final class StatTypeTime
/*    */   implements IStatType
/*    */ {
/*    */   public String format(long par1) {
/* 20 */     long years = par1 / 630720000L;
/* 21 */     par1 %= 630720000L;
/*    */     
/* 23 */     long days = par1 / 1728000L;
/* 24 */     par1 %= 1728000L;
/*    */     
/* 26 */     long hours = par1 / 72000L;
/* 27 */     par1 %= 72000L;
/*    */     
/* 29 */     long minutes = par1 / 1200L;
/* 30 */     par1 %= 1200L;
/*    */     
/* 32 */     long seconds = par1 / 20L;
/*    */     
/* 34 */     StringBuffer sb = new StringBuffer();
/*    */     
/* 36 */     if (years > 0L) {
/* 37 */       sb.append(years + " y ");
/*    */     }
/* 39 */     if (days > 0L) {
/* 40 */       sb.append(days + " d ");
/*    */     }
/* 42 */     if (hours > 0L) {
/* 43 */       sb.append(hours + " h ");
/*    */     }
/* 45 */     if (minutes > 0L) {
/* 46 */       sb.append(minutes + " m ");
/*    */     }
/* 48 */     if (seconds > 0L) {
/* 49 */       sb.append(seconds + " s ");
/*    */     }
/* 51 */     return StringHelper.left(sb.toString(), sb.toString().length() - 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatTypeTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */