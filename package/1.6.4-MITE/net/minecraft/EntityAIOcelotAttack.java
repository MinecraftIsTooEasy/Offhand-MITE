/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAIOcelotAttack
/*    */   extends EntityAIBase
/*    */ {
/*    */   World theWorld;
/*    */   EntityLiving theEntity;
/*    */   EntityLivingBase theVictim;
/*    */   int attackCountdown;
/*    */   
/*    */   public EntityAIOcelotAttack(EntityLiving par1EntityLiving) {
/* 12 */     this.theEntity = par1EntityLiving;
/* 13 */     this.theWorld = par1EntityLiving.worldObj;
/* 14 */     setMutexBits(3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 22 */     EntityLivingBase var1 = this.theEntity.getAttackTarget();
/*    */     
/* 24 */     if (var1 == null)
/*    */     {
/* 26 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 30 */     this.theVictim = var1;
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 40 */     return !this.theVictim.isEntityAlive() ? false : ((this.theEntity.getDistanceSqToEntity(this.theVictim) > 225.0D) ? false : ((!this.theEntity.getNavigator().noPath() || shouldExecute())));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 48 */     this.theVictim = null;
/* 49 */     this.theEntity.getNavigator().clearPathEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 57 */     this.theEntity.getLookHelper().setLookPositionWithEntity(this.theVictim, 30.0F, 30.0F);
/* 58 */     double var1 = (this.theEntity.width * 2.0F * this.theEntity.width * 2.0F);
/* 59 */     double var3 = this.theEntity.getDistanceSq(this.theVictim.posX, this.theVictim.boundingBox.minY, this.theVictim.posZ);
/* 60 */     double var5 = 0.8D;
/*    */     
/* 62 */     if (var3 > var1 && var3 < 16.0D) {
/*    */       
/* 64 */       var5 = 1.33D;
/*    */     }
/* 66 */     else if (var3 < 225.0D) {
/*    */       
/* 68 */       var5 = 0.6D;
/*    */     } 
/*    */     
/* 71 */     this.theEntity.getNavigator().tryMoveToEntityLiving(this.theVictim, var5);
/* 72 */     this.attackCountdown = Math.max(this.attackCountdown - 1, 0);
/*    */     
/* 74 */     if (var3 <= var1)
/*    */     {
/* 76 */       if (this.attackCountdown <= 0) {
/*    */         
/* 78 */         this.attackCountdown = 20;
/* 79 */         this.theEntity.attackEntityAsMob(this.theVictim);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIOcelotAttack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */