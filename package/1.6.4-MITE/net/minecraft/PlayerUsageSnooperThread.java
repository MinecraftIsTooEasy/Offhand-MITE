/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.TimerTask;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class PlayerUsageSnooperThread
/*    */   extends TimerTask
/*    */ {
/*    */   PlayerUsageSnooperThread(PlayerUsageSnooper playerUsageSnooper) {}
/*    */   
/*    */   public void run() {
/*    */     HashMap<Object, Object> hashMap;
/* 48 */     if (!PlayerUsageSnooper.getStatsCollectorFor(this.snooper).isSnooperEnabled()) {
/*    */       return;
/*    */     }
/* 51 */     synchronized (PlayerUsageSnooper.getSyncLockFor(this.snooper)) {
/* 52 */       hashMap = new HashMap<Object, Object>(PlayerUsageSnooper.getDataMapFor(this.snooper));
/* 53 */       hashMap.put("snooper_count", Integer.valueOf(PlayerUsageSnooper.getSelfCounterFor(this.snooper)));
/*    */     } 
/*    */     
/* 56 */     HttpUtil.sendPost(PlayerUsageSnooper.getStatsCollectorFor(this.snooper).getLogAgent(), PlayerUsageSnooper.getServerUrlFor(this.snooper), hashMap, true);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerUsageSnooperThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */