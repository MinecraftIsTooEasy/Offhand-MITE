/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityWitherSkull
/*     */   extends EntityFireball
/*     */ {
/*     */   public EntityWitherSkull(World par1World) {
/*   7 */     super(par1World);
/*   8 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityWitherSkull(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
/*  13 */     super(par1World, par2EntityLivingBase, par3, par5, par7);
/*  14 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getMotionFactor() {
/*  22 */     return isInvulnerable() ? 0.73F : super.getMotionFactor();
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityWitherSkull(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/*  27 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*  28 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/*  36 */     return false;
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
/*     */   protected void onImpact(RaycastCollision rc) {
/*  60 */     if (!this.worldObj.isRemote) {
/*     */ 
/*     */       
/*  63 */       if (rc.isEntity()) {
/*     */         
/*  65 */         Entity entity_hit = rc.getEntityHit();
/*     */         
/*  67 */         if (this.shootingEntity != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  75 */           EntityDamageResult result = entity_hit.attackEntityFrom(new Damage(DamageSource.causeMobDamage(this.shootingEntity), 8.0F));
/*     */           
/*  77 */           if (result != null && result.entityWasDestroyed()) {
/*  78 */             this.shootingEntity.heal(5.0F, EnumEntityFX.vampiric_gain);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/*  83 */           entity_hit.attackEntityFrom(new Damage(DamageSource.magic, 5.0F));
/*     */         } 
/*     */ 
/*     */         
/*  87 */         if (entity_hit instanceof EntityLivingBase) {
/*     */           
/*  89 */           byte var2 = 0;
/*     */           
/*  91 */           if (this.worldObj.difficultySetting > 1)
/*     */           {
/*  93 */             if (this.worldObj.difficultySetting == 2) {
/*     */               
/*  95 */               var2 = 10;
/*     */             }
/*  97 */             else if (this.worldObj.difficultySetting == 3) {
/*     */               
/*  99 */               var2 = 40;
/*     */             } 
/*     */           }
/*     */           
/* 103 */           if (var2 > 0)
/*     */           {
/*     */             
/* 106 */             ((EntityLivingBase)entity_hit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * var2, 1));
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 112 */       this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, 1.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
/* 113 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 122 */     return false;
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 142 */     this.dataWatcher.addObject(10, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInvulnerable() {
/* 150 */     return (this.dataWatcher.getWatchableObjectByte(10) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInvulnerable(boolean par1) {
/* 158 */     this.dataWatcher.updateObject(10, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCollisionPolicies(Raycast raycast) {
/* 163 */     raycast.setForBluntProjectile(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityWitherSkull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */