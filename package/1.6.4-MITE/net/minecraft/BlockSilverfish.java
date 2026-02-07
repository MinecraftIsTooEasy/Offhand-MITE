/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class BlockSilverfish
/*     */   extends Block
/*     */ {
/*   9 */   public static final String[] silverfishStoneTypes = new String[] { "stone", "cobble", "brick", "netherrack", "stone" };
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockSilverfish(int par1) {
/*  14 */     super(par1, Material.clay, new BlockConstants());
/*  15 */     setHardness(0.0F);
/*  16 */     setTickRandomly(true);
/*  17 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  25 */     if (par2 == 3) {
/*  26 */       return Block.netherrack.getBlockTextureFromSide(par1);
/*     */     }
/*  28 */     if (par2 == 4) {
/*  29 */       return Block.stone.getBlockTextureFromSide(par1);
/*     */     }
/*  31 */     return (par2 == 1) ? Block.cobblestone.getBlockTextureFromSide(par1) : ((par2 == 2) ? Block.stoneBrick.getBlockTextureFromSide(par1) : Block.stone.getBlockTextureFromSide(par1));
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
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
/*     */   public static boolean getPosingIdByMetadata(int par0) {
/*  71 */     if (par0 == Block.netherrack.blockID) {
/*  72 */       return true;
/*     */     }
/*  74 */     return (par0 == Block.stone.blockID || par0 == Block.cobblestone.blockID || par0 == Block.stoneBrick.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMetadataForBlockType(int par0) {
/*  83 */     if (par0 == Block.netherrack.blockID) {
/*  84 */       return 3;
/*     */     }
/*  86 */     return (par0 == Block.cobblestone.blockID) ? 1 : ((par0 == Block.stoneBrick.blockID) ? 2 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack createStackedBlock(int par1) {
/*  95 */     Block var2 = Block.stone;
/*     */     
/*  97 */     if (par1 == 1)
/*     */     {
/*  99 */       var2 = Block.cobblestone;
/*     */     }
/*     */     
/* 102 */     if (par1 == 2)
/*     */     {
/* 104 */       var2 = Block.stoneBrick;
/*     */     }
/*     */     
/* 107 */     if (par1 == 3) {
/* 108 */       var2 = Block.netherrack;
/*     */     }
/* 110 */     if (par1 == 4) {
/* 111 */       var2 = Block.stone;
/*     */     }
/* 113 */     return new ItemStack(var2);
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
/*     */   public String getMetadataNotes() {
/* 126 */     return "0=Stone, 1=Cobblestone, 2=Stone Brick, 3=Netherrack, 4=Copperspine";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/* 133 */     return (metadata >= 0 && metadata < 5);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/* 139 */     return metadata;
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
/*     */   public static EntitySilverfish getSilverfishTypeForMetadata(World world, int metadata) {
/* 156 */     if (metadata == 3) {
/* 157 */       return new EntityNetherspawn(world);
/*     */     }
/* 159 */     if (metadata == 4) {
/* 160 */       return new EntityCopperspine(world);
/*     */     }
/* 162 */     if (world.isUnderworld()) {
/* 163 */       return new EntityHoarySilverfish(world);
/*     */     }
/* 165 */     return new EntitySilverfish(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void spawnSilverfishEntity(World world, int x, int y, int z, int metadata, EntityLivingBase target) {
/* 170 */     int updated_metadata = getUpdatedSilverfishBlockMetadata(world, x, y, z, metadata);
/*     */     
/* 172 */     if (updated_metadata != metadata) {
/*     */       
/* 174 */       convertNeighborMetadata(world, x, y, z, metadata, updated_metadata);
/* 175 */       metadata = updated_metadata;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 180 */     EntitySilverfish entity_silverfish = getSilverfishTypeForMetadata(world, metadata);
/* 181 */     entity_silverfish.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
/* 182 */     world.spawnEntityInWorld(entity_silverfish);
/* 183 */     entity_silverfish.spawnExplosionParticle();
/* 184 */     entity_silverfish.setTarget(target);
/*     */     
/* 186 */     entity_silverfish.makeLivingSound();
/*     */     
/* 188 */     entity_silverfish.refreshDespawnCounter(-9600);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getUpdatedSilverfishBlockMetadata(World world, int x, int y, int z, int metadata) {
/* 193 */     if (metadata == 0 && world.blockTypeIsNearTo(Block.oreCopper.blockID, x, y, z, 3, 3)) {
/* 194 */       return 4;
/*     */     }
/* 196 */     return metadata;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean updateSilverfishType(World world, int x, int y, int z, int metadata) {
/* 202 */     int updated_metadata = getUpdatedSilverfishBlockMetadata(world, x, y, z, metadata);
/*     */     
/* 204 */     if (updated_metadata != metadata) {
/*     */       
/* 206 */       convertVeinMetadata(world, x, y, z, metadata, updated_metadata, false);
/* 207 */       return true;
/*     */     } 
/*     */     
/* 210 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean updateSilverfishType(World world, int x, int y, int z) {
/* 216 */     return updateSilverfishType(world, x, y, z, world.getBlockMetadata(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 221 */     if (info.wasSilverfish() || info.getResponsiblePlayer() != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       spawnSilverfishEntity(info.world, info.x, info.y, info.z, info.getMetadata(), info.getResponsiblePlayer());
/*     */     }
/*     */     
/* 231 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void convertNeighborMetadata(World world, int x, int y, int z, int old_metadata, int new_metadata) {
/* 236 */     if (world.blockExists(x - 1, y, z)) {
/* 237 */       convertVeinMetadata(world, x - 1, y, z, old_metadata, new_metadata, true);
/*     */     }
/* 239 */     if (world.blockExists(x + 1, y, z)) {
/* 240 */       convertVeinMetadata(world, x + 1, y, z, old_metadata, new_metadata, true);
/*     */     }
/* 242 */     if (world.blockExists(x, y - 1, z)) {
/* 243 */       convertVeinMetadata(world, x, y - 1, z, old_metadata, new_metadata, true);
/*     */     }
/* 245 */     if (world.blockExists(x, y + 1, z)) {
/* 246 */       convertVeinMetadata(world, x, y + 1, z, old_metadata, new_metadata, true);
/*     */     }
/* 248 */     if (world.blockExists(x, y, z - 1)) {
/* 249 */       convertVeinMetadata(world, x, y, z - 1, old_metadata, new_metadata, true);
/*     */     }
/* 251 */     if (world.blockExists(x, y, z + 1)) {
/* 252 */       convertVeinMetadata(world, x, y, z + 1, old_metadata, new_metadata, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void convertVeinMetadata(World world, int x, int y, int z, int old_metadata, int new_metadata, boolean perform_check) {
/* 257 */     if (!perform_check || (world.getBlock(x, y, z) == silverfish && world.getBlockMetadata(x, y, z) == old_metadata)) {
/*     */       
/* 259 */       world.setBlockMetadataWithNotify(x, y, z, new_metadata, 2);
/* 260 */       convertNeighborMetadata(world, x, y, z, old_metadata, new_metadata);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 266 */     if (super.updateTick(world, x, y, z, random)) {
/* 267 */       return true;
/*     */     }
/* 269 */     if (world.isTheNether()) {
/* 270 */       return false;
/*     */     }
/* 272 */     if (!world.isPlayerNearby(x, y, z, 16.0D)) {
/* 273 */       return false;
/*     */     }
/* 275 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 277 */     if (metadata == 0) {
/*     */       
/* 279 */       Chunk chunk = world.getChunkFromBlockCoords(x, z);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 288 */       if (chunk.doAllNeighborsExist(1, false, false)) {
/* 289 */         return updateSilverfishType(world, x, y, z, metadata);
/*     */       }
/*     */     } 
/* 292 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSilverfish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */