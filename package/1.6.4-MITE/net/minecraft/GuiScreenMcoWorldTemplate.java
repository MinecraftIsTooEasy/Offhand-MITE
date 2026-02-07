/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiScreenMcoWorldTemplate
/*    */   extends GuiScreen
/*    */ {
/*    */   private final ScreenWithCallback field_110401_a;
/*    */   private WorldTemplate field_110398_b;
/* 19 */   private List field_110399_c = Collections.emptyList();
/*    */   
/*    */   private GuiScreenMcoWorldTemplateSelectionList field_110396_d;
/*    */   
/* 23 */   private int field_110397_e = -1;
/*    */ 
/*    */   
/*    */   private GuiButton field_110400_p;
/*    */ 
/*    */ 
/*    */   
/*    */   public GuiScreenMcoWorldTemplate(ScreenWithCallback screenWithCallback, WorldTemplate worldTemplate) {
/* 31 */     this.field_110401_a = screenWithCallback;
/* 32 */     this.field_110398_b = worldTemplate;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 37 */     Keyboard.enableRepeatEvents(true);
/* 38 */     this.buttonList.clear();
/*    */     
/* 40 */     this.field_110396_d = new GuiScreenMcoWorldTemplateSelectionList(this);
/*    */     
/* 42 */     (new GuiScreenMcoWorldTemplateDownloadThread(this)).start();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     func_110385_g();
/*    */   }
/*    */   
/*    */   private void func_110385_g() {
/* 58 */     this.buttonList.add(new GuiButton(0, this.width / 2 + 6, this.height - 52, 153, 20, I18n.getString("gui.cancel")));
/* 59 */     this.buttonList.add(this.field_110400_p = new GuiButton(1, this.width / 2 - 154, this.height - 52, 153, 20, I18n.getString("mco.template.button.select")));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateScreen() {
/* 64 */     super.updateScreen();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 69 */     if (!guiButton.enabled)
/*    */       return; 
/* 71 */     if (guiButton.id == 1) {
/* 72 */       func_110394_h();
/* 73 */     } else if (guiButton.id == 0) {
/* 74 */       this.field_110401_a.func_110354_a(null);
/* 75 */       this.mc.displayGuiScreen(this.field_110401_a);
/*    */     } else {
/* 77 */       this.field_110396_d.actionPerformed(guiButton);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void func_110394_h() {
/* 82 */     if (this.field_110397_e >= 0 && this.field_110397_e < this.field_110399_c.size()) {
/* 83 */       this.field_110401_a.func_110354_a(this.field_110399_c.get(this.field_110397_e));
/* 84 */       this.mc.displayGuiScreen(this.field_110401_a);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 91 */     drawDefaultBackground();
/*    */     
/* 93 */     this.field_110396_d.drawScreen(i, j, f);
/* 94 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.template.title"), this.width / 2, 20, 16777215);
/*    */     
/* 96 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenMcoWorldTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */