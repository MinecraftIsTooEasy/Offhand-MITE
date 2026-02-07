/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockVine
/*     */   extends Block
/*     */ {
/*     */   public BlockVine(int par1) {
/*  11 */     super(par1, Material.vine, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  12 */     setTickRandomly(true);
/*  13 */     setMaxStackSize(8);
/*  14 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  19 */     return "Vines can appear on any combination of sides, and each bit represents one side. If no bits set then vine appears only on bottom surface, which sometimes occurs during chunk generation when swamp trees overlap";
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
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/*  33 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getRenderType() {
/*  42 */     return 20;
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
/*     */   public final void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  70 */     float var5 = 0.0625F;
/*  71 */     int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*  72 */     float var7 = 1.0F;
/*  73 */     float var8 = 1.0F;
/*  74 */     float var9 = 1.0F;
/*  75 */     float var10 = 0.0F;
/*  76 */     float var11 = 0.0F;
/*  77 */     float var12 = 0.0F;
/*  78 */     boolean var13 = (var6 > 0);
/*     */     
/*  80 */     if ((var6 & 0x2) != 0) {
/*     */       
/*  82 */       var10 = Math.max(var10, 0.0625F);
/*  83 */       var7 = 0.0F;
/*  84 */       var8 = 0.0F;
/*  85 */       var11 = 1.0F;
/*  86 */       var9 = 0.0F;
/*  87 */       var12 = 1.0F;
/*  88 */       var13 = true;
/*     */     } 
/*     */     
/*  91 */     if ((var6 & 0x8) != 0) {
/*     */       
/*  93 */       var7 = Math.min(var7, 0.9375F);
/*  94 */       var10 = 1.0F;
/*  95 */       var8 = 0.0F;
/*  96 */       var11 = 1.0F;
/*  97 */       var9 = 0.0F;
/*  98 */       var12 = 1.0F;
/*  99 */       var13 = true;
/*     */     } 
/*     */     
/* 102 */     if ((var6 & 0x4) != 0) {
/*     */       
/* 104 */       var12 = Math.max(var12, 0.0625F);
/* 105 */       var9 = 0.0F;
/* 106 */       var7 = 0.0F;
/* 107 */       var10 = 1.0F;
/* 108 */       var8 = 0.0F;
/* 109 */       var11 = 1.0F;
/* 110 */       var13 = true;
/*     */     } 
/*     */     
/* 113 */     if ((var6 & 0x1) != 0) {
/*     */       
/* 115 */       var9 = Math.min(var9, 0.9375F);
/* 116 */       var12 = 1.0F;
/* 117 */       var7 = 0.0F;
/* 118 */       var10 = 1.0F;
/* 119 */       var8 = 0.0F;
/* 120 */       var11 = 1.0F;
/* 121 */       var13 = true;
/*     */     } 
/*     */     
/* 124 */     if (!var13 && canBePlacedOn(par1IBlockAccess.getBlockId(par2, par3 + 1, par4))) {
/*     */       
/* 126 */       var8 = Math.min(var8, 0.9375F);
/* 127 */       var11 = 1.0F;
/* 128 */       var7 = 0.0F;
/* 129 */       var10 = 1.0F;
/* 130 */       var9 = 0.0F;
/* 131 */       var12 = 1.0F;
/*     */     } 
/*     */     
/* 134 */     setBlockBoundsForCurrentThread(var7, var8, var9, var10, var11, var12);
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
/*     */   public final boolean canPlaceBlockOnSide(World world, int x, int y, int z, EnumFace face) {
/* 175 */     if (face.isTop()) {
/* 176 */       y++;
/* 177 */     } else if (face.isNorth()) {
/* 178 */       z++;
/* 179 */     } else if (face.isSouth()) {
/* 180 */       z--;
/* 181 */     } else if (face.isWest()) {
/* 182 */       x++;
/* 183 */     } else if (face.isEast()) {
/* 184 */       x--;
/*     */     } else {
/* 186 */       return false;
/*     */     } 
/* 188 */     return canBePlacedOn(world.getBlockId(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   private int getMetadataForFace(EnumFace face) {
/* 193 */     return face.isNorth() ? 1 : (face.isEast() ? 2 : (face.isSouth() ? 4 : (face.isWest() ? 8 : -1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 198 */     if (face.isSide() && world.getBlock(x, y, z) == this) {
/*     */       
/* 200 */       int existing_metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 202 */       int combined_metadata = existing_metadata | getMetadataForFace(face);
/*     */       
/* 204 */       return (combined_metadata == existing_metadata) ? -1 : combined_metadata;
/*     */     } 
/* 206 */     if (entity.isEntityPlayer() && face.isBottom() && world.getBlock(x, y, z) == this && world.getBlock(x, y + 1, z) == this) {
/*     */ 
/*     */ 
/*     */       
/* 210 */       int existing_metadata = world.getBlockMetadata(x, y + 1, z);
/*     */       
/* 212 */       int num_sides = 0;
/*     */       int i;
/* 214 */       for (i = 1; i <= 8; i <<= 1) {
/*     */         
/* 216 */         if (BitHelper.isBitSet(existing_metadata, i))
/*     */         {
/* 218 */           if (++num_sides > 1) {
/* 219 */             return -1;
/*     */           }
/*     */         }
/*     */       } 
/* 223 */       existing_metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 225 */       int combined_metadata = existing_metadata | world.getBlockMetadata(x, y + 1, z);
/*     */       
/* 227 */       return (combined_metadata == existing_metadata) ? -1 : combined_metadata;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     if (face.isNorth())
/* 237 */       return 1; 
/* 238 */     if (face.isEast())
/* 239 */       return 2; 
/* 240 */     if (face.isSouth())
/* 241 */       return 4; 
/* 242 */     if (face.isWest())
/* 243 */       return 8; 
/* 244 */     if (face.isBottom() && world.getBlock(x, y + 1, z) == vine) {
/* 245 */       return world.getBlockMetadata(x, y + 1, z);
/*     */     }
/* 247 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean canBePlacedOn(int par1) {
/* 256 */     if (par1 == 0)
/*     */     {
/* 258 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 262 */     Block var2 = Block.blocksList[par1];
/*     */     
/* 264 */     return (var2.isAlwaysSolidOpaqueStandardFormCube() || var2 instanceof BlockLeaves);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean canVineStay(World par1World, int par2, int par3, int par4, int metadata) {
/* 275 */     int var5 = metadata;
/* 276 */     int var6 = var5;
/*     */     
/* 278 */     if (var5 > 0)
/*     */     {
/* 280 */       for (int var7 = 0; var7 <= 3; var7++) {
/*     */         
/* 282 */         int var8 = 1 << var7;
/*     */         
/* 284 */         if ((var5 & var8) != 0 && !canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[var7], par3, par4 + Direction.offsetZ[var7])) && (par1World.getBlockId(par2, par3 + 1, par4) != this.blockID || (par1World.getBlockMetadata(par2, par3 + 1, par4) & var8) == 0))
/*     */         {
/* 286 */           var6 &= var8 ^ 0xFFFFFFFF;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 291 */     if (var6 == 0 && !canBePlacedOn(par1World.getBlockId(par2, par3 + 1, par4)))
/*     */     {
/* 293 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 297 */     if (var6 != var5)
/*     */     {
/* 299 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 2);
/*     */     }
/*     */     
/* 302 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 308 */     return (super.isLegalAt(world, x, y, z, metadata) && canVineStay(world, x, y, z, metadata));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getBlockColor() {
/* 314 */     return ColorizerFoliage.getFoliageColorBasic();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getRenderColor(int par1) {
/* 323 */     return ColorizerFoliage.getFoliageColorBasic();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 333 */     return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeFoliageColor();
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
/*     */   public final int tickRate(World world) {
/* 354 */     return 200;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 360 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/* 361 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 367 */     if (((WorldServer)par1World).fast_forwarding ? RNG.chance_in_16[++RNG.random_number_index & 0x7FFF] : RNG.chance_in_4[++RNG.random_number_index & 0x7FFF]) {
/*     */       
/* 369 */       byte var6 = 4;
/* 370 */       int var7 = 5;
/* 371 */       boolean var8 = false;
/*     */ 
/*     */       
/*     */       int var9;
/*     */ 
/*     */       
/* 377 */       label94: for (var9 = par2 - var6; var9 <= par2 + var6; var9++) {
/*     */         
/* 379 */         for (int i = par4 - var6; i <= par4 + var6; i++) {
/*     */           
/* 381 */           for (int j = par3 - 1; j <= par3 + 1; j++) {
/*     */             
/* 383 */             if (par1World.getBlockId(var9, j, i) == this.blockID) {
/*     */               
/* 385 */               var7--;
/*     */               
/* 387 */               if (var7 <= 0) {
/*     */                 
/* 389 */                 var8 = true;
/*     */                 
/*     */                 break label94;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 397 */       var9 = par1World.getBlockMetadata(par2, par3, par4);
/* 398 */       int var10 = par1World.rand.nextInt(6);
/* 399 */       int var11 = Direction.facingToDirection[var10];
/*     */ 
/*     */ 
/*     */       
/* 403 */       if (var10 == 1 && par3 < 255 && par1World.isAirBlock(par2, par3 + 1, par4)) {
/*     */         
/* 405 */         if (var8)
/*     */         {
/*     */           
/* 408 */           return false;
/*     */         }
/*     */         
/* 411 */         int var12 = par1World.rand.nextInt(16) & var9;
/*     */         
/* 413 */         if (var12 > 0)
/*     */         {
/* 415 */           for (int var13 = 0; var13 <= 3; var13++) {
/*     */             
/* 417 */             if (!canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[var13], par3 + 1, par4 + Direction.offsetZ[var13])))
/*     */             {
/* 419 */               var12 &= 1 << var13 ^ 0xFFFFFFFF;
/*     */             }
/*     */           } 
/*     */           
/* 423 */           if (var12 > 0)
/*     */           {
/* 425 */             par1World.setBlock(par2, par3 + 1, par4, this.blockID, var12, 2);
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 433 */       else if (var10 >= 2 && var10 <= 5 && (var9 & 1 << var11) == 0) {
/*     */         
/* 435 */         if (var8)
/*     */         {
/*     */           
/* 438 */           return false;
/*     */         }
/*     */         
/* 441 */         int var12 = par1World.getBlockId(par2 + Direction.offsetX[var11], par3, par4 + Direction.offsetZ[var11]);
/*     */         
/* 443 */         if (var12 != 0 && Block.blocksList[var12] != null) {
/*     */           
/* 445 */           Block block = getBlock(var12);
/*     */ 
/*     */           
/* 448 */           if (block.isSolidOpaqueStandardFormCube(par1World, par2 + Direction.offsetX[var11], par3, par4 + Direction.offsetZ[var11]))
/*     */           {
/*     */ 
/*     */             
/* 452 */             par1World.setBlockMetadataWithNotify(par2, par3, par4, var9 | 1 << var11, 2);
/* 453 */             return true;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 458 */           int var13 = var11 + 1 & 0x3;
/* 459 */           int var14 = var11 + 3 & 0x3;
/*     */           
/* 461 */           if ((var9 & 1 << var13) != 0 && canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[var11] + Direction.offsetX[var13], par3, par4 + Direction.offsetZ[var11] + Direction.offsetZ[var13])))
/*     */           {
/* 463 */             par1World.setBlock(par2 + Direction.offsetX[var11], par3, par4 + Direction.offsetZ[var11], this.blockID, 1 << var13, 2);
/*     */           }
/* 465 */           else if ((var9 & 1 << var14) != 0 && canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[var11] + Direction.offsetX[var14], par3, par4 + Direction.offsetZ[var11] + Direction.offsetZ[var14])))
/*     */           {
/* 467 */             par1World.setBlock(par2 + Direction.offsetX[var11], par3, par4 + Direction.offsetZ[var11], this.blockID, 1 << var14, 2);
/*     */           }
/* 469 */           else if ((var9 & 1 << var13) != 0 && par1World.isAirBlock(par2 + Direction.offsetX[var11] + Direction.offsetX[var13], par3, par4 + Direction.offsetZ[var11] + Direction.offsetZ[var13]) && canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[var13], par3, par4 + Direction.offsetZ[var13])))
/*     */           {
/* 471 */             par1World.setBlock(par2 + Direction.offsetX[var11] + Direction.offsetX[var13], par3, par4 + Direction.offsetZ[var11] + Direction.offsetZ[var13], this.blockID, 1 << (var11 + 2 & 0x3), 2);
/*     */           }
/* 473 */           else if ((var9 & 1 << var14) != 0 && par1World.isAirBlock(par2 + Direction.offsetX[var11] + Direction.offsetX[var14], par3, par4 + Direction.offsetZ[var11] + Direction.offsetZ[var14]) && canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[var14], par3, par4 + Direction.offsetZ[var14])))
/*     */           {
/* 475 */             par1World.setBlock(par2 + Direction.offsetX[var11] + Direction.offsetX[var14], par3, par4 + Direction.offsetZ[var11] + Direction.offsetZ[var14], this.blockID, 1 << (var11 + 2 & 0x3), 2);
/*     */           }
/* 477 */           else if (canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[var11], par3 + 1, par4 + Direction.offsetZ[var11])))
/*     */           {
/* 479 */             par1World.setBlock(par2 + Direction.offsetX[var11], par3, par4 + Direction.offsetZ[var11], this.blockID, 0, 2);
/*     */           }
/*     */         
/*     */         } 
/* 483 */       } else if (par3 > 1) {
/*     */         
/* 485 */         int var12 = par1World.getBlockId(par2, par3 - 1, par4);
/*     */         
/* 487 */         if (var12 == 0) {
/*     */           
/* 489 */           if (!RNG.chance_in_4[par2 * 25498 + par3 * 544685 + par4 * 8567567 & 0x7FFF] || par1World.getBlock(par2, par3 + 1, par4) != this)
/*     */           {
/* 491 */             int var13 = par1World.rand.nextInt(16) & var9;
/*     */             
/* 493 */             if (var13 > 0)
/*     */             {
/* 495 */               par1World.setBlock(par2, par3 - 1, par4, this.blockID, var13, 2);
/*     */             }
/*     */           }
/*     */         
/* 499 */         } else if (var12 == this.blockID) {
/*     */           
/* 501 */           int var13 = par1World.rand.nextInt(16) & var9;
/* 502 */           int var14 = par1World.getBlockMetadata(par2, par3 - 1, par4);
/*     */           
/* 504 */           if (var14 != (var14 | var13))
/*     */           {
/* 506 */             par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, var14 | var13, 2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 513 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockHardness(int metadata) {
/* 585 */     return super.getBlockHardness(metadata) * getNumVines(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getNumVines(int metadata) {
/* 590 */     int num_sides = 0;
/*     */     
/* 592 */     if (BitHelper.isBitSet(metadata, 1)) {
/* 593 */       num_sides++;
/*     */     }
/* 595 */     if (BitHelper.isBitSet(metadata, 2)) {
/* 596 */       num_sides++;
/*     */     }
/* 598 */     if (BitHelper.isBitSet(metadata, 4)) {
/* 599 */       num_sides++;
/*     */     }
/* 601 */     if (BitHelper.isBitSet(metadata, 8)) {
/* 602 */       num_sides++;
/*     */     }
/* 604 */     if (num_sides == 0) {
/* 605 */       num_sides = 1;
/*     */     }
/* 607 */     return num_sides;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack createStackedBlock(int metadata) {
/* 612 */     return new ItemStack(this, getNumVines(metadata), getItemSubtype(metadata));
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
/*     */   public final int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 652 */     if (!info.wasReplaced()) {
/* 653 */       info.world.destroyBlock(info, false, true);
/*     */     }
/* 655 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePlacedAt(World world, int x, int y, int z, int metadata) {
/* 660 */     Block existing_block = world.getBlock(x, y, z);
/*     */     
/* 662 */     if (existing_block == this) {
/*     */       
/* 664 */       int existing_metadata = world.getBlockMetadata(x, y, z);
/*     */       int i;
/* 666 */       for (i = 1; i <= 8; i <<= 1) {
/*     */         
/* 668 */         if (BitHelper.isBitSet(metadata, i) && !BitHelper.isBitSet(existing_metadata, i)) {
/* 669 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 673 */     return super.canBePlacedAt(world, x, y, z, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 678 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 683 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAlwaysReplaceable() {
/* 688 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean showDestructionParticlesWhenReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 693 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockVine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */