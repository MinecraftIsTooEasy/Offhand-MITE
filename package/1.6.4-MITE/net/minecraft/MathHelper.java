/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MathHelper
/*     */ {
/*  11 */   private static float[] SIN_TABLE = new float[65536];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final float sin(float par0) {
/*  18 */     return SIN_TABLE[(int)(par0 * 10430.378F) & 0xFFFF];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final float cos(float par0) {
/*  26 */     return SIN_TABLE[(int)(par0 * 10430.378F + 16384.0F) & 0xFFFF];
/*     */   }
/*     */ 
/*     */   
/*     */   public static final float sqrt_float(float par0) {
/*  31 */     return (float)Math.sqrt(par0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final float sqrt_double(double par0) {
/*  36 */     return (float)Math.sqrt(par0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int floor_float(float par0) {
/*  44 */     int var1 = (int)par0;
/*  45 */     return (par0 < var1) ? (var1 - 1) : var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int truncateDoubleToInt(double par0) {
/*  53 */     return (int)(par0 + 1024.0D) - 1024;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int floor_double(double par0) {
/*  61 */     int var2 = (int)par0;
/*  62 */     return (par0 < var2) ? (var2 - 1) : var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long floor_double_long(double par0) {
/*  70 */     long var2 = (long)par0;
/*  71 */     return (par0 < var2) ? (var2 - 1L) : var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float abs(float par0) {
/*  76 */     return (par0 >= 0.0F) ? par0 : -par0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int abs_int(int par0) {
/*  84 */     return (par0 >= 0) ? par0 : -par0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int ceiling_float_int(float par0) {
/*  89 */     int var1 = (int)par0;
/*  90 */     return (par0 > var1) ? (var1 + 1) : var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int ceiling_double_int(double par0) {
/*  95 */     int var2 = (int)par0;
/*  96 */     return (par0 > var2) ? (var2 + 1) : var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int clamp_int(int par0, int par1, int par2) {
/* 105 */     return (par0 < par1) ? par1 : ((par0 > par2) ? par2 : par0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float clamp_float(float par0, float par1, float par2) {
/* 114 */     return (par0 < par1) ? par1 : ((par0 > par2) ? par2 : par0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double clamp_double(double value, double min, double max) {
/* 119 */     return (value < min) ? min : ((value > max) ? max : value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double abs_max(double par0, double par2) {
/* 127 */     if (par0 < 0.0D)
/*     */     {
/* 129 */       par0 = -par0;
/*     */     }
/*     */     
/* 132 */     if (par2 < 0.0D)
/*     */     {
/* 134 */       par2 = -par2;
/*     */     }
/*     */     
/* 137 */     return (par0 > par2) ? par0 : par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int bucketInt(int par0, int par1) {
/* 145 */     return (par0 < 0) ? (-((-par0 - 1) / par1) - 1) : (par0 / par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean stringNullOrLengthZero(String par0Str) {
/* 153 */     return (par0Str == null || par0Str.length() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getRandomIntegerInRange(Random par0Random, int par1, int par2) {
/* 158 */     return (par1 >= par2) ? par1 : (par0Random.nextInt(par2 - par1 + 1) + par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getRandomDoubleInRange(Random par0Random, double par1, double par3) {
/* 163 */     return (par1 >= par3) ? par1 : (par0Random.nextDouble() * (par3 - par1) + par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double average(long[] par0ArrayOfLong) {
/* 168 */     long var1 = 0L;
/* 169 */     long[] var3 = par0ArrayOfLong;
/* 170 */     int var4 = par0ArrayOfLong.length;
/*     */     
/* 172 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 174 */       long var6 = var3[var5];
/* 175 */       var1 += var6;
/*     */     } 
/*     */     
/* 178 */     return var1 / par0ArrayOfLong.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float wrapAngleTo180_float(float par0) {
/* 186 */     par0 %= 360.0F;
/*     */     
/* 188 */     if (par0 >= 180.0F)
/*     */     {
/* 190 */       par0 -= 360.0F;
/*     */     }
/*     */     
/* 193 */     if (par0 < -180.0F)
/*     */     {
/* 195 */       par0 += 360.0F;
/*     */     }
/*     */     
/* 198 */     return par0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double wrapAngleTo180_double(double par0) {
/* 206 */     par0 %= 360.0D;
/*     */     
/* 208 */     if (par0 >= 180.0D)
/*     */     {
/* 210 */       par0 -= 360.0D;
/*     */     }
/*     */     
/* 213 */     if (par0 < -180.0D)
/*     */     {
/* 215 */       par0 += 360.0D;
/*     */     }
/*     */     
/* 218 */     return par0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseIntWithDefault(String par0Str, int par1) {
/* 226 */     int var2 = par1;
/*     */ 
/*     */     
/*     */     try {
/* 230 */       var2 = Integer.parseInt(par0Str);
/*     */     }
/* 232 */     catch (Throwable var4) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseIntWithDefaultAndMax(String par0Str, int par1, int par2) {
/* 245 */     int var3 = par1;
/*     */ 
/*     */     
/*     */     try {
/* 249 */       var3 = Integer.parseInt(par0Str);
/*     */     }
/* 251 */     catch (Throwable var5) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     if (var3 < par2)
/*     */     {
/* 258 */       var3 = par2;
/*     */     }
/*     */     
/* 261 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double parseDoubleWithDefault(String par0Str, double par1) {
/* 269 */     double var3 = par1;
/*     */ 
/*     */     
/*     */     try {
/* 273 */       var3 = Double.parseDouble(par0Str);
/*     */     }
/* 275 */     catch (Throwable var6) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double func_82713_a(String par0Str, double par1, double par3) {
/* 285 */     double var5 = par1;
/*     */ 
/*     */     
/*     */     try {
/* 289 */       var5 = Double.parseDouble(par0Str);
/*     */     }
/* 291 */     catch (Throwable var8) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     if (var5 < par3)
/*     */     {
/* 298 */       var5 = par3;
/*     */     }
/*     */     
/* 301 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   private static float getNearestFloat(float value, float[] values) {
/* 306 */     float nearest_float = values[0];
/* 307 */     float smallest_difference = nearest_float - value;
/*     */     
/* 309 */     if (smallest_difference < 0.0F) {
/* 310 */       smallest_difference = -smallest_difference;
/*     */     }
/* 312 */     for (int i = 1; i < values.length; i++) {
/*     */       
/* 314 */       float difference = values[i] - value;
/*     */       
/* 316 */       if (difference < 0.0F) {
/* 317 */         difference = -difference;
/*     */       }
/* 319 */       if (difference < smallest_difference) {
/*     */         
/* 321 */         nearest_float = values[i];
/* 322 */         smallest_difference = difference;
/*     */       } 
/*     */     } 
/*     */     
/* 326 */     return nearest_float;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float tryFitToFloats(float value, float[] values, float tolerance) {
/* 332 */     float nearest_float = getNearestFloat(value, values);
/*     */     
/* 334 */     if (value < nearest_float) {
/*     */       
/* 336 */       if (nearest_float - value <= tolerance) {
/* 337 */         return nearest_float;
/*     */       
/*     */       }
/*     */     }
/* 341 */     else if (value - nearest_float <= tolerance) {
/* 342 */       return nearest_float;
/*     */     } 
/*     */     
/* 345 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float tryFitToNearestInteger(float value, float tolerance) {
/* 350 */     float floor = (value < 0.0F) ? ((int)value - 1) : (int)value;
/* 351 */     float fraction = value - floor;
/*     */     
/* 353 */     if (fraction < 0.5F) {
/* 354 */       return (value - floor <= tolerance) ? floor : value;
/*     */     }
/* 356 */     return (floor + 1.0F - value <= tolerance) ? (floor + 1.0F) : value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getYawInDegrees(double origin_pos_x, double origin_pos_z, double target_pos_x, double target_pos_z) {
/* 362 */     double delta_pos_x = target_pos_x - origin_pos_x;
/* 363 */     double delta_pos_z = target_pos_z - origin_pos_z;
/*     */     
/* 365 */     double yaw = Math.atan2(delta_pos_z, delta_pos_x) * 180.0D / Math.PI - 90.0D;
/*     */     
/* 367 */     if (yaw < 0.0D) {
/* 368 */       yaw += 360.0D;
/*     */     }
/* 370 */     return yaw;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getYawInDegrees(Vec3 origin, Vec3 target) {
/* 376 */     return getYawInDegrees(origin.xCoord, origin.zCoord, target.xCoord, target.zCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getHorizontalDistance(double origin_pos_x, double origin_pos_z, double target_pos_x, double target_pos_z) {
/* 381 */     double delta_pos_x = target_pos_x - origin_pos_x;
/* 382 */     double delta_pos_z = target_pos_z - origin_pos_z;
/*     */     
/* 384 */     return sqrt_double(delta_pos_x * delta_pos_x + delta_pos_z * delta_pos_z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getPitchInDegrees(double origin_pos_x, double origin_pos_y, double origin_pos_z, double target_pos_x, double target_pos_y, double target_pos_z) {
/* 390 */     return getPitchInDegrees(getHorizontalDistance(origin_pos_x, origin_pos_z, target_pos_x, target_pos_z), origin_pos_y, target_pos_y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getPitchInDegrees(double horizontal_distance, double origin_pos_y, double target_pos_y) {
/* 396 */     double rise = target_pos_y - origin_pos_y;
/*     */     
/* 398 */     return -Math.atan2(rise, horizontal_distance) * 180.0D / Math.PI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getPitchInDegrees(Vec3 origin, Vec3 target) {
/* 404 */     return getPitchInDegrees(origin.xCoord, origin.yCoord, origin.zCoord, target.xCoord, target.yCoord, target.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 getNormalizedVector(float yaw, float pitch, Vec3Pool vec3_pool) {
/* 410 */     float a = cos(-yaw * 0.017453292F - 3.1415927F);
/* 411 */     float b = sin(-yaw * 0.017453292F - 3.1415927F);
/* 412 */     float c = -cos(-pitch * 0.017453292F);
/* 413 */     float d = sin(-pitch * 0.017453292F);
/*     */     
/* 415 */     return vec3_pool.getVecFromPool((b * c), d, (a * c));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 getNormalizedVector_DP(float yaw, float pitch, Vec3Pool vec3_pool) {
/* 421 */     double PI_divided_by_180 = 0.017453292519943295D;
/*     */     
/* 423 */     double a = Math.cos(-yaw * PI_divided_by_180 - Math.PI);
/* 424 */     double b = Math.sin(-yaw * PI_divided_by_180 - Math.PI);
/* 425 */     double c = -Math.cos(-pitch * PI_divided_by_180);
/* 426 */     double d = Math.sin(-pitch * PI_divided_by_180);
/*     */     
/* 428 */     return vec3_pool.getVecFromPool(b * c, d, a * c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int wrapToRange(int n, int min, int max) {
/* 434 */     int offset = min;
/*     */     
/* 436 */     n -= offset;
/* 437 */     min -= offset;
/* 438 */     max -= offset;
/*     */     
/* 440 */     int range = max + 1;
/*     */     
/* 442 */     if (n < min) {
/* 443 */       n -= (n - range + 1) / range * range;
/* 444 */     } else if (n > max) {
/* 445 */       n -= n / range * range;
/*     */     } 
/* 447 */     return n + offset;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getWrappedIndex(int n, int array_length) {
/* 453 */     return wrapToRange(n, 0, array_length - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isInRange(int n, int min, int max) {
/* 459 */     return (n >= min && n <= max);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getInterpolatedValue(double value_last_tick, double value_this_tick, float partial_tick) {
/* 464 */     return value_last_tick + (value_this_tick - value_last_tick) * partial_tick;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getIntPairHash(int a, int b) {
/* 470 */     int hash = 17;
/*     */     
/* 472 */     hash = hash * 31 + a;
/* 473 */     hash = hash * 31 + b;
/*     */     
/* 475 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 480 */     for (int var0 = 0; var0 < 65536; var0++)
/*     */     {
/* 482 */       SIN_TABLE[var0] = (float)Math.sin(var0 * Math.PI * 2.0D / 65536.0D);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MathHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */