/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ class SlotBrewingStandIngredient
/*    */   extends Slot
/*    */ {
/*    */   final ContainerBrewingStand brewingStand;
/*    */   
/*    */   public SlotBrewingStandIngredient(ContainerBrewingStand par1ContainerBrewingStand, IInventory par2IInventory, int par3, int par4, int par5) {
/* 10 */     super(par2IInventory, par3, par4, par5);
/* 11 */     this.brewingStand = par1ContainerBrewingStand;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack par1ItemStack) {
/* 19 */     ContainerBrewingStand cbs = (ContainerBrewingStand)getContainer();
/*    */     
/* 21 */     if (!cbs.canPlayerAddIngredients()) {
/* 22 */       return false;
/*    */     }
/* 24 */     return (par1ItemStack != null) ? Item.itemsList[par1ItemStack.itemID].isPotionIngredient() : false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSlotStackLimit() {
/* 33 */     return 64;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotBrewingStandIngredient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */