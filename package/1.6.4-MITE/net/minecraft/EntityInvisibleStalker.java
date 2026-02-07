/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityInvisibleStalker
/*     */   extends EntityMob
/*     */ {
/*     */   public EntityInvisibleStalker(World par1World) {
/*   9 */     super(par1World);
/*     */     
/*  11 */     getNavigator().setBreakDoors(true);
/*     */     
/*  13 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  14 */     this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  15 */     this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  16 */     this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  17 */     this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
/*  18 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  19 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*  20 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
/*  21 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */     
/*  23 */     this.tasks.addTask(4, new EntityAISeekLitTorch(this, 1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  30 */     super.applyEntityAttributes();
/*  31 */     getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
/*  32 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.23000000417232513D);
/*  33 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  38 */     super.entityInit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAIEnabled() {
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  53 */     tryDisableNearbyLightSource();
/*     */     
/*  55 */     super.onLivingUpdate();
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
/*     */   protected String getLivingSound() {
/*  87 */     return "imported.mob.invisiblestalker.say";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  92 */     return "imported.mob.invisiblestalker.hurt";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  97 */     return "imported.mob.invisiblestalker.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {}
/*     */   
/*     */   protected float getSoundVolume(String sound) {
/* 104 */     return 0.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 109 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 114 */     return EnumCreatureAttribute.UNDEFINED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 121 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 129 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onKillEntity(EntityLivingBase par1EntityLivingBase) {
/* 134 */     super.onKillEntity(par1EntityLivingBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 139 */     return super.onSpawnWithEgg(par1EntityLivingData);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 144 */     return super.getExperienceValue() * 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityInvisibleStalker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */