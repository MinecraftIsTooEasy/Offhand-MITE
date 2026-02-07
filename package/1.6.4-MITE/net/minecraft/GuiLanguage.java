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
/*    */ public class GuiLanguage
/*    */   extends GuiScreen
/*    */ {
/*    */   protected GuiScreen parentGui;
/*    */   private GuiSlotLanguage languageList;
/*    */   private final GameSettings theGameSettings;
/*    */   private final LanguageManager field_135014_d;
/*    */   private GuiSmallButton doneButton;
/*    */   
/*    */   public GuiLanguage(GuiScreen guiScreen, GameSettings gameSettings, LanguageManager languageManager) {
/* 24 */     this.parentGui = guiScreen;
/* 25 */     this.theGameSettings = gameSettings;
/* 26 */     this.field_135014_d = languageManager;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 32 */     this.buttonList.add(this.doneButton = new GuiSmallButton(6, this.width / 2 - 75, this.height - 38, I18n.getString("gui.done")));
/*    */     
/* 34 */     this.languageList = new GuiSlotLanguage(this);
/* 35 */     this.languageList.registerScrollButtons(7, 8);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 40 */     if (!guiButton.enabled)
/*    */       return; 
/* 42 */     switch (guiButton.id) {
/*    */       case 5:
/*    */         return;
/*    */       case 6:
/* 46 */         this.mc.displayGuiScreen(this.parentGui);
/*    */     } 
/*    */     
/* 49 */     this.languageList.actionPerformed(guiButton);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 57 */     this.languageList.drawScreen(i, j, f);
/*    */     
/* 59 */     drawCenteredString(this.fontRenderer, I18n.getString("options.language"), this.width / 2, 16, 16777215);
/* 60 */     drawCenteredString(this.fontRenderer, "(" + I18n.getString("options.languageWarning") + ")", this.width / 2, this.height - 56, 8421504);
/*    */     
/* 62 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiLanguage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */