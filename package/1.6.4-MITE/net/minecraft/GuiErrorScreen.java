/*    */ package net.minecraft;
/*    */ 
/*    */ public class GuiErrorScreen
/*    */   extends GuiScreen {
/*    */   private String message1;
/*    */   private String message2;
/*    */   
/*    */   public GuiErrorScreen(String string, String string2) {
/*  9 */     this.message1 = string;
/* 10 */     this.message2 = string2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 15 */     super.initGui();
/*    */     
/* 17 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 140, I18n.getString("gui.cancel")));
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 22 */     drawGradientRect(0, 0, this.width, this.height, -12574688, -11530224);
/*    */     
/* 24 */     drawCenteredString(this.fontRenderer, this.message1, this.width / 2, 90, 16777215);
/* 25 */     drawCenteredString(this.fontRenderer, this.message2, this.width / 2, 110, 16777215);
/*    */     
/* 27 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char c, int i) {}
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 36 */     this.mc.displayGuiScreen(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiErrorScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */