/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockTallGrass
/*     */   extends BlockPlant
/*     */ {
/*  10 */   private static final String[] grassTypes = new String[] { "deadbush", "tallgrass", "fern" };
/*     */   
/*     */   private Icon[] iconArray;
/*     */   
/*     */   protected BlockTallGrass(int id) {
/*  15 */     super(id, Material.vine);
/*     */     
/*  17 */     float size = 0.4F;
/*  18 */     setBlockBoundsForAllThreads((0.5F - size), 0.0D, (0.5F - size), (0.5F + size), 0.800000011920929D, (0.5F + size));
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  23 */     if (par2 >= this.iconArray.length) {
/*  24 */       par2 = 0;
/*     */     }
/*  26 */     return this.iconArray[par2];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockColor() {
/*  31 */     double var1 = 0.5D;
/*  32 */     double var3 = 1.0D;
/*  33 */     return ColorizerGrass.getGrassColor(var1, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderColor(int par1) {
/*  38 */     return (par1 == 0) ? 16777215 : ColorizerFoliage.getFoliageColorBasic();
/*     */   }
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  43 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*  44 */     return (var5 == 0) ? 16777215 : par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  49 */     return "1=Grass, 2=Fern";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  54 */     return (metadata >= 1 && metadata < 3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/*  60 */     return (metadata == 1) ? 1 : 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  71 */     this.iconArray = new Icon[grassTypes.length];
/*     */     
/*  73 */     for (int var2 = 0; var2 < this.iconArray.length; var2++) {
/*  74 */       this.iconArray[var2] = par1IconRegister.registerIcon(grassTypes[var2]);
/*     */     }
/*     */   }
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/*  79 */     if (info.wasSelfDropped() || info.wasNotLegal()) {
/*  80 */       return super.dropBlockAsEntityItem(info);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  85 */     if (!info.wasHarvestedByPlayer() || getItemSubtype(info.getMetadata()) != 1) {
/*  86 */       return 0;
/*     */     }
/*  88 */     return dropBlockAsEntityItem(info, Item.seeds.itemID, 0, 1, 0.2F);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/*  93 */     return "tall";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dropsAsSelfWhenTrampled(Entity entity) {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World world, int x, int y, int z) {
/* 103 */     super.onBlockAdded(world, x, y, z);
/*     */     
/* 105 */     if (world.getBlockId(x, y - 1, z) == dirt.blockID) {
/* 106 */       world.setBlock(x, y - 1, z, grass.blockID);
/*     */     }
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
/*     */   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 119 */     return (other_block != null && other_block != this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean showDestructionParticlesWhenReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 124 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockTallGrass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */