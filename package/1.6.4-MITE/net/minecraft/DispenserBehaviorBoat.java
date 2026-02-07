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
/*     */ final class DispenserBehaviorBoat
/*     */   extends BehaviorDefaultDispenseItem
/*     */ {
/* 156 */   private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();
/*     */   
/*     */   public ItemStack dispenseStack(IBlockSource iBlockSource, ItemStack itemStack) {
/*     */     double d4;
/* 160 */     EnumFacing enumFacing = BlockDispenser.getFacing(iBlockSource.getBlockMetadata());
/* 161 */     World world = iBlockSource.getWorld();
/*     */ 
/*     */     
/* 164 */     double d1 = iBlockSource.getX() + (enumFacing.getFrontOffsetX() * 1.125F);
/* 165 */     double d2 = iBlockSource.getY() + (enumFacing.getFrontOffsetY() * 1.125F);
/* 166 */     double d3 = iBlockSource.getZ() + (enumFacing.getFrontOffsetZ() * 1.125F);
/*     */     
/* 168 */     int i = iBlockSource.getXInt() + enumFacing.getFrontOffsetX();
/* 169 */     int j = iBlockSource.getYInt() + enumFacing.getFrontOffsetY();
/* 170 */     int k = iBlockSource.getZInt() + enumFacing.getFrontOffsetZ();
/* 171 */     Material material = world.getBlockMaterial(i, j, k);
/*     */ 
/*     */     
/* 174 */     if (Material.water.equals(material)) {
/* 175 */       d4 = 1.0D;
/* 176 */     } else if (Material.air.equals(material) && Material.water.equals(world.getBlockMaterial(i, j - 1, k))) {
/* 177 */       d4 = 0.0D;
/*     */     } else {
/* 179 */       return this.defaultDispenserItemBehavior.dispense(iBlockSource, itemStack);
/*     */     } 
/*     */     
/* 182 */     EntityBoat entityBoat = new EntityBoat(world, d1, d2 + d4, d3);
/* 183 */     world.spawnEntityInWorld(entityBoat);
/*     */     
/* 185 */     itemStack.splitStack(1);
/* 186 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playDispenseSound(IBlockSource iBlockSource) {
/* 191 */     iBlockSource.getWorld().playAuxSFX(1000, iBlockSource.getXInt(), iBlockSource.getYInt(), iBlockSource.getZInt(), 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorBoat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */