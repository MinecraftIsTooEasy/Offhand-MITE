/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiScreenClientOutdated
/*    */   extends GuiScreen
/*    */ {
/*    */   private final GuiScreen previousScreen;
/*    */   
/*    */   public GuiScreenClientOutdated(GuiScreen guiScreen) {
/* 14 */     this.previousScreen = guiScreen;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 19 */     this.buttonList.clear();
/* 20 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Back"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 26 */     drawDefaultBackground();
/*    */     
/* 28 */     String str1 = I18n.getString("mco.client.outdated.title");
/* 29 */     String str2 = I18n.getString("mco.client.outdated.msg");
/*    */     
/* 31 */     drawCenteredString(this.fontRenderer, str1, this.width / 2, this.height / 2 - 50, 16711680);
/* 32 */     drawCenteredString(this.fontRenderer, str2, this.width / 2, this.height / 2 - 30, 16777215);
/*    */     
/* 34 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 40 */     if (guiButton.id == 0) {
/* 41 */       this.mc.displayGuiScreen(this.previousScreen);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char c, int i) {
/* 48 */     if (i == 28 || i == 156)
/* 49 */       this.mc.displayGuiScreen(this.previousScreen); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenClientOutdated.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */