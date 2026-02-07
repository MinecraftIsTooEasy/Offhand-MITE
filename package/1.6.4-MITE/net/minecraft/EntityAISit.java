/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAISit
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityTameable theEntity;
/*    */   private boolean isSitting;
/*    */   
/*    */   public EntityAISit(EntityTameable par1EntityTameable) {
/* 12 */     this.theEntity = par1EntityTameable;
/* 13 */     setMutexBits(5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 21 */     if (!this.theEntity.isTamed())
/*    */     {
/* 23 */       return false;
/*    */     }
/* 25 */     if (this.theEntity.isInWater())
/*    */     {
/* 27 */       return false;
/*    */     }
/* 29 */     if (!this.theEntity.onGround)
/*    */     {
/* 31 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 35 */     EntityLivingBase var1 = this.theEntity.func_130012_q();
/* 36 */     return (var1 == null) ? true : ((this.theEntity.getDistanceSqToEntity(var1) < 144.0D && var1.getAITarget() != null) ? false : this.isSitting);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 45 */     this.theEntity.getNavigator().clearPathEntity();
/* 46 */     this.theEntity.setSitting(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 54 */     this.theEntity.setSitting(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSitting(boolean par1) {
/* 62 */     this.isSitting = par1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSitting() {
/* 67 */     return this.isSitting;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAISit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */