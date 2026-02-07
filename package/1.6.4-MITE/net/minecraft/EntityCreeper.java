/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityCreeper
/*     */   extends EntityMob
/*     */ {
/*     */   private int lastActiveTime;
/*     */   private int timeSinceIgnited;
/*  15 */   private int fuseTime = 30;
/*     */ 
/*     */ 
/*     */   
/*  19 */   protected float explosionRadius = 1.0F;
/*     */ 
/*     */   
/*     */   private boolean has_exploded;
/*     */   
/*     */   public int recently_took_damage_from_conspicuous_cactus;
/*     */ 
/*     */   
/*     */   public EntityCreeper(World par1World) {
/*  28 */     super(par1World);
/*  29 */     setSize(this.width * getScale(), this.height * getScale());
/*     */     
/*  31 */     this.tasks.addTask(1, new EntityAISwimming(this));
/*  32 */     this.tasks.addTask(2, new EntityAICreeperSwell(this));
/*  33 */     this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
/*  34 */     this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, false));
/*  35 */     this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
/*  36 */     this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  37 */     this.tasks.addTask(6, new EntityAILookIdle(this));
/*  38 */     this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  39 */     this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  44 */     super.applyEntityAttributes();
/*  45 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAIEnabled() {
/*  53 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxSafePointTries() {
/*  61 */     return (getAttackTarget() == null) ? 3 : (3 + (int)(getHealth() - 1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fall(float par1) {
/*  69 */     super.fall(par1);
/*  70 */     this.timeSinceIgnited = (int)(this.timeSinceIgnited + par1 * 1.5F);
/*     */     
/*  72 */     if (this.timeSinceIgnited > this.fuseTime - 5)
/*     */     {
/*  74 */       this.timeSinceIgnited = this.fuseTime - 5;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  80 */     super.entityInit();
/*  81 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)-1));
/*  82 */     this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  90 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/*  92 */     if (this.dataWatcher.getWatchableObjectByte(17) == 1)
/*     */     {
/*  94 */       par1NBTTagCompound.setBoolean("powered", true);
/*     */     }
/*     */     
/*  97 */     par1NBTTagCompound.setShort("Fuse", (short)this.fuseTime);
/*     */     
/*  99 */     par1NBTTagCompound.setFloat("ExplosionRadius", this.explosionRadius);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 107 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 108 */     this.dataWatcher.updateObject(17, Byte.valueOf((byte)(par1NBTTagCompound.getBoolean("powered") ? 1 : 0)));
/*     */     
/* 110 */     if (par1NBTTagCompound.hasKey("Fuse"))
/*     */     {
/* 112 */       this.fuseTime = par1NBTTagCompound.getShort("Fuse");
/*     */     }
/*     */     
/* 115 */     if (par1NBTTagCompound.hasKey("ExplosionRadius"))
/*     */     {
/*     */       
/* 118 */       this.explosionRadius = par1NBTTagCompound.getFloat("ExplosionRadius");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 127 */     if (this.recently_took_damage_from_conspicuous_cactus > 0) {
/* 128 */       this.recently_took_damage_from_conspicuous_cactus--;
/*     */     }
/* 130 */     if (isEntityAlive()) {
/*     */       
/* 132 */       this.lastActiveTime = this.timeSinceIgnited;
/* 133 */       int var1 = getCreeperState();
/*     */       
/* 135 */       if (var1 > 0 && this.timeSinceIgnited == 0)
/*     */       {
/* 137 */         playSound("random.fuse", 1.0F, 0.5F);
/*     */       }
/*     */       
/* 140 */       this.timeSinceIgnited += var1;
/*     */       
/* 142 */       if (this.timeSinceIgnited < 0)
/*     */       {
/* 144 */         this.timeSinceIgnited = 0;
/*     */       }
/*     */       
/* 147 */       if (this.timeSinceIgnited >= this.fuseTime) {
/*     */         
/* 149 */         this.timeSinceIgnited = this.fuseTime;
/*     */         
/* 151 */         if (!this.worldObj.isRemote) {
/*     */           
/* 153 */           boolean var2 = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
/*     */           
/* 155 */           float explosion_size_vs_blocks = this.explosionRadius * 0.715F;
/* 156 */           float explosion_size_vs_living_entities = this.explosionRadius * 1.1F;
/*     */           
/* 158 */           if (getPowered()) {
/*     */ 
/*     */             
/* 161 */             this.worldObj.createExplosion(this, this.posX, this.posY + (this.height / 4.0F), this.posZ, explosion_size_vs_blocks * 2.0F, explosion_size_vs_living_entities * 2.0F, var2);
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 166 */             this.worldObj.createExplosion(this, this.posX, this.posY + (this.height / 4.0F), this.posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities, var2);
/*     */           } 
/*     */           
/* 169 */           this.has_exploded = true;
/*     */           
/* 171 */           entityFX(EnumEntityFX.frags);
/*     */           
/* 173 */           setDead();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 178 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 186 */     return "mob.creeper.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 194 */     return "mob.creeper.death";
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
/*     */   public void onEntityDamaged(DamageSource damage_source, float amount) {
/* 208 */     if (damage_source.isCactus() && BlockCactus.getKillCount(this.worldObj, damage_source.block_x, damage_source.block_y, damage_source.block_z) > 1 && this.rand.nextInt(2) == 0) {
/* 209 */       this.recently_took_damage_from_conspicuous_cactus = 120;
/*     */     }
/* 211 */     super.onEntityDamaged(damage_source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource par1DamageSource) {
/* 219 */     super.onDeath(par1DamageSource);
/*     */ 
/*     */     
/* 222 */     if (par1DamageSource.getResponsibleEntity() instanceof EntitySkeleton) {
/*     */       
/* 224 */       int var2 = Item.record13.itemID + this.rand.nextInt(Item.recordWait.itemID - Item.record13.itemID + 1);
/* 225 */       dropItem(var2, 1);
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
/*     */   public boolean getPowered() {
/* 246 */     return (this.dataWatcher.getWatchableObjectByte(17) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCreeperFlashIntensity(float par1) {
/* 254 */     return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * par1) / (this.fuseTime - 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 263 */     return (this.recentlyHit > 0 || this.rand.nextInt(3) == 0) ? Item.gunpowder.itemID : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCreeperState() {
/* 271 */     return this.dataWatcher.getWatchableObjectByte(16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreeperState(int par1) {
/* 279 */     this.dataWatcher.updateObject(16, Byte.valueOf((byte)par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {
/* 287 */     super.onStruckByLightning(par1EntityLightningBolt);
/* 288 */     this.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 293 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasExploded() {
/* 298 */     return this.has_exploded;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFragParticle() {
/* 303 */     return Item.fragsCreeper.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSeeEntity(Entity entity, boolean ignore_leaves) {
/* 309 */     if (entity.canEntityBeSeenFrom(this.posX, getFootPosY() + (this.height / 4.0F), this.posZ, Double.MAX_VALUE, ignore_leaves)) {
/*     */       
/* 311 */       onEntitySeen(entity);
/* 312 */       return true;
/*     */     } 
/*     */     
/* 315 */     return super.canSeeEntity(entity, ignore_leaves);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawBackFaces() {
/* 320 */     return (getPowered() || isWearingItems(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeAttackedBy(EntityLivingBase attacker) {
/* 325 */     if (this.rand.nextInt(4) > 0 && attacker.hasCurse(Curse.fear_of_creepers, true)) {
/* 326 */       return false;
/*     */     }
/* 328 */     return super.canBeAttackedBy(attacker);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityCreeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */