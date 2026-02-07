/*     */ package net.minecraft;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiSelectWorld
/*     */   extends GuiScreen
/*     */ {
/*  13 */   private final DateFormat dateFormatter = new SimpleDateFormat();
/*     */ 
/*     */ 
/*     */   
/*     */   protected GuiScreen parentScreen;
/*     */ 
/*     */ 
/*     */   
/*  21 */   protected String screenTitle = "Select world";
/*     */ 
/*     */   
/*     */   private boolean selected;
/*     */ 
/*     */   
/*     */   private int selectedWorld;
/*     */ 
/*     */   
/*     */   private List saveList;
/*     */ 
/*     */   
/*     */   private GuiWorldSlot worldSlotContainer;
/*     */ 
/*     */   
/*     */   private String localizedWorldText;
/*     */   
/*     */   private String localizedMustConvertText;
/*     */   
/*  40 */   private String[] localizedGameModeText = new String[3];
/*     */ 
/*     */   
/*     */   private boolean deleting;
/*     */ 
/*     */   
/*     */   private GuiButton buttonDelete;
/*     */ 
/*     */   
/*     */   private GuiButton buttonSelect;
/*     */ 
/*     */   
/*     */   private GuiButton button_create;
/*     */ 
/*     */   
/*     */   private GuiButton buttonRename;
/*     */   
/*     */   private GuiButton buttonRecreate;
/*     */ 
/*     */   
/*     */   public GuiSelectWorld(GuiScreen par1GuiScreen) {
/*  61 */     this.parentScreen = par1GuiScreen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  69 */     this.screenTitle = I18n.getString("selectWorld.title");
/*     */ 
/*     */     
/*     */     try {
/*  73 */       loadSaves();
/*     */     }
/*  75 */     catch (AnvilConverterException var2) {
/*     */       
/*  77 */       var2.printStackTrace();
/*  78 */       this.mc.displayGuiScreen(new GuiErrorScreen("Unable to load words", var2.getMessage()));
/*     */       
/*     */       return;
/*     */     } 
/*  82 */     this.localizedWorldText = I18n.getString("selectWorld.world");
/*  83 */     this.localizedMustConvertText = I18n.getString("selectWorld.conversion");
/*  84 */     this.localizedGameModeText[EnumGameType.SURVIVAL.getID()] = I18n.getString("gameMode.survival");
/*  85 */     this.localizedGameModeText[EnumGameType.CREATIVE.getID()] = I18n.getString("gameMode.creative");
/*  86 */     this.localizedGameModeText[EnumGameType.ADVENTURE.getID()] = I18n.getString("gameMode.adventure");
/*  87 */     this.worldSlotContainer = new GuiWorldSlot(this);
/*  88 */     this.worldSlotContainer.registerScrollButtons(4, 5);
/*  89 */     initButtons();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadSaves() throws AnvilConverterException {
/*  97 */     ISaveFormat var1 = this.mc.getSaveLoader();
/*  98 */     this.saveList = var1.getSaveList();
/*  99 */     Collections.sort(this.saveList);
/* 100 */     this.selectedWorld = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getSaveFileName(int par1) {
/* 108 */     return ((SaveFormatComparator)this.saveList.get(par1)).getFileName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getSaveName(int par1) {
/* 116 */     String var2 = ((SaveFormatComparator)this.saveList.get(par1)).getDisplayName();
/*     */     
/* 118 */     if (var2 == null || MathHelper.stringNullOrLengthZero(var2))
/*     */     {
/* 120 */       var2 = I18n.getString("selectWorld.world") + " " + (par1 + 1);
/*     */     }
/*     */     
/* 123 */     return var2;
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
/*     */ 
/*     */   
/*     */   public void initButtons() {
/* 140 */     this.buttonList.add(this.buttonSelect = new GuiButton(1, this.width / 2 - 154, this.height - 52, 152, 20, I18n.getString("selectWorld.select")));
/* 141 */     this.buttonList.add(this.button_create = new GuiButton(3, this.width / 2 + 2, this.height - 52, 152, 20, I18n.getString("selectWorld.create")));
/*     */     
/* 143 */     this.buttonList.add(this.buttonRename = new GuiButton(6, this.width / 2 - 154, this.height - 28, 74, 20, I18n.getString("selectWorld.rename")));
/* 144 */     this.buttonList.add(this.buttonDelete = new GuiButton(2, this.width / 2 - 76, this.height - 28, 74, 20, I18n.getString("selectWorld.delete")));
/* 145 */     this.buttonList.add(this.buttonRecreate = new GuiButton(7, this.width / 2 + 2, this.height - 28, 74, 20, I18n.getString("selectWorld.recreate")));
/* 146 */     this.buttonList.add(new GuiButton(0, this.width / 2 + 80, this.height - 28, 74, 20, I18n.getString("gui.cancel")));
/*     */ 
/*     */ 
/*     */     
/* 150 */     this.buttonSelect.enabled = false;
/* 151 */     this.buttonDelete.enabled = false;
/* 152 */     this.buttonRename.enabled = false;
/* 153 */     this.buttonRecreate.enabled = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/* 161 */     if (par1GuiButton.enabled)
/*     */     {
/* 163 */       if (par1GuiButton.id == 2) {
/*     */         
/* 165 */         String var2 = getSaveName(this.selectedWorld);
/*     */         
/* 167 */         if (var2 != null)
/*     */         {
/* 169 */           this.deleting = true;
/* 170 */           GuiYesNoMITE var3 = getDeleteWorldScreen(this, var2, this.selectedWorld);
/* 171 */           this.mc.displayGuiScreen(var3);
/*     */         }
/*     */       
/* 174 */       } else if (par1GuiButton.id == 1) {
/*     */         
/* 176 */         selectWorld(this.selectedWorld);
/*     */       }
/* 178 */       else if (par1GuiButton.id == 3) {
/*     */         
/* 180 */         this.mc.displayGuiScreen(new GuiCreateWorld(this));
/*     */       }
/* 182 */       else if (par1GuiButton.id == 6) {
/*     */         
/* 184 */         this.mc.displayGuiScreen(new GuiRenameWorld(this, getSaveFileName(this.selectedWorld)));
/*     */       }
/* 186 */       else if (par1GuiButton.id == 0) {
/*     */         
/* 188 */         this.mc.displayGuiScreen(this.parentScreen);
/*     */       }
/* 190 */       else if (par1GuiButton.id == 7) {
/*     */         
/* 192 */         GuiCreateWorld var5 = new GuiCreateWorld(this);
/* 193 */         ISaveHandler var6 = this.mc.getSaveLoader().getSaveLoader(getSaveFileName(this.selectedWorld), false);
/* 194 */         WorldInfo var4 = var6.loadWorldInfo();
/* 195 */         var6.flush();
/* 196 */         var5.func_82286_a(var4);
/* 197 */         this.mc.displayGuiScreen(var5);
/*     */       }
/*     */       else {
/*     */         
/* 201 */         this.worldSlotContainer.actionPerformed(par1GuiButton);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean selectedWorldPassedValidation() {
/* 208 */     return ((SaveFormatComparator)this.saveList.get(this.selectedWorld)).passed_validation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectWorld(int par1) {
/* 218 */     if (!this.selected) {
/*     */       
/* 220 */       this.selected = true;
/* 221 */       String var2 = getSaveFileName(par1);
/*     */       
/* 223 */       if (var2 == null)
/*     */       {
/* 225 */         var2 = "World" + par1;
/*     */       }
/*     */       
/* 228 */       String var3 = getSaveName(par1);
/*     */       
/* 230 */       if (var3 == null)
/*     */       {
/* 232 */         var3 = "World" + par1;
/*     */       }
/*     */ 
/*     */       
/* 236 */       if (selectedWorldPassedValidation() && this.mc.getSaveLoader().canLoadWorld(var2)) {
/*     */ 
/*     */         
/* 239 */         while (!ThreadedFileIOBase.isFinished()) {
/*     */ 
/*     */           
/*     */           try {
/* 243 */             Thread.sleep(500L);
/*     */           }
/* 245 */           catch (InterruptedException var5) {}
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 251 */         this.mc.displayGuiScreen((GuiScreen)null);
/*     */         
/* 253 */         this.mc.launchIntegratedServer(var2, var3, (WorldSettings)null);
/* 254 */         this.mc.statFileWriter.readStat(StatList.loadWorldStat, 1);
/* 255 */         this.mc.increment_loadWorldStat_asap = true;
/*     */       }
/*     */       else {
/*     */         
/* 259 */         this.selected = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean par1, int par2) {
/* 266 */     if (this.deleting) {
/*     */       
/* 268 */       this.deleting = false;
/*     */       
/* 270 */       if (par1) {
/*     */         
/* 272 */         ISaveFormat var3 = this.mc.getSaveLoader();
/* 273 */         var3.flushCache();
/* 274 */         var3.deleteWorldDirectory(getSaveFileName(par2));
/*     */ 
/*     */         
/*     */         try {
/* 278 */           loadSaves();
/*     */         }
/* 280 */         catch (AnvilConverterException var5) {
/*     */           
/* 282 */           var5.printStackTrace();
/*     */         } 
/*     */       } 
/*     */       
/* 286 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 295 */     Minecraft.clearWorldSessionClientData();
/*     */     
/* 297 */     this.worldSlotContainer.drawScreen(par1, par2, par3);
/* 298 */     drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
/* 299 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static GuiYesNoMITE getDeleteWorldScreen(GuiScreen par0GuiScreen, String par1Str, int par2) {
/* 307 */     String var3 = I18n.getString("selectWorld.deleteQuestion");
/* 308 */     String var4 = "'" + par1Str + "' " + I18n.getString("selectWorld.deleteWarning");
/* 309 */     String var5 = I18n.getString("selectWorld.deleteButton");
/* 310 */     String var6 = I18n.getString("gui.cancel");
/* 311 */     GuiYesNoMITE var7 = new GuiYesNoMITE(par0GuiScreen, var3, var4, var5, var6, par2);
/* 312 */     return var7;
/*     */   }
/*     */ 
/*     */   
/*     */   static List getSize(GuiSelectWorld par0GuiSelectWorld) {
/* 317 */     return par0GuiSelectWorld.saveList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int onElementSelected(GuiSelectWorld par0GuiSelectWorld, int par1) {
/* 325 */     return par0GuiSelectWorld.selectedWorld = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getSelectedWorld(GuiSelectWorld par0GuiSelectWorld) {
/* 333 */     return par0GuiSelectWorld.selectedWorld;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static GuiButton getSelectButton(GuiSelectWorld par0GuiSelectWorld) {
/* 341 */     return par0GuiSelectWorld.buttonSelect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static GuiButton getRenameButton(GuiSelectWorld par0GuiSelectWorld) {
/* 349 */     return par0GuiSelectWorld.buttonDelete;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static GuiButton getDeleteButton(GuiSelectWorld par0GuiSelectWorld) {
/* 357 */     return par0GuiSelectWorld.buttonRename;
/*     */   }
/*     */ 
/*     */   
/*     */   static GuiButton func_82312_f(GuiSelectWorld par0GuiSelectWorld) {
/* 362 */     return par0GuiSelectWorld.buttonRecreate;
/*     */   }
/*     */ 
/*     */   
/*     */   static String func_82313_g(GuiSelectWorld par0GuiSelectWorld) {
/* 367 */     return par0GuiSelectWorld.localizedWorldText;
/*     */   }
/*     */ 
/*     */   
/*     */   static DateFormat func_82315_h(GuiSelectWorld par0GuiSelectWorld) {
/* 372 */     return par0GuiSelectWorld.dateFormatter;
/*     */   }
/*     */ 
/*     */   
/*     */   static String func_82311_i(GuiSelectWorld par0GuiSelectWorld) {
/* 377 */     return par0GuiSelectWorld.localizedMustConvertText;
/*     */   }
/*     */ 
/*     */   
/*     */   static String[] func_82314_j(GuiSelectWorld par0GuiSelectWorld) {
/* 382 */     return par0GuiSelectWorld.localizedGameModeText;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 388 */     if (par2 == 1 && this.selectedWorld >= 0) {
/*     */       
/* 390 */       this.selected = false;
/* 391 */       this.selectedWorld = -1;
/*     */       
/* 393 */       (getSelectButton(this)).enabled = false;
/* 394 */       (getRenameButton(this)).enabled = false;
/* 395 */       (getDeleteButton(this)).enabled = false;
/* 396 */       (func_82312_f(this)).enabled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 403 */     else if (par2 == 28) {
/*     */       
/* 405 */       if (this.selectedWorld < 0) {
/* 406 */         actionPerformed(this.button_create);
/*     */       } else {
/* 408 */         actionPerformed(this.buttonSelect);
/*     */       } 
/* 410 */     } else if (par2 == 211 && this.buttonDelete.isClickable()) {
/*     */       
/* 412 */       actionPerformed(this.buttonDelete);
/*     */     }
/*     */     else {
/*     */       
/* 416 */       super.keyTyped(par1, par2);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSelectWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */