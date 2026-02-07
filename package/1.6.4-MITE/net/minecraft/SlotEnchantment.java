/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ class SlotEnchantment
/*    */   extends Slot
/*    */ {
/*    */   final ContainerEnchantment container;
/*    */   
/*    */   SlotEnchantment(ContainerEnchantment par1ContainerEnchantment, IInventory par2IInventory, int par3, int par4, int par5) {
/* 10 */     super(par2IInventory, par3, par4, par5);
/* 11 */     this.container = par1ContainerEnchantment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack par1ItemStack) {
/* 20 */     return par1ItemStack.isEnchantable();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotEnchantment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */