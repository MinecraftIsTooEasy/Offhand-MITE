/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockPlant
/*     */   extends Block
/*     */ {
/*     */   protected BlockPlant(int id, Material material) {
/*  11 */     this(id, material, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockPlant(int id, Material material, BlockConstants constants) {
/*  16 */     super(id, material, constants);
/*     */     
/*  18 */     setTickRandomly(true);
/*  19 */     float var3 = 0.2F;
/*  20 */     setBlockBoundsForAllThreads((0.5F - var3), 0.0D, (0.5F - var3), (0.5F + var3), (var3 * 3.0F), (0.5F + var3));
/*     */     
/*  22 */     setMaxStackSize(32);
/*  23 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockPlant(int id) {
/*  28 */     this(id, Material.plants);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoresLightCheckIfUnderOpenSky() {
/*  34 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinAllowedLightValue() {
/*  40 */     return 8;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxAllowedLightValue() {
/*  46 */     return 15;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isLightLevelSuitable(World world, int x, int y, int z) {
/*  51 */     int min_allowed_light_value = getMinAllowedLightValue();
/*  52 */     int max_allowed_light_value = getMaxAllowedLightValue();
/*     */     
/*  54 */     if (min_allowed_light_value < 1 && max_allowed_light_value > 14) {
/*  55 */       return true;
/*     */     }
/*  57 */     int block_light_value = world.getFullBlockLightValue(x, y, z);
/*     */     
/*  59 */     return (block_light_value >= min_allowed_light_value && block_light_value <= max_allowed_light_value);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/*  64 */     return (super.isLegalAt(world, x, y, z, metadata) && ((ignoresLightCheckIfUnderOpenSky() && world.canBlockSeeTheSky(x, y, z)) || isLightLevelSuitable(world, x, y, z)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/*  69 */     return (block_below == grass || block_below == dirt || block_below == tilledField);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNotLegal(World world, int x, int y, int z, int metadata) {
/*  74 */     if (world.getClosestPlayer((x + 0.5F), (y + 0.5F), (z + 0.5F), 16.0D, false) == null) {
/*     */       
/*  76 */       world.setBlockToAir(x, y, z);
/*  77 */       return true;
/*     */     } 
/*     */     
/*  80 */     return super.onNotLegal(world, x, y, z, metadata);
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
/*     */   public int getRenderType() {
/* 100 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dropsAsSelfWhenTrampled(Entity entity) {
/* 105 */     return !(entity instanceof EntityCow);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTrampledBy(World world, int x, int y, int z, Entity entity) {
/* 110 */     world.destroyBlock((new BlockBreakInfo(world, x, y, z)).setTrampledBy(entity), dropsAsSelfWhenTrampled(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPatchSize(BiomeGenBase biome) {
/* 115 */     return 64;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 125 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPlant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */