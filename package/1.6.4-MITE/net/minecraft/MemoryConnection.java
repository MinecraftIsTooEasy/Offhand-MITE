/*     */ package net.minecraft;
/*     */ 
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.main.Main;
/*     */ 
/*     */ 
/*     */ public final class MemoryConnection
/*     */   implements INetworkManager
/*     */ {
/*  14 */   private static final SocketAddress mySocketAddress = new InetSocketAddress("127.0.0.1", 0);
/*  15 */   private final List readPacketCache = Collections.synchronizedList(new ArrayList());
/*     */   
/*     */   private final ILogAgent field_98214_c;
/*     */   
/*     */   private MemoryConnection pairedConnection;
/*     */   private NetHandler myNetHandler;
/*     */   private boolean shuttingDown;
/*  22 */   private String shutdownReason = "";
/*     */   
/*     */   private Object[] field_74439_g;
/*     */   private boolean gamePaused;
/*     */   
/*     */   public MemoryConnection(ILogAgent par1ILogAgent, NetHandler par2NetHandler) {
/*  28 */     this.myNetHandler = par2NetHandler;
/*  29 */     this.field_98214_c = par1ILogAgent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNetHandler(NetHandler par1NetHandler) {
/*  37 */     this.myNetHandler = par1NetHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToSendQueue(Packet par1Packet) {
/*  45 */     if (!this.shuttingDown)
/*     */     {
/*  47 */       this.pairedConnection.processOrCachePacket(par1Packet);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeConnections() {
/*  53 */     this.pairedConnection = null;
/*  54 */     this.myNetHandler = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConnectionActive() {
/*  59 */     return (!this.shuttingDown && this.pairedConnection != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void wakeThreads() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processReadPackets() {
/*  72 */     boolean is_MITE_DS_client_player = (Main.is_MITE_DS && this.myNetHandler instanceof NetClientHandler);
/*     */     
/*  74 */     int var1 = 2500;
/*     */     
/*  76 */     while (var1-- >= 0 && !this.readPacketCache.isEmpty()) {
/*     */       
/*  78 */       Packet var2 = this.readPacketCache.remove(0);
/*     */       
/*  80 */       if (is_MITE_DS_client_player && !Main.isPacketThatMITEDSClientPlayerCanSendOrReceive(var2)) {
/*     */         continue;
/*     */       }
/*  83 */       long before = System.currentTimeMillis();
/*     */       
/*  85 */       var2.processPacket(this.myNetHandler);
/*     */       
/*  87 */       long delay = System.currentTimeMillis() - before;
/*     */       
/*  89 */       if (delay > 4L)
/*     */       {
/*  91 */         Minecraft.MITE_log.logInfo(((this.myNetHandler instanceof NetClientHandler) ? "[Client]" : "[Server]") + " Long time processing packet (delay=" + delay + "ms, packet id=" + var2.getPacketId() + ")");
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (this.readPacketCache.size() > var1)
/*     */     {
/* 103 */       this.field_98214_c.logWarning("Memory connection overburdened; after processing 2500 packets, we still have " + this.readPacketCache.size() + " to go!");
/*     */     }
/*     */     
/* 106 */     if (this.shuttingDown && this.readPacketCache.isEmpty())
/*     */     {
/* 108 */       this.myNetHandler.handleErrorMessage(this.shutdownReason, this.field_74439_g);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocketAddress getSocketAddress() {
/* 117 */     return mySocketAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void serverShutdown() {
/* 125 */     this.shuttingDown = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void networkShutdown(String par1Str, Object... par2ArrayOfObj) {
/* 134 */     this.shuttingDown = true;
/* 135 */     this.shutdownReason = par1Str;
/* 136 */     this.field_74439_g = par2ArrayOfObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int packetSize() {
/* 144 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pairWith(MemoryConnection par1MemoryConnection) {
/* 149 */     this.pairedConnection = par1MemoryConnection;
/* 150 */     par1MemoryConnection.pairedConnection = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGamePaused() {
/* 155 */     return this.gamePaused;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGamePaused(boolean par1) {
/* 160 */     this.gamePaused = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public MemoryConnection getPairedConnection() {
/* 165 */     return this.pairedConnection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processOrCachePacket(Packet par1Packet) {
/* 173 */     if (Main.isPacketIgnored(this.myNetHandler, par1Packet)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 190 */     if (par1Packet.canProcessAsync() && this.myNetHandler.canProcessPacketsAsync()) {
/*     */       
/* 192 */       par1Packet.processPacket(this.myNetHandler);
/*     */     }
/*     */     else {
/*     */       
/* 196 */       this.readPacketCache.add(par1Packet);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int clearReceivedPackets() {
/* 202 */     int num_packets = this.readPacketCache.size();
/*     */     
/* 204 */     this.readPacketCache.clear();
/*     */     
/* 206 */     return num_packets;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MemoryConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */