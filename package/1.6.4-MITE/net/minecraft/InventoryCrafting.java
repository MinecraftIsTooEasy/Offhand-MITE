/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryCrafting
/*     */   implements IInventory
/*     */ {
/*     */   private ItemStack[] stackList;
/*     */   private int inventoryWidth;
/*     */   private Container eventHandler;
/*     */   
/*     */   public InventoryCrafting(Container par1Container, int par2, int par3) {
/*  18 */     int var4 = par2 * par3;
/*  19 */     this.stackList = new ItemStack[var4];
/*  20 */     this.eventHandler = par1Container;
/*  21 */     this.inventoryWidth = par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  29 */     return this.stackList.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  37 */     return (par1 >= getSizeInventory()) ? null : this.stackList[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInRowAndColumn(int par1, int par2) {
/*  45 */     if (par1 >= 0 && par1 < this.inventoryWidth) {
/*     */       
/*  47 */       int var3 = par1 + par2 * this.inventoryWidth;
/*  48 */       return getStackInSlot(var3);
/*     */     } 
/*     */ 
/*     */     
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCustomNameOrUnlocalized() {
/*  61 */     return "container.crafting";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/*  79 */     if (this.stackList[par1] != null) {
/*     */       
/*  81 */       ItemStack var2 = this.stackList[par1];
/*  82 */       this.stackList[par1] = null;
/*  83 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  97 */     if (this.stackList[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/* 101 */       if ((this.stackList[par1]).stackSize <= par2) {
/*     */         
/* 103 */         ItemStack itemStack = this.stackList[par1];
/* 104 */         this.stackList[par1] = null;
/* 105 */         this.eventHandler.onCraftMatrixChanged(this);
/* 106 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 110 */       ItemStack var3 = this.stackList[par1].splitStack(par2);
/*     */       
/* 112 */       if ((this.stackList[par1]).stackSize == 0)
/*     */       {
/* 114 */         this.stackList[par1] = null;
/*     */       }
/*     */       
/* 117 */       this.eventHandler.onCraftMatrixChanged(this);
/* 118 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 135 */     this.stackList[par1] = par2ItemStack;
/* 136 */     this.eventHandler.onCraftMatrixChanged(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInventory(ItemStack[] item_stacks) {
/* 141 */     this.stackList = item_stacks;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getInventory() {
/* 146 */     return this.stackList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 155 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInventoryChanged() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 168 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void openChest() {}
/*     */ 
/*     */   
/*     */   public void closeChest() {}
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
/* 180 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Container getEventHandler() {
/* 185 */     return this.eventHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 191 */     ItemStack[] item_stacks = this.stackList;
/*     */     
/* 193 */     for (int i = 0; i < item_stacks.length; i++) {
/* 194 */       item_stacks[i] = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasDamagedItem() {
/* 231 */     for (int i = 0; i < this.stackList.length; i++) {
/*     */       
/* 233 */       ItemStack item_stack = this.stackList[i];
/*     */       
/* 235 */       if (item_stack != null && item_stack.isItemDamaged()) {
/* 236 */         return true;
/*     */       }
/*     */     } 
/* 239 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\InventoryCrafting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */