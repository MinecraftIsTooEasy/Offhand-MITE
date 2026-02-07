/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.TreeSet;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.client.main.Main;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WorldServer
/*      */   extends World
/*      */ {
/*      */   private final MinecraftServer mcServer;
/*      */   private final EntityTracker theEntityTracker;
/*      */   private final PlayerManager thePlayerManager;
/*      */   private Set pendingTickListEntriesHashSet;
/*      */   private TreeSet pendingTickListEntriesTreeSet;
/*      */   public ChunkProviderServer theChunkProviderServer;
/*      */   public boolean canNotSave;
/*      */   private int updateEntityTick;
/*      */   private final Teleporter worldTeleporter;
/*   40 */   private final SpawnerAnimals animalSpawner = new SpawnerAnimals();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   45 */   private ServerBlockEventList[] blockEventCache = new ServerBlockEventList[] { new ServerBlockEventList((ServerBlockEvent)null), new ServerBlockEventList((ServerBlockEvent)null) };
/*      */ 
/*      */ 
/*      */   
/*      */   private int blockEventCacheIndex;
/*      */ 
/*      */ 
/*      */   
/*   53 */   private static final WeightedRandomChestContent[] bonusChestContent = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.stick.itemID, 0, 1, 3, 10), new WeightedRandomChestContent(Block.planks.blockID, 0, 1, 3, 10), new WeightedRandomChestContent(Block.wood.blockID, 0, 1, 3, 10), new WeightedRandomChestContent(Item.hatchetFlint.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.hatchetFlint.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.axeCopper.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.pickaxeCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.appleRed.itemID, 0, 2, 3, 5), new WeightedRandomChestContent(Item.bread.itemID, 0, 2, 3, 3) };
/*   54 */   private List pendingTickListEntriesThisTick = new ArrayList();
/*      */   
/*      */   private IntHashMap entityIdMap;
/*      */   
/*      */   private EntityLiving wake_causing_entity;
/*      */   
/*      */   public boolean fast_forwarding;
/*      */   
/*      */   public WorldMap world_map;
/*      */   
/*      */   private List scheduled_block_changes;
/*      */   
/*      */   public int decreased_hostile_mob_spawning_counter;
/*      */   
/*      */   public int increased_hostile_mob_spawning_counter;
/*      */   
/*      */   public int no_hostile_mob_spawning_counter;
/*      */   
/*      */   private int wm_value;
/*      */   private boolean wms_checked;
/*   74 */   private List queued_block_operations = new ArrayList();
/*      */   
/*      */   public int last_mob_spawn_limit_under_60;
/*      */   
/*      */   public int last_mob_spawn_limit_at_60_or_higher;
/*      */   
/*      */   public WorldServer(MinecraftServer par1MinecraftServer, ISaveHandler par2ISaveHandler, String par3Str, int par4, WorldSettings par5WorldSettings, Profiler par6Profiler, ILogAgent par7ILogAgent) {
/*   81 */     super(par2ISaveHandler, par3Str, par5WorldSettings, WorldProvider.getProviderForDimension(par4), par6Profiler, par7ILogAgent);
/*   82 */     this.mcServer = par1MinecraftServer;
/*   83 */     this.theEntityTracker = new EntityTracker(this);
/*   84 */     this.thePlayerManager = new PlayerManager(this, par1MinecraftServer.getConfigurationManager().getViewDistance());
/*      */     
/*   86 */     if (this.entityIdMap == null)
/*      */     {
/*   88 */       this.entityIdMap = new IntHashMap();
/*      */     }
/*      */     
/*   91 */     if (this.pendingTickListEntriesHashSet == null)
/*      */     {
/*   93 */       this.pendingTickListEntriesHashSet = new HashSet();
/*      */     }
/*      */     
/*   96 */     if (this.pendingTickListEntriesTreeSet == null)
/*      */     {
/*   98 */       this.pendingTickListEntriesTreeSet = new TreeSet();
/*      */     }
/*      */     
/*  101 */     this.worldTeleporter = new Teleporter(this);
/*  102 */     this.worldScoreboard = new ServerScoreboard(par1MinecraftServer);
/*  103 */     ScoreboardSaveData var8 = (ScoreboardSaveData)this.mapStorage.loadData(ScoreboardSaveData.class, "scoreboard");
/*      */     
/*  105 */     if (var8 == null) {
/*      */       
/*  107 */       var8 = new ScoreboardSaveData();
/*  108 */       this.mapStorage.setData("scoreboard", var8);
/*      */     } 
/*      */     
/*  111 */     var8.func_96499_a(this.worldScoreboard);
/*  112 */     ((ServerScoreboard)this.worldScoreboard).func_96547_a(var8);
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacketToAllPlayersInThisDimension(Packet packet) {
/*  117 */     Iterator<ServerPlayer> i = this.playerEntities.iterator();
/*      */ 
/*      */     
/*  120 */     while (i.hasNext()) {
/*      */       
/*  122 */       ServerPlayer player_mp = i.next();
/*  123 */       player_mp.playerNetServerHandler.sendPacketToPlayer(packet);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacketToAllNearbyPlayers(int x, int y, int z, Packet packet, double max_distance_sq) {
/*  129 */     Iterator<ServerPlayer> i = this.playerEntities.iterator();
/*      */ 
/*      */     
/*  132 */     while (i.hasNext()) {
/*      */       
/*  134 */       ServerPlayer player_mp = i.next();
/*      */       
/*  136 */       if (player_mp.getDistanceSqToBlock(x, y, z) <= max_distance_sq) {
/*  137 */         player_mp.playerNetServerHandler.sendPacketToPlayer(packet);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void sendPacketToAllPlayersInAllDimensions(Packet packet) {
/*  143 */     MinecraftServer mc_server = MinecraftServer.getServer();
/*      */     
/*  145 */     for (int i = 0; i < mc_server.worldServers.length; i++) {
/*      */       
/*  147 */       Iterator<ServerPlayer> iterator = (mc_server.worldServers[i]).playerEntities.iterator();
/*      */       
/*  149 */       while (iterator.hasNext()) {
/*      */         
/*  151 */         ServerPlayer player_mp = iterator.next();
/*  152 */         player_mp.playerNetServerHandler.sendPacketToPlayer(packet);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void signalPlayerToStartFallingAsleep(ServerPlayer player) {
/*  160 */     if (player.conscious_state == EnumConsciousState.falling_asleep || player.conscious_state == EnumConsciousState.sleeping) {
/*      */       
/*  162 */       Minecraft.setErrorMessage("signalPlayerToStartFallingAsleep: player is already sleeping or falling asleep");
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  167 */     player.conscious_state = EnumConsciousState.falling_asleep;
/*  168 */     player.playerNetServerHandler.sendPacketToPlayer(new Packet85SimpleSignal(EnumSignal.start_falling_asleep));
/*      */   }
/*      */ 
/*      */   
/*      */   private void signalAllPlayersToStartFallingAsleep() {
/*  173 */     Iterator<ServerPlayer> i = this.playerEntities.iterator();
/*      */     
/*  175 */     while (i.hasNext()) {
/*      */       
/*  177 */       ServerPlayer player = i.next();
/*      */       
/*  179 */       if (player.isGhost() || player.isZevimrgvInTournament()) {
/*      */         continue;
/*      */       }
/*  182 */       if (player.conscious_state == EnumConsciousState.waking_up || player.conscious_state == EnumConsciousState.fully_awake) {
/*  183 */         signalPlayerToStartFallingAsleep(player);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldTimeProgress() {
/*  241 */     boolean non_ghost_players_in_overworld_or_nether = (this.mcServer.worldServerForDimension(-1).hasNonGhostPlayers() || this.mcServer.worldServerForDimension(0).hasNonGhostPlayers());
/*  242 */     boolean non_ghost_players_in_overworld_or_underworld_or_nether = (non_ghost_players_in_overworld_or_nether || this.mcServer.getUnderworld().hasNonGhostPlayers());
/*      */     
/*  244 */     if (this.mcServer.isDedicatedServer()) {
/*      */ 
/*      */ 
/*      */       
/*  248 */       if (isUnderworld()) {
/*  249 */         return (this.worldInfo.getUnderworldHasBeenVisited() && non_ghost_players_in_overworld_or_underworld_or_nether);
/*      */       }
/*  251 */       if (isTheNether())
/*      */       {
/*      */         
/*  254 */         return (this.worldInfo.getNetherHasBeenVisited() && non_ghost_players_in_overworld_or_underworld_or_nether); } 
/*  255 */       if (isTheEnd()) {
/*  256 */         return hasNonGhostPlayers();
/*      */       }
/*  258 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  265 */     if (this.mcServer.hasOnlyGhostPlayersConnected()) {
/*  266 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  270 */     if (isUnderworld()) {
/*  271 */       return (this.worldInfo.getUnderworldHasBeenVisited() && non_ghost_players_in_overworld_or_underworld_or_nether);
/*      */     }
/*  273 */     if (isTheNether())
/*      */     {
/*  275 */       return (this.worldInfo.getNetherHasBeenVisited() && non_ghost_players_in_overworld_or_underworld_or_nether); } 
/*  276 */     if (isTheEnd()) {
/*  277 */       return hasNonGhostPlayers();
/*      */     }
/*      */ 
/*      */     
/*  281 */     return (this.mcServer.hasNoPlayersOfAnyKindConnected() || non_ghost_players_in_overworld_or_underworld_or_nether);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldRandomBlockTicksBePerformed() {
/*  288 */     if (!shouldTimeProgress()) {
/*  289 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  294 */     if (this.mcServer.isDedicatedServer())
/*      */     {
/*  296 */       return this.mcServer.hasNonGhostPlayersConnected(false);
/*      */     }
/*      */ 
/*      */     
/*  300 */     return this.mcServer.hasNonGhostPlayersConnected(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldTimeForwardingBeSkipped() {
/*  307 */     if (Main.no_time_forwarding || DedicatedServer.isTournamentThatPreventsTimeForwarding()) {
/*  308 */       return true;
/*      */     }
/*  310 */     if (this.mcServer.isDedicatedServer() && isOverworld()) {
/*  311 */       return true;
/*      */     }
/*  313 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tick() {
/*  323 */     if (this.no_hostile_mob_spawning_counter > 0 && Minecraft.inDevMode() && this.no_hostile_mob_spawning_counter % 200 == 0) {
/*  324 */       System.out.println("no_hostile_mob_spawning_counter=" + this.no_hostile_mob_spawning_counter);
/*      */     }
/*  326 */     this.worldInfo.setEarliestAllowableMITERelease(149);
/*      */     
/*  328 */     super.tick();
/*      */     
/*  330 */     if (!this.worldInfo.isValidMITEWorld()) {
/*  331 */       MinecraftServer.setTreacheryDetected();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  336 */     if (this.provider.dimensionId == 0 && this.mcServer.isServerSideMappingEnabled())
/*      */     {
/*  338 */       if (this.world_map == null) {
/*  339 */         this.world_map = new WorldMap(this);
/*      */       }
/*      */     }
/*  342 */     if (this.world_map != null) {
/*  343 */       this.world_map.writeToFileProgressively(false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  348 */     checkCurses();
/*      */     
/*  350 */     if (getWorldInfo().isHardcoreModeEnabled() && this.difficultySetting < 3)
/*      */     {
/*  352 */       this.difficultySetting = 3;
/*      */     }
/*      */     
/*  355 */     this.provider.worldChunkMgr.cleanupCache();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  369 */     if (hasNonGhostPlayers()) {
/*      */       
/*  371 */       boolean sleeping_prevented = (isBloodMoon(false) || DedicatedServer.isTournament());
/*      */ 
/*      */       
/*  374 */       if (!sleeping_prevented && allPlayersInBedOrDead() && (getAdjustedTimeOfDay() < getTimeOfSunrise() - 1000 || getAdjustedTimeOfDay() >= getTimeOfSleeping())) {
/*      */         
/*  376 */         if (allPlayersAsleepOrDead())
/*      */         {
/*      */ 
/*      */           
/*  380 */           if (getGameRules().getGameRuleBooleanValue("doDaylightCycle"))
/*      */           {
/*      */ 
/*      */ 
/*      */             
/*  385 */             runSleepTicks(getTimeTillSunrise());
/*      */           }
/*      */           else
/*      */           {
/*  389 */             wakeAllPlayersGently();
/*      */           }
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*  395 */           signalAllPlayersToStartFallingAsleep();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  400 */         wakeAllPlayersGently();
/*      */       } 
/*      */     } 
/*      */     
/*  404 */     this.theProfiler.startSection("mobSpawner");
/*      */     
/*  406 */     if (getGameRules().getGameRuleBooleanValue("doMobSpawning"))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  428 */       this.animalSpawner.performRandomLivingEntitySpawning(this);
/*      */     }
/*      */     
/*  431 */     this.theProfiler.endStartSection("chunkSource");
/*  432 */     this.chunkProvider.unloadQueuedChunks();
/*      */     
/*  434 */     tickBlocksInFastForward();
/*      */     
/*  436 */     checkScheduledBlockChanges(false);
/*      */     
/*  438 */     int var3 = calculateSkylightSubtracted(1.0F);
/*      */     
/*  440 */     if (var3 != this.skylightSubtracted)
/*      */     {
/*  442 */       this.skylightSubtracted = var3;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  447 */     int ticks_progressed = shouldTimeProgress() ? 1 : 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  452 */     if (ticks_progressed > 0) {
/*  453 */       advanceTotalWorldTime(ticks_progressed);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  461 */     this.theProfiler.endStartSection("tickPending");
/*  462 */     tickUpdates(false);
/*  463 */     this.theProfiler.endStartSection("tickTiles");
/*      */ 
/*      */     
/*  466 */     performQueuedBlockOperations();
/*      */     
/*  468 */     if (ticks_progressed <= 0) {
/*  469 */       setActivePlayerChunks();
/*      */     } else {
/*  471 */       tickBlocksAndAmbiance();
/*      */     } 
/*  473 */     this.theProfiler.endStartSection("chunkMap");
/*  474 */     this.thePlayerManager.updatePlayerInstances();
/*  475 */     this.theProfiler.endStartSection("village");
/*  476 */     this.villageCollectionObj.tick();
/*  477 */     this.villageSiegeObj.tick();
/*  478 */     this.theProfiler.endStartSection("portalForcer");
/*  479 */     this.worldTeleporter.removeStalePortalLocations(getTotalWorldTime());
/*  480 */     this.theProfiler.endSection();
/*  481 */     sendAndApplyBlockEvents();
/*      */   }
/*      */ 
/*      */   
/*      */   private void performQueuedBlockOperations() {
/*  486 */     long total_world_time = getTotalWorldTime();
/*      */     
/*  488 */     Iterator<BlockOperation> i = this.queued_block_operations.iterator();
/*      */     
/*  490 */     while (i.hasNext()) {
/*      */       
/*  492 */       BlockOperation block_operation = i.next();
/*      */       
/*  494 */       if (block_operation.tick <= total_world_time) {
/*      */         
/*  496 */         if (block_operation.tick == total_world_time) {
/*  497 */           block_operation.perform(this);
/*      */         }
/*  499 */         i.remove();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void flushQueuedBlockOperations() {
/*  506 */     long total_world_time = getTotalWorldTime();
/*      */     
/*  508 */     Iterator<BlockOperation> i = this.queued_block_operations.iterator();
/*      */     
/*  510 */     while (i.hasNext()) {
/*      */       
/*  512 */       BlockOperation block_operation = i.next();
/*      */       
/*  514 */       if (block_operation.isFlushedOnExit() && block_operation.tick >= total_world_time) {
/*      */         
/*  516 */         block_operation.perform(this);
/*  517 */         i.remove();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean doesQueuedBlockOperationExist(int x, int y, int z, EnumBlockOperation type) {
/*  524 */     Iterator<BlockOperation> i = this.queued_block_operations.iterator();
/*      */     
/*  526 */     while (i.hasNext()) {
/*      */       
/*  528 */       BlockOperation block_operation = i.next();
/*      */       
/*  530 */       if (block_operation.x == x && block_operation.y == y && block_operation.z == z && block_operation.type == type) {
/*  531 */         return true;
/*      */       }
/*      */     } 
/*  534 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EntityLiving tryCreateNewLivingEntityCloseTo(int x, int y, int z, int min_distance, int max_distance, Class<EntitySkeleton> entity_living_class, EnumCreatureType enum_creature_type) {
/*  540 */     boolean is_skeleton = (entity_living_class == EntitySkeleton.class || entity_living_class == EntityLongdead.class);
/*  541 */     boolean can_spawn_in_shallow_water = is_skeleton;
/*      */     
/*  543 */     int min_distance_sq = min_distance * min_distance;
/*  544 */     int max_distance_sq = max_distance * max_distance;
/*      */     
/*  546 */     int random_number_index = this.rand.nextInt();
/*      */     
/*  548 */     for (int attempt = 0; attempt < 16; attempt++) {
/*      */       
/*  550 */       int dx = RNG.int_max[++random_number_index & 0x7FFF] % (max_distance * 2 + 1) - max_distance;
/*  551 */       int dy = RNG.int_7_minus_3[++random_number_index & 0x7FFF];
/*  552 */       int dz = RNG.int_max[++random_number_index & 0x7FFF] % (max_distance * 2 + 1) - max_distance;
/*      */       
/*  554 */       int trial_x = x + dx;
/*  555 */       int trial_y = y + dy;
/*  556 */       int trial_z = z + dz;
/*      */       int i;
/*  558 */       for (i = 0; i < 8;) {
/*      */ 
/*      */         
/*  561 */         if (isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, can_spawn_in_shallow_water)) {
/*  562 */           trial_y--;
/*      */           
/*      */           i++;
/*      */         } 
/*      */       } 
/*  567 */       for (i = 0; i < 8;) {
/*      */ 
/*      */         
/*  570 */         if (!isAirOrPassableBlock(trial_x, trial_y, trial_z, can_spawn_in_shallow_water)) {
/*  571 */           trial_y++;
/*      */           
/*      */           i++;
/*      */         } 
/*      */       } 
/*      */       
/*  577 */       this; double distance_sq = getDistanceSqFromDeltas(dx, (trial_y - y), dz);
/*      */       
/*  579 */       if (distance_sq >= min_distance_sq && distance_sq <= max_distance_sq)
/*      */       {
/*      */         
/*  582 */         if (blockExists(trial_x, trial_y - 1, trial_z) && blockExists(trial_x, trial_y, trial_z) && blockExists(trial_x, trial_y + 1, trial_z))
/*      */         {
/*      */ 
/*      */           
/*  586 */           if (!isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, true) && isAirOrPassableBlock(trial_x, trial_y, trial_z, can_spawn_in_shallow_water) && isAirOrPassableBlock(trial_x, trial_y + 1, trial_z, false)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  606 */             double[] resulting_y_pos = new double[1];
/*      */ 
/*      */             
/*  609 */             if (SpawnerAnimals.canCreatureTypeSpawnAtLocation(enum_creature_type, this, trial_x, trial_y, trial_z, false, resulting_y_pos)) {
/*      */               EntityLiving entity_living;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               try {
/*  620 */                 entity_living = entity_living_class.getConstructor(new Class[] { World.class }).newInstance(new Object[] { this });
/*      */               }
/*  622 */               catch (Exception var33) {
/*      */                 
/*  624 */                 var33.printStackTrace();
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  629 */               entity_living.setLocationAndAngles((trial_x + 0.5F), resulting_y_pos[0], (trial_z + 0.5F), this.rand.nextFloat() * 360.0F, 0.0F);
/*      */               
/*  631 */               if (is_skeleton || entity_living.getCanSpawnHere(false))
/*      */               {
/*      */ 
/*      */ 
/*      */                 
/*  636 */                 return entity_living; } 
/*      */             } 
/*      */           }  }  } 
/*  639 */     }  return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EntityLiving tryPlaceNewSuitableMob(int x, int y, int z, int min_distance_from_players, int max_distance_from_players) {
/*  645 */     int min_distance_sq_from_players = min_distance_from_players * min_distance_from_players;
/*  646 */     int max_distance_sq_from_players = max_distance_from_players * max_distance_from_players;
/*      */     
/*  648 */     int random_number_index = this.rand.nextInt();
/*      */     
/*  650 */     for (int attempt = 0; attempt < 16; attempt++) {
/*      */       
/*  652 */       int dx = RNG.int_max[++random_number_index & 0x7FFF] % (max_distance_from_players * 2 + 1) - max_distance_from_players;
/*  653 */       int dy = RNG.int_7_minus_3[++random_number_index & 0x7FFF];
/*  654 */       int dz = RNG.int_max[++random_number_index & 0x7FFF] % (max_distance_from_players * 2 + 1) - max_distance_from_players;
/*      */       
/*  656 */       int trial_x = x + dx;
/*  657 */       int trial_y = y + dy;
/*  658 */       int trial_z = z + dz;
/*      */       int i;
/*  660 */       for (i = 0; i < 8;) {
/*      */         
/*  662 */         if (isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, false)) {
/*  663 */           trial_y--;
/*      */           
/*      */           i++;
/*      */         } 
/*      */       } 
/*  668 */       for (i = 0; i < 8;) {
/*      */         
/*  670 */         if (!isAirOrPassableBlock(trial_x, trial_y, trial_z, false)) {
/*  671 */           trial_y++;
/*      */           
/*      */           i++;
/*      */         } 
/*      */       } 
/*  676 */       double distance_sq_to_nearest_player = getDistanceSqToNearestPlayer(trial_x, trial_y, trial_z);
/*      */       
/*  678 */       if (distance_sq_to_nearest_player < min_distance_sq_from_players || distance_sq_to_nearest_player > max_distance_sq_from_players) {
/*      */         continue;
/*      */       }
/*  681 */       if (!blockExists(trial_x, trial_y - 1, trial_z) || !blockExists(trial_x, trial_y, trial_z) || !blockExists(trial_x, trial_y + 1, trial_z)) {
/*      */         continue;
/*      */       }
/*  684 */       if (isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, true) || !isAirOrPassableBlock(trial_x, trial_y, trial_z, false) || !isAirOrPassableBlock(trial_x, trial_y + 1, trial_z, false)) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  690 */       if (isOutdoors(trial_x, trial_y, trial_z)) {
/*      */         
/*  692 */         if (getLightBrightness(trial_x, trial_y, trial_z) > 0.4F)
/*      */         {
/*      */           continue;
/*      */         }
/*      */       }
/*  697 */       else if (getLightBrightness(trial_x, trial_y, trial_z) > 0.1F) {
/*      */         continue;
/*      */       } 
/*      */       
/*  701 */       double[] resulting_y_pos = new double[1];
/*      */ 
/*      */       
/*  704 */       if (SpawnerAnimals.canCreatureTypeSpawnAtLocation(EnumCreatureType.monster, this, trial_x, trial_y, trial_z, false, resulting_y_pos)) {
/*      */ 
/*      */         
/*  707 */         Class<EntityEnderman> suitable_creature_class = getSuitableCreature(EnumCreatureType.monster, trial_x, trial_y, trial_z);
/*      */ 
/*      */ 
/*      */         
/*  711 */         if (suitable_creature_class != null && suitable_creature_class != EntityEnderman.class && suitable_creature_class != EntitySlime.class && suitable_creature_class != EntityJelly.class && suitable_creature_class != EntityBlob.class && suitable_creature_class != EntityOoze.class && suitable_creature_class != EntityPudding.class) {
/*      */           EntityLiving entity_living;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/*  718 */             entity_living = suitable_creature_class.getConstructor(new Class[] { World.class }).newInstance(new Object[] { this });
/*      */           }
/*  720 */           catch (Exception var33) {
/*      */             
/*  722 */             var33.printStackTrace();
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  727 */           entity_living.setLocationAndAngles((trial_x + 0.5F), resulting_y_pos[0], (trial_z + 0.5F), this.rand.nextFloat() * 360.0F, 0.0F);
/*      */           
/*  729 */           if (entity_living.getCanSpawnHere(false))
/*      */           {
/*      */ 
/*      */ 
/*      */             
/*  734 */             return entity_living; } 
/*      */         } 
/*      */       }  continue;
/*  737 */     }  return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnDecoy(Class<EntityLiving> entity_living_class, EntityPlayer player) {
/*      */     try {
/*  746 */       EntityLiving entity_living = entity_living_class.getConstructor(new Class[] { World.class }).newInstance(new Object[] { this });
/*      */       
/*  748 */       entity_living.setAsDecoy();
/*      */       
/*  750 */       entity_living.setLocationAndAngles(player.posX, player.posY + 2.0D, player.posZ, this.rand.nextFloat() * 360.0F, 0.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  755 */       entity_living.onSpawnWithEgg((EntityLivingData)null);
/*  756 */       spawnEntityInWorld(entity_living);
/*      */     }
/*  758 */     catch (Exception var33) {
/*      */       
/*  760 */       var33.printStackTrace();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityPlayer pathNearbyMobToRandomSleepingPlayer() {
/*  872 */     EntityPlayer player = getRandomNonGhostPlayer(true);
/*      */     
/*  874 */     if (player == null) {
/*  875 */       return null;
/*      */     }
/*  877 */     List<EntityLiving> mobs = null;
/*  878 */     int distance = 0;
/*      */     
/*  880 */     while (distance < 64) {
/*      */       
/*  882 */       distance += 16;
/*      */       
/*  884 */       mobs = getEntitiesWithinAABB(IMob.class, player.boundingBox.expand(distance, (distance / 4), distance));
/*      */       
/*  886 */       if (mobs.size() >= 16) {
/*      */         break;
/*      */       }
/*      */     } 
/*  890 */     boolean[] tried = new boolean[mobs.size()];
/*      */     
/*  892 */     int attempts = Math.min(mobs.size(), 16);
/*      */     
/*  894 */     for (int attempt = 0; attempt < attempts; attempt++) {
/*      */ 
/*      */ 
/*      */       
/*  898 */       int entity_index = this.rand.nextInt(mobs.size());
/*      */       
/*  900 */       if (!tried[entity_index]) {
/*      */ 
/*      */         
/*  903 */         EntityLiving entity_living = mobs.get(entity_index);
/*      */         
/*  905 */         if (entity_living.ticksExisted > 0 && entity_living instanceof IMob && !(entity_living instanceof EntityEnderman))
/*      */         {
/*  907 */           if (tryPathMobToSleepingPlayer(entity_living, player, distance, true)) {
/*  908 */             return player;
/*      */           }
/*      */         }
/*  911 */         tried[entity_index] = true;
/*      */       } 
/*      */     } 
/*  914 */     return null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryPathMobToSleepingPlayer(EntityLiving entity_living, EntityPlayer player, int max_path_length, boolean sync_last_tick_pos_on_next_update) {
/*  956 */     PathNavigate navigator = entity_living.getNavigator();
/*  957 */     PathEntity path = getEntityPathToXYZ(entity_living, player.bed_location.posX, player.bed_location.posY, player.bed_location.posZ, max_path_length, navigator.canPassOpenWoodenDoors, false, navigator.avoidsWater, navigator.canSwim);
/*      */     
/*  959 */     if (path == null) {
/*  960 */       return false;
/*      */     }
/*  962 */     PathPoint final_point = path.getFinalPathPoint();
/*      */     
/*  964 */     if (getDistanceSqFromDeltas((final_point.xCoord - player.bed_location.posX), (final_point.yCoord - player.bed_location.posY), (final_point.zCoord - player.bed_location.posZ)) > 2.0D) {
/*  965 */       return false;
/*      */     }
/*  967 */     PathPoint path_point = null;
/*      */     
/*  969 */     int path_point_index = Math.max(path.getCurrentPathLength() - 8, 0);
/*      */     
/*  971 */     Vec3 player_pos = getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
/*  972 */     Vec3 entity_eye_pos = getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
/*      */     
/*  974 */     for (int i = path_point_index; i < path.getCurrentPathLength(); i++) {
/*      */       
/*  976 */       path_point = path.getPathPointFromIndex(i);
/*      */       
/*  978 */       player_pos.setComponents((player.bed_location.posX + 0.5F), (player.bed_location.posY + 1.5F), (player.bed_location.posZ + 0.5F));
/*  979 */       entity_eye_pos.setComponents((path_point.xCoord + 0.5F), (path_point.yCoord + entity_living.getEyeHeight()), (path_point.zCoord + 0.5F));
/*      */ 
/*      */       
/*  982 */       boolean seen = checkForLineOfSight(entity_eye_pos, player_pos, false);
/*      */       
/*  984 */       if (seen) {
/*      */         break;
/*      */       }
/*  987 */       path_point = null;
/*      */     } 
/*      */     
/*  990 */     if (path_point == null) {
/*  991 */       path_point = path.getPathPointFromIndex(path_point_index);
/*      */     }
/*  993 */     entity_living.sync_last_tick_pos_on_next_update = sync_last_tick_pos_on_next_update;
/*      */ 
/*      */     
/*  996 */     Block block_below = getBlock(path_point.xCoord, path_point.yCoord - 1, path_point.zCoord);
/*      */     
/*  998 */     if (block_below == null) {
/*      */       
/* 1000 */       entity_living.setPosition((path_point.xCoord + 0.5F), path_point.yCoord, (path_point.zCoord + 0.5F));
/*      */     }
/*      */     else {
/*      */       
/* 1004 */       block_below.setBlockBoundsBasedOnStateAndNeighbors(this, path_point.xCoord, path_point.yCoord - 1, path_point.zCoord);
/* 1005 */       entity_living.setPosition((path_point.xCoord + 0.5F), (path_point.yCoord - 1) + block_below.maxY[Minecraft.getThreadIndex()], (path_point.zCoord + 0.5F));
/*      */     } 
/*      */     
/* 1008 */     this.wake_causing_entity = entity_living;
/*      */     
/* 1010 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean runSleepTicks(int ticks) {
/* 1015 */     int ticks_slept = 0;
/* 1016 */     boolean player_woke_up = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1021 */     while (ticks_slept < ticks && !player_woke_up) {
/*      */       
/* 1023 */       Iterator<ServerPlayer> players = this.playerEntities.iterator();
/*      */       
/* 1025 */       while (players.hasNext()) {
/*      */         
/* 1027 */         ServerPlayer player = players.next();
/*      */         
/* 1029 */         if (player.isGhost() || player.isZevimrgvInTournament()) {
/*      */           continue;
/*      */         }
/* 1032 */         if (player.getHealth() <= 0.0F) {
/*      */           continue;
/*      */         }
/* 1035 */         player.foodStats.onUpdate(player);
/*      */         
/* 1037 */         if (!player.hasFoodEnergy()) {
/*      */           
/* 1039 */           player.wakeUpPlayer(true, (Entity)null);
/* 1040 */           player.addChatMessage("tile.bed.wakeHungry");
/*      */           
/* 1042 */           player_woke_up = true; continue;
/*      */         } 
/* 1044 */         if (!player.isSleeping())
/*      */         {
/* 1046 */           player_woke_up = true;
/*      */         }
/*      */       } 
/*      */       
/* 1050 */       if (!player_woke_up && this.rand.nextInt(1000) == 0) {
/*      */         
/* 1052 */         EntityPlayer player_to_wake = pathNearbyMobToRandomSleepingPlayer();
/*      */         
/* 1054 */         if (player_to_wake == null) {
/*      */           
/* 1056 */           EntityPlayer player = getRandomNonGhostPlayer(true);
/*      */           
/* 1058 */           if (player != null) {
/*      */             
/* 1060 */             EntityLiving entity_living = tryPlaceNewSuitableMob(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ), 8, 48);
/*      */             
/* 1062 */             if (entity_living != null) {
/*      */               
/* 1064 */               entity_living.refreshDespawnCounter(-2400);
/*      */               
/* 1066 */               if (tryPathMobToSleepingPlayer(entity_living, player, 32, false)) {
/* 1067 */                 player_to_wake = player;
/*      */               }
/* 1069 */               if (player_to_wake != null || this.rand.nextInt(2) == 0) {
/*      */ 
/*      */                 
/* 1072 */                 entity_living.onSpawnWithEgg((EntityLivingData)null);
/* 1073 */                 spawnEntityInWorld(entity_living);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 1079 */         if (player_to_wake != null) {
/*      */           
/* 1081 */           this.wake_causing_entity.extinguish();
/*      */ 
/*      */ 
/*      */           
/* 1085 */           player_to_wake.wakeUpPlayer(true, this.wake_causing_entity);
/* 1086 */           player_to_wake.addChatMessage("tile.bed.wakeMobs");
/*      */           
/* 1088 */           player_woke_up = true;
/*      */         } 
/*      */       } 
/*      */       
/* 1092 */       ticks_slept++;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1097 */     if (ticks_slept == ticks && !player_woke_up && ticks_slept >= 6000) {
/*      */       
/* 1099 */       Iterator<ServerPlayer> i = this.playerEntities.iterator();
/*      */       
/* 1101 */       while (i.hasNext()) {
/*      */         
/* 1103 */         ServerPlayer player = i.next();
/*      */         
/* 1105 */         if (player.isGhost() || player.isZevimrgvInTournament()) {
/*      */           continue;
/*      */         }
/* 1108 */         if (player.getHealth() <= 0.0F) {
/*      */           continue;
/*      */         }
/* 1111 */         if (!player.inBed()) {
/*      */           continue;
/*      */         }
/* 1114 */         player.triggerAchievement(AchievementList.wellRested);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1121 */     advanceTotalWorldTime(ticks_slept);
/*      */     
/* 1123 */     if (ticks_slept >= 1000) {
/*      */       
/* 1125 */       Iterator<ServerPlayer> i = this.playerEntities.iterator();
/*      */       
/* 1127 */       while (i.hasNext()) {
/*      */         
/* 1129 */         ServerPlayer player = i.next();
/*      */         
/* 1131 */         if (player.isGhost() || player.isZevimrgvInTournament()) {
/*      */           continue;
/*      */         }
/* 1134 */         if (player.getHealth() <= 0.0F) {
/*      */           continue;
/*      */         }
/* 1137 */         if (!player.inBed()) {
/*      */           continue;
/*      */         }
/* 1140 */         player.clearActivePotions();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1149 */     _calculateInitialWeather();
/*      */     
/* 1151 */     return (ticks_slept == ticks);
/*      */   }
/*      */ 
/*      */   
/*      */   public Class getSuitableCreature(EnumCreatureType creature_type, int x, int y, int z) {
/* 1156 */     boolean check_depth = isOverworld();
/*      */ 
/*      */     
/* 1159 */     boolean is_blood_moon_up = isBloodMoon(true);
/* 1160 */     boolean is_freezing_biome = getBiomeGenForCoords(x, z).isFreezing();
/* 1161 */     boolean is_desert_biome = getBiomeGenForCoords(x, z).isDesertBiome();
/*      */     
/* 1163 */     boolean can_spawn_ghouls_on_surface = is_blood_moon_up;
/* 1164 */     boolean can_spawn_wights_on_surface = (is_blood_moon_up && is_freezing_biome);
/* 1165 */     boolean can_spawn_shadows_on_surface = (is_blood_moon_up && is_desert_biome);
/* 1166 */     boolean can_spawn_revenants_on_surface = is_blood_moon_up;
/* 1167 */     boolean can_spawn_bone_lords_on_surface = is_blood_moon_up;
/*      */     
/* 1169 */     for (int attempt = 0; attempt < 16; attempt++) {
/*      */       
/* 1171 */       List possible_creatures = getChunkProvider().getPossibleCreatures(creature_type, x, y, z);
/*      */       
/* 1173 */       if (possible_creatures == null || possible_creatures.isEmpty()) {
/* 1174 */         return null;
/*      */       }
/* 1176 */       SpawnListEntry entry = (SpawnListEntry)WeightedRandom.getRandomItem(this.rand, possible_creatures);
/* 1177 */       Class<EntityCreeper> entity_class = entry.entityClass;
/*      */ 
/*      */ 
/*      */       
/* 1181 */       if (entity_class == EntityCreeper.class) {
/*      */         
/* 1183 */         if (hasSkylight() && !isDaytime() && this.rand.nextInt(4) != 0 && isOutdoors(x, y, z)) {
/*      */           continue;
/*      */         }
/* 1186 */         if (this.rand.nextInt(40) >= y && this.rand.nextFloat() < 0.5F) {
/* 1187 */           return EntityInfernalCreeper.class;
/*      */         }
/* 1189 */       } else if (entity_class == EntitySlime.class) {
/*      */         
/* 1191 */         if (blockTypeIsAbove(Block.stone, x, y, z)) {
/*      */           continue;
/*      */         }
/* 1194 */       } else if (entity_class == EntityGhoul.class) {
/*      */         
/* 1196 */         if (check_depth && y > 56 && !can_spawn_ghouls_on_surface) {
/*      */           continue;
/*      */         }
/* 1199 */       } else if (entity_class == EntityJelly.class) {
/*      */ 
/*      */         
/* 1202 */         if (!blockTypeIsAbove(Block.stone, x, y, z)) {
/*      */           continue;
/*      */         }
/* 1205 */       } else if (entity_class == EntityWight.class) {
/*      */         
/* 1207 */         if (check_depth && y > 48 && !can_spawn_wights_on_surface) {
/*      */           continue;
/*      */         }
/* 1210 */       } else if (entity_class == EntityVampireBat.class) {
/*      */         
/* 1212 */         if (check_depth && y > 48 && !is_blood_moon_up) {
/*      */           continue;
/*      */         }
/* 1215 */       } else if (entity_class == EntityRevenant.class) {
/*      */         
/* 1217 */         if (check_depth && y > 44 && !can_spawn_revenants_on_surface) {
/*      */           continue;
/*      */         }
/* 1220 */       } else if (entity_class == EntityInvisibleStalker.class) {
/*      */         
/* 1222 */         if (check_depth && y > 40) {
/*      */           continue;
/*      */         }
/* 1225 */       } else if (entity_class == EntityEarthElemental.class) {
/*      */         
/* 1227 */         if (check_depth)
/*      */         {
/* 1229 */           if (y > 40) {
/*      */             continue;
/*      */           }
/*      */         }
/* 1233 */       } else if (entity_class == EntityBlob.class) {
/*      */         
/* 1235 */         if ((check_depth && y > 40) || !blockTypeIsAbove(Block.stone, x, y, z)) {
/*      */           continue;
/*      */         }
/* 1238 */       } else if (entity_class == EntityOoze.class) {
/*      */         
/* 1240 */         if (check_depth && y > 32) {
/*      */           continue;
/*      */         }
/* 1243 */         if (getBlock(x, y - 1, z) != Block.stone || !blockTypeIsAbove(Block.stone, x, y, z)) {
/*      */           continue;
/*      */         }
/* 1246 */       } else if (entity_class == EntityNightwing.class) {
/*      */         
/* 1248 */         if (check_depth && y > 32 && !is_blood_moon_up) {
/*      */           continue;
/*      */         }
/* 1251 */       } else if (entity_class == EntityBoneLord.class) {
/*      */         
/* 1253 */         if (check_depth && y > 32 && !can_spawn_bone_lords_on_surface) {
/*      */           continue;
/*      */         }
/* 1256 */       } else if (entity_class == EntityPudding.class) {
/*      */         
/* 1258 */         if (check_depth && y > 24) {
/*      */           continue;
/*      */         }
/* 1261 */         if (getBlock(x, y - 1, z) != Block.stone || !blockTypeIsAbove(Block.stone, x, y, z)) {
/*      */           continue;
/*      */         }
/* 1264 */       } else if (entity_class == EntityDemonSpider.class || entity_class == EntityPhaseSpider.class) {
/*      */         
/* 1266 */         if (check_depth && y > 32) {
/*      */           continue;
/*      */         }
/* 1269 */       } else if (entity_class == EntityHellhound.class) {
/*      */         
/* 1271 */         if (check_depth && y > 32) {
/*      */           continue;
/*      */         }
/* 1274 */       } else if (entity_class == EntityShadow.class) {
/*      */         
/* 1276 */         if (check_depth && y > 32 && !can_spawn_shadows_on_surface) {
/*      */           continue;
/*      */         }
/* 1279 */       } else if (entity_class == EntitySpider.class) {
/*      */         
/* 1281 */         if (hasSkylight() && this.rand.nextInt(4) == 0 && isOutdoors(x, y, z))
/*      */         {
/*      */           continue;
/*      */         
/*      */         }
/*      */       }
/* 1287 */       else if (entity_class == EntityWoodSpider.class) {
/*      */         
/* 1289 */         if (!canBlockSeeTheSky(x, y, z) && !blockTypeIsAbove(Block.leaves, x, y, z) && !blockTypeIsAbove(Block.wood, x, y, z)) {
/*      */           continue;
/*      */         }
/* 1292 */         if (!blockTypeIsNearTo(Block.wood.blockID, x, y, z, 5, 2) || !blockTypeIsNearTo(Block.leaves.blockID, x, y + 5, z, 5, 5)) {
/*      */           continue;
/*      */         }
/* 1295 */       } else if (entity_class == EntityBlackWidowSpider.class) {
/*      */         
/* 1297 */         if (this.rand.nextFloat() < 0.5F) {
/*      */           continue;
/*      */         }
/* 1300 */       } else if (entity_class == EntityGhast.class) {
/*      */         
/* 1302 */         Iterator<Entity> i = this.loadedEntityList.iterator();
/*      */         
/* 1304 */         while (i.hasNext()) {
/*      */           
/* 1306 */           Entity entity = i.next();
/*      */ 
/*      */           
/* 1309 */           if (entity instanceof EntityGhast && entity.getDistanceSqToBlock(x, y, z) < 2304.0D && this.rand.nextFloat() < 0.8F)
/*      */           {
/* 1311 */             entity_class = null;
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1317 */       return entity_class;
/*      */     } 
/*      */     
/* 1320 */     return null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean allPlayersInBedOrDead() {
/* 1372 */     if (!hasNonGhostPlayers()) {
/* 1373 */       return false;
/*      */     }
/* 1375 */     boolean at_least_one_player_in_bed = false;
/*      */     
/* 1377 */     Iterator<EntityPlayer> i = this.playerEntities.iterator();
/*      */     
/* 1379 */     while (i.hasNext()) {
/*      */       
/* 1381 */       EntityPlayer player = i.next();
/*      */       
/* 1383 */       if (player.isGhost() || player.isZevimrgvInTournament()) {
/*      */         continue;
/*      */       }
/* 1386 */       if (player.getHealth() <= 0.0F) {
/*      */         continue;
/*      */       }
/* 1389 */       if (!player.inBed()) {
/* 1390 */         return false;
/*      */       }
/* 1392 */       at_least_one_player_in_bed = true;
/*      */     } 
/*      */ 
/*      */     
/* 1396 */     return at_least_one_player_in_bed;
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
/*      */   protected void wakeAllPlayersGently() {
/* 1422 */     Iterator<ServerPlayer> i = this.playerEntities.iterator();
/*      */     
/* 1424 */     while (i.hasNext()) {
/*      */       
/* 1426 */       ServerPlayer player = i.next();
/*      */       
/* 1428 */       if (player.isGhost() || player.isZevimrgvInTournament()) {
/*      */         continue;
/*      */       }
/*      */       
/* 1432 */       if (player.conscious_state == EnumConsciousState.falling_asleep || player.conscious_state == EnumConsciousState.sleeping) {
/* 1433 */         player.wakeUpPlayer(false, (Entity)null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean allPlayersAsleepOrDead() {
/* 1493 */     if (!hasNonGhostPlayers()) {
/* 1494 */       return false;
/*      */     }
/* 1496 */     boolean at_least_one_player_asleep = false;
/*      */     
/* 1498 */     Iterator<EntityPlayer> i = this.playerEntities.iterator();
/*      */     
/* 1500 */     while (i.hasNext()) {
/*      */       
/* 1502 */       EntityPlayer player = i.next();
/*      */       
/* 1504 */       if (player.isGhost() || player.isZevimrgvInTournament()) {
/*      */         continue;
/*      */       }
/* 1507 */       if (player.getHealth() <= 0.0F) {
/*      */         continue;
/*      */       }
/* 1510 */       if (!player.isSleeping()) {
/* 1511 */         return false;
/*      */       }
/* 1513 */       at_least_one_player_asleep = true;
/*      */     } 
/*      */ 
/*      */     
/* 1517 */     return at_least_one_player_asleep;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSpawnLocation() {
/* 1525 */     if (this.worldInfo.getSpawnY() <= 0)
/*      */     {
/* 1527 */       this.worldInfo.setSpawnY(64);
/*      */     }
/*      */     
/* 1530 */     int var1 = this.worldInfo.getSpawnX();
/* 1531 */     int var2 = this.worldInfo.getSpawnZ();
/* 1532 */     int var3 = 0;
/*      */     
/* 1534 */     while (getFirstUncoveredBlock(var1, var2) == 0) {
/*      */       
/* 1536 */       var1 += this.rand.nextInt(8) - this.rand.nextInt(8);
/* 1537 */       var2 += this.rand.nextInt(8) - this.rand.nextInt(8);
/*      */       
/* 1539 */       var3++;
/*      */       
/* 1541 */       if (var3 == 10000) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1547 */     this.worldInfo.setSpawnX(var1);
/* 1548 */     this.worldInfo.setSpawnZ(var2);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void tickBlocksInFastForward(Chunk chunk) {
/* 1553 */     long current_total_world_time = getTotalWorldTime();
/*      */ 
/*      */     
/* 1556 */     if (chunk.last_total_world_time == 0L || chunk.last_total_world_time >= current_total_world_time || shouldTimeForwardingBeSkipped()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1561 */       chunk.last_total_world_time = current_total_world_time;
/*      */       
/*      */       return;
/*      */     } 
/* 1565 */     if (current_total_world_time - chunk.last_total_world_time > 768000L) {
/* 1566 */       chunk.last_total_world_time = current_total_world_time - 768000L;
/*      */     }
/* 1568 */     int delta_total_world_time = (int)(chunk.last_total_world_time - current_total_world_time);
/*      */     
/* 1570 */     if (delta_total_world_time <= -600) {
/* 1571 */       chunk.should_be_saved_once_time_forwarding_is_completed = true;
/*      */     }
/* 1573 */     boolean simulate_day_cycles = (this.provider.dimensionId == 0 && delta_total_world_time <= -2000);
/*      */ 
/*      */     
/* 1576 */     int saved_skylight_subtracted = this.skylightSubtracted;
/*      */ 
/*      */ 
/*      */     
/* 1580 */     float saved_prev_raining_strength = this.prevRainingStrength;
/* 1581 */     float saved_raining_strength = this.rainingStrength;
/* 1582 */     float saved_prev_thundering_strength = this.prevThunderingStrength;
/* 1583 */     float saved_thundering_strength = this.thunderingStrength;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1592 */     int var5 = chunk.xPosition * 16;
/* 1593 */     int var6 = chunk.zPosition * 16;
/*      */ 
/*      */     
/* 1596 */     int distance_sq_from_nearest_player = 100;
/*      */     
/* 1598 */     Iterator<EntityPlayer> iterator = this.playerEntities.iterator();
/*      */     
/* 1600 */     while (iterator.hasNext()) {
/*      */       
/* 1602 */       EntityPlayer player = iterator.next();
/*      */       
/* 1604 */       int dx = chunk.xPosition - player.getChunkPosX();
/* 1605 */       int dz = chunk.zPosition - player.getChunkPosZ();
/*      */       
/* 1607 */       int distance_sq = dx * dx + dz * dz;
/*      */       
/* 1609 */       if (distance_sq < distance_sq_from_nearest_player) {
/* 1610 */         distance_sq_from_nearest_player = distance_sq;
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
/* 1622 */     int max_ticks_to_fast_forward_this_tick = (distance_sq_from_nearest_player <= 2) ? 8000 : ((distance_sq_from_nearest_player <= 5) ? 4000 : ((distance_sq_from_nearest_player <= 10) ? 2000 : ((distance_sq_from_nearest_player <= 20) ? 1000 : ((distance_sq_from_nearest_player <= 29) ? 500 : 250))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1629 */     int total_ticks_to_fast_forward = Math.min(-delta_total_world_time, max_ticks_to_fast_forward_this_tick);
/* 1630 */     int remaining_ticks_to_fast_forward = total_ticks_to_fast_forward;
/*      */ 
/*      */ 
/*      */     
/* 1634 */     while (remaining_ticks_to_fast_forward > 0) {
/*      */       
/* 1636 */       int ticks_to_run = simulate_day_cycles ? Math.min(remaining_ticks_to_fast_forward, 4000) : remaining_ticks_to_fast_forward;
/*      */       
/* 1638 */       if (simulate_day_cycles) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1643 */         setTotalWorldTime(chunk.last_total_world_time);
/*      */ 
/*      */ 
/*      */         
/* 1647 */         WeatherEvent weather_event = getCurrentWeatherEvent();
/*      */         
/* 1649 */         if (weather_event == null) {
/*      */           
/* 1651 */           this.prevRainingStrength = 0.0F;
/* 1652 */           this.rainingStrength = 0.0F;
/* 1653 */           this.prevThunderingStrength = 0.0F;
/* 1654 */           this.thunderingStrength = 0.0F;
/*      */         }
/*      */         else {
/*      */           
/* 1658 */           this.prevRainingStrength = 1.0F;
/* 1659 */           this.rainingStrength = 1.0F;
/*      */           
/* 1661 */           if (weather_event.isStormingAt(chunk.last_total_world_time)) {
/*      */             
/* 1663 */             this.prevThunderingStrength = 1.0F;
/* 1664 */             this.thunderingStrength = 1.0F;
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1670 */         this.skylightSubtracted = calculateSkylightSubtracted(1.0F);
/* 1671 */         chunk.updateSkylight(false);
/*      */       } 
/*      */       
/* 1674 */       ExtendedBlockStorage[] var19 = chunk.getBlockStorageArray();
/* 1675 */       int var9 = var19.length;
/*      */       
/* 1677 */       for (int i = 0; i < ticks_to_run; i++) {
/*      */         
/* 1679 */         if (RNG.chance_in_16[++RNG.random_number_index & 0x7FFF]) {
/*      */           
/* 1681 */           this.updateLCG = this.updateLCG * 3 + 1013904223;
/* 1682 */           int var8 = this.updateLCG >> 2;
/* 1683 */           int x = var8 & 0xF;
/* 1684 */           int z = var8 >> 8 & 0xF;
/* 1685 */           int y = getPrecipitationHeight(x + var5, z + var6);
/*      */           
/* 1687 */           if (isBlockFreezableNaturally(x + var5, y - 1, z + var6))
/*      */           {
/* 1689 */             setBlock(x + var5, y - 1, z + var6, Block.ice.blockID);
/*      */           }
/*      */           
/* 1692 */           if (isPrecipitating(false)) {
/*      */             
/* 1694 */             if (canSnowAt(x + var5, y, z + var6))
/*      */             {
/*      */               
/* 1697 */               placeSnowfallAt(x + var5, y, z + var6);
/*      */             }
/*      */             
/* 1700 */             BiomeGenBase var12 = getBiomeGenForCoords(x + var5, z + var6);
/*      */             
/* 1702 */             if (var12.canSpawnLightningBolt(isBloodMoon24HourPeriod())) {
/*      */               
/* 1704 */               int var13 = getBlockId(x + var5, y - 1, z + var6);
/*      */               
/* 1706 */               if (var13 != 0)
/*      */               {
/* 1708 */                 Block.blocksList[var13].fillWithRain(this, x + var5, y - 1, z + var6);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1717 */         for (int var10 = 0; var10 < var9; var10++) {
/*      */           
/* 1719 */           ExtendedBlockStorage var21 = var19[var10];
/*      */           
/* 1721 */           if (var21 != null && var21.getNeedsRandomTick()) {
/*      */             
/* 1723 */             int y_location = var21.getYLocation();
/*      */             
/* 1725 */             for (int var20 = 0; var20 < 3; var20++) {
/*      */               
/* 1727 */               this.updateLCG = this.updateLCG * 3 + 1013904223;
/* 1728 */               int var13 = this.updateLCG >> 2;
/* 1729 */               int var14 = var13 & 0xF;
/* 1730 */               int var15 = var13 >> 8 & 0xF;
/* 1731 */               int var16 = var13 >> 16 & 0xF;
/* 1732 */               int var17 = var21.getExtBlockID(var14, var16, var15);
/*      */               
/* 1734 */               Block var18 = Block.blocksList[var17];
/*      */               
/* 1736 */               if (var18 != null && var18.getTickRandomly()) {
/* 1737 */                 var18.updateTick(this, var14 + var5, var16 + y_location, var15 + var6, this.rand);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1749 */       chunk.last_total_world_time += ticks_to_run;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1754 */       remaining_ticks_to_fast_forward -= ticks_to_run;
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
/* 1769 */     if (chunk.last_total_world_time > current_total_world_time) {
/* 1770 */       Minecraft.setErrorMessage("Fast forwarding error: chunk time (" + chunk.last_total_world_time + ") greater than world time (" + current_total_world_time + ")");
/*      */     }
/* 1772 */     if (simulate_day_cycles) {
/*      */ 
/*      */ 
/*      */       
/* 1776 */       setTotalWorldTime(current_total_world_time);
/* 1777 */       this.skylightSubtracted = saved_skylight_subtracted;
/*      */ 
/*      */ 
/*      */       
/* 1781 */       this.prevRainingStrength = saved_prev_raining_strength;
/* 1782 */       this.rainingStrength = saved_raining_strength;
/* 1783 */       this.prevThunderingStrength = saved_prev_thundering_strength;
/* 1784 */       this.thunderingStrength = saved_thundering_strength;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void tickBlocksInFastForward() {
/* 1793 */     if (DedicatedServer.isTournamentThatPreventsTimeForwarding()) {
/*      */       return;
/*      */     }
/* 1796 */     float server_load = this.mcServer.getLoadOnServer();
/*      */ 
/*      */ 
/*      */     
/* 1800 */     this.fast_forwarding = true;
/*      */     
/* 1802 */     Iterator<ChunkCoordIntPair> var3 = this.activeChunkSet.iterator();
/*      */     
/* 1804 */     if (server_load < 0.5F) {
/*      */ 
/*      */       
/* 1807 */       while (var3.hasNext())
/*      */       {
/* 1809 */         ChunkCoordIntPair var4 = var3.next();
/* 1810 */         Chunk var7 = getChunkFromChunkCoords(var4.chunkXPos, var4.chunkZPos);
/*      */         
/* 1812 */         tickBlocksInFastForward(var7);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1817 */       int tick_of_day = getTimeOfDay();
/* 1818 */       int modulus = (int)((server_load - 0.29F) * 10.0F);
/*      */ 
/*      */ 
/*      */       
/* 1822 */       while (var3.hasNext()) {
/*      */         
/* 1824 */         ChunkCoordIntPair var4 = var3.next();
/*      */         
/* 1826 */         if (++tick_of_day % modulus == 0) {
/*      */           
/* 1828 */           Chunk var7 = getChunkFromChunkCoords(var4.chunkXPos, var4.chunkZPos);
/*      */           
/* 1830 */           tickBlocksInFastForward(var7);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1835 */     if (this.worldInfo.getEarliestMITEReleaseRunIn() < 105) {
/* 1836 */       this.mcServer.initiateShutdown();
/*      */     }
/* 1838 */     this.fast_forwarding = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean placeSnowfallAt(int x, int y, int z) {
/* 1844 */     Block block = getBlock(x, y, z);
/*      */     
/* 1846 */     if (block instanceof BlockCrops || block instanceof BlockStem) {
/* 1847 */       block.dropBlockAsEntityItem((new BlockBreakInfo(this, x, y, z)).setSnowedUpon());
/*      */     }
/* 1849 */     return setBlock(x, y, z, Block.snow.blockID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void tickBlocksAndAmbiance() {
/* 1858 */     super.tickBlocksAndAmbiance();
/* 1859 */     int var1 = 0;
/* 1860 */     int var2 = 0;
/* 1861 */     Iterator<ChunkCoordIntPair> var3 = this.activeChunkSet.iterator();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1866 */     boolean perform_random_block_ticks = shouldRandomBlockTicksBePerformed();
/*      */     
/* 1868 */     boolean is_blood_moon = isBloodMoon24HourPeriod();
/*      */     
/* 1870 */     int rarity_of_lightning = is_blood_moon ? 20000 : 100000;
/*      */     
/* 1872 */     while (var3.hasNext()) {
/*      */       
/* 1874 */       ChunkCoordIntPair var4 = var3.next();
/* 1875 */       int var5 = var4.chunkXPos * 16;
/* 1876 */       int var6 = var4.chunkZPos * 16;
/* 1877 */       this.theProfiler.startSection("getChunk");
/* 1878 */       Chunk var7 = getChunkFromChunkCoords(var4.chunkXPos, var4.chunkZPos);
/* 1879 */       moodSoundAndLightCheck(var5, var6, var7);
/* 1880 */       this.theProfiler.endStartSection("tickChunk");
/* 1881 */       var7.updateSkylight(false);
/* 1882 */       var7.performPendingSandFallsIfPossible();
/* 1883 */       this.theProfiler.endStartSection("thunder");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1890 */       if (this.rand.nextInt(rarity_of_lightning) == 0 && isPrecipitating(true) && isThundering(true)) {
/*      */         
/* 1892 */         this.updateLCG = this.updateLCG * 3 + 1013904223;
/* 1893 */         int var8 = this.updateLCG >> 2;
/* 1894 */         int i = var5 + (var8 & 0xF);
/* 1895 */         int j = var6 + (var8 >> 8 & 0xF);
/* 1896 */         int var11 = getPrecipitationHeight(i, j);
/*      */         
/* 1898 */         if (canLightningStrikeAt(i, var11, j))
/*      */         {
/* 1900 */           addWeatherEffect(new EntityLightningBolt(this, i, var11, j));
/*      */         }
/*      */       } 
/*      */       
/* 1904 */       this.theProfiler.endStartSection("iceandsnow");
/*      */ 
/*      */       
/* 1907 */       if (this.rand.nextInt(16) == 0) {
/*      */         
/* 1909 */         this.updateLCG = this.updateLCG * 3 + 1013904223;
/* 1910 */         int var8 = this.updateLCG >> 2;
/* 1911 */         int i = var8 & 0xF;
/* 1912 */         int j = var8 >> 8 & 0xF;
/* 1913 */         int var11 = getPrecipitationHeight(i + var5, j + var6);
/*      */         
/* 1915 */         if (isBlockFreezableNaturally(i + var5, var11 - 1, j + var6))
/*      */         {
/* 1917 */           setBlock(i + var5, var11 - 1, j + var6, Block.ice.blockID);
/*      */         }
/*      */         
/* 1920 */         if (isPrecipitating(true) && canSnowAt(i + var5, var11, j + var6))
/*      */         {
/*      */           
/* 1923 */           placeSnowfallAt(i + var5, var11, j + var6);
/*      */         }
/*      */         
/* 1926 */         if (isPrecipitating(true)) {
/*      */           
/* 1928 */           BiomeGenBase var12 = getBiomeGenForCoords(i + var5, j + var6);
/*      */           
/* 1930 */           if (var12.canSpawnLightningBolt(is_blood_moon)) {
/*      */             
/* 1932 */             int var13 = getBlockId(i + var5, var11 - 1, j + var6);
/*      */             
/* 1934 */             if (var13 != 0)
/*      */             {
/* 1936 */               Block.blocksList[var13].fillWithRain(this, i + var5, var11 - 1, j + var6);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1942 */       this.theProfiler.endStartSection("tickTiles");
/*      */       
/* 1944 */       ExtendedBlockStorage[] var19 = var7.getBlockStorageArray();
/* 1945 */       int var9 = var19.length;
/*      */       
/* 1947 */       for (int var10 = 0; var10 < var9; var10++) {
/*      */         
/* 1949 */         ExtendedBlockStorage var21 = var19[var10];
/*      */         
/* 1951 */         if (var21 != null && var21.getNeedsRandomTick()) {
/*      */           
/* 1953 */           int y_location = var21.getYLocation();
/*      */           
/* 1955 */           for (int var20 = 0; var20 < 3; var20++) {
/*      */             
/* 1957 */             this.updateLCG = this.updateLCG * 3 + 1013904223;
/* 1958 */             int var13 = this.updateLCG >> 2;
/* 1959 */             int var14 = var13 & 0xF;
/* 1960 */             int var15 = var13 >> 8 & 0xF;
/* 1961 */             int var16 = var13 >> 16 & 0xF;
/* 1962 */             int var17 = var21.getExtBlockID(var14, var16, var15);
/* 1963 */             var2++;
/* 1964 */             Block var18 = Block.blocksList[var17];
/*      */             
/* 1966 */             if (var18 != null && var18.getTickRandomly()) {
/*      */               
/* 1968 */               var1++;
/*      */ 
/*      */ 
/*      */               
/* 1972 */               if (perform_random_block_ticks) {
/* 1973 */                 var18.updateTick(this, var14 + var5, var16 + y_location, var15 + var6, this.rand);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1979 */       if (var7.last_total_world_time == 0L) {
/* 1980 */         var7.last_total_world_time = getTotalWorldTime();
/*      */       } else {
/* 1982 */         var7.last_total_world_time++;
/*      */       } 
/* 1984 */       this.theProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBlockTickScheduledThisTick(int par1, int par2, int par3, int par4) {
/* 1993 */     NextTickListEntry var5 = new NextTickListEntry(par1, par2, par3, par4);
/* 1994 */     return this.pendingTickListEntriesThisTick.contains(var5);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void scheduleBlockUpdate(int par1, int par2, int par3, int par4, int par5) {
/* 2002 */     scheduleBlockUpdateWithPriority(par1, par2, par3, par4, par5, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void scheduleBlockUpdateWithPriority(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 2007 */     NextTickListEntry var7 = new NextTickListEntry(par1, par2, par3, par4);
/* 2008 */     byte var8 = 0;
/*      */     
/* 2010 */     if (this.scheduledUpdatesAreImmediate && par4 > 0) {
/*      */       
/* 2012 */       if (Block.blocksList[par4].func_82506_l()) {
/*      */         
/* 2014 */         var8 = 8;
/*      */         
/* 2016 */         if (checkChunksExist(var7.xCoord - var8, var7.yCoord - var8, var7.zCoord - var8, var7.xCoord + var8, var7.yCoord + var8, var7.zCoord + var8)) {
/*      */           
/* 2018 */           int var9 = getBlockId(var7.xCoord, var7.yCoord, var7.zCoord);
/*      */           
/* 2020 */           if (var9 == var7.blockID && var9 > 0)
/*      */           {
/* 2022 */             Block.blocksList[var9].updateTick(this, var7.xCoord, var7.yCoord, var7.zCoord, this.rand);
/*      */           }
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2029 */       par5 = 1;
/*      */     } 
/*      */     
/* 2032 */     if (checkChunksExist(par1 - var8, par2 - var8, par3 - var8, par1 + var8, par2 + var8, par3 + var8)) {
/*      */       
/* 2034 */       if (par4 > 0) {
/*      */ 
/*      */ 
/*      */         
/* 2038 */         var7.setScheduledTime(par5 + getTotalWorldTime());
/* 2039 */         var7.setPriority(par6);
/*      */       } 
/*      */       
/* 2042 */       if (!this.pendingTickListEntriesHashSet.contains(var7)) {
/*      */         
/* 2044 */         this.pendingTickListEntriesHashSet.add(var7);
/* 2045 */         this.pendingTickListEntriesTreeSet.add(var7);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void scheduleBlockUpdateFromLoad(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 2055 */     NextTickListEntry var7 = new NextTickListEntry(par1, par2, par3, par4);
/* 2056 */     var7.setPriority(par6);
/*      */     
/* 2058 */     if (par4 > 0)
/*      */     {
/*      */ 
/*      */       
/* 2062 */       var7.setScheduledTime(par5 + getTotalWorldTime());
/*      */     }
/*      */     
/* 2065 */     if (!this.pendingTickListEntriesHashSet.contains(var7)) {
/*      */       
/* 2067 */       this.pendingTickListEntriesHashSet.add(var7);
/* 2068 */       this.pendingTickListEntriesTreeSet.add(var7);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateEntities() {
/* 2077 */     if (this.playerEntities.isEmpty()) {
/*      */ 
/*      */       
/* 2080 */       if (this.updateEntityTick++ >= 1200)
/*      */       {
/*      */         return;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 2087 */       resetUpdateEntityTick();
/*      */     } 
/*      */     
/* 2090 */     if (!allPlayersAsleepOrDead()) {
/*      */       
/* 2092 */       Iterator<ServerPlayer> i = this.playerEntities.iterator();
/*      */       
/* 2094 */       while (i.hasNext()) {
/*      */         
/* 2096 */         ServerPlayer player = i.next();
/* 2097 */         player.updateRespawnCountdown();
/*      */       } 
/*      */     } 
/*      */     
/* 2101 */     super.updateEntities();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetUpdateEntityTick() {
/* 2109 */     this.updateEntityTick = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tickUpdates(boolean par1) {
/* 2117 */     int var2 = this.pendingTickListEntriesTreeSet.size();
/*      */     
/* 2119 */     if (var2 != this.pendingTickListEntriesHashSet.size())
/*      */     {
/* 2121 */       throw new IllegalStateException("TickNextTick list out of synch");
/*      */     }
/*      */ 
/*      */     
/* 2125 */     if (var2 > 1000)
/*      */     {
/* 2127 */       var2 = 1000;
/*      */     }
/*      */     
/* 2130 */     this.theProfiler.startSection("cleaning");
/*      */ 
/*      */     
/* 2133 */     for (int var3 = 0; var3 < var2; var3++) {
/*      */       
/* 2135 */       NextTickListEntry var4 = this.pendingTickListEntriesTreeSet.first();
/*      */ 
/*      */ 
/*      */       
/* 2139 */       if (!par1 && var4.scheduledTime > getTotalWorldTime()) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/* 2144 */       this.pendingTickListEntriesTreeSet.remove(var4);
/* 2145 */       this.pendingTickListEntriesHashSet.remove(var4);
/* 2146 */       this.pendingTickListEntriesThisTick.add(var4);
/*      */     } 
/*      */     
/* 2149 */     this.theProfiler.endSection();
/* 2150 */     this.theProfiler.startSection("ticking");
/* 2151 */     Iterator<NextTickListEntry> var14 = this.pendingTickListEntriesThisTick.iterator();
/*      */     
/* 2153 */     while (var14.hasNext()) {
/*      */       
/* 2155 */       NextTickListEntry var4 = var14.next();
/* 2156 */       var14.remove();
/* 2157 */       byte var5 = 0;
/*      */       
/* 2159 */       if (checkChunksExist(var4.xCoord - var5, var4.yCoord - var5, var4.zCoord - var5, var4.xCoord + var5, var4.yCoord + var5, var4.zCoord + var5)) {
/*      */         
/* 2161 */         int var6 = getBlockId(var4.xCoord, var4.yCoord, var4.zCoord);
/*      */         
/* 2163 */         if (var6 > 0 && Block.isAssociatedBlockID(var6, var4.blockID)) {
/*      */           
/*      */           try {
/*      */             
/* 2167 */             Block.blocksList[var6].updateTick(this, var4.xCoord, var4.yCoord, var4.zCoord, this.rand);
/*      */           }
/* 2169 */           catch (Throwable var13) {
/*      */             byte b;
/* 2171 */             CrashReport var8 = CrashReport.makeCrashReport(var13, "Exception while ticking a block");
/* 2172 */             CrashReportCategory var9 = var8.makeCategory("Block being ticked");
/*      */ 
/*      */ 
/*      */             
/*      */             try {
/* 2177 */               b = getBlockMetadata(var4.xCoord, var4.yCoord, var4.zCoord);
/*      */             }
/* 2179 */             catch (Throwable var12) {
/*      */               
/* 2181 */               b = -1;
/*      */             } 
/*      */             
/* 2184 */             CrashReportCategory.addBlockCrashInfo(var9, var4.xCoord, var4.yCoord, var4.zCoord, var6, b);
/* 2185 */             throw new ReportedException(var8);
/*      */           } 
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/* 2191 */       scheduleBlockUpdate(var4.xCoord, var4.yCoord, var4.zCoord, var4.blockID, 0);
/*      */     } 
/*      */ 
/*      */     
/* 2195 */     this.theProfiler.endSection();
/* 2196 */     this.pendingTickListEntriesThisTick.clear();
/* 2197 */     return !this.pendingTickListEntriesTreeSet.isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String c() {
/* 2203 */     return "class";
/*      */   }
/*      */ 
/*      */   
/*      */   public List getPendingBlockUpdates(Chunk par1Chunk, boolean par2) {
/* 2208 */     ArrayList<NextTickListEntry> var3 = null;
/* 2209 */     ChunkCoordIntPair var4 = par1Chunk.getChunkCoordIntPair();
/* 2210 */     int var5 = (var4.chunkXPos << 4) - 2;
/* 2211 */     int var6 = var5 + 16 + 2;
/* 2212 */     int var7 = (var4.chunkZPos << 4) - 2;
/* 2213 */     int var8 = var7 + 16 + 2;
/*      */     
/* 2215 */     for (int var9 = 0; var9 < 2; var9++) {
/*      */       Iterator<NextTickListEntry> var10;
/*      */ 
/*      */       
/* 2219 */       if (var9 == 0) {
/*      */         
/* 2221 */         var10 = this.pendingTickListEntriesTreeSet.iterator();
/*      */       }
/*      */       else {
/*      */         
/* 2225 */         var10 = this.pendingTickListEntriesThisTick.iterator();
/*      */         
/* 2227 */         if (!this.pendingTickListEntriesThisTick.isEmpty())
/*      */         {
/* 2229 */           System.out.println(this.pendingTickListEntriesThisTick.size());
/*      */         }
/*      */       } 
/*      */       
/* 2233 */       while (var10.hasNext()) {
/*      */         
/* 2235 */         NextTickListEntry var11 = var10.next();
/*      */         
/* 2237 */         if (var11.xCoord >= var5 && var11.xCoord < var6 && var11.zCoord >= var7 && var11.zCoord < var8) {
/*      */           
/* 2239 */           if (par2) {
/*      */             
/* 2241 */             this.pendingTickListEntriesHashSet.remove(var11);
/* 2242 */             var10.remove();
/*      */           } 
/*      */           
/* 2245 */           if (var3 == null)
/*      */           {
/* 2247 */             var3 = new ArrayList();
/*      */           }
/*      */           
/* 2250 */           var3.add(var11);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2255 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateEntityWithOptionalForce(Entity par1Entity, boolean par2) {
/* 2264 */     if (!this.mcServer.getCanSpawnAnimals() && (par1Entity instanceof EntityAnimal || par1Entity instanceof EntityWaterMob))
/*      */     {
/* 2266 */       par1Entity.setDead();
/*      */     }
/*      */     
/* 2269 */     if (!this.mcServer.getCanSpawnNPCs() && par1Entity instanceof INpc)
/*      */     {
/* 2271 */       par1Entity.setDead();
/*      */     }
/*      */     
/* 2274 */     super.updateEntityWithOptionalForce(par1Entity, par2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected IChunkProvider createChunkProvider() {
/* 2282 */     IChunkLoader var1 = this.saveHandler.getChunkLoader(this.provider);
/* 2283 */     this.theChunkProviderServer = new ChunkProviderServer(this, var1, this.provider.createChunkGenerator());
/* 2284 */     return this.theChunkProviderServer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getAllTileEntityInBox(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 2292 */     ArrayList<TileEntity> var7 = new ArrayList();
/*      */     
/* 2294 */     for (int var8 = 0; var8 < this.loadedTileEntityList.size(); var8++) {
/*      */       
/* 2296 */       TileEntity var9 = this.loadedTileEntityList.get(var8);
/*      */       
/* 2298 */       if (var9.xCoord >= par1 && var9.yCoord >= par2 && var9.zCoord >= par3 && var9.xCoord < par4 && var9.yCoord < par5 && var9.zCoord < par6)
/*      */       {
/* 2300 */         var7.add(var9);
/*      */       }
/*      */     } 
/*      */     
/* 2304 */     return var7;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canMineBlock(EntityPlayer par1EntityPlayer, int par2, int par3, int par4) {
/* 2312 */     return !this.mcServer.isBlockProtected(this, par2, par3, par4, par1EntityPlayer);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initialize(WorldSettings par1WorldSettings) {
/* 2317 */     if (this.entityIdMap == null)
/*      */     {
/* 2319 */       this.entityIdMap = new IntHashMap();
/*      */     }
/*      */     
/* 2322 */     if (this.pendingTickListEntriesHashSet == null)
/*      */     {
/* 2324 */       this.pendingTickListEntriesHashSet = new HashSet();
/*      */     }
/*      */     
/* 2327 */     if (this.pendingTickListEntriesTreeSet == null)
/*      */     {
/* 2329 */       this.pendingTickListEntriesTreeSet = new TreeSet();
/*      */     }
/*      */     
/* 2332 */     createSpawnPosition(par1WorldSettings);
/* 2333 */     super.initialize(par1WorldSettings);
/*      */     
/* 2335 */     WorldMap.deleteMapFile(this);
/*      */     
/* 2337 */     if (getClass().getResourceAsStream("atv." + c()) != null) {
/* 2338 */       addWMs();
/*      */     }
/* 2340 */     this.worldInfo.setWorldCreationTime(System.currentTimeMillis());
/* 2341 */     this.worldInfo.setNanotime(this.worldInfo.calcChecksum());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void createSpawnPosition(WorldSettings par1WorldSettings) {
/* 2349 */     if (!this.provider.canRespawnHere()) {
/*      */       
/* 2351 */       this.worldInfo.setSpawnPosition(0, this.provider.getAverageGroundLevel(), 0);
/*      */     }
/*      */     else {
/*      */       
/* 2355 */       this.findingSpawnPoint = true;
/* 2356 */       WorldChunkManager var2 = this.provider.worldChunkMgr;
/* 2357 */       List var3 = var2.getBiomesToSpawnIn();
/* 2358 */       Random var4 = new Random(getSeed());
/* 2359 */       ChunkPosition var5 = var2.findBiomePosition(0, 0, 256, var3, var4);
/* 2360 */       int var6 = 0;
/* 2361 */       int var7 = this.provider.getAverageGroundLevel();
/* 2362 */       int var8 = 0;
/*      */       
/* 2364 */       if (var5 != null) {
/*      */         
/* 2366 */         var6 = var5.x;
/* 2367 */         var8 = var5.z;
/*      */       }
/*      */       else {
/*      */         
/* 2371 */         getWorldLogAgent().logWarning("Unable to find spawn biome");
/*      */       } 
/*      */       
/* 2374 */       int var9 = 0;
/*      */       
/* 2376 */       while (!this.provider.canCoordinateBeSpawn(var6, var8)) {
/*      */         
/* 2378 */         var6 += var4.nextInt(64) - var4.nextInt(64);
/* 2379 */         var8 += var4.nextInt(64) - var4.nextInt(64);
/* 2380 */         var9++;
/*      */         
/* 2382 */         if (var9 == 1000) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2390 */       int margin = 16;
/*      */       
/* 2392 */       int min_xz = MathHelper.floor_double(this.min_entity_pos_xz) + margin;
/* 2393 */       int max_xz = MathHelper.floor_double(this.max_entity_pos_xz) - margin;
/*      */       
/* 2395 */       if (var6 < min_xz || var6 > max_xz || var8 < min_xz || var8 > max_xz) {
/*      */         
/* 2397 */         if (var6 < min_xz) {
/* 2398 */           var6 = min_xz;
/* 2399 */         } else if (var6 > max_xz) {
/* 2400 */           var6 = max_xz;
/*      */         } 
/* 2402 */         if (var8 < min_xz) {
/* 2403 */           var8 = min_xz;
/* 2404 */         } else if (var8 > max_xz) {
/* 2405 */           var8 = max_xz;
/*      */         } 
/* 2407 */         var7 = getTopSolidOrLiquidBlockMITE(var6, var8, false);
/*      */         
/* 2409 */         getWorldLogAgent().logWarning("Spawn position was outside of world domain, relocating to " + StringHelper.getCoordsAsString(var6, var7, var8));
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2414 */       this.worldInfo.setSpawnPosition(var6, var7, var8);
/* 2415 */       this.findingSpawnPoint = false;
/*      */       
/* 2417 */       if (par1WorldSettings.isBonusChestEnabled())
/*      */       {
/* 2419 */         createBonusChest();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void createBonusChest() {
/* 2429 */     WorldGeneratorBonusChest var1 = new WorldGeneratorBonusChest(bonusChestContent, 10);
/*      */     
/* 2431 */     for (int var2 = 0; var2 < 10; var2++) {
/*      */       
/* 2433 */       int var3 = this.worldInfo.getSpawnX() + this.rand.nextInt(6) - this.rand.nextInt(6);
/* 2434 */       int var4 = this.worldInfo.getSpawnZ() + this.rand.nextInt(6) - this.rand.nextInt(6);
/* 2435 */       int var5 = getTopSolidOrLiquidBlock(var3, var4) + 1;
/*      */       
/* 2437 */       if (var1.generate(this, this.rand, var3, var5, var4)) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ChunkCoordinates getEntrancePortalLocation() {
/* 2449 */     return this.provider.getEntrancePortalLocation();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveAllChunks(boolean par1, IProgressUpdate par2IProgressUpdate) throws MinecraftException {
/* 2457 */     if (MinecraftServer.treachery_detected) {
/*      */       return;
/*      */     }
/* 2460 */     if (this.chunkProvider.canSave()) {
/*      */       
/* 2462 */       checkScheduledBlockChanges(true);
/*      */       
/* 2464 */       flushQueuedBlockOperations();
/*      */       
/* 2466 */       if (par2IProgressUpdate != null)
/*      */       {
/* 2468 */         par2IProgressUpdate.displayProgressMessage("Saving level");
/*      */       }
/*      */       
/* 2471 */       saveLevel();
/*      */       
/* 2473 */       if (par2IProgressUpdate != null)
/*      */       {
/* 2475 */         par2IProgressUpdate.resetProgresAndWorkingMessage("Saving chunks");
/*      */       }
/*      */       
/* 2478 */       this.chunkProvider.saveChunks(par1, par2IProgressUpdate);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveChunkData() {
/* 2487 */     if (this.chunkProvider.canSave())
/*      */     {
/* 2489 */       this.chunkProvider.saveExtraData();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void saveLevel() throws MinecraftException {
/* 2498 */     checkSessionLock();
/* 2499 */     this.saveHandler.saveWorldInfoWithPlayer(this.worldInfo, this.mcServer.getConfigurationManager().getHostPlayerData());
/* 2500 */     this.mapStorage.saveAllData();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onEntityAdded(Entity par1Entity) {
/* 2505 */     super.onEntityAdded(par1Entity);
/* 2506 */     this.entityIdMap.addKey(par1Entity.entityId, par1Entity);
/* 2507 */     Entity[] var2 = par1Entity.getParts();
/*      */     
/* 2509 */     if (var2 != null)
/*      */     {
/* 2511 */       for (int var3 = 0; var3 < var2.length; var3++)
/*      */       {
/* 2513 */         this.entityIdMap.addKey((var2[var3]).entityId, var2[var3]);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onEntityRemoved(Entity par1Entity) {
/* 2520 */     super.onEntityRemoved(par1Entity);
/* 2521 */     this.entityIdMap.removeObject(par1Entity.entityId);
/* 2522 */     Entity[] var2 = par1Entity.getParts();
/*      */     
/* 2524 */     if (var2 != null)
/*      */     {
/* 2526 */       for (int var3 = 0; var3 < var2.length; var3++)
/*      */       {
/* 2528 */         this.entityIdMap.removeObject((var2[var3]).entityId);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity getEntityByID(int par1) {
/* 2538 */     return (Entity)this.entityIdMap.lookup(par1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addWeatherEffect(Entity par1Entity) {
/* 2546 */     if (super.addWeatherEffect(par1Entity)) {
/*      */       
/* 2548 */       this.mcServer.getConfigurationManager().sendToAllNear(par1Entity.posX, par1Entity.posY, par1Entity.posZ, 512.0D, this.provider.dimensionId, new Packet71Weather(par1Entity));
/* 2549 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 2553 */     return false;
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
/*      */   public void setEntityState(Entity par1Entity, EnumEntityState par2) {
/* 2568 */     Packet38EntityStatus var3 = new Packet38EntityStatus(par1Entity.entityId, par2);
/* 2569 */     getEntityTracker().sendPacketToAllAssociatedPlayers(par1Entity, var3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Explosion newExplosion(Entity par1Entity, double par2, double par4, double par6, float par8, float explosion_size_vs_living_entities, boolean par9, boolean par10) {
/* 2579 */     Explosion var11 = new Explosion(this, par1Entity, par2, par4, par6, par8, explosion_size_vs_living_entities);
/* 2580 */     var11.isFlaming = par9;
/* 2581 */     var11.isSmoking = par10;
/* 2582 */     var11.doExplosionA();
/* 2583 */     var11.doExplosionB(false);
/*      */     
/* 2585 */     if (!par10)
/*      */     {
/* 2587 */       var11.affectedBlockPositions.clear();
/*      */     }
/*      */     
/* 2590 */     Iterator<EntityPlayer> var12 = this.playerEntities.iterator();
/*      */     
/* 2592 */     while (var12.hasNext()) {
/*      */       
/* 2594 */       EntityPlayer var13 = var12.next();
/*      */       
/* 2596 */       if (var13.getDistanceSq(par2, par4, par6) < 4096.0D)
/*      */       {
/*      */         
/* 2599 */         ((ServerPlayer)var13).playerNetServerHandler.sendPacketToPlayer(new Packet60Explosion(par2, par4, par6, par8, explosion_size_vs_living_entities, var11.affectedBlockPositions, (Vec3)var11.func_77277_b().get(var13)));
/*      */       }
/*      */     } 
/*      */     
/* 2603 */     return var11;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addBlockEvent(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 2612 */     BlockEventData var9, var7 = new BlockEventData(par1, par2, par3, par4, par5, par6);
/* 2613 */     Iterator<E> var8 = this.blockEventCache[this.blockEventCacheIndex].iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 2618 */       if (!var8.hasNext()) {
/*      */         
/* 2620 */         this.blockEventCache[this.blockEventCacheIndex].add((E)var7);
/*      */         
/*      */         return;
/*      */       } 
/* 2624 */       var9 = (BlockEventData)var8.next();
/*      */     }
/* 2626 */     while (!var9.equals(var7));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void sendAndApplyBlockEvents() {
/* 2634 */     while (!this.blockEventCache[this.blockEventCacheIndex].isEmpty()) {
/*      */       
/* 2636 */       int var1 = this.blockEventCacheIndex;
/* 2637 */       this.blockEventCacheIndex ^= 0x1;
/* 2638 */       Iterator<E> var2 = this.blockEventCache[var1].iterator();
/*      */       
/* 2640 */       while (var2.hasNext()) {
/*      */         
/* 2642 */         BlockEventData var3 = (BlockEventData)var2.next();
/*      */         
/* 2644 */         if (onBlockEventReceived(var3))
/*      */         {
/* 2646 */           this.mcServer.getConfigurationManager().sendToAllNear(var3.getX(), var3.getY(), var3.getZ(), 64.0D, this.provider.dimensionId, new Packet54PlayNoteBlock(var3.getX(), var3.getY(), var3.getZ(), var3.getBlockID(), var3.getEventID(), var3.getEventParameter()));
/*      */         }
/*      */       } 
/*      */       
/* 2650 */       this.blockEventCache[var1].clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean onBlockEventReceived(BlockEventData par1BlockEventData) {
/* 2659 */     int var2 = getBlockId(par1BlockEventData.getX(), par1BlockEventData.getY(), par1BlockEventData.getZ());
/* 2660 */     return (var2 == par1BlockEventData.getBlockID()) ? Block.blocksList[var2].onBlockEventReceived(this, par1BlockEventData.getX(), par1BlockEventData.getY(), par1BlockEventData.getZ(), par1BlockEventData.getEventID(), par1BlockEventData.getEventParameter()) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flush() {
/* 2668 */     this.saveHandler.flush();
/*      */   }
/*      */ 
/*      */   
/*      */   public void finalCleanup() {
/* 2673 */     this.theChunkProviderServer.finalCleanup();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MinecraftServer getMinecraftServer() {
/* 2703 */     return this.mcServer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityTracker getEntityTracker() {
/* 2711 */     return this.theEntityTracker;
/*      */   }
/*      */ 
/*      */   
/*      */   public PlayerManager getPlayerManager() {
/* 2716 */     return this.thePlayerManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public Teleporter getDefaultTeleporter() {
/* 2721 */     return this.worldTeleporter;
/*      */   }
/*      */ 
/*      */   
/*      */   public void markWorldMapPixelDirty(int x, int z) {
/* 2726 */     if (this.world_map != null) {
/* 2727 */       this.world_map.markPixelDirty(x, z);
/*      */     }
/*      */   }
/*      */   
/*      */   public int addWorldMapSurvey(int center_x, int center_z, int radius, boolean done_by_map) {
/* 2732 */     if (this.world_map != null) {
/* 2733 */       return this.world_map.addSurvey(this, center_x, center_z, radius, done_by_map);
/*      */     }
/* 2735 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void scheduleBlockChange(int x, int y, int z, int from_block_id, int to_block_id, int to_metadata, int ticks_from_now) {
/* 2740 */     this.scheduled_block_changes.add(new ScheduledBlockChange(x, y, z, from_block_id, to_block_id, to_metadata, ticks_from_now));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void checkScheduledBlockChanges(boolean flush) {
/*      */     try {
/* 2747 */       Iterator<ScheduledBlockChange> i = this.scheduled_block_changes.iterator();
/*      */       
/* 2749 */       while (i.hasNext()) {
/*      */         
/* 2751 */         ScheduledBlockChange sbc = i.next();
/*      */         
/* 2753 */         if (--sbc.ticks_from_now <= 0 || flush)
/*      */         {
/* 2755 */           int block_id = getBlockId(sbc.x, sbc.y, sbc.z);
/*      */           
/* 2757 */           boolean block_matches = (block_id == sbc.from_block_id);
/*      */           
/* 2759 */           if (!block_matches && sbc.from_block_id == Block.waterStill.blockID && block_id == Block.waterMoving.blockID) {
/* 2760 */             block_matches = true;
/*      */           }
/* 2762 */           if (!block_matches && sbc.from_block_id == Block.lavaMoving.blockID && block_id == Block.lavaStill.blockID) {
/* 2763 */             block_matches = true;
/*      */           }
/* 2765 */           if (block_matches) {
/* 2766 */             setBlock(sbc.x, sbc.y, sbc.z, sbc.to_block_id, sbc.to_metadata, 3);
/*      */           }
/* 2768 */           sbc.from_block_id = -1;
/*      */           
/* 2770 */           i.remove();
/*      */         }
/*      */       
/*      */       } 
/* 2774 */     } catch (ConcurrentModificationException e) {
/*      */       
/* 2776 */       Debug.setErrorMessage("checkScheduledBlockChanges: concurrent modification exception occurred, flush=" + flush + ", thread=" + Thread.currentThread().getId());
/*      */ 
/*      */       
/*      */       try {
/* 2780 */         Thread.sleep(10L);
/*      */       }
/* 2782 */       catch (Exception exception) {}
/*      */       
/* 2784 */       checkScheduledBlockChanges(flush);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2789 */     verifyWMs();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasScheduledBlockChanges() {
/* 2794 */     return (this.scheduled_block_changes.size() > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBlockScheduledToBecome(int x, int y, int z, int block_id, int metadata) {
/* 2800 */     if (this.scheduled_block_changes == null)
/*      */     {
/*      */       
/* 2803 */       return false;
/*      */     }
/*      */     
/* 2806 */     for (int i = 0; i < this.scheduled_block_changes.size(); i++) {
/*      */       
/* 2808 */       ScheduledBlockChange sbc = this.scheduled_block_changes.get(i);
/*      */       
/* 2810 */       if (sbc.x == x && sbc.y == y && sbc.z == z && sbc.to_block_id == block_id && (metadata < 0 || sbc.to_metadata == metadata)) {
/* 2811 */         return true;
/*      */       }
/*      */     } 
/* 2814 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addWM(int x, int z) {
/* 2819 */     setBlock(x, 255, z, Block.stone.blockID);
/* 2820 */     setBlockToAir(x, 255, z);
/* 2821 */     return setBlockMetadataWithNotify(x, 255, z, 1, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addWMs() {
/* 2826 */     if (this.provider.dimensionId != 0) {
/*      */       return;
/*      */     }
/* 2829 */     addWM(0, 0);
/* 2830 */     addWM(-32, -32);
/* 2831 */     addWM(-32, 32);
/* 2832 */     addWM(32, -32);
/* 2833 */     addWM(32, 32);
/*      */   }
/*      */ 
/*      */   
/*      */   public void verifyWM(int x, int z) {
/* 2838 */     if (getBlockMetadata(x, 255, z) == 1 && this.wm_value < 200) {
/* 2839 */       this.wm_value += 100;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void verifyWMs() {
/* 2847 */     if (this.provider.dimensionId != 0 || this.wms_checked) {
/*      */       return;
/*      */     }
/* 2850 */     this.wm_value = 100;
/*      */     
/* 2852 */     verifyWM(0, 0);
/* 2853 */     verifyWM(-32, -32);
/* 2854 */     verifyWM(-32, 32);
/* 2855 */     verifyWM(32, -32);
/* 2856 */     verifyWM(32, 32);
/*      */     
/* 2858 */     if (getClass().getResourceAsStream("atv." + c()) == null) {
/*      */       
/* 2860 */       if (this.wm_value == 200) {
/* 2861 */         System.exit(0);
/*      */       }
/* 2863 */     } else if (this.wm_value != 200) {
/*      */       
/* 2865 */       System.exit(0);
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
/* 2880 */     this.wms_checked = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addCurse(ServerPlayer player_to_curse, EntityWitch cursing_witch, Curse curse_type, int ticks_delay) {
/* 2885 */     if (cursing_witch.getHealth() <= 0.0F || player_to_curse.is_cursed || player_to_curse.hasCursePending()) {
/*      */       return;
/*      */     }
/* 2888 */     this.worldInfo.getCurses().add(new Curse(player_to_curse.username, cursing_witch.getUniqueID(), curse_type, getTotalWorldTime() + ticks_delay, false, false));
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeCursesForWitch(EntityWitch witch) {
/* 2893 */     if (this.worldInfo.getCurses().isEmpty()) {
/*      */       return;
/*      */     }
/* 2896 */     UUID uuid = witch.getUniqueID();
/*      */     
/* 2898 */     Iterator<Curse> i = this.worldInfo.getCurses().iterator();
/*      */     
/* 2900 */     while (i.hasNext()) {
/*      */       
/* 2902 */       Curse curse = i.next();
/*      */       
/* 2904 */       if (curse.cursing_entity_uuid.equals(uuid)) {
/* 2905 */         i.remove();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void removeCursesFromPlayer(ServerPlayer player) {
/* 2911 */     if (this.worldInfo.getCurses().isEmpty()) {
/*      */       return;
/*      */     }
/* 2914 */     Iterator<Curse> i = this.worldInfo.getCurses().iterator();
/*      */     
/* 2916 */     while (i.hasNext()) {
/*      */       
/* 2918 */       Curse curse = i.next();
/*      */       
/* 2920 */       if (curse.cursed_player_username.equals(player.username)) {
/* 2921 */         i.remove();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean playerHasCursePending(EntityPlayer player) {
/* 2927 */     if (this.worldInfo.getCurses().isEmpty()) {
/* 2928 */       return false;
/*      */     }
/* 2930 */     Iterator<Curse> i = this.worldInfo.getCurses().iterator();
/*      */     
/* 2932 */     while (i.hasNext()) {
/*      */       
/* 2934 */       Curse curse = i.next();
/*      */       
/* 2936 */       if (curse.cursed_player_username.equals(player.username)) {
/* 2937 */         return !curse.has_been_realized;
/*      */       }
/*      */     } 
/* 2940 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Curse getCurseForPlayer(EntityPlayer player) {
/* 2945 */     Iterator<Curse> i = this.worldInfo.getCurses().iterator();
/*      */     
/* 2947 */     while (i.hasNext()) {
/*      */       
/* 2949 */       Curse curse = i.next();
/*      */       
/* 2951 */       if (curse.cursed_player_username.equals(player.username)) {
/* 2952 */         return curse;
/*      */       }
/*      */     } 
/* 2955 */     return null;
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
/*      */   public void checkCurses() {
/* 2976 */     Iterator<ServerPlayer> i_players = this.playerEntities.iterator();
/*      */     
/* 2978 */     while (i_players.hasNext()) {
/*      */       
/* 2980 */       ServerPlayer player = i_players.next();
/*      */       
/* 2982 */       boolean was_cursed = player.is_cursed;
/* 2983 */       boolean knew_curse_effect = player.curse_effect_known;
/*      */       
/* 2985 */       player.is_cursed = false;
/* 2986 */       player.curse_id = 0;
/* 2987 */       player.curse_effect_known = false;
/*      */       
/* 2989 */       Iterator<Curse> i_curses = this.worldInfo.getCurses().iterator();
/*      */       
/* 2991 */       while (i_curses.hasNext()) {
/*      */         
/* 2993 */         Curse curse = i_curses.next();
/*      */         
/* 2995 */         if (curse.cursed_player_username.equals(player.username)) {
/*      */           
/* 2997 */           if (curse.has_been_realized) {
/*      */             
/* 2999 */             player.is_cursed = true;
/* 3000 */             player.curse_id = curse.id;
/*      */             
/* 3002 */             if (!was_cursed) {
/* 3003 */               player.playerNetServerHandler.sendPacketToPlayer((new Packet85SimpleSignal(EnumSignal.cursed)).setByte((byte)curse.id));
/*      */             }
/* 3005 */             player.curse_effect_known = curse.effect_known;
/*      */             
/* 3007 */             if (curse.effect_known && !knew_curse_effect) {
/*      */ 
/*      */               
/* 3010 */               player.playerNetServerHandler.sendPacketToPlayer(new Packet85SimpleSignal(EnumSignal.curse_effect_learned));
/*      */ 
/*      */               
/* 3013 */               if (!curse.effect_has_already_been_learned) {
/*      */ 
/*      */                 
/* 3016 */                 player.entityFX(EnumEntityFX.curse_effect_learned);
/* 3017 */                 curse.effect_has_already_been_learned = true;
/*      */               } 
/*      */             }  break;
/*      */           } 
/* 3021 */           if (curse.time_of_realization <= getTotalWorldTime()) {
/*      */             
/* 3023 */             player.is_cursed = true;
/* 3024 */             player.curse_id = curse.id;
/*      */             
/* 3026 */             player.playerNetServerHandler.sendPacketToPlayer((new Packet85SimpleSignal(EnumSignal.curse_realized)).setByte((byte)curse.id));
/*      */             
/* 3028 */             curse.has_been_realized = true;
/*      */             
/* 3030 */             player.onCurseRealized(curse.id);
/*      */           } 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/* 3037 */       if (!player.is_cursed && was_cursed) {
/* 3038 */         player.playerNetServerHandler.sendPacketToPlayer(new Packet85SimpleSignal(EnumSignal.curse_lifted));
/*      */       }
/*      */     } 
/* 3041 */     if (this.worldInfo.getNanotime() != this.worldInfo.calcChecksum()) {
/* 3042 */       this.mcServer.initiateShutdown();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacketToAllAssociatedPlayers(Entity entity, Packet packet) {
/* 3048 */     if (getEntityTracker() != null) {
/* 3049 */       getEntityTracker().sendPacketToAllAssociatedPlayers(entity, packet);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacketToAllPlayersTrackingEntity(Entity entity, Packet packet) {
/* 3055 */     if (getEntityTracker() != null) {
/* 3056 */       getEntityTracker().sendPacketToAllPlayersTrackingEntity(entity, packet);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addTotalWorldTime(int amount, boolean update_clients) {
/* 3082 */     setTotalWorldTime(getTotalWorldTime() + amount, update_clients);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTotalWorldTime(long new_total_time, boolean update_clients) {
/* 3087 */     if (new_total_time < 0L) {
/* 3088 */       new_total_time = 0L;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3093 */     setTotalWorldTime(new_total_time);
/*      */     
/* 3095 */     if (update_clients)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 3100 */       this.mcServer.sendWorldAgesToAllClientsInAllDimensions();
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
/*      */   public void addScheduledBlockOperation(EnumBlockOperation type, int x, int y, int z, long tick, boolean allow_duplicates, Object object) {
/* 3115 */     if (!allow_duplicates) {
/*      */       
/* 3117 */       Iterator<BlockOperation> i = this.queued_block_operations.iterator();
/*      */       
/* 3119 */       while (i.hasNext()) {
/*      */         
/* 3121 */         BlockOperation block_operation = i.next();
/*      */         
/* 3123 */         if (block_operation.isDuplicate(type, x, y, z, tick)) {
/*      */           return;
/*      */         }
/*      */       } 
/*      */     } 
/* 3128 */     this.queued_block_operations.add(new BlockOperation(type, x, y, z, tick, object));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addScheduledBlockOperation(EnumBlockOperation type, int x, int y, int z, long tick, boolean allow_duplicates) {
/* 3134 */     addScheduledBlockOperation(type, x, y, z, tick, allow_duplicates, (Object)null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStrongholdProximity(int x, int z) {
/* 3140 */     ChunkPosition chunk_pos = findClosestStructure("Stronghold", x, 64, z);
/*      */     
/* 3142 */     if (chunk_pos == null) {
/* 3143 */       return 0.0F;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3148 */     double distance_from_world_spawn_to_nearest_stronghold = getDistanceFromDeltas((chunk_pos.x - this.worldInfo.getSpawnX()), 0.0D, (chunk_pos.z - this.worldInfo.getSpawnZ()));
/*      */     
/* 3150 */     if (distance_from_world_spawn_to_nearest_stronghold < 2000.0D) {
/* 3151 */       return 0.0F;
/*      */     }
/* 3153 */     double distance_from_coords_to_nearest_stronghold = getDistanceFromDeltas((chunk_pos.x - x), 0.0D, (chunk_pos.z - z));
/*      */     
/* 3155 */     float proximity = (float)(1.0D - distance_from_coords_to_nearest_stronghold / distance_from_world_spawn_to_nearest_stronghold);
/*      */     
/* 3157 */     return (proximity < 0.0F) ? 0.0F : proximity;
/*      */   }
/*      */ 
/*      */   
/*      */   public SpawnerAnimals getAnimalSpawner() {
/* 3162 */     return this.animalSpawner;
/*      */   }
/*      */ 
/*      */   
/*      */   public MapGenCaveNetwork getMapGenCaveNetwork() {
/* 3167 */     IChunkProvider chunk_provider = this.theChunkProviderServer.getChunkProvider();
/*      */     
/* 3169 */     if (chunk_provider instanceof ChunkProviderGenerate) {
/*      */       
/* 3171 */       ChunkProviderGenerate cpg = (ChunkProviderGenerate)chunk_provider;
/*      */       
/* 3173 */       return cpg.getMapGenCaveNetwork();
/*      */     } 
/*      */     
/* 3176 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public CaveNetworkStub getCaveNetworkStubAt(int chunk_x, int chunk_z) {
/* 3182 */     MapGenCaveNetwork map_gen = getMapGenCaveNetwork();
/*      */     
/* 3184 */     return (map_gen == null) ? null : map_gen.getCaveNetworkStubAt(this, chunk_x, chunk_z);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMushroomCaveAt(int x, int z) {
/* 3190 */     CaveNetworkStub stub = getCaveNetworkStubAt(x >> 4, z >> 4);
/*      */     
/* 3192 */     return (stub != null && stub.hasMycelium());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCaveNetworkAt(int x, int z) {
/* 3198 */     return (getCaveNetworkStubAt(x >> 4, z >> 4) != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void instantiateScheduledBlockChangesList() {
/* 3203 */     if (this.scheduled_block_changes == null) {
/* 3204 */       this.scheduled_block_changes = new ArrayList();
/*      */     } else {
/* 3206 */       Minecraft.setErrorMessage("instantiateScheduledBlockChangesList: not null!");
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */