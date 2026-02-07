/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockContainer
/*    */   extends Block
/*    */   implements ITileEntityProvider
/*    */ {
/*    */   protected BlockContainer(int par1, Material par2Material, BlockConstants block_constants) {
/* 10 */     super(par1, par2Material, block_constants);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 19 */     super.onBlockAdded(par1World, par2, par3, par4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 29 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/* 30 */     par1World.removeBlockTileEntity(par2, par3, par4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 39 */     super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
/* 40 */     TileEntity var7 = par1World.getBlockTileEntity(par2, par3, par4);
/* 41 */     return (var7 != null) ? var7.receiveClientEvent(par5, par6) : false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */