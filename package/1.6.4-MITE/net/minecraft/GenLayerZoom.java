/*     */ package net.minecraft;
/*     */ 
/*     */ public class GenLayerZoom
/*     */   extends GenLayer
/*     */ {
/*     */   public GenLayerZoom(long par1, GenLayer par3GenLayer) {
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
/*     */   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
/*  61 */     int var5 = par1 >> 1;
/*  62 */     int var6 = par2 >> 1;
/*  63 */     int var7 = (par3 >> 1) + 3;
/*  64 */     int var8 = (par4 >> 1) + 3;
/*     */     
/*  66 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8, z);
/*  67 */     int[] var10 = IntCache.getIntCache(var7 * 2 * var8 * 2);
/*  68 */     int var11 = var7 << 1;
/*     */ 
/*     */     
/*  71 */     for (int var12 = 0; var12 < var8 - 1; var12++) {
/*     */       
/*  73 */       int i = var12 << 1;
/*  74 */       int var14 = i * var11;
/*  75 */       int var15 = var9[0 + (var12 + 0) * var7];
/*  76 */       int var16 = var9[0 + (var12 + 1) * var7];
/*     */       
/*  78 */       for (int var17 = 0; var17 < var7 - 1; var17++) {
/*     */         
/*  80 */         long par1_1 = (var17 + var5 << 1);
/*  81 */         long par3_1 = (var12 + var6 << 1);
/*  82 */         this.chunkSeed = this.worldGenSeed;
/*  83 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/*  84 */         this.chunkSeed += par1_1;
/*  85 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/*  86 */         this.chunkSeed += par3_1;
/*  87 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/*  88 */         this.chunkSeed += par1_1;
/*  89 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/*  90 */         this.chunkSeed += par3_1;
/*     */         
/*  92 */         int var18 = var9[var17 + 1 + (var12 + 0) * var7];
/*  93 */         int var19 = var9[var17 + 1 + (var12 + 1) * var7];
/*  94 */         var10[var14] = var15;
/*     */         
/*  96 */         var10[var14++ + var11] = ((this.chunkSeed >> 24L & 0x1L) == 0L) ? var15 : var16;
/*     */         
/*  98 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/*  99 */         this.chunkSeed += this.worldGenSeed;
/*     */         
/* 101 */         var10[var14] = ((this.chunkSeed >> 24L & 0x1L) == 0L) ? var15 : var18;
/*     */         
/* 103 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 104 */         this.chunkSeed += this.worldGenSeed;
/*     */         
/* 106 */         var10[var14++ + var11] = modeOrRandom(var15, var18, var16, var19);
/* 107 */         var15 = var18;
/* 108 */         var16 = var19;
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     int[] var20 = IntCache.getIntCache(par3 * par4);
/*     */     
/* 114 */     for (int var13 = 0; var13 < par4; var13++)
/*     */     {
/* 116 */       System.arraycopy(var10, (var13 + (par2 & 0x1)) * (var7 << 1) + (par1 & 0x1), var20, var13 * par3, par3);
/*     */     }
/*     */     
/* 119 */     return var20;
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
/*     */   protected int modeOrRandom(int par1, int par2, int par3, int par4) {
/* 135 */     if (par2 == par3 && par3 == par4)
/*     */     {
/* 137 */       return par2;
/*     */     }
/* 139 */     if (par1 == par2 && par1 == par3)
/*     */     {
/* 141 */       return par1;
/*     */     }
/* 143 */     if (par1 == par2 && par1 == par4)
/*     */     {
/* 145 */       return par1;
/*     */     }
/* 147 */     if (par1 == par3 && par1 == par4)
/*     */     {
/* 149 */       return par1;
/*     */     }
/* 151 */     if (par1 == par2 && par3 != par4)
/*     */     {
/* 153 */       return par1;
/*     */     }
/* 155 */     if (par1 == par3 && par2 != par4)
/*     */     {
/* 157 */       return par1;
/*     */     }
/* 159 */     if (par1 == par4 && par2 != par3)
/*     */     {
/* 161 */       return par1;
/*     */     }
/* 163 */     if (par2 == par1 && par3 != par4)
/*     */     {
/* 165 */       return par2;
/*     */     }
/* 167 */     if (par2 == par3 && par1 != par4)
/*     */     {
/* 169 */       return par2;
/*     */     }
/* 171 */     if (par2 == par4 && par1 != par3)
/*     */     {
/* 173 */       return par2;
/*     */     }
/* 175 */     if (par3 == par1 && par2 != par4)
/*     */     {
/* 177 */       return par3;
/*     */     }
/* 179 */     if (par3 == par2 && par1 != par4)
/*     */     {
/* 181 */       return par3;
/*     */     }
/* 183 */     if (par3 == par4 && par1 != par2)
/*     */     {
/* 185 */       return par3;
/*     */     }
/* 187 */     if (par4 == par1 && par2 != par3)
/*     */     {
/* 189 */       return par3;
/*     */     }
/* 191 */     if (par4 == par2 && par1 != par3)
/*     */     {
/* 193 */       return par3;
/*     */     }
/* 195 */     if (par4 == par3 && par1 != par2)
/*     */     {
/* 197 */       return par3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     int var5 = (int)(this.chunkSeed >> 24L & 0x3L);
/*     */     
/* 208 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 209 */     this.chunkSeed += this.worldGenSeed;
/*     */     
/* 211 */     return (var5 == 0) ? par1 : ((var5 == 1) ? par2 : ((var5 == 2) ? par3 : par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static GenLayer magnify(long par0, GenLayer par2GenLayer, int par3) {
/* 222 */     Object var4 = par2GenLayer;
/*     */     
/* 224 */     for (int var5 = 0; var5 < par3; var5++)
/*     */     {
/* 226 */       var4 = new GenLayerZoom(par0 + var5, (GenLayer)var4);
/*     */     }
/*     */     
/* 229 */     return (GenLayer)var4;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerZoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */