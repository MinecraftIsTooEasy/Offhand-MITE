/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentTreeFelling
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentTreeFelling(int id, EnumRarity rarity, int difficulty) {
/*  9 */     super(id, rarity, difficulty);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameSuffix() {
/* 14 */     return "tree_felling";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchantItem(Item item) {
/* 19 */     return (item instanceof ItemAxe && !(item instanceof ItemHatchet));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 24 */     return (creative_tab == CreativeTabs.tabTools);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentTreeFelling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */