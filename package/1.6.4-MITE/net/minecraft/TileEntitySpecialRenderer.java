/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class TileEntitySpecialRenderer
/*    */ {
/*    */   protected TileEntityRenderer tileEntityRenderer;
/*    */   
/*    */   public abstract void renderTileEntityAt(TileEntity paramTileEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat);
/*    */   
/*    */   protected void bindTexture(ResourceLocation resourceLocation) {
/* 15 */     TextureManager textureManager = this.tileEntityRenderer.renderEngine;
/* 16 */     if (textureManager != null) {
/* 17 */       textureManager.bindTexture(resourceLocation);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTileEntityRenderer(TileEntityRenderer tileEntityRenderer) {
/* 26 */     this.tileEntityRenderer = tileEntityRenderer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onWorldChange(World world) {}
/*    */ 
/*    */   
/*    */   public FontRenderer getFontRenderer() {
/* 34 */     return this.tileEntityRenderer.getFontRenderer();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntitySpecialRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */