/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockBreakInfo
/*     */ {
/*     */   public World world;
/*     */   public int x;
/*     */   public int y;
/*     */   public int z;
/*     */   public int block_id;
/*     */   public Block block;
/*     */   public TileEntity tile_entity;
/*     */   public EnumBlockBreakReason reason;
/*     */   private int metadata;
/*     */   public int neighbor_block_id;
/*     */   public Block crushing_block;
/*     */   public BlockFluid flooding_block;
/*     */   public Entity responsible_entity;
/*     */   public ItemStack responsible_item_stack;
/*     */   public Explosion explosion;
/*     */   private boolean was_silk_harvested;
/*     */   public int damage;
/*     */   public int drop_x;
/*     */   public int drop_y;
/*     */   public int drop_z;
/*     */   private AxisAlignedBB drop_bounds;
/*  34 */   private static AxisAlignedBB drop_bounds_default = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D).scale(0.5D);
/*     */ 
/*     */   
/*     */   public BlockBreakInfo(World world, int x, int y, int z) {
/*  38 */     if (world.isRemote) {
/*     */       
/*  40 */       Minecraft.setErrorMessage("BlockBreakInfo: Why creating this object on client?", true);
/*  41 */       (new Exception()).printStackTrace();
/*     */     } 
/*     */     
/*  44 */     this.world = world;
/*  45 */     this.x = x;
/*  46 */     this.y = y;
/*  47 */     this.z = z;
/*  48 */     this.block_id = world.getBlockId(x, y, z);
/*  49 */     this.block = Block.blocksList[this.block_id];
/*  50 */     this.metadata = world.getBlockMetadata(x, y, z);
/*     */     
/*  52 */     this.tile_entity = world.getBlockTileEntity(x, y, z);
/*     */     
/*  54 */     this.drop_x = x;
/*  55 */     this.drop_y = y;
/*  56 */     this.drop_z = z;
/*     */     
/*  58 */     if (this.block == null) {
/*     */       
/*  60 */       this.drop_bounds = drop_bounds_default;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  65 */       this.drop_bounds = this.block.getSelectedBoundingBoxFromPool(world, x, y, z).translate(-x, -y, -z).scale(0.5D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockBreakInfo(int block_id, int metadata) {
/*  72 */     this.block_id = block_id;
/*  73 */     this.block = Block.getBlock(block_id);
/*  74 */     this.metadata = metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setMetadata(int metadata) {
/*  79 */     this.metadata = metadata;
/*     */ 
/*     */     
/*  82 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setBlock(Block block, int metadata) {
/*  88 */     this.block_id = block.blockID;
/*  89 */     this.block = block;
/*  90 */     this.metadata = metadata;
/*     */ 
/*     */     
/*  93 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setBlock(Block block, int metadata, int flags) {
/*  99 */     setBlock(block, metadata);
/*     */     
/* 101 */     this.world.setBlock(this.x, this.y, this.z, block.blockID, metadata, flags);
/*     */     
/* 103 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setWasNotLegal() {
/* 108 */     this.reason = EnumBlockBreakReason.was_not_legal;
/* 109 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setNeighborChanged(int neighbor_block_id) {
/* 114 */     this.reason = EnumBlockBreakReason.neighbor_changed;
/* 115 */     this.neighbor_block_id = neighbor_block_id;
/*     */     
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setReplaced() {
/* 122 */     this.reason = EnumBlockBreakReason.replaced;
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setFlooded(BlockFluid flooding_block) {
/* 128 */     this.reason = EnumBlockBreakReason.flooded;
/* 129 */     this.flooding_block = flooding_block;
/*     */     
/* 131 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setHarvestedBy(EntityLivingBase harvesting_entity) {
/* 136 */     this.reason = EnumBlockBreakReason.harvested;
/* 137 */     this.responsible_entity = harvesting_entity;
/* 138 */     this.responsible_item_stack = harvesting_entity.getHeldItemStack();
/*     */     
/* 140 */     if (this.block == null) {
/*     */       
/* 142 */       Minecraft.setErrorMessage("setHarvestedBy: block is null (" + harvesting_entity.getEntityName() + ")");
/* 143 */       Debug.printStackTrace();
/* 144 */       return this;
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
/* 156 */     this.was_silk_harvested = (harvesting_entity instanceof EntityLivingBase && harvesting_entity.getAsEntityLivingBase().canSilkHarvestBlock(this.block, this.metadata));
/*     */     
/* 158 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setPickedBy(EntityLivingBase picking_entity) {
/* 164 */     this.reason = EnumBlockBreakReason.picked;
/* 165 */     this.responsible_entity = picking_entity;
/*     */ 
/*     */     
/* 168 */     if (this.block == null) {
/*     */       
/* 170 */       Minecraft.setErrorMessage("setPickedBy: block is null (" + picking_entity.getEntityName() + ")");
/* 171 */       return this;
/*     */     } 
/*     */     
/* 174 */     return this;
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
/*     */   public BlockBreakInfo setTrampledBy(Entity trampling_entity) {
/* 193 */     this.reason = EnumBlockBreakReason.trampled;
/* 194 */     this.responsible_entity = trampling_entity;
/*     */     
/* 196 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setExploded(Explosion explosion) {
/* 201 */     this.reason = EnumBlockBreakReason.exploded;
/* 202 */     this.explosion = explosion;
/*     */     
/* 204 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setDroppedSelf() {
/* 209 */     this.reason = EnumBlockBreakReason.self_dropped;
/*     */     
/* 211 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setCrushed(Block crushing_block) {
/* 216 */     this.reason = EnumBlockBreakReason.crushed;
/* 217 */     this.crushing_block = crushing_block;
/*     */     
/* 219 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setEatenBy(Entity entity) {
/* 224 */     this.reason = EnumBlockBreakReason.eaten;
/* 225 */     this.responsible_entity = entity;
/*     */     
/* 227 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setCollidedWith(Entity entity) {
/* 232 */     this.reason = EnumBlockBreakReason.collided_with_entity;
/* 233 */     this.responsible_entity = entity;
/*     */     
/* 235 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setSilverfish(Entity entity) {
/* 240 */     this.reason = EnumBlockBreakReason.silverfish;
/* 241 */     this.responsible_entity = entity;
/*     */     
/* 243 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setDrought() {
/* 248 */     this.reason = EnumBlockBreakReason.drought;
/*     */     
/* 250 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setSnowedUpon() {
/* 255 */     this.reason = EnumBlockBreakReason.snowfall;
/*     */     
/* 257 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setDestroyedBy(Entity entity) {
/* 262 */     this.reason = EnumBlockBreakReason.destroyed;
/* 263 */     this.responsible_entity = entity;
/*     */     
/* 265 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setWindfall() {
/* 270 */     this.reason = EnumBlockBreakReason.windfall;
/*     */     
/* 272 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setOther() {
/* 277 */     this.reason = EnumBlockBreakReason.other;
/*     */     
/* 279 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadata() {
/* 285 */     return this.metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasNotLegal() {
/* 290 */     return (this.reason == EnumBlockBreakReason.was_not_legal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean wasReplaced() {
/* 300 */     return (this.reason == EnumBlockBreakReason.replaced);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasFlooded() {
/* 305 */     return (this.reason == EnumBlockBreakReason.flooded);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasHarvested() {
/* 310 */     return (this.reason == EnumBlockBreakReason.harvested);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasHarvestedByPlayer() {
/* 315 */     return (this.reason == EnumBlockBreakReason.harvested && this.responsible_entity instanceof EntityPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasPicked() {
/* 320 */     return (this.reason == EnumBlockBreakReason.picked);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasPickedByPlayer() {
/* 325 */     return (wasPicked() && this.responsible_entity instanceof EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean wasSilkHarvested() {
/* 332 */     return this.was_silk_harvested;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasTrampled() {
/* 337 */     return (this.reason == EnumBlockBreakReason.trampled);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasExploded() {
/* 342 */     return (this.reason == EnumBlockBreakReason.exploded);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasSelfDropped() {
/* 347 */     return (this.reason == EnumBlockBreakReason.self_dropped);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasCrushed() {
/* 352 */     return (this.reason == EnumBlockBreakReason.crushed);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasEaten() {
/* 357 */     return (this.reason == EnumBlockBreakReason.eaten);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasCollidedWithEntity() {
/* 362 */     return (this.reason == EnumBlockBreakReason.collided_with_entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasSilverfish() {
/* 367 */     return (this.reason == EnumBlockBreakReason.silverfish);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasDrought() {
/* 372 */     return (this.reason == EnumBlockBreakReason.drought);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasSnowedUpon() {
/* 377 */     return (this.reason == EnumBlockBreakReason.snowfall);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasWindfall() {
/* 382 */     return (this.reason == EnumBlockBreakReason.windfall);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLivingBase getHarvester() {
/* 388 */     return wasHarvested() ? (EntityLivingBase)this.responsible_entity : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getHarvesterItemStack() {
/* 393 */     return (getHarvester() == null) ? null : getHarvester().getHeldItemStack();
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getHarvesterItem() {
/* 398 */     return (getHarvesterItemStack() == null) ? null : getHarvesterItemStack().getItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHarvesterFortune() {
/* 403 */     return EnchantmentHelper.getFortuneModifier(getHarvester());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer getResponsiblePlayer() {
/* 408 */     return (this.responsible_entity instanceof EntityPlayer) ? (EntityPlayer)this.responsible_entity : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isResponsiblePlayerInCreativeMode() {
/* 413 */     return (getResponsiblePlayer() != null && (getResponsiblePlayer()).capabilities.isCreativeMode);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBlockHardness() {
/* 418 */     return this.block.getBlockHardness(this.metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockBreakReason getReason() {
/* 423 */     return this.reason;
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeGenBase getBiome() {
/* 428 */     return this.world.getBiomeGenForCoords(this.x, this.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setDamage(int damage) {
/* 433 */     this.damage = damage;
/* 434 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSoundEffectAtBlock(String sound, float volume, float pitch) {
/* 439 */     if (this.world.isRemote) {
/* 440 */       Minecraft.setErrorMessage("playSoundEffect: why calling this function on client?");
/*     */     }
/* 442 */     this.world.playSoundEffect((this.x + 0.5F), (this.y + 0.5F), (this.z + 0.5F), sound, volume, pitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsItself(boolean set_block_to_air) {
/* 447 */     int num_drops = this.block.dropBlockAsItself(this);
/*     */     
/* 449 */     if (set_block_to_air) {
/*     */       
/* 451 */       if (this.world.getBlock(this.x, this.y, this.z) != this.block) {
/* 452 */         Minecraft.setErrorMessage("dropBlockAsItself: Block mismatch");
/*     */       }
/* 454 */       this.world.setBlockToAir(this.x, this.y, this.z);
/*     */     } 
/*     */     
/* 457 */     return num_drops;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(boolean set_block_to_air) {
/* 462 */     int num_drops = this.block.dropBlockAsEntityItem(this);
/*     */     
/* 464 */     if (set_block_to_air) {
/*     */       
/* 466 */       if (this.world.getBlock(this.x, this.y, this.z) != this.block) {
/* 467 */         Minecraft.setErrorMessage("dropBlockAsItself: Block mismatch");
/*     */       }
/* 469 */       this.world.setBlockToAir(this.x, this.y, this.z);
/*     */     } 
/*     */     
/* 472 */     return num_drops;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBreakInfo setDropCoords(int x, int y, int z) {
/* 477 */     this.drop_x = x;
/* 478 */     this.drop_y = y;
/* 479 */     this.drop_z = z;
/*     */     
/* 481 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityItem createEntityItem(ItemStack item_stack) {
/* 488 */     Random random = this.world.rand;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 498 */     double pos_x = this.x + random.nextFloat() * (this.drop_bounds.maxX - this.drop_bounds.minX) + this.drop_bounds.minX;
/* 499 */     double pos_y = this.y + random.nextFloat() * (this.drop_bounds.maxY - this.drop_bounds.minY) + this.drop_bounds.minY;
/* 500 */     double pos_z = this.z + random.nextFloat() * (this.drop_bounds.maxZ - this.drop_bounds.minZ) + this.drop_bounds.minZ;
/*     */     
/* 502 */     return new EntityItem(this.world, pos_x, pos_y, pos_z, item_stack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk getChunkIfItExists() {
/* 508 */     return this.world.getChunkFromBlockCoordsIfItExists(this.x, this.z);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBreakInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */