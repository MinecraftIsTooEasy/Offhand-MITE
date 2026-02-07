/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WeightedRandom {
/*    */   public static int getTotalWeight(Collection collection) {
/*  8 */     int i = 0;
/*  9 */     for (WeightedRandomItem weightedRandomItem : collection) {
/* 10 */       i += weightedRandomItem.itemWeight;
/*    */     }
/* 12 */     return i;
/*    */   }
/*    */   
/*    */   public static WeightedRandomItem getRandomItem(Random random, Collection collection, int i) {
/* 16 */     if (i <= 0) {
/* 17 */       throw new IllegalArgumentException();
/*    */     }
/*    */     
/* 20 */     int j = random.nextInt(i);
/* 21 */     for (WeightedRandomItem weightedRandomItem : collection) {
/* 22 */       j -= weightedRandomItem.itemWeight;
/* 23 */       if (j < 0) {
/* 24 */         return weightedRandomItem;
/*    */       }
/*    */     } 
/* 27 */     return null;
/*    */   }
/*    */   
/*    */   public static WeightedRandomItem getRandomItem(Random random, Collection collection) {
/* 31 */     return getRandomItem(random, collection, getTotalWeight(collection));
/*    */   }
/*    */   
/*    */   public static int getTotalWeight(WeightedRandomItem[] weightedRandomItems) {
/* 35 */     int i = 0;
/* 36 */     for (WeightedRandomItem weightedRandomItem : weightedRandomItems) {
/* 37 */       i += weightedRandomItem.itemWeight;
/*    */     }
/* 39 */     return i;
/*    */   }
/*    */ 
/*    */   
/*    */   public static WeightedRandomItem getRandomItem(Random random, WeightedRandomItem[] weightedRandomItems, int i) {
/* 44 */     if (i <= 0) {
/* 45 */       throw new IllegalArgumentException();
/*    */     }
/*    */     
/* 48 */     int j = random.nextInt(i);
/* 49 */     for (WeightedRandomItem weightedRandomItem : weightedRandomItems) {
/* 50 */       j -= weightedRandomItem.itemWeight;
/* 51 */       if (j < 0) {
/* 52 */         return weightedRandomItem;
/*    */       }
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   public static WeightedRandomItem getRandomItem(Random random, WeightedRandomItem[] weightedRandomItems) {
/* 59 */     return getRandomItem(random, weightedRandomItems, getTotalWeight(weightedRandomItems));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WeightedRandom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */