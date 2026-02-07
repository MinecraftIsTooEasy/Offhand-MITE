/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotFuel
/*    */   extends Slot
/*    */ {
/*    */   private TileEntityFurnace furnace;
/*    */   
/*    */   public SlotFuel(IInventory inventory, int slot_index, int display_x, int display_y, TileEntityFurnace furnace) {
/* 12 */     super(inventory, slot_index, display_x, display_y, furnace.acceptsLargeItems());
/* 13 */     this.furnace = furnace;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack item_stack) {
/* 18 */     int heat_level = this.furnace.getItemHeatLevel(item_stack);
/* 19 */     return (heat_level > 0 && heat_level <= this.furnace.getMaxHeatLevel() && super.isItemValid(item_stack));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotFuel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */