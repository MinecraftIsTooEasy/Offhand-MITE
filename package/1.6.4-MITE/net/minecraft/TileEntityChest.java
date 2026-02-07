/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TileEntityChest
/*     */   extends TileEntity implements IInventory {
/*   9 */   private ItemStack[] chestContents = new ItemStack[36];
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean adjacentChestChecked;
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntityChest adjacentChestZNeg;
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntityChest adjacentChestXPos;
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntityChest adjacentChestXNeg;
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntityChest adjacentChestZPosition;
/*     */ 
/*     */ 
/*     */   
/*     */   public float lidAngle;
/*     */ 
/*     */ 
/*     */   
/*     */   public float prevLidAngle;
/*     */ 
/*     */ 
/*     */   
/*     */   public int numUsingPlayers;
/*     */ 
/*     */   
/*     */   private int ticksSinceSync;
/*     */ 
/*     */   
/*     */   private EnumChestType cached_chest_type;
/*     */ 
/*     */   
/*     */   private float compost;
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntityChest() {
/*  55 */     this.cached_chest_type = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntityChest(EnumChestType chest_type, Block block) {
/*  60 */     this.cached_chest_type = chest_type;
/*     */     
/*  62 */     setBlock(block);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  70 */     return 27;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  78 */     return this.chestContents[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  87 */     if (this.chestContents[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/*  91 */       if ((this.chestContents[par1]).stackSize <= par2) {
/*     */         
/*  93 */         ItemStack itemStack = this.chestContents[par1];
/*  94 */         this.chestContents[par1] = null;
/*  95 */         onInventoryChanged();
/*  96 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 100 */       ItemStack var3 = this.chestContents[par1].splitStack(par2);
/*     */       
/* 102 */       if ((this.chestContents[par1]).stackSize == 0)
/*     */       {
/* 104 */         this.chestContents[par1] = null;
/*     */       }
/*     */       
/* 107 */       onInventoryChanged();
/* 108 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/* 123 */     if (this.chestContents[par1] != null) {
/*     */       
/* 125 */       ItemStack var2 = this.chestContents[par1];
/* 126 */       this.chestContents[par1] = null;
/* 127 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 140 */     this.chestContents[par1] = par2ItemStack;
/*     */     
/* 142 */     if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
/*     */     {
/* 144 */       par2ItemStack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */     
/* 147 */     onInventoryChanged();
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
/* 160 */     return "container.chest";
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
/* 185 */     super.readFromNBT(par1NBTTagCompound);
/* 186 */     NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
/* 187 */     this.chestContents = new ItemStack[getSizeInventory()];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 196 */       NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/* 197 */       int var5 = var4.getByte("Slot") & 0xFF;
/*     */       
/* 199 */       if (var5 >= 0 && var5 < this.chestContents.length)
/*     */       {
/* 201 */         this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */     
/* 205 */     this.compost = par1NBTTagCompound.getFloat("compost");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 213 */     super.writeToNBT(par1NBTTagCompound);
/* 214 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 216 */     for (int var3 = 0; var3 < this.chestContents.length; var3++) {
/*     */       
/* 218 */       if (this.chestContents[var3] != null) {
/*     */         
/* 220 */         NBTTagCompound var4 = new NBTTagCompound();
/* 221 */         var4.setByte("Slot", (byte)var3);
/* 222 */         this.chestContents[var3].writeToNBT(var4);
/* 223 */         var2.appendTag(var4);
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     par1NBTTagCompound.setTag("Items", var2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     par1NBTTagCompound.setFloat("compost", this.compost);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 243 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 251 */     return (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) ? false : ((par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateContainingBlockInfo() {
/* 260 */     super.updateContainingBlockInfo();
/* 261 */     this.adjacentChestChecked = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_90009_a(TileEntityChest par1TileEntityChest, int par2) {
/* 266 */     if (par1TileEntityChest.isInvalid()) {
/*     */       
/* 268 */       this.adjacentChestChecked = false;
/*     */     }
/* 270 */     else if (this.adjacentChestChecked) {
/*     */       
/* 272 */       switch (par2) {
/*     */         
/*     */         case 0:
/* 275 */           if (this.adjacentChestZPosition != par1TileEntityChest)
/*     */           {
/* 277 */             this.adjacentChestChecked = false;
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 283 */           if (this.adjacentChestXNeg != par1TileEntityChest)
/*     */           {
/* 285 */             this.adjacentChestChecked = false;
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 291 */           if (this.adjacentChestZNeg != par1TileEntityChest)
/*     */           {
/* 293 */             this.adjacentChestChecked = false;
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 299 */           if (this.adjacentChestXPos != par1TileEntityChest)
/*     */           {
/* 301 */             this.adjacentChestChecked = false;
/*     */           }
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkForAdjacentChests() {
/* 312 */     if (!this.adjacentChestChecked) {
/*     */       
/* 314 */       this.adjacentChestChecked = true;
/* 315 */       this.adjacentChestZNeg = null;
/* 316 */       this.adjacentChestXPos = null;
/* 317 */       this.adjacentChestXNeg = null;
/* 318 */       this.adjacentChestZPosition = null;
/*     */       
/* 320 */       if (func_94044_a(this.xCoord - 1, this.yCoord, this.zCoord))
/*     */       {
/* 322 */         this.adjacentChestXNeg = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
/*     */       }
/*     */       
/* 325 */       if (func_94044_a(this.xCoord + 1, this.yCoord, this.zCoord))
/*     */       {
/* 327 */         this.adjacentChestXPos = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
/*     */       }
/*     */       
/* 330 */       if (func_94044_a(this.xCoord, this.yCoord, this.zCoord - 1))
/*     */       {
/* 332 */         this.adjacentChestZNeg = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
/*     */       }
/*     */       
/* 335 */       if (func_94044_a(this.xCoord, this.yCoord, this.zCoord + 1))
/*     */       {
/* 337 */         this.adjacentChestZPosition = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
/*     */       }
/*     */       
/* 340 */       if (this.adjacentChestZNeg != null)
/*     */       {
/* 342 */         this.adjacentChestZNeg.func_90009_a(this, 0);
/*     */       }
/*     */       
/* 345 */       if (this.adjacentChestZPosition != null)
/*     */       {
/* 347 */         this.adjacentChestZPosition.func_90009_a(this, 2);
/*     */       }
/*     */       
/* 350 */       if (this.adjacentChestXPos != null)
/*     */       {
/* 352 */         this.adjacentChestXPos.func_90009_a(this, 1);
/*     */       }
/*     */       
/* 355 */       if (this.adjacentChestXNeg != null)
/*     */       {
/* 357 */         this.adjacentChestXNeg.func_90009_a(this, 3);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_94044_a(int par1, int par2, int par3) {
/* 364 */     Block var4 = Block.blocksList[this.worldObj.getBlockId(par1, par2, par3)];
/*     */     
/* 366 */     return (var4 != null && var4 instanceof BlockChest && !(var4 instanceof BlockStrongbox)) ? ((((BlockChest)var4).chest_type == getChestType())) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEntity() {
/* 375 */     super.updateEntity();
/* 376 */     checkForAdjacentChests();
/* 377 */     this.ticksSinceSync++;
/*     */ 
/*     */     
/* 380 */     if (!this.worldObj.isRemote && this.numUsingPlayers != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
/*     */       
/* 382 */       this.numUsingPlayers = 0;
/* 383 */       float f = 5.0F;
/* 384 */       List var2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB((this.xCoord - f), (this.yCoord - f), (this.zCoord - f), ((this.xCoord + 1) + f), ((this.yCoord + 1) + f), ((this.zCoord + 1) + f)));
/* 385 */       Iterator<EntityPlayer> var3 = var2.iterator();
/*     */       
/* 387 */       while (var3.hasNext()) {
/*     */         
/* 389 */         EntityPlayer var4 = var3.next();
/*     */         
/* 391 */         if (var4.openContainer instanceof ContainerChest) {
/*     */           
/* 393 */           IInventory var5 = ((ContainerChest)var4.openContainer).getLowerChestInventory();
/*     */           
/* 395 */           if (var5 == this || (var5 instanceof InventoryLargeChest && ((InventoryLargeChest)var5).isPartOfLargeChest(this)))
/*     */           {
/* 397 */             this.numUsingPlayers++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 403 */     this.prevLidAngle = this.lidAngle;
/* 404 */     float var1 = 0.1F;
/*     */ 
/*     */     
/* 407 */     if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
/*     */       
/* 409 */       double var8 = this.xCoord + 0.5D;
/* 410 */       double var11 = this.zCoord + 0.5D;
/*     */       
/* 412 */       if (this.adjacentChestZPosition != null)
/*     */       {
/* 414 */         var11 += 0.5D;
/*     */       }
/*     */       
/* 417 */       if (this.adjacentChestXPos != null)
/*     */       {
/* 419 */         var8 += 0.5D;
/*     */       }
/*     */       
/* 422 */       this.worldObj.playSoundEffect(var8, this.yCoord + 0.5D, var11, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*     */     } 
/*     */     
/* 425 */     if ((this.numUsingPlayers == 0 && this.lidAngle > 0.0F) || (this.numUsingPlayers > 0 && this.lidAngle < 1.0F)) {
/*     */       
/* 427 */       float var9 = this.lidAngle;
/*     */       
/* 429 */       if (this.numUsingPlayers > 0) {
/*     */         
/* 431 */         this.lidAngle += var1;
/*     */       }
/*     */       else {
/*     */         
/* 435 */         this.lidAngle -= var1;
/*     */       } 
/*     */       
/* 438 */       if (this.lidAngle > 1.0F)
/*     */       {
/* 440 */         this.lidAngle = 1.0F;
/*     */       }
/*     */       
/* 443 */       float var10 = 0.5F;
/*     */       
/* 445 */       if (this.lidAngle < var10 && var9 >= var10 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
/*     */         
/* 447 */         double var11 = this.xCoord + 0.5D;
/* 448 */         double var6 = this.zCoord + 0.5D;
/*     */         
/* 450 */         if (this.adjacentChestZPosition != null)
/*     */         {
/* 452 */           var6 += 0.5D;
/*     */         }
/*     */         
/* 455 */         if (this.adjacentChestXPos != null)
/*     */         {
/* 457 */           var11 += 0.5D;
/*     */         }
/*     */         
/* 460 */         this.worldObj.playSoundEffect(var11, this.yCoord + 0.5D, var6, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/*     */       
/* 463 */       if (this.lidAngle < 0.0F)
/*     */       {
/* 465 */         this.lidAngle = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumLiveWorms() {
/* 472 */     int num_live_worms = 0;
/*     */     
/* 474 */     ItemStack[] item_stacks = this.chestContents;
/*     */     
/* 476 */     for (int i = 0; i < item_stacks.length; i++) {
/*     */       
/* 478 */       ItemStack item_stack = item_stacks[i];
/*     */       
/* 480 */       if (item_stack != null && item_stack.getItem() == Item.wormRaw) {
/* 481 */         num_live_worms += item_stack.stackSize;
/*     */       }
/*     */     } 
/* 484 */     return num_live_worms;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkForWormComposting() {
/* 489 */     int num_worms = getNumLiveWorms();
/*     */     
/* 491 */     if (num_worms < 1) {
/*     */       return;
/*     */     }
/* 494 */     if (this.compost >= 1.0F) {
/*     */       
/* 496 */       convertAsMuchCompostAsPossible();
/*     */       
/*     */       return;
/*     */     } 
/* 500 */     for (int i = 0; i < num_worms; i++) {
/*     */       
/* 502 */       int source_index = getIndexOfRandomWormFood();
/*     */       
/* 504 */       if (source_index < 0) {
/*     */         return;
/*     */       }
/* 507 */       ItemStack source_item_stack = getStackInSlot(source_index);
/*     */       
/* 509 */       float composting_value = source_item_stack.getItem().getCompostingValue();
/*     */       
/* 511 */       int chance_in = (int)(100.0F * composting_value);
/*     */       
/* 513 */       if (this.worldObj.rand.nextInt(chance_in) == 0) {
/*     */         
/* 515 */         int num_required_empty_slots = 0;
/*     */         
/* 517 */         int destination_for_manure_index = canChestAcceptOneItem(Item.manure, true);
/*     */         
/* 519 */         if (destination_for_manure_index < 0) {
/* 520 */           num_required_empty_slots++;
/*     */         }
/* 522 */         Item composting_remains = source_item_stack.getItem().getCompostingRemains(source_item_stack);
/*     */         
/* 524 */         int destination_for_composting_remains_index = (composting_remains == null) ? -1 : canChestAcceptOneItem(composting_remains, true);
/*     */         
/* 526 */         if (composting_remains != null && destination_for_composting_remains_index < 0) {
/* 527 */           num_required_empty_slots++;
/*     */         }
/* 529 */         if (source_item_stack.itemID == Block.pumpkinLantern.blockID && canChestAcceptOneItem(Item.pumpkinSeeds, true) < 0) {
/* 530 */           num_required_empty_slots++;
/*     */         }
/* 532 */         if (num_required_empty_slots > 0 && source_item_stack.stackSize == 1) {
/* 533 */           num_required_empty_slots--;
/*     */         }
/* 535 */         if (getNumEmptySlots() >= num_required_empty_slots) {
/*     */ 
/*     */           
/* 538 */           decrStackSize(source_index, 1);
/*     */           
/* 540 */           this.compost += composting_value;
/*     */           
/* 542 */           if (composting_remains != null) {
/*     */             
/* 544 */             if (destination_for_composting_remains_index < 0) {
/* 545 */               destination_for_composting_remains_index = canChestAcceptOneItem(composting_remains, false);
/*     */             }
/* 547 */             ItemStack destination_item_stack = getStackInSlot(destination_for_composting_remains_index);
/*     */             
/* 549 */             if (destination_item_stack == null) {
/* 550 */               setInventorySlotContents(destination_for_composting_remains_index, new ItemStack(composting_remains));
/*     */             } else {
/* 552 */               destination_item_stack.stackSize++;
/*     */             } 
/*     */           } 
/* 555 */           if (source_item_stack.itemID == Block.pumpkinLantern.blockID) {
/*     */             
/* 557 */             destination_for_composting_remains_index = canChestAcceptOneItem(Item.getItem(Block.torchWood), false);
/*     */             
/* 559 */             ItemStack destination_item_stack = getStackInSlot(destination_for_composting_remains_index);
/*     */             
/* 561 */             if (destination_item_stack == null) {
/* 562 */               setInventorySlotContents(destination_for_composting_remains_index, new ItemStack(Block.torchWood));
/*     */             } else {
/* 564 */               destination_item_stack.stackSize++;
/*     */             } 
/*     */           } 
/* 567 */           convertAsMuchCompostAsPossible();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void convertAsMuchCompostAsPossible() {
/*     */     int destination_for_manure_index;
/* 576 */     while (this.compost >= 1.0F && (destination_for_manure_index = canChestAcceptOneItem(Item.manure, false)) >= 0) {
/*     */       
/* 578 */       ItemStack destination_item_stack = getStackInSlot(destination_for_manure_index);
/*     */       
/* 580 */       if (destination_item_stack == null) {
/* 581 */         setInventorySlotContents(destination_for_manure_index, new ItemStack(Item.manure));
/*     */       } else {
/* 583 */         destination_item_stack.stackSize++;
/*     */       } 
/* 585 */       this.compost--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int canChestAcceptOneItem(Item item, boolean ignore_empty_slots) {
/* 592 */     int size = getSizeInventory();
/*     */     int i;
/* 594 */     for (i = 0; i < size; i++) {
/*     */       
/* 596 */       ItemStack item_stack = getStackInSlot(i);
/*     */       
/* 598 */       if (item_stack != null && item_stack.getItem() == item && item_stack.stackSize < item_stack.getMaxStackSize()) {
/* 599 */         return i;
/*     */       }
/*     */     } 
/* 602 */     if (!ignore_empty_slots)
/*     */     {
/* 604 */       for (i = 0; i < size; i++) {
/*     */         
/* 606 */         ItemStack item_stack = getStackInSlot(i);
/*     */         
/* 608 */         if (item_stack == null) {
/* 609 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/* 613 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getIndexOfRandomWormFood() {
/* 619 */     List<Integer> list = new ArrayList();
/*     */     
/* 621 */     int size = getSizeInventory();
/*     */     
/* 623 */     for (int i = 0; i < size; i++) {
/*     */       
/* 625 */       ItemStack item_stack = getStackInSlot(i);
/*     */       
/* 627 */       if (item_stack != null && item_stack.canBeCompostedByWorms()) {
/* 628 */         list.add(Integer.valueOf(i));
/*     */       }
/*     */     } 
/* 631 */     return list.isEmpty() ? -1 : ((Integer)list.get(this.worldObj.rand.nextInt(list.size()))).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private int getNumEmptySlots() {
/* 636 */     int num_empty_slots = 0;
/*     */     
/* 638 */     int size = getSizeInventory();
/*     */     
/* 640 */     for (int i = 0; i < size; i++) {
/*     */       
/* 642 */       ItemStack item_stack = getStackInSlot(i);
/*     */       
/* 644 */       if (item_stack == null) {
/* 645 */         num_empty_slots++;
/*     */       }
/*     */     } 
/* 648 */     return num_empty_slots;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean receiveClientEvent(int par1, int par2) {
/* 656 */     if (par1 == 1) {
/*     */       
/* 658 */       this.numUsingPlayers = par2;
/* 659 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 663 */     return super.receiveClientEvent(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void openChest() {
/* 669 */     if (this.numUsingPlayers < 0)
/*     */     {
/* 671 */       this.numUsingPlayers = 0;
/*     */     }
/*     */     
/* 674 */     this.numUsingPlayers++;
/* 675 */     this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID, 1, this.numUsingPlayers);
/* 676 */     this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID);
/* 677 */     this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, (getBlockType()).blockID);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeChest() {
/* 682 */     if (getBlockType() != null && getBlockType() instanceof BlockChest) {
/*     */       
/* 684 */       this.numUsingPlayers--;
/* 685 */       this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID, 1, this.numUsingPlayers);
/* 686 */       this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID);
/* 687 */       this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, (getBlockType()).blockID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
/* 696 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 704 */     super.invalidate();
/* 705 */     updateContainingBlockInfo();
/* 706 */     checkForAdjacentChests();
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
/*     */   public EnumChestType getChestType() {
/* 726 */     if (this.cached_chest_type == null) {
/*     */       
/* 728 */       if (this.worldObj == null || !(getBlockType() instanceof BlockChest)) {
/* 729 */         return EnumChestType.normal;
/*     */       }
/* 731 */       this.cached_chest_type = ((BlockChest)getBlockType()).chest_type;
/*     */     } 
/*     */     
/* 734 */     return this.cached_chest_type;
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
/*     */   public void destroyInventory() {
/* 748 */     ItemStack[] item_stacks = this.chestContents;
/*     */     
/* 750 */     for (int i = 0; i < item_stacks.length; i++)
/* 751 */       item_stacks[i] = null; 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */