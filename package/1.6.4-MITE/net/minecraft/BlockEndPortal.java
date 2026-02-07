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
/*     */ public class BlockEndPortal
/*     */   extends BlockContainer
/*     */ {
/*     */   public static boolean bossDefeated;
/*     */   
/*     */   protected BlockEndPortal(int par1, Material par2Material) {
/*  17 */     super(par1, par2Material, (new BlockConstants()).setNeverHidesAdjacentFaces());
/*  18 */     setLightValue(1.0F);
/*     */     
/*  20 */     setUnlocalizedName("endPortal");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/*  28 */     return new TileEntityEndPortal();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  36 */     float var5 = 0.0625F;
/*  37 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, var5, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  46 */     return (par5 != 0) ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
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
/*     */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/*  85 */     if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null && !par1World.isRemote)
/*     */     {
/*  87 */       par5Entity.travelToDimension(1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  96 */     double var6 = (par2 + par5Random.nextFloat());
/*  97 */     double var8 = (par3 + 0.8F);
/*  98 */     double var10 = (par4 + par5Random.nextFloat());
/*  99 */     double var12 = 0.0D;
/* 100 */     double var14 = 0.0D;
/* 101 */     double var16 = 0.0D;
/*     */     
/* 103 */     par1World.spawnParticle(EnumParticle.smoke, var6, var8, var10, var12, var14, var16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 111 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 119 */     if (!bossDefeated)
/*     */     {
/* 121 */       if (par1World.provider.dimensionId != 0)
/*     */       {
/* 123 */         par1World.setBlockToAir(par2, par3, par4);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 133 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 142 */     this.blockIcon = par1IconRegister.registerIcon("portal");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 152 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 167 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 172 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockEndPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */