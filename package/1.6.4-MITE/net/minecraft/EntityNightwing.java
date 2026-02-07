/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityNightwing
/*     */   extends EntityBat
/*     */ {
/*     */   private int attack_cooldown;
/*     */   
/*     */   public EntityNightwing(World world) {
/*  11 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  16 */     super.applyEntityAttributes();
/*     */     
/*  18 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 1.0D * getScaleFactor());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isImmuneTo(DamageSource damage_source) {
/*  23 */     if (damage_source.hasSilverAspect() || damage_source.hasMagicAspect() || damage_source.isSunlight()) {
/*  24 */       return false;
/*     */     }
/*  26 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/*  31 */     if (perform_light_check && isInSunlight()) {
/*  32 */       return false;
/*     */     }
/*  34 */     return super.getCanSpawnHere(perform_light_check);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  39 */     super.onUpdate();
/*     */     
/*  41 */     if (this.attack_cooldown > 0) {
/*  42 */       this.attack_cooldown--;
/*     */     }
/*  44 */     if (getTicksExistedWithOffset() % 20 == 0) {
/*  45 */       setAttackTarget(this.worldObj.getClosestPrey(this, 32.0D, true, false));
/*     */     }
/*     */   }
/*     */   
/*     */   public void onLivingUpdate() {
/*  50 */     if (onServer())
/*     */     {
/*  52 */       if (isInSunlight()) {
/*     */         
/*  54 */         attackEntityFrom(new Damage(DamageSource.sunlight, 1000.0F));
/*     */       }
/*  56 */       else if (this.ticksExisted % 40 == 0) {
/*     */         
/*  58 */         float brightness = getBrightness(1.0F);
/*  59 */         int amount_to_heal = (int)((0.4F - brightness) * 10.0F);
/*     */         
/*  61 */         if (amount_to_heal > 0) {
/*  62 */           heal(amount_to_heal);
/*     */         }
/*     */       } 
/*     */     }
/*  66 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean preysUpon(Entity entity) {
/*  71 */     return ((entity.isEntityPlayer() && !entity.getAsPlayer().inCreativeMode()) || entity.isTrueAnimal() || entity instanceof EntityVillager);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void collideWithEntity(Entity entity) {
/*  76 */     super.collideWithEntity(entity);
/*     */     
/*  78 */     if (this.attack_cooldown <= 0 && entity == getAttackTarget()) {
/*     */       
/*  80 */       if (this.boundingBox.copy().scaleXZ(0.5D).intersectsWith(entity.boundingBox)) {
/*     */         
/*  82 */         EntityDamageResult result = EntityMob.attackEntityAsMob(this, entity);
/*     */         
/*  84 */         if (result != null && result.entityWasNegativelyAffected())
/*     */         {
/*  86 */           if (entity instanceof EntityPlayer) {
/*  87 */             (entity.getAsPlayer()).vision_dimming += entity.getAsEntityLivingBase().getAmountAfterResistance(1.25F, 4);
/*     */           }
/*     */         }
/*     */       } 
/*  91 */       this.attack_cooldown = 20;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/*  97 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 102 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 112 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityNightwing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */