/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentVillageHouse3
/*     */   extends ComponentVillage
/*     */ {
/*     */   public ComponentVillageHouse3() {}
/*     */   
/*     */   public ComponentVillageHouse3(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  12 */     super(par1ComponentVillageStartPiece, par2);
/*  13 */     this.coordBaseMode = par5;
/*  14 */     this.boundingBox = par4StructureBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentVillageHouse3 func_74921_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
/*  19 */     StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 9, 7, 12, par6);
/*  20 */     return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null) ? new ComponentVillageHouse3(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
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
/*  43 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 5, 8, 0, 10, Block.planks.blockID, Block.planks.blockID, false);
/*  44 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 7, 0, 4, Block.planks.blockID, Block.planks.blockID, false);
/*  45 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 0, 3, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  46 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 0, 0, 8, 3, 10, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  47 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 7, 2, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  48 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 5, 2, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  49 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 6, 2, 3, 10, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  50 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 0, 10, 7, 3, 10, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  51 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 0, 7, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
/*  52 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 5, 2, 3, 5, Block.planks.blockID, Block.planks.blockID, false);
/*  53 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 1, 8, 4, 1, Block.planks.blockID, Block.planks.blockID, false);
/*  54 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 4, 3, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
/*  55 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 2, 8, 5, 3, Block.planks.blockID, Block.planks.blockID, false);
/*  56 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 0, 4, 2, par3StructureBoundingBox);
/*  57 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 0, 4, 3, par3StructureBoundingBox);
/*  58 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 8, 4, 2, par3StructureBoundingBox);
/*  59 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 8, 4, 3, par3StructureBoundingBox);
/*  60 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 8, 4, 4, par3StructureBoundingBox);
/*  61 */     int var4 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
/*  62 */     int var5 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 2);
/*     */     
/*     */     int var6;
/*     */     
/*  66 */     for (var6 = -1; var6 <= 2; var6++) {
/*     */       
/*  68 */       for (int i = 0; i <= 8; i++) {
/*     */         
/*  70 */         placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var4, i, 4 + var6, var6, par3StructureBoundingBox);
/*     */         
/*  72 */         if ((var6 > -1 || i <= 1) && (var6 > 0 || i <= 3) && (var6 > 1 || i <= 4 || i >= 6))
/*     */         {
/*  74 */           placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var5, i, 4 + var6, 5 - var6, par3StructureBoundingBox);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 4, 5, 3, 4, 10, Block.planks.blockID, Block.planks.blockID, false);
/*  80 */     fillWithBlocks(par1World, par3StructureBoundingBox, 7, 4, 2, 7, 4, 10, Block.planks.blockID, Block.planks.blockID, false);
/*  81 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 5, 4, 4, 5, 10, Block.planks.blockID, Block.planks.blockID, false);
/*  82 */     fillWithBlocks(par1World, par3StructureBoundingBox, 6, 5, 4, 6, 5, 10, Block.planks.blockID, Block.planks.blockID, false);
/*  83 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 6, 3, 5, 6, 10, Block.planks.blockID, Block.planks.blockID, false);
/*  84 */     var6 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 0);
/*     */     
/*     */     int var7;
/*  87 */     for (var7 = 4; var7 >= 1; var7--) {
/*     */       
/*  89 */       placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, var7, 2 + var7, 7 - var7, par3StructureBoundingBox);
/*     */       
/*  91 */       for (int i = 8 - var7; i <= 10; i++)
/*     */       {
/*  93 */         placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var6, var7, 2 + var7, i, par3StructureBoundingBox);
/*     */       }
/*     */     } 
/*     */     
/*  97 */     var7 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 1);
/*  98 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 6, 6, 3, par3StructureBoundingBox);
/*  99 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 7, 5, 4, par3StructureBoundingBox);
/* 100 */     placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var7, 6, 6, 4, par3StructureBoundingBox);
/*     */     
/*     */     int var8;
/* 103 */     for (var8 = 6; var8 <= 8; var8++) {
/*     */       
/* 105 */       for (int var9 = 5; var9 <= 10; var9++)
/*     */       {
/* 107 */         placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var7, var8, 12 - var8, var9, par3StructureBoundingBox);
/*     */       }
/*     */     } 
/*     */     
/* 111 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 0, 2, 1, par3StructureBoundingBox);
/* 112 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 0, 2, 4, par3StructureBoundingBox);
/* 113 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 2, par3StructureBoundingBox);
/* 114 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 3, par3StructureBoundingBox);
/* 115 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 4, 2, 0, par3StructureBoundingBox);
/* 116 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 5, 2, 0, par3StructureBoundingBox);
/* 117 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 6, 2, 0, par3StructureBoundingBox);
/* 118 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 8, 2, 1, par3StructureBoundingBox);
/* 119 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 2, par3StructureBoundingBox);
/* 120 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 3, par3StructureBoundingBox);
/* 121 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 8, 2, 4, par3StructureBoundingBox);
/* 122 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 8, 2, 5, par3StructureBoundingBox);
/* 123 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 8, 2, 6, par3StructureBoundingBox);
/* 124 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 7, par3StructureBoundingBox);
/* 125 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 8, par3StructureBoundingBox);
/* 126 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 8, 2, 9, par3StructureBoundingBox);
/* 127 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 2, 2, 6, par3StructureBoundingBox);
/* 128 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 2, 7, par3StructureBoundingBox);
/* 129 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 2, 8, par3StructureBoundingBox);
/* 130 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 2, 2, 9, par3StructureBoundingBox);
/* 131 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 4, 4, 10, par3StructureBoundingBox);
/* 132 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 5, 4, 10, par3StructureBoundingBox);
/* 133 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 6, 4, 10, par3StructureBoundingBox);
/* 134 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 5, 5, 10, par3StructureBoundingBox);
/* 135 */     placeBlockAtCurrentPosition(par1World, 0, 0, 2, 1, 0, par3StructureBoundingBox);
/* 136 */     placeBlockAtCurrentPosition(par1World, 0, 0, 2, 2, 0, par3StructureBoundingBox);
/*     */     
/* 138 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 2, 3, 1, 4, par3StructureBoundingBox);
/* 139 */     placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 2, 1, 0, getMetadataWithOffset(Block.doorWood.blockID, 1));
/* 140 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, -1, 3, 2, -1, 0, 0, false);
/*     */     
/* 142 */     if (getBlockIdAtCurrentPosition(par1World, 2, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 2, -1, -1, par3StructureBoundingBox) != 0)
/*     */     {
/* 144 */       placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 2, 0, -1, par3StructureBoundingBox);
/*     */     }
/*     */     
/* 147 */     for (var8 = 0; var8 < 5; var8++) {
/*     */       
/* 149 */       for (int var9 = 0; var9 < 9; var9++) {
/*     */         
/* 151 */         clearCurrentPositionBlocksUpwards(par1World, var9, 7, var8, par3StructureBoundingBox);
/* 152 */         fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var9, -1, var8, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 156 */     for (var8 = 5; var8 < 11; var8++) {
/*     */       
/* 158 */       for (int var9 = 2; var9 < 9; var9++) {
/*     */         
/* 160 */         clearCurrentPositionBlocksUpwards(par1World, var9, 7, var8, par3StructureBoundingBox);
/* 161 */         fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var9, -1, var8, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 165 */     spawnVillagers(par1World, par3StructureBoundingBox, 4, 1, 2, 2);
/* 166 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageHouse3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */