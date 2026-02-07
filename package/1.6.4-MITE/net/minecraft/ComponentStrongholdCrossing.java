/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentStrongholdCrossing
/*     */   extends ComponentStronghold
/*     */ {
/*     */   private boolean field_74996_b;
/*     */   private boolean field_74997_c;
/*     */   private boolean field_74995_d;
/*     */   private boolean field_74999_h;
/*     */   
/*     */   public ComponentStrongholdCrossing() {}
/*     */   
/*     */   public ComponentStrongholdCrossing(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
/*  17 */     super(par1);
/*  18 */     this.coordBaseMode = par4;
/*  19 */     this.field_143013_d = getRandomDoor(par2Random);
/*  20 */     this.boundingBox = par3StructureBoundingBox;
/*  21 */     this.field_74996_b = par2Random.nextBoolean();
/*  22 */     this.field_74997_c = par2Random.nextBoolean();
/*  23 */     this.field_74995_d = par2Random.nextBoolean();
/*  24 */     this.field_74999_h = (par2Random.nextInt(3) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  29 */     super.func_143012_a(par1NBTTagCompound);
/*  30 */     par1NBTTagCompound.setBoolean("leftLow", this.field_74996_b);
/*  31 */     par1NBTTagCompound.setBoolean("leftHigh", this.field_74997_c);
/*  32 */     par1NBTTagCompound.setBoolean("rightLow", this.field_74995_d);
/*  33 */     par1NBTTagCompound.setBoolean("rightHigh", this.field_74999_h);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  38 */     super.func_143011_b(par1NBTTagCompound);
/*  39 */     this.field_74996_b = par1NBTTagCompound.getBoolean("leftLow");
/*  40 */     this.field_74997_c = par1NBTTagCompound.getBoolean("leftHigh");
/*  41 */     this.field_74995_d = par1NBTTagCompound.getBoolean("rightLow");
/*  42 */     this.field_74999_h = par1NBTTagCompound.getBoolean("rightHigh");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/*  50 */     int var4 = 3;
/*  51 */     int var5 = 5;
/*     */     
/*  53 */     if (this.coordBaseMode == 1 || this.coordBaseMode == 2) {
/*     */       
/*  55 */       var4 = 8 - var4;
/*  56 */       var5 = 8 - var5;
/*     */     } 
/*     */     
/*  59 */     getNextComponentNormal((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 5, 1);
/*     */     
/*  61 */     if (this.field_74996_b)
/*     */     {
/*  63 */       getNextComponentX((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, var4, 1);
/*     */     }
/*     */     
/*  66 */     if (this.field_74997_c)
/*     */     {
/*  68 */       getNextComponentX((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, var5, 7);
/*     */     }
/*     */     
/*  71 */     if (this.field_74995_d)
/*     */     {
/*  73 */       getNextComponentZ((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, var4, 1);
/*     */     }
/*     */     
/*  76 */     if (this.field_74999_h)
/*     */     {
/*  78 */       getNextComponentZ((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, var5, 7);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentStrongholdCrossing findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
/*  84 */     StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -4, -3, 0, 10, 9, 11, par5);
/*  85 */     return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null) ? new ComponentStrongholdCrossing(par6, par1Random, var7, par5) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  94 */     if (isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox))
/*     */     {
/*  96 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 100 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 9, 8, 10, true, par2Random, StructureStrongholdPieces.getStrongholdStones());
/* 101 */     placeDoor(par1World, par2Random, par3StructureBoundingBox, this.field_143013_d, 4, 3, 0);
/*     */     
/* 103 */     if (this.field_74996_b)
/*     */     {
/* 105 */       fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 1, 0, 5, 3, 0, 0, false);
/*     */     }
/*     */     
/* 108 */     if (this.field_74995_d)
/*     */     {
/* 110 */       fillWithBlocks(par1World, par3StructureBoundingBox, 9, 3, 1, 9, 5, 3, 0, 0, false);
/*     */     }
/*     */     
/* 113 */     if (this.field_74997_c)
/*     */     {
/* 115 */       fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 7, 0, 7, 9, 0, 0, false);
/*     */     }
/*     */     
/* 118 */     if (this.field_74999_h)
/*     */     {
/* 120 */       fillWithBlocks(par1World, par3StructureBoundingBox, 9, 5, 7, 9, 7, 9, 0, 0, false);
/*     */     }
/*     */     
/* 123 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 10, 7, 3, 10, 0, 0, false);
/* 124 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 2, 1, 8, 2, 6, false, par2Random, StructureStrongholdPieces.getStrongholdStones());
/* 125 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 1, 5, 4, 4, 9, false, par2Random, StructureStrongholdPieces.getStrongholdStones());
/* 126 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 8, 1, 5, 8, 4, 9, false, par2Random, StructureStrongholdPieces.getStrongholdStones());
/* 127 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 4, 7, 3, 4, 9, false, par2Random, StructureStrongholdPieces.getStrongholdStones());
/* 128 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 3, 5, 3, 3, 6, false, par2Random, StructureStrongholdPieces.getStrongholdStones());
/* 129 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 3, 4, 3, 3, 4, Block.stoneSingleSlab.blockID, Block.stoneSingleSlab.blockID, false);
/* 130 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 6, 3, 4, 6, Block.stoneSingleSlab.blockID, Block.stoneSingleSlab.blockID, false);
/* 131 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 5, 1, 7, 7, 1, 8, false, par2Random, StructureStrongholdPieces.getStrongholdStones());
/* 132 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 9, 7, 1, 9, Block.stoneSingleSlab.blockID, Block.stoneSingleSlab.blockID, false);
/* 133 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 2, 7, 7, 2, 7, Block.stoneSingleSlab.blockID, Block.stoneSingleSlab.blockID, false);
/* 134 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 5, 7, 4, 5, 9, Block.stoneSingleSlab.blockID, Block.stoneSingleSlab.blockID, false);
/* 135 */     fillWithBlocks(par1World, par3StructureBoundingBox, 8, 5, 7, 8, 5, 9, Block.stoneSingleSlab.blockID, Block.stoneSingleSlab.blockID, false);
/* 136 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 5, 7, 7, 5, 9, Block.stoneDoubleSlab.blockID, Block.stoneDoubleSlab.blockID, false);
/*     */     
/* 138 */     placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 6, 5, 6, 3, par3StructureBoundingBox);
/*     */     
/* 140 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentStrongholdCrossing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */