/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class TileEntityRendererPiston
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private RenderBlocks blockRenderer;
/*    */   
/*    */   public void renderPiston(TileEntityPiston par1TileEntityPiston, double par2, double par4, double par6, float par8) {
/* 12 */     Block var9 = Block.blocksList[par1TileEntityPiston.getStoredBlockID()];
/*    */     
/* 14 */     if (var9 != null && par1TileEntityPiston.getProgress(par8) < 1.0F) {
/*    */       
/* 16 */       Tessellator var10 = Tessellator.instance;
/* 17 */       bindTexture(TextureMap.locationBlocksTexture);
/* 18 */       RenderHelper.disableStandardItemLighting();
/* 19 */       GL11.glBlendFunc(770, 771);
/* 20 */       GL11.glEnable(3042);
/* 21 */       GL11.glDisable(2884);
/*    */       
/* 23 */       if (Minecraft.isAmbientOcclusionEnabled()) {
/*    */         
/* 25 */         GL11.glShadeModel(7425);
/*    */       }
/*    */       else {
/*    */         
/* 29 */         GL11.glShadeModel(7424);
/*    */       } 
/*    */       
/* 32 */       var10.startDrawingQuads();
/* 33 */       var10.setTranslation(((float)par2 - par1TileEntityPiston.xCoord + par1TileEntityPiston.getOffsetX(par8)), ((float)par4 - par1TileEntityPiston.yCoord + par1TileEntityPiston.getOffsetY(par8)), ((float)par6 - par1TileEntityPiston.zCoord + par1TileEntityPiston.getOffsetZ(par8)));
/*    */       
/* 35 */       var10.setColorRGBA(1, 1, 1, 255);
/*    */       
/* 37 */       if (var9 == Block.pistonExtension && par1TileEntityPiston.getProgress(par8) < 0.5F) {
/*    */         
/* 39 */         this.blockRenderer.renderPistonExtensionAllFaces(var9, par1TileEntityPiston.xCoord, par1TileEntityPiston.yCoord, par1TileEntityPiston.zCoord, false);
/*    */       }
/* 41 */       else if (par1TileEntityPiston.shouldRenderHead() && !par1TileEntityPiston.isExtending()) {
/*    */         
/* 43 */         Block.pistonExtension.setHeadTexture(((BlockPistonBase)var9).getPistonExtensionTexture());
/* 44 */         this.blockRenderer.renderPistonExtensionAllFaces(Block.pistonExtension, par1TileEntityPiston.xCoord, par1TileEntityPiston.yCoord, par1TileEntityPiston.zCoord, (par1TileEntityPiston.getProgress(par8) < 0.5F));
/* 45 */         Block.pistonExtension.clearHeadTexture();
/* 46 */         var10.setTranslation(((float)par2 - par1TileEntityPiston.xCoord), ((float)par4 - par1TileEntityPiston.yCoord), ((float)par6 - par1TileEntityPiston.zCoord));
/* 47 */         this.blockRenderer.renderPistonBaseAllFaces(var9, par1TileEntityPiston.xCoord, par1TileEntityPiston.yCoord, par1TileEntityPiston.zCoord);
/*    */       }
/*    */       else {
/*    */         
/* 51 */         this.blockRenderer.renderBlockAllFaces(var9, par1TileEntityPiston.xCoord, par1TileEntityPiston.yCoord, par1TileEntityPiston.zCoord);
/*    */       } 
/*    */       
/* 54 */       var10.setTranslation(0.0D, 0.0D, 0.0D);
/* 55 */       var10.draw();
/* 56 */       RenderHelper.enableStandardItemLighting();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onWorldChange(World par1World) {
/* 66 */     this.blockRenderer = new RenderBlocks(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 71 */     renderPiston((TileEntityPiston)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityRendererPiston.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */