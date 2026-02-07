/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ final class DispenserBehaviorFireball
/*     */   extends BehaviorDefaultDispenseItem
/*     */ {
/*     */   public ItemStack dispenseStack(IBlockSource iBlockSource, ItemStack itemStack) {
/* 129 */     EnumFacing enumFacing = BlockDispenser.getFacing(iBlockSource.getBlockMetadata());
/*     */     
/* 131 */     IPosition iPosition = BlockDispenser.getIPositionFromBlockSource(iBlockSource);
/* 132 */     double d1 = iPosition.getX() + (enumFacing.getFrontOffsetX() * 0.3F);
/* 133 */     double d2 = iPosition.getY() + (enumFacing.getFrontOffsetX() * 0.3F);
/* 134 */     double d3 = iPosition.getZ() + (enumFacing.getFrontOffsetZ() * 0.3F);
/*     */     
/* 136 */     World world = iBlockSource.getWorld();
/* 137 */     Random random = world.rand;
/*     */     
/* 139 */     double d4 = random.nextGaussian() * 0.05D + enumFacing.getFrontOffsetX();
/* 140 */     double d5 = random.nextGaussian() * 0.05D + enumFacing.getFrontOffsetY();
/* 141 */     double d6 = random.nextGaussian() * 0.05D + enumFacing.getFrontOffsetZ();
/*     */     
/* 143 */     world.spawnEntityInWorld(new EntitySmallFireball(world, d1, d2, d3, d4, d5, d6));
/*     */     
/* 145 */     itemStack.splitStack(1);
/* 146 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playDispenseSound(IBlockSource iBlockSource) {
/* 151 */     iBlockSource.getWorld().playAuxSFX(1009, iBlockSource.getXInt(), iBlockSource.getYInt(), iBlockSource.getZInt(), 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorFireball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */