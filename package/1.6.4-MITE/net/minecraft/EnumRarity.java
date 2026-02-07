/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumRarity
/*    */ {
/* 12 */   common(15, "Common", 100),
/* 13 */   uncommon(14, "Uncommon", 25),
/* 14 */   rare(11, "Rare", 5),
/* 15 */   epic(13, "Epic", 1);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final int rarityColor;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final String rarityName;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final int standard_weight;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   EnumRarity(int color_index, String name, int standard_weight) {
/* 38 */     this.rarityColor = color_index;
/* 39 */     this.rarityName = name;
/* 40 */     this.standard_weight = standard_weight;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumRarity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */