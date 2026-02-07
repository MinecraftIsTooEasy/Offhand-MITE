/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiScreenServerList
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen guiScreen;
/*     */   private final ServerData theServerData;
/*     */   private GuiTextField serverTextField;
/*     */   private GuiButton button_cancel;
/*     */   
/*     */   public GuiScreenServerList(GuiScreen par1GuiScreen, ServerData par2ServerData) {
/*  18 */     this.guiScreen = par1GuiScreen;
/*  19 */     this.theServerData = par2ServerData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  27 */     this.serverTextField.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  35 */     Keyboard.enableRepeatEvents(true);
/*  36 */     this.buttonList.clear();
/*  37 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, I18n.getString("selectServer.select")));
/*     */     
/*  39 */     this.buttonList.add(this.button_cancel = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.cancel")));
/*  40 */     this.serverTextField = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 116, 200, 20);
/*  41 */     this.serverTextField.setMaxStringLength(128);
/*  42 */     this.serverTextField.setFocused(true);
/*  43 */     this.serverTextField.setText(this.mc.gameSettings.lastServer);
/*  44 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.serverTextField.getText().length() > 0 && (this.serverTextField.getText().split(":")).length > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  52 */     Keyboard.enableRepeatEvents(false);
/*  53 */     this.mc.gameSettings.lastServer = this.serverTextField.getText();
/*  54 */     this.mc.gameSettings.saveOptions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  62 */     if (par1GuiButton.enabled)
/*     */     {
/*  64 */       if (par1GuiButton.id == 1) {
/*     */         
/*  66 */         this.guiScreen.confirmClicked(false, 0);
/*     */       }
/*  68 */       else if (par1GuiButton.id == 0) {
/*     */         
/*  70 */         this.theServerData.serverIP = this.serverTextField.getText();
/*  71 */         this.guiScreen.confirmClicked(true, 0);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/*  81 */     if (par2 == 1) {
/*     */       
/*  83 */       actionPerformed(this.button_cancel);
/*     */       
/*     */       return;
/*     */     } 
/*  87 */     if (this.serverTextField.textboxKeyTyped(par1, par2)) {
/*     */       
/*  89 */       ((GuiButton)this.buttonList.get(0)).enabled = (this.serverTextField.getText().length() > 0 && (this.serverTextField.getText().split(":")).length > 0);
/*     */     }
/*  91 */     else if (par2 == 28 || par2 == 156) {
/*     */       
/*  93 */       actionPerformed(this.buttonList.get(0));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/* 102 */     super.mouseClicked(par1, par2, par3);
/* 103 */     this.serverTextField.mouseClicked(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 111 */     drawDefaultBackground();
/* 112 */     drawCenteredString(this.fontRenderer, I18n.getString("selectServer.direct"), this.width / 2, 20, 16777215);
/* 113 */     drawString(this.fontRenderer, I18n.getString("addServer.enterIp"), this.width / 2 - 100, 100, 10526880);
/* 114 */     this.serverTextField.drawTextBox();
/* 115 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenServerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */