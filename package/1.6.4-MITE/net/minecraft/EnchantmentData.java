/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentData
/*    */   extends WeightedRandomItem
/*    */ {
/*    */   public final Enchantment enchantmentobj;
/*    */   public int enchantmentLevel;
/*    */   
/*    */   public EnchantmentData(Enchantment par1Enchantment, int par2) {
/* 14 */     super(par1Enchantment.getWeight());
/* 15 */     this.enchantmentobj = par1Enchantment;
/* 16 */     this.enchantmentLevel = par2;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnchantmentData(int par1, int par2) {
/* 21 */     this(Enchantment.enchantmentsList[par1], par2);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */