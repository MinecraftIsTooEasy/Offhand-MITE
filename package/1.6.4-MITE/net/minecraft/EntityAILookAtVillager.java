/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAILookAtVillager
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityIronGolem theGolem;
/*    */   private EntityVillager theVillager;
/*    */   private int lookTime;
/*    */   
/*    */   public EntityAILookAtVillager(EntityIronGolem entityIronGolem) {
/* 15 */     this.theGolem = entityIronGolem;
/* 16 */     setMutexBits(3);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 21 */     if (!this.theGolem.worldObj.isDaytime()) return false; 
/* 22 */     if (this.theGolem.getRNG().nextInt(8000) != 0) return false; 
/* 23 */     this.theVillager = (EntityVillager)this.theGolem.worldObj.findNearestEntityWithinAABB(EntityVillager.class, this.theGolem.boundingBox.expand(6.0D, 2.0D, 6.0D), this.theGolem);
/* 24 */     return (this.theVillager != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 29 */     return (this.lookTime > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 34 */     this.lookTime = 400;
/* 35 */     this.theGolem.setHoldingRose(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 40 */     this.theGolem.setHoldingRose(false);
/* 41 */     this.theVillager = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 46 */     this.theGolem.getLookHelper().setLookPositionWithEntity(this.theVillager, 30.0F, 30.0F);
/* 47 */     this.lookTime--;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAILookAtVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */