/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentMineshaftCross
/*     */   extends StructureComponent
/*     */ {
/*     */   private int corridorDirection;
/*     */   private boolean isMultipleFloors;
/*     */   
/*     */   public ComponentMineshaftCross() {}
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  15 */     par1NBTTagCompound.setBoolean("tf", this.isMultipleFloors);
/*  16 */     par1NBTTagCompound.setInteger("D", this.corridorDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  21 */     this.isMultipleFloors = par1NBTTagCompound.getBoolean("tf");
/*  22 */     this.corridorDirection = par1NBTTagCompound.getInteger("D");
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentMineshaftCross(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
/*  27 */     super(par1);
/*  28 */     this.corridorDirection = par4;
/*  29 */     this.boundingBox = par3StructureBoundingBox;
/*  30 */     this.isMultipleFloors = (par3StructureBoundingBox.getYSize() > 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public static StructureBoundingBox findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5) {
/*  35 */     StructureBoundingBox var6 = new StructureBoundingBox(par2, par3, par4, par2, par3 + 2, par4);
/*     */     
/*  37 */     if (par1Random.nextInt(4) == 0)
/*     */     {
/*  39 */       var6.maxY += 4;
/*     */     }
/*     */     
/*  42 */     switch (par5) {
/*     */       
/*     */       case 0:
/*  45 */         var6.minX = par2 - 1;
/*  46 */         var6.maxX = par2 + 3;
/*  47 */         var6.maxZ = par4 + 4;
/*     */         break;
/*     */       
/*     */       case 1:
/*  51 */         var6.minX = par2 - 4;
/*  52 */         var6.minZ = par4 - 1;
/*  53 */         var6.maxZ = par4 + 3;
/*     */         break;
/*     */       
/*     */       case 2:
/*  57 */         var6.minX = par2 - 1;
/*  58 */         var6.maxX = par2 + 3;
/*  59 */         var6.minZ = par4 - 4;
/*     */         break;
/*     */       
/*     */       case 3:
/*  63 */         var6.maxX = par2 + 4;
/*  64 */         var6.minZ = par4 - 1;
/*  65 */         var6.maxZ = par4 + 3;
/*     */         break;
/*     */     } 
/*  68 */     return (StructureComponent.findIntersecting(par0List, var6) != null) ? null : var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/*  76 */     int var4 = getComponentType();
/*     */     
/*  78 */     switch (this.corridorDirection) {
/*     */       
/*     */       case 0:
/*  81 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, var4);
/*  82 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 1, var4);
/*  83 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 3, var4);
/*     */         break;
/*     */       
/*     */       case 1:
/*  87 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, var4);
/*  88 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, var4);
/*  89 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 1, var4);
/*     */         break;
/*     */       
/*     */       case 2:
/*  93 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, var4);
/*  94 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 1, var4);
/*  95 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 3, var4);
/*     */         break;
/*     */       
/*     */       case 3:
/*  99 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, var4);
/* 100 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, var4);
/* 101 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 3, var4);
/*     */         break;
/*     */     } 
/* 104 */     if (this.isMultipleFloors) {
/*     */       
/* 106 */       if (par3Random.nextBoolean())
/*     */       {
/* 108 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ - 1, 2, var4);
/*     */       }
/*     */       
/* 111 */       if (par3Random.nextBoolean())
/*     */       {
/* 113 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, 1, var4);
/*     */       }
/*     */       
/* 116 */       if (par3Random.nextBoolean())
/*     */       {
/* 118 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, 3, var4);
/*     */       }
/*     */       
/* 121 */       if (par3Random.nextBoolean())
/*     */       {
/* 123 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.maxZ + 1, 0, var4);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/* 134 */     if (isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox))
/*     */     {
/* 136 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 140 */     if (this.isMultipleFloors) {
/*     */       
/* 142 */       fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ, 0, 0, false);
/* 143 */       fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ - 1, 0, 0, false);
/* 144 */       fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.minX + 1, this.boundingBox.maxY - 2, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, 0, 0, false);
/* 145 */       fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.minX, this.boundingBox.maxY - 2, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, 0, 0, false);
/* 146 */       fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.minX + 1, this.boundingBox.minY + 3, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.minY + 3, this.boundingBox.maxZ - 1, 0, 0, false);
/*     */     }
/*     */     else {
/*     */       
/* 150 */       fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, 0, 0, false);
/* 151 */       fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, 0, 0, false);
/*     */     } 
/*     */     
/* 154 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.minX + 1, this.boundingBox.maxY, this.boundingBox.minZ + 1, Block.planks.blockID, 0, false);
/* 155 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.minX + 1, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Block.planks.blockID, 0, false);
/* 156 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.minZ + 1, Block.planks.blockID, 0, false);
/* 157 */     fillWithBlocks(par1World, par3StructureBoundingBox, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Block.planks.blockID, 0, false);
/*     */     
/* 159 */     for (int var4 = this.boundingBox.minX; var4 <= this.boundingBox.maxX; var4++) {
/*     */       
/* 161 */       for (int var5 = this.boundingBox.minZ; var5 <= this.boundingBox.maxZ; var5++) {
/*     */         
/* 163 */         int var6 = getBlockIdAtCurrentPosition(par1World, var4, this.boundingBox.minY - 1, var5, par3StructureBoundingBox);
/*     */         
/* 165 */         if (var6 == 0)
/*     */         {
/* 167 */           placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, var4, this.boundingBox.minY - 1, var5, par3StructureBoundingBox);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 172 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentMineshaftCross.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */