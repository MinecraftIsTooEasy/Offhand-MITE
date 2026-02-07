/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiScreenConfirmation
/*    */   extends GuiScreen
/*    */ {
/*    */   private final GuiScreenConfirmationType field_140045_e;
/*    */   private final String field_140049_p;
/*    */   private final String field_96288_n;
/*    */   protected final GuiScreen field_140048_a;
/*    */   protected final String field_140046_b;
/*    */   protected final String field_140047_c;
/*    */   protected final int field_140044_d;
/*    */   
/*    */   public GuiScreenConfirmation(GuiScreen guiScreen, GuiScreenConfirmationType guiScreenConfirmationType, String string, String string2, int i) {
/* 20 */     this.field_140048_a = guiScreen;
/* 21 */     this.field_140044_d = i;
/* 22 */     this.field_140045_e = guiScreenConfirmationType;
/* 23 */     this.field_140049_p = string;
/* 24 */     this.field_96288_n = string2;
/* 25 */     this.field_140046_b = I18n.getString("gui.yes");
/* 26 */     this.field_140047_c = I18n.getString("gui.no");
/*    */   }
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 31 */     this.buttonList.add(new GuiSmallButton(0, this.width / 2 - 155, this.height / 6 + 112, this.field_140046_b));
/* 32 */     this.buttonList.add(new GuiSmallButton(1, this.width / 2 - 155 + 160, this.height / 6 + 112, this.field_140047_c));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 37 */     this.field_140048_a.confirmClicked((guiButton.id == 0), this.field_140044_d);
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 42 */     drawDefaultBackground();
/*    */     
/* 44 */     drawCenteredString(this.fontRenderer, this.field_140045_e.field_140072_d, this.width / 2, 70, this.field_140045_e.field_140075_c);
/* 45 */     drawCenteredString(this.fontRenderer, this.field_140049_p, this.width / 2, 90, 16777215);
/*    */     
/* 47 */     drawCenteredString(this.fontRenderer, this.field_96288_n, this.width / 2, 110, 16777215);
/*    */     
/* 49 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenConfirmation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */