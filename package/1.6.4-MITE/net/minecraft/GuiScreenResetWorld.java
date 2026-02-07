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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiScreenResetWorld
/*     */   extends ScreenWithCallback
/*     */ {
/*     */   private GuiScreen field_96152_a;
/*     */   private McoServer field_96150_b;
/*     */   private GuiTextField field_96151_c;
/*  22 */   private final int field_96149_d = 1;
/*  23 */   private final int field_96153_n = 2;
/*  24 */   private static int field_110360_p = 3;
/*     */   
/*     */   private WorldTemplate field_110359_q;
/*     */   
/*     */   private GuiButton field_96154_o;
/*     */   
/*     */   public GuiScreenResetWorld(GuiScreen guiScreen, McoServer mcoServer) {
/*  31 */     this.field_96152_a = guiScreen;
/*  32 */     this.field_96150_b = mcoServer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  37 */     this.field_96151_c.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  43 */     Keyboard.enableRepeatEvents(true);
/*  44 */     this.buttonList.clear();
/*  45 */     this.buttonList.add(this.field_96154_o = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, 97, 20, I18n.getString("mco.configure.world.buttons.reset")));
/*  46 */     this.buttonList.add(new GuiButton(2, this.width / 2 + 5, this.height / 4 + 120 + 12, 97, 20, I18n.getString("gui.cancel")));
/*     */     
/*  48 */     this.field_96151_c = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 99, 200, 20);
/*  49 */     this.field_96151_c.setFocused(true);
/*  50 */     this.field_96151_c.setMaxStringLength(32);
/*  51 */     this.field_96151_c.setText("");
/*     */     
/*  53 */     if (this.field_110359_q == null) {
/*  54 */       this.buttonList.add(new GuiButton(field_110360_p, this.width / 2 - 100, 125, 200, 20, I18n.getString("mco.template.default.name")));
/*     */     } else {
/*  56 */       this.field_96151_c.setText("");
/*  57 */       this.field_96151_c.setEnabled(false);
/*  58 */       this.field_96151_c.setFocused(false);
/*  59 */       this.buttonList.add(new GuiButton(field_110360_p, this.width / 2 - 100, 125, 200, 20, I18n.getString("mco.template.name") + ": " + this.field_110359_q.field_110732_b));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  65 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char c, int i) {
/*  70 */     this.field_96151_c.textboxKeyTyped(c, i);
/*     */     
/*  72 */     if (i == 28 || i == 156) {
/*  73 */       actionPerformed(this.field_96154_o);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  79 */     if (!guiButton.enabled)
/*  80 */       return;  if (guiButton.id == 2) {
/*  81 */       this.mc.displayGuiScreen(this.field_96152_a);
/*  82 */     } else if (guiButton.id == 1) {
/*  83 */       String str1 = I18n.getString("mco.configure.world.reset.question.line1");
/*  84 */       String str2 = I18n.getString("mco.configure.world.reset.question.line2");
/*  85 */       this.mc.displayGuiScreen(new GuiScreenConfirmation(this, GuiScreenConfirmationType.Warning, str1, str2, 1));
/*  86 */     } else if (guiButton.id == field_110360_p) {
/*  87 */       this.mc.displayGuiScreen(new GuiScreenMcoWorldTemplate(this, this.field_110359_q));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void confirmClicked(boolean bl, int i) {
/*  92 */     if (bl && i == 1) {
/*  93 */       func_140006_g();
/*     */     } else {
/*  95 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_140006_g() {
/* 100 */     TaskResetWorld taskResetWorld = new TaskResetWorld(this, this.field_96150_b.field_96408_a, this.field_96151_c.getText(), this.field_110359_q);
/* 101 */     GuiScreenLongRunningTask guiScreenLongRunningTask = new GuiScreenLongRunningTask(this.mc, this.field_96152_a, taskResetWorld);
/* 102 */     guiScreenLongRunningTask.func_98117_g();
/* 103 */     this.mc.displayGuiScreen(guiScreenLongRunningTask);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int i, int j, int k) {
/* 108 */     super.mouseClicked(i, j, k);
/*     */     
/* 110 */     this.field_96151_c.mouseClicked(i, j, k);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 116 */     drawDefaultBackground();
/*     */     
/* 118 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.reset.world.title"), this.width / 2, 17, 16777215);
/* 119 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.reset.world.warning"), this.width / 2, 56, 16711680);
/* 120 */     drawString(this.fontRenderer, I18n.getString("mco.reset.world.seed"), this.width / 2 - 100, 86, 10526880);
/*     */     
/* 122 */     this.field_96151_c.drawTextBox();
/*     */     
/* 124 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ 
/*     */   
/*     */   void func_110358_a(WorldTemplate worldTemplate) {
/* 129 */     this.field_110359_q = worldTemplate;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenResetWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */