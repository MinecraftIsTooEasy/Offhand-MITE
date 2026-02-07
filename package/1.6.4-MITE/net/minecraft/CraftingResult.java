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
/*     */ public class CraftingResult
/*     */ {
/*     */   public ItemStack item_stack;
/*     */   private final float difficulty;
/*     */   public boolean is_experience_cost_exempt;
/*     */   public EnumQuality quality_override;
/*     */   public final int[] applicable_skillsets;
/*     */   public final IRecipe recipe;
/*  19 */   public int consumption = 1;
/*     */ 
/*     */   
/*     */   private boolean is_repair;
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftingResult(ItemStack item_stack, float difficulty, int[] applicable_skillsets, IRecipe recipe) {
/*  27 */     this.item_stack = item_stack;
/*  28 */     this.difficulty = difficulty;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     this.applicable_skillsets = applicable_skillsets;
/*  34 */     this.recipe = recipe;
/*     */   }
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
/*     */   public static boolean haveEquivalentItemStacks(CraftingResult first, CraftingResult second) {
/*  51 */     return ItemStack.areItemStacksEqual((first == null) ? null : first.item_stack, (second == null) ? null : second.item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getUnmodifiedDifficulty() {
/*  56 */     return this.difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getQualityAdjustedDifficulty(EnumQuality quality) {
/*  61 */     if (this.quality_override != null) {
/*  62 */       quality = this.quality_override;
/*     */     }
/*  64 */     return getQualityAdjustedDifficulty(this.difficulty, quality);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getQualityAdjustedDifficulty(float difficulty, EnumQuality quality) {
/*  69 */     if (quality == null)
/*     */     {
/*     */       
/*  72 */       return difficulty;
/*     */     }
/*     */     
/*  75 */     int quality_levels_above_average = quality.ordinal() - EnumQuality.average.ordinal();
/*     */     
/*  77 */     float modified_difficulty = difficulty;
/*     */     
/*  79 */     for (int i = 0; i < quality_levels_above_average; i++) {
/*  80 */       modified_difficulty *= 2.0F;
/*     */     }
/*  82 */     return modified_difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftingResult setExperienceCostExempt() {
/*  87 */     this.is_experience_cost_exempt = true;
/*  88 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftingResult setQualityOverride(EnumQuality quality_override) {
/*  93 */     this.quality_override = quality_override;
/*  94 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftingResult setConsumption(int consumption) {
/*  99 */     this.consumption = consumption;
/* 100 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftingResult setRepair() {
/* 105 */     this.is_repair = true;
/* 106 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepair() {
/* 111 */     return this.is_repair;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CraftingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */