/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAITargetNonTamed
/*    */   extends EntityAINearestAttackableTarget
/*    */ {
/*    */   private EntityTameable theTameable;
/*    */   
/*    */   public EntityAITargetNonTamed(EntityTameable par1EntityTameable, Class par2Class, int par3, boolean par4) {
/*  9 */     super(par1EntityTameable, par2Class, par3, par4);
/* 10 */     this.theTameable = par1EntityTameable;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 18 */     if (!this.theTameable.canEat()) {
/* 19 */       return false;
/*    */     }
/* 21 */     return (!this.theTameable.isTamed() && super.shouldExecute());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 26 */     if (!this.theTameable.canEat()) {
/* 27 */       return false;
/*    */     }
/* 29 */     return super.continueExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAITargetNonTamed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */