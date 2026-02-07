/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class ServerListenThread
/*     */   extends Thread
/*     */ {
/*  16 */   private final List pendingConnections = Collections.synchronizedList(new ArrayList());
/*  17 */   private final HashMap recentConnections = new HashMap<Object, Object>();
/*     */   
/*     */   private int connectionCounter;
/*     */   
/*     */   private final ServerSocket myServerSocket;
/*     */   private NetworkListenThread myNetworkListenThread;
/*     */   private final InetAddress myServerAddress;
/*     */   private final int myPort;
/*     */   
/*     */   public ServerListenThread(NetworkListenThread networkListenThread, InetAddress inetAddress, int i) {
/*  27 */     super("Listen thread");
/*  28 */     this.myNetworkListenThread = networkListenThread;
/*  29 */     this.myPort = i;
/*     */     
/*  31 */     this.myServerSocket = new ServerSocket(i, 0, inetAddress);
/*     */ 
/*     */     
/*  34 */     this.myServerAddress = (inetAddress == null) ? this.myServerSocket.getInetAddress() : inetAddress;
/*     */     
/*  36 */     this.myServerSocket.setPerformancePreferences(0, 2, 1);
/*     */   }
/*     */   
/*     */   public void processPendingConnections() {
/*  40 */     synchronized (this.pendingConnections) {
/*  41 */       for (byte b = 0; b < this.pendingConnections.size(); b++) {
/*  42 */         NetLoginHandler netLoginHandler = this.pendingConnections.get(b);
/*     */         try {
/*  44 */           netLoginHandler.tryLogin();
/*  45 */         } catch (Exception exception) {
/*  46 */           netLoginHandler.raiseErrorAndDisconnect("Internal server error");
/*  47 */           this.myNetworkListenThread.getServer().getLogAgent().logWarningException("Failed to handle packet for " + netLoginHandler.getUsernameAndAddress() + ": " + exception, exception);
/*     */         } 
/*  49 */         if (netLoginHandler.connectionComplete) {
/*  50 */           this.pendingConnections.remove(b--);
/*     */         }
/*  52 */         netLoginHandler.myTCPConnection.wakeThreads();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  59 */     while (this.myNetworkListenThread.isListening) {
/*     */       try {
/*  61 */         Socket socket = this.myServerSocket.accept();
/*     */         
/*  63 */         NetLoginHandler netLoginHandler = new NetLoginHandler(this.myNetworkListenThread.getServer(), socket, "Connection #" + this.connectionCounter++);
/*  64 */         addPendingConnection(netLoginHandler);
/*  65 */       } catch (IOException iOException) {
/*  66 */         iOException.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/*  70 */     this.myNetworkListenThread.getServer().getLogAgent().logInfo("Closing listening thread");
/*     */   }
/*     */   
/*     */   private void addPendingConnection(NetLoginHandler netLoginHandler) {
/*  74 */     if (netLoginHandler == null) {
/*  75 */       throw new IllegalArgumentException("Got null pendingconnection!");
/*     */     }
/*  77 */     synchronized (this.pendingConnections) {
/*  78 */       this.pendingConnections.add(netLoginHandler);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71769_a(InetAddress inetAddress) {
/*  87 */     if (inetAddress != null) {
/*  88 */       synchronized (this.recentConnections) {
/*  89 */         this.recentConnections.remove(inetAddress);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_71768_b() {
/*     */     try {
/*  96 */       this.myServerSocket.close();
/*  97 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMyPort() {
/* 106 */     return this.myPort;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerListenThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */