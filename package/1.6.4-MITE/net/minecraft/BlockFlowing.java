/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockFlowing
/*     */   extends BlockFluid
/*     */ {
/*     */   int numAdjacentSources;
/*  18 */   boolean[] isOptimalFlowDirection = new boolean[4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  24 */   int[] flowCost = new int[4];
/*     */ 
/*     */   
/*     */   protected BlockFlowing(int par1, Material par2Material) {
/*  28 */     super(par1, par2Material);
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
/*     */   private void updateFlow(World par1World, int par2, int par3, int par4) {
/*  41 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/*  42 */     par1World.setBlock(par2, par3, par4, this.blockID + 1, var5, 2);
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
/*     */   private boolean hasStationaryPeerNeighbor(World world, int x, int y, int z) {
/*  58 */     if (world.getBlockId(x - 1, y, z) == this.blockID + 1)
/*  59 */       return true; 
/*  60 */     if (world.getBlockId(x + 1, y, z) == this.blockID + 1)
/*  61 */       return true; 
/*  62 */     if (world.getBlockId(x, y, z - 1) == this.blockID + 1)
/*  63 */       return true; 
/*  64 */     if (world.getBlockId(x, y, z + 1) == this.blockID + 1) {
/*  65 */       return true;
/*     */     }
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean handleFreefall(World world, int x, int y, int z) {
/*  72 */     if (world.getDistanceSqToNearestPlayer(x, z) < 256.0D && world.isAirBlock(x, y - 1, z) && world.getBlockMaterial(x, y + 1, z) != this.blockMaterial && !hasStationaryPeerNeighbor(world, x, y, z)) {
/*     */ 
/*     */       
/*  75 */       world.setBlock(x, y, z, 0, 0, 2);
/*     */       
/*  77 */       y--;
/*     */ 
/*     */ 
/*     */       
/*  81 */       world.setBlock(x, y, z, this.blockID);
/*     */ 
/*     */       
/*  84 */       if (this.blockMaterial == Material.water) {
/*  85 */         world.scheduleBlockChange(x, y, z, this.blockID + 1, Block.waterMoving.blockID, 1, 8);
/*  86 */       } else if (this.blockMaterial == Material.lava) {
/*     */ 
/*     */         
/*  89 */         world.scheduleBlockChange(x, y, z, this.blockID, Block.lavaMoving.blockID, 1, world.doesLavaFlowQuicklyInThisWorld() ? 16 : 48);
/*     */       } 
/*  91 */       return true;
/*     */     } 
/*     */     
/*  94 */     return false;
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
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 161 */     if (handleFreefall(par1World, par2, par3, par4)) {
/* 162 */       return true;
/*     */     }
/* 164 */     boolean changed_state = false;
/*     */     
/* 166 */     int var6 = getFlowDecay(par1World, par2, par3, par4);
/* 167 */     byte var7 = 1;
/*     */     
/* 169 */     if (this.blockMaterial == Material.lava && !par1World.provider.isHellWorld)
/*     */     {
/* 171 */       var7 = 2;
/*     */     }
/*     */     
/* 174 */     boolean var8 = true;
/* 175 */     int var9 = tickRate(par1World);
/*     */ 
/*     */     
/* 178 */     if (var6 > 0) {
/*     */       
/* 180 */       byte var10 = -100;
/* 181 */       this.numAdjacentSources = 0;
/* 182 */       int var13 = getSmallestFlowDecay(par1World, par2 - 1, par3, par4, var10);
/* 183 */       var13 = getSmallestFlowDecay(par1World, par2 + 1, par3, par4, var13);
/* 184 */       var13 = getSmallestFlowDecay(par1World, par2, par3, par4 - 1, var13);
/* 185 */       var13 = getSmallestFlowDecay(par1World, par2, par3, par4 + 1, var13);
/* 186 */       int var11 = var13 + var7;
/*     */       
/* 188 */       if (var11 >= 8 || var13 < 0)
/*     */       {
/* 190 */         var11 = -1;
/*     */       }
/*     */       
/* 193 */       if (getFlowDecay(par1World, par2, par3 + 1, par4) >= 0) {
/*     */         
/* 195 */         int var12 = getFlowDecay(par1World, par2, par3 + 1, par4);
/*     */         
/* 197 */         if (var12 >= 8) {
/*     */           
/* 199 */           var11 = var12;
/*     */         }
/*     */         else {
/*     */           
/* 203 */           var11 = var12 + 8;
/*     */         } 
/*     */       } 
/*     */       
/* 207 */       if (this.numAdjacentSources >= 2 && this.blockMaterial == Material.water)
/*     */       {
/* 209 */         if (par1World.getBlockMaterial(par2, par3 - 1, par4).isSolid()) {
/*     */           
/* 211 */           var11 = 0;
/*     */         }
/* 213 */         else if (par1World.getBlockMaterial(par2, par3 - 1, par4) == this.blockMaterial && par1World.getBlockMetadata(par2, par3 - 1, par4) == 0) {
/*     */           
/* 215 */           var11 = 0;
/*     */         } 
/*     */       }
/*     */       
/* 219 */       if (this.blockMaterial == Material.lava && var6 < 8 && var11 < 8 && var11 > var6 && par5Random.nextInt(4) != 0)
/*     */       {
/* 221 */         var9 *= 4;
/*     */       }
/*     */       
/* 224 */       if (var11 == var6) {
/*     */         
/* 226 */         if (var8)
/*     */         {
/* 228 */           updateFlow(par1World, par2, par3, par4);
/* 229 */           changed_state = true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 234 */         var6 = var11;
/*     */         
/* 236 */         if (var11 < 0) {
/*     */           
/* 238 */           par1World.setBlockToAir(par2, par3, par4);
/*     */         }
/*     */         else {
/*     */           
/* 242 */           par1World.setBlockMetadataWithNotify(par2, par3, par4, var11, 2);
/* 243 */           par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, var9);
/* 244 */           par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
/*     */         } 
/*     */         
/* 247 */         changed_state = true;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 252 */       updateFlow(par1World, par2, par3, par4);
/* 253 */       changed_state = true;
/*     */     } 
/*     */     
/* 256 */     if (liquidCanDisplaceBlock(par1World, par2, par3 - 1, par4)) {
/*     */       
/* 258 */       if (this.blockMaterial == Material.lava && par1World.getBlockMaterial(par2, par3 - 1, par4) == Material.water) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 263 */         if (!par1World.isRemote) {
/* 264 */           par1World.tryConvertWaterToCobblestone(par2, par3 - 1, par4);
/*     */         }
/*     */         
/* 267 */         return changed_state;
/*     */       } 
/*     */       
/* 270 */       if (var6 >= 8)
/*     */       {
/*     */         
/* 273 */         flowIntoBlock(par1World, par2, par3 - 1, par4, var6);
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 278 */         flowIntoBlock(par1World, par2, par3 - 1, par4, var6 + 8);
/*     */       }
/*     */     
/*     */     }
/* 282 */     else if (var6 >= 0 && (var6 == 0 || par1World.doesBlockBlockFluids(par2, par3 - 1, par4))) {
/*     */       
/* 284 */       boolean[] var14 = getOptimalFlowDirections(par1World, par2, par3, par4);
/* 285 */       int var11 = var6 + var7;
/*     */       
/* 287 */       if (var6 >= 8)
/*     */       {
/* 289 */         var11 = 1;
/*     */       }
/*     */       
/* 292 */       if (var11 >= 8)
/*     */       {
/*     */         
/* 295 */         return changed_state;
/*     */       }
/*     */       
/* 298 */       if (var14[0])
/*     */       {
/* 300 */         flowIntoBlock(par1World, par2 - 1, par3, par4, var11);
/*     */       }
/*     */       
/* 303 */       if (var14[1])
/*     */       {
/* 305 */         flowIntoBlock(par1World, par2 + 1, par3, par4, var11);
/*     */       }
/*     */       
/* 308 */       if (var14[2])
/*     */       {
/* 310 */         flowIntoBlock(par1World, par2, par3, par4 - 1, var11);
/*     */       }
/*     */       
/* 313 */       if (var14[3])
/*     */       {
/* 315 */         flowIntoBlock(par1World, par2, par3, par4 + 1, var11);
/*     */       }
/*     */     } 
/*     */     
/* 319 */     return changed_state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void flowIntoBlock(World par1World, int par2, int par3, int par4, int par5) {
/* 328 */     if (liquidCanDisplaceBlock(par1World, par2, par3, par4)) {
/*     */       
/* 330 */       int var6 = par1World.getBlockId(par2, par3, par4);
/*     */       
/* 332 */       if (var6 > 0)
/*     */       {
/* 334 */         if (this.blockMaterial == Material.lava) {
/*     */ 
/*     */           
/* 337 */           par1World.blockFX(EnumBlockFX.lava_mixing_with_water, par2, par3, par4);
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 343 */           Block block = Block.blocksList[var6];
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 348 */           block.dropBlockAsEntityItem((new BlockBreakInfo(par1World, par2, par3, par4)).setFlooded(this));
/*     */         } 
/*     */       }
/*     */       
/* 352 */       if (this.blockMaterial == Material.water)
/*     */       {
/* 354 */         if (par1World.getBlock(par2, par3 - 1, par4) == mantleOrCore) {
/*     */           
/* 356 */           par1World.blockFX(EnumBlockFX.steam, par2, par3, par4);
/*     */           
/*     */           return;
/*     */         } 
/*     */       }
/* 361 */       par1World.setBlock(par2, par3, par4, this.blockID, par5, 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int calculateFlowCost(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 372 */     int var7 = 1000;
/*     */     
/* 374 */     for (int var8 = 0; var8 < 4; var8++) {
/*     */       
/* 376 */       if ((var8 != 0 || par6 != 1) && (var8 != 1 || par6 != 0) && (var8 != 2 || par6 != 3) && (var8 != 3 || par6 != 2)) {
/*     */         
/* 378 */         int var9 = par2;
/* 379 */         int var11 = par4;
/*     */         
/* 381 */         if (var8 == 0)
/*     */         {
/* 383 */           var9 = par2 - 1;
/*     */         }
/*     */         
/* 386 */         if (var8 == 1)
/*     */         {
/* 388 */           var9++;
/*     */         }
/*     */         
/* 391 */         if (var8 == 2)
/*     */         {
/* 393 */           var11 = par4 - 1;
/*     */         }
/*     */         
/* 396 */         if (var8 == 3)
/*     */         {
/* 398 */           var11++;
/*     */         }
/*     */ 
/*     */         
/* 402 */         if (!par1World.doesBlockBlockFluids(var9, par3, var11) && (par1World.getBlockMaterial(var9, par3, var11) != this.blockMaterial || par1World.getBlockMetadata(var9, par3, var11) != 0)) {
/*     */ 
/*     */           
/* 405 */           if (!par1World.doesBlockBlockFluids(var9, par3 - 1, var11))
/*     */           {
/* 407 */             return par5;
/*     */           }
/*     */           
/* 410 */           if (par5 < 4) {
/*     */             
/* 412 */             int var12 = calculateFlowCost(par1World, var9, par3, var11, par5 + 1, var8);
/*     */             
/* 414 */             if (var12 < var7)
/*     */             {
/* 416 */               var7 = var12;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 423 */     return var7;
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
/*     */   private boolean[] getOptimalFlowDirections(World par1World, int par2, int par3, int par4) {
/*     */     int var5;
/* 436 */     for (var5 = 0; var5 < 4; var5++) {
/*     */       
/* 438 */       this.flowCost[var5] = 1000;
/* 439 */       int i = par2;
/* 440 */       int var8 = par4;
/*     */       
/* 442 */       if (var5 == 0)
/*     */       {
/* 444 */         i = par2 - 1;
/*     */       }
/*     */       
/* 447 */       if (var5 == 1)
/*     */       {
/* 449 */         i++;
/*     */       }
/*     */       
/* 452 */       if (var5 == 2)
/*     */       {
/* 454 */         var8 = par4 - 1;
/*     */       }
/*     */       
/* 457 */       if (var5 == 3)
/*     */       {
/* 459 */         var8++;
/*     */       }
/*     */ 
/*     */       
/* 463 */       if (!par1World.doesBlockBlockFluids(i, par3, var8) && (par1World.getBlockMaterial(i, par3, var8) != this.blockMaterial || par1World.getBlockMetadata(i, par3, var8) != 0))
/*     */       {
/*     */         
/* 466 */         if (par1World.doesBlockBlockFluids(i, par3 - 1, var8)) {
/*     */           
/* 468 */           this.flowCost[var5] = calculateFlowCost(par1World, i, par3, var8, 1, var5);
/*     */         }
/*     */         else {
/*     */           
/* 472 */           this.flowCost[var5] = 0;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 477 */     var5 = this.flowCost[0];
/*     */     int var6;
/* 479 */     for (var6 = 1; var6 < 4; var6++) {
/*     */       
/* 481 */       if (this.flowCost[var6] < var5)
/*     */       {
/* 483 */         var5 = this.flowCost[var6];
/*     */       }
/*     */     } 
/*     */     
/* 487 */     for (var6 = 0; var6 < 4; var6++)
/*     */     {
/* 489 */       this.isOptimalFlowDirection[var6] = (this.flowCost[var6] == var5);
/*     */     }
/*     */     
/* 492 */     return this.isOptimalFlowDirection;
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
/*     */   protected int getSmallestFlowDecay(World par1World, int par2, int par3, int par4, int par5) {
/* 558 */     int var6 = getFlowDecay(par1World, par2, par3, par4);
/*     */     
/* 560 */     if (var6 < 0)
/*     */     {
/* 562 */       return par5;
/*     */     }
/*     */ 
/*     */     
/* 566 */     if (var6 == 0)
/*     */     {
/* 568 */       this.numAdjacentSources++;
/*     */     }
/*     */     
/* 571 */     if (var6 >= 8)
/*     */     {
/* 573 */       var6 = 0;
/*     */     }
/*     */     
/* 576 */     return (par5 >= 0 && var6 >= par5) ? par5 : var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean liquidCanDisplaceBlock(World par1World, int par2, int par3, int par4) {
/* 585 */     Material var5 = par1World.getBlockMaterial(par2, par3, par4);
/*     */     
/* 587 */     return (var5 == this.blockMaterial) ? false : ((var5 == Material.lava) ? false : (!par1World.doesBlockBlockFluids(par2, par3, par4)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 595 */     super.onBlockAdded(par1World, par2, par3, par4);
/*     */     
/* 597 */     if (par1World.getBlockId(par2, par3, par4) == this.blockID)
/*     */     {
/* 599 */       par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate(par1World));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82506_l() {
/* 605 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFlowing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */