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
/*     */ class GuiScreenPendingInvitationINNER3
/*     */   extends Thread
/*     */ {
/*     */   GuiScreenPendingInvitationINNER3(GuiScreenPendingInvitation guiScreenPendingInvitation) {}
/*     */   
/*     */   public void run() {
/*     */     try {
/* 103 */       McoClient mcoClient = new McoClient(GuiScreenPendingInvitation.func_130046_h(this.field_130131_a).getSession());
/* 104 */       mcoClient.func_130107_a(((PendingInvite)GuiScreenPendingInvitation.func_130042_e(this.field_130131_a).get(GuiScreenPendingInvitation.func_130049_d(this.field_130131_a))).field_130094_a);
/* 105 */       GuiScreenPendingInvitation.func_130040_f(this.field_130131_a);
/* 106 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 107 */       GuiScreenPendingInvitation.func_130055_i(this.field_130131_a).getLogAgent().logSevere(exceptionMcoService.toString());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenPendingInvitationINNER3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */