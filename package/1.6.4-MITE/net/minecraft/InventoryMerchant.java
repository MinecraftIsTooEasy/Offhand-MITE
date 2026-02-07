/*     */ package net.minecraft;
/*     */ 
/*     */ public class InventoryMerchant
/*     */   implements IInventory {
/*     */   private final IMerchant theMerchant;
/*   6 */   private ItemStack[] theInventory = new ItemStack[3];
/*     */   
/*     */   private final EntityPlayer thePlayer;
/*     */   private MerchantRecipe currentRecipe;
/*     */   private int currentRecipeIndex;
/*     */   
/*     */   public InventoryMerchant(EntityPlayer par1EntityPlayer, IMerchant par2IMerchant) {
/*  13 */     this.thePlayer = par1EntityPlayer;
/*  14 */     this.theMerchant = par2IMerchant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  22 */     return this.theInventory.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  30 */     return this.theInventory[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  39 */     if (this.theInventory[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/*  43 */       if (par1 == 2) {
/*     */         
/*  45 */         ItemStack itemStack = this.theInventory[par1];
/*  46 */         this.theInventory[par1] = null;
/*  47 */         return itemStack;
/*     */       } 
/*  49 */       if ((this.theInventory[par1]).stackSize <= par2) {
/*     */         
/*  51 */         ItemStack itemStack = this.theInventory[par1];
/*  52 */         this.theInventory[par1] = null;
/*     */         
/*  54 */         if (inventoryResetNeededOnSlotChange(par1))
/*     */         {
/*  56 */           resetRecipeAndSlots();
/*     */         }
/*     */         
/*  59 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  63 */       ItemStack var3 = this.theInventory[par1].splitStack(par2);
/*     */       
/*  65 */       if ((this.theInventory[par1]).stackSize == 0)
/*     */       {
/*  67 */         this.theInventory[par1] = null;
/*     */       }
/*     */       
/*  70 */       if (inventoryResetNeededOnSlotChange(par1))
/*     */       {
/*  72 */         resetRecipeAndSlots();
/*     */       }
/*     */       
/*  75 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean inventoryResetNeededOnSlotChange(int par1) {
/*  89 */     return (par1 == 0 || par1 == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/*  98 */     if (this.theInventory[par1] != null) {
/*     */       
/* 100 */       ItemStack var2 = this.theInventory[par1];
/* 101 */       this.theInventory[par1] = null;
/* 102 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 115 */     this.theInventory[par1] = par2ItemStack;
/*     */     
/* 117 */     if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
/*     */     {
/* 119 */       par2ItemStack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */     
/* 122 */     if (inventoryResetNeededOnSlotChange(par1))
/*     */     {
/* 124 */       resetRecipeAndSlots();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCustomNameOrUnlocalized() {
/* 133 */     return "mob.villager";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 151 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 159 */     return (this.theMerchant.getCustomer() == par1EntityPlayer);
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
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInventoryChanged() {
/* 179 */     resetRecipeAndSlots();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetRecipeAndSlots() {
/* 184 */     this.currentRecipe = null;
/* 185 */     ItemStack var1 = this.theInventory[0];
/* 186 */     ItemStack var2 = this.theInventory[1];
/*     */     
/* 188 */     if (var1 == null) {
/*     */       
/* 190 */       var1 = var2;
/* 191 */       var2 = null;
/*     */     } 
/*     */     
/* 194 */     if (var1 == null) {
/*     */       
/* 196 */       setInventorySlotContents(2, (ItemStack)null);
/*     */     }
/*     */     else {
/*     */       
/* 200 */       MerchantRecipeList var3 = this.theMerchant.getRecipes(this.thePlayer);
/*     */       
/* 202 */       if (var3 != null) {
/*     */         
/* 204 */         MerchantRecipe var4 = var3.canRecipeBeUsed(var1, var2, this.currentRecipeIndex);
/*     */         
/* 206 */         if (var4 != null && !var4.func_82784_g()) {
/*     */           
/* 208 */           this.currentRecipe = var4;
/* 209 */           setInventorySlotContents(2, var4.getItemToSell().copy());
/*     */         }
/* 211 */         else if (var2 != null) {
/*     */           
/* 213 */           var4 = var3.canRecipeBeUsed(var2, var1, this.currentRecipeIndex);
/*     */           
/* 215 */           if (var4 != null && !var4.func_82784_g())
/*     */           {
/* 217 */             this.currentRecipe = var4;
/* 218 */             setInventorySlotContents(2, var4.getItemToSell().copy());
/*     */           }
/*     */           else
/*     */           {
/* 222 */             setInventorySlotContents(2, (ItemStack)null);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 227 */           setInventorySlotContents(2, (ItemStack)null);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 232 */     this.theMerchant.func_110297_a_(getStackInSlot(2));
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe getCurrentRecipe() {
/* 237 */     return this.currentRecipe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentRecipeIndex(int par1) {
/* 242 */     this.currentRecipeIndex = par1;
/* 243 */     resetRecipeAndSlots();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 249 */     ItemStack[] item_stacks = this.theInventory;
/*     */     
/* 251 */     for (int i = 0; i < item_stacks.length; i++)
/* 252 */       item_stacks[i] = null; 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\InventoryMerchant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */