/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAILeapAtTarget
/*    */   extends EntityAIBase
/*    */ {
/*    */   EntityLiving leaper;
/*    */   EntityLivingBase leapTarget;
/*    */   float leapMotionY;
/*    */   
/*    */   public EntityAILeapAtTarget(EntityLiving par1EntityLiving, float par2) {
/* 16 */     this.leaper = par1EntityLiving;
/* 17 */     this.leapMotionY = par2;
/* 18 */     setMutexBits(5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 26 */     this.leapTarget = this.leaper.getAttackTarget();
/*    */     
/* 28 */     if (this.leapTarget == null)
/*    */     {
/* 30 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 34 */     double var1 = this.leaper.getDistanceSqToEntity(this.leapTarget);
/* 35 */     return (var1 >= 4.0D && var1 <= 16.0D) ? (!this.leaper.onGround ? false : ((this.leaper.getRNG().nextInt(5) == 0))) : false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 44 */     return !this.leaper.onGround;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 52 */     double var1 = this.leapTarget.posX - this.leaper.posX;
/* 53 */     double var3 = this.leapTarget.posZ - this.leaper.posZ;
/* 54 */     float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3);
/* 55 */     this.leaper.motionX += var1 / var5 * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.20000000298023224D;
/* 56 */     this.leaper.motionZ += var3 / var5 * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.20000000298023224D;
/* 57 */     this.leaper.motionY = this.leapMotionY;
/*    */     
/* 59 */     this.leaper.rotationYaw = (float)MathHelper.getYawInDegrees(this.leaper.posX, this.leaper.posZ, this.leapTarget.posX, this.leapTarget.posZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAILeapAtTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */