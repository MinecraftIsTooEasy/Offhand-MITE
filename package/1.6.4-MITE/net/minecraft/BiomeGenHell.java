/*    */ package net.minecraft;
/*    */ 
/*    */ public class BiomeGenHell
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenHell(int par1) {
/*  7 */     super(par1);
/*  8 */     this.spawnableMonsterList.clear();
/*  9 */     this.spawnableCreatureList.clear();
/* 10 */     this.spawnableWaterCreatureList.clear();
/* 11 */     this.spawnableCaveCreatureList.clear();
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 27 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 1, 2));
/* 28 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 1, 4));
/* 29 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 10, 4, 4));
/* 30 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityEarthElemental.class, 40, 1, 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenHell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */