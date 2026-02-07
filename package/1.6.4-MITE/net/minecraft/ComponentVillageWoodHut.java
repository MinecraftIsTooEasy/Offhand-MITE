/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentVillageWoodHut
/*     */   extends ComponentVillage
/*     */ {
/*     */   private boolean isTallHouse;
/*     */   private int tablePosition;
/*     */   
/*     */   public ComponentVillageWoodHut() {}
/*     */   
/*     */   public ComponentVillageWoodHut(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  15 */     super(par1ComponentVillageStartPiece, par2);
/*  16 */     this.coordBaseMode = par5;
/*  17 */     this.boundingBox = par4StructureBoundingBox;
/*  18 */     this.isTallHouse = par3Random.nextBoolean();
/*  19 */     this.tablePosition = par3Random.nextInt(3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  24 */     super.func_143012_a(par1NBTTagCompound);
/*  25 */     par1NBTTagCompound.setInteger("T", this.tablePosition);
/*  26 */     par1NBTTagCompound.setBoolean("C", this.isTallHouse);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  31 */     super.func_143011_b(par1NBTTagCompound);
/*  32 */     this.tablePosition = par1NBTTagCompound.getInteger("T");
/*  33 */     this.isTallHouse = par1NBTTagCompound.getBoolean("C");
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentVillageWoodHut func_74908_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
/*  38 */     StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 4, 6, 5, par6);
/*  39 */     return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null) ? new ComponentVillageWoodHut(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  48 */     if (this.field_143015_k < 0) {
/*     */       
/*  50 */       this.field_143015_k = getAverageGroundLevel(par1World, par3StructureBoundingBox);
/*     */       
/*  52 */       if (this.field_143015_k < 0)
/*     */       {
/*  54 */         return true;
/*     */       }
/*     */       
/*  57 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
/*     */     } 
/*     */     
/*  60 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 3, 5, 4, 0, 0, false);
/*  61 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 3, 0, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
/*  62 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 2, 0, 3, Block.dirt.blockID, Block.dirt.blockID, false);
/*     */     
/*  64 */     if (this.isTallHouse) {
/*     */       
/*  66 */       fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 2, 4, 3, Block.wood.blockID, Block.wood.blockID, false);
/*     */     }
/*     */     else {
/*     */       
/*  70 */       fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 2, 5, 3, Block.wood.blockID, Block.wood.blockID, false);
/*     */     } 
/*     */     
/*  73 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 1, 4, 0, par3StructureBoundingBox);
/*  74 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 2, 4, 0, par3StructureBoundingBox);
/*  75 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 1, 4, 4, par3StructureBoundingBox);
/*  76 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 2, 4, 4, par3StructureBoundingBox);
/*  77 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 0, 4, 1, par3StructureBoundingBox);
/*  78 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 0, 4, 2, par3StructureBoundingBox);
/*  79 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 0, 4, 3, par3StructureBoundingBox);
/*  80 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 3, 4, 1, par3StructureBoundingBox);
/*  81 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 3, 4, 2, par3StructureBoundingBox);
/*  82 */     placeBlockAtCurrentPosition(par1World, Block.wood.blockID, 0, 3, 4, 3, par3StructureBoundingBox);
/*  83 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 3, 0, Block.wood.blockID, Block.wood.blockID, false);
/*  84 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 0, 3, 3, 0, Block.wood.blockID, Block.wood.blockID, false);
/*  85 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 4, 0, 3, 4, Block.wood.blockID, Block.wood.blockID, false);
/*  86 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 4, 3, 3, 4, Block.wood.blockID, Block.wood.blockID, false);
/*  87 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
/*  88 */     fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 1, 3, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
/*  89 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 0, 2, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
/*  90 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 4, 2, 3, 4, Block.planks.blockID, Block.planks.blockID, false);
/*  91 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 2, par3StructureBoundingBox);
/*  92 */     placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 3, 2, 2, par3StructureBoundingBox);
/*     */     
/*  94 */     if (this.tablePosition > 0) {
/*     */       
/*  96 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, this.tablePosition, 1, 3, par3StructureBoundingBox);
/*  97 */       placeBlockAtCurrentPosition(par1World, Block.pressurePlatePlanks.blockID, 0, this.tablePosition, 2, 3, par3StructureBoundingBox);
/*     */     } 
/*     */     
/* 100 */     placeBlockAtCurrentPosition(par1World, 0, 0, 1, 1, 0, par3StructureBoundingBox);
/* 101 */     placeBlockAtCurrentPosition(par1World, 0, 0, 1, 2, 0, par3StructureBoundingBox);
/* 102 */     placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 1, 1, 0, getMetadataWithOffset(Block.doorWood.blockID, 1));
/*     */     
/* 104 */     if (getBlockIdAtCurrentPosition(par1World, 1, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 1, -1, -1, par3StructureBoundingBox) != 0)
/*     */     {
/* 106 */       placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 1, 0, -1, par3StructureBoundingBox);
/*     */     }
/*     */     
/* 109 */     for (int var4 = 0; var4 < 5; var4++) {
/*     */       
/* 111 */       for (int var5 = 0; var5 < 4; var5++) {
/*     */         
/* 113 */         clearCurrentPositionBlocksUpwards(par1World, var5, 6, var4, par3StructureBoundingBox);
/* 114 */         fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var5, -1, var4, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     spawnVillagers(par1World, par3StructureBoundingBox, 1, 1, 2, 1);
/* 119 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageWoodHut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */