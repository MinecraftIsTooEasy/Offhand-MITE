/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class DispenserBehaviorMobEgg
/*    */   extends BehaviorDefaultDispenseItem
/*    */ {
/*    */   public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/* 10 */     EnumFacing var3 = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
/* 11 */     double var4 = par1IBlockSource.getX() + var3.getFrontOffsetX();
/* 12 */     double var6 = (par1IBlockSource.getYInt() + 0.2F);
/* 13 */     double var8 = par1IBlockSource.getZ() + var3.getFrontOffsetZ();
/*    */     
/* 15 */     Entity var10 = ItemMonsterPlacer.spawnCreature(par1IBlockSource.getWorld(), par2ItemStack.getItemSubtype(), var4, var6, var8, false, null);
/*    */     
/* 17 */     if (var10 instanceof EntityLivingBase && par2ItemStack.hasDisplayName())
/*    */     {
/* 19 */       ((EntityLiving)var10).setCustomNameTag(par2ItemStack.getDisplayName());
/*    */     }
/*    */     
/* 22 */     par2ItemStack.splitStack(1);
/* 23 */     return par2ItemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorMobEgg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */