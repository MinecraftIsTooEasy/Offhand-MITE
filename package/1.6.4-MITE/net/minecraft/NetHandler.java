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
/*     */ public abstract class NetHandler
/*     */ {
/*     */   public abstract boolean isServerHandler();
/*     */   
/*     */   public void handleMapChunk(Packet51MapChunk par1Packet51MapChunk) {}
/*     */   
/*     */   public void unexpectedPacket(Packet par1Packet) {}
/*     */   
/*     */   public void handleErrorMessage(String par1Str, Object[] par2ArrayOfObj) {}
/*     */   
/*     */   public void handleKickDisconnect(Packet255KickDisconnect par1Packet255KickDisconnect) {
/*  25 */     unexpectedPacket(par1Packet255KickDisconnect);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleLogin(Packet1Login par1Packet1Login) {
/*  30 */     unexpectedPacket(par1Packet1Login);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleFlying(Packet10Flying par1Packet10Flying) {
/*  35 */     unexpectedPacket(par1Packet10Flying);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleMultiBlockChange(Packet52MultiBlockChange par1Packet52MultiBlockChange) {
/*  40 */     unexpectedPacket(par1Packet52MultiBlockChange);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleMultiBlockChange(Packet97MultiBlockChange packet) {
/*  45 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleBlockDig(Packet14BlockDig par1Packet14BlockDig) {
/*  50 */     unexpectedPacket(par1Packet14BlockDig);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleBlockChange(Packet53BlockChange par1Packet53BlockChange) {
/*  55 */     unexpectedPacket(par1Packet53BlockChange);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleNamedEntitySpawn(Packet20NamedEntitySpawn par1Packet20NamedEntitySpawn) {
/*  60 */     unexpectedPacket(par1Packet20NamedEntitySpawn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleEntity(Packet30Entity par1Packet30Entity) {
/*  65 */     unexpectedPacket(par1Packet30Entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleEntityLook(Packet32EntityLook packet) {
/*  70 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleEntityTeleport(Packet34EntityTeleport par1Packet34EntityTeleport) {
/*  75 */     unexpectedPacket(par1Packet34EntityTeleport);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handlePlace(Packet15Place par1Packet15Place) {
/*  80 */     unexpectedPacket(par1Packet15Place);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleBlockItemSwitch(Packet16BlockItemSwitch par1Packet16BlockItemSwitch) {
/*  85 */     unexpectedPacket(par1Packet16BlockItemSwitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleDestroyEntity(Packet29DestroyEntity par1Packet29DestroyEntity) {
/*  90 */     unexpectedPacket(par1Packet29DestroyEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleCollect(Packet22Collect par1Packet22Collect) {
/*  95 */     unexpectedPacket(par1Packet22Collect);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleChat(Packet3Chat par1Packet3Chat) {
/* 100 */     unexpectedPacket(par1Packet3Chat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleVehicleSpawn(Packet23VehicleSpawn par1Packet23VehicleSpawn) {
/* 105 */     unexpectedPacket(par1Packet23VehicleSpawn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleAnimation(Packet18Animation par1Packet18Animation) {
/* 110 */     unexpectedPacket(par1Packet18Animation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEntityAction(Packet19EntityAction par1Packet19EntityAction) {
/* 118 */     unexpectedPacket(par1Packet19EntityAction);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleClientProtocol(Packet2ClientProtocol par1Packet2ClientProtocol) {
/* 123 */     unexpectedPacket(par1Packet2ClientProtocol);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleServerAuthData(Packet253ServerAuthData par1Packet253ServerAuthData) {
/* 128 */     unexpectedPacket(par1Packet253ServerAuthData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleSharedKey(Packet252SharedKey par1Packet252SharedKey) {
/* 133 */     unexpectedPacket(par1Packet252SharedKey);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleMobSpawn(Packet24MobSpawn par1Packet24MobSpawn) {
/* 138 */     unexpectedPacket(par1Packet24MobSpawn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleUpdateTime(Packet4UpdateTime par1Packet4UpdateTime) {
/* 143 */     unexpectedPacket(par1Packet4UpdateTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleSpawnPosition(Packet6SpawnPosition par1Packet6SpawnPosition) {
/* 148 */     unexpectedPacket(par1Packet6SpawnPosition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEntityVelocity(Packet28EntityVelocity par1Packet28EntityVelocity) {
/* 156 */     unexpectedPacket(par1Packet28EntityVelocity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEntityMetadata(Packet40EntityMetadata par1Packet40EntityMetadata) {
/* 164 */     unexpectedPacket(par1Packet40EntityMetadata);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleAttachEntity(Packet39AttachEntity par1Packet39AttachEntity) {
/* 172 */     unexpectedPacket(par1Packet39AttachEntity);
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
/*     */   public void handleEntityStatus(Packet38EntityStatus par1Packet38EntityStatus) {
/* 185 */     unexpectedPacket(par1Packet38EntityStatus);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleUpdateHealth(Packet8UpdateHealth par1Packet8UpdateHealth) {
/* 193 */     unexpectedPacket(par1Packet8UpdateHealth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleRespawn(Packet9Respawn par1Packet9Respawn) {
/* 201 */     unexpectedPacket(par1Packet9Respawn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleExplosion(Packet60Explosion par1Packet60Explosion) {
/* 206 */     unexpectedPacket(par1Packet60Explosion);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleOpenWindow(Packet100OpenWindow par1Packet100OpenWindow) {
/* 211 */     unexpectedPacket(par1Packet100OpenWindow);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleCloseWindow(Packet101CloseWindow par1Packet101CloseWindow) {
/* 216 */     unexpectedPacket(par1Packet101CloseWindow);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleWindowClick(Packet102WindowClick par1Packet102WindowClick) {
/* 221 */     unexpectedPacket(par1Packet102WindowClick);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleSetSlot(Packet103SetSlot par1Packet103SetSlot) {
/* 226 */     unexpectedPacket(par1Packet103SetSlot);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleWindowItems(Packet104WindowItems par1Packet104WindowItems) {
/* 231 */     unexpectedPacket(par1Packet104WindowItems);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleUpdateSign(Packet130UpdateSign par1Packet130UpdateSign) {
/* 239 */     unexpectedPacket(par1Packet130UpdateSign);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleUpdateProgressbar(Packet105UpdateProgressbar par1Packet105UpdateProgressbar) {
/* 244 */     unexpectedPacket(par1Packet105UpdateProgressbar);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handlePlayerInventory(Packet5PlayerInventory par1Packet5PlayerInventory) {
/* 249 */     unexpectedPacket(par1Packet5PlayerInventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleTransaction(Packet106Transaction par1Packet106Transaction) {
/* 254 */     unexpectedPacket(par1Packet106Transaction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEntityPainting(Packet25EntityPainting par1Packet25EntityPainting) {
/* 262 */     unexpectedPacket(par1Packet25EntityPainting);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleBlockEvent(Packet54PlayNoteBlock par1Packet54PlayNoteBlock) {
/* 267 */     unexpectedPacket(par1Packet54PlayNoteBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleStatistic(Packet200Statistic par1Packet200Statistic) {
/* 275 */     unexpectedPacket(par1Packet200Statistic);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleSleep(Packet17Sleep par1Packet17Sleep) {
/* 280 */     unexpectedPacket(par1Packet17Sleep);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110774_a(Packet27PlayerInput par1Packet27PlayerInput) {
/* 285 */     unexpectedPacket(par1Packet27PlayerInput);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleGameEvent(Packet70GameEvent par1Packet70GameEvent) {
/* 290 */     unexpectedPacket(par1Packet70GameEvent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleWeather(Packet71Weather par1Packet71Weather) {
/* 298 */     unexpectedPacket(par1Packet71Weather);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMapData(Packet131MapData par1Packet131MapData) {
/* 306 */     unexpectedPacket(par1Packet131MapData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleDoorChange(Packet61DoorChange par1Packet61DoorChange) {
/* 311 */     unexpectedPacket(par1Packet61DoorChange);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleServerPing(Packet254ServerPing par1Packet254ServerPing) {
/* 319 */     unexpectedPacket(par1Packet254ServerPing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEntityEffect(Packet41EntityEffect par1Packet41EntityEffect) {
/* 327 */     unexpectedPacket(par1Packet41EntityEffect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleRemoveEntityEffect(Packet42RemoveEntityEffect par1Packet42RemoveEntityEffect) {
/* 335 */     unexpectedPacket(par1Packet42RemoveEntityEffect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handlePlayerInfo(Packet201PlayerInfo par1Packet201PlayerInfo) {
/* 343 */     unexpectedPacket(par1Packet201PlayerInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleKeepAlive(Packet0KeepAlive par1Packet0KeepAlive) {
/* 351 */     unexpectedPacket(par1Packet0KeepAlive);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleExperience(Packet43Experience par1Packet43Experience) {
/* 359 */     unexpectedPacket(par1Packet43Experience);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleCreativeSetSlot(Packet107CreativeSetSlot par1Packet107CreativeSetSlot) {
/* 367 */     unexpectedPacket(par1Packet107CreativeSetSlot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEntityExpOrb(Packet26EntityExpOrb par1Packet26EntityExpOrb) {
/* 375 */     unexpectedPacket(par1Packet26EntityExpOrb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleEnchantItem(Packet108EnchantItem par1Packet108EnchantItem) {}
/*     */   
/*     */   public void handleCustomPayload(Packet250CustomPayload par1Packet250CustomPayload) {}
/*     */   
/*     */   public void handleEntityHeadRotation(Packet35EntityHeadRotation par1Packet35EntityHeadRotation) {
/* 384 */     unexpectedPacket(par1Packet35EntityHeadRotation);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleTileEntityData(Packet132TileEntityData par1Packet132TileEntityData) {
/* 389 */     unexpectedPacket(par1Packet132TileEntityData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handlePlayerAbilities(Packet202PlayerAbilities par1Packet202PlayerAbilities) {
/* 397 */     unexpectedPacket(par1Packet202PlayerAbilities);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleAutoComplete(Packet203AutoComplete par1Packet203AutoComplete) {
/* 402 */     unexpectedPacket(par1Packet203AutoComplete);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleClientInfo(Packet204ClientInfo par1Packet204ClientInfo) {
/* 407 */     unexpectedPacket(par1Packet204ClientInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleLevelSound(Packet62LevelSound par1Packet62LevelSound) {
/* 412 */     unexpectedPacket(par1Packet62LevelSound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleLongDistanceSound(Packet80LongDistanceSound packet) {
/* 417 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleRightClick(Packet81RightClick packet) {
/* 427 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleAddHunger(Packet82AddHunger packet) {
/* 432 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleSimpleSignal(Packet85SimpleSignal packet) {
/* 437 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleSetDespawnCounters(Packet87SetDespawnCounters packet) {
/* 442 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleUpdateStrongboxOwner(Packet88UpdateStrongboxOwner packet) {
/* 447 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handlePlaySoundOnServerAtEntity(Packet89PlaySoundOnServerAtEntity packet) {
/* 452 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleBroadcastToAssociatedPlayers(Packet90BroadcastToAssociatedPlayers packet) {
/* 462 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handlePlayerStat(Packet91PlayerStat packet) {
/* 467 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleUpdateTimeSmall(Packet92UpdateTimeSmall packet) {
/* 472 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleWorldAchievement(Packet93WorldAchievement packet) {
/* 477 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleCreateFile(Packet94CreateFile packet) {
/* 482 */     unexpectedPacket(packet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleBlockDestroy(Packet55BlockDestroy par1Packet55BlockDestroy) {
/* 492 */     unexpectedPacket(par1Packet55BlockDestroy);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleClientCommand(Packet205ClientCommand par1Packet205ClientCommand) {}
/*     */   
/*     */   public void handleMapChunks(Packet56MapChunks par1Packet56MapChunks) {
/* 499 */     unexpectedPacket(par1Packet56MapChunks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProcessPacketsAsync() {
/* 509 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleSetObjective(Packet206SetObjective par1Packet206SetObjective) {
/* 517 */     unexpectedPacket(par1Packet206SetObjective);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleSetScore(Packet207SetScore par1Packet207SetScore) {
/* 525 */     unexpectedPacket(par1Packet207SetScore);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleSetDisplayObjective(Packet208SetDisplayObjective par1Packet208SetDisplayObjective) {
/* 533 */     unexpectedPacket(par1Packet208SetDisplayObjective);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleSetPlayerTeam(Packet209SetPlayerTeam par1Packet209SetPlayerTeam) {
/* 541 */     unexpectedPacket(par1Packet209SetPlayerTeam);
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
/*     */   public void func_110773_a(Packet44UpdateAttributes par1Packet44UpdateAttributes) {
/* 554 */     unexpectedPacket(par1Packet44UpdateAttributes);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_142031_a(Packet133TileEditorOpen par1Packet133TileEditorOpen) {}
/*     */   
/*     */   public boolean isConnectionClosed() {
/* 561 */     return false;
/*     */   }
/*     */   
/*     */   public abstract INetworkManager getNetManager();
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NetHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */