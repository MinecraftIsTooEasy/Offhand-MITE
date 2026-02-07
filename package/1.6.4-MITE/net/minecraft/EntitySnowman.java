/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntitySnowman
/*     */   extends EntityGolem
/*     */   implements IRangedAttackMob {
/*     */   public EntitySnowman(World par1World) {
/*   7 */     super(par1World);
/*     */     
/*   9 */     setSize(0.6F, 1.8F);
/*  10 */     getNavigator().setAvoidsWater(true);
/*  11 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  12 */     this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
/*  13 */     this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
/*  14 */     this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  15 */     this.tasks.addTask(4, new EntityAILookIdle(this));
/*  16 */     this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));
/*     */     
/*  18 */     this.tasks.addTask(4, new EntityAISeekShelterFromRain(this, 1.0F, true));
/*  19 */     this.tasks.addTask(2, new EntityAIMoveToRepairItem(this, 1.0F, true));
/*  20 */     this.tasks.addTask(2, new EntityAIAvoidFire(this, 1.0F, true));
/*     */     
/*  22 */     this.tasks.addTask(1, new EntityAIGetOutOfWater(this, 1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAIEnabled() {
/*  30 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  35 */     super.applyEntityAttributes();
/*     */     
/*  37 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
/*  38 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.20000000298023224D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  47 */     super.onLivingUpdate();
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
/*  78 */     if (onServer())
/*     */     {
/*  80 */       if (getTicksExistedWithOffset() % (isInWater() ? 40 : 100) == 0 && isWet()) {
/*     */         
/*  82 */         attackEntityFrom(new Damage(DamageSource.melt, 1.0F));
/*     */       }
/*     */       else {
/*     */         
/*  86 */         float temperature = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ()).getFloatTemperature();
/*     */         
/*  88 */         if (temperature >= 0.2F) {
/*     */           
/*  90 */           int damage_rate = (temperature <= 0.4F) ? 4000 : ((temperature <= 0.6F) ? 3000 : ((temperature <= 0.8F) ? 2000 : ((temperature <= 1.0F) ? 1000 : ((temperature <= 1.2F) ? 500 : ((temperature <= 1.4F) ? 300 : ((temperature <= 1.6F) ? 200 : 100))))));
/*     */           
/*  92 */           if (getTicksExistedWithOffset() % damage_rate == 0) {
/*  93 */             attackEntityFrom(new Damage(DamageSource.melt, 1.0F));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 100 */     for (int var1 = 0; var1 < 4; var1++) {
/*     */       
/* 102 */       int var2 = MathHelper.floor_double(this.posX + ((var1 % 2 * 2 - 1) * 0.25F));
/* 103 */       int var3 = MathHelper.floor_double(this.posY);
/* 104 */       int var4 = MathHelper.floor_double(this.posZ + ((var1 / 2 % 2 * 2 - 1) * 0.25F));
/*     */ 
/*     */       
/* 107 */       if (this.worldObj.getBlockId(var2, var3, var4) == 0 && this.worldObj.getBiomeGenForCoords(var2, var4).getFloatTemperature() < 0.8F && Block.snow.canOccurAt(this.worldObj, var2, var3, var4, 0))
/*     */       {
/* 109 */         this.worldObj.setBlock(var2, var3, var4, Block.snow.blockID);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 119 */     return Item.snowball.itemID;
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 138 */     int num_drops = this.rand.nextInt(8 + damage_source.getLootingModifier() * 4);
/*     */     
/* 140 */     for (int i = 0; i < num_drops; i++) {
/* 141 */       dropItem(Item.snowball.itemID, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
/* 149 */     EntitySnowball var3 = new EntitySnowball(this.worldObj, this);
/* 150 */     double var4 = par1EntityLivingBase.posX - this.posX;
/* 151 */     double var6 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - var3.posY;
/* 152 */     double var8 = par1EntityLivingBase.posZ - this.posZ;
/* 153 */     float var10 = MathHelper.sqrt_double(var4 * var4 + var8 * var8) * 0.2F;
/* 154 */     var3.setThrowableHeading(var4, var6 + var10, var8, 1.6F, 12.0F);
/* 155 */     playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
/* 156 */     this.worldObj.spawnEntityInWorld(var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDouseFire() {
/* 161 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 171 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepairItem(ItemStack item_stack) {
/* 176 */     return (item_stack != null && item_stack.getItem() == Item.snowball);
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 192 */     if (damage.getSource() != DamageSource.melt) {
/* 193 */       damage.scaleAmount(2.0F);
/*     */     }
/* 195 */     if (damage.isArrowDamage() && damage.getAmount() > 1.0F) {
/* 196 */       damage.scaleAmount(0.5F, 1.0F);
/*     */     }
/* 198 */     return super.attackEntityFrom(damage);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityBiologicallyAlive() {
/* 203 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumEntityFX getHealFX() {
/* 208 */     return EnumEntityFX.repair;
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
/*     */   public boolean healsWithTime() {
/* 221 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySnowman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */