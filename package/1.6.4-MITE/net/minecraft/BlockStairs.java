/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.Random;
/*      */ 
/*      */ public class BlockStairs
/*      */   extends Block {
/*    7 */   private static final int[][] field_72159_a = new int[][] { { 2, 6 }, { 3, 7 }, { 2, 3 }, { 6, 7 }, { 0, 4 }, { 1, 5 }, { 0, 1 }, { 4, 5 } };
/*      */ 
/*      */ 
/*      */   
/*      */   public final Block modelBlock;
/*      */ 
/*      */   
/*      */   private final int modelBlockMetadata;
/*      */ 
/*      */ 
/*      */   
/*      */   protected BlockStairs(int par1, Block par2Block, int par3) {
/*   19 */     super(par1, par2Block.blockMaterial, new BlockConstants());
/*   20 */     this.modelBlock = par2Block;
/*   21 */     this.modelBlockMetadata = par3;
/*      */     
/*   23 */     setHardness(par2Block.getBlockHardness(0));
/*      */ 
/*      */     
/*   26 */     setStepSound(par2Block.stepSound);
/*   27 */     setLightOpacity(255);
/*   28 */     setCreativeTab(CreativeTabs.tabBlock);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMetadataNotes() {
/*   33 */     return "Bits 1 and 2 used for direction facing, and bit 4 set if inverted";
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isValidMetadata(int metadata) {
/*   38 */     return (metadata >= 0 && metadata < 8);
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
/*      */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*   55 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
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
/*      */   public int getRenderType() {
/*   80 */     return 10;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_82541_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*   85 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*      */     
/*   87 */     if ((var5 & 0x4) != 0) {
/*      */       
/*   89 */       setBlockBoundsForCurrentThread(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */     }
/*      */     else {
/*      */       
/*   93 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*      */     } 
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
/*      */   public static boolean isBlockStairsID(int par0) {
/*  117 */     return (par0 > 0 && Block.blocksList[par0] instanceof BlockStairs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isBlockStairsDirection(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  126 */     int var6 = par1IBlockAccess.getBlockId(par2, par3, par4);
/*  127 */     return (isBlockStairsID(var6) && par1IBlockAccess.getBlockMetadata(par2, par3, par4) == par5);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_82542_g(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  132 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*  133 */     int var6 = var5 & 0x3;
/*  134 */     float var7 = 0.5F;
/*  135 */     float var8 = 1.0F;
/*      */     
/*  137 */     if ((var5 & 0x4) != 0) {
/*      */       
/*  139 */       var7 = 0.0F;
/*  140 */       var8 = 0.5F;
/*      */     } 
/*      */     
/*  143 */     float var9 = 0.0F;
/*  144 */     float var10 = 1.0F;
/*  145 */     float var11 = 0.0F;
/*  146 */     float var12 = 0.5F;
/*  147 */     boolean var13 = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  152 */     if (var6 == 0) {
/*      */       
/*  154 */       var9 = 0.5F;
/*  155 */       var12 = 1.0F;
/*  156 */       int var14 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4);
/*  157 */       int var15 = par1IBlockAccess.getBlockMetadata(par2 + 1, par3, par4);
/*      */       
/*  159 */       if (isBlockStairsID(var14) && (var5 & 0x4) == (var15 & 0x4)) {
/*      */         
/*  161 */         int var16 = var15 & 0x3;
/*      */         
/*  163 */         if (var16 == 3 && !isBlockStairsDirection(par1IBlockAccess, par2, par3, par4 + 1, var5))
/*      */         {
/*  165 */           var12 = 0.5F;
/*  166 */           var13 = false;
/*      */         }
/*  168 */         else if (var16 == 2 && !isBlockStairsDirection(par1IBlockAccess, par2, par3, par4 - 1, var5))
/*      */         {
/*  170 */           var11 = 0.5F;
/*  171 */           var13 = false;
/*      */         }
/*      */       
/*      */       } 
/*  175 */     } else if (var6 == 1) {
/*      */       
/*  177 */       var10 = 0.5F;
/*  178 */       var12 = 1.0F;
/*  179 */       int var14 = par1IBlockAccess.getBlockId(par2 - 1, par3, par4);
/*  180 */       int var15 = par1IBlockAccess.getBlockMetadata(par2 - 1, par3, par4);
/*      */       
/*  182 */       if (isBlockStairsID(var14) && (var5 & 0x4) == (var15 & 0x4)) {
/*      */         
/*  184 */         int var16 = var15 & 0x3;
/*      */         
/*  186 */         if (var16 == 3 && !isBlockStairsDirection(par1IBlockAccess, par2, par3, par4 + 1, var5))
/*      */         {
/*  188 */           var12 = 0.5F;
/*  189 */           var13 = false;
/*      */         }
/*  191 */         else if (var16 == 2 && !isBlockStairsDirection(par1IBlockAccess, par2, par3, par4 - 1, var5))
/*      */         {
/*  193 */           var11 = 0.5F;
/*  194 */           var13 = false;
/*      */         }
/*      */       
/*      */       } 
/*  198 */     } else if (var6 == 2) {
/*      */       
/*  200 */       var11 = 0.5F;
/*  201 */       var12 = 1.0F;
/*  202 */       int var14 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1);
/*  203 */       int var15 = par1IBlockAccess.getBlockMetadata(par2, par3, par4 + 1);
/*      */       
/*  205 */       if (isBlockStairsID(var14) && (var5 & 0x4) == (var15 & 0x4)) {
/*      */         
/*  207 */         int var16 = var15 & 0x3;
/*      */         
/*  209 */         if (var16 == 1 && !isBlockStairsDirection(par1IBlockAccess, par2 + 1, par3, par4, var5))
/*      */         {
/*  211 */           var10 = 0.5F;
/*  212 */           var13 = false;
/*      */         }
/*  214 */         else if (var16 == 0 && !isBlockStairsDirection(par1IBlockAccess, par2 - 1, par3, par4, var5))
/*      */         {
/*  216 */           var9 = 0.5F;
/*  217 */           var13 = false;
/*      */         }
/*      */       
/*      */       } 
/*  221 */     } else if (var6 == 3) {
/*      */       
/*  223 */       int var14 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1);
/*  224 */       int var15 = par1IBlockAccess.getBlockMetadata(par2, par3, par4 - 1);
/*      */       
/*  226 */       if (isBlockStairsID(var14) && (var5 & 0x4) == (var15 & 0x4)) {
/*      */         
/*  228 */         int var16 = var15 & 0x3;
/*      */         
/*  230 */         if (var16 == 1 && !isBlockStairsDirection(par1IBlockAccess, par2 + 1, par3, par4, var5)) {
/*      */           
/*  232 */           var10 = 0.5F;
/*  233 */           var13 = false;
/*      */         }
/*  235 */         else if (var16 == 0 && !isBlockStairsDirection(par1IBlockAccess, par2 - 1, par3, par4, var5)) {
/*      */           
/*  237 */           var9 = 0.5F;
/*  238 */           var13 = false;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  243 */     setBlockBoundsForCurrentThread(var9, var7, var11, var10, var8, var12);
/*  244 */     return var13;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_82544_h(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  368 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*  369 */     int var6 = var5 & 0x3;
/*  370 */     float var7 = 0.5F;
/*  371 */     float var8 = 1.0F;
/*      */     
/*  373 */     if ((var5 & 0x4) != 0) {
/*      */       
/*  375 */       var7 = 0.0F;
/*  376 */       var8 = 0.5F;
/*      */     } 
/*      */     
/*  379 */     float var9 = 0.0F;
/*  380 */     float var10 = 0.5F;
/*  381 */     float var11 = 0.5F;
/*  382 */     float var12 = 1.0F;
/*  383 */     boolean var13 = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  388 */     if (var6 == 0) {
/*      */       
/*  390 */       int var14 = par1IBlockAccess.getBlockId(par2 - 1, par3, par4);
/*  391 */       int var15 = par1IBlockAccess.getBlockMetadata(par2 - 1, par3, par4);
/*      */       
/*  393 */       if (isBlockStairsID(var14) && (var5 & 0x4) == (var15 & 0x4)) {
/*      */         
/*  395 */         int var16 = var15 & 0x3;
/*      */         
/*  397 */         if (var16 == 3 && !isBlockStairsDirection(par1IBlockAccess, par2, par3, par4 - 1, var5))
/*      */         {
/*  399 */           var11 = 0.0F;
/*  400 */           var12 = 0.5F;
/*  401 */           var13 = true;
/*      */         }
/*  403 */         else if (var16 == 2 && !isBlockStairsDirection(par1IBlockAccess, par2, par3, par4 + 1, var5))
/*      */         {
/*  405 */           var11 = 0.5F;
/*  406 */           var12 = 1.0F;
/*  407 */           var13 = true;
/*      */         }
/*      */       
/*      */       } 
/*  411 */     } else if (var6 == 1) {
/*      */       
/*  413 */       int var14 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4);
/*  414 */       int var15 = par1IBlockAccess.getBlockMetadata(par2 + 1, par3, par4);
/*      */       
/*  416 */       if (isBlockStairsID(var14) && (var5 & 0x4) == (var15 & 0x4)) {
/*      */         
/*  418 */         var9 = 0.5F;
/*  419 */         var10 = 1.0F;
/*  420 */         int var16 = var15 & 0x3;
/*      */         
/*  422 */         if (var16 == 3 && !isBlockStairsDirection(par1IBlockAccess, par2, par3, par4 - 1, var5))
/*      */         {
/*  424 */           var11 = 0.0F;
/*  425 */           var12 = 0.5F;
/*  426 */           var13 = true;
/*      */         }
/*  428 */         else if (var16 == 2 && !isBlockStairsDirection(par1IBlockAccess, par2, par3, par4 + 1, var5))
/*      */         {
/*  430 */           var11 = 0.5F;
/*  431 */           var12 = 1.0F;
/*  432 */           var13 = true;
/*      */         }
/*      */       
/*      */       } 
/*  436 */     } else if (var6 == 2) {
/*      */       
/*  438 */       int var14 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1);
/*  439 */       int var15 = par1IBlockAccess.getBlockMetadata(par2, par3, par4 - 1);
/*      */       
/*  441 */       if (isBlockStairsID(var14) && (var5 & 0x4) == (var15 & 0x4)) {
/*      */         
/*  443 */         var11 = 0.0F;
/*  444 */         var12 = 0.5F;
/*  445 */         int var16 = var15 & 0x3;
/*      */         
/*  447 */         if (var16 == 1 && !isBlockStairsDirection(par1IBlockAccess, par2 - 1, par3, par4, var5))
/*      */         {
/*  449 */           var13 = true;
/*      */         }
/*  451 */         else if (var16 == 0 && !isBlockStairsDirection(par1IBlockAccess, par2 + 1, par3, par4, var5))
/*      */         {
/*  453 */           var9 = 0.5F;
/*  454 */           var10 = 1.0F;
/*  455 */           var13 = true;
/*      */         }
/*      */       
/*      */       } 
/*  459 */     } else if (var6 == 3) {
/*      */       
/*  461 */       int var14 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1);
/*  462 */       int var15 = par1IBlockAccess.getBlockMetadata(par2, par3, par4 + 1);
/*      */       
/*  464 */       if (isBlockStairsID(var14) && (var5 & 0x4) == (var15 & 0x4)) {
/*      */         
/*  466 */         int var16 = var15 & 0x3;
/*      */         
/*  468 */         if (var16 == 1 && !isBlockStairsDirection(par1IBlockAccess, par2 - 1, par3, par4, var5)) {
/*      */           
/*  470 */           var13 = true;
/*      */         }
/*  472 */         else if (var16 == 0 && !isBlockStairsDirection(par1IBlockAccess, par2 + 1, par3, par4, var5)) {
/*      */           
/*  474 */           var9 = 0.5F;
/*  475 */           var10 = 1.0F;
/*  476 */           var13 = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  481 */     if (var13)
/*      */     {
/*  483 */       setBlockBoundsForCurrentThread(var9, var7, var11, var10, var8, var12);
/*      */     }
/*      */     
/*  486 */     return var13;
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
/*      */ 
/*      */   
/*      */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  659 */     this.modelBlock.randomDisplayTick(par1World, par2, par3, par4, par5Random);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
/*  667 */     this.modelBlock.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
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
/*      */   public int getMixedBrightnessForBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  688 */     return this.modelBlock.getMixedBrightnessForBlock(par1IBlockAccess, par2, par3, par4);
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
/*      */   public float getBlockBrightness(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  701 */     return this.modelBlock.getBlockBrightness(par1IBlockAccess, par2, par3, par4);
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
/*      */   public float getExplosionResistance(Explosion explosion) {
/*  714 */     return this.modelBlock.getExplosionResistance(explosion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRenderBlockPass() {
/*  722 */     return this.modelBlock.getRenderBlockPass();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getIcon(int par1, int par2) {
/*  730 */     return this.modelBlock.getIcon(par1, this.modelBlockMetadata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int tickRate(World par1World) {
/*  738 */     return this.modelBlock.tickRate(par1World);
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
/*      */   public void velocityToAddToEntity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {
/*  754 */     this.modelBlock.velocityToAddToEntity(par1World, par2, par3, par4, par5Entity, par6Vec3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCollidable() {
/*  762 */     return this.modelBlock.isCollidable();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCollideCheck(int par1, boolean par2) {
/*  771 */     return this.modelBlock.canCollideCheck(par1, par2);
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
/*      */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/*  784 */     return this.modelBlock.isLegalAt(world, x, y, z, metadata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/*  792 */     onNeighborBlockChange(par1World, par2, par3, par4, 0);
/*  793 */     this.modelBlock.onBlockAdded(par1World, par2, par3, par4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/*  803 */     this.modelBlock.breakBlock(par1World, par2, par3, par4, par5, par6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/*  811 */     this.modelBlock.onEntityWalking(par1World, par2, par3, par4, par5Entity);
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
/*      */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  824 */     return this.modelBlock.updateTick(world, x, y, z, random);
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
/*      */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  837 */     return this.modelBlock.onBlockActivated(world, x, y, z, player, EnumFace.BOTTOM, 0.0F, 0.0F, 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {
/*  845 */     this.modelBlock.onBlockDestroyedByExplosion(par1World, par2, par3, par4, par5Explosion);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*      */     int metadata;
/*  883 */     EnumDirection direction = entity.getDirectionFromYaw();
/*      */     
/*  885 */     if (direction.isEast()) {
/*  886 */       metadata = 0;
/*  887 */     } else if (direction.isWest()) {
/*  888 */       metadata = 1;
/*  889 */     } else if (direction.isSouth()) {
/*  890 */       metadata = 2;
/*      */     } else {
/*  892 */       metadata = 3;
/*      */     } 
/*  894 */     if (face == EnumFace.BOTTOM || (face != EnumFace.TOP && offset_y > 0.5F)) {
/*  895 */       metadata |= 0x4;
/*      */     }
/*  897 */     return metadata;
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
/*      */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/* 1008 */     AxisAlignedBB[] multiple_bounds = new AxisAlignedBB[3];
/*      */     
/* 1010 */     AxisAlignedBB original_bounds = getBoundsFromPool();
/*      */     
/* 1012 */     func_82541_d(world, x, y, z);
/* 1013 */     multiple_bounds[0] = getBoundsFromPool();
/*      */     
/* 1015 */     boolean has_extra_corner = func_82542_g(world, x, y, z);
/* 1016 */     multiple_bounds[1] = getBoundsFromPool();
/*      */     
/* 1018 */     if (has_extra_corner && func_82544_h(world, x, y, z)) {
/* 1019 */       multiple_bounds[2] = getBoundsFromPool();
/*      */     }
/* 1021 */     setBlockBoundsForCurrentThread(original_bounds);
/*      */     
/* 1023 */     return multiple_bounds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerIcons(IconRegister par1IconRegister) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 1034 */     if (info.wasExploded()) {
/* 1035 */       return this.modelBlock.dropBlockAsEntityItem(info);
/*      */     }
/* 1037 */     return super.dropBlockAsEntityItem(info);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 1042 */     return isFaceFlatAndSolid(block_access.getBlockMetadata(x, y, z), EnumFace.get(side).getOpposite());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
/* 1052 */     if (metadata == 0)
/* 1053 */       return (face.isEast() || face.isBottom()); 
/* 1054 */     if (metadata == 1)
/* 1055 */       return (face.isWest() || face.isBottom()); 
/* 1056 */     if (metadata == 2)
/* 1057 */       return (face.isSouth() || face.isBottom()); 
/* 1058 */     if (metadata == 3)
/* 1059 */       return (face.isNorth() || face.isBottom()); 
/* 1060 */     if (metadata == 4)
/* 1061 */       return (face.isEast() || face.isTop()); 
/* 1062 */     if (metadata == 5)
/* 1063 */       return (face.isWest() || face.isTop()); 
/* 1064 */     if (metadata == 6)
/* 1065 */       return (face.isSouth() || face.isTop()); 
/* 1066 */     if (metadata == 7) {
/* 1067 */       return (face.isNorth() || face.isTop());
/*      */     }
/* 1069 */     Minecraft.setErrorMessage("isFaceFlatAndSolid: unexpected metadata " + metadata);
/* 1070 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 1075 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 1080 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockStairs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */