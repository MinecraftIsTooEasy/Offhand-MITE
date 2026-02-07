/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class BiomeEndDecorator
/*    */   extends BiomeDecorator
/*    */ {
/*    */   protected WorldGenerator spikeGen;
/*    */   
/*    */   public BiomeEndDecorator(BiomeGenBase biomeGenBase) {
/* 10 */     super(biomeGenBase);
/*    */ 
/*    */     
/* 13 */     this.spikeGen = new WorldGenSpikes(Block.whiteStone.blockID);
/*    */   }
/*    */   
/*    */   protected void decorate() {
/* 17 */     generateOres();
/*    */     
/* 19 */     if (this.randomGenerator.nextInt(5) == 0) {
/* 20 */       int i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 21 */       int j = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/* 22 */       int k = this.currentWorld.getTopSolidOrLiquidBlock(i, j);
/* 23 */       this.spikeGen.generate(this.currentWorld, this.randomGenerator, i, k, j);
/*    */     } 
/*    */     
/* 26 */     if (this.chunk_X == 0 && this.chunk_Z == 0) {
/* 27 */       EntityDragon entityDragon = new EntityDragon(this.currentWorld);
/* 28 */       entityDragon.setLocationAndAngles(0.0D, 128.0D, 0.0D, this.randomGenerator.nextFloat() * 360.0F, 0.0F);
/* 29 */       this.currentWorld.spawnEntityInWorld(entityDragon);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeEndDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */