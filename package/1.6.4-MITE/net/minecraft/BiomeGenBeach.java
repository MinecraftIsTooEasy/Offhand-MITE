/*    */ package net.minecraft;
/*    */ 
/*    */ public class BiomeGenBeach
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenBeach(int par1) {
/*  7 */     super(par1);
/*  8 */     this.spawnableCreatureList.clear();
/*  9 */     this.topBlock = (byte)Block.sand.blockID;
/* 10 */     this.fillerBlock = (byte)Block.sand.blockID;
/* 11 */     this.theBiomeDecorator.treesPerChunk = -999;
/* 12 */     this.theBiomeDecorator.deadBushPerChunk = 0;
/* 13 */     this.theBiomeDecorator.reedsPerChunk = 0;
/* 14 */     this.theBiomeDecorator.cactiPerChunk = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenBeach.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */