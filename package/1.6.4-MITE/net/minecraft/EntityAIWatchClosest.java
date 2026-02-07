/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIWatchClosest
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityLiving theWatcher;
/*    */   protected Entity closestEntity;
/*    */   private float maxDistanceForPlayer;
/*    */   private int lookTime;
/*    */   private float field_75331_e;
/*    */   private Class watchedClass;
/*    */   
/*    */   public EntityAIWatchClosest(EntityLiving par1EntityLiving, Class par2Class, float par3) {
/* 18 */     this.theWatcher = par1EntityLiving;
/* 19 */     this.watchedClass = par2Class;
/* 20 */     this.maxDistanceForPlayer = par3;
/* 21 */     this.field_75331_e = 0.02F;
/* 22 */     setMutexBits(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAIWatchClosest(EntityLiving par1EntityLiving, Class par2Class, float par3, float par4) {
/* 27 */     this.theWatcher = par1EntityLiving;
/* 28 */     this.watchedClass = par2Class;
/* 29 */     this.maxDistanceForPlayer = par3;
/* 30 */     this.field_75331_e = par4;
/* 31 */     setMutexBits(2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 39 */     if (this.theWatcher.getRNG().nextFloat() >= this.field_75331_e)
/*    */     {
/* 41 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 45 */     if (this.theWatcher.getAttackTarget() != null)
/*    */     {
/* 47 */       this.closestEntity = this.theWatcher.getAttackTarget();
/*    */     }
/*    */     
/* 50 */     if (this.watchedClass == EntityPlayer.class) {
/*    */ 
/*    */       
/* 53 */       this.closestEntity = this.theWatcher.worldObj.getClosestPlayerToEntity(this.theWatcher, this.maxDistanceForPlayer, false);
/*    */     }
/*    */     else {
/*    */       
/* 57 */       this.closestEntity = this.theWatcher.worldObj.findNearestEntityWithinAABB(this.watchedClass, this.theWatcher.boundingBox.expand(this.maxDistanceForPlayer, 3.0D, this.maxDistanceForPlayer), this.theWatcher);
/*    */     } 
/*    */     
/* 60 */     return (this.closestEntity != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 69 */     return !this.closestEntity.isEntityAlive() ? false : ((this.theWatcher.getDistanceSqToEntity(this.closestEntity) > (this.maxDistanceForPlayer * this.maxDistanceForPlayer)) ? false : ((this.lookTime > 0)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 77 */     this.lookTime = 40 + this.theWatcher.getRNG().nextInt(40);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 85 */     this.closestEntity = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 93 */     this.theWatcher.getLookHelper().setLookPosition(this.closestEntity.posX, this.closestEntity.posY + this.closestEntity.getEyeHeight(), this.closestEntity.posZ, 10.0F, this.theWatcher.getVerticalFaceSpeed());
/* 94 */     this.lookTime--;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIWatchClosest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */