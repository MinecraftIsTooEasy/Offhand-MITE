/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockFlowerMulti
/*     */   extends BlockFlower
/*     */ {
/*     */   public static final int ROSE = 0;
/*     */   public static final int ORCHID = 1;
/*     */   public static final int ALLIUM = 2;
/*     */   public static final int TULIP = 5;
/*     */   public static final int DAHLIA = 7;
/*     */   public static final int DAISY = 8;
/*  17 */   public static final String[] types = new String[] { "rose", "orchid", "allium", null, null, "tulip", null, "dahlia", "daisy" };
/*     */   
/*     */   private Icon[] icons;
/*  20 */   private static int[] candidates = new int[types.length];
/*     */ 
/*     */   
/*     */   protected BlockFlowerMulti(int id, Material material) {
/*  24 */     super(id, material);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockFlowerMulti(int id) {
/*  29 */     this(id, Material.plants);
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  51 */     this.icons = registerIcons(par1IconRegister, types, getTextureName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int side, int metadata) {
/*  57 */     return this.icons[getBlockSubtype(metadata)];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  62 */     String[] array = new String[types.length];
/*     */     
/*  64 */     for (int i = 0; i < types.length; i++) {
/*     */       
/*  66 */       if (types[i] != null)
/*     */       {
/*     */         
/*  69 */         array[i] = i + "=" + StringHelper.capitalize(types[i]);
/*     */       }
/*     */     } 
/*  72 */     return StringHelper.implode(array, ", ", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  77 */     return (metadata >= 0 && metadata < types.length && types[metadata] != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/*  82 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/*  87 */     return (other_block == this) ? ((other_block_metadata != metadata)) : super.canBeReplacedBy(metadata, other_block, other_block_metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess block_access, int x, int y, int z) {
/*  92 */     int metadata = block_access.getBlockMetadata(x, y, z);
/*     */     
/*  94 */     float width = 0.2F;
/*     */     
/*  96 */     if (metadata == 0) {
/*     */       
/*  98 */       setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.6000000238418579D, (0.5F + width));
/*     */     }
/* 100 */     else if (metadata == 1) {
/*     */       
/* 102 */       width = 0.3F;
/* 103 */       setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.8125D, (0.5F + width));
/*     */     }
/* 105 */     else if (metadata == 2) {
/*     */       
/* 107 */       setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.875D, (0.5F + width));
/*     */     }
/* 109 */     else if (metadata == 5) {
/*     */       
/* 111 */       setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.75D, (0.5F + width));
/*     */     }
/* 113 */     else if (metadata == 7) {
/*     */       
/* 115 */       setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.8125D, (0.5F + width));
/*     */     }
/* 117 */     else if (metadata == 8) {
/*     */       
/* 119 */       setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.8125D, (0.5F + width));
/*     */     }
/*     */     else {
/*     */       
/* 123 */       Minecraft.setErrorMessage("setBlockBoundsBasedOnStateAndNeighbors: unhandled case");
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
/*     */   public int getRandomSubtypeForBiome(Random random, BiomeGenBase biome) {
/* 158 */     if (biome == BiomeGenBase.plains && random.nextInt(2) == 0) {
/* 159 */       return 8;
/*     */     }
/* 161 */     int num_candidates = 0;
/*     */     
/* 163 */     for (int i = 0; i < types.length; i++) {
/*     */       
/* 165 */       if (types[i] != null && isBiomeSuitable(biome, i)) {
/* 166 */         candidates[num_candidates++] = i;
/*     */       }
/*     */     } 
/* 169 */     return (num_candidates == 0) ? -1 : candidates[random.nextInt(num_candidates)];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRandomSubtypeThatCanOccurAt(World world, int x, int y, int z) {
/* 175 */     BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
/*     */     
/* 177 */     int subtype = getRandomSubtypeForBiome(world.rand, biome);
/*     */     
/* 179 */     if (subtype < 0) {
/* 180 */       return -1;
/*     */     }
/*     */     
/*     */     while (true) {
/* 184 */       if (canOccurAt(world, x, y, z, subtype)) {
/* 185 */         return subtype;
/*     */       }
/* 187 */       subtype = getRandomSubtypeForBiome(world.rand, biome);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBiomeSuitable(BiomeGenBase biome, int metadata) {
/* 193 */     if (!isValidMetadata(metadata)) {
/*     */       
/* 195 */       Minecraft.setErrorMessage("isBiomeSuitable: invalid metadata " + metadata);
/* 196 */       return false;
/*     */     } 
/*     */     
/* 199 */     int subtype = getBlockSubtype(metadata);
/*     */     
/* 201 */     if (types[subtype] == null) {
/*     */       
/* 203 */       Minecraft.setErrorMessage("isBiomeSuitable: invalid subtype " + subtype);
/*     */       
/* 205 */       return false;
/*     */     } 
/*     */     
/* 208 */     if (subtype == 2 && !biome.isSwampBiome()) {
/* 209 */       return false;
/*     */     }
/* 211 */     if (biome.isSwampBiome() && subtype != 2) {
/* 212 */       return false;
/*     */     }
/* 214 */     if (subtype == 1 && biome.temperature <= BiomeGenBase.plains.temperature) {
/* 215 */       return false;
/*     */     }
/* 217 */     if ((subtype == 5 || subtype == 7) && biome.temperature < BiomeGenBase.forestHills.temperature) {
/* 218 */       return false;
/*     */     }
/* 220 */     if (biome.isJungleBiome() && subtype == 8) {
/* 221 */       return false;
/*     */     }
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canOccurAt(World world, int x, int y, int z, int metadata) {
/* 228 */     return (isBiomeSuitable(world.getBiomeGenForCoords(x, z), metadata) && super.canOccurAt(world, x, y, z, metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPatchSize(int metadata, BiomeGenBase biome) {
/* 233 */     if (!isValidMetadata(metadata)) {
/* 234 */       Minecraft.setErrorMessage("getPatchSize: invalid metadata " + metadata);
/*     */     }
/* 236 */     int subtype = getBlockSubtype(metadata);
/*     */     
/* 238 */     if (subtype == 2) {
/* 239 */       return 8;
/*     */     }
/* 241 */     if (biome == BiomeGenBase.plains || biome.isJungleBiome()) {
/* 242 */       return 64;
/*     */     }
/* 244 */     return 16;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 249 */     return (isBiomeSuitable(world.getBiomeGenForCoords(x, z), metadata) && super.isLegalAt(world, x, y, z, metadata));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFlowerMulti.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */