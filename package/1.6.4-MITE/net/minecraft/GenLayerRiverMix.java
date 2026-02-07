/*    */ package net.minecraft;
/*    */ 
/*    */ public class GenLayerRiverMix
/*    */   extends GenLayer
/*    */ {
/*    */   private GenLayer biomePatternGeneratorChain;
/*    */   private GenLayer riverPatternGeneratorChain;
/*    */   
/*    */   public GenLayerRiverMix(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer) {
/* 10 */     super(par1);
/* 11 */     this.biomePatternGeneratorChain = par3GenLayer;
/* 12 */     this.riverPatternGeneratorChain = par4GenLayer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initWorldGenSeed(long par1) {
/* 21 */     this.biomePatternGeneratorChain.initWorldGenSeed(par1);
/* 22 */     this.riverPatternGeneratorChain.initWorldGenSeed(par1);
/* 23 */     super.initWorldGenSeed(par1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
/* 36 */     int[] var5 = this.biomePatternGeneratorChain.getInts(par1, par2, par3, par4, z);
/* 37 */     int[] var6 = this.riverPatternGeneratorChain.getInts(par1, par2, par3, par4, z);
/*    */     
/* 39 */     int[] var7 = IntCache.getIntCache(par3 * par4);
/*    */     
/* 41 */     for (int var8 = 0; var8 < par3 * par4; var8++) {
/*    */       
/* 43 */       if (var5[var8] == BiomeGenBase.ocean.biomeID) {
/*    */         
/* 45 */         var7[var8] = var5[var8];
/*    */       }
/* 47 */       else if (var6[var8] >= 0) {
/*    */         
/* 49 */         int biome_id = var5[var8];
/*    */         
/* 51 */         if (biome_id == BiomeGenBase.icePlains.biomeID)
/*    */         {
/* 53 */           var7[var8] = BiomeGenBase.frozenRiver.biomeID;
/*    */         }
/* 55 */         else if (biome_id == BiomeGenBase.desert.biomeID || biome_id == BiomeGenBase.desertHills.biomeID)
/*    */         {
/* 57 */           var7[var8] = BiomeGenBase.desertRiver.biomeID;
/*    */         }
/* 59 */         else if (biome_id == BiomeGenBase.jungle.biomeID || biome_id == BiomeGenBase.jungleHills.biomeID)
/*    */         {
/* 61 */           var7[var8] = BiomeGenBase.jungleRiver.biomeID;
/*    */         }
/* 63 */         else if (biome_id == BiomeGenBase.swampland.biomeID)
/*    */         {
/* 65 */           var7[var8] = BiomeGenBase.swampRiver.biomeID;
/*    */ 
/*    */ 
/*    */         
/*    */         }
/*    */         else
/*    */         {
/*    */ 
/*    */ 
/*    */           
/* 75 */           var7[var8] = var6[var8];
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 80 */         var7[var8] = var5[var8];
/*    */       } 
/*    */     } 
/*    */     
/* 84 */     return var7;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerRiverMix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */