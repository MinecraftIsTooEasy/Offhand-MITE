/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockGrass
/*     */   extends Block
/*     */ {
/*     */   private Icon iconGrassTop;
/*     */   private Icon iconSnowSide;
/*     */   private Icon iconGrassSideOverlay;
/*     */   
/*     */   protected BlockGrass(int par1) {
/*  16 */     super(par1, Material.grass, (new BlockConstants()).setNotAlwaysLegal());
/*  17 */     setTickRandomly(true);
/*  18 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */     
/*  20 */     setCushioning(0.2F);
/*  21 */     this.has_grass_top_icon = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  27 */     return "All bits used to track number of recent tramplings";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  32 */     return (metadata >= 0 && metadata < 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  40 */     return (par1 == 1) ? this.iconGrassTop : ((par1 == 0) ? Block.dirt.getBlockTextureFromSide(par1) : this.blockIcon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  48 */     if (par5 == 1)
/*     */     {
/*  50 */       return this.iconGrassTop;
/*     */     }
/*  52 */     if (par5 == 0)
/*     */     {
/*  54 */       return Block.dirt.getBlockTextureFromSide(par5);
/*     */     }
/*     */ 
/*     */     
/*  58 */     Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
/*  59 */     return (var6 != Material.snow && var6 != Material.craftedSnow) ? this.blockIcon : this.iconSnowSide;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  69 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/*  70 */     this.iconGrassTop = par1IconRegister.registerIcon(getTextureName() + "_top");
/*  71 */     this.iconSnowSide = par1IconRegister.registerIcon(getTextureName() + "_side_snowed");
/*  72 */     this.iconGrassSideOverlay = par1IconRegister.registerIcon(getTextureName() + "_side_overlay");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockColor() {
/*  77 */     double var1 = 0.5D;
/*  78 */     double var3 = 1.0D;
/*  79 */     return ColorizerGrass.getGrassColor(var1, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderColor(int par1) {
/*  87 */     return getBlockColor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  96 */     int var5 = 0;
/*  97 */     int var6 = 0;
/*  98 */     int var7 = 0;
/*     */     
/* 100 */     for (int var8 = -1; var8 <= 1; var8++) {
/*     */       
/* 102 */       for (int var9 = -1; var9 <= 1; var9++) {
/*     */         
/* 104 */         int var10 = par1IBlockAccess.getBiomeGenForCoords(par2 + var9, par4 + var8).getBiomeGrassColor();
/* 105 */         var5 += (var10 & 0xFF0000) >> 16;
/* 106 */         var6 += (var10 & 0xFF00) >> 8;
/* 107 */         var7 += var10 & 0xFF;
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     int r = var5 / 9 & 0xFF;
/* 112 */     int g = var6 / 9 & 0xFF;
/* 113 */     int b = var7 / 9 & 0xFF;
/*     */     
/* 115 */     float trampling_effect = getTramplingEffect(getTramplings(par1IBlockAccess.getBlockMetadata(par2, par3, par4)));
/*     */     
/* 117 */     if (trampling_effect > 0.0F) {
/*     */       
/* 119 */       float weight_grass = 1.0F - trampling_effect;
/*     */       
/* 121 */       r = (int)(r * weight_grass + 134.0F * trampling_effect);
/* 122 */       g = (int)(g * weight_grass + 96.0F * trampling_effect);
/* 123 */       b = (int)(b * weight_grass + 67.0F * trampling_effect);
/*     */     } 
/*     */ 
/*     */     
/* 127 */     return r << 16 | g << 8 | b;
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 185 */     int block_light_value = world.getBlockLightValue(x, y + 1, z);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     if (block_light_value < 4) {
/*     */       
/* 193 */       int block_above_id = world.getBlockId(x, y + 1, z);
/*     */       
/* 195 */       if (lightOpacity[block_above_id] > 2) {
/*     */         
/* 197 */         Block block_above = Block.getBlock(block_above_id);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 202 */         if (block_above != blockSnow && block_above != ice) {
/* 203 */           return world.setBlock(x, y, z, dirt.blockID);
/*     */         }
/*     */       } 
/* 206 */     } else if (block_light_value >= 13) {
/*     */       
/* 208 */       for (int attempts = 0; attempts < 4; attempts++) {
/*     */         
/* 210 */         int try_x = x + random.nextInt(3) - 1;
/* 211 */         int try_y = y + random.nextInt(5) - 3;
/* 212 */         int try_z = z + random.nextInt(3) - 1;
/*     */         
/* 214 */         if (isLegalAt(world, try_x, try_y, try_z, 0)) {
/*     */ 
/*     */           
/* 217 */           int block_above_id = world.getBlockId(try_x, try_y + 1, try_z);
/*     */           
/* 219 */           Block block = Block.blocksList[world.getBlockId(try_x, try_y, try_z)];
/*     */           
/* 221 */           if ((block == Block.dirt || block == Block.grass) && world.getBlockLightValue(try_x, try_y + 1, try_z) >= 4 && Block.lightOpacity[block_above_id] <= 2) {
/*     */             
/* 223 */             if (block == Block.grass) {
/*     */               
/* 225 */               if (((WorldServer)world).fast_forwarding) {
/* 226 */                 return false;
/*     */               }
/* 228 */               int metadata = world.getBlockMetadata(try_x, try_y, try_z);
/* 229 */               int tramplings = getTramplings(metadata);
/*     */               
/* 231 */               if (tramplings > 0) {
/* 232 */                 return world.setBlockMetadataWithNotify(try_x, try_y, try_z, setTramplings(metadata, --tramplings), 2);
/*     */               }
/* 234 */               return false;
/*     */             } 
/*     */             
/* 237 */             return world.setBlock(try_x, try_y, try_z, Block.grass.blockID);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 242 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 247 */     boolean changed = super.onNeighborBlockChange(world, x, y, z, neighbor_block_id);
/*     */     
/* 249 */     if (changed && world.getBlock(x, y, z) != this) {
/* 250 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 254 */     if (world.isLavaBlock(x, y + 1, z)) {
/*     */       
/* 256 */       world.blockFX(EnumBlockFX.lava_mixing_with_water, x, y, z);
/* 257 */       return (world.setBlock(x, y, z, Block.dirt.blockID) || changed);
/*     */     } 
/*     */     
/* 260 */     return changed;
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
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 273 */     Block block_above = world.getBlock(x, y + 1, z);
/*     */     
/* 275 */     if (block_above == null || block_above == leaves || block_above.blockMaterial == Material.snow || block_above.blockMaterial == Material.craftedSnow) {
/* 276 */       return true;
/*     */     }
/* 278 */     if (block_above instanceof BlockPistonBase) {
/* 279 */       return false;
/*     */     }
/* 281 */     if (!block_above.hidesAdjacentSide(world, x, y + 1, z, this, 1)) {
/* 282 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 287 */     return !block_above.isFaceFlatAndSolid(world.getBlockMetadata(x, y + 1, z), EnumFace.BOTTOM);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNotLegal(World world, int x, int y, int z, int metadata) {
/* 292 */     return world.setBlock(x, y, z, dirt.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 302 */     if (info.wasHarvestedByPlayer() && !info.world.isFreezing(info.x, info.z)) {
/*     */       
/* 304 */       int fortune = info.getHarvesterFortune();
/*     */       
/* 306 */       if (fortune > 3) {
/* 307 */         fortune = 3;
/*     */       }
/* 309 */       if (info.world.isInRain(info.x, info.y + 1, info.z)) {
/* 310 */         fortune += 12;
/*     */       }
/* 312 */       if (fortune > 14) {
/* 313 */         fortune = 14;
/*     */       }
/* 315 */       if (info.world.rand.nextInt(16 - fortune) == 0) {
/* 316 */         return dropBlockAsEntityItem(info, Item.wormRaw);
/*     */       }
/*     */     } 
/* 319 */     return dropBlockAsEntityItem(info, Block.dirt.blockID);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Icon getIconSideOverlay() {
/* 324 */     return Block.grass.iconGrassSideOverlay;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTrampledBy(World world, int x, int y, int z, Entity entity) {
/* 329 */     if (!world.isRemote && entity instanceof EntityLivestock) {
/*     */       
/* 331 */       int metadata = world.getBlockMetadata(x, y, z);
/* 332 */       int tramplings = getTramplings(metadata);
/*     */       
/* 334 */       if (tramplings < getTramplingBits()) {
/* 335 */         world.setBlockMetadataWithNotify(x, y, z, setTramplings(metadata, ++tramplings), 2);
/*     */       }
/* 337 */       float trampling_effect = getTramplingEffect(tramplings);
/*     */       
/* 339 */       if (entity instanceof EntityCow) {
/* 340 */         trampling_effect *= 2.0F;
/*     */       }
/* 342 */       if (trampling_effect >= 0.2F && Math.random() < (trampling_effect * 2.0F)) {
/*     */         
/* 344 */         Block block = Block.blocksList[world.getBlockId(x, y + 1, z)];
/*     */         
/* 346 */         if (block != null) {
/* 347 */           block.onTrampledBy(world, x, y + 1, z, entity);
/*     */         }
/*     */       } 
/*     */     } 
/* 351 */     onEntityWalking(world, x, y, z, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTramplingBits() {
/* 356 */     return 15;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTramplings(int metadata) {
/* 361 */     return metadata & getTramplingBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public int setTramplings(int metadata, int tramplings) {
/* 366 */     return metadata & (getTramplingBits() ^ 0xFFFFFFFF) | tramplings & getTramplingBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getTramplingEffect(int tramplings) {
/* 371 */     return MathHelper.clamp_float((tramplings - 3) * 0.05F, 0.0F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getAlternativeBlockForPlacement() {
/* 376 */     return dirt;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fertilize(World world, int x, int y, int z, ItemStack item_stack) {
/* 381 */     Item item = item_stack.getItem();
/*     */     
/* 383 */     if (item == Item.dyePowder) {
/*     */       
/* 385 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 387 */       Random itemRand = Item.itemRand;
/*     */       
/* 389 */       if (!world.isRemote) {
/*     */         
/* 391 */         (ItemDye)item; ItemDye.suppress_standard_particle_effect = true;
/*     */         
/* 393 */         for (int dx = -2; dx <= 2; dx++) {
/*     */           
/* 395 */           for (int dy = -1; dy <= 1; dy++) {
/*     */             
/* 397 */             for (int dz = -2; dz <= 2; dz++) {
/*     */               
/* 399 */               if (world.getBlock(x + dx, y + dy, z + dz) == Block.grass && world.isAirBlock(x + dx, y + dy + 1, z + dz)) {
/* 400 */                 world.playAuxSFX(2005, x + dx, y + dy + 1, z + dz, 1);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 406 */       if (!world.isRemote) {
/*     */         int var6;
/*     */ 
/*     */         
/* 410 */         label57: for (var6 = 0; var6 < 128; var6++) {
/*     */           
/* 412 */           int var7 = x;
/* 413 */           int var8 = y + 1;
/* 414 */           int var9 = z;
/*     */           
/* 416 */           for (int var10 = 0; var10 < var6 / 16; ) {
/*     */             
/* 418 */             var7 += itemRand.nextInt(3) - 1;
/* 419 */             var8 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
/* 420 */             var9 += itemRand.nextInt(3) - 1;
/*     */             
/* 422 */             if (world.getBlockId(var7, var8 - 1, var9) == Block.grass.blockID) { if (world.isBlockNormalCube(var7, var8, var9))
/*     */                 continue label57; 
/*     */               var10++; }
/*     */             
/*     */             continue label57;
/*     */           } 
/* 428 */           if (world.getBlockId(var7, var8, var9) == 0 && itemRand.nextInt(2) == 0)
/*     */           {
/* 430 */             if (itemRand.nextInt(10) != 0) {
/*     */ 
/*     */               
/* 433 */               if (Block.tallGrass.isLegalAt(world, var7, var8, var9, 1))
/*     */               {
/* 435 */                 world.setBlock(var7, var8, var9, Block.tallGrass.blockID, 1, 3);
/*     */               
/*     */               }
/*     */             }
/* 439 */             else if (itemRand.nextInt(3) != 0) {
/*     */ 
/*     */               
/* 442 */               if (Block.plantYellow.isLegalAt(world, var7, var8, var9, 0))
/*     */               {
/* 444 */                 world.setBlock(var7, var8, var9, Block.plantYellow.blockID);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */             else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 464 */               int subtype = Block.plantRed.getRandomSubtypeThatCanOccurAt(world, var7, var8, var9);
/*     */               
/* 466 */               if (subtype == 2 && itemRand.nextFloat() < 0.8F) {
/* 467 */                 subtype = -1;
/*     */               }
/* 469 */               if (subtype >= 0) {
/* 470 */                 world.setBlock(var7, var8, var9, Block.plantRed.blockID, subtype, 3);
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/* 476 */       return true;
/*     */     } 
/*     */     
/* 479 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockGrass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */