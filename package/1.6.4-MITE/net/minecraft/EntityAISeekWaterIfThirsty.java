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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAISeekWaterIfThirsty
/*     */   extends EntityAIMovementTask
/*     */ {
/*     */   private EntityLivestock task_owner;
/*     */   
/*     */   public EntityAISeekWaterIfThirsty(EntityLivestock task_owner, float movement_speed, boolean swim_if_necessary) {
/* 105 */     super(task_owner, movement_speed, swim_if_necessary);
/* 106 */     this.task_owner = task_owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/* 111 */     if (!this.task_owner.isThirsty()) {
/* 112 */       return false;
/*     */     }
/* 114 */     if (this.task_owner.isNearWaterSource()) {
/* 115 */       return true;
/*     */     }
/* 117 */     if (this.task_owner.getRNG().nextInt(this.task_owner.isDesperateForWater() ? 10 : (this.task_owner.isVeryThirsty() ? 40 : 120)) > 0) {
/* 118 */       return false;
/*     */     }
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathEntity getMovementPath() {
/* 125 */     boolean is_very_thirsty = this.task_owner.isVeryThirsty();
/* 126 */     boolean is_desperate = this.task_owner.isDesperateForWater();
/*     */     
/* 128 */     int max_candidates = is_desperate ? 24 : (is_very_thirsty ? 16 : 8);
/*     */     
/* 130 */     int[] candidate_x = new int[max_candidates];
/* 131 */     int[] candidate_y = new int[max_candidates];
/* 132 */     int[] candidate_z = new int[max_candidates];
/* 133 */     double[] candidate_distance_sq = new double[max_candidates];
/*     */     
/* 135 */     int max_distance = is_desperate ? 48 : (is_very_thirsty ? 32 : 16);
/* 136 */     int[] block_ids = this.task_owner.getWaterBlockIDs();
/* 137 */     int candidates = this.task_owner.worldObj.getNearestBlockCandidates(this.task_owner.posX, this.task_owner.posY + (this.task_owner.height * 0.75F), this.task_owner.posZ, max_distance, max_distance / 8, max_candidates, block_ids, candidate_x, candidate_y, candidate_z, candidate_distance_sq);
/*     */ 
/*     */ 
/*     */     
/* 141 */     for (int candidate_index = 0; candidate_index < candidates; candidate_index++) {
/*     */       
/* 143 */       PathEntity path = this.task_owner.getNavigator().getPathToXYZ(candidate_x[candidate_index], candidate_y[candidate_index], candidate_z[candidate_index], max_distance);
/*     */       
/* 145 */       if (path != null) {
/*     */         
/* 147 */         PathPoint final_point = path.getFinalPathPoint();
/*     */         
/* 149 */         if (this.task_owner.isNearWaterSource(final_point.xCoord, final_point.yCoord, final_point.zCoord)) {
/* 150 */           return path;
/*     */         }
/*     */       } 
/*     */     } 
/* 154 */     return this.task_owner.findPathAwayFromXYZ(MathHelper.floor_double(this.task_owner.posX), MathHelper.floor_double(this.task_owner.posY), MathHelper.floor_double(this.task_owner.posZ), 16, 32, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 159 */     if (this.task_owner.isNearWaterSource()) {
/*     */       
/* 161 */       resetTask();
/*     */       
/*     */       return;
/*     */     } 
/* 165 */     super.startExecuting();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 170 */     if (this.task_owner.getWater() > 0.95F) {
/* 171 */       return false;
/*     */     }
/* 173 */     if (this.task_owner.isNearWaterSource()) {
/*     */       
/* 175 */       resetTask();
/* 176 */       return true;
/*     */     } 
/*     */     
/* 179 */     return super.continueExecuting();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAISeekWaterIfThirsty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */