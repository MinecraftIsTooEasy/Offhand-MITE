/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class NoiseGeneratorPerlin
/*     */   extends NoiseGenerator
/*     */ {
/*     */   private int[] permutations;
/*     */   public double xCoord;
/*     */   public double yCoord;
/*     */   public double zCoord;
/*     */   
/*     */   public NoiseGeneratorPerlin() {
/*  14 */     this(new Random());
/*     */   }
/*     */ 
/*     */   
/*     */   public NoiseGeneratorPerlin(Random par1Random) {
/*  19 */     this.permutations = new int[512];
/*  20 */     this.xCoord = par1Random.nextDouble() * 256.0D;
/*  21 */     this.yCoord = par1Random.nextDouble() * 256.0D;
/*  22 */     this.zCoord = par1Random.nextDouble() * 256.0D;
/*     */     
/*     */     int var2;
/*  25 */     for (var2 = 0; var2 < 256; this.permutations[var2] = var2++);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  30 */     for (var2 = 0; var2 < 256; var2++) {
/*     */       
/*  32 */       int var3 = par1Random.nextInt(256 - var2) + var2;
/*  33 */       int var4 = this.permutations[var2];
/*  34 */       this.permutations[var2] = this.permutations[var3];
/*  35 */       this.permutations[var3] = var4;
/*  36 */       this.permutations[var2 + 256] = this.permutations[var2];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final double lerp(double par1, double par3, double par5) {
/*  42 */     return par3 + par1 * (par5 - par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public final double func_76309_a(int par1, double par2, double par4) {
/*  47 */     int var6 = par1 & 0xF;
/*  48 */     double var7 = (1 - ((var6 & 0x8) >> 3)) * par2;
/*  49 */     double var9 = (var6 < 4) ? 0.0D : ((var6 != 12 && var6 != 14) ? par4 : par2);
/*  50 */     return (((var6 & 0x1) == 0) ? var7 : -var7) + (((var6 & 0x2) == 0) ? var9 : -var9);
/*     */   }
/*     */ 
/*     */   
/*     */   public final double grad(int par1, double par2, double par4, double par6) {
/*  55 */     int var8 = par1 & 0xF;
/*  56 */     double var9 = (var8 < 8) ? par2 : par4;
/*  57 */     double var11 = (var8 < 4) ? par4 : ((var8 != 12 && var8 != 14) ? par6 : par2);
/*  58 */     return (((var8 & 0x1) == 0) ? var9 : -var9) + (((var8 & 0x2) == 0) ? var11 : -var11);
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
/*     */   public void populateNoiseArray(double[] par1ArrayOfDouble, double par2, double par4, double par6, int par8, int par9, int par10, double par11, double par13, double par15, double par17) {
/*  79 */     if (par9 == 1) {
/*     */       
/*  81 */       boolean var66 = false;
/*  82 */       boolean var65 = false;
/*  83 */       boolean var21 = false;
/*  84 */       boolean var67 = false;
/*  85 */       double var72 = 0.0D;
/*  86 */       double var71 = 0.0D;
/*  87 */       int var77 = 0;
/*  88 */       double var74 = 1.0D / par17;
/*     */       
/*  90 */       for (int var30 = 0; var30 < par8; var30++) {
/*     */         
/*  92 */         double var31 = par2 + var30 * par11 + this.xCoord;
/*  93 */         int var78 = (int)var31;
/*     */         
/*  95 */         if (var31 < var78)
/*     */         {
/*  97 */           var78--;
/*     */         }
/*     */         
/* 100 */         int var34 = var78 & 0xFF;
/* 101 */         var31 -= var78;
/* 102 */         double var35 = var31 * var31 * var31 * (var31 * (var31 * 6.0D - 15.0D) + 10.0D);
/*     */         
/* 104 */         for (int var37 = 0; var37 < par10; var37++)
/*     */         {
/* 106 */           double var38 = par6 + var37 * par15 + this.zCoord;
/* 107 */           int var40 = (int)var38;
/*     */           
/* 109 */           if (var38 < var40)
/*     */           {
/* 111 */             var40--;
/*     */           }
/*     */           
/* 114 */           int var41 = var40 & 0xFF;
/* 115 */           var38 -= var40;
/* 116 */           double var42 = var38 * var38 * var38 * (var38 * (var38 * 6.0D - 15.0D) + 10.0D);
/* 117 */           int var19 = this.permutations[var34] + 0;
/* 118 */           int var64 = this.permutations[var19] + var41;
/* 119 */           int var69 = this.permutations[var34 + 1] + 0;
/* 120 */           int var22 = this.permutations[var69] + var41;
/* 121 */           var72 = lerp(var35, func_76309_a(this.permutations[var64], var31, var38), grad(this.permutations[var22], var31 - 1.0D, 0.0D, var38));
/* 122 */           var71 = lerp(var35, grad(this.permutations[var64 + 1], var31, 0.0D, var38 - 1.0D), grad(this.permutations[var22 + 1], var31 - 1.0D, 0.0D, var38 - 1.0D));
/* 123 */           double var79 = lerp(var42, var72, var71);
/* 124 */           int var10001 = var77++;
/* 125 */           par1ArrayOfDouble[var10001] = par1ArrayOfDouble[var10001] + var79 * var74;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 131 */       int var19 = 0;
/* 132 */       double var20 = 1.0D / par17;
/* 133 */       int var22 = -1;
/* 134 */       boolean var23 = false;
/* 135 */       boolean var24 = false;
/* 136 */       boolean var25 = false;
/* 137 */       boolean var26 = false;
/* 138 */       boolean var27 = false;
/* 139 */       boolean var28 = false;
/* 140 */       double var29 = 0.0D;
/* 141 */       double var31 = 0.0D;
/* 142 */       double var33 = 0.0D;
/* 143 */       double var35 = 0.0D;
/*     */       
/* 145 */       for (int var37 = 0; var37 < par8; var37++) {
/*     */         
/* 147 */         double var38 = par2 + var37 * par11 + this.xCoord;
/* 148 */         int var40 = (int)var38;
/*     */         
/* 150 */         if (var38 < var40)
/*     */         {
/* 152 */           var40--;
/*     */         }
/*     */         
/* 155 */         int var41 = var40 & 0xFF;
/* 156 */         var38 -= var40;
/* 157 */         double var42 = var38 * var38 * var38 * (var38 * (var38 * 6.0D - 15.0D) + 10.0D);
/*     */         
/* 159 */         for (int var44 = 0; var44 < par10; var44++) {
/*     */           
/* 161 */           double var45 = par6 + var44 * par15 + this.zCoord;
/* 162 */           int var47 = (int)var45;
/*     */           
/* 164 */           if (var45 < var47)
/*     */           {
/* 166 */             var47--;
/*     */           }
/*     */           
/* 169 */           int var48 = var47 & 0xFF;
/* 170 */           var45 -= var47;
/* 171 */           double var49 = var45 * var45 * var45 * (var45 * (var45 * 6.0D - 15.0D) + 10.0D);
/*     */           
/* 173 */           for (int var51 = 0; var51 < par9; var51++) {
/*     */             
/* 175 */             double var52 = par4 + var51 * par13 + this.yCoord;
/* 176 */             int var54 = (int)var52;
/*     */             
/* 178 */             if (var52 < var54)
/*     */             {
/* 180 */               var54--;
/*     */             }
/*     */             
/* 183 */             int var55 = var54 & 0xFF;
/* 184 */             var52 -= var54;
/* 185 */             double var56 = var52 * var52 * var52 * (var52 * (var52 * 6.0D - 15.0D) + 10.0D);
/*     */             
/* 187 */             if (var51 == 0 || var55 != var22) {
/*     */               
/* 189 */               var22 = var55;
/* 190 */               int var68 = this.permutations[var41] + var55;
/* 191 */               int var73 = this.permutations[var68] + var48;
/* 192 */               int var70 = this.permutations[var68 + 1] + var48;
/* 193 */               int var76 = this.permutations[var41 + 1] + var55;
/* 194 */               int var77 = this.permutations[var76] + var48;
/* 195 */               int var75 = this.permutations[var76 + 1] + var48;
/* 196 */               var29 = lerp(var42, grad(this.permutations[var73], var38, var52, var45), grad(this.permutations[var77], var38 - 1.0D, var52, var45));
/* 197 */               var31 = lerp(var42, grad(this.permutations[var70], var38, var52 - 1.0D, var45), grad(this.permutations[var75], var38 - 1.0D, var52 - 1.0D, var45));
/* 198 */               var33 = lerp(var42, grad(this.permutations[var73 + 1], var38, var52, var45 - 1.0D), grad(this.permutations[var77 + 1], var38 - 1.0D, var52, var45 - 1.0D));
/* 199 */               var35 = lerp(var42, grad(this.permutations[var70 + 1], var38, var52 - 1.0D, var45 - 1.0D), grad(this.permutations[var75 + 1], var38 - 1.0D, var52 - 1.0D, var45 - 1.0D));
/*     */             } 
/*     */             
/* 202 */             double var58 = lerp(var56, var29, var31);
/* 203 */             double var60 = lerp(var56, var33, var35);
/* 204 */             double var62 = lerp(var49, var58, var60);
/* 205 */             int var10001 = var19++;
/* 206 */             par1ArrayOfDouble[var10001] = par1ArrayOfDouble[var10001] + var62 * var20;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void xpopulateNoiseArray(double[] par1ArrayOfDouble, double par2, double par4, double par6, int par8, int par9, int par10, double par11, double par13, double par15, double par17) {
/* 227 */     if (par9 == 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 233 */       double var72 = 0.0D;
/* 234 */       double var71 = 0.0D;
/* 235 */       int var77 = 0;
/*     */       
/* 237 */       double var74 = 1.0D / par17;
/*     */       
/* 239 */       for (int var30 = 0; var30 < par8; var30++) {
/*     */         
/* 241 */         double var31 = par2 + var30 * par11 + this.xCoord;
/* 242 */         int var78 = (int)var31;
/*     */         
/* 244 */         if (var31 < var78)
/*     */         {
/* 246 */           var78--;
/*     */         }
/*     */         
/* 249 */         int var34 = var78 & 0xFF;
/* 250 */         var31 -= var78;
/* 251 */         double var35 = var31 * var31 * var31 * (var31 * (var31 * 6.0D - 15.0D) + 10.0D);
/*     */         
/* 253 */         for (int var37 = 0; var37 < par10; var37++)
/*     */         {
/* 255 */           double var38 = par6 + var37 * par15 + this.zCoord;
/* 256 */           int var40 = (int)var38;
/*     */           
/* 258 */           if (var38 < var40)
/*     */           {
/* 260 */             var40--;
/*     */           }
/*     */           
/* 263 */           int var41 = var40 & 0xFF;
/* 264 */           var38 -= var40;
/* 265 */           double var42 = var38 * var38 * var38 * (var38 * (var38 * 6.0D - 15.0D) + 10.0D);
/* 266 */           int var19 = this.permutations[var34] + 0;
/* 267 */           int var64 = this.permutations[var19] + var41;
/* 268 */           int var69 = this.permutations[var34 + 1] + 0;
/* 269 */           int var22 = this.permutations[var69] + var41;
/* 270 */           var72 = lerp(var35, func_76309_a(this.permutations[var64], var31, var38), grad(this.permutations[var22], var31 - 1.0D, 0.0D, var38));
/* 271 */           var71 = lerp(var35, grad(this.permutations[var64 + 1], var31, 0.0D, var38 - 1.0D), grad(this.permutations[var22 + 1], var31 - 1.0D, 0.0D, var38 - 1.0D));
/*     */ 
/*     */ 
/*     */           
/* 275 */           par1ArrayOfDouble[var77++] = par1ArrayOfDouble[var77++] + lerp(var42, var72, var71) * var74;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 281 */       int var19 = 0;
/*     */       
/* 283 */       double var20 = 1.0D / par17;
/* 284 */       int var22 = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 291 */       double var29 = 0.0D;
/* 292 */       double var31 = 0.0D;
/* 293 */       double var33 = 0.0D;
/* 294 */       double var35 = 0.0D;
/*     */       
/* 296 */       for (int var37 = 0; var37 < par8; var37++) {
/*     */         
/* 298 */         double var38 = par2 + var37 * par11 + this.xCoord;
/* 299 */         int var40 = (int)var38;
/*     */         
/* 301 */         if (var38 < var40)
/*     */         {
/* 303 */           var40--;
/*     */         }
/*     */         
/* 306 */         int var41 = var40 & 0xFF;
/* 307 */         var38 -= var40;
/* 308 */         double var42 = var38 * var38 * var38 * (var38 * (var38 * 6.0D - 15.0D) + 10.0D);
/*     */         
/* 310 */         double var38_minus_1 = var38 - 1.0D;
/*     */         
/* 312 */         for (int var44 = 0; var44 < par10; var44++) {
/*     */           
/* 314 */           double var45 = par6 + var44 * par15 + this.zCoord;
/* 315 */           int var47 = (int)var45;
/*     */           
/* 317 */           if (var45 < var47)
/*     */           {
/* 319 */             var47--;
/*     */           }
/*     */           
/* 322 */           int var48 = var47 & 0xFF;
/* 323 */           var45 -= var47;
/* 324 */           double var49 = var45 * var45 * var45 * (var45 * (var45 * 6.0D - 15.0D) + 10.0D);
/*     */           
/* 326 */           double var45_minus_1 = var45 - 1.0D;
/*     */           
/* 328 */           for (int var51 = 0; var51 < par9; var51++) {
/*     */             
/* 330 */             double var52 = par4 + var51 * par13 + this.yCoord;
/* 331 */             int var54 = (int)var52;
/*     */             
/* 333 */             if (var52 < var54)
/*     */             {
/* 335 */               var54--;
/*     */             }
/*     */             
/* 338 */             int var55 = var54 & 0xFF;
/* 339 */             var52 -= var54;
/* 340 */             double var56 = var52 * var52 * var52 * (var52 * (var52 * 6.0D - 15.0D) + 10.0D);
/*     */             
/* 342 */             double var52_minus_1 = var52 - 1.0D;
/*     */             
/* 344 */             if (var51 == 0 || var55 != var22) {
/*     */               
/* 346 */               var22 = var55;
/* 347 */               int var68 = this.permutations[var41] + var55;
/* 348 */               int var73 = this.permutations[var68] + var48;
/* 349 */               int var70 = this.permutations[var68 + 1] + var48;
/* 350 */               int var76 = this.permutations[var41 + 1] + var55;
/* 351 */               int var77 = this.permutations[var76] + var48;
/* 352 */               int var75 = this.permutations[var76 + 1] + var48;
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 357 */               var29 = lerp(var42, grad(this.permutations[var73], var38, var52, var45), grad(this.permutations[var77], var38_minus_1, var52, var45));
/* 358 */               var31 = lerp(var42, grad(this.permutations[var70], var38, var52_minus_1, var45), grad(this.permutations[var75], var38_minus_1, var52_minus_1, var45));
/* 359 */               var33 = lerp(var42, grad(this.permutations[var73 + 1], var38, var52, var45_minus_1), grad(this.permutations[var77 + 1], var38_minus_1, var52, var45_minus_1));
/* 360 */               var35 = lerp(var42, grad(this.permutations[var70 + 1], var38, var52_minus_1, var45_minus_1), grad(this.permutations[var75 + 1], var38_minus_1, var52_minus_1, var45_minus_1));
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 368 */             par1ArrayOfDouble[var19++] = par1ArrayOfDouble[var19++] + lerp(var49, lerp(var56, var29, var31), lerp(var56, var33, var35)) * var20;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NoiseGeneratorPerlin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */