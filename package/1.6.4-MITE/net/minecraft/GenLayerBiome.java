/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class GenLayerBiome
/*    */   extends GenLayer
/*    */ {
/*    */   private BiomeGenBase[] allowedBiomes;
/*    */   
/*    */   public GenLayerBiome(long par1, GenLayer par3GenLayer, WorldType par4WorldType) {
/* 10 */     super(par1);
/* 11 */     this.allowedBiomes = new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.jungle };
/* 12 */     this.parent = par3GenLayer;
/*    */     
/* 14 */     if (par4WorldType == WorldType.DEFAULT_1_1)
/*    */     {
/* 16 */       this.allowedBiomes = new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga };
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private float getLatitudeTemperature(int z) {
/* 22 */     return MathHelper.clamp_float(0.7F + z / 1024.0F, -0.1F, 2.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean doesBiomeHaveValidTemperature(BiomeGenBase biome, int z, float tolerance) {
/* 27 */     float latitude_temperature = getLatitudeTemperature(z);
/*    */     
/* 29 */     float min_biome_temperature = latitude_temperature - tolerance;
/* 30 */     float max_biome_temperature = latitude_temperature + tolerance;
/*    */     
/* 32 */     return (biome.temperature >= min_biome_temperature && biome.temperature <= max_biome_temperature);
/*    */   }
/*    */ 
/*    */   
/*    */   private int getRandomBiomeWithValidTemperature(int z) {
/*    */     int biome_id;
/* 38 */     float tolerance = 0.2F;
/*    */ 
/*    */     
/*    */     do {
/* 42 */       biome_id = (this.allowedBiomes[nextInt(this.allowedBiomes.length)]).biomeID;
/*    */       
/* 44 */       if (tolerance >= 0.5F)
/* 45 */         continue;  tolerance += 0.01F;
/* 46 */     } while (!doesBiomeHaveValidTemperature(BiomeGenBase.biomeList[biome_id], z, tolerance));
/*    */     
/* 48 */     return biome_id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
/* 59 */     int[] var5 = this.parent.getInts(par1, par2, par3, par4, z);
/* 60 */     int[] var6 = IntCache.getIntCache(par3 * par4);
/*    */     
/* 62 */     for (int var7 = 0; var7 < par4; var7++) {
/*    */       
/* 64 */       for (int var8 = 0; var8 < par3; var8++) {
/*    */         
/* 66 */         initChunkSeed((var8 + par1), (var7 + par2));
/* 67 */         int var9 = var5[var8 + var7 * par3];
/*    */         
/* 69 */         if (var9 == 0) {
/*    */           
/* 71 */           var6[var8 + var7 * par3] = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         }
/* 77 */         else if (var9 == 1) {
/*    */           
/* 79 */           var6[var8 + var7 * par3] = (this.allowedBiomes[nextInt(this.allowedBiomes.length)]).biomeID;
/*    */         
/*    */         }
/*    */         else {
/*    */           
/* 84 */           int var10 = (this.allowedBiomes[nextInt(this.allowedBiomes.length)]).biomeID;
/*    */ 
/*    */           
/* 87 */           if (var10 == BiomeGenBase.taiga.biomeID) {
/*    */             
/* 89 */             var6[var8 + var7 * par3] = var10;
/*    */           }
/*    */           else {
/*    */             
/* 93 */             var6[var8 + var7 * par3] = BiomeGenBase.icePlains.biomeID;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 99 */     return var6;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerBiome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */