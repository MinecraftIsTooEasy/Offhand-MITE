/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiShareToLan
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen parentScreen;
/*     */   private GuiButton buttonAllowCommandsToggle;
/*     */   private GuiButton buttonGameMode;
/*  15 */   private String gameMode = "survival";
/*     */ 
/*     */   
/*     */   private boolean allowCommands;
/*     */ 
/*     */   
/*     */   public GuiShareToLan(GuiScreen par1GuiScreen) {
/*  22 */     this.parentScreen = par1GuiScreen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  30 */     this.buttonList.clear();
/*  31 */     this.buttonList.add(new GuiButton(101, this.width / 2 - 155, this.height - 28, 150, 20, I18n.getString("lanServer.start")));
/*  32 */     this.buttonList.add(new GuiButton(102, this.width / 2 + 5, this.height - 28, 150, 20, I18n.getString("gui.cancel")));
/*  33 */     this.buttonList.add(this.buttonGameMode = new GuiButton(104, this.width / 2 - 155, 100, 150, 20, I18n.getString("selectWorld.gameMode")));
/*  34 */     this.buttonList.add(this.buttonAllowCommandsToggle = new GuiButton(103, this.width / 2 + 5, 100, 150, 20, I18n.getString("selectWorld.allowCommands")));
/*     */     
/*  36 */     this.buttonGameMode.enabled = false;
/*  37 */     this.buttonAllowCommandsToggle.enabled = false;
/*     */     
/*  39 */     func_74088_g();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_74088_g() {
/*  44 */     this.buttonGameMode.displayString = I18n.getString("selectWorld.gameMode") + " " + I18n.getString("selectWorld.gameMode." + this.gameMode);
/*  45 */     this.buttonAllowCommandsToggle.displayString = I18n.getString("selectWorld.allowCommands") + " ";
/*     */     
/*  47 */     if (this.allowCommands) {
/*     */       
/*  49 */       this.buttonAllowCommandsToggle.displayString += I18n.getString("options.on");
/*     */     }
/*     */     else {
/*     */       
/*  53 */       this.buttonAllowCommandsToggle.displayString += I18n.getString("options.off");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void shareToLAN() {
/*     */     ChatMessageComponent var3;
/*  59 */     String var2 = Minecraft.isInTournamentMode() ? null : Minecraft.theMinecraft.getIntegratedServer().shareToLAN(EnumGameType.getByName("survival"), false);
/*     */ 
/*     */ 
/*     */     
/*  63 */     if (var2 != null) {
/*     */       
/*  65 */       var3 = ChatMessageComponent.createFromTranslationWithSubstitutions("commands.publish.started", new Object[] { var2 });
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  70 */       var3 = ChatMessageComponent.createFromTranslationKey("commands.publish.failed");
/*     */     } 
/*     */     
/*  73 */     Minecraft.theMinecraft.ingameGUI.getChatGUI().printChatMessage(var3.toStringWithFormatting(true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  81 */     if (par1GuiButton.id == 102) {
/*     */       
/*  83 */       this.mc.displayGuiScreen(this.parentScreen);
/*     */     }
/*  85 */     else if (par1GuiButton.id == 104) {
/*     */       
/*  87 */       if (this.gameMode.equals("survival")) {
/*     */         
/*  89 */         this.gameMode = "creative";
/*     */       }
/*  91 */       else if (this.gameMode.equals("creative")) {
/*     */         
/*  93 */         this.gameMode = "adventure";
/*     */       }
/*     */       else {
/*     */         
/*  97 */         this.gameMode = "survival";
/*     */       } 
/*     */       
/* 100 */       this.gameMode = "survival";
/*     */       
/* 102 */       func_74088_g();
/*     */     }
/* 104 */     else if (par1GuiButton.id == 103) {
/*     */       
/* 106 */       this.allowCommands = !this.allowCommands;
/*     */       
/* 108 */       this.allowCommands = false;
/*     */       
/* 110 */       func_74088_g();
/*     */     }
/* 112 */     else if (par1GuiButton.id == 101) {
/*     */       
/* 114 */       if (!Minecraft.inDevMode()) {
/*     */         
/* 116 */         this.gameMode = "survival";
/* 117 */         this.allowCommands = false;
/*     */       } 
/*     */       
/* 120 */       this.mc.displayGuiScreen((GuiScreen)null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 138 */       shareToLAN();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 147 */     drawDefaultBackground();
/* 148 */     drawCenteredString(this.fontRenderer, I18n.getString("lanServer.title"), this.width / 2, 50, 16777215);
/* 149 */     drawCenteredString(this.fontRenderer, I18n.getString("lanServer.otherPlayers"), this.width / 2, 82, 16777215);
/* 150 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiShareToLan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */