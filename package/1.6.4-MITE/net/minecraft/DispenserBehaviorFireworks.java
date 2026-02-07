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
/*     */ final class DispenserBehaviorFireworks
/*     */   extends BehaviorDefaultDispenseItem
/*     */ {
/*     */   public ItemStack dispenseStack(IBlockSource iBlockSource, ItemStack itemStack) {
/* 107 */     EnumFacing enumFacing = BlockDispenser.getFacing(iBlockSource.getBlockMetadata());
/*     */     
/* 109 */     double d1 = iBlockSource.getX() + enumFacing.getFrontOffsetX();
/* 110 */     double d2 = (iBlockSource.getYInt() + 0.2F);
/* 111 */     double d3 = iBlockSource.getZ() + enumFacing.getFrontOffsetZ();
/*     */     
/* 113 */     EntityFireworkRocket entityFireworkRocket = new EntityFireworkRocket(iBlockSource.getWorld(), d1, d2, d3, itemStack);
/* 114 */     iBlockSource.getWorld().spawnEntityInWorld(entityFireworkRocket);
/*     */     
/* 116 */     itemStack.splitStack(1);
/* 117 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playDispenseSound(IBlockSource iBlockSource) {
/* 122 */     iBlockSource.getWorld().playAuxSFX(1002, iBlockSource.getXInt(), iBlockSource.getYInt(), iBlockSource.getZInt(), 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorFireworks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */