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
/*    */ class ContainerRepairINNER1
/*    */   extends InventoryBasic
/*    */ {
/*    */   ContainerRepairINNER1(ContainerRepair containerRepair, String string, boolean bl, int i) {
/* 34 */     super(string, bl, i);
/*    */   }
/*    */   public void onInventoryChanged() {
/* 37 */     super.onInventoryChanged();
/* 38 */     this.repairContainer.onCraftMatrixChanged(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isItemValidForSlot(int i, ItemStack itemStack) {
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerRepairINNER1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */