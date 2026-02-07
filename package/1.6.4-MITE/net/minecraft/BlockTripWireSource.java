/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTripWireSource
/*     */   extends BlockMounted
/*     */ {
/*     */   public BlockTripWireSource(int par1) {
/*  11 */     super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  12 */     setCreativeTab(CreativeTabs.tabRedstone);
/*  13 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  18 */     return "Bits 1 and 2 used for orientation, bit 4 set if attached to another hook via wire (ready to be triggered), and bit 8 set if triggered (entity pulled wire)";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  23 */     return (metadata >= 0 && metadata < 16);
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
/*  57 */     return 29;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int tickRate(World par1World) {
/*  66 */     return 10;
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
/* 125 */     if (!test_only) {
/* 126 */       func_72143_a(world, x, y, z, this.blockID, metadata, false, -1, 0);
/*     */     }
/* 128 */     return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumFace getFaceMountedTo(int metadata) {
/* 185 */     int orientation = metadata & 0x3;
/*     */     
/* 187 */     if (orientation == 0)
/* 188 */       return EnumFace.SOUTH; 
/* 189 */     if (orientation == 1)
/* 190 */       return EnumFace.WEST; 
/* 191 */     if (orientation == 2) {
/* 192 */       return EnumFace.NORTH;
/*     */     }
/* 194 */     return EnumFace.EAST;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getDefaultMetadataForFaceMountedTo(EnumFace face) {
/* 199 */     if (face.isSouth())
/* 200 */       return 0; 
/* 201 */     if (face.isWest())
/* 202 */       return 1; 
/* 203 */     if (face.isNorth())
/* 204 */       return 2; 
/* 205 */     if (face.isEast()) {
/* 206 */       return 3;
/*     */     }
/* 208 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_72143_a(World par1World, int par2, int par3, int par4, int par5, int par6, boolean par7, int par8, int par9) {
/*     */     int i, j;
/* 214 */     boolean changed_state = false;
/*     */     
/* 216 */     int var10 = par6 & 0x3;
/* 217 */     int var11 = ((par6 & 0x4) == 4) ? 1 : 0;
/* 218 */     boolean var12 = ((par6 & 0x8) == 8);
/* 219 */     boolean var13 = (par5 == Block.tripWireSource.blockID);
/* 220 */     boolean var14 = false;
/* 221 */     boolean var15 = !par1World.isBlockTopFlatAndSolid(par2, par3 - 1, par4);
/* 222 */     int var16 = Direction.offsetX[var10];
/* 223 */     int var17 = Direction.offsetZ[var10];
/* 224 */     int var18 = 0;
/* 225 */     int[] var19 = new int[42];
/*     */ 
/*     */ 
/*     */     
/*     */     int var20;
/*     */ 
/*     */     
/* 232 */     for (var20 = 1; var20 < 42; var20++) {
/*     */       
/* 234 */       int var21 = par2 + var16 * var20;
/* 235 */       int var22 = par4 + var17 * var20;
/* 236 */       int var23 = par1World.getBlockId(var21, par3, var22);
/*     */       
/* 238 */       if (var23 == Block.tripWireSource.blockID) {
/*     */         
/* 240 */         int var24 = par1World.getBlockMetadata(var21, par3, var22);
/*     */         
/* 242 */         if ((var24 & 0x3) == Direction.rotateOpposite[var10])
/*     */         {
/* 244 */           var18 = var20;
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 250 */       if (var23 != Block.tripWire.blockID && var20 != par8) {
/*     */         
/* 252 */         var19[var20] = -1;
/* 253 */         var13 = false;
/*     */       }
/*     */       else {
/*     */         
/* 257 */         int var24 = (var20 == par8) ? par9 : par1World.getBlockMetadata(var21, par3, var22);
/* 258 */         int var25 = ((var24 & 0x8) != 8) ? 1 : 0;
/* 259 */         boolean var26 = ((var24 & 0x1) == 1);
/* 260 */         boolean var27 = ((var24 & 0x2) == 2);
/* 261 */         int k = var13 & ((var27 == var15) ? 1 : 0);
/* 262 */         j = var14 | ((var25 && var26) ? 1 : 0);
/* 263 */         var19[var20] = var24;
/*     */         
/* 265 */         if (var20 == par8) {
/*     */           
/* 267 */           par1World.scheduleBlockUpdate(par2, par3, par4, par5, tickRate(par1World));
/* 268 */           i = k & var25;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 273 */     i &= (var18 > 1) ? 1 : 0;
/* 274 */     j &= i;
/* 275 */     var20 = ((i != 0) ? 4 : 0) | ((j != 0) ? 8 : 0);
/* 276 */     par6 = var10 | var20;
/*     */     
/* 278 */     if (var18 > 0) {
/*     */       
/* 280 */       int var21 = par2 + var16 * var18;
/* 281 */       int var22 = par4 + var17 * var18;
/* 282 */       int var23 = Direction.rotateOpposite[var10];
/* 283 */       par1World.setBlockMetadataWithNotify(var21, par3, var22, var23 | var20, 3);
/* 284 */       notifyNeighborOfChange(par1World, var21, par3, var22, var23);
/* 285 */       playSoundEffect(par1World, var21, par3, var22, i, j, var11, var12);
/*     */     } 
/*     */     
/* 288 */     playSoundEffect(par1World, par2, par3, par4, i, j, var11, var12);
/*     */     
/* 290 */     if (par5 > 0) {
/*     */       
/* 292 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, par6, 3);
/*     */       
/* 294 */       changed_state = true;
/*     */       
/* 296 */       if (par7)
/*     */       {
/* 298 */         notifyNeighborOfChange(par1World, par2, par3, par4, var10);
/*     */       }
/*     */     } 
/*     */     
/* 302 */     if (var11 != i)
/*     */     {
/* 304 */       for (int var21 = 1; var21 < var18; var21++) {
/*     */         
/* 306 */         int var22 = par2 + var16 * var21;
/* 307 */         int var23 = par4 + var17 * var21;
/* 308 */         int var24 = var19[var21];
/*     */         
/* 310 */         if (var24 >= 0) {
/*     */           
/* 312 */           if (i != 0) {
/*     */             
/* 314 */             var24 |= 0x4;
/*     */           }
/*     */           else {
/*     */             
/* 318 */             var24 &= 0xFFFFFFFB;
/*     */           } 
/*     */           
/* 321 */           par1World.setBlockMetadataWithNotify(var22, par3, var23, var24, 3);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 326 */     return changed_state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 335 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/* 336 */       return true;
/*     */     }
/* 338 */     return func_72143_a(par1World, par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4), true, -1, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSoundEffect(World par1World, int par2, int par3, int par4, boolean par5, boolean par6, boolean par7, boolean par8) {
/* 346 */     if (par6 && !par8) {
/*     */       
/* 348 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.1D, par4 + 0.5D, "random.click", 0.4F, 0.6F);
/*     */     }
/* 350 */     else if (!par6 && par8) {
/*     */       
/* 352 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.1D, par4 + 0.5D, "random.click", 0.4F, 0.5F);
/*     */     }
/* 354 */     else if (par5 && !par7) {
/*     */       
/* 356 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.1D, par4 + 0.5D, "random.click", 0.4F, 0.7F);
/*     */     }
/* 358 */     else if (!par5 && par7) {
/*     */       
/* 360 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.1D, par4 + 0.5D, "random.bowhit", 0.4F, 1.2F / (par1World.rand.nextFloat() * 0.2F + 0.9F));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void notifyNeighborOfChange(World par1World, int par2, int par3, int par4, int par5) {
/* 366 */     par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
/*     */     
/* 368 */     if (par5 == 3) {
/*     */       
/* 370 */       par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
/*     */     }
/* 372 */     else if (par5 == 1) {
/*     */       
/* 374 */       par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
/*     */     }
/* 376 */     else if (par5 == 0) {
/*     */       
/* 378 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
/*     */     }
/* 380 */     else if (par5 == 2) {
/*     */       
/* 382 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 406 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x3;
/* 407 */     float var6 = 0.1875F;
/*     */     
/* 409 */     if (var5 == 3) {
/*     */       
/* 411 */       setBlockBoundsForCurrentThread(0.0D, 0.20000000298023224D, (0.5F - var6), (var6 * 2.0F), 0.800000011920929D, (0.5F + var6));
/*     */     }
/* 413 */     else if (var5 == 1) {
/*     */       
/* 415 */       setBlockBoundsForCurrentThread((1.0F - var6 * 2.0F), 0.20000000298023224D, (0.5F - var6), 1.0D, 0.800000011920929D, (0.5F + var6));
/*     */     }
/* 417 */     else if (var5 == 0) {
/*     */       
/* 419 */       setBlockBoundsForCurrentThread((0.5F - var6), 0.20000000298023224D, 0.0D, (0.5F + var6), 0.800000011920929D, (var6 * 2.0F));
/*     */     }
/* 421 */     else if (var5 == 2) {
/*     */       
/* 423 */       setBlockBoundsForCurrentThread((0.5F - var6), 0.20000000298023224D, (1.0F - var6 * 2.0F), (0.5F + var6), 0.800000011920929D, 1.0D);
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
/* 434 */     boolean var7 = ((par6 & 0x4) == 4);
/* 435 */     boolean var8 = ((par6 & 0x8) == 8);
/*     */     
/* 437 */     if (var7 || var8)
/*     */     {
/* 439 */       func_72143_a(par1World, par2, par3, par4, 0, par6, false, -1, 0);
/*     */     }
/*     */     
/* 442 */     if (var8) {
/*     */       
/* 444 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
/* 445 */       int var9 = par6 & 0x3;
/*     */       
/* 447 */       if (var9 == 3) {
/*     */         
/* 449 */         par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
/*     */       }
/* 451 */       else if (var9 == 1) {
/*     */         
/* 453 */         par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
/*     */       }
/* 455 */       else if (var9 == 0) {
/*     */         
/* 457 */         par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
/*     */       }
/* 459 */       else if (var9 == 2) {
/*     */         
/* 461 */         par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
/*     */       } 
/*     */     } 
/*     */     
/* 465 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 475 */     return ((par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x8) == 8) ? 15 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 484 */     int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */     
/* 486 */     if ((var6 & 0x8) != 8)
/*     */     {
/* 488 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 492 */     int var7 = var6 & 0x3;
/* 493 */     return (var7 == 2 && par5 == 2) ? 15 : ((var7 == 0 && par5 == 3) ? 15 : ((var7 == 1 && par5 == 4) ? 15 : ((var7 == 3 && par5 == 5) ? 15 : 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 502 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 507 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMountToBlock(int metadata, Block neighbor_block, int neighbor_block_metadata, EnumFace face) {
/* 512 */     if (!BlockLever.isMaterialSuitableForLeverMounting(neighbor_block.blockMaterial, face)) {
/* 513 */       return false;
/*     */     }
/* 515 */     return super.canMountToBlock(metadata, neighbor_block, neighbor_block_metadata, face);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 520 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 525 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockTripWireSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */