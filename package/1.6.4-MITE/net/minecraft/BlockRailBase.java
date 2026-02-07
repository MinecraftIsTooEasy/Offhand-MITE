/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockRailBase
/*     */   extends Block
/*     */ {
/*     */   protected final boolean isPowered;
/*     */   
/*     */   public static final boolean isRailBlockAt(World par0World, int par1, int par2, int par3) {
/*  16 */     return isRailBlock(par0World.getBlockId(par1, par2, par3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean isRailBlock(int par0) {
/*  24 */     return (par0 == Block.rail.blockID || par0 == Block.railPowered.blockID || par0 == Block.railDetector.blockID || par0 == Block.railActivator.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockRailBase(int par1, boolean par2) {
/*  30 */     super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  31 */     this.isPowered = par2;
/*  32 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/*  33 */     setMaxStackSize(8);
/*  34 */     setCreativeTab(CreativeTabs.tabTransport);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/*  42 */     return this.isPowered;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  78 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */     
/*  80 */     if (var5 >= 2 && var5 <= 5) {
/*     */       
/*  82 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
/*     */     }
/*     */     else {
/*     */       
/*  86 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
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
/*     */   public int getRenderType() {
/* 103 */     return 9;
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
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 124 */     return (block_below != null && block_below.isTopFlatAndSolid(block_below_metadata));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 132 */     if (!par1World.isRemote) {
/*     */       
/* 134 */       refreshTrackShape(par1World, par2, par3, par4, true);
/*     */       
/* 136 */       if (this.isPowered)
/*     */       {
/* 138 */         onNeighborBlockChange(par1World, par2, par3, par4, this.blockID);
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 203 */     if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
/* 204 */       return true;
/*     */     }
/* 206 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 208 */     func_94358_a(world, x, y, z, metadata, this.isPowered ? (metadata & 0x7) : metadata, neighbor_block_id);
/*     */     
/* 210 */     return (world.getBlock(x, y, z) != this || world.getBlockMetadata(x, y, z) != metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/*     */     int var7;
/* 217 */     if (this.isPowered) {
/* 218 */       var7 = metadata & 0x7;
/*     */     } else {
/* 220 */       var7 = metadata;
/*     */     } 
/* 222 */     boolean var8 = false;
/*     */ 
/*     */ 
/*     */     
/* 226 */     if (var7 == 2 && !world.isBlockTopFlatAndSolid(x + 1, y, z)) {
/* 227 */       return false;
/*     */     }
/* 229 */     if (var7 == 3 && !world.isBlockTopFlatAndSolid(x - 1, y, z)) {
/* 230 */       return false;
/*     */     }
/* 232 */     if (var7 == 4 && !world.isBlockTopFlatAndSolid(x, y, z - 1)) {
/* 233 */       return false;
/*     */     }
/* 235 */     if (var7 == 5 && !world.isBlockTopFlatAndSolid(x, y, z + 1)) {
/* 236 */       return false;
/*     */     }
/* 238 */     return super.isLegalAt(world, x, y, z, metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_94358_a(World par1World, int par2, int par3, int par4, int par5, int par6, int par7) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void refreshTrackShape(World par1World, int par2, int par3, int par4, boolean par5) {
/* 248 */     if (!par1World.isRemote)
/*     */     {
/* 250 */       (new BlockBaseRailLogic(this, par1World, par2, par3, par4)).func_94511_a(par1World.isBlockIndirectlyGettingPowered(par2, par3, par4), par5);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMobilityFlag() {
/* 260 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 270 */     int var7 = par6;
/*     */     
/* 272 */     if (this.isPowered)
/*     */     {
/* 274 */       var7 = par6 & 0x7;
/*     */     }
/*     */     
/* 277 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*     */     
/* 279 */     if (var7 == 2 || var7 == 3 || var7 == 4 || var7 == 5)
/*     */     {
/* 281 */       par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, par5);
/*     */     }
/*     */     
/* 284 */     if (this.isPowered) {
/*     */       
/* 286 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4, par5);
/* 287 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, par5);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getExplosionResistance(Explosion explosion) {
/* 293 */     if (explosion.exploder instanceof EntityMinecartTNT) {
/* 294 */       return Float.MAX_VALUE;
/*     */     }
/* 296 */     return super.getExplosionResistance(explosion);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 301 */     EnumDirection direction = entity.getDirectionFromYaw();
/*     */     
/* 303 */     return direction.isNorthOrSouth() ? 0 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDislodgedOrCrushedByFallingBlock(int metadata, Block falling_block, int falling_block_metadata) {
/* 313 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 318 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 323 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 328 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRailBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */