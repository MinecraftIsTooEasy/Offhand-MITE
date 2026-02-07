/*    */ package net.minecraft;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemGlassBottle
/*    */   extends Item
/*    */ {
/*    */   public ItemGlassBottle(int id) {
/* 71 */     super(id, Material.glass, "potion_bottle_empty");
/*    */     
/* 73 */     setMaxStackSize(8);
/* 74 */     setCreativeTab(CreativeTabs.tabBrewing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getIconFromSubtype(int par1) {
/* 79 */     return Item.potion.getIconFromSubtype(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 84 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*    */     
/* 86 */     if (rc == null || !rc.isBlock()) {
/* 87 */       return false;
/*    */     }
/* 89 */     if (rc.getBlockHitMaterial() == Material.water || rc.getNeighborOfBlockHitMaterial() == Material.water) {
/*    */       
/* 91 */       if (player.onServer()) {
/* 92 */         player.convertOneOfHeldItem(new ItemStack(potion, 1, 0));
/*    */       }
/* 94 */       return true;
/*    */     } 
/*    */     
/* 97 */     return false;
/*    */   }
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {}
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemGlassBottle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */