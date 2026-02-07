/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockCarpet
/*     */   extends Block
/*     */ {
/*     */   protected BlockCarpet(int par1) {
/*  11 */     super(par1, Material.materialCarpet, (new BlockConstants()).setNotAlwaysLegal());
/*  12 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
/*  13 */     setTickRandomly(true);
/*  14 */     setMaxStackSize(8);
/*  15 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  16 */     func_111047_d(0, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  24 */     return Block.cloth.getIcon(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/*  45 */     if (entity instanceof EntityItem) {
/*  46 */       return getStandardFormBoundingBoxFromPool(x, y, z).setMaxY(y + 0.0625D);
/*     */     }
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCollidingBoundsToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
/*  53 */     if (entity instanceof EntityItem) {
/*  54 */       addIntersectingBoundsToList((AxisAlignedBB)getCollisionBounds(world, x, y, z, entity), list, mask);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/*  80 */     func_111047_d(0, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  88 */     func_111047_d(par1IBlockAccess.getBlockMetadata(par2, par3, par4), false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_111047_d(int par1, boolean for_all_threads) {
/*  98 */     byte var2 = 0;
/*  99 */     float var3 = (1 * (1 + var2)) / 16.0F;
/* 100 */     setBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, var3, 1.0D, for_all_threads);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 147 */     return (block_below != null && block_below.isTopFlatAndSolid(block_below_metadata));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 156 */     return (par5 == 1) ? true : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/* 169 */     return "All bits used for color";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/* 174 */     return (metadata >= 0 && metadata < 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/* 179 */     return metadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 201 */     if (other_block == null) {
/* 202 */       return false;
/*     */     }
/* 204 */     if (other_block.isLiquid()) {
/* 205 */       return true;
/*     */     }
/* 207 */     if (other_block == this && getBlockSubtype(other_block_metadata) != getBlockSubtype(metadata)) {
/* 208 */       return true;
/*     */     }
/* 210 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 215 */     return (side == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 225 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 235 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockCarpet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */