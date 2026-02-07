/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemKnife
/*    */   extends ItemDagger
/*    */ {
/*    */   protected ItemKnife(int par1, Material material) {
/*  9 */     super(par1, material);
/*    */     
/* 11 */     setReachBonus(0.25F);
/*    */     
/* 13 */     setCreativeTab(CreativeTabs.tabTools);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 18 */     return "knife";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 23 */     return super.getBaseDamageVsEntity() - 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 28 */     if (block != null)
/*    */     {
/* 30 */       if (block.blockMaterial == Material.cloth || block.blockMaterial == Material.plants || block.blockMaterial == Material.vine) {
/* 31 */         return super.getBaseDecayRateForBreakingBlock(block) / 4.0F;
/*    */       }
/*    */     }
/* 34 */     return super.getBaseDecayRateForBreakingBlock(block) / 2.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemKnife.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */