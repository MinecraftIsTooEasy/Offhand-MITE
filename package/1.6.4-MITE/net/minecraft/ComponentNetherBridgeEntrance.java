/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentNetherBridgeEntrance
/*     */   extends ComponentNetherBridgePiece
/*     */ {
/*     */   public ComponentNetherBridgeEntrance() {}
/*     */   
/*     */   public ComponentNetherBridgeEntrance(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
/*  12 */     super(par1);
/*  13 */     this.coordBaseMode = par4;
/*  14 */     this.boundingBox = par3StructureBoundingBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/*  22 */     getNextComponentNormal((ComponentNetherBridgeStartPiece)par1StructureComponent, par2List, par3Random, 5, 3, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentNetherBridgeEntrance createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
/*  30 */     StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -5, -3, 0, 13, 14, 13, par5);
/*  31 */     return (isAboveGround(var7) && StructureComponent.findIntersecting(par0List, var7) == null) ? new ComponentNetherBridgeEntrance(par6, par1Random, var7, par5) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  40 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 0, 12, 4, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  41 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 12, 13, 12, 0, 0, false);
/*  42 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 1, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  43 */     fillWithBlocks(par1World, par3StructureBoundingBox, 11, 5, 0, 12, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  44 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 11, 4, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  45 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 5, 11, 10, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  46 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 9, 11, 7, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  47 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 0, 4, 12, 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  48 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 5, 0, 10, 12, 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  49 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 9, 0, 7, 12, 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  50 */     fillWithBlocks(par1World, par3StructureBoundingBox, 2, 11, 2, 10, 12, 10, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  51 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 8, 0, 7, 8, 0, Block.netherFence.blockID, Block.netherFence.blockID, false);
/*     */     
/*     */     int var4;
/*  54 */     for (var4 = 1; var4 <= 11; var4 += 2) {
/*     */       
/*  56 */       fillWithBlocks(par1World, par3StructureBoundingBox, var4, 10, 0, var4, 11, 0, Block.netherFence.blockID, Block.netherFence.blockID, false);
/*  57 */       fillWithBlocks(par1World, par3StructureBoundingBox, var4, 10, 12, var4, 11, 12, Block.netherFence.blockID, Block.netherFence.blockID, false);
/*  58 */       fillWithBlocks(par1World, par3StructureBoundingBox, 0, 10, var4, 0, 11, var4, Block.netherFence.blockID, Block.netherFence.blockID, false);
/*  59 */       fillWithBlocks(par1World, par3StructureBoundingBox, 12, 10, var4, 12, 11, var4, Block.netherFence.blockID, Block.netherFence.blockID, false);
/*  60 */       placeBlockAtCurrentPosition(par1World, Block.netherBrick.blockID, 0, var4, 13, 0, par3StructureBoundingBox);
/*  61 */       placeBlockAtCurrentPosition(par1World, Block.netherBrick.blockID, 0, var4, 13, 12, par3StructureBoundingBox);
/*  62 */       placeBlockAtCurrentPosition(par1World, Block.netherBrick.blockID, 0, 0, 13, var4, par3StructureBoundingBox);
/*  63 */       placeBlockAtCurrentPosition(par1World, Block.netherBrick.blockID, 0, 12, 13, var4, par3StructureBoundingBox);
/*  64 */       placeBlockAtCurrentPosition(par1World, Block.netherFence.blockID, 0, var4 + 1, 13, 0, par3StructureBoundingBox);
/*  65 */       placeBlockAtCurrentPosition(par1World, Block.netherFence.blockID, 0, var4 + 1, 13, 12, par3StructureBoundingBox);
/*  66 */       placeBlockAtCurrentPosition(par1World, Block.netherFence.blockID, 0, 0, 13, var4 + 1, par3StructureBoundingBox);
/*  67 */       placeBlockAtCurrentPosition(par1World, Block.netherFence.blockID, 0, 12, 13, var4 + 1, par3StructureBoundingBox);
/*     */     } 
/*     */     
/*  70 */     placeBlockAtCurrentPosition(par1World, Block.netherFence.blockID, 0, 0, 13, 0, par3StructureBoundingBox);
/*  71 */     placeBlockAtCurrentPosition(par1World, Block.netherFence.blockID, 0, 0, 13, 12, par3StructureBoundingBox);
/*  72 */     placeBlockAtCurrentPosition(par1World, Block.netherFence.blockID, 0, 0, 13, 0, par3StructureBoundingBox);
/*  73 */     placeBlockAtCurrentPosition(par1World, Block.netherFence.blockID, 0, 12, 13, 0, par3StructureBoundingBox);
/*     */     
/*  75 */     for (var4 = 3; var4 <= 9; var4 += 2) {
/*     */       
/*  77 */       fillWithBlocks(par1World, par3StructureBoundingBox, 1, 7, var4, 1, 8, var4, Block.netherFence.blockID, Block.netherFence.blockID, false);
/*  78 */       fillWithBlocks(par1World, par3StructureBoundingBox, 11, 7, var4, 11, 8, var4, Block.netherFence.blockID, Block.netherFence.blockID, false);
/*     */     } 
/*     */     
/*  81 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 0, 8, 2, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  82 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 4, 12, 2, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  83 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 0, 8, 1, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  84 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 9, 8, 1, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  85 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 4, 3, 1, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*  86 */     fillWithBlocks(par1World, par3StructureBoundingBox, 9, 0, 4, 12, 1, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*     */ 
/*     */     
/*  89 */     for (var4 = 4; var4 <= 8; var4++) {
/*     */       
/*  91 */       for (int i = 0; i <= 2; i++) {
/*     */         
/*  93 */         fillCurrentPositionBlocksDownwards(par1World, Block.netherBrick.blockID, 0, var4, -1, i, par3StructureBoundingBox);
/*  94 */         fillCurrentPositionBlocksDownwards(par1World, Block.netherBrick.blockID, 0, var4, -1, 12 - i, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     for (var4 = 0; var4 <= 2; var4++) {
/*     */       
/* 100 */       for (int i = 4; i <= 8; i++) {
/*     */         
/* 102 */         fillCurrentPositionBlocksDownwards(par1World, Block.netherBrick.blockID, 0, var4, -1, i, par3StructureBoundingBox);
/* 103 */         fillCurrentPositionBlocksDownwards(par1World, Block.netherBrick.blockID, 0, 12 - var4, -1, i, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 5, 5, 7, 5, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/* 108 */     fillWithBlocks(par1World, par3StructureBoundingBox, 6, 1, 6, 6, 4, 6, 0, 0, false);
/* 109 */     placeBlockAtCurrentPosition(par1World, Block.netherBrick.blockID, 0, 6, 0, 6, par3StructureBoundingBox);
/* 110 */     placeBlockAtCurrentPosition(par1World, Block.lavaMoving.blockID, 0, 6, 5, 6, par3StructureBoundingBox);
/* 111 */     var4 = getXWithOffset(6, 6);
/* 112 */     int var5 = getYWithOffset(5);
/* 113 */     int var6 = getZWithOffset(6, 6);
/*     */     
/* 115 */     if (par3StructureBoundingBox.isVecInside(var4, var5, var6)) {
/*     */       
/* 117 */       par1World.scheduledUpdatesAreImmediate = true;
/* 118 */       Block.blocksList[Block.lavaMoving.blockID].updateTick(par1World, var4, var5, var6, par2Random);
/* 119 */       par1World.scheduledUpdatesAreImmediate = false;
/*     */     } 
/*     */     
/* 122 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentNetherBridgeEntrance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */