/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenClay
/*    */   extends WorldGenerator
/*    */ {
/*    */   private int clayBlockId;
/*    */   private int numberOfBlocks;
/*    */   
/*    */   public WorldGenClay(int i) {
/* 14 */     this.clayBlockId = Block.blockClay.blockID;
/* 15 */     this.numberOfBlocks = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World world, Random random, int i, int j, int k) {
/* 20 */     if (world.getBlockMaterial(i, j, k) != Material.water) return false; 
/* 21 */     int m = random.nextInt(this.numberOfBlocks - 2) + 2;
/* 22 */     byte b = 1;
/* 23 */     for (int n = i - m; n <= i + m; n++) {
/* 24 */       for (int i1 = k - m; i1 <= k + m; i1++) {
/* 25 */         int i2 = n - i;
/* 26 */         int i3 = i1 - k;
/* 27 */         if (i2 * i2 + i3 * i3 <= m * m) {
/* 28 */           for (int i4 = j - b; i4 <= j + b; i4++) {
/* 29 */             int i5 = world.getBlockId(n, i4, i1);
/* 30 */             if (i5 == Block.dirt.blockID || i5 == Block.blockClay.blockID) {
/* 31 */               world.setBlock(n, i4, i1, this.clayBlockId, 0, 2);
/*    */             }
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/* 37 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenClay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */