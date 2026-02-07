/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapGenCavesHell
/*     */   extends MapGenBase
/*     */ {
/*     */   protected void generateLargeCaveNode(long l, int i, int j, byte[] bs, double d, double e, double f) {
/*  12 */     generateCaveNode(l, i, j, bs, d, e, f, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
/*     */   }
/*     */   
/*     */   protected void generateCaveNode(long l, int i, int j, byte[] bs, double d, double e, double f, float g, float h, float k, int m, int n, double o) {
/*  16 */     double d1 = (i * 16 + 8);
/*  17 */     double d2 = (j * 16 + 8);
/*     */     
/*  19 */     float f1 = 0.0F;
/*  20 */     float f2 = 0.0F;
/*  21 */     Random random = new Random(l);
/*     */     
/*  23 */     if (n <= 0) {
/*  24 */       int i2 = this.range * 16 - 16;
/*  25 */       n = i2 - random.nextInt(i2 / 4);
/*     */     } 
/*  27 */     boolean bool1 = false;
/*     */     
/*  29 */     if (m == -1) {
/*  30 */       m = n / 2;
/*  31 */       bool1 = true;
/*     */     } 
/*     */     
/*  34 */     int i1 = random.nextInt(n / 2) + n / 4;
/*  35 */     boolean bool2 = (random.nextInt(6) == 0) ? true : false;
/*     */     
/*  37 */     for (; m < n; m++) {
/*  38 */       double d3 = 1.5D + (MathHelper.sin(m * 3.1415927F / n) * g * 1.0F);
/*  39 */       double d4 = d3 * o;
/*     */       
/*  41 */       float f3 = MathHelper.cos(k);
/*  42 */       float f4 = MathHelper.sin(k);
/*  43 */       d += (MathHelper.cos(h) * f3);
/*  44 */       e += f4;
/*  45 */       f += (MathHelper.sin(h) * f3);
/*     */       
/*  47 */       if (bool2) {
/*  48 */         k *= 0.92F;
/*     */       } else {
/*  50 */         k *= 0.7F;
/*     */       } 
/*  52 */       k += f2 * 0.1F;
/*  53 */       h += f1 * 0.1F;
/*     */       
/*  55 */       f2 *= 0.9F;
/*  56 */       f1 *= 0.75F;
/*  57 */       f2 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
/*  58 */       f1 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
/*     */       
/*  60 */       if (!bool1 && m == i1 && g > 1.0F) {
/*  61 */         generateCaveNode(random.nextLong(), i, j, bs, d, e, f, random.nextFloat() * 0.5F + 0.5F, h - 1.5707964F, k / 3.0F, m, n, 1.0D);
/*  62 */         generateCaveNode(random.nextLong(), i, j, bs, d, e, f, random.nextFloat() * 0.5F + 0.5F, h + 1.5707964F, k / 3.0F, m, n, 1.0D);
/*     */         return;
/*     */       } 
/*  65 */       if (bool1 || random.nextInt(4) != 0) {
/*     */ 
/*     */         
/*  68 */         double d5 = d - d1;
/*  69 */         double d6 = f - d2;
/*  70 */         double d7 = (n - m);
/*  71 */         double d8 = (g + 2.0F + 16.0F);
/*  72 */         if (d5 * d5 + d6 * d6 - d7 * d7 > d8 * d8) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  77 */         if (d >= d1 - 16.0D - d3 * 2.0D && f >= d2 - 16.0D - d3 * 2.0D && d <= d1 + 16.0D + d3 * 2.0D && f <= d2 + 16.0D + d3 * 2.0D) {
/*     */ 
/*     */           
/*  80 */           int i2 = MathHelper.floor_double(d - d3) - i * 16 - 1;
/*  81 */           int i3 = MathHelper.floor_double(d + d3) - i * 16 + 1;
/*     */           
/*  83 */           int i4 = MathHelper.floor_double(e - d4) - 1;
/*  84 */           int i5 = MathHelper.floor_double(e + d4) + 1;
/*     */           
/*  86 */           int i6 = MathHelper.floor_double(f - d3) - j * 16 - 1;
/*  87 */           int i7 = MathHelper.floor_double(f + d3) - j * 16 + 1;
/*     */           
/*  89 */           if (i2 < 0) i2 = 0; 
/*  90 */           if (i3 > 16) i3 = 16;
/*     */           
/*  92 */           if (i4 < 1) i4 = 1; 
/*  93 */           if (i5 > 120) i5 = 120;
/*     */           
/*  95 */           if (i6 < 0) i6 = 0; 
/*  96 */           if (i7 > 16) i7 = 16;
/*     */           
/*  98 */           boolean bool = false; int i8;
/*  99 */           for (i8 = i2; !bool && i8 < i3; i8++) {
/* 100 */             for (int i9 = i6; !bool && i9 < i7; i9++) {
/* 101 */               for (int i10 = i5 + 1; !bool && i10 >= i4 - 1; i10--) {
/* 102 */                 int i11 = (i8 * 16 + i9) * 128 + i10;
/* 103 */                 if (i10 >= 0 && i10 < 128) {
/* 104 */                   if (bs[i11] == Block.lavaMoving.blockID || bs[i11] == Block.lavaStill.blockID) {
/* 105 */                     bool = true;
/*     */                   }
/* 107 */                   if (i10 != i4 - 1 && i8 != i2 && i8 != i3 - 1 && i9 != i6 && i9 != i7 - 1)
/* 108 */                     i10 = i4; 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/* 113 */           if (!bool) {
/*     */             
/* 115 */             for (i8 = i2; i8 < i3; i8++) {
/* 116 */               double d9 = ((i8 + i * 16) + 0.5D - d) / d3;
/* 117 */               for (int i9 = i6; i9 < i7; i9++) {
/* 118 */                 double d10 = ((i9 + j * 16) + 0.5D - f) / d3;
/* 119 */                 int i10 = (i8 * 16 + i9) * 128 + i5;
/* 120 */                 for (int i11 = i5 - 1; i11 >= i4; i11--) {
/* 121 */                   double d11 = (i11 + 0.5D - e) / d4;
/* 122 */                   if (d11 > -0.7D && d9 * d9 + d11 * d11 + d10 * d10 < 1.0D) {
/* 123 */                     byte b = bs[i10];
/* 124 */                     if (b == Block.netherrack.blockID || b == Block.dirt.blockID || b == Block.grass.blockID) {
/* 125 */                       bs[i10] = 0;
/*     */                     }
/*     */                   } 
/* 128 */                   i10--;
/*     */                 } 
/*     */               } 
/*     */             } 
/* 132 */             if (bool1)
/*     */               break; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }  } protected void recursiveGenerate(World world, int i, int j, int k, int l, byte[] bs) {
/* 138 */     int m = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(10) + 1) + 1);
/* 139 */     if (this.rand.nextInt(5) != 0) m = 0;
/*     */     
/* 141 */     for (byte b = 0; b < m; b++) {
/* 142 */       double d1 = (i * 16 + this.rand.nextInt(16));
/* 143 */       double d2 = this.rand.nextInt(128);
/* 144 */       double d3 = (j * 16 + this.rand.nextInt(16));
/*     */       
/* 146 */       int n = 1;
/* 147 */       if (this.rand.nextInt(4) == 0) {
/* 148 */         generateLargeCaveNode(this.rand.nextLong(), k, l, bs, d1, d2, d3);
/* 149 */         n += this.rand.nextInt(4);
/*     */       } 
/*     */       
/* 152 */       for (byte b1 = 0; b1 < n; b1++) {
/*     */         
/* 154 */         float f1 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 155 */         float f2 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 156 */         float f3 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
/*     */         
/* 158 */         generateCaveNode(this.rand.nextLong(), k, l, bs, d1, d2, d3, f3 * 2.0F, f1, f2, 0, 0, 0.5D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenCavesHell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */