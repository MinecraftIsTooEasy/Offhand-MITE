/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockCactus
/*     */   extends Block
/*     */ {
/*     */   private Icon cactusTopIcon;
/*     */   private Icon cactusBottomIcon;
/*     */   
/*     */   protected BlockCactus(int par1) {
/*  15 */     super(par1, Material.cactus, (new BlockConstants()).setNotAlwaysLegal());
/*  16 */     setTickRandomly(true);
/*  17 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  22 */     return "All bits used to track growth";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  27 */     return (metadata >= 0 && metadata < 16);
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
/*     */   public static int getMaxHeight(int x, int y, int z) {
/*  71 */     return RNG.int_3[x + y * 501 + z * 9043 & 0x7FFF] + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  76 */     if (super.updateTick(world, x, y, z, random)) {
/*  77 */       return true;
/*     */     }
/*  79 */     if (world.getBlock(x, y + 1, z) == cactus) {
/*  80 */       return false;
/*     */     }
/*  82 */     if (world.rand.nextFloat() < 0.5F) {
/*  83 */       return tryDecrementKillCount(world, x, y, z);
/*     */     }
/*  85 */     if (random.nextFloat() < 0.9F) {
/*  86 */       return false;
/*     */     }
/*     */     
/*  89 */     if (!world.getBiomeGenForCoords(x, z).isDesertBiome()) {
/*     */       
/*  91 */       dropBlockAsEntityItem((new BlockBreakInfo(world, x, y, z)).setDroppedSelf());
/*  92 */       world.setBlockToAir(x, y, z);
/*     */       
/*  94 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  98 */     if (world.isAirBlock(x, y + 1, z) && isLegalAt(world, x, y + 1, z, 0)) {
/*     */       int cactus_height;
/*     */ 
/*     */       
/* 102 */       for (cactus_height = 1; world.getBlockId(x, y - cactus_height, z) == this.blockID; cactus_height++);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 107 */       int max_height = getMaxHeight(x, y - cactus_height, z);
/*     */ 
/*     */       
/* 110 */       if (cactus_height < max_height) {
/*     */         
/* 112 */         int metadata = world.getBlockMetadata(x, y, z);
/*     */         
/* 114 */         if (metadata == 15) {
/*     */           
/* 116 */           world.setBlock(x, y + 1, z, this.blockID);
/* 117 */           world.setBlockMetadataWithNotify(x, y, z, 0, 4);
/* 118 */           onNeighborBlockChange(world, x, y + 1, z, this.blockID);
/*     */           
/* 120 */           return true;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         return world.setBlockMetadataWithNotify(x, y, z, metadata + 1, 4);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 132 */     return false;
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
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/* 147 */     float var5 = 0.0625F;
/* 148 */     return AxisAlignedBB.getAABBPool().getAABB((x + var5), y, (z + var5), ((x + 1) - var5), ((y + 1) - var5), ((z + 1) - var5));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
/* 156 */     float var5 = 0.0625F;
/* 157 */     return AxisAlignedBB.getAABBPool().getAABB((par2 + var5), par3, (par4 + var5), ((par2 + 1) - var5), (par3 + 1), ((par4 + 1) - var5));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 165 */     return (par1 == 1) ? this.cactusTopIcon : ((par1 == 0) ? this.cactusBottomIcon : this.blockIcon);
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
/* 190 */     return 13;
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
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 246 */     if (world.getNeighborBlockMaterial(x, y, z, EnumFace.WEST).isSolid() || world.getNeighborBlockMaterial(x, y, z, EnumFace.EAST).isSolid() || world.getNeighborBlockMaterial(x, y, z, EnumFace.NORTH).isSolid() || world.getNeighborBlockMaterial(x, y, z, EnumFace.SOUTH).isSolid()) {
/* 247 */       return false;
/*     */     }
/* 249 */     int new_height = 1;
/* 250 */     int dy = 0;
/*     */     
/* 252 */     while (world.getBlockId(x, y + --dy, z) == this.blockID) {
/* 253 */       new_height++;
/*     */     }
/* 255 */     if (new_height > 3) {
/* 256 */       return false;
/*     */     }
/* 258 */     return super.isLegalAt(world, x, y, z, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 263 */     return (block_below == cactus || block_below == sand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNotLegal(World world, int x, int y, int z, int metadata) {
/* 268 */     if ((world.getChunkFromBlockCoords(x, z)).last_total_world_time == 0L) {
/*     */       
/* 270 */       world.setBlockToAir(x, y, z);
/* 271 */       return true;
/*     */     } 
/*     */     
/* 274 */     return super.onNotLegal(world, x, y, z, metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 284 */     if (par1World.isWorldServer()) {
/*     */       
/* 286 */       if (par5Entity.isEntityLiving()) {
/*     */ 
/*     */         
/* 289 */         EntityLiving entity_living = par5Entity.getAsEntityLiving();
/*     */         
/* 291 */         boolean is_moving_recklessly = (entity_living.getCurrentSpeed() > 0.20000000298023224D);
/*     */         
/* 293 */         if (!is_moving_recklessly) {
/*     */ 
/*     */           
/* 296 */           BlockInfo info = entity_living.getBlockRestingOn3();
/*     */           
/* 298 */           boolean is_standing_on_cactus = (info != null && info.block == this);
/*     */           
/* 300 */           if (!is_standing_on_cactus && !entity_living.is_collided_with_entities && entity_living.getCollidingBlockBounds().size() == 0) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       } 
/* 305 */       EntityDamageResult result = par5Entity.attackEntityFrom(new Damage(DamageSource.cactus.setBlock(par1World, par2, par3, par4), 1.0F));
/*     */       
/* 307 */       if (result != null && result.entityWasDestroyed()) {
/* 308 */         tryIncrementKillCount(par1World, par2, par3, par4);
/*     */       }
/* 310 */       if (par5Entity instanceof EntityLiving && result != null && result.entityWasNegativelyAffected()) {
/* 311 */         (par5Entity.getAsEntityLiving()).last_tick_harmed_by_cactus = par1World.getTotalWorldTime();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int getKillCountBits() {
/* 317 */     return 7;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getKillCount(int metadata) {
/* 322 */     return metadata & getKillCountBits();
/*     */   }
/*     */ 
/*     */   
/*     */   private static int setKillCount(int metadata, int kills) {
/* 327 */     return metadata & (getKillCountBits() ^ 0xFFFFFFFF) | kills & getKillCountBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getKillCount(World world, int x, int y, int z) {
/* 332 */     y = getYCoordOfSandBeneath(world, x, y, z);
/*     */     
/* 334 */     if (y < 0) {
/* 335 */       return 0;
/*     */     }
/* 337 */     return getKillCount(world.getBlockMetadata(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void tryIncrementKillCount(World world, int x, int y, int z) {
/* 342 */     y = getYCoordOfSandBeneath(world, x, y, z);
/*     */     
/* 344 */     if (y < 0) {
/*     */       return;
/*     */     }
/* 347 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 349 */     if (getKillCount(metadata) < getKillCountBits()) {
/* 350 */       world.setBlockMetadataWithNotify(x, y, z, setKillCount(metadata, getKillCount(metadata) + 1), 4);
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean tryDecrementKillCount(World world, int x, int y, int z) {
/* 355 */     y = getYCoordOfSandBeneath(world, x, y, z);
/*     */     
/* 357 */     if (y < 0) {
/* 358 */       return false;
/*     */     }
/* 360 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 362 */     if (getKillCount(metadata) > 0) {
/* 363 */       return world.setBlockMetadataWithNotify(x, y, z, setKillCount(metadata, getKillCount(metadata) - 1), 4);
/*     */     }
/* 365 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getYCoordOfSandBeneath(World world, int x, int y, int z) {
/*     */     Block block;
/* 372 */     while ((block = world.getBlock(x, --y, z)) == Block.cactus);
/*     */     
/* 374 */     return (block == sand) ? y : -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPreDestroy(World world, int x, int y, int z, int old_metadata) {
/* 379 */     if (world.getBlock(x, --y, z) == sand) {
/* 380 */       world.setBlockMetadataWithNotify(x, y, z, 0, 4);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 389 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/* 390 */     this.cactusTopIcon = par1IconRegister.registerIcon(getTextureName() + "_top");
/* 391 */     this.cactusBottomIcon = par1IconRegister.registerIcon(getTextureName() + "_bottom");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 396 */     return ((side == 0 || side == 1) && neighbor == this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 401 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 406 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockCactus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */