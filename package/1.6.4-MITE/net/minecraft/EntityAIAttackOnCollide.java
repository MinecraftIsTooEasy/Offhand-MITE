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
/*     */ public class EntityAIAttackOnCollide
/*     */   extends EntityAIBase
/*     */ {
/*     */   World worldObj;
/*     */   EntityCreature attacker;
/*     */   int attackTick;
/*     */   double speedTowardsTarget;
/*     */   boolean longMemory;
/*     */   PathEntity entityPathEntity;
/*     */   Class classTarget;
/*     */   private int field_75445_i;
/*     */   public int ticks_suppressed;
/*     */   
/*     */   public EntityAIAttackOnCollide(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par5) {
/*  29 */     this(par1EntityCreature, par3, par5);
/*  30 */     this.classTarget = par2Class;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAIAttackOnCollide(EntityCreature par1EntityCreature, double par2, boolean par4) {
/*  35 */     this.attacker = par1EntityCreature;
/*  36 */     this.worldObj = par1EntityCreature.worldObj;
/*  37 */     this.speedTowardsTarget = par2;
/*  38 */     this.longMemory = par4;
/*  39 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  47 */     EntityLivingBase var1 = this.attacker.getAttackTarget();
/*     */     
/*  49 */     if (var1 == null)
/*     */     {
/*  51 */       return false;
/*     */     }
/*  53 */     if (this.classTarget == EntityAnimal.class && var1 instanceof EntityHellhound)
/*     */     {
/*  55 */       return false;
/*     */     }
/*  57 */     if (!var1.isEntityAlive())
/*     */     {
/*  59 */       return false;
/*     */     }
/*  61 */     if (this.classTarget != null && !this.classTarget.isAssignableFrom(var1.getClass()))
/*     */     {
/*  63 */       return false;
/*     */     }
/*  65 */     if (this.attacker.worldObj.getEntitiesWithinAABB(EntityFallingSand.class, this.attacker.boundingBox.expand(1.0D, 1.0D, 1.0D).translate(0.0D, 1.0D, 0.0D)).size() > 0) {
/*     */       
/*  67 */       if (this.ticks_suppressed < 10) {
/*  68 */         this.ticks_suppressed = 10;
/*     */       }
/*  70 */       if (this.attackTick < 10) {
/*  71 */         this.attackTick = 10;
/*     */       }
/*  73 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (this.ticks_suppressed < 1 && this.attacker.getTicksExistedWithOffset() % 10 == 0 && this.attacker.getDistanceToEntity(var1) < 3.0F && !this.attacker.hasLineOfStrikeAndTargetIsWithinStrikingDistance(var1)) {
/*     */ 
/*     */ 
/*     */       
/*  86 */       Vec3 limit = var1.getCenterPoint();
/*     */       
/*  88 */       RaycastCollision rc = this.attacker.worldObj.getBlockCollisionForPhysicalReach(this.attacker.getFootPosPlusFractionOfHeight(0.75F), limit);
/*     */       
/*  90 */       if (rc == null) {
/*  91 */         rc = this.attacker.worldObj.getBlockCollisionForPhysicalReach(this.attacker.getFootPosPlusFractionOfHeight(0.25F), limit);
/*     */       }
/*  93 */       boolean edging_prevented = false;
/*     */       
/*  95 */       if (rc != null)
/*     */       {
/*  97 */         if (rc.getBlockHit() == Block.cactus && this.attacker.canBeDamagedByCacti()) {
/*  98 */           edging_prevented = true;
/*     */         }
/*     */       }
/* 101 */       if (!edging_prevented) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 106 */         this.attacker.getMoveHelper().setMoveTo(var1.posX, var1.posY, var1.posZ, 2.0D);
/*     */         
/* 108 */         this.attacker.getLookHelper().setLookPositionWithEntity(var1, 30.0F, 30.0F);
/* 109 */         this.attacker.getLookHelper().onUpdateLook();
/* 110 */         this.attacker.setRotation(this.attacker.rotationYawHead, this.attacker.rotationPitch);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     if (this.ticks_suppressed > 0) {
/* 120 */       this.ticks_suppressed--;
/*     */     }
/* 122 */     this.attackTick = Math.max(this.attackTick - 1, 0);
/*     */     
/* 124 */     this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(var1);
/*     */     
/* 126 */     if (this.attacker.hasLineOfStrikeAndTargetIsWithinStrikingDistance(var1)) {
/* 127 */       return true;
/*     */     }
/* 129 */     return (this.entityPathEntity != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 138 */     EntityLivingBase var1 = this.attacker.getAttackTarget();
/* 139 */     return (var1 == null) ? false : (!var1.isEntityAlive() ? false : (!this.longMemory ? (!this.attacker.getNavigator().noPath()) : this.attacker.func_110176_b(MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 147 */     this.attacker.getNavigator().setPath(this.entityPathEntity, this.speedTowardsTarget);
/* 148 */     this.field_75445_i = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 156 */     this.attacker.getNavigator().clearPathEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStrikeTargetNow() {
/* 163 */     if (this.ticks_suppressed > 0 || this.attackTick > 0) {
/* 164 */       return false;
/*     */     }
/* 166 */     EntityLivingBase target = this.attacker.getTarget();
/*     */     
/* 168 */     return (target != null && this.attacker.hasLineOfStrikeAndTargetIsWithinStrikingDistance(target));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 178 */     if (this.ticks_suppressed > 0) {
/*     */       
/* 180 */       this.ticks_suppressed--;
/*     */       
/*     */       return;
/*     */     } 
/* 184 */     EntityLivingBase var1 = this.attacker.getAttackTarget();
/*     */     
/* 186 */     if (var1 == null) {
/*     */       return;
/*     */     }
/* 189 */     this.attacker.getLookHelper().setLookPositionWithEntity(var1, 30.0F, 30.0F);
/*     */     
/* 191 */     if ((this.longMemory || this.attacker.getEntitySenses().canSee(var1)) && --this.field_75445_i <= 0) {
/*     */       
/* 193 */       this.field_75445_i = 4 + this.attacker.getRNG().nextInt(7);
/* 194 */       this.attacker.getNavigator().tryMoveToEntityLiving(var1, this.speedTowardsTarget);
/*     */     } 
/*     */ 
/*     */     
/* 198 */     this.attackTick = Math.max(this.attackTick - 1, 0);
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
/* 234 */     if (this.attackTick > 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 240 */     if (this.attacker.hasLineOfStrikeAndTargetIsWithinStrikingDistance(var1)) {
/*     */ 
/*     */ 
/*     */       
/* 244 */       this.attackTick = 20;
/*     */       
/* 246 */       if (this.attacker.getHeldItemStack() != null)
/*     */       {
/* 248 */         this.attacker.swingArm();
/*     */       }
/*     */       
/* 251 */       this.attacker.attackEntityAsMob(var1);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 256 */     else if (this.attacker instanceof EntityAnimalWatcher) {
/*     */       
/* 258 */       EntityAnimalWatcher entity_digger = (EntityAnimalWatcher)this.attacker;
/*     */       
/* 260 */       if (entity_digger.isDiggingEnabled()) {
/*     */         
/* 262 */         int attacker_y = MathHelper.floor_double(this.attacker.posY + 0.5D);
/*     */         
/* 264 */         EntityLivingBase target = var1;
/*     */         
/* 266 */         int target_y = MathHelper.floor_double(target.posY + 0.5D);
/*     */         
/* 268 */         if (target_y > attacker_y) {
/*     */           
/* 270 */           int target_x = MathHelper.floor_double(target.posX);
/* 271 */           int target_z = MathHelper.floor_double(target.posZ);
/*     */           
/* 273 */           entity_digger.setBlockToDig(target_x, target_y - 1, target_z, true);
/*     */           
/* 275 */           this.attackTick = 20;
/*     */         } 
/*     */       } 
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
/*     */   
/*     */   public double getMovementSpeed() {
/* 292 */     return this.speedTowardsTarget;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIAttackOnCollide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */