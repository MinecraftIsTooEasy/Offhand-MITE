/*      */ package net.minecraft;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileReader;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.net.InetAddress;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ 
/*      */ public final class DedicatedServer
/*      */   extends MinecraftServer
/*      */   implements IServer
/*      */ {
/*   22 */   private final List pendingCommandList = Collections.synchronizedList(new ArrayList());
/*      */   
/*      */   private final ILogAgent field_98131_l;
/*      */   
/*      */   private RConThreadQuery theRConThreadQuery;
/*      */   
/*      */   private RConThreadMain theRConThreadMain;
/*      */   
/*      */   private PropertyManager settings;
/*      */   
/*      */   private boolean canSpawnStructures;
/*      */   
/*      */   private EnumGameType gameType;
/*      */   
/*      */   private NetworkListenThread networkThread;
/*      */   private boolean guiIsEnabled;
/*      */   private final ILogAgent achievements_log;
/*      */   private final ILogAgent performance_log;
/*      */   public static int shutdown_counter;
/*      */   public static EnumTournamentType tournament_type;
/*      */   public static String tournament_start_time;
/*      */   public static String tournament_notice_append;
/*      */   public static boolean tournament_won;
/*      */   public static DedicatedServer theDedicatedServer;
/*   46 */   public static List soonest_reconnection_times = new ArrayList();
/*      */ 
/*      */   
/*      */   public static boolean disconnection_penalty_enabled = true;
/*      */   
/*      */   private boolean server_side_mapping_enabled;
/*      */   
/*   53 */   private static HashMap tournament_standings = new HashMap<Object, Object>();
/*   54 */   public static HashMap players_kicked_for_depleted_time_shares = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int allotted_time;
/*      */ 
/*      */ 
/*      */   
/*   63 */   private static int required_pyramid_height = 20;
/*      */   
/*   65 */   private static String newline = new String(System.getProperty("line.separator").getBytes());
/*      */ 
/*      */   
/*      */   private final ILogAgent aux_log;
/*      */ 
/*      */   
/*      */   private static boolean are_skills_enabled_in_settings_file;
/*      */ 
/*      */   
/*      */   public DedicatedServer(File par1File) {
/*   75 */     super(par1File);
/*   76 */     theDedicatedServer = this;
/*   77 */     this.field_98131_l = new LogAgent("Minecraft-Server", (String)null, (new File(par1File, "server.log")).getAbsolutePath());
/*   78 */     this.achievements_log = new LogAgent("Achievements-Log", (String)null, (new File(par1File, "achievements.log")).getAbsolutePath());
/*   79 */     this.performance_log = new LogAgent("Performance-Log", (String)null, (new File(par1File, "performance.log")).getAbsolutePath());
/*   80 */     this.aux_log = new LogAgent(StringHelper.mirrorString("Hfhkrxrlfh-Olt"), (String)null, (new File(par1File, StringHelper.mirrorString("hfhkrxrlfh.olt"))).getAbsolutePath());
/*   81 */     new DedicatedServerSleepThread(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean startServer() throws IOException {
/*   89 */     DedicatedServerCommandThread var1 = new DedicatedServerCommandThread(this);
/*   90 */     var1.setDaemon(true);
/*   91 */     var1.start();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   96 */     getLogAgent().logInfo("Starting minecraft server version 1.6.4-MITE-HDS (R196)" + (Minecraft.inDevMode() ? " DEV" : ""));
/*      */     
/*   98 */     String os_arch = System.getProperty("os.arch");
/*      */     
/*  100 */     if (os_arch != null) {
/*      */       
/*  102 */       int JVM_bits = os_arch.contains("128") ? 128 : (os_arch.contains("64") ? 64 : ((os_arch.contains("86") || os_arch.contains("32")) ? 32 : 0));
/*  103 */       getLogAgent().logInfo("Using " + ((JVM_bits == 0) ? os_arch : (JVM_bits + "-bit")) + " JVM" + ((JVM_bits == 32) ? " (64-bit is recommended)" : ""));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  108 */     if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L)
/*      */     {
/*  110 */       getLogAgent().logWarning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
/*      */     }
/*      */     
/*  113 */     getLogAgent().logInfo("Loading properties");
/*      */     
/*  115 */     this.settings = new PropertyManager(new File("server.properties"), getLogAgent(), "Minecraft server properties");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  123 */     String t_p = this.settings.getProperty(tE("glfimznvmg-kzhhdliw"));
/*      */     
/*  125 */     if (t_p != null)
/*      */     {
/*  127 */       if (t_p.equals(tE("alnyrvh"))) {
/*  128 */         setTournamentType(this.settings.getProperty("tournament-type"));
/*      */       } else {
/*  130 */         getLogAgent().logWarning(tE("Rmezorw glfimznvmg kzhhdliw!"));
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  148 */     if (this.settings.getProperty("allotted-time") == null) {
/*  149 */       allotted_time = 432000;
/*      */     } else {
/*  151 */       allotted_time = this.settings.getIntProperty("allotted-time", 432000);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  161 */     are_skills_enabled_in_settings_file = this.settings.getBooleanProperty("professions", false);
/*      */ 
/*      */     
/*  164 */     are_skills_enabled_in_settings_file = false;
/*      */     
/*  166 */     if (isSinglePlayer()) {
/*      */       
/*  168 */       setHostname("127.0.0.1");
/*      */     }
/*      */     else {
/*      */       
/*  172 */       setOnlineMode(this.settings.getBooleanProperty("online-mode", true));
/*  173 */       setHostname(this.settings.getProperty("server-ip", ""));
/*      */     } 
/*      */     
/*  176 */     setCanSpawnAnimals(this.settings.getBooleanProperty("spawn-animals", true));
/*  177 */     setCanSpawnNPCs(this.settings.getBooleanProperty("spawn-npcs", true));
/*      */     
/*  179 */     setAllowPvp(true);
/*  180 */     setAllowFlight(this.settings.getBooleanProperty("allow-flight", false));
/*  181 */     setTexturePack(this.settings.getProperty("texture-pack", ""));
/*      */ 
/*      */     
/*  184 */     setMOTD(this.settings.getProperty("motd", "A 1.6.4-MITE Server"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  191 */     setForceGamemode(this.settings.getBooleanProperty("force-gamemode", false));
/*  192 */     func_143006_e(this.settings.getIntProperty("player-idle-timeout", 0));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  203 */     this.settings.setProperty("difficulty", Integer.valueOf(3));
/*      */     
/*  205 */     this.canSpawnStructures = this.settings.getBooleanProperty("generate-structures", true);
/*  206 */     int var2 = this.settings.getIntProperty("gamemode", EnumGameType.SURVIVAL.getID());
/*      */     
/*  208 */     if (!Minecraft.inDevMode()) {
/*  209 */       var2 = EnumGameType.SURVIVAL.id;
/*      */     }
/*  211 */     this.gameType = WorldSettings.getGameTypeById(var2);
/*  212 */     getLogAgent().logInfo("Default game type: " + this.gameType);
/*  213 */     InetAddress var3 = null;
/*      */     
/*  215 */     if (getServerHostname().length() > 0)
/*      */     {
/*  217 */       var3 = InetAddress.getByName(getServerHostname());
/*      */     }
/*      */     
/*  220 */     if (getServerPort() < 0)
/*      */     {
/*  222 */       setServerPort(this.settings.getIntProperty("server-port", 25565));
/*      */     }
/*      */     
/*  225 */     getLogAgent().logInfo("Generating keypair");
/*  226 */     setKeyPair(CryptManager.createNewKeyPair());
/*  227 */     getLogAgent().logInfo("Starting Minecraft server on " + ((getServerHostname().length() == 0) ? "*" : getServerHostname()) + ":" + getServerPort());
/*      */ 
/*      */     
/*      */     try {
/*  231 */       this.networkThread = new DedicatedServerListenThread(this, var3, getServerPort());
/*      */     }
/*  233 */     catch (IOException var16) {
/*      */       
/*  235 */       getLogAgent().logWarning("**** FAILED TO BIND TO PORT!");
/*  236 */       getLogAgent().logWarningFormatted("The exception was: {0}", new Object[] { var16.toString() });
/*  237 */       getLogAgent().logWarning("Perhaps a server is already running on that port?");
/*  238 */       return false;
/*      */     } 
/*      */     
/*  241 */     if (!isServerInOnlineMode()) {
/*      */       
/*  243 */       getLogAgent().logWarning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
/*  244 */       getLogAgent().logWarning("The server will make no attempt to authenticate usernames. Beware.");
/*  245 */       getLogAgent().logWarning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
/*  246 */       getLogAgent().logWarning("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
/*      */     } 
/*      */     
/*  249 */     setConfigurationManager(new DedicatedPlayerList(this));
/*      */     
/*  251 */     if (getConfigurationManager().isWhiteListAllInclusive()) {
/*  252 */       getLogAgent().logInfo("White-list is ALL-INCLUSIVE");
/*  253 */     } else if (getConfigurationManager().getWhiteListedPlayers().size() == 0) {
/*  254 */       getLogAgent().logInfo("White-list contains NO USERNAMES!");
/*      */     } else {
/*  256 */       getLogAgent().logInfo("White-list contains " + getConfigurationManager().getWhiteListedPlayers().size() + " username(s)");
/*      */     } 
/*  258 */     long var4 = System.nanoTime();
/*      */     
/*  260 */     if (getFolderName() == null)
/*      */     {
/*  262 */       setFolderName(this.settings.getProperty("level-name", "world"));
/*      */     }
/*      */     
/*  265 */     String var6 = this.settings.getProperty("level-seed", "");
/*      */ 
/*      */     
/*  268 */     String var7 = "LARGEBIOMES";
/*  269 */     String var8 = this.settings.getProperty("generator-settings", "");
/*  270 */     long var9 = (new Random()).nextLong();
/*      */     
/*  272 */     if (var6.length() > 0) {
/*      */       
/*      */       try {
/*      */         
/*  276 */         long var11 = Long.parseLong(var6);
/*      */         
/*  278 */         if (var11 != 0L)
/*      */         {
/*  280 */           var9 = var11;
/*      */         }
/*      */       }
/*  283 */       catch (NumberFormatException var15) {
/*      */         
/*  285 */         var9 = var6.hashCode();
/*      */       } 
/*      */     }
/*      */     
/*  289 */     if (tournament_type == EnumTournamentType.open) {
/*  290 */       var9 = 2L;
/*      */     }
/*  292 */     WorldType var17 = WorldType.parseWorldType(var7);
/*      */     
/*  294 */     if (var17 == null)
/*      */     {
/*  296 */       var17 = WorldType.DEFAULT;
/*      */     }
/*      */     
/*  299 */     setBuildLimit(this.settings.getIntProperty("max-build-height", 256));
/*  300 */     setBuildLimit((getBuildLimit() + 8) / 16 * 16);
/*  301 */     setBuildLimit(MathHelper.clamp_int(getBuildLimit(), 64, 256));
/*  302 */     this.settings.setProperty("max-build-height", Integer.valueOf(getBuildLimit()));
/*  303 */     getLogAgent().logInfo("Preparing level \"" + getFolderName() + "\"");
/*  304 */     loadAllWorlds(getFolderName(), getFolderName(), var9, var17, var8);
/*  305 */     getLogAgent().logInfo("World seed is " + this.worldServers[0].getSeed());
/*  306 */     long var12 = System.nanoTime() - var4;
/*  307 */     String var14 = String.format("%.3fs", new Object[] { Double.valueOf(var12 / 1.0E9D) });
/*  308 */     getLogAgent().logInfo("Done (" + var14 + ")! For help, type \"help\" or \"?\"");
/*      */     
/*  310 */     if (this.settings.getBooleanProperty("enable-query", false)) {
/*      */       
/*  312 */       getLogAgent().logInfo("Starting GS4 status listener");
/*  313 */       this.theRConThreadQuery = new RConThreadQuery(this);
/*  314 */       this.theRConThreadQuery.startThread();
/*      */     } 
/*      */     
/*  317 */     if (this.settings.getBooleanProperty("enable-rcon", false)) {
/*      */       
/*  319 */       getLogAgent().logInfo("Starting remote control listener");
/*  320 */       this.theRConThreadMain = new RConThreadMain(this);
/*  321 */       this.theRConThreadMain.startThread();
/*      */     } 
/*      */     
/*  324 */     this.default_world_map_size = MathHelper.clamp_int(this.settings.getIntProperty("default-map-size", 4096), 256, 16384);
/*      */     
/*  326 */     if (isTournament())
/*      */     {
/*  328 */       this.default_world_map_size = getTournamentArenaRadius() * 2;
/*      */     }
/*  330 */     setServerSideMapping((tournament_type != EnumTournamentType.open && this.settings.getBooleanProperty("enable-mapping", true)));
/*      */     
/*  332 */     if (this.server_side_mapping_enabled) {
/*  333 */       getLogAgent().logInfo("Mapping enabled, default map size is " + this.default_world_map_size + "x" + this.default_world_map_size + " (" + (this.default_world_map_size * this.default_world_map_size * 4 / 1024 / 1024) + "MB of memory)");
/*      */     } else {
/*  335 */       getLogAgent().logInfo("Mapping disabled");
/*      */     } 
/*  337 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canStructuresSpawn() {
/*  342 */     return this.canSpawnStructures;
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumGameType getGameType() {
/*  347 */     if (!Minecraft.inDevMode()) {
/*  348 */       this.gameType = EnumGameType.SURVIVAL;
/*      */     }
/*  350 */     return this.gameType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDifficulty() {
/*  359 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHardcore() {
/*  367 */     return this.settings.getBooleanProperty("hardcore", false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void updatePlayersFile() {
/*  372 */     File file = new File("players");
/*      */ 
/*      */     
/*      */     try {
/*  376 */       FileWriter fw = new FileWriter(file);
/*      */       
/*  378 */       StringBuffer players = new StringBuffer();
/*      */       
/*  380 */       Iterator<ServerPlayer> i = (getConfigurationManager()).playerEntityList.iterator();
/*      */       
/*  382 */       while (i.hasNext()) {
/*      */         
/*  384 */         ServerPlayer player = i.next();
/*      */ 
/*      */         
/*  387 */         players.append(player.username);
/*      */         
/*  389 */         if (i.hasNext()) {
/*  390 */           players.append(newline);
/*      */         }
/*      */       } 
/*  393 */       fw.write(players.toString());
/*  394 */       fw.close();
/*      */     }
/*  396 */     catch (Exception e) {}
/*      */   }
/*      */ 
/*      */   
/*      */   public static void appendTournamentStandingsToFile(boolean server_is_shutting_down) {
/*  401 */     if (server_is_shutting_down) {
/*  402 */       getServer().getLogAgent().logInfo("Updating tournament standings file");
/*      */     }
/*  404 */     File file = new File("tournament_standings.txt");
/*      */     
/*      */     try {
/*      */       String header;
/*  408 */       FileWriter fw = new FileWriter(file, true);
/*      */       
/*  410 */       StringBuffer sb = new StringBuffer();
/*      */       
/*  412 */       if (server_is_shutting_down) {
/*  413 */         sb.append("*** Server Shutting Down ***" + newline + newline);
/*      */       }
/*  415 */       WorldServer world = getServer().worldServerForDimension(0);
/*      */       
/*  417 */       String AMPM = world.getHourOfDayAMPM();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  422 */       if (AMPM.equalsIgnoreCase("NOON")) {
/*  423 */         header = "Day " + world.getDayOfWorld() + ", Noon";
/*  424 */       } else if (AMPM.equalsIgnoreCase("MDNT")) {
/*  425 */         header = "End of Day " + (world.getDayOfWorld() - 1);
/*      */       } else {
/*  427 */         header = "Day " + world.getDayOfWorld() + ", " + world.getHourOfDayAMPM();
/*      */       } 
/*  429 */       sb.append(header + newline + StringHelper.repeat("-", header.length()) + newline);
/*      */       
/*  431 */       Set<String> usernames = tournament_standings.keySet();
/*      */       
/*  433 */       boolean is_avernite_in_list = false;
/*      */       
/*  435 */       Iterator<String> i = usernames.iterator();
/*      */       
/*  437 */       while (i.hasNext()) {
/*      */         
/*  439 */         if ("avernite".equals(i.next())) {
/*      */           
/*  441 */           is_avernite_in_list = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  446 */       if (usernames.size() == 0 || (usernames.size() == 1 && is_avernite_in_list)) {
/*      */         
/*  448 */         sb.append("(No players have joined yet)" + newline);
/*      */       }
/*      */       else {
/*      */         
/*  452 */         i = usernames.iterator();
/*      */         
/*  454 */         while (i.hasNext()) {
/*      */           
/*  456 */           String username = i.next();
/*      */ 
/*      */           
/*  459 */           if ("avernite".equals(username)) {
/*      */             continue;
/*      */           }
/*  462 */           TournamentStanding ts = (TournamentStanding)tournament_standings.get(username);
/*      */           
/*  464 */           sb.append(ts.toString(username) + newline);
/*      */         } 
/*      */       } 
/*      */       
/*  468 */       sb.append(newline);
/*  469 */       fw.append(sb.toString());
/*  470 */       fw.close();
/*      */     }
/*  472 */     catch (Exception e) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tick() {
/*  479 */     super.tick();
/*      */ 
/*      */     
/*  482 */     if (this.tickCounter % 1000 == 0) {
/*      */ 
/*      */       
/*  485 */       getConfigurationManager().loadWhiteList();
/*      */       
/*  487 */       updatePlayersFile();
/*      */       
/*  489 */       if (getConfigurationManager().getCurrentPlayerCount() == 0) {
/*  490 */         System.gc();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  532 */       logPerformance();
/*      */       
/*  534 */       if (tournament_type == EnumTournamentType.score) {
/*  535 */         appendTournamentStandingsToFile(false);
/*      */       }
/*      */     } 
/*  538 */     boolean shutting_down_immediately = false;
/*      */ 
/*      */ 
/*      */     
/*  542 */     if (tournament_type != null && tournament_type.time_limit_in_days > 0 && shutdown_counter == 0 && this.worldServers[0].getDayOfWorld() > tournament_type.time_limit_in_days) {
/*      */       
/*  544 */       shutdown_counter = 200;
/*  545 */       String msg = "The tournament is now over! Server is shutting down in 10 seconds.";
/*  546 */       getLogAgent().logInfo(msg);
/*  547 */       sendPacketToAllPlayersOnServer(new Packet3Chat(ChatMessageComponent.createFromText("Notice: " + msg).setColor(EnumChatFormatting.YELLOW)));
/*      */       
/*  549 */       tournament_won = true;
/*      */     } 
/*      */     
/*  552 */     if (this.tickCounter % 20 == 0) {
/*      */       
/*  554 */       String username_to_kick = null;
/*      */       
/*  556 */       File file = new File("kick.txt");
/*      */       
/*  558 */       if (file.exists()) {
/*      */ 
/*      */         
/*      */         try {
/*  562 */           BufferedReader bf = new BufferedReader(new FileReader(file));
/*  563 */           username_to_kick = bf.readLine();
/*  564 */           bf.close();
/*      */         }
/*  566 */         catch (Exception e) {}
/*      */         
/*  568 */         if (username_to_kick != null && username_to_kick.length() > 0) {
/*      */ 
/*      */           
/*  571 */           EntityPlayer player_to_kick = getConfigurationManager().getPlayerForUsername(username_to_kick);
/*      */           
/*  573 */           if (player_to_kick instanceof ServerPlayer) {
/*      */             
/*  575 */             (player_to_kick.getAsEntityPlayerMP()).playerNetServerHandler.kickPlayerFromServer("Kicked by administrator");
/*  576 */             file.delete();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  581 */       String file_signal = null;
/*      */       
/*  583 */       file = new File("signal.txt");
/*      */       
/*  585 */       if (file.exists()) {
/*      */ 
/*      */         
/*      */         try {
/*  589 */           BufferedReader bf = new BufferedReader(new FileReader(file));
/*  590 */           file_signal = bf.readLine();
/*  591 */           bf.close();
/*      */         }
/*  593 */         catch (Exception e) {}
/*      */         
/*  595 */         if (file_signal != null) {
/*      */           
/*  597 */           boolean signal_processed = true;
/*      */           
/*  599 */           if (file_signal.equals("shutdown")) {
/*      */             
/*  601 */             shutdown_counter = 400;
/*  602 */             String msg = "Server is shutting down in 20 seconds.";
/*  603 */             getLogAgent().logInfo(msg);
/*  604 */             sendPacketToAllPlayersOnServer(new Packet3Chat(ChatMessageComponent.createFromText("Notice: " + msg).setColor(EnumChatFormatting.YELLOW)));
/*      */           }
/*  606 */           else if (file_signal.equals("shutdown immediately")) {
/*      */             
/*  608 */             shutdown_counter = 1;
/*  609 */             getLogAgent().logInfo("Server is shutting down immediately.");
/*      */             
/*  611 */             shutting_down_immediately = true;
/*      */           }
/*  613 */           else if (file_signal.equals("shutdown for world backup")) {
/*      */             
/*  615 */             shutdown_counter = 400;
/*  616 */             String msg = "Server is shutting down for world backup in 20 seconds.";
/*  617 */             getLogAgent().logInfo(msg);
/*  618 */             sendPacketToAllPlayersOnServer(new Packet3Chat(ChatMessageComponent.createFromText("Notice: " + msg).setColor(EnumChatFormatting.YELLOW)));
/*      */           }
/*  620 */           else if (file_signal.equals("shutdown for update")) {
/*      */             
/*  622 */             shutdown_counter = 400;
/*  623 */             String msg = "Server is shutting down for update in 20 seconds.";
/*  624 */             getLogAgent().logInfo(msg);
/*  625 */             sendPacketToAllPlayersOnServer(new Packet3Chat(ChatMessageComponent.createFromText("Notice: " + msg).setColor(EnumChatFormatting.YELLOW)));
/*      */           }
/*      */           else {
/*      */             
/*  629 */             signal_processed = false;
/*      */           } 
/*      */           
/*  632 */           if (signal_processed) {
/*  633 */             file.delete();
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*  638 */     if (shutdown_counter > 0) {
/*      */       
/*  640 */       if (getCurrentPlayerCount() == 0) {
/*  641 */         shutdown_counter = 1;
/*      */       }
/*  643 */       if (--shutdown_counter == 0) {
/*      */         
/*  645 */         this.save_world_maps_on_shutdown = (this.server_side_mapping_enabled && !shutting_down_immediately);
/*  646 */         initiateShutdown();
/*      */         
/*      */         return;
/*      */       } 
/*  650 */       if (tournament_won && shutdown_counter == 400) {
/*  651 */         sendPacketToAllPlayersOnServer(new Packet3Chat(ChatMessageComponent.createFromText("Server will shutdown in 20 seconds.").setColor(EnumChatFormatting.YELLOW)));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void finalTick(CrashReport par1CrashReport) {
/*  660 */     while (isServerRunning()) {
/*      */       
/*  662 */       executePendingCommands();
/*      */ 
/*      */       
/*      */       try {
/*  666 */         Thread.sleep(10L);
/*      */       }
/*  668 */       catch (InterruptedException var3) {
/*      */         
/*  670 */         var3.printStackTrace();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CrashReport addServerInfoToCrashReport(CrashReport par1CrashReport) {
/*  680 */     par1CrashReport = super.addServerInfoToCrashReport(par1CrashReport);
/*  681 */     par1CrashReport.getCategory().addCrashSectionCallable("Is Modded", new CallableType(this));
/*  682 */     par1CrashReport.getCategory().addCrashSectionCallable("Type", new CallableServerType(this));
/*  683 */     return par1CrashReport;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void systemExitNow() {
/*  691 */     ThreadedFileIOBase.reportErrorIfNotFinished();
/*      */     
/*  693 */     System.exit(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateTimeLightAndEntities() {
/*  698 */     super.updateTimeLightAndEntities();
/*  699 */     executePendingCommands();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAllowNether() {
/*  704 */     return this.settings.getBooleanProperty("allow-nether", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean allowSpawnMonsters() {
/*  711 */     this.settings.getBooleanProperty("spawn-monsters", true);
/*  712 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   static {
/*  717 */     TextureManager.unloadTextures();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1448 */     Entity.resetEntityIds();
/*      */   }
/*      */   
/*      */   public void addServerStatsToSnooper(PlayerUsageSnooper par1PlayerUsageSnooper) {
/*      */     par1PlayerUsageSnooper.addData("whitelist_enabled", Boolean.valueOf(getDedicatedPlayerList().isWhiteListEnabled()));
/*      */     par1PlayerUsageSnooper.addData("whitelist_count", Integer.valueOf(getDedicatedPlayerList().getWhiteListedPlayers().size()));
/*      */     super.addServerStatsToSnooper(par1PlayerUsageSnooper);
/*      */   }
/*      */   
/*      */   public boolean isSnooperEnabled() {
/*      */     return this.settings.getBooleanProperty("snooper-enabled", true);
/*      */   }
/*      */   
/*      */   public void addPendingCommand(String par1Str, ICommandSender par2ICommandSender, boolean permission_override) {
/*      */     this.pendingCommandList.add(new ServerCommand(par1Str, par2ICommandSender, permission_override));
/*      */   }
/*      */   
/*      */   public void executePendingCommands() {
/*      */     while (!this.pendingCommandList.isEmpty()) {
/*      */       ServerCommand var1 = this.pendingCommandList.remove(0);
/*      */       getCommandManager().executeCommand(var1.sender, var1.command, var1.permission_override);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isDedicatedServer() {
/*      */     return true;
/*      */   }
/*      */   
/*      */   public DedicatedPlayerList getDedicatedPlayerList() {
/*      */     return (DedicatedPlayerList)super.getConfigurationManager();
/*      */   }
/*      */   
/*      */   public NetworkListenThread getNetworkThread() {
/*      */     return this.networkThread;
/*      */   }
/*      */   
/*      */   public int getIntProperty(String par1Str, int par2) {
/*      */     return this.settings.getIntProperty(par1Str, par2);
/*      */   }
/*      */   
/*      */   public String getStringProperty(String par1Str, String par2Str) {
/*      */     return this.settings.getProperty(par1Str, par2Str);
/*      */   }
/*      */   
/*      */   public boolean getBooleanProperty(String par1Str, boolean par2) {
/*      */     return this.settings.getBooleanProperty(par1Str, par2);
/*      */   }
/*      */   
/*      */   public void setProperty(String par1Str, Object par2Obj) {
/*      */     this.settings.setProperty(par1Str, par2Obj);
/*      */   }
/*      */   
/*      */   public void saveProperties() {
/*      */     this.settings.saveProperties();
/*      */   }
/*      */   
/*      */   public String getSettingsFilename() {
/*      */     File var1 = this.settings.getPropertiesFile();
/*      */     return (var1 != null) ? var1.getAbsolutePath() : "No settings file";
/*      */   }
/*      */   
/*      */   public void func_120011_ar() {
/*      */     MinecraftServerGuiMITE.func_120016_a(this);
/*      */     this.guiIsEnabled = true;
/*      */   }
/*      */   
/*      */   public boolean getGuiEnabled() {
/*      */     return this.guiIsEnabled;
/*      */   }
/*      */   
/*      */   public String shareToLAN(EnumGameType par1EnumGameType, boolean par2) {
/*      */     return "";
/*      */   }
/*      */   
/*      */   public boolean isCommandBlockEnabled() {
/*      */     return this.settings.getBooleanProperty("enable-command-block", false);
/*      */   }
/*      */   
/*      */   public int getSpawnProtectionSize() {
/*      */     return this.settings.getIntProperty("spawn-protection", super.getSpawnProtectionSize());
/*      */   }
/*      */   
/*      */   public boolean isBlockProtected(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
/*      */     if (par1World.provider.dimensionId != 0)
/*      */       return false; 
/*      */     if (getDedicatedPlayerList().getOps().isEmpty())
/*      */       return false; 
/*      */     if (getDedicatedPlayerList().isPlayerOpped(par5EntityPlayer.getCommandSenderName()))
/*      */       return false; 
/*      */     if (getSpawnProtectionSize() <= 0)
/*      */       return false; 
/*      */     ChunkCoordinates var6 = par1World.getSpawnPoint();
/*      */     int var7 = MathHelper.abs_int(par2 - var6.posX);
/*      */     int var8 = MathHelper.abs_int(par4 - var6.posZ);
/*      */     int var9 = Math.max(var7, var8);
/*      */     return (var9 <= getSpawnProtectionSize());
/*      */   }
/*      */   
/*      */   public ILogAgent getLogAgent() {
/*      */     return this.field_98131_l;
/*      */   }
/*      */   
/*      */   public ILogAgent getAuxLogAgent() {
/*      */     return this.aux_log;
/*      */   }
/*      */   
/*      */   public int func_110455_j() {
/*      */     return 0;
/*      */   }
/*      */   
/*      */   public boolean areSkillsEnabled() {
/*      */     return areSkillsEnabledInSettingsFile();
/*      */   }
/*      */   
/*      */   public void func_143006_e(int par1) {
/*      */     super.func_143006_e(par1);
/*      */     this.settings.setProperty("player-idle-timeout", Integer.valueOf(par1));
/*      */     saveProperties();
/*      */   }
/*      */   
/*      */   public ServerConfigurationManager getConfigurationManager() {
/*      */     return getDedicatedPlayerList();
/*      */   }
/*      */   
/*      */   public static boolean it() {
/*      */     return isTournament();
/*      */   }
/*      */   
/*      */   public void setTournamentType(String tournament_type) {
/*      */     if (tournament_type == null) {
/*      */       this;
/*      */       DedicatedServer.tournament_type = null;
/*      */       return;
/*      */     } 
/*      */     this;
/*      */     DedicatedServer.tournament_type = EnumTournamentType.getTournamentType(tournament_type);
/*      */     this;
/*      */     if (DedicatedServer.tournament_type == null) {
/*      */       getLogAgent().logWarning("Invalid tournament type!");
/*      */     } else {
/*      */       if (this.settings.getProperty("required-pyramid-height") != null)
/*      */         required_pyramid_height = this.settings.getIntProperty("required-pyramid-height", required_pyramid_height); 
/*      */       getLogAgent().logInfo("Server is running in TOURNAMENT MODE (" + tournament_type + ": " + (getTournamentArenaRadius() * 2) + "x" + (getTournamentArenaRadius() * 2) + ", " + getTournamentTimeLimitInDays() + " day limit)");
/*      */       if (DedicatedServer.tournament_type == EnumTournamentType.open) {
/*      */         tournament_start_time = this.settings.getProperty("tournament-start-time");
/*      */         if ("".equals(tournament_start_time))
/*      */           tournament_start_time = null; 
/*      */       } 
/*      */       tournament_notice_append = this.settings.getProperty("tournament-notice-append");
/*      */       if ("".equals(tournament_notice_append))
/*      */         tournament_notice_append = null; 
/*      */       getLogAgent().logInfo(getTournamentNotice());
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String getTournamentObjective() {
/*      */     return EnumTournamentType.getTournamentObjective(tournament_type);
/*      */   }
/*      */   
/*      */   public static String getTournamentNotice() {
/*      */     String notice = "Notice:";
/*      */     if (tournament_type == EnumTournamentType.open) {
/*      */       if (tournament_start_time == null) {
/*      */         notice = notice + " The tournament hasn't started yet.";
/*      */       } else {
/*      */         notice = notice + " The tournament will be starting at " + tournament_start_time + ".";
/*      */       } 
/*      */     } else {
/*      */       notice = notice + " This server is running in tournament mode.";
/*      */       notice = notice + " " + getTournamentObjective();
/*      */     } 
/*      */     if (tournament_notice_append != null)
/*      */       notice = notice + " " + tournament_notice_append; 
/*      */     return notice;
/*      */   }
/*      */   
/*      */   public static void checkForTournamentWinner(EntityPlayer player, EnumTournamentType tournament_type) {
/*      */     if (player.worldObj.isRemote) {
/*      */       Minecraft.setErrorMessage("checkForTournamentWinner: not supposed to be called on client");
/*      */       return;
/*      */     } 
/*      */     if (tournament_won)
/*      */       return; 
/*      */     if (tournament_type != DedicatedServer.tournament_type)
/*      */       return; 
/*      */     tournament_won = true;
/*      */     shutdown_counter = 500;
/*      */     player.is_tournament_winner = true;
/*      */     player.capabilities.disableDamage = true;
/*      */     String victory_message = EnumTournamentType.getTournamentVictoryMessage(player, tournament_type);
/*      */     theDedicatedServer.getLogAgent().logInfo(victory_message);
/*      */     MinecraftServer.sendPacketToAllPlayersOnServer(new Packet3Chat(ChatMessageComponent.createFromText(victory_message).setColor(EnumChatFormatting.YELLOW)));
/*      */   }
/*      */   
/*      */   public static void checkForTournamentCompletion() {
/*      */     if (tournament_type == EnumTournamentType.wonder)
/*      */       if (BlockSandStone.sacred_pyramid_completed) {
/*      */         tournament_won = true;
/*      */         shutdown_counter = 500;
/*      */         String victory_message = EnumTournamentType.getTournamentVictoryMessage(null, tournament_type);
/*      */         theDedicatedServer.getLogAgent().logInfo(victory_message);
/*      */         MinecraftServer.sendPacketToAllPlayersOnServer(new Packet3Chat(ChatMessageComponent.createFromText(victory_message).setColor(EnumChatFormatting.YELLOW)));
/*      */         File file = new File("pyramid");
/*      */         try {
/*      */           FileWriter fw = new FileWriter(file);
/*      */           fw.write(victory_message);
/*      */           fw.close();
/*      */         } catch (Exception e) {}
/*      */       }  
/*      */   }
/*      */   
/*      */   public static long getTickOfWorld() {
/*      */     return (getServer()).worldServers[0].getTotalWorldTime();
/*      */   }
/*      */   
/*      */   public static void setSoonestReconnectionTime(ServerPlayer player) {
/*      */     Iterator<SoonestReconnectionTime> i = soonest_reconnection_times.iterator();
/*      */     while (i.hasNext()) {
/*      */       SoonestReconnectionTime srt = i.next();
/*      */       if (srt.username.equals(player.username)) {
/*      */         srt.update(player);
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     soonest_reconnection_times.add(new SoonestReconnectionTime(player));
/*      */   }
/*      */   
/*      */   public static void clearSoonestReconnectionTime(ServerPlayer player) {
/*      */     Iterator<SoonestReconnectionTime> i = soonest_reconnection_times.iterator();
/*      */     while (i.hasNext()) {
/*      */       SoonestReconnectionTime srt = i.next();
/*      */       if (srt.username.equals(player.username)) {
/*      */         i.remove();
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static SoonestReconnectionTime getSoonestReconnectionTime(String username) {
/*      */     Iterator<SoonestReconnectionTime> i = soonest_reconnection_times.iterator();
/*      */     while (i.hasNext()) {
/*      */       SoonestReconnectionTime srt = i.next();
/*      */       if (srt.username.equals(username))
/*      */         return srt; 
/*      */     } 
/*      */     return null;
/*      */   }
/*      */   
/*      */   public static void logAchievement(ServerPlayer player, StatBase stat_base) {
/*      */     if (theDedicatedServer != null && tournament_type != EnumTournamentType.open)
/*      */       theDedicatedServer.achievements_log.logInfo(player.username + ": " + stat_base.getName()); 
/*      */   }
/*      */   
/*      */   public void logPerformance() {
/*      */     if (theDedicatedServer == null)
/*      */       return; 
/*      */     StringBuffer sb = new StringBuffer();
/*      */     sb.append("Server load: ");
/*      */     sb.append((int)(MinecraftServer.getServer().getLoadOnServer() * 100.0F));
/*      */     sb.append("% (");
/*      */     int num_players_online = getCurrentPlayerCount();
/*      */     sb.append(num_players_online);
/*      */     sb.append((num_players_online == 1) ? " player online, " : " players online, ");
/*      */     int num_chunks_active = 0;
/*      */     int num_entities_loaded = 0;
/*      */     int num_entity_livings_loaded = 0;
/*      */     int num_entity_items_loaded = 0;
/*      */     for (int i = 0; i < this.worldServers.length; i++) {
/*      */       WorldServer world = this.worldServers[i];
/*      */       num_chunks_active += world.activeChunkSet.size();
/*      */       num_entities_loaded += world.getLoadedEntityList().size();
/*      */       Iterator<Entity> iterator = world.getLoadedEntityList().iterator();
/*      */       while (iterator.hasNext()) {
/*      */         Entity entity = iterator.next();
/*      */         if (entity instanceof EntityLiving) {
/*      */           num_entity_livings_loaded++;
/*      */           continue;
/*      */         } 
/*      */         if (entity instanceof EntityItem)
/*      */           num_entity_items_loaded++; 
/*      */       } 
/*      */     } 
/*      */     sb.append(num_chunks_active);
/*      */     sb.append(" chunks active, ");
/*      */     sb.append(num_entities_loaded);
/*      */     sb.append(" total entities loaded, ");
/*      */     sb.append(num_entity_livings_loaded);
/*      */     sb.append(" living entities loaded, ");
/*      */     sb.append(num_entity_items_loaded);
/*      */     sb.append(" item entities loaded, ");
/*      */     sb.append(getAverageTickTime());
/*      */     sb.append("ms average tick time, ");
/*      */     sb.append((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L);
/*      */     sb.append("MB of ");
/*      */     sb.append(Runtime.getRuntime().totalMemory() / 1024L / 1024L);
/*      */     sb.append("MB memory in use, ");
/*      */     sb.append("time of day is ");
/*      */     sb.append(getEntityWorld().getHourOfDayAMPM());
/*      */     sb.append(")");
/*      */     this.performance_log.logInfo(sb.toString());
/*      */   }
/*      */   
/*      */   public void playerLoggedIn(ServerPlayer par1EntityPlayerMP) {
/*      */     updatePlayersFile();
/*      */   }
/*      */   
/*      */   public void playerLoggedOut(ServerPlayer par1EntityPlayerMP) {
/*      */     updatePlayersFile();
/*      */     boolean player_is_exempt_from_disconnection_penalty = (par1EntityPlayerMP.getHealth() <= 0.0F || par1EntityPlayerMP.is_disconnecting_while_in_bed || tournament_type == EnumTournamentType.open || par1EntityPlayerMP.isZevimrgvInTournament());
/*      */     if (player_is_exempt_from_disconnection_penalty) {
/*      */       clearSoonestReconnectionTime(par1EntityPlayerMP);
/*      */     } else {
/*      */       setSoonestReconnectionTime(par1EntityPlayerMP);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setServerSideMapping(boolean server_side_mapping_enabled) {
/*      */     this.server_side_mapping_enabled = server_side_mapping_enabled;
/*      */   }
/*      */   
/*      */   public boolean isServerSideMappingEnabled() {
/*      */     return this.server_side_mapping_enabled;
/*      */   }
/*      */   
/*      */   public static TournamentStanding getOrCreateTournamentStanding(EntityPlayer player) {
/*      */     TournamentStanding ts = (TournamentStanding)tournament_standings.get(player.username);
/*      */     if (ts == null) {
/*      */       ts = new TournamentStanding();
/*      */       tournament_standings.put(player.username, ts);
/*      */     } 
/*      */     return ts;
/*      */   }
/*      */   
/*      */   public static void incrementTournamentScoringCounter(EntityPlayer player, Item item_harvested) {
/*      */     if (!(player instanceof ServerPlayer) || tournament_type != EnumTournamentType.score)
/*      */       return; 
/*      */     TournamentStanding ts = getOrCreateTournamentStanding(player);
/*      */     if (item_harvested == Item.copperNugget) {
/*      */       ts.copper_nuggets_harvested++;
/*      */     } else if (item_harvested == Item.silverNugget) {
/*      */       ts.silver_nuggets_harvested++;
/*      */     } else if (item_harvested == Item.goldNugget) {
/*      */       ts.gold_nuggets_harvested++;
/*      */     } else if (item_harvested == Item.mithrilNugget) {
/*      */       ts.mithril_nuggets_harvested++;
/*      */     } else if (item_harvested == Item.adamantiumNugget) {
/*      */       ts.adamantium_nuggets_harvested++;
/*      */     } else if (item_harvested == Item.getItem(Block.oreCopper)) {
/*      */       ts.copper_ore_harvested++;
/*      */     } else if (item_harvested == Item.getItem(Block.oreSilver)) {
/*      */       ts.silver_ore_harvested++;
/*      */     } else if (item_harvested == Item.getItem(Block.oreGold)) {
/*      */       ts.gold_ore_harvested++;
/*      */     } else if (item_harvested == Item.getItem(Block.oreIron)) {
/*      */       ts.iron_ore_harvested++;
/*      */     } else if (item_harvested == Item.getItem(Block.oreMithril)) {
/*      */       ts.mithril_ore_harvested++;
/*      */     } else if (item_harvested == Item.getItem(Block.oreAdamantium)) {
/*      */       ts.adamantium_ore_harvested++;
/*      */     } 
/*      */     updateTournamentScoreOnClient(player, true);
/*      */   }
/*      */   
/*      */   public static void updateTournamentScoreOnClient(EntityPlayer player, boolean show_delta) {
/*      */     if (!(player instanceof ServerPlayer) || tournament_type != EnumTournamentType.score)
/*      */       return; 
/*      */     player.getAsEntityPlayerMP().sendPacket((new Packet85SimpleSignal(EnumSignal.tournament_score)).setBoolean(show_delta).setInteger(getOrCreateTournamentStanding(player).calcScore()));
/*      */   }
/*      */   
/*      */   public static void generatePrizeKeyFile(ServerPlayer player) {
/*      */     try {
/*      */       File dir = new File("prize_keys");
/*      */       if (!dir.exists())
/*      */         dir.mkdir(); 
/*      */       FileWriter fw = new FileWriter(dir.getPath() + "/" + player.username + ".key");
/*      */       StringBuffer sb = new StringBuffer();
/*      */       int username_hash = 0;
/*      */       for (int i = 0; i < player.username.length(); i++)
/*      */         username_hash += player.username.charAt(i) * i; 
/*      */       Random random = new Random(player.worldObj.getSeed() * 37L + (username_hash * 61));
/*      */       sb.append(random.nextInt(10));
/*      */       sb.append(random.nextInt(10));
/*      */       sb.append(random.nextInt(10));
/*      */       sb.append(random.nextInt(10));
/*      */       sb.append(random.nextInt(10));
/*      */       sb.append(random.nextInt(10));
/*      */       sb.append(random.nextInt(10));
/*      */       sb.append(random.nextInt(10));
/*      */       fw.write(sb.toString());
/*      */       fw.close();
/*      */       player.sendPacket((new Packet85SimpleSignal(EnumSignal.prize_key_code)).setInteger(Integer.valueOf(sb.toString()).intValue()));
/*      */     } catch (Exception e) {}
/*      */   }
/*      */   
/*      */   public static boolean isTournament() {
/*      */     return (tournament_type != null);
/*      */   }
/*      */   
/*      */   public static boolean isTournament(EnumTournamentType tournament_type) {
/*      */     return (DedicatedServer.tournament_type == tournament_type);
/*      */   }
/*      */   
/*      */   private static String tE(String s) {
/*      */     char[] chars = s.toCharArray();
/*      */     for (int i = 0; i < chars.length; i++) {
/*      */       int c = chars[i];
/*      */       if (c >= 65 && c <= 90) {
/*      */         c = 90 - c - 65;
/*      */       } else if (c >= 97 && c <= 122) {
/*      */         c = 122 - c - 97;
/*      */       } else if (c >= 48 && c <= 57) {
/*      */         c = 57 - c - 48;
/*      */       } 
/*      */       chars[i] = (char)c;
/*      */     } 
/*      */     return new String(chars);
/*      */   }
/*      */   
/*      */   public static int getRequiredPyramidHeight() {
/*      */     return Minecraft.inDevMode() ? 3 : required_pyramid_height;
/*      */   }
/*      */   
/*      */   public static boolean isTournamentThatHasSafeZone() {
/*      */     return (tournament_type != null && tournament_type.has_safe_zone);
/*      */   }
/*      */   
/*      */   public static boolean isTournamentThatUsesAllottedTimes() {
/*      */     return (tournament_type != null && tournament_type.uses_allotted_times);
/*      */   }
/*      */   
/*      */   public static boolean isTournamentThatAllowsAnimalSpawning() {
/*      */     return (tournament_type != null && tournament_type.allows_animal_spawning);
/*      */   }
/*      */   
/*      */   public static boolean isTournamentThatPreventsTimeForwarding() {
/*      */     return (tournament_type != null && tournament_type.prevents_time_forwarding);
/*      */   }
/*      */   
/*      */   public static int getTournamentArenaRadius() {
/*      */     return (tournament_type == null) ? 0 : tournament_type.arena_radius;
/*      */   }
/*      */   
/*      */   public static int getTournamentTimeLimitInDays() {
/*      */     return (tournament_type == null) ? 0 : tournament_type.time_limit_in_days;
/*      */   }
/*      */   
/*      */   public static boolean areSkillsEnabledInSettingsFile() {
/*      */     return are_skills_enabled_in_settings_file;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DedicatedServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */