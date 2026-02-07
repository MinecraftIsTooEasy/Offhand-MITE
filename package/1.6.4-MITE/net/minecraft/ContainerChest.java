/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class ContainerChest
/*     */   extends Container
/*     */ {
/*     */   private IInventory lowerChestInventory;
/*     */   private int numRows;
/*     */   
/*     */   public ContainerChest(EntityPlayer player, IInventory par2IInventory) {
/*  11 */     super(player);
/*     */     
/*  13 */     this.lowerChestInventory = par2IInventory;
/*  14 */     this.numRows = par2IInventory.getSizeInventory() / 9;
/*  15 */     par2IInventory.openChest();
/*  16 */     int var3 = (this.numRows - 4) * 18;
/*     */     
/*     */     int var4;
/*     */     
/*  20 */     for (var4 = 0; var4 < this.numRows; var4++) {
/*     */       
/*  22 */       for (int var5 = 0; var5 < 9; var5++)
/*     */       {
/*  24 */         addSlotToContainer(new Slot(par2IInventory, var5 + var4 * 9, 8 + var5 * 18, 18 + var4 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  28 */     for (var4 = 0; var4 < 3; var4++) {
/*     */       
/*  30 */       for (int var5 = 0; var5 < 9; var5++)
/*     */       {
/*     */         
/*  33 */         addSlotToContainer(new Slot(player.inventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 103 + var4 * 18 + var3));
/*     */       }
/*     */     } 
/*     */     
/*  37 */     for (var4 = 0; var4 < 9; var4++)
/*     */     {
/*     */       
/*  40 */       addSlotToContainer(new Slot(player.inventory, var4, 8 + var4 * 18, 161 + var3));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/*  46 */     return this.lowerChestInventory.isUseableByPlayer(par1EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/*  54 */     ItemStack var3 = null;
/*  55 */     Slot var4 = this.inventorySlots.get(par2);
/*     */     
/*  57 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  59 */       ItemStack var5 = var4.getStack();
/*  60 */       var3 = var5.copy();
/*     */       
/*  62 */       if (par2 < this.numRows * 9) {
/*     */         
/*  64 */         if (!mergeItemStack(var5, this.numRows * 9, this.inventorySlots.size(), true))
/*     */         {
/*  66 */           return null;
/*     */         }
/*     */       }
/*  69 */       else if (!mergeItemStack(var5, 0, this.numRows * 9, false)) {
/*     */         
/*  71 */         return null;
/*     */       } 
/*     */       
/*  74 */       if (var5.stackSize == 0) {
/*     */         
/*  76 */         var4.putStack((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/*  80 */         var4.onSlotChanged();
/*     */       } 
/*     */     } 
/*     */     
/*  84 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
/*  92 */     super.onContainerClosed(par1EntityPlayer);
/*  93 */     this.lowerChestInventory.closeChest();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IInventory getLowerChestInventory() {
/* 101 */     return this.lowerChestInventory;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */