/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumArmorMaterial
/*     */ {
/*  67 */   CLOTH(5, new int[] { 1, 3, 2, 1 }, 15),
/*     */   
/*  69 */   CHAIN(15, new int[] { 2, 5, 4, 1 }, 12),
/*     */   
/*  71 */   IRON(15, new int[] { 2, 6, 5, 2 }, 9),
/*     */   
/*  73 */   GOLD(7, new int[] { 2, 5, 3, 1 }, 25),
/*     */   
/*  75 */   DIAMOND(33, new int[] { 3, 8, 6, 3 }, 10);
/*     */   
/*     */   private int maxDamageFactor;
/*     */   
/*     */   private int[] damageReductionAmountArray;
/*     */   
/*     */   private int enchantability;
/*     */   
/*     */   EnumArmorMaterial(int j, int[] is, int k) {
/*  84 */     this.maxDamageFactor = j;
/*  85 */     this.damageReductionAmountArray = is;
/*  86 */     this.enchantability = k;
/*     */   }
/*     */   
/*     */   public int getDurability(int i) {
/*  90 */     return ItemArmor.e()[i] * this.maxDamageFactor;
/*     */   }
/*     */   
/*     */   public int getDamageReductionAmount(int i) {
/*  94 */     return this.damageReductionAmountArray[i];
/*     */   }
/*     */   
/*     */   public int getEnchantability() {
/*  98 */     return this.enchantability;
/*     */   }
/*     */   
/*     */   public int getArmorCraftingMaterial() {
/* 102 */     if (this == CLOTH)
/* 103 */       return Item.leather.itemID; 
/* 104 */     if (this == CHAIN)
/* 105 */       return Item.ingotIron.itemID; 
/* 106 */     if (this == GOLD)
/* 107 */       return Item.ingotGold.itemID; 
/* 108 */     if (this == IRON)
/* 109 */       return Item.ingotIron.itemID; 
/* 110 */     if (this == DIAMOND) {
/* 111 */       return Item.diamond.itemID;
/*     */     }
/* 113 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumArmorMaterial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */