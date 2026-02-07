/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityVampireBat
/*     */   extends EntityBat
/*     */ {
/*     */   private int attack_cooldown;
/*     */   private int feed_cooldown;
/*     */   
/*     */   public EntityVampireBat(World world) {
/*  12 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  17 */     super.applyEntityAttributes();
/*     */     
/*  19 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 1.0D * getScaleFactor());
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  24 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/*  26 */     this.feed_cooldown = par1NBTTagCompound.getShort("feed_cooldown");
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  31 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/*  33 */     if (this.feed_cooldown > 0) {
/*  34 */       par1NBTTagCompound.setShort("feed_cooldown", (short)this.feed_cooldown);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  39 */     super.onUpdate();
/*     */     
/*  41 */     if (this.attack_cooldown > 0) {
/*  42 */       this.attack_cooldown--;
/*     */     }
/*  44 */     if (this.feed_cooldown > 0)
/*     */     {
/*  46 */       if (getHealth() < getMaxHealth()) {
/*     */         
/*  48 */         this.feed_cooldown = 0;
/*     */       }
/*  50 */       else if (--this.feed_cooldown > 0) {
/*     */         
/*  52 */         Entity target = getAttackTarget();
/*     */         
/*  54 */         if (target != null && !preysUpon(target)) {
/*  55 */           setAttackTarget((EntityLivingBase)null);
/*     */         }
/*     */       } 
/*     */     }
/*  59 */     if (getTicksExistedWithOffset() % 20 == 0) {
/*  60 */       setAttackTarget(this.worldObj.getClosestPrey(this, 32.0D, true, false));
/*     */     }
/*     */   }
/*     */   
/*     */   protected final boolean preysUpon(Entity entity) {
/*  65 */     if (this.feed_cooldown > 0) {
/*  66 */       return (entity.isEntityPlayer() && !entity.getAsPlayer().inCreativeMode());
/*     */     }
/*  68 */     return ((entity.isEntityPlayer() && !entity.getAsPlayer().inCreativeMode()) || entity.isTrueAnimal() || entity instanceof EntityVillager);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void collideWithEntity(Entity entity) {
/*  73 */     super.collideWithEntity(entity);
/*     */     
/*  75 */     if (this.attack_cooldown <= 0 && entity == getAttackTarget()) {
/*     */       
/*  77 */       if (this.boundingBox.copy().scaleXZ(0.5D).intersectsWith(entity.boundingBox)) {
/*     */         
/*  79 */         EntityDamageResult result = EntityMob.attackEntityAsMob(this, entity);
/*     */         
/*  81 */         if (result != null && result.entityLostHealth()) {
/*     */           
/*  83 */           heal(result.getAmountOfHealthLost(), EnumEntityFX.vampiric_gain);
/*     */           
/*  85 */           if (entity instanceof EntityOcelot) {
/*     */             
/*  87 */             EntityOcelot ocelot = (EntityOcelot)entity;
/*     */             
/*  89 */             if (ocelot.getHealth() > 0.0F && ocelot.getTarget() == null) {
/*  90 */               ocelot.setTarget(this);
/*     */             }
/*     */           } 
/*  93 */           if (!(this instanceof EntityGiantVampireBat) && getHealth() >= getMaxHealth()) {
/*  94 */             this.feed_cooldown = 1200;
/*     */           }
/*     */         } 
/*     */       } 
/*  98 */       this.attack_cooldown = 20;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 104 */     return 8;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityVampireBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */