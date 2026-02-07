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
/*     */ public class MerchantRecipe
/*     */ {
/*     */   private ItemStack itemToBuy;
/*     */   private ItemStack secondItemToBuy;
/*     */   private ItemStack itemToSell;
/*     */   private int toolUses;
/*     */   private int maxTradeUses;
/*     */   
/*     */   public MerchantRecipe(NBTTagCompound par1NBTTagCompound) {
/*  24 */     readFromTags(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe(ItemStack par1ItemStack, ItemStack par2ItemStack, ItemStack par3ItemStack) {
/*  29 */     this.itemToBuy = par1ItemStack;
/*  30 */     this.secondItemToBuy = par2ItemStack;
/*  31 */     this.itemToSell = par3ItemStack;
/*  32 */     this.maxTradeUses = 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe(ItemStack par1ItemStack, ItemStack par2ItemStack) {
/*  37 */     this(par1ItemStack, (ItemStack)null, par2ItemStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe(ItemStack par1ItemStack, Item par2Item) {
/*  42 */     this(par1ItemStack, new ItemStack(par2Item));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItemToBuy() {
/*  50 */     return this.itemToBuy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getSecondItemToBuy() {
/*  58 */     return this.secondItemToBuy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSecondItemToBuy() {
/*  66 */     return (this.secondItemToBuy != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItemToSell() {
/*  74 */     return this.itemToSell;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSameIDsAs(MerchantRecipe par1MerchantRecipe) {
/*  82 */     return (this.itemToBuy.itemID == par1MerchantRecipe.itemToBuy.itemID && this.itemToSell.itemID == par1MerchantRecipe.itemToSell.itemID) ? (((this.secondItemToBuy == null && par1MerchantRecipe.secondItemToBuy == null) || (this.secondItemToBuy != null && par1MerchantRecipe.secondItemToBuy != null && this.secondItemToBuy.itemID == par1MerchantRecipe.secondItemToBuy.itemID))) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSameItemsAs(MerchantRecipe par1MerchantRecipe) {
/*  90 */     return (hasSameIDsAs(par1MerchantRecipe) && (this.itemToBuy.stackSize < par1MerchantRecipe.itemToBuy.stackSize || (this.secondItemToBuy != null && this.secondItemToBuy.stackSize < par1MerchantRecipe.secondItemToBuy.stackSize)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void incrementToolUses() {
/*  95 */     this.toolUses++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82783_a(int par1) {
/* 100 */     this.maxTradeUses += par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82784_g() {
/* 105 */     return (this.toolUses >= this.maxTradeUses);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82785_h() {
/* 110 */     this.toolUses = this.maxTradeUses;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromTags(NBTTagCompound par1NBTTagCompound) {
/* 115 */     NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("buy");
/* 116 */     this.itemToBuy = ItemStack.loadItemStackFromNBT(var2);
/* 117 */     NBTTagCompound var3 = par1NBTTagCompound.getCompoundTag("sell");
/* 118 */     this.itemToSell = ItemStack.loadItemStackFromNBT(var3);
/*     */     
/* 120 */     if (par1NBTTagCompound.hasKey("buyB"))
/*     */     {
/* 122 */       this.secondItemToBuy = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("buyB"));
/*     */     }
/*     */     
/* 125 */     if (par1NBTTagCompound.hasKey("uses"))
/*     */     {
/* 127 */       this.toolUses = par1NBTTagCompound.getInteger("uses");
/*     */     }
/*     */     
/* 130 */     if (par1NBTTagCompound.hasKey("maxUses")) {
/*     */       
/* 132 */       this.maxTradeUses = par1NBTTagCompound.getInteger("maxUses");
/*     */     }
/*     */     else {
/*     */       
/* 136 */       this.maxTradeUses = 7;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeToTags() {
/* 142 */     NBTTagCompound var1 = new NBTTagCompound();
/* 143 */     var1.setCompoundTag("buy", this.itemToBuy.writeToNBT(new NBTTagCompound("buy")));
/* 144 */     var1.setCompoundTag("sell", this.itemToSell.writeToNBT(new NBTTagCompound("sell")));
/*     */     
/* 146 */     if (this.secondItemToBuy != null)
/*     */     {
/* 148 */       var1.setCompoundTag("buyB", this.secondItemToBuy.writeToNBT(new NBTTagCompound("buyB")));
/*     */     }
/*     */     
/* 151 */     var1.setInteger("uses", this.toolUses);
/* 152 */     var1.setInteger("maxUses", this.maxTradeUses);
/* 153 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemToSell(ItemStack item_stack) {
/* 158 */     this.itemToSell = item_stack;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MerchantRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */