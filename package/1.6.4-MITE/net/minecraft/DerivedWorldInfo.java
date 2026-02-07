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
/*     */ public class DerivedWorldInfo
/*     */   extends WorldInfo
/*     */ {
/*     */   private final WorldInfo theWorldInfo;
/*     */   
/*     */   public DerivedWorldInfo(WorldInfo worldInfo) {
/*  21 */     this.theWorldInfo = worldInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound getNBTTagCompound() {
/*  26 */     return this.theWorldInfo.getNBTTagCompound();
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound cloneNBTCompound(NBTTagCompound nBTTagCompound) {
/*  31 */     return this.theWorldInfo.cloneNBTCompound(nBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  36 */     return this.theWorldInfo.getSeed();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpawnX() {
/*  41 */     return this.theWorldInfo.getSpawnX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpawnY() {
/*  46 */     return this.theWorldInfo.getSpawnY();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpawnZ() {
/*  51 */     return this.theWorldInfo.getSpawnZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getWorldTotalTime() {
/*  56 */     return this.theWorldInfo.f();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getWorldTime() {
/*  61 */     return this.theWorldInfo.g();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSizeOnDisk() {
/*  66 */     return this.theWorldInfo.getSizeOnDisk();
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound getPlayerNBTTagCompound() {
/*  71 */     return this.theWorldInfo.getPlayerNBTTagCompound();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVanillaDimension() {
/*  76 */     return this.theWorldInfo.getVanillaDimension();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWorldName() {
/*  81 */     return this.theWorldInfo.getWorldName();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSaveVersion() {
/*  86 */     return this.theWorldInfo.getSaveVersion();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastTimePlayed() {
/*  91 */     return this.theWorldInfo.getLastTimePlayed();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isThundering() {
/*  96 */     return this.theWorldInfo.n();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getThunderTime() {
/* 101 */     return this.theWorldInfo.o();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRaining() {
/* 106 */     return this.theWorldInfo.p();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRainTime() {
/* 111 */     return this.theWorldInfo.q();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumGameType getGameType() {
/* 116 */     return this.theWorldInfo.getGameType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnX(int i) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnY(int i) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnZ(int i) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementTotalWorldTime(long l) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldTime(long l) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnPosition(int i, int j, int k) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldName(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSaveVersion(int i) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThundering(boolean bl) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThunderTime(int i) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRaining(boolean bl) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRainTime(int i) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMapFeaturesEnabled() {
/* 185 */     return this.theWorldInfo.isMapFeaturesEnabled();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHardcoreModeEnabled() {
/* 193 */     return this.theWorldInfo.isHardcoreModeEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldType getTerrainType() {
/* 198 */     return this.theWorldInfo.getTerrainType();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTerrainType(WorldType worldType) {}
/*     */ 
/*     */   
/*     */   public boolean areCommandsAllowed() {
/* 207 */     return this.theWorldInfo.areCommandsAllowed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInitialized() {
/* 216 */     return this.theWorldInfo.isInitialized();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerInitialized(boolean bl) {}
/*     */ 
/*     */   
/*     */   public GameRules getGameRulesInstance() {
/* 225 */     return this.theWorldInfo.getGameRulesInstance();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DerivedWorldInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */