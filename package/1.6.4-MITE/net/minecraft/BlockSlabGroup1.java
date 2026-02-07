/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public final class BlockSlabGroup1
/*    */   extends BlockSlab
/*    */ {
/*  7 */   private static String[] types = new String[] { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick", "netherBrick", "quartz" };
/*    */   
/*    */   private static Block[] model_blocks;
/*    */   
/*    */   private Icon side_icon;
/*    */   
/*    */   public BlockSlabGroup1(int id, Material material) {
/* 14 */     super(id, material);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGroup() {
/* 19 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getTypes() {
/* 24 */     return types;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 29 */     return (metadata >= 0 && metadata < 16 && getBlockSubtypeUnchecked(metadata) != 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBlockSubtypeUnchecked(int metadata) {
/* 34 */     return metadata & 0x7;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 39 */     this.blockIcon = par1IconRegister.registerIcon("stone_slab_top");
/* 40 */     this.side_icon = par1IconRegister.registerIcon("stone_slab_side");
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getModelBlock(int metadata) {
/* 45 */     if (model_blocks == null) {
/* 46 */       model_blocks = new Block[] { stone, sandStone, planks, cobblestone, brick, stoneBrick, netherBrick, blockNetherQuartz };
/*    */     }
/* 48 */     return model_blocks[getBlockSubtype(metadata)];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIcon(int side, int metadata) {
/* 65 */     Block model_block = getModelBlock(metadata);
/*    */     
/* 67 */     if (model_block == stone) {
/* 68 */       return (side == 0 || side == 1) ? this.blockIcon : this.side_icon;
/*    */     }
/* 70 */     if (model_block == stoneBrick) {
/* 71 */       return model_block.getIcon(side, 0);
/*    */     }
/* 73 */     if (model_block == netherBrick) {
/* 74 */       side = 1;
/*    */     }
/* 76 */     return model_block.getBlockTextureFromSide(side);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSlabGroup1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */