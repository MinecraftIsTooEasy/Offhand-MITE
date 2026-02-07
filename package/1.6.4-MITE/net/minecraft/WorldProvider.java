/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class WorldProvider
/*     */ {
/*   7 */   public static final float[] moonPhaseFactors = new float[] { 1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F };
/*     */ 
/*     */ 
/*     */   
/*     */   public World worldObj;
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldType terrainType;
/*     */ 
/*     */   
/*     */   public String field_82913_c;
/*     */ 
/*     */   
/*     */   public WorldChunkManager worldChunkMgr;
/*     */ 
/*     */   
/*     */   public final boolean isHellWorld;
/*     */ 
/*     */   
/*     */   public final boolean hasNoSky;
/*     */ 
/*     */   
/*  30 */   public float[] lightBrightnessTable = new float[16];
/*     */ 
/*     */ 
/*     */   
/*     */   public final int dimensionId;
/*     */ 
/*     */   
/*  37 */   private float[] colorsSunriseSunset = new float[4];
/*     */   
/*     */   private final boolean is_underworld;
/*     */   
/*     */   private final boolean is_nether;
/*     */   
/*     */   private final boolean is_overworld;
/*     */   
/*     */   private final boolean is_the_end;
/*     */   
/*     */   private final String name;
/*     */   private final int block_domain_radius;
/*     */   private int weather_forecast;
/*     */   private int last_forecast_day;
/*     */   
/*     */   public WorldProvider(int dimension_id, String name) {
/*  53 */     this.dimensionId = dimension_id;
/*     */     
/*  55 */     boolean is_underworld = false;
/*  56 */     boolean is_nether = false;
/*  57 */     boolean is_overworld = false;
/*  58 */     boolean is_the_end = false;
/*     */     
/*  60 */     if (this.dimensionId == -2) {
/*  61 */       is_underworld = true;
/*  62 */     } else if (this.dimensionId == -1) {
/*  63 */       is_nether = true;
/*  64 */     } else if (this.dimensionId == 0) {
/*  65 */       is_overworld = true;
/*  66 */     } else if (this.dimensionId == 1) {
/*  67 */       is_the_end = true;
/*     */     } else {
/*  69 */       Minecraft.setErrorMessage("WorldProvider: Unrecognized dimension id " + this.dimensionId);
/*     */     } 
/*  71 */     this.is_underworld = is_underworld;
/*  72 */     this.is_nether = is_nether;
/*  73 */     this.is_overworld = is_overworld;
/*  74 */     this.is_the_end = is_the_end;
/*     */     
/*  76 */     this.block_domain_radius = 524288 / (is_nether ? 8 : 1);
/*     */     
/*  78 */     this.hasNoSky = !is_overworld;
/*     */     
/*  80 */     this.isHellWorld = is_nether;
/*     */     
/*  82 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void registerWorld(World par1World) {
/*  90 */     this.worldObj = par1World;
/*  91 */     this.terrainType = par1World.getWorldInfo().getTerrainType();
/*  92 */     this.field_82913_c = par1World.getWorldInfo().getGeneratorOptions();
/*  93 */     registerWorldChunkManager();
/*  94 */     generateLightBrightnessTable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateLightBrightnessTable() {
/* 102 */     float var1 = 0.0F;
/*     */     
/* 104 */     for (int var2 = 0; var2 <= 15; var2++) {
/*     */       
/* 106 */       float var3 = 1.0F - var2 / 15.0F;
/* 107 */       this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
/*     */       
/* 109 */       if (var2 > 0 && var2 < 15) {
/*     */ 
/*     */         
/* 112 */         float extra_drop_off = 0.001F * (15 - var2);
/*     */         
/* 114 */         if (extra_drop_off > 0.0F) {
/* 115 */           this.lightBrightnessTable[var2] = this.lightBrightnessTable[var2] - extra_drop_off;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerWorldChunkManager() {
/* 127 */     if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT) {
/*     */       
/* 129 */       FlatGeneratorInfo var1 = FlatGeneratorInfo.createFlatGeneratorFromString(this.worldObj.getWorldInfo().getGeneratorOptions());
/* 130 */       this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.biomeList[var1.getBiome()], 0.5F, 0.5F);
/*     */     }
/*     */     else {
/*     */       
/* 134 */       this.worldChunkMgr = new WorldChunkManager(this.worldObj);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkProvider createChunkGenerator() {
/* 143 */     return (this.terrainType == WorldType.FLAT) ? new ChunkProviderFlat(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.field_82913_c) : new ChunkProviderGenerate(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCoordinateBeSpawn(int par1, int par2) {
/* 154 */     return (this.worldObj.getFirstUncoveredBlock(par1, par2) == Block.grass.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float calculateCelestialAngle(long par1, float par3) {
/* 162 */     int var4 = (int)(par1 % 24000L);
/* 163 */     float var5 = (var4 + par3) / 24000.0F - 0.25F;
/*     */     
/* 165 */     if (var5 < 0.0F)
/*     */     {
/* 167 */       var5++;
/*     */     }
/*     */     
/* 170 */     if (var5 > 1.0F)
/*     */     {
/* 172 */       var5--;
/*     */     }
/*     */     
/* 175 */     float var6 = var5;
/* 176 */     var5 = 1.0F - (float)((Math.cos(var5 * Math.PI) + 1.0D) / 2.0D);
/* 177 */     var5 = var6 + (var5 - var6) / 3.0F;
/* 178 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMoonPhase(long par1) {
/* 184 */     return (int)(par1 / 24000L + 1L) % 8;
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
/*     */   public final boolean isSurfaceWorld() {
/* 197 */     return this.is_overworld;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] calcSunriseSunsetColors(float par1, float par2) {
/* 205 */     float var3 = 0.4F;
/* 206 */     float var4 = MathHelper.cos(par1 * 3.1415927F * 2.0F) - 0.0F;
/* 207 */     float var5 = -0.0F;
/*     */     
/* 209 */     if (var4 >= var5 - var3 && var4 <= var5 + var3) {
/*     */       
/* 211 */       float var6 = (var4 - var5) / var3 * 0.5F + 0.5F;
/* 212 */       float var7 = 1.0F - (1.0F - MathHelper.sin(var6 * 3.1415927F)) * 0.99F;
/* 213 */       var7 *= var7;
/* 214 */       this.colorsSunriseSunset[0] = var6 * 0.3F + 0.7F;
/* 215 */       this.colorsSunriseSunset[1] = var6 * var6 * 0.7F + 0.2F;
/* 216 */       this.colorsSunriseSunset[2] = var6 * var6 * 0.0F + 0.2F;
/* 217 */       this.colorsSunriseSunset[3] = var7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 224 */       if (this.worldObj.getHourOfDay() < 12) {
/*     */         
/* 226 */         if (this.last_forecast_day != this.worldObj.getDayOfWorld()) {
/*     */           
/* 228 */           if (this.worldObj.willStormStartToday(4000) || this.worldObj.isStormingTodayAt(6000)) {
/* 229 */             this.weather_forecast = 2;
/* 230 */           } else if (this.worldObj.willPrecipitationStartToday(4000) || this.worldObj.isPrecipitatingTodayAt(6000)) {
/* 231 */             this.weather_forecast = 1;
/*     */           } else {
/* 233 */             this.weather_forecast = 0;
/*     */           } 
/* 235 */           this.last_forecast_day = this.worldObj.getDayOfWorld();
/*     */         } 
/*     */         
/* 238 */         if (this.weather_forecast == 0)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 244 */           float r1 = this.colorsSunriseSunset[0];
/* 245 */           float g1 = this.colorsSunriseSunset[1] = this.colorsSunriseSunset[1] * 1.2F;
/* 246 */           float b1 = this.colorsSunriseSunset[2];
/*     */           
/* 248 */           float r2 = (this.colorsSunriseSunset[0] + 1.0F) * 0.5F;
/* 249 */           float g2 = (this.colorsSunriseSunset[1] + 0.7F) * 0.5F;
/* 250 */           float b2 = (this.colorsSunriseSunset[2] + 0.4F) * 0.5F;
/*     */           
/* 252 */           float w2 = var6 * var6;
/* 253 */           float w1 = 1.0F - w2;
/*     */           
/* 255 */           this.colorsSunriseSunset[0] = r1 * w1 + r2 * w2;
/* 256 */           this.colorsSunriseSunset[1] = g1 * w1 + g2 * w2;
/* 257 */           this.colorsSunriseSunset[2] = b1 * w1 + b2 * w2;
/*     */         }
/* 259 */         else if (this.weather_forecast == 2)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 265 */           float r1 = this.colorsSunriseSunset[0];
/* 266 */           float g1 = this.colorsSunriseSunset[1];
/* 267 */           float b1 = this.colorsSunriseSunset[2];
/*     */           
/* 269 */           float r2 = (this.colorsSunriseSunset[0] + 1.0F) * 0.5F;
/* 270 */           float g2 = (this.colorsSunriseSunset[1] + 0.0F) * 0.5F;
/* 271 */           float b2 = (this.colorsSunriseSunset[2] + 0.0F) * 0.5F;
/*     */           
/* 273 */           float w2 = var6;
/* 274 */           float w1 = 1.0F - w2;
/*     */           
/* 276 */           this.colorsSunriseSunset[0] = r1 * w1 + r2 * w2;
/* 277 */           this.colorsSunriseSunset[1] = g1 * w1 + g2 * w2;
/* 278 */           this.colorsSunriseSunset[2] = b1 * w1 + b2 * w2;
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 287 */         int index = (int)(this.worldObj.getSeed() * 15771L + this.worldObj.getWorldCreationTime() + this.worldObj.getDayOfWorld());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 294 */         float bf = 1.0F + RNG.float_1_minus_float_1[++index & 0x7FFF] * 0.6F;
/*     */         
/* 296 */         if (bf < 0.6F) {
/* 297 */           bf = 1.0F + bf - 0.5F;
/*     */         }
/* 299 */         this.colorsSunriseSunset[2] = MathHelper.clamp_float(this.colorsSunriseSunset[2] * bf, 0.0F, 1.0F);
/*     */         
/* 301 */         if (bf > 0.6F && bf < 1.4F) {
/*     */           
/* 303 */           float gf = 1.0F + RNG.float_1_minus_float_1[++index & 0x7FFF] * 0.2F;
/*     */           
/* 305 */           if (gf > 1.1F) {
/* 306 */             gf = 1.0F;
/*     */           }
/* 308 */           this.colorsSunriseSunset[1] = MathHelper.clamp_float(this.colorsSunriseSunset[1] * gf, 0.0F, 1.0F);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 318 */       this.colorsSunriseSunset[3] = this.colorsSunriseSunset[3] * (1.0F - this.worldObj.getRainStrength(par2));
/*     */       
/* 320 */       return this.colorsSunriseSunset;
/*     */     } 
/*     */ 
/*     */     
/* 324 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getFogColor(float par1, float par2, EntityLivingBase viewer) {
/* 334 */     float var3 = MathHelper.cos(par1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*     */     
/* 336 */     if (var3 < 0.0F)
/*     */     {
/* 338 */       var3 = 0.0F;
/*     */     }
/*     */     
/* 341 */     if (var3 > 1.0F)
/*     */     {
/* 343 */       var3 = 1.0F;
/*     */     }
/*     */     
/* 346 */     float var4 = 0.7529412F;
/* 347 */     float var5 = 0.84705883F;
/* 348 */     float var6 = 1.0F;
/* 349 */     var4 *= var3 * 0.94F + 0.06F;
/* 350 */     var5 *= var3 * 0.94F + 0.06F;
/* 351 */     var6 *= var3 * 0.91F + 0.09F;
/* 352 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(var4, var5, var6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRespawnHere() {
/* 360 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WorldProvider getProviderForDimension(int par0) {
/* 367 */     if (par0 == -2)
/* 368 */       return new WorldProviderUnderworld(); 
/* 369 */     if (par0 == -1)
/* 370 */       return new WorldProviderHell(); 
/* 371 */     if (par0 == 0)
/* 372 */       return new WorldProviderSurface(); 
/* 373 */     if (par0 == 1) {
/* 374 */       return new WorldProviderEnd();
/*     */     }
/* 376 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCloudHeight() {
/* 384 */     return 128.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSkyColored() {
/* 389 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkCoordinates getEntrancePortalLocation() {
/* 397 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAverageGroundLevel() {
/* 402 */     return (this.terrainType == WorldType.FLAT) ? 4 : 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getWorldHasVoidParticles() {
/* 413 */     return !isTheEnd();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getWorldHasVoidFog() {
/* 419 */     return isSurfaceWorld();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getVoidFogYFactor() {
/* 429 */     return (this.terrainType == WorldType.FLAT) ? 1.0D : 0.03125D;
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
/*     */   public boolean doesXZShowFog(int x, int y, int z) {
/* 442 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawGuiVignette() {
/* 447 */     return !isUnderworld();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getDimensionName() {
/* 457 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isUnderworld() {
/* 462 */     return this.is_underworld;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isTheNether() {
/* 467 */     return this.is_nether;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isTheEnd() {
/* 477 */     return this.is_the_end;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getBlockDomainRadius() {
/* 483 */     return this.block_domain_radius;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */