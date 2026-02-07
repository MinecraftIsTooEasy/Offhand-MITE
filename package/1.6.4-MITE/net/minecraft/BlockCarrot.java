/*    */ package net.minecraft;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCarrot
/*    */   extends BlockCrops
/*    */ {
/*    */   public BlockCarrot(int par1) {
/* 56 */     super(par1, 4);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getSeedItem() {
/* 61 */     return Item.carrot.itemID;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getCropItem() {
/* 66 */     return Item.carrot.itemID;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getDeadCropBlockId() {
/* 71 */     return Block.carrotDead.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getMatureYield() {
/* 76 */     return (Math.random() < 0.25D) ? 3 : 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGrowthStage(int metadata) {
/* 81 */     int growth = getGrowth(metadata);
/*    */     
/* 83 */     if (growth == 6) {
/* 84 */       growth = 5;
/*    */     }
/* 86 */     return growth / 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockCarrot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */