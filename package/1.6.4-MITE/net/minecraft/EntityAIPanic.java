/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class EntityAIPanic
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature theEntityCreature;
/*    */   private double speed;
/*    */   private double randPosX;
/*    */   private double randPosY;
/*    */   private double randPosZ;
/*    */   
/*    */   public EntityAIPanic(EntityCreature entityCreature, double d) {
/* 14 */     this.theEntityCreature = entityCreature;
/* 15 */     this.speed = d;
/* 16 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 21 */     if (this.theEntityCreature.getAITarget() == null && !this.theEntityCreature.isBurning()) return false; 
/* 22 */     Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 5, 4);
/* 23 */     if (vec3 == null) return false; 
/* 24 */     this.randPosX = vec3.xCoord;
/* 25 */     this.randPosY = vec3.yCoord;
/* 26 */     this.randPosZ = vec3.zCoord;
/* 27 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 32 */     this.theEntityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 37 */     return !this.theEntityCreature.getNavigator().noPath();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIPanic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */