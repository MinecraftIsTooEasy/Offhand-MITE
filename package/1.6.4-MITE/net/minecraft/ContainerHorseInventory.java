/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class ContainerHorseInventory
/*     */   extends Container
/*     */ {
/*     */   private IInventory field_111243_a;
/*     */   private EntityHorse theHorse;
/*     */   
/*     */   public ContainerHorseInventory(EntityPlayer player, IInventory par2IInventory, EntityHorse par3EntityHorse) {
/*  11 */     super(player);
/*     */     
/*  13 */     this.field_111243_a = par2IInventory;
/*  14 */     this.theHorse = par3EntityHorse;
/*  15 */     byte var4 = 3;
/*  16 */     par2IInventory.openChest();
/*  17 */     int var5 = (var4 - 4) * 18;
/*  18 */     addSlotToContainer(new ContainerHorseInventorySlotSaddle(this, par2IInventory, 0, 8, 18));
/*  19 */     addSlotToContainer(new ContainerHorseInventorySlotArmor(this, par2IInventory, 1, 8, 36, par3EntityHorse));
/*     */ 
/*     */ 
/*     */     
/*  23 */     if (par3EntityHorse.isChested())
/*     */     {
/*  25 */       for (int i = 0; i < var4; i++) {
/*     */         
/*  27 */         for (int var7 = 0; var7 < 5; var7++)
/*     */         {
/*  29 */           addSlotToContainer(new Slot(par2IInventory, 2 + var7 + i * 5, 80 + var7 * 18, 18 + i * 18));
/*     */         }
/*     */       } 
/*     */     }
/*     */     int var6;
/*  34 */     for (var6 = 0; var6 < 3; var6++) {
/*     */       
/*  36 */       for (int var7 = 0; var7 < 9; var7++)
/*     */       {
/*     */         
/*  39 */         addSlotToContainer(new Slot(player.inventory, var7 + var6 * 9 + 9, 8 + var7 * 18, 102 + var6 * 18 + var5));
/*     */       }
/*     */     } 
/*     */     
/*  43 */     for (var6 = 0; var6 < 9; var6++)
/*     */     {
/*     */       
/*  46 */       addSlotToContainer(new Slot(player.inventory, var6, 8 + var6 * 18, 160 + var5));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/*  52 */     return (this.field_111243_a.isUseableByPlayer(par1EntityPlayer) && this.theHorse.isEntityAlive() && this.theHorse.getDistanceToEntity(par1EntityPlayer) < 8.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/*  60 */     ItemStack var3 = null;
/*  61 */     Slot var4 = this.inventorySlots.get(par2);
/*     */     
/*  63 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  65 */       ItemStack var5 = var4.getStack();
/*  66 */       var3 = var5.copy();
/*     */       
/*  68 */       if (par2 < this.field_111243_a.getSizeInventory()) {
/*     */         
/*  70 */         if (!mergeItemStack(var5, this.field_111243_a.getSizeInventory(), this.inventorySlots.size(), true))
/*     */         {
/*  72 */           return null;
/*     */         }
/*     */       }
/*  75 */       else if (getSlot(1).isItemValid(var5) && !getSlot(1).getHasStack()) {
/*     */         
/*  77 */         if (!mergeItemStack(var5, 1, 2, false))
/*     */         {
/*  79 */           return null;
/*     */         }
/*     */       }
/*  82 */       else if (getSlot(0).isItemValid(var5)) {
/*     */         
/*  84 */         if (!mergeItemStack(var5, 0, 1, false))
/*     */         {
/*  86 */           return null;
/*     */         }
/*     */       }
/*  89 */       else if (this.field_111243_a.getSizeInventory() <= 2 || !mergeItemStack(var5, 2, this.field_111243_a.getSizeInventory(), false)) {
/*     */         
/*  91 */         return null;
/*     */       } 
/*     */       
/*  94 */       if (var5.stackSize == 0) {
/*     */         
/*  96 */         var4.putStack((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/* 100 */         var4.onSlotChanged();
/*     */       } 
/*     */     } 
/*     */     
/* 104 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
/* 112 */     super.onContainerClosed(par1EntityPlayer);
/* 113 */     this.field_111243_a.closeChest();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerHorseInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */