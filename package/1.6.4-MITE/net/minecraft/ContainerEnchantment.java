/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ContainerEnchantment
/*     */   extends Container
/*     */ {
/*   9 */   public IInventory tableInventory = new SlotEnchantmentTable(this, "Enchant", true, 1);
/*     */   
/*     */   private int posX;
/*     */   
/*     */   private int posY;
/*     */   
/*     */   private int posZ;
/*  16 */   private Random rand = new Random();
/*     */ 
/*     */   
/*     */   public long nameSeed;
/*     */ 
/*     */   
/*  22 */   public int[] enchantLevels = new int[3];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContainerEnchantment(EntityPlayer player, World par2World, int par3, int par4, int par5) {
/*  29 */     super(player);
/*     */ 
/*     */ 
/*     */     
/*  33 */     this.posX = par3;
/*  34 */     this.posY = par4;
/*  35 */     this.posZ = par5;
/*     */     
/*  37 */     addSlotToContainer(new SlotEnchantment(this, this.tableInventory, 0, 25, 47));
/*     */     
/*     */     int var6;
/*  40 */     for (var6 = 0; var6 < 3; var6++) {
/*     */       
/*  42 */       for (int var7 = 0; var7 < 9; var7++)
/*     */       {
/*     */         
/*  45 */         addSlotToContainer(new Slot(player.inventory, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  49 */     for (var6 = 0; var6 < 9; var6++)
/*     */     {
/*     */       
/*  52 */       addSlotToContainer(new Slot(player.inventory, var6, 8 + var6 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCraftingToCrafters(ICrafting par1ICrafting) {
/*  58 */     super.addCraftingToCrafters(par1ICrafting);
/*  59 */     par1ICrafting.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
/*  60 */     par1ICrafting.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
/*  61 */     par1ICrafting.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/*  69 */     super.detectAndSendChanges();
/*     */     
/*  71 */     for (int var1 = 0; var1 < this.crafters.size(); var1++) {
/*     */       
/*  73 */       ICrafting var2 = this.crafters.get(var1);
/*  74 */       var2.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
/*  75 */       var2.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
/*  76 */       var2.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int par1, int par2) {
/*  82 */     if (par1 >= 0 && par1 <= 2) {
/*     */       
/*  84 */       this.enchantLevels[par1] = par2;
/*     */     }
/*     */     else {
/*     */       
/*  88 */       super.updateProgressBar(par1, par2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getNumAccessibleBookshelves() {
/*  96 */     int num_bookshelves = 0;
/*     */     
/*  98 */     for (int dy = 0; dy <= 1; dy++) {
/*     */       
/* 100 */       if (dy == 1 && !this.world.isAirOrPassableBlock(this.posX, this.posY + 1, this.posZ, false)) {
/*     */         break;
/*     */       }
/* 103 */       boolean[][] is_block_accessible = new boolean[5][5];
/*     */       
/* 105 */       if (this.world.isAirOrPassableBlock(this.posX, this.posY + dy, this.posZ - 1, false)) {
/*     */         
/* 107 */         is_block_accessible[2][0] = true;
/*     */         
/* 109 */         if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ - 1, false)) {
/*     */           
/* 111 */           is_block_accessible[0][1] = true;
/* 112 */           is_block_accessible[1][0] = true;
/*     */         } 
/*     */         
/* 115 */         if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ - 1, false)) {
/*     */           
/* 117 */           is_block_accessible[3][0] = true;
/* 118 */           is_block_accessible[4][1] = true;
/*     */         } 
/*     */       } 
/*     */       
/* 122 */       if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ, false)) {
/*     */         
/* 124 */         is_block_accessible[4][2] = true;
/*     */         
/* 126 */         if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ - 1, false)) {
/*     */           
/* 128 */           is_block_accessible[3][0] = true;
/* 129 */           is_block_accessible[4][1] = true;
/*     */         } 
/*     */         
/* 132 */         if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ + 1, false)) {
/*     */           
/* 134 */           is_block_accessible[4][3] = true;
/* 135 */           is_block_accessible[3][4] = true;
/*     */         } 
/*     */       } 
/*     */       
/* 139 */       if (this.world.isAirOrPassableBlock(this.posX, this.posY + dy, this.posZ + 1, false)) {
/*     */         
/* 141 */         is_block_accessible[2][4] = true;
/*     */         
/* 143 */         if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ + 1, false)) {
/*     */           
/* 145 */           is_block_accessible[4][3] = true;
/* 146 */           is_block_accessible[3][4] = true;
/*     */         } 
/*     */         
/* 149 */         if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ + 1, false)) {
/*     */           
/* 151 */           is_block_accessible[1][4] = true;
/* 152 */           is_block_accessible[0][3] = true;
/*     */         } 
/*     */       } 
/*     */       
/* 156 */       if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ, false)) {
/*     */         
/* 158 */         is_block_accessible[0][2] = true;
/*     */         
/* 160 */         if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ + 1, false)) {
/*     */           
/* 162 */           is_block_accessible[1][4] = true;
/* 163 */           is_block_accessible[0][3] = true;
/*     */         } 
/*     */         
/* 166 */         if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ - 1, false)) {
/*     */           
/* 168 */           is_block_accessible[0][1] = true;
/* 169 */           is_block_accessible[1][0] = true;
/*     */         } 
/*     */       } 
/*     */       
/* 173 */       for (int dx = -2; dx <= 2; dx++) {
/*     */         
/* 175 */         for (int dz = -2; dz <= 2; dz++) {
/*     */           
/* 177 */           if (is_block_accessible[2 + dx][2 + dz] && this.world.getBlock(this.posX + dx, this.posY + dy, this.posZ + dz) == Block.bookShelf) {
/* 178 */             num_bookshelves++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 183 */     return num_bookshelves;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory par1IInventory) {
/* 191 */     if (par1IInventory == this.tableInventory) {
/*     */       
/* 193 */       ItemStack var2 = par1IInventory.getStackInSlot(0);
/*     */ 
/*     */ 
/*     */       
/* 197 */       if (var2 != null && var2.isEnchantable()) {
/*     */         
/* 199 */         this.nameSeed = this.rand.nextLong();
/*     */ 
/*     */         
/* 202 */         if (!this.world.isRemote)
/*     */         {
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
/*     */ 
/*     */           
/* 258 */           int num_accessible_bookshelves = getNumAccessibleBookshelves();
/*     */           
/* 260 */           for (int slot_index = 0; slot_index < 3; slot_index++) {
/* 261 */             this.enchantLevels[slot_index] = calcEnchantmentLevelsForSlot(this.rand, slot_index, num_accessible_bookshelves, var2);
/*     */           }
/* 263 */           detectAndSendChanges();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 268 */         for (int var3 = 0; var3 < 3; var3++)
/*     */         {
/* 270 */           this.enchantLevels[var3] = 0;
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
/* 281 */     ItemStack var3 = this.tableInventory.getStackInSlot(0);
/*     */ 
/*     */     
/* 284 */     int experience_cost = Enchantment.getExperienceCost(this.enchantLevels[par2]);
/*     */ 
/*     */ 
/*     */     
/* 288 */     if (this.enchantLevels[par2] > 0 && var3 != null && (par1EntityPlayer.experience >= experience_cost || par1EntityPlayer.capabilities.isCreativeMode)) {
/*     */ 
/*     */       
/* 291 */       if (!this.world.isRemote) {
/*     */         
/* 293 */         if (ItemPotion.isBottleOfWater(var3)) {
/*     */           
/* 295 */           par1EntityPlayer.addExperience(-experience_cost);
/* 296 */           this.tableInventory.setInventorySlotContents(0, new ItemStack(Item.expBottle));
/*     */           
/* 298 */           return true;
/*     */         } 
/* 300 */         if (ItemAppleGold.isUnenchantedGoldenApple(var3)) {
/*     */           
/* 302 */           par1EntityPlayer.addExperience(-experience_cost);
/* 303 */           this.tableInventory.setInventorySlotContents(0, new ItemStack(Item.appleGold, 1, 1));
/*     */           
/* 305 */           return true;
/*     */         } 
/*     */         
/* 308 */         List<EnchantmentData> var4 = EnchantmentHelper.buildEnchantmentList(this.rand, var3, this.enchantLevels[par2]);
/* 309 */         boolean var5 = (var3.itemID == Item.book.itemID);
/*     */         
/* 311 */         if (var4 != null) {
/*     */ 
/*     */ 
/*     */           
/* 315 */           par1EntityPlayer.addExperience(-experience_cost);
/*     */           
/* 317 */           if (var5)
/*     */           {
/* 319 */             var3.itemID = Item.enchantedBook.itemID;
/*     */           }
/*     */           
/* 322 */           int var6 = var5 ? this.rand.nextInt(var4.size()) : -1;
/*     */           
/* 324 */           for (int var7 = 0; var7 < var4.size(); var7++) {
/*     */             
/* 326 */             EnchantmentData var8 = var4.get(var7);
/*     */             
/* 328 */             if (!var5 || var7 == var6)
/*     */             {
/* 330 */               if (var5) {
/*     */                 
/* 332 */                 Item.enchantedBook.addEnchantment(var3, var8);
/*     */               }
/*     */               else {
/*     */                 
/* 336 */                 var3.addEnchantment(var8.enchantmentobj, var8.enchantmentLevel);
/*     */               } 
/*     */             }
/*     */           } 
/*     */           
/* 341 */           getSlot(0).onSlotChanged();
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 346 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 350 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
/* 359 */     super.onContainerClosed(par1EntityPlayer);
/*     */ 
/*     */     
/* 362 */     if (!this.world.isRemote) {
/*     */       
/* 364 */       ItemStack var2 = this.tableInventory.getStackInSlotOnClosing(0);
/*     */       
/* 366 */       if (var2 != null)
/*     */       {
/* 368 */         par1EntityPlayer.dropPlayerItem(var2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/* 376 */     return !(Block.blocksList[this.world.getBlockId(this.posX, this.posY, this.posZ)] instanceof BlockEnchantmentTable) ? false : ((par1EntityPlayer.getDistanceSq(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/* 384 */     ItemStack var3 = null;
/* 385 */     Slot var4 = this.inventorySlots.get(par2);
/*     */     
/* 387 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/* 389 */       ItemStack var5 = var4.getStack();
/* 390 */       var3 = var5.copy();
/*     */       
/* 392 */       if (par2 == 0) {
/*     */         
/* 394 */         if (!mergeItemStack(var5, 1, 37, true))
/*     */         {
/* 396 */           return null;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 401 */         if (((Slot)this.inventorySlots.get(0)).getHasStack() || !((Slot)this.inventorySlots.get(0)).isItemValid(var5))
/*     */         {
/* 403 */           return null;
/*     */         }
/*     */         
/* 406 */         if (var5.hasTagCompound() && var5.stackSize == 1) {
/*     */           
/* 408 */           ((Slot)this.inventorySlots.get(0)).putStack(var5.copy());
/* 409 */           var5.stackSize = 0;
/*     */         }
/* 411 */         else if (var5.stackSize >= 1) {
/*     */ 
/*     */ 
/*     */           
/* 415 */           ItemStack item_stack = new ItemStack(var5.itemID, 1, var5.getItemSubtype());
/*     */           
/* 417 */           if (var5.isItemDamaged()) {
/* 418 */             item_stack.setItemDamage(var5.getItemDamage());
/*     */           }
/* 420 */           if (var5.getItem().hasQuality()) {
/* 421 */             item_stack.setQuality(var5.getQuality());
/*     */           }
/* 423 */           ((Slot)this.inventorySlots.get(0)).putStack(item_stack);
/* 424 */           var5.stackSize--;
/*     */         } 
/*     */       } 
/*     */       
/* 428 */       if (var5.stackSize == 0) {
/*     */         
/* 430 */         var4.putStack((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/* 434 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 437 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 439 */         return null;
/*     */       }
/*     */       
/* 442 */       var4.onPickupFromSlot(par1EntityPlayer, var5);
/*     */     } 
/*     */     
/* 445 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int calcEnchantmentLevelsForSlot(Random random, int slot_index, int num_accessible_bookshelves, ItemStack item_stack) {
/* 451 */     Item item = item_stack.getItem();
/*     */     
/* 453 */     if (ItemPotion.isBottleOfWater(item_stack) || ItemAppleGold.isUnenchantedGoldenApple(item_stack)) {
/* 454 */       return 2;
/*     */     }
/* 456 */     if (item.getItemEnchantability() <= 0) {
/* 457 */       return 0;
/*     */     }
/* 459 */     if (num_accessible_bookshelves > 24) {
/* 460 */       num_accessible_bookshelves = 24;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 465 */     Block enchantment_table_block = this.world.getBlock(this.posX, this.posY, this.posZ);
/*     */     
/* 467 */     int enchantment_table_power = (1 + num_accessible_bookshelves) * ((enchantment_table_block == Block.enchantmentTableEmerald) ? 2 : 4);
/*     */     
/* 469 */     int enchantment_levels = EnchantmentHelper.getEnchantmentLevelsAlteredByItemEnchantability(enchantment_table_power, item);
/*     */     
/* 471 */     float fraction = (1.0F + slot_index) / 3.0F;
/*     */     
/* 473 */     if (slot_index < 2) {
/* 474 */       fraction += (random.nextFloat() - 0.5F) * 0.2F;
/*     */     }
/* 476 */     return Math.max(Math.round(enchantment_levels * fraction), 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerEnchantment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */