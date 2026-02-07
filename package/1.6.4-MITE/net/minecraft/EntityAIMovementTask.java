/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityAIMovementTask
/*     */   extends EntityAIBase
/*     */ {
/*     */   protected EntityLiving task_owner;
/*     */   protected float movement_speed;
/*     */   protected boolean swim_if_necessary;
/*     */   protected World world;
/*     */   protected boolean normally_avoids_water;
/*     */   private int last_task_owner_x;
/*     */   private int last_task_owner_z;
/*     */   private int task_owner_has_not_moved_counter;
/*     */   private int task_owner_has_not_moved_threshold;
/*     */   protected int random_number_index;
/*     */   
/*     */   public EntityAIMovementTask(EntityLiving task_owner, float movement_speed, boolean swim_if_necessary) {
/*  23 */     this.task_owner = task_owner;
/*  24 */     this.movement_speed = movement_speed;
/*  25 */     this.swim_if_necessary = swim_if_necessary;
/*     */     
/*  27 */     this.world = task_owner.worldObj;
/*     */     
/*  29 */     this.random_number_index = task_owner.rand.nextInt();
/*     */     
/*  31 */     setMutexBits(1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkIfTaskOwnerHasMoved() {
/*  36 */     int task_owner_x = MathHelper.floor_double(this.task_owner.posX);
/*     */     
/*  38 */     int task_owner_z = MathHelper.floor_double(this.task_owner.posZ);
/*     */     
/*  40 */     if (task_owner_x == this.last_task_owner_x && task_owner_z == this.last_task_owner_z) {
/*     */       
/*  42 */       this.task_owner_has_not_moved_counter++;
/*     */     }
/*     */     else {
/*     */       
/*  46 */       this.last_task_owner_x = task_owner_x;
/*     */       
/*  48 */       this.last_task_owner_z = task_owner_z;
/*     */       
/*  50 */       this.task_owner_has_not_moved_counter = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean taskOwnerIsStuck() {
/*  59 */     return (this.task_owner_has_not_moved_counter > this.task_owner_has_not_moved_threshold);
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract PathEntity getMovementPath();
/*     */   
/*     */   protected float getMovementSpeed() {
/*  66 */     return this.movement_speed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  71 */     this.last_task_owner_x = MathHelper.floor_double(this.task_owner.posX);
/*     */     
/*  73 */     this.last_task_owner_z = MathHelper.floor_double(this.task_owner.posZ);
/*     */     
/*  75 */     this.task_owner_has_not_moved_counter = 0;
/*  76 */     this.task_owner_has_not_moved_threshold = 30 + this.task_owner.rand.nextInt(20);
/*     */     
/*  78 */     this.normally_avoids_water = this.task_owner.getNavigator().getAvoidsWater();
/*     */     
/*  80 */     PathEntity path = getMovementPath();
/*     */     
/*  82 */     boolean path_is_valid = true;
/*     */     
/*  84 */     if (path == null || path.isFinished()) {
/*     */       
/*  86 */       path_is_valid = false;
/*     */     }
/*     */     else {
/*     */       
/*  90 */       PathPoint final_point = path.getFinalPathPoint();
/*     */       
/*  92 */       double distance_from_final_point = World.getDistanceSqFromDeltas(this.task_owner.posX - final_point.xCoord, this.task_owner.posY - final_point.yCoord, this.task_owner.posZ - final_point.zCoord);
/*     */ 
/*     */ 
/*     */       
/*  96 */       if (distance_from_final_point < 1.0D) {
/*  97 */         path_is_valid = false;
/*     */       }
/*     */     } 
/* 100 */     if (!path_is_valid && this.normally_avoids_water && this.swim_if_necessary) {
/*     */       
/* 102 */       this.task_owner.getNavigator().setAvoidsWater(false);
/* 103 */       path = getMovementPath();
/*     */     } 
/*     */     
/* 106 */     this.task_owner.getNavigator().setPath(path, getMovementSpeed());
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 111 */     this.task_owner.getNavigator().setSpeed(getMovementSpeed());
/* 112 */     checkIfTaskOwnerHasMoved();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 117 */     return (!taskOwnerIsStuck() && !this.task_owner.getNavigator().noPath());
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 122 */     this.task_owner.getNavigator().clearPathEntity();
/* 123 */     this.task_owner.getNavigator().setAvoidsWater(this.normally_avoids_water);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIMovementTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */