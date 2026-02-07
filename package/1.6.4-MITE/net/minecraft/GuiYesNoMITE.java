/*     */ package net.minecraft;
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
/*     */ public class GuiYesNoMITE
/*     */   extends GuiScreen
/*     */ {
/*     */   protected GuiScreen parentScreen;
/*     */   protected String message1;
/*     */   private String message2;
/*     */   protected String buttonText1;
/*     */   protected String buttonText2;
/*     */   protected int worldNumber;
/*     */   GuiSmallButton button_yes;
/*     */   GuiSmallButton button_no;
/*     */   
/*     */   public GuiYesNoMITE(GuiScreen par1GuiScreen, String par2Str, String par3Str, int par4) {
/*  31 */     this.parentScreen = par1GuiScreen;
/*  32 */     this.message1 = par2Str;
/*  33 */     this.message2 = par3Str;
/*  34 */     this.worldNumber = par4;
/*  35 */     this.buttonText1 = I18n.getString("gui.yes");
/*  36 */     this.buttonText2 = I18n.getString("gui.no");
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiYesNoMITE(GuiScreen par1GuiScreen, String par2Str, String par3Str, String par4Str, String par5Str, int par6) {
/*  41 */     this.parentScreen = par1GuiScreen;
/*  42 */     this.message1 = par2Str;
/*  43 */     this.message2 = par3Str;
/*  44 */     this.buttonText1 = par4Str;
/*  45 */     this.buttonText2 = par5Str;
/*  46 */     this.worldNumber = par6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  57 */     this.buttonList.add(this.button_yes = new GuiSmallButton(0, this.width / 2 - 155, this.height / 6 + 96, this.buttonText1));
/*  58 */     this.buttonList.add(this.button_no = new GuiSmallButton(1, this.width / 2 - 155 + 160, this.height / 6 + 96, this.buttonText2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  66 */     this.parentScreen.confirmClicked((par1GuiButton.id == 0), this.worldNumber);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getMessage1YPos() {
/*  71 */     return this.height / 2 - 50;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getMessage2YPos() {
/*  76 */     return getMessage1YPos() + 20;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean showMessage2() {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/*  89 */     drawDefaultBackground();
/*     */ 
/*     */     
/*  92 */     drawCenteredString(this.fontRenderer, this.message1, this.width / 2, getMessage1YPos(), 16777215);
/*     */     
/*  94 */     if (showMessage2()) {
/*  95 */       drawCenteredString(this.fontRenderer, this.message2, this.width / 2, getMessage2YPos(), 16777215);
/*     */     }
/*  97 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 102 */     if (par2 == 1) {
/* 103 */       actionPerformed(this.button_no);
/*     */     } else {
/* 105 */       super.keyTyped(par1, par2);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiYesNoMITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */