/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityArachnid
/*     */   extends EntityMob
/*     */ {
/*     */   int num_webs;
/*     */   
/*     */   public EntityArachnid(World par1World, float scaling) {
/*  15 */     super(par1World);
/*  16 */     setSize(1.4F * scaling, 0.9F * scaling);
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
/*  30 */     if (par1World != null && !par1World.isRemote && !(this instanceof EntityPhaseSpider)) {
/*     */       
/*  32 */       this.num_webs = this.rand.nextInt(4);
/*     */       
/*  34 */       if (this.num_webs > 0 && !(this instanceof EntityCaveSpider) && !(this instanceof EntityDemonSpider)) {
/*  35 */         this.num_webs--;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean canClimbWalls() {
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  46 */     super.entityInit();
/*     */     
/*  48 */     if (canClimbWalls()) {
/*  49 */       this.dataWatcher.addObject(16, new Byte((byte)0));
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  54 */     super.writeEntityToNBT(par1NBTTagCompound);
/*  55 */     par1NBTTagCompound.setByte("num_webs", (byte)this.num_webs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  60 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  61 */     this.num_webs = par1NBTTagCompound.getByte("num_webs");
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkSwitchingToPeaceful() {
/*  66 */     if (this.worldObj.isRemote) {
/*     */       
/*  68 */       Minecraft.setErrorMessage("checkSwitchingToPeacful: only meant to be called on server");
/*     */       
/*     */       return;
/*     */     } 
/*  72 */     if (getEntityToAttack() instanceof EntityPlayer && peacefulDuringDay() && getBrightness(1.0F) > 0.5F && !(getLastHarmingEntity() instanceof EntityPlayer) && this.rand.nextInt(100) == 0 && isOutdoors())
/*     */     {
/*     */       
/*  75 */       setEntityToAttack((Entity)null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicksBetweenWebThrows() {
/*  81 */     if (this instanceof EntityCaveSpider || this instanceof EntityDemonSpider) {
/*  82 */       return 200;
/*     */     }
/*  84 */     return 500;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  89 */     super.onUpdate();
/*     */     
/*  91 */     if (!this.worldObj.isRemote && canClimbWalls())
/*     */     {
/*  93 */       setBesideClimbableBlock(this.isCollidedHorizontally);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (!this.worldObj.isRemote) {
/*     */       
/* 104 */       checkSwitchingToPeaceful();
/*     */       
/* 106 */       if (this.num_webs > 0 && getTicksExistedWithOffset() % getTicksBetweenWebThrows() == 0) {
/*     */         
/* 108 */         Entity target = getEntityToAttack();
/*     */         
/* 110 */         if (target instanceof EntityLivingBase) {
/*     */           
/* 112 */           double distance = getDistanceToEntity(target);
/*     */           
/* 114 */           if (distance <= 8.0D) {
/*     */             
/* 116 */             EntityLivingBase elb_target = (EntityLivingBase)target;
/*     */ 
/*     */             
/* 119 */             Raycast raycast = (new Raycast(this.worldObj, getEyePos(), elb_target.getEyePos())).setForThrownWeb(null).performVsBlocks();
/*     */             
/* 121 */             if (raycast.hasBlockCollision()) {
/*     */               
/* 123 */               raycast.setLimit(elb_target.getFootPosPlusFractionOfHeight(0.25F));
/* 124 */               raycast.performVsBlocks();
/*     */             } 
/*     */             
/* 127 */             RaycastCollision rc = raycast.getBlockCollision();
/*     */             
/* 129 */             if (rc == null) {
/*     */               
/* 131 */               raycast.setOriginator(this).performVsEntities();
/* 132 */               rc = raycast.getNearestEntityCollision();
/*     */               
/* 134 */               if (rc.getEntityHit() == target) {
/* 135 */                 attackEntityWithRangedAttack((EntityLivingBase)target, 1.0F);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
/* 145 */     EntityWeb var3 = new EntityWeb(this.worldObj, this);
/*     */     
/* 147 */     int lead = 10;
/*     */     
/* 149 */     double var4 = par1EntityLivingBase.getPredictedPosX(lead) - this.posX;
/* 150 */     double var6 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - var3.posY;
/* 151 */     double var8 = par1EntityLivingBase.getPredictedPosZ(lead) - this.posZ;
/*     */     
/* 153 */     float var10 = MathHelper.sqrt_double(var4 * var4 + var8 * var8) * 0.2F;
/* 154 */     var3.setThrowableHeading(var4, var6 + var10, var8, 0.8F, 0.0F);
/* 155 */     playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
/* 156 */     this.worldObj.spawnEntityInWorld(var3);
/*     */     
/* 158 */     if (this instanceof EntityDemonSpider || isBurning()) {
/* 159 */       var3.setFire(10);
/*     */     }
/* 161 */     this.num_webs--;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 166 */     super.applyEntityAttributes();
/*     */     
/* 168 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 12.0D);
/* 169 */     setEntityAttribute(SharedMonsterAttributes.followRange, 28.0D);
/* 170 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 1.0D);
/*     */     
/* 172 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 177 */     return "mob.spider.say";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 182 */     return "mob.spider.say";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 187 */     return "mob.spider.death";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 193 */     makeSound("mob.spider.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean peacefulDuringDay() {
/* 198 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityPlayer findPlayerToAttack(float max_distance) {
/* 203 */     if (peacefulDuringDay() && getBrightness(1.0F) > 0.5F && isOutdoors()) {
/* 204 */       return null;
/*     */     }
/*     */     
/* 207 */     return super.findPlayerToAttack(max_distance);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Entity findNonPlayerToAttack(float max_distance) {
/* 212 */     if (peacefulDuringDay() && getBrightness(1.0F) > 0.5F && isOutdoors()) {
/* 213 */       return null;
/*     */     }
/* 215 */     Entity target = this.worldObj.findNearestSeenEntityWithinAABB(EntityChicken.class, this.boundingBox.expand(max_distance, (max_distance / 4.0F), max_distance), this, getEntitySenses());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     return target;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean preysUpon(Entity entity) {
/* 225 */     return entity instanceof EntityChicken;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canJump() {
/* 230 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackEntity(Entity par1Entity, float par2) {
/* 241 */     if (par2 > 2.0F && par2 < 6.0F && canJump() && this.rand.nextInt(10) == 0) {
/*     */       
/* 243 */       if (this.onGround)
/*     */       {
/* 245 */         double var4 = par1Entity.posX - this.posX;
/* 246 */         double var6 = par1Entity.posZ - this.posZ;
/* 247 */         float var8 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
/* 248 */         this.motionX = var4 / var8 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
/* 249 */         this.motionZ = var6 / var8 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
/* 250 */         this.motionY = 0.4000000059604645D;
/*     */         
/* 252 */         this.rotationYaw = (float)MathHelper.getYawInDegrees(this.posX, this.posZ, par1Entity.posX, par1Entity.posZ);
/*     */         
/* 254 */         PathEntity path = this.worldObj.getPathEntityToEntity(this, par1Entity, 8.0F, true, false, avoidsPathingThroughWater(), true);
/*     */         
/* 256 */         if (path != null)
/*     */         {
/*     */           
/* 259 */           setPathToEntity(path);
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 265 */       super.attackEntity(par1Entity, par2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 272 */     return Item.silk.itemID;
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
/*     */   protected final void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 289 */     if (recently_hit_by_player)
/*     */     {
/* 291 */       while (this.num_webs-- > 0) {
/* 292 */         dropItemStack(new ItemStack(Item.silk.itemID));
/*     */       }
/*     */     }
/* 295 */     if (recently_hit_by_player && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + damage_source.getButcheringModifier()) > 0)) {
/* 296 */       dropItem(Item.spiderEye.itemID, 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isOnLadder() {
/* 301 */     return isBesideClimbableBlock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInWeb() {}
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 308 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPotionApplicable(PotionEffect par1PotionEffect) {
/* 313 */     return (par1PotionEffect.getPotionID() == Potion.poison.id) ? false : super.isPotionApplicable(par1PotionEffect);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBesideClimbableBlock() {
/* 318 */     return (canClimbWalls() && (this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBesideClimbableBlock(boolean par1) {
/* 323 */     if (!canClimbWalls()) {
/*     */       return;
/*     */     }
/* 326 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 328 */     if (par1) {
/*     */       
/* 330 */       var2 = (byte)(var2 | 0x1);
/*     */     }
/*     */     else {
/*     */       
/* 334 */       var2 = (byte)(var2 & 0xFFFFFFFE);
/*     */     } 
/*     */     
/* 337 */     this.dataWatcher.updateObject(16, Byte.valueOf(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 342 */     return super.onSpawnWithEgg(par1EntityLivingData);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawBackFaces() {
/* 347 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeAttackedBy(EntityLivingBase attacker) {
/* 352 */     if (this.rand.nextInt(4) > 0 && attacker.hasCurse(Curse.fear_of_spiders, true)) {
/* 353 */       return false;
/*     */     }
/* 355 */     return super.canBeAttackedBy(attacker);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresLineOfSightToTargets() {
/* 360 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isBlackWidowSpider() {
/* 365 */     return (getClass() == EntityBlackWidowSpider.class);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityArachnid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */