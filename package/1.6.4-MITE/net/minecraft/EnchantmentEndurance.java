/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentEndurance
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentEndurance(int id, EnumRarity rarity, int difficulty) {
/*  9 */     super(id, rarity, difficulty);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumLevels() {
/* 14 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameSuffix() {
/* 19 */     return "endurance";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchantItem(Item item) {
/* 24 */     return item instanceof ItemCuirass;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 29 */     return (creative_tab == CreativeTabs.tabCombat);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentEndurance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */