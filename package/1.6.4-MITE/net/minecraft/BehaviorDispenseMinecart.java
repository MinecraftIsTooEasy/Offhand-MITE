/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class BehaviorDispenseMinecart
/*    */   extends BehaviorDefaultDispenseItem
/*    */ {
/* 11 */   private final BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();
/*    */   
/*    */   public ItemStack dispenseStack(IBlockSource iBlockSource, ItemStack itemStack) {
/*    */     double d4;
/* 15 */     EnumFacing enumFacing = BlockDispenser.getFacing(iBlockSource.getBlockMetadata());
/* 16 */     World world = iBlockSource.getWorld();
/*    */ 
/*    */ 
/*    */     
/* 20 */     double d1 = iBlockSource.getX() + (enumFacing.getFrontOffsetX() * 1.125F);
/* 21 */     double d2 = iBlockSource.getY() + (enumFacing.getFrontOffsetY() * 1.125F);
/* 22 */     double d3 = iBlockSource.getZ() + (enumFacing.getFrontOffsetZ() * 1.125F);
/*    */     
/* 24 */     int i = iBlockSource.getXInt() + enumFacing.getFrontOffsetX();
/* 25 */     int j = iBlockSource.getYInt() + enumFacing.getFrontOffsetY();
/* 26 */     int k = iBlockSource.getZInt() + enumFacing.getFrontOffsetZ();
/* 27 */     int m = world.getBlockId(i, j, k);
/*    */ 
/*    */     
/* 30 */     if (BlockRailBase.isRailBlock(m)) {
/* 31 */       d4 = 0.0D;
/* 32 */     } else if (m == 0 && BlockRailBase.isRailBlock(world.getBlockId(i, j - 1, k))) {
/* 33 */       d4 = -1.0D;
/*    */     } else {
/* 35 */       return this.behaviourDefaultDispenseItem.dispense(iBlockSource, itemStack);
/*    */     } 
/*    */     
/* 38 */     EntityMinecart entityMinecart = EntityMinecart.createMinecart(world, d1, d2 + d4, d3, ((ItemMinecart)itemStack.getItem()).minecartType);
/* 39 */     if (itemStack.hasDisplayName()) {
/* 40 */       entityMinecart.setMinecartName(itemStack.getDisplayName());
/*    */     }
/* 42 */     world.spawnEntityInWorld(entityMinecart);
/*    */     
/* 44 */     itemStack.splitStack(1);
/* 45 */     return itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void playDispenseSound(IBlockSource iBlockSource) {
/* 50 */     iBlockSource.getWorld().playAuxSFX(1000, iBlockSource.getXInt(), iBlockSource.getYInt(), iBlockSource.getZInt(), 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BehaviorDispenseMinecart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */