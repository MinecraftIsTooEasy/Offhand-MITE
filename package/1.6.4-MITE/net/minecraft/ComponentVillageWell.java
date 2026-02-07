/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class ComponentVillageWell
/*    */   extends ComponentVillage
/*    */ {
/*    */   public ComponentVillageWell() {}
/*    */   
/*    */   public ComponentVillageWell(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, int par4, int par5) {
/* 12 */     super(par1ComponentVillageStartPiece, par2);
/* 13 */     this.coordBaseMode = par3Random.nextInt(4);
/*    */     
/* 15 */     switch (this.coordBaseMode) {
/*    */       
/*    */       case 0:
/*    */       case 2:
/* 19 */         this.boundingBox = new StructureBoundingBox(par4, 64, par5, par4 + 6 - 1, 78, par5 + 6 - 1);
/*    */         return;
/*    */     } 
/*    */     
/* 23 */     this.boundingBox = new StructureBoundingBox(par4, 64, par5, par4 + 6 - 1, 78, par5 + 6 - 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/* 32 */     StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 1, getComponentType());
/* 33 */     StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 3, getComponentType());
/* 34 */     StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, 2, getComponentType());
/* 35 */     StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, 0, getComponentType());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/* 44 */     if (this.field_143015_k < 0) {
/*    */       
/* 46 */       this.field_143015_k = getAverageGroundLevel(par1World, par3StructureBoundingBox);
/*    */       
/* 48 */       if (this.field_143015_k < 0)
/*    */       {
/* 50 */         return true;
/*    */       }
/*    */       
/* 53 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 3, 0);
/*    */     } 
/*    */     
/* 56 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 4, 12, 4, Block.cobblestone.blockID, Block.waterMoving.blockID, false);
/* 57 */     placeBlockAtCurrentPosition(par1World, 0, 0, 2, 12, 2, par3StructureBoundingBox);
/* 58 */     placeBlockAtCurrentPosition(par1World, 0, 0, 3, 12, 2, par3StructureBoundingBox);
/* 59 */     placeBlockAtCurrentPosition(par1World, 0, 0, 2, 12, 3, par3StructureBoundingBox);
/* 60 */     placeBlockAtCurrentPosition(par1World, 0, 0, 3, 12, 3, par3StructureBoundingBox);
/* 61 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 13, 1, par3StructureBoundingBox);
/* 62 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 14, 1, par3StructureBoundingBox);
/* 63 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 13, 1, par3StructureBoundingBox);
/* 64 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 14, 1, par3StructureBoundingBox);
/* 65 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 13, 4, par3StructureBoundingBox);
/* 66 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 14, 4, par3StructureBoundingBox);
/* 67 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 13, 4, par3StructureBoundingBox);
/* 68 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 14, 4, par3StructureBoundingBox);
/* 69 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 15, 1, 4, 15, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*    */     
/* 71 */     for (int var4 = 0; var4 <= 5; var4++) {
/*    */       
/* 73 */       for (int var5 = 0; var5 <= 5; var5++) {
/*    */         
/* 75 */         if (var5 == 0 || var5 == 5 || var4 == 0 || var4 == 5) {
/*    */           
/* 77 */           placeBlockAtCurrentPosition(par1World, Block.gravel.blockID, 0, var5, 11, var4, par3StructureBoundingBox);
/* 78 */           clearCurrentPositionBlocksUpwards(par1World, var5, 12, var4, par3StructureBoundingBox);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 83 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageWell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */