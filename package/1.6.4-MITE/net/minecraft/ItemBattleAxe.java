/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBattleAxe
/*    */   extends ItemAxe
/*    */ {
/*    */   protected ItemBattleAxe(int par1, Material material) {
/*  9 */     super(par1, material);
/*    */     
/* 11 */     setCreativeTab(CreativeTabs.tabCombat);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 16 */     return "battle_axe";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseHarvestEfficiency(Block block) {
/* 21 */     return super.getBaseHarvestEfficiency(block) * 0.75F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 26 */     return super.getBaseDamageVsEntity() + 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 31 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 36 */     return super.getBaseDecayRateForBreakingBlock(block) * 1.25F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 41 */     return super.getBaseDecayRateForAttackingEntity(item_stack) * 0.75F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class[] getSimilarClasses() {
/* 46 */     return spliceClassArrays(ItemTool.weapon_classes, new Class[] { ItemAxe.class, ItemHatchet.class });
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBattleAxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */