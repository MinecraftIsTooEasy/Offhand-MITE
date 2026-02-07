/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class EntityAILookIdle
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityLiving idleEntity;
/*    */   private double lookX;
/*    */   private double lookZ;
/*    */   private int idleTime;
/*    */   
/*    */   public EntityAILookIdle(EntityLiving entityLiving) {
/* 13 */     this.idleEntity = entityLiving;
/* 14 */     setMutexBits(3);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 19 */     return (this.idleEntity.getRNG().nextFloat() < 0.02F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 24 */     return (this.idleTime >= 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 29 */     double d = 6.283185307179586D * this.idleEntity.getRNG().nextDouble();
/* 30 */     this.lookX = Math.cos(d);
/* 31 */     this.lookZ = Math.sin(d);
/* 32 */     this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 37 */     this.idleTime--;
/* 38 */     this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + this.idleEntity.getEyeHeight(), this.idleEntity.posZ + this.lookZ, 10.0F, this.idleEntity.getVerticalFaceSpeed());
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAILookIdle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */