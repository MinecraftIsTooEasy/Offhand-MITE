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
/*    */ class GuiScreenPendingInvitationINNER2
/*    */   extends Thread
/*    */ {
/*    */   GuiScreenPendingInvitationINNER2(GuiScreenPendingInvitation guiScreenPendingInvitation) {}
/*    */   
/*    */   public void run() {
/*    */     try {
/* 86 */       McoClient mcoClient = new McoClient(GuiScreenPendingInvitation.func_130041_c(this.field_130132_a).getSession());
/* 87 */       mcoClient.func_130109_b(((PendingInvite)GuiScreenPendingInvitation.func_130042_e(this.field_130132_a).get(GuiScreenPendingInvitation.func_130049_d(this.field_130132_a))).field_130094_a);
/* 88 */       GuiScreenPendingInvitation.func_130040_f(this.field_130132_a);
/* 89 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 90 */       GuiScreenPendingInvitation.func_130056_g(this.field_130132_a).getLogAgent().logSevere(exceptionMcoService.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenPendingInvitationINNER2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */