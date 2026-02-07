/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTorch
/*     */   extends BlockMounted
/*     */ {
/*     */   protected BlockTorch(int par1) {
/*  11 */     super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  12 */     setTickRandomly(true);
/*  13 */     setMaxStackSize(16);
/*  14 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  19 */     String[] array = new String[5];
/*     */     
/*  21 */     for (int i = 0; i < array.length; i++) {
/*  22 */       array[i] = (i + 1) + "=Mounted " + getDirectionOfSupportBlock(i + 1).getDescriptor(true);
/*     */     }
/*  24 */     return StringHelper.implode(array, ", ", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  29 */     return (metadata > 0 && metadata < 6);
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
/*     */   public final int getRenderType() {
/*  68 */     return 2;
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
/*     */ 
/*     */   
/*     */   public EnumFace getFaceMountedTo(int metadata) {
/* 129 */     if (metadata == 1)
/* 130 */       return EnumFace.EAST; 
/* 131 */     if (metadata == 2)
/* 132 */       return EnumFace.WEST; 
/* 133 */     if (metadata == 3)
/* 134 */       return EnumFace.SOUTH; 
/* 135 */     if (metadata == 4)
/* 136 */       return EnumFace.NORTH; 
/* 137 */     if (metadata == 5) {
/* 138 */       return EnumFace.TOP;
/*     */     }
/* 140 */     Minecraft.setErrorMessage("getFaceMountedTo: unexpected metadata " + metadata);
/* 141 */     return null;
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
/*     */   public final int getDefaultMetadataForFaceMountedTo(EnumFace face) {
/* 181 */     if (face.isEast())
/* 182 */       return 1; 
/* 183 */     if (face.isWest())
/* 184 */       return 2; 
/* 185 */     if (face.isSouth())
/* 186 */       return 3; 
/* 187 */     if (face.isNorth())
/* 188 */       return 4; 
/* 189 */     if (face.isTop()) {
/* 190 */       return 5;
/*     */     }
/* 192 */     return -1;
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 210 */     if (super.updateTick(world, x, y, z, random)) {
/* 211 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 361 */     int var7 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x7;
/* 362 */     float var8 = 0.15F;
/*     */     
/* 364 */     if (var7 == 1) {
/*     */       
/* 366 */       setBlockBoundsForCurrentThread(0.0D, 0.20000000298023224D, (0.5F - var8), (var8 * 2.0F), 0.800000011920929D, (0.5F + var8));
/*     */     }
/* 368 */     else if (var7 == 2) {
/*     */       
/* 370 */       setBlockBoundsForCurrentThread((1.0F - var8 * 2.0F), 0.20000000298023224D, (0.5F - var8), 1.0D, 0.800000011920929D, (0.5F + var8));
/*     */     }
/* 372 */     else if (var7 == 3) {
/*     */       
/* 374 */       setBlockBoundsForCurrentThread((0.5F - var8), 0.20000000298023224D, 0.0D, (0.5F + var8), 0.800000011920929D, (var8 * 2.0F));
/*     */     }
/* 376 */     else if (var7 == 4) {
/*     */       
/* 378 */       setBlockBoundsForCurrentThread((0.5F - var8), 0.20000000298023224D, (1.0F - var8 * 2.0F), (0.5F + var8), 0.800000011920929D, 1.0D);
/*     */     }
/*     */     else {
/*     */       
/* 382 */       var8 = 0.1F;
/* 383 */       setBlockBoundsForCurrentThread((0.5F - var8), 0.0D, (0.5F - var8), (0.5F + var8), 0.6000000238418579D, (0.5F + var8));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 426 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 432 */     double var7 = par2 + 0.5D;
/* 433 */     double var9 = par3 + 0.7D;
/* 434 */     double var11 = par4 + 0.5D;
/*     */     
/* 436 */     double var13 = 0.2199999988079071D;
/* 437 */     double var15 = 0.27000001072883606D;
/*     */     
/* 439 */     if (var6 == 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 444 */       par1World.spawnParticle(EnumParticle.smoke, var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
/* 445 */       par1World.spawnParticle(EnumParticle.flame, var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
/*     */     }
/* 447 */     else if (var6 == 2) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 452 */       par1World.spawnParticle(EnumParticle.smoke, var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
/* 453 */       par1World.spawnParticle(EnumParticle.flame, var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
/*     */     }
/* 455 */     else if (var6 == 3) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 460 */       par1World.spawnParticle(EnumParticle.smoke, var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
/* 461 */       par1World.spawnParticle(EnumParticle.flame, var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
/*     */     }
/* 463 */     else if (var6 == 4) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 468 */       par1World.spawnParticle(EnumParticle.smoke, var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
/* 469 */       par1World.spawnParticle(EnumParticle.flame, var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 476 */       par1World.spawnParticle(EnumParticle.smoke, var7, var9, var11, 0.0D, 0.0D, 0.0D);
/* 477 */       par1World.spawnParticle(EnumParticle.flame, var7, var9, var11, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 483 */     return info.wasExploded() ? 0 : super.dropBlockAsEntityItem(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 488 */     item_block.addMaterial(new Material[] { Material.wood, Material.coal });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMountToBlock(int metadata, Block neighbor_block, int neighbor_block_metadata, EnumFace face) {
/* 493 */     if (face.isTop()) {
/*     */       
/* 495 */       if (neighbor_block == fence || neighbor_block == netherFence || neighbor_block == cobblestoneWall) {
/* 496 */         return true;
/*     */       }
/* 498 */       if (neighbor_block != leaves && !(neighbor_block instanceof BlockDirectional) && neighbor_block.isFaceFlatAndSolid(neighbor_block_metadata, face)) {
/* 499 */         return true;
/*     */       }
/* 501 */     } else if (face.isSide()) {
/*     */       
/* 503 */       if (neighbor_block == snow && neighbor_block_metadata > 2)
/*     */       {
/* 505 */         return true;
/*     */       }
/*     */       
/* 508 */       if (neighbor_block.isSingleSlab()) {
/*     */         
/* 510 */         if (BlockSlab.isBottom(neighbor_block_metadata)) {
/* 511 */           return true;
/*     */         }
/* 513 */       } else if (neighbor_block instanceof BlockStairs) {
/*     */         
/* 515 */         if ((neighbor_block_metadata & 0x4) == 0) {
/* 516 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 520 */     if (neighbor_block == cloth) {
/* 521 */       return true;
/*     */     }
/* 523 */     if ((neighbor_block instanceof BlockPistonBase || neighbor_block instanceof BlockPistonMoving || neighbor_block instanceof BlockPistonExtension) && face == Block.pistonBase.getFrontFace(neighbor_block_metadata)) {
/* 524 */       return true;
/*     */     }
/* 526 */     return super.canMountToBlock(metadata, neighbor_block, neighbor_block_metadata, face);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 536 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 541 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockTorch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */