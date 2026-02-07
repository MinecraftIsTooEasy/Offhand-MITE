/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIWanderBackToSpawnPoint
/*    */   extends EntityAIMovementTask
/*    */ {
/*    */   public EntityAIWanderBackToSpawnPoint(EntityLiving task_owner, float movement_speed, boolean swim_if_necessary) {
/* 60 */     super(task_owner, movement_speed, swim_if_necessary);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 65 */     if (this.task_owner.getRNG().nextInt(40) > 0 || this.task_owner.getDistanceSqToSpawnPoint() < 64.0D) {
/* 66 */       return false;
/*    */     }
/* 68 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected PathEntity getMovementPath() {
/* 73 */     return this.task_owner.findPathTowardXYZ(this.task_owner.spawn_x, this.task_owner.spawn_y, this.task_owner.spawn_z, 16, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 78 */     if (this.task_owner.getDistanceSqToSpawnPoint() < 64.0D) {
/* 79 */       return false;
/*    */     }
/* 81 */     return super.continueExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIWanderBackToSpawnPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */