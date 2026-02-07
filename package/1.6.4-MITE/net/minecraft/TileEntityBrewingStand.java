/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class TileEntityBrewingStand
/*     */   extends TileEntity implements ISidedInventory {
/*   7 */   private static final int[] field_102017_a = new int[] { 3 };
/*   8 */   private static final int[] field_102016_b = new int[] { 0, 1, 2 };
/*     */ 
/*     */   
/*  11 */   private ItemStack[] brewingItemStacks = new ItemStack[4];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int brewTime;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int filledSlots;
/*     */ 
/*     */ 
/*     */   
/*     */   private int ingredientID;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedInvName() {
/*  31 */     return "container.brewing";
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
/*     */   public int getSizeInventory() {
/*  53 */     return this.brewingItemStacks.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEntity() {
/*  62 */     if (this.brewTime > 0) {
/*     */       
/*  64 */       this.brewTime--;
/*     */       
/*  66 */       if (this.brewTime == 0)
/*     */       {
/*  68 */         brewPotions();
/*  69 */         onInventoryChanged();
/*     */       }
/*  71 */       else if (!canBrew())
/*     */       {
/*  73 */         this.brewTime = 0;
/*  74 */         onInventoryChanged();
/*     */       }
/*  76 */       else if (this.ingredientID != (this.brewingItemStacks[3]).itemID)
/*     */       {
/*  78 */         this.brewTime = 0;
/*  79 */         onInventoryChanged();
/*     */       }
/*     */     
/*  82 */     } else if (canBrew()) {
/*     */       
/*  84 */       this.brewTime = 400;
/*  85 */       this.ingredientID = (this.brewingItemStacks[3]).itemID;
/*     */     } 
/*     */     
/*  88 */     int var1 = getFilledSlots();
/*     */     
/*  90 */     if (var1 != this.filledSlots) {
/*     */       
/*  92 */       this.filledSlots = var1;
/*  93 */       this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, var1, 2);
/*     */     } 
/*     */     
/*  96 */     super.updateEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrewTime() {
/* 101 */     return this.brewTime;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canBrew() {
/* 106 */     if (this.brewingItemStacks[3] != null && (this.brewingItemStacks[3]).stackSize > 0) {
/*     */       
/* 108 */       ItemStack var1 = this.brewingItemStacks[3];
/*     */       
/* 110 */       if (!Item.itemsList[var1.itemID].isPotionIngredient())
/*     */       {
/* 112 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 116 */       boolean var2 = false;
/*     */       
/* 118 */       for (int var3 = 0; var3 < 3; var3++) {
/*     */         
/* 120 */         if (this.brewingItemStacks[var3] != null && (this.brewingItemStacks[var3]).itemID == Item.potion.itemID) {
/*     */           
/* 122 */           int var4 = this.brewingItemStacks[var3].getItemSubtype();
/* 123 */           int var5 = getPotionResult(var4, var1);
/*     */           
/* 125 */           if (!ItemPotion.isSplash(var4) && ItemPotion.isSplash(var5)) {
/*     */             
/* 127 */             var2 = true;
/*     */             
/*     */             break;
/*     */           } 
/* 131 */           List var6 = Item.potion.getEffects(var4);
/* 132 */           List var7 = Item.potion.getEffects(var5);
/*     */           
/* 134 */           if ((var4 <= 0 || var6 != var7) && (var6 == null || (!var6.equals(var7) && var7 != null)) && var4 != var5) {
/*     */             
/* 136 */             var2 = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 142 */       return var2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void brewPotions() {
/* 153 */     if (canBrew()) {
/*     */       
/* 155 */       ItemStack var1 = this.brewingItemStacks[3];
/*     */       
/* 157 */       for (int var2 = 0; var2 < 3; var2++) {
/*     */         
/* 159 */         if (this.brewingItemStacks[var2] != null && (this.brewingItemStacks[var2]).itemID == Item.potion.itemID) {
/*     */           
/* 161 */           int var3 = this.brewingItemStacks[var2].getItemSubtype();
/* 162 */           int var4 = getPotionResult(var3, var1);
/* 163 */           List var5 = Item.potion.getEffects(var3);
/* 164 */           List var6 = Item.potion.getEffects(var4);
/*     */           
/* 166 */           if ((var3 <= 0 || var5 != var6) && (var5 == null || (!var5.equals(var6) && var6 != null))) {
/*     */             
/* 168 */             if (var3 != var4)
/*     */             {
/* 170 */               this.brewingItemStacks[var2].setItemSubtype(var4);
/*     */             }
/*     */           }
/* 173 */           else if (!ItemPotion.isSplash(var3) && ItemPotion.isSplash(var4)) {
/*     */             
/* 175 */             this.brewingItemStacks[var2].setItemSubtype(var4);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 180 */       if (Item.itemsList[var1.itemID].hasContainerItem()) {
/*     */         
/* 182 */         this.brewingItemStacks[3] = new ItemStack(Item.itemsList[var1.itemID].getContainerItem());
/*     */       }
/*     */       else {
/*     */         
/* 186 */         (this.brewingItemStacks[3]).stackSize--;
/*     */         
/* 188 */         if ((this.brewingItemStacks[3]).stackSize <= 0)
/*     */         {
/* 190 */           this.brewingItemStacks[3] = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getPotionResult(int par1, ItemStack par2ItemStack) {
/* 201 */     return (par2ItemStack == null) ? par1 : (Item.itemsList[par2ItemStack.itemID].isPotionIngredient() ? PotionHelper.applyIngredient(par1, Item.itemsList[par2ItemStack.itemID].getPotionEffect()) : par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 209 */     super.readFromNBT(par1NBTTagCompound);
/* 210 */     NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
/* 211 */     this.brewingItemStacks = new ItemStack[getSizeInventory()];
/*     */     
/* 213 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 215 */       NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/* 216 */       byte var5 = var4.getByte("Slot");
/*     */       
/* 218 */       if (var5 >= 0 && var5 < this.brewingItemStacks.length)
/*     */       {
/* 220 */         this.brewingItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */     
/* 224 */     this.brewTime = par1NBTTagCompound.getShort("BrewTime");
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
/* 237 */     super.writeToNBT(par1NBTTagCompound);
/* 238 */     par1NBTTagCompound.setShort("BrewTime", (short)this.brewTime);
/* 239 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 241 */     for (int var3 = 0; var3 < this.brewingItemStacks.length; var3++) {
/*     */       
/* 243 */       if (this.brewingItemStacks[var3] != null) {
/*     */         
/* 245 */         NBTTagCompound var4 = new NBTTagCompound();
/* 246 */         var4.setByte("Slot", (byte)var3);
/* 247 */         this.brewingItemStacks[var3].writeToNBT(var4);
/* 248 */         var2.appendTag(var4);
/*     */       } 
/*     */     } 
/*     */     
/* 252 */     par1NBTTagCompound.setTag("Items", var2);
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
/*     */   public ItemStack getStackInSlot(int par1) {
/* 265 */     return (par1 >= 0 && par1 < this.brewingItemStacks.length) ? this.brewingItemStacks[par1] : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/* 274 */     if (par1 >= 0 && par1 < this.brewingItemStacks.length) {
/*     */       
/* 276 */       ItemStack var3 = this.brewingItemStacks[par1];
/* 277 */       this.brewingItemStacks[par1] = null;
/* 278 */       return var3;
/*     */     } 
/*     */ 
/*     */     
/* 282 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/* 292 */     if (par1 >= 0 && par1 < this.brewingItemStacks.length) {
/*     */       
/* 294 */       ItemStack var2 = this.brewingItemStacks[par1];
/* 295 */       this.brewingItemStacks[par1] = null;
/* 296 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 300 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 309 */     if (par1 >= 0 && par1 < this.brewingItemStacks.length)
/*     */     {
/* 311 */       this.brewingItemStacks[par1] = par2ItemStack;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 321 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 329 */     return (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) ? false : ((par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D));
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
/* 341 */     return (par1 == 3) ? Item.itemsList[par2ItemStack.itemID].isPotionIngredient() : ((par2ItemStack.itemID == Item.potion.itemID || par2ItemStack.itemID == Item.glassBottle.itemID));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBrewTime(int par1) {
/* 346 */     this.brewTime = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilledSlots() {
/* 354 */     int var1 = 0;
/*     */     
/* 356 */     for (int var2 = 0; var2 < 3; var2++) {
/*     */       
/* 358 */       if (this.brewingItemStacks[var2] != null)
/*     */       {
/* 360 */         var1 |= 1 << var2;
/*     */       }
/*     */     } 
/*     */     
/* 364 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getAccessibleSlotsFromSide(int par1) {
/* 373 */     return (par1 == 1) ? field_102017_a : field_102016_b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
/* 382 */     return isItemValidForSlot(par1, par2ItemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
/* 391 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 397 */     ItemStack[] item_stacks = this.brewingItemStacks;
/*     */     
/* 399 */     for (int i = 0; i < item_stacks.length; i++)
/* 400 */       item_stacks[i] = null; 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityBrewingStand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */