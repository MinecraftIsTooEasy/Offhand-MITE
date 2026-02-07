/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public abstract class BlockMountedWithTileEntity
/*    */   extends BlockMounted
/*    */   implements ITileEntityProvider
/*    */ {
/*    */   private Class tile_entity_class;
/*    */   
/*    */   protected BlockMountedWithTileEntity(int id, Material material, Class tile_entity_class, BlockConstants constants) {
/* 11 */     super(id, material, constants);
/*    */     
/* 13 */     this.tile_entity_class = tile_entity_class;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createNewTileEntity(World par1World) {
/*    */     try {
/* 20 */       return this.tile_entity_class.newInstance();
/*    */     }
/* 22 */     catch (Exception var3) {
/*    */       
/* 24 */       throw new RuntimeException(var3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void breakBlock(World world, int x, int y, int z, int block_id, int metadata) {
/* 30 */     super.breakBlock(world, x, y, z, block_id, metadata);
/* 31 */     world.removeBlockTileEntity(x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onBlockEventReceived(World world, int x, int y, int z, int block_id, int event_id) {
/* 36 */     super.onBlockEventReceived(world, x, y, z, block_id, event_id);
/*    */     
/* 38 */     TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
/*    */     
/* 40 */     return (tile_entity != null) ? tile_entity.receiveClientEvent(block_id, event_id) : false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockMountedWithTileEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */