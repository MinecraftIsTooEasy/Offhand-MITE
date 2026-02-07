/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockStem
/*     */   extends BlockGrowingPlant
/*     */ {
/*     */   private final Block fruitType;
/*     */   private Icon theIcon;
/*     */   
/*     */   protected BlockStem(int par1, Block par2Block) {
/*  14 */     super(par1);
/*  15 */     this.fruitType = par2Block;
/*  16 */     setTickRandomly(true);
/*  17 */     float var3 = 0.125F;
/*  18 */     setBlockBoundsForAllThreads((0.5F - var3), 0.0D, (0.5F - var3), (0.5F + var3), 0.25D, (0.5F + var3));
/*  19 */     setCreativeTab((CreativeTabs)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  24 */     return "Bits 1, 2, and 4 used to track growth, bit 8 set if stem is dead";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  29 */     return (metadata >= 0 && metadata < 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getLowestOptimalTemperature() {
/*  34 */     return (this.fruitType == Block.pumpkin) ? 0.6F : 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHighestOptimalTemperature() {
/*  39 */     return (this.fruitType == Block.pumpkin) ? 1.0F : 1.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHumidityGrowthRateModifier(boolean high_humidity) {
/*  44 */     return (this.fruitType == Block.melon && high_humidity) ? 1.2F : 1.0F;
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
/*  58 */     return (block_below == tilledField);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  69 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/*  70 */       return true;
/*     */     }
/*  72 */     int metadata = par1World.getBlockMetadata(par2, par3, par4);
/*     */     
/*  74 */     if (isDead(metadata)) {
/*  75 */       return false;
/*     */     }
/*  77 */     boolean state_changed = false;
/*     */     
/*  79 */     float growth_modifier = getGrowthRate(par1World, par2, par3, par4);
/*     */     
/*  81 */     if (growth_modifier == 0.0F && !BlockFarmland.isWaterNearby(par1World, par2, par3 - 1, par4)) {
/*     */       
/*  83 */       if (par5Random.nextFloat() < 0.05F) {
/*  84 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, setDead(metadata, true), 2);
/*     */       }
/*  86 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  90 */     if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= getMinAllowedLightValueForGrowth()) {
/*     */ 
/*     */       
/*  93 */       float var6 = growth_modifier;
/*     */       
/*  95 */       if (var6 == 0.0F) {
/*  96 */         return false;
/*     */       }
/*  98 */       if (par5Random.nextInt((int)(25.0F / var6) + 1) == 0) {
/*     */ 
/*     */         
/* 101 */         int var7 = getGrowth(metadata);
/*     */         
/* 103 */         if (var7 < 7) {
/*     */           
/* 105 */           var7++;
/* 106 */           par1World.setBlockMetadataWithNotify(par2, par3, par4, var7, 2);
/* 107 */           state_changed = true;
/*     */         }
/*     */         else {
/*     */           
/* 111 */           if (par5Random.nextInt(2) == 0) {
/* 112 */             return false;
/*     */           }
/* 114 */           if (par1World.getBlockId(par2 - 1, par3, par4) == this.fruitType.blockID)
/*     */           {
/*     */             
/* 117 */             return false;
/*     */           }
/*     */           
/* 120 */           if (par1World.getBlockId(par2 + 1, par3, par4) == this.fruitType.blockID)
/*     */           {
/*     */             
/* 123 */             return false;
/*     */           }
/*     */           
/* 126 */           if (par1World.getBlockId(par2, par3, par4 - 1) == this.fruitType.blockID)
/*     */           {
/*     */             
/* 129 */             return false;
/*     */           }
/*     */           
/* 132 */           if (par1World.getBlockId(par2, par3, par4 + 1) == this.fruitType.blockID)
/*     */           {
/*     */             
/* 135 */             return false;
/*     */           }
/*     */           
/* 138 */           int var8 = par5Random.nextInt(4);
/* 139 */           int var9 = par2;
/* 140 */           int var10 = par4;
/*     */           
/* 142 */           if (var8 == 0)
/*     */           {
/* 144 */             var9 = par2 - 1;
/*     */           }
/*     */           
/* 147 */           if (var8 == 1)
/*     */           {
/* 149 */             var9++;
/*     */           }
/*     */           
/* 152 */           if (var8 == 2)
/*     */           {
/* 154 */             var10 = par4 - 1;
/*     */           }
/*     */           
/* 157 */           if (var8 == 3)
/*     */           {
/* 159 */             var10++;
/*     */           }
/*     */           
/* 162 */           int var11 = par1World.getBlockId(var9, par3 - 1, var10);
/*     */           
/* 164 */           if (par1World.getBlockId(var9, par3, var10) == 0 && (var11 == Block.tilledField.blockID || var11 == Block.dirt.blockID || var11 == Block.grass.blockID)) {
/*     */             
/* 166 */             par1World.setBlock(var9, par3, var10, this.fruitType.blockID);
/*     */             
/* 168 */             if (par5Random.nextFloat() < 0.1F) {
/*     */               
/* 170 */               par1World.setBlockMetadataWithNotify(par2, par3, par4, setDead(metadata, true), 2);
/* 171 */               state_changed = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 178 */         if (par5Random.nextInt(256) == 0 && par1World.getBlockId(par2, par3 - 1, par4) == Block.tilledField.blockID) {
/*     */           
/* 180 */           metadata = par1World.getBlockMetadata(par2, par3 - 1, par4);
/*     */           
/* 182 */           if (BlockFarmland.isFertilized(metadata)) {
/* 183 */             par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, BlockFarmland.setFertilized(metadata, false), 2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 188 */     return state_changed;
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
/*     */   public boolean fertilizeStem(World world, int x, int y, int z, ItemStack item_stack) {
/* 206 */     if (item_stack.getItem() == Item.dyePowder) {
/*     */       
/* 208 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 210 */       if (isDead(metadata)) {
/* 211 */         return false;
/*     */       }
/* 213 */       int growth = getGrowth(metadata);
/*     */       
/* 215 */       if (growth == 7 || getGrowthRate(world, x, y, z) == 0.0F) {
/* 216 */         return false;
/*     */       }
/* 218 */       if (!world.isRemote) {
/*     */         
/* 220 */         growth += MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
/*     */         
/* 222 */         if (growth > 7) {
/* 223 */           growth = 7;
/*     */         }
/* 225 */         world.setBlockMetadataWithNotify(x, y, z, setGrowth(metadata, growth), 2);
/*     */       } 
/*     */       
/* 228 */       return true;
/*     */     } 
/*     */     
/* 231 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getGrowthRate(World par1World, int par2, int par3, int par4) {
/* 237 */     Block block_below = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
/* 238 */     int block_below_metadata = par1World.getBlockMetadata(par2, par3 - 1, par4);
/*     */     
/* 240 */     if (block_below == Block.tilledField && BlockFarmland.getWetness(block_below_metadata) == 0) {
/* 241 */       return 0.0F;
/*     */     }
/* 243 */     float var5 = 1.0F;
/* 244 */     int var6 = par1World.getBlockId(par2, par3, par4 - 1);
/* 245 */     int var7 = par1World.getBlockId(par2, par3, par4 + 1);
/* 246 */     int var8 = par1World.getBlockId(par2 - 1, par3, par4);
/* 247 */     int var9 = par1World.getBlockId(par2 + 1, par3, par4);
/* 248 */     int var10 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
/* 249 */     int var11 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
/* 250 */     int var12 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
/* 251 */     int var13 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
/* 252 */     boolean var14 = (var8 == this.blockID || var9 == this.blockID);
/* 253 */     boolean var15 = (var6 == this.blockID || var7 == this.blockID);
/* 254 */     boolean var16 = (var10 == this.blockID || var11 == this.blockID || var12 == this.blockID || var13 == this.blockID);
/*     */     
/* 256 */     for (int var17 = par2 - 1; var17 <= par2 + 1; var17++) {
/*     */       
/* 258 */       for (int var18 = par4 - 1; var18 <= par4 + 1; var18++) {
/*     */         
/* 260 */         int var19 = par1World.getBlockId(var17, par3 - 1, var18);
/* 261 */         float var20 = 0.0F;
/*     */         
/* 263 */         if (var19 == Block.tilledField.blockID) {
/*     */           
/* 265 */           var20 = 1.0F;
/*     */           
/* 267 */           if (par1World.getBlockMetadata(var17, par3 - 1, var18) > 0)
/*     */           {
/* 269 */             var20 = 3.0F;
/*     */           }
/*     */         } 
/*     */         
/* 273 */         if (var17 != par2 || var18 != par4)
/*     */         {
/* 275 */           var20 /= 4.0F;
/*     */         }
/*     */         
/* 278 */         var5 += var20;
/*     */       } 
/*     */     } 
/*     */     
/* 282 */     if (var16 || (var14 && var15))
/*     */     {
/* 284 */       var5 /= 2.0F;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 289 */     if (block_below == Block.tilledField && BlockFarmland.isFertilized(block_below_metadata)) {
/* 290 */       var5 *= 1.5F;
/*     */     }
/* 292 */     BiomeGenBase biome = par1World.getBiomeGenForCoords(par2, par4);
/*     */     
/* 294 */     var5 *= getTemperatureGrowthRateModifier(biome.temperature);
/* 295 */     var5 *= getHumidityGrowthRateModifier(biome.isHighHumidity());
/* 296 */     var5 *= getGlobalGrowthRateModifierFromMITE();
/*     */ 
/*     */ 
/*     */     
/* 300 */     return var5;
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
/*     */   public int getRenderColor(int metadata) {
/* 322 */     int growth = isDead(metadata) ? 7 : getGrowth(metadata);
/*     */     
/* 324 */     int r = growth * 32;
/* 325 */     int g = 255 - growth * 8;
/* 326 */     int b = growth * 4;
/*     */     
/* 328 */     if (isDead(metadata)) {
/*     */       
/* 330 */       r = (r + 296) / 3;
/* 331 */       g = (g + 200) / 3;
/* 332 */       b = (b + 80) / 3;
/*     */     } 
/*     */     
/* 335 */     return r << 16 | g << 8 | b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 344 */     return getRenderColor(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/* 353 */     float var1 = 0.125F;
/* 354 */     setBlockBoundsForCurrentThread((0.5F - var1), 0.0D, (0.5F - var1), (0.5F + var1), 0.25D, (0.5F + var1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 362 */     int index = Minecraft.getThreadIndex();
/*     */ 
/*     */     
/* 365 */     this.maxY[index] = ((getGrowth(par1IBlockAccess.getBlockMetadata(par2, par3, par4)) * 2 + 2) / 16.0F);
/*     */     
/* 367 */     if (getState(par1IBlockAccess, par2, par3, par4) >= 0) {
/* 368 */       this.maxY[index] = 0.625D;
/*     */     }
/* 370 */     float var5 = 0.125F;
/* 371 */     setBlockBoundsForCurrentThread((0.5F - var5), 0.0D, (0.5F - var5), (0.5F + var5), (float)this.maxY[index], (0.5F + var5));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 379 */     return 19;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 388 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 393 */     return (var5 < 7) ? -1 : ((par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.fruitType.blockID) ? 0 : ((par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.fruitType.blockID) ? 1 : ((par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.fruitType.blockID) ? 2 : ((par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.fruitType.blockID) ? 3 : -1))));
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
/*     */   public int getSeedItem() {
/* 429 */     return (this.fruitType == Block.pumpkin) ? Item.pumpkinSeeds.itemID : ((this.fruitType == Block.melon) ? Item.melonSeeds.itemID : -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 434 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 439 */     if (info.wasSnowedUpon() && getGrowth(info.getMetadata()) == 0) {
/*     */       
/* 441 */       BlockCrops.playCropPopSound(info);
/* 442 */       return dropBlockAsEntityItem(info, getSeedItem(), 0, 1, 1.0F);
/*     */     } 
/*     */     
/* 445 */     if (!info.wasHarvestedByPlayer() || isDead(info.getMetadata()) || getGrowth(info.getMetadata()) > 0) {
/* 446 */       return 0;
/*     */     }
/* 448 */     return dropBlockAsEntityItem(info, getSeedItem(), 0, 1, 1.0F);
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
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 472 */     return (this.fruitType == Block.pumpkin) ? Item.pumpkinSeeds.itemID : ((this.fruitType == Block.melon) ? Item.melonSeeds.itemID : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 481 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_disconnected");
/* 482 */     this.theIcon = par1IconRegister.registerIcon(getTextureName() + "_connected");
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getStemIcon() {
/* 487 */     return this.theIcon;
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
/*     */   public static int getGrowthBits() {
/* 511 */     return 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMaxGrowth() {
/* 516 */     return getGrowthBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getGrowth(int metadata) {
/* 521 */     return metadata & getGrowthBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int incrementGrowth(int metadata) {
/* 526 */     if (getGrowth(metadata) < getMaxGrowth()) {
/* 527 */       metadata++;
/*     */     }
/* 529 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int setGrowth(int metadata, int growth) {
/* 534 */     return metadata & (getGrowthBits() ^ 0xFFFFFFFF) | growth & getGrowthBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getGrowthStage(int metadata) {
/* 539 */     return getGrowth(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isMature(int metadata) {
/* 544 */     return (getGrowth(metadata) == getMaxGrowth());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDeadBit() {
/* 549 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isDead(int metadata) {
/* 554 */     return ((metadata & getDeadBit()) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int setDead(int metadata, boolean dead) {
/* 559 */     if (dead) {
/* 560 */       metadata |= getDeadBit();
/*     */     } else {
/* 562 */       metadata &= getDeadBit() ^ 0xFFFFFFFF;
/*     */     } 
/* 564 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 569 */     return (this.fruitType == pumpkin) ? "pumpkin" : ((this.fruitType == melon) ? "melon" : super.getNameDisambiguationForReferenceFile(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeSoundWhenPlaced(World world, int x, int y, int z, int metadata) {
/* 574 */     StepSound step_sound = Block.tilledField.stepSound;
/*     */     
/* 576 */     if (step_sound != null)
/* 577 */       world.playSoundAtBlock(x, y, z, step_sound.getPlaceSound(), 0.2F, step_sound.getPitch() * 0.8F); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockStem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */