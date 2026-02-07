/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class RConThreadMain
/*     */   extends RConThreadBase
/*     */ {
/*     */   private int rconPort;
/*     */   private int serverPort;
/*     */   private String hostname;
/*     */   private ServerSocket serverSocket;
/*     */   private String rconPassword;
/*     */   private Map clientThreads;
/*     */   
/*     */   public RConThreadMain(IServer iServer) {
/*  24 */     super(iServer);
/*  25 */     this.rconPort = iServer.getIntProperty("rcon.port", 0);
/*  26 */     this.rconPassword = iServer.getStringProperty("rcon.password", "");
/*  27 */     this.hostname = iServer.getHostname();
/*  28 */     this.serverPort = iServer.getPort();
/*  29 */     if (0 == this.rconPort) {
/*     */       
/*  31 */       this.rconPort = this.serverPort + 10;
/*  32 */       logInfo("Setting default rcon port to " + this.rconPort);
/*  33 */       iServer.setProperty("rcon.port", Integer.valueOf(this.rconPort));
/*  34 */       if (0 == this.rconPassword.length()) {
/*  35 */         iServer.setProperty("rcon.password", "");
/*     */       }
/*  37 */       iServer.saveProperties();
/*     */     } 
/*     */     
/*  40 */     if (0 == this.hostname.length()) {
/*  41 */       this.hostname = "0.0.0.0";
/*     */     }
/*     */     
/*  44 */     initClientThreadList();
/*  45 */     this.serverSocket = null;
/*     */   }
/*     */   
/*     */   private void initClientThreadList() {
/*  49 */     this.clientThreads = new HashMap<Object, Object>();
/*     */   }
/*     */   
/*     */   private void cleanClientThreadsMap() {
/*  53 */     Iterator<Map.Entry> iterator = this.clientThreads.entrySet().iterator();
/*  54 */     while (iterator.hasNext()) {
/*  55 */       Map.Entry entry = iterator.next();
/*  56 */       if (!((RConThreadClient)entry.getValue()).isRunning()) {
/*  57 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  64 */     logInfo("RCON running on " + this.hostname + ":" + this.rconPort);
/*     */     try {
/*  66 */       while (this.running) {
/*     */         
/*     */         try {
/*  69 */           Socket socket = this.serverSocket.accept();
/*  70 */           socket.setSoTimeout(500);
/*  71 */           RConThreadClient rConThreadClient = new RConThreadClient(this.server, socket);
/*  72 */           rConThreadClient.startThread();
/*  73 */           this.clientThreads.put(socket.getRemoteSocketAddress(), rConThreadClient);
/*     */ 
/*     */           
/*  76 */           cleanClientThreadsMap();
/*  77 */         } catch (SocketTimeoutException socketTimeoutException) {
/*     */           
/*  79 */           cleanClientThreadsMap();
/*  80 */         } catch (IOException iOException) {
/*  81 */           if (this.running) {
/*  82 */             logInfo("IO: " + iOException.getMessage());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } finally {
/*  87 */       closeServerSocket(this.serverSocket);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startThread() {
/*  93 */     if (0 == this.rconPassword.length()) {
/*  94 */       logWarning("No rcon password set in '" + this.server.getSettingsFilename() + "', rcon disabled!");
/*     */       
/*     */       return;
/*     */     } 
/*  98 */     if (0 >= this.rconPort || 65535 < this.rconPort) {
/*  99 */       logWarning("Invalid rcon port " + this.rconPort + " found in '" + this.server.getSettingsFilename() + "', rcon disabled!");
/*     */       
/*     */       return;
/*     */     } 
/* 103 */     if (this.running) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/* 108 */       this.serverSocket = new ServerSocket(this.rconPort, 0, InetAddress.getByName(this.hostname));
/* 109 */       this.serverSocket.setSoTimeout(500);
/* 110 */       super.startThread();
/* 111 */     } catch (IOException iOException) {
/* 112 */       logWarning("Unable to initialise rcon on " + this.hostname + ":" + this.rconPort + " : " + iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RConThreadMain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */