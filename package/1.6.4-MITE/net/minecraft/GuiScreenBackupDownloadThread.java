/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class GuiScreenBackupDownloadThread
/*    */   extends Thread
/*    */ {
/*    */   GuiScreenBackupDownloadThread(GuiScreenBackup guiScreenBackup) {}
/*    */   
/*    */   public void run() {
/* 49 */     McoClient mcoClient = new McoClient(GuiScreenBackup.func_110366_a(this.field_111250_a).getSession());
/*    */     try {
/* 51 */       GuiScreenBackup.func_110373_a(this.field_111250_a, (mcoClient.func_111232_c(GuiScreenBackup.func_110367_b(this.field_111250_a))).field_111223_a);
/* 52 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 53 */       GuiScreenBackup.func_130030_c(this.field_111250_a).getLogAgent().logSevere(exceptionMcoService.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenBackupDownloadThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */