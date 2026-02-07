/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public final class BlockLeaves
/*     */   extends BlockLeavesBase
/*     */ {
/*   9 */   public static final String[] LEAF_TYPES = new String[] { "oak", "spruce", "birch", "jungle" };
/*  10 */   public static final String[][] field_94396_b = new String[][] { { "leaves_oak", "leaves_spruce", "leaves_birch", "leaves_jungle" }, { "leaves_oak_opaque", "leaves_spruce_opaque", "leaves_birch_opaque", "leaves_jungle_opaque" } };
/*     */   
/*     */   private int iconType;
/*     */   
/*  14 */   private Icon[][] iconArray = new Icon[2][];
/*     */   
/*     */   int[] adjacentTreeBlocks;
/*     */   
/*     */   public static final int OAK = 0;
/*     */   public static final int SPRUCE = 1;
/*     */   public static final int BIRCH = 2;
/*     */   public static final int JUNGLE = 3;
/*     */   
/*     */   protected BlockLeaves(int par1) {
/*  24 */     super(par1, Material.tree_leaves, false);
/*  25 */     setTickRandomly(true);
/*  26 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */     
/*  28 */     setCushioning(0.8F);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockColor() {
/*  33 */     double var1 = 0.5D;
/*  34 */     double var3 = 1.0D;
/*  35 */     return ColorizerFoliage.getFoliageColor(var1, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderColor(int par1) {
/*  43 */     return ((par1 & 0x3) == 1) ? ColorizerFoliage.getFoliageColorPine() : (((par1 & 0x3) == 2) ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  52 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */     
/*  54 */     if ((var5 & 0x3) == 1)
/*     */     {
/*  56 */       return ColorizerFoliage.getFoliageColorPine();
/*     */     }
/*  58 */     if ((var5 & 0x3) == 2)
/*     */     {
/*  60 */       return ColorizerFoliage.getFoliageColorBirch();
/*     */     }
/*     */ 
/*     */     
/*  64 */     int var6 = 0;
/*  65 */     int var7 = 0;
/*  66 */     int var8 = 0;
/*     */     
/*  68 */     for (int var9 = -1; var9 <= 1; var9++) {
/*     */       
/*  70 */       for (int var10 = -1; var10 <= 1; var10++) {
/*     */         
/*  72 */         int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor();
/*  73 */         var6 += (var11 & 0xFF0000) >> 16;
/*  74 */         var7 += (var11 & 0xFF00) >> 8;
/*  75 */         var8 += var11 & 0xFF;
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     return (var6 / 9 & 0xFF) << 16 | (var7 / 9 & 0xFF) << 8 | var8 / 9 & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/*  90 */     if (par1World.decorating) {
/*     */       return;
/*     */     }
/*  93 */     byte var7 = 1;
/*  94 */     int var8 = var7 + 1;
/*     */     
/*  96 */     if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
/*     */     {
/*  98 */       for (int var9 = -var7; var9 <= var7; var9++) {
/*     */         
/* 100 */         for (int var10 = -var7; var10 <= var7; var10++) {
/*     */           
/* 102 */           for (int var11 = -var7; var11 <= var7; var11++) {
/*     */             
/* 104 */             int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);
/*     */             
/* 106 */             if (var12 == Block.leaves.blockID) {
/*     */               
/* 108 */               int var13 = par1World.getBlockMetadata(par2 + var9, par3 + var10, par4 + var11);
/* 109 */               par1World.setBlockMetadataWithNotify(par2 + var9, par3 + var10, par4 + var11, var13 | 0x8, 4);
/*     */             } 
/*     */           } 
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
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 125 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */     
/* 127 */     if ((var6 & 0x8) != 0 && (var6 & 0x4) == 0) {
/*     */       
/* 129 */       byte var7 = 4;
/* 130 */       int var8 = var7 + 1;
/* 131 */       byte var9 = 32;
/* 132 */       int var10 = var9 * var9;
/* 133 */       int var11 = var9 / 2;
/*     */       
/* 135 */       if (this.adjacentTreeBlocks == null)
/*     */       {
/* 137 */         this.adjacentTreeBlocks = new int[var9 * var9 * var9];
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 142 */       if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
/*     */         int i;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 148 */         for (i = -var7; i <= var7; i++) {
/*     */           
/* 150 */           int var12_plus_var11_times_var10_plus_var11 = (i + var11) * var10 + var11;
/*     */           
/* 152 */           for (int var13 = -var7; var13 <= var7; var13++) {
/*     */             
/* 154 */             int var13_plus_var11_times_var9 = (var13 + var11) * var9;
/*     */             
/* 156 */             for (int var14 = -var7; var14 <= var7; var14++) {
/*     */               
/* 158 */               int var15 = par1World.getBlockId(par2 + i, par3 + var13, par4 + var14);
/*     */               
/* 160 */               if (var15 == Block.wood.blockID) {
/*     */ 
/*     */                 
/* 163 */                 this.adjacentTreeBlocks[var12_plus_var11_times_var10_plus_var11 + var13_plus_var11_times_var9 + var14] = 0;
/*     */               }
/* 165 */               else if (var15 == Block.leaves.blockID) {
/*     */ 
/*     */                 
/* 168 */                 this.adjacentTreeBlocks[var12_plus_var11_times_var10_plus_var11 + var13_plus_var11_times_var9 + var14] = -2;
/*     */               
/*     */               }
/*     */               else {
/*     */                 
/* 173 */                 this.adjacentTreeBlocks[var12_plus_var11_times_var10_plus_var11 + var13_plus_var11_times_var9 + var14] = -1;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 179 */         for (i = 1; i <= 4; i++) {
/*     */           
/* 181 */           for (int var13 = -var7; var13 <= var7; var13++) {
/*     */             
/* 183 */             int var13_plus_var11 = var13 + var11;
/* 184 */             int var13_plus_var11_minus_1_times_var10 = (var13_plus_var11 - 1) * var10;
/* 185 */             int var13_plus_var11_plus_1_times_var10 = (var13_plus_var11 + 1) * var10;
/* 186 */             int var13_plus_var11_times_var10 = var13_plus_var11 * var10;
/*     */             
/* 188 */             for (int var14 = -var7; var14 <= var7; var14++) {
/*     */               
/* 190 */               int var14_plus_var11 = var14 + var11;
/* 191 */               int var14_plus_var11_times_var9 = var14_plus_var11 * var9;
/* 192 */               for (int var15 = -var7; var15 <= var7; var15++) {
/*     */                 
/* 194 */                 int index1 = var13_plus_var11_times_var10 + var14_plus_var11_times_var9 + var15 + var11;
/* 195 */                 if (this.adjacentTreeBlocks[index1] == i - 1) {
/*     */                   
/* 197 */                   int index2 = var13_plus_var11_minus_1_times_var10 + var14_plus_var11_times_var9 + var15 + var11;
/* 198 */                   if (this.adjacentTreeBlocks[index2] == -2)
/*     */                   {
/* 200 */                     this.adjacentTreeBlocks[index2] = i;
/*     */                   }
/*     */                   
/* 203 */                   index2 = var13_plus_var11_plus_1_times_var10 + var14_plus_var11_times_var9 + var15 + var11;
/* 204 */                   if (this.adjacentTreeBlocks[index2] == -2)
/*     */                   {
/* 206 */                     this.adjacentTreeBlocks[index2] = i;
/*     */                   }
/*     */                   
/* 209 */                   index2 = var13_plus_var11_times_var10 + (var14_plus_var11 - 1) * var9 + var15 + var11;
/* 210 */                   if (this.adjacentTreeBlocks[index2] == -2)
/*     */                   {
/* 212 */                     this.adjacentTreeBlocks[index2] = i;
/*     */                   }
/*     */                   
/* 215 */                   index2 = var13_plus_var11_times_var10 + (var14_plus_var11 + 1) * var9 + var15 + var11;
/* 216 */                   if (this.adjacentTreeBlocks[index2] == -2)
/*     */                   {
/* 218 */                     this.adjacentTreeBlocks[index2] = i;
/*     */                   }
/*     */                   
/* 221 */                   index2 = var13_plus_var11_times_var10 + var14_plus_var11_times_var9 + var15 + var11 - 1;
/* 222 */                   if (this.adjacentTreeBlocks[index2] == -2)
/*     */                   {
/* 224 */                     this.adjacentTreeBlocks[index2] = i;
/*     */                   }
/*     */                   
/* 227 */                   if (this.adjacentTreeBlocks[index1 + 1] == -2)
/*     */                   {
/* 229 */                     this.adjacentTreeBlocks[index1 + 1] = i;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
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
/* 271 */       int var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11];
/*     */       
/* 273 */       if (var12 >= 0) {
/*     */         
/* 275 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 & 0xFFFFFFF7, 4);
/* 276 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 280 */       removeLeaves(par1World, par2, par3, par4);
/* 281 */       return true;
/*     */     } 
/*     */     
/* 284 */     if (RNG.chance_in_100[++RNG.random_number_index + (int)par1World.total_time & 0x7FFF] && !wasPlaced(var6))
/*     */     {
/* 286 */       if (!(par1World.getAsWorldServer()).fast_forwarding || RNG.chance_in_32[++RNG.random_number_index & 0x7FFF])
/*     */       {
/* 288 */         if (par5Random.nextInt(500) == 0) {
/*     */           
/* 290 */           if (par1World.getBiomeGenForCoords(par2, par4).isSwampBiome() && par5Random.nextInt(2) == 0) {
/* 291 */             return false;
/*     */           }
/* 293 */           Item item = Item.stick;
/*     */           
/* 295 */           int subtype = getBlockSubtype(var6);
/*     */           
/* 297 */           if (subtype == 0) {
/*     */             
/* 299 */             if (par5Random.nextInt(3) > 0) {
/* 300 */               item = par1World.getBiomeGenForCoords(par2, par4).isJungleBiome() ? Item.orange : Item.appleRed;
/*     */             }
/* 302 */           } else if (subtype == 3) {
/*     */             
/* 304 */             if (par5Random.nextInt(3) > 0) {
/* 305 */               item = Item.banana;
/*     */             }
/*     */           } 
/* 308 */           dropBlockAsEntityItem((new BlockBreakInfo(par1World, par2, par3, par4)).setWindfall(), item);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 315 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 323 */     if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !par1World.isBlockTopFlatAndSolid(par2, par3 - 1, par4) && par5Random.nextInt(15) == 1) {
/*     */       
/* 325 */       double var6 = (par2 + par5Random.nextFloat());
/* 326 */       double var8 = par3 - 0.05D;
/* 327 */       double var10 = (par4 + par5Random.nextFloat());
/*     */       
/* 329 */       par1World.spawnParticle(EnumParticle.dripWater, var6, var8, var10, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeLeaves(World par1World, int par2, int par3, int par4) {
/* 336 */     dropBlockAsEntityItem((new BlockBreakInfo(par1World, par2, par3, par4)).setDroppedSelf());
/* 337 */     par1World.setBlockToAir(par2, par3, par4);
/*     */     
/* 339 */     Block block_above = par1World.getBlock(par2, par3 + 1, par4);
/*     */     
/* 341 */     if (block_above instanceof BlockUnderminable) {
/* 342 */       ((BlockUnderminable)block_above).tryToFall(par1World, par2, par3 + 1, par4);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean wasPlaced(int metadata) {
/* 468 */     return BitHelper.isBitSet(metadata, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 476 */     if (BitHelper.isBitSet(info.getMetadata(), 4)) {
/* 477 */       return 0;
/*     */     }
/* 479 */     if (info.getBiome().isSwampBiome() && info.world.rand.nextInt(2) == 0) {
/* 480 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 484 */     int leaf_kind = getBlockSubtype(info.getMetadata());
/*     */     
/*     */     int num_drops;
/*     */     
/* 488 */     if ((num_drops = dropBlockAsEntityItem(info, Block.sapling.blockID, leaf_kind, 1, 0.01F)) > 0) {
/* 489 */       return num_drops;
/*     */     }
/* 491 */     if ((num_drops = dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 0.05F)) > 0) {
/* 492 */       return num_drops;
/*     */     }
/*     */ 
/*     */     
/* 496 */     if (leaf_kind == 0)
/* 497 */       return dropBlockAsEntityItem(info, info.getBiome().isJungleBiome() ? Item.orange.itemID : Item.appleRed.itemID, 0, 1, 0.005F); 
/* 498 */     if (leaf_kind == 3) {
/* 499 */       return dropBlockAsEntityItem(info, Item.banana.itemID, 0, 1, 0.005F);
/*     */     }
/* 501 */     return 0;
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
/*     */   public String getMetadataNotes() {
/* 531 */     return "Bits 1 and 2 used for subtype, bit 4 set if placed by player, and bit 8 set when a neighboring leaf block of any type is destroyed";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/* 536 */     return (metadata >= 0 && metadata < 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/* 541 */     return metadata & 0x3;
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
/*     */   public Icon getIcon(int par1, int par2) {
/* 558 */     return ((par2 & 0x3) == 1) ? this.iconArray[this.iconType][1] : (((par2 & 0x3) == 3) ? this.iconArray[this.iconType][3] : (((par2 & 0x3) == 2) ? this.iconArray[this.iconType][2] : this.iconArray[this.iconType][0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGraphicsLevel(boolean par1) {
/* 566 */     this.graphicsLevel = par1;
/* 567 */     this.iconType = par1 ? 0 : 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 572 */     return !this.graphicsLevel;
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 601 */     for (int var2 = 0; var2 < field_94396_b.length; var2++) {
/*     */       
/* 603 */       this.iconArray[var2] = new Icon[(field_94396_b[var2]).length];
/*     */       
/* 605 */       for (int var3 = 0; var3 < (field_94396_b[var2]).length; var3++)
/*     */       {
/* 607 */         this.iconArray[var2][var3] = par1IconRegister.registerIcon(field_94396_b[var2][var3]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBlockUnplacedJungleLeaves(World world, int x, int y, int z) {
/* 614 */     Block block = Block.blocksList[world.getBlockId(x, y, z)];
/*     */     
/* 616 */     if (block != Block.leaves) {
/* 617 */       return false;
/*     */     }
/* 619 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 621 */     return (metadata == 3 || metadata == 11);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockLeaves.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */