/*     */ package net.minecraft;
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
/*     */ public class Slot
/*     */ {
/*     */   private final int slotIndex;
/*     */   public final IInventory inventory;
/*     */   public int slotNumber;
/*     */   public int xDisplayPosition;
/*     */   public int yDisplayPosition;
/*     */   protected boolean accepts_large_items;
/*     */   protected boolean locked;
/*     */   private Container container;
/*     */   
/*     */   public Slot(IInventory inventory, int slot_index, int display_x, int display_y) {
/*  35 */     this(inventory, slot_index, display_x, display_y, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Slot(IInventory inventory, int slot_index, int display_x, int display_y, boolean accepts_large_items) {
/*  41 */     this.inventory = inventory;
/*  42 */     this.slotIndex = slot_index;
/*  43 */     this.xDisplayPosition = display_x;
/*  44 */     this.yDisplayPosition = display_y;
/*  45 */     this.accepts_large_items = accepts_large_items;
/*     */   }
/*     */ 
/*     */   
/*     */   public Slot setContainer(Container container) {
/*  50 */     this.container = container;
/*  51 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Container getContainer() {
/*  56 */     return this.container;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSlotChange(ItemStack par1ItemStack, ItemStack par2ItemStack) {
/*  64 */     if (par1ItemStack != null && par2ItemStack != null)
/*     */     {
/*  66 */       if (par1ItemStack.itemID == par2ItemStack.itemID) {
/*     */         
/*  68 */         int var3 = par2ItemStack.stackSize - par1ItemStack.stackSize;
/*     */         
/*  70 */         if (var3 > 0)
/*     */         {
/*  72 */           onCrafting(par1ItemStack, var3);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack par1ItemStack, int par2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack par1ItemStack) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
/*  91 */     onSlotChanged();
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
/*     */   public static boolean isLargeItem(Item item) {
/* 114 */     if (item instanceof ItemBlock) {
/*     */       
/* 116 */       Block block = ((ItemBlock)item).getBlock();
/*     */       
/* 118 */       if (block instanceof BlockTorch || block instanceof BlockSapling || block instanceof BlockFlower || block instanceof BlockTallGrass || block instanceof BlockMushroom || block instanceof BlockButton || block instanceof BlockLilyPad || block instanceof BlockVine) {
/* 119 */         return false;
/*     */       }
/* 121 */       return true;
/*     */     } 
/*     */     
/* 124 */     return (item instanceof ItemDoor || item instanceof ItemBoat || item instanceof ItemBed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValid(ItemStack par1ItemStack) {
/* 131 */     if (par1ItemStack == null) {
/* 132 */       return true;
/*     */     }
/* 134 */     if (getContainer() instanceof ContainerFurnace && getContainer().getSlot(0) == this) {
/*     */       
/* 136 */       ContainerFurnace container_furnace = (ContainerFurnace)getContainer();
/*     */       
/* 138 */       if (!container_furnace.canPlayerAddItemToSmelt(par1ItemStack.getItem())) {
/* 139 */         return false;
/*     */       }
/* 141 */       if (!FurnaceRecipes.smelting().doesSmeltingRecipeExistFor(par1ItemStack)) {
/* 142 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     return (!this.locked && (!isLargeItem(par1ItemStack.getItem()) || this.accepts_large_items));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStack() {
/* 161 */     return this.inventory.getStackInSlot(this.slotIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHasStack() {
/* 169 */     return (getStack() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putStack(ItemStack par1ItemStack) {
/* 177 */     this.inventory.setInventorySlotContents(this.slotIndex, par1ItemStack);
/* 178 */     onSlotChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSlotChanged() {
/* 186 */     if (this.container != null) {
/*     */       
/* 188 */       if (this.container.world != null) {
/*     */         
/* 190 */         if (this.container.world.isRemote) {
/*     */           
/* 192 */           if (getHasStack())
/*     */           {
/*     */             
/* 195 */             setLocked(true);
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/* 202 */         else if (getHasStack()) {
/* 203 */           ((ServerPlayer)this.container.player).sendPacket(new Packet85SimpleSignal(EnumSignal.unlock_slots));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 208 */         System.out.println("world was null for " + this + ",  " + this.inventory);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 213 */       System.out.println("container was null for " + this + ",  " + this.inventory);
/*     */     } 
/*     */     
/* 216 */     this.inventory.onInventoryChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSlotStackLimit() {
/* 225 */     return this.inventory.getInventoryStackLimit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getBackgroundIconIndex() {
/* 233 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1) {
/* 242 */     return this.inventory.decrStackSize(this.slotIndex, par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSlotInInventory(IInventory par1IInventory, int par2) {
/* 250 */     return (par1IInventory == this.inventory && par2 == this.slotIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
/* 259 */     return !this.locked;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_111238_b() {
/* 264 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocked(boolean locked) {
/* 269 */     this.locked = locked;
/*     */     
/* 271 */     if (locked)
/* 272 */       this.container.slot_was_locked_this_tick = true; 
/*     */   }
/*     */   
/*     */   public void onSlotClicked(EntityPlayer entity_player, int button, Container container) {}
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Slot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */