/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class DispenserBehaviorEmptyBucket
/*    */   extends BehaviorDefaultDispenseItem
/*    */ {
/*    */   public ItemBucket item_bucket;
/*    */   private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior;
/*    */   
/*    */   public DispenserBehaviorEmptyBucket(ItemBucket item_bucket) {
/* 14 */     this.defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();
/*    */     this.item_bucket = item_bucket;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/*    */     Item var10;
/* 21 */     EnumFacing var3 = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
/* 22 */     World var4 = par1IBlockSource.getWorld();
/* 23 */     int var5 = par1IBlockSource.getXInt() + var3.getFrontOffsetX();
/* 24 */     int var6 = par1IBlockSource.getYInt() + var3.getFrontOffsetY();
/* 25 */     int var7 = par1IBlockSource.getZInt() + var3.getFrontOffsetZ();
/* 26 */     Material var8 = var4.getBlockMaterial(var5, var6, var7);
/* 27 */     int var9 = var4.getBlockMetadata(var5, var6, var7);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 44 */     if (var8 == Material.water) {
/* 45 */       var10 = this.item_bucket.getPeerForContents(Material.water);
/* 46 */     } else if (var8 == Material.lava) {
/* 47 */       var10 = this.item_bucket.getPeerForContents(Material.lava);
/*    */     } else {
/* 49 */       return super.dispenseStack(par1IBlockSource, par2ItemStack);
/*    */     } 
/* 51 */     if (var8 == Material.lava) {
/*    */       
/* 53 */       World world = par1IBlockSource.getWorld();
/*    */       
/* 55 */       if (world.rand.nextFloat() < this.item_bucket.getChanceOfMeltingWhenFilledWithLava()) {
/*    */         
/* 57 */         world.blockFX(EnumBlockFX.item_consumed_by_lava, var5, var6, var7);
/*    */         
/* 59 */         par2ItemStack.stackSize--;
/*    */         
/* 61 */         this.suppress_dispense_particles = true;
/*    */         
/* 63 */         return par2ItemStack;
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     var4.setBlockToAir(var5, var6, var7);
/*    */     
/* 69 */     if (--par2ItemStack.stackSize == 0) {
/*    */       
/* 71 */       par2ItemStack.itemID = var10.itemID;
/* 72 */       par2ItemStack.stackSize = 1;
/*    */     }
/* 74 */     else if (((TileEntityDispenser)par1IBlockSource.getBlockTileEntity()).addItem(new ItemStack(var10)) < 0) {
/*    */       
/* 76 */       this.defaultDispenserItemBehavior.dispense(par1IBlockSource, new ItemStack(var10));
/*    */     } 
/*    */     
/* 79 */     this.suppress_dispense_particles = true;
/*    */     
/* 81 */     return par2ItemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorEmptyBucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */