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
/*     */ class GameRuleValue
/*     */ {
/*     */   private String valueString;
/*     */   private boolean valueBoolean;
/*     */   private int valueInteger;
/*     */   private double valueDouble;
/*     */   
/*     */   public GameRuleValue(String string) {
/* 117 */     setValue(string);
/*     */   }
/*     */   
/*     */   public void setValue(String string) {
/* 121 */     this.valueString = string;
/* 122 */     this.valueBoolean = Boolean.parseBoolean(string);
/*     */     try {
/* 124 */       this.valueInteger = Integer.parseInt(string);
/* 125 */     } catch (NumberFormatException numberFormatException) {}
/*     */     
/*     */     try {
/* 128 */       this.valueDouble = Double.parseDouble(string);
/* 129 */     } catch (NumberFormatException numberFormatException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGameRuleStringValue() {
/* 134 */     return this.valueString;
/*     */   }
/*     */   
/*     */   public boolean getGameRuleBooleanValue() {
/* 138 */     return this.valueBoolean;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GameRuleValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */