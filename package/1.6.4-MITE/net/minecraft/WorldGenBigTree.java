/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenBigTree
/*     */   extends WorldGenerator
/*     */ {
/*  11 */   static final byte[] otherCoordPairs = new byte[] { 2, 0, 0, 1, 2, 1 };
/*     */ 
/*     */   
/*  14 */   Random rand = new Random();
/*     */   
/*     */   World worldObj;
/*     */   
/*  18 */   int[] basePos = new int[] { 0, 0, 0 };
/*     */   int heightLimit;
/*     */   int height;
/*  21 */   double heightAttenuation = 0.618D;
/*  22 */   double branchDensity = 1.0D;
/*  23 */   double branchSlope = 0.381D;
/*  24 */   double scaleWidth = 1.0D;
/*  25 */   double leafDensity = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   int trunkSize = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   int heightLimitLimit = 12;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   int leafDistanceLimit = 4;
/*     */ 
/*     */   
/*     */   int[][] leafNodes;
/*     */ 
/*     */   
/*     */   public WorldGenBigTree(boolean par1) {
/*  47 */     super(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNodeList() {
/*  55 */     this.height = (int)(this.heightLimit * this.heightAttenuation);
/*     */     
/*  57 */     if (this.height >= this.heightLimit)
/*     */     {
/*  59 */       this.height = this.heightLimit - 1;
/*     */     }
/*     */     
/*  62 */     int var1 = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  64 */     if (var1 < 1)
/*     */     {
/*  66 */       var1 = 1;
/*     */     }
/*     */     
/*  69 */     int[][] var2 = new int[var1 * this.heightLimit][4];
/*  70 */     int var3 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
/*  71 */     int var4 = 1;
/*  72 */     int var5 = this.basePos[1] + this.height;
/*  73 */     int var6 = var3 - this.basePos[1];
/*  74 */     var2[0][0] = this.basePos[0];
/*  75 */     var2[0][1] = var3;
/*  76 */     var2[0][2] = this.basePos[2];
/*  77 */     var2[0][3] = var5;
/*  78 */     var3--;
/*     */     
/*  80 */     while (var6 >= 0) {
/*     */       
/*  82 */       int var7 = 0;
/*  83 */       float var8 = layerSize(var6);
/*     */       
/*  85 */       if (var8 < 0.0F) {
/*     */         
/*  87 */         var3--;
/*  88 */         var6--;
/*     */         
/*     */         continue;
/*     */       } 
/*  92 */       for (double var9 = 0.5D; var7 < var1; var7++) {
/*     */         
/*  94 */         double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
/*  95 */         double var13 = this.rand.nextFloat() * 2.0D * Math.PI;
/*  96 */         int var15 = MathHelper.floor_double(var11 * Math.sin(var13) + this.basePos[0] + var9);
/*  97 */         int var16 = MathHelper.floor_double(var11 * Math.cos(var13) + this.basePos[2] + var9);
/*  98 */         int[] var17 = { var15, var3, var16 };
/*  99 */         int[] var18 = { var15, var3 + this.leafDistanceLimit, var16 };
/*     */         
/* 101 */         if (checkBlockLine(var17, var18) == -1) {
/*     */           
/* 103 */           int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 104 */           double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
/* 105 */           double var22 = var20 * this.branchSlope;
/*     */           
/* 107 */           if (var17[1] - var22 > var5) {
/*     */             
/* 109 */             var19[1] = var5;
/*     */           }
/*     */           else {
/*     */             
/* 113 */             var19[1] = (int)(var17[1] - var22);
/*     */           } 
/*     */           
/* 116 */           if (checkBlockLine(var19, var17) == -1) {
/*     */             
/* 118 */             var2[var4][0] = var15;
/* 119 */             var2[var4][1] = var3;
/* 120 */             var2[var4][2] = var16;
/* 121 */             var2[var4][3] = var19[1];
/* 122 */             var4++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 127 */       var3--;
/* 128 */       var6--;
/*     */     } 
/*     */ 
/*     */     
/* 132 */     this.leafNodes = new int[var4][4];
/* 133 */     System.arraycopy(var2, 0, this.leafNodes, 0, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, int par6) {
/* 138 */     int var7 = (int)(par4 + 0.618D);
/* 139 */     byte var8 = otherCoordPairs[par5];
/* 140 */     byte var9 = otherCoordPairs[par5 + 3];
/* 141 */     int[] var10 = { par1, par2, par3 };
/* 142 */     int[] var11 = { 0, 0, 0 };
/* 143 */     int var12 = -var7;
/* 144 */     int var13 = -var7;
/*     */     
/* 146 */     for (var11[par5] = var10[par5]; var12 <= var7; var12++) {
/*     */       
/* 148 */       var11[var8] = var10[var8] + var12;
/* 149 */       var13 = -var7;
/*     */       
/* 151 */       while (var13 <= var7) {
/*     */         
/* 153 */         double var15 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);
/*     */         
/* 155 */         if (var15 > (par4 * par4)) {
/*     */           
/* 157 */           var13++;
/*     */           
/*     */           continue;
/*     */         } 
/* 161 */         var11[var9] = var10[var9] + var13;
/* 162 */         int var14 = this.worldObj.getBlockId(var11[0], var11[1], var11[2]);
/*     */         
/* 164 */         if (var14 != 0 && var14 != Block.leaves.blockID) {
/*     */           
/* 166 */           var13++;
/*     */           
/*     */           continue;
/*     */         } 
/* 170 */         setBlockAndMetadata(this.worldObj, var11[0], var11[1], var11[2], par6, 0);
/* 171 */         var13++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float layerSize(int par1) {
/*     */     float var4;
/* 183 */     if (par1 < this.heightLimit * 0.3D)
/*     */     {
/* 185 */       return -1.618F;
/*     */     }
/*     */ 
/*     */     
/* 189 */     float var2 = this.heightLimit / 2.0F;
/* 190 */     float var3 = this.heightLimit / 2.0F - par1;
/*     */ 
/*     */     
/* 193 */     if (var3 == 0.0F) {
/*     */       
/* 195 */       var4 = var2;
/*     */     }
/* 197 */     else if (Math.abs(var3) >= var2) {
/*     */       
/* 199 */       var4 = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 203 */       var4 = (float)Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
/*     */     } 
/*     */     
/* 206 */     var4 *= 0.5F;
/* 207 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   float leafSize(int par1) {
/* 213 */     return (par1 >= 0 && par1 < this.leafDistanceLimit) ? ((par1 != 0 && par1 != this.leafDistanceLimit - 1) ? 3.0F : 2.0F) : -1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNode(int par1, int par2, int par3) {
/* 221 */     int var4 = par2;
/*     */     
/* 223 */     for (int var5 = par2 + this.leafDistanceLimit; var4 < var5; var4++) {
/*     */       
/* 225 */       float var6 = leafSize(var4 - par2);
/* 226 */       genTreeLayer(par1, var4, par3, var6, (byte)1, Block.leaves.blockID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, int par3) {
/* 235 */     int[] var4 = { 0, 0, 0 };
/* 236 */     byte var5 = 0;
/*     */     
/*     */     byte var6;
/* 239 */     for (var6 = 0; var5 < 3; var5 = (byte)(var5 + 1)) {
/*     */       
/* 241 */       var4[var5] = par2ArrayOfInteger[var5] - par1ArrayOfInteger[var5];
/*     */       
/* 243 */       if (Math.abs(var4[var5]) > Math.abs(var4[var6]))
/*     */       {
/* 245 */         var6 = var5;
/*     */       }
/*     */     } 
/*     */     
/* 249 */     if (var4[var6] != 0) {
/*     */       
/* 251 */       byte var9, var7 = otherCoordPairs[var6];
/* 252 */       byte var8 = otherCoordPairs[var6 + 3];
/*     */ 
/*     */       
/* 255 */       if (var4[var6] > 0) {
/*     */         
/* 257 */         var9 = 1;
/*     */       }
/*     */       else {
/*     */         
/* 261 */         var9 = -1;
/*     */       } 
/*     */       
/* 264 */       double var10 = var4[var7] / var4[var6];
/* 265 */       double var12 = var4[var8] / var4[var6];
/* 266 */       int[] var14 = { 0, 0, 0 };
/* 267 */       int var15 = 0;
/*     */       
/* 269 */       for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9) {
/*     */         
/* 271 */         var14[var6] = MathHelper.floor_double((par1ArrayOfInteger[var6] + var15) + 0.5D);
/* 272 */         var14[var7] = MathHelper.floor_double(par1ArrayOfInteger[var7] + var15 * var10 + 0.5D);
/* 273 */         var14[var8] = MathHelper.floor_double(par1ArrayOfInteger[var8] + var15 * var12 + 0.5D);
/* 274 */         byte var17 = 0;
/* 275 */         int var18 = Math.abs(var14[0] - par1ArrayOfInteger[0]);
/* 276 */         int var19 = Math.abs(var14[2] - par1ArrayOfInteger[2]);
/* 277 */         int var20 = Math.max(var18, var19);
/*     */         
/* 279 */         if (var20 > 0)
/*     */         {
/* 281 */           if (var18 == var20) {
/*     */             
/* 283 */             var17 = 4;
/*     */           }
/* 285 */           else if (var19 == var20) {
/*     */             
/* 287 */             var17 = 8;
/*     */           } 
/*     */         }
/*     */         
/* 291 */         setBlockAndMetadata(this.worldObj, var14[0], var14[1], var14[2], par3, var17);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeaves() {
/* 301 */     int var1 = 0;
/*     */     
/* 303 */     for (int var2 = this.leafNodes.length; var1 < var2; var1++) {
/*     */       
/* 305 */       int var3 = this.leafNodes[var1][0];
/* 306 */       int var4 = this.leafNodes[var1][1];
/* 307 */       int var5 = this.leafNodes[var1][2];
/* 308 */       generateLeafNode(var3, var4, var5);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean leafNodeNeedsBase(int par1) {
/* 317 */     return (par1 >= this.heightLimit * 0.2D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateTrunk() {
/* 326 */     int var1 = this.basePos[0];
/* 327 */     int var2 = this.basePos[1];
/* 328 */     int var3 = this.basePos[1] + this.height;
/* 329 */     int var4 = this.basePos[2];
/* 330 */     int[] var5 = { var1, var2, var4 };
/* 331 */     int[] var6 = { var1, var3, var4 };
/* 332 */     placeBlockLine(var5, var6, Block.wood.blockID);
/*     */     
/* 334 */     if (this.trunkSize == 2) {
/*     */       
/* 336 */       var5[0] = var5[0] + 1;
/* 337 */       var6[0] = var6[0] + 1;
/* 338 */       placeBlockLine(var5, var6, Block.wood.blockID);
/* 339 */       var5[2] = var5[2] + 1;
/* 340 */       var6[2] = var6[2] + 1;
/* 341 */       placeBlockLine(var5, var6, Block.wood.blockID);
/* 342 */       var5[0] = var5[0] + -1;
/* 343 */       var6[0] = var6[0] + -1;
/* 344 */       placeBlockLine(var5, var6, Block.wood.blockID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNodeBases() {
/* 353 */     int var1 = 0;
/* 354 */     int var2 = this.leafNodes.length;
/*     */     
/* 356 */     for (int[] var3 = { this.basePos[0], this.basePos[1], this.basePos[2] }; var1 < var2; var1++) {
/*     */       
/* 358 */       int[] var4 = this.leafNodes[var1];
/* 359 */       int[] var5 = { var4[0], var4[1], var4[2] };
/* 360 */       var3[1] = var4[3];
/* 361 */       int var6 = var3[1] - this.basePos[1];
/*     */       
/* 363 */       if (leafNodeNeedsBase(var6))
/*     */       {
/* 365 */         placeBlockLine(var3, var5, (byte)Block.wood.blockID);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
/*     */     byte var8;
/* 376 */     int[] var3 = { 0, 0, 0 };
/* 377 */     byte var4 = 0;
/*     */     
/*     */     byte var5;
/* 380 */     for (var5 = 0; var4 < 3; var4 = (byte)(var4 + 1)) {
/*     */       
/* 382 */       var3[var4] = par2ArrayOfInteger[var4] - par1ArrayOfInteger[var4];
/*     */       
/* 384 */       if (Math.abs(var3[var4]) > Math.abs(var3[var5]))
/*     */       {
/* 386 */         var5 = var4;
/*     */       }
/*     */     } 
/*     */     
/* 390 */     if (var3[var5] == 0)
/*     */     {
/* 392 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 396 */     byte var6 = otherCoordPairs[var5];
/* 397 */     byte var7 = otherCoordPairs[var5 + 3];
/*     */ 
/*     */     
/* 400 */     if (var3[var5] > 0) {
/*     */       
/* 402 */       var8 = 1;
/*     */     }
/*     */     else {
/*     */       
/* 406 */       var8 = -1;
/*     */     } 
/*     */     
/* 409 */     double var9 = var3[var6] / var3[var5];
/* 410 */     double var11 = var3[var7] / var3[var5];
/* 411 */     int[] var13 = { 0, 0, 0 };
/* 412 */     int var14 = 0;
/*     */     
/*     */     int var15;
/* 415 */     for (var15 = var3[var5] + var8; var14 != var15; var14 += var8) {
/*     */       
/* 417 */       var13[var5] = par1ArrayOfInteger[var5] + var14;
/* 418 */       var13[var6] = MathHelper.floor_double(par1ArrayOfInteger[var6] + var14 * var9);
/* 419 */       var13[var7] = MathHelper.floor_double(par1ArrayOfInteger[var7] + var14 * var11);
/* 420 */       int var16 = this.worldObj.getBlockId(var13[0], var13[1], var13[2]);
/*     */       
/* 422 */       if (var16 != 0 && var16 != Block.leaves.blockID) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 428 */     return (var14 == var15) ? -1 : Math.abs(var14);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean validTreeLocation() {
/* 438 */     int[] var1 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 439 */     int[] var2 = { this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2] };
/* 440 */     int var3 = this.worldObj.getBlockId(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
/*     */     
/* 442 */     if (var3 != 2 && var3 != 3)
/*     */     {
/* 444 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 448 */     int var4 = checkBlockLine(var1, var2);
/*     */     
/* 450 */     if (var4 == -1)
/*     */     {
/* 452 */       return true;
/*     */     }
/* 454 */     if (var4 < 6)
/*     */     {
/* 456 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 460 */     this.heightLimit = var4;
/* 461 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScale(double par1, double par3, double par5) {
/* 471 */     this.heightLimitLimit = (int)(par1 * 12.0D);
/*     */     
/* 473 */     if (par1 > 0.5D)
/*     */     {
/* 475 */       this.leafDistanceLimit = 5;
/*     */     }
/*     */     
/* 478 */     this.scaleWidth = par3;
/* 479 */     this.leafDensity = par5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/* 484 */     this.worldObj = par1World;
/* 485 */     long var6 = par2Random.nextLong();
/* 486 */     this.rand.setSeed(var6);
/* 487 */     this.basePos[0] = par3;
/* 488 */     this.basePos[1] = par4;
/* 489 */     this.basePos[2] = par5;
/*     */     
/* 491 */     if (this.heightLimit == 0)
/*     */     {
/* 493 */       this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
/*     */     }
/*     */     
/* 496 */     if (!validTreeLocation())
/*     */     {
/* 498 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 502 */     generateLeafNodeList();
/* 503 */     generateLeaves();
/* 504 */     generateTrunk();
/* 505 */     generateLeafNodeBases();
/*     */ 
/*     */ 
/*     */     
/* 509 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeightLimit(int height_limit) {
/* 515 */     this.heightLimit = height_limit;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenBigTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */