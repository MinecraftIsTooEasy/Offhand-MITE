/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentUntouching
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentUntouching(int id, EnumRarity rarity, int difficulty) {
/* 13 */     super(id, rarity, difficulty);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumLevels() {
/* 18 */     return 1;
/*    */   }
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
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNameSuffix() {
/* 47 */     return "untouching";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canApplyTogether(Enchantment par1Enchantment) {
/* 55 */     return (super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != fortune.effectId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canEnchantItem(Item item) {
/* 66 */     return (item.getClass() == ItemPickaxe.class || item.getClass() == ItemShovel.class || item instanceof ItemShears || item.getClass() == ItemKnife.class || item.getClass() == ItemDagger.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 71 */     return (creative_tab == CreativeTabs.tabTools);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentUntouching.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */