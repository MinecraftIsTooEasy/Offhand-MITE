/*     */ package net.minecraft;
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
/*     */ public class EntitySnowball
/*     */   extends EntityThrowable
/*     */ {
/*     */   public EntitySnowball(World world) {
/*  52 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySnowball(World world, EntityLivingBase thrower) {
/*  57 */     super(world, thrower);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySnowball(World world, double pos_x, double pos_y, double pos_z) {
/*  62 */     super(world, pos_x, pos_y, pos_z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(RaycastCollision rc) {
/*  73 */     if (!this.worldObj.isRemote)
/*     */     {
/*  75 */       if (rc.isEntity()) {
/*     */         float damage;
/*  77 */         Entity entity_hit = rc.getEntityHit();
/*     */ 
/*     */ 
/*     */         
/*  81 */         if (entity_hit instanceof EntityBlaze || entity_hit instanceof EntityFireElemental) {
/*     */           
/*  83 */           damage = 3.0F;
/*  84 */           entity_hit.causeQuenchEffect();
/*     */         }
/*  86 */         else if (entity_hit instanceof EntityMagmaCube || entity_hit instanceof EntityNetherspawn) {
/*     */           
/*  88 */           damage = 2.0F;
/*  89 */           entity_hit.causeQuenchEffect();
/*     */         }
/*     */         else {
/*     */           
/*  93 */           damage = 1.0F;
/*     */         } 
/*     */         
/*  96 */         entity_hit.attackEntityFrom(new Damage(DamageSource.causeThrownDamage(this, getThrower()), damage));
/*     */         
/*  98 */         if (entity_hit.isBurning()) {
/*     */           
/* 100 */           entity_hit.causeQuenchEffect();
/*     */           
/* 102 */           if (Math.random() < 0.5D) {
/* 103 */             entity_hit.extinguish();
/*     */           }
/* 105 */         } else if (entity_hit instanceof EntityEarthElemental) {
/*     */           
/* 107 */           EntityEarthElemental elemental = (EntityEarthElemental)entity_hit;
/*     */           
/* 109 */           if (elemental.isMagma()) {
/* 110 */             elemental.convertToNormal(true);
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/* 115 */         if (rc.getNeighborOfBlockHit() == Block.fire)
/*     */         {
/* 117 */           this.worldObj.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, this);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 124 */         rc.getBlockHit().onEntityCollidedWithBlock(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, this);
/*     */       } 
/*     */     }
/*     */     
/* 128 */     for (int var3 = 0; var3 < 8; var3++)
/*     */     {
/*     */       
/* 131 */       this.worldObj.spawnParticle(EnumParticle.snowballpoof, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 134 */     if (!this.worldObj.isRemote)
/*     */     {
/* 136 */       setDead();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 142 */     return Item.snowball;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySnowball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */