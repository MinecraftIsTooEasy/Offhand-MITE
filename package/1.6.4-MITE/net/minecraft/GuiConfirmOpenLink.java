/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class GuiConfirmOpenLink
/*     */   extends GuiYesNoMITE
/*     */ {
/*     */   private String openLinkWarning;
/*     */   private String copyLinkButtonText;
/*     */   private String field_92028_p;
/*     */   private boolean field_92027_q = true;
/*     */   private EnumSpecialSplash enum_special_splash;
/*     */   
/*     */   public GuiConfirmOpenLink(GuiScreen par1GuiScreen, String par2Str, int par3, boolean par4) {
/*  26 */     this(par1GuiScreen, par2Str, par3, par4, (EnumSpecialSplash)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiConfirmOpenLink(GuiScreen par1GuiScreen, String par2Str, int par3, boolean par4, EnumSpecialSplash enum_special_splash) {
/*  34 */     super(par1GuiScreen, (enum_special_splash == null) ? I18n.getString(par4 ? "chat.link.confirmTrusted" : "chat.link.confirm") : enum_special_splash.getMessageText(), par2Str, par3);
/*  35 */     this.buttonText1 = I18n.getString(par4 ? "chat.link.open" : "gui.yes");
/*  36 */     this.buttonText2 = I18n.getString(par4 ? "gui.cancel" : "gui.no");
/*  37 */     this.copyLinkButtonText = I18n.getString("chat.copy");
/*  38 */     this.openLinkWarning = I18n.getString("chat.link.warning");
/*  39 */     this.field_92028_p = par2Str;
/*  40 */     this.enum_special_splash = enum_special_splash;
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiConfirmOpenLink(GuiScreen gui_screen, EnumSpecialSplash enum_special_splash) {
/*  45 */     this(gui_screen, enum_special_splash.getURL(), 15 + enum_special_splash.ordinal(), true, enum_special_splash);
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
/*  75 */     if (this.enum_special_splash != null) {
/*     */       
/*  77 */       this.buttonList.add(new GuiButton(0, this.width / 2 - 154 + 0, this.height / 2 + 66, 100, 20, this.buttonText1));
/*  78 */       this.buttonList.add(new GuiButton(2, this.width / 2 - 154 + 105, this.height / 2 + 66, 100, 20, this.copyLinkButtonText));
/*  79 */       this.buttonList.add(new GuiButton(1, this.width / 2 - 154 + 210, this.height / 2 + 66, 100, 20, this.buttonText2));
/*     */     }
/*     */     else {
/*     */       
/*  83 */       this.buttonList.add(new GuiButton(0, this.width / 2 - 154 + 0, this.height / 2 + 16, 100, 20, this.buttonText1));
/*  84 */       this.buttonList.add(new GuiButton(2, this.width / 2 - 154 + 105, this.height / 2 + 16, 100, 20, this.copyLinkButtonText));
/*  85 */       this.buttonList.add(new GuiButton(1, this.width / 2 - 154 + 210, this.height / 2 + 16, 100, 20, this.buttonText2));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  94 */     if (par1GuiButton == null) {
/*     */       return;
/*     */     }
/*  97 */     if (par1GuiButton.id == 2)
/*     */     {
/*  99 */       copyLinkToClipboard();
/*     */     }
/*     */     
/* 102 */     this.parentScreen.confirmClicked((par1GuiButton.id == 0), this.worldNumber);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 107 */     if (par2 == 1) {
/*     */       
/* 109 */       this.mc.displayGuiScreen(this.parentScreen);
/*     */       
/*     */       return;
/*     */     } 
/* 113 */     super.keyTyped(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyLinkToClipboard() {
/* 121 */     setClipboardString(this.field_92028_p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 129 */     super.drawScreen(par1, par2, par3);
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
/* 209 */     if (this.enum_special_splash != null && this.enum_special_splash.hasLinkPageTexture()) {
/*     */       
/* 211 */       GL11.glEnable(3042);
/*     */       
/* 213 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 214 */       this.mc.getTextureManager().bindTexture(this.enum_special_splash.getLinkPageTexture());
/*     */       
/* 216 */       int image_width = MathHelper.floor_double((this.enum_special_splash.getWidth() * this.enum_special_splash.getScale()));
/* 217 */       int image_height = MathHelper.floor_double((this.enum_special_splash.getHeight() * this.enum_special_splash.getScale()));
/*     */       
/* 219 */       GL11.glTexParameteri(3553, 10241, 9729);
/* 220 */       GL11.glTexParameteri(3553, 10240, 9729);
/* 221 */       drawTexturedModalRect2(this.width / 2 - image_width / 2, this.height / 2 - image_height / 2 + 10 - 10, image_width, image_height);
/* 222 */       GL11.glTexParameteri(3553, 10241, 9728);
/* 223 */       GL11.glTexParameteri(3553, 10240, 9728);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_92026_h() {
/* 229 */     this.field_92027_q = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getMessage1YPos() {
/* 239 */     return (this.enum_special_splash == null) ? super.getMessage1YPos() : (this.height / 2 - this.enum_special_splash.getMessageHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean showMessage2() {
/* 246 */     return (this.enum_special_splash == null && super.showMessage2());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiConfirmOpenLink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */