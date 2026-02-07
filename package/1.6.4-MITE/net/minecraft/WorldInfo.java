/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
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
/*     */ public final class WorldInfo
/*     */ {
/*     */   public static final int village_condition_wheat = 1;
/*     */   public static final int village_condition_carrot = 2;
/*     */   public static final int village_condition_potato = 4;
/*     */   public static final int village_condition_onion = 8;
/*     */   public static final int village_condition_iron_pickaxe_or_warhammer = 16;
/*     */   private WorldInfoShared shared;
/*     */   private int dimension_id;
/*     */   
/*     */   protected WorldInfo() {
/*  31 */     this.shared = new WorldInfoShared();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldInfo(NBTTagCompound par1NBTTagCompound) {
/*  36 */     NBTBase.loading_world_info = true;
/*     */     
/*  38 */     this.shared = new WorldInfoShared(par1NBTTagCompound);
/*     */ 
/*     */ 
/*     */     
/*  42 */     NBTBase.loading_world_info = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldInfo(WorldSettings par1WorldSettings, String par2Str) {
/*  47 */     this.shared = new WorldInfoShared(par1WorldSettings, par2Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldInfo(WorldInfo world_info, int dimension_id) {
/*  58 */     this.shared = world_info.shared;
/*  59 */     this.dimension_id = dimension_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getNBTTagCompound() {
/*  67 */     NBTTagCompound var1 = new NBTTagCompound();
/*  68 */     updateTagCompound(var1, this.shared.playerTag);
/*  69 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound cloneNBTCompound(NBTTagCompound par1NBTTagCompound) {
/*  77 */     NBTTagCompound var2 = new NBTTagCompound();
/*  78 */     updateTagCompound(var2, par1NBTTagCompound);
/*  79 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateTagCompound(NBTTagCompound par1NBTTagCompound, NBTTagCompound par2NBTTagCompound) {
/*  84 */     this.shared.updateTagCompound(par1NBTTagCompound, par2NBTTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  94 */     return this.shared.randomSeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getHashedSeed() {
/*  99 */     return this.shared.random_seed_hashed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpawnX() {
/* 107 */     return this.shared.spawnX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpawnY() {
/* 115 */     return this.shared.spawnY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpawnZ() {
/* 123 */     return this.shared.spawnZ;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getWorldTotalTime(int dimension_id) {
/* 145 */     return this.shared.getTotalWorldTime(dimension_id);
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
/*     */ 
/*     */   
/*     */   public int getWorldTimeOfDay(int dimension_id) {
/* 164 */     return (int)(getWorldTotalTime(dimension_id) % 24000L);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSizeOnDisk() {
/* 169 */     return this.shared.sizeOnDisk;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getPlayerNBTTagCompound() {
/* 177 */     return this.shared.playerTag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVanillaDimension() {
/* 186 */     return this.shared.dimension;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnX(int par1) {
/* 194 */     if (this.dimension_id == 0) {
/* 195 */       this.shared.spawnX = par1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnY(int par1) {
/* 203 */     if (this.dimension_id == 0) {
/* 204 */       this.shared.spawnY = par1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnZ(int par1) {
/* 212 */     if (this.dimension_id == 0) {
/* 213 */       this.shared.spawnZ = par1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTotalWorldTime(long total_world_time, World world) {
/* 224 */     if (Minecraft.inDevMode() && world.getDimensionId() != this.dimension_id) {
/*     */       
/* 226 */       Minecraft.setErrorMessage("setTotalWorldTime: dimension id discrepency: " + world.getDimensionId() + " vs " + this.dimension_id);
/* 227 */       (new Exception()).printStackTrace();
/*     */     } 
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
/* 239 */     this.shared.setTotalWorldTime(world, total_world_time);
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
/*     */   public void setTotalWorldTimes(long[] total_world_times, WorldClient world) {
/* 269 */     this.shared.setTotalWorldTimes(total_world_times, world);
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
/*     */   public void setSpawnPosition(int par1, int par2, int par3) {
/* 286 */     if (this.dimension_id == 0) {
/*     */       
/* 288 */       this.shared.spawnX = par1;
/* 289 */       this.shared.spawnY = par2;
/* 290 */       this.shared.spawnZ = par3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWorldName() {
/* 299 */     return this.shared.levelName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorldName(String par1Str) {
/* 304 */     if (this.dimension_id == 0) {
/* 305 */       this.shared.levelName = par1Str;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSaveVersion() {
/* 313 */     return this.shared.saveVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSaveVersion(int par1) {
/* 321 */     if (this.dimension_id == 0) {
/* 322 */       this.shared.saveVersion = par1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLastTimePlayed() {
/* 330 */     return this.shared.lastTimePlayed;
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
/*     */   public EnumGameType getGameType() {
/* 441 */     if (!Minecraft.inDevMode()) {
/* 442 */       this.shared.theGameType = EnumGameType.SURVIVAL;
/*     */     }
/* 444 */     return this.shared.theGameType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMapFeaturesEnabled() {
/* 452 */     return this.shared.mapFeaturesEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameType(EnumGameType par1EnumGameType) {
/* 460 */     if (!Minecraft.inDevMode()) {
/* 461 */       par1EnumGameType = EnumGameType.SURVIVAL;
/*     */     }
/* 463 */     this.shared.theGameType = par1EnumGameType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHardcoreModeEnabled() {
/* 471 */     return this.shared.hardcore;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldType getTerrainType() {
/* 476 */     return this.shared.terrainType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTerrainType(WorldType par1WorldType) {
/* 481 */     if (this.dimension_id == 0) {
/* 482 */       this.shared.terrainType = par1WorldType;
/*     */     }
/*     */   }
/*     */   
/*     */   public String getGeneratorOptions() {
/* 487 */     return this.shared.generatorOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areCommandsAllowed() {
/* 495 */     return Minecraft.inDevMode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInitialized() {
/* 504 */     return this.shared.initialized;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerInitialized(boolean par1) {
/* 512 */     if (this.dimension_id == 0) {
/* 513 */       this.shared.initialized = par1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameRules getGameRulesInstance() {
/* 521 */     return this.shared.theGameRules;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToCrashReport(CrashReportCategory par1CrashReportCategory) {
/* 529 */     par1CrashReportCategory.addCrashSectionCallable("Level seed", new CallableLevelSeed(this));
/* 530 */     par1CrashReportCategory.addCrashSectionCallable("Level generator", new CallableLevelGenerator(this));
/* 531 */     par1CrashReportCategory.addCrashSectionCallable("Level generator options", new CallableLevelGeneratorOptions(this));
/* 532 */     par1CrashReportCategory.addCrashSectionCallable("Level spawn location", new CallableLevelSpawnLocation(this));
/* 533 */     par1CrashReportCategory.addCrashSectionCallable("Level time", new CallableLevelTime(this));
/* 534 */     par1CrashReportCategory.addCrashSectionCallable("Level dimension", new CallableLevelDimension(this));
/* 535 */     par1CrashReportCategory.addCrashSectionCallable("Level storage version", new CallableLevelStorageVersion(this));
/* 536 */     par1CrashReportCategory.addCrashSectionCallable("Level weather", new CallableLevelWeather(this));
/* 537 */     par1CrashReportCategory.addCrashSectionCallable("Level game mode", new CallableLevelGamemode(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static WorldType getTerrainTypeOfWorld(WorldInfo par0WorldInfo) {
/* 545 */     return par0WorldInfo.shared.terrainType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean getMapFeaturesEnabled(WorldInfo par0WorldInfo) {
/* 553 */     return par0WorldInfo.shared.mapFeaturesEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   static String getWorldGeneratorOptions(WorldInfo par0WorldInfo) {
/* 558 */     return par0WorldInfo.shared.generatorOptions;
/*     */   }
/*     */ 
/*     */   
/*     */   static int getSpawnXCoordinate(WorldInfo par0WorldInfo) {
/* 563 */     return par0WorldInfo.shared.spawnX;
/*     */   }
/*     */ 
/*     */   
/*     */   static int getSpawnYCoordinate(WorldInfo par0WorldInfo) {
/* 568 */     return par0WorldInfo.shared.spawnY;
/*     */   }
/*     */ 
/*     */   
/*     */   static int getSpawnZCoordinate(WorldInfo par0WorldInfo) {
/* 573 */     return par0WorldInfo.shared.spawnZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static long func_85126_g(WorldInfo par0WorldInfo) {
/* 584 */     return par0WorldInfo.shared.getTotalWorldTime(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final long getWorldTimeOfDay(WorldInfo par0WorldInfo, int dimension_id) {
/* 594 */     return par0WorldInfo.getWorldTimeOfDay(dimension_id);
/*     */   }
/*     */ 
/*     */   
/*     */   static int func_85122_i(WorldInfo par0WorldInfo) {
/* 599 */     return par0WorldInfo.shared.dimension;
/*     */   }
/*     */ 
/*     */   
/*     */   static int getSaveVersion(WorldInfo par0WorldInfo) {
/* 604 */     return par0WorldInfo.shared.saveVersion;
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
/*     */   static EnumGameType getGameType(WorldInfo par0WorldInfo) {
/* 645 */     return par0WorldInfo.shared.theGameType;
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean func_85117_p(WorldInfo par0WorldInfo) {
/* 650 */     return par0WorldInfo.shared.hardcore;
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean func_85131_q(WorldInfo par0WorldInfo) {
/* 655 */     return par0WorldInfo.shared.allowCommands;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fullfillVillageCondition(int condition, WorldServer world_server) {
/* 660 */     this.shared.village_conditions = (byte)(this.shared.village_conditions | condition);
/* 661 */     world_server.sendPacketToAllPlayersInThisDimension(new Packet70GameEvent(7, this.shared.village_conditions));
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
/*     */   public int calcChecksum() {
/* 674 */     return this.shared.calcChecksum();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnderworldVisited() {
/* 679 */     this.shared.the_underworld_has_been_visited = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getUnderworldHasBeenVisited() {
/* 684 */     return this.shared.the_underworld_has_been_visited;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNetherVisited() {
/* 689 */     this.shared.the_nether_has_been_visited = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getNetherHasBeenVisited() {
/* 694 */     return this.shared.the_nether_has_been_visited;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillsEnabled(boolean are_skills_enabled) {
/* 699 */     this.shared.are_skills_enabled = are_skills_enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areSkillsEnabled() {
/* 704 */     return this.shared.are_skills_enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areCoinsEnabled() {
/* 709 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEarliestMITEReleaseRunIn(int earliest_MITE_release_run_in) {
/* 715 */     this.shared.earliest_MITE_release_run_in = earliest_MITE_release_run_in;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLatestMITEReleaseRunIn(int latest_MITE_release_run_in) {
/* 720 */     this.shared.latest_MITE_release_run_in = latest_MITE_release_run_in;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEarliestMITEReleaseRunIn() {
/* 725 */     return this.shared.earliest_MITE_release_run_in;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLatestMITEReleaseRunIn() {
/* 730 */     return this.shared.latest_MITE_release_run_in;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getCurses() {
/* 735 */     return this.shared.curses;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMITEWorld() {
/* 740 */     return this.shared.is_valid_MITE_world;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVillageConditions(byte village_conditions) {
/* 745 */     this.shared.village_conditions = village_conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getVillageConditions() {
/* 750 */     return this.shared.village_conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte getVillagePrerequisites() {
/* 756 */     return 16;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorldCreationTime(long world_creation_time) {
/* 761 */     this.shared.world_creation_time = world_creation_time;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getWorldCreationTime() {
/* 766 */     return this.shared.world_creation_time;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNanotime(long nanotime) {
/* 771 */     this.shared.nanotime = nanotime;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getNanotime() {
/* 776 */     return this.shared.nanotime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsNotValidReason() {
/* 781 */     return this.shared.is_not_valid_reason;
/*     */   }
/*     */ 
/*     */   
/*     */   public void incrementSacredSandstonesPlaced() {
/* 786 */     this.shared.sacred_stones_placed++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDimensionId(int dimension_id) {
/* 792 */     this.dimension_id = dimension_id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDimensionId() {
/* 797 */     return this.dimension_id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSignatureBeenAdded(int id) {
/* 803 */     return this.shared.hasSignatureBeenAdded(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSignature(int id) {
/* 809 */     this.shared.addSignature(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeSignature(int id) {
/* 815 */     return this.shared.removeSignature(id);
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
/*     */   public int getNumSignatures() {
/* 830 */     return this.shared.getNumSignatures();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAchievementUnlocked(Achievement achievement) {
/* 835 */     return this.shared.hasAchievementUnlocked(achievement);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAchievementUnlockedOrIsNull(Achievement achievement) {
/* 840 */     return (achievement == null || hasAchievementUnlocked(achievement));
/*     */   }
/*     */ 
/*     */   
/*     */   public void unlockAchievement(Achievement achievement, String username, int day, boolean update_clients) {
/* 845 */     this.shared.unlockAchievement(achievement, username, day, update_clients);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unlockAchievement(Achievement achievement, EntityPlayer player) {
/* 850 */     this.shared.unlockAchievement(achievement, player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAchievements(HashMap achievements) {
/* 856 */     this.shared.setAchievements(achievements);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HashMap getAchievements() {
/* 862 */     return this.shared.getAchievements();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldAchievement getWorldAchievement(Achievement achievement) {
/* 867 */     return this.shared.getWorldAchievement(achievement);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean haveAchievementsBeenUnlockedByOtherPlayers(EntityPlayer player) {
/* 872 */     return this.shared.haveAchievementsBeenUnlockedByOtherPlayers(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEarliestAllowableMITERelease(int earliest_allowable_MITE_release) {
/* 878 */     if (this.shared.earliest_allowable_MITE_release < earliest_allowable_MITE_release)
/* 879 */       this.shared.earliest_allowable_MITE_release = earliest_allowable_MITE_release; 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */