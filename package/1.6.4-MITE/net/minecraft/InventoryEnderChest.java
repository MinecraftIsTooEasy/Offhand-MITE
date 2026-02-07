/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryEnderChest
/*    */   extends InventoryBasic
/*    */ {
/*    */   private TileEntityEnderChest associatedChest;
/*    */   
/*    */   public InventoryEnderChest() {
/* 15 */     super("container.enderchest", false, 27);
/*    */   }
/*    */   
/*    */   public void setAssociatedChest(TileEntityEnderChest tileEntityEnderChest) {
/* 19 */     this.associatedChest = tileEntityEnderChest;
/*    */   }
/*    */   public void loadInventoryFromNBT(NBTTagList nBTTagList) {
/*    */     byte b;
/* 23 */     for (b = 0; b < getSizeInventory(); b++) {
/* 24 */       setInventorySlotContents(b, null);
/*    */     }
/* 26 */     for (b = 0; b < nBTTagList.tagCount(); b++) {
/* 27 */       NBTTagCompound nBTTagCompound = (NBTTagCompound)nBTTagList.tagAt(b);
/* 28 */       int i = nBTTagCompound.getByte("Slot") & 0xFF;
/* 29 */       if (i >= 0 && i < getSizeInventory()) setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(nBTTagCompound)); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public NBTTagList saveInventoryToNBT() {
/* 34 */     NBTTagList nBTTagList = new NBTTagList("EnderItems");
/* 35 */     for (byte b = 0; b < getSizeInventory(); b++) {
/* 36 */       ItemStack itemStack = getStackInSlot(b);
/* 37 */       if (itemStack != null) {
/* 38 */         NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 39 */         nBTTagCompound.setByte("Slot", (byte)b);
/* 40 */         itemStack.writeToNBT(nBTTagCompound);
/* 41 */         nBTTagList.appendTag(nBTTagCompound);
/*    */       } 
/*    */     } 
/* 44 */     return nBTTagList;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
/* 49 */     if (this.associatedChest != null && !this.associatedChest.isUseableByPlayer(entityPlayer)) {
/* 50 */       return false;
/*    */     }
/* 52 */     return super.isUseableByPlayer(entityPlayer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void openChest() {
/* 57 */     if (this.associatedChest != null) {
/* 58 */       this.associatedChest.openChest();
/*    */     }
/* 60 */     super.openChest();
/*    */   }
/*    */ 
/*    */   
/*    */   public void closeChest() {
/* 65 */     if (this.associatedChest != null) {
/* 66 */       this.associatedChest.closeChest();
/*    */     }
/* 68 */     super.closeChest();
/* 69 */     this.associatedChest = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isItemValidForSlot(int i, ItemStack itemStack) {
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\InventoryEnderChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */