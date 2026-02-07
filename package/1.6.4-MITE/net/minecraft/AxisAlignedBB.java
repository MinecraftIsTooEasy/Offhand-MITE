/*      */ package net.minecraft;
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class AxisAlignedBB
/*      */ {
/*    7 */   private static final ThreadLocal theAABBLocalPool = new AABBLocalPool();
/*      */   
/*      */   public double minX;
/*      */   
/*      */   public double minY;
/*      */   
/*      */   public double minZ;
/*      */   
/*      */   public double maxX;
/*      */   public double maxY;
/*      */   public double maxZ;
/*      */   
/*      */   public AxisAlignedBB() {}
/*      */   
/*      */   public static AxisAlignedBB getBoundingBox(double par0, double par2, double par4, double par6, double par8, double par10) {
/*   22 */     return new AxisAlignedBB(par0, par2, par4, par6, par8, par10);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static AxisAlignedBB getBoundingBoxFromPool(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z) {
/*   28 */     return getAABBPool().getAABB(min_x, min_y, min_z, max_x, max_y, max_z);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static AxisAlignedBB getBoundingBoxFromPool(int x, int y, int z, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
/*   34 */     return getAABBPool().getAABB(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AABBPool getAABBPool() {
/*   42 */     return theAABBLocalPool.get();
/*      */   }
/*      */ 
/*      */   
/*      */   protected AxisAlignedBB(double par1, double par3, double par5, double par7, double par9, double par11) {
/*   47 */     this.minX = par1;
/*   48 */     this.minY = par3;
/*   49 */     this.minZ = par5;
/*   50 */     this.maxX = par7;
/*   51 */     this.maxY = par9;
/*   52 */     this.maxZ = par11;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected AxisAlignedBB(AxisAlignedBB bb) {
/*   58 */     this.minX = bb.minX;
/*   59 */     this.minY = bb.minY;
/*   60 */     this.minZ = bb.minZ;
/*   61 */     this.maxX = bb.maxX;
/*   62 */     this.maxY = bb.maxY;
/*   63 */     this.maxZ = bb.maxZ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB setBounds(double par1, double par3, double par5, double par7, double par9, double par11) {
/*   71 */     this.minX = par1;
/*   72 */     this.minY = par3;
/*   73 */     this.minZ = par5;
/*   74 */     this.maxX = par7;
/*   75 */     this.maxY = par9;
/*   76 */     this.maxZ = par11;
/*   77 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB setBounds(int x, int y, int z, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
/*   82 */     this.minX = x + minX;
/*   83 */     this.minY = y + minY;
/*   84 */     this.minZ = z + minZ;
/*   85 */     this.maxX = x + maxX;
/*   86 */     this.maxY = y + maxY;
/*   87 */     this.maxZ = z + maxZ;
/*      */     
/*   89 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB addCoord(double par1, double par3, double par5) {
/*   97 */     double var7 = this.minX;
/*   98 */     double var9 = this.minY;
/*   99 */     double var11 = this.minZ;
/*  100 */     double var13 = this.maxX;
/*  101 */     double var15 = this.maxY;
/*  102 */     double var17 = this.maxZ;
/*      */     
/*  104 */     if (par1 < 0.0D)
/*      */     {
/*  106 */       var7 += par1;
/*      */     }
/*      */     
/*  109 */     if (par1 > 0.0D)
/*      */     {
/*  111 */       var13 += par1;
/*      */     }
/*      */     
/*  114 */     if (par3 < 0.0D)
/*      */     {
/*  116 */       var9 += par3;
/*      */     }
/*      */     
/*  119 */     if (par3 > 0.0D)
/*      */     {
/*  121 */       var15 += par3;
/*      */     }
/*      */     
/*  124 */     if (par5 < 0.0D)
/*      */     {
/*  126 */       var11 += par5;
/*      */     }
/*      */     
/*  129 */     if (par5 > 0.0D)
/*      */     {
/*  131 */       var17 += par5;
/*      */     }
/*      */     
/*  134 */     return getAABBPool().getAABB(var7, var9, var11, var13, var15, var17);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB expand(double par1, double par3, double par5) {
/*  143 */     double var7 = this.minX - par1;
/*  144 */     double var9 = this.minY - par3;
/*  145 */     double var11 = this.minZ - par5;
/*  146 */     double var13 = this.maxX + par1;
/*  147 */     double var15 = this.maxY + par3;
/*  148 */     double var17 = this.maxZ + par5;
/*  149 */     return getAABBPool().getAABB(var7, var9, var11, var13, var15, var17);
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB func_111270_a(AxisAlignedBB par1AxisAlignedBB) {
/*  154 */     double var2 = Math.min(this.minX, par1AxisAlignedBB.minX);
/*  155 */     double var4 = Math.min(this.minY, par1AxisAlignedBB.minY);
/*  156 */     double var6 = Math.min(this.minZ, par1AxisAlignedBB.minZ);
/*  157 */     double var8 = Math.max(this.maxX, par1AxisAlignedBB.maxX);
/*  158 */     double var10 = Math.max(this.maxY, par1AxisAlignedBB.maxY);
/*  159 */     double var12 = Math.max(this.maxZ, par1AxisAlignedBB.maxZ);
/*  160 */     return getAABBPool().getAABB(var2, var4, var6, var8, var10, var12);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB include(AxisAlignedBB bb) {
/*  166 */     if (bb.minX < this.minX) {
/*  167 */       this.minX = bb.minX;
/*      */     }
/*  169 */     if (bb.maxX > this.maxX) {
/*  170 */       this.maxX = bb.maxX;
/*      */     }
/*  172 */     if (bb.minY < this.minY) {
/*  173 */       this.minY = bb.minY;
/*      */     }
/*  175 */     if (bb.maxY > this.maxY) {
/*  176 */       this.maxY = bb.maxY;
/*      */     }
/*  178 */     if (bb.minZ < this.minZ) {
/*  179 */       this.minZ = bb.minZ;
/*      */     }
/*  181 */     if (bb.maxZ > this.maxZ) {
/*  182 */       this.maxZ = bb.maxZ;
/*      */     }
/*  184 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getOffsetBoundingBox(double par1, double par3, double par5) {
/*  193 */     return getAABBPool().getAABB(this.minX + par1, this.minY + par3, this.minZ + par5, this.maxX + par1, this.maxY + par3, this.maxZ + par5);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double calculateXOffset(AxisAlignedBB par1AxisAlignedBB, double par2, double[] limits) {
/*  204 */     if (par1AxisAlignedBB.maxY > this.minY && par1AxisAlignedBB.minY < this.maxY) {
/*      */       
/*  206 */       if (par1AxisAlignedBB.maxZ > this.minZ && par1AxisAlignedBB.minZ < this.maxZ) {
/*      */ 
/*      */ 
/*      */         
/*  210 */         if (par2 > 0.0D && par1AxisAlignedBB.maxX <= this.minX) {
/*      */           
/*  212 */           if (Double.isNaN(limits[0]) || limits[0] > this.minX) {
/*  213 */             limits[0] = this.minX;
/*      */           }
/*  215 */           double var4 = this.minX - par1AxisAlignedBB.maxX;
/*      */           
/*  217 */           if (var4 < par2)
/*      */           {
/*  219 */             par2 = var4;
/*      */           }
/*      */         } 
/*      */         
/*  223 */         if (par2 < 0.0D && par1AxisAlignedBB.minX >= this.maxX) {
/*      */           
/*  225 */           if (Double.isNaN(limits[1]) || limits[1] < this.maxX) {
/*  226 */             limits[1] = this.maxX;
/*      */           }
/*  228 */           double var4 = this.maxX - par1AxisAlignedBB.minX;
/*      */           
/*  230 */           if (var4 > par2)
/*      */           {
/*  232 */             par2 = var4;
/*      */           }
/*      */         } 
/*      */         
/*  236 */         return par2;
/*      */       } 
/*      */ 
/*      */       
/*  240 */       return par2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  245 */     return par2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double calculateYOffset(AxisAlignedBB par1AxisAlignedBB, double par2, double[] limits) {
/*  257 */     if (par1AxisAlignedBB.maxX > this.minX && par1AxisAlignedBB.minX < this.maxX) {
/*      */       
/*  259 */       if (par1AxisAlignedBB.maxZ > this.minZ && par1AxisAlignedBB.minZ < this.maxZ) {
/*      */ 
/*      */ 
/*      */         
/*  263 */         if (par2 > 0.0D && par1AxisAlignedBB.maxY <= this.minY) {
/*      */           
/*  265 */           if (Double.isNaN(limits[0]) || limits[0] > this.minY) {
/*  266 */             limits[0] = this.minY;
/*      */           }
/*  268 */           double var4 = this.minY - par1AxisAlignedBB.maxY;
/*      */           
/*  270 */           if (var4 < par2)
/*      */           {
/*  272 */             par2 = var4;
/*      */           }
/*      */         } 
/*      */         
/*  276 */         if (par2 < 0.0D && par1AxisAlignedBB.minY >= this.maxY) {
/*      */           
/*  278 */           if (Double.isNaN(limits[1]) || limits[1] < this.maxY) {
/*  279 */             limits[1] = this.maxY;
/*      */           }
/*  281 */           double var4 = this.maxY - par1AxisAlignedBB.minY;
/*      */           
/*  283 */           if (var4 > par2)
/*      */           {
/*  285 */             par2 = var4;
/*      */           }
/*      */         } 
/*      */         
/*  289 */         return par2;
/*      */       } 
/*      */ 
/*      */       
/*  293 */       return par2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  298 */     return par2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double calculateZOffset(AxisAlignedBB par1AxisAlignedBB, double par2, double[] limits) {
/*  310 */     if (par1AxisAlignedBB.maxX > this.minX && par1AxisAlignedBB.minX < this.maxX) {
/*      */       
/*  312 */       if (par1AxisAlignedBB.maxY > this.minY && par1AxisAlignedBB.minY < this.maxY) {
/*      */ 
/*      */ 
/*      */         
/*  316 */         if (par2 > 0.0D && par1AxisAlignedBB.maxZ <= this.minZ) {
/*      */           
/*  318 */           if (Double.isNaN(limits[0]) || limits[0] > this.minZ) {
/*  319 */             limits[0] = this.minZ;
/*      */           }
/*  321 */           double var4 = this.minZ - par1AxisAlignedBB.maxZ;
/*      */           
/*  323 */           if (var4 < par2)
/*      */           {
/*  325 */             par2 = var4;
/*      */           }
/*      */         } 
/*      */         
/*  329 */         if (par2 < 0.0D && par1AxisAlignedBB.minZ >= this.maxZ) {
/*      */           
/*  331 */           if (Double.isNaN(limits[1]) || limits[1] < this.maxZ) {
/*  332 */             limits[1] = this.maxZ;
/*      */           }
/*  334 */           double var4 = this.maxZ - par1AxisAlignedBB.minZ;
/*      */           
/*  336 */           if (var4 > par2)
/*      */           {
/*  338 */             par2 = var4;
/*      */           }
/*      */         } 
/*      */         
/*  342 */         return par2;
/*      */       } 
/*      */ 
/*      */       
/*  346 */       return par2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  351 */     return par2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean intersectsWith(AxisAlignedBB par1AxisAlignedBB) {
/*  360 */     return (par1AxisAlignedBB.maxX > this.minX && par1AxisAlignedBB.minX < this.maxX) ? ((par1AxisAlignedBB.maxY > this.minY && par1AxisAlignedBB.minY < this.maxY) ? ((par1AxisAlignedBB.maxZ > this.minZ && par1AxisAlignedBB.minZ < this.maxZ)) : false) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB offset(double par1, double par3, double par5) {
/*  368 */     this.minX += par1;
/*  369 */     this.minY += par3;
/*  370 */     this.minZ += par5;
/*  371 */     this.maxX += par1;
/*  372 */     this.maxY += par3;
/*  373 */     this.maxZ += par5;
/*  374 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public void offsetX(double dx) {
/*  379 */     this.minX += dx;
/*  380 */     this.maxX += dx;
/*      */   }
/*      */ 
/*      */   
/*      */   public void offsetY(double dy) {
/*  385 */     this.minY += dy;
/*  386 */     this.maxY += dy;
/*      */   }
/*      */ 
/*      */   
/*      */   public void offsetZ(double dz) {
/*  391 */     this.minZ += dz;
/*  392 */     this.maxZ += dz;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isVecInside(Vec3 par1Vec3) {
/*  400 */     return (par1Vec3.xCoord > this.minX && par1Vec3.xCoord < this.maxX) ? ((par1Vec3.yCoord > this.minY && par1Vec3.yCoord < this.maxY) ? ((par1Vec3.zCoord > this.minZ && par1Vec3.zCoord < this.maxZ)) : false) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getAverageEdgeLength() {
/*  408 */     double var1 = this.maxX - this.minX;
/*  409 */     double var3 = this.maxY - this.minY;
/*  410 */     double var5 = this.maxZ - this.minZ;
/*  411 */     return (var1 + var3 + var5) / 3.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB contract(double par1, double par3, double par5) {
/*  419 */     double var7 = this.minX + par1;
/*  420 */     double var9 = this.minY + par3;
/*  421 */     double var11 = this.minZ + par5;
/*  422 */     double var13 = this.maxX - par1;
/*  423 */     double var15 = this.maxY - par3;
/*  424 */     double var17 = this.maxZ - par5;
/*  425 */     return getAABBPool().getAABB(var7, var9, var11, var13, var15, var17);
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB scale(double amount) {
/*  430 */     double center_x = (this.minX + this.maxX) * 0.5D;
/*  431 */     double center_y = (this.minY + this.maxY) * 0.5D;
/*  432 */     double center_z = (this.minZ + this.maxZ) * 0.5D;
/*      */     
/*  434 */     this.minX = center_x + (this.minX - center_x) * amount;
/*  435 */     this.minY = center_y + (this.minY - center_y) * amount;
/*  436 */     this.minZ = center_z + (this.minZ - center_z) * amount;
/*      */     
/*  438 */     this.maxX = center_x + (this.maxX - center_x) * amount;
/*  439 */     this.maxY = center_y + (this.maxY - center_y) * amount;
/*  440 */     this.maxZ = center_z + (this.maxZ - center_z) * amount;
/*      */     
/*  442 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB scaleXZ(double amount) {
/*  466 */     double center_x = (this.minX + this.maxX) * 0.5D;
/*  467 */     double center_z = (this.minZ + this.maxZ) * 0.5D;
/*      */     
/*  469 */     this.minX = center_x + (this.minX - center_x) * amount;
/*  470 */     this.minZ = center_z + (this.minZ - center_z) * amount;
/*      */     
/*  472 */     this.maxX = center_x + (this.maxX - center_x) * amount;
/*  473 */     this.maxZ = center_z + (this.maxZ - center_z) * amount;
/*      */     
/*  475 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB translate(double x, double y, double z) {
/*  504 */     this.minX += x;
/*  505 */     this.minY += y;
/*  506 */     this.minZ += z;
/*      */     
/*  508 */     this.maxX += x;
/*  509 */     this.maxY += y;
/*  510 */     this.maxZ += z;
/*      */     
/*  512 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB translateCopy(double dx, double dy, double dz) {
/*  517 */     return copy().translate(dx, dy, dz);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB copy() {
/*  525 */     return getAABBPool().getAABB(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public static AxisAlignedBB[] copyArray(AxisAlignedBB[] array) {
/*  530 */     AxisAlignedBB[] copied_array = new AxisAlignedBB[array.length];
/*      */     
/*  532 */     for (int i = 0; i < array.length; i++) {
/*      */       
/*  534 */       if (array[i] != null)
/*      */       {
/*      */         
/*  537 */         copied_array[i] = array[i].copy();
/*      */       }
/*      */     } 
/*  540 */     return copied_array;
/*      */   }
/*      */ 
/*      */   
/*      */   public static AxisAlignedBB[] translateCopy(AxisAlignedBB[] array, double dx, double dy, double dz) {
/*  545 */     AxisAlignedBB[] copied_array = new AxisAlignedBB[array.length];
/*      */     
/*  547 */     for (int i = 0; i < array.length; i++) {
/*      */       
/*  549 */       if (array[i] != null)
/*      */       {
/*      */         
/*  552 */         copied_array[i] = array[i].translateCopy(dx, dy, dz);
/*      */       }
/*      */     } 
/*  555 */     return copied_array;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AABBIntercept calculateIntercept(World world, Vec3 par1Vec3, Vec3 par2Vec3) {
/*      */     Vec3 var7, var8, var9, var10, var11, var12;
/*      */     EnumFace face_hit;
/*  679 */     double dx = par1Vec3.xCoord - par2Vec3.xCoord;
/*  680 */     double dy = par1Vec3.yCoord - par2Vec3.yCoord;
/*  681 */     double dz = par1Vec3.zCoord - par2Vec3.zCoord;
/*      */     
/*  683 */     if (dx * dx < 1.0000000116860974E-7D) {
/*      */       
/*  685 */       var7 = null;
/*  686 */       var8 = null;
/*      */     }
/*      */     else {
/*      */       
/*  690 */       double unknown = (this.minX - par2Vec3.xCoord) / dx;
/*  691 */       var7 = (unknown >= 0.0D && unknown <= 1.0D) ? par2Vec3.myVec3LocalPool.getVecFromPool(par2Vec3.xCoord + dx * unknown, par2Vec3.yCoord + dy * unknown, par2Vec3.zCoord + dz * unknown) : null;
/*      */       
/*  693 */       unknown = (this.maxX - par2Vec3.xCoord) / dx;
/*  694 */       var8 = (unknown >= 0.0D && unknown <= 1.0D) ? par2Vec3.myVec3LocalPool.getVecFromPool(par2Vec3.xCoord + dx * unknown, par2Vec3.yCoord + dy * unknown, par2Vec3.zCoord + dz * unknown) : null;
/*      */     } 
/*      */ 
/*      */     
/*  698 */     if (dy * dy < 1.0000000116860974E-7D) {
/*      */       
/*  700 */       var9 = null;
/*  701 */       var10 = null;
/*      */     }
/*      */     else {
/*      */       
/*  705 */       double unknown = (this.minY - par2Vec3.yCoord) / dy;
/*  706 */       var9 = (unknown >= 0.0D && unknown <= 1.0D) ? par2Vec3.myVec3LocalPool.getVecFromPool(par2Vec3.xCoord + dx * unknown, par2Vec3.yCoord + dy * unknown, par2Vec3.zCoord + dz * unknown) : null;
/*      */       
/*  708 */       unknown = (this.maxY - par2Vec3.yCoord) / dy;
/*  709 */       var10 = (unknown >= 0.0D && unknown <= 1.0D) ? par2Vec3.myVec3LocalPool.getVecFromPool(par2Vec3.xCoord + dx * unknown, par2Vec3.yCoord + dy * unknown, par2Vec3.zCoord + dz * unknown) : null;
/*      */     } 
/*      */     
/*  712 */     if (dz * dz < 1.0000000116860974E-7D) {
/*      */       
/*  714 */       var11 = null;
/*  715 */       var12 = null;
/*      */     }
/*      */     else {
/*      */       
/*  719 */       double unknown = (this.minZ - par2Vec3.zCoord) / dz;
/*  720 */       var11 = (unknown >= 0.0D && unknown <= 1.0D) ? par2Vec3.myVec3LocalPool.getVecFromPool(par2Vec3.xCoord + dx * unknown, par2Vec3.yCoord + dy * unknown, par2Vec3.zCoord + dz * unknown) : null;
/*      */       
/*  722 */       unknown = (this.maxZ - par2Vec3.zCoord) / dz;
/*  723 */       var12 = (unknown >= 0.0D && unknown <= 1.0D) ? par2Vec3.myVec3LocalPool.getVecFromPool(par2Vec3.xCoord + dx * unknown, par2Vec3.yCoord + dy * unknown, par2Vec3.zCoord + dz * unknown) : null;
/*      */     } 
/*      */     
/*  726 */     if (var7 != null && (var7.yCoord < this.minY || var7.yCoord > this.maxY || var7.zCoord < this.minZ || var7.zCoord > this.maxZ)) {
/*  727 */       var7 = null;
/*      */     }
/*  729 */     if (var8 != null && (var8.yCoord < this.minY || var8.yCoord > this.maxY || var8.zCoord < this.minZ || var8.zCoord > this.maxZ)) {
/*  730 */       var8 = null;
/*      */     }
/*  732 */     if (var9 != null && (var9.xCoord < this.minX || var9.xCoord > this.maxX || var9.zCoord < this.minZ || var9.zCoord > this.maxZ)) {
/*  733 */       var9 = null;
/*      */     }
/*  735 */     if (var10 != null && (var10.xCoord < this.minX || var10.xCoord > this.maxX || var10.zCoord < this.minZ || var10.zCoord > this.maxZ)) {
/*  736 */       var10 = null;
/*      */     }
/*  738 */     if (var11 != null && (var11.xCoord < this.minX || var11.xCoord > this.maxX || var11.yCoord < this.minY || var11.yCoord > this.maxY)) {
/*  739 */       var11 = null;
/*      */     }
/*  741 */     if (var12 != null && (var12.xCoord < this.minX || var12.xCoord > this.maxX || var12.yCoord < this.minY || var12.yCoord > this.maxY)) {
/*  742 */       var12 = null;
/*      */     }
/*  744 */     Vec3 var13 = null;
/*      */ 
/*      */ 
/*      */     
/*  748 */     if (var7 != null) {
/*  749 */       var13 = var7;
/*      */     }
/*  751 */     if (var8 != null)
/*      */     {
/*  753 */       if (var13 == null) {
/*      */         
/*  755 */         var13 = var8;
/*      */       }
/*      */       else {
/*      */         
/*  759 */         dx = var8.xCoord - par1Vec3.xCoord;
/*  760 */         dy = var8.yCoord - par1Vec3.yCoord;
/*  761 */         dz = var8.zCoord - par1Vec3.zCoord;
/*  762 */         double distance_squared_left = dx * dx + dy * dy + dz * dz;
/*      */         
/*  764 */         dx = var13.xCoord - par1Vec3.xCoord;
/*  765 */         dy = var13.yCoord - par1Vec3.yCoord;
/*  766 */         dz = var13.zCoord - par1Vec3.zCoord;
/*  767 */         double distance_squared_right = dx * dx + dy * dy + dz * dz;
/*      */         
/*  769 */         if (distance_squared_left < distance_squared_right) {
/*  770 */           var13 = var8;
/*      */         }
/*      */       } 
/*      */     }
/*  774 */     if (var9 != null)
/*      */     {
/*  776 */       if (var13 == null) {
/*      */         
/*  778 */         var13 = var9;
/*      */       }
/*      */       else {
/*      */         
/*  782 */         dx = var9.xCoord - par1Vec3.xCoord;
/*  783 */         dy = var9.yCoord - par1Vec3.yCoord;
/*  784 */         dz = var9.zCoord - par1Vec3.zCoord;
/*  785 */         double distance_squared_left = dx * dx + dy * dy + dz * dz;
/*      */         
/*  787 */         dx = var13.xCoord - par1Vec3.xCoord;
/*  788 */         dy = var13.yCoord - par1Vec3.yCoord;
/*  789 */         dz = var13.zCoord - par1Vec3.zCoord;
/*  790 */         double distance_squared_right = dx * dx + dy * dy + dz * dz;
/*      */         
/*  792 */         if (distance_squared_left < distance_squared_right) {
/*  793 */           var13 = var9;
/*      */         }
/*      */       } 
/*      */     }
/*  797 */     if (var10 != null)
/*      */     {
/*  799 */       if (var13 == null) {
/*      */         
/*  801 */         var13 = var10;
/*      */       }
/*      */       else {
/*      */         
/*  805 */         dx = var10.xCoord - par1Vec3.xCoord;
/*  806 */         dy = var10.yCoord - par1Vec3.yCoord;
/*  807 */         dz = var10.zCoord - par1Vec3.zCoord;
/*  808 */         double distance_squared_left = dx * dx + dy * dy + dz * dz;
/*      */         
/*  810 */         dx = var13.xCoord - par1Vec3.xCoord;
/*  811 */         dy = var13.yCoord - par1Vec3.yCoord;
/*  812 */         dz = var13.zCoord - par1Vec3.zCoord;
/*  813 */         double distance_squared_right = dx * dx + dy * dy + dz * dz;
/*      */         
/*  815 */         if (distance_squared_left < distance_squared_right) {
/*  816 */           var13 = var10;
/*      */         }
/*      */       } 
/*      */     }
/*  820 */     if (var11 != null)
/*      */     {
/*  822 */       if (var13 == null) {
/*      */         
/*  824 */         var13 = var11;
/*      */       }
/*      */       else {
/*      */         
/*  828 */         dx = var11.xCoord - par1Vec3.xCoord;
/*  829 */         dy = var11.yCoord - par1Vec3.yCoord;
/*  830 */         dz = var11.zCoord - par1Vec3.zCoord;
/*  831 */         double distance_squared_left = dx * dx + dy * dy + dz * dz;
/*      */         
/*  833 */         dx = var13.xCoord - par1Vec3.xCoord;
/*  834 */         dy = var13.yCoord - par1Vec3.yCoord;
/*  835 */         dz = var13.zCoord - par1Vec3.zCoord;
/*  836 */         double distance_squared_right = dx * dx + dy * dy + dz * dz;
/*      */         
/*  838 */         if (distance_squared_left < distance_squared_right) {
/*  839 */           var13 = var11;
/*      */         }
/*      */       } 
/*      */     }
/*  843 */     if (var12 != null)
/*      */     {
/*  845 */       if (var13 == null) {
/*      */         
/*  847 */         var13 = var12;
/*      */       }
/*      */       else {
/*      */         
/*  851 */         dx = var12.xCoord - par1Vec3.xCoord;
/*  852 */         dy = var12.yCoord - par1Vec3.yCoord;
/*  853 */         dz = var12.zCoord - par1Vec3.zCoord;
/*  854 */         double distance_squared_left = dx * dx + dy * dy + dz * dz;
/*      */         
/*  856 */         dx = var13.xCoord - par1Vec3.xCoord;
/*  857 */         dy = var13.yCoord - par1Vec3.yCoord;
/*  858 */         dz = var13.zCoord - par1Vec3.zCoord;
/*  859 */         double distance_squared_right = dx * dx + dy * dy + dz * dz;
/*      */         
/*  861 */         if (distance_squared_left < distance_squared_right) {
/*  862 */           var13 = var12;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  902 */     if (var13 == null)
/*      */     {
/*  904 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  929 */     if (var13 == var12) {
/*  930 */       face_hit = EnumFace.SOUTH;
/*  931 */     } else if (var13 == var11) {
/*  932 */       face_hit = EnumFace.NORTH;
/*  933 */     } else if (var13 == var10) {
/*  934 */       face_hit = EnumFace.TOP;
/*  935 */     } else if (var13 == var9) {
/*  936 */       face_hit = EnumFace.BOTTOM;
/*  937 */     } else if (var13 == var8) {
/*  938 */       face_hit = EnumFace.EAST;
/*  939 */     } else if (var13 == var7) {
/*  940 */       face_hit = EnumFace.WEST;
/*      */     } else {
/*  942 */       face_hit = null;
/*      */     } 
/*      */ 
/*      */     
/*  946 */     return new AABBIntercept(var13, face_hit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isVecInYZ(Vec3 par1Vec3) {
/*  955 */     return (par1Vec3 == null) ? false : ((par1Vec3.yCoord >= this.minY && par1Vec3.yCoord <= this.maxY && par1Vec3.zCoord >= this.minZ && par1Vec3.zCoord <= this.maxZ));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isVecInXZ(Vec3 par1Vec3) {
/*  963 */     return (par1Vec3 == null) ? false : ((par1Vec3.xCoord >= this.minX && par1Vec3.xCoord <= this.maxX && par1Vec3.zCoord >= this.minZ && par1Vec3.zCoord <= this.maxZ));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isVecInXY(Vec3 par1Vec3) {
/*  971 */     return (par1Vec3 == null) ? false : ((par1Vec3.xCoord >= this.minX && par1Vec3.xCoord <= this.maxX && par1Vec3.yCoord >= this.minY && par1Vec3.yCoord <= this.maxY));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBB(AxisAlignedBB par1AxisAlignedBB) {
/*  979 */     this.minX = par1AxisAlignedBB.minX;
/*  980 */     this.minY = par1AxisAlignedBB.minY;
/*  981 */     this.minZ = par1AxisAlignedBB.minZ;
/*  982 */     this.maxX = par1AxisAlignedBB.maxX;
/*  983 */     this.maxY = par1AxisAlignedBB.maxY;
/*  984 */     this.maxZ = par1AxisAlignedBB.maxZ;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEquivalentTo(AxisAlignedBB bb) {
/*  989 */     return (this.minX == bb.minX && this.minY == bb.minY && this.minZ == bb.minZ && this.maxX == bb.maxX && this.maxY == bb.maxY && this.maxZ == bb.maxZ);
/*      */   }
/*      */ 
/*      */   
/*      */   private static final int getBlockCoordForMin(double min) {
/*  994 */     return MathHelper.floor_double(min);
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getBlockCoordForMinX() {
/*  999 */     return getBlockCoordForMin(this.minX);
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getBlockCoordForMinY() {
/* 1004 */     return getBlockCoordForMin(this.minY);
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getBlockCoordForMinZ() {
/* 1009 */     return getBlockCoordForMin(this.minZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int getBlockCoordForMax(double max) {
/* 1022 */     return MathHelper.ceiling_double_int(max) - 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBlockCoordForMaxX() {
/* 1028 */     return getBlockCoordForMax(this.maxX);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBlockCoordForMaxY() {
/* 1034 */     return getBlockCoordForMax(this.maxY);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBlockCoordForMaxZ() {
/* 1040 */     return getBlockCoordForMax(this.maxZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB setMinY(double min_y) {
/* 1045 */     this.minY = min_y;
/* 1046 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB setMaxY(double max_y) {
/* 1051 */     this.maxY = max_y;
/* 1052 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB addToMaxY(double amount) {
/* 1057 */     this.maxY += amount;
/* 1058 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1063 */     return "box[" + this.minX + ", " + this.minY + ", " + this.minZ + " -> " + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AxisAlignedBB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */