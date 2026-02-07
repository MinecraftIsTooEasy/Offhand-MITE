/*     */ package net.minecraft;
/*     */ 
/*     */ public class TileEntityFurnace
/*     */   extends TileEntity implements ISidedInventory {
/*   5 */   private static final int[] slots_top = new int[] { 0 };
/*   6 */   private static final int[] slots_bottom = new int[] { 2, 1 };
/*   7 */   private static final int[] slots_sides = new int[] { 1 };
/*     */   
/*     */   public static final int INPUT = 0;
/*     */   
/*     */   public static final int FUEL = 1;
/*     */   
/*     */   public static final int OUTPUT = 2;
/*     */   
/*     */   public static final int HEAT_LEVEL_WOOD_AND_CHARCOAL = 1;
/*     */   
/*     */   public static final int HEAT_LEVEL_COAL = 2;
/*     */   
/*     */   public static final int HEAT_LEVEL_LAVA = 3;
/*     */   public static final int HEAT_LEVEL_BLAZE_ROD = 4;
/*  21 */   private ItemStack[] furnaceItemStacks = new ItemStack[3];
/*     */ 
/*     */   
/*     */   public int furnaceBurnTime;
/*     */ 
/*     */   
/*  27 */   public int heat_level = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int currentItemBurnTime;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int furnaceCookTime;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  43 */     return this.furnaceItemStacks.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  51 */     return this.furnaceItemStacks[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  60 */     if (this.furnaceItemStacks[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/*  64 */       if ((this.furnaceItemStacks[par1]).stackSize <= par2) {
/*     */         
/*  66 */         ItemStack itemStack = this.furnaceItemStacks[par1];
/*  67 */         this.furnaceItemStacks[par1] = null;
/*  68 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  72 */       ItemStack var3 = this.furnaceItemStacks[par1].splitStack(par2);
/*     */       
/*  74 */       if ((this.furnaceItemStacks[par1]).stackSize == 0)
/*     */       {
/*  76 */         this.furnaceItemStacks[par1] = null;
/*     */       }
/*     */       
/*  79 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/*  94 */     if (this.furnaceItemStacks[par1] != null) {
/*     */       
/*  96 */       ItemStack var2 = this.furnaceItemStacks[par1];
/*  97 */       this.furnaceItemStacks[par1] = null;
/*  98 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 111 */     this.furnaceItemStacks[par1] = par2ItemStack;
/*     */     
/* 113 */     if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
/*     */     {
/* 115 */       par2ItemStack.stackSize = getInventoryStackLimit();
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
/*     */   public String getUnlocalizedInvName() {
/* 132 */     return getBlockType().getUnlocalizedName() + ".name";
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
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 157 */     super.readFromNBT(par1NBTTagCompound);
/* 158 */     NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
/* 159 */     this.furnaceItemStacks = new ItemStack[getSizeInventory()];
/*     */     
/* 161 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 163 */       NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/* 164 */       byte var5 = var4.getByte("Slot");
/*     */       
/* 166 */       if (var5 >= 0 && var5 < this.furnaceItemStacks.length)
/*     */       {
/* 168 */         this.furnaceItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */     
/* 172 */     this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
/* 173 */     this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
/*     */     
/* 175 */     this.currentItemBurnTime = par1NBTTagCompound.getShort("CurrentItemBurnTime");
/* 176 */     this.heat_level = par1NBTTagCompound.getByte("heat_level");
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
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 189 */     super.writeToNBT(par1NBTTagCompound);
/* 190 */     par1NBTTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
/* 191 */     par1NBTTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
/* 192 */     par1NBTTagCompound.setShort("CurrentItemBurnTime", (short)this.currentItemBurnTime);
/* 193 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 195 */     for (int var3 = 0; var3 < this.furnaceItemStacks.length; var3++) {
/*     */       
/* 197 */       if (this.furnaceItemStacks[var3] != null) {
/*     */         
/* 199 */         NBTTagCompound var4 = new NBTTagCompound();
/* 200 */         var4.setByte("Slot", (byte)var3);
/* 201 */         this.furnaceItemStacks[var3].writeToNBT(var4);
/* 202 */         var2.appendTag(var4);
/*     */       } 
/*     */     } 
/*     */     
/* 206 */     par1NBTTagCompound.setTag("Items", var2);
/* 207 */     par1NBTTagCompound.setByte("heat_level", (byte)this.heat_level);
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
/*     */   public int getInventoryStackLimit() {
/* 221 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCookProgressScaled(int par1) {
/* 230 */     return this.furnaceCookTime * par1 / 200;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBurnTimeRemainingScaled(int par1) {
/* 239 */     if (this.currentItemBurnTime == 0)
/*     */     {
/* 241 */       this.currentItemBurnTime = 200;
/*     */     }
/*     */     
/* 244 */     return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 252 */     return (this.furnaceBurnTime > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlooded() {
/* 257 */     return (getFurnaceBlock() != null && this.worldObj.getNeighborBlockMaterial(this.xCoord, this.yCoord, this.zCoord, getFurnaceBlock().getDirectionFacing(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord))) == Material.water);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSmotheredBySolidBlock() {
/* 262 */     if (getFurnaceBlock() == null) {
/* 263 */       return false;
/*     */     }
/* 265 */     EnumFace facing = getFurnaceBlock().getDirectionFacing(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)).getFace();
/*     */     
/* 267 */     int[] neighbor_coords = World.getNeighboringBlockCoords(this.xCoord, this.yCoord, this.zCoord, facing);
/*     */     
/* 269 */     return this.worldObj.isBlockFaceFlatAndSolid(neighbor_coords[0], neighbor_coords[1], neighbor_coords[2], facing.getOpposite());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEntity() {
/* 278 */     if (!this.worldObj.isRemote && this.furnaceBurnTime != 1 && (isFlooded() || isSmotheredBySolidBlock())) {
/*     */       
/* 280 */       if (this.furnaceBurnTime > 0) {
/*     */         
/* 282 */         if (isFlooded()) {
/* 283 */           this.worldObj.blockFX(EnumBlockFX.steam, this.xCoord, this.yCoord, this.zCoord);
/*     */         }
/* 285 */         BlockFurnace.updateFurnaceBlockState(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
/*     */       } 
/*     */       
/* 288 */       this.furnaceBurnTime = 0;
/* 289 */       this.furnaceCookTime = 0;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 294 */     boolean var1 = (this.furnaceBurnTime > 0);
/* 295 */     boolean var2 = false;
/*     */     
/* 297 */     if (this.furnaceBurnTime > 0) {
/*     */       
/* 299 */       this.furnaceBurnTime--;
/*     */     }
/*     */     else {
/*     */       
/* 303 */       this.heat_level = 0;
/*     */     } 
/*     */     
/* 306 */     if (!this.worldObj.isRemote) {
/*     */ 
/*     */       
/* 309 */       if (this.furnaceBurnTime == 0 && canSmelt(getFuelHeatLevel())) {
/*     */         
/* 311 */         this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
/*     */ 
/*     */         
/* 314 */         if (this.furnaceBurnTime > 0) {
/*     */           
/* 316 */           this.heat_level = getItemHeatLevel(this.furnaceItemStacks[1]);
/* 317 */           var2 = true;
/*     */           
/* 319 */           if (this.furnaceItemStacks[1] != null) {
/*     */             
/* 321 */             (this.furnaceItemStacks[1]).stackSize--;
/*     */             
/* 323 */             if ((this.furnaceItemStacks[1]).stackSize == 0) {
/*     */               
/* 325 */               Item var3 = this.furnaceItemStacks[1].getItem().getContainerItem();
/* 326 */               this.furnaceItemStacks[1] = (var3 != null) ? new ItemStack(var3) : null;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 333 */       if (isBurning() && canSmelt(this.heat_level)) {
/*     */         
/* 335 */         this.furnaceCookTime++;
/*     */         
/* 337 */         if (this.furnaceCookTime == 200)
/*     */         {
/* 339 */           this.furnaceCookTime = 0;
/*     */           
/* 341 */           smeltItem(this.heat_level);
/* 342 */           var2 = true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 347 */         this.furnaceCookTime = 0;
/*     */       } 
/*     */       
/* 350 */       if (var1 != ((this.furnaceBurnTime > 0))) {
/*     */         
/* 352 */         var2 = true;
/* 353 */         BlockFurnace.updateFurnaceBlockState((this.furnaceBurnTime > 0), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
/*     */       } 
/*     */     } 
/*     */     
/* 357 */     if (var2)
/*     */     {
/* 359 */       onInventoryChanged();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getHeatLevelRequired(int item_id) {
/* 366 */     if (item_id == Block.oreAdamantium.blockID)
/* 367 */       return 4; 
/* 368 */     if (item_id == Block.oreMithril.blockID)
/* 369 */       return 3; 
/* 370 */     if (item_id == Block.oreCopper.blockID || item_id == Block.oreSilver.blockID || item_id == Block.oreGold.blockID || item_id == Block.oreIron.blockID)
/* 371 */       return 2; 
/* 372 */     if (item_id == Block.oreNetherQuartz.blockID || item_id == Block.oreEmerald.blockID || item_id == Block.oreDiamond.blockID || item_id == Block.oreRedstone.blockID)
/* 373 */       return 2; 
/* 374 */     if (item_id == Block.oreLapis.blockID)
/* 375 */       return 2; 
/* 376 */     if (item_id == Block.sandStone.blockID)
/* 377 */       return 2; 
/* 378 */     if (item_id == Block.sand.blockID)
/*     */     {
/* 380 */       return 1;
/*     */     }
/* 382 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] getSkillsetsThatCanSmelt(Item item) {
/* 388 */     if (item instanceof ItemFood)
/*     */     {
/* 390 */       return new int[] { Skill.FOOD_PREPARATION.id };
/*     */     }
/* 392 */     if (item == Item.clay)
/*     */     {
/* 394 */       return new int[] { Skill.MASONRY.id };
/*     */     }
/* 396 */     if (item instanceof ItemBlock) {
/*     */       
/* 398 */       ItemBlock item_block = (ItemBlock)item;
/* 399 */       Block block = item_block.getBlock();
/*     */ 
/*     */       
/* 402 */       if (block == Block.sand || block instanceof BlockClay || block instanceof BlockNetherrack)
/* 403 */         return new int[] { Skill.MASONRY.id }; 
/* 404 */       if (block instanceof BlockOre || block instanceof BlockRedstoneOre) {
/* 405 */         return new int[] { Skill.MINING.id };
/*     */       }
/*     */     } 
/* 408 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canSmelt(int heat_level) {
/* 417 */     if (this.furnaceItemStacks[0] == null)
/*     */     {
/* 419 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 423 */     BlockFurnace furnace = getFurnaceBlock();
/*     */ 
/*     */     
/* 426 */     if (furnace == null || (!acceptsLargeItems() && Slot.isLargeItem(getInputItemStack().getItem()))) {
/* 427 */       return false;
/*     */     }
/*     */     
/* 430 */     ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(getInputItemStack(), heat_level);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 435 */     if (var1 == null) {
/* 436 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 441 */     ItemStack output_item_stack = getOutputItemStack();
/*     */     
/* 443 */     return (output_item_stack == null) ? true : (!output_item_stack.isItemStackEqual(var1, true, false, false, true) ? false : ((output_item_stack.stackSize < getInventoryStackLimit() && output_item_stack.stackSize < output_item_stack.getMaxStackSize()) ? true : ((output_item_stack.stackSize < var1.getMaxStackSize()))));
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
/*     */   public void smeltItem(int heat_level) {
/* 456 */     if (canSmelt(heat_level)) {
/*     */       int consumption;
/*     */       
/* 459 */       ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(getInputItemStack(), heat_level);
/*     */       
/* 461 */       if (this.furnaceItemStacks[2] == null) {
/*     */         
/* 463 */         this.furnaceItemStacks[2] = var1.copy();
/*     */       }
/* 465 */       else if ((this.furnaceItemStacks[2]).itemID == var1.itemID) {
/*     */ 
/*     */         
/* 468 */         (this.furnaceItemStacks[2]).stackSize += var1.stackSize;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 475 */       if ((getInputItemStack()).itemID == Block.sand.blockID && var1.itemID == Block.sandStone.blockID) {
/* 476 */         consumption = 4;
/* 477 */       } else if ((getInputItemStack()).itemID == Block.sand.blockID && var1.itemID == Block.glass.blockID) {
/* 478 */         consumption = 4;
/*     */       } else {
/* 480 */         consumption = 1;
/*     */       } 
/* 482 */       (getInputItemStack()).stackSize -= consumption;
/*     */       
/* 484 */       if (getInputItemStack().getItem() == Item.clay && var1.getItem() == Item.brick) {
/*     */         
/* 486 */         int extra_converted = Math.min(getOutputItemStack().getMaxStackSize() - (getOutputItemStack()).stackSize, (getInputItemStack()).stackSize);
/*     */         
/* 488 */         if (extra_converted > 3) {
/* 489 */           extra_converted = 3;
/*     */         }
/* 491 */         (getOutputItemStack()).stackSize += extra_converted;
/* 492 */         (getInputItemStack()).stackSize -= extra_converted;
/*     */       } 
/*     */       
/* 495 */       if ((this.furnaceItemStacks[0]).stackSize <= 0)
/*     */       {
/* 497 */         this.furnaceItemStacks[0] = null;
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemBurnTime(ItemStack item_stack) {
/* 543 */     if (item_stack == null) {
/* 544 */       return 0;
/*     */     }
/* 546 */     return item_stack.getItem().getBurnTime(item_stack);
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
/*     */   public int getItemHeatLevel(ItemStack item_stack) {
/* 601 */     if (item_stack == null) {
/* 602 */       return 0;
/*     */     }
/* 604 */     return item_stack.getItem().getHeatLevel(item_stack);
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
/*     */   public boolean isItemFuel(ItemStack item_stack) {
/* 635 */     return (getItemHeatLevel(item_stack) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 643 */     return (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) ? false : ((par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D));
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
/* 655 */     if (par1 == 0) {
/*     */       
/* 657 */       Slot slot = new Slot(this, 0, 56, 17, acceptsLargeItems());
/*     */       
/* 659 */       if (!slot.isItemValid(par2ItemStack)) {
/* 660 */         return false;
/*     */       }
/* 662 */       if (!FurnaceRecipes.smelting().doesSmeltingRecipeExistFor(par2ItemStack)) {
/* 663 */         return false;
/*     */       }
/* 665 */     } else if (par1 == 1) {
/*     */       
/* 667 */       SlotFuel slot_fuel = new SlotFuel(this, 1, 56, 53, this);
/*     */       
/* 669 */       if (!slot_fuel.isItemValid(par2ItemStack)) {
/* 670 */         return false;
/*     */       }
/*     */     } 
/* 673 */     return (par1 == 2) ? false : ((par1 == 1) ? isItemFuel(par2ItemStack) : true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getAccessibleSlotsFromSide(int par1) {
/* 682 */     return (par1 == 0) ? slots_bottom : ((par1 == 1) ? slots_top : slots_sides);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
/* 691 */     return isItemValidForSlot(par1, par2ItemStack);
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
/*     */   public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
/* 705 */     if (par3 != 0 || par1 != 1) {
/* 706 */       return true;
/*     */     }
/* 708 */     if (!(par2ItemStack.getItem() instanceof ItemBucket)) {
/* 709 */       return false;
/*     */     }
/* 711 */     ItemBucket bucket = (ItemBucket)par2ItemStack.getItem();
/*     */ 
/*     */     
/* 714 */     return bucket.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFurnace getFurnaceBlock() {
/* 720 */     return (BlockFurnace)getBlockType();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxHeatLevel() {
/* 725 */     return getFurnaceBlock().getMaxHeatLevel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean acceptsLargeItems() {
/* 735 */     return getFurnaceBlock().acceptsLargeItems();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 741 */     ItemStack[] item_stacks = this.furnaceItemStacks;
/*     */     
/* 743 */     for (int i = 0; i < item_stacks.length; i++) {
/* 744 */       item_stacks[i] = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack getInputItemStack() {
/* 749 */     return this.furnaceItemStacks[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getFuelItemStack() {
/* 754 */     return this.furnaceItemStacks[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getOutputItemStack() {
/* 759 */     return this.furnaceItemStacks[2];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFuelHeatLevel() {
/* 765 */     return getItemHeatLevel(getFuelItemStack());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */