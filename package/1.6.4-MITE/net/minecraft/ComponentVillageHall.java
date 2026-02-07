/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentVillageHall
/*     */   extends ComponentVillage
/*     */ {
/*     */   public ComponentVillageHall() {}
/*     */   
/*     */   public ComponentVillageHall(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  12 */     super(par1ComponentVillageStartPiece, par2);
/*  13 */     this.coordBaseMode = par5;
/*  14 */     this.boundingBox = par4StructureBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentVillageHall func_74906_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
/*  19 */     StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 9, 7, 11, par6);
/*  20 */     return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null) ? new ComponentVillageHall(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  29 */     if (this.field_143015_k < 0) {
/*     */       
/*  31 */       this.field_143015_k = getAverageGroundLevel(par1World, par3StructureBoundingBox);
/*     */       
/*  33 */       if (this.field_143015_k < 0)
/*     */       {
/*  35 */         return true;
/*     */       }
/*     */       
/*  38 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 7 - 1, 0);
/*     */     } 
/*     */     
/*  41 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 7, 4, 4, 0, 0, false);
/*  42 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 1, 6, 8, 4, 10, 0, 0, false);
/*  43 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 6, 8, 0, 10, Block.dirt.blockID, Block.dirt.blockID, false);
/*  44 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 6, 0, 6, par3StructureBoundingBox);
/*  45 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 1, 6, 2, 1, 10, Block.fence.blockID, Block.fence.blockID, false);
/*  46 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 1, 6, 8, 1, 10, Block.fence.blockID, Block.fence.blockID, false);
/*  47 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 10, 7, 1, 10, Block.fence.blockID, Block.fence.blockID, false);
/*  48 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 7, 0, 4, Block.planks.blockID, Block.planks.blockID, false);
/*  49 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 0, 3, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  50 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 0, 0, 8, 3, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  51 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 7, 1, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  52 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 5, 7, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  53 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 0, 7, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
/*  54 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 5, 7, 3, 5, Block.planks.blockID, Block.planks.blockID, false);
/*  55 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 1, 8, 4, 1, Block.planks.blockID, Block.planks.blockID, false);
/*  56 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 4, 8, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
/*  57 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 2, 8, 5, 3, Block.planks.blockID, Block.planks.blockID, false);
/*  58 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 0, 4, 2, par3StructureBoundingBox);
/*  59 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 0, 4, 3, par3StructureBoundingBox);
/*  60 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 8, 4, 2, par3StructureBoundingBox);
/*  61 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 8, 4, 3, par3StructureBoundingBox);
/*  62 */     int var4 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
/*  63 */     int var5 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 2);
/*     */     
/*     */     int var6;
/*     */     
/*  67 */     for (var6 = -1; var6 <= 2; var6++) {
/*     */       
/*  69 */       for (int var7 = 0; var7 <= 8; var7++) {
/*     */         
/*  71 */         placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var4, var7, 4 + var6, var6, par3StructureBoundingBox);
/*  72 */         placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var5, var7, 4 + var6, 5 - var6, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 0, 2, 1, par3StructureBoundingBox);
/*  77 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 0, 2, 4, par3StructureBoundingBox);
/*  78 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 8, 2, 1, par3StructureBoundingBox);
/*  79 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 8, 2, 4, par3StructureBoundingBox);
/*  80 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 2, par3StructureBoundingBox);
/*  81 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 3, par3StructureBoundingBox);
/*  82 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 2, par3StructureBoundingBox);
/*  83 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 3, par3StructureBoundingBox);
/*  84 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 2, 5, par3StructureBoundingBox);
/*  85 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 3, 2, 5, par3StructureBoundingBox);
/*  86 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 5, 2, 0, par3StructureBoundingBox);
/*  87 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 6, 2, 5, par3StructureBoundingBox);
/*  88 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 2, 1, 3, par3StructureBoundingBox);
/*  89 */     placeBlockAtCurrentPosition(par1World, Block.pressurePlatePlanks.blockID, 0, 2, 2, 3, par3StructureBoundingBox);
/*  90 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 1, 1, 4, par3StructureBoundingBox);
/*  91 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, getMetadataWithOffset(Block.stairsWoodOak.blockID, 3), 2, 1, 4, par3StructureBoundingBox);
/*  92 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, getMetadataWithOffset(Block.stairsWoodOak.blockID, 1), 1, 1, 3, par3StructureBoundingBox);
/*  93 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 0, 1, 7, 0, 3, Block.stoneDoubleSlab.blockID, Block.stoneDoubleSlab.blockID, false);
/*  94 */     placeBlockAtCurrentPosition(par1World, Block.stoneDoubleSlab.blockID, 0, 6, 1, 1, par3StructureBoundingBox);
/*  95 */     placeBlockAtCurrentPosition(par1World, Block.stoneDoubleSlab.blockID, 0, 6, 1, 2, par3StructureBoundingBox);
/*  96 */     placeBlockAtCurrentPosition(par1World, 0, 0, 2, 1, 0, par3StructureBoundingBox);
/*  97 */     placeBlockAtCurrentPosition(par1World, 0, 0, 2, 2, 0, par3StructureBoundingBox);
/*     */     
/*  99 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 2, 3, 1, 4, par3StructureBoundingBox);
/* 100 */     placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 2, 1, 0, getMetadataWithOffset(Block.doorWood.blockID, 1));
/*     */     
/* 102 */     if (getBlockIdAtCurrentPosition(par1World, 2, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 2, -1, -1, par3StructureBoundingBox) != 0)
/*     */     {
/* 104 */       placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 2, 0, -1, par3StructureBoundingBox);
/*     */     }
/*     */     
/* 107 */     placeBlockAtCurrentPosition(par1World, 0, 0, 6, 1, 5, par3StructureBoundingBox);
/* 108 */     placeBlockAtCurrentPosition(par1World, 0, 0, 6, 2, 5, par3StructureBoundingBox);
/*     */     
/* 110 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 6, 3, 4, 3, par3StructureBoundingBox);
/* 111 */     placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 6, 1, 5, getMetadataWithOffset(Block.doorWood.blockID, 1));
/*     */     
/* 113 */     for (var6 = 0; var6 < 5; var6++) {
/*     */       
/* 115 */       for (int var7 = 0; var7 < 9; var7++) {
/*     */         
/* 117 */         clearCurrentPositionBlocksUpwards(par1World, var7, 7, var6, par3StructureBoundingBox);
/* 118 */         fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var7, -1, var6, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     spawnVillagers(par1World, par3StructureBoundingBox, 4, 1, 2, 2);
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getVillagerType(int par1) {
/* 131 */     return (par1 == 0) ? 4 : 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageHall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */