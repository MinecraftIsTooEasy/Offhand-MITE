/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ class ContainerCreative
/*     */   extends Container
/*     */ {
/*   9 */   public List itemList = new ArrayList();
/*     */ 
/*     */   
/*     */   public ContainerCreative(EntityPlayer par1EntityPlayer) {
/*  13 */     super(par1EntityPlayer);
/*     */     
/*  15 */     InventoryPlayer var2 = par1EntityPlayer.inventory;
/*     */     
/*     */     int var3;
/*  18 */     for (var3 = 0; var3 < 5; var3++) {
/*     */       
/*  20 */       for (int var4 = 0; var4 < 9; var4++)
/*     */       {
/*  22 */         addSlotToContainer(new Slot(GuiContainerCreative.getInventory(), var3 * 9 + var4, 9 + var4 * 18, 18 + var3 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  26 */     for (var3 = 0; var3 < 9; var3++)
/*     */     {
/*  28 */       addSlotToContainer(new Slot(var2, var3, 9 + var3 * 18, 112));
/*     */     }
/*     */     
/*  31 */     scrollTo(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/*  36 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scrollTo(float par1) {
/*  44 */     int var2 = this.itemList.size() / 9 - 5 + 1;
/*  45 */     int var3 = (int)((par1 * var2) + 0.5D);
/*     */     
/*  47 */     if (var3 < 0)
/*     */     {
/*  49 */       var3 = 0;
/*     */     }
/*     */     
/*  52 */     for (int var4 = 0; var4 < 5; var4++) {
/*     */       
/*  54 */       for (int var5 = 0; var5 < 9; var5++) {
/*     */         
/*  56 */         int var6 = var5 + (var4 + var3) * 9;
/*     */         
/*  58 */         if (var6 >= 0 && var6 < this.itemList.size()) {
/*     */           
/*  60 */           GuiContainerCreative.getInventory().setInventorySlotContents(var5 + var4 * 9, this.itemList.get(var6));
/*     */         }
/*     */         else {
/*     */           
/*  64 */           GuiContainerCreative.getInventory().setInventorySlotContents(var5 + var4 * 9, (ItemStack)null);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMoreThan1PageOfItemsInList() {
/*  75 */     return (this.itemList.size() > 45);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/*  85 */     if (par2 >= this.inventorySlots.size() - 9 && par2 < this.inventorySlots.size()) {
/*     */       
/*  87 */       Slot var3 = this.inventorySlots.get(par2);
/*     */       
/*  89 */       if (var3 != null && var3.getHasStack())
/*     */       {
/*  91 */         var3.putStack((ItemStack)null);
/*     */       }
/*     */     } 
/*     */     
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot) {
/* 100 */     return (par2Slot.yDisplayPosition > 90);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDragIntoSlot(Slot par1Slot) {
/* 109 */     return (par1Slot.inventory instanceof InventoryPlayer || (par1Slot.yDisplayPosition > 90 && par1Slot.xDisplayPosition <= 162));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerCreative.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */