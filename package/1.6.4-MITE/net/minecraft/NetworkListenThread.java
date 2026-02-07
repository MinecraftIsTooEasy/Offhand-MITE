/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class NetworkListenThread
/*    */ {
/*    */   private final MinecraftServer mcServer;
/* 17 */   private final List connections = Collections.synchronizedList(new ArrayList());
/*    */   public volatile boolean isListening;
/*    */   
/*    */   public NetworkListenThread(MinecraftServer minecraftServer) {
/* 21 */     this.mcServer = minecraftServer;
/* 22 */     this.isListening = true;
/*    */   }
/*    */   
/*    */   public void addPlayer(NetServerHandler netServerHandler) {
/* 26 */     this.connections.add(netServerHandler);
/*    */   }
/*    */   
/*    */   public void stopListening() {
/* 30 */     this.isListening = false;
/*    */   }
/*    */   
/*    */   public void networkTick() {
/* 34 */     for (byte b = 0; b < this.connections.size(); b++) {
/* 35 */       NetServerHandler netServerHandler = this.connections.get(b);
/*    */       try {
/* 37 */         netServerHandler.networkTick();
/* 38 */       } catch (Exception exception) {
/* 39 */         if (netServerHandler.netManager instanceof MemoryConnection) {
/* 40 */           CrashReport crashReport = CrashReport.makeCrashReport(exception, "Ticking memory connection");
/* 41 */           CrashReportCategory crashReportCategory = crashReport.makeCategory("Ticking connection");
/*    */           
/* 43 */           crashReportCategory.addCrashSectionCallable("Connection", new CallableConnectionName(this, netServerHandler));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 50 */           throw new ReportedException(crashReport);
/*    */         } 
/* 52 */         this.mcServer.getLogAgent().logWarningException("Failed to handle packet for " + netServerHandler.playerEntity.getEntityName() + "/" + netServerHandler.playerEntity.getPlayerIP() + ": " + exception, exception);
/* 53 */         netServerHandler.kickPlayerFromServer("Internal server error");
/*    */       } 
/*    */       
/* 56 */       if (netServerHandler.connectionClosed) {
/* 57 */         this.connections.remove(b--);
/*    */       }
/* 59 */       netServerHandler.netManager.wakeThreads();
/*    */     } 
/*    */   }
/*    */   
/*    */   public MinecraftServer getServer() {
/* 64 */     return this.mcServer;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NetworkListenThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */