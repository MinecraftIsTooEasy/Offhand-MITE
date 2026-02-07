/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentVillagePathGen
/*     */   extends ComponentVillageRoadPiece
/*     */ {
/*     */   private int averageGroundLevel;
/*     */   
/*     */   public ComponentVillagePathGen() {}
/*     */   
/*     */   public ComponentVillagePathGen(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
/*  14 */     super(par1ComponentVillageStartPiece, par2);
/*  15 */     this.coordBaseMode = par5;
/*  16 */     this.boundingBox = par4StructureBoundingBox;
/*  17 */     this.averageGroundLevel = Math.max(par4StructureBoundingBox.getXSize(), par4StructureBoundingBox.getZSize());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  22 */     super.func_143012_a(par1NBTTagCompound);
/*  23 */     par1NBTTagCompound.setInteger("Length", this.averageGroundLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  28 */     super.func_143011_b(par1NBTTagCompound);
/*  29 */     this.averageGroundLevel = par1NBTTagCompound.getInteger("Length");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/*  37 */     boolean var4 = false;
/*     */     
/*     */     int var5;
/*     */     
/*  41 */     for (var5 = par3Random.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + par3Random.nextInt(5)) {
/*     */       
/*  43 */       StructureComponent var6 = getNextComponentNN((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, 0, var5);
/*     */       
/*  45 */       if (var6 != null) {
/*     */         
/*  47 */         var5 += Math.max(var6.boundingBox.getXSize(), var6.boundingBox.getZSize());
/*  48 */         var4 = true;
/*     */       } 
/*     */     } 
/*     */     
/*  52 */     for (var5 = par3Random.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + par3Random.nextInt(5)) {
/*     */       
/*  54 */       StructureComponent var6 = getNextComponentPP((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, 0, var5);
/*     */       
/*  56 */       if (var6 != null) {
/*     */         
/*  58 */         var5 += Math.max(var6.boundingBox.getXSize(), var6.boundingBox.getZSize());
/*  59 */         var4 = true;
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     if (var4 && par3Random.nextInt(3) > 0)
/*     */     {
/*  65 */       switch (this.coordBaseMode) {
/*     */         
/*     */         case 0:
/*  68 */           StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 1, getComponentType());
/*     */           break;
/*     */         
/*     */         case 1:
/*  72 */           StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, getComponentType());
/*     */           break;
/*     */         
/*     */         case 2:
/*  76 */           StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, 1, getComponentType());
/*     */           break;
/*     */         
/*     */         case 3:
/*  80 */           StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, getComponentType());
/*     */           break;
/*     */       } 
/*     */     }
/*  84 */     if (var4 && par3Random.nextInt(3) > 0)
/*     */     {
/*  86 */       switch (this.coordBaseMode) {
/*     */         
/*     */         case 0:
/*  89 */           StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 3, getComponentType());
/*     */           break;
/*     */         
/*     */         case 1:
/*  93 */           StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, getComponentType());
/*     */           break;
/*     */         
/*     */         case 2:
/*  97 */           StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, 3, getComponentType());
/*     */           break;
/*     */         
/*     */         case 3:
/* 101 */           StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, getComponentType());
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static StructureBoundingBox func_74933_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6) {
/* 108 */     for (int var7 = 7 * MathHelper.getRandomIntegerInRange(par2Random, 3, 5); var7 >= 7; var7 -= 7) {
/*     */       
/* 110 */       StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 3, 3, var7, par6);
/*     */       
/* 112 */       if (StructureComponent.findIntersecting(par1List, var8) == null)
/*     */       {
/* 114 */         return var8;
/*     */       }
/*     */     } 
/*     */     
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/* 127 */     int var4 = getBiomeSpecificBlock(Block.gravel.blockID, 0);
/* 128 */     int metadata = (var4 == Block.gravel.blockID) ? 1 : 0;
/*     */     
/* 130 */     for (int var5 = this.boundingBox.minX; var5 <= this.boundingBox.maxX; var5++) {
/*     */       
/* 132 */       for (int var6 = this.boundingBox.minZ; var6 <= this.boundingBox.maxZ; var6++) {
/*     */         
/* 134 */         if (par3StructureBoundingBox.isVecInside(var5, 64, var6)) {
/*     */           
/* 136 */           int var7 = par1World.getTopSolidOrLiquidBlock(var5, var6) - 1;
/*     */           
/* 138 */           par1World.setBlock(var5, var7, var6, var4, metadata, 2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillagePathGen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */