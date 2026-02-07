/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ContainerDispenser
/*    */   extends Container
/*    */ {
/*    */   private TileEntityDispenser tileEntityDispenser;
/*    */   
/*    */   public ContainerDispenser(EntityPlayer player, TileEntityDispenser par2TileEntityDispenser) {
/* 10 */     super(player);
/*    */     
/* 12 */     this.tileEntityDispenser = par2TileEntityDispenser;
/*    */     
/*    */     int var3;
/*    */     
/* 16 */     for (var3 = 0; var3 < 3; var3++) {
/*    */       
/* 18 */       for (int var4 = 0; var4 < 3; var4++)
/*    */       {
/* 20 */         addSlotToContainer(new Slot(par2TileEntityDispenser, var4 + var3 * 3, 62 + var4 * 18, 17 + var3 * 18));
/*    */       }
/*    */     } 
/*    */     
/* 24 */     for (var3 = 0; var3 < 3; var3++) {
/*    */       
/* 26 */       for (int var4 = 0; var4 < 9; var4++)
/*    */       {
/*    */         
/* 29 */         addSlotToContainer(new Slot(player.inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
/*    */       }
/*    */     } 
/*    */     
/* 33 */     for (var3 = 0; var3 < 9; var3++)
/*    */     {
/*    */       
/* 36 */       addSlotToContainer(new Slot(player.inventory, var3, 8 + var3 * 18, 142));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/* 42 */     return this.tileEntityDispenser.isUseableByPlayer(par1EntityPlayer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/* 50 */     ItemStack var3 = null;
/* 51 */     Slot var4 = this.inventorySlots.get(par2);
/*    */     
/* 53 */     if (var4 != null && var4.getHasStack()) {
/*    */       
/* 55 */       ItemStack var5 = var4.getStack();
/* 56 */       var3 = var5.copy();
/*    */       
/* 58 */       if (par2 < 9) {
/*    */         
/* 60 */         if (!mergeItemStack(var5, 9, 45, true))
/*    */         {
/* 62 */           return null;
/*    */         }
/*    */       }
/* 65 */       else if (!mergeItemStack(var5, 0, 9, false)) {
/*    */         
/* 67 */         return null;
/*    */       } 
/*    */       
/* 70 */       if (var5.stackSize == 0) {
/*    */         
/* 72 */         var4.putStack((ItemStack)null);
/*    */       }
/*    */       else {
/*    */         
/* 76 */         var4.onSlotChanged();
/*    */       } 
/*    */       
/* 79 */       if (var5.stackSize == var3.stackSize)
/*    */       {
/* 81 */         return null;
/*    */       }
/*    */       
/* 84 */       var4.onPickupFromSlot(par1EntityPlayer, var5);
/*    */     } 
/*    */     
/* 87 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerDispenser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */