/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockGrowingPlant
/*     */   extends BlockPlant
/*     */ {
/*     */   public BlockGrowingPlant(int block_id) {
/*   9 */     super(block_id);
/*     */     
/*  11 */     setMaxStackSize(8);
/*     */   }
/*     */   
/*     */   public abstract float getLowestOptimalTemperature();
/*     */   
/*     */   public abstract float getHighestOptimalTemperature();
/*     */   
/*     */   public float getTemperatureTolerance() {
/*  19 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getGlobalGrowthRateModifierFromMITE() {
/*  24 */     return 0.25F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTemperatureGrowthRateModifier(float temperature) {
/*     */     float delta_temp;
/*  31 */     if (temperature < getLowestOptimalTemperature()) {
/*  32 */       delta_temp = getLowestOptimalTemperature() - temperature;
/*  33 */     } else if (temperature > getHighestOptimalTemperature()) {
/*  34 */       delta_temp = temperature - getHighestOptimalTemperature();
/*     */     } else {
/*  36 */       return 1.0F;
/*     */     } 
/*  38 */     return Math.max(1.0F - delta_temp / getTemperatureTolerance(), 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract float getHumidityGrowthRateModifier(boolean paramBoolean);
/*     */   
/*     */   public float getProximityGrowthRateModifier(World world, int x, int y, int z) {
/*  45 */     boolean north = (world.getBlock(x, y, z - 1) == this);
/*  46 */     boolean east = (world.getBlock(x + 1, y, z) == this);
/*  47 */     boolean south = (world.getBlock(x, y, z + 1) == this);
/*  48 */     boolean west = (world.getBlock(x - 1, y, z) == this);
/*     */     
/*  50 */     int num_neighbors = 0;
/*     */     
/*  52 */     if (north) {
/*  53 */       num_neighbors++;
/*     */     }
/*  55 */     if (east) {
/*  56 */       num_neighbors++;
/*     */     }
/*  58 */     if (south) {
/*  59 */       num_neighbors++;
/*     */     }
/*  61 */     if (west) {
/*  62 */       num_neighbors++;
/*     */     }
/*  64 */     if (num_neighbors > 1) {
/*  65 */       return 1.0F;
/*     */     }
/*  67 */     float isolation_penalty_factor = 0.5F;
/*     */     
/*  69 */     if (num_neighbors == 0) {
/*  70 */       return 0.5F;
/*     */     }
/*  72 */     if (north)
/*     */     {
/*  74 */       if (world.getBlock(x, y, z - 2) == this || world.getBlock(x + 1, y, z - 1) == this || world.getBlock(x - 1, y, z - 1) == this) {
/*  75 */         return 1.0F;
/*     */       }
/*     */     }
/*  78 */     if (east)
/*     */     {
/*  80 */       if (world.getBlock(x + 1, y, z - 1) == this || world.getBlock(x + 2, y, z) == this || world.getBlock(x + 1, y, z + 1) == this) {
/*  81 */         return 1.0F;
/*     */       }
/*     */     }
/*  84 */     if (south)
/*     */     {
/*  86 */       if (world.getBlock(x + 1, y, z + 1) == this || world.getBlock(x, y, z + 2) == this || world.getBlock(x - 1, y, z + 1) == this) {
/*  87 */         return 1.0F;
/*     */       }
/*     */     }
/*  90 */     if (west)
/*     */     {
/*  92 */       if (world.getBlock(x - 1, y, z - 1) == this || world.getBlock(x - 1, y, z + 1) == this || world.getBlock(x - 2, y, z) == this) {
/*  93 */         return 1.0F;
/*     */       }
/*     */     }
/*  96 */     return 0.75F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinAllowedLightValueForGrowth() {
/* 103 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxAllowedLightValueForGrowth() {
/* 109 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLightLevelSuitableForGrowth(int block_light_value) {
/* 115 */     return (block_light_value >= getMinAllowedLightValueForGrowth() && block_light_value <= getMaxAllowedLightValueForGrowth());
/*     */   }
/*     */   
/*     */   public abstract float getGrowthRate(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockGrowingPlant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */