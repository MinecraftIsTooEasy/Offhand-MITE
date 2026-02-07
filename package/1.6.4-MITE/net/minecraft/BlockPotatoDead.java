/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPotatoDead
/*    */   extends BlockCropsDead
/*    */ {
/*    */   public BlockPotatoDead(int block_id) {
/*  9 */     super(block_id, 3);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGrowthStage(int metadata) {
/* 14 */     int growth = getGrowth(metadata);
/*    */     
/* 16 */     if (growth == 6) {
/* 17 */       growth = 5;
/*    */     }
/* 19 */     return growth / 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPotatoDead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */