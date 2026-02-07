/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ThreadDedicatedServerMITE
/*    */   extends Thread
/*    */ {
/*    */   final DedicatedServer connectedDedicatedServer;
/*    */   
/*    */   public ThreadDedicatedServerMITE(DedicatedServer par1DedicatedServer) {
/* 11 */     this.connectedDedicatedServer = par1DedicatedServer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 16 */     this.connectedDedicatedServer.stopServer();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadDedicatedServerMITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */