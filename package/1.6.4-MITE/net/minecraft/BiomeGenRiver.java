/*    */ package net.minecraft;
/*    */ 
/*    */ public class BiomeGenRiver
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenRiver(int par1) {
/*  7 */     super(par1);
/*  8 */     this.spawnableCreatureList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBiomeGrassColor() {
/* 13 */     if (this == BiomeGenBase.swampRiver) {
/*    */       
/* 15 */       double var1 = getFloatTemperature();
/* 16 */       double var3 = getFloatRainfall();
/* 17 */       return ((ColorizerGrass.getGrassColor(var1, var3) & 0xFEFEFE) + 5115470) / 2;
/*    */     } 
/*    */     
/* 20 */     return super.getBiomeGrassColor();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBiomeFoliageColor() {
/* 25 */     if (this == BiomeGenBase.swampRiver) {
/*    */       
/* 27 */       double var1 = getFloatTemperature();
/* 28 */       double var3 = getFloatRainfall();
/* 29 */       return ((ColorizerFoliage.getFoliageColor(var1, var3) & 0xFEFEFE) + 5115470) / 2;
/*    */     } 
/*    */     
/* 32 */     return super.getBiomeFoliageColor();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenRiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */