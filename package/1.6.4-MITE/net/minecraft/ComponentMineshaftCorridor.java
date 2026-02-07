/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComponentMineshaftCorridor
/*     */   extends StructureComponent
/*     */ {
/*     */   private boolean hasRails;
/*     */   private boolean hasSpiders;
/*     */   private boolean spawnerPlaced;
/*     */   private int sectionCount;
/*     */   
/*     */   public ComponentMineshaftCorridor() {}
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  21 */     par1NBTTagCompound.setBoolean("hr", this.hasRails);
/*  22 */     par1NBTTagCompound.setBoolean("sc", this.hasSpiders);
/*  23 */     par1NBTTagCompound.setBoolean("hps", this.spawnerPlaced);
/*  24 */     par1NBTTagCompound.setInteger("Num", this.sectionCount);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  29 */     this.hasRails = par1NBTTagCompound.getBoolean("hr");
/*  30 */     this.hasSpiders = par1NBTTagCompound.getBoolean("sc");
/*  31 */     this.spawnerPlaced = par1NBTTagCompound.getBoolean("hps");
/*  32 */     this.sectionCount = par1NBTTagCompound.getInteger("Num");
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentMineshaftCorridor(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
/*  37 */     super(par1);
/*  38 */     this.coordBaseMode = par4;
/*  39 */     this.boundingBox = par3StructureBoundingBox;
/*  40 */     this.hasRails = (par2Random.nextInt(3) == 0);
/*  41 */     this.hasSpiders = (!this.hasRails && par2Random.nextInt(23) == 0);
/*     */     
/*  43 */     if (this.coordBaseMode != 2 && this.coordBaseMode != 0) {
/*     */       
/*  45 */       this.sectionCount = par3StructureBoundingBox.getXSize() / 5;
/*     */     }
/*     */     else {
/*     */       
/*  49 */       this.sectionCount = par3StructureBoundingBox.getZSize() / 5;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static StructureBoundingBox findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5) {
/*  55 */     StructureBoundingBox var6 = new StructureBoundingBox(par2, par3, par4, par2, par3 + 2, par4);
/*     */     
/*     */     int var7;
/*  58 */     for (var7 = par1Random.nextInt(3) + 2; var7 > 0; var7--) {
/*     */       
/*  60 */       int var8 = var7 * 5;
/*     */       
/*  62 */       switch (par5) {
/*     */         
/*     */         case 0:
/*  65 */           var6.maxX = par2 + 2;
/*  66 */           var6.maxZ = par4 + var8 - 1;
/*     */           break;
/*     */         
/*     */         case 1:
/*  70 */           var6.minX = par2 - var8 - 1;
/*  71 */           var6.maxZ = par4 + 2;
/*     */           break;
/*     */         
/*     */         case 2:
/*  75 */           var6.maxX = par2 + 2;
/*  76 */           var6.minZ = par4 - var8 - 1;
/*     */           break;
/*     */         
/*     */         case 3:
/*  80 */           var6.maxX = par2 + var8 - 1;
/*  81 */           var6.maxZ = par4 + 2;
/*     */           break;
/*     */       } 
/*  84 */       if (StructureComponent.findIntersecting(par0List, var6) == null) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  90 */     return (var7 > 0) ? var6 : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/*  98 */     int var4 = getComponentType();
/*  99 */     int var5 = par3Random.nextInt(4);
/*     */     
/* 101 */     switch (this.coordBaseMode) {
/*     */       
/*     */       case 0:
/* 104 */         if (var5 <= 1) {
/*     */           
/* 106 */           StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ + 1, this.coordBaseMode, var4); break;
/*     */         } 
/* 108 */         if (var5 == 2) {
/*     */           
/* 110 */           StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ - 3, 1, var4);
/*     */           
/*     */           break;
/*     */         } 
/* 114 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ - 3, 3, var4);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 120 */         if (var5 <= 1) {
/*     */           
/* 122 */           StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, var4); break;
/*     */         } 
/* 124 */         if (var5 == 2) {
/*     */           
/* 126 */           StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ - 1, 2, var4);
/*     */           
/*     */           break;
/*     */         } 
/* 130 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ + 1, 0, var4);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 136 */         if (var5 <= 1) {
/*     */           
/* 138 */           StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ - 1, this.coordBaseMode, var4); break;
/*     */         } 
/* 140 */         if (var5 == 2) {
/*     */           
/* 142 */           StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ, 1, var4);
/*     */           
/*     */           break;
/*     */         } 
/* 146 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ, 3, var4);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 152 */         if (var5 <= 1) {
/*     */           
/* 154 */           StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, var4); break;
/*     */         } 
/* 156 */         if (var5 == 2) {
/*     */           
/* 158 */           StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ - 1, 2, var4);
/*     */           
/*     */           break;
/*     */         } 
/* 162 */         StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ + 1, 0, var4);
/*     */         break;
/*     */     } 
/*     */     
/* 166 */     if (var4 < 8)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 171 */       if (this.coordBaseMode != 2 && this.coordBaseMode != 0) {
/*     */         
/* 173 */         for (int var6 = this.boundingBox.minX + 3; var6 + 3 <= this.boundingBox.maxX; var6 += 5) {
/*     */           
/* 175 */           int var7 = par3Random.nextInt(5);
/*     */           
/* 177 */           if (var7 == 0)
/*     */           {
/* 179 */             StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, var6, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, var4 + 1);
/*     */           }
/* 181 */           else if (var7 == 1)
/*     */           {
/* 183 */             StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, var6, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, var4 + 1);
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 189 */         for (int var6 = this.boundingBox.minZ + 3; var6 + 3 <= this.boundingBox.maxZ; var6 += 5) {
/*     */           
/* 191 */           int var7 = par3Random.nextInt(5);
/*     */           
/* 193 */           if (var7 == 0) {
/*     */             
/* 195 */             StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, var6, 1, var4 + 1);
/*     */           }
/* 197 */           else if (var7 == 1) {
/*     */             
/* 199 */             StructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, var6, 3, var4 + 1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final boolean generateStructureChestContents(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, int par4, int par5, int par6, int chest_block_id, WeightedRandomChestContent[] par7ArrayOfWeightedRandomChestContent, int par8) {
/* 214 */     int var9 = getXWithOffset(par4, par6);
/* 215 */     int var10 = getYWithOffset(par5);
/* 216 */     int var11 = getZWithOffset(par4, par6);
/*     */     
/* 218 */     if (par2StructureBoundingBox.isVecInside(var9, var10, var11) && par1World.getBlockId(var9, var10, var11) == 0) {
/*     */       
/* 220 */       par1World.setBlock(var9, var10, var11, Block.rail.blockID, getMetadataWithOffset(Block.rail.blockID, par3Random.nextBoolean() ? 1 : 0), 2);
/* 221 */       EntityMinecartChest var12 = new EntityMinecartChest(par1World, (var9 + 0.5F), (var10 + 0.5F), (var11 + 0.5F));
/*     */       
/* 223 */       WeightedRandomChestContent.generateChestContents(par1World, var10, par3Random, par7ArrayOfWeightedRandomChestContent, var12, par8, (float[])null);
/* 224 */       par1World.spawnEntityInWorld(var12);
/* 225 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 229 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/* 239 */     if (isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox))
/*     */     {
/* 241 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 245 */     boolean var4 = false;
/* 246 */     boolean var5 = true;
/* 247 */     boolean var6 = false;
/* 248 */     boolean var7 = true;
/* 249 */     int var8 = this.sectionCount * 5 - 1;
/* 250 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 2, 1, var8, 0, 0, false);
/* 251 */     randomlyFillWithBlocks(par1World, par3StructureBoundingBox, par2Random, 0.8F, 0, 2, 0, 2, 2, var8, 0, 0, false);
/*     */     
/* 253 */     if (this.hasSpiders)
/*     */     {
/* 255 */       randomlyFillWithBlocks(par1World, par3StructureBoundingBox, par2Random, 0.6F, 0, 0, 0, 2, 1, var8, Block.web.blockID, 0, false);
/*     */     }
/*     */ 
/*     */     
/*     */     int var9;
/*     */ 
/*     */     
/* 262 */     for (var9 = 0; var9 < this.sectionCount; var9++) {
/*     */       
/* 264 */       int var10 = 2 + var9 * 5;
/* 265 */       fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, var10, 0, 1, var10, Block.fence.blockID, 0, false);
/* 266 */       fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, var10, 2, 1, var10, Block.fence.blockID, 0, false);
/*     */       
/* 268 */       if (par2Random.nextInt(4) == 0) {
/*     */         
/* 270 */         fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, var10, 0, 2, var10, Block.planks.blockID, 0, false);
/* 271 */         fillWithBlocks(par1World, par3StructureBoundingBox, 2, 2, var10, 2, 2, var10, Block.planks.blockID, 0, false);
/*     */       }
/*     */       else {
/*     */         
/* 275 */         fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, var10, 2, 2, var10, Block.planks.blockID, 0, false);
/*     */       } 
/*     */       
/* 278 */       randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.1F, 0, 2, var10 - 1, Block.web.blockID, 0);
/* 279 */       randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.1F, 2, 2, var10 - 1, Block.web.blockID, 0);
/* 280 */       randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.1F, 0, 2, var10 + 1, Block.web.blockID, 0);
/* 281 */       randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.1F, 2, 2, var10 + 1, Block.web.blockID, 0);
/* 282 */       randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.05F, 0, 2, var10 - 2, Block.web.blockID, 0);
/* 283 */       randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.05F, 2, 2, var10 - 2, Block.web.blockID, 0);
/* 284 */       randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.05F, 0, 2, var10 + 2, Block.web.blockID, 0);
/* 285 */       randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.05F, 2, 2, var10 + 2, Block.web.blockID, 0);
/*     */ 
/*     */ 
/*     */       
/* 289 */       if (par1World.getDayOfWorld() >= 10) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 294 */         placeBlockWithChanceAndDefaultMetadata(par1World, par3StructureBoundingBox, par2Random, 0.05F, 1, 2, var10 - 1, Block.torchWood.blockID, false);
/* 295 */         placeBlockWithChanceAndDefaultMetadata(par1World, par3StructureBoundingBox, par2Random, 0.05F, 1, 2, var10 + 1, Block.torchWood.blockID, false);
/*     */       }
/*     */       else {
/*     */         
/* 299 */         par2Random.nextFloat();
/* 300 */         par2Random.nextFloat();
/*     */       } 
/*     */       
/* 303 */       if (par2Random.nextInt(100) == 0)
/*     */       {
/*     */         
/* 306 */         generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 2, 0, var10 - 1, Block.rail.blockID, WeightedRandomChestContent.func_92080_a(StructureMineshaftPieces.func_78816_a(), new WeightedRandomChestContent[] { Item.enchantedBook.func_92114_b(par2Random) }), 3 + par2Random.nextInt(4));
/*     */       }
/*     */       
/* 309 */       if (par2Random.nextInt(100) == 0)
/*     */       {
/*     */         
/* 312 */         generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 0, 0, var10 + 1, Block.rail.blockID, WeightedRandomChestContent.func_92080_a(StructureMineshaftPieces.func_78816_a(), new WeightedRandomChestContent[] { Item.enchantedBook.func_92114_b(par2Random) }), 3 + par2Random.nextInt(4));
/*     */       }
/*     */       
/* 315 */       if (this.hasSpiders && !this.spawnerPlaced) {
/*     */         
/* 317 */         int var11 = getYWithOffset(0);
/* 318 */         int var12 = var10 - 1 + par2Random.nextInt(3);
/* 319 */         int var13 = getXWithOffset(1, var12);
/* 320 */         var12 = getZWithOffset(1, var12);
/*     */         
/* 322 */         if (par3StructureBoundingBox.isVecInside(var13, var11, var12)) {
/*     */           
/* 324 */           this.spawnerPlaced = true;
/* 325 */           par1World.setBlock(var13, var11, var12, Block.mobSpawner.blockID, 0, 2);
/* 326 */           TileEntityMobSpawner var14 = (TileEntityMobSpawner)par1World.getBlockTileEntity(var13, var11, var12);
/*     */           
/* 328 */           if (var14 != null)
/*     */           {
/* 330 */             var14.getSpawnerLogic().setMobID("CaveSpider");
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 336 */     for (var9 = 0; var9 <= 2; var9++) {
/*     */       
/* 338 */       for (int var10 = 0; var10 <= var8; var10++) {
/*     */         
/* 340 */         int var11 = getBlockIdAtCurrentPosition(par1World, var9, -1, var10, par3StructureBoundingBox);
/*     */         
/* 342 */         if (var11 == 0)
/*     */         {
/* 344 */           placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, var9, -1, var10, par3StructureBoundingBox);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 349 */     if (this.hasRails)
/*     */     {
/* 351 */       for (var9 = 0; var9 <= var8; var9++) {
/*     */         
/* 353 */         int var10 = getBlockIdAtCurrentPosition(par1World, 1, -1, var9, par3StructureBoundingBox);
/*     */         
/* 355 */         if (var10 > 0 && Block.opaqueCubeLookup[var10])
/*     */         {
/* 357 */           randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.7F, 1, 0, var9, Block.rail.blockID, getMetadataWithOffset(Block.rail.blockID, 0));
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 362 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentMineshaftCorridor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */