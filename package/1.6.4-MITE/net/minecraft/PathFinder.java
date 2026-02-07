/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathFinder
/*     */ {
/*     */   private IBlockAccess worldMap;
/*   9 */   private Path path = new Path();
/*     */ 
/*     */   
/*  12 */   private IntHashMap pointMap = new IntHashMap();
/*     */ 
/*     */   
/*  15 */   private PathPoint[] pathOptions = new PathPoint[32];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean allow_open_wooden_doors;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isMovementBlockAllowed;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean avoid_water;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canEntityDrown;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathFinder(IBlockAccess par1IBlockAccess, boolean can_pass_open_wooden_doors, boolean can_path_through_closed_wooden_doors, boolean avoid_water, boolean can_entity_swim) {
/*  42 */     this.worldMap = par1IBlockAccess;
/*  43 */     this.allow_open_wooden_doors = can_pass_open_wooden_doors;
/*  44 */     this.isMovementBlockAllowed = can_path_through_closed_wooden_doors;
/*  45 */     this.avoid_water = avoid_water;
/*  46 */     this.canEntityDrown = can_entity_swim;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathEntity createEntityPathTo(Entity par1Entity, Entity par2Entity, float max_path_length) {
/*  55 */     return createEntityPathTo(par1Entity, par2Entity.posX, par2Entity.boundingBox.minY, par2Entity.posZ, max_path_length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathEntity createEntityPathTo(Entity par1Entity, int par2, int par3, int par4, float par5) {
/*  63 */     return createEntityPathTo(par1Entity, (par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), par5);
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
/*     */   private PathEntity createEntityPathTo(Entity par1Entity, double par2, double par4, double par6, float max_path_length) {
/*  78 */     if (max_path_length > 32.0F) {
/*  79 */       max_path_length = 32.0F;
/*     */     }
/*  81 */     this.path.clearPath();
/*  82 */     this.pointMap.clearMap();
/*  83 */     boolean var9 = this.avoid_water;
/*  84 */     int var10 = MathHelper.floor_double(par1Entity.boundingBox.minY + 0.5D);
/*     */     
/*  86 */     if (this.canEntityDrown && par1Entity.isInWater()) {
/*     */       
/*  88 */       var10 = (int)par1Entity.boundingBox.minY;
/*     */       int var11;
/*  90 */       for (var11 = this.worldMap.getBlockId(MathHelper.floor_double(par1Entity.posX), var10, MathHelper.floor_double(par1Entity.posZ)); var11 == Block.waterMoving.blockID || var11 == Block.waterStill.blockID; var11 = this.worldMap.getBlockId(MathHelper.floor_double(par1Entity.posX), var10, MathHelper.floor_double(par1Entity.posZ)))
/*     */       {
/*  92 */         var10++;
/*     */       }
/*     */       
/*  95 */       var9 = this.avoid_water;
/*  96 */       this.avoid_water = false;
/*     */     }
/*     */     else {
/*     */       
/* 100 */       var10 = MathHelper.floor_double(par1Entity.boundingBox.minY + 0.5D);
/*     */     } 
/*     */     
/* 103 */     PathPoint var15 = openPoint(MathHelper.floor_double(par1Entity.boundingBox.minX), var10, MathHelper.floor_double(par1Entity.boundingBox.minZ));
/* 104 */     PathPoint var12 = openPoint(MathHelper.floor_double(par2 - (par1Entity.width / 2.0F)), MathHelper.floor_double(par4), MathHelper.floor_double(par6 - (par1Entity.width / 2.0F)));
/* 105 */     PathPoint var13 = new PathPoint(MathHelper.floor_float(par1Entity.width + 1.0F), MathHelper.floor_float(par1Entity.height + 1.0F), MathHelper.floor_float(par1Entity.width + 1.0F));
/* 106 */     PathEntity var14 = addToPath(par1Entity, var15, var12, var13, max_path_length);
/* 107 */     this.avoid_water = var9;
/* 108 */     return var14;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PathEntity addToPath(Entity par1Entity, PathPoint par2PathPoint, PathPoint par3PathPoint, PathPoint par4PathPoint, float max_path_length) {
/* 117 */     par2PathPoint.totalPathDistance = 0.0F;
/* 118 */     par2PathPoint.distanceToNext = par2PathPoint.func_75832_b(par3PathPoint);
/* 119 */     par2PathPoint.distanceToTarget = par2PathPoint.distanceToNext;
/* 120 */     this.path.clearPath();
/* 121 */     this.path.addPoint(par2PathPoint);
/* 122 */     PathPoint var6 = par2PathPoint;
/*     */     
/* 124 */     while (!this.path.isPathEmpty()) {
/*     */       
/* 126 */       PathPoint var7 = this.path.dequeue();
/*     */       
/* 128 */       if (var7.equals(par3PathPoint))
/*     */       {
/* 130 */         return createEntityPath(par2PathPoint, par3PathPoint);
/*     */       }
/*     */       
/* 133 */       if (var7.func_75832_b(par3PathPoint) < var6.func_75832_b(par3PathPoint))
/*     */       {
/* 135 */         var6 = var7;
/*     */       }
/*     */       
/* 138 */       var7.isFirst = true;
/* 139 */       int var8 = findPathOptions(par1Entity, var7, par4PathPoint, par3PathPoint, max_path_length);
/*     */       
/* 141 */       for (int var9 = 0; var9 < var8; var9++) {
/*     */         
/* 143 */         PathPoint var10 = this.pathOptions[var9];
/* 144 */         float var11 = var7.totalPathDistance + var7.func_75832_b(var10);
/*     */         
/* 146 */         if (!var10.isAssigned() || var11 < var10.totalPathDistance) {
/*     */           
/* 148 */           var10.previous = var7;
/* 149 */           var10.totalPathDistance = var11;
/* 150 */           var10.distanceToNext = var10.func_75832_b(par3PathPoint);
/*     */           
/* 152 */           if (var10.isAssigned()) {
/*     */             
/* 154 */             this.path.changeDistance(var10, var10.totalPathDistance + var10.distanceToNext);
/*     */           }
/*     */           else {
/*     */             
/* 158 */             var10.distanceToTarget = var10.totalPathDistance + var10.distanceToNext;
/* 159 */             this.path.addPoint(var10);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 165 */     if (var6 == par2PathPoint)
/*     */     {
/* 167 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 171 */     return createEntityPath(par2PathPoint, var6);
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
/*     */   private int findPathOptions(Entity par1Entity, PathPoint par2PathPoint, PathPoint par3PathPoint, PathPoint par4PathPoint, float max_path_length) {
/* 219 */     int var6 = 0;
/* 220 */     byte var7 = 0;
/*     */     
/* 222 */     if (getVerticalOffset(par1Entity, par2PathPoint.xCoord, par2PathPoint.yCoord + 1, par2PathPoint.zCoord, par3PathPoint) == 1)
/*     */     {
/* 224 */       var7 = 1;
/*     */     }
/*     */     
/* 227 */     PathPoint var8 = getSafePoint(par1Entity, par2PathPoint.xCoord, par2PathPoint.yCoord, par2PathPoint.zCoord + 1, par3PathPoint, var7);
/* 228 */     PathPoint var9 = getSafePoint(par1Entity, par2PathPoint.xCoord - 1, par2PathPoint.yCoord, par2PathPoint.zCoord, par3PathPoint, var7);
/* 229 */     PathPoint var10 = getSafePoint(par1Entity, par2PathPoint.xCoord + 1, par2PathPoint.yCoord, par2PathPoint.zCoord, par3PathPoint, var7);
/* 230 */     PathPoint var11 = getSafePoint(par1Entity, par2PathPoint.xCoord, par2PathPoint.yCoord, par2PathPoint.zCoord - 1, par3PathPoint, var7);
/*     */     
/* 232 */     float par5_sq = max_path_length * max_path_length;
/*     */     
/* 234 */     if (var8 != null && !var8.isFirst && var8.distanceToSq(par4PathPoint) < par5_sq)
/*     */     {
/* 236 */       this.pathOptions[var6++] = var8;
/*     */     }
/*     */     
/* 239 */     if (var9 != null && !var9.isFirst && var9.distanceToSq(par4PathPoint) < par5_sq)
/*     */     {
/* 241 */       this.pathOptions[var6++] = var9;
/*     */     }
/*     */     
/* 244 */     if (var10 != null && !var10.isFirst && var10.distanceToSq(par4PathPoint) < par5_sq)
/*     */     {
/* 246 */       this.pathOptions[var6++] = var10;
/*     */     }
/*     */     
/* 249 */     if (var11 != null && !var11.isFirst && var11.distanceToSq(par4PathPoint) < par5_sq)
/*     */     {
/* 251 */       this.pathOptions[var6++] = var11;
/*     */     }
/*     */     
/* 254 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PathPoint getSafePoint(Entity par1Entity, int par2, int par3, int par4, PathPoint par5PathPoint, int par6) {
/* 263 */     PathPoint var7 = null;
/* 264 */     int var8 = getVerticalOffset(par1Entity, par2, par3, par4, par5PathPoint);
/*     */     
/* 266 */     if (var8 == 2)
/*     */     {
/* 268 */       return openPoint(par2, par3, par4);
/*     */     }
/*     */ 
/*     */     
/* 272 */     if (var8 == 1)
/*     */     {
/* 274 */       var7 = openPoint(par2, par3, par4);
/*     */     }
/*     */     
/* 277 */     if (var7 == null && par6 > 0 && var8 != -3 && var8 != -4 && getVerticalOffset(par1Entity, par2, par3 + par6, par4, par5PathPoint) == 1) {
/*     */       
/* 279 */       var7 = openPoint(par2, par3 + par6, par4);
/* 280 */       par3 += par6;
/*     */     } 
/*     */     
/* 283 */     if (var7 != null) {
/*     */       
/* 285 */       int var9 = 0;
/* 286 */       int var10 = 0;
/*     */       
/* 288 */       while (par3 > 0) {
/*     */         
/* 290 */         var10 = getVerticalOffset(par1Entity, par2, par3 - 1, par4, par5PathPoint);
/*     */         
/* 292 */         if (this.avoid_water && var10 == -1)
/*     */         {
/* 294 */           return null;
/*     */         }
/*     */         
/* 297 */         if (var10 == 3) {
/* 298 */           return null;
/*     */         }
/* 300 */         if (var10 != 1) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 305 */         if (var9++ >= par1Entity.getMaxSafePointTries())
/*     */         {
/* 307 */           return null;
/*     */         }
/*     */         
/* 310 */         par3--;
/*     */         
/* 312 */         if (par3 > 0)
/*     */         {
/* 314 */           var7 = openPoint(par2, par3, par4);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 319 */       if (var10 == -2 && par1Entity.isHarmedByLava())
/*     */       {
/* 321 */         return null;
/*     */       }
/*     */     } 
/*     */     
/* 325 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final PathPoint openPoint(int par1, int par2, int par3) {
/* 334 */     int var4 = PathPoint.makeHash(par1, par2, par3);
/* 335 */     PathPoint var5 = (PathPoint)this.pointMap.lookup(var4);
/*     */     
/* 337 */     if (var5 == null) {
/*     */       
/* 339 */       var5 = new PathPoint(par1, par2, par3);
/* 340 */       this.pointMap.addKey(var4, var5);
/*     */     } 
/*     */     
/* 343 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVerticalOffset(Entity par1Entity, int par2, int par3, int par4, PathPoint par5PathPoint) {
/* 353 */     return func_82565_a(par1Entity, par2, par3, par4, par5PathPoint, this.avoid_water, this.isMovementBlockAllowed, this.allow_open_wooden_doors);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWoodenPortal(int block_id) {
/* 359 */     return (block_id == Block.doorWood.blockID || block_id == Block.trapdoor.blockID || block_id == Block.fenceGate.blockID);
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
/*     */   public static boolean isAClosedWoodenPortal(World world, int x, int y, int z) {
/* 394 */     Block block = world.getBlock(x, y, z);
/*     */     
/* 396 */     if (block == Block.doorWood) {
/*     */       
/* 398 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 400 */       if (BlockDoor.isTopHalf(metadata)) {
/* 401 */         return isAClosedWoodenPortal(world, x, y - 1, z);
/*     */       }
/* 403 */       return !BlockDoor.isOpen(metadata);
/*     */     } 
/* 405 */     if (block == Block.trapdoor)
/*     */     {
/* 407 */       return !BlockTrapDoor.isTrapdoorOpen(world.getBlockMetadata(x, y, z));
/*     */     }
/* 409 */     if (block == Block.fenceGate)
/*     */     {
/* 411 */       return !BlockFenceGate.isFenceGateOpen(world.getBlockMetadata(x, y, z));
/*     */     }
/*     */ 
/*     */     
/* 415 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int func_82565_a(Entity par0Entity, int par1, int par2, int par3, PathPoint par4PathPoint, boolean par5, boolean par6, boolean par7) {
/* 422 */     boolean var8 = false;
/*     */     
/* 424 */     for (int var9 = par1; var9 < par1 + par4PathPoint.xCoord; var9++) {
/*     */       
/* 426 */       for (int var10 = par2; var10 < par2 + par4PathPoint.yCoord; var10++) {
/*     */         
/* 428 */         for (int var11 = par3; var11 < par3 + par4PathPoint.zCoord; var11++) {
/*     */           
/* 430 */           int var12 = par0Entity.worldObj.getBlockId(var9, var10, var11);
/*     */           
/* 432 */           if (var12 > 0) {
/*     */             
/* 434 */             if (var12 == Block.trapdoor.blockID) {
/*     */               
/* 436 */               var8 = true;
/*     */             }
/* 438 */             else if (var12 != Block.waterMoving.blockID && var12 != Block.waterStill.blockID) {
/*     */ 
/*     */               
/* 441 */               if (!par7 && isWoodenPortal(var12))
/*     */               {
/* 443 */                 return 0;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 448 */               if (par5)
/*     */               {
/* 450 */                 return -1;
/*     */               }
/*     */               
/* 453 */               var8 = true;
/*     */             } 
/*     */             
/* 456 */             Block var13 = Block.blocksList[var12];
/* 457 */             int var14 = var13.getRenderType();
/*     */             
/* 459 */             if (var13 == Block.cactus && par0Entity.isEntityLiving()) {
/*     */               
/* 461 */               EntityLiving entity_living = par0Entity.getAsEntityLiving();
/*     */               
/* 463 */               if (entity_living.canBeDamagedByCacti())
/*     */               {
/* 465 */                 if (entity_living.tasks == null || !entity_living.tasks.isTaskExecuting(EntityAIControlledByPlayer.class)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 473 */                   BlockInfo info = entity_living.getBlockRestingOn3();
/*     */                   
/* 475 */                   if (info == null || info.block != Block.cactus) {
/* 476 */                     return 3;
/*     */                   }
/*     */                 } 
/*     */               }
/*     */             } 
/* 481 */             if (par0Entity.worldObj.blockGetRenderType(var9, var10, var11) == 9) {
/*     */               
/* 483 */               int var18 = MathHelper.floor_double(par0Entity.posX);
/* 484 */               int var16 = MathHelper.floor_double(par0Entity.posY);
/* 485 */               int var17 = MathHelper.floor_double(par0Entity.posZ);
/*     */               
/* 487 */               if (par0Entity.worldObj.blockGetRenderType(var18, var16, var17) != 9 && par0Entity.worldObj.blockGetRenderType(var18, var16 - 1, var17) != 9)
/*     */               {
/* 489 */                 return -3;
/*     */               
/*     */               }
/*     */             
/*     */             }
/*     */             else {
/*     */ 
/*     */               
/* 497 */               boolean can_block_be_pathed_into = var13.canBePathedInto(par0Entity.worldObj, var9, var10, var11, par0Entity, par6);
/*     */               
/* 499 */               if (var13.blockMaterial == Material.lava || !can_block_be_pathed_into) {
/*     */                 
/* 501 */                 if (var14 == 11 || var12 == Block.fenceGate.blockID || var14 == 32)
/*     */                 {
/* 503 */                   return -3;
/*     */                 }
/*     */                 
/* 506 */                 if (var12 == Block.trapdoor.blockID)
/*     */                 {
/* 508 */                   return -4;
/*     */                 }
/*     */                 
/* 511 */                 Material var15 = var13.blockMaterial;
/*     */ 
/*     */ 
/*     */                 
/* 515 */                 if (var15 != Material.lava || par0Entity instanceof EntityFireElemental || par0Entity.handleLavaMovement())
/*     */                 {
/* 517 */                   return 0;
/*     */                 }
/*     */                 
/* 520 */                 if (!par0Entity.handleLavaMovement())
/*     */                 {
/* 522 */                   return -2;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 531 */     return var8 ? 2 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PathEntity createEntityPath(PathPoint par1PathPoint, PathPoint par2PathPoint) {
/* 539 */     int var3 = 1;
/*     */     
/*     */     PathPoint var4;
/* 542 */     for (var4 = par2PathPoint; var4.previous != null; var4 = var4.previous)
/*     */     {
/* 544 */       var3++;
/*     */     }
/*     */     
/* 547 */     PathPoint[] var5 = new PathPoint[var3];
/* 548 */     var4 = par2PathPoint;
/* 549 */     var3--;
/*     */     
/* 551 */     for (var5[var3] = par2PathPoint; var4.previous != null; var5[var3] = var4) {
/*     */       
/* 553 */       var4 = var4.previous;
/* 554 */       var3--;
/*     */     } 
/*     */     
/* 557 */     return new PathEntity(var5);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PathFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */