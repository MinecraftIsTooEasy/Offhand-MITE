/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ public abstract class Container
/*     */ {
/*  12 */   public List inventoryItemStacks = new ArrayList();
/*     */ 
/*     */   
/*  15 */   public List inventorySlots = new ArrayList();
/*     */   public int windowId;
/*     */   private short transactionID;
/*  18 */   private int field_94535_f = -1;
/*     */   private int field_94536_g;
/*  20 */   private final Set field_94537_h = new HashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   protected List crafters = new ArrayList();
/*  26 */   private Set playerList = new HashSet();
/*     */   
/*     */   int[] stack_sizes_before;
/*     */   
/*     */   private boolean unlock_next_tick;
/*     */   
/*     */   public final EntityPlayer player;
/*     */   
/*     */   public final World world;
/*     */   
/*     */   public boolean slot_was_locked_this_tick;
/*     */   
/*     */   public Slot slot_that_did_not_accept_item;
/*     */   
/*     */   public static final int REPAIR_FAIL_NOT_ENOUGH_XP = 1;
/*     */   
/*     */   public static final int REPAIR_FAIL_NEEDS_HARDER_ANVIL = 2;
/*     */   
/*     */   public int repair_fail_condition;
/*     */   
/*     */   public boolean crafting_result_shown_but_prevented;
/*     */ 
/*     */   
/*     */   public Container(EntityPlayer player) {
/*  50 */     this.player = player;
/*  51 */     this.world = (player == null) ? null : player.worldObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Slot addSlotToContainer(Slot par1Slot) {
/*  59 */     par1Slot.slotNumber = this.inventorySlots.size();
/*  60 */     this.inventorySlots.add(par1Slot);
/*  61 */     this.inventoryItemStacks.add(null);
/*     */     
/*  63 */     par1Slot.setContainer(this);
/*     */     
/*  65 */     return par1Slot;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCraftingToCrafters(ICrafting par1ICrafting) {
/*  70 */     if (this.crafters.contains(par1ICrafting))
/*     */     {
/*  72 */       throw new IllegalArgumentException("Listener already listening");
/*     */     }
/*     */ 
/*     */     
/*  76 */     this.crafters.add(par1ICrafting);
/*  77 */     par1ICrafting.sendContainerAndContentsToPlayer(this, getInventory());
/*  78 */     detectAndSendChanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCraftingFromCrafters(ICrafting par1ICrafting) {
/*  87 */     this.crafters.remove(par1ICrafting);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventory() {
/*  95 */     ArrayList<ItemStack> var1 = new ArrayList();
/*     */     
/*  97 */     for (int var2 = 0; var2 < this.inventorySlots.size(); var2++)
/*     */     {
/*  99 */       var1.add(((Slot)this.inventorySlots.get(var2)).getStack());
/*     */     }
/*     */     
/* 102 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/* 110 */     for (int var1 = 0; var1 < this.inventorySlots.size(); var1++) {
/*     */       
/* 112 */       ItemStack var2 = ((Slot)this.inventorySlots.get(var1)).getStack();
/* 113 */       ItemStack var3 = this.inventoryItemStacks.get(var1);
/*     */       
/* 115 */       if (!ItemStack.areItemStacksEqual(var3, var2)) {
/*     */         
/* 117 */         var3 = (var2 == null) ? null : var2.copy();
/* 118 */         this.inventoryItemStacks.set(var1, var3);
/*     */         
/* 120 */         for (int var4 = 0; var4 < this.crafters.size(); var4++)
/*     */         {
/* 122 */           ((ICrafting)this.crafters.get(var4)).sendSlotContents(this, var1, var3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enchantItem(EntityPlayer par1EntityPlayer, int par2) {
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Slot getSlotFromInventory(IInventory par1IInventory, int par2) {
/* 138 */     for (int var3 = 0; var3 < this.inventorySlots.size(); var3++) {
/*     */       
/* 140 */       Slot var4 = this.inventorySlots.get(var3);
/*     */       
/* 142 */       if (var4.isSlotInInventory(par1IInventory, par2))
/*     */       {
/* 144 */         return var4;
/*     */       }
/*     */     } 
/*     */     
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Slot getSlot(int par1) {
/* 153 */     return this.inventorySlots.get(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/* 161 */     Slot var3 = this.inventorySlots.get(par2);
/* 162 */     return (var3 != null) ? var3.getStack() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ItemStack slotClick(int par1, int par2, int par3, boolean holding_shift, EntityPlayer par4EntityPlayer) {
/* 171 */     if (par1 == 2) {
/*     */       
/* 173 */       Container container = getSlot(par1).getContainer();
/*     */       
/* 175 */       if (container instanceof ContainerRepair && container.repair_fail_condition != 0) {
/* 176 */         return null;
/*     */       }
/*     */     } 
/* 179 */     if (par2 == 2 && par3 == 3 && par1 != -999 && !par4EntityPlayer.inCreativeMode() && getSlot(par1).getHasStack())
/*     */     {
/* 181 */       if (handleMiddleMouseClick(par4EntityPlayer, par1, holding_shift)) {
/* 182 */         return null;
/*     */       }
/*     */     }
/* 185 */     ItemStack var5 = null;
/* 186 */     InventoryPlayer var6 = par4EntityPlayer.inventory;
/*     */ 
/*     */ 
/*     */     
/* 190 */     if (par3 == 5) {
/*     */       
/* 192 */       int var7 = this.field_94536_g;
/* 193 */       this.field_94536_g = func_94532_c(par2);
/*     */       
/* 195 */       if ((var7 != 1 || this.field_94536_g != 2) && var7 != this.field_94536_g) {
/*     */         
/* 197 */         func_94533_d();
/*     */       }
/* 199 */       else if (var6.getItemStack() == null) {
/*     */         
/* 201 */         func_94533_d();
/*     */       }
/* 203 */       else if (this.field_94536_g == 0) {
/*     */         
/* 205 */         this.field_94535_f = func_94529_b(par2);
/*     */         
/* 207 */         if (func_94528_d(this.field_94535_f))
/*     */         {
/* 209 */           this.field_94536_g = 1;
/* 210 */           this.field_94537_h.clear();
/*     */         }
/*     */         else
/*     */         {
/* 214 */           func_94533_d();
/*     */         }
/*     */       
/* 217 */       } else if (this.field_94536_g == 1) {
/*     */         
/* 219 */         Slot var8 = this.inventorySlots.get(par1);
/*     */         
/* 221 */         if (var8 != null && func_94527_a(var8, var6.getItemStack(), true) && var8.isItemValid(var6.getItemStack()) && (var6.getItemStack()).stackSize > this.field_94537_h.size() && canDragIntoSlot(var8))
/*     */         {
/* 223 */           this.field_94537_h.add(var8);
/*     */         }
/*     */       }
/* 226 */       else if (this.field_94536_g == 2) {
/*     */         
/* 228 */         if (!this.field_94537_h.isEmpty()) {
/*     */           
/* 230 */           ItemStack var17 = var6.getItemStack().copy();
/* 231 */           int var9 = (var6.getItemStack()).stackSize;
/* 232 */           Iterator<Slot> var10 = this.field_94537_h.iterator();
/*     */           
/* 234 */           while (var10.hasNext()) {
/*     */             
/* 236 */             Slot var11 = var10.next();
/*     */             
/* 238 */             if (var11 != null && func_94527_a(var11, var6.getItemStack(), true) && var11.isItemValid(var6.getItemStack()) && (var6.getItemStack()).stackSize >= this.field_94537_h.size() && canDragIntoSlot(var11)) {
/*     */               
/* 240 */               ItemStack var12 = var17.copy();
/* 241 */               int var13 = var11.getHasStack() ? (var11.getStack()).stackSize : 0;
/* 242 */               func_94525_a(this.field_94537_h, this.field_94535_f, var12, var13);
/*     */               
/* 244 */               if (var12.stackSize > var12.getMaxStackSize())
/*     */               {
/* 246 */                 var12.stackSize = var12.getMaxStackSize();
/*     */               }
/*     */               
/* 249 */               if (var12.stackSize > var11.getSlotStackLimit())
/*     */               {
/* 251 */                 var12.stackSize = var11.getSlotStackLimit();
/*     */               }
/*     */               
/* 254 */               var9 -= var12.stackSize - var13;
/* 255 */               var11.putStack(var12);
/*     */             } 
/*     */           } 
/*     */           
/* 259 */           var17.stackSize = var9;
/*     */           
/* 261 */           if (var17.stackSize <= 0)
/*     */           {
/* 263 */             var17 = null;
/*     */           }
/*     */           
/* 266 */           var6.setItemStack(var17);
/*     */         } 
/*     */         
/* 269 */         func_94533_d();
/*     */       }
/*     */       else {
/*     */         
/* 273 */         func_94533_d();
/*     */       }
/*     */     
/* 276 */     } else if (this.field_94536_g != 0) {
/*     */       
/* 278 */       func_94533_d();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 286 */     else if ((par3 == 0 || par3 == 1) && (par2 == 0 || par2 == 1)) {
/*     */       
/* 288 */       if (par1 == -999) {
/*     */         
/* 290 */         if (var6.getItemStack() != null && par1 == -999)
/*     */         {
/* 292 */           if (par2 == 0) {
/*     */             
/* 294 */             par4EntityPlayer.dropPlayerItem(var6.getItemStack());
/* 295 */             var6.setItemStack((ItemStack)null);
/*     */           } 
/*     */           
/* 298 */           if (par2 == 1)
/*     */           {
/* 300 */             par4EntityPlayer.dropPlayerItem(var6.getItemStack().splitStack(1));
/*     */             
/* 302 */             if ((var6.getItemStack()).stackSize == 0)
/*     */             {
/* 304 */               var6.setItemStack((ItemStack)null);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 310 */       } else if (par3 == 1 && par1 >= 0 && !(getSlot(par1) instanceof SlotCrafting)) {
/*     */         
/* 312 */         if (par1 < 0)
/*     */         {
/* 314 */           return null;
/*     */         }
/*     */         
/* 317 */         Slot var16 = this.inventorySlots.get(par1);
/*     */         
/* 319 */         if (var16 != null && var16.canTakeStack(par4EntityPlayer)) {
/*     */           
/* 321 */           ItemStack var17 = transferStackInSlot(par4EntityPlayer, par1);
/*     */           
/* 323 */           if (var17 != null)
/*     */           {
/* 325 */             int var9 = var17.itemID;
/* 326 */             var5 = var17.copy();
/*     */             
/* 328 */             if (var16 != null && var16.getStack() != null && (var16.getStack()).itemID == var9)
/*     */             {
/*     */               
/* 331 */               retrySlotClick(par1, par2, true, holding_shift, par4EntityPlayer);
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 338 */         if (par1 < 0)
/*     */         {
/* 340 */           return null;
/*     */         }
/*     */         
/* 343 */         Slot var16 = this.inventorySlots.get(par1);
/*     */         
/* 345 */         if (var16 != null)
/*     */         {
/* 347 */           ItemStack var17 = var16.getStack();
/* 348 */           ItemStack var19 = var6.getItemStack();
/*     */           
/* 350 */           if (var17 != null)
/*     */           {
/* 352 */             var5 = var17.copy();
/*     */           }
/*     */           
/* 355 */           if (var17 == null) {
/*     */             
/* 357 */             if (var19 != null && var16.isItemValid(var19)) {
/*     */               
/* 359 */               int var21 = (par2 == 0) ? var19.stackSize : 1;
/*     */               
/* 361 */               if (var21 > var16.getSlotStackLimit())
/*     */               {
/* 363 */                 var21 = var16.getSlotStackLimit();
/*     */               }
/*     */               
/* 366 */               if (var19.stackSize >= var21)
/*     */               {
/* 368 */                 var16.putStack(var19.splitStack(var21));
/*     */               }
/*     */               
/* 371 */               if (var19.stackSize == 0)
/*     */               {
/* 373 */                 var6.setItemStack((ItemStack)null);
/*     */               }
/*     */             } 
/*     */             
/* 377 */             if (var19 != null && !var16.isItemValid(var19)) {
/* 378 */               this.slot_that_did_not_accept_item = var16;
/*     */             }
/* 380 */           } else if (var16.canTakeStack(par4EntityPlayer)) {
/*     */             
/* 382 */             if (var19 == null) {
/*     */               
/* 384 */               int var21 = (par2 == 0) ? var17.stackSize : ((var17.stackSize + 1) / 2);
/* 385 */               ItemStack var23 = var16.decrStackSize(var21);
/* 386 */               var6.setItemStack(var23);
/*     */               
/* 388 */               if (var17.stackSize == 0)
/*     */               {
/* 390 */                 var16.putStack((ItemStack)null);
/*     */               }
/*     */               
/* 393 */               var16.onPickupFromSlot(par4EntityPlayer, var6.getItemStack());
/*     */             }
/* 395 */             else if (var16.isItemValid(var19)) {
/*     */ 
/*     */               
/* 398 */               if (ItemStack.areItemStacksEqual(var17, var19, true))
/*     */               {
/* 400 */                 int var21 = (par2 == 0) ? var19.stackSize : 1;
/*     */                 
/* 402 */                 if (var21 > var16.getSlotStackLimit() - var17.stackSize)
/*     */                 {
/* 404 */                   var21 = var16.getSlotStackLimit() - var17.stackSize;
/*     */                 }
/*     */                 
/* 407 */                 if (var21 > var19.getMaxStackSize() - var17.stackSize)
/*     */                 {
/* 409 */                   var21 = var19.getMaxStackSize() - var17.stackSize;
/*     */                 }
/*     */                 
/* 412 */                 var19.splitStack(var21);
/*     */                 
/* 414 */                 if (var19.stackSize == 0)
/*     */                 {
/* 416 */                   var6.setItemStack((ItemStack)null);
/*     */                 }
/*     */                 
/* 419 */                 var17.stackSize += var21;
/*     */               }
/* 421 */               else if (var19.stackSize <= var16.getSlotStackLimit())
/*     */               {
/* 423 */                 var16.putStack(var19);
/* 424 */                 var6.setItemStack(var17);
/*     */               }
/*     */             
/*     */             }
/* 428 */             else if (var19.getMaxStackSize() > 1 && ItemStack.areItemStacksEqual(var17, var19, true, false, true)) {
/*     */ 
/*     */               
/* 431 */               int var21 = (par2 == 0) ? var17.stackSize : ((var17.stackSize + 1) / 2);
/*     */               
/* 433 */               if (var21 + var19.stackSize > var19.getMaxStackSize()) {
/* 434 */                 var21 = var19.getMaxStackSize() - var19.stackSize;
/*     */               }
/* 436 */               if (var21 > 0 && var21 + var19.stackSize <= var19.getMaxStackSize())
/*     */               {
/* 438 */                 var19.stackSize += var21;
/* 439 */                 var17 = var16.decrStackSize(var21);
/*     */                 
/* 441 */                 if (var17.stackSize == 0)
/*     */                 {
/* 443 */                   var16.putStack((ItemStack)null);
/*     */                 }
/*     */                 
/* 446 */                 var16.onPickupFromSlot(par4EntityPlayer, var6.getItemStack());
/*     */               }
/*     */             
/* 449 */             } else if (var19 != null && !var16.isItemValid(var19)) {
/*     */               
/* 451 */               this.slot_that_did_not_accept_item = var16;
/*     */             } 
/*     */           } 
/*     */           
/* 455 */           var16.onSlotChanged();
/* 456 */           var16.onSlotClicked(par4EntityPlayer, par2, this);
/*     */         }
/*     */       
/*     */       } 
/* 460 */     } else if (par3 == 2 && par2 >= 0 && par2 < 9) {
/*     */       
/* 462 */       Slot var16 = this.inventorySlots.get(par1);
/*     */       
/* 464 */       if (var16.canTakeStack(par4EntityPlayer)) {
/*     */         int i;
/* 466 */         ItemStack var17 = var6.getStackInSlot(par2);
/* 467 */         boolean var18 = (var17 == null || (var16.inventory == var6 && var16.isItemValid(var17)));
/* 468 */         int var21 = -1;
/*     */         
/* 470 */         if (!var18) {
/*     */           
/* 472 */           var21 = var6.getFirstEmptyStack();
/* 473 */           i = var18 | ((var21 > -1) ? 1 : 0);
/*     */         } 
/*     */         
/* 476 */         if (var16.getHasStack() && i != 0) {
/*     */           
/* 478 */           ItemStack var23 = var16.getStack();
/* 479 */           var6.setInventorySlotContents(par2, var23.copy());
/*     */           
/* 481 */           if ((var16.inventory != var6 || !var16.isItemValid(var17)) && var17 != null) {
/*     */             
/* 483 */             if (var21 > -1)
/*     */             {
/* 485 */               var6.addItemStackToInventory(var17);
/* 486 */               var16.decrStackSize(var23.stackSize);
/* 487 */               var16.putStack((ItemStack)null);
/* 488 */               var16.onPickupFromSlot(par4EntityPlayer, var23);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 493 */             var16.decrStackSize(var23.stackSize);
/* 494 */             var16.putStack(var17);
/* 495 */             var16.onPickupFromSlot(par4EntityPlayer, var23);
/*     */           }
/*     */         
/* 498 */         } else if (!var16.getHasStack() && var17 != null && var16.isItemValid(var17)) {
/*     */           
/* 500 */           var6.setInventorySlotContents(par2, (ItemStack)null);
/* 501 */           var16.putStack(var17);
/*     */         }
/*     */       
/*     */       } 
/* 505 */     } else if (par3 == 3 && par4EntityPlayer.capabilities.isCreativeMode && var6.getItemStack() == null && par1 >= 0) {
/*     */       
/* 507 */       Slot var16 = this.inventorySlots.get(par1);
/*     */       
/* 509 */       if (var16 != null && var16.getHasStack())
/*     */       {
/* 511 */         ItemStack var17 = var16.getStack().copy();
/* 512 */         var17.stackSize = var17.getMaxStackSize();
/* 513 */         var6.setItemStack(var17);
/*     */       }
/*     */     
/* 516 */     } else if (par3 == 4 && var6.getItemStack() == null && par1 >= 0) {
/*     */       
/* 518 */       Slot var16 = this.inventorySlots.get(par1);
/*     */       
/* 520 */       if (var16 != null && var16.getHasStack() && var16.canTakeStack(par4EntityPlayer))
/*     */       {
/* 522 */         ItemStack var17 = var16.decrStackSize((par2 == 0) ? 1 : (var16.getStack()).stackSize);
/* 523 */         var16.onPickupFromSlot(par4EntityPlayer, var17);
/* 524 */         par4EntityPlayer.dropPlayerItem(var17);
/*     */       }
/*     */     
/* 527 */     } else if (par3 == 6 && par1 >= 0) {
/*     */       
/* 529 */       Slot var16 = this.inventorySlots.get(par1);
/* 530 */       ItemStack var17 = var6.getItemStack();
/*     */       
/* 532 */       if (var17 != null && (var16 == null || !var16.getHasStack() || !var16.canTakeStack(par4EntityPlayer))) {
/*     */         
/* 534 */         int var9 = (par2 == 0) ? 0 : (this.inventorySlots.size() - 1);
/* 535 */         int var21 = (par2 == 0) ? 1 : -1;
/*     */         
/* 537 */         for (int var20 = 0; var20 < 2; var20++) {
/*     */           int var22;
/* 539 */           for (var22 = var9; var22 >= 0 && var22 < this.inventorySlots.size() && var17.stackSize < var17.getMaxStackSize(); var22 += var21) {
/*     */             
/* 541 */             Slot var24 = this.inventorySlots.get(var22);
/*     */             
/* 543 */             if (var24.getHasStack() && func_94527_a(var24, var17, true) && var24.canTakeStack(par4EntityPlayer) && func_94530_a(var17, var24) && (var20 != 0 || (var24.getStack()).stackSize != var24.getStack().getMaxStackSize())) {
/*     */               
/* 545 */               int var14 = Math.min(var17.getMaxStackSize() - var17.stackSize, (var24.getStack()).stackSize);
/* 546 */               ItemStack var15 = var24.decrStackSize(var14);
/* 547 */               var17.stackSize += var14;
/*     */               
/* 549 */               if (var15.stackSize <= 0)
/*     */               {
/* 551 */                 var24.putStack((ItemStack)null);
/*     */               }
/*     */               
/* 554 */               var24.onPickupFromSlot(par4EntityPlayer, var15);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 560 */       detectAndSendChanges();
/*     */     } 
/*     */ 
/*     */     
/* 564 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot) {
/* 569 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void retrySlotClick(int par1, int par2, boolean par3, boolean holding_shift, EntityPlayer par4EntityPlayer) {
/* 579 */     slotClick(par1, par2, 1, holding_shift, par4EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
/* 587 */     InventoryPlayer var2 = par1EntityPlayer.inventory;
/*     */     
/* 589 */     if (var2.getItemStack() != null) {
/*     */       
/* 591 */       par1EntityPlayer.dropPlayerItem(var2.getItemStack());
/* 592 */       var2.setItemStack((ItemStack)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory par1IInventory) {
/* 601 */     detectAndSendChanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putStackInSlot(int par1, ItemStack par2ItemStack) {
/* 609 */     getSlot(par1).putStack(par2ItemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putStacksInSlots(ItemStack[] par1ArrayOfItemStack) {
/* 617 */     for (int var2 = 0; var2 < par1ArrayOfItemStack.length; var2++)
/*     */     {
/* 619 */       getSlot(var2).putStack(par1ArrayOfItemStack[var2]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int par1, int par2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public short getNextTransactionID(InventoryPlayer par1InventoryPlayer) {
/* 630 */     this.transactionID = (short)(this.transactionID + 1);
/* 631 */     return this.transactionID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPlayerNotUsingContainer(EntityPlayer par1EntityPlayer) {
/* 639 */     return !this.playerList.contains(par1EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerIsPresent(EntityPlayer par1EntityPlayer, boolean par2) {
/* 647 */     if (par2) {
/*     */       
/* 649 */       this.playerList.remove(par1EntityPlayer);
/*     */     }
/*     */     else {
/*     */       
/* 653 */       this.playerList.add(par1EntityPlayer);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean canInteractWith(EntityPlayer paramEntityPlayer);
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4) {
/* 664 */     boolean var5 = false;
/* 665 */     int var6 = par2;
/*     */     
/* 667 */     if (par4)
/*     */     {
/* 669 */       var6 = par3 - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 675 */     if (par1ItemStack.isStackable())
/*     */     {
/* 677 */       while (par1ItemStack.stackSize > 0 && ((!par4 && var6 < par3) || (par4 && var6 >= par2))) {
/*     */         
/* 679 */         Slot var7 = this.inventorySlots.get(var6);
/* 680 */         ItemStack var8 = var7.getStack();
/*     */ 
/*     */         
/* 683 */         if (var8 != null && ItemStack.areItemStacksEqual(var8, par1ItemStack, true, false, true)) {
/*     */           
/* 685 */           int var9 = var8.stackSize + par1ItemStack.stackSize;
/*     */           
/* 687 */           if (var9 <= par1ItemStack.getMaxStackSize()) {
/*     */             
/* 689 */             par1ItemStack.stackSize = 0;
/* 690 */             var8.stackSize = var9;
/* 691 */             var7.onSlotChanged();
/* 692 */             var5 = true;
/*     */           }
/* 694 */           else if (var8.stackSize < par1ItemStack.getMaxStackSize()) {
/*     */             
/* 696 */             par1ItemStack.stackSize -= par1ItemStack.getMaxStackSize() - var8.stackSize;
/* 697 */             var8.stackSize = par1ItemStack.getMaxStackSize();
/* 698 */             var7.onSlotChanged();
/* 699 */             var5 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 703 */         if (par4) {
/*     */           
/* 705 */           var6--;
/*     */           
/*     */           continue;
/*     */         } 
/* 709 */         var6++;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 714 */     if (par1ItemStack.stackSize > 0) {
/*     */       
/* 716 */       if (par4) {
/*     */         
/* 718 */         var6 = par3 - 1;
/*     */       }
/*     */       else {
/*     */         
/* 722 */         var6 = par2;
/*     */       } 
/*     */       
/* 725 */       while ((!par4 && var6 < par3) || (par4 && var6 >= par2)) {
/*     */         
/* 727 */         Slot var7 = this.inventorySlots.get(var6);
/* 728 */         ItemStack var8 = var7.getStack();
/*     */ 
/*     */         
/* 731 */         if (var8 == null && var7.isItemValid(par1ItemStack)) {
/*     */           
/* 733 */           var7.putStack(par1ItemStack.copy());
/* 734 */           var7.onSlotChanged();
/* 735 */           par1ItemStack.stackSize = 0;
/* 736 */           var5 = true;
/*     */           
/*     */           break;
/*     */         } 
/* 740 */         if (par4) {
/*     */           
/* 742 */           var6--;
/*     */           
/*     */           continue;
/*     */         } 
/* 746 */         var6++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 751 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_94529_b(int par0) {
/* 756 */     return par0 >> 2 & 0x3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_94532_c(int par0) {
/* 761 */     return par0 & 0x3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_94534_d(int par0, int par1) {
/* 766 */     return par0 & 0x3 | (par1 & 0x3) << 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_94528_d(int par0) {
/* 771 */     return (par0 == 0 || par0 == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_94533_d() {
/* 776 */     this.field_94536_g = 0;
/* 777 */     this.field_94537_h.clear();
/*     */   }
/*     */   
/*     */   public static boolean func_94527_a(Slot par0Slot, ItemStack par1ItemStack, boolean par2) {
/*     */     int i;
/* 782 */     boolean var3 = (par0Slot == null || !par0Slot.getHasStack());
/*     */ 
/*     */     
/* 785 */     if (par0Slot != null && par0Slot.getHasStack() && par1ItemStack != null && par1ItemStack.isItemStackEqual(par0Slot.getStack(), true, false, false, false)) {
/*     */       
/* 787 */       int var10002 = par2 ? 0 : par1ItemStack.stackSize;
/* 788 */       i = var3 | (((par0Slot.getStack()).stackSize + var10002 <= par1ItemStack.getMaxStackSize()) ? 1 : 0);
/*     */     } 
/*     */     
/* 791 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_94525_a(Set par0Set, int par1, ItemStack par2ItemStack, int par3) {
/* 796 */     switch (par1) {
/*     */       
/*     */       case 0:
/* 799 */         par2ItemStack.stackSize = MathHelper.floor_float(par2ItemStack.stackSize / par0Set.size());
/*     */         break;
/*     */       
/*     */       case 1:
/* 803 */         par2ItemStack.stackSize = 1;
/*     */         break;
/*     */     } 
/* 806 */     par2ItemStack.stackSize += par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDragIntoSlot(Slot par1Slot) {
/* 815 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int calcRedstoneFromInventory(IInventory par0IInventory) {
/* 820 */     if (par0IInventory == null)
/*     */     {
/* 822 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 826 */     int var1 = 0;
/* 827 */     float var2 = 0.0F;
/*     */     
/* 829 */     for (int var3 = 0; var3 < par0IInventory.getSizeInventory(); var3++) {
/*     */       
/* 831 */       ItemStack var4 = par0IInventory.getStackInSlot(var3);
/*     */       
/* 833 */       if (var4 != null) {
/*     */         
/* 835 */         var2 += var4.stackSize / Math.min(par0IInventory.getInventoryStackLimit(), var4.getMaxStackSize());
/* 836 */         var1++;
/*     */       } 
/*     */     } 
/*     */     
/* 840 */     var2 /= par0IInventory.getSizeInventory();
/* 841 */     return MathHelper.floor_float(var2 * 14.0F) + ((var1 > 0) ? 1 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordSlotStackSizes() {
/* 848 */     if (this.stack_sizes_before == null) {
/* 849 */       this.stack_sizes_before = new int[this.inventorySlots.size()];
/*     */     }
/* 851 */     for (int i = 0; i < this.stack_sizes_before.length; i++) {
/*     */       
/* 853 */       ItemStack item_stack = ((Slot)this.inventorySlots.get(i)).getStack();
/* 854 */       this.stack_sizes_before[i] = (item_stack == null) ? 0 : item_stack.stackSize;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void lockSlotsThatChanged() {
/* 861 */     for (int i = 0; i < this.stack_sizes_before.length; i++) {
/*     */       
/* 863 */       Slot slot = this.inventorySlots.get(i);
/* 864 */       ItemStack item_stack = slot.getStack();
/*     */       
/* 866 */       int stack_size = (item_stack == null) ? 0 : item_stack.stackSize;
/*     */       
/* 868 */       if (stack_size != this.stack_sizes_before[i])
/*     */       {
/* 870 */         slot.setLocked(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 878 */     if (this.slot_was_locked_this_tick) {
/*     */       
/* 880 */       this.player.sendPacket(new Packet85SimpleSignal(EnumSignal.slot_locked));
/* 881 */       this.slot_was_locked_this_tick = false;
/*     */     } 
/*     */     
/* 884 */     if (this.unlock_next_tick) {
/* 885 */       unlockAllSlots();
/*     */     }
/*     */   }
/*     */   
/*     */   public void unlockAllSlots() {
/* 890 */     for (int i = 0; i < this.inventorySlots.size(); i++) {
/* 891 */       ((Slot)this.inventorySlots.get(i)).setLocked(false);
/*     */     }
/* 893 */     this.unlock_next_tick = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void unlockNextTick() {
/* 898 */     this.unlock_next_tick = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private final boolean handleMiddleMouseClick(EntityPlayer player, int gui_slot_index, boolean shift_is_down) {
/* 903 */     Slot slot = getSlot(gui_slot_index);
/*     */ 
/*     */     
/* 906 */     if (!slot.canTakeStack(player) || !slot.getHasStack())
/*     */     {
/*     */       
/* 909 */       return false;
/*     */     }
/*     */     
/* 912 */     Class<?> slot_class = slot.getClass();
/*     */     
/* 914 */     boolean must_drop_entire_stack = (slot_class == SlotEnchantment.class || slot_class == ContainerRepairINNER2.class);
/*     */     
/* 916 */     ItemStack item_stack = slot.decrStackSize((shift_is_down || must_drop_entire_stack) ? (slot.getStack()).stackSize : 1);
/*     */     
/* 918 */     if (!player.worldObj.isRemote) {
/* 919 */       player.dropPlayerItemWithRandomChoice(item_stack, false);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 926 */     if (!slot.getHasStack() || slot_class == SlotFurnace.class) {
/* 927 */       slot.onPickupFromSlot(player, item_stack);
/*     */     }
/* 929 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Container.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */