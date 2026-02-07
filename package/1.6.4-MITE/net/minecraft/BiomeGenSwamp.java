/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BiomeGenSwamp
/*    */   extends BiomeGenBase
/*    */ {
/*    */   protected BiomeGenSwamp(int par1) {
/*  9 */     super(par1);
/* 10 */     this.theBiomeDecorator.treesPerChunk = 2;
/*    */     
/* 12 */     this.theBiomeDecorator.flowersPerChunk = 1;
/* 13 */     this.theBiomeDecorator.deadBushPerChunk = 1;
/*    */     
/* 15 */     this.theBiomeDecorator.surface_mushrooms_per_chunk = 8;
/* 16 */     this.theBiomeDecorator.reedsPerChunk = 10;
/* 17 */     this.theBiomeDecorator.clayPerChunk = 1;
/* 18 */     this.theBiomeDecorator.waterlilyPerChunk = 4;
/* 19 */     this.waterColorMultiplier = 14745518;
/*    */     
/* 21 */     this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
/* 29 */     return this.worldGeneratorSwamp;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getBiomeGrassColor() {
/* 37 */     double var1 = getFloatTemperature();
/* 38 */     double var3 = getFloatRainfall();
/* 39 */     return ((ColorizerGrass.getGrassColor(var1, var3) & 0xFEFEFE) + 5115470) / 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getBiomeFoliageColor() {
/* 47 */     double var1 = getFloatTemperature();
/* 48 */     double var3 = getFloatRainfall();
/* 49 */     return ((ColorizerFoliage.getFoliageColor(var1, var3) & 0xFEFEFE) + 5115470) / 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenSwamp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */