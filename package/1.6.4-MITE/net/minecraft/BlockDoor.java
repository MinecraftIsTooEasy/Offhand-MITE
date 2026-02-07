/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockDoor
/*     */   extends Block
/*     */   implements IBlockWithPartner
/*     */ {
/*     */   private Icon[] field_111044_a;
/*     */   private Icon[] field_111043_b;
/*     */   
/*     */   protected BlockDoor(int par1, Material par2Material) {
/*  14 */     super(par1, par2Material, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  15 */     float var3 = 0.5F;
/*  16 */     float var4 = 1.0F;
/*  17 */     setBlockBoundsForAllThreads((0.5F - var3), 0.0D, (0.5F - var3), (0.5F + var3), var4, (0.5F + var3));
/*     */     
/*  19 */     setHardnessRelativeToWood(0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  25 */     return "Lower half: Bits 1 and 2 used for orientation and bit 4 set if open. Upper half: Bit 1 set if hinge is reversed and bit 8 always set";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  30 */     return (metadata >= 0 && metadata < 10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  38 */     return this.field_111043_b[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  46 */     if (par5 != 1 && par5 != 0) {
/*     */       
/*  48 */       int var6 = getFullMetadata(par1IBlockAccess, par2, par3, par4);
/*  49 */       int var7 = var6 & 0x3;
/*  50 */       boolean var8 = ((var6 & 0x4) != 0);
/*  51 */       boolean var9 = false;
/*  52 */       boolean var10 = ((var6 & 0x8) != 0);
/*     */       
/*  54 */       if (var8) {
/*     */         
/*  56 */         if (var7 == 0 && par5 == 2)
/*     */         {
/*  58 */           var9 = !var9;
/*     */         }
/*  60 */         else if (var7 == 1 && par5 == 5)
/*     */         {
/*  62 */           var9 = !var9;
/*     */         }
/*  64 */         else if (var7 == 2 && par5 == 3)
/*     */         {
/*  66 */           var9 = !var9;
/*     */         }
/*  68 */         else if (var7 == 3 && par5 == 4)
/*     */         {
/*  70 */           var9 = !var9;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  75 */         if (var7 == 0 && par5 == 5) {
/*     */           
/*  77 */           var9 = !var9;
/*     */         }
/*  79 */         else if (var7 == 1 && par5 == 3) {
/*     */           
/*  81 */           var9 = !var9;
/*     */         }
/*  83 */         else if (var7 == 2 && par5 == 4) {
/*     */           
/*  85 */           var9 = !var9;
/*     */         }
/*  87 */         else if (var7 == 3 && par5 == 2) {
/*     */           
/*  89 */           var9 = !var9;
/*     */         } 
/*     */         
/*  92 */         if ((var6 & 0x10) != 0)
/*     */         {
/*  94 */           var9 = !var9;
/*     */         }
/*     */       } 
/*     */       
/*  98 */       return var10 ? this.field_111044_a[var9 ? 1 : 0] : this.field_111043_b[var9 ? 1 : 0];
/*     */     } 
/*     */ 
/*     */     
/* 102 */     return this.field_111043_b[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 112 */     this.field_111044_a = new Icon[2];
/* 113 */     this.field_111043_b = new Icon[2];
/* 114 */     this.field_111044_a[0] = par1IconRegister.registerIcon(getTextureName() + "_upper");
/* 115 */     this.field_111043_b[0] = par1IconRegister.registerIcon(getTextureName() + "_lower");
/* 116 */     this.field_111044_a[1] = new IconFlipped(this.field_111044_a[0], true, false);
/* 117 */     this.field_111043_b[1] = new IconFlipped(this.field_111043_b[0], true, false);
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
/*     */   public int getRenderType() {
/* 152 */     return 7;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 179 */     setDoorRotation(getFullMetadata(par1IBlockAccess, par2, par3, par4), false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDoorOrientation(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 187 */     return getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDoorOpen(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 192 */     return ((getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getIsOpenBit() {
/* 197 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isOpen(int metadata) {
/* 202 */     return ((metadata & getIsOpenBit()) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDoorRotation(int par1, boolean for_all_threads) {
/* 207 */     float var2 = 0.1875F;
/* 208 */     setBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D, for_all_threads);
/* 209 */     int var3 = par1 & 0x3;
/* 210 */     boolean var4 = ((par1 & 0x4) != 0);
/* 211 */     boolean var5 = ((par1 & 0x10) != 0);
/*     */     
/* 213 */     if (var3 == 0) {
/*     */       
/* 215 */       if (var4) {
/*     */         
/* 217 */         if (!var5)
/*     */         {
/* 219 */           setBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, var2, for_all_threads);
/*     */         }
/*     */         else
/*     */         {
/* 223 */           setBlockBounds(0.0D, 0.0D, (1.0F - var2), 1.0D, 1.0D, 1.0D, for_all_threads);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 228 */         setBlockBounds(0.0D, 0.0D, 0.0D, var2, 1.0D, 1.0D, for_all_threads);
/*     */       }
/*     */     
/* 231 */     } else if (var3 == 1) {
/*     */       
/* 233 */       if (var4) {
/*     */         
/* 235 */         if (!var5)
/*     */         {
/* 237 */           setBlockBounds((1.0F - var2), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D, for_all_threads);
/*     */         }
/*     */         else
/*     */         {
/* 241 */           setBlockBounds(0.0D, 0.0D, 0.0D, var2, 1.0D, 1.0D, for_all_threads);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 246 */         setBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, var2, for_all_threads);
/*     */       }
/*     */     
/* 249 */     } else if (var3 == 2) {
/*     */       
/* 251 */       if (var4) {
/*     */         
/* 253 */         if (!var5)
/*     */         {
/* 255 */           setBlockBounds(0.0D, 0.0D, (1.0F - var2), 1.0D, 1.0D, 1.0D, for_all_threads);
/*     */         }
/*     */         else
/*     */         {
/* 259 */           setBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, var2, for_all_threads);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 264 */         setBlockBounds((1.0F - var2), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D, for_all_threads);
/*     */       }
/*     */     
/* 267 */     } else if (var3 == 3) {
/*     */       
/* 269 */       if (var4) {
/*     */         
/* 271 */         if (!var5)
/*     */         {
/* 273 */           setBlockBounds(0.0D, 0.0D, 0.0D, var2, 1.0D, 1.0D, for_all_threads);
/*     */         }
/*     */         else
/*     */         {
/* 277 */           setBlockBounds((1.0F - var2), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D, for_all_threads);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 282 */         setBlockBounds(0.0D, 0.0D, (1.0F - var2), 1.0D, 1.0D, 1.0D, for_all_threads);
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
/*     */   public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void makeOpenOrCloseSound(World world, int x, int y, int z, int metadata_after) {
/* 325 */     if (isOpen(metadata_after)) {
/* 326 */       world.playSoundAtBlock(x, y, z, "random.door_open", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
/*     */     } else {
/* 328 */       world.playSoundAtBlock(x, y, z, "random.door_close", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 333 */     if (this.blockMaterial != Material.wood) {
/* 334 */       return false;
/*     */     }
/* 336 */     if (player.onServer()) {
/*     */       
/* 338 */       int var10 = getFullMetadata(world, x, y, z);
/* 339 */       int var11 = var10 & 0x7;
/* 340 */       var11 ^= 0x4;
/*     */       
/* 342 */       if ((var10 & 0x8) == 0) {
/*     */         
/* 344 */         world.setBlockMetadataWithNotify(x, y, z, var11, 2);
/* 345 */         world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
/*     */       }
/*     */       else {
/*     */         
/* 349 */         world.setBlockMetadataWithNotify(x, y - 1, z, var11, 2);
/* 350 */         world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
/*     */       } 
/*     */       
/* 353 */       makeOpenOrCloseSound(world, x, y, z, var11);
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
/*     */     
/* 367 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean par5) {
/* 376 */     int var6 = getFullMetadata(par1World, par2, par3, par4);
/* 377 */     boolean var7 = ((var6 & 0x4) != 0);
/*     */     
/* 379 */     if (var7 != par5) {
/*     */       
/* 381 */       int var8 = var6 & 0x7;
/* 382 */       var8 ^= 0x4;
/*     */       
/* 384 */       if ((var6 & 0x8) == 0) {
/*     */         
/* 386 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, var8, 2);
/* 387 */         par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
/*     */       }
/*     */       else {
/*     */         
/* 391 */         par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, var8, 2);
/* 392 */         par1World.markBlockRangeForRenderUpdate(par2, par3 - 1, par4, par2, par3, par4);
/*     */       } 
/*     */       
/* 395 */       makeOpenOrCloseSound(par1World, par2, par3, par4, var8);
/*     */ 
/*     */ 
/*     */       
/* 399 */       return true;
/*     */     } 
/*     */     
/* 402 */     return false;
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
/*     */   public static boolean isTopHalf(int metadata) {
/* 468 */     return ((metadata & 0x8) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBottomHalf(int metadata) {
/* 473 */     return !isTopHalf(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 478 */     if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
/* 479 */       return true;
/*     */     }
/* 481 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 483 */     if (isBottomHalf(metadata)) {
/*     */       
/* 485 */       boolean is_indirectly_powered = (world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z));
/*     */       
/* 487 */       if ((is_indirectly_powered || (neighbor_block_id > 0 && Block.blocksList[neighbor_block_id].canProvidePower())) && neighbor_block_id != this.blockID) {
/* 488 */         return onPoweredBlockChange(world, x, y, z, is_indirectly_powered);
/*     */       
/*     */       }
/*     */     }
/* 492 */     else if (neighbor_block_id > 0 && neighbor_block_id != this.blockID) {
/* 493 */       onNeighborBlockChange(world, x, y - 1, z, neighbor_block_id);
/*     */     } 
/*     */     
/* 496 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 501 */     if (!super.isLegalAt(world, x, y, z, metadata)) {
/* 502 */       return false;
/*     */     }
/* 504 */     if (!this.is_being_placed && isBottomHalf(metadata))
/*     */     {
/* 506 */       if (world.getBlock(x, y + 1, z) != this) {
/* 507 */         return false;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 512 */     if (this.is_being_placed && isTopHalf(metadata)) {
/* 513 */       return true;
/*     */     }
/* 515 */     EnumDirection direction_facing = getDirectionFacing(isBottomHalf(metadata) ? metadata : world.getBlockMetadata(x, y - 1, z));
/*     */     
/* 517 */     boolean is_double_door = isTopHalf(metadata) ? isDoubleDoor(metadata) : isDoubleDoor(world, x, y + 1, z);
/*     */     
/* 519 */     if (is_double_door) {
/* 520 */       direction_facing = direction_facing.getOpposite();
/*     */     }
/* 522 */     if (direction_facing.isWest()) {
/* 523 */       z--;
/* 524 */     } else if (direction_facing.isNorth()) {
/* 525 */       x++;
/* 526 */     } else if (direction_facing.isEast()) {
/* 527 */       z++;
/*     */     } else {
/* 529 */       x--;
/*     */     } 
/* 531 */     if (!is_double_door && world.getBlock(x, y, z) == this && !isDoubleDoor(world, x, y, z) && getDirectionFacing(world, x, y, z) == direction_facing) {
/*     */       
/* 533 */       direction_facing = direction_facing.getOpposite();
/*     */       
/* 535 */       if (direction_facing.isWest()) {
/* 536 */         z -= 2;
/* 537 */       } else if (direction_facing.isNorth()) {
/* 538 */         x += 2;
/* 539 */       } else if (direction_facing.isEast()) {
/* 540 */         z += 2;
/*     */       } else {
/* 542 */         x -= 2;
/*     */       } 
/*     */     } 
/* 545 */     if (!world.isBlockFaceFlatAndSolid(x, y, z, direction_facing.isWest() ? EnumFace.SOUTH : (direction_facing.isNorth() ? EnumFace.WEST : (direction_facing.isEast() ? EnumFace.NORTH : EnumFace.EAST))) || !isSuitableMaterialForAttachingHingesTo(world.getBlockMaterial(x, y, z))) {
/* 546 */       return false;
/*     */     }
/* 548 */     if (isBottomHalf(metadata))
/*     */     {
/* 550 */       if (!world.isBlockFaceFlatAndSolid(x, y + 1, z, direction_facing.isWest() ? EnumFace.SOUTH : (direction_facing.isNorth() ? EnumFace.WEST : (direction_facing.isEast() ? EnumFace.NORTH : EnumFace.EAST))) || !isSuitableMaterialForAttachingHingesTo(world.getBlockMaterial(x, y + 1, z))) {
/* 551 */         return false;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 556 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSuitableMaterialForAttachingHingesTo(Material material) {
/* 561 */     if (material == Material.dirt || material == Material.grass || material == Material.sand || material == Material.clay || material == Material.tree_leaves || material == Material.craftedSnow || material == Material.cloth || material == Material.pumpkin || material == Material.sponge || material == Material.glass) {
/* 562 */       return false;
/*     */     }
/* 564 */     return true;
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
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 586 */     if (isBottomHalf(metadata)) {
/* 587 */       return super.isLegalOn(metadata, block_below, block_below_metadata);
/*     */     }
/*     */     
/* 590 */     if (this.is_being_placed && (block_below == null || block_below.canBeReplacedBy(block_below_metadata, this, metadata))) {
/* 591 */       return true;
/*     */     }
/* 593 */     return (block_below == this && isBottomHalf(block_below_metadata));
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
/*     */   public int getMobilityFlag() {
/* 630 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFullMetadata(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 638 */     int var7, var8, var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/* 639 */     boolean var6 = ((var5 & 0x8) != 0);
/*     */ 
/*     */ 
/*     */     
/* 643 */     if (var6) {
/*     */       
/* 645 */       var7 = par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4);
/* 646 */       var8 = var5;
/*     */     }
/*     */     else {
/*     */       
/* 650 */       var7 = var5;
/* 651 */       var8 = par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4);
/*     */     } 
/*     */     
/* 654 */     boolean var9 = ((var8 & 0x1) != 0);
/* 655 */     return var7 & 0x7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 663 */     return (this.blockMaterial == Material.iron) ? Item.doorIron.itemID : Item.doorWood.itemID;
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
/*     */   public void onBlockAboutToBeBroken(BlockBreakInfo info) {
/* 679 */     if (!isTopHalf(info.getMetadata()) || info.world.getBlockId(info.x, info.y - 1, info.z) != this.blockID) {
/*     */       return;
/*     */     }
/* 682 */     if (info.isResponsiblePlayerInCreativeMode()) {
/*     */       
/* 684 */       info.world.setBlockToAir(info.x, info.y - 1, info.z);
/*     */     }
/* 686 */     else if (info.wasExploded()) {
/*     */       
/* 688 */       dropBlockAsEntityItem((new BlockBreakInfo(info.world, info.x, info.y - 1, info.z)).setExploded(info.explosion));
/* 689 */       info.world.setBlockToAir(info.x, info.y - 1, info.z);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean partnerDropsAsItem(int metadata) {
/* 695 */     return isTopHalf(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDoorItem() {
/* 700 */     if (this == Block.doorWood)
/* 701 */       return Item.doorWood; 
/* 702 */     if (this == Block.doorCopper)
/* 703 */       return Item.doorCopper; 
/* 704 */     if (this == Block.doorSilver)
/* 705 */       return Item.doorSilver; 
/* 706 */     if (this == Block.doorGold)
/* 707 */       return Item.doorGold; 
/* 708 */     if (this == Block.doorIron)
/* 709 */       return Item.doorIron; 
/* 710 */     if (this == Block.doorMithril)
/* 711 */       return Item.doorMithril; 
/* 712 */     if (this == Block.doorAdamantium)
/* 713 */       return Item.doorAdamantium; 
/* 714 */     if (this == Block.doorAncientMetal) {
/* 715 */       return Item.doorAncientMetal;
/*     */     }
/* 717 */     Minecraft.setErrorMessage("getDoorItem: unhandled door type " + this);
/* 718 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 723 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 728 */     if (isTopHalf(info.getMetadata())) {
/* 729 */       return 0;
/*     */     }
/* 731 */     if (info.wasExploded() && this.blockMaterial == Material.wood) {
/* 732 */       return dropBlockAsEntityItem(info, Item.stick);
/*     */     }
/* 734 */     return dropBlockAsEntityItem(info, getDoorItem());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isDoubleDoor(int metadata) {
/* 740 */     if (!isTopHalf(metadata)) {
/*     */       
/* 742 */       Minecraft.setErrorMessage("isDoubleDoor: must be top half of door");
/* 743 */       (new Exception()).printStackTrace();
/* 744 */       return false;
/*     */     } 
/* 746 */     return BitHelper.isBitSet(metadata, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isDoubleDoor(World world, int x, int y, int z) {
/* 751 */     if (world.getBlock(x, y, z) != this) {
/* 752 */       return false;
/*     */     }
/* 754 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 756 */     if (isBottomHalf(metadata))
/*     */     {
/* 758 */       return isDoubleDoor(world, x, y + 1, z);
/*     */     }
/* 760 */     return isDoubleDoor(metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean shouldBeDoubleDoor(World world, int x, int y, int z) {
/* 766 */     if (world.getBlock(x, y - 1, z) != this) {
/*     */       
/* 768 */       Minecraft.setErrorMessage("shouldBeDoubleDoor: top had no bottom below it");
/* 769 */       return false;
/*     */     } 
/*     */     
/* 772 */     int direction = world.getBlockMetadata(x, y - 1, z) & 0x3;
/*     */     
/* 774 */     if (direction == 0) {
/*     */       
/* 776 */       if (world.getBlock(x, y, z - 1) == this && (world.getBlockMetadata(x, y - 1, z - 1) & 0x3) == direction && world.getBlockMetadata(x, y, z - 1) == 8) {
/* 777 */         return true;
/*     */       }
/* 779 */     } else if (direction == 1) {
/*     */       
/* 781 */       if (world.getBlock(x + 1, y, z) == this && (world.getBlockMetadata(x + 1, y - 1, z) & 0x3) == direction && world.getBlockMetadata(x + 1, y, z) == 8) {
/* 782 */         return true;
/*     */       }
/* 784 */     } else if (direction == 2) {
/*     */       
/* 786 */       if (world.getBlock(x, y, z + 1) == this && (world.getBlockMetadata(x, y - 1, z + 1) & 0x3) == direction && world.getBlockMetadata(x, y, z + 1) == 8) {
/* 787 */         return true;
/*     */       }
/* 789 */     } else if (direction == 3) {
/*     */       
/* 791 */       if (world.getBlock(x - 1, y, z) == this && (world.getBlockMetadata(x - 1, y - 1, z) & 0x3) == direction && world.getBlockMetadata(x - 1, y, z) == 8) {
/* 792 */         return true;
/*     */       }
/*     */     } 
/* 795 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePlacedAt(World world, int x, int y, int z, int metadata) {
/* 800 */     return (world.getBlock(x, y, z) != Block.torchRedstoneActive && super.canBePlacedAt(world, x, y, z, metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 805 */     if (isTopHalf(metadata)) {
/* 806 */       return true;
/*     */     }
/* 808 */     if (!canBePlacedAt(world, x, y + 1, z, 8)) {
/* 809 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 844 */     int saved_metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 846 */     world.setBlockMetadataWithNotify(x, y, z, metadata, 0);
/*     */     
/* 848 */     boolean result = tryPlaceBlock(world, x, y + 1, z, EnumFace.TOP, (test_only || !shouldBeDoubleDoor(world, x, y + 1, z)) ? 8 : 9, placer, false, true, test_only);
/*     */     
/* 850 */     world.setBlockMetadataWithNotify(x, y, z, saved_metadata, 0);
/*     */     
/* 852 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*     */     int metadata;
/* 861 */     EnumDirection direction = entity.getDirectionFromYaw();
/*     */     
/* 863 */     if (direction.isEast()) {
/* 864 */       metadata = 0;
/* 865 */     } else if (direction.isSouth()) {
/* 866 */       metadata = 1;
/* 867 */     } else if (direction.isWest()) {
/* 868 */       metadata = 2;
/*     */     } else {
/* 870 */       metadata = 3;
/*     */     } 
/* 872 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlockSuitableDoorFrame(World world, int x, int y, int z) {
/* 877 */     Block block = world.getBlock(x, y, z);
/*     */     
/* 879 */     if (block == null) {
/* 880 */       return false;
/*     */     }
/* 882 */     if (block == this) {
/* 883 */       return true;
/*     */     }
/* 885 */     return block.isSolidStandardFormCube(world.getBlockMetadata(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultMetadata(World world, int x, int y, int z) {
/* 890 */     if (world.getBlock(x, y - 1, z) == this) {
/* 891 */       return shouldBeDoubleDoor(world, x, y, z) ? 9 : 8;
/*     */     }
/* 893 */     if (isBlockSuitableDoorFrame(world, x - 1, y, z) && isBlockSuitableDoorFrame(world, x + 1, y, z))
/* 894 */       return 1; 
/* 895 */     if (isBlockSuitableDoorFrame(world, x, y, z - 1) && isBlockSuitableDoorFrame(world, x, y, z + 1)) {
/* 896 */       return 2;
/*     */     }
/* 898 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDirection getDirectionFacing(int metadata) {
/* 903 */     int direction = metadata & 0x3;
/*     */     
/* 905 */     return (direction == 0) ? EnumDirection.WEST : ((direction == 1) ? EnumDirection.NORTH : ((direction == 2) ? EnumDirection.EAST : EnumDirection.SOUTH));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumDirection getDirectionFacing(World world, int x, int y, int z) {
/* 911 */     if (world.getBlock(x, y, z) != this) {
/* 912 */       return null;
/*     */     }
/* 914 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 916 */     if (isTopHalf(metadata)) {
/* 917 */       return getDirectionFacing(world, x, y - 1, z);
/*     */     }
/* 919 */     return getDirectionFacing(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 924 */     if (isBottomHalf(metadata)) {
/*     */       
/* 926 */       metadata &= 0xFFFFFFFC;
/* 927 */       metadata |= direction.isWest() ? 0 : (direction.isNorth() ? 1 : (direction.isEast() ? 2 : (direction.isSouth() ? 3 : -1)));
/*     */     } 
/*     */     
/* 930 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPortal() {
/* 935 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpenPortal(World world, int x, int y, int z) {
/* 940 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 942 */     if (isTopHalf(metadata)) {
/*     */       
/* 944 */       Block block = world.getBlock(x, y - 1, z);
/*     */       
/* 946 */       return (block == null) ? false : block.isOpenPortal(world, x, y - 1, z);
/*     */     } 
/*     */     
/* 949 */     return isOpen(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 954 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetX(int metadata) {
/* 959 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetY(int metadata) {
/* 964 */     return isTopHalf(metadata) ? -1 : 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetZ(int metadata) {
/* 969 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresPartner(int metadata) {
/* 974 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartner(int metadata, Block neighbor_block, int neighbor_block_metadata) {
/* 979 */     return (neighbor_block == this && isTopHalf(neighbor_block_metadata) != isTopHalf(metadata));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */