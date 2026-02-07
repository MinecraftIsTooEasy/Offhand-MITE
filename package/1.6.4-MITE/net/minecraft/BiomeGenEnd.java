/*    */ package net.minecraft;
/*    */ 
/*    */ public class BiomeGenEnd
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenEnd(int par1) {
/*  7 */     super(par1);
/*  8 */     this.spawnableMonsterList.clear();
/*  9 */     this.spawnableCreatureList.clear();
/* 10 */     this.spawnableWaterCreatureList.clear();
/* 11 */     this.spawnableCaveCreatureList.clear();
/*    */     
/* 13 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 100, 4, 4));
/* 14 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityEarthElemental.class, 20, 1, 4));
/* 15 */     this.topBlock = (byte)Block.dirt.blockID;
/* 16 */     this.fillerBlock = (byte)Block.dirt.blockID;
/* 17 */     this.theBiomeDecorator = new BiomeEndDecorator(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSkyColorByTemp(float par1) {
/* 25 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenEnd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */