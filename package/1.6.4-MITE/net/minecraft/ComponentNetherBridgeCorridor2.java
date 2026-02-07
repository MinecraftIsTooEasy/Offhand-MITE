/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class ComponentNetherBridgeCorridor2
/*    */   extends ComponentNetherBridgePiece
/*    */ {
/*    */   private boolean field_111020_b;
/*    */   
/*    */   public ComponentNetherBridgeCorridor2() {}
/*    */   
/*    */   public ComponentNetherBridgeCorridor2(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
/* 14 */     super(par1);
/* 15 */     this.coordBaseMode = par4;
/* 16 */     this.boundingBox = par3StructureBoundingBox;
/* 17 */     this.field_111020_b = (par2Random.nextInt(3) == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/* 22 */     super.func_143011_b(par1NBTTagCompound);
/* 23 */     this.field_111020_b = par1NBTTagCompound.getBoolean("Chest");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/* 28 */     super.func_143012_a(par1NBTTagCompound);
/* 29 */     par1NBTTagCompound.setBoolean("Chest", this.field_111020_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/* 37 */     getNextComponentZ((ComponentNetherBridgeStartPiece)par1StructureComponent, par2List, par3Random, 0, 1, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ComponentNetherBridgeCorridor2 createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
/* 45 */     StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, 0, 0, 5, 7, 5, par5);
/* 46 */     return (isAboveGround(var7) && StructureComponent.findIntersecting(par0List, var7) == null) ? new ComponentNetherBridgeCorridor2(par6, par1Random, var7, par5) : null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/* 55 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 1, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/* 56 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 4, 5, 4, 0, 0, false);
/* 57 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 5, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/* 58 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 1, 0, 4, 1, Block.netherFence.blockID, Block.netherFence.blockID, false);
/* 59 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 3, 0, 4, 3, Block.netherFence.blockID, Block.netherFence.blockID, false);
/* 60 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 0, 4, 5, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/* 61 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 4, 4, 5, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/* 62 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 3, 4, 1, 4, 4, Block.netherFence.blockID, Block.netherBrick.blockID, false);
/* 63 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 3, 4, 3, 4, 4, Block.netherFence.blockID, Block.netherBrick.blockID, false);
/*    */ 
/*    */ 
/*    */     
/* 67 */     if (this.field_111020_b) {
/*    */       
/* 69 */       int i = getYWithOffset(2);
/* 70 */       int var5 = getXWithOffset(1, 3);
/* 71 */       int var6 = getZWithOffset(1, 3);
/*    */       
/* 73 */       if (par3StructureBoundingBox.isVecInside(var5, i, var6)) {
/*    */         
/* 75 */         this.field_111020_b = false;
/*    */         
/* 77 */         generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 1, 2, 3, Block.chest.blockID, field_111019_a, 2 + par2Random.nextInt(4), (float[])null, (par2Random.nextInt(2) == 0) ? EnumDirection.SOUTH : EnumDirection.EAST);
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 0, 4, 6, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
/*    */     
/* 83 */     for (int var4 = 0; var4 <= 4; var4++) {
/*    */       
/* 85 */       for (int var5 = 0; var5 <= 4; var5++)
/*    */       {
/* 87 */         fillCurrentPositionBlocksDownwards(par1World, Block.netherBrick.blockID, 0, var4, -1, var5, par3StructureBoundingBox);
/*    */       }
/*    */     } 
/*    */     
/* 91 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentNetherBridgeCorridor2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */