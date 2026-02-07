/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockLever
/*     */   extends BlockMounted
/*     */ {
/*     */   protected BlockLever(int par1) {
/*   9 */     super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  10 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  15 */     String[] array = new String[8];
/*     */     
/*  17 */     for (int i = 0; i < array.length; i++) {
/*  18 */       array[i] = i + "=Mounted " + getDirectionOfSupportBlock(i).getDescriptor(true);
/*     */     }
/*  20 */     return StringHelper.implode(array, ", ", true, false) + ", and bit 8 set if pulled";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  25 */     return (metadata >= 0 && metadata < 16);
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
/*     */   public final int getRenderType() {
/*  63 */     return 12;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isMaterialSuitableForLeverMounting(Material material, EnumFace face) {
/*  68 */     if (material == Material.clay) {
/*  69 */       return false;
/*     */     }
/*  71 */     return BlockButton.isMaterialSuitableForButtonMounting(material, face);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMountToBlock(int metadata, Block neighbor_block, int neighbor_block_metadata, EnumFace face) {
/*  76 */     if (!isMaterialSuitableForLeverMounting(neighbor_block.blockMaterial, face)) {
/*  77 */       return false;
/*     */     }
/*  79 */     if ((neighbor_block instanceof BlockPistonBase || neighbor_block instanceof BlockPistonMoving) && face.isSide() && ((BlockDirectional)neighbor_block).isAlignedWith(neighbor_block_metadata, getAxisOfMotion(metadata))) {
/*  80 */       return false;
/*     */     }
/*  82 */     return super.canMountToBlock(metadata, neighbor_block, neighbor_block_metadata, face);
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
/*     */   public EnumFace getFaceMountedTo(int metadata) {
/* 103 */     int orientation = metadata & 0x7;
/*     */     
/* 105 */     if (orientation == 0 || orientation == 7)
/* 106 */       return EnumFace.BOTTOM; 
/* 107 */     if (orientation == 1)
/* 108 */       return EnumFace.EAST; 
/* 109 */     if (orientation == 2)
/* 110 */       return EnumFace.WEST; 
/* 111 */     if (orientation == 3)
/* 112 */       return EnumFace.SOUTH; 
/* 113 */     if (orientation == 4)
/* 114 */       return EnumFace.NORTH; 
/* 115 */     if (orientation == 5 || orientation == 6) {
/* 116 */       return EnumFace.TOP;
/*     */     }
/* 118 */     Minecraft.setErrorMessage("getFaceMountedTo: invalid orientation " + orientation);
/* 119 */     return null;
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
/*     */   public final int getDefaultMetadataForFaceMountedTo(EnumFace face) {
/* 145 */     if (face.isBottom())
/* 146 */       return 0; 
/* 147 */     if (face.isEast())
/* 148 */       return 1; 
/* 149 */     if (face.isWest())
/* 150 */       return 2; 
/* 151 */     if (face.isSouth())
/* 152 */       return 3; 
/* 153 */     if (face.isNorth())
/* 154 */       return 4; 
/* 155 */     if (face.isTop()) {
/* 156 */       return 5;
/*     */     }
/* 158 */     return -1;
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
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction, int base_coord_mode) {
/* 181 */     if (direction.isHorizontal()) {
/* 182 */       return getMetadataForDirectionFacing(metadata, direction);
/*     */     }
/* 184 */     if (base_coord_mode == 0 || base_coord_mode == 2) {
/* 185 */       return metadata;
/*     */     }
/* 187 */     int toggled_bit = metadata & 0x8;
/* 188 */     int orientation = metadata & 0xFFFFFFF7;
/*     */     
/* 190 */     orientation = (orientation == 0) ? 7 : ((orientation == 7) ? 0 : ((orientation == 5) ? 6 : ((orientation == 6) ? 5 : -1)));
/*     */     
/* 192 */     return orientation | toggled_bit;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 197 */     Block block = world.getNeighborBlock(x, y, z, face.getOpposite());
/*     */     
/* 199 */     if (block instanceof BlockPistonBase && face.isTopOrBottom()) {
/*     */       
/* 201 */       BlockPistonBase block_piston_base = (BlockPistonBase)block;
/*     */       
/* 203 */       EnumDirection enumDirection = block_piston_base.getDirectionFacing(world.getNeighborBlockMetadata(x, y, z, face.getOpposite()));
/*     */       
/* 205 */       if (enumDirection.isNorthOrSouth())
/* 206 */         return face.isBottom() ? 0 : 6; 
/* 207 */       if (enumDirection.isEastOrWest()) {
/* 208 */         return face.isTop() ? 5 : 7;
/*     */       }
/*     */     } 
/* 211 */     EnumDirection direction = entity.getDirectionFromYaw();
/*     */     
/* 213 */     if (face.isBottom())
/* 214 */       return direction.isEastOrWest() ? 0 : 7; 
/* 215 */     if (face.isTop()) {
/* 216 */       return direction.isNorthOrSouth() ? 5 : 6;
/*     */     }
/* 218 */     return getDefaultMetadataForFaceMountedTo(face);
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
/*     */   public static int invertMetadata(int par0) {
/* 303 */     switch (par0) {
/*     */       
/*     */       case 0:
/* 306 */         return 0;
/*     */       
/*     */       case 1:
/* 309 */         return 5;
/*     */       
/*     */       case 2:
/* 312 */         return 4;
/*     */       
/*     */       case 3:
/* 315 */         return 3;
/*     */       
/*     */       case 4:
/* 318 */         return 2;
/*     */       
/*     */       case 5:
/* 321 */         return 1;
/*     */     } 
/*     */     
/* 324 */     return -1;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 414 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x7;
/* 415 */     float var6 = 0.1875F;
/*     */     
/* 417 */     if (var5 == 1) {
/*     */       
/* 419 */       setBlockBoundsForCurrentThread(0.0D, 0.20000000298023224D, (0.5F - var6), (var6 * 2.0F), 0.800000011920929D, (0.5F + var6));
/*     */     }
/* 421 */     else if (var5 == 2) {
/*     */       
/* 423 */       setBlockBoundsForCurrentThread((1.0F - var6 * 2.0F), 0.20000000298023224D, (0.5F - var6), 1.0D, 0.800000011920929D, (0.5F + var6));
/*     */     }
/* 425 */     else if (var5 == 3) {
/*     */       
/* 427 */       setBlockBoundsForCurrentThread((0.5F - var6), 0.20000000298023224D, 0.0D, (0.5F + var6), 0.800000011920929D, (var6 * 2.0F));
/*     */     }
/* 429 */     else if (var5 == 4) {
/*     */       
/* 431 */       setBlockBoundsForCurrentThread((0.5F - var6), 0.20000000298023224D, (1.0F - var6 * 2.0F), (0.5F + var6), 0.800000011920929D, 1.0D);
/*     */     }
/* 433 */     else if (var5 != 5 && var5 != 6) {
/*     */       
/* 435 */       if (var5 == 0 || var5 == 7)
/*     */       {
/* 437 */         var6 = 0.25F;
/* 438 */         setBlockBoundsForCurrentThread((0.5F - var6), 0.4000000059604645D, (0.5F - var6), (0.5F + var6), 1.0D, (0.5F + var6));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 443 */       var6 = 0.25F;
/* 444 */       setBlockBoundsForCurrentThread((0.5F - var6), 0.0D, (0.5F - var6), (0.5F + var6), 0.6000000238418579D, (0.5F + var6));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 500 */     if (player.onServer()) {
/*     */       
/* 502 */       int metadata = world.getBlockMetadata(x, y, z);
/* 503 */       int var11 = metadata & 0x7;
/* 504 */       int var12 = 8 - (metadata & 0x8);
/*     */       
/* 506 */       world.setBlockMetadataWithNotify(x, y, z, var11 + var12, 3);
/* 507 */       world.playSoundAtBlock(x, y, z, "random.click", 0.3F, (var12 > 0) ? 0.6F : 0.5F);
/*     */       
/* 509 */       world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
/*     */       
/* 511 */       EnumDirection direction = getDirectionFacing(metadata);
/*     */       
/* 513 */       if (direction.isEast()) {
/* 514 */         x--;
/* 515 */       } else if (direction.isWest()) {
/* 516 */         x++;
/* 517 */       } else if (direction.isSouth()) {
/* 518 */         z--;
/* 519 */       } else if (direction.isNorth()) {
/* 520 */         z++;
/* 521 */       } else if (direction.isUp()) {
/* 522 */         y--;
/*     */       } else {
/* 524 */         y++;
/*     */       } 
/* 526 */       world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
/*     */     } 
/*     */     
/* 529 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 539 */     if ((par6 & 0x8) > 0) {
/*     */       
/* 541 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
/* 542 */       int var7 = par6 & 0x7;
/*     */       
/* 544 */       if (var7 == 1) {
/*     */         
/* 546 */         par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
/*     */       }
/* 548 */       else if (var7 == 2) {
/*     */         
/* 550 */         par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
/*     */       }
/* 552 */       else if (var7 == 3) {
/*     */         
/* 554 */         par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
/*     */       }
/* 556 */       else if (var7 == 4) {
/*     */         
/* 558 */         par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
/*     */       }
/* 560 */       else if (var7 != 5 && var7 != 6) {
/*     */         
/* 562 */         if (var7 == 0 || var7 == 7)
/*     */         {
/* 564 */           par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 569 */         par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/*     */       } 
/*     */     } 
/*     */     
/* 573 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 583 */     return ((par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x8) > 0) ? 15 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 592 */     int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */     
/* 594 */     if ((var6 & 0x8) == 0)
/*     */     {
/* 596 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 600 */     int var7 = var6 & 0x7;
/* 601 */     return (var7 == 0 && par5 == 0) ? 15 : ((var7 == 7 && par5 == 0) ? 15 : ((var7 == 6 && par5 == 1) ? 15 : ((var7 == 5 && par5 == 1) ? 15 : ((var7 == 4 && par5 == 2) ? 15 : ((var7 == 3 && par5 == 3) ? 15 : ((var7 == 2 && par5 == 4) ? 15 : ((var7 == 1 && par5 == 5) ? 15 : 0)))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 610 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 615 */     item_block.addMaterial(new Material[] { Material.stone, Material.wood });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Axis getAxisOfMotion(int metadata) {
/* 621 */     EnumFace face = getFaceMountedTo(metadata);
/*     */     
/* 623 */     if (face.isSide()) {
/* 624 */       return Axis.UP_DOWN;
/*     */     }
/* 626 */     return (metadata == 5 || metadata == 7 || metadata == 13 || metadata == 15) ? Axis.NORTH_SOUTH : Axis.EAST_WEST;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 631 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 636 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockLever.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */