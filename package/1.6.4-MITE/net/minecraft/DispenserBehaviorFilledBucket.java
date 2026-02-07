/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class DispenserBehaviorFilledBucket
/*    */   extends BehaviorDefaultDispenseItem
/*    */ {
/*    */   public ItemBucket item_bucket;
/*    */   private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior;
/*    */   
/*    */   public DispenserBehaviorFilledBucket(ItemBucket item_bucket) {
/* 14 */     this.defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();
/*    */     this.item_bucket = item_bucket;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/* 21 */     ItemBucket var3 = (ItemBucket)par2ItemStack.getItem();
/* 22 */     int var4 = par1IBlockSource.getXInt();
/* 23 */     int var5 = par1IBlockSource.getYInt();
/* 24 */     int var6 = par1IBlockSource.getZInt();
/* 25 */     EnumFacing var7 = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
/*    */ 
/*    */     
/* 28 */     if (var3.tryPlaceContainedLiquid(par1IBlockSource.getWorld(), (EntityPlayer)null, var4 + var7.getFrontOffsetX(), var5 + var7.getFrontOffsetY(), var6 + var7.getFrontOffsetZ(), false)) {
/*    */       
/* 30 */       this.suppress_dispense_particles = true;
/*    */ 
/*    */       
/* 33 */       par2ItemStack.itemID = (this.item_bucket.getEmptyVessel()).itemID;
/* 34 */       par2ItemStack.stackSize = 1;
/* 35 */       return par2ItemStack;
/*    */     } 
/*    */ 
/*    */     
/* 39 */     return this.defaultDispenserItemBehavior.dispense(par1IBlockSource, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorFilledBucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */