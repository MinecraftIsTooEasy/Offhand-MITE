/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiSleepMP
/*    */   extends GuiChat
/*    */ {
/*    */   public void initGui() {
/* 10 */     super.initGui();
/*    */ 
/*    */ 
/*    */     
/* 14 */     if (Minecraft.is_dedicated_server_running) {
/*    */       
/* 16 */       int center_x = this.width / 2;
/*    */       
/* 18 */       this.buttonList.add(new GuiButton(1, center_x - 91, this.height - 44, 89, 20, I18n.getString("multiplayer.stopSleeping")));
/* 19 */       this.buttonList.add(new GuiButton(2, center_x + 2, this.height - 44, 89, 20, I18n.getString("menu.disconnect")));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char par1, int par2) {
/* 32 */     if (par2 == 1) {
/*    */       
/* 34 */       wakeEntity();
/*    */     }
/* 36 */     else if (par2 != 28 && par2 != 156) {
/*    */       
/* 38 */       super.keyTyped(par1, par2);
/*    */     }
/*    */     else {
/*    */       
/* 42 */       String var3 = this.inputField.getText().trim();
/*    */       
/* 44 */       if (var3.length() > 0)
/*    */       {
/* 46 */         this.mc.thePlayer.sendChatMessage(var3);
/*    */       }
/*    */       
/* 49 */       this.inputField.setText("");
/* 50 */       this.mc.ingameGUI.getChatGUI().resetScroll();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton par1GuiButton) {
/* 59 */     if (par1GuiButton.id == 1) {
/*    */       
/* 61 */       wakeEntity();
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 66 */     else if (par1GuiButton.id == 2) {
/*    */       
/* 68 */       par1GuiButton.enabled = false;
/* 69 */       this.mc.statFileWriter.readStat(StatList.leaveGameStat, 1);
/* 70 */       this.mc.theWorld.sendQuittingDisconnectingPacket();
/* 71 */       this.mc.loadWorld((WorldClient)null);
/* 72 */       this.mc.displayGuiScreen(new GuiMainMenu());
/*    */     }
/*    */     else {
/*    */       
/* 76 */       super.actionPerformed(par1GuiButton);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void wakeEntity() {
/* 85 */     NetClientHandler var1 = this.mc.thePlayer.sendQueue;
/* 86 */     var1.addToSendQueue(new Packet19EntityAction(this.mc.thePlayer, 3));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSleepMP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */