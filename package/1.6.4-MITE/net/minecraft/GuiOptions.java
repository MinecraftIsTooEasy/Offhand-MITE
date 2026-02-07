/*     */ package net.minecraft;
/*     */ 
/*     */ import net.minecraft.client.main.Main;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiOptions
/*     */   extends GuiScreen
/*     */ {
/*  10 */   private static final EnumOptions[] relevantOptions = new EnumOptions[] { EnumOptions.MUSIC, EnumOptions.SOUND, EnumOptions.INVERT_MOUSE, EnumOptions.SENSITIVITY, EnumOptions.FOV, EnumOptions.DIFFICULTY, EnumOptions.TOUCHSCREEN };
/*     */ 
/*     */ 
/*     */   
/*     */   private final GuiScreen parentScreen;
/*     */ 
/*     */ 
/*     */   
/*     */   private final GameSettings options;
/*     */ 
/*     */   
/*  21 */   protected String screenTitle = "Options";
/*     */ 
/*     */   
/*     */   public GuiOptions(GuiScreen par1GuiScreen, GameSettings par2GameSettings) {
/*  25 */     this.parentScreen = par1GuiScreen;
/*  26 */     this.options = par2GameSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  34 */     int var1 = 0;
/*  35 */     this.screenTitle = I18n.getString("options.title");
/*  36 */     EnumOptions[] var2 = relevantOptions;
/*  37 */     int var3 = var2.length;
/*     */     
/*  39 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  41 */       EnumOptions var5 = var2[var4];
/*     */       
/*  43 */       if (var5.getEnumFloat()) {
/*     */         
/*  45 */         this.buttonList.add(new GuiSlider(var5.returnEnumOrdinal(), this.width / 2 - 155 + var1 % 2 * 160, this.height / 6 - 12 + 24 * (var1 >> 1), var5, this.options.getKeyBinding(var5), this.options.getOptionFloatValue(var5)));
/*     */       }
/*     */       else {
/*     */         
/*  49 */         GuiSmallButton var6 = new GuiSmallButton(var5.returnEnumOrdinal(), this.width / 2 - 155 + var1 % 2 * 160, this.height / 6 - 12 + 24 * (var1 >> 1), var5, this.options.getKeyBinding(var5));
/*     */         
/*  51 */         if (var5 == EnumOptions.DIFFICULTY && this.mc.theWorld != null && this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
/*     */           
/*  53 */           var6.enabled = false;
/*  54 */           var6.displayString = I18n.getString("options.difficulty") + ": " + I18n.getString("options.difficulty.hardcore");
/*     */         } 
/*     */         
/*  57 */         if (var5 == EnumOptions.DIFFICULTY) {
/*  58 */           var6.enabled = false;
/*     */         }
/*  60 */         this.buttonList.add(var6);
/*     */       } 
/*     */       
/*  63 */       var1++;
/*     */     } 
/*     */     
/*  66 */     GuiButton button_video_settings = new GuiButton(101, this.width / 2 - 152, this.height / 6 + 96 - 6, 150, 20, I18n.getString("options.video"));
/*  67 */     button_video_settings.enabled = !Main.is_MITE_DS;
/*  68 */     this.buttonList.add(button_video_settings);
/*     */ 
/*     */     
/*  71 */     this.buttonList.add(new GuiButton(100, this.width / 2 + 2, this.height / 6 + 96 - 6, 150, 20, I18n.getString("options.controls")));
/*  72 */     this.buttonList.add(new GuiButton(102, this.width / 2 - 152, this.height / 6 + 120 - 6, 150, 20, I18n.getString("options.language")));
/*  73 */     this.buttonList.add(new GuiButton(103, this.width / 2 + 2, this.height / 6 + 120 - 6, 150, 20, I18n.getString("options.multiplayer.title")));
/*  74 */     this.buttonList.add(new GuiButton(105, this.width / 2 - 152, this.height / 6 + 144 - 6, 150, 20, I18n.getString("options.resourcepack")));
/*  75 */     this.buttonList.add(new GuiButton(104, this.width / 2 + 2, this.height / 6 + 144 - 6, 150, 20, I18n.getString("options.snooper.view")));
/*  76 */     this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  84 */     if (par1GuiButton.enabled) {
/*     */       
/*  86 */       if (par1GuiButton.id < 100 && par1GuiButton instanceof GuiSmallButton) {
/*     */         
/*  88 */         this.options.setOptionValue(((GuiSmallButton)par1GuiButton).returnEnumOptions(), 1);
/*  89 */         par1GuiButton.displayString = this.options.getKeyBinding(EnumOptions.getEnumOptions(par1GuiButton.id));
/*     */       } 
/*     */ 
/*     */       
/*  93 */       if (par1GuiButton.id == 101 && !Main.is_MITE_DS) {
/*     */         
/*  95 */         this.mc.gameSettings.saveOptions();
/*  96 */         this.mc.displayGuiScreen(new GuiVideoSettings(this, this.options));
/*     */       } 
/*     */       
/*  99 */       if (par1GuiButton.id == 100) {
/*     */         
/* 101 */         this.mc.gameSettings.saveOptions();
/* 102 */         this.mc.displayGuiScreen(new GuiControls(this, this.options));
/*     */       } 
/*     */       
/* 105 */       if (par1GuiButton.id == 102) {
/*     */         
/* 107 */         this.mc.gameSettings.saveOptions();
/* 108 */         this.mc.displayGuiScreen(new GuiLanguage(this, this.options, this.mc.getLanguageManager()));
/*     */       } 
/*     */       
/* 111 */       if (par1GuiButton.id == 103) {
/*     */         
/* 113 */         this.mc.gameSettings.saveOptions();
/* 114 */         this.mc.displayGuiScreen(new ScreenChatOptions(this, this.options));
/*     */       } 
/*     */       
/* 117 */       if (par1GuiButton.id == 104) {
/*     */         
/* 119 */         this.mc.gameSettings.saveOptions();
/* 120 */         this.mc.displayGuiScreen(new GuiSnooper(this, this.options));
/*     */       } 
/*     */       
/* 123 */       if (par1GuiButton.id == 200) {
/*     */         
/* 125 */         this.mc.gameSettings.saveOptions();
/* 126 */         this.mc.displayGuiScreen(this.parentScreen);
/*     */       } 
/*     */       
/* 129 */       if (par1GuiButton.id == 105) {
/*     */         
/* 131 */         this.mc.gameSettings.saveOptions();
/* 132 */         this.mc.displayGuiScreen(new GuiScreenTemporaryResourcePackSelect(this, this.options));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 142 */     drawDefaultBackground();
/* 143 */     drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 15, 16777215);
/* 144 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */