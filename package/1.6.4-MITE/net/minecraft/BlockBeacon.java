/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class BlockBeacon
/*    */   extends BlockContainer
/*    */ {
/*    */   public BlockBeacon(int par1) {
/*  8 */     super(par1, Material.glass, (new BlockConstants()).setNeverHidesAdjacentFaces());
/*  9 */     setHardness(3.0F);
/* 10 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createNewTileEntity(World par1World) {
/* 18 */     return new TileEntityBeacon();
/*    */   }
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
/*    */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 45 */     if (player.onServer()) {
/*    */       
/* 47 */       TileEntityBeacon tile_entity = (TileEntityBeacon)world.getBlockTileEntity(x, y, z);
/*    */       
/* 49 */       if (tile_entity != null) {
/* 50 */         player.displayGUIBeacon(tile_entity);
/*    */       }
/*    */     } 
/* 53 */     return true;
/*    */   }
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
/*    */   public int getRenderType() {
/* 79 */     return 34;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 88 */     super.registerIcons(par1IconRegister);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBeacon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */