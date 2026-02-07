/*      */ package net.minecraft.server;
/*      */ 
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.net.Proxy;
/*      */ import java.security.KeyPair;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import net.minecraft.AnvilSaveConverter;
/*      */ import net.minecraft.AxisAlignedBB;
/*      */ import net.minecraft.CallableIsServerModded;
/*      */ import net.minecraft.CallableServerMemoryStats;
/*      */ import net.minecraft.CallableServerProfiler;
/*      */ import net.minecraft.ChatMessageComponent;
/*      */ import net.minecraft.ChunkCoordinates;
/*      */ import net.minecraft.CommandBase;
/*      */ import net.minecraft.ConvertingProgressUpdate;
/*      */ import net.minecraft.CrashReport;
/*      */ import net.minecraft.Debug;
/*      */ import net.minecraft.DebugAttack;
/*      */ import net.minecraft.DedicatedServer;
/*      */ import net.minecraft.DemoWorldServer;
/*      */ import net.minecraft.DispenserBehaviors;
/*      */ import net.minecraft.Entity;
/*      */ import net.minecraft.EntityPlayer;
/*      */ import net.minecraft.EnumChatFormatting;
/*      */ import net.minecraft.EnumGameType;
/*      */ import net.minecraft.EnumSignal;
/*      */ import net.minecraft.EnumTournamentType;
/*      */ import net.minecraft.ICommandManager;
/*      */ import net.minecraft.ICommandSender;
/*      */ import net.minecraft.ILogAgent;
/*      */ import net.minecraft.IPlayerUsage;
/*      */ import net.minecraft.IProgressUpdate;
/*      */ import net.minecraft.ISaveFormat;
/*      */ import net.minecraft.ISaveHandler;
/*      */ import net.minecraft.IUpdatePlayerListBox;
/*      */ import net.minecraft.IWorldAccess;
/*      */ import net.minecraft.MathHelper;
/*      */ import net.minecraft.Minecraft;
/*      */ import net.minecraft.MinecraftException;
/*      */ import net.minecraft.NetworkListenThread;
/*      */ import net.minecraft.Packet;
/*      */ import net.minecraft.Packet4UpdateTime;
/*      */ import net.minecraft.Packet85SimpleSignal;
/*      */ import net.minecraft.Packet92UpdateTimeSmall;
/*      */ import net.minecraft.PlayerUsageSnooper;
/*      */ import net.minecraft.Profiler;
/*      */ import net.minecraft.RConConsoleSource;
/*      */ import net.minecraft.ReportedException;
/*      */ import net.minecraft.ResourceLocation;
/*      */ import net.minecraft.ServerCommandManager;
/*      */ import net.minecraft.ServerConfigurationManager;
/*      */ import net.minecraft.ServerPlayer;
/*      */ import net.minecraft.StatList;
/*      */ import net.minecraft.StringHelper;
/*      */ import net.minecraft.TextureManager;
/*      */ import net.minecraft.ThreadDedicatedServerMITE;
/*      */ import net.minecraft.ThreadMinecraftServer;
/*      */ import net.minecraft.ThreadedFileIOBase;
/*      */ import net.minecraft.World;
/*      */ import net.minecraft.WorldInfo;
/*      */ import net.minecraft.WorldManager;
/*      */ import net.minecraft.WorldServer;
/*      */ import net.minecraft.WorldServerMulti;
/*      */ import net.minecraft.WorldSettings;
/*      */ import net.minecraft.WorldType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class MinecraftServer
/*      */   implements ICommandSender, Runnable, IPlayerUsage
/*      */ {
/*      */   private static MinecraftServer mcServer;
/*      */   private final ISaveFormat anvilConverterForAnvilFile;
/*   84 */   private final PlayerUsageSnooper usageSnooper = new PlayerUsageSnooper("server", this, getSystemTimeMillis());
/*      */   
/*      */   private final File anvilFile;
/*      */   
/*   88 */   private final List playersOnline = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   93 */   private final List tickables = new ArrayList();
/*      */   private final ICommandManager commandManager;
/*   95 */   public final Profiler theProfiler = new Profiler();
/*      */ 
/*      */   
/*      */   private String hostname;
/*      */ 
/*      */   
/*  101 */   private int serverPort = -1;
/*      */ 
/*      */   
/*      */   public WorldServer[] worldServers;
/*      */ 
/*      */   
/*      */   private ServerConfigurationManager serverConfigManager;
/*      */ 
/*      */   
/*      */   private boolean serverRunning = true;
/*      */ 
/*      */   
/*      */   private boolean serverStopped;
/*      */   
/*      */   protected int tickCounter;
/*      */   
/*      */   protected Proxy serverProxy;
/*      */   
/*      */   public String currentTask;
/*      */   
/*      */   public int percentDone;
/*      */   
/*      */   private boolean onlineMode;
/*      */   
/*      */   private boolean canSpawnAnimals;
/*      */   
/*      */   private boolean canSpawnNPCs;
/*      */   
/*      */   private boolean pvpEnabled;
/*      */   
/*      */   private boolean allowFlight;
/*      */   
/*      */   private String motd;
/*      */   
/*      */   private int buildLimit;
/*      */   
/*      */   private int field_143008_E;
/*      */   
/*      */   private long lastSentPacketID;
/*      */   
/*      */   private long lastSentPacketSize;
/*      */   
/*      */   private long lastReceivedID;
/*      */   
/*      */   private long lastReceivedSize;
/*      */   
/*      */   public final long[] sentPacketCountArray;
/*      */   
/*      */   public final long[] sentPacketSizeArray;
/*      */   
/*      */   public final long[] receivedPacketCountArray;
/*      */   
/*      */   public final long[] receivedPacketSizeArray;
/*      */   
/*      */   public final long[] tickTimeArray;
/*      */   
/*      */   public long[][] timeOfLastDimensionTick;
/*      */   
/*      */   private KeyPair serverKeyPair;
/*      */   
/*      */   private String serverOwner;
/*      */   
/*      */   private String folderName;
/*      */   
/*      */   private String worldName;
/*      */   
/*      */   private boolean isDemo;
/*      */   
/*      */   private boolean enableBonusChest;
/*      */   
/*      */   private boolean worldIsBeingDeleted;
/*      */   
/*      */   private String texturePack;
/*      */   
/*      */   private boolean serverIsRunning;
/*      */   
/*      */   private long timeOfLastWarning;
/*      */   
/*      */   private String userMessage;
/*      */   
/*      */   private boolean startProfiling;
/*      */   
/*      */   private boolean isGamemodeForced;
/*      */   
/*  185 */   public int default_world_map_size = 1024;
/*      */   
/*      */   public boolean save_world_maps_on_shutdown;
/*      */   
/*      */   public int ms_taken_for_last_100_ticks;
/*      */   
/*      */   public static boolean treachery_detected;
/*      */   
/*      */   private static int treachery_shutdown_counter;
/*      */   
/*      */   public static final int num_world_servers = 4;
/*      */   
/*      */   public final ThreadMinecraftServer thread;
/*      */   public static final int WORLD_INDEX_OVERWORLD = 0;
/*      */   public static final int WORLD_INDEX_NETHER = 1;
/*      */   public static final int WORLD_INDEX_THE_END = 2;
/*      */   public static final int WORLD_INDEX_UNDERWORLD = 3;
/*      */   
/*      */   public MinecraftServer(File par1File) {
/*  204 */     this.serverProxy = Proxy.NO_PROXY;
/*  205 */     this.field_143008_E = 0;
/*  206 */     this.sentPacketCountArray = new long[100];
/*  207 */     this.sentPacketSizeArray = new long[100];
/*  208 */     this.receivedPacketCountArray = new long[100];
/*  209 */     this.receivedPacketSizeArray = new long[100];
/*  210 */     this.tickTimeArray = new long[100];
/*  211 */     this.texturePack = "";
/*  212 */     mcServer = this;
/*  213 */     this.anvilFile = par1File;
/*  214 */     this.commandManager = (ICommandManager)new ServerCommandManager();
/*  215 */     this.anvilConverterForAnvilFile = (ISaveFormat)new AnvilSaveConverter(par1File);
/*  216 */     registerDispenseBehaviors();
/*      */     
/*  218 */     if (Minecraft.hit_list == null) {
/*  219 */       Minecraft.hit_list = Minecraft.getHitList();
/*      */     }
/*  221 */     this.thread = new ThreadMinecraftServer(this, "Server thread");
/*  222 */     Minecraft.server_thread = this.thread;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void registerDispenseBehaviors() {
/*  232 */     DispenserBehaviors.registerDispenserBehaviours();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void convertMapIfNeeded(String par1Str) {
/*  242 */     if (getActiveAnvilConverter().isOldMapFormat(par1Str)) {
/*      */ 
/*      */ 
/*      */       
/*  246 */       getLogAgent().logInfo("Converting map!");
/*  247 */       setUserMessage("menu.convertingLevel");
/*  248 */       getActiveAnvilConverter().convertMapFormat(par1Str, (IProgressUpdate)new ConvertingProgressUpdate(this));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected synchronized void setUserMessage(String par1Str) {
/*  257 */     this.userMessage = par1Str;
/*      */   }
/*      */ 
/*      */   
/*      */   public synchronized String getUserMessage() {
/*  262 */     return this.userMessage;
/*      */   }
/*      */   
/*      */   protected void loadAllWorlds(String par1Str, String par2Str, long par3, WorldType par5WorldType, String par6Str) {
/*      */     WorldSettings var8;
/*  267 */     convertMapIfNeeded(par1Str);
/*  268 */     setUserMessage("menu.loadingLevel");
/*      */     
/*  270 */     this.worldServers = new WorldServer[4];
/*  271 */     this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
/*  272 */     ISaveHandler var7 = this.anvilConverterForAnvilFile.getSaveLoader(par1Str, true);
/*  273 */     WorldInfo var9 = var7.loadWorldInfo();
/*      */ 
/*      */     
/*  276 */     if (var9 == null) {
/*      */ 
/*      */       
/*  279 */       var8 = new WorldSettings(par3, getGameType(), canStructuresSpawn(), isHardcore(), par5WorldType, areSkillsEnabled());
/*  280 */       var8.func_82750_a(par6Str);
/*      */     }
/*      */     else {
/*      */       
/*  284 */       var8 = new WorldSettings(var9);
/*      */     } 
/*      */     
/*  287 */     if (this.enableBonusChest)
/*      */     {
/*  289 */       var8.enableBonusChest();
/*      */     }
/*      */     
/*  292 */     for (int var10 = 0; var10 < this.worldServers.length; var10++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  309 */       byte var11 = (byte)getWorldDimensionIdFromIndex(var10);
/*      */       
/*  311 */       if (var10 == 0) {
/*      */         
/*  313 */         if (isDemo())
/*      */         {
/*  315 */           this.worldServers[var10] = (WorldServer)new DemoWorldServer(this, var7, par2Str, var11, this.theProfiler, getLogAgent());
/*      */         }
/*      */         else
/*      */         {
/*  319 */           this.worldServers[var10] = new WorldServer(this, var7, par2Str, var11, var8, this.theProfiler, getLogAgent());
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  324 */         this.worldServers[var10] = (WorldServer)new WorldServerMulti(this, var7, par2Str, var11, var8, this.worldServers[0], this.theProfiler, getLogAgent());
/*      */       } 
/*      */       
/*  327 */       this.worldServers[var10].addWorldAccess((IWorldAccess)new WorldManager(this, this.worldServers[var10]));
/*      */       
/*  329 */       if (!isSinglePlayer())
/*      */       {
/*  331 */         this.worldServers[var10].getWorldInfo().setGameType(getGameType());
/*      */       }
/*      */       
/*  334 */       this.serverConfigManager.setPlayerManager(this.worldServers);
/*      */     } 
/*      */     
/*  337 */     setDifficultyForAllWorlds(getDifficulty());
/*  338 */     initialWorldChunkLoad();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initialWorldChunkLoad() {
/*  343 */     boolean var1 = true;
/*  344 */     boolean var2 = true;
/*  345 */     boolean var3 = true;
/*  346 */     boolean var4 = true;
/*  347 */     int var5 = 0;
/*  348 */     setUserMessage("menu.generatingTerrain");
/*  349 */     byte var6 = 0;
/*  350 */     getLogAgent().logInfo("Preparing start region for level " + var6);
/*  351 */     WorldServer var7 = this.worldServers[var6];
/*  352 */     ChunkCoordinates var8 = var7.getSpawnPoint();
/*  353 */     long var9 = getSystemTimeMillis();
/*      */     
/*  355 */     for (int var11 = -192; var11 <= 192 && isServerRunning(); var11 += 16) {
/*      */       
/*  357 */       for (int var12 = -192; var12 <= 192 && isServerRunning(); var12 += 16) {
/*      */         
/*  359 */         long var13 = getSystemTimeMillis();
/*      */         
/*  361 */         if (var13 - var9 > 1000L) {
/*      */           
/*  363 */           outputPercentRemaining("Preparing spawn area", var5 * 100 / 625);
/*  364 */           var9 = var13;
/*      */         } 
/*      */         
/*  367 */         var5++;
/*  368 */         var7.theChunkProviderServer.loadChunk(var8.posX + var11 >> 4, var8.posZ + var12 >> 4);
/*      */       } 
/*      */     } 
/*      */     
/*  372 */     clearCurrentTask();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void outputPercentRemaining(String par1Str, int par2) {
/*  398 */     this.currentTask = par1Str;
/*  399 */     this.percentDone = par2;
/*  400 */     getLogAgent().logInfo(par1Str + ": " + par2 + "%");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void clearCurrentTask() {
/*  408 */     this.currentTask = null;
/*  409 */     this.percentDone = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void saveAllWorlds(boolean par1, boolean wait_until_finished) {
/*  418 */     if (treachery_detected) {
/*      */       return;
/*      */     }
/*  421 */     if (!this.worldIsBeingDeleted) {
/*      */       
/*  423 */       WorldServer[] var2 = this.worldServers;
/*  424 */       int var3 = var2.length;
/*      */       
/*  426 */       for (int var4 = 0; var4 < var3; var4++) {
/*      */         
/*  428 */         WorldServer var5 = var2[var4];
/*      */         
/*  430 */         if (var5 != null) {
/*      */           
/*      */           try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  439 */             var5.saveAllChunks(true, (IProgressUpdate)null);
/*      */           }
/*  441 */           catch (MinecraftException var7) {
/*      */             
/*  443 */             getLogAgent().logWarning(var7.getMessage());
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  448 */       if (wait_until_finished) {
/*  449 */         ThreadedFileIOBase.waitForFinish();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stopServer() {
/*  463 */     if (!this.worldIsBeingDeleted) {
/*      */       
/*  465 */       getLogAgent().logInfo("Stopping server");
/*      */       
/*  467 */       if (getNetworkThread() != null)
/*      */       {
/*  469 */         getNetworkThread().stopListening();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  482 */       if (this.serverConfigManager != null) {
/*      */         
/*  484 */         if (!treachery_detected) {
/*      */           
/*  486 */           getLogAgent().logInfo("Saving players");
/*  487 */           this.serverConfigManager.saveAllPlayerData();
/*      */         } 
/*      */         
/*  490 */         this.serverConfigManager.removeAllPlayers();
/*      */       } 
/*      */       
/*  493 */       if (!treachery_detected) {
/*      */         
/*  495 */         getLogAgent().logInfo("Saving worlds");
/*      */         
/*  497 */         long t = System.currentTimeMillis();
/*  498 */         saveAllWorlds(false, true);
/*  499 */         getLogAgent().logInfo("Finished saving all worlds in " + StringHelper.formatFloat((float)(System.currentTimeMillis() - t) / 1000.0F) + " seconds");
/*      */         
/*  501 */         if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*  502 */           DedicatedServer.appendTournamentStandingsToFile(true);
/*      */         }
/*  504 */         if (this.save_world_maps_on_shutdown) {
/*  505 */           saveWorldMaps();
/*      */         } else {
/*  507 */           getLogAgent().logInfo("Discarding world maps");
/*      */         } 
/*      */       } 
/*  510 */       if (treachery_detected) {
/*  511 */         clearTreacheryDetected();
/*      */       }
/*  513 */       for (int var1 = 0; var1 < this.worldServers.length; var1++) {
/*      */         
/*  515 */         WorldServer var2 = this.worldServers[var1];
/*  516 */         var2.flush();
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  521 */       if (this.usageSnooper != null && this.usageSnooper.isSnooperRunning())
/*      */       {
/*  523 */         this.usageSnooper.stopSnooper();
/*      */       }
/*      */       
/*  526 */       finalCleanup();
/*      */ 
/*      */     
/*      */     }
/*  530 */     else if (treachery_detected) {
/*  531 */       clearTreacheryDetected();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void finalCleanup() {
/*  537 */     boolean display_amount_freed = false;
/*      */     
/*  539 */     long free_memory = 0L;
/*      */     
/*  541 */     if (display_amount_freed) {
/*      */       
/*  543 */       System.gc();
/*  544 */       free_memory = Runtime.getRuntime().freeMemory();
/*      */     } 
/*      */     
/*  547 */     for (int i = 0; i < this.worldServers.length; i++) {
/*  548 */       this.worldServers[i].finalCleanup();
/*      */     }
/*  550 */     if (display_amount_freed) {
/*      */       
/*  552 */       System.gc();
/*  553 */       Debug.println("finalCleanup: " + ((Runtime.getRuntime().freeMemory() - free_memory) / 1024L / 1024L) + " MB of memory was freed");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getServerHostname() {
/*  562 */     return this.hostname;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHostname(String par1Str) {
/*  567 */     this.hostname = par1Str;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isServerRunning() {
/*  572 */     return this.serverRunning;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initiateShutdown() {
/*  580 */     this.serverRunning = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void run() {
/*      */     try {
/*  587 */       if (startServer())
/*      */       {
/*  589 */         long var1 = getSystemTimeMillis();
/*      */         
/*  591 */         for (long var50 = 0L; this.serverRunning; this.serverIsRunning = true)
/*      */         {
/*  593 */           long var5 = getSystemTimeMillis();
/*  594 */           long var7 = var5 - var1;
/*      */           
/*  596 */           if (var7 > 2000L && var1 - this.timeOfLastWarning >= 15000L) {
/*      */             
/*  598 */             getLogAgent().logWarning("Can't keep up! Did the system time change, or is the server overloaded?");
/*  599 */             var7 = 2000L;
/*  600 */             this.timeOfLastWarning = var1;
/*      */           } 
/*      */           
/*  603 */           if (var7 < 0L) {
/*      */             
/*  605 */             getLogAgent().logWarning("Time ran backwards! Did the system time change?");
/*  606 */             var7 = 0L;
/*      */           } 
/*      */           
/*  609 */           var50 += var7;
/*  610 */           var1 = var5;
/*      */ 
/*      */           
/*  613 */           if (this.worldServers[0].allPlayersAsleepOrDead()) {
/*      */             
/*  615 */             tick();
/*  616 */             var50 = 0L;
/*      */           }
/*      */           else {
/*      */             
/*  620 */             while (var50 > 50L) {
/*      */               
/*  622 */               var50 -= 50L;
/*  623 */               tick();
/*      */             } 
/*      */           } 
/*      */           
/*  627 */           Thread.sleep(1L);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*  635 */         finalTick((CrashReport)null);
/*      */       }
/*      */     
/*  638 */     } catch (Throwable var48) {
/*      */       
/*  640 */       var48.printStackTrace();
/*  641 */       getLogAgent().logSevereException("Encountered an unexpected exception " + var48.getClass().getSimpleName(), var48);
/*  642 */       CrashReport var2 = null;
/*      */       
/*  644 */       if (var48 instanceof ReportedException) {
/*      */         
/*  646 */         var2 = addServerInfoToCrashReport(((ReportedException)var48).getCrashReport());
/*      */       }
/*      */       else {
/*      */         
/*  650 */         var2 = addServerInfoToCrashReport(new CrashReport("Exception in server tick loop", var48));
/*      */       } 
/*      */       
/*  653 */       File var3 = new File(new File(getDataDirectory(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");
/*      */       
/*  655 */       if (var2.saveToFile(var3, getLogAgent())) {
/*      */         
/*  657 */         getLogAgent().logSevere("This crash report has been saved to: " + var3.getAbsolutePath());
/*      */       }
/*      */       else {
/*      */         
/*  661 */         getLogAgent().logSevere("We were unable to save this crash report to disk.");
/*      */       } 
/*      */       
/*  664 */       finalTick(var2);
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/*  670 */         this.save_world_maps_on_shutdown = isServerSideMappingEnabled();
/*  671 */         stopServer();
/*  672 */         this.serverStopped = true;
/*      */       }
/*  674 */       catch (Throwable var46) {
/*      */         
/*  676 */         var46.printStackTrace();
/*      */       }
/*      */       finally {
/*      */         
/*  680 */         systemExitNow();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected File getDataDirectory() {
/*  687 */     return new File(".");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void finalTick(CrashReport par1CrashReport) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void systemExitNow() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tick() {
/*  709 */     if (treachery_detected) {
/*      */       
/*  711 */       if (treachery_shutdown_counter == 200) {
/*  712 */         this.serverConfigManager.sendChatMsg(ChatMessageComponent.createFromText(scramble("Givzxsvib wvgvxgvw! Tznv droo hsfg wldm rm 89 hvxlmwh.")).setColor(EnumChatFormatting.YELLOW));
/*      */       }
/*  714 */       if (--treachery_shutdown_counter <= 0) {
/*  715 */         initiateShutdown();
/*      */       }
/*      */     } 
/*  718 */     if (!isDedicatedServer() && this.tickCounter % 20 == 0) {
/*  719 */       ResourceLocation.verifyResourceLocations();
/*      */     }
/*  721 */     long system_ms_at_beginning_of_tick = System.currentTimeMillis();
/*      */     
/*  723 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  725 */     for (int i = 0; i < this.worldServers.length; i++) {
/*      */       
/*  727 */       sb.append(this.worldServers[i].getWorldVec3Pool().getPoolSize());
/*      */       
/*  729 */       if (i < this.worldServers.length - 1) {
/*  730 */         sb.append(" | ");
/*      */       }
/*      */     } 
/*  733 */     Minecraft.server_pools_string = sb.toString();
/*      */     
/*  735 */     long var1 = System.nanoTime();
/*  736 */     AxisAlignedBB.getAABBPool().cleanPool();
/*  737 */     this.tickCounter++;
/*      */     
/*  739 */     if (this.startProfiling) {
/*      */       
/*  741 */       this.startProfiling = false;
/*  742 */       this.theProfiler.profilingEnabled = true;
/*  743 */       this.theProfiler.clearProfiling();
/*      */     } 
/*      */     
/*  746 */     this.theProfiler.startSection("root");
/*  747 */     updateTimeLightAndEntities();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  757 */     if (!treachery_detected && this.tickCounter % 900 == 0) {
/*      */       
/*  759 */       boolean abort = false;
/*      */       
/*  761 */       for (int j = 0; j < this.worldServers.length; j++) {
/*      */         
/*  763 */         if (this.worldServers[j] != null && this.worldServers[j].hasScheduledBlockChanges()) {
/*      */           
/*  765 */           abort = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  770 */       if (!abort) {
/*      */         
/*  772 */         this.theProfiler.startSection("save");
/*  773 */         this.serverConfigManager.saveAllPlayerData();
/*  774 */         saveAllWorlds(true, false);
/*  775 */         this.theProfiler.endSection();
/*      */       } 
/*      */     } 
/*      */     
/*  779 */     this.theProfiler.startSection("tallying");
/*  780 */     this.tickTimeArray[this.tickCounter % 100] = System.nanoTime() - var1;
/*  781 */     this.sentPacketCountArray[this.tickCounter % 100] = Packet.sentID - this.lastSentPacketID;
/*  782 */     this.lastSentPacketID = Packet.sentID;
/*  783 */     this.sentPacketSizeArray[this.tickCounter % 100] = Packet.sentSize - this.lastSentPacketSize;
/*  784 */     this.lastSentPacketSize = Packet.sentSize;
/*  785 */     this.receivedPacketCountArray[this.tickCounter % 100] = Packet.receivedID - this.lastReceivedID;
/*  786 */     this.lastReceivedID = Packet.receivedID;
/*  787 */     this.receivedPacketSizeArray[this.tickCounter % 100] = Packet.receivedSize - this.lastReceivedSize;
/*  788 */     this.lastReceivedSize = Packet.receivedSize;
/*  789 */     this.theProfiler.endSection();
/*  790 */     this.theProfiler.startSection("snooper");
/*      */     
/*  792 */     if (!this.usageSnooper.isSnooperRunning() && this.tickCounter > 100)
/*      */     {
/*  794 */       this.usageSnooper.startSnooper();
/*      */     }
/*      */     
/*  797 */     if (this.tickCounter % 6000 == 0)
/*      */     {
/*  799 */       this.usageSnooper.addMemoryStatsToSnooper();
/*      */     }
/*      */     
/*  802 */     this.theProfiler.endSection();
/*  803 */     this.theProfiler.endSection();
/*      */ 
/*      */ 
/*      */     
/*  807 */     this.ms_taken_for_last_100_ticks = (int)(this.ms_taken_for_last_100_ticks + System.currentTimeMillis() - system_ms_at_beginning_of_tick);
/*      */     
/*  809 */     if (this.tickCounter >= 100) {
/*      */       
/*  811 */       int avg_ms_per_tick = getAverageTickTime();
/*      */       
/*  813 */       this.ms_taken_for_last_100_ticks -= avg_ms_per_tick;
/*      */ 
/*      */ 
/*      */       
/*  817 */       if (this.tickCounter % 20 == 0 && getLoadOnServer() >= 1.0F) {
/*  818 */         this.serverConfigManager.sendPacketToAllPlayers((Packet)new Packet85SimpleSignal(EnumSignal.cpu_overburdened));
/*      */       }
/*      */     } 
/*  821 */     if (DebugAttack.instance != null) {
/*  822 */       DebugAttack.flush();
/*      */     }
/*      */   }
/*      */   
/*      */   public int getAverageTickTime() {
/*  827 */     return this.ms_taken_for_last_100_ticks / 100;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getLoadOnServer() {
/*  833 */     return (this.tickCounter < 100) ? -1.0F : (this.ms_taken_for_last_100_ticks / 5000.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendWorldAgesToAllClientsInAllDimensions() {
/*  839 */     if (Packet92UpdateTimeSmall.areAllWorldTotalTimesSuitable(this.worldServers)) {
/*  840 */       this.serverConfigManager.sendPacketToAllPlayers((Packet)new Packet92UpdateTimeSmall(this));
/*      */     } else {
/*  842 */       this.serverConfigManager.sendPacketToAllPlayers((Packet)new Packet4UpdateTime(this));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendWorldAgesToClient(ServerPlayer player) {
/*  857 */     if (Packet92UpdateTimeSmall.areAllWorldTotalTimesSuitable(this.worldServers)) {
/*  858 */       player.sendPacket((Packet)new Packet92UpdateTimeSmall(this));
/*      */     } else {
/*  860 */       player.sendPacket((Packet)new Packet4UpdateTime(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void updateTimeLightAndEntities() {
/*  865 */     this.theProfiler.startSection("levels");
/*      */ 
/*      */     
/*  868 */     if (this.tickCounter % 20 == 0)
/*  869 */       sendWorldAgesToAllClientsInAllDimensions(); 
/*      */     int var1;
/*  871 */     for (var1 = 0; var1 < this.worldServers.length; var1++) {
/*      */       
/*  873 */       long var2 = System.nanoTime();
/*      */       
/*  875 */       if (var1 == 0 || getAllowNether()) {
/*      */         
/*  877 */         WorldServer var4 = this.worldServers[var1];
/*  878 */         this.theProfiler.startSection(var4.getWorldInfo().getWorldName());
/*  879 */         this.theProfiler.startSection("pools");
/*  880 */         var4.getWorldVec3Pool().clear();
/*  881 */         this.theProfiler.endSection();
/*      */ 
/*      */ 
/*      */         
/*  885 */         this.theProfiler.startSection("timeSync");
/*      */ 
/*      */ 
/*      */         
/*  889 */         this.theProfiler.endSection();
/*      */ 
/*      */         
/*  892 */         this.theProfiler.startSection("tick");
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  897 */           var4.tick();
/*      */         }
/*  899 */         catch (Throwable var8) {
/*      */           
/*  901 */           CrashReport var6 = CrashReport.makeCrashReport(var8, "Exception ticking world");
/*  902 */           var4.addWorldInfoToCrashReport(var6);
/*  903 */           throw new ReportedException(var6);
/*      */         } 
/*      */ 
/*      */         
/*      */         try {
/*  908 */           var4.updateEntities();
/*      */         }
/*  910 */         catch (Throwable var7) {
/*      */           
/*  912 */           CrashReport var6 = CrashReport.makeCrashReport(var7, "Exception ticking world entities");
/*  913 */           var4.addWorldInfoToCrashReport(var6);
/*  914 */           throw new ReportedException(var6);
/*      */         } 
/*      */         
/*  917 */         this.theProfiler.endSection();
/*  918 */         this.theProfiler.startSection("tracker");
/*  919 */         var4.getEntityTracker().updateTrackedEntities();
/*  920 */         this.theProfiler.endSection();
/*  921 */         this.theProfiler.endSection();
/*      */       } 
/*      */       
/*  924 */       this.timeOfLastDimensionTick[var1][this.tickCounter % 100] = System.nanoTime() - var2;
/*      */     } 
/*      */     
/*  927 */     this.theProfiler.endStartSection("connection");
/*  928 */     getNetworkThread().networkTick();
/*  929 */     this.theProfiler.endStartSection("players");
/*      */     
/*  931 */     this.serverConfigManager.sendPlayerInfoToAllPlayers(false);
/*  932 */     this.theProfiler.endStartSection("tickables");
/*      */     
/*  934 */     for (var1 = 0; var1 < this.tickables.size(); var1++)
/*      */     {
/*  936 */       ((IUpdatePlayerListBox)this.tickables.get(var1)).update();
/*      */     }
/*      */     
/*  939 */     this.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAllowNether() {
/*  944 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_82010_a(IUpdatePlayerListBox par1IUpdatePlayerListBox) {
/*  949 */     this.playersOnline.add(par1IUpdatePlayerListBox);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void main(String[] par0ArrayOfStr) {
/*  954 */     if (Minecraft.java_version_is_outdated) {
/*      */       
/*  956 */       System.out.println("Minecraft Is Too Easy requires Java 1.7 or later!");
/*      */       
/*      */       return;
/*      */     } 
/*  960 */     StatList.nopInit();
/*  961 */     ILogAgent var1 = null;
/*      */ 
/*      */     
/*      */     try {
/*  965 */       boolean var2 = !GraphicsEnvironment.isHeadless();
/*  966 */       String var3 = null;
/*  967 */       String var4 = ".";
/*  968 */       String var5 = null;
/*  969 */       boolean var6 = false;
/*  970 */       boolean var7 = false;
/*  971 */       int var8 = -1;
/*      */       
/*  973 */       for (int var9 = 0; var9 < par0ArrayOfStr.length; var9++) {
/*      */         
/*  975 */         String var10 = par0ArrayOfStr[var9];
/*  976 */         String var11 = (var9 == par0ArrayOfStr.length - 1) ? null : par0ArrayOfStr[var9 + 1];
/*  977 */         boolean var12 = false;
/*      */         
/*  979 */         if (!var10.equals("nogui") && !var10.equals("--nogui")) {
/*      */           
/*  981 */           if (var10.equals("--port") && var11 != null)
/*      */           {
/*  983 */             var12 = true;
/*      */ 
/*      */             
/*      */             try {
/*  987 */               var8 = Integer.parseInt(var11);
/*      */             }
/*  989 */             catch (NumberFormatException var14) {}
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*  994 */           else if (var10.equals("--singleplayer") && var11 != null)
/*      */           {
/*  996 */             var12 = true;
/*  997 */             var3 = var11;
/*      */           }
/*  999 */           else if (var10.equals("--universe") && var11 != null)
/*      */           {
/* 1001 */             var12 = true;
/* 1002 */             var4 = var11;
/*      */           }
/* 1004 */           else if (var10.equals("--world") && var11 != null)
/*      */           {
/* 1006 */             var12 = true;
/* 1007 */             var5 = var11;
/*      */           }
/* 1009 */           else if (var10.equals("--demo"))
/*      */           {
/* 1011 */             var6 = true;
/*      */           }
/* 1013 */           else if (var10.equals("--bonusChest"))
/*      */           {
/* 1015 */             var7 = true;
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1020 */           var2 = false;
/*      */         } 
/*      */         
/* 1023 */         if (var12)
/*      */         {
/* 1025 */           var9++;
/*      */         }
/*      */       } 
/*      */       
/* 1029 */       DedicatedServer var16 = new DedicatedServer(new File(var4));
/* 1030 */       var1 = var16.getLogAgent();
/*      */       
/* 1032 */       if (var3 != null)
/*      */       {
/* 1034 */         var16.setServerOwner(var3);
/*      */       }
/*      */       
/* 1037 */       if (var5 != null)
/*      */       {
/* 1039 */         var16.setFolderName(var5);
/*      */       }
/*      */       
/* 1042 */       if (var8 >= 0)
/*      */       {
/* 1044 */         var16.setServerPort(var8);
/*      */       }
/*      */       
/* 1047 */       if (var6)
/*      */       {
/* 1049 */         var16.setDemo(true);
/*      */       }
/*      */       
/* 1052 */       if (var7)
/*      */       {
/* 1054 */         var16.canCreateBonusChest(true);
/*      */       }
/*      */       
/* 1057 */       if (var2)
/*      */       {
/* 1059 */         var16.func_120011_ar();
/*      */       }
/*      */       
/* 1062 */       var16.startServerThread();
/* 1063 */       Runtime.getRuntime().addShutdownHook((Thread)new ThreadDedicatedServerMITE(var16));
/*      */     }
/* 1065 */     catch (Exception var15) {
/*      */       
/* 1067 */       if (var1 != null) {
/*      */         
/* 1069 */         var1.logSevereException("Failed to start the minecraft server", var15);
/*      */       }
/*      */       else {
/*      */         
/* 1073 */         Logger.getAnonymousLogger().log(Level.SEVERE, "Failed to start the minecraft server", var15);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startServerThread() {
/* 1084 */     this.thread.start();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public File getFile(String par1Str) {
/* 1092 */     return new File(getDataDirectory(), par1Str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void logInfo(String par1Str) {
/* 1100 */     getLogAgent().logInfo(par1Str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void logWarning(String par1Str) {
/* 1108 */     getLogAgent().logWarning(par1Str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WorldServer worldServerForDimension(int par1) {
/* 1117 */     return this.worldServers[getWorldIndexForDimensionId(par1)];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getHostname() {
/* 1125 */     return this.hostname;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPort() {
/* 1133 */     return this.serverPort;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getServerMOTD() {
/* 1142 */     return getMOTD();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMinecraftVersion() {
/* 1150 */     return "1.6.4";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentPlayerCount() {
/* 1158 */     return this.serverConfigManager.getCurrentPlayerCount();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxPlayers() {
/* 1166 */     return this.serverConfigManager.getMaxPlayers();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getAllUsernames() {
/* 1174 */     return this.serverConfigManager.getAllUsernames();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPlugins() {
/* 1182 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String executeCommand(String par1Str, boolean permission_override) {
/* 1194 */     RConConsoleSource.consoleBuffer.resetLog();
/* 1195 */     this.commandManager.executeCommand((ICommandSender)RConConsoleSource.consoleBuffer, par1Str, permission_override);
/* 1196 */     return RConConsoleSource.consoleBuffer.getChatBuffer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDebuggingEnabled() {
/* 1205 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void logSevere(String par1Str) {
/* 1213 */     getLogAgent().logSevere(par1Str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void logDebug(String par1Str) {
/* 1221 */     if (isDebuggingEnabled())
/*      */     {
/* 1223 */       getLogAgent().logInfo(par1Str);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public String getServerModName() {
/* 1229 */     return "vanilla";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CrashReport addServerInfoToCrashReport(CrashReport par1CrashReport) {
/* 1237 */     par1CrashReport.getCategory().addCrashSectionCallable("Profiler Position", (Callable)new CallableIsServerModded(this));
/*      */     
/* 1239 */     if (this.worldServers != null && this.worldServers.length > 0 && this.worldServers[0] != null)
/*      */     {
/* 1241 */       par1CrashReport.getCategory().addCrashSectionCallable("Vec3 Pool Size", (Callable)new CallableServerProfiler(this));
/*      */     }
/*      */     
/* 1244 */     if (this.serverConfigManager != null)
/*      */     {
/* 1246 */       par1CrashReport.getCategory().addCrashSectionCallable("Player Count", (Callable)new CallableServerMemoryStats(this));
/*      */     }
/*      */     
/* 1249 */     return par1CrashReport;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getPossibleCompletions(ICommandSender par1ICommandSender, String par2Str) {
/* 1257 */     ArrayList<String> var3 = new ArrayList();
/*      */     
/* 1259 */     if (par2Str.startsWith("/")) {
/*      */       
/* 1261 */       par2Str = par2Str.substring(1);
/* 1262 */       boolean var10 = !par2Str.contains(" ");
/* 1263 */       List var11 = this.commandManager.getPossibleCommands(par1ICommandSender, par2Str);
/*      */       
/* 1265 */       if (var11 != null) {
/*      */         
/* 1267 */         Iterator<String> var12 = var11.iterator();
/*      */         
/* 1269 */         while (var12.hasNext()) {
/*      */           
/* 1271 */           String var13 = var12.next();
/*      */           
/* 1273 */           if (var10) {
/*      */             
/* 1275 */             var3.add("/" + var13);
/*      */             
/*      */             continue;
/*      */           } 
/* 1279 */           var3.add(var13);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1284 */       return var3;
/*      */     } 
/*      */ 
/*      */     
/* 1288 */     String[] var4 = par2Str.split(" ", -1);
/* 1289 */     String var5 = var4[var4.length - 1];
/* 1290 */     String[] var6 = this.serverConfigManager.getAllUsernames();
/* 1291 */     int var7 = var6.length;
/*      */     
/* 1293 */     for (int var8 = 0; var8 < var7; var8++) {
/*      */       
/* 1295 */       String var9 = var6[var8];
/*      */       
/* 1297 */       if (CommandBase.doesStringStartWith(var5, var9))
/*      */       {
/* 1299 */         var3.add(var9);
/*      */       }
/*      */     } 
/*      */     
/* 1303 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static MinecraftServer getServer() {
/* 1312 */     return mcServer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCommandSenderName() {
/* 1320 */     return "Server";
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendChatToPlayer(ChatMessageComponent par1ChatMessageComponent) {
/* 1325 */     getLogAgent().logInfo(par1ChatMessageComponent.toString());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCommandSenderUseCommand(int par1, String par2Str) {
/* 1333 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public ICommandManager getCommandManager() {
/* 1338 */     return this.commandManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public KeyPair getKeyPair() {
/* 1346 */     return this.serverKeyPair;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getServerPort() {
/* 1354 */     return this.serverPort;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setServerPort(int par1) {
/* 1359 */     this.serverPort = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getServerOwner() {
/* 1367 */     return this.serverOwner;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setServerOwner(String par1Str) {
/* 1375 */     this.serverOwner = par1Str;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSinglePlayer() {
/* 1380 */     return (this.serverOwner != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getFolderName() {
/* 1385 */     return this.folderName;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFolderName(String par1Str) {
/* 1390 */     this.folderName = par1Str;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWorldName(String par1Str) {
/* 1395 */     this.worldName = par1Str;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getWorldName() {
/* 1400 */     return this.worldName;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setKeyPair(KeyPair par1KeyPair) {
/* 1405 */     this.serverKeyPair = par1KeyPair;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDifficultyForAllWorlds(int par1) {
/* 1410 */     for (int var2 = 0; var2 < this.worldServers.length; var2++) {
/*      */       
/* 1412 */       WorldServer var3 = this.worldServers[var2];
/*      */       
/* 1414 */       if (var3 != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1432 */         var3.difficultySetting = 3;
/* 1433 */         var3.setAllowedSpawnTypes(true, true);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean allowSpawnMonsters() {
/* 1440 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDemo() {
/* 1448 */     return this.isDemo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDemo(boolean par1) {
/* 1456 */     this.isDemo = par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void canCreateBonusChest(boolean par1) {
/* 1461 */     this.enableBonusChest = par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public ISaveFormat getActiveAnvilConverter() {
/* 1466 */     return this.anvilConverterForAnvilFile;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteWorldAndStopServer() {
/* 1475 */     this.worldIsBeingDeleted = true;
/* 1476 */     getActiveAnvilConverter().flushCache();
/*      */     
/* 1478 */     for (int var1 = 0; var1 < this.worldServers.length; var1++) {
/*      */       
/* 1480 */       WorldServer var2 = this.worldServers[var1];
/*      */       
/* 1482 */       if (var2 != null)
/*      */       {
/* 1484 */         var2.flush();
/*      */       }
/*      */     } 
/*      */     
/* 1488 */     getActiveAnvilConverter().deleteWorldDirectory(this.worldServers[0].getSaveHandler().getWorldDirectoryName());
/* 1489 */     initiateShutdown();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getTexturePack() {
/* 1494 */     return this.texturePack; }
/*      */   public void addServerStatsToSnooper(PlayerUsageSnooper par1PlayerUsageSnooper) { par1PlayerUsageSnooper.addData("whitelist_enabled", Boolean.valueOf(false)); par1PlayerUsageSnooper.addData("whitelist_count", Integer.valueOf(0)); par1PlayerUsageSnooper.addData("players_current", Integer.valueOf(getCurrentPlayerCount())); par1PlayerUsageSnooper.addData("players_max", Integer.valueOf(getMaxPlayers())); par1PlayerUsageSnooper.addData("players_seen", Integer.valueOf((this.serverConfigManager.getAvailablePlayerDat()).length)); par1PlayerUsageSnooper.addData("uses_auth", Boolean.valueOf(this.onlineMode)); par1PlayerUsageSnooper.addData("gui_state", getGuiEnabled() ? "enabled" : "disabled"); par1PlayerUsageSnooper.addData("run_time", Long.valueOf((getSystemTimeMillis() - par1PlayerUsageSnooper.func_130105_g()) / 60L * 1000L)); par1PlayerUsageSnooper.addData("avg_tick_ms", Integer.valueOf((int)(MathHelper.average(this.tickTimeArray) * 1.0E-6D))); par1PlayerUsageSnooper.addData("avg_sent_packet_count", Integer.valueOf((int)MathHelper.average(this.sentPacketCountArray))); par1PlayerUsageSnooper.addData("avg_sent_packet_size", Integer.valueOf((int)MathHelper.average(this.sentPacketSizeArray))); par1PlayerUsageSnooper.addData("avg_rec_packet_count", Integer.valueOf((int)MathHelper.average(this.receivedPacketCountArray))); par1PlayerUsageSnooper.addData("avg_rec_packet_size", Integer.valueOf((int)MathHelper.average(this.receivedPacketSizeArray))); int var2 = 0; for (int var3 = 0; var3 < this.worldServers.length; var3++) { if (this.worldServers[var3] != null) { WorldServer var4 = this.worldServers[var3]; WorldInfo var5 = var4.getWorldInfo(); par1PlayerUsageSnooper.addData("world[" + var2 + "][dimension]", Integer.valueOf(var4.provider.dimensionId)); par1PlayerUsageSnooper.addData("world[" + var2 + "][mode]", var5.getGameType()); par1PlayerUsageSnooper.addData("world[" + var2 + "][difficulty]", Integer.valueOf(var4.difficultySetting)); par1PlayerUsageSnooper.addData("world[" + var2 + "][hardcore]", Boolean.valueOf(var5.isHardcoreModeEnabled())); par1PlayerUsageSnooper.addData("world[" + var2 + "][generator_name]", var5.getTerrainType().getWorldTypeName()); par1PlayerUsageSnooper.addData("world[" + var2 + "][generator_version]", Integer.valueOf(var5.getTerrainType().getGeneratorVersion())); par1PlayerUsageSnooper.addData("world[" + var2 + "][height]", Integer.valueOf(this.buildLimit)); par1PlayerUsageSnooper.addData("world[" + var2 + "][chunks_loaded]", Integer.valueOf(var4.getChunkProvider().getLoadedChunkCount())); var2++; }  }
/*      */      par1PlayerUsageSnooper.addData("worlds", Integer.valueOf(var2)); }
/*      */   public void addServerTypeToSnooper(PlayerUsageSnooper par1PlayerUsageSnooper) { par1PlayerUsageSnooper.addData("singleplayer", Boolean.valueOf(isSinglePlayer())); par1PlayerUsageSnooper.addData("server_brand", getServerModName()); par1PlayerUsageSnooper.addData("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported"); par1PlayerUsageSnooper.addData("dedicated", Boolean.valueOf(isDedicatedServer())); }
/*      */   public boolean isSnooperEnabled() { return true; }
/* 1499 */   public int textureSize() { return 16; } public void setTexturePack(String par1Str) { this.texturePack = par1Str; }
/*      */   public boolean isServerInOnlineMode() { return this.onlineMode; }
/*      */   public void setOnlineMode(boolean par1) { this.onlineMode = par1; }
/*      */   public boolean getCanSpawnAnimals() { return this.canSpawnAnimals; }
/*      */   public void setCanSpawnAnimals(boolean par1) { this.canSpawnAnimals = par1; } static {
/* 1504 */     TextureManager.unloadTextures();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1741 */     Entity.resetEntityIds(); } public boolean getCanSpawnNPCs() { return this.canSpawnNPCs; }
/*      */   public void setCanSpawnNPCs(boolean par1) { this.canSpawnNPCs = par1; }
/*      */   public boolean isPVPEnabled() { return this.pvpEnabled; }
/*      */   public void setAllowPvp(boolean par1) { this.pvpEnabled = par1; }
/*      */   public boolean isFlightAllowed() { return this.allowFlight; }
/*      */   public void setAllowFlight(boolean par1) { this.allowFlight = par1; }
/*      */   public String getMOTD() { if (Minecraft.isInTournamentMode())
/*      */       return "1.6.4-MITE Tournament Server " + getEntityWorld().getHourOfDayAMPM();  if (mcServer.isDedicatedServer())
/*      */       return this.motd + " (" + getEntityWorld().getHourOfDayAMPM() + ")";  return this.motd; }
/* 1750 */   public void setForceGamemode(boolean par1) { this.isGamemodeForced = par1; } public void setMOTD(String par1Str) { this.motd = par1Str; } public int getBuildLimit() { return this.buildLimit; } public void setBuildLimit(int par1) { this.buildLimit = par1; } public boolean isServerStopped() { return this.serverStopped; }
/*      */   public ServerConfigurationManager getConfigurationManager() { return this.serverConfigManager; }
/*      */   public void setConfigurationManager(ServerConfigurationManager par1ServerConfigurationManager) { this.serverConfigManager = par1ServerConfigurationManager; }
/*      */   public void setGameType(EnumGameType par1EnumGameType) { for (int var2 = 0; var2 < this.worldServers.length; var2++) (getServer()).worldServers[var2].getWorldInfo().setGameType(par1EnumGameType);  }
/*      */   public boolean serverIsInRunLoop() { return this.serverIsRunning; }
/* 1755 */   public boolean getForceGamemode() { return this.isGamemodeForced; } public boolean getGuiEnabled() { return false; } public int getTickCounter() { return this.tickCounter; } public void enableProfiling() { this.startProfiling = true; } public PlayerUsageSnooper getPlayerUsageSnooper() { return this.usageSnooper; }
/*      */   public ChunkCoordinates getPlayerCoordinates() { return new ChunkCoordinates(0, 0, 0); }
/*      */   public World getEntityWorld() { return (World)this.worldServers[0]; }
/*      */   public int getSpawnProtectionSize() { return 16; }
/*      */   public boolean isBlockProtected(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) { return false; }
/* 1760 */   public Proxy getServerProxy() { return this.serverProxy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long getSystemTimeMillis() {
/* 1769 */     return System.currentTimeMillis();
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_143007_ar() {
/* 1774 */     return this.field_143008_E;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_143006_e(int par1) {
/* 1779 */     this.field_143008_E = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ServerConfigurationManager getServerConfigurationManager(MinecraftServer par0MinecraftServer) {
/* 1787 */     return par0MinecraftServer.serverConfigManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sendPacketToAllPlayersOnServer(Packet packet) {
/* 1792 */     Iterator<ServerPlayer> i = (mcServer.getConfigurationManager()).playerEntityList.iterator();
/*      */     
/* 1794 */     while (i.hasNext()) {
/*      */       
/* 1796 */       ServerPlayer player = i.next();
/*      */       
/* 1798 */       player.playerNetServerHandler.sendPacketToPlayer(packet);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void playerLoggedIn(ServerPlayer par1EntityPlayerMP) {}
/*      */ 
/*      */   
/*      */   public void playerLoggedOut(ServerPlayer par1EntityPlayerMP) {}
/*      */   
/*      */   public void saveWorldMaps() {
/* 1809 */     if (treachery_detected) {
/*      */       return;
/*      */     }
/* 1812 */     getLogAgent().logInfo("Saving world maps...");
/*      */     
/* 1814 */     for (int i = 0; i < this.worldServers.length; i++) {
/*      */       
/* 1816 */       WorldServer world = this.worldServers[i];
/*      */       
/* 1818 */       if (world.world_map != null) {
/* 1819 */         world.world_map.writeToFile();
/*      */       }
/*      */     } 
/* 1822 */     getLogAgent().logInfo("Finished saving world maps");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setTreacheryDetected() {
/* 1827 */     if (treachery_detected) {
/*      */       return;
/*      */     }
/* 1830 */     treachery_detected = true;
/*      */     
/* 1832 */     if (treachery_shutdown_counter == 0) {
/* 1833 */       treachery_shutdown_counter = 400;
/*      */     }
/*      */   }
/*      */   
/*      */   public static void clearTreacheryDetected() {
/* 1838 */     treachery_detected = false;
/* 1839 */     treachery_shutdown_counter = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isPlayerHostingGame(EntityPlayer player) {
/* 1844 */     if (Minecraft.theMinecraft == null || Minecraft.theMinecraft.thePlayer == null) {
/* 1845 */       return false;
/*      */     }
/* 1847 */     return (Minecraft.theMinecraft.thePlayer == player || Minecraft.theMinecraft.thePlayer.entityId == player.entityId);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isZevimrgvOnServer() {
/* 1853 */     return getConfigurationManager().isZevimrgvOnServer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addTotalTimeForAllWorlds(int time_to_add) {
/* 1878 */     for (int i = 0; i < this.worldServers.length; i++) {
/*      */       
/* 1880 */       WorldServer world = this.worldServers[i];
/*      */       
/* 1882 */       long new_total_time = world.getTotalWorldTime() + time_to_add;
/*      */       
/* 1884 */       if (new_total_time < 0L) {
/* 1885 */         new_total_time = 0L;
/*      */       }
/* 1887 */       int new_time_of_day = (int)(new_total_time % 24000L);
/*      */ 
/*      */       
/* 1890 */       world.setTotalWorldTime(new_total_time);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1897 */     sendWorldAgesToAllClientsInAllDimensions();
/*      */   }
/*      */ 
/*      */   
/*      */   public String scramble(String s) {
/* 1902 */     char[] chars = s.toCharArray();
/*      */     
/* 1904 */     for (int i = 0; i < chars.length; i++) {
/*      */       
/* 1906 */       int c = chars[i];
/*      */       
/* 1908 */       if (c >= 65 && c <= 90) {
/* 1909 */         c = 90 - c - 65;
/* 1910 */       } else if (c >= 97 && c <= 122) {
/* 1911 */         c = 122 - c - 97;
/* 1912 */       } else if (c >= 48 && c <= 57) {
/* 1913 */         c = 57 - c - 48;
/*      */       } 
/* 1915 */       chars[i] = (char)c;
/*      */     } 
/*      */     
/* 1918 */     return new String(chars);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean hasPlayers(boolean must_be_alive, boolean must_not_be_ghosts) {
/* 1941 */     Iterator<ServerPlayer> i = (getConfigurationManager()).playerEntityList.iterator();
/*      */     
/* 1943 */     while (i.hasNext()) {
/*      */       
/* 1945 */       ServerPlayer player = i.next();
/*      */       
/* 1947 */       if (must_be_alive && player.getHealth() <= 0.0F) {
/*      */         continue;
/*      */       }
/* 1950 */       if (must_not_be_ghosts && (player.isGhost() || player.isZevimrgvInTournament())) {
/*      */         continue;
/*      */       }
/* 1953 */       return true;
/*      */     } 
/*      */     
/* 1956 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean hasNoPlayersOfAnyKindConnected() {
/* 1962 */     return (getConfigurationManager()).playerEntityList.isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean hasPlayersOfAnyKindConnected() {
/* 1968 */     return !hasNoPlayersOfAnyKindConnected();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean hasNonGhostPlayersConnected(boolean must_be_alive) {
/* 1974 */     return hasPlayers(must_be_alive, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean hasOnlyGhostPlayersConnected() {
/* 1980 */     return (hasPlayersOfAnyKindConnected() && !hasNonGhostPlayersConnected(false));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean hasOnlyGhostsOrDeadPlayersConnected() {
/* 1986 */     return (hasPlayersOfAnyKindConnected() && !hasNonGhostPlayersConnected(true));
/*      */   }
/*      */ 
/*      */   
/*      */   public final WorldServer getOverworld() {
/* 1991 */     return this.worldServers[0];
/*      */   }
/*      */ 
/*      */   
/*      */   public final WorldServer getUnderworld() {
/* 1996 */     return this.worldServers[3];
/*      */   }
/*      */ 
/*      */   
/*      */   public final WorldServer getNether() {
/* 2001 */     return this.worldServers[1];
/*      */   }
/*      */ 
/*      */   
/*      */   public final WorldServer getTheEnd() {
/* 2006 */     return this.worldServers[2];
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getVillageConditions() {
/* 2011 */     return getOverworld().getWorldInfo().getVillageConditions();
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getWorldIndexForDimensionId(int dimension_id) {
/* 2016 */     if (dimension_id == 0) {
/* 2017 */       return 0;
/*      */     }
/* 2019 */     if (dimension_id == -2) {
/* 2020 */       return 3;
/*      */     }
/* 2022 */     if (dimension_id == -1) {
/* 2023 */       return 1;
/*      */     }
/* 2025 */     if (dimension_id == 1) {
/* 2026 */       return 2;
/*      */     }
/* 2028 */     Minecraft.setErrorMessage("getWorldIndexForDimensionId: unable to map dimension id " + dimension_id + " to a world index");
/*      */     
/* 2030 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getWorldDimensionIdFromIndex(int index) {
/* 2035 */     if (index == 0) {
/* 2036 */       return 0;
/*      */     }
/* 2038 */     if (index == 3) {
/* 2039 */       return -2;
/*      */     }
/* 2041 */     if (index == 1) {
/* 2042 */       return -1;
/*      */     }
/* 2044 */     if (index == 2) {
/* 2045 */       return 1;
/*      */     }
/* 2047 */     Minecraft.setErrorMessage("getWorldDimensionIdFromIndex: unable to map index " + index + " to a dimension id");
/*      */     
/* 2049 */     return 0;
/*      */   }
/*      */   
/*      */   protected abstract boolean startServer() throws IOException;
/*      */   
/*      */   public abstract boolean canStructuresSpawn();
/*      */   
/*      */   public abstract EnumGameType getGameType();
/*      */   
/*      */   public abstract int getDifficulty();
/*      */   
/*      */   public abstract boolean isHardcore();
/*      */   
/*      */   public abstract int func_110455_j();
/*      */   
/*      */   public abstract boolean areSkillsEnabled();
/*      */   
/*      */   public abstract boolean isDedicatedServer();
/*      */   
/*      */   public abstract boolean isCommandBlockEnabled();
/*      */   
/*      */   public abstract NetworkListenThread getNetworkThread();
/*      */   
/*      */   public abstract String shareToLAN(EnumGameType paramEnumGameType, boolean paramBoolean);
/*      */   
/*      */   public abstract ILogAgent getLogAgent();
/*      */   
/*      */   public abstract ILogAgent getAuxLogAgent();
/*      */   
/*      */   public abstract boolean isServerSideMappingEnabled();
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\server\MinecraftServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */