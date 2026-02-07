/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentScatteredFeatureDesertPyramid
/*     */   extends ComponentScatteredFeature {
/*   7 */   private boolean[] field_74940_h = new boolean[4];
/*     */ 
/*     */ 
/*     */   
/*  11 */   private static final WeightedRandomChestContent[] itemsToGenerateInTemple = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.shardDiamond.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 2, 7, 15), new WeightedRandomChestContent(Item.shardEmerald.itemID, 0, 1, 3, 2), new WeightedRandomChestContent(Item.bone.itemID, 0, 4, 6, 20), new WeightedRandomChestContent(Item.rottenFlesh.itemID, 0, 3, 7, 16), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.bowlEmpty.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.paper.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.flowerPot.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.horseArmorIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.horseArmorSilver.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.horseArmorGold.itemID, 0, 1, 1, 1) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentScatteredFeatureDesertPyramid() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentScatteredFeatureDesertPyramid(Random par1Random, int par2, int par3) {
/*  31 */     super(par1Random, par2, 64, par3, 21, 15, 21);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  36 */     super.func_143012_a(par1NBTTagCompound);
/*  37 */     par1NBTTagCompound.setBoolean("hasPlacedChest0", this.field_74940_h[0]);
/*  38 */     par1NBTTagCompound.setBoolean("hasPlacedChest1", this.field_74940_h[1]);
/*  39 */     par1NBTTagCompound.setBoolean("hasPlacedChest2", this.field_74940_h[2]);
/*  40 */     par1NBTTagCompound.setBoolean("hasPlacedChest3", this.field_74940_h[3]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  45 */     super.func_143011_b(par1NBTTagCompound);
/*  46 */     this.field_74940_h[0] = par1NBTTagCompound.getBoolean("hasPlacedChest0");
/*  47 */     this.field_74940_h[1] = par1NBTTagCompound.getBoolean("hasPlacedChest1");
/*  48 */     this.field_74940_h[2] = par1NBTTagCompound.getBoolean("hasPlacedChest2");
/*  49 */     this.field_74940_h[3] = par1NBTTagCompound.getBoolean("hasPlacedChest3");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  58 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, Block.sandStone.blockID, Block.sandStone.blockID, false);
/*     */     
/*     */     int var4;
/*  61 */     for (var4 = 1; var4 <= 9; var4++) {
/*     */       
/*  63 */       fillWithBlocks(par1World, par3StructureBoundingBox, var4, var4, var4, this.scatteredFeatureSizeX - 1 - var4, var4, this.scatteredFeatureSizeZ - 1 - var4, Block.sandStone.blockID, Block.sandStone.blockID, false);
/*  64 */       fillWithBlocks(par1World, par3StructureBoundingBox, var4 + 1, var4, var4 + 1, this.scatteredFeatureSizeX - 2 - var4, var4, this.scatteredFeatureSizeZ - 2 - var4, 0, 0, false);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  69 */     for (var4 = 0; var4 < this.scatteredFeatureSizeX; var4++) {
/*     */       
/*  71 */       for (int i = 0; i < this.scatteredFeatureSizeZ; i++)
/*     */       {
/*  73 */         fillCurrentPositionBlocksDownwards(par1World, Block.sandStone.blockID, 0, var4, -5, i, par3StructureBoundingBox);
/*     */       }
/*     */     } 
/*     */     
/*  77 */     var4 = getMetadataWithOffset(Block.stairsSandStone.blockID, 3);
/*  78 */     int var5 = getMetadataWithOffset(Block.stairsSandStone.blockID, 2);
/*  79 */     int var6 = getMetadataWithOffset(Block.stairsSandStone.blockID, 0);
/*  80 */     int var7 = getMetadataWithOffset(Block.stairsSandStone.blockID, 1);
/*  81 */     byte var8 = 1;
/*  82 */     byte var9 = 11;
/*  83 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 9, 4, Block.sandStone.blockID, 0, false);
/*  84 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 10, 1, 3, 10, 3, Block.sandStone.blockID, Block.sandStone.blockID, false);
/*  85 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var4, 2, 10, 0, par3StructureBoundingBox);
/*  86 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var5, 2, 10, 4, par3StructureBoundingBox);
/*  87 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var6, 0, 10, 2, par3StructureBoundingBox);
/*  88 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var7, 4, 10, 2, par3StructureBoundingBox);
/*  89 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 5, 0, 0, this.scatteredFeatureSizeX - 1, 9, 4, Block.sandStone.blockID, 0, false);
/*  90 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 4, 10, 1, this.scatteredFeatureSizeX - 2, 10, 3, Block.sandStone.blockID, Block.sandStone.blockID, false);
/*  91 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var4, this.scatteredFeatureSizeX - 3, 10, 0, par3StructureBoundingBox);
/*  92 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var5, this.scatteredFeatureSizeX - 3, 10, 4, par3StructureBoundingBox);
/*  93 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var6, this.scatteredFeatureSizeX - 5, 10, 2, par3StructureBoundingBox);
/*  94 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var7, this.scatteredFeatureSizeX - 1, 10, 2, par3StructureBoundingBox);
/*  95 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 0, 0, 12, 4, 4, Block.sandStone.blockID, 0, false);
/*  96 */     fillWithBlocks(par1World, par3StructureBoundingBox, 9, 1, 0, 11, 3, 4, 0, 0, false);
/*  97 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 9, 1, 1, par3StructureBoundingBox);
/*  98 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 9, 2, 1, par3StructureBoundingBox);
/*  99 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 9, 3, 1, par3StructureBoundingBox);
/* 100 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 10, 3, 1, par3StructureBoundingBox);
/* 101 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 11, 3, 1, par3StructureBoundingBox);
/* 102 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 11, 2, 1, par3StructureBoundingBox);
/* 103 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 11, 1, 1, par3StructureBoundingBox);
/* 104 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 1, 8, 3, 3, Block.sandStone.blockID, 0, false);
/* 105 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 2, 8, 2, 2, 0, 0, false);
/* 106 */     fillWithBlocks(par1World, par3StructureBoundingBox, 12, 1, 1, 16, 3, 3, Block.sandStone.blockID, 0, false);
/* 107 */     fillWithBlocks(par1World, par3StructureBoundingBox, 12, 1, 2, 16, 2, 2, 0, 0, false);
/* 108 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 4, 5, this.scatteredFeatureSizeX - 6, 4, this.scatteredFeatureSizeZ - 6, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 109 */     fillWithBlocks(par1World, par3StructureBoundingBox, 9, 4, 9, 11, 4, 11, 0, 0, false);
/* 110 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 1, 8, 8, 3, 8, Block.sandStone.blockID, 2, Block.sandStone.blockID, 2, false);
/* 111 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 1, 8, 12, 3, 8, Block.sandStone.blockID, 2, Block.sandStone.blockID, 2, false);
/* 112 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 1, 12, 8, 3, 12, Block.sandStone.blockID, 2, Block.sandStone.blockID, 2, false);
/* 113 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 1, 12, 12, 3, 12, Block.sandStone.blockID, 2, Block.sandStone.blockID, 2, false);
/* 114 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 5, 4, 4, 11, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 115 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 5, 1, 5, this.scatteredFeatureSizeX - 2, 4, 11, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 116 */     fillWithBlocks(par1World, par3StructureBoundingBox, 6, 7, 9, 6, 7, 11, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 117 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 7, 7, 9, this.scatteredFeatureSizeX - 7, 7, 11, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 118 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 5, 9, 5, 7, 11, Block.sandStone.blockID, 2, Block.sandStone.blockID, 2, false);
/* 119 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 6, 5, 9, this.scatteredFeatureSizeX - 6, 7, 11, Block.sandStone.blockID, 2, Block.sandStone.blockID, 2, false);
/* 120 */     placeBlockAtCurrentPosition(par1World, 0, 0, 5, 5, 10, par3StructureBoundingBox);
/* 121 */     placeBlockAtCurrentPosition(par1World, 0, 0, 5, 6, 10, par3StructureBoundingBox);
/* 122 */     placeBlockAtCurrentPosition(par1World, 0, 0, 6, 6, 10, par3StructureBoundingBox);
/* 123 */     placeBlockAtCurrentPosition(par1World, 0, 0, this.scatteredFeatureSizeX - 6, 5, 10, par3StructureBoundingBox);
/* 124 */     placeBlockAtCurrentPosition(par1World, 0, 0, this.scatteredFeatureSizeX - 6, 6, 10, par3StructureBoundingBox);
/* 125 */     placeBlockAtCurrentPosition(par1World, 0, 0, this.scatteredFeatureSizeX - 7, 6, 10, par3StructureBoundingBox);
/* 126 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 4, 4, 2, 6, 4, 0, 0, false);
/* 127 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 3, 4, 4, this.scatteredFeatureSizeX - 3, 6, 4, 0, 0, false);
/* 128 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var4, 2, 4, 5, par3StructureBoundingBox);
/* 129 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var4, 2, 3, 4, par3StructureBoundingBox);
/* 130 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var4, this.scatteredFeatureSizeX - 3, 4, 5, par3StructureBoundingBox);
/* 131 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var4, this.scatteredFeatureSizeX - 3, 3, 4, par3StructureBoundingBox);
/* 132 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 3, 2, 2, 3, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 133 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 3, 1, 3, this.scatteredFeatureSizeX - 2, 2, 3, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 134 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, 0, 1, 1, 2, par3StructureBoundingBox);
/* 135 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, 0, this.scatteredFeatureSizeX - 2, 1, 2, par3StructureBoundingBox);
/* 136 */     placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 1, 1, 2, 2, par3StructureBoundingBox);
/* 137 */     placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 1, this.scatteredFeatureSizeX - 2, 2, 2, par3StructureBoundingBox);
/* 138 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var7, 2, 1, 2, par3StructureBoundingBox);
/* 139 */     placeBlockAtCurrentPosition(par1World, Block.stairsSandStone.blockID, var6, this.scatteredFeatureSizeX - 3, 1, 2, par3StructureBoundingBox);
/* 140 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 5, 4, 3, 18, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 141 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 5, 3, 5, this.scatteredFeatureSizeX - 5, 3, 17, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 142 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 5, 4, 2, 16, 0, 0, false);
/* 143 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 6, 1, 5, this.scatteredFeatureSizeX - 5, 2, 16, 0, 0, false);
/*     */     
/*     */     int var10;
/* 146 */     for (var10 = 5; var10 <= 17; var10 += 2) {
/*     */       
/* 148 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 4, 1, var10, par3StructureBoundingBox);
/* 149 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, 4, 2, var10, par3StructureBoundingBox);
/* 150 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, this.scatteredFeatureSizeX - 5, 1, var10, par3StructureBoundingBox);
/* 151 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, this.scatteredFeatureSizeX - 5, 2, var10, par3StructureBoundingBox);
/*     */     } 
/*     */     
/* 154 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 10, 0, 7, par3StructureBoundingBox);
/* 155 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 10, 0, 8, par3StructureBoundingBox);
/* 156 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 9, 0, 9, par3StructureBoundingBox);
/* 157 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 11, 0, 9, par3StructureBoundingBox);
/* 158 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 8, 0, 10, par3StructureBoundingBox);
/* 159 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 12, 0, 10, par3StructureBoundingBox);
/* 160 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 7, 0, 10, par3StructureBoundingBox);
/* 161 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 13, 0, 10, par3StructureBoundingBox);
/* 162 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 9, 0, 11, par3StructureBoundingBox);
/* 163 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 11, 0, 11, par3StructureBoundingBox);
/* 164 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 10, 0, 12, par3StructureBoundingBox);
/* 165 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 10, 0, 13, par3StructureBoundingBox);
/* 166 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var9, 10, 0, 10, par3StructureBoundingBox);
/*     */     
/* 168 */     for (var10 = 0; var10 <= this.scatteredFeatureSizeX - 1; var10 += this.scatteredFeatureSizeX - 1) {
/*     */       
/* 170 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 2, 1, par3StructureBoundingBox);
/* 171 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 2, 2, par3StructureBoundingBox);
/* 172 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 2, 3, par3StructureBoundingBox);
/* 173 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 3, 1, par3StructureBoundingBox);
/* 174 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 3, 2, par3StructureBoundingBox);
/* 175 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 3, 3, par3StructureBoundingBox);
/* 176 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 4, 1, par3StructureBoundingBox);
/* 177 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, var10, 4, 2, par3StructureBoundingBox);
/* 178 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 4, 3, par3StructureBoundingBox);
/* 179 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 5, 1, par3StructureBoundingBox);
/* 180 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 5, 2, par3StructureBoundingBox);
/* 181 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 5, 3, par3StructureBoundingBox);
/* 182 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 6, 1, par3StructureBoundingBox);
/* 183 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, var10, 6, 2, par3StructureBoundingBox);
/* 184 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 6, 3, par3StructureBoundingBox);
/* 185 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 7, 1, par3StructureBoundingBox);
/* 186 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 7, 2, par3StructureBoundingBox);
/* 187 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 7, 3, par3StructureBoundingBox);
/* 188 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 8, 1, par3StructureBoundingBox);
/* 189 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 8, 2, par3StructureBoundingBox);
/* 190 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 8, 3, par3StructureBoundingBox);
/*     */     } 
/*     */     
/* 193 */     for (var10 = 2; var10 <= this.scatteredFeatureSizeX - 3; var10 += this.scatteredFeatureSizeX - 3 - 2) {
/*     */       
/* 195 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10 - 1, 2, 0, par3StructureBoundingBox);
/* 196 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 2, 0, par3StructureBoundingBox);
/* 197 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10 + 1, 2, 0, par3StructureBoundingBox);
/* 198 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10 - 1, 3, 0, par3StructureBoundingBox);
/* 199 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 3, 0, par3StructureBoundingBox);
/* 200 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10 + 1, 3, 0, par3StructureBoundingBox);
/* 201 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10 - 1, 4, 0, par3StructureBoundingBox);
/* 202 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, var10, 4, 0, par3StructureBoundingBox);
/* 203 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10 + 1, 4, 0, par3StructureBoundingBox);
/* 204 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10 - 1, 5, 0, par3StructureBoundingBox);
/* 205 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 5, 0, par3StructureBoundingBox);
/* 206 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10 + 1, 5, 0, par3StructureBoundingBox);
/* 207 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10 - 1, 6, 0, par3StructureBoundingBox);
/* 208 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, var10, 6, 0, par3StructureBoundingBox);
/* 209 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10 + 1, 6, 0, par3StructureBoundingBox);
/* 210 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10 - 1, 7, 0, par3StructureBoundingBox);
/* 211 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10, 7, 0, par3StructureBoundingBox);
/* 212 */       placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, var10 + 1, 7, 0, par3StructureBoundingBox);
/* 213 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10 - 1, 8, 0, par3StructureBoundingBox);
/* 214 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10, 8, 0, par3StructureBoundingBox);
/* 215 */       placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, var10 + 1, 8, 0, par3StructureBoundingBox);
/*     */     } 
/*     */     
/* 218 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 4, 0, 12, 6, 0, Block.sandStone.blockID, 2, Block.sandStone.blockID, 2, false);
/* 219 */     placeBlockAtCurrentPosition(par1World, 0, 0, 8, 6, 0, par3StructureBoundingBox);
/* 220 */     placeBlockAtCurrentPosition(par1World, 0, 0, 12, 6, 0, par3StructureBoundingBox);
/* 221 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 9, 5, 0, par3StructureBoundingBox);
/* 222 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, 10, 5, 0, par3StructureBoundingBox);
/* 223 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, var8, 11, 5, 0, par3StructureBoundingBox);
/* 224 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, -14, 8, 12, -11, 12, Block.sandStone.blockID, 2, Block.sandStone.blockID, 2, false);
/* 225 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, -10, 8, 12, -10, 12, Block.sandStone.blockID, 1, Block.sandStone.blockID, 1, false);
/* 226 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, -9, 8, 12, -9, 12, Block.sandStone.blockID, 2, Block.sandStone.blockID, 2, false);
/* 227 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, -8, 8, 12, -1, 12, Block.sandStone.blockID, Block.sandStone.blockID, false);
/* 228 */     fillWithBlocks(par1World, par3StructureBoundingBox, 9, -11, 9, 11, -1, 11, 0, 0, false);
/* 229 */     placeBlockAtCurrentPosition(par1World, Block.pressurePlateStone.blockID, 0, 10, -11, 10, par3StructureBoundingBox);
/* 230 */     fillWithBlocks(par1World, par3StructureBoundingBox, 9, -13, 9, 11, -13, 11, Block.tnt.blockID, 0, false);
/* 231 */     placeBlockAtCurrentPosition(par1World, 0, 0, 8, -11, 10, par3StructureBoundingBox);
/* 232 */     placeBlockAtCurrentPosition(par1World, 0, 0, 8, -10, 10, par3StructureBoundingBox);
/* 233 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, 7, -10, 10, par3StructureBoundingBox);
/* 234 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 7, -11, 10, par3StructureBoundingBox);
/* 235 */     placeBlockAtCurrentPosition(par1World, 0, 0, 12, -11, 10, par3StructureBoundingBox);
/* 236 */     placeBlockAtCurrentPosition(par1World, 0, 0, 12, -10, 10, par3StructureBoundingBox);
/* 237 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, 13, -10, 10, par3StructureBoundingBox);
/* 238 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 13, -11, 10, par3StructureBoundingBox);
/* 239 */     placeBlockAtCurrentPosition(par1World, 0, 0, 10, -11, 8, par3StructureBoundingBox);
/* 240 */     placeBlockAtCurrentPosition(par1World, 0, 0, 10, -10, 8, par3StructureBoundingBox);
/* 241 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, 10, -10, 7, par3StructureBoundingBox);
/* 242 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 10, -11, 7, par3StructureBoundingBox);
/* 243 */     placeBlockAtCurrentPosition(par1World, 0, 0, 10, -11, 12, par3StructureBoundingBox);
/* 244 */     placeBlockAtCurrentPosition(par1World, 0, 0, 10, -10, 12, par3StructureBoundingBox);
/* 245 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 1, 10, -10, 13, par3StructureBoundingBox);
/* 246 */     placeBlockAtCurrentPosition(par1World, Block.sandStone.blockID, 2, 10, -11, 13, par3StructureBoundingBox);
/*     */     
/* 248 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 8, -10, 10, 1, par3StructureBoundingBox);
/* 249 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 12, -10, 10, 2, par3StructureBoundingBox);
/* 250 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 10, -10, 8, 4, par3StructureBoundingBox);
/* 251 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 10, -10, 12, 3, par3StructureBoundingBox);
/*     */     
/* 253 */     float[] chances_of_artifact = new float[1];
/* 254 */     chances_of_artifact[0] = 0.1F;
/*     */     
/* 256 */     for (var10 = 0; var10 < 4; var10++) {
/*     */       
/* 258 */       if (!this.field_74940_h[var10]) {
/*     */         
/* 260 */         int var11 = Direction.offsetX[var10] * 2;
/* 261 */         int var12 = Direction.offsetZ[var10] * 2;
/*     */ 
/*     */         
/* 264 */         EnumDirection direction_facing = (var10 == 0) ? EnumDirection.SOUTH : ((var10 == 1) ? EnumDirection.EAST : ((var10 == 2) ? EnumDirection.NORTH : EnumDirection.WEST));
/* 265 */         this.field_74940_h[var10] = generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 10 + var11, -11, 10 + var12, Block.chest.blockID, WeightedRandomChestContent.func_92080_a(itemsToGenerateInTemple, new WeightedRandomChestContent[] { Item.enchantedBook.func_92114_b(par2Random) }), 2 + par2Random.nextInt(5), chances_of_artifact, direction_facing);
/*     */       } 
/*     */     } 
/*     */     
/* 269 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentScatteredFeatureDesertPyramid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */