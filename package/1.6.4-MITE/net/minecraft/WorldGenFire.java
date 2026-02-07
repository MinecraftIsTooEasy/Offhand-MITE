/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFire
/*    */   extends WorldGenerator
/*    */ {
/*    */   public boolean generate(World world, Random random, int i, int j, int k) {
/* 11 */     for (byte b = 0; b < 64; b++) {
/* 12 */       int m = i + random.nextInt(8) - random.nextInt(8);
/* 13 */       int n = j + random.nextInt(4) - random.nextInt(4);
/* 14 */       int i1 = k + random.nextInt(8) - random.nextInt(8);
/* 15 */       if (world.isAirBlock(m, n, i1) && 
/* 16 */         world.getBlockId(m, n - 1, i1) == Block.netherrack.blockID) {
/* 17 */         world.setBlock(m, n, i1, Block.fire.blockID, 0, 2);
/*    */       }
/*    */     } 
/* 20 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenFire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */