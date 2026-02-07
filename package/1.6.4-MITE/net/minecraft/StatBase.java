/*     */ package net.minecraft;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StatBase
/*     */ {
/*     */   public final int statId;
/*     */   private final String statName;
/*     */   public boolean isIndependent;
/*     */   public String statGuid;
/*     */   private final IStatType type;
/*  18 */   private static NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.US);
/*  19 */   public static IStatType simpleStatType = new StatTypeSimple();
/*  20 */   private static DecimalFormat decimalFormat = new DecimalFormat("########0.00");
/*  21 */   public static IStatType timeStatType = new StatTypeTime();
/*  22 */   public static IStatType distanceStatType = new StatTypeDistance();
/*  23 */   public static IStatType field_111202_k = new StatTypeFloat();
/*     */ 
/*     */   
/*     */   public StatBase(int par1, String par2Str, IStatType par3IStatType) {
/*  27 */     this.statId = par1;
/*  28 */     this.statName = par2Str;
/*  29 */     this.type = par3IStatType;
/*     */   }
/*     */ 
/*     */   
/*     */   public StatBase(int par1, String par2Str) {
/*  34 */     this(par1, par2Str, simpleStatType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StatBase initIndependentStat() {
/*  43 */     this.isIndependent = true;
/*  44 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StatBase registerStat() {
/*  52 */     if (StatList.oneShotStats.containsKey(Integer.valueOf(this.statId)))
/*     */     {
/*  54 */       throw new RuntimeException("Duplicate stat id: \"" + ((StatBase)StatList.oneShotStats.get(Integer.valueOf(this.statId))).statName + "\" and \"" + this.statName + "\" at id " + this.statId);
/*     */     }
/*     */ 
/*     */     
/*  58 */     StatList.allStats.add(this);
/*  59 */     StatList.oneShotStats.put(Integer.valueOf(this.statId), this);
/*  60 */     this.statGuid = AchievementMap.getGuid(this.statId);
/*  61 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAchievement() {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String func_75968_a(long par1) {
/*  80 */     return this.type.format(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  85 */     return this.statName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  90 */     return StatCollector.translateToLocal(this.statName);
/*     */   }
/*     */ 
/*     */   
/*     */   static NumberFormat getNumberFormat() {
/*  95 */     return numberFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   static DecimalFormat getDecimalFormat() {
/* 100 */     return decimalFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public IStatType getType() {
/* 105 */     return this.type;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */