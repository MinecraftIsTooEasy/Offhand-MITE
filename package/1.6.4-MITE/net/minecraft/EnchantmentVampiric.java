/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentVampiric
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentVampiric(int id, EnumRarity rarity, int difficulty) {
/*  9 */     super(id, rarity, difficulty);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameSuffix() {
/* 14 */     return "vampiric";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchantItem(Item item) {
/* 19 */     if (item instanceof ItemTool) {
/*    */       
/* 21 */       Material material = ((ItemTool)item).getToolMaterial();
/*    */       
/* 23 */       if (material == Material.silver || material == Material.mithril) {
/* 24 */         return false;
/*    */       }
/*    */     } 
/* 27 */     return (item instanceof ItemSword || item instanceof ItemScythe);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 32 */     return (creative_tab == CreativeTabs.tabCombat);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentVampiric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */