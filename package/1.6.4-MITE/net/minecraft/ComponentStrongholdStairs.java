/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentStrongholdStairs
/*     */   extends ComponentStronghold
/*     */ {
/*     */   private boolean field_75024_a;
/*     */   
/*     */   public ComponentStrongholdStairs() {}
/*     */   
/*     */   public ComponentStrongholdStairs(int par1, Random par2Random, int par3, int par4) {
/*  14 */     super(par1);
/*  15 */     this.field_75024_a = true;
/*  16 */     this.coordBaseMode = par2Random.nextInt(4);
/*  17 */     this.field_143013_d = EnumDoor.OPENING;
/*     */     
/*  19 */     switch (this.coordBaseMode) {
/*     */       
/*     */       case 0:
/*     */       case 2:
/*  23 */         this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 5 - 1, 74, par4 + 5 - 1);
/*     */         return;
/*     */     } 
/*     */     
/*  27 */     this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 5 - 1, 74, par4 + 5 - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentStrongholdStairs(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
/*  33 */     super(par1);
/*  34 */     this.field_75024_a = false;
/*  35 */     this.coordBaseMode = par4;
/*  36 */     this.field_143013_d = getRandomDoor(par2Random);
/*  37 */     this.boundingBox = par3StructureBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  42 */     super.func_143012_a(par1NBTTagCompound);
/*  43 */     par1NBTTagCompound.setBoolean("Source", this.field_75024_a);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  48 */     super.func_143011_b(par1NBTTagCompound);
/*  49 */     this.field_75024_a = par1NBTTagCompound.getBoolean("Source");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/*  57 */     if (this.field_75024_a)
/*     */     {
/*  59 */       StructureStrongholdPieces.setComponentType(ComponentStrongholdCrossing.class);
/*     */     }
/*     */     
/*  62 */     getNextComponentNormal((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 1, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentStrongholdStairs getStrongholdStairsComponent(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
/*  70 */     StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, -7, 0, 5, 11, 5, par5);
/*  71 */     return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null) ? new ComponentStrongholdStairs(par6, par1Random, var7, par5) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  80 */     if (isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox))
/*     */     {
/*  82 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  86 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 10, 4, true, par2Random, StructureStrongholdPieces.getStrongholdStones());
/*  87 */     placeDoor(par1World, par2Random, par3StructureBoundingBox, this.field_143013_d, 1, 7, 0);
/*  88 */     placeDoor(par1World, par2Random, par3StructureBoundingBox, EnumDoor.OPENING, 1, 1, 4);
/*  89 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 2, 6, 1, par3StructureBoundingBox);
/*  90 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 1, 5, 1, par3StructureBoundingBox);
/*  91 */     placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 1, 6, 1, par3StructureBoundingBox);
/*  92 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 1, 5, 2, par3StructureBoundingBox);
/*  93 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 1, 4, 3, par3StructureBoundingBox);
/*  94 */     placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 1, 5, 3, par3StructureBoundingBox);
/*  95 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 2, 4, 3, par3StructureBoundingBox);
/*  96 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 3, 3, 3, par3StructureBoundingBox);
/*  97 */     placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 3, 4, 3, par3StructureBoundingBox);
/*  98 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 3, 3, 2, par3StructureBoundingBox);
/*  99 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 3, 2, 1, par3StructureBoundingBox);
/* 100 */     placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 3, 3, 1, par3StructureBoundingBox);
/* 101 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 2, 2, 1, par3StructureBoundingBox);
/* 102 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 1, 1, 1, par3StructureBoundingBox);
/* 103 */     placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 1, 2, 1, par3StructureBoundingBox);
/* 104 */     placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 1, 1, 2, par3StructureBoundingBox);
/* 105 */     placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 1, 1, 3, par3StructureBoundingBox);
/* 106 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentStrongholdStairs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */