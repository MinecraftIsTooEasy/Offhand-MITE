/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentButchering
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentButchering(int id, EnumRarity rarity, int difficulty) {
/*  9 */     super(id, rarity, difficulty);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumLevels() {
/* 14 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameSuffix() {
/* 19 */     return "butchering";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchantItem(Item item) {
/* 24 */     return (item.getClass() == ItemKnife.class || item.getClass() == ItemDagger.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 29 */     return (creative_tab == CreativeTabs.tabTools);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentButchering.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */