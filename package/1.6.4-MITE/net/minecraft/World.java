/*       */ package net.minecraft;
/*       */ 
/*       */ import java.util.ArrayList;
/*       */ import java.util.Calendar;
/*       */ import java.util.Collection;
/*       */ import java.util.Collections;
/*       */ import java.util.HashMap;
/*       */ import java.util.HashSet;
/*       */ import java.util.Iterator;
/*       */ import java.util.List;
/*       */ import java.util.Random;
/*       */ import java.util.Set;
/*       */ import net.minecraft.server.MinecraftServer;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ public abstract class World
/*       */   implements IBlockAccess
/*       */ {
/*       */   public boolean scheduledUpdatesAreImmediate;
/*    24 */   public List loadedEntityList = new ArrayList();
/*       */ 
/*       */ 
/*       */   
/*    28 */   protected List unloadedEntityList = new ArrayList();
/*       */ 
/*       */   
/*    31 */   public List loadedTileEntityList = new ArrayList();
/*    32 */   private List addedTileEntityList = new ArrayList();
/*       */ 
/*       */   
/*    35 */   private List entityRemoval = new ArrayList();
/*       */ 
/*       */   
/*    38 */   public List playerEntities = new ArrayList();
/*       */ 
/*       */   
/*    41 */   public List weatherEffects = new ArrayList();
/*    42 */   private long cloudColour = 16777215L;
/*       */ 
/*       */ 
/*       */   
/*       */   public int skylightSubtracted;
/*       */ 
/*       */ 
/*       */   
/*       */   public int skylight_subtracted_ignoring_rain_and_thunder;
/*       */ 
/*       */   
/*    53 */   protected int updateLCG = (new Random()).nextInt();
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*    58 */   protected final int DIST_HASH_MAGIC = 1013904223;
/*       */ 
/*       */   
/*       */   protected float prevRainingStrength;
/*       */ 
/*       */   
/*       */   protected float rainingStrength;
/*       */   
/*       */   protected float prevThunderingStrength;
/*       */   
/*       */   protected float thunderingStrength;
/*       */   
/*       */   public int lastLightningBolt;
/*       */   
/*       */   public int difficultySetting;
/*       */   
/*    74 */   public Random rand = new Random();
/*       */   
/*       */   public final WorldProvider provider;
/*       */   
/*    78 */   protected List worldAccesses = new ArrayList();
/*       */ 
/*       */   
/*       */   protected IChunkProvider chunkProvider;
/*       */   
/*       */   protected final ISaveHandler saveHandler;
/*       */   
/*       */   protected WorldInfo worldInfo;
/*       */   
/*       */   public boolean findingSpawnPoint;
/*       */   
/*       */   public MapStorage mapStorage;
/*       */   
/*       */   public final VillageCollection villageCollectionObj;
/*       */   
/*    93 */   protected final VillageSiege villageSiegeObj = new VillageSiege(this);
/*       */   
/*       */   public final Profiler theProfiler;
/*       */   
/*    97 */   private final Vec3Pool vecPool = new Vec3Pool(300, 2000);
/*    98 */   private final Calendar theCalendar = Calendar.getInstance();
/*    99 */   protected Scoreboard worldScoreboard = new Scoreboard();
/*       */   
/*       */   private final ILogAgent worldLogAgent;
/*       */   
/*   103 */   private ArrayList collidingBoundingBoxes = new ArrayList();
/*       */ 
/*       */   
/*       */   private boolean scanningTileEntities;
/*       */ 
/*       */   
/*       */   protected boolean spawnHostileMobs = true;
/*       */   
/*       */   protected boolean spawnPeacefulMobs = true;
/*       */   
/*   113 */   protected Set activeChunkSet = new HashSet();
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private int ambientTickCountdown;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   int[] lightUpdateBlockList;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isRemote;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean decorating;
/*       */ 
/*       */ 
/*       */   
/*   137 */   public List pending_entity_spawns = new ArrayList();
/*       */   
/*       */   public boolean ignore_rain_and_thunder_for_next_BLV;
/*       */   
/*       */   private int times_checkLightingOfRandomBlockInView_called;
/*       */   
/*       */   public long total_time;
/*       */   
/*       */   private final boolean is_overworld;
/*       */   
/*       */   private final boolean is_underworld;
/*       */   
/*       */   private final boolean is_nether;
/*       */   
/*       */   private final boolean is_the_end;
/*       */   
/*       */   private final boolean has_skylight;
/*       */   
/*       */   private final int block_domain_radius;
/*       */   
/*       */   public final int min_chunk_xz;
/*       */   
/*       */   public final int max_chunk_xz;
/*       */   
/*       */   public final int min_block_xz;
/*       */   
/*       */   public final int max_block_xz;
/*       */   public final double min_entity_pos_xz;
/*       */   public final double max_entity_pos_xz;
/*       */   public final int runegate_mithril_domain_radius;
/*       */   public final int runegate_adamantium_domain_radius;
/*   168 */   public float biome_temperature_transition_for_sky_color = Float.NaN;
/*       */   
/*       */   private List weather_events_for_today;
/*       */   
/*       */   private int weather_events_for_day;
/*       */   
/*   174 */   private long tick_flags_last_updated = -1L;
/*       */ 
/*       */   
/*       */   private WeatherEvent current_weather_event;
/*       */ 
/*       */   
/*       */   private boolean is_precipitating;
/*       */ 
/*       */   
/*       */   private boolean is_storming;
/*       */ 
/*       */   
/*       */   private boolean is_harvest_moon_24_hour_period;
/*       */   
/*       */   private boolean is_harvest_moon_day;
/*       */   
/*       */   private boolean is_harvest_moon_night;
/*       */   
/*       */   private boolean is_blood_moon_24_hour_period;
/*       */   
/*       */   private boolean is_blood_moon_day;
/*       */   
/*       */   private boolean is_blood_moon_night;
/*       */   
/*       */   private boolean is_blue_moon_24_hour_period;
/*       */   
/*       */   private boolean is_blue_moon_day;
/*       */   
/*       */   private boolean is_blue_moon_night;
/*       */   
/*       */   private boolean is_moon_dog_24_hour_period;
/*       */   
/*       */   private boolean is_moon_dog_day;
/*       */   
/*       */   private boolean is_moon_dog_night;
/*       */   
/*       */   public static final int DIMENSION_ID_UNDERWORLD = -2;
/*       */   
/*       */   public static final int DIMENSION_ID_NETHER = -1;
/*       */   
/*       */   public static final int DIMENSION_ID_OVERWORLD = 0;
/*       */   
/*       */   public static final int DIMENSION_ID_THE_END = 1;
/*       */   
/*       */   private final ChunkPostField mycelium_posts;
/*       */   
/*       */   public static final int Y_OFFSET_FOR_UNDERWORLD = 120;
/*       */   
/*       */   public final int underworld_y_offset;
/*       */   
/*       */   private final Block bottom_block;
/*       */   
/*       */   private final int bottom_block_metadata;
/*       */   
/*       */   public HashMap pending_sand_falls;
/*       */ 
/*       */   
/*       */   public BiomeGenBase getBiomeGenForCoords(int par1, int par2) {
/*   232 */     if (blockExists(par1, 0, par2)) {
/*       */       
/*   234 */       Chunk var3 = getChunkFromBlockCoords(par1, par2);
/*       */       
/*   236 */       if (var3 != null)
/*       */       {
/*   238 */         return var3.getBiomeGenForWorldCoords(par1 & 0xF, par2 & 0xF, this.provider.worldChunkMgr);
/*       */       }
/*       */     } 
/*       */     
/*   242 */     return this.provider.worldChunkMgr.getBiomeGenAt(par1, par2);
/*       */   }
/*       */ 
/*       */   
/*       */   public WorldChunkManager getWorldChunkManager() {
/*   247 */     return this.provider.worldChunkMgr;
/*       */   }
/*       */ 
/*       */   
/*       */   private int getNextAmbientTickCountdown(boolean is_first_count_down) {
/*   252 */     if (is_first_count_down) {
/*   253 */       return this.rand.nextInt(12000);
/*       */     }
/*   255 */     return this.rand.nextInt(12000) + 6000;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public World(ISaveHandler par1ISaveHandler, String par2Str, WorldProvider par3WorldProvider, WorldSettings par4WorldSettings, Profiler par5Profiler, ILogAgent par6ILogAgent, long world_creation_time, long total_world_time) {
/*   262 */     if (!(this instanceof WorldClient)) {
/*   263 */       Minecraft.setErrorMessage("World: This constructor must only be invoked by WorldClient!");
/*       */     }
/*   265 */     this.isRemote = this instanceof WorldClient;
/*       */ 
/*       */     
/*   268 */     this.ambientTickCountdown = getNextAmbientTickCountdown(true);
/*       */     
/*   270 */     this.lightUpdateBlockList = new int[42875];
/*   271 */     this.saveHandler = par1ISaveHandler;
/*   272 */     this.theProfiler = par5Profiler;
/*   273 */     this.worldInfo = new WorldInfo(par4WorldSettings, par2Str);
/*       */     
/*   275 */     this.worldInfo.setDimensionId(par3WorldProvider.dimensionId);
/*       */     
/*   277 */     this.provider = par3WorldProvider;
/*       */     
/*   279 */     this.worldInfo.setWorldCreationTime(world_creation_time);
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   284 */     this.is_underworld = this.provider.isUnderworld();
/*   285 */     this.is_nether = this.provider.isTheNether();
/*   286 */     this.is_overworld = this.provider.isSurfaceWorld();
/*   287 */     this.is_the_end = this.provider.isTheEnd();
/*       */     
/*   289 */     this.bottom_block = isOverworld() ? Block.bedrock : ((isUnderworld() || isTheNether()) ? Block.mantleOrCore : null);
/*   290 */     this.bottom_block_metadata = isUnderworld() ? 0 : (isTheNether() ? 1 : -1);
/*       */     
/*   292 */     this.has_skylight = (!this.provider.hasNoSky && this.is_overworld);
/*       */     
/*   294 */     this.block_domain_radius = this.provider.getBlockDomainRadius();
/*       */     
/*   296 */     this.min_chunk_xz = -this.block_domain_radius / 16;
/*   297 */     this.max_chunk_xz = this.block_domain_radius / 16 - 1;
/*       */     
/*   299 */     this.min_block_xz = -this.block_domain_radius;
/*   300 */     this.max_block_xz = this.block_domain_radius - 1;
/*       */     
/*   302 */     this.min_entity_pos_xz = this.min_block_xz;
/*   303 */     this.max_entity_pos_xz = this.max_block_xz + 0.9999D;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   308 */     this.runegate_adamantium_domain_radius = Math.min(this.block_domain_radius / 2, 40000);
/*   309 */     this.runegate_mithril_domain_radius = this.runegate_adamantium_domain_radius / 8;
/*       */     
/*   311 */     validateDomainValues();
/*       */ 
/*       */ 
/*       */     
/*   315 */     this.worldInfo.setTotalWorldTime(total_world_time, this);
/*       */     
/*   317 */     this.mapStorage = new MapStorage(par1ISaveHandler);
/*   318 */     this.worldLogAgent = par6ILogAgent;
/*   319 */     VillageCollection var7 = (VillageCollection)this.mapStorage.loadData(VillageCollection.class, "villages");
/*       */     
/*   321 */     if (var7 == null) {
/*       */       
/*   323 */       this.villageCollectionObj = new VillageCollection(this);
/*   324 */       this.mapStorage.setData("villages", this.villageCollectionObj);
/*       */     }
/*       */     else {
/*       */       
/*   328 */       this.villageCollectionObj = var7;
/*   329 */       this.villageCollectionObj.func_82566_a(this);
/*       */     } 
/*       */     
/*   332 */     par3WorldProvider.registerWorld(this);
/*   333 */     this.chunkProvider = createChunkProvider();
/*   334 */     calculateInitialSkylight();
/*   335 */     _calculateInitialWeather();
/*       */ 
/*       */     
/*   338 */     RNG.init(this);
/*       */     
/*   340 */     this.mycelium_posts = createMyceliumPostField();
/*       */     
/*   342 */     this.underworld_y_offset = this.is_underworld ? 120 : 0;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public World(ISaveHandler par1ISaveHandler, String par2Str, WorldSettings par3WorldSettings, WorldProvider par4WorldProvider, Profiler par5Profiler, ILogAgent par6ILogAgent) {
/*   348 */     if (!(this instanceof WorldServer)) {
/*   349 */       Minecraft.setErrorMessage("World: This constructor must only be invoked by WorldServer!");
/*       */     }
/*   351 */     getAsWorldServer().instantiateScheduledBlockChangesList();
/*       */     
/*   353 */     this.isRemote = this instanceof WorldClient;
/*       */ 
/*       */     
/*   356 */     this.ambientTickCountdown = getNextAmbientTickCountdown(true);
/*       */     
/*   358 */     this.lightUpdateBlockList = new int[42875];
/*   359 */     this.saveHandler = par1ISaveHandler;
/*   360 */     this.theProfiler = par5Profiler;
/*   361 */     this.mapStorage = new MapStorage(par1ISaveHandler);
/*   362 */     this.worldLogAgent = par6ILogAgent;
/*   363 */     this.worldInfo = par1ISaveHandler.loadWorldInfo();
/*       */     
/*   365 */     if (par4WorldProvider != null) {
/*       */       
/*   367 */       this.provider = par4WorldProvider;
/*       */     }
/*   369 */     else if (this.worldInfo != null && this.worldInfo.getVanillaDimension() != 0) {
/*       */       
/*   371 */       this.provider = WorldProvider.getProviderForDimension(this.worldInfo.getVanillaDimension());
/*       */     }
/*       */     else {
/*       */       
/*   375 */       this.provider = WorldProvider.getProviderForDimension(0);
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*   380 */     this.is_underworld = this.provider.isUnderworld();
/*   381 */     this.is_nether = this.provider.isTheNether();
/*   382 */     this.is_overworld = this.provider.isSurfaceWorld();
/*   383 */     this.is_the_end = this.provider.isTheEnd();
/*       */     
/*   385 */     this.bottom_block = isOverworld() ? Block.bedrock : ((isUnderworld() || isTheNether()) ? Block.mantleOrCore : null);
/*   386 */     this.bottom_block_metadata = isUnderworld() ? 0 : (isTheNether() ? 1 : -1);
/*       */     
/*   388 */     this.has_skylight = (!this.provider.hasNoSky && this.is_overworld);
/*       */     
/*   390 */     this.block_domain_radius = this.provider.getBlockDomainRadius();
/*       */     
/*   392 */     this.min_chunk_xz = -this.block_domain_radius / 16;
/*   393 */     this.max_chunk_xz = this.block_domain_radius / 16 - 1;
/*       */     
/*   395 */     this.min_block_xz = -this.block_domain_radius;
/*   396 */     this.max_block_xz = this.block_domain_radius - 1;
/*       */     
/*   398 */     this.min_entity_pos_xz = this.min_block_xz;
/*   399 */     this.max_entity_pos_xz = this.max_block_xz + 0.9999D;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   404 */     this.runegate_adamantium_domain_radius = Math.min(this.block_domain_radius / 2, 40000);
/*   405 */     this.runegate_mithril_domain_radius = this.runegate_adamantium_domain_radius / 8;
/*       */     
/*   407 */     validateDomainValues();
/*       */ 
/*       */ 
/*       */     
/*   411 */     if (this.worldInfo == null) {
/*       */       
/*   413 */       this.worldInfo = new WorldInfo(par3WorldSettings, par2Str);
/*       */     }
/*       */     else {
/*       */       
/*   417 */       this.worldInfo.setWorldName(par2Str);
/*       */     } 
/*       */     
/*   420 */     this.provider.registerWorld(this);
/*       */     
/*   422 */     updateTickFlags();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   443 */     this.chunkProvider = createChunkProvider();
/*       */     
/*   445 */     if (!this.worldInfo.isInitialized()) {
/*       */ 
/*       */       
/*       */       try {
/*   449 */         initialize(par3WorldSettings);
/*       */       }
/*   451 */       catch (Throwable var11) {
/*       */         
/*   453 */         CrashReport var8 = CrashReport.makeCrashReport(var11, "Exception initializing level");
/*       */ 
/*       */         
/*       */         try {
/*   457 */           addWorldInfoToCrashReport(var8);
/*       */         }
/*   459 */         catch (Throwable var10) {}
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*   464 */         throw new ReportedException(var8);
/*       */       } 
/*       */       
/*   467 */       this.worldInfo.setServerInitialized(true);
/*       */     } 
/*       */     
/*   470 */     VillageCollection var7 = (VillageCollection)this.mapStorage.loadData(VillageCollection.class, "villages");
/*       */     
/*   472 */     if (var7 == null) {
/*       */       
/*   474 */       this.villageCollectionObj = new VillageCollection(this);
/*   475 */       this.mapStorage.setData("villages", this.villageCollectionObj);
/*       */     }
/*       */     else {
/*       */       
/*   479 */       this.villageCollectionObj = var7;
/*   480 */       this.villageCollectionObj.func_82566_a(this);
/*       */     } 
/*       */     
/*   483 */     if (this.worldInfo != null) {
/*   484 */       this.total_time = this.worldInfo.getWorldTotalTime(this.provider.dimensionId);
/*       */     }
/*   486 */     calculateInitialSkylight();
/*   487 */     _calculateInitialWeather();
/*       */ 
/*       */     
/*   490 */     RNG.init(this);
/*       */     
/*   492 */     this.mycelium_posts = createMyceliumPostField();
/*       */     
/*   494 */     this.underworld_y_offset = this.is_underworld ? 120 : 0;
/*       */   }
/*       */ 
/*       */   
/*       */   private ChunkPostField createMyceliumPostField() {
/*   499 */     return isUnderworld() ? new ChunkPostField(1, getHashedSeed(), 24, 0.0625F) : null;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public ChunkPostField getMyceliumPostField() {
/*   505 */     return this.mycelium_posts;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   protected abstract IChunkProvider createChunkProvider();
/*       */ 
/*       */ 
/*       */   
/*       */   protected void initialize(WorldSettings par1WorldSettings) {
/*   515 */     this.worldInfo.setServerInitialized(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setSpawnLocation() {
/*   523 */     setSpawnLocation(8, 64, 8);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getFirstUncoveredBlock(int par1, int par2) {
/*       */     int var3;
/*   534 */     for (var3 = 63; !isAirBlock(par1, var3 + 1, par2); var3++);
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   539 */     return getBlockId(par1, var3, par2);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getBlockHardness(int x, int y, int z) {
/*   546 */     return Block.blocksList[getBlockId(x, y, z)].getBlockHardness(getBlockMetadata(x, y, z));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getBlockId(int par1, int par2, int par3) {
/*   616 */     if (isWithinBlockDomain(par1, par3)) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*   627 */       if ((par2 & 0xFFFFFF00) == 0) {
/*       */         
/*   629 */         Chunk var4 = null;
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*   634 */           if (!this.isRemote) {
/*       */             
/*   636 */             ChunkProviderServer cps = (ChunkProviderServer)this.chunkProvider;
/*       */             
/*   638 */             LongHashMap lhm = cps.loadedChunkHashMap;
/*       */             
/*   640 */             long key = (par1 >> 4) & 0xFFFFFFFFL | ((par3 >> 4) & 0xFFFFFFFFL) << 32L;
/*       */             
/*   642 */             int var3_1 = (int)(key ^ key >>> 32L);
/*   643 */             var3_1 ^= var3_1 >>> 20 ^ var3_1 >>> 12;
/*       */ 
/*       */ 
/*       */             
/*   647 */             for (LongHashMapEntry var4_1 = lhm.hashArray[(var3_1 ^ var3_1 >>> 7 ^ var3_1 >>> 4) & lhm.hashArray.length - 1]; var4_1 != null; var4_1 = var4_1.nextEntry) {
/*       */               
/*   649 */               if (var4_1.key == key) {
/*       */                 
/*   651 */                 var4 = (Chunk)var4_1.value;
/*       */ 
/*       */                 
/*       */                 break;
/*       */               } 
/*       */             } 
/*       */             
/*   658 */             if (var4 == null) {
/*   659 */               var4 = (!cps.worldObj.findingSpawnPoint && !cps.loadChunkOnProvideRequest) ? cps.defaultEmptyChunk : cps.loadChunk(par1 >> 4, par3 >> 4);
/*       */             }
/*       */           }
/*       */           else {
/*       */             
/*   664 */             ChunkProviderClient cps = (ChunkProviderClient)this.chunkProvider;
/*       */             
/*   666 */             LongHashMap lhm = cps.chunkMapping;
/*       */             
/*   668 */             long key = (par1 >> 4) & 0xFFFFFFFFL | ((par3 >> 4) & 0xFFFFFFFFL) << 32L;
/*       */             
/*   670 */             int var3_1 = (int)(key ^ key >>> 32L);
/*   671 */             var3_1 ^= var3_1 >>> 20 ^ var3_1 >>> 12;
/*   672 */             var3_1 = var3_1 ^ var3_1 >>> 7 ^ var3_1 >>> 4;
/*       */             
/*   674 */             for (LongHashMapEntry var4_1 = lhm.hashArray[var3_1 & lhm.hashArray.length - 1]; var4_1 != null; var4_1 = var4_1.nextEntry) {
/*       */               
/*   676 */               if (var4_1.key == key) {
/*       */                 
/*   678 */                 var4 = (Chunk)var4_1.value;
/*       */                 
/*       */                 break;
/*       */               } 
/*       */             } 
/*   683 */             if (var4 == null) {
/*   684 */               var4 = cps.blankChunk;
/*       */             }
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*   697 */           if (var4.isEmpty())
/*       */           {
/*   699 */             return 0;
/*       */           }
/*       */ 
/*       */           
/*   703 */           ExtendedBlockStorage extended_block_storage = var4.storageArrays[par2 >> 4];
/*       */ 
/*       */           
/*   706 */           if (extended_block_storage == null)
/*       */           {
/*   708 */             return 0;
/*       */           }
/*       */ 
/*       */           
/*   712 */           int par1_and_15 = par1 & 0xF;
/*   713 */           int par2_and_15 = par2 & 0xF;
/*   714 */           int par3_and_15 = par3 & 0xF;
/*       */ 
/*       */ 
/*       */           
/*   718 */           if (extended_block_storage.blockMSBArray == null)
/*       */           {
/*       */             
/*   721 */             return extended_block_storage.blockLSBArray[par2_and_15 << 8 | par3_and_15 << 4 | par1_and_15] & 0xFF;
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*   726 */           int var7_2 = par2_and_15 << 8 | par3_and_15 << 4 | par1_and_15;
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*   731 */           return ((var7_2 & 0x1) == 0) ? (extended_block_storage.blockMSBArray.data[var7_2 >> 1] & 0xF) : (extended_block_storage.blockMSBArray.data[var7_2 >> 1] >> 4 & 0xF);
/*       */ 
/*       */ 
/*       */         
/*       */         }
/*   736 */         catch (Throwable var8) {
/*       */           
/*   738 */           CrashReport var6 = CrashReport.makeCrashReport(var8, "Exception getting block type in world");
/*   739 */           CrashReportCategory var7 = var6.makeCategory("Requested block coordinates");
/*   740 */           var7.addCrashSection("Found chunk", Boolean.valueOf((var4 == null)));
/*   741 */           var7.addCrashSection("Location", CrashReportCategory.getLocationInfo(par1, par2, par3));
/*   742 */           throw new ReportedException(var6);
/*       */         } 
/*       */       } 
/*       */ 
/*       */       
/*   747 */       return 0;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*   752 */     return 0;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isAdjacentToBlock(double x, double y, double z, int block_id) {
/*   758 */     return isAdjacentToBlock(MathHelper.floor_double(x), MathHelper.floor_double(y), MathHelper.floor_double(z), block_id);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isAdjacentToBlock(int x, int y, int z, int block_id) {
/*   763 */     for (int a = -1; a < 2; a++) {
/*       */       
/*   765 */       for (int b = -1; b < 2; b++) {
/*       */         
/*   767 */         for (int c = -1; c < 2; c++) {
/*       */           
/*   769 */           if (getBlockId(x + a, y + b, z + c) == block_id) {
/*   770 */             return true;
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*   775 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean blockTypeIsAbove(Block block, double x, double y, double z) {
/*   783 */     return blockTypeIsAbove(block, MathHelper.floor_double(x), MathHelper.floor_double(y), MathHelper.floor_double(z));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean blockTypeIsAbove(Block block, int x, int y, int z) {
/*   790 */     int block_id = block.blockID;
/*   791 */     int max_y = ((Block.lightOpacity[block_id] == 0) ? getActualHeight() : getHeightValue(x, z)) - 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   809 */     while (++y <= max_y) {
/*       */       
/*   811 */       int id = getBlockId(x, y, z);
/*       */       
/*   813 */       if (id == block_id)
/*   814 */         return true; 
/*   815 */       if (id > 0) {
/*   816 */         return false;
/*       */       }
/*       */     } 
/*   819 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public static float getDistanceFromDeltas(double dx, double dy, double dz) {
/*   824 */     return MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
/*       */   }
/*       */ 
/*       */   
/*       */   public static double getDistanceSqFromDeltas(double dx, double dy, double dz) {
/*   829 */     return dx * dx + dy * dy + dz * dz;
/*       */   }
/*       */ 
/*       */   
/*       */   public static double getDistanceSqFromDeltas(float dx, float dy, float dz) {
/*   834 */     return (dx * dx + dy * dy + dz * dz);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public static double getDistanceSqFromDeltas(double dx, double dz) {
/*   840 */     return dx * dx + dz * dz;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public static double getDistanceFromDeltas(double dx, double dz) {
/*   846 */     return MathHelper.sqrt_double(getDistanceSqFromDeltas(dx, dz));
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean blockTypeIsNearTo(int block_id, double origin_x, double origin_y, double origin_z, int horizontal_radius, int vertical_radius) {
/*   851 */     return blockTypeIsNearTo(block_id, MathHelper.floor_double(origin_x), MathHelper.floor_double(origin_y), MathHelper.floor_double(origin_z), horizontal_radius, vertical_radius);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean blockTypeIsNearTo(int block_id, int origin_x, int origin_y, int origin_z, int horizontal_radius, int vertical_radius) {
/*   856 */     int x = origin_x;
/*   857 */     int y = origin_y;
/*   858 */     int z = origin_z;
/*       */     
/*   860 */     int width = horizontal_radius * 2 + 1;
/*       */     
/*   862 */     int run = 1;
/*       */     
/*   864 */     int min_y = y - vertical_radius;
/*   865 */     int max_y = y + vertical_radius;
/*       */     
/*   867 */     for (y = min_y; y <= max_y; y++) {
/*   868 */       if (getBlockId(x, y, z) == block_id) {
/*   869 */         return true;
/*       */       }
/*       */     } 
/*       */     while (true) {
/*   873 */       if (run >= width) {
/*       */         
/*   875 */         for (int j = 1; j < run; j++) {
/*       */           
/*   877 */           x++;
/*       */           
/*   879 */           for (y = min_y; y <= max_y; y++) {
/*   880 */             if (getBlockId(x, y, z) == block_id)
/*   881 */               return true; 
/*       */           } 
/*       */         } 
/*   884 */         return false;
/*       */       } 
/*       */       
/*       */       int i;
/*   888 */       for (i = 1; i <= run; i++) {
/*       */         
/*   890 */         x++;
/*       */         
/*   892 */         for (y = min_y; y <= max_y; y++) {
/*   893 */           if (getBlockId(x, y, z) == block_id) {
/*   894 */             return true;
/*       */           }
/*       */         } 
/*       */       } 
/*   898 */       for (i = 1; i <= run; i++) {
/*       */         
/*   900 */         z++;
/*       */         
/*   902 */         for (y = min_y; y <= max_y; y++) {
/*   903 */           if (getBlockId(x, y, z) == block_id)
/*   904 */             return true; 
/*       */         } 
/*       */       } 
/*   907 */       run++;
/*       */       
/*   909 */       for (i = 1; i <= run; i++) {
/*       */         
/*   911 */         x--;
/*       */         
/*   913 */         for (y = min_y; y <= max_y; y++) {
/*   914 */           if (getBlockId(x, y, z) == block_id)
/*   915 */             return true; 
/*       */         } 
/*       */       } 
/*   918 */       for (i = 1; i <= run; i++) {
/*       */         
/*   920 */         z--;
/*       */         
/*   922 */         for (y = min_y; y <= max_y; y++) {
/*   923 */           if (getBlockId(x, y, z) == block_id)
/*   924 */             return true; 
/*       */         } 
/*       */       } 
/*   927 */       run++;
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   public double shortestDistanceToBlockType(int block_id, double origin_x, double origin_y, double origin_z, int horizontal_radius, int vertical_radius) {
/*   933 */     int x = MathHelper.floor_double(origin_x);
/*   934 */     int y = MathHelper.floor_double(origin_y);
/*   935 */     int z = MathHelper.floor_double(origin_z);
/*       */     
/*   937 */     origin_x -= 0.5D;
/*   938 */     origin_y -= 0.5D;
/*   939 */     origin_z -= 0.5D;
/*       */     
/*   941 */     int width = horizontal_radius * 2 + 1;
/*   942 */     int height = vertical_radius * 2 + 1;
/*       */     
/*   944 */     int run = 1;
/*       */     
/*   946 */     int min_y = y - vertical_radius;
/*   947 */     int max_y = y + vertical_radius;
/*       */     
/*   949 */     List<Float> distances = new ArrayList<Float>();
/*       */     
/*   951 */     for (y = min_y; y <= max_y; y++) {
/*   952 */       if (getBlockId(x, y, z) == block_id) {
/*   953 */         distances.add(Float.valueOf(getDistanceFromDeltas(x - origin_x, y - origin_y, z - origin_z)));
/*       */       }
/*       */     } 
/*       */     while (true) {
/*   957 */       if (run >= width || (run >= height && !distances.isEmpty())) {
/*       */         
/*   959 */         for (int j = 1; j < run; j++) {
/*       */           
/*   961 */           x++;
/*       */           
/*   963 */           for (y = min_y; y <= max_y; y++) {
/*   964 */             if (getBlockId(x, y, z) == block_id)
/*   965 */               distances.add(Float.valueOf(getDistanceFromDeltas(x - origin_x, y - origin_y, z - origin_z))); 
/*       */           } 
/*       */         } 
/*   968 */         if (distances.isEmpty())
/*       */         {
/*   970 */           return -1.0D;
/*       */         }
/*       */ 
/*       */         
/*   974 */         double least_distance = ((Float)distances.get(0)).floatValue();
/*       */         
/*   976 */         for (int k = 1; k < distances.size(); k++) {
/*   977 */           if (((Float)distances.get(k)).floatValue() < least_distance)
/*   978 */             least_distance = ((Float)distances.get(k)).floatValue(); 
/*       */         } 
/*   980 */         return least_distance;
/*       */       } 
/*       */       
/*       */       int i;
/*       */       
/*   985 */       for (i = 1; i <= run; i++) {
/*       */         
/*   987 */         x++;
/*       */         
/*   989 */         for (y = min_y; y <= max_y; y++) {
/*   990 */           if (getBlockId(x, y, z) == block_id) {
/*   991 */             distances.add(Float.valueOf(getDistanceFromDeltas(x - origin_x, y - origin_y, z - origin_z)));
/*       */           }
/*       */         } 
/*       */       } 
/*   995 */       for (i = 1; i <= run; i++) {
/*       */         
/*   997 */         z++;
/*       */         
/*   999 */         for (y = min_y; y <= max_y; y++) {
/*  1000 */           if (getBlockId(x, y, z) == block_id)
/*  1001 */             distances.add(Float.valueOf(getDistanceFromDeltas(x - origin_x, y - origin_y, z - origin_z))); 
/*       */         } 
/*       */       } 
/*  1004 */       run++;
/*       */       
/*  1006 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1008 */         x--;
/*       */         
/*  1010 */         for (y = min_y; y <= max_y; y++) {
/*  1011 */           if (getBlockId(x, y, z) == block_id)
/*  1012 */             distances.add(Float.valueOf(getDistanceFromDeltas(x - origin_x, y - origin_y, z - origin_z))); 
/*       */         } 
/*       */       } 
/*  1015 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1017 */         z--;
/*       */         
/*  1019 */         for (y = min_y; y <= max_y; y++) {
/*  1020 */           if (getBlockId(x, y, z) == block_id)
/*  1021 */             distances.add(Float.valueOf(getDistanceFromDeltas(x - origin_x, y - origin_y, z - origin_z))); 
/*       */         } 
/*       */       } 
/*  1024 */       run++;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private int getNearestCandidateIndex(double[] candidate_distance_sq, int num_candidates) {
/*  1035 */     int nearest_candidate_index = 0;
/*  1036 */     double nearest_candidate_distance_sq = candidate_distance_sq[nearest_candidate_index];
/*       */     
/*  1038 */     for (int candidate_index = 1; candidate_index < num_candidates; candidate_index++) {
/*       */       
/*  1040 */       if (candidate_distance_sq[candidate_index] < nearest_candidate_distance_sq) {
/*       */         
/*  1042 */         nearest_candidate_index = candidate_index;
/*  1043 */         nearest_candidate_distance_sq = candidate_distance_sq[candidate_index];
/*       */       } 
/*       */     } 
/*       */     
/*  1047 */     return nearest_candidate_index;
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean nearestBlockCoords(double origin_x, double origin_y, double origin_z, int horizontal_radius, int vertical_radius, int block_id, int[] block_coords) {
/*  1052 */     return nearestBlockCoords((float)origin_x, (float)origin_y, (float)origin_z, horizontal_radius, vertical_radius, block_id, block_coords);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean nearestBlockCoords(float origin_x, float origin_y, float origin_z, int horizontal_radius, int vertical_radius, int block_id, int[] block_coords) {
/*  1057 */     int x = MathHelper.floor_double(origin_x);
/*  1058 */     int y = MathHelper.floor_double(origin_y);
/*  1059 */     int z = MathHelper.floor_double(origin_z);
/*       */     
/*  1061 */     origin_x -= 0.5F;
/*  1062 */     origin_y -= 0.5F;
/*  1063 */     origin_z -= 0.5F;
/*       */     
/*  1065 */     int width = horizontal_radius * 2 + 1;
/*  1066 */     int height = vertical_radius * 2 + 1;
/*       */     
/*  1068 */     int run = 1;
/*       */     
/*  1070 */     int min_y = y - vertical_radius;
/*  1071 */     int max_y = y + vertical_radius;
/*       */     
/*  1073 */     int max_candidates = 64;
/*       */     
/*  1075 */     int[] candidate_x = new int[max_candidates];
/*  1076 */     int[] candidate_y = new int[max_candidates];
/*  1077 */     int[] candidate_z = new int[max_candidates];
/*  1078 */     double[] candidate_distance_sq = new double[max_candidates];
/*       */     
/*  1080 */     int next_candidate_index = 0;
/*       */ 
/*       */ 
/*       */     
/*  1084 */     for (y = min_y; y <= max_y; y++) {
/*       */       
/*  1086 */       if (getBlockId(x, y, z) == block_id) {
/*       */ 
/*       */         
/*  1089 */         candidate_x[next_candidate_index] = x;
/*  1090 */         candidate_y[next_candidate_index] = y;
/*  1091 */         candidate_z[next_candidate_index] = z;
/*  1092 */         candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */         
/*  1094 */         if (++next_candidate_index == max_candidates) {
/*       */           
/*  1096 */           int nearest_candidate_index = getNearestCandidateIndex(candidate_distance_sq, max_candidates);
/*       */           
/*  1098 */           block_coords[0] = candidate_x[nearest_candidate_index];
/*  1099 */           block_coords[1] = candidate_y[nearest_candidate_index];
/*  1100 */           block_coords[2] = candidate_z[nearest_candidate_index];
/*       */           
/*  1102 */           return true;
/*       */         } 
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*       */     while (true) {
/*  1110 */       if (run >= width || (run >= height && next_candidate_index > 0)) {
/*       */         
/*  1112 */         for (int j = 1; j < run; j++) {
/*       */           
/*  1114 */           x++;
/*       */           
/*  1116 */           for (y = min_y; y <= max_y; y++) {
/*       */             
/*  1118 */             if (getBlockId(x, y, z) == block_id) {
/*       */ 
/*       */               
/*  1121 */               candidate_x[next_candidate_index] = x;
/*  1122 */               candidate_y[next_candidate_index] = y;
/*  1123 */               candidate_z[next_candidate_index] = z;
/*  1124 */               candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */               
/*  1126 */               if (++next_candidate_index == max_candidates) {
/*       */                 
/*  1128 */                 int k = getNearestCandidateIndex(candidate_distance_sq, max_candidates);
/*       */                 
/*  1130 */                 block_coords[0] = candidate_x[k];
/*  1131 */                 block_coords[1] = candidate_y[k];
/*  1132 */                 block_coords[2] = candidate_z[k];
/*       */                 
/*  1134 */                 return true;
/*       */               } 
/*       */             } 
/*       */           } 
/*       */         } 
/*       */ 
/*       */         
/*  1141 */         if (next_candidate_index == 0)
/*       */         {
/*  1143 */           return false;
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1153 */         int nearest_candidate_index = getNearestCandidateIndex(candidate_distance_sq, next_candidate_index);
/*       */         
/*  1155 */         block_coords[0] = candidate_x[nearest_candidate_index];
/*  1156 */         block_coords[1] = candidate_y[nearest_candidate_index];
/*  1157 */         block_coords[2] = candidate_z[nearest_candidate_index];
/*       */         
/*  1159 */         return true;
/*       */       } 
/*       */       
/*       */       int i;
/*       */       
/*  1164 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1166 */         x++;
/*       */         
/*  1168 */         for (y = min_y; y <= max_y; y++) {
/*       */           
/*  1170 */           if (getBlockId(x, y, z) == block_id) {
/*       */ 
/*       */             
/*  1173 */             candidate_x[next_candidate_index] = x;
/*  1174 */             candidate_y[next_candidate_index] = y;
/*  1175 */             candidate_z[next_candidate_index] = z;
/*  1176 */             candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */             
/*  1178 */             if (++next_candidate_index == max_candidates) {
/*       */               
/*  1180 */               int nearest_candidate_index = getNearestCandidateIndex(candidate_distance_sq, max_candidates);
/*       */               
/*  1182 */               block_coords[0] = candidate_x[nearest_candidate_index];
/*  1183 */               block_coords[1] = candidate_y[nearest_candidate_index];
/*  1184 */               block_coords[2] = candidate_z[nearest_candidate_index];
/*       */               
/*  1186 */               return true;
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } 
/*       */ 
/*       */       
/*  1193 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1195 */         z++;
/*       */         
/*  1197 */         for (y = min_y; y <= max_y; y++) {
/*       */           
/*  1199 */           if (getBlockId(x, y, z) == block_id) {
/*       */ 
/*       */             
/*  1202 */             candidate_x[next_candidate_index] = x;
/*  1203 */             candidate_y[next_candidate_index] = y;
/*  1204 */             candidate_z[next_candidate_index] = z;
/*  1205 */             candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */             
/*  1207 */             if (++next_candidate_index == max_candidates) {
/*       */               
/*  1209 */               int nearest_candidate_index = getNearestCandidateIndex(candidate_distance_sq, max_candidates);
/*       */               
/*  1211 */               block_coords[0] = candidate_x[nearest_candidate_index];
/*  1212 */               block_coords[1] = candidate_y[nearest_candidate_index];
/*  1213 */               block_coords[2] = candidate_z[nearest_candidate_index];
/*       */               
/*  1215 */               return true;
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } 
/*       */ 
/*       */       
/*  1222 */       run++;
/*       */       
/*  1224 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1226 */         x--;
/*       */         
/*  1228 */         for (y = min_y; y <= max_y; y++) {
/*       */           
/*  1230 */           if (getBlockId(x, y, z) == block_id) {
/*       */ 
/*       */             
/*  1233 */             candidate_x[next_candidate_index] = x;
/*  1234 */             candidate_y[next_candidate_index] = y;
/*  1235 */             candidate_z[next_candidate_index] = z;
/*  1236 */             candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */             
/*  1238 */             if (++next_candidate_index == max_candidates) {
/*       */               
/*  1240 */               int nearest_candidate_index = getNearestCandidateIndex(candidate_distance_sq, max_candidates);
/*       */               
/*  1242 */               block_coords[0] = candidate_x[nearest_candidate_index];
/*  1243 */               block_coords[1] = candidate_y[nearest_candidate_index];
/*  1244 */               block_coords[2] = candidate_z[nearest_candidate_index];
/*       */               
/*  1246 */               return true;
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } 
/*       */       
/*  1252 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1254 */         z--;
/*       */         
/*  1256 */         for (y = min_y; y <= max_y; y++) {
/*       */           
/*  1258 */           if (getBlockId(x, y, z) == block_id) {
/*       */ 
/*       */             
/*  1261 */             candidate_x[next_candidate_index] = x;
/*  1262 */             candidate_y[next_candidate_index] = y;
/*  1263 */             candidate_z[next_candidate_index] = z;
/*  1264 */             candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */             
/*  1266 */             if (++next_candidate_index == max_candidates) {
/*       */               
/*  1268 */               int nearest_candidate_index = getNearestCandidateIndex(candidate_distance_sq, max_candidates);
/*       */               
/*  1270 */               block_coords[0] = candidate_x[nearest_candidate_index];
/*  1271 */               block_coords[1] = candidate_y[nearest_candidate_index];
/*  1272 */               block_coords[2] = candidate_z[nearest_candidate_index];
/*       */               
/*  1274 */               return true;
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } 
/*       */       
/*  1280 */       run++;
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   private boolean intArrayContains(int[] array, int value) {
/*  1286 */     int len = array.length;
/*       */     
/*  1288 */     while (--len >= 0) {
/*  1289 */       if (array[len] == value)
/*  1290 */         return true; 
/*       */     } 
/*  1292 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public int getNearestBlockCandidates(double origin_x, double origin_y, double origin_z, int horizontal_radius, int vertical_radius, int max_candidates, int[] block_ids, int[] candidate_x, int[] candidate_y, int[] candidate_z, double[] candidate_distance_sq) {
/*  1297 */     return getNearestBlockCandidates((float)origin_x, (float)origin_y, (float)origin_z, horizontal_radius, vertical_radius, max_candidates, block_ids, candidate_x, candidate_y, candidate_z, candidate_distance_sq);
/*       */   }
/*       */ 
/*       */   
/*       */   public int getNearestBlockCandidates(float origin_x, float origin_y, float origin_z, int horizontal_radius, int vertical_radius, int max_candidates, int[] block_ids, int[] candidate_x, int[] candidate_y, int[] candidate_z, double[] candidate_distance_sq) {
/*  1302 */     int x = MathHelper.floor_double(origin_x);
/*  1303 */     int y = MathHelper.floor_double(origin_y);
/*  1304 */     int z = MathHelper.floor_double(origin_z);
/*       */     
/*  1306 */     origin_x -= 0.5F;
/*  1307 */     origin_y -= 0.5F;
/*  1308 */     origin_z -= 0.5F;
/*       */     
/*  1310 */     int width = horizontal_radius * 2 + 1;
/*  1311 */     int height = vertical_radius * 2 + 1;
/*       */     
/*  1313 */     int run = 1;
/*       */     
/*  1315 */     int min_y = y - vertical_radius;
/*  1316 */     int max_y = y + vertical_radius;
/*       */     
/*  1318 */     int next_candidate_index = 0;
/*       */     
/*  1320 */     for (y = min_y; y <= max_y; y++) {
/*       */ 
/*       */       
/*  1323 */       if (intArrayContains(block_ids, getBlockId(x, y, z))) {
/*       */         
/*  1325 */         candidate_x[next_candidate_index] = x;
/*  1326 */         candidate_y[next_candidate_index] = y;
/*  1327 */         candidate_z[next_candidate_index] = z;
/*  1328 */         candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */         
/*  1330 */         if (++next_candidate_index == max_candidates) {
/*  1331 */           return max_candidates;
/*       */         }
/*       */       } 
/*       */     } 
/*       */     
/*       */     while (true) {
/*  1337 */       if (run >= width) {
/*       */         
/*  1339 */         for (int j = 1; j < run; j++) {
/*       */           
/*  1341 */           x++;
/*       */           
/*  1343 */           for (y = min_y; y <= max_y; y++) {
/*       */ 
/*       */             
/*  1346 */             if (intArrayContains(block_ids, getBlockId(x, y, z))) {
/*       */               
/*  1348 */               candidate_x[next_candidate_index] = x;
/*  1349 */               candidate_y[next_candidate_index] = y;
/*  1350 */               candidate_z[next_candidate_index] = z;
/*  1351 */               candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */               
/*  1353 */               if (++next_candidate_index == max_candidates) {
/*  1354 */                 return max_candidates;
/*       */               }
/*       */             } 
/*       */           } 
/*       */         } 
/*  1359 */         return next_candidate_index;
/*       */       } 
/*       */       
/*       */       int i;
/*  1363 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1365 */         x++;
/*       */         
/*  1367 */         for (y = min_y; y <= max_y; y++) {
/*       */ 
/*       */           
/*  1370 */           if (intArrayContains(block_ids, getBlockId(x, y, z))) {
/*       */             
/*  1372 */             candidate_x[next_candidate_index] = x;
/*  1373 */             candidate_y[next_candidate_index] = y;
/*  1374 */             candidate_z[next_candidate_index] = z;
/*  1375 */             candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */             
/*  1377 */             if (++next_candidate_index == max_candidates) {
/*  1378 */               return max_candidates;
/*       */             }
/*       */           } 
/*       */         } 
/*       */       } 
/*       */       
/*  1384 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1386 */         z++;
/*       */         
/*  1388 */         for (y = min_y; y <= max_y; y++) {
/*       */ 
/*       */           
/*  1391 */           if (intArrayContains(block_ids, getBlockId(x, y, z))) {
/*       */             
/*  1393 */             candidate_x[next_candidate_index] = x;
/*  1394 */             candidate_y[next_candidate_index] = y;
/*  1395 */             candidate_z[next_candidate_index] = z;
/*  1396 */             candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */             
/*  1398 */             if (++next_candidate_index == max_candidates) {
/*  1399 */               return max_candidates;
/*       */             }
/*       */           } 
/*       */         } 
/*       */       } 
/*  1404 */       run++;
/*       */       
/*  1406 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1408 */         x--;
/*       */         
/*  1410 */         for (y = min_y; y <= max_y; y++) {
/*       */ 
/*       */           
/*  1413 */           if (intArrayContains(block_ids, getBlockId(x, y, z))) {
/*       */             
/*  1415 */             candidate_x[next_candidate_index] = x;
/*  1416 */             candidate_y[next_candidate_index] = y;
/*  1417 */             candidate_z[next_candidate_index] = z;
/*  1418 */             candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */             
/*  1420 */             if (++next_candidate_index == max_candidates) {
/*  1421 */               return max_candidates;
/*       */             }
/*       */           } 
/*       */         } 
/*       */       } 
/*  1426 */       for (i = 1; i <= run; i++) {
/*       */         
/*  1428 */         z--;
/*       */         
/*  1430 */         for (y = min_y; y <= max_y; y++) {
/*       */ 
/*       */           
/*  1433 */           if (intArrayContains(block_ids, getBlockId(x, y, z))) {
/*       */             
/*  1435 */             candidate_x[next_candidate_index] = x;
/*  1436 */             candidate_y[next_candidate_index] = y;
/*  1437 */             candidate_z[next_candidate_index] = z;
/*  1438 */             candidate_distance_sq[next_candidate_index] = getDistanceSqFromDeltas(x - origin_x, y - origin_y, z - origin_z);
/*       */             
/*  1440 */             if (++next_candidate_index == max_candidates) {
/*  1441 */               return max_candidates;
/*       */             }
/*       */           } 
/*       */         } 
/*       */       } 
/*  1446 */       run++;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isAirBlock(int par1, int par2, int par3) {
/*  1455 */     return (getBlockId(par1, par2, par3) == 0);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean blockHasTileEntity(int par1, int par2, int par3) {
/*  1463 */     int var4 = getBlockId(par1, par2, par3);
/*  1464 */     return (Block.blocksList[var4] != null && Block.blocksList[var4].hasTileEntity());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int blockGetRenderType(int par1, int par2, int par3) {
/*  1472 */     int var4 = getBlockId(par1, par2, par3);
/*  1473 */     return (Block.blocksList[var4] != null) ? Block.blocksList[var4].getRenderType() : -1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean blockExists(int par1, int par2, int par3) {
/*  1481 */     return (par2 >= 0 && par2 < 256) ? chunkExists(par1 >> 4, par3 >> 4) : false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean doChunksNearChunkExist(int par1, int par2, int par3, int par4, boolean include_empty_chunks) {
/*  1491 */     return checkChunksExist(par1 - par4, par2 - par4, par3 - par4, par1 + par4, par2 + par4, par3 + par4, include_empty_chunks);
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean doChunksNearChunkExist(int par1, int par2, int par3, int par4) {
/*  1496 */     return doChunksNearChunkExist(par1, par2, par3, par4, true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean checkChunksExist(int par1, int par2, int par3, int par4, int par5, int par6, boolean include_empty_chunks) {
/*  1506 */     if (par5 >= 0 && par2 < 256) {
/*       */       
/*  1508 */       par1 >>= 4;
/*  1509 */       par3 >>= 4;
/*  1510 */       par4 >>= 4;
/*  1511 */       par6 >>= 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1527 */       if (include_empty_chunks) {
/*       */         
/*  1529 */         for (int var7 = par1; var7 <= par4; var7++) {
/*       */           
/*  1531 */           for (int var8 = par3; var8 <= par6; var8++) {
/*       */             
/*  1533 */             if (!chunkExists(var7, var8)) {
/*  1534 */               return false;
/*       */             }
/*       */           } 
/*       */         } 
/*       */       } else {
/*       */         
/*  1540 */         for (int var7 = par1; var7 <= par4; var7++) {
/*       */           
/*  1542 */           for (int var8 = par3; var8 <= par6; var8++) {
/*       */             
/*  1544 */             Chunk chunk = getChunkIfItExists(var7, var8);
/*       */             
/*  1546 */             if (chunk == null || chunk.isEmpty()) {
/*  1547 */               return false;
/*       */             }
/*       */           } 
/*       */         } 
/*       */       } 
/*  1552 */       return true;
/*       */     } 
/*       */ 
/*       */     
/*  1556 */     Debug.setErrorMessage("checkChunksExist: got here");
/*  1557 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean checkChunksExist(int par1, int par2, int par3, int par4, int par5, int par6) {
/*  1563 */     return checkChunksExist(par1, par2, par3, par4, par5, par6, true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   protected final boolean chunkExists(int par1, int par2) {
/*  1572 */     return this.chunkProvider.chunkExists(par1, par2);
/*       */   }
/*       */ 
/*       */   
/*       */   protected final boolean chunkExistsAndIsNotEmpty(int chunk_x, int chunk_z) {
/*  1577 */     return (this.chunkProvider.chunkExists(chunk_x, chunk_z) && !getChunkFromChunkCoords(chunk_x, chunk_z).isEmpty());
/*       */   }
/*       */ 
/*       */   
/*       */   protected final boolean chunkExistsAndIsNotEmptyFromBlockCoords(int x, int z) {
/*  1582 */     return chunkExistsAndIsNotEmpty(x >> 4, z >> 4);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean doesChunkAndAllNeighborsExist(int chunk_x, int chunk_z, int range, boolean include_empty_chunks) {
/*  1588 */     if (!chunkExists(chunk_x, chunk_z)) {
/*  1589 */       return false;
/*       */     }
/*  1591 */     Chunk chunk = getChunkFromChunkCoords(chunk_x, chunk_z);
/*       */     
/*  1593 */     if (!include_empty_chunks && chunk.isEmpty()) {
/*  1594 */       return false;
/*       */     }
/*  1596 */     return chunk.doAllNeighborsExist(range, false, include_empty_chunks);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final Chunk getChunkIfItExists(int chunk_x, int chunk_z) {
/*  1602 */     return this.chunkProvider.getChunkIfItExists(chunk_x, chunk_z);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final Chunk getChunkFromBlockCoordsIfItExists(int x, int z) {
/*  1608 */     return getChunkIfItExists(x >> 4, z >> 4);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final Chunk getChunkFromBlockCoords(int par1, int par2) {
/*  1617 */     return getChunkFromChunkCoords(par1 >> 4, par2 >> 4);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final Chunk getChunkFromChunkCoords(int par1, int par2) {
/*  1626 */     return this.chunkProvider.provideChunk(par1, par2);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final Chunk getChunkFromPosition(double pos_x, double pos_z) {
/*  1632 */     return getChunkFromChunkCoords(Chunk.getChunkCoordFromDouble(pos_x), Chunk.getChunkCoordFromDouble(pos_z));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean setBlockWithDefaultMetadata(int x, int y, int z, Block block, int flags, boolean report_metadata_failure) {
/*  1638 */     int metadata = block.getDefaultMetadata(this, x, y, z);
/*       */     
/*  1640 */     if (metadata < 0) {
/*       */       
/*  1642 */       if (report_metadata_failure) {
/*  1643 */         Minecraft.setErrorMessage("setBlockWithDefaultMetadata: unable to place " + block.getLocalizedName() + " at " + x + "," + y + "," + z + " because a valid default metadata could not be obtained");
/*       */       }
/*  1645 */       return false;
/*       */     } 
/*       */     
/*  1648 */     boolean result = setBlock(x, y, z, block.blockID, metadata, flags);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1656 */     return result;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean setBlockWithMetadataAdjustedForCoordBaseMode(int x, int y, int z, Block block, int metadata_in_coord_base_mode_2, int flags, int coord_base_mode) {
/*  1664 */     EnumDirection direction_facing_in_coord_base_mode_2 = block.getDirectionFacing(metadata_in_coord_base_mode_2);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1687 */     int adjusted_metadata = (direction_facing_in_coord_base_mode_2 == null) ? metadata_in_coord_base_mode_2 : block.getMetadataForDirectionFacing(metadata_in_coord_base_mode_2, direction_facing_in_coord_base_mode_2.adjustForCoordBaseMode(coord_base_mode), coord_base_mode);
/*       */     
/*  1689 */     if (adjusted_metadata < 0) {
/*       */       
/*  1691 */       Minecraft.setErrorMessage("setBlockWithMetadataAdjustedForCoordBaseMode: invalid adjusted metadata for " + block + " at " + x + "," + y + "," + z);
/*  1692 */       return false;
/*       */     } 
/*       */     
/*  1695 */     return setBlock(x, y, z, block.blockID, adjusted_metadata, flags);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean setBlock(int par1, int par2, int par3, int par4, int par5, int par6) {
/*  1716 */     if (isWithinBlockBounds(par1, par2, par3)) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1728 */       Chunk var7 = getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1733 */       if (var7.isEmpty()) {
/*       */         
/*  1735 */         Debug.setErrorMessage("setBlock: called for coords in empty chunk");
/*  1736 */         Debug.printStackTrace();
/*       */         
/*  1738 */         return false;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1748 */       int block_id_before = var7.getBlockID(par1 & 0xF, par2, par3 & 0xF);
/*       */ 
/*       */       
/*  1751 */       boolean var9 = var7.setBlockIDWithMetadata(par1 & 0xF, par2, par3 & 0xF, par4, par5, block_id_before);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1763 */       if (Block.lightOpacity[par4] != Block.lightOpacity[block_id_before]) {
/*  1764 */         updateAllLightTypes(par1, par2, par3, var7);
/*  1765 */       } else if (Block.lightValue[par4] != Block.lightValue[block_id_before]) {
/*       */         
/*  1767 */         propagateBlocklight(par1, par2, par3, false, var7);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1780 */       if (var9) {
/*       */         
/*  1782 */         if ((par6 & 0x2) != 0 && (!this.isRemote || (par6 & 0x4) == 0))
/*       */         {
/*  1784 */           markBlockForUpdate(par1, par2, par3);
/*       */         }
/*       */         
/*  1787 */         if (!this.isRemote && (par6 & 0x1) != 0) {
/*       */ 
/*       */ 
/*       */           
/*  1791 */           notifyBlockChange(par1, par2, par3, block_id_before);
/*  1792 */           Block var10 = Block.blocksList[par4];
/*       */           
/*  1794 */           if (var10 != null && var10.hasComparatorInputOverride())
/*       */           {
/*  1796 */             func_96440_m(par1, par2, par3, par4);
/*       */           }
/*       */         } 
/*       */       } 
/*       */       
/*  1801 */       return var9;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  1806 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final Material getBlockMaterial(int par1, int par2, int par3) {
/*  1816 */     int var4 = getBlockId(par1, par2, par3);
/*  1817 */     return (var4 == 0) ? Material.air : (Block.blocksList[var4]).blockMaterial;
/*       */   }
/*       */ 
/*       */   
/*       */   public final Material getBlockMaterial(int block_id) {
/*  1822 */     return (block_id == 0) ? Material.air : (Block.blocksList[block_id]).blockMaterial;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getBlockMetadata(int x, int y, int z) {
/*  1882 */     return isWithinBlockBounds(x, y, z) ? getChunkFromChunkCoords(x >> 4, z >> 4).getBlockMetadata(x & 0xF, y, z & 0xF) : 0;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean setBlockMetadataWithNotify(int par1, int par2, int par3, int par4, int par5) {
/*  1899 */     if (isWithinBlockDomain(par1, par3)) {
/*       */       
/*  1901 */       if (par2 < 0)
/*       */       {
/*  1903 */         return false;
/*       */       }
/*  1905 */       if (par2 >= 256)
/*       */       {
/*  1907 */         return false;
/*       */       }
/*       */ 
/*       */       
/*  1911 */       Chunk var6 = getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
/*  1912 */       int var7 = par1 & 0xF;
/*  1913 */       int var8 = par3 & 0xF;
/*  1914 */       boolean var9 = var6.setBlockMetadata(var7, par2, var8, par4);
/*       */       
/*  1916 */       if (var9) {
/*       */         
/*  1918 */         int var10 = var6.getBlockID(var7, par2, var8);
/*       */         
/*  1920 */         if ((par5 & 0x2) != 0 && (!this.isRemote || (par5 & 0x4) == 0))
/*       */         {
/*  1922 */           markBlockForUpdate(par1, par2, par3);
/*       */         }
/*       */         
/*  1925 */         if (!this.isRemote && (par5 & 0x1) != 0) {
/*       */           
/*  1927 */           notifyBlockChange(par1, par2, par3, var10);
/*  1928 */           Block var11 = Block.blocksList[var10];
/*       */           
/*  1930 */           if (var11 != null && var11.hasComparatorInputOverride())
/*       */           {
/*  1932 */             func_96440_m(par1, par2, par3, var10);
/*       */           }
/*       */         } 
/*       */       } 
/*       */       
/*  1937 */       return var9;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  1942 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean setBlockToAir(int x, int y, int z) {
/*  1952 */     return setBlock(x, y, z, 0, 0, 3);
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean setBlockToAir(int x, int y, int z, int flags) {
/*  1957 */     return setBlock(x, y, z, 0, 0, flags);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean destroyBlock(BlockBreakInfo info, boolean drop_as_item) {
/*  1987 */     return destroyBlock(info, drop_as_item, false);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean destroyBlock(BlockBreakInfo info, boolean drop_as_item, boolean suppress_sound) {
/*  1992 */     if (info.wasSilkHarvested()) {
/*  1993 */       Minecraft.setErrorMessage("destroyBlock: not meant to handle silk harvesting");
/*       */     }
/*  1995 */     int x = info.x;
/*  1996 */     int y = info.y;
/*  1997 */     int z = info.z;
/*       */     
/*  1999 */     int block_id = getBlockId(x, y, z);
/*       */     
/*  2001 */     if (block_id > 0) {
/*       */       
/*  2003 */       if (block_id != info.block_id) {
/*       */         
/*  2005 */         Minecraft.setErrorMessage("destroyBlock: block mismatch");
/*  2006 */         return false;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  2011 */       int data = block_id + (info.getMetadata() << 12);
/*       */       
/*  2013 */       if (suppress_sound) {
/*  2014 */         data |= RenderGlobal.SFX_2001_SUPPRESS_SOUND;
/*       */       }
/*       */       
/*  2017 */       if (info.wasNotLegal()) {
/*  2018 */         data |= RenderGlobal.SFX_2001_WAS_NOT_LEGAL;
/*       */       }
/*  2020 */       playAuxSFX(2001, x, y, z, data);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2026 */       if (drop_as_item) {
/*       */         
/*  2028 */         Block block = Block.getBlock(block_id);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2033 */         block.dropBlockAsEntityItem(info);
/*       */       } 
/*       */       
/*  2036 */       return setBlock(x, y, z, 0, 0, 3);
/*       */     } 
/*       */ 
/*       */     
/*  2040 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean destroyBlockWithoutDroppingItem(int x, int y, int z, EnumBlockFX fx) {
/*  2047 */     Block block = getBlock(x, y, z);
/*       */     
/*  2049 */     if (block == null) {
/*       */       
/*  2051 */       Debug.setErrorMessage("destroyBlockWithoutDroppingItem: no block found at " + StringHelper.getCoordsAsString(x, y, z));
/*  2052 */       return false;
/*       */     } 
/*       */     
/*  2055 */     if (block instanceof IBlockWithPartner) {
/*       */ 
/*       */ 
/*       */       
/*  2059 */       IBlockWithPartner block_with_partner = (IBlockWithPartner)block;
/*       */       
/*  2061 */       int metadata = getBlockMetadata(x, y, z);
/*       */       
/*  2063 */       if (block_with_partner.requiresPartner(metadata) && block_with_partner.isPartnerPresent(this, x, y, z)) {
/*       */         
/*  2065 */         int partner_x = x + block_with_partner.getPartnerOffsetX(metadata);
/*  2066 */         int partner_y = y + block_with_partner.getPartnerOffsetY(metadata);
/*  2067 */         int partner_z = z + block_with_partner.getPartnerOffsetZ(metadata);
/*       */         
/*  2069 */         if (fx != null) {
/*  2070 */           blockFX(fx, partner_x, partner_y, partner_z);
/*       */         }
/*  2072 */         if (block_with_partner.partnerDropsAsItem(metadata)) {
/*  2073 */           setBlockToAir(partner_x, partner_y, partner_z);
/*       */         }
/*       */       } 
/*       */     } 
/*  2077 */     if (fx != null) {
/*  2078 */       blockFX(fx, x, y, z);
/*       */     }
/*  2080 */     return setBlockToAir(x, y, z);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean destroyBlockWithoutDroppingItem(int x, int y, int z) {
/*  2086 */     return destroyBlockWithoutDroppingItem(x, y, z, null);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean setBlock(int par1, int par2, int par3, int par4) {
/*  2094 */     return setBlock(par1, par2, par3, par4, 0, 3);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void markBlockForUpdate(int par1, int par2, int par3) {
/*  2103 */     for (int var4 = 0; var4 < this.worldAccesses.size(); var4++)
/*       */     {
/*  2105 */       ((IWorldAccess)this.worldAccesses.get(var4)).markBlockForUpdate(par1, par2, par3);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void notifyBlockChange(int par1, int par2, int par3, int par4) {
/*  2114 */     notifyBlocksOfNeighborChange(par1, par2, par3, par4);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void markBlockRangeForRenderUpdate(int par1, int par2, int par3, int par4, int par5, int par6) {
/*  2152 */     for (int var7 = 0; var7 < this.worldAccesses.size(); var7++)
/*       */     {
/*  2154 */       ((IWorldAccess)this.worldAccesses.get(var7)).markBlockRangeForRenderUpdate(par1, par2, par3, par4, par5, par6);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void notifyBlocksOfNeighborChange(int par1, int par2, int par3, int par4) {
/*  2163 */     notifyBlockOfNeighborChange(par1 - 1, par2, par3, par4);
/*  2164 */     notifyBlockOfNeighborChange(par1 + 1, par2, par3, par4);
/*  2165 */     notifyBlockOfNeighborChange(par1, par2 - 1, par3, par4);
/*  2166 */     notifyBlockOfNeighborChange(par1, par2 + 1, par3, par4);
/*  2167 */     notifyBlockOfNeighborChange(par1, par2, par3 - 1, par4);
/*  2168 */     notifyBlockOfNeighborChange(par1, par2, par3 + 1, par4);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void notifyBlocksOfNeighborChange(int par1, int par2, int par3, int par4, int par5) {
/*  2177 */     if (par5 != 4)
/*       */     {
/*  2179 */       notifyBlockOfNeighborChange(par1 - 1, par2, par3, par4);
/*       */     }
/*       */     
/*  2182 */     if (par5 != 5)
/*       */     {
/*  2184 */       notifyBlockOfNeighborChange(par1 + 1, par2, par3, par4);
/*       */     }
/*       */     
/*  2187 */     if (par5 != 0)
/*       */     {
/*  2189 */       notifyBlockOfNeighborChange(par1, par2 - 1, par3, par4);
/*       */     }
/*       */     
/*  2192 */     if (par5 != 1)
/*       */     {
/*  2194 */       notifyBlockOfNeighborChange(par1, par2 + 1, par3, par4);
/*       */     }
/*       */     
/*  2197 */     if (par5 != 2)
/*       */     {
/*  2199 */       notifyBlockOfNeighborChange(par1, par2, par3 - 1, par4);
/*       */     }
/*       */     
/*  2202 */     if (par5 != 3)
/*       */     {
/*  2204 */       notifyBlockOfNeighborChange(par1, par2, par3 + 1, par4);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final void notifyBlockOfNeighborChange(int par1, int par2, int par3, int par4) {
/*  2213 */     if (!this.isRemote) {
/*       */       
/*  2215 */       int var5 = getBlockId(par1, par2, par3);
/*  2216 */       Block var6 = Block.blocksList[var5];
/*       */       
/*  2218 */       if (var6 != null) {
/*       */         
/*       */         try {
/*       */           
/*  2222 */           var6.onNeighborBlockChange(this, par1, par2, par3, par4);
/*       */         }
/*  2224 */         catch (Throwable var13) {
/*       */           byte b;
/*  2226 */           CrashReport var8 = CrashReport.makeCrashReport(var13, "Exception while updating neighbours");
/*  2227 */           CrashReportCategory var9 = var8.makeCategory("Block being updated");
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/*  2232 */             b = getBlockMetadata(par1, par2, par3);
/*       */           }
/*  2234 */           catch (Throwable var12) {
/*       */             
/*  2236 */             b = -1;
/*       */           } 
/*       */           
/*  2239 */           var9.addCrashSectionCallable("Source block type", new CallableLvl1(this, par4));
/*  2240 */           CrashReportCategory.addBlockCrashInfo(var9, par1, par2, par3, var5, b);
/*  2241 */           throw new ReportedException(var8);
/*       */         } 
/*       */       }
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isBlockTickScheduledThisTick(int par1, int par2, int par3, int par4) {
/*  2252 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean canBlockSeeTheSky(int par1, int par2, int par3) {
/*  2261 */     return getChunkFromChunkCoords(par1 >> 4, par3 >> 4).canBlockSeeTheSky(par1 & 0xF, par2, par3 & 0xF);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getFullBlockLightValue(int par1, int par2, int par3) {
/*  2269 */     if (par2 < 0)
/*       */     {
/*  2271 */       return 0;
/*       */     }
/*       */ 
/*       */     
/*  2275 */     if (par2 >= 256)
/*       */     {
/*  2277 */       par2 = 255;
/*       */     }
/*       */     
/*  2280 */     return getChunkFromChunkCoords(par1 >> 4, par3 >> 4).getBlockLightValue(par1 & 0xF, par2, par3 & 0xF, 0);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getBlockLightValue(int par1, int par2, int par3) {
/*  2289 */     return getBlockLightValue_do(par1, par2, par3, true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getBlockLightValue_do(int par1, int par2, int par3, boolean par4) {
/*  2301 */     if (isWithinBlockDomain(par1, par3)) {
/*       */       int blv;
/*  2303 */       if (par4) {
/*       */         
/*  2305 */         int var5 = getBlockId(par1, par2, par3);
/*       */         
/*  2307 */         if (Block.useNeighborBrightness[var5]) {
/*       */           
/*  2309 */           Block block = Block.getBlock(var5);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2362 */           int metadata = getBlockMetadata(par1, par2, par3);
/*       */           
/*  2364 */           int brightness = 0;
/*       */           
/*  2366 */           for (int ordinal = 0; ordinal < 6; ordinal++) {
/*       */             
/*  2368 */             EnumDirection direction = EnumDirection.get(ordinal);
/*       */             
/*  2370 */             if (block.useNeighborBrightness(metadata, direction)) {
/*       */               
/*  2372 */               brightness = Math.max(brightness, getBlockLightValue_do(par1 + direction.dx, par2 + direction.dy, par3 + direction.dz, false));
/*       */               
/*  2374 */               if (brightness > 14) {
/*       */                 break;
/*       */               }
/*       */             } 
/*       */           } 
/*  2379 */           return brightness;
/*       */         } 
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2390 */       if (par2 < 0)
/*       */       {
/*  2392 */         return 0;
/*       */       }
/*       */ 
/*       */       
/*  2396 */       if (par2 >= 256)
/*       */       {
/*  2398 */         par2 = 255;
/*       */       }
/*       */       
/*  2401 */       Chunk var11 = getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
/*  2402 */       par1 &= 0xF;
/*  2403 */       par3 &= 0xF;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2410 */       if (this.ignore_rain_and_thunder_for_next_BLV) {
/*       */         
/*  2412 */         blv = var11.getBlockLightValue(par1, par2, par3, this.skylight_subtracted_ignoring_rain_and_thunder);
/*  2413 */         this.ignore_rain_and_thunder_for_next_BLV = false;
/*       */       }
/*       */       else {
/*       */         
/*  2417 */         blv = var11.getBlockLightValue(par1, par2, par3, this.skylightSubtracted);
/*       */       } 
/*       */       
/*  2420 */       return blv;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2427 */     return 15;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getHeightValue(int par1, int par2) {
/*  2436 */     if (isWithinBlockDomain(par1, par2)) {
/*       */       
/*  2438 */       if (!chunkExists(par1 >> 4, par2 >> 4))
/*       */       {
/*  2440 */         return 0;
/*       */       }
/*       */ 
/*       */       
/*  2444 */       Chunk var3 = getChunkFromChunkCoords(par1 >> 4, par2 >> 4);
/*  2445 */       return var3.getHeightValue(par1 & 0xF, par2 & 0xF);
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  2450 */     return 0;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getSkyBlockTypeBrightness(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4) {
/*  2460 */     if (this.provider.hasNoSky && par1EnumSkyBlock == EnumSkyBlock.Sky)
/*       */     {
/*  2462 */       return 0;
/*       */     }
/*       */ 
/*       */     
/*  2466 */     if (par3 < 0)
/*       */     {
/*  2468 */       par3 = 0;
/*       */     }
/*       */     
/*  2471 */     if (par3 >= 256)
/*       */     {
/*  2473 */       return par1EnumSkyBlock.defaultLightValue;
/*       */     }
/*       */ 
/*       */     
/*  2477 */     if (isWithinBlockDomain(par2, par4)) {
/*       */       
/*  2479 */       int var5 = par2 >> 4;
/*  2480 */       int var6 = par4 >> 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2533 */       if (!chunkExists(var5, var6)) {
/*  2534 */         return par1EnumSkyBlock.defaultLightValue;
/*       */       }
/*  2536 */       int block_id = getBlockId(par2, par3, par4);
/*       */       
/*  2538 */       if (Block.useNeighborBrightness[block_id]) {
/*       */         
/*  2540 */         Block block = Block.getBlock(block_id);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2603 */         int metadata = getBlockMetadata(par2, par3, par4);
/*       */         
/*  2605 */         int brightness = 0;
/*       */         
/*  2607 */         for (int ordinal = 0; ordinal < 6; ordinal++) {
/*       */           
/*  2609 */           EnumDirection direction = EnumDirection.get(ordinal);
/*       */           
/*  2611 */           int x = par2 + direction.dx;
/*  2612 */           int z = par4 + direction.dz;
/*       */           
/*  2614 */           if (block.useNeighborBrightness(metadata, direction) && chunkExistsAndIsNotEmptyFromBlockCoords(x, z)) {
/*       */             
/*  2616 */             brightness = Math.max(brightness, getSavedLightValue(par1EnumSkyBlock, x, par3 + direction.dy, z));
/*       */             
/*  2618 */             if (brightness > 14) {
/*       */               break;
/*       */             }
/*       */           } 
/*       */         } 
/*  2623 */         return brightness;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2633 */       Chunk chunk = getChunkFromChunkCoords(var5, var6);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2638 */       return chunk.getSavedLightValue(par1EnumSkyBlock, par2 & 0xF, par3, par4 & 0xF);
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2644 */     return par1EnumSkyBlock.defaultLightValue;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getSavedLightValue(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4) {
/*  2665 */     if (par3 < 0) {
/*  2666 */       par3 = 0;
/*  2667 */     } else if (par3 > 255) {
/*  2668 */       par3 = 255;
/*       */     } 
/*       */ 
/*       */     
/*  2672 */     if (isWithinBlockDomain(par2, par4)) {
/*       */       
/*  2674 */       int var5 = par2 >> 4;
/*  2675 */       int var6 = par4 >> 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2691 */       if (!chunkExists(var5, var6))
/*       */       {
/*  2693 */         return par1EnumSkyBlock.defaultLightValue;
/*       */       }
/*       */ 
/*       */       
/*  2697 */       Chunk var7 = getChunkFromChunkCoords(var5, var6);
/*  2698 */       return var7.getSavedLightValue(par1EnumSkyBlock, par2 & 0xF, par3, par4 & 0xF);
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  2703 */     return par1EnumSkyBlock.defaultLightValue;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getSavedSkylightValue(int par2, int par3, int par4) {
/*  2710 */     if (!isWithinBlockDomain(par2, par4)) {
/*  2711 */       return 15;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2721 */     Chunk chunk = getChunkIfItExists(par2 >> 4, par4 >> 4);
/*       */     
/*  2723 */     return (chunk == null) ? 15 : chunk.getSavedSkylightValue(par2 & 0xF, (par3 < 0) ? 0 : ((par3 > 255) ? 255 : par3), par4 & 0xF);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getSavedBlocklightValue(int par2, int par3, int par4) {
/*  2729 */     if (!isWithinBlockDomain(par2, par4)) {
/*  2730 */       return 0;
/*       */     }
/*  2732 */     Chunk chunk = getChunkIfItExists(par2 >> 4, par4 >> 4);
/*  2733 */     return (chunk == null) ? 0 : chunk.getSavedBlocklightValue(par2 & 0xF, (par3 < 0) ? 0 : ((par3 > 255) ? 255 : par3), par4 & 0xF);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getSavedLightValueMITE(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4, Chunk chunk) {
/*  2742 */     if (par3 < 0) {
/*  2743 */       par3 = 0;
/*  2744 */     } else if (par3 > 255) {
/*  2745 */       par3 = 255;
/*       */     } 
/*  2747 */     if (chunk.isWithinBlockDomain()) {
/*  2748 */       return chunk.getSavedLightValueForNonEmptyChunk(par1EnumSkyBlock, par2 & 0xF, par3, par4 & 0xF);
/*       */     }
/*  2750 */     return par1EnumSkyBlock.defaultLightValue;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getSavedSkylightValueMITE(int x, int y, int z, Chunk chunk) {
/*  2759 */     return chunk.isWithinBlockDomain() ? chunk.getSavedSkylightValueForNonEmptyChunk(x & 0xF, (y < 0) ? 0 : ((y > 255) ? 255 : y), z & 0xF) : 15;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getSavedBlocklightValueMITE(int x, int y, int z, Chunk chunk) {
/*  2768 */     return chunk.isWithinBlockDomain() ? chunk.getSavedBlocklightValueForNonEmptyChunk(x & 0xF, (y < 0) ? 0 : ((y > 255) ? 255 : y), z & 0xF) : 0;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setLightValue(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4, int par5) {
/*  2808 */     if (isWithinBlockDomain(par2, par4))
/*       */     {
/*  2810 */       if (par3 >= 0)
/*       */       {
/*  2812 */         if (par3 < 256)
/*       */         {
/*  2814 */           if (chunkExists(par2 >> 4, par4 >> 4)) {
/*       */             
/*  2816 */             Chunk var6 = getChunkFromChunkCoords(par2 >> 4, par4 >> 4);
/*  2817 */             var6.setLightValue(par1EnumSkyBlock, par2 & 0xF, par3, par4 & 0xF, par5);
/*       */             
/*  2819 */             for (int var7 = 0; var7 < this.worldAccesses.size(); var7++)
/*       */             {
/*  2821 */               ((IWorldAccess)this.worldAccesses.get(var7)).markBlockForRenderUpdate(par2, par3, par4);
/*       */             }
/*       */           } 
/*       */         }
/*       */       }
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   public void setSkylightValue(int par2, int par3, int par4, int par5) {
/*  2831 */     if (isWithinBlockBounds(par2, par3, par4)) {
/*       */       
/*  2833 */       Chunk var6 = getChunkIfItExists(par2 >> 4, par4 >> 4);
/*       */       
/*  2835 */       if (var6 == null) {
/*       */         return;
/*       */       }
/*  2838 */       var6.setSkylightValue(par2 & 0xF, par3, par4 & 0xF, par5);
/*       */       
/*  2840 */       for (int var7 = 0; var7 < this.worldAccesses.size(); var7++) {
/*  2841 */         ((IWorldAccess)this.worldAccesses.get(var7)).markBlockForRenderUpdate(par2, par3, par4);
/*       */       }
/*       */     } 
/*       */   }
/*       */   
/*       */   public void setBlocklightValue(int par2, int par3, int par4, int par5) {
/*  2847 */     if (isWithinBlockBounds(par2, par3, par4)) {
/*       */       
/*  2849 */       Chunk var6 = getChunkIfItExists(par2 >> 4, par4 >> 4);
/*       */       
/*  2851 */       if (var6 == null) {
/*       */         return;
/*       */       }
/*  2854 */       var6.setBlocklightValue(par2 & 0xF, par3, par4 & 0xF, par5);
/*       */       
/*  2856 */       for (int var7 = 0; var7 < this.worldAccesses.size(); var7++) {
/*  2857 */         ((IWorldAccess)this.worldAccesses.get(var7)).markBlockForRenderUpdate(par2, par3, par4);
/*       */       }
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public void setLightValueMITE(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4, int par5, Chunk chunk) {
/*  2865 */     if (isWithinBlockDomain(par2, par4))
/*       */     {
/*  2867 */       if (par3 >= 0)
/*       */       {
/*  2869 */         if (par3 < 256) {
/*       */           
/*  2871 */           chunk.setLightValue(par1EnumSkyBlock, par2 & 0xF, par3, par4 & 0xF, par5);
/*       */           
/*  2873 */           for (int var7 = 0; var7 < this.worldAccesses.size(); var7++)
/*       */           {
/*  2875 */             ((IWorldAccess)this.worldAccesses.get(var7)).markBlockForRenderUpdate(par2, par3, par4);
/*       */           }
/*       */         } 
/*       */       }
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   public void setSkylightValueMITE(int par2, int par3, int par4, int par5, Chunk chunk) {
/*  2884 */     if (isWithinBlockBounds(par2, par3, par4)) {
/*       */       
/*  2886 */       chunk.setSkylightValue(par2 & 0xF, par3, par4 & 0xF, par5);
/*       */       
/*  2888 */       for (int var7 = 0; var7 < this.worldAccesses.size(); var7++)
/*       */       {
/*  2890 */         ((IWorldAccess)this.worldAccesses.get(var7)).markBlockForRenderUpdate(par2, par3, par4);
/*       */       }
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   public void setBlocklightValueMITE(int par2, int par3, int par4, int par5, Chunk chunk) {
/*  2897 */     if (isWithinBlockBounds(par2, par3, par4)) {
/*       */       
/*  2899 */       chunk.setBlocklightValue(par2 & 0xF, par3, par4 & 0xF, par5);
/*       */       
/*  2901 */       for (int var7 = 0; var7 < this.worldAccesses.size(); var7++)
/*       */       {
/*  2903 */         ((IWorldAccess)this.worldAccesses.get(var7)).markBlockForRenderUpdate(par2, par3, par4);
/*       */       }
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void markBlockForRenderUpdate(int par1, int par2, int par3) {
/*  2926 */     for (int var4 = 0; var4 < this.worldAccesses.size(); var4++)
/*       */     {
/*  2928 */       ((IWorldAccess)this.worldAccesses.get(var4)).markBlockForRenderUpdate(par1, par2, par3);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getLightBrightnessForSkyBlocks(int par1, int par2, int par3, int par4) {
/*  2937 */     int var5 = getSkyBlockTypeBrightness(EnumSkyBlock.Sky, par1, par2, par3);
/*  2938 */     int var6 = getSkyBlockTypeBrightness(EnumSkyBlock.Block, par1, par2, par3);
/*       */     
/*  2940 */     if (var6 < par4)
/*       */     {
/*  2942 */       var6 = par4;
/*       */     }
/*       */     
/*  2945 */     return var5 << 20 | var6 << 4;
/*       */   }
/*       */ 
/*       */   
/*       */   public float getBrightness(int par1, int par2, int par3, int par4) {
/*  2950 */     int var5 = getBlockLightValue(par1, par2, par3);
/*       */     
/*  2952 */     if (var5 < par4)
/*       */     {
/*  2954 */       var5 = par4;
/*       */     }
/*       */     
/*  2957 */     return this.provider.lightBrightnessTable[var5];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getLightBrightness(int par1, int par2, int par3) {
/*  2966 */     return this.provider.lightBrightnessTable[getBlockLightValue(par1, par2, par3)];
/*       */   }
/*       */ 
/*       */   
/*       */   public static final int getUnadjustedTimeOfDay(long unadjusted_tick) {
/*  2971 */     return (int)(unadjusted_tick % 24000L);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getTimeOfDay() {
/*  2977 */     return this.worldInfo.getWorldTimeOfDay(getDimensionId());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int getTimeOfSunrise() {
/*  2984 */     return 5000;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int getTimeOfSunset() {
/*  2991 */     return 19000;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int getHourOfLatestReconnection() {
/*  3003 */     return getTimeOfSleeping() / 1000 - 1;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int getTimeOfSleeping() {
/*  3009 */     return 21000;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getAdjustedTimeOfDay() {
/*  3019 */     return getAdjustedTimeOfDay(getTimeOfDay());
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public static int getAdjustedTimeOfDay(int unadjusted_time_of_day) {
/*  3025 */     return (unadjusted_time_of_day + 6000) % 24000;
/*       */   }
/*       */ 
/*       */   
/*       */   public int getTimeTillSunrise() {
/*  3030 */     int time_of_day = getAdjustedTimeOfDay();
/*       */     
/*  3032 */     if (time_of_day < getTimeOfSunrise()) {
/*  3033 */       return getTimeOfSunrise() - time_of_day;
/*       */     }
/*  3035 */     return getTimeOfSunrise() - time_of_day + 24000;
/*       */   }
/*       */ 
/*       */   
/*       */   public static boolean isDaytime(long unadjusted_tick) {
/*  3040 */     long time_of_day = getAdjustedTimeOfDay(getUnadjustedTimeOfDay(unadjusted_tick));
/*  3041 */     return (time_of_day > getTimeOfSunrise() && time_of_day < getTimeOfSunset());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isDaytime() {
/*  3051 */     long time_of_day = getAdjustedTimeOfDay();
/*  3052 */     return (time_of_day > getTimeOfSunrise() && time_of_day < getTimeOfSunset());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final RaycastCollision tryRaycastVsBlocks(Raycast raycast) {
/*  3069 */     boolean hit_liquids = !raycast.alwaysIgnoreLiquids();
/*  3070 */     boolean par4 = raycast.getOriginator() instanceof EntityArrow;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3075 */     raycast.clearImpedance();
/*       */     
/*  3077 */     Vec3 par1Vec3 = raycast.getOrigin().copy();
/*  3078 */     Vec3 par2Vec3 = raycast.getLimit().copy();
/*       */ 
/*       */ 
/*       */     
/*  3082 */     if (!Double.isNaN(par1Vec3.xCoord) && !Double.isNaN(par1Vec3.yCoord) && !Double.isNaN(par1Vec3.zCoord))
/*       */     {
/*  3084 */       if (!Double.isNaN(par2Vec3.xCoord) && !Double.isNaN(par2Vec3.yCoord) && !Double.isNaN(par2Vec3.zCoord)) {
/*       */         
/*  3086 */         int var5 = MathHelper.floor_double(par2Vec3.xCoord);
/*  3087 */         int var6 = MathHelper.floor_double(par2Vec3.yCoord);
/*  3088 */         int var7 = MathHelper.floor_double(par2Vec3.zCoord);
/*  3089 */         int var8 = MathHelper.floor_double(par1Vec3.xCoord);
/*  3090 */         int var9 = MathHelper.floor_double(par1Vec3.yCoord);
/*  3091 */         int var10 = MathHelper.floor_double(par1Vec3.zCoord);
/*  3092 */         int var11 = getBlockId(var8, var9, var10);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3111 */         if (var11 > 0) {
/*       */           
/*  3113 */           Block var13 = Block.blocksList[var11];
/*       */ 
/*       */ 
/*       */           
/*  3117 */           if (var13.canCollideCheck(getBlockMetadata(var8, var9, var10), hit_liquids)) {
/*       */             
/*  3119 */             RaycastCollision var14 = var13.tryRaycastVsBlock(raycast, var8, var9, var10, par1Vec3, par2Vec3);
/*       */             
/*  3121 */             if (var14 != null) {
/*  3122 */               return var14;
/*       */             }
/*       */           } 
/*       */         } 
/*       */         
/*  3127 */         var11 = 200;
/*       */         
/*  3129 */         while (var11-- >= 0) {
/*       */           byte var42;
/*  3131 */           if (Double.isNaN(par1Vec3.xCoord) || Double.isNaN(par1Vec3.yCoord) || Double.isNaN(par1Vec3.zCoord))
/*       */           {
/*  3133 */             return null;
/*       */           }
/*       */           
/*  3136 */           if (var8 == var5 && var9 == var6 && var10 == var7)
/*       */           {
/*  3138 */             return null;
/*       */           }
/*       */           
/*  3141 */           boolean var39 = true;
/*  3142 */           boolean var40 = true;
/*  3143 */           boolean var41 = true;
/*  3144 */           double var15 = 999.0D;
/*  3145 */           double var17 = 999.0D;
/*  3146 */           double var19 = 999.0D;
/*       */           
/*  3148 */           if (var5 > var8) {
/*       */             
/*  3150 */             var15 = var8 + 1.0D;
/*       */           }
/*  3152 */           else if (var5 < var8) {
/*       */             
/*  3154 */             var15 = var8 + 0.0D;
/*       */           }
/*       */           else {
/*       */             
/*  3158 */             var39 = false;
/*       */           } 
/*       */           
/*  3161 */           if (var6 > var9) {
/*       */             
/*  3163 */             var17 = var9 + 1.0D;
/*       */           }
/*  3165 */           else if (var6 < var9) {
/*       */             
/*  3167 */             var17 = var9 + 0.0D;
/*       */           }
/*       */           else {
/*       */             
/*  3171 */             var40 = false;
/*       */           } 
/*       */           
/*  3174 */           if (var7 > var10) {
/*       */             
/*  3176 */             var19 = var10 + 1.0D;
/*       */           }
/*  3178 */           else if (var7 < var10) {
/*       */             
/*  3180 */             var19 = var10 + 0.0D;
/*       */           }
/*       */           else {
/*       */             
/*  3184 */             var41 = false;
/*       */           } 
/*       */           
/*  3187 */           double var21 = 999.0D;
/*  3188 */           double var23 = 999.0D;
/*  3189 */           double var25 = 999.0D;
/*  3190 */           double var27 = par2Vec3.xCoord - par1Vec3.xCoord;
/*  3191 */           double var29 = par2Vec3.yCoord - par1Vec3.yCoord;
/*  3192 */           double var31 = par2Vec3.zCoord - par1Vec3.zCoord;
/*       */           
/*  3194 */           if (var39)
/*       */           {
/*  3196 */             var21 = (var15 - par1Vec3.xCoord) / var27;
/*       */           }
/*       */           
/*  3199 */           if (var40)
/*       */           {
/*  3201 */             var23 = (var17 - par1Vec3.yCoord) / var29;
/*       */           }
/*       */           
/*  3204 */           if (var41)
/*       */           {
/*  3206 */             var25 = (var19 - par1Vec3.zCoord) / var31;
/*       */           }
/*       */           
/*  3209 */           boolean var33 = false;
/*       */ 
/*       */           
/*  3212 */           if (var21 < var23 && var21 < var25) {
/*       */             
/*  3214 */             if (var5 > var8) {
/*       */               
/*  3216 */               var42 = 4;
/*       */             }
/*       */             else {
/*       */               
/*  3220 */               var42 = 5;
/*       */             } 
/*       */             
/*  3223 */             par1Vec3.xCoord = var15;
/*  3224 */             par1Vec3.yCoord += var29 * var21;
/*  3225 */             par1Vec3.zCoord += var31 * var21;
/*       */           }
/*  3227 */           else if (var23 < var25) {
/*       */             
/*  3229 */             if (var6 > var9) {
/*       */               
/*  3231 */               var42 = 0;
/*       */             }
/*       */             else {
/*       */               
/*  3235 */               var42 = 1;
/*       */             } 
/*       */             
/*  3238 */             par1Vec3.xCoord += var27 * var23;
/*  3239 */             par1Vec3.yCoord = var17;
/*  3240 */             par1Vec3.zCoord += var31 * var23;
/*       */           }
/*       */           else {
/*       */             
/*  3244 */             if (var7 > var10) {
/*       */               
/*  3246 */               var42 = 2;
/*       */             }
/*       */             else {
/*       */               
/*  3250 */               var42 = 3;
/*       */             } 
/*       */             
/*  3253 */             par1Vec3.xCoord += var27 * var25;
/*  3254 */             par1Vec3.yCoord += var29 * var25;
/*  3255 */             par1Vec3.zCoord = var19;
/*       */           } 
/*       */           
/*  3258 */           Vec3 var34 = getWorldVec3Pool().getVecFromPool(par1Vec3.xCoord, par1Vec3.yCoord, par1Vec3.zCoord);
/*  3259 */           var8 = (int)(var34.xCoord = MathHelper.floor_double(par1Vec3.xCoord));
/*       */           
/*  3261 */           if (var42 == 5) {
/*       */             
/*  3263 */             var8--;
/*  3264 */             var34.xCoord++;
/*       */           } 
/*       */           
/*  3267 */           var9 = (int)(var34.yCoord = MathHelper.floor_double(par1Vec3.yCoord));
/*       */           
/*  3269 */           if (var42 == 1) {
/*       */             
/*  3271 */             var9--;
/*  3272 */             var34.yCoord++;
/*       */           } 
/*       */           
/*  3275 */           var10 = (int)(var34.zCoord = MathHelper.floor_double(par1Vec3.zCoord));
/*       */           
/*  3277 */           if (var42 == 3) {
/*       */             
/*  3279 */             var10--;
/*  3280 */             var34.zCoord++;
/*       */           } 
/*       */           
/*  3283 */           int var35 = getBlockId(var8, var9, var10);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  3301 */           if (var35 > 0) {
/*       */             
/*  3303 */             Block var37 = Block.blocksList[var35];
/*       */ 
/*       */ 
/*       */             
/*  3307 */             if (var37.canCollideCheck(getBlockMetadata(var8, var9, var10), hit_liquids)) {
/*       */               
/*  3309 */               RaycastCollision var38 = var37.tryRaycastVsBlock(raycast, var8, var9, var10, par1Vec3, par2Vec3);
/*       */               
/*  3311 */               if (var38 != null) {
/*  3312 */                 return var38;
/*       */               }
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3330 */     return null;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public void playSoundAtBlock(int x, int y, int z, String name, float volume, float pitch) {
/*  3336 */     if (this.isRemote) {
/*       */       
/*  3338 */       Minecraft.setErrorMessage("playSoundAtBlock: only meant to be called on server");
/*       */       
/*       */       return;
/*       */     } 
/*  3342 */     if (name == null) {
/*       */       return;
/*       */     }
/*  3345 */     for (int i = 0; i < this.worldAccesses.size(); i++) {
/*  3346 */       ((IWorldAccess)this.worldAccesses.get(i)).playSound(name, (x + 0.5F), (y + 0.5F), (z + 0.5F), volume, pitch);
/*       */     }
/*       */   }
/*       */   
/*       */   public void playSoundAtBlock(int x, int y, int z, String name, float volume) {
/*  3351 */     playSoundAtBlock(x, y, z, name, volume, 0.9F + (this.rand.nextFloat() + this.rand.nextFloat()) / 10.0F);
/*       */   }
/*       */ 
/*       */   
/*       */   public void playSoundAtEntity(Entity par1Entity, String par2Str) {
/*  3356 */     playSoundAtEntity(par1Entity, par2Str, 0.9F + (this.rand.nextFloat() + this.rand.nextFloat()) / 10.0F, 0.9F + (this.rand.nextFloat() + this.rand.nextFloat()) / 10.0F);
/*       */   }
/*       */ 
/*       */   
/*       */   public void playSoundAtEntity(Entity par1Entity, String par2Str, float par3) {
/*  3361 */     playSoundAtEntity(par1Entity, par2Str, par3, 0.9F + (this.rand.nextFloat() + this.rand.nextFloat()) / 10.0F);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void playSoundAtEntity(Entity par1Entity, String par2Str, float par3, float par4) {
/*  3372 */     if (par1Entity != null && par2Str != null) {
/*       */       
/*  3374 */       if (par1Entity.isZevimrgvInTournament()) {
/*       */         return;
/*       */       }
/*  3377 */       for (int var5 = 0; var5 < this.worldAccesses.size(); var5++)
/*       */       {
/*  3379 */         ((IWorldAccess)this.worldAccesses.get(var5)).playSound(par2Str, par1Entity.posX, par1Entity.posY - par1Entity.yOffset, par1Entity.posZ, par3, par4);
/*       */       }
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   public void playLongDistanceSoundAtEntity(Entity par1Entity, String par2Str, float par3, float par4) {
/*  3386 */     if (par1Entity != null && par2Str != null)
/*       */     {
/*  3388 */       for (int var5 = 0; var5 < this.worldAccesses.size(); var5++)
/*       */       {
/*  3390 */         ((IWorldAccess)this.worldAccesses.get(var5)).playLongDistanceSound(par2Str, par1Entity.posX, par1Entity.posY - par1Entity.yOffset, par1Entity.posZ, par3, par4);
/*       */       }
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void playSoundToNearExcept(EntityPlayer par1EntityPlayer, String par2Str, float par3, float par4) {
/*  3400 */     if (par1EntityPlayer != null && par2Str != null)
/*       */     {
/*  3402 */       for (int var5 = 0; var5 < this.worldAccesses.size(); var5++)
/*       */       {
/*  3404 */         ((IWorldAccess)this.worldAccesses.get(var5)).playSoundToNearExcept(par1EntityPlayer, par2Str, par1EntityPlayer.posX, par1EntityPlayer.posY - par1EntityPlayer.yOffset, par1EntityPlayer.posZ, par3, par4);
/*       */       }
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   public void playSoundEffect(double par1, double par3, double par5, String par7Str) {
/*  3411 */     playSoundEffect(par1, par3, par5, par7Str, 0.9F + (this.rand.nextFloat() + this.rand.nextFloat()) / 10.0F, 0.9F + (this.rand.nextFloat() + this.rand.nextFloat()) / 10.0F);
/*       */   }
/*       */ 
/*       */   
/*       */   public void playSoundEffect(double par1, double par3, double par5, String par7Str, float par8) {
/*  3416 */     playSoundEffect(par1, par3, par5, par7Str, par8, 0.9F + (this.rand.nextFloat() + this.rand.nextFloat()) / 10.0F);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void playSoundEffect(double par1, double par3, double par5, String par7Str, float par8, float par9) {
/*  3426 */     if (par7Str != null)
/*       */     {
/*  3428 */       for (int var10 = 0; var10 < this.worldAccesses.size(); var10++)
/*       */       {
/*  3430 */         ((IWorldAccess)this.worldAccesses.get(var10)).playSound(par7Str, par1, par3, par5, par8, par9);
/*       */       }
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void playSound(double par1, double par3, double par5, String par7Str, float par8, float par9, boolean par10) {}
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void playRecord(String par1Str, int par2, int par3, int par4) {
/*  3445 */     for (int var5 = 0; var5 < this.worldAccesses.size(); var5++)
/*       */     {
/*  3447 */       ((IWorldAccess)this.worldAccesses.get(var5)).playRecord(par1Str, par2, par3, par4);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spawnParticle(EnumParticle enum_particle, double par2, double par4, double par6, double par8, double par10, double par12) {
/*  3459 */     if (!isWithinEntityDomain(par2, par6)) {
/*       */       return;
/*       */     }
/*  3462 */     for (int var14 = 0; var14 < this.worldAccesses.size(); var14++)
/*       */     {
/*       */       
/*  3465 */       ((IWorldAccess)this.worldAccesses.get(var14)).spawnParticle(enum_particle, par2, par4, par6, par8, par10, par12);
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   public void spawnParticleEx(EnumParticle enum_particle, int index, int data, double par2, double par4, double par6, double par8, double par10, double par12) {
/*  3471 */     if (!isWithinEntityDomain(par2, par6)) {
/*       */       return;
/*       */     }
/*  3474 */     for (int var14 = 0; var14 < this.worldAccesses.size(); var14++) {
/*  3475 */       ((IWorldAccess)this.worldAccesses.get(var14)).spawnParticleEx(enum_particle, index, data, par2, par4, par6, par8, par10, par12);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean addWeatherEffect(Entity par1Entity) {
/*  3483 */     this.weatherEffects.add(par1Entity);
/*  3484 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean spawnEntityInWorld(Entity par1Entity) {
/*  3492 */     int var2 = MathHelper.floor_double(par1Entity.posX / 16.0D);
/*  3493 */     int var3 = MathHelper.floor_double(par1Entity.posZ / 16.0D);
/*  3494 */     boolean var4 = par1Entity.forceSpawn;
/*       */     
/*  3496 */     if (par1Entity instanceof EntityPlayer)
/*       */     {
/*  3498 */       var4 = true;
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  3503 */     if (!var4 && (!chunkExists(var2, var3) || !isWithinEntityDomain(par1Entity.posX, par1Entity.posZ)))
/*       */     {
/*  3505 */       return false;
/*       */     }
/*       */ 
/*       */     
/*  3509 */     if (par1Entity instanceof EntityPlayer) {
/*       */       
/*  3511 */       EntityPlayer var5 = (EntityPlayer)par1Entity;
/*  3512 */       this.playerEntities.add(var5);
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  3517 */     getChunkFromChunkCoords(var2, var3).addEntity(par1Entity);
/*  3518 */     this.loadedEntityList.add(par1Entity);
/*  3519 */     onEntityAdded(par1Entity);
/*       */     
/*  3521 */     par1Entity.spawn_x = MathHelper.floor_double(par1Entity.posX);
/*  3522 */     par1Entity.spawn_y = MathHelper.floor_double(par1Entity.posY);
/*  3523 */     par1Entity.spawn_z = MathHelper.floor_double(par1Entity.posZ);
/*       */     
/*  3525 */     par1Entity.onSpawned();
/*       */     
/*  3527 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   protected void onEntityAdded(Entity par1Entity) {
/*  3533 */     for (int var2 = 0; var2 < this.worldAccesses.size(); var2++)
/*       */     {
/*  3535 */       ((IWorldAccess)this.worldAccesses.get(var2)).onEntityCreate(par1Entity);
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   protected void onEntityRemoved(Entity par1Entity) {
/*  3541 */     for (int var2 = 0; var2 < this.worldAccesses.size(); var2++)
/*       */     {
/*  3543 */       ((IWorldAccess)this.worldAccesses.get(var2)).onEntityDestroy(par1Entity);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void removeEntity(Entity par1Entity) {
/*  3552 */     if (par1Entity.riddenByEntity != null)
/*       */     {
/*  3554 */       par1Entity.riddenByEntity.mountEntity((Entity)null);
/*       */     }
/*       */     
/*  3557 */     if (par1Entity.ridingEntity != null)
/*       */     {
/*  3559 */       par1Entity.mountEntity((Entity)null);
/*       */     }
/*       */     
/*  3562 */     par1Entity.setDead();
/*       */     
/*  3564 */     if (par1Entity instanceof EntityPlayer)
/*       */     {
/*  3566 */       this.playerEntities.remove(par1Entity);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void removePlayerEntityDangerously(Entity par1Entity) {
/*  3577 */     par1Entity.setDead();
/*       */     
/*  3579 */     if (par1Entity instanceof EntityPlayer)
/*       */     {
/*  3581 */       this.playerEntities.remove(par1Entity);
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3594 */     if (par1Entity.isAddedToAChunk()) {
/*       */       
/*  3596 */       Chunk chunk = par1Entity.getChunkAddedTo();
/*       */       
/*  3598 */       if (chunkExists(chunk.xPosition, chunk.zPosition)) {
/*  3599 */         par1Entity.removeFromChunk();
/*       */       }
/*       */     } 
/*  3602 */     this.loadedEntityList.remove(par1Entity);
/*  3603 */     onEntityRemoved(par1Entity);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void addWorldAccess(IWorldAccess par1IWorldAccess) {
/*  3611 */     this.worldAccesses.add(par1IWorldAccess);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void removeWorldAccess(IWorldAccess par1IWorldAccess) {
/*  3619 */     this.worldAccesses.remove(par1IWorldAccess);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final List getCollidingBoundingBoxes(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB) {
/*  3664 */     getCollidingBlockBounds(par2AxisAlignedBB, par1Entity);
/*       */     
/*  3666 */     if (par1Entity instanceof EntityPlayer && (par1Entity.getAsPlayer()).tentative_bounding_boxes.size() > 0) {
/*       */       
/*  3668 */       Iterator<TentativeBoundingBox> i = (par1Entity.getAsPlayer()).tentative_bounding_boxes.iterator();
/*       */       
/*  3670 */       while (i.hasNext()) {
/*       */         
/*  3672 */         TentativeBoundingBox tbb = i.next();
/*       */         
/*  3674 */         if (par2AxisAlignedBB.intersectsWith(tbb.bb)) {
/*  3675 */           this.collidingBoundingBoxes.add(tbb.bb);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3684 */     if (par1Entity.isEntityPlayer())
/*       */     {
/*  3686 */       if (par1Entity.getAsPlayer().isGhost() || par1Entity.isZevimrgvInTournament()) {
/*  3687 */         return this.collidingBoundingBoxes;
/*       */       }
/*       */     }
/*  3690 */     double var14 = 0.25D;
/*  3691 */     List<Entity> var16 = getEntitiesWithinAABBExcludingEntity(par1Entity, par2AxisAlignedBB.expand(var14, var14, var14));
/*       */     
/*  3693 */     for (int var15 = 0; var15 < var16.size(); var15++) {
/*       */       
/*  3695 */       Entity entity = var16.get(var15);
/*       */       
/*  3697 */       if (entity instanceof EntityPlayer) {
/*       */         
/*  3699 */         if (((EntityPlayer)entity).isGhost()) {
/*       */           continue;
/*       */         }
/*  3702 */         if (entity.isZevimrgvInTournament()) {
/*       */           continue;
/*       */         }
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3711 */       AxisAlignedBB var13 = entity.getBoundingBox();
/*       */ 
/*       */ 
/*       */       
/*  3715 */       if (var13 != null && var13.intersectsWith(par2AxisAlignedBB))
/*       */       {
/*  3717 */         this.collidingBoundingBoxes.add(var13);
/*       */       }
/*       */       
/*  3720 */       var13 = par1Entity.getCollisionBox(var16.get(var15));
/*       */       
/*  3722 */       if (var13 != null && var13.intersectsWith(par2AxisAlignedBB))
/*       */       {
/*  3724 */         this.collidingBoundingBoxes.add(var13);
/*       */       }
/*       */       continue;
/*       */     } 
/*  3728 */     return this.collidingBoundingBoxes;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final List getCollidingBlockBounds(AxisAlignedBB bounding_box, Entity entity) {
/*  3775 */     this.collidingBoundingBoxes.clear();
/*       */     
/*  3777 */     int min_x = bounding_box.getBlockCoordForMinX();
/*  3778 */     int max_x = bounding_box.getBlockCoordForMaxX();
/*  3779 */     int min_y = bounding_box.getBlockCoordForMinY();
/*  3780 */     int max_y = bounding_box.getBlockCoordForMaxY();
/*  3781 */     int min_z = bounding_box.getBlockCoordForMinZ();
/*  3782 */     int max_z = bounding_box.getBlockCoordForMaxZ();
/*       */     
/*  3784 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  3786 */       for (int z = min_z; z <= max_z; z++) {
/*       */         
/*  3788 */         if (blockExists(x, 64, z))
/*       */         {
/*  3790 */           for (int y = min_y - 1; y <= max_y; y++) {
/*       */             
/*  3792 */             Block block = getBlock(x, y, z);
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  3797 */             if (block != null) {
/*  3798 */               block.addCollidingBoundsToList(this, x, y, z, bounding_box, this.collidingBoundingBoxes, entity);
/*       */             }
/*       */           } 
/*       */         }
/*       */       } 
/*       */     } 
/*  3804 */     return this.collidingBoundingBoxes;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final BlockInfo getHighestCollidingBlockClosestTo(AxisAlignedBB bounding_box, Entity entity, double pos_x, double pos_z) {
/*  3810 */     BlockInfo info = null;
/*       */     
/*  3812 */     double highest_max_y = 0.0D;
/*  3813 */     double shortest_distance_sq = 0.0D;
/*       */     
/*  3815 */     int min_x = bounding_box.getBlockCoordForMinX();
/*  3816 */     int max_x = bounding_box.getBlockCoordForMaxX();
/*  3817 */     int min_y = bounding_box.getBlockCoordForMinY();
/*  3818 */     int max_y = bounding_box.getBlockCoordForMaxY();
/*  3819 */     int min_z = bounding_box.getBlockCoordForMinZ();
/*  3820 */     int max_z = bounding_box.getBlockCoordForMaxZ();
/*       */     
/*  3822 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  3824 */       for (int z = min_z; z <= max_z; z++) {
/*       */         
/*  3826 */         if (blockExists(x, 64, z))
/*       */         {
/*  3828 */           for (int y = min_y - 1; y <= max_y; y++) {
/*       */             
/*  3830 */             Block block = getBlock(x, y, z);
/*       */             
/*  3832 */             if (block != null) {
/*       */               
/*  3834 */               this.collidingBoundingBoxes.clear();
/*  3835 */               block.addCollidingBoundsToList(this, x, y, z, bounding_box, this.collidingBoundingBoxes, entity);
/*       */               
/*  3837 */               Iterator<AxisAlignedBB> i = this.collidingBoundingBoxes.iterator();
/*       */               
/*  3839 */               while (i.hasNext()) {
/*       */                 
/*  3841 */                 AxisAlignedBB bb = i.next();
/*       */                 
/*  3843 */                 if (info == null || bb.maxY > highest_max_y) {
/*       */                   
/*  3845 */                   info = new BlockInfo(block, x, y, z);
/*  3846 */                   highest_max_y = bb.maxY;
/*       */                   
/*  3848 */                   double dx = x + 0.5D - pos_x;
/*  3849 */                   double dz = z + 0.5D - pos_z;
/*       */                   
/*  3851 */                   shortest_distance_sq = dx * dx + dz * dz; continue;
/*       */                 } 
/*  3853 */                 if (bb.maxY == highest_max_y) {
/*       */                   
/*  3855 */                   double dx = x + 0.5D - pos_x;
/*  3856 */                   double dz = z + 0.5D - pos_z;
/*       */                   
/*  3858 */                   double distance_sq = dx * dx + dz * dz;
/*       */                   
/*  3860 */                   if (distance_sq < shortest_distance_sq) {
/*       */                     
/*  3862 */                     info = new BlockInfo(block, x, y, z);
/*  3863 */                     shortest_distance_sq = distance_sq;
/*       */                   } 
/*       */                 } 
/*       */               } 
/*       */             } 
/*       */           } 
/*       */         }
/*       */       } 
/*       */     } 
/*       */     
/*  3873 */     return info;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isInsideSolidBlock(double pos_x, double pos_y, double pos_z) {
/*  3879 */     int x = MathHelper.floor_double(pos_x);
/*  3880 */     int y = MathHelper.floor_double(pos_y);
/*  3881 */     int z = MathHelper.floor_double(pos_z);
/*       */     
/*  3883 */     int block_id = getBlockId(x, y, z);
/*       */     
/*  3885 */     if (block_id == 0) {
/*  3886 */       return false;
/*       */     }
/*  3888 */     Block block = Block.getBlock(block_id);
/*       */     
/*  3890 */     if (block.is_always_solid_standard_form_cube) {
/*  3891 */       return true;
/*       */     }
/*  3893 */     if (block.isNeverSolid()) {
/*  3894 */       return false;
/*       */     }
/*  3896 */     if (isBlockSolid(block, x, y, z)) {
/*       */       
/*  3898 */       block.setBlockBoundsBasedOnStateAndNeighbors(this, x, y, z);
/*       */       
/*  3900 */       int index = Minecraft.getThreadIndex();
/*       */       
/*  3902 */       pos_x -= x;
/*  3903 */       pos_y -= y;
/*  3904 */       pos_z -= z;
/*       */       
/*  3906 */       if (pos_x < block.minX[index] || pos_x >= block.maxX[index]) {
/*  3907 */         return false;
/*       */       }
/*  3909 */       if (pos_y < block.minY[index] || pos_y >= block.maxY[index]) {
/*  3910 */         return false;
/*       */       }
/*  3912 */       if (pos_z < block.minZ[index] || pos_z >= block.maxZ[index]) {
/*  3913 */         return false;
/*       */       }
/*  3915 */       return true;
/*       */     } 
/*       */     
/*  3918 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int calculateSkylightSubtracted(float par1) {
/*  3926 */     float var2 = getCelestialAngle(par1);
/*  3927 */     float var3 = 1.0F - MathHelper.cos(var2 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*       */     
/*  3929 */     if (var3 < 0.0F)
/*       */     {
/*  3931 */       var3 = 0.0F;
/*       */     }
/*       */     
/*  3934 */     if (var3 > 1.0F)
/*       */     {
/*  3936 */       var3 = 1.0F;
/*       */     }
/*       */     
/*  3939 */     this.skylight_subtracted_ignoring_rain_and_thunder = (int)(var3 * 11.0F);
/*       */     
/*  3941 */     var3 = 1.0F - var3;
/*  3942 */     var3 = (float)(var3 * (1.0D - (getRainStrength(par1) * 5.0F) / 16.0D));
/*  3943 */     var3 = (float)(var3 * (1.0D - (getWeightedThunderStrength(par1) * 5.0F) / 16.0D));
/*  3944 */     var3 = 1.0F - var3;
/*  3945 */     return (int)(var3 * 11.0F);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getSunBrightness(float par1) {
/*  3953 */     float var2 = getCelestialAngle(par1);
/*  3954 */     float var3 = 1.0F - MathHelper.cos(var2 * 3.1415927F * 2.0F) * 2.0F + 0.2F;
/*       */     
/*  3956 */     if (var3 < 0.0F)
/*       */     {
/*  3958 */       var3 = 0.0F;
/*       */     }
/*       */     
/*  3961 */     if (var3 > 1.0F)
/*       */     {
/*  3963 */       var3 = 1.0F;
/*       */     }
/*       */     
/*  3966 */     var3 = 1.0F - var3;
/*  3967 */     var3 = (float)(var3 * (1.0D - (getRainStrength(par1) * 5.0F) / 16.0D));
/*  3968 */     var3 = (float)(var3 * (1.0D - (getWeightedThunderStrength(par1) * 5.0F) / 16.0D));
/*  3969 */     return var3 * 0.8F + 0.2F;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Vec3 getSkyColor(Entity par1Entity, float par2) {
/*  3977 */     float var3 = getCelestialAngle(par2);
/*  3978 */     float var4 = MathHelper.cos(var3 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*       */     
/*  3980 */     if (var4 < 0.0F)
/*       */     {
/*  3982 */       var4 = 0.0F;
/*       */     }
/*       */     
/*  3985 */     if (var4 > 1.0F)
/*       */     {
/*  3987 */       var4 = 1.0F;
/*       */     }
/*       */     
/*  3990 */     int var5 = MathHelper.floor_double(par1Entity.posX);
/*  3991 */     int var6 = MathHelper.floor_double(par1Entity.posZ);
/*  3992 */     BiomeGenBase var7 = getBiomeGenForCoords(var5, var6);
/*  3993 */     float var8 = var7.getFloatTemperature();
/*       */     
/*  3995 */     if (Float.isNaN(this.biome_temperature_transition_for_sky_color)) {
/*       */       
/*  3997 */       this.biome_temperature_transition_for_sky_color = var8;
/*       */     }
/*       */     else {
/*       */       
/*  4001 */       float delta_temperature = var8 - this.biome_temperature_transition_for_sky_color;
/*       */       
/*  4003 */       if (delta_temperature < -0.005F) {
/*  4004 */         this.biome_temperature_transition_for_sky_color -= 0.005F;
/*  4005 */       } else if (delta_temperature > 0.005F) {
/*  4006 */         this.biome_temperature_transition_for_sky_color += 0.005F;
/*       */       } else {
/*  4008 */         this.biome_temperature_transition_for_sky_color = var8;
/*       */       } 
/*  4010 */       var8 = this.biome_temperature_transition_for_sky_color;
/*       */     } 
/*       */     
/*  4013 */     int var9 = var7.getSkyColorByTemp(var8);
/*  4014 */     float var10 = (var9 >> 16 & 0xFF) / 255.0F;
/*  4015 */     float var11 = (var9 >> 8 & 0xFF) / 255.0F;
/*  4016 */     float var12 = (var9 & 0xFF) / 255.0F;
/*  4017 */     var10 *= var4;
/*  4018 */     var11 *= var4;
/*  4019 */     var12 *= var4;
/*  4020 */     float var13 = getRainStrength(par2);
/*       */ 
/*       */ 
/*       */     
/*  4024 */     if (var13 > 0.0F) {
/*       */       
/*  4026 */       float f1 = (var10 * 0.3F + var11 * 0.59F + var12 * 0.11F) * 0.6F;
/*  4027 */       float var15 = 1.0F - var13 * 0.75F;
/*  4028 */       var10 = var10 * var15 + f1 * (1.0F - var15);
/*  4029 */       var11 = var11 * var15 + f1 * (1.0F - var15);
/*  4030 */       var12 = var12 * var15 + f1 * (1.0F - var15);
/*       */     } 
/*       */     
/*  4033 */     float var14 = getWeightedThunderStrength(par2);
/*       */     
/*  4035 */     if (var14 > 0.0F) {
/*       */       
/*  4037 */       float var15 = (var10 * 0.3F + var11 * 0.59F + var12 * 0.11F) * 0.2F;
/*  4038 */       float var16 = 1.0F - var14 * 0.75F;
/*  4039 */       var10 = var10 * var16 + var15 * (1.0F - var16);
/*  4040 */       var11 = var11 * var16 + var15 * (1.0F - var16);
/*  4041 */       var12 = var12 * var16 + var15 * (1.0F - var16);
/*       */     } 
/*       */     
/*  4044 */     if (this.lastLightningBolt > 0) {
/*       */       
/*  4046 */       float var15 = this.lastLightningBolt - par2;
/*       */       
/*  4048 */       if (var15 > 1.0F)
/*       */       {
/*  4050 */         var15 = 1.0F;
/*       */       }
/*       */       
/*  4053 */       float var10_before_flash = var10;
/*  4054 */       float var11_before_flash = var11;
/*  4055 */       float var12_before_flash = var12;
/*       */       
/*  4057 */       var15 *= 0.45F;
/*  4058 */       var10 = var10 * (1.0F - var15) + 0.8F * var15;
/*  4059 */       var11 = var11 * (1.0F - var15) + 0.8F * var15;
/*  4060 */       var12 = var12 * (1.0F - var15) + 1.0F * var15;
/*       */       
/*  4062 */       if (this == Minecraft.theMinecraft.theWorld) {
/*       */         
/*  4064 */         float raining_strength_for_render_view_entity = Minecraft.theMinecraft.raining_strength_for_render_view_entity;
/*  4065 */         float distance_factor = (float)Math.pow(raining_strength_for_render_view_entity, 4.0D);
/*       */         
/*  4067 */         var10 = var10 * distance_factor + var10_before_flash * (1.0F - distance_factor);
/*  4068 */         var11 = var11 * distance_factor + var11_before_flash * (1.0F - distance_factor);
/*  4069 */         var12 = var12 * distance_factor + var12_before_flash * (1.0F - distance_factor);
/*       */       } 
/*       */     } 
/*       */     
/*  4073 */     return getWorldVec3Pool().getVecFromPool(var10, var11, var12);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getCelestialAngle(float par1) {
/*  4082 */     return this.provider.calculateCelestialAngle(getTimeOfDay(), par1);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getMoonPhase() {
/*  4089 */     return this.provider.getMoonPhase(getTotalWorldTime());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getCurrentMoonPhaseFactor() {
/*  4098 */     return WorldProvider.moonPhaseFactors[this.provider.getMoonPhase(getTotalWorldTime())];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final float getMoonBrightness(float partial_tick, boolean include_weather) {
/*       */     float brightness;
/*  4119 */     if (isBloodMoon24HourPeriod()) {
/*  4120 */       brightness = 0.6F;
/*  4121 */     } else if (isHarvestMoon24HourPeriod()) {
/*  4122 */       brightness = 1.0F;
/*  4123 */     } else if (isBlueMoon24HourPeriod()) {
/*  4124 */       brightness = 1.1F;
/*       */     } else {
/*  4126 */       brightness = getCurrentMoonPhaseFactor() * 0.5F + 0.75F;
/*       */     } 
/*  4128 */     if (include_weather && brightness > 0.75F) {
/*       */       
/*  4130 */       float apparent_raining_strength = getRainStrength(partial_tick);
/*       */       
/*  4132 */       if (apparent_raining_strength > 0.0F) {
/*  4133 */         brightness = brightness * (1.0F - apparent_raining_strength) + 0.75F * apparent_raining_strength;
/*       */       }
/*       */     } 
/*  4136 */     return brightness;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isFullMoon() {
/*  4141 */     return (getMoonPhase() == 0);
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isNewMoon() {
/*  4146 */     return (getMoonPhase() == 4);
/*       */   }
/*       */ 
/*       */   
/*       */   public final float getMoonAscensionFactor() {
/*       */     float factor;
/*  4152 */     int time_of_day = getAdjustedTimeOfDay();
/*       */ 
/*       */ 
/*       */     
/*  4156 */     if (time_of_day <= getTimeOfSunrise()) {
/*  4157 */       factor = 1.0F - time_of_day / getTimeOfSunrise();
/*  4158 */     } else if (time_of_day >= getTimeOfSunset()) {
/*  4159 */       factor = (time_of_day - getTimeOfSunset()) / (24000 - getTimeOfSunset());
/*       */     } else {
/*  4161 */       factor = 0.0F;
/*       */     } 
/*  4163 */     if (factor < 0.0F || factor > 1.0F) {
/*  4164 */       Debug.setErrorMessage("getMoonAscensionFactor: value out of bounds " + factor);
/*       */     }
/*  4166 */     return factor;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final boolean isBloodMoon(long unadjusted_tick, boolean exclusively_at_night) {
/*  4181 */     if (exclusively_at_night && isDaytime(unadjusted_tick)) {
/*  4182 */       return false;
/*       */     }
/*  4184 */     return ((unadjusted_tick / 24000L + 1L) % 32L == 0L && !isBlueMoon(unadjusted_tick, exclusively_at_night));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBloodMoon(boolean exclusively_at_night) {
/*  4190 */     if (!isOverworld()) {
/*  4191 */       return false;
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  4196 */     if (exclusively_at_night && isDaytime()) {
/*  4197 */       return false;
/*       */     }
/*       */     
/*  4200 */     return ((getTotalWorldTime() / 24000L + 1L) % 32L == 0L && !isBlueMoon(exclusively_at_night));
/*       */   }
/*       */ 
/*       */   
/*       */   public static final boolean isBlueMoon(long unadjusted_tick, boolean exclusively_at_night) {
/*  4205 */     if (exclusively_at_night && isDaytime(unadjusted_tick)) {
/*  4206 */       return false;
/*       */     }
/*  4208 */     return ((unadjusted_tick / 24000L + 1L) % 128L == 0L);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlueMoon(boolean exclusively_at_night) {
/*  4214 */     if (!isOverworld()) {
/*  4215 */       return false;
/*       */     }
/*  4217 */     if (exclusively_at_night && isDaytime()) {
/*  4218 */       return false;
/*       */     }
/*       */     
/*  4221 */     return ((getTotalWorldTime() / 24000L + 1L) % 128L == 0L);
/*       */   }
/*       */ 
/*       */   
/*       */   public static final boolean isHarvestMoon(long unadjusted_tick, boolean exclusively_at_night) {
/*  4226 */     if (exclusively_at_night && isDaytime(unadjusted_tick)) {
/*  4227 */       return false;
/*       */     }
/*  4229 */     return isBloodMoon(unadjusted_tick + 192000L, exclusively_at_night);
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isHarvestMoon(boolean exclusively_at_night) {
/*  4234 */     return isHarvestMoon(getTotalWorldTime(), exclusively_at_night);
/*       */   }
/*       */ 
/*       */   
/*       */   public static final boolean isMoonDog(long unadjusted_tick, boolean exclusively_at_night) {
/*  4239 */     if (exclusively_at_night && isDaytime(unadjusted_tick)) {
/*  4240 */       return false;
/*       */     }
/*  4242 */     return isBlueMoon(unadjusted_tick + 192000L, exclusively_at_night);
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isMoonDog(boolean exclusively_at_night) {
/*  4247 */     return isMoonDog(getTotalWorldTime(), exclusively_at_night);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getCelestialAngleRadians(float par1) {
/*  4255 */     float var2 = getCelestialAngle(par1);
/*  4256 */     return var2 * 3.1415927F * 2.0F;
/*       */   }
/*       */ 
/*       */   
/*       */   public Vec3 getCloudColour(float par1) {
/*  4261 */     float var2 = getCelestialAngle(par1);
/*  4262 */     float var3 = MathHelper.cos(var2 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*       */     
/*  4264 */     if (var3 < 0.0F)
/*       */     {
/*  4266 */       var3 = 0.0F;
/*       */     }
/*       */     
/*  4269 */     if (var3 > 1.0F)
/*       */     {
/*  4271 */       var3 = 1.0F;
/*       */     }
/*       */     
/*  4274 */     float var4 = (float)(this.cloudColour >> 16L & 0xFFL) / 255.0F;
/*  4275 */     float var5 = (float)(this.cloudColour >> 8L & 0xFFL) / 255.0F;
/*  4276 */     float var6 = (float)(this.cloudColour & 0xFFL) / 255.0F;
/*  4277 */     float var7 = getRainStrength(par1);
/*       */ 
/*       */ 
/*       */     
/*  4281 */     if (var7 > 0.0F) {
/*       */       
/*  4283 */       float f1 = (var4 * 0.3F + var5 * 0.59F + var6 * 0.11F) * 0.6F;
/*  4284 */       float var9 = 1.0F - var7 * 0.95F;
/*  4285 */       var4 = var4 * var9 + f1 * (1.0F - var9);
/*  4286 */       var5 = var5 * var9 + f1 * (1.0F - var9);
/*  4287 */       var6 = var6 * var9 + f1 * (1.0F - var9);
/*       */     } 
/*       */     
/*  4290 */     var4 *= var3 * 0.9F + 0.1F;
/*  4291 */     var5 *= var3 * 0.9F + 0.1F;
/*  4292 */     var6 *= var3 * 0.85F + 0.15F;
/*  4293 */     float var8 = getWeightedThunderStrength(par1);
/*       */     
/*  4295 */     if (var8 > 0.0F) {
/*       */       
/*  4297 */       float var9 = (var4 * 0.3F + var5 * 0.59F + var6 * 0.11F) * 0.2F;
/*  4298 */       float var10 = 1.0F - var8 * 0.95F;
/*  4299 */       var4 = var4 * var10 + var9 * (1.0F - var10);
/*  4300 */       var5 = var5 * var10 + var9 * (1.0F - var10);
/*  4301 */       var6 = var6 * var10 + var9 * (1.0F - var10);
/*       */     } 
/*       */     
/*  4304 */     return getWorldVec3Pool().getVecFromPool(var4, var5, var6);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final Vec3 getFogColor(float par1, EntityLivingBase viewer) {
/*  4313 */     float var2 = getCelestialAngle(par1);
/*  4314 */     return this.provider.getFogColor(var2, par1, viewer);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getPrecipitationHeight(int par1, int par2) {
/*  4322 */     return getChunkFromBlockCoords(par1, par2).getPrecipitationHeight(par1 & 0xF, par2 & 0xF);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getTopSolidOrLiquidBlock(int par1, int par2) {
/*  4330 */     int x = par1;
/*  4331 */     int z = par2;
/*       */     
/*  4333 */     Chunk var3 = getChunkFromBlockCoords(par1, par2);
/*  4334 */     int var4 = var3.getTopFilledSegment() + 15;
/*  4335 */     par1 &= 0xF;
/*       */     
/*  4337 */     for (par2 &= 0xF; var4 > 0; var4--) {
/*       */       
/*  4339 */       int var5 = var3.getBlockID(par1, var4, par2);
/*       */       
/*  4341 */       if (var5 != 0) {
/*       */ 
/*       */         
/*  4344 */         Block block = Block.getBlock(var5);
/*       */ 
/*       */ 
/*       */         
/*  4348 */         if (isBlockSolid(block, x, var4, z) && block.blockMaterial != Material.tree_leaves)
/*       */         {
/*  4350 */           return var4 + 1;
/*       */         }
/*       */       } 
/*       */     } 
/*  4354 */     return -1;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public int getTopSolidOrLiquidBlockMITE(int par1, int par2, boolean ignore_leaves) {
/*  4360 */     int x = par1;
/*  4361 */     int z = par2;
/*       */     
/*  4363 */     Chunk var3 = getChunkFromBlockCoords(par1, par2);
/*  4364 */     int var4 = var3.getTopFilledSegment() + 15;
/*  4365 */     par1 &= 0xF;
/*       */     
/*  4367 */     for (par2 &= 0xF; var4 > 0; var4--) {
/*       */       
/*  4369 */       Block block = Block.getBlock(var3.getBlockID(par1, var4, par2));
/*       */       
/*  4371 */       if (block != null)
/*       */       {
/*       */         
/*  4374 */         if (!ignore_leaves || block.blockMaterial != Material.tree_leaves)
/*       */         {
/*       */ 
/*       */ 
/*       */           
/*  4379 */           if (block.isSolid(this, x, var4, z) || block.blockMaterial.isLiquid())
/*  4380 */             return var4;  } 
/*       */       }
/*       */     } 
/*  4383 */     return -1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getStarBrightness(float par1) {
/*  4391 */     float var2 = getCelestialAngle(par1);
/*  4392 */     float var3 = 1.0F - MathHelper.cos(var2 * 3.1415927F * 2.0F) * 2.0F + 0.25F;
/*       */     
/*  4394 */     if (var3 < 0.0F)
/*       */     {
/*  4396 */       var3 = 0.0F;
/*       */     }
/*       */     
/*  4399 */     if (var3 > 1.0F)
/*       */     {
/*  4401 */       var3 = 1.0F;
/*       */     }
/*       */     
/*  4404 */     return var3 * var3 * 0.5F;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void scheduleBlockUpdate(int par1, int par2, int par3, int par4, int par5) {}
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void scheduleBlockUpdateWithPriority(int par1, int par2, int par3, int par4, int par5, int par6) {}
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void scheduleBlockUpdateFromLoad(int par1, int par2, int par3, int par4, int par5, int par6) {}
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getHourOfDay() {
/*  4430 */     return getAdjustedTimeOfDay() / 1000;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public String getHourOfDayAMPM() {
/*  4447 */     return getHourOfDayAMPM(getHourOfDay());
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public static String getHourOfDayAMPM(int hour_of_day) {
/*  4453 */     return (hour_of_day == 0) ? "MDNT" : ((hour_of_day == 12) ? "NOON" : ((hour_of_day < 12) ? (hour_of_day + "AM") : ((hour_of_day - 12) + "PM")));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void updateEntities() {
/*  4461 */     this.theProfiler.startSection("entities");
/*  4462 */     this.theProfiler.startSection("global");
/*       */ 
/*       */     
/*       */     int var1;
/*       */ 
/*       */     
/*  4468 */     for (var1 = 0; var1 < this.weatherEffects.size(); var1++) {
/*       */       
/*  4470 */       Entity var2 = this.weatherEffects.get(var1);
/*       */ 
/*       */       
/*       */       try {
/*  4474 */         var2.ticksExisted++;
/*  4475 */         var2.despawn_counter++;
/*  4476 */         var2.onUpdate();
/*       */       }
/*  4478 */       catch (Throwable var8) {
/*       */         
/*  4480 */         CrashReport var4 = CrashReport.makeCrashReport(var8, "Ticking entity");
/*  4481 */         CrashReportCategory var5 = var4.makeCategory("Entity being ticked");
/*       */         
/*  4483 */         if (var2 == null) {
/*       */           
/*  4485 */           var5.addCrashSection("Entity", "~~NULL~~");
/*       */         }
/*       */         else {
/*       */           
/*  4489 */           var2.addEntityCrashInfo(var5);
/*       */         } 
/*       */         
/*  4492 */         throw new ReportedException(var4);
/*       */       } 
/*       */       
/*  4495 */       if (var2.isDead)
/*       */       {
/*  4497 */         this.weatherEffects.remove(var1--);
/*       */       }
/*       */     } 
/*       */     
/*  4501 */     this.theProfiler.endStartSection("remove");
/*  4502 */     this.loadedEntityList.removeAll(this.unloadedEntityList);
/*       */ 
/*       */ 
/*       */     
/*  4506 */     for (var1 = 0; var1 < this.unloadedEntityList.size(); var1++) {
/*       */       
/*  4508 */       Entity var2 = this.unloadedEntityList.get(var1);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4517 */       if (var2.isAddedToAChunk()) {
/*       */         
/*  4519 */         Chunk chunk = var2.getChunkAddedTo();
/*       */         
/*  4521 */         if (chunkExists(chunk.xPosition, chunk.zPosition)) {
/*  4522 */           var2.removeFromChunk();
/*       */         }
/*       */       } 
/*       */     } 
/*  4526 */     for (var1 = 0; var1 < this.unloadedEntityList.size(); var1++)
/*       */     {
/*  4528 */       onEntityRemoved(this.unloadedEntityList.get(var1));
/*       */     }
/*       */     
/*  4531 */     this.unloadedEntityList.clear();
/*  4532 */     this.theProfiler.endStartSection("regular");
/*       */     
/*  4534 */     for (var1 = 0; var1 < this.loadedEntityList.size(); var1++) {
/*       */       
/*  4536 */       Entity var2 = this.loadedEntityList.get(var1);
/*       */       
/*  4538 */       if (var2.ridingEntity != null) {
/*       */         
/*  4540 */         if (!var2.ridingEntity.isDead && var2.ridingEntity.riddenByEntity == var2) {
/*       */           continue;
/*       */         }
/*       */ 
/*       */         
/*  4545 */         var2.ridingEntity.riddenByEntity = null;
/*  4546 */         var2.ridingEntity = null;
/*       */       } 
/*       */       
/*  4549 */       this.theProfiler.startSection("tick");
/*       */       
/*  4551 */       if (!var2.isDead) {
/*       */         
/*       */         try {
/*       */           
/*  4555 */           updateEntity(var2);
/*       */         }
/*  4557 */         catch (Throwable var7) {
/*       */           
/*  4559 */           CrashReport var4 = CrashReport.makeCrashReport(var7, "Ticking entity");
/*  4560 */           CrashReportCategory var5 = var4.makeCategory("Entity being ticked");
/*  4561 */           var2.addEntityCrashInfo(var5);
/*  4562 */           throw new ReportedException(var4);
/*       */         } 
/*       */       }
/*       */       
/*  4566 */       this.theProfiler.endSection();
/*  4567 */       this.theProfiler.startSection("remove");
/*       */       
/*  4569 */       if (var2.isDead) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4579 */         if (var2.isAddedToAChunk()) {
/*       */           
/*  4581 */           Chunk chunk = var2.getChunkAddedTo();
/*       */           
/*  4583 */           if (chunkExists(chunk.xPosition, chunk.zPosition)) {
/*  4584 */             var2.removeFromChunk();
/*       */           }
/*       */         } 
/*  4587 */         this.loadedEntityList.remove(var1--);
/*  4588 */         onEntityRemoved(var2);
/*       */       } 
/*       */       
/*  4591 */       this.theProfiler.endSection();
/*       */       continue;
/*       */     } 
/*  4594 */     this.theProfiler.endStartSection("tileEntities");
/*  4595 */     this.scanningTileEntities = true;
/*  4596 */     Iterator<TileEntity> var14 = this.loadedTileEntityList.iterator();
/*       */     
/*  4598 */     while (var14.hasNext()) {
/*       */       
/*  4600 */       TileEntity var9 = var14.next();
/*       */       
/*  4602 */       if (!var9.isInvalid() && var9.hasWorldObj() && blockExists(var9.xCoord, var9.yCoord, var9.zCoord)) {
/*       */         
/*       */         try {
/*       */           
/*  4606 */           var9.updateEntity();
/*       */         }
/*  4608 */         catch (Throwable var6) {
/*       */           
/*  4610 */           CrashReport var4 = CrashReport.makeCrashReport(var6, "Ticking tile entity");
/*  4611 */           CrashReportCategory var5 = var4.makeCategory("Tile entity being ticked");
/*  4612 */           var9.func_85027_a(var5);
/*  4613 */           throw new ReportedException(var4);
/*       */         } 
/*       */       }
/*       */       
/*  4617 */       if (var9.isInvalid()) {
/*       */         
/*  4619 */         var14.remove();
/*       */         
/*  4621 */         if (chunkExists(var9.xCoord >> 4, var9.zCoord >> 4)) {
/*       */           
/*  4623 */           Chunk var11 = getChunkFromChunkCoords(var9.xCoord >> 4, var9.zCoord >> 4);
/*       */           
/*  4625 */           if (var11 != null)
/*       */           {
/*  4627 */             var11.removeChunkBlockTileEntity(var9.xCoord & 0xF, var9.yCoord, var9.zCoord & 0xF);
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  4633 */     this.scanningTileEntities = false;
/*       */     
/*  4635 */     if (!this.entityRemoval.isEmpty()) {
/*       */       
/*  4637 */       this.loadedTileEntityList.removeAll(this.entityRemoval);
/*  4638 */       this.entityRemoval.clear();
/*       */     } 
/*       */     
/*  4641 */     this.theProfiler.endStartSection("pendingTileEntities");
/*       */     
/*  4643 */     if (!this.addedTileEntityList.isEmpty()) {
/*       */       
/*  4645 */       for (int var10 = 0; var10 < this.addedTileEntityList.size(); var10++) {
/*       */         
/*  4647 */         TileEntity var12 = this.addedTileEntityList.get(var10);
/*       */         
/*  4649 */         if (!var12.isInvalid()) {
/*       */           
/*  4651 */           if (!this.loadedTileEntityList.contains(var12))
/*       */           {
/*  4653 */             this.loadedTileEntityList.add(var12);
/*       */           }
/*       */           
/*  4656 */           if (chunkExists(var12.xCoord >> 4, var12.zCoord >> 4)) {
/*       */             
/*  4658 */             Chunk var15 = getChunkFromChunkCoords(var12.xCoord >> 4, var12.zCoord >> 4);
/*       */             
/*  4660 */             if (var15 != null)
/*       */             {
/*  4662 */               var15.setChunkBlockTileEntity(var12.xCoord & 0xF, var12.yCoord, var12.zCoord & 0xF, var12);
/*       */             }
/*       */           } 
/*       */           
/*  4666 */           markBlockForUpdate(var12.xCoord, var12.yCoord, var12.zCoord);
/*       */         } 
/*       */       } 
/*       */       
/*  4670 */       this.addedTileEntityList.clear();
/*       */     } 
/*       */     
/*  4673 */     this.theProfiler.endSection();
/*  4674 */     this.theProfiler.endSection();
/*       */   }
/*       */ 
/*       */   
/*       */   public void addTileEntity(Collection par1Collection) {
/*  4679 */     if (this.scanningTileEntities) {
/*       */       
/*  4681 */       this.addedTileEntityList.addAll(par1Collection);
/*       */     }
/*       */     else {
/*       */       
/*  4685 */       this.loadedTileEntityList.addAll(par1Collection);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void updateEntity(Entity par1Entity) {
/*  4694 */     updateEntityWithOptionalForce(par1Entity, true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void updateEntityWithOptionalForce(Entity par1Entity, boolean par2) {
/*  4703 */     int var3 = MathHelper.floor_double(par1Entity.posX);
/*  4704 */     int var4 = MathHelper.floor_double(par1Entity.posZ);
/*  4705 */     byte var5 = 32;
/*       */     
/*  4707 */     if (!par2 || checkChunksExist(var3 - var5, 0, var4 - var5, var3 + var5, 0, var4 + var5)) {
/*       */       
/*  4709 */       par1Entity.lastTickPosX = par1Entity.posX;
/*  4710 */       par1Entity.lastTickPosY = par1Entity.posY;
/*  4711 */       par1Entity.lastTickPosZ = par1Entity.posZ;
/*  4712 */       par1Entity.prevRotationYaw = par1Entity.rotationYaw;
/*  4713 */       par1Entity.prevRotationPitch = par1Entity.rotationPitch;
/*       */ 
/*       */       
/*  4716 */       if (par2 && par1Entity.isAddedToAChunk()) {
/*       */         
/*  4718 */         par1Entity.ticksExisted++;
/*  4719 */         par1Entity.despawn_counter++;
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4724 */         if (par1Entity.ridingEntity != null) {
/*       */           
/*  4726 */           par1Entity.updateRidden();
/*       */         }
/*       */         else {
/*       */           
/*  4730 */           par1Entity.onUpdate();
/*       */         } 
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4745 */       this.theProfiler.startSection("chunkCheck");
/*       */       
/*  4747 */       if (Double.isNaN(par1Entity.posX) || Double.isInfinite(par1Entity.posX))
/*       */       {
/*  4749 */         par1Entity.posX = par1Entity.lastTickPosX;
/*       */       }
/*       */       
/*  4752 */       if (Double.isNaN(par1Entity.posY) || Double.isInfinite(par1Entity.posY))
/*       */       {
/*  4754 */         par1Entity.posY = par1Entity.lastTickPosY;
/*       */       }
/*       */       
/*  4757 */       if (Double.isNaN(par1Entity.posZ) || Double.isInfinite(par1Entity.posZ))
/*       */       {
/*  4759 */         par1Entity.posZ = par1Entity.lastTickPosZ;
/*       */       }
/*       */       
/*  4762 */       if (Double.isNaN(par1Entity.rotationPitch) || Double.isInfinite(par1Entity.rotationPitch))
/*       */       {
/*  4764 */         par1Entity.rotationPitch = par1Entity.prevRotationPitch;
/*       */       }
/*       */       
/*  4767 */       if (Double.isNaN(par1Entity.rotationYaw) || Double.isInfinite(par1Entity.rotationYaw))
/*       */       {
/*  4769 */         par1Entity.rotationYaw = par1Entity.prevRotationYaw;
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4776 */       int var6 = par1Entity.getChunkPosX();
/*  4777 */       int var7 = par1Entity.getChunkCurrentlyInSectionIndex();
/*  4778 */       int var8 = par1Entity.getChunkPosZ();
/*       */       
/*  4780 */       if (par1Entity.isAddedToAChunk() && (par1Entity.getChunkAddedTo()).worldObj != this) {
/*  4781 */         par1Entity.removeFromChunk();
/*       */       }
/*       */       
/*  4784 */       if (!par1Entity.isAddedToAChunk() || (par1Entity.getChunkAddedTo()).xPosition != var6 || par1Entity.chunk_added_to_section_index != var7 || (par1Entity.getChunkAddedTo()).zPosition != var8) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4793 */         if (par1Entity.isAddedToAChunk()) {
/*       */           
/*  4795 */           Chunk chunk = par1Entity.getChunkAddedTo();
/*       */           
/*  4797 */           if (chunk.worldObj != this) {
/*       */             
/*  4799 */             Minecraft.setErrorMessage("updateEntityWithOptionalForce: entity still belongs to a chunk of a different world");
/*       */             
/*  4801 */             (new Exception()).printStackTrace();
/*       */           } 
/*       */           
/*  4804 */           if (chunkExists(chunk.xPosition, chunk.zPosition)) {
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  4809 */             par1Entity.removeFromChunk();
/*       */           }
/*       */           else {
/*       */             
/*  4813 */             Minecraft.setErrorMessage("updateEntityWithOptionalForce: " + par1Entity.getEntityName() + " was added to a chunk that no longer exists?");
/*       */           } 
/*       */         } 
/*       */         
/*  4817 */         if (chunkExists(var6, var8)) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  4826 */           getChunkFromChunkCoords(var6, var8).addEntity(par1Entity);
/*       */         
/*       */         }
/*       */         else {
/*       */ 
/*       */           
/*  4832 */           par1Entity.setChunkAddedToUnchecked(null, -1);
/*       */         } 
/*       */       } 
/*       */       
/*  4836 */       this.theProfiler.endSection();
/*       */ 
/*       */       
/*  4839 */       if (par2 && par1Entity.isAddedToAChunk() && par1Entity.riddenByEntity != null)
/*       */       {
/*  4841 */         if (!par1Entity.riddenByEntity.isDead && par1Entity.riddenByEntity.ridingEntity == par1Entity)
/*       */         {
/*  4843 */           updateEntity(par1Entity.riddenByEntity);
/*       */         }
/*       */         else
/*       */         {
/*  4847 */           par1Entity.riddenByEntity.ridingEntity = null;
/*  4848 */           par1Entity.riddenByEntity = null;
/*       */         }
/*       */       
/*       */       }
/*  4852 */     } else if (par1Entity instanceof IMob && par1Entity instanceof EntityLiving && par1Entity.ridingEntity == null && par1Entity.riddenByEntity == null) {
/*       */       
/*  4854 */       par1Entity.despawn_counter++;
/*       */       
/*  4856 */       ((EntityLiving)par1Entity).tryDespawnEntity();
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean checkNoEntityCollision(AxisAlignedBB par1AxisAlignedBB) {
/*  4868 */     return checkNoEntityCollision(par1AxisAlignedBB, (Entity)null);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean checkNoEntityCollision(AxisAlignedBB par1AxisAlignedBB, Entity par2Entity) {
/*  4876 */     List<Entity> var3 = getEntitiesWithinAABBExcludingEntity((Entity)null, par1AxisAlignedBB);
/*       */     
/*  4878 */     for (int var4 = 0; var4 < var3.size(); var4++) {
/*       */       
/*  4880 */       Entity var5 = var3.get(var4);
/*       */       
/*  4882 */       if (!var5.isDead && var5.preventEntitySpawning && var5 != par2Entity)
/*       */       {
/*  4884 */         return false;
/*       */       }
/*       */     } 
/*       */     
/*  4888 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean checkBlockCollision(AxisAlignedBB bounding_box) {
/*  4942 */     int min_x = bounding_box.getBlockCoordForMinX();
/*  4943 */     int max_x = bounding_box.getBlockCoordForMaxX();
/*  4944 */     int min_y = bounding_box.getBlockCoordForMinY();
/*  4945 */     int max_y = bounding_box.getBlockCoordForMaxY();
/*  4946 */     int min_z = bounding_box.getBlockCoordForMinZ();
/*  4947 */     int max_z = bounding_box.getBlockCoordForMaxZ();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  4958 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  4960 */       for (int y = min_y; y <= max_y; y++) {
/*       */         
/*  4962 */         for (int z = min_z; z <= max_z; z++) {
/*       */           
/*  4964 */           if (getBlock(x, y, z) != null) {
/*  4965 */             return true;
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*  4970 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isAnyLiquid(AxisAlignedBB bounding_box) {
/*  5024 */     int min_x = bounding_box.getBlockCoordForMinX();
/*  5025 */     int max_x = bounding_box.getBlockCoordForMaxX();
/*  5026 */     int min_y = bounding_box.getBlockCoordForMinY();
/*  5027 */     int max_y = bounding_box.getBlockCoordForMaxY();
/*  5028 */     int min_z = bounding_box.getBlockCoordForMinZ();
/*  5029 */     int max_z = bounding_box.getBlockCoordForMaxZ();
/*       */     
/*  5031 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  5033 */       for (int y = min_y; y <= max_y; y++) {
/*       */         
/*  5035 */         for (int z = min_z; z <= max_z; z++) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5042 */           if (getBlockMaterial(x, y, z).isLiquid()) {
/*  5043 */             return true;
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*  5048 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isAnyLava(AxisAlignedBB bounding_box) {
/*  5099 */     int min_x = bounding_box.getBlockCoordForMinX();
/*  5100 */     int max_x = bounding_box.getBlockCoordForMaxX();
/*  5101 */     int min_y = bounding_box.getBlockCoordForMinY();
/*  5102 */     int max_y = bounding_box.getBlockCoordForMaxY();
/*  5103 */     int min_z = bounding_box.getBlockCoordForMinZ();
/*  5104 */     int max_z = bounding_box.getBlockCoordForMaxZ();
/*       */     
/*  5106 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  5108 */       for (int y = min_y; y <= max_y; y++) {
/*       */         
/*  5110 */         for (int z = min_z; z <= max_z; z++) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5117 */           if (getBlockMaterial(x, y, z) == Material.lava) {
/*  5118 */             return true;
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*  5123 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isOnlyWater(AxisAlignedBB bounding_box) {
/*  5131 */     int min_x = bounding_box.getBlockCoordForMinX();
/*  5132 */     int max_x = bounding_box.getBlockCoordForMaxX();
/*  5133 */     int min_y = bounding_box.getBlockCoordForMinY();
/*  5134 */     int max_y = bounding_box.getBlockCoordForMaxY();
/*  5135 */     int min_z = bounding_box.getBlockCoordForMinZ();
/*  5136 */     int max_z = bounding_box.getBlockCoordForMaxZ();
/*       */     
/*  5138 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  5140 */       for (int y = min_y; y <= max_y; y++) {
/*       */         
/*  5142 */         for (int z = min_z; z <= max_z; z++) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5149 */           if (getBlockMaterial(x, y, z) != Material.water) {
/*  5150 */             return false;
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*  5155 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBoundingBoxBurning(AxisAlignedBB par1AxisAlignedBB, boolean include_lava) {
/*  5196 */     int min_x = par1AxisAlignedBB.getBlockCoordForMinX();
/*  5197 */     int max_x = par1AxisAlignedBB.getBlockCoordForMaxX();
/*  5198 */     int min_y = par1AxisAlignedBB.getBlockCoordForMinY();
/*  5199 */     int max_y = par1AxisAlignedBB.getBlockCoordForMaxY();
/*  5200 */     int min_z = par1AxisAlignedBB.getBlockCoordForMinZ();
/*  5201 */     int max_z = par1AxisAlignedBB.getBlockCoordForMaxZ();
/*       */     
/*  5203 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  5205 */       for (int y = min_y; y <= max_y; y++) {
/*       */         
/*  5207 */         for (int z = min_z; z <= max_z; z++) {
/*       */           
/*  5209 */           int block_id = getBlockId(x, y, z);
/*       */           
/*  5211 */           if (block_id == Block.fire.blockID || (include_lava && (block_id == Block.lavaMoving.blockID || block_id == Block.lavaStill.blockID))) {
/*  5212 */             return true;
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*  5217 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean doesBoundingBoxContainBlock(AxisAlignedBB par1AxisAlignedBB, int block_id, int metadata) {
/*  5223 */     int min_x = par1AxisAlignedBB.getBlockCoordForMinX();
/*  5224 */     int max_x = par1AxisAlignedBB.getBlockCoordForMaxX();
/*  5225 */     int min_y = par1AxisAlignedBB.getBlockCoordForMinY();
/*  5226 */     int max_y = par1AxisAlignedBB.getBlockCoordForMaxY();
/*  5227 */     int min_z = par1AxisAlignedBB.getBlockCoordForMinZ();
/*  5228 */     int max_z = par1AxisAlignedBB.getBlockCoordForMaxZ();
/*       */     
/*  5230 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  5232 */       for (int y = min_y; y <= max_y; y++) {
/*       */         
/*  5234 */         for (int z = min_z; z <= max_z; z++) {
/*       */           
/*  5236 */           if (getBlockId(x, y, z) == block_id)
/*       */           {
/*  5238 */             if (metadata < 0 || getBlockMetadata(x, y, z) == metadata) {
/*  5239 */               return true;
/*       */             }
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*  5245 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean extinguishAllFireBlocksInBoundingBox(AxisAlignedBB bb) {
/*  5250 */     boolean result = false;
/*       */     
/*  5252 */     int min_x = bb.getBlockCoordForMinX();
/*  5253 */     int max_x = bb.getBlockCoordForMaxX();
/*  5254 */     int min_y = bb.getBlockCoordForMinY();
/*  5255 */     int max_y = bb.getBlockCoordForMaxY();
/*  5256 */     int min_z = bb.getBlockCoordForMinZ();
/*  5257 */     int max_z = bb.getBlockCoordForMaxZ();
/*       */     
/*  5259 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  5261 */       for (int y = min_y; y <= max_y; y++) {
/*       */         
/*  5263 */         for (int z = min_z; z <= max_z; z++) {
/*       */           
/*  5265 */           int block_id = getBlockId(x, y, z);
/*       */           
/*  5267 */           if (block_id == Block.fire.blockID) {
/*       */             
/*  5269 */             douseFire(x, y, z, null);
/*  5270 */             result = true;
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  5276 */     return result;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean handleMaterialAcceleration(AxisAlignedBB par1AxisAlignedBB, Material par2Material, Entity par3Entity) {
/*  5338 */     int min_x = par1AxisAlignedBB.getBlockCoordForMinX();
/*  5339 */     int max_x = par1AxisAlignedBB.getBlockCoordForMaxX();
/*  5340 */     int min_y = par1AxisAlignedBB.getBlockCoordForMinY();
/*  5341 */     int max_y = par1AxisAlignedBB.getBlockCoordForMaxY();
/*  5342 */     int min_z = par1AxisAlignedBB.getBlockCoordForMinZ();
/*  5343 */     int max_z = par1AxisAlignedBB.getBlockCoordForMaxZ();
/*       */     
/*  5345 */     boolean is_in_material = false;
/*  5346 */     Vec3 vec3 = null;
/*       */     
/*  5348 */     boolean is_pushed_by_material = par3Entity.isPushedByWater();
/*       */     
/*  5350 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  5352 */       for (int y = min_y; y <= max_y; y++) {
/*       */         
/*  5354 */         for (int z = min_z; z <= max_z; z++) {
/*       */           
/*  5356 */           int block_id = getBlockId(x, y, z);
/*       */           
/*  5358 */           if (block_id != 0) {
/*       */ 
/*       */             
/*  5361 */             Block block = Block.getBlock(block_id);
/*       */             
/*  5363 */             if (block.blockMaterial == par2Material) {
/*       */               
/*  5365 */               double fluid_top_y = ((y + 1) - BlockFluid.getFluidHeightPercent(getBlockMetadata(x, y, z)));
/*       */               
/*  5367 */               if (max_y + 1.0D >= fluid_top_y) {
/*       */                 
/*  5369 */                 is_in_material = true;
/*       */                 
/*  5371 */                 if (is_pushed_by_material) {
/*       */                   
/*  5373 */                   if (vec3 == null) {
/*  5374 */                     vec3 = getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
/*       */                   }
/*  5376 */                   block.velocityToAddToEntity(this, x, y, z, par3Entity, vec3);
/*       */                 } 
/*       */               } 
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } 
/*  5384 */     if (!is_in_material) {
/*  5385 */       return false;
/*       */     }
/*  5387 */     if (is_pushed_by_material && vec3.lengthVector() > 0.0D) {
/*       */       
/*  5389 */       vec3 = vec3.normalize();
/*  5390 */       double var18 = 0.014D;
/*  5391 */       par3Entity.motionX += vec3.xCoord * var18;
/*  5392 */       par3Entity.motionY += vec3.yCoord * var18;
/*  5393 */       par3Entity.motionZ += vec3.zCoord * var18;
/*       */     } 
/*       */     
/*  5396 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isMaterialInBB(AxisAlignedBB par1AxisAlignedBB, Material par2Material) {
/*  5404 */     int var3 = MathHelper.floor_double(par1AxisAlignedBB.minX);
/*  5405 */     int var4 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
/*  5406 */     int var5 = MathHelper.floor_double(par1AxisAlignedBB.minY);
/*  5407 */     int var6 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
/*  5408 */     int var7 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
/*  5409 */     int var8 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);
/*       */     
/*  5411 */     for (int var9 = var3; var9 < var4; var9++) {
/*       */       
/*  5413 */       for (int var10 = var5; var10 < var6; var10++) {
/*       */         
/*  5415 */         for (int var11 = var7; var11 < var8; var11++) {
/*       */           
/*  5417 */           Block var12 = Block.blocksList[getBlockId(var9, var10, var11)];
/*       */           
/*  5419 */           if (var12 != null && var12.blockMaterial == par2Material)
/*       */           {
/*  5421 */             return true;
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  5427 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isAABBInMaterial(AxisAlignedBB par1AxisAlignedBB, Material par2Material) {
/*  5435 */     int var3 = MathHelper.floor_double(par1AxisAlignedBB.minX);
/*  5436 */     int var4 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
/*  5437 */     int var5 = MathHelper.floor_double(par1AxisAlignedBB.minY);
/*  5438 */     int var6 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
/*  5439 */     int var7 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
/*  5440 */     int var8 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);
/*       */     
/*  5442 */     for (int var9 = var3; var9 < var4; var9++) {
/*       */       
/*  5444 */       for (int var10 = var5; var10 < var6; var10++) {
/*       */         
/*  5446 */         for (int var11 = var7; var11 < var8; var11++) {
/*       */           
/*  5448 */           Block var12 = Block.blocksList[getBlockId(var9, var10, var11)];
/*       */           
/*  5450 */           if (var12 != null && var12.blockMaterial == par2Material) {
/*       */             
/*  5452 */             int var13 = getBlockMetadata(var9, var10, var11);
/*  5453 */             double var14 = (var10 + 1);
/*       */             
/*  5455 */             if (var13 < 8)
/*       */             {
/*  5457 */               var14 = (var10 + 1) - var13 / 8.0D;
/*       */             }
/*       */             
/*  5460 */             if (var14 >= par1AxisAlignedBB.minY)
/*       */             {
/*  5462 */               return true;
/*       */             }
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  5469 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Explosion createExplosion(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities, boolean is_smoking) {
/*  5482 */     return newExplosion(exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities, false, is_smoking);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Explosion newExplosion(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities, boolean is_flaming, boolean is_smoking) {
/*  5500 */     Explosion explosion = new Explosion(this, exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities);
/*  5501 */     explosion.isFlaming = is_flaming;
/*  5502 */     explosion.isSmoking = is_smoking;
/*  5503 */     explosion.doExplosionA();
/*  5504 */     explosion.doExplosionB(true);
/*  5505 */     return explosion;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getBlockDensity(Vec3 par1Vec3, AxisAlignedBB par2AxisAlignedBB) {
/*  5513 */     double var3 = 1.0D / ((par2AxisAlignedBB.maxX - par2AxisAlignedBB.minX) * 2.0D + 1.0D);
/*  5514 */     double var5 = 1.0D / ((par2AxisAlignedBB.maxY - par2AxisAlignedBB.minY) * 2.0D + 1.0D);
/*  5515 */     double var7 = 1.0D / ((par2AxisAlignedBB.maxZ - par2AxisAlignedBB.minZ) * 2.0D + 1.0D);
/*  5516 */     int var9 = 0;
/*  5517 */     int var10 = 0;
/*       */     float var11;
/*  5519 */     for (var11 = 0.0F; var11 <= 1.0F; var11 = (float)(var11 + var3)) {
/*       */       float var12;
/*  5521 */       for (var12 = 0.0F; var12 <= 1.0F; var12 = (float)(var12 + var5)) {
/*       */         float var13;
/*  5523 */         for (var13 = 0.0F; var13 <= 1.0F; var13 = (float)(var13 + var7)) {
/*       */           
/*  5525 */           double var14 = par2AxisAlignedBB.minX + (par2AxisAlignedBB.maxX - par2AxisAlignedBB.minX) * var11;
/*  5526 */           double var16 = par2AxisAlignedBB.minY + (par2AxisAlignedBB.maxY - par2AxisAlignedBB.minY) * var12;
/*  5527 */           double var18 = par2AxisAlignedBB.minZ + (par2AxisAlignedBB.maxZ - par2AxisAlignedBB.minZ) * var13;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5533 */           if (checkForLineOfPhysicalReach(getVec3(var14, var16, var18), par1Vec3))
/*       */           {
/*  5535 */             var9++;
/*       */           }
/*       */           
/*  5538 */           var10++;
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  5543 */     return var9 / var10;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final RaycastCollision getBlockCollisionForSelection(Vec3 origin, Vec3 limit, boolean hit_liquids) {
/*  5549 */     return (new Raycast(this, origin, limit)).setForSelection(hit_liquids).performVsBlocks().getBlockCollision();
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final RaycastCollision getBlockCollisionForVision(Vec3 origin, Vec3 limit, boolean ignore_leaves) {
/*  5555 */     return (new Raycast(this, origin, limit)).setForVision(ignore_leaves).performVsBlocks().getBlockCollision();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean checkForLineOfSight(Vec3 origin, Vec3 limit, boolean ignore_leaves) {
/*  5563 */     return (getBlockCollisionForVision(origin, limit, ignore_leaves) == null);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final RaycastCollision getBlockCollisionForPhysicalReach(Vec3 origin, Vec3 limit) {
/*  5569 */     return (new Raycast(this, origin, limit)).setForPhysicalReach().performVsBlocks().getBlockCollision();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean checkForLineOfPhysicalReach(Vec3 origin, Vec3 limit) {
/*  5577 */     return (getBlockCollisionForPhysicalReach(origin, limit) == null);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final RaycastCollision getBlockCollisionForPolicies(Vec3 origin, Vec3 limit, RaycastPolicies policies) {
/*  5583 */     return (new Raycast(this, origin, limit)).setPolicies(policies).performVsBlocks().getBlockCollision();
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean checkForNoBlockCollision(Vec3 origin, Vec3 limit, RaycastPolicies policies) {
/*  5589 */     return (getBlockCollisionForPolicies(origin, limit, policies) == null);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final RaycastCollision getBlockCollisionForPolicies(Vec3 origin, Vec3 limit, RaycastPolicies policies, Entity originator) {
/*  5595 */     return (new Raycast(this, origin, limit)).setPolicies(policies).setOriginator(originator).performVsBlocks().getBlockCollision();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean extinguishFire(EntityPlayer player, int x, int y, int z, EnumFace face) {
/*  5648 */     x = face.getNeighborX(x);
/*  5649 */     y = face.getNeighborY(y);
/*  5650 */     z = face.getNeighborZ(z);
/*       */     
/*  5652 */     if (getBlock(x, y, z) != Block.fire) {
/*  5653 */       return false;
/*       */     }
/*  5655 */     playAuxSFXAtEntity(player, 1004, x, y, z, 0);
/*  5656 */     setBlockToAir(x, y, z);
/*  5657 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public String getDebugLoadedEntities() {
/*  5665 */     return "All: " + this.loadedEntityList.size();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public String getProviderName() {
/*  5673 */     return this.chunkProvider.makeString();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public TileEntity getBlockTileEntity(int par1, int par2, int par3) {
/*  5681 */     if (par2 >= 0 && par2 < 256) {
/*       */       
/*  5683 */       TileEntity var4 = null;
/*       */ 
/*       */ 
/*       */       
/*  5687 */       if (this.scanningTileEntities)
/*       */       {
/*  5689 */         for (int var5 = 0; var5 < this.addedTileEntityList.size(); var5++) {
/*       */           
/*  5691 */           TileEntity var6 = this.addedTileEntityList.get(var5);
/*       */           
/*  5693 */           if (!var6.isInvalid() && var6.xCoord == par1 && var6.yCoord == par2 && var6.zCoord == par3) {
/*       */             
/*  5695 */             var4 = var6;
/*       */             
/*       */             break;
/*       */           } 
/*       */         } 
/*       */       }
/*  5701 */       if (var4 == null) {
/*       */         
/*  5703 */         Chunk var7 = getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
/*       */         
/*  5705 */         if (var7 != null)
/*       */         {
/*  5707 */           var4 = var7.getChunkBlockTileEntity(par1 & 0xF, par2, par3 & 0xF);
/*       */         }
/*       */       } 
/*       */       
/*  5711 */       if (var4 == null)
/*       */       {
/*  5713 */         for (int var5 = 0; var5 < this.addedTileEntityList.size(); var5++) {
/*       */           
/*  5715 */           TileEntity var6 = this.addedTileEntityList.get(var5);
/*       */           
/*  5717 */           if (!var6.isInvalid() && var6.xCoord == par1 && var6.yCoord == par2 && var6.zCoord == par3) {
/*       */             
/*  5719 */             var4 = var6;
/*       */             
/*       */             break;
/*       */           } 
/*       */         } 
/*       */       }
/*  5725 */       return var4;
/*       */     } 
/*       */ 
/*       */     
/*  5729 */     return null;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setBlockTileEntity(int par1, int par2, int par3, TileEntity par4TileEntity) {
/*  5738 */     if (par4TileEntity != null && !par4TileEntity.isInvalid())
/*       */     {
/*  5740 */       if (this.scanningTileEntities) {
/*       */         
/*  5742 */         par4TileEntity.xCoord = par1;
/*  5743 */         par4TileEntity.yCoord = par2;
/*  5744 */         par4TileEntity.zCoord = par3;
/*  5745 */         Iterator<TileEntity> var5 = this.addedTileEntityList.iterator();
/*       */         
/*  5747 */         while (var5.hasNext()) {
/*       */           
/*  5749 */           TileEntity var6 = var5.next();
/*       */           
/*  5751 */           if (var6.xCoord == par1 && var6.yCoord == par2 && var6.zCoord == par3) {
/*       */             
/*  5753 */             var6.invalidate();
/*  5754 */             var5.remove();
/*       */           } 
/*       */         } 
/*       */         
/*  5758 */         this.addedTileEntityList.add(par4TileEntity);
/*       */       }
/*       */       else {
/*       */         
/*  5762 */         this.loadedTileEntityList.add(par4TileEntity);
/*  5763 */         Chunk var7 = getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
/*       */         
/*  5765 */         if (var7 != null)
/*       */         {
/*  5767 */           var7.setChunkBlockTileEntity(par1 & 0xF, par2, par3 & 0xF, par4TileEntity);
/*       */         }
/*       */       } 
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void removeBlockTileEntity(int par1, int par2, int par3) {
/*  5778 */     TileEntity var4 = getBlockTileEntity(par1, par2, par3);
/*       */     
/*  5780 */     if (var4 != null && this.scanningTileEntities) {
/*       */       
/*  5782 */       var4.invalidate();
/*  5783 */       this.addedTileEntityList.remove(var4);
/*       */     }
/*       */     else {
/*       */       
/*  5787 */       if (var4 != null) {
/*       */         
/*  5789 */         this.addedTileEntityList.remove(var4);
/*  5790 */         this.loadedTileEntityList.remove(var4);
/*       */       } 
/*       */       
/*  5793 */       Chunk var5 = getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
/*       */       
/*  5795 */       if (var5 != null)
/*       */       {
/*  5797 */         var5.removeChunkBlockTileEntity(par1 & 0xF, par2, par3 & 0xF);
/*       */       }
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void markTileEntityForDespawn(TileEntity par1TileEntity) {
/*  5807 */     this.entityRemoval.add(par1TileEntity);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlockStandardFormOpaqueCube(int par1, int par2, int par3) {
/*  5819 */     return Block.isBlockOpaqueStandardFormCube(this, par1, par2, par3);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlockNormalCube(int par1, int par2, int par3) {
/*  5859 */     return Block.isNormalCube(getBlockId(par1, par2, par3));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlockSolidStandardFormCube(int x, int y, int z) {
/*  5865 */     Block block = getBlock(x, y, z);
/*       */     
/*  5867 */     if (block == null) {
/*  5868 */       return false;
/*       */     }
/*  5870 */     if (block.isAlwaysSolidStandardFormCube()) {
/*  5871 */       return true;
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  5876 */     return block.isSolidStandardFormCube(getBlockMetadata(x, y, z));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlockFullSolidCube(int x, int y, int z) {
/*  5898 */     int block_id = getBlockId(x, y, z);
/*       */     
/*  5900 */     if (block_id == 0) {
/*  5901 */       return false;
/*       */     }
/*  5903 */     Block block = Block.getBlock(block_id);
/*       */     
/*  5905 */     if (block == null) {
/*  5906 */       return false;
/*       */     }
/*  5908 */     if (block.isAlwaysSolidStandardFormCube()) {
/*  5909 */       return true;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  5923 */     if (block.isNeverSolidStandardFormCube()) {
/*  5924 */       return false;
/*       */     }
/*  5926 */     return block.isSolidStandardFormCube(getBlockMetadata(x, y, z));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isBlockTopFlatAndSolid(int x, int y, int z) {
/*  5941 */     Block block = Block.blocksList[getBlockId(x, y, z)];
/*  5942 */     return (block != null && block.isTopFlatAndSolid(getBlockMetadata(x, y, z)));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static boolean isBlockTopFlatAndSolid(Block block, int metadata) {
/*  5984 */     return (block != null && block.isTopFlatAndSolid(metadata));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlockNormalCubeDefault(int par1, int par2, int par3, boolean par4) {
/*  5996 */     if (isWithinBlockDomain(par1, par3)) {
/*       */       
/*  5998 */       Chunk var5 = this.chunkProvider.provideChunk(par1 >> 4, par3 >> 4);
/*       */       
/*  6000 */       if (var5 != null && !var5.isEmpty())
/*       */       {
/*       */ 
/*       */         
/*  6004 */         return Block.is_normal_cube_lookup[getBlockId(par1, par2, par3)];
/*       */       }
/*       */ 
/*       */       
/*  6008 */       return par4;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  6013 */     return par4;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final void calculateInitialSkylight() {
/*  6023 */     int var1 = calculateSkylightSubtracted(1.0F);
/*       */     
/*  6025 */     if (var1 != this.skylightSubtracted)
/*       */     {
/*  6027 */       this.skylightSubtracted = var1;
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setAllowedSpawnTypes(boolean par1, boolean par2) {
/*  6036 */     this.spawnHostileMobs = par1;
/*  6037 */     this.spawnPeacefulMobs = par2;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void tick() {
/*  6045 */     if (this.isRemote) {
/*       */       
/*  6047 */       if (this instanceof WorldServer) {
/*  6048 */         Minecraft.setErrorMessage("tick: isRemote is true but world is instanceof WorldServer");
/*       */       
/*       */       }
/*       */     }
/*  6052 */     else if (!(this instanceof WorldServer)) {
/*  6053 */       Minecraft.setErrorMessage("tick: isRemote is false but world is not instanceof WorldServer");
/*       */     } 
/*       */     
/*  6056 */     long tt = this.total_time;
/*  6057 */     long twt = getTotalWorldTime();
/*       */     
/*  6059 */     if (Minecraft.inDevMode() && tt != twt) {
/*       */       
/*  6061 */       Minecraft.setErrorMessage("tick: total world time discrepency: " + tt + " vs " + twt + " for " + getDimensionId() + ", " + this.isRemote);
/*  6062 */       this.total_time = twt;
/*       */     } 
/*       */     
/*  6065 */     updateWeather();
/*       */   }
/*       */ 
/*       */   
/*       */   public void checkPendingEntitySpawns() {
/*  6070 */     if (this.isRemote) {
/*       */       return;
/*       */     }
/*  6073 */     long total_world_time = getTotalWorldTime();
/*       */     
/*  6075 */     Iterator<EntitySpawnPendingEntry> i = this.pending_entity_spawns.iterator();
/*       */     
/*  6077 */     while (i.hasNext()) {
/*       */       
/*  6079 */       EntitySpawnPendingEntry entry = i.next();
/*       */       
/*  6081 */       if (entry.scheduled_spawn_time <= total_world_time) {
/*       */         
/*  6083 */         spawnEntityInWorld(entry.entity);
/*  6084 */         i.remove();
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final void _calculateInitialWeather() {
/*  6094 */     if (!canPrecipitate()) {
/*       */       return;
/*       */     }
/*  6097 */     this.rainingStrength = 0.0F;
/*  6098 */     this.thunderingStrength = 0.0F;
/*       */ 
/*       */     
/*  6101 */     if (isPrecipitating(false)) {
/*       */       
/*  6103 */       this.rainingStrength = 1.0F;
/*       */ 
/*       */       
/*  6106 */       if (isThundering(false))
/*       */       {
/*  6108 */         this.thunderingStrength = 1.0F;
/*       */       }
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   protected void updateWeather() {
/*  6120 */     if (this.isRemote) {
/*  6121 */       Minecraft.setErrorMessage("updateWeather: Called on client?");
/*       */     }
/*  6123 */     if (!this.provider.hasNoSky) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6180 */       this.prevRainingStrength = this.rainingStrength;
/*       */ 
/*       */       
/*  6183 */       if (isPrecipitating(false)) {
/*       */         
/*  6185 */         this.rainingStrength = (float)(this.rainingStrength + 0.01D);
/*       */       }
/*       */       else {
/*       */         
/*  6189 */         this.rainingStrength = (float)(this.rainingStrength - 0.01D);
/*       */       } 
/*       */       
/*  6192 */       if (this.rainingStrength < 0.0F)
/*       */       {
/*  6194 */         this.rainingStrength = 0.0F;
/*       */       }
/*       */       
/*  6197 */       if (this.rainingStrength > 1.0F)
/*       */       {
/*  6199 */         this.rainingStrength = 1.0F;
/*       */       }
/*       */       
/*  6202 */       this.prevThunderingStrength = this.thunderingStrength;
/*       */ 
/*       */       
/*  6205 */       if (isThundering(false)) {
/*       */         
/*  6207 */         this.thunderingStrength = (float)(this.thunderingStrength + 0.01D);
/*       */       }
/*       */       else {
/*       */         
/*  6211 */         this.thunderingStrength = (float)(this.thunderingStrength - 0.01D);
/*       */       } 
/*       */       
/*  6214 */       if (this.thunderingStrength < 0.0F)
/*       */       {
/*  6216 */         this.thunderingStrength = 0.0F;
/*       */       }
/*       */       
/*  6219 */       if (this.thunderingStrength > 1.0F)
/*       */       {
/*  6221 */         this.thunderingStrength = 1.0F;
/*       */       }
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void checkLightingOfRandomBlockInView(boolean minimal) {
/*  6233 */     if (!hasSkylight()) {
/*       */       
/*  6235 */       Debug.setErrorMessage("checkLightingOfRandomBlockInView: why called for world without skylight?");
/*       */       
/*       */       return;
/*       */     } 
/*  6239 */     this.times_checkLightingOfRandomBlockInView_called++;
/*       */     
/*  6241 */     EntityLivingBase viewer = Minecraft.theMinecraft.renderViewEntity;
/*       */     
/*  6243 */     if (viewer == null) {
/*       */       return;
/*       */     }
/*       */     
/*  6247 */     if (!minimal || this.times_checkLightingOfRandomBlockInView_called % 40 != 0)
/*       */     {
/*       */ 
/*       */ 
/*       */       
/*  6252 */       if (!minimal || this.times_checkLightingOfRandomBlockInView_called % 20 != 0)
/*       */       {
/*       */ 
/*       */ 
/*       */         
/*  6257 */         if (MITEConstant.maxRandomRaycastsPerTickForCorrectiveLightingUpdates(this) > 0) {
/*       */           
/*  6259 */           int raycast_seed_offset = viewer.raycast_seed_offset;
/*  6260 */           viewer.raycast_seed_offset = getTimeOfDay();
/*       */           
/*  6262 */           for (int i = 0; i < (minimal ? 4 : 4); i++) {
/*       */             
/*  6264 */             float rotationYaw = viewer.rotationYaw;
/*  6265 */             float rotationPitch = viewer.rotationPitch;
/*       */             
/*  6267 */             viewer.rotationYaw = (float)(viewer.rotationYaw + Math.random() * 181.0D - 90.0D);
/*  6268 */             viewer.rotationPitch = (float)(viewer.rotationPitch + Math.random() * 181.0D - 90.0D);
/*       */             
/*  6270 */             Raycast raycast = new Raycast(viewer, 1.0F, 128.0D);
/*       */             
/*  6272 */             int raycast_type = i % 4;
/*       */             
/*  6274 */             if (raycast_type == 0) {
/*  6275 */               raycast.setForSelection(true);
/*  6276 */             } else if (raycast_type == 1) {
/*  6277 */               raycast.setForVision(false);
/*  6278 */             } else if (raycast_type == 2) {
/*  6279 */               raycast.setForBluntProjectile(viewer);
/*  6280 */             } else if (raycast_type == 3) {
/*  6281 */               raycast.setForPiercingProjectile(viewer);
/*       */             } 
/*  6283 */             RaycastCollision rc = raycast.performVsBlocksSingle().getBlockCollision();
/*       */             
/*  6285 */             if (rc != null && rc.isBlock() && MathHelper.isInRange(rc.neighbor_block_y, 0, 255)) {
/*       */               
/*  6287 */               int x = rc.neighbor_block_x;
/*  6288 */               int z = rc.neighbor_block_z;
/*       */               
/*  6290 */               Chunk chunk = getChunkFromBlockCoords(x, z);
/*       */               
/*  6292 */               if (!chunk.isEmpty())
/*       */               {
/*  6294 */                 updateLightByType(EnumSkyBlock.Sky, x, rc.neighbor_block_y, z, canUpdateLightByType(x, z), chunk);
/*       */               }
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  6325 */             viewer.rotationYaw = rotationYaw;
/*  6326 */             viewer.rotationPitch = rotationPitch;
/*       */           } 
/*       */           
/*  6329 */           viewer.raycast_seed_offset = raycast_seed_offset;
/*       */         }  } 
/*       */     }
/*       */   }
/*       */   
/*       */   protected void setActivePlayerChunks() {
/*  6335 */     this.activeChunkSet.clear();
/*  6336 */     this.theProfiler.startSection("buildList");
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6342 */     for (int var1 = 0; var1 < this.playerEntities.size(); var1++) {
/*       */       
/*  6344 */       EntityPlayer var2 = this.playerEntities.get(var1);
/*  6345 */       int var3 = MathHelper.floor_double(var2.posX / 16.0D);
/*  6346 */       int var4 = MathHelper.floor_double(var2.posZ / 16.0D);
/*  6347 */       byte var5 = 7;
/*       */       
/*  6349 */       for (int var6 = -var5; var6 <= var5; var6++) {
/*       */         
/*  6351 */         for (int var7 = -var5; var7 <= var5; var7++)
/*       */         {
/*  6353 */           this.activeChunkSet.add(new ChunkCoordIntPair(var6 + var3, var7 + var4));
/*       */         }
/*       */       } 
/*       */     } 
/*       */     
/*  6358 */     this.theProfiler.endSection();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   protected void setActivePlayerChunksAndCheckLight() {
/*  6388 */     setActivePlayerChunks();
/*       */     
/*  6390 */     if (this.ambientTickCountdown > 0)
/*       */     {
/*  6392 */       this.ambientTickCountdown--;
/*       */     }
/*       */     
/*  6395 */     this.theProfiler.startSection("playerCheckLight");
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6407 */     if (this.isRemote)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6510 */       if (hasSkylight()) {
/*  6511 */         checkLightingOfRandomBlockInView(true);
/*       */       }
/*       */     }
/*  6514 */     this.theProfiler.endSection();
/*       */   }
/*       */ 
/*       */   
/*       */   protected void moodSoundAndLightCheck(int par1, int par2, Chunk par3Chunk) {
/*  6519 */     this.theProfiler.endStartSection("moodSound");
/*       */     
/*  6521 */     if (this.ambientTickCountdown == 0 && !this.isRemote) {
/*       */       
/*  6523 */       this.updateLCG = this.updateLCG * 3 + 1013904223;
/*  6524 */       int var4 = this.updateLCG >> 2;
/*  6525 */       int var5 = var4 & 0xF;
/*  6526 */       int var6 = var4 >> 8 & 0xF;
/*  6527 */       int var7 = var4 >> 16 & 0x7F;
/*  6528 */       int var8 = par3Chunk.getBlockID(var5, var7, var6);
/*  6529 */       var5 += par1;
/*  6530 */       var6 += par2;
/*       */       
/*  6532 */       if (var8 == 0 && getFullBlockLightValue(var5, var7, var6) <= this.rand.nextInt(8) && getSavedLightValue(EnumSkyBlock.Sky, var5, var7, var6) <= 0) {
/*       */ 
/*       */         
/*  6535 */         EntityPlayer var9 = getClosestPlayer(var5 + 0.5D, var7 + 0.5D, var6 + 0.5D, 8.0D, true);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6545 */         if (var9 != null && var9.getDistanceSq(var5 + 0.5D, var7 + 0.5D, var6 + 0.5D) > 4.0D) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  6554 */           if (Minecraft.getMinecraft() == null) {
/*  6555 */             playSoundEffect(var5 + 0.5D, var7 + 0.5D, var6 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.rand.nextFloat() * 0.2F);
/*       */           } else {
/*  6557 */             (Minecraft.getMinecraft()).sndManager.playSoundFX("ambient.cave.cave", 0.7F, 0.8F + this.rand.nextFloat() * 0.2F);
/*       */           } 
/*       */           
/*  6560 */           this.ambientTickCountdown = getNextAmbientTickCountdown(false);
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  6565 */     this.theProfiler.endStartSection("checkLight");
/*       */ 
/*       */     
/*  6568 */     if (!this.isRemote) {
/*       */       
/*  6570 */       par3Chunk.performPendingSkylightUpdatesIfPossible();
/*  6571 */       par3Chunk.performPendingBlocklightUpdatesIfPossible();
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   protected void tickBlocksAndAmbiance() {
/*  6581 */     setActivePlayerChunksAndCheckLight();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isBlockFreezable(int par1, int par2, int par3) {
/*  6589 */     return canBlockFreeze(par1, par2, par3, false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isBlockFreezableNaturally(int par1, int par2, int par3) {
/*  6597 */     return canBlockFreeze(par1, par2, par3, true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isFreezing(int x, int z) {
/*  6607 */     return getBiomeGenForCoords(x, z).isFreezing();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean canBlockFreeze(int par1, int par2, int par3, boolean par4) {
/*  6616 */     BiomeGenBase var5 = getBiomeGenForCoords(par1, par3);
/*  6617 */     float var6 = var5.getFloatTemperature();
/*       */ 
/*       */     
/*  6620 */     if (var6 > 0.15F)
/*       */     {
/*  6622 */       return false;
/*       */     }
/*       */ 
/*       */     
/*  6626 */     if (par2 >= 0 && par2 < 256 && getSavedLightValue(EnumSkyBlock.Block, par1, par2, par3) < 10) {
/*       */       
/*  6628 */       int var7 = getBlockId(par1, par2, par3);
/*       */       
/*  6630 */       if ((var7 == Block.waterStill.blockID || var7 == Block.waterMoving.blockID) && getBlockMetadata(par1, par2, par3) == 0) {
/*       */         
/*  6632 */         if (!par4)
/*       */         {
/*  6634 */           return true;
/*       */         }
/*       */         
/*  6637 */         boolean var8 = true;
/*       */         
/*  6639 */         if (var8 && getBlockMaterial(par1 - 1, par2, par3) != Material.water)
/*       */         {
/*  6641 */           var8 = false;
/*       */         }
/*       */         
/*  6644 */         if (var8 && getBlockMaterial(par1 + 1, par2, par3) != Material.water)
/*       */         {
/*  6646 */           var8 = false;
/*       */         }
/*       */         
/*  6649 */         if (var8 && getBlockMaterial(par1, par2, par3 - 1) != Material.water)
/*       */         {
/*  6651 */           var8 = false;
/*       */         }
/*       */         
/*  6654 */         if (var8 && getBlockMaterial(par1, par2, par3 + 1) != Material.water)
/*       */         {
/*  6656 */           var8 = false;
/*       */         }
/*       */         
/*  6659 */         if (!var8)
/*       */         {
/*  6661 */           return true;
/*       */         }
/*       */       } 
/*       */     } 
/*       */     
/*  6666 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean canSnowAt(int par1, int par2, int par3) {
/*  6675 */     BiomeGenBase var4 = getBiomeGenForCoords(par1, par3);
/*  6676 */     float var5 = var4.getFloatTemperature();
/*       */ 
/*       */     
/*  6679 */     if (var5 > 0.15F)
/*       */     {
/*  6681 */       return false;
/*       */     }
/*       */ 
/*       */     
/*  6685 */     if (par2 >= 0 && par2 < 256 && getSavedLightValue(EnumSkyBlock.Block, par1, par2, par3) < 10) {
/*       */       
/*  6687 */       int var6 = getBlockId(par1, par2 - 1, par3);
/*  6688 */       int var7 = getBlockId(par1, par2, par3);
/*       */       
/*  6690 */       Block block_below = Block.getBlock(var6);
/*  6691 */       Block block = Block.getBlock(var7);
/*       */       
/*  6693 */       if (block_below == Block.tilledField && block != Block.pumpkinStem) {
/*  6694 */         return true;
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  6699 */       if (var7 == 0 && Block.snow.isLegalAt(this, par1, par2, par3, 0) && var6 != Block.ice.blockID)
/*       */       {
/*  6701 */         return true;
/*       */       }
/*       */     } 
/*       */     
/*  6705 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final void placeNaturallyOccurringSnow(int min_x, int min_z, int max_x, int max_z) {
/*  6712 */     boolean freezing_biome_nearby = (isBiomeFreezing(min_x, min_z) || isBiomeFreezing(min_x, max_z) || isBiomeFreezing(max_x, min_z) || isBiomeFreezing(max_x, max_z));
/*       */     
/*  6714 */     if (!freezing_biome_nearby) {
/*       */       return;
/*       */     }
/*  6717 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/*  6719 */       for (int z = min_z; z <= max_z; z++) {
/*       */         
/*  6721 */         int y = getPrecipitationHeight(x, z);
/*       */         
/*  6723 */         if (canSnowAt(x, y, z))
/*       */         {
/*  6725 */           if (setBlock(x, y, z, Block.snow.blockID, 0, 2))
/*       */           {
/*  6727 */             while (--y > 62) {
/*       */               
/*  6729 */               if (getBlockId(x, y, z) == Block.snow.blockID) {
/*       */                 
/*  6731 */                 setBlockToAir(x, y, z, 2);
/*       */                 break;
/*       */               } 
/*       */             } 
/*       */           }
/*       */         }
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final void updateAllLightTypes(int par1, int par2, int par3, Chunk chunk) {
/*  6748 */     boolean trusted_xz = canUpdateLightByType(par1, par3);
/*       */ 
/*       */     
/*  6751 */     if (hasSkylight())
/*       */     {
/*       */       
/*  6754 */       updateLightByType(EnumSkyBlock.Sky, par1, par2, par3, trusted_xz, chunk);
/*       */     }
/*       */ 
/*       */     
/*  6758 */     updateLightByType(EnumSkyBlock.Block, par1, par2, par3, trusted_xz, chunk);
/*       */   }
/*       */ 
/*       */   
/*       */   private int computeLightValue(int par1, int par2, int par3, EnumSkyBlock par4EnumSkyBlock) {
/*  6763 */     if (par4EnumSkyBlock == EnumSkyBlock.Sky && canBlockSeeTheSky(par1, par2, par3))
/*       */     {
/*  6765 */       return 15;
/*       */     }
/*       */ 
/*       */     
/*  6769 */     int var5 = getBlockId(par1, par2, par3);
/*  6770 */     int var6 = (par4EnumSkyBlock == EnumSkyBlock.Sky) ? 0 : Block.lightValue[var5];
/*  6771 */     int var7 = Block.lightOpacity[var5];
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6819 */     if (var7 < 1) {
/*       */       
/*  6821 */       var7 = 1;
/*       */     }
/*       */     else {
/*       */       
/*  6825 */       if (getHeightValue(par1, par3) - 1 > par2) {
/*  6826 */         var7++;
/*       */       }
/*  6828 */       if (var7 >= 15)
/*       */       {
/*  6830 */         if (Block.lightValue[var5] > 0) {
/*  6831 */           var7 = 1;
/*       */         } else {
/*  6833 */           return 0;
/*       */         } 
/*       */       }
/*       */     } 
/*  6837 */     if (var6 >= 14) {
/*  6838 */       return var6;
/*       */     }
/*       */ 
/*       */     
/*  6842 */     int local_x = par1 & 0xF;
/*  6843 */     int local_z = par3 & 0xF;
/*       */     
/*  6845 */     Chunk chunk = getChunkFromBlockCoords(par1, par3);
/*       */     
/*  6847 */     if (chunk.isEmpty()) {
/*       */ 
/*       */ 
/*       */       
/*  6851 */       if (Minecraft.inDevMode()) {
/*  6852 */         Minecraft.setErrorMessage("computeLightValue: chunk was empty at " + par1 + "," + par3, false);
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       int i;
/*       */ 
/*       */ 
/*       */       
/*  6862 */       if (local_z == 0 && (i = getSavedLightValue(par4EnumSkyBlock, par1, par2, par3 - 1) - var7) > var6 && (var6 = i) >= 14) {
/*  6863 */         return var6;
/*       */       }
/*  6865 */       if (local_z == 15 && (i = getSavedLightValue(par4EnumSkyBlock, par1, par2, par3 + 1) - var7) > var6 && (var6 = i) >= 14) {
/*  6866 */         return var6;
/*       */       }
/*  6868 */       if (local_x == 0 && (i = getSavedLightValue(par4EnumSkyBlock, par1 - 1, par2, par3) - var7) > var6 && (var6 = i) >= 14) {
/*  6869 */         return var6;
/*       */       }
/*  6871 */       if (local_x == 15 && (i = getSavedLightValue(par4EnumSkyBlock, par1 + 1, par2, par3) - var7) > var6 && (var6 = i) >= 14) {
/*  6872 */         return var6;
/*       */       }
/*  6874 */       return var6;
/*       */     } 
/*       */     int var12;
/*  6877 */     if ((var12 = getSavedLightValueMITE(par4EnumSkyBlock, par1, par2 - 1, par3, chunk) - var7) > var6 && (var6 = var12) >= 14) {
/*  6878 */       return var6;
/*       */     }
/*  6880 */     if ((var12 = getSavedLightValueMITE(par4EnumSkyBlock, par1, par2 + 1, par3, chunk) - var7) > var6 && (var6 = var12) >= 14) {
/*  6881 */       return var6;
/*       */     }
/*  6883 */     if ((var12 = ((local_z > 0) ? getSavedLightValueMITE(par4EnumSkyBlock, par1, par2, par3 - 1, chunk) : getSavedLightValue(par4EnumSkyBlock, par1, par2, par3 - 1)) - var7) > var6 && (var6 = var12) >= 14) {
/*  6884 */       return var6;
/*       */     }
/*  6886 */     if ((var12 = ((local_z < 15) ? getSavedLightValueMITE(par4EnumSkyBlock, par1, par2, par3 + 1, chunk) : getSavedLightValue(par4EnumSkyBlock, par1, par2, par3 + 1)) - var7) > var6 && (var6 = var12) >= 14) {
/*  6887 */       return var6;
/*       */     }
/*  6889 */     if ((var12 = ((local_x > 0) ? getSavedLightValueMITE(par4EnumSkyBlock, par1 - 1, par2, par3, chunk) : getSavedLightValue(par4EnumSkyBlock, par1 - 1, par2, par3)) - var7) > var6 && (var6 = var12) >= 14) {
/*  6890 */       return var6;
/*       */     }
/*  6892 */     if ((var12 = ((local_x < 15) ? getSavedLightValueMITE(par4EnumSkyBlock, par1 + 1, par2, par3, chunk) : getSavedLightValue(par4EnumSkyBlock, par1 + 1, par2, par3)) - var7) > var6 && (var6 = var12) >= 14) {
/*  6893 */       return var6;
/*       */     }
/*  6895 */     return var6;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private int computeLightValueMITE(int par1, int par2, int par3, EnumSkyBlock par4EnumSkyBlock, Chunk chunk) {
/*       */     int var5;
/*  6905 */     if (par4EnumSkyBlock == EnumSkyBlock.Sky && chunk.canBlockSeeTheSkyForNonEmptyChunk(par1 & 0xF, par2, par3 & 0xF))
/*       */     {
/*  6907 */       return 15;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6916 */     if (isWithinBlockBounds(par1, par2, par3)) {
/*       */       
/*  6918 */       var5 = chunk.getBlockIDOptimized((par1 & 0xF) + (par3 & 0xF) * 16, par2);
/*       */     } else {
/*  6920 */       var5 = 0;
/*       */     } 
/*  6922 */     int var6 = (par4EnumSkyBlock == EnumSkyBlock.Sky) ? 0 : Block.lightValue[var5];
/*  6923 */     int var7 = Block.lightOpacity[var5];
/*       */     
/*  6925 */     if (var7 < 1) {
/*       */       
/*  6927 */       var7 = 1;
/*       */     }
/*       */     else {
/*       */       
/*  6931 */       if (chunk.getHeightValue(par1 & 0xF, par3 & 0xF) - 1 > par2) {
/*  6932 */         var7++;
/*       */       }
/*  6934 */       if (var7 >= 15)
/*       */       {
/*  6936 */         if (Block.lightValue[var5] > 0) {
/*  6937 */           var7 = 1;
/*       */         } else {
/*  6939 */           return 0;
/*       */         } 
/*       */       }
/*       */     } 
/*  6943 */     if (var6 >= 14) {
/*  6944 */       return var6;
/*       */     }
/*       */ 
/*       */     
/*  6948 */     int local_x = par1 & 0xF;
/*  6949 */     int local_z = par3 & 0xF;
/*       */     int var12;
/*  6951 */     if ((var12 = getSavedLightValueMITE(par4EnumSkyBlock, par1, par2 - 1, par3, chunk) - var7) > var6 && (var6 = var12) >= 14) {
/*  6952 */       return var6;
/*       */     }
/*  6954 */     if ((var12 = getSavedLightValueMITE(par4EnumSkyBlock, par1, par2 + 1, par3, chunk) - var7) > var6 && (var6 = var12) >= 14) {
/*  6955 */       return var6;
/*       */     }
/*  6957 */     if ((var12 = ((local_z > 0) ? getSavedLightValueMITE(par4EnumSkyBlock, par1, par2, par3 - 1, chunk) : getSavedLightValue(par4EnumSkyBlock, par1, par2, par3 - 1)) - var7) > var6 && (var6 = var12) >= 14) {
/*  6958 */       return var6;
/*       */     }
/*  6960 */     if ((var12 = ((local_z < 15) ? getSavedLightValueMITE(par4EnumSkyBlock, par1, par2, par3 + 1, chunk) : getSavedLightValue(par4EnumSkyBlock, par1, par2, par3 + 1)) - var7) > var6 && (var6 = var12) >= 14) {
/*  6961 */       return var6;
/*       */     }
/*  6963 */     if ((var12 = ((local_x > 0) ? getSavedLightValueMITE(par4EnumSkyBlock, par1 - 1, par2, par3, chunk) : getSavedLightValue(par4EnumSkyBlock, par1 - 1, par2, par3)) - var7) > var6 && (var6 = var12) >= 14) {
/*  6964 */       return var6;
/*       */     }
/*  6966 */     if ((var12 = ((local_x < 15) ? getSavedLightValueMITE(par4EnumSkyBlock, par1 + 1, par2, par3, chunk) : getSavedLightValue(par4EnumSkyBlock, par1 + 1, par2, par3)) - var7) > var6 && (var6 = var12) >= 14) {
/*  6967 */       return var6;
/*       */     }
/*  6969 */     return var6;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private int computeSkylightValueMITE(int par1, int par2, int par3, Chunk chunk) {
/*  6976 */     if (chunk.canBlockSeeTheSkyForNonEmptyChunk(par1 & 0xF, par2, par3 & 0xF)) {
/*  6977 */       return 15;
/*       */     }
/*  6979 */     int var5 = isWithinBlockBounds(par1, par2, par3) ? chunk.getBlockIDOptimized((par1 & 0xF) + (par3 & 0xF) * 16, par2) : 0;
/*  6980 */     int var7 = Block.lightOpacity[var5];
/*       */     
/*  6982 */     if (var7 < 1) {
/*       */       
/*  6984 */       var7 = 1;
/*       */     }
/*       */     else {
/*       */       
/*  6988 */       if (chunk.getHeightValue(par1 & 0xF, par3 & 0xF) - 1 > par2) {
/*  6989 */         var7++;
/*       */       }
/*  6991 */       if (var7 >= 15)
/*       */       {
/*  6993 */         if (Block.lightValue[var5] > 0) {
/*  6994 */           var7 = 1;
/*       */         } else {
/*  6996 */           return 0;
/*       */         } 
/*       */       }
/*       */     } 
/*  7000 */     int var6 = 0;
/*       */     
/*       */     int var12;
/*  7003 */     if ((var12 = getSavedSkylightValueMITE(par1, par2 - 1, par3, chunk) - var7) > var6 && (var6 = var12) >= 14) {
/*  7004 */       return var6;
/*       */     }
/*  7006 */     if ((var12 = getSavedSkylightValueMITE(par1, par2 + 1, par3, chunk) - var7) > var6 && (var6 = var12) >= 14) {
/*  7007 */       return var6;
/*       */     }
/*  7009 */     int local_z = par3 & 0xF;
/*       */     
/*  7011 */     if ((var12 = ((local_z > 0) ? getSavedSkylightValueMITE(par1, par2, par3 - 1, chunk) : getSavedSkylightValue(par1, par2, par3 - 1)) - var7) > var6 && (var6 = var12) >= 14) {
/*  7012 */       return var6;
/*       */     }
/*  7014 */     if ((var12 = ((local_z < 15) ? getSavedSkylightValueMITE(par1, par2, par3 + 1, chunk) : getSavedSkylightValue(par1, par2, par3 + 1)) - var7) > var6 && (var6 = var12) >= 14) {
/*  7015 */       return var6;
/*       */     }
/*  7017 */     int local_x = par1 & 0xF;
/*       */     
/*  7019 */     if ((var12 = ((local_x > 0) ? getSavedSkylightValueMITE(par1 - 1, par2, par3, chunk) : getSavedSkylightValue(par1 - 1, par2, par3)) - var7) > var6 && (var6 = var12) >= 14) {
/*  7020 */       return var6;
/*       */     }
/*  7022 */     if ((var12 = ((local_x < 15) ? getSavedSkylightValueMITE(par1 + 1, par2, par3, chunk) : getSavedSkylightValue(par1 + 1, par2, par3)) - var7) > var6 && (var6 = var12) >= 14) {
/*  7023 */       return var6;
/*       */     }
/*  7025 */     return var6;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   private int computeBlocklightValueMITE(int par1, int par2, int par3, Chunk chunk) {
/*  7031 */     int var5 = isWithinBlockBounds(par1, par2, par3) ? chunk.getBlockIDOptimized((par1 & 0xF) + (par3 & 0xF) * 16, par2) : 0;
/*       */     
/*  7033 */     int var6 = Block.lightValue[var5];
/*  7034 */     int var7 = Block.lightOpacity[var5];
/*       */     
/*  7036 */     if (var7 < 1) {
/*       */       
/*  7038 */       var7 = 1;
/*       */     }
/*       */     else {
/*       */       
/*  7042 */       if (chunk.getHeightValue(par1 & 0xF, par3 & 0xF) - 1 > par2) {
/*  7043 */         var7++;
/*       */       }
/*  7045 */       if (var7 >= 15)
/*       */       {
/*  7047 */         if (var6 > 0) {
/*  7048 */           var7 = 1;
/*       */         } else {
/*  7050 */           return 0;
/*       */         } 
/*       */       }
/*       */     } 
/*  7054 */     if (var6 >= 14) {
/*  7055 */       return var6;
/*       */     }
/*       */     
/*       */     int var12;
/*  7059 */     if ((var12 = getSavedBlocklightValueMITE(par1, par2 - 1, par3, chunk) - var7) > var6 && (var6 = var12) >= 14) {
/*  7060 */       return var6;
/*       */     }
/*  7062 */     if ((var12 = getSavedBlocklightValueMITE(par1, par2 + 1, par3, chunk) - var7) > var6 && (var6 = var12) >= 14) {
/*  7063 */       return var6;
/*       */     }
/*  7065 */     int local_z = par3 & 0xF;
/*       */     
/*  7067 */     if ((var12 = ((local_z > 0) ? getSavedBlocklightValueMITE(par1, par2, par3 - 1, chunk) : getSavedBlocklightValue(par1, par2, par3 - 1)) - var7) > var6 && (var6 = var12) >= 14) {
/*  7068 */       return var6;
/*       */     }
/*  7070 */     if ((var12 = ((local_z < 15) ? getSavedBlocklightValueMITE(par1, par2, par3 + 1, chunk) : getSavedBlocklightValue(par1, par2, par3 + 1)) - var7) > var6 && (var6 = var12) >= 14) {
/*  7071 */       return var6;
/*       */     }
/*  7073 */     int local_x = par1 & 0xF;
/*       */     
/*  7075 */     if ((var12 = ((local_x > 0) ? getSavedBlocklightValueMITE(par1 - 1, par2, par3, chunk) : getSavedBlocklightValue(par1 - 1, par2, par3)) - var7) > var6 && (var6 = var12) >= 14) {
/*  7076 */       return var6;
/*       */     }
/*  7078 */     if ((var12 = ((local_x < 15) ? getSavedBlocklightValueMITE(par1 + 1, par2, par3, chunk) : getSavedBlocklightValue(par1 + 1, par2, par3)) - var7) > var6 && (var6 = var12) >= 14) {
/*  7079 */       return var6;
/*       */     }
/*  7081 */     return var6;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean canUpdateLightByType(int x, int z) {
/*  7087 */     return doesChunkAndAllNeighborsExist(x >> 4, z >> 4, 1, MITEConstant.includeEmptyChunksForLighting());
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean canUpdateLightByType(int x, int z, Chunk chunk) {
/*  7092 */     if (chunk.isEmpty() && !MITEConstant.includeEmptyChunksForLighting()) {
/*  7093 */       return false;
/*       */     }
/*  7095 */     return chunk.doAllNeighborsExist(1, false, MITEConstant.includeEmptyChunksForLighting());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void updateLightByType(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4, boolean trusted_xz, Chunk chunk) {
/*  7104 */     if (par1EnumSkyBlock == EnumSkyBlock.Sky) {
/*  7105 */       propagateSkylight(par2, par3, par4, trusted_xz, chunk);
/*       */     } else {
/*  7107 */       propagateBlocklight(par2, par3, par4, trusted_xz, chunk);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void propagateSkylight(int par2, int par3, int par4, boolean trusted_xz, Chunk chunk) {
/*  7477 */     if (chunk.isEmpty()) {
/*  7478 */       Debug.setErrorMessage("propagateSkylight: Why called for empty chunk?");
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7485 */     if (!this.decorating && (trusted_xz || canUpdateLightByType(par2, par4, chunk))) {
/*       */ 
/*       */ 
/*       */       
/*  7489 */       int var5 = 0;
/*  7490 */       int var6 = 0;
/*       */       
/*  7492 */       int var7 = getSavedSkylightValueMITE(par2, par3, par4, chunk);
/*  7493 */       int var8 = computeSkylightValueMITE(par2, par3, par4, chunk);
/*       */ 
/*       */ 
/*       */       
/*  7497 */       int par2_minus_32 = par2 - 32;
/*  7498 */       int par3_minus_32 = par3 - 32;
/*  7499 */       int par4_minus_32 = par4 - 32;
/*       */       
/*  7501 */       int minus_par2_plus_32 = -par2 + 32;
/*  7502 */       int minus_par3_plus_32 = -par3 + 32;
/*  7503 */       int minus_par4_plus_32 = -par4 + 32;
/*       */       
/*  7505 */       int pos_max_distance = Math.max(var7, var8) + 1;
/*  7506 */       int neg_max_distance = -pos_max_distance;
/*       */       
/*  7508 */       if (var8 > var7) {
/*       */         
/*  7510 */         this.lightUpdateBlockList[var6++] = 133152;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       }
/*  7518 */       else if (var8 < var7) {
/*       */         
/*  7520 */         this.lightUpdateBlockList[var6++] = 0x20820 | var7 << 18;
/*       */         
/*  7522 */         while (var5 < var6) {
/*       */           
/*  7524 */           int var9 = this.lightUpdateBlockList[var5++];
/*       */           
/*  7526 */           int var10 = (var9 & 0x3F) + par2_minus_32;
/*  7527 */           int var11 = (var9 >> 6 & 0x3F) + par3_minus_32;
/*  7528 */           int var12 = (var9 >> 12 & 0x3F) + par4_minus_32;
/*       */           
/*  7530 */           int var13 = var9 >> 18 & 0xF;
/*       */           
/*  7532 */           boolean same_chunk = chunk.hasCoords(var10 >> 4, var12 >> 4);
/*       */           
/*  7534 */           if ((same_chunk ? getSavedSkylightValueMITE(var10, var11, var12, chunk) : getSavedSkylightValue(var10, var11, var12)) == var13) {
/*       */             
/*  7536 */             if (same_chunk) {
/*  7537 */               setSkylightValueMITE(var10, var11, var12, 0, chunk);
/*       */             } else {
/*  7539 */               setSkylightValue(var10, var11, var12, 0);
/*       */             } 
/*  7541 */             if (var13 > 0) {
/*       */               
/*  7543 */               int var15 = var10 - par2;
/*  7544 */               int var16 = var11 - par3;
/*  7545 */               int var17 = var12 - par4;
/*       */ 
/*       */               
/*  7548 */               if (var15 >= neg_max_distance && var15 <= pos_max_distance && var16 >= neg_max_distance && var16 <= pos_max_distance && var17 >= neg_max_distance && var17 <= pos_max_distance)
/*       */               {
/*  7550 */                 for (int var18 = 0; var18 < 6; var18++) {
/*       */                   
/*  7552 */                   int var19 = var10 + Facing.offsetsXForSide[var18];
/*  7553 */                   int var20 = var11 + Facing.offsetsYForSide[var18];
/*  7554 */                   int var21 = var12 + Facing.offsetsZForSide[var18];
/*       */                   
/*  7556 */                   int opacity = Block.lightOpacity[getBlockId(var19, var20, var21)];
/*       */                   
/*  7558 */                   if (opacity == 0 || getHeightValue(var19, var21) - 1 > var20) {
/*  7559 */                     opacity++;
/*       */                   }
/*  7561 */                   Chunk chunk2 = chunk.hasCoords(var19 >> 4, var21 >> 4) ? chunk : getChunkFromBlockCoords(var19, var21);
/*       */                   
/*  7563 */                   if (chunk2.isEmpty()) {
/*  7564 */                     Debug.setErrorMessage("You need to handle an empty chunk");
/*       */                   }
/*  7566 */                   if (getSavedSkylightValueMITE(var19, var20, var21, chunk2) == var13 - opacity) {
/*  7567 */                     this.lightUpdateBlockList[var6++] = var19 + minus_par2_plus_32 | var20 + minus_par3_plus_32 << 6 | var21 + minus_par4_plus_32 << 12 | var13 - opacity << 18;
/*       */                   }
/*       */                 } 
/*       */               }
/*       */             } 
/*       */           } 
/*       */         } 
/*  7574 */         var5 = 0;
/*       */       } 
/*       */       
/*  7577 */       while (var5 < var6) {
/*       */         
/*  7579 */         int var13, var14, var9 = this.lightUpdateBlockList[var5++];
/*       */         
/*  7581 */         int var10 = (var9 & 0x3F) + par2_minus_32;
/*  7582 */         int var11 = (var9 >> 6 & 0x3F) + par3_minus_32;
/*  7583 */         int var12 = (var9 >> 12 & 0x3F) + par4_minus_32;
/*       */         
/*  7585 */         boolean same_chunk = chunk.hasCoords(var10 >> 4, var12 >> 4);
/*       */         
/*  7587 */         if (same_chunk) {
/*       */           
/*  7589 */           var13 = getSavedSkylightValueMITE(var10, var11, var12, chunk);
/*  7590 */           var14 = computeSkylightValueMITE(var10, var11, var12, chunk);
/*       */         }
/*       */         else {
/*       */           
/*  7594 */           var13 = getSavedSkylightValue(var10, var11, var12);
/*  7595 */           var14 = computeLightValue(var10, var11, var12, EnumSkyBlock.Sky);
/*       */         } 
/*       */         
/*  7598 */         if (var14 != var13) {
/*       */           
/*  7600 */           if (same_chunk) {
/*  7601 */             setSkylightValueMITE(var10, var11, var12, var14, chunk);
/*       */           } else {
/*  7603 */             setSkylightValue(var10, var11, var12, var14);
/*       */           } 
/*  7605 */           if (var14 > var13) {
/*       */             
/*  7607 */             int var15 = var10 - par2;
/*  7608 */             int var16 = var11 - par3;
/*  7609 */             int var17 = var12 - par4;
/*       */             
/*  7611 */             boolean var23 = (var6 < this.lightUpdateBlockList.length - 6);
/*       */ 
/*       */             
/*  7614 */             if (var15 > -18 && var15 < 18 && var16 > -18 && var16 < 18 && var17 > -18 && var17 < 18 && var23)
/*       */             {
/*       */ 
/*       */               
/*  7618 */               int local_x = var10 & 0xF;
/*  7619 */               int local_z = var12 & 0xF;
/*       */               
/*  7621 */               if (local_x > 0 && local_x < 15 && local_z > 0 && local_z < 15) {
/*       */                 
/*  7623 */                 Chunk chunk2 = chunk.hasCoords(var10 >> 4, var12 >> 4) ? chunk : getChunkFromBlockCoords(var10, var12);
/*       */                 
/*  7625 */                 if (chunk2.isEmpty()) {
/*  7626 */                   Debug.setErrorMessage("updateLightByType: chunk was empty");
/*       */                 }
/*  7628 */                 int i = var10 - 1, j = var11, k = var12;
/*       */                 
/*  7630 */                 if (getSavedSkylightValueMITE(i, j, k, chunk2) < var14) {
/*  7631 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7633 */                 i = var10 + 1; j = var11; k = var12;
/*       */                 
/*  7635 */                 if (getSavedSkylightValueMITE(i, j, k, chunk2) < var14) {
/*  7636 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7638 */                 i = var10; j = var11 - 1; k = var12;
/*       */                 
/*  7640 */                 if (getSavedSkylightValueMITE(i, j, k, chunk2) < var14) {
/*  7641 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7643 */                 i = var10; j = var11 + 1; k = var12;
/*       */                 
/*  7645 */                 if (getSavedSkylightValueMITE(i, j, k, chunk2) < var14) {
/*  7646 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7648 */                 i = var10; j = var11; k = var12 - 1;
/*       */                 
/*  7650 */                 if (getSavedSkylightValueMITE(i, j, k, chunk2) < var14) {
/*  7651 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7653 */                 i = var10; j = var11; k = var12 + 1;
/*       */                 
/*  7655 */                 if (getSavedSkylightValueMITE(i, j, k, chunk2) < var14) {
/*  7656 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*       */                 continue;
/*       */               } 
/*  7660 */               int x = var10 - 1, y = var11, z = var12;
/*       */               
/*  7662 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedSkylightValueMITE(x, y, z, chunk) : getSavedSkylightValue(x, y, z)) < var14) {
/*  7663 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7665 */               x = var10 + 1; y = var11; z = var12;
/*       */               
/*  7667 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedSkylightValueMITE(x, y, z, chunk) : getSavedSkylightValue(x, y, z)) < var14) {
/*  7668 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7670 */               x = var10; y = var11 - 1; z = var12;
/*       */               
/*  7672 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedSkylightValueMITE(x, y, z, chunk) : getSavedSkylightValue(x, y, z)) < var14) {
/*  7673 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7675 */               x = var10; y = var11 + 1; z = var12;
/*       */               
/*  7677 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedSkylightValueMITE(x, y, z, chunk) : getSavedSkylightValue(x, y, z)) < var14) {
/*  7678 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7680 */               x = var10; y = var11; z = var12 - 1;
/*       */               
/*  7682 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedSkylightValueMITE(x, y, z, chunk) : getSavedSkylightValue(x, y, z)) < var14) {
/*  7683 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7685 */               x = var10; y = var11; z = var12 + 1;
/*       */               
/*  7687 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedSkylightValueMITE(x, y, z, chunk) : getSavedSkylightValue(x, y, z)) < var14) {
/*  7688 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*       */             }
/*       */           
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } else {
/*       */       
/*  7697 */       chunk.addPendingSkylightUpdate(par2, par3, par4);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public void propagateBlocklight(int par2, int par3, int par4, boolean trusted_xz, Chunk chunk) {
/*  7704 */     if (chunk.isEmpty()) {
/*  7705 */       Debug.setErrorMessage("propagateBlocklight: Why called for empty chunk?");
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7712 */     if (!this.decorating && (trusted_xz || canUpdateLightByType(par2, par4, chunk))) {
/*       */ 
/*       */ 
/*       */       
/*  7716 */       int var5 = 0;
/*  7717 */       int var6 = 0;
/*       */       
/*  7719 */       int var7 = getSavedBlocklightValueMITE(par2, par3, par4, chunk);
/*  7720 */       int var8 = computeBlocklightValueMITE(par2, par3, par4, chunk);
/*       */ 
/*       */ 
/*       */       
/*  7724 */       int par2_minus_32 = par2 - 32;
/*  7725 */       int par3_minus_32 = par3 - 32;
/*  7726 */       int par4_minus_32 = par4 - 32;
/*       */       
/*  7728 */       int minus_par2_plus_32 = -par2 + 32;
/*  7729 */       int minus_par3_plus_32 = -par3 + 32;
/*  7730 */       int minus_par4_plus_32 = -par4 + 32;
/*       */       
/*  7732 */       int pos_max_distance = Math.max(var7, var8) + 1;
/*  7733 */       int neg_max_distance = -pos_max_distance;
/*       */       
/*  7735 */       if (var8 > var7) {
/*       */         
/*  7737 */         this.lightUpdateBlockList[var6++] = 133152;
/*       */ 
/*       */ 
/*       */       
/*       */       }
/*       */       else {
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7747 */         this.lightUpdateBlockList[var6++] = 0x20820 | var7 << 18;
/*       */         
/*  7749 */         while (var5 < var6) {
/*       */           
/*  7751 */           int var9 = this.lightUpdateBlockList[var5++];
/*       */           
/*  7753 */           int var10 = (var9 & 0x3F) + par2_minus_32;
/*  7754 */           int var11 = (var9 >> 6 & 0x3F) + par3_minus_32;
/*  7755 */           int var12 = (var9 >> 12 & 0x3F) + par4_minus_32;
/*       */           
/*  7757 */           int var13 = var9 >> 18 & 0xF;
/*       */           
/*  7759 */           boolean same_chunk = chunk.hasCoords(var10 >> 4, var12 >> 4);
/*       */           
/*  7761 */           if ((same_chunk ? getSavedBlocklightValueMITE(var10, var11, var12, chunk) : getSavedBlocklightValue(var10, var11, var12)) == var13) {
/*       */             
/*  7763 */             if (same_chunk) {
/*  7764 */               setBlocklightValueMITE(var10, var11, var12, 0, chunk);
/*       */             } else {
/*  7766 */               setBlocklightValue(var10, var11, var12, 0);
/*       */             } 
/*  7768 */             if (var13 > 0) {
/*       */               
/*  7770 */               int var15 = var10 - par2;
/*  7771 */               int var16 = var11 - par3;
/*  7772 */               int var17 = var12 - par4;
/*       */               
/*  7774 */               if (var15 < 0) {
/*  7775 */                 var15 = -var15;
/*       */               }
/*  7777 */               if (var16 < 0) {
/*  7778 */                 var16 = -var16;
/*       */               }
/*  7780 */               if (var17 < 0) {
/*  7781 */                 var17 = -var17;
/*       */               }
/*       */               
/*  7784 */               if (var15 + var16 + var17 <= pos_max_distance)
/*       */               {
/*  7786 */                 for (int var18 = 0; var18 < 6; var18++) {
/*       */                   
/*  7788 */                   int var19 = var10 + Facing.offsetsXForSide[var18];
/*  7789 */                   int var20 = var11 + Facing.offsetsYForSide[var18];
/*  7790 */                   int var21 = var12 + Facing.offsetsZForSide[var18];
/*       */                   
/*  7792 */                   int opacity = Block.lightOpacity[getBlockId(var19, var20, var21)];
/*       */                   
/*  7794 */                   if (opacity == 0 || getHeightValue(var19, var21) - 1 > var20) {
/*  7795 */                     opacity++;
/*       */                   }
/*  7797 */                   Chunk chunk2 = chunk.hasCoords(var19 >> 4, var21 >> 4) ? chunk : getChunkFromBlockCoords(var19, var21);
/*       */                   
/*  7799 */                   if (chunk2.isEmpty()) {
/*  7800 */                     Debug.setErrorMessage("You need to handle an empty chunk");
/*       */                   }
/*  7802 */                   if (getSavedBlocklightValueMITE(var19, var20, var21, chunk2) == var13 - opacity) {
/*  7803 */                     this.lightUpdateBlockList[var6++] = var19 + minus_par2_plus_32 | var20 + minus_par3_plus_32 << 6 | var21 + minus_par4_plus_32 << 12 | var13 - opacity << 18;
/*       */                   }
/*       */                 } 
/*       */               }
/*       */             } 
/*       */           } 
/*       */         } 
/*  7810 */         var5 = 0;
/*       */       } 
/*       */       
/*  7813 */       while (var5 < var6) {
/*       */         
/*  7815 */         int var13, var14, var9 = this.lightUpdateBlockList[var5++];
/*       */         
/*  7817 */         int var10 = (var9 & 0x3F) + par2_minus_32;
/*  7818 */         int var11 = (var9 >> 6 & 0x3F) + par3_minus_32;
/*  7819 */         int var12 = (var9 >> 12 & 0x3F) + par4_minus_32;
/*       */         
/*  7821 */         boolean same_chunk = chunk.hasCoords(var10 >> 4, var12 >> 4);
/*       */         
/*  7823 */         if (same_chunk) {
/*       */           
/*  7825 */           var13 = getSavedBlocklightValueMITE(var10, var11, var12, chunk);
/*  7826 */           var14 = computeBlocklightValueMITE(var10, var11, var12, chunk);
/*       */         }
/*       */         else {
/*       */           
/*  7830 */           var13 = getSavedBlocklightValue(var10, var11, var12);
/*  7831 */           var14 = computeLightValue(var10, var11, var12, EnumSkyBlock.Block);
/*       */         } 
/*       */         
/*  7834 */         if (var14 != var13) {
/*       */           
/*  7836 */           if (same_chunk) {
/*  7837 */             setBlocklightValueMITE(var10, var11, var12, var14, chunk);
/*       */           } else {
/*  7839 */             setBlocklightValue(var10, var11, var12, var14);
/*       */           } 
/*  7841 */           if (var6 >= this.lightUpdateBlockList.length - 6) {
/*       */             continue;
/*       */           }
/*  7844 */           if (var14 > var13) {
/*       */             
/*  7846 */             int var15 = var10 - par2;
/*  7847 */             int var16 = var11 - par3;
/*  7848 */             int var17 = var12 - par4;
/*       */ 
/*       */ 
/*       */             
/*  7852 */             if (var15 < 0) {
/*  7853 */               var15 = -var15;
/*       */             }
/*  7855 */             if (var16 < 0) {
/*  7856 */               var16 = -var16;
/*       */             }
/*  7858 */             if (var17 < 0) {
/*  7859 */               var17 = -var17;
/*       */             }
/*       */             
/*  7862 */             if (var15 + var16 + var17 <= pos_max_distance)
/*       */             {
/*       */ 
/*       */               
/*  7866 */               int local_x = var10 & 0xF;
/*  7867 */               int local_z = var12 & 0xF;
/*       */               
/*  7869 */               if (local_x > 0 && local_x < 15 && local_z > 0 && local_z < 15) {
/*       */                 
/*  7871 */                 Chunk chunk2 = chunk.hasCoords(var10 >> 4, var12 >> 4) ? chunk : getChunkFromBlockCoords(var10, var12);
/*       */                 
/*  7873 */                 if (chunk2.isEmpty()) {
/*  7874 */                   Debug.setErrorMessage("updateLightByType: chunk was empty");
/*       */                 }
/*  7876 */                 int i = var10 - 1, j = var11, k = var12;
/*       */                 
/*  7878 */                 if (getSavedBlocklightValueMITE(i, j, k, chunk2) < var14) {
/*  7879 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7881 */                 i = var10 + 1; j = var11; k = var12;
/*       */                 
/*  7883 */                 if (getSavedBlocklightValueMITE(i, j, k, chunk2) < var14) {
/*  7884 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7886 */                 i = var10; j = var11 - 1; k = var12;
/*       */                 
/*  7888 */                 if (getSavedBlocklightValueMITE(i, j, k, chunk2) < var14) {
/*  7889 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7891 */                 i = var10; j = var11 + 1; k = var12;
/*       */                 
/*  7893 */                 if (getSavedBlocklightValueMITE(i, j, k, chunk2) < var14) {
/*  7894 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7896 */                 i = var10; j = var11; k = var12 - 1;
/*       */                 
/*  7898 */                 if (getSavedBlocklightValueMITE(i, j, k, chunk2) < var14) {
/*  7899 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*  7901 */                 i = var10; j = var11; k = var12 + 1;
/*       */                 
/*  7903 */                 if (getSavedBlocklightValueMITE(i, j, k, chunk2) < var14) {
/*  7904 */                   this.lightUpdateBlockList[var6++] = i + minus_par2_plus_32 + (j + minus_par3_plus_32 << 6) + (k + minus_par4_plus_32 << 12);
/*       */                 }
/*       */                 continue;
/*       */               } 
/*  7908 */               int x = var10 - 1, y = var11, z = var12;
/*       */               
/*  7910 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedBlocklightValueMITE(x, y, z, chunk) : getSavedBlocklightValue(x, y, z)) < var14) {
/*  7911 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7913 */               x = var10 + 1; y = var11; z = var12;
/*       */               
/*  7915 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedBlocklightValueMITE(x, y, z, chunk) : getSavedBlocklightValue(x, y, z)) < var14) {
/*  7916 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7918 */               x = var10; y = var11 - 1; z = var12;
/*       */               
/*  7920 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedBlocklightValueMITE(x, y, z, chunk) : getSavedBlocklightValue(x, y, z)) < var14) {
/*  7921 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7923 */               x = var10; y = var11 + 1; z = var12;
/*       */               
/*  7925 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedBlocklightValueMITE(x, y, z, chunk) : getSavedBlocklightValue(x, y, z)) < var14) {
/*  7926 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7928 */               x = var10; y = var11; z = var12 - 1;
/*       */               
/*  7930 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedBlocklightValueMITE(x, y, z, chunk) : getSavedBlocklightValue(x, y, z)) < var14) {
/*  7931 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*  7933 */               x = var10; y = var11; z = var12 + 1;
/*       */               
/*  7935 */               if ((chunk.hasCoords(x >> 4, z >> 4) ? getSavedBlocklightValueMITE(x, y, z, chunk) : getSavedBlocklightValue(x, y, z)) < var14) {
/*  7936 */                 this.lightUpdateBlockList[var6++] = x + minus_par2_plus_32 + (y + minus_par3_plus_32 << 6) + (z + minus_par4_plus_32 << 12);
/*       */               }
/*       */             }
/*       */           
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } else {
/*       */       
/*  7945 */       chunk.addPendingBlocklightUpdate(par2, par3, par4);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean tickUpdates(boolean par1) {
/*  7954 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public List getPendingBlockUpdates(Chunk par1Chunk, boolean par2) {
/*  7959 */     return null;
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean occupiedByLivingEntity(int x, int y, int z) {
/*  7964 */     List entities = getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getAABBPool().getAABB(x, y, z, (x + 1), (y + 1), (z + 1)));
/*       */     
/*  7966 */     return (entities != null && entities.size() > 0);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public List getPredatorsWithinAABBForEntity(Entity prey, AxisAlignedBB bounding_box) {
/*  7979 */     List entities = getEntitiesWithinAABBExcludingEntity(prey, bounding_box);
/*  7980 */     List<Entity> predators = new ArrayList();
/*       */     
/*  7982 */     Iterator<Entity> i = entities.iterator();
/*       */     
/*  7984 */     while (i.hasNext()) {
/*       */       
/*  7986 */       Entity entity = i.next();
/*       */       
/*  7988 */       if (entity instanceof EntityLiving && ((EntityLiving)entity).preysUpon(prey)) {
/*  7989 */         predators.add(entity);
/*       */       }
/*       */     } 
/*  7992 */     return predators;
/*       */   }
/*       */ 
/*       */   
/*       */   public List getFoodItemEntitiesWithinAABBForLivingEntity(EntityLiving entity_living, AxisAlignedBB bounding_box) {
/*  7997 */     List entities = getEntitiesWithinAABB(EntityItem.class, bounding_box);
/*  7998 */     List<EntityItem> food_item_entities = new ArrayList();
/*       */     
/*  8000 */     Iterator<EntityItem> i = entities.iterator();
/*       */     
/*  8002 */     while (i.hasNext()) {
/*       */       
/*  8004 */       EntityItem entity_item = i.next();
/*       */       
/*  8006 */       if (entity_living.isFoodItem(entity_item.getEntityItem())) {
/*  8007 */         food_item_entities.add(entity_item);
/*       */       }
/*       */     } 
/*  8010 */     return food_item_entities;
/*       */   }
/*       */ 
/*       */   
/*       */   public List getRepairItemEntitiesWithinAABBForLivingEntity(EntityLiving entity_living, AxisAlignedBB bounding_box) {
/*  8015 */     List entities = getEntitiesWithinAABB(EntityItem.class, bounding_box);
/*  8016 */     List<EntityItem> repair_item_entities = new ArrayList();
/*       */     
/*  8018 */     Iterator<EntityItem> i = entities.iterator();
/*       */     
/*  8020 */     while (i.hasNext()) {
/*       */       
/*  8022 */       EntityItem entity_item = i.next();
/*       */       
/*  8024 */       if (entity_living.isRepairItem(entity_item.getEntityItem())) {
/*  8025 */         repair_item_entities.add(entity_item);
/*       */       }
/*       */     } 
/*  8028 */     return repair_item_entities;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public List getEntitiesWithinAABBExcludingEntity(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB) {
/*  8036 */     return getEntitiesWithinAABBExcludingEntity(par1Entity, par2AxisAlignedBB, (IEntitySelector)null);
/*       */   }
/*       */ 
/*       */   
/*       */   public List getEntitiesWithinAABBExcludingEntity(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB, IEntitySelector par3IEntitySelector) {
/*  8041 */     ArrayList var4 = new ArrayList();
/*  8042 */     int var5 = MathHelper.floor_double((par2AxisAlignedBB.minX - 2.0D) / 16.0D);
/*  8043 */     int var6 = MathHelper.floor_double((par2AxisAlignedBB.maxX + 2.0D) / 16.0D);
/*  8044 */     int var7 = MathHelper.floor_double((par2AxisAlignedBB.minZ - 2.0D) / 16.0D);
/*  8045 */     int var8 = MathHelper.floor_double((par2AxisAlignedBB.maxZ + 2.0D) / 16.0D);
/*       */     
/*  8047 */     for (int var9 = var5; var9 <= var6; var9++) {
/*       */       
/*  8049 */       for (int var10 = var7; var10 <= var8; var10++) {
/*       */         
/*  8051 */         if (chunkExists(var9, var10))
/*       */         {
/*  8053 */           getChunkFromChunkCoords(var9, var10).getEntitiesWithinAABBForEntity(par1Entity, par2AxisAlignedBB, var4, par3IEntitySelector);
/*       */         }
/*       */       } 
/*       */     } 
/*       */     
/*  8058 */     return var4;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public List getEntitiesWithinAABB(Class par1Class, AxisAlignedBB par2AxisAlignedBB) {
/*  8066 */     return selectEntitiesWithinAABB(par1Class, par2AxisAlignedBB, (IEntitySelector)null);
/*       */   }
/*       */ 
/*       */   
/*       */   public List selectEntitiesWithinAABB(Class par1Class, AxisAlignedBB par2AxisAlignedBB, IEntitySelector par3IEntitySelector) {
/*  8071 */     int var4 = MathHelper.floor_double((par2AxisAlignedBB.minX - 2.0D) / 16.0D);
/*  8072 */     int var5 = MathHelper.floor_double((par2AxisAlignedBB.maxX + 2.0D) / 16.0D);
/*  8073 */     int var6 = MathHelper.floor_double((par2AxisAlignedBB.minZ - 2.0D) / 16.0D);
/*  8074 */     int var7 = MathHelper.floor_double((par2AxisAlignedBB.maxZ + 2.0D) / 16.0D);
/*  8075 */     ArrayList var8 = new ArrayList();
/*       */     
/*  8077 */     for (int var9 = var4; var9 <= var5; var9++) {
/*       */       
/*  8079 */       for (int var10 = var6; var10 <= var7; var10++) {
/*       */         
/*  8081 */         if (chunkExists(var9, var10))
/*       */         {
/*  8083 */           getChunkFromChunkCoords(var9, var10).getEntitiesOfTypeWithinAAAB(par1Class, par2AxisAlignedBB, var8, par3IEntitySelector);
/*       */         }
/*       */       } 
/*       */     } 
/*       */     
/*  8088 */     return var8;
/*       */   }
/*       */ 
/*       */   
/*       */   public Entity findNearestEntityWithinAABB(Class par1Class, AxisAlignedBB par2AxisAlignedBB, Entity par3Entity) {
/*  8093 */     List<Entity> var4 = getEntitiesWithinAABB(par1Class, par2AxisAlignedBB);
/*  8094 */     Entity var5 = null;
/*  8095 */     double var6 = Double.MAX_VALUE;
/*       */     
/*  8097 */     for (int var8 = 0; var8 < var4.size(); var8++) {
/*       */       
/*  8099 */       Entity var9 = var4.get(var8);
/*       */       
/*  8101 */       if (var9 != par3Entity) {
/*       */         
/*  8103 */         double var10 = par3Entity.getDistanceSqToEntity(var9);
/*       */         
/*  8105 */         if (var10 <= var6) {
/*       */           
/*  8107 */           var5 = var9;
/*  8108 */           var6 = var10;
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  8113 */     return var5;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public Entity findNearestSeenEntityWithinAABB(Class par1Class, AxisAlignedBB par2AxisAlignedBB, Entity par3Entity, EntitySenses entity_senses) {
/*  8119 */     List<Entity> var4 = getEntitiesWithinAABB(par1Class, par2AxisAlignedBB);
/*  8120 */     Entity var5 = null;
/*  8121 */     double var6 = Double.MAX_VALUE;
/*       */     
/*  8123 */     for (int var8 = 0; var8 < var4.size(); var8++) {
/*       */       
/*  8125 */       Entity var9 = var4.get(var8);
/*       */       
/*  8127 */       if (!var9.isDead && (!var9.isEntityLivingBase() || var9.getAsEntityLivingBase().getHealth() > 0.0F))
/*       */       {
/*       */         
/*  8130 */         if (var9 != par3Entity && entity_senses.canSee(var9)) {
/*       */           
/*  8132 */           double var10 = par3Entity.getDistanceSqToEntity(var9);
/*       */           
/*  8134 */           if (var10 <= var6) {
/*       */             
/*  8136 */             var5 = var9;
/*  8137 */             var6 = var10;
/*       */           } 
/*       */         } 
/*       */       }
/*       */     } 
/*  8142 */     return var5;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public abstract Entity getEntityByID(int paramInt);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public List getLoadedEntityList() {
/*  8156 */     return this.loadedEntityList;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void markTileEntityChunkModified(int par1, int par2, int par3, TileEntity par4TileEntity) {
/*  8165 */     if (blockExists(par1, par2, par3))
/*       */     {
/*  8167 */       getChunkFromBlockCoords(par1, par3).setChunkModified();
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int countEntities(Class par1Class) {
/*  8176 */     int var2 = 0;
/*       */     
/*  8178 */     for (int var3 = 0; var3 < this.loadedEntityList.size(); var3++) {
/*       */       
/*  8180 */       Entity var4 = this.loadedEntityList.get(var3);
/*       */       
/*  8182 */       if ((!(var4 instanceof EntityLiving) || !((EntityLiving)var4).isNoDespawnRequired()) && par1Class.isAssignableFrom(var4.getClass()))
/*       */       {
/*  8184 */         var2++;
/*       */       }
/*       */     } 
/*       */     
/*  8188 */     return var2;
/*       */   }
/*       */ 
/*       */   
/*       */   public int countMobs(boolean include_mobs_below_height_of_60, boolean include_mobs_at_height_of_60_or_higher) {
/*  8193 */     int count = 0;
/*       */     
/*  8195 */     for (int i = 0; i < this.loadedEntityList.size(); i++) {
/*       */       
/*  8197 */       Entity entity = this.loadedEntityList.get(i);
/*       */       
/*  8199 */       if (entity instanceof IMob)
/*       */       {
/*  8201 */         if (entity.getBlockPosY() < 60) {
/*       */           
/*  8203 */           if (include_mobs_below_height_of_60) {
/*  8204 */             count++;
/*       */           
/*       */           }
/*       */         }
/*  8208 */         else if (include_mobs_at_height_of_60_or_higher) {
/*  8209 */           count++;
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/*  8214 */     return count;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final Entity getEntityWithSameClassAndUUIDInLoadedEntityList(Entity entity, boolean report_error_on_object_match) {
/*  8221 */     Iterator<Entity> i = this.loadedEntityList.iterator();
/*       */     
/*  8223 */     while (i.hasNext()) {
/*       */       
/*  8225 */       Entity loaded_entity = i.next();
/*       */       
/*  8227 */       if (loaded_entity == entity) {
/*       */         
/*  8229 */         if (report_error_on_object_match) {
/*  8230 */           System.out.println("getEntityWithSameClassAndUUIDInLoadedEntityList: object match!");
/*       */         }
/*  8232 */         return loaded_entity;
/*       */       } 
/*       */       
/*  8235 */       if (loaded_entity.getClass() == entity.getClass() && loaded_entity.getUniqueID().equals(entity.getUniqueID()))
/*       */       {
/*  8237 */         return loaded_entity;
/*       */       }
/*       */     } 
/*       */     
/*  8241 */     return null;
/*       */   }
/*       */ 
/*       */   
/*       */   public static final Entity getEntityWithSameClassAndUUIDInEntityList(String name_of_calling_function, Entity entity, List entity_list, boolean report_error_on_object_match) {
/*  8246 */     Iterator<Entity> i = entity_list.iterator();
/*       */     
/*  8248 */     while (i.hasNext()) {
/*       */       
/*  8250 */       Entity entity_in_list = i.next();
/*       */       
/*  8252 */       if (entity_in_list == entity) {
/*       */         
/*  8254 */         if (report_error_on_object_match) {
/*  8255 */           System.out.println(name_of_calling_function + ": object match!");
/*       */         }
/*  8257 */         return entity_in_list;
/*       */       } 
/*       */       
/*  8260 */       if (entity_in_list.getClass() == entity.getClass() && entity_in_list.getUniqueID().equals(entity.getUniqueID())) {
/*  8261 */         return entity_in_list;
/*       */       }
/*       */     } 
/*  8264 */     return null;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isEntityObjectInLoadedEntityList(Entity entity) {
/*  8269 */     return this.loadedEntityList.contains(entity);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isEntityWithSameClassAndUUIDInUnloadedEntityList(Entity entity) {
/*  8275 */     Iterator<Entity> i = this.unloadedEntityList.iterator();
/*       */     
/*  8277 */     while (i.hasNext()) {
/*       */       
/*  8279 */       Entity unloaded_entity = i.next();
/*       */       
/*  8281 */       if (unloaded_entity == entity) {
/*  8282 */         return true;
/*       */       }
/*  8284 */       if (unloaded_entity.getClass() == entity.getClass() && unloaded_entity.getUniqueID().equals(entity.getUniqueID())) {
/*  8285 */         return true;
/*       */       }
/*       */     } 
/*  8288 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isEntityObjectInUnloadedEntityList(Entity entity) {
/*  8293 */     return this.unloadedEntityList.contains(entity);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void addLoadedEntities(List<Entity> par1List) {
/*  8301 */     this.loadedEntityList.addAll(par1List);
/*       */     
/*  8303 */     for (int var2 = 0; var2 < par1List.size(); var2++)
/*       */     {
/*  8305 */       onEntityAdded(par1List.get(var2));
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void unloadEntities(List par1List) {
/*  8314 */     this.unloadedEntityList.addAll(par1List);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public PathEntity getPathEntityToEntity(Entity par1Entity, Entity par2Entity, float max_path_length, boolean can_pass_open_wooden_doors, boolean can_path_through_closed_wooden_doors, boolean avoid_water, boolean can_entity_swim) {
/*  8393 */     if (max_path_length > 32.0F) {
/*  8394 */       max_path_length = 32.0F;
/*       */     }
/*  8396 */     this.theProfiler.startSection("pathfind");
/*  8397 */     int var8 = MathHelper.floor_double(par1Entity.posX);
/*  8398 */     int var9 = MathHelper.floor_double(par1Entity.posY + 1.0D);
/*  8399 */     int var10 = MathHelper.floor_double(par1Entity.posZ);
/*  8400 */     int var11 = (int)(max_path_length + 16.0F);
/*  8401 */     int var12 = var8 - var11;
/*  8402 */     int var13 = var9 - var11;
/*  8403 */     int var14 = var10 - var11;
/*  8404 */     int var15 = var8 + var11;
/*  8405 */     int var16 = var9 + var11;
/*  8406 */     int var17 = var10 + var11;
/*  8407 */     ChunkCache var18 = new ChunkCache(this, var12, var13, var14, var15, var16, var17, 0);
/*  8408 */     PathEntity var19 = (new PathFinder(var18, can_pass_open_wooden_doors, can_path_through_closed_wooden_doors, avoid_water, can_entity_swim)).createEntityPathTo(par1Entity, par2Entity, max_path_length);
/*  8409 */     this.theProfiler.endSection();
/*  8410 */     return var19;
/*       */   }
/*       */ 
/*       */   
/*       */   protected PathEntity findEntityPathTowardXYZ(Entity entity, int x, int y, int z, int max_path_length, boolean use_navigator) {
/*  8415 */     int entity_x = MathHelper.floor_double(entity.posX);
/*  8416 */     int entity_y = MathHelper.floor_double(entity.posY);
/*  8417 */     int entity_z = MathHelper.floor_double(entity.posZ);
/*       */     
/*  8419 */     double shortest_distance_to_xyz_sq = getDistanceSqFromDeltas((entity_x - x), (entity_y - y), (entity_z - z));
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  8425 */     PathEntity selected_path = null;
/*       */     
/*  8427 */     int random_number_index = this.rand.nextInt();
/*       */     
/*  8429 */     for (int attempt = 0; attempt < 16; attempt++) {
/*       */       
/*  8431 */       int dx = RNG.int_max[++random_number_index & 0x7FFF] % (max_path_length * 2 + 1) - max_path_length;
/*  8432 */       int dy = RNG.int_7_minus_3[++random_number_index & 0x7FFF];
/*  8433 */       int dz = RNG.int_max[++random_number_index & 0x7FFF] % (max_path_length * 2 + 1) - max_path_length;
/*       */       
/*  8435 */       int trial_x = entity_x + dx;
/*  8436 */       int trial_y = entity_y + dy;
/*  8437 */       int trial_z = entity_z + dz;
/*       */       int i;
/*  8439 */       for (i = 0; i < 8;) {
/*       */         
/*  8441 */         if (isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, false)) {
/*  8442 */           trial_y--;
/*       */           
/*       */           i++;
/*       */         } 
/*       */       } 
/*  8447 */       for (i = 0; i < 8;) {
/*       */         
/*  8449 */         if (!isAirOrPassableBlock(trial_x, trial_y, trial_z, false)) {
/*  8450 */           trial_y++;
/*       */           
/*       */           i++;
/*       */         } 
/*       */       } 
/*  8455 */       double distance_to_xyz_sq = getDistanceSqFromDeltas((trial_x - x), (trial_y - y), (trial_z - z));
/*       */       
/*  8457 */       if (distance_to_xyz_sq < shortest_distance_to_xyz_sq) {
/*       */         PathEntity path;
/*       */ 
/*       */         
/*  8461 */         if (use_navigator && entity instanceof EntityLiving) {
/*  8462 */           path = ((EntityLiving)entity).getNavigator().getPathToXYZ(trial_x, trial_y, trial_z, max_path_length);
/*       */         } else {
/*  8464 */           path = getEntityPathToXYZ(entity, trial_x, trial_y, trial_z, max_path_length, true, false, false, true);
/*       */         } 
/*  8466 */         if (path != null) {
/*       */           
/*  8468 */           PathPoint final_point = path.getFinalPathPoint();
/*       */           
/*  8470 */           distance_to_xyz_sq = getDistanceSqFromDeltas((final_point.xCoord - x), (final_point.yCoord - y), (final_point.zCoord - z));
/*       */           
/*  8472 */           if (distance_to_xyz_sq < shortest_distance_to_xyz_sq) {
/*       */             
/*  8474 */             shortest_distance_to_xyz_sq = distance_to_xyz_sq;
/*  8475 */             selected_path = path;
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  8481 */     return selected_path;
/*       */   }
/*       */ 
/*       */   
/*       */   protected PathEntity findEntityPathAwayFromXYZ(Entity entity, int x, int y, int z, int min_distance, int max_path_length, boolean use_navigator) {
/*  8486 */     int entity_x = MathHelper.floor_double(entity.posX);
/*  8487 */     int entity_y = MathHelper.floor_double(entity.posY);
/*  8488 */     int entity_z = MathHelper.floor_double(entity.posZ);
/*       */     
/*  8490 */     int min_distance_sq = min_distance * min_distance;
/*       */     
/*  8492 */     double furthest_distance_from_xyz_sq = getDistanceSqFromDeltas((entity_x - x), (entity_y - y), (entity_z - z));
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  8498 */     PathEntity selected_path = null;
/*       */     
/*  8500 */     int random_number_index = this.rand.nextInt();
/*       */     
/*  8502 */     for (int attempt = 0; attempt < 16; attempt++) {
/*       */       
/*  8504 */       int dx = RNG.int_max[++random_number_index & 0x7FFF] % (max_path_length * 2 + 1) - max_path_length;
/*  8505 */       int dy = RNG.int_7_minus_3[++random_number_index & 0x7FFF];
/*  8506 */       int dz = RNG.int_max[++random_number_index & 0x7FFF] % (max_path_length * 2 + 1) - max_path_length;
/*       */       
/*  8508 */       int trial_x = entity_x + dx;
/*  8509 */       int trial_y = entity_y + dy;
/*  8510 */       int trial_z = entity_z + dz;
/*       */       
/*       */       int i;
/*       */       
/*  8514 */       for (i = 0; i < 8;) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8523 */         if (isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, false)) {
/*  8524 */           trial_y--;
/*       */           
/*       */           i++;
/*       */         } 
/*       */       } 
/*  8529 */       for (i = 0; i < 8;) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8538 */         if (!isAirOrPassableBlock(trial_x, trial_y, trial_z, false)) {
/*  8539 */           trial_y++;
/*       */           
/*       */           i++;
/*       */         } 
/*       */       } 
/*  8544 */       double distance_from_xyz_sq = getDistanceSqFromDeltas((trial_x - x), (trial_y - y), (trial_z - z));
/*       */       
/*  8546 */       if (distance_from_xyz_sq >= min_distance_sq && distance_from_xyz_sq > furthest_distance_from_xyz_sq) {
/*       */         PathEntity path;
/*       */ 
/*       */         
/*  8550 */         if (use_navigator && entity instanceof EntityLiving) {
/*  8551 */           path = ((EntityLiving)entity).getNavigator().getPathToXYZ(trial_x, trial_y, trial_z, max_path_length);
/*       */         } else {
/*  8553 */           path = getEntityPathToXYZ(entity, trial_x, trial_y, trial_z, max_path_length, true, false, false, true);
/*       */         } 
/*  8555 */         if (path != null) {
/*       */           
/*  8557 */           PathPoint final_point = path.getFinalPathPoint();
/*       */           
/*  8559 */           distance_from_xyz_sq = getDistanceSqFromDeltas((final_point.xCoord - x), (final_point.yCoord - y), (final_point.zCoord - z));
/*       */           
/*  8561 */           if (distance_from_xyz_sq >= min_distance_sq && distance_from_xyz_sq > furthest_distance_from_xyz_sq) {
/*       */             
/*  8563 */             furthest_distance_from_xyz_sq = distance_from_xyz_sq;
/*  8564 */             selected_path = path;
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  8570 */     return selected_path;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public PathEntity getEntityPathToXYZ(Entity par1Entity, int par2, int par3, int par4, float par5, boolean can_pass_open_wooden_doors, boolean can_path_through_closed_wooden_doors, boolean avoid_water, boolean can_entity_swim) {
/*  8576 */     this.theProfiler.startSection("pathfind");
/*  8577 */     int var10 = MathHelper.floor_double(par1Entity.posX);
/*  8578 */     int var11 = MathHelper.floor_double(par1Entity.posY);
/*  8579 */     int var12 = MathHelper.floor_double(par1Entity.posZ);
/*  8580 */     int var13 = (int)(par5 + 8.0F);
/*  8581 */     int var14 = var10 - var13;
/*  8582 */     int var15 = var11 - var13;
/*  8583 */     int var16 = var12 - var13;
/*  8584 */     int var17 = var10 + var13;
/*  8585 */     int var18 = var11 + var13;
/*  8586 */     int var19 = var12 + var13;
/*  8587 */     ChunkCache var20 = new ChunkCache(this, var14, var15, var16, var17, var18, var19, 0);
/*  8588 */     PathEntity var21 = (new PathFinder(var20, can_pass_open_wooden_doors, can_path_through_closed_wooden_doors, avoid_water, can_entity_swim)).createEntityPathTo(par1Entity, par2, par3, par4, par5);
/*  8589 */     this.theProfiler.endSection();
/*  8590 */     return var21;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int isBlockProvidingPowerTo(int par1, int par2, int par3, int par4) {
/*  8598 */     int var5 = getBlockId(par1, par2, par3);
/*  8599 */     return (var5 == 0) ? 0 : Block.blocksList[var5].isProvidingStrongPower(this, par1, par2, par3, par4);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getBlockPowerInput(int par1, int par2, int par3) {
/*  8607 */     byte var4 = 0;
/*  8608 */     int var5 = Math.max(var4, isBlockProvidingPowerTo(par1, par2 - 1, par3, 0));
/*       */     
/*  8610 */     if (var5 >= 15)
/*       */     {
/*  8612 */       return var5;
/*       */     }
/*       */ 
/*       */     
/*  8616 */     var5 = Math.max(var5, isBlockProvidingPowerTo(par1, par2 + 1, par3, 1));
/*       */     
/*  8618 */     if (var5 >= 15)
/*       */     {
/*  8620 */       return var5;
/*       */     }
/*       */ 
/*       */     
/*  8624 */     var5 = Math.max(var5, isBlockProvidingPowerTo(par1, par2, par3 - 1, 2));
/*       */     
/*  8626 */     if (var5 >= 15)
/*       */     {
/*  8628 */       return var5;
/*       */     }
/*       */ 
/*       */     
/*  8632 */     var5 = Math.max(var5, isBlockProvidingPowerTo(par1, par2, par3 + 1, 3));
/*       */     
/*  8634 */     if (var5 >= 15)
/*       */     {
/*  8636 */       return var5;
/*       */     }
/*       */ 
/*       */     
/*  8640 */     var5 = Math.max(var5, isBlockProvidingPowerTo(par1 - 1, par2, par3, 4));
/*       */     
/*  8642 */     if (var5 >= 15)
/*       */     {
/*  8644 */       return var5;
/*       */     }
/*       */ 
/*       */     
/*  8648 */     var5 = Math.max(var5, isBlockProvidingPowerTo(par1 + 1, par2, par3, 5));
/*  8649 */     return (var5 >= 15) ? var5 : var5;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean getIndirectPowerOutput(int par1, int par2, int par3, int par4) {
/*  8663 */     return (getIndirectPowerLevelTo(par1, par2, par3, par4) > 0);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getIndirectPowerLevelTo(int par1, int par2, int par3, int par4) {
/*  8671 */     if (isBlockNormalCube(par1, par2, par3))
/*       */     {
/*  8673 */       return getBlockPowerInput(par1, par2, par3);
/*       */     }
/*       */ 
/*       */     
/*  8677 */     int var5 = getBlockId(par1, par2, par3);
/*  8678 */     return (var5 == 0) ? 0 : Block.blocksList[var5].isProvidingWeakPower(this, par1, par2, par3, par4);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isBlockIndirectlyGettingPowered(int par1, int par2, int par3) {
/*  8688 */     return (getIndirectPowerLevelTo(par1, par2 - 1, par3, 0) > 0) ? true : ((getIndirectPowerLevelTo(par1, par2 + 1, par3, 1) > 0) ? true : ((getIndirectPowerLevelTo(par1, par2, par3 - 1, 2) > 0) ? true : ((getIndirectPowerLevelTo(par1, par2, par3 + 1, 3) > 0) ? true : ((getIndirectPowerLevelTo(par1 - 1, par2, par3, 4) > 0) ? true : ((getIndirectPowerLevelTo(par1 + 1, par2, par3, 5) > 0))))));
/*       */   }
/*       */ 
/*       */   
/*       */   public int getStrongestIndirectPower(int par1, int par2, int par3) {
/*  8693 */     int var4 = 0;
/*       */     
/*  8695 */     for (int var5 = 0; var5 < 6; var5++) {
/*       */       
/*  8697 */       int var6 = getIndirectPowerLevelTo(par1 + Facing.offsetsXForSide[var5], par2 + Facing.offsetsYForSide[var5], par3 + Facing.offsetsZForSide[var5], var5);
/*       */       
/*  8699 */       if (var6 >= 15)
/*       */       {
/*  8701 */         return 15;
/*       */       }
/*       */       
/*  8704 */       if (var6 > var4)
/*       */       {
/*  8706 */         var4 = var6;
/*       */       }
/*       */     } 
/*       */     
/*  8710 */     return var4;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public EntityPlayer getClosestPlayerToEntity(Entity par1Entity, double max_distance, boolean must_be_alive) {
/*  8748 */     return getClosestPlayer(par1Entity.posX, par1Entity.posY, par1Entity.posZ, max_distance, must_be_alive);
/*       */   }
/*       */ 
/*       */   
/*       */   public EntityPlayer getClosestPlayer(double par1, double par3, double par5, double max_distance, boolean must_be_alive) {
/*  8753 */     double var9 = -1.0D;
/*  8754 */     EntityPlayer var11 = null;
/*       */     
/*  8756 */     for (int var12 = 0; var12 < this.playerEntities.size(); var12++) {
/*       */       
/*  8758 */       EntityPlayer var13 = this.playerEntities.get(var12);
/*       */       
/*  8760 */       if (!var13.isGhost() && !var13.isZevimrgvInTournament())
/*       */       {
/*       */         
/*  8763 */         if (!must_be_alive || (!var13.isDead && var13.getHealth() > 0.0F)) {
/*       */ 
/*       */           
/*  8766 */           double var14 = var13.getDistanceSq(par1, par3, par5);
/*       */           
/*  8768 */           if ((max_distance < 0.0D || var14 < max_distance * max_distance) && (var9 == -1.0D || var14 < var9)) {
/*       */             
/*  8770 */             var9 = var14;
/*  8771 */             var11 = var13;
/*       */           } 
/*       */         }  } 
/*       */     } 
/*  8775 */     return var11;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public EntityPlayer getClosestVulnerablePlayer(EntityLiving attacker, double par7, boolean requires_line_of_sight) {
/*  8797 */     double par1 = attacker.posX;
/*  8798 */     double par3 = attacker.posY + (attacker.height / 2.0F);
/*  8799 */     double par5 = attacker.posZ;
/*       */     
/*  8801 */     if (this.isRemote) {
/*  8802 */       Minecraft.setErrorMessage("getClosestVulnerablePlayer: no meant to be called on client");
/*       */     }
/*       */     
/*  8805 */     double var9 = -1.0D;
/*  8806 */     EntityPlayer var11 = null;
/*       */     
/*  8808 */     for (int var12 = 0; var12 < this.playerEntities.size(); var12++) {
/*       */       
/*  8810 */       EntityPlayer var13 = this.playerEntities.get(var12);
/*       */       
/*  8812 */       if (!var13.isGhost() && !var13.isZevimrgvInTournament())
/*       */       {
/*       */         
/*  8815 */         if (!var13.isImmuneByGrace())
/*       */         {
/*       */           
/*  8818 */           if (!var13.capabilities.disableDamage && var13.isEntityAlive()) {
/*       */             
/*  8820 */             double var14 = var13.getDistanceSq(par1, par3, par5);
/*  8821 */             double var16 = par7;
/*       */             
/*  8823 */             if (var13.isSneaking())
/*       */             {
/*  8825 */               var16 = par7 * 0.800000011920929D;
/*       */             }
/*       */             
/*  8828 */             if (var13.isInvisible()) {
/*       */               
/*  8830 */               float var18 = var13.getArmorVisibility();
/*       */               
/*  8832 */               if (var18 < 0.1F)
/*       */               {
/*  8834 */                 var18 = 0.1F;
/*       */               }
/*       */               
/*  8837 */               var16 *= (0.7F * var18);
/*       */             } 
/*       */             
/*  8840 */             if ((par7 < 0.0D || var14 < var16 * var16) && (var9 == -1.0D || var14 < var9))
/*       */             {
/*  8842 */               if (!requires_line_of_sight || attacker.getEntitySenses().canSee(var13)) {
/*       */ 
/*       */                 
/*  8845 */                 var9 = var14;
/*  8846 */                 var11 = var13;
/*       */               }  } 
/*       */           }  } 
/*       */       }
/*       */     } 
/*  8851 */     return var11;
/*       */   }
/*       */ 
/*       */   
/*       */   public EntityAnimal getClosestAnimal(EntityLiving attacker, double max_distance, boolean requires_line_of_sight, boolean requires_path) {
/*  8856 */     List animals = getEntitiesWithinAABB(EntityAnimal.class, attacker.boundingBox.expand(max_distance, max_distance, max_distance));
/*       */     
/*  8858 */     if (animals.isEmpty()) {
/*  8859 */       return null;
/*       */     }
/*  8861 */     EntityAnimal closest_animal = null;
/*  8862 */     float closest_distance = 0.0F;
/*       */     
/*  8864 */     Iterator<EntityAnimal> i = animals.iterator();
/*       */     
/*  8866 */     while (i.hasNext()) {
/*       */       
/*  8868 */       EntityAnimal animal = i.next();
/*       */       
/*  8870 */       if (animal.isDead || animal.getHealth() <= 0.0F || !animal.isTrueAnimal()) {
/*       */         continue;
/*       */       }
/*  8873 */       if (requires_line_of_sight && !attacker.getEntitySenses().canSee(animal)) {
/*       */         continue;
/*       */       }
/*  8876 */       if (requires_path && !attacker.canPathTo(animal.getBlockPosX(), animal.getFootBlockPosY(), animal.getBlockPosZ(), (int)max_distance)) {
/*       */         continue;
/*       */       }
/*  8879 */       float distance = attacker.getDistanceToEntity(animal);
/*       */       
/*  8881 */       if (closest_animal == null || distance < closest_distance) {
/*       */         
/*  8883 */         closest_animal = animal;
/*  8884 */         closest_distance = distance;
/*       */       } 
/*       */     } 
/*       */     
/*  8888 */     return closest_animal;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public EntityLivingBase getClosestEntityLivingBase(EntityLiving attacker, Class[] target_classes, double max_distance, boolean requires_line_of_sight, boolean requires_path) {
/*  8894 */     List targets = getEntitiesWithinAABB(EntityLivingBase.class, attacker.boundingBox.expand(max_distance, max_distance, max_distance));
/*       */     
/*  8896 */     if (targets.isEmpty()) {
/*  8897 */       return null;
/*       */     }
/*  8899 */     EntityLivingBase closest_target = null;
/*  8900 */     float closest_distance = 0.0F;
/*       */     
/*  8902 */     Iterator<EntityLivingBase> i = targets.iterator();
/*       */     
/*  8904 */     while (i.hasNext()) {
/*       */       
/*  8906 */       EntityLivingBase target = i.next();
/*       */       
/*  8908 */       if (target.isDead || target.getHealth() <= 0.0F) {
/*       */         continue;
/*       */       }
/*  8911 */       boolean is_a_target_class = false;
/*       */       
/*  8913 */       for (int index = 0; index < target_classes.length; index++) {
/*       */         
/*  8915 */         if (target_classes[index] != EntityAnimal.class || !(target instanceof EntityAnimal) || target.isTrueAnimal())
/*       */         {
/*       */           
/*  8918 */           if (target.getClass().isAssignableFrom(target_classes[index])) {
/*       */             
/*  8920 */             is_a_target_class = true;
/*       */             break;
/*       */           } 
/*       */         }
/*       */       } 
/*  8925 */       if (!is_a_target_class) {
/*       */         continue;
/*       */       }
/*  8928 */       if (requires_line_of_sight && !attacker.getEntitySenses().canSee(target)) {
/*       */         continue;
/*       */       }
/*  8931 */       if (requires_path && !attacker.canPathTo(target.getBlockPosX(), target.getFootBlockPosY(), target.getBlockPosZ(), (int)max_distance)) {
/*       */         continue;
/*       */       }
/*  8934 */       float distance = attacker.getDistanceToEntity(target);
/*       */       
/*  8936 */       if (closest_target == null || distance < closest_distance) {
/*       */         
/*  8938 */         closest_target = target;
/*  8939 */         closest_distance = distance;
/*       */       } 
/*       */     } 
/*       */     
/*  8943 */     return closest_target;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public EntityLivingBase getClosestPrey(EntityLiving attacker, double max_distance, boolean requires_line_of_sight, boolean requires_path) {
/*  8949 */     List targets = getEntitiesWithinAABB(EntityLivingBase.class, attacker.boundingBox.expand(max_distance, max_distance, max_distance));
/*       */     
/*  8951 */     if (targets.isEmpty()) {
/*  8952 */       return null;
/*       */     }
/*  8954 */     EntityLivingBase closest_target = null;
/*  8955 */     float closest_distance = 0.0F;
/*       */     
/*  8957 */     Iterator<EntityLivingBase> i = targets.iterator();
/*       */     
/*  8959 */     while (i.hasNext()) {
/*       */       
/*  8961 */       EntityLivingBase target = i.next();
/*       */       
/*  8963 */       if (target.isDead || target.getHealth() <= 0.0F) {
/*       */         continue;
/*       */       }
/*  8966 */       if (!attacker.preysUpon(target)) {
/*       */         continue;
/*       */       }
/*  8969 */       if (requires_line_of_sight && !attacker.getEntitySenses().canSee(target)) {
/*       */         continue;
/*       */       }
/*  8972 */       if (requires_path && !attacker.canPathTo(target.getBlockPosX(), target.getFootBlockPosY(), target.getBlockPosZ(), (int)max_distance)) {
/*       */         continue;
/*       */       }
/*  8975 */       float distance = attacker.getDistanceToEntity(target);
/*       */       
/*  8977 */       if (closest_target == null || distance < closest_distance) {
/*       */         
/*  8979 */         closest_target = target;
/*  8980 */         closest_distance = distance;
/*       */       } 
/*       */     } 
/*       */     
/*  8984 */     return closest_target;
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isPlayerNearby(double x, double y, double z, double range) {
/*  8989 */     double range_sq = range * range;
/*       */     
/*  8991 */     for (int i = 0; i < this.playerEntities.size(); i++) {
/*       */       
/*  8993 */       EntityPlayer player = this.playerEntities.get(i);
/*       */       
/*  8995 */       if (!player.isGhost() && !player.isZevimrgvInTournament() && player.getDistanceSq(x, y, z) <= range_sq) {
/*  8996 */         return true;
/*       */       }
/*       */     } 
/*  8999 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean hasNonGhostPlayers() {
/*  9005 */     int size = this.playerEntities.size();
/*       */     
/*  9007 */     for (int i = 0; i < size; i++) {
/*       */       
/*  9009 */       EntityPlayer player = this.playerEntities.get(i);
/*       */       
/*  9011 */       if (!player.isGhost() && !player.isZevimrgvInTournament()) {
/*  9012 */         return true;
/*       */       }
/*       */     } 
/*  9015 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public double getDistanceSqToNearestPlayer(int x, int y, int z) {
/*  9020 */     if (!hasNonGhostPlayers()) {
/*  9021 */       return Double.MAX_VALUE;
/*       */     }
/*  9023 */     double distance_sq_to_nearest_player = Double.MAX_VALUE;
/*       */     
/*  9025 */     Iterator<EntityPlayer> i = this.playerEntities.iterator();
/*       */     
/*  9027 */     while (i.hasNext()) {
/*       */       
/*  9029 */       EntityPlayer player = i.next();
/*       */       
/*  9031 */       if (player.isGhost() || player.isZevimrgvInTournament()) {
/*       */         continue;
/*       */       }
/*       */       
/*  9035 */       double distance_sq_to_player = getDistanceSqFromDeltas((x - player.getBlockPosX()), (y - player.getBlockPosY()), (z - player.getBlockPosZ()));
/*       */       
/*  9037 */       if (distance_sq_to_player < distance_sq_to_nearest_player) {
/*  9038 */         distance_sq_to_nearest_player = distance_sq_to_player;
/*       */       }
/*       */     } 
/*  9041 */     if (distance_sq_to_nearest_player > 65536.0D) {
/*  9042 */       return Double.MAX_VALUE;
/*       */     }
/*  9044 */     return distance_sq_to_nearest_player;
/*       */   }
/*       */ 
/*       */   
/*       */   public float distanceToNearestPlayer(double x, double y, double z) {
/*  9049 */     if (!hasNonGhostPlayers()) {
/*  9050 */       return Float.MAX_VALUE;
/*       */     }
/*  9052 */     double distance_to_nearest_player_sq = Double.MAX_VALUE;
/*       */     
/*  9054 */     int size = this.playerEntities.size();
/*       */     
/*  9056 */     for (int i = 0; i < size; i++) {
/*       */       
/*  9058 */       EntityPlayer player = this.playerEntities.get(i);
/*       */       
/*  9060 */       if (!player.isGhost() && !player.isZevimrgvInTournament()) {
/*       */ 
/*       */         
/*  9063 */         double distance_sq = player.getDistanceSq(x, y, z);
/*       */         
/*  9065 */         if (distance_sq < distance_to_nearest_player_sq)
/*  9066 */           distance_to_nearest_player_sq = distance_sq; 
/*       */       } 
/*       */     } 
/*  9069 */     if (distance_to_nearest_player_sq > 65536.0D) {
/*  9070 */       return Float.MAX_VALUE;
/*       */     }
/*  9072 */     return (float)Math.sqrt(distance_to_nearest_player_sq);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public double getDistanceSqToNearestPlayer(int x, int z) {
/*  9078 */     if (!hasNonGhostPlayers()) {
/*  9079 */       return Double.MAX_VALUE;
/*       */     }
/*  9081 */     double distance_sq_to_nearest_player = Double.MAX_VALUE;
/*       */     
/*  9083 */     Iterator<EntityPlayer> i = this.playerEntities.iterator();
/*       */     
/*  9085 */     while (i.hasNext()) {
/*       */       
/*  9087 */       EntityPlayer player = i.next();
/*       */       
/*  9089 */       if (player.isGhost() || player.isZevimrgvInTournament()) {
/*       */         continue;
/*       */       }
/*  9092 */       double distance_sq_to_player = getDistanceSqFromDeltas((x - player.getBlockPosX()), (z - player.getBlockPosZ()));
/*       */       
/*  9094 */       if (distance_sq_to_player < distance_sq_to_nearest_player) {
/*  9095 */         distance_sq_to_nearest_player = distance_sq_to_player;
/*       */       }
/*       */     } 
/*  9098 */     if (distance_sq_to_nearest_player > 65536.0D) {
/*  9099 */       return Double.MAX_VALUE;
/*       */     }
/*  9101 */     return distance_sq_to_nearest_player;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public EntityPlayer getPlayerEntityByName(String par1Str) {
/*  9109 */     for (int var2 = 0; var2 < this.playerEntities.size(); var2++) {
/*       */       
/*  9111 */       if (par1Str.equals(((EntityPlayer)this.playerEntities.get(var2)).getCommandSenderName()))
/*       */       {
/*  9113 */         return this.playerEntities.get(var2);
/*       */       }
/*       */     } 
/*       */     
/*  9117 */     return null;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void sendQuittingDisconnectingPacket() {}
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void checkSessionLock() throws MinecraftException {
/*  9130 */     this.saveHandler.checkSessionLock();
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final void setTotalWorldTime(long par1) {
/*  9136 */     this.worldInfo.setTotalWorldTime(par1, this);
/*       */   }
/*       */ 
/*       */   
/*       */   public final void advanceTotalWorldTime(long ticks) {
/*  9141 */     setTotalWorldTime(getTotalWorldTime() + ticks);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public long getSeed() {
/*  9149 */     return this.worldInfo.getSeed();
/*       */   }
/*       */ 
/*       */   
/*       */   public long getHashedSeed() {
/*  9154 */     return this.worldInfo.getHashedSeed();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final long getTotalWorldTime() {
/*  9162 */     return this.worldInfo.getWorldTotalTime(getDimensionId());
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final long getTotalWorldTimeAtStartOfToday() {
/*  9168 */     return getTotalWorldTimeAtStartOfDay(getDayOfWorld());
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final long getTotalWorldTimeAtEndOfToday() {
/*  9174 */     return getTotalWorldTimeAtEndOfDay(getDayOfWorld());
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public static final long getTotalWorldTimeAtStartOfDay(int day) {
/*  9180 */     return ((day - 1) * 24000 - 6000);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public static final long getTotalWorldTimeAtEndOfDay(int day) {
/*  9186 */     return getTotalWorldTimeAtStartOfDay(day) + 24000L - 1L;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final long getAdjustedTotalWorldTime(long unadjusted_tick) {
/*  9200 */     return unadjusted_tick + 6000L;
/*       */   }
/*       */ 
/*       */   
/*       */   public static final int getDayOfWorld(long unadjusted_tick) {
/*  9205 */     return (int)(getAdjustedTotalWorldTime(unadjusted_tick) / 24000L) + 1;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getDayOfWorld() {
/*  9211 */     return getDayOfWorld(getTotalWorldTime());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public ChunkCoordinates getSpawnPoint() {
/*  9219 */     return new ChunkCoordinates(this.worldInfo.getSpawnX(), this.worldInfo.getSpawnY(), this.worldInfo.getSpawnZ());
/*       */   }
/*       */ 
/*       */   
/*       */   public int getSpawnX() {
/*  9224 */     return this.worldInfo.getSpawnX();
/*       */   }
/*       */ 
/*       */   
/*       */   public int getSpawnY() {
/*  9229 */     return this.worldInfo.getSpawnY();
/*       */   }
/*       */ 
/*       */   
/*       */   public int getSpawnZ() {
/*  9234 */     return this.worldInfo.getSpawnZ();
/*       */   }
/*       */ 
/*       */   
/*       */   public void setSpawnLocation(int par1, int par2, int par3) {
/*  9239 */     this.worldInfo.setSpawnPosition(par1, par2, par3);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void joinEntityInSurroundings(Entity par1Entity) {
/*  9247 */     int var2 = MathHelper.floor_double(par1Entity.posX / 16.0D);
/*  9248 */     int var3 = MathHelper.floor_double(par1Entity.posZ / 16.0D);
/*  9249 */     byte var4 = 2;
/*       */     
/*  9251 */     for (int var5 = var2 - var4; var5 <= var2 + var4; var5++) {
/*       */       
/*  9253 */       for (int var6 = var3 - var4; var6 <= var3 + var4; var6++)
/*       */       {
/*  9255 */         getChunkFromChunkCoords(var5, var6);
/*       */       }
/*       */     } 
/*       */     
/*  9259 */     if (!this.loadedEntityList.contains(par1Entity))
/*       */     {
/*  9261 */       this.loadedEntityList.add(par1Entity);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean canMineBlock(EntityPlayer par1EntityPlayer, int par2, int par3, int par4) {
/*  9270 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setEntityState(Entity par1Entity, EnumEntityState par2) {}
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public IChunkProvider getChunkProvider() {
/*  9284 */     return this.chunkProvider;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void addBlockEvent(int par1, int par2, int par3, int par4, int par5, int par6) {
/*  9293 */     if (par4 > 0)
/*       */     {
/*  9295 */       Block.blocksList[par4].onBlockEventReceived(this, par1, par2, par3, par5, par6);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public ISaveHandler getSaveHandler() {
/*  9304 */     return this.saveHandler;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final WorldInfo getWorldInfo() {
/*  9313 */     return this.worldInfo;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public GameRules getGameRules() {
/*  9321 */     return this.worldInfo.getGameRulesInstance();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getWeightedThunderStrength(float par1) {
/*  9332 */     return (this.prevThunderingStrength + (this.thunderingStrength - this.prevThunderingStrength) * par1) * getRainStrength(par1);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getRainStrength(float par1) {
/*  9340 */     return this.prevRainingStrength + (this.rainingStrength - this.prevRainingStrength) * par1;
/*       */   }
/*       */ 
/*       */   
/*       */   public void setRainStrength(float par1) {
/*  9345 */     this.prevRainingStrength = par1;
/*  9346 */     this.rainingStrength = par1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isPrecipitatingAt(int x, int y, int z) {
/*  9376 */     return (((getBiomeGenForCoords(x, z)).rainfall > 0.0F || isBloodMoon24HourPeriod()) && isPrecipitating(true) && getPrecipitationHeight(x, z) <= y);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isSnowing(int x, int z) {
/*  9382 */     return (isPrecipitating(true) && isFreezing(x, z));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isInRain(int x, int y, int z) {
/*  9388 */     if (!isPrecipitating(true)) {
/*  9389 */       return false;
/*       */     }
/*  9391 */     BiomeGenBase biome = getBiomeGenForCoords(x, z);
/*       */ 
/*       */     
/*  9394 */     if ((biome.rainfall == 0.0F && !isBloodMoon24HourPeriod()) || biome.isFreezing()) {
/*  9395 */       return false;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9403 */     return (canBlockSeeTheSky(x, y, z) && getPrecipitationHeight(x, z) <= y);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean canLightningStrikeAt(int par1, int par2, int par3) {
/*  9408 */     if (!isPrecipitating(true))
/*       */     {
/*  9410 */       return false;
/*       */     }
/*  9412 */     if (!canBlockSeeTheSky(par1, par2, par3))
/*       */     {
/*  9414 */       return false;
/*       */     }
/*  9416 */     if (getPrecipitationHeight(par1, par3) > par2)
/*       */     {
/*  9418 */       return false;
/*       */     }
/*       */ 
/*       */     
/*  9422 */     BiomeGenBase var4 = getBiomeGenForCoords(par1, par3);
/*       */     
/*  9424 */     return var4.getEnableSnow() ? false : var4.canSpawnLightningBolt(isBloodMoon24HourPeriod());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isSkyOvercast(int x, int z) {
/*  9431 */     if (!isPrecipitating(true)) {
/*  9432 */       return false;
/*       */     }
/*  9434 */     BiomeGenBase biome = getBiomeGenForCoords(x, z);
/*       */     
/*  9436 */     return (biome.hasRainfall() || isBloodMoon24HourPeriod());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isBlockHighHumidity(int par1, int par2, int par3) {
/*  9444 */     BiomeGenBase var4 = getBiomeGenForCoords(par1, par3);
/*  9445 */     return var4.isHighHumidity();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setItemData(String par1Str, WorldSavedData par2WorldSavedData) {
/*  9454 */     this.mapStorage.setData(par1Str, par2WorldSavedData);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public WorldSavedData loadItemData(Class par1Class, String par2Str) {
/*  9463 */     return this.mapStorage.loadData(par1Class, par2Str);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public int peekUniqueDataId(String prefix) {
/*  9469 */     return this.mapStorage.peekUniqueDataId(prefix);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public void setUniqueDataId(String prefix, short value) {
/*  9475 */     this.mapStorage.setUniqueDataId(this, prefix, value);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getUniqueDataId(String par1Str) {
/*  9486 */     return this.mapStorage.getUniqueDataId(this, par1Str);
/*       */   }
/*       */ 
/*       */   
/*       */   public void func_82739_e(int par1, int par2, int par3, int par4, int par5) {
/*  9491 */     for (int var6 = 0; var6 < this.worldAccesses.size(); var6++)
/*       */     {
/*  9493 */       ((IWorldAccess)this.worldAccesses.get(var6)).broadcastSound(par1, par2, par3, par4, par5);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void playAuxSFX(int id, int x, int y, int z, int data) {
/*  9507 */     playAuxSFXAtEntity((EntityPlayer)null, id, x, y, z, data);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void playAuxSFXAtEntity(EntityPlayer par1EntityPlayer, int par2, int par3, int par4, int par5, int par6) {
/*       */     try {
/*  9517 */       for (int var7 = 0; var7 < this.worldAccesses.size(); var7++)
/*       */       {
/*  9519 */         ((IWorldAccess)this.worldAccesses.get(var7)).playAuxSFX(par1EntityPlayer, par2, par3, par4, par5, par6);
/*       */       }
/*       */     }
/*  9522 */     catch (Throwable var10) {
/*       */       
/*  9524 */       CrashReport var8 = CrashReport.makeCrashReport(var10, "Playing level event");
/*  9525 */       CrashReportCategory var9 = var8.makeCategory("Level event being played");
/*  9526 */       var9.addCrashSection("Block coordinates", CrashReportCategory.getLocationInfo(par3, par4, par5));
/*  9527 */       var9.addCrashSection("Event source", par1EntityPlayer);
/*  9528 */       var9.addCrashSection("Event type", Integer.valueOf(par2));
/*  9529 */       var9.addCrashSection("Event data", Integer.valueOf(par6));
/*  9530 */       throw new ReportedException(var8);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getHeight() {
/*  9539 */     return 256;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int getMaxBlockY() {
/*  9545 */     return 255;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getActualHeight() {
/*  9553 */     if (this.underworld_y_offset != 0) {
/*  9554 */       return 256;
/*       */     }
/*  9556 */     return this.provider.hasNoSky ? 128 : 256;
/*       */   }
/*       */ 
/*       */   
/*       */   public IUpdatePlayerListBox getMinecartSoundUpdater(EntityMinecart par1EntityMinecart) {
/*  9561 */     return null;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Random setRandomSeed(int par1, int par2, int par3) {
/*  9569 */     long var4 = par1 * 341873128712L + par2 * 132897987541L + getWorldInfo().getSeed() + par3;
/*  9570 */     this.rand.setSeed(var4);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9577 */     return this.rand;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public ChunkPosition findClosestStructure(String par1Str, int par2, int par3, int par4) {
/*  9585 */     return getChunkProvider().findClosestStructure(this, par1Str, par2, par3, par4);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean extendedLevelsInChunkCache() {
/*  9593 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public double getHorizon() {
/*  9601 */     return (this.worldInfo.getTerrainType() == WorldType.FLAT) ? 0.0D : 63.0D;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public CrashReportCategory addWorldInfoToCrashReport(CrashReport par1CrashReport) {
/*  9609 */     CrashReportCategory var2 = par1CrashReport.makeCategoryDepth("Affected level", 1);
/*  9610 */     var2.addCrashSection("Level name", (this.worldInfo == null) ? "????" : this.worldInfo.getWorldName());
/*  9611 */     var2.addCrashSectionCallable("All players", new CallableLvl2(this));
/*  9612 */     var2.addCrashSectionCallable("Chunk stats", new CallableLvl3(this));
/*       */ 
/*       */     
/*       */     try {
/*  9616 */       this.worldInfo.addToCrashReport(var2);
/*       */     }
/*  9618 */     catch (Throwable var4) {
/*       */       
/*  9620 */       var2.addCrashSectionThrowable("Level Data Unobtainable", var4);
/*       */     } 
/*       */     
/*  9623 */     return var2;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void destroyBlockInWorldPartially(int destroying_entity_id, int x, int y, int z, int tenths_destroyed) {
/*  9641 */     for (int i = 0; i < this.worldAccesses.size(); i++) {
/*       */       
/*  9643 */       IWorldAccess world_access = this.worldAccesses.get(i);
/*  9644 */       world_access.destroyBlockPartially(destroying_entity_id, x, y, z, tenths_destroyed);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Vec3Pool getWorldVec3Pool() {
/*  9653 */     return this.vecPool;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Calendar getCurrentDate() {
/*  9661 */     if (getTotalWorldTime() % 600L == 0L)
/*       */     {
/*  9663 */       this.theCalendar.setTimeInMillis(MinecraftServer.getSystemTimeMillis());
/*       */     }
/*       */     
/*  9666 */     return this.theCalendar;
/*       */   }
/*       */ 
/*       */   
/*       */   public void func_92088_a(double par1, double par3, double par5, double par7, double par9, double par11, NBTTagCompound par13NBTTagCompound) {}
/*       */   
/*       */   public Scoreboard getScoreboard() {
/*  9673 */     return this.worldScoreboard;
/*       */   }
/*       */ 
/*       */   
/*       */   public void func_96440_m(int par1, int par2, int par3, int par4) {
/*  9678 */     for (int var5 = 0; var5 < 4; var5++) {
/*       */       
/*  9680 */       int var6 = par1 + Direction.offsetX[var5];
/*  9681 */       int var7 = par3 + Direction.offsetZ[var5];
/*  9682 */       int var8 = getBlockId(var6, par2, var7);
/*       */       
/*  9684 */       if (var8 != 0) {
/*       */         
/*  9686 */         Block var9 = Block.blocksList[var8];
/*       */         
/*  9688 */         if (Block.redstoneComparatorIdle.func_94487_f(var8)) {
/*       */           
/*  9690 */           var9.onNeighborBlockChange(this, var6, par2, var7, par4);
/*       */         }
/*  9692 */         else if (Block.isNormalCube(var8)) {
/*       */           
/*  9694 */           var6 += Direction.offsetX[var5];
/*  9695 */           var7 += Direction.offsetZ[var5];
/*  9696 */           var8 = getBlockId(var6, par2, var7);
/*  9697 */           var9 = Block.blocksList[var8];
/*       */           
/*  9699 */           if (Block.redstoneComparatorIdle.func_94487_f(var8))
/*       */           {
/*  9701 */             var9.onNeighborBlockChange(this, var6, par2, var7, par4);
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   public ILogAgent getWorldLogAgent() {
/*  9710 */     return this.worldLogAgent;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getLocationTensionFactor(double par1, double par3, double par5) {
/*  9720 */     return getTensionFactorForBlock(MathHelper.floor_double(par1), MathHelper.floor_double(par3), MathHelper.floor_double(par5));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public float getTensionFactorForBlock(int par1, int par2, int par3) {
/*  9730 */     float var4 = 0.0F;
/*  9731 */     boolean var5 = (this.difficultySetting == 3);
/*       */     
/*  9733 */     if (blockExists(par1, par2, par3)) {
/*       */       
/*  9735 */       float var6 = getCurrentMoonPhaseFactor();
/*  9736 */       var4 += MathHelper.clamp_float((float)(getChunkFromBlockCoords(par1, par3)).inhabitedTime / 3600000.0F, 0.0F, 1.0F) * (var5 ? 1.0F : 0.75F);
/*  9737 */       var4 += var6 * 0.25F;
/*       */     } 
/*       */     
/*  9740 */     if (this.difficultySetting < 2)
/*       */     {
/*  9742 */       var4 *= this.difficultySetting / 2.0F;
/*       */     }
/*       */     
/*  9745 */     return MathHelper.clamp_float(var4, 0.0F, var5 ? 1.5F : 1.0F);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isServerRunning() {
/*  9750 */     return DedicatedServer.it();
/*       */   }
/*       */ 
/*       */   
/*       */   public Block getNearestBlockDirectlyAbove(int x, int y, int z) {
/*  9755 */     while (++y < 256) {
/*       */       
/*  9757 */       int block_id = getBlockId(x, y, z);
/*       */       
/*  9759 */       if (block_id != 0) {
/*  9760 */         return Block.blocksList[block_id];
/*       */       }
/*       */     } 
/*  9763 */     return null;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isAirOrPassableBlock(int x, int y, int z, boolean include_liquid) {
/*  9796 */     if (y < 0 || y > 255) {
/*  9797 */       return true;
/*       */     }
/*  9799 */     if (!blockExists(x, y, z)) {
/*  9800 */       return false;
/*       */     }
/*  9802 */     int block_id = getBlockId(x, y, z);
/*       */     
/*  9804 */     if (block_id == 0) {
/*  9805 */       return true;
/*       */     }
/*  9807 */     Block block = Block.getBlock(block_id);
/*       */     
/*  9809 */     if (block == null) {
/*  9810 */       return false;
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  9815 */     return (!include_liquid && block.isLiquid()) ? false : (!block.isSolid(this, x, y, z));
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isAirOrPassableBlock(int[] coords, boolean include_liquid) {
/*  9820 */     return isAirOrPassableBlock(coords[0], coords[1], coords[2], include_liquid);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isOutdoors(int x, int y, int z, boolean initial_call) {
/*  9826 */     if (this.provider.hasNoSky) {
/*  9827 */       return false;
/*       */     }
/*  9829 */     boolean[] check_block = new boolean[49];
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9837 */     if (initial_call && isAirOrPassableBlock(x, y + 1, z, false)) {
/*       */       
/*  9839 */       if (isOutdoors(x, y + 1, z, false)) {
/*  9840 */         return true;
/*       */       }
/*  9842 */       if (isAirOrPassableBlock(x, y + 2, z, false) && isOutdoors(x, y + 2, z, false)) {
/*  9843 */         return true;
/*       */       }
/*       */     } 
/*  9846 */     if (isAirOrPassableBlock(x, y, z, false) && getPrecipitationHeight(x, z) <= y) {
/*  9847 */       return true;
/*       */     }
/*  9849 */     if (isAirOrPassableBlock(x + 1, y, z, false)) {
/*       */       
/*  9851 */       if (getPrecipitationHeight(x + 1, z) <= y) {
/*  9852 */         return true;
/*       */       }
/*  9854 */       check_block[18] = true;
/*  9855 */       check_block[26] = true;
/*  9856 */       check_block[32] = true;
/*       */     } 
/*       */     
/*  9859 */     if (isAirOrPassableBlock(x - 1, y, z, false)) {
/*       */       
/*  9861 */       if (getPrecipitationHeight(x - 1, z) <= y) {
/*  9862 */         return true;
/*       */       }
/*  9864 */       check_block[16] = true;
/*  9865 */       check_block[22] = true;
/*  9866 */       check_block[30] = true;
/*       */     } 
/*       */     
/*  9869 */     if (isAirOrPassableBlock(x, y, z + 1, false)) {
/*       */       
/*  9871 */       if (getPrecipitationHeight(x, z + 1) <= y) {
/*  9872 */         return true;
/*       */       }
/*  9874 */       check_block[30] = true;
/*  9875 */       check_block[38] = true;
/*  9876 */       check_block[32] = true;
/*       */     } 
/*       */     
/*  9879 */     if (isAirOrPassableBlock(x, y, z - 1, false)) {
/*       */       
/*  9881 */       if (getPrecipitationHeight(x, z - 1) <= y) {
/*  9882 */         return true;
/*       */       }
/*  9884 */       check_block[16] = true;
/*  9885 */       check_block[10] = true;
/*  9886 */       check_block[18] = true;
/*       */     } 
/*       */     
/*  9889 */     if (check_block[18] && isAirOrPassableBlock(x + 1, y, z - 1, false)) {
/*       */       
/*  9891 */       if (getPrecipitationHeight(x + 1, z - 1) <= y) {
/*  9892 */         return true;
/*       */       }
/*  9894 */       check_block[11] = true;
/*  9895 */       check_block[19] = true;
/*       */     } 
/*       */     
/*  9898 */     if (check_block[32] && isAirOrPassableBlock(x + 1, y, z + 1, false)) {
/*       */       
/*  9900 */       if (getPrecipitationHeight(x + 1, z + 1) <= y) {
/*  9901 */         return true;
/*       */       }
/*  9903 */       check_block[33] = true;
/*  9904 */       check_block[39] = true;
/*       */     } 
/*       */     
/*  9907 */     if (check_block[30] && isAirOrPassableBlock(x - 1, y, z + 1, false)) {
/*       */       
/*  9909 */       if (getPrecipitationHeight(x - 1, z + 1) <= y) {
/*  9910 */         return true;
/*       */       }
/*  9912 */       check_block[29] = true;
/*  9913 */       check_block[37] = true;
/*       */     } 
/*       */     
/*  9916 */     if (check_block[16] && isAirOrPassableBlock(x - 1, y, z - 1, false)) {
/*       */       
/*  9918 */       if (getPrecipitationHeight(x - 1, z - 1) <= y) {
/*  9919 */         return true;
/*       */       }
/*  9921 */       check_block[9] = true;
/*  9922 */       check_block[15] = true;
/*       */     } 
/*       */     
/*  9925 */     if (check_block[26] && isAirOrPassableBlock(x + 2, y, z, false)) {
/*       */       
/*  9927 */       if (getPrecipitationHeight(x + 2, z) <= y) {
/*  9928 */         return true;
/*       */       }
/*  9930 */       check_block[19] = true;
/*  9931 */       check_block[27] = true;
/*  9932 */       check_block[33] = true;
/*       */     } 
/*       */     
/*  9935 */     if (check_block[38] && isAirOrPassableBlock(x, y, z + 2, false)) {
/*       */       
/*  9937 */       if (getPrecipitationHeight(x, z + 2) <= y) {
/*  9938 */         return true;
/*       */       }
/*  9940 */       check_block[37] = true;
/*  9941 */       check_block[39] = true;
/*  9942 */       check_block[45] = true;
/*       */     } 
/*       */     
/*  9945 */     if (check_block[22] && isAirOrPassableBlock(x - 2, y, z, false)) {
/*       */       
/*  9947 */       if (getPrecipitationHeight(x - 2, z) <= y) {
/*  9948 */         return true;
/*       */       }
/*  9950 */       check_block[15] = true;
/*  9951 */       check_block[21] = true;
/*  9952 */       check_block[29] = true;
/*       */     } 
/*       */     
/*  9955 */     if (check_block[10] && isAirOrPassableBlock(x, y, z - 2, false)) {
/*       */       
/*  9957 */       if (getPrecipitationHeight(x, z - 2) <= y) {
/*  9958 */         return true;
/*       */       }
/*  9960 */       check_block[3] = true;
/*  9961 */       check_block[9] = true;
/*  9962 */       check_block[11] = true;
/*       */     } 
/*       */     
/*  9965 */     if (check_block[11] && isAirOrPassableBlock(x + 1, y, z - 2, false)) {
/*       */       
/*  9967 */       if (getPrecipitationHeight(x + 1, z - 2) <= y) {
/*  9968 */         return true;
/*       */       }
/*  9970 */       check_block[12] = true;
/*       */     } 
/*       */     
/*  9973 */     if (check_block[19] && isAirOrPassableBlock(x + 2, y, z - 1, false)) {
/*       */       
/*  9975 */       if (getPrecipitationHeight(x + 2, z - 1) <= y) {
/*  9976 */         return true;
/*       */       }
/*  9978 */       check_block[12] = true;
/*       */     } 
/*       */     
/*  9981 */     if (check_block[33] && isAirOrPassableBlock(x + 2, y, z + 1, false)) {
/*       */       
/*  9983 */       if (getPrecipitationHeight(x + 2, z + 1) <= y) {
/*  9984 */         return true;
/*       */       }
/*  9986 */       check_block[40] = true;
/*       */     } 
/*       */     
/*  9989 */     if (check_block[39] && isAirOrPassableBlock(x + 1, y, z + 2, false)) {
/*       */       
/*  9991 */       if (getPrecipitationHeight(x + 1, z + 2) <= y) {
/*  9992 */         return true;
/*       */       }
/*  9994 */       check_block[40] = true;
/*       */     } 
/*       */     
/*  9997 */     if (check_block[37] && isAirOrPassableBlock(x - 1, y, z + 2, false)) {
/*       */       
/*  9999 */       if (getPrecipitationHeight(x - 1, z + 2) <= y) {
/* 10000 */         return true;
/*       */       }
/* 10002 */       check_block[36] = true;
/*       */     } 
/*       */     
/* 10005 */     if (check_block[29] && isAirOrPassableBlock(x - 2, y, z + 1, false)) {
/*       */       
/* 10007 */       if (getPrecipitationHeight(x - 2, z + 1) <= y) {
/* 10008 */         return true;
/*       */       }
/* 10010 */       check_block[36] = true;
/*       */     } 
/*       */     
/* 10013 */     if (check_block[15] && isAirOrPassableBlock(x - 2, y, z - 1, false)) {
/*       */       
/* 10015 */       if (getPrecipitationHeight(x - 2, z - 1) <= y) {
/* 10016 */         return true;
/*       */       }
/* 10018 */       check_block[8] = true;
/*       */     } 
/*       */     
/* 10021 */     if (check_block[9] && isAirOrPassableBlock(x - 1, y, z - 2, false)) {
/*       */       
/* 10023 */       if (getPrecipitationHeight(x - 1, z - 2) <= y) {
/* 10024 */         return true;
/*       */       }
/* 10026 */       check_block[8] = true;
/*       */     } 
/*       */     
/* 10029 */     if (check_block[12] && isAirOrPassableBlock(x + 2, y, z - 2, false))
/*       */     {
/* 10031 */       if (getPrecipitationHeight(x + 2, z - 2) <= y) {
/* 10032 */         return true;
/*       */       }
/*       */     }
/* 10035 */     if (check_block[40] && isAirOrPassableBlock(x + 2, y, z + 2, false))
/*       */     {
/* 10037 */       if (getPrecipitationHeight(x + 2, z + 2) <= y) {
/* 10038 */         return true;
/*       */       }
/*       */     }
/* 10041 */     if (check_block[36] && isAirOrPassableBlock(x - 2, y, z + 2, false))
/*       */     {
/* 10043 */       if (getPrecipitationHeight(x - 2, z + 2) <= y) {
/* 10044 */         return true;
/*       */       }
/*       */     }
/* 10047 */     if (check_block[8] && isAirOrPassableBlock(x - 2, y, z - 2, false))
/*       */     {
/* 10049 */       if (getPrecipitationHeight(x - 2, z - 2) <= y) {
/* 10050 */         return true;
/*       */       }
/*       */     }
/* 10053 */     if (check_block[27] && isAirOrPassableBlock(x + 3, y, z, false))
/*       */     {
/* 10055 */       if (getPrecipitationHeight(x + 3, z) <= y) {
/* 10056 */         return true;
/*       */       }
/*       */     }
/* 10059 */     if (check_block[45] && isAirOrPassableBlock(x, y, z + 3, false))
/*       */     {
/* 10061 */       if (getPrecipitationHeight(x, z + 3) <= y) {
/* 10062 */         return true;
/*       */       }
/*       */     }
/* 10065 */     if (check_block[21] && isAirOrPassableBlock(x - 3, y, z, false))
/*       */     {
/* 10067 */       if (getPrecipitationHeight(x - 3, z) <= y) {
/* 10068 */         return true;
/*       */       }
/*       */     }
/* 10071 */     if (check_block[3] && isAirOrPassableBlock(x, y, z - 3, false))
/*       */     {
/* 10073 */       if (getPrecipitationHeight(x, z - 3) <= y) {
/* 10074 */         return true;
/*       */       }
/*       */     }
/* 10077 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isOutdoors(int x, int y, int z) {
/* 10082 */     return isOutdoors(x, y, z, true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isInSunlight(int x, int y, int z) {
/* 10136 */     return (isDaytime() && canBlockSeeTheSky(x, y, z) && !isPrecipitating(true));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final EntityPlayer getRandomNonGhostPlayer(boolean must_be_alive) {
/* 10159 */     return getRandomPlayer(true, must_be_alive);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final EntityPlayer getRandomPlayer(boolean must_not_be_ghost, boolean must_be_alive) {
/* 10165 */     if (this.playerEntities.size() == 0) {
/* 10166 */       return null;
/*       */     }
/* 10168 */     EntityPlayer[] candidates = new EntityPlayer[100];
/* 10169 */     int num_candidates = 0;
/*       */     
/* 10171 */     Iterator<EntityPlayer> i = this.playerEntities.iterator();
/*       */     
/* 10173 */     while (i.hasNext()) {
/*       */       
/* 10175 */       EntityPlayer player = i.next();
/*       */       
/* 10177 */       if (player.isZevimrgvInTournament()) {
/*       */         continue;
/*       */       }
/* 10180 */       if (must_not_be_ghost && player.isGhost()) {
/*       */         continue;
/*       */       }
/* 10183 */       if (must_be_alive && player.getHealth() <= 0.0F) {
/*       */         continue;
/*       */       }
/* 10186 */       candidates[num_candidates++] = player;
/*       */     } 
/*       */     
/* 10189 */     return (num_candidates == 0) ? null : candidates[this.rand.nextInt(num_candidates)];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static int[] getNeighboringBlockCoords(int x, int y, int z, EnumFace face) {
/* 10273 */     if (face == EnumFace.BOTTOM) {
/* 10274 */       y--;
/* 10275 */     } else if (face == EnumFace.TOP) {
/* 10276 */       y++;
/* 10277 */     } else if (face == EnumFace.NORTH) {
/* 10278 */       z--;
/* 10279 */     } else if (face == EnumFace.SOUTH) {
/* 10280 */       z++;
/* 10281 */     } else if (face == EnumFace.WEST) {
/* 10282 */       x--;
/* 10283 */     } else if (face == EnumFace.EAST) {
/* 10284 */       x++;
/*       */     } 
/* 10286 */     return new int[] { x, y, z };
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final Block getBlock(int x, int y, int z) {
/* 10355 */     return Block.blocksList[getBlockId(x, y, z)];
/*       */   }
/*       */ 
/*       */   
/*       */   public final Block getBlock(int[] coords) {
/* 10360 */     return getBlock(coords[0], coords[1], coords[2]);
/*       */   }
/*       */ 
/*       */   
/*       */   public final Block getBlockWithRefreshedBounds(int x, int y, int z) {
/* 10365 */     Block block = getBlock(x, y, z);
/*       */     
/* 10367 */     if (block != null) {
/* 10368 */       block.setBlockBoundsBasedOnStateAndNeighbors(this, x, y, z);
/*       */     }
/* 10370 */     return block;
/*       */   }
/*       */ 
/*       */   
/*       */   public BlockInfo getBlockInfo(int x, int y, int z) {
/* 10375 */     Block block = getBlock(x, y, z);
/*       */     
/* 10377 */     return (block == null) ? null : new BlockInfo(block, x, y, z);
/*       */   }
/*       */ 
/*       */   
/*       */   public void watchAnimal(int par1, int par2, int par3, int par4, int par5) {
/* 10382 */     for (int var6 = 0; var6 < this.worldAccesses.size(); var6++) {
/*       */       
/* 10384 */       IWorldAccess var7 = this.worldAccesses.get(var6);
/* 10385 */       var7.destroyBlockPartially(par1, par2, par3, par4, par5);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public Vec3 getBlockCenterPos(int x, int y, int z) {
/* 10392 */     return getWorldVec3Pool().getVecFromPool(x + 0.50000001D, (y + 0.5F), (z + 0.5F));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean canCastRayBetweenBlockCenters(RaycastPolicies policies, int origin_x, int origin_y, int origin_z, int target_x, int target_y, int target_z, boolean allow_collision_at_target_coords) {
/* 10417 */     RaycastCollision rc = getBlockCollisionForPolicies(getBlockCenterPos(origin_x, origin_y, origin_z), getBlockCenterPos(target_x, target_y, target_z), policies);
/*       */     
/* 10419 */     return (rc == null || (allow_collision_at_target_coords && rc.isBlockAt(target_x, target_y, target_z)));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean canCastRayBetweenBlockCenters(Raycast raycast, int origin_x, int origin_y, int origin_z, int target_x, int target_y, int target_z, boolean allow_collision_at_target_coords) {
/* 10425 */     RaycastCollision rc = raycast.getBlockCollision(getBlockCenterPos(origin_x, origin_y, origin_z), getBlockCenterPos(target_x, target_y, target_z));
/*       */     
/* 10427 */     return (rc == null || (allow_collision_at_target_coords && rc.isBlockAt(target_x, target_y, target_z)));
/*       */   }
/*       */ 
/*       */   
/*       */   public void addToSpawnPendingList(Entity entity, long spawn_time) {
/* 10432 */     if (this.isRemote) {
/*       */       return;
/*       */     }
/* 10435 */     this.pending_entity_spawns.add(new EntitySpawnPendingEntry(entity, spawn_time));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public Vec3 getVec3(double x, double y, double z) {
/* 10441 */     return this.vecPool.getVecFromPool(x, y, z);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public Vec3 getVec3() {
/* 10447 */     return this.vecPool.getVecFromPool(0.0D, 0.0D, 0.0D);
/*       */   }
/*       */ 
/*       */   
/*       */   public final AxisAlignedBB getBoundingBoxFromPool(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z) {
/* 10452 */     return AxisAlignedBB.getBoundingBoxFromPool(min_x, min_y, min_z, max_x, max_y, max_z);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final AxisAlignedBB getBoundingBoxFromPool(int x, int y, int z) {
/* 10458 */     return AxisAlignedBB.getBoundingBoxFromPool(x, y, z, (x + 1), (y + 1), (z + 1));
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void markWorldMapPixelDirty(int x, int z) {}
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void scheduleBlockChange(int x, int y, int z, int from_block_id, int to_block_id, int to_metadata, int ticks_from_now) {}
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void blockFX(EnumBlockFX kind, int x, int y, int z, SignalData data) {
/* 10498 */     if (this.isRemote) {
/*       */       
/* 10500 */       Minecraft.setErrorMessage("blockFX: only valid on server (" + kind + ")");
/*       */       
/*       */       return;
/*       */     } 
/* 10504 */     Packet85SimpleSignal packet = (new Packet85SimpleSignal(EnumSignal.block_fx, kind)).setBlockCoords(x, y, z);
/*       */     
/* 10506 */     if (data != null) {
/* 10507 */       packet.addData(data);
/*       */     }
/* 10509 */     MinecraftServer.getServer().getConfigurationManager().sendToAllNearExcept(null, x, y, z, 64.0D, this.provider.dimensionId, packet);
/*       */   }
/*       */ 
/*       */   
/*       */   public void blockFX(EnumBlockFX kind, int x, int y, int z) {
/* 10514 */     blockFX(kind, x, y, z, null);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isFullWaterBlock(int x, int y, int z, boolean include_moving_water) {
/* 10519 */     return BlockFluid.isFullWaterBlock(getBlock(x, y, z), getBlockMetadata(x, y, z), include_moving_water);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isFullLavaBlock(int x, int y, int z, boolean include_moving_lava) {
/* 10524 */     return BlockFluid.isFullLavaBlock(getBlock(x, y, z), getBlockMetadata(x, y, z), include_moving_lava);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isWaterBlock(int x, int y, int z) {
/* 10529 */     return (getBlockMaterial(x, y, z) == Material.water);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isLavaBlock(int x, int y, int z) {
/* 10534 */     return (getBlockMaterial(x, y, z) == Material.lava);
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isLiquidBlock(int x, int y, int z) {
/* 10539 */     return getBlockMaterial(x, y, z).isLiquid();
/*       */   }
/*       */ 
/*       */   
/*       */   public void sendPacketToAllAssociatedPlayers(Entity entity, Packet packet) {
/* 10544 */     if (this.isRemote) {
/* 10545 */       Minecraft.setErrorMessage("sendPacketToAllAssociatedPlayers: only meant to be called on server");
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isOverworld() {
/* 10551 */     return this.is_overworld;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public void douseFire(int x, int y, int z, Entity dousing_entity) {
/* 10557 */     if (this.isRemote) {
/*       */       
/* 10559 */       Minecraft.setErrorMessage("douseFire: not meant to be called on client");
/*       */       
/*       */       return;
/*       */     } 
/* 10563 */     if (getBlock(x, y, z) != Block.fire) {
/*       */       return;
/*       */     }
/* 10566 */     blockFX(EnumBlockFX.smoke_and_steam, x, y, z);
/* 10567 */     setBlockToAir(x, y, z);
/*       */     
/* 10569 */     if (dousing_entity != null) {
/* 10570 */       dousing_entity.causeQuenchEffect();
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean tryConvertLavaToCobblestoneOrObsidian(int x, int y, int z) {
/* 10576 */     if (this instanceof WorldServer) {
/*       */       
/* 10578 */       if (getBlockMaterial(x, y, z) != Material.lava) {
/* 10579 */         return false;
/*       */       }
/* 10581 */       blockFX(EnumBlockFX.lava_mixing_with_water, x, y, z);
/*       */ 
/*       */       
/* 10584 */       if (isFullLavaBlock(x, y, z, true) && !((WorldServer)this).isBlockScheduledToBecome(x, y, z, Block.lavaMoving.blockID, -1)) {
/* 10585 */         setBlock(x, y, z, Block.obsidian.blockID, 0, 3);
/*       */       } else {
/* 10587 */         setBlock(x, y, z, Block.cobblestone.blockID, 0, 3);
/*       */       } 
/* 10589 */       return true;
/*       */     } 
/*       */     
/* 10592 */     Minecraft.setErrorMessage("tryConvertLavaToCobblestoneOrObsidian: only meant to be called on server");
/* 10593 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean tryConvertWaterToCobblestone(int x, int y, int z) {
/* 10599 */     if (this instanceof WorldServer) {
/*       */       
/* 10601 */       if (getBlockMaterial(x, y, z) != Material.water) {
/* 10602 */         return false;
/*       */       }
/* 10604 */       blockFX(EnumBlockFX.lava_mixing_with_water, x, y, z);
/* 10605 */       setBlock(x, y, z, Block.cobblestone.blockID, 0, 3);
/*       */       
/* 10607 */       return true;
/*       */     } 
/*       */     
/* 10610 */     Minecraft.setErrorMessage("tryConvertWaterToCobblestone: only meant to be called on server");
/* 10611 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public Block getNeighborBlock(int x, int y, int z, EnumFace face) {
/* 10616 */     return getBlock(face.getNeighborX(x), face.getNeighborY(y), face.getNeighborZ(z));
/*       */   }
/*       */ 
/*       */   
/*       */   public Block getNeighborBlock(int x, int y, int z, EnumDirection direction) {
/* 10621 */     return getBlock(direction.getNeighborX(x), direction.getNeighborY(y), direction.getNeighborZ(z));
/*       */   }
/*       */ 
/*       */   
/*       */   public int getNeighborBlockMetadata(int x, int y, int z, EnumFace face) {
/* 10626 */     return getBlockMetadata(face.getNeighborX(x), face.getNeighborY(y), face.getNeighborZ(z));
/*       */   }
/*       */ 
/*       */   
/*       */   public int getNeighborBlockMetadata(int x, int y, int z, EnumDirection direction) {
/* 10631 */     return getBlockMetadata(direction.getNeighborX(x), direction.getNeighborY(y), direction.getNeighborZ(z));
/*       */   }
/*       */ 
/*       */   
/*       */   public Material getNeighborBlockMaterial(int x, int y, int z, EnumFace face) {
/* 10636 */     return getBlockMaterial(face.getNeighborX(x), face.getNeighborY(y), face.getNeighborZ(z));
/*       */   }
/*       */ 
/*       */   
/*       */   public final Material getNeighborBlockMaterial(int x, int y, int z, EnumDirection direction) {
/* 10641 */     return getBlockMaterial(direction.getNeighborX(x), direction.getNeighborY(y), direction.getNeighborZ(z));
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isNeighborBlockNormalCube(int x, int y, int z, EnumFace face) {
/* 10646 */     return isBlockNormalCube(face.getNeighborX(x), face.getNeighborY(y), face.getNeighborZ(z));
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isNeighborBlockNormalCubeDefault(int x, int y, int z, EnumDirection direction, boolean return_value_if_chunk_does_not_exist) {
/* 10651 */     return isBlockNormalCubeDefault(direction.getNeighborX(x), direction.getNeighborY(y), direction.getNeighborZ(z), return_value_if_chunk_does_not_exist);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isNeighborBlockSolidStandardFormCube(int x, int y, int z, EnumFace face) {
/* 10657 */     return isBlockSolidStandardFormCube(face.getNeighborX(x), face.getNeighborY(y), face.getNeighborZ(z));
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean neighborBlockExists(int x, int y, int z, EnumDirection direction) {
/* 10662 */     return blockExists(direction.getNeighborX(x), direction.getNeighborY(y), direction.getNeighborZ(z));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isBlockFaceFlatAndSolid(int x, int y, int z, EnumFace face) {
/* 10668 */     Block block = getBlock(x, y, z);
/*       */     
/* 10670 */     return (block != null && block.isFaceFlatAndSolid(getBlockMetadata(x, y, z), face));
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isWorldClient() {
/* 10675 */     return this instanceof WorldClient;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isWorldServer() {
/* 10680 */     return this instanceof WorldServer;
/*       */   }
/*       */ 
/*       */   
/*       */   public final WorldClient getAsWorldClient() {
/* 10685 */     return (WorldClient)this;
/*       */   }
/*       */ 
/*       */   
/*       */   public final WorldServer getAsWorldServer() {
/* 10690 */     return (WorldServer)this;
/*       */   }
/*       */ 
/*       */   
/*       */   public final double getDistanceSqFromWorldSpawn(int x, int y, int z) {
/* 10695 */     return getDistanceSqFromDeltas((x - this.worldInfo.getSpawnX()), (y - this.worldInfo.getSpawnY()), (z - this.worldInfo.getSpawnZ()));
/*       */   }
/*       */ 
/*       */   
/*       */   public final double getDistanceSqFromWorldSpawn(int x, int z) {
/* 10700 */     return getDistanceSqFromDeltas((x - this.worldInfo.getSpawnX()), 0.0F, (z - this.worldInfo.getSpawnZ()));
/*       */   }
/*       */ 
/*       */   
/*       */   public final double getDistanceFromWorldSpawn(int x, int y, int z) {
/* 10705 */     return MathHelper.sqrt_double(getDistanceSqFromWorldSpawn(x, y, z));
/*       */   }
/*       */ 
/*       */   
/*       */   public final double getDistanceFromWorldSpawn(int x, int z) {
/* 10710 */     return MathHelper.sqrt_double(getDistanceSqFromWorldSpawn(x, z));
/*       */   }
/*       */ 
/*       */   
/*       */   public final double getDistanceSqFromWorldOrigin(int x, int z) {
/* 10715 */     return getDistanceSqFromDeltas(x, 0.0F, z);
/*       */   }
/*       */ 
/*       */   
/*       */   public final double getDistanceFromWorldOrigin(int x, int z) {
/* 10720 */     return MathHelper.sqrt_double(getDistanceSqFromWorldOrigin(x, z));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isWithinTournamentSafeZone(int x, int y, int z) {
/* 10726 */     return (DedicatedServer.isTournamentThatHasSafeZone() && getDistanceSqFromWorldSpawn(x, y, z) < 1024.0D);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isWithinTournamentArena(int x, int z) {
/* 10732 */     if (!DedicatedServer.isTournament()) {
/* 10733 */       return false;
/*       */     }
/* 10735 */     int spawn_x = this.worldInfo.getSpawnX();
/* 10736 */     int spawn_z = this.worldInfo.getSpawnZ();
/*       */ 
/*       */     
/* 10739 */     int domain = DedicatedServer.getTournamentArenaRadius();
/*       */     
/* 10741 */     int min_x = spawn_x - domain;
/* 10742 */     int max_x = spawn_x + domain;
/*       */     
/* 10744 */     int min_z = spawn_z - domain;
/* 10745 */     int max_z = spawn_z + domain;
/*       */     
/* 10747 */     return (x >= min_x && x <= max_x && z >= min_z && z <= max_z);
/*       */   }
/*       */ 
/*       */   
/*       */   public final int getDimensionId() {
/* 10752 */     return this.provider.dimensionId;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isUnderworld() {
/* 10757 */     return this.is_underworld;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isTheNether() {
/* 10763 */     return this.is_nether;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isTheEnd() {
/* 10769 */     return this.is_the_end;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean hasSkylight() {
/* 10796 */     return this.has_skylight;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean areSkillsEnabled() {
/* 10807 */     return this.worldInfo.areSkillsEnabled();
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean areCoinsEnabled() {
/* 10812 */     return this.worldInfo.areCoinsEnabled();
/*       */   }
/*       */ 
/*       */   
/*       */   public World getWorld() {
/* 10817 */     return this;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isBiomeFreezing(int x, int z) {
/* 10822 */     return getBiomeGenForCoords(x, z).isFreezing();
/*       */   }
/*       */ 
/*       */   
/*       */   public String getDimensionName() {
/* 10827 */     return this.provider.getDimensionName();
/*       */   }
/*       */ 
/*       */   
/*       */   public String getClientOrServerString() {
/* 10832 */     return this.isRemote ? "client" : ((this instanceof WorldServer) ? "server" : "unknown");
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final AxisAlignedBB getCollisionBoundsCombined(int x, int y, int z, Entity entity) {
/* 10838 */     Block block = getBlock(x, y, z);
/*       */ 
/*       */ 
/*       */     
/* 10842 */     return (block == null) ? null : block.getCollisionBoundsCombined(this, x, y, z, entity, true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final AxisAlignedBB getCollisionBoundsCombined(Block block, int x, int y, int z, Entity entity) {
/* 10850 */     return (block == null) ? null : block.getCollisionBoundsCombined(this, x, y, z, entity, true);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlockSolid(int x, int y, int z) {
/* 10856 */     return Block.isBlockSolid(this, x, y, z);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlockSolid(Block block, int x, int y, int z) {
/* 10862 */     return Block.isBlockSolid(this, block, x, y, z);
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isBlockLiquid(int x, int y, int z) {
/* 10867 */     Block block = getBlock(x, y, z);
/*       */     
/* 10869 */     return (block != null && block.isLiquid());
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean checkIfBlockIsNotLegal(int x, int y, int z) {
/* 10890 */     Block block = getBlock(x, y, z);
/*       */     
/* 10892 */     if (block == null) {
/* 10893 */       return false;
/*       */     }
/* 10895 */     return block.checkIfNotLegal(this, x, y, z);
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean doesBlockBlockFluids(int x, int y, int z) {
/* 10900 */     int block_id = getBlockId(x, y, z);
/*       */     
/* 10902 */     if (block_id == 0) {
/* 10903 */       return false;
/*       */     }
/* 10905 */     Block block = Block.getBlock(block_id);
/*       */     
/* 10907 */     return block.always_blocks_fluids ? true : (block.never_blocks_fluids ? false : block.blocksFluids(getBlockMetadata(x, y, z)));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final double getBlockCollisionTopY(int x, int y, int z, Entity entity) {
/* 10913 */     AxisAlignedBB bb = getCollisionBoundsCombined(x, y, z, entity);
/*       */     
/* 10915 */     if (bb == null) {
/* 10916 */       bb = getCollisionBoundsCombined(x, --y, z, entity);
/*       */     }
/* 10918 */     return (bb == null) ? y : bb.maxY;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final double getBlockRenderTopY(int x, int y, int z) {
/* 10924 */     return getBlockRenderTopY(getBlock(x, y, z), x, y, z);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final double getBlockRenderTopY(Block block, int x, int y, int z) {
/* 10930 */     if (block == null) {
/* 10931 */       return y;
/*       */     }
/* 10933 */     block.setBlockBoundsBasedOnStateAndNeighbors(this, x, y, z);
/*       */     
/* 10935 */     return y + block.maxY[Minecraft.getThreadIndex()];
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean canBlockBePathedInto(int x, int y, int z, Entity entity, boolean allow_closed_wooden_portals) {
/* 10941 */     if (!blockExists(x, y, z)) {
/* 10942 */       return false;
/*       */     }
/* 10944 */     int block_id = getBlockId(x, y, z);
/*       */     
/* 10946 */     return (block_id == 0 || Block.getBlock(block_id).canBePathedInto(this, x, y, z, entity, allow_closed_wooden_portals));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isPointInsideBlockCollisionBounds(Vec3 point) {
/* 10952 */     int x = point.getBlockX();
/* 10953 */     int y = point.getBlockY();
/* 10954 */     int z = point.getBlockZ();
/*       */     
/* 10956 */     int block_id = getBlockId(x, y, z);
/*       */     
/* 10958 */     return (block_id != 0 && Block.getBlock(block_id).doCollisionBoundsContain(this, x, y, z, point));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean doBlockCollisionBoundsIntersectWithBB(int x, int y, int z, AxisAlignedBB bb) {
/* 10964 */     int block_id = getBlockId(x, y, z);
/*       */     
/* 10966 */     return (block_id != 0 && Block.getBlock(block_id).doCollisionBoundsIntersectWith(this, x, y, z, bb));
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean doesBBIntersectWithBlockCollisionBounds(AxisAlignedBB bb) {
/* 10972 */     int min_x = bb.getBlockCoordForMinX();
/* 10973 */     int min_y = bb.getBlockCoordForMinY();
/* 10974 */     int min_z = bb.getBlockCoordForMinZ();
/* 10975 */     int max_x = bb.getBlockCoordForMaxX();
/* 10976 */     int max_y = bb.getBlockCoordForMaxY();
/* 10977 */     int max_z = bb.getBlockCoordForMaxZ();
/*       */ 
/*       */ 
/*       */     
/* 10981 */     for (int x = min_x; x <= max_x; x++) {
/*       */       
/* 10983 */       for (int y = min_y; y <= max_y; y++) {
/*       */         
/* 10985 */         for (int z = min_z; z <= max_z; z++) {
/*       */           
/* 10987 */           if (doBlockCollisionBoundsIntersectWithBB(x, y, z, bb)) {
/* 10988 */             return true;
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/* 10993 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean doesEBSExist(int x, int y, int z) {
/* 10998 */     if (y < 0 || y > 255) {
/* 10999 */       return false;
/*       */     }
/* 11001 */     Chunk chunk = getChunkFromBlockCoords(x, z);
/*       */     
/* 11003 */     return (chunk.storageArrays[y >> 4] != null);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getBlockDomainRadius() {
/* 11009 */     return this.block_domain_radius;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getRunegateDomainRadius(Material material) {
/* 11016 */     return (material == Material.adamantium) ? this.runegate_adamantium_domain_radius : this.runegate_mithril_domain_radius;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isWithinBlockDomain(int x, int z) {
/* 11022 */     return (x >= this.min_block_xz && x <= this.max_block_xz && z >= this.min_block_xz && z <= this.max_block_xz);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isWithinBlockBounds(int x, int y, int z) {
/* 11028 */     return (y >= 0 && y < 256 && x >= this.min_block_xz && x <= this.max_block_xz && z >= this.min_block_xz && z <= this.max_block_xz);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isChunkWithinBlockDomain(int chunk_x, int chunk_z) {
/* 11034 */     return (chunk_x >= this.min_chunk_xz && chunk_x <= this.max_chunk_xz && chunk_z >= this.min_chunk_xz && chunk_z <= this.max_chunk_xz);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isWithinEntityDomain(double pos_x, double pos_z) {
/* 11045 */     return (pos_x >= this.min_entity_pos_xz && pos_x <= this.max_entity_pos_xz && pos_z >= this.min_entity_pos_xz && pos_z <= this.max_entity_pos_xz);
/*       */   }
/*       */ 
/*       */   
/*       */   public final void validateDomainValues() {
/* 11050 */     int domain_radius = this.block_domain_radius;
/*       */     
/* 11052 */     int multiple_of = isOverworld() ? 128 : 16;
/*       */     
/* 11054 */     if (domain_radius % multiple_of != 0) {
/* 11055 */       Debug.setErrorMessage("Minecraft: domain_radius of " + getDimensionName() + " must be a multiple of " + multiple_of);
/*       */     }
/* 11057 */     if (this.min_block_xz != -domain_radius) {
/* 11058 */       Debug.setErrorMessage("Minecraft: min_block_xz is invalid");
/*       */     }
/* 11060 */     if (this.max_block_xz != domain_radius - 1) {
/* 11061 */       Debug.setErrorMessage("Minecraft: max_block_xz is invalid");
/*       */     }
/* 11063 */     if (this.min_chunk_xz != -domain_radius / 16) {
/* 11064 */       Debug.setErrorMessage("Minecraft: min_chunk_xz is invalid");
/*       */     }
/* 11066 */     if (this.max_chunk_xz != domain_radius / 16 - 1) {
/* 11067 */       Debug.setErrorMessage("Minecraft: max_chunk_xz is invalid");
/*       */     }
/* 11069 */     if (this.min_chunk_xz % 32 != 0) {
/* 11070 */       Debug.setErrorMessage("Minecraft: min_chunk_xz must be a multiple of 32");
/*       */     }
/* 11072 */     if (this.max_chunk_xz % 32 != 31) {
/* 11073 */       Debug.setErrorMessage("Minecraft: max_chunk_xz is not aligned to a region boundary");
/*       */     }
/* 11075 */     int min_chunk_xz = this.min_chunk_xz;
/* 11076 */     int max_chunk_xz = this.max_chunk_xz;
/*       */     
/* 11078 */     if (min_chunk_xz * 16 != this.min_block_xz) {
/* 11079 */       Debug.setErrorMessage("Minecraft: min_chunk_xz vs min_block_xz discrepency");
/*       */     }
/* 11081 */     if (max_chunk_xz * 16 + 15 != this.max_block_xz) {
/* 11082 */       Debug.setErrorMessage("Minecraft: max_chunk_xz vs max_block_x discrepency");
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean hasStandardFormOpaqueBlockAbove(int x, int y, int z) {
/* 11088 */     int hmv = getHeightValue(x, z);
/*       */     
/* 11090 */     while (++y < hmv) {
/*       */       
/* 11092 */       if (isBlockStandardFormOpaqueCube(x, y, z)) {
/* 11093 */         return true;
/*       */       }
/*       */     } 
/* 11096 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean tryToMeltBlock(int x, int y, int z) {
/* 11102 */     Block block = getBlock(x, y, z);
/*       */     
/* 11104 */     return (block != null && block.melt(this, x, y, z));
/*       */   }
/*       */ 
/*       */   
/*       */   public final List generateWeatherEvents(int day) {
/* 11109 */     if (!isOverworld()) {
/* 11110 */       Debug.setErrorMessage("generateWeatherEvents: called for " + getDimensionName());
/*       */     }
/* 11112 */     List<WeatherEvent> events = new ArrayList();
/*       */     
/* 11114 */     if (day < 2) {
/* 11115 */       return events;
/*       */     }
/* 11117 */     long first_tick_of_day = ((day - 1) * 24000 - 6000);
/*       */     
/* 11119 */     Random random = new Random(getWorldCreationTime() + (getDimensionId() * 938473) + day);
/*       */     
/* 11121 */     random.nextInt();
/*       */     
/* 11123 */     for (int i = 0; i < 3; i++) {
/*       */ 
/*       */       
/* 11126 */       if (random.nextInt(4) > 0) {
/*       */         break;
/*       */       }
/* 11129 */       WeatherEvent event = new WeatherEvent(first_tick_of_day + random.nextInt(24000), random.nextInt(12000) + 6000);
/*       */       
/* 11131 */       if (!isHarvestMoon(event.start, true) && !isHarvestMoon(event.end, true) && !isHarvestMoon(event.start + 6000L, true) && !isHarvestMoon(event.end - 6000L, true))
/*       */       {
/*       */         
/* 11134 */         if (!isBloodMoon(event.start, false) && !isBloodMoon(event.end, false))
/*       */         {
/*       */           
/* 11137 */           if (!isBlueMoon(event.start, false) && !isBlueMoon(event.end, false))
/*       */           {
/*       */             
/* 11140 */             events.add(event); }  } 
/*       */       }
/*       */     } 
/* 11143 */     if (isBloodMoon(first_tick_of_day + 6000L, false)) {
/*       */       
/* 11145 */       WeatherEvent event = new WeatherEvent(first_tick_of_day + 6000L, 13000);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 11150 */       event.setStorm(event.start, event.end);
/*       */       
/* 11152 */       events.add(event);
/*       */     } 
/*       */     
/* 11155 */     return events;
/*       */   }
/*       */ 
/*       */   
/*       */   public final List generateWeatherEvents(int from_day, int to_day) {
/* 11160 */     List events = new ArrayList();
/*       */     
/* 11162 */     for (int day = from_day; day <= to_day; day++) {
/* 11163 */       events.addAll(generateWeatherEvents(day));
/*       */     }
/* 11165 */     return events;
/*       */   }
/*       */ 
/*       */   
/*       */   private static List mergeWeatherEvents(List<WeatherEvent> events) {
/* 11170 */     Iterator<WeatherEvent> i = events.iterator();
/*       */     
/* 11172 */     while (i.hasNext()) {
/*       */       
/* 11174 */       WeatherEvent a = i.next();
/*       */       
/* 11176 */       if (a.removed) {
/*       */         continue;
/*       */       }
/* 11179 */       Iterator<WeatherEvent> j = events.iterator();
/*       */       
/* 11181 */       while (j.hasNext()) {
/*       */         
/* 11183 */         WeatherEvent b = j.next();
/*       */         
/* 11185 */         if (b == a || b.removed) {
/*       */           continue;
/*       */         }
/* 11188 */         int padding = 6000;
/*       */         
/* 11190 */         if (a.end + padding < b.start || a.start > b.end + padding) {
/*       */           continue;
/*       */         }
/* 11193 */         a.setStartAndEnd(Math.min(a.start, b.start), Math.max(a.end, b.end));
/*       */         
/* 11195 */         if (b.hasStorm() && !a.hasStorm()) {
/* 11196 */           a.setStorm(b.start_of_storm, b.end_of_storm);
/*       */         }
/* 11198 */         b.removed = true;
/*       */       } 
/*       */     } 
/*       */     
/* 11202 */     i = events.iterator();
/*       */     
/* 11204 */     while (i.hasNext()) {
/*       */       
/* 11206 */       WeatherEvent a = i.next();
/*       */       
/* 11208 */       if (a.removed) {
/* 11209 */         i.remove();
/*       */       }
/*       */     } 
/* 11212 */     Collections.sort(events, new WeatherEventComparator());
/*       */     
/* 11214 */     return events;
/*       */   }
/*       */ 
/*       */   
/*       */   public static List addRandomWindAndStormsToWeatherEvents(List events) {
/* 11219 */     Iterator<WeatherEvent> i = events.iterator();
/*       */     
/* 11221 */     while (i.hasNext()) {
/*       */       
/* 11223 */       WeatherEvent event = i.next();
/*       */       
/* 11225 */       event.randomizeType();
/* 11226 */       event.addStorm();
/*       */     } 
/*       */     
/* 11229 */     return events;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean canPrecipitate() {
/* 11234 */     return isOverworld();
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final List getWeatherEventsForToday() {
/* 11240 */     if (!canPrecipitate()) {
/* 11241 */       return null;
/*       */     }
/* 11243 */     int day = getDayOfWorld();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 11254 */     if (this.weather_events_for_today == null || this.weather_events_for_day != day) {
/*       */       
/* 11256 */       this.weather_events_for_day = day;
/* 11257 */       this.weather_events_for_today = addRandomWindAndStormsToWeatherEvents(mergeWeatherEvents(generateWeatherEvents(day - 1, day)));
/*       */     } 
/*       */     
/* 11260 */     return this.weather_events_for_today;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final WeatherEvent getWeatherEventAt(long unadjusted_tick, boolean must_have_storm, boolean must_have_storm_at_unadjusted_tick) {
/* 11306 */     int day = getDayOfWorld();
/*       */     
/* 11308 */     if (unadjusted_tick < getTotalWorldTimeAtStartOfDay(day - 1) || unadjusted_tick > getTotalWorldTimeAtEndOfDay(day)) {
/* 11309 */       Debug.setErrorMessage("getWeatherEventAt: params out of bounds");
/*       */     }
/* 11311 */     if (!must_have_storm && must_have_storm_at_unadjusted_tick) {
/* 11312 */       Debug.setErrorMessage("getWeatherEventAt: must_have_storm_at_specified_time=true but must_have_storm=false");
/*       */     }
/* 11314 */     List events = getWeatherEventsForToday();
/*       */     
/* 11316 */     if (events == null) {
/* 11317 */       return null;
/*       */     }
/* 11319 */     Iterator<WeatherEvent> i = events.iterator();
/*       */     
/* 11321 */     while (i.hasNext()) {
/*       */       
/* 11323 */       WeatherEvent event = i.next();
/*       */       
/* 11325 */       if (!event.isOccurringAt(unadjusted_tick)) {
/*       */         continue;
/*       */       }
/* 11328 */       if (must_have_storm && !event.hasStorm()) {
/*       */         continue;
/*       */       }
/* 11331 */       if (must_have_storm_at_unadjusted_tick && !event.isStormingAt(unadjusted_tick)) {
/*       */         continue;
/*       */       }
/* 11334 */       return event;
/*       */     } 
/*       */     
/* 11337 */     return null;
/*       */   }
/*       */ 
/*       */   
/*       */   public final WeatherEvent getCurrentWeatherEvent(boolean with_storm, boolean with_storm_at_unadjusted_tick) {
/* 11342 */     return getWeatherEventAt(getTotalWorldTime(), with_storm, with_storm_at_unadjusted_tick);
/*       */   }
/*       */ 
/*       */   
/*       */   public final WeatherEvent getCurrentWeatherEvent() {
/* 11347 */     return getCurrentWeatherEvent(false, false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final WeatherEvent getNextWeatherEvent(boolean with_storm) {
/* 11375 */     return getNextWeatherEvent(with_storm, getTotalWorldTime());
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final WeatherEvent getNextWeatherEvent(boolean with_storm, long from_tick) {
/* 11381 */     List events = getWeatherEventsForToday();
/*       */     
/* 11383 */     if (events == null) {
/* 11384 */       return null;
/*       */     }
/* 11386 */     Iterator<WeatherEvent> i = events.iterator();
/*       */     
/* 11388 */     while (i.hasNext()) {
/*       */       
/* 11390 */       WeatherEvent event = i.next();
/*       */       
/* 11392 */       if (with_storm && !event.hasStorm()) {
/*       */         continue;
/*       */       }
/* 11395 */       if (event.start >= from_tick) {
/* 11396 */         return event;
/*       */       }
/*       */     } 
/* 11399 */     return null;
/*       */   }
/*       */ 
/*       */   
/*       */   public final WeatherEvent getPreviousWeatherEvent(boolean with_storm) {
/* 11404 */     List events = getWeatherEventsForToday();
/*       */     
/* 11406 */     if (events == null) {
/* 11407 */       return null;
/*       */     }
/* 11409 */     WeatherEvent previous_event = null;
/* 11410 */     long latest_end = 0L;
/*       */     
/* 11412 */     Iterator<WeatherEvent> i = events.iterator();
/*       */     
/* 11414 */     while (i.hasNext()) {
/*       */       
/* 11416 */       WeatherEvent event = i.next();
/*       */       
/* 11418 */       if (with_storm && !event.hasStorm()) {
/*       */         continue;
/*       */       }
/* 11421 */       if (event.end > latest_end && event.end < getTotalWorldTime()) {
/*       */         
/* 11423 */         latest_end = event.end;
/* 11424 */         previous_event = event;
/*       */       } 
/*       */     } 
/*       */     
/* 11428 */     return previous_event;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isPrecipitating(boolean based_on_rain_strength) {
/* 11434 */     if (based_on_rain_strength) {
/* 11435 */       return (getRainStrength(1.0F) > 0.2D);
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 11442 */     return this.is_precipitating;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isThundering(boolean based_on_thunder_strength) {
/* 11448 */     if (based_on_thunder_strength) {
/* 11449 */       return (getWeightedThunderStrength(1.0F) > 0.9D);
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 11459 */     return this.is_storming;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getPrecipitationType(int default_type) {
/* 11465 */     WeatherEvent event = getCurrentWeatherEvent();
/*       */     
/* 11467 */     return (event == null) ? default_type : event.type;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean willPrecipitationStart(long unadjusted_tick_from, long unadjusted_tick_to) {
/* 11473 */     int day = getDayOfWorld();
/*       */     
/* 11475 */     if (unadjusted_tick_from < getTotalWorldTimeAtStartOfDay(day - 1) || unadjusted_tick_to > getTotalWorldTimeAtEndOfDay(day)) {
/* 11476 */       Debug.setErrorMessage("willPrecipitationStart: params out of bounds");
/*       */     }
/* 11478 */     List events = getWeatherEventsForToday();
/*       */     
/* 11480 */     if (events == null) {
/* 11481 */       return false;
/*       */     }
/* 11483 */     Iterator<WeatherEvent> i = events.iterator();
/*       */     
/* 11485 */     while (i.hasNext()) {
/*       */       
/* 11487 */       WeatherEvent event = i.next();
/*       */       
/* 11489 */       if (event.startsPrecipitating(unadjusted_tick_from, unadjusted_tick_to)) {
/* 11490 */         return true;
/*       */       }
/*       */     } 
/* 11493 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean willStormStart(long unadjusted_tick_from, long unadjusted_tick_to) {
/* 11499 */     int day = getDayOfWorld();
/*       */     
/* 11501 */     if (unadjusted_tick_from < getTotalWorldTimeAtStartOfDay(day - 1) || unadjusted_tick_to > getTotalWorldTimeAtEndOfDay(day)) {
/* 11502 */       Debug.setErrorMessage("willStormStart: params out of bounds");
/*       */     }
/* 11504 */     List events = getWeatherEventsForToday();
/*       */     
/* 11506 */     if (events == null) {
/* 11507 */       return false;
/*       */     }
/* 11509 */     Iterator<WeatherEvent> i = events.iterator();
/*       */     
/* 11511 */     while (i.hasNext()) {
/*       */       
/* 11513 */       WeatherEvent event = i.next();
/*       */       
/* 11515 */       if (event.startsStorming(unadjusted_tick_from, unadjusted_tick_to)) {
/* 11516 */         return true;
/*       */       }
/*       */     } 
/* 11519 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean willPrecipitationStartToday(int time_offset_from_start_of_day) {
/* 11525 */     if (time_offset_from_start_of_day < 0 || time_offset_from_start_of_day > 24000) {
/* 11526 */       Debug.setErrorMessage("willPrecipitationStartToday: time_offset out of bounds " + time_offset_from_start_of_day);
/*       */     }
/* 11528 */     return willPrecipitationStart(getTotalWorldTimeAtStartOfToday() + time_offset_from_start_of_day, getTotalWorldTimeAtEndOfToday());
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean willStormStartToday(int time_offset_from_start_of_day) {
/* 11534 */     if (time_offset_from_start_of_day < 0 || time_offset_from_start_of_day >= 24000) {
/* 11535 */       Debug.setErrorMessage("willStormStartToday: time_offset out of bounds " + time_offset_from_start_of_day);
/*       */     }
/* 11537 */     return willStormStart(getTotalWorldTimeAtStartOfToday() + time_offset_from_start_of_day, getTotalWorldTimeAtEndOfToday());
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean willPrecipitationStartToday() {
/* 11543 */     return willPrecipitationStartToday(0);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean willStormStartToday() {
/* 11549 */     return willStormStartToday(0);
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isPrecipitatingAt(long unadjusted_tick) {
/* 11554 */     int day = getDayOfWorld();
/*       */     
/* 11556 */     if (unadjusted_tick < getTotalWorldTimeAtStartOfDay(day - 1) || unadjusted_tick > getTotalWorldTimeAtEndOfDay(day)) {
/* 11557 */       Debug.setErrorMessage("willBePrecipitatingAt: params out of bounds");
/*       */     }
/* 11559 */     List events = getWeatherEventsForToday();
/*       */     
/* 11561 */     Iterator<WeatherEvent> i = events.iterator();
/*       */     
/* 11563 */     while (i.hasNext()) {
/*       */       
/* 11565 */       WeatherEvent event = i.next();
/*       */       
/* 11567 */       if (event.isPrecipitatingAt(unadjusted_tick)) {
/* 11568 */         return true;
/*       */       }
/*       */     } 
/* 11571 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isStormingAt(long unadjusted_tick) {
/* 11576 */     int day = getDayOfWorld();
/*       */     
/* 11578 */     if (unadjusted_tick < getTotalWorldTimeAtStartOfDay(day - 1) || unadjusted_tick > getTotalWorldTimeAtEndOfDay(day)) {
/* 11579 */       Debug.setErrorMessage("isStormingAt: params out of bounds");
/*       */     }
/* 11581 */     List events = getWeatherEventsForToday();
/*       */     
/* 11583 */     Iterator<WeatherEvent> i = events.iterator();
/*       */     
/* 11585 */     while (i.hasNext()) {
/*       */       
/* 11587 */       WeatherEvent event = i.next();
/*       */       
/* 11589 */       if (event.isStormingAt(unadjusted_tick)) {
/* 11590 */         return true;
/*       */       }
/*       */     } 
/* 11593 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isPrecipitatingTodayAt(int time_offset_from_start_of_day) {
/* 11598 */     return isPrecipitatingAt(getTotalWorldTimeAtStartOfToday() + time_offset_from_start_of_day);
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isStormingTodayAt(int time_offset_from_start_of_day) {
/* 11603 */     return isStormingAt(getTotalWorldTimeAtStartOfToday() + time_offset_from_start_of_day);
/*       */   }
/*       */ 
/*       */   
/*       */   public final long getWorldCreationTime() {
/* 11608 */     return this.worldInfo.getWorldCreationTime();
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isOpenPortal(int x, int y, int z) {
/* 11613 */     Block block = getBlock(x, y, z);
/*       */     
/* 11615 */     return (block != null && block.isOpenPortal(this, x, y, z));
/*       */   }
/*       */ 
/*       */   
/*       */   public void generateWeatherReport(int from_day, int to_day) {
/* 11620 */     WeatherEvent.printWeatherEvents(addRandomWindAndStormsToWeatherEvents(mergeWeatherEvents(generateWeatherEvents(from_day, to_day))));
/*       */   }
/*       */ 
/*       */   
/*       */   public final void updateTickFlags() {
/* 11625 */     long total_world_time = getTotalWorldTime();
/*       */     
/* 11627 */     if (this.tick_flags_last_updated == total_world_time) {
/*       */       return;
/*       */     }
/* 11630 */     this.total_time = total_world_time;
/*       */     
/* 11632 */     if (isOverworld()) {
/*       */       
/* 11634 */       updateWeatherFlags(total_world_time);
/* 11635 */       updateMoonFlags(total_world_time);
/*       */     } 
/*       */     
/* 11638 */     this.tick_flags_last_updated = total_world_time;
/*       */   }
/*       */ 
/*       */   
/*       */   public final void updateWeatherFlags(long total_world_time) {
/* 11643 */     if (!isOverworld()) {
/*       */       
/* 11645 */       Minecraft.setErrorMessage("updateWeatherFlags: Why called for " + getDimensionName());
/*       */       
/*       */       return;
/*       */     } 
/* 11649 */     if (this.current_weather_event != null && !this.current_weather_event.isOccurringAt(total_world_time)) {
/* 11650 */       this.current_weather_event = null;
/*       */     }
/* 11652 */     if (this.current_weather_event == null) {
/* 11653 */       this.current_weather_event = getWeatherEventAt(total_world_time, false, false);
/*       */     }
/* 11655 */     if (this.current_weather_event == null) {
/*       */       
/* 11657 */       this.is_precipitating = false;
/* 11658 */       this.is_storming = false;
/*       */     }
/*       */     else {
/*       */       
/* 11662 */       this.is_precipitating = true;
/* 11663 */       this.is_storming = this.current_weather_event.isStormingAt(total_world_time);
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   public final void updateMoonFlags(long total_world_time) {
/* 11669 */     if (!isOverworld()) {
/*       */       
/* 11671 */       Minecraft.setErrorMessage("updateMoonFlags: Why called for " + getDimensionName());
/*       */       
/*       */       return;
/*       */     } 
/* 11675 */     boolean is_daytime = isDaytime(total_world_time);
/*       */     
/* 11677 */     this.is_harvest_moon_24_hour_period = isHarvestMoon(total_world_time, false);
/* 11678 */     this.is_harvest_moon_day = (this.is_harvest_moon_24_hour_period && is_daytime);
/* 11679 */     this.is_harvest_moon_night = (this.is_harvest_moon_24_hour_period && !this.is_harvest_moon_day);
/*       */     
/* 11681 */     this.is_blood_moon_24_hour_period = isBloodMoon(total_world_time, false);
/* 11682 */     this.is_blood_moon_day = (this.is_blood_moon_24_hour_period && is_daytime);
/* 11683 */     this.is_blood_moon_night = (this.is_blood_moon_24_hour_period && !this.is_blood_moon_day);
/*       */     
/* 11685 */     this.is_blue_moon_24_hour_period = isBlueMoon(total_world_time, false);
/* 11686 */     this.is_blue_moon_day = (this.is_blue_moon_24_hour_period && is_daytime);
/* 11687 */     this.is_blue_moon_night = (this.is_blue_moon_24_hour_period && !this.is_blue_moon_day);
/*       */     
/* 11689 */     this.is_moon_dog_24_hour_period = isMoonDog(total_world_time, false);
/* 11690 */     this.is_moon_dog_day = (this.is_moon_dog_24_hour_period && is_daytime);
/* 11691 */     this.is_moon_dog_night = (this.is_moon_dog_24_hour_period && !this.is_moon_dog_day);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isHarvestMoon24HourPeriod() {
/* 11697 */     return this.is_harvest_moon_24_hour_period;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isHarvestMoonDay() {
/* 11703 */     return this.is_harvest_moon_day;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isHarvestMoonNight() {
/* 11709 */     return this.is_harvest_moon_night;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBloodMoon24HourPeriod() {
/* 11715 */     return this.is_blood_moon_24_hour_period;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBloodMoonDay() {
/* 11721 */     return this.is_blood_moon_day;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBloodMoonNight() {
/* 11727 */     return this.is_blood_moon_night;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlueMoon24HourPeriod() {
/* 11733 */     return this.is_blue_moon_24_hour_period;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlueMoonDay() {
/* 11739 */     return this.is_blue_moon_day;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBlueMoonNight() {
/* 11745 */     return this.is_blue_moon_night;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isMoonDog24HourPeriod() {
/* 11751 */     return this.is_moon_dog_24_hour_period;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isMoonDogDay() {
/* 11757 */     return this.is_moon_dog_day;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isMoonDogNight() {
/* 11763 */     return this.is_moon_dog_night;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final void tryRemoveFromWorldUniques(ItemStack item_stack) {
/* 11769 */     if (this.isRemote) {
/*       */       
/* 11771 */       Minecraft.setErrorMessage("tryRemoveFromWorldUniques: called on client");
/*       */       
/*       */       return;
/*       */     } 
/* 11775 */     if (item_stack.hasSignature()) {
/* 11776 */       this.worldInfo.removeSignature(item_stack.getSignature());
/*       */     }
/*       */   }
/*       */   
/*       */   public boolean doesLavaFlowQuicklyInThisWorld() {
/* 11781 */     return isTheNether();
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isCeilingBedrock(int x, int y, int z) {
/* 11787 */     return (y > 31 && getBlockId(x, y, z) == Block.bedrock.blockID);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final Block getBottomBlock() {
/* 11793 */     return this.bottom_block;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final int getBottomBlockMetadata() {
/* 11799 */     return this.bottom_block_metadata;
/*       */   }
/*       */ 
/*       */   
/*       */   public final boolean isBottomBlock(Block block, int metadata) {
/* 11804 */     if (this.bottom_block == null) {
/* 11805 */       return false;
/*       */     }
/* 11807 */     if (block != this.bottom_block) {
/* 11808 */       return false;
/*       */     }
/* 11810 */     return (this.bottom_block_metadata < 0 || metadata == this.bottom_block_metadata);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean isBottomBlock(int x, int y, int z) {
/* 11816 */     return isBottomBlock(getBlock(x, y, z), getBlockMetadata(x, y, z));
/*       */   }
/*       */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\World.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */