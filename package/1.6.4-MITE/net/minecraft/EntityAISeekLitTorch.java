/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAISeekLitTorch
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityLiving task_owner;
/*     */   private float movement_speed;
/*     */   private int random_number_index;
/*     */   
/*     */   public EntityAISeekLitTorch(EntityLiving task_owner, float movement_speed) {
/*  14 */     this.task_owner = task_owner;
/*  15 */     this.movement_speed = movement_speed;
/*  16 */     setMutexBits(3);
/*  17 */     this.random_number_index = task_owner.rand.nextInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  25 */     if (this.task_owner.getRNG().nextInt((this.task_owner instanceof EntityShadow) ? 40 : 200) > 0) {
/*  26 */       return false;
/*     */     }
/*  28 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PathEntity findPathToLitTorch() {
/*  83 */     int max_candidates = 8;
/*     */     
/*  85 */     int[] candidate_x = new int[max_candidates];
/*  86 */     int[] candidate_y = new int[max_candidates];
/*  87 */     int[] candidate_z = new int[max_candidates];
/*  88 */     double[] candidate_distance_sq = new double[max_candidates];
/*     */     
/*  90 */     int max_distance = 16;
/*  91 */     int[] block_ids = { Block.torchWood.blockID, Block.torchRedstoneActive.blockID, Block.pumpkinLantern.blockID };
/*  92 */     int candidates = this.task_owner.worldObj.getNearestBlockCandidates(this.task_owner.posX, this.task_owner.posY + (this.task_owner.height * 0.75F), this.task_owner.posZ, max_distance, max_distance / 4, max_candidates, block_ids, candidate_x, candidate_y, candidate_z, candidate_distance_sq);
/*     */     
/*  94 */     if (candidates == 0) {
/*  95 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  99 */     for (int candidate_index = 0; candidate_index < candidates; candidate_index++) {
/*     */       
/* 101 */       PathEntity path = this.task_owner.getNavigator().getPathToXYZ(candidate_x[candidate_index], candidate_y[candidate_index], candidate_z[candidate_index], max_distance);
/*     */       
/* 103 */       if (path != null) {
/*     */         
/* 105 */         PathPoint final_point = path.getFinalPathPoint();
/*     */         
/* 107 */         if (this.task_owner.isNearLitTorch(final_point.xCoord, final_point.yCoord, final_point.zCoord)) {
/* 108 */           return path;
/*     */         }
/*     */       } 
/*     */     } 
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 120 */     return !this.task_owner.getNavigator().noPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 128 */     this.task_owner.getNavigator().setPath(findPathToLitTorch(), this.movement_speed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 133 */     this.task_owner.getNavigator().clearPathEntity();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAISeekLitTorch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */