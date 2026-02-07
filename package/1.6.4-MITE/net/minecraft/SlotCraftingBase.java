/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotCraftingBase
/*    */   extends Slot
/*    */ {
/*    */   protected EntityPlayer player;
/*    */   protected int quantity_taken;
/*    */   
/*    */   public SlotCraftingBase(EntityPlayer player, IInventory inventory, int slot_index, int display_x, int display_y) {
/* 12 */     super(inventory, slot_index, display_x, display_y);
/*    */     
/* 14 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack item_stack) {
/* 19 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack decrStackSize(int quantity) {
/* 24 */     if (getHasStack()) {
/* 25 */       this.quantity_taken += Math.min(quantity, (getStack()).stackSize);
/*    */     }
/* 27 */     return super.decrStackSize(quantity);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onCrafting(ItemStack item_stack, int quantity) {
/* 32 */     this.quantity_taken += quantity;
/* 33 */     onCrafting(item_stack);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onCrafting(ItemStack item_stack) {
/* 38 */     item_stack.onCrafting(this.player.worldObj, this.player, this.quantity_taken);
/* 39 */     this.quantity_taken = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotCraftingBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */