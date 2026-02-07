/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockReed
/*     */   extends Block
/*     */ {
/*     */   protected BlockReed(int par1) {
/*  11 */     super(par1, Material.plants, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  12 */     float var2 = 0.375F;
/*  13 */     setBlockBoundsForAllThreads((0.5F - var2), 0.0D, (0.5F - var2), (0.5F + var2), 1.0D, (0.5F + var2));
/*  14 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  19 */     return "All bits used to track growth";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  24 */     return (metadata >= 0 && metadata < 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  33 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/*  34 */       return true;
/*     */     }
/*  36 */     if (par5Random.nextFloat() > (par1World.getBiomeGenForCoords(par2, par4)).temperature - 0.2F) {
/*  37 */       return false;
/*     */     }
/*  39 */     if (par5Random.nextFloat() < 0.8F) {
/*  40 */       return false;
/*     */     }
/*  42 */     if (par1World.getBlockLightValue(par2, par3, par4) < 15) {
/*  43 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     if (canOccurAt(par1World, par2, par3 + 1, par4, 0)) {
/*     */       
/*  77 */       int metadata = par1World.getBlockMetadata(par2, par3, par4);
/*     */       
/*  79 */       if (++metadata == 16) {
/*     */         
/*  81 */         par1World.setBlock(par2, par3 + 1, par4, this.blockID);
/*  82 */         metadata = 0;
/*     */       } 
/*     */       
/*  85 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, metadata, 4);
/*     */       
/*  87 */       return true;
/*     */     } 
/*     */     
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canOccurAt(World world, int x, int y, int z, int metadata) {
/*  95 */     return (world.canBlockSeeTheSky(x, y + 1, z) && super.canOccurAt(world, x, y, z, metadata));
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
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 140 */     if (!super.isLegalAt(world, x, y, z, metadata)) {
/* 141 */       return false;
/*     */     }
/* 143 */     if (world.getBlock(x, y - 1, z) == this) {
/*     */       
/* 145 */       y--;
/*     */       
/* 147 */       int height = 1;
/*     */       
/* 149 */       while (world.getBlock(x, --y, z) == this) {
/* 150 */         height++;
/*     */       }
/* 152 */       return (height < 3);
/*     */     } 
/*     */ 
/*     */     
/* 156 */     y--;
/*     */     
/* 158 */     return (world.getNeighborBlockMaterial(x, y, z, EnumFace.NORTH) == Material.water || world.getNeighborBlockMaterial(x, y, z, EnumFace.EAST) == Material.water || world.getNeighborBlockMaterial(x, y, z, EnumFace.SOUTH) == Material.water || world.getNeighborBlockMaterial(x, y, z, EnumFace.WEST) == Material.water);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 165 */     if (block_below == this) {
/* 166 */       return true;
/*     */     }
/* 168 */     return (block_below == grass || block_below == dirt || block_below == sand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePlacedOnBlock(int metadata, Block block_below, int block_below_metadata, double block_below_bounds_max_y) {
/* 173 */     return (block_below == this || super.canBePlacedOnBlock(metadata, block_below, block_below_metadata, block_below_bounds_max_y));
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
/*     */   public boolean canBeCarried() {
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 200 */     if (info.wasNotLegal() || info.wasExploded()) {
/* 201 */       info.world.destroyBlock(info, false, true);
/*     */     }
/* 203 */     if (info.wasExploded() || info.wasCrushed()) {
/* 204 */       return 0;
/*     */     }
/* 206 */     return dropBlockAsEntityItem(info, Item.reed);
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
/*     */   public int getRenderType() {
/* 231 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 239 */     return Item.reed.itemID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 254 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockReed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */