/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ final class DispenserBehaviorArrow
/*    */   extends BehaviorProjectileDispense
/*    */ {
/*    */   public ItemArrow item_arrow;
/*    */   
/*    */   public DispenserBehaviorArrow(ItemArrow item_arrow) {
/* 11 */     this.item_arrow = item_arrow;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected IProjectile getProjectileEntity(World par1World, IPosition par2IPosition) {
/* 20 */     EntityArrow var3 = new EntityArrow(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ(), this.item_arrow, false);
/* 21 */     var3.canBePickedUp = 1;
/* 22 */     var3.shot_by_dispenser = true;
/* 23 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */