/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class DispenserBehaviorFire
/*    */   extends BehaviorDefaultDispenseItem
/*    */ {
/*    */   private boolean field_96466_b = true;
/*    */   
/*    */   protected ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/* 12 */     EnumFacing var3 = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
/* 13 */     World var4 = par1IBlockSource.getWorld();
/*    */     
/* 15 */     int var5 = par1IBlockSource.getXInt() + var3.getFrontOffsetX();
/* 16 */     int var6 = par1IBlockSource.getYInt() + var3.getFrontOffsetY();
/* 17 */     int var7 = par1IBlockSource.getZInt() + var3.getFrontOffsetZ();
/*    */     
/* 19 */     if (var4.isAirBlock(var5, var6, var7)) {
/*    */       
/* 21 */       var4.setBlock(var5, var6, var7, Block.fire.blockID);
/*    */       
/* 23 */       ItemDamageResult result = par2ItemStack.tryDamageItem(var4, 1, false);
/*    */ 
/*    */       
/* 26 */       if (result != null && result.itemWasDestroyed())
/*    */       {
/* 28 */         par2ItemStack.stackSize = 0;
/*    */       }
/*    */     }
/* 31 */     else if (var4.getBlockId(var5, var6, var7) == Block.tnt.blockID) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 37 */       BlockTNT.primeTnt(var4, var5, var6, var7, 1, null);
/* 38 */       var4.setBlockToAir(var5, var6, var7);
/*    */     }
/*    */     else {
/*    */       
/* 42 */       this.field_96466_b = false;
/*    */     } 
/*    */     
/* 45 */     return par2ItemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void playDispenseSound(IBlockSource par1IBlockSource) {
/* 53 */     if (this.field_96466_b) {
/*    */       
/* 55 */       par1IBlockSource.getWorld().playAuxSFX(1000, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
/*    */     }
/*    */     else {
/*    */       
/* 59 */       par1IBlockSource.getWorld().playAuxSFX(1001, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorFire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */