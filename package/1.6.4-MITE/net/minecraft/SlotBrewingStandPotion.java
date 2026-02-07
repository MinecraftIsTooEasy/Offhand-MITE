/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ class SlotBrewingStandPotion
/*    */   extends Slot
/*    */ {
/*    */   private EntityPlayer player;
/*    */   
/*    */   public SlotBrewingStandPotion(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4, int par5) {
/* 10 */     super(par2IInventory, par3, par4, par5);
/* 11 */     this.player = par1EntityPlayer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack par1ItemStack) {
/* 19 */     return canHoldPotion(par1ItemStack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSlotStackLimit() {
/* 28 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
/* 33 */     if (par2ItemStack.itemID == Item.potion.itemID && par2ItemStack.getItemSubtype() > 0)
/*    */     {
/* 35 */       this.player.addStat(AchievementList.potion, 1);
/*    */     }
/*    */     
/* 38 */     super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean canHoldPotion(ItemStack par0ItemStack) {
/* 46 */     return (par0ItemStack != null && (par0ItemStack.itemID == Item.potion.itemID || par0ItemStack.itemID == Item.glassBottle.itemID));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotBrewingStandPotion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */