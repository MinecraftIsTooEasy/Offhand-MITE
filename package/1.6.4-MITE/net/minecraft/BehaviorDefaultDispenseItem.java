/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorDefaultDispenseItem
/*    */   implements IBehaviorDispenseItem
/*    */ {
/*    */   public boolean suppress_dispense_particles;
/*    */   
/*    */   public final ItemStack dispense(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/* 12 */     if ((par1IBlockSource.getWorld()).isRemote) {
/* 13 */       Minecraft.setErrorMessage("dispense: called on client?");
/*    */     }
/* 15 */     ItemStack var3 = dispenseStack(par1IBlockSource, par2ItemStack);
/* 16 */     playDispenseSound(par1IBlockSource);
/*    */ 
/*    */     
/* 19 */     if (this.suppress_dispense_particles) {
/* 20 */       this.suppress_dispense_particles = false;
/*    */     } else {
/* 22 */       spawnDispenseParticles(par1IBlockSource, BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata()));
/*    */     } 
/* 24 */     return var3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/* 32 */     EnumFacing var3 = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
/* 33 */     IPosition var4 = BlockDispenser.getIPositionFromBlockSource(par1IBlockSource);
/* 34 */     ItemStack var5 = par2ItemStack.splitStack(1);
/* 35 */     doDispense(par1IBlockSource.getWorld(), var5, 6, var3, var4);
/* 36 */     return par2ItemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void doDispense(World par0World, ItemStack par1ItemStack, int par2, EnumFacing par3EnumFacing, IPosition par4IPosition) {
/* 41 */     double var5 = par4IPosition.getX();
/* 42 */     double var7 = par4IPosition.getY();
/* 43 */     double var9 = par4IPosition.getZ();
/* 44 */     EntityItem var11 = new EntityItem(par0World, var5, var7 - 0.3D, var9, par1ItemStack);
/* 45 */     double var12 = par0World.rand.nextDouble() * 0.1D + 0.2D;
/* 46 */     var11.motionX = par3EnumFacing.getFrontOffsetX() * var12;
/* 47 */     var11.motionY = 0.20000000298023224D;
/* 48 */     var11.motionZ = par3EnumFacing.getFrontOffsetZ() * var12;
/* 49 */     var11.motionX += par0World.rand.nextGaussian() * 0.007499999832361937D * par2;
/* 50 */     var11.motionY += par0World.rand.nextGaussian() * 0.007499999832361937D * par2;
/* 51 */     var11.motionZ += par0World.rand.nextGaussian() * 0.007499999832361937D * par2;
/* 52 */     par0World.spawnEntityInWorld(var11);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void playDispenseSound(IBlockSource par1IBlockSource) {
/* 60 */     par1IBlockSource.getWorld().playAuxSFX(1000, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void spawnDispenseParticles(IBlockSource par1IBlockSource, EnumFacing par2EnumFacing) {
/* 68 */     par1IBlockSource.getWorld().playAuxSFX(2000, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), func_82488_a(par2EnumFacing));
/*    */   }
/*    */ 
/*    */   
/*    */   private int func_82488_a(EnumFacing par1EnumFacing) {
/* 73 */     return par1EnumFacing.getFrontOffsetX() + 1 + (par1EnumFacing.getFrontOffsetZ() + 1) * 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BehaviorDefaultDispenseItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */