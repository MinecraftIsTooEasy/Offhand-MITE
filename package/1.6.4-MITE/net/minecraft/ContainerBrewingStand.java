/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerBrewingStand
/*     */   extends Container
/*     */ {
/*     */   private TileEntityBrewingStand tileBrewingStand;
/*     */   private final Slot theSlot;
/*     */   private int brewTime;
/*     */   
/*     */   public ContainerBrewingStand(EntityPlayer player, TileEntityBrewingStand par2TileEntityBrewingStand) {
/*  14 */     super(player);
/*     */     
/*  16 */     this.tileBrewingStand = par2TileEntityBrewingStand;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  22 */     addSlotToContainer(new SlotBrewingStandPotion(player, par2TileEntityBrewingStand, 0, 56, 46));
/*  23 */     addSlotToContainer(new SlotBrewingStandPotion(player, par2TileEntityBrewingStand, 1, 79, 53));
/*  24 */     addSlotToContainer(new SlotBrewingStandPotion(player, par2TileEntityBrewingStand, 2, 102, 46));
/*     */     
/*  26 */     this.theSlot = addSlotToContainer(new SlotBrewingStandIngredient(this, par2TileEntityBrewingStand, 3, 79, 17));
/*     */     
/*     */     int var3;
/*  29 */     for (var3 = 0; var3 < 3; var3++) {
/*     */       
/*  31 */       for (int var4 = 0; var4 < 9; var4++)
/*     */       {
/*     */         
/*  34 */         addSlotToContainer(new Slot(player.inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  38 */     for (var3 = 0; var3 < 9; var3++)
/*     */     {
/*     */       
/*  41 */       addSlotToContainer(new Slot(player.inventory, var3, 8 + var3 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCraftingToCrafters(ICrafting par1ICrafting) {
/*  47 */     super.addCraftingToCrafters(par1ICrafting);
/*  48 */     par1ICrafting.sendProgressBarUpdate(this, 0, this.tileBrewingStand.getBrewTime());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/*  56 */     super.detectAndSendChanges();
/*     */     
/*  58 */     for (int var1 = 0; var1 < this.crafters.size(); var1++) {
/*     */       
/*  60 */       ICrafting var2 = this.crafters.get(var1);
/*     */       
/*  62 */       if (this.brewTime != this.tileBrewingStand.getBrewTime()) {
/*     */         
/*  64 */         var2.sendProgressBarUpdate(this, 0, this.tileBrewingStand.getBrewTime());
/*     */         
/*  66 */         if (this.brewTime == 0) {
/*  67 */           this.tileBrewingStand.worldObj.playSoundEffect((this.tileBrewingStand.xCoord + 0.5F), (this.tileBrewingStand.yCoord + 0.5F), (this.tileBrewingStand.zCoord + 0.5F), "imported.random.boil", 0.2F);
/*     */         }
/*     */       } 
/*     */     } 
/*  71 */     this.brewTime = this.tileBrewingStand.getBrewTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int par1, int par2) {
/*  76 */     if (par1 == 0)
/*     */     {
/*  78 */       this.tileBrewingStand.setBrewTime(par2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/*  84 */     return this.tileBrewingStand.isUseableByPlayer(par1EntityPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlayerAddIngredients() {
/*  89 */     return (!this.player.worldObj.areSkillsEnabled() || this.player.hasSkill(Skill.BREWING));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/*  97 */     ItemStack var3 = null;
/*  98 */     Slot var4 = this.inventorySlots.get(par2);
/*     */     
/* 100 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/* 102 */       ItemStack var5 = var4.getStack();
/* 103 */       var3 = var5.copy();
/*     */       
/* 105 */       if ((par2 < 0 || par2 > 2) && par2 != 3) {
/*     */ 
/*     */         
/* 108 */         if (!this.theSlot.getHasStack() && this.theSlot.isItemValid(var5) && canPlayerAddIngredients())
/*     */         {
/* 110 */           if (!mergeItemStack(var5, 3, 4, false))
/*     */           {
/* 112 */             return null;
/*     */           }
/*     */         }
/* 115 */         else if (SlotBrewingStandPotion.canHoldPotion(var3))
/*     */         {
/* 117 */           if (!mergeItemStack(var5, 0, 3, false))
/*     */           {
/* 119 */             return null;
/*     */           }
/*     */         }
/* 122 */         else if (par2 >= 4 && par2 < 31)
/*     */         {
/* 124 */           if (!mergeItemStack(var5, 31, 40, false))
/*     */           {
/* 126 */             return null;
/*     */           }
/*     */         }
/* 129 */         else if (par2 >= 31 && par2 < 40)
/*     */         {
/* 131 */           if (!mergeItemStack(var5, 4, 31, false))
/*     */           {
/* 133 */             return null;
/*     */           }
/*     */         }
/* 136 */         else if (!mergeItemStack(var5, 4, 40, false))
/*     */         {
/* 138 */           return null;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 143 */         if (!mergeItemStack(var5, 4, 40, true))
/*     */         {
/* 145 */           return null;
/*     */         }
/*     */         
/* 148 */         var4.onSlotChange(var5, var3);
/*     */       } 
/*     */       
/* 151 */       if (var5.stackSize == 0) {
/*     */         
/* 153 */         var4.putStack((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/* 157 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 160 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 162 */         return null;
/*     */       }
/*     */       
/* 165 */       var4.onPickupFromSlot(par1EntityPlayer, var5);
/*     */     } 
/*     */     
/* 168 */     return var3;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerBrewingStand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */