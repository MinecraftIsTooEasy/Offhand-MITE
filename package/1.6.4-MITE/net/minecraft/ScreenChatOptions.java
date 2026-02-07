/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ScreenChatOptions
/*    */   extends GuiScreen
/*    */ {
/*  7 */   private static final EnumOptions[] allScreenChatOptions = new EnumOptions[] { EnumOptions.CHAT_VISIBILITY, EnumOptions.CHAT_COLOR, EnumOptions.CHAT_LINKS, EnumOptions.CHAT_OPACITY, EnumOptions.CHAT_LINKS_PROMPT, EnumOptions.CHAT_SCALE, EnumOptions.CHAT_HEIGHT_FOCUSED, EnumOptions.CHAT_HEIGHT_UNFOCUSED, EnumOptions.CHAT_WIDTH };
/*    */ 
/*    */ 
/*    */   
/* 11 */   private static final EnumOptions[] allMultiplayerOptions = new EnumOptions[] { EnumOptions.SHOW_CAPE };
/*    */   
/*    */   private final GuiScreen theGuiScreen;
/*    */   
/*    */   private final GameSettings theSettings;
/*    */   
/*    */   private String theChatOptions;
/*    */   private String field_82268_n;
/*    */   private int field_82269_o;
/*    */   
/*    */   public ScreenChatOptions(GuiScreen guiScreen, GameSettings gameSettings) {
/* 22 */     this.theGuiScreen = guiScreen;
/* 23 */     this.theSettings = gameSettings;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 28 */     byte b = 0;
/* 29 */     this.theChatOptions = I18n.getString("options.chat.title");
/* 30 */     this.field_82268_n = I18n.getString("options.multiplayer.title");
/*    */     
/* 32 */     for (EnumOptions enumOptions : allScreenChatOptions) {
/* 33 */       if (enumOptions.getEnumFloat()) {
/* 34 */         this.buttonList.add(new GuiSlider(enumOptions.returnEnumOrdinal(), this.width / 2 - 155 + b % 2 * 160, this.height / 6 + 24 * (b >> 1), enumOptions, this.theSettings.getKeyBinding(enumOptions), this.theSettings.getOptionFloatValue(enumOptions)));
/*    */       } else {
/* 36 */         this.buttonList.add(new GuiSmallButton(enumOptions.returnEnumOrdinal(), this.width / 2 - 155 + b % 2 * 160, this.height / 6 + 24 * (b >> 1), enumOptions, this.theSettings.getKeyBinding(enumOptions)));
/*    */       } 
/*    */       
/* 39 */       b++;
/*    */     } 
/*    */     
/* 42 */     if (b % 2 == 1) b++; 
/* 43 */     this.field_82269_o = this.height / 6 + 24 * (b >> 1);
/* 44 */     b += 2;
/*    */     
/* 46 */     for (EnumOptions enumOptions : allMultiplayerOptions) {
/* 47 */       if (enumOptions.getEnumFloat()) {
/* 48 */         this.buttonList.add(new GuiSlider(enumOptions.returnEnumOrdinal(), this.width / 2 - 155 + b % 2 * 160, this.height / 6 + 24 * (b >> 1), enumOptions, this.theSettings.getKeyBinding(enumOptions), this.theSettings.getOptionFloatValue(enumOptions)));
/*    */       } else {
/* 50 */         this.buttonList.add(new GuiSmallButton(enumOptions.returnEnumOrdinal(), this.width / 2 - 155 + b % 2 * 160, this.height / 6 + 24 * (b >> 1), enumOptions, this.theSettings.getKeyBinding(enumOptions)));
/*    */       } 
/*    */       
/* 53 */       b++;
/*    */     } 
/*    */     
/* 56 */     this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 61 */     if (!guiButton.enabled)
/*    */       return; 
/* 63 */     if (guiButton.id < 100 && guiButton instanceof GuiSmallButton) {
/* 64 */       this.theSettings.setOptionValue(((GuiSmallButton)guiButton).returnEnumOptions(), 1);
/* 65 */       guiButton.displayString = this.theSettings.getKeyBinding(EnumOptions.getEnumOptions(guiButton.id));
/*    */     } 
/* 67 */     if (guiButton.id == 200) {
/* 68 */       this.mc.gameSettings.saveOptions();
/* 69 */       this.mc.displayGuiScreen(this.theGuiScreen);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 75 */     drawDefaultBackground();
/* 76 */     drawCenteredString(this.fontRenderer, this.theChatOptions, this.width / 2, 20, 16777215);
/* 77 */     drawCenteredString(this.fontRenderer, this.field_82268_n, this.width / 2, this.field_82269_o + 7, 16777215);
/*    */     
/* 79 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScreenChatOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */