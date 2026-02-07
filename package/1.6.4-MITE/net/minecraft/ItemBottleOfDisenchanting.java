/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBottleOfDisenchanting
/*    */   extends Item
/*    */ {
/*    */   public ItemBottleOfDisenchanting(int id) {
/* 11 */     super(id, Material.glass, "bottle_of_disenchanting");
/*    */     
/* 13 */     setMaxStackSize(1);
/*    */     
/* 15 */     setCraftingDifficultyAsComponent(25.0F);
/*    */     
/* 17 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
/* 22 */     if (player.onServer()) {
/*    */       
/* 24 */       player.clearActivePotions();
/* 25 */       ((WorldServer)world).removeCursesFromPlayer((ServerPlayer)player);
/*    */     } 
/*    */     
/* 28 */     super.onItemUseFinish(item_stack, world, player);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 33 */     return 32;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDrinkable(int item_subtype) {
/* 38 */     return true;
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
/*    */   public Item getItemProducedOnItemUseFinish() {
/* 62 */     return glassBottle;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBottleOfDisenchanting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */