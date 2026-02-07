/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentVillageHouse1
/*     */   extends ComponentVillage
/*     */ {
/*     */   public ComponentVillageHouse1() {}
/*     */   
/*     */   public ComponentVillageHouse1(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  12 */     super(par1ComponentVillageStartPiece, par2);
/*  13 */     this.coordBaseMode = par5;
/*  14 */     this.boundingBox = par4StructureBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentVillageHouse1 func_74898_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
/*  19 */     StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 9, 9, 6, par6);
/*  20 */     return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null) ? new ComponentVillageHouse1(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
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
/*  38 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 9 - 1, 0);
/*     */     } 
/*     */     
/*  41 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 7, 5, 4, 0, 0, false);
/*  42 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 8, 0, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  43 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 8, 5, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  44 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 1, 8, 6, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  45 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 7, 2, 8, 7, 3, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  46 */     int var4 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
/*  47 */     int var5 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 2);
/*     */     
/*     */     int var6;
/*     */     
/*  51 */     for (var6 = -1; var6 <= 2; var6++) {
/*     */       
/*  53 */       for (int i = 0; i <= 8; i++) {
/*     */         
/*  55 */         placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var4, i, 6 + var6, var6, par3StructureBoundingBox);
/*  56 */         placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var5, i, 6 + var6, 5 - var6, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/*  60 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  61 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 5, 8, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  62 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 1, 0, 8, 1, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  63 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 1, 0, 7, 1, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  64 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 4, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  65 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 5, 0, 4, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  66 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 2, 5, 8, 4, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  67 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 2, 0, 8, 4, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  68 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 1, 0, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
/*  69 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 5, 7, 4, 5, Block.planks.blockID, Block.planks.blockID, false);
/*  70 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 2, 1, 8, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
/*  71 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 0, 7, 4, 0, Block.planks.blockID, Block.planks.blockID, false);
/*  72 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 2, 0, par3StructureBoundingBox);
/*  73 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 5, 2, 0, par3StructureBoundingBox);
/*  74 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 6, 2, 0, par3StructureBoundingBox);
/*  75 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 3, 0, par3StructureBoundingBox);
/*  76 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 5, 3, 0, par3StructureBoundingBox);
/*  77 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 6, 3, 0, par3StructureBoundingBox);
/*  78 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 2, par3StructureBoundingBox);
/*  79 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 3, par3StructureBoundingBox);
/*  80 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 3, 2, par3StructureBoundingBox);
/*  81 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 3, 3, par3StructureBoundingBox);
/*  82 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 2, par3StructureBoundingBox);
/*  83 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 3, par3StructureBoundingBox);
/*  84 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 3, 2, par3StructureBoundingBox);
/*  85 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 3, 3, par3StructureBoundingBox);
/*  86 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 2, 5, par3StructureBoundingBox);
/*  87 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 3, 2, 5, par3StructureBoundingBox);
/*  88 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 5, 2, 5, par3StructureBoundingBox);
/*  89 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 6, 2, 5, par3StructureBoundingBox);
/*  90 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 7, 4, 1, Block.planks.blockID, Block.planks.blockID, false);
/*  91 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 4, 7, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
/*  92 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 3, 4, 7, 3, 4, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
/*  93 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 7, 1, 4, par3StructureBoundingBox);
/*  94 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, getMetadataWithOffset(Block.stairsWoodOak.blockID, 0), 7, 1, 3, par3StructureBoundingBox);
/*  95 */     var6 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
/*  96 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var6, 6, 1, 4, par3StructureBoundingBox);
/*  97 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var6, 5, 1, 4, par3StructureBoundingBox);
/*  98 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var6, 4, 1, 4, par3StructureBoundingBox);
/*  99 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var6, 3, 1, 4, par3StructureBoundingBox);
/* 100 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 6, 1, 3, par3StructureBoundingBox);
/* 101 */     placeBlockAtCurrentPosition(par1World, Block.pressurePlatePlanks.blockID, 0, 6, 2, 3, par3StructureBoundingBox);
/* 102 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 1, 3, par3StructureBoundingBox);
/* 103 */     placeBlockAtCurrentPosition(par1World, Block.pressurePlatePlanks.blockID, 0, 4, 2, 3, par3StructureBoundingBox);
/* 104 */     placeBlockAtCurrentPosition(par1World, Block.workbench.blockID, 0, 7, 1, 1, par3StructureBoundingBox);
/* 105 */     placeBlockAtCurrentPosition(par1World, 0, 0, 1, 1, 0, par3StructureBoundingBox);
/* 106 */     placeBlockAtCurrentPosition(par1World, 0, 0, 1, 2, 0, par3StructureBoundingBox);
/* 107 */     placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 1, 1, 0, getMetadataWithOffset(Block.doorWood.blockID, 1));
/*     */     
/* 109 */     if (getBlockIdAtCurrentPosition(par1World, 1, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 1, -1, -1, par3StructureBoundingBox) != 0)
/*     */     {
/* 111 */       placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 1, 0, -1, par3StructureBoundingBox);
/*     */     }
/*     */     
/* 114 */     for (int var7 = 0; var7 < 6; var7++) {
/*     */       
/* 116 */       for (int var8 = 0; var8 < 9; var8++) {
/*     */         
/* 118 */         clearCurrentPositionBlocksUpwards(par1World, var8, 9, var7, par3StructureBoundingBox);
/* 119 */         fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var8, -1, var7, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     spawnVillagers(par1World, par3StructureBoundingBox, 2, 1, 2, 1);
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getVillagerType(int par1) {
/* 132 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageHouse1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */