/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIMoveToTree
/*     */   extends EntityAIMovementTask
/*     */ {
/*     */   public EntityAIMoveToTree(EntityLiving task_owner, float movement_speed) {
/*   9 */     super(task_owner, movement_speed, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  17 */     if (this.task_owner.getRNG().nextInt(40) > 0) {
/*  18 */       return false;
/*     */     }
/*  20 */     return this.task_owner.isBurning();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathEntity getMovementPath() {
/*  25 */     int max_candidates = 8;
/*     */     
/*  27 */     int[] candidate_x = new int[max_candidates];
/*  28 */     int[] candidate_y = new int[max_candidates];
/*  29 */     int[] candidate_z = new int[max_candidates];
/*  30 */     double[] candidate_distance_sq = new double[max_candidates];
/*     */     
/*  32 */     int max_distance = 16;
/*  33 */     int[] block_ids = { Block.wood.blockID };
/*  34 */     int candidates = this.task_owner.worldObj.getNearestBlockCandidates(this.task_owner.posX, this.task_owner.posY + (this.task_owner.height * 0.75F), this.task_owner.posZ, max_distance, max_distance / 4, max_candidates, block_ids, candidate_x, candidate_y, candidate_z, candidate_distance_sq);
/*     */     
/*  36 */     if (candidates == 0) {
/*  37 */       return null;
/*     */     }
/*  39 */     for (int candidate_index = 0; candidate_index < candidates; candidate_index++) {
/*     */       
/*  41 */       int x = candidate_x[candidate_index];
/*  42 */       int y = candidate_y[candidate_index];
/*  43 */       int z = candidate_z[candidate_index];
/*     */       
/*  45 */       WorldServer world = this.task_owner.worldObj.getAsWorldServer();
/*     */       
/*  47 */       for (int i = 0; i < world.playerEntities.size(); i++) {
/*     */         
/*  49 */         EntityPlayer player = world.playerEntities.get(i);
/*     */         
/*  51 */         if (!player.isGhost() && !player.isZevimrgvInTournament() && !player.isDead && player.getHealth() > 0.0F)
/*     */         {
/*     */           
/*  54 */           if (player.getFootBlockPosY() > y + 2 && player.getFootBlockPosY() < y + 9) {
/*     */             
/*  56 */             int dx = player.getBlockPosX() - x;
/*  57 */             int dz = player.getBlockPosZ() - z;
/*     */             
/*  59 */             int horizontal_distance_sq = dx * dx + dz * dz;
/*     */             
/*  61 */             if (horizontal_distance_sq <= 32) {
/*     */               
/*  63 */               PathEntity path = this.task_owner.getNavigator().getPathToXYZ(candidate_x[candidate_index], candidate_y[candidate_index], candidate_z[candidate_index], max_distance);
/*     */               
/*  65 */               if (path != null) {
/*     */                 
/*  67 */                 PathPoint final_point = path.getFinalPathPoint();
/*     */                 
/*  69 */                 if (WorldServer.getDistanceSqFromDeltas((final_point.xCoord - x), (final_point.yCoord - y), (final_point.zCoord - z)) < 2.0D) {
/*     */                   
/*  71 */                   this.task_owner.increased_chance_of_spreading_fire_countdown = 100;
/*  72 */                   return path;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 101 */     return !this.task_owner.getNavigator().noPath();
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
/*     */   public void resetTask() {
/* 114 */     this.task_owner.getNavigator().clearPathEntity();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIMoveToTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */