/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTrapDoor
/*     */   extends BlockMounted
/*     */ {
/*     */   private static final int hinge_side_bits = 3;
/*     */   private static final int open_bit = 4;
/*     */   private static final int height_bit = 8;
/*     */   
/*     */   protected BlockTrapDoor(int par1, Material par2Material) {
/*  13 */     super(par1, par2Material, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  14 */     float var3 = 0.5F;
/*  15 */     float var4 = 1.0F;
/*  16 */     setBlockBoundsForAllThreads((0.5F - var3), 0.0D, (0.5F - var3), (0.5F + var3), var4, (0.5F + var3));
/*  17 */     setHardness(BlockHardness.planks);
/*  18 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  23 */     String[] array = new String[4];
/*     */     
/*  25 */     for (int i = 0; i < array.length; i++) {
/*  26 */       array[i] = i + "=Mounted " + getDirectionOfSupportBlock(i).getDescriptor(true);
/*     */     }
/*  28 */     return StringHelper.implode(array, ", ", true, false) + ", bit 4 set if open, and bit 8 set if upper";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  33 */     return (metadata >= 0 && metadata < 16);
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
/*     */   public int getRenderType() {
/*  66 */     return 0;
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
/*  93 */     setBlockBoundsForBlockRender(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/* 102 */     float var1 = 0.1875F;
/* 103 */     setBlockBoundsForCurrentThread(0.0D, (0.5F - var1 / 2.0F), 0.0D, 1.0D, (0.5F + var1 / 2.0F), 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForBlockRender(int par1) {
/* 108 */     float var2 = 0.1875F;
/*     */     
/* 110 */     if ((par1 & 0x8) != 0) {
/*     */       
/* 112 */       setBlockBoundsForCurrentThread(0.0D, (1.0F - var2), 0.0D, 1.0D, 1.0D, 1.0D);
/*     */     }
/*     */     else {
/*     */       
/* 116 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, var2, 1.0D);
/*     */     } 
/*     */     
/* 119 */     if (isTrapdoorOpen(par1)) {
/*     */       
/* 121 */       if ((par1 & 0x3) == 0)
/*     */       {
/* 123 */         setBlockBoundsForCurrentThread(0.0D, 0.0D, (1.0F - var2), 1.0D, 1.0D, 1.0D);
/*     */       }
/*     */       
/* 126 */       if ((par1 & 0x3) == 1)
/*     */       {
/* 128 */         setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, var2);
/*     */       }
/*     */       
/* 131 */       if ((par1 & 0x3) == 2)
/*     */       {
/* 133 */         setBlockBoundsForCurrentThread((1.0F - var2), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */       }
/*     */       
/* 136 */       if ((par1 & 0x3) == 3)
/*     */       {
/* 138 */         setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, var2, 1.0D, 1.0D);
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 169 */     if (this.blockMaterial != Material.wood) {
/* 170 */       return false;
/*     */     }
/* 172 */     if (player.onServer()) {
/*     */       
/* 174 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 176 */       metadata ^= 0x4;
/*     */       
/* 178 */       world.setBlockMetadataWithNotify(x, y, z, metadata, 3);
/* 179 */       makeOpenOrCloseSound(world, x, y, z, metadata);
/*     */     } 
/*     */     
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void makeOpenOrCloseSound(World world, int x, int y, int z, int metadata_after) {
/* 187 */     if (isOpen(metadata_after)) {
/* 188 */       world.playSoundAtBlock(x, y, z, "random.door_open", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
/*     */     } else {
/* 190 */       world.playSoundAtBlock(x, y, z, "random.door_close", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
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
/*     */   public boolean onPoweredBlockChange(World world, int x, int y, int z, boolean par5) {
/* 207 */     int metadata = world.getBlockMetadata(x, y, z);
/* 208 */     boolean var7 = ((metadata & 0x4) > 0);
/*     */     
/* 210 */     if (var7 != par5) {
/*     */       
/* 212 */       metadata ^= 0x4;
/*     */       
/* 214 */       if (world.setBlockMetadataWithNotify(x, y, z, metadata, 2)) {
/*     */         
/* 216 */         makeOpenOrCloseSound(world, x, y, z, metadata);
/* 217 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 221 */     return false;
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 275 */     if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
/* 276 */       return true;
/*     */     }
/* 278 */     boolean is_indirectly_powered = world.isBlockIndirectlyGettingPowered(x, y, z);
/*     */     
/* 280 */     if (is_indirectly_powered || (neighbor_block_id > 0 && Block.blocksList[neighbor_block_id].canProvidePower())) {
/* 281 */       return onPoweredBlockChange(world, x, y, z, is_indirectly_powered);
/*     */     }
/* 283 */     return false;
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
/*     */   public EnumFace getFaceMountedTo(int metadata) {
/* 333 */     int side = metadata & 0x3;
/*     */     
/* 335 */     if (side == 0)
/* 336 */       return EnumFace.NORTH; 
/* 337 */     if (side == 1)
/* 338 */       return EnumFace.SOUTH; 
/* 339 */     if (side == 2) {
/* 340 */       return EnumFace.WEST;
/*     */     }
/* 342 */     return EnumFace.EAST;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getDefaultMetadataForFaceMountedTo(EnumFace face) {
/* 347 */     if (face.isNorth())
/* 348 */       return 0; 
/* 349 */     if (face.isSouth())
/* 350 */       return 1; 
/* 351 */     if (face.isWest())
/* 352 */       return 2; 
/* 353 */     if (face.isEast()) {
/* 354 */       return 3;
/*     */     }
/* 356 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 361 */     return getDefaultMetadataForFaceMountedTo(face) | ((!face.isTopOrBottom() && offset_y > 0.5F) ? 8 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMountToBlock(int metadata, Block neighbor_block, int neighbor_block_metadata, EnumFace face) {
/* 366 */     if (!BlockDoor.isSuitableMaterialForAttachingHingesTo(neighbor_block.blockMaterial)) {
/* 367 */       return false;
/*     */     }
/* 369 */     if (neighbor_block == glass)
/*     */     {
/* 371 */       return true;
/*     */     }
/*     */     
/* 374 */     if (neighbor_block.isSingleSlab()) {
/*     */       
/* 376 */       if (BlockSlab.isBottom(neighbor_block_metadata)) {
/*     */         
/* 378 */         if (isLower(metadata)) {
/* 379 */           return true;
/*     */         
/*     */         }
/*     */       }
/* 383 */       else if (!isLower(metadata)) {
/* 384 */         return true;
/*     */       }
/*     */     
/* 387 */     } else if (neighbor_block instanceof BlockStairs) {
/*     */       
/* 389 */       if ((neighbor_block_metadata & 0x4) == 0) {
/*     */         
/* 391 */         if (isLower(metadata)) {
/* 392 */           return true;
/*     */         
/*     */         }
/*     */       }
/* 396 */       else if (!isLower(metadata)) {
/* 397 */         return true;
/*     */       }
/*     */     
/* 400 */     } else if (neighbor_block == snow) {
/*     */       
/* 402 */       if (neighbor_block_metadata > 2)
/*     */       {
/* 404 */         if (isLower(metadata)) {
/* 405 */           return true;
/*     */         }
/*     */       }
/*     */     } 
/* 409 */     return super.canMountToBlock(metadata, neighbor_block, neighbor_block_metadata, face);
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
/*     */   public static boolean isTrapdoorOpen(int par0) {
/* 453 */     return ((par0 & 0x4) != 0);
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
/*     */   private boolean isOpen(int metadata) {
/* 475 */     return ((metadata & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int setOpen(int metadata, boolean open) {
/* 480 */     return open ? (metadata | 0x4) : (metadata & 0xFFFFFFFB);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isLower(int metadata) {
/* 485 */     return ((metadata & 0x8) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int setUpper(int metadata, boolean upper) {
/* 490 */     return upper ? (metadata | 0x8) : (metadata & 0xFFFFFFF7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPortal() {
/* 495 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpenPortal(World world, int x, int y, int z) {
/* 500 */     return isOpen(world.getBlockMetadata(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
/* 505 */     if (face.isSide() || isOpen(metadata)) {
/* 506 */       return false;
/*     */     }
/* 508 */     return isLower(metadata) ? face.isBottom() : face.isTop();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 513 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 518 */     return !isOpen(metadata);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockTrapDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */