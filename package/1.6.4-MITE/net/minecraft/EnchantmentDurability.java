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
/*    */ public class EnchantmentDurability
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentDurability(int id, EnumRarity rarity, int difficulty) {
/* 15 */     super(id, rarity, difficulty);
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
/* 44 */     return "durability";
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
/*    */   public boolean canEnchantItem(Item item) {
/* 65 */     if (item instanceof ItemArmor) {
/*    */       
/* 67 */       ItemArmor item_armor = (ItemArmor)item;
/*    */       
/* 69 */       return item_armor.isSolidMetal();
/*    */     } 
/*    */ 
/*    */     
/* 73 */     return (item instanceof ItemCudgel || item instanceof ItemPickaxe || item instanceof ItemShovel || item instanceof ItemHoe || item instanceof ItemAxe || item instanceof ItemFishingRod || item instanceof ItemBow);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 79 */     return (creative_tab == CreativeTabs.tabTools || creative_tab == CreativeTabs.tabCombat);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentDurability.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */