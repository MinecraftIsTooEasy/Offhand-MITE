/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockMobSpawner
/*     */   extends BlockContainer
/*     */ {
/*     */   protected BlockMobSpawner(int par1) {
/*  10 */     super(par1, Material.iron, (new BlockConstants()).setNeverHidesAdjacentFaces());
/*  11 */     setMinHarvestLevel(2);
/*  12 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/*  20 */     return new TileEntityMobSpawner();
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
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/*  51 */     dropXpOnBlockBreak(info.world, info.x, info.y, info.z, 15 + info.world.rand.nextInt(15) + info.world.rand.nextInt(15));
/*     */     
/*  53 */     return 0;
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
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/*  70 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void incrementSpawnsKilled(World world, int x, int y, int z) {
/*  76 */     if (world.getBlock(x, y, z) instanceof BlockMobSpawner) {
/*     */       
/*  78 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/*  80 */       if (metadata < 15) {
/*  81 */         world.setBlockMetadataWithNotify(x, y, z, ++metadata, 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean canBeCarried() {
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 103 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockMobSpawner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */