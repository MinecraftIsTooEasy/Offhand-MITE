/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class BlockSapling
/*     */   extends BlockPlant
/*     */ {
/*   9 */   public static final String[] WOOD_TYPES = new String[] { "oak", "spruce", "birch", "jungle" };
/*     */   
/*     */   private Icon[] saplingIcon;
/*     */   
/*     */   public static final int OAK_TREE = 0;
/*     */   
/*     */   protected BlockSapling(int par1) {
/*  16 */     super(par1);
/*  17 */     float var2 = 0.4F;
/*  18 */     setBlockBoundsForAllThreads((0.5F - var2), 0.0D, (0.5F - var2), (0.5F + var2), (var2 * 2.0F), (0.5F + var2));
/*  19 */     setMaxStackSize(16);
/*  20 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */     
/*  22 */     setCushioning(0.2F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SPRUCE_TREE = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int BIRCH_TREE = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int JUNGLE_TREE = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  43 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/*  44 */       return true;
/*     */     }
/*  46 */     if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(28) == 0) {
/*  47 */       return markOrGrowMarked(par1World, par2, par3, par4, par5Random);
/*     */     }
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  60 */     return this.saplingIcon[getBlockSubtype(par2)];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  66 */     if (!canGrowInBiome(getItemSubtype(par1World.getBlockMetadata(par2, par3, par4)), par1World.getBiomeGenForCoords(par2, par4))) {
/*  67 */       return false;
/*     */     }
/*  69 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */     
/*  71 */     if ((var6 & 0x8) == 0) {
/*     */       
/*  73 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 0x8, 4);
/*  74 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  78 */     growTree(par1World, par2, par3, par4, par5Random);
/*  79 */     return (par1World.getBlock(par2, par3, par4) != this || par1World.getBlockMetadata(par2, par3, par4) != var6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  89 */     int var6 = par1World.getBlockMetadata(par2, par3, par4) & 0x3;
/*  90 */     Object var7 = null;
/*  91 */     int var8 = 0;
/*  92 */     int var9 = 0;
/*  93 */     boolean var10 = false;
/*     */     
/*  95 */     if (var6 == 1) {
/*     */       
/*  97 */       var7 = new WorldGenTaiga2(true);
/*     */     }
/*  99 */     else if (var6 == 2) {
/*     */       
/* 101 */       var7 = new WorldGenForest(true);
/*     */     }
/* 103 */     else if (var6 == 3) {
/*     */       
/* 105 */       for (var8 = 0; var8 >= -1; var8--) {
/*     */         
/* 107 */         for (var9 = 0; var9 >= -1; var9--) {
/*     */           
/* 109 */           if (isSameSapling(par1World, par2 + var8, par3, par4 + var9, 3) && isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9, 3) && isSameSapling(par1World, par2 + var8, par3, par4 + var9 + 1, 3) && isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9 + 1, 3)) {
/*     */             
/* 111 */             var7 = new WorldGenHugeTrees(true, 10 + par5Random.nextInt(20), 3, 3);
/* 112 */             var10 = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 117 */         if (var7 != null) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 123 */       if (var7 == null)
/*     */       {
/* 125 */         var9 = 0;
/* 126 */         var8 = 0;
/* 127 */         var7 = new WorldGenTrees(true, 4 + par5Random.nextInt(7), 3, 3, false);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 132 */       var7 = new WorldGenTrees(true);
/*     */       
/* 134 */       if (par5Random.nextInt(10) == 0)
/*     */       {
/* 136 */         var7 = new WorldGenBigTree(true);
/*     */       }
/*     */     } 
/*     */     
/* 140 */     if (var10) {
/*     */       
/* 142 */       par1World.setBlock(par2 + var8, par3, par4 + var9, 0, 0, 4);
/* 143 */       par1World.setBlock(par2 + var8 + 1, par3, par4 + var9, 0, 0, 4);
/* 144 */       par1World.setBlock(par2 + var8, par3, par4 + var9 + 1, 0, 0, 4);
/* 145 */       par1World.setBlock(par2 + var8 + 1, par3, par4 + var9 + 1, 0, 0, 4);
/*     */     }
/*     */     else {
/*     */       
/* 149 */       par1World.setBlock(par2, par3, par4, 0, 0, 4);
/*     */     } 
/*     */     
/* 152 */     if (!((WorldGenerator)var7).generate(par1World, par5Random, par2 + var8, par3, par4 + var9))
/*     */     {
/* 154 */       if (var10) {
/*     */         
/* 156 */         par1World.setBlock(par2 + var8, par3, par4 + var9, this.blockID, var6, 4);
/* 157 */         par1World.setBlock(par2 + var8 + 1, par3, par4 + var9, this.blockID, var6, 4);
/* 158 */         par1World.setBlock(par2 + var8, par3, par4 + var9 + 1, this.blockID, var6, 4);
/* 159 */         par1World.setBlock(par2 + var8 + 1, par3, par4 + var9 + 1, this.blockID, var6, 4);
/*     */       }
/*     */       else {
/*     */         
/* 163 */         par1World.setBlock(par2, par3, par4, this.blockID, var6, 4);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5) {
/* 173 */     return (par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 0x3) == par5);
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
/* 186 */     return "Bits 1 and 2 used for subtype, bit 8 used for (one) intermediate growth stage";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/* 191 */     return ((metadata >= 0 && metadata < 4) || (metadata >= 8 && metadata < 12));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/* 196 */     return metadata & 0x3;
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
/* 216 */     this.saplingIcon = new Icon[WOOD_TYPES.length];
/*     */     
/* 218 */     for (int var2 = 0; var2 < this.saplingIcon.length; var2++)
/*     */     {
/* 220 */       this.saplingIcon[var2] = par1IconRegister.registerIcon(getTextureName() + "_" + WOOD_TYPES[var2]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canGrowInBiome(int subtype, BiomeGenBase biome) {
/* 226 */     if (!biome.hasRainfall()) {
/* 227 */       return false;
/*     */     }
/* 229 */     if (subtype == 0)
/*     */     {
/* 231 */       return (biome.temperature >= 0.4F);
/*     */     }
/* 233 */     if (subtype == 2)
/*     */     {
/* 235 */       return (biome.temperature >= 0.5F);
/*     */     }
/* 237 */     if (subtype == 3)
/*     */     {
/* 239 */       return (biome == BiomeGenBase.jungle || biome == BiomeGenBase.jungleHills || biome == BiomeGenBase.jungleRiver);
/*     */     }
/*     */     
/* 242 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSapling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */