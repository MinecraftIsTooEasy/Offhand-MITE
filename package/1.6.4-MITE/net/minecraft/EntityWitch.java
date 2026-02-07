/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class EntityWitch
/*     */   extends EntityMob
/*     */   implements IRangedAttackMob {
/*  11 */   private static final UUID field_110184_bp = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
/*  12 */   private static final AttributeModifier field_110185_bq = (new AttributeModifier(field_110184_bp, "Drinking speed penalty", -0.25D, 0)).setSaved(false);
/*     */ 
/*     */ 
/*     */   
/*  16 */   private static final int[] witchDrops = new int[] { Item.glowstone.itemID, Item.sugar.itemID, Item.redstone.itemID, Item.spiderEye.itemID, Item.glassBottle.itemID, Item.gunpowder.itemID, Item.stick.itemID, Item.stick.itemID, Item.knifeFlint.itemID, Item.ironNugget.itemID, Item.seeds.itemID, Item.pumpkinSeeds.itemID, Item.carrot.itemID, Item.potato.itemID, Item.onion.itemID, Block.plantYellow.blockID, Block.plantRed.blockID, Item.potion.itemID };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int witchAttackTimer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityLivingBase summon_wolf_target;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int summon_wolf_countdown;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean has_summoned_wolves;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int curse_random_seed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityWitch(World par1World) {
/*  52 */     super(par1World);
/*  53 */     getNavigator().setAvoidsWater(true);
/*  54 */     this.tasks.addTask(1, new EntityAISwimming(this));
/*  55 */     this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
/*  56 */     this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
/*  57 */     this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  58 */     this.tasks.addTask(3, new EntityAILookIdle(this));
/*  59 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
/*  60 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */     
/*  62 */     this.tasks.addTask(3, new EntityAIWanderBackToSpawnPoint(this, 1.0F, true));
/*  63 */     this.tasks.addTask(3, new EntityAIWitchRoaming(this));
/*     */     
/*  65 */     if (par1World != null && !par1World.isRemote) {
/*  66 */       this.curse_random_seed = (new Random()).nextInt();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void entityInit() {
/*  71 */     super.entityInit();
/*  72 */     getDataWatcher().addObject(21, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  90 */     return "imported.mob.witch.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  99 */     return "imported.mob.witch.death";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLongDistanceLivingSound() {
/*     */     float chance;
/* 106 */     if (getAttackTarget() instanceof EntityPlayer) {
/* 107 */       chance = 0.3F;
/* 108 */     } else if (!this.worldObj.isDaytime()) {
/* 109 */       chance = 0.1F;
/*     */     } else {
/* 111 */       chance = 0.0F;
/*     */     } 
/* 113 */     if (this.rand.nextFloat() < chance) {
/* 114 */       return "imported.mob.witch.cackle";
/*     */     }
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume(String sound) {
/* 121 */     if (sound.equals("imported.mob.witch.cackle")) {
/* 122 */       return 0.6F;
/*     */     }
/* 124 */     return 0.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAggressive(boolean par1) {
/* 132 */     getDataWatcher().updateObject(21, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAggressive() {
/* 140 */     return (getDataWatcher().getWatchableObjectByte(21) == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 145 */     super.applyEntityAttributes();
/* 146 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(26.0D);
/* 147 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAIEnabled() {
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 164 */     if (!this.worldObj.isRemote) {
/*     */       
/* 166 */       if (getAggressive()) {
/*     */         
/* 168 */         if (this.witchAttackTimer-- <= 0)
/*     */         {
/* 170 */           setAggressive(false);
/* 171 */           ItemStack var1 = getHeldItemStack();
/* 172 */           setCurrentItemOrArmor(0, (ItemStack)null);
/*     */           
/* 174 */           if (var1 != null && var1.itemID == Item.potion.itemID) {
/*     */             
/* 176 */             List var2 = Item.potion.getEffects(var1);
/*     */             
/* 178 */             if (var2 != null) {
/*     */               
/* 180 */               Iterator<PotionEffect> var3 = var2.iterator();
/*     */               
/* 182 */               while (var3.hasNext()) {
/*     */                 
/* 184 */                 PotionEffect var4 = var3.next();
/* 185 */                 addPotionEffect(new PotionEffect(var4));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 190 */           getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(field_110185_bq);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 195 */         short var5 = -1;
/*     */         
/* 197 */         if (this.rand.nextFloat() < 0.15F && isBurning() && !isPotionActive(Potion.fireResistance)) {
/*     */           
/* 199 */           var5 = 16307;
/*     */         
/*     */         }
/* 202 */         else if (this.rand.nextFloat() < 0.01F && getHealth() < getMaxHealth()) {
/*     */           
/* 204 */           var5 = 16341;
/*     */         }
/* 206 */         else if (this.rand.nextFloat() < 0.25F && getAttackTarget() != null && !isPotionActive(Potion.moveSpeed) && getAttackTarget().getDistanceSqToEntity(this) > 121.0D) {
/*     */           
/* 208 */           var5 = 16274;
/*     */         }
/* 210 */         else if (this.rand.nextFloat() < 0.25F && getAttackTarget() != null && !isPotionActive(Potion.moveSpeed) && getAttackTarget().getDistanceSqToEntity(this) > 121.0D) {
/*     */           
/* 212 */           var5 = 16274;
/*     */         } 
/*     */         
/* 215 */         if (var5 > -1) {
/*     */           
/* 217 */           setCurrentItemOrArmor(0, new ItemStack(Item.potion, 1, var5));
/* 218 */           this.witchAttackTimer = getHeldItemStack().getMaxItemUseDuration();
/* 219 */           setAggressive(true);
/* 220 */           AttributeInstance var6 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/* 221 */           var6.removeModifier(field_110185_bq);
/* 222 */           var6.applyModifier(field_110185_bq);
/*     */         } 
/*     */       } 
/*     */       
/* 226 */       if (this.rand.nextFloat() < 7.5E-4F)
/*     */       {
/*     */         
/* 229 */         this.worldObj.setEntityState(this, EnumEntityState.witch_magic);
/*     */       }
/*     */       
/* 232 */       if (this.summon_wolf_countdown > 0) {
/*     */         
/* 234 */         if (--this.summon_wolf_countdown == 0) {
/* 235 */           this.has_summoned_wolves = (summonWolves() > 0 || this.has_summoned_wolves);
/*     */         }
/* 237 */       } else if (!this.has_summoned_wolves && getLastHarmingEntity() instanceof EntityPlayer) {
/*     */         
/* 239 */         this.summon_wolf_target = (EntityLivingBase)getLastHarmingEntity();
/* 240 */         this.summon_wolf_countdown = 60;
/*     */       } 
/*     */     } 
/*     */     
/* 244 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   private int summonWolves() {
/* 249 */     EntityLivingBase target = (EntityLivingBase)this.worldObj.getEntityByID(this.summon_wolf_target.entityId);
/*     */     
/* 251 */     if (target == null || target.isDead) {
/* 252 */       return 0;
/*     */     }
/* 254 */     int target_x = target.getBlockPosX();
/* 255 */     int target_y = target.getFootBlockPosY();
/* 256 */     int target_z = target.getBlockPosZ();
/*     */     
/* 258 */     int max_wolves = this.rand.nextInt(3) + 1;
/* 259 */     int num_wolves_spawned = 0;
/*     */     
/* 261 */     for (int attempts = 0; attempts < 16; attempts++) {
/*     */       
/* 263 */       EntityWolf wolf = (EntityWolf)((WorldServer)this.worldObj).tryCreateNewLivingEntityCloseTo(target_x, target_y, target_z, 8, 16, EntityWolf.class, EnumCreatureType.animal);
/*     */       
/* 265 */       if (wolf != null) {
/*     */         
/* 267 */         PathNavigate navigator = wolf.getNavigator();
/* 268 */         PathEntity path = this.worldObj.getEntityPathToXYZ(wolf, target_x, target_y, target_z, 32.0F, navigator.canPassOpenWoodenDoors, false, navigator.avoidsWater, navigator.canSwim);
/*     */         
/* 270 */         if (path != null) {
/*     */ 
/*     */           
/* 273 */           PathPoint final_point = path.getFinalPathPoint();
/*     */           
/* 275 */           if (World.getDistanceSqFromDeltas((final_point.xCoord - target_x), (final_point.yCoord - target_y), (final_point.zCoord - target_z)) <= 2.0D) {
/*     */ 
/*     */             
/* 278 */             wolf.refreshDespawnCounter(-9600);
/*     */             
/* 280 */             this.worldObj.spawnEntityInWorld(wolf);
/* 281 */             wolf.onSpawnWithEgg((EntityLivingData)null);
/*     */             
/* 283 */             wolf.setWitchAlly();
/* 284 */             wolf.setAttackTarget(target);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 289 */             wolf.entityFX(EnumEntityFX.summoned);
/*     */             
/* 291 */             if (++num_wolves_spawned == max_wolves)
/*     */               break; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 296 */     }  return num_wolves_spawned;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(EnumEntityState par1) {
/* 303 */     if (par1 == EnumEntityState.witch_magic) {
/*     */       
/* 305 */       for (int var2 = 0; var2 < this.rand.nextInt(35) + 10; var2++)
/*     */       {
/*     */         
/* 308 */         this.worldObj.spawnParticle(EnumParticle.witchMagic, this.posX + this.rand.nextGaussian() * 0.12999999523162842D, this.boundingBox.maxY + 0.5D + this.rand.nextGaussian() * 0.12999999523162842D, this.posZ + this.rand.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 313 */       super.handleHealthUpdate(par1);
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
/*     */   public float getNaturalDefense(DamageSource damage_source) {
/* 340 */     return (damage_source.hasMagicAspect() && damage_source.isIndirect()) ? 10.0F : super.getNaturalDefense(damage_source);
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 370 */     int num_drops = this.rand.nextInt(5 + damage_source.getLootingModifier()) + 1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 375 */     for (int i = 0; i < num_drops; i++) {
/*     */       
/* 377 */       int item_id = witchDrops[this.rand.nextInt(witchDrops.length)];
/*     */       
/* 379 */       if (item_id == Block.plantRed.blockID) {
/*     */         
/* 381 */         dropItemStack(new ItemStack(Block.plantRed, 1, 2));
/*     */       }
/* 383 */       else if (item_id == Item.potion.itemID) {
/*     */         
/* 385 */         int subtype = this.rand.nextInt(6);
/*     */         
/* 387 */         if (subtype == 0) {
/* 388 */           subtype = 8227;
/* 389 */         } else if (subtype == 1) {
/* 390 */           subtype = 8261;
/* 391 */         } else if (subtype == 2) {
/* 392 */           subtype = 16388;
/* 393 */         } else if (subtype == 3) {
/* 394 */           subtype = 16424;
/* 395 */         } else if (subtype == 4) {
/* 396 */           subtype = 16426;
/* 397 */         } else if (subtype == 5) {
/* 398 */           subtype = 16460;
/*     */         } else {
/* 400 */           Minecraft.setErrorMessage("dropFewItems: unhandled subtype " + subtype);
/*     */         } 
/* 402 */         dropItemStack(new ItemStack(item_id, 1, subtype));
/*     */       }
/*     */       else {
/*     */         
/* 406 */         dropItem(item_id, 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
/* 416 */     if (!getAggressive()) {
/*     */       
/* 418 */       EntityPotion var3 = new EntityPotion(this.worldObj, this, 32732);
/* 419 */       var3.rotationPitch -= -20.0F;
/* 420 */       double var4 = par1EntityLivingBase.posX + par1EntityLivingBase.motionX - this.posX;
/* 421 */       double var6 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - this.posY;
/* 422 */       double var8 = par1EntityLivingBase.posZ + par1EntityLivingBase.motionZ - this.posZ;
/* 423 */       float var10 = MathHelper.sqrt_double(var4 * var4 + var8 * var8);
/*     */       
/* 425 */       if (var10 >= 8.0F && !par1EntityLivingBase.isPotionActive(Potion.moveSlowdown)) {
/*     */         
/* 427 */         var3.setPotionType(32698);
/*     */       }
/* 429 */       else if (par1EntityLivingBase.getHealth() >= 8.0F && !par1EntityLivingBase.isPotionActive(Potion.poison)) {
/*     */         
/* 431 */         var3.setPotionType(32660);
/*     */       }
/* 433 */       else if (var10 <= 3.0F && !par1EntityLivingBase.isPotionActive(Potion.weakness) && this.rand.nextFloat() < 0.25F) {
/*     */         
/* 435 */         var3.setPotionType(32696);
/*     */       } 
/*     */       
/* 438 */       float distance_squared = (float)(var4 * var4 + var8 * var8);
/*     */       
/* 440 */       if (par1EntityLivingBase instanceof ServerPlayer) {
/*     */         
/* 442 */         float lead = (float)Math.pow(distance_squared, 0.5D);
/*     */         
/* 444 */         lead *= 0.5F + this.rand.nextFloat();
/*     */         
/* 446 */         var4 = par1EntityLivingBase.getPredictedPosX(lead) - this.posX;
/* 447 */         var8 = par1EntityLivingBase.getPredictedPosZ(lead) - this.posZ;
/*     */       } 
/*     */       
/* 450 */       var3.setThrowableHeading(var4, var6 + (var10 * 0.2F), var8, 0.75F, 8.0F);
/*     */ 
/*     */       
/* 453 */       double y_correction = (distance_squared * 0.001F - 0.025F);
/* 454 */       var3.motionY += y_correction;
/*     */       
/* 456 */       var3.motionX *= 1.2000000476837158D;
/* 457 */       var3.motionY *= 1.2000000476837158D;
/* 458 */       var3.motionZ *= 1.2000000476837158D;
/*     */       
/* 460 */       this.worldObj.spawnEntityInWorld(var3);
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
/*     */   protected boolean canDespawn() {
/* 480 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 485 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/* 487 */     if (this.has_summoned_wolves) {
/* 488 */       par1NBTTagCompound.setBoolean("has_summoned_wolves", this.has_summoned_wolves);
/*     */     }
/* 490 */     par1NBTTagCompound.setInteger("curse_random_seed", this.curse_random_seed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 495 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/* 497 */     this.has_summoned_wolves = par1NBTTagCompound.getBoolean("has_summoned_wolves");
/*     */     
/* 499 */     if (par1NBTTagCompound.hasKey("curse_random_seed")) {
/* 500 */       this.curse_random_seed = par1NBTTagCompound.getInteger("curse_random_seed");
/*     */     }
/*     */   }
/*     */   
/*     */   public void cursePlayer(ServerPlayer player) {
/* 505 */     if (this.worldObj.isRemote) {
/*     */       return;
/*     */     }
/* 508 */     if (getHealth() <= 0.0F || player.is_cursed || player.hasCursePending()) {
/*     */       return;
/*     */     }
/* 511 */     int username_hash = 0;
/*     */     
/* 513 */     for (int i = 0; i < player.username.length(); i++) {
/* 514 */       username_hash += player.username.charAt(i) * i;
/*     */     }
/*     */     
/* 517 */     ((WorldServer)this.worldObj).addCurse(player, this, Curse.getRandomCurse(new Random((this.curse_random_seed + username_hash))), 6000);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource par1DamageSource) {
/* 522 */     if (!this.worldObj.isRemote) {
/* 523 */       ((WorldServer)this.worldObj).removeCursesForWitch(this);
/*     */     }
/* 525 */     super.onDeath(par1DamageSource);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 530 */     return super.getExperienceValue() * 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityWitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */