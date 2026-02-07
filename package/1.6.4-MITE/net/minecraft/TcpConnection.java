/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketAddress;
/*     */ import java.net.SocketException;
/*     */ import java.security.PrivateKey;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import javax.crypto.SecretKey;
/*     */ import net.minecraft.client.main.Main;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ public class TcpConnection
/*     */   implements INetworkManager
/*     */ {
/*  27 */   public static AtomicInteger field_74471_a = new AtomicInteger();
/*  28 */   public static AtomicInteger field_74469_b = new AtomicInteger();
/*     */ 
/*     */ 
/*     */   
/*     */   private final Object sendQueueLock;
/*     */ 
/*     */ 
/*     */   
/*     */   private final ILogAgent tcpConLogAgent;
/*     */ 
/*     */ 
/*     */   
/*     */   private Socket networkSocket;
/*     */ 
/*     */ 
/*     */   
/*     */   private final SocketAddress remoteSocketAddress;
/*     */ 
/*     */   
/*     */   private volatile DataInputStream socketInputStream;
/*     */ 
/*     */   
/*     */   private volatile DataOutputStream socketOutputStream;
/*     */ 
/*     */   
/*     */   private volatile boolean isRunning;
/*     */ 
/*     */   
/*     */   private volatile boolean isTerminating;
/*     */ 
/*     */   
/*     */   private Queue readPackets;
/*     */ 
/*     */   
/*     */   private List dataPackets;
/*     */ 
/*     */   
/*     */   private List chunkDataPackets;
/*     */ 
/*     */   
/*     */   private NetHandler theNetHandler;
/*     */ 
/*     */   
/*     */   private boolean isServerTerminating;
/*     */ 
/*     */   
/*     */   private Thread writeThread;
/*     */ 
/*     */   
/*     */   private Thread readThread;
/*     */ 
/*     */   
/*     */   private String terminationReason;
/*     */ 
/*     */   
/*     */   private Object[] shutdownDescription;
/*     */ 
/*     */   
/*     */   private int field_74490_x;
/*     */ 
/*     */   
/*     */   private int sendQueueByteLength;
/*     */ 
/*     */   
/*  92 */   public static int[] field_74470_c = new int[256];
/*  93 */   public static int[] field_74467_d = new int[256];
/*     */   
/*     */   public int field_74468_e;
/*     */   
/*     */   boolean isInputBeingDecrypted;
/*     */   
/*     */   boolean isOutputEncrypted;
/*     */   
/*     */   private SecretKey sharedKeyForEncryption;
/*     */   
/*     */   private PrivateKey field_74463_A;
/*     */   private int chunkDataPacketsDelay;
/*     */   
/*     */   public TcpConnection(ILogAgent par1ILogAgent, Socket par2Socket, String par3Str, NetHandler par4NetHandler) throws IOException {
/* 107 */     this(par1ILogAgent, par2Socket, par3Str, par4NetHandler, (PrivateKey)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public TcpConnection(ILogAgent par1ILogAgent, Socket par2Socket, String par3Str, NetHandler par4NetHandler, PrivateKey par5PrivateKey) throws IOException {
/* 112 */     this.sendQueueLock = new Object();
/* 113 */     this.isRunning = true;
/* 114 */     this.readPackets = new ConcurrentLinkedQueue();
/* 115 */     this.dataPackets = Collections.synchronizedList(new ArrayList());
/* 116 */     this.chunkDataPackets = Collections.synchronizedList(new ArrayList());
/* 117 */     this.terminationReason = "";
/* 118 */     this.chunkDataPacketsDelay = 50;
/* 119 */     this.field_74463_A = par5PrivateKey;
/* 120 */     this.networkSocket = par2Socket;
/* 121 */     this.tcpConLogAgent = par1ILogAgent;
/* 122 */     this.remoteSocketAddress = par2Socket.getRemoteSocketAddress();
/* 123 */     this.theNetHandler = par4NetHandler;
/*     */ 
/*     */     
/*     */     try {
/* 127 */       par2Socket.setSoTimeout(30000);
/* 128 */       par2Socket.setTrafficClass(24);
/*     */     }
/* 130 */     catch (SocketException var7) {
/*     */       
/* 132 */       System.err.println(var7.getMessage());
/*     */     } 
/*     */     
/* 135 */     this.socketInputStream = new DataInputStream(par2Socket.getInputStream());
/* 136 */     this.socketOutputStream = new DataOutputStream(new BufferedOutputStream(par2Socket.getOutputStream(), 5120));
/* 137 */     this.readThread = new TcpReaderThread(this, par3Str + " read thread");
/* 138 */     this.writeThread = new TcpWriterThread(this, par3Str + " write thread");
/* 139 */     this.readThread.start();
/* 140 */     this.writeThread.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeConnections() {
/* 145 */     wakeThreads();
/* 146 */     this.writeThread = null;
/* 147 */     this.readThread = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNetHandler(NetHandler par1NetHandler) {
/* 155 */     this.theNetHandler = par1NetHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToSendQueue(Packet par1Packet) {
/* 163 */     if (!par1Packet.hasBeenAddedToTcpSendQueue()) {
/*     */       
/* 165 */       par1Packet.compressPayload();
/* 166 */       par1Packet.setHasBeenAddedToTcpSendQueue();
/*     */     } 
/*     */     
/* 169 */     if (Main.isPacketIgnored(this.theNetHandler, par1Packet)) {
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
/* 183 */     if (!this.isServerTerminating) {
/*     */       
/* 185 */       Object var2 = this.sendQueueLock;
/*     */       
/* 187 */       synchronized (this.sendQueueLock) {
/*     */         
/* 189 */         this.sendQueueByteLength += par1Packet.getPacketSize() + 1;
/* 190 */         this.dataPackets.add(par1Packet);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean sendPacket() {
/* 201 */     boolean var1 = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 209 */       if (this.field_74468_e == 0 || (!this.dataPackets.isEmpty() && MinecraftServer.getSystemTimeMillis() - ((Packet)this.dataPackets.get(0)).creationTimeMillis >= this.field_74468_e)) {
/*     */         
/* 211 */         Packet var2 = func_74460_a(false);
/*     */         
/* 213 */         if (var2 != null) {
/*     */           
/* 215 */           Packet.writePacket(var2, this.socketOutputStream);
/*     */           
/* 217 */           if (var2 instanceof Packet252SharedKey && !this.isOutputEncrypted) {
/*     */             
/* 219 */             if (!this.theNetHandler.isServerHandler())
/*     */             {
/* 221 */               this.sharedKeyForEncryption = ((Packet252SharedKey)var2).getSharedKey();
/*     */             }
/*     */             
/* 224 */             encryptOuputStream();
/*     */           } 
/*     */           
/* 227 */           int[] var10000 = field_74467_d;
/* 228 */           int var10001 = var2.getPacketId();
/* 229 */           var10000[var10001] = var10000[var10001] + var2.getPacketSize() + 1;
/* 230 */           var1 = true;
/*     */         } 
/*     */       } 
/*     */       
/* 234 */       if (this.chunkDataPacketsDelay-- <= 0 && (this.field_74468_e == 0 || (!this.chunkDataPackets.isEmpty() && MinecraftServer.getSystemTimeMillis() - ((Packet)this.chunkDataPackets.get(0)).creationTimeMillis >= this.field_74468_e))) {
/*     */         
/* 236 */         Packet var2 = func_74460_a(true);
/*     */         
/* 238 */         if (var2 != null) {
/*     */           
/* 240 */           Packet.writePacket(var2, this.socketOutputStream);
/* 241 */           int[] var10000 = field_74467_d;
/* 242 */           int var10001 = var2.getPacketId();
/* 243 */           var10000[var10001] = var10000[var10001] + var2.getPacketSize() + 1;
/* 244 */           this.chunkDataPacketsDelay = 0;
/* 245 */           var1 = true;
/*     */         } 
/*     */       } 
/*     */       
/* 249 */       return var1;
/*     */     }
/* 251 */     catch (Exception var3) {
/*     */       
/* 253 */       if (!this.isTerminating)
/*     */       {
/* 255 */         onNetworkError(var3);
/*     */       }
/*     */       
/* 258 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Packet func_74460_a(boolean par1) {
/* 264 */     Packet var2 = null;
/* 265 */     List<Packet> var3 = par1 ? this.chunkDataPackets : this.dataPackets;
/* 266 */     Object var4 = this.sendQueueLock;
/*     */     
/* 268 */     synchronized (this.sendQueueLock) {
/*     */       
/* 270 */       while (!var3.isEmpty() && var2 == null) {
/*     */         
/* 272 */         var2 = var3.remove(0);
/* 273 */         this.sendQueueByteLength -= var2.getPacketSize() + 1;
/*     */         
/* 275 */         if (func_74454_a(var2, par1))
/*     */         {
/* 277 */           var2 = null;
/*     */         }
/*     */       } 
/*     */       
/* 281 */       return var2;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean func_74454_a(Packet par1Packet, boolean par2) {
/*     */     Packet var5;
/* 287 */     if (!par1Packet.isRealPacket())
/*     */     {
/* 289 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 293 */     List var3 = par2 ? this.chunkDataPackets : this.dataPackets;
/* 294 */     Iterator<Packet> var4 = var3.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 299 */       if (!var4.hasNext())
/*     */       {
/* 301 */         return false;
/*     */       }
/*     */       
/* 304 */       var5 = var4.next();
/*     */     }
/* 306 */     while (var5.getPacketId() != par1Packet.getPacketId());
/*     */     
/* 308 */     return par1Packet.containsSameEntityIDAs(var5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void wakeThreads() {
/* 317 */     if (this.readThread != null)
/*     */     {
/* 319 */       this.readThread.interrupt();
/*     */     }
/*     */     
/* 322 */     if (this.writeThread != null)
/*     */     {
/* 324 */       this.writeThread.interrupt();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean readPacket() {
/* 334 */     boolean var1 = false;
/*     */ 
/*     */     
/*     */     try {
/* 338 */       Packet var2 = Packet.readPacket(this.tcpConLogAgent, this.socketInputStream, this.theNetHandler.isServerHandler(), this.networkSocket);
/*     */       
/* 340 */       if (var2 != null) {
/*     */         
/* 342 */         if (var2 instanceof Packet252SharedKey && !this.isInputBeingDecrypted) {
/*     */           
/* 344 */           if (this.theNetHandler.isServerHandler())
/*     */           {
/* 346 */             this.sharedKeyForEncryption = ((Packet252SharedKey)var2).getSharedKey(this.field_74463_A);
/*     */           }
/*     */           
/* 349 */           decryptInputStream();
/*     */         } 
/*     */         
/* 352 */         int[] var10000 = field_74470_c;
/* 353 */         int var10001 = var2.getPacketId();
/* 354 */         var10000[var10001] = var10000[var10001] + var2.getPacketSize() + 1;
/*     */         
/* 356 */         if (!this.isServerTerminating)
/*     */         {
/* 358 */           if (var2.canProcessAsync() && this.theNetHandler.canProcessPacketsAsync()) {
/*     */             
/* 360 */             this.field_74490_x = 0;
/* 361 */             var2.processPacket(this.theNetHandler);
/*     */           }
/*     */           else {
/*     */             
/* 365 */             this.readPackets.add(var2);
/*     */           } 
/*     */         }
/*     */         
/* 369 */         var1 = true;
/*     */       }
/*     */       else {
/*     */         
/* 373 */         networkShutdown("disconnect.endOfStream", new Object[0]);
/*     */       } 
/*     */       
/* 376 */       return var1;
/*     */     }
/* 378 */     catch (Exception var3) {
/*     */       
/* 380 */       if (!this.isTerminating)
/*     */       {
/* 382 */         onNetworkError(var3);
/*     */       }
/*     */       
/* 385 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onNetworkError(Exception par1Exception) {
/* 394 */     par1Exception.printStackTrace();
/* 395 */     networkShutdown("disconnect.genericReason", new Object[] { "Internal exception: " + par1Exception.toString() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void networkShutdown(String par1Str, Object... par2ArrayOfObj) {
/* 404 */     if (this.isRunning) {
/*     */       
/* 406 */       this.isTerminating = true;
/* 407 */       this.terminationReason = par1Str;
/* 408 */       this.shutdownDescription = par2ArrayOfObj;
/* 409 */       this.isRunning = false;
/* 410 */       (new TcpMasterThread(this)).start();
/*     */ 
/*     */       
/*     */       try {
/* 414 */         this.socketInputStream.close();
/*     */       }
/* 416 */       catch (Throwable var6) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 423 */         this.socketOutputStream.close();
/*     */       }
/* 425 */       catch (Throwable var5) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 432 */         this.networkSocket.close();
/*     */       }
/* 434 */       catch (Throwable var4) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 439 */       this.socketInputStream = null;
/* 440 */       this.socketOutputStream = null;
/* 441 */       this.networkSocket = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processReadPackets() {
/* 450 */     if (this.sendQueueByteLength > 2097152)
/*     */     {
/* 452 */       networkShutdown("disconnect.overflow", new Object[0]);
/*     */     }
/*     */     
/* 455 */     if (this.readPackets.isEmpty()) {
/*     */       
/* 457 */       if (this.field_74490_x++ == 1200)
/*     */       {
/* 459 */         networkShutdown("disconnect.timeout", new Object[0]);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 464 */       this.field_74490_x = 0;
/*     */     } 
/*     */     
/* 467 */     boolean is_MITE_DS_client_player = (Main.is_MITE_DS && this.theNetHandler instanceof NetClientHandler);
/*     */     
/* 469 */     int var1 = 1000;
/*     */     
/* 471 */     while (var1-- >= 0) {
/*     */       
/* 473 */       Packet var2 = this.readPackets.poll();
/*     */       
/* 475 */       if (is_MITE_DS_client_player && !Main.isPacketThatMITEDSClientPlayerCanSendOrReceive(var2)) {
/*     */         continue;
/*     */       }
/* 478 */       if (var2 != null && !this.theNetHandler.isConnectionClosed())
/*     */       {
/* 480 */         var2.processPacket(this.theNetHandler);
/*     */       }
/*     */     } 
/*     */     
/* 484 */     wakeThreads();
/*     */     
/* 486 */     if (this.isTerminating && this.readPackets.isEmpty())
/*     */     {
/* 488 */       this.theNetHandler.handleErrorMessage(this.terminationReason, this.shutdownDescription);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocketAddress getSocketAddress() {
/* 497 */     return this.remoteSocketAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void serverShutdown() {
/* 505 */     if (!this.isServerTerminating) {
/*     */       
/* 507 */       wakeThreads();
/* 508 */       this.isServerTerminating = true;
/* 509 */       this.readThread.interrupt();
/* 510 */       (new TcpMonitorThread(this)).start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void decryptInputStream() throws IOException {
/* 516 */     this.isInputBeingDecrypted = true;
/* 517 */     InputStream var1 = this.networkSocket.getInputStream();
/* 518 */     this.socketInputStream = new DataInputStream(CryptManager.decryptInputStream(this.sharedKeyForEncryption, var1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void encryptOuputStream() throws IOException {
/* 526 */     this.socketOutputStream.flush();
/* 527 */     this.isOutputEncrypted = true;
/* 528 */     BufferedOutputStream var1 = new BufferedOutputStream(CryptManager.encryptOuputStream(this.sharedKeyForEncryption, this.networkSocket.getOutputStream()), 5120);
/* 529 */     this.socketOutputStream = new DataOutputStream(var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int packetSize() {
/* 537 */     return this.chunkDataPackets.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket getSocket() {
/* 542 */     return this.networkSocket;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean isRunning(TcpConnection par0TcpConnection) {
/* 550 */     return par0TcpConnection.isRunning;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean isServerTerminating(TcpConnection par0TcpConnection) {
/* 558 */     return par0TcpConnection.isServerTerminating;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean readNetworkPacket(TcpConnection par0TcpConnection) {
/* 566 */     return par0TcpConnection.readPacket();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean sendNetworkPacket(TcpConnection par0TcpConnection) {
/* 574 */     return par0TcpConnection.sendPacket();
/*     */   }
/*     */ 
/*     */   
/*     */   static DataOutputStream getOutputStream(TcpConnection par0TcpConnection) {
/* 579 */     return par0TcpConnection.socketOutputStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean isTerminating(TcpConnection par0TcpConnection) {
/* 587 */     return par0TcpConnection.isTerminating;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void sendError(TcpConnection par0TcpConnection, Exception par1Exception) {
/* 595 */     par0TcpConnection.onNetworkError(par1Exception);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Thread getReadThread(TcpConnection par0TcpConnection) {
/* 603 */     return par0TcpConnection.readThread;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Thread getWriteThread(TcpConnection par0TcpConnection) {
/* 611 */     return par0TcpConnection.writeThread;
/*     */   }
/*     */ 
/*     */   
/*     */   public int clearReceivedPackets() {
/* 616 */     int num_packets = 0;
/*     */     
/* 618 */     while (this.readPackets.poll() != null && !this.theNetHandler.isConnectionClosed()) {
/* 619 */       num_packets++;
/*     */     }
/* 621 */     return num_packets;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TcpConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */