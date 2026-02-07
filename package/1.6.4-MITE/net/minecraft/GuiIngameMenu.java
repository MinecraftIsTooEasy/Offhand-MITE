/*     */ package net.minecraft;
/*     */ 
/*     */ import net.minecraft.client.main.Main;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiIngameMenu
/*     */   extends GuiScreen
/*     */ {
/*     */   private int updateCounter2;
/*     */   private int updateCounter;
/*     */   
/*     */   public void initGui() {
/*  19 */     this.updateCounter2 = 0;
/*  20 */     this.buttonList.clear();
/*  21 */     byte var1 = -16;
/*  22 */     boolean var2 = true;
/*  23 */     this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + var1, I18n.getString("menu.returnToMenu")));
/*     */     
/*  25 */     if (!this.mc.isIntegratedServerRunning()) {
/*     */       
/*  27 */       ((GuiButton)this.buttonList.get(0)).displayString = I18n.getString("menu.disconnect");
/*  28 */       ((GuiButton)this.buttonList.get(0)).is_disconnect_button = true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  33 */     GuiButton button_return = new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + var1, I18n.getString("menu.returnToGame"));
/*  34 */     GuiButton button_options = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + var1, 98, 20, I18n.getString("menu.options"));
/*  35 */     GuiButton button_lan = new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + var1, 98, 20, I18n.getString("menu.shareToLan"));
/*  36 */     GuiButton button_achievements = new GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + var1, 98, 20, I18n.getString("gui.achievements"));
/*  37 */     GuiButton button_stats = new GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + var1, 98, 20, I18n.getString("gui.stats"));
/*     */     
/*  39 */     if (this.mc.thePlayer.isGhost()) {
/*     */       
/*  41 */       button_return.enabled = false;
/*  42 */       button_options.enabled = false;
/*  43 */       button_achievements.enabled = false;
/*  44 */       button_stats.enabled = false;
/*     */       
/*  46 */       button_return.yPosition = -100;
/*  47 */       button_options.yPosition = -100;
/*  48 */       button_achievements.yPosition = -100;
/*  49 */       button_stats.yPosition = -100;
/*  50 */       button_lan.yPosition = -100;
/*     */     } 
/*     */     
/*  53 */     button_lan.enabled = (Minecraft.isSingleplayer() && !this.mc.getIntegratedServer().getPublic() && !Minecraft.isInTournamentMode());
/*     */     
/*  55 */     this.buttonList.add(button_return);
/*  56 */     this.buttonList.add(button_options);
/*  57 */     this.buttonList.add(button_lan);
/*  58 */     this.buttonList.add(button_achievements);
/*  59 */     this.buttonList.add(button_stats);
/*     */   }
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
/*     */   protected void keyTyped(char par1, int par2) {
/*  74 */     if (!Main.is_MITE_DS) {
/*  75 */       super.keyTyped(par1, par2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  83 */     switch (par1GuiButton.id) {
/*     */       
/*     */       case 0:
/*  86 */         if (!this.mc.thePlayer.isGhost())
/*     */         {
/*     */           
/*  89 */           this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
/*     */         }
/*     */ 
/*     */       
/*     */       case 1:
/*  94 */         par1GuiButton.enabled = false;
/*  95 */         this.mc.statFileWriter.readStat(StatList.leaveGameStat, 1);
/*  96 */         this.mc.theWorld.sendQuittingDisconnectingPacket();
/*  97 */         this.mc.loadWorld((WorldClient)null);
/*  98 */         this.mc.displayGuiScreen(new GuiMainMenu());
/*     */ 
/*     */       
/*     */       default:
/*     */         return;
/*     */ 
/*     */       
/*     */       case 4:
/* 106 */         this.mc.displayGuiScreen((GuiScreen)null);
/* 107 */         this.mc.setIngameFocus();
/* 108 */         this.mc.sndManager.resumeAllSounds();
/*     */ 
/*     */       
/*     */       case 5:
/* 112 */         if (!this.mc.thePlayer.isGhost())
/*     */         {
/*     */           
/* 115 */           this.mc.displayGuiScreen(new GuiAchievements(this.mc.statFileWriter));
/*     */         }
/*     */       
/*     */       case 6:
/* 119 */         if (!this.mc.thePlayer.isGhost())
/*     */         {
/*     */           
/* 122 */           this.mc.displayGuiScreen(new GuiStats(this, this.mc.statFileWriter)); } 
/*     */       case 7:
/*     */         break;
/*     */     } 
/* 126 */     if (Minecraft.isInTournamentMode()) {
/*     */       
/* 128 */       this.mc.ingameGUI.getChatGUI().printChatMessage(ChatMessageComponent.createFromTranslationKey("commands.publish.failed").toStringWithFormatting(true));
/*     */       
/* 130 */       this.mc.displayGuiScreen((GuiScreen)null);
/* 131 */       this.mc.setIngameFocus();
/* 132 */       this.mc.sndManager.resumeAllSounds();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 137 */     this.mc.displayGuiScreen(new GuiShareToLan(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 146 */     super.updateScreen();
/* 147 */     this.updateCounter++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 155 */     drawDefaultBackground();
/*     */ 
/*     */ 
/*     */     
/* 159 */     if (Main.is_MITE_DS && this.mc.getIntegratedServer().getPublic()) {
/*     */       
/* 161 */       drawCenteredString(this.fontRenderer, "Dedicated Server Is Now Running", this.width / 2, 60, 16777215);
/* 162 */       drawCenteredString(this.fontRenderer, "Players Connected: " + (MinecraftServer.getServer().getCurrentPlayerCount() - 1), this.width / 2, 80, 16777215);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 167 */       drawCenteredString(this.fontRenderer, Translator.get("menu.title"), this.width / 2, 40, 16777215);
/*     */     } 
/*     */     
/* 170 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiIngameMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */