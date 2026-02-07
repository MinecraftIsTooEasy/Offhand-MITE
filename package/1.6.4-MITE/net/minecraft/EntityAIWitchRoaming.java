/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIWitchRoaming
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityWitch witch;
/*    */   
/*    */   public EntityAIWitchRoaming(EntityWitch witch) {
/* 11 */     this.witch = witch;
/* 12 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 20 */     if (this.witch.worldObj.isDaytime() || this.witch.worldObj.isPlayerNearby(this.witch.spawn_x, this.witch.spawn_y, this.witch.spawn_z, 16.0D) || this.witch.getRNG().nextInt(100) > 0) {
/* 21 */       return false;
/*    */     }
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 31 */     if (this.witch.worldObj.isDaytime() || this.witch.getDistanceSqToSpawnPoint() > 4096.0D || this.witch.worldObj.isPlayerNearby(this.witch.spawn_x, this.witch.spawn_y, this.witch.spawn_z, 16.0D) || this.witch.getRNG().nextInt(4000) == 0) {
/* 32 */       return false;
/*    */     }
/* 34 */     return !this.witch.getNavigator().noPath();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 42 */     this.witch.getNavigator().setPath(this.witch.findPathAwayFromXYZ(this.witch.spawn_x, this.witch.spawn_y, this.witch.spawn_z, 16, 48, true), 1.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 47 */     this.witch.getNavigator().clearPathEntity();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIWitchRoaming.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */