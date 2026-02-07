/*    */ package net.minecraft;
/*    */ 
/*    */ public class BiomeGenPlains
/*    */   extends BiomeGenBase
/*    */ {
/*    */   protected BiomeGenPlains(int par1) {
/*  7 */     super(par1);
/*    */ 
/*    */     
/* 10 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 1, 2));
/* 11 */     this.theBiomeDecorator.treesPerChunk = -999;
/* 12 */     this.theBiomeDecorator.flowersPerChunk = 4;
/* 13 */     this.theBiomeDecorator.grassPerChunk = 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenPlains.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */