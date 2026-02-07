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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class DispenserBehaviorPotionProjectile
/*    */   extends BehaviorProjectileDispense
/*    */ {
/*    */   DispenserBehaviorPotionProjectile(DispenserBehaviorPotion dispenserBehaviorPotion, ItemStack itemStack) {}
/*    */   
/*    */   protected IProjectile getProjectileEntity(World world, IPosition iPosition) {
/* 64 */     return new EntityPotion(world, iPosition.getX(), iPosition.getY(), iPosition.getZ(), this.potionItemStack.copy());
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_82498_a() {
/* 69 */     return super.func_82498_a() * 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_82500_b() {
/* 74 */     return super.func_82500_b() * 1.25F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorPotionProjectile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */