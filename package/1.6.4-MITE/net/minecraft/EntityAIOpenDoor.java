/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAIOpenDoor
/*    */   extends EntityAIDoorInteract
/*    */ {
/*    */   boolean field_75361_i;
/*    */   int field_75360_j;
/*    */   
/*    */   public EntityAIOpenDoor(EntityLiving par1EntityLiving, boolean par2) {
/* 10 */     super(par1EntityLiving);
/* 11 */     this.theEntity = par1EntityLiving;
/* 12 */     this.field_75361_i = par2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 20 */     return (this.field_75361_i && this.field_75360_j > 0 && super.continueExecuting());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 28 */     this.field_75360_j = 20;
/*    */ 
/*    */     
/* 31 */     if (this.targetDoor instanceof BlockDoor) {
/* 32 */       ((BlockDoor)this.targetDoor).onPoweredBlockChange(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, true);
/* 33 */     } else if (this.targetDoor instanceof BlockTrapDoor) {
/* 34 */       ((BlockTrapDoor)this.targetDoor).onPoweredBlockChange(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, true);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 42 */     if (this.field_75361_i)
/*    */     {
/*    */ 
/*    */       
/* 46 */       if (this.targetDoor instanceof BlockDoor) {
/* 47 */         ((BlockDoor)this.targetDoor).onPoweredBlockChange(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, false);
/* 48 */       } else if (this.targetDoor instanceof BlockTrapDoor) {
/* 49 */         ((BlockTrapDoor)this.targetDoor).onPoweredBlockChange(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, false);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 58 */     this.field_75360_j--;
/* 59 */     super.updateTask();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIOpenDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */