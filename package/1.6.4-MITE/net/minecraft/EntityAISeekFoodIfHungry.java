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
/*     */ 
/*     */ 
/*     */ public class EntityAISeekFoodIfHungry
/*     */   extends EntityAIMovementTask
/*     */ {
/*     */   private EntityLivestock task_owner;
/*     */   
/*     */   public EntityAISeekFoodIfHungry(EntityLivestock task_owner, float movement_speed, boolean swim_if_necessary) {
/* 102 */     super(task_owner, movement_speed, swim_if_necessary);
/* 103 */     this.task_owner = task_owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/* 108 */     if (!this.task_owner.isHungry()) {
/* 109 */       return false;
/*     */     }
/* 111 */     if (this.task_owner.isNearFood()) {
/* 112 */       return true;
/*     */     }
/* 114 */     if (this.task_owner.getRNG().nextInt(this.task_owner.isDesperateForFood() ? 10 : (this.task_owner.isVeryHungry() ? 40 : 120)) > 0) {
/* 115 */       return false;
/*     */     }
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathEntity getMovementPath() {
/* 122 */     boolean is_very_hungry = this.task_owner.isVeryHungry();
/* 123 */     boolean is_desperate = this.task_owner.isDesperateForFood();
/*     */     
/* 125 */     int max_candidates = is_desperate ? 24 : (is_very_hungry ? 16 : 8);
/*     */     
/* 127 */     int[] candidate_x = new int[max_candidates];
/* 128 */     int[] candidate_y = new int[max_candidates];
/* 129 */     int[] candidate_z = new int[max_candidates];
/* 130 */     double[] candidate_distance_sq = new double[max_candidates];
/*     */     
/* 132 */     int max_distance = is_desperate ? 48 : (is_very_hungry ? 32 : 16);
/* 133 */     int[] block_ids = this.task_owner.getFoodBlockIDs();
/* 134 */     int candidates = this.task_owner.worldObj.getNearestBlockCandidates(this.task_owner.posX, this.task_owner.posY + (this.task_owner.height * 0.75F), this.task_owner.posZ, max_distance, max_distance / 8, max_candidates, block_ids, candidate_x, candidate_y, candidate_z, candidate_distance_sq);
/*     */ 
/*     */ 
/*     */     
/* 138 */     for (int candidate_index = 0; candidate_index < candidates; candidate_index++) {
/*     */       
/* 140 */       PathEntity path = this.task_owner.getNavigator().getPathToXYZ(candidate_x[candidate_index], candidate_y[candidate_index], candidate_z[candidate_index], max_distance);
/*     */       
/* 142 */       if (path != null) {
/*     */         
/* 144 */         PathPoint final_point = path.getFinalPathPoint();
/*     */         
/* 146 */         if (this.task_owner.isNearFood(final_point.xCoord, final_point.yCoord, final_point.zCoord)) {
/* 147 */           return path;
/*     */         }
/*     */       } 
/*     */     } 
/* 151 */     return this.task_owner.findPathAwayFromXYZ(MathHelper.floor_double(this.task_owner.posX), MathHelper.floor_double(this.task_owner.posY), MathHelper.floor_double(this.task_owner.posZ), 16, 32, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 156 */     if (this.task_owner.isNearFood()) {
/*     */       return;
/*     */     }
/* 159 */     super.startExecuting();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 164 */     if (this.task_owner.getFood() > 0.95F) {
/* 165 */       return false;
/*     */     }
/* 167 */     if (this.task_owner.isNearFood()) {
/*     */       
/* 169 */       resetTask();
/* 170 */       return true;
/*     */     } 
/*     */     
/* 173 */     return super.continueExecuting();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAISeekFoodIfHungry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */