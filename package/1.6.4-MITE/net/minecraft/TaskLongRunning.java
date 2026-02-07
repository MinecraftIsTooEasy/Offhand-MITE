/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class TaskLongRunning
/*    */   implements Runnable
/*    */ {
/*    */   protected GuiScreenLongRunningTask taskGUI;
/*    */   
/*    */   public void setGUI(GuiScreenLongRunningTask guiScreenLongRunningTask) {
/* 11 */     this.taskGUI = guiScreenLongRunningTask;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFailedMessage(String string) {
/* 16 */     this.taskGUI.setFailedMessage(string);
/*    */   }
/*    */   
/*    */   public void setMessage(String string) {
/* 20 */     this.taskGUI.setMessage(string);
/*    */   }
/*    */   
/*    */   public Minecraft getMinecraft() {
/* 24 */     return this.taskGUI.func_96208_g();
/*    */   }
/*    */   
/*    */   public boolean wasScreenClosed() {
/* 28 */     return this.taskGUI.wasScreenClosed();
/*    */   }
/*    */   
/*    */   public void updateScreen() {}
/*    */   
/*    */   public void buttonClicked(GuiButton guiButton) {}
/*    */   
/*    */   public void initGUI() {}
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TaskLongRunning.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */