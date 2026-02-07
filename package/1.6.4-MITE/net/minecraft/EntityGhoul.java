/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityGhoul
/*     */   extends EntityAnimalWatcher
/*     */ {
/*     */   public EntityGhoul(World par1World) {
/*   9 */     super(par1World);
/*     */     
/*  11 */     getNavigator().setBreakDoors(true);
/*     */     
/*  13 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  14 */     this.tasks.addTask(1, new EntityAIBreakDoor(this));
/*  15 */     this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  16 */     this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  17 */     this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*     */     
/*  19 */     this.tasks.addTask(6, new EntityAIWander(this, 0.8D));
/*  20 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  21 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*  22 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
/*  23 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  24 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
/*     */     
/*  26 */     this.tasks.addTask(1, new EntityAIMoveToFoodItem(this, 1.0F, true));
/*  27 */     this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0D, true));
/*  28 */     this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 10, true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  35 */     super.applyEntityAttributes();
/*  36 */     getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
/*  37 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.2800000011920929D);
/*  38 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  43 */     super.entityInit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAIEnabled() {
/*  53 */     return true;
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
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/*  76 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*     */     
/*  78 */     if (result == null || result.entityWasDestroyed()) {
/*  79 */       return result;
/*     */     }
/*  81 */     if (result.entityLostHealth() && target instanceof EntityLivingBase) {
/*  82 */       target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 5));
/*     */     }
/*  84 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  89 */     return "imported.mob.ghoul.say";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  94 */     return "imported.mob.ghoul.hurt";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  99 */     return "imported.mob.ghoul.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 104 */     makeSound("mob.zombie.step", 0.15F, 1.0F);
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
/*     */   protected boolean preysUpon(Entity entity) {
/* 144 */     return entity instanceof EntityAnimal;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFoodItem(ItemStack item_stack) {
/* 149 */     return (item_stack != null && item_stack.getItem() instanceof ItemMeat);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawBackFaces() {
/* 154 */     return isWearingItems(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getCooloffForBlock() {
/* 159 */     return super.getCooloffForBlock() / 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 164 */     return super.getExperienceValue() * 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityGhoul.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */