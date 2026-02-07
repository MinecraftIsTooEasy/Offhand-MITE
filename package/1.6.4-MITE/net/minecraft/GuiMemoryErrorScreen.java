/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiMemoryErrorScreen
/*    */   extends GuiScreen
/*    */ {
/*    */   public void initGui() {
/* 10 */     this.buttonList.clear();
/* 11 */     this.buttonList.add(new GuiSmallButton(0, this.width / 2 - 155, this.height / 4 + 120 + 12, I18n.getString("gui.toMenu")));
/* 12 */     this.buttonList.add(new GuiSmallButton(1, this.width / 2 - 155 + 160, this.height / 4 + 120 + 12, I18n.getString("menu.quit")));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 17 */     if (guiButton.id == 0) {
/* 18 */       this.mc.displayGuiScreen(new GuiMainMenu());
/* 19 */     } else if (guiButton.id == 1) {
/* 20 */       this.mc.shutdown();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char c, int i) {}
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 30 */     drawDefaultBackground();
/*    */     
/* 32 */     drawCenteredString(this.fontRenderer, "Out of memory!", this.width / 2, this.height / 4 - 60 + 20, 16777215);
/* 33 */     drawString(this.fontRenderer, "Minecraft has run out of memory.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 0, 10526880);
/* 34 */     drawString(this.fontRenderer, "This could be caused by a bug in the game or by the", this.width / 2 - 140, this.height / 4 - 60 + 60 + 18, 10526880);
/* 35 */     drawString(this.fontRenderer, "Java Virtual Machine not being allocated enough", this.width / 2 - 140, this.height / 4 - 60 + 60 + 27, 10526880);
/* 36 */     drawString(this.fontRenderer, "memory. If you are playing in a web browser, try", this.width / 2 - 140, this.height / 4 - 60 + 60 + 36, 10526880);
/* 37 */     drawString(this.fontRenderer, "downloading the game and playing it offline.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 45, 10526880);
/* 38 */     drawString(this.fontRenderer, "To prevent level corruption, the current game has quit.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 63, 10526880);
/* 39 */     drawString(this.fontRenderer, "We've tried to free up enough memory to let you go back to", this.width / 2 - 140, this.height / 4 - 60 + 60 + 81, 10526880);
/* 40 */     drawString(this.fontRenderer, "the main menu and back to playing, but this may not have worked.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 90, 10526880);
/* 41 */     drawString(this.fontRenderer, "Please restart the game if you see this message again.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 99, 10526880);
/*    */     
/* 43 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiMemoryErrorScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */