/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockOnionDead
/*    */   extends BlockCropsDead
/*    */ {
/*    */   public BlockOnionDead(int block_id) {
/*  9 */     super(block_id, 4);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGrowthStage(int metadata) {
/* 14 */     int growth = getGrowth(metadata);
/*    */     
/* 16 */     if (growth == 0)
/* 17 */       return 0; 
/* 18 */     if (growth == 7) {
/* 19 */       return 4;
/*    */     }
/* 21 */     return (growth + 1) / 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockOnionDead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */