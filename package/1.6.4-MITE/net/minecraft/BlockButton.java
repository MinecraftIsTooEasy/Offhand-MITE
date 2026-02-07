/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockButton
/*     */   extends BlockMounted
/*     */ {
/*     */   private final boolean sensible;
/*     */   
/*     */   protected BlockButton(int par1, boolean par2) {
/*  15 */     super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  16 */     setTickRandomly(true);
/*  17 */     setCreativeTab(CreativeTabs.tabRedstone);
/*  18 */     this.sensible = par2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  23 */     String[] array = new String[4];
/*     */     
/*  25 */     for (int i = 0; i < array.length; i++) {
/*  26 */       array[i] = (i + 1) + "=Mounted " + getDirectionOfSupportBlock(i + 1).getDescriptor(true);
/*     */     }
/*  28 */     return StringHelper.implode(array, ", ", true, false) + ", and bit 8 set if pressed";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  33 */     return ((metadata > 0 && metadata < 5) || (metadata > 8 && metadata < 13));
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
/*     */   public int tickRate(World par1World) {
/*  51 */     return 30;
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
/*     */   public static boolean isMaterialSuitableForButtonMounting(Material material, EnumFace face) {
/*  75 */     if (material == Material.dirt || material == Material.grass || material == Material.sand || material.isSnow() || material == Material.tree_leaves || material == Material.pumpkin || material == Material.cloth || material == Material.glass || material == Material.sponge) {
/*  76 */       return false;
/*     */     }
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMountToBlock(int metadata, Block neighbor_block, int neighbor_block_metadata, EnumFace face) {
/*  83 */     if (!isMaterialSuitableForButtonMounting(neighbor_block.blockMaterial, face)) {
/*  84 */       return false;
/*     */     }
/*  86 */     return super.canMountToBlock(metadata, neighbor_block, neighbor_block_metadata, face);
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
/*     */   public EnumFace getFaceMountedTo(int metadata) {
/*  99 */     int orientation = metadata & 0x7;
/*     */     
/* 101 */     if (orientation == 1)
/* 102 */       return EnumFace.EAST; 
/* 103 */     if (orientation == 2)
/* 104 */       return EnumFace.WEST; 
/* 105 */     if (orientation == 3)
/* 106 */       return EnumFace.SOUTH; 
/* 107 */     if (orientation == 4) {
/* 108 */       return EnumFace.NORTH;
/*     */     }
/* 110 */     Minecraft.setErrorMessage("getFaceMountedTo: invalid orientation " + orientation);
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getDefaultMetadataForFaceMountedTo(EnumFace face) {
/* 116 */     if (face.isEast())
/* 117 */       return 1; 
/* 118 */     if (face.isWest())
/* 119 */       return 2; 
/* 120 */     if (face.isSouth())
/* 121 */       return 3; 
/* 122 */     if (face.isNorth()) {
/* 123 */       return 4;
/*     */     }
/* 125 */     return -1;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 240 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/* 241 */     func_82534_e(var5);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_82534_e(int par1) {
/* 246 */     int var2 = par1 & 0x7;
/* 247 */     boolean var3 = ((par1 & 0x8) > 0);
/* 248 */     float var4 = 0.375F;
/* 249 */     float var5 = 0.625F;
/* 250 */     float var6 = 0.1875F;
/* 251 */     float var7 = 0.125F;
/*     */     
/* 253 */     if (var3)
/*     */     {
/* 255 */       var7 = 0.0625F;
/*     */     }
/*     */     
/* 258 */     if (var2 == 1) {
/*     */       
/* 260 */       setBlockBoundsForCurrentThread(0.0D, var4, (0.5F - var6), var7, var5, (0.5F + var6));
/*     */     }
/* 262 */     else if (var2 == 2) {
/*     */       
/* 264 */       setBlockBoundsForCurrentThread((1.0F - var7), var4, (0.5F - var6), 1.0D, var5, (0.5F + var6));
/*     */     }
/* 266 */     else if (var2 == 3) {
/*     */       
/* 268 */       setBlockBoundsForCurrentThread((0.5F - var6), var4, 0.0D, (0.5F + var6), var5, var7);
/*     */     }
/* 270 */     else if (var2 == 4) {
/*     */       
/* 272 */       setBlockBoundsForCurrentThread((0.5F - var6), var4, (1.0F - var7), (0.5F + var6), var5, 1.0D);
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
/*     */   public static boolean isPressed(int metadata) {
/* 307 */     return ((metadata & 0x8) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getPressed(int metadata) {
/* 312 */     return metadata | 0x8;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 317 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 319 */     if (isPressed(metadata)) {
/*     */       
/* 321 */       player.cancelRightClick();
/* 322 */       return true;
/*     */     } 
/*     */     
/* 325 */     if (player.onServer()) {
/*     */       
/* 327 */       world.setBlockMetadataWithNotify(x, y, z, getPressed(metadata), 3);
/*     */ 
/*     */ 
/*     */       
/* 331 */       world.playSoundAtBlock(x, y, z, "random.click", 0.3F, 0.6F);
/*     */       
/* 333 */       func_82536_d(world, x, y, z, metadata & 0x7);
/*     */       
/* 335 */       world.scheduleBlockUpdate(x, y, z, this.blockID, tickRate(world));
/*     */     } 
/*     */     
/* 338 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 348 */     if ((par6 & 0x8) > 0) {
/*     */       
/* 350 */       int var7 = par6 & 0x7;
/* 351 */       func_82536_d(par1World, par2, par3, par4, var7);
/*     */     } 
/*     */     
/* 354 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 364 */     return ((par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x8) > 0) ? 15 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 373 */     int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */     
/* 375 */     if ((var6 & 0x8) == 0)
/*     */     {
/* 377 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 381 */     int var7 = var6 & 0x7;
/* 382 */     return (var7 == 5 && par5 == 1) ? 15 : ((var7 == 4 && par5 == 2) ? 15 : ((var7 == 3 && par5 == 3) ? 15 : ((var7 == 2 && par5 == 4) ? 15 : ((var7 == 1 && par5 == 5) ? 15 : 0))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 391 */     return true;
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 423 */     if (super.updateTick(world, x, y, z, random)) {
/* 424 */       return true;
/*     */     }
/* 426 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 428 */     if ((metadata & 0x8) != 0) {
/*     */       
/* 430 */       if (this.sensible)
/*     */       {
/*     */         
/* 433 */         return func_82535_o(world, x, y, z);
/*     */       }
/*     */ 
/*     */       
/* 437 */       world.setBlockMetadataWithNotify(x, y, z, metadata & 0x7, 3);
/* 438 */       int var7 = metadata & 0x7;
/* 439 */       func_82536_d(world, x, y, z, var7);
/* 440 */       world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.5F);
/* 441 */       world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
/*     */       
/* 443 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 447 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/* 456 */     float var1 = 0.1875F;
/* 457 */     float var2 = 0.125F;
/* 458 */     float var3 = 0.125F;
/* 459 */     setBlockBoundsForCurrentThread((0.5F - var1), (0.5F - var2), (0.5F - var3), (0.5F + var1), (0.5F + var2), (0.5F + var3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 467 */     if (!par1World.isRemote)
/*     */     {
/* 469 */       if (this.sensible)
/*     */       {
/* 471 */         if ((par1World.getBlockMetadata(par2, par3, par4) & 0x8) == 0)
/*     */         {
/* 473 */           func_82535_o(par1World, par2, par3, par4);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_82535_o(World par1World, int par2, int par3, int par4) {
/* 482 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/* 483 */     int var6 = var5 & 0x7;
/* 484 */     boolean var7 = ((var5 & 0x8) != 0);
/* 485 */     func_82534_e(var5);
/*     */     
/* 487 */     int index = Minecraft.getThreadIndex();
/*     */     
/* 489 */     List var9 = par1World.getEntitiesWithinAABB(EntityArrow.class, AxisAlignedBB.getAABBPool().getAABB(par2 + this.minX[index], par3 + this.minY[index], par4 + this.minZ[index], par2 + this.maxX[index], par3 + this.maxY[index], par4 + this.maxZ[index]));
/* 490 */     boolean var8 = !var9.isEmpty();
/*     */     
/* 492 */     boolean changed_state = false;
/*     */     
/* 494 */     if (var8 && !var7) {
/*     */       
/* 496 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 0x8, 3);
/* 497 */       func_82536_d(par1World, par2, par3, par4, var6);
/* 498 */       par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
/* 499 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "random.click", 0.3F, 0.6F);
/*     */       
/* 501 */       changed_state = true;
/*     */     } 
/*     */     
/* 504 */     if (!var8 && var7) {
/*     */       
/* 506 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 3);
/* 507 */       func_82536_d(par1World, par2, par3, par4, var6);
/* 508 */       par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
/* 509 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "random.click", 0.3F, 0.5F);
/*     */       
/* 511 */       changed_state = true;
/*     */     } 
/*     */     
/* 514 */     if (var8)
/*     */     {
/* 516 */       par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate(par1World));
/*     */     }
/*     */     
/* 519 */     return changed_state;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_82536_d(World par1World, int par2, int par3, int par4, int par5) {
/* 524 */     par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
/*     */     
/* 526 */     if (par5 == 1) {
/*     */       
/* 528 */       par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
/*     */     }
/* 530 */     else if (par5 == 2) {
/*     */       
/* 532 */       par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
/*     */     }
/* 534 */     else if (par5 == 3) {
/*     */       
/* 536 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
/*     */     }
/* 538 */     else if (par5 == 4) {
/*     */       
/* 540 */       par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
/*     */     }
/*     */     else {
/*     */       
/* 544 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 556 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 561 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */