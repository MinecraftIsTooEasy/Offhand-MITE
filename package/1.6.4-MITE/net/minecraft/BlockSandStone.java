/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSandStone
/*     */   extends Block
/*     */ {
/*  12 */   public static final String[] SAND_STONE_TYPES = new String[] { "default", "chiseled", "smooth", "sacred" };
/*  13 */   private static final String[] field_94405_b = new String[] { "normal", "carved", "smooth", "smooth" };
/*     */   
/*     */   private Icon[] field_94406_c;
/*     */   
/*     */   private Icon field_94403_cO;
/*     */   
/*     */   private Icon field_94404_cP;
/*     */   public static boolean sacred_pyramid_completed;
/*     */   
/*     */   public BlockSandStone(int par1) {
/*  23 */     super(par1, Material.stone, new BlockConstants());
/*     */     
/*  25 */     modifyMinHarvestLevel(-1);
/*  26 */     setCreativeTab(CreativeTabs.tabBlock);
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
/*     */   public Icon getIcon(int par1, int par2) {
/*  57 */     int subtype = getBlockSubtype(par2);
/*     */     
/*  59 */     if (par1 == 0)
/*  60 */       return (subtype == 0) ? this.field_94404_cP : this.field_94403_cO; 
/*  61 */     if (par1 == 1) {
/*  62 */       return this.field_94403_cO;
/*     */     }
/*  64 */     return this.field_94406_c[subtype];
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
/*     */   public String getMetadataNotes() {
/*  79 */     return "0=Regular, 1=Chiseled, 2=Smooth, 3=Sacred";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  84 */     return (metadata >= 0 && metadata < 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/*  89 */     return metadata & 0x3;
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 109 */     this.field_94406_c = new Icon[field_94405_b.length];
/*     */     
/* 111 */     for (int var2 = 0; var2 < this.field_94406_c.length; var2++)
/*     */     {
/* 113 */       this.field_94406_c[var2] = par1IconRegister.registerIcon(getTextureName() + "_" + field_94405_b[var2]);
/*     */     }
/*     */     
/* 116 */     this.field_94403_cO = par1IconRegister.registerIcon(getTextureName() + "_top");
/* 117 */     this.field_94404_cP = par1IconRegister.registerIcon(getTextureName() + "_bottom");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSacredSandstone(Block block, int metadata) {
/* 123 */     return (block == sandStone && block.getBlockSubtype(metadata) == 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSacredSandstone(int metadata) {
/* 128 */     return isSacredSandstone(this, metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 134 */     if (info.wasExploded() || isSacredSandstone(info.getMetadata())) {
/* 135 */       return dropBlockAsEntityItem(info, sand);
/*     */     }
/* 137 */     return super.dropBlockAsEntityItem(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
/* 142 */     if (!world.isRemote) {
/*     */       
/* 144 */       Minecraft.setErrorMessage("Why this called on server?");
/*     */       
/*     */       return;
/*     */     } 
/* 148 */     if (world.getBlock(x, y, z) != sandStone || world.getBlockMetadata(x, y, z) != 3) {
/*     */       return;
/*     */     }
/* 151 */     if (RNG.chance_in_2[++RNG.random_number_index & 0x7FFF]) {
/*     */       return;
/*     */     }
/* 154 */     Random var5 = world.rand;
/* 155 */     double var6 = 0.0625D;
/*     */     
/* 157 */     for (int var8 = 0; var8 < 6; var8++) {
/*     */       
/* 159 */       double var9 = (x + var5.nextFloat());
/* 160 */       double var11 = (y + var5.nextFloat());
/* 161 */       double var13 = (z + var5.nextFloat());
/*     */       
/* 163 */       if (var8 == 0 && !world.isBlockStandardFormOpaqueCube(x, y + 1, z))
/*     */       {
/* 165 */         var11 = (y + 1) + var6;
/*     */       }
/*     */       
/* 168 */       if (var8 == 1 && !world.isBlockStandardFormOpaqueCube(x, y - 1, z))
/*     */       {
/* 170 */         var11 = (y + 0) - var6;
/*     */       }
/*     */       
/* 173 */       if (var8 == 2 && !world.isBlockStandardFormOpaqueCube(x, y, z + 1))
/*     */       {
/* 175 */         var13 = (z + 1) + var6;
/*     */       }
/*     */       
/* 178 */       if (var8 == 3 && !world.isBlockStandardFormOpaqueCube(x, y, z - 1))
/*     */       {
/* 180 */         var13 = (z + 0) - var6;
/*     */       }
/*     */       
/* 183 */       if (var8 == 4 && !world.isBlockStandardFormOpaqueCube(x + 1, y, z))
/*     */       {
/* 185 */         var9 = (x + 1) + var6;
/*     */       }
/*     */       
/* 188 */       if (var8 == 5 && !world.isBlockStandardFormOpaqueCube(x - 1, y, z))
/*     */       {
/* 190 */         var9 = (x + 0) - var6;
/*     */       }
/*     */       
/* 193 */       if (var9 < x || var9 > (x + 1) || var11 < 0.0D || var11 > (y + 1) || var13 < z || var13 > (z + 1))
/*     */       {
/* 195 */         world.spawnParticle(EnumParticle.sacred, var9, var11, var13, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSacredSandstoneBlock(World world, int x, int y, int z) {
/* 204 */     return isSacredSandstone(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkForCompletionOfPyramid(World world, int x, int y, int z) {
/* 210 */     if (sacred_pyramid_completed) {
/*     */       return;
/*     */     }
/* 213 */     boolean is_topmost = true;
/*     */     
/* 215 */     if (isSacredSandstoneBlock(world, x - 1, y + 1, z - 1)) {
/*     */       
/* 217 */       is_topmost = false;
/* 218 */       checkForCompletionOfPyramid(world, x - 1, y + 1, z - 1);
/*     */     } 
/*     */     
/* 221 */     if (isSacredSandstoneBlock(world, x - 1, y + 1, z)) {
/*     */       
/* 223 */       is_topmost = false;
/* 224 */       checkForCompletionOfPyramid(world, x - 1, y + 1, z);
/*     */     } 
/*     */     
/* 227 */     if (isSacredSandstoneBlock(world, x - 1, y + 1, z + 1)) {
/*     */       
/* 229 */       is_topmost = false;
/* 230 */       checkForCompletionOfPyramid(world, x - 1, y + 1, z + 1);
/*     */     } 
/*     */ 
/*     */     
/* 234 */     if (isSacredSandstoneBlock(world, x, y + 1, z - 1)) {
/*     */       
/* 236 */       is_topmost = false;
/* 237 */       checkForCompletionOfPyramid(world, x, y + 1, z - 1);
/*     */     } 
/*     */     
/* 240 */     if (isSacredSandstoneBlock(world, x, y + 1, z + 1)) {
/*     */       
/* 242 */       is_topmost = false;
/* 243 */       checkForCompletionOfPyramid(world, x, y + 1, z + 1);
/*     */     } 
/*     */ 
/*     */     
/* 247 */     if (isSacredSandstoneBlock(world, x + 1, y + 1, z - 1)) {
/*     */       
/* 249 */       is_topmost = false;
/* 250 */       checkForCompletionOfPyramid(world, x + 1, y + 1, z - 1);
/*     */     } 
/*     */     
/* 253 */     if (isSacredSandstoneBlock(world, x + 1, y + 1, z)) {
/*     */       
/* 255 */       is_topmost = false;
/* 256 */       checkForCompletionOfPyramid(world, x + 1, y + 1, z);
/*     */     } 
/*     */     
/* 259 */     if (isSacredSandstoneBlock(world, x + 1, y + 1, z + 1)) {
/*     */       
/* 261 */       is_topmost = false;
/* 262 */       checkForCompletionOfPyramid(world, x + 1, y + 1, z + 1);
/*     */     } 
/*     */     
/* 265 */     if (is_topmost) {
/*     */ 
/*     */ 
/*     */       
/* 269 */       for (int dy = -1; dy > -DedicatedServer.getRequiredPyramidHeight(); dy--) {
/*     */         
/* 271 */         for (int dx = dy; dx <= -dy; dx++) {
/*     */           
/* 273 */           for (int dz = dy; dz <= -dy; dz++) {
/*     */             
/* 275 */             if (dx == dy || dx == -dy || dz == dy || dz == -dy)
/*     */             {
/* 277 */               if (!isSacredSandstoneBlock(world, x + dx, y + dy, z + dz) || !world.getBiomeGenForCoords(x + dx, z + dz).isDesertBiome()) {
/*     */                 return;
/*     */               }
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 284 */       sacred_pyramid_completed = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 290 */     if (world.isRemote || metadata != 3 || DedicatedServer.tournament_type != EnumTournamentType.wonder) {
/* 291 */       return true;
/*     */     }
/* 293 */     if (sacred_pyramid_completed) {
/* 294 */       return true;
/*     */     }
/*     */     
/* 297 */     world.worldInfo.incrementSacredSandstonesPlaced();
/*     */     
/* 299 */     if (placer instanceof ServerPlayer) {
/*     */       
/* 301 */       ServerPlayer player = (ServerPlayer)placer;
/*     */       
/* 303 */       player.sacred_stones_placed++;
/*     */ 
/*     */       
/*     */       try {
/* 307 */         File dir = new File("sacred_stones_placed");
/*     */         
/* 309 */         if (!dir.exists()) {
/* 310 */           dir.mkdir();
/*     */         }
/* 312 */         FileWriter fw = new FileWriter(dir.getPath() + "/" + player.username);
/*     */         
/* 314 */         fw.write("" + player.sacred_stones_placed);
/* 315 */         fw.close();
/*     */       }
/* 317 */       catch (Exception e) {}
/*     */     } 
/*     */     
/* 320 */     checkForCompletionOfPyramid(world, x, y, z);
/*     */     
/* 322 */     if (sacred_pyramid_completed) {
/* 323 */       DedicatedServer.checkForTournamentCompletion();
/*     */     }
/* 325 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSilkHarvest(int metadata) {
/* 331 */     return (!isSacredSandstone(metadata) && super.canSilkHarvest(metadata));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSandStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */