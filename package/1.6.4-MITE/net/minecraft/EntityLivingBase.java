/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ 
/*      */ public abstract class EntityLivingBase
/*      */   extends Entity {
/*   13 */   private static final UUID sprintingSpeedBoostModifierUUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
/*   14 */   private static final AttributeModifier sprintingSpeedBoostModifier = (new AttributeModifier(sprintingSpeedBoostModifierUUID, "Sprinting speed boost", 0.30000001192092896D, 2)).setSaved(false);
/*      */   private BaseAttributeMap attributeMap;
/*   16 */   private final CombatTracker _combatTracker = new CombatTracker(this);
/*   17 */   private final HashMap activePotionsMap = new HashMap<Object, Object>();
/*      */ 
/*      */   
/*   20 */   private final ItemStack[] previousEquipment = new ItemStack[5];
/*      */ 
/*      */   
/*      */   public boolean isSwingInProgress;
/*      */ 
/*      */   
/*      */   public int swingProgressInt;
/*      */ 
/*      */   
/*      */   public int arrowHitTimer;
/*      */ 
/*      */   
/*      */   public float prevHealth;
/*      */   
/*      */   public int hurtTime;
/*      */   
/*      */   public int maxHurtTime;
/*      */   
/*      */   public float attackedAtYaw;
/*      */   
/*      */   public int deathTime;
/*      */   
/*      */   public int attackTime;
/*      */   
/*      */   public float prevSwingProgress;
/*      */   
/*      */   public float swingProgress;
/*      */   
/*      */   public float prevLimbSwingAmount;
/*      */   
/*      */   public float limbSwingAmount;
/*      */   
/*      */   public float limbSwing;
/*      */   
/*   54 */   public int maxHurtResistantTime = 20;
/*      */   
/*      */   public float prevCameraPitch;
/*      */   
/*      */   public float cameraPitch;
/*      */   
/*      */   public float field_70769_ao;
/*      */   
/*      */   public float field_70770_ap;
/*      */   
/*      */   public float renderYawOffset;
/*      */   
/*      */   public float prevRenderYawOffset;
/*      */   
/*      */   public float rotationYawHead;
/*      */   
/*      */   public float prevRotationYawHead;
/*   71 */   public float jumpMovementFactor = 0.02F;
/*      */ 
/*      */ 
/*      */   
/*      */   protected EntityPlayer attackingPlayer;
/*      */ 
/*      */ 
/*      */   
/*      */   private Entity last_harming_entity;
/*      */ 
/*      */ 
/*      */   
/*      */   private int last_harming_entity_memory_countdown;
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean has_decided_to_flee;
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean fleeing;
/*      */ 
/*      */   
/*      */   private String last_harming_entity_unique_id_string;
/*      */ 
/*      */   
/*      */   protected int recentlyHit;
/*      */ 
/*      */   
/*      */   protected int scoreValue;
/*      */ 
/*      */   
/*      */   protected float lastDamage;
/*      */ 
/*      */   
/*      */   protected boolean isJumping;
/*      */ 
/*      */   
/*      */   public float moveStrafing;
/*      */ 
/*      */   
/*      */   public float moveForward;
/*      */ 
/*      */   
/*      */   protected float randomYawVelocity;
/*      */ 
/*      */   
/*      */   protected int newPosRotationIncrements;
/*      */ 
/*      */   
/*      */   public double newPosX;
/*      */ 
/*      */   
/*      */   public double newPosY;
/*      */ 
/*      */   
/*      */   public double newPosZ;
/*      */ 
/*      */   
/*      */   protected double newRotationYaw;
/*      */ 
/*      */   
/*      */   protected double newRotationPitch;
/*      */ 
/*      */   
/*      */   private boolean potionsNeedUpdate = true;
/*      */ 
/*      */   
/*      */   private EntityLivingBase entityLivingToAttack;
/*      */ 
/*      */   
/*      */   private int revengeTimer;
/*      */ 
/*      */   
/*      */   private EntityLivingBase lastAttackTarget;
/*      */ 
/*      */   
/*      */   private int lastAttackTime;
/*      */ 
/*      */   
/*      */   private float landMovementFactor;
/*      */ 
/*      */   
/*      */   private int jumpTicks;
/*      */ 
/*      */   
/*      */   private float field_110151_bq;
/*      */ 
/*      */   
/*  160 */   public List contained_items = new ArrayList();
/*      */   
/*  162 */   public Entity was_killed_by = null;
/*      */   
/*      */   public boolean has_taken_massive_fall_damage;
/*      */   
/*      */   private int knockback_resistant_ticks;
/*      */   
/*      */   public static final int RESISTANCE_POISON = 1;
/*      */   
/*      */   public static final int RESISTANCE_PARALYSIS = 2;
/*      */   
/*      */   public static final int RESISTANCE_DRAIN = 3;
/*      */   
/*      */   public static final int RESISTANCE_SHADOW = 4;
/*      */   
/*      */   public boolean is_collided_with_entities;
/*      */ 
/*      */   
/*      */   public EntityLivingBase(World par1World) {
/*  180 */     super(par1World);
/*  181 */     applyEntityAttributes();
/*  182 */     setHealth(getMaxHealth());
/*  183 */     this.preventEntitySpawning = true;
/*  184 */     this.field_70770_ap = (float)(Math.random() + 1.0D) * 0.01F;
/*  185 */     setPosition(this.posX, this.posY, this.posZ);
/*  186 */     this.field_70769_ao = (float)Math.random() * 12398.0F;
/*  187 */     this.rotationYaw = (float)(Math.random() * Math.PI * 2.0D);
/*  188 */     this.rotationYawHead = this.rotationYaw;
/*  189 */     this.stepHeight = 0.5F;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  194 */     this.dataWatcher.addObject(7, Integer.valueOf(0));
/*  195 */     this.dataWatcher.addObject(8, Byte.valueOf((byte)0));
/*  196 */     this.dataWatcher.addObject(9, Byte.valueOf((byte)0));
/*  197 */     this.dataWatcher.addObject(6, Float.valueOf(1.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*  206 */     setEntityAttribute(SharedMonsterAttributes.maxHealth);
/*  207 */     setEntityAttribute(SharedMonsterAttributes.knockbackResistance);
/*  208 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*      */     
/*  210 */     if (!isAIEnabled())
/*      */     {
/*      */       
/*  213 */       setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.10000000149011612D);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void reapplyEntityAttributes() {
/*  220 */     float max_health_before = (float)getEntityAttributeValue(SharedMonsterAttributes.maxHealth);
/*  221 */     applyEntityAttributes();
/*  222 */     float max_health_after = (float)getEntityAttributeValue(SharedMonsterAttributes.maxHealth);
/*      */     
/*  224 */     if (max_health_after > max_health_before) {
/*  225 */       setHealth(getHealth() + max_health_after - max_health_before);
/*  226 */     } else if (getHealth() > max_health_after) {
/*  227 */       setHealth(max_health_after);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateFallState(double par1, boolean par3) {
/*  236 */     if (!isInWater())
/*      */     {
/*  238 */       handleWaterMovement();
/*      */     }
/*      */     
/*  241 */     if (par3 && this.fallDistance > 0.0F) {
/*      */       
/*  243 */       int var4 = MathHelper.floor_double(this.posX);
/*  244 */       int var5 = MathHelper.floor_double(this.posY - 0.20000000298023224D - this.yOffset);
/*  245 */       int var6 = MathHelper.floor_double(this.posZ);
/*  246 */       int var7 = this.worldObj.getBlockId(var4, var5, var6);
/*      */       
/*  248 */       if (var7 == 0) {
/*      */         
/*  250 */         int var8 = this.worldObj.blockGetRenderType(var4, var5 - 1, var6);
/*      */         
/*  252 */         if (var8 == 11 || var8 == 32 || var8 == 21)
/*      */         {
/*  254 */           var7 = this.worldObj.getBlockId(var4, var5 - 1, var6);
/*      */         }
/*      */       } 
/*      */       
/*  258 */       if (var7 > 0)
/*      */       {
/*  260 */         Block.blocksList[var7].onFallenUpon(this.worldObj, var4, var5, var6, this, this.fallDistance);
/*      */       }
/*      */     } 
/*      */     
/*  264 */     super.updateFallState(par1, par3);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBreatheUnderwater() {
/*  270 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean breathesAir() {
/*  276 */     return (getCreatureAttribute() != EnumCreatureAttribute.UNDEAD);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityUpdate() {
/*  284 */     this.prevSwingProgress = this.swingProgress;
/*  285 */     super.onEntityUpdate();
/*  286 */     this.worldObj.theProfiler.startSection("livingEntityBaseTick");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  295 */     if (onServer() && isEntityAlive())
/*      */     {
/*  297 */       if (this instanceof EntitySilverfish) {
/*      */         
/*  299 */         if (isSilverfishInsideDamagingOpaqueBlock()) {
/*  300 */           attackEntityFrom(new Damage(DamageSource.inWall, 1.0F));
/*      */         }
/*  302 */       } else if (isEntityInsideOpaqueBlock()) {
/*      */         
/*  304 */         attackEntityFrom(new Damage(DamageSource.inWall, 1.0F));
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  309 */     if (!canCatchFire() || this.worldObj.isRemote)
/*      */     {
/*  311 */       extinguish();
/*      */     }
/*      */     
/*  314 */     boolean var1 = (this instanceof EntityPlayer && ((EntityPlayer)this).capabilities.disableDamage);
/*      */     
/*  316 */     if (!breathesAir()) {
/*  317 */       var1 = true;
/*      */     }
/*  319 */     if (isEntityAlive() && isInsideOfMaterial(Material.water)) {
/*      */       
/*  321 */       if (this instanceof EntityChicken && isChild())
/*      */       {
/*  323 */         if (this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + this.height), MathHelper.floor_double(this.posZ)) == 0) {
/*      */           
/*  325 */           setAir(300);
/*  326 */           var1 = true;
/*      */         } 
/*      */       }
/*      */       
/*  330 */       if (!canBreatheUnderwater() && !isPotionActive(Potion.waterBreathing.id) && !var1) {
/*      */         
/*  332 */         setAir(decreaseAirSupply(getAir()));
/*      */ 
/*      */         
/*  335 */         if (getAir() <= -20) {
/*      */           
/*  337 */           setAir(0);
/*      */           
/*  339 */           for (int var2 = 0; var2 < 8; var2++) {
/*      */             
/*  341 */             float var3 = this.rand.nextFloat() - this.rand.nextFloat();
/*  342 */             float var4 = this.rand.nextFloat() - this.rand.nextFloat();
/*  343 */             float var5 = this.rand.nextFloat() - this.rand.nextFloat();
/*      */             
/*  345 */             this.worldObj.spawnParticle(EnumParticle.bubble, this.posX + var3, this.posY + var4, this.posZ + var5, this.motionX, this.motionY, this.motionZ);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  350 */           if (onServer()) {
/*  351 */             attackEntityFrom(new Damage(DamageSource.drown, 2.0F));
/*      */           }
/*      */         } 
/*      */       } 
/*  355 */       extinguish();
/*      */       
/*  357 */       if (!this.worldObj.isRemote && isRiding() && this.ridingEntity instanceof EntityLivingBase)
/*      */       {
/*  359 */         mountEntity((Entity)null);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  366 */     else if (isEntityPlayer()) {
/*  367 */       setAir(MathHelper.clamp_int(getAir() + 10, 0, 300));
/*      */     } else {
/*  369 */       setAir(300);
/*      */     } 
/*      */     
/*  372 */     this.prevCameraPitch = this.cameraPitch;
/*      */     
/*  374 */     if (this.attackTime > 0)
/*      */     {
/*  376 */       this.attackTime--;
/*      */     }
/*      */     
/*  379 */     if (this.hurtTime > 0)
/*      */     {
/*  381 */       this.hurtTime--;
/*      */     }
/*      */     
/*  384 */     if (this.hurtResistantTime > 0)
/*      */     {
/*  386 */       this.hurtResistantTime--;
/*      */     }
/*      */     
/*  389 */     if (this.knockback_resistant_ticks > 0) {
/*  390 */       this.knockback_resistant_ticks--;
/*      */     }
/*  392 */     if (getHealth() <= 0.0F)
/*      */     {
/*  394 */       onDeathUpdate();
/*      */     }
/*      */     
/*  397 */     if (this.recentlyHit > 0) {
/*      */       
/*  399 */       this.recentlyHit--;
/*      */     }
/*      */     else {
/*      */       
/*  403 */       this.attackingPlayer = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  411 */     if (this.lastAttackTarget != null && !this.lastAttackTarget.isEntityAlive())
/*      */     {
/*  413 */       this.lastAttackTarget = null;
/*      */     }
/*      */     
/*  416 */     if (this.entityLivingToAttack != null && !this.entityLivingToAttack.isEntityAlive())
/*      */     {
/*  418 */       setRevengeTarget((EntityLivingBase)null);
/*      */     }
/*      */     
/*  421 */     updatePotionEffects();
/*      */     
/*  423 */     this.prevRenderYawOffset = this.renderYawOffset;
/*  424 */     this.prevRotationYawHead = this.rotationYawHead;
/*  425 */     this.prevRotationYaw = this.rotationYaw;
/*  426 */     this.prevRotationPitch = this.rotationPitch;
/*  427 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChild() {
/*  435 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onDeathUpdate() {
/*  443 */     this.deathTime++;
/*      */     
/*  445 */     if (this instanceof EntityShadow || this instanceof EntityFireElemental || this instanceof EntityNightwing) {
/*  446 */       this.deathTime = 20;
/*      */     }
/*  448 */     if (this.deathTime == 20) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  455 */       if (!this.worldObj.isRemote && (this.recentlyHit > 0 || isEntityPlayer()) && !isChild() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
/*      */ 
/*      */         
/*  458 */         int i = getExperienceValue();
/*      */         
/*  460 */         while (i > 0) {
/*      */           
/*  462 */           int var2 = EntityXPOrb.getXPSplit(i);
/*  463 */           i -= var2;
/*      */ 
/*      */ 
/*      */           
/*  467 */           EntityXPOrb xp_orb = new EntityXPOrb(this.worldObj, this.posX, this.posY + 0.5D, this.posZ, var2);
/*      */           
/*  469 */           if (isEntityPlayer()) {
/*      */             
/*  471 */             EntityPlayer player = (EntityPlayer)this;
/*      */             
/*  473 */             xp_orb.setPlayerThisBelongsTo(player.username);
/*  474 */             xp_orb.xpOrbAge = -18000;
/*      */           } 
/*      */           
/*  477 */           this.worldObj.spawnEntityInWorld(xp_orb);
/*      */         } 
/*      */       } 
/*      */       
/*  481 */       setDead();
/*      */       
/*  483 */       for (int var1 = 0; var1 < 20; var1++) {
/*      */         
/*  485 */         double var8 = this.rand.nextGaussian() * 0.02D;
/*  486 */         double var4 = this.rand.nextGaussian() * 0.02D;
/*  487 */         double var6 = this.rand.nextGaussian() * 0.02D;
/*      */         
/*  489 */         this.worldObj.spawnParticle(EnumParticle.explode, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var8, var4, var6);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnDeathParticles() {
/*  496 */     for (int i = 0; i < 20; i++) {
/*      */       
/*  498 */       double var8 = this.rand.nextGaussian() * 0.02D;
/*  499 */       double var4 = this.rand.nextGaussian() * 0.02D;
/*  500 */       double var6 = this.rand.nextGaussian() * 0.02D;
/*      */       
/*  502 */       this.worldObj.spawnParticle(EnumParticle.explode, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var8, var4, var6);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int decreaseAirSupply(int par1) {
/*  511 */     if (!(this instanceof EntityPlayer) || ((EntityPlayer)this).hasCurse(Curse.cannot_hold_breath, true));
/*      */     
/*  513 */     int var2 = EnchantmentHelper.getRespiration(this);
/*  514 */     return (var2 > 0 && this.rand.nextInt(var2 + 1) > 0) ? par1 : (par1 - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Random getRNG() {
/*  535 */     return this.rand;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLivingBase getAITarget() {
/*  540 */     return this.entityLivingToAttack;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAITarget(EntityLivingBase target) {
/*  545 */     this.entityLivingToAttack = target;
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_142015_aE() {
/*  550 */     return this.revengeTimer;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRevengeTarget(EntityLivingBase par1EntityLivingBase) {
/*  555 */     this.entityLivingToAttack = par1EntityLivingBase;
/*  556 */     this.revengeTimer = this.ticksExisted;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityLivingBase getLastAttackTarget() {
/*  585 */     return this.lastAttackTarget;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLastAttackTime() {
/*  590 */     return this.lastAttackTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastAttackTarget(Entity par1Entity) {
/*  595 */     if (par1Entity instanceof EntityLivingBase) {
/*      */       
/*  597 */       this.lastAttackTarget = (EntityLivingBase)par1Entity;
/*      */     }
/*      */     else {
/*      */       
/*  601 */       this.lastAttackTarget = null;
/*      */     } 
/*      */     
/*  604 */     this.lastAttackTime = this.ticksExisted;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  617 */     par1NBTTagCompound.setFloat("HealF", getHealth());
/*  618 */     par1NBTTagCompound.setShort("Health", (short)(int)Math.ceil(getHealth()));
/*  619 */     par1NBTTagCompound.setShort("HurtTime", (short)this.hurtTime);
/*  620 */     par1NBTTagCompound.setShort("DeathTime", (short)this.deathTime);
/*  621 */     par1NBTTagCompound.setShort("AttackTime", (short)this.attackTime);
/*  622 */     par1NBTTagCompound.setFloat("AbsorptionAmount", getAbsorptionAmount());
/*  623 */     ItemStack[] var2 = getLastActiveItems();
/*  624 */     int var3 = var2.length;
/*      */     
/*      */     int var4;
/*      */     
/*  628 */     for (var4 = 0; var4 < var3; var4++) {
/*      */       
/*  630 */       ItemStack var5 = var2[var4];
/*      */       
/*  632 */       if (var5 != null)
/*      */       {
/*  634 */         this.attributeMap.removeAttributeModifiers(var5.getAttributeModifiers());
/*      */       }
/*      */     } 
/*      */     
/*  638 */     par1NBTTagCompound.setTag("Attributes", SharedMonsterAttributes.func_111257_a(getAttributeMap()));
/*  639 */     var2 = getLastActiveItems();
/*  640 */     var3 = var2.length;
/*      */     
/*  642 */     for (var4 = 0; var4 < var3; var4++) {
/*      */       
/*  644 */       ItemStack var5 = var2[var4];
/*      */       
/*  646 */       if (var5 != null)
/*      */       {
/*  648 */         this.attributeMap.applyAttributeModifiers(var5.getAttributeModifiers());
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  677 */     if (!this.activePotionsMap.isEmpty()) {
/*      */       
/*  679 */       NBTTagList var6 = new NBTTagList();
/*  680 */       Iterator<PotionEffect> var7 = this.activePotionsMap.values().iterator();
/*      */       
/*  682 */       while (var7.hasNext()) {
/*      */         
/*  684 */         PotionEffect var8 = var7.next();
/*  685 */         var6.appendTag(var8.writeCustomPotionEffectToNBT(new NBTTagCompound()));
/*      */       } 
/*      */       
/*  688 */       par1NBTTagCompound.setTag("ActiveEffects", var6);
/*      */     } 
/*      */     
/*  691 */     par1NBTTagCompound.setString("contained_items", convertContainedItemsToString());
/*      */     
/*  693 */     par1NBTTagCompound.setString("last_harming_entity_unique_id", (this.last_harming_entity == null) ? "" : this.last_harming_entity.getUniqueID().toString());
/*  694 */     par1NBTTagCompound.setInteger("last_harming_entity_memory_countdown", this.last_harming_entity_memory_countdown);
/*  695 */     par1NBTTagCompound.setBoolean("has_decided_to_flee", this.has_decided_to_flee);
/*  696 */     par1NBTTagCompound.setBoolean("fleeing", this.fleeing);
/*      */     
/*  698 */     par1NBTTagCompound.setFloat("rotationYawHead", this.rotationYawHead);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  706 */     setAbsorptionAmount(par1NBTTagCompound.getFloat("AbsorptionAmount"));
/*      */     
/*  708 */     if (par1NBTTagCompound.hasKey("Attributes") && this.worldObj != null && !this.worldObj.isRemote)
/*      */     {
/*  710 */       SharedMonsterAttributes.func_111260_a(getAttributeMap(), par1NBTTagCompound.getTagList("Attributes"), (this.worldObj == null) ? null : this.worldObj.getWorldLogAgent());
/*      */     }
/*      */     
/*  713 */     if (par1NBTTagCompound.hasKey("ActiveEffects")) {
/*      */       
/*  715 */       NBTTagList var2 = par1NBTTagCompound.getTagList("ActiveEffects");
/*      */       
/*  717 */       for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*      */         
/*  719 */         NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/*  720 */         PotionEffect var5 = PotionEffect.readCustomPotionEffectFromNBT(var4);
/*  721 */         this.activePotionsMap.put(Integer.valueOf(var5.getPotionID()), var5);
/*      */       } 
/*      */     } 
/*      */     
/*  725 */     if (par1NBTTagCompound.hasKey("HealF")) {
/*      */       
/*  727 */       setHealth(par1NBTTagCompound.getFloat("HealF"));
/*      */     }
/*      */     else {
/*      */       
/*  731 */       NBTBase var6 = par1NBTTagCompound.getTag("Health");
/*      */       
/*  733 */       if (var6 == null) {
/*      */         
/*  735 */         setHealth(getMaxHealth());
/*      */       }
/*  737 */       else if (var6.getId() == 5) {
/*      */         
/*  739 */         setHealth(((NBTTagFloat)var6).data);
/*      */       }
/*  741 */       else if (var6.getId() == 2) {
/*      */         
/*  743 */         setHealth(((NBTTagShort)var6).data);
/*      */       } 
/*      */     } 
/*      */     
/*  747 */     this.hurtTime = par1NBTTagCompound.getShort("HurtTime");
/*  748 */     this.deathTime = par1NBTTagCompound.getShort("DeathTime");
/*  749 */     this.attackTime = par1NBTTagCompound.getShort("AttackTime");
/*      */     
/*  751 */     obtainContainedItemsFromString(par1NBTTagCompound.getString("contained_items"));
/*      */     
/*  753 */     if (par1NBTTagCompound.hasKey("last_harming_entity_unique_id")) {
/*      */       
/*  755 */       this.last_harming_entity_unique_id_string = par1NBTTagCompound.getString("last_harming_entity_unique_id");
/*      */       
/*  757 */       if (this.last_harming_entity_unique_id_string.isEmpty()) {
/*  758 */         this.last_harming_entity_unique_id_string = null;
/*      */       }
/*      */     } 
/*  761 */     this.last_harming_entity_memory_countdown = par1NBTTagCompound.getInteger("last_harming_entity_memory_countdown");
/*  762 */     this.has_decided_to_flee = par1NBTTagCompound.getBoolean("has_decided_to_flee");
/*  763 */     this.fleeing = par1NBTTagCompound.getBoolean("fleeing");
/*      */     
/*  765 */     this.rotationYawHead = par1NBTTagCompound.getFloat("rotationYawHead");
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updatePotionEffects() {
/*  770 */     Iterator<Integer> var1 = this.activePotionsMap.keySet().iterator();
/*      */     
/*  772 */     while (var1.hasNext()) {
/*      */       
/*  774 */       Integer var2 = var1.next();
/*  775 */       PotionEffect var3 = (PotionEffect)this.activePotionsMap.get(var2);
/*      */       
/*  777 */       if (!var3.onUpdate(this)) {
/*      */         
/*  779 */         if (!this.worldObj.isRemote) {
/*      */           
/*  781 */           var1.remove();
/*  782 */           onFinishedPotionEffect(var3);
/*      */         }  continue;
/*      */       } 
/*  785 */       if (var3.getDuration() % 600 == 0)
/*      */       {
/*  787 */         onChangedPotionEffect(var3, false);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  793 */     if (this.potionsNeedUpdate) {
/*      */       
/*  795 */       if (!this.worldObj.isRemote)
/*      */       {
/*  797 */         if (this.activePotionsMap.isEmpty()) {
/*      */           
/*  799 */           this.dataWatcher.updateObject(8, Byte.valueOf((byte)0));
/*  800 */           this.dataWatcher.updateObject(7, Integer.valueOf(0));
/*  801 */           setInvisible(false);
/*      */         }
/*      */         else {
/*      */           
/*  805 */           int i = PotionHelper.calcPotionLiquidColor(this.activePotionsMap.values());
/*  806 */           this.dataWatcher.updateObject(8, Byte.valueOf((byte)(PotionHelper.func_82817_b(this.activePotionsMap.values()) ? 1 : 0)));
/*  807 */           this.dataWatcher.updateObject(7, Integer.valueOf(i));
/*  808 */           setInvisible(isPotionActive(Potion.invisibility.id));
/*      */         } 
/*      */       }
/*      */       
/*  812 */       this.potionsNeedUpdate = false;
/*      */     } 
/*      */     
/*  815 */     int var11 = this.dataWatcher.getWatchableObjectInt(7);
/*  816 */     boolean var12 = (this.dataWatcher.getWatchableObjectByte(8) > 0);
/*      */     
/*  818 */     if (var11 < 1 && this instanceof EntityMob) {
/*      */       
/*  820 */       EntityMob mob = (EntityMob)this;
/*      */       
/*  822 */       if (mob.isFrenzied()) {
/*      */         
/*  824 */         var11 = 8527390;
/*  825 */         var12 = false;
/*      */       } 
/*      */     } 
/*      */     
/*  829 */     if (var11 > 0) {
/*      */       int i;
/*  831 */       boolean var4 = false;
/*      */       
/*  833 */       if (!isInvisible()) {
/*      */         
/*  835 */         var4 = this.rand.nextBoolean();
/*      */       }
/*      */       else {
/*      */         
/*  839 */         var4 = (this.rand.nextInt(15) == 0);
/*      */       } 
/*      */       
/*  842 */       if (var12)
/*      */       {
/*  844 */         i = var4 & ((this.rand.nextInt(5) == 0) ? 1 : 0);
/*      */       }
/*      */       
/*  847 */       if (i != 0 && var11 > 0) {
/*      */         
/*  849 */         double var5 = (var11 >> 16 & 0xFF) / 255.0D;
/*  850 */         double var7 = (var11 >> 8 & 0xFF) / 255.0D;
/*  851 */         double var9 = (var11 >> 0 & 0xFF) / 255.0D;
/*      */         
/*  853 */         this.worldObj.spawnParticle(var12 ? EnumParticle.mobSpellAmbient : EnumParticle.mobSpell, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height - this.yOffset, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, var5, var7, var9);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearActivePotions() {
/*  860 */     Iterator<Integer> var1 = this.activePotionsMap.keySet().iterator();
/*      */     
/*  862 */     while (var1.hasNext()) {
/*      */       
/*  864 */       Integer var2 = var1.next();
/*  865 */       PotionEffect var3 = (PotionEffect)this.activePotionsMap.get(var2);
/*      */       
/*  867 */       if (!this.worldObj.isRemote) {
/*      */         
/*  869 */         var1.remove();
/*  870 */         onFinishedPotionEffect(var3);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection getActivePotionEffects() {
/*  877 */     return this.activePotionsMap.values();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasActivePotionEffects() {
/*  882 */     return (this.activePotionsMap != null && !this.activePotionsMap.isEmpty());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPotionActive(int par1) {
/*  887 */     return this.activePotionsMap.containsKey(Integer.valueOf(par1));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPotionActive(Potion par1Potion) {
/*  892 */     return this.activePotionsMap.containsKey(Integer.valueOf(par1Potion.id));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PotionEffect getActivePotionEffect(Potion par1Potion) {
/*  900 */     return (PotionEffect)this.activePotionsMap.get(Integer.valueOf(par1Potion.id));
/*      */   }
/*      */ 
/*      */   
/*      */   public PotionEffect getActivePotionEffect(int potion_id) {
/*  905 */     return (PotionEffect)this.activePotionsMap.get(Integer.valueOf(potion_id));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPotionEffect(PotionEffect par1PotionEffect) {
/*  913 */     if (isPotionApplicable(par1PotionEffect)) {
/*      */       
/*  915 */       if (onServer()) {
/*      */         
/*  917 */         int id = par1PotionEffect.getPotionID();
/*  918 */         Potion potion = Potion.get(id);
/*      */         
/*  920 */         if (potion == Potion.poison) {
/*  921 */           par1PotionEffect = (new PotionEffect(par1PotionEffect)).scaleDuration(1.0F - getResistanceToPoison());
/*      */         }
/*      */       } 
/*  924 */       if (this.activePotionsMap.containsKey(Integer.valueOf(par1PotionEffect.getPotionID()))) {
/*      */         
/*  926 */         ((PotionEffect)this.activePotionsMap.get(Integer.valueOf(par1PotionEffect.getPotionID()))).combine(par1PotionEffect);
/*  927 */         onChangedPotionEffect((PotionEffect)this.activePotionsMap.get(Integer.valueOf(par1PotionEffect.getPotionID())), true);
/*      */       }
/*      */       else {
/*      */         
/*  931 */         this.activePotionsMap.put(Integer.valueOf(par1PotionEffect.getPotionID()), par1PotionEffect);
/*  932 */         onNewPotionEffect(par1PotionEffect);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPotionApplicable(PotionEffect par1PotionEffect) {
/*  949 */     Potion potion = par1PotionEffect.getPotion();
/*      */     
/*  951 */     if (potion == Potion.regeneration || potion == Potion.poison || potion == Potion.wither)
/*      */     {
/*  953 */       if (!isEntityBiologicallyAlive()) {
/*  954 */         return false;
/*      */       }
/*      */     }
/*  957 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityUndead() {
/*  965 */     return (getCreatureAttribute() == EnumCreatureAttribute.UNDEAD);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEntityBiologicallyAlive() {
/*  970 */     return !isEntityUndead();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBePoisoned() {
/*  975 */     return isEntityBiologicallyAlive();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isArthropod() {
/*  980 */     return (this instanceof EntityArachnid || getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removePotionEffectClient(int par1) {
/*  988 */     this.activePotionsMap.remove(Integer.valueOf(par1));
/*      */     
/*  990 */     if (MITEConstant.sync_client_potion_attribute_modifiers_with_server) {
/*  991 */       onFinishedPotionEffect(new PotionEffect(par1, 0, 0));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removePotionEffect(int par1) {
/*  999 */     PotionEffect var2 = (PotionEffect)this.activePotionsMap.remove(Integer.valueOf(par1));
/*      */     
/* 1001 */     if (var2 != null)
/*      */     {
/* 1003 */       onFinishedPotionEffect(var2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onNewPotionEffect(PotionEffect par1PotionEffect) {
/* 1009 */     this.potionsNeedUpdate = true;
/*      */ 
/*      */     
/* 1012 */     if (onServer() || MITEConstant.sync_client_potion_attribute_modifiers_with_server)
/*      */     {
/* 1014 */       Potion.potionTypes[par1PotionEffect.getPotionID()].applyAttributesModifiersToEntity(this, getAttributeMap(), par1PotionEffect.getAmplifier());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onChangedPotionEffect(PotionEffect par1PotionEffect, boolean par2) {
/* 1020 */     this.potionsNeedUpdate = true;
/*      */ 
/*      */     
/* 1023 */     if (par2 && (onServer() || MITEConstant.sync_client_potion_attribute_modifiers_with_server)) {
/*      */       
/* 1025 */       Potion.potionTypes[par1PotionEffect.getPotionID()].removeAttributesModifiersFromEntity(this, getAttributeMap(), par1PotionEffect.getAmplifier());
/* 1026 */       Potion.potionTypes[par1PotionEffect.getPotionID()].applyAttributesModifiersToEntity(this, getAttributeMap(), par1PotionEffect.getAmplifier());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onFinishedPotionEffect(PotionEffect par1PotionEffect) {
/* 1032 */     this.potionsNeedUpdate = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1038 */     if (onServer() || MITEConstant.sync_client_potion_attribute_modifiers_with_server)
/*      */     {
/* 1040 */       Potion.potionTypes[par1PotionEffect.getPotionID()].removeAttributesModifiersFromEntity(this, getAttributeMap(), par1PotionEffect.getAmplifier());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void heal(float par1, EnumEntityFX gain_fx) {
/* 1049 */     if (par1 == 0.0F) {
/*      */       return;
/*      */     }
/* 1052 */     float var2 = getHealth();
/*      */     
/* 1054 */     if (var2 > 0.0F)
/*      */     {
/*      */       
/* 1057 */       setHealth(var2 + par1, true, gain_fx);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void heal(float amount) {
/* 1064 */     heal(amount, getHealFX());
/*      */   }
/*      */ 
/*      */   
/*      */   public final float getHealth() {
/* 1069 */     return this.dataWatcher.getWatchableObjectFloat(6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setHealth(float par1) {
/* 1079 */     setHealth(par1, true, getHealFX());
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumEntityFX getHealFX() {
/* 1084 */     return isEntityBiologicallyAlive() ? EnumEntityFX.heal : null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setHealth(float par1, boolean check_limit, EnumEntityFX gain_fx) {
/* 1090 */     int health_before = (int)getHealth();
/*      */     
/* 1092 */     if (check_limit) {
/* 1093 */       this.dataWatcher.updateObject(6, Float.valueOf(MathHelper.clamp_float(par1, 0.0F, getMaxHealth())));
/*      */     } else {
/* 1095 */       this.dataWatcher.updateObject(6, Float.valueOf(Math.max(par1, 0.0F)));
/*      */     } 
/* 1097 */     if (this.worldObj != null && (int)getHealth() > health_before)
/*      */     {
/* 1099 */       onHealthRegained(gain_fx);
/*      */     }
/*      */   }
/*      */   
/*      */   public float getHealthFraction() {
/* 1104 */     return getHealth() / getMaxHealth();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasFullHealth() {
/* 1115 */     return (getHealth() >= getMaxHealth());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private EntityDamageResult attackEntityFromHelper(Damage damage, EntityDamageResult result) {
/* 1531 */     Entity responsible_entity = damage.getResponsibleEntity();
/*      */     
/* 1533 */     float knockback_amount = (damage.isMelee() && responsible_entity instanceof EntityLivingBase && responsible_entity.getAsEntityLivingBase().canOnlyPerformWeakStrike()) ? 0.6F : 1.0F;
/*      */     
/* 1535 */     if (responsible_entity != null && knockBack(responsible_entity, knockback_amount)) {
/* 1536 */       result.setEntityWasKnockedBack();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1548 */     if (damage.isMelee() && damage.getResponsibleEntity() instanceof EntityLivingBase && damage.getResponsibleEntity().getAsEntityLivingBase().canOnlyPerformWeakStrike()) {
/* 1549 */       return result;
/*      */     }
/* 1551 */     if (damage.isEggDamage() && !canTakeDamageFromThrownEggs()) {
/* 1552 */       return result;
/*      */     }
/* 1554 */     if (damage.isSnowball() && damage.wasCausedByPlayer() && !canTakeDamageFromPlayerThrownSnowballs()) {
/* 1555 */       return result;
/*      */     }
/* 1557 */     if (damage.isKnockbackOnly()) {
/* 1558 */       return result;
/*      */     }
/* 1560 */     if (this.hurtResistantTime * 2 <= this.maxHurtResistantTime) {
/*      */       
/* 1562 */       this.lastDamage = damage.getAmount();
/* 1563 */       this.prevHealth = getHealth();
/* 1564 */       this.hurtResistantTime = this.maxHurtResistantTime;
/* 1565 */       this.hurtTime = this.maxHurtTime = 10;
/*      */     }
/*      */     else {
/*      */       
/* 1569 */       if (damage.getAmount() <= this.lastDamage) {
/* 1570 */         return result;
/*      */       }
/* 1572 */       float amount_reduction = this.lastDamage;
/*      */       
/* 1574 */       this.lastDamage = damage.getAmount();
/* 1575 */       damage.setAmount(damage.getAmount() - amount_reduction);
/*      */     } 
/*      */ 
/*      */     
/* 1579 */     if (damage.isLessThanHalfAHeart()) {
/* 1580 */       return result;
/*      */     }
/*      */ 
/*      */     
/* 1584 */     if (damage.isArrowFromPlayer()) {
/* 1585 */       tryAddArrowToContainedItems((EntityArrow)damage.getImmediateEntity());
/*      */     }
/* 1587 */     damage.setAmount(damage.applyTargetDefenseModifiers(this, result));
/*      */ 
/*      */ 
/*      */     
/* 1591 */     float amount = damage.getAmount();
/*      */     
/* 1593 */     if (amount > 0.0F) {
/*      */       
/* 1595 */       float var3 = amount;
/* 1596 */       amount = Math.max(amount - getAbsorptionAmount(), 0.0F);
/* 1597 */       setAbsorptionAmount(getAbsorptionAmount() - var3 - amount);
/*      */       
/* 1599 */       if (amount < 1.0F) {
/* 1600 */         amount = 1.0F;
/*      */       }
/*      */     } else {
/*      */       
/* 1604 */       this.lastDamage = 0.0F;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1615 */     result.startTrackingHealth(getHealth());
/* 1616 */     setHealth(getHealth() - amount);
/* 1617 */     result.finishTrackingHealth(getHealth());
/*      */     
/* 1619 */     if (result.entityLostHealth()) {
/* 1620 */       onEntityDamaged(damage.getSource(), result.getAmountOfHealthLost());
/*      */     }
/* 1622 */     if (getHealth() <= 0.0F) {
/* 1623 */       result.setEntityWasDestroyed();
/*      */     }
/*      */     
/* 1626 */     func_110142_aN().func_94547_a(damage.getSource(), getHealth(), amount);
/*      */ 
/*      */     
/* 1629 */     if (!isEntityPlayer()) {
/* 1630 */       setAbsorptionAmount(getAbsorptionAmount() - amount);
/*      */     }
/* 1632 */     DebugAttack.setResultingDamage(amount);
/* 1633 */     DebugAttack.setHealthAfter(getHealth());
/*      */     
/* 1635 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 1642 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*      */ 
/*      */ 
/*      */     
/* 1646 */     if (result == null || result.entityWasDestroyed()) {
/* 1647 */       return result;
/*      */     }
/* 1649 */     if (getHealth() <= 0.0F)
/*      */     {
/*      */       
/* 1652 */       return null;
/*      */     }
/* 1654 */     if (damage.isFireDamage() && isPotionActive(Potion.fireResistance))
/*      */     {
/*      */       
/* 1657 */       return null;
/*      */     }
/* 1659 */     if (damage.isAnvil() || damage.isFallingBlock()) {
/*      */       
/* 1661 */       ItemStack head_armor = getHelmet();
/*      */       
/* 1663 */       if (head_armor != null) {
/*      */         
/* 1665 */         result.applyArmorDamageResult(head_armor.tryDamageItem(damage.getSource(), (int)(damage.getAmount() * 4.0F + this.rand.nextFloat() * damage.getAmount() * 2.0F), this));
/* 1666 */         damage.scaleAmount(0.75F);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1677 */     if (damage.isNil())
/* 1678 */       return result; 
/* 1679 */     if (damage.isLessThanHalfAHeart()) {
/* 1680 */       damage.setAmount(1.0F);
/*      */     }
/* 1682 */     boolean treat_as_completely_new_attack = (this.hurtResistantTime * 2 <= this.maxHurtResistantTime);
/*      */     
/* 1684 */     attackEntityFromHelper(damage, result);
/*      */     
/* 1686 */     boolean make_death_sound = result.entityWasDestroyed();
/* 1687 */     boolean make_hurt_sound = (!make_death_sound && (result.entityWasKnockedBack() || result.entityLostHealth()));
/*      */     
/* 1689 */     if (result.entityWasNegativelyAffected()) {
/*      */       
/* 1691 */       if (treat_as_completely_new_attack) {
/*      */         
/* 1693 */         boolean refresh_red_tint = result.entityLostHealth();
/*      */         
/* 1695 */         if (refresh_red_tint)
/*      */         {
/* 1697 */           if (damage.isSunlight() && (this instanceof EntityShadow || this instanceof EntityNightwing)) {
/* 1698 */             refresh_red_tint = false;
/*      */           }
/*      */         }
/* 1701 */         this.worldObj.setEntityState(this, refresh_red_tint ? EnumEntityState.hurt_with_red_tint_refreshed : EnumEntityState.hurt_without_red_tint_refreshed);
/*      */         
/* 1703 */         if (make_death_sound) {
/* 1704 */           makeSound(getDeathSound());
/* 1705 */         } else if (make_hurt_sound) {
/* 1706 */           makeSound(getHurtSound());
/*      */         } 
/*      */       } 
/* 1709 */       if (!damage.isDrowning()) {
/* 1710 */         setBeenAttacked();
/*      */       }
/* 1712 */       this.limbSwingAmount = 1.5F;
/* 1713 */       this.attackedAtYaw = 0.0F;
/*      */       
/* 1715 */       Entity responsible_entity = damage.getResponsibleEntity();
/*      */       
/* 1717 */       if (responsible_entity == null) {
/*      */         
/* 1719 */         this.attackedAtYaw = ((int)(Math.random() * 2.0D) * 180);
/*      */       }
/*      */       else {
/*      */         
/* 1723 */         refreshDespawnCounter(-1200);
/* 1724 */         responsible_entity.refreshDespawnCounter(-1200);
/*      */         
/* 1726 */         if (responsible_entity instanceof EntityLivingBase) {
/*      */           
/* 1728 */           setRevengeTarget((EntityLivingBase)responsible_entity);
/* 1729 */           setLastHarmingEntity(responsible_entity);
/* 1730 */           considerFleeing();
/*      */         } 
/*      */         
/* 1733 */         if (responsible_entity instanceof EntityPlayer) {
/*      */ 
/*      */ 
/*      */           
/* 1737 */           if (!(damage.getImmediateEntity() instanceof EntitySnowball) || canTakeDamageFromPlayerThrownSnowballs())
/*      */           {
/*      */             
/* 1740 */             this.recentlyHit = 100;
/*      */           }
/* 1742 */           this.attackingPlayer = (EntityPlayer)responsible_entity;
/*      */           
/* 1744 */           refreshDespawnCounter(-9600);
/*      */         }
/* 1746 */         else if (responsible_entity instanceof EntityWolf) {
/*      */           
/* 1748 */           EntityWolf var5 = (EntityWolf)responsible_entity;
/*      */           
/* 1750 */           if (var5.isTamed()) {
/*      */             
/* 1752 */             this.recentlyHit = 100;
/* 1753 */             this.attackingPlayer = null;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1761 */       if (result.entityWasDestroyed()) {
/* 1762 */         onDeath(damage.getSource());
/*      */       }
/*      */     } 
/* 1765 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getBaseChanceOfRareDrop() {
/* 1824 */     return 200;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource par1DamageSource) {
/* 1833 */     Entity var2 = par1DamageSource.getResponsibleEntity();
/* 1834 */     EntityLivingBase var3 = func_94060_bK();
/*      */     
/* 1836 */     if (this.scoreValue >= 0 && var3 != null)
/*      */     {
/* 1838 */       var3.addToPlayerScore(this, this.scoreValue);
/*      */     }
/*      */     
/* 1841 */     if (var2 != null)
/*      */     {
/* 1843 */       var2.onKillEntity(this);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1848 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1850 */       int var4 = 0;
/*      */       
/* 1852 */       if (var2 instanceof EntityPlayer)
/*      */       {
/* 1854 */         var4 = EnchantmentHelper.getLootingModifier((EntityLivingBase)var2);
/*      */       }
/*      */       
/* 1857 */       if (!isChild() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1863 */         if (!this.has_taken_massive_fall_damage || this.rand.nextFloat() < 0.1F)
/*      */         {
/* 1865 */           dropFewItems((this.recentlyHit > 0), par1DamageSource);
/*      */         }
/* 1867 */         dropContainedItems();
/*      */ 
/*      */         
/* 1870 */         dropEquipment((this.recentlyHit > 0), var4);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1887 */     this.worldObj.setEntityState(this, EnumEntityState.dead);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropEquipment(boolean recently_hit_by_player, int par2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void knockBack(Entity par1Entity, float par2, double par3, double par5) {
/* 1901 */     if (this.rand.nextDouble() >= getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()) {
/*      */       
/* 1903 */       this.isAirBorne = true;
/* 1904 */       float var7 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
/* 1905 */       float var8 = 0.4F;
/* 1906 */       this.motionX /= 2.0D;
/* 1907 */       this.motionY /= 2.0D;
/* 1908 */       this.motionZ /= 2.0D;
/* 1909 */       this.motionX -= par3 / var7 * var8;
/* 1910 */       this.motionY += var8;
/* 1911 */       this.motionZ -= par5 / var7 * var8;
/*      */       
/* 1913 */       if (this.motionY > 0.4000000059604645D)
/*      */       {
/* 1915 */         this.motionY = 0.4000000059604645D;
/*      */       }
/*      */       
/* 1918 */       if (!isEntityPlayer()) {
/*      */         
/* 1920 */         this.motionX *= 0.800000011920929D;
/* 1921 */         this.motionY *= 0.800000011920929D;
/* 1922 */         this.motionZ *= 0.800000011920929D;
/*      */       } 
/*      */       
/* 1925 */       if (this instanceof EntityEarthElemental) {
/*      */         
/* 1927 */         this.motionX *= 0.4000000059604645D;
/* 1928 */         this.motionY *= 0.4000000059604645D;
/* 1929 */         this.motionZ *= 0.4000000059604645D;
/*      */       } 
/*      */       
/* 1932 */       this.motionX *= par2;
/* 1933 */       this.motionY *= par2;
/* 1934 */       this.motionZ *= par2;
/*      */       
/* 1936 */       this.knockback_resistant_ticks = 10;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean knockBack(Entity attacker, float amount) {
/* 1943 */     if (attacker == null || amount <= 0.0F || !canBeKnockedBack() || this.knockback_resistant_ticks > 0) {
/* 1944 */       return false;
/*      */     }
/* 1946 */     double var9 = attacker.posX - this.posX;
/*      */     
/*      */     double var7;
/* 1949 */     for (var7 = attacker.posZ - this.posZ; var9 * var9 + var7 * var7 < 1.0E-4D; var7 = (Math.random() - Math.random()) * 0.01D)
/*      */     {
/* 1951 */       var9 = (Math.random() - Math.random()) * 0.01D;
/*      */     }
/*      */     
/* 1954 */     this.attackedAtYaw = (float)(Math.atan2(var7, var9) * 180.0D / Math.PI) - this.rotationYaw;
/* 1955 */     knockBack(attacker, amount, var9, var7);
/*      */     
/* 1957 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getHurtSound() {
/* 1965 */     return "damage.hit";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getDeathSound() {
/* 1973 */     return "damage.hit";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropFewItems(boolean par1, DamageSource damage_source) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOnLadder() {
/* 1991 */     int var1 = MathHelper.floor_double(this.posX);
/* 1992 */     int var2 = MathHelper.floor_double(this.boundingBox.minY);
/* 1993 */     int var3 = MathHelper.floor_double(this.posZ);
/* 1994 */     int var4 = this.worldObj.getBlockId(var1, var2, var3);
/* 1995 */     return (var4 == Block.ladder.blockID || var4 == Block.vine.blockID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getClimbingSpeed() {
/* 2004 */     float factor = getSpeedBoostOrSlowDownFactor();
/*      */     
/* 2006 */     if (factor < 1.0F) {
/* 2007 */       return 0.2F * (getSpeedBoostOrSlowDownFactor() * 0.7F + 0.3F);
/*      */     }
/* 2009 */     return 0.2F * getSpeedBoostOrSlowDownFactor();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityAlive() {
/* 2019 */     return (!this.isDead && getHealth() > 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void calcFallDamage(float fall_distance, float[] damages) {
/* 2057 */     damages[0] = 0.0F;
/* 2058 */     damages[1] = 0.0F;
/*      */     
/* 2060 */     if (fall_distance < 0.5F) {
/*      */       return;
/*      */     }
/* 2063 */     PotionEffect var2 = getActivePotionEffect(Potion.jump);
/* 2064 */     float var3 = (var2 != null) ? (var2.getAmplifier() + 1) : 0.0F;
/*      */     
/* 2066 */     float damage = fall_distance - 2.5F - var3;
/* 2067 */     float damage_before_cushioning = damage;
/*      */     
/* 2069 */     damage *= 2.0F;
/*      */     
/* 2071 */     BlockInfo block_landed_on_info = getBlockRestingOn(0.1F);
/*      */     
/* 2073 */     if (damage >= 1.0F) {
/*      */       
/* 2075 */       int var5 = 0;
/*      */       
/* 2077 */       if (block_landed_on_info != null) {
/*      */         
/* 2079 */         var5 = block_landed_on_info.block.blockID;
/*      */         
/* 2081 */         if (var5 > 0) {
/*      */           
/* 2083 */           Block block = Block.blocksList[var5];
/*      */           
/* 2085 */           float cushioning = block.getCushioning(this.worldObj.getBlockMetadata(block_landed_on_info.x, block_landed_on_info.y, block_landed_on_info.z));
/*      */           
/* 2087 */           if (block.blockMaterial == Material.snow || block.blockMaterial == Material.craftedSnow) {
/*      */             
/* 2089 */             block = this.worldObj.getBlock(block_landed_on_info.x, block_landed_on_info.y - 1, block_landed_on_info.z);
/*      */             
/* 2091 */             if (block == null) {
/* 2092 */               cushioning++;
/*      */             } else {
/* 2094 */               cushioning += block.getCushioning(this.worldObj.getBlockMetadata(block_landed_on_info.x, block_landed_on_info.y - 1, block_landed_on_info.z));
/*      */             } 
/*      */           } 
/* 2097 */           block = Block.blocksList[this.worldObj.getBlockId(block_landed_on_info.x, block_landed_on_info.y + 1, block_landed_on_info.z)];
/*      */           
/* 2099 */           if (block != null) {
/* 2100 */             cushioning += block.getCushioning(this.worldObj.getBlockMetadata(block_landed_on_info.x, block_landed_on_info.y + 1, block_landed_on_info.z));
/*      */           }
/* 2102 */           if (cushioning > 1.0F) {
/* 2103 */             cushioning = 1.0F;
/*      */           }
/* 2105 */           damage -= cushioning * 10.0F;
/*      */ 
/*      */           
/* 2108 */           damage *= 1.0F - cushioning;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2114 */       if (this instanceof EntityArachnid) {
/* 2115 */         damage *= (this instanceof EntityWoodSpider) ? 0.25F : 0.5F;
/*      */       }
/* 2117 */       damages[0] = damage_before_cushioning;
/* 2118 */       damages[1] = damage;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void fall(float fall_distance) {
/* 2124 */     super.fall(fall_distance);
/*      */     
/* 2126 */     if (this.worldObj.isRemote) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 2131 */     if (fall_distance < 0.5F) {
/*      */       return;
/*      */     }
/* 2134 */     PotionEffect var2 = getActivePotionEffect(Potion.jump);
/* 2135 */     float var3 = (var2 != null) ? (var2.getAmplifier() + 1) : 0.0F;
/*      */     
/* 2137 */     float damage = fall_distance - 2.5F - var3;
/* 2138 */     float damage_before_cushioning = damage;
/*      */     
/* 2140 */     damage *= 2.0F;
/*      */     
/* 2142 */     BlockInfo block_landed_on_info = getBlockRestingOn(0.1F);
/*      */     
/* 2144 */     if (damage >= 1.0F) {
/*      */       
/* 2146 */       int var5 = 0;
/*      */       
/* 2148 */       if (block_landed_on_info != null) {
/*      */         
/* 2150 */         var5 = block_landed_on_info.block.blockID;
/*      */         
/* 2152 */         if (var5 > 0) {
/*      */           
/* 2154 */           Block block = Block.blocksList[var5];
/*      */           
/* 2156 */           float cushioning = block.getCushioning(this.worldObj.getBlockMetadata(block_landed_on_info.x, block_landed_on_info.y, block_landed_on_info.z));
/*      */           
/* 2158 */           if (block.blockMaterial == Material.snow || block.blockMaterial == Material.craftedSnow) {
/*      */             
/* 2160 */             block = this.worldObj.getBlock(block_landed_on_info.x, block_landed_on_info.y - 1, block_landed_on_info.z);
/*      */             
/* 2162 */             if (block == null) {
/* 2163 */               cushioning++;
/*      */             } else {
/* 2165 */               cushioning += block.getCushioning(this.worldObj.getBlockMetadata(block_landed_on_info.x, block_landed_on_info.y - 1, block_landed_on_info.z));
/*      */             } 
/*      */           } 
/* 2168 */           block = Block.blocksList[this.worldObj.getBlockId(block_landed_on_info.x, block_landed_on_info.y + 1, block_landed_on_info.z)];
/*      */           
/* 2170 */           if (block != null) {
/* 2171 */             cushioning += block.getCushioning(this.worldObj.getBlockMetadata(block_landed_on_info.x, block_landed_on_info.y + 1, block_landed_on_info.z));
/*      */           }
/* 2173 */           if (cushioning > 1.0F) {
/* 2174 */             cushioning = 1.0F;
/*      */           }
/* 2176 */           damage -= cushioning * 10.0F;
/*      */ 
/*      */           
/* 2179 */           damage *= 1.0F - cushioning;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2185 */       if (this instanceof EntityArachnid) {
/* 2186 */         damage *= (this instanceof EntityWoodSpider) ? 0.25F : 0.5F;
/*      */       }
/* 2188 */       if (damage_before_cushioning > 4.0F)
/*      */       {
/* 2190 */         if (!this.worldObj.isRemote && block_landed_on_info != null)
/*      */         {
/* 2192 */           if (block_landed_on_info.block == Block.glass || (block_landed_on_info.block == Block.blockSnow && this.worldObj.isAirOrPassableBlock(block_landed_on_info.x, block_landed_on_info.y - 1, block_landed_on_info.z, true))) {
/*      */             
/* 2194 */             this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, block_landed_on_info.x, block_landed_on_info.y, block_landed_on_info.z)).setCollidedWith(this), true);
/*      */             
/* 2196 */             if (damage > 5.0F) {
/* 2197 */               damage = 5.0F;
/*      */             }
/*      */           } 
/*      */         }
/*      */       }
/* 2202 */       if (damage >= 1.0F) {
/*      */         
/* 2204 */         if (damage >= getMaxHealth() * 0.5F) {
/* 2205 */           this.has_taken_massive_fall_damage = true;
/*      */         }
/* 2207 */         makeSound((damage > 4.0F) ? "damage.fallbig" : "damage.fallsmall");
/*      */         
/* 2209 */         attackEntityFrom(new Damage(DamageSource.fall, damage));
/*      */       } 
/*      */     } 
/*      */     
/* 2213 */     if (block_landed_on_info != null && block_landed_on_info.block.stepSound != null)
/*      */     {
/* 2215 */       if (this instanceof EntityPlayer) {
/* 2216 */         makeSound("step." + block_landed_on_info.block.stepSound.stepSoundName, Math.min(fall_distance * 0.1F, 1.0F), 1.0F);
/*      */       } else {
/* 2218 */         makeSound("step." + block_landed_on_info.block.stepSound.stepSoundName, Math.min(fall_distance * 0.2F, 2.0F), 1.0F);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void performHurtAnimation() {
/* 2227 */     this.hurtTime = this.maxHurtTime = 10;
/* 2228 */     this.attackedAtYaw = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getNaturalDefense(DamageSource damage_source) {
/* 2257 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract ItemStack[] getWornItems();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract boolean isWearing(ItemStack paramItemStack);
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWearingArmor() {
/* 2273 */     ItemStack[] worn_items = getWornItems();
/*      */     
/* 2275 */     for (int i = 0; i < worn_items.length; i++) {
/*      */       
/* 2277 */       ItemStack item_stack = worn_items[i];
/*      */       
/* 2279 */       if (item_stack != null && item_stack.getItem() instanceof ItemArmor) {
/* 2280 */         return true;
/*      */       }
/*      */     } 
/* 2283 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getProtectionFromArmor(DamageSource damage_source, boolean include_enchantments) {
/* 2289 */     return ItemArmor.getTotalArmorProtection(getWornItems(), damage_source, include_enchantments, this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getProtection(DamageSource damage_source, boolean include_natural_protection, boolean include_armor, boolean include_armor_enchantments, boolean include_potion_effects) {
/* 2295 */     float total_protection = 0.0F;
/*      */     
/* 2297 */     if (include_natural_protection) {
/* 2298 */       total_protection += getNaturalDefense(damage_source);
/*      */     }
/* 2300 */     if (include_armor) {
/* 2301 */       total_protection += getProtectionFromArmor(damage_source, include_armor_enchantments);
/*      */     }
/* 2303 */     if (include_potion_effects) {
/* 2304 */       total_protection += getTotalProtectionOfPotionEffects(damage_source);
/*      */     }
/* 2306 */     return total_protection;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getTotalProtection(DamageSource damage_source) {
/* 2312 */     return getProtection(damage_source, true, true, true, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getTotalProtectionOfPotionEffects(DamageSource damage_source) {
/* 2317 */     float total_protection = 0.0F;
/*      */     
/* 2319 */     if (damage_source != null && damage_source.isAbsolute()) {
/* 2320 */       return total_protection;
/*      */     }
/* 2322 */     if (isPotionActive(Potion.resistance)) {
/* 2323 */       total_protection += (getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5.0F;
/*      */     }
/* 2325 */     if (damage_source != null)
/*      */     {
/* 2327 */       if (isPotionActive(Potion.fireResistance))
/*      */       {
/* 2329 */         if (damage_source.isFireDamage()) {
/* 2330 */           total_protection += (getActivePotionEffect(Potion.fireResistance).getAmplifier() + 1) * 5.0F;
/*      */         }
/*      */       }
/*      */     }
/* 2334 */     return total_protection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSilverArmorCoverage() {
/* 2362 */     float coverage = 0.0F;
/*      */     
/* 2364 */     ItemStack[] worn_items = getWornItems();
/*      */     
/* 2366 */     for (int i = 0; i < worn_items.length; i++) {
/*      */       
/* 2368 */       ItemStack item_stack = worn_items[i];
/*      */       
/* 2370 */       if (item_stack != null)
/*      */       {
/*      */         
/* 2373 */         if (item_stack.isArmor()) {
/*      */           
/* 2375 */           ItemArmor item_armor = item_stack.getItem().getAsArmor();
/*      */           
/* 2377 */           if (item_armor.getArmorMaterial() == Material.silver) {
/* 2378 */             coverage += item_armor.getCoverage() * item_armor.getDamageFactor(item_stack, this);
/*      */           }
/* 2380 */         } else if (item_stack.getItem() instanceof ItemHorseArmor) {
/*      */           
/* 2382 */           ItemHorseArmor barding = (ItemHorseArmor)item_stack.getItem();
/*      */           
/* 2384 */           if (barding.getArmorMaterial() == Material.silver)
/* 2385 */             coverage += barding.getCoverage(); 
/*      */         } 
/*      */       }
/*      */     } 
/* 2389 */     return coverage;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getResistanceToPoison() {
/* 2395 */     return getSilverArmorCoverage() * 0.5F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getResistanceToParalysis() {
/* 2401 */     return EnchantmentHelper.getFreeActionModifier(this) * 0.8F / Enchantment.free_action.getNumLevels();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getResistanceToDrain() {
/* 2407 */     return getSilverArmorCoverage() * 0.5F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getResistanceToShadow() {
/* 2413 */     return getSilverArmorCoverage() * 0.5F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDrainAfterResistance(int drain) {
/* 2419 */     return Math.round(drain * (1.0F - getResistanceToDrain()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAmountAfterResistance(float amount, int resistance_type) {
/* 2425 */     float resistance = 0.0F;
/*      */     
/* 2427 */     if (resistance_type == 1) {
/* 2428 */       resistance = getResistanceToPoison();
/* 2429 */     } else if (resistance_type == 2) {
/* 2430 */       resistance = getResistanceToParalysis();
/* 2431 */     } else if (resistance_type == 3) {
/* 2432 */       Minecraft.setErrorMessage("getAmountAfterResistance: use getDrainAfterResistance() instead because it returns an int");
/* 2433 */     } else if (resistance_type == 4) {
/* 2434 */       resistance = getResistanceToShadow();
/*      */     } else {
/* 2436 */       Minecraft.setErrorMessage("getAmountAfterResistance: unhandled resistance type " + resistance_type);
/*      */     } 
/* 2438 */     if (resistance < 0.0F) {
/* 2439 */       Minecraft.setErrorMessage("getAmountAfterResistance: resistance was less than 0.0F");
/* 2440 */     } else if (resistance > 1.0F) {
/* 2441 */       Minecraft.setErrorMessage("getAmountAfterResistance: resistance was more than 1.0F");
/*      */     } 
/* 2443 */     return amount * (1.0F - resistance);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWearingItems(boolean include_non_armor) {
/* 2451 */     ItemStack[] worn_items = getWornItems();
/*      */     
/* 2453 */     for (int i = 0; i < worn_items.length; i++) {
/*      */       
/* 2455 */       ItemStack item_stack = worn_items[i];
/*      */       
/* 2457 */       if (item_stack != null && (include_non_armor || item_stack.getItem() instanceof ItemArmor)) {
/* 2458 */         return true;
/*      */       }
/*      */     } 
/* 2461 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWearingDamageableItems(boolean include_non_armor) {
/* 2466 */     ItemStack[] worn_items = getWornItems();
/*      */     
/* 2468 */     for (int i = 0; i < worn_items.length; i++) {
/*      */       
/* 2470 */       ItemStack item_stack = worn_items[i];
/*      */       
/* 2472 */       if (item_stack != null && (include_non_armor || item_stack.getItem() instanceof ItemArmor) && item_stack.isItemStackDamageable()) {
/* 2473 */         return true;
/*      */       }
/*      */     } 
/* 2476 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final ItemStack getBoots() {
/* 2482 */     return getWornItems()[0];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final ItemStack getLeggings() {
/* 2488 */     return getWornItems()[1];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final ItemStack getCuirass() {
/* 2494 */     return getWornItems()[2];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final ItemStack getHelmet() {
/* 2500 */     return getWornItems()[3];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract boolean setWornItem(int paramInt, ItemStack paramItemStack);
/*      */ 
/*      */   
/*      */   public final boolean setBoots(ItemStack item_stack) {
/* 2509 */     return setWornItem(0, item_stack);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean setLeggings(ItemStack item_stack) {
/* 2515 */     return setWornItem(1, item_stack);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean setCuirass(ItemStack item_stack) {
/* 2521 */     return setWornItem(2, item_stack);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean setHelmet(ItemStack item_stack) {
/* 2527 */     return setWornItem(3, item_stack);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWearingHelmet(boolean include_non_armor) {
/* 2533 */     ItemStack helmet = getHelmet();
/*      */     
/* 2535 */     return (helmet != null && (include_non_armor || helmet.getItem() instanceof ItemArmor));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWearingPumpkinHelmet() {
/* 2540 */     ItemStack helmet = getHelmet();
/*      */     
/* 2542 */     return (helmet != null && helmet.getItem() == Item.getItem(Block.pumpkin.blockID));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWearingCuirass(boolean include_non_armor) {
/* 2548 */     ItemStack cuirass = getCuirass();
/*      */     
/* 2550 */     return (cuirass != null && (include_non_armor || cuirass.getItem() instanceof ItemArmor));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void tryDamageArmor(DamageSource damage_source, float amount, EntityDamageResult result) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tryAddArrowToContainedItems(EntityArrow entity_arrow) {
/* 2659 */     if (entity_arrow.canBePickedUp == 1) {
/*      */       
/* 2661 */       ItemArrow item_arrow = entity_arrow.item_arrow;
/*      */       
/* 2663 */       EntityPlayer player = entity_arrow.shootingEntity.getAsPlayer();
/*      */       
/* 2665 */       if (this.rand.nextInt(5) < EnchantmentHelper.getEnchantmentLevel(Enchantment.arrow_recovery.effectId, player.getHeldItemStack())) {
/* 2666 */         addContainedItem(item_arrow);
/*      */       } else {
/* 2668 */         item_arrow.addToEntityContainedItemsWithChance(this.rand, this);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CombatTracker func_110142_aN() {
/* 2739 */     return this._combatTracker;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLivingBase func_94060_bK() {
/* 2744 */     return (this._combatTracker.func_94550_c() != null) ? this._combatTracker.func_94550_c() : ((this.attackingPlayer != null) ? this.attackingPlayer : ((this.entityLivingToAttack != null) ? this.entityLivingToAttack : null));
/*      */   }
/*      */ 
/*      */   
/*      */   public final float getMaxHealth() {
/* 2749 */     if (this instanceof EntityPlayer) {
/* 2750 */       return ((EntityPlayer)this).getHealthLimit();
/*      */     }
/* 2752 */     return (float)getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getArrowCountInEntity() {
/* 2760 */     return this.dataWatcher.getWatchableObjectByte(9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setArrowCountInEntity(int par1) {
/* 2768 */     this.dataWatcher.updateObject(9, Byte.valueOf((byte)par1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getArmSwingAnimationEnd() {
/* 2777 */     return isPotionActive(Potion.digSpeed) ? (6 - (1 + getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1) : (isPotionActive(Potion.digSlowdown) ? (6 + (1 + getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2) : 6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void swingArm() {
/* 2785 */     if (!this.isSwingInProgress || this.swingProgressInt >= getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
/*      */       
/* 2787 */       this.swingProgressInt = -1;
/* 2788 */       this.isSwingInProgress = true;
/*      */       
/* 2790 */       if (this.worldObj instanceof WorldServer)
/*      */       {
/* 2792 */         ((WorldServer)this.worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(this, new Packet18Animation(this, 1));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public final void onHealthRegained(EnumEntityFX gain_fx) {
/* 2799 */     if (!this.worldObj.isRemote)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 2804 */       if (gain_fx != null) {
/* 2805 */         entityFX(gain_fx);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(EnumEntityState par1) {
/* 2854 */     if (par1 == EnumEntityState.hurt_with_red_tint_refreshed || par1 == EnumEntityState.hurt_without_red_tint_refreshed) {
/*      */       
/* 2856 */       if (par1 == EnumEntityState.hurt_with_red_tint_refreshed) {
/* 2857 */         this.hurtTime = this.maxHurtTime = 10;
/*      */       }
/* 2859 */       this.limbSwingAmount = 1.5F;
/* 2860 */       this.hurtResistantTime = this.maxHurtResistantTime;
/*      */       
/* 2862 */       this.attackedAtYaw = 0.0F;
/*      */     }
/* 2864 */     else if (par1 == EnumEntityState.dead) {
/*      */       
/* 2866 */       playSound(getDeathSound(), getSoundVolume(getDeathSound()), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/* 2867 */       setHealth(0.0F);
/* 2868 */       onDeath(DamageSource.generic);
/*      */     }
/*      */     else {
/*      */       
/* 2872 */       super.handleHealthUpdate(par1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void kill() {
/* 2883 */     if (onServer()) {
/* 2884 */       attackEntityFrom(new Damage(DamageSource.outOfWorld, 4.0F));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateArmSwingProgress() {
/* 2892 */     int var1 = getArmSwingAnimationEnd();
/*      */     
/* 2894 */     if (this.isSwingInProgress) {
/*      */       
/* 2896 */       this.swingProgressInt++;
/*      */       
/* 2898 */       if (this.swingProgressInt >= var1)
/*      */       {
/* 2900 */         this.swingProgressInt = 0;
/* 2901 */         this.isSwingInProgress = false;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 2906 */       this.swingProgressInt = 0;
/*      */     } 
/*      */     
/* 2909 */     this.swingProgress = this.swingProgressInt / var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasEntityAttribute(Attribute attribute) {
/* 2914 */     return (getEntityAttribute(attribute) != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public AttributeInstance setEntityAttribute(Attribute par1Attribute) {
/* 2919 */     AttributeInstance instance = getAttributeMap().getAttributeInstance(par1Attribute);
/*      */     
/* 2921 */     if (instance == null) {
/* 2922 */       instance = getAttributeMap().register(par1Attribute);
/*      */     }
/* 2924 */     return instance;
/*      */   }
/*      */ 
/*      */   
/*      */   public AttributeInstance setEntityAttribute(Attribute par1Attribute, double value) {
/* 2929 */     AttributeInstance instance = getAttributeMap().getAttributeInstance(par1Attribute);
/*      */     
/* 2931 */     if (instance == null) {
/* 2932 */       instance = getAttributeMap().register(par1Attribute);
/*      */     }
/* 2934 */     instance.setAttribute(value);
/*      */     
/* 2936 */     return instance;
/*      */   }
/*      */ 
/*      */   
/*      */   public AttributeInstance getEntityAttribute(Attribute par1Attribute) {
/* 2941 */     return getAttributeMap().getAttributeInstance(par1Attribute);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getEntityAttributeValue(Attribute attribute) {
/* 2947 */     return getAttributeMap().getAttributeInstance(attribute).getAttributeValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getEntityAttributeBaseValue(Attribute attribute) {
/* 2953 */     return getAttributeMap().getAttributeInstance(attribute).getBaseValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public BaseAttributeMap getAttributeMap() {
/* 2958 */     if (this.attributeMap == null)
/*      */     {
/* 2960 */       this.attributeMap = new ServersideAttributeMap();
/*      */     }
/*      */     
/* 2963 */     return this.attributeMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumCreatureAttribute getCreatureAttribute() {
/* 2971 */     return EnumCreatureAttribute.UNDEFINED;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void setHeldItemStack(ItemStack paramItemStack);
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract ItemStack getHeldItemStack();
/*      */ 
/*      */ 
/*      */   
/*      */   public Item getHeldItem() {
/* 2986 */     ItemStack item_stack = getHeldItemStack();
/* 2987 */     return (item_stack == null) ? null : item_stack.getItem();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHeldItemID() {
/* 2992 */     Item item = getHeldItem();
/*      */     
/* 2994 */     return (item == null) ? 0 : item.itemID;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasHeldItem() {
/* 2999 */     ItemStack item_stack = getHeldItemStack();
/*      */     
/* 3001 */     if (item_stack == null) {
/* 3002 */       return false;
/*      */     }
/* 3004 */     if (item_stack.stackSize < 1) {
/*      */       
/* 3006 */       Minecraft.setErrorMessage("hasHeldItem: stack size is " + item_stack.stackSize);
/* 3007 */       return false;
/*      */     } 
/*      */     
/* 3010 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract ItemStack getCurrentItemOrArmor(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void setCurrentItemOrArmor(int paramInt, ItemStack paramItemStack);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSprinting(boolean par1) {
/* 3034 */     super.setSprinting(par1);
/* 3035 */     AttributeInstance var2 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*      */     
/* 3037 */     if (var2.getModifier(sprintingSpeedBoostModifierUUID) != null)
/*      */     {
/* 3039 */       var2.removeModifier(sprintingSpeedBoostModifier);
/*      */     }
/*      */     
/* 3042 */     if (par1)
/*      */     {
/* 3044 */       var2.applyModifier(sprintingSpeedBoostModifier);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract ItemStack[] getLastActiveItems();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getSoundVolume(String sound) {
/* 3060 */     return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getSoundPitch(String sound) {
/* 3073 */     return isChild() ? ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.5F) : ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isMovementBlocked() {
/* 3081 */     return (getHealth() <= 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndUpdate(double par1, double par3, double par5) {
/* 3089 */     setLocationAndAngles(par1, par3, par5, this.rotationYaw, this.rotationPitch);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dismountEntity(Entity par1Entity) {
/* 3097 */     double var3 = par1Entity.posX;
/* 3098 */     double var5 = par1Entity.boundingBox.minY + par1Entity.height;
/* 3099 */     double var7 = par1Entity.posZ;
/*      */     double var9;
/* 3101 */     for (var9 = -1.5D; var9 < 2.0D; var9++) {
/*      */       double var11;
/* 3103 */       for (var11 = -1.5D; var11 < 2.0D; var11++) {
/*      */         
/* 3105 */         if (var9 != 0.0D || var11 != 0.0D) {
/*      */           
/* 3107 */           int var13 = (int)(this.posX + var9);
/* 3108 */           int var14 = (int)(this.posZ + var11);
/* 3109 */           AxisAlignedBB var2 = this.boundingBox.getOffsetBoundingBox(var9, 1.0D, var11);
/*      */ 
/*      */           
/* 3112 */           if (this.worldObj.getCollidingBlockBounds(var2, this).isEmpty()) {
/*      */             
/* 3114 */             if (this.worldObj.isBlockTopFlatAndSolid(var13, (int)this.posY, var14)) {
/*      */               
/* 3116 */               setPositionAndUpdate(this.posX + var9, this.posY + 1.0D, this.posZ + var11);
/*      */               
/*      */               return;
/*      */             } 
/* 3120 */             if (this.worldObj.isBlockTopFlatAndSolid(var13, (int)this.posY - 1, var14) || this.worldObj.getBlockMaterial(var13, (int)this.posY - 1, var14) == Material.water) {
/*      */               
/* 3122 */               var3 = this.posX + var9;
/* 3123 */               var5 = this.posY + 1.0D;
/* 3124 */               var7 = this.posZ + var11;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3131 */     setPositionAndUpdate(var3, var5, var7);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAlwaysRenderNameTagForRender() {
/* 3136 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getItemIcon(ItemStack par1ItemStack, int par2) {
/* 3144 */     return par1ItemStack.getIconIndex();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void jump() {
/* 3154 */     this.motionY = 0.42100000381469727D;
/*      */     
/* 3156 */     if (isPotionActive(Potion.jump))
/*      */     {
/* 3158 */       this.motionY += ((getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
/*      */     }
/*      */     
/* 3161 */     if (isSprinting()) {
/*      */       
/* 3163 */       float var1 = this.rotationYaw * 0.017453292F;
/* 3164 */       this.motionX -= (MathHelper.sin(var1) * 0.2F);
/* 3165 */       this.motionZ += (MathHelper.cos(var1) * 0.2F);
/*      */     } 
/*      */     
/* 3168 */     this.isAirBorne = true;
/*      */   }
/*      */ 
/*      */   
/*      */   private float handleSpecialArachnidMovement(float forward_movement) {
/* 3173 */     if (forward_movement >= 0.5F) {
/* 3174 */       return forward_movement;
/*      */     }
/* 3176 */     EntityArachnid arachnid = (EntityArachnid)this;
/* 3177 */     Entity target = arachnid.getEntityToAttack();
/*      */     
/* 3179 */     if (target instanceof EntityPlayer) {
/*      */       
/* 3181 */       boolean can_pass_through_leaves = this instanceof EntityWoodSpider;
/*      */       
/* 3183 */       double dx = target.posX - this.posX;
/* 3184 */       double dy = target.posY - this.posY;
/* 3185 */       double dz = target.posZ - this.posZ;
/*      */       
/* 3187 */       double dx_sq = dx * dx;
/* 3188 */       double dz_sq = dz * dz;
/*      */ 
/*      */ 
/*      */       
/* 3192 */       if (dy > 0.0D && dx_sq < 8.0D && dz_sq < 8.0D) {
/*      */         
/* 3194 */         if (dy > 1.0D || dx_sq > 0.10000000149011612D || dz_sq > 0.10000000149011612D) {
/*      */ 
/*      */ 
/*      */           
/* 3198 */           int x = getBlockPosX();
/* 3199 */           int y = getBlockPosY();
/* 3200 */           int z = getBlockPosZ();
/*      */           
/* 3202 */           boolean is_in_leaves = isInsideOfMaterial(Material.tree_leaves);
/*      */           
/* 3204 */           if (is_in_leaves) {
/*      */             
/* 3206 */             if (this.worldObj.getBlock(x, y + 1, z) == Block.snow) {
/* 3207 */               this.worldObj.destroyBlock(new BlockBreakInfo(this.worldObj, x, y + 1, z), false);
/*      */             }
/*      */ 
/*      */             
/* 3211 */             int trial_x = MathHelper.floor_double(this.boundingBox.minX);
/* 3212 */             int trial_z = MathHelper.floor_double(this.boundingBox.minZ);
/*      */             
/* 3214 */             if ((trial_x != x || trial_z != z) && this.worldObj.getBlock(trial_x, y + 1, trial_z) == Block.snow && this.worldObj.getBlock(trial_x, y, trial_z) instanceof BlockLeaves) {
/* 3215 */               this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, trial_x, y + 1, trial_z)).setDestroyedBy(this), false);
/*      */             }
/* 3217 */             trial_x = MathHelper.floor_double(this.boundingBox.minX);
/* 3218 */             trial_z = MathHelper.floor_double(this.boundingBox.maxZ);
/*      */             
/* 3220 */             if ((trial_x != x || trial_z != z) && this.worldObj.getBlock(trial_x, y + 1, trial_z) == Block.snow && this.worldObj.getBlock(trial_x, y, trial_z) instanceof BlockLeaves) {
/* 3221 */               this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, trial_x, y + 1, trial_z)).setDestroyedBy(this), false);
/*      */             }
/* 3223 */             trial_x = MathHelper.floor_double(this.boundingBox.maxX);
/* 3224 */             trial_z = MathHelper.floor_double(this.boundingBox.minZ);
/*      */             
/* 3226 */             if ((trial_x != x || trial_z != z) && this.worldObj.getBlock(trial_x, y + 1, trial_z) == Block.snow && this.worldObj.getBlock(trial_x, y, trial_z) instanceof BlockLeaves) {
/* 3227 */               this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, trial_x, y + 1, trial_z)).setDestroyedBy(this), false);
/*      */             }
/* 3229 */             trial_x = MathHelper.floor_double(this.boundingBox.maxX);
/* 3230 */             trial_z = MathHelper.floor_double(this.boundingBox.maxZ);
/*      */             
/* 3232 */             if ((trial_x != x || trial_z != z) && this.worldObj.getBlock(trial_x, y + 1, trial_z) == Block.snow && this.worldObj.getBlock(trial_x, y, trial_z) instanceof BlockLeaves) {
/* 3233 */               this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, trial_x, y + 1, trial_z)).setDestroyedBy(this), false);
/*      */             }
/*      */           } 
/* 3236 */           int target_y = target.getBlockPosY();
/*      */           
/* 3238 */           boolean can_climb_up_to_player = true;
/*      */           
/* 3240 */           while (++y <= target_y) {
/*      */             
/* 3242 */             if (!this.worldObj.isAirOrPassableBlock(x, y, z, false) && (!can_pass_through_leaves || !(this.worldObj.getBlock(x, y, z) instanceof BlockLeaves))) {
/*      */               
/* 3244 */               can_climb_up_to_player = false;
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/* 3249 */           if (can_climb_up_to_player) {
/* 3250 */             forward_movement = 0.5F;
/*      */           } else {
/* 3252 */             forward_movement = 0.0F;
/*      */           } 
/* 3254 */           if (isInsideOfMaterial(Material.tree_leaves))
/*      */           {
/* 3256 */             if (can_climb_up_to_player) {
/* 3257 */               forward_movement *= 0.2F;
/*      */             }
/* 3259 */             setJumping(true);
/*      */           }
/*      */         
/* 3262 */         } else if (dy <= 1.0D && this.ticksExisted % 40 == 0) {
/*      */           
/* 3264 */           this.motionX = this.motionY = this.motionZ = 0.0D;
/* 3265 */           this.posX = (target.getBlockPosX() + 0.5F);
/* 3266 */           this.posY = target.posY;
/* 3267 */           this.posZ = (target.getBlockPosZ() + 0.5F);
/* 3268 */           forward_movement = 0.0F;
/*      */           
/* 3270 */           attackEntityAsMob(target);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 3275 */         forward_movement = 0.5F;
/*      */       } 
/*      */     } 
/*      */     
/* 3279 */     return forward_movement;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveEntityWithHeading(float par1, float par2) {
/* 3287 */     if (!this.worldObj.isRemote && this instanceof EntityArachnid) {
/* 3288 */       par2 = handleSpecialArachnidMovement(par2);
/*      */     }
/* 3290 */     Block block_at_feet = this.worldObj.getBlock(getBlockPosX(), getFootBlockPosY(), getBlockPosZ());
/*      */     
/* 3292 */     if (block_at_feet != null && (block_at_feet.blockMaterial == Material.vine || block_at_feet.blockMaterial == Material.plants))
/*      */     {
/* 3294 */       if (hasCurse(Curse.entanglement, true)) {
/*      */         
/* 3296 */         float slow_down = (block_at_feet == Block.vine) ? 0.2F : 0.4F;
/*      */         
/* 3298 */         par1 *= slow_down;
/* 3299 */         par2 *= slow_down;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3305 */     if (isInWater() && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).capabilities.isFlying) && !(this instanceof EntityOoze)) {
/*      */       
/* 3307 */       double d = this.posY;
/*      */       
/* 3309 */       moveFlying(par1, par2, (this instanceof EntityEarthElemental) ? 0.055F : (isAIEnabled() ? 0.04F : 0.02F));
/* 3310 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 3311 */       this.motionX *= 0.800000011920929D;
/* 3312 */       this.motionY *= 0.800000011920929D;
/* 3313 */       this.motionZ *= 0.800000011920929D;
/* 3314 */       this.motionY -= 0.02D;
/*      */       
/* 3316 */       if (this.isCollidedHorizontally && isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d, this.motionZ))
/*      */       {
/* 3318 */         this.motionY = 0.30000001192092896D;
/*      */       }
/*      */     }
/* 3321 */     else if (handleLavaMovement() && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).capabilities.isFlying)) {
/*      */       
/* 3323 */       double d = this.posY;
/*      */       
/* 3325 */       moveFlying(par1, par2, (this instanceof EntityFireElemental || this instanceof EntityEarthElemental) ? 0.1F : (!isHarmedByLava() ? 0.05F : 0.02F));
/* 3326 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 3327 */       this.motionX *= 0.5D;
/* 3328 */       this.motionY *= 0.5D;
/* 3329 */       this.motionZ *= 0.5D;
/* 3330 */       this.motionY -= 0.02D;
/*      */       
/* 3332 */       if (this.isCollidedHorizontally && isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d, this.motionZ))
/*      */       {
/* 3334 */         this.motionY = 0.30000001192092896D;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 3339 */       float var5, var3 = 0.91F;
/*      */       
/* 3341 */       if (this.onGround) {
/*      */         
/* 3343 */         var3 = 0.54600006F;
/*      */ 
/*      */         
/* 3346 */         BlockInfo block_info = getBlockRestingOn(0.1F);
/* 3347 */         int var4 = (block_info == null) ? 0 : block_info.block.blockID;
/*      */         
/* 3349 */         if (block_info != null && this.worldObj.getBlock(block_info.x, block_info.y + 1, block_info.z) == Block.snow && BlockSnow.getDepth(this.worldObj.getBlockMetadata(block_info.x, block_info.y + 1, block_info.z)) == 1) {
/* 3350 */           var4 = Block.snow.blockID;
/*      */         }
/* 3352 */         if (var4 > 0)
/*      */         {
/* 3354 */           var3 = (Block.blocksList[var4]).slipperiness * 0.91F;
/*      */         }
/*      */       } 
/*      */       
/* 3358 */       float var8 = 0.16277136F / var3 * var3 * var3;
/*      */ 
/*      */       
/* 3361 */       if (this.onGround) {
/*      */         
/* 3363 */         var5 = getAIMoveSpeed() * var8;
/*      */       }
/*      */       else {
/*      */         
/* 3367 */         var5 = this.jumpMovementFactor;
/*      */       } 
/*      */       
/* 3370 */       moveFlying(par1, par2, var5);
/* 3371 */       var3 = 0.91F;
/*      */       
/* 3373 */       if (this.onGround) {
/*      */         
/* 3375 */         var3 = 0.54600006F;
/*      */ 
/*      */         
/* 3378 */         BlockInfo block_info = getBlockRestingOn(0.1F);
/* 3379 */         int var6 = (block_info == null) ? 0 : block_info.block.blockID;
/*      */         
/* 3381 */         if (block_info != null && this.worldObj.getBlock(block_info.x, block_info.y + 1, block_info.z) == Block.snow && BlockSnow.getDepth(this.worldObj.getBlockMetadata(block_info.x, block_info.y + 1, block_info.z)) == 1) {
/* 3382 */           var6 = Block.snow.blockID;
/*      */         }
/* 3384 */         if (var6 > 0)
/*      */         {
/* 3386 */           var3 = (Block.blocksList[var6]).slipperiness * 0.91F;
/*      */         }
/*      */       } 
/*      */       
/* 3390 */       if (isOnLadder()) {
/*      */         
/* 3392 */         float var11 = 0.15F;
/*      */         
/* 3394 */         if (this.motionX < -var11)
/*      */         {
/* 3396 */           this.motionX = -var11;
/*      */         }
/*      */         
/* 3399 */         if (this.motionX > var11)
/*      */         {
/* 3401 */           this.motionX = var11;
/*      */         }
/*      */         
/* 3404 */         if (this.motionZ < -var11)
/*      */         {
/* 3406 */           this.motionZ = -var11;
/*      */         }
/*      */         
/* 3409 */         if (this.motionZ > var11)
/*      */         {
/* 3411 */           this.motionZ = var11;
/*      */         }
/*      */         
/* 3414 */         this.fallDistance = 0.0F;
/*      */         
/* 3416 */         if (this.motionY < -0.15D)
/*      */         {
/* 3418 */           this.motionY = -0.15D;
/*      */         }
/*      */         
/* 3421 */         this.motionX *= 0.800000011920929D; this.motionZ *= 0.800000011920929D;
/*      */         
/* 3423 */         boolean var7 = (isSneaking() && this instanceof EntityPlayer);
/*      */         
/* 3425 */         if (var7 && this.motionY < 0.0D)
/*      */         {
/* 3427 */           this.motionY = 0.0D;
/*      */         }
/*      */       } 
/*      */       
/* 3431 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/*      */       
/* 3433 */       if (this.isCollidedHorizontally && isOnLadder())
/*      */       {
/*      */         
/* 3436 */         this.motionY = getClimbingSpeed();
/*      */       }
/*      */       
/* 3439 */       int x = getBlockPosX();
/* 3440 */       int z = getBlockPosZ();
/*      */ 
/*      */ 
/*      */       
/* 3444 */       if (this.worldObj.isRemote && (!this.worldObj.blockExists(x, 0, z) || !(this.worldObj.getChunkFromBlockCoords(x, z)).isChunkLoaded)) {
/*      */         
/* 3446 */         if (this.posY > 0.0D)
/*      */         {
/* 3448 */           this.motionY = -0.1D;
/*      */         }
/*      */         else
/*      */         {
/* 3452 */           this.motionY = 0.0D;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 3457 */         this.motionY -= 0.08D;
/*      */       } 
/*      */       
/* 3460 */       this.motionY *= 0.9800000190734863D;
/* 3461 */       this.motionX *= var3;
/* 3462 */       this.motionZ *= var3;
/*      */     } 
/*      */     
/* 3465 */     this.prevLimbSwingAmount = this.limbSwingAmount;
/* 3466 */     double var10 = this.posX - this.prevPosX;
/* 3467 */     double var9 = this.posZ - this.prevPosZ;
/* 3468 */     float var12 = MathHelper.sqrt_double(var10 * var10 + var9 * var9) * 4.0F;
/*      */     
/* 3470 */     if (var12 > 1.0F)
/*      */     {
/* 3472 */       var12 = 1.0F;
/*      */     }
/*      */     
/* 3475 */     this.limbSwingAmount += (var12 - this.limbSwingAmount) * 0.4F;
/* 3476 */     this.limbSwing += this.limbSwingAmount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isAIEnabled() {
/* 3484 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAIMoveSpeed() {
/* 3492 */     return isAIEnabled() ? this.landMovementFactor : 0.1F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAIMoveSpeed(float par1) {
/* 3500 */     this.landMovementFactor = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityDamageResult attackEntityAsMob(Entity par1Entity) {
/* 3512 */     EntityDamageResult result = new EntityDamageResult(par1Entity);
/*      */     
/* 3514 */     setLastAttackTarget(par1Entity);
/*      */     
/* 3516 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean inBed() {
/* 3529 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void tryReinstateLastHarmingEntityFromDisk() {
/* 3534 */     if (!this.worldObj.isRemote && this.last_harming_entity_unique_id_string != null && this.last_harming_entity == null && this.ticksExisted % 10 == 0) {
/*      */       
/* 3536 */       this.last_harming_entity = getNearbyEntityByUniqueID(this.last_harming_entity_unique_id_string);
/*      */       
/* 3538 */       if (this.last_harming_entity != null) {
/* 3539 */         this.last_harming_entity_unique_id_string = null;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/* 3548 */     if (this.last_harming_entity_memory_countdown > 0) {
/* 3549 */       this.last_harming_entity_memory_countdown--;
/*      */     }
/* 3551 */     if (this.last_harming_entity != null && (this.last_harming_entity.isDead || this.last_harming_entity_memory_countdown == 0)) {
/* 3552 */       setLastHarmingEntity((Entity)null);
/*      */     }
/* 3554 */     if (this.last_harming_entity_memory_countdown == 0) {
/* 3555 */       this.last_harming_entity_unique_id_string = null;
/*      */     } else {
/* 3557 */       tryReinstateLastHarmingEntityFromDisk();
/*      */     } 
/* 3559 */     if (this.fleeing && this.ticksExisted % 100 == 0) {
/* 3560 */       considerStopFleeing();
/*      */     }
/* 3562 */     super.onUpdate();
/*      */     
/* 3564 */     if (!this.worldObj.isRemote) {
/*      */       
/* 3566 */       int var1 = getArrowCountInEntity();
/*      */       
/* 3568 */       if (var1 > 0) {
/*      */         
/* 3570 */         if (this.arrowHitTimer <= 0)
/*      */         {
/* 3572 */           this.arrowHitTimer = 20 * (30 - var1);
/*      */         }
/*      */         
/* 3575 */         this.arrowHitTimer--;
/*      */         
/* 3577 */         if (this.arrowHitTimer <= 0)
/*      */         {
/* 3579 */           setArrowCountInEntity(var1 - 1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3607 */     for (int var2 = 0; var2 < 5; var2++) {
/*      */       
/* 3609 */       ItemStack var3 = this.previousEquipment[var2];
/* 3610 */       ItemStack var4 = getCurrentItemOrArmor(var2);
/*      */       
/* 3612 */       if (!ItemStack.areItemStacksEqual(var4, var3)) {
/*      */         
/* 3614 */         if (this.worldObj instanceof WorldServer) {
/* 3615 */           ((WorldServer)this.worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(this, new Packet5PlayerInventory(this.entityId, var2, var4));
/*      */         }
/* 3617 */         if (var3 != null)
/*      */         {
/* 3619 */           this.attributeMap.removeAttributeModifiers(var3.getAttributeModifiers());
/*      */         }
/*      */         
/* 3622 */         if (var4 != null)
/*      */         {
/* 3624 */           this.attributeMap.applyAttributeModifiers(var4.getAttributeModifiers());
/*      */         }
/*      */         
/* 3627 */         this.previousEquipment[var2] = (var4 == null) ? null : var4.copy();
/*      */       } 
/*      */     } 
/*      */     
/* 3631 */     onLivingUpdate();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3636 */     double var9 = this.posX - this.prevPosX;
/* 3637 */     double var10 = this.posZ - this.prevPosZ;
/* 3638 */     float var5 = (float)(var9 * var9 + var10 * var10);
/* 3639 */     float var6 = this.renderYawOffset;
/* 3640 */     float var7 = 0.0F;
/*      */     
/* 3642 */     float var8 = 0.0F;
/*      */     
/* 3644 */     if (var5 > 0.0025000002F) {
/*      */       
/* 3646 */       var8 = 1.0F;
/* 3647 */       var7 = (float)Math.sqrt(var5) * 3.0F;
/* 3648 */       var6 = (float)Math.atan2(var10, var9) * 180.0F / 3.1415927F - 90.0F;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3654 */     if (this instanceof EntityLivestock) {
/* 3655 */       pushOutOfBlocks();
/*      */     }
/* 3657 */     if (this.swingProgress > 0.0F)
/*      */     {
/* 3659 */       var6 = this.rotationYaw;
/*      */     }
/*      */     
/* 3662 */     if (!this.onGround)
/*      */     {
/* 3664 */       var8 = 0.0F;
/*      */     }
/*      */ 
/*      */     
/* 3668 */     this.worldObj.theProfiler.startSection("headTurn");
/* 3669 */     var7 = func_110146_f(var6, var7);
/* 3670 */     this.worldObj.theProfiler.endSection();
/* 3671 */     this.worldObj.theProfiler.startSection("rangeChecks");
/*      */     
/* 3673 */     while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*      */     {
/* 3675 */       this.prevRotationYaw -= 360.0F;
/*      */     }
/*      */     
/* 3678 */     while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*      */     {
/* 3680 */       this.prevRotationYaw += 360.0F;
/*      */     }
/*      */     
/* 3683 */     while (this.renderYawOffset - this.prevRenderYawOffset < -180.0F)
/*      */     {
/* 3685 */       this.prevRenderYawOffset -= 360.0F;
/*      */     }
/*      */     
/* 3688 */     while (this.renderYawOffset - this.prevRenderYawOffset >= 180.0F)
/*      */     {
/* 3690 */       this.prevRenderYawOffset += 360.0F;
/*      */     }
/*      */     
/* 3693 */     while (this.rotationPitch - this.prevRotationPitch < -180.0F)
/*      */     {
/* 3695 */       this.prevRotationPitch -= 360.0F;
/*      */     }
/*      */     
/* 3698 */     while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*      */     {
/* 3700 */       this.prevRotationPitch += 360.0F;
/*      */     }
/*      */     
/* 3703 */     while (this.rotationYawHead - this.prevRotationYawHead < -180.0F)
/*      */     {
/* 3705 */       this.prevRotationYawHead -= 360.0F;
/*      */     }
/*      */     
/* 3708 */     while (this.rotationYawHead - this.prevRotationYawHead >= 180.0F)
/*      */     {
/* 3710 */       this.prevRotationYawHead += 360.0F;
/*      */     }
/*      */     
/* 3713 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected float func_110146_f(float par1, float par2) {
/* 3719 */     float var3 = MathHelper.wrapAngleTo180_float(par1 - this.renderYawOffset);
/* 3720 */     this.renderYawOffset += var3 * 0.3F;
/* 3721 */     float var4 = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.renderYawOffset);
/* 3722 */     boolean var5 = (var4 < -90.0F || var4 >= 90.0F);
/*      */     
/* 3724 */     if (var4 < -75.0F)
/*      */     {
/* 3726 */       var4 = -75.0F;
/*      */     }
/*      */     
/* 3729 */     if (var4 >= 75.0F)
/*      */     {
/* 3731 */       var4 = 75.0F;
/*      */     }
/*      */     
/* 3734 */     this.renderYawOffset = this.rotationYaw - var4;
/*      */     
/* 3736 */     if (var4 * var4 > 2500.0F)
/*      */     {
/* 3738 */       this.renderYawOffset += var4 * 0.2F;
/*      */     }
/*      */     
/* 3741 */     if (var5)
/*      */     {
/* 3743 */       par2 *= -1.0F;
/*      */     }
/*      */     
/* 3746 */     return par2;
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnInLoveHeartParticle() {
/* 3751 */     double var2 = this.rand.nextGaussian() * 0.02D;
/* 3752 */     double var4 = this.rand.nextGaussian() * 0.02D;
/* 3753 */     double var6 = this.rand.nextGaussian() * 0.02D;
/*      */ 
/*      */     
/* 3756 */     this.worldObj.spawnParticle(EnumParticle.heart, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, getFootPosY() + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var2, var4, var6);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean healsWithTime() {
/* 3761 */     return !isEntityUndead();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final float getSpeedBoostVsSlowDown() {
/* 3792 */     PotionEffect slowdown_effect = getActivePotionEffect(Potion.moveSlowdown);
/* 3793 */     PotionEffect haste_effect = getActivePotionEffect(Potion.moveSpeed);
/*      */     
/* 3795 */     float slow_amount = (slowdown_effect == null) ? 0.0F : ((slowdown_effect.getAmplifier() + 1) * -0.2F);
/* 3796 */     float haste_amount = (haste_effect == null) ? 0.0F : ((haste_effect.getAmplifier() + 1) * 0.2F);
/*      */     
/* 3798 */     if (this.isInWeb) {
/* 3799 */       slow_amount -= 0.75F;
/*      */     }
/* 3801 */     double overall_speed_modifier = (slow_amount + haste_amount);
/*      */     
/* 3803 */     if (overall_speed_modifier < 0.0D) {
/* 3804 */       overall_speed_modifier *= (1.0F - getResistanceToParalysis());
/*      */     }
/* 3806 */     return (float)overall_speed_modifier;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final float getSpeedBoostOrSlowDownFactor() {
/* 3812 */     return MathHelper.clamp_float(1.0F + getSpeedBoostVsSlowDown(), 0.0F, 4.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/* 3829 */     if (!this.worldObj.isRemote && getTicksExistedWithOffset() % 1000 == 0 && !(this instanceof EntityPlayer) && healsWithTime()) {
/* 3830 */       healByPercentage(0.1F);
/*      */     }
/* 3832 */     if (this.jumpTicks > 0)
/*      */     {
/* 3834 */       this.jumpTicks--;
/*      */     }
/*      */     
/* 3837 */     if (this.newPosRotationIncrements > 0) {
/*      */       
/* 3839 */       double var1 = this.posX + (this.newPosX - this.posX) / this.newPosRotationIncrements;
/* 3840 */       double var3 = this.posY + (this.newPosY - this.posY) / this.newPosRotationIncrements;
/* 3841 */       double var5 = this.posZ + (this.newPosZ - this.posZ) / this.newPosRotationIncrements;
/* 3842 */       double var7 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - this.rotationYaw);
/* 3843 */       this.rotationYaw = (float)(this.rotationYaw + var7 / this.newPosRotationIncrements);
/* 3844 */       this.rotationPitch = (float)(this.rotationPitch + (this.newRotationPitch - this.rotationPitch) / this.newPosRotationIncrements);
/* 3845 */       this.newPosRotationIncrements--;
/* 3846 */       setPosition(var1, var3, var5);
/* 3847 */       setRotation(this.rotationYaw, this.rotationPitch);
/*      */ 
/*      */     
/*      */     }
/* 3851 */     else if (this.worldObj.isRemote && !(this instanceof ClientPlayer)) {
/*      */       
/* 3853 */       this.motionX *= 0.98D;
/* 3854 */       this.motionY *= 0.98D;
/* 3855 */       this.motionZ *= 0.98D;
/*      */     } 
/*      */     
/* 3858 */     if (Math.abs(this.motionX) < 0.005D)
/*      */     {
/* 3860 */       this.motionX = 0.0D;
/*      */     }
/*      */     
/* 3863 */     if (Math.abs(this.motionY) < 0.005D)
/*      */     {
/* 3865 */       this.motionY = 0.0D;
/*      */     }
/*      */     
/* 3868 */     if (Math.abs(this.motionZ) < 0.005D)
/*      */     {
/* 3870 */       this.motionZ = 0.0D;
/*      */     }
/*      */     
/* 3873 */     this.worldObj.theProfiler.startSection("ai");
/*      */     
/* 3875 */     if (isMovementBlocked()) {
/*      */       
/* 3877 */       this.isJumping = false;
/* 3878 */       this.moveStrafing = 0.0F;
/* 3879 */       this.moveForward = 0.0F;
/* 3880 */       this.randomYawVelocity = 0.0F;
/*      */ 
/*      */     
/*      */     }
/* 3884 */     else if (!this.worldObj.isRemote || this instanceof ClientPlayer) {
/*      */       
/* 3886 */       if (isAIEnabled()) {
/*      */         
/* 3888 */         this.worldObj.theProfiler.startSection("newAi");
/* 3889 */         updateAITasks();
/* 3890 */         this.worldObj.theProfiler.endSection();
/*      */       }
/*      */       else {
/*      */         
/* 3894 */         this.worldObj.theProfiler.startSection("oldAi");
/* 3895 */         updateEntityActionState();
/* 3896 */         this.worldObj.theProfiler.endSection();
/* 3897 */         this.rotationYawHead = this.rotationYaw;
/*      */       } 
/*      */     } 
/*      */     
/* 3901 */     this.worldObj.theProfiler.endSection();
/* 3902 */     this.worldObj.theProfiler.startSection("jump");
/*      */ 
/*      */ 
/*      */     
/* 3906 */     if (this.isJumping) {
/*      */ 
/*      */       
/* 3909 */       if (this instanceof EntityCubic || (!isInWater() && !handleLavaMovement()))
/*      */       {
/* 3911 */         if (this.onGround && this.jumpTicks == 0)
/*      */         {
/* 3913 */           jump();
/* 3914 */           this.jumpTicks = 10;
/*      */         }
/*      */       
/*      */       }
/* 3918 */       else if (this.ridingEntity == null)
/*      */       {
/* 3920 */         Material material_at_feet = this.worldObj.getBlockMaterial(getBlockPosX(), getFootBlockPosY(), getBlockPosZ());
/* 3921 */         boolean outside_of_liquid = (material_at_feet != Material.water && material_at_feet != Material.lava);
/*      */ 
/*      */         
/* 3924 */         boolean in_waterfall = (this.worldObj.getBlockMetadata(getBlockPosX(), getFootBlockPosY(), getBlockPosZ()) == 9 && this.worldObj.getBlockId(getBlockPosX(), getFootBlockPosY(), getBlockPosZ()) == 9 && this.worldObj.getBlockMetadata(getBlockPosX(), getHeadBlockPosY(), getBlockPosZ()) == 9 && this.worldObj.getBlockId(getBlockPosX(), getHeadBlockPosY(), getBlockPosZ()) == 9);
/*      */ 
/*      */         
/* 3927 */         float swim_modifier = (outside_of_liquid || in_waterfall) ? 0.4375F : 1.0F;
/*      */         
/* 3929 */         if (this instanceof EntityPlayer) {
/*      */           
/* 3931 */           EntityPlayer player = (EntityPlayer)this;
/*      */           
/* 3933 */           if (this.worldObj.isRemote) {
/* 3934 */             player.addHungerClientSide(0.01F);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 3939 */           boolean has_both_hands_occupied = (player.itemInUse != null && player.itemInUse.getItem() instanceof ItemBow);
/*      */ 
/*      */           
/* 3942 */           if (has_both_hands_occupied && isSuspendedInLiquid())
/*      */           {
/*      */ 
/*      */             
/* 3946 */             swim_modifier *= 0.4375F;
/*      */           }
/*      */           
/* 3949 */           float overall_speed_modifier = getSpeedBoostVsSlowDown();
/*      */           
/* 3951 */           if (overall_speed_modifier < 0.0F && 1.0F + overall_speed_modifier < swim_modifier) {
/* 3952 */             swim_modifier = 1.0F + overall_speed_modifier;
/*      */           }
/* 3954 */           if (swim_modifier < 1.0F && this.isCollidedHorizontally && !has_both_hands_occupied)
/*      */           {
/* 3956 */             if (!outside_of_liquid && !in_waterfall) {
/* 3957 */               swim_modifier = swim_modifier * 0.7F + 0.3F;
/*      */             }
/*      */           }
/*      */         } 
/* 3961 */         if (swim_modifier < 0.0F) {
/* 3962 */           swim_modifier = 0.0F;
/* 3963 */         } else if (swim_modifier > 1.0F) {
/* 3964 */           swim_modifier = 1.0F;
/*      */         } 
/*      */         
/* 3967 */         this.motionY += 0.04D * swim_modifier;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 3972 */       this.jumpTicks = 0;
/*      */     } 
/*      */     
/* 3975 */     this.worldObj.theProfiler.endSection();
/* 3976 */     this.worldObj.theProfiler.startSection("travel");
/* 3977 */     this.moveStrafing *= 0.98F;
/* 3978 */     this.moveForward *= 0.98F;
/* 3979 */     this.randomYawVelocity *= 0.9F;
/* 3980 */     moveEntityWithHeading(this.moveStrafing, this.moveForward);
/* 3981 */     this.worldObj.theProfiler.endSection();
/* 3982 */     this.worldObj.theProfiler.startSection("push");
/*      */     
/* 3984 */     if (!this.worldObj.isRemote) {
/*      */       
/* 3986 */       this.is_collided_with_entities = false;
/* 3987 */       collideWithNearbyEntities();
/*      */     } 
/*      */     
/* 3990 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateAITasks() {}
/*      */   
/*      */   protected void collideWithNearbyEntities() {
/* 3997 */     List<Entity> var1 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
/*      */     
/* 3999 */     if (var1 != null && !var1.isEmpty())
/*      */     {
/* 4001 */       for (int var2 = 0; var2 < var1.size(); var2++) {
/*      */         
/* 4003 */         Entity var3 = var1.get(var2);
/*      */         
/* 4005 */         if (var3.canBePushed()) {
/*      */           
/* 4007 */           collideWithEntity(var3);
/* 4008 */           this.is_collided_with_entities = true;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void collideWithEntity(Entity par1Entity) {
/* 4017 */     par1Entity.applyEntityCollision(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRidden() {
/* 4025 */     super.updateRidden();
/*      */ 
/*      */     
/* 4028 */     this.fallDistance = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 4037 */     this.yOffset = 0.0F;
/* 4038 */     this.newPosX = par1;
/* 4039 */     this.newPosY = par3;
/* 4040 */     this.newPosZ = par5;
/* 4041 */     this.newRotationYaw = par7;
/* 4042 */     this.newRotationPitch = par8;
/* 4043 */     this.newPosRotationIncrements = par9;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateAITick() {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateEntityActionState() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJumping(boolean par1) {
/* 4058 */     this.isJumping = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onItemPickup(Entity par1Entity, int par2) {
/* 4066 */     if (!par1Entity.isDead && !this.worldObj.isRemote) {
/*      */       
/* 4068 */       EntityTracker var3 = ((WorldServer)this.worldObj).getEntityTracker();
/*      */       
/* 4070 */       if (par1Entity instanceof EntityItem)
/*      */       {
/* 4072 */         var3.sendPacketToAllPlayersTrackingEntity(par1Entity, new Packet22Collect(par1Entity.entityId, this.entityId));
/*      */       }
/*      */       
/* 4075 */       if (par1Entity instanceof EntityArrow)
/*      */       {
/* 4077 */         var3.sendPacketToAllPlayersTrackingEntity(par1Entity, new Packet22Collect(par1Entity.entityId, this.entityId));
/*      */       }
/*      */       
/* 4080 */       if (par1Entity instanceof EntityXPOrb)
/*      */       {
/* 4082 */         var3.sendPacketToAllPlayersTrackingEntity(par1Entity, new Packet22Collect(par1Entity.entityId, this.entityId));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean canSeeEntity(Entity par1Entity) {
/* 4089 */     return canSeeEntity(par1Entity, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSeeEntity(Entity par1Entity, boolean ignore_leaves) {
/* 4102 */     boolean seen = par1Entity.canEntityBeSeenFrom(this.posX, getEyePosY(), this.posZ, Double.MAX_VALUE, ignore_leaves);
/*      */     
/* 4104 */     if (seen) {
/* 4105 */       onEntitySeen(par1Entity);
/*      */     }
/* 4107 */     return seen;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntitySeen(Entity entity) {
/* 4114 */     if (this instanceof EntityPlayer) {
/*      */       
/* 4116 */       entity.refreshDespawnCounter(-1200);
/* 4117 */     } else if (entity instanceof EntityPlayer) {
/*      */       
/* 4119 */       refreshDespawnCounter(-1200);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getLookVec() {
/* 4127 */     return getLook(1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getLook(float par1) {
/* 4140 */     if (par1 == 1.0F) {
/*      */       
/* 4142 */       float f1 = MathHelper.cos(-this.rotationYaw * 0.017453292F - 3.1415927F);
/* 4143 */       float f2 = MathHelper.sin(-this.rotationYaw * 0.017453292F - 3.1415927F);
/* 4144 */       float f3 = -MathHelper.cos(-this.rotationPitch * 0.017453292F);
/* 4145 */       float f4 = MathHelper.sin(-this.rotationPitch * 0.017453292F);
/* 4146 */       return this.worldObj.getWorldVec3Pool().getVecFromPool((f2 * f3), f4, (f1 * f3));
/*      */     } 
/*      */ 
/*      */     
/* 4150 */     float var2 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * par1;
/* 4151 */     float var3 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * par1;
/* 4152 */     float var4 = MathHelper.cos(-var3 * 0.017453292F - 3.1415927F);
/* 4153 */     float var5 = MathHelper.sin(-var3 * 0.017453292F - 3.1415927F);
/* 4154 */     float var6 = -MathHelper.cos(-var2 * 0.017453292F);
/* 4155 */     float var7 = MathHelper.sin(-var2 * 0.017453292F);
/* 4156 */     return this.worldObj.getWorldVec3Pool().getVecFromPool((var5 * var6), var7, (var4 * var6));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSwingProgress(float par1) {
/* 4165 */     float var2 = this.swingProgress - this.prevSwingProgress;
/*      */     
/* 4167 */     if (var2 < 0.0F)
/*      */     {
/* 4169 */       var2++;
/*      */     }
/*      */     
/* 4172 */     return this.prevSwingProgress + var2 * par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getPosition(float par1) {
/* 4180 */     if (par1 == 1.0F)
/*      */     {
/* 4182 */       return this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
/*      */     }
/*      */ 
/*      */     
/* 4186 */     double var2 = this.prevPosX + (this.posX - this.prevPosX) * par1;
/* 4187 */     double var4 = this.prevPosY + (this.posY - this.prevPosY) * par1;
/* 4188 */     double var6 = this.prevPosZ + (this.posZ - this.prevPosZ) * par1;
/* 4189 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(var2, var4, var6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getEyePosition(float par1) {
/* 4196 */     Vec3 vec3 = getPosition(par1);
/*      */ 
/*      */ 
/*      */     
/* 4200 */     double delta_y = getEyePosY() - this.posY;
/* 4201 */     vec3.yCoord += delta_y;
/*      */     
/* 4203 */     return vec3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeCollidedWith() {
/* 4231 */     return (!this.isDead && getHealth() > 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePushed() {
/* 4240 */     return (!this.isDead && getHealth() > 0.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getEyeHeight() {
/* 4245 */     return this.height * 0.85F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getFootPosY() {
/* 4253 */     return this.posY;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFootBlockPosY() {
/* 4259 */     return MathHelper.floor_double(getFootPosY() + 9.999999747378752E-5D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getEyePosY() {
/* 4265 */     return this.posY + getEyeHeight();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEyeBlockPosY() {
/* 4271 */     return MathHelper.floor_double(getEyePosY());
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHeadBlockPosY() {
/* 4276 */     return MathHelper.floor_double(getFootPosY() + 9.999999747378752E-5D + this.height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBeenAttacked() {
/* 4284 */     this.velocityChanged = (this.rand.nextDouble() >= getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue());
/*      */   }
/*      */ 
/*      */   
/*      */   public float getRotationYawHead() {
/* 4289 */     return this.rotationYawHead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotationYawHead(float par1) {
/* 4297 */     this.rotationYawHead = par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAbsorptionAmount() {
/* 4302 */     return this.field_110151_bq;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAbsorptionAmount(float par1) {
/* 4307 */     if (par1 < 0.0F)
/*      */     {
/* 4309 */       par1 = 0.0F;
/*      */     }
/*      */     
/* 4312 */     this.field_110151_bq = par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public Team getTeam() {
/* 4317 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOnSameTeam(EntityLivingBase par1EntityLivingBase) {
/* 4322 */     return isOnTeam(par1EntityLivingBase.getTeam());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOnTeam(Team par1Team) {
/* 4330 */     return (getTeam() != null) ? getTeam().isSameTeam(par1Team) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void healByPercentage(float percentage) {
/* 4335 */     if (this.isDead) {
/*      */       return;
/*      */     }
/* 4338 */     float health = getHealth();
/*      */     
/* 4340 */     if (health > 0.0F) {
/*      */       
/* 4342 */       float max_health = getMaxHealth();
/*      */       
/* 4344 */       if (health < max_health)
/*      */       {
/* 4346 */         setHealth(health + max_health * percentage);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onKillEntity(EntityLivingBase par1EntityLivingBase) {
/* 4354 */     par1EntityLivingBase.was_killed_by = this;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean preysUpon(Entity entity) {
/* 4359 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addContainedItem(Item item) {
/* 4364 */     addContainedItem(item.itemID);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addContainedItem(int item_id) {
/* 4369 */     this.contained_items.add(Integer.valueOf(item_id));
/*      */   }
/*      */ 
/*      */   
/*      */   public String convertContainedItemsToString() {
/* 4374 */     if (this.contained_items.isEmpty()) {
/* 4375 */       return "";
/*      */     }
/* 4377 */     Iterator<String> i = this.contained_items.iterator();
/*      */     
/* 4379 */     String s = "";
/*      */     
/* 4381 */     while (i.hasNext())
/*      */     {
/* 4383 */       s = s + "|" + i.next();
/*      */     }
/*      */     
/* 4386 */     return s;
/*      */   }
/*      */ 
/*      */   
/*      */   public void obtainContainedItemsFromString(String s) {
/* 4391 */     char[] char_array = s.toCharArray();
/* 4392 */     int item_id = 0;
/* 4393 */     int digit_power = 0;
/*      */     
/* 4395 */     for (int i = char_array.length - 1; i >= 0; i--) {
/*      */       
/* 4397 */       char c = char_array[i];
/*      */       
/* 4399 */       if (c == '|') {
/*      */         
/* 4401 */         addContainedItem(item_id);
/*      */         
/* 4403 */         item_id = 0;
/* 4404 */         digit_power = 0;
/*      */       }
/*      */       else {
/*      */         
/* 4408 */         item_id = (int)(item_id + (c - 48) * Math.pow(10.0D, digit_power));
/* 4409 */         digit_power++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack[] getContainedItems() {
/* 4416 */     if (this.contained_items == null || this.contained_items.size() == 0) {
/* 4417 */       return null;
/*      */     }
/* 4419 */     ItemStack[] item_stacks = new ItemStack[this.contained_items.size()];
/*      */     
/* 4421 */     for (int i = 0; i < this.contained_items.size(); i++) {
/* 4422 */       item_stacks[i] = new ItemStack(((Integer)this.contained_items.get(i)).intValue());
/*      */     }
/* 4424 */     return item_stacks;
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropContainedItems() {
/* 4429 */     Iterator<Integer> i = this.contained_items.iterator();
/*      */     
/* 4431 */     while (i.hasNext()) {
/* 4432 */       dropItem(((Integer)i.next()).intValue(), 1);
/*      */     }
/*      */   }
/*      */   
/*      */   public static float getScale() {
/* 4437 */     return 1.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeSound(String sound) {
/* 4442 */     makeSound(sound, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeSound(String sound, float volume_multiplier, float pitch_multiplier) {
/* 4447 */     if (isZevimrgvInTournament()) {
/*      */       return;
/*      */     }
/* 4450 */     this.worldObj.playSoundAtEntity(this, sound, getSoundVolume(sound) * volume_multiplier, getSoundPitch(sound) * pitch_multiplier);
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeLongDistanceSound(String sound) {
/* 4455 */     makeLongDistanceSound(sound, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeLongDistanceSound(String sound, float volume_multiplier, float pitch_multiplier) {
/* 4460 */     this.worldObj.playLongDistanceSoundAtEntity(this, sound, getSoundVolume(sound) * volume_multiplier, getSoundPitch(sound) * pitch_multiplier);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastHarmingEntity(Entity entity) {
/* 4465 */     this.last_harming_entity = entity;
/* 4466 */     this.last_harming_entity_memory_countdown = (entity == null) ? 0 : (900 + this.rand.nextInt(200));
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getLastHarmingEntity() {
/* 4471 */     return this.last_harming_entity;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBadlyWounded() {
/* 4476 */     return (getHealth() / getMaxHealth() < 0.2F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean fleesWhenBadlyWounded() {
/* 4481 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean considerFleeing() {
/* 4486 */     Entity last_harming_entity = getLastHarmingEntity();
/*      */     
/* 4488 */     if (last_harming_entity == null || isEntityUndead() || !fleesWhenBadlyWounded() || !isBadlyWounded() || getDistanceToEntity(last_harming_entity) > 32.0F) {
/* 4489 */       this.has_decided_to_flee = false;
/*      */     } else {
/* 4491 */       this.has_decided_to_flee = (this.rand.nextInt(2) == 0);
/*      */     } 
/* 4493 */     return this.has_decided_to_flee;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean considerStopFleeing() {
/* 4498 */     Entity last_harming_entity = getLastHarmingEntity();
/*      */     
/* 4500 */     if (last_harming_entity == null || !fleesWhenBadlyWounded() || !isBadlyWounded()) {
/*      */       
/* 4502 */       this.has_decided_to_flee = false;
/* 4503 */       this.fleeing = false;
/* 4504 */       return true;
/*      */     } 
/*      */     
/* 4507 */     if (getDistanceToEntity(last_harming_entity) > 40.0F) {
/*      */       
/* 4509 */       this.fleeing = false;
/* 4510 */       return true;
/*      */     } 
/*      */     
/* 4513 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onFleeing() {}
/*      */   
/*      */   public boolean hasCurse(Curse curse, boolean learn_effect_if_so) {
/* 4520 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getExperienceValue() {
/* 4525 */     return 5;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canCatchFire() {
/* 4530 */     return isHarmedByFire();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByFire() {
/* 4535 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByLava() {
/* 4540 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByPepsin() {
/* 4545 */     return isEntityBiologicallyAlive();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHoldingItemThatPreventsHandDamage() {
/* 4550 */     Item held_item = getHeldItem();
/*      */     
/* 4552 */     return (held_item != null && held_item.preventsHandDamage());
/*      */   }
/*      */ 
/*      */   
/*      */   public float getHeldItemReachBonus() {
/* 4557 */     Item held_item = getHeldItem();
/*      */     
/* 4559 */     return (held_item == null) ? 0.0F : held_item.getReachBonus();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getFootPos() {
/* 4565 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, getFootPosY(), this.posZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getFootPosPlusFractionOfHeight(float fraction) {
/* 4570 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, getFootPosY() + (this.height * fraction), this.posZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public final Vec3 getEyePos() {
/* 4575 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, getEyePosY(), this.posZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getPrimaryPointOfAttack() {
/* 4580 */     return isEntityPlayer() ? getEyePos() : getFootPosPlusFractionOfHeight(0.75F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByDrowning() {
/* 4585 */     return (breathesAir() && !canBreatheUnderwater());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeDamagedByCacti() {
/* 4590 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canTakeDamageFromThrownEggs() {
/* 4595 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeKnockedBack() {
/* 4600 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public List getTargetPoints() {
/* 4605 */     List<Vec3> target_points = new ArrayList();
/*      */     
/* 4607 */     target_points.add(getFootPosPlusFractionOfHeight(0.5F));
/* 4608 */     target_points.add(getFootPosPlusFractionOfHeight(0.9F));
/* 4609 */     target_points.add(getFootPosPlusFractionOfHeight(0.1F));
/*      */     
/* 4611 */     return target_points;
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getCenterPoint() {
/* 4616 */     return getFootPosPlusFractionOfHeight(0.5F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canOnlyPerformWeakStrike() {
/* 4621 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHoldingAnEffectiveTool(Block block, int metadata) {
/* 4626 */     ItemStack held_item_stack = getHeldItemStack();
/*      */     
/* 4628 */     if (held_item_stack == null || !held_item_stack.getItem().isTool()) {
/* 4629 */       return false;
/*      */     }
/* 4631 */     ItemTool tool = (ItemTool)held_item_stack.getItem();
/*      */     
/* 4633 */     return tool.isEffectiveAgainstBlock(block, metadata);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canEntityBeSeenFrom(double x, double y, double z, double max_range_sq, boolean ignore_leaves) {
/* 4639 */     Vec3 origin = this.worldObj.getVec3(x, y, z);
/*      */     
/* 4641 */     if (getCenterPoint().squareDistanceTo(origin) > max_range_sq) {
/* 4642 */       return false;
/*      */     }
/* 4644 */     RaycastPolicies policies = RaycastPolicies.for_vision(ignore_leaves);
/*      */     
/* 4646 */     return (this.worldObj.checkForNoBlockCollision(origin, getFootPosPlusFractionOfHeight(0.25F), policies) || this.worldObj.checkForNoBlockCollision(origin, getFootPosPlusFractionOfHeight(0.75F), policies));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final RaycastCollision getBlockCollisionAlongLookVector(RaycastPolicies policies, float partial_tick, double distance_to_limit) {
/* 4654 */     Vec3 origin = getEyePosition(partial_tick);
/* 4655 */     Vec3 limit = origin.applyDirectionAndDistance(getLook(partial_tick), distance_to_limit);
/*      */     
/* 4657 */     return this.worldObj.getBlockCollisionForPolicies(origin, limit, policies, this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotationForLookingAt(Vec3 target_pos) {
/* 4663 */     Vec3 eye_pos = getEyePos();
/*      */     
/* 4665 */     setRotation(EntityLookHelper.getYawForLookingAt(eye_pos, target_pos), EntityLookHelper.getPitchForLookingAt(eye_pos, target_pos));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInRain() {
/* 4670 */     return this.worldObj.isInRain(getBlockPosX(), MathHelper.floor_double(getFootPosY() + this.height), getBlockPosZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInPrecipitation() {
/* 4675 */     return this.worldObj.isPrecipitatingAt(getBlockPosX(), MathHelper.floor_double(getFootPosY() + this.height), getBlockPosZ());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSilkHarvestBlock(Block block, int metadata) {
/* 4681 */     if (!block.canSilkHarvest(metadata) || !EnchantmentHelper.getSilkTouchModifier(this)) {
/* 4682 */       return false;
/*      */     }
/* 4684 */     ItemStack held_item_stack = getHeldItemStack();
/*      */     
/* 4686 */     return (held_item_stack != null && held_item_stack.isTool() && held_item_stack.getItemAsTool().isEffectiveAgainstBlock(block, metadata));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Block getBlockAtFeet() {
/* 4692 */     return this.worldObj.getBlock(getBlockPosX(), getFootBlockPosY(), getBlockPosZ());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Material getBlockMaterialAtFeet() {
/* 4698 */     return this.worldObj.getBlockMaterial(getBlockPosX(), getFootBlockPosY(), getBlockPosZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Block getBlockBelow() {
/* 4708 */     return this.worldObj.getBlock(getBlockPosX(), getFootBlockPosY() - 1, getBlockPosZ());
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLivingBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */