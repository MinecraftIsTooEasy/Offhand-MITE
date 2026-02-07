/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class EntityJumpHelper
/*    */ {
/*    */   private EntityLiving entity;
/*    */   private boolean isJumping;
/*    */   
/*    */   public EntityJumpHelper(EntityLiving entityLiving) {
/* 10 */     this.entity = entityLiving;
/*    */   }
/*    */   
/*    */   public void setJumping() {
/* 14 */     this.isJumping = true;
/*    */   }
/*    */   
/*    */   public void doJump() {
/* 18 */     this.entity.setJumping(this.isJumping);
/* 19 */     this.isJumping = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityJumpHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */