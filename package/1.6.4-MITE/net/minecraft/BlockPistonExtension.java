/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPistonExtension
/*     */   extends BlockDirectional
/*     */   implements IBlockWithPartner
/*     */ {
/*     */   private Icon headTexture;
/*  12 */   private static final AxisAlignedBB[] bounds_for_plate = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.75D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D), new AxisAlignedBB(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D), new AxisAlignedBB(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   private static final AxisAlignedBB[] bounds_for_rod = new AxisAlignedBB[] { new AxisAlignedBB(0.375D, 0.25D, 0.375D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.75D, 0.625D), new AxisAlignedBB(0.375D, 0.375D, 0.25D, 0.625D, 0.625D, 1.0D), new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 0.75D), new AxisAlignedBB(0.25D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D), new AxisAlignedBB(0.0D, 0.375D, 0.375D, 0.75D, 0.625D, 0.625D) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   private static final AxisAlignedBB[][] multiple_bounds = getMultipleBounds();
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPistonExtension(int par1) {
/*  37 */     super(par1, Material.piston, (new BlockConstants()).setNotAlwaysLegal());
/*  38 */     setStepSound(soundStoneFootstep);
/*  39 */     setHardness(0.5F);
/*     */     
/*  41 */     setUnlocalizedName("pistonExtension");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  46 */     String[] array = new String[6];
/*     */     
/*  48 */     for (int i = 0; i < array.length; i++) {
/*  49 */       array[i] = i + "=" + pistonBase.getDirectionFacing(i).getDescriptor(true);
/*     */     }
/*  51 */     return StringHelper.implode(array, ", ", true, false) + ", bit 8 set if extension is sticky";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  56 */     return (metadata >= 0 && metadata < 14);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadTexture(Icon par1Icon) {
/*  61 */     this.headTexture = par1Icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearHeadTexture() {
/*  66 */     this.headTexture = null;
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
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/*  95 */     int block_id = par1World.getBlockId(par2, par3, par4);
/*  96 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*  97 */     int var7 = Facing.oppositeSide[getDirectionMeta(par6)];
/*  98 */     par2 += Facing.offsetsXForSide[var7];
/*  99 */     par3 += Facing.offsetsYForSide[var7];
/* 100 */     par4 += Facing.offsetsZForSide[var7];
/* 101 */     int var8 = par1World.getBlockId(par2, par3, par4);
/*     */     
/* 103 */     if (var8 == Block.pistonBase.blockID || var8 == Block.pistonStickyBase.blockID) {
/*     */       
/* 105 */       par6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */       
/* 107 */       if (BlockPistonBase.isExtended(par6)) {
/*     */ 
/*     */         
/* 110 */         Block.blocksList[var8].dropBlockAsEntityItem((new BlockBreakInfo(par1World, par2, par3, par4)).setNeighborChanged(block_id));
/* 111 */         par1World.setBlockToAir(par2, par3, par4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAboutToBeBroken(BlockBreakInfo info) {
/* 118 */     if (!info.isResponsiblePlayerInCreativeMode()) {
/*     */       return;
/*     */     }
/* 121 */     int x = info.x;
/* 122 */     int y = info.y;
/* 123 */     int z = info.z;
/*     */     
/* 125 */     int var7 = Facing.oppositeSide[getDirectionMeta(info.getMetadata())];
/* 126 */     x += Facing.offsetsXForSide[var7];
/* 127 */     y += Facing.offsetsYForSide[var7];
/* 128 */     z += Facing.offsetsZForSide[var7];
/*     */     
/* 130 */     int var8 = info.world.getBlockId(x, y, z);
/*     */     
/* 132 */     if (var8 == Block.pistonBase.blockID || var8 == Block.pistonStickyBase.blockID)
/*     */     {
/* 134 */       if (BlockPistonBase.isExtended(info.world.getBlockMetadata(x, y, z))) {
/* 135 */         info.world.setBlockToAir(x, y, z);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 144 */     int var3 = getDirectionMeta(par2);
/* 145 */     return (par1 == var3) ? ((this.headTexture != null) ? this.headTexture : (((par2 & 0x8) != 0) ? BlockPistonBase.getPistonBaseIcon("piston_top_sticky") : BlockPistonBase.getPistonBaseIcon("piston_top_normal"))) : ((var3 < 6 && par1 == Facing.oppositeSide[var3]) ? BlockPistonBase.getPistonBaseIcon("piston_top_normal") : BlockPistonBase.getPistonBaseIcon("piston_side"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 159 */     return 17;
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
/*     */   private void setBoundsForPlate(int metadata) {
/* 205 */     setBlockBoundsForCurrentThread(bounds_for_plate[getDirectionMeta(metadata)]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AxisAlignedBB[][] getMultipleBounds() {
/* 215 */     AxisAlignedBB[][] multiple_bounds = new AxisAlignedBB[bounds_for_plate.length][];
/*     */     
/* 217 */     for (int i = 0; i < multiple_bounds.length; i++) {
/*     */       
/* 219 */       multiple_bounds[i] = new AxisAlignedBB[2];
/*     */       
/* 221 */       multiple_bounds[i][0] = bounds_for_plate[i];
/* 222 */       multiple_bounds[i][1] = bounds_for_rod[i];
/*     */     } 
/*     */     
/* 225 */     return multiple_bounds;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/* 230 */     return multiple_bounds[getDirectionMeta(world.getBlockMetadata(x, y, z))];
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 361 */     setBoundsForPlate(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 385 */     if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
/* 386 */       return true;
/*     */     }
/* 388 */     int direction = getDirectionMeta(world.getBlockMetadata(x, y, z));
/*     */     
/* 390 */     int base_x = x - Facing.offsetsXForSide[direction];
/* 391 */     int base_y = y - Facing.offsetsYForSide[direction];
/* 392 */     int base_z = z - Facing.offsetsZForSide[direction];
/*     */     
/* 394 */     Block block = world.getBlock(base_x, base_y, base_z);
/*     */     
/* 396 */     return block.onNeighborBlockChange(world, base_x, base_y, base_z, neighbor_block_id);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 401 */     int direction = getDirectionMeta(world.getBlockMetadata(x, y, z));
/* 402 */     Block block = world.getBlock(x - Facing.offsetsXForSide[direction], y - Facing.offsetsYForSide[direction], z - Facing.offsetsZForSide[direction]);
/*     */     
/* 404 */     return (block == pistonBase || block == pistonStickyBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDirectionMeta(int par0) {
/* 409 */     return par0 & 0x7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 417 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/* 418 */     return ((var5 & 0x8) != 0) ? Block.pistonStickyBase.blockID : Block.pistonBase.blockID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 423 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 428 */     if (info.isResponsiblePlayerInCreativeMode()) {
/*     */       
/* 430 */       int var7 = getDirectionMeta(info.getMetadata());
/* 431 */       int var8 = info.world.getBlockId(info.x - Facing.offsetsXForSide[var7], info.y - Facing.offsetsYForSide[var7], info.z - Facing.offsetsZForSide[var7]);
/*     */       
/* 433 */       if (var8 == Block.pistonBase.blockID || var8 == Block.pistonStickyBase.blockID)
/*     */       {
/* 435 */         info.world.setBlockToAir(info.x - Facing.offsetsXForSide[var7], info.y - Facing.offsetsYForSide[var7], info.z - Facing.offsetsZForSide[var7]);
/*     */       }
/*     */     } 
/*     */     
/* 439 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 444 */     return "extension";
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/* 449 */     return pistonBase.getDirectionFacing(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 454 */     return pistonBase.getMetadataForDirectionFacing(metadata, direction);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 459 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSupportEntityShadow(int metadata) {
/* 464 */     return getDirectionFacing(metadata).isUp();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetX(int metadata) {
/* 469 */     return -(getDirectionFacing(metadata)).dx;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetY(int metadata) {
/* 474 */     return -(getDirectionFacing(metadata)).dy;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetZ(int metadata) {
/* 479 */     return -(getDirectionFacing(metadata)).dz;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresPartner(int metadata) {
/* 484 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartner(int metadata, Block neighbor_block, int neighbor_block_metadata) {
/* 489 */     return (neighbor_block instanceof BlockPistonBase && ((BlockPistonBase)neighbor_block).getDirectionFacing(neighbor_block_metadata) == getDirectionFacing(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean partnerDropsAsItem(int metadata) {
/* 494 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPistonExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */