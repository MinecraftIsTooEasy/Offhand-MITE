/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumInsulinResistanceLevel
/*     */ {
/*   7 */   mild("mild", EnumChatFormatting.YELLOW, 48000, true),
/*   8 */   moderate("moderate", EnumChatFormatting.GOLD, 96000, false),
/*   9 */   severe("severe", EnumChatFormatting.RED, 144000, false);
/*     */   
/*     */   private final String unlocalized_name;
/*     */   
/*     */   private final EnumChatFormatting color;
/*     */   private final int threshold;
/*     */   private final boolean can_metabolize_food_sugars;
/*     */   
/*     */   EnumInsulinResistanceLevel(String unlocalized_name, EnumChatFormatting color, int threshold, boolean can_metabolize_food_sugars) {
/*  18 */     this.unlocalized_name = unlocalized_name;
/*  19 */     this.color = color;
/*  20 */     this.threshold = threshold;
/*  21 */     this.can_metabolize_food_sugars = can_metabolize_food_sugars;
/*     */   }
/*     */ 
/*     */   
/*     */   static EnumInsulinResistanceLevel get(int ordinal) {
/*  26 */     return values()[ordinal];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int getOrdinalForTransmission() {
/*  32 */     return ordinal() + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static int getOrdinalForTransmission(EnumInsulinResistanceLevel insulin_resistance_level) {
/*  38 */     return (insulin_resistance_level == null) ? 0 : insulin_resistance_level.getOrdinalForTransmission();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static EnumInsulinResistanceLevel getByTransmittedOrdinal(int transmitted_ordinal) {
/*  44 */     return (transmitted_ordinal < 1) ? null : values()[transmitted_ordinal - 1];
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isMild() {
/*  49 */     return (this == mild);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isModerate() {
/*  54 */     return (this == moderate);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isSevere() {
/*  59 */     return (this == severe);
/*     */   }
/*     */ 
/*     */   
/*     */   String getUnlocalizedName() {
/*  64 */     return this.unlocalized_name;
/*     */   }
/*     */ 
/*     */   
/*     */   EnumChatFormatting getColor() {
/*  69 */     return this.color;
/*     */   }
/*     */ 
/*     */   
/*     */   float getRedAsFloat() {
/*  74 */     return this.color.getRedAsFloat();
/*     */   }
/*     */ 
/*     */   
/*     */   float getGreenAsFloat() {
/*  79 */     return this.color.getGreenAsFloat();
/*     */   }
/*     */ 
/*     */   
/*     */   float getBlueAsFloat() {
/*  84 */     return this.color.getBlueAsFloat();
/*     */   }
/*     */ 
/*     */   
/*     */   int getThreshold() {
/*  89 */     return this.threshold;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isLessSevereThan(EnumInsulinResistanceLevel insulin_resistance_level) {
/*  94 */     return (insulin_resistance_level != null && insulin_resistance_level.threshold > this.threshold);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isMoreSevereThan(EnumInsulinResistanceLevel insulin_resistance_level) {
/*  99 */     return (insulin_resistance_level == null || insulin_resistance_level.threshold < this.threshold);
/*     */   }
/*     */ 
/*     */   
/*     */   EnumInsulinResistanceLevel getNext() {
/* 104 */     return values()[ordinal() + 1];
/*     */   }
/*     */ 
/*     */   
/*     */   static EnumInsulinResistanceLevel getInsulinResistanceLevel(int insulin_resistance) {
/* 109 */     for (int i = (values()).length - 1; i >= 0; i--) {
/*     */       
/* 111 */       if (insulin_resistance >= (values()[i]).threshold) {
/* 112 */         return values()[i];
/*     */       }
/*     */     } 
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean canMetabolizeFoodSugars() {
/* 120 */     return this.can_metabolize_food_sugars;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumInsulinResistanceLevel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */