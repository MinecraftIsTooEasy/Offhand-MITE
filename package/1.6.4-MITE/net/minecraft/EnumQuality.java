/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumQuality
/*     */ {
/*   9 */   wretched("wretched", "Wretched Quality", 0.5F),
/*  10 */   poor("poor", "Poor Quality", 0.75F),
/*  11 */   average("average", "Average Quality", 1.0F),
/*  12 */   fine("fine", "Fine Quality", 1.5F),
/*  13 */   excellent("excellent", "Excellent Quality", 2.0F),
/*  14 */   superb("superb", "Superb Quality", 2.5F),
/*  15 */   masterwork("masterwork", "Masterwork", 3.0F),
/*  16 */   legendary("legendary", "Legendary", 3.5F);
/*     */   
/*     */   private final String unlocalized_name;
/*     */   
/*     */   private final float durability_modifier;
/*     */ 
/*     */   
/*     */   EnumQuality(String unlocalized_name, String descriptor, float durability_modifier) {
/*  24 */     this.unlocalized_name = unlocalized_name;
/*     */     
/*  26 */     this.durability_modifier = durability_modifier;
/*     */   }
/*     */ 
/*     */   
/*     */   static EnumQuality get(int ordinal) {
/*  31 */     return values()[ordinal];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescriptor() {
/*  37 */     return Translator.get("quality." + this.unlocalized_name);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDurabilityModifier() {
/*  42 */     return this.durability_modifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAverage() {
/*  47 */     return (this == average);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLowerThan(EnumQuality quality) {
/*  52 */     return (ordinal() < quality.ordinal());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHigherThan(EnumQuality quality) {
/*  57 */     return (ordinal() > quality.ordinal());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAverageOrLower() {
/*  62 */     return (ordinal() <= average.ordinal());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAverageOrHigher() {
/*  67 */     return (ordinal() >= average.ordinal());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumQuality getHighestQuality() {
/*  73 */     return get((values()).length - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumQuality getLowest(EnumQuality first, EnumQuality second) {
/*  78 */     return first.isLowerThan(second) ? first : second;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumQuality getLowest(List<EnumQuality> list) {
/*  83 */     if (list.size() == 0) {
/*  84 */       return null;
/*     */     }
/*  86 */     EnumQuality lowest_quality = list.get(0);
/*     */     
/*  88 */     for (int i = 1; i < list.size(); i++) {
/*  89 */       lowest_quality = getLowest(list.get(i), lowest_quality);
/*     */     }
/*  91 */     return lowest_quality;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumQuality getHighest(EnumQuality first, EnumQuality second) {
/*  96 */     return first.isHigherThan(second) ? first : second;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumQuality getHighest(List<EnumQuality> list) {
/* 101 */     if (list.size() == 0) {
/* 102 */       return null;
/*     */     }
/* 104 */     EnumQuality highest_quality = list.get(0);
/*     */     
/* 106 */     for (int i = 1; i < list.size(); i++) {
/* 107 */       highest_quality = getHighest(list.get(i), highest_quality);
/*     */     }
/* 109 */     return highest_quality;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumQuality getNextLower() {
/* 114 */     int ordinal = ordinal() - 1;
/*     */     
/* 116 */     if (ordinal < 0) {
/*     */       
/* 118 */       Minecraft.setErrorMessage("getNextLower: quality is already the lowest");
/* 119 */       ordinal = 0;
/*     */     } 
/*     */     
/* 122 */     return get(ordinal);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 128 */     return getDescriptor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName() {
/* 133 */     return this.unlocalized_name;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumQuality.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */