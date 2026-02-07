package net.minecraft;

public interface IInventory {
  int getSizeInventory();
  
  ItemStack getStackInSlot(int paramInt);
  
  ItemStack decrStackSize(int paramInt1, int paramInt2);
  
  ItemStack getStackInSlotOnClosing(int paramInt);
  
  void setInventorySlotContents(int paramInt, ItemStack paramItemStack);
  
  String getCustomNameOrUnlocalized();
  
  boolean hasCustomName();
  
  int getInventoryStackLimit();
  
  void onInventoryChanged();
  
  boolean isUseableByPlayer(EntityPlayer paramEntityPlayer);
  
  void openChest();
  
  void closeChest();
  
  boolean isItemValidForSlot(int paramInt, ItemStack paramItemStack);
  
  void destroyInventory();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */