/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCrops
/*     */   extends BlockGrowingPlant
/*     */ {
/*     */   protected int num_growth_stages;
/*     */   protected Icon[] iconArray;
/*     */   protected Icon[] iconArrayBlighted;
/*     */   
/*     */   protected BlockCrops(int block_id, int num_growth_stages) {
/* 194 */     super(block_id);
/* 195 */     this.num_growth_stages = num_growth_stages;
/* 196 */     setTickRandomly(true);
/* 197 */     float var2 = 0.5F;
/* 198 */     setBlockBoundsForAllThreads((0.5F - var2), 0.0D, (0.5F - var2), (0.5F + var2), 0.25D, (0.5F + var2));
/* 199 */     setCreativeTab((CreativeTabs)null);
/* 200 */     setHardness(0.02F);
/* 201 */     setStepSound(soundGrassFootstep);
/* 202 */     disableStats();
/*     */     
/* 204 */     setCushioning(0.2F);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/* 209 */     return "Bits 1, 2, and 4 used to track growth, bit 8 set if crop is blighted";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/* 214 */     return (metadata >= 0 && metadata < 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getLowestOptimalTemperature() {
/* 219 */     return 0.8F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHighestOptimalTemperature() {
/* 224 */     return 1.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHumidityGrowthRateModifier(boolean high_humidity) {
/* 229 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 234 */     return (block_below == tilledField);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getDeadCropBlockId() {
/* 239 */     return Block.cropsDead.blockID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random rand) {
/* 244 */     if (super.updateTick(world, x, y, z, rand)) {
/* 245 */       return true;
/*     */     }
/* 247 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 249 */     float growth_rate = getGrowthRate(world, x, y, z);
/*     */     
/* 251 */     if (growth_rate == 0.0F && !BlockFarmland.isWaterNearby(world, x, y - 1, z)) {
/*     */       
/* 253 */       if (rand.nextFloat() < 0.05F) {
/*     */         
/* 255 */         if (isMature(metadata)) {
/*     */ 
/*     */           
/* 258 */           dropBlockAsEntityItem((new BlockBreakInfo(world, x, y, z)).setDrought());
/* 259 */           return world.setBlock(x, y, z, 0);
/*     */         } 
/*     */ 
/*     */         
/* 263 */         return world.setBlock(x, y, z, getDeadCropBlockId(), getGrowth(metadata), 3);
/*     */       } 
/*     */ 
/*     */       
/* 267 */       return false;
/*     */     } 
/*     */     
/* 270 */     if (isBlighted(metadata)) {
/*     */       
/* 272 */       if (rand.nextInt(64) == 0) {
/* 273 */         return world.setBlock(x, y, z, getDeadCropBlockId(), isMature(metadata) ? (getGrowth(metadata) - 1) : getGrowth(metadata), 3);
/*     */       }
/* 275 */       if (rand.nextBoolean()) {
/* 276 */         return false;
/*     */       }
/* 278 */       int dx = rand.nextInt(3) - 1;
/* 279 */       int dy = rand.nextInt(3) - 1;
/* 280 */       int dz = rand.nextInt(3) - 1;
/*     */       
/* 282 */       if (dx != 0 || dy != 0 || dz != 0)
/*     */       {
/* 284 */         if (world.blockExists(x + dx, y + dy, z + dz)) {
/*     */           
/* 286 */           int block_id = world.getBlockId(x + dx, y + dy, z + dz);
/*     */           
/* 288 */           Block block = Block.getBlock(block_id);
/*     */ 
/*     */           
/* 291 */           if (block instanceof BlockCrops && !((BlockCrops)block).isDead()) {
/*     */             
/* 293 */             metadata = world.getBlockMetadata(x + dx, y + dy, z + dz);
/*     */             
/* 295 */             if (!isBlighted(metadata)) {
/* 296 */               world.setBlockMetadataWithNotify(x + dx, y + dy, z + dz, setBlighted(metadata, true), 2);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/* 301 */       return false;
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
/* 329 */     if (world.isBloodMoonNight() && !isBlighted(metadata) && rand.nextFloat() < 0.25F && !MinecraftServer.getServer().isDedicatedServer())
/*     */     {
/* 331 */       if (!world.hasStandardFormOpaqueBlockAbove(x, y, z) || world.isOutdoors(x, y, z))
/*     */       {
/* 333 */         if (world.setBlockMetadataWithNotify(x, y, z, setBlighted(metadata, true), 2)) {
/* 334 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 338 */     int blv = world.getBlockLightValue(x, y + 1, z);
/*     */     
/* 340 */     if (rand.nextFloat() < chanceOfBlightPerRandomTick() * getBlightChanceModifierForBiome(world.getBiomeGenForCoords(x, z)) * (1.0F - blv / 16.0F)) {
/*     */       
/* 342 */       if (!isBlighted(metadata)) {
/* 343 */         return world.setBlockMetadataWithNotify(x, y, z, setBlighted(metadata, true), 2);
/*     */       }
/* 345 */     } else if (isLightLevelSuitableForGrowth(blv)) {
/*     */       
/* 347 */       if (growth_rate == 0.0F || isMature(metadata)) {
/* 348 */         return false;
/*     */       }
/* 350 */       if (rand.nextInt((int)(25.0F / growth_rate) + 1) == 0) {
/*     */         
/* 352 */         world.setBlockMetadataWithNotify(x, y, z, incrementGrowth(metadata), 2);
/*     */         
/* 354 */         if (rand.nextInt(256) == 0 && Block.blocksList[world.getBlockId(x, y - 1, z)] == Block.tilledField) {
/*     */           
/* 356 */           metadata = world.getBlockMetadata(x, y - 1, z);
/*     */           
/* 358 */           if (BlockFarmland.isFertilized(metadata)) {
/* 359 */             world.setBlockMetadataWithNotify(x, y - 1, z, BlockFarmland.setFertilized(metadata, false), 2);
/*     */           }
/*     */         } 
/* 362 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 366 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getBlightChanceModifierForBiome(BiomeGenBase biome) {
/* 371 */     float delta_temperature, temperature = biome.temperature;
/*     */ 
/*     */     
/* 374 */     if (temperature < 1.0F) {
/* 375 */       delta_temperature = 1.0F - temperature;
/* 376 */     } else if (temperature > 1.2F) {
/* 377 */       delta_temperature = temperature - 1.2F;
/*     */     } else {
/* 379 */       delta_temperature = 0.0F;
/*     */     } 
/* 381 */     return Math.max((1.0F - delta_temperature) * (biome.isHighHumidity() ? 1.5F : 1.0F), 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fertilize(World world, int x, int y, int z, ItemStack item_stack) {
/* 386 */     Item item = item_stack.getItem();
/*     */     
/* 388 */     if (item == Item.dyePowder) {
/*     */       
/* 390 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 407 */       return (isBlighted(metadata) && world.setBlockMetadataWithNotify(x, y, z, setBlighted(metadata, false), 2));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 417 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getGrowthRate(World par1World, int par2, int par3, int par4) {
/* 427 */     Block block_below = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
/* 428 */     int block_below_metadata = par1World.getBlockMetadata(par2, par3 - 1, par4);
/*     */     
/* 430 */     if (block_below == Block.tilledField && BlockFarmland.getWetness(block_below_metadata) == 0) {
/* 431 */       return 0.0F;
/*     */     }
/* 433 */     float var5 = 1.0F;
/* 434 */     int var6 = par1World.getBlockId(par2, par3, par4 - 1);
/* 435 */     int var7 = par1World.getBlockId(par2, par3, par4 + 1);
/* 436 */     int var8 = par1World.getBlockId(par2 - 1, par3, par4);
/* 437 */     int var9 = par1World.getBlockId(par2 + 1, par3, par4);
/* 438 */     int var10 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
/* 439 */     int var11 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
/* 440 */     int var12 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
/* 441 */     int var13 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
/* 442 */     boolean var14 = (var8 == this.blockID || var9 == this.blockID);
/* 443 */     boolean var15 = (var6 == this.blockID || var7 == this.blockID);
/* 444 */     boolean var16 = (var10 == this.blockID || var11 == this.blockID || var12 == this.blockID || var13 == this.blockID);
/*     */     
/* 446 */     for (int var17 = par2 - 1; var17 <= par2 + 1; var17++) {
/*     */       
/* 448 */       for (int var18 = par4 - 1; var18 <= par4 + 1; var18++) {
/*     */         
/* 450 */         int var19 = par1World.getBlockId(var17, par3 - 1, var18);
/* 451 */         float var20 = 0.0F;
/*     */         
/* 453 */         if (var19 == Block.tilledField.blockID) {
/*     */           
/* 455 */           var20 = 1.0F;
/*     */           
/* 457 */           if (par1World.getBlockMetadata(var17, par3 - 1, var18) > 0)
/*     */           {
/* 459 */             var20 = 3.0F;
/*     */           }
/*     */         } 
/*     */         
/* 463 */         if (var17 != par2 || var18 != par4)
/*     */         {
/* 465 */           var20 /= 4.0F;
/*     */         }
/*     */         
/* 468 */         var5 += var20;
/*     */       } 
/*     */     } 
/*     */     
/* 472 */     if (var16 || (var14 && var15))
/*     */     {
/* 474 */       var5 /= 2.0F;
/*     */     }
/*     */     
/* 477 */     if (block_below == Block.tilledField && BlockFarmland.isFertilized(block_below_metadata)) {
/* 478 */       var5 *= 1.5F;
/*     */     }
/* 480 */     BiomeGenBase biome = par1World.getBiomeGenForCoords(par2, par4);
/*     */     
/* 482 */     var5 *= getTemperatureGrowthRateModifier(biome.temperature);
/* 483 */     var5 *= getHumidityGrowthRateModifier(biome.isHighHumidity());
/* 484 */     var5 *= getGlobalGrowthRateModifierFromMITE();
/* 485 */     var5 *= getProximityGrowthRateModifier(par1World, par2, par3, par4);
/*     */     
/* 487 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon(int side, int metadata) {
/* 492 */     return isBlighted(metadata) ? this.iconArrayBlighted[getGrowthStage(metadata)] : this.iconArray[getGrowthStage(metadata)];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 497 */     return 6;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSeedItem() {
/* 502 */     return Item.seeds.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getCropItem() {
/* 512 */     return Item.wheat.itemID;
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
/*     */   protected int getMatureYield() {
/* 527 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 532 */     return getSeedItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 537 */     this.iconArray = new Icon[this.num_growth_stages];
/*     */     
/* 539 */     if (chanceOfBlightPerRandomTick() > 0.0F) {
/* 540 */       this.iconArrayBlighted = new Icon[this.num_growth_stages];
/*     */     }
/* 542 */     for (int i = 0; i < this.num_growth_stages; i++) {
/*     */       
/* 544 */       this.iconArray[i] = par1IconRegister.registerIcon("crops/" + getTextureName() + "/" + i);
/*     */       
/* 546 */       if (chanceOfBlightPerRandomTick() > 0.0F) {
/* 547 */         this.iconArrayBlighted[i] = par1IconRegister.registerIcon("crops/" + getTextureName() + "/blighted/" + i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockHardness(int metadata) {
/* 558 */     return (metadata == 0) ? 0.0F : (super.getBlockHardness(metadata) * (isMature(metadata) ? 2 : true));
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
/*     */   public float chanceOfBlightPerRandomTick() {
/* 585 */     return 5.0E-4F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlightBit() {
/* 590 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGrowthBits() {
/* 595 */     return 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxGrowth() {
/* 600 */     return getGrowthBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGrowth(int metadata) {
/* 605 */     return metadata & getGrowthBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public int incrementGrowth(int metadata) {
/* 610 */     if (getGrowth(metadata) < getMaxGrowth()) {
/* 611 */       metadata++;
/*     */     }
/* 613 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGrowthStage(int metadata) {
/* 618 */     return getGrowth(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMature(int metadata) {
/* 623 */     return (getGrowth(metadata) == getMaxGrowth());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlighted(int metadata) {
/* 628 */     return ((metadata & getBlightBit()) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int setBlighted(int metadata, boolean blighted) {
/* 633 */     if (blighted) {
/* 634 */       metadata |= getBlightBit();
/*     */     } else {
/* 636 */       metadata &= getBlightBit() ^ 0xFFFFFFFF;
/*     */     } 
/* 638 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setBlighted(World world, int x, int y, int z, boolean is_blighted) {
/* 643 */     if (isDead()) {
/*     */       return;
/*     */     }
/* 646 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 648 */     if (isBlighted(metadata) != is_blighted) {
/* 649 */       world.setBlockMetadataWithNotify(x, y, z, setBlighted(metadata, is_blighted), 2);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canBeCarried() {
/* 654 */     return false;
/*     */   }
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/*     */     float harvesting_enchantment;
/*     */     int num_drops;
/* 659 */     if (!info.wasHarvestedByPlayer() && !info.wasDrought() && !info.wasSnowedUpon() && !info.wasSelfDropped()) {
/* 660 */       return 0;
/*     */     }
/* 662 */     if (info.wasDrought())
/*     */     {
/* 664 */       playCropPopSound(info);
/*     */     }
/* 666 */     if (isBlighted(info.getMetadata())) {
/* 667 */       return 0;
/*     */     }
/* 669 */     ItemStack item_stack = info.getHarvesterItemStack();
/* 670 */     Item item = (item_stack == null) ? null : item_stack.getItem();
/* 671 */     ItemTool tool = (item instanceof ItemTool) ? (ItemTool)item : null;
/*     */ 
/*     */ 
/*     */     
/* 675 */     if (tool != null && tool.isEffectiveAgainstBlock(this, info.getMetadata())) {
/* 676 */       harvesting_enchantment = EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.harvesting, info.getHarvesterItemStack()) * 0.5F;
/*     */     } else {
/* 678 */       harvesting_enchantment = 0.0F;
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
/* 691 */     if (getGrowth(info.getMetadata()) == 0) {
/* 692 */       num_drops = dropBlockAsEntityItem(info, getSeedItem(), 0, 1, 1.0F);
/* 693 */     } else if (isMature(info.getMetadata()) && !info.wasSelfDropped()) {
/* 694 */       num_drops = dropBlockAsEntityItem(info, getCropItem(), 0, getMatureYield(), 1.0F + harvesting_enchantment);
/*     */     } else {
/* 696 */       return 0;
/*     */     } 
/* 698 */     if (info.wasSnowedUpon() && num_drops > 0) {
/* 699 */       playCropPopSound(info);
/*     */     }
/* 701 */     return num_drops;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDead() {
/* 708 */     return false;
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
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 721 */     return isDead() ? "dead" : "living";
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeSoundWhenPlaced(World world, int x, int y, int z, int metadata) {
/* 726 */     StepSound step_sound = Block.tilledField.stepSound;
/*     */     
/* 728 */     if (step_sound != null) {
/* 729 */       world.playSoundAtBlock(x, y, z, step_sound.getPlaceSound(), 0.2F, step_sound.getPitch() * 0.8F);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void playCropPopSound(BlockBreakInfo info) {
/* 734 */     info.playSoundEffectAtBlock("random.pop", 0.05F, ((info.world.rand.nextFloat() - info.world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockCrops.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */