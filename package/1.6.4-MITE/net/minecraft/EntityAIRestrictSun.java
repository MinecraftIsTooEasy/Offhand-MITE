/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAIRestrictSun
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature theEntity;
/*    */   
/*    */   public EntityAIRestrictSun(EntityCreature par1EntityCreature) {
/*  9 */     this.theEntity = par1EntityCreature;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 17 */     if (this.theEntity instanceof EntitySkeleton && !((EntitySkeleton)this.theEntity).avoidsSunlight()) {
/* 18 */       return false;
/*    */     }
/* 20 */     return this.theEntity.worldObj.isDaytime();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 28 */     this.theEntity.getNavigator().setAvoidSun(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 36 */     this.theEntity.getNavigator().setAvoidSun(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIRestrictSun.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */