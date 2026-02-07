/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public abstract class MITEContainerCrafting
/*     */   extends Container
/*     */ {
/*   7 */   public InventoryCrafting craft_matrix = new InventoryCrafting(this, getMatrixSize(), getMatrixSize());
/*   8 */   public IInventory craft_result = new InventoryCraftResult();
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftingResult current_crafting_result;
/*     */ 
/*     */ 
/*     */   
/*     */   private CraftingResult previous_crafting_result;
/*     */ 
/*     */ 
/*     */   
/*     */   public MITEContainerCrafting(EntityPlayer player) {
/*  21 */     super(player);
/*     */     
/*  23 */     createSlots();
/*     */     
/*  25 */     onCraftMatrixChanged(this.craft_matrix);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getMatrixSize();
/*     */   
/*     */   public abstract void createSlots();
/*     */   
/*     */   private SlotCrafting getCraftingSlot() {
/*  34 */     return (SlotCrafting)getSlot(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory par1IInventory) {
/*  39 */     this.current_crafting_result = CraftingManager.getInstance().findMatchingRecipe(this.craft_matrix, this.player.worldObj, this.player);
/*     */     
/*  41 */     if (!CraftingResult.haveEquivalentItemStacks(this.current_crafting_result, this.previous_crafting_result)) {
/*     */       
/*  43 */       this.player.clearCrafting();
/*  44 */       getCraftingSlot().setInitialItemStack(this.player, this);
/*     */     } 
/*     */     
/*  47 */     this.previous_crafting_result = this.current_crafting_result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer entity_player) {
/*  52 */     this.player.clearCrafting();
/*     */     
/*  54 */     super.onContainerClosed(entity_player);
/*     */     
/*  56 */     if (!this.world.isRemote)
/*     */     {
/*  58 */       for (int var2 = 0; var2 < this.craft_matrix.getSizeInventory(); var2++) {
/*     */         
/*  60 */         ItemStack var3 = this.craft_matrix.getStackInSlotOnClosing(var2);
/*     */         
/*  62 */         if (var3 != null)
/*     */         {
/*  64 */           entity_player.dropPlayerItem(var3);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  69 */     this.craft_result.setInventorySlotContents(0, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract boolean canInteractWith(EntityPlayer paramEntityPlayer);
/*     */   
/*     */   public abstract ItemStack transferStackInSlot(EntityPlayer paramEntityPlayer, int paramInt);
/*     */   
/*     */   public boolean func_94530_a(ItemStack item_stack, Slot slot) {
/*  78 */     return (slot.inventory != this.craft_result && super.func_94530_a(item_stack, slot));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  83 */     if (this.player instanceof EntityOtherPlayerMP) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  88 */     onCraftMatrixChanged((IInventory)null);
/*     */     
/*  90 */     SlotCrafting crafting_slot = getCraftingSlot();
/*     */     
/*  92 */     if (crafting_slot.checkCraftingResultIndex(this.player)) {
/*  93 */       this.player.clearCrafting();
/*     */     }
/*  95 */     if (this.player instanceof EntityClientPlayerMP) {
/*  96 */       (this.player.getAsEntityClientPlayerMP()).crafting_experience_cost_tentative = 0;
/*     */     }
/*  98 */     if (!crafting_slot.canPlayerCraftItem(this.player)) {
/*     */       
/* 100 */       if (this.player instanceof EntityClientPlayerMP && this.crafting_result_shown_but_prevented) {
/* 101 */         (this.player.getAsEntityClientPlayerMP()).crafting_experience_cost_tentative = (this.player.getAsEntityClientPlayerMP()).crafting_experience_cost;
/*     */       }
/* 103 */       this.player.clearCrafting();
/*     */     } 
/*     */     
/* 106 */     if (this.player.worldObj.isRemote) {
/*     */       
/* 108 */       EntityClientPlayerMP player = (EntityClientPlayerMP)this.player;
/*     */       
/* 110 */       if (player.crafting_proceed && player.hasFoodEnergy()) {
/*     */         
/* 112 */         ItemStack item_stack = crafting_slot.getStack();
/*     */         
/* 114 */         if (item_stack.getItem().hasCraftingEffect() && player.ticksExisted % 5 == 0 && player.rand.nextInt(5) == 0) {
/* 115 */           player.sendPacket(new Packet90BroadcastToAssociatedPlayers((new Packet85SimpleSignal(EnumSignal.entity_fx, EnumEntityFX.crafting)).setEntityID(player.entityId).setShort(item_stack.itemID), false));
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 120 */       if (player.crafting_proceed && player.hasFoodEnergy() && ++player.crafting_ticks >= player.crafting_period) {
/*     */         
/* 122 */         ItemStack item_stack = crafting_slot.getStack();
/*     */         
/* 124 */         int crafting_experience_cost = player.crafting_experience_cost;
/*     */         
/* 126 */         recordSlotStackSizes();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 132 */         crafting_slot.onPickupFromSlot(player, item_stack);
/*     */         
/* 134 */         lockSlotsThatChanged();
/*     */         
/* 136 */         Minecraft.theMinecraft.thePlayer.sendQueue.addToSendQueue((new Packet85SimpleSignal(EnumSignal.crafting_completed)).setInteger(crafting_experience_cost));
/*     */         
/* 138 */         if (crafting_experience_cost > 0) {
/* 139 */           player.crafting_proceed = false;
/*     */         }
/* 141 */         player.crafting_ticks = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRecipeForbidden(IRecipe recipe) {
/* 152 */     if (recipe.getRecipeOutput().getItem() instanceof ItemCoin && !this.world.areCoinsEnabled()) {
/* 153 */       return true;
/*     */     }
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCraftingResultForbidden(CraftingResult crafting_result) {
/* 160 */     ItemStack item_stack = crafting_result.item_stack;
/*     */     
/* 162 */     if (ItemMap.isBeingExtended(item_stack) && !ItemMap.isAnotherMapIdAvailable(this.world)) {
/* 163 */       return true;
/*     */     }
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe getRecipe() {
/* 170 */     return (this.current_crafting_result == null) ? null : this.current_crafting_result.recipe;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MITEContainerCrafting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */