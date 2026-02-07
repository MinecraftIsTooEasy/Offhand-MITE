/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComponentScatteredFeatureJunglePyramid
/*     */   extends ComponentScatteredFeature
/*     */ {
/*     */   private boolean field_74947_h;
/*     */   private boolean field_74948_i;
/*     */   private boolean field_74945_j;
/*     */   private boolean field_74946_k;
/*  14 */   private static final WeightedRandomChestContent[] junglePyramidsChestContents = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.shardDiamond.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 2, 7, 15), new WeightedRandomChestContent(Item.shardEmerald.itemID, 0, 1, 3, 2), new WeightedRandomChestContent(Item.bone.itemID, 0, 4, 6, 20), new WeightedRandomChestContent(Item.rottenFlesh.itemID, 0, 3, 7, 16), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.horseArmorIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.horseArmorSilver.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.horseArmorGold.itemID, 0, 1, 1, 1) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  20 */   private static final WeightedRandomChestContent[] junglePyramidsDispenserContents = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.arrowFlint.itemID, 0, 2, 7, 30) };
/*     */ 
/*     */   
/*  23 */   private static StructureScatteredFeatureStones junglePyramidsRandomScatteredStones = new StructureScatteredFeatureStones((ComponentScatteredFeaturePieces2)null);
/*     */ 
/*     */   
/*     */   public ComponentScatteredFeatureJunglePyramid() {}
/*     */   
/*     */   public ComponentScatteredFeatureJunglePyramid(Random par1Random, int par2, int par3) {
/*  29 */     super(par1Random, par2, 64, par3, 12, 10, 15);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  34 */     super.func_143012_a(par1NBTTagCompound);
/*  35 */     par1NBTTagCompound.setBoolean("placedMainChest", this.field_74947_h);
/*  36 */     par1NBTTagCompound.setBoolean("placedHiddenChest", this.field_74948_i);
/*  37 */     par1NBTTagCompound.setBoolean("placedTrap1", this.field_74945_j);
/*  38 */     par1NBTTagCompound.setBoolean("placedTrap2", this.field_74946_k);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  43 */     super.func_143011_b(par1NBTTagCompound);
/*  44 */     this.field_74947_h = par1NBTTagCompound.getBoolean("placedMainChest");
/*  45 */     this.field_74948_i = par1NBTTagCompound.getBoolean("placedHiddenChest");
/*  46 */     this.field_74945_j = par1NBTTagCompound.getBoolean("placedTrap1");
/*  47 */     this.field_74946_k = par1NBTTagCompound.getBoolean("placedTrap2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  56 */     if (!func_74935_a(par1World, par3StructureBoundingBox, 0))
/*     */     {
/*  58 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  62 */     int var4 = getMetadataWithOffset(Block.stairsCobblestone.blockID, 3);
/*  63 */     int var5 = getMetadataWithOffset(Block.stairsCobblestone.blockID, 2);
/*  64 */     int var6 = getMetadataWithOffset(Block.stairsCobblestone.blockID, 0);
/*  65 */     int var7 = getMetadataWithOffset(Block.stairsCobblestone.blockID, 1);
/*  66 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, false, par2Random, junglePyramidsRandomScatteredStones);
/*  67 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 1, 2, 9, 2, 2, false, par2Random, junglePyramidsRandomScatteredStones);
/*  68 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 1, 12, 9, 2, 12, false, par2Random, junglePyramidsRandomScatteredStones);
/*  69 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 1, 3, 2, 2, 11, false, par2Random, junglePyramidsRandomScatteredStones);
/*  70 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, 1, 3, 9, 2, 11, false, par2Random, junglePyramidsRandomScatteredStones);
/*  71 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 3, 1, 10, 6, 1, false, par2Random, junglePyramidsRandomScatteredStones);
/*  72 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 3, 13, 10, 6, 13, false, par2Random, junglePyramidsRandomScatteredStones);
/*  73 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 3, 2, 1, 6, 12, false, par2Random, junglePyramidsRandomScatteredStones);
/*  74 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 10, 3, 2, 10, 6, 12, false, par2Random, junglePyramidsRandomScatteredStones);
/*  75 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 3, 2, 9, 3, 12, false, par2Random, junglePyramidsRandomScatteredStones);
/*  76 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 6, 2, 9, 6, 12, false, par2Random, junglePyramidsRandomScatteredStones);
/*  77 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 3, 7, 3, 8, 7, 11, false, par2Random, junglePyramidsRandomScatteredStones);
/*  78 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 8, 4, 7, 8, 10, false, par2Random, junglePyramidsRandomScatteredStones);
/*  79 */     fillWithAir(par1World, par3StructureBoundingBox, 3, 1, 3, 8, 2, 11);
/*  80 */     fillWithAir(par1World, par3StructureBoundingBox, 4, 3, 6, 7, 3, 9);
/*  81 */     fillWithAir(par1World, par3StructureBoundingBox, 2, 4, 2, 9, 5, 12);
/*  82 */     fillWithAir(par1World, par3StructureBoundingBox, 4, 6, 5, 7, 6, 9);
/*  83 */     fillWithAir(par1World, par3StructureBoundingBox, 5, 7, 6, 6, 7, 8);
/*  84 */     fillWithAir(par1World, par3StructureBoundingBox, 5, 1, 2, 6, 2, 2);
/*  85 */     fillWithAir(par1World, par3StructureBoundingBox, 5, 2, 12, 6, 2, 12);
/*  86 */     fillWithAir(par1World, par3StructureBoundingBox, 5, 5, 1, 6, 5, 1);
/*  87 */     fillWithAir(par1World, par3StructureBoundingBox, 5, 5, 13, 6, 5, 13);
/*  88 */     placeBlockAtCurrentPosition(par1World, 0, 0, 1, 5, 5, par3StructureBoundingBox);
/*  89 */     placeBlockAtCurrentPosition(par1World, 0, 0, 10, 5, 5, par3StructureBoundingBox);
/*  90 */     placeBlockAtCurrentPosition(par1World, 0, 0, 1, 5, 9, par3StructureBoundingBox);
/*  91 */     placeBlockAtCurrentPosition(par1World, 0, 0, 10, 5, 9, par3StructureBoundingBox);
/*     */     
/*     */     int var8;
/*  94 */     for (var8 = 0; var8 <= 14; var8 += 14) {
/*     */       
/*  96 */       fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 4, var8, 2, 5, var8, false, par2Random, junglePyramidsRandomScatteredStones);
/*  97 */       fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 4, var8, 4, 5, var8, false, par2Random, junglePyramidsRandomScatteredStones);
/*  98 */       fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, 4, var8, 7, 5, var8, false, par2Random, junglePyramidsRandomScatteredStones);
/*  99 */       fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, 4, var8, 9, 5, var8, false, par2Random, junglePyramidsRandomScatteredStones);
/*     */     } 
/*     */     
/* 102 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 5, 6, 0, 6, 6, 0, false, par2Random, junglePyramidsRandomScatteredStones);
/*     */     
/* 104 */     for (var8 = 0; var8 <= 11; var8 += 11) {
/*     */       
/* 106 */       for (int var9 = 2; var9 <= 12; var9 += 2)
/*     */       {
/* 108 */         fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, var8, 4, var9, var8, 5, var9, false, par2Random, junglePyramidsRandomScatteredStones);
/*     */       }
/*     */       
/* 111 */       fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, var8, 6, 5, var8, 6, 5, false, par2Random, junglePyramidsRandomScatteredStones);
/* 112 */       fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, var8, 6, 9, var8, 6, 9, false, par2Random, junglePyramidsRandomScatteredStones);
/*     */     } 
/*     */     
/* 115 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 7, 2, 2, 9, 2, false, par2Random, junglePyramidsRandomScatteredStones);
/* 116 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, 7, 2, 9, 9, 2, false, par2Random, junglePyramidsRandomScatteredStones);
/* 117 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 7, 12, 2, 9, 12, false, par2Random, junglePyramidsRandomScatteredStones);
/* 118 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, 7, 12, 9, 9, 12, false, par2Random, junglePyramidsRandomScatteredStones);
/* 119 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 9, 4, 4, 9, 4, false, par2Random, junglePyramidsRandomScatteredStones);
/* 120 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, 9, 4, 7, 9, 4, false, par2Random, junglePyramidsRandomScatteredStones);
/* 121 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 9, 10, 4, 9, 10, false, par2Random, junglePyramidsRandomScatteredStones);
/* 122 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, 9, 10, 7, 9, 10, false, par2Random, junglePyramidsRandomScatteredStones);
/* 123 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 5, 9, 7, 6, 9, 7, false, par2Random, junglePyramidsRandomScatteredStones);
/* 124 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 5, 9, 6, par3StructureBoundingBox);
/* 125 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 6, 9, 6, par3StructureBoundingBox);
/* 126 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 5, 9, 8, par3StructureBoundingBox);
/* 127 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 6, 9, 8, par3StructureBoundingBox);
/* 128 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 4, 0, 0, par3StructureBoundingBox);
/* 129 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 5, 0, 0, par3StructureBoundingBox);
/* 130 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 6, 0, 0, par3StructureBoundingBox);
/* 131 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 7, 0, 0, par3StructureBoundingBox);
/* 132 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 4, 1, 8, par3StructureBoundingBox);
/* 133 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 4, 2, 9, par3StructureBoundingBox);
/* 134 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 4, 3, 10, par3StructureBoundingBox);
/* 135 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 7, 1, 8, par3StructureBoundingBox);
/* 136 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 7, 2, 9, par3StructureBoundingBox);
/* 137 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var4, 7, 3, 10, par3StructureBoundingBox);
/* 138 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 1, 9, 4, 1, 9, false, par2Random, junglePyramidsRandomScatteredStones);
/* 139 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, 1, 9, 7, 1, 9, false, par2Random, junglePyramidsRandomScatteredStones);
/* 140 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 1, 10, 7, 2, 10, false, par2Random, junglePyramidsRandomScatteredStones);
/* 141 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 5, 4, 5, 6, 4, 5, false, par2Random, junglePyramidsRandomScatteredStones);
/* 142 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var6, 4, 4, 5, par3StructureBoundingBox);
/* 143 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var7, 7, 4, 5, par3StructureBoundingBox);
/*     */     
/* 145 */     for (var8 = 0; var8 < 4; var8++) {
/*     */       
/* 147 */       placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 5, 0 - var8, 6 + var8, par3StructureBoundingBox);
/* 148 */       placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 6, 0 - var8, 6 + var8, par3StructureBoundingBox);
/* 149 */       fillWithAir(par1World, par3StructureBoundingBox, 5, 0 - var8, 7 + var8, 6, 0 - var8, 9 + var8);
/*     */     } 
/*     */     
/* 152 */     fillWithAir(par1World, par3StructureBoundingBox, 1, -3, 12, 10, -1, 13);
/* 153 */     fillWithAir(par1World, par3StructureBoundingBox, 1, -3, 1, 3, -1, 13);
/* 154 */     fillWithAir(par1World, par3StructureBoundingBox, 1, -3, 1, 9, -1, 5);
/*     */     
/* 156 */     for (var8 = 1; var8 <= 13; var8 += 2)
/*     */     {
/* 158 */       fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, -3, var8, 1, -2, var8, false, par2Random, junglePyramidsRandomScatteredStones);
/*     */     }
/*     */     
/* 161 */     for (var8 = 2; var8 <= 12; var8 += 2)
/*     */     {
/* 163 */       fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, -1, var8, 3, -1, var8, false, par2Random, junglePyramidsRandomScatteredStones);
/*     */     }
/*     */     
/* 166 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, -2, 1, 5, -2, 1, false, par2Random, junglePyramidsRandomScatteredStones);
/* 167 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, -2, 1, 9, -2, 1, false, par2Random, junglePyramidsRandomScatteredStones);
/* 168 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 6, -3, 1, 6, -3, 1, false, par2Random, junglePyramidsRandomScatteredStones);
/* 169 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 6, -1, 1, 6, -1, 1, false, par2Random, junglePyramidsRandomScatteredStones);
/* 170 */     placeBlockAtCurrentPosition(par1World, Block.tripWireSource.blockID, getMetadataWithOffset(Block.tripWireSource.blockID, 3) | 0x4, 1, -3, 8, par3StructureBoundingBox);
/* 171 */     placeBlockAtCurrentPosition(par1World, Block.tripWireSource.blockID, getMetadataWithOffset(Block.tripWireSource.blockID, 1) | 0x4, 4, -3, 8, par3StructureBoundingBox);
/* 172 */     placeBlockAtCurrentPosition(par1World, Block.tripWire.blockID, 4, 2, -3, 8, par3StructureBoundingBox);
/* 173 */     placeBlockAtCurrentPosition(par1World, Block.tripWire.blockID, 4, 3, -3, 8, par3StructureBoundingBox);
/* 174 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 5, -3, 7, par3StructureBoundingBox);
/* 175 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 5, -3, 6, par3StructureBoundingBox);
/* 176 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 5, -3, 5, par3StructureBoundingBox);
/* 177 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 5, -3, 4, par3StructureBoundingBox);
/* 178 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 5, -3, 3, par3StructureBoundingBox);
/* 179 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 5, -3, 2, par3StructureBoundingBox);
/* 180 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 5, -3, 1, par3StructureBoundingBox);
/* 181 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 4, -3, 1, par3StructureBoundingBox);
/* 182 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 3, -3, 1, par3StructureBoundingBox);
/*     */     
/* 184 */     if (!this.field_74945_j)
/*     */     {
/* 186 */       this.field_74945_j = generateStructureDispenserContents(par1World, par3StructureBoundingBox, par2Random, 3, -2, 1, 2, junglePyramidsDispenserContents, 2);
/*     */     }
/*     */     
/* 189 */     placeBlockAtCurrentPosition(par1World, Block.vine.blockID, 15, 3, -2, 2, par3StructureBoundingBox);
/* 190 */     placeBlockAtCurrentPosition(par1World, Block.tripWireSource.blockID, getMetadataWithOffset(Block.tripWireSource.blockID, 2) | 0x4, 7, -3, 1, par3StructureBoundingBox);
/* 191 */     placeBlockAtCurrentPosition(par1World, Block.tripWireSource.blockID, getMetadataWithOffset(Block.tripWireSource.blockID, 0) | 0x4, 7, -3, 5, par3StructureBoundingBox);
/* 192 */     placeBlockAtCurrentPosition(par1World, Block.tripWire.blockID, 4, 7, -3, 2, par3StructureBoundingBox);
/* 193 */     placeBlockAtCurrentPosition(par1World, Block.tripWire.blockID, 4, 7, -3, 3, par3StructureBoundingBox);
/* 194 */     placeBlockAtCurrentPosition(par1World, Block.tripWire.blockID, 4, 7, -3, 4, par3StructureBoundingBox);
/* 195 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 8, -3, 6, par3StructureBoundingBox);
/* 196 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 9, -3, 6, par3StructureBoundingBox);
/* 197 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 9, -3, 5, par3StructureBoundingBox);
/* 198 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 9, -3, 4, par3StructureBoundingBox);
/* 199 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 9, -2, 4, par3StructureBoundingBox);
/*     */     
/* 201 */     if (!this.field_74946_k)
/*     */     {
/* 203 */       this.field_74946_k = generateStructureDispenserContents(par1World, par3StructureBoundingBox, par2Random, 9, -2, 3, 4, junglePyramidsDispenserContents, 2);
/*     */     }
/*     */     
/* 206 */     placeBlockAtCurrentPosition(par1World, Block.vine.blockID, 15, 8, -1, 3, par3StructureBoundingBox);
/* 207 */     placeBlockAtCurrentPosition(par1World, Block.vine.blockID, 15, 8, -2, 3, par3StructureBoundingBox);
/*     */     
/* 209 */     if (!this.field_74947_h)
/*     */     {
/*     */       
/* 212 */       this.field_74947_h = generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 8, -3, 3, Block.chest.blockID, WeightedRandomChestContent.func_92080_a(junglePyramidsChestContents, new WeightedRandomChestContent[] { Item.enchantedBook.func_92114_b(par2Random) }), 2 + par2Random.nextInt(5), (float[])null, EnumDirection.WEST);
/*     */     }
/*     */     
/* 215 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 9, -3, 2, par3StructureBoundingBox);
/* 216 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 8, -3, 1, par3StructureBoundingBox);
/* 217 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 4, -3, 5, par3StructureBoundingBox);
/* 218 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 5, -2, 5, par3StructureBoundingBox);
/* 219 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 5, -1, 5, par3StructureBoundingBox);
/* 220 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 6, -3, 5, par3StructureBoundingBox);
/* 221 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 7, -2, 5, par3StructureBoundingBox);
/* 222 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 7, -1, 5, par3StructureBoundingBox);
/* 223 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 8, -3, 5, par3StructureBoundingBox);
/* 224 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, -1, 1, 9, -1, 5, false, par2Random, junglePyramidsRandomScatteredStones);
/* 225 */     fillWithAir(par1World, par3StructureBoundingBox, 8, -3, 8, 10, -1, 10);
/* 226 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 8, -2, 11, par3StructureBoundingBox);
/* 227 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 9, -2, 11, par3StructureBoundingBox);
/* 228 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 10, -2, 11, par3StructureBoundingBox);
/* 229 */     placeBlockAtCurrentPosition(par1World, Block.lever.blockID, BlockLever.invertMetadata(getMetadataWithOffset(Block.lever.blockID, 2)), 8, -2, 12, par3StructureBoundingBox);
/* 230 */     placeBlockAtCurrentPosition(par1World, Block.lever.blockID, BlockLever.invertMetadata(getMetadataWithOffset(Block.lever.blockID, 2)), 9, -2, 12, par3StructureBoundingBox);
/* 231 */     placeBlockAtCurrentPosition(par1World, Block.lever.blockID, BlockLever.invertMetadata(getMetadataWithOffset(Block.lever.blockID, 2)), 10, -2, 12, par3StructureBoundingBox);
/* 232 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 8, -3, 8, 8, -3, 10, false, par2Random, junglePyramidsRandomScatteredStones);
/* 233 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 10, -3, 8, 10, -3, 10, false, par2Random, junglePyramidsRandomScatteredStones);
/* 234 */     placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 10, -2, 9, par3StructureBoundingBox);
/* 235 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 8, -2, 9, par3StructureBoundingBox);
/* 236 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 8, -2, 10, par3StructureBoundingBox);
/* 237 */     placeBlockAtCurrentPosition(par1World, Block.redstoneWire.blockID, 0, 10, -1, 9, par3StructureBoundingBox);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 242 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.pistonStickyBase, 9, -2, 8, 1, par3StructureBoundingBox);
/* 243 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.pistonStickyBase, 10, -2, 8, 4, par3StructureBoundingBox);
/* 244 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.pistonStickyBase, 10, -1, 8, 4, par3StructureBoundingBox);
/*     */     
/* 246 */     placeBlockAtCurrentPosition(par1World, Block.redstoneRepeaterIdle.blockID, getMetadataWithOffset(Block.redstoneRepeaterIdle.blockID, 2), 10, -2, 10, par3StructureBoundingBox);
/*     */     
/* 248 */     if (!this.field_74948_i) {
/*     */       
/* 250 */       float[] chances_of_artifact = new float[1];
/* 251 */       chances_of_artifact[0] = 0.25F;
/*     */ 
/*     */       
/* 254 */       this.field_74948_i = generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 9, -3, 10, Block.chest.blockID, WeightedRandomChestContent.func_92080_a(junglePyramidsChestContents, new WeightedRandomChestContent[] { Item.enchantedBook.func_92114_b(par2Random) }), 2 + par2Random.nextInt(5), chances_of_artifact, EnumDirection.SOUTH);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentScatteredFeatureJunglePyramid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */