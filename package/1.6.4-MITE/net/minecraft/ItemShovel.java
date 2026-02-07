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
/*    */ public class ItemShovel
/*    */   extends ItemTool
/*    */ {
/*    */   protected ItemShovel(int par1, Material material) {
/* 24 */     super(par1, material);
/*    */     
/* 26 */     addMaterialsEffectiveAgainst(new Material[] { Material.cake, Material.clay, Material.craftedSnow, Material.grass, Material.dirt, Material.sand, Material.snow });
/* 27 */     addBlocksEffectiveAgainst(new Block[] { Block.carrot, Block.onions, Block.potato, Block.thinGlass });
/*    */     
/* 29 */     if (material.isMetal()) {
/* 30 */       addBlocksEffectiveAgainst(new Block[] { Block.glass });
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 37 */     return "shovel";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 42 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 47 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 52 */     return 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 57 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class[] getSimilarClasses() {
/* 62 */     return new Class[] { ItemMattock.class, ItemHoe.class };
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemShovel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */