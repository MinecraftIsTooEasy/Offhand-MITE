/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentPiercing
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentPiercing(int id, EnumRarity rarity, int difficulty) {
/*  9 */     super(id, rarity, difficulty);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTranslatedName(Item item) {
/* 14 */     if (item instanceof ItemAxe) {
/* 15 */       return "Cleaving";
/*    */     }
/* 17 */     return super.getTranslatedName(item);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameSuffix() {
/* 22 */     return "piercing";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchantItem(Item item) {
/* 27 */     return (item.getClass() == ItemPickaxe.class || item.getClass() == ItemBattleAxe.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 32 */     return (creative_tab == CreativeTabs.tabCombat);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentPiercing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */