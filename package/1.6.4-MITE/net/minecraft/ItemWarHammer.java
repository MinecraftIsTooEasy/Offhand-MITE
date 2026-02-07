/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemWarHammer
/*    */   extends ItemPickaxe
/*    */ {
/*    */   protected ItemWarHammer(int par1, Material material) {
/*  9 */     super(par1, material);
/*    */     
/* 11 */     addMaterialsEffectiveAgainst(new Material[] { Material.cake, Material.pumpkin });
/*    */ 
/*    */     
/* 14 */     setCreativeTab(CreativeTabs.tabCombat);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 19 */     return "war_hammer";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseHarvestEfficiency(Block block) {
/* 24 */     return super.getBaseHarvestEfficiency(block) * 0.75F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 29 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 34 */     return super.getBaseDecayRateForBreakingBlock(block) * 2.0F / 3.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 39 */     return super.getBaseDecayRateForAttackingEntity(item_stack) * 2.0F / 3.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class[] getSimilarClasses() {
/* 44 */     return spliceClassArrays(new Class[] { ItemPickaxe.class }, ItemTool.weapon_classes);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemWarHammer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */