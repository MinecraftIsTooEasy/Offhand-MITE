/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityChicken
/*     */   extends EntityLivestock
/*     */ {
/*     */   public float field_70886_e;
/*     */   public float destPos;
/*     */   public float field_70884_g;
/*     */   public float field_70888_h;
/*  10 */   public float field_70889_i = 1.0F;
/*     */ 
/*     */   
/*     */   private int max_num_feathers;
/*     */ 
/*     */   
/*     */   private int num_feathers;
/*     */ 
/*     */   
/*     */   public EntityChicken(World par1World) {
/*  20 */     super(par1World);
/*  21 */     setSize(0.3F, 0.7F);
/*     */ 
/*     */     
/*  24 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*     */ 
/*     */     
/*  27 */     this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
/*  28 */     this.tasks.addTask(3, new EntityAITempt(this, 1.0D, Item.seeds.itemID, false));
/*  29 */     this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
/*  30 */     this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
/*  31 */     this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  32 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*     */ 
/*     */     
/*  35 */     this.tasks.addTask(1, new EntityAIFleeAttackerOrPanic(this, 1.3F, 0.75F, true));
/*     */     
/*  37 */     if (this.worldObj != null && !this.worldObj.isRemote) {
/*     */       
/*  39 */       setManurePeriod(getManurePeriod() * 8 * 2);
/*     */ 
/*     */ 
/*     */       
/*  43 */       this.max_num_feathers = this.rand.nextInt(2) + 1;
/*     */       
/*  45 */       if (this.max_num_feathers > 1 && this.rand.nextInt(2) == 0) {
/*  46 */         this.max_num_feathers--;
/*     */       }
/*  48 */       this.num_feathers = this.max_num_feathers;
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
/*     */   protected void applyEntityAttributes() {
/*  62 */     super.applyEntityAttributes();
/*  63 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(4.0D);
/*  64 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  69 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/*  71 */     par1NBTTagCompound.setInteger("max_num_feathers", this.max_num_feathers);
/*  72 */     par1NBTTagCompound.setInteger("num_feathers", this.num_feathers);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  77 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/*  79 */     this.max_num_feathers = par1NBTTagCompound.getInteger("max_num_feathers");
/*  80 */     this.num_feathers = par1NBTTagCompound.getInteger("num_feathers");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  89 */     super.onLivingUpdate();
/*  90 */     this.field_70888_h = this.field_70886_e;
/*  91 */     this.field_70884_g = this.destPos;
/*  92 */     this.destPos = (float)(this.destPos + (this.onGround ? -1 : 4) * 0.3D);
/*     */     
/*  94 */     if (this.destPos < 0.0F)
/*     */     {
/*  96 */       this.destPos = 0.0F;
/*     */     }
/*     */     
/*  99 */     if (this.destPos > 1.0F)
/*     */     {
/* 101 */       this.destPos = 1.0F;
/*     */     }
/*     */     
/* 104 */     if (!this.onGround && this.field_70889_i < 1.0F)
/*     */     {
/* 106 */       this.field_70889_i = 1.0F;
/*     */     }
/*     */     
/* 109 */     this.field_70889_i = (float)(this.field_70889_i * 0.9D);
/*     */     
/* 111 */     if (!this.onGround && this.motionY < 0.0D)
/*     */     {
/* 113 */       this.motionY *= 0.6D;
/*     */     }
/*     */     
/* 116 */     this.field_70886_e += this.field_70889_i * 2.0F;
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
/*     */   public void produceGoods() {
/* 130 */     int feather_threshold = 100;
/*     */ 
/*     */     
/* 133 */     if (this.production_counter >= feather_threshold && this.rand.nextInt(feather_threshold * 5) == 0) {
/*     */       
/* 135 */       gainFeather();
/*     */       
/* 137 */       this.production_counter -= feather_threshold;
/*     */       
/*     */       return;
/*     */     } 
/* 141 */     int egg_threshold = 200;
/*     */     
/* 143 */     if (this.production_counter >= egg_threshold && this.rand.nextInt(20) == 0) {
/*     */       
/* 145 */       playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/* 146 */       dropItem(Item.egg.itemID, 1);
/*     */       
/* 148 */       this.production_counter -= egg_threshold;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 167 */     return "mob.chicken.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 175 */     return "mob.chicken.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 183 */     return "mob.chicken.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 192 */     makeSound("mob.chicken.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 200 */     return Item.feather.itemID;
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 243 */     if (isBurning()) {
/*     */       
/* 245 */       if (isWell()) {
/* 246 */         dropItem(Item.chickenCooked);
/*     */       }
/*     */     } else {
/*     */       
/* 250 */       for (int i = 0; i < this.num_feathers; i++) {
/* 251 */         dropItem(Item.feather);
/*     */       }
/* 253 */       if (isWell()) {
/* 254 */         dropItem(Item.chickenRaw);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityChicken spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
/* 263 */     return new EntityChicken(this.worldObj);
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
/*     */   public boolean isFoodItem(ItemStack item_stack) {
/* 277 */     return (item_stack != null && item_stack.getItem() == Item.seeds);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
/* 282 */     return spawnBabyAnimal(par1EntityAgeable);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 288 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 293 */     if (result == null || result.entityWasDestroyed()) {
/* 294 */       return result;
/*     */     }
/* 296 */     if (result.entityWasKnockedBack()) {
/*     */       
/* 298 */       if (getHealth() <= 0.0F || isChild() || damage.isFireDamage() || damage.isLavaDamage() || damage.hasMagicAspect() || damage.isDrowning() || damage.isStarving()) {
/* 299 */         return result;
/*     */       }
/* 301 */       for (int i = 0; i < (int)damage.getAmount() && this.num_feathers > 0; i++) {
/*     */         
/* 303 */         if (this.rand.nextFloat() < 0.2F)
/*     */         {
/* 305 */           if (tryDropFeather(false)) {
/* 306 */             result.setEntityWasAffected();
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/* 311 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void jump() {
/* 316 */     super.jump();
/*     */     
/* 318 */     if (!isChild() && this.rand.nextInt(40) == 0 && !isInsideOfMaterial(Material.water)) {
/* 319 */       tryDropFeather(true);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void gainFeather() {
/* 324 */     if (this.num_feathers < this.max_num_feathers) {
/* 325 */       this.num_feathers++;
/*     */     } else {
/* 327 */       dropItem(Item.feather.itemID, 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean tryDropFeather(boolean retain_at_least_one) {
/* 333 */     if (this.num_feathers < (retain_at_least_one ? 2 : 1) || isChild()) {
/* 334 */       return false;
/*     */     }
/* 336 */     dropItem(Item.feather.itemID, 1);
/*     */     
/* 338 */     this.num_feathers--;
/*     */     
/* 340 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 345 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityChicken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */