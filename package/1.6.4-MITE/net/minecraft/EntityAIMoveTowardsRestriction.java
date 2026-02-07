/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIMoveTowardsRestriction
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature theEntity;
/*    */   private double movePosX;
/*    */   private double movePosY;
/*    */   private double movePosZ;
/*    */   private double movementSpeed;
/*    */   
/*    */   public EntityAIMoveTowardsRestriction(EntityCreature entityCreature, double d) {
/* 16 */     this.theEntity = entityCreature;
/* 17 */     this.movementSpeed = d;
/* 18 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 23 */     if (this.theEntity.func_110173_bK()) return false; 
/* 24 */     ChunkCoordinates chunkCoordinates = this.theEntity.getHomePosition();
/* 25 */     Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 16, 7, this.theEntity.worldObj.getWorldVec3Pool().getVecFromPool(chunkCoordinates.posX, chunkCoordinates.posY, chunkCoordinates.posZ));
/* 26 */     if (vec3 == null) return false; 
/* 27 */     this.movePosX = vec3.xCoord;
/* 28 */     this.movePosY = vec3.yCoord;
/* 29 */     this.movePosZ = vec3.zCoord;
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 35 */     return !this.theEntity.getNavigator().noPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 40 */     this.theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.movementSpeed);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIMoveTowardsRestriction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */