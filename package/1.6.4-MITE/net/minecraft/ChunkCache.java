/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChunkCache
/*     */   implements IBlockAccess
/*     */ {
/*     */   private int chunkX;
/*     */   private int chunkZ;
/*     */   private Chunk[][] chunkArray;
/*     */   private boolean isEmpty;
/*     */   private World worldObj;
/*     */   
/*     */   public ChunkCache(World par1World, int par2, int par3, int par4, int par5, int par6, int par7, int par8) {
/*  19 */     this.worldObj = par1World;
/*  20 */     this.chunkX = par2 - par8 >> 4;
/*  21 */     this.chunkZ = par4 - par8 >> 4;
/*  22 */     int var9 = par5 + par8 >> 4;
/*  23 */     int var10 = par7 + par8 >> 4;
/*  24 */     this.chunkArray = new Chunk[var9 - this.chunkX + 1][var10 - this.chunkZ + 1];
/*  25 */     this.isEmpty = true;
/*     */ 
/*     */     
/*     */     int var11;
/*     */     
/*  30 */     for (var11 = this.chunkX; var11 <= var9; var11++) {
/*     */       
/*  32 */       for (int var12 = this.chunkZ; var12 <= var10; var12++) {
/*     */         
/*  34 */         Chunk var13 = par1World.getChunkFromChunkCoords(var11, var12);
/*     */         
/*  36 */         if (var13 != null)
/*     */         {
/*  38 */           this.chunkArray[var11 - this.chunkX][var12 - this.chunkZ] = var13;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  43 */     for (var11 = par2 >> 4; var11 <= par5 >> 4; var11++) {
/*     */       
/*  45 */       for (int var12 = par4 >> 4; var12 <= par7 >> 4; var12++) {
/*     */         
/*  47 */         Chunk var13 = this.chunkArray[var11 - this.chunkX][var12 - this.chunkZ];
/*     */         
/*  49 */         if (var13 != null && !var13.getAreLevelsEmpty(par3, par6)) {
/*     */           
/*  51 */           this.isEmpty = false;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean extendedLevelsInChunkCache() {
/*  63 */     return this.isEmpty;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockId(int par1, int par2, int par3) {
/*  71 */     if (par2 < 0)
/*     */     {
/*  73 */       return 0;
/*     */     }
/*  75 */     if (par2 >= 256)
/*     */     {
/*  77 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  81 */     int var4 = (par1 >> 4) - this.chunkX;
/*  82 */     int var5 = (par3 >> 4) - this.chunkZ;
/*     */     
/*  84 */     if (var4 >= 0 && var4 < this.chunkArray.length && var5 >= 0 && var5 < (this.chunkArray[var4]).length) {
/*     */       
/*  86 */       Chunk var6 = this.chunkArray[var4][var5];
/*  87 */       return (var6 == null) ? 0 : var6.getBlockID(par1 & 0xF, par2, par3 & 0xF);
/*     */     } 
/*     */ 
/*     */     
/*  91 */     return 0;
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
/*     */   public Block getBlock(int x, int y, int z) {
/* 112 */     return Block.getBlock(getBlockId(x, y, z));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isBlockSolid(int x, int y, int z) {
/* 118 */     return Block.isBlockSolid(this, x, y, z);
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
/*     */   public TileEntity getBlockTileEntity(int par1, int par2, int par3) {
/* 141 */     int var4 = (par1 >> 4) - this.chunkX;
/* 142 */     int var5 = (par3 >> 4) - this.chunkZ;
/* 143 */     return this.chunkArray[var4][var5].getChunkBlockTileEntity(par1 & 0xF, par2, par3 & 0xF);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBrightness(int par1, int par2, int par3, int par4) {
/* 148 */     int var5 = getLightValue(par1, par2, par3);
/*     */     
/* 150 */     if (var5 < par4)
/*     */     {
/* 152 */       var5 = par4;
/*     */     }
/*     */     
/* 155 */     return this.worldObj.provider.lightBrightnessTable[var5];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getLightBrightnessForSkyBlocks(int par1, int par2, int par3, int par4) {
/* 163 */     int var5 = getSkyBlockTypeBrightness(EnumSkyBlock.Sky, par1, par2, par3);
/* 164 */     int var6 = getSkyBlockTypeBrightness(EnumSkyBlock.Block, par1, par2, par3);
/*     */     
/* 166 */     if (var6 < par4)
/*     */     {
/* 168 */       var6 = par4;
/*     */     }
/*     */     
/* 171 */     return var5 << 20 | var6 << 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLightBrightness(int par1, int par2, int par3) {
/* 180 */     return this.worldObj.provider.lightBrightnessTable[getLightValue(par1, par2, par3)];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getLightValue(int par1, int par2, int par3) {
/* 188 */     return getLightValueExt(par1, par2, par3, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getLightValueExt(int par1, int par2, int par3, boolean par4) {
/* 198 */     if (this.worldObj.isWithinBlockDomain(par1, par3)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 203 */       if (par4) {
/*     */         
/* 205 */         int i = getBlockId(par1, par2, par3);
/*     */ 
/*     */ 
/*     */         
/* 209 */         if (Block.useNeighborBrightness[i]) {
/*     */           
/* 211 */           Block block = Block.getBlock(i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 282 */           int metadata = getBlockMetadata(par1, par2, par3);
/*     */           
/* 284 */           int brightness = 0;
/*     */           
/* 286 */           for (int ordinal = 0; ordinal < 6; ordinal++) {
/*     */             
/* 288 */             EnumDirection direction = EnumDirection.get(ordinal);
/*     */             
/* 290 */             if (block.useNeighborBrightness(metadata, direction)) {
/*     */               
/* 292 */               brightness = Math.max(brightness, getLightValueExt(par1 + direction.dx, par2 + direction.dy, par3 + direction.dz, false));
/*     */               
/* 294 */               if (brightness > 14) {
/*     */                 break;
/*     */               }
/*     */             } 
/*     */           } 
/* 299 */           return brightness;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 310 */       if (par2 < 0)
/*     */       {
/* 312 */         return 0;
/*     */       }
/* 314 */       if (par2 >= 256) {
/*     */         
/* 316 */         int i = 15 - this.worldObj.skylightSubtracted;
/*     */         
/* 318 */         if (i < 0)
/*     */         {
/* 320 */           i = 0;
/*     */         }
/*     */         
/* 323 */         return i;
/*     */       } 
/*     */ 
/*     */       
/* 327 */       int var5 = (par1 >> 4) - this.chunkX;
/* 328 */       int var6 = (par3 >> 4) - this.chunkZ;
/* 329 */       return this.chunkArray[var5][var6].getBlockLightValue(par1 & 0xF, par2, par3 & 0xF, this.worldObj.skylightSubtracted);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 334 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockMetadata(int par1, int par2, int par3) {
/* 343 */     if (par2 < 0)
/*     */     {
/* 345 */       return 0;
/*     */     }
/* 347 */     if (par2 >= 256)
/*     */     {
/* 349 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 353 */     int var4 = (par1 >> 4) - this.chunkX;
/* 354 */     int var5 = (par3 >> 4) - this.chunkZ;
/* 355 */     return this.chunkArray[var4][var5].getBlockMetadata(par1 & 0xF, par2, par3 & 0xF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Material getBlockMaterial(int par1, int par2, int par3) {
/* 364 */     int var4 = getBlockId(par1, par2, par3);
/* 365 */     return (var4 == 0) ? Material.air : (Block.blocksList[var4]).blockMaterial;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getBlockMaterial(int block_id) {
/* 370 */     return (block_id == 0) ? Material.air : (Block.blocksList[block_id]).blockMaterial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase getBiomeGenForCoords(int par1, int par2) {
/* 378 */     return this.worldObj.getBiomeGenForCoords(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isBlockStandardFormOpaqueCube(int par1, int par2, int par3) {
/* 389 */     return Block.isBlockOpaqueStandardFormCube(this, par1, par2, par3);
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
/*     */   public final boolean isBlockNormalCube(int par1, int par2, int par3) {
/* 432 */     return Block.isNormalCube(getBlockId(par1, par2, par3));
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
/*     */   public boolean isBlockTopFlatAndSolid(int x, int y, int z) {
/* 447 */     Block block = Block.blocksList[getBlockId(x, y, z)];
/* 448 */     return (block != null && block.isTopFlatAndSolid(getBlockMetadata(x, y, z)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3Pool getWorldVec3Pool() {
/* 456 */     return this.worldObj.getWorldVec3Pool();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAirBlock(int par1, int par2, int par3) {
/* 464 */     Block var4 = Block.blocksList[getBlockId(par1, par2, par3)];
/* 465 */     return (var4 == null);
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
/*     */   public int getSkyBlockTypeBrightness(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4) {
/* 484 */     if (par3 < 0) {
/* 485 */       par3 = 0;
/* 486 */     } else if (par3 > 255) {
/* 487 */       par3 = 255;
/*     */     } 
/*     */ 
/*     */     
/* 491 */     if (this.worldObj.isWithinBlockDomain(par2, par4)) {
/*     */       
/* 493 */       if (par1EnumSkyBlock == EnumSkyBlock.Sky && this.worldObj.provider.hasNoSky)
/*     */       {
/* 495 */         return 0;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 502 */       int block_id = getBlockId(par2, par3, par4);
/*     */ 
/*     */       
/* 505 */       if (Block.useNeighborBrightness[block_id]) {
/*     */         
/* 507 */         Block block = Block.getBlock(block_id);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 564 */         int metadata = getBlockMetadata(par2, par3, par4);
/*     */         
/* 566 */         int brightness = 0;
/*     */         
/* 568 */         for (int ordinal = 0; ordinal < 6; ordinal++) {
/*     */           
/* 570 */           EnumDirection direction = EnumDirection.get(ordinal);
/*     */           
/* 572 */           if (block.useNeighborBrightness(metadata, direction)) {
/*     */             
/* 574 */             brightness = Math.max(brightness, getSpecialBlockBrightness(par1EnumSkyBlock, par2 + direction.dx, par3 + direction.dy, par4 + direction.dz));
/*     */             
/* 576 */             if (brightness > 14) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/* 581 */         return brightness;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 592 */       int var5 = (par2 >> 4) - this.chunkX;
/* 593 */       int var6 = (par4 >> 4) - this.chunkZ;
/* 594 */       return this.chunkArray[var5][var6].getSavedLightValue(par1EnumSkyBlock, par2 & 0xF, par3, par4 & 0xF);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 600 */     return par1EnumSkyBlock.defaultLightValue;
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
/*     */   public int getSpecialBlockBrightness(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4) {
/* 619 */     if (par3 < 0) {
/* 620 */       par3 = 0;
/* 621 */     } else if (par3 > 255) {
/* 622 */       par3 = 255;
/*     */     } 
/*     */ 
/*     */     
/* 626 */     if (this.worldObj.isWithinBlockDomain(par2, par4)) {
/*     */       
/* 628 */       int var5 = (par2 >> 4) - this.chunkX;
/* 629 */       int var6 = (par4 >> 4) - this.chunkZ;
/* 630 */       return this.chunkArray[var5][var6].getSavedLightValue(par1EnumSkyBlock, par2 & 0xF, par3, par4 & 0xF);
/*     */     } 
/*     */ 
/*     */     
/* 634 */     return par1EnumSkyBlock.defaultLightValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 643 */     return 256;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isBlockProvidingPowerTo(int par1, int par2, int par3, int par4) {
/* 651 */     int var5 = getBlockId(par1, par2, par3);
/* 652 */     return (var5 == 0) ? 0 : Block.blocksList[var5].isProvidingStrongPower(this, par1, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorld() {
/* 657 */     return this.worldObj;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */