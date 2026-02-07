/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import org.lwjgl.input.Keyboard;
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
/*     */ public class GuiScreenEditOnlineWorld
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiScreen field_96204_a;
/*     */   private GuiScreen field_96202_b;
/*     */   private GuiTextField field_96203_c;
/*     */   private GuiTextField field_96201_d;
/*     */   private McoServer field_96205_n;
/*     */   private GuiButton field_96206_o;
/*     */   private int field_104054_p;
/*     */   private int field_104053_q;
/*     */   private int field_104052_r;
/*     */   private GuiScreenOnlineServersSubscreen field_104051_s;
/*     */   
/*     */   public GuiScreenEditOnlineWorld(GuiScreen guiScreen, GuiScreen guiScreen2, McoServer mcoServer) {
/*  36 */     this.field_96204_a = guiScreen;
/*  37 */     this.field_96202_b = guiScreen2;
/*  38 */     this.field_96205_n = mcoServer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  43 */     this.field_96201_d.updateCursorCounter();
/*  44 */     this.field_96203_c.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  50 */     this.field_104054_p = this.width / 4;
/*  51 */     this.field_104053_q = this.width / 4 - 2;
/*  52 */     this.field_104052_r = this.width / 2 + 4;
/*     */     
/*  54 */     Keyboard.enableRepeatEvents(true);
/*  55 */     this.buttonList.clear();
/*     */     
/*  57 */     this.buttonList.add(this.field_96206_o = new GuiButton(0, this.field_104054_p, this.height / 4 + 120 + 22, this.field_104053_q, 20, I18n.getString("mco.configure.world.buttons.done")));
/*  58 */     this.buttonList.add(new GuiButton(1, this.field_104052_r, this.height / 4 + 120 + 22, this.field_104053_q, 20, I18n.getString("gui.cancel")));
/*     */     
/*  60 */     this.field_96201_d = new GuiTextField(this.fontRenderer, this.field_104054_p, 56, 212, 20);
/*  61 */     this.field_96201_d.setFocused(true);
/*  62 */     this.field_96201_d.setMaxStringLength(32);
/*  63 */     this.field_96201_d.setText(this.field_96205_n.func_96398_b());
/*     */     
/*  65 */     this.field_96203_c = new GuiTextField(this.fontRenderer, this.field_104054_p, 96, 212, 20);
/*  66 */     this.field_96203_c.setMaxStringLength(32);
/*  67 */     this.field_96203_c.setText(this.field_96205_n.func_96397_a());
/*     */     
/*  69 */     this.field_104051_s = new GuiScreenOnlineServersSubscreen(this.width, this.height, this.field_104054_p, 122, this.field_96205_n.field_110729_i, this.field_96205_n.field_110728_j);
/*  70 */     this.buttonList.addAll(this.field_104051_s.field_104079_a);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  75 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  80 */     if (!guiButton.enabled)
/*  81 */       return;  if (guiButton.id == 1) {
/*  82 */       this.mc.displayGuiScreen(this.field_96204_a);
/*  83 */     } else if (guiButton.id == 0) {
/*  84 */       func_96200_g();
/*  85 */     } else if (guiButton.id == 2) {
/*  86 */       this.mc.displayGuiScreen(new GuiScreenResetWorld(this, this.field_96205_n));
/*     */     } else {
/*  88 */       this.field_104051_s.func_104069_a(guiButton);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_96200_g() {
/*  93 */     McoClient mcoClient = new McoClient(this.mc.getSession());
/*     */     try {
/*  95 */       String str = (this.field_96203_c.getText() == null || this.field_96203_c.getText().trim().equals("")) ? null : this.field_96203_c.getText();
/*  96 */       mcoClient.func_96384_a(this.field_96205_n.field_96408_a, this.field_96201_d.getText(), str, this.field_104051_s.field_104076_e, this.field_104051_s.field_104073_f);
/*  97 */       this.field_96205_n.func_96399_a(this.field_96201_d.getText());
/*  98 */       this.field_96205_n.func_96400_b(this.field_96203_c.getText());
/*  99 */       this.field_96205_n.field_110729_i = this.field_104051_s.field_104076_e;
/* 100 */       this.field_96205_n.field_110728_j = this.field_104051_s.field_104073_f;
/* 101 */       this.mc.displayGuiScreen(new GuiScreenConfigureWorld(this.field_96202_b, this.field_96205_n));
/* 102 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 103 */       this.mc.getLogAgent().logSevere(exceptionMcoService.toString());
/* 104 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 105 */       this.mc.getLogAgent().logWarning("Realms: " + unsupportedEncodingException.getLocalizedMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char c, int i) {
/* 111 */     this.field_96201_d.textboxKeyTyped(c, i);
/* 112 */     this.field_96203_c.textboxKeyTyped(c, i);
/*     */     
/* 114 */     if (i == 15) {
/* 115 */       this.field_96201_d.setFocused(!this.field_96201_d.isFocused());
/* 116 */       this.field_96203_c.setFocused(!this.field_96203_c.isFocused());
/*     */     } 
/* 118 */     if (i == 28 || i == 156) {
/* 119 */       func_96200_g();
/*     */     }
/*     */     
/* 122 */     this.field_96206_o.enabled = (this.field_96201_d.getText() != null && !this.field_96201_d.getText().trim().equals(""));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int i, int j, int k) {
/* 127 */     super.mouseClicked(i, j, k);
/*     */     
/* 129 */     this.field_96203_c.mouseClicked(i, j, k);
/* 130 */     this.field_96201_d.mouseClicked(i, j, k);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 136 */     drawDefaultBackground();
/*     */     
/* 138 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.configure.world.edit.title"), this.width / 2, 17, 16777215);
/* 139 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.name"), this.field_104054_p, 43, 10526880);
/* 140 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.description"), this.field_104054_p, 84, 10526880);
/*     */     
/* 142 */     this.field_96201_d.drawTextBox();
/* 143 */     this.field_96203_c.drawTextBox();
/*     */     
/* 145 */     this.field_104051_s.func_104071_a(this, this.fontRenderer);
/*     */     
/* 147 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenEditOnlineWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */