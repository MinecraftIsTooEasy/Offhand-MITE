/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiScreenAddServer
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiScreen parentGui;
/*     */   private GuiTextField serverAddress;
/*     */   private GuiTextField serverName;
/*     */   private ServerData newServerData;
/*     */   private GuiButton button_cancel;
/*     */   private String context;
/*     */   
/*     */   public GuiScreenAddServer(GuiScreen par1GuiScreen, ServerData par2ServerData, String context) {
/*  22 */     this.parentGui = par1GuiScreen;
/*  23 */     this.newServerData = par2ServerData;
/*     */     
/*  25 */     this.context = context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  33 */     this.serverName.updateCursorCounter();
/*  34 */     this.serverAddress.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  42 */     Keyboard.enableRepeatEvents(true);
/*  43 */     this.buttonList.clear();
/*  44 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, I18n.getString("addServer.add")));
/*     */     
/*  46 */     this.buttonList.add(this.button_cancel = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.cancel")));
/*     */     
/*  48 */     this.buttonList.add(new GuiButton(2, this.width / 2 - 100, 144, I18n.getString("addServer.hideAddress") + ": " + (this.newServerData.isHidingAddress() ? I18n.getString("gui.yes") : I18n.getString("gui.no"))));
/*  49 */     this.serverName = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 66, 200, 20);
/*  50 */     this.serverName.setFocused(true);
/*  51 */     this.serverName.setText(this.newServerData.serverName);
/*  52 */     this.serverAddress = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 106, 200, 20);
/*  53 */     this.serverAddress.setMaxStringLength(128);
/*  54 */     this.serverAddress.setText(this.newServerData.serverIP);
/*  55 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.serverAddress.getText().length() > 0 && (this.serverAddress.getText().split(":")).length > 0 && this.serverName.getText().length() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  63 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  71 */     if (par1GuiButton.enabled)
/*     */     {
/*  73 */       if (par1GuiButton.id == 1) {
/*     */         
/*  75 */         this.parentGui.confirmClicked(false, 0);
/*     */       }
/*  77 */       else if (par1GuiButton.id == 0) {
/*     */         
/*  79 */         this.newServerData.serverName = this.serverName.getText();
/*  80 */         this.newServerData.serverIP = this.serverAddress.getText();
/*  81 */         this.parentGui.confirmClicked(true, 0);
/*     */       }
/*  83 */       else if (par1GuiButton.id == 2) {
/*     */         
/*  85 */         this.newServerData.setHideAddress(!this.newServerData.isHidingAddress());
/*  86 */         ((GuiButton)this.buttonList.get(2)).displayString = I18n.getString("addServer.hideAddress") + ": " + (this.newServerData.isHidingAddress() ? I18n.getString("gui.yes") : I18n.getString("gui.no"));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/*  96 */     if (par2 == 1) {
/*     */       
/*  98 */       actionPerformed(this.button_cancel);
/*     */       
/*     */       return;
/*     */     } 
/* 102 */     this.serverName.textboxKeyTyped(par1, par2);
/* 103 */     this.serverAddress.textboxKeyTyped(par1, par2);
/*     */     
/* 105 */     if (par2 == 15) {
/*     */       
/* 107 */       this.serverName.setFocused(!this.serverName.isFocused());
/* 108 */       this.serverAddress.setFocused(!this.serverAddress.isFocused());
/*     */     } 
/*     */     
/* 111 */     if (par2 == 28 || par2 == 156)
/*     */     {
/* 113 */       actionPerformed(this.buttonList.get(0));
/*     */     }
/*     */     
/* 116 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.serverAddress.getText().length() > 0 && (this.serverAddress.getText().split(":")).length > 0 && this.serverName.getText().length() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/* 124 */     super.mouseClicked(par1, par2, par3);
/* 125 */     this.serverAddress.mouseClicked(par1, par2, par3);
/* 126 */     this.serverName.mouseClicked(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 134 */     drawDefaultBackground();
/*     */     
/* 136 */     drawCenteredString(this.fontRenderer, I18n.getString("addServer.title." + this.context), this.width / 2, 20, 16777215);
/* 137 */     drawString(this.fontRenderer, I18n.getString("addServer.enterName"), this.width / 2 - 100, 53, 10526880);
/* 138 */     drawString(this.fontRenderer, I18n.getString("addServer.enterIp"), this.width / 2 - 100, 94, 10526880);
/* 139 */     this.serverName.drawTextBox();
/* 140 */     this.serverAddress.drawTextBox();
/* 141 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenAddServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */