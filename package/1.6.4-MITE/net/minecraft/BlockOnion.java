/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockOnion
/*    */   extends BlockCrops
/*    */ {
/*    */   public BlockOnion(int block_id) {
/*  9 */     super(block_id, 5);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getSeedItem() {
/* 14 */     return Item.onion.itemID;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getCropItem() {
/* 19 */     return Item.onion.itemID;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getDeadCropBlockId() {
/* 24 */     return Block.onionsDead.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getMatureYield() {
/* 29 */     return (Math.random() < 0.25D) ? 3 : 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGrowthStage(int metadata) {
/* 34 */     int growth = getGrowth(metadata);
/*    */     
/* 36 */     if (growth == 0)
/* 37 */       return 0; 
/* 38 */     if (growth == 7) {
/* 39 */       return 4;
/*    */     }
/* 41 */     return (growth + 1) / 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockOnion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */