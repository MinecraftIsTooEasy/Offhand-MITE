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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RaycastCollision
/*     */ {
/*     */   public final Raycast raycast;
/*     */   public final World world;
/*     */   private final Object object_hit;
/*     */   public int block_hit_x;
/*     */   public int block_hit_y;
/*     */   public int block_hit_z;
/*     */   public float block_hit_offset_x;
/*     */   public float block_hit_offset_y;
/*     */   public float block_hit_offset_z;
/*     */   public int block_hit_metadata;
/*     */   public int neighbor_block_x;
/*     */   public int neighbor_block_y;
/*     */   public int neighbor_block_z;
/*     */   public final EnumFace face_hit;
/*     */   public final Vec3 position_hit;
/*     */   
/*     */   public RaycastCollision(Raycast raycast, Entity entity_hit, AABBIntercept intercept) {
/*  41 */     this.raycast = raycast.setHasProducedCollisions();
/*  42 */     this.world = entity_hit.worldObj;
/*     */     
/*  44 */     this.object_hit = entity_hit;
/*     */     
/*  46 */     this.face_hit = intercept.face_hit;
/*  47 */     this.position_hit = intercept.position_hit;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RaycastCollision(Raycast raycast, int x, int y, int z, EnumFace face_hit, Vec3 position_hit) {
/*  53 */     this.raycast = raycast.setHasProducedCollisions();
/*  54 */     this.world = raycast.getWorld();
/*     */ 
/*     */ 
/*     */     
/*  58 */     Block block = this.world.getBlock(x, y, z);
/*  59 */     this.object_hit = block;
/*  60 */     this.block_hit_metadata = this.world.getBlockMetadata(x, y, z);
/*  61 */     this.block_hit_x = x;
/*  62 */     this.block_hit_y = y;
/*  63 */     this.block_hit_z = z;
/*  64 */     this.face_hit = face_hit;
/*  65 */     this.position_hit = position_hit.myVec3LocalPool.getVecFromPool(position_hit.xCoord, position_hit.yCoord, position_hit.zCoord);
/*     */     
/*  67 */     this.block_hit_offset_x = (float)(position_hit.xCoord - x);
/*  68 */     this.block_hit_offset_y = (float)(position_hit.yCoord - y);
/*  69 */     this.block_hit_offset_z = (float)(position_hit.zCoord - z);
/*     */ 
/*     */ 
/*     */     
/*  73 */     if (face_hit != null) {
/*     */       
/*  75 */       if (face_hit == EnumFace.BOTTOM) {
/*  76 */         y--;
/*  77 */       } else if (face_hit == EnumFace.TOP) {
/*  78 */         y++;
/*  79 */       } else if (face_hit == EnumFace.NORTH) {
/*  80 */         z--;
/*  81 */       } else if (face_hit == EnumFace.SOUTH) {
/*  82 */         z++;
/*  83 */       } else if (face_hit == EnumFace.WEST) {
/*  84 */         x--;
/*  85 */       } else if (face_hit == EnumFace.EAST) {
/*  86 */         x++;
/*     */       } 
/*  88 */       this.neighbor_block_x = x;
/*  89 */       this.neighbor_block_y = y;
/*  90 */       this.neighbor_block_z = z;
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
/*     */   public RaycastCollision(Entity entity_hit) {
/* 137 */     this.world = entity_hit.worldObj;
/*     */     
/* 139 */     this.object_hit = entity_hit;
/* 140 */     this.face_hit = null;
/* 141 */     this.position_hit = entity_hit.getCenterPoint();
/*     */     
/* 143 */     this.raycast = new Raycast(this.world, this.position_hit, this.position_hit);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlock() {
/* 148 */     return this.object_hit instanceof Block;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolidBlock() {
/* 153 */     Block block = getBlockHit();
/*     */     
/* 155 */     return (block != null && block.isSolid(this.block_hit_metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLiquidBlock() {
/* 160 */     Block block = getBlockHit();
/*     */     
/* 162 */     return (block != null && block.isLiquid());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlockAt(int x, int y, int z) {
/* 168 */     if (!isBlock()) {
/* 169 */       return false;
/*     */     }
/* 171 */     return (x == this.block_hit_x && y == this.block_hit_y && z == this.block_hit_z);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntity() {
/* 176 */     return this.object_hit instanceof Entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlockHit() {
/* 181 */     if (isBlock()) {
/* 182 */       return (Block)this.object_hit;
/*     */     }
/*     */     
/* 185 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockHitID() {
/* 190 */     if (isBlock()) {
/* 191 */       return (getBlockHit()).blockID;
/*     */     }
/* 193 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getBlockHitMaterial() {
/* 198 */     if (isBlock()) {
/* 199 */       return (getBlockHit()).blockMaterial;
/*     */     }
/* 201 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getNeighborOfBlockHit() {
/* 206 */     if (isBlock()) {
/* 207 */       return this.world.getBlock(this.neighbor_block_x, this.neighbor_block_y, this.neighbor_block_z);
/*     */     }
/* 209 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNeighborOfBlockHitID() {
/* 214 */     if (isBlock()) {
/* 215 */       return this.world.getBlockId(this.neighbor_block_x, this.neighbor_block_y, this.neighbor_block_z);
/*     */     }
/* 217 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNeighborOfBlockHitMetadata() {
/* 222 */     if (isBlock()) {
/* 223 */       return this.world.getBlockMetadata(this.neighbor_block_x, this.neighbor_block_y, this.neighbor_block_z);
/*     */     }
/* 225 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getNeighborOfBlockHitMaterial() {
/* 230 */     if (isBlock()) {
/* 231 */       return this.world.getBlockMaterial(this.neighbor_block_x, this.neighbor_block_y, this.neighbor_block_z);
/*     */     }
/* 233 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getEntityHit() {
/* 238 */     if (isEntity()) {
/* 239 */       return (Entity)this.object_hit;
/*     */     }
/*     */     
/* 242 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockHitFullWaterBlock(boolean include_moving_water) {
/* 247 */     if (isBlock()) {
/* 248 */       return BlockFluid.isFullWaterBlock(getBlockHit(), this.block_hit_metadata, include_moving_water);
/*     */     }
/* 250 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockHitFullLavaBlock(boolean include_moving_lava) {
/* 255 */     if (isBlock()) {
/* 256 */       return BlockFluid.isFullLavaBlock(getBlockHit(), this.block_hit_metadata, include_moving_lava);
/*     */     }
/* 258 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockHitReplaceableBy(Block block, int metadata) {
/* 263 */     if (isBlock()) {
/* 264 */       return getBlockHit().canBeReplacedBy(this.block_hit_metadata, block, metadata);
/*     */     }
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setBlockHitToAir() {
/* 272 */     if (this.world.isRemote) {
/* 273 */       Minecraft.setErrorMessage("setBlockHitToAir: why calling this on client?");
/*     */     }
/* 275 */     return this.world.setBlockToAir(this.block_hit_x, this.block_hit_y, this.block_hit_z);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNeighborAirBlock() {
/* 280 */     if (isBlock()) {
/* 281 */       return this.world.isAirBlock(this.neighbor_block_x, this.neighbor_block_y, this.neighbor_block_z);
/*     */     }
/* 283 */     Minecraft.setErrorMessage("isNeighborAirBlock: object hit is not block");
/* 284 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setNeighborBlock(Block block) {
/* 290 */     if (this.world.isRemote) {
/* 291 */       Minecraft.setErrorMessage("setNeighborBlock: why calling this on client?");
/*     */     }
/* 293 */     return this.world.setBlock(this.neighbor_block_x, this.neighbor_block_y, this.neighbor_block_z, (block == null) ? 0 : block.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSoundAtNeighborBlock(String name, float volume, float pitch) {
/* 299 */     if (this.world.isRemote) {
/* 300 */       Minecraft.setErrorMessage("playSoundAtNeighborBlock: not meant to be called on client (" + name + ")");
/*     */     }
/* 302 */     this.world.playSoundAtBlock(this.neighbor_block_x, this.neighbor_block_y, this.neighbor_block_z, name, volume, pitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlayerMineBlockHit(EntityPlayer player) {
/* 307 */     if (isBlock()) {
/* 308 */       return this.world.canMineBlock(player, this.block_hit_x, this.block_hit_y, this.block_hit_z);
/*     */     }
/* 310 */     Minecraft.setErrorMessage("canPlayerMineBlockHit: object hit is not block");
/* 311 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlayerEditBlockHit(EntityPlayer player, ItemStack item_stack) {
/* 316 */     if (isBlock()) {
/* 317 */       return player.canPlayerEdit(this.block_hit_x, this.block_hit_y, this.block_hit_z, item_stack);
/*     */     }
/* 319 */     Minecraft.setErrorMessage("canPlayerEditBlockHit: object hit is not block");
/* 320 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlayerMineNeighborOfBlockHit(EntityPlayer player) {
/* 325 */     if (isBlock()) {
/* 326 */       return this.world.canMineBlock(player, this.neighbor_block_x, this.neighbor_block_y, this.neighbor_block_z);
/*     */     }
/* 328 */     Minecraft.setErrorMessage("canPlayerMineNeighborOfBlockHit: object hit is not block");
/* 329 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlayerEditNeighborOfBlockHit(EntityPlayer player, ItemStack item_stack) {
/* 334 */     if (isBlock()) {
/* 335 */       return player.canPlayerEdit(this.neighbor_block_x, this.neighbor_block_y, this.neighbor_block_z, item_stack);
/*     */     }
/* 337 */     Minecraft.setErrorMessage("canPlayerEditNeighborOfBlockHit: object hit is not block");
/* 338 */     return false;
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
/*     */   public double getDistanceFromOriginToCollisionPoint() {
/* 358 */     return this.raycast.getOrigin().distanceTo(this.position_hit);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 363 */     if (isEntity())
/*     */     {
/* 365 */       return getEntityHit().getEntityName(); } 
/* 366 */     if (isBlock()) {
/* 367 */       return getBlockHit().getLocalizedName() + " @ " + this.block_hit_x + "," + this.block_hit_y + "," + this.block_hit_z;
/*     */     }
/* 369 */     return "Raycast hit other?";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RaycastCollision.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */