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
/*     */ class GuiSnooperList
/*     */   extends GuiSlot
/*     */ {
/*     */   public GuiSnooperList(GuiSnooper guiSnooper) {
/*  95 */     super(guiSnooper.mc, guiSnooper.width, guiSnooper.height, 80, guiSnooper.height - 40, guiSnooper.fontRenderer.FONT_HEIGHT + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/* 100 */     return GuiSnooper.func_74095_a(this.snooperGui).size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void elementClicked(int i, boolean bl) {}
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int i) {
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawBackground() {}
/*     */ 
/*     */   
/*     */   protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
/* 118 */     this.snooperGui.fontRenderer.drawString(GuiSnooper.func_74095_a(this.snooperGui).get(i), 10, k, 16777215);
/*     */     
/* 120 */     this.snooperGui.fontRenderer.drawString(GuiSnooper.func_74094_b(this.snooperGui).get(i), 230, k, 16777215);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/* 125 */     return this.snooperGui.width - 10;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSnooperList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */