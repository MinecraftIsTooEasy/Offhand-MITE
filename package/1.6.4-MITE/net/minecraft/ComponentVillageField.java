/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComponentVillageField
/*     */   extends ComponentVillage
/*     */ {
/*     */   private int cropTypeA;
/*     */   private int cropTypeB;
/*     */   private int cropTypeC;
/*     */   private int cropTypeD;
/*     */   
/*     */   public ComponentVillageField() {}
/*     */   
/*     */   public ComponentVillageField(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  24 */     super(par1ComponentVillageStartPiece, par2);
/*  25 */     this.coordBaseMode = par5;
/*  26 */     this.boundingBox = par4StructureBoundingBox;
/*  27 */     this.cropTypeA = getRandomCrop(par3Random);
/*  28 */     this.cropTypeB = getRandomCrop(par3Random);
/*  29 */     this.cropTypeC = getRandomCrop(par3Random);
/*  30 */     this.cropTypeD = getRandomCrop(par3Random);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  35 */     super.func_143012_a(par1NBTTagCompound);
/*  36 */     par1NBTTagCompound.setInteger("CA", this.cropTypeA);
/*  37 */     par1NBTTagCompound.setInteger("CB", this.cropTypeB);
/*  38 */     par1NBTTagCompound.setInteger("CC", this.cropTypeC);
/*  39 */     par1NBTTagCompound.setInteger("CD", this.cropTypeD);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  44 */     super.func_143011_b(par1NBTTagCompound);
/*  45 */     this.cropTypeA = par1NBTTagCompound.getInteger("CA");
/*  46 */     this.cropTypeB = par1NBTTagCompound.getInteger("CB");
/*  47 */     this.cropTypeC = par1NBTTagCompound.getInteger("CC");
/*  48 */     this.cropTypeD = par1NBTTagCompound.getInteger("CD");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getRandomCrop(Random par1Random) {
/*  57 */     switch (par1Random.nextInt(6)) {
/*     */ 
/*     */       
/*     */       case 0:
/*  61 */         return Block.carrotDead.blockID;
/*     */ 
/*     */       
/*     */       case 1:
/*  65 */         return Block.potatoDead.blockID;
/*     */ 
/*     */       
/*     */       case 2:
/*  69 */         return Block.onionsDead.blockID;
/*     */     } 
/*     */ 
/*     */     
/*  73 */     return Block.cropsDead.blockID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentVillageField func_74900_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
/*  79 */     StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 13, 4, 9, par6);
/*  80 */     return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null) ? new ComponentVillageField(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  89 */     if (this.field_143015_k < 0) {
/*     */       
/*  91 */       this.field_143015_k = getAverageGroundLevel(par1World, par3StructureBoundingBox);
/*     */       
/*  93 */       if (this.field_143015_k < 0)
/*     */       {
/*  95 */         return true;
/*     */       }
/*     */       
/*  98 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
/*     */     } 
/*     */     
/* 101 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 12, 4, 8, 0, 0, false);
/* 102 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 2, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
/* 103 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 1, 5, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
/* 104 */     fillWithBlocks(par1World, par3StructureBoundingBox, 7, 0, 1, 8, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
/* 105 */     fillWithBlocks(par1World, par3StructureBoundingBox, 10, 0, 1, 11, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
/* 106 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 0, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
/* 107 */     fillWithBlocks(par1World, par3StructureBoundingBox, 6, 0, 0, 6, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
/* 108 */     fillWithBlocks(par1World, par3StructureBoundingBox, 12, 0, 0, 12, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
/* 109 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 11, 0, 0, Block.wood.blockID, Block.wood.blockID, false);
/* 110 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 8, 11, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
/*     */ 
/*     */     
/*     */     int var4;
/*     */ 
/*     */     
/* 116 */     for (var4 = 1; var4 <= 7; var4++) {
/*     */ 
/*     */ 
/*     */       
/* 120 */       placeBlockAtCurrentPosition(par1World, this.cropTypeA, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 1, 1, var4, par3StructureBoundingBox);
/* 121 */       placeBlockAtCurrentPosition(par1World, this.cropTypeA, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 2, 1, var4, par3StructureBoundingBox);
/* 122 */       placeBlockAtCurrentPosition(par1World, this.cropTypeB, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 4, 1, var4, par3StructureBoundingBox);
/* 123 */       placeBlockAtCurrentPosition(par1World, this.cropTypeB, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 5, 1, var4, par3StructureBoundingBox);
/* 124 */       placeBlockAtCurrentPosition(par1World, this.cropTypeC, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 7, 1, var4, par3StructureBoundingBox);
/* 125 */       placeBlockAtCurrentPosition(par1World, this.cropTypeC, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 8, 1, var4, par3StructureBoundingBox);
/* 126 */       placeBlockAtCurrentPosition(par1World, this.cropTypeD, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 10, 1, var4, par3StructureBoundingBox);
/* 127 */       placeBlockAtCurrentPosition(par1World, this.cropTypeD, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 11, 1, var4, par3StructureBoundingBox);
/*     */     } 
/*     */     
/* 130 */     for (var4 = 0; var4 < 9; var4++) {
/*     */       
/* 132 */       for (int var5 = 0; var5 < 13; var5++) {
/*     */         
/* 134 */         clearCurrentPositionBlocksUpwards(par1World, var5, 4, var4, par3StructureBoundingBox);
/* 135 */         fillCurrentPositionBlocksDownwards(par1World, Block.dirt.blockID, 0, var5, -1, var4, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */