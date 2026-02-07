/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BiomeGenTaiga
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenTaiga(int par1) {
/*  9 */     super(par1);
/*    */     
/* 11 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 10, 1, 3));
/* 12 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityDireWolf.class, 5, 1, 3));
/* 13 */     this.theBiomeDecorator.treesPerChunk = 10;
/* 14 */     this.theBiomeDecorator.grassPerChunk = 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
/* 22 */     return (par1Random.nextInt(3) == 0) ? new WorldGenTaiga1() : new WorldGenTaiga2(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenTaiga.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */