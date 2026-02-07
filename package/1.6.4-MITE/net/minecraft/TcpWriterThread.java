/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ class TcpWriterThread
/*     */   extends Thread
/*     */ {
/*     */   TcpWriterThread(TcpConnection tcpConnection, String string) {
/* 108 */     super(string);
/*     */   }
/*     */   public void run() {
/* 111 */     TcpConnection.field_74469_b.getAndIncrement();
/*     */     try {
/* 113 */       while (TcpConnection.isRunning(this.theTcpConnection)) {
/* 114 */         boolean bool = false;
/* 115 */         while (TcpConnection.sendNetworkPacket(this.theTcpConnection)) {
/* 116 */           bool = true;
/*     */         }
/*     */         
/*     */         try {
/* 120 */           if (bool && TcpConnection.getOutputStream(this.theTcpConnection) != null) {
/* 121 */             TcpConnection.getOutputStream(this.theTcpConnection).flush();
/*     */           }
/* 123 */         } catch (IOException iOException) {
/* 124 */           if (!TcpConnection.isTerminating(this.theTcpConnection)) TcpConnection.sendError(this.theTcpConnection, iOException); 
/* 125 */           iOException.printStackTrace();
/*     */         } 
/*     */         
/*     */         try {
/* 129 */           sleep(2L);
/* 130 */         } catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     } finally {
/*     */       
/* 134 */       TcpConnection.field_74469_b.getAndDecrement();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TcpWriterThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */