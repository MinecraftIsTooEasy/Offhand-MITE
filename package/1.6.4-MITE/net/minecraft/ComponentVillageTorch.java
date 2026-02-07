/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class ComponentVillageTorch
/*    */   extends ComponentVillage
/*    */ {
/*    */   public ComponentVillageTorch() {}
/*    */   
/*    */   public ComponentVillageTorch(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/* 12 */     super(par1ComponentVillageStartPiece, par2);
/* 13 */     this.coordBaseMode = par5;
/* 14 */     this.boundingBox = par4StructureBoundingBox;
/*    */   }
/*    */ 
/*    */   
/*    */   public static StructureBoundingBox func_74904_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6) {
/* 19 */     StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 3, 4, 2, par6);
/* 20 */     return (StructureComponent.findIntersecting(par1List, var7) != null) ? null : var7;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/* 29 */     if (this.field_143015_k < 0) {
/*    */       
/* 31 */       this.field_143015_k = getAverageGroundLevel(par1World, par3StructureBoundingBox);
/*    */       
/* 33 */       if (this.field_143015_k < 0)
/*    */       {
/* 35 */         return true;
/*    */       }
/*    */       
/* 38 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
/*    */     } 
/*    */     
/* 41 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 2, 3, 1, 0, 0, false);
/* 42 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 0, 0, par3StructureBoundingBox);
/* 43 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 1, 0, par3StructureBoundingBox);
/* 44 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 2, 0, par3StructureBoundingBox);
/* 45 */     placeBlockAtCurrentPosition(par1World, Block.cloth.blockID, 15, 1, 3, 0, par3StructureBoundingBox);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 0, 3, 0, 2, par3StructureBoundingBox);
/* 52 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 1, 3, 1, 4, par3StructureBoundingBox);
/* 53 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 2, 3, 0, 1, par3StructureBoundingBox);
/* 54 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 1, 3, -1, 3, par3StructureBoundingBox);
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageTorch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */