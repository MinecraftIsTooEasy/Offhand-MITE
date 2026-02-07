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
/*    */ class ContainerHorseInventorySlotSaddle
/*    */   extends Slot
/*    */ {
/*    */   ContainerHorseInventorySlotSaddle(ContainerHorseInventory containerHorseInventory, IInventory iInventory, int i, int j, int k) {
/* 21 */     super(iInventory, i, j, k);
/*    */   }
/*    */   public boolean isItemValid(ItemStack itemStack) {
/* 24 */     return (super.isItemValid(itemStack) && itemStack.itemID == Item.saddle.itemID && !getHasStack());
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerHorseInventorySlotSaddle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */