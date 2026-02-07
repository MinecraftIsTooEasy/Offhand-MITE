/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenSpikes
/*    */   extends WorldGenerator
/*    */ {
/*    */   private int replaceID;
/*    */   
/*    */   public WorldGenSpikes(int i) {
/* 14 */     this.replaceID = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World world, Random random, int i, int j, int k) {
/* 19 */     if (!world.isAirBlock(i, j, k) || world.getBlockId(i, j - 1, k) != this.replaceID) {
/* 20 */       return false;
/*    */     }
/* 22 */     int m = random.nextInt(32) + 6;
/* 23 */     int n = random.nextInt(4) + 1; int i1;
/* 24 */     for (i1 = i - n; i1 <= i + n; i1++) {
/* 25 */       for (int i2 = k - n; i2 <= k + n; i2++) {
/* 26 */         int i3 = i1 - i;
/* 27 */         int i4 = i2 - k;
/* 28 */         if (i3 * i3 + i4 * i4 <= n * n + 1 && 
/* 29 */           world.getBlockId(i1, j - 1, i2) != this.replaceID) return false; 
/*    */       } 
/*    */     } 
/* 32 */     for (i1 = j; i1 < j + m && 
/* 33 */       i1 < 128; i1++) {
/* 34 */       for (int i2 = i - n; i2 <= i + n; i2++) {
/* 35 */         for (int i3 = k - n; i3 <= k + n; i3++) {
/* 36 */           int i4 = i2 - i;
/* 37 */           int i5 = i3 - k;
/* 38 */           if (i4 * i4 + i5 * i5 <= n * n + 1) {
/* 39 */             world.setBlock(i2, i1, i3, Block.obsidian.blockID, 0, 2);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     EntityEnderCrystal entityEnderCrystal = new EntityEnderCrystal(world);
/* 46 */     entityEnderCrystal.setLocationAndAngles((i + 0.5F), (j + m), (k + 0.5F), random.nextFloat() * 360.0F, 0.0F);
/* 47 */     world.spawnEntityInWorld(entityEnderCrystal);
/* 48 */     world.setBlock(i, j + m, k, Block.bedrock.blockID, 0, 2);
/*    */     
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenSpikes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */