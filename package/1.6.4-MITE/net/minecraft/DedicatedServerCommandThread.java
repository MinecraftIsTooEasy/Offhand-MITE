/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ class DedicatedServerCommandThread
/*    */   extends Thread
/*    */ {
/*    */   final DedicatedServer server;
/*    */   
/*    */   DedicatedServerCommandThread(DedicatedServer par1DedicatedServer) {
/* 13 */     this.server = par1DedicatedServer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 18 */     BufferedReader var1 = new BufferedReader(new InputStreamReader(System.in));
/*    */     
/*    */     try {
/*    */       String var2;
/*    */       
/* 23 */       while (!this.server.isServerStopped() && this.server.isServerRunning() && (var2 = var1.readLine()) != null)
/*    */       {
/*    */         
/* 26 */         this.server.addPendingCommand(var2, (ICommandSender)this.server, false);
/*    */       }
/*    */     }
/* 29 */     catch (IOException var4) {
/*    */       
/* 31 */       var4.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DedicatedServerCommandThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */