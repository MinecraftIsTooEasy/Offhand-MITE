/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSword
/*    */   extends ItemTool
/*    */ {
/*    */   protected ItemSword(int par1, Material material) {
/*  9 */     super(par1, material);
/*    */     
/* 11 */     addMaterialsEffectiveAgainst(new Material[] { Material.materialCarpet, Material.cloth, Material.tree_leaves, Material.plants, Material.pumpkin, Material.vine, Material.web });
/*    */ 
/*    */     
/* 14 */     setCreativeTab(CreativeTabs.tabCombat);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 19 */     return "sword";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseHarvestEfficiency(Block block) {
/* 24 */     return 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 29 */     return 4.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 34 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 39 */     return 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 44 */     return 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Class[] getSimilarClasses() {
/* 54 */     return ItemTool.weapon_classes;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */