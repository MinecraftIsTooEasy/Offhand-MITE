/*     */ package net.minecraft;
/*     */ 
/*     */ public class InventoryCraftResult
/*     */   implements IInventory
/*     */ {
/*   6 */   private ItemStack[] stackResult = new ItemStack[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  13 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  21 */     return this.stackResult[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCustomNameOrUnlocalized() {
/*  29 */     return "Result";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/*  38 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  47 */     if (this.stackResult[0] != null) {
/*     */       
/*  49 */       ItemStack var3 = this.stackResult[0];
/*  50 */       this.stackResult[0] = null;
/*  51 */       return var3;
/*     */     } 
/*     */ 
/*     */     
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/*  65 */     if (this.stackResult[0] != null) {
/*     */       
/*  67 */       ItemStack var2 = this.stackResult[0];
/*  68 */       this.stackResult[0] = null;
/*  69 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/*  82 */     this.stackResult[0] = par2ItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/*  91 */     return 64;
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
/* 104 */     return true;
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
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 122 */     ItemStack[] item_stacks = this.stackResult;
/*     */     
/* 124 */     for (int i = 0; i < item_stacks.length; i++)
/* 125 */       item_stacks[i] = null; 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\InventoryCraftResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */