/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockRedstoneLogic
/*     */   extends BlockDirectional
/*     */ {
/*     */   protected final boolean isRepeaterPowered;
/*     */   
/*     */   protected BlockRedstoneLogic(int par1, boolean par2) {
/*  13 */     super(par1, Material.circuits, (new BlockConstants()).setNotAlwaysLegal());
/*  14 */     this.isRepeaterPowered = par2;
/*  15 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
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
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/*  44 */     return (block_below != null && block_below.isTopFlatAndSolid(block_below_metadata));
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
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  58 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/*  59 */       return true;
/*     */     }
/*  61 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */     
/*  63 */     if (!func_94476_e(par1World, par2, par3, par4, var6)) {
/*     */       
/*  65 */       boolean var7 = isGettingInput(par1World, par2, par3, par4, var6);
/*     */       
/*  67 */       if (this.isRepeaterPowered && !var7) {
/*     */         
/*  69 */         par1World.setBlock(par2, par3, par4, (func_94484_i()).blockID, var6, 2);
/*  70 */         return true;
/*     */       } 
/*  72 */       if (!this.isRepeaterPowered) {
/*     */         
/*  74 */         par1World.setBlock(par2, par3, par4, (func_94485_e()).blockID, var6, 2);
/*     */         
/*  76 */         if (!var7)
/*     */         {
/*  78 */           par1World.scheduleBlockUpdateWithPriority(par2, par3, par4, (func_94485_e()).blockID, func_94486_g(var6), -1);
/*     */         }
/*     */         
/*  81 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  93 */     return (par1 == 0) ? (this.isRepeaterPowered ? Block.torchRedstoneActive.getBlockTextureFromSide(par1) : Block.torchRedstoneIdle.getBlockTextureFromSide(par1)) : ((par1 == 1) ? this.blockIcon : Block.stoneDoubleSlab.getBlockTextureFromSide(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int j(int metadata) {
/*  98 */     return metadata & 0x3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 107 */     return (par5 != 0 && par5 != 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 115 */     return 36;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_96470_c(int par1) {
/* 120 */     return this.isRepeaterPowered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 129 */     return isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 139 */     int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */     
/* 141 */     if (!func_96470_c(var6))
/*     */     {
/* 143 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 147 */     int var7 = j(var6);
/* 148 */     return (var7 == 0 && par5 == 3) ? func_94480_d(par1IBlockAccess, par2, par3, par4, var6) : ((var7 == 1 && par5 == 4) ? func_94480_d(par1IBlockAccess, par2, par3, par4, var6) : ((var7 == 2 && par5 == 2) ? func_94480_d(par1IBlockAccess, par2, par3, par4, var6) : ((var7 == 3 && par5 == 5) ? func_94480_d(par1IBlockAccess, par2, par3, par4, var6) : 0)));
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
/* 179 */     if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
/*     */       
/* 181 */       world.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID);
/* 182 */       world.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID);
/* 183 */       world.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID);
/* 184 */       world.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID);
/* 185 */       world.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID);
/* 186 */       world.notifyBlocksOfNeighborChange(x, y + 1, z, this.blockID);
/*     */       
/* 188 */       return true;
/*     */     } 
/*     */     
/* 191 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 193 */     func_94479_f(world, x, y, z, neighbor_block_id);
/*     */     
/* 195 */     return (world.getBlock(x, y, z) != this || world.getBlockMetadata(x, y, z) != metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_94479_f(World par1World, int par2, int par3, int par4, int par5) {
/* 200 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */     
/* 202 */     if (!func_94476_e(par1World, par2, par3, par4, var6)) {
/*     */       
/* 204 */       boolean var7 = isGettingInput(par1World, par2, par3, par4, var6);
/*     */       
/* 206 */       if (((this.isRepeaterPowered && !var7) || (!this.isRepeaterPowered && var7)) && !par1World.isBlockTickScheduledThisTick(par2, par3, par4, this.blockID)) {
/*     */         
/* 208 */         byte var8 = -1;
/*     */         
/* 210 */         if (func_83011_d(par1World, par2, par3, par4, var6)) {
/*     */           
/* 212 */           var8 = -3;
/*     */         }
/* 214 */         else if (this.isRepeaterPowered) {
/*     */           
/* 216 */           var8 = -2;
/*     */         } 
/*     */         
/* 219 */         par1World.scheduleBlockUpdateWithPriority(par2, par3, par4, this.blockID, func_94481_j_(var6), var8);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94476_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isGettingInput(World par1World, int par2, int par3, int par4, int par5) {
/* 231 */     return (getInputStrength(par1World, par2, par3, par4, par5) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getInputStrength(World par1World, int par2, int par3, int par4, int par5) {
/* 239 */     int var6 = j(par5);
/* 240 */     int var7 = par2 + Direction.offsetX[var6];
/* 241 */     int var8 = par4 + Direction.offsetZ[var6];
/* 242 */     int var9 = par1World.getIndirectPowerLevelTo(var7, par3, var8, Direction.directionToFacing[var6]);
/* 243 */     return (var9 >= 15) ? var9 : Math.max(var9, (par1World.getBlockId(var7, par3, var8) == Block.redstoneWire.blockID) ? par1World.getBlockMetadata(var7, par3, var8) : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_94482_f(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 248 */     int var6 = j(par5);
/*     */     
/* 250 */     switch (var6) {
/*     */       
/*     */       case 0:
/*     */       case 2:
/* 254 */         return Math.max(func_94488_g(par1IBlockAccess, par2 - 1, par3, par4, 4), func_94488_g(par1IBlockAccess, par2 + 1, par3, par4, 5));
/*     */       
/*     */       case 1:
/*     */       case 3:
/* 258 */         return Math.max(func_94488_g(par1IBlockAccess, par2, par3, par4 + 1, 3), func_94488_g(par1IBlockAccess, par2, par3, par4 - 1, 2));
/*     */     } 
/*     */     
/* 261 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_94488_g(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 267 */     int var6 = par1IBlockAccess.getBlockId(par2, par3, par4);
/* 268 */     return func_94477_d(var6) ? ((var6 == Block.redstoneWire.blockID) ? par1IBlockAccess.getBlockMetadata(par2, par3, par4) : par1IBlockAccess.isBlockProvidingPowerTo(par2, par3, par4, par5)) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 276 */     return true;
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
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 298 */     if (!test_only)
/*     */     {
/* 300 */       if (placer != null) {
/*     */         
/* 302 */         int placer_direction = ((MathHelper.floor_double((placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3) + 2) % 4;
/* 303 */         world.setBlockMetadataWithNotify(x, y, z, placer_direction, 3);
/*     */         
/* 305 */         if (isGettingInput(world, x, y, z, placer_direction)) {
/* 306 */           world.scheduleBlockUpdate(x, y, z, this.blockID, 1);
/*     */         }
/*     */       } 
/*     */     }
/* 310 */     return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 318 */     func_94483_i_(par1World, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_94483_i_(World par1World, int par2, int par3, int par4) {
/* 323 */     int var5 = j(par1World.getBlockMetadata(par2, par3, par4));
/*     */     
/* 325 */     if (var5 == 1) {
/*     */       
/* 327 */       par1World.notifyBlockOfNeighborChange(par2 + 1, par3, par4, this.blockID);
/* 328 */       par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID, 4);
/*     */     } 
/*     */     
/* 331 */     if (var5 == 3) {
/*     */       
/* 333 */       par1World.notifyBlockOfNeighborChange(par2 - 1, par3, par4, this.blockID);
/* 334 */       par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID, 5);
/*     */     } 
/*     */     
/* 337 */     if (var5 == 2) {
/*     */       
/* 339 */       par1World.notifyBlockOfNeighborChange(par2, par3, par4 + 1, this.blockID);
/* 340 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID, 2);
/*     */     } 
/*     */     
/* 343 */     if (var5 == 0) {
/*     */       
/* 345 */       par1World.notifyBlockOfNeighborChange(par2, par3, par4 - 1, this.blockID);
/* 346 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID, 3);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_94477_d(int par1) {
/* 381 */     Block var2 = Block.blocksList[par1];
/* 382 */     return (var2 != null && var2.canProvidePower());
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_94480_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 387 */     return 15;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isRedstoneRepeaterBlockID(int par0) {
/* 392 */     return (Block.redstoneRepeaterIdle.func_94487_f(par0) || Block.redstoneComparatorIdle.func_94487_f(par0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94487_f(int par1) {
/* 397 */     return (par1 == (func_94485_e()).blockID || par1 == (func_94484_i()).blockID);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_83011_d(World par1World, int par2, int par3, int par4, int par5) {
/* 402 */     int var6 = j(par5);
/*     */     
/* 404 */     if (isRedstoneRepeaterBlockID(par1World.getBlockId(par2 - Direction.offsetX[var6], par3, par4 - Direction.offsetZ[var6]))) {
/*     */       
/* 406 */       int var7 = par1World.getBlockMetadata(par2 - Direction.offsetX[var6], par3, par4 - Direction.offsetZ[var6]);
/* 407 */       int var8 = j(var7);
/* 408 */       return (var8 != var6);
/*     */     } 
/*     */ 
/*     */     
/* 412 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_94486_g(int par1) {
/* 418 */     return func_94481_j_(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int func_94481_j_(int paramInt);
/*     */ 
/*     */   
/*     */   protected abstract BlockRedstoneLogic func_94485_e();
/*     */ 
/*     */   
/*     */   protected abstract BlockRedstoneLogic func_94484_i();
/*     */ 
/*     */   
/*     */   public boolean isAssociatedBlockID(int par1) {
/* 433 */     return func_94487_f(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 438 */     if (this.isRepeaterPowered && info.getResponsiblePlayer() != null) {
/*     */       
/* 440 */       World world = info.world;
/* 441 */       int x = info.x;
/* 442 */       int y = info.y;
/* 443 */       int z = info.z;
/*     */       
/* 445 */       world.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID);
/* 446 */       world.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID);
/* 447 */       world.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID);
/* 448 */       world.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID);
/* 449 */       world.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID);
/* 450 */       world.notifyBlocksOfNeighborChange(x, y + 1, z, this.blockID);
/*     */     } 
/*     */     
/* 453 */     return super.dropBlockAsEntityItem(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 458 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 463 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRedstoneLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */