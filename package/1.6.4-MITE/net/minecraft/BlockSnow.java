/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockSnow
/*     */   extends Block
/*     */ {
/*     */   protected BlockSnow(int par1) {
/*  13 */     super(par1, Material.snow, (new BlockConstants()).setNotAlwaysLegal());
/*  14 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/*  15 */     setTickRandomly(true);
/*  16 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  17 */     setBlockBoundsForSnowDepth(0, true);
/*     */     
/*  19 */     setMaxStackSize(32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  28 */     this.blockIcon = par1IconRegister.registerIcon("snow");
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
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/*  51 */     int depth = getDepth(world.getBlockMetadata(x, y, z));
/*     */     
/*  53 */     if (entity instanceof EntityItem) {
/*  54 */       depth++;
/*     */     }
/*  56 */     return (depth == 1) ? null : getStandardFormBoundingBoxFromPool(x, y, z).setMaxY(y + getRenderHeight(depth - 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public double getRenderHeight(int depth) {
/*  61 */     return depth * 0.125D;
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
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/*  91 */     setBlockBoundsForSnowDepth(item_damage, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  99 */     setBlockBoundsForSnowDepth(par1IBlockAccess.getBlockMetadata(par2, par3, par4), false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setBlockBoundsForSnowDepth(int par1, boolean for_all_threads) {
/* 107 */     int var2 = par1 & 0x7;
/* 108 */     float var3 = (2 * (1 + var2)) / 16.0F;
/* 109 */     setBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, var3, 1.0D, for_all_threads);
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
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 123 */     if (getDepth(metadata) == getMaxDepth()) {
/* 124 */       return Block.blockSnow.isLegalOn(metadata, block_below, block_below_metadata);
/*     */     }
/* 126 */     return (block_below != null && block_below.isTopFlatAndSolid(block_below_metadata));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDepthBits() {
/* 190 */     return 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDepth(int metadata) {
/* 195 */     return (metadata & getDepthBits()) + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int setDepth(int metadata, int depth) {
/* 200 */     return metadata & (getDepthBits() ^ 0xFFFFFFFF) | depth - 1 & getDepthBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMaxDepth() {
/* 205 */     return getDepthBits() + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canMelt(World world, int x, int y, int z) {
/* 210 */     Block block_above = Block.blocksList[world.getBlockId(x, y + 1, z)];
/*     */     
/* 212 */     return (block_above == null || (block_above.blockMaterial != Material.snow && block_above.blockMaterial != Material.craftedSnow && block_above.blockMaterial != Material.ice));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean melt(World world, int x, int y, int z) {
/* 217 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 219 */     if (metadata == 0) {
/* 220 */       return world.setBlockToAir(x, y, z);
/*     */     }
/* 222 */     return world.setBlockMetadataWithNotify(x, y, z, --metadata, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 231 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/* 232 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 240 */     if ((!par1World.isFreezing(par2, par4) || par1World.getSavedLightValue(EnumSkyBlock.Block, par2, par3 + 1, par4) > 11) && canMelt(par1World, par2, par3, par4)) {
/*     */       
/* 242 */       melt(par1World, par2, par3, par4);
/* 243 */       return true;
/*     */     } 
/*     */     
/* 246 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 255 */     return (par5 == 1) ? true : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
/* 260 */     if (world.isRemote) {
/*     */       return;
/*     */     }
/* 263 */     if (this == Block.snow)
/*     */     {
/* 265 */       if (world.isAirBlock(x, y - 1, z)) {
/*     */         
/* 267 */         dropBlockAsEntityItem((new BlockBreakInfo(world, x, y, z)).setDroppedSelf());
/* 268 */         world.setBlockToAir(x, y, z);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCushioning(int metadata) {
/* 276 */     return 0.2F * getDepth(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 281 */     if (info.wasFlooded()) {
/* 282 */       return 0;
/*     */     }
/* 284 */     if (info.wasNotLegal()) {
/* 285 */       info.world.destroyBlock(info, false, true);
/*     */     }
/* 287 */     if (!info.wasHarvestedByPlayer()) {
/* 288 */       return 0;
/*     */     }
/* 290 */     return dropBlockAsEntityItem(info, Item.snowball.itemID, 0, getDepth(info.getMetadata()), 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSlab(int metadata) {
/* 295 */     return (getDepth(metadata) == 4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 301 */     return ReferenceFileWriter.running ? (isSlab(metadata) ? "slab" : "thin") : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 311 */     if (other_block == null || other_block == this || other_block == blockSnow) {
/* 312 */       return false;
/*     */     }
/* 314 */     if (other_block.getBlockHardness(other_block_metadata) == 0.0F) {
/* 315 */       return false;
/*     */     }
/* 317 */     if (other_block.isLiquid()) {
/* 318 */       return (getDepth(metadata) < 8);
/*     */     }
/* 320 */     return (getDepth(metadata) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
/* 327 */     int depth = getDepth(metadata);
/*     */     
/* 329 */     return (depth == 1) ? false : ((face.isBottom() || depth == getMaxDepth()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockHardness(int metadata) {
/* 335 */     return BlockHardness.snow * getDepth(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryPlaceBlock(World world, int x, int y, int z, EnumFace face, int metadata, Entity placer, boolean perform_placement_check, boolean drop_existing_block, boolean test_only) {
/* 340 */     if (getDepth(metadata) == getMaxDepth()) {
/* 341 */       return blockSnow.tryPlaceBlock(world, x, y, z, face, 0, placer, perform_placement_check, drop_existing_block, test_only);
/*     */     }
/* 343 */     return super.tryPlaceBlock(world, x, y, z, face, metadata, placer, perform_placement_check, drop_existing_block, test_only);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/* 348 */     return "Bits 1, 2, and 4 used for snow depth";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/* 353 */     return (metadata >= 0 && metadata < 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemSubtype(int metadata) {
/* 358 */     if (!isValidMetadata(metadata)) {
/* 359 */       reportInvalidMetadata(metadata);
/*     */     }
/* 361 */     return isSlab(metadata) ? (metadata & getDepthBits()) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 368 */     if (side == 1) {
/* 369 */       return true;
/*     */     }
/* 371 */     if (side == 0 || neighbor != this) {
/* 372 */       return false;
/*     */     }
/* 374 */     EnumFace face = EnumFace.get(side).getOpposite();
/*     */ 
/*     */ 
/*     */     
/* 378 */     int neighbor_metadata = block_access.getBlockMetadata(face.getNeighborX(x), y, face.getNeighborZ(z));
/*     */     
/* 380 */     return (neighbor_metadata == 0 || neighbor_metadata <= block_access.getBlockMetadata(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 385 */     return (getDepth(metadata) > 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 390 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 395 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 400 */     return (getDepth(metadata) > 5);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean showDestructionParticlesWhenReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 405 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSnow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */