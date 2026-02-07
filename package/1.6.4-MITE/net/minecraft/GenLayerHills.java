/*     */ package net.minecraft;
/*     */ 
/*     */ public class GenLayerHills
/*     */   extends GenLayer
/*     */ {
/*     */   public GenLayerHills(long par1, GenLayer par3GenLayer) {
/*   7 */     super(par1);
/*   8 */     this.parent = par3GenLayer;
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
/*     */   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
/*  90 */     int[] var5 = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2, z);
/*  91 */     int[] var6 = IntCache.getIntCache(par3 * par4);
/*     */     
/*  93 */     int par3_plus_2 = par3 + 2;
/*     */     
/*  95 */     for (int var7 = 0; var7 < par4; var7++) {
/*     */       
/*  97 */       for (int var8 = 0; var8 < par3; var8++) {
/*     */         
/*  99 */         long par1_1 = (var8 + par1);
/* 100 */         long par3_1 = (var7 + par2);
/* 101 */         this.chunkSeed = this.worldGenSeed;
/* 102 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 103 */         this.chunkSeed += par1_1;
/* 104 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 105 */         this.chunkSeed += par3_1;
/* 106 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 107 */         this.chunkSeed += par1_1;
/* 108 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 109 */         this.chunkSeed += par3_1;
/*     */         
/* 111 */         int var9 = var5[var8 + 1 + (var7 + 1) * par3_plus_2];
/*     */         
/* 113 */         if ((this.chunkSeed >> 24L) % 3L == 0L) {
/*     */           
/* 115 */           int var10 = var9;
/*     */           
/* 117 */           if (var9 == BiomeGenBase.desert.biomeID) {
/*     */             
/* 119 */             var10 = BiomeGenBase.desertHills.biomeID;
/*     */           }
/* 121 */           else if (var9 == BiomeGenBase.forest.biomeID) {
/*     */             
/* 123 */             var10 = BiomeGenBase.forestHills.biomeID;
/*     */           }
/* 125 */           else if (var9 == BiomeGenBase.taiga.biomeID) {
/*     */             
/* 127 */             var10 = BiomeGenBase.taigaHills.biomeID;
/*     */           }
/* 129 */           else if (var9 == BiomeGenBase.plains.biomeID) {
/*     */             
/* 131 */             var10 = BiomeGenBase.forest.biomeID;
/*     */           }
/* 133 */           else if (var9 == BiomeGenBase.icePlains.biomeID) {
/*     */             
/* 135 */             var10 = BiomeGenBase.iceMountains.biomeID;
/*     */           }
/* 137 */           else if (var9 == BiomeGenBase.jungle.biomeID) {
/*     */             
/* 139 */             var10 = BiomeGenBase.jungleHills.biomeID;
/*     */           } 
/*     */           
/* 142 */           if (var10 == var9) {
/*     */             
/* 144 */             var6[var8 + var7 * par3] = var9;
/*     */           }
/*     */           else {
/*     */             
/* 148 */             int var11 = var5[var8 + 1 + (var7 + 1 - 1) * par3_plus_2];
/* 149 */             int var12 = var5[var8 + 1 + 1 + (var7 + 1) * par3_plus_2];
/* 150 */             int var13 = var5[var8 + 1 - 1 + (var7 + 1) * par3_plus_2];
/* 151 */             int var14 = var5[var8 + 1 + (var7 + 1 + 1) * par3_plus_2];
/*     */             
/* 153 */             if (var11 == var9 && var12 == var9 && var13 == var9 && var14 == var9)
/*     */             {
/* 155 */               var6[var8 + var7 * par3] = var10;
/*     */             }
/*     */             else
/*     */             {
/* 159 */               var6[var8 + var7 * par3] = var9;
/*     */             }
/*     */           
/*     */           } 
/*     */         } else {
/*     */           
/* 165 */           var6[var8 + var7 * par3] = var9;
/*     */         } 
/*     */         
/* 168 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 169 */         this.chunkSeed += this.worldGenSeed;
/*     */       } 
/*     */     } 
/*     */     
/* 173 */     return var6;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerHills.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */