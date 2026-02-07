/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemHatchet
/*    */   extends ItemAxe
/*    */ {
/*    */   protected ItemHatchet(int par1, Material material) {
/*  9 */     super(par1, material);
/*    */     
/* 11 */     setReachBonus(0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 16 */     return "hatchet";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseHarvestEfficiency(Block block) {
/* 21 */     return super.getBaseHarvestEfficiency(block) * 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 26 */     return super.getBaseDamageVsEntity() - 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBlock() {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 36 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 41 */     return super.getBaseDecayRateForBreakingBlock(block) * 4.0F / 3.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 46 */     return super.getBaseDecayRateForAttackingEntity(item_stack) * 4.0F / 3.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class[] getSimilarClasses() {
/* 51 */     return new Class[] { ItemAxe.class, ItemBattleAxe.class };
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemHatchet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */