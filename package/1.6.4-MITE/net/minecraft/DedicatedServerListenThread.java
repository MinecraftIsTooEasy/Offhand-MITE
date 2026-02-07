/*    */ package net.minecraft;
/*    */ 
/*    */ import java.net.InetAddress;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DedicatedServerListenThread
/*    */   extends NetworkListenThread
/*    */ {
/*    */   private final ServerListenThread theServerListenThread;
/*    */   
/*    */   public DedicatedServerListenThread(MinecraftServer minecraftServer, InetAddress inetAddress, int i) {
/* 14 */     super(minecraftServer);
/*    */     
/* 16 */     this.theServerListenThread = new ServerListenThread(this, inetAddress, i);
/* 17 */     this.theServerListenThread.start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopListening() {
/* 22 */     super.stopListening();
/* 23 */     this.theServerListenThread.func_71768_b();
/* 24 */     this.theServerListenThread.interrupt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void networkTick() {
/* 29 */     this.theServerListenThread.processPendingConnections();
/* 30 */     super.networkTick();
/*    */   }
/*    */ 
/*    */   
/*    */   public DedicatedServer getDedicatedServer() {
/* 35 */     return (DedicatedServer)super.getServer();
/*    */   }
/*    */   
/*    */   public void func_71761_a(InetAddress inetAddress) {
/* 39 */     this.theServerListenThread.func_71769_a(inetAddress);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DedicatedServerListenThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */