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
/*    */ class SlotEnchantmentTable
/*    */   extends InventoryBasic
/*    */ {
/*    */   SlotEnchantmentTable(ContainerEnchantment containerEnchantment, String string, boolean bl, int i) {
/* 18 */     super(string, bl, i);
/*    */   }
/*    */   public int getInventoryStackLimit() {
/* 21 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInventoryChanged() {
/* 26 */     super.onInventoryChanged();
/* 27 */     this.container.onCraftMatrixChanged(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isItemValidForSlot(int i, ItemStack itemStack) {
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotEnchantmentTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */