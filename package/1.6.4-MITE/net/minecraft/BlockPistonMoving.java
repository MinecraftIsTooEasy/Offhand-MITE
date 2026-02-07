/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPistonMoving
/*     */   extends BlockDirectionalWithTileEntity
/*     */ {
/*     */   public BlockPistonMoving(int par1) {
/*  11 */     super(par1, Material.piston, (new BlockConstants()).setNotAlwaysLegal());
/*  12 */     setHardness(-1.0F);
/*     */     
/*  14 */     setUnlocalizedName("pistonMoving");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  19 */     String[] array = new String[6];
/*     */     
/*  21 */     for (int i = 0; i < array.length; i++) {
/*  22 */       array[i] = i + "=" + pistonBase.getDirectionFacing(i).getDescriptor(true);
/*     */     }
/*  24 */     return StringHelper.implode(array, ", ", true, false) + ", bit 8 set if extension is sticky";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  29 */     return (metadata >= 0 && metadata < 14);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/*  37 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/*  52 */     TileEntity var7 = par1World.getBlockTileEntity(par2, par3, par4);
/*     */     
/*  54 */     if (var7 instanceof TileEntityPiston) {
/*     */       
/*  56 */       ((TileEntityPiston)var7).clearPistonTileEntity();
/*     */     }
/*     */     else {
/*     */       
/*  60 */       super.breakBlock(par1World, par2, par3, par4, par5, par6);
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
/*     */   public int getRenderType() {
/*  85 */     return -1;
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 123 */     if (world.getBlockTileEntity(x, y, z) == null) {
/*     */       
/* 125 */       if (player.onServer()) {
/* 126 */         world.setBlockToAir(x, y, z);
/*     */       }
/* 128 */       return true;
/*     */     } 
/*     */     
/* 131 */     return false;
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
/*     */   public boolean canBeCarried() {
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 165 */     TileEntityPiston var8 = getTileEntityAtLocation(info.world, info.x, info.y, info.z);
/*     */     
/* 167 */     if (var8 == null) {
/* 168 */       return 0;
/*     */     }
/* 170 */     return Block.blocksList[var8.getStoredBlockID()].dropBlockAsEntityItem(info.setMetadata(var8.getBlockMetadata()));
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 187 */     world.getBlockTileEntity(x, y, z);
/*     */     
/* 189 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TileEntity getTileEntity(int par0, int par1, int par2, boolean par3, boolean par4) {
/* 197 */     return new TileEntityPiston(par0, par1, par2, par3, par4);
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
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/* 227 */     TileEntityPiston tile_entity = getTileEntityAtLocation(world, x, y, z);
/*     */     
/* 229 */     if (tile_entity == null) {
/* 230 */       return null;
/*     */     }
/* 232 */     float progress = tile_entity.getProgress(0.0F);
/*     */     
/* 234 */     if (tile_entity.isExtending()) {
/* 235 */       progress = 1.0F - progress;
/*     */     }
/* 237 */     return getAxisAlignedBB(world, x, y, z, tile_entity.getStoredBlockID(), progress, tile_entity.getPistonOrientation());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 245 */     TileEntityPiston var5 = getTileEntityAtLocation(par1IBlockAccess, par2, par3, par4);
/*     */     
/* 247 */     if (var5 != null) {
/*     */       
/* 249 */       Block var6 = Block.blocksList[var5.getStoredBlockID()];
/*     */       
/* 251 */       if (var6 == null || var6 == this) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 256 */       var6.setBlockBoundsBasedOnStateAndNeighbors(par1IBlockAccess, par2, par3, par4);
/* 257 */       float var7 = var5.getProgress(0.0F);
/*     */       
/* 259 */       if (var5.isExtending())
/*     */       {
/* 261 */         var7 = 1.0F - var7;
/*     */       }
/*     */       
/* 264 */       if (var7 < 1.0F) {
/* 265 */         var7 = 0.0F;
/*     */       }
/* 267 */       int index = Minecraft.getThreadIndex();
/*     */       
/* 269 */       int var8 = var5.getPistonOrientation();
/* 270 */       this.minX[index] = var6.getBlockBoundsMinX(index) - (Facing.offsetsXForSide[var8] * var7);
/* 271 */       this.minY[index] = var6.getBlockBoundsMinY(index) - (Facing.offsetsYForSide[var8] * var7);
/* 272 */       this.minZ[index] = var6.getBlockBoundsMinZ(index) - (Facing.offsetsZForSide[var8] * var7);
/* 273 */       this.maxX[index] = var6.getBlockBoundsMaxX(index) - (Facing.offsetsXForSide[var8] * var7);
/* 274 */       this.maxY[index] = var6.getBlockBoundsMaxY(index) - (Facing.offsetsYForSide[var8] * var7);
/* 275 */       this.maxZ[index] = var6.getBlockBoundsMaxZ(index) - (Facing.offsetsZForSide[var8] * var7);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getAxisAlignedBB(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
/* 284 */     if (par5 != 0 && par5 != this.blockID) {
/*     */ 
/*     */       
/* 287 */       AxisAlignedBB var8 = Block.blocksList[par5].getCollisionBoundsCombined(par1World, par2, par3, par4, null, true);
/*     */       
/* 289 */       if (var8 == null)
/*     */       {
/* 291 */         return null;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 322 */       var8 = getBoundingBoxFromPool(par2, par3, par4, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */       
/* 324 */       EnumDirection direction = getDirectionFacing(par1World.getBlockMetadata(par2, par3, par4));
/*     */       
/* 326 */       double difference = par6 * 0.75D;
/*     */       
/* 328 */       if (direction.isUp()) {
/* 329 */         var8.maxY -= difference;
/* 330 */       } else if (direction.isDown()) {
/* 331 */         var8.minY += difference;
/* 332 */       } else if (direction.isSouth()) {
/* 333 */         var8.maxZ -= difference;
/* 334 */       } else if (direction.isNorth()) {
/* 335 */         var8.minZ += difference;
/* 336 */       } else if (direction.isEast()) {
/* 337 */         var8.maxX -= difference;
/* 338 */       } else if (direction.isWest()) {
/* 339 */         var8.minX += difference;
/*     */       } 
/* 341 */       return var8;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 346 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TileEntityPiston getTileEntityAtLocation(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 355 */     TileEntity var5 = par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
/* 356 */     return (var5 instanceof TileEntityPiston) ? (TileEntityPiston)var5 : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 364 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 373 */     this.blockIcon = par1IconRegister.registerIcon("piston_top_normal");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 378 */     return "moving";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
/* 383 */     return (face == getBackFace(metadata));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/* 393 */     return pistonBase.getDirectionFacing(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 398 */     return pistonBase.getMetadataForDirectionFacing(metadata, direction);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 403 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 408 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPistonMoving.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */