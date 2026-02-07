/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class SlotArmor
/*    */   extends Slot
/*    */ {
/*    */   final int armorType;
/*    */   final ContainerPlayer parent;
/*    */   
/*    */   SlotArmor(ContainerPlayer par1ContainerPlayer, IInventory par2IInventory, int par3, int par4, int par5, int par6) {
/* 17 */     super(par2IInventory, par3, par4, par5);
/* 18 */     this.parent = par1ContainerPlayer;
/* 19 */     this.armorType = par6;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack par1ItemStack) {
/* 37 */     if (this.parent.player.hasCurse(Curse.cannot_wear_armor, true)) {
/* 38 */       return false;
/*    */     }
/* 40 */     return (par1ItemStack == null) ? false : ((par1ItemStack.getItem() instanceof ItemArmor) ? ((((ItemArmor)par1ItemStack.getItem()).armorType == this.armorType)) : (((par1ItemStack.getItem()).itemID != Block.pumpkin.blockID && (par1ItemStack.getItem()).itemID != Item.skull.itemID) ? false : ((this.armorType == 0))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getBackgroundIconIndex() {
/* 48 */     return ItemArmor.func_94602_b(this.armorType);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */