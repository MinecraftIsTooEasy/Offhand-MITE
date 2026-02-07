/*     */ package net.minecraft;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SelectionListInvited
/*     */   extends SelectionListBase
/*     */ {
/*     */   public SelectionListInvited(GuiScreenConfigureWorld guiScreenConfigureWorld) {
/* 239 */     super(GuiScreenConfigureWorld.getMinecraft(guiScreenConfigureWorld), GuiScreenConfigureWorld.func_96271_b(guiScreenConfigureWorld), GuiScreenConfigureWorld.func_96274_a(guiScreenConfigureWorld, 2), GuiScreenConfigureWorld.func_96269_c(guiScreenConfigureWorld), GuiScreenConfigureWorld.func_96274_a(guiScreenConfigureWorld, 9) - GuiScreenConfigureWorld.func_96274_a(guiScreenConfigureWorld, 2), 12);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_96608_a() {
/* 244 */     return (GuiScreenConfigureWorld.func_96266_d(this.theGuiScreenConfigureWorld)).field_96402_f.size() + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96615_a(int i, boolean bl) {
/* 249 */     if (i >= (GuiScreenConfigureWorld.func_96266_d(this.theGuiScreenConfigureWorld)).field_96402_f.size()) {
/*     */       return;
/*     */     }
/* 252 */     GuiScreenConfigureWorld.func_96270_b(this.theGuiScreenConfigureWorld, i);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_96609_a(int i) {
/* 257 */     return (i == GuiScreenConfigureWorld.func_96263_e(this.theGuiScreenConfigureWorld));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_96613_b() {
/* 262 */     return func_96608_a() * 12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_96611_c() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_96610_a(int i, int j, int k, int l, Tessellator tessellator) {
/* 272 */     if (i < (GuiScreenConfigureWorld.func_96266_d(this.theGuiScreenConfigureWorld)).field_96402_f.size()) {
/* 273 */       func_98263_b(i, j, k, l, tessellator);
/*     */     }
/*     */   }
/*     */   
/*     */   private void func_98263_b(int i, int j, int k, int l, Tessellator tessellator) {
/* 278 */     String str = (GuiScreenConfigureWorld.func_96266_d(this.theGuiScreenConfigureWorld)).field_96402_f.get(i);
/* 279 */     this.theGuiScreenConfigureWorld.drawString(GuiScreenConfigureWorld.func_96273_f(this.theGuiScreenConfigureWorld), str, j + 2, k + 1, 16777215);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SelectionListInvited.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */