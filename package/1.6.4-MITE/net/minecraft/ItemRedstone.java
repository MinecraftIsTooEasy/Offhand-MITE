/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ItemRedstone
/*    */   extends Item
/*    */ {
/*    */   public ItemRedstone(int par1) {
/*  8 */     super(par1, Material.redstone, "redstone_dust");
/*  9 */     setCreativeTab(CreativeTabs.tabRedstone);
/*    */     
/* 11 */     setCraftingDifficultyAsComponent(25.0F);
/* 12 */     setXPReward(5);
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 77 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*    */     
/* 79 */     if (rc == null || !rc.isBlock()) {
/* 80 */       return false;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 85 */     return player.tryPlaceHeldItemAsBlock(rc, Block.redstoneWire);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemRedstone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */