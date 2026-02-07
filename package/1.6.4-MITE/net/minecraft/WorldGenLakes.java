/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class WorldGenLakes
/*     */   extends WorldGenerator
/*     */ {
/*     */   private int blockIndex;
/*     */   
/*     */   public WorldGenLakes(int par1) {
/*  11 */     this.blockIndex = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  16 */     par3 -= 8;
/*     */     
/*  18 */     for (par5 -= 8; par4 > 5 && par1World.isAirBlock(par3, par4, par5); par4--);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  23 */     if (par4 <= 4)
/*     */     {
/*  25 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  29 */     par4 -= 4;
/*  30 */     boolean[] var6 = new boolean[2048];
/*  31 */     int var7 = par2Random.nextInt(4) + 4;
/*     */     
/*     */     int var8;
/*  34 */     for (var8 = 0; var8 < var7; var8++) {
/*     */       
/*  36 */       double var9 = par2Random.nextDouble() * 6.0D + 3.0D;
/*  37 */       double var11 = par2Random.nextDouble() * 4.0D + 2.0D;
/*  38 */       double var13 = par2Random.nextDouble() * 6.0D + 3.0D;
/*  39 */       double var15 = par2Random.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
/*  40 */       double var17 = par2Random.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
/*  41 */       double var19 = par2Random.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;
/*     */       
/*  43 */       for (int var21 = 1; var21 < 15; var21++) {
/*     */         
/*  45 */         for (int var22 = 1; var22 < 15; var22++) {
/*     */           
/*  47 */           for (int var23 = 1; var23 < 7; var23++) {
/*     */             
/*  49 */             double var24 = (var21 - var15) / var9 / 2.0D;
/*  50 */             double var26 = (var23 - var17) / var11 / 2.0D;
/*  51 */             double var28 = (var22 - var19) / var13 / 2.0D;
/*  52 */             double var30 = var24 * var24 + var26 * var26 + var28 * var28;
/*     */             
/*  54 */             if (var30 < 1.0D)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*  61 */               var6[(var21 * 16 + var22) * 8 + var23] = true;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     for (var8 = 0; var8 < 16; var8++) {
/*     */       
/*  74 */       for (int var32 = 0; var32 < 16; var32++) {
/*     */         
/*  76 */         for (int var10 = 0; var10 < 8; var10++) {
/*     */           
/*  78 */           boolean var33 = (!var6[(var8 * 16 + var32) * 8 + var10] && ((var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10]) || (var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10]) || (var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10]) || (var32 > 0 && var6[(var8 * 16 + var32 - 1) * 8 + var10]) || (var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1]) || (var10 > 0 && var6[(var8 * 16 + var32) * 8 + var10 - 1])));
/*     */           
/*  80 */           if (var33) {
/*     */             
/*  82 */             Material var12 = par1World.getBlockMaterial(par3 + var8, par4 + var10, par5 + var32);
/*     */             
/*  84 */             if (var10 >= 4 && var12.isLiquid())
/*     */             {
/*  86 */               return false;
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  95 */             if (var10 < 4 && !var12.isSolid() && par1World.getBlockId(par3 + var8, par4 + var10, par5 + var32) != this.blockIndex)
/*     */             {
/*  97 */               return false;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 106 */     for (var8 = 0; var8 < 16; var8++) {
/*     */       
/* 108 */       int var8_times_16 = var8 * 16;
/*     */       
/* 110 */       for (int var32 = 0; var32 < 16; var32++) {
/*     */         
/* 112 */         int var8_times_128_plus_var32_times_8 = (var8_times_16 + var32) * 8;
/*     */         
/* 114 */         for (int var10 = 0; var10 < 8; var10++) {
/*     */           
/* 116 */           if (var6[var8_times_128_plus_var32_times_8 + var10])
/*     */           {
/* 118 */             if (var10 < 3) {
/*     */               
/* 120 */               Block block = par1World.getBlock(par3 + var8, par4 + var10 + 1, par5 + var32);
/*     */               
/* 122 */               if (block == Block.wood) {
/* 123 */                 return false;
/*     */               }
/* 125 */             } else if (var10 >= 4) {
/*     */               
/* 127 */               Block block = par1World.getBlock(par3 + var8, par4 + var10, par5 + var32);
/*     */               
/* 129 */               if (block == Block.wood || block == Block.leaves || block == Block.planks)
/*     */               {
/* 131 */                 var6[(var8 * 16 + var32) * 8 + var10] = false;
/*     */               
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 137 */               Block block = par1World.getBlock(par3 + var8, par4 + var10 + 1, par5 + var32);
/*     */               
/* 139 */               if (block == Block.wood)
/*     */               {
/* 141 */                 var6[(var8 * 16 + var32) * 8 + var10] = false;
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 150 */     Block lake_block = Block.getBlock(this.blockIndex);
/*     */ 
/*     */ 
/*     */     
/* 154 */     for (var8 = 0; var8 < 16; var8++) {
/*     */       
/* 156 */       for (int var32 = 0; var32 < 16; var32++) {
/*     */         
/* 158 */         for (int var10 = 0; var10 < 8; var10++) {
/*     */           
/* 160 */           if (var6[(var8 * 16 + var32) * 8 + var10]) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 167 */             int block_id, x = par3 + var8;
/* 168 */             int y = par4 + var10;
/* 169 */             int z = par5 + var32;
/*     */ 
/*     */ 
/*     */             
/* 173 */             if (var10 >= 4) {
/* 174 */               block_id = 0;
/*     */             }
/*     */             else {
/*     */               
/* 178 */               block_id = this.blockIndex;
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 183 */             par1World.setBlock(x, y, z, block_id, 0, 2);
/*     */ 
/*     */ 
/*     */             
/* 187 */             Block block_above = par1World.getBlock(par3 + var8, par4 + var10 + 1, par5 + var32);
/*     */             
/* 189 */             if (block_above != null && !block_above.isLegalOn(par1World.getBlockMetadata(par3 + var8, par4 + var10 + 1, par5 + var32), lake_block, 0)) {
/*     */               
/* 191 */               par1World.setBlock(par3 + var8, par4 + var10 + 1, par5 + var32, 0, 0, 2);
/*     */ 
/*     */ 
/*     */               
/* 195 */               int y_above = par4 + var10 + 1;
/*     */               
/* 197 */               while (++y_above < 256) {
/*     */                 
/* 199 */                 block_above = par1World.getBlock(x, y_above, z);
/*     */                 
/* 201 */                 if (block_above == null || block_above.isLegalOn(par1World.getBlockMetadata(x, y_above, z), null, 0)) {
/*     */                   break;
/*     */                 }
/* 204 */                 par1World.setBlock(x, y_above, z, 0, 0, 2);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     for (var8 = 0; var8 < 16; var8++) {
/*     */       
/* 218 */       for (int var32 = 0; var32 < 16; var32++) {
/*     */         
/* 220 */         for (int var10 = 4; var10 < 8; var10++) {
/*     */           
/* 222 */           if (var6[(var8 * 16 + var32) * 8 + var10] && par1World.getBlockId(par3 + var8, par4 + var10 - 1, par5 + var32) == Block.dirt.blockID && par1World.getSavedLightValue(EnumSkyBlock.Sky, par3 + var8, par4 + var10, par5 + var32) > 0) {
/*     */             
/* 224 */             BiomeGenBase var35 = par1World.getBiomeGenForCoords(par3 + var8, par5 + var32);
/*     */             
/* 226 */             if (var35.topBlock == Block.mycelium.blockID) {
/*     */               
/* 228 */               par1World.setBlock(par3 + var8, par4 + var10 - 1, par5 + var32, Block.mycelium.blockID, 0, 2);
/*     */             }
/*     */             else {
/*     */               
/* 232 */               par1World.setBlock(par3 + var8, par4 + var10 - 1, par5 + var32, Block.grass.blockID, 0, 2);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 239 */     if ((Block.blocksList[this.blockIndex]).blockMaterial == Material.lava)
/*     */     {
/* 241 */       for (var8 = 0; var8 < 16; var8++) {
/*     */         
/* 243 */         for (int var32 = 0; var32 < 16; var32++) {
/*     */           
/* 245 */           for (int var10 = 0; var10 < 8; var10++) {
/*     */             
/* 247 */             boolean var33 = (!var6[(var8 * 16 + var32) * 8 + var10] && ((var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10]) || (var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10]) || (var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10]) || (var32 > 0 && var6[(var8 * 16 + var32 - 1) * 8 + var10]) || (var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1]) || (var10 > 0 && var6[(var8 * 16 + var32) * 8 + var10 - 1])));
/*     */             
/* 249 */             if (var33 && (var10 < 4 || par2Random.nextInt(2) != 0) && par1World.getBlockMaterial(par3 + var8, par4 + var10, par5 + var32).isSolid())
/*     */             {
/* 251 */               par1World.setBlock(par3 + var8, par4 + var10, par5 + var32, Block.stone.blockID, 0, 2);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
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
/* 274 */     for (var8 = 0; var8 < 16; var8++) {
/*     */       
/* 276 */       for (int var32 = 0; var32 < 16; var32++) {
/*     */         
/* 278 */         int x = par3 + var8;
/* 279 */         int z = par5 + var32;
/*     */         
/* 281 */         if (par1World.getBiomeGenForCoords(x, z).isFreezing()) {
/*     */           
/* 283 */           int y = par1World.getPrecipitationHeight(x, z) - 1;
/*     */           
/* 285 */           Block topmost_block = par1World.getBlock(x, y, z);
/*     */           
/* 287 */           if (topmost_block == Block.waterStill) {
/* 288 */             par1World.setBlock(x, y, z, Block.ice.blockID, 0, 2);
/* 289 */           } else if (topmost_block != Block.ice && par1World.isBlockTopFlatAndSolid(x, y, z) && par1World.isAirBlock(x, y + 1, z)) {
/* 290 */             par1World.setBlock(x, y + 1, z, Block.snow.blockID, 0, 2);
/*     */           } 
/* 292 */           for (int i = 7; i >= 0; i--) {
/*     */             
/* 294 */             y = par4 + i;
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
/* 306 */             if (par1World.getBlock(x, y, z) == Block.waterStill && par1World.isAirBlock(x, y + 1, z)) {
/* 307 */               par1World.setBlock(x, y, z, Block.ice.blockID, 0, 2);
/*     */             }
/*     */           } 
/*     */         } else {
/*     */           
/* 312 */           int y = par1World.getPrecipitationHeight(x, z) - 1;
/*     */           
/* 314 */           Block topmost_block = par1World.getBlock(x, y, z);
/*     */           
/* 316 */           if (topmost_block == Block.dirt) {
/* 317 */             par1World.setBlock(x, y, z, Block.grass.blockID, 0, 2);
/*     */           }
/*     */         } 
/* 320 */         for (int dy = 7; dy >= 0; dy--) {
/*     */           
/* 322 */           int y = par4 + dy;
/*     */ 
/*     */           
/* 325 */           if (par1World.isAirOrPassableBlock(x, y, z, true)) {
/*     */             
/* 327 */             Block block_above = par1World.getBlock(x, y + 1, z);
/*     */             
/* 329 */             if (block_above == Block.wood) {
/*     */ 
/*     */ 
/*     */               
/* 333 */               int metadata = par1World.getBlockMetadata(x, y + 1, z);
/*     */               
/* 335 */               BlockLog log = (BlockLog)block_above;
/*     */               
/* 337 */               if (log.getAxis(metadata).isUpDown()) {
/* 338 */                 par1World.setBlock(x, y, z, Block.wood.blockID, metadata, 2);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 345 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenLakes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */