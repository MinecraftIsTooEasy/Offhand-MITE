/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class IntegratedServer
/*     */   extends MinecraftServer
/*     */ {
/*     */   private final Minecraft mc;
/*     */   private final WorldSettings theWorldSettings;
/*     */   private final ILogAgent serverLogAgent;
/*     */   private IntegratedServerListenThread theServerListeningThread;
/*     */   private boolean isGamePaused;
/*     */   private boolean isPublic;
/*     */   private ThreadLanServerPing lanServerPing;
/*     */   private final ILogAgent aux_log;
/*     */   
/*     */   public IntegratedServer(Minecraft par1Minecraft, String par2Str, String par3Str, WorldSettings par4WorldSettings) {
/*  28 */     super(par1Minecraft.saves_dir_MITE);
/*  29 */     this.serverLogAgent = new LogAgent("Minecraft-Server", " [SERVER]", (new File(par1Minecraft.mcDataDir, "output-server.log")).getAbsolutePath());
/*  30 */     this.aux_log = new LogAgent(StringHelper.mirrorString("Hfhkrxrlfh-Olt"), (String)null, (new File(par1Minecraft.mcDataDir, StringHelper.mirrorString("hfhkrxrlfh.olt"))).getAbsolutePath());
/*  31 */     setServerOwner(par1Minecraft.getSession().getUsername());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  37 */     setFolderName(par2Str);
/*  38 */     setWorldName(par3Str);
/*  39 */     setDemo(par1Minecraft.isDemo());
/*  40 */     canCreateBonusChest(par4WorldSettings.isBonusChestEnabled());
/*  41 */     setBuildLimit(256);
/*  42 */     setConfigurationManager(new IntegratedPlayerList(this));
/*  43 */     this.mc = par1Minecraft;
/*  44 */     this.serverProxy = par1Minecraft.getProxy();
/*  45 */     this.theWorldSettings = par4WorldSettings;
/*     */ 
/*     */     
/*     */     try {
/*  49 */       this.theServerListeningThread = new IntegratedServerListenThread(this);
/*     */     }
/*  51 */     catch (IOException var6) {
/*     */       
/*  53 */       throw new Error();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadAllWorlds(String par1Str, String par2Str, long par3, WorldType par5WorldType, String par6Str) {
/*  59 */     convertMapIfNeeded(par1Str);
/*     */     
/*  61 */     this.worldServers = new WorldServer[4];
/*  62 */     this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
/*  63 */     ISaveHandler var7 = getActiveAnvilConverter().getSaveLoader(par1Str, true);
/*     */     
/*  65 */     for (int var8 = 0; var8 < this.worldServers.length; var8++) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  82 */       byte var9 = (byte)getWorldDimensionIdFromIndex(var8);
/*     */       
/*  84 */       if (var8 == 0) {
/*     */         
/*  86 */         if (isDemo())
/*     */         {
/*  88 */           this.worldServers[var8] = new DemoWorldServer(this, var7, par2Str, var9, this.theProfiler, getLogAgent());
/*     */         }
/*     */         else
/*     */         {
/*  92 */           this.worldServers[var8] = new WorldServer(this, var7, par2Str, var9, this.theWorldSettings, this.theProfiler, getLogAgent());
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  97 */         this.worldServers[var8] = new WorldServerMulti(this, var7, par2Str, var9, this.theWorldSettings, this.worldServers[0], this.theProfiler, getLogAgent());
/*     */       } 
/*     */       
/* 100 */       this.worldServers[var8].addWorldAccess(new WorldManager(this, this.worldServers[var8]));
/* 101 */       getConfigurationManager().setPlayerManager(this.worldServers);
/*     */     } 
/*     */     
/* 104 */     setDifficultyForAllWorlds(getDifficulty());
/* 105 */     initialWorldChunkLoad();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean startServer() throws IOException {
/* 113 */     this.serverLogAgent.logInfo("Starting integrated minecraft server version 1.6.4");
/* 114 */     setOnlineMode(false);
/* 115 */     setCanSpawnAnimals(true);
/* 116 */     setCanSpawnNPCs(true);
/* 117 */     setAllowPvp(true);
/* 118 */     setAllowFlight(true);
/* 119 */     this.serverLogAgent.logInfo("Generating keypair");
/* 120 */     setKeyPair(CryptManager.createNewKeyPair());
/* 121 */     loadAllWorlds(getFolderName(), getWorldName(), this.theWorldSettings.getSeed(), this.theWorldSettings.getTerrainType(), this.theWorldSettings.func_82749_j());
/*     */ 
/*     */     
/* 124 */     if (getServerOwner().equals("Dedicated_Server")) {
/*     */       
/* 126 */       setMOTD("MITE 1.6.4 Server - " + this.worldServers[0].getWorldInfo().getWorldName());
/*     */     } else {
/* 128 */       setMOTD(getServerOwner() + " - " + this.worldServers[0].getWorldInfo().getWorldName());
/*     */     } 
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 138 */     boolean var1 = this.isGamePaused;
/* 139 */     this.isGamePaused = this.theServerListeningThread.isGamePaused();
/*     */ 
/*     */     
/* 142 */     if (!var1 && this.isGamePaused && !treachery_detected) {
/*     */ 
/*     */       
/* 145 */       this.serverLogAgent.logInfo("Saving and pausing game...");
/* 146 */       getConfigurationManager().saveAllPlayerData();
/*     */       
/* 148 */       saveAllWorlds(false, false);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 153 */     if (!this.isGamePaused)
/*     */     {
/* 155 */       super.tick();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveAllPlayersAndWorlds() {
/* 162 */     if (!treachery_detected) {
/*     */       
/* 164 */       this.serverLogAgent.logInfo("Saving all players and worlds...");
/* 165 */       getConfigurationManager().saveAllPlayerData();
/* 166 */       saveAllWorlds(false, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canStructuresSpawn() {
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumGameType getGameType() {
/* 177 */     return this.theWorldSettings.getGameType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDifficulty() {
/* 186 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHardcore() {
/* 194 */     return this.theWorldSettings.getHardcoreEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   protected File getDataDirectory() {
/* 199 */     return this.mc.mcDataDir;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDedicatedServer() {
/* 204 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntegratedServerListenThread getServerListeningThread() {
/* 212 */     return this.theServerListeningThread;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalTick(CrashReport par1CrashReport) {
/* 220 */     this.mc.crashed(par1CrashReport);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReport addServerInfoToCrashReport(CrashReport par1CrashReport) {
/* 228 */     par1CrashReport = super.addServerInfoToCrashReport(par1CrashReport);
/* 229 */     par1CrashReport.getCategory().addCrashSectionCallable("Type", new CallableType3(this));
/* 230 */     par1CrashReport.getCategory().addCrashSectionCallable("Is Modded", new CallableIsModded(this));
/* 231 */     return par1CrashReport;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addServerStatsToSnooper(PlayerUsageSnooper par1PlayerUsageSnooper) {
/* 236 */     super.addServerStatsToSnooper(par1PlayerUsageSnooper);
/* 237 */     par1PlayerUsageSnooper.addData("snooper_partner", this.mc.getPlayerUsageSnooper().getUniqueID());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSnooperEnabled() {
/* 245 */     return Minecraft.getMinecraft().isSnooperEnabled();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shareToLAN(EnumGameType par1EnumGameType, boolean par2) {
/* 253 */     if (Minecraft.isInTournamentMode()) {
/* 254 */       return null;
/*     */     }
/* 256 */     if (!Minecraft.inDevMode()) {
/*     */       
/* 258 */       par1EnumGameType = EnumGameType.SURVIVAL;
/* 259 */       par2 = false;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 264 */       String var3 = this.theServerListeningThread.func_71755_c();
/* 265 */       getLogAgent().logInfo("Started on " + var3);
/* 266 */       this.isPublic = true;
/* 267 */       this.lanServerPing = new ThreadLanServerPing(getMOTD(), var3);
/* 268 */       this.lanServerPing.start();
/* 269 */       getConfigurationManager().setGameType(par1EnumGameType);
/* 270 */       getConfigurationManager().setCommandsAllowedForAll(par2);
/* 271 */       return var3;
/*     */     }
/* 273 */     catch (IOException var4) {
/*     */       
/* 275 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ILogAgent getLogAgent() {
/* 281 */     return this.serverLogAgent;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILogAgent getAuxLogAgent() {
/* 286 */     return this.aux_log;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopServer() {
/* 294 */     super.stopServer();
/*     */     
/* 296 */     if (this.lanServerPing != null) {
/*     */       
/* 298 */       this.lanServerPing.interrupt();
/* 299 */       this.lanServerPing = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initiateShutdown() {
/* 308 */     super.initiateShutdown();
/*     */     
/* 310 */     if (this.lanServerPing != null) {
/*     */       
/* 312 */       this.lanServerPing.interrupt();
/* 313 */       this.lanServerPing = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPublic() {
/* 322 */     return this.isPublic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameType(EnumGameType par1EnumGameType) {
/* 330 */     getConfigurationManager().setGameType(par1EnumGameType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCommandBlockEnabled() {
/* 338 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_110455_j() {
/* 343 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areSkillsEnabled() {
/* 348 */     return this.theWorldSettings.areSkillsEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public NetworkListenThread getNetworkThread() {
/* 353 */     return getServerListeningThread();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isServerSideMappingEnabled() {
/* 358 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IntegratedServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */