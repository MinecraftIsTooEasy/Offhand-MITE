/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class StructureComponent
/*      */ {
/*      */   protected StructureBoundingBox boundingBox;
/*      */   protected int coordBaseMode;
/*      */   protected int componentType;
/*      */   
/*      */   public StructureComponent() {}
/*      */   
/*      */   protected StructureComponent(int par1) {
/*   21 */     this.componentType = par1;
/*   22 */     this.coordBaseMode = -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public NBTTagCompound func_143010_b() {
/*   27 */     NBTTagCompound var1 = new NBTTagCompound();
/*   28 */     var1.setString("id", MapGenStructureIO.func_143036_a(this));
/*   29 */     var1.setTag("BB", this.boundingBox.func_143047_a("BB"));
/*   30 */     var1.setInteger("O", this.coordBaseMode);
/*   31 */     var1.setInteger("GD", this.componentType);
/*   32 */     func_143012_a(var1);
/*   33 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   protected abstract void func_143012_a(NBTTagCompound paramNBTTagCompound);
/*      */   
/*      */   public void func_143009_a(World par1World, NBTTagCompound par2NBTTagCompound) {
/*   40 */     if (par2NBTTagCompound.hasKey("BB"))
/*      */     {
/*   42 */       this.boundingBox = new StructureBoundingBox(par2NBTTagCompound.getIntArray("BB"));
/*      */     }
/*      */     
/*   45 */     this.coordBaseMode = par2NBTTagCompound.getInteger("O");
/*   46 */     this.componentType = par2NBTTagCompound.getInteger("GD");
/*   47 */     func_143011_b(par2NBTTagCompound);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void func_143011_b(NBTTagCompound paramNBTTagCompound);
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract boolean addComponentParts(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox);
/*      */ 
/*      */ 
/*      */   
/*      */   public StructureBoundingBox getBoundingBox() {
/*   65 */     return this.boundingBox;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getComponentType() {
/*   73 */     return this.componentType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static StructureComponent findIntersecting(List par0List, StructureBoundingBox par1StructureBoundingBox) {
/*      */     StructureComponent var3;
/*   81 */     Iterator<StructureComponent> var2 = par0List.iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*   86 */       if (!var2.hasNext())
/*      */       {
/*   88 */         return null;
/*      */       }
/*      */       
/*   91 */       var3 = var2.next();
/*      */     }
/*   93 */     while (var3.getBoundingBox() == null || !var3.getBoundingBox().intersectsWith(par1StructureBoundingBox));
/*      */     
/*   95 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkPosition getCenter() {
/*  100 */     return new ChunkPosition(this.boundingBox.getCenterX(), this.boundingBox.getCenterY(), this.boundingBox.getCenterZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isLiquidInStructureBoundingBox(World par1World, StructureBoundingBox par2StructureBoundingBox) {
/*  108 */     int var3 = Math.max(this.boundingBox.minX - 1, par2StructureBoundingBox.minX);
/*  109 */     int var4 = Math.max(this.boundingBox.minY - 1, par2StructureBoundingBox.minY);
/*  110 */     int var5 = Math.max(this.boundingBox.minZ - 1, par2StructureBoundingBox.minZ);
/*  111 */     int var6 = Math.min(this.boundingBox.maxX + 1, par2StructureBoundingBox.maxX);
/*  112 */     int var7 = Math.min(this.boundingBox.maxY + 1, par2StructureBoundingBox.maxY);
/*  113 */     int var8 = Math.min(this.boundingBox.maxZ + 1, par2StructureBoundingBox.maxZ);
/*      */ 
/*      */     
/*      */     int var9;
/*      */     
/*  118 */     for (var9 = var3; var9 <= var6; var9++) {
/*      */       
/*  120 */       for (int var10 = var5; var10 <= var8; var10++) {
/*      */         
/*  122 */         int var11 = par1World.getBlockId(var9, var4, var10);
/*      */         
/*  124 */         if (var11 > 0 && (Block.blocksList[var11]).blockMaterial.isLiquid())
/*      */         {
/*  126 */           return true;
/*      */         }
/*      */         
/*  129 */         var11 = par1World.getBlockId(var9, var7, var10);
/*      */         
/*  131 */         if (var11 > 0 && (Block.blocksList[var11]).blockMaterial.isLiquid())
/*      */         {
/*  133 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  138 */     for (var9 = var3; var9 <= var6; var9++) {
/*      */       
/*  140 */       for (int var10 = var4; var10 <= var7; var10++) {
/*      */         
/*  142 */         int var11 = par1World.getBlockId(var9, var10, var5);
/*      */         
/*  144 */         if (var11 > 0 && (Block.blocksList[var11]).blockMaterial.isLiquid())
/*      */         {
/*  146 */           return true;
/*      */         }
/*      */         
/*  149 */         var11 = par1World.getBlockId(var9, var10, var8);
/*      */         
/*  151 */         if (var11 > 0 && (Block.blocksList[var11]).blockMaterial.isLiquid())
/*      */         {
/*  153 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  158 */     for (var9 = var5; var9 <= var8; var9++) {
/*      */       
/*  160 */       for (int var10 = var4; var10 <= var7; var10++) {
/*      */         
/*  162 */         int var11 = par1World.getBlockId(var3, var10, var9);
/*      */         
/*  164 */         if (var11 > 0 && (Block.blocksList[var11]).blockMaterial.isLiquid())
/*      */         {
/*  166 */           return true;
/*      */         }
/*      */         
/*  169 */         var11 = par1World.getBlockId(var6, var10, var9);
/*      */         
/*  171 */         if (var11 > 0 && (Block.blocksList[var11]).blockMaterial.isLiquid())
/*      */         {
/*  173 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  178 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getXWithOffset(int par1, int par2) {
/*  183 */     switch (this.coordBaseMode) {
/*      */       
/*      */       case 0:
/*      */       case 2:
/*  187 */         return this.boundingBox.minX + par1;
/*      */       
/*      */       case 1:
/*  190 */         return this.boundingBox.maxX - par2;
/*      */       
/*      */       case 3:
/*  193 */         return this.boundingBox.minX + par2;
/*      */     } 
/*      */     
/*  196 */     return par1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getYWithOffset(int par1) {
/*  202 */     return (this.coordBaseMode == -1) ? par1 : (par1 + this.boundingBox.minY);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getZWithOffset(int par1, int par2) {
/*  207 */     switch (this.coordBaseMode) {
/*      */       
/*      */       case 0:
/*  210 */         return this.boundingBox.minZ + par2;
/*      */       
/*      */       case 1:
/*      */       case 3:
/*  214 */         return this.boundingBox.minZ + par1;
/*      */       
/*      */       case 2:
/*  217 */         return this.boundingBox.maxZ - par2;
/*      */     } 
/*      */     
/*  220 */     return par2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getMetadataWithOffset(int par1, int par2) {
/*  230 */     if (par1 == Block.rail.blockID) {
/*      */       
/*  232 */       if (this.coordBaseMode == 1 || this.coordBaseMode == 3)
/*      */       {
/*  234 */         if (par2 == 1)
/*      */         {
/*  236 */           return 0;
/*      */         }
/*      */         
/*  239 */         return 1;
/*      */       }
/*      */     
/*  242 */     } else if (par1 != Block.doorWood.blockID && par1 != Block.doorIron.blockID) {
/*      */       
/*  244 */       if (par1 != Block.stairsCobblestone.blockID && par1 != Block.stairsWoodOak.blockID && par1 != Block.stairsNetherBrick.blockID && par1 != Block.stairsStoneBrick.blockID && par1 != Block.stairsSandStone.blockID) {
/*      */         
/*  246 */         if (par1 == Block.ladder.blockID) {
/*      */           
/*  248 */           if (this.coordBaseMode == 0)
/*      */           {
/*  250 */             if (par2 == 2)
/*      */             {
/*  252 */               return 3;
/*      */             }
/*      */             
/*  255 */             if (par2 == 3)
/*      */             {
/*  257 */               return 2;
/*      */             }
/*      */           }
/*  260 */           else if (this.coordBaseMode == 1)
/*      */           {
/*  262 */             if (par2 == 2)
/*      */             {
/*  264 */               return 4;
/*      */             }
/*      */             
/*  267 */             if (par2 == 3)
/*      */             {
/*  269 */               return 5;
/*      */             }
/*      */             
/*  272 */             if (par2 == 4)
/*      */             {
/*  274 */               return 2;
/*      */             }
/*      */             
/*  277 */             if (par2 == 5)
/*      */             {
/*  279 */               return 3;
/*      */             }
/*      */           }
/*  282 */           else if (this.coordBaseMode == 3)
/*      */           {
/*  284 */             if (par2 == 2)
/*      */             {
/*  286 */               return 5;
/*      */             }
/*      */             
/*  289 */             if (par2 == 3)
/*      */             {
/*  291 */               return 4;
/*      */             }
/*      */             
/*  294 */             if (par2 == 4)
/*      */             {
/*  296 */               return 2;
/*      */             }
/*      */             
/*  299 */             if (par2 == 5)
/*      */             {
/*  301 */               return 3;
/*      */             }
/*      */           }
/*      */         
/*  305 */         } else if (par1 == Block.stoneButton.blockID) {
/*      */           
/*  307 */           if (this.coordBaseMode == 0)
/*      */           {
/*  309 */             if (par2 == 3)
/*      */             {
/*  311 */               return 4;
/*      */             }
/*      */             
/*  314 */             if (par2 == 4)
/*      */             {
/*  316 */               return 3;
/*      */             }
/*      */           }
/*  319 */           else if (this.coordBaseMode == 1)
/*      */           {
/*  321 */             if (par2 == 3)
/*      */             {
/*  323 */               return 1;
/*      */             }
/*      */             
/*  326 */             if (par2 == 4)
/*      */             {
/*  328 */               return 2;
/*      */             }
/*      */             
/*  331 */             if (par2 == 2)
/*      */             {
/*  333 */               return 3;
/*      */             }
/*      */             
/*  336 */             if (par2 == 1)
/*      */             {
/*  338 */               return 4;
/*      */             }
/*      */           }
/*  341 */           else if (this.coordBaseMode == 3)
/*      */           {
/*  343 */             if (par2 == 3)
/*      */             {
/*  345 */               return 2;
/*      */             }
/*      */             
/*  348 */             if (par2 == 4)
/*      */             {
/*  350 */               return 1;
/*      */             }
/*      */             
/*  353 */             if (par2 == 2)
/*      */             {
/*  355 */               return 3;
/*      */             }
/*      */             
/*  358 */             if (par2 == 1)
/*      */             {
/*  360 */               return 4;
/*      */             }
/*      */           }
/*      */         
/*  364 */         } else if (par1 != Block.tripWireSource.blockID && (Block.blocksList[par1] == null || !(Block.blocksList[par1] instanceof BlockDirectional))) {
/*      */           
/*  366 */           if (par1 == Block.pistonBase.blockID || par1 == Block.pistonStickyBase.blockID || par1 == Block.lever.blockID || par1 == Block.dispenser.blockID)
/*      */           {
/*  368 */             if (this.coordBaseMode == 0)
/*      */             {
/*  370 */               if (par2 == 2 || par2 == 3)
/*      */               {
/*  372 */                 return Facing.oppositeSide[par2];
/*      */               }
/*      */             }
/*  375 */             else if (this.coordBaseMode == 1)
/*      */             {
/*  377 */               if (par2 == 2)
/*      */               {
/*  379 */                 return 4;
/*      */               }
/*      */               
/*  382 */               if (par2 == 3)
/*      */               {
/*  384 */                 return 5;
/*      */               }
/*      */               
/*  387 */               if (par2 == 4)
/*      */               {
/*  389 */                 return 2;
/*      */               }
/*      */               
/*  392 */               if (par2 == 5)
/*      */               {
/*  394 */                 return 3;
/*      */               }
/*      */             }
/*  397 */             else if (this.coordBaseMode == 3)
/*      */             {
/*  399 */               if (par2 == 2)
/*      */               {
/*  401 */                 return 5;
/*      */               }
/*      */               
/*  404 */               if (par2 == 3)
/*      */               {
/*  406 */                 return 4;
/*      */               }
/*      */               
/*  409 */               if (par2 == 4)
/*      */               {
/*  411 */                 return 2;
/*      */               }
/*      */               
/*  414 */               if (par2 == 5)
/*      */               {
/*  416 */                 return 3;
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*  421 */         } else if (this.coordBaseMode == 0) {
/*      */           
/*  423 */           if (par2 == 0 || par2 == 2)
/*      */           {
/*  425 */             return Direction.rotateOpposite[par2];
/*      */           }
/*      */         }
/*  428 */         else if (this.coordBaseMode == 1) {
/*      */           
/*  430 */           if (par2 == 2)
/*      */           {
/*  432 */             return 1;
/*      */           }
/*      */           
/*  435 */           if (par2 == 0)
/*      */           {
/*  437 */             return 3;
/*      */           }
/*      */           
/*  440 */           if (par2 == 1)
/*      */           {
/*  442 */             return 2;
/*      */           }
/*      */           
/*  445 */           if (par2 == 3)
/*      */           {
/*  447 */             return 0;
/*      */           }
/*      */         }
/*  450 */         else if (this.coordBaseMode == 3) {
/*      */           
/*  452 */           if (par2 == 2)
/*      */           {
/*  454 */             return 3;
/*      */           }
/*      */           
/*  457 */           if (par2 == 0)
/*      */           {
/*  459 */             return 1;
/*      */           }
/*      */           
/*  462 */           if (par2 == 1)
/*      */           {
/*  464 */             return 2;
/*      */           }
/*      */           
/*  467 */           if (par2 == 3)
/*      */           {
/*  469 */             return 0;
/*      */           }
/*      */         }
/*      */       
/*  473 */       } else if (this.coordBaseMode == 0) {
/*      */         
/*  475 */         if (par2 == 2)
/*      */         {
/*  477 */           return 3;
/*      */         }
/*      */         
/*  480 */         if (par2 == 3)
/*      */         {
/*  482 */           return 2;
/*      */         }
/*      */       }
/*  485 */       else if (this.coordBaseMode == 1) {
/*      */         
/*  487 */         if (par2 == 0)
/*      */         {
/*  489 */           return 2;
/*      */         }
/*      */         
/*  492 */         if (par2 == 1)
/*      */         {
/*  494 */           return 3;
/*      */         }
/*      */         
/*  497 */         if (par2 == 2)
/*      */         {
/*  499 */           return 0;
/*      */         }
/*      */         
/*  502 */         if (par2 == 3)
/*      */         {
/*  504 */           return 1;
/*      */         }
/*      */       }
/*  507 */       else if (this.coordBaseMode == 3) {
/*      */         
/*  509 */         if (par2 == 0)
/*      */         {
/*  511 */           return 2;
/*      */         }
/*      */         
/*  514 */         if (par2 == 1)
/*      */         {
/*  516 */           return 3;
/*      */         }
/*      */         
/*  519 */         if (par2 == 2)
/*      */         {
/*  521 */           return 1;
/*      */         }
/*      */         
/*  524 */         if (par2 == 3)
/*      */         {
/*  526 */           return 0;
/*      */         }
/*      */       }
/*      */     
/*  530 */     } else if (this.coordBaseMode == 0) {
/*      */       
/*  532 */       if (par2 == 0)
/*      */       {
/*  534 */         return 2;
/*      */       }
/*      */       
/*  537 */       if (par2 == 2)
/*      */       {
/*  539 */         return 0;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  544 */       if (this.coordBaseMode == 1)
/*      */       {
/*  546 */         return par2 + 1 & 0x3;
/*      */       }
/*      */       
/*  549 */       if (this.coordBaseMode == 3)
/*      */       {
/*  551 */         return par2 + 3 & 0x3;
/*      */       }
/*      */     } 
/*      */     
/*  555 */     return par2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void placeBlockAtCurrentPosition(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox) {
/*  563 */     int var8 = getXWithOffset(par4, par6);
/*  564 */     int var9 = getYWithOffset(par5);
/*  565 */     int var10 = getZWithOffset(par4, par6);
/*      */     
/*  567 */     if (par7StructureBoundingBox.isVecInside(var8, var9, var10))
/*      */     {
/*  569 */       par1World.setBlock(var8, var9, var10, par2, par3, 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean placeBlockRelativeWithDefaultMetadata(World world, Block block, int dx, int dy, int dz, StructureBoundingBox structure_bounding_box, boolean report_metadata_failure) {
/*  578 */     int x = getXWithOffset(dx, dz);
/*  579 */     int y = getYWithOffset(dy);
/*  580 */     int z = getZWithOffset(dx, dz);
/*      */     
/*  582 */     if (structure_bounding_box.isVecInside(x, y, z)) {
/*      */       
/*  584 */       if (block == null) {
/*  585 */         return world.setBlock(x, y, z, 0, 0, 2);
/*      */       }
/*  587 */       return world.setBlockWithDefaultMetadata(x, y, z, block, 2, report_metadata_failure);
/*      */     } 
/*      */     
/*  590 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean placeBlockRelativeWithDefaultMetadata(World world, Block block, int dx, int dy, int dz, StructureBoundingBox structure_bounding_box) {
/*  595 */     return placeBlockRelativeWithDefaultMetadata(world, block, dx, dy, dz, structure_bounding_box, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void placeBlockRelativeWithAdjustedMetadata(World world, Block block, int dx, int dy, int dz, int metadata_in_coord_base_mode_2, StructureBoundingBox structure_bounding_box) {
/*  601 */     int x = getXWithOffset(dx, dz);
/*  602 */     int y = getYWithOffset(dy);
/*  603 */     int z = getZWithOffset(dx, dz);
/*      */     
/*  605 */     if (!structure_bounding_box.isVecInside(x, y, z)) {
/*      */       return;
/*      */     }
/*  608 */     if (block == null) {
/*  609 */       world.setBlock(x, y, z, 0, 0, 2);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  617 */     world.setBlockWithMetadataAdjustedForCoordBaseMode(x, y, z, block, metadata_in_coord_base_mode_2, 2, this.coordBaseMode);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getBlockIdAtCurrentPosition(World par1World, int par2, int par3, int par4, StructureBoundingBox par5StructureBoundingBox) {
/*  622 */     int var6 = getXWithOffset(par2, par4);
/*  623 */     int var7 = getYWithOffset(par3);
/*  624 */     int var8 = getZWithOffset(par2, par4);
/*  625 */     return !par5StructureBoundingBox.isVecInside(var6, var7, var8) ? 0 : par1World.getBlockId(var6, var7, var8);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fillWithAir(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8) {
/*  634 */     for (int var9 = par4; var9 <= par7; var9++) {
/*      */       
/*  636 */       for (int var10 = par3; var10 <= par6; var10++) {
/*      */         
/*  638 */         for (int var11 = par5; var11 <= par8; var11++)
/*      */         {
/*  640 */           placeBlockAtCurrentPosition(par1World, 0, 0, var10, var9, var11, par2StructureBoundingBox);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fillWithBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8, int par9, int par10, boolean par11) {
/*  652 */     for (int var12 = par4; var12 <= par7; var12++) {
/*      */       
/*  654 */       for (int var13 = par3; var13 <= par6; var13++) {
/*      */         
/*  656 */         for (int var14 = par5; var14 <= par8; var14++) {
/*      */           
/*  658 */           if (!par11 || getBlockIdAtCurrentPosition(par1World, var13, var12, var14, par2StructureBoundingBox) != 0)
/*      */           {
/*  660 */             if (var12 != par4 && var12 != par7 && var13 != par3 && var13 != par6 && var14 != par5 && var14 != par8) {
/*      */               
/*  662 */               placeBlockAtCurrentPosition(par1World, par10, 0, var13, var12, var14, par2StructureBoundingBox);
/*      */             }
/*      */             else {
/*      */               
/*  666 */               placeBlockAtCurrentPosition(par1World, par9, 0, var13, var12, var14, par2StructureBoundingBox);
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fillWithMetadataBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8, int par9, int par10, int par11, int par12, boolean par13) {
/*  681 */     for (int var14 = par4; var14 <= par7; var14++) {
/*      */       
/*  683 */       for (int var15 = par3; var15 <= par6; var15++) {
/*      */         
/*  685 */         for (int var16 = par5; var16 <= par8; var16++) {
/*      */           
/*  687 */           if (!par13 || getBlockIdAtCurrentPosition(par1World, var15, var14, var16, par2StructureBoundingBox) != 0)
/*      */           {
/*  689 */             if (var14 != par4 && var14 != par7 && var15 != par3 && var15 != par6 && var16 != par5 && var16 != par8) {
/*      */               
/*  691 */               placeBlockAtCurrentPosition(par1World, par11, par12, var15, var14, var16, par2StructureBoundingBox);
/*      */             }
/*      */             else {
/*      */               
/*  695 */               placeBlockAtCurrentPosition(par1World, par9, par10, var15, var14, var16, par2StructureBoundingBox);
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fillWithRandomizedBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8, boolean par9, Random par10Random, StructurePieceBlockSelector par11StructurePieceBlockSelector) {
/*  709 */     for (int var12 = par4; var12 <= par7; var12++) {
/*      */       
/*  711 */       for (int var13 = par3; var13 <= par6; var13++) {
/*      */         
/*  713 */         for (int var14 = par5; var14 <= par8; var14++) {
/*      */           
/*  715 */           if (!par9 || getBlockIdAtCurrentPosition(par1World, var13, var12, var14, par2StructureBoundingBox) != 0) {
/*      */             
/*  717 */             par11StructurePieceBlockSelector.selectBlocks(par10Random, var13, var12, var14, (var12 == par4 || var12 == par7 || var13 == par3 || var13 == par6 || var14 == par5 || var14 == par8));
/*  718 */             placeBlockAtCurrentPosition(par1World, par11StructurePieceBlockSelector.getSelectedBlockId(), par11StructurePieceBlockSelector.getSelectedBlockMetaData(), var13, var12, var14, par2StructureBoundingBox);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void randomlyFillWithBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, float par4, int par5, int par6, int par7, int par8, int par9, int par10, int par11, int par12, boolean par13) {
/*  731 */     for (int var14 = par6; var14 <= par9; var14++) {
/*      */       
/*  733 */       for (int var15 = par5; var15 <= par8; var15++) {
/*      */         
/*  735 */         for (int var16 = par7; var16 <= par10; var16++) {
/*      */           
/*  737 */           if (par3Random.nextFloat() <= par4 && (!par13 || getBlockIdAtCurrentPosition(par1World, var15, var14, var16, par2StructureBoundingBox) != 0))
/*      */           {
/*  739 */             if (var14 != par6 && var14 != par9 && var15 != par5 && var15 != par8 && var16 != par7 && var16 != par10) {
/*      */               
/*  741 */               placeBlockAtCurrentPosition(par1World, par12, 0, var15, var14, var16, par2StructureBoundingBox);
/*      */             }
/*      */             else {
/*      */               
/*  745 */               placeBlockAtCurrentPosition(par1World, par11, 0, var15, var14, var16, par2StructureBoundingBox);
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void randomlyPlaceBlock(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, float par4, int par5, int par6, int par7, int par8, int par9) {
/*  758 */     if (par3Random.nextFloat() < par4)
/*      */     {
/*  760 */       placeBlockAtCurrentPosition(par1World, par8, par9, par5, par6, par7, par2StructureBoundingBox);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean placeBlockWithChanceAndDefaultMetadata(World world, StructureBoundingBox structure_bounding_box, Random random, float chance, int dx, int dy, int dz, int block_id, boolean report_metadata_failure) {
/*  766 */     if (random.nextFloat() < chance) {
/*      */ 
/*      */ 
/*      */       
/*  770 */       Block block = Block.getBlock(block_id);
/*      */       
/*  772 */       if (block == null) {
/*      */         
/*  774 */         Minecraft.setErrorMessage("randomlyPlaceBlockWithDefaultMetadata: block was null");
/*  775 */         return false;
/*      */       } 
/*      */       
/*  778 */       return placeBlockRelativeWithDefaultMetadata(world, block, dx, dy, dz, structure_bounding_box, report_metadata_failure);
/*      */     } 
/*      */     
/*  781 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean placeBlockWithChanceAndDefaultMetadata(World world, StructureBoundingBox structure_bounding_box, Random random, float chance, int dx, int dy, int dz, int block_id) {
/*  786 */     return placeBlockWithChanceAndDefaultMetadata(world, structure_bounding_box, random, chance, dx, dy, dz, block_id, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void randomlyRareFillWithBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8, int par9, boolean par10) {
/*  804 */     float var11 = (par6 - par3 + 1);
/*  805 */     float var12 = (par7 - par4 + 1);
/*  806 */     float var13 = (par8 - par5 + 1);
/*  807 */     float var14 = par3 + var11 / 2.0F;
/*  808 */     float var15 = par5 + var13 / 2.0F;
/*      */     
/*  810 */     for (int var16 = par4; var16 <= par7; var16++) {
/*      */       
/*  812 */       float var17 = (var16 - par4) / var12;
/*      */       
/*  814 */       for (int var18 = par3; var18 <= par6; var18++) {
/*      */         
/*  816 */         float var19 = (var18 - var14) / var11 * 0.5F;
/*      */         
/*  818 */         for (int var20 = par5; var20 <= par8; var20++) {
/*      */           
/*  820 */           float var21 = (var20 - var15) / var13 * 0.5F;
/*      */           
/*  822 */           if (!par10 || getBlockIdAtCurrentPosition(par1World, var18, var16, var20, par2StructureBoundingBox) != 0) {
/*      */             
/*  824 */             float var22 = var19 * var19 + var17 * var17 + var21 * var21;
/*      */             
/*  826 */             if (var22 <= 1.05F)
/*      */             {
/*  828 */               placeBlockAtCurrentPosition(par1World, par9, 0, var18, var16, var20, par2StructureBoundingBox);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void clearCurrentPositionBlocksUpwards(World par1World, int par2, int par3, int par4, StructureBoundingBox par5StructureBoundingBox) {
/*  841 */     int var6 = getXWithOffset(par2, par4);
/*  842 */     int var7 = getYWithOffset(par3);
/*  843 */     int var8 = getZWithOffset(par2, par4);
/*      */     
/*  845 */     if (par5StructureBoundingBox.isVecInside(var6, var7, var8))
/*      */     {
/*  847 */       while (!par1World.isAirBlock(var6, var7, var8) && var7 < 255) {
/*      */         
/*  849 */         par1World.setBlock(var6, var7, var8, 0, 0, 2);
/*  850 */         var7++;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fillCurrentPositionBlocksDownwards(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox) {
/*  860 */     int var8 = getXWithOffset(par4, par6);
/*  861 */     int var9 = getYWithOffset(par5);
/*  862 */     int var10 = getZWithOffset(par4, par6);
/*      */     
/*  864 */     if (par7StructureBoundingBox.isVecInside(var8, var9, var10))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  872 */       while (var9 > 1) {
/*      */         
/*  874 */         int block_id = par1World.getBlockId(var8, var9, var10);
/*      */         
/*  876 */         if (block_id != 0) {
/*      */           
/*  878 */           Block block = Block.getBlock(block_id);
/*      */           
/*  880 */           if (!block.isLiquid())
/*      */           {
/*  882 */             if (block != Block.waterlily) {
/*      */               break;
/*      */             }
/*      */           }
/*      */         } 
/*  887 */         par1World.setBlock(var8, var9, var10, par2, par3, 2);
/*  888 */         var9--;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final boolean generateStructureChestContents(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, int par4, int par5, int par6, int chest_block_id, WeightedRandomChestContent[] par7ArrayOfWeightedRandomChestContent, int par8, float[] chances_of_artifact, EnumDirection direction_facing_in_coord_base_mode_2) {
/*  899 */     int var9 = getXWithOffset(par4, par6);
/*  900 */     int var10 = getYWithOffset(par5);
/*  901 */     int var11 = getZWithOffset(par4, par6);
/*      */ 
/*      */     
/*  904 */     if (par2StructureBoundingBox.isVecInside(var9, var10, var11) && par1World.getBlockId(var9, var10, var11) != chest_block_id) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  915 */       par1World.setBlock(var9, var10, var11, chest_block_id, Block.chest.getDefaultMetadataAdjustedForCoordBaseMode(direction_facing_in_coord_base_mode_2, this.coordBaseMode), 2);
/*      */       
/*  917 */       TileEntityChest var12 = (TileEntityChest)par1World.getBlockTileEntity(var9, var10, var11);
/*      */       
/*  919 */       if (var12 != null)
/*      */       {
/*      */         
/*  922 */         WeightedRandomChestContent.generateChestContents(par1World, var10, par3Random, par7ArrayOfWeightedRandomChestContent, var12, par8, chances_of_artifact);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  928 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  932 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean generateStructureDispenserContents(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, int par4, int par5, int par6, int par7, WeightedRandomChestContent[] par8ArrayOfWeightedRandomChestContent, int par9) {
/*  941 */     int var10 = getXWithOffset(par4, par6);
/*  942 */     int var11 = getYWithOffset(par5);
/*  943 */     int var12 = getZWithOffset(par4, par6);
/*      */     
/*  945 */     if (par2StructureBoundingBox.isVecInside(var10, var11, var12) && par1World.getBlockId(var10, var11, var12) != Block.dispenser.blockID) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  956 */       par1World.setBlockWithMetadataAdjustedForCoordBaseMode(var10, var11, var12, Block.dispenser, par7, 2, this.coordBaseMode);
/*      */       
/*  958 */       TileEntityDispenser var13 = (TileEntityDispenser)par1World.getBlockTileEntity(var10, var11, var12);
/*      */       
/*  960 */       if (var13 != null)
/*      */       {
/*  962 */         WeightedRandomChestContent.generateDispenserContents(par3Random, par8ArrayOfWeightedRandomChestContent, var13, par9);
/*      */       }
/*      */       
/*  965 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  969 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void placeDoorAtCurrentPosition(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, int par4, int par5, int par6, int par7) {
/* 1000 */     int var8 = getXWithOffset(par4, par6);
/* 1001 */     int var9 = getYWithOffset(par5);
/* 1002 */     int var10 = getZWithOffset(par4, par6);
/*      */     
/* 1004 */     if (par2StructureBoundingBox.isVecInside(var8, var9, var10))
/*      */     {
/* 1006 */       ItemDoor.placeDoorBlock(par1World, var8, var9, var10, par7, Block.doorWood);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */