/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ChestItemRenderHelper
/*    */ {
/*  6 */   public static ChestItemRenderHelper instance = new ChestItemRenderHelper();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 12 */   private TileEntityChest theChest = new TileEntityChest(EnumChestType.normal, Block.chest);
/* 13 */   private TileEntityChest field_142033_c = new TileEntityChest(EnumChestType.trapped, Block.chestTrapped);
/*    */   
/* 15 */   private TileEntityChest copperChest = new TileEntityChest(EnumChestType.strongbox, Block.chestCopper);
/* 16 */   private TileEntityChest silverChest = new TileEntityChest(EnumChestType.strongbox, Block.chestSilver);
/* 17 */   private TileEntityChest goldChest = new TileEntityChest(EnumChestType.strongbox, Block.chestGold);
/* 18 */   private TileEntityChest ironChest = new TileEntityChest(EnumChestType.strongbox, Block.chestIron);
/* 19 */   private TileEntityChest mithrilChest = new TileEntityChest(EnumChestType.strongbox, Block.chestMithril);
/* 20 */   private TileEntityChest adamantiumChest = new TileEntityChest(EnumChestType.strongbox, Block.chestAdamantium);
/* 21 */   private TileEntityChest ancientMetalChest = new TileEntityChest(EnumChestType.strongbox, Block.chestAncientMetal);
/*    */ 
/*    */   
/* 24 */   private TileEntityEnderChest theEnderChest = new TileEntityEnderChest();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderChest(Block par1Block, int par2, float par3) {
/* 31 */     if (par1Block.blockID == Block.enderChest.blockID) {
/*    */       
/* 33 */       TileEntityRenderer.instance.renderTileEntityAt(this.theEnderChest, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 35 */     else if (par1Block.blockID == Block.chestTrapped.blockID) {
/*    */       
/* 37 */       TileEntityRenderer.instance.renderTileEntityAt(this.field_142033_c, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 39 */     else if (par1Block.blockID == Block.chestCopper.blockID) {
/*    */       
/* 41 */       TileEntityRenderer.instance.renderTileEntityAt(this.copperChest, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 43 */     else if (par1Block.blockID == Block.chestSilver.blockID) {
/*    */       
/* 45 */       TileEntityRenderer.instance.renderTileEntityAt(this.silverChest, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 47 */     else if (par1Block.blockID == Block.chestGold.blockID) {
/*    */       
/* 49 */       TileEntityRenderer.instance.renderTileEntityAt(this.goldChest, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 51 */     else if (par1Block.blockID == Block.chestIron.blockID) {
/*    */       
/* 53 */       TileEntityRenderer.instance.renderTileEntityAt(this.ironChest, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 55 */     else if (par1Block.blockID == Block.chestMithril.blockID) {
/*    */       
/* 57 */       TileEntityRenderer.instance.renderTileEntityAt(this.mithrilChest, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 59 */     else if (par1Block.blockID == Block.chestAdamantium.blockID) {
/*    */       
/* 61 */       TileEntityRenderer.instance.renderTileEntityAt(this.adamantiumChest, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 63 */     else if (par1Block.blockID == Block.chestAncientMetal.blockID) {
/*    */       
/* 65 */       TileEntityRenderer.instance.renderTileEntityAt(this.ancientMetalChest, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/*    */     else {
/*    */       
/* 69 */       TileEntityRenderer.instance.renderTileEntityAt(this.theChest, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChestItemRenderHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */