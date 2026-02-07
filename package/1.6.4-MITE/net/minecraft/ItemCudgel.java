/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCudgel
/*    */   extends ItemTool
/*    */ {
/*    */   protected ItemCudgel(int par1, Material material) {
/*  9 */     super(par1, material);
/*    */     
/* 11 */     addMaterialsEffectiveAgainst(new Material[] { Material.cake, Material.coral, Material.glass, Material.ice, Material.pumpkin });
/*    */     
/* 13 */     setReachBonus(0.25F);
/*    */     
/* 15 */     setCreativeTab(CreativeTabs.tabCombat);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 20 */     return "cudgel";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseHarvestEfficiency(Block block) {
/* 25 */     return 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 30 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBlock() {
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 40 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 45 */     return 0.25F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 50 */     return 0.25F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSimilarityToItem(Item item) {
/* 55 */     return (item == Item.stick || item == Item.bone) ? 1 : super.getSimilarityToItem(item);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemCudgel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */