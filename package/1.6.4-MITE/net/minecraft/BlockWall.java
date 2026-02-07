/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockWall
/*     */   extends Block
/*     */ {
/*   8 */   public static final String[] types = new String[] { "normal", "mossy" };
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockWall(int par1, Block par2Block) {
/*  13 */     super(par1, par2Block.blockMaterial, (new BlockConstants()).setNeverHidesAdjacentFaces());
/*     */ 
/*     */     
/*  16 */     setHardness(par2Block.getBlockHardness(0) * 6.0F / 8.0F);
/*  17 */     setStepSound(par2Block.stepSound);
/*  18 */     setMaxStackSize(8);
/*  19 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  27 */     return (par2 == 1) ? Block.cobblestoneMossy.getBlockTextureFromSide(par1) : Block.cobblestone.getBlockTextureFromSide(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  35 */     return 32;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  68 */     boolean var5 = canConnectWallTo(par1IBlockAccess, par2, par3, par4 - 1);
/*  69 */     boolean var6 = canConnectWallTo(par1IBlockAccess, par2, par3, par4 + 1);
/*  70 */     boolean var7 = canConnectWallTo(par1IBlockAccess, par2 - 1, par3, par4);
/*  71 */     boolean var8 = canConnectWallTo(par1IBlockAccess, par2 + 1, par3, par4);
/*  72 */     float var9 = 0.25F;
/*  73 */     float var10 = 0.75F;
/*  74 */     float var11 = 0.25F;
/*  75 */     float var12 = 0.75F;
/*  76 */     float var13 = 1.0F;
/*     */     
/*  78 */     if (var5)
/*     */     {
/*  80 */       var11 = 0.0F;
/*     */     }
/*     */     
/*  83 */     if (var6)
/*     */     {
/*  85 */       var12 = 1.0F;
/*     */     }
/*     */     
/*  88 */     if (var7)
/*     */     {
/*  90 */       var9 = 0.0F;
/*     */     }
/*     */     
/*  93 */     if (var8)
/*     */     {
/*  95 */       var10 = 1.0F;
/*     */     }
/*     */     
/*  98 */     if (var5 && var6 && !var7 && !var8) {
/*     */       
/* 100 */       var13 = 0.8125F;
/* 101 */       var9 = 0.3125F;
/* 102 */       var10 = 0.6875F;
/*     */     }
/* 104 */     else if (!var5 && !var6 && var7 && var8) {
/*     */       
/* 106 */       var13 = 0.8125F;
/* 107 */       var11 = 0.3125F;
/* 108 */       var12 = 0.6875F;
/*     */     } 
/*     */     
/* 111 */     setBlockBoundsForCurrentThread(var9, 0.0D, var11, var10, var13, var12);
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
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/* 137 */     if (entity instanceof EntityPudding) {
/*     */       
/* 139 */       AxisAlignedBB bounds = (AxisAlignedBB)getCollisionBounds(world, x, y, z, (Entity)null);
/* 140 */       return bounds.setMaxY(y + 1.5D);
/*     */     } 
/*     */ 
/*     */     
/* 144 */     return useFullBlockForCollisions(entity) ? AxisAlignedBB.getBoundingBoxFromPool(x, y, z, 0.0D, 0.0D, 0.0D, 1.0D, 1.5D, 1.0D) : super.getCollisionBounds(world, x, y, z, entity);
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
/*     */   public boolean canConnectWallTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 164 */     Block block = par1IBlockAccess.getBlock(par2, par3, par4);
/*     */     
/* 166 */     if (block == null || block instanceof BlockFence) {
/* 167 */       return false;
/*     */     }
/* 169 */     return (block == this || block.connectsWithFence());
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
/*     */   public boolean isValidMetadata(int metadata) {
/* 191 */     return (metadata >= 0 && metadata < 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/* 196 */     return metadata & 0x1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 205 */     return (par5 == 0) ? super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5) : true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 216 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockWall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */