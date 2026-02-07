/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenPlants
/*    */   extends WorldGenerator
/*    */ {
/*    */   private BlockPlant block;
/*    */   private int metadata;
/*    */   
/*    */   public WorldGenPlants(BlockPlant block) {
/* 14 */     this.block = block;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World world, Random random, int origin_x, int origin_y, int origin_z) {
/* 19 */     int attempts = this.block.getPatchSize(world.getBiomeGenForCoords(origin_x, origin_z));
/*    */     
/* 21 */     int placements = 0;
/*    */     
/* 23 */     for (int attempt = 0; attempt < attempts; attempt++) {
/*    */       
/* 25 */       int x = origin_x + random.nextInt(8) - random.nextInt(8);
/* 26 */       int y = origin_y + random.nextInt(4) - random.nextInt(4);
/* 27 */       int z = origin_z + random.nextInt(8) - random.nextInt(8);
/*    */       
/* 29 */       if (world.isAirBlock(x, y, z) && (!world.provider.hasNoSky || y < 127) && this.block.canOccurAt(world, x, y, z, this.metadata)) {
/*    */         
/* 31 */         world.setBlock(x, y, z, this.block.blockID, this.metadata, 2);
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 36 */         if (++placements > 3 && random.nextInt(3) == 0) {
/*    */           break;
/*    */         }
/*    */       } 
/*    */     } 
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMetadata(int metadata) {
/* 46 */     this.metadata = metadata;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenPlants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */