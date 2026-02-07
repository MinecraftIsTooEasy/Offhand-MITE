/*     */ package net.minecraft;
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
/*     */ class TcpReaderThread
/*     */   extends Thread
/*     */ {
/*     */   TcpReaderThread(TcpConnection tcpConnection, String string) {
/*  88 */     super(string);
/*     */   }
/*     */   public void run() {
/*  91 */     TcpConnection.field_74471_a.getAndIncrement();
/*     */     try {
/*  93 */       while (TcpConnection.isRunning(this.theTcpConnection) && !TcpConnection.isServerTerminating(this.theTcpConnection)) {
/*  94 */         while (TcpConnection.readNetworkPacket(this.theTcpConnection));
/*     */ 
/*     */         
/*     */         try {
/*  98 */           sleep(2L);
/*  99 */         } catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     } finally {
/*     */       
/* 103 */       TcpConnection.field_74471_a.getAndDecrement();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TcpReaderThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */