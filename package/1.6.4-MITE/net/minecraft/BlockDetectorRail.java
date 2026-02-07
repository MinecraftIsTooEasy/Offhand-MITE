/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockDetectorRail
/*     */   extends BlockRailBase
/*     */ {
/*     */   private Icon[] iconArray;
/*     */   
/*     */   public BlockDetectorRail(int par1) {
/*  12 */     super(par1, true);
/*  13 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  18 */     return "0=Flat NS, 1=Flat EW, 2=Inclined East, 3=Inclined West, 4=Inclined North, 5=Inclined South, bit 8 set if activated";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  23 */     return ((metadata >= 0 && metadata < 6) || (metadata >= 8 && metadata < 14));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World par1World) {
/*  31 */     return 20;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/*  39 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/*  47 */     if (!par1World.isRemote) {
/*     */       
/*  49 */       int var6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */       
/*  51 */       if ((var6 & 0x8) == 0)
/*     */       {
/*  53 */         setStateIfMinecartInteractsWithRail(par1World, par2, par3, par4, var6);
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  76 */     if (super.updateTick(world, x, y, z, random)) {
/*  77 */       return true;
/*     */     }
/*  79 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/*  81 */     if ((metadata & 0x8) != 0) {
/*  82 */       setStateIfMinecartInteractsWithRail(world, x, y, z, metadata);
/*     */     }
/*  84 */     return (world.getBlock(x, y, z) != this || world.getBlockMetadata(x, y, z) != metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  94 */     return ((par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x8) != 0) ? 15 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 103 */     return ((par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x8) == 0) ? 0 : ((par5 == 1) ? 15 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setStateIfMinecartInteractsWithRail(World par1World, int par2, int par3, int par4, int par5) {
/* 111 */     boolean var6 = ((par5 & 0x8) != 0);
/* 112 */     boolean var7 = false;
/* 113 */     float var8 = 0.125F;
/* 114 */     List var9 = par1World.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getAABBPool().getAABB((par2 + var8), par3, (par4 + var8), ((par2 + 1) - var8), ((par3 + 1) - var8), ((par4 + 1) - var8)));
/*     */     
/* 116 */     if (!var9.isEmpty())
/*     */     {
/* 118 */       var7 = true;
/*     */     }
/*     */     
/* 121 */     if (var7 && !var6) {
/*     */       
/* 123 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, par5 | 0x8, 3);
/* 124 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
/* 125 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/* 126 */       par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
/*     */     } 
/*     */     
/* 129 */     if (!var7 && var6) {
/*     */       
/* 131 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, par5 & 0x7, 3);
/* 132 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
/* 133 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/* 134 */       par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
/*     */     } 
/*     */     
/* 137 */     if (var7)
/*     */     {
/* 139 */       par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate(par1World));
/*     */     }
/*     */     
/* 142 */     par1World.func_96440_m(par2, par3, par4, this.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 150 */     super.onBlockAdded(par1World, par2, par3, par4);
/* 151 */     setStateIfMinecartInteractsWithRail(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 169 */     if ((par1World.getBlockMetadata(par2, par3, par4) & 0x8) > 0) {
/*     */       
/* 171 */       float var6 = 0.125F;
/* 172 */       List<IInventory> var7 = par1World.selectEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getAABBPool().getAABB((par2 + var6), par3, (par4 + var6), ((par2 + 1) - var6), ((par3 + 1) - var6), ((par4 + 1) - var6)), IEntitySelector.selectInventories);
/*     */       
/* 174 */       if (var7.size() > 0)
/*     */       {
/* 176 */         return Container.calcRedstoneFromInventory(var7.get(0));
/*     */       }
/*     */     } 
/*     */     
/* 180 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 189 */     this.iconArray = new Icon[2];
/* 190 */     this.iconArray[0] = par1IconRegister.registerIcon(getTextureName());
/* 191 */     this.iconArray[1] = par1IconRegister.registerIcon(getTextureName() + "_powered");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 199 */     return ((par2 & 0x8) != 0) ? this.iconArray[1] : this.iconArray[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 204 */     item_block.addMaterial(new Material[] { Material.iron, Material.stone, Material.redstone });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDetectorRail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */