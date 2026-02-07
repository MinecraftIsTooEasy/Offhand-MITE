/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentVillageChurch
/*     */   extends ComponentVillage
/*     */ {
/*     */   public ComponentVillageChurch() {}
/*     */   
/*     */   public ComponentVillageChurch(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  12 */     super(par1ComponentVillageStartPiece, par2);
/*  13 */     this.coordBaseMode = par5;
/*  14 */     this.boundingBox = par4StructureBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentVillageChurch func_74919_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
/*  19 */     StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 5, 12, 9, par6);
/*  20 */     return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null) ? new ComponentVillageChurch(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
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
/*  38 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 12 - 1, 0);
/*     */     } 
/*     */     
/*  41 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 3, 3, 7, 0, 0, false);
/*  42 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 3, 9, 3, 0, 0, false);
/*  43 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 3, 0, 8, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  44 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 0, 3, 10, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  45 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 10, 3, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  46 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 1, 4, 10, 3, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  47 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 4, 0, 4, 7, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  48 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 4, 4, 4, 7, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  49 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 8, 3, 4, 8, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  50 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 4, 3, 10, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  51 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 5, 3, 5, 7, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  52 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 9, 0, 4, 9, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  53 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 0, 4, 4, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  54 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 0, 11, 2, par3StructureBoundingBox);
/*  55 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 11, 2, par3StructureBoundingBox);
/*  56 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 2, 11, 0, par3StructureBoundingBox);
/*  57 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 2, 11, 4, par3StructureBoundingBox);
/*  58 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1, 1, 6, par3StructureBoundingBox);
/*  59 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1, 1, 7, par3StructureBoundingBox);
/*  60 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 2, 1, 7, par3StructureBoundingBox);
/*  61 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 3, 1, 6, par3StructureBoundingBox);
/*  62 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 3, 1, 7, par3StructureBoundingBox);
/*  63 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 1, 1, 5, par3StructureBoundingBox);
/*  64 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 2, 1, 6, par3StructureBoundingBox);
/*  65 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 3, 1, 5, par3StructureBoundingBox);
/*  66 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 1), 1, 2, 7, par3StructureBoundingBox);
/*  67 */     placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 0), 3, 2, 7, par3StructureBoundingBox);
/*  68 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 2, par3StructureBoundingBox);
/*  69 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 3, 2, par3StructureBoundingBox);
/*  70 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 2, 2, par3StructureBoundingBox);
/*  71 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 3, 2, par3StructureBoundingBox);
/*  72 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 6, 2, par3StructureBoundingBox);
/*  73 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 7, 2, par3StructureBoundingBox);
/*  74 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 6, 2, par3StructureBoundingBox);
/*  75 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 7, 2, par3StructureBoundingBox);
/*  76 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 6, 0, par3StructureBoundingBox);
/*  77 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 7, 0, par3StructureBoundingBox);
/*  78 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 6, 4, par3StructureBoundingBox);
/*  79 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 7, 4, par3StructureBoundingBox);
/*  80 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 3, 6, par3StructureBoundingBox);
/*  81 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 3, 6, par3StructureBoundingBox);
/*  82 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 3, 8, par3StructureBoundingBox);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 2, 4, 7, 3, par3StructureBoundingBox);
/*  89 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 1, 4, 6, 1, par3StructureBoundingBox);
/*  90 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 3, 4, 6, 2, par3StructureBoundingBox);
/*  91 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 2, 4, 5, 4, par3StructureBoundingBox);
/*     */     
/*  93 */     int var4 = getMetadataWithOffset(Block.ladder.blockID, 4);
/*     */     
/*     */     int var5;
/*  96 */     for (var5 = 1; var5 <= 9; var5++)
/*     */     {
/*  98 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, var4, 3, var5, 3, par3StructureBoundingBox);
/*     */     }
/*     */     
/* 101 */     placeBlockAtCurrentPosition(par1World, 0, 0, 2, 1, 0, par3StructureBoundingBox);
/* 102 */     placeBlockAtCurrentPosition(par1World, 0, 0, 2, 2, 0, par3StructureBoundingBox);
/* 103 */     placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 2, 1, 0, getMetadataWithOffset(Block.doorWood.blockID, 1));
/*     */     
/* 105 */     if (getBlockIdAtCurrentPosition(par1World, 2, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 2, -1, -1, par3StructureBoundingBox) != 0)
/*     */     {
/* 107 */       placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 2, 0, -1, par3StructureBoundingBox);
/*     */     }
/*     */     
/* 110 */     for (var5 = 0; var5 < 9; var5++) {
/*     */       
/* 112 */       for (int var6 = 0; var6 < 5; var6++) {
/*     */         
/* 114 */         clearCurrentPositionBlocksUpwards(par1World, var6, 12, var5, par3StructureBoundingBox);
/* 115 */         fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var6, -1, var5, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     spawnVillagers(par1World, par3StructureBoundingBox, 2, 1, 2, 1);
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getVillagerType(int par1) {
/* 128 */     return 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageChurch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */