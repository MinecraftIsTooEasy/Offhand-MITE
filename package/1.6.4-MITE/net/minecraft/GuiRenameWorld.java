/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ 
/*    */ public class GuiRenameWorld
/*    */   extends GuiScreen
/*    */ {
/*    */   private GuiScreen parentGuiScreen;
/*    */   private GuiTextField theGuiTextField;
/*    */   private final String worldName;
/*    */   
/*    */   public GuiRenameWorld(GuiScreen guiScreen, String string) {
/* 14 */     this.parentGuiScreen = guiScreen;
/* 15 */     this.worldName = string;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateScreen() {
/* 20 */     this.theGuiTextField.updateCursorCounter();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 26 */     Keyboard.enableRepeatEvents(true);
/* 27 */     this.buttonList.clear();
/* 28 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, I18n.getString("selectWorld.renameButton")));
/* 29 */     this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.cancel")));
/*    */     
/* 31 */     ISaveFormat iSaveFormat = this.mc.getSaveLoader();
/* 32 */     WorldInfo worldInfo = iSaveFormat.getWorldInfo(this.worldName);
/* 33 */     String str = worldInfo.getWorldName();
/*    */     
/* 35 */     this.theGuiTextField = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
/* 36 */     this.theGuiTextField.setFocused(true);
/* 37 */     this.theGuiTextField.setText(str);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onGuiClosed() {
/* 42 */     Keyboard.enableRepeatEvents(false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 47 */     if (!guiButton.enabled)
/* 48 */       return;  if (guiButton.id == 1) {
/* 49 */       this.mc.displayGuiScreen(this.parentGuiScreen);
/* 50 */     } else if (guiButton.id == 0) {
/*    */       
/* 52 */       ISaveFormat iSaveFormat = this.mc.getSaveLoader();
/* 53 */       iSaveFormat.renameWorld(this.worldName, this.theGuiTextField.getText().trim());
/*    */       
/* 55 */       this.mc.displayGuiScreen(this.parentGuiScreen);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void keyTyped(char c, int i) {
/* 61 */     this.theGuiTextField.textboxKeyTyped(c, i);
/* 62 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.theGuiTextField.getText().trim().length() > 0);
/*    */     
/* 64 */     if (i == 28 || i == 156) {
/* 65 */       actionPerformed(this.buttonList.get(0));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void mouseClicked(int i, int j, int k) {
/* 71 */     super.mouseClicked(i, j, k);
/*    */     
/* 73 */     this.theGuiTextField.mouseClicked(i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 79 */     drawDefaultBackground();
/*    */     
/* 81 */     drawCenteredString(this.fontRenderer, I18n.getString("selectWorld.renameTitle"), this.width / 2, 20, 16777215);
/* 82 */     drawString(this.fontRenderer, I18n.getString("selectWorld.enterName"), this.width / 2 - 100, 47, 10526880);
/*    */     
/* 84 */     this.theGuiTextField.drawTextBox();
/*    */     
/* 86 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiRenameWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */