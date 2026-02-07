/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BlockGlass
/*    */   extends BlockBreakable
/*    */ {
/*    */   public BlockGlass(int par1, Material par2Material, boolean par3) {
/* 11 */     super(par1, "glass", par2Material, par3, (new BlockConstants()).setNeverHidesAdjacentFaces());
/* 12 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */     
/* 14 */     setMinHarvestLevel(1);
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
/*    */   public int getRenderBlockPass() {
/* 30 */     return 0;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 62 */     return dropBlockAsEntityItem(info, Item.shardGlass.itemID, 0, 6, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockGlass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */