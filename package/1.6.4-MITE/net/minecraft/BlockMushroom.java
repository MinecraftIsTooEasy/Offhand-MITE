/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockMushroom
/*     */   extends BlockPlant
/*     */ {
/*     */   protected BlockMushroom(int id) {
/*  11 */     super(id);
/*     */     
/*  13 */     float size = 0.2F;
/*  14 */     setBlockBoundsForAllThreads((0.5F - size), 0.0D, (0.5F - size), (0.5F + size), (size * 2.0F), (0.5F + size));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  19 */     return "Bits 1 and 2 used to track growth";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  24 */     return (metadata >= 0 && metadata < 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  29 */     if (super.updateTick(world, x, y, z, random)) {
/*  30 */       return true;
/*     */     }
/*  32 */     int ran = random.nextInt(100);
/*     */ 
/*     */     
/*  35 */     if (ran == 0) {
/*     */       
/*  37 */       byte var6 = 4;
/*  38 */       int var7 = 5;
/*     */ 
/*     */       
/*     */       int var8;
/*     */       
/*  43 */       for (var8 = x - var6; var8 <= x + var6; var8++) {
/*     */         
/*  45 */         for (int i = z - var6; i <= z + var6; i++) {
/*     */           
/*  47 */           for (int j = y - 1; j <= y + 1; j++) {
/*     */             
/*  49 */             if (world.getBlockId(var8, j, i) == this.blockID) {
/*     */               
/*  51 */               var7--;
/*     */               
/*  53 */               if (var7 <= 0)
/*     */               {
/*  55 */                 return false;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  62 */       var8 = x + random.nextInt(3) - 1;
/*  63 */       int var9 = y + random.nextInt(2) - random.nextInt(2);
/*  64 */       int var10 = z + random.nextInt(3) - 1;
/*     */       
/*  66 */       for (int var11 = 0; var11 < 4; var11++) {
/*     */ 
/*     */         
/*  69 */         if (world.isAirBlock(var8, var9, var10) && isLegalAt(world, var8, var9, var10, 0)) {
/*     */           
/*  71 */           x = var8;
/*  72 */           y = var9;
/*  73 */           z = var10;
/*     */         } 
/*     */         
/*  76 */         var8 = x + random.nextInt(3) - 1;
/*  77 */         var9 = y + random.nextInt(2) - random.nextInt(2);
/*  78 */         var10 = z + random.nextInt(3) - 1;
/*     */       } 
/*     */ 
/*     */       
/*  82 */       if (world.isAirBlock(var8, var9, var10) && isLegalAt(world, var8, var9, var10, 0))
/*     */       {
/*  84 */         if (this == Block.mushroomBrown && world.getBlock(var8, var9 - 1, var10) == Block.mycelium && random.nextInt(4) > 0) {
/*  85 */           return false;
/*     */         }
/*  87 */         world.setBlock(var8, var9, var10, this.blockID, 0, 2);
/*     */       }
/*     */     
/*  90 */     } else if (ran == 1 || ran == 2) {
/*     */       
/*  92 */       if (this == Block.mushroomBrown) {
/*  93 */         tryGrowGiantMushroom(world, x, y, z, (EntityPlayer)null);
/*     */       
/*     */       }
/*     */     }
/*  97 */     else if (this == Block.mushroomBrown && ran < 10) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 110 */       if (canConvertBlockBelowToMycelium(world, x, y, z)) {
/* 111 */         world.setBlock(x, y - 1, z, Block.mycelium.blockID);
/*     */       }
/*     */     } 
/*     */     
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canConvertBlockBelowToMycelium(World world, int x, int y, int z) {
/* 121 */     if (world.getBlock(x, y, z) != mushroomBrown) {
/* 122 */       return false;
/*     */     }
/* 124 */     Block block = world.getBlock(x, --y, z);
/*     */     
/* 126 */     if (block != tilledField) {
/* 127 */       return false;
/*     */     }
/* 129 */     BlockFarmland farmland = (BlockFarmland)block;
/*     */     
/* 131 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 133 */     if (BlockFarmland.getWetness(metadata) != 0) if (BlockFarmland.isFertilized(metadata)) {
/*     */ 
/*     */         
/* 136 */         y++;
/*     */         
/* 138 */         return (!world.isOutdoors(x, y, z) && isLightLevelSuitable(world, x, y, z));
/*     */       }  
/*     */     return false;
/*     */   }
/*     */   public boolean canBePlacedAt(World world, int x, int y, int z, int metadata) {
/* 143 */     if (this == Block.mushroomRed) {
/*     */       
/* 145 */       if (!world.isOutdoors(x, y, z)) {
/* 146 */         return false;
/*     */       }
/* 148 */       for (int dx = -1; dx <= 1; dx++) {
/*     */         
/* 150 */         for (int dz = -1; dz <= 1; dz++) {
/*     */           
/* 152 */           if (world.isAirOrPassableBlock(x + dx, y, z + dz, false) && world.canBlockSeeTheSky(x + dx, y, z + dz)) {
/* 153 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/* 157 */     } else if (this == mushroomBrown) {
/*     */       
/* 159 */       if (world.isOutdoors(x, y, z)) {
/* 160 */         return false;
/*     */       }
/*     */     } 
/* 163 */     return super.canBePlacedAt(world, x, y, z, metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 169 */     return ((world.provider.hasNoSky || !world.canBlockSeeTheSky(x, y, z)) && super.isLegalAt(world, x, y, z, metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 174 */     if (block_below == null)
/*     */     {
/*     */       
/* 177 */       return false;
/*     */     }
/*     */     
/* 180 */     if (!Block.opaqueCubeLookup[block_below.blockID] && block_below != Block.tilledField) {
/* 181 */       return false;
/*     */     }
/* 183 */     if (block_below == Block.sand || block_below == Block.blockClay) {
/* 184 */       return false;
/*     */     }
/* 186 */     if (this == Block.mushroomRed && block_below != Block.grass) {
/* 187 */       return false;
/*     */     }
/* 189 */     if (this == Block.mushroomBrown && block_below != Block.stone && block_below != Block.gravel && block_below != Block.dirt && block_below != Block.tilledField && block_below != Block.mycelium) {
/* 190 */       return false;
/*     */     }
/* 192 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ignoresLightCheckIfUnderOpenSky() {
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinAllowedLightValue() {
/* 202 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxAllowedLightValue() {
/* 207 */     return (this == mushroomBrown) ? BlockMycelium.getLightValueTolerance() : 15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean fertilizeMushroom(World world, int x, int y, int z, ItemStack item_stack, EntityPlayer player) {
/* 213 */     if (item_stack == null || item_stack.getItem() != Item.manure) {
/* 214 */       return false;
/*     */     }
/* 216 */     return tryGrowGiantMushroom(world, x, y, z, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean tryGrowGiantMushroom(World world, int x, int y, int z, EntityPlayer player) {
/* 221 */     if (world.isRemote) {
/* 222 */       Debug.setErrorMessage("tryGrowGiantMushroom: called on client?");
/*     */     }
/* 224 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 226 */     if (!isLegalAt(world, x, y, z, metadata)) {
/* 227 */       return false;
/*     */     }
/* 229 */     Block block_below = world.getBlock(x, y - 1, z);
/*     */     
/* 231 */     if (this == Block.mushroomRed) {
/*     */       
/* 233 */       if (block_below != Block.grass) {
/* 234 */         return false;
/*     */       }
/* 236 */       if (!world.isOutdoors(x, y, z)) {
/* 237 */         return false;
/*     */       }
/* 239 */     } else if (this == Block.mushroomBrown) {
/*     */       
/* 241 */       if (block_below != Block.mycelium) {
/* 242 */         return false;
/*     */       }
/* 244 */       if (world.isOutdoors(x, y, z)) {
/* 245 */         return false;
/*     */       }
/*     */     } 
/* 248 */     metadata = incrementGrowth(metadata);
/*     */     
/* 250 */     if (!isMature(metadata)) {
/*     */       
/* 252 */       world.setBlockMetadataWithNotify(x, y, z, metadata, 6);
/* 253 */       return true;
/*     */     } 
/*     */     
/* 256 */     world.setBlockToAir(x, y, z);
/* 257 */     WorldGenBigMushroom var7 = null;
/*     */     
/* 259 */     if (this.blockID == Block.mushroomBrown.blockID) {
/*     */       
/* 261 */       var7 = new WorldGenBigMushroom(0);
/*     */     }
/* 263 */     else if (this.blockID == Block.mushroomRed.blockID) {
/*     */       
/* 265 */       var7 = new WorldGenBigMushroom(1);
/*     */     } 
/*     */     
/* 268 */     if (var7 != null && var7.generate(world, world.rand, x, y, z)) {
/*     */       
/* 270 */       if (player != null) {
/* 271 */         player.triggerAchievement(AchievementList.supersizeMe);
/*     */       }
/* 273 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 277 */     world.setBlock(x, y, z, this.blockID, metadata, 3);
/* 278 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGrowthBits() {
/* 284 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxGrowth() {
/* 289 */     return getGrowthBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGrowth(int metadata) {
/* 294 */     return metadata & getGrowthBits();
/*     */   }
/*     */ 
/*     */   
/*     */   public int incrementGrowth(int metadata) {
/* 299 */     if (getGrowth(metadata) < getMaxGrowth()) {
/* 300 */       metadata++;
/*     */     }
/* 302 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMature(int metadata) {
/* 307 */     return (getGrowth(metadata) == getMaxGrowth());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 312 */     return (this == mushroomBrown) ? "brown, small" : ((this == mushroomRed) ? "red, small" : super.getNameDisambiguationForReferenceFile(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 317 */     if (super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only)) {
/*     */       
/* 319 */       if (!test_only && placer instanceof EntityPlayer) {
/* 320 */         BlockFarmland.checkForMyceliumConditions(world, x, y - 1, z, (EntityPlayer)placer);
/*     */       }
/* 322 */       return true;
/*     */     } 
/*     */     
/* 325 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockMushroom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */