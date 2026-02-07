/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityGhast
/*     */   extends EntityFlying
/*     */   implements IMob
/*     */ {
/*     */   public int courseChangeCooldown;
/*     */   public double waypointX;
/*     */   public double waypointY;
/*     */   public double waypointZ;
/*     */   private Entity targetedEntity;
/*     */   private int aggroCooldown;
/*     */   public int prevAttackCounter;
/*     */   public int attackCounter;
/*  17 */   private int explosionStrength = 1;
/*     */ 
/*     */   
/*     */   public EntityGhast(World par1World) {
/*  21 */     super(par1World);
/*  22 */     setSize(4.0F, 4.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_110182_bF() {
/*  29 */     return (this.dataWatcher.getWatchableObjectByte(16) != 0);
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/*  57 */     if (damage.isFireballFromPlayer()) {
/*  58 */       damage.setAmount(1000.0F).setIgnoreSpecificImmunities();
/*     */     }
/*  60 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */     
/*  62 */     if (result == null) {
/*  63 */       return result;
/*     */     }
/*  65 */     if (result.entityWasDestroyed() && damage.isFireballFromPlayer()) {
/*  66 */       ((EntityPlayer)damage.getResponsibleEntity()).triggerAchievement(AchievementList.ghast);
/*     */     }
/*  68 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  73 */     super.entityInit();
/*  74 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  79 */     super.applyEntityAttributes();
/*  80 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateEntityActionState() {
/*  85 */     if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0)
/*     */     {
/*  87 */       setDead();
/*     */     }
/*     */     
/*  90 */     tryDespawnEntity();
/*  91 */     this.prevAttackCounter = this.attackCounter;
/*  92 */     double var1 = this.waypointX - this.posX;
/*  93 */     double var3 = this.waypointY - this.posY;
/*  94 */     double var5 = this.waypointZ - this.posZ;
/*  95 */     double var7 = var1 * var1 + var3 * var3 + var5 * var5;
/*     */     
/*  97 */     if (var7 < 1.0D || var7 > 3600.0D) {
/*     */       
/*  99 */       this.waypointX = this.posX + ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
/* 100 */       this.waypointY = this.posY + ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
/* 101 */       this.waypointZ = this.posZ + ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
/*     */     } 
/*     */     
/* 104 */     if (this.courseChangeCooldown-- <= 0) {
/*     */       
/* 106 */       this.courseChangeCooldown += this.rand.nextInt(5) + 2;
/* 107 */       var7 = MathHelper.sqrt_double(var7);
/*     */       
/* 109 */       if (isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7)) {
/*     */         
/* 111 */         this.motionX += var1 / var7 * 0.1D;
/* 112 */         this.motionY += var3 / var7 * 0.1D;
/* 113 */         this.motionZ += var5 / var7 * 0.1D;
/*     */       }
/*     */       else {
/*     */         
/* 117 */         this.waypointX = this.posX;
/* 118 */         this.waypointY = this.posY;
/* 119 */         this.waypointZ = this.posZ;
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     if (this.targetedEntity != null && this.targetedEntity.isDead)
/*     */     {
/* 125 */       this.targetedEntity = null;
/*     */     }
/*     */     
/* 128 */     if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
/*     */ 
/*     */       
/* 131 */       this.targetedEntity = getClosestVulnerablePlayer(100.0D);
/*     */       
/* 133 */       if (this.targetedEntity != null)
/*     */       {
/* 135 */         this.aggroCooldown = 20;
/*     */       }
/*     */     } 
/*     */     
/* 139 */     double var9 = 64.0D;
/*     */     
/* 141 */     if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < var9 * var9) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 148 */       Vec3 target_center = this.targetedEntity.getCenterPoint();
/* 149 */       this.renderYawOffset = this.rotationYaw = (float)MathHelper.getYawInDegrees(getCenterPoint(), target_center);
/*     */       
/* 151 */       if (canSeeEntity(this.targetedEntity)) {
/*     */         
/* 153 */         if (this.attackCounter == 10)
/*     */         {
/* 155 */           this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */         }
/*     */         
/* 158 */         this.attackCounter++;
/*     */         
/* 160 */         if (this.attackCounter == 20)
/*     */         {
/*     */ 
/*     */           
/* 164 */           double distance_sq = getCenterPoint().squareDistanceTo(target_center);
/*     */           
/* 166 */           float lead = (float)Math.pow(distance_sq, 0.44D);
/*     */           
/* 168 */           lead *= 0.5F + this.rand.nextFloat();
/*     */           
/* 170 */           target_center.xCoord = this.targetedEntity.getPredictedPosX(lead);
/* 171 */           target_center.zCoord = this.targetedEntity.getPredictedPosZ(lead);
/*     */ 
/*     */ 
/*     */           
/* 175 */           this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */           
/* 177 */           EntityLargeFireball var17 = new EntityLargeFireball(this.worldObj, this, target_center, 4.0F);
/* 178 */           var17.field_92057_e = this.explosionStrength;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 184 */           this.worldObj.spawnEntityInWorld(var17);
/* 185 */           this.attackCounter = -40;
/*     */         }
/*     */       
/* 188 */       } else if (this.attackCounter > 0) {
/*     */         
/* 190 */         this.attackCounter--;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 195 */       this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / 3.1415927F;
/*     */       
/* 197 */       if (this.attackCounter > 0)
/*     */       {
/* 199 */         this.attackCounter--;
/*     */       }
/*     */     } 
/*     */     
/* 203 */     if (!this.worldObj.isRemote) {
/*     */       
/* 205 */       byte var21 = this.dataWatcher.getWatchableObjectByte(16);
/* 206 */       byte var12 = (byte)((this.attackCounter > 10) ? 1 : 0);
/*     */       
/* 208 */       if (var21 != var12)
/*     */       {
/* 210 */         this.dataWatcher.updateObject(16, Byte.valueOf(var12));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
/* 220 */     double var9 = (this.waypointX - this.posX) / par7;
/* 221 */     double var11 = (this.waypointY - this.posY) / par7;
/* 222 */     double var13 = (this.waypointZ - this.posZ) / par7;
/* 223 */     AxisAlignedBB var15 = this.boundingBox.copy();
/*     */     
/* 225 */     for (int var16 = 1; var16 < par7; var16++) {
/*     */       
/* 227 */       var15.offset(var9, var11, var13);
/*     */       
/* 229 */       if (!this.worldObj.getCollidingBoundingBoxes(this, var15).isEmpty())
/*     */       {
/* 231 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 235 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 243 */     return "mob.ghast.moan";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 251 */     return "mob.ghast.scream";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 259 */     return "mob.ghast.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 267 */     return Item.gunpowder.itemID;
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 294 */     int num_drops = this.rand.nextInt(2);
/*     */     
/* 296 */     if (damage_source.isFireballFromPlayer() && num_drops < 1)
/* 297 */       num_drops = 1; 
/*     */     int i;
/* 299 */     for (i = 0; i < num_drops; i++) {
/* 300 */       dropItem(Item.ghastTear.itemID, 1);
/*     */     }
/* 302 */     num_drops = this.rand.nextInt(3);
/*     */     
/* 304 */     for (i = 0; i < num_drops; i++) {
/* 305 */       dropItem(Item.gunpowder.itemID, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume(String sound) {
/* 314 */     return 10.0F;
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
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 327 */     return (this.rand.nextInt(20) == 0 && super.getCanSpawnHere(perform_light_check) && this.worldObj.difficultySetting > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 335 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 343 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 344 */     par1NBTTagCompound.setInteger("ExplosionPower", this.explosionStrength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 352 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/* 354 */     if (par1NBTTagCompound.hasKey("ExplosionPower"))
/*     */     {
/* 356 */       this.explosionStrength = par1NBTTagCompound.getInteger("ExplosionPower");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 362 */     return super.getExperienceValue() * 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 367 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 372 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnInShallowWater() {
/* 377 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityGhast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */