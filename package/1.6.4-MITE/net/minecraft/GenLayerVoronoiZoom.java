/*     */ package net.minecraft;
/*     */ 
/*     */ public class GenLayerVoronoiZoom
/*     */   extends GenLayer
/*     */ {
/*     */   public GenLayerVoronoiZoom(long par1, GenLayer par3GenLayer) {
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
/* 101 */     par1 -= 2;
/* 102 */     par2 -= 2;
/* 103 */     byte var5 = 2;
/* 104 */     int var6 = 1 << var5;
/* 105 */     int var7 = par1 >> var5;
/* 106 */     int var8 = par2 >> var5;
/* 107 */     int var9 = (par3 >> var5) + 3;
/* 108 */     int var10 = (par4 >> var5) + 3;
/*     */     
/* 110 */     int[] var11 = this.parent.getInts(var7, var8, var9, var10, z);
/* 111 */     int var12 = var9 << var5;
/* 112 */     int var13 = var10 << var5;
/* 113 */     int[] var14 = IntCache.getIntCache(var12 * var13);
/*     */ 
/*     */     
/* 116 */     for (int var15 = 0; var15 < var10 - 1; var15++) {
/*     */       
/* 118 */       int i = var11[0 + (var15 + 0) * var9];
/* 119 */       int var17 = var11[0 + (var15 + 1) * var9];
/*     */       
/* 121 */       for (int var18 = 0; var18 < var9 - 1; var18++) {
/*     */         
/* 123 */         double var19 = var6 * 0.9D;
/*     */ 
/*     */ 
/*     */         
/* 127 */         long par1_1 = (var18 + var7 << var5);
/* 128 */         long par3_1 = (var15 + var8 << var5);
/* 129 */         this.chunkSeed = this.worldGenSeed;
/* 130 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 131 */         this.chunkSeed += par1_1;
/* 132 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 133 */         this.chunkSeed += par3_1;
/* 134 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 135 */         this.chunkSeed += par1_1;
/* 136 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 137 */         this.chunkSeed += par3_1;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 142 */         double var21 = ((this.chunkSeed >> 24L & 0x3FFL) / 1024.0D - 0.5D) * var19;
/* 143 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 144 */         this.chunkSeed += this.worldGenSeed;
/*     */         
/* 146 */         double var23 = ((this.chunkSeed >> 24L & 0x3FFL) / 1024.0D - 0.5D) * var19;
/* 147 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 148 */         this.chunkSeed += this.worldGenSeed;
/*     */ 
/*     */ 
/*     */         
/* 152 */         par1_1 = (var18 + var7 + 1 << var5);
/* 153 */         par3_1 = (var15 + var8 << var5);
/* 154 */         this.chunkSeed = this.worldGenSeed;
/* 155 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 156 */         this.chunkSeed += par1_1;
/* 157 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 158 */         this.chunkSeed += par3_1;
/* 159 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 160 */         this.chunkSeed += par1_1;
/* 161 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 162 */         this.chunkSeed += par3_1;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 167 */         double var25 = ((this.chunkSeed >> 24L & 0x3FFL) / 1024.0D - 0.5D) * var19 + var6;
/* 168 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 169 */         this.chunkSeed += this.worldGenSeed;
/*     */         
/* 171 */         double var27 = ((this.chunkSeed >> 24L & 0x3FFL) / 1024.0D - 0.5D) * var19;
/* 172 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 173 */         this.chunkSeed += this.worldGenSeed;
/*     */ 
/*     */ 
/*     */         
/* 177 */         par1_1 = (var18 + var7 << var5);
/* 178 */         par3_1 = (var15 + var8 + 1 << var5);
/* 179 */         this.chunkSeed = this.worldGenSeed;
/* 180 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 181 */         this.chunkSeed += par1_1;
/* 182 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 183 */         this.chunkSeed += par3_1;
/* 184 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 185 */         this.chunkSeed += par1_1;
/* 186 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 187 */         this.chunkSeed += par3_1;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 192 */         double var29 = ((this.chunkSeed >> 24L & 0x3FFL) / 1024.0D - 0.5D) * var19;
/* 193 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 194 */         this.chunkSeed += this.worldGenSeed;
/*     */         
/* 196 */         double var31 = ((this.chunkSeed >> 24L & 0x3FFL) / 1024.0D - 0.5D) * var19 + var6;
/* 197 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 198 */         this.chunkSeed += this.worldGenSeed;
/*     */ 
/*     */ 
/*     */         
/* 202 */         par1_1 = (var18 + var7 + 1 << var5);
/* 203 */         par3_1 = (var15 + var8 + 1 << var5);
/* 204 */         this.chunkSeed = this.worldGenSeed;
/* 205 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 206 */         this.chunkSeed += par1_1;
/* 207 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 208 */         this.chunkSeed += par3_1;
/* 209 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 210 */         this.chunkSeed += par1_1;
/* 211 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 212 */         this.chunkSeed += par3_1;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 217 */         double var33 = ((this.chunkSeed >> 24L & 0x3FFL) / 1024.0D - 0.5D) * var19 + var6;
/* 218 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 219 */         this.chunkSeed += this.worldGenSeed;
/*     */         
/* 221 */         double var35 = ((this.chunkSeed >> 24L & 0x3FFL) / 1024.0D - 0.5D) * var19 + var6;
/* 222 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 223 */         this.chunkSeed += this.worldGenSeed;
/*     */         
/* 225 */         int var37 = var11[var18 + 1 + (var15 + 0) * var9];
/* 226 */         int var38 = var11[var18 + 1 + (var15 + 1) * var9];
/*     */         
/* 228 */         for (int var39 = 0; var39 < var6; var39++) {
/*     */           
/* 230 */           int var40 = ((var15 << var5) + var39) * var12 + (var18 << var5);
/*     */           
/* 232 */           for (int var41 = 0; var41 < var6; var41++) {
/*     */             
/* 234 */             double var42 = (var39 - var23) * (var39 - var23) + (var41 - var21) * (var41 - var21);
/* 235 */             double var44 = (var39 - var27) * (var39 - var27) + (var41 - var25) * (var41 - var25);
/* 236 */             double var46 = (var39 - var31) * (var39 - var31) + (var41 - var29) * (var41 - var29);
/* 237 */             double var48 = (var39 - var35) * (var39 - var35) + (var41 - var33) * (var41 - var33);
/*     */             
/* 239 */             if (var42 < var44 && var42 < var46 && var42 < var48) {
/*     */               
/* 241 */               var14[var40++] = i;
/*     */             }
/* 243 */             else if (var44 < var42 && var44 < var46 && var44 < var48) {
/*     */               
/* 245 */               var14[var40++] = var37;
/*     */             }
/* 247 */             else if (var46 < var42 && var46 < var44 && var46 < var48) {
/*     */               
/* 249 */               var14[var40++] = var17;
/*     */             }
/*     */             else {
/*     */               
/* 253 */               var14[var40++] = var38;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 258 */         i = var37;
/* 259 */         var17 = var38;
/*     */       } 
/*     */     } 
/*     */     
/* 263 */     int[] var50 = IntCache.getIntCache(par3 * par4);
/*     */     
/* 265 */     for (int var16 = 0; var16 < par4; var16++)
/*     */     {
/* 267 */       System.arraycopy(var14, (var16 + (par2 & var6 - 1)) * (var9 << var5) + (par1 & var6 - 1), var50, var16 * par3, par3);
/*     */     }
/*     */     
/* 270 */     return var50;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerVoronoiZoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */