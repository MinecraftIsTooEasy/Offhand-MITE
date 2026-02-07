/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIPlay
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityVillager villagerObj;
/*    */   private EntityLivingBase targetVillager;
/*    */   private double field_75261_c;
/*    */   private int playTime;
/*    */   
/*    */   public EntityAIPlay(EntityVillager entityVillager, double d) {
/* 19 */     this.villagerObj = entityVillager;
/* 20 */     this.field_75261_c = d;
/* 21 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 26 */     if (this.villagerObj.getGrowingAge() >= 0) return false; 
/* 27 */     if (this.villagerObj.getRNG().nextInt(400) != 0) return false;
/*    */     
/* 29 */     List list = this.villagerObj.worldObj.getEntitiesWithinAABB(EntityVillager.class, this.villagerObj.boundingBox.expand(6.0D, 3.0D, 6.0D));
/* 30 */     double d = Double.MAX_VALUE;
/* 31 */     for (EntityVillager entityVillager : list) {
/* 32 */       if (entityVillager == this.villagerObj || 
/* 33 */         entityVillager.isPlaying() || 
/* 34 */         entityVillager.getGrowingAge() >= 0)
/* 35 */         continue;  double d1 = entityVillager.getDistanceSqToEntity(this.villagerObj);
/* 36 */       if (d1 > d)
/* 37 */         continue;  d = d1;
/* 38 */       this.targetVillager = entityVillager;
/*    */     } 
/*    */     
/* 41 */     if (this.targetVillager == null) {
/* 42 */       Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);
/* 43 */       if (vec3 == null) return false; 
/*    */     } 
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 50 */     return (this.playTime > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 55 */     if (this.targetVillager != null) this.villagerObj.setPlaying(true); 
/* 56 */     this.playTime = 1000;
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 61 */     this.villagerObj.setPlaying(false);
/* 62 */     this.targetVillager = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 67 */     this.playTime--;
/* 68 */     if (this.targetVillager != null) {
/* 69 */       if (this.villagerObj.getDistanceSqToEntity(this.targetVillager) > 4.0D) this.villagerObj.getNavigator().tryMoveToEntityLiving(this.targetVillager, this.field_75261_c);
/*    */     
/* 71 */     } else if (this.villagerObj.getNavigator().noPath()) {
/* 72 */       Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);
/* 73 */       if (vec3 == null)
/* 74 */         return;  this.villagerObj.getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, this.field_75261_c);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIPlay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */