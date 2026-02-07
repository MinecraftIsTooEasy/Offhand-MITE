/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAITargetIfHostileToPlayers
/*    */   extends EntityAINearestAttackableTarget
/*    */ {
/*    */   private EntityWolf theEntity;
/*    */   
/*    */   public EntityAITargetIfHostileToPlayers(EntityWolf par1Entity, Class par2Class, int par3, boolean par4) {
/* 11 */     super(par1Entity, par2Class, par3, par4);
/* 12 */     this.theEntity = par1Entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 21 */     return ((this.theEntity.isHostileToPlayers() || this.theEntity.is_witch_ally) && super.shouldExecute());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 27 */     this.theEntity.setIsAttacking(true);
/*    */     
/* 29 */     super.startExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAITargetIfHostileToPlayers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */