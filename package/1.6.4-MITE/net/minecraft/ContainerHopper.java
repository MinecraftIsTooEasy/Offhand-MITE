/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ContainerHopper
/*    */   extends Container
/*    */ {
/*    */   private final IInventory field_94538_a;
/*    */   
/*    */   public ContainerHopper(EntityPlayer player, IInventory par2IInventory) {
/* 10 */     super(player);
/*    */     
/* 12 */     this.field_94538_a = par2IInventory;
/* 13 */     par2IInventory.openChest();
/* 14 */     byte var3 = 51;
/*    */     
/*    */     int var4;
/* 17 */     for (var4 = 0; var4 < par2IInventory.getSizeInventory(); var4++)
/*    */     {
/* 19 */       addSlotToContainer(new Slot(par2IInventory, var4, 44 + var4 * 18, 20));
/*    */     }
/*    */     
/* 22 */     for (var4 = 0; var4 < 3; var4++) {
/*    */       
/* 24 */       for (int var5 = 0; var5 < 9; var5++)
/*    */       {
/*    */         
/* 27 */         addSlotToContainer(new Slot(player.inventory, var5 + var4 * 9 + 9, 8 + var5 * 18, var4 * 18 + var3));
/*    */       }
/*    */     } 
/*    */     
/* 31 */     for (var4 = 0; var4 < 9; var4++)
/*    */     {
/*    */       
/* 34 */       addSlotToContainer(new Slot(player.inventory, var4, 8 + var4 * 18, 58 + var3));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/* 40 */     return this.field_94538_a.isUseableByPlayer(par1EntityPlayer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/* 48 */     ItemStack var3 = null;
/* 49 */     Slot var4 = this.inventorySlots.get(par2);
/*    */     
/* 51 */     if (var4 != null && var4.getHasStack()) {
/*    */       
/* 53 */       ItemStack var5 = var4.getStack();
/* 54 */       var3 = var5.copy();
/*    */       
/* 56 */       if (par2 < this.field_94538_a.getSizeInventory()) {
/*    */         
/* 58 */         if (!mergeItemStack(var5, this.field_94538_a.getSizeInventory(), this.inventorySlots.size(), true))
/*    */         {
/* 60 */           return null;
/*    */         }
/*    */       }
/* 63 */       else if (!mergeItemStack(var5, 0, this.field_94538_a.getSizeInventory(), false)) {
/*    */         
/* 65 */         return null;
/*    */       } 
/*    */       
/* 68 */       if (var5.stackSize == 0) {
/*    */         
/* 70 */         var4.putStack((ItemStack)null);
/*    */       }
/*    */       else {
/*    */         
/* 74 */         var4.onSlotChanged();
/*    */       } 
/*    */     } 
/*    */     
/* 78 */     return var3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
/* 86 */     super.onContainerClosed(par1EntityPlayer);
/* 87 */     this.field_94538_a.closeChest();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerHopper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */