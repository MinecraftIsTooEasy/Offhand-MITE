/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ public class GuiScreenBackup
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreenConfigureWorld field_110380_a;
/*     */   private final long field_110377_b;
/*  23 */   private List field_110378_c = Collections.emptyList();
/*     */   
/*     */   private GuiScreenBackupSelectionList field_110375_d;
/*     */   
/*  27 */   private int field_110376_e = -1;
/*     */ 
/*     */   
/*     */   private GuiButton field_110379_p;
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiScreenBackup(GuiScreenConfigureWorld guiScreenConfigureWorld, long l) {
/*  35 */     this.field_110380_a = guiScreenConfigureWorld;
/*  36 */     this.field_110377_b = l;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  41 */     Keyboard.enableRepeatEvents(true);
/*  42 */     this.buttonList.clear();
/*     */     
/*  44 */     this.field_110375_d = new GuiScreenBackupSelectionList(this);
/*     */     
/*  46 */     (new GuiScreenBackupDownloadThread(this)).start();
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
/*  58 */     func_110369_g();
/*     */   }
/*     */   
/*     */   private void func_110369_g() {
/*  62 */     this.buttonList.add(new GuiButton(0, this.width / 2 + 6, this.height - 52, 153, 20, I18n.getString("gui.back")));
/*  63 */     this.buttonList.add(this.field_110379_p = new GuiButton(1, this.width / 2 - 154, this.height - 52, 153, 20, I18n.getString("mco.backup.button.restore")));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  68 */     super.updateScreen();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  73 */     if (!guiButton.enabled)
/*     */       return; 
/*  75 */     if (guiButton.id == 1) {
/*  76 */       String str1 = I18n.getString("mco.configure.world.restore.question.line1");
/*  77 */       String str2 = I18n.getString("mco.configure.world.restore.question.line2");
/*  78 */       this.mc.displayGuiScreen(new GuiScreenConfirmation(this, GuiScreenConfirmationType.Warning, str1, str2, 1));
/*  79 */     } else if (guiButton.id == 0) {
/*  80 */       this.mc.displayGuiScreen(this.field_110380_a);
/*     */     } else {
/*  82 */       this.field_110375_d.actionPerformed(guiButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean bl, int i) {
/*  88 */     if (bl && i == 1) {
/*  89 */       func_110374_h();
/*     */     } else {
/*  91 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_110374_h() {
/*  96 */     if (this.field_110376_e >= 0 && this.field_110376_e < this.field_110378_c.size()) {
/*  97 */       Backup backup = this.field_110378_c.get(this.field_110376_e);
/*  98 */       GuiScreenBackupRestoreTask guiScreenBackupRestoreTask = new GuiScreenBackupRestoreTask(this, backup, null);
/*  99 */       GuiScreenLongRunningTask guiScreenLongRunningTask = new GuiScreenLongRunningTask(this.mc, this.field_110380_a, guiScreenBackupRestoreTask);
/* 100 */       guiScreenLongRunningTask.func_98117_g();
/* 101 */       this.mc.displayGuiScreen(guiScreenLongRunningTask);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 109 */     drawDefaultBackground();
/*     */     
/* 111 */     this.field_110375_d.drawScreen(i, j, f);
/* 112 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.backup.title"), this.width / 2, 20, 16777215);
/*     */     
/* 114 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenBackup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */