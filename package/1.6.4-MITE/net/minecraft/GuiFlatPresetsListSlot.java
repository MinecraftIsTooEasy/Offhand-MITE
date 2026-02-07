/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GuiFlatPresetsListSlot
/*     */   extends GuiSlot
/*     */ {
/* 199 */   public int field_82459_a = -1;
/*     */   
/*     */   public GuiFlatPresetsListSlot(GuiFlatPresets guiFlatPresets) {
/* 202 */     super(guiFlatPresets.mc, guiFlatPresets.width, guiFlatPresets.height, 80, guiFlatPresets.height - 37, 24);
/*     */   }
/*     */   
/*     */   private void func_82457_a(int i, int j, int k) {
/* 206 */     func_82456_d(i + 1, j + 1);
/*     */     
/* 208 */     GL11.glEnable(32826);
/*     */     
/* 210 */     RenderHelper.enableGUIStandardItemLighting();
/*     */     
/* 212 */     GuiFlatPresets.getPresetIconRenderer().renderItemIntoGUI(this.flatPresetsGui.fontRenderer, this.flatPresetsGui.mc.getTextureManager(), new ItemStack(k, 1, 0), i + 2, j + 2);
/* 213 */     RenderHelper.disableStandardItemLighting();
/*     */     
/* 215 */     GL11.glDisable(32826);
/*     */   }
/*     */   
/*     */   private void func_82456_d(int i, int j) {
/* 219 */     func_82455_b(i, j, 0, 0);
/*     */   }
/*     */   
/*     */   private void func_82455_b(int i, int j, int k, int l) {
/* 223 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 224 */     this.flatPresetsGui.mc.getTextureManager().bindTexture(Gui.statIcons);
/*     */     
/* 226 */     float f1 = 0.0078125F;
/* 227 */     float f2 = 0.0078125F;
/* 228 */     byte b1 = 18;
/* 229 */     byte b2 = 18;
/* 230 */     Tessellator tessellator = Tessellator.instance;
/* 231 */     tessellator.startDrawingQuads();
/* 232 */     tessellator.addVertexWithUV((i + 0), (j + 18), this.flatPresetsGui.zLevel, ((k + 0) * 0.0078125F), ((l + 18) * 0.0078125F));
/* 233 */     tessellator.addVertexWithUV((i + 18), (j + 18), this.flatPresetsGui.zLevel, ((k + 18) * 0.0078125F), ((l + 18) * 0.0078125F));
/* 234 */     tessellator.addVertexWithUV((i + 18), (j + 0), this.flatPresetsGui.zLevel, ((k + 18) * 0.0078125F), ((l + 0) * 0.0078125F));
/* 235 */     tessellator.addVertexWithUV((i + 0), (j + 0), this.flatPresetsGui.zLevel, ((k + 0) * 0.0078125F), ((l + 0) * 0.0078125F));
/* 236 */     tessellator.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/* 241 */     return GuiFlatPresets.getPresets().size();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void elementClicked(int i, boolean bl) {
/* 246 */     this.field_82459_a = i;
/* 247 */     this.flatPresetsGui.func_82296_g();
/* 248 */     GuiFlatPresets.func_82298_b(this.flatPresetsGui).setText(((GuiFlatPresetsItem)GuiFlatPresets.getPresets().get((GuiFlatPresets.func_82292_a(this.flatPresetsGui)).field_82459_a)).presetData);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int i) {
/* 253 */     return (i == this.field_82459_a);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawBackground() {}
/*     */ 
/*     */   
/*     */   protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
/* 262 */     GuiFlatPresetsItem guiFlatPresetsItem = GuiFlatPresets.getPresets().get(i);
/* 263 */     func_82457_a(j, k, guiFlatPresetsItem.iconId);
/* 264 */     this.flatPresetsGui.fontRenderer.drawString(guiFlatPresetsItem.presetName, j + 18 + 5, k + 6, 16777215);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiFlatPresetsListSlot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */