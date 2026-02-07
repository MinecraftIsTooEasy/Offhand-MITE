/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class EntityTameable
/*     */   extends EntityAnimal implements EntityOwnable {
/*   8 */   protected EntityAISit aiSit = new EntityAISit(this);
/*     */   
/*     */   protected EntityLiving threatening_entity;
/*     */   
/*     */   private int threatening_entity_countdown;
/*     */   public int taming_cooldown;
/*     */   
/*     */   public EntityTameable(World par1World) {
/*  16 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  21 */     super.entityInit();
/*  22 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*  23 */     this.dataWatcher.addObject(17, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  31 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/*  33 */     if (getOwnerName() == null) {
/*     */       
/*  35 */       par1NBTTagCompound.setString("Owner", "");
/*     */     }
/*     */     else {
/*     */       
/*  39 */       par1NBTTagCompound.setString("Owner", getOwnerName());
/*     */     } 
/*     */     
/*  42 */     par1NBTTagCompound.setBoolean("Sitting", isSitting());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  50 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  51 */     String var2 = par1NBTTagCompound.getString("Owner");
/*     */     
/*  53 */     if (var2.length() > 0) {
/*     */       
/*  55 */       setOwner(var2);
/*  56 */       setTamed(true);
/*     */     } 
/*     */     
/*  59 */     this.aiSit.setSitting(par1NBTTagCompound.getBoolean("Sitting"));
/*  60 */     setSitting(par1NBTTagCompound.getBoolean("Sitting"));
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
/*     */   protected void playTameEffect(boolean par1) {
/*  75 */     EnumParticle particle = par1 ? EnumParticle.heart : EnumParticle.smoke;
/*     */     
/*  77 */     for (int var3 = 0; var3 < 7; var3++) {
/*     */       
/*  79 */       double var4 = this.rand.nextGaussian() * 0.02D;
/*  80 */       double var6 = this.rand.nextGaussian() * 0.02D;
/*  81 */       double var8 = this.rand.nextGaussian() * 0.02D;
/*     */       
/*  83 */       this.worldObj.spawnParticle(particle, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var4, var6, var8);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(EnumEntityState par1) {
/*  91 */     if (par1 == EnumEntityState.tame_success) {
/*     */       
/*  93 */       playTameEffect(true);
/*     */     
/*     */     }
/*  96 */     else if (par1 == EnumEntityState.tame_failure) {
/*     */       
/*  98 */       playTameEffect(false);
/*     */     }
/*     */     else {
/*     */       
/* 102 */       super.handleHealthUpdate(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/* 108 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTamed(boolean par1) {
/* 113 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 115 */     if (par1) {
/*     */       
/* 117 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x4)));
/*     */     }
/*     */     else {
/*     */       
/* 121 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFB)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSitting() {
/* 127 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSitting(boolean par1) {
/* 132 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 134 */     if (par1) {
/*     */       
/* 136 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else {
/*     */       
/* 140 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOwnerName() {
/* 146 */     return this.dataWatcher.getWatchableObjectString(17);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(String par1Str) {
/* 151 */     this.dataWatcher.updateObject(17, par1Str);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingBase func_130012_q() {
/* 156 */     return this.worldObj.getPlayerEntityByName(getOwnerName());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAISit func_70907_r() {
/* 161 */     return this.aiSit;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase) {
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Team getTeam() {
/* 171 */     if (isTamed()) {
/*     */       
/* 173 */       EntityLivingBase var1 = func_130012_q();
/*     */       
/* 175 */       if (var1 != null)
/*     */       {
/* 177 */         return var1.getTeam();
/*     */       }
/*     */     } 
/*     */     
/* 181 */     return super.getTeam();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnSameTeam(EntityLivingBase par1EntityLivingBase) {
/* 186 */     if (isTamed()) {
/*     */       
/* 188 */       EntityLivingBase var2 = func_130012_q();
/*     */       
/* 190 */       if (par1EntityLivingBase == var2)
/*     */       {
/* 192 */         return true;
/*     */       }
/*     */       
/* 195 */       if (var2 != null)
/*     */       {
/* 197 */         return var2.isOnSameTeam(par1EntityLivingBase);
/*     */       }
/*     */     } 
/*     */     
/* 201 */     return super.isOnSameTeam(par1EntityLivingBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getOwner() {
/* 206 */     return func_130012_q();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAThreat(EntityLiving entity_living) {
/* 211 */     if (!entity_living.isEntityAlive()) {
/* 212 */       return false;
/*     */     }
/* 214 */     if (!(entity_living instanceof IMob)) {
/* 215 */       return false;
/*     */     }
/* 217 */     if (entity_living instanceof EntityHorse) {
/*     */       
/* 219 */       EntityHorse horse = (EntityHorse)entity_living;
/*     */       
/* 221 */       if (horse.getHorseType() < 3) {
/* 222 */         return false;
/*     */       }
/* 224 */     } else if (entity_living instanceof EntityLivestock) {
/*     */       
/* 226 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     if (entity_living instanceof EntityWolf) {
/*     */       
/* 236 */       EntityWolf wolf = (EntityWolf)entity_living;
/*     */       
/* 238 */       if (wolf.getOwner() == getOwner()) {
/* 239 */         return false;
/*     */       }
/*     */     } 
/* 242 */     Entity owner = getOwner();
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
/* 257 */     EntityLivingBase target = entity_living.getTarget();
/*     */     
/* 259 */     if ((target == this || target == owner) && getEntitySenses().canSee(entity_living)) {
/* 260 */       return true;
/*     */     }
/* 262 */     return false;
/*     */   }
/*     */   
/*     */   public void warnOwner() {}
/*     */   
/*     */   public void callToOwner() {}
/*     */   
/*     */   public void onLivingUpdate() {
/* 270 */     super.onLivingUpdate();
/*     */     
/* 272 */     if (!this.worldObj.isRemote) {
/*     */       
/* 274 */       if (isTamed() && (isSitting() || getAttackTarget() == null) && getTicksExistedWithOffset() % 10 == 0 && Math.random() < 0.25D && distanceToNearestPlayer() <= 16.0D) {
/*     */         
/* 276 */         Entity owner = getOwner();
/* 277 */         List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
/*     */         
/* 279 */         Iterator<Entity> i = entities.iterator();
/*     */         
/* 281 */         while (i.hasNext()) {
/*     */           
/* 283 */           Entity entity = i.next();
/*     */           
/* 285 */           if (entity instanceof EntityLiving)
/*     */           {
/* 287 */             if (isAThreat((EntityLiving)entity)) {
/*     */               
/* 289 */               warnOwner();
/* 290 */               setThreateningEntity((EntityLiving)entity);
/*     */               
/*     */               break;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/* 297 */       if (this.threatening_entity_countdown > 0) {
/* 298 */         this.threatening_entity_countdown--;
/*     */       }
/* 300 */       if (this.threatening_entity_countdown == 0) {
/* 301 */         this.threatening_entity = null;
/*     */       }
/* 303 */       if (getThreateningEntity() != null) {
/* 304 */         getLookHelper().setLookPositionWithEntity(getThreateningEntity(), 10.0F, getVerticalFaceSpeed());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setThreateningEntity(EntityLiving entity_living) {
/* 310 */     if (entity_living != null && !entity_living.isEntityAlive()) {
/* 311 */       entity_living = null;
/*     */     }
/* 313 */     this.threatening_entity = entity_living;
/*     */     
/* 315 */     if (entity_living != null) {
/* 316 */       this.threatening_entity_countdown = (this.rand.nextInt(10) + 5) * 10;
/*     */     }
/*     */   }
/*     */   
/*     */   public EntityLiving getThreateningEntity() {
/* 321 */     if (this.threatening_entity != null && !this.threatening_entity.isEntityAlive()) {
/* 322 */       this.threatening_entity = null;
/*     */     }
/* 324 */     else if (this.threatening_entity_countdown == 0 || getTarget() != null) {
/* 325 */       this.threatening_entity = null;
/*     */     } 
/* 327 */     return this.threatening_entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 332 */     super.onUpdate();
/*     */     
/* 334 */     if (this.taming_cooldown > 0) {
/* 335 */       this.taming_cooldown--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int getTamingOutcome(EntityPlayer paramEntityPlayer);
/*     */ 
/*     */   
/*     */   public void setAttackTarget(EntityLivingBase target) {
/* 345 */     if (target != null && target == getOwner()) {
/*     */       return;
/*     */     }
/* 348 */     super.setAttackTarget(target);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTarget(EntityLivingBase target) {
/* 353 */     if (target == getOwner()) {
/*     */       return;
/*     */     }
/* 356 */     super.setTarget(target);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityTameable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */