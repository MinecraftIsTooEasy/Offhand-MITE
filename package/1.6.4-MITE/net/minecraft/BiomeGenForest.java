/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BiomeGenForest
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenForest(int par1) {
/*  9 */     super(par1);
/*    */     
/* 11 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 10, 1, 3));
/* 12 */     this.theBiomeDecorator.treesPerChunk = 10;
/* 13 */     this.theBiomeDecorator.grassPerChunk = 2;
/*    */     
/* 15 */     this.theBiomeDecorator.surface_mushrooms_per_chunk = 2;
/*    */ 
/*    */ 
/*    */     
/* 19 */     this.theBiomeDecorator.bush_patches_per_chunk_tenths = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
/* 27 */     return (par1Random.nextInt(5) == 0) ? this.worldGeneratorForest : ((par1Random.nextInt(10) == 0) ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenForest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */