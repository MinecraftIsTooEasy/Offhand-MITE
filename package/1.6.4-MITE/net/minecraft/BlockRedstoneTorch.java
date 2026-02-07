/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockRedstoneTorch
/*     */   extends BlockTorch
/*     */ {
/*     */   private boolean torchActive;
/*  15 */   private static Map redstoneUpdateInfoCache = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   private boolean checkForBurnout(World par1World, int par2, int par3, int par4, boolean par5) {
/*  19 */     if (!redstoneUpdateInfoCache.containsKey(par1World))
/*     */     {
/*  21 */       redstoneUpdateInfoCache.put(par1World, new ArrayList());
/*     */     }
/*     */     
/*  24 */     List<RedstoneUpdateInfo> var6 = (List)redstoneUpdateInfoCache.get(par1World);
/*     */     
/*  26 */     if (par5)
/*     */     {
/*  28 */       var6.add(new RedstoneUpdateInfo(par2, par3, par4, par1World.getTotalWorldTime()));
/*     */     }
/*     */     
/*  31 */     int var7 = 0;
/*     */     
/*  33 */     for (int var8 = 0; var8 < var6.size(); var8++) {
/*     */       
/*  35 */       RedstoneUpdateInfo var9 = var6.get(var8);
/*     */       
/*  37 */       if (var9.x == par2 && var9.y == par3 && var9.z == par4) {
/*     */         
/*  39 */         var7++;
/*     */         
/*  41 */         if (var7 >= 8)
/*     */         {
/*  43 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockRedstoneTorch(int par1, boolean par2) {
/*  53 */     super(par1);
/*  54 */     this.torchActive = par2;
/*  55 */     setTickRandomly(true);
/*  56 */     setCreativeTab((CreativeTabs)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World par1World) {
/*  64 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/*  72 */     if (par1World.getBlockMetadata(par2, par3, par4) == 0)
/*     */     {
/*  74 */       super.onBlockAdded(par1World, par2, par3, par4);
/*     */     }
/*     */     
/*  77 */     if (this.torchActive) {
/*     */       
/*  79 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/*  80 */       par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
/*  81 */       par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
/*  82 */       par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
/*  83 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
/*  84 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/*  95 */     if (this.torchActive) {
/*     */       
/*  97 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/*  98 */       par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
/*  99 */       par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
/* 100 */       par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
/* 101 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
/* 102 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 113 */     if (!this.torchActive)
/*     */     {
/* 115 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 119 */     int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/* 120 */     return (var6 == 5 && par5 == 1) ? 0 : ((var6 == 3 && par5 == 3) ? 0 : ((var6 == 4 && par5 == 2) ? 0 : ((var6 == 1 && par5 == 5) ? 0 : ((var6 == 2 && par5 == 4) ? 0 : 15))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isIndirectlyPowered(World par1World, int par2, int par3, int par4) {
/* 129 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/* 130 */     return (var5 == 5 && par1World.getIndirectPowerOutput(par2, par3 - 1, par4, 0)) ? true : ((var5 == 3 && par1World.getIndirectPowerOutput(par2, par3, par4 - 1, 2)) ? true : ((var5 == 4 && par1World.getIndirectPowerOutput(par2, par3, par4 + 1, 3)) ? true : ((var5 == 1 && par1World.getIndirectPowerOutput(par2 - 1, par3, par4, 4)) ? true : ((var5 == 2 && par1World.getIndirectPowerOutput(par2 + 1, par3, par4, 5))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 139 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/* 140 */       return true;
/*     */     }
/* 142 */     boolean var6 = isIndirectlyPowered(par1World, par2, par3, par4);
/* 143 */     List var7 = (List)redstoneUpdateInfoCache.get(par1World);
/*     */     
/* 145 */     while (var7 != null && !var7.isEmpty() && par1World.getTotalWorldTime() - ((RedstoneUpdateInfo)var7.get(0)).updateTime > 60L)
/*     */     {
/* 147 */       var7.remove(0);
/*     */     }
/*     */     
/* 150 */     if (this.torchActive) {
/*     */       
/* 152 */       if (var6)
/*     */       {
/* 154 */         par1World.setBlock(par2, par3, par4, Block.torchRedstoneIdle.blockID, par1World.getBlockMetadata(par2, par3, par4), 3);
/*     */         
/* 156 */         if (checkForBurnout(par1World, par2, par3, par4, true)) {
/*     */           
/* 158 */           par1World.playSoundEffect((par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), "random.fizz", 0.5F, 2.6F + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.8F);
/*     */           
/* 160 */           for (int var8 = 0; var8 < 5; var8++) {
/*     */             
/* 162 */             double var9 = par2 + par5Random.nextDouble() * 0.6D + 0.2D;
/* 163 */             double var11 = par3 + par5Random.nextDouble() * 0.6D + 0.2D;
/* 164 */             double var13 = par4 + par5Random.nextDouble() * 0.6D + 0.2D;
/*     */             
/* 166 */             par1World.spawnParticle(EnumParticle.smoke, var9, var11, var13, 0.0D, 0.0D, 0.0D);
/*     */           } 
/*     */         } 
/*     */         
/* 170 */         return true;
/*     */       }
/*     */     
/* 173 */     } else if (!var6 && !checkForBurnout(par1World, par2, par3, par4, false)) {
/*     */       
/* 175 */       par1World.setBlock(par2, par3, par4, Block.torchRedstoneActive.blockID, par1World.getBlockMetadata(par2, par3, par4), 3);
/* 176 */       return true;
/*     */     } 
/*     */     
/* 179 */     return false;
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 201 */     if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
/* 202 */       return true;
/*     */     }
/* 204 */     boolean indirectly_powered = isIndirectlyPowered(world, x, y, z);
/*     */     
/* 206 */     if ((this.torchActive && indirectly_powered) || (!this.torchActive && !indirectly_powered)) {
/* 207 */       world.scheduleBlockUpdate(x, y, z, this.blockID, tickRate(world));
/*     */     }
/* 209 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 218 */     return (par5 == 0) ? isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5) : 0;
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
/*     */   public boolean canBeCarried() {
/* 231 */     return this.torchActive;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 236 */     if (info.wasExploded()) {
/* 237 */       return super.dropBlockAsEntityItem(info);
/*     */     }
/* 239 */     return dropBlockAsEntityItem(info, Block.torchRedstoneActive);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 247 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 255 */     if (this.torchActive) {
/*     */       
/* 257 */       int var6 = par1World.getBlockMetadata(par2, par3, par4);
/* 258 */       double var7 = (par2 + 0.5F) + (par5Random.nextFloat() - 0.5F) * 0.2D;
/* 259 */       double var9 = (par3 + 0.7F) + (par5Random.nextFloat() - 0.5F) * 0.2D;
/* 260 */       double var11 = (par4 + 0.5F) + (par5Random.nextFloat() - 0.5F) * 0.2D;
/* 261 */       double var13 = 0.2199999988079071D;
/* 262 */       double var15 = 0.27000001072883606D;
/*     */       
/* 264 */       if (var6 == 1) {
/*     */ 
/*     */         
/* 267 */         par1World.spawnParticle(EnumParticle.reddust, var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
/*     */       }
/* 269 */       else if (var6 == 2) {
/*     */ 
/*     */         
/* 272 */         par1World.spawnParticle(EnumParticle.reddust, var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
/*     */       }
/* 274 */       else if (var6 == 3) {
/*     */ 
/*     */         
/* 277 */         par1World.spawnParticle(EnumParticle.reddust, var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
/*     */       }
/* 279 */       else if (var6 == 4) {
/*     */ 
/*     */         
/* 282 */         par1World.spawnParticle(EnumParticle.reddust, var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 287 */         par1World.spawnParticle(EnumParticle.reddust, var7, var9, var11, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 297 */     return Block.torchRedstoneActive.blockID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAssociatedBlockID(int par1) {
/* 306 */     return (par1 == Block.torchRedstoneIdle.blockID || par1 == Block.torchRedstoneActive.blockID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 311 */     return this.torchActive ? "lit" : "unlit";
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 316 */     item_block.addMaterial(new Material[] { Material.wood, Material.redstone });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 321 */     return (!(other_block instanceof BlockRedstoneTorch) && super.canBeReplacedBy(metadata, other_block, other_block_metadata));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRedstoneTorch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */