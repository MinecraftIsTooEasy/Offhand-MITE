/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockFarmland
/*     */   extends Block
/*     */ {
/*     */   private Icon[] icon_array;
/*     */   
/*     */   protected BlockFarmland(int par1) {
/*  16 */     super(par1, Material.dirt, (new BlockConstants()).setNotAlwaysLegal());
/*  17 */     setTickRandomly(true);
/*  18 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
/*  19 */     setLightOpacity(255);
/*     */     
/*  21 */     setCushioning(0.4F);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  26 */     return "Bits 1, 2, and 4 used for wetness, bit 8 set if fertilized";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  31 */     return (metadata >= 0 && metadata < 16);
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
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/*  45 */     return getStandardFormBoundingBoxFromPool(x, y, z);
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
/*     */   public Icon getIcon(int side, int metadata) {
/*  75 */     if (side == 1) {
/*  76 */       return this.icon_array[((getWetness(metadata) > 0) ? 2 : 0) + (isFertilized(metadata) ? 1 : 0)];
/*     */     }
/*  78 */     return Block.dirt.getBlockTextureFromSide(side);
/*     */   }
/*     */ 
/*     */   
/*     */   public float chanceOfDrying(World world, int x, int z) {
/*  83 */     BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
/*     */     
/*  85 */     float chance = biome.temperature - 0.15F - (biome.isHighHumidity() ? 0.5F : 0.0F);
/*     */     
/*  87 */     if (chance < 0.0F) {
/*  88 */       chance = 0.0F;
/*     */     }
/*  90 */     return chance;
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random rand) {
/* 119 */     if (super.updateTick(world, x, y, z, rand)) {
/* 120 */       return true;
/*     */     }
/* 122 */     if (isWaterNearby(world, x, y, z)) {
/* 123 */       return water(world, x, y, z);
/*     */     }
/* 125 */     int metadata = world.getBlockMetadata(x, y, z);
/* 126 */     int wetness = getWetness(metadata);
/*     */     
/* 128 */     if (world.canLightningStrikeAt(x, y + 1, z)) {
/*     */       
/* 130 */       if (wetness == getWetnessBits()) {
/* 131 */         return false;
/*     */       }
/* 133 */       return world.setBlockMetadataWithNotify(x, y, z, setWetness(metadata, ++wetness), 3);
/*     */     } 
/*     */ 
/*     */     
/* 137 */     if (rand.nextFloat() >= chanceOfDrying(world, x, z)) {
/* 138 */       return false;
/*     */     }
/* 140 */     if (wetness > 0)
/* 141 */       return world.setBlockMetadataWithNotify(x, y, z, setWetness(metadata, wetness - 1), 2); 
/* 142 */     if (world.isAirBlock(x, y + 1, z) && !isCropsNearby(world, x, y, z)) {
/* 143 */       return world.setBlock(x, y, z, Block.dirt.blockID);
/*     */     }
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6) {
/* 154 */     if (par5Entity instanceof EntityGelatinousCube) {
/*     */       return;
/*     */     }
/* 157 */     if (!par1World.isRemote && par1World.rand.nextFloat() < par6 - 0.5F) {
/*     */       
/* 159 */       Block block_above = par1World.getBlock(par2, par3 + 1, par4);
/*     */       
/* 161 */       if (block_above instanceof BlockPlant) {
/*     */         
/* 163 */         BlockPlant plant = (BlockPlant)block_above;
/* 164 */         plant.onTrampledBy(par1World, par2, par3 + 1, par4, par5Entity);
/*     */       } 
/*     */       
/* 167 */       int metadata = par1World.getBlockMetadata(par2, par3, par4);
/*     */       
/* 169 */       if (getWetness(metadata) > 0 || isFertilized(metadata)) {
/*     */         return;
/*     */       }
/* 172 */       if (!(par5Entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 177 */       par1World.setBlock(par2, par3, par4, Block.dirt.blockID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isCropsNearby(World par1World, int par2, int par3, int par4) {
/* 187 */     byte var5 = 1;
/*     */     
/* 189 */     for (int var6 = par2 - var5; var6 <= par2 + var5; var6++) {
/*     */       
/* 191 */       for (int var7 = par4 - var5; var7 <= par4 + var5; var7++) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 200 */         Block block = Block.blocksList[par1World.getBlockId(var6, par3 + 1, var7)];
/*     */         
/* 202 */         if (block instanceof BlockCrops || block instanceof BlockStem) {
/* 203 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWaterNearby(World par1World, int par2, int par3, int par4) {
/* 216 */     for (int var5 = par2 - 4; var5 <= par2 + 4; var5++) {
/*     */       
/* 218 */       for (int var6 = par3; var6 <= par3 + 1; var6++) {
/*     */         
/* 220 */         for (int var7 = par4 - 4; var7 <= par4 + 4; var7++) {
/*     */           
/* 222 */           if (par1World.getBlockMaterial(var5, var6, var7) == Material.water)
/*     */           {
/* 224 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 230 */     for (int dx = -1; dx <= 1; dx++) {
/*     */       
/* 232 */       for (int dy = 0; dy <= 1; dy++) {
/*     */         
/* 234 */         for (int dz = -1; dz <= 1; dz++) {
/*     */           
/* 236 */           Block block = Block.blocksList[par1World.getBlockId(par2 + dx, par3 + dy, par4 + dz)];
/*     */           
/* 238 */           if (block != null && (block.blockMaterial == Material.snow || block.blockMaterial == Material.craftedSnow || block.blockMaterial == Material.ice)) {
/* 239 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 244 */     return false;
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
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 266 */     Block block_above = world.getBlockWithRefreshedBounds(x, y + 1, z);
/*     */     
/* 268 */     if (block_above instanceof BlockBush) {
/* 269 */       return false;
/*     */     }
/* 271 */     return (block_above == null || block_above instanceof BlockGrowingPlant || block_above == Block.mushroomBrown || block_above.minY[Minecraft.getThreadIndex()] > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNotLegal(World world, int x, int y, int z, int metadata) {
/* 276 */     return world.setBlock(x, y, z, dirt.blockID);
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
/*     */   public boolean canBeCarried() {
/* 289 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 294 */     return dropBlockAsEntityItem(info, Block.dirt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 302 */     return Block.dirt.blockID;
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 317 */     this.icon_array = new Icon[4];
/*     */     
/* 319 */     this.icon_array[0] = par1IconRegister.registerIcon(getTextureName() + "_dry");
/* 320 */     this.icon_array[1] = par1IconRegister.registerIcon(getTextureName() + "_dry_fertilized");
/* 321 */     this.icon_array[2] = par1IconRegister.registerIcon(getTextureName() + "_wet");
/* 322 */     this.icon_array[3] = par1IconRegister.registerIcon(getTextureName() + "_wet_fertilized");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getWetnessBits() {
/* 327 */     return 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getWetness(int metadata) {
/* 332 */     return metadata & getWetnessBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int setWetness(int metadata, int wetness) {
/* 337 */     return metadata & (getWetnessBits() ^ 0xFFFFFFFF) | wetness & getWetnessBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFertilizedBit() {
/* 342 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFertilized(int metadata) {
/* 347 */     return ((metadata & getFertilizedBit()) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int setFertilized(int metadata, boolean fertilized) {
/* 352 */     return fertilized ? (metadata | getFertilizedBit()) : (metadata & (getFertilizedBit() ^ 0xFFFFFFFF));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setFertilized(World world, int x, int y, int z, boolean fertilized) {
/* 357 */     if (fertilized) {
/* 358 */       world.blockFX(EnumBlockFX.manure, x, y, z);
/*     */     }
/* 360 */     world.setBlockMetadataWithNotify(x, y, z, setFertilized(world.getBlockMetadata(x, y, z), fertilized), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean water(World world, int x, int y, int z) {
/* 365 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 367 */     if (getWetness(metadata) < 7) {
/*     */       
/* 369 */       if (!world.isRemote) {
/* 370 */         world.setBlockMetadataWithNotify(x, y, z, setWetness(metadata, 7), 2);
/*     */       }
/* 372 */       return true;
/*     */     } 
/*     */     
/* 375 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean fertilize(World world, int x, int y, int z, ItemStack item_stack, EntityPlayer player) {
/* 380 */     Item item = item_stack.getItem();
/*     */     
/* 382 */     if (item == Item.manure) {
/*     */       
/* 384 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 386 */       if (!isFertilized(metadata))
/*     */       {
/* 388 */         if (!world.isRemote) {
/*     */ 
/*     */           
/* 391 */           setFertilized(world, x, y, z, true);
/*     */ 
/*     */ 
/*     */           
/* 395 */           if (player != null) {
/* 396 */             player.triggerAchievement(AchievementList.soilEnrichment);
/*     */           }
/* 398 */           checkForMyceliumConditions(world, x, y, z, player);
/*     */         } 
/*     */         
/* 401 */         return true;
/*     */       }
/*     */     
/* 404 */     } else if (item == Item.dyePowder) {
/*     */       
/* 406 */       Block block = Block.blocksList[world.getBlockId(x, y + 1, z)];
/*     */       
/* 408 */       if (block instanceof BlockCrops)
/* 409 */         return ((BlockCrops)block).fertilize(world, x, y + 1, z, item_stack); 
/* 410 */       if (block instanceof BlockStem)
/* 411 */         return ((BlockStem)block).fertilizeStem(world, x, y + 1, z, item_stack); 
/*     */     } else {
/* 413 */       if (item == Item.bowlWater || ItemPotion.isBottleOfWater(item_stack)) {
/*     */         
/* 415 */         if (world.provider.isHellWorld) {
/*     */           
/* 417 */           if (!world.isRemote) {
/* 418 */             world.blockFX(EnumBlockFX.water_evaporation_in_hell, x, y + 1, z);
/*     */           }
/* 420 */           return true;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 425 */         if (water(world, x, y, z)) {
/*     */           
/* 427 */           checkForMyceliumConditions(world, x, y, z, player);
/* 428 */           return true;
/*     */         } 
/*     */         
/* 431 */         return false;
/*     */       } 
/*     */       
/* 434 */       if (item instanceof ItemBucket && ((ItemBucket)item).contains(Material.water)) {
/*     */         
/* 436 */         if (world.provider.isHellWorld) {
/*     */           
/* 438 */           if (!world.isRemote) {
/* 439 */             world.blockFX(EnumBlockFX.water_evaporation_in_hell, x, y + 1, z);
/*     */           }
/* 441 */           return true;
/*     */         } 
/*     */         
/* 444 */         boolean used = false;
/*     */         
/* 446 */         for (int dx = -1; dx <= 1; dx++) {
/*     */           
/* 448 */           for (int dz = -1; dz <= 1; dz++) {
/*     */             
/* 450 */             if (world.getBlockId(x + dx, y, z + dz) == Block.tilledField.blockID) {
/*     */ 
/*     */               
/* 453 */               int metadata = world.getBlockMetadata(x + dx, y, z + dz);
/*     */               
/* 455 */               if (getWetness(metadata) < 7) {
/*     */                 
/* 457 */                 if (!world.isRemote) {
/*     */                   
/* 459 */                   world.setBlockMetadataWithNotify(x + dx, y, z + dz, setWetness(metadata, 7), 2);
/*     */                   
/* 461 */                   checkForMyceliumConditions(world, x + dx, y, z + dz, player);
/*     */                 } 
/*     */                 
/* 464 */                 used = true;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 469 */         return used;
/*     */       } 
/*     */     } 
/* 472 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World world, int x, int y, int z) {
/* 477 */     if (isWaterNearby(world, x, y, z)) {
/* 478 */       water(world, x, y, z);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 483 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 488 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
/* 493 */     return face.isBottom();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkForMyceliumConditions(World world, int x, int y, int z, EntityPlayer player) {
/* 499 */     if (mushroomBrown.canConvertBlockBelowToMycelium(world, x, y + 1, z)) {
/*     */       
/* 501 */       if (player != null) {
/* 502 */         player.triggerAchievement(AchievementList.makeMycelium);
/*     */       }
/* 504 */       return true;
/*     */     } 
/*     */     
/* 507 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFarmland.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */