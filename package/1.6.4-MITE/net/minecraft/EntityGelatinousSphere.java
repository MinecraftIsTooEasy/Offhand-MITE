/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityGelatinousSphere
/*     */   extends EntityThrowable
/*     */ {
/*     */   private ItemGelatinousSphere item;
/*     */   private int subtype;
/*     */   
/*     */   public EntityGelatinousSphere(World world, ItemGelatinousSphere item, int subtype) {
/*  14 */     super(world);
/*  15 */     this.item = item;
/*  16 */     this.subtype = subtype;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityGelatinousSphere(World world, EntityLivingBase thrower, ItemGelatinousSphere item, int subtype) {
/*  21 */     super(world, thrower);
/*  22 */     this.item = item;
/*  23 */     this.subtype = subtype;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityGelatinousSphere(World world, double pos_x, double pos_y, double pos_z, ItemGelatinousSphere item, int subtype) {
/*  28 */     super(world, pos_x, pos_y, pos_z);
/*  29 */     this.item = item;
/*  30 */     this.subtype = subtype;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getGravityVelocity() {
/*  35 */     return 0.07F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onImpact(RaycastCollision rc) {
/*  40 */     if (rc.isEntity()) {
/*     */       
/*  42 */       EntityDamageResult result = rc.getEntityHit().attackEntityFrom(new Damage(DamageSource.causeThrownDamage(this, getThrower()), 1.0F + this.item.getAttackDamage(this.subtype)));
/*     */       
/*  44 */       if (result != null && result.entityWasNegativelyAffected()) {
/*  45 */         rc.getEntityHit().entityFX(EnumEntityFX.steam_with_hiss);
/*     */       }
/*     */     } 
/*  48 */     if (onServer() && rc.isBlock()) {
/*     */       
/*  50 */       Block block = rc.getBlockHit();
/*     */       
/*  52 */       int x = rc.block_hit_x;
/*  53 */       int y = rc.block_hit_y;
/*  54 */       int z = rc.block_hit_z;
/*     */       
/*  56 */       if (hasPepsin()) {
/*     */         
/*  58 */         int[] coords = World.getNeighboringBlockCoords(x, y, z, rc.face_hit);
/*     */         
/*  60 */         Block neighbor_block = this.worldObj.getBlock(coords);
/*     */         
/*  62 */         if (neighbor_block != null && neighbor_block.onContactWithPepsin(this.worldObj, coords[0], coords[1], coords[2], rc.face_hit.getOpposite(), true)) {
/*  63 */           block = this.worldObj.getBlock(x, y, z);
/*     */         }
/*  65 */         if (block != null && block.onContactWithPepsin(this.worldObj, x, y, z, rc.face_hit, true)) {
/*  66 */           block = this.worldObj.getBlock(x, y, z);
/*     */         }
/*     */       } 
/*  69 */       if (isAcidic()) {
/*     */         
/*  71 */         int[] coords = World.getNeighboringBlockCoords(x, y, z, rc.face_hit);
/*     */         
/*  73 */         Block neighbor_block = this.worldObj.getBlock(coords);
/*     */         
/*  75 */         if (neighbor_block != null && neighbor_block.onContactWithAcid(this.worldObj, coords[0], coords[1], coords[2], rc.face_hit.getOpposite(), true)) {
/*  76 */           block = this.worldObj.getBlock(x, y, z);
/*     */         }
/*  78 */         if (block != null && block.onContactWithAcid(this.worldObj, x, y, z, rc.face_hit, true)) {
/*  79 */           block = this.worldObj.getBlock(x, y, z);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  84 */       if (block != null) {
/*  85 */         block.onEntityCollidedWithBlock(this.worldObj, x, y, z, this);
/*     */       }
/*     */     } 
/*  88 */     EnumParticle enum_particle = (this.subtype == 0) ? EnumParticle.slime : ((this.subtype == 1) ? EnumParticle.ochre_jelly : ((this.subtype == 2) ? EnumParticle.crimson_blob : ((this.subtype == 3) ? EnumParticle.gray_ooze : EnumParticle.black_pudding)));
/*     */     
/*  90 */     for (int var5 = 0; var5 < 8; var5++) {
/*  91 */       this.worldObj.spawnParticle(enum_particle, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
/*     */     }
/*  93 */     if (!this.worldObj.isRemote) {
/*  94 */       setDead();
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemGelatinousSphere getModelItem() {
/*  99 */     return this.item;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getModelSubtype() {
/* 104 */     return this.subtype;
/*     */   }
/*     */ 
/*     */   
/*     */   public DamageSource getDamageType() {
/* 109 */     return this.item.getDamageType(this.subtype);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAcidic() {
/* 114 */     return getDamageType().isAcidDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPepsin() {
/* 119 */     return getDamageType().isPepsinDamage();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityGelatinousSphere.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */