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
/*    */ class DedicatedServerSleepThread
/*    */   extends Thread
/*    */ {
/*    */   DedicatedServerSleepThread(DedicatedServer dedicatedServer) {
/* 56 */     setDaemon(true);
/* 57 */     start();
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     while (true) {
/*    */       try {
/*    */         while (true)
/* 64 */           Thread.sleep(2147483647L);  break;
/* 65 */       } catch (InterruptedException interruptedException) {}
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DedicatedServerSleepThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */