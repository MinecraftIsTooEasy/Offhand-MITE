/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiControls
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiScreen parentScreen;
/*  11 */   protected String screenTitle = "Controls";
/*     */ 
/*     */   
/*     */   private GameSettings options;
/*     */ 
/*     */   
/*  17 */   private int buttonId = -1;
/*     */   
/*     */   private int page_index;
/*     */ 
/*     */   
/*     */   public GuiControls(GuiScreen par1GuiScreen, GameSettings par2GameSettings) {
/*  23 */     this.parentScreen = par1GuiScreen;
/*  24 */     this.options = par2GameSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getLeftBorder() {
/*  32 */     return this.width / 2 - 155;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getKeybindButtonPosX(int index) {
/*  37 */     index %= 10;
/*  38 */     return getLeftBorder() + index % 2 * 160;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getKeybindButtonPosY(int index) {
/*  43 */     index %= 10;
/*  44 */     return this.height / 6 + 24 * index / 2 + 6;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  65 */     int left_border = getLeftBorder();
/*     */     
/*  67 */     for (int id = 0; id < this.options.keyBindings.length; id++) {
/*  68 */       this.buttonList.add(new GuiSmallButton(id, getKeybindButtonPosX(id), getKeybindButtonPosY(id), 70, 20, this.options.getOptionDisplayString(id)));
/*     */     }
/*  70 */     setKeybindButtonVisibilities();
/*     */     
/*  72 */     this.buttonList.add(new GuiButton(201, this.width / 2 - 100, this.height / 6 + 168 - 24, I18n.getString("gui.nextPage")));
/*  73 */     this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
/*  74 */     this.screenTitle = I18n.getString("controls.title");
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isKeybindButtonVisible(int index) {
/*  79 */     return (index >= this.page_index * 10 && index < (this.page_index + 1) * 10);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setKeybindButtonVisibilities() {
/*  84 */     for (int i = 0; i < this.options.keyBindings.length; i++) {
/*  85 */       ((GuiButton)this.buttonList.get(i)).drawButton = isKeybindButtonVisible(i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  93 */     for (int var2 = 0; var2 < this.options.keyBindings.length; var2++)
/*     */     {
/*  95 */       ((GuiButton)this.buttonList.get(var2)).displayString = this.options.getOptionDisplayString(var2);
/*     */     }
/*     */     
/*  98 */     if (par1GuiButton.id == 200) {
/*     */       
/* 100 */       this.mc.displayGuiScreen(this.parentScreen);
/*     */     }
/* 102 */     else if (par1GuiButton.id == 201) {
/*     */       
/* 104 */       if (++this.page_index > (this.options.keyBindings.length - 1) / 10) {
/* 105 */         this.page_index = 0;
/*     */       }
/* 107 */       setKeybindButtonVisibilities();
/*     */     }
/*     */     else {
/*     */       
/* 111 */       this.buttonId = par1GuiButton.id;
/* 112 */       par1GuiButton.displayString = "> " + this.options.getOptionDisplayString(par1GuiButton.id) + " <";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/* 121 */     if (this.buttonId >= 0) {
/*     */       
/* 123 */       this.options.setKeyBinding(this.buttonId, -100 + par3);
/* 124 */       ((GuiButton)this.buttonList.get(this.buttonId)).displayString = this.options.getOptionDisplayString(this.buttonId);
/* 125 */       this.buttonId = -1;
/* 126 */       KeyBinding.resetKeyBindingArrayAndHash();
/*     */     }
/*     */     else {
/*     */       
/* 130 */       super.mouseClicked(par1, par2, par3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 139 */     if (this.buttonId >= 0) {
/*     */       
/* 141 */       this.options.setKeyBinding(this.buttonId, par2);
/* 142 */       ((GuiButton)this.buttonList.get(this.buttonId)).displayString = this.options.getOptionDisplayString(this.buttonId);
/* 143 */       this.buttonId = -1;
/* 144 */       KeyBinding.resetKeyBindingArrayAndHash();
/*     */     }
/*     */     else {
/*     */       
/* 148 */       super.keyTyped(par1, par2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 157 */     drawDefaultBackground();
/* 158 */     drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
/* 159 */     int var4 = getLeftBorder();
/* 160 */     int var5 = 0;
/*     */     
/* 162 */     while (var5 < this.options.keyBindings.length) {
/*     */       
/* 164 */       if (!isKeybindButtonVisible(var5)) {
/*     */         
/* 166 */         var5++;
/*     */         
/*     */         continue;
/*     */       } 
/* 170 */       boolean var6 = false;
/* 171 */       int var7 = 0;
/*     */ 
/*     */ 
/*     */       
/* 175 */       while (var7 < this.options.keyBindings.length) {
/*     */         
/* 177 */         if (var7 == var5 || (this.options.keyBindings[var5]).keyCode != (this.options.keyBindings[var7]).keyCode) {
/*     */           
/* 179 */           var7++;
/*     */           
/*     */           continue;
/*     */         } 
/* 183 */         var6 = true;
/*     */       } 
/*     */       
/* 186 */       if (this.buttonId == var5) {
/*     */         
/* 188 */         ((GuiButton)this.buttonList.get(var5)).displayString = "" + EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + "??? " + EnumChatFormatting.WHITE + "<";
/*     */       }
/* 190 */       else if (var6) {
/*     */         
/* 192 */         ((GuiButton)this.buttonList.get(var5)).displayString = EnumChatFormatting.RED + this.options.getOptionDisplayString(var5);
/*     */       }
/*     */       else {
/*     */         
/* 196 */         ((GuiButton)this.buttonList.get(var5)).displayString = this.options.getOptionDisplayString(var5);
/*     */       } 
/*     */ 
/*     */       
/* 200 */       drawString(this.fontRenderer, this.options.getKeyBindingDescription(var5), getKeybindButtonPosX(var5) + 70 + 6, getKeybindButtonPosY(var5) + 7, -1);
/* 201 */       var5++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 206 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiControls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */