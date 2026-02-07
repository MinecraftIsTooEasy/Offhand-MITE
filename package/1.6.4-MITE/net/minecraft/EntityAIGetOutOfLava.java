/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class EntityAIGetOutOfLava
/*     */   extends EntityAIMovementTask
/*     */ {
/*   7 */   private int max_path_length = 16;
/*     */ 
/*     */   
/*     */   public EntityAIGetOutOfLava(EntityLiving task_owner, float movement_speed) {
/*  11 */     super(task_owner, movement_speed, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  16 */     if (!RNG.chance_in_16[++this.random_number_index & 0x7FFF]) {
/*  17 */       return false;
/*     */     }
/*  19 */     return this.task_owner.handleLavaMovement();
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
/*  42 */     int task_owner_x = MathHelper.floor_double(this.task_owner.posX);
/*  43 */     int task_owner_y = MathHelper.floor_double(this.task_owner.posY);
/*  44 */     int task_owner_z = MathHelper.floor_double(this.task_owner.posZ);
/*     */     
/*  46 */     double shortest_distance_sq_to_solid_ground = 0.0D;
/*  47 */     PathEntity selected_path = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     int domain_size = this.max_path_length * 2 + 1;
/*     */     
/*  55 */     for (int attempt = 0; attempt < 16; attempt++) {
/*     */       
/*  57 */       int dx = RNG.int_max[++this.random_number_index & 0x7FFF] % domain_size - this.max_path_length;
/*  58 */       int dy = RNG.int_7_minus_3[++this.random_number_index & 0x7FFF];
/*  59 */       int dz = RNG.int_max[++this.random_number_index & 0x7FFF] % domain_size - this.max_path_length;
/*     */       
/*  61 */       int trial_x = task_owner_x + dx;
/*  62 */       int trial_y = task_owner_y + dy;
/*  63 */       int trial_z = task_owner_z + dz;
/*     */       int i;
/*  65 */       for (i = 0; i < 8;) {
/*     */         
/*  67 */         if (this.world.isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, false)) {
/*  68 */           trial_y--;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  73 */       for (i = 0; i < 8;) {
/*     */         
/*  75 */         if (!this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false)) {
/*  76 */           trial_y++;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  81 */       if (this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false) && !this.world.isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, true) && !this.world.occupiedByLivingEntity(trial_x, trial_y, trial_z)) {
/*     */         
/*  83 */         double distance_sq_to_solid_ground = World.getDistanceSqFromDeltas(dx, dy, dz);
/*     */         
/*  85 */         if (selected_path == null || distance_sq_to_solid_ground < shortest_distance_sq_to_solid_ground) {
/*     */           
/*  87 */           PathEntity path = this.task_owner.getNavigator().getPathToXYZ(trial_x, trial_y, trial_z, this.max_path_length);
/*     */           
/*  89 */           if (path != null) {
/*     */             
/*  91 */             PathPoint final_point = path.getFinalPathPoint();
/*     */             
/*  93 */             if (!this.world.isAirOrPassableBlock(final_point.xCoord, final_point.yCoord - 1, final_point.zCoord, true) && !this.world.occupiedByLivingEntity(final_point.xCoord, final_point.yCoord, final_point.zCoord)) {
/*     */               
/*  95 */               dx = final_point.xCoord - task_owner_x;
/*  96 */               dy = final_point.yCoord - task_owner_y;
/*  97 */               dz = final_point.zCoord - task_owner_z;
/*     */               
/*  99 */               distance_sq_to_solid_ground = World.getDistanceSqFromDeltas(dx, dy, dz);
/*     */               
/* 101 */               if (selected_path == null || distance_sq_to_solid_ground < shortest_distance_sq_to_solid_ground) {
/*     */                 
/* 103 */                 shortest_distance_sq_to_solid_ground = distance_sq_to_solid_ground;
/* 104 */                 selected_path = path;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     if (selected_path == null) {
/* 113 */       selected_path = this.task_owner.findPathTowardXYZ(this.task_owner.spawn_x, this.task_owner.spawn_y, this.task_owner.spawn_z, 16, true);
/*     */     }
/* 115 */     return selected_path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 123 */     return super.continueExecuting();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIGetOutOfLava.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */