/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockDaylightDetector
/*     */   extends BlockContainer {
/*   7 */   private Icon[] iconArray = new Icon[2];
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockDaylightDetector(int par1) {
/*  12 */     super(par1, Material.wood, new BlockConstants());
/*  13 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);
/*  14 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  19 */     return "All bits used for light value";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  24 */     return (metadata >= 0 && metadata < 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  32 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  42 */     return par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateLightLevel(World par1World, int par2, int par3, int par4) {
/*  73 */     if (!par1World.provider.hasNoSky) {
/*     */       
/*  75 */       int var5 = par1World.getBlockMetadata(par2, par3, par4);
/*  76 */       int var6 = par1World.getSavedLightValue(EnumSkyBlock.Sky, par2, par3, par4) - par1World.skylightSubtracted;
/*  77 */       float var7 = par1World.getCelestialAngleRadians(1.0F);
/*     */       
/*  79 */       if (var7 < 3.1415927F) {
/*     */         
/*  81 */         var7 += (0.0F - var7) * 0.2F;
/*     */       }
/*     */       else {
/*     */         
/*  85 */         var7 += (6.2831855F - var7) * 0.2F;
/*     */       } 
/*     */       
/*  88 */       var6 = Math.round(var6 * MathHelper.cos(var7));
/*     */       
/*  90 */       if (var6 < 0)
/*     */       {
/*  92 */         var6 = 0;
/*     */       }
/*     */       
/*  95 */       if (var6 > 15)
/*     */       {
/*  97 */         var6 = 15;
/*     */       }
/*     */       
/* 100 */       if (var5 != var6)
/*     */       {
/* 102 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 3);
/*     */       }
/*     */     } 
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
/*     */   public boolean canProvidePower() {
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/* 137 */     return new TileEntityDaylightDetector();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 145 */     return (par1 == 1) ? this.iconArray[0] : this.iconArray[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 154 */     this.iconArray[0] = par1IconRegister.registerIcon(getTextureName() + "_top");
/* 155 */     this.iconArray[1] = par1IconRegister.registerIcon(getTextureName() + "_side");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 165 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDaylightDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */