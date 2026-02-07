/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockInfo
/*    */ {
/*    */   public Block block;
/*    */   public int x;
/*    */   public int y;
/*    */   public int z;
/*    */   public int metadata;
/*    */   
/*    */   public BlockInfo(Block block, int x, int y, int z) {
/* 14 */     this(block, x, y, z, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockInfo(Block block, int x, int y, int z, int metadata) {
/* 19 */     this.block = block;
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/* 22 */     this.z = z;
/* 23 */     this.metadata = metadata;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockInfo(World world, Block block, int x, int y, int z) {
/* 29 */     this(block, x, y, z, world.getBlockMetadata(x, y, z));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockInfo(RaycastCollision rc) {
/* 34 */     this(rc.getBlockHit(), rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.block_hit_metadata);
/*    */     
/* 36 */     if (rc.getBlockHit() == null) {
/* 37 */       Minecraft.setErrorMessage("BlockInfo(rc): rc.getBlockHit() was null");
/*    */     }
/*    */   }
/*    */   
/*    */   public String toString() {
/* 42 */     return this.block.getLocalizedName() + " @ [" + this.x + "," + this.y + "," + this.z + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */