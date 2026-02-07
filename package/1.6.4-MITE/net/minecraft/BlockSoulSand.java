/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BlockSoulSand
/*    */   extends BlockUnderminable
/*    */ {
/*    */   public BlockSoulSand(int par1) {
/* 11 */     super(par1, Material.sand, new BlockConstants());
/* 12 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */     
/* 14 */     setCushioning(0.4F);
/*    */   }
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
/*    */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/* 29 */     return (entity instanceof EntityItem || entity instanceof EntityFallingSand) ? getStandardFormBoundingBoxFromPool(x, y, z) : getStandardFormBoundingBoxFromPool(x, y, z).addToMaxY(-0.125D);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 37 */     par5Entity.motionX *= 0.4D;
/* 38 */     par5Entity.motionZ *= 0.4D;
/*    */     
/* 40 */     super.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSoulSand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */