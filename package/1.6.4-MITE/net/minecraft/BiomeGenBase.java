/*     */ package net.minecraft;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BiomeGenBase
/*     */ {
/*  14 */   public static final BiomeGenBase[] biomeList = new BiomeGenBase[256];
/*  15 */   public static final BiomeGenBase ocean = (new BiomeGenOcean(0)).setColor(112).setBiomeName("Ocean").setMinMaxHeight(-1.0F, 0.4F);
/*  16 */   public static final BiomeGenBase plains = (new BiomeGenPlains(1)).setColor(9286496).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F);
/*     */   
/*  18 */   public static final BiomeGenBase desert = (new BiomeGenDesert(2)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(1.6F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
/*     */   
/*  20 */   public static final BiomeGenBase extremeHills = (new BiomeGenHills(3)).setColor(6316128).setBiomeName("Extreme Hills").setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.4F, 0.3F);
/*  21 */   public static final BiomeGenBase forest = (new BiomeGenForest(4)).setColor(353825).setBiomeName("Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F);
/*  22 */   public static final BiomeGenBase taiga = (new BiomeGenTaiga(5)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.1F, 0.4F);
/*  23 */   public static final BiomeGenBase swampland = (new BiomeGenSwamp(6)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
/*  24 */   public static final BiomeGenBase river = (new BiomeGenRiver(7)).setColor(255).setBiomeName("River").setMinMaxHeight(-0.5F, 0.0F);
/*  25 */   public static final BiomeGenBase hell = (new BiomeGenHell(8)).setColor(16711680).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
/*  26 */   public static final BiomeGenBase underworld = (new BiomeGenUnderworld(26)).setColor(16711680).setBiomeName("Underworld").setDisableRain().setTemperatureRainfall(1.0F, 0.0F);
/*     */ 
/*     */   
/*  29 */   public static final BiomeGenBase sky = (new BiomeGenEnd(9)).setColor(8421631).setBiomeName("Sky").setDisableRain();
/*  30 */   public static final BiomeGenBase frozenOcean = (new BiomeGenOcean(10)).setColor(9474208).setBiomeName("FrozenOcean").setEnableSnow().setMinMaxHeight(-1.0F, 0.5F).setTemperatureRainfall(0.0F, 0.5F);
/*  31 */   public static final BiomeGenBase frozenRiver = (new BiomeGenRiver(11)).setColor(10526975).setBiomeName("FrozenRiver").setEnableSnow().setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.0F, 0.5F);
/*  32 */   public static final BiomeGenBase icePlains = (new BiomeGenSnow(12)).setColor(16777215).setBiomeName("Ice Plains").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
/*  33 */   public static final BiomeGenBase iceMountains = (new BiomeGenSnow(13)).setColor(10526880).setBiomeName("Ice Mountains").setEnableSnow().setMinMaxHeight(0.3F, 1.3F).setTemperatureRainfall(0.0F, 0.5F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public static final BiomeGenBase beach = (new BiomeGenBeach(16)).setColor(16440917).setBiomeName("Beach").setTemperatureRainfall(1.0F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static final BiomeGenBase desertHills = (new BiomeGenDesert(17)).setColor(13786898).setBiomeName("DesertHills").setDisableRain().setTemperatureRainfall(1.6F, 0.0F).setMinMaxHeight(0.3F, 0.8F);
/*     */ 
/*     */   
/*  46 */   public static final BiomeGenBase forestHills = (new BiomeGenForest(18)).setColor(2250012).setBiomeName("ForestHills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.7F);
/*     */ 
/*     */   
/*  49 */   public static final BiomeGenBase taigaHills = (new BiomeGenTaiga(19)).setColor(1456435).setBiomeName("TaigaHills").setEnableSnow().func_76733_a(5159473).setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.3F, 0.8F);
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static final BiomeGenBase extremeHillsEdge = (new BiomeGenHills(20)).setColor(7501978).setBiomeName("Extreme Hills Edge").setMinMaxHeight(0.2F, 0.8F).setTemperatureRainfall(0.4F, 0.3F);
/*     */ 
/*     */   
/*  56 */   public static final BiomeGenBase jungle = (new BiomeGenJungle(21)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.4F);
/*  57 */   public static final BiomeGenBase jungleHills = (new BiomeGenJungle(22)).setColor(2900485).setBiomeName("JungleHills").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.8F, 0.5F);
/*     */   
/*  59 */   public static final BiomeGenBase desertRiver = (new BiomeGenRiver(23)).setColor(255).setBiomeName("DesertRiver").setMinMaxHeight(-0.5F, 0.0F).setDisableRain().setTemperatureRainfall(1.4F, 0.0F);
/*  60 */   public static final BiomeGenBase jungleRiver = (new BiomeGenRiver(24)).setColor(255).setBiomeName("JungleRiver").setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(1.0F, 0.9F);
/*  61 */   public static final BiomeGenBase swampRiver = (new BiomeGenRiver(25)).setColor(255).setBiomeName("SwampRiver").setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.8F, 0.9F);
/*     */ 
/*     */   
/*     */   public String biomeName;
/*     */ 
/*     */   
/*     */   public int color;
/*     */ 
/*     */   
/*     */   public byte topBlock;
/*     */ 
/*     */   
/*     */   public byte fillerBlock;
/*     */ 
/*     */   
/*     */   public int field_76754_C;
/*     */ 
/*     */   
/*     */   public float minHeight;
/*     */ 
/*     */   
/*     */   public float maxHeight;
/*     */ 
/*     */   
/*     */   public float temperature;
/*     */ 
/*     */   
/*     */   public float rainfall;
/*     */ 
/*     */   
/*     */   public int waterColorMultiplier;
/*     */ 
/*     */   
/*     */   public BiomeDecorator theBiomeDecorator;
/*     */ 
/*     */   
/*     */   protected List spawnableMonsterList;
/*     */ 
/*     */   
/*     */   protected List spawnableCreatureList;
/*     */ 
/*     */   
/*     */   protected List spawnableWaterCreatureList;
/*     */ 
/*     */   
/*     */   protected List spawnableCaveCreatureList;
/*     */ 
/*     */   
/*     */   private boolean enableSnow;
/*     */ 
/*     */   
/*     */   private boolean enableRain;
/*     */ 
/*     */   
/*     */   public final int biomeID;
/*     */ 
/*     */   
/*     */   protected WorldGenTrees worldGeneratorTrees;
/*     */ 
/*     */   
/*     */   protected WorldGenBigTree worldGeneratorBigTree;
/*     */ 
/*     */   
/*     */   protected WorldGenForest worldGeneratorForest;
/*     */ 
/*     */   
/*     */   protected WorldGenSwamp worldGeneratorSwamp;
/*     */ 
/*     */ 
/*     */   
/*     */   protected BiomeGenBase(int par1) {
/* 132 */     this.topBlock = (byte)Block.grass.blockID;
/* 133 */     this.fillerBlock = (byte)Block.dirt.blockID;
/* 134 */     this.field_76754_C = 5169201;
/* 135 */     this.minHeight = 0.1F;
/* 136 */     this.maxHeight = 0.3F;
/* 137 */     this.temperature = 0.5F;
/* 138 */     this.rainfall = 0.5F;
/* 139 */     this.waterColorMultiplier = 16777215;
/* 140 */     this.spawnableMonsterList = new ArrayList();
/* 141 */     this.spawnableCreatureList = new ArrayList();
/* 142 */     this.spawnableWaterCreatureList = new ArrayList();
/* 143 */     this.spawnableCaveCreatureList = new ArrayList();
/* 144 */     this.enableRain = true;
/* 145 */     this.worldGeneratorTrees = new WorldGenTrees(false);
/* 146 */     this.worldGeneratorBigTree = new WorldGenBigTree(false);
/* 147 */     this.worldGeneratorForest = new WorldGenForest(false);
/* 148 */     this.worldGeneratorSwamp = new WorldGenSwamp();
/* 149 */     this.biomeID = par1;
/* 150 */     biomeList[par1] = this;
/* 151 */     this.theBiomeDecorator = createBiomeDecorator();
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
/* 169 */     this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 10, 1, 1));
/* 170 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 1, 1));
/* 171 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 1, 1));
/* 172 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 10, 1, 1));
/*     */     
/* 174 */     this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 80, 1, 2));
/* 175 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 100, 1, 4));
/* 176 */     this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 100, 1, 4));
/* 177 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 100, 1, 2));
/* 178 */     this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 100, 1, 4));
/* 179 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 4));
/*     */     
/* 181 */     this.spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
/*     */ 
/*     */     
/* 184 */     this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 100, 8, 8));
/* 185 */     this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityVampireBat.class, 20, 8, 8));
/* 186 */     this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityNightwing.class, 4, 1, 4));
/*     */     
/* 188 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityGhoul.class, 10, 1, 1));
/* 189 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityWight.class, 10, 1, 1));
/* 190 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityInvisibleStalker.class, 10, 1, 1));
/* 191 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityDemonSpider.class, 10, 1, 1));
/* 192 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityHellhound.class, 10, 1, 2));
/*     */     
/* 194 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityWoodSpider.class, 20, 1, 1));
/* 195 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityShadow.class, 10, 1, 1));
/*     */     
/* 197 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityRevenant.class, 10, 1, 1));
/* 198 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityEarthElemental.class, 10, 1, 1));
/* 199 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityJelly.class, 30, 1, 4));
/* 200 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityBlob.class, 30, 1, 4));
/* 201 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityOoze.class, 20, 1, 4));
/* 202 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityPudding.class, 30, 1, 4));
/* 203 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityClayGolem.class, 50, 1, 1));
/* 204 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityBoneLord.class, 5, 1, 1));
/* 205 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityPhaseSpider.class, 5, 1, 4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BiomeDecorator createBiomeDecorator() {
/* 215 */     return new BiomeDecorator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BiomeGenBase setTemperatureRainfall(float par1, float par2) {
/* 223 */     if (par1 > 0.1F && par1 < 0.2F)
/*     */     {
/* 225 */       throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
/*     */     }
/*     */ 
/*     */     
/* 229 */     this.temperature = par1;
/* 230 */     this.rainfall = par2;
/* 231 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRainfall() {
/* 237 */     return (this.enableRain && this.rainfall > 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BiomeGenBase setMinMaxHeight(float par1, float par2) {
/* 245 */     this.minHeight = par1;
/* 246 */     this.maxHeight = par2;
/* 247 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BiomeGenBase setDisableRain() {
/* 255 */     this.enableRain = false;
/* 256 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
/* 264 */     return (par1Random.nextInt(10) == 0) ? this.worldGeneratorBigTree : this.worldGeneratorTrees;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
/* 272 */     return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BiomeGenBase setEnableSnow() {
/* 280 */     this.enableSnow = true;
/* 281 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase setBiomeName(String par1Str) {
/* 286 */     this.biomeName = par1Str;
/* 287 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase func_76733_a(int par1) {
/* 292 */     this.field_76754_C = par1;
/* 293 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase setColor(int par1) {
/* 298 */     this.color = par1;
/* 299 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSkyColorByTemp(float par1) {
/* 307 */     par1 /= 3.0F;
/*     */     
/* 309 */     if (par1 < -1.0F)
/*     */     {
/* 311 */       par1 = -1.0F;
/*     */     }
/*     */     
/* 314 */     if (par1 > 1.0F)
/*     */     {
/* 316 */       par1 = 1.0F;
/*     */     }
/*     */     
/* 319 */     return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSpawnableList(EnumCreatureType par1EnumCreatureType) {
/* 327 */     return (par1EnumCreatureType == EnumCreatureType.monster) ? this.spawnableMonsterList : ((par1EnumCreatureType == EnumCreatureType.animal) ? this.spawnableCreatureList : ((par1EnumCreatureType == EnumCreatureType.aquatic) ? this.spawnableWaterCreatureList : ((par1EnumCreatureType == EnumCreatureType.ambient) ? this.spawnableCaveCreatureList : null)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getEnableSnow() {
/* 335 */     return this.enableSnow;
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
/*     */   public boolean canSpawnLightningBolt(boolean is_blood_moon) {
/* 348 */     return this.enableSnow ? false : ((this.enableRain || is_blood_moon));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHighHumidity() {
/* 356 */     return (this.rainfall > 0.85F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSpawningChance() {
/* 364 */     if (isFreezing()) {
/* 365 */       return 0.05F;
/*     */     }
/* 367 */     return 0.1F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIntRainfall() {
/* 375 */     return (int)(this.rainfall * 65536.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIntTemperature() {
/* 383 */     return (int)(this.temperature * 65536.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getFloatRainfall() {
/* 391 */     return this.rainfall;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getFloatTemperature() {
/* 399 */     return this.temperature;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateSilverfishBlocks(World par1World, Random par2Random, int par3, int par4) {
/* 405 */     int max_height = 64;
/* 406 */     int sum_height = 0;
/*     */     
/* 408 */     for (int dx = 0; dx < 16; dx++) {
/*     */       
/* 410 */       for (int dz = 0; dz < 16; dz++) {
/*     */         
/* 412 */         int height = par1World.getHeightValue(par3 + dx, par4 + dz);
/*     */         
/* 414 */         if (height > max_height) {
/* 415 */           max_height = height;
/*     */         }
/* 417 */         sum_height += height;
/*     */       } 
/*     */     } 
/*     */     
/* 421 */     int vein_size = Math.max((sum_height - 8192) / 256 / 8, 2);
/* 422 */     int num_vein_placement_attempts = vein_size - 1;
/*     */     
/* 424 */     WorldGenerator silverfish_generator = new WorldGenMinable(Block.silverfish.blockID, vein_size);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 431 */     for (int var5 = 0; var5 < num_vein_placement_attempts; var5++) {
/*     */       
/* 433 */       int var6 = par3 + par2Random.nextInt(16);
/* 434 */       int var7 = par2Random.nextInt(max_height);
/* 435 */       int var8 = par4 + par2Random.nextInt(16);
/* 436 */       silverfish_generator.generate(par1World, par2Random, var6, var7, var8);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decorate(World par1World, Random par2Random, int par3, int par4) {
/* 442 */     this.theBiomeDecorator.decorate(par1World, par2Random, par3, par4);
/*     */     
/* 444 */     if (par1World.isOverworld()) {
/* 445 */       generateSilverfishBlocks(par1World, par2Random, par3, par4);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBiomeGrassColor() {
/* 453 */     double var1 = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
/* 454 */     double var3 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
/* 455 */     return ColorizerGrass.getGrassColor(var1, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBiomeFoliageColor() {
/* 463 */     double var1 = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
/* 464 */     double var3 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
/* 465 */     return ColorizerFoliage.getFoliageColor(var1, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFreezing() {
/* 470 */     return (this.temperature <= 0.15F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isJungleBiome() {
/* 475 */     return (this == jungle || this == jungleHills || this == jungleRiver);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDesertBiome() {
/* 480 */     return (this == desert || this == desertHills || this == desertRiver);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSwampBiome() {
/* 485 */     return (this == swampland || this == swampRiver);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHillyOrMountainous() {
/* 490 */     return (this == extremeHills || this == iceMountains || this == desertHills || this == forestHills || this == taigaHills || this == extremeHillsEdge || this == jungleHills);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveMineshafts() {
/* 495 */     return isHillyOrMountainous();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeEntityFromSpawnableList(List list, Class _class) {
/* 500 */     Iterator<SpawnListEntry> i = list.iterator();
/*     */     
/* 502 */     while (i.hasNext()) {
/*     */       
/* 504 */       SpawnListEntry entry = i.next();
/*     */       
/* 506 */       if (entry.entityClass == _class) {
/* 507 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeEntityFromSpawnableLists(Class _class) {
/* 514 */     removeEntityFromSpawnableList(this.spawnableMonsterList, _class);
/* 515 */     removeEntityFromSpawnableList(this.spawnableCreatureList, _class);
/* 516 */     removeEntityFromSpawnableList(this.spawnableWaterCreatureList, _class);
/* 517 */     removeEntityFromSpawnableList(this.spawnableCaveCreatureList, _class);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */