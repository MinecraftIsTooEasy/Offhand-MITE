/*    */ package net.minecraft;
/*    */ 
/*    */ public class BiomeGenMushroomIsland
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenMushroomIsland(int par1) {
/*  7 */     super(par1);
/*  8 */     this.theBiomeDecorator.treesPerChunk = -100;
/*  9 */     this.theBiomeDecorator.flowersPerChunk = -100;
/* 10 */     this.theBiomeDecorator.grassPerChunk = -100;
/*    */     
/* 12 */     this.theBiomeDecorator.surface_mushrooms_per_chunk = 0;
/*    */     
/* 14 */     this.theBiomeDecorator.bigMushroomsPerChunk = 0;
/* 15 */     this.topBlock = (byte)Block.mycelium.blockID;
/* 16 */     this.spawnableMonsterList.clear();
/* 17 */     this.spawnableCreatureList.clear();
/* 18 */     this.spawnableWaterCreatureList.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenMushroomIsland.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */