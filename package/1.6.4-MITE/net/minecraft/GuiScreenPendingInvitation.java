/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
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
/*     */ 
/*     */ public class GuiScreenPendingInvitation
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen field_130061_a;
/*     */   private GuiScreenPendingInvitationList field_130059_b;
/*  24 */   private List field_130060_c = Lists.newArrayList();
/*     */   
/*  26 */   private int field_130058_d = -1;
/*     */   
/*     */   public GuiScreenPendingInvitation(GuiScreen guiScreen) {
/*  29 */     this.field_130061_a = guiScreen;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  34 */     Keyboard.enableRepeatEvents(true);
/*  35 */     this.buttonList.clear();
/*     */     
/*  37 */     this.field_130059_b = new GuiScreenPendingInvitationList(this);
/*     */     
/*  39 */     (new GuiScreenPendingInvitationINNER1(this)).start();
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
/*  51 */     func_130050_g();
/*     */   }
/*     */   
/*     */   private void func_130050_g() {
/*  55 */     this.buttonList.add(new GuiButton(1, this.width / 2 - 154, this.height - 52, 153, 20, I18n.getString("mco.invites.button.accept")));
/*  56 */     this.buttonList.add(new GuiButton(2, this.width / 2 + 6, this.height - 52, 153, 20, I18n.getString("mco.invites.button.reject")));
/*  57 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 75, this.height - 28, 153, 20, I18n.getString("gui.back")));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  62 */     super.updateScreen();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  67 */     if (!guiButton.enabled)
/*     */       return; 
/*  69 */     if (guiButton.id == 1) {
/*  70 */       func_130051_i();
/*  71 */     } else if (guiButton.id == 0) {
/*  72 */       this.mc.displayGuiScreen(this.field_130061_a);
/*  73 */     } else if (guiButton.id == 2) {
/*  74 */       func_130057_h();
/*     */     } else {
/*  76 */       this.field_130059_b.actionPerformed(guiButton);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_130057_h() {
/*  81 */     if (this.field_130058_d >= 0 && this.field_130058_d < this.field_130060_c.size()) {
/*  82 */       (new GuiScreenPendingInvitationINNER2(this)).start();
/*     */     }
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
/*     */   private void func_130051_i() {
/*  98 */     if (this.field_130058_d >= 0 && this.field_130058_d < this.field_130060_c.size()) {
/*  99 */       (new GuiScreenPendingInvitationINNER3(this)).start();
/*     */     }
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
/*     */   private void func_130047_j() {
/* 115 */     int i = this.field_130058_d;
/*     */     
/* 117 */     if (this.field_130060_c.size() - 1 == this.field_130058_d) {
/* 118 */       this.field_130058_d--;
/*     */     }
/*     */     
/* 121 */     this.field_130060_c.remove(i);
/*     */     
/* 123 */     if (this.field_130060_c.size() == 0) {
/* 124 */       this.field_130058_d = -1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 131 */     drawDefaultBackground();
/*     */     
/* 133 */     this.field_130059_b.drawScreen(i, j, f);
/* 134 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.invites.title"), this.width / 2, 20, 16777215);
/*     */     
/* 136 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenPendingInvitation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */