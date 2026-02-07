/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class TileEntityHopper
/*     */   extends TileEntity implements Hopper {
/*   7 */   private ItemStack[] hopperItemStacks = new ItemStack[5];
/*     */ 
/*     */ 
/*     */   
/*  11 */   private int transferCooldown = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  18 */     super.readFromNBT(par1NBTTagCompound);
/*  19 */     NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
/*  20 */     this.hopperItemStacks = new ItemStack[getSizeInventory()];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  27 */     this.transferCooldown = par1NBTTagCompound.getInteger("TransferCooldown");
/*     */     
/*  29 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/*  31 */       NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/*  32 */       byte var5 = var4.getByte("Slot");
/*     */       
/*  34 */       if (var5 >= 0 && var5 < this.hopperItemStacks.length)
/*     */       {
/*  36 */         this.hopperItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/*  46 */     super.writeToNBT(par1NBTTagCompound);
/*  47 */     NBTTagList var2 = new NBTTagList();
/*     */     
/*  49 */     for (int var3 = 0; var3 < this.hopperItemStacks.length; var3++) {
/*     */       
/*  51 */       if (this.hopperItemStacks[var3] != null) {
/*     */         
/*  53 */         NBTTagCompound var4 = new NBTTagCompound();
/*  54 */         var4.setByte("Slot", (byte)var3);
/*  55 */         this.hopperItemStacks[var3].writeToNBT(var4);
/*  56 */         var2.appendTag(var4);
/*     */       } 
/*     */     } 
/*     */     
/*  60 */     par1NBTTagCompound.setTag("Items", var2);
/*  61 */     par1NBTTagCompound.setInteger("TransferCooldown", this.transferCooldown);
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
/*     */   public void onInventoryChanged() {
/*  74 */     super.onInventoryChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  82 */     return this.hopperItemStacks.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  90 */     return this.hopperItemStacks[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  99 */     if (this.hopperItemStacks[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/* 103 */       if ((this.hopperItemStacks[par1]).stackSize <= par2) {
/*     */         
/* 105 */         ItemStack itemStack = this.hopperItemStacks[par1];
/* 106 */         this.hopperItemStacks[par1] = null;
/* 107 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 111 */       ItemStack var3 = this.hopperItemStacks[par1].splitStack(par2);
/*     */       
/* 113 */       if ((this.hopperItemStacks[par1]).stackSize == 0)
/*     */       {
/* 115 */         this.hopperItemStacks[par1] = null;
/*     */       }
/*     */       
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
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/* 133 */     if (this.hopperItemStacks[par1] != null) {
/*     */       
/* 135 */       ItemStack var2 = this.hopperItemStacks[par1];
/* 136 */       this.hopperItemStacks[par1] = null;
/* 137 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 141 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 150 */     this.hopperItemStacks[par1] = par2ItemStack;
/*     */     
/* 152 */     if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
/*     */     {
/* 154 */       par2ItemStack.stackSize = getInventoryStackLimit();
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
/*     */   public String getUnlocalizedInvName() {
/* 168 */     return "container.hopper";
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
/*     */   public int getInventoryStackLimit() {
/* 191 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 199 */     return (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) ? false : ((par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D));
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
/* 211 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEntity() {
/* 220 */     if (this.worldObj != null && !this.worldObj.isRemote) {
/*     */       
/* 222 */       this.transferCooldown--;
/*     */       
/* 224 */       if (!isCoolingDown()) {
/*     */         
/* 226 */         setTransferCooldown(0);
/* 227 */         updateHopper();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateHopper() {
/* 234 */     if (this.worldObj != null && !this.worldObj.isRemote) {
/*     */       
/* 236 */       if (!isCoolingDown() && BlockHopper.getIsBlockNotPoweredFromMetadata(getBlockMetadata())) {
/*     */         
/* 238 */         boolean var1 = insertItemToInventory();
/* 239 */         var1 = (suckItemsIntoHopper(this) || var1);
/*     */         
/* 241 */         if (var1) {
/*     */           
/* 243 */           setTransferCooldown(8);
/* 244 */           onInventoryChanged();
/* 245 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 249 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean insertItemToInventory() {
/* 262 */     IInventory var1 = getOutputInventory();
/*     */     
/* 264 */     if (var1 == null)
/*     */     {
/* 266 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 270 */     for (int var2 = 0; var2 < getSizeInventory(); var2++) {
/*     */       
/* 272 */       if (getStackInSlot(var2) != null) {
/*     */         
/* 274 */         ItemStack var3 = getStackInSlot(var2).copy();
/* 275 */         ItemStack var4 = insertStack(var1, decrStackSize(var2, 1), Facing.oppositeSide[BlockHopper.getDirectionFromMetadata(getBlockMetadata())]);
/*     */         
/* 277 */         if (var4 == null || var4.stackSize == 0) {
/*     */           
/* 279 */           var1.onInventoryChanged();
/* 280 */           return true;
/*     */         } 
/*     */         
/* 283 */         setInventorySlotContents(var2, var3);
/*     */       } 
/*     */     } 
/*     */     
/* 287 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isFurnaceAbove(Hopper par0Hopper) {
/* 293 */     return par0Hopper.getWorldObj().getBlock(par0Hopper.getX(), par0Hopper.getY() + 1, par0Hopper.getZ()) instanceof BlockFurnace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean suckItemsIntoHopper(Hopper par0Hopper) {
/* 301 */     IInventory var1 = getInventoryAboveHopper(par0Hopper);
/*     */     
/* 303 */     if (var1 != null) {
/*     */       
/* 305 */       byte var2 = 0;
/*     */       
/* 307 */       if (var1 instanceof ISidedInventory && var2 > -1) {
/*     */         
/* 309 */         ISidedInventory var7 = (ISidedInventory)var1;
/* 310 */         int[] var8 = var7.getAccessibleSlotsFromSide(var2);
/*     */         
/* 312 */         for (int var5 = 0; var5 < var8.length; var5++)
/*     */         {
/* 314 */           if (insertStackFromInventory(par0Hopper, var1, var8[var5], var2))
/*     */           {
/* 316 */             return true;
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 322 */         int var3 = var1.getSizeInventory();
/*     */         
/* 324 */         for (int var4 = 0; var4 < var3; var4++)
/*     */         {
/* 326 */           if (insertStackFromInventory(par0Hopper, var1, var4, var2))
/*     */           {
/* 328 */             return true;
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 334 */     } else if (!par0Hopper.getWorldObj().isBlockFaceFlatAndSolid(MathHelper.floor_double(par0Hopper.getXPos()), MathHelper.floor_double(par0Hopper.getYPos()) + 1, MathHelper.floor_double(par0Hopper.getZPos()), EnumFace.BOTTOM)) {
/*     */       
/* 336 */       EntityItem var6 = getEntityAbove(par0Hopper.getWorldObj(), par0Hopper.getXPos(), par0Hopper.getYPos() + 1.0D, par0Hopper.getZPos());
/*     */       
/* 338 */       if (var6 != null)
/*     */       {
/* 340 */         return insertStackFromEntity(par0Hopper, var6);
/*     */       }
/*     */     } 
/*     */     
/* 344 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean insertStackFromInventory(Hopper par0Hopper, IInventory par1IInventory, int par2, int par3) {
/* 349 */     ItemStack var4 = par1IInventory.getStackInSlot(par2);
/*     */     
/* 351 */     if (var4 != null && canExtractItemFromInventory(par1IInventory, var4, par2, par3)) {
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
/* 381 */       ItemStack var5 = var4.copy();
/* 382 */       ItemStack var6 = insertStack(par0Hopper, par1IInventory.decrStackSize(par2, 1), -1);
/*     */       
/* 384 */       if (var6 == null || var6.stackSize == 0) {
/*     */         
/* 386 */         if (!(par0Hopper.getWorldObj()).isRemote && isFurnaceAbove(par0Hopper)) {
/*     */           
/* 388 */           int xp_reward = var4.getExperienceReward(1);
/*     */           
/* 390 */           if (xp_reward > 0) {
/*     */             
/* 392 */             World world = par0Hopper.getWorldObj();
/*     */             
/* 394 */             int x = par0Hopper.getX();
/* 395 */             int y = par0Hopper.getY();
/* 396 */             int z = par0Hopper.getZ();
/*     */             
/* 398 */             BlockFurnace block_furnace = (BlockFurnace)world.getBlock(x, ++y, z);
/*     */             
/* 400 */             EnumDirection direction = block_furnace.getDirectionFacing(world.getBlockMetadata(x, y, z));
/*     */             
/* 402 */             int[] coords = World.getNeighboringBlockCoords(x, y, z, direction.getFace());
/*     */             
/* 404 */             EntityXPOrb xp_orb = new EntityXPOrb(world, coords[0] + 0.5D, coords[1] + 0.5D, coords[2] + 0.5D, xp_reward);
/*     */             
/* 406 */             xp_orb.motionX *= 0.20000000298023224D;
/* 407 */             xp_orb.motionY *= 0.20000000298023224D;
/* 408 */             xp_orb.motionZ *= 0.20000000298023224D;
/*     */             
/* 410 */             par0Hopper.getWorldObj().spawnEntityInWorld(xp_orb);
/*     */           } 
/*     */         } 
/*     */         
/* 414 */         par1IInventory.onInventoryChanged();
/* 415 */         return true;
/*     */       } 
/*     */       
/* 418 */       par1IInventory.setInventorySlotContents(par2, var5);
/*     */     } 
/*     */     
/* 421 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean insertStackFromEntity(IInventory par0IInventory, EntityItem par1EntityItem) {
/* 426 */     boolean var2 = false;
/*     */     
/* 428 */     if (par1EntityItem == null)
/*     */     {
/* 430 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 434 */     ItemStack var3 = par1EntityItem.getEntityItem().copy();
/* 435 */     ItemStack var4 = insertStack(par0IInventory, var3, -1);
/*     */     
/* 437 */     if (var4 != null && var4.stackSize != 0) {
/*     */       
/* 439 */       par1EntityItem.setEntityItemStack(var4);
/*     */     }
/*     */     else {
/*     */       
/* 443 */       var2 = true;
/* 444 */       par1EntityItem.setDead();
/*     */     } 
/*     */     
/* 447 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack insertStack(IInventory par0IInventory, ItemStack par1ItemStack, int par2) {
/* 456 */     if (par0IInventory instanceof ISidedInventory && par2 > -1) {
/*     */       
/* 458 */       ISidedInventory var6 = (ISidedInventory)par0IInventory;
/* 459 */       int[] var7 = var6.getAccessibleSlotsFromSide(par2);
/*     */       
/* 461 */       for (int var5 = 0; var5 < var7.length && par1ItemStack != null && par1ItemStack.stackSize > 0; var5++)
/*     */       {
/* 463 */         par1ItemStack = func_102014_c(par0IInventory, par1ItemStack, var7[var5], par2);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 468 */       int var3 = par0IInventory.getSizeInventory();
/*     */       
/* 470 */       for (int var4 = 0; var4 < var3 && par1ItemStack != null && par1ItemStack.stackSize > 0; var4++)
/*     */       {
/* 472 */         par1ItemStack = func_102014_c(par0IInventory, par1ItemStack, var4, par2);
/*     */       }
/*     */     } 
/*     */     
/* 476 */     if (par1ItemStack != null && par1ItemStack.stackSize == 0)
/*     */     {
/* 478 */       par1ItemStack = null;
/*     */     }
/*     */     
/* 481 */     return par1ItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean canInsertItemToInventory(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3) {
/* 489 */     return !par0IInventory.isItemValidForSlot(par2, par1ItemStack) ? false : ((!(par0IInventory instanceof ISidedInventory) || ((ISidedInventory)par0IInventory).canInsertItem(par2, par1ItemStack, par3)));
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean canExtractItemFromInventory(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3) {
/* 494 */     if (par0IInventory instanceof TileEntityStrongbox) {
/* 495 */       return false;
/*     */     }
/* 497 */     return (!(par0IInventory instanceof ISidedInventory) || ((ISidedInventory)par0IInventory).canExtractItem(par2, par1ItemStack, par3));
/*     */   }
/*     */ 
/*     */   
/*     */   private static ItemStack func_102014_c(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3) {
/* 502 */     ItemStack var4 = par0IInventory.getStackInSlot(par2);
/*     */     
/* 504 */     if (canInsertItemToInventory(par0IInventory, par1ItemStack, par2, par3)) {
/*     */       
/* 506 */       boolean var5 = false;
/*     */       
/* 508 */       if (var4 == null) {
/*     */         
/* 510 */         par0IInventory.setInventorySlotContents(par2, par1ItemStack);
/* 511 */         par1ItemStack = null;
/* 512 */         var5 = true;
/*     */       }
/* 514 */       else if (areItemStacksEqualItem(var4, par1ItemStack)) {
/*     */         
/* 516 */         int var6 = par1ItemStack.getMaxStackSize() - var4.stackSize;
/* 517 */         int var7 = Math.min(par1ItemStack.stackSize, var6);
/* 518 */         par1ItemStack.stackSize -= var7;
/* 519 */         var4.stackSize += var7;
/* 520 */         var5 = (var7 > 0);
/*     */       } 
/*     */       
/* 523 */       if (var5) {
/*     */         
/* 525 */         if (par0IInventory instanceof TileEntityHopper) {
/*     */           
/* 527 */           ((TileEntityHopper)par0IInventory).setTransferCooldown(8);
/* 528 */           par0IInventory.onInventoryChanged();
/*     */         } 
/*     */         
/* 531 */         par0IInventory.onInventoryChanged();
/*     */       } 
/*     */     } 
/*     */     
/* 535 */     return par1ItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IInventory getOutputInventory() {
/* 543 */     int var1 = BlockHopper.getDirectionFromMetadata(getBlockMetadata());
/* 544 */     return getInventoryAtLocation(getWorldObj(), (this.xCoord + Facing.offsetsXForSide[var1]), (this.yCoord + Facing.offsetsYForSide[var1]), (this.zCoord + Facing.offsetsZForSide[var1]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IInventory getInventoryAboveHopper(Hopper par0Hopper) {
/* 552 */     return getInventoryAtLocation(par0Hopper.getWorldObj(), par0Hopper.getXPos(), par0Hopper.getYPos() + 1.0D, par0Hopper.getZPos());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static EntityItem getEntityAbove(World par0World, double par1, double par3, double par5) {
/* 558 */     List<EntityItem> var7 = par0World.selectEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getAABBPool().getAABB(par1, par3 - 0.5D, par5, par1 + 1.0D, par3 + 1.0D, par5 + 1.0D), IEntitySelector.selectAnything);
/* 559 */     return (var7.size() > 0) ? var7.get(0) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IInventory getInventoryAtLocation(World par0World, double par1, double par3, double par5) {
/* 568 */     IInventory var7 = null;
/* 569 */     int var8 = MathHelper.floor_double(par1);
/* 570 */     int var9 = MathHelper.floor_double(par3);
/* 571 */     int var10 = MathHelper.floor_double(par5);
/* 572 */     TileEntity var11 = par0World.getBlockTileEntity(var8, var9, var10);
/*     */     
/* 574 */     if (var11 != null && var11 instanceof IInventory) {
/*     */       
/* 576 */       var7 = (IInventory)var11;
/*     */ 
/*     */       
/* 579 */       if (var7 instanceof TileEntityChest && !(var7 instanceof TileEntityStrongbox)) {
/*     */         
/* 581 */         int var12 = par0World.getBlockId(var8, var9, var10);
/* 582 */         Block var13 = Block.blocksList[var12];
/*     */ 
/*     */         
/* 585 */         if (var13 instanceof BlockChest && !(var13 instanceof BlockStrongbox))
/*     */         {
/* 587 */           var7 = ((BlockChest)var13).getInventory(par0World, var8, var9, var10);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 592 */     if (var7 == null) {
/*     */       
/* 594 */       List<IInventory> var14 = par0World.getEntitiesWithinAABBExcludingEntity((Entity)null, AxisAlignedBB.getAABBPool().getAABB(par1, par3, par5, par1 + 1.0D, par3 + 1.0D, par5 + 1.0D), IEntitySelector.selectInventories);
/*     */       
/* 596 */       if (var14 != null && var14.size() > 0)
/*     */       {
/* 598 */         var7 = var14.get(par0World.rand.nextInt(var14.size()));
/*     */       }
/*     */     } 
/*     */     
/* 602 */     return var7;
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
/*     */   private static boolean areItemStacksEqualItem(ItemStack par0ItemStack, ItemStack par1ItemStack) {
/* 623 */     if (par0ItemStack.stackSize > par0ItemStack.getMaxStackSize()) {
/* 624 */       return false;
/*     */     }
/* 626 */     return ItemStack.areItemStacksEqual(par0ItemStack, par1ItemStack, true, false, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getXPos() {
/* 634 */     return this.xCoord;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYPos() {
/* 642 */     return this.yCoord;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getZPos() {
/* 650 */     return this.zCoord;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferCooldown(int par1) {
/* 655 */     this.transferCooldown = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCoolingDown() {
/* 660 */     return (this.transferCooldown > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 666 */     ItemStack[] item_stacks = this.hopperItemStacks;
/*     */     
/* 668 */     for (int i = 0; i < item_stacks.length; i++) {
/* 669 */       item_stacks[i] = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public int getX() {
/* 674 */     return this.xCoord;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 679 */     return this.yCoord;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getZ() {
/* 684 */     return this.zCoord;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityHopper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */