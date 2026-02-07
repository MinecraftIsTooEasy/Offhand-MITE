/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryLargeChest
/*     */   implements IInventory
/*     */ {
/*     */   private String name;
/*     */   private IInventory upperChest;
/*     */   private IInventory lowerChest;
/*     */   
/*     */   public InventoryLargeChest(String par1Str, IInventory par2IInventory, IInventory par3IInventory) {
/*  16 */     this.name = par1Str;
/*     */     
/*  18 */     if (par2IInventory == null)
/*     */     {
/*  20 */       par2IInventory = par3IInventory;
/*     */     }
/*     */     
/*  23 */     if (par3IInventory == null)
/*     */     {
/*  25 */       par3IInventory = par2IInventory;
/*     */     }
/*     */     
/*  28 */     this.upperChest = par2IInventory;
/*  29 */     this.lowerChest = par3IInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  37 */     return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPartOfLargeChest(IInventory par1IInventory) {
/*  45 */     return (this.upperChest == par1IInventory || this.lowerChest == par1IInventory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCustomNameOrUnlocalized() {
/*  53 */     return this.upperChest.hasCustomName() ? this.upperChest.getCustomNameOrUnlocalized() : (this.lowerChest.hasCustomName() ? this.lowerChest.getCustomNameOrUnlocalized() : this.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/*  62 */     return (this.upperChest.hasCustomName() || this.lowerChest.hasCustomName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  70 */     return (par1 >= this.upperChest.getSizeInventory()) ? this.lowerChest.getStackInSlot(par1 - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlot(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  79 */     return (par1 >= this.upperChest.getSizeInventory()) ? this.lowerChest.decrStackSize(par1 - this.upperChest.getSizeInventory(), par2) : this.upperChest.decrStackSize(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/*  88 */     return (par1 >= this.upperChest.getSizeInventory()) ? this.lowerChest.getStackInSlotOnClosing(par1 - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlotOnClosing(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/*  96 */     if (par1 >= this.upperChest.getSizeInventory()) {
/*     */       
/*  98 */       this.lowerChest.setInventorySlotContents(par1 - this.upperChest.getSizeInventory(), par2ItemStack);
/*     */     }
/*     */     else {
/*     */       
/* 102 */       this.upperChest.setInventorySlotContents(par1, par2ItemStack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 112 */     return this.upperChest.getInventoryStackLimit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInventoryChanged() {
/* 120 */     this.upperChest.onInventoryChanged();
/* 121 */     this.lowerChest.onInventoryChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 129 */     return (this.upperChest.isUseableByPlayer(par1EntityPlayer) && this.lowerChest.isUseableByPlayer(par1EntityPlayer));
/*     */   }
/*     */ 
/*     */   
/*     */   public void openChest() {
/* 134 */     this.upperChest.openChest();
/* 135 */     this.lowerChest.openChest();
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeChest() {
/* 140 */     this.upperChest.closeChest();
/* 141 */     this.lowerChest.closeChest();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
/* 149 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 155 */     this.upperChest.destroyInventory();
/* 156 */     this.lowerChest.destroyInventory();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\InventoryLargeChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */