/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public abstract class BlockMounted
/*     */   extends Block
/*     */ {
/*   7 */   EnumDirection[] default_metadata_direction_order = new EnumDirection[6];
/*     */ 
/*     */   
/*     */   public BlockMounted(int id, Material material, BlockConstants constants) {
/*  11 */     super(id, material, constants);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract EnumFace getFaceMountedTo(int paramInt);
/*     */ 
/*     */   
/*     */   public EnumDirection getDirectionFacing(int metadata) {
/*  20 */     return getFaceMountedTo(metadata).getNormal();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumDirection getDirectionOfSupportBlock(int metadata) {
/*  28 */     EnumFace face = getFaceMountedTo(metadata);
/*  29 */     return (face == null) ? null : face.getAntiNormal();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMountToBlock(int metadata, Block neighbor_block, int neighbor_block_metadata, EnumFace face) {
/*  35 */     if (neighbor_block == tilledField) {
/*  36 */       return true;
/*     */     }
/*  38 */     if (face.isTop() && neighbor_block == jukebox) {
/*  39 */       return false;
/*     */     }
/*     */     
/*  42 */     if (neighbor_block instanceof BlockLeaves || neighbor_block.neverHidesAdjacentFaces() || neighbor_block.blockMaterial == Material.cloth || neighbor_block instanceof BlockSponge) {
/*  43 */       return false;
/*     */     }
/*  45 */     if (neighbor_block instanceof BlockPistonBase || neighbor_block instanceof BlockPistonMoving) {
/*  46 */       return (face != ((BlockDirectional)neighbor_block).getFrontFace(neighbor_block_metadata));
/*     */     }
/*  48 */     if (neighbor_block instanceof BlockFurnace || neighbor_block instanceof BlockDispenser) {
/*  49 */       return (face != ((BlockDirectional)neighbor_block).getFrontFace(neighbor_block_metadata));
/*     */     }
/*  51 */     return neighbor_block.isFaceFlatAndSolid(neighbor_block_metadata, face);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean canMountToBlock(World world, int metadata, int x, int y, int z, EnumDirection direction) {
/*  56 */     x = direction.getNeighborX(x);
/*  57 */     y = direction.getNeighborY(y);
/*  58 */     z = direction.getNeighborZ(z);
/*     */     
/*  60 */     Block block = world.getBlock(x, y, z);
/*     */     
/*  62 */     return (block != null && canMountToBlock(metadata, block, world.getBlockMetadata(x, y, z), direction.getOppositeFace()));
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean canMountToBlockWithDefault(World world, int metadata, int x, int y, int z, EnumDirection direction, boolean result_if_block_does_not_exist) {
/*  67 */     if (direction == null) {
/*  68 */       return false;
/*     */     }
/*  70 */     return world.neighborBlockExists(x, y, z, direction) ? canMountToBlock(world, metadata, x, y, z, direction) : result_if_block_does_not_exist;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/*  75 */     return canMountToBlockWithDefault(world, metadata, x, y, z, getDirectionOfSupportBlock(metadata), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/*  80 */     if (!getFaceMountedTo(metadata).isTop()) {
/*  81 */       return true;
/*     */     }
/*  83 */     return (block_below != null && canMountToBlock(metadata, block_below, block_below_metadata, EnumFace.TOP));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getDefaultMetadataForFaceMountedTo(EnumFace paramEnumFace);
/*     */ 
/*     */   
/*     */   public int getDefaultMetadata(World world, int x, int y, int z) {
/*  92 */     this.default_metadata_direction_order[0] = EnumDirection.DOWN;
/*  93 */     this.default_metadata_direction_order[1] = EnumDirection.WEST;
/*  94 */     this.default_metadata_direction_order[2] = EnumDirection.EAST;
/*  95 */     this.default_metadata_direction_order[3] = EnumDirection.NORTH;
/*  96 */     this.default_metadata_direction_order[4] = EnumDirection.SOUTH;
/*  97 */     this.default_metadata_direction_order[5] = EnumDirection.UP;
/*     */     
/*  99 */     for (int i = 0; i < 6; i++) {
/*     */       
/* 101 */       EnumDirection direction = this.default_metadata_direction_order[i];
/*     */ 
/*     */ 
/*     */       
/* 105 */       int metadata = getDefaultMetadataForFaceMountedTo(direction.getOppositeFace());
/*     */       
/* 107 */       if (metadata >= 0 && canMountToBlockWithDefault(world, metadata, x, y, z, direction, false)) {
/* 108 */         return metadata;
/*     */       }
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
/* 122 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 127 */     return getDefaultMetadataForFaceMountedTo(face);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReplaceBlock(int metadata, Block existing_block, int existing_block_metadata) {
/* 132 */     return (existing_block != redstoneWire && super.canReplaceBlock(metadata, existing_block, existing_block_metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 137 */     return getDefaultMetadataForFaceMountedTo(direction.getFace());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 142 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockMounted.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */