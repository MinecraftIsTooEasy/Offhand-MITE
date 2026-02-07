/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public abstract class BlockDirectionalWithTileEntity
/*    */   extends BlockDirectional
/*    */   implements ITileEntityProvider
/*    */ {
/*    */   protected BlockDirectionalWithTileEntity(int id, Material material, BlockConstants constants) {
/*  9 */     super(id, material, constants);
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract TileEntity createNewTileEntity(World paramWorld);
/*    */   
/*    */   public void breakBlock(World world, int x, int y, int z, int block_id, int metadata) {
/* 16 */     super.breakBlock(world, x, y, z, block_id, metadata);
/* 17 */     world.removeBlockTileEntity(x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onBlockEventReceived(World world, int x, int y, int z, int block_id, int event_id) {
/* 22 */     super.onBlockEventReceived(world, x, y, z, block_id, event_id);
/*    */     
/* 24 */     TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
/*    */     
/* 26 */     return (tile_entity != null) ? tile_entity.receiveClientEvent(block_id, event_id) : false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDirectionalWithTileEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */