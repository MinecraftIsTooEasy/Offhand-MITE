/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAIOwnerHurtTarget
/*    */   extends EntityAITarget
/*    */ {
/*    */   EntityTameable theEntityTameable;
/*    */   EntityLivingBase theTarget;
/*    */   private int field_142050_e;
/*    */   
/*    */   public EntityAIOwnerHurtTarget(EntityTameable par1EntityTameable) {
/* 11 */     super(par1EntityTameable, false);
/* 12 */     this.theEntityTameable = par1EntityTameable;
/* 13 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 21 */     if (!this.theEntityTameable.isTamed())
/*    */     {
/* 23 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 27 */     EntityLivingBase var1 = this.theEntityTameable.func_130012_q();
/*    */     
/* 29 */     if (var1 == null)
/*    */     {
/* 31 */       return false;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     this.theTarget = var1.getLastAttackTarget();
/* 38 */     int var2 = var1.getLastAttackTime();
/* 39 */     return (var2 != this.field_142050_e && isSuitableTarget(this.theTarget, false) && this.theEntityTameable.func_142018_a(this.theTarget, var1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 49 */     this.taskOwner.setAttackTarget(this.theTarget);
/* 50 */     EntityLivingBase var1 = this.theEntityTameable.func_130012_q();
/*    */     
/* 52 */     if (var1 != null)
/*    */     {
/*    */       
/* 55 */       this.field_142050_e = var1.getLastAttackTime();
/*    */     }
/*    */     
/* 58 */     super.startExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIOwnerHurtTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */