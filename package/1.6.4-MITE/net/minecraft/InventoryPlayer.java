/*      */ package net.minecraft;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class InventoryPlayer
/*      */   implements IInventory
/*      */ {
/*    8 */   public ItemStack[] mainInventory = new ItemStack[36];
/*      */ 
/*      */   
/*   11 */   public ItemStack[] armorInventory = new ItemStack[4];
/*      */ 
/*      */   
/*      */   public int currentItem;
/*      */ 
/*      */   
/*      */   private ItemStack currentItemStack;
/*      */ 
/*      */   
/*      */   public EntityPlayer player;
/*      */ 
/*      */   
/*      */   private ItemStack itemStack;
/*      */ 
/*      */   
/*      */   public boolean inventoryChanged;
/*      */ 
/*      */ 
/*      */   
/*      */   public InventoryPlayer(EntityPlayer par1EntityPlayer) {
/*   31 */     this.player = par1EntityPlayer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getCurrentItemStack() {
/*   44 */     ItemStack item_stack = (this.currentItem < 9 && this.currentItem >= 0) ? this.mainInventory[this.currentItem] : null;
/*      */     
/*   46 */     if (this.player.isLocalClient())
/*      */     {
/*   48 */       if (Minecraft.inDevMode())
/*      */       {
/*   50 */         if (item_stack == null) {
/*   51 */           Debug.equipped_item_info = "No item equipped";
/*   52 */         } else if (item_stack.isItemStackDamageable()) {
/*   53 */           Debug.equipped_item_info = "Equipped item durability=" + item_stack.getRemainingDurability() + "/" + item_stack.getItem().getMaxDamage(item_stack);
/*      */         } else {
/*   55 */           Debug.equipped_item_info = "";
/*      */         } 
/*      */       }
/*      */     }
/*      */     
/*   60 */     return item_stack;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getHotbarSize() {
/*   68 */     return 9;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canCompletelyMergeWithExistingItemStacks(ItemStack item_stack) {
/*   73 */     int quantity_remaining = item_stack.stackSize;
/*      */     
/*   75 */     for (int i = 0; i < this.mainInventory.length; i++) {
/*      */       
/*   77 */       ItemStack slot_item_stack = this.mainInventory[i];
/*      */ 
/*      */       
/*   80 */       if (ItemStack.areItemStacksEqual(slot_item_stack, item_stack, true, false, false, true)) {
/*      */         
/*   82 */         quantity_remaining -= slot_item_stack.getMaxStackSize() - slot_item_stack.stackSize;
/*      */         
/*   84 */         if (quantity_remaining <= 0) {
/*   85 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*   89 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasEmptyMainInventorySlot() {
/*   94 */     for (int i = 0; i < this.mainInventory.length; i++) {
/*      */       
/*   96 */       if (this.mainInventory[i] == null) {
/*   97 */         return true;
/*      */       }
/*      */     } 
/*  100 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInventorySlotContainItem(int par1, boolean include_hotbar) {
/*  110 */     for (int var2 = include_hotbar ? 0 : getHotbarSize(); var2 < this.mainInventory.length; var2++) {
/*      */       
/*  112 */       if (this.mainInventory[var2] != null && (this.mainInventory[var2]).itemID == par1)
/*      */       {
/*  114 */         return var2;
/*      */       }
/*      */     } 
/*      */     
/*  118 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getSimilarityOfItems(Item item, int item_subtype, ItemStack item_stack) {
/*  123 */     if (item_stack == null || item_stack.stackSize == 0) {
/*  124 */       return 0;
/*      */     }
/*      */     
/*  127 */     if (item instanceof ItemBlock && ((ItemBlock)item).getBlock() instanceof BlockGravel && item_stack.getItem() instanceof ItemShovel) {
/*  128 */       return 1;
/*      */     }
/*  130 */     if (item.getHasSubtypes() && item_subtype != item_stack.getItemSubtype()) {
/*  131 */       return 0;
/*      */     }
/*  133 */     return (item_stack == null || item_stack.stackSize == 0) ? 0 : ((item.itemID == item_stack.itemID) ? 100 : MathHelper.clamp_int(item.getSimilarityToItem(item_stack.getItem()), 0, 99));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNextHotbarOrInventorySlotContainingMostSimilarItem(Item item, int item_subtype, int hotbar_slot_index) {
/*  138 */     if (item == null) {
/*  139 */       return -1;
/*      */     }
/*      */     
/*  142 */     int minimum_similarity = 1;
/*      */     
/*  144 */     int most_similar_item_slot_index = -1;
/*  145 */     int closest_similarity = 0;
/*      */     
/*      */     int i;
/*      */     
/*  149 */     for (i = hotbar_slot_index; i < getHotbarSize(); i++) {
/*      */       
/*  151 */       int similarity = getSimilarityOfItems(item, item_subtype, this.mainInventory[i]);
/*      */       
/*  153 */       if (similarity >= minimum_similarity && similarity > closest_similarity) {
/*      */         
/*  155 */         most_similar_item_slot_index = i;
/*  156 */         closest_similarity = similarity;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  162 */     for (i = 0; i < hotbar_slot_index; i++) {
/*      */       
/*  164 */       int similarity = getSimilarityOfItems(item, item_subtype, this.mainInventory[i]);
/*      */       
/*  166 */       if (similarity >= minimum_similarity && similarity > closest_similarity) {
/*      */         
/*  168 */         most_similar_item_slot_index = i;
/*  169 */         closest_similarity = similarity;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  175 */     for (i = getHotbarSize(); i < this.mainInventory.length; i++) {
/*      */       
/*  177 */       int similarity = getSimilarityOfItems(item, item_subtype, this.mainInventory[i]);
/*      */       
/*  179 */       if (similarity >= minimum_similarity && similarity > closest_similarity) {
/*      */         
/*  181 */         most_similar_item_slot_index = i;
/*  182 */         closest_similarity = similarity;
/*      */       } 
/*      */     } 
/*      */     
/*  186 */     return most_similar_item_slot_index;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean trySwitchItemOrRestock(Item previous_item, int item_subtype, boolean immediately) {
/*  191 */     if (!this.player.worldObj.isRemote) {
/*      */       
/*  193 */       Minecraft.setErrorMessage("trySwitchItemOrRestock is only meant to be called on client");
/*  194 */       return false;
/*      */     } 
/*      */     
/*  197 */     if (previous_item == null) {
/*      */       
/*  199 */       Minecraft.setErrorMessage("trySwitchItemOrRestock: previous_item is null");
/*  200 */       return false;
/*      */     } 
/*      */     
/*  203 */     PlayerControllerMP player_controller = Minecraft.theMinecraft.playerController;
/*      */     
/*  205 */     if (immediately) {
/*      */       
/*  207 */       player_controller.item_switch_or_restock_pending = false;
/*      */       
/*  209 */       int slot_index = getNextHotbarOrInventorySlotContainingMostSimilarItem(previous_item, item_subtype, this.currentItem);
/*      */       
/*  211 */       if (slot_index == -1) {
/*  212 */         return false;
/*      */       }
/*  214 */       if (slot_index == this.currentItem) {
/*  215 */         return false;
/*      */       }
/*  217 */       if (slot_index < getHotbarSize()) {
/*      */         
/*  219 */         this.currentItem = slot_index;
/*  220 */         ((EntityClientPlayerMP)this.player).change_rendering_for_item_equipping = true;
/*      */         
/*  222 */         player_controller.syncCurrentPlayItem();
/*      */         
/*  224 */         return true;
/*      */       } 
/*      */ 
/*      */       
/*  228 */       if (!player_controller.autoStockEnabled()) {
/*  229 */         return false;
/*      */       }
/*  231 */       if (this.mainInventory[this.currentItem] != null) {
/*  232 */         return false;
/*      */       }
/*  234 */       ItemStack item_stack = getStackInSlot(slot_index);
/*      */       
/*  236 */       setInventorySlotContents(this.currentItem, item_stack);
/*  237 */       setInventorySlotContents(slot_index, null);
/*      */       
/*  239 */       player_controller.netClientHandler.addToSendQueue(new Packet5PlayerInventory(this.player.entityId, this.currentItem, item_stack));
/*  240 */       player_controller.netClientHandler.addToSendQueue(new Packet5PlayerInventory(this.player.entityId, slot_index, null));
/*      */       
/*  242 */       player_controller.setLastUsedItem(null, 0);
/*      */       
/*  244 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  249 */     player_controller.setLastUsedItem(previous_item, item_subtype);
/*  250 */     player_controller.item_switch_or_restock_pending = true;
/*      */     
/*  252 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHotbarSlotContainArrow() {
/*  258 */     for (int var2 = 0; var2 < getHotbarSize(); var2++) {
/*      */       
/*  260 */       if (this.mainInventory[var2] != null && this.mainInventory[var2].getItem() instanceof ItemArrow && (this.mainInventory[var2]).stackSize > 0) {
/*  261 */         return var2;
/*      */       }
/*      */     } 
/*  264 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHotbarSlotContainItem(Item item) {
/*  270 */     for (int i = 0; i < getHotbarSize(); i++) {
/*      */       
/*  272 */       ItemStack item_stack = this.mainInventory[i];
/*      */       
/*  274 */       if (item_stack != null && item_stack.stackSize >= 1)
/*      */       {
/*      */         
/*  277 */         if (item_stack.getItem() == item)
/*  278 */           return i; 
/*      */       }
/*      */     } 
/*  281 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemArrow getReadiedArrow() {
/*  286 */     int slot_index = getHotbarSlotContainArrow();
/*      */     
/*  288 */     if (slot_index < 0) {
/*  289 */       return null;
/*      */     }
/*  291 */     ItemStack item_stack = this.mainInventory[slot_index];
/*      */     
/*  293 */     if (item_stack == null) {
/*  294 */       return null;
/*      */     }
/*  296 */     return (ItemArrow)item_stack.getItem();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getInventorySlotContainingItemAndSubtype(int id, int subtype) {
/*  315 */     for (int i = 0; i < this.mainInventory.length; i++) {
/*      */       
/*  317 */       if (this.mainInventory[i] != null && (this.mainInventory[i]).itemID == id && this.mainInventory[i].getItemSubtype() == subtype) {
/*  318 */         return i;
/*      */       }
/*      */     } 
/*  321 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int storeItemStack(ItemStack par1ItemStack) {
/*  342 */     int inventory_stack_limit = getInventoryStackLimit();
/*      */     
/*  344 */     for (int i = 0; i < this.mainInventory.length; i++) {
/*      */       
/*  346 */       ItemStack item_stack = this.mainInventory[i];
/*      */       
/*  348 */       if (item_stack != null && item_stack.isStackable() && item_stack.stackSize < item_stack.getMaxStackSize() && item_stack.stackSize < inventory_stack_limit && ItemStack.areItemStacksEqual(item_stack, par1ItemStack, true)) {
/*  349 */         return i;
/*      */       }
/*      */     } 
/*  352 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstEmptyStack() {
/*  360 */     for (int var1 = 0; var1 < this.mainInventory.length; var1++) {
/*      */       
/*  362 */       if (this.mainInventory[var1] == null)
/*      */       {
/*  364 */         return var1;
/*      */       }
/*      */     } 
/*      */     
/*  368 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurrentItem(int par1, int par2, boolean par3, boolean par4) {
/*      */     int var7;
/*  376 */     boolean var5 = true;
/*  377 */     this.currentItemStack = getCurrentItemStack();
/*      */ 
/*      */     
/*  380 */     if (par3) {
/*      */ 
/*      */       
/*  383 */       var7 = getInventorySlotContainingItemAndSubtype(par1, par2);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  388 */       var7 = getInventorySlotContainItem(par1, true);
/*      */     } 
/*      */     
/*  391 */     if (var7 >= 0 && var7 < 9) {
/*      */       
/*  393 */       this.currentItem = var7;
/*      */ 
/*      */     
/*      */     }
/*  397 */     else if (par4 && par1 > 0) {
/*      */       
/*  399 */       int var6 = getFirstEmptyStack();
/*      */       
/*  401 */       if (var6 >= 0 && var6 < 9)
/*      */       {
/*  403 */         this.currentItem = var6;
/*      */       }
/*      */       
/*  406 */       func_70439_a(Item.itemsList[par1], par2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void changeCurrentItem(int par1) {
/*  416 */     if (par1 > 0)
/*      */     {
/*  418 */       par1 = 1;
/*      */     }
/*      */     
/*  421 */     if (par1 < 0)
/*      */     {
/*  423 */       par1 = -1;
/*      */     }
/*      */     
/*  426 */     int previous_current_item = this.currentItem;
/*      */     
/*  428 */     for (this.currentItem -= par1; this.currentItem < 0; this.currentItem += 9);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  433 */     while (this.currentItem >= 9)
/*      */     {
/*  435 */       this.currentItem -= 9;
/*      */     }
/*      */     
/*  438 */     if (this.currentItem == previous_current_item) {
/*      */       return;
/*      */     }
/*  441 */     if (this.player.worldObj.isRemote) {
/*      */       
/*  443 */       Minecraft.theMinecraft.playerController.setLastUsedItem(null, 0);
/*  444 */       Minecraft.theMinecraft.playerController.clearAutoHarvestMode();
/*  445 */       Minecraft.theMinecraft.playerController.clearAutoUseMode();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int clearInventory(int par1, int par2) {
/*  454 */     int var3 = 0;
/*      */     
/*      */     int var4;
/*      */     
/*  458 */     for (var4 = 0; var4 < this.mainInventory.length; var4++) {
/*      */       
/*  460 */       ItemStack var5 = this.mainInventory[var4];
/*      */       
/*  462 */       if (var5 != null && (par1 <= -1 || var5.itemID == par1) && (par2 <= -1 || var5.getItemSubtype() == par2)) {
/*      */         
/*  464 */         var3 += var5.stackSize;
/*  465 */         this.mainInventory[var4] = null;
/*      */       } 
/*      */     } 
/*      */     
/*  469 */     for (var4 = 0; var4 < this.armorInventory.length; var4++) {
/*      */       
/*  471 */       ItemStack var5 = this.armorInventory[var4];
/*      */       
/*  473 */       if (var5 != null && (par1 <= -1 || var5.itemID == par1) && (par2 <= -1 || var5.getItemSubtype() == par2)) {
/*      */         
/*  475 */         var3 += var5.stackSize;
/*  476 */         this.armorInventory[var4] = null;
/*      */       } 
/*      */     } 
/*      */     
/*  480 */     if (this.itemStack != null) {
/*      */       
/*  482 */       if (par1 > -1 && this.itemStack.itemID != par1)
/*      */       {
/*  484 */         return var3;
/*      */       }
/*      */       
/*  487 */       if (par2 > -1 && this.itemStack.getItemSubtype() != par2)
/*      */       {
/*  489 */         return var3;
/*      */       }
/*      */       
/*  492 */       var3 += this.itemStack.stackSize;
/*  493 */       setItemStack((ItemStack)null);
/*      */     } 
/*      */     
/*  496 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70439_a(Item par1Item, int par2) {
/*  501 */     if (par1Item != null) {
/*      */ 
/*      */ 
/*      */       
/*  505 */       if (this.currentItemStack != null && this.currentItemStack.isEnchantable() && getInventorySlotContainingItemAndSubtype(this.currentItemStack.itemID, this.currentItemStack.getItemDamageForDisplay()) == this.currentItem) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  511 */       int var3 = getInventorySlotContainingItemAndSubtype(par1Item.itemID, par2);
/*      */       
/*  513 */       if (var3 >= 0) {
/*      */         
/*  515 */         int var4 = (this.mainInventory[var3]).stackSize;
/*      */         
/*  517 */         setInventorySlotContents(var3, this.mainInventory[this.currentItem]);
/*      */         
/*  519 */         setInventorySlotContents(this.currentItem, new ItemStack(Item.itemsList[par1Item.itemID], var4, par2));
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  524 */         setInventorySlotContents(this.currentItem, new ItemStack(Item.itemsList[par1Item.itemID], 1, par2));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int storePartialItemStack(ItemStack par1ItemStack) {
/*  613 */     if (par1ItemStack.stackSize == 0) {
/*  614 */       return 0;
/*      */     }
/*  616 */     int item_id = par1ItemStack.itemID;
/*  617 */     int stack_size = par1ItemStack.stackSize;
/*      */ 
/*      */     
/*  620 */     if (par1ItemStack.getMaxStackSize() == 1) {
/*      */       
/*  622 */       int i = getFirstEmptyStack();
/*      */       
/*  624 */       if (i < 0)
/*      */       {
/*  626 */         return stack_size;
/*      */       }
/*      */ 
/*      */       
/*  630 */       if (this.mainInventory[i] == null) {
/*  631 */         setInventorySlotContents(i, par1ItemStack.copy());
/*      */       }
/*  633 */       return 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  638 */     int slot_index = storeItemStack(par1ItemStack);
/*      */     
/*  640 */     if (slot_index < 0)
/*      */     {
/*  642 */       slot_index = getFirstEmptyStack();
/*      */     }
/*      */     
/*  645 */     if (slot_index < 0)
/*      */     {
/*  647 */       return stack_size;
/*      */     }
/*      */ 
/*      */     
/*  651 */     if (this.mainInventory[slot_index] == null) {
/*      */       
/*  653 */       setInventorySlotContents(slot_index, par1ItemStack.copy());
/*  654 */       return 0;
/*      */     } 
/*      */     
/*  657 */     int var5 = stack_size;
/*      */     
/*  659 */     if (stack_size > this.mainInventory[slot_index].getMaxStackSize() - (this.mainInventory[slot_index]).stackSize)
/*      */     {
/*  661 */       var5 = this.mainInventory[slot_index].getMaxStackSize() - (this.mainInventory[slot_index]).stackSize;
/*      */     }
/*      */     
/*  664 */     if (var5 > getInventoryStackLimit() - (this.mainInventory[slot_index]).stackSize)
/*      */     {
/*  666 */       var5 = getInventoryStackLimit() - (this.mainInventory[slot_index]).stackSize;
/*      */     }
/*      */     
/*  669 */     if (var5 == 0) {
/*      */       
/*  671 */       if (!this.player.worldObj.isRemote) {
/*  672 */         inventorySlotChangedOnServer(slot_index);
/*      */       }
/*  674 */       return stack_size;
/*      */     } 
/*      */ 
/*      */     
/*  678 */     stack_size -= var5;
/*  679 */     (this.mainInventory[slot_index]).stackSize += var5;
/*  680 */     (this.mainInventory[slot_index]).animationsToGo = 5;
/*      */     
/*  682 */     if (!this.player.worldObj.isRemote) {
/*  683 */       inventorySlotChangedOnServer(slot_index);
/*      */     }
/*  685 */     return stack_size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decrementAnimations() {
/*  697 */     for (int var1 = 0; var1 < this.mainInventory.length; var1++) {
/*      */       
/*  699 */       if (this.mainInventory[var1] != null)
/*      */       {
/*  701 */         this.mainInventory[var1].updateAnimation(this.player.worldObj, this.player, var1, (this.currentItem == var1));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean consumeInventoryItem(int par1) {
/*  712 */     int var2 = getInventorySlotContainItem(par1, true);
/*      */     
/*  714 */     if (var2 < 0)
/*      */     {
/*  716 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  720 */     if (--(this.mainInventory[var2]).stackSize <= 0)
/*      */     {
/*      */       
/*  723 */       setInventorySlotContents(var2, null);
/*      */     }
/*      */     
/*  726 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean consumeArrow() {
/*  741 */     int var2 = getHotbarSlotContainArrow();
/*      */     
/*  743 */     if (var2 < 0)
/*      */     {
/*  745 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  749 */     if (--(this.mainInventory[var2]).stackSize <= 0) {
/*      */       
/*  751 */       if (getReadiedArrow() == null) {
/*      */         
/*  753 */         int slot_index = getInventorySlotContainItem((this.mainInventory[var2]).itemID, false);
/*      */         
/*  755 */         if (slot_index != -1) {
/*      */           
/*  757 */           setInventorySlotContents(var2, getStackInSlot(slot_index));
/*  758 */           setInventorySlotContents(slot_index, null);
/*      */           
/*  760 */           return true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  765 */       setInventorySlotContents(var2, null);
/*      */     } 
/*      */     
/*  768 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasItem(int par1) {
/*  778 */     int var2 = getInventorySlotContainItem(par1, true);
/*  779 */     return (var2 >= 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addItemStackToInventoryOrDropIt(ItemStack item_stack) {
/*  785 */     if (item_stack == null || item_stack.stackSize < 1) {
/*      */       return;
/*      */     }
/*  788 */     item_stack = item_stack.copy();
/*      */     
/*  790 */     while (item_stack.stackSize > item_stack.getMaxStackSize()) {
/*      */       
/*  792 */       addItemStackToInventoryOrDropIt(item_stack.copy().setStackSize(item_stack.getMaxStackSize()));
/*  793 */       item_stack.stackSize -= item_stack.getMaxStackSize();
/*      */     } 
/*      */     
/*  796 */     if (!addItemStackToInventory(item_stack)) {
/*  797 */       this.player.dropPlayerItem(item_stack);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void inventorySlotChangedOnServer(int slot_index) {
/*  804 */     if (this.player.worldObj.isRemote) {
/*  805 */       Minecraft.setErrorMessage("inventorySlotChangedOnServer is not meant to called on client");
/*      */     } else {
/*  807 */       this.player.sendPacket((new Packet5PlayerInventory(this.player.entityId, slot_index, this.mainInventory[slot_index])).setFullInventory());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addItemStackToInventory(ItemStack par1ItemStack) {
/*  815 */     if (par1ItemStack == null)
/*      */     {
/*  817 */       return false;
/*      */     }
/*  819 */     if (par1ItemStack.stackSize == 0)
/*      */     {
/*  821 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*      */       int var2;
/*      */ 
/*      */       
/*  829 */       if (par1ItemStack.isItemDamaged()) {
/*      */         
/*  831 */         var2 = getFirstEmptyStack();
/*      */         
/*  833 */         if (var2 >= 0) {
/*      */ 
/*      */           
/*  836 */           setInventorySlotContents(var2, ItemStack.copyItemStack(par1ItemStack));
/*  837 */           (this.mainInventory[var2]).animationsToGo = 5;
/*  838 */           par1ItemStack.stackSize = 0;
/*  839 */           return true;
/*      */         } 
/*  841 */         if (this.player.capabilities.isCreativeMode) {
/*      */           
/*  843 */           par1ItemStack.stackSize = 0;
/*  844 */           return true;
/*      */         } 
/*      */ 
/*      */         
/*  848 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/*  855 */         var2 = par1ItemStack.stackSize;
/*  856 */         par1ItemStack.stackSize = storePartialItemStack(par1ItemStack);
/*      */       }
/*  858 */       while (par1ItemStack.stackSize > 0 && par1ItemStack.stackSize < var2);
/*      */       
/*  860 */       if (par1ItemStack.stackSize == var2 && this.player.capabilities.isCreativeMode) {
/*      */         
/*  862 */         par1ItemStack.stackSize = 0;
/*  863 */         return true;
/*      */       } 
/*      */ 
/*      */       
/*  867 */       return (par1ItemStack.stackSize < var2);
/*      */ 
/*      */     
/*      */     }
/*  871 */     catch (Throwable var5) {
/*      */       
/*  873 */       CrashReport var3 = CrashReport.makeCrashReport(var5, "Adding item to inventory");
/*  874 */       CrashReportCategory var4 = var3.makeCategory("Item being added");
/*  875 */       var4.addCrashSection("Item ID", Integer.valueOf(par1ItemStack.itemID));
/*      */       
/*  877 */       var4.addCrashSection("Item data", Integer.valueOf(par1ItemStack.getItemSubtype()));
/*  878 */       var4.addCrashSectionCallable("Item name", new CallableItemName(this, par1ItemStack));
/*  879 */       throw new ReportedException(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack decrStackSize(int par1, int par2) {
/*  890 */     ItemStack[] var3 = this.mainInventory;
/*      */     
/*  892 */     if (par1 >= this.mainInventory.length) {
/*      */       
/*  894 */       var3 = this.armorInventory;
/*  895 */       par1 -= this.mainInventory.length;
/*      */     } 
/*      */     
/*  898 */     if (var3[par1] != null) {
/*      */ 
/*      */ 
/*      */       
/*  902 */       if ((var3[par1]).stackSize <= par2) {
/*      */         
/*  904 */         ItemStack itemStack = var3[par1];
/*  905 */         var3[par1] = null;
/*  906 */         return itemStack;
/*      */       } 
/*      */ 
/*      */       
/*  910 */       ItemStack var4 = var3[par1].splitStack(par2);
/*      */       
/*  912 */       if ((var3[par1]).stackSize == 0)
/*      */       {
/*  914 */         var3[par1] = null;
/*      */       }
/*      */       
/*  917 */       return var4;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  922 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getStackInSlotOnClosing(int par1) {
/*  932 */     ItemStack[] var2 = this.mainInventory;
/*      */     
/*  934 */     if (par1 >= this.mainInventory.length) {
/*      */       
/*  936 */       var2 = this.armorInventory;
/*  937 */       par1 -= this.mainInventory.length;
/*      */     } 
/*      */     
/*  940 */     if (var2[par1] != null) {
/*      */       
/*  942 */       ItemStack var3 = var2[par1];
/*  943 */       var2[par1] = null;
/*  944 */       return var3;
/*      */     } 
/*      */ 
/*      */     
/*  948 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/*  957 */     ItemStack[] var3 = this.mainInventory;
/*      */     
/*  959 */     if (par1 >= var3.length) {
/*      */       
/*  961 */       par1 -= var3.length;
/*  962 */       var3 = this.armorInventory;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  967 */     if (var3[par1] != par2ItemStack) {
/*      */       
/*  969 */       var3[par1] = par2ItemStack;
/*      */ 
/*      */ 
/*      */       
/*  973 */       if (var3 == this.mainInventory && !this.player.worldObj.isRemote) {
/*  974 */         inventorySlotChangedOnServer(par1);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public ItemStack getInventorySlotContents(int index) {
/*  980 */     if (index >= this.mainInventory.length)
/*      */     {
/*  982 */       return this.armorInventory[index - this.mainInventory.length];
/*      */     }
/*      */ 
/*      */     
/*  986 */     return this.mainInventory[index];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagList writeToNBT(NBTTagList par1NBTTagList) {
/*      */     int var2;
/* 1022 */     for (var2 = 0; var2 < this.mainInventory.length; var2++) {
/*      */       
/* 1024 */       if (this.mainInventory[var2] != null) {
/*      */         
/* 1026 */         NBTTagCompound var3 = new NBTTagCompound();
/* 1027 */         var3.setByte("Slot", (byte)var2);
/* 1028 */         this.mainInventory[var2].writeToNBT(var3);
/* 1029 */         par1NBTTagList.appendTag(var3);
/*      */       } 
/*      */     } 
/*      */     
/* 1033 */     for (var2 = 0; var2 < this.armorInventory.length; var2++) {
/*      */       
/* 1035 */       if (this.armorInventory[var2] != null) {
/*      */         
/* 1037 */         NBTTagCompound var3 = new NBTTagCompound();
/* 1038 */         var3.setByte("Slot", (byte)(var2 + 100));
/* 1039 */         this.armorInventory[var2].writeToNBT(var3);
/* 1040 */         par1NBTTagList.appendTag(var3);
/*      */       } 
/*      */     } 
/*      */     
/* 1044 */     return par1NBTTagList;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readFromNBT(NBTTagList par1NBTTagList) {
/* 1052 */     this.mainInventory = new ItemStack[36];
/* 1053 */     this.armorInventory = new ItemStack[4];
/*      */     
/* 1055 */     for (int var2 = 0; var2 < par1NBTTagList.tagCount(); var2++) {
/*      */       
/* 1057 */       NBTTagCompound var3 = (NBTTagCompound)par1NBTTagList.tagAt(var2);
/* 1058 */       int var4 = var3.getByte("Slot") & 0xFF;
/* 1059 */       ItemStack var5 = ItemStack.loadItemStackFromNBT(var3);
/*      */       
/* 1061 */       if (var5 != null) {
/*      */         
/* 1063 */         if (var4 >= 0 && var4 < this.mainInventory.length)
/*      */         {
/* 1065 */           this.mainInventory[var4] = var5;
/*      */         }
/*      */         
/* 1068 */         if (var4 >= 100 && var4 < this.armorInventory.length + 100)
/*      */         {
/* 1070 */           this.armorInventory[var4 - 100] = var5;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSizeInventory() {
/* 1081 */     return this.mainInventory.length + 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getStackInSlot(int par1) {
/* 1089 */     ItemStack[] var2 = this.mainInventory;
/*      */     
/* 1091 */     if (par1 >= var2.length) {
/*      */       
/* 1093 */       par1 -= var2.length;
/* 1094 */       var2 = this.armorInventory;
/*      */     } 
/*      */     
/* 1097 */     return var2[par1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCustomNameOrUnlocalized() {
/* 1105 */     return "container.inventory";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasCustomName() {
/* 1114 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInventoryStackLimit() {
/* 1123 */     return 64;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack armorItemInSlot(int par1) {
/* 1157 */     return this.armorInventory[par1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfArmorPiecesEquipped() {
/* 1227 */     int number = 0;
/*      */     
/* 1229 */     for (int i = 0; i < this.armorInventory.length; i++) {
/*      */       
/* 1231 */       if (this.armorInventory[i] != null && this.armorInventory[i].getItem() instanceof ItemArmor) {
/* 1232 */         number++;
/*      */       }
/*      */     } 
/* 1235 */     return number;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tryDamageArmor(DamageSource damage_source, float amount, EntityDamageResult result) {
/* 1245 */     if (this.player.onClient()) {
/* 1246 */       Minecraft.setErrorMessage("InventoryPlayer.damageArmor: called on client?");
/*      */     }
/* 1248 */     if (!this.player.isWearingDamageableItems(true) || this.player.inCreativeMode()) {
/*      */       return;
/*      */     }
/* 1251 */     if (damage_source != null && damage_source.isUnblockable()) {
/*      */       return;
/*      */     }
/* 1254 */     if (result == null) {
/* 1255 */       result = new EntityDamageResult(this.player);
/*      */     }
/* 1257 */     int amount_remaining = (int)amount;
/*      */     
/* 1259 */     if (amount_remaining < 1) {
/* 1260 */       amount_remaining = 1;
/*      */     }
/* 1262 */     while (amount_remaining > 0) {
/*      */       
/* 1264 */       if (!this.player.isWearingDamageableItems(true)) {
/*      */         break;
/*      */       }
/* 1267 */       int armor_index = this.player.rand.nextInt(this.armorInventory.length);
/*      */       
/* 1269 */       ItemStack item_stack = this.armorInventory[armor_index];
/*      */       
/* 1271 */       if (item_stack != null && item_stack.getItem() instanceof ItemArmor) {
/*      */         int portion;
/*      */ 
/*      */         
/* 1275 */         if (getNumberOfArmorPiecesEquipped() == 1) {
/* 1276 */           portion = amount_remaining;
/*      */         } else {
/* 1278 */           portion = this.player.rand.nextInt(amount_remaining) + 1;
/*      */         } 
/*      */ 
/*      */         
/* 1282 */         result.applyArmorDamageResult(item_stack.tryDamageItem(damage_source, portion, this.player));
/*      */         
/* 1284 */         if (item_stack.stackSize == 0) {
/* 1285 */           this.armorInventory[armor_index] = null;
/*      */         }
/* 1287 */         amount_remaining -= portion;
/*      */         
/*      */         continue;
/*      */       } 
/* 1291 */       amount_remaining -= this.player.rand.nextInt(amount_remaining) + 1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean dropAllArmor() {
/* 1298 */     boolean armor_was_dropped = false;
/*      */     
/* 1300 */     for (int var1 = 0; var1 < this.armorInventory.length; var1++) {
/*      */       
/* 1302 */       if (this.armorInventory[var1] != null) {
/*      */         
/* 1304 */         this.player.dropPlayerItemWithNoTrajectory(this.armorInventory[var1]);
/* 1305 */         this.armorInventory[var1] = null;
/*      */         
/* 1307 */         armor_was_dropped = true;
/*      */       } 
/*      */     } 
/*      */     
/* 1311 */     return armor_was_dropped;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dropAllItems() {
/*      */     int var1;
/* 1321 */     for (var1 = 0; var1 < this.mainInventory.length; var1++) {
/*      */       
/* 1323 */       if (this.mainInventory[var1] != null) {
/*      */         
/* 1325 */         this.player.dropPlayerItemWithRandomChoice(this.mainInventory[var1], true);
/* 1326 */         this.mainInventory[var1] = null;
/*      */       } 
/*      */     } 
/*      */     
/* 1330 */     for (var1 = 0; var1 < this.armorInventory.length; var1++) {
/*      */       
/* 1332 */       if (this.armorInventory[var1] != null) {
/*      */         
/* 1334 */         this.player.dropPlayerItemWithRandomChoice(this.armorInventory[var1], true);
/* 1335 */         this.armorInventory[var1] = null;
/*      */       } 
/*      */     } 
/*      */     
/* 1339 */     this.player.sendPacket(new Packet85SimpleSignal(EnumSignal.clear_inventory));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onInventoryChanged() {
/* 1347 */     this.inventoryChanged = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setItemStack(ItemStack par1ItemStack) {
/* 1352 */     this.itemStack = par1ItemStack;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack getItemStack() {
/* 1357 */     return this.itemStack;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 1365 */     return this.player.isDead ? false : ((par1EntityPlayer.getDistanceSqToEntity(this.player) <= 64.0D));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasItemStack(ItemStack par1ItemStack) {
/*      */     int i;
/* 1396 */     for (i = 0; i < this.armorInventory.length; i++) {
/*      */       
/* 1398 */       if (ItemStack.areItemStacksEqual(this.armorInventory[i], par1ItemStack, true, false, false, true)) {
/* 1399 */         return true;
/*      */       }
/*      */     } 
/* 1402 */     for (i = 0; i < this.mainInventory.length; i++) {
/*      */       
/* 1404 */       if (ItemStack.areItemStacksEqual(this.mainInventory[i], par1ItemStack, true, false, false, true)) {
/* 1405 */         return true;
/*      */       }
/*      */     } 
/* 1408 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void openChest() {}
/*      */ 
/*      */   
/*      */   public void closeChest() {}
/*      */ 
/*      */   
/*      */   public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
/* 1420 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void copyInventory(InventoryPlayer par1InventoryPlayer) {
/*      */     int var2;
/* 1430 */     for (var2 = 0; var2 < this.mainInventory.length; var2++)
/*      */     {
/* 1432 */       this.mainInventory[var2] = ItemStack.copyItemStack(par1InventoryPlayer.mainInventory[var2]);
/*      */     }
/*      */     
/* 1435 */     for (var2 = 0; var2 < this.armorInventory.length; var2++)
/*      */     {
/* 1437 */       this.armorInventory[var2] = ItemStack.copyItemStack(par1InventoryPlayer.armorInventory[var2]);
/*      */     }
/*      */     
/* 1440 */     this.currentItem = par1InventoryPlayer.currentItem;
/*      */   }
/*      */ 
/*      */   
/*      */   public void convertOneOfCurrentItem(ItemStack created_item_stack) {
/* 1445 */     convertOneItem(this.currentItem, created_item_stack);
/*      */   }
/*      */ 
/*      */   
/*      */   public void convertOneItem(int slot_index, ItemStack created_item_stack) {
/* 1450 */     convertOneItem(slot_index, getInventorySlotContents(slot_index), created_item_stack);
/*      */   }
/*      */ 
/*      */   
/*      */   public void convertOneItem(int slot_index, ItemStack item_stack, ItemStack created_item_stack) {
/* 1455 */     if (this.player.worldObj.isRemote) {
/*      */       
/* 1457 */       Minecraft.setErrorMessage("convertOneItem: only meant to be called on server");
/*      */       
/*      */       return;
/*      */     } 
/* 1461 */     if (item_stack.stackSize == 1 && slot_index == this.currentItem)
/*      */     {
/* 1463 */       this.player.getAsEntityPlayerMP().sendTryAutoSwitchOrRestockPacket(item_stack);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1484 */     if (--item_stack.stackSize > 0 || (created_item_stack != null && canCompletelyMergeWithExistingItemStacks(created_item_stack))) {
/*      */       
/* 1486 */       if (item_stack.stackSize == 0)
/*      */       {
/* 1488 */         setInventorySlotContents(this.currentItem, null);
/*      */       }
/*      */ 
/*      */       
/* 1492 */       addItemStackToInventoryOrDropIt(created_item_stack);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1504 */       setInventorySlotContents(this.currentItem, created_item_stack);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack decrementSlotStackSize(int slot_index) {
/* 1511 */     ItemStack item_stack = this.mainInventory[slot_index];
/*      */     
/* 1513 */     if (item_stack != null && item_stack.stackSize >= 0 && --item_stack.stackSize <= 0)
/*      */     {
/* 1515 */       setInventorySlotContents(slot_index, null);
/*      */     }
/* 1517 */     return this.mainInventory[slot_index];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSlotIndex(ItemStack item_stack, ItemStack[] inventory) {
/* 1529 */     for (int i = 0; i < inventory.length; i++) {
/*      */       
/* 1531 */       if (inventory[i] == item_stack) {
/* 1532 */         return i;
/*      */       }
/*      */     } 
/* 1535 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSlotIndex(ItemStack item_stack) {
/*      */     int i;
/* 1541 */     for (i = 0; i < this.mainInventory.length; i++) {
/*      */       
/* 1543 */       if (this.mainInventory[i] == item_stack) {
/* 1544 */         return i;
/*      */       }
/*      */     } 
/* 1547 */     for (i = 0; i < this.armorInventory.length; i++) {
/*      */       
/* 1549 */       if (this.armorInventory[i] == item_stack) {
/* 1550 */         return i + this.mainInventory.length;
/*      */       }
/*      */     } 
/* 1553 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroyInventoryItemStack(int slot_index, ItemStack[] inventory) {
/* 1560 */     ItemStack item_stack = inventory[slot_index];
/*      */     
/* 1562 */     if (item_stack == null) {
/*      */       return;
/*      */     }
/* 1565 */     if (inventory == this.armorInventory) {
/* 1566 */       slot_index += this.mainInventory.length;
/*      */     }
/* 1568 */     setInventorySlotContents(slot_index, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroyInventoryItemStack(ItemStack item_stack) {
/* 1577 */     if (item_stack == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1589 */     int slot_index = getSlotIndex(item_stack, this.mainInventory);
/*      */     
/* 1591 */     if (slot_index >= 0) {
/*      */       
/* 1593 */       destroyInventoryItemStack(slot_index, this.mainInventory);
/*      */       
/*      */       return;
/*      */     } 
/* 1597 */     slot_index = getSlotIndex(item_stack, this.armorInventory);
/*      */     
/* 1599 */     if (slot_index >= 0) {
/* 1600 */       destroyInventoryItemStack(slot_index, this.armorInventory);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroyCurrentItemStack() {
/* 1608 */     destroyInventoryItemStack(this.currentItem, this.mainInventory);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int calcChecksum(int for_release_number) {
/* 1617 */     int checksum = 0;
/*      */     int i;
/* 1619 */     for (i = 0; i < this.mainInventory.length; i++) {
/*      */       
/* 1621 */       ItemStack item_stack = this.mainInventory[i];
/*      */       
/* 1623 */       if (item_stack != null) {
/* 1624 */         checksum += item_stack.calcChecksum(for_release_number);
/*      */       }
/*      */     } 
/* 1627 */     for (i = 0; i < this.armorInventory.length; i++) {
/*      */       
/* 1629 */       ItemStack item_stack = this.armorInventory[i];
/*      */       
/* 1631 */       if (item_stack != null) {
/* 1632 */         checksum += item_stack.calcChecksum(for_release_number);
/*      */       }
/*      */     } 
/* 1635 */     return checksum;
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropItem(int slot_index, int quantity) {
/* 1640 */     if (slot_index < 0 || slot_index >= this.mainInventory.length) {
/*      */       return;
/*      */     }
/* 1643 */     ItemStack item_stack = decrStackSize(slot_index, quantity);
/*      */     
/* 1645 */     if (!this.player.worldObj.isRemote) {
/* 1646 */       this.player.dropPlayerItemWithRandomChoice(item_stack, false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void destroyInventory() {
/* 1652 */     ItemStack[] item_stacks = this.mainInventory;
/*      */     int i;
/* 1654 */     for (i = 0; i < item_stacks.length; i++) {
/* 1655 */       item_stacks[i] = null;
/*      */     }
/* 1657 */     item_stacks = this.armorInventory;
/*      */     
/* 1659 */     for (i = 0; i < item_stacks.length; i++) {
/* 1660 */       item_stacks[i] = null;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isWearing(ItemStack item_stack) {
/* 1665 */     for (int i = 0; i < this.armorInventory.length; i++) {
/*      */       
/* 1667 */       if (this.armorInventory[i] == item_stack) {
/* 1668 */         return true;
/*      */       }
/*      */     } 
/* 1671 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumItems(Item item) {
/* 1677 */     int num = 0;
/*      */     int i;
/* 1679 */     for (i = 0; i < this.mainInventory.length; i++) {
/*      */       
/* 1681 */       if (this.mainInventory[i] != null && this.mainInventory[i].getItem() == item) {
/* 1682 */         num += (this.mainInventory[i]).stackSize;
/*      */       }
/*      */     } 
/* 1685 */     for (i = 0; i < this.armorInventory.length; i++) {
/*      */       
/* 1687 */       if (this.armorInventory[i] != null && this.armorInventory[i].getItem() == item) {
/* 1688 */         num += (this.armorInventory[i]).stackSize;
/*      */       }
/*      */     } 
/* 1691 */     return num;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setWornItem(int slot_index, ItemStack item_stack) {
/* 1697 */     if (this.armorInventory[slot_index] == item_stack) {
/* 1698 */       return false;
/*      */     }
/* 1700 */     this.armorInventory[slot_index] = item_stack;
/*      */     
/* 1702 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean takeDamage(DamageSource damage_source, float chance_per_item, float amount, boolean include_worn_items) {
/* 1708 */     if (this.player.onClient()) {
/*      */       
/* 1710 */       Debug.setErrorMessage("takeDamage: called on client?");
/* 1711 */       return false;
/*      */     } 
/*      */     
/* 1714 */     boolean damage_occurred = false;
/*      */     int slot_index;
/* 1716 */     for (slot_index = 0; slot_index < this.mainInventory.length; slot_index++) {
/*      */       
/* 1718 */       ItemStack item_stack = this.mainInventory[slot_index];
/*      */       
/* 1720 */       if (item_stack != null)
/*      */       {
/*      */         
/* 1723 */         for (int i = 0; i < item_stack.stackSize; i++) {
/*      */           
/* 1725 */           if (this.player.rand.nextFloat() < chance_per_item)
/*      */           {
/*      */             
/* 1728 */             if (takeDamage(item_stack, damage_source, amount))
/* 1729 */               damage_occurred = true;  } 
/*      */         } 
/*      */       }
/*      */     } 
/* 1733 */     if (include_worn_items) {
/*      */       
/* 1735 */       chance_per_item *= 5.0F;
/*      */       
/* 1737 */       for (slot_index = 0; slot_index < this.armorInventory.length; slot_index++) {
/*      */         
/* 1739 */         ItemStack item_stack = this.armorInventory[slot_index];
/*      */         
/* 1741 */         if (item_stack != null)
/*      */         {
/*      */           
/* 1744 */           for (int i = 0; i < item_stack.stackSize; i++) {
/*      */             
/* 1746 */             if (this.player.rand.nextFloat() < chance_per_item)
/*      */             {
/*      */               
/* 1749 */               if (takeDamage(item_stack, damage_source, amount)) {
/* 1750 */                 damage_occurred = true;
/*      */               }
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1758 */     if (damage_occurred) {
/*      */       
/* 1760 */       if (damage_source.isPepsinDamage()) {
/* 1761 */         this.player.entityFX(EnumEntityFX.steam_with_hiss);
/*      */       }
/* 1763 */       if (damage_source.isAcidDamage()) {
/* 1764 */         this.player.entityFX(EnumEntityFX.steam_with_hiss);
/*      */       }
/*      */     } 
/* 1767 */     return damage_occurred;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean takeDamage(ItemStack item_stack, DamageSource damage_source, float amount) {
/* 1773 */     if (this.player.onClient()) {
/*      */       
/* 1775 */       Debug.setErrorMessage("takeDamage: called on client?");
/* 1776 */       return false;
/*      */     } 
/*      */     
/* 1779 */     if (this.player.inCreativeMode()) {
/* 1780 */       return false;
/*      */     }
/* 1782 */     if (damage_source.isPepsinDamage()) {
/*      */       
/* 1784 */       if (!item_stack.isHarmedByPepsin()) {
/* 1785 */         return false;
/*      */       }
/* 1787 */     } else if (damage_source.isAcidDamage()) {
/*      */       
/* 1789 */       if (!item_stack.isHarmedByAcid()) {
/* 1790 */         return false;
/*      */       }
/*      */     } 
/* 1793 */     int slot_index = getSlotIndex(item_stack);
/*      */     
/* 1795 */     if (slot_index < 0) {
/*      */       
/* 1797 */       Debug.setErrorMessage("takeDamage: item_stack not found in either main or armor inventory " + item_stack);
/* 1798 */       return false;
/*      */     } 
/*      */     
/* 1801 */     if (item_stack.isItemStackDamageable()) {
/*      */       
/* 1803 */       ItemDamageResult idr = item_stack.tryDamageItem(damage_source, item_stack.getScaledDamage(amount), this.player);
/*      */       
/* 1805 */       if (idr == null) {
/* 1806 */         return false;
/*      */       }
/* 1808 */       if (idr.itemWasDestroyed()) {
/* 1809 */         setInventorySlotContents(slot_index, null);
/*      */       }
/* 1811 */       return true;
/*      */     } 
/*      */     
/* 1814 */     if (this.player.rand.nextFloat() * 10.0F < amount) {
/*      */       
/* 1816 */       if (--item_stack.stackSize < 1) {
/*      */         
/* 1818 */         setInventorySlotContents(slot_index, null);
/* 1819 */         this.player.worldObj.tryRemoveFromWorldUniques(item_stack);
/*      */       } 
/*      */       
/* 1822 */       if (item_stack.getItem().hasContainerItem()) {
/*      */         
/* 1824 */         Item container = item_stack.getItem().getContainerItem();
/*      */         
/* 1826 */         if (!container.isHarmedBy(damage_source)) {
/* 1827 */           addItemStackToInventoryOrDropIt(new ItemStack(container));
/*      */         }
/*      */       } 
/* 1830 */       return true;
/*      */     } 
/*      */     
/* 1833 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean convertAllItemsInSlot(int slot_index, Item item) {
/* 1838 */     ItemStack existing_stack = getInventorySlotContents(slot_index);
/*      */     
/* 1840 */     if (existing_stack == null || existing_stack.getItem() == item) {
/* 1841 */       return false;
/*      */     }
/* 1843 */     ItemStack item_stack = new ItemStack(item, existing_stack.stackSize);
/*      */     
/* 1845 */     while (item_stack.stackSize > item_stack.getMaxStackSize()) {
/*      */       
/* 1847 */       ItemStack created_item_stack = new ItemStack(item);
/*      */       
/* 1849 */       created_item_stack.stackSize = (canCompletelyMergeWithExistingItemStacks(created_item_stack) || hasEmptyMainInventorySlot()) ? 1 : Math.min(item_stack.stackSize - item_stack.getMaxStackSize(), created_item_stack.getMaxStackSize());
/*      */       
/* 1851 */       addItemStackToInventoryOrDropIt(created_item_stack);
/* 1852 */       item_stack.stackSize -= created_item_stack.stackSize;
/*      */     } 
/*      */     
/* 1855 */     setInventorySlotContents(slot_index, item_stack);
/*      */     
/* 1857 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\InventoryPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */