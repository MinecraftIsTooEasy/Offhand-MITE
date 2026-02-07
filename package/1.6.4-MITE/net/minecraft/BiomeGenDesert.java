/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BiomeGenDesert
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenDesert(int par1) {
/*  9 */     super(par1);
/* 10 */     this.spawnableCreatureList.clear();
/* 11 */     this.topBlock = (byte)Block.sand.blockID;
/* 12 */     this.fillerBlock = (byte)Block.sand.blockID;
/* 13 */     this.theBiomeDecorator.treesPerChunk = -999;
/* 14 */     this.theBiomeDecorator.deadBushPerChunk = 2;
/* 15 */     this.theBiomeDecorator.reedsPerChunk = 50;
/* 16 */     this.theBiomeDecorator.cactiPerChunk = 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public void decorate(World par1World, Random par2Random, int par3, int par4) {
/* 21 */     super.decorate(par1World, par2Random, par3, par4);
/*    */     
/* 23 */     if (par2Random.nextInt(1000) == 0) {
/*    */       
/* 25 */       int var5 = par3 + par2Random.nextInt(16) + 8;
/* 26 */       int var6 = par4 + par2Random.nextInt(16) + 8;
/* 27 */       WorldGenDesertWells var7 = new WorldGenDesertWells();
/* 28 */       var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenDesert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */