/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComponentVillageField2
/*     */   extends ComponentVillage
/*     */ {
/*     */   private int cropTypeA;
/*     */   private int cropTypeB;
/*     */   
/*     */   public ComponentVillageField2() {}
/*     */   
/*     */   public ComponentVillageField2(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  18 */     super(par1ComponentVillageStartPiece, par2);
/*  19 */     this.coordBaseMode = par5;
/*  20 */     this.boundingBox = par4StructureBoundingBox;
/*  21 */     this.cropTypeA = pickRandomCrop(par3Random);
/*  22 */     this.cropTypeB = pickRandomCrop(par3Random);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  27 */     super.func_143012_a(par1NBTTagCompound);
/*  28 */     par1NBTTagCompound.setInteger("CA", this.cropTypeA);
/*  29 */     par1NBTTagCompound.setInteger("CB", this.cropTypeB);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  34 */     super.func_143011_b(par1NBTTagCompound);
/*  35 */     this.cropTypeA = par1NBTTagCompound.getInteger("CA");
/*  36 */     this.cropTypeB = par1NBTTagCompound.getInteger("CB");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int pickRandomCrop(Random par1Random) {
/*  45 */     switch (par1Random.nextInt(6)) {
/*     */ 
/*     */       
/*     */       case 0:
/*  49 */         return Block.carrotDead.blockID;
/*     */ 
/*     */       
/*     */       case 1:
/*  53 */         return Block.potatoDead.blockID;
/*     */ 
/*     */       
/*     */       case 2:
/*  57 */         return Block.onionsDead.blockID;
/*     */     } 
/*     */ 
/*     */     
/*  61 */     return Block.cropsDead.blockID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentVillageField2 func_74902_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
/*  67 */     StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 7, 4, 9, par6);
/*  68 */     return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null) ? new ComponentVillageField2(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  77 */     if (this.field_143015_k < 0) {
/*     */       
/*  79 */       this.field_143015_k = getAverageGroundLevel(par1World, par3StructureBoundingBox);
/*     */       
/*  81 */       if (this.field_143015_k < 0)
/*     */       {
/*  83 */         return true;
/*     */       }
/*     */       
/*  86 */       this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
/*     */     } 
/*     */     
/*  89 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 6, 4, 8, 0, 0, false);
/*  90 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 2, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
/*  91 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 1, 5, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
/*  92 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 0, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
/*  93 */     fillWithBlocks(par1World, par3StructureBoundingBox, 6, 0, 0, 6, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
/*  94 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 5, 0, 0, Block.wood.blockID, Block.wood.blockID, false);
/*  95 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 8, 5, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
/*     */     
/*     */     int var4;
/*     */     
/*  99 */     for (var4 = 1; var4 <= 7; var4++) {
/*     */ 
/*     */ 
/*     */       
/* 103 */       placeBlockAtCurrentPosition(par1World, this.cropTypeA, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 1, 1, var4, par3StructureBoundingBox);
/* 104 */       placeBlockAtCurrentPosition(par1World, this.cropTypeA, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 2, 1, var4, par3StructureBoundingBox);
/* 105 */       placeBlockAtCurrentPosition(par1World, this.cropTypeB, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 4, 1, var4, par3StructureBoundingBox);
/* 106 */       placeBlockAtCurrentPosition(par1World, this.cropTypeB, MathHelper.getRandomIntegerInRange(par2Random, 2, 6), 5, 1, var4, par3StructureBoundingBox);
/*     */     } 
/*     */     
/* 109 */     for (var4 = 0; var4 < 9; var4++) {
/*     */       
/* 111 */       for (int var5 = 0; var5 < 7; var5++) {
/*     */         
/* 113 */         clearCurrentPositionBlocksUpwards(par1World, var5, 4, var4, par3StructureBoundingBox);
/* 114 */         fillCurrentPositionBlocksDownwards(par1World, Block.dirt.blockID, 0, var5, -1, var4, par3StructureBoundingBox);
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillageField2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */