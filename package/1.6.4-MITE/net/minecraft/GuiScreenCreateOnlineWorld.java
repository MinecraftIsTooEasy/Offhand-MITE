/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ public class GuiScreenCreateOnlineWorld
/*     */   extends ScreenWithCallback
/*     */ {
/*     */   private GuiScreen field_96260_a;
/*     */   private GuiTextField field_96257_c;
/*     */   private GuiTextField field_96255_b;
/*     */   private String field_98108_c;
/*     */   private String field_98109_n;
/*     */   private static int field_96253_d;
/*  27 */   private static int field_96261_n = 1;
/*  28 */   private static int field_110357_r = 2;
/*     */   
/*     */   private boolean field_96256_r;
/*  31 */   private String field_96254_s = "You must enter a name!";
/*     */   private WorldTemplate field_110356_u;
/*     */   
/*     */   public GuiScreenCreateOnlineWorld(GuiScreen guiScreen) {
/*  35 */     this.buttonList = Collections.synchronizedList(new ArrayList());
/*  36 */     this.field_96260_a = guiScreen;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  41 */     this.field_96257_c.updateCursorCounter();
/*  42 */     this.field_98108_c = this.field_96257_c.getText();
/*     */     
/*  44 */     this.field_96255_b.updateCursorCounter();
/*  45 */     this.field_98109_n = this.field_96255_b.getText();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  51 */     Keyboard.enableRepeatEvents(true);
/*  52 */     this.buttonList.clear();
/*     */     
/*  54 */     this.buttonList.add(new GuiButton(field_96253_d, this.width / 2 - 100, this.height / 4 + 120 + 17, 97, 20, I18n.getString("mco.create.world")));
/*  55 */     this.buttonList.add(new GuiButton(field_96261_n, this.width / 2 + 5, this.height / 4 + 120 + 17, 95, 20, I18n.getString("gui.cancel")));
/*     */     
/*  57 */     this.field_96257_c = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 65, 200, 20);
/*  58 */     this.field_96257_c.setFocused(true);
/*  59 */     if (this.field_98108_c != null) this.field_96257_c.setText(this.field_98108_c);
/*     */     
/*  61 */     this.field_96255_b = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 111, 200, 20);
/*  62 */     if (this.field_98109_n != null) this.field_96255_b.setText(this.field_98109_n);
/*     */     
/*  64 */     if (this.field_110356_u == null) {
/*  65 */       this.buttonList.add(new GuiButton(field_110357_r, this.width / 2 - 100, 147, 200, 20, I18n.getString("mco.template.default.name")));
/*     */     } else {
/*  67 */       this.field_96255_b.setText("");
/*  68 */       this.field_96255_b.setEnabled(false);
/*  69 */       this.field_96255_b.setFocused(false);
/*  70 */       this.buttonList.add(new GuiButton(field_110357_r, this.width / 2 - 100, 147, 200, 20, I18n.getString("mco.template.name") + ": " + this.field_110356_u.field_110732_b));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  76 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  81 */     if (!guiButton.enabled)
/*     */       return; 
/*  83 */     if (guiButton.id == field_96261_n) {
/*  84 */       this.mc.displayGuiScreen(this.field_96260_a);
/*  85 */     } else if (guiButton.id == field_96253_d) {
/*  86 */       func_96252_h();
/*  87 */     } else if (guiButton.id == field_110357_r) {
/*  88 */       this.mc.displayGuiScreen(new GuiScreenMcoWorldTemplate(this, this.field_110356_u));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_96252_h() {
/*  93 */     if (func_96249_i()) {
/*  94 */       TaskWorldCreation taskWorldCreation = new TaskWorldCreation(this, this.field_96257_c.getText(), "Minecraft Realms Server", this.field_98109_n, this.field_110356_u);
/*  95 */       GuiScreenLongRunningTask guiScreenLongRunningTask = new GuiScreenLongRunningTask(this.mc, this.field_96260_a, taskWorldCreation);
/*  96 */       guiScreenLongRunningTask.func_98117_g();
/*  97 */       this.mc.displayGuiScreen(guiScreenLongRunningTask);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean func_96249_i() {
/* 102 */     this.field_96256_r = (this.field_96257_c.getText() == null || this.field_96257_c.getText().trim().equals(""));
/* 103 */     return !this.field_96256_r;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char c, int i) {
/* 108 */     this.field_96257_c.textboxKeyTyped(c, i);
/* 109 */     this.field_96255_b.textboxKeyTyped(c, i);
/*     */     
/* 111 */     if (i == 15) {
/* 112 */       this.field_96257_c.setFocused(!this.field_96257_c.isFocused());
/* 113 */       this.field_96255_b.setFocused(!this.field_96255_b.isFocused());
/*     */     } 
/* 115 */     if (i == 28 || i == 156) {
/* 116 */       actionPerformed(this.buttonList.get(0));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int i, int j, int k) {
/* 123 */     super.mouseClicked(i, j, k);
/*     */     
/* 125 */     this.field_96257_c.mouseClicked(i, j, k);
/* 126 */     this.field_96255_b.mouseClicked(i, j, k);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 132 */     drawDefaultBackground();
/*     */     
/* 134 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.selectServer.create"), this.width / 2, 11, 16777215);
/* 135 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.name"), this.width / 2 - 100, 52, 10526880);
/* 136 */     drawString(this.fontRenderer, I18n.getString("mco.create.world.seed"), this.width / 2 - 100, 98, 10526880);
/*     */     
/* 138 */     if (this.field_96256_r) {
/* 139 */       drawCenteredString(this.fontRenderer, this.field_96254_s, this.width / 2, 167, 16711680);
/*     */     }
/* 141 */     this.field_96257_c.drawTextBox();
/* 142 */     this.field_96255_b.drawTextBox();
/*     */     
/* 144 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110355_a(WorldTemplate worldTemplate) {
/* 149 */     this.field_110356_u = worldTemplate;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenCreateOnlineWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */