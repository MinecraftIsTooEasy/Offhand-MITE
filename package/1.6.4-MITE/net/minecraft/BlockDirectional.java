/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockDirectional
/*     */   extends Block
/*     */ {
/*  20 */   private EnumDirection[] direction_facing_for_metadata = new EnumDirection[16];
/*  21 */   private int[] default_metadata_for_direction_facing = new int[6];
/*     */   
/*     */   private boolean are_maps_ready;
/*     */   
/*     */   private final boolean can_face_up;
/*     */   private final boolean can_face_down;
/*  27 */   EnumDirection[] default_metadata_direction_order = new EnumDirection[6];
/*     */ 
/*     */   
/*     */   protected BlockDirectional(int id, Material material, BlockConstants block_constants) {
/*  31 */     super(id, material, block_constants);
/*     */     
/*  33 */     createMaps();
/*     */     
/*  35 */     this.can_face_up = canFaceDirection(EnumDirection.UP);
/*  36 */     this.can_face_down = canFaceDirection(EnumDirection.DOWN);
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
/*     */   private void createMaps() {
/*     */     int i;
/*  50 */     for (i = 0; i < this.direction_facing_for_metadata.length; i++) {
/*  51 */       this.direction_facing_for_metadata[i] = getDirectionFacing(i);
/*     */     }
/*  53 */     for (i = 0; i < this.default_metadata_for_direction_facing.length; i++) {
/*     */       
/*  55 */       this.default_metadata_for_direction_facing[i] = -1;
/*     */       
/*  57 */       EnumDirection direction = EnumDirection.get(i);
/*     */       
/*  59 */       for (int j = 0; j < this.direction_facing_for_metadata.length; j++) {
/*     */         
/*  61 */         if (this.direction_facing_for_metadata[j] == direction) {
/*     */           
/*  63 */           this.default_metadata_for_direction_facing[i] = j;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*  69 */     this.are_maps_ready = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract EnumDirection getDirectionFacing(int paramInt);
/*     */   
/*     */   public final EnumDirection getDirectionFacingStandard4(int metadata) {
/*  76 */     if (this.are_maps_ready)
/*     */     {
/*     */       
/*  79 */       return this.direction_facing_for_metadata[metadata];
/*     */     }
/*     */     
/*  82 */     int direction = metadata & 0x3;
/*     */     
/*  84 */     if (direction == 0)
/*  85 */       return EnumDirection.SOUTH; 
/*  86 */     if (direction == 1)
/*  87 */       return EnumDirection.WEST; 
/*  88 */     if (direction == 2) {
/*  89 */       return EnumDirection.NORTH;
/*     */     }
/*  91 */     return EnumDirection.EAST;
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumDirection getDirectionFacingStandard6(int metadata) {
/*  96 */     if (this.are_maps_ready) {
/*  97 */       return this.direction_facing_for_metadata[metadata];
/*     */     }
/*  99 */     int direction = metadata & 0x7;
/*     */     
/* 101 */     if (direction == 0)
/* 102 */       return EnumDirection.DOWN; 
/* 103 */     if (direction == 1)
/* 104 */       return EnumDirection.UP; 
/* 105 */     if (direction == 2)
/* 106 */       return EnumDirection.NORTH; 
/* 107 */     if (direction == 3)
/* 108 */       return EnumDirection.SOUTH; 
/* 109 */     if (direction == 4)
/* 110 */       return EnumDirection.WEST; 
/* 111 */     if (direction == 5) {
/* 112 */       return EnumDirection.EAST;
/*     */     }
/*     */     
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumDirection getDirectionFacingStandard6(int metadata, boolean include_up_and_down) {
/* 120 */     EnumDirection direction = getDirectionFacingStandard6(metadata);
/* 121 */     return (!include_up_and_down && (direction == EnumDirection.DOWN || direction == EnumDirection.UP)) ? null : direction;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getMetadataForDirectionFacing(int paramInt, EnumDirection paramEnumDirection);
/*     */ 
/*     */   
/*     */   public final int getDefaultMetadataForDirectionFacing(EnumDirection direction) {
/* 129 */     if (this.are_maps_ready)
/*     */     {
/*     */       
/* 132 */       return this.default_metadata_for_direction_facing[direction.ordinal()];
/*     */     }
/*     */     
/* 135 */     for (int i = 0; i < this.direction_facing_for_metadata.length; i++) {
/*     */       
/* 137 */       if (this.direction_facing_for_metadata[i] == direction) {
/* 138 */         return i;
/*     */       }
/*     */     } 
/* 141 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean canFaceDirection(EnumDirection direction) {
/* 147 */     return (getDefaultMetadataForDirectionFacing(direction) >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean canFaceUp() {
/* 152 */     return this.can_face_up;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean canFaceDown() {
/* 157 */     return this.can_face_down;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 162 */     if (this.can_face_up || this.can_face_down) {
/*     */       
/* 164 */       double entity_foot_pos_y = entity.posY - entity.yOffset;
/* 165 */       int entity_foot_block_y = (int)Math.round(entity_foot_pos_y);
/* 166 */       int dy = y - entity_foot_block_y;
/*     */       
/* 168 */       if (dy < 0 && entity.rotationPitch > 55.0F && this.can_face_up)
/* 169 */         return getDefaultMetadataForDirectionFacing(EnumDirection.UP); 
/* 170 */       if (dy > 1 && entity.rotationPitch < -35.0F && this.can_face_down) {
/* 171 */         return getDefaultMetadataForDirectionFacing(EnumDirection.DOWN);
/*     */       }
/*     */     } 
/* 174 */     return getDefaultMetadataForDirectionFacing(entity.getDirectionFromYaw().getOpposite());
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
/*     */   public int getDefaultMetadata(World world, int x, int y, int z) {
/* 246 */     this.default_metadata_direction_order[0] = EnumDirection.WEST;
/* 247 */     this.default_metadata_direction_order[1] = EnumDirection.EAST;
/*     */ 
/*     */ 
/*     */     
/* 251 */     this.default_metadata_direction_order[2] = EnumDirection.NORTH;
/* 252 */     this.default_metadata_direction_order[3] = EnumDirection.SOUTH;
/* 253 */     this.default_metadata_direction_order[4] = EnumDirection.UP;
/* 254 */     this.default_metadata_direction_order[5] = EnumDirection.DOWN;
/*     */     
/* 256 */     Block[] blocks = new Block[6];
/* 257 */     int[] metadatas = new int[6];
/*     */     int i;
/* 259 */     for (i = 0; i < 6; i++) {
/*     */       
/* 261 */       EnumDirection direction = this.default_metadata_direction_order[i];
/*     */       
/* 263 */       if (canFaceDirection(direction)) {
/*     */         
/* 265 */         Block block = world.getBlock(x + direction.dx, y + direction.dy, z + direction.dz);
/*     */         
/* 267 */         if (block == null) {
/* 268 */           return getDefaultMetadataForDirectionFacing(direction);
/*     */         }
/* 270 */         blocks[i] = block;
/* 271 */         metadatas[i] = world.getBlockMetadata(x + direction.dx, y + direction.dy, z + direction.dz);
/*     */       } 
/*     */     } 
/*     */     
/* 275 */     for (i = 0; i < 6; i++) {
/*     */       
/* 277 */       EnumDirection direction = this.default_metadata_direction_order[i];
/*     */       
/* 279 */       if (canFaceDirection(direction))
/*     */       {
/* 281 */         if (!blocks[i].isSolid(metadatas[i])) {
/* 282 */           return getDefaultMetadataForDirectionFacing(direction);
/*     */         }
/*     */       }
/*     */     } 
/* 286 */     for (i = 0; i < 6; i++) {
/*     */       
/* 288 */       EnumDirection direction = this.default_metadata_direction_order[i];
/*     */       
/* 290 */       if (canFaceDirection(direction)) {
/*     */         
/* 292 */         int metadata = world.getBlockMetadata(x + direction.dx, y + direction.dy, z + direction.dz);
/*     */         
/* 294 */         if (!blocks[i].isStandardFormCube(metadata)) {
/* 295 */           return getDefaultMetadataForDirectionFacing(direction);
/*     */         }
/*     */       } 
/*     */     } 
/* 299 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getDefaultMetadataAdjustedForCoordBaseMode(EnumDirection direction_facing_in_coord_base_mode_2, int coord_base_mode) {
/* 305 */     return getMetadataForDirectionFacing(0, direction_facing_in_coord_base_mode_2.adjustForCoordBaseMode(coord_base_mode));
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumFace getFrontFace(int metadata) {
/* 310 */     return getDirectionFacing(metadata).getFace();
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumFace getBackFace(int metadata) {
/* 315 */     return getDirectionFacing(metadata).getOppositeFace();
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isAlignedWith(int metadata, Axis axis) {
/* 320 */     return getDirectionFacing(metadata).isAlignedWith(axis);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDirectional.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */