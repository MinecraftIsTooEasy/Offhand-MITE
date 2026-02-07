/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class EntityAIHurtByTarget
/*    */   extends EntityAITarget
/*    */ {
/*    */   boolean entityCallsForHelp;
/*    */   private int field_142052_b;
/*    */   
/*    */   public EntityAIHurtByTarget(EntityCreature par1EntityCreature, boolean par2) {
/* 13 */     super(par1EntityCreature, false);
/* 14 */     this.entityCallsForHelp = par2;
/* 15 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 23 */     int var1 = this.taskOwner.func_142015_aE();
/* 24 */     return (var1 != this.field_142052_b && isSuitableTarget(this.taskOwner.getAITarget(), false));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 32 */     this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
/* 33 */     this.field_142052_b = this.taskOwner.func_142015_aE();
/*    */     
/* 35 */     if (this.entityCallsForHelp) {
/*    */ 
/*    */       
/* 38 */       double var1 = this.taskOwner.getMaxTargettingRange();
/* 39 */       List var3 = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), AxisAlignedBB.getAABBPool().getAABB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D).expand(var1, 10.0D, var1));
/* 40 */       Iterator<EntityCreature> var4 = var3.iterator();
/*    */       
/* 42 */       while (var4.hasNext()) {
/*    */         
/* 44 */         EntityCreature var5 = var4.next();
/*    */         
/* 46 */         if (this.taskOwner != var5 && var5.getAttackTarget() == null && !var5.isOnSameTeam(this.taskOwner.getAITarget()))
/*    */         {
/* 48 */           var5.setAttackTarget(this.taskOwner.getAITarget());
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 53 */     super.startExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIHurtByTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */