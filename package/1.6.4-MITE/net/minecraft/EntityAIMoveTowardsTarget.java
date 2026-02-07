/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIMoveTowardsTarget
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature theEntity;
/*    */   private EntityLivingBase targetEntity;
/*    */   private double movePosX;
/*    */   private double movePosY;
/*    */   private double movePosZ;
/*    */   private double speed;
/*    */   private float maxTargetDistance;
/*    */   
/*    */   public EntityAIMoveTowardsTarget(EntityCreature entityCreature, double d, float f) {
/* 17 */     this.theEntity = entityCreature;
/* 18 */     this.speed = d;
/* 19 */     this.maxTargetDistance = f;
/* 20 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 25 */     this.targetEntity = this.theEntity.getAttackTarget();
/* 26 */     if (this.targetEntity == null) return false; 
/* 27 */     if (this.targetEntity.getDistanceSqToEntity(this.theEntity) > (this.maxTargetDistance * this.maxTargetDistance)) return false; 
/* 28 */     Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 16, 7, this.theEntity.worldObj.getWorldVec3Pool().getVecFromPool(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ));
/* 29 */     if (vec3 == null) return false; 
/* 30 */     this.movePosX = vec3.xCoord;
/* 31 */     this.movePosY = vec3.yCoord;
/* 32 */     this.movePosZ = vec3.zCoord;
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 38 */     return (!this.theEntity.getNavigator().noPath() && this.targetEntity.isEntityAlive() && this.targetEntity.getDistanceSqToEntity(this.theEntity) < (this.maxTargetDistance * this.maxTargetDistance));
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 43 */     this.targetEntity = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 48 */     this.theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIMoveTowardsTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */