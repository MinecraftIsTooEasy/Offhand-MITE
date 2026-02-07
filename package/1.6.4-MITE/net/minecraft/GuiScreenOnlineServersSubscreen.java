/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiScreenOnlineServersSubscreen
/*    */ {
/*    */   private final int field_104074_g;
/*    */   private final int field_104081_h;
/*    */   private final int field_104082_i;
/*    */   private final int field_104080_j;
/* 19 */   List field_104079_a = new ArrayList();
/*    */   
/*    */   String[] field_104077_b;
/*    */   String[] field_104078_c;
/*    */   String[][] field_104075_d;
/*    */   int field_104076_e;
/*    */   int field_104073_f;
/*    */   
/*    */   public GuiScreenOnlineServersSubscreen(int i, int j, int k, int l, int m, int n) {
/* 28 */     this.field_104074_g = i;
/* 29 */     this.field_104081_h = j;
/* 30 */     this.field_104082_i = k;
/* 31 */     this.field_104080_j = l;
/* 32 */     this.field_104076_e = m;
/* 33 */     this.field_104073_f = n;
/* 34 */     func_104068_a();
/*    */   }
/*    */   
/*    */   private void func_104068_a() {
/* 38 */     func_104070_b();
/*    */     
/* 40 */     this.field_104079_a.add(new GuiButton(5005, this.field_104082_i, this.field_104080_j + 1, 212, 20, func_104072_c()));
/* 41 */     this.field_104079_a.add(new GuiButton(5006, this.field_104082_i, this.field_104080_j + 25, 212, 20, func_104067_d()));
/*    */   }
/*    */ 
/*    */   
/*    */   private void func_104070_b() {
/* 46 */     this.field_104077_b = new String[] { I18n.getString("options.difficulty.peaceful"), I18n.getString("options.difficulty.easy"), I18n.getString("options.difficulty.normal"), I18n.getString("options.difficulty.hard") };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 53 */     this.field_104078_c = new String[] { I18n.getString("selectWorld.gameMode.survival"), I18n.getString("selectWorld.gameMode.creative"), I18n.getString("selectWorld.gameMode.adventure") };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     this.field_104075_d = new String[][] { { I18n.getString("selectWorld.gameMode.survival.line1"), I18n.getString("selectWorld.gameMode.survival.line2") }, { I18n.getString("selectWorld.gameMode.creative.line1"), I18n.getString("selectWorld.gameMode.creative.line2") }, { I18n.getString("selectWorld.gameMode.adventure.line1"), I18n.getString("selectWorld.gameMode.adventure.line2") } };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String func_104072_c() {
/* 68 */     String str = I18n.getString("options.difficulty");
/* 69 */     return str + ": " + this.field_104077_b[this.field_104076_e];
/*    */   }
/*    */   
/*    */   private String func_104067_d() {
/* 73 */     String str = I18n.getString("selectWorld.gameMode");
/* 74 */     return str + ": " + this.field_104078_c[this.field_104073_f];
/*    */   }
/*    */   
/*    */   void func_104069_a(GuiButton guiButton) {
/* 78 */     if (!guiButton.enabled)
/* 79 */       return;  if (guiButton.id == 5005) {
/* 80 */       this.field_104076_e = (this.field_104076_e + 1) % this.field_104077_b.length;
/* 81 */       guiButton.displayString = func_104072_c();
/* 82 */     } else if (guiButton.id == 5006) {
/* 83 */       this.field_104073_f = (this.field_104073_f + 1) % this.field_104078_c.length;
/* 84 */       guiButton.displayString = func_104067_d();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void func_104071_a(GuiScreen guiScreen, FontRenderer fontRenderer) {
/* 89 */     guiScreen.drawString(fontRenderer, this.field_104075_d[this.field_104073_f][0], this.field_104082_i, this.field_104080_j + 50, 10526880);
/* 90 */     guiScreen.drawString(fontRenderer, this.field_104075_d[this.field_104073_f][1], this.field_104082_i, this.field_104080_j + 60, 10526880);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenOnlineServersSubscreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */