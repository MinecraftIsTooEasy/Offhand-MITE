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
/*    */ public enum EnumToolMaterial
/*    */ {
/* 30 */   WOOD(0, 59, 2.0F, 0.0F, 15),
/*    */   
/* 32 */   STONE(1, 131, 4.0F, 1.0F, 5),
/*    */   
/* 34 */   IRON(2, 250, 6.0F, 2.0F, 14),
/*    */   
/* 36 */   EMERALD(3, 1561, 8.0F, 3.0F, 10),
/*    */   
/* 38 */   GOLD(0, 32, 12.0F, 0.0F, 22);
/*    */   
/*    */   private final int harvestLevel;
/*    */   private final int maxUses;
/*    */   private final float efficiencyOnProperMaterial;
/*    */   private final float damageVsEntity;
/*    */   private final int enchantability;
/*    */   
/*    */   EnumToolMaterial(int j, int k, float f, float g, int l) {
/* 47 */     this.harvestLevel = j;
/* 48 */     this.maxUses = k;
/* 49 */     this.efficiencyOnProperMaterial = f;
/* 50 */     this.damageVsEntity = g;
/* 51 */     this.enchantability = l;
/*    */   }
/*    */   
/*    */   public int getMaxUses() {
/* 55 */     return this.maxUses;
/*    */   }
/*    */   
/*    */   public float getEfficiencyOnProperMaterial() {
/* 59 */     return this.efficiencyOnProperMaterial;
/*    */   }
/*    */   
/*    */   public float getDamageVsEntity() {
/* 63 */     return this.damageVsEntity;
/*    */   }
/*    */   
/*    */   public int getHarvestLevel() {
/* 67 */     return this.harvestLevel;
/*    */   }
/*    */   
/*    */   public int getEnchantability() {
/* 71 */     return this.enchantability;
/*    */   }
/*    */   
/*    */   public int getToolCraftingMaterial() {
/* 75 */     if (this == WOOD)
/* 76 */       return Block.C.blockID; 
/* 77 */     if (this == STONE)
/* 78 */       return Block.cobblestone.blockID; 
/* 79 */     if (this == GOLD)
/* 80 */       return Item.ingotGold.itemID; 
/* 81 */     if (this == IRON)
/* 82 */       return Item.ingotIron.itemID; 
/* 83 */     if (this == EMERALD) {
/* 84 */       return Item.diamond.itemID;
/*    */     }
/* 86 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumToolMaterial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */