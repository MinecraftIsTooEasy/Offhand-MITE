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
/*    */ public class ItemAxe
/*    */   extends ItemTool
/*    */ {
/*    */   protected ItemAxe(int par1, Material material) {
/* 24 */     super(par1, material);
/*    */     
/* 26 */     addMaterialsEffectiveAgainst(new Material[] { Material.cactus, Material.clay, Material.glass, Material.hardened_clay, Material.ice, Material.pumpkin, Material.wood });
/*    */     
/* 28 */     addBlocksEffectiveAgainst(new Block[] { Block.ladder, Block.reed, Block.sandStone });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 33 */     return "axe";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseHarvestEfficiency(Block block) {
/* 38 */     return (block == Block.sandStone) ? (super.getBaseHarvestEfficiency(block) * 0.5F) : super.getBaseHarvestEfficiency(block);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 43 */     return 3.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 48 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 53 */     if (block == Block.sandStone) {
/* 54 */       return 1.875F;
/*    */     }
/* 56 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 61 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class[] getSimilarClasses() {
/* 66 */     return spliceClassArrays(new Class[] { ItemBattleAxe.class, ItemHatchet.class }, ItemTool.weapon_classes);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemAxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */