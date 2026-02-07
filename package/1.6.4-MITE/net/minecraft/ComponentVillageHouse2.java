/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComponentVillageHouse2
/*     */   extends ComponentVillage
/*     */ {
/*  12 */   private static final WeightedRandomChestContent[] villageBlacksmithChestContents = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.shardEmerald.itemID, 0, 1, 3, 2), new WeightedRandomChestContent(Item.shardDiamond.itemID, 0, 1, 3, 1), new WeightedRandomChestContent(Item.copperNugget.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.silverNugget.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 1, 8, 5), new WeightedRandomChestContent(Item.ironNugget.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.ingotCopper.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.coinCopper.itemID, 0, 1, 20, 15), new WeightedRandomChestContent(Item.coinSilver.itemID, 0, 1, 10, 10), new WeightedRandomChestContent(Item.coinGold.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.bread.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.appleRed.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.plateLeather.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.helmetLeather.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.legsLeather.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.bootsLeather.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.helmetCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.plateCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.legsCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.bootsCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.helmetChainCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.plateChainCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.legsChainCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.bootsChainCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.helmetIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.plateIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.legsIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.bootsIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.helmetChainIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.plateChainIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.legsChainIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.bootsChainIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.shovelCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.shovelIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.hoeCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.hoeIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.mattockCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.mattockIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.hatchetCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.hatchetIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.axeCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.axeIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.shearsCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.shears.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.scytheCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.pickaxeCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.daggerCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.daggerIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.swordCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.swordIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.warHammerCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.warHammerIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.battleAxeCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.battleAxeIron.itemID, 0, 1, 1, 5) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasMadeChest;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentVillageHouse2() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentVillageHouse2(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  99 */     super(par1ComponentVillageStartPiece, par2);
/* 100 */     this.coordBaseMode = par5;
/* 101 */     this.boundingBox = par4StructureBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentVillageHouse2 func_74915_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
/* 106 */     StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 10, 6, 7, par6);
/* 107 */     return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null) ? new ComponentVillageHouse2(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/* 112 */     super.func_143012_a(par1NBTTagCompound);
/* 113 */     par1NBTTagCompound.setBoolean("Chest", this.hasMadeChest);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/* 118 */     super.func_143011_b(par1NBTTagCompound);
/* 119 */     this.hasMadeChest = par1NBTTagCompound.getBoolean("Chest");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/* 128 */     if (this.field_143015_k < 0) {
/*     */       
/* 130 */       this.field_143015_k = getAverageGroundLevel(par1World, par3StructureBoundingBox);
/*     */       
/* 132 */       if (this.field_143015_k < 0)
/*     */       {
/* 134 */         return true;
/*     */       }
/*     */       
/* 137 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
/*     */     } 
/*     */     
/* 140 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 9, 4, 6, 0, 0, false);
/* 141 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 9, 0, 6, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/* 142 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 0, 9, 4, 6, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/* 143 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 9, 5, 6, Block.stoneSingleSlab.blockID, Block.stoneSingleSlab.blockID, false);
/* 144 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 8, 5, 5, 0, 0, false);
/* 145 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 0, 2, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
/* 146 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 4, 0, Block.wood.blockID, Block.wood.blockID, false);
/* 147 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 0, 3, 4, 0, Block.wood.blockID, Block.wood.blockID, false);
/* 148 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 6, 0, 4, 6, Block.wood.blockID, Block.wood.blockID, false);
/* 149 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 3, 3, 1, par3StructureBoundingBox);
/* 150 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 2, 3, 3, 2, Block.planks.blockID, Block.planks.blockID, false);
/* 151 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 3, 5, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
/* 152 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 3, 5, Block.planks.blockID, Block.planks.blockID, false);
/* 153 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 6, 5, 3, 6, Block.planks.blockID, Block.planks.blockID, false);
/* 154 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 0, 5, 3, 0, Block.fence.blockID, Block.fence.blockID, false);
/* 155 */     fillWithBlocks(par1World, par3StructureBoundingBox, 9, 1, 0, 9, 3, 0, Block.fence.blockID, Block.fence.blockID, false);
/* 156 */     fillWithBlocks(par1World, par3StructureBoundingBox, 6, 1, 4, 9, 4, 6, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/* 157 */     placeBlockAtCurrentPosition(par1World, Block.lavaMoving.blockID, 0, 7, 1, 5, par3StructureBoundingBox);
/* 158 */     placeBlockAtCurrentPosition(par1World, Block.lavaMoving.blockID, 0, 8, 1, 5, par3StructureBoundingBox);
/* 159 */     placeBlockAtCurrentPosition(par1World, Block.fenceIron.blockID, 0, 9, 2, 5, par3StructureBoundingBox);
/* 160 */     placeBlockAtCurrentPosition(par1World, Block.fenceIron.blockID, 0, 9, 2, 4, par3StructureBoundingBox);
/* 161 */     fillWithBlocks(par1World, par3StructureBoundingBox, 7, 2, 4, 8, 2, 5, 0, 0, false);
/* 162 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 6, 1, 3, par3StructureBoundingBox);
/*     */ 
/*     */ 
/*     */     
/* 166 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.furnaceIdle, 6, 2, 3, 5, par3StructureBoundingBox);
/* 167 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.furnaceIdle, 6, 3, 3, 5, par3StructureBoundingBox);
/*     */     
/* 169 */     placeBlockAtCurrentPosition(par1World, Block.stoneDoubleSlab.blockID, 0, 8, 1, 1, par3StructureBoundingBox);
/* 170 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 2, par3StructureBoundingBox);
/* 171 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 4, par3StructureBoundingBox);
/* 172 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 2, 6, par3StructureBoundingBox);
/* 173 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 2, 6, par3StructureBoundingBox);
/* 174 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 2, 1, 4, par3StructureBoundingBox);
/* 175 */     placeBlockAtCurrentPosition(par1World, Block.pressurePlatePlanks.blockID, 0, 2, 2, 4, par3StructureBoundingBox);
/* 176 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 1, 1, 5, par3StructureBoundingBox);
/* 177 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, getMetadataWithOffset(Block.stairsWoodOak.blockID, 3), 2, 1, 5, par3StructureBoundingBox);
/* 178 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, getMetadataWithOffset(Block.stairsWoodOak.blockID, 1), 1, 1, 4, par3StructureBoundingBox);
/*     */ 
/*     */ 
/*     */     
/* 182 */     if (!this.hasMadeChest) {
/*     */       
/* 184 */       int i = getYWithOffset(1);
/* 185 */       int var5 = getXWithOffset(5, 5);
/* 186 */       int var6 = getZWithOffset(5, 5);
/*     */       
/* 188 */       if (par3StructureBoundingBox.isVecInside(var5, i, var6)) {
/*     */         
/* 190 */         this.hasMadeChest = true;
/*     */         
/* 192 */         generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 5, 1, 5, Block.chestIron.blockID, villageBlacksmithChestContents, 3 + par2Random.nextInt(6), (float[])null, EnumDirection.SOUTH);
/*     */       } 
/*     */     } 
/*     */     int var4;
/* 196 */     for (var4 = 6; var4 <= 8; var4++) {
/*     */       
/* 198 */       if (getBlockIdAtCurrentPosition(par1World, var4, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, var4, -1, -1, par3StructureBoundingBox) != 0)
/*     */       {
/* 200 */         placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), var4, 0, -1, par3StructureBoundingBox);
/*     */       }
/*     */     } 
/*     */     
/* 204 */     for (var4 = 0; var4 < 7; var4++) {
/*     */       
/* 206 */       for (int var5 = 0; var5 < 10; var5++) {
/*     */         
/* 208 */         clearCurrentPositionBlocksUpwards(par1World, var5, 6, var4, par3StructureBoundingBox);
/* 209 */         fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var5, -1, var4, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 213 */     spawnVillagers(par1World, par3StructureBoundingBox, 7, 1, 1, 1);
/* 214 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getVillagerType(int par1) {
/* 222 */     return 3;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageHouse2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */