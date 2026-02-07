/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAIRunAroundLikeCrazy
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityHorse horseHost;
/*    */   private double field_111178_b;
/*    */   private double field_111179_c;
/*    */   private double field_111176_d;
/*    */   private double field_111177_e;
/*    */   
/*    */   public EntityAIRunAroundLikeCrazy(EntityHorse par1EntityHorse, double par2) {
/* 13 */     this.horseHost = par1EntityHorse;
/* 14 */     this.field_111178_b = par2;
/* 15 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 23 */     if (!this.horseHost.isTame() && this.horseHost.riddenByEntity != null) {
/*    */       
/* 25 */       Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.horseHost, 5, 4);
/*    */       
/* 27 */       if (var1 == null)
/*    */       {
/* 29 */         return false;
/*    */       }
/*    */ 
/*    */       
/* 33 */       this.field_111179_c = var1.xCoord;
/* 34 */       this.field_111176_d = var1.yCoord;
/* 35 */       this.field_111177_e = var1.zCoord;
/* 36 */       return true;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 50 */     this.horseHost.getNavigator().tryMoveToXYZ(this.field_111179_c, this.field_111176_d, this.field_111177_e, this.field_111178_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 58 */     return (!this.horseHost.getNavigator().noPath() && this.horseHost.riddenByEntity != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 66 */     if (this.horseHost.getRNG().nextInt(50) == 0) {
/*    */       
/* 68 */       if (this.horseHost.riddenByEntity instanceof EntityPlayer) {
/*    */         
/* 70 */         int var1 = this.horseHost.getTemper();
/* 71 */         int var2 = this.horseHost.getMaxTemper();
/*    */         
/* 73 */         if (var2 > 0 && this.horseHost.getRNG().nextInt(var2) < var1) {
/*    */           
/* 75 */           this.horseHost.setTamedBy((EntityPlayer)this.horseHost.riddenByEntity);
/*    */           
/* 77 */           this.horseHost.worldObj.setEntityState(this.horseHost, EnumEntityState.tame_success);
/*    */           
/*    */           return;
/*    */         } 
/* 81 */         this.horseHost.increaseTemper(5);
/* 82 */         this.horseHost.setRebelliousForRidingCounter(4000);
/*    */       } 
/*    */       
/* 85 */       if (this.horseHost.riddenByEntity instanceof EntityLivingBase && this.horseHost.rand.nextInt(2) == 0) {
/* 86 */         this.horseHost.riddenByEntity.fallDistance = 3.0F;
/*    */       }
/* 88 */       this.horseHost.riddenByEntity.mountEntity((Entity)null);
/* 89 */       this.horseHost.riddenByEntity = null;
/* 90 */       this.horseHost.makeHorseRearWithSound();
/*    */       
/* 92 */       this.horseHost.worldObj.setEntityState(this.horseHost, EnumEntityState.tame_failure);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIRunAroundLikeCrazy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */