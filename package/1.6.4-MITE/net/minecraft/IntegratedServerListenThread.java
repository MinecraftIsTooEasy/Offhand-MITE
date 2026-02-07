/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntegratedServerListenThread
/*    */   extends NetworkListenThread
/*    */ {
/*    */   private final MemoryConnection netMemoryConnection;
/*    */   private MemoryConnection theMemoryConnection;
/*    */   private String field_71759_e;
/*    */   private boolean field_71756_f;
/*    */   private ServerListenThread myServerListenThread;
/*    */   
/*    */   public IntegratedServerListenThread(IntegratedServer integratedServer) {
/* 22 */     super(integratedServer);
/* 23 */     this.netMemoryConnection = new MemoryConnection(integratedServer.getLogAgent(), null);
/*    */   }
/*    */   
/*    */   public void func_71754_a(MemoryConnection memoryConnection, String string) {
/* 27 */     this.theMemoryConnection = memoryConnection;
/* 28 */     this.field_71759_e = string;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71755_c() {
/* 33 */     if (this.myServerListenThread == null) {
/* 34 */       int i = -1;
/*    */       try {
/* 36 */         i = HttpUtil.func_76181_a();
/* 37 */       } catch (IOException iOException) {}
/*    */       
/* 39 */       if (i <= 0) {
/* 40 */         i = 25564;
/*    */       }
/*    */       
/*    */       try {
/* 44 */         this.myServerListenThread = new ServerListenThread(this, null, i);
/* 45 */         this.myServerListenThread.start();
/* 46 */       } catch (IOException iOException) {
/* 47 */         throw iOException;
/*    */       } 
/*    */     } 
/* 50 */     return String.valueOf(this.myServerListenThread.getMyPort());
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopListening() {
/* 55 */     super.stopListening();
/* 56 */     if (this.myServerListenThread != null) {
/* 57 */       getIntegratedServer().getLogAgent().logInfo("Stopping server connection");
/* 58 */       this.myServerListenThread.func_71768_b();
/* 59 */       this.myServerListenThread.interrupt();
/* 60 */       this.myServerListenThread = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void networkTick() {
/* 66 */     if (this.theMemoryConnection != null) {
/* 67 */       ServerPlayer serverPlayer = getIntegratedServer().getConfigurationManager().createPlayerForUser(this.field_71759_e);
/*    */       
/* 69 */       if (serverPlayer != null) {
/* 70 */         this.netMemoryConnection.pairWith(this.theMemoryConnection);
/* 71 */         this.field_71756_f = true;
/*    */         
/* 73 */         getIntegratedServer().getConfigurationManager().initializeConnectionToPlayer(this.netMemoryConnection, serverPlayer);
/*    */       } 
/*    */       
/* 76 */       this.theMemoryConnection = null;
/* 77 */       this.field_71759_e = null;
/*    */     } 
/*    */     
/* 80 */     if (this.myServerListenThread != null) {
/* 81 */       this.myServerListenThread.processPendingConnections();
/*    */     }
/*    */     
/* 84 */     super.networkTick();
/*    */   }
/*    */ 
/*    */   
/*    */   public IntegratedServer getIntegratedServer() {
/* 89 */     return (IntegratedServer)super.getServer();
/*    */   }
/*    */   
/*    */   public boolean isGamePaused() {
/* 93 */     return (this.field_71756_f && this.netMemoryConnection.getPairedConnection().isConnectionActive() && this.netMemoryConnection.getPairedConnection().isGamePaused());
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IntegratedServerListenThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */