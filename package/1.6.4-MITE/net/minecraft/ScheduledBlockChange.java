/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ScheduledBlockChange
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   public int z;
/*    */   
/*    */   public ScheduledBlockChange(int x, int y, int z, int from_block_id, int to_block_id, int to_metadata, int ticks_from_now) {
/* 11 */     this.x = x;
/* 12 */     this.y = y;
/* 13 */     this.z = z;
/* 14 */     this.from_block_id = from_block_id;
/* 15 */     this.to_block_id = to_block_id;
/* 16 */     this.to_metadata = to_metadata;
/* 17 */     this.ticks_from_now = ticks_from_now;
/*    */   }
/*    */   
/*    */   public int from_block_id;
/*    */   public int to_block_id;
/*    */   public int to_metadata;
/*    */   public int ticks_from_now;
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScheduledBlockChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */