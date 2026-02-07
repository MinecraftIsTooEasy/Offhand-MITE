/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAISwimming
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityLiving theEntity;
/*    */   
/*    */   public EntityAISwimming(EntityLiving par1EntityLiving) {
/*  9 */     this.theEntity = par1EntityLiving;
/* 10 */     setMutexBits(4);
/* 11 */     par1EntityLiving.getNavigator().setCanSwim(true);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean isTheEntityDeepInLava() {
/* 16 */     return (this.theEntity.worldObj.getBlockMaterial(this.theEntity.getBlockPosX(), this.theEntity.getFootBlockPosY() + 1, this.theEntity.getBlockPosZ()) == Material.lava);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean isEntityBuoyant() {
/* 21 */     if (this.theEntity.isCollidedHorizontally) {
/* 22 */       return true;
/*    */     }
/* 24 */     if (this.theEntity.isPreventedFromSwimmingDueToParalyzation()) {
/* 25 */       return false;
/*    */     }
/* 27 */     if (this.theEntity instanceof EntityEarthElemental) {
/*    */       
/* 29 */       EntityLivingBase target = this.theEntity.getTarget();
/*    */       
/* 31 */       if (target == null || target.getHealth() <= 0.0F) {
/* 32 */         return false;
/*    */       }
/* 34 */       return isTheEntityDeepInLava();
/*    */     } 
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 46 */     if (!isEntityBuoyant()) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     if (this.theEntity.isInWater() && !this.theEntity.isInsideOfMaterial(Material.water, -this.theEntity.getEyeHeight()) && !BlockFluid.isFullWaterBlock(this.theEntity.worldObj, this.theEntity.getBlockPosX(), this.theEntity.getFootBlockPosY(), this.theEntity.getBlockPosZ(), true)) {
/* 51 */       return this.theEntity.handleLavaMovement();
/*    */     }
/* 53 */     return (this.theEntity.isInWater() || this.theEntity.handleLavaMovement());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 62 */     if (!isEntityBuoyant()) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 68 */     if (this.theEntity.worldObj.getBlockMaterial(MathHelper.floor_double(this.theEntity.posX), MathHelper.floor_double(this.theEntity.posY + this.theEntity.getEyeHeight() + 0.5D), MathHelper.floor_double(this.theEntity.posZ)) == Material.water) {
/*    */       
/* 70 */       this.theEntity.motionY = 0.10000000149011612D;
/*    */       
/*    */       return;
/*    */     } 
/* 74 */     if (this.theEntity.getRNG().nextFloat() < 0.8F)
/*    */     {
/* 76 */       this.theEntity.getJumpHelper().setJumping();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 83 */     if (!isEntityBuoyant()) {
/* 84 */       return false;
/*    */     }
/* 86 */     return super.continueExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAISwimming.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */