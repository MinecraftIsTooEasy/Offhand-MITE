/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIFleeAttackerOrPanic
/*     */   extends EntityAIMovementTask
/*     */ {
/*     */   private float chance_of_panicking;
/*     */   private int panic_countdown;
/*     */   boolean is_done_panicking;
/*     */   
/*     */   public EntityAIFleeAttackerOrPanic(EntityCreature task_owner, float movement_speed, float chance_of_panicking, boolean swim_if_necessary) {
/*  17 */     super(task_owner, movement_speed, swim_if_necessary);
/*  18 */     this.chance_of_panicking = chance_of_panicking;
/*     */   }
/*     */ 
/*     */   
/*     */   private Entity getAttacker() {
/*  23 */     return this.task_owner.getLastHarmingEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public void startPanicking() {
/*  28 */     this.panic_countdown = this.task_owner.rand.nextInt(100) + 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPanicking() {
/*  33 */     if (this.task_owner.isBurning() || this.task_owner.isSpooked()) {
/*  34 */       startPanicking();
/*     */     }
/*  36 */     return (this.panic_countdown > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  41 */     if (this.is_done_panicking && this.world.rand.nextInt(10000) == 0) {
/*  42 */       this.is_done_panicking = false;
/*     */     }
/*  44 */     this.task_owner.considerFleeing();
/*     */ 
/*     */     
/*  47 */     return (this.task_owner.has_decided_to_flee || isPanicking());
/*     */   }
/*     */ 
/*     */   
/*     */   private PathEntity getRandomPath() {
/*  52 */     Vec3 var1 = RandomPositionGenerator.findRandomTarget((EntityCreature)this.task_owner, 5, 4);
/*     */     
/*  54 */     if (var1 == null) {
/*  55 */       return null;
/*     */     }
/*  57 */     return this.task_owner.getNavigator().getPathToXYZ(var1.xCoord, var1.yCoord, var1.zCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   private PathEntity getPathAwayFromAttacker() {
/*  62 */     Entity attacker = getAttacker();
/*     */     
/*  64 */     if (attacker == null) {
/*  65 */       return null;
/*     */     }
/*  67 */     PathEntity path = this.task_owner.findPathAwayFromXYZ(MathHelper.floor_double(attacker.posX), MathHelper.floor_double(attacker.posY), MathHelper.floor_double(attacker.posZ), 16, 48, true);
/*     */     
/*  69 */     if (path == null) {
/*  70 */       return null;
/*     */     }
/*  72 */     this.task_owner.fleeing = true;
/*  73 */     this.task_owner.onFleeing();
/*     */     
/*  75 */     return path;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathEntity getMovementPath() {
/*  80 */     if (isPanicking())
/*     */     {
/*  82 */       return getRandomPath();
/*     */     }
/*     */ 
/*     */     
/*  86 */     PathEntity path = getPathAwayFromAttacker();
/*     */     
/*  88 */     if (path == null) {
/*     */       
/*  90 */       if (this.task_owner.rand.nextFloat() < this.chance_of_panicking) {
/*  91 */         startPanicking();
/*     */       }
/*  93 */       return getRandomPath();
/*     */     } 
/*     */ 
/*     */     
/*  97 */     return path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 104 */     if (!this.is_done_panicking && this.task_owner.rand.nextFloat() < this.chance_of_panicking) {
/* 105 */       startPanicking();
/*     */     }
/* 107 */     if (!isPanicking()) {
/* 108 */       this.is_done_panicking = true;
/*     */     }
/*     */ 
/*     */     
/* 112 */     super.startExecuting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 119 */     int panic_countdown_before = this.panic_countdown;
/*     */     
/* 121 */     if (this.task_owner.isBurning()) {
/* 122 */       startPanicking();
/* 123 */     } else if (this.panic_countdown > 0 && --this.panic_countdown == 0) {
/* 124 */       this.is_done_panicking = true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 129 */     if ((panic_countdown_before == 0 && this.panic_countdown > 0) || taskOwnerIsStuck() || this.task_owner.getNavigator().noPath()) {
/* 130 */       super.startExecuting();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean continueExecuting() {
/* 135 */     this.task_owner.considerStopFleeing();
/*     */     
/* 137 */     return (this.task_owner.has_decided_to_flee || isPanicking());
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getMovementSpeed() {
/* 142 */     return super.getMovementSpeed() + (isPanicking() ? 0.2F : 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 148 */     this.task_owner.considerStopFleeing();
/* 149 */     super.resetTask();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIFleeAttackerOrPanic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */