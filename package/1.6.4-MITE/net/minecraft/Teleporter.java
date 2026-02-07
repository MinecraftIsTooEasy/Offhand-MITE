/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Teleporter
/*     */ {
/*     */   private final WorldServer worldServerInstance;
/*     */   private final Random random;
/*  16 */   private final LongHashMap destinationCoordinateCache = new LongHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   private final List destinationCoordinateKeys = new ArrayList();
/*     */ 
/*     */   
/*     */   public Teleporter(WorldServer par1WorldServer) {
/*  26 */     this.worldServerInstance = par1WorldServer;
/*  27 */     this.random = new Random(par1WorldServer.getSeed());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void placeInPortal(Entity par1Entity, int dimension_id_from, double par2, double par4, double par6, float par8) {
/*  36 */     if (this.worldServerInstance.provider.dimensionId != 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  44 */       int metadata = BlockPortal.getDestinationBitForDimensionId(dimension_id_from);
/*     */       
/*  46 */       if (!placeInExistingPortal(par1Entity, metadata, par2, par4, par6, par8))
/*     */       {
/*  48 */         makePortal(par1Entity, metadata);
/*  49 */         placeInExistingPortal(par1Entity, metadata, par2, par4, par6, par8);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  54 */       int var9 = MathHelper.floor_double(par1Entity.posX);
/*  55 */       int var10 = MathHelper.floor_double(par1Entity.posY) - 1;
/*  56 */       int var11 = MathHelper.floor_double(par1Entity.posZ);
/*  57 */       byte var12 = 1;
/*  58 */       byte var13 = 0;
/*     */       
/*  60 */       for (int var14 = -2; var14 <= 2; var14++) {
/*     */         
/*  62 */         for (int var15 = -2; var15 <= 2; var15++) {
/*     */           
/*  64 */           for (int var16 = -1; var16 < 3; var16++) {
/*     */             
/*  66 */             int var17 = var9 + var15 * var12 + var14 * var13;
/*  67 */             int var18 = var10 + var16;
/*  68 */             int var19 = var11 + var15 * var13 - var14 * var12;
/*  69 */             boolean var20 = (var16 < 0);
/*  70 */             this.worldServerInstance.setBlock(var17, var18, var19, var20 ? Block.obsidian.blockID : 0);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  75 */       par1Entity.setLocationAndAngles(var9, var10, var11, par1Entity.rotationYaw, 0.0F);
/*  76 */       par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean placeInExistingPortal(Entity par1Entity, int metadata, double par2, double par4, double par6, float par8) {
/*  86 */     short var9 = 128;
/*  87 */     double var10 = -1.0D;
/*  88 */     int var12 = 0;
/*  89 */     int var13 = 0;
/*  90 */     int var14 = 0;
/*  91 */     int var15 = MathHelper.floor_double(par1Entity.posX);
/*  92 */     int var16 = MathHelper.floor_double(par1Entity.posZ);
/*  93 */     long var17 = ChunkCoordIntPair.chunkXZ2Int(var15, var16);
/*  94 */     boolean var19 = true;
/*     */ 
/*     */ 
/*     */     
/*  98 */     if (this.destinationCoordinateCache.containsItem(var17)) {
/*     */       
/* 100 */       PortalPosition var20 = (PortalPosition)this.destinationCoordinateCache.getValueByKey(var17);
/* 101 */       var10 = 0.0D;
/* 102 */       var12 = var20.posX;
/* 103 */       var13 = var20.posY;
/* 104 */       var14 = var20.posZ;
/* 105 */       var20.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
/* 106 */       var19 = false;
/*     */     }
/*     */     else {
/*     */       
/* 110 */       for (int var48 = var15 - var9; var48 <= var15 + var9; var48++) {
/*     */         
/* 112 */         double var21 = var48 + 0.5D - par1Entity.posX;
/*     */         
/* 114 */         for (int var23 = var16 - var9; var23 <= var16 + var9; var23++) {
/*     */           
/* 116 */           double var24 = var23 + 0.5D - par1Entity.posZ;
/*     */           
/* 118 */           for (int var26 = this.worldServerInstance.getActualHeight() - 1; var26 >= 0; var26--) {
/*     */             
/* 120 */             if (this.worldServerInstance.getBlockId(var48, var26, var23) == Block.portal.blockID)
/*     */             {
/* 122 */               if (!Block.portal.isRunegate(this.worldServerInstance, var48, var26, var23, false))
/*     */               {
/*     */                 
/* 125 */                 if (this.worldServerInstance.getBlockMetadata(var48, var26, var23) == metadata) {
/*     */ 
/*     */                   
/* 128 */                   while (this.worldServerInstance.getBlockId(var48, var26 - 1, var23) == Block.portal.blockID)
/*     */                   {
/* 130 */                     var26--;
/*     */                   }
/*     */                   
/* 133 */                   double var27 = var26 + 0.5D - par1Entity.posY;
/* 134 */                   double var29 = var21 * var21 + var27 * var27 + var24 * var24;
/*     */                   
/* 136 */                   if (var10 < 0.0D || var29 < var10) {
/*     */                     
/* 138 */                     var10 = var29;
/* 139 */                     var12 = var48;
/* 140 */                     var13 = var26;
/* 141 */                     var14 = var23;
/*     */                   } 
/*     */                 }  } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 149 */     if (var10 >= 0.0D) {
/*     */       
/* 151 */       if (var19) {
/*     */         
/* 153 */         this.destinationCoordinateCache.add(var17, new PortalPosition(this, var12, var13, var14, this.worldServerInstance.getTotalWorldTime()));
/* 154 */         this.destinationCoordinateKeys.add(Long.valueOf(var17));
/*     */       } 
/*     */       
/* 157 */       double var49 = var12 + 0.5D;
/* 158 */       double var25 = var13 + 0.5D;
/* 159 */       double var27 = var14 + 0.5D;
/* 160 */       int var50 = -1;
/*     */       
/* 162 */       if (this.worldServerInstance.getBlockId(var12 - 1, var13, var14) == Block.portal.blockID)
/*     */       {
/* 164 */         var50 = 2;
/*     */       }
/*     */       
/* 167 */       if (this.worldServerInstance.getBlockId(var12 + 1, var13, var14) == Block.portal.blockID)
/*     */       {
/* 169 */         var50 = 0;
/*     */       }
/*     */       
/* 172 */       if (this.worldServerInstance.getBlockId(var12, var13, var14 - 1) == Block.portal.blockID)
/*     */       {
/* 174 */         var50 = 3;
/*     */       }
/*     */       
/* 177 */       if (this.worldServerInstance.getBlockId(var12, var13, var14 + 1) == Block.portal.blockID)
/*     */       {
/* 179 */         var50 = 1;
/*     */       }
/*     */       
/* 182 */       int var30 = par1Entity.getTeleportDirection();
/*     */       
/* 184 */       if (var50 > -1) {
/*     */         
/* 186 */         int var31 = Direction.rotateLeft[var50];
/* 187 */         int var32 = Direction.offsetX[var50];
/* 188 */         int var33 = Direction.offsetZ[var50];
/* 189 */         int var34 = Direction.offsetX[var31];
/* 190 */         int var35 = Direction.offsetZ[var31];
/* 191 */         boolean var36 = (!this.worldServerInstance.isAirBlock(var12 + var32 + var34, var13, var14 + var33 + var35) || !this.worldServerInstance.isAirBlock(var12 + var32 + var34, var13 + 1, var14 + var33 + var35));
/* 192 */         boolean var37 = (!this.worldServerInstance.isAirBlock(var12 + var32, var13, var14 + var33) || !this.worldServerInstance.isAirBlock(var12 + var32, var13 + 1, var14 + var33));
/*     */         
/* 194 */         if (var36 && var37) {
/*     */           
/* 196 */           var50 = Direction.rotateOpposite[var50];
/* 197 */           var31 = Direction.rotateOpposite[var31];
/* 198 */           var32 = Direction.offsetX[var50];
/* 199 */           var33 = Direction.offsetZ[var50];
/* 200 */           var34 = Direction.offsetX[var31];
/* 201 */           var35 = Direction.offsetZ[var31];
/* 202 */           int var48 = var12 - var34;
/* 203 */           var49 -= var34;
/* 204 */           int var22 = var14 - var35;
/* 205 */           var27 -= var35;
/* 206 */           var36 = (!this.worldServerInstance.isAirBlock(var48 + var32 + var34, var13, var22 + var33 + var35) || !this.worldServerInstance.isAirBlock(var48 + var32 + var34, var13 + 1, var22 + var33 + var35));
/* 207 */           var37 = (!this.worldServerInstance.isAirBlock(var48 + var32, var13, var22 + var33) || !this.worldServerInstance.isAirBlock(var48 + var32, var13 + 1, var22 + var33));
/*     */         } 
/*     */         
/* 210 */         float var38 = 0.5F;
/* 211 */         float var39 = 0.5F;
/*     */         
/* 213 */         if (!var36 && var37) {
/*     */           
/* 215 */           var38 = 1.0F;
/*     */         }
/* 217 */         else if (var36 && !var37) {
/*     */           
/* 219 */           var38 = 0.0F;
/*     */         }
/* 221 */         else if (var36 && var37) {
/*     */           
/* 223 */           var39 = 0.0F;
/*     */         } 
/*     */         
/* 226 */         var49 += (var34 * var38 + var39 * var32);
/* 227 */         var27 += (var35 * var38 + var39 * var33);
/* 228 */         float var40 = 0.0F;
/* 229 */         float var41 = 0.0F;
/* 230 */         float var42 = 0.0F;
/* 231 */         float var43 = 0.0F;
/*     */         
/* 233 */         if (var50 == var30) {
/*     */           
/* 235 */           var40 = 1.0F;
/* 236 */           var41 = 1.0F;
/*     */         }
/* 238 */         else if (var50 == Direction.rotateOpposite[var30]) {
/*     */           
/* 240 */           var40 = -1.0F;
/* 241 */           var41 = -1.0F;
/*     */         }
/* 243 */         else if (var50 == Direction.rotateRight[var30]) {
/*     */           
/* 245 */           var42 = 1.0F;
/* 246 */           var43 = -1.0F;
/*     */         }
/*     */         else {
/*     */           
/* 250 */           var42 = -1.0F;
/* 251 */           var43 = 1.0F;
/*     */         } 
/*     */         
/* 254 */         double var44 = par1Entity.motionX;
/* 255 */         double var46 = par1Entity.motionZ;
/* 256 */         par1Entity.motionX = var44 * var40 + var46 * var43;
/* 257 */         par1Entity.motionZ = var44 * var42 + var46 * var41;
/* 258 */         par1Entity.rotationYaw = par8 - (var30 * 90) + (var50 * 90);
/*     */       }
/*     */       else {
/*     */         
/* 262 */         par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
/*     */       } 
/*     */       
/* 265 */       par1Entity.setLocationAndAngles(var49, var25, var27, par1Entity.rotationYaw, par1Entity.rotationPitch);
/* 266 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 270 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean makePortal(Entity par1Entity, int metadata) {
/* 279 */     int dimension_id_from = BlockPortal.getDestinationDimensionIdForMetadata(metadata);
/*     */     
/* 281 */     int direction_of_travel = -1;
/*     */     
/* 283 */     if (dimension_id_from == -1) {
/* 284 */       direction_of_travel = 1;
/* 285 */     } else if (dimension_id_from == -2 && par1Entity.dimension == 0) {
/* 286 */       direction_of_travel = 1;
/*     */     } 
/* 288 */     Debug.println("makePortal: direction_of_travel=" + direction_of_travel);
/*     */     
/* 290 */     par1Entity.posY = (direction_of_travel == -1) ? (this.worldServerInstance.getActualHeight() - 1) : 1.0D;
/*     */ 
/*     */ 
/*     */     
/* 294 */     byte var2 = 16;
/* 295 */     double var3 = -1.0D;
/* 296 */     int var5 = MathHelper.floor_double(par1Entity.posX);
/* 297 */     int var6 = MathHelper.floor_double(par1Entity.posY);
/* 298 */     int var7 = MathHelper.floor_double(par1Entity.posZ);
/* 299 */     int var8 = var5;
/* 300 */     int var9 = var6;
/* 301 */     int var10 = var7;
/* 302 */     int var11 = 0;
/* 303 */     int var12 = this.random.nextInt(4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int var13;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 320 */     for (var13 = var5 - var2; var13 <= var5 + var2; var13++) {
/*     */       
/* 322 */       double var14 = var13 + 0.5D - par1Entity.posX;
/*     */       
/* 324 */       for (int i = var7 - var2; i <= var7 + var2; i++) {
/*     */         
/* 326 */         double var17 = i + 0.5D - par1Entity.posZ;
/*     */         
/*     */         int j;
/* 329 */         label192: for (j = this.worldServerInstance.getActualHeight() - 1; j >= 0; j--) {
/*     */           
/* 331 */           if (this.worldServerInstance.isAirBlock(var13, j, i)) {
/*     */             
/* 333 */             while (j > 0 && this.worldServerInstance.isAirBlock(var13, j - 1, i))
/*     */             {
/* 335 */               j--;
/*     */             }
/*     */             
/* 338 */             for (int var20 = var12; var20 < var12 + 4; var20++) {
/*     */               
/* 340 */               int var21 = var20 % 2;
/* 341 */               int var22 = 1 - var21;
/*     */               
/* 343 */               if (var20 % 4 >= 2) {
/*     */                 
/* 345 */                 var21 = -var21;
/* 346 */                 var22 = -var22;
/*     */               } 
/*     */               
/* 349 */               for (int var23 = 0; var23 < 3; var23++) {
/*     */                 
/* 351 */                 for (int var24 = 0; var24 < 4; var24++) {
/*     */                   
/* 353 */                   for (int var25 = -1; var25 < 4; ) {
/*     */                     
/* 355 */                     int var26 = var13 + (var24 - 1) * var21 + var23 * var22;
/* 356 */                     int var27 = j + var25;
/* 357 */                     int var28 = i + (var24 - 1) * var22 - var23 * var21;
/*     */                     
/* 359 */                     if (var25 < 0 && this.worldServerInstance.isCeilingBedrock(var26, var27, var28)) {
/*     */                       continue label192;
/*     */                     }
/* 362 */                     if (var25 >= 0 || this.worldServerInstance.getBlockMaterial(var26, var27, var28).isSolid()) { if (var25 >= 0 && !this.worldServerInstance.isAirBlock(var26, var27, var28))
/*     */                         continue label192; 
/*     */                       var25++; }
/*     */                     
/*     */                     continue label192;
/*     */                   } 
/*     */                 } 
/*     */               } 
/* 370 */               double var32 = j + 0.5D - par1Entity.posY;
/* 371 */               double var31 = var14 * var14 + var32 * var32 + var17 * var17;
/*     */               
/* 373 */               if (var3 < 0.0D || var31 < var3) {
/*     */                 
/* 375 */                 var3 = var31;
/* 376 */                 var8 = var13;
/* 377 */                 var9 = j;
/* 378 */                 var10 = i;
/* 379 */                 var11 = var20 % 4;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 387 */     if (var3 < 0.0D)
/*     */     {
/* 389 */       for (var13 = var5 - var2; var13 <= var5 + var2; var13++) {
/*     */         
/* 391 */         double var14 = var13 + 0.5D - par1Entity.posX;
/*     */         
/* 393 */         for (int i = var7 - var2; i <= var7 + var2; i++) {
/*     */           
/* 395 */           double var17 = i + 0.5D - par1Entity.posZ;
/*     */           
/*     */           int j;
/* 398 */           label189: for (j = this.worldServerInstance.getActualHeight() - 1; j >= 0; j--) {
/*     */             
/* 400 */             if (this.worldServerInstance.isAirBlock(var13, j, i)) {
/*     */               
/* 402 */               while (j > 0 && this.worldServerInstance.isAirBlock(var13, j - 1, i))
/*     */               {
/* 404 */                 j--;
/*     */               }
/*     */               
/* 407 */               for (int var20 = var12; var20 < var12 + 2; var20++) {
/*     */                 
/* 409 */                 int var21 = var20 % 2;
/* 410 */                 int var22 = 1 - var21;
/*     */                 
/* 412 */                 for (int var23 = 0; var23 < 4; var23++) {
/*     */                   
/* 414 */                   for (int var24 = -1; var24 < 4; ) {
/*     */                     
/* 416 */                     int var25 = var13 + (var23 - 1) * var21;
/* 417 */                     int var26 = j + var24;
/* 418 */                     int var27 = i + (var23 - 1) * var22;
/*     */                     
/* 420 */                     if (var24 < 0 && this.worldServerInstance.isCeilingBedrock(var25, var26, var27)) {
/*     */                       continue label189;
/*     */                     }
/* 423 */                     if (var24 >= 0 || this.worldServerInstance.getBlockMaterial(var25, var26, var27).isSolid()) { if (var24 >= 0 && !this.worldServerInstance.isAirBlock(var25, var26, var27))
/*     */                         continue label189; 
/*     */                       var24++; }
/*     */                     
/*     */                     continue label189;
/*     */                   } 
/*     */                 } 
/* 430 */                 double var32 = j + 0.5D - par1Entity.posY;
/* 431 */                 double var31 = var14 * var14 + var32 * var32 + var17 * var17;
/*     */                 
/* 433 */                 if (var3 < 0.0D || var31 < var3) {
/*     */                   
/* 435 */                   var3 = var31;
/* 436 */                   var8 = var13;
/* 437 */                   var9 = j;
/* 438 */                   var10 = i;
/* 439 */                   var11 = var20 % 2;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 448 */     int var29 = var8;
/* 449 */     int var15 = var9;
/* 450 */     int var16 = var10;
/* 451 */     int var30 = var11 % 2;
/* 452 */     int var18 = 1 - var30;
/*     */     
/* 454 */     if (var11 % 4 >= 2) {
/*     */       
/* 456 */       var30 = -var30;
/* 457 */       var18 = -var18;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 462 */     if (var3 < 0.0D) {
/*     */       
/* 464 */       if (var9 < 70)
/*     */       {
/* 466 */         var9 = 70;
/*     */       }
/*     */       
/* 469 */       if (var9 > this.worldServerInstance.getActualHeight() - 10)
/*     */       {
/* 471 */         var9 = this.worldServerInstance.getActualHeight() - 10;
/*     */       }
/*     */       
/* 474 */       var15 = var9;
/*     */       
/* 476 */       for (int i = -1; i <= 1; i++) {
/*     */         
/* 478 */         for (int var20 = 1; var20 < 3; var20++) {
/*     */           
/* 480 */           for (int var21 = -1; var21 < 3; var21++) {
/*     */             
/* 482 */             int var22 = var29 + (var20 - 1) * var30 + i * var18;
/* 483 */             int var23 = var15 + var21;
/* 484 */             int var24 = var16 + (var20 - 1) * var18 - i * var30;
/* 485 */             boolean var33 = (var21 < 0);
/* 486 */             this.worldServerInstance.setBlock(var22, var23, var24, var33 ? Block.obsidian.blockID : 0);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 492 */     for (int var19 = 0; var19 < 4; var19++) {
/*     */       int var20;
/* 494 */       for (var20 = 0; var20 < 4; var20++) {
/*     */         
/* 496 */         for (int var21 = -1; var21 < 4; var21++) {
/*     */           
/* 498 */           int var22 = var29 + (var20 - 1) * var30;
/* 499 */           int var23 = var15 + var21;
/* 500 */           int var24 = var16 + (var20 - 1) * var18;
/* 501 */           boolean var33 = (var20 == 0 || var20 == 3 || var21 == -1 || var21 == 3);
/*     */ 
/*     */           
/* 504 */           if (var33) {
/* 505 */             this.worldServerInstance.setBlock(var22, var23, var24, Block.obsidian.blockID, 0, 2);
/*     */           } else {
/* 507 */             this.worldServerInstance.setBlock(var22, var23, var24, Block.portal.blockID, metadata, 2);
/*     */           } 
/*     */         } 
/*     */       } 
/* 511 */       for (var20 = 0; var20 < 4; var20++) {
/*     */         
/* 513 */         for (int var21 = -1; var21 < 4; var21++) {
/*     */           
/* 515 */           int var22 = var29 + (var20 - 1) * var30;
/* 516 */           int var23 = var15 + var21;
/* 517 */           int var24 = var16 + (var20 - 1) * var18;
/* 518 */           this.worldServerInstance.notifyBlocksOfNeighborChange(var22, var23, var24, this.worldServerInstance.getBlockId(var22, var23, var24));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 523 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeStalePortalLocations(long par1) {
/* 532 */     if (par1 % 100L == 0L) {
/*     */       
/* 534 */       Iterator<Long> var3 = this.destinationCoordinateKeys.iterator();
/* 535 */       long var4 = par1 - 600L;
/*     */       
/* 537 */       while (var3.hasNext()) {
/*     */         
/* 539 */         Long var6 = var3.next();
/* 540 */         PortalPosition var7 = (PortalPosition)this.destinationCoordinateCache.getValueByKey(var6.longValue());
/*     */         
/* 542 */         if (var7 == null || var7.lastUpdateTime < var4) {
/*     */           
/* 544 */           var3.remove();
/* 545 */           this.destinationCoordinateCache.remove(var6.longValue());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Teleporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */