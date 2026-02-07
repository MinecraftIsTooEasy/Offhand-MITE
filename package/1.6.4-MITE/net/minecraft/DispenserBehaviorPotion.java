/*    */ package net.minecraft;
/*    */ 
/*    */ final class DispenserBehaviorPotion
/*    */   implements IBehaviorDispenseItem {
/*  5 */   private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack dispense(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/* 12 */     return ItemPotion.isSplash(par2ItemStack.getItemSubtype()) ? (new DispenserBehaviorPotionProjectile(this, par2ItemStack)).dispense(par1IBlockSource, par2ItemStack) : this.defaultDispenserItemBehavior.dispense(par1IBlockSource, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorPotion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */