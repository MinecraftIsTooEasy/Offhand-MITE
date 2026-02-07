/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockRedstoneWire
/*     */   extends Block
/*     */ {
/*     */   private boolean wiresProvidePower = true;
/*  16 */   private Set blocksNeedingUpdate = new HashSet();
/*     */   
/*     */   private Icon field_94413_c;
/*     */   
/*     */   private Icon field_94410_cO;
/*     */   private Icon field_94411_cP;
/*     */   private Icon field_94412_cQ;
/*     */   
/*     */   public BlockRedstoneWire(int par1) {
/*  25 */     super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  26 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  31 */     return "All bits used for amount of power, which diminishes with distance from power source";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  36 */     return (metadata >= 0 && metadata < 16);
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
/*     */   public int getRenderType() {
/*  70 */     return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  79 */     return 8388608;
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
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/*  92 */     if (block_below == null) {
/*  93 */       return false;
/*     */     }
/*  95 */     return block_below.isTopFlatAndSolid(block_below_metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean updateAndPropagateCurrentStrength(World par1World, int par2, int par3, int par4) {
/* 106 */     boolean changed_state = calculateCurrentChanges(par1World, par2, par3, par4, par2, par3, par4);
/*     */     
/* 108 */     ArrayList<ChunkPosition> var5 = new ArrayList(this.blocksNeedingUpdate);
/* 109 */     this.blocksNeedingUpdate.clear();
/*     */     
/* 111 */     for (int var6 = 0; var6 < var5.size(); var6++) {
/*     */       
/* 113 */       ChunkPosition var7 = var5.get(var6);
/* 114 */       par1World.notifyBlocksOfNeighborChange(var7.x, var7.y, var7.z, this.blockID);
/*     */     } 
/*     */     
/* 117 */     return changed_state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean calculateCurrentChanges(World par1World, int par2, int par3, int par4, int par5, int par6, int par7) {
/* 123 */     int var8 = par1World.getBlockMetadata(par2, par3, par4);
/* 124 */     byte var9 = 0;
/* 125 */     int var15 = getMaxCurrentStrength(par1World, par5, par6, par7, var9);
/* 126 */     this.wiresProvidePower = false;
/* 127 */     int var10 = par1World.getStrongestIndirectPower(par2, par3, par4);
/* 128 */     this.wiresProvidePower = true;
/*     */     
/* 130 */     if (var10 > 0 && var10 > var15 - 1)
/*     */     {
/* 132 */       var15 = var10;
/*     */     }
/*     */     
/* 135 */     int var11 = 0;
/*     */     
/* 137 */     for (int var12 = 0; var12 < 4; var12++) {
/*     */       
/* 139 */       int var13 = par2;
/* 140 */       int var14 = par4;
/*     */       
/* 142 */       if (var12 == 0)
/*     */       {
/* 144 */         var13 = par2 - 1;
/*     */       }
/*     */       
/* 147 */       if (var12 == 1)
/*     */       {
/* 149 */         var13++;
/*     */       }
/*     */       
/* 152 */       if (var12 == 2)
/*     */       {
/* 154 */         var14 = par4 - 1;
/*     */       }
/*     */       
/* 157 */       if (var12 == 3)
/*     */       {
/* 159 */         var14++;
/*     */       }
/*     */       
/* 162 */       if (var13 != par5 || var14 != par7)
/*     */       {
/* 164 */         var11 = getMaxCurrentStrength(par1World, var13, par3, var14, var11);
/*     */       }
/*     */       
/* 167 */       if (par1World.isBlockNormalCube(var13, par3, var14) && !par1World.isBlockNormalCube(par2, par3 + 1, par4)) {
/*     */         
/* 169 */         if ((var13 != par5 || var14 != par7) && par3 >= par6)
/*     */         {
/* 171 */           var11 = getMaxCurrentStrength(par1World, var13, par3 + 1, var14, var11);
/*     */         }
/*     */       }
/* 174 */       else if (!par1World.isBlockNormalCube(var13, par3, var14) && (var13 != par5 || var14 != par7) && par3 <= par6) {
/*     */         
/* 176 */         var11 = getMaxCurrentStrength(par1World, var13, par3 - 1, var14, var11);
/*     */       } 
/*     */     } 
/*     */     
/* 180 */     if (var11 > var15) {
/*     */       
/* 182 */       var15 = var11 - 1;
/*     */     }
/* 184 */     else if (var15 > 0) {
/*     */       
/* 186 */       var15--;
/*     */     }
/*     */     else {
/*     */       
/* 190 */       var15 = 0;
/*     */     } 
/*     */     
/* 193 */     if (var10 > var15 - 1)
/*     */     {
/* 195 */       var15 = var10;
/*     */     }
/*     */     
/* 198 */     if (var8 != var15) {
/*     */       
/* 200 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, var15, 2);
/* 201 */       this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4));
/* 202 */       this.blocksNeedingUpdate.add(new ChunkPosition(par2 - 1, par3, par4));
/* 203 */       this.blocksNeedingUpdate.add(new ChunkPosition(par2 + 1, par3, par4));
/* 204 */       this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3 - 1, par4));
/* 205 */       this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3 + 1, par4));
/* 206 */       this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4 - 1));
/* 207 */       this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4 + 1));
/*     */       
/* 209 */       return true;
/*     */     } 
/*     */     
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void notifyWireNeighborsOfNeighborChange(World par1World, int par2, int par3, int par4) {
/* 221 */     if (par1World.getBlockId(par2, par3, par4) == this.blockID) {
/*     */       
/* 223 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
/* 224 */       par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
/* 225 */       par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
/* 226 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
/* 227 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
/* 228 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/* 229 */       par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 238 */     super.onBlockAdded(par1World, par2, par3, par4);
/*     */     
/* 240 */     if (!par1World.isRemote) {
/*     */       
/* 242 */       updateAndPropagateCurrentStrength(par1World, par2, par3, par4);
/* 243 */       par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
/* 244 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/* 245 */       notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3, par4);
/* 246 */       notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3, par4);
/* 247 */       notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 - 1);
/* 248 */       notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 + 1);
/*     */       
/* 250 */       if (par1World.isBlockNormalCube(par2 - 1, par3, par4)) {
/*     */         
/* 252 */         notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 + 1, par4);
/*     */       }
/*     */       else {
/*     */         
/* 256 */         notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 - 1, par4);
/*     */       } 
/*     */       
/* 259 */       if (par1World.isBlockNormalCube(par2 + 1, par3, par4)) {
/*     */         
/* 261 */         notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 + 1, par4);
/*     */       }
/*     */       else {
/*     */         
/* 265 */         notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 - 1, par4);
/*     */       } 
/*     */       
/* 268 */       if (par1World.isBlockNormalCube(par2, par3, par4 - 1)) {
/*     */         
/* 270 */         notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 - 1);
/*     */       }
/*     */       else {
/*     */         
/* 274 */         notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 - 1);
/*     */       } 
/*     */       
/* 277 */       if (par1World.isBlockNormalCube(par2, par3, par4 + 1)) {
/*     */         
/* 279 */         notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 + 1);
/*     */       }
/*     */       else {
/*     */         
/* 283 */         notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 + 1);
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
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 295 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*     */     
/* 297 */     if (!par1World.isRemote) {
/*     */       
/* 299 */       par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
/* 300 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/* 301 */       par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
/* 302 */       par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
/* 303 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
/* 304 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
/* 305 */       updateAndPropagateCurrentStrength(par1World, par2, par3, par4);
/* 306 */       notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3, par4);
/* 307 */       notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3, par4);
/* 308 */       notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 - 1);
/* 309 */       notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 + 1);
/*     */       
/* 311 */       if (par1World.isBlockNormalCube(par2 - 1, par3, par4)) {
/*     */         
/* 313 */         notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 + 1, par4);
/*     */       }
/*     */       else {
/*     */         
/* 317 */         notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 - 1, par4);
/*     */       } 
/*     */       
/* 320 */       if (par1World.isBlockNormalCube(par2 + 1, par3, par4)) {
/*     */         
/* 322 */         notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 + 1, par4);
/*     */       }
/*     */       else {
/*     */         
/* 326 */         notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 - 1, par4);
/*     */       } 
/*     */       
/* 329 */       if (par1World.isBlockNormalCube(par2, par3, par4 - 1)) {
/*     */         
/* 331 */         notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 - 1);
/*     */       }
/*     */       else {
/*     */         
/* 335 */         notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 - 1);
/*     */       } 
/*     */       
/* 338 */       if (par1World.isBlockNormalCube(par2, par3, par4 + 1)) {
/*     */         
/* 340 */         notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 + 1);
/*     */       }
/*     */       else {
/*     */         
/* 344 */         notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 + 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMaxCurrentStrength(World par1World, int par2, int par3, int par4, int par5) {
/* 355 */     if (par1World.getBlockId(par2, par3, par4) != this.blockID)
/*     */     {
/* 357 */       return par5;
/*     */     }
/*     */ 
/*     */     
/* 361 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/* 362 */     return (var6 > par5) ? var6 : par5;
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 393 */     if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
/* 394 */       return true;
/*     */     }
/* 396 */     return updateAndPropagateCurrentStrength(world, x, y, z);
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
/* 409 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 414 */     return dropBlockAsEntityItem(info, Item.redstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDropExperienceOrbs() {
/* 419 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 428 */     return !this.wiresProvidePower ? 0 : isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 438 */     if (!this.wiresProvidePower)
/*     */     {
/* 440 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 444 */     int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */     
/* 446 */     if (var6 == 0)
/*     */     {
/* 448 */       return 0;
/*     */     }
/* 450 */     if (par5 == 1)
/*     */     {
/* 452 */       return var6;
/*     */     }
/*     */ 
/*     */     
/* 456 */     boolean var7 = (isPoweredOrRepeater(par1IBlockAccess, par2 - 1, par3, par4, 1) || (!par1IBlockAccess.isBlockNormalCube(par2 - 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 - 1, par3 - 1, par4, -1)));
/* 457 */     boolean var8 = (isPoweredOrRepeater(par1IBlockAccess, par2 + 1, par3, par4, 3) || (!par1IBlockAccess.isBlockNormalCube(par2 + 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 + 1, par3 - 1, par4, -1)));
/* 458 */     boolean var9 = (isPoweredOrRepeater(par1IBlockAccess, par2, par3, par4 - 1, 2) || (!par1IBlockAccess.isBlockNormalCube(par2, par3, par4 - 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 - 1, par4 - 1, -1)));
/* 459 */     boolean var10 = (isPoweredOrRepeater(par1IBlockAccess, par2, par3, par4 + 1, 0) || (!par1IBlockAccess.isBlockNormalCube(par2, par3, par4 + 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 - 1, par4 + 1, -1)));
/*     */     
/* 461 */     if (!par1IBlockAccess.isBlockNormalCube(par2, par3 + 1, par4)) {
/*     */       
/* 463 */       if (par1IBlockAccess.isBlockNormalCube(par2 - 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 - 1, par3 + 1, par4, -1))
/*     */       {
/* 465 */         var7 = true;
/*     */       }
/*     */       
/* 468 */       if (par1IBlockAccess.isBlockNormalCube(par2 + 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 + 1, par3 + 1, par4, -1))
/*     */       {
/* 470 */         var8 = true;
/*     */       }
/*     */       
/* 473 */       if (par1IBlockAccess.isBlockNormalCube(par2, par3, par4 - 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 + 1, par4 - 1, -1))
/*     */       {
/* 475 */         var9 = true;
/*     */       }
/*     */       
/* 478 */       if (par1IBlockAccess.isBlockNormalCube(par2, par3, par4 + 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 + 1, par4 + 1, -1))
/*     */       {
/* 480 */         var10 = true;
/*     */       }
/*     */     } 
/*     */     
/* 484 */     return (!var9 && !var8 && !var7 && !var10 && par5 >= 2 && par5 <= 5) ? var6 : ((par5 == 2 && var9 && !var7 && !var8) ? var6 : ((par5 == 3 && var10 && !var7 && !var8) ? var6 : ((par5 == 4 && var7 && !var9 && !var10) ? var6 : ((par5 == 5 && var8 && !var9 && !var10) ? var6 : 0))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 494 */     return this.wiresProvidePower;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 502 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */     
/* 504 */     if (var6 > 0) {
/*     */       
/* 506 */       double var7 = par2 + 0.5D + (par5Random.nextFloat() - 0.5D) * 0.2D;
/* 507 */       double var9 = (par3 + 0.0625F);
/* 508 */       double var11 = par4 + 0.5D + (par5Random.nextFloat() - 0.5D) * 0.2D;
/* 509 */       float var13 = var6 / 15.0F;
/* 510 */       float var14 = var13 * 0.6F + 0.4F;
/*     */       
/* 512 */       if (var6 == 0)
/*     */       {
/* 514 */         var14 = 0.0F;
/*     */       }
/*     */       
/* 517 */       float var15 = var13 * var13 * 0.7F - 0.5F;
/* 518 */       float var16 = var13 * var13 * 0.6F - 0.7F;
/*     */       
/* 520 */       if (var15 < 0.0F)
/*     */       {
/* 522 */         var15 = 0.0F;
/*     */       }
/*     */       
/* 525 */       if (var16 < 0.0F)
/*     */       {
/* 527 */         var16 = 0.0F;
/*     */       }
/*     */ 
/*     */       
/* 531 */       par1World.spawnParticle(EnumParticle.reddust, var7, var9, var11, var14, var15, var16);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPowerProviderOrWire(IBlockAccess par0IBlockAccess, int par1, int par2, int par3, int par4) {
/* 541 */     int var5 = par0IBlockAccess.getBlockId(par1, par2, par3);
/*     */     
/* 543 */     if (var5 == Block.redstoneWire.blockID)
/*     */     {
/* 545 */       return true;
/*     */     }
/* 547 */     if (var5 == 0)
/*     */     {
/* 549 */       return false;
/*     */     }
/* 551 */     if (!Block.redstoneRepeaterIdle.func_94487_f(var5))
/*     */     {
/* 553 */       return (Block.blocksList[var5].canProvidePower() && par4 != -1);
/*     */     }
/*     */ 
/*     */     
/* 557 */     int var6 = par0IBlockAccess.getBlockMetadata(par1, par2, par3);
/* 558 */     return (par4 == (var6 & 0x3) || par4 == Direction.rotateOpposite[var6 & 0x3]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPoweredOrRepeater(IBlockAccess par0IBlockAccess, int par1, int par2, int par3, int par4) {
/* 568 */     if (isPowerProviderOrWire(par0IBlockAccess, par1, par2, par3, par4))
/*     */     {
/* 570 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 574 */     int var5 = par0IBlockAccess.getBlockId(par1, par2, par3);
/*     */     
/* 576 */     if (var5 == Block.redstoneRepeaterActive.blockID) {
/*     */       
/* 578 */       int var6 = par0IBlockAccess.getBlockMetadata(par1, par2, par3);
/* 579 */       return (par4 == (var6 & 0x3));
/*     */     } 
/*     */ 
/*     */     
/* 583 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 593 */     return Item.redstone.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 602 */     this.field_94413_c = par1IconRegister.registerIcon(getTextureName() + "_" + "cross");
/* 603 */     this.field_94410_cO = par1IconRegister.registerIcon(getTextureName() + "_" + "line");
/* 604 */     this.field_94411_cP = par1IconRegister.registerIcon(getTextureName() + "_" + "cross_overlay");
/* 605 */     this.field_94412_cQ = par1IconRegister.registerIcon(getTextureName() + "_" + "line_overlay");
/* 606 */     this.blockIcon = this.field_94413_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Icon getRedstoneWireIcon(String par0Str) {
/* 611 */     return par0Str.equals("cross") ? Block.redstoneWire.field_94413_c : (par0Str.equals("line") ? Block.redstoneWire.field_94410_cO : (par0Str.equals("cross_overlay") ? Block.redstoneWire.field_94411_cP : (par0Str.equals("line_overlay") ? Block.redstoneWire.field_94412_cQ : null)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeSoundWhenPlaced(World world, int x, int y, int z, int metadata) {
/* 616 */     if (this.stepSound != null) {
/* 617 */       world.playSoundAtBlock(x, y, z, this.stepSound.getPlaceSound(), this.stepSound.getVolume() / 4.0F, this.stepSound.getPitch() * 0.8F);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 622 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 627 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRedstoneWire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */