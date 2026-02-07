/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerBeacon
/*     */   extends Container
/*     */ {
/*     */   private TileEntityBeacon theBeacon;
/*     */   private final SlotBeacon beaconSlot;
/*     */   private int field_82865_g;
/*     */   private int field_82867_h;
/*     */   private int field_82868_i;
/*     */   
/*     */   public ContainerBeacon(EntityPlayer player, TileEntityBeacon par2TileEntityBeacon) {
/*  18 */     super(player);
/*     */     
/*  20 */     this.theBeacon = par2TileEntityBeacon;
/*  21 */     addSlotToContainer(this.beaconSlot = new SlotBeacon(this, par2TileEntityBeacon, 0, 136, 110));
/*  22 */     byte var3 = 36;
/*  23 */     short var4 = 137;
/*     */     
/*     */     int var5;
/*  26 */     for (var5 = 0; var5 < 3; var5++) {
/*     */       
/*  28 */       for (int var6 = 0; var6 < 9; var6++)
/*     */       {
/*     */         
/*  31 */         addSlotToContainer(new Slot(player.inventory, var6 + var5 * 9 + 9, var3 + var6 * 18, var4 + var5 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  35 */     for (var5 = 0; var5 < 9; var5++)
/*     */     {
/*     */       
/*  38 */       addSlotToContainer(new Slot(player.inventory, var5, var3 + var5 * 18, 58 + var4));
/*     */     }
/*     */     
/*  41 */     this.field_82865_g = par2TileEntityBeacon.getLevels();
/*  42 */     this.field_82867_h = par2TileEntityBeacon.getPrimaryEffect();
/*  43 */     this.field_82868_i = par2TileEntityBeacon.getSecondaryEffect();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCraftingToCrafters(ICrafting par1ICrafting) {
/*  48 */     super.addCraftingToCrafters(par1ICrafting);
/*  49 */     par1ICrafting.sendProgressBarUpdate(this, 0, this.field_82865_g);
/*  50 */     par1ICrafting.sendProgressBarUpdate(this, 1, this.field_82867_h);
/*  51 */     par1ICrafting.sendProgressBarUpdate(this, 2, this.field_82868_i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int par1, int par2) {
/*  56 */     if (par1 == 0)
/*     */     {
/*  58 */       this.theBeacon.setLevels(par2);
/*     */     }
/*     */     
/*  61 */     if (par1 == 1)
/*     */     {
/*  63 */       this.theBeacon.setPrimaryEffect(par2);
/*     */     }
/*     */     
/*  66 */     if (par1 == 2)
/*     */     {
/*  68 */       this.theBeacon.setSecondaryEffect(par2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntityBeacon getBeacon() {
/*  77 */     return this.theBeacon;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/*  82 */     return this.theBeacon.isUseableByPlayer(par1EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/*  90 */     ItemStack var3 = null;
/*  91 */     Slot var4 = this.inventorySlots.get(par2);
/*     */     
/*  93 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  95 */       ItemStack var5 = var4.getStack();
/*  96 */       var3 = var5.copy();
/*     */       
/*  98 */       if (par2 == 0) {
/*     */         
/* 100 */         if (!mergeItemStack(var5, 1, 37, true))
/*     */         {
/* 102 */           return null;
/*     */         }
/*     */         
/* 105 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 107 */       else if (!this.beaconSlot.getHasStack() && this.beaconSlot.isItemValid(var5) && var5.stackSize == 1) {
/*     */         
/* 109 */         if (!mergeItemStack(var5, 0, 1, false))
/*     */         {
/* 111 */           return null;
/*     */         }
/*     */       }
/* 114 */       else if (par2 >= 1 && par2 < 28) {
/*     */         
/* 116 */         if (!mergeItemStack(var5, 28, 37, false))
/*     */         {
/* 118 */           return null;
/*     */         }
/*     */       }
/* 121 */       else if (par2 >= 28 && par2 < 37) {
/*     */         
/* 123 */         if (!mergeItemStack(var5, 1, 28, false))
/*     */         {
/* 125 */           return null;
/*     */         }
/*     */       }
/* 128 */       else if (!mergeItemStack(var5, 1, 37, false)) {
/*     */         
/* 130 */         return null;
/*     */       } 
/*     */       
/* 133 */       if (var5.stackSize == 0) {
/*     */         
/* 135 */         var4.putStack((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/* 139 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 142 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 144 */         return null;
/*     */       }
/*     */       
/* 147 */       var4.onPickupFromSlot(par1EntityPlayer, var5);
/*     */     } 
/*     */     
/* 150 */     return var3;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerBeacon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */