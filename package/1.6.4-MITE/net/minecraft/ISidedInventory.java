package net.minecraft;

public interface ISidedInventory extends IInventory {
  int[] getAccessibleSlotsFromSide(int paramInt);
  
  boolean canInsertItem(int paramInt1, ItemStack paramItemStack, int paramInt2);
  
  boolean canExtractItem(int paramInt1, ItemStack paramItemStack, int paramInt2);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ISidedInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */