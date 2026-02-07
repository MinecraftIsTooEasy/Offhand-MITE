/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PathNavigate
/*     */ {
/*     */   private EntityLiving theEntity;
/*     */   private World worldObj;
/*     */   private PathEntity currentPath;
/*     */   private double speed;
/*     */   private AttributeInstance pathSearchRange;
/*     */   private boolean noSunPathfind;
/*     */   private int totalTicks;
/*     */   private int ticksAtLastPos;
/*  30 */   private Vec3 lastPosCheck = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPassOpenWoodenDoors = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPassClosedWoodenDoors;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean avoidsWater;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSwim;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathNavigate(EntityLiving par1EntityLiving, World par2World) {
/*  56 */     this.theEntity = par1EntityLiving;
/*  57 */     this.worldObj = par2World;
/*  58 */     this.pathSearchRange = par1EntityLiving.getEntityAttribute(SharedMonsterAttributes.followRange);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAvoidsWater(boolean par1) {
/*  63 */     this.avoidsWater = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAvoidsWater() {
/*  68 */     return this.avoidsWater;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBreakDoors(boolean par1) {
/*  73 */     this.canPassClosedWoodenDoors = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnterDoors(boolean par1) {
/*  81 */     this.canPassOpenWoodenDoors = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanBreakDoors() {
/*  89 */     return this.canPassClosedWoodenDoors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAvoidSun(boolean par1) {
/*  97 */     this.noSunPathfind = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpeed(double par1) {
/* 105 */     this.speed = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanSwim(boolean par1) {
/* 113 */     this.canSwim = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float getPathSearchRange() {
/* 120 */     if (Entity.isClass(this.theEntity, EntityWoodSpider.class) && this.theEntity.getBrightness(1.0F) > 0.65F) {
/* 121 */       return (float)this.pathSearchRange.getAttributeValue() * 0.5F;
/*     */     }
/* 123 */     return (float)this.pathSearchRange.getAttributeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathEntity getPathToXYZ(double par1, double par3, double par5) {
/* 131 */     return !canNavigate() ? null : this.worldObj.getEntityPathToXYZ(this.theEntity, MathHelper.floor_double(par1), (int)par3, MathHelper.floor_double(par5), getPathSearchRange(), this.canPassOpenWoodenDoors, this.canPassClosedWoodenDoors, this.avoidsWater, this.canSwim);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity getPathToXYZ(int x, int y, int z, int max_range) {
/* 136 */     return !canNavigate() ? null : this.worldObj.getEntityPathToXYZ(this.theEntity, x, y, z, max_range, this.canPassOpenWoodenDoors, this.canPassClosedWoodenDoors, this.avoidsWater, this.canSwim);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryMoveToXYZ(double par1, double par3, double par5, double par7) {
/* 144 */     PathEntity var9 = getPathToXYZ(MathHelper.floor_double(par1), (int)par3, MathHelper.floor_double(par5));
/* 145 */     return setPath(var9, par7);
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
/*     */   public PathEntity getPathToEntityLiving(Entity par1Entity) {
/* 158 */     return !canNavigate() ? null : this.worldObj.getPathEntityToEntity(this.theEntity, par1Entity, this.theEntity.getMaxTargettingRange(), this.canPassOpenWoodenDoors, this.canPassClosedWoodenDoors, this.avoidsWater, this.canSwim);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity getPathToEntityLiving(Entity par1Entity, int max_path_length_override) {
/* 163 */     return !canNavigate() ? null : this.worldObj.getPathEntityToEntity(this.theEntity, par1Entity, max_path_length_override, this.canPassOpenWoodenDoors, this.canPassClosedWoodenDoors, this.avoidsWater, this.canSwim);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryMoveToEntityLiving(Entity par1Entity, double par2) {
/* 171 */     PathEntity var4 = getPathToEntityLiving(par1Entity);
/* 172 */     return (var4 != null) ? setPath(var4, par2) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setPath(PathEntity par1PathEntity, double par2) {
/* 181 */     if (par1PathEntity == null) {
/*     */       
/* 183 */       this.currentPath = null;
/* 184 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 188 */     if (!par1PathEntity.isSamePath(this.currentPath))
/*     */     {
/* 190 */       this.currentPath = par1PathEntity;
/*     */     }
/*     */     
/* 193 */     if (this.noSunPathfind)
/*     */     {
/* 195 */       removeSunnyPath();
/*     */     }
/*     */     
/* 198 */     if (this.currentPath.getCurrentPathLength() == 0)
/*     */     {
/* 200 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 204 */     this.speed = par2;
/* 205 */     Vec3 var4 = getEntityPosition();
/* 206 */     this.ticksAtLastPos = this.totalTicks;
/* 207 */     this.lastPosCheck.xCoord = var4.xCoord;
/* 208 */     this.lastPosCheck.yCoord = var4.yCoord;
/* 209 */     this.lastPosCheck.zCoord = var4.zCoord;
/* 210 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathEntity getPath() {
/* 220 */     return this.currentPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateNavigation() {
/* 225 */     this.totalTicks++;
/*     */     
/* 227 */     if (!noPath()) {
/*     */       
/* 229 */       if (canNavigate())
/*     */       {
/* 231 */         pathFollow();
/*     */       }
/*     */       
/* 234 */       if (!noPath()) {
/*     */         
/* 236 */         Vec3 var1 = this.currentPath.getPosition(this.theEntity);
/*     */         
/* 238 */         if (var1 != null)
/*     */         {
/* 240 */           this.theEntity.getMoveHelper().setMoveTo(var1.xCoord, var1.yCoord, var1.zCoord, this.speed);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void pathFollow() {
/* 248 */     Vec3 var1 = getEntityPosition();
/* 249 */     int var2 = this.currentPath.getCurrentPathLength();
/*     */     
/* 251 */     for (int var3 = this.currentPath.getCurrentPathIndex(); var3 < this.currentPath.getCurrentPathLength(); var3++) {
/*     */       
/* 253 */       if ((this.currentPath.getPathPointFromIndex(var3)).yCoord != (int)var1.yCoord) {
/*     */         
/* 255 */         var2 = var3;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 260 */     float var8 = this.theEntity.width * this.theEntity.width;
/*     */     
/*     */     int var4;
/* 263 */     for (var4 = this.currentPath.getCurrentPathIndex(); var4 < var2; var4++) {
/*     */       
/* 265 */       if (var1.squareDistanceTo(this.currentPath.getVectorFromIndex(this.theEntity, var4)) < var8)
/*     */       {
/* 267 */         this.currentPath.setCurrentPathIndex(var4 + 1);
/*     */       }
/*     */     } 
/*     */     
/* 271 */     var4 = MathHelper.ceiling_float_int(this.theEntity.width);
/* 272 */     int var5 = (int)this.theEntity.height + 1;
/* 273 */     int var6 = var4;
/*     */     
/* 275 */     for (int var7 = var2 - 1; var7 >= this.currentPath.getCurrentPathIndex(); var7--) {
/*     */       
/* 277 */       if (isDirectPathBetweenPoints(var1, this.currentPath.getVectorFromIndex(this.theEntity, var7), var4, var5, var6)) {
/*     */         
/* 279 */         this.currentPath.setCurrentPathIndex(var7);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 284 */     if (this.totalTicks - this.ticksAtLastPos > 100) {
/*     */       
/* 286 */       if (var1.squareDistanceTo(this.lastPosCheck) < 2.25D)
/*     */       {
/* 288 */         clearPathEntity();
/*     */       }
/*     */       
/* 291 */       this.ticksAtLastPos = this.totalTicks;
/* 292 */       this.lastPosCheck.xCoord = var1.xCoord;
/* 293 */       this.lastPosCheck.yCoord = var1.yCoord;
/* 294 */       this.lastPosCheck.zCoord = var1.zCoord;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean noPath() {
/* 303 */     return (this.currentPath == null || this.currentPath.isFinished());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPathEntity() {
/* 311 */     this.currentPath = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private Vec3 getEntityPosition() {
/* 316 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(this.theEntity.posX, getPathableYPos(), this.theEntity.posZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getPathableYPos() {
/* 324 */     if (this.theEntity.isInWater() && this.canSwim) {
/*     */       
/* 326 */       int var1 = (int)this.theEntity.boundingBox.minY;
/* 327 */       int var2 = this.worldObj.getBlockId(MathHelper.floor_double(this.theEntity.posX), var1, MathHelper.floor_double(this.theEntity.posZ));
/* 328 */       int var3 = 0;
/*     */ 
/*     */       
/*     */       do {
/* 332 */         if (var2 != Block.waterMoving.blockID && var2 != Block.waterStill.blockID)
/*     */         {
/* 334 */           return var1;
/*     */         }
/*     */         
/* 337 */         var1++;
/* 338 */         var2 = this.worldObj.getBlockId(MathHelper.floor_double(this.theEntity.posX), var1, MathHelper.floor_double(this.theEntity.posZ));
/* 339 */         ++var3;
/*     */       }
/* 341 */       while (var3 <= 16);
/*     */       
/* 343 */       return (int)this.theEntity.boundingBox.minY;
/*     */     } 
/*     */ 
/*     */     
/* 347 */     return (int)(this.theEntity.boundingBox.minY + 0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canNavigate() {
/* 356 */     return (this.theEntity.onGround || (this.canSwim && isInFluid()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isInFluid() {
/* 364 */     return (this.theEntity.isInWater() || this.theEntity.handleLavaMovement());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeSunnyPath() {
/* 372 */     if (this.theEntity.isWearingHelmet(true)) {
/*     */       return;
/*     */     }
/* 375 */     if (!this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.theEntity.posX), (int)(this.theEntity.boundingBox.minY + 0.5D), MathHelper.floor_double(this.theEntity.posZ)))
/*     */     {
/* 377 */       for (int var1 = 0; var1 < this.currentPath.getCurrentPathLength(); var1++) {
/*     */         
/* 379 */         PathPoint var2 = this.currentPath.getPathPointFromIndex(var1);
/*     */         
/* 381 */         if (!this.worldObj.isInRain(var2.xCoord, var2.yCoord, var2.zCoord))
/*     */         {
/*     */           
/* 384 */           if (this.worldObj.canBlockSeeTheSky(var2.xCoord, var2.yCoord, var2.zCoord)) {
/*     */             
/* 386 */             this.currentPath.setCurrentPathLength(var1 - 1);
/*     */             return;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
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
/*     */   private boolean isDirectPathBetweenPoints(Vec3 par1Vec3, Vec3 par2Vec3, int par3, int par4, int par5) {
/* 405 */     int var6 = MathHelper.floor_double(par1Vec3.xCoord);
/* 406 */     int var7 = MathHelper.floor_double(par1Vec3.zCoord);
/* 407 */     double var8 = par2Vec3.xCoord - par1Vec3.xCoord;
/* 408 */     double var10 = par2Vec3.zCoord - par1Vec3.zCoord;
/* 409 */     double var12 = var8 * var8 + var10 * var10;
/*     */     
/* 411 */     if (var12 < 1.0E-8D)
/*     */     {
/* 413 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 417 */     double var14 = 1.0D / Math.sqrt(var12);
/* 418 */     var8 *= var14;
/* 419 */     var10 *= var14;
/* 420 */     par3 += 2;
/* 421 */     par5 += 2;
/*     */     
/* 423 */     if (!isSafeToStandAt(var6, (int)par1Vec3.yCoord, var7, par3, par4, par5, par1Vec3, var8, var10))
/*     */     {
/* 425 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 429 */     par3 -= 2;
/* 430 */     par5 -= 2;
/* 431 */     double var16 = 1.0D / Math.abs(var8);
/* 432 */     double var18 = 1.0D / Math.abs(var10);
/* 433 */     double var20 = (var6 * 1) - par1Vec3.xCoord;
/* 434 */     double var22 = (var7 * 1) - par1Vec3.zCoord;
/*     */     
/* 436 */     if (var8 >= 0.0D)
/*     */     {
/* 438 */       var20++;
/*     */     }
/*     */     
/* 441 */     if (var10 >= 0.0D)
/*     */     {
/* 443 */       var22++;
/*     */     }
/*     */     
/* 446 */     var20 /= var8;
/* 447 */     var22 /= var10;
/* 448 */     int var24 = (var8 < 0.0D) ? -1 : 1;
/* 449 */     int var25 = (var10 < 0.0D) ? -1 : 1;
/* 450 */     int var26 = MathHelper.floor_double(par2Vec3.xCoord);
/* 451 */     int var27 = MathHelper.floor_double(par2Vec3.zCoord);
/* 452 */     int var28 = var26 - var6;
/* 453 */     int var29 = var27 - var7;
/*     */ 
/*     */     
/*     */     do {
/* 457 */       if (var28 * var24 <= 0 && var29 * var25 <= 0)
/*     */       {
/* 459 */         return true;
/*     */       }
/*     */       
/* 462 */       if (var20 < var22)
/*     */       {
/* 464 */         var20 += var16;
/* 465 */         var6 += var24;
/* 466 */         var28 = var26 - var6;
/*     */       }
/*     */       else
/*     */       {
/* 470 */         var22 += var18;
/* 471 */         var7 += var25;
/* 472 */         var29 = var27 - var7;
/*     */       }
/*     */     
/* 475 */     } while (isSafeToStandAt(var6, (int)par1Vec3.yCoord, var7, par3, par4, par5, par1Vec3, var8, var10));
/*     */     
/* 477 */     return false;
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
/*     */   private boolean isSafeToStandAt(int par1, int par2, int par3, int par4, int par5, int par6, Vec3 par7Vec3, double par8, double par10) {
/* 492 */     int var12 = par1 - par4 / 2;
/* 493 */     int var13 = par3 - par6 / 2;
/*     */     
/* 495 */     if (!isPositionClear(var12, par2, var13, par4, par5, par6, par7Vec3, par8, par10))
/*     */     {
/* 497 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 501 */     for (int var14 = var12; var14 < var12 + par4; var14++) {
/*     */       
/* 503 */       for (int var15 = var13; var15 < var13 + par6; var15++) {
/*     */         
/* 505 */         double var16 = var14 + 0.5D - par7Vec3.xCoord;
/* 506 */         double var18 = var15 + 0.5D - par7Vec3.zCoord;
/*     */         
/* 508 */         if (var16 * par8 + var18 * par10 >= 0.0D) {
/*     */           
/* 510 */           int var20 = this.worldObj.getBlockId(var14, par2 - 1, var15);
/*     */           
/* 512 */           if (var20 <= 0)
/*     */           {
/* 514 */             return false;
/*     */           }
/*     */           
/* 517 */           Material var21 = (Block.blocksList[var20]).blockMaterial;
/*     */           
/* 519 */           if (var21 == Material.water && !this.theEntity.isInWater())
/*     */           {
/* 521 */             return false;
/*     */           }
/*     */           
/* 524 */           if (var21 == Material.lava)
/*     */           {
/* 526 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 532 */     return true;
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
/*     */   private boolean isPositionClear(int min_x, int min_y, int min_z, int range_x, int range_y, int range_z, Vec3 entity_pos, double dx, double dz) {
/* 587 */     for (int var12 = min_x; var12 < min_x + range_x; var12++) {
/*     */       
/* 589 */       double var15 = var12 + 0.5D - entity_pos.xCoord;
/* 590 */       double var15_times_par8 = var15 * dx;
/*     */       
/* 592 */       for (int var13 = min_y; var13 < min_y + range_y; var13++) {
/*     */         
/* 594 */         for (int var14 = min_z; var14 < min_z + range_z; var14++) {
/*     */ 
/*     */           
/* 597 */           double var17 = var14 + 0.5D - entity_pos.zCoord;
/*     */ 
/*     */           
/* 600 */           if (var15_times_par8 + var17 * dz >= 0.0D) {
/*     */             
/* 602 */             int var19 = this.worldObj.getBlockId(var12, var13, var14);
/*     */             
/* 604 */             if (var19 != 0) {
/*     */ 
/*     */               
/* 607 */               Block block = Block.blocksList[var19];
/*     */               
/* 609 */               if (block.blockMaterial == Material.fire) {
/*     */                 
/* 611 */                 if (this.theEntity.isHarmedByFire()) {
/* 612 */                   return false;
/*     */                 }
/* 614 */               } else if (block.blockMaterial == Material.lava) {
/*     */ 
/*     */                 
/* 617 */                 if (!this.theEntity.isComfortableInLava()) {
/* 618 */                   return false;
/*     */                 }
/*     */               } 
/* 621 */               if (!block.canBePathedInto(this.worldObj, var12, var13, var14, this.theEntity, false))
/*     */               {
/*     */ 
/*     */ 
/*     */                 
/* 626 */                 return false;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 633 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PathNavigate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */