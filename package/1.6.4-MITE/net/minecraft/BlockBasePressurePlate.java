/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public abstract class BlockBasePressurePlate
/*     */   extends Block
/*     */ {
/*     */   private String pressurePlateIconName;
/*     */   
/*     */   protected BlockBasePressurePlate(int par1, String par2Str, Material par3Material) {
/*  12 */     super(par1, par3Material, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  13 */     this.pressurePlateIconName = par2Str;
/*  14 */     setCreativeTab(CreativeTabs.tabRedstone);
/*  15 */     setTickRandomly(true);
/*  16 */     setMaxStackSize(8);
/*     */     
/*  18 */     func_94353_c_(getMetaFromWeight(15), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  26 */     func_94353_c_(par1IBlockAccess.getBlockMetadata(par2, par3, par4), false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_94353_c_(int par1, boolean for_all_threads) {
/*  32 */     boolean var2 = (getPowerSupply(par1) > 0);
/*  33 */     float var3 = 0.0625F;
/*     */     
/*  35 */     if (var2) {
/*     */       
/*  37 */       setBlockBounds(var3, 0.0D, var3, (1.0F - var3), 0.03125D, (1.0F - var3), for_all_threads);
/*     */     }
/*     */     else {
/*     */       
/*  41 */       setBlockBounds(var3, 0.0D, var3, (1.0F - var3), 0.0625D, (1.0F - var3), for_all_threads);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World par1World) {
/*  50 */     return 20;
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
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/*  97 */     if (block_below == null) {
/*  98 */       return false;
/*     */     }
/* 100 */     return (block_below.isTopFlatAndSolid(block_below_metadata) || BlockFence.isIdAFence(block_below.blockID));
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 142 */     if (super.updateTick(world, x, y, z, random)) {
/* 143 */       return true;
/*     */     }
/* 145 */     int power_level = getPowerSupply(world.getBlockMetadata(x, y, z));
/*     */     
/* 147 */     if (power_level > 0) {
/* 148 */       return setStateIfMobInteractsWithPlate(world, x, y, z, power_level);
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 158 */     if (!par1World.isRemote) {
/*     */       
/* 160 */       int var6 = getPowerSupply(par1World.getBlockMetadata(par2, par3, par4));
/*     */       
/* 162 */       if (var6 == 0)
/*     */       {
/* 164 */         setStateIfMobInteractsWithPlate(par1World, par2, par3, par4, var6);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean setStateIfMobInteractsWithPlate(World par1World, int par2, int par3, int par4, int par5) {
/* 175 */     int var6 = getPlateState(par1World, par2, par3, par4);
/* 176 */     boolean var7 = (par5 > 0);
/* 177 */     boolean var8 = (var6 > 0);
/*     */     
/* 179 */     boolean changed_state = false;
/*     */     
/* 181 */     if (par5 != var6) {
/*     */       
/* 183 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, getMetaFromWeight(var6), 2);
/* 184 */       func_94354_b_(par1World, par2, par3, par4);
/* 185 */       par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
/*     */       
/* 187 */       changed_state = true;
/*     */     } 
/*     */     
/* 190 */     if (!var8 && var7) {
/*     */       
/* 192 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.1D, par4 + 0.5D, "random.click", 0.3F, 0.5F);
/*     */     }
/* 194 */     else if (var8 && !var7) {
/*     */       
/* 196 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.1D, par4 + 0.5D, "random.click", 0.3F, 0.6F);
/*     */     } 
/*     */     
/* 199 */     if (var8)
/*     */     {
/* 201 */       par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate(par1World));
/*     */     }
/*     */     
/* 204 */     return changed_state;
/*     */   }
/*     */ 
/*     */   
/*     */   protected AxisAlignedBB getSensitiveAABB(int par1, int par2, int par3) {
/* 209 */     float var4 = 0.125F;
/* 210 */     return AxisAlignedBB.getAABBPool().getAABB((par1 + var4), par2, (par3 + var4), ((par1 + 1) - var4), par2 + 0.25D, ((par3 + 1) - var4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 220 */     if (getPowerSupply(par6) > 0)
/*     */     {
/* 222 */       func_94354_b_(par1World, par2, par3, par4);
/*     */     }
/*     */     
/* 225 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_94354_b_(World par1World, int par2, int par3, int par4) {
/* 230 */     par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
/* 231 */     par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 241 */     return getPowerSupply(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 250 */     return (par5 == 1) ? getPowerSupply(par1IBlockAccess.getBlockMetadata(par2, par3, par4)) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 258 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/* 267 */     float var1 = 0.5F;
/* 268 */     float var2 = 0.125F;
/* 269 */     float var3 = 0.5F;
/* 270 */     setBlockBoundsForCurrentThread((0.5F - var1), (0.5F - var2), (0.5F - var3), (0.5F + var1), (0.5F + var2), (0.5F + var3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMobilityFlag() {
/* 279 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int getPlateState(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int getPowerSupply(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int getMetaFromWeight(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 304 */     this.blockIcon = par1IconRegister.registerIcon(this.pressurePlateIconName);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 314 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 319 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 324 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBasePressurePlate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */