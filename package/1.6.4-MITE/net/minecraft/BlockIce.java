/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockIce
/*     */   extends BlockBreakable
/*     */ {
/*     */   public BlockIce(int par1) {
/*  11 */     super(par1, "ice", Material.ice, false, (new BlockConstants()).setUsesAlphaBlending());
/*  12 */     this.slipperiness = 0.98F;
/*  13 */     setTickRandomly(true);
/*     */     
/*  15 */     modifyMinHarvestLevel(1);
/*  16 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderBlockPass() {
/*  24 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  33 */     return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean melt(World world, int x, int y, int z) {
/*  83 */     return world.setBlock(x, y, z, Block.waterMoving.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 110 */     if ((!world.isFreezing(x, z) || world.getSavedLightValue(EnumSkyBlock.Block, x, y, z) > 11 - lightOpacity[this.blockID]) && BlockSnow.canMelt(world, x, y, z)) {
/*     */       
/* 112 */       melt(world, x, y, z);
/* 113 */       return true;
/*     */     } 
/*     */     
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMobilityFlag() {
/* 125 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 137 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockIce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */