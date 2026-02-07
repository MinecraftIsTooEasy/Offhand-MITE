/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class EntityAINearestAttackableTargetSelector
/*    */   implements IEntitySelector
/*    */ {
/*    */   EntityAINearestAttackableTargetSelector(EntityAINearestAttackableTarget entityAINearestAttackableTarget, IEntitySelector iEntitySelector) {}
/*    */   
/*    */   public boolean isEntityApplicable(Entity entity) {
/* 33 */     if (!(entity instanceof EntityLivingBase)) return false; 
/* 34 */     if (this.field_111103_c != null && !this.field_111103_c.isEntityApplicable(entity)) return false; 
/* 35 */     return this.field_111102_d.isSuitableTarget((EntityLivingBase)entity, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAINearestAttackableTargetSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */