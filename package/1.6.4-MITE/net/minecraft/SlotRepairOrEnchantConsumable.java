/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotRepairOrEnchantConsumable
/*    */   extends Slot
/*    */ {
/*    */   private BlockAnvil anvil;
/*    */   
/*    */   public SlotRepairOrEnchantConsumable(IInventory inventory, int slot_index, int display_x, int display_y, BlockAnvil anvil) {
/* 11 */     super(inventory, slot_index, display_x, display_y, true);
/*    */     
/* 13 */     this.anvil = anvil;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack item_stack) {
/* 18 */     if (!super.isItemValid(item_stack)) {
/* 19 */       return false;
/*    */     }
/* 21 */     Item item = item_stack.getItem();
/*    */     
/* 23 */     if (item == Item.enchantedBook || item == Item.bottleOfDisenchanting) {
/* 24 */       return true;
/*    */     }
/*    */     
/* 27 */     if (item_stack.isItemStackDamageable() && !item_stack.isItemEnchanted()) {
/* 28 */       return true;
/*    */     }
/*    */ 
/*    */     
/* 32 */     return item_stack.isRepairItem();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotRepairOrEnchantConsumable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */