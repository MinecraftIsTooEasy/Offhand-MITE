/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.FileWriter;
/*     */ import java.util.Date;
/*     */ import java.util.Random;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiCreateWorld
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiScreen parentGuiScreen;
/*     */   private GuiTextField textboxWorldName;
/*     */   private GuiTextField textboxSeed;
/*     */   private String folderName;
/*  18 */   private String gameMode = "survival";
/*     */ 
/*     */   
/*     */   private boolean generateStructures = true;
/*     */ 
/*     */   
/*     */   private boolean commandsAllowed;
/*     */ 
/*     */   
/*     */   private boolean commandsToggled;
/*     */ 
/*     */   
/*     */   private boolean bonusItems;
/*     */ 
/*     */   
/*     */   private boolean isHardcore;
/*     */ 
/*     */   
/*     */   private boolean createClicked;
/*     */ 
/*     */   
/*     */   private boolean moreOptions;
/*     */ 
/*     */   
/*     */   private GuiButton buttonGameMode;
/*     */ 
/*     */   
/*     */   private GuiButton moreWorldOptions;
/*     */ 
/*     */   
/*     */   private GuiButton buttonGenerateStructures;
/*     */ 
/*     */   
/*     */   private GuiButton buttonBonusItems;
/*     */ 
/*     */   
/*     */   private GuiButton buttonWorldType;
/*     */   
/*     */   private GuiButton buttonAllowCommands;
/*     */   
/*     */   private GuiButton buttonCustomize;
/*     */   
/*     */   private String gameModeDescriptionLine1;
/*     */   
/*     */   private String gameModeDescriptionLine2;
/*     */   
/*     */   private String seed;
/*     */   
/*     */   private String localizedNewWorldText;
/*     */   
/*  68 */   private int worldTypeId = 2;
/*     */ 
/*     */   
/*  71 */   public String generatorOptionsToUse = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private static final String[] ILLEGAL_WORLD_NAMES = new String[] { "CON", "COM", "PRN", "AUX", "CLOCK$", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9" };
/*     */   
/*     */   private GuiButton button_cancel;
/*     */   
/*     */   private GuiButton button_skills;
/*     */   
/*     */   private boolean are_skills_enabled;
/*     */   
/*     */   public GuiCreateWorld(GuiScreen par1GuiScreen) {
/*  85 */     this.parentGuiScreen = par1GuiScreen;
/*  86 */     this.seed = "";
/*  87 */     this.localizedNewWorldText = I18n.getString("selectWorld.newWorld");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  95 */     this.textboxWorldName.updateCursorCounter();
/*  96 */     this.textboxSeed.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/* 104 */     Keyboard.enableRepeatEvents(true);
/* 105 */     this.buttonList.clear();
/*     */     
/* 107 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 154, this.height - 28, 152, 20, I18n.getString("selectWorld.create")));
/*     */     
/* 109 */     this.buttonList.add(this.button_cancel = new GuiButton(1, this.width / 2 + 2, this.height - 28, 152, 20, I18n.getString("gui.cancel")));
/*     */     
/* 111 */     this.buttonList.add(this.buttonGameMode = new GuiButton(2, this.width / 2 - 74, 114, 152, 20, I18n.getString("selectWorld.gameMode")));
/*     */     
/* 113 */     this.buttonList.add(this.moreWorldOptions = new GuiButton(3, this.width / 2 - 74, this.height - 52, 152, 20, I18n.getString("selectWorld.moreWorldOptions")));
/*     */     
/* 115 */     this.buttonList.add(this.buttonGenerateStructures = new GuiButton(4, this.width / 2 - 154, 114, 152, 20, I18n.getString("selectWorld.mapFeatures")));
/* 116 */     this.buttonGenerateStructures.drawButton = false;
/*     */     
/* 118 */     this.buttonList.add(this.buttonBonusItems = new GuiButton(7, this.width / 2 + 2, 150, 152, 20, I18n.getString("selectWorld.bonusItems")));
/* 119 */     this.buttonBonusItems.drawButton = false;
/*     */     
/* 121 */     this.buttonList.add(this.buttonWorldType = new GuiButton(5, this.width / 2 + 2, 114, 152, 20, I18n.getString("selectWorld.mapType")));
/* 122 */     this.buttonWorldType.drawButton = false;
/*     */     
/* 124 */     this.buttonList.add(this.buttonAllowCommands = new GuiButton(6, this.width / 2 - 154, 150, 152, 20, I18n.getString("selectWorld.allowCommands")));
/* 125 */     this.buttonAllowCommands.drawButton = false;
/*     */     
/* 127 */     this.buttonList.add(this.button_skills = new GuiButton(9, this.width / 2 + 2, 114, 152, 20, I18n.getString("selectWorld.professions")));
/* 128 */     this.button_skills.drawButton = false;
/*     */     
/* 130 */     this.buttonWorldType.enabled = false;
/* 131 */     this.buttonAllowCommands.enabled = false;
/* 132 */     this.buttonBonusItems.enabled = false;
/*     */     
/* 134 */     this.buttonList.add(this.buttonCustomize = new GuiButton(8, this.width / 2 + 5, 120, 150, 20, I18n.getString("selectWorld.customizeType")));
/* 135 */     this.buttonCustomize.drawButton = false;
/* 136 */     this.textboxWorldName = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
/* 137 */     this.textboxWorldName.setFocused(true);
/* 138 */     this.textboxWorldName.setText(this.localizedNewWorldText);
/* 139 */     this.textboxSeed = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
/* 140 */     this.textboxSeed.setText(this.seed);
/* 141 */     func_82288_a(this.moreOptions);
/* 142 */     makeUseableName();
/* 143 */     updateButtonText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void makeUseableName() {
/* 152 */     this.folderName = this.textboxWorldName.getText().trim();
/* 153 */     char[] var1 = ChatAllowedCharacters.allowedCharactersArray;
/* 154 */     int var2 = var1.length;
/*     */     
/* 156 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 158 */       char var4 = var1[var3];
/* 159 */       this.folderName = this.folderName.replace(var4, '_');
/*     */     } 
/*     */     
/* 162 */     if (MathHelper.stringNullOrLengthZero(this.folderName))
/*     */     {
/* 164 */       this.folderName = "World";
/*     */     }
/*     */     
/* 167 */     this.folderName = func_73913_a(this.mc.getSaveLoader(), this.folderName);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateButtonText() {
/* 172 */     this.buttonGameMode.displayString = I18n.getString("selectWorld.gameMode") + " " + I18n.getString("selectWorld.gameMode." + this.gameMode);
/* 173 */     this.gameModeDescriptionLine1 = I18n.getString("selectWorld.gameMode." + this.gameMode + ".line1");
/* 174 */     this.gameModeDescriptionLine2 = I18n.getString("selectWorld.gameMode." + this.gameMode + ".line2");
/* 175 */     this.buttonGenerateStructures.displayString = I18n.getString("selectWorld.mapFeatures") + " ";
/*     */     
/* 177 */     if (this.generateStructures) {
/*     */       
/* 179 */       this.buttonGenerateStructures.displayString += I18n.getString("options.on");
/*     */     }
/*     */     else {
/*     */       
/* 183 */       this.buttonGenerateStructures.displayString += I18n.getString("options.off");
/*     */     } 
/*     */     
/* 186 */     this.buttonBonusItems.displayString = I18n.getString("selectWorld.bonusItems") + " ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     this.buttonBonusItems.displayString += I18n.getString("options.off");
/*     */ 
/*     */     
/* 197 */     this.buttonWorldType.displayString = I18n.getString("selectWorld.mapType") + " " + I18n.getString(WorldType.worldTypes[this.worldTypeId].getTranslateName());
/* 198 */     this.buttonAllowCommands.displayString = I18n.getString("selectWorld.allowCommands") + " ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     this.buttonAllowCommands.displayString += I18n.getString("options.off");
/*     */ 
/*     */     
/* 209 */     this.button_skills.displayString = I18n.getString("selectWorld.professions") + " " + I18n.getString(this.are_skills_enabled ? "options.enabled" : "options.disabled");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_73913_a(ISaveFormat par0ISaveFormat, String par1Str) {
/* 214 */     par1Str = par1Str.replaceAll("[\\./\"]", "_");
/* 215 */     String[] var2 = ILLEGAL_WORLD_NAMES;
/* 216 */     int var3 = var2.length;
/*     */     
/* 218 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 220 */       String var5 = var2[var4];
/*     */       
/* 222 */       if (par1Str.equalsIgnoreCase(var5))
/*     */       {
/* 224 */         par1Str = "_" + par1Str + "_";
/*     */       }
/*     */     } 
/*     */     
/* 228 */     while (par0ISaveFormat.getWorldInfo(par1Str) != null)
/*     */     {
/* 230 */       par1Str = par1Str + "-";
/*     */     }
/*     */     
/* 233 */     return par1Str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 241 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/* 249 */     if (par1GuiButton.enabled)
/*     */     {
/* 251 */       if (par1GuiButton.id == 1) {
/*     */         
/* 253 */         this.mc.displayGuiScreen(this.parentGuiScreen);
/*     */       }
/* 255 */       else if (par1GuiButton.id == 0) {
/*     */         
/* 257 */         this.mc.displayGuiScreen((GuiScreen)null);
/*     */         
/* 259 */         if (this.createClicked) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 264 */         this.createClicked = true;
/* 265 */         long var2 = (new Random()).nextLong();
/* 266 */         String var4 = this.textboxSeed.getText();
/*     */         
/* 268 */         if (!MathHelper.stringNullOrLengthZero(var4)) {
/*     */           
/*     */           try {
/*     */             
/* 272 */             long var5 = Long.parseLong(var4);
/*     */             
/* 274 */             if (var5 != 0L)
/*     */             {
/* 276 */               var2 = var5;
/*     */             }
/*     */           }
/* 279 */           catch (NumberFormatException var7) {
/*     */             
/* 281 */             var2 = var4.hashCode();
/*     */           } 
/*     */         }
/*     */         
/* 285 */         EnumGameType var8 = EnumGameType.getByName(this.gameMode);
/*     */         
/* 287 */         WorldSettings var6 = new WorldSettings(var2, var8, this.generateStructures, this.isHardcore, WorldType.worldTypes[this.worldTypeId], this.are_skills_enabled);
/* 288 */         var6.func_82750_a(this.generatorOptionsToUse);
/*     */         
/* 290 */         if (this.bonusItems && !this.isHardcore)
/*     */         {
/* 292 */           var6.enableBonusChest();
/*     */         }
/*     */         
/* 295 */         if (this.commandsAllowed && !this.isHardcore)
/*     */         {
/* 297 */           var6.enableCommands();
/*     */         }
/*     */         
/* 300 */         this.mc.launchIntegratedServer(this.folderName, this.textboxWorldName.getText().trim(), var6);
/* 301 */         this.mc.statFileWriter.readStat(StatList.createWorldStat, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 312 */           FileWriter fw = new FileWriter("MITE/world_seeds.txt", true);
/*     */           
/* 314 */           StringBuffer sb = new StringBuffer();
/*     */           
/* 316 */           sb.append(this.textboxWorldName.getText().trim());
/* 317 */           sb.append(": ");
/* 318 */           sb.append(var6.getSeed());
/* 319 */           sb.append(" (");
/* 320 */           sb.append(new Date());
/* 321 */           sb.append(")" + MITEConstant.newline);
/*     */           
/* 323 */           fw.append(sb.toString());
/* 324 */           fw.close();
/*     */         
/*     */         }
/* 327 */         catch (Exception e) {}
/*     */       }
/* 329 */       else if (par1GuiButton.id == 3) {
/*     */         
/* 331 */         func_82287_i();
/*     */       }
/* 333 */       else if (par1GuiButton.id == 2) {
/*     */         
/* 335 */         if (this.gameMode.equals("survival")) {
/*     */           
/* 337 */           if (!this.commandsToggled)
/*     */           {
/* 339 */             this.commandsAllowed = false;
/*     */           }
/*     */           
/* 342 */           this.isHardcore = false;
/* 343 */           this.gameMode = "hardcore";
/* 344 */           this.isHardcore = true;
/* 345 */           this.buttonAllowCommands.enabled = false;
/* 346 */           this.buttonBonusItems.enabled = false;
/* 347 */           updateButtonText();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 365 */           if (!this.commandsToggled)
/*     */           {
/* 367 */             this.commandsAllowed = false;
/*     */           }
/*     */           
/* 370 */           this.gameMode = "survival";
/* 371 */           updateButtonText();
/*     */ 
/*     */           
/* 374 */           this.isHardcore = false;
/*     */         } 
/*     */         
/* 377 */         updateButtonText();
/*     */       }
/* 379 */       else if (par1GuiButton.id == 4) {
/*     */         
/* 381 */         this.generateStructures = !this.generateStructures;
/* 382 */         updateButtonText();
/*     */       }
/* 384 */       else if (par1GuiButton.id == 7) {
/*     */ 
/*     */         
/* 387 */         this.bonusItems = false;
/* 388 */         updateButtonText();
/*     */       }
/* 390 */       else if (par1GuiButton.id != 5) {
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 413 */         if (par1GuiButton.id == 6) {
/*     */           
/* 415 */           this.commandsToggled = true;
/*     */           
/* 417 */           this.commandsAllowed = false;
/* 418 */           updateButtonText();
/*     */         }
/* 420 */         else if (par1GuiButton.id == 8) {
/*     */           
/* 422 */           this.mc.displayGuiScreen(new GuiCreateFlatWorld(this, this.generatorOptionsToUse));
/*     */         }
/* 424 */         else if (par1GuiButton == this.button_skills) {
/*     */           
/* 426 */           this.are_skills_enabled = !this.are_skills_enabled;
/* 427 */           updateButtonText();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void func_82287_i() {
/* 434 */     func_82288_a(!this.moreOptions);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_82288_a(boolean par1) {
/* 439 */     this.moreOptions = par1;
/* 440 */     this.buttonGameMode.drawButton = !this.moreOptions;
/* 441 */     this.buttonGenerateStructures.drawButton = this.moreOptions;
/* 442 */     this.buttonBonusItems.drawButton = this.moreOptions;
/*     */     
/* 444 */     this.buttonAllowCommands.drawButton = this.moreOptions;
/* 445 */     this.button_skills.drawButton = this.moreOptions;
/*     */ 
/*     */ 
/*     */     
/* 449 */     this.buttonWorldType.drawButton = this.moreOptions;
/* 450 */     this.button_skills.drawButton = false;
/*     */ 
/*     */     
/* 453 */     this.buttonCustomize.drawButton = (this.moreOptions && WorldType.worldTypes[this.worldTypeId] == WorldType.FLAT);
/*     */     
/* 455 */     if (this.moreOptions) {
/*     */ 
/*     */       
/* 458 */       this.moreWorldOptions.displayString = "Back";
/* 459 */       this.textboxSeed.setFocused(true);
/*     */     }
/*     */     else {
/*     */       
/* 463 */       this.moreWorldOptions.displayString = I18n.getString("selectWorld.moreWorldOptions");
/* 464 */       this.textboxWorldName.setFocused(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 473 */     if (par2 == 1) {
/*     */       
/* 475 */       if (this.moreOptions) {
/* 476 */         actionPerformed(this.moreWorldOptions);
/*     */       } else {
/* 478 */         actionPerformed(this.button_cancel);
/*     */       } 
/* 480 */     } else if (par2 == 15) {
/*     */ 
/*     */       
/* 483 */       actionPerformed(this.moreWorldOptions);
/*     */       
/*     */       return;
/*     */     } 
/* 487 */     if (this.textboxWorldName.isFocused() && !this.moreOptions) {
/*     */       
/* 489 */       this.textboxWorldName.textboxKeyTyped(par1, par2);
/* 490 */       this.localizedNewWorldText = this.textboxWorldName.getText();
/*     */     }
/* 492 */     else if (this.textboxSeed.isFocused() && this.moreOptions) {
/*     */       
/* 494 */       this.textboxSeed.textboxKeyTyped(par1, par2);
/* 495 */       this.seed = this.textboxSeed.getText();
/*     */     } 
/*     */     
/* 498 */     if (par2 == 28 || par2 == 156)
/*     */     {
/* 500 */       actionPerformed(this.buttonList.get(0));
/*     */     }
/*     */     
/* 503 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.textboxWorldName.getText().length() > 0);
/* 504 */     makeUseableName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/* 512 */     super.mouseClicked(par1, par2, par3);
/*     */     
/* 514 */     if (this.moreOptions) {
/*     */       
/* 516 */       this.textboxSeed.mouseClicked(par1, par2, par3);
/*     */     }
/*     */     else {
/*     */       
/* 520 */       this.textboxWorldName.mouseClicked(par1, par2, par3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 529 */     drawDefaultBackground();
/* 530 */     drawCenteredString(this.fontRenderer, I18n.getString("selectWorld.create"), this.width / 2, 20, 16777215);
/*     */     
/* 532 */     if (this.moreOptions) {
/*     */       
/* 534 */       drawString(this.fontRenderer, I18n.getString("selectWorld.enterSeed"), this.width / 2 - 100, 47, 10526880);
/* 535 */       drawString(this.fontRenderer, I18n.getString("selectWorld.seedInfo"), this.width / 2 - 100, 85, 10526880);
/*     */       
/* 537 */       drawString(this.fontRenderer, I18n.getString("selectWorld.mapFeatures.info"), this.width / 2 - 150, 136, 10526880);
/* 538 */       drawString(this.fontRenderer, I18n.getString("selectWorld.allowCommands.info"), this.width / 2 - 150, 172, 10526880);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 543 */       this.textboxSeed.drawTextBox();
/*     */     }
/*     */     else {
/*     */       
/* 547 */       drawString(this.fontRenderer, I18n.getString("selectWorld.enterName"), this.width / 2 - 100, 47, 10526880);
/* 548 */       drawString(this.fontRenderer, I18n.getString("selectWorld.resultFolder") + " " + this.folderName, this.width / 2 - 100, 85, 10526880);
/* 549 */       this.textboxWorldName.drawTextBox();
/*     */ 
/*     */       
/* 552 */       drawString(this.fontRenderer, this.gameModeDescriptionLine1, this.width / 2 - 100, 149, 10526880);
/* 553 */       drawString(this.fontRenderer, this.gameModeDescriptionLine2, this.width / 2 - 100, 161, 10526880);
/*     */     } 
/*     */     
/* 556 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82286_a(WorldInfo par1WorldInfo) {
/* 561 */     this.localizedNewWorldText = I18n.getStringParams("selectWorld.newWorld.copyOf", new Object[] { par1WorldInfo.getWorldName() });
/* 562 */     this.seed = par1WorldInfo.getSeed() + "";
/* 563 */     this.worldTypeId = par1WorldInfo.getTerrainType().getWorldTypeID();
/* 564 */     this.generatorOptionsToUse = par1WorldInfo.getGeneratorOptions();
/* 565 */     this.generateStructures = par1WorldInfo.isMapFeaturesEnabled();
/* 566 */     this.commandsAllowed = par1WorldInfo.areCommandsAllowed();
/*     */     
/* 568 */     if (par1WorldInfo.isHardcoreModeEnabled()) {
/*     */       
/* 570 */       this.gameMode = "hardcore";
/*     */     }
/* 572 */     else if (par1WorldInfo.getGameType().isSurvivalOrAdventure()) {
/*     */       
/* 574 */       this.gameMode = "survival";
/*     */     }
/* 576 */     else if (par1WorldInfo.getGameType().isCreative()) {
/*     */       
/* 578 */       this.gameMode = "creative";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiCreateWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */