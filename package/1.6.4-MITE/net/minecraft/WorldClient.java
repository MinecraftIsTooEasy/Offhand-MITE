/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.main.Main;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WorldClient
/*     */   extends World
/*     */ {
/*     */   private NetClientHandler sendQueue;
/*     */   private ChunkProviderClient clientChunkProvider;
/*  22 */   private IntHashMap entityHashSet = new IntHashMap();
/*     */ 
/*     */   
/*  25 */   private Set entityList = new HashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   private Set entitySpawnQueue = new HashSet();
/*  32 */   private final Minecraft mc = Minecraft.getMinecraft();
/*  33 */   private final Set previousActiveChunkSet = new HashSet();
/*     */ 
/*     */   
/*     */   public boolean tick_has_passed;
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldClient(NetClientHandler par1NetClientHandler, WorldSettings par2WorldSettings, int par3, int par4, Profiler par5Profiler, ILogAgent par6ILogAgent, long world_creation_time, long total_world_time) {
/*  41 */     super(new SaveHandlerMP(), "MpServer", WorldProvider.getProviderForDimension(par3), par2WorldSettings, par5Profiler, par6ILogAgent, world_creation_time, total_world_time);
/*  42 */     this.sendQueue = par1NetClientHandler;
/*  43 */     this.difficultySetting = par4;
/*  44 */     setSpawnLocation(8, 64, 8);
/*  45 */     this.mapStorage = par1NetClientHandler.mapStorage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  55 */     if (Minecraft.java_version_is_outdated) {
/*     */       
/*  57 */       Minecraft.theMinecraft.thePlayer = null;
/*  58 */       Minecraft.theMinecraft = null;
/*     */     } 
/*     */     
/*  61 */     if (Main.is_MITE_DS && !this.mc.inGameHasFocus) {
/*     */       
/*  63 */       this.sendQueue.processReadPackets();
/*     */       
/*  65 */       int num_packets = this.sendQueue.getNetManager().clearReceivedPackets();
/*     */       
/*  67 */       if (num_packets > 0) {
/*  68 */         System.out.println(num_packets + " packets cleared from the dedicated server received queue");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*  73 */     super.tick();
/*  74 */     setTotalWorldTime(getTotalWorldTime() + 1L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     this.skylightSubtracted = calculateSkylightSubtracted(1.0F);
/*     */     
/*  84 */     this.theProfiler.startSection("reEntryProcessing");
/*     */     
/*  86 */     for (int var1 = 0; var1 < 10 && !this.entitySpawnQueue.isEmpty(); var1++) {
/*     */       
/*  88 */       Entity var2 = this.entitySpawnQueue.iterator().next();
/*  89 */       this.entitySpawnQueue.remove(var2);
/*     */       
/*  91 */       if (!this.loadedEntityList.contains(var2))
/*     */       {
/*  93 */         spawnEntityInWorld(var2);
/*     */       }
/*     */     } 
/*     */     
/*  97 */     this.theProfiler.endStartSection("connection");
/*  98 */     this.sendQueue.processReadPackets();
/*  99 */     this.theProfiler.endStartSection("chunkCache");
/* 100 */     this.clientChunkProvider.unloadQueuedChunks();
/* 101 */     this.theProfiler.endStartSection("tiles");
/* 102 */     tickBlocksAndAmbiance();
/* 103 */     this.theProfiler.endSection();
/*     */     
/* 105 */     this.tick_has_passed = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidateBlockReceiveRegion(int par1, int par2, int par3, int par4, int par5, int par6) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IChunkProvider createChunkProvider() {
/* 119 */     this.clientChunkProvider = new ChunkProviderClient(this);
/* 120 */     return this.clientChunkProvider;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void tickBlocksAndAmbiance() {
/* 129 */     super.tickBlocksAndAmbiance();
/* 130 */     this.previousActiveChunkSet.retainAll(this.activeChunkSet);
/*     */     
/* 132 */     if (this.previousActiveChunkSet.size() == this.activeChunkSet.size())
/*     */     {
/* 134 */       this.previousActiveChunkSet.clear();
/*     */     }
/*     */     
/* 137 */     int var1 = 0;
/* 138 */     Iterator<ChunkCoordIntPair> var2 = this.activeChunkSet.iterator();
/*     */ 
/*     */ 
/*     */     
/* 142 */     while (var2.hasNext()) {
/*     */       
/* 144 */       ChunkCoordIntPair var3 = var2.next();
/*     */       
/* 146 */       Chunk chunk = getChunkFromChunkCoords(var3.chunkXPos, var3.chunkZPos);
/*     */       
/* 148 */       chunk.performPendingSkylightUpdatesIfPossible();
/* 149 */       chunk.performPendingBlocklightUpdatesIfPossible();
/*     */     } 
/*     */     
/* 152 */     var2 = this.activeChunkSet.iterator();
/*     */ 
/*     */ 
/*     */     
/* 156 */     while (var2.hasNext()) {
/*     */       
/* 158 */       ChunkCoordIntPair var3 = var2.next();
/*     */       
/* 160 */       if (!this.previousActiveChunkSet.contains(var3)) {
/*     */         
/* 162 */         int var4 = var3.chunkXPos * 16;
/* 163 */         int var5 = var3.chunkZPos * 16;
/* 164 */         this.theProfiler.startSection("getChunk");
/* 165 */         Chunk var6 = getChunkFromChunkCoords(var3.chunkXPos, var3.chunkZPos);
/* 166 */         moodSoundAndLightCheck(var4, var5, var6);
/* 167 */         this.theProfiler.endSection();
/* 168 */         this.previousActiveChunkSet.add(var3);
/* 169 */         var1++;
/*     */         
/* 171 */         if (var1 >= 10) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPreChunk(int par1, int par2, boolean par3) {
/* 181 */     if (par3) {
/*     */       
/* 183 */       this.clientChunkProvider.loadChunk(par1, par2);
/*     */     }
/*     */     else {
/*     */       
/* 187 */       this.clientChunkProvider.unloadChunk(par1, par2);
/*     */     } 
/*     */     
/* 190 */     if (!par3)
/*     */     {
/* 192 */       markBlockRangeForRenderUpdate(par1 * 16, 0, par2 * 16, par1 * 16 + 15, 256, par2 * 16 + 15);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean spawnEntityInWorld(Entity par1Entity) {
/* 201 */     boolean var2 = super.spawnEntityInWorld(par1Entity);
/* 202 */     this.entityList.add(par1Entity);
/*     */     
/* 204 */     if (!var2)
/*     */     {
/* 206 */       this.entitySpawnQueue.add(par1Entity);
/*     */     }
/*     */     
/* 209 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeEntity(Entity par1Entity) {
/* 217 */     super.removeEntity(par1Entity);
/* 218 */     this.entityList.remove(par1Entity);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onEntityAdded(Entity par1Entity) {
/* 223 */     super.onEntityAdded(par1Entity);
/*     */     
/* 225 */     if (this.entitySpawnQueue.contains(par1Entity))
/*     */     {
/* 227 */       this.entitySpawnQueue.remove(par1Entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onEntityRemoved(Entity par1Entity) {
/* 233 */     super.onEntityRemoved(par1Entity);
/*     */     
/* 235 */     if (this.entityList.contains(par1Entity))
/*     */     {
/* 237 */       if (par1Entity.isEntityAlive()) {
/*     */         
/* 239 */         this.entitySpawnQueue.add(par1Entity);
/*     */       }
/*     */       else {
/*     */         
/* 243 */         this.entityList.remove(par1Entity);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntityToWorld(int par1, Entity par2Entity) {
/* 253 */     Entity var3 = getEntityByID(par1);
/*     */     
/* 255 */     if (var3 != null)
/*     */     {
/* 257 */       removeEntity(var3);
/*     */     }
/*     */     
/* 260 */     this.entityList.add(par2Entity);
/* 261 */     par2Entity.entityId = par1;
/*     */     
/* 263 */     if (!spawnEntityInWorld(par2Entity))
/*     */     {
/* 265 */       this.entitySpawnQueue.add(par2Entity);
/*     */     }
/*     */     
/* 268 */     this.entityHashSet.addKey(par1, par2Entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getEntityByID(int par1) {
/* 276 */     return (par1 == this.mc.thePlayer.entityId) ? this.mc.thePlayer : (Entity)this.entityHashSet.lookup(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity removeEntityFromWorld(int par1) {
/* 281 */     Entity var2 = (Entity)this.entityHashSet.removeObject(par1);
/*     */     
/* 283 */     if (var2 != null) {
/*     */       
/* 285 */       this.entityList.remove(var2);
/* 286 */       removeEntity(var2);
/*     */     } 
/*     */     
/* 289 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setBlockAndMetadataAndInvalidate(int par1, int par2, int par3, int par4, int par5) {
/* 294 */     invalidateBlockReceiveRegion(par1, par2, par3, par1, par2, par3);
/* 295 */     return setBlock(par1, par2, par3, par4, par5, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendQuittingDisconnectingPacket() {
/* 304 */     this.sendQueue.quitWithPacket(new Packet255KickDisconnect("Quitting", this.mc.isIntegratedServerRunning()));
/*     */   }
/*     */ 
/*     */   
/*     */   public IUpdatePlayerListBox getMinecartSoundUpdater(EntityMinecart par1EntityMinecart) {
/* 309 */     return new SoundUpdaterMinecart(this.mc.sndManager, par1EntityMinecart, this.mc.thePlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRainStrengthForRenderViewEntity() {
/* 314 */     this.mc.prev_raining_strength_for_render_view_entity = this.mc.raining_strength_for_render_view_entity;
/*     */     
/* 316 */     if (this.rainingStrength == 0.0D) {
/*     */       
/* 318 */       this.mc.raining_strength_for_render_view_entity = 0.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 322 */     EntityLivingBase viewer = this.mc.renderViewEntity;
/* 323 */     int x = viewer.getBlockPosX();
/* 324 */     int z = viewer.getBlockPosZ();
/*     */     
/* 326 */     BiomeGenBase biome = getBiomeGenForCoords(x, z);
/*     */     
/* 328 */     if (biome.rainfall > 0.0F || isBloodMoon24HourPeriod()) {
/*     */       
/* 330 */       this.mc.raining_strength_for_render_view_entity = this.rainingStrength;
/*     */       
/*     */       return;
/*     */     } 
/* 334 */     double distance_to_nearest_raining_coord_sq = Double.MAX_VALUE;
/*     */     
/* 336 */     int falloff_distance = 32;
/*     */     
/* 338 */     for (int dx = -falloff_distance; dx <= falloff_distance; dx++) {
/*     */       
/* 340 */       for (int dz = -falloff_distance; dz <= falloff_distance; dz++) {
/*     */         
/* 342 */         if (chunkExistsAndIsNotEmptyFromBlockCoords(x + dx, z + dz)) {
/*     */ 
/*     */           
/* 345 */           biome = getBiomeGenForCoords(x + dx, z + dz);
/*     */           
/* 347 */           if (biome.rainfall > 0.0F) {
/*     */             
/* 349 */             double dxd = ((x + dx) + 0.5F) - viewer.posX;
/* 350 */             double dzd = ((z + dz) + 0.5F) - viewer.posZ;
/*     */             
/* 352 */             double distance_sq = dxd * dxd + dzd * dzd;
/*     */             
/* 354 */             if (distance_sq < distance_to_nearest_raining_coord_sq)
/* 355 */               distance_to_nearest_raining_coord_sq = distance_sq; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 360 */     distance_to_nearest_raining_coord_sq -= 0.5D;
/*     */     
/* 362 */     if (distance_to_nearest_raining_coord_sq <= 0.0D) {
/*     */       
/* 364 */       this.mc.raining_strength_for_render_view_entity = this.rainingStrength;
/*     */       
/*     */       return;
/*     */     } 
/* 368 */     if (distance_to_nearest_raining_coord_sq >= (falloff_distance * falloff_distance)) {
/* 369 */       this.mc.raining_strength_for_render_view_entity = 0.0F;
/*     */     } else {
/* 371 */       this.mc.raining_strength_for_render_view_entity = Math.max(this.rainingStrength * (1.0F - MathHelper.sqrt_double(distance_to_nearest_raining_coord_sq) / falloff_distance), 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getRainStrength(float par1) {
/* 376 */     if (this.mc == null) {
/* 377 */       return super.getRainStrength(par1);
/*     */     }
/*     */     
/* 380 */     return this.mc.prev_raining_strength_for_render_view_entity + (this.mc.raining_strength_for_render_view_entity - this.mc.prev_raining_strength_for_render_view_entity) * par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateWeather() {
/* 388 */     if (!this.provider.hasNoSky) {
/*     */       
/* 390 */       this.prevRainingStrength = this.rainingStrength;
/*     */ 
/*     */       
/* 393 */       if (isPrecipitating(false)) {
/*     */         
/* 395 */         this.rainingStrength = (float)(this.rainingStrength + 0.01D);
/*     */       }
/*     */       else {
/*     */         
/* 399 */         this.rainingStrength = (float)(this.rainingStrength - 0.01D);
/*     */       } 
/*     */       
/* 402 */       if (this.rainingStrength < 0.0F)
/*     */       {
/* 404 */         this.rainingStrength = 0.0F;
/*     */       }
/*     */       
/* 407 */       if (this.rainingStrength > 1.0F)
/*     */       {
/* 409 */         this.rainingStrength = 1.0F;
/*     */       }
/*     */       
/* 412 */       if (this == this.mc.renderViewEntity.worldObj) {
/* 413 */         setRainStrengthForRenderViewEntity();
/*     */       }
/* 415 */       this.prevThunderingStrength = this.thunderingStrength;
/*     */ 
/*     */       
/* 418 */       if (isThundering(false)) {
/*     */         
/* 420 */         this.thunderingStrength = (float)(this.thunderingStrength + 0.01D);
/*     */       }
/*     */       else {
/*     */         
/* 424 */         this.thunderingStrength = (float)(this.thunderingStrength - 0.01D);
/*     */       } 
/*     */       
/* 427 */       if (this.thunderingStrength < 0.0F)
/*     */       {
/* 429 */         this.thunderingStrength = 0.0F;
/*     */       }
/*     */       
/* 432 */       if (this.thunderingStrength > 1.0F)
/*     */       {
/* 434 */         this.thunderingStrength = 1.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doVoidFogParticles(int par1, int par2, int par3) {
/* 442 */     byte var4 = 16;
/* 443 */     Random var5 = new Random();
/*     */     
/* 445 */     int random_number_index = this.rand.nextInt();
/* 446 */     boolean world_has_void_particles = this.provider.getWorldHasVoidParticles();
/* 447 */     int particles_spawned = 0;
/*     */     
/* 449 */     for (int var6 = 0; var6 < 1000; var6++) {
/*     */       
/* 451 */       int var7 = par1 + this.rand.nextInt(var4) - this.rand.nextInt(var4);
/* 452 */       int var8 = par2 + this.rand.nextInt(var4) - this.rand.nextInt(var4);
/* 453 */       int var9 = par3 + this.rand.nextInt(var4) - this.rand.nextInt(var4);
/*     */       
/* 455 */       if (isWithinBlockDomain(var7, var9)) {
/*     */ 
/*     */         
/* 458 */         int var10 = getBlockId(var7, var8, var9);
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
/* 473 */         if (var10 > 0) {
/* 474 */           Block.blocksList[var10].randomDisplayTick(this, var7, var8, var9, var5);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllEntities() {
/* 483 */     this.loadedEntityList.removeAll(this.unloadedEntityList);
/*     */ 
/*     */     
/*     */     int var1;
/*     */ 
/*     */     
/* 489 */     for (var1 = 0; var1 < this.unloadedEntityList.size(); var1++) {
/*     */       
/* 491 */       Entity var2 = this.unloadedEntityList.get(var1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 500 */       if (var2.isAddedToAChunk()) {
/*     */         
/* 502 */         Chunk chunk = var2.getChunkAddedTo();
/*     */         
/* 504 */         if (chunkExists(chunk.xPosition, chunk.zPosition)) {
/* 505 */           var2.removeFromChunk();
/*     */         }
/*     */       } 
/*     */     } 
/* 509 */     for (var1 = 0; var1 < this.unloadedEntityList.size(); var1++)
/*     */     {
/* 511 */       onEntityRemoved(this.unloadedEntityList.get(var1));
/*     */     }
/*     */     
/* 514 */     this.unloadedEntityList.clear();
/*     */     
/* 516 */     for (var1 = 0; var1 < this.loadedEntityList.size(); var1++) {
/*     */       
/* 518 */       Entity var2 = this.loadedEntityList.get(var1);
/*     */       
/* 520 */       if (var2.ridingEntity != null) {
/*     */         
/* 522 */         if (!var2.ridingEntity.isDead && var2.ridingEntity.riddenByEntity == var2) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 527 */         var2.ridingEntity.riddenByEntity = null;
/* 528 */         var2.ridingEntity = null;
/*     */       } 
/*     */       
/* 531 */       if (var2.isDead) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 541 */         if (var2.isAddedToAChunk()) {
/*     */           
/* 543 */           Chunk chunk = var2.getChunkAddedTo();
/*     */           
/* 545 */           if (chunkExists(chunk.xPosition, chunk.zPosition)) {
/* 546 */             var2.removeFromChunk();
/*     */           }
/*     */         } 
/* 549 */         this.loadedEntityList.remove(var1--);
/* 550 */         onEntityRemoved(var2);
/*     */       } 
/*     */       continue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReportCategory addWorldInfoToCrashReport(CrashReport par1CrashReport) {
/* 560 */     CrashReportCategory var2 = super.addWorldInfoToCrashReport(par1CrashReport);
/* 561 */     var2.addCrashSectionCallable("Forced entities", new CallableMPL1(this));
/* 562 */     var2.addCrashSectionCallable("Retry entities", new CallableMPL2(this));
/* 563 */     var2.addCrashSectionCallable("Server brand", new WorldClientINNER3(this));
/* 564 */     var2.addCrashSectionCallable("Server type", new WorldClientINNER4(this));
/* 565 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSound(double par1, double par3, double par5, String par7Str, float par8, float par9, boolean par10) {
/* 573 */     if (this.mc.raining_strength_for_render_view_entity < 1.0F && "ambient.weather.thunder".equals(par7Str)) {
/*     */       
/* 575 */       par1 = this.mc.thePlayer.posX;
/* 576 */       par3 = this.mc.thePlayer.posY;
/* 577 */       par5 = this.mc.thePlayer.posZ;
/*     */       
/* 579 */       par8 = (float)Math.pow(this.mc.raining_strength_for_render_view_entity, 4.0D);
/*     */     } 
/*     */     
/* 582 */     float var11 = 16.0F;
/*     */     
/* 584 */     if (par8 > 1.0F)
/*     */     {
/* 586 */       var11 *= par8;
/*     */     }
/*     */     
/* 589 */     double var12 = this.mc.renderViewEntity.getDistanceSq(par1, par3, par5);
/*     */     
/* 591 */     if (var12 < (var11 * var11))
/*     */     {
/* 593 */       if (par10 && var12 > 100.0D) {
/*     */         
/* 595 */         double var14 = Math.sqrt(var12) / 40.0D;
/* 596 */         this.mc.sndManager.func_92070_a(par7Str, (float)par1, (float)par3, (float)par5, par8, par9, (int)Math.round(var14 * 20.0D));
/*     */       }
/*     */       else {
/*     */         
/* 600 */         this.mc.sndManager.playSound(par7Str, (float)par1, (float)par3, (float)par5, par8, par9);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playLongDistanceSound(double par1, double par3, double par5, String par7Str, float volume, float pitch, boolean par10) {
/* 610 */     double var12 = this.mc.renderViewEntity.getDistanceSq(par1, par3, par5);
/*     */     
/* 612 */     if (par10 && var12 > 100.0D) {
/*     */       
/* 614 */       double var14 = Math.sqrt(var12) / 40.0D;
/* 615 */       this.mc.sndManager.func_92070_a(par7Str, (float)par1, (float)par3, (float)par5, volume, pitch, (int)Math.round(var14 * 20.0D));
/*     */     }
/*     */     else {
/*     */       
/* 619 */       this.mc.sndManager.playLongDistanceSound(par7Str, (float)par1, (float)par3, (float)par5, volume, pitch);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_92088_a(double par1, double par3, double par5, double par7, double par9, double par11, NBTTagCompound par13NBTTagCompound) {
/* 625 */     this.mc.effectRenderer.addEffect(new EntityFireworkStarterFX(this, par1, par3, par5, par7, par9, par11, this.mc.effectRenderer, par13NBTTagCompound));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96443_a(Scoreboard par1Scoreboard) {
/* 630 */     this.worldScoreboard = par1Scoreboard;
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
/*     */   static Set getEntityList(WorldClient par0WorldClient) {
/* 654 */     return par0WorldClient.entityList;
/*     */   }
/*     */ 
/*     */   
/*     */   static Set getEntitySpawnQueue(WorldClient par0WorldClient) {
/* 659 */     return par0WorldClient.entitySpawnQueue;
/*     */   }
/*     */ 
/*     */   
/*     */   static Minecraft func_142030_c(WorldClient par0WorldClient) {
/* 664 */     return par0WorldClient.mc;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */