/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockFluid
/*     */   extends Block
/*     */ {
/*     */   private Icon[] theIcon;
/*     */   
/*     */   protected BlockFluid(int par1, Material par2Material) {
/*  13 */     super(par1, par2Material, (par2Material == Material.water) ? (new BlockConstants()).setUsesAlphaBlending() : (new BlockConstants()).setNeverHidesAdjacentFaces());
/*  14 */     float var3 = 0.0F;
/*  15 */     float var4 = 0.0F;
/*  16 */     setBlockBoundsForAllThreads((0.0F + var4), (0.0F + var3), (0.0F + var4), (1.0F + var4), (1.0F + var3), (1.0F + var4));
/*  17 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  22 */     return "Bits 1, 2, and 4 used for (inverted) fluid height, bit 8 set if falling";
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
/*     */   public final int getBlockColor() {
/*  42 */     return 16777215;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  52 */     if (this.blockMaterial != Material.water)
/*     */     {
/*  54 */       return 16777215;
/*     */     }
/*     */ 
/*     */     
/*  58 */     int var5 = 0;
/*  59 */     int var6 = 0;
/*  60 */     int var7 = 0;
/*     */     
/*  62 */     for (int var8 = -1; var8 <= 1; var8++) {
/*     */       
/*  64 */       for (int var9 = -1; var9 <= 1; var9++) {
/*     */         
/*  66 */         int var10 = (par1IBlockAccess.getBiomeGenForCoords(par2 + var9, par4 + var8)).waterColorMultiplier;
/*  67 */         var5 += (var10 & 0xFF0000) >> 16;
/*  68 */         var6 += (var10 & 0xFF00) >> 8;
/*  69 */         var7 += var10 & 0xFF;
/*     */       } 
/*     */     } 
/*     */     
/*  73 */     return (var5 / 9 & 0xFF) << 16 | (var6 / 9 & 0xFF) << 8 | var7 / 9 & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getFluidHeightPercent(int par0) {
/*  82 */     if (par0 >= 8)
/*     */     {
/*  84 */       par0 = 0;
/*     */     }
/*     */     
/*  87 */     return (par0 + 1) / 9.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Icon getIcon(int par1, int par2) {
/*  96 */     return (par1 != 0 && par1 != 1) ? this.theIcon[1] : this.theIcon[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getFlowDecay(World par1World, int par2, int par3, int par4) {
/* 105 */     return (par1World.getBlockMaterial(par2, par3, par4) == this.blockMaterial) ? par1World.getBlockMetadata(par2, par3, par4) : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getEffectiveFlowDecay(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 114 */     if (par1IBlockAccess.getBlockMaterial(par2, par3, par4) != this.blockMaterial)
/*     */     {
/* 116 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 120 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */     
/* 122 */     if (var5 >= 8)
/*     */     {
/* 124 */       var5 = 0;
/*     */     }
/*     */     
/* 127 */     return var5;
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
/*     */   public boolean canCollideCheck(int par1, boolean par2) {
/* 156 */     return (par2 && par1 == 0);
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
/*     */   public final boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 176 */     Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3, par4);
/* 177 */     return (var6 == this.blockMaterial) ? false : ((par5 == 1) ? true : ((var6 == Material.ice) ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5)));
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
/*     */   public final int getRenderType() {
/* 195 */     return 4;
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
/*     */   private final boolean getFlowVectorHelper(IBlockAccess block_access, int x, int y, int z, int side) {
/* 217 */     Material material = block_access.getBlockMaterial(x, y, z);
/*     */     
/* 219 */     return (material == this.blockMaterial) ? false : ((side == 1) ? true : ((material == Material.ice) ? false : material.isSolid()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Vec3 getFlowVector(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 227 */     Vec3 var5 = par1IBlockAccess.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
/* 228 */     int var6 = getEffectiveFlowDecay(par1IBlockAccess, par2, par3, par4);
/*     */     
/* 230 */     for (int var7 = 0; var7 < 4; var7++) {
/*     */       
/* 232 */       int var8 = par2;
/* 233 */       int var10 = par4;
/*     */       
/* 235 */       if (var7 == 0)
/*     */       {
/* 237 */         var8 = par2 - 1;
/*     */       }
/*     */       
/* 240 */       if (var7 == 1)
/*     */       {
/* 242 */         var10 = par4 - 1;
/*     */       }
/*     */       
/* 245 */       if (var7 == 2)
/*     */       {
/* 247 */         var8++;
/*     */       }
/*     */       
/* 250 */       if (var7 == 3)
/*     */       {
/* 252 */         var10++;
/*     */       }
/*     */       
/* 255 */       int var11 = getEffectiveFlowDecay(par1IBlockAccess, var8, par3, var10);
/*     */ 
/*     */       
/* 258 */       if (var11 < 0) {
/*     */ 
/*     */         
/* 261 */         if (!par1IBlockAccess.isBlockSolid(var8, par3, var10)) {
/*     */           
/* 263 */           var11 = getEffectiveFlowDecay(par1IBlockAccess, var8, par3 - 1, var10);
/*     */           
/* 265 */           if (var11 >= 0)
/*     */           {
/* 267 */             int var12 = var11 - var6 - 8;
/* 268 */             var5 = var5.addVector(((var8 - par2) * var12), ((par3 - par3) * var12), ((var10 - par4) * var12));
/*     */           }
/*     */         
/*     */         } 
/* 272 */       } else if (var11 >= 0) {
/*     */         
/* 274 */         int var12 = var11 - var6;
/* 275 */         var5 = var5.addVector(((var8 - par2) * var12), ((par3 - par3) * var12), ((var10 - par4) * var12));
/*     */       } 
/*     */     } 
/*     */     
/* 279 */     if (par1IBlockAccess.getBlockMetadata(par2, par3, par4) >= 8) {
/*     */       
/* 281 */       boolean var13 = false;
/*     */ 
/*     */       
/* 284 */       if (var13 || getFlowVectorHelper(par1IBlockAccess, par2, par3, par4 - 1, 2))
/*     */       {
/* 286 */         var13 = true;
/*     */       }
/*     */ 
/*     */       
/* 290 */       if (var13 || getFlowVectorHelper(par1IBlockAccess, par2, par3, par4 + 1, 3))
/*     */       {
/* 292 */         var13 = true;
/*     */       }
/*     */ 
/*     */       
/* 296 */       if (var13 || getFlowVectorHelper(par1IBlockAccess, par2 - 1, par3, par4, 4))
/*     */       {
/* 298 */         var13 = true;
/*     */       }
/*     */ 
/*     */       
/* 302 */       if (var13 || getFlowVectorHelper(par1IBlockAccess, par2 + 1, par3, par4, 5))
/*     */       {
/* 304 */         var13 = true;
/*     */       }
/*     */ 
/*     */       
/* 308 */       if (var13 || getFlowVectorHelper(par1IBlockAccess, par2, par3 + 1, par4 - 1, 2))
/*     */       {
/* 310 */         var13 = true;
/*     */       }
/*     */ 
/*     */       
/* 314 */       if (var13 || getFlowVectorHelper(par1IBlockAccess, par2, par3 + 1, par4 + 1, 3))
/*     */       {
/* 316 */         var13 = true;
/*     */       }
/*     */ 
/*     */       
/* 320 */       if (var13 || getFlowVectorHelper(par1IBlockAccess, par2 - 1, par3 + 1, par4, 4))
/*     */       {
/* 322 */         var13 = true;
/*     */       }
/*     */ 
/*     */       
/* 326 */       if (var13 || getFlowVectorHelper(par1IBlockAccess, par2 + 1, par3 + 1, par4, 5))
/*     */       {
/* 328 */         var13 = true;
/*     */       }
/*     */       
/* 331 */       if (var13)
/*     */       {
/* 333 */         var5 = var5.normalize().addVector(0.0D, -6.0D, 0.0D);
/*     */       }
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
/* 365 */     var5 = var5.normalize();
/* 366 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void velocityToAddToEntity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {
/* 374 */     Vec3 var7 = getFlowVector(par1World, par2, par3, par4);
/* 375 */     par6Vec3.xCoord += var7.xCoord;
/* 376 */     par6Vec3.yCoord += var7.yCoord;
/* 377 */     par6Vec3.zCoord += var7.zCoord;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int tickRate(World par1World) {
/* 387 */     return (this.blockMaterial == Material.water) ? 5 : ((this.blockMaterial == Material.lava) ? (par1World.doesLavaFlowQuicklyInThisWorld() ? 10 : 30) : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getMixedBrightnessForBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 396 */     int var5 = par1IBlockAccess.getLightBrightnessForSkyBlocks(par2, par3, par4, 0);
/* 397 */     int var6 = par1IBlockAccess.getLightBrightnessForSkyBlocks(par2, par3 + 1, par4, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 402 */     if (this.blockMaterial == Material.lava)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 407 */       return 6553796;
/*     */     }
/* 409 */     int var7 = var5 & 0xFF;
/* 410 */     int var8 = var6 & 0xFF;
/* 411 */     int var9 = var5 >> 16 & 0xFF;
/* 412 */     int var10 = var6 >> 16 & 0xFF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 419 */     return ((var7 > var8) ? var7 : var8) | ((var9 > var10) ? var9 : var10) << 16;
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
/*     */   public final float getBlockBrightness(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 445 */     float var5 = par1IBlockAccess.getLightBrightness(par2, par3, par4);
/* 446 */     float var6 = par1IBlockAccess.getLightBrightness(par2, par3 + 1, par4);
/* 447 */     return (var5 > var6) ? var5 : var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getRenderBlockPass() {
/* 456 */     return (this.blockMaterial == Material.water) ? 1 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 467 */     if (this.blockMaterial == Material.water) {
/*     */ 
/*     */       
/* 470 */       if (par5Random.nextInt(20) == 0) {
/*     */         
/* 472 */         int i = par1World.getBlockMetadata(par2, par3, par4);
/*     */         
/* 474 */         if (i <= 0 || i >= 8)
/*     */         {
/*     */           
/* 477 */           par1World.spawnParticle(EnumParticle.suspended, (par2 + par5Random.nextFloat()), (par3 + par5Random.nextFloat()), (par4 + par5Random.nextFloat()), 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       } 
/*     */       
/* 481 */       for (int var6 = 0; var6 < 0; var6++) {
/*     */         
/* 483 */         int var7 = par5Random.nextInt(4);
/* 484 */         int var8 = par2;
/* 485 */         int var9 = par4;
/*     */         
/* 487 */         if (var7 == 0)
/*     */         {
/* 489 */           var8 = par2 - 1;
/*     */         }
/*     */         
/* 492 */         if (var7 == 1)
/*     */         {
/* 494 */           var8++;
/*     */         }
/*     */         
/* 497 */         if (var7 == 2)
/*     */         {
/* 499 */           var9 = par4 - 1;
/*     */         }
/*     */         
/* 502 */         if (var7 == 3)
/*     */         {
/* 504 */           var9++;
/*     */         }
/*     */ 
/*     */         
/* 508 */         if (par1World.getBlockMaterial(var8, par3, var9) == Material.air && (par1World.isBlockSolid(var8, par3 - 1, var9) || par1World.isBlockLiquid(var8, par3 - 1, var9))) {
/*     */           
/* 510 */           float var10 = 0.0625F;
/* 511 */           double var11 = (par2 + par5Random.nextFloat());
/* 512 */           double var13 = (par3 + par5Random.nextFloat());
/* 513 */           double var15 = (par4 + par5Random.nextFloat());
/*     */           
/* 515 */           if (var7 == 0)
/*     */           {
/* 517 */             var11 = (par2 - var10);
/*     */           }
/*     */           
/* 520 */           if (var7 == 1)
/*     */           {
/* 522 */             var11 = ((par2 + 1) + var10);
/*     */           }
/*     */           
/* 525 */           if (var7 == 2)
/*     */           {
/* 527 */             var15 = (par4 - var10);
/*     */           }
/*     */           
/* 530 */           if (var7 == 3)
/*     */           {
/* 532 */             var15 = ((par4 + 1) + var10);
/*     */           }
/*     */           
/* 535 */           double var17 = 0.0D;
/* 536 */           double var19 = 0.0D;
/*     */           
/* 538 */           if (var7 == 0)
/*     */           {
/* 540 */             var17 = -var10;
/*     */           }
/*     */           
/* 543 */           if (var7 == 1)
/*     */           {
/* 545 */             var17 = var10;
/*     */           }
/*     */           
/* 548 */           if (var7 == 2)
/*     */           {
/* 550 */             var19 = -var10;
/*     */           }
/*     */           
/* 553 */           if (var7 == 3)
/*     */           {
/* 555 */             var19 = var10;
/*     */           }
/*     */ 
/*     */           
/* 559 */           par1World.spawnParticle(EnumParticle.splash, var11, var13, var15, var17, 0.0D, var19);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 564 */     if (this.blockMaterial == Material.water && par5Random.nextInt(64) == 0) {
/*     */       
/* 566 */       int var6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */       
/* 568 */       if (var6 > 0 && var6 < 8)
/*     */       {
/* 570 */         par1World.playSound((par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), "liquid.water", par5Random.nextFloat() * 0.25F + 0.75F, par5Random.nextFloat() * 1.0F + 0.5F, false);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 578 */     if (this.blockMaterial == Material.lava && par1World.getBlockMaterial(par2, par3 + 1, par4) == Material.air && !par1World.isBlockStandardFormOpaqueCube(par2, par3 + 1, par4)) {
/*     */       
/* 580 */       if (par5Random.nextInt(100) == 0) {
/*     */         
/* 582 */         double var21 = (par2 + par5Random.nextFloat());
/* 583 */         double var22 = par3 + this.maxY[Minecraft.getThreadIndex()];
/* 584 */         double var23 = (par4 + par5Random.nextFloat());
/*     */         
/* 586 */         par1World.spawnParticle(EnumParticle.lava, var21, var22, var23, 0.0D, 0.0D, 0.0D);
/* 587 */         par1World.playSound(var21, var22, var23, "liquid.lavapop", 0.2F + par5Random.nextFloat() * 0.2F, 0.9F + par5Random.nextFloat() * 0.15F, false);
/*     */       } 
/*     */       
/* 590 */       if (par5Random.nextInt(200) == 0)
/*     */       {
/* 592 */         par1World.playSound(par2, par3, par4, "liquid.lava", 0.2F + par5Random.nextFloat() * 0.2F, 0.9F + par5Random.nextFloat() * 0.15F, false);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 598 */     if (par5Random.nextInt(10) == 0 && par1World.isBlockTopFlatAndSolid(par2, par3 - 1, par4) && !par1World.isBlockTopFlatAndSolid(par2, par3 - 2, par4)) {
/*     */       
/* 600 */       double var21 = (par2 + par5Random.nextFloat());
/* 601 */       double var22 = par3 - 1.05D;
/* 602 */       double var23 = (par4 + par5Random.nextFloat());
/*     */       
/* 604 */       if (this.blockMaterial == Material.water) {
/*     */ 
/*     */         
/* 607 */         par1World.spawnParticle(EnumParticle.dripWater, var21, var22, var23, 0.0D, 0.0D, 0.0D);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 612 */         par1World.spawnParticle(EnumParticle.dripLava, var21, var22, var23, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getFlowDirection(IBlockAccess par0IBlockAccess, int par1, int par2, int par3, Material par4Material) {
/* 622 */     Vec3 var5 = null;
/*     */     
/* 624 */     if (par4Material == Material.water)
/*     */     {
/* 626 */       var5 = Block.waterMoving.getFlowVector(par0IBlockAccess, par1, par2, par3);
/*     */     }
/*     */     
/* 629 */     if (par4Material == Material.lava)
/*     */     {
/* 631 */       var5 = Block.lavaMoving.getFlowVector(par0IBlockAccess, par1, par2, par3);
/*     */     }
/*     */     
/* 634 */     return (var5.xCoord == 0.0D && var5.zCoord == 0.0D) ? -1000.0D : (Math.atan2(var5.zCoord, var5.xCoord) - 1.5707963267948966D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 642 */     checkForHarden(par1World, par2, par3, par4);
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
/*     */   public boolean onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
/* 659 */     return checkForHarden(par1World, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean checkForHarden(World par1World, int par2, int par3, int par4) {
/* 668 */     if (par1World.getBlockId(par2, par3, par4) == this.blockID)
/*     */     {
/* 670 */       if (this.blockMaterial == Material.lava) {
/*     */         
/* 672 */         boolean var5 = false;
/*     */         
/* 674 */         if (var5 || par1World.getBlockMaterial(par2, par3, par4 - 1) == Material.water)
/*     */         {
/* 676 */           var5 = true;
/*     */         }
/*     */         
/* 679 */         if (var5 || par1World.getBlockMaterial(par2, par3, par4 + 1) == Material.water)
/*     */         {
/* 681 */           var5 = true;
/*     */         }
/*     */         
/* 684 */         if (var5 || par1World.getBlockMaterial(par2 - 1, par3, par4) == Material.water)
/*     */         {
/* 686 */           var5 = true;
/*     */         }
/*     */         
/* 689 */         if (var5 || par1World.getBlockMaterial(par2 + 1, par3, par4) == Material.water)
/*     */         {
/* 691 */           var5 = true;
/*     */         }
/*     */         
/* 694 */         if (var5 || par1World.getBlockMaterial(par2, par3 + 1, par4) == Material.water)
/*     */         {
/* 696 */           var5 = true;
/*     */         }
/*     */         
/* 699 */         if (var5)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 715 */           if (!par1World.isRemote && par1World.tryConvertLavaToCobblestoneOrObsidian(par2, par3, par4)) {
/* 716 */             return true;
/*     */           }
/*     */         }
/*     */       } 
/*     */     }
/* 721 */     return false;
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 743 */     if (this.blockMaterial == Material.lava) {
/*     */       
/* 745 */       this.theIcon = new Icon[] { par1IconRegister.registerIcon("lava_still"), par1IconRegister.registerIcon("lava_flow") };
/*     */     }
/*     */     else {
/*     */       
/* 749 */       this.theIcon = new Icon[] { par1IconRegister.registerIcon("water_still"), par1IconRegister.registerIcon("water_flow") };
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Icon getFluidIcon(String par0Str) {
/* 755 */     return (par0Str == "water_still") ? Block.waterMoving.theIcon[0] : ((par0Str == "water_flow") ? Block.waterMoving.theIcon[1] : ((par0Str == "lava_still") ? Block.lavaMoving.theIcon[0] : ((par0Str == "lava_flow") ? Block.lavaMoving.theIcon[1] : null)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFullWaterBlock(World world, int x, int y, int z, boolean include_moving_water) {
/* 760 */     return isFullWaterBlock(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z), include_moving_water);
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
/*     */   public static boolean isFullWaterBlock(Block block, int metadata, boolean include_moving_water) {
/* 779 */     if (metadata != 0) {
/* 780 */       return false;
/*     */     }
/* 782 */     if (include_moving_water && block == waterMoving) {
/* 783 */       return true;
/*     */     }
/* 785 */     return (block == waterStill);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPartialWaterBlock(World world, int x, int y, int z) {
/* 790 */     return isPartialWaterBlock(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPartialWaterBlock(Block block, int metadata) {
/* 795 */     if (block == waterMoving || block == waterStill) {
/* 796 */       return !isFullWaterBlock(block, metadata, true);
/*     */     }
/* 798 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFullLavaBlock(World world, int x, int y, int z, boolean include_moving_lava) {
/* 803 */     return isFullLavaBlock(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z), include_moving_lava);
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
/*     */   public static boolean isFullLavaBlock(Block block, int metadata, boolean include_moving_lava) {
/* 819 */     if (metadata != 0) {
/* 820 */       return false;
/*     */     }
/* 822 */     if (include_moving_lava && block == lavaMoving) {
/* 823 */       return true;
/*     */     }
/* 825 */     return (block == lavaStill);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 830 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 835 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 840 */     return "moving";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 845 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 850 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFluid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */