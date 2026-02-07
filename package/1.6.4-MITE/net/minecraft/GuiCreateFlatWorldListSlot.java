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
/*     */ class GuiCreateFlatWorldListSlot
/*     */   extends GuiSlot
/*     */ {
/* 130 */   public int field_82454_a = -1;
/*     */   
/*     */   public GuiCreateFlatWorldListSlot(GuiCreateFlatWorld guiCreateFlatWorld) {
/* 133 */     super(guiCreateFlatWorld.mc, guiCreateFlatWorld.width, guiCreateFlatWorld.height, 43, guiCreateFlatWorld.height - 60, 24);
/*     */   }
/*     */   
/*     */   private void func_82452_a(int i, int j, ItemStack itemStack) {
/* 137 */     func_82451_d(i + 1, j + 1);
/*     */     
/* 139 */     GL11.glEnable(32826);
/*     */     
/* 141 */     if (itemStack != null) {
/* 142 */       RenderHelper.enableGUIStandardItemLighting();
/* 143 */       GuiCreateFlatWorld.getRenderItem().renderItemIntoGUI(this.createFlatWorldGui.fontRenderer, this.createFlatWorldGui.mc.getTextureManager(), itemStack, i + 2, j + 2);
/* 144 */       RenderHelper.disableStandardItemLighting();
/*     */     } 
/*     */     
/* 147 */     GL11.glDisable(32826);
/*     */   }
/*     */   
/*     */   private void func_82451_d(int i, int j) {
/* 151 */     func_82450_b(i, j, 0, 0);
/*     */   }
/*     */   
/*     */   private void func_82450_b(int i, int j, int k, int l) {
/* 155 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 156 */     this.createFlatWorldGui.mc.getTextureManager().bindTexture(Gui.statIcons);
/*     */     
/* 158 */     float f1 = 0.0078125F;
/* 159 */     float f2 = 0.0078125F;
/* 160 */     byte b1 = 18;
/* 161 */     byte b2 = 18;
/* 162 */     Tessellator tessellator = Tessellator.instance;
/* 163 */     tessellator.startDrawingQuads();
/* 164 */     tessellator.addVertexWithUV((i + 0), (j + 18), this.createFlatWorldGui.zLevel, ((k + 0) * 0.0078125F), ((l + 18) * 0.0078125F));
/* 165 */     tessellator.addVertexWithUV((i + 18), (j + 18), this.createFlatWorldGui.zLevel, ((k + 18) * 0.0078125F), ((l + 18) * 0.0078125F));
/* 166 */     tessellator.addVertexWithUV((i + 18), (j + 0), this.createFlatWorldGui.zLevel, ((k + 18) * 0.0078125F), ((l + 0) * 0.0078125F));
/* 167 */     tessellator.addVertexWithUV((i + 0), (j + 0), this.createFlatWorldGui.zLevel, ((k + 0) * 0.0078125F), ((l + 0) * 0.0078125F));
/* 168 */     tessellator.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/* 173 */     return GuiCreateFlatWorld.func_82271_a(this.createFlatWorldGui).getFlatLayers().size();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void elementClicked(int i, boolean bl) {
/* 178 */     this.field_82454_a = i;
/* 179 */     this.createFlatWorldGui.func_82270_g();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int i) {
/* 184 */     return (i == this.field_82454_a);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawBackground() {}
/*     */ 
/*     */   
/*     */   protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
/*     */     String str2;
/* 193 */     FlatLayerInfo flatLayerInfo = GuiCreateFlatWorld.func_82271_a(this.createFlatWorldGui).getFlatLayers().get(GuiCreateFlatWorld.func_82271_a(this.createFlatWorldGui).getFlatLayers().size() - i - 1);
/*     */     
/* 195 */     ItemStack itemStack = (flatLayerInfo.getFillBlock() == 0) ? null : new ItemStack(flatLayerInfo.getFillBlock(), 1, flatLayerInfo.getFillBlockMeta());
/* 196 */     String str1 = (itemStack == null) ? "Air" : Item.itemsList[flatLayerInfo.getFillBlock()].getItemStackDisplayName(itemStack);
/*     */     
/* 198 */     func_82452_a(j, k, itemStack);
/* 199 */     this.createFlatWorldGui.fontRenderer.drawString(str1, j + 18 + 5, k + 3, 16777215);
/*     */ 
/*     */ 
/*     */     
/* 203 */     if (i == 0) {
/* 204 */       str2 = I18n.getStringParams("createWorld.customize.flat.layer.top", new Object[] { Integer.valueOf(flatLayerInfo.getLayerCount()) });
/* 205 */     } else if (i == GuiCreateFlatWorld.func_82271_a(this.createFlatWorldGui).getFlatLayers().size() - 1) {
/* 206 */       str2 = I18n.getStringParams("createWorld.customize.flat.layer.bottom", new Object[] { Integer.valueOf(flatLayerInfo.getLayerCount()) });
/*     */     } else {
/* 208 */       str2 = I18n.getStringParams("createWorld.customize.flat.layer", new Object[] { Integer.valueOf(flatLayerInfo.getLayerCount()) });
/*     */     } 
/*     */     
/* 211 */     this.createFlatWorldGui.fontRenderer.drawString(str2, j + 2 + 213 - this.createFlatWorldGui.fontRenderer.getStringWidth(str2), k + 3, 16777215);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/* 216 */     return this.createFlatWorldGui.width - 70;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiCreateFlatWorldListSlot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */