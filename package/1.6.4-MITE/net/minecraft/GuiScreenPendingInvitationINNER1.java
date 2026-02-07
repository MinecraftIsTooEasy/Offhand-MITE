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
/*    */ class GuiScreenPendingInvitationINNER1
/*    */   extends Thread
/*    */ {
/*    */   GuiScreenPendingInvitationINNER1(GuiScreenPendingInvitation guiScreenPendingInvitation) {}
/*    */   
/*    */   public void run() {
/* 42 */     McoClient mcoClient = new McoClient(GuiScreenPendingInvitation.func_130048_a(this.field_130121_a).getSession());
/*    */     try {
/* 44 */       GuiScreenPendingInvitation.func_130043_a(this.field_130121_a, (mcoClient.func_130108_f()).field_130096_a);
/* 45 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 46 */       GuiScreenPendingInvitation.func_130044_b(this.field_130121_a).getLogAgent().logSevere(exceptionMcoService.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenPendingInvitationINNER1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */