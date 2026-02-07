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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPickaxe
/*    */   extends ItemTool
/*    */ {
/*    */   protected ItemPickaxe(int par1, Material material) {
/* 29 */     super(par1, material);
/*    */     
/* 31 */     addMaterialsEffectiveAgainst(new Material[] { Material.adamantium, Material.ancient_metal, Material.circuits, Material.clay, Material.coal, Material.copper, Material.coral, Material.diamond, Material.emerald, Material.glass, Material.gold, Material.hardened_clay, Material.ice, Material.iron, Material.mithril, Material.netherrack, Material.obsidian, Material.quartz, Material.redstone, Material.stone, Material.rusted_iron, Material.silver });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 37 */     return "pickaxe";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 42 */     return 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 47 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 52 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 57 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class[] getSimilarClasses() {
/* 62 */     return spliceClassArrays(new Class[] { ItemWarHammer.class }, ItemTool.weapon_classes);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemPickaxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */