/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BiomeGenJungle
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenJungle(int par1) {
/*  9 */     super(par1);
/* 10 */     this.theBiomeDecorator.treesPerChunk = 50;
/* 11 */     this.theBiomeDecorator.grassPerChunk = 25;
/*    */     
/* 13 */     this.theBiomeDecorator.flowersPerChunk = 6;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 19 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 10, 1, 1));
/* 20 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 1, 1));
/*    */     
/* 22 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityBlackWidowSpider.class, 10, 1, 1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
/* 30 */     return (par1Random.nextInt(10) == 0) ? this.worldGeneratorBigTree : ((par1Random.nextInt(2) == 0) ? new WorldGenShrub(3, 0) : ((par1Random.nextInt(3) == 0) ? new WorldGenHugeTrees(false, 10 + par1Random.nextInt(20), 3, 3) : new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
/* 38 */     return (par1Random.nextInt(4) == 0) ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void decorate(World par1World, Random par2Random, int par3, int par4) {
/* 43 */     super.decorate(par1World, par2Random, par3, par4);
/* 44 */     WorldGenVines var5 = new WorldGenVines();
/*    */     
/* 46 */     for (int var6 = 0; var6 < 50; var6++) {
/*    */       
/* 48 */       int var7 = par3 + par2Random.nextInt(16) + 8;
/* 49 */       byte var8 = 64;
/* 50 */       int var9 = par4 + par2Random.nextInt(16) + 8;
/* 51 */       var5.generate(par1World, par2Random, var7, var8, var9);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenJungle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */