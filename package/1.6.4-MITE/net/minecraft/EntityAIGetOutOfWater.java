/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class EntityAIGetOutOfWater
/*     */   extends EntityAIMovementTask
/*     */ {
/*   7 */   private int max_path_length = 16;
/*     */ 
/*     */   
/*     */   public EntityAIGetOutOfWater(EntityLiving task_owner, float movement_speed) {
/*  11 */     super(task_owner, movement_speed, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  16 */     if (this.task_owner.riddenByEntity instanceof EntityPlayer) {
/*  17 */       return false;
/*     */     }
/*  19 */     if (!RNG.chance_in_16[++this.random_number_index & 0x7FFF]) {
/*  20 */       return false;
/*     */     }
/*  22 */     if (this.task_owner instanceof EntityLivestock && ((EntityLivestock)this.task_owner).isThirsty()) {
/*  23 */       return false;
/*     */     }
/*  25 */     if (!this.task_owner.breathesAir() || this.task_owner.canBreatheUnderwater()) {
/*  26 */       return false;
/*     */     }
/*     */     
/*  29 */     return this.task_owner.isInWater();
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
/*     */   protected PathEntity getMovementPath() {
/*  52 */     int task_owner_x = MathHelper.floor_double(this.task_owner.posX);
/*  53 */     int task_owner_y = MathHelper.floor_double(this.task_owner.posY);
/*  54 */     int task_owner_z = MathHelper.floor_double(this.task_owner.posZ);
/*     */     
/*  56 */     double shortest_distance_sq_to_solid_ground = 0.0D;
/*  57 */     PathEntity selected_path = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     int domain_size = this.max_path_length * 2 + 1;
/*     */     
/*  65 */     for (int attempt = 0; attempt < 16; attempt++) {
/*     */       
/*  67 */       int dx = RNG.int_max[++this.random_number_index & 0x7FFF] % domain_size - this.max_path_length;
/*  68 */       int dy = RNG.int_7_minus_3[++this.random_number_index & 0x7FFF];
/*  69 */       int dz = RNG.int_max[++this.random_number_index & 0x7FFF] % domain_size - this.max_path_length;
/*     */       
/*  71 */       int trial_x = task_owner_x + dx;
/*  72 */       int trial_y = task_owner_y + dy;
/*  73 */       int trial_z = task_owner_z + dz;
/*     */       int i;
/*  75 */       for (i = 0; i < 8;) {
/*     */         
/*  77 */         if (this.world.isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, false)) {
/*  78 */           trial_y--;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  83 */       for (i = 0; i < 8;) {
/*     */         
/*  85 */         if (!this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false)) {
/*  86 */           trial_y++;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  91 */       if (this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false) && !this.world.isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, true) && !this.world.occupiedByLivingEntity(trial_x, trial_y, trial_z)) {
/*     */         
/*  93 */         double distance_sq_to_solid_ground = World.getDistanceSqFromDeltas(dx, dy, dz);
/*     */         
/*  95 */         if (selected_path == null || distance_sq_to_solid_ground < shortest_distance_sq_to_solid_ground) {
/*     */           
/*  97 */           PathEntity path = this.task_owner.getNavigator().getPathToXYZ(trial_x, trial_y, trial_z, this.max_path_length);
/*     */           
/*  99 */           if (path != null) {
/*     */             
/* 101 */             PathPoint final_point = path.getFinalPathPoint();
/*     */             
/* 103 */             if (!this.world.isAirOrPassableBlock(final_point.xCoord, final_point.yCoord - 1, final_point.zCoord, true) && !this.world.occupiedByLivingEntity(final_point.xCoord, final_point.yCoord, final_point.zCoord)) {
/*     */               
/* 105 */               dx = final_point.xCoord - task_owner_x;
/* 106 */               dy = final_point.yCoord - task_owner_y;
/* 107 */               dz = final_point.zCoord - task_owner_z;
/*     */               
/* 109 */               distance_sq_to_solid_ground = World.getDistanceSqFromDeltas(dx, dy, dz);
/*     */               
/* 111 */               if (selected_path == null || distance_sq_to_solid_ground < shortest_distance_sq_to_solid_ground) {
/*     */                 
/* 113 */                 shortest_distance_sq_to_solid_ground = distance_sq_to_solid_ground;
/* 114 */                 selected_path = path;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     if (selected_path == null) {
/* 123 */       selected_path = this.task_owner.findPathTowardXYZ(this.task_owner.spawn_x, this.task_owner.spawn_y, this.task_owner.spawn_z, 16, true);
/*     */     }
/* 125 */     return selected_path;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 130 */     if (this.task_owner instanceof EntityLivestock && ((EntityLivestock)this.task_owner).isThirsty()) {
/* 131 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 136 */     return super.continueExecuting();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIGetOutOfWater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */