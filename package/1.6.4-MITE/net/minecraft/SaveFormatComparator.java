/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SaveFormatComparator
/*     */   implements Comparable
/*     */ {
/*     */   private final String fileName;
/*     */   private final String displayName;
/*     */   private final long lastTimePlayed;
/*     */   private final long sizeOnDisk;
/*     */   private final boolean requiresConversion;
/*     */   private final EnumGameType theEnumGameType;
/*     */   private final boolean hardcore;
/*     */   private final boolean are_skills_enabled;
/*     */   private final boolean cheatsEnabled;
/*     */   public final boolean passed_validation;
/*     */   public final String failed_validation_reason;
/*     */   
/*     */   public SaveFormatComparator(String par1Str, String par2Str, long par3, long par5, EnumGameType par7EnumGameType, boolean par8, boolean par9, boolean par10, boolean are_skills_enabled, boolean passed_validation, String failed_validation_reason) {
/*  26 */     this.fileName = par1Str;
/*  27 */     this.displayName = par2Str;
/*  28 */     this.lastTimePlayed = par3;
/*  29 */     this.sizeOnDisk = par5;
/*  30 */     this.theEnumGameType = par7EnumGameType;
/*  31 */     this.requiresConversion = par8;
/*  32 */     this.hardcore = par9;
/*  33 */     this.cheatsEnabled = par10;
/*     */     
/*  35 */     this.are_skills_enabled = are_skills_enabled;
/*     */     
/*  37 */     this.passed_validation = passed_validation;
/*  38 */     this.failed_validation_reason = failed_validation_reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileName() {
/*  46 */     return this.fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  54 */     return this.displayName;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresConversion() {
/*  59 */     return this.requiresConversion;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastTimePlayed() {
/*  64 */     return this.lastTimePlayed;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(SaveFormatComparator par1SaveFormatComparator) {
/*  69 */     return (this.lastTimePlayed < par1SaveFormatComparator.lastTimePlayed) ? 1 : ((this.lastTimePlayed > par1SaveFormatComparator.lastTimePlayed) ? -1 : this.fileName.compareTo(par1SaveFormatComparator.fileName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumGameType getEnumGameType() {
/*  77 */     return this.theEnumGameType;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHardcoreModeEnabled() {
/*  82 */     return this.hardcore;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areSkillsEnabled() {
/*  87 */     return this.are_skills_enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCheatsEnabled() {
/*  95 */     return this.cheatsEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object par1Obj) {
/* 100 */     return compareTo((SaveFormatComparator)par1Obj);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SaveFormatComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */