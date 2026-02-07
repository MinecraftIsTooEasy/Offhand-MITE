/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIDefendVillage
/*    */   extends EntityAITarget
/*    */ {
/*    */   EntityIronGolem irongolem;
/*    */   EntityLivingBase villageAgressorTarget;
/*    */   
/*    */   public EntityAIDefendVillage(EntityIronGolem entityIronGolem) {
/* 12 */     super(entityIronGolem, false, true);
/* 13 */     this.irongolem = entityIronGolem;
/* 14 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 19 */     Village village = this.irongolem.getVillage();
/* 20 */     if (village == null) return false; 
/* 21 */     this.villageAgressorTarget = village.findNearestVillageAggressor(this.irongolem);
/* 22 */     if (!isSuitableTarget(this.villageAgressorTarget, false)) {
/*    */       
/* 24 */       if (this.taskOwner.getRNG().nextInt(20) == 0) {
/* 25 */         this.villageAgressorTarget = village.func_82685_c(this.irongolem);
/* 26 */         return isSuitableTarget(this.villageAgressorTarget, false);
/*    */       } 
/* 28 */       return false;
/*    */     } 
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 36 */     this.irongolem.setAttackTarget(this.villageAgressorTarget);
/* 37 */     super.startExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIDefendVillage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */