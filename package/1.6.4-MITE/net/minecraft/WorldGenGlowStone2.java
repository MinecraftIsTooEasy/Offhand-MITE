/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenGlowStone2
/*    */   extends WorldGenerator
/*    */ {
/*    */   public boolean generate(World world, Random random, int i, int j, int k) {
/* 11 */     if (!world.isAirBlock(i, j, k)) return false; 
/* 12 */     if (world.getBlockId(i, j + 1, k) != Block.netherrack.blockID) return false; 
/* 13 */     world.setBlock(i, j, k, Block.glowStone.blockID, 0, 2);
/*    */     
/* 15 */     for (byte b = 0; b < 'ל'; b++) {
/* 16 */       int m = i + random.nextInt(8) - random.nextInt(8);
/* 17 */       int n = j - random.nextInt(12);
/* 18 */       int i1 = k + random.nextInt(8) - random.nextInt(8);
/* 19 */       if (world.getBlockId(m, n, i1) == 0) {
/*    */         
/* 21 */         byte b1 = 0;
/* 22 */         for (byte b2 = 0; b2 < 6; b2++) {
/* 23 */           int i2 = 0;
/* 24 */           if (b2 == 0) i2 = world.getBlockId(m - 1, n, i1); 
/* 25 */           if (b2 == 1) i2 = world.getBlockId(m + 1, n, i1); 
/* 26 */           if (b2 == 2) i2 = world.getBlockId(m, n - 1, i1); 
/* 27 */           if (b2 == 3) i2 = world.getBlockId(m, n + 1, i1); 
/* 28 */           if (b2 == 4) i2 = world.getBlockId(m, n, i1 - 1); 
/* 29 */           if (b2 == 5) i2 = world.getBlockId(m, n, i1 + 1);
/*    */           
/* 31 */           if (i2 == Block.glowStone.blockID) b1++;
/*    */         
/*    */         } 
/* 34 */         if (b1 == 1) world.setBlock(m, n, i1, Block.glowStone.blockID, 0, 2); 
/*    */       } 
/*    */     } 
/* 37 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenGlowStone2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */