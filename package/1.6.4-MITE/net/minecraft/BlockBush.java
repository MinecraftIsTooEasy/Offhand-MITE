/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockBush
/*     */   extends BlockGrowingPlant
/*     */ {
/*     */   public static final int BLUEBERRY = 0;
/*  12 */   public static final String[] types = new String[] { "blueberry" };
/*     */   
/*     */   private Icon[] icons;
/*     */   
/*     */   protected BlockBush(int id) {
/*  17 */     super(id);
/*     */     
/*  19 */     float width = 0.3F;
/*  20 */     float height = 0.6F;
/*  21 */     setBlockBoundsForAllThreads((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), height, (0.5F + width));
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
/*     */   public void registerIcons(IconRegister icon_register) {
/*  35 */     this.icons = new Icon[types.length * 2];
/*     */     
/*  37 */     for (int i = 0; i < types.length; i++) {
/*     */       
/*  39 */       if (types[i] != null) {
/*     */         
/*  41 */         this.icons[i * 2] = icon_register.registerIcon(getTextureName() + "/" + types[i]);
/*  42 */         this.icons[i * 2 + 1] = icon_register.registerIcon(getTextureName() + "/" + types[i] + "_picked");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int side, int metadata) {
/*  51 */     return this.icons[getBlockSubtype(metadata) * 2 + (hasBerries(metadata) ? 0 : 1)];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  56 */     return "Bits 1 and 2 used for subtype, bits 4 and 8 used to track berry growth";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  61 */     return ((metadata & 0x3) < types.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/*  66 */     return metadata & 0x3;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemSubtype(int metadata) {
/*  71 */     return setBerryGrowth(getBlockSubtype(metadata), getMaxBerryGrowth());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMaxBerryGrowth() {
/*  76 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasBerries(int metadata) {
/*  81 */     return (getBerryGrowth(metadata) == getMaxBerryGrowth());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int setBerryGrowth(int metadata, int growth) {
/*  87 */     return metadata & 0x3 | MathHelper.clamp_int(growth, 0, getMaxBerryGrowth()) << 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getBerryGrowth(int metadata) {
/*  92 */     return metadata >> 2;
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
/*     */   public static int incrementBerryGrowth(int metadata) {
/* 107 */     return setBerryGrowth(metadata, getBerryGrowth(metadata) + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int setToMaximumBerryGrowth(int metadata) {
/* 112 */     return setBerryGrowth(metadata, getMaxBerryGrowth());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMetadataForBushWithBerries(int subtype) {
/* 117 */     return setToMaximumBerryGrowth(subtype);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinAllowedLightValue() {
/* 122 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxAllowedLightValue() {
/* 127 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean dropsAsSelfWhenTrampled(Entity entity) {
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBiomeSuitable(BiomeGenBase biome, int metadata) {
/* 142 */     return (biome == BiomeGenBase.forest || biome == BiomeGenBase.forestHills);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePlacedAt(World world, int x, int y, int z, int metadata) {
/* 147 */     return (isBiomeSuitable(world.getBiomeGenForCoords(x, z), metadata) && world.canBlockSeeTheSky(x, y, z) && super.canBePlacedAt(world, x, y, z, metadata));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPatchSize(BiomeGenBase biome) {
/* 153 */     return 64;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 158 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 160 */     if (!hasBerries(metadata)) {
/* 161 */       return false;
/*     */     }
/* 163 */     if (player.onServer()) {
/*     */       
/* 165 */       dropBlockAsEntityItem((new BlockBreakInfo(world, x, y, z)).setPickedBy(player), Item.blueberries);
/* 166 */       world.setBlock(x, y, z, this.blockID, setBerryGrowth(metadata, 0), 2);
/*     */     } 
/*     */     
/* 169 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fertilize(World world, int x, int y, int z, ItemStack item_stack) {
/* 174 */     Item item = item_stack.getItem();
/*     */     
/* 176 */     if (item == Item.dyePowder) {
/*     */       
/* 178 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 180 */       if (hasBerries(metadata)) {
/* 181 */         return false;
/*     */       }
/* 183 */       if (!world.isRemote) {
/*     */         
/* 185 */         int growth_added = MathHelper.getRandomIntegerInRange(world.rand, 1, 2);
/*     */         
/* 187 */         while (growth_added-- > 0) {
/* 188 */           metadata = incrementBerryGrowth(metadata);
/*     */         }
/* 190 */         world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/*     */       } 
/*     */       
/* 193 */       return true;
/*     */     } 
/*     */     
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsItself(BlockBreakInfo info) {
/* 201 */     return dropBlockAsEntityItem(info) + super.dropBlockAsItself(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 206 */     if (hasBerries(info.getMetadata()) && (info.wasHarvestedByPlayer() || info.wasSelfDropped() || info.wasNotLegal())) {
/* 207 */       return dropBlockAsEntityItem(info, Item.blueberries);
/*     */     }
/* 209 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 215 */     return (entity instanceof EntityPlayer && entity.getAsPlayer().inCreativeMode()) ? item_stack.getItemSubtype() : setBerryGrowth(item_stack.getItemSubtype(), 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getGrowthRate(World world, int x, int y, int z) {
/* 226 */     float growth_rate = 0.1F;
/*     */     
/* 228 */     BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
/*     */     
/* 230 */     growth_rate *= getTemperatureGrowthRateModifier(biome.temperature);
/* 231 */     growth_rate *= getHumidityGrowthRateModifier(biome.isHighHumidity());
/* 232 */     growth_rate *= getGlobalGrowthRateModifierFromMITE();
/*     */     
/* 234 */     return growth_rate;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random rand) {
/* 239 */     if (super.updateTick(world, x, y, z, rand)) {
/* 240 */       return true;
/*     */     }
/* 242 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 244 */     if (hasBerries(metadata)) {
/* 245 */       return false;
/*     */     }
/* 247 */     int berry_growth = getBerryGrowth(metadata);
/*     */     
/* 249 */     float growth_rate = getGrowthRate(world, x, y, z);
/*     */     
/* 251 */     if (growth_rate == 0.0F) {
/* 252 */       return false;
/*     */     }
/* 254 */     int blv = world.getBlockLightValue(x, y + 1, z);
/*     */     
/* 256 */     if (isLightLevelSuitableForGrowth(blv))
/*     */     {
/* 258 */       if (rand.nextFloat() < growth_rate) {
/*     */         
/* 260 */         world.setBlockMetadataWithNotify(x, y, z, incrementBerryGrowth(metadata), 2);
/* 261 */         return true;
/*     */       } 
/*     */     }
/*     */     
/* 265 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getLowestOptimalTemperature() {
/* 270 */     return BiomeGenBase.forestHills.temperature;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHighestOptimalTemperature() {
/* 275 */     return BiomeGenBase.forest.temperature;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHumidityGrowthRateModifier(boolean high_humidity) {
/* 280 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBush.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */