/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerMerchant
/*     */   extends Container
/*     */ {
/*     */   private IMerchant theMerchant;
/*     */   private InventoryMerchant merchantInventory;
/*     */   
/*     */   public ContainerMerchant(EntityPlayer player, IMerchant par2IMerchant) {
/*  15 */     super(player);
/*     */     
/*  17 */     this.theMerchant = par2IMerchant;
/*     */ 
/*     */     
/*  20 */     this.merchantInventory = new InventoryMerchant(player, par2IMerchant);
/*  21 */     addSlotToContainer(new Slot(this.merchantInventory, 0, 36, 53));
/*  22 */     addSlotToContainer(new Slot(this.merchantInventory, 1, 62, 53));
/*     */     
/*  24 */     addSlotToContainer(new SlotMerchantResult(player, par2IMerchant, this.merchantInventory, 2, 120, 53));
/*     */     
/*     */     int var4;
/*  27 */     for (var4 = 0; var4 < 3; var4++) {
/*     */       
/*  29 */       for (int var5 = 0; var5 < 9; var5++)
/*     */       {
/*     */         
/*  32 */         addSlotToContainer(new Slot(player.inventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  36 */     for (var4 = 0; var4 < 9; var4++)
/*     */     {
/*     */       
/*  39 */       addSlotToContainer(new Slot(player.inventory, var4, 8 + var4 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryMerchant getMerchantInventory() {
/*  45 */     return this.merchantInventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCraftingToCrafters(ICrafting par1ICrafting) {
/*  50 */     super.addCraftingToCrafters(par1ICrafting);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/*  58 */     super.detectAndSendChanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory par1IInventory) {
/*  66 */     this.merchantInventory.resetRecipeAndSlots();
/*  67 */     super.onCraftMatrixChanged(par1IInventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentRecipeIndex(int par1) {
/*  72 */     this.merchantInventory.setCurrentRecipeIndex(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int par1, int par2) {}
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/*  79 */     return (this.theMerchant.getCustomer() == par1EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/*  87 */     ItemStack var3 = null;
/*  88 */     Slot var4 = this.inventorySlots.get(par2);
/*     */     
/*  90 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  92 */       ItemStack var5 = var4.getStack();
/*  93 */       var3 = var5.copy();
/*     */       
/*  95 */       if (par2 == 2) {
/*     */         
/*  97 */         if (!mergeItemStack(var5, 3, 39, true))
/*     */         {
/*  99 */           return null;
/*     */         }
/*     */         
/* 102 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 104 */       else if (par2 != 0 && par2 != 1) {
/*     */         
/* 106 */         if (par2 >= 3 && par2 < 30)
/*     */         {
/* 108 */           if (!mergeItemStack(var5, 30, 39, false))
/*     */           {
/* 110 */             return null;
/*     */           }
/*     */         }
/* 113 */         else if (par2 >= 30 && par2 < 39 && !mergeItemStack(var5, 3, 30, false))
/*     */         {
/* 115 */           return null;
/*     */         }
/*     */       
/* 118 */       } else if (!mergeItemStack(var5, 3, 39, false)) {
/*     */         
/* 120 */         return null;
/*     */       } 
/*     */       
/* 123 */       if (var5.stackSize == 0) {
/*     */         
/* 125 */         var4.putStack((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/* 129 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 132 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 134 */         return null;
/*     */       }
/*     */       
/* 137 */       var4.onPickupFromSlot(par1EntityPlayer, var5);
/*     */     } 
/*     */     
/* 140 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
/* 148 */     super.onContainerClosed(par1EntityPlayer);
/* 149 */     this.theMerchant.setCustomer((EntityPlayer)null);
/* 150 */     super.onContainerClosed(par1EntityPlayer);
/*     */ 
/*     */     
/* 153 */     if (!this.world.isRemote) {
/*     */       
/* 155 */       ItemStack var2 = this.merchantInventory.getStackInSlotOnClosing(0);
/*     */       
/* 157 */       if (var2 != null)
/*     */       {
/* 159 */         par1EntityPlayer.dropPlayerItem(var2);
/*     */       }
/*     */       
/* 162 */       var2 = this.merchantInventory.getStackInSlotOnClosing(1);
/*     */       
/* 164 */       if (var2 != null)
/*     */       {
/* 166 */         par1EntityPlayer.dropPlayerItem(var2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerMerchant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */