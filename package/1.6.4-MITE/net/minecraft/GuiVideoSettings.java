/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class GuiVideoSettings
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiScreen parentGuiScreen;
/*   8 */   protected String screenTitle = "Video Settings";
/*     */ 
/*     */ 
/*     */   
/*     */   private GameSettings guiGameSettings;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean is64bit;
/*     */ 
/*     */   
/*  19 */   private static EnumOptions[] videoOptions = new EnumOptions[] { EnumOptions.GRAPHICS, EnumOptions.RENDER_DISTANCE, EnumOptions.AMBIENT_OCCLUSION, EnumOptions.FRAMERATE_LIMIT, EnumOptions.ANAGLYPH, EnumOptions.VIEW_BOBBING, EnumOptions.GUI_SCALE, EnumOptions.ADVANCED_OPENGL, EnumOptions.GAMMA, EnumOptions.RENDER_CLOUDS, EnumOptions.PARTICLES, EnumOptions.USE_SERVER_TEXTURES, EnumOptions.USE_FULLSCREEN, EnumOptions.ENABLE_VSYNC };
/*     */ 
/*     */   
/*     */   public GuiVideoSettings(GuiScreen par1GuiScreen, GameSettings par2GameSettings) {
/*  23 */     this.parentGuiScreen = par1GuiScreen;
/*  24 */     this.guiGameSettings = par2GameSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  32 */     this.screenTitle = I18n.getString("options.videoTitle");
/*  33 */     this.buttonList.clear();
/*  34 */     this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
/*  35 */     this.is64bit = false;
/*  36 */     String[] var1 = { "sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch" };
/*  37 */     String[] var2 = var1;
/*  38 */     int var3 = var1.length;
/*     */     
/*  40 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  42 */       String var5 = var2[var4];
/*  43 */       String var6 = System.getProperty(var5);
/*     */       
/*  45 */       if (var6 != null && var6.contains("64")) {
/*     */         
/*  47 */         this.is64bit = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  52 */     int var8 = 0;
/*  53 */     var3 = this.is64bit ? 0 : -15;
/*  54 */     EnumOptions[] var9 = videoOptions;
/*  55 */     int var10 = var9.length;
/*     */     
/*  57 */     for (int var11 = 0; var11 < var10; var11++) {
/*     */       
/*  59 */       EnumOptions var7 = var9[var11];
/*     */       
/*  61 */       if (var7.getEnumFloat()) {
/*     */         
/*  63 */         GuiSlider slider = new GuiSlider(var7.returnEnumOrdinal(), this.width / 2 - 155 + var8 % 2 * 160, this.height / 7 + var3 + 24 * (var8 >> 1), var7, this.guiGameSettings.getKeyBinding(var7), this.guiGameSettings.getOptionFloatValue(var7));
/*     */         
/*  65 */         if (var7 == EnumOptions.GAMMA) {
/*  66 */           slider.enabled = false;
/*     */         }
/*  68 */         this.buttonList.add(slider);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/*  74 */         this.buttonList.add(new GuiSmallButton(var7.returnEnumOrdinal(), this.width / 2 - 155 + var8 % 2 * 160, this.height / 7 + var3 + 24 * (var8 >> 1), var7, this.guiGameSettings.getKeyBinding(var7)));
/*     */       } 
/*     */       
/*  77 */       var8++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  86 */     if (par1GuiButton.enabled) {
/*     */       
/*  88 */       int var2 = this.guiGameSettings.guiScale;
/*     */       
/*  90 */       if (par1GuiButton.id < 100 && par1GuiButton instanceof GuiSmallButton) {
/*     */         
/*  92 */         this.guiGameSettings.setOptionValue(((GuiSmallButton)par1GuiButton).returnEnumOptions(), 1);
/*  93 */         par1GuiButton.displayString = this.guiGameSettings.getKeyBinding(EnumOptions.getEnumOptions(par1GuiButton.id));
/*     */       } 
/*     */       
/*  96 */       if (par1GuiButton.id == 200) {
/*     */         
/*  98 */         this.mc.gameSettings.saveOptions();
/*  99 */         this.mc.displayGuiScreen(this.parentGuiScreen);
/*     */       } 
/*     */       
/* 102 */       if (this.guiGameSettings.guiScale != var2) {
/*     */         
/* 104 */         ScaledResolution var3 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/* 105 */         int var4 = var3.getScaledWidth();
/* 106 */         int var5 = var3.getScaledHeight();
/* 107 */         setWorldAndResolution(this.mc, var4, var5);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 117 */     drawDefaultBackground();
/* 118 */     drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, this.is64bit ? 20 : 5, 16777215);
/*     */ 
/*     */     
/* 121 */     if (!this.is64bit && this.guiGameSettings.getRenderDistance() == 0) {
/*     */       
/* 123 */       drawCenteredString(this.fontRenderer, I18n.getString("options.farWarning1"), this.width / 2, this.height / 6 + 144 + 1, 11468800);
/* 124 */       drawCenteredString(this.fontRenderer, I18n.getString("options.farWarning2"), this.width / 2, this.height / 6 + 144 + 13, 11468800);
/*     */     } 
/*     */     
/* 127 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiVideoSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */