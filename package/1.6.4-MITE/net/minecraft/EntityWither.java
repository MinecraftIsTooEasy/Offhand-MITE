/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class EntityWither
/*     */   extends EntityMob implements IBossDisplayData, IRangedAttackMob {
/*   7 */   private float[] field_82220_d = new float[2];
/*   8 */   private float[] field_82221_e = new float[2];
/*   9 */   private float[] field_82217_f = new float[2];
/*  10 */   private float[] field_82218_g = new float[2];
/*  11 */   private int[] field_82223_h = new int[2];
/*  12 */   private int[] field_82224_i = new int[2];
/*     */   
/*     */   private int field_82222_j;
/*     */   
/*  16 */   private static final IEntitySelector attackEntitySelector = new EntityWitherAttackFilter();
/*     */ 
/*     */   
/*     */   public EntityWither(World par1World) {
/*  20 */     super(par1World);
/*  21 */     setHealth(getMaxHealth());
/*  22 */     setSize(0.9F, 4.0F);
/*     */     
/*  24 */     getNavigator().setCanSwim(true);
/*  25 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  26 */     this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
/*  27 */     this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
/*  28 */     this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  29 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*  30 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
/*  31 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, attackEntitySelector));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  37 */     super.entityInit();
/*  38 */     this.dataWatcher.addObject(17, new Integer(0));
/*  39 */     this.dataWatcher.addObject(18, new Integer(0));
/*  40 */     this.dataWatcher.addObject(19, new Integer(0));
/*  41 */     this.dataWatcher.addObject(20, new Integer(0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  49 */     super.writeEntityToNBT(par1NBTTagCompound);
/*  50 */     par1NBTTagCompound.setInteger("Invul", func_82212_n());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  58 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  59 */     func_82215_s(par1NBTTagCompound.getInteger("Invul"));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  64 */     return this.height / 8.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  72 */     return "mob.wither.idle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  80 */     return "mob.wither.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  88 */     return "mob.wither.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  97 */     this.motionY *= 0.6000000238418579D;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (!this.worldObj.isRemote && getWatchedTargetId(0) > 0) {
/*     */       
/* 104 */       Entity var1 = this.worldObj.getEntityByID(getWatchedTargetId(0));
/*     */       
/* 106 */       if (var1 != null) {
/*     */         
/* 108 */         if (this.posY < var1.posY || (!isArmored() && this.posY < var1.posY + 5.0D)) {
/*     */           
/* 110 */           if (this.motionY < 0.0D)
/*     */           {
/* 112 */             this.motionY = 0.0D;
/*     */           }
/*     */           
/* 115 */           this.motionY += (0.5D - this.motionY) * 0.6000000238418579D;
/*     */         } 
/*     */         
/* 118 */         double var2 = var1.posX - this.posX;
/* 119 */         double var4 = var1.posZ - this.posZ;
/* 120 */         double var6 = var2 * var2 + var4 * var4;
/*     */         
/* 122 */         if (var6 > 9.0D) {
/*     */           
/* 124 */           double var8 = MathHelper.sqrt_double(var6);
/* 125 */           this.motionX += (var2 / var8 * 0.5D - this.motionX) * 0.6000000238418579D;
/* 126 */           this.motionZ += (var4 / var8 * 0.5D - this.motionZ) * 0.6000000238418579D;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05000000074505806D)
/*     */     {
/* 133 */       this.rotationYaw = (float)Math.atan2(this.motionZ, this.motionX) * 57.295776F - 90.0F;
/*     */     }
/*     */     
/* 136 */     super.onLivingUpdate();
/*     */     
/*     */     int var20;
/* 139 */     for (var20 = 0; var20 < 2; var20++) {
/*     */       
/* 141 */       this.field_82218_g[var20] = this.field_82221_e[var20];
/* 142 */       this.field_82217_f[var20] = this.field_82220_d[var20];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 147 */     for (var20 = 0; var20 < 2; var20++) {
/*     */       
/* 149 */       int i = getWatchedTargetId(var20 + 1);
/* 150 */       Entity var3 = null;
/*     */       
/* 152 */       if (i > 0)
/*     */       {
/* 154 */         var3 = this.worldObj.getEntityByID(i);
/*     */       }
/*     */       
/* 157 */       if (var3 != null) {
/*     */         
/* 159 */         double var4 = func_82214_u(var20 + 1);
/* 160 */         double var6 = func_82208_v(var20 + 1);
/* 161 */         double var8 = func_82213_w(var20 + 1);
/* 162 */         double var10 = var3.posX - var4;
/* 163 */         double var12 = var3.posY + var3.getEyeHeight() - var6;
/* 164 */         double var14 = var3.posZ - var8;
/* 165 */         double var16 = MathHelper.sqrt_double(var10 * var10 + var14 * var14);
/* 166 */         float var18 = (float)(Math.atan2(var14, var10) * 180.0D / Math.PI) - 90.0F;
/* 167 */         float var19 = (float)-(Math.atan2(var12, var16) * 180.0D / Math.PI);
/* 168 */         this.field_82220_d[var20] = func_82204_b(this.field_82220_d[var20], var19, 40.0F);
/* 169 */         this.field_82221_e[var20] = func_82204_b(this.field_82221_e[var20], var18, 10.0F);
/*     */       }
/*     */       else {
/*     */         
/* 173 */         this.field_82221_e[var20] = func_82204_b(this.field_82221_e[var20], this.renderYawOffset, 10.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 177 */     boolean var22 = isArmored();
/*     */     int var21;
/* 179 */     for (var21 = 0; var21 < 3; var21++) {
/*     */       
/* 181 */       double var23 = func_82214_u(var21);
/* 182 */       double var5 = func_82208_v(var21);
/* 183 */       double var7 = func_82213_w(var21);
/*     */       
/* 185 */       this.worldObj.spawnParticle(EnumParticle.smoke, var23 + this.rand.nextGaussian() * 0.30000001192092896D, var5 + this.rand.nextGaussian() * 0.30000001192092896D, var7 + this.rand.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);
/*     */       
/* 187 */       if (var22 && this.worldObj.rand.nextInt(4) == 0)
/*     */       {
/*     */         
/* 190 */         this.worldObj.spawnParticle(EnumParticle.mobSpell, var23 + this.rand.nextGaussian() * 0.30000001192092896D, var5 + this.rand.nextGaussian() * 0.30000001192092896D, var7 + this.rand.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
/*     */       }
/*     */     } 
/*     */     
/* 194 */     if (func_82212_n() > 0)
/*     */     {
/* 196 */       for (var21 = 0; var21 < 3; var21++)
/*     */       {
/*     */         
/* 199 */         this.worldObj.spawnParticle(EnumParticle.mobSpell, this.posX + this.rand.nextGaussian() * 1.0D, this.posY + (this.rand.nextFloat() * 3.3F), this.posZ + this.rand.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/* 208 */     if (func_82212_n() > 0) {
/*     */       
/* 210 */       int var1 = func_82212_n() - 1;
/*     */       
/* 212 */       if (var1 <= 0) {
/*     */ 
/*     */         
/* 215 */         this.worldObj.newExplosion(this, this.posX, this.posY + getEyeHeight(), this.posZ, 7.0F, 7.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
/* 216 */         this.worldObj.func_82739_e(1013, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */       } 
/*     */       
/* 219 */       func_82215_s(var1);
/*     */       
/* 221 */       if (this.ticksExisted % 10 == 0)
/*     */       {
/* 223 */         heal(10.0F);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 228 */       super.updateAITasks();
/*     */       
/*     */       int var1;
/* 231 */       for (var1 = 1; var1 < 3; var1++) {
/*     */         
/* 233 */         if (this.ticksExisted >= this.field_82223_h[var1 - 1]) {
/*     */           
/* 235 */           this.field_82223_h[var1 - 1] = this.ticksExisted + 10 + this.rand.nextInt(10);
/*     */           
/* 237 */           if (this.worldObj.difficultySetting >= 2) {
/*     */             
/* 239 */             int var10001 = var1 - 1;
/* 240 */             int var10003 = this.field_82224_i[var1 - 1];
/* 241 */             this.field_82224_i[var10001] = this.field_82224_i[var1 - 1] + 1;
/*     */             
/* 243 */             if (var10003 > 15) {
/*     */               
/* 245 */               float var2 = 10.0F;
/* 246 */               float var3 = 5.0F;
/* 247 */               double var4 = MathHelper.getRandomDoubleInRange(this.rand, this.posX - var2, this.posX + var2);
/* 248 */               double var6 = MathHelper.getRandomDoubleInRange(this.rand, this.posY - var3, this.posY + var3);
/* 249 */               double var8 = MathHelper.getRandomDoubleInRange(this.rand, this.posZ - var2, this.posZ + var2);
/* 250 */               func_82209_a(var1 + 1, var4, var6, var8, true);
/* 251 */               this.field_82224_i[var1 - 1] = 0;
/*     */             } 
/*     */           } 
/*     */           
/* 255 */           int var12 = getWatchedTargetId(var1);
/*     */           
/* 257 */           if (var12 > 0) {
/*     */             
/* 259 */             Entity var14 = this.worldObj.getEntityByID(var12);
/*     */             
/* 261 */             if (var14 != null && var14.isEntityAlive() && getDistanceSqToEntity(var14) <= 900.0D && canSeeEntity(var14))
/*     */             {
/* 263 */               func_82216_a(var1 + 1, (EntityLivingBase)var14);
/* 264 */               this.field_82223_h[var1 - 1] = this.ticksExisted + 40 + this.rand.nextInt(20);
/* 265 */               this.field_82224_i[var1 - 1] = 0;
/*     */             }
/*     */             else
/*     */             {
/* 269 */               func_82211_c(var1, 0);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 274 */             List<EntityLivingBase> var13 = this.worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(20.0D, 8.0D, 20.0D), attackEntitySelector);
/*     */             
/* 276 */             for (int var16 = 0; var16 < 10 && !var13.isEmpty(); var16++) {
/*     */               
/* 278 */               EntityLivingBase var5 = var13.get(this.rand.nextInt(var13.size()));
/*     */               
/* 280 */               if (var5 != this && var5.isEntityAlive() && canSeeEntity(var5)) {
/*     */                 
/* 282 */                 if (var5 instanceof EntityPlayer) {
/*     */                   
/* 284 */                   if (!((EntityPlayer)var5).capabilities.disableDamage)
/*     */                   {
/* 286 */                     func_82211_c(var1, var5.entityId);
/*     */                   }
/*     */                   
/*     */                   break;
/*     */                 } 
/* 291 */                 func_82211_c(var1, var5.entityId);
/*     */ 
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/* 297 */               var13.remove(var5);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 303 */       if (getAttackTarget() != null) {
/*     */         
/* 305 */         func_82211_c(0, (getAttackTarget()).entityId);
/*     */       }
/*     */       else {
/*     */         
/* 309 */         func_82211_c(0, 0);
/*     */       } 
/*     */       
/* 312 */       if (this.field_82222_j > 0) {
/*     */         
/* 314 */         this.field_82222_j--;
/*     */         
/* 316 */         if (this.field_82222_j == 0 && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*     */           
/* 318 */           var1 = MathHelper.floor_double(this.posY);
/* 319 */           int var12 = MathHelper.floor_double(this.posX);
/* 320 */           int var15 = MathHelper.floor_double(this.posZ);
/* 321 */           boolean var18 = false;
/*     */           
/* 323 */           for (int var17 = -1; var17 <= 1; var17++) {
/*     */             
/* 325 */             for (int var19 = -1; var19 <= 1; var19++) {
/*     */               
/* 327 */               for (int var7 = 0; var7 <= 3; var7++) {
/*     */                 
/* 329 */                 int var20 = var12 + var17;
/* 330 */                 int var9 = var1 + var7;
/* 331 */                 int var10 = var15 + var19;
/* 332 */                 int var11 = this.worldObj.getBlockId(var20, var9, var10);
/*     */ 
/*     */                 
/* 335 */                 if (var11 > 0 && var11 != Block.bedrock.blockID && var11 != Block.endPortal.blockID && var11 != Block.endPortalFrame.blockID && var11 != Block.mantleOrCore.blockID)
/*     */                 {
/*     */                   
/* 338 */                   var18 = (this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, var20, var9, var10)).setDestroyedBy(this), true) || var18);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 344 */           if (var18)
/*     */           {
/* 346 */             this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1012, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 351 */       if (this.ticksExisted % 20 == 0)
/*     */       {
/* 353 */         heal(1.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82206_m() {
/* 360 */     func_82215_s(220);
/* 361 */     setHealth(getMaxHealth() / 3.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInWeb() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getNaturalDefense(DamageSource damage_source) {
/* 379 */     return super.getNaturalDefense(damage_source) + 4.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private double func_82214_u(int par1) {
/* 384 */     if (par1 <= 0)
/*     */     {
/* 386 */       return this.posX;
/*     */     }
/*     */ 
/*     */     
/* 390 */     float var2 = (this.renderYawOffset + (180 * (par1 - 1))) / 180.0F * 3.1415927F;
/* 391 */     float var3 = MathHelper.cos(var2);
/* 392 */     return this.posX + var3 * 1.3D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double func_82208_v(int par1) {
/* 398 */     return (par1 <= 0) ? (this.posY + 3.0D) : (this.posY + 2.2D);
/*     */   }
/*     */ 
/*     */   
/*     */   private double func_82213_w(int par1) {
/* 403 */     if (par1 <= 0)
/*     */     {
/* 405 */       return this.posZ;
/*     */     }
/*     */ 
/*     */     
/* 409 */     float var2 = (this.renderYawOffset + (180 * (par1 - 1))) / 180.0F * 3.1415927F;
/* 410 */     float var3 = MathHelper.sin(var2);
/* 411 */     return this.posZ + var3 * 1.3D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float func_82204_b(float par1, float par2, float par3) {
/* 417 */     float var4 = MathHelper.wrapAngleTo180_float(par2 - par1);
/*     */     
/* 419 */     if (var4 > par3)
/*     */     {
/* 421 */       var4 = par3;
/*     */     }
/*     */     
/* 424 */     if (var4 < -par3)
/*     */     {
/* 426 */       var4 = -par3;
/*     */     }
/*     */     
/* 429 */     return par1 + var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_82216_a(int par1, EntityLivingBase par2EntityLivingBase) {
/* 434 */     func_82209_a(par1, par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight() * 0.5D, par2EntityLivingBase.posZ, (par1 == 0 && this.rand.nextFloat() < 0.001F));
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_82209_a(int par1, double par2, double par4, double par6, boolean par8) {
/* 439 */     this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1014, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/* 440 */     double var9 = func_82214_u(par1);
/* 441 */     double var11 = func_82208_v(par1);
/* 442 */     double var13 = func_82213_w(par1);
/* 443 */     double var15 = par2 - var9;
/* 444 */     double var17 = par4 - var11;
/* 445 */     double var19 = par6 - var13;
/* 446 */     EntityWitherSkull var21 = new EntityWitherSkull(this.worldObj, this, var15, var17, var19);
/*     */     
/* 448 */     if (par8)
/*     */     {
/* 450 */       var21.setInvulnerable(true);
/*     */     }
/*     */     
/* 453 */     var21.posY = var11;
/* 454 */     var21.posX = var9;
/* 455 */     var21.posZ = var13;
/* 456 */     this.worldObj.spawnEntityInWorld(var21);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
/* 464 */     func_82216_a(0, par1EntityLivingBase);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 527 */     if (func_82212_n() > 0)
/*     */     {
/*     */       
/* 530 */       return null;
/*     */     }
/* 532 */     if (isArmored() && damage.isArrowDamage())
/*     */     {
/*     */       
/* 535 */       return null;
/*     */     }
/* 537 */     Entity var3 = damage.getResponsibleEntity();
/*     */     
/* 539 */     if (var3 != null && !var3.isEntityPlayer() && var3 instanceof EntityLivingBase && var3.getAsEntityLivingBase().getCreatureAttribute() == getCreatureAttribute())
/*     */     {
/*     */       
/* 542 */       return null;
/*     */     }
/* 544 */     if (this.field_82222_j <= 0) {
/* 545 */       this.field_82222_j = 20;
/*     */     }
/* 547 */     for (int var4 = 0; var4 < this.field_82224_i.length; var4++) {
/* 548 */       this.field_82224_i[var4] = this.field_82224_i[var4] + 3;
/*     */     }
/* 550 */     return super.attackEntityFrom(damage);
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 564 */     dropItem(Item.netherStar.itemID, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void tryDespawnEntity() {
/* 573 */     this.despawn_counter = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/* 578 */     return 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 586 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fall(float par1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPotionEffect(PotionEffect par1PotionEffect) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAIEnabled() {
/* 604 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 609 */     super.applyEntityAttributes();
/* 610 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(300.0D);
/* 611 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6000000238418579D);
/* 612 */     getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_82207_a(int par1) {
/* 617 */     return this.field_82221_e[par1];
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_82210_r(int par1) {
/* 622 */     return this.field_82220_d[par1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82212_n() {
/* 627 */     return this.dataWatcher.getWatchableObjectInt(20);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82215_s(int par1) {
/* 632 */     this.dataWatcher.updateObject(20, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWatchedTargetId(int par1) {
/* 640 */     return this.dataWatcher.getWatchableObjectInt(17 + par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82211_c(int par1, int par2) {
/* 645 */     this.dataWatcher.updateObject(17 + par1, Integer.valueOf(par2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isArmored() {
/* 654 */     return (getHealth() <= getMaxHealth() / 2.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 662 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mountEntity(Entity par1Entity) {
/* 670 */     this.ridingEntity = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 675 */     return 50;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 680 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 685 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityWither.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */