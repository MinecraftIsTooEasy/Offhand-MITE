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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GuiScreenBackupRestoreTask
/*     */   extends TaskLongRunning
/*     */ {
/*     */   private final Backup theBackup;
/*     */   
/*     */   private GuiScreenBackupRestoreTask(GuiScreenBackup guiScreenBackup, Backup backup) {
/* 126 */     this.theBackup = backup;
/*     */   }
/*     */   
/*     */   public void run() {
/* 130 */     setMessage(I18n.getString("mco.backup.restoring"));
/*     */     
/*     */     try {
/* 133 */       McoClient mcoClient = new McoClient(getMinecraft().getSession());
/* 134 */       mcoClient.func_111235_c(GuiScreenBackup.func_110367_b(this.theBackupScreen), this.theBackup.field_110727_a);
/*     */       try {
/* 136 */         Thread.sleep(1000L);
/* 137 */       } catch (InterruptedException interruptedException) {
/* 138 */         Thread.currentThread().interrupt();
/*     */       } 
/* 140 */       getMinecraft().displayGuiScreen(GuiScreenBackup.func_130031_d(this.theBackupScreen));
/* 141 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 142 */       GuiScreenBackup.func_130035_e(this.theBackupScreen).getLogAgent().logSevere(exceptionMcoService.toString());
/* 143 */       setFailedMessage(exceptionMcoService.toString());
/* 144 */     } catch (Exception exception) {
/* 145 */       setFailedMessage(exception.getLocalizedMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenBackupRestoreTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */