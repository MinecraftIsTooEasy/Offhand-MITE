/*      */ package net.minecraft;
/*      */ 
/*      */ import com.google.common.base.Charsets;
/*      */ import java.io.File;
/*      */ import java.net.SocketAddress;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class ServerConfigurationManager
/*      */ {
/*   21 */   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
/*      */ 
/*      */   
/*      */   private final MinecraftServer mcServer;
/*      */ 
/*      */   
/*   27 */   public final List playerEntityList = new ArrayList();
/*   28 */   private final BanList bannedPlayers = new BanList(new File("banned-players.txt"));
/*   29 */   private final BanList bannedIPs = new BanList(new File("banned-ips.txt"));
/*      */ 
/*      */   
/*   32 */   private Set ops = new HashSet();
/*      */ 
/*      */   
/*   35 */   private Set whiteListedPlayers = new HashSet();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private IPlayerFileData playerNBTManagerObj;
/*      */ 
/*      */ 
/*      */   
/*   44 */   private final boolean whiteListEnforced = MinecraftServer.getServer().isDedicatedServer();
/*      */ 
/*      */   
/*      */   protected int maxPlayers;
/*      */ 
/*      */   
/*      */   protected int viewDistance;
/*      */ 
/*      */   
/*      */   private EnumGameType gameType;
/*      */   
/*      */   private boolean commandsAllowedForAll;
/*      */   
/*      */   private int playerPingIndex;
/*      */ 
/*      */   
/*      */   public ServerConfigurationManager(MinecraftServer par1MinecraftServer) {
/*   61 */     this.mcServer = par1MinecraftServer;
/*   62 */     this.bannedPlayers.setListActive(false);
/*   63 */     this.bannedIPs.setListActive(false);
/*   64 */     this.maxPlayers = 8;
/*      */   }
/*      */ 
/*      */   
/*      */   public void initializeConnectionToPlayer(INetworkManager par1INetworkManager, ServerPlayer par2EntityPlayerMP) {
/*   69 */     NBTTagCompound var3 = readPlayerDataFromFile(par2EntityPlayerMP);
/*   70 */     par2EntityPlayerMP.setWorld(this.mcServer.worldServerForDimension(par2EntityPlayerMP.dimension));
/*   71 */     par2EntityPlayerMP.theItemInWorldManager.setWorld((WorldServer)par2EntityPlayerMP.worldObj);
/*   72 */     String var4 = "local";
/*      */     
/*   74 */     if (par1INetworkManager.getSocketAddress() != null)
/*      */     {
/*   76 */       var4 = par1INetworkManager.getSocketAddress().toString();
/*      */     }
/*      */     
/*   79 */     this.mcServer.getLogAgent().logInfo(par2EntityPlayerMP.getCommandSenderName() + "[" + var4 + "] logged in with entity id " + par2EntityPlayerMP.entityId + " at (" + par2EntityPlayerMP.posX + ", " + par2EntityPlayerMP.posY + ", " + par2EntityPlayerMP.posZ + ")");
/*   80 */     WorldServer var5 = this.mcServer.worldServerForDimension(par2EntityPlayerMP.dimension);
/*   81 */     ChunkCoordinates var6 = var5.getSpawnPoint();
/*   82 */     func_72381_a(par2EntityPlayerMP, (ServerPlayer)null, var5);
/*   83 */     NetServerHandler var7 = new NetServerHandler(this.mcServer, par1INetworkManager, par2EntityPlayerMP);
/*      */     
/*   85 */     var7.sendPacketToPlayer(new Packet1Login(par2EntityPlayerMP.entityId, var5.getWorldInfo().getTerrainType(), par2EntityPlayerMP.theItemInWorldManager.getGameType(), var5.getWorldInfo().isHardcoreModeEnabled(), var5.provider.dimensionId, var5.difficultySetting, var5.getHeight(), getMaxPlayers(), var5.worldInfo.getVillageConditions(), var5.worldInfo.getAchievements(), var5.worldInfo.getEarliestMITEReleaseRunIn(), var5.worldInfo.getLatestMITEReleaseRunIn(), var5.areSkillsEnabled(), var5.getWorldCreationTime(), var5.getTotalWorldTime()));
/*   86 */     var7.sendPacketToPlayer(new Packet250CustomPayload("MC|Brand", getServerInstance().getServerModName().getBytes(Charsets.UTF_8)));
/*   87 */     var7.sendPacketToPlayer(new Packet6SpawnPosition(var6.posX, var6.posY, var6.posZ));
/*   88 */     var7.sendPacketToPlayer(new Packet202PlayerAbilities(par2EntityPlayerMP.capabilities));
/*   89 */     var7.sendPacketToPlayer(new Packet16BlockItemSwitch(par2EntityPlayerMP.inventory.currentItem));
/*   90 */     func_96456_a((ServerScoreboard)var5.getScoreboard(), par2EntityPlayerMP);
/*   91 */     updateTimeAndWeatherForPlayer(par2EntityPlayerMP, var5);
/*      */ 
/*      */     
/*   94 */     if (!par2EntityPlayerMP.isZevimrgvInTournament()) {
/*   95 */       sendChatMsg(ChatMessageComponent.createFromTranslationWithSubstitutions("multiplayer.player.joined", new Object[] { par2EntityPlayerMP.getTranslatedEntityName() }).setColor(EnumChatFormatting.YELLOW));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  100 */     if (Minecraft.isInTournamentMode()) {
/*      */ 
/*      */       
/*  103 */       var7.sendPacketToPlayer((new Packet85SimpleSignal(EnumSignal.tournament_mode)).setByte(DedicatedServer.tournament_type.ordinal()));
/*  104 */       var7.sendPacketToPlayer(new Packet3Chat(ChatMessageComponent.createFromText(DedicatedServer.getTournamentNotice()).setColor(EnumChatFormatting.YELLOW)));
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  109 */       var7.sendPacketToPlayer((new Packet85SimpleSignal(EnumSignal.tournament_mode)).setByte(-1));
/*      */     } 
/*      */     
/*  112 */     var7.sendPacketToPlayer((new Packet85SimpleSignal(EnumSignal.allotted_time)).setInteger(par2EntityPlayerMP.allotted_time));
/*      */     
/*  114 */     if (this.mcServer.isDedicatedServer()) {
/*  115 */       var7.sendPacketToPlayer(new Packet85SimpleSignal(EnumSignal.dedicated_server));
/*      */     }
/*  117 */     if (var5.peekUniqueDataId("map") > 0)
/*      */     {
/*  119 */       var7.sendPacketToPlayer((new Packet85SimpleSignal(EnumSignal.last_issued_map_id)).setShort(var5.peekUniqueDataId("map") - 1));
/*      */     }
/*  121 */     playerLoggedIn(par2EntityPlayerMP);
/*  122 */     var7.setPlayerLocation(par2EntityPlayerMP.posX, par2EntityPlayerMP.posY, par2EntityPlayerMP.posZ, par2EntityPlayerMP.rotationYaw, par2EntityPlayerMP.rotationPitch);
/*  123 */     this.mcServer.getNetworkThread().addPlayer(var7);
/*      */ 
/*      */ 
/*      */     
/*  127 */     par2EntityPlayerMP.sendWorldAgesToClient();
/*      */     
/*  129 */     if (this.mcServer.getTexturePack().length() > 0)
/*      */     {
/*  131 */       par2EntityPlayerMP.requestTexturePackLoad(this.mcServer.getTexturePack(), this.mcServer.textureSize());
/*      */     }
/*      */     
/*  134 */     Iterator<PotionEffect> var8 = par2EntityPlayerMP.getActivePotionEffects().iterator();
/*      */     
/*  136 */     while (var8.hasNext()) {
/*      */       
/*  138 */       PotionEffect var9 = var8.next();
/*  139 */       var7.sendPacketToPlayer(new Packet41EntityEffect(par2EntityPlayerMP.entityId, var9));
/*      */     } 
/*      */     
/*  142 */     par2EntityPlayerMP.addSelfToInternalCraftingInventory();
/*      */     
/*  144 */     if (var3 != null && var3.hasKey("Riding")) {
/*      */       
/*  146 */       Entity var10 = EntityList.createEntityFromNBT(var3.getCompoundTag("Riding"), var5);
/*      */       
/*  148 */       if (var10 != null) {
/*      */         
/*  150 */         var10.forceSpawn = true;
/*  151 */         var5.spawnEntityInWorld(var10);
/*  152 */         par2EntityPlayerMP.mountEntity(var10);
/*  153 */         var10.forceSpawn = false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_96456_a(ServerScoreboard par1ServerScoreboard, ServerPlayer par2EntityPlayerMP) {
/*  162 */     HashSet<ScoreObjective> var3 = new HashSet();
/*  163 */     Iterator<ScorePlayerTeam> var4 = par1ServerScoreboard.func_96525_g().iterator();
/*      */     
/*  165 */     while (var4.hasNext()) {
/*      */       
/*  167 */       ScorePlayerTeam var5 = var4.next();
/*  168 */       par2EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet209SetPlayerTeam(var5, 0));
/*      */     } 
/*      */     
/*  171 */     for (int var9 = 0; var9 < 3; var9++) {
/*      */       
/*  173 */       ScoreObjective var10 = par1ServerScoreboard.func_96539_a(var9);
/*      */       
/*  175 */       if (var10 != null && !var3.contains(var10)) {
/*      */         
/*  177 */         List var6 = par1ServerScoreboard.func_96550_d(var10);
/*  178 */         Iterator<Packet> var7 = var6.iterator();
/*      */         
/*  180 */         while (var7.hasNext()) {
/*      */           
/*  182 */           Packet var8 = var7.next();
/*  183 */           par2EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(var8);
/*      */         } 
/*      */         
/*  186 */         var3.add(var10);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlayerManager(WorldServer[] par1ArrayOfWorldServer) {
/*  196 */     this.playerNBTManagerObj = par1ArrayOfWorldServer[0].getSaveHandler().getSaveHandler();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_72375_a(ServerPlayer par1EntityPlayerMP, WorldServer par2WorldServer) {
/*  201 */     WorldServer var3 = par1EntityPlayerMP.getServerForPlayer();
/*      */     
/*  203 */     if (par2WorldServer != null)
/*      */     {
/*  205 */       par2WorldServer.getPlayerManager().removePlayer(par1EntityPlayerMP);
/*      */     }
/*      */     
/*  208 */     var3.getPlayerManager().addPlayer(par1EntityPlayerMP);
/*  209 */     var3.theChunkProviderServer.loadChunk((int)par1EntityPlayerMP.posX >> 4, (int)par1EntityPlayerMP.posZ >> 4);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEntityViewDistance() {
/*  214 */     return PlayerManager.getFurthestViewableBlock(getViewDistance());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound readPlayerDataFromFile(ServerPlayer par1EntityPlayerMP) {
/*  222 */     NBTTagCompound var3, var2 = this.mcServer.worldServers[0].getWorldInfo().getPlayerNBTTagCompound();
/*      */ 
/*      */     
/*  225 */     if (par1EntityPlayerMP.getCommandSenderName().equals(this.mcServer.getServerOwner()) && var2 != null) {
/*      */       
/*  227 */       par1EntityPlayerMP.readFromNBT(var2);
/*  228 */       var3 = var2;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  233 */       var3 = this.playerNBTManagerObj.readPlayerData(par1EntityPlayerMP);
/*      */     } 
/*      */     
/*  236 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writePlayerData(ServerPlayer par1EntityPlayerMP) {
/*  244 */     this.playerNBTManagerObj.writePlayerData(par1EntityPlayerMP);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playerLoggedIn(ServerPlayer par1EntityPlayerMP) {
/*  253 */     sendPacketToAllPlayers(new Packet201PlayerInfo(par1EntityPlayerMP.getCommandSenderName(), true, 1000, par1EntityPlayerMP.getExperienceLevel()));
/*  254 */     this.playerEntityList.add(par1EntityPlayerMP);
/*  255 */     WorldServer var2 = this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension);
/*  256 */     var2.spawnEntityInWorld(par1EntityPlayerMP);
/*  257 */     func_72375_a(par1EntityPlayerMP, (WorldServer)null);
/*      */     
/*  259 */     for (int var3 = 0; var3 < this.playerEntityList.size(); var3++) {
/*      */       
/*  261 */       ServerPlayer var4 = this.playerEntityList.get(var3);
/*      */       
/*  263 */       par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet201PlayerInfo(var4.getCommandSenderName(), true, var4.ping, var4.getExperienceLevel()));
/*      */     } 
/*      */     
/*  266 */     this.mcServer.playerLoggedIn(par1EntityPlayerMP);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void serverUpdateMountedMovingPlayer(ServerPlayer par1EntityPlayerMP) {
/*  274 */     par1EntityPlayerMP.getServerForPlayer().getPlayerManager().updateMountedMovingPlayer(par1EntityPlayerMP);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playerLoggedOut(ServerPlayer par1EntityPlayerMP) {
/*  282 */     writePlayerData(par1EntityPlayerMP);
/*  283 */     WorldServer var2 = par1EntityPlayerMP.getServerForPlayer();
/*      */     
/*  285 */     if (par1EntityPlayerMP.ridingEntity != null) {
/*      */       
/*  287 */       var2.removePlayerEntityDangerously(par1EntityPlayerMP.ridingEntity);
/*  288 */       System.out.println("removing player mount");
/*      */     } 
/*      */     
/*  291 */     var2.removeEntity(par1EntityPlayerMP);
/*  292 */     var2.getPlayerManager().removePlayer(par1EntityPlayerMP);
/*  293 */     this.playerEntityList.remove(par1EntityPlayerMP);
/*      */     
/*  295 */     sendPacketToAllPlayers(new Packet201PlayerInfo(par1EntityPlayerMP.getCommandSenderName(), false, 9999, par1EntityPlayerMP.getExperienceLevel()));
/*      */     
/*  297 */     this.mcServer.playerLoggedOut(par1EntityPlayerMP);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String allowUserToConnect(SocketAddress par1SocketAddress, String par2Str) {
/*  305 */     if (EntityPlayer.isZevimrgv(par2Str)) {
/*  306 */       return null;
/*      */     }
/*  308 */     if (this.bannedPlayers.isBanned(par2Str)) {
/*      */       
/*  310 */       BanEntry var6 = (BanEntry)this.bannedPlayers.getBannedList().get(par2Str);
/*  311 */       String var7 = "You are banned from this server!\nReason: " + var6.getBanReason();
/*      */       
/*  313 */       if (var6.getBanEndDate() != null)
/*      */       {
/*  315 */         var7 = var7 + "\nYour ban will be removed on " + dateFormat.format(var6.getBanEndDate());
/*      */       }
/*      */       
/*  318 */       return var7;
/*      */     } 
/*  320 */     if (!isAllowedToLogin(par2Str))
/*      */     {
/*  322 */       return "You are not white-listed on this server!";
/*      */     }
/*      */ 
/*      */     
/*  326 */     String var3 = par1SocketAddress.toString();
/*  327 */     var3 = var3.substring(var3.indexOf("/") + 1);
/*  328 */     var3 = var3.substring(0, var3.indexOf(":"));
/*      */     
/*  330 */     if (this.bannedIPs.isBanned(var3)) {
/*      */       
/*  332 */       BanEntry var4 = (BanEntry)this.bannedIPs.getBannedList().get(var3);
/*  333 */       String var5 = "Your IP address is banned from this server!\nReason: " + var4.getBanReason();
/*      */       
/*  335 */       if (var4.getBanEndDate() != null)
/*      */       {
/*  337 */         var5 = var5 + "\nYour ban will be removed on " + dateFormat.format(var4.getBanEndDate());
/*      */       }
/*      */       
/*  340 */       return var5;
/*      */     } 
/*      */ 
/*      */     
/*  344 */     return (this.playerEntityList.size() >= this.maxPlayers) ? "The server is full!" : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ServerPlayer createPlayerForUser(String par1Str) {
/*      */     Object var6;
/*  354 */     ArrayList<ServerPlayer> var2 = new ArrayList();
/*      */ 
/*      */     
/*  357 */     for (int var3 = 0; var3 < this.playerEntityList.size(); var3++) {
/*      */       
/*  359 */       ServerPlayer var4 = this.playerEntityList.get(var3);
/*      */       
/*  361 */       if (var4.getCommandSenderName().equalsIgnoreCase(par1Str))
/*      */       {
/*  363 */         var2.add(var4);
/*      */       }
/*      */     } 
/*      */     
/*  367 */     Iterator<ServerPlayer> var5 = var2.iterator();
/*      */     
/*  369 */     while (var5.hasNext()) {
/*      */       
/*  371 */       ServerPlayer var4 = var5.next();
/*  372 */       var4.playerNetServerHandler.kickPlayerFromServer("You logged in from another location");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  377 */     if (this.mcServer.isDemo()) {
/*      */       
/*  379 */       var6 = new DemoWorldManager(this.mcServer.worldServerForDimension(0));
/*      */     }
/*      */     else {
/*      */       
/*  383 */       var6 = new ItemInWorldManager(this.mcServer.worldServerForDimension(0));
/*      */     } 
/*      */     
/*  386 */     return new ServerPlayer(this.mcServer, this.mcServer.worldServerForDimension(0), par1Str, (ItemInWorldManager)var6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ServerPlayer respawnPlayer(ServerPlayer par1EntityPlayerMP, int par2, boolean par3) {
/*      */     Object var6;
/*  397 */     par1EntityPlayerMP.getServerForPlayer().getEntityTracker().removePlayerFromTrackers(par1EntityPlayerMP);
/*  398 */     par1EntityPlayerMP.getServerForPlayer().getEntityTracker().removeEntityFromAllTrackingPlayers(par1EntityPlayerMP);
/*  399 */     par1EntityPlayerMP.getServerForPlayer().getPlayerManager().removePlayer(par1EntityPlayerMP);
/*  400 */     this.playerEntityList.remove(par1EntityPlayerMP);
/*  401 */     this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension).removePlayerEntityDangerously(par1EntityPlayerMP);
/*  402 */     ChunkCoordinates var4 = par1EntityPlayerMP.getBedLocation();
/*  403 */     boolean var5 = par1EntityPlayerMP.isSpawnForced();
/*  404 */     par1EntityPlayerMP.dimension = par2;
/*      */ 
/*      */     
/*  407 */     if (this.mcServer.isDemo()) {
/*      */       
/*  409 */       var6 = new DemoWorldManager(this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension));
/*      */     }
/*      */     else {
/*      */       
/*  413 */       var6 = new ItemInWorldManager(this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  419 */     ServerPlayer var7 = new ServerPlayer(this.mcServer, this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension), par1EntityPlayerMP.getCommandSenderName(), (ItemInWorldManager)var6);
/*  420 */     var7.playerNetServerHandler = par1EntityPlayerMP.playerNetServerHandler;
/*      */ 
/*      */     
/*  423 */     var7.experience = par1EntityPlayerMP.respawn_experience;
/*  424 */     var7.ticks_logged_in = par1EntityPlayerMP.ticks_logged_in;
/*  425 */     var7.setProtein(par1EntityPlayerMP.getProtein());
/*  426 */     var7.setEssentialFats(par1EntityPlayerMP.getEssentialFats());
/*  427 */     var7.setPhytonutrients(par1EntityPlayerMP.getPhytonutrients());
/*  428 */     var7.setInsulinResistance(par1EntityPlayerMP.getInsulinResistance());
/*  429 */     var7.insulin_resistance_level = par1EntityPlayerMP.insulin_resistance_level;
/*  430 */     var7.master_hash_received = par1EntityPlayerMP.master_hash_received;
/*  431 */     var7.master_hash_validated = par1EntityPlayerMP.master_hash_validated;
/*      */ 
/*      */     
/*  434 */     var7.Sr = par1EntityPlayerMP.Sr;
/*  435 */     var7.raS = par1EntityPlayerMP.raS;
/*  436 */     var7.sacred_stones_placed = par1EntityPlayerMP.sacred_stones_placed;
/*  437 */     var7.allotted_time = par1EntityPlayerMP.allotted_time;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  442 */     var7.last_skill_learned_on_day = par1EntityPlayerMP.last_skill_learned_on_day;
/*  443 */     var7.setSkills(par1EntityPlayerMP.getSkills());
/*      */     
/*  445 */     var7.stats = par1EntityPlayerMP.stats;
/*      */ 
/*      */ 
/*      */     
/*  449 */     var7.clonePlayer(par1EntityPlayerMP, par3);
/*  450 */     var7.entityId = par1EntityPlayerMP.entityId;
/*  451 */     WorldServer var8 = this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension);
/*  452 */     func_72381_a(var7, par1EntityPlayerMP, var8);
/*      */ 
/*      */     
/*  455 */     if (var4 != null) {
/*      */       
/*  457 */       ChunkCoordinates chunkCoordinates = EntityPlayer.verifyRespawnCoordinates(this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension), var4, var5);
/*      */       
/*  459 */       if (chunkCoordinates != null) {
/*      */         
/*  461 */         var7.setLocationAndAngles((chunkCoordinates.posX + 0.5F), (chunkCoordinates.posY + 0.1F), (chunkCoordinates.posZ + 0.5F), 0.0F, 0.0F);
/*  462 */         var7.setSpawnChunk(var4, var5);
/*      */       }
/*      */       else {
/*      */         
/*  466 */         var7.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(0, 0));
/*      */       } 
/*      */     } 
/*      */     
/*  470 */     var8.theChunkProviderServer.loadChunk((int)var7.posX >> 4, (int)var7.posZ >> 4);
/*      */     
/*  472 */     while (!var8.getCollidingBoundingBoxes(var7, var7.boundingBox).isEmpty())
/*      */     {
/*  474 */       var7.setPosition(var7.posX, var7.posY + 1.0D, var7.posZ);
/*      */     }
/*      */ 
/*      */     
/*  478 */     var7.playerNetServerHandler.sendPacketToPlayer(new Packet9Respawn(var7.dimension, (byte)var7.worldObj.difficultySetting, var7.worldObj.getWorldInfo().getTerrainType(), var7.worldObj.getHeight(), var7.theItemInWorldManager.getGameType(), var7.worldObj.getWorldCreationTime(), var7.worldObj.getTotalWorldTime()));
/*  479 */     ChunkCoordinates var9 = var8.getSpawnPoint();
/*  480 */     var7.playerNetServerHandler.setPlayerLocation(var7.posX, var7.posY, var7.posZ, var7.rotationYaw, var7.rotationPitch);
/*  481 */     var7.playerNetServerHandler.sendPacketToPlayer(new Packet6SpawnPosition(var9.posX, var9.posY, var9.posZ));
/*      */     
/*  483 */     var7.playerNetServerHandler.sendPacketToPlayer(new Packet43Experience(var7.experience));
/*  484 */     updateTimeAndWeatherForPlayer(var7, var8);
/*  485 */     var8.getPlayerManager().addPlayer(var7);
/*  486 */     var8.spawnEntityInWorld(var7);
/*  487 */     this.playerEntityList.add(var7);
/*  488 */     var7.addSelfToInternalCraftingInventory();
/*  489 */     var7.setHealth(var7.getHealth());
/*      */     
/*  491 */     var7.afterRespawn();
/*      */     
/*  493 */     return var7;
/*      */   }
/*      */ 
/*      */   
/*      */   public void teleportPlayerInsideDimension(ServerPlayer player, double posX, double posY, double posZ, boolean sync_last_tick_pos_on_next_update) {
/*  498 */     WorldServer world_server = this.mcServer.worldServerForDimension(player.dimension);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  508 */     if (player.isEntityAlive()) {
/*      */ 
/*      */       
/*  511 */       player.setLocationAndAngles(posX, posY, posZ, player.rotationYaw, player.rotationPitch);
/*      */       
/*  513 */       if (sync_last_tick_pos_on_next_update) {
/*  514 */         player.sync_last_tick_pos_on_next_update = true;
/*      */       }
/*  516 */       world_server.updateEntityWithOptionalForce(player, false);
/*      */     } 
/*      */     
/*  519 */     func_72375_a(player, world_server);
/*      */     
/*  521 */     player.playerNetServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
/*      */     
/*  523 */     if (sync_last_tick_pos_on_next_update) {
/*  524 */       player.sync_last_tick_pos_on_next_update = true;
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
/*      */   public void transferPlayerToDimension(ServerPlayer par1EntityPlayerMP, int par2) {
/*  563 */     int var3 = par1EntityPlayerMP.dimension;
/*  564 */     WorldServer var4 = this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension);
/*  565 */     par1EntityPlayerMP.dimension = par2;
/*  566 */     WorldServer var5 = this.mcServer.worldServerForDimension(par1EntityPlayerMP.dimension);
/*      */     
/*  568 */     par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet9Respawn(par1EntityPlayerMP.dimension, (byte)par1EntityPlayerMP.worldObj.difficultySetting, var5.getWorldInfo().getTerrainType(), var5.getHeight(), par1EntityPlayerMP.theItemInWorldManager.getGameType(), var5.getWorldCreationTime(), var5.getTotalWorldTime()));
/*  569 */     var4.removePlayerEntityDangerously(par1EntityPlayerMP);
/*  570 */     par1EntityPlayerMP.isDead = false;
/*  571 */     transferEntityToWorld(par1EntityPlayerMP, var3, var4, var5);
/*  572 */     func_72375_a(par1EntityPlayerMP, var4);
/*  573 */     par1EntityPlayerMP.playerNetServerHandler.setPlayerLocation(par1EntityPlayerMP.posX, par1EntityPlayerMP.posY, par1EntityPlayerMP.posZ, par1EntityPlayerMP.rotationYaw, par1EntityPlayerMP.rotationPitch);
/*  574 */     par1EntityPlayerMP.theItemInWorldManager.setWorld(var5);
/*  575 */     updateTimeAndWeatherForPlayer(par1EntityPlayerMP, var5);
/*  576 */     syncPlayerInventory(par1EntityPlayerMP);
/*  577 */     Iterator<PotionEffect> var6 = par1EntityPlayerMP.getActivePotionEffects().iterator();
/*      */     
/*  579 */     while (var6.hasNext()) {
/*      */       
/*  581 */       PotionEffect var7 = var6.next();
/*  582 */       par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(par1EntityPlayerMP.entityId, var7));
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
/*  595 */     par1EntityPlayerMP.syncClientPlayer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void transferEntityToWorld(Entity par1Entity, int par2, WorldServer par3WorldServer, WorldServer par4WorldServer) {
/*  603 */     int dimension_id_from = par2;
/*  604 */     int dimension_id_to = par1Entity.dimension;
/*      */     
/*  606 */     double var5 = par1Entity.posX;
/*  607 */     double var7 = par1Entity.posZ;
/*  608 */     double var9 = 8.0D;
/*  609 */     double var11 = par1Entity.posX;
/*  610 */     double var13 = par1Entity.posY;
/*  611 */     double var15 = par1Entity.posZ;
/*  612 */     float var17 = par1Entity.rotationYaw;
/*  613 */     par3WorldServer.theProfiler.startSection("moving");
/*      */     
/*  615 */     if (dimension_id_to != dimension_id_from) {
/*      */       
/*  617 */       par1Entity.ticks_since_portal_teleport = 0;
/*      */       
/*  619 */       if (dimension_id_from == -1) {
/*      */         
/*  621 */         var5 *= var9;
/*  622 */         var7 *= var9;
/*      */       }
/*  624 */       else if (dimension_id_to == -1) {
/*      */         
/*  626 */         var5 /= var9;
/*  627 */         var7 /= var9;
/*      */       } 
/*      */     } 
/*      */     
/*  631 */     if (par1Entity.dimension == -1) {
/*      */ 
/*      */ 
/*      */       
/*  635 */       par1Entity.setLocationAndAngles(var5, par1Entity.posY, var7, par1Entity.rotationYaw, par1Entity.rotationPitch);
/*      */       
/*  637 */       if (par1Entity.isEntityAlive())
/*      */       {
/*  639 */         par3WorldServer.updateEntityWithOptionalForce(par1Entity, false);
/*      */       
/*      */       }
/*      */     }
/*  643 */     else if (par1Entity.dimension == 0 || par1Entity.dimension == -2) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  648 */       par1Entity.setLocationAndAngles(var5, par1Entity.posY, var7, par1Entity.rotationYaw, par1Entity.rotationPitch);
/*      */       
/*  650 */       if (par1Entity.isEntityAlive())
/*      */       {
/*  652 */         par3WorldServer.updateEntityWithOptionalForce(par1Entity, false);
/*      */       }
/*      */     } else {
/*      */       ChunkCoordinates var18;
/*      */ 
/*      */ 
/*      */       
/*  659 */       if (par2 == 1) {
/*      */         
/*  661 */         var18 = par4WorldServer.getSpawnPoint();
/*      */       }
/*      */       else {
/*      */         
/*  665 */         var18 = par4WorldServer.getEntrancePortalLocation();
/*      */       } 
/*      */       
/*  668 */       var5 = var18.posX;
/*  669 */       par1Entity.posY = var18.posY;
/*  670 */       var7 = var18.posZ;
/*  671 */       par1Entity.setLocationAndAngles(var5, par1Entity.posY, var7, 90.0F, 0.0F);
/*      */       
/*  673 */       if (par1Entity.isEntityAlive())
/*      */       {
/*  675 */         par3WorldServer.updateEntityWithOptionalForce(par1Entity, false);
/*      */       }
/*      */     } 
/*      */     
/*  679 */     if (par1Entity.isAddedToAChunk()) {
/*  680 */       par1Entity.removeFromChunk();
/*      */     }
/*  682 */     par3WorldServer.theProfiler.endSection();
/*      */     
/*  684 */     if (par2 != 1) {
/*      */       
/*  686 */       par3WorldServer.theProfiler.startSection("placing");
/*  687 */       var5 = MathHelper.clamp_int((int)var5, -29999872, 29999872);
/*  688 */       var7 = MathHelper.clamp_int((int)var7, -29999872, 29999872);
/*      */       
/*  690 */       if (par1Entity.isEntityAlive()) {
/*      */         
/*  692 */         par4WorldServer.spawnEntityInWorld(par1Entity);
/*  693 */         par1Entity.setLocationAndAngles(var5, par1Entity.posY, var7, par1Entity.rotationYaw, par1Entity.rotationPitch);
/*  694 */         par4WorldServer.updateEntityWithOptionalForce(par1Entity, false);
/*      */         
/*  696 */         par4WorldServer.getDefaultTeleporter().placeInPortal(par1Entity, dimension_id_from, var11, var13, var15, var17);
/*      */       } 
/*      */       
/*  699 */       par3WorldServer.theProfiler.endSection();
/*      */     } 
/*      */     
/*  702 */     par1Entity.setWorld(par4WorldServer);
/*      */     
/*  704 */     par1Entity.onTransferToWorld();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPlayerInfoToAllPlayers(boolean immediate) {
/*  714 */     if (immediate || ++this.playerPingIndex > 600)
/*      */     {
/*  716 */       this.playerPingIndex = 0;
/*      */     }
/*      */     
/*  719 */     if (this.playerPingIndex < this.playerEntityList.size()) {
/*      */       
/*  721 */       ServerPlayer var1 = this.playerEntityList.get(this.playerPingIndex);
/*      */       
/*  723 */       sendPacketToAllPlayers(new Packet201PlayerInfo(var1.getCommandSenderName(), true, var1.ping, var1.getExperienceLevel()));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPacketToAllPlayers(Packet par1Packet) {
/*  732 */     for (int var2 = 0; var2 < this.playerEntityList.size(); var2++)
/*      */     {
/*  734 */       ((ServerPlayer)this.playerEntityList.get(var2)).playerNetServerHandler.sendPacketToPlayer(par1Packet);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPacketToAllPlayersInDimension(Packet par1Packet, int par2) {
/*  743 */     for (int var3 = 0; var3 < this.playerEntityList.size(); var3++) {
/*      */       
/*  745 */       ServerPlayer var4 = this.playerEntityList.get(var3);
/*      */       
/*  747 */       if (var4.dimension == par2)
/*      */       {
/*  749 */         var4.playerNetServerHandler.sendPacketToPlayer(par1Packet);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPlayerListAsString() {
/*  759 */     String var1 = "";
/*      */     
/*  761 */     for (int var2 = 0; var2 < this.playerEntityList.size(); var2++) {
/*      */       
/*  763 */       if (var2 > 0)
/*      */       {
/*  765 */         var1 = var1 + ", ";
/*      */       }
/*      */       
/*  768 */       var1 = var1 + ((ServerPlayer)this.playerEntityList.get(var2)).getCommandSenderName();
/*      */     } 
/*      */     
/*  771 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getAllUsernames() {
/*  779 */     String[] var1 = new String[this.playerEntityList.size()];
/*      */     
/*  781 */     for (int var2 = 0; var2 < this.playerEntityList.size(); var2++)
/*      */     {
/*  783 */       var1[var2] = ((ServerPlayer)this.playerEntityList.get(var2)).getCommandSenderName();
/*      */     }
/*      */     
/*  786 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public BanList getBannedPlayers() {
/*  791 */     return this.bannedPlayers;
/*      */   }
/*      */ 
/*      */   
/*      */   public BanList getBannedIPs() {
/*  796 */     return this.bannedIPs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addOp(String par1Str) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeOp(String par1Str) {
/*  814 */     this.ops.remove(par1Str.toLowerCase());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAllowedToLogin(String par1Str) {
/*  822 */     if (EntityPlayer.isZevimrgv(par1Str)) {
/*  823 */       return true;
/*      */     }
/*  825 */     par1Str = par1Str.trim().toLowerCase();
/*      */     
/*  827 */     return (!this.whiteListEnforced || this.ops.contains(par1Str) || isPlayerWhiteListed(par1Str));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWhiteListAllInclusive() {
/*  832 */     return getWhiteListedPlayers().contains("*");
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPlayerWhiteListed(String username) {
/*  837 */     return (isWhiteListAllInclusive() || getWhiteListedPlayers().contains(username));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPlayerOpped(String par1Str) {
/*  846 */     return false;
/*      */   }
/*      */   
/*      */   public ServerPlayer getPlayerForUsername(String par1Str) {
/*      */     ServerPlayer var3;
/*  851 */     Iterator<ServerPlayer> var2 = this.playerEntityList.iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  856 */       if (!var2.hasNext())
/*      */       {
/*  858 */         return null;
/*      */       }
/*      */       
/*  861 */       var3 = var2.next();
/*      */     }
/*  863 */     while (!var3.getCommandSenderName().equalsIgnoreCase(par1Str));
/*      */     
/*  865 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List findPlayers(ChunkCoordinates par1ChunkCoordinates, int par2, int par3, int par4, int par5, int par6, int par7, Map par8Map, String par9Str, String par10Str, World par11World) {
/*  873 */     if (this.playerEntityList.isEmpty())
/*      */     {
/*  875 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  879 */     Object var12 = new ArrayList();
/*  880 */     boolean var13 = (par4 < 0);
/*  881 */     boolean var14 = (par9Str != null && par9Str.startsWith("!"));
/*  882 */     boolean var15 = (par10Str != null && par10Str.startsWith("!"));
/*  883 */     int var16 = par2 * par2;
/*  884 */     int var17 = par3 * par3;
/*  885 */     par4 = MathHelper.abs_int(par4);
/*      */     
/*  887 */     if (var14)
/*      */     {
/*  889 */       par9Str = par9Str.substring(1);
/*      */     }
/*      */     
/*  892 */     if (var15)
/*      */     {
/*  894 */       par10Str = par10Str.substring(1);
/*      */     }
/*      */     
/*  897 */     for (int var18 = 0; var18 < this.playerEntityList.size(); var18++) {
/*      */       
/*  899 */       ServerPlayer var19 = this.playerEntityList.get(var18);
/*      */       
/*  901 */       if ((par11World == null || var19.worldObj == par11World) && (par9Str == null || var14 != par9Str.equalsIgnoreCase(var19.getEntityName()))) {
/*      */         
/*  903 */         if (par10Str != null) {
/*      */           
/*  905 */           Team var20 = var19.getTeam();
/*  906 */           String var21 = (var20 == null) ? "" : var20.func_96661_b();
/*      */           
/*  908 */           if (var15 == par10Str.equalsIgnoreCase(var21)) {
/*      */             continue;
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  914 */         if (par1ChunkCoordinates != null && (par2 > 0 || par3 > 0)) {
/*      */           
/*  916 */           float var22 = par1ChunkCoordinates.getDistanceSquaredToChunkCoordinates(var19.getPlayerCoordinates());
/*      */           
/*  918 */           if ((par2 > 0 && var22 < var16) || (par3 > 0 && var22 > var17)) {
/*      */             continue;
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  925 */         if (func_96457_a(var19, par8Map) && (par5 == EnumGameType.NOT_SET.getID() || par5 == var19.theItemInWorldManager.getGameType().getID()) && (par6 <= 0 || var19.getExperienceLevel() >= par6) && var19.getExperienceLevel() <= par7)
/*      */         {
/*  927 */           ((List<ServerPlayer>)var12).add(var19);
/*      */         }
/*      */       } 
/*      */       continue;
/*      */     } 
/*  932 */     if (par1ChunkCoordinates != null)
/*      */     {
/*  934 */       Collections.sort((List)var12, new PlayerPositionComparator(par1ChunkCoordinates));
/*      */     }
/*      */     
/*  937 */     if (var13)
/*      */     {
/*  939 */       Collections.reverse((List)var12);
/*      */     }
/*      */     
/*  942 */     if (par4 > 0)
/*      */     {
/*  944 */       var12 = ((List)var12).subList(0, Math.min(par4, ((List)var12).size()));
/*      */     }
/*      */     
/*  947 */     return (List)var12;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean func_96457_a(EntityPlayer par1EntityPlayer, Map par2Map) {
/*  953 */     if (par2Map != null && par2Map.size() != 0) {
/*      */       Map.Entry var4; boolean var6; int var10;
/*  955 */       Iterator<Map.Entry> var3 = par2Map.entrySet().iterator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/*  962 */         if (!var3.hasNext())
/*      */         {
/*  964 */           return true;
/*      */         }
/*      */         
/*  967 */         var4 = var3.next();
/*  968 */         String var5 = (String)var4.getKey();
/*  969 */         var6 = false;
/*      */         
/*  971 */         if (var5.endsWith("_min") && var5.length() > 4) {
/*      */           
/*  973 */           var6 = true;
/*  974 */           var5 = var5.substring(0, var5.length() - 4);
/*      */         } 
/*      */         
/*  977 */         Scoreboard var7 = par1EntityPlayer.getWorldScoreboard();
/*  978 */         ScoreObjective var8 = var7.getObjective(var5);
/*      */         
/*  980 */         if (var8 == null)
/*      */         {
/*  982 */           return false;
/*      */         }
/*      */         
/*  985 */         Score var9 = par1EntityPlayer.getWorldScoreboard().func_96529_a(par1EntityPlayer.getEntityName(), var8);
/*  986 */         var10 = var9.getScorePoints();
/*      */         
/*  988 */         if (var10 < ((Integer)var4.getValue()).intValue() && var6)
/*      */         {
/*  990 */           return false;
/*      */         }
/*      */       }
/*  993 */       while (var10 <= ((Integer)var4.getValue()).intValue() || var6);
/*      */       
/*  995 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  999 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendToAllNear(double par1, double par3, double par5, double par7, int par9, Packet par10Packet) {
/* 1008 */     sendToAllNearExcept((EntityPlayer)null, par1, par3, par5, par7, par9, par10Packet);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendToAllNearExcept(EntityPlayer par1EntityPlayer, double par2, double par4, double par6, double par8, int par10, Packet par11Packet) {
/* 1017 */     for (int var12 = 0; var12 < this.playerEntityList.size(); var12++) {
/*      */       
/* 1019 */       ServerPlayer var13 = this.playerEntityList.get(var12);
/*      */       
/* 1021 */       if (var13 != par1EntityPlayer && var13.dimension == par10) {
/*      */         
/* 1023 */         double var14 = par2 - var13.posX;
/* 1024 */         double var16 = par4 - var13.posY;
/* 1025 */         double var18 = par6 - var13.posZ;
/*      */         
/* 1027 */         if (var14 * var14 + var16 * var16 + var18 * var18 < par8 * par8)
/*      */         {
/* 1029 */           var13.playerNetServerHandler.sendPacketToPlayer(par11Packet);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendToAllOutdoorsNear(double par1, double par3, double par5, double par7, int par9, Packet par10Packet) {
/* 1037 */     sendToAllOutdoorsNearExcept((EntityPlayer)null, par1, par3, par5, par7, par9, par10Packet);
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendToAllOutdoorsNearExcept(EntityPlayer par1EntityPlayer, double par2, double par4, double par6, double par8, int par10, Packet par11Packet) {
/* 1042 */     for (int var12 = 0; var12 < this.playerEntityList.size(); var12++) {
/*      */       
/* 1044 */       ServerPlayer var13 = this.playerEntityList.get(var12);
/*      */       
/* 1046 */       if (var13 != par1EntityPlayer && var13.dimension == par10 && var13.isOutdoors()) {
/*      */         
/* 1048 */         double var14 = par2 - var13.posX;
/* 1049 */         double var16 = par4 - var13.posY;
/* 1050 */         double var18 = par6 - var13.posZ;
/*      */         
/* 1052 */         if (var14 * var14 + var16 * var16 + var18 * var18 < par8 * par8)
/*      */         {
/* 1054 */           var13.playerNetServerHandler.sendPacketToPlayer(par11Packet);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveAllPlayerData() {
/* 1065 */     if (MinecraftServer.treachery_detected) {
/*      */       return;
/*      */     }
/* 1068 */     for (int var1 = 0; var1 < this.playerEntityList.size(); var1++)
/*      */     {
/* 1070 */       writePlayerData(this.playerEntityList.get(var1));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToWhiteList(String par1Str) {
/* 1079 */     this.whiteListedPlayers.add(par1Str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeFromWhitelist(String par1Str) {
/* 1087 */     this.whiteListedPlayers.remove(par1Str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set getWhiteListedPlayers() {
/* 1095 */     return this.whiteListedPlayers;
/*      */   }
/*      */ 
/*      */   
/*      */   public Set getOps() {
/* 1100 */     return this.ops;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadWhiteList() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateTimeAndWeatherForPlayer(ServerPlayer par1EntityPlayerMP, WorldServer par2WorldServer) {
/* 1116 */     par1EntityPlayerMP.sendWorldAgesToClient();
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
/*      */   public void syncPlayerInventory(ServerPlayer par1EntityPlayerMP) {
/* 1133 */     par1EntityPlayerMP.sendContainerToPlayer(par1EntityPlayerMP.inventoryContainer);
/* 1134 */     par1EntityPlayerMP.setPlayerHealthUpdated();
/* 1135 */     par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet16BlockItemSwitch(par1EntityPlayerMP.inventory.currentItem));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentPlayerCount() {
/* 1143 */     return this.playerEntityList.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxPlayers() {
/* 1151 */     return this.maxPlayers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getAvailablePlayerDat() {
/* 1159 */     return this.mcServer.worldServers[0].getSaveHandler().getSaveHandler().getAvailablePlayerDat();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWhiteListEnabled() {
/* 1164 */     return this.whiteListEnforced;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getPlayerList(String par1Str) {
/* 1174 */     ArrayList<ServerPlayer> var2 = new ArrayList();
/* 1175 */     Iterator<ServerPlayer> var3 = this.playerEntityList.iterator();
/*      */     
/* 1177 */     while (var3.hasNext()) {
/*      */       
/* 1179 */       ServerPlayer var4 = var3.next();
/*      */       
/* 1181 */       if (var4.getPlayerIP().equals(par1Str))
/*      */       {
/* 1183 */         var2.add(var4);
/*      */       }
/*      */     } 
/*      */     
/* 1187 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getViewDistance() {
/* 1195 */     return this.viewDistance;
/*      */   }
/*      */ 
/*      */   
/*      */   public MinecraftServer getServerInstance() {
/* 1200 */     return this.mcServer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound getHostPlayerData() {
/* 1208 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGameType(EnumGameType par1EnumGameType) {
/* 1213 */     if (!Minecraft.inDevMode()) {
/* 1214 */       par1EnumGameType = EnumGameType.SURVIVAL;
/*      */     }
/* 1216 */     this.gameType = par1EnumGameType;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_72381_a(ServerPlayer par1EntityPlayerMP, ServerPlayer par2EntityPlayerMP, World par3World) {
/* 1221 */     if (par2EntityPlayerMP != null) {
/*      */       
/* 1223 */       par1EntityPlayerMP.theItemInWorldManager.setGameType(par2EntityPlayerMP.theItemInWorldManager.getGameType());
/*      */     }
/* 1225 */     else if (this.gameType != null) {
/*      */       
/* 1227 */       par1EntityPlayerMP.theItemInWorldManager.setGameType(this.gameType);
/*      */     } 
/*      */     
/* 1230 */     par1EntityPlayerMP.theItemInWorldManager.initializeGameType(par3World.getWorldInfo().getGameType());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCommandsAllowedForAll(boolean par1) {
/* 1238 */     if (!Minecraft.inDevMode()) {
/* 1239 */       par1 = false;
/*      */     }
/* 1241 */     this.commandsAllowedForAll = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeAllPlayers() {
/* 1249 */     while (!this.playerEntityList.isEmpty())
/*      */     {
/*      */       
/* 1252 */       ((ServerPlayer)this.playerEntityList.get(0)).playerNetServerHandler.kickPlayerFromServer(DedicatedServer.tournament_won ? "Tournament Finished" : "Server Closed");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_110459_a(ChatMessageComponent par1ChatMessageComponent, boolean par2) {
/* 1258 */     this.mcServer.sendChatToPlayer(par1ChatMessageComponent);
/* 1259 */     sendPacketToAllPlayers(new Packet3Chat(par1ChatMessageComponent, par2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendChatMsg(ChatMessageComponent par1ChatMessageComponent) {
/* 1267 */     func_110459_a(par1ChatMessageComponent, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isZevimrgvOnServer() {
/* 1273 */     Iterator<ServerPlayer> i = this.playerEntityList.iterator();
/*      */     
/* 1275 */     while (i.hasNext()) {
/*      */       
/* 1277 */       ServerPlayer player = i.next();
/*      */       
/* 1279 */       if (player.isZevimrgv()) {
/* 1280 */         return true;
/*      */       }
/*      */     } 
/* 1283 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerConfigurationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */