/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BehaviorProjectileDispense
/*    */   extends BehaviorDefaultDispenseItem
/*    */ {
/*    */   public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/* 10 */     World var3 = par1IBlockSource.getWorld();
/* 11 */     IPosition var4 = BlockDispenser.getIPositionFromBlockSource(par1IBlockSource);
/* 12 */     EnumFacing var5 = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
/* 13 */     IProjectile var6 = getProjectileEntity(var3, var4);
/* 14 */     var6.setThrowableHeading(var5.getFrontOffsetX(), (var5.getFrontOffsetY() + 0.1F), var5.getFrontOffsetZ(), func_82500_b(), func_82498_a());
/*    */     
/* 16 */     if (var6 instanceof EntityArrow) {
/*    */       
/* 18 */       EntityArrow arrow = (EntityArrow)var6;
/*    */       
/* 20 */       arrow.scaleVelocity(2.0F);
/*    */     } 
/*    */     
/* 23 */     var3.spawnEntityInWorld((Entity)var6);
/* 24 */     par2ItemStack.splitStack(1);
/* 25 */     return par2ItemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void playDispenseSound(IBlockSource par1IBlockSource) {
/* 33 */     par1IBlockSource.getWorld().playAuxSFX(1002, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract IProjectile getProjectileEntity(World paramWorld, IPosition paramIPosition);
/*    */ 
/*    */ 
/*    */   
/*    */   protected float func_82498_a() {
/* 43 */     return 6.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_82500_b() {
/* 48 */     return 1.1F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BehaviorProjectileDispense.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */