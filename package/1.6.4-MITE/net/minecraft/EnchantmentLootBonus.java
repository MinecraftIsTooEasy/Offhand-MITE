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
/*    */ public class EnchantmentLootBonus
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentLootBonus(int id, EnumRarity rarity, int difficulty) {
/* 18 */     super(id, rarity, difficulty);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumLevels() {
/* 23 */     return 3;
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
/* 52 */     if (this == looting)
/* 53 */       return "lootBonus"; 
/* 54 */     if (this == fortune) {
/* 55 */       return "lootBonusDigger";
/*    */     }
/* 57 */     Minecraft.setErrorMessage("getName: no handler for " + this);
/* 58 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canApplyTogether(Enchantment par1Enchantment) {
/* 66 */     return (super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != silkTouch.effectId);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchantItem(Item item) {
/* 71 */     if (this == looting)
/* 72 */       return (item instanceof ItemCudgel || item instanceof ItemSword); 
/* 73 */     if (this == fortune) {
/* 74 */       return (item.getClass() == ItemPickaxe.class || item.getClass() == ItemShovel.class);
/*    */     }
/* 76 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 81 */     if (this == looting) {
/* 82 */       return (creative_tab == CreativeTabs.tabCombat);
/*    */     }
/* 84 */     return (creative_tab == CreativeTabs.tabTools);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentLootBonus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */