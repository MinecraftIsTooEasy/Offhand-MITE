/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class EntityIronGolem
/*     */   extends EntityGolem
/*     */ {
/*     */   private int homeCheckTimer;
/*     */   Village villageObj;
/*     */   private int attackTimer;
/*     */   private int holdRoseTick;
/*     */   
/*     */   public EntityIronGolem(World par1World) {
/*  13 */     super(par1World);
/*  14 */     setSize(1.4F, 2.9F);
/*  15 */     getNavigator().setAvoidsWater(true);
/*  16 */     this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
/*  17 */     this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
/*  18 */     this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
/*  19 */     this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  20 */     this.tasks.addTask(5, new EntityAILookAtVillager(this));
/*  21 */     this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
/*  22 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  23 */     this.tasks.addTask(8, new EntityAILookIdle(this));
/*  24 */     this.targetTasks.addTask(1, new EntityAIDefendVillage(this));
/*  25 */     this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
/*  26 */     this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  31 */     super.entityInit();
/*  32 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAIEnabled() {
/*  40 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAITick() {
/*  48 */     if (--this.homeCheckTimer <= 0) {
/*     */       
/*  50 */       this.homeCheckTimer = 70 + this.rand.nextInt(50);
/*  51 */       this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);
/*     */       
/*  53 */       if (this.villageObj == null) {
/*     */         
/*  55 */         detachHome();
/*     */       }
/*     */       else {
/*     */         
/*  59 */         ChunkCoordinates var1 = this.villageObj.getCenter();
/*  60 */         setHomeArea(var1.posX, var1.posY, var1.posZ, (int)(this.villageObj.getVillageRadius() * 0.6F));
/*     */       } 
/*     */     } 
/*     */     
/*  64 */     super.updateAITick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  69 */     super.applyEntityAttributes();
/*  70 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(100.0D);
/*  71 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int decreaseAirSupply(int par1) {
/*  79 */     return par1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void collideWithEntity(Entity par1Entity) {
/*  84 */     if (par1Entity instanceof IMob && getRNG().nextInt(20) == 0)
/*     */     {
/*  86 */       setAttackTarget((EntityLivingBase)par1Entity);
/*     */     }
/*     */     
/*  89 */     super.collideWithEntity(par1Entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  98 */     super.onLivingUpdate();
/*     */     
/* 100 */     if (this.attackTimer > 0)
/*     */     {
/* 102 */       this.attackTimer--;
/*     */     }
/*     */     
/* 105 */     if (this.holdRoseTick > 0)
/*     */     {
/* 107 */       this.holdRoseTick--;
/*     */     }
/*     */     
/* 110 */     if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0) {
/*     */       
/* 112 */       int var1 = MathHelper.floor_double(this.posX);
/* 113 */       int var2 = MathHelper.floor_double(this.posY - 0.20000000298023224D - this.yOffset);
/* 114 */       int var3 = MathHelper.floor_double(this.posZ);
/* 115 */       int var4 = this.worldObj.getBlockId(var1, var2, var3);
/*     */       
/* 117 */       if (var4 > 0)
/*     */       {
/*     */         
/* 120 */         this.worldObj.spawnParticleEx(EnumParticle.tilecrack, var4, this.worldObj.getBlockMetadata(var1, var2, var3), this.posX + (this.rand.nextFloat() - 0.5D) * this.width, this.boundingBox.minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D, (this.rand.nextFloat() - 0.5D) * 4.0D);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackClass(Class<?> par1Class) {
/* 130 */     return (isPlayerCreated() && EntityPlayer.class.isAssignableFrom(par1Class)) ? false : super.canAttackClass(par1Class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 138 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 139 */     par1NBTTagCompound.setBoolean("PlayerCreated", isPlayerCreated());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 147 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 148 */     setPlayerCreated(par1NBTTagCompound.getBoolean("PlayerCreated"));
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
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 169 */     this.attackTimer = 10;
/* 170 */     this.worldObj.setEntityState(this, EnumEntityState.golem_throw);
/*     */     
/* 172 */     EntityDamageResult result = target.attackEntityFrom(new Damage(DamageSource.causeMobDamage(this), (7 + this.rand.nextInt(15))));
/*     */     
/* 174 */     if (result == null) {
/* 175 */       return result;
/*     */     }
/* 177 */     if (result.entityWasKnockedBack()) {
/* 178 */       target.motionY += 0.4000000059604645D;
/*     */     }
/* 180 */     playSound("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     
/* 182 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(EnumEntityState par1) {
/* 189 */     if (par1 == EnumEntityState.golem_throw) {
/*     */       
/* 191 */       this.attackTimer = 10;
/* 192 */       playSound("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     
/*     */     }
/* 195 */     else if (par1 == EnumEntityState.golem_holding_rose) {
/*     */       
/* 197 */       this.holdRoseTick = 400;
/*     */     }
/*     */     else {
/*     */       
/* 201 */       super.handleHealthUpdate(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Village getVillage() {
/* 207 */     return this.villageObj;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAttackTimer() {
/* 212 */     return this.attackTimer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHoldingRose(boolean par1) {
/* 217 */     this.holdRoseTick = par1 ? 400 : 0;
/*     */     
/* 219 */     this.worldObj.setEntityState(this, EnumEntityState.golem_holding_rose);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 228 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 236 */     return "mob.irongolem.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 244 */     return "mob.irongolem.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 252 */     playSound("mob.irongolem.walk", 1.0F, 1.0F);
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 283 */     int num_drops = this.rand.nextInt(3);
/*     */     int i;
/* 285 */     for (i = 0; i < num_drops; i++) {
/* 286 */       dropItem(Block.plantRed.blockID, 1);
/*     */     }
/* 288 */     if (recently_hit_by_player && !this.has_taken_massive_fall_damage) {
/*     */       
/* 290 */       num_drops = 3 + this.rand.nextInt(3 + damage_source.getLootingModifier());
/*     */       
/* 292 */       for (i = 0; i < num_drops; i++) {
/* 293 */         dropItem(Item.ironNugget.itemID, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getHoldRoseTick() {
/* 299 */     return this.holdRoseTick;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlayerCreated() {
/* 304 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerCreated(boolean par1) {
/* 309 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 311 */     if (par1) {
/*     */       
/* 313 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else {
/*     */       
/* 317 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource par1DamageSource) {
/* 326 */     if (!isPlayerCreated() && this.attackingPlayer != null && this.villageObj != null)
/*     */     {
/* 328 */       this.villageObj.setReputationForPlayer(this.attackingPlayer.getCommandSenderName(), -5);
/*     */     }
/*     */     
/* 331 */     super.onDeath(par1DamageSource);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 336 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 341 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 346 */     return super.getExperienceValue() * 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getReach() {
/* 351 */     return super.getReach() + 0.5F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityIronGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */