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
/*    */ class GuiScreenMcoWorldTemplateDownloadThread
/*    */   extends Thread
/*    */ {
/*    */   GuiScreenMcoWorldTemplateDownloadThread(GuiScreenMcoWorldTemplate guiScreenMcoWorldTemplate) {}
/*    */   
/*    */   public void run() {
/* 45 */     McoClient mcoClient = new McoClient(GuiScreenMcoWorldTemplate.func_110382_a(this.field_111256_a).getSession());
/*    */     try {
/* 47 */       GuiScreenMcoWorldTemplate.func_110388_a(this.field_111256_a, (mcoClient.func_111231_d()).field_110736_a);
/* 48 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 49 */       GuiScreenMcoWorldTemplate.func_110392_b(this.field_111256_a).getLogAgent().logSevere(exceptionMcoService.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenMcoWorldTemplateDownloadThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */