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
/*     */ public final class WorldSettings
/*     */ {
/*     */   private final long seed;
/*     */   private final EnumGameType theGameType;
/*     */   private final boolean mapFeaturesEnabled;
/*     */   private final boolean hardcoreEnabled;
/*     */   private final WorldType terrainType;
/*     */   private boolean commandsAllowed;
/*     */   private boolean bonusChestEnabled;
/*     */   private String field_82751_h;
/*     */   private boolean are_skills_enabled;
/*     */   
/*     */   public WorldSettings(long par1, EnumGameType par3EnumGameType, boolean par4, boolean par5, WorldType par6WorldType, boolean are_skills_enabled) {
/*  32 */     this.field_82751_h = "";
/*  33 */     this.seed = par1;
/*  34 */     this.theGameType = par3EnumGameType;
/*  35 */     this.mapFeaturesEnabled = par4;
/*  36 */     this.hardcoreEnabled = par5;
/*  37 */     this.terrainType = par6WorldType;
/*     */     
/*  39 */     this.are_skills_enabled = are_skills_enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldSettings(WorldInfo par1WorldInfo) {
/*  45 */     this(par1WorldInfo.getSeed(), par1WorldInfo.getGameType(), par1WorldInfo.isMapFeaturesEnabled(), par1WorldInfo.isHardcoreModeEnabled(), par1WorldInfo.getTerrainType(), par1WorldInfo.areSkillsEnabled());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldSettings enableBonusChest() {
/*  54 */     this.bonusChestEnabled = false;
/*  55 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldSettings enableCommands() {
/*  65 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldSettings func_82750_a(String par1Str) {
/*  70 */     this.field_82751_h = par1Str;
/*  71 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBonusChestEnabled() {
/*  79 */     return this.bonusChestEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  87 */     return this.seed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumGameType getGameType() {
/*  95 */     return this.theGameType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHardcoreEnabled() {
/* 103 */     return this.hardcoreEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMapFeaturesEnabled() {
/* 111 */     return this.mapFeaturesEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldType getTerrainType() {
/* 116 */     return this.terrainType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areCommandsAllowed() {
/* 125 */     return Minecraft.inDevMode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumGameType getGameTypeById(int par0) {
/* 133 */     return EnumGameType.getByID(par0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_82749_j() {
/* 138 */     return this.field_82751_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areSkillsEnabled() {
/* 143 */     return this.are_skills_enabled;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */