/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ import java.net.InetAddress;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThreadLanServerPing
/*    */   extends Thread
/*    */ {
/*    */   private final String motd;
/*    */   private final DatagramSocket socket;
/*    */   private boolean isStopping = true;
/*    */   private final String address;
/*    */   
/*    */   public ThreadLanServerPing(String string, String string2) {
/* 22 */     super("LanServerPinger");
/* 23 */     this.motd = string;
/* 24 */     this.address = string2;
/* 25 */     setDaemon(true);
/*    */     
/* 27 */     this.socket = new DatagramSocket();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 33 */     String str = getPingResponse(this.motd, this.address);
/* 34 */     byte[] arrayOfByte = str.getBytes();
/*    */     
/* 36 */     while (!isInterrupted() && this.isStopping) {
/*    */       
/*    */       try {
/* 39 */         InetAddress inetAddress = InetAddress.getByName("224.0.2.60");
/*    */         
/* 41 */         DatagramPacket datagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length, inetAddress, 4445);
/* 42 */         this.socket.send(datagramPacket);
/* 43 */       } catch (IOException iOException) {
/* 44 */         Minecraft.getMinecraft().getLogAgent().logWarning("LanServerPinger: " + iOException.getMessage());
/*    */         
/*    */         break;
/*    */       } 
/*    */       try {
/* 49 */         sleep(1500L);
/* 50 */       } catch (InterruptedException interruptedException) {}
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void interrupt() {
/* 57 */     super.interrupt();
/*    */ 
/*    */     
/* 60 */     this.isStopping = false;
/*    */   }
/*    */   
/*    */   public static String getPingResponse(String string, String string2) {
/* 64 */     return "[MOTD]" + string + "[/MOTD][AD]" + string2 + "[/AD]";
/*    */   }
/*    */   
/*    */   public static String getMotdFromPingResponse(String string) {
/* 68 */     int i = string.indexOf("[MOTD]");
/* 69 */     if (i < 0) {
/* 70 */       return "missing no";
/*    */     }
/* 72 */     int j = string.indexOf("[/MOTD]", i + "[MOTD]".length());
/* 73 */     if (j < i) {
/* 74 */       return "missing no";
/*    */     }
/* 76 */     return string.substring(i + "[MOTD]".length(), j);
/*    */   }
/*    */   
/*    */   public static String getAdFromPingResponse(String string) {
/* 80 */     int i = string.indexOf("[/MOTD]");
/* 81 */     if (i < 0) {
/* 82 */       return null;
/*    */     }
/*    */     
/* 85 */     int j = string.indexOf("[/MOTD]", i + "[/MOTD]".length());
/* 86 */     if (j >= 0)
/*    */     {
/* 88 */       return null;
/*    */     }
/*    */     
/* 91 */     int k = string.indexOf("[AD]", i + "[/MOTD]".length());
/* 92 */     if (k < 0) {
/* 93 */       return null;
/*    */     }
/* 95 */     int m = string.indexOf("[/AD]", k + "[AD]".length());
/* 96 */     if (m < k) {
/* 97 */       return null;
/*    */     }
/* 99 */     return string.substring(k + "[AD]".length(), m);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadLanServerPing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */