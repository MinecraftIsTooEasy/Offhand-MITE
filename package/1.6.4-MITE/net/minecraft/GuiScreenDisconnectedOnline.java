/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiScreenDisconnectedOnline
/*    */   extends GuiScreen
/*    */ {
/*    */   private String field_98113_a;
/*    */   private String field_98111_b;
/*    */   private Object[] field_98112_c;
/*    */   private List field_98110_d;
/*    */   private final GuiScreen field_98114_n;
/*    */   
/*    */   public GuiScreenDisconnectedOnline(GuiScreen guiScreen, String string, String string2, Object... objects) {
/* 17 */     this.field_98114_n = guiScreen;
/* 18 */     this.field_98113_a = I18n.getString(string);
/* 19 */     this.field_98111_b = string2;
/* 20 */     this.field_98112_c = objects;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char c, int i) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 30 */     this.buttonList.clear();
/* 31 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.back")));
/*    */     
/* 33 */     if (this.field_98112_c != null) {
/* 34 */       this.field_98110_d = this.fontRenderer.listFormattedStringToWidth(I18n.getStringParams(this.field_98111_b, this.field_98112_c), this.width - 50);
/*    */     } else {
/* 36 */       this.field_98110_d = this.fontRenderer.listFormattedStringToWidth(I18n.getString(this.field_98111_b), this.width - 50);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 42 */     if (guiButton.id == 0) {
/* 43 */       this.mc.displayGuiScreen(this.field_98114_n);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 49 */     drawDefaultBackground();
/*    */     
/* 51 */     drawCenteredString(this.fontRenderer, this.field_98113_a, this.width / 2, this.height / 2 - 50, 11184810);
/*    */     
/* 53 */     int k = this.height / 2 - 30;
/*    */     
/* 55 */     if (this.field_98110_d != null) {
/* 56 */       for (String str : this.field_98110_d) {
/* 57 */         drawCenteredString(this.fontRenderer, str, this.width / 2, k, 16777215);
/* 58 */         k += this.fontRenderer.FONT_HEIGHT;
/*    */       } 
/*    */     }
/*    */     
/* 62 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenDisconnectedOnline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */