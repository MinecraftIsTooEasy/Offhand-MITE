/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.ServerSocket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class RConThreadBase implements Runnable {
/*     */   protected boolean running;
/*     */   protected IServer server;
/*     */   protected Thread rconThread;
/*  13 */   protected int field_72615_d = 5;
/*  14 */   protected List socketList = new ArrayList();
/*  15 */   protected List serverSocketList = new ArrayList();
/*     */   
/*     */   RConThreadBase(IServer iServer) {
/*  18 */     this.server = iServer;
/*  19 */     if (this.server.isDebuggingEnabled()) {
/*  20 */       logWarning("Debugging is enabled, performance maybe reduced!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void startThread() {
/*  28 */     this.rconThread = new Thread(this);
/*  29 */     this.rconThread.start();
/*  30 */     this.running = true;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRunning() {
/*  69 */     return this.running;
/*     */   }
/*     */   
/*     */   protected void logDebug(String string) {
/*  73 */     this.server.logDebug(string);
/*     */   }
/*     */   
/*     */   protected void logInfo(String string) {
/*  77 */     this.server.logInfo(string);
/*     */   }
/*     */   
/*     */   protected void logWarning(String string) {
/*  81 */     this.server.logWarning(string);
/*     */   }
/*     */   
/*     */   protected void logSevere(String string) {
/*  85 */     this.server.logSevere(string);
/*     */   }
/*     */   
/*     */   protected int getNumberOfPlayers() {
/*  89 */     return this.server.getCurrentPlayerCount();
/*     */   }
/*     */   
/*     */   protected void registerSocket(DatagramSocket datagramSocket) {
/*  93 */     logDebug("registerSocket: " + datagramSocket);
/*  94 */     this.socketList.add(datagramSocket);
/*     */   }
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
/*     */   protected boolean closeSocket(DatagramSocket datagramSocket, boolean bl) {
/* 107 */     logDebug("closeSocket: " + datagramSocket);
/* 108 */     if (null == datagramSocket) {
/* 109 */       return false;
/*     */     }
/*     */     
/* 112 */     boolean bool = false;
/* 113 */     if (!datagramSocket.isClosed()) {
/* 114 */       datagramSocket.close();
/* 115 */       bool = true;
/*     */     } 
/*     */     
/* 118 */     if (bl) {
/* 119 */       this.socketList.remove(datagramSocket);
/*     */     }
/*     */     
/* 122 */     return bool;
/*     */   }
/*     */   
/*     */   protected boolean closeServerSocket(ServerSocket serverSocket) {
/* 126 */     return closeServerSocket_do(serverSocket, true);
/*     */   }
/*     */   
/*     */   protected boolean closeServerSocket_do(ServerSocket serverSocket, boolean bl) {
/* 130 */     logDebug("closeSocket: " + serverSocket);
/* 131 */     if (null == serverSocket) {
/* 132 */       return false;
/*     */     }
/*     */     
/* 135 */     boolean bool = false;
/*     */     try {
/* 137 */       if (!serverSocket.isClosed()) {
/* 138 */         serverSocket.close();
/* 139 */         bool = true;
/*     */       } 
/* 141 */     } catch (IOException iOException) {
/* 142 */       logWarning("IO: " + iOException.getMessage());
/*     */     } 
/*     */     
/* 145 */     if (bl) {
/* 146 */       this.serverSocketList.remove(serverSocket);
/*     */     }
/*     */     
/* 149 */     return bool;
/*     */   }
/*     */   
/*     */   protected void closeAllSockets() {
/* 153 */     closeAllSockets_do(false);
/*     */   }
/*     */   
/*     */   protected void closeAllSockets_do(boolean bl) {
/* 157 */     byte b = 0;
/* 158 */     for (DatagramSocket datagramSocket : this.socketList) {
/* 159 */       if (closeSocket(datagramSocket, false)) {
/* 160 */         b++;
/*     */       }
/*     */     } 
/* 163 */     this.socketList.clear();
/*     */     
/* 165 */     for (ServerSocket serverSocket : this.serverSocketList) {
/* 166 */       if (closeServerSocket_do(serverSocket, false)) {
/* 167 */         b++;
/*     */       }
/*     */     } 
/* 170 */     this.serverSocketList.clear();
/*     */     
/* 172 */     if (bl && 0 < b)
/* 173 */       logWarning("Force closed " + b + " sockets"); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RConThreadBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */