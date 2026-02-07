/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIFollowGolem
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityVillager theVillager;
/*    */   private EntityIronGolem theGolem;
/*    */   private int takeGolemRoseTick;
/*    */   private boolean tookGolemRose;
/*    */   
/*    */   public EntityAIFollowGolem(EntityVillager entityVillager) {
/* 17 */     this.theVillager = entityVillager;
/* 18 */     setMutexBits(3);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 23 */     if (this.theVillager.getGrowingAge() >= 0) return false; 
/* 24 */     if (!this.theVillager.worldObj.isDaytime()) return false;
/*    */     
/* 26 */     List list = this.theVillager.worldObj.getEntitiesWithinAABB(EntityIronGolem.class, this.theVillager.boundingBox.expand(6.0D, 2.0D, 6.0D));
/* 27 */     if (list.isEmpty()) return false;
/*    */     
/* 29 */     for (EntityIronGolem entityIronGolem : list) {
/* 30 */       if (entityIronGolem.getHoldRoseTick() > 0) {
/* 31 */         this.theGolem = entityIronGolem;
/*    */         break;
/*    */       } 
/*    */     } 
/* 35 */     return (this.theGolem != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 40 */     return (this.theGolem.getHoldRoseTick() > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 45 */     this.takeGolemRoseTick = this.theVillager.getRNG().nextInt(320);
/* 46 */     this.tookGolemRose = false;
/* 47 */     this.theGolem.getNavigator().clearPathEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 52 */     this.theGolem = null;
/* 53 */     this.theVillager.getNavigator().clearPathEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 58 */     this.theVillager.getLookHelper().setLookPositionWithEntity(this.theGolem, 30.0F, 30.0F);
/* 59 */     if (this.theGolem.getHoldRoseTick() == this.takeGolemRoseTick) {
/* 60 */       this.theVillager.getNavigator().tryMoveToEntityLiving(this.theGolem, 0.5D);
/* 61 */       this.tookGolemRose = true;
/*    */     } 
/*    */     
/* 64 */     if (this.tookGolemRose && 
/* 65 */       this.theVillager.getDistanceSqToEntity(this.theGolem) < 4.0D) {
/* 66 */       this.theGolem.setHoldingRose(false);
/* 67 */       this.theVillager.getNavigator().clearPathEntity();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIFollowGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */