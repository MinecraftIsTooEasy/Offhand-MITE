/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlotMerchantResult
/*     */   extends Slot
/*     */ {
/*     */   private final InventoryMerchant theMerchantInventory;
/*     */   private EntityPlayer thePlayer;
/*     */   private int field_75231_g;
/*     */   private final IMerchant theMerchant;
/*     */   
/*     */   public SlotMerchantResult(EntityPlayer par1EntityPlayer, IMerchant par2IMerchant, InventoryMerchant par3InventoryMerchant, int par4, int par5, int par6) {
/*  19 */     super(par3InventoryMerchant, par4, par5, par6);
/*  20 */     this.thePlayer = par1EntityPlayer;
/*  21 */     this.theMerchant = par2IMerchant;
/*  22 */     this.theMerchantInventory = par3InventoryMerchant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValid(ItemStack par1ItemStack) {
/*  30 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1) {
/*  39 */     if (getHasStack())
/*     */     {
/*  41 */       this.field_75231_g += Math.min(par1, (getStack()).stackSize);
/*     */     }
/*     */     
/*  44 */     return super.decrStackSize(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack par1ItemStack, int par2) {
/*  53 */     this.field_75231_g += par2;
/*  54 */     onCrafting(par1ItemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack par1ItemStack) {
/*  62 */     par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75231_g);
/*  63 */     this.field_75231_g = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
/*  68 */     onCrafting(par2ItemStack);
/*  69 */     MerchantRecipe var3 = this.theMerchantInventory.getCurrentRecipe();
/*     */     
/*  71 */     if (var3 != null) {
/*     */       
/*  73 */       ItemStack var4 = this.theMerchantInventory.getStackInSlot(0);
/*  74 */       ItemStack var5 = this.theMerchantInventory.getStackInSlot(1);
/*     */       
/*  76 */       if (func_75230_a(var3, var4, var5) || func_75230_a(var3, var5, var4)) {
/*     */         
/*  78 */         this.theMerchant.useRecipe(var3);
/*     */         
/*  80 */         if (var4 != null && var4.stackSize <= 0)
/*     */         {
/*  82 */           var4 = null;
/*     */         }
/*     */         
/*  85 */         if (var5 != null && var5.stackSize <= 0)
/*     */         {
/*  87 */           var5 = null;
/*     */         }
/*     */         
/*  90 */         this.theMerchantInventory.setInventorySlotContents(0, var4);
/*  91 */         this.theMerchantInventory.setInventorySlotContents(1, var5);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_75230_a(MerchantRecipe par1MerchantRecipe, ItemStack par2ItemStack, ItemStack par3ItemStack) {
/*  98 */     ItemStack var4 = par1MerchantRecipe.getItemToBuy();
/*  99 */     ItemStack var5 = par1MerchantRecipe.getSecondItemToBuy();
/*     */     
/* 101 */     if (par2ItemStack != null && par2ItemStack.itemID == var4.itemID) {
/*     */       
/* 103 */       if (var5 != null && par3ItemStack != null && var5.itemID == par3ItemStack.itemID) {
/*     */         
/* 105 */         par2ItemStack.stackSize -= var4.stackSize;
/* 106 */         par3ItemStack.stackSize -= var5.stackSize;
/* 107 */         return true;
/*     */       } 
/*     */       
/* 110 */       if (var5 == null && par3ItemStack == null) {
/*     */         
/* 112 */         par2ItemStack.stackSize -= var4.stackSize;
/* 113 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotMerchantResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */