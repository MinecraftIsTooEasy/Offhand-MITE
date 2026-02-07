/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityEarthElemental
/*     */   extends EntityAnimalWatcher
/*     */ {
/*     */   private static final int MAGMA_BIT = 256;
/*  12 */   public static final int STONE_NORMAL = getType(Block.stone, false);
/*  13 */   public static final int STONE_MAGMA = getType(Block.stone, true);
/*  14 */   public static final int OBSIDIAN_NORMAL = getType(Block.obsidian, false);
/*  15 */   public static final int OBSIDIAN_MAGMA = getType(Block.obsidian, true);
/*  16 */   public static final int NETHERRACK_NORMAL = getType(Block.netherrack, false);
/*  17 */   public static final int NETHERRACK_MAGMA = getType(Block.netherrack, true);
/*  18 */   public static final int END_STONE_NORMAL = getType(Block.whiteStone, false);
/*  19 */   public static final int END_STONE_MAGMA = getType(Block.whiteStone, true);
/*  20 */   public static final int CLAY_NORMAL = getType(Block.blockClay, false);
/*  21 */   public static final int CLAY_HARDENED = getType(Block.hardenedClay, false);
/*     */ 
/*     */   
/*  24 */   private int heat = 0;
/*     */   
/*     */   private int data_object_id_type;
/*     */   
/*     */   private int ticks_until_next_fizz_sound;
/*     */ 
/*     */   
/*     */   public EntityEarthElemental(World world) {
/*  32 */     super(world);
/*     */     
/*  34 */     getNavigator().setBreakDoors(true);
/*  35 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  36 */     this.tasks.addTask(1, new EntityAIBreakDoor(this));
/*  37 */     this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  38 */     this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  39 */     this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*     */     
/*  41 */     this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
/*  42 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  43 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*  44 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
/*  45 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  46 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getType(Block block, boolean is_magma) {
/*  52 */     return block.blockID + (is_magma ? 256 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  57 */     super.applyEntityAttributes();
/*     */     
/*  59 */     setEntityAttribute(SharedMonsterAttributes.followRange, 20.0D);
/*  60 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.20000000298023224D);
/*  61 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 12.0D);
/*     */     
/*  63 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 30.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getNaturalDefense() {
/*  71 */     return 4.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getNaturalDefense(DamageSource damage_source) {
/*  77 */     return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : getNaturalDefense());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockHarvestLevel() {
/*  83 */     return getBlock().getMinHarvestLevel(0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  88 */     super.entityInit();
/*     */     
/*  90 */     this.data_object_id_type = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Short((short)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  95 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/*  97 */     par1NBTTagCompound.setShort("type", (short)getType());
/*  98 */     par1NBTTagCompound.setShort("heat", (short)this.heat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 103 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/* 105 */     setType(par1NBTTagCompound.getShort("type"));
/* 106 */     this.heat = par1NBTTagCompound.getShort("heat");
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 111 */     return (isInNether() || super.isValidLightLevel());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(int x, int y, int z) {
/* 116 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlockBelow() {
/* 121 */     return this.worldObj.getBlock(getBlockPosX(), getFootBlockPosY() - 1, getBlockPosZ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidBlock(Block block) {
/* 127 */     return (block == Block.stone || block == Block.obsidian || block == Block.netherrack || block == Block.whiteStone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 134 */     return (isValidBlock(getBlockBelow()) && super.getCanSpawnHere(perform_light_check));
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
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
/* 165 */     setTypeForBlock(getBlockBelow(), (getBlockMaterialAtFeet() == Material.lava || (isClay() && getBlockMaterialAtFeet() == Material.fire)));
/*     */     
/* 167 */     return super.onSpawnWithEgg(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int setType(int type) {
/* 173 */     this.dataWatcher.updateObject(this.data_object_id_type, Short.valueOf((short)type));
/* 174 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 180 */     return this.dataWatcher.getWatchableObjectShort(this.data_object_id_type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTypeForBlock(Block block, boolean heated) {
/* 186 */     setType((block == Block.whiteStone) ? END_STONE_NORMAL : ((block == Block.netherrack) ? NETHERRACK_NORMAL : ((block == Block.obsidian) ? OBSIDIAN_NORMAL : STONE_NORMAL)));
/*     */     
/* 188 */     if (heated) {
/* 189 */       convertToMagma();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isMagma() {
/* 194 */     if (onServer()) {
/* 195 */       return (this.heat >= 100);
/*     */     }
/* 197 */     return BitHelper.isBitSet(getType(), 256);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClay() {
/* 202 */     return (getClass() == EntityClayGolem.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNormalClay() {
/* 207 */     return (isClay() && !isHardenedClay());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHardenedClay() {
/* 212 */     if (!isClay()) {
/* 213 */       return false;
/*     */     }
/* 215 */     if (onServer() && this.heat >= 100) {
/* 216 */       return true;
/*     */     }
/* 218 */     return (getType() == CLAY_HARDENED);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isImmuneTo(DamageSource damage_source) {
/* 223 */     if (isNormalClay()) {
/* 224 */       return super.isImmuneTo(damage_source);
/*     */     }
/* 226 */     if (damage_source == DamageSource.fall) {
/* 227 */       return false;
/*     */     }
/* 229 */     if (damage_source.isMelee() && damage_source.getResponsibleEntity() instanceof EntityIronGolem) {
/* 230 */       return false;
/*     */     }
/* 232 */     ItemStack item_stack = damage_source.getItemAttackedWith();
/*     */     
/* 234 */     if (item_stack != null && item_stack.getItem() instanceof ItemTool && item_stack.getItemAsTool().isEffectiveAgainstBlock(getBlock(), 0)) {
/* 235 */       return false;
/*     */     }
/* 237 */     return !damage_source.isExplosion();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAIEnabled() {
/* 247 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void convertToMagma() {
/* 252 */     this.heat = 1000;
/*     */     
/* 254 */     if (isClay()) {
/*     */       
/* 256 */       if (getType() != setType(CLAY_HARDENED)) {
/* 257 */         entityFX(EnumEntityFX.smoke);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 262 */     if (getType() != setType(getType() | 0x100)) {
/* 263 */       entityFX(EnumEntityFX.smoke);
/*     */     }
/*     */   }
/*     */   
/*     */   public void convertToNormal(boolean steam) {
/* 268 */     this.heat = 0;
/*     */     
/* 270 */     if (isClay()) {
/*     */       
/* 272 */       Debug.setErrorMessage("convertToNormal: Why called for Clay Golem?");
/*     */       
/*     */       return;
/*     */     } 
/* 276 */     if (getType() != setType(BitHelper.clearBit(getType(), 256)) && steam) {
/* 277 */       causeQuenchEffect();
/*     */     }
/*     */   }
/*     */   
/*     */   public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {
/* 282 */     super.onStruckByLightning(par1EntityLightningBolt);
/*     */     
/* 284 */     if (!isMagma() && !isHardenedClay()) {
/* 285 */       convertToMagma();
/* 286 */     } else if (this.heat < 1000) {
/* 287 */       this.heat = 1000;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onLivingUpdate() {
/* 292 */     super.onLivingUpdate();
/*     */     
/* 294 */     if (getClass() == EntityClayGolem.class) {
/*     */       
/* 296 */       if (getType() != CLAY_NORMAL && getType() != CLAY_HARDENED) {
/* 297 */         Debug.setErrorMessage("onLivingUpdate: EntityClayGolem has invalid type: " + getType());
/*     */       
/*     */       }
/*     */     }
/* 301 */     else if (getType() == CLAY_NORMAL || getType() == CLAY_HARDENED) {
/* 302 */       Debug.setErrorMessage("onLivingUpdate: EntityEarthElemental has invalid type: " + getType());
/*     */     } 
/*     */     
/* 305 */     if (onClient()) {
/*     */       
/* 307 */       if (!this.inWater && isMagma() && isInPrecipitation()) {
/* 308 */         spawnSteamParticles(1);
/*     */       }
/* 310 */     } else if (this.inWater) {
/*     */       
/* 312 */       if (isMagma()) {
/* 313 */         convertToNormal(true);
/*     */       }
/* 315 */       this.heat = 0;
/*     */     }
/*     */     else {
/*     */       
/* 319 */       if (handleLavaMovement() || (isClay() && isInFire())) {
/*     */         
/* 321 */         if (this.heat < 1000 && ++this.heat == 100) {
/* 322 */           convertToMagma();
/*     */         }
/* 324 */       } else if (this.heat > 0) {
/*     */         
/* 326 */         boolean was_magma = isMagma();
/*     */         
/* 328 */         int cooling = 1;
/*     */         
/* 330 */         if (isInPrecipitation()) {
/* 331 */           cooling++;
/*     */         }
/* 333 */         int x = getBlockPosX();
/* 334 */         int y = getFootBlockPosY();
/* 335 */         int z = getBlockPosZ();
/*     */         
/* 337 */         if (this.worldObj.isFreezing(x, z)) {
/* 338 */           cooling++;
/*     */         }
/* 340 */         if (this.worldObj.getBlockMaterial(x, y, z).isFreezing()) {
/* 341 */           cooling++;
/*     */         }
/* 343 */         if (this.worldObj.getBlockMaterial(x, --y, z).isFreezing()) {
/* 344 */           cooling++;
/*     */         }
/* 346 */         this.heat -= cooling;
/*     */         
/* 348 */         if (this.heat < 0) {
/* 349 */           this.heat = 0;
/*     */         }
/* 351 */         if (was_magma && !isMagma()) {
/* 352 */           convertToNormal(isInPrecipitation());
/*     */         }
/*     */       } 
/* 355 */       if (isMagma())
/*     */       {
/* 357 */         if (isInPrecipitation())
/*     */         {
/* 359 */           if (--this.ticks_until_next_fizz_sound <= 0) {
/*     */             
/* 361 */             playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/* 362 */             this.ticks_until_next_fizz_sound = this.rand.nextInt(7) + 2;
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean preysUpon(Entity entity) {
/* 371 */     return entity instanceof EntityVillager;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 376 */     swingArm();
/*     */     
/* 378 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*     */     
/* 380 */     if (result == null || result.entityWasDestroyed()) {
/* 381 */       return result;
/*     */     }
/* 383 */     if (result.entityWasNegativelyAffected() && isMagma() && getRNG().nextFloat() < 0.4F) {
/* 384 */       target.setFire(1 + this.rand.nextInt(8));
/*     */     }
/* 386 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlock() {
/* 391 */     return Block.getBlock(getType() & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 396 */     Block block = getBlock();
/*     */     
/* 398 */     return (block == Block.stone) ? Block.cobblestone.blockID : block.blockID;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 403 */     dropItem(getDropItemId(), 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 408 */     return super.getExperienceValue() * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 413 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean healsWithTime() {
/* 418 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityBiologicallyAlive() {
/* 423 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 428 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 433 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPushedByWater() {
/* 443 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean breathesAir() {
/* 448 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/* 453 */     int brightness = super.getBrightnessForRender(par1);
/*     */     
/* 455 */     if (!isMagma()) {
/* 456 */       return brightness;
/*     */     }
/* 458 */     int blocklight = brightness & 0xFF;
/* 459 */     int skylight = brightness >> 16;
/*     */     
/* 461 */     blocklight = Math.max(blocklight, 80);
/* 462 */     skylight = Math.max(skylight, 80);
/*     */     
/* 464 */     return skylight << 16 | blocklight;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 469 */     return "mob.irongolem.hit";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 474 */     return "mob.irongolem.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 479 */     super.playStepSound(par1, par2, par3, par4);
/* 480 */     playSound("mob.irongolem.walk", 0.25F, 1.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityEarthElemental.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */