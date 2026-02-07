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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ThreadOnlineScreen
/*     */   extends Thread
/*     */ {
/*     */   ThreadOnlineScreen(GuiScreenOnlineServers guiScreenOnlineServers) {}
/*     */   
/*     */   public void run() {
/*     */     try {
/* 196 */       McoServer mcoServer = GuiScreenOnlineServers.func_140011_a(this.field_98173_a, GuiScreenOnlineServers.func_140041_a(this.field_98173_a));
/* 197 */       if (mcoServer != null) {
/* 198 */         McoClient mcoClient = new McoClient(GuiScreenOnlineServers.func_98075_b(this.field_98173_a).getSession());
/* 199 */         GuiScreenOnlineServers.func_140040_h().func_140058_a(mcoServer);
/* 200 */         GuiScreenOnlineServers.func_140013_c(this.field_98173_a).remove(mcoServer);
/* 201 */         mcoClient.func_140055_c(mcoServer.field_96408_a);
/* 202 */         GuiScreenOnlineServers.func_140040_h().func_140058_a(mcoServer);
/* 203 */         GuiScreenOnlineServers.func_140013_c(this.field_98173_a).remove(mcoServer);
/* 204 */         GuiScreenOnlineServers.func_140017_d(this.field_98173_a);
/*     */       } 
/* 206 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 207 */       GuiScreenOnlineServers.func_98076_f(this.field_98173_a).getLogAgent().logSevere(exceptionMcoService.toString());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadOnlineScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */