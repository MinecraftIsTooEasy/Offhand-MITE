/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.InetAddress;
/*     */ import java.net.MulticastSocket;
/*     */ import java.net.SocketTimeoutException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThreadLanServerFind
/*     */   extends Thread
/*     */ {
/*     */   private final LanServerList localServerList;
/*     */   private final InetAddress broadcastAddress;
/*     */   private final MulticastSocket socket;
/*     */   
/*     */   public ThreadLanServerFind(LanServerList lanServerList) {
/*  93 */     super("LanServerDetector");
/*  94 */     this.localServerList = lanServerList;
/*  95 */     setDaemon(true);
/*     */     
/*  97 */     this.socket = new MulticastSocket(4445);
/*  98 */     this.broadcastAddress = InetAddress.getByName("224.0.2.60");
/*  99 */     this.socket.setSoTimeout(5000);
/* 100 */     this.socket.joinGroup(this.broadcastAddress);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 107 */     byte[] arrayOfByte = new byte[1024];
/*     */     
/* 109 */     while (!isInterrupted()) {
/*     */       
/* 111 */       DatagramPacket datagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length);
/*     */       try {
/* 113 */         this.socket.receive(datagramPacket);
/* 114 */       } catch (SocketTimeoutException socketTimeoutException) {
/*     */         continue;
/* 116 */       } catch (IOException iOException) {
/* 117 */         iOException.printStackTrace();
/*     */         
/*     */         break;
/*     */       } 
/* 121 */       String str = new String(datagramPacket.getData(), datagramPacket.getOffset(), datagramPacket.getLength());
/* 122 */       Minecraft.getMinecraft().getLogAgent().logFine(datagramPacket.getAddress() + ": " + str);
/* 123 */       this.localServerList.func_77551_a(str, datagramPacket.getAddress());
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 128 */       this.socket.leaveGroup(this.broadcastAddress);
/* 129 */     } catch (IOException iOException) {}
/*     */     
/* 131 */     this.socket.close();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadLanServerFind.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */