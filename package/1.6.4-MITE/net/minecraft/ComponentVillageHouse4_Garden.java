/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentVillageHouse4_Garden
/*     */   extends ComponentVillage
/*     */ {
/*     */   private boolean isRoofAccessible;
/*     */   
/*     */   public ComponentVillageHouse4_Garden() {}
/*     */   
/*     */   public ComponentVillageHouse4_Garden(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  14 */     super(par1ComponentVillageStartPiece, par2);
/*  15 */     this.coordBaseMode = par5;
/*  16 */     this.boundingBox = par4StructureBoundingBox;
/*  17 */     this.isRoofAccessible = par3Random.nextBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  22 */     super.func_143012_a(par1NBTTagCompound);
/*  23 */     par1NBTTagCompound.setBoolean("Terrace", this.isRoofAccessible);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  28 */     super.func_143011_b(par1NBTTagCompound);
/*  29 */     this.isRoofAccessible = par1NBTTagCompound.getBoolean("Terrace");
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentVillageHouse4_Garden func_74912_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
/*  34 */     StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 5, 6, 5, par6);
/*  35 */     return (StructureComponent.findIntersecting(par1List, var8) != null) ? null : new ComponentVillageHouse4_Garden(par0ComponentVillageStartPiece, par7, par2Random, var8, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  44 */     if (this.field_143015_k < 0) {
/*     */       
/*  46 */       this.field_143015_k = getAverageGroundLevel(par1World, par3StructureBoundingBox);
/*     */       
/*  48 */       if (this.field_143015_k < 0)
/*     */       {
/*  50 */         return true;
/*     */       }
/*     */       
/*  53 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
/*     */     } 
/*     */     
/*  56 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 0, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  57 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 0, 4, 4, 4, Block.wood.blockID, Block.wood.blockID, false);
/*  58 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 3, 4, 3, Block.planks.blockID, Block.planks.blockID, false);
/*  59 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 0, 1, 0, par3StructureBoundingBox);
/*  60 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 0, 2, 0, par3StructureBoundingBox);
/*  61 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 0, 3, 0, par3StructureBoundingBox);
/*  62 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 1, 0, par3StructureBoundingBox);
/*  63 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 2, 0, par3StructureBoundingBox);
/*  64 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 3, 0, par3StructureBoundingBox);
/*  65 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 0, 1, 4, par3StructureBoundingBox);
/*  66 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 0, 2, 4, par3StructureBoundingBox);
/*  67 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 0, 3, 4, par3StructureBoundingBox);
/*  68 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 1, 4, par3StructureBoundingBox);
/*  69 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 2, 4, par3StructureBoundingBox);
/*  70 */     placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 3, 4, par3StructureBoundingBox);
/*  71 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
/*  72 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 1, 4, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
/*  73 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 4, 3, 3, 4, Block.planks.blockID, Block.planks.blockID, false);
/*  74 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 2, par3StructureBoundingBox);
/*  75 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 2, 4, par3StructureBoundingBox);
/*  76 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 2, 2, par3StructureBoundingBox);
/*  77 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 1, 1, 0, par3StructureBoundingBox);
/*  78 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 1, 2, 0, par3StructureBoundingBox);
/*  79 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 1, 3, 0, par3StructureBoundingBox);
/*  80 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 2, 3, 0, par3StructureBoundingBox);
/*  81 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 3, 3, 0, par3StructureBoundingBox);
/*  82 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 3, 2, 0, par3StructureBoundingBox);
/*  83 */     placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 3, 1, 0, par3StructureBoundingBox);
/*     */     
/*  85 */     if (getBlockIdAtCurrentPosition(par1World, 2, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 2, -1, -1, par3StructureBoundingBox) != 0)
/*     */     {
/*  87 */       placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 2, 0, -1, par3StructureBoundingBox);
/*     */     }
/*     */     
/*  90 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 3, 3, 3, 0, 0, false);
/*     */     
/*  92 */     if (this.isRoofAccessible) {
/*     */       
/*  94 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 0, 5, 0, par3StructureBoundingBox);
/*  95 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 5, 0, par3StructureBoundingBox);
/*  96 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 2, 5, 0, par3StructureBoundingBox);
/*  97 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 3, 5, 0, par3StructureBoundingBox);
/*  98 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 5, 0, par3StructureBoundingBox);
/*  99 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 0, 5, 4, par3StructureBoundingBox);
/* 100 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 5, 4, par3StructureBoundingBox);
/* 101 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 2, 5, 4, par3StructureBoundingBox);
/* 102 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 3, 5, 4, par3StructureBoundingBox);
/* 103 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 5, 4, par3StructureBoundingBox);
/* 104 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 5, 1, par3StructureBoundingBox);
/* 105 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 5, 2, par3StructureBoundingBox);
/* 106 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 5, 3, par3StructureBoundingBox);
/* 107 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 0, 5, 1, par3StructureBoundingBox);
/* 108 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 0, 5, 2, par3StructureBoundingBox);
/* 109 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 0, 5, 3, par3StructureBoundingBox);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (this.isRoofAccessible) {
/*     */       
/* 116 */       int i = getMetadataWithOffset(Block.ladder.blockID, 3);
/* 117 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, i, 3, 1, 3, par3StructureBoundingBox);
/* 118 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, i, 3, 2, 3, par3StructureBoundingBox);
/* 119 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, i, 3, 3, 3, par3StructureBoundingBox);
/* 120 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, i, 3, 4, 3, par3StructureBoundingBox);
/*     */     } 
/*     */ 
/*     */     
/* 124 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 2, 3, 1, 4, par3StructureBoundingBox);
/*     */     
/* 126 */     for (int var4 = 0; var4 < 5; var4++) {
/*     */       
/* 128 */       for (int var5 = 0; var5 < 5; var5++) {
/*     */         
/* 130 */         clearCurrentPositionBlocksUpwards(par1World, var5, 6, var4, par3StructureBoundingBox);
/* 131 */         fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var5, -1, var4, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     spawnVillagers(par1World, par3StructureBoundingBox, 1, 1, 2, 1);
/* 136 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageHouse4_Garden.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */