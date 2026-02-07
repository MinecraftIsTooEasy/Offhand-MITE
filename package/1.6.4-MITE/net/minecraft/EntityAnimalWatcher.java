/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAnimalWatcher
/*     */   extends EntityMob
/*     */ {
/*     */   protected boolean is_destroying_block;
/*     */   protected int destroy_block_x;
/*     */   protected int destroy_block_y;
/*     */   protected int destroy_block_z;
/*     */   protected int destroy_block_progress;
/*  16 */   protected int destroy_block_cooloff = 40;
/*     */   
/*     */   protected int destroy_pause_ticks;
/*     */   
/*     */   public EntityAnimalWatcher(World world) {
/*  21 */     super(world);
/*     */     
/*  23 */     this.tasks.addTask(1, new EntityAIWatchAnimal(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHoldingItemThatPreventsDigging() {
/*  28 */     Item held_item = getHeldItem();
/*     */     
/*  30 */     return (held_item instanceof ItemSword || held_item instanceof ItemCudgel || held_item instanceof ItemScythe);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDiggingEnabled() {
/*  35 */     return !isHoldingItemThatPreventsDigging();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blockWillFall(int x, int y, int z) {
/*  40 */     Block block = this.worldObj.getBlock(x, y, z);
/*     */     
/*  42 */     return (block instanceof BlockFalling || block == Block.cactus || block instanceof BlockTorch || block == Block.snow);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void partiallyDestroyBlock() {
/*  47 */     int x = this.destroy_block_x;
/*  48 */     int y = this.destroy_block_y;
/*  49 */     int z = this.destroy_block_z;
/*     */     
/*  51 */     if (!canDestroyBlock(x, y, z, true)) {
/*     */       
/*  53 */       cancelBlockDestruction();
/*     */       
/*     */       return;
/*     */     } 
/*  57 */     refreshDespawnCounter(-400);
/*     */     
/*  59 */     World world = this.worldObj;
/*     */     
/*  61 */     Block block = world.getBlock(x, y, z);
/*     */ 
/*     */     
/*  64 */     if (block == Block.cactus && !isHoldingItemThatPreventsHandDamage()) {
/*  65 */       attackEntityFrom(new Damage(DamageSource.cactus, 1.0F));
/*     */     }
/*  67 */     if (++this.destroy_block_progress < 10) {
/*     */       
/*  69 */       this.is_destroying_block = true;
/*     */     }
/*     */     else {
/*     */       
/*  73 */       this.destroy_block_progress = -1;
/*     */       
/*  75 */       if (block.blockMaterial == Material.glass) {
/*  76 */         world.playAuxSFX(2001, x, y, z, block.blockID);
/*     */       }
/*  78 */       BlockBreakInfo info = (new BlockBreakInfo(world, x, y, z)).setHarvestedBy(this);
/*     */       
/*  80 */       block.dropBlockAsEntityItem(info);
/*     */       
/*  82 */       world.setBlockToAir(x, y, z);
/*     */ 
/*     */ 
/*     */       
/*  86 */       if (blockWillFall(x, y + 1, z)) {
/*     */         
/*  88 */         List entities = world.selectEntitiesWithinAABB(EntityLiving.class, this.boundingBox.expand(3.0D, 1.0D, 3.0D), new EntitySelectorEntityLiving(true, true));
/*     */         
/*  90 */         Iterator<EntityLiving> i = entities.iterator();
/*     */         
/*  92 */         while (i.hasNext()) {
/*     */           
/*  94 */           EntityLiving entity_living = i.next();
/*     */           
/*  96 */           EntityAIAttackOnCollide ai = (EntityAIAttackOnCollide)entity_living.getEntityAITask(EntityAIAttackOnCollide.class);
/*     */           
/*  98 */           if (ai != null) {
/*     */             
/* 100 */             if (ai.ticks_suppressed < 10) {
/* 101 */               ai.ticks_suppressed = 10;
/*     */             }
/* 103 */             if (ai.attackTick < 10) {
/* 104 */               ai.attackTick = 10;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 113 */       ItemStack item_stack = getHeldItemStack();
/*     */       
/* 115 */       if (item_stack != null) {
/* 116 */         item_stack.getItem().onBlockDestroyed(info);
/*     */       }
/* 118 */       this.is_destroying_block = false;
/*     */       
/* 120 */       Block block_above = world.getBlock(x, y + 1, z);
/*     */       
/* 122 */       if (block_above instanceof BlockFalling) {
/*     */         
/* 124 */         this.is_destroying_block = true;
/* 125 */         this.destroy_pause_ticks = 10;
/*     */       }
/* 127 */       else if (block_above != null && !blockWillFall(x, y + 1, z)) {
/*     */         
/* 129 */         if (y == getFootBlockPosY() && canDestroyBlock(x, y + 1, z, true)) {
/* 130 */           this.destroy_block_y++;
/*     */         } else {
/* 132 */           this.destroy_block_y--;
/*     */         } 
/* 134 */         this.is_destroying_block = true;
/* 135 */         this.destroy_pause_ticks = 10;
/*     */       }
/* 137 */       else if (y == getFootBlockPosY() + 1 && !world.isAirOrPassableBlock(getBlockPosX(), getBlockPosY() + 2, getBlockPosZ(), false) && canDestroyBlock(x, y - 1, z, true)) {
/*     */         
/* 139 */         this.is_destroying_block = true;
/* 140 */         this.destroy_pause_ticks = 10;
/*     */         
/* 142 */         this.destroy_block_y--;
/*     */       } 
/*     */       
/* 145 */       if (block_above instanceof BlockUnderminable) {
/* 146 */         ((BlockUnderminable)block_above).tryToFall(world, x, y + 1, z);
/*     */       }
/*     */     } 
/*     */     
/* 150 */     world.watchAnimal(this.entityId, x, y, z, this.destroy_block_progress);
/*     */ 
/*     */ 
/*     */     
/* 154 */     if (block.blockMaterial == Material.glass) {
/* 155 */       world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), Block.glass.stepSound.getPlaceSound(), Block.glass.stepSound.getVolume() + 2.0F, Block.glass.stepSound.getPitch() * 1.0F);
/*     */     } else {
/* 157 */       world.playAuxSFX(2001, x, y, z, block.blockID + (world.getBlockMetadata(x, y, z) << 12));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double getCenterPosYForBlockDestroying() {
/* 162 */     return this.posY + (this.height * 0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Vec3 getEyePosForBlockDestroying() {
/* 171 */     return getPrimaryPointOfAttack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Vec3 getAttackerLegPosForBlockDestroying() {
/* 181 */     Vec3Pool vec3_pool = this.worldObj.getWorldVec3Pool();
/*     */     
/* 183 */     return vec3_pool.getVecFromPool(this.posX, this.posY + (this.height * 0.25F), this.posZ);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3 getTargetEntityCenterPosForBlockDestroying(EntityLivingBase entity_living_base) {
/* 188 */     Vec3Pool vec3_pool = entity_living_base.worldObj.getWorldVec3Pool();
/* 189 */     return vec3_pool.getVecFromPool(entity_living_base.posX, entity_living_base.posY + (entity_living_base.height / 2.0F), entity_living_base.posZ);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasDownwardsDiggingTool() {
/* 194 */     ItemStack held_item = getHeldItemStack();
/*     */     
/* 196 */     return (held_item != null && held_item.getItem() instanceof ItemShovel);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlockClaimedByAnother(int x, int y, int z) {
/* 201 */     AxisAlignedBB bb = AxisAlignedBB.getAABBPool().getAABB(this.posX - 4.0D, this.posY - 4.0D, this.posZ - 4.0D, this.posX + 4.0D, this.posY + 4.0D, this.posZ + 4.0D);
/*     */     
/* 203 */     List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bb);
/*     */     
/* 205 */     Iterator<Entity> i = entities.iterator();
/*     */     
/* 207 */     while (i.hasNext()) {
/*     */       
/* 209 */       Entity entity = i.next();
/*     */       
/* 211 */       if (entity instanceof EntityAnimalWatcher) {
/*     */         
/* 213 */         EntityAnimalWatcher digger = (EntityAnimalWatcher)entity;
/*     */         
/* 215 */         if (digger.is_destroying_block && digger.destroy_block_x == x && digger.destroy_block_y == y && digger.destroy_block_z == z) {
/* 216 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 220 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canDestroyBlock(int x, int y, int z, boolean check_clipping) {
/* 225 */     if (isHoldingItemThatPreventsDigging()) {
/* 226 */       return false;
/*     */     }
/* 228 */     int foot_y = getFootBlockPosY();
/*     */     
/* 230 */     if (y < foot_y && !hasDownwardsDiggingTool())
/* 231 */       return false; 
/* 232 */     if (y > foot_y + 1) {
/* 233 */       return false;
/*     */     }
/* 235 */     World world = this.worldObj;
/*     */     
/* 237 */     if (World.getDistanceSqFromDeltas(this.posX - (x + 0.5F), getCenterPosYForBlockDestroying() - (y + 0.5F), this.posZ - (z + 0.5F)) > 3.25D) {
/* 238 */       return false;
/*     */     }
/* 240 */     if (check_clipping) {
/*     */       
/* 242 */       RaycastCollision rc = world.getBlockCollisionForPhysicalReach(getEyePosForBlockDestroying(), world.getBlockCenterPos(x, y, z));
/*     */       
/* 244 */       if (rc != null)
/*     */       {
/* 246 */         if (rc.isEntity() || (rc.isBlock() && (rc.block_hit_x != x || rc.block_hit_y != y || rc.block_hit_z != z))) {
/*     */           
/* 248 */           rc = world.getBlockCollisionForPhysicalReach(getAttackerLegPosForBlockDestroying(), world.getBlockCenterPos(x, y, z));
/*     */           
/* 250 */           if (rc != null)
/*     */           {
/* 252 */             if (rc.isEntity() || (rc.isBlock() && (rc.block_hit_x != x || rc.block_hit_y != y || rc.block_hit_z != z))) {
/* 253 */               return false;
/*     */             }
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 259 */     Block block = Block.blocksList[world.getBlockId(x, y, z)];
/*     */     
/* 261 */     if (block == null) {
/* 262 */       return false;
/*     */     }
/* 264 */     if (block.blockMaterial.isLiquid()) {
/* 265 */       return false;
/*     */     }
/* 267 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     if (this instanceof EntityEarthElemental) {
/*     */       
/* 276 */       EntityEarthElemental entity_earth_elemental = (EntityEarthElemental)this;
/*     */       
/* 278 */       if (block.getMinHarvestLevel(metadata) <= entity_earth_elemental.getBlockHarvestLevel()) {
/* 279 */         return true;
/*     */       }
/*     */     } 
/* 282 */     Item held_item = (getHeldItemStack() == null) ? null : getHeldItemStack().getItem();
/*     */     
/* 284 */     boolean has_effective_tool = (held_item instanceof ItemTool && ((ItemTool)held_item).getStrVsBlock(block, metadata) > 0.0F);
/*     */ 
/*     */     
/* 287 */     if (!block.blockMaterial.requiresTool(block, metadata) || (isFrenzied() && block.getMinHarvestLevel(metadata) < 2) || has_effective_tool || block == Block.sand || block == Block.dirt || block == Block.grass || block == Block.gravel || block == Block.blockSnow || block == Block.tilledField || block == Block.blockClay || block == Block.leaves || block == Block.cloth || (block == Block.cactus && (isEntityInvulnerable() || isEntityUndead())) || block == Block.sponge || block instanceof BlockPumpkin || block instanceof BlockMelon || block == Block.mycelium || block == Block.hay || block == Block.thinGlass) {
/* 288 */       return !isBlockClaimedByAnother(x, y, z);
/*     */     }
/* 290 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean setBlockToDig(int x, int y, int z, boolean check_clipping) {
/* 295 */     if (!canDestroyBlock(x, y, z, check_clipping)) {
/* 296 */       return false;
/*     */     }
/* 298 */     this.is_destroying_block = true;
/*     */     
/* 300 */     if (x == this.destroy_block_x && y == this.destroy_block_y && z == this.destroy_block_z) {
/* 301 */       return true;
/*     */     }
/* 303 */     if (y == getFootBlockPosY() + 1 && this.worldObj.getBlock(x, y, z) == Block.cactus && canDestroyBlock(x, y - 1, z, check_clipping)) {
/* 304 */       y--;
/*     */     }
/* 306 */     this.destroy_block_progress = -1;
/*     */     
/* 308 */     this.destroy_block_x = x;
/* 309 */     this.destroy_block_y = y;
/* 310 */     this.destroy_block_z = z;
/*     */     
/* 312 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void cancelBlockDestruction() {
/* 317 */     if (!this.is_destroying_block) {
/*     */       return;
/*     */     }
/*     */     
/* 321 */     this.worldObj.watchAnimal(this.entityId, this.destroy_block_x, this.destroy_block_y, this.destroy_block_z, -1);
/*     */     
/* 323 */     this.is_destroying_block = false;
/* 324 */     this.destroy_block_progress = -1;
/* 325 */     this.destroy_block_cooloff = 40;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getCooloffForBlock() {
/* 330 */     Block block = Block.blocksList[this.worldObj.getBlockId(this.destroy_block_x, this.destroy_block_y, this.destroy_block_z)];
/*     */     
/* 332 */     if (block == null) {
/* 333 */       return 40;
/*     */     }
/* 335 */     int cooloff = (int)(300.0F * this.worldObj.getBlockHardness(this.destroy_block_x, this.destroy_block_y, this.destroy_block_z));
/*     */     
/* 337 */     if (isFrenzied()) {
/* 338 */       cooloff /= 2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 343 */     if (this instanceof EntityEarthElemental) {
/*     */       
/* 345 */       EntityEarthElemental elemental = (EntityEarthElemental)this;
/*     */       
/* 347 */       if (elemental.isNormalClay()) {
/* 348 */         cooloff /= 4;
/* 349 */       } else if (elemental.isHardenedClay()) {
/* 350 */         cooloff /= 6;
/*     */       } else {
/* 352 */         cooloff /= 8;
/*     */       } 
/*     */     } 
/* 355 */     if (getHeldItemStack() == null) {
/* 356 */       return cooloff;
/*     */     }
/* 358 */     Item held_item = getHeldItemStack().getItem();
/*     */     
/* 360 */     if (held_item instanceof ItemTool) {
/*     */       
/* 362 */       ItemTool item_tool = (ItemTool)held_item;
/* 363 */       cooloff = (int)(cooloff / (1.0F + item_tool.getStrVsBlock(block, this.worldObj.getBlockMetadata(this.destroy_block_x, this.destroy_block_y, this.destroy_block_z)) * 0.5F));
/*     */     } 
/*     */     
/* 366 */     return cooloff;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 371 */     if (this.is_destroying_block) {
/*     */       
/* 373 */       if (this.destroy_pause_ticks == 0) {
/*     */         
/* 375 */         getLookHelper().setLookPosition((this.destroy_block_x + 0.5F), (this.destroy_block_y + 0.5F), (this.destroy_block_z + 0.5F), 10.0F, getVerticalFaceSpeed());
/*     */         
/* 377 */         if (!canDestroyBlock(this.destroy_block_x, this.destroy_block_y, this.destroy_block_z, true)) {
/* 378 */           cancelBlockDestruction();
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 383 */       this.destroy_block_cooloff = 40;
/* 384 */       this.destroy_block_progress = -1;
/*     */     } 
/*     */     
/* 387 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 392 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/* 394 */     if (this.is_destroying_block) {
/*     */       
/* 396 */       par1NBTTagCompound.setBoolean("is_destroying_block", this.is_destroying_block);
/* 397 */       par1NBTTagCompound.setInteger("destroy_block_x", this.destroy_block_x);
/* 398 */       par1NBTTagCompound.setInteger("destroy_block_y", this.destroy_block_y);
/* 399 */       par1NBTTagCompound.setInteger("destroy_block_z", this.destroy_block_z);
/* 400 */       par1NBTTagCompound.setInteger("destroy_block_progress", this.destroy_block_progress);
/* 401 */       par1NBTTagCompound.setInteger("destroy_block_cooloff", this.destroy_block_cooloff);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 407 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/* 409 */     if (par1NBTTagCompound.hasKey("is_destroying_block")) {
/*     */       
/* 411 */       this.is_destroying_block = par1NBTTagCompound.getBoolean("is_destroying_block");
/* 412 */       this.destroy_block_x = par1NBTTagCompound.getInteger("destroy_block_x");
/* 413 */       this.destroy_block_y = par1NBTTagCompound.getInteger("destroy_block_y");
/* 414 */       this.destroy_block_z = par1NBTTagCompound.getInteger("destroy_block_z");
/* 415 */       this.destroy_block_progress = par1NBTTagCompound.getInteger("destroy_block_progress");
/* 416 */       this.destroy_block_cooloff = par1NBTTagCompound.getInteger("destroy_block_cooloff");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource par1DamageSource) {
/* 422 */     cancelBlockDestruction();
/* 423 */     super.onDeath(par1DamageSource);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAnimalWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */