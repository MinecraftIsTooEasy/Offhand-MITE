/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ class ContainerHorseInventorySlotArmor
/*    */   extends Slot
/*    */ {
/*    */   final EntityHorse theHorse;
/*    */   final ContainerHorseInventory field_111240_b;
/*    */   
/*    */   ContainerHorseInventorySlotArmor(ContainerHorseInventory par1ContainerHorseInventory, IInventory par2IInventory, int par3, int par4, int par5, EntityHorse par6EntityHorse) {
/* 11 */     super(par2IInventory, par3, par4, par5);
/* 12 */     this.field_111240_b = par1ContainerHorseInventory;
/* 13 */     this.theHorse = par6EntityHorse;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack par1ItemStack) {
/* 22 */     return (super.isItemValid(par1ItemStack) && this.theHorse.isNormalHorse() && EntityHorse.func_110211_v(par1ItemStack.itemID));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_111238_b() {
/* 28 */     return this.theHorse.isNormalHorse();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerHorseInventorySlotArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */