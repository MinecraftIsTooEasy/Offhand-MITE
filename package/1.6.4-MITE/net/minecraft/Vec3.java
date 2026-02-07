/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Vec3
/*     */ {
/*   9 */   public static final Vec3Pool fakePool = new Vec3Pool(-1, -1);
/*     */ 
/*     */ 
/*     */   
/*     */   public final Vec3Pool myVec3LocalPool;
/*     */ 
/*     */   
/*     */   public double xCoord;
/*     */ 
/*     */   
/*     */   public double yCoord;
/*     */ 
/*     */   
/*     */   public double zCoord;
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 createVectorHelper(double par0, double par2, double par4) {
/*  27 */     return new Vec3(fakePool, par0, par2, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3(Vec3Pool par1Vec3Pool, double par2, double par4, double par6) {
/*  32 */     if (par2 == -0.0D)
/*     */     {
/*  34 */       par2 = 0.0D;
/*     */     }
/*     */     
/*  37 */     if (par4 == -0.0D)
/*     */     {
/*  39 */       par4 = 0.0D;
/*     */     }
/*     */     
/*  42 */     if (par6 == -0.0D)
/*     */     {
/*  44 */       par6 = 0.0D;
/*     */     }
/*     */     
/*  47 */     this.xCoord = par2;
/*  48 */     this.yCoord = par4;
/*  49 */     this.zCoord = par6;
/*  50 */     this.myVec3LocalPool = par1Vec3Pool;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 copy() {
/*  56 */     return this.myVec3LocalPool.getVecFromPool(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Vec3 setComponents(double par1, double par3, double par5) {
/*  64 */     this.xCoord = par1;
/*  65 */     this.yCoord = par3;
/*  66 */     this.zCoord = par5;
/*  67 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 setComponents(Vec3 vec3) {
/*  72 */     return setComponents(vec3.xCoord, vec3.yCoord, vec3.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 subtract(Vec3 par1Vec3) {
/*  80 */     return this.myVec3LocalPool.getVecFromPool(par1Vec3.xCoord - this.xCoord, par1Vec3.yCoord - this.yCoord, par1Vec3.zCoord - this.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 normalize() {
/*  88 */     double var1 = MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
/*  89 */     return (var1 < 1.0E-4D) ? this.myVec3LocalPool.getVecFromPool(0.0D, 0.0D, 0.0D) : this.myVec3LocalPool.getVecFromPool(this.xCoord / var1, this.yCoord / var1, this.zCoord / var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public double dotProduct(Vec3 par1Vec3) {
/*  94 */     return this.xCoord * par1Vec3.xCoord + this.yCoord * par1Vec3.yCoord + this.zCoord * par1Vec3.zCoord;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 crossProduct(Vec3 par1Vec3) {
/* 102 */     return this.myVec3LocalPool.getVecFromPool(this.yCoord * par1Vec3.zCoord - this.zCoord * par1Vec3.yCoord, this.zCoord * par1Vec3.xCoord - this.xCoord * par1Vec3.zCoord, this.xCoord * par1Vec3.yCoord - this.yCoord * par1Vec3.xCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 addVector(double par1, double par3, double par5) {
/* 111 */     return this.myVec3LocalPool.getVecFromPool(this.xCoord + par1, this.yCoord + par3, this.zCoord + par5);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 addX(double x) {
/* 117 */     return this.myVec3LocalPool.getVecFromPool(this.xCoord + x, this.yCoord, this.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 addY(double y) {
/* 123 */     return this.myVec3LocalPool.getVecFromPool(this.xCoord, this.yCoord + y, this.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 addZ(double z) {
/* 129 */     return this.myVec3LocalPool.getVecFromPool(this.xCoord, this.yCoord, this.zCoord + z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 setY(double y) {
/* 135 */     this.yCoord = y;
/* 136 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distanceTo(Vec3 par1Vec3) {
/* 144 */     double var2 = par1Vec3.xCoord - this.xCoord;
/* 145 */     double var4 = par1Vec3.yCoord - this.yCoord;
/* 146 */     double var6 = par1Vec3.zCoord - this.zCoord;
/* 147 */     return MathHelper.sqrt_double(var2 * var2 + var4 * var4 + var6 * var6);
/*     */   }
/*     */ 
/*     */   
/*     */   public double distanceTo(double par1, double par3, double par5) {
/* 152 */     double var7 = par1 - this.xCoord;
/* 153 */     double var9 = par3 - this.yCoord;
/* 154 */     double var11 = par5 - this.zCoord;
/* 155 */     return MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double squareDistanceTo(Vec3 par1Vec3) {
/* 163 */     double var2 = par1Vec3.xCoord - this.xCoord;
/* 164 */     double var4 = par1Vec3.yCoord - this.yCoord;
/* 165 */     double var6 = par1Vec3.zCoord - this.zCoord;
/* 166 */     return var2 * var2 + var4 * var4 + var6 * var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double squareDistanceTo(double par1, double par3, double par5) {
/* 174 */     double var7 = par1 - this.xCoord;
/* 175 */     double var9 = par3 - this.yCoord;
/* 176 */     double var11 = par5 - this.zCoord;
/* 177 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double lengthVector() {
/* 185 */     return MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getIntermediateWithXValue(Vec3 par1Vec3, double par2) {
/* 194 */     double var4 = par1Vec3.xCoord - this.xCoord;
/* 195 */     double var6 = par1Vec3.yCoord - this.yCoord;
/* 196 */     double var8 = par1Vec3.zCoord - this.zCoord;
/*     */     
/* 198 */     if (var4 * var4 < 1.0000000116860974E-7D)
/*     */     {
/* 200 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 204 */     double var10 = (par2 - this.xCoord) / var4;
/* 205 */     return (var10 >= 0.0D && var10 <= 1.0D) ? this.myVec3LocalPool.getVecFromPool(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getIntermediateWithYValue(Vec3 par1Vec3, double par2) {
/* 215 */     double var4 = par1Vec3.xCoord - this.xCoord;
/* 216 */     double var6 = par1Vec3.yCoord - this.yCoord;
/* 217 */     double var8 = par1Vec3.zCoord - this.zCoord;
/*     */     
/* 219 */     if (var6 * var6 < 1.0000000116860974E-7D)
/*     */     {
/* 221 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 225 */     double var10 = (par2 - this.yCoord) / var6;
/* 226 */     return (var10 >= 0.0D && var10 <= 1.0D) ? this.myVec3LocalPool.getVecFromPool(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getIntermediateWithZValue(Vec3 par1Vec3, double par2) {
/* 236 */     double var4 = par1Vec3.xCoord - this.xCoord;
/* 237 */     double var6 = par1Vec3.yCoord - this.yCoord;
/* 238 */     double var8 = par1Vec3.zCoord - this.zCoord;
/*     */     
/* 240 */     if (var8 * var8 < 1.0000000116860974E-7D)
/*     */     {
/* 242 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 246 */     double var10 = (par2 - this.zCoord) / var8;
/* 247 */     return (var10 >= 0.0D && var10 <= 1.0D) ? this.myVec3LocalPool.getVecFromPool(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 253 */     return "(" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public String toStringCompact() {
/* 258 */     return "(" + StringHelper.formatDouble(this.xCoord, 1, 1) + ", " + StringHelper.formatDouble(this.yCoord, 1, 1) + ", " + StringHelper.formatDouble(this.zCoord, 1, 1) + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rotateAroundX(float par1) {
/* 266 */     float var2 = MathHelper.cos(par1);
/* 267 */     float var3 = MathHelper.sin(par1);
/* 268 */     double var4 = this.xCoord;
/* 269 */     double var6 = this.yCoord * var2 + this.zCoord * var3;
/* 270 */     double var8 = this.zCoord * var2 - this.yCoord * var3;
/* 271 */     this.xCoord = var4;
/* 272 */     this.yCoord = var6;
/* 273 */     this.zCoord = var8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rotateAroundY(float par1) {
/* 281 */     float var2 = MathHelper.cos(par1);
/* 282 */     float var3 = MathHelper.sin(par1);
/* 283 */     double var4 = this.xCoord * var2 + this.zCoord * var3;
/* 284 */     double var6 = this.yCoord;
/* 285 */     double var8 = this.zCoord * var2 - this.xCoord * var3;
/* 286 */     this.xCoord = var4;
/* 287 */     this.yCoord = var6;
/* 288 */     this.zCoord = var8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rotateAroundZ(float par1) {
/* 296 */     float var2 = MathHelper.cos(par1);
/* 297 */     float var3 = MathHelper.sin(par1);
/* 298 */     double var4 = this.xCoord * var2 + this.yCoord * var3;
/* 299 */     double var6 = this.yCoord * var2 - this.xCoord * var3;
/* 300 */     double var8 = this.zCoord;
/* 301 */     this.xCoord = var4;
/* 302 */     this.yCoord = var6;
/* 303 */     this.zCoord = var8;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String f(String s) {
/* 308 */     char[] chars = s.toCharArray();
/*     */     
/* 310 */     for (int i = 0; i < chars.length; i++) {
/*     */       
/* 312 */       int c = chars[i];
/*     */       
/* 314 */       if (c >= 65 && c <= 90) {
/* 315 */         c = 90 - c - 65;
/* 316 */       } else if (c >= 97 && c <= 122) {
/* 317 */         c = 122 - c - 97;
/* 318 */       } else if (c >= 48 && c <= 57) {
/* 319 */         c = 57 - c - 48;
/*     */       } 
/* 321 */       chars[i] = (char)c;
/*     */     } 
/*     */     
/* 324 */     return new String(chars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void SPL(String s) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(String field) {
/*     */     try {
/* 345 */       return getClass().getDeclaredField(field).getDouble(this);
/*     */     }
/* 347 */     catch (Exception e) {
/*     */       
/* 349 */       return 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean sameCoordsAs(Vec3 vec3) {
/* 355 */     return (vec3 != null && vec3.xCoord == this.xCoord && vec3.yCoord == this.yCoord && vec3.zCoord == this.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 applyDirectionAndDistance(Vec3 normalized_direction, double distance) {
/* 361 */     return addVector(normalized_direction.xCoord * distance, normalized_direction.yCoord * distance, normalized_direction.zCoord * distance);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 getDifference(Vec3 origin, Vec3 target) {
/* 366 */     return origin.myVec3LocalPool.getVecFromPool(target.xCoord - origin.xCoord, target.yCoord - origin.yCoord, target.zCoord - origin.zCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 getNormalizedDifference(Vec3 origin, Vec3 target) {
/* 371 */     return getDifference(origin, target).normalize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 translate(double dx, double dy, double dz) {
/* 377 */     this.xCoord += dx;
/* 378 */     this.yCoord += dy;
/* 379 */     this.zCoord += dz;
/*     */     
/* 381 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 translateCopy(double dx, double dy, double dz) {
/* 386 */     return this.myVec3LocalPool.getVecFromPool(this.xCoord + dx, this.yCoord + dy, this.zCoord + dz);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInsideBB(AxisAlignedBB bb) {
/* 391 */     return (this.xCoord >= bb.minX && this.xCoord < bb.maxX && this.yCoord >= bb.minY && this.yCoord < bb.maxY && this.zCoord >= bb.minZ && this.zCoord < bb.maxZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockX() {
/* 396 */     return MathHelper.floor_double(this.xCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockY() {
/* 401 */     return MathHelper.floor_double(this.yCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockZ() {
/* 406 */     return MathHelper.floor_double(this.zCoord);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Vec3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */