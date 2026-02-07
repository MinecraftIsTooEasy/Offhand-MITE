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
/*     */ class GuiScreenMcoWorldTemplateSelectionList
/*     */   extends GuiScreenSelectLocation
/*     */ {
/*     */   public GuiScreenMcoWorldTemplateSelectionList(GuiScreenMcoWorldTemplate guiScreenMcoWorldTemplate) {
/* 106 */     super(GuiScreenMcoWorldTemplate.func_130066_c(guiScreenMcoWorldTemplate), guiScreenMcoWorldTemplate.width, guiScreenMcoWorldTemplate.height, 32, guiScreenMcoWorldTemplate.height - 64, 36);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/* 111 */     return GuiScreenMcoWorldTemplate.func_110395_c(this.field_111245_a).size() + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void elementClicked(int i, boolean bl) {
/* 117 */     if (i >= GuiScreenMcoWorldTemplate.func_110395_c(this.field_111245_a).size()) {
/*     */       return;
/*     */     }
/*     */     
/* 121 */     GuiScreenMcoWorldTemplate.func_130064_a(this.field_111245_a, i);
/* 122 */     GuiScreenMcoWorldTemplate.func_130065_a(this.field_111245_a, (WorldTemplate)null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int i) {
/* 127 */     if (GuiScreenMcoWorldTemplate.func_110395_c(this.field_111245_a).size() == 0) return false;
/*     */     
/* 129 */     if (i >= GuiScreenMcoWorldTemplate.func_110395_c(this.field_111245_a).size()) return false;
/*     */     
/* 131 */     if (GuiScreenMcoWorldTemplate.func_130067_e(this.field_111245_a) != null)
/* 132 */       return (GuiScreenMcoWorldTemplate.func_130067_e(this.field_111245_a)).field_110732_b.equals(((WorldTemplate)GuiScreenMcoWorldTemplate.func_110395_c(this.field_111245_a).get(i)).field_110732_b); 
/* 133 */     return (i == GuiScreenMcoWorldTemplate.func_130062_f(this.field_111245_a));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_104086_b(int i) {
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_130003_b() {
/* 143 */     return getSize() * 36;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_130004_c() {
/* 148 */     this.field_111245_a.drawDefaultBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
/* 153 */     if (i < GuiScreenMcoWorldTemplate.func_110395_c(this.field_111245_a).size()) {
/* 154 */       func_111244_b(i, j, k, l, tessellator);
/*     */     }
/*     */   }
/*     */   
/*     */   private void func_111244_b(int i, int j, int k, int l, Tessellator tessellator) {
/* 159 */     WorldTemplate worldTemplate = GuiScreenMcoWorldTemplate.func_110395_c(this.field_111245_a).get(i);
/*     */     
/* 161 */     this.field_111245_a.drawString(GuiScreenMcoWorldTemplate.func_110389_g(this.field_111245_a), worldTemplate.field_110732_b, j + 2, k + 1, 16777215);
/* 162 */     this.field_111245_a.drawString(GuiScreenMcoWorldTemplate.func_110387_h(this.field_111245_a), worldTemplate.field_110731_d, j + 2, k + 12, 7105644);
/* 163 */     this.field_111245_a.drawString(GuiScreenMcoWorldTemplate.func_110384_i(this.field_111245_a), worldTemplate.field_110733_c, j + 2 + 207 - GuiScreenMcoWorldTemplate.func_130063_j(this.field_111245_a).getStringWidth(worldTemplate.field_110733_c), k + 1, 5000268);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenMcoWorldTemplateSelectionList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */