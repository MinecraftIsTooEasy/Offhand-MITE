/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSourceImpl
/*    */   implements IBlockSource
/*    */ {
/*    */   private final World worldObj;
/*    */   private final int xPos;
/*    */   private final int yPos;
/*    */   private final int zPos;
/*    */   
/*    */   public BlockSourceImpl(World world, int i, int j, int k) {
/* 15 */     this.worldObj = world;
/* 16 */     this.xPos = i;
/* 17 */     this.yPos = j;
/* 18 */     this.zPos = k;
/*    */   }
/*    */ 
/*    */   
/*    */   public World getWorld() {
/* 23 */     return this.worldObj;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getX() {
/* 28 */     return this.xPos + 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getY() {
/* 33 */     return this.yPos + 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZ() {
/* 38 */     return this.zPos + 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getXInt() {
/* 43 */     return this.xPos;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getYInt() {
/* 48 */     return this.yPos;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getZInt() {
/* 53 */     return this.zPos;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getBlockMetadata() {
/* 63 */     return this.worldObj.getBlockMetadata(this.xPos, this.yPos, this.zPos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity getBlockTileEntity() {
/* 73 */     return this.worldObj.getBlockTileEntity(this.xPos, this.yPos, this.zPos);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSourceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */