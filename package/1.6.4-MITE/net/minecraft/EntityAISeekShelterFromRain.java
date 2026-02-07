/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class EntityAISeekShelterFromRain
/*     */   extends EntityAIMovementTask
/*     */ {
/*   7 */   private int max_path_length = 16;
/*     */ 
/*     */   
/*     */   public EntityAISeekShelterFromRain(EntityLiving task_owner, float movement_speed, boolean swim_if_necessary) {
/*  11 */     super(task_owner, movement_speed, swim_if_necessary);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  16 */     if (!RNG.chance_in_16[++this.random_number_index & 0x7FFF]) {
/*  17 */       return false;
/*     */     }
/*  19 */     if (this.task_owner instanceof EntityLivestock && ((EntityLivestock)this.task_owner).isThirsty()) {
/*  20 */       return false;
/*     */     }
/*  22 */     return this.task_owner.isInRain();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathEntity getMovementPath() {
/*  27 */     int task_owner_x = MathHelper.floor_double(this.task_owner.posX);
/*  28 */     int task_owner_y = MathHelper.floor_double(this.task_owner.posY);
/*  29 */     int task_owner_z = MathHelper.floor_double(this.task_owner.posZ);
/*     */     
/*  31 */     double shortest_distance_sq_to_shelter = 0.0D;
/*  32 */     PathEntity selected_path = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  38 */     int domain_size = this.max_path_length * 2 + 1;
/*     */     
/*  40 */     for (int attempt = 0; attempt < 16; attempt++) {
/*     */       
/*  42 */       int dx = RNG.int_max[++this.random_number_index & 0x7FFF] % domain_size - this.max_path_length;
/*  43 */       int dy = RNG.int_7_minus_3[++this.random_number_index & 0x7FFF];
/*  44 */       int dz = RNG.int_max[++this.random_number_index & 0x7FFF] % domain_size - this.max_path_length;
/*     */       
/*  46 */       int trial_x = task_owner_x + dx;
/*  47 */       int trial_y = task_owner_y + dy;
/*  48 */       int trial_z = task_owner_z + dz;
/*     */       int i;
/*  50 */       for (i = 0; i < 8;) {
/*     */         
/*  52 */         if (this.world.isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, false)) {
/*  53 */           trial_y--;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  58 */       for (i = 0; i < 8;) {
/*     */         
/*  60 */         if (!this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false)) {
/*  61 */           trial_y++;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  66 */       if (this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false) && !this.world.isInRain(trial_x, trial_y, trial_z) && !this.world.occupiedByLivingEntity(trial_x, trial_y, trial_z)) {
/*     */         
/*  68 */         double distance_sq_to_shelter = World.getDistanceSqFromDeltas(dx, dy, dz);
/*     */         
/*  70 */         if (selected_path == null || distance_sq_to_shelter < shortest_distance_sq_to_shelter) {
/*     */           
/*  72 */           PathEntity path = this.task_owner.getNavigator().getPathToXYZ(trial_x, trial_y, trial_z, this.max_path_length);
/*     */           
/*  74 */           if (path != null) {
/*     */             
/*  76 */             PathPoint final_point = path.getFinalPathPoint();
/*     */             
/*  78 */             if (!this.world.isInRain(final_point.xCoord, final_point.yCoord, final_point.zCoord) && !this.world.occupiedByLivingEntity(final_point.xCoord, final_point.yCoord, final_point.zCoord)) {
/*     */               
/*  80 */               dx = final_point.xCoord - task_owner_x;
/*  81 */               dy = final_point.yCoord - task_owner_y;
/*  82 */               dz = final_point.zCoord - task_owner_z;
/*     */               
/*  84 */               distance_sq_to_shelter = World.getDistanceSqFromDeltas(dx, dy, dz);
/*     */               
/*  86 */               if (selected_path == null || distance_sq_to_shelter < shortest_distance_sq_to_shelter) {
/*     */                 
/*  88 */                 shortest_distance_sq_to_shelter = distance_sq_to_shelter;
/*  89 */                 selected_path = path;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     return selected_path;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 102 */     if (this.task_owner instanceof EntityLivestock && ((EntityLivestock)this.task_owner).isThirsty()) {
/* 103 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 108 */     return super.continueExecuting();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAISeekShelterFromRain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */