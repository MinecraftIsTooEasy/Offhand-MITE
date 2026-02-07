/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAIOwnerHurtByTarget
/*    */   extends EntityAITarget
/*    */ {
/*    */   EntityTameable theDefendingTameable;
/*    */   EntityLivingBase theOwnerAttacker;
/*    */   private int field_142051_e;
/*    */   
/*    */   public EntityAIOwnerHurtByTarget(EntityTameable entityTameable) {
/* 11 */     super(entityTameable, false);
/* 12 */     this.theDefendingTameable = entityTameable;
/* 13 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 18 */     if (!this.theDefendingTameable.isTamed()) return false; 
/* 19 */     EntityLivingBase entityLivingBase = this.theDefendingTameable.func_130012_q();
/* 20 */     if (entityLivingBase == null) return false; 
/* 21 */     this.theOwnerAttacker = entityLivingBase.getAITarget();
/* 22 */     int i = entityLivingBase.func_142015_aE();
/* 23 */     return (i != this.field_142051_e && isSuitableTarget(this.theOwnerAttacker, false) && this.theDefendingTameable.func_142018_a(this.theOwnerAttacker, entityLivingBase));
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 28 */     this.taskOwner.setAttackTarget(this.theOwnerAttacker);
/*    */     
/* 30 */     EntityLivingBase entityLivingBase = this.theDefendingTameable.func_130012_q();
/* 31 */     if (entityLivingBase != null) {
/* 32 */       this.field_142051_e = entityLivingBase.func_142015_aE();
/*    */     }
/*    */     
/* 35 */     super.startExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIOwnerHurtByTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */