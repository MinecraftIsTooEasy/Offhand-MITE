/*    */ package net.minecraft;
/*    */ 
/*    */ public class GuiYesNo
/*    */   extends GuiScreen {
/*    */   protected GuiScreen parentScreen;
/*    */   protected String message1;
/*    */   private String message2;
/*    */   protected String buttonText1;
/*    */   protected String buttonText2;
/*    */   protected int worldNumber;
/*    */   
/*    */   public GuiYesNo(GuiScreen guiScreen, String string, String string2, int i) {
/* 13 */     this.parentScreen = guiScreen;
/* 14 */     this.message1 = string;
/* 15 */     this.message2 = string2;
/* 16 */     this.worldNumber = i;
/*    */     
/* 18 */     this.buttonText1 = I18n.getString("gui.yes");
/* 19 */     this.buttonText2 = I18n.getString("gui.no");
/*    */   }
/*    */   
/*    */   public GuiYesNo(GuiScreen guiScreen, String string, String string2, String string3, String string4, int i) {
/* 23 */     this.parentScreen = guiScreen;
/* 24 */     this.message1 = string;
/* 25 */     this.message2 = string2;
/* 26 */     this.buttonText1 = string3;
/* 27 */     this.buttonText2 = string4;
/* 28 */     this.worldNumber = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 33 */     this.buttonList.add(new GuiSmallButton(0, this.width / 2 - 155, this.height / 6 + 96, this.buttonText1));
/* 34 */     this.buttonList.add(new GuiSmallButton(1, this.width / 2 - 155 + 160, this.height / 6 + 96, this.buttonText2));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 39 */     this.parentScreen.confirmClicked((guiButton.id == 0), this.worldNumber);
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 44 */     drawDefaultBackground();
/*    */     
/* 46 */     drawCenteredString(this.fontRenderer, this.message1, this.width / 2, 70, 16777215);
/* 47 */     drawCenteredString(this.fontRenderer, this.message2, this.width / 2, 90, 16777215);
/*    */     
/* 49 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiYesNo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */