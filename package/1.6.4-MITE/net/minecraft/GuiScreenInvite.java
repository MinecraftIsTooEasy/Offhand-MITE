/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public class GuiScreenInvite
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiTextField field_96227_a;
/*     */   private McoServer field_96223_b;
/*     */   private final GuiScreen field_96224_c;
/*     */   private final GuiScreenConfigureWorld field_96222_d;
/*  22 */   private final int field_96228_n = 0;
/*  23 */   private final int field_96229_o = 1;
/*  24 */   private String field_101016_p = "Could not invite the provided name";
/*     */   
/*     */   private String field_96226_p;
/*     */   private boolean field_96225_q;
/*     */   
/*     */   public GuiScreenInvite(GuiScreen guiScreen, GuiScreenConfigureWorld guiScreenConfigureWorld, McoServer mcoServer) {
/*  30 */     this.field_96224_c = guiScreen;
/*  31 */     this.field_96222_d = guiScreenConfigureWorld;
/*  32 */     this.field_96223_b = mcoServer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  37 */     this.field_96227_a.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  43 */     Keyboard.enableRepeatEvents(true);
/*  44 */     this.buttonList.clear();
/*  45 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, I18n.getString("mco.configure.world.buttons.invite")));
/*  46 */     this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.cancel")));
/*     */     
/*  48 */     this.field_96227_a = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 66, 200, 20);
/*  49 */     this.field_96227_a.setFocused(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  55 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  60 */     if (!guiButton.enabled)
/*  61 */       return;  if (guiButton.id == 1) {
/*  62 */       this.mc.displayGuiScreen(this.field_96222_d);
/*  63 */     } else if (guiButton.id == 0) {
/*  64 */       McoClient mcoClient = new McoClient(this.mc.getSession());
/*  65 */       if (this.field_96227_a.getText() == null || this.field_96227_a.getText().isEmpty()) {
/*     */         return;
/*     */       }
/*     */       try {
/*  69 */         McoServer mcoServer = mcoClient.func_96387_b(this.field_96223_b.field_96408_a, this.field_96227_a.getText());
/*  70 */         if (mcoServer != null)
/*  71 */         { this.field_96223_b.field_96402_f = mcoServer.field_96402_f;
/*  72 */           this.mc.displayGuiScreen(new GuiScreenConfigureWorld(this.field_96224_c, this.field_96223_b)); }
/*     */         else
/*  74 */         { func_101015_a(this.field_101016_p); } 
/*  75 */       } catch (ExceptionMcoService exceptionMcoService) {
/*  76 */         this.mc.getLogAgent().logSevere(exceptionMcoService.toString());
/*  77 */         func_101015_a(exceptionMcoService.field_96391_b);
/*  78 */       } catch (IOException iOException) {
/*  79 */         this.mc.getLogAgent().logWarning("Realms: could not parse response");
/*  80 */         func_101015_a(this.field_101016_p);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_101015_a(String string) {
/*  86 */     this.field_96225_q = true;
/*  87 */     this.field_96226_p = string;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char c, int i) {
/*  92 */     this.field_96227_a.textboxKeyTyped(c, i);
/*     */     
/*  94 */     if (i == 15) {
/*  95 */       if (this.field_96227_a.isFocused()) {
/*  96 */         this.field_96227_a.setFocused(false);
/*     */       } else {
/*  98 */         this.field_96227_a.setFocused(true);
/*     */       } 
/*     */     }
/* 101 */     if (i == 28 || i == 156) {
/* 102 */       actionPerformed(this.buttonList.get(0));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int i, int j, int k) {
/* 108 */     super.mouseClicked(i, j, k);
/*     */     
/* 110 */     this.field_96227_a.mouseClicked(i, j, k);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 115 */     drawDefaultBackground();
/*     */     
/* 117 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.invite.profile.name"), this.width / 2 - 100, 53, 10526880);
/*     */     
/* 119 */     if (this.field_96225_q) {
/* 120 */       drawCenteredString(this.fontRenderer, this.field_96226_p, this.width / 2, 100, 16711680);
/*     */     }
/*     */     
/* 123 */     this.field_96227_a.drawTextBox();
/*     */     
/* 125 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenInvite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */