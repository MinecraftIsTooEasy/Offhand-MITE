/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.main.Main;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldInfoShared
/*     */ {
/*     */   public long randomSeed;
/*     */   public long random_seed_hashed;
/*     */   public WorldType terrainType;
/*     */   public String generatorOptions;
/*     */   public int spawnX;
/*     */   public int spawnY;
/*     */   public int spawnZ;
/*  36 */   private long[] totalTime = new long[4];
/*     */ 
/*     */ 
/*     */   
/*     */   public long lastTimePlayed;
/*     */ 
/*     */ 
/*     */   
/*     */   public long sizeOnDisk;
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound playerTag;
/*     */ 
/*     */ 
/*     */   
/*     */   public int dimension;
/*     */ 
/*     */   
/*     */   public String levelName;
/*     */ 
/*     */   
/*     */   public int saveVersion;
/*     */ 
/*     */   
/*     */   public EnumGameType theGameType;
/*     */ 
/*     */   
/*     */   public boolean mapFeaturesEnabled;
/*     */ 
/*     */   
/*     */   public boolean hardcore;
/*     */ 
/*     */   
/*     */   public boolean allowCommands;
/*     */ 
/*     */   
/*     */   public boolean initialized;
/*     */ 
/*     */   
/*     */   public GameRules theGameRules;
/*     */ 
/*     */   
/*     */   protected byte village_conditions;
/*     */ 
/*     */   
/*  82 */   protected int earliest_MITE_release_run_in = 196;
/*  83 */   protected int latest_MITE_release_run_in = 196;
/*     */ 
/*     */   
/*     */   protected int earliest_allowable_MITE_release;
/*     */ 
/*     */   
/*     */   public boolean is_valid_MITE_world = true;
/*     */   
/*     */   public String is_not_valid_reason;
/*     */   
/*     */   public int sacred_stones_placed;
/*     */   
/*  95 */   protected List curses = new ArrayList();
/*     */ 
/*     */   
/*     */   public long world_creation_time;
/*     */   
/*     */   public long nanotime;
/*     */   
/*     */   public boolean the_underworld_has_been_visited;
/*     */   
/*     */   public boolean the_nether_has_been_visited;
/*     */   
/*     */   public boolean are_skills_enabled;
/*     */   
/* 108 */   private List uniques = new ArrayList();
/*     */   
/* 110 */   private HashMap achievements = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   public WorldInfoShared() {
/* 114 */     this.terrainType = WorldType.DEFAULT;
/* 115 */     this.generatorOptions = "";
/* 116 */     this.theGameRules = new GameRules();
/*     */   }
/*     */ 
/*     */   
/*     */   private long getRandomSeedHashed(long random_seed) {
/* 121 */     Random random = new Random(random_seed);
/*     */     
/* 123 */     random.nextInt();
/*     */     
/* 125 */     return random.nextLong();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldInfoShared(NBTTagCompound par1NBTTagCompound) {
/* 130 */     this.terrainType = WorldType.DEFAULT;
/* 131 */     this.generatorOptions = "";
/* 132 */     this.theGameRules = new GameRules();
/* 133 */     this.randomSeed = par1NBTTagCompound.getLong("RandomSeed");
/*     */     
/* 135 */     this.random_seed_hashed = getRandomSeedHashed(this.randomSeed);
/*     */     
/* 137 */     if (par1NBTTagCompound.hasKey("generatorName")) {
/*     */       
/* 139 */       String var2 = par1NBTTagCompound.getString("generatorName");
/* 140 */       this.terrainType = WorldType.parseWorldType(var2);
/*     */       
/* 142 */       if (this.terrainType == null) {
/*     */         
/* 144 */         this.terrainType = WorldType.DEFAULT;
/*     */       }
/* 146 */       else if (this.terrainType.isVersioned()) {
/*     */         
/* 148 */         int var3 = 0;
/*     */         
/* 150 */         if (par1NBTTagCompound.hasKey("generatorVersion"))
/*     */         {
/* 152 */           var3 = par1NBTTagCompound.getInteger("generatorVersion");
/*     */         }
/*     */         
/* 155 */         this.terrainType = this.terrainType.getWorldTypeForGeneratorVersion(var3);
/*     */       } 
/*     */       
/* 158 */       if (par1NBTTagCompound.hasKey("generatorOptions"))
/*     */       {
/* 160 */         this.generatorOptions = par1NBTTagCompound.getString("generatorOptions");
/*     */       }
/*     */     } 
/*     */     
/* 164 */     this.theGameType = EnumGameType.getByID(par1NBTTagCompound.getInteger("GameType"));
/*     */     
/* 166 */     if (par1NBTTagCompound.hasKey("MapFeatures")) {
/*     */       
/* 168 */       this.mapFeaturesEnabled = par1NBTTagCompound.getBoolean("MapFeatures");
/*     */     }
/*     */     else {
/*     */       
/* 172 */       this.mapFeaturesEnabled = true;
/*     */     } 
/*     */     
/* 175 */     this.spawnX = par1NBTTagCompound.getInteger("SpawnX");
/* 176 */     this.spawnY = par1NBTTagCompound.getInteger("SpawnY");
/* 177 */     this.spawnZ = par1NBTTagCompound.getInteger("SpawnZ");
/*     */ 
/*     */     
/* 180 */     for (int i = 0; i < this.totalTime.length; i++) {
/* 181 */       this.totalTime[i] = par1NBTTagCompound.getLong("Time" + i);
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
/*     */     
/* 194 */     if (par1NBTTagCompound.hasKey("Time") || par1NBTTagCompound.hasKey("DayTime")) {
/*     */       
/* 196 */       this.is_valid_MITE_world = false;
/*     */       
/* 198 */       this.is_not_valid_reason = Translator.get("invalidWorld.outdated");
/*     */     } 
/*     */     
/* 201 */     this.lastTimePlayed = par1NBTTagCompound.getLong("LastPlayed");
/* 202 */     this.sizeOnDisk = par1NBTTagCompound.getLong("SizeOnDisk");
/* 203 */     this.levelName = par1NBTTagCompound.getString("LevelName");
/* 204 */     this.saveVersion = par1NBTTagCompound.getInteger("version");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     this.hardcore = par1NBTTagCompound.getBoolean("hardcore");
/*     */     
/* 212 */     if (par1NBTTagCompound.hasKey("initialized")) {
/*     */       
/* 214 */       this.initialized = par1NBTTagCompound.getBoolean("initialized");
/*     */     }
/*     */     else {
/*     */       
/* 218 */       this.initialized = true;
/*     */     } 
/*     */     
/* 221 */     if (par1NBTTagCompound.hasKey("allowCommands")) {
/*     */       
/* 223 */       this.allowCommands = par1NBTTagCompound.getBoolean("allowCommands");
/*     */     }
/*     */     else {
/*     */       
/* 227 */       this.allowCommands = (this.theGameType == EnumGameType.CREATIVE);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     if (!par1NBTTagCompound.hasKey("last_run_on_MITE_DS") || Main.is_MITE_DS)
/*     */     {
/*     */ 
/*     */       
/* 240 */       if (par1NBTTagCompound.hasKey("last_run_on_MITE_DS") || !Main.is_MITE_DS)
/*     */       {
/*     */ 
/*     */         
/* 244 */         if (par1NBTTagCompound.hasKey("Player")) {
/*     */           
/* 246 */           this.playerTag = par1NBTTagCompound.getCompoundTag("Player");
/* 247 */           this.dimension = this.playerTag.getInteger("Dimension");
/*     */         }  } 
/*     */     }
/* 250 */     if (par1NBTTagCompound.hasKey("GameRules"))
/*     */     {
/* 252 */       this.theGameRules.readGameRulesFromNBT(par1NBTTagCompound.getCompoundTag("GameRules"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 257 */     this.village_conditions = par1NBTTagCompound.getByte("village_conditions");
/*     */     
/* 259 */     if (par1NBTTagCompound.hasKey("earliest_MITE_release_run_in")) {
/*     */       
/* 261 */       this.earliest_MITE_release_run_in = par1NBTTagCompound.getInteger("earliest_MITE_release_run_in");
/*     */     }
/*     */     else {
/*     */       
/* 265 */       this.earliest_MITE_release_run_in = 0;
/* 266 */       this.is_valid_MITE_world = false;
/*     */     } 
/*     */     
/* 269 */     if (par1NBTTagCompound.hasKey("latest_MITE_release_run_in")) {
/* 270 */       this.latest_MITE_release_run_in = par1NBTTagCompound.getInteger("latest_MITE_release_run_in");
/*     */     } else {
/* 272 */       this.is_valid_MITE_world = false;
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
/* 283 */     if (this.earliest_MITE_release_run_in < 172) {
/*     */       
/* 285 */       this.is_valid_MITE_world = false;
/*     */       
/* 287 */       this.is_not_valid_reason = Translator.get("invalidWorld.outdated");
/*     */     }
/*     */     else {
/*     */       
/* 291 */       int[] incompatible_releases = Minecraft.incompatible_releases;
/*     */       
/* 293 */       for (int j = 0; j < incompatible_releases.length; j++) {
/*     */         
/* 295 */         if (this.earliest_MITE_release_run_in == incompatible_releases[j] || this.latest_MITE_release_run_in == incompatible_releases[j]) {
/*     */           
/* 297 */           this.is_valid_MITE_world = false;
/*     */           
/* 299 */           this.is_not_valid_reason = Translator.get("invalidWorld.incompatible");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     if (this.latest_MITE_release_run_in < 196) {
/*     */       
/* 318 */       this.latest_MITE_release_run_in = 196;
/*     */     }
/* 320 */     else if (this.latest_MITE_release_run_in > 196) {
/*     */       
/* 322 */       this.is_valid_MITE_world = false;
/*     */       
/* 324 */       this.is_not_valid_reason = Translator.getFormatted("invalidWorld.needsRelease", new Object[] { Integer.valueOf(this.latest_MITE_release_run_in) });
/*     */     } 
/*     */     
/* 327 */     if (par1NBTTagCompound.hasKey("earliest_allowable_MITE_release")) {
/* 328 */       this.earliest_allowable_MITE_release = par1NBTTagCompound.getInteger("earliest_allowable_MITE_release");
/*     */     }
/* 330 */     if (this.earliest_allowable_MITE_release > 196) {
/*     */       
/* 332 */       this.is_valid_MITE_world = false;
/*     */       
/* 334 */       this.is_not_valid_reason = Translator.getFormatted("invalidWorld.needsRelease", new Object[] { Integer.valueOf(this.earliest_allowable_MITE_release) });
/*     */     } 
/*     */     
/* 337 */     if (par1NBTTagCompound.hasKey("curses")) {
/*     */       
/* 339 */       String[] curses = par1NBTTagCompound.getString("curses").split("\\|");
/*     */       
/* 341 */       for (int j = 0; j < curses.length; j++) {
/*     */         
/* 343 */         String[] curse_fields = curses[j].split(":");
/*     */         
/* 345 */         Curse curse = new Curse(curse_fields[0], UUID.fromString(curse_fields[1]), Curse.cursesList[Integer.valueOf(curse_fields[2]).intValue()], Long.valueOf(curse_fields[3]).longValue(), Boolean.valueOf(curse_fields[4]).booleanValue(), Boolean.valueOf(curse_fields[5]).booleanValue());
/*     */         
/* 347 */         curse.effect_has_already_been_learned = curse.effect_known;
/*     */         
/* 349 */         this.curses.add(curse);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 354 */     if (par1NBTTagCompound.hasKey("sacred_stones_placed")) {
/* 355 */       this.sacred_stones_placed = par1NBTTagCompound.getInteger("sacred_stones_placed");
/*     */     }
/* 357 */     this.the_underworld_has_been_visited = par1NBTTagCompound.getBoolean("the_underworld_has_been_visited");
/* 358 */     this.the_nether_has_been_visited = par1NBTTagCompound.getBoolean("the_nether_has_been_visited");
/*     */     
/* 360 */     this.are_skills_enabled = par1NBTTagCompound.getBoolean("are_skills_enabled");
/*     */     
/* 362 */     if (this.initialized) {
/*     */       
/* 364 */       if (par1NBTTagCompound.hasKey("world_creation_time")) {
/* 365 */         this.world_creation_time = par1NBTTagCompound.getLong("world_creation_time");
/*     */       } else {
/* 367 */         this.is_valid_MITE_world = false;
/*     */       } 
/* 369 */       if (par1NBTTagCompound.hasKey("nanotime")) {
/* 370 */         this.nanotime = par1NBTTagCompound.getLong("nanotime");
/*     */       } else {
/* 372 */         this.is_valid_MITE_world = false;
/*     */       } 
/* 374 */       if (this.nanotime != calcChecksum()) {
/* 375 */         this.is_valid_MITE_world = false;
/*     */       }
/*     */     } 
/* 378 */     if (!this.is_valid_MITE_world && MinecraftServer.getServer() instanceof DedicatedServer) {
/*     */       
/* 380 */       System.out.println((this.is_not_valid_reason == null) ? "Invalid world" : this.is_not_valid_reason);
/* 381 */       System.exit(0);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 386 */     if (par1NBTTagCompound.hasKey("uniques")) {
/*     */       
/* 388 */       int[] uniques = par1NBTTagCompound.getIntArray("uniques");
/*     */       
/* 390 */       this.uniques.clear();
/*     */       
/* 392 */       for (int j = 0; j < uniques.length; j++) {
/* 393 */         this.uniques.add(Integer.valueOf(uniques[j]));
/*     */       }
/*     */     } 
/* 396 */     if (par1NBTTagCompound.hasKey("achievements")) {
/*     */       
/* 398 */       NBTTagList achievements = par1NBTTagCompound.getTagList("achievements");
/*     */ 
/*     */ 
/*     */       
/* 402 */       this.achievements.clear();
/*     */       
/* 404 */       for (int j = 0; j < achievements.tagCount(); j++) {
/*     */         
/* 406 */         WorldAchievement wa = new WorldAchievement((NBTTagCompound)achievements.tagAt(j));
/* 407 */         this.achievements.put(wa.achievement, wa);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldInfoShared(WorldSettings world_settings, String level_name) {
/* 416 */     this.terrainType = WorldType.DEFAULT;
/* 417 */     this.generatorOptions = "";
/* 418 */     this.theGameRules = new GameRules();
/* 419 */     this.randomSeed = world_settings.getSeed();
/*     */     
/* 421 */     this.random_seed_hashed = getRandomSeedHashed(this.randomSeed);
/*     */     
/* 423 */     this.theGameType = world_settings.getGameType();
/* 424 */     this.mapFeaturesEnabled = world_settings.isMapFeaturesEnabled();
/* 425 */     this.levelName = level_name;
/* 426 */     this.hardcore = world_settings.getHardcoreEnabled();
/* 427 */     this.terrainType = world_settings.getTerrainType();
/* 428 */     this.generatorOptions = world_settings.func_82749_j();
/* 429 */     this.allowCommands = world_settings.areCommandsAllowed();
/* 430 */     this.initialized = false;
/*     */     
/* 432 */     this.are_skills_enabled = world_settings.areSkillsEnabled();
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
/*     */   public void updateTagCompound(NBTTagCompound par1NBTTagCompound, NBTTagCompound par2NBTTagCompound) {
/* 471 */     par1NBTTagCompound.setLong("RandomSeed", this.randomSeed);
/* 472 */     par1NBTTagCompound.setString("generatorName", this.terrainType.getWorldTypeName());
/* 473 */     par1NBTTagCompound.setInteger("generatorVersion", this.terrainType.getGeneratorVersion());
/* 474 */     par1NBTTagCompound.setString("generatorOptions", this.generatorOptions);
/* 475 */     par1NBTTagCompound.setInteger("GameType", this.theGameType.getID());
/* 476 */     par1NBTTagCompound.setBoolean("MapFeatures", this.mapFeaturesEnabled);
/* 477 */     par1NBTTagCompound.setInteger("SpawnX", this.spawnX);
/* 478 */     par1NBTTagCompound.setInteger("SpawnY", this.spawnY);
/* 479 */     par1NBTTagCompound.setInteger("SpawnZ", this.spawnZ);
/*     */ 
/*     */     
/* 482 */     for (int i = 0; i < this.totalTime.length; i++) {
/* 483 */       par1NBTTagCompound.setLong("Time" + i, this.totalTime[i]);
/*     */     }
/*     */     
/* 486 */     par1NBTTagCompound.setLong("SizeOnDisk", this.sizeOnDisk);
/* 487 */     par1NBTTagCompound.setLong("LastPlayed", MinecraftServer.getSystemTimeMillis());
/* 488 */     par1NBTTagCompound.setString("LevelName", this.levelName);
/* 489 */     par1NBTTagCompound.setInteger("version", this.saveVersion);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 495 */     par1NBTTagCompound.setBoolean("hardcore", this.hardcore);
/* 496 */     par1NBTTagCompound.setBoolean("allowCommands", this.allowCommands);
/* 497 */     par1NBTTagCompound.setBoolean("initialized", this.initialized);
/* 498 */     par1NBTTagCompound.setCompoundTag("GameRules", this.theGameRules.writeGameRulesToNBT());
/*     */     
/* 500 */     par1NBTTagCompound.setByte("village_conditions", this.village_conditions);
/* 501 */     par1NBTTagCompound.setInteger("earliest_MITE_release_run_in", this.earliest_MITE_release_run_in);
/* 502 */     par1NBTTagCompound.setInteger("latest_MITE_release_run_in", this.latest_MITE_release_run_in);
/*     */     
/* 504 */     par1NBTTagCompound.setInteger("earliest_allowable_MITE_release", this.earliest_allowable_MITE_release);
/*     */     
/* 506 */     if (Main.is_MITE_DS) {
/* 507 */       par1NBTTagCompound.setBoolean("last_run_on_MITE_DS", true);
/*     */     }
/* 509 */     if (par2NBTTagCompound != null)
/*     */     {
/* 511 */       par1NBTTagCompound.setCompoundTag("Player", par2NBTTagCompound);
/*     */     }
/*     */     
/* 514 */     if (!this.curses.isEmpty()) {
/*     */       
/* 516 */       StringBuffer sb = new StringBuffer();
/*     */       
/* 518 */       Iterator<Curse> iterator = this.curses.iterator();
/*     */       
/* 520 */       while (iterator.hasNext()) {
/*     */         
/* 522 */         Curse curse = iterator.next();
/*     */         
/* 524 */         sb.append('|');
/* 525 */         sb.append(curse.cursed_player_username);
/* 526 */         sb.append(':');
/* 527 */         sb.append(curse.cursing_entity_uuid.toString());
/* 528 */         sb.append(':');
/* 529 */         sb.append(curse.id);
/* 530 */         sb.append(':');
/* 531 */         sb.append(curse.time_of_realization);
/* 532 */         sb.append(':');
/* 533 */         sb.append(curse.has_been_realized);
/* 534 */         sb.append(':');
/* 535 */         sb.append(curse.effect_known);
/*     */       } 
/*     */       
/* 538 */       par1NBTTagCompound.setString("curses", sb.substring(1));
/*     */     } 
/*     */     
/* 541 */     par1NBTTagCompound.setInteger("sacred_stones_placed", this.sacred_stones_placed);
/*     */     
/* 543 */     par1NBTTagCompound.setBoolean("the_underworld_has_been_visited", this.the_underworld_has_been_visited);
/* 544 */     par1NBTTagCompound.setBoolean("the_nether_has_been_visited", this.the_nether_has_been_visited);
/*     */     
/* 546 */     if (this.are_skills_enabled) {
/* 547 */       par1NBTTagCompound.setBoolean("are_skills_enabled", this.are_skills_enabled);
/*     */     }
/*     */     
/* 550 */     if (this.world_creation_time != 0L) {
/*     */       
/* 552 */       par1NBTTagCompound.setLong("world_creation_time", this.world_creation_time);
/* 553 */       par1NBTTagCompound.setLong("nanotime", this.nanotime);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 558 */     if (this.uniques.size() > 0) {
/*     */       
/* 560 */       int[] uniques = new int[this.uniques.size()];
/*     */       
/* 562 */       for (int j = 0; j < uniques.length; j++) {
/* 563 */         uniques[j] = ((Integer)this.uniques.get(j)).intValue();
/*     */       }
/* 565 */       par1NBTTagCompound.setIntArray("uniques", uniques);
/*     */     } 
/*     */     
/* 568 */     if (this.achievements.size() > 0) {
/*     */       
/* 570 */       NBTTagList achievements = new NBTTagList();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 575 */       Iterator<Map.Entry> iterator = this.achievements.entrySet().iterator();
/*     */       
/* 577 */       while (iterator.hasNext()) {
/*     */         
/* 579 */         Map.Entry entry = iterator.next();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 584 */         achievements.appendTag(((WorldAchievement)entry.getValue()).getAsNBTTagCompound());
/*     */       } 
/*     */       
/* 587 */       par1NBTTagCompound.setTag("achievements", achievements);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int calcChecksum() {
/* 593 */     int checksum = 0;
/*     */     
/* 595 */     checksum += this.earliest_MITE_release_run_in * 67;
/* 596 */     checksum += (int)this.randomSeed * 83;
/* 597 */     checksum = (int)(checksum + this.world_creation_time * 561L);
/*     */     
/* 599 */     return checksum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSignatureBeenAdded(int id) {
/* 605 */     return this.uniques.contains(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSignature(int id) {
/* 611 */     if (hasSignatureBeenAdded(id)) {
/*     */       
/* 613 */       Minecraft.setErrorMessage("addSignature: signature already exists in list " + id);
/*     */       return;
/*     */     } 
/* 616 */     if (Minecraft.inDevMode())
/*     */     {
/* 618 */       System.out.println("Unique added to world, signature=" + id);
/*     */     }
/*     */     
/* 621 */     this.uniques.add(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeSignature(int id) {
/* 627 */     if (Minecraft.inDevMode()) {
/* 628 */       System.out.println("removeSignature: trying to remove " + id);
/*     */     }
/* 630 */     return (hasSignatureBeenAdded(id) && this.uniques.remove(new Integer(id)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumSignatures() {
/* 636 */     return this.uniques.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAchievementUnlocked(Achievement achievement) {
/* 641 */     return this.achievements.containsKey(achievement);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unlockAchievement(Achievement achievement, String username, int day, boolean update_clients) {
/* 646 */     if (!hasAchievementUnlocked(achievement)) {
/*     */       
/* 648 */       this.achievements.put(achievement, new WorldAchievement(achievement, username, day));
/*     */       
/* 650 */       if (update_clients) {
/* 651 */         MinecraftServer.getServer(); MinecraftServer.sendPacketToAllPlayersOnServer(new Packet93WorldAchievement(achievement, username, day));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unlockAchievement(Achievement achievement, EntityPlayer player) {
/* 657 */     unlockAchievement(achievement, player.username, player.worldObj.getDayOfWorld(), player instanceof ServerPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAchievements(HashMap achievements) {
/* 662 */     this.achievements = achievements;
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
/*     */   public HashMap getAchievements() {
/* 679 */     return this.achievements;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldAchievement getWorldAchievement(Achievement achievement) {
/* 684 */     return (WorldAchievement)this.achievements.get(achievement);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean haveAchievementsBeenUnlockedByOtherPlayers(EntityPlayer player) {
/* 689 */     Iterator<Map.Entry> i = this.achievements.entrySet().iterator();
/*     */     
/* 691 */     while (i.hasNext()) {
/*     */       
/* 693 */       Map.Entry entry = i.next();
/*     */       
/* 695 */       WorldAchievement wa = (WorldAchievement)entry.getValue();
/*     */       
/* 697 */       if (!player.username.equals(wa.username)) {
/* 698 */         return true;
/*     */       }
/*     */     } 
/* 701 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getWorldIndexForDimensionId(int dimension_id) {
/* 708 */     return MinecraftServer.getWorldIndexForDimensionId(dimension_id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTotalWorldTime(World world, long total_world_time) {
/* 713 */     this.totalTime[getWorldIndexForDimensionId(world.getDimensionId())] = total_world_time;
/*     */ 
/*     */ 
/*     */     
/* 717 */     world.updateTickFlags();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTotalWorldTimes(long[] total_world_times, WorldClient world) {
/* 722 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 724 */       this.totalTime[i] = total_world_times[i];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 730 */     world.updateTickFlags();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTotalWorldTime(int dimension_id) {
/* 735 */     return this.totalTime[getWorldIndexForDimensionId(dimension_id)];
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldInfoShared.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */