/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ abstract class ComponentVillage
/*     */   extends StructureComponent {
/*   8 */   protected int field_143015_k = -1;
/*     */   
/*     */   private int villagersSpawned;
/*     */   
/*     */   private boolean field_143014_b;
/*     */ 
/*     */   
/*     */   public ComponentVillage() {}
/*     */   
/*     */   protected ComponentVillage(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2) {
/*  18 */     super(par2);
/*     */     
/*  20 */     if (par1ComponentVillageStartPiece != null)
/*     */     {
/*  22 */       this.field_143014_b = par1ComponentVillageStartPiece.inDesert;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  28 */     par1NBTTagCompound.setInteger("HPos", this.field_143015_k);
/*  29 */     par1NBTTagCompound.setInteger("VCount", this.villagersSpawned);
/*  30 */     par1NBTTagCompound.setBoolean("Desert", this.field_143014_b);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  35 */     this.field_143015_k = par1NBTTagCompound.getInteger("HPos");
/*  36 */     this.villagersSpawned = par1NBTTagCompound.getInteger("VCount");
/*  37 */     this.field_143014_b = par1NBTTagCompound.getBoolean("Desert");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StructureComponent getNextComponentNN(ComponentVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5) {
/*  45 */     switch (this.coordBaseMode) {
/*     */       
/*     */       case 0:
/*  48 */         return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, getComponentType());
/*     */       
/*     */       case 1:
/*  51 */         return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, getComponentType());
/*     */       
/*     */       case 2:
/*  54 */         return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, getComponentType());
/*     */       
/*     */       case 3:
/*  57 */         return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, getComponentType());
/*     */     } 
/*     */     
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StructureComponent getNextComponentPP(ComponentVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5) {
/*  69 */     switch (this.coordBaseMode) {
/*     */       
/*     */       case 0:
/*  72 */         return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, getComponentType());
/*     */       
/*     */       case 1:
/*  75 */         return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, getComponentType());
/*     */       
/*     */       case 2:
/*  78 */         return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, getComponentType());
/*     */       
/*     */       case 3:
/*  81 */         return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, getComponentType());
/*     */     } 
/*     */     
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getAverageGroundLevel(World par1World, StructureBoundingBox par2StructureBoundingBox) {
/*  94 */     int var3 = 0;
/*  95 */     int var4 = 0;
/*     */     
/*  97 */     for (int var5 = this.boundingBox.minZ; var5 <= this.boundingBox.maxZ; var5++) {
/*     */       
/*  99 */       for (int var6 = this.boundingBox.minX; var6 <= this.boundingBox.maxX; var6++) {
/*     */         
/* 101 */         if (par2StructureBoundingBox.isVecInside(var6, 64, var5)) {
/*     */           
/* 103 */           var3 += Math.max(par1World.getTopSolidOrLiquidBlock(var6, var5), par1World.provider.getAverageGroundLevel());
/* 104 */           var4++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     if (var4 == 0)
/*     */     {
/* 111 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 115 */     return var3 / var4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean canVillageGoDeeper(StructureBoundingBox par0StructureBoundingBox) {
/* 121 */     return (par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void spawnVillagers(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getVillagerType(int par1) {
/* 158 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getBiomeSpecificBlock(int par1, int par2) {
/* 166 */     if (this.field_143014_b) {
/*     */       
/* 168 */       if (par1 == Block.wood.blockID)
/*     */       {
/* 170 */         return Block.sandStone.blockID;
/*     */       }
/*     */       
/* 173 */       if (par1 == Block.cobblestone.blockID)
/*     */       {
/* 175 */         return Block.sandStone.blockID;
/*     */       }
/*     */       
/* 178 */       if (par1 == Block.planks.blockID)
/*     */       {
/* 180 */         return Block.sandStone.blockID;
/*     */       }
/*     */       
/* 183 */       if (par1 == Block.stairsWoodOak.blockID)
/*     */       {
/* 185 */         return Block.stairsSandStone.blockID;
/*     */       }
/*     */       
/* 188 */       if (par1 == Block.stairsCobblestone.blockID)
/*     */       {
/* 190 */         return Block.stairsSandStone.blockID;
/*     */       }
/*     */       
/* 193 */       if (par1 == Block.gravel.blockID)
/*     */       {
/* 195 */         return Block.sandStone.blockID;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     return par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getBiomeSpecificBlockMetadata(int par1, int par2) {
/* 210 */     if (this.field_143014_b) {
/*     */       
/* 212 */       if (par1 == Block.wood.blockID)
/*     */       {
/* 214 */         return 0;
/*     */       }
/*     */       
/* 217 */       if (par1 == Block.cobblestone.blockID)
/*     */       {
/* 219 */         return 0;
/*     */       }
/*     */       
/* 222 */       if (par1 == Block.planks.blockID)
/*     */       {
/* 224 */         return 2;
/*     */       }
/*     */     } 
/*     */     
/* 228 */     if (par1 == Block.gravel.blockID) {
/* 229 */       return 1;
/*     */     }
/* 231 */     return par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void placeBlockAtCurrentPosition(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox) {
/* 239 */     int var8 = getBiomeSpecificBlock(par2, par3);
/* 240 */     int var9 = getBiomeSpecificBlockMetadata(par2, par3);
/* 241 */     super.placeBlockAtCurrentPosition(par1World, var8, var9, par4, par5, par6, par7StructureBoundingBox);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillWithBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8, int par9, int par10, boolean par11) {
/* 250 */     int var12 = getBiomeSpecificBlock(par9, 0);
/* 251 */     int var13 = getBiomeSpecificBlockMetadata(par9, 0);
/* 252 */     int var14 = getBiomeSpecificBlock(par10, 0);
/* 253 */     int var15 = getBiomeSpecificBlockMetadata(par10, 0);
/* 254 */     fillWithMetadataBlocks(par1World, par2StructureBoundingBox, par3, par4, par5, par6, par7, par8, var12, var13, var14, var15, par11);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillCurrentPositionBlocksDownwards(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox) {
/* 262 */     int var8 = getBiomeSpecificBlock(par2, par3);
/* 263 */     int var9 = getBiomeSpecificBlockMetadata(par2, par3);
/* 264 */     super.fillCurrentPositionBlocksDownwards(par1World, var8, var9, par4, par5, par6, par7StructureBoundingBox);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentVillage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */