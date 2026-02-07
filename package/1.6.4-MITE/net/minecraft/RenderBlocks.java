/*      */ package net.minecraft;
/*      */ 
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class RenderBlocks
/*      */ {
/*      */   public IBlockAccess blockAccess;
/*      */   private Icon overrideBlockTexture;
/*      */   private boolean flipTexture;
/*      */   private boolean renderAllFaces;
/*      */   public static boolean fancyGrass = true;
/*      */   public boolean useInventoryTint = true;
/*      */   private double renderMinX;
/*      */   private double renderMaxX;
/*      */   private double renderMinY;
/*      */   private double renderMaxY;
/*      */   private double renderMinZ;
/*      */   private double renderMaxZ;
/*      */   private boolean lockBlockBounds;
/*      */   private boolean partialRenderBounds;
/*      */   private final Minecraft minecraftRB;
/*      */   private int uvRotateEast;
/*      */   private int uvRotateWest;
/*      */   private int uvRotateSouth;
/*      */   private int uvRotateNorth;
/*      */   private int uvRotateTop;
/*      */   private int uvRotateBottom;
/*      */   private boolean enableAO;
/*      */   private float aoLightValueScratchXYZNNN;
/*      */   private float aoLightValueScratchXYNN;
/*      */   private float aoLightValueScratchXYZNNP;
/*      */   private float aoLightValueScratchYZNN;
/*      */   private float aoLightValueScratchYZNP;
/*      */   private float aoLightValueScratchXYZPNN;
/*      */   private float aoLightValueScratchXYPN;
/*      */   private float aoLightValueScratchXYZPNP;
/*      */   private float aoLightValueScratchXYZNPN;
/*      */   private float aoLightValueScratchXYNP;
/*      */   private float aoLightValueScratchXYZNPP;
/*      */   private float aoLightValueScratchYZPN;
/*      */   private float aoLightValueScratchXYZPPN;
/*      */   private float aoLightValueScratchXYPP;
/*      */   private float aoLightValueScratchYZPP;
/*      */   private float aoLightValueScratchXYZPPP;
/*      */   private float aoLightValueScratchXZNN;
/*      */   private float aoLightValueScratchXZPN;
/*      */   private float aoLightValueScratchXZNP;
/*      */   private float aoLightValueScratchXZPP;
/*      */   private int aoBrightnessXYZNNN;
/*      */   private int aoBrightnessXYNN;
/*      */   private int aoBrightnessXYZNNP;
/*      */   private int aoBrightnessYZNN;
/*      */   private int aoBrightnessYZNP;
/*      */   private int aoBrightnessXYZPNN;
/*      */   private int aoBrightnessXYPN;
/*      */   private int aoBrightnessXYZPNP;
/*      */   private int aoBrightnessXYZNPN;
/*      */   private int aoBrightnessXYNP;
/*      */   private int aoBrightnessXYZNPP;
/*      */   private int aoBrightnessYZPN;
/*      */   private int aoBrightnessXYZPPN;
/*      */   private int aoBrightnessXYPP;
/*      */   private int aoBrightnessYZPP;
/*      */   private int aoBrightnessXYZPPP;
/*      */   private int aoBrightnessXZNN;
/*      */   private int aoBrightnessXZPN;
/*      */   private int aoBrightnessXZNP;
/*      */   private int aoBrightnessXZPP;
/*      */   private int brightnessTopLeft;
/*      */   private int brightnessBottomLeft;
/*      */   private int brightnessBottomRight;
/*      */   private int brightnessTopRight;
/*      */   private float colorRedTopLeft;
/*      */   private float colorRedBottomLeft;
/*      */   private float colorRedBottomRight;
/*      */   private float colorRedTopRight;
/*      */   private float colorGreenTopLeft;
/*      */   private float colorGreenBottomLeft;
/*      */   private float colorGreenBottomRight;
/*      */   private float colorGreenTopRight;
/*      */   private float colorBlueTopLeft;
/*      */   private float colorBlueBottomLeft;
/*      */   private float colorBlueBottomRight;
/*      */   private float colorBlueTopRight;
/*  275 */   double[] x = new double[4];
/*  276 */   double[] y = new double[4];
/*  277 */   double[] z = new double[4];
/*  278 */   double[] u = new double[4];
/*  279 */   double[] v = new double[4];
/*  280 */   float[] r = new float[4];
/*  281 */   float[] g = new float[4];
/*  282 */   float[] b = new float[4];
/*  283 */   int[] brightness = new int[4];
/*      */ 
/*      */   
/*      */   public final int thread_index;
/*      */ 
/*      */ 
/*      */   
/*      */   public RenderBlocks(IBlockAccess par1IBlockAccess) {
/*  291 */     this.blockAccess = par1IBlockAccess;
/*  292 */     this.minecraftRB = Minecraft.getMinecraft();
/*      */     
/*  294 */     this.thread_index = Minecraft.getThreadIndex();
/*      */   }
/*      */ 
/*      */   
/*      */   public RenderBlocks() {
/*  299 */     this.minecraftRB = Minecraft.getMinecraft();
/*      */     
/*  301 */     this.thread_index = Minecraft.getThreadIndex();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOverrideBlockTexture(Icon par1Icon) {
/*  309 */     this.overrideBlockTexture = par1Icon;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearOverrideBlockTexture() {
/*  317 */     this.overrideBlockTexture = null;
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
/*      */   public void setRenderBounds(double par1, double par3, double par5, double par7, double par9, double par11) {
/*  330 */     if (!this.lockBlockBounds) {
/*      */       
/*  332 */       this.renderMinX = par1;
/*  333 */       this.renderMaxX = par7;
/*  334 */       this.renderMinY = par3;
/*  335 */       this.renderMaxY = par9;
/*  336 */       this.renderMinZ = par5;
/*  337 */       this.renderMaxZ = par11;
/*  338 */       this.partialRenderBounds = (this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRenderBoundsForStandardFormBlock() {
/*  344 */     if (this.lockBlockBounds) {
/*      */       return;
/*      */     }
/*  347 */     this.renderMinX = 0.0D;
/*  348 */     this.renderMaxX = 1.0D;
/*  349 */     this.renderMinY = 0.0D;
/*  350 */     this.renderMaxY = 1.0D;
/*  351 */     this.renderMinZ = 0.0D;
/*  352 */     this.renderMaxZ = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  361 */     this.partialRenderBounds = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRenderBoundsForNonStandardFormBlock(Block block) {
/*  369 */     if (this.lockBlockBounds) {
/*      */       return;
/*      */     }
/*  372 */     this.renderMinX = block.minX[this.thread_index];
/*  373 */     this.renderMaxX = block.maxX[this.thread_index];
/*  374 */     this.renderMinY = block.minY[this.thread_index];
/*  375 */     this.renderMaxY = block.maxY[this.thread_index];
/*  376 */     this.renderMinZ = block.minZ[this.thread_index];
/*  377 */     this.renderMaxZ = block.maxZ[this.thread_index];
/*      */     
/*  379 */     this.partialRenderBounds = (this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void XXXsetRenderBoundsFromBlock(Block par1Block) {
/*  387 */     if (!this.lockBlockBounds)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  407 */       if (par1Block.isAlwaysStandardFormCube()) {
/*      */         
/*  409 */         this.renderMinX = 0.0D;
/*  410 */         this.renderMaxX = 1.0D;
/*  411 */         this.renderMinY = 0.0D;
/*  412 */         this.renderMaxY = 1.0D;
/*  413 */         this.renderMinZ = 0.0D;
/*  414 */         this.renderMaxZ = 1.0D;
/*      */         
/*  416 */         this.partialRenderBounds = false;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  422 */         this.renderMinX = par1Block.minX[this.thread_index];
/*  423 */         this.renderMaxX = par1Block.maxX[this.thread_index];
/*  424 */         this.renderMinY = par1Block.minY[this.thread_index];
/*  425 */         this.renderMaxY = par1Block.maxY[this.thread_index];
/*  426 */         this.renderMinZ = par1Block.minZ[this.thread_index];
/*  427 */         this.renderMaxZ = par1Block.maxZ[this.thread_index];
/*      */         
/*  429 */         this.partialRenderBounds = (this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D));
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void overrideBlockBounds(double par1, double par3, double par5, double par7, double par9, double par11) {
/*  440 */     this.renderMinX = par1;
/*  441 */     this.renderMaxX = par7;
/*  442 */     this.renderMinY = par3;
/*  443 */     this.renderMaxY = par9;
/*  444 */     this.renderMinZ = par5;
/*  445 */     this.renderMaxZ = par11;
/*  446 */     this.lockBlockBounds = true;
/*  447 */     this.partialRenderBounds = (this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unlockBlockBounds() {
/*  455 */     this.lockBlockBounds = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderBlockUsingTexture(Block par1Block, int par2, int par3, int par4, Icon par5Icon) {
/*  463 */     setOverrideBlockTexture(par5Icon);
/*  464 */     renderBlockByRenderType(par1Block, par2, par3, par4);
/*  465 */     clearOverrideBlockTexture();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderBlockAllFaces(Block par1Block, int par2, int par3, int par4) {
/*  473 */     this.renderAllFaces = true;
/*  474 */     renderBlockByRenderType(par1Block, par2, par3, par4);
/*  475 */     this.renderAllFaces = false;
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
/*      */   public boolean renderBlockByRenderType(Block par1Block, int par2, int par3, int par4) {
/*  488 */     int var5 = par1Block.getRenderType();
/*      */     
/*  490 */     if (var5 == -1)
/*      */     {
/*  492 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  496 */     if (this.overrideBlockTexture != null)
/*      */     {
/*  498 */       if (var5 == 22) {
/*  499 */         var5 = 0;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  505 */     if (par1Block.isAlwaysStandardFormCube()) {
/*      */       
/*  507 */       setRenderBoundsForStandardFormBlock();
/*      */     }
/*      */     else {
/*      */       
/*  511 */       par1Block.setBlockBoundsBasedOnStateAndNeighbors(this.blockAccess, par2, par3, par4);
/*  512 */       setRenderBoundsForNonStandardFormBlock(par1Block);
/*      */     } 
/*      */     
/*  515 */     return (var5 == 0) ? renderStandardBlock(par1Block, par2, par3, par4) : ((var5 == 4) ? renderBlockFluids(par1Block, par2, par3, par4) : ((var5 == 31) ? renderBlockLog(par1Block, par2, par3, par4) : ((var5 == 1) ? renderCrossedSquares(par1Block, par2, par3, par4) : ((var5 == 2) ? renderBlockTorch(par1Block, par2, par3, par4) : ((var5 == 20) ? renderBlockVine(par1Block, par2, par3, par4) : ((var5 == 11) ? renderBlockFence((BlockFence)par1Block, par2, par3, par4) : ((var5 == 39) ? renderBlockQuartz(par1Block, par2, par3, par4) : ((var5 == 5) ? renderBlockRedstoneWire(par1Block, par2, par3, par4) : ((var5 == 13) ? renderBlockCactus(par1Block, par2, par3, par4) : ((var5 == 9) ? renderBlockMinecartTrack((BlockRailBase)par1Block, par2, par3, par4) : ((var5 == 19) ? renderBlockStem(par1Block, par2, par3, par4) : ((var5 == 23) ? renderBlockLilyPad(par1Block, par2, par3, par4) : ((var5 == 6) ? renderBlockCrops(par1Block, par2, par3, par4) : ((var5 == 3) ? renderBlockFire((BlockFire)par1Block, par2, par3, par4) : ((var5 == 8) ? renderBlockLadder(par1Block, par2, par3, par4) : ((var5 == 7) ? renderBlockDoor(par1Block, par2, par3, par4) : ((var5 == 10) ? renderBlockStairs((BlockStairs)par1Block, par2, par3, par4) : ((var5 == 27) ? renderBlockDragonEgg((BlockDragonEgg)par1Block, par2, par3, par4) : ((var5 == 32) ? renderBlockWall((BlockWall)par1Block, par2, par3, par4) : ((var5 == 12) ? renderBlockLever(par1Block, par2, par3, par4) : ((var5 == 29) ? renderBlockTripWireSource(par1Block, par2, par3, par4) : ((var5 == 30) ? renderBlockTripWire(par1Block, par2, par3, par4) : ((var5 == 14) ? renderBlockBed(par1Block, par2, par3, par4) : ((var5 == 15) ? renderBlockRepeater((BlockRedstoneRepeater)par1Block, par2, par3, par4) : ((var5 == 36) ? renderBlockRedstoneLogic((BlockRedstoneLogic)par1Block, par2, par3, par4) : ((var5 == 37) ? renderBlockComparator((BlockComparator)par1Block, par2, par3, par4) : ((var5 == 16) ? renderPistonBase(par1Block, par2, par3, par4, false) : ((var5 == 17) ? renderPistonExtension(par1Block, par2, par3, par4, true) : ((var5 == 18) ? renderBlockPane((BlockPane)par1Block, par2, par3, par4) : ((var5 == 21) ? renderBlockFenceGate((BlockFenceGate)par1Block, par2, par3, par4) : ((var5 == 24) ? renderBlockCauldron((BlockCauldron)par1Block, par2, par3, par4) : ((var5 == 33) ? renderBlockFlowerpot((BlockFlowerPot)par1Block, par2, par3, par4) : ((var5 == 35) ? renderBlockAnvil((BlockAnvil)par1Block, par2, par3, par4) : ((var5 == 25) ? renderBlockBrewingStand((BlockBrewingStand)par1Block, par2, par3, par4) : ((var5 == 26) ? renderBlockEndPortalFrame((BlockEndPortalFrame)par1Block, par2, par3, par4) : ((var5 == 28) ? renderBlockCocoa((BlockCocoa)par1Block, par2, par3, par4) : ((var5 == 34) ? renderBlockBeacon((BlockBeacon)par1Block, par2, par3, par4) : ((var5 == 38) ? renderBlockHopper((BlockHopper)par1Block, par2, par3, par4) : false))))))))))))))))))))))))))))))))))))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockEndPortalFrame(BlockEndPortalFrame par1BlockEndPortalFrame, int par2, int par3, int par4) {
/*  524 */     int var5 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*  525 */     int var6 = var5 & 0x3;
/*      */     
/*  527 */     if (var6 == 0) {
/*      */       
/*  529 */       this.uvRotateTop = 3;
/*      */     }
/*  531 */     else if (var6 == 3) {
/*      */       
/*  533 */       this.uvRotateTop = 1;
/*      */     }
/*  535 */     else if (var6 == 1) {
/*      */       
/*  537 */       this.uvRotateTop = 2;
/*      */     } 
/*      */     
/*  540 */     if (!BlockEndPortalFrame.isEnderEyeInserted(var5)) {
/*      */       
/*  542 */       setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
/*  543 */       renderStandardBlock(par1BlockEndPortalFrame, par2, par3, par4);
/*  544 */       this.uvRotateTop = 0;
/*  545 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  549 */     this.renderAllFaces = true;
/*  550 */     setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
/*  551 */     renderStandardBlock(par1BlockEndPortalFrame, par2, par3, par4);
/*  552 */     setOverrideBlockTexture(par1BlockEndPortalFrame.func_94398_p());
/*  553 */     setRenderBounds(0.25D, 0.8125D, 0.25D, 0.75D, 1.0D, 0.75D);
/*  554 */     renderStandardBlock(par1BlockEndPortalFrame, par2, par3, par4);
/*  555 */     this.renderAllFaces = false;
/*  556 */     clearOverrideBlockTexture();
/*  557 */     this.uvRotateTop = 0;
/*  558 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockBed(Block par1Block, int par2, int par3, int par4) {
/*  567 */     Tessellator var5 = Tessellator.instance;
/*  568 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*  569 */     int var7 = BlockBed.j(var6);
/*  570 */     boolean var8 = BlockBed.isBlockHeadOfBed(var6);
/*  571 */     float var9 = 0.5F;
/*  572 */     float var10 = 1.0F;
/*  573 */     float var11 = 0.8F;
/*  574 */     float var12 = 0.6F;
/*  575 */     int var25 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4);
/*  576 */     var5.setBrightness(var25);
/*  577 */     var5.setColorOpaque_F(var9, var9, var9);
/*  578 */     Icon var27 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 0);
/*  579 */     double var28 = var27.getMinU();
/*  580 */     double var30 = var27.getMaxU();
/*  581 */     double var32 = var27.getMinV();
/*  582 */     double var34 = var27.getMaxV();
/*  583 */     double var36 = par2 + this.renderMinX;
/*  584 */     double var38 = par2 + this.renderMaxX;
/*  585 */     double var40 = par3 + this.renderMinY + 0.1875D;
/*  586 */     double var42 = par4 + this.renderMinZ;
/*  587 */     double var44 = par4 + this.renderMaxZ;
/*  588 */     var5.addVertexWithUV(var36, var40, var44, var28, var34);
/*  589 */     var5.addVertexWithUV(var36, var40, var42, var28, var32);
/*  590 */     var5.addVertexWithUV(var38, var40, var42, var30, var32);
/*  591 */     var5.addVertexWithUV(var38, var40, var44, var30, var34);
/*  592 */     var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4));
/*  593 */     var5.setColorOpaque_F(var10, var10, var10);
/*  594 */     var27 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 1);
/*  595 */     var28 = var27.getMinU();
/*  596 */     var30 = var27.getMaxU();
/*  597 */     var32 = var27.getMinV();
/*  598 */     var34 = var27.getMaxV();
/*  599 */     var36 = var28;
/*  600 */     var38 = var30;
/*  601 */     var40 = var32;
/*  602 */     var42 = var32;
/*  603 */     var44 = var28;
/*  604 */     double var46 = var30;
/*  605 */     double var48 = var34;
/*  606 */     double var50 = var34;
/*      */     
/*  608 */     if (var7 == 0) {
/*      */       
/*  610 */       var38 = var28;
/*  611 */       var40 = var34;
/*  612 */       var44 = var30;
/*  613 */       var50 = var32;
/*      */     }
/*  615 */     else if (var7 == 2) {
/*      */       
/*  617 */       var36 = var30;
/*  618 */       var42 = var34;
/*  619 */       var46 = var28;
/*  620 */       var48 = var32;
/*      */     }
/*  622 */     else if (var7 == 3) {
/*      */       
/*  624 */       var36 = var30;
/*  625 */       var42 = var34;
/*  626 */       var46 = var28;
/*  627 */       var48 = var32;
/*  628 */       var38 = var28;
/*  629 */       var40 = var34;
/*  630 */       var44 = var30;
/*  631 */       var50 = var32;
/*      */     } 
/*      */     
/*  634 */     double var52 = par2 + this.renderMinX;
/*  635 */     double var54 = par2 + this.renderMaxX;
/*  636 */     double var56 = par3 + this.renderMaxY;
/*  637 */     double var58 = par4 + this.renderMinZ;
/*  638 */     double var60 = par4 + this.renderMaxZ;
/*  639 */     var5.addVertexWithUV(var54, var56, var60, var44, var48);
/*  640 */     var5.addVertexWithUV(var54, var56, var58, var36, var40);
/*  641 */     var5.addVertexWithUV(var52, var56, var58, var38, var42);
/*  642 */     var5.addVertexWithUV(var52, var56, var60, var46, var50);
/*  643 */     int var62 = Direction.directionToFacing[var7];
/*      */     
/*  645 */     if (var8)
/*      */     {
/*  647 */       var62 = Direction.directionToFacing[Direction.rotateOpposite[var7]];
/*      */     }
/*      */     
/*  650 */     byte var63 = 4;
/*      */     
/*  652 */     switch (var7) {
/*      */       
/*      */       case 0:
/*  655 */         var63 = 5;
/*      */         break;
/*      */       
/*      */       case 1:
/*  659 */         var63 = 3;
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/*  666 */         var63 = 2;
/*      */         break;
/*      */     } 
/*  669 */     if (var62 != 2 && (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 - 1, 2))) {
/*      */       
/*  671 */       var5.setBrightness((this.renderMinZ > 0.0D) ? var25 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1));
/*  672 */       var5.setColorOpaque_F(var11, var11, var11);
/*  673 */       this.flipTexture = (var63 == 2);
/*  674 */       renderFaceZNeg(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 2));
/*      */     } 
/*      */     
/*  677 */     if (var62 != 3 && (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 + 1, 3))) {
/*      */       
/*  679 */       var5.setBrightness((this.renderMaxZ < 1.0D) ? var25 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1));
/*  680 */       var5.setColorOpaque_F(var11, var11, var11);
/*  681 */       this.flipTexture = (var63 == 3);
/*  682 */       renderFaceZPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 3));
/*      */     } 
/*      */     
/*  685 */     if (var62 != 4 && (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2 - 1, par3, par4, 4))) {
/*      */       
/*  687 */       var5.setBrightness((this.renderMinZ > 0.0D) ? var25 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4));
/*  688 */       var5.setColorOpaque_F(var12, var12, var12);
/*  689 */       this.flipTexture = (var63 == 4);
/*  690 */       renderFaceXNeg(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 4));
/*      */     } 
/*      */     
/*  693 */     if (var62 != 5 && (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2 + 1, par3, par4, 5))) {
/*      */       
/*  695 */       var5.setBrightness((this.renderMaxZ < 1.0D) ? var25 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4));
/*  696 */       var5.setColorOpaque_F(var12, var12, var12);
/*  697 */       this.flipTexture = (var63 == 5);
/*  698 */       renderFaceXPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 5));
/*      */     } 
/*      */     
/*  701 */     this.flipTexture = false;
/*  702 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockBrewingStand(BlockBrewingStand par1BlockBrewingStand, int par2, int par3, int par4) {
/*  710 */     setRenderBounds(0.4375D, 0.0D, 0.4375D, 0.5625D, 0.875D, 0.5625D);
/*  711 */     renderStandardBlock(par1BlockBrewingStand, par2, par3, par4);
/*  712 */     setOverrideBlockTexture(par1BlockBrewingStand.getBrewingStandIcon());
/*  713 */     this.renderAllFaces = true;
/*  714 */     setRenderBounds(0.5625D, 0.0D, 0.3125D, 0.9375D, 0.125D, 0.6875D);
/*  715 */     renderStandardBlock(par1BlockBrewingStand, par2, par3, par4);
/*  716 */     setRenderBounds(0.125D, 0.0D, 0.0625D, 0.5D, 0.125D, 0.4375D);
/*  717 */     renderStandardBlock(par1BlockBrewingStand, par2, par3, par4);
/*  718 */     setRenderBounds(0.125D, 0.0D, 0.5625D, 0.5D, 0.125D, 0.9375D);
/*  719 */     renderStandardBlock(par1BlockBrewingStand, par2, par3, par4);
/*  720 */     this.renderAllFaces = false;
/*  721 */     clearOverrideBlockTexture();
/*  722 */     Tessellator var5 = Tessellator.instance;
/*  723 */     var5.setBrightness(par1BlockBrewingStand.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/*  724 */     float var6 = 1.0F;
/*  725 */     int var7 = par1BlockBrewingStand.colorMultiplier(this.blockAccess, par2, par3, par4);
/*  726 */     float var8 = (var7 >> 16 & 0xFF) / 255.0F;
/*  727 */     float var9 = (var7 >> 8 & 0xFF) / 255.0F;
/*  728 */     float var10 = (var7 & 0xFF) / 255.0F;
/*      */     
/*  730 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/*  732 */       float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
/*  733 */       float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
/*  734 */       float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
/*  735 */       var8 = var11;
/*  736 */       var9 = var12;
/*  737 */       var10 = var13;
/*      */     } 
/*      */     
/*  740 */     var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  748 */     Icon var32 = (this.overrideBlockTexture == null) ? getBlockIconFromSideAndMetadata(par1BlockBrewingStand, 0, 0) : this.overrideBlockTexture;
/*      */     
/*  750 */     double var33 = var32.getMinV();
/*  751 */     double var14 = var32.getMaxV();
/*  752 */     int var16 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*      */     
/*  754 */     for (int var17 = 0; var17 < 3; var17++) {
/*      */       
/*  756 */       double var18 = var17 * Math.PI * 2.0D / 3.0D + 1.5707963267948966D;
/*  757 */       double var20 = var32.getInterpolatedU(8.0D);
/*  758 */       double var22 = var32.getMaxU();
/*      */       
/*  760 */       if ((var16 & 1 << var17) != 0)
/*      */       {
/*  762 */         var22 = var32.getMinU();
/*      */       }
/*      */       
/*  765 */       double var24 = par2 + 0.5D;
/*  766 */       double var26 = par2 + 0.5D + Math.sin(var18) * 8.0D / 16.0D;
/*  767 */       double var28 = par4 + 0.5D;
/*  768 */       double var30 = par4 + 0.5D + Math.cos(var18) * 8.0D / 16.0D;
/*  769 */       var5.addVertexWithUV(var24, (par3 + 1), var28, var20, var33);
/*  770 */       var5.addVertexWithUV(var24, (par3 + 0), var28, var20, var14);
/*  771 */       var5.addVertexWithUV(var26, (par3 + 0), var30, var22, var14);
/*  772 */       var5.addVertexWithUV(var26, (par3 + 1), var30, var22, var33);
/*  773 */       var5.addVertexWithUV(var26, (par3 + 1), var30, var22, var33);
/*  774 */       var5.addVertexWithUV(var26, (par3 + 0), var30, var22, var14);
/*  775 */       var5.addVertexWithUV(var24, (par3 + 0), var28, var20, var14);
/*  776 */       var5.addVertexWithUV(var24, (par3 + 1), var28, var20, var33);
/*      */     } 
/*      */ 
/*      */     
/*  780 */     par1BlockBrewingStand.setBlockBoundsForItemRender(0);
/*  781 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockCauldron(BlockCauldron par1BlockCauldron, int par2, int par3, int par4) {
/*  789 */     renderStandardBlock(par1BlockCauldron, par2, par3, par4);
/*  790 */     Tessellator var5 = Tessellator.instance;
/*  791 */     var5.setBrightness(par1BlockCauldron.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/*  792 */     float var6 = 1.0F;
/*  793 */     int var7 = par1BlockCauldron.colorMultiplier(this.blockAccess, par2, par3, par4);
/*  794 */     float var8 = (var7 >> 16 & 0xFF) / 255.0F;
/*  795 */     float var9 = (var7 >> 8 & 0xFF) / 255.0F;
/*  796 */     float var10 = (var7 & 0xFF) / 255.0F;
/*      */ 
/*      */     
/*  799 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/*  801 */       float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
/*  802 */       float f1 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
/*  803 */       float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
/*  804 */       var8 = var11;
/*  805 */       var9 = f1;
/*  806 */       var10 = var13;
/*      */     } 
/*      */     
/*  809 */     var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
/*  810 */     Icon var16 = par1BlockCauldron.getBlockTextureFromSide(2);
/*  811 */     float var12 = 0.125F;
/*  812 */     renderFaceXPos(par1BlockCauldron, (par2 - 1.0F + var12), par3, par4, var16);
/*  813 */     renderFaceXNeg(par1BlockCauldron, (par2 + 1.0F - var12), par3, par4, var16);
/*  814 */     renderFaceZPos(par1BlockCauldron, par2, par3, (par4 - 1.0F + var12), var16);
/*  815 */     renderFaceZNeg(par1BlockCauldron, par2, par3, (par4 + 1.0F - var12), var16);
/*  816 */     Icon var17 = BlockCauldron.getCauldronIcon("inner");
/*  817 */     renderFaceYPos(par1BlockCauldron, par2, (par3 - 1.0F + 0.25F), par4, var17);
/*  818 */     renderFaceYNeg(par1BlockCauldron, par2, (par3 + 1.0F - 0.75F), par4, var17);
/*  819 */     int var14 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*      */     
/*  821 */     if (var14 > 0) {
/*      */       
/*  823 */       Icon var15 = BlockFluid.getFluidIcon("water_still");
/*      */       
/*  825 */       if (var14 > 3)
/*      */       {
/*  827 */         var14 = 3;
/*      */       }
/*      */       
/*  830 */       renderFaceYPos(par1BlockCauldron, par2, (par3 - 1.0F + (6.0F + var14 * 3.0F) / 16.0F), par4, var15);
/*      */     } 
/*      */     
/*  833 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockFlowerpot(BlockFlowerPot par1BlockFlowerPot, int par2, int par3, int par4) {
/*  841 */     renderStandardBlock(par1BlockFlowerPot, par2, par3, par4);
/*  842 */     Tessellator var5 = Tessellator.instance;
/*  843 */     var5.setBrightness(par1BlockFlowerPot.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/*  844 */     float var6 = 1.0F;
/*  845 */     int var7 = par1BlockFlowerPot.colorMultiplier(this.blockAccess, par2, par3, par4);
/*  846 */     Icon var8 = getBlockIconFromSide(par1BlockFlowerPot, 0);
/*  847 */     float var9 = (var7 >> 16 & 0xFF) / 255.0F;
/*  848 */     float var10 = (var7 >> 8 & 0xFF) / 255.0F;
/*  849 */     float var11 = (var7 & 0xFF) / 255.0F;
/*      */ 
/*      */ 
/*      */     
/*  853 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/*  855 */       float f1 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
/*  856 */       float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
/*  857 */       float var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
/*  858 */       var9 = f1;
/*  859 */       var10 = var13;
/*  860 */       var11 = var14;
/*      */     } 
/*      */     
/*  863 */     var5.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
/*  864 */     float var12 = 0.1865F;
/*  865 */     renderFaceXPos(par1BlockFlowerPot, (par2 - 0.5F + var12), par3, par4, var8);
/*  866 */     renderFaceXNeg(par1BlockFlowerPot, (par2 + 0.5F - var12), par3, par4, var8);
/*  867 */     renderFaceZPos(par1BlockFlowerPot, par2, par3, (par4 - 0.5F + var12), var8);
/*  868 */     renderFaceZNeg(par1BlockFlowerPot, par2, par3, (par4 + 0.5F - var12), var8);
/*  869 */     renderFaceYPos(par1BlockFlowerPot, par2, (par3 - 0.5F + var12 + 0.1875F), par4, getBlockIcon(Block.dirt));
/*  870 */     int var19 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*      */     
/*  872 */     if (this.blockAccess.getBlockId(par2, par3, par4) == Block.flowerPotMulti.blockID) {
/*      */       
/*  874 */       if (var19 == 0) {
/*  875 */         return true;
/*      */       }
/*  877 */       float var14 = 0.0F;
/*  878 */       float var15 = 4.0F;
/*  879 */       float var16 = 0.0F;
/*      */ 
/*      */ 
/*      */       
/*  883 */       var5.addTranslation(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);
/*      */       
/*  885 */       renderBlockByRenderType(Block.plantRed, par2, par3, par4);
/*      */       
/*  887 */       var5.addTranslation(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
/*      */     
/*      */     }
/*  890 */     else if (var19 != 0) {
/*      */       
/*  892 */       float var14 = 0.0F;
/*  893 */       float var15 = 4.0F;
/*  894 */       float var16 = 0.0F;
/*      */       
/*  896 */       BlockPlant var17 = null;
/*      */       
/*  898 */       switch (var19) {
/*      */         
/*      */         case 1:
/*  901 */           var17 = Block.plantRed;
/*      */           break;
/*      */         
/*      */         case 2:
/*  905 */           var17 = Block.plantYellow;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 7:
/*  915 */           var17 = Block.mushroomRed;
/*      */           break;
/*      */         
/*      */         case 8:
/*  919 */           var17 = Block.mushroomBrown;
/*      */           break;
/*      */       } 
/*  922 */       var5.addTranslation(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);
/*      */       
/*  924 */       if (var17 != null) {
/*      */ 
/*      */         
/*  927 */         this.blockAccess.getWorld().setBlockMetadataWithNotify(par2, par3, par4, 0, 4);
/*      */         
/*  929 */         renderBlockByRenderType(var17, par2, par3, par4);
/*      */ 
/*      */         
/*  932 */         this.blockAccess.getWorld().setBlockMetadataWithNotify(par2, par3, par4, var19, 4);
/*      */       }
/*  934 */       else if (var19 == 9) {
/*      */         
/*  936 */         this.renderAllFaces = true;
/*  937 */         float var18 = 0.125F;
/*  938 */         setRenderBounds((0.5F - var18), 0.0D, (0.5F - var18), (0.5F + var18), 0.25D, (0.5F + var18));
/*  939 */         renderStandardBlock(Block.cactus, par2, par3, par4);
/*  940 */         setRenderBounds((0.5F - var18), 0.25D, (0.5F - var18), (0.5F + var18), 0.5D, (0.5F + var18));
/*  941 */         renderStandardBlock(Block.cactus, par2, par3, par4);
/*  942 */         setRenderBounds((0.5F - var18), 0.5D, (0.5F - var18), (0.5F + var18), 0.75D, (0.5F + var18));
/*  943 */         renderStandardBlock(Block.cactus, par2, par3, par4);
/*  944 */         this.renderAllFaces = false;
/*  945 */         setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */       }
/*  947 */       else if (var19 == 3) {
/*      */         
/*  949 */         drawCrossedSquares(Block.sapling, 0, par2, par3, par4, 0.75F);
/*      */       }
/*  951 */       else if (var19 == 5) {
/*      */         
/*  953 */         drawCrossedSquares(Block.sapling, 2, par2, par3, par4, 0.75F);
/*      */       }
/*  955 */       else if (var19 == 4) {
/*      */         
/*  957 */         drawCrossedSquares(Block.sapling, 1, par2, par3, par4, 0.75F);
/*      */       }
/*  959 */       else if (var19 == 6) {
/*      */         
/*  961 */         drawCrossedSquares(Block.sapling, 3, par2, par3, par4, 0.75F);
/*      */       }
/*  963 */       else if (var19 == 11) {
/*      */         
/*  965 */         var7 = Block.tallGrass.colorMultiplier(this.blockAccess, par2, par3, par4);
/*  966 */         var9 = (var7 >> 16 & 0xFF) / 255.0F;
/*  967 */         var10 = (var7 >> 8 & 0xFF) / 255.0F;
/*  968 */         var11 = (var7 & 0xFF) / 255.0F;
/*  969 */         var5.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
/*  970 */         drawCrossedSquares(Block.tallGrass, 2, par2, par3, par4, 0.75F);
/*      */       
/*      */       }
/*  973 */       else if (var19 == 10 || var19 == 12) {
/*      */ 
/*      */         
/*  976 */         drawCrossedSquares(Block.deadBush, (var19 == 12) ? 1 : 0, par2, par3, par4, 0.75F);
/*      */       } 
/*      */       
/*  979 */       var5.addTranslation(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
/*      */     } 
/*      */     
/*  982 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockAnvil(BlockAnvil par1BlockAnvil, int par2, int par3, int par4) {
/*  990 */     return renderBlockAnvilMetadata(par1BlockAnvil, par2, par3, par4, this.blockAccess.getBlockMetadata(par2, par3, par4));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockAnvilMetadata(BlockAnvil par1BlockAnvil, int par2, int par3, int par4, int par5) {
/*  998 */     Tessellator var6 = Tessellator.instance;
/*  999 */     var6.setBrightness(par1BlockAnvil.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1000 */     float var7 = 1.0F;
/* 1001 */     int var8 = par1BlockAnvil.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 1002 */     float var9 = (var8 >> 16 & 0xFF) / 255.0F;
/* 1003 */     float var10 = (var8 >> 8 & 0xFF) / 255.0F;
/* 1004 */     float var11 = (var8 & 0xFF) / 255.0F;
/*      */     
/* 1006 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/* 1008 */       float var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
/* 1009 */       float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
/* 1010 */       float var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
/* 1011 */       var9 = var12;
/* 1012 */       var10 = var13;
/* 1013 */       var11 = var14;
/*      */     } 
/*      */     
/* 1016 */     var6.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
/* 1017 */     return renderBlockAnvilOrient(par1BlockAnvil, par2, par3, par4, par5, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockAnvilOrient(BlockAnvil par1BlockAnvil, int par2, int par3, int par4, int par5, boolean par6) {
/* 1025 */     int var7 = par6 ? 0 : (par5 & 0x3);
/* 1026 */     boolean var8 = false;
/* 1027 */     float var9 = 0.0F;
/*      */     
/* 1029 */     switch (var7) {
/*      */       
/*      */       case 0:
/* 1032 */         this.uvRotateSouth = 2;
/* 1033 */         this.uvRotateNorth = 1;
/* 1034 */         this.uvRotateTop = 3;
/* 1035 */         this.uvRotateBottom = 3;
/*      */         break;
/*      */       
/*      */       case 1:
/* 1039 */         this.uvRotateEast = 1;
/* 1040 */         this.uvRotateWest = 2;
/* 1041 */         this.uvRotateTop = 2;
/* 1042 */         this.uvRotateBottom = 1;
/* 1043 */         var8 = true;
/*      */         break;
/*      */       
/*      */       case 2:
/* 1047 */         this.uvRotateSouth = 1;
/* 1048 */         this.uvRotateNorth = 2;
/*      */         break;
/*      */       
/*      */       case 3:
/* 1052 */         this.uvRotateEast = 2;
/* 1053 */         this.uvRotateWest = 1;
/* 1054 */         this.uvRotateTop = 1;
/* 1055 */         this.uvRotateBottom = 2;
/* 1056 */         var8 = true;
/*      */         break;
/*      */     } 
/* 1059 */     var9 = renderBlockAnvilRotate(par1BlockAnvil, par2, par3, par4, 0, var9, 0.75F, 0.25F, 0.75F, var8, par6, par5);
/* 1060 */     var9 = renderBlockAnvilRotate(par1BlockAnvil, par2, par3, par4, 1, var9, 0.5F, 0.0625F, 0.625F, var8, par6, par5);
/* 1061 */     var9 = renderBlockAnvilRotate(par1BlockAnvil, par2, par3, par4, 2, var9, 0.25F, 0.3125F, 0.5F, var8, par6, par5);
/* 1062 */     renderBlockAnvilRotate(par1BlockAnvil, par2, par3, par4, 3, var9, 0.625F, 0.375F, 1.0F, var8, par6, par5);
/* 1063 */     setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 1064 */     this.uvRotateEast = 0;
/* 1065 */     this.uvRotateWest = 0;
/* 1066 */     this.uvRotateSouth = 0;
/* 1067 */     this.uvRotateNorth = 0;
/* 1068 */     this.uvRotateTop = 0;
/* 1069 */     this.uvRotateBottom = 0;
/* 1070 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float renderBlockAnvilRotate(BlockAnvil par1BlockAnvil, int par2, int par3, int par4, int par5, float par6, float par7, float par8, float par9, boolean par10, boolean par11, int par12) {
/* 1078 */     if (par10) {
/*      */       
/* 1080 */       float var13 = par7;
/* 1081 */       par7 = par9;
/* 1082 */       par9 = var13;
/*      */     } 
/*      */     
/* 1085 */     par7 /= 2.0F;
/* 1086 */     par9 /= 2.0F;
/* 1087 */     par1BlockAnvil.field_82521_b = par5;
/* 1088 */     setRenderBounds((0.5F - par7), par6, (0.5F - par9), (0.5F + par7), (par6 + par8), (0.5F + par9));
/*      */     
/* 1090 */     if (par11) {
/*      */       
/* 1092 */       Tessellator var14 = Tessellator.instance;
/* 1093 */       var14.startDrawingQuads();
/* 1094 */       var14.setNormal(0.0F, -1.0F, 0.0F);
/* 1095 */       renderFaceYNeg(par1BlockAnvil, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockAnvil, 0, par12));
/* 1096 */       var14.draw();
/* 1097 */       var14.startDrawingQuads();
/* 1098 */       var14.setNormal(0.0F, 1.0F, 0.0F);
/* 1099 */       renderFaceYPos(par1BlockAnvil, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockAnvil, 1, par12));
/* 1100 */       var14.draw();
/* 1101 */       var14.startDrawingQuads();
/* 1102 */       var14.setNormal(0.0F, 0.0F, -1.0F);
/* 1103 */       renderFaceZNeg(par1BlockAnvil, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockAnvil, 2, par12));
/* 1104 */       var14.draw();
/* 1105 */       var14.startDrawingQuads();
/* 1106 */       var14.setNormal(0.0F, 0.0F, 1.0F);
/* 1107 */       renderFaceZPos(par1BlockAnvil, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockAnvil, 3, par12));
/* 1108 */       var14.draw();
/* 1109 */       var14.startDrawingQuads();
/* 1110 */       var14.setNormal(-1.0F, 0.0F, 0.0F);
/* 1111 */       renderFaceXNeg(par1BlockAnvil, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockAnvil, 4, par12));
/* 1112 */       var14.draw();
/* 1113 */       var14.startDrawingQuads();
/* 1114 */       var14.setNormal(1.0F, 0.0F, 0.0F);
/* 1115 */       renderFaceXPos(par1BlockAnvil, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockAnvil, 5, par12));
/* 1116 */       var14.draw();
/*      */     }
/*      */     else {
/*      */       
/* 1120 */       renderStandardBlock(par1BlockAnvil, par2, par3, par4);
/*      */     } 
/*      */     
/* 1123 */     return par6 + par8;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockTorch(Block par1Block, int par2, int par3, int par4) {
/* 1131 */     int var5 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 1132 */     Tessellator var6 = Tessellator.instance;
/* 1133 */     var6.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1134 */     var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
/* 1135 */     double var7 = 0.4000000059604645D;
/* 1136 */     double var9 = 0.5D - var7;
/* 1137 */     double var11 = 0.20000000298023224D;
/*      */     
/* 1139 */     if (var5 == 1) {
/*      */       
/* 1141 */       renderTorchAtAngle(par1Block, par2 - var9, par3 + var11, par4, -var7, 0.0D, 0);
/*      */     }
/* 1143 */     else if (var5 == 2) {
/*      */       
/* 1145 */       renderTorchAtAngle(par1Block, par2 + var9, par3 + var11, par4, var7, 0.0D, 0);
/*      */     }
/* 1147 */     else if (var5 == 3) {
/*      */       
/* 1149 */       renderTorchAtAngle(par1Block, par2, par3 + var11, par4 - var9, 0.0D, -var7, 0);
/*      */     }
/* 1151 */     else if (var5 == 4) {
/*      */       
/* 1153 */       renderTorchAtAngle(par1Block, par2, par3 + var11, par4 + var9, 0.0D, var7, 0);
/*      */     }
/*      */     else {
/*      */       
/* 1157 */       renderTorchAtAngle(par1Block, par2, par3, par4, 0.0D, 0.0D, 0);
/*      */     } 
/*      */     
/* 1160 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockRepeater(BlockRedstoneRepeater par1BlockRedstoneRepeater, int par2, int par3, int par4) {
/* 1168 */     int var5 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 1169 */     int var6 = var5 & 0x3;
/* 1170 */     int var7 = (var5 & 0xC) >> 2;
/* 1171 */     Tessellator var8 = Tessellator.instance;
/* 1172 */     var8.setBrightness(par1BlockRedstoneRepeater.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1173 */     var8.setColorOpaque_F(1.0F, 1.0F, 1.0F);
/* 1174 */     double var9 = -0.1875D;
/* 1175 */     boolean var11 = par1BlockRedstoneRepeater.func_94476_e(this.blockAccess, par2, par3, par4, var5);
/* 1176 */     double var12 = 0.0D;
/* 1177 */     double var14 = 0.0D;
/* 1178 */     double var16 = 0.0D;
/* 1179 */     double var18 = 0.0D;
/*      */     
/* 1181 */     switch (var6) {
/*      */       
/*      */       case 0:
/* 1184 */         var18 = -0.3125D;
/* 1185 */         var14 = BlockRedstoneRepeater.repeaterTorchOffset[var7];
/*      */         break;
/*      */       
/*      */       case 1:
/* 1189 */         var16 = 0.3125D;
/* 1190 */         var12 = -BlockRedstoneRepeater.repeaterTorchOffset[var7];
/*      */         break;
/*      */       
/*      */       case 2:
/* 1194 */         var18 = 0.3125D;
/* 1195 */         var14 = -BlockRedstoneRepeater.repeaterTorchOffset[var7];
/*      */         break;
/*      */       
/*      */       case 3:
/* 1199 */         var16 = -0.3125D;
/* 1200 */         var12 = BlockRedstoneRepeater.repeaterTorchOffset[var7];
/*      */         break;
/*      */     } 
/* 1203 */     if (!var11) {
/*      */       
/* 1205 */       renderTorchAtAngle(par1BlockRedstoneRepeater, par2 + var12, par3 + var9, par4 + var14, 0.0D, 0.0D, 0);
/*      */     }
/*      */     else {
/*      */       
/* 1209 */       Icon var20 = getBlockIcon(Block.bedrock);
/* 1210 */       setOverrideBlockTexture(var20);
/* 1211 */       float var21 = 2.0F;
/* 1212 */       float var22 = 14.0F;
/* 1213 */       float var23 = 7.0F;
/* 1214 */       float var24 = 9.0F;
/*      */       
/* 1216 */       switch (var6) {
/*      */         
/*      */         case 1:
/*      */         case 3:
/* 1220 */           var21 = 7.0F;
/* 1221 */           var22 = 9.0F;
/* 1222 */           var23 = 2.0F;
/* 1223 */           var24 = 14.0F;
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/* 1228 */       setRenderBounds((var21 / 16.0F + (float)var12), 0.125D, (var23 / 16.0F + (float)var14), (var22 / 16.0F + (float)var12), 0.25D, (var24 / 16.0F + (float)var14));
/* 1229 */       double var25 = var20.getInterpolatedU(var21);
/* 1230 */       double var27 = var20.getInterpolatedV(var23);
/* 1231 */       double var29 = var20.getInterpolatedU(var22);
/* 1232 */       double var31 = var20.getInterpolatedV(var24);
/* 1233 */       var8.addVertexWithUV((par2 + var21 / 16.0F) + var12, (par3 + 0.25F), (par4 + var23 / 16.0F) + var14, var25, var27);
/* 1234 */       var8.addVertexWithUV((par2 + var21 / 16.0F) + var12, (par3 + 0.25F), (par4 + var24 / 16.0F) + var14, var25, var31);
/* 1235 */       var8.addVertexWithUV((par2 + var22 / 16.0F) + var12, (par3 + 0.25F), (par4 + var24 / 16.0F) + var14, var29, var31);
/* 1236 */       var8.addVertexWithUV((par2 + var22 / 16.0F) + var12, (par3 + 0.25F), (par4 + var23 / 16.0F) + var14, var29, var27);
/* 1237 */       renderStandardBlock(par1BlockRedstoneRepeater, par2, par3, par4);
/* 1238 */       setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/* 1239 */       clearOverrideBlockTexture();
/*      */     } 
/*      */ 
/*      */     
/* 1243 */     var8.setBrightness(par1BlockRedstoneRepeater.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1244 */     var8.setColorOpaque_F(1.0F, 1.0F, 1.0F);
/* 1245 */     renderTorchAtAngle(par1BlockRedstoneRepeater, par2 + var16, par3 + var9, par4 + var18, 0.0D, 0.0D, 0);
/* 1246 */     renderBlockRedstoneLogic(par1BlockRedstoneRepeater, par2, par3, par4);
/* 1247 */     return true;
/*      */   }
/*      */   
/*      */   private boolean renderBlockComparator(BlockComparator par1BlockComparator, int par2, int par3, int par4) {
/*      */     Icon var18;
/* 1252 */     Tessellator var5 = Tessellator.instance;
/* 1253 */     var5.setBrightness(par1BlockComparator.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1254 */     var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
/* 1255 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 1256 */     int var7 = var6 & 0x3;
/* 1257 */     double var8 = 0.0D;
/* 1258 */     double var10 = -0.1875D;
/* 1259 */     double var12 = 0.0D;
/* 1260 */     double var14 = 0.0D;
/* 1261 */     double var16 = 0.0D;
/*      */ 
/*      */     
/* 1264 */     if (par1BlockComparator.func_94490_c(var6)) {
/*      */       
/* 1266 */       var18 = Block.torchRedstoneActive.getBlockTextureFromSide(0);
/*      */     }
/*      */     else {
/*      */       
/* 1270 */       var10 -= 0.1875D;
/* 1271 */       var18 = Block.torchRedstoneIdle.getBlockTextureFromSide(0);
/*      */     } 
/*      */     
/* 1274 */     switch (var7) {
/*      */       
/*      */       case 0:
/* 1277 */         var12 = -0.3125D;
/* 1278 */         var16 = 1.0D;
/*      */         break;
/*      */       
/*      */       case 1:
/* 1282 */         var8 = 0.3125D;
/* 1283 */         var14 = -1.0D;
/*      */         break;
/*      */       
/*      */       case 2:
/* 1287 */         var12 = 0.3125D;
/* 1288 */         var16 = -1.0D;
/*      */         break;
/*      */       
/*      */       case 3:
/* 1292 */         var8 = -0.3125D;
/* 1293 */         var14 = 1.0D;
/*      */         break;
/*      */     } 
/* 1296 */     renderTorchAtAngle(par1BlockComparator, par2 + 0.25D * var14 + 0.1875D * var16, (par3 - 0.1875F), par4 + 0.25D * var16 + 0.1875D * var14, 0.0D, 0.0D, var6);
/* 1297 */     renderTorchAtAngle(par1BlockComparator, par2 + 0.25D * var14 + -0.1875D * var16, (par3 - 0.1875F), par4 + 0.25D * var16 + -0.1875D * var14, 0.0D, 0.0D, var6);
/* 1298 */     setOverrideBlockTexture(var18);
/* 1299 */     renderTorchAtAngle(par1BlockComparator, par2 + var8, par3 + var10, par4 + var12, 0.0D, 0.0D, var6);
/* 1300 */     clearOverrideBlockTexture();
/* 1301 */     renderBlockRedstoneLogicMetadata(par1BlockComparator, par2, par3, par4, var7);
/* 1302 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean renderBlockRedstoneLogic(BlockRedstoneLogic par1BlockRedstoneLogic, int par2, int par3, int par4) {
/* 1307 */     Tessellator var5 = Tessellator.instance;
/* 1308 */     renderBlockRedstoneLogicMetadata(par1BlockRedstoneLogic, par2, par3, par4, this.blockAccess.getBlockMetadata(par2, par3, par4) & 0x3);
/* 1309 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderBlockRedstoneLogicMetadata(BlockRedstoneLogic par1BlockRedstoneLogic, int par2, int par3, int par4, int par5) {
/* 1314 */     renderStandardBlock(par1BlockRedstoneLogic, par2, par3, par4);
/* 1315 */     Tessellator var6 = Tessellator.instance;
/* 1316 */     var6.setBrightness(par1BlockRedstoneLogic.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1317 */     var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
/* 1318 */     int var7 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 1319 */     Icon var8 = getBlockIconFromSideAndMetadata(par1BlockRedstoneLogic, 1, var7);
/* 1320 */     double var9 = var8.getMinU();
/* 1321 */     double var11 = var8.getMaxU();
/* 1322 */     double var13 = var8.getMinV();
/* 1323 */     double var15 = var8.getMaxV();
/* 1324 */     double var17 = 0.125D;
/* 1325 */     double var19 = (par2 + 1);
/* 1326 */     double var21 = (par2 + 1);
/* 1327 */     double var23 = (par2 + 0);
/* 1328 */     double var25 = (par2 + 0);
/* 1329 */     double var27 = (par4 + 0);
/* 1330 */     double var29 = (par4 + 1);
/* 1331 */     double var31 = (par4 + 1);
/* 1332 */     double var33 = (par4 + 0);
/* 1333 */     double var35 = par3 + var17;
/*      */ 
/*      */ 
/*      */     
/* 1337 */     var19 = var21 = (par2 + 0);
/* 1338 */     var23 = var25 = (par2 + 1);
/* 1339 */     var27 = var33 = (par4 + 1);
/* 1340 */     var29 = var31 = (par4 + 0);
/*      */ 
/*      */ 
/*      */     
/* 1344 */     var19 = var25 = (par2 + 0);
/* 1345 */     var21 = var23 = (par2 + 1);
/* 1346 */     var27 = var29 = (par4 + 0);
/* 1347 */     var31 = var33 = (par4 + 1);
/*      */     
/* 1349 */     if (par5 == 1) {
/*      */       
/* 1351 */       var19 = var25 = (par2 + 1);
/* 1352 */       var21 = var23 = (par2 + 0);
/* 1353 */       var27 = var29 = (par4 + 1);
/* 1354 */       var31 = var33 = (par4 + 0);
/*      */     } 
/*      */     
/* 1357 */     var6.addVertexWithUV(var25, var35, var33, var9, var13);
/* 1358 */     var6.addVertexWithUV(var23, var35, var31, var9, var15);
/* 1359 */     var6.addVertexWithUV(var21, var35, var29, var11, var15);
/* 1360 */     var6.addVertexWithUV(var19, var35, var27, var11, var13);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderPistonBaseAllFaces(Block par1Block, int par2, int par3, int par4) {
/* 1368 */     this.renderAllFaces = true;
/* 1369 */     renderPistonBase(par1Block, par2, par3, par4, true);
/* 1370 */     this.renderAllFaces = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderPistonBase(Block par1Block, int par2, int par3, int par4, boolean par5) {
/* 1378 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 1379 */     boolean var7 = (par5 || (var6 & 0x8) != 0);
/* 1380 */     int var8 = BlockPistonBase.getOrientation(var6);
/* 1381 */     float var9 = 0.25F;
/*      */     
/* 1383 */     if (var7) {
/*      */       
/* 1385 */       switch (var8) {
/*      */         
/*      */         case 0:
/* 1388 */           this.uvRotateEast = 3;
/* 1389 */           this.uvRotateWest = 3;
/* 1390 */           this.uvRotateSouth = 3;
/* 1391 */           this.uvRotateNorth = 3;
/* 1392 */           setRenderBounds(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */           break;
/*      */         
/*      */         case 1:
/* 1396 */           setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
/*      */           break;
/*      */         
/*      */         case 2:
/* 1400 */           this.uvRotateSouth = 1;
/* 1401 */           this.uvRotateNorth = 2;
/* 1402 */           setRenderBounds(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D);
/*      */           break;
/*      */         
/*      */         case 3:
/* 1406 */           this.uvRotateSouth = 2;
/* 1407 */           this.uvRotateNorth = 1;
/* 1408 */           this.uvRotateTop = 3;
/* 1409 */           this.uvRotateBottom = 3;
/* 1410 */           setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D);
/*      */           break;
/*      */         
/*      */         case 4:
/* 1414 */           this.uvRotateEast = 1;
/* 1415 */           this.uvRotateWest = 2;
/* 1416 */           this.uvRotateTop = 2;
/* 1417 */           this.uvRotateBottom = 1;
/* 1418 */           setRenderBounds(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */           break;
/*      */         
/*      */         case 5:
/* 1422 */           this.uvRotateEast = 2;
/* 1423 */           this.uvRotateWest = 1;
/* 1424 */           this.uvRotateTop = 1;
/* 1425 */           this.uvRotateBottom = 2;
/* 1426 */           setRenderBounds(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D);
/*      */           break;
/*      */       } 
/* 1429 */       ((BlockPistonBase)par1Block).func_96479_b((float)this.renderMinX, (float)this.renderMinY, (float)this.renderMinZ, (float)this.renderMaxX, (float)this.renderMaxY, (float)this.renderMaxZ);
/* 1430 */       renderStandardBlock(par1Block, par2, par3, par4);
/* 1431 */       this.uvRotateEast = 0;
/* 1432 */       this.uvRotateWest = 0;
/* 1433 */       this.uvRotateSouth = 0;
/* 1434 */       this.uvRotateNorth = 0;
/* 1435 */       this.uvRotateTop = 0;
/* 1436 */       this.uvRotateBottom = 0;
/* 1437 */       setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 1438 */       ((BlockPistonBase)par1Block).func_96479_b((float)this.renderMinX, (float)this.renderMinY, (float)this.renderMinZ, (float)this.renderMaxX, (float)this.renderMaxY, (float)this.renderMaxZ);
/*      */     }
/*      */     else {
/*      */       
/* 1442 */       switch (var8) {
/*      */         
/*      */         case 0:
/* 1445 */           this.uvRotateEast = 3;
/* 1446 */           this.uvRotateWest = 3;
/* 1447 */           this.uvRotateSouth = 3;
/* 1448 */           this.uvRotateNorth = 3;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/* 1455 */           this.uvRotateSouth = 1;
/* 1456 */           this.uvRotateNorth = 2;
/*      */           break;
/*      */         
/*      */         case 3:
/* 1460 */           this.uvRotateSouth = 2;
/* 1461 */           this.uvRotateNorth = 1;
/* 1462 */           this.uvRotateTop = 3;
/* 1463 */           this.uvRotateBottom = 3;
/*      */           break;
/*      */         
/*      */         case 4:
/* 1467 */           this.uvRotateEast = 1;
/* 1468 */           this.uvRotateWest = 2;
/* 1469 */           this.uvRotateTop = 2;
/* 1470 */           this.uvRotateBottom = 1;
/*      */           break;
/*      */         
/*      */         case 5:
/* 1474 */           this.uvRotateEast = 2;
/* 1475 */           this.uvRotateWest = 1;
/* 1476 */           this.uvRotateTop = 1;
/* 1477 */           this.uvRotateBottom = 2;
/*      */           break;
/*      */       } 
/* 1480 */       renderStandardBlock(par1Block, par2, par3, par4);
/* 1481 */       this.uvRotateEast = 0;
/* 1482 */       this.uvRotateWest = 0;
/* 1483 */       this.uvRotateSouth = 0;
/* 1484 */       this.uvRotateNorth = 0;
/* 1485 */       this.uvRotateTop = 0;
/* 1486 */       this.uvRotateBottom = 0;
/*      */     } 
/*      */     
/* 1489 */     return true;
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
/*      */   private void renderPistonRodUD(double par1, double par3, double par5, double par7, double par9, double par11, float par13, double par14) {
/* 1504 */     Icon var16 = (this.overrideBlockTexture == null) ? BlockPistonBase.getPistonBaseIcon("piston_side") : this.overrideBlockTexture;
/*      */     
/* 1506 */     Tessellator var17 = Tessellator.instance;
/* 1507 */     double var18 = var16.getMinU();
/* 1508 */     double var20 = var16.getMinV();
/* 1509 */     double var22 = var16.getInterpolatedU(par14);
/* 1510 */     double var24 = var16.getInterpolatedV(4.0D);
/* 1511 */     var17.setColorOpaque_F(par13, par13, par13);
/* 1512 */     var17.addVertexWithUV(par1, par7, par9, var22, var20);
/* 1513 */     var17.addVertexWithUV(par1, par5, par9, var18, var20);
/* 1514 */     var17.addVertexWithUV(par3, par5, par11, var18, var24);
/* 1515 */     var17.addVertexWithUV(par3, par7, par11, var22, var24);
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
/*      */   private void renderPistonRodSN(double par1, double par3, double par5, double par7, double par9, double par11, float par13, double par14) {
/* 1530 */     Icon var16 = (this.overrideBlockTexture == null) ? BlockPistonBase.getPistonBaseIcon("piston_side") : this.overrideBlockTexture;
/*      */     
/* 1532 */     Tessellator var17 = Tessellator.instance;
/* 1533 */     double var18 = var16.getMinU();
/* 1534 */     double var20 = var16.getMinV();
/* 1535 */     double var22 = var16.getInterpolatedU(par14);
/* 1536 */     double var24 = var16.getInterpolatedV(4.0D);
/* 1537 */     var17.setColorOpaque_F(par13, par13, par13);
/* 1538 */     var17.addVertexWithUV(par1, par5, par11, var22, var20);
/* 1539 */     var17.addVertexWithUV(par1, par5, par9, var18, var20);
/* 1540 */     var17.addVertexWithUV(par3, par7, par9, var18, var24);
/* 1541 */     var17.addVertexWithUV(par3, par7, par11, var22, var24);
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
/*      */   private void renderPistonRodEW(double par1, double par3, double par5, double par7, double par9, double par11, float par13, double par14) {
/* 1556 */     Icon var16 = (this.overrideBlockTexture == null) ? BlockPistonBase.getPistonBaseIcon("piston_side") : this.overrideBlockTexture;
/*      */     
/* 1558 */     Tessellator var17 = Tessellator.instance;
/* 1559 */     double var18 = var16.getMinU();
/* 1560 */     double var20 = var16.getMinV();
/* 1561 */     double var22 = var16.getInterpolatedU(par14);
/* 1562 */     double var24 = var16.getInterpolatedV(4.0D);
/* 1563 */     var17.setColorOpaque_F(par13, par13, par13);
/* 1564 */     var17.addVertexWithUV(par3, par5, par9, var22, var20);
/* 1565 */     var17.addVertexWithUV(par1, par5, par9, var18, var20);
/* 1566 */     var17.addVertexWithUV(par1, par7, par11, var18, var24);
/* 1567 */     var17.addVertexWithUV(par3, par7, par11, var22, var24);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderPistonExtensionAllFaces(Block par1Block, int par2, int par3, int par4, boolean par5) {
/* 1575 */     this.renderAllFaces = true;
/* 1576 */     renderPistonExtension(par1Block, par2, par3, par4, par5);
/* 1577 */     this.renderAllFaces = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderPistonExtension(Block par1Block, int par2, int par3, int par4, boolean par5) {
/* 1585 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 1586 */     int var7 = BlockPistonExtension.getDirectionMeta(var6);
/* 1587 */     float var8 = 0.25F;
/* 1588 */     float var9 = 0.375F;
/* 1589 */     float var10 = 0.625F;
/* 1590 */     float var11 = par1Block.getBlockBrightness(this.blockAccess, par2, par3, par4);
/* 1591 */     float var12 = par5 ? 1.0F : 0.5F;
/* 1592 */     double var13 = par5 ? 16.0D : 8.0D;
/*      */     
/* 1594 */     Tessellator tessellator = Tessellator.instance;
/*      */     
/* 1596 */     switch (var7) {
/*      */       
/*      */       case 0:
/* 1599 */         this.uvRotateEast = 3;
/* 1600 */         this.uvRotateWest = 3;
/* 1601 */         this.uvRotateSouth = 3;
/* 1602 */         this.uvRotateNorth = 3;
/* 1603 */         setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
/* 1604 */         renderStandardBlock(par1Block, par2, par3, par4);
/* 1605 */         tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1606 */         renderPistonRodUD((par2 + 0.375F), (par2 + 0.625F), (par3 + 0.25F), (par3 + 0.25F + var12), (par4 + 0.625F), (par4 + 0.625F), var11 * 0.8F, var13);
/* 1607 */         renderPistonRodUD((par2 + 0.625F), (par2 + 0.375F), (par3 + 0.25F), (par3 + 0.25F + var12), (par4 + 0.375F), (par4 + 0.375F), var11 * 0.8F, var13);
/* 1608 */         renderPistonRodUD((par2 + 0.375F), (par2 + 0.375F), (par3 + 0.25F), (par3 + 0.25F + var12), (par4 + 0.375F), (par4 + 0.625F), var11 * 0.6F, var13);
/* 1609 */         renderPistonRodUD((par2 + 0.625F), (par2 + 0.625F), (par3 + 0.25F), (par3 + 0.25F + var12), (par4 + 0.625F), (par4 + 0.375F), var11 * 0.6F, var13);
/*      */         break;
/*      */       
/*      */       case 1:
/* 1613 */         setRenderBounds(0.0D, 0.75D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 1614 */         renderStandardBlock(par1Block, par2, par3, par4);
/* 1615 */         tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1616 */         renderPistonRodUD((par2 + 0.375F), (par2 + 0.625F), (par3 - 0.25F + 1.0F - var12), (par3 - 0.25F + 1.0F), (par4 + 0.625F), (par4 + 0.625F), var11 * 0.8F, var13);
/* 1617 */         renderPistonRodUD((par2 + 0.625F), (par2 + 0.375F), (par3 - 0.25F + 1.0F - var12), (par3 - 0.25F + 1.0F), (par4 + 0.375F), (par4 + 0.375F), var11 * 0.8F, var13);
/* 1618 */         renderPistonRodUD((par2 + 0.375F), (par2 + 0.375F), (par3 - 0.25F + 1.0F - var12), (par3 - 0.25F + 1.0F), (par4 + 0.375F), (par4 + 0.625F), var11 * 0.6F, var13);
/* 1619 */         renderPistonRodUD((par2 + 0.625F), (par2 + 0.625F), (par3 - 0.25F + 1.0F - var12), (par3 - 0.25F + 1.0F), (par4 + 0.625F), (par4 + 0.375F), var11 * 0.6F, var13);
/*      */         break;
/*      */       
/*      */       case 2:
/* 1623 */         this.uvRotateSouth = 1;
/* 1624 */         this.uvRotateNorth = 2;
/* 1625 */         setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
/* 1626 */         renderStandardBlock(par1Block, par2, par3, par4);
/* 1627 */         tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1628 */         renderPistonRodSN((par2 + 0.375F), (par2 + 0.375F), (par3 + 0.625F), (par3 + 0.375F), (par4 + 0.25F), (par4 + 0.25F + var12), var11 * 0.6F, var13);
/* 1629 */         renderPistonRodSN((par2 + 0.625F), (par2 + 0.625F), (par3 + 0.375F), (par3 + 0.625F), (par4 + 0.25F), (par4 + 0.25F + var12), var11 * 0.6F, var13);
/* 1630 */         renderPistonRodSN((par2 + 0.375F), (par2 + 0.625F), (par3 + 0.375F), (par3 + 0.375F), (par4 + 0.25F), (par4 + 0.25F + var12), var11 * 0.5F, var13);
/* 1631 */         renderPistonRodSN((par2 + 0.625F), (par2 + 0.375F), (par3 + 0.625F), (par3 + 0.625F), (par4 + 0.25F), (par4 + 0.25F + var12), var11, var13);
/*      */         break;
/*      */       
/*      */       case 3:
/* 1635 */         this.uvRotateSouth = 2;
/* 1636 */         this.uvRotateNorth = 1;
/* 1637 */         this.uvRotateTop = 3;
/* 1638 */         this.uvRotateBottom = 3;
/* 1639 */         setRenderBounds(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
/* 1640 */         renderStandardBlock(par1Block, par2, par3, par4);
/* 1641 */         tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1642 */         renderPistonRodSN((par2 + 0.375F), (par2 + 0.375F), (par3 + 0.625F), (par3 + 0.375F), (par4 - 0.25F + 1.0F - var12), (par4 - 0.25F + 1.0F), var11 * 0.6F, var13);
/* 1643 */         renderPistonRodSN((par2 + 0.625F), (par2 + 0.625F), (par3 + 0.375F), (par3 + 0.625F), (par4 - 0.25F + 1.0F - var12), (par4 - 0.25F + 1.0F), var11 * 0.6F, var13);
/* 1644 */         renderPistonRodSN((par2 + 0.375F), (par2 + 0.625F), (par3 + 0.375F), (par3 + 0.375F), (par4 - 0.25F + 1.0F - var12), (par4 - 0.25F + 1.0F), var11 * 0.5F, var13);
/* 1645 */         renderPistonRodSN((par2 + 0.625F), (par2 + 0.375F), (par3 + 0.625F), (par3 + 0.625F), (par4 - 0.25F + 1.0F - var12), (par4 - 0.25F + 1.0F), var11, var13);
/*      */         break;
/*      */       
/*      */       case 4:
/* 1649 */         this.uvRotateEast = 1;
/* 1650 */         this.uvRotateWest = 2;
/* 1651 */         this.uvRotateTop = 2;
/* 1652 */         this.uvRotateBottom = 1;
/* 1653 */         setRenderBounds(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);
/* 1654 */         renderStandardBlock(par1Block, par2, par3, par4);
/* 1655 */         tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1656 */         renderPistonRodEW((par2 + 0.25F), (par2 + 0.25F + var12), (par3 + 0.375F), (par3 + 0.375F), (par4 + 0.625F), (par4 + 0.375F), var11 * 0.5F, var13);
/* 1657 */         renderPistonRodEW((par2 + 0.25F), (par2 + 0.25F + var12), (par3 + 0.625F), (par3 + 0.625F), (par4 + 0.375F), (par4 + 0.625F), var11, var13);
/* 1658 */         renderPistonRodEW((par2 + 0.25F), (par2 + 0.25F + var12), (par3 + 0.375F), (par3 + 0.625F), (par4 + 0.375F), (par4 + 0.375F), var11 * 0.6F, var13);
/* 1659 */         renderPistonRodEW((par2 + 0.25F), (par2 + 0.25F + var12), (par3 + 0.625F), (par3 + 0.375F), (par4 + 0.625F), (par4 + 0.625F), var11 * 0.6F, var13);
/*      */         break;
/*      */       
/*      */       case 5:
/* 1663 */         this.uvRotateEast = 2;
/* 1664 */         this.uvRotateWest = 1;
/* 1665 */         this.uvRotateTop = 1;
/* 1666 */         this.uvRotateBottom = 2;
/* 1667 */         setRenderBounds(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 1668 */         renderStandardBlock(par1Block, par2, par3, par4);
/* 1669 */         tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1670 */         renderPistonRodEW((par2 - 0.25F + 1.0F - var12), (par2 - 0.25F + 1.0F), (par3 + 0.375F), (par3 + 0.375F), (par4 + 0.625F), (par4 + 0.375F), var11 * 0.5F, var13);
/* 1671 */         renderPistonRodEW((par2 - 0.25F + 1.0F - var12), (par2 - 0.25F + 1.0F), (par3 + 0.625F), (par3 + 0.625F), (par4 + 0.375F), (par4 + 0.625F), var11, var13);
/* 1672 */         renderPistonRodEW((par2 - 0.25F + 1.0F - var12), (par2 - 0.25F + 1.0F), (par3 + 0.375F), (par3 + 0.625F), (par4 + 0.375F), (par4 + 0.375F), var11 * 0.6F, var13);
/* 1673 */         renderPistonRodEW((par2 - 0.25F + 1.0F - var12), (par2 - 0.25F + 1.0F), (par3 + 0.625F), (par3 + 0.375F), (par4 + 0.625F), (par4 + 0.625F), var11 * 0.6F, var13);
/*      */         break;
/*      */     } 
/* 1676 */     this.uvRotateEast = 0;
/* 1677 */     this.uvRotateWest = 0;
/* 1678 */     this.uvRotateSouth = 0;
/* 1679 */     this.uvRotateNorth = 0;
/* 1680 */     this.uvRotateTop = 0;
/* 1681 */     this.uvRotateBottom = 0;
/* 1682 */     setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 1683 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockLever(Block par1Block, int par2, int par3, int par4) {
/* 1691 */     int var5 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 1692 */     int var6 = var5 & 0x7;
/* 1693 */     boolean var7 = ((var5 & 0x8) > 0);
/* 1694 */     Tessellator var8 = Tessellator.instance;
/*      */     
/* 1696 */     boolean var9 = (this.overrideBlockTexture != null);
/*      */     
/* 1698 */     if (!var9)
/*      */     {
/* 1700 */       setOverrideBlockTexture(getBlockIcon(Block.cobblestone));
/*      */     }
/*      */     
/* 1703 */     float var10 = 0.25F;
/* 1704 */     float var11 = 0.1875F;
/* 1705 */     float var12 = 0.1875F;
/*      */     
/* 1707 */     if (var6 == 5) {
/*      */       
/* 1709 */       setRenderBounds((0.5F - var11), 0.0D, (0.5F - var10), (0.5F + var11), var12, (0.5F + var10));
/*      */     }
/* 1711 */     else if (var6 == 6) {
/*      */       
/* 1713 */       setRenderBounds((0.5F - var10), 0.0D, (0.5F - var11), (0.5F + var10), var12, (0.5F + var11));
/*      */     }
/* 1715 */     else if (var6 == 4) {
/*      */       
/* 1717 */       setRenderBounds((0.5F - var11), (0.5F - var10), (1.0F - var12), (0.5F + var11), (0.5F + var10), 1.0D);
/*      */     }
/* 1719 */     else if (var6 == 3) {
/*      */       
/* 1721 */       setRenderBounds((0.5F - var11), (0.5F - var10), 0.0D, (0.5F + var11), (0.5F + var10), var12);
/*      */     }
/* 1723 */     else if (var6 == 2) {
/*      */       
/* 1725 */       setRenderBounds((1.0F - var12), (0.5F - var10), (0.5F - var11), 1.0D, (0.5F + var10), (0.5F + var11));
/*      */     }
/* 1727 */     else if (var6 == 1) {
/*      */       
/* 1729 */       setRenderBounds(0.0D, (0.5F - var10), (0.5F - var11), var12, (0.5F + var10), (0.5F + var11));
/*      */     }
/* 1731 */     else if (var6 == 0) {
/*      */       
/* 1733 */       setRenderBounds((0.5F - var10), (1.0F - var12), (0.5F - var11), (0.5F + var10), 1.0D, (0.5F + var11));
/*      */     }
/* 1735 */     else if (var6 == 7) {
/*      */       
/* 1737 */       setRenderBounds((0.5F - var11), (1.0F - var12), (0.5F - var10), (0.5F + var11), 1.0D, (0.5F + var10));
/*      */     } 
/*      */     
/* 1740 */     renderStandardBlock(par1Block, par2, par3, par4);
/*      */     
/* 1742 */     if (!var9)
/*      */     {
/* 1744 */       clearOverrideBlockTexture();
/*      */     }
/*      */     
/* 1747 */     var8.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1748 */     float var13 = 1.0F;
/*      */     
/* 1750 */     if (Block.lightValue[par1Block.blockID] > 0)
/*      */     {
/* 1752 */       var13 = 1.0F;
/*      */     }
/*      */     
/* 1755 */     var8.setColorOpaque_F(var13, var13, var13);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1763 */     Icon var14 = (this.overrideBlockTexture == null) ? getBlockIconFromSide(par1Block, 0) : this.overrideBlockTexture;
/*      */     
/* 1765 */     double var15 = var14.getMinU();
/* 1766 */     double var17 = var14.getMinV();
/* 1767 */     double var19 = var14.getMaxU();
/* 1768 */     double var21 = var14.getMaxV();
/* 1769 */     Vec3[] var23 = new Vec3[8];
/* 1770 */     float var24 = 0.0625F;
/* 1771 */     float var25 = 0.0625F;
/* 1772 */     float var26 = 0.625F;
/* 1773 */     var23[0] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var24, 0.0D, -var25);
/* 1774 */     var23[1] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var24, 0.0D, -var25);
/* 1775 */     var23[2] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var24, 0.0D, var25);
/* 1776 */     var23[3] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var24, 0.0D, var25);
/* 1777 */     var23[4] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var24, var26, -var25);
/* 1778 */     var23[5] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var24, var26, -var25);
/* 1779 */     var23[6] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var24, var26, var25);
/* 1780 */     var23[7] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var24, var26, var25);
/*      */     
/* 1782 */     for (int var27 = 0; var27 < 8; var27++) {
/*      */       
/* 1784 */       if (var7) {
/*      */         
/* 1786 */         (var23[var27]).zCoord -= 0.0625D;
/* 1787 */         var23[var27].rotateAroundX(0.69813174F);
/*      */       }
/*      */       else {
/*      */         
/* 1791 */         (var23[var27]).zCoord += 0.0625D;
/* 1792 */         var23[var27].rotateAroundX(-0.69813174F);
/*      */       } 
/*      */       
/* 1795 */       if (var6 == 0 || var6 == 7)
/*      */       {
/* 1797 */         var23[var27].rotateAroundZ(3.1415927F);
/*      */       }
/*      */       
/* 1800 */       if (var6 == 6 || var6 == 0)
/*      */       {
/* 1802 */         var23[var27].rotateAroundY(1.5707964F);
/*      */       }
/*      */       
/* 1805 */       if (var6 > 0 && var6 < 5) {
/*      */         
/* 1807 */         (var23[var27]).yCoord -= 0.375D;
/* 1808 */         var23[var27].rotateAroundX(1.5707964F);
/*      */         
/* 1810 */         if (var6 == 4)
/*      */         {
/* 1812 */           var23[var27].rotateAroundY(0.0F);
/*      */         }
/*      */         
/* 1815 */         if (var6 == 3)
/*      */         {
/* 1817 */           var23[var27].rotateAroundY(3.1415927F);
/*      */         }
/*      */         
/* 1820 */         if (var6 == 2)
/*      */         {
/* 1822 */           var23[var27].rotateAroundY(1.5707964F);
/*      */         }
/*      */         
/* 1825 */         if (var6 == 1)
/*      */         {
/* 1827 */           var23[var27].rotateAroundY(-1.5707964F);
/*      */         }
/*      */         
/* 1830 */         (var23[var27]).xCoord += par2 + 0.5D;
/* 1831 */         (var23[var27]).yCoord += (par3 + 0.5F);
/* 1832 */         (var23[var27]).zCoord += par4 + 0.5D;
/*      */       }
/* 1834 */       else if (var6 != 0 && var6 != 7) {
/*      */         
/* 1836 */         (var23[var27]).xCoord += par2 + 0.5D;
/* 1837 */         (var23[var27]).yCoord += (par3 + 0.125F);
/* 1838 */         (var23[var27]).zCoord += par4 + 0.5D;
/*      */       }
/*      */       else {
/*      */         
/* 1842 */         (var23[var27]).xCoord += par2 + 0.5D;
/* 1843 */         (var23[var27]).yCoord += (par3 + 0.875F);
/* 1844 */         (var23[var27]).zCoord += par4 + 0.5D;
/*      */       } 
/*      */     } 
/*      */     
/* 1848 */     Vec3 var32 = null;
/* 1849 */     Vec3 var28 = null;
/* 1850 */     Vec3 var29 = null;
/* 1851 */     Vec3 var30 = null;
/*      */     
/* 1853 */     for (int var31 = 0; var31 < 6; var31++) {
/*      */       
/* 1855 */       if (var31 == 0) {
/*      */         
/* 1857 */         var15 = var14.getInterpolatedU(7.0D);
/* 1858 */         var17 = var14.getInterpolatedV(6.0D);
/* 1859 */         var19 = var14.getInterpolatedU(9.0D);
/* 1860 */         var21 = var14.getInterpolatedV(8.0D);
/*      */       }
/* 1862 */       else if (var31 == 2) {
/*      */         
/* 1864 */         var15 = var14.getInterpolatedU(7.0D);
/* 1865 */         var17 = var14.getInterpolatedV(6.0D);
/* 1866 */         var19 = var14.getInterpolatedU(9.0D);
/* 1867 */         var21 = var14.getMaxV();
/*      */       } 
/*      */       
/* 1870 */       if (var31 == 0) {
/*      */         
/* 1872 */         var32 = var23[0];
/* 1873 */         var28 = var23[1];
/* 1874 */         var29 = var23[2];
/* 1875 */         var30 = var23[3];
/*      */       }
/* 1877 */       else if (var31 == 1) {
/*      */         
/* 1879 */         var32 = var23[7];
/* 1880 */         var28 = var23[6];
/* 1881 */         var29 = var23[5];
/* 1882 */         var30 = var23[4];
/*      */       }
/* 1884 */       else if (var31 == 2) {
/*      */         
/* 1886 */         var32 = var23[1];
/* 1887 */         var28 = var23[0];
/* 1888 */         var29 = var23[4];
/* 1889 */         var30 = var23[5];
/*      */       }
/* 1891 */       else if (var31 == 3) {
/*      */         
/* 1893 */         var32 = var23[2];
/* 1894 */         var28 = var23[1];
/* 1895 */         var29 = var23[5];
/* 1896 */         var30 = var23[6];
/*      */       }
/* 1898 */       else if (var31 == 4) {
/*      */         
/* 1900 */         var32 = var23[3];
/* 1901 */         var28 = var23[2];
/* 1902 */         var29 = var23[6];
/* 1903 */         var30 = var23[7];
/*      */       }
/* 1905 */       else if (var31 == 5) {
/*      */         
/* 1907 */         var32 = var23[0];
/* 1908 */         var28 = var23[3];
/* 1909 */         var29 = var23[7];
/* 1910 */         var30 = var23[4];
/*      */       } 
/*      */       
/* 1913 */       var8.addVertexWithUV(var32.xCoord, var32.yCoord, var32.zCoord, var15, var21);
/* 1914 */       var8.addVertexWithUV(var28.xCoord, var28.yCoord, var28.zCoord, var19, var21);
/* 1915 */       var8.addVertexWithUV(var29.xCoord, var29.yCoord, var29.zCoord, var19, var17);
/* 1916 */       var8.addVertexWithUV(var30.xCoord, var30.yCoord, var30.zCoord, var15, var17);
/*      */     } 
/*      */     
/* 1919 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockTripWireSource(Block par1Block, int par2, int par3, int par4) {
/* 1927 */     Tessellator var5 = Tessellator.instance;
/* 1928 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 1929 */     int var7 = var6 & 0x3;
/* 1930 */     boolean var8 = ((var6 & 0x4) == 4);
/* 1931 */     boolean var9 = ((var6 & 0x8) == 8);
/* 1932 */     boolean var10 = !this.blockAccess.isBlockTopFlatAndSolid(par2, par3 - 1, par4);
/*      */     
/* 1934 */     boolean var11 = (this.overrideBlockTexture != null);
/*      */     
/* 1936 */     if (!var11)
/*      */     {
/* 1938 */       setOverrideBlockTexture(getBlockIcon(Block.planks));
/*      */     }
/*      */     
/* 1941 */     float var12 = 0.25F;
/* 1942 */     float var13 = 0.125F;
/* 1943 */     float var14 = 0.125F;
/* 1944 */     float var15 = 0.3F - var12;
/* 1945 */     float var16 = 0.3F + var12;
/*      */     
/* 1947 */     if (var7 == 2) {
/*      */       
/* 1949 */       setRenderBounds((0.5F - var13), var15, (1.0F - var14), (0.5F + var13), var16, 1.0D);
/*      */     }
/* 1951 */     else if (var7 == 0) {
/*      */       
/* 1953 */       setRenderBounds((0.5F - var13), var15, 0.0D, (0.5F + var13), var16, var14);
/*      */     }
/* 1955 */     else if (var7 == 1) {
/*      */       
/* 1957 */       setRenderBounds((1.0F - var14), var15, (0.5F - var13), 1.0D, var16, (0.5F + var13));
/*      */     }
/* 1959 */     else if (var7 == 3) {
/*      */       
/* 1961 */       setRenderBounds(0.0D, var15, (0.5F - var13), var14, var16, (0.5F + var13));
/*      */     } 
/*      */     
/* 1964 */     renderStandardBlock(par1Block, par2, par3, par4);
/*      */     
/* 1966 */     if (!var11)
/*      */     {
/* 1968 */       clearOverrideBlockTexture();
/*      */     }
/*      */     
/* 1971 */     var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 1972 */     float var17 = 1.0F;
/*      */     
/* 1974 */     if (Block.lightValue[par1Block.blockID] > 0)
/*      */     {
/* 1976 */       var17 = 1.0F;
/*      */     }
/*      */     
/* 1979 */     var5.setColorOpaque_F(var17, var17, var17);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1987 */     Icon var18 = (this.overrideBlockTexture == null) ? getBlockIconFromSide(par1Block, 0) : this.overrideBlockTexture;
/*      */     
/* 1989 */     double var19 = var18.getMinU();
/* 1990 */     double var21 = var18.getMinV();
/* 1991 */     double var23 = var18.getMaxU();
/* 1992 */     double var25 = var18.getMaxV();
/* 1993 */     Vec3[] var27 = new Vec3[8];
/* 1994 */     float var28 = 0.046875F;
/* 1995 */     float var29 = 0.046875F;
/* 1996 */     float var30 = 0.3125F;
/* 1997 */     var27[0] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var28, 0.0D, -var29);
/* 1998 */     var27[1] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var28, 0.0D, -var29);
/* 1999 */     var27[2] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var28, 0.0D, var29);
/* 2000 */     var27[3] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var28, 0.0D, var29);
/* 2001 */     var27[4] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var28, var30, -var29);
/* 2002 */     var27[5] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var28, var30, -var29);
/* 2003 */     var27[6] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var28, var30, var29);
/* 2004 */     var27[7] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var28, var30, var29);
/*      */     
/* 2006 */     for (int var31 = 0; var31 < 8; var31++) {
/*      */       
/* 2008 */       (var27[var31]).zCoord += 0.0625D;
/*      */       
/* 2010 */       if (var9) {
/*      */         
/* 2012 */         var27[var31].rotateAroundX(0.5235988F);
/* 2013 */         (var27[var31]).yCoord -= 0.4375D;
/*      */       }
/* 2015 */       else if (var8) {
/*      */         
/* 2017 */         var27[var31].rotateAroundX(0.08726647F);
/* 2018 */         (var27[var31]).yCoord -= 0.4375D;
/*      */       }
/*      */       else {
/*      */         
/* 2022 */         var27[var31].rotateAroundX(-0.69813174F);
/* 2023 */         (var27[var31]).yCoord -= 0.375D;
/*      */       } 
/*      */       
/* 2026 */       var27[var31].rotateAroundX(1.5707964F);
/*      */       
/* 2028 */       if (var7 == 2)
/*      */       {
/* 2030 */         var27[var31].rotateAroundY(0.0F);
/*      */       }
/*      */       
/* 2033 */       if (var7 == 0)
/*      */       {
/* 2035 */         var27[var31].rotateAroundY(3.1415927F);
/*      */       }
/*      */       
/* 2038 */       if (var7 == 1)
/*      */       {
/* 2040 */         var27[var31].rotateAroundY(1.5707964F);
/*      */       }
/*      */       
/* 2043 */       if (var7 == 3)
/*      */       {
/* 2045 */         var27[var31].rotateAroundY(-1.5707964F);
/*      */       }
/*      */       
/* 2048 */       (var27[var31]).xCoord += par2 + 0.5D;
/* 2049 */       (var27[var31]).yCoord += (par3 + 0.3125F);
/* 2050 */       (var27[var31]).zCoord += par4 + 0.5D;
/*      */     } 
/*      */     
/* 2053 */     Vec3 var62 = null;
/* 2054 */     Vec3 var32 = null;
/* 2055 */     Vec3 var33 = null;
/* 2056 */     Vec3 var34 = null;
/* 2057 */     byte var35 = 7;
/* 2058 */     byte var36 = 9;
/* 2059 */     byte var37 = 9;
/* 2060 */     byte var38 = 16;
/*      */     
/* 2062 */     for (int var39 = 0; var39 < 6; var39++) {
/*      */       
/* 2064 */       if (var39 == 0) {
/*      */         
/* 2066 */         var62 = var27[0];
/* 2067 */         var32 = var27[1];
/* 2068 */         var33 = var27[2];
/* 2069 */         var34 = var27[3];
/* 2070 */         var19 = var18.getInterpolatedU(var35);
/* 2071 */         var21 = var18.getInterpolatedV(var37);
/* 2072 */         var23 = var18.getInterpolatedU(var36);
/* 2073 */         var25 = var18.getInterpolatedV((var37 + 2));
/*      */       }
/* 2075 */       else if (var39 == 1) {
/*      */         
/* 2077 */         var62 = var27[7];
/* 2078 */         var32 = var27[6];
/* 2079 */         var33 = var27[5];
/* 2080 */         var34 = var27[4];
/*      */       }
/* 2082 */       else if (var39 == 2) {
/*      */         
/* 2084 */         var62 = var27[1];
/* 2085 */         var32 = var27[0];
/* 2086 */         var33 = var27[4];
/* 2087 */         var34 = var27[5];
/* 2088 */         var19 = var18.getInterpolatedU(var35);
/* 2089 */         var21 = var18.getInterpolatedV(var37);
/* 2090 */         var23 = var18.getInterpolatedU(var36);
/* 2091 */         var25 = var18.getInterpolatedV(var38);
/*      */       }
/* 2093 */       else if (var39 == 3) {
/*      */         
/* 2095 */         var62 = var27[2];
/* 2096 */         var32 = var27[1];
/* 2097 */         var33 = var27[5];
/* 2098 */         var34 = var27[6];
/*      */       }
/* 2100 */       else if (var39 == 4) {
/*      */         
/* 2102 */         var62 = var27[3];
/* 2103 */         var32 = var27[2];
/* 2104 */         var33 = var27[6];
/* 2105 */         var34 = var27[7];
/*      */       }
/* 2107 */       else if (var39 == 5) {
/*      */         
/* 2109 */         var62 = var27[0];
/* 2110 */         var32 = var27[3];
/* 2111 */         var33 = var27[7];
/* 2112 */         var34 = var27[4];
/*      */       } 
/*      */       
/* 2115 */       var5.addVertexWithUV(var62.xCoord, var62.yCoord, var62.zCoord, var19, var25);
/* 2116 */       var5.addVertexWithUV(var32.xCoord, var32.yCoord, var32.zCoord, var23, var25);
/* 2117 */       var5.addVertexWithUV(var33.xCoord, var33.yCoord, var33.zCoord, var23, var21);
/* 2118 */       var5.addVertexWithUV(var34.xCoord, var34.yCoord, var34.zCoord, var19, var21);
/*      */     } 
/*      */     
/* 2121 */     float var63 = 0.09375F;
/* 2122 */     float var40 = 0.09375F;
/* 2123 */     float var41 = 0.03125F;
/* 2124 */     var27[0] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var63, 0.0D, -var40);
/* 2125 */     var27[1] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var63, 0.0D, -var40);
/* 2126 */     var27[2] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var63, 0.0D, var40);
/* 2127 */     var27[3] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var63, 0.0D, var40);
/* 2128 */     var27[4] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var63, var41, -var40);
/* 2129 */     var27[5] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var63, var41, -var40);
/* 2130 */     var27[6] = this.blockAccess.getWorldVec3Pool().getVecFromPool(var63, var41, var40);
/* 2131 */     var27[7] = this.blockAccess.getWorldVec3Pool().getVecFromPool(-var63, var41, var40);
/*      */     
/* 2133 */     for (int var42 = 0; var42 < 8; var42++) {
/*      */       
/* 2135 */       (var27[var42]).zCoord += 0.21875D;
/*      */       
/* 2137 */       if (var9) {
/*      */         
/* 2139 */         (var27[var42]).yCoord -= 0.09375D;
/* 2140 */         (var27[var42]).zCoord -= 0.1625D;
/* 2141 */         var27[var42].rotateAroundX(0.0F);
/*      */       }
/* 2143 */       else if (var8) {
/*      */         
/* 2145 */         (var27[var42]).yCoord += 0.015625D;
/* 2146 */         (var27[var42]).zCoord -= 0.171875D;
/* 2147 */         var27[var42].rotateAroundX(0.17453294F);
/*      */       }
/*      */       else {
/*      */         
/* 2151 */         var27[var42].rotateAroundX(0.87266463F);
/*      */       } 
/*      */       
/* 2154 */       if (var7 == 2)
/*      */       {
/* 2156 */         var27[var42].rotateAroundY(0.0F);
/*      */       }
/*      */       
/* 2159 */       if (var7 == 0)
/*      */       {
/* 2161 */         var27[var42].rotateAroundY(3.1415927F);
/*      */       }
/*      */       
/* 2164 */       if (var7 == 1)
/*      */       {
/* 2166 */         var27[var42].rotateAroundY(1.5707964F);
/*      */       }
/*      */       
/* 2169 */       if (var7 == 3)
/*      */       {
/* 2171 */         var27[var42].rotateAroundY(-1.5707964F);
/*      */       }
/*      */       
/* 2174 */       (var27[var42]).xCoord += par2 + 0.5D;
/* 2175 */       (var27[var42]).yCoord += (par3 + 0.3125F);
/* 2176 */       (var27[var42]).zCoord += par4 + 0.5D;
/*      */     } 
/*      */     
/* 2179 */     byte var65 = 5;
/* 2180 */     byte var43 = 11;
/* 2181 */     byte var44 = 3;
/* 2182 */     byte var45 = 9;
/*      */     
/* 2184 */     for (int var46 = 0; var46 < 6; var46++) {
/*      */       
/* 2186 */       if (var46 == 0) {
/*      */         
/* 2188 */         var62 = var27[0];
/* 2189 */         var32 = var27[1];
/* 2190 */         var33 = var27[2];
/* 2191 */         var34 = var27[3];
/* 2192 */         var19 = var18.getInterpolatedU(var65);
/* 2193 */         var21 = var18.getInterpolatedV(var44);
/* 2194 */         var23 = var18.getInterpolatedU(var43);
/* 2195 */         var25 = var18.getInterpolatedV(var45);
/*      */       }
/* 2197 */       else if (var46 == 1) {
/*      */         
/* 2199 */         var62 = var27[7];
/* 2200 */         var32 = var27[6];
/* 2201 */         var33 = var27[5];
/* 2202 */         var34 = var27[4];
/*      */       }
/* 2204 */       else if (var46 == 2) {
/*      */         
/* 2206 */         var62 = var27[1];
/* 2207 */         var32 = var27[0];
/* 2208 */         var33 = var27[4];
/* 2209 */         var34 = var27[5];
/* 2210 */         var19 = var18.getInterpolatedU(var65);
/* 2211 */         var21 = var18.getInterpolatedV(var44);
/* 2212 */         var23 = var18.getInterpolatedU(var43);
/* 2213 */         var25 = var18.getInterpolatedV((var44 + 2));
/*      */       }
/* 2215 */       else if (var46 == 3) {
/*      */         
/* 2217 */         var62 = var27[2];
/* 2218 */         var32 = var27[1];
/* 2219 */         var33 = var27[5];
/* 2220 */         var34 = var27[6];
/*      */       }
/* 2222 */       else if (var46 == 4) {
/*      */         
/* 2224 */         var62 = var27[3];
/* 2225 */         var32 = var27[2];
/* 2226 */         var33 = var27[6];
/* 2227 */         var34 = var27[7];
/*      */       }
/* 2229 */       else if (var46 == 5) {
/*      */         
/* 2231 */         var62 = var27[0];
/* 2232 */         var32 = var27[3];
/* 2233 */         var33 = var27[7];
/* 2234 */         var34 = var27[4];
/*      */       } 
/*      */       
/* 2237 */       var5.addVertexWithUV(var62.xCoord, var62.yCoord, var62.zCoord, var19, var25);
/* 2238 */       var5.addVertexWithUV(var32.xCoord, var32.yCoord, var32.zCoord, var23, var25);
/* 2239 */       var5.addVertexWithUV(var33.xCoord, var33.yCoord, var33.zCoord, var23, var21);
/* 2240 */       var5.addVertexWithUV(var34.xCoord, var34.yCoord, var34.zCoord, var19, var21);
/*      */     } 
/*      */     
/* 2243 */     if (var8) {
/*      */       
/* 2245 */       double var64 = (var27[0]).yCoord;
/* 2246 */       float var48 = 0.03125F;
/* 2247 */       float var49 = 0.5F - var48 / 2.0F;
/* 2248 */       float var50 = var49 + var48;
/* 2249 */       Icon var51 = getBlockIcon(Block.tripWire);
/* 2250 */       double var52 = var18.getMinU();
/* 2251 */       double var54 = var18.getInterpolatedV(var8 ? 2.0D : 0.0D);
/* 2252 */       double var56 = var18.getMaxU();
/* 2253 */       double var58 = var18.getInterpolatedV(var8 ? 4.0D : 2.0D);
/* 2254 */       double var60 = (var10 ? 3.5F : 1.5F) / 16.0D;
/* 2255 */       var17 = par1Block.getBlockBrightness(this.blockAccess, par2, par3, par4) * 0.75F;
/* 2256 */       var5.setColorOpaque_F(var17, var17, var17);
/*      */       
/* 2258 */       if (var7 == 2) {
/*      */         
/* 2260 */         var5.addVertexWithUV((par2 + var49), par3 + var60, par4 + 0.25D, var52, var54);
/* 2261 */         var5.addVertexWithUV((par2 + var50), par3 + var60, par4 + 0.25D, var52, var58);
/* 2262 */         var5.addVertexWithUV((par2 + var50), par3 + var60, par4, var56, var58);
/* 2263 */         var5.addVertexWithUV((par2 + var49), par3 + var60, par4, var56, var54);
/* 2264 */         var5.addVertexWithUV((par2 + var49), var64, par4 + 0.5D, var52, var54);
/* 2265 */         var5.addVertexWithUV((par2 + var50), var64, par4 + 0.5D, var52, var58);
/* 2266 */         var5.addVertexWithUV((par2 + var50), par3 + var60, par4 + 0.25D, var56, var58);
/* 2267 */         var5.addVertexWithUV((par2 + var49), par3 + var60, par4 + 0.25D, var56, var54);
/*      */       }
/* 2269 */       else if (var7 == 0) {
/*      */         
/* 2271 */         var5.addVertexWithUV((par2 + var49), par3 + var60, par4 + 0.75D, var52, var54);
/* 2272 */         var5.addVertexWithUV((par2 + var50), par3 + var60, par4 + 0.75D, var52, var58);
/* 2273 */         var5.addVertexWithUV((par2 + var50), var64, par4 + 0.5D, var56, var58);
/* 2274 */         var5.addVertexWithUV((par2 + var49), var64, par4 + 0.5D, var56, var54);
/* 2275 */         var5.addVertexWithUV((par2 + var49), par3 + var60, (par4 + 1), var52, var54);
/* 2276 */         var5.addVertexWithUV((par2 + var50), par3 + var60, (par4 + 1), var52, var58);
/* 2277 */         var5.addVertexWithUV((par2 + var50), par3 + var60, par4 + 0.75D, var56, var58);
/* 2278 */         var5.addVertexWithUV((par2 + var49), par3 + var60, par4 + 0.75D, var56, var54);
/*      */       }
/* 2280 */       else if (var7 == 1) {
/*      */         
/* 2282 */         var5.addVertexWithUV(par2, par3 + var60, (par4 + var50), var52, var58);
/* 2283 */         var5.addVertexWithUV(par2 + 0.25D, par3 + var60, (par4 + var50), var56, var58);
/* 2284 */         var5.addVertexWithUV(par2 + 0.25D, par3 + var60, (par4 + var49), var56, var54);
/* 2285 */         var5.addVertexWithUV(par2, par3 + var60, (par4 + var49), var52, var54);
/* 2286 */         var5.addVertexWithUV(par2 + 0.25D, par3 + var60, (par4 + var50), var52, var58);
/* 2287 */         var5.addVertexWithUV(par2 + 0.5D, var64, (par4 + var50), var56, var58);
/* 2288 */         var5.addVertexWithUV(par2 + 0.5D, var64, (par4 + var49), var56, var54);
/* 2289 */         var5.addVertexWithUV(par2 + 0.25D, par3 + var60, (par4 + var49), var52, var54);
/*      */       }
/*      */       else {
/*      */         
/* 2293 */         var5.addVertexWithUV(par2 + 0.5D, var64, (par4 + var50), var52, var58);
/* 2294 */         var5.addVertexWithUV(par2 + 0.75D, par3 + var60, (par4 + var50), var56, var58);
/* 2295 */         var5.addVertexWithUV(par2 + 0.75D, par3 + var60, (par4 + var49), var56, var54);
/* 2296 */         var5.addVertexWithUV(par2 + 0.5D, var64, (par4 + var49), var52, var54);
/* 2297 */         var5.addVertexWithUV(par2 + 0.75D, par3 + var60, (par4 + var50), var52, var58);
/* 2298 */         var5.addVertexWithUV((par2 + 1), par3 + var60, (par4 + var50), var56, var58);
/* 2299 */         var5.addVertexWithUV((par2 + 1), par3 + var60, (par4 + var49), var56, var54);
/* 2300 */         var5.addVertexWithUV(par2 + 0.75D, par3 + var60, (par4 + var49), var52, var54);
/*      */       } 
/*      */     } 
/*      */     
/* 2304 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockTripWire(Block par1Block, int par2, int par3, int par4) {
/* 2312 */     Tessellator var5 = Tessellator.instance;
/*      */     
/* 2314 */     int var7 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 2315 */     boolean var8 = ((var7 & 0x4) == 4);
/* 2316 */     boolean var9 = ((var7 & 0x2) == 2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2323 */     Icon var6 = (this.overrideBlockTexture == null) ? getBlockIconFromSide(par1Block, 0) : this.overrideBlockTexture;
/*      */     
/* 2325 */     var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 2326 */     float var10 = par1Block.getBlockBrightness(this.blockAccess, par2, par3, par4) * 0.75F;
/* 2327 */     var5.setColorOpaque_F(var10, var10, var10);
/* 2328 */     double var11 = var6.getMinU();
/* 2329 */     double var13 = var6.getInterpolatedV(var8 ? 2.0D : 0.0D);
/* 2330 */     double var15 = var6.getMaxU();
/* 2331 */     double var17 = var6.getInterpolatedV(var8 ? 4.0D : 2.0D);
/* 2332 */     double var19 = (var9 ? 3.5F : 1.5F) / 16.0D;
/* 2333 */     boolean var21 = BlockTripWire.func_72148_a(this.blockAccess, par2, par3, par4, var7, 1);
/* 2334 */     boolean var22 = BlockTripWire.func_72148_a(this.blockAccess, par2, par3, par4, var7, 3);
/* 2335 */     boolean var23 = BlockTripWire.func_72148_a(this.blockAccess, par2, par3, par4, var7, 2);
/* 2336 */     boolean var24 = BlockTripWire.func_72148_a(this.blockAccess, par2, par3, par4, var7, 0);
/* 2337 */     float var25 = 0.03125F;
/* 2338 */     float var26 = 0.5F - var25 / 2.0F;
/* 2339 */     float var27 = var26 + var25;
/*      */     
/* 2341 */     if (!var23 && !var22 && !var24 && !var21) {
/*      */       
/* 2343 */       var23 = true;
/* 2344 */       var24 = true;
/*      */     } 
/*      */     
/* 2347 */     if (var23) {
/*      */       
/* 2349 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.25D, var11, var13);
/* 2350 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.25D, var11, var17);
/* 2351 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4, var15, var17);
/* 2352 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4, var15, var13);
/* 2353 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4, var15, var13);
/* 2354 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4, var15, var17);
/* 2355 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.25D, var11, var17);
/* 2356 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.25D, var11, var13);
/*      */     } 
/*      */     
/* 2359 */     if (var23 || (var24 && !var22 && !var21)) {
/*      */       
/* 2361 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.5D, var11, var13);
/* 2362 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.5D, var11, var17);
/* 2363 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.25D, var15, var17);
/* 2364 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.25D, var15, var13);
/* 2365 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.25D, var15, var13);
/* 2366 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.25D, var15, var17);
/* 2367 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.5D, var11, var17);
/* 2368 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.5D, var11, var13);
/*      */     } 
/*      */     
/* 2371 */     if (var24 || (var23 && !var22 && !var21)) {
/*      */       
/* 2373 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.75D, var11, var13);
/* 2374 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.75D, var11, var17);
/* 2375 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.5D, var15, var17);
/* 2376 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.5D, var15, var13);
/* 2377 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.5D, var15, var13);
/* 2378 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.5D, var15, var17);
/* 2379 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.75D, var11, var17);
/* 2380 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.75D, var11, var13);
/*      */     } 
/*      */     
/* 2383 */     if (var24) {
/*      */       
/* 2385 */       var5.addVertexWithUV((par2 + var26), par3 + var19, (par4 + 1), var11, var13);
/* 2386 */       var5.addVertexWithUV((par2 + var27), par3 + var19, (par4 + 1), var11, var17);
/* 2387 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.75D, var15, var17);
/* 2388 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.75D, var15, var13);
/* 2389 */       var5.addVertexWithUV((par2 + var26), par3 + var19, par4 + 0.75D, var15, var13);
/* 2390 */       var5.addVertexWithUV((par2 + var27), par3 + var19, par4 + 0.75D, var15, var17);
/* 2391 */       var5.addVertexWithUV((par2 + var27), par3 + var19, (par4 + 1), var11, var17);
/* 2392 */       var5.addVertexWithUV((par2 + var26), par3 + var19, (par4 + 1), var11, var13);
/*      */     } 
/*      */     
/* 2395 */     if (var21) {
/*      */       
/* 2397 */       var5.addVertexWithUV(par2, par3 + var19, (par4 + var27), var11, var17);
/* 2398 */       var5.addVertexWithUV(par2 + 0.25D, par3 + var19, (par4 + var27), var15, var17);
/* 2399 */       var5.addVertexWithUV(par2 + 0.25D, par3 + var19, (par4 + var26), var15, var13);
/* 2400 */       var5.addVertexWithUV(par2, par3 + var19, (par4 + var26), var11, var13);
/* 2401 */       var5.addVertexWithUV(par2, par3 + var19, (par4 + var26), var11, var13);
/* 2402 */       var5.addVertexWithUV(par2 + 0.25D, par3 + var19, (par4 + var26), var15, var13);
/* 2403 */       var5.addVertexWithUV(par2 + 0.25D, par3 + var19, (par4 + var27), var15, var17);
/* 2404 */       var5.addVertexWithUV(par2, par3 + var19, (par4 + var27), var11, var17);
/*      */     } 
/*      */     
/* 2407 */     if (var21 || (var22 && !var23 && !var24)) {
/*      */       
/* 2409 */       var5.addVertexWithUV(par2 + 0.25D, par3 + var19, (par4 + var27), var11, var17);
/* 2410 */       var5.addVertexWithUV(par2 + 0.5D, par3 + var19, (par4 + var27), var15, var17);
/* 2411 */       var5.addVertexWithUV(par2 + 0.5D, par3 + var19, (par4 + var26), var15, var13);
/* 2412 */       var5.addVertexWithUV(par2 + 0.25D, par3 + var19, (par4 + var26), var11, var13);
/* 2413 */       var5.addVertexWithUV(par2 + 0.25D, par3 + var19, (par4 + var26), var11, var13);
/* 2414 */       var5.addVertexWithUV(par2 + 0.5D, par3 + var19, (par4 + var26), var15, var13);
/* 2415 */       var5.addVertexWithUV(par2 + 0.5D, par3 + var19, (par4 + var27), var15, var17);
/* 2416 */       var5.addVertexWithUV(par2 + 0.25D, par3 + var19, (par4 + var27), var11, var17);
/*      */     } 
/*      */     
/* 2419 */     if (var22 || (var21 && !var23 && !var24)) {
/*      */       
/* 2421 */       var5.addVertexWithUV(par2 + 0.5D, par3 + var19, (par4 + var27), var11, var17);
/* 2422 */       var5.addVertexWithUV(par2 + 0.75D, par3 + var19, (par4 + var27), var15, var17);
/* 2423 */       var5.addVertexWithUV(par2 + 0.75D, par3 + var19, (par4 + var26), var15, var13);
/* 2424 */       var5.addVertexWithUV(par2 + 0.5D, par3 + var19, (par4 + var26), var11, var13);
/* 2425 */       var5.addVertexWithUV(par2 + 0.5D, par3 + var19, (par4 + var26), var11, var13);
/* 2426 */       var5.addVertexWithUV(par2 + 0.75D, par3 + var19, (par4 + var26), var15, var13);
/* 2427 */       var5.addVertexWithUV(par2 + 0.75D, par3 + var19, (par4 + var27), var15, var17);
/* 2428 */       var5.addVertexWithUV(par2 + 0.5D, par3 + var19, (par4 + var27), var11, var17);
/*      */     } 
/*      */     
/* 2431 */     if (var22) {
/*      */       
/* 2433 */       var5.addVertexWithUV(par2 + 0.75D, par3 + var19, (par4 + var27), var11, var17);
/* 2434 */       var5.addVertexWithUV((par2 + 1), par3 + var19, (par4 + var27), var15, var17);
/* 2435 */       var5.addVertexWithUV((par2 + 1), par3 + var19, (par4 + var26), var15, var13);
/* 2436 */       var5.addVertexWithUV(par2 + 0.75D, par3 + var19, (par4 + var26), var11, var13);
/* 2437 */       var5.addVertexWithUV(par2 + 0.75D, par3 + var19, (par4 + var26), var11, var13);
/* 2438 */       var5.addVertexWithUV((par2 + 1), par3 + var19, (par4 + var26), var15, var13);
/* 2439 */       var5.addVertexWithUV((par2 + 1), par3 + var19, (par4 + var27), var15, var17);
/* 2440 */       var5.addVertexWithUV(par2 + 0.75D, par3 + var19, (par4 + var27), var11, var17);
/*      */     } 
/*      */     
/* 2443 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockFire(BlockFire par1BlockFire, int par2, int par3, int par4) {
/* 2452 */     Tessellator var5 = Tessellator.instance;
/* 2453 */     Icon var6 = par1BlockFire.getFireIcon(0);
/* 2454 */     Icon var7 = par1BlockFire.getFireIcon(1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2462 */     Icon var8 = (this.overrideBlockTexture == null) ? var6 : this.overrideBlockTexture;
/*      */     
/* 2464 */     var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
/* 2465 */     var5.setBrightness(par1BlockFire.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 2466 */     double var9 = var8.getMinU();
/* 2467 */     double var11 = var8.getMinV();
/* 2468 */     double var13 = var8.getMaxU();
/* 2469 */     double var15 = var8.getMaxV();
/* 2470 */     float var17 = 1.4F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2479 */     if (!this.blockAccess.isBlockTopFlatAndSolid(par2, par3 - 1, par4) && !Block.fire.canBlockCatchFire(this.blockAccess, par2, par3 - 1, par4)) {
/*      */       
/* 2481 */       float var36 = 0.2F;
/* 2482 */       float var19 = 0.0625F;
/*      */       
/* 2484 */       if ((par2 + par3 + par4 & 0x1) == 1) {
/*      */         
/* 2486 */         var9 = var7.getMinU();
/* 2487 */         var11 = var7.getMinV();
/* 2488 */         var13 = var7.getMaxU();
/* 2489 */         var15 = var7.getMaxV();
/*      */       } 
/*      */       
/* 2492 */       if ((par2 / 2 + par3 / 2 + par4 / 2 & 0x1) == 1) {
/*      */         
/* 2494 */         double var20 = var13;
/* 2495 */         var13 = var9;
/* 2496 */         var9 = var20;
/*      */       } 
/*      */       
/* 2499 */       if (Block.fire.canBlockCatchFire(this.blockAccess, par2 - 1, par3, par4)) {
/*      */         
/* 2501 */         var5.addVertexWithUV((par2 + var36), (par3 + var17 + var19), (par4 + 1), var13, var11);
/* 2502 */         var5.addVertexWithUV((par2 + 0), ((par3 + 0) + var19), (par4 + 1), var13, var15);
/* 2503 */         var5.addVertexWithUV((par2 + 0), ((par3 + 0) + var19), (par4 + 0), var9, var15);
/* 2504 */         var5.addVertexWithUV((par2 + var36), (par3 + var17 + var19), (par4 + 0), var9, var11);
/* 2505 */         var5.addVertexWithUV((par2 + var36), (par3 + var17 + var19), (par4 + 0), var9, var11);
/* 2506 */         var5.addVertexWithUV((par2 + 0), ((par3 + 0) + var19), (par4 + 0), var9, var15);
/* 2507 */         var5.addVertexWithUV((par2 + 0), ((par3 + 0) + var19), (par4 + 1), var13, var15);
/* 2508 */         var5.addVertexWithUV((par2 + var36), (par3 + var17 + var19), (par4 + 1), var13, var11);
/*      */       } 
/*      */       
/* 2511 */       if (Block.fire.canBlockCatchFire(this.blockAccess, par2 + 1, par3, par4)) {
/*      */         
/* 2513 */         var5.addVertexWithUV(((par2 + 1) - var36), (par3 + var17 + var19), (par4 + 0), var9, var11);
/* 2514 */         var5.addVertexWithUV((par2 + 1 - 0), ((par3 + 0) + var19), (par4 + 0), var9, var15);
/* 2515 */         var5.addVertexWithUV((par2 + 1 - 0), ((par3 + 0) + var19), (par4 + 1), var13, var15);
/* 2516 */         var5.addVertexWithUV(((par2 + 1) - var36), (par3 + var17 + var19), (par4 + 1), var13, var11);
/* 2517 */         var5.addVertexWithUV(((par2 + 1) - var36), (par3 + var17 + var19), (par4 + 1), var13, var11);
/* 2518 */         var5.addVertexWithUV((par2 + 1 - 0), ((par3 + 0) + var19), (par4 + 1), var13, var15);
/* 2519 */         var5.addVertexWithUV((par2 + 1 - 0), ((par3 + 0) + var19), (par4 + 0), var9, var15);
/* 2520 */         var5.addVertexWithUV(((par2 + 1) - var36), (par3 + var17 + var19), (par4 + 0), var9, var11);
/*      */       } 
/*      */       
/* 2523 */       if (Block.fire.canBlockCatchFire(this.blockAccess, par2, par3, par4 - 1)) {
/*      */         
/* 2525 */         var5.addVertexWithUV((par2 + 0), (par3 + var17 + var19), (par4 + var36), var13, var11);
/* 2526 */         var5.addVertexWithUV((par2 + 0), ((par3 + 0) + var19), (par4 + 0), var13, var15);
/* 2527 */         var5.addVertexWithUV((par2 + 1), ((par3 + 0) + var19), (par4 + 0), var9, var15);
/* 2528 */         var5.addVertexWithUV((par2 + 1), (par3 + var17 + var19), (par4 + var36), var9, var11);
/* 2529 */         var5.addVertexWithUV((par2 + 1), (par3 + var17 + var19), (par4 + var36), var9, var11);
/* 2530 */         var5.addVertexWithUV((par2 + 1), ((par3 + 0) + var19), (par4 + 0), var9, var15);
/* 2531 */         var5.addVertexWithUV((par2 + 0), ((par3 + 0) + var19), (par4 + 0), var13, var15);
/* 2532 */         var5.addVertexWithUV((par2 + 0), (par3 + var17 + var19), (par4 + var36), var13, var11);
/*      */       } 
/*      */       
/* 2535 */       if (Block.fire.canBlockCatchFire(this.blockAccess, par2, par3, par4 + 1)) {
/*      */         
/* 2537 */         var5.addVertexWithUV((par2 + 1), (par3 + var17 + var19), ((par4 + 1) - var36), var9, var11);
/* 2538 */         var5.addVertexWithUV((par2 + 1), ((par3 + 0) + var19), (par4 + 1 - 0), var9, var15);
/* 2539 */         var5.addVertexWithUV((par2 + 0), ((par3 + 0) + var19), (par4 + 1 - 0), var13, var15);
/* 2540 */         var5.addVertexWithUV((par2 + 0), (par3 + var17 + var19), ((par4 + 1) - var36), var13, var11);
/* 2541 */         var5.addVertexWithUV((par2 + 0), (par3 + var17 + var19), ((par4 + 1) - var36), var13, var11);
/* 2542 */         var5.addVertexWithUV((par2 + 0), ((par3 + 0) + var19), (par4 + 1 - 0), var13, var15);
/* 2543 */         var5.addVertexWithUV((par2 + 1), ((par3 + 0) + var19), (par4 + 1 - 0), var9, var15);
/* 2544 */         var5.addVertexWithUV((par2 + 1), (par3 + var17 + var19), ((par4 + 1) - var36), var9, var11);
/*      */       } 
/*      */       
/* 2547 */       if (Block.fire.canBlockCatchFire(this.blockAccess, par2, par3 + 1, par4)) {
/*      */         
/* 2549 */         double var20 = par2 + 0.5D + 0.5D;
/* 2550 */         double var22 = par2 + 0.5D - 0.5D;
/* 2551 */         double var24 = par4 + 0.5D + 0.5D;
/* 2552 */         double var26 = par4 + 0.5D - 0.5D;
/* 2553 */         double var28 = par2 + 0.5D - 0.5D;
/* 2554 */         double var30 = par2 + 0.5D + 0.5D;
/* 2555 */         double var32 = par4 + 0.5D - 0.5D;
/* 2556 */         double var34 = par4 + 0.5D + 0.5D;
/* 2557 */         var9 = var6.getMinU();
/* 2558 */         var11 = var6.getMinV();
/* 2559 */         var13 = var6.getMaxU();
/* 2560 */         var15 = var6.getMaxV();
/* 2561 */         par3++;
/* 2562 */         var17 = -0.2F;
/*      */         
/* 2564 */         if ((par2 + par3 + par4 & 0x1) == 0)
/*      */         {
/* 2566 */           var5.addVertexWithUV(var28, (par3 + var17), (par4 + 0), var13, var11);
/* 2567 */           var5.addVertexWithUV(var20, (par3 + 0), (par4 + 0), var13, var15);
/* 2568 */           var5.addVertexWithUV(var20, (par3 + 0), (par4 + 1), var9, var15);
/* 2569 */           var5.addVertexWithUV(var28, (par3 + var17), (par4 + 1), var9, var11);
/* 2570 */           var9 = var7.getMinU();
/* 2571 */           var11 = var7.getMinV();
/* 2572 */           var13 = var7.getMaxU();
/* 2573 */           var15 = var7.getMaxV();
/* 2574 */           var5.addVertexWithUV(var30, (par3 + var17), (par4 + 1), var13, var11);
/* 2575 */           var5.addVertexWithUV(var22, (par3 + 0), (par4 + 1), var13, var15);
/* 2576 */           var5.addVertexWithUV(var22, (par3 + 0), (par4 + 0), var9, var15);
/* 2577 */           var5.addVertexWithUV(var30, (par3 + var17), (par4 + 0), var9, var11);
/*      */         }
/*      */         else
/*      */         {
/* 2581 */           var5.addVertexWithUV((par2 + 0), (par3 + var17), var34, var13, var11);
/* 2582 */           var5.addVertexWithUV((par2 + 0), (par3 + 0), var26, var13, var15);
/* 2583 */           var5.addVertexWithUV((par2 + 1), (par3 + 0), var26, var9, var15);
/* 2584 */           var5.addVertexWithUV((par2 + 1), (par3 + var17), var34, var9, var11);
/* 2585 */           var9 = var7.getMinU();
/* 2586 */           var11 = var7.getMinV();
/* 2587 */           var13 = var7.getMaxU();
/* 2588 */           var15 = var7.getMaxV();
/* 2589 */           var5.addVertexWithUV((par2 + 1), (par3 + var17), var32, var13, var11);
/* 2590 */           var5.addVertexWithUV((par2 + 1), (par3 + 0), var24, var13, var15);
/* 2591 */           var5.addVertexWithUV((par2 + 0), (par3 + 0), var24, var9, var15);
/* 2592 */           var5.addVertexWithUV((par2 + 0), (par3 + var17), var32, var9, var11);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 2598 */       double var18 = par2 + 0.5D + 0.2D;
/* 2599 */       double var20 = par2 + 0.5D - 0.2D;
/* 2600 */       double var22 = par4 + 0.5D + 0.2D;
/* 2601 */       double var24 = par4 + 0.5D - 0.2D;
/* 2602 */       double var26 = par2 + 0.5D - 0.3D;
/* 2603 */       double var28 = par2 + 0.5D + 0.3D;
/* 2604 */       double var30 = par4 + 0.5D - 0.3D;
/* 2605 */       double var32 = par4 + 0.5D + 0.3D;
/* 2606 */       var5.addVertexWithUV(var26, (par3 + var17), (par4 + 1), var13, var11);
/* 2607 */       var5.addVertexWithUV(var18, (par3 + 0), (par4 + 1), var13, var15);
/* 2608 */       var5.addVertexWithUV(var18, (par3 + 0), (par4 + 0), var9, var15);
/* 2609 */       var5.addVertexWithUV(var26, (par3 + var17), (par4 + 0), var9, var11);
/* 2610 */       var5.addVertexWithUV(var28, (par3 + var17), (par4 + 0), var13, var11);
/* 2611 */       var5.addVertexWithUV(var20, (par3 + 0), (par4 + 0), var13, var15);
/* 2612 */       var5.addVertexWithUV(var20, (par3 + 0), (par4 + 1), var9, var15);
/* 2613 */       var5.addVertexWithUV(var28, (par3 + var17), (par4 + 1), var9, var11);
/* 2614 */       var9 = var7.getMinU();
/* 2615 */       var11 = var7.getMinV();
/* 2616 */       var13 = var7.getMaxU();
/* 2617 */       var15 = var7.getMaxV();
/* 2618 */       var5.addVertexWithUV((par2 + 1), (par3 + var17), var32, var13, var11);
/* 2619 */       var5.addVertexWithUV((par2 + 1), (par3 + 0), var24, var13, var15);
/* 2620 */       var5.addVertexWithUV((par2 + 0), (par3 + 0), var24, var9, var15);
/* 2621 */       var5.addVertexWithUV((par2 + 0), (par3 + var17), var32, var9, var11);
/* 2622 */       var5.addVertexWithUV((par2 + 0), (par3 + var17), var30, var13, var11);
/* 2623 */       var5.addVertexWithUV((par2 + 0), (par3 + 0), var22, var13, var15);
/* 2624 */       var5.addVertexWithUV((par2 + 1), (par3 + 0), var22, var9, var15);
/* 2625 */       var5.addVertexWithUV((par2 + 1), (par3 + var17), var30, var9, var11);
/* 2626 */       var18 = par2 + 0.5D - 0.5D;
/* 2627 */       var20 = par2 + 0.5D + 0.5D;
/* 2628 */       var22 = par4 + 0.5D - 0.5D;
/* 2629 */       var24 = par4 + 0.5D + 0.5D;
/* 2630 */       var26 = par2 + 0.5D - 0.4D;
/* 2631 */       var28 = par2 + 0.5D + 0.4D;
/* 2632 */       var30 = par4 + 0.5D - 0.4D;
/* 2633 */       var32 = par4 + 0.5D + 0.4D;
/* 2634 */       var5.addVertexWithUV(var26, (par3 + var17), (par4 + 0), var9, var11);
/* 2635 */       var5.addVertexWithUV(var18, (par3 + 0), (par4 + 0), var9, var15);
/* 2636 */       var5.addVertexWithUV(var18, (par3 + 0), (par4 + 1), var13, var15);
/* 2637 */       var5.addVertexWithUV(var26, (par3 + var17), (par4 + 1), var13, var11);
/* 2638 */       var5.addVertexWithUV(var28, (par3 + var17), (par4 + 1), var9, var11);
/* 2639 */       var5.addVertexWithUV(var20, (par3 + 0), (par4 + 1), var9, var15);
/* 2640 */       var5.addVertexWithUV(var20, (par3 + 0), (par4 + 0), var13, var15);
/* 2641 */       var5.addVertexWithUV(var28, (par3 + var17), (par4 + 0), var13, var11);
/* 2642 */       var9 = var6.getMinU();
/* 2643 */       var11 = var6.getMinV();
/* 2644 */       var13 = var6.getMaxU();
/* 2645 */       var15 = var6.getMaxV();
/* 2646 */       var5.addVertexWithUV((par2 + 0), (par3 + var17), var32, var9, var11);
/* 2647 */       var5.addVertexWithUV((par2 + 0), (par3 + 0), var24, var9, var15);
/* 2648 */       var5.addVertexWithUV((par2 + 1), (par3 + 0), var24, var13, var15);
/* 2649 */       var5.addVertexWithUV((par2 + 1), (par3 + var17), var32, var13, var11);
/* 2650 */       var5.addVertexWithUV((par2 + 1), (par3 + var17), var30, var9, var11);
/* 2651 */       var5.addVertexWithUV((par2 + 1), (par3 + 0), var22, var9, var15);
/* 2652 */       var5.addVertexWithUV((par2 + 0), (par3 + 0), var22, var13, var15);
/* 2653 */       var5.addVertexWithUV((par2 + 0), (par3 + var17), var30, var13, var11);
/*      */     } 
/*      */     
/* 2656 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockRedstoneWire(Block par1Block, int par2, int par3, int par4) {
/* 2664 */     Tessellator var5 = Tessellator.instance;
/* 2665 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 2666 */     Icon var7 = BlockRedstoneWire.getRedstoneWireIcon("cross");
/* 2667 */     Icon var8 = BlockRedstoneWire.getRedstoneWireIcon("line");
/* 2668 */     Icon var9 = BlockRedstoneWire.getRedstoneWireIcon("cross_overlay");
/* 2669 */     Icon var10 = BlockRedstoneWire.getRedstoneWireIcon("line_overlay");
/* 2670 */     var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 2671 */     float var11 = 1.0F;
/* 2672 */     float var12 = var6 / 15.0F;
/* 2673 */     float var13 = var12 * 0.6F + 0.4F;
/*      */     
/* 2675 */     if (var6 == 0)
/*      */     {
/* 2677 */       var13 = 0.3F;
/*      */     }
/*      */     
/* 2680 */     float var14 = var12 * var12 * 0.7F - 0.5F;
/* 2681 */     float var15 = var12 * var12 * 0.6F - 0.7F;
/*      */     
/* 2683 */     if (var14 < 0.0F)
/*      */     {
/* 2685 */       var14 = 0.0F;
/*      */     }
/*      */     
/* 2688 */     if (var15 < 0.0F)
/*      */     {
/* 2690 */       var15 = 0.0F;
/*      */     }
/*      */     
/* 2693 */     var5.setColorOpaque_F(var13, var14, var15);
/* 2694 */     double var16 = 0.015625D;
/* 2695 */     double var18 = 0.015625D;
/* 2696 */     boolean var20 = (BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2 - 1, par3, par4, 1) || (!this.blockAccess.isBlockNormalCube(par2 - 1, par3, par4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2 - 1, par3 - 1, par4, -1)));
/* 2697 */     boolean var21 = (BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2 + 1, par3, par4, 3) || (!this.blockAccess.isBlockNormalCube(par2 + 1, par3, par4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2 + 1, par3 - 1, par4, -1)));
/* 2698 */     boolean var22 = (BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2, par3, par4 - 1, 2) || (!this.blockAccess.isBlockNormalCube(par2, par3, par4 - 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2, par3 - 1, par4 - 1, -1)));
/* 2699 */     boolean var23 = (BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2, par3, par4 + 1, 0) || (!this.blockAccess.isBlockNormalCube(par2, par3, par4 + 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2, par3 - 1, par4 + 1, -1)));
/*      */     
/* 2701 */     if (!this.blockAccess.isBlockNormalCube(par2, par3 + 1, par4)) {
/*      */       
/* 2703 */       if (this.blockAccess.isBlockNormalCube(par2 - 1, par3, par4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2 - 1, par3 + 1, par4, -1))
/*      */       {
/* 2705 */         var20 = true;
/*      */       }
/*      */       
/* 2708 */       if (this.blockAccess.isBlockNormalCube(par2 + 1, par3, par4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2 + 1, par3 + 1, par4, -1))
/*      */       {
/* 2710 */         var21 = true;
/*      */       }
/*      */       
/* 2713 */       if (this.blockAccess.isBlockNormalCube(par2, par3, par4 - 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2, par3 + 1, par4 - 1, -1))
/*      */       {
/* 2715 */         var22 = true;
/*      */       }
/*      */       
/* 2718 */       if (this.blockAccess.isBlockNormalCube(par2, par3, par4 + 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, par2, par3 + 1, par4 + 1, -1))
/*      */       {
/* 2720 */         var23 = true;
/*      */       }
/*      */     } 
/*      */     
/* 2724 */     float var24 = (par2 + 0);
/* 2725 */     float var25 = (par2 + 1);
/* 2726 */     float var26 = (par4 + 0);
/* 2727 */     float var27 = (par4 + 1);
/* 2728 */     int var28 = 0;
/*      */     
/* 2730 */     if ((var20 || var21) && !var22 && !var23)
/*      */     {
/* 2732 */       var28 = 1;
/*      */     }
/*      */     
/* 2735 */     if ((var22 || var23) && !var21 && !var20)
/*      */     {
/* 2737 */       var28 = 2;
/*      */     }
/*      */     
/* 2740 */     if (var28 == 0) {
/*      */       
/* 2742 */       int var29 = 0;
/* 2743 */       int var30 = 0;
/* 2744 */       int var31 = 16;
/* 2745 */       int var32 = 16;
/* 2746 */       boolean var33 = true;
/*      */       
/* 2748 */       if (!var20)
/*      */       {
/* 2750 */         var24 += 0.3125F;
/*      */       }
/*      */       
/* 2753 */       if (!var20)
/*      */       {
/* 2755 */         var29 += 5;
/*      */       }
/*      */       
/* 2758 */       if (!var21)
/*      */       {
/* 2760 */         var25 -= 0.3125F;
/*      */       }
/*      */       
/* 2763 */       if (!var21)
/*      */       {
/* 2765 */         var31 -= 5;
/*      */       }
/*      */       
/* 2768 */       if (!var22)
/*      */       {
/* 2770 */         var26 += 0.3125F;
/*      */       }
/*      */       
/* 2773 */       if (!var22)
/*      */       {
/* 2775 */         var30 += 5;
/*      */       }
/*      */       
/* 2778 */       if (!var23)
/*      */       {
/* 2780 */         var27 -= 0.3125F;
/*      */       }
/*      */       
/* 2783 */       if (!var23)
/*      */       {
/* 2785 */         var32 -= 5;
/*      */       }
/*      */       
/* 2788 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var27, var7.getInterpolatedU(var31), var7.getInterpolatedV(var32));
/* 2789 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var26, var7.getInterpolatedU(var31), var7.getInterpolatedV(var30));
/* 2790 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var26, var7.getInterpolatedU(var29), var7.getInterpolatedV(var30));
/* 2791 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var27, var7.getInterpolatedU(var29), var7.getInterpolatedV(var32));
/* 2792 */       var5.setColorOpaque_F(var11, var11, var11);
/* 2793 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var27, var9.getInterpolatedU(var31), var9.getInterpolatedV(var32));
/* 2794 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var26, var9.getInterpolatedU(var31), var9.getInterpolatedV(var30));
/* 2795 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var26, var9.getInterpolatedU(var29), var9.getInterpolatedV(var30));
/* 2796 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var27, var9.getInterpolatedU(var29), var9.getInterpolatedV(var32));
/*      */     }
/* 2798 */     else if (var28 == 1) {
/*      */       
/* 2800 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var27, var8.getMaxU(), var8.getMaxV());
/* 2801 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var26, var8.getMaxU(), var8.getMinV());
/* 2802 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var26, var8.getMinU(), var8.getMinV());
/* 2803 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var27, var8.getMinU(), var8.getMaxV());
/* 2804 */       var5.setColorOpaque_F(var11, var11, var11);
/* 2805 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var27, var10.getMaxU(), var10.getMaxV());
/* 2806 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var26, var10.getMaxU(), var10.getMinV());
/* 2807 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var26, var10.getMinU(), var10.getMinV());
/* 2808 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var27, var10.getMinU(), var10.getMaxV());
/*      */     }
/*      */     else {
/*      */       
/* 2812 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var27, var8.getMaxU(), var8.getMaxV());
/* 2813 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var26, var8.getMinU(), var8.getMaxV());
/* 2814 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var26, var8.getMinU(), var8.getMinV());
/* 2815 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var27, var8.getMaxU(), var8.getMinV());
/* 2816 */       var5.setColorOpaque_F(var11, var11, var11);
/* 2817 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var27, var10.getMaxU(), var10.getMaxV());
/* 2818 */       var5.addVertexWithUV(var25, par3 + 0.015625D, var26, var10.getMinU(), var10.getMaxV());
/* 2819 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var26, var10.getMinU(), var10.getMinV());
/* 2820 */       var5.addVertexWithUV(var24, par3 + 0.015625D, var27, var10.getMaxU(), var10.getMinV());
/*      */     } 
/*      */     
/* 2823 */     if (!this.blockAccess.isBlockNormalCube(par2, par3 + 1, par4)) {
/*      */       
/* 2825 */       float var34 = 0.021875F;
/*      */       
/* 2827 */       if (this.blockAccess.isBlockNormalCube(par2 - 1, par3, par4) && this.blockAccess.getBlockId(par2 - 1, par3 + 1, par4) == Block.redstoneWire.blockID) {
/*      */         
/* 2829 */         var5.setColorOpaque_F(var11 * var13, var11 * var14, var11 * var15);
/* 2830 */         var5.addVertexWithUV(par2 + 0.015625D, ((par3 + 1) + 0.021875F), (par4 + 1), var8.getMaxU(), var8.getMinV());
/* 2831 */         var5.addVertexWithUV(par2 + 0.015625D, (par3 + 0), (par4 + 1), var8.getMinU(), var8.getMinV());
/* 2832 */         var5.addVertexWithUV(par2 + 0.015625D, (par3 + 0), (par4 + 0), var8.getMinU(), var8.getMaxV());
/* 2833 */         var5.addVertexWithUV(par2 + 0.015625D, ((par3 + 1) + 0.021875F), (par4 + 0), var8.getMaxU(), var8.getMaxV());
/* 2834 */         var5.setColorOpaque_F(var11, var11, var11);
/* 2835 */         var5.addVertexWithUV(par2 + 0.015625D, ((par3 + 1) + 0.021875F), (par4 + 1), var10.getMaxU(), var10.getMinV());
/* 2836 */         var5.addVertexWithUV(par2 + 0.015625D, (par3 + 0), (par4 + 1), var10.getMinU(), var10.getMinV());
/* 2837 */         var5.addVertexWithUV(par2 + 0.015625D, (par3 + 0), (par4 + 0), var10.getMinU(), var10.getMaxV());
/* 2838 */         var5.addVertexWithUV(par2 + 0.015625D, ((par3 + 1) + 0.021875F), (par4 + 0), var10.getMaxU(), var10.getMaxV());
/*      */       } 
/*      */       
/* 2841 */       if (this.blockAccess.isBlockNormalCube(par2 + 1, par3, par4) && this.blockAccess.getBlockId(par2 + 1, par3 + 1, par4) == Block.redstoneWire.blockID) {
/*      */         
/* 2843 */         var5.setColorOpaque_F(var11 * var13, var11 * var14, var11 * var15);
/* 2844 */         var5.addVertexWithUV((par2 + 1) - 0.015625D, (par3 + 0), (par4 + 1), var8.getMinU(), var8.getMaxV());
/* 2845 */         var5.addVertexWithUV((par2 + 1) - 0.015625D, ((par3 + 1) + 0.021875F), (par4 + 1), var8.getMaxU(), var8.getMaxV());
/* 2846 */         var5.addVertexWithUV((par2 + 1) - 0.015625D, ((par3 + 1) + 0.021875F), (par4 + 0), var8.getMaxU(), var8.getMinV());
/* 2847 */         var5.addVertexWithUV((par2 + 1) - 0.015625D, (par3 + 0), (par4 + 0), var8.getMinU(), var8.getMinV());
/* 2848 */         var5.setColorOpaque_F(var11, var11, var11);
/* 2849 */         var5.addVertexWithUV((par2 + 1) - 0.015625D, (par3 + 0), (par4 + 1), var10.getMinU(), var10.getMaxV());
/* 2850 */         var5.addVertexWithUV((par2 + 1) - 0.015625D, ((par3 + 1) + 0.021875F), (par4 + 1), var10.getMaxU(), var10.getMaxV());
/* 2851 */         var5.addVertexWithUV((par2 + 1) - 0.015625D, ((par3 + 1) + 0.021875F), (par4 + 0), var10.getMaxU(), var10.getMinV());
/* 2852 */         var5.addVertexWithUV((par2 + 1) - 0.015625D, (par3 + 0), (par4 + 0), var10.getMinU(), var10.getMinV());
/*      */       } 
/*      */       
/* 2855 */       if (this.blockAccess.isBlockNormalCube(par2, par3, par4 - 1) && this.blockAccess.getBlockId(par2, par3 + 1, par4 - 1) == Block.redstoneWire.blockID) {
/*      */         
/* 2857 */         var5.setColorOpaque_F(var11 * var13, var11 * var14, var11 * var15);
/* 2858 */         var5.addVertexWithUV((par2 + 1), (par3 + 0), par4 + 0.015625D, var8.getMinU(), var8.getMaxV());
/* 2859 */         var5.addVertexWithUV((par2 + 1), ((par3 + 1) + 0.021875F), par4 + 0.015625D, var8.getMaxU(), var8.getMaxV());
/* 2860 */         var5.addVertexWithUV((par2 + 0), ((par3 + 1) + 0.021875F), par4 + 0.015625D, var8.getMaxU(), var8.getMinV());
/* 2861 */         var5.addVertexWithUV((par2 + 0), (par3 + 0), par4 + 0.015625D, var8.getMinU(), var8.getMinV());
/* 2862 */         var5.setColorOpaque_F(var11, var11, var11);
/* 2863 */         var5.addVertexWithUV((par2 + 1), (par3 + 0), par4 + 0.015625D, var10.getMinU(), var10.getMaxV());
/* 2864 */         var5.addVertexWithUV((par2 + 1), ((par3 + 1) + 0.021875F), par4 + 0.015625D, var10.getMaxU(), var10.getMaxV());
/* 2865 */         var5.addVertexWithUV((par2 + 0), ((par3 + 1) + 0.021875F), par4 + 0.015625D, var10.getMaxU(), var10.getMinV());
/* 2866 */         var5.addVertexWithUV((par2 + 0), (par3 + 0), par4 + 0.015625D, var10.getMinU(), var10.getMinV());
/*      */       } 
/*      */       
/* 2869 */       if (this.blockAccess.isBlockNormalCube(par2, par3, par4 + 1) && this.blockAccess.getBlockId(par2, par3 + 1, par4 + 1) == Block.redstoneWire.blockID) {
/*      */         
/* 2871 */         var5.setColorOpaque_F(var11 * var13, var11 * var14, var11 * var15);
/* 2872 */         var5.addVertexWithUV((par2 + 1), ((par3 + 1) + 0.021875F), (par4 + 1) - 0.015625D, var8.getMaxU(), var8.getMinV());
/* 2873 */         var5.addVertexWithUV((par2 + 1), (par3 + 0), (par4 + 1) - 0.015625D, var8.getMinU(), var8.getMinV());
/* 2874 */         var5.addVertexWithUV((par2 + 0), (par3 + 0), (par4 + 1) - 0.015625D, var8.getMinU(), var8.getMaxV());
/* 2875 */         var5.addVertexWithUV((par2 + 0), ((par3 + 1) + 0.021875F), (par4 + 1) - 0.015625D, var8.getMaxU(), var8.getMaxV());
/* 2876 */         var5.setColorOpaque_F(var11, var11, var11);
/* 2877 */         var5.addVertexWithUV((par2 + 1), ((par3 + 1) + 0.021875F), (par4 + 1) - 0.015625D, var10.getMaxU(), var10.getMinV());
/* 2878 */         var5.addVertexWithUV((par2 + 1), (par3 + 0), (par4 + 1) - 0.015625D, var10.getMinU(), var10.getMinV());
/* 2879 */         var5.addVertexWithUV((par2 + 0), (par3 + 0), (par4 + 1) - 0.015625D, var10.getMinU(), var10.getMaxV());
/* 2880 */         var5.addVertexWithUV((par2 + 0), ((par3 + 1) + 0.021875F), (par4 + 1) - 0.015625D, var10.getMaxU(), var10.getMaxV());
/*      */       } 
/*      */     } 
/*      */     
/* 2884 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockMinecartTrack(BlockRailBase par1BlockRailBase, int par2, int par3, int par4) {
/* 2892 */     Tessellator var5 = Tessellator.instance;
/* 2893 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2901 */     Icon var7 = (this.overrideBlockTexture == null) ? getBlockIconFromSideAndMetadata(par1BlockRailBase, 0, var6) : this.overrideBlockTexture;
/*      */     
/* 2903 */     if (par1BlockRailBase.isPowered())
/*      */     {
/* 2905 */       var6 &= 0x7;
/*      */     }
/*      */     
/* 2908 */     var5.setBrightness(par1BlockRailBase.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 2909 */     var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
/* 2910 */     double var8 = var7.getMinU();
/* 2911 */     double var10 = var7.getMinV();
/* 2912 */     double var12 = var7.getMaxU();
/* 2913 */     double var14 = var7.getMaxV();
/* 2914 */     double var16 = 0.0625D;
/* 2915 */     double var18 = (par2 + 1);
/* 2916 */     double var20 = (par2 + 1);
/* 2917 */     double var22 = (par2 + 0);
/* 2918 */     double var24 = (par2 + 0);
/* 2919 */     double var26 = (par4 + 0);
/* 2920 */     double var28 = (par4 + 1);
/* 2921 */     double var30 = (par4 + 1);
/* 2922 */     double var32 = (par4 + 0);
/* 2923 */     double var34 = par3 + var16;
/* 2924 */     double var36 = par3 + var16;
/* 2925 */     double var38 = par3 + var16;
/* 2926 */     double var40 = par3 + var16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2932 */     var18 = var20 = (par2 + 0);
/* 2933 */     var22 = var24 = (par2 + 1);
/* 2934 */     var26 = var32 = (par4 + 1);
/* 2935 */     var28 = var30 = (par4 + 0);
/*      */     
/* 2937 */     if (var6 == 9) {
/*      */       
/* 2939 */       var18 = var24 = (par2 + 0);
/* 2940 */       var20 = var22 = (par2 + 1);
/* 2941 */       var26 = var28 = (par4 + 0);
/* 2942 */       var30 = var32 = (par4 + 1);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2947 */     var18 = var24 = (par2 + 1);
/* 2948 */     var20 = var22 = (par2 + 0);
/* 2949 */     var26 = var28 = (par4 + 1);
/* 2950 */     var30 = var32 = (par4 + 0);
/*      */ 
/*      */     
/* 2953 */     if (var6 != 2 && var6 != 4) {
/*      */       
/* 2955 */       if (var6 == 3 || var6 == 5)
/*      */       {
/* 2957 */         var36++;
/* 2958 */         var38++;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 2963 */       var34++;
/* 2964 */       var40++;
/*      */     } 
/*      */     
/* 2967 */     var5.addVertexWithUV(var18, var34, var26, var12, var10);
/* 2968 */     var5.addVertexWithUV(var20, var36, var28, var12, var14);
/* 2969 */     var5.addVertexWithUV(var22, var38, var30, var8, var14);
/* 2970 */     var5.addVertexWithUV(var24, var40, var32, var8, var10);
/* 2971 */     var5.addVertexWithUV(var24, var40, var32, var8, var10);
/* 2972 */     var5.addVertexWithUV(var22, var38, var30, var8, var14);
/* 2973 */     var5.addVertexWithUV(var20, var36, var28, var12, var14);
/* 2974 */     var5.addVertexWithUV(var18, var34, var26, var12, var10);
/* 2975 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockLadder(Block par1Block, int par2, int par3, int par4) {
/* 2983 */     Tessellator var5 = Tessellator.instance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2991 */     Icon var6 = (this.overrideBlockTexture == null) ? getBlockIconFromSide(par1Block, 0) : this.overrideBlockTexture;
/*      */     
/* 2993 */     var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 2994 */     float var7 = 1.0F;
/* 2995 */     var5.setColorOpaque_F(var7, var7, var7);
/* 2996 */     double var20 = var6.getMinU();
/* 2997 */     double var9 = var6.getMinV();
/* 2998 */     double var11 = var6.getMaxU();
/* 2999 */     double var13 = var6.getMaxV();
/* 3000 */     int var15 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 3001 */     double var16 = 0.0D;
/* 3002 */     double var18 = 0.05000000074505806D;
/*      */     
/* 3004 */     if (var15 == 5) {
/*      */       
/* 3006 */       var5.addVertexWithUV(par2 + var18, (par3 + 1) + var16, (par4 + 1) + var16, var20, var9);
/* 3007 */       var5.addVertexWithUV(par2 + var18, (par3 + 0) - var16, (par4 + 1) + var16, var20, var13);
/* 3008 */       var5.addVertexWithUV(par2 + var18, (par3 + 0) - var16, (par4 + 0) - var16, var11, var13);
/* 3009 */       var5.addVertexWithUV(par2 + var18, (par3 + 1) + var16, (par4 + 0) - var16, var11, var9);
/*      */     } 
/*      */     
/* 3012 */     if (var15 == 4) {
/*      */       
/* 3014 */       var5.addVertexWithUV((par2 + 1) - var18, (par3 + 0) - var16, (par4 + 1) + var16, var11, var13);
/* 3015 */       var5.addVertexWithUV((par2 + 1) - var18, (par3 + 1) + var16, (par4 + 1) + var16, var11, var9);
/* 3016 */       var5.addVertexWithUV((par2 + 1) - var18, (par3 + 1) + var16, (par4 + 0) - var16, var20, var9);
/* 3017 */       var5.addVertexWithUV((par2 + 1) - var18, (par3 + 0) - var16, (par4 + 0) - var16, var20, var13);
/*      */     } 
/*      */     
/* 3020 */     if (var15 == 3) {
/*      */       
/* 3022 */       var5.addVertexWithUV((par2 + 1) + var16, (par3 + 0) - var16, par4 + var18, var11, var13);
/* 3023 */       var5.addVertexWithUV((par2 + 1) + var16, (par3 + 1) + var16, par4 + var18, var11, var9);
/* 3024 */       var5.addVertexWithUV((par2 + 0) - var16, (par3 + 1) + var16, par4 + var18, var20, var9);
/* 3025 */       var5.addVertexWithUV((par2 + 0) - var16, (par3 + 0) - var16, par4 + var18, var20, var13);
/*      */     } 
/*      */     
/* 3028 */     if (var15 == 2) {
/*      */       
/* 3030 */       var5.addVertexWithUV((par2 + 1) + var16, (par3 + 1) + var16, (par4 + 1) - var18, var20, var9);
/* 3031 */       var5.addVertexWithUV((par2 + 1) + var16, (par3 + 0) - var16, (par4 + 1) - var18, var20, var13);
/* 3032 */       var5.addVertexWithUV((par2 + 0) - var16, (par3 + 0) - var16, (par4 + 1) - var18, var11, var13);
/* 3033 */       var5.addVertexWithUV((par2 + 0) - var16, (par3 + 1) + var16, (par4 + 1) - var18, var11, var9);
/*      */     } 
/*      */     
/* 3036 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockVine(Block par1Block, int par2, int par3, int par4) {
/* 3045 */     Tessellator var5 = Tessellator.instance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3053 */     Icon var6 = (this.overrideBlockTexture == null) ? getBlockIconFromSide(par1Block, 0) : this.overrideBlockTexture;
/*      */     
/* 3055 */     float var7 = 1.0F;
/* 3056 */     var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 3057 */     int var8 = par1Block.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 3058 */     float var9 = (var8 >> 16 & 0xFF) / 255.0F;
/* 3059 */     float var10 = (var8 >> 8 & 0xFF) / 255.0F;
/* 3060 */     float var11 = (var8 & 0xFF) / 255.0F;
/* 3061 */     var5.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
/* 3062 */     double var19 = var6.getMinU();
/* 3063 */     double var20 = var6.getMinV();
/* 3064 */     double var12 = var6.getMaxU();
/* 3065 */     double var14 = var6.getMaxV();
/* 3066 */     double var16 = 0.05000000074505806D;
/* 3067 */     int var18 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*      */     
/* 3069 */     if ((var18 & 0x2) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3082 */       if (RenderingScheme.current == 0) {
/*      */         
/* 3084 */         var5.addVertexWithUV(par2 + var16, (par3 + 1), (par4 + 1), var19, var20);
/* 3085 */         var5.addVertexWithUV(par2 + var16, (par3 + 0), (par4 + 1), var19, var14);
/* 3086 */         var5.addVertexWithUV(par2 + var16, (par3 + 0), (par4 + 0), var12, var14);
/* 3087 */         var5.addVertexWithUV(par2 + var16, (par3 + 1), (par4 + 0), var12, var20);
/* 3088 */         var5.addVertexWithUV(par2 + var16, (par3 + 1), (par4 + 0), var12, var20);
/* 3089 */         var5.addVertexWithUV(par2 + var16, (par3 + 0), (par4 + 0), var12, var14);
/* 3090 */         var5.addVertexWithUV(par2 + var16, (par3 + 0), (par4 + 1), var19, var14);
/* 3091 */         var5.addVertexWithUV(par2 + var16, (par3 + 1), (par4 + 1), var19, var20);
/*      */       }
/*      */       else {
/*      */         
/* 3095 */         this.x[0] = par2 + var16;
/* 3096 */         this.y[0] = (par3 + 1);
/* 3097 */         this.z[0] = (par4 + 1);
/* 3098 */         this.u[0] = var19;
/* 3099 */         this.v[0] = var20;
/*      */         
/* 3101 */         this.x[1] = par2 + var16;
/* 3102 */         this.y[1] = par3;
/* 3103 */         this.z[1] = (par4 + 1);
/* 3104 */         this.u[1] = var19;
/* 3105 */         this.v[1] = var14;
/*      */         
/* 3107 */         this.x[2] = par2 + var16;
/* 3108 */         this.y[2] = par3;
/* 3109 */         this.z[2] = par4;
/* 3110 */         this.u[2] = var12;
/* 3111 */         this.v[2] = var14;
/*      */         
/* 3113 */         this.x[3] = par2 + var16;
/* 3114 */         this.y[3] = (par3 + 1);
/* 3115 */         this.z[3] = par4;
/* 3116 */         this.u[3] = var12;
/* 3117 */         this.v[3] = var20;
/*      */         
/* 3119 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */         
/* 3121 */         this.x[0] = par2 + var16;
/* 3122 */         this.y[0] = (par3 + 1);
/* 3123 */         this.z[0] = par4;
/* 3124 */         this.u[0] = var12;
/* 3125 */         this.v[0] = var20;
/*      */         
/* 3127 */         this.x[1] = par2 + var16;
/* 3128 */         this.y[1] = par3;
/* 3129 */         this.z[1] = par4;
/* 3130 */         this.u[1] = var12;
/* 3131 */         this.v[1] = var14;
/*      */         
/* 3133 */         this.x[2] = par2 + var16;
/* 3134 */         this.y[2] = par3;
/* 3135 */         this.z[2] = (par4 + 1);
/* 3136 */         this.u[2] = var19;
/* 3137 */         this.v[2] = var14;
/*      */         
/* 3139 */         this.x[3] = par2 + var16;
/* 3140 */         this.y[3] = (par3 + 1);
/* 3141 */         this.z[3] = (par4 + 1);
/* 3142 */         this.u[3] = var19;
/* 3143 */         this.v[3] = var20;
/*      */         
/* 3145 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3151 */     if ((var18 & 0x8) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3164 */       if (RenderingScheme.current == 0) {
/*      */         
/* 3166 */         var5.addVertexWithUV((par2 + 1) - var16, (par3 + 0), (par4 + 1), var12, var14);
/* 3167 */         var5.addVertexWithUV((par2 + 1) - var16, (par3 + 1), (par4 + 1), var12, var20);
/* 3168 */         var5.addVertexWithUV((par2 + 1) - var16, (par3 + 1), (par4 + 0), var19, var20);
/* 3169 */         var5.addVertexWithUV((par2 + 1) - var16, (par3 + 0), (par4 + 0), var19, var14);
/* 3170 */         var5.addVertexWithUV((par2 + 1) - var16, (par3 + 0), (par4 + 0), var19, var14);
/* 3171 */         var5.addVertexWithUV((par2 + 1) - var16, (par3 + 1), (par4 + 0), var19, var20);
/* 3172 */         var5.addVertexWithUV((par2 + 1) - var16, (par3 + 1), (par4 + 1), var12, var20);
/* 3173 */         var5.addVertexWithUV((par2 + 1) - var16, (par3 + 0), (par4 + 1), var12, var14);
/*      */       }
/*      */       else {
/*      */         
/* 3177 */         this.x[0] = (par2 + 1) - var16;
/* 3178 */         this.y[0] = par3;
/* 3179 */         this.z[0] = (par4 + 1);
/* 3180 */         this.u[0] = var12;
/* 3181 */         this.v[0] = var14;
/*      */         
/* 3183 */         this.x[1] = (par2 + 1) - var16;
/* 3184 */         this.y[1] = (par3 + 1);
/* 3185 */         this.z[1] = (par4 + 1);
/* 3186 */         this.u[1] = var12;
/* 3187 */         this.v[1] = var20;
/*      */         
/* 3189 */         this.x[2] = (par2 + 1) - var16;
/* 3190 */         this.y[2] = (par3 + 1);
/* 3191 */         this.z[2] = par4;
/* 3192 */         this.u[2] = var19;
/* 3193 */         this.v[2] = var20;
/*      */         
/* 3195 */         this.x[3] = (par2 + 1) - var16;
/* 3196 */         this.y[3] = par3;
/* 3197 */         this.z[3] = par4;
/* 3198 */         this.u[3] = var19;
/* 3199 */         this.v[3] = var14;
/*      */         
/* 3201 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */         
/* 3203 */         this.x[0] = (par2 + 1) - var16;
/* 3204 */         this.y[0] = par3;
/* 3205 */         this.z[0] = par4;
/* 3206 */         this.u[0] = var19;
/* 3207 */         this.v[0] = var14;
/*      */         
/* 3209 */         this.x[1] = (par2 + 1) - var16;
/* 3210 */         this.y[1] = (par3 + 1);
/* 3211 */         this.z[1] = par4;
/* 3212 */         this.u[1] = var19;
/* 3213 */         this.v[1] = var20;
/*      */         
/* 3215 */         this.x[2] = (par2 + 1) - var16;
/* 3216 */         this.y[2] = (par3 + 1);
/* 3217 */         this.z[2] = (par4 + 1);
/* 3218 */         this.u[2] = var12;
/* 3219 */         this.v[2] = var20;
/*      */         
/* 3221 */         this.x[3] = (par2 + 1) - var16;
/* 3222 */         this.y[3] = par3;
/* 3223 */         this.z[3] = (par4 + 1);
/* 3224 */         this.u[3] = var12;
/* 3225 */         this.v[3] = var14;
/*      */         
/* 3227 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3233 */     if ((var18 & 0x4) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3246 */       if (RenderingScheme.current == 0) {
/*      */         
/* 3248 */         var5.addVertexWithUV((par2 + 1), (par3 + 0), par4 + var16, var12, var14);
/* 3249 */         var5.addVertexWithUV((par2 + 1), (par3 + 1), par4 + var16, var12, var20);
/* 3250 */         var5.addVertexWithUV((par2 + 0), (par3 + 1), par4 + var16, var19, var20);
/* 3251 */         var5.addVertexWithUV((par2 + 0), (par3 + 0), par4 + var16, var19, var14);
/* 3252 */         var5.addVertexWithUV((par2 + 0), (par3 + 0), par4 + var16, var19, var14);
/* 3253 */         var5.addVertexWithUV((par2 + 0), (par3 + 1), par4 + var16, var19, var20);
/* 3254 */         var5.addVertexWithUV((par2 + 1), (par3 + 1), par4 + var16, var12, var20);
/* 3255 */         var5.addVertexWithUV((par2 + 1), (par3 + 0), par4 + var16, var12, var14);
/*      */       }
/*      */       else {
/*      */         
/* 3259 */         this.x[0] = (par2 + 1);
/* 3260 */         this.y[0] = par3;
/* 3261 */         this.z[0] = par4 + var16;
/* 3262 */         this.u[0] = var12;
/* 3263 */         this.v[0] = var14;
/*      */         
/* 3265 */         this.x[1] = (par2 + 1);
/* 3266 */         this.y[1] = (par3 + 1);
/* 3267 */         this.z[1] = par4 + var16;
/* 3268 */         this.u[1] = var12;
/* 3269 */         this.v[1] = var20;
/*      */         
/* 3271 */         this.x[2] = par2;
/* 3272 */         this.y[2] = (par3 + 1);
/* 3273 */         this.z[2] = par4 + var16;
/* 3274 */         this.u[2] = var19;
/* 3275 */         this.v[2] = var20;
/*      */         
/* 3277 */         this.x[3] = par2;
/* 3278 */         this.y[3] = par3;
/* 3279 */         this.z[3] = par4 + var16;
/* 3280 */         this.u[3] = var19;
/* 3281 */         this.v[3] = var14;
/*      */         
/* 3283 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */         
/* 3285 */         this.x[0] = par2;
/* 3286 */         this.y[0] = par3;
/* 3287 */         this.z[0] = par4 + var16;
/* 3288 */         this.u[0] = var19;
/* 3289 */         this.v[0] = var14;
/*      */         
/* 3291 */         this.x[1] = par2;
/* 3292 */         this.y[1] = (par3 + 1);
/* 3293 */         this.z[1] = par4 + var16;
/* 3294 */         this.u[1] = var19;
/* 3295 */         this.v[1] = var20;
/*      */         
/* 3297 */         this.x[2] = (par2 + 1);
/* 3298 */         this.y[2] = (par3 + 1);
/* 3299 */         this.z[2] = par4 + var16;
/* 3300 */         this.u[2] = var12;
/* 3301 */         this.v[2] = var20;
/*      */         
/* 3303 */         this.x[3] = (par2 + 1);
/* 3304 */         this.y[3] = par3;
/* 3305 */         this.z[3] = par4 + var16;
/* 3306 */         this.u[3] = var12;
/* 3307 */         this.v[3] = var14;
/*      */         
/* 3309 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3315 */     if ((var18 & 0x1) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3328 */       if (RenderingScheme.current == 0) {
/*      */         
/* 3330 */         var5.addVertexWithUV((par2 + 1), (par3 + 1), (par4 + 1) - var16, var19, var20);
/* 3331 */         var5.addVertexWithUV((par2 + 1), (par3 + 0), (par4 + 1) - var16, var19, var14);
/* 3332 */         var5.addVertexWithUV((par2 + 0), (par3 + 0), (par4 + 1) - var16, var12, var14);
/* 3333 */         var5.addVertexWithUV((par2 + 0), (par3 + 1), (par4 + 1) - var16, var12, var20);
/* 3334 */         var5.addVertexWithUV((par2 + 0), (par3 + 1), (par4 + 1) - var16, var12, var20);
/* 3335 */         var5.addVertexWithUV((par2 + 0), (par3 + 0), (par4 + 1) - var16, var12, var14);
/* 3336 */         var5.addVertexWithUV((par2 + 1), (par3 + 0), (par4 + 1) - var16, var19, var14);
/* 3337 */         var5.addVertexWithUV((par2 + 1), (par3 + 1), (par4 + 1) - var16, var19, var20);
/*      */       }
/*      */       else {
/*      */         
/* 3341 */         this.x[0] = (par2 + 1);
/* 3342 */         this.y[0] = (par3 + 1);
/* 3343 */         this.z[0] = (par4 + 1) - var16;
/* 3344 */         this.u[0] = var19;
/* 3345 */         this.v[0] = var20;
/*      */         
/* 3347 */         this.x[1] = (par2 + 1);
/* 3348 */         this.y[1] = par3;
/* 3349 */         this.z[1] = (par4 + 1) - var16;
/* 3350 */         this.u[1] = var19;
/* 3351 */         this.v[1] = var14;
/*      */         
/* 3353 */         this.x[2] = par2;
/* 3354 */         this.y[2] = par3;
/* 3355 */         this.z[2] = (par4 + 1) - var16;
/* 3356 */         this.u[2] = var12;
/* 3357 */         this.v[2] = var14;
/*      */         
/* 3359 */         this.x[3] = par2;
/* 3360 */         this.y[3] = (par3 + 1);
/* 3361 */         this.z[3] = (par4 + 1) - var16;
/* 3362 */         this.u[3] = var12;
/* 3363 */         this.v[3] = var20;
/*      */         
/* 3365 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */         
/* 3367 */         this.x[0] = par2;
/* 3368 */         this.y[0] = (par3 + 1);
/* 3369 */         this.z[0] = (par4 + 1) - var16;
/* 3370 */         this.u[0] = var12;
/* 3371 */         this.v[0] = var20;
/*      */         
/* 3373 */         this.x[1] = par2;
/* 3374 */         this.y[1] = par3;
/* 3375 */         this.z[1] = (par4 + 1) - var16;
/* 3376 */         this.u[1] = var12;
/* 3377 */         this.v[1] = var14;
/*      */         
/* 3379 */         this.x[2] = (par2 + 1);
/* 3380 */         this.y[2] = par3;
/* 3381 */         this.z[2] = (par4 + 1) - var16;
/* 3382 */         this.u[2] = var19;
/* 3383 */         this.v[2] = var14;
/*      */         
/* 3385 */         this.x[3] = (par2 + 1);
/* 3386 */         this.y[3] = (par3 + 1);
/* 3387 */         this.z[3] = (par4 + 1) - var16;
/* 3388 */         this.u[3] = var19;
/* 3389 */         this.v[3] = var20;
/*      */         
/* 3391 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3397 */     if (this.blockAccess.isBlockNormalCube(par2, par3 + 1, par4))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3406 */       if (RenderingScheme.current == 0) {
/*      */         
/* 3408 */         var5.addVertexWithUV((par2 + 1), (par3 + 1) - var16, (par4 + 0), var19, var20);
/* 3409 */         var5.addVertexWithUV((par2 + 1), (par3 + 1) - var16, (par4 + 1), var19, var14);
/* 3410 */         var5.addVertexWithUV((par2 + 0), (par3 + 1) - var16, (par4 + 1), var12, var14);
/* 3411 */         var5.addVertexWithUV((par2 + 0), (par3 + 1) - var16, (par4 + 0), var12, var20);
/*      */       }
/*      */       else {
/*      */         
/* 3415 */         this.x[0] = (par2 + 1);
/* 3416 */         this.y[0] = (par3 + 1) - var16;
/* 3417 */         this.z[0] = par4;
/* 3418 */         this.u[0] = var19;
/* 3419 */         this.v[0] = var20;
/*      */         
/* 3421 */         this.x[1] = (par2 + 1);
/* 3422 */         this.y[1] = (par3 + 1) - var16;
/* 3423 */         this.z[1] = (par4 + 1);
/* 3424 */         this.u[1] = var19;
/* 3425 */         this.v[1] = var14;
/*      */         
/* 3427 */         this.x[2] = par2;
/* 3428 */         this.y[2] = (par3 + 1) - var16;
/* 3429 */         this.z[2] = (par4 + 1);
/* 3430 */         this.u[2] = var12;
/* 3431 */         this.v[2] = var14;
/*      */         
/* 3433 */         this.x[3] = par2;
/* 3434 */         this.y[3] = (par3 + 1) - var16;
/* 3435 */         this.z[3] = par4;
/* 3436 */         this.u[3] = var12;
/* 3437 */         this.v[3] = var20;
/*      */         
/* 3439 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3445 */     return true;
/*      */   }
/*      */   
/*      */   public boolean renderBlockPane(BlockPane par1BlockPane, int par2, int par3, int par4) {
/*      */     Icon var64, var65;
/* 3450 */     int var5 = this.blockAccess.getHeight();
/* 3451 */     Tessellator var6 = Tessellator.instance;
/* 3452 */     var6.setBrightness(par1BlockPane.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 3453 */     float var7 = 1.0F;
/* 3454 */     int var8 = par1BlockPane.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 3455 */     float var9 = (var8 >> 16 & 0xFF) / 255.0F;
/* 3456 */     float var10 = (var8 >> 8 & 0xFF) / 255.0F;
/* 3457 */     float var11 = (var8 & 0xFF) / 255.0F;
/*      */     
/* 3459 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/* 3461 */       float var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
/* 3462 */       float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
/* 3463 */       float var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
/* 3464 */       var9 = var12;
/* 3465 */       var10 = var13;
/* 3466 */       var11 = var14;
/*      */     } 
/*      */     
/* 3469 */     var6.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3474 */     if (this.overrideBlockTexture != null) {
/*      */       
/* 3476 */       var64 = this.overrideBlockTexture;
/* 3477 */       var65 = this.overrideBlockTexture;
/*      */     }
/*      */     else {
/*      */       
/* 3481 */       int var66 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 3482 */       var64 = getBlockIconFromSideAndMetadata(par1BlockPane, 0, var66);
/* 3483 */       var65 = par1BlockPane.getSideTextureIndex();
/*      */     } 
/*      */     
/* 3486 */     double var67 = var64.getMinU();
/* 3487 */     double var16 = var64.getInterpolatedU(8.0D);
/* 3488 */     double var18 = var64.getMaxU();
/* 3489 */     double var20 = var64.getMinV();
/* 3490 */     double var22 = var64.getMaxV();
/* 3491 */     double var24 = var65.getInterpolatedU(7.0D);
/* 3492 */     double var26 = var65.getInterpolatedU(9.0D);
/* 3493 */     double var28 = var65.getMinV();
/* 3494 */     double var30 = var65.getInterpolatedV(8.0D);
/* 3495 */     double var32 = var65.getMaxV();
/* 3496 */     double var34 = par2;
/* 3497 */     double var36 = par2 + 0.5D;
/* 3498 */     double var38 = (par2 + 1);
/* 3499 */     double var40 = par4;
/* 3500 */     double var42 = par4 + 0.5D;
/* 3501 */     double var44 = (par4 + 1);
/* 3502 */     double var46 = par2 + 0.5D - 0.0625D;
/* 3503 */     double var48 = par2 + 0.5D + 0.0625D;
/* 3504 */     double var50 = par4 + 0.5D - 0.0625D;
/* 3505 */     double var52 = par4 + 0.5D + 0.0625D;
/* 3506 */     boolean var54 = par1BlockPane.canThisPaneConnectToThisBlockID(this.blockAccess.getBlockId(par2, par3, par4 - 1));
/* 3507 */     boolean var55 = par1BlockPane.canThisPaneConnectToThisBlockID(this.blockAccess.getBlockId(par2, par3, par4 + 1));
/* 3508 */     boolean var56 = par1BlockPane.canThisPaneConnectToThisBlockID(this.blockAccess.getBlockId(par2 - 1, par3, par4));
/* 3509 */     boolean var57 = par1BlockPane.canThisPaneConnectToThisBlockID(this.blockAccess.getBlockId(par2 + 1, par3, par4));
/* 3510 */     boolean var58 = par1BlockPane.shouldSideBeRendered(this.blockAccess, par2, par3 + 1, par4, 1);
/* 3511 */     boolean var59 = par1BlockPane.shouldSideBeRendered(this.blockAccess, par2, par3 - 1, par4, 0);
/* 3512 */     double var60 = 0.01D;
/* 3513 */     double var62 = 0.005D;
/*      */     
/* 3515 */     if ((!var56 || !var57) && (var56 || var57 || var54 || var55)) {
/*      */       
/* 3517 */       if (var56 && !var57) {
/*      */         
/* 3519 */         var6.addVertexWithUV(var34, (par3 + 1), var42, var67, var20);
/* 3520 */         var6.addVertexWithUV(var34, (par3 + 0), var42, var67, var22);
/* 3521 */         var6.addVertexWithUV(var36, (par3 + 0), var42, var16, var22);
/* 3522 */         var6.addVertexWithUV(var36, (par3 + 1), var42, var16, var20);
/* 3523 */         var6.addVertexWithUV(var36, (par3 + 1), var42, var67, var20);
/* 3524 */         var6.addVertexWithUV(var36, (par3 + 0), var42, var67, var22);
/* 3525 */         var6.addVertexWithUV(var34, (par3 + 0), var42, var16, var22);
/* 3526 */         var6.addVertexWithUV(var34, (par3 + 1), var42, var16, var20);
/*      */         
/* 3528 */         if (!var55 && !var54) {
/*      */           
/* 3530 */           var6.addVertexWithUV(var36, (par3 + 1), var52, var24, var28);
/* 3531 */           var6.addVertexWithUV(var36, (par3 + 0), var52, var24, var32);
/* 3532 */           var6.addVertexWithUV(var36, (par3 + 0), var50, var26, var32);
/* 3533 */           var6.addVertexWithUV(var36, (par3 + 1), var50, var26, var28);
/* 3534 */           var6.addVertexWithUV(var36, (par3 + 1), var50, var24, var28);
/* 3535 */           var6.addVertexWithUV(var36, (par3 + 0), var50, var24, var32);
/* 3536 */           var6.addVertexWithUV(var36, (par3 + 0), var52, var26, var32);
/* 3537 */           var6.addVertexWithUV(var36, (par3 + 1), var52, var26, var28);
/*      */         } 
/*      */         
/* 3540 */         if (var58 || (par3 < var5 - 1 && this.blockAccess.isAirBlock(par2 - 1, par3 + 1, par4))) {
/*      */           
/* 3542 */           var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var52, var26, var30);
/* 3543 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var52, var26, var32);
/* 3544 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var50, var24, var32);
/* 3545 */           var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var50, var24, var30);
/* 3546 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var52, var26, var30);
/* 3547 */           var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var52, var26, var32);
/* 3548 */           var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var50, var24, var32);
/* 3549 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var50, var24, var30);
/*      */         } 
/*      */         
/* 3552 */         if (var59 || (par3 > 1 && this.blockAccess.isAirBlock(par2 - 1, par3 - 1, par4)))
/*      */         {
/* 3554 */           var6.addVertexWithUV(var34, par3 - 0.01D, var52, var26, var30);
/* 3555 */           var6.addVertexWithUV(var36, par3 - 0.01D, var52, var26, var32);
/* 3556 */           var6.addVertexWithUV(var36, par3 - 0.01D, var50, var24, var32);
/* 3557 */           var6.addVertexWithUV(var34, par3 - 0.01D, var50, var24, var30);
/* 3558 */           var6.addVertexWithUV(var36, par3 - 0.01D, var52, var26, var30);
/* 3559 */           var6.addVertexWithUV(var34, par3 - 0.01D, var52, var26, var32);
/* 3560 */           var6.addVertexWithUV(var34, par3 - 0.01D, var50, var24, var32);
/* 3561 */           var6.addVertexWithUV(var36, par3 - 0.01D, var50, var24, var30);
/*      */         }
/*      */       
/* 3564 */       } else if (!var56 && var57) {
/*      */         
/* 3566 */         var6.addVertexWithUV(var36, (par3 + 1), var42, var16, var20);
/* 3567 */         var6.addVertexWithUV(var36, (par3 + 0), var42, var16, var22);
/* 3568 */         var6.addVertexWithUV(var38, (par3 + 0), var42, var18, var22);
/* 3569 */         var6.addVertexWithUV(var38, (par3 + 1), var42, var18, var20);
/* 3570 */         var6.addVertexWithUV(var38, (par3 + 1), var42, var16, var20);
/* 3571 */         var6.addVertexWithUV(var38, (par3 + 0), var42, var16, var22);
/* 3572 */         var6.addVertexWithUV(var36, (par3 + 0), var42, var18, var22);
/* 3573 */         var6.addVertexWithUV(var36, (par3 + 1), var42, var18, var20);
/*      */         
/* 3575 */         if (!var55 && !var54) {
/*      */           
/* 3577 */           var6.addVertexWithUV(var36, (par3 + 1), var50, var24, var28);
/* 3578 */           var6.addVertexWithUV(var36, (par3 + 0), var50, var24, var32);
/* 3579 */           var6.addVertexWithUV(var36, (par3 + 0), var52, var26, var32);
/* 3580 */           var6.addVertexWithUV(var36, (par3 + 1), var52, var26, var28);
/* 3581 */           var6.addVertexWithUV(var36, (par3 + 1), var52, var24, var28);
/* 3582 */           var6.addVertexWithUV(var36, (par3 + 0), var52, var24, var32);
/* 3583 */           var6.addVertexWithUV(var36, (par3 + 0), var50, var26, var32);
/* 3584 */           var6.addVertexWithUV(var36, (par3 + 1), var50, var26, var28);
/*      */         } 
/*      */         
/* 3587 */         if (var58 || (par3 < var5 - 1 && this.blockAccess.isAirBlock(par2 + 1, par3 + 1, par4))) {
/*      */           
/* 3589 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var52, var26, var28);
/* 3590 */           var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var52, var26, var30);
/* 3591 */           var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var50, var24, var30);
/* 3592 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var50, var24, var28);
/* 3593 */           var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var52, var26, var28);
/* 3594 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var52, var26, var30);
/* 3595 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var50, var24, var30);
/* 3596 */           var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var50, var24, var28);
/*      */         } 
/*      */         
/* 3599 */         if (var59 || (par3 > 1 && this.blockAccess.isAirBlock(par2 + 1, par3 - 1, par4)))
/*      */         {
/* 3601 */           var6.addVertexWithUV(var36, par3 - 0.01D, var52, var26, var28);
/* 3602 */           var6.addVertexWithUV(var38, par3 - 0.01D, var52, var26, var30);
/* 3603 */           var6.addVertexWithUV(var38, par3 - 0.01D, var50, var24, var30);
/* 3604 */           var6.addVertexWithUV(var36, par3 - 0.01D, var50, var24, var28);
/* 3605 */           var6.addVertexWithUV(var38, par3 - 0.01D, var52, var26, var28);
/* 3606 */           var6.addVertexWithUV(var36, par3 - 0.01D, var52, var26, var30);
/* 3607 */           var6.addVertexWithUV(var36, par3 - 0.01D, var50, var24, var30);
/* 3608 */           var6.addVertexWithUV(var38, par3 - 0.01D, var50, var24, var28);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 3614 */       var6.addVertexWithUV(var34, (par3 + 1), var42, var67, var20);
/* 3615 */       var6.addVertexWithUV(var34, (par3 + 0), var42, var67, var22);
/* 3616 */       var6.addVertexWithUV(var38, (par3 + 0), var42, var18, var22);
/* 3617 */       var6.addVertexWithUV(var38, (par3 + 1), var42, var18, var20);
/* 3618 */       var6.addVertexWithUV(var38, (par3 + 1), var42, var67, var20);
/* 3619 */       var6.addVertexWithUV(var38, (par3 + 0), var42, var67, var22);
/* 3620 */       var6.addVertexWithUV(var34, (par3 + 0), var42, var18, var22);
/* 3621 */       var6.addVertexWithUV(var34, (par3 + 1), var42, var18, var20);
/*      */       
/* 3623 */       if (var58) {
/*      */         
/* 3625 */         var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var52, var26, var32);
/* 3626 */         var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var52, var26, var28);
/* 3627 */         var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var50, var24, var28);
/* 3628 */         var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var50, var24, var32);
/* 3629 */         var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var52, var26, var32);
/* 3630 */         var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var52, var26, var28);
/* 3631 */         var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var50, var24, var28);
/* 3632 */         var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var50, var24, var32);
/*      */       }
/*      */       else {
/*      */         
/* 3636 */         if (par3 < var5 - 1 && this.blockAccess.isAirBlock(par2 - 1, par3 + 1, par4)) {
/*      */           
/* 3638 */           var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var52, var26, var30);
/* 3639 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var52, var26, var32);
/* 3640 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var50, var24, var32);
/* 3641 */           var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var50, var24, var30);
/* 3642 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var52, var26, var30);
/* 3643 */           var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var52, var26, var32);
/* 3644 */           var6.addVertexWithUV(var34, (par3 + 1) + 0.01D, var50, var24, var32);
/* 3645 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var50, var24, var30);
/*      */         } 
/*      */         
/* 3648 */         if (par3 < var5 - 1 && this.blockAccess.isAirBlock(par2 + 1, par3 + 1, par4)) {
/*      */           
/* 3650 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var52, var26, var28);
/* 3651 */           var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var52, var26, var30);
/* 3652 */           var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var50, var24, var30);
/* 3653 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var50, var24, var28);
/* 3654 */           var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var52, var26, var28);
/* 3655 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var52, var26, var30);
/* 3656 */           var6.addVertexWithUV(var36, (par3 + 1) + 0.01D, var50, var24, var30);
/* 3657 */           var6.addVertexWithUV(var38, (par3 + 1) + 0.01D, var50, var24, var28);
/*      */         } 
/*      */       } 
/*      */       
/* 3661 */       if (var59) {
/*      */         
/* 3663 */         var6.addVertexWithUV(var34, par3 - 0.01D, var52, var26, var32);
/* 3664 */         var6.addVertexWithUV(var38, par3 - 0.01D, var52, var26, var28);
/* 3665 */         var6.addVertexWithUV(var38, par3 - 0.01D, var50, var24, var28);
/* 3666 */         var6.addVertexWithUV(var34, par3 - 0.01D, var50, var24, var32);
/* 3667 */         var6.addVertexWithUV(var38, par3 - 0.01D, var52, var26, var32);
/* 3668 */         var6.addVertexWithUV(var34, par3 - 0.01D, var52, var26, var28);
/* 3669 */         var6.addVertexWithUV(var34, par3 - 0.01D, var50, var24, var28);
/* 3670 */         var6.addVertexWithUV(var38, par3 - 0.01D, var50, var24, var32);
/*      */       }
/*      */       else {
/*      */         
/* 3674 */         if (par3 > 1 && this.blockAccess.isAirBlock(par2 - 1, par3 - 1, par4)) {
/*      */           
/* 3676 */           var6.addVertexWithUV(var34, par3 - 0.01D, var52, var26, var30);
/* 3677 */           var6.addVertexWithUV(var36, par3 - 0.01D, var52, var26, var32);
/* 3678 */           var6.addVertexWithUV(var36, par3 - 0.01D, var50, var24, var32);
/* 3679 */           var6.addVertexWithUV(var34, par3 - 0.01D, var50, var24, var30);
/* 3680 */           var6.addVertexWithUV(var36, par3 - 0.01D, var52, var26, var30);
/* 3681 */           var6.addVertexWithUV(var34, par3 - 0.01D, var52, var26, var32);
/* 3682 */           var6.addVertexWithUV(var34, par3 - 0.01D, var50, var24, var32);
/* 3683 */           var6.addVertexWithUV(var36, par3 - 0.01D, var50, var24, var30);
/*      */         } 
/*      */         
/* 3686 */         if (par3 > 1 && this.blockAccess.isAirBlock(par2 + 1, par3 - 1, par4)) {
/*      */           
/* 3688 */           var6.addVertexWithUV(var36, par3 - 0.01D, var52, var26, var28);
/* 3689 */           var6.addVertexWithUV(var38, par3 - 0.01D, var52, var26, var30);
/* 3690 */           var6.addVertexWithUV(var38, par3 - 0.01D, var50, var24, var30);
/* 3691 */           var6.addVertexWithUV(var36, par3 - 0.01D, var50, var24, var28);
/* 3692 */           var6.addVertexWithUV(var38, par3 - 0.01D, var52, var26, var28);
/* 3693 */           var6.addVertexWithUV(var36, par3 - 0.01D, var52, var26, var30);
/* 3694 */           var6.addVertexWithUV(var36, par3 - 0.01D, var50, var24, var30);
/* 3695 */           var6.addVertexWithUV(var38, par3 - 0.01D, var50, var24, var28);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3700 */     if ((!var54 || !var55) && (var56 || var57 || var54 || var55)) {
/*      */       
/* 3702 */       if (var54 && !var55) {
/*      */         
/* 3704 */         var6.addVertexWithUV(var36, (par3 + 1), var40, var67, var20);
/* 3705 */         var6.addVertexWithUV(var36, (par3 + 0), var40, var67, var22);
/* 3706 */         var6.addVertexWithUV(var36, (par3 + 0), var42, var16, var22);
/* 3707 */         var6.addVertexWithUV(var36, (par3 + 1), var42, var16, var20);
/* 3708 */         var6.addVertexWithUV(var36, (par3 + 1), var42, var67, var20);
/* 3709 */         var6.addVertexWithUV(var36, (par3 + 0), var42, var67, var22);
/* 3710 */         var6.addVertexWithUV(var36, (par3 + 0), var40, var16, var22);
/* 3711 */         var6.addVertexWithUV(var36, (par3 + 1), var40, var16, var20);
/*      */         
/* 3713 */         if (!var57 && !var56) {
/*      */           
/* 3715 */           var6.addVertexWithUV(var46, (par3 + 1), var42, var24, var28);
/* 3716 */           var6.addVertexWithUV(var46, (par3 + 0), var42, var24, var32);
/* 3717 */           var6.addVertexWithUV(var48, (par3 + 0), var42, var26, var32);
/* 3718 */           var6.addVertexWithUV(var48, (par3 + 1), var42, var26, var28);
/* 3719 */           var6.addVertexWithUV(var48, (par3 + 1), var42, var24, var28);
/* 3720 */           var6.addVertexWithUV(var48, (par3 + 0), var42, var24, var32);
/* 3721 */           var6.addVertexWithUV(var46, (par3 + 0), var42, var26, var32);
/* 3722 */           var6.addVertexWithUV(var46, (par3 + 1), var42, var26, var28);
/*      */         } 
/*      */         
/* 3725 */         if (var58 || (par3 < var5 - 1 && this.blockAccess.isAirBlock(par2, par3 + 1, par4 - 1))) {
/*      */           
/* 3727 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var40, var26, var28);
/* 3728 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var42, var26, var30);
/* 3729 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var42, var24, var30);
/* 3730 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var40, var24, var28);
/* 3731 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var42, var26, var28);
/* 3732 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var40, var26, var30);
/* 3733 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var40, var24, var30);
/* 3734 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var42, var24, var28);
/*      */         } 
/*      */         
/* 3737 */         if (var59 || (par3 > 1 && this.blockAccess.isAirBlock(par2, par3 - 1, par4 - 1)))
/*      */         {
/* 3739 */           var6.addVertexWithUV(var46, par3 - 0.005D, var40, var26, var28);
/* 3740 */           var6.addVertexWithUV(var46, par3 - 0.005D, var42, var26, var30);
/* 3741 */           var6.addVertexWithUV(var48, par3 - 0.005D, var42, var24, var30);
/* 3742 */           var6.addVertexWithUV(var48, par3 - 0.005D, var40, var24, var28);
/* 3743 */           var6.addVertexWithUV(var46, par3 - 0.005D, var42, var26, var28);
/* 3744 */           var6.addVertexWithUV(var46, par3 - 0.005D, var40, var26, var30);
/* 3745 */           var6.addVertexWithUV(var48, par3 - 0.005D, var40, var24, var30);
/* 3746 */           var6.addVertexWithUV(var48, par3 - 0.005D, var42, var24, var28);
/*      */         }
/*      */       
/* 3749 */       } else if (!var54 && var55) {
/*      */         
/* 3751 */         var6.addVertexWithUV(var36, (par3 + 1), var42, var16, var20);
/* 3752 */         var6.addVertexWithUV(var36, (par3 + 0), var42, var16, var22);
/* 3753 */         var6.addVertexWithUV(var36, (par3 + 0), var44, var18, var22);
/* 3754 */         var6.addVertexWithUV(var36, (par3 + 1), var44, var18, var20);
/* 3755 */         var6.addVertexWithUV(var36, (par3 + 1), var44, var16, var20);
/* 3756 */         var6.addVertexWithUV(var36, (par3 + 0), var44, var16, var22);
/* 3757 */         var6.addVertexWithUV(var36, (par3 + 0), var42, var18, var22);
/* 3758 */         var6.addVertexWithUV(var36, (par3 + 1), var42, var18, var20);
/*      */         
/* 3760 */         if (!var57 && !var56) {
/*      */           
/* 3762 */           var6.addVertexWithUV(var48, (par3 + 1), var42, var24, var28);
/* 3763 */           var6.addVertexWithUV(var48, (par3 + 0), var42, var24, var32);
/* 3764 */           var6.addVertexWithUV(var46, (par3 + 0), var42, var26, var32);
/* 3765 */           var6.addVertexWithUV(var46, (par3 + 1), var42, var26, var28);
/* 3766 */           var6.addVertexWithUV(var46, (par3 + 1), var42, var24, var28);
/* 3767 */           var6.addVertexWithUV(var46, (par3 + 0), var42, var24, var32);
/* 3768 */           var6.addVertexWithUV(var48, (par3 + 0), var42, var26, var32);
/* 3769 */           var6.addVertexWithUV(var48, (par3 + 1), var42, var26, var28);
/*      */         } 
/*      */         
/* 3772 */         if (var58 || (par3 < var5 - 1 && this.blockAccess.isAirBlock(par2, par3 + 1, par4 + 1))) {
/*      */           
/* 3774 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var42, var24, var30);
/* 3775 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var44, var24, var32);
/* 3776 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var44, var26, var32);
/* 3777 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var42, var26, var30);
/* 3778 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var44, var24, var30);
/* 3779 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var42, var24, var32);
/* 3780 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var42, var26, var32);
/* 3781 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var44, var26, var30);
/*      */         } 
/*      */         
/* 3784 */         if (var59 || (par3 > 1 && this.blockAccess.isAirBlock(par2, par3 - 1, par4 + 1)))
/*      */         {
/* 3786 */           var6.addVertexWithUV(var46, par3 - 0.005D, var42, var24, var30);
/* 3787 */           var6.addVertexWithUV(var46, par3 - 0.005D, var44, var24, var32);
/* 3788 */           var6.addVertexWithUV(var48, par3 - 0.005D, var44, var26, var32);
/* 3789 */           var6.addVertexWithUV(var48, par3 - 0.005D, var42, var26, var30);
/* 3790 */           var6.addVertexWithUV(var46, par3 - 0.005D, var44, var24, var30);
/* 3791 */           var6.addVertexWithUV(var46, par3 - 0.005D, var42, var24, var32);
/* 3792 */           var6.addVertexWithUV(var48, par3 - 0.005D, var42, var26, var32);
/* 3793 */           var6.addVertexWithUV(var48, par3 - 0.005D, var44, var26, var30);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 3799 */       var6.addVertexWithUV(var36, (par3 + 1), var44, var67, var20);
/* 3800 */       var6.addVertexWithUV(var36, (par3 + 0), var44, var67, var22);
/* 3801 */       var6.addVertexWithUV(var36, (par3 + 0), var40, var18, var22);
/* 3802 */       var6.addVertexWithUV(var36, (par3 + 1), var40, var18, var20);
/* 3803 */       var6.addVertexWithUV(var36, (par3 + 1), var40, var67, var20);
/* 3804 */       var6.addVertexWithUV(var36, (par3 + 0), var40, var67, var22);
/* 3805 */       var6.addVertexWithUV(var36, (par3 + 0), var44, var18, var22);
/* 3806 */       var6.addVertexWithUV(var36, (par3 + 1), var44, var18, var20);
/*      */       
/* 3808 */       if (var58) {
/*      */         
/* 3810 */         var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var44, var26, var32);
/* 3811 */         var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var40, var26, var28);
/* 3812 */         var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var40, var24, var28);
/* 3813 */         var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var44, var24, var32);
/* 3814 */         var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var40, var26, var32);
/* 3815 */         var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var44, var26, var28);
/* 3816 */         var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var44, var24, var28);
/* 3817 */         var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var40, var24, var32);
/*      */       }
/*      */       else {
/*      */         
/* 3821 */         if (par3 < var5 - 1 && this.blockAccess.isAirBlock(par2, par3 + 1, par4 - 1)) {
/*      */           
/* 3823 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var40, var26, var28);
/* 3824 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var42, var26, var30);
/* 3825 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var42, var24, var30);
/* 3826 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var40, var24, var28);
/* 3827 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var42, var26, var28);
/* 3828 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var40, var26, var30);
/* 3829 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var40, var24, var30);
/* 3830 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var42, var24, var28);
/*      */         } 
/*      */         
/* 3833 */         if (par3 < var5 - 1 && this.blockAccess.isAirBlock(par2, par3 + 1, par4 + 1)) {
/*      */           
/* 3835 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var42, var24, var30);
/* 3836 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var44, var24, var32);
/* 3837 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var44, var26, var32);
/* 3838 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var42, var26, var30);
/* 3839 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var44, var24, var30);
/* 3840 */           var6.addVertexWithUV(var46, (par3 + 1) + 0.005D, var42, var24, var32);
/* 3841 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var42, var26, var32);
/* 3842 */           var6.addVertexWithUV(var48, (par3 + 1) + 0.005D, var44, var26, var30);
/*      */         } 
/*      */       } 
/*      */       
/* 3846 */       if (var59) {
/*      */         
/* 3848 */         var6.addVertexWithUV(var48, par3 - 0.005D, var44, var26, var32);
/* 3849 */         var6.addVertexWithUV(var48, par3 - 0.005D, var40, var26, var28);
/* 3850 */         var6.addVertexWithUV(var46, par3 - 0.005D, var40, var24, var28);
/* 3851 */         var6.addVertexWithUV(var46, par3 - 0.005D, var44, var24, var32);
/* 3852 */         var6.addVertexWithUV(var48, par3 - 0.005D, var40, var26, var32);
/* 3853 */         var6.addVertexWithUV(var48, par3 - 0.005D, var44, var26, var28);
/* 3854 */         var6.addVertexWithUV(var46, par3 - 0.005D, var44, var24, var28);
/* 3855 */         var6.addVertexWithUV(var46, par3 - 0.005D, var40, var24, var32);
/*      */       }
/*      */       else {
/*      */         
/* 3859 */         if (par3 > 1 && this.blockAccess.isAirBlock(par2, par3 - 1, par4 - 1)) {
/*      */           
/* 3861 */           var6.addVertexWithUV(var46, par3 - 0.005D, var40, var26, var28);
/* 3862 */           var6.addVertexWithUV(var46, par3 - 0.005D, var42, var26, var30);
/* 3863 */           var6.addVertexWithUV(var48, par3 - 0.005D, var42, var24, var30);
/* 3864 */           var6.addVertexWithUV(var48, par3 - 0.005D, var40, var24, var28);
/* 3865 */           var6.addVertexWithUV(var46, par3 - 0.005D, var42, var26, var28);
/* 3866 */           var6.addVertexWithUV(var46, par3 - 0.005D, var40, var26, var30);
/* 3867 */           var6.addVertexWithUV(var48, par3 - 0.005D, var40, var24, var30);
/* 3868 */           var6.addVertexWithUV(var48, par3 - 0.005D, var42, var24, var28);
/*      */         } 
/*      */         
/* 3871 */         if (par3 > 1 && this.blockAccess.isAirBlock(par2, par3 - 1, par4 + 1)) {
/*      */           
/* 3873 */           var6.addVertexWithUV(var46, par3 - 0.005D, var42, var24, var30);
/* 3874 */           var6.addVertexWithUV(var46, par3 - 0.005D, var44, var24, var32);
/* 3875 */           var6.addVertexWithUV(var48, par3 - 0.005D, var44, var26, var32);
/* 3876 */           var6.addVertexWithUV(var48, par3 - 0.005D, var42, var26, var30);
/* 3877 */           var6.addVertexWithUV(var46, par3 - 0.005D, var44, var24, var30);
/* 3878 */           var6.addVertexWithUV(var46, par3 - 0.005D, var42, var24, var32);
/* 3879 */           var6.addVertexWithUV(var48, par3 - 0.005D, var42, var26, var32);
/* 3880 */           var6.addVertexWithUV(var48, par3 - 0.005D, var44, var26, var30);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3885 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderCrossedSquares(Block par1Block, int par2, int par3, int par4) {
/* 3895 */     Tessellator.instance.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 3896 */     float var6 = 1.0F;
/* 3897 */     int var7 = par1Block.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 3898 */     float var8 = (var7 >> 16 & 0xFF) / 255.0F;
/* 3899 */     float var9 = (var7 >> 8 & 0xFF) / 255.0F;
/* 3900 */     float var10 = (var7 & 0xFF) / 255.0F;
/*      */     
/* 3902 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/* 3904 */       float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
/* 3905 */       float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
/* 3906 */       float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
/* 3907 */       var8 = var11;
/* 3908 */       var9 = var12;
/* 3909 */       var10 = var13;
/*      */     } 
/*      */ 
/*      */     
/* 3913 */     Tessellator.instance.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
/* 3914 */     double var19 = par2;
/* 3915 */     double var20 = par3;
/* 3916 */     double var15 = par4;
/*      */     
/* 3918 */     if (par1Block == Block.tallGrass) {
/*      */       
/* 3920 */       long var17 = (par2 * 3129871) ^ par4 * 116129781L ^ par3;
/* 3921 */       var17 = var17 * var17 * 42317861L + var17 * 11L;
/* 3922 */       var19 += (((float)(var17 >> 16L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
/* 3923 */       var20 += (((float)(var17 >> 20L & 0xFL) / 15.0F) - 1.0D) * 0.2D;
/* 3924 */       var15 += (((float)(var17 >> 24L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
/*      */     } 
/*      */     
/* 3927 */     drawCrossedSquares(par1Block, this.blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, 1.0F);
/* 3928 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockStem(Block par1Block, int par2, int par3, int par4) {
/* 3936 */     BlockStem var5 = (BlockStem)par1Block;
/* 3937 */     Tessellator var6 = Tessellator.instance;
/* 3938 */     var6.setBrightness(var5.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 3939 */     float var7 = 1.0F;
/* 3940 */     int var8 = var5.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 3941 */     float var9 = (var8 >> 16 & 0xFF) / 255.0F;
/* 3942 */     float var10 = (var8 >> 8 & 0xFF) / 255.0F;
/* 3943 */     float var11 = (var8 & 0xFF) / 255.0F;
/*      */     
/* 3945 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/* 3947 */       float var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
/* 3948 */       float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
/* 3949 */       float var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
/* 3950 */       var9 = var12;
/* 3951 */       var10 = var13;
/* 3952 */       var11 = var14;
/*      */     } 
/*      */     
/* 3955 */     var6.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
/* 3956 */     var5.setBlockBoundsBasedOnStateAndNeighbors(this.blockAccess, par2, par3, par4);
/* 3957 */     int var15 = var5.getState(this.blockAccess, par2, par3, par4);
/*      */     
/* 3959 */     if (var15 < 0) {
/*      */       
/* 3961 */       renderBlockStemSmall(var5, this.blockAccess.getBlockMetadata(par2, par3, par4), this.renderMaxY, par2, (par3 - 0.0625F), par4);
/*      */     }
/*      */     else {
/*      */       
/* 3965 */       renderBlockStemSmall(var5, this.blockAccess.getBlockMetadata(par2, par3, par4), 0.5D, par2, (par3 - 0.0625F), par4);
/*      */       
/* 3967 */       renderBlockStemBig(var5, this.blockAccess.getBlockMetadata(par2, par3, par4), var15, 1.0D, par2, (par3 - 0.0625F), par4);
/*      */     } 
/*      */     
/* 3970 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockCrops(Block par1Block, int par2, int par3, int par4) {
/* 3978 */     Tessellator var5 = Tessellator.instance;
/* 3979 */     var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 3980 */     var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
/* 3981 */     renderBlockCropsImpl(par1Block, this.blockAccess.getBlockMetadata(par2, par3, par4), par2, (par3 - 0.0625F), par4);
/* 3982 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderTorchAtAngle(Block par1Block, double par2, double par4, double par6, double par8, double par10, int par12) {
/* 3990 */     Tessellator var13 = Tessellator.instance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3998 */     Icon var14 = (this.overrideBlockTexture == null) ? getBlockIconFromSideAndMetadata(par1Block, 0, par12) : this.overrideBlockTexture;
/*      */     
/* 4000 */     double var15 = var14.getMinU();
/* 4001 */     double var17 = var14.getMinV();
/* 4002 */     double var19 = var14.getMaxU();
/* 4003 */     double var21 = var14.getMaxV();
/* 4004 */     double var23 = var14.getInterpolatedU(7.0D);
/* 4005 */     double var25 = var14.getInterpolatedV(6.0D);
/* 4006 */     double var27 = var14.getInterpolatedU(9.0D);
/* 4007 */     double var29 = var14.getInterpolatedV(8.0D);
/* 4008 */     double var31 = var14.getInterpolatedU(7.0D);
/* 4009 */     double var33 = var14.getInterpolatedV(13.0D);
/* 4010 */     double var35 = var14.getInterpolatedU(9.0D);
/* 4011 */     double var37 = var14.getInterpolatedV(15.0D);
/* 4012 */     par2 += 0.5D;
/* 4013 */     par6 += 0.5D;
/* 4014 */     double var39 = par2 - 0.5D;
/* 4015 */     double var41 = par2 + 0.5D;
/* 4016 */     double var43 = par6 - 0.5D;
/* 4017 */     double var45 = par6 + 0.5D;
/* 4018 */     double var47 = 0.0625D;
/* 4019 */     double var49 = 0.625D;
/* 4020 */     var13.addVertexWithUV(par2 + par8 * (1.0D - var49) - var47, par4 + var49, par6 + par10 * (1.0D - var49) - var47, var23, var25);
/* 4021 */     var13.addVertexWithUV(par2 + par8 * (1.0D - var49) - var47, par4 + var49, par6 + par10 * (1.0D - var49) + var47, var23, var29);
/* 4022 */     var13.addVertexWithUV(par2 + par8 * (1.0D - var49) + var47, par4 + var49, par6 + par10 * (1.0D - var49) + var47, var27, var29);
/* 4023 */     var13.addVertexWithUV(par2 + par8 * (1.0D - var49) + var47, par4 + var49, par6 + par10 * (1.0D - var49) - var47, var27, var25);
/* 4024 */     var13.addVertexWithUV(par2 + var47 + par8, par4, par6 - var47 + par10, var35, var33);
/* 4025 */     var13.addVertexWithUV(par2 + var47 + par8, par4, par6 + var47 + par10, var35, var37);
/* 4026 */     var13.addVertexWithUV(par2 - var47 + par8, par4, par6 + var47 + par10, var31, var37);
/* 4027 */     var13.addVertexWithUV(par2 - var47 + par8, par4, par6 - var47 + par10, var31, var33);
/* 4028 */     var13.addVertexWithUV(par2 - var47, par4 + 1.0D, var43, var15, var17);
/* 4029 */     var13.addVertexWithUV(par2 - var47 + par8, par4 + 0.0D, var43 + par10, var15, var21);
/* 4030 */     var13.addVertexWithUV(par2 - var47 + par8, par4 + 0.0D, var45 + par10, var19, var21);
/* 4031 */     var13.addVertexWithUV(par2 - var47, par4 + 1.0D, var45, var19, var17);
/* 4032 */     var13.addVertexWithUV(par2 + var47, par4 + 1.0D, var45, var15, var17);
/* 4033 */     var13.addVertexWithUV(par2 + par8 + var47, par4 + 0.0D, var45 + par10, var15, var21);
/* 4034 */     var13.addVertexWithUV(par2 + par8 + var47, par4 + 0.0D, var43 + par10, var19, var21);
/* 4035 */     var13.addVertexWithUV(par2 + var47, par4 + 1.0D, var43, var19, var17);
/* 4036 */     var13.addVertexWithUV(var39, par4 + 1.0D, par6 + var47, var15, var17);
/* 4037 */     var13.addVertexWithUV(var39 + par8, par4 + 0.0D, par6 + var47 + par10, var15, var21);
/* 4038 */     var13.addVertexWithUV(var41 + par8, par4 + 0.0D, par6 + var47 + par10, var19, var21);
/* 4039 */     var13.addVertexWithUV(var41, par4 + 1.0D, par6 + var47, var19, var17);
/* 4040 */     var13.addVertexWithUV(var41, par4 + 1.0D, par6 - var47, var15, var17);
/* 4041 */     var13.addVertexWithUV(var41 + par8, par4 + 0.0D, par6 - var47 + par10, var15, var21);
/* 4042 */     var13.addVertexWithUV(var39 + par8, par4 + 0.0D, par6 - var47 + par10, var19, var21);
/* 4043 */     var13.addVertexWithUV(var39, par4 + 1.0D, par6 - var47, var19, var17);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawCrossedSquares(Block par1Block, int par2, double par3, double par5, double par7, float par9) {
/* 4052 */     Tessellator var10 = Tessellator.instance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4060 */     Icon var11 = (this.overrideBlockTexture == null) ? getBlockIconFromSideAndMetadata(par1Block, 0, par2) : this.overrideBlockTexture;
/*      */     
/* 4062 */     double var12 = var11.getMinU();
/* 4063 */     double var14 = var11.getMinV();
/* 4064 */     double var16 = var11.getMaxU();
/* 4065 */     double var18 = var11.getMaxV();
/* 4066 */     double var20 = 0.45D * par9;
/* 4067 */     double var22 = par3 + 0.5D - var20;
/* 4068 */     double var24 = par3 + 0.5D + var20;
/* 4069 */     double var26 = par7 + 0.5D - var20;
/* 4070 */     double var28 = par7 + 0.5D + var20;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4093 */     if (RenderingScheme.current == 0) {
/*      */       
/* 4095 */       var10.addVertexWithUV(var22, par5 + par9, var26, var12, var14);
/* 4096 */       var10.addVertexWithUV(var22, par5 + 0.0D, var26, var12, var18);
/* 4097 */       var10.addVertexWithUV(var24, par5 + 0.0D, var28, var16, var18);
/* 4098 */       var10.addVertexWithUV(var24, par5 + par9, var28, var16, var14);
/*      */       
/* 4100 */       var10.addVertexWithUV(var24, par5 + par9, var28, var12, var14);
/* 4101 */       var10.addVertexWithUV(var24, par5 + 0.0D, var28, var12, var18);
/* 4102 */       var10.addVertexWithUV(var22, par5 + 0.0D, var26, var16, var18);
/* 4103 */       var10.addVertexWithUV(var22, par5 + par9, var26, var16, var14);
/*      */       
/* 4105 */       var10.addVertexWithUV(var22, par5 + par9, var28, var12, var14);
/* 4106 */       var10.addVertexWithUV(var22, par5 + 0.0D, var28, var12, var18);
/* 4107 */       var10.addVertexWithUV(var24, par5 + 0.0D, var26, var16, var18);
/* 4108 */       var10.addVertexWithUV(var24, par5 + par9, var26, var16, var14);
/*      */       
/* 4110 */       var10.addVertexWithUV(var24, par5 + par9, var26, var12, var14);
/* 4111 */       var10.addVertexWithUV(var24, par5 + 0.0D, var26, var12, var18);
/* 4112 */       var10.addVertexWithUV(var22, par5 + 0.0D, var28, var16, var18);
/* 4113 */       var10.addVertexWithUV(var22, par5 + par9, var28, var16, var14);
/*      */     }
/*      */     else {
/*      */       
/* 4117 */       this.x[0] = var22;
/* 4118 */       this.y[0] = par5 + par9;
/* 4119 */       this.z[0] = var26;
/* 4120 */       this.u[0] = var12;
/* 4121 */       this.v[0] = var14;
/*      */       
/* 4123 */       this.x[1] = var22;
/* 4124 */       this.y[1] = par5;
/* 4125 */       this.z[1] = var26;
/* 4126 */       this.u[1] = var12;
/* 4127 */       this.v[1] = var18;
/*      */       
/* 4129 */       this.x[2] = var24;
/* 4130 */       this.y[2] = par5;
/* 4131 */       this.z[2] = var28;
/* 4132 */       this.u[2] = var16;
/* 4133 */       this.v[2] = var18;
/*      */       
/* 4135 */       this.x[3] = var24;
/* 4136 */       this.y[3] = par5 + par9;
/* 4137 */       this.z[3] = var28;
/* 4138 */       this.u[3] = var16;
/* 4139 */       this.v[3] = var14;
/*      */       
/* 4141 */       var10.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */       
/* 4143 */       this.x[0] = var24;
/* 4144 */       this.y[0] = par5 + par9;
/* 4145 */       this.z[0] = var28;
/* 4146 */       this.u[0] = var12;
/* 4147 */       this.v[0] = var14;
/*      */       
/* 4149 */       this.x[1] = var24;
/* 4150 */       this.y[1] = par5;
/* 4151 */       this.z[1] = var28;
/* 4152 */       this.u[1] = var12;
/* 4153 */       this.v[1] = var18;
/*      */       
/* 4155 */       this.x[2] = var22;
/* 4156 */       this.y[2] = par5;
/* 4157 */       this.z[2] = var26;
/* 4158 */       this.u[2] = var16;
/* 4159 */       this.v[2] = var18;
/*      */       
/* 4161 */       this.x[3] = var22;
/* 4162 */       this.y[3] = par5 + par9;
/* 4163 */       this.z[3] = var26;
/* 4164 */       this.u[3] = var16;
/* 4165 */       this.v[3] = var14;
/*      */       
/* 4167 */       var10.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */       
/* 4169 */       this.x[0] = var22;
/* 4170 */       this.y[0] = par5 + par9;
/* 4171 */       this.z[0] = var28;
/* 4172 */       this.u[0] = var12;
/* 4173 */       this.v[0] = var14;
/*      */       
/* 4175 */       this.x[1] = var22;
/* 4176 */       this.y[1] = par5;
/* 4177 */       this.z[1] = var28;
/* 4178 */       this.u[1] = var12;
/* 4179 */       this.v[1] = var18;
/*      */       
/* 4181 */       this.x[2] = var24;
/* 4182 */       this.y[2] = par5;
/* 4183 */       this.z[2] = var26;
/* 4184 */       this.u[2] = var16;
/* 4185 */       this.v[2] = var18;
/*      */       
/* 4187 */       this.x[3] = var24;
/* 4188 */       this.y[3] = par5 + par9;
/* 4189 */       this.z[3] = var26;
/* 4190 */       this.u[3] = var16;
/* 4191 */       this.v[3] = var14;
/*      */       
/* 4193 */       var10.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */       
/* 4195 */       this.x[0] = var24;
/* 4196 */       this.y[0] = par5 + par9;
/* 4197 */       this.z[0] = var26;
/* 4198 */       this.u[0] = var12;
/* 4199 */       this.v[0] = var14;
/*      */       
/* 4201 */       this.x[1] = var24;
/* 4202 */       this.y[1] = par5;
/* 4203 */       this.z[1] = var26;
/* 4204 */       this.u[1] = var12;
/* 4205 */       this.v[1] = var18;
/*      */       
/* 4207 */       this.x[2] = var22;
/* 4208 */       this.y[2] = par5;
/* 4209 */       this.z[2] = var28;
/* 4210 */       this.u[2] = var16;
/* 4211 */       this.v[2] = var18;
/*      */       
/* 4213 */       this.x[3] = var22;
/* 4214 */       this.y[3] = par5 + par9;
/* 4215 */       this.z[3] = var28;
/* 4216 */       this.u[3] = var16;
/* 4217 */       this.v[3] = var14;
/*      */       
/* 4219 */       var10.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderBlockStemSmall(Block par1Block, int par2, double par3, double par5, double par7, double par9) {
/* 4230 */     Tessellator var11 = Tessellator.instance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4238 */     Icon var12 = (this.overrideBlockTexture == null) ? getBlockIconFromSideAndMetadata(par1Block, 0, par2) : this.overrideBlockTexture;
/*      */     
/* 4240 */     double var13 = var12.getMinU();
/* 4241 */     double var15 = var12.getMinV();
/* 4242 */     double var17 = var12.getMaxU();
/* 4243 */     double var19 = var12.getInterpolatedV(par3 * 16.0D);
/* 4244 */     double var21 = par5 + 0.5D - 0.44999998807907104D;
/* 4245 */     double var23 = par5 + 0.5D + 0.44999998807907104D;
/* 4246 */     double var25 = par9 + 0.5D - 0.44999998807907104D;
/* 4247 */     double var27 = par9 + 0.5D + 0.44999998807907104D;
/* 4248 */     var11.addVertexWithUV(var21, par7 + par3, var25, var13, var15);
/* 4249 */     var11.addVertexWithUV(var21, par7 + 0.0D, var25, var13, var19);
/* 4250 */     var11.addVertexWithUV(var23, par7 + 0.0D, var27, var17, var19);
/* 4251 */     var11.addVertexWithUV(var23, par7 + par3, var27, var17, var15);
/* 4252 */     var11.addVertexWithUV(var23, par7 + par3, var27, var13, var15);
/* 4253 */     var11.addVertexWithUV(var23, par7 + 0.0D, var27, var13, var19);
/* 4254 */     var11.addVertexWithUV(var21, par7 + 0.0D, var25, var17, var19);
/* 4255 */     var11.addVertexWithUV(var21, par7 + par3, var25, var17, var15);
/* 4256 */     var11.addVertexWithUV(var21, par7 + par3, var27, var13, var15);
/* 4257 */     var11.addVertexWithUV(var21, par7 + 0.0D, var27, var13, var19);
/* 4258 */     var11.addVertexWithUV(var23, par7 + 0.0D, var25, var17, var19);
/* 4259 */     var11.addVertexWithUV(var23, par7 + par3, var25, var17, var15);
/* 4260 */     var11.addVertexWithUV(var23, par7 + par3, var25, var13, var15);
/* 4261 */     var11.addVertexWithUV(var23, par7 + 0.0D, var25, var13, var19);
/* 4262 */     var11.addVertexWithUV(var21, par7 + 0.0D, var27, var17, var19);
/* 4263 */     var11.addVertexWithUV(var21, par7 + par3, var27, var17, var15);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockLilyPad(Block par1Block, int par2, int par3, int par4) {
/* 4271 */     Tessellator var5 = Tessellator.instance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4279 */     Icon var6 = (this.overrideBlockTexture == null) ? getBlockIconFromSide(par1Block, 1) : this.overrideBlockTexture;
/*      */     
/* 4281 */     float var7 = 0.015625F;
/* 4282 */     double var8 = var6.getMinU();
/* 4283 */     double var10 = var6.getMinV();
/* 4284 */     double var12 = var6.getMaxU();
/* 4285 */     double var14 = var6.getMaxV();
/* 4286 */     long var16 = (par2 * 3129871) ^ par4 * 116129781L ^ par3;
/* 4287 */     var16 = var16 * var16 * 42317861L + var16 * 11L;
/* 4288 */     int var18 = (int)(var16 >> 16L & 0x3L);
/* 4289 */     var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 4290 */     float var19 = par2 + 0.5F;
/* 4291 */     float var20 = par4 + 0.5F;
/* 4292 */     float var21 = (var18 & 0x1) * 0.5F * (1 - var18 / 2 % 2 * 2);
/* 4293 */     float var22 = (var18 + 1 & 0x1) * 0.5F * (1 - (var18 + 1) / 2 % 2 * 2);
/* 4294 */     var5.setColorOpaque_I(par1Block.getBlockColor());
/* 4295 */     var5.addVertexWithUV((var19 + var21 - var22), (par3 + var7), (var20 + var21 + var22), var8, var10);
/* 4296 */     var5.addVertexWithUV((var19 + var21 + var22), (par3 + var7), (var20 - var21 + var22), var12, var10);
/* 4297 */     var5.addVertexWithUV((var19 - var21 + var22), (par3 + var7), (var20 - var21 - var22), var12, var14);
/* 4298 */     var5.addVertexWithUV((var19 - var21 - var22), (par3 + var7), (var20 + var21 - var22), var8, var14);
/* 4299 */     var5.setColorOpaque_I((par1Block.getBlockColor() & 0xFEFEFE) >> 1);
/* 4300 */     var5.addVertexWithUV((var19 - var21 - var22), (par3 + var7), (var20 + var21 - var22), var8, var14);
/* 4301 */     var5.addVertexWithUV((var19 - var21 + var22), (par3 + var7), (var20 - var21 - var22), var12, var14);
/* 4302 */     var5.addVertexWithUV((var19 + var21 + var22), (par3 + var7), (var20 - var21 + var22), var12, var10);
/* 4303 */     var5.addVertexWithUV((var19 + var21 - var22), (par3 + var7), (var20 + var21 + var22), var8, var10);
/* 4304 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderBlockStemBig(BlockStem par1BlockStem, int par2, int par3, double par4, double par6, double par8, double par10) {
/* 4312 */     Tessellator var12 = Tessellator.instance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4320 */     Icon var13 = (this.overrideBlockTexture == null) ? par1BlockStem.getStemIcon() : this.overrideBlockTexture;
/*      */     
/* 4322 */     double var14 = var13.getMinU();
/* 4323 */     double var16 = var13.getMinV();
/* 4324 */     double var18 = var13.getMaxU();
/* 4325 */     double var20 = var13.getMaxV();
/* 4326 */     double var22 = par6 + 0.5D - 0.5D;
/* 4327 */     double var24 = par6 + 0.5D + 0.5D;
/* 4328 */     double var26 = par10 + 0.5D - 0.5D;
/* 4329 */     double var28 = par10 + 0.5D + 0.5D;
/* 4330 */     double var30 = par6 + 0.5D;
/* 4331 */     double var32 = par10 + 0.5D;
/*      */     
/* 4333 */     if ((par3 + 1) / 2 % 2 == 1) {
/*      */       
/* 4335 */       double var34 = var18;
/* 4336 */       var18 = var14;
/* 4337 */       var14 = var34;
/*      */     } 
/*      */     
/* 4340 */     if (par3 < 2) {
/*      */       
/* 4342 */       var12.addVertexWithUV(var22, par8 + par4, var32, var14, var16);
/* 4343 */       var12.addVertexWithUV(var22, par8 + 0.0D, var32, var14, var20);
/* 4344 */       var12.addVertexWithUV(var24, par8 + 0.0D, var32, var18, var20);
/* 4345 */       var12.addVertexWithUV(var24, par8 + par4, var32, var18, var16);
/* 4346 */       var12.addVertexWithUV(var24, par8 + par4, var32, var18, var16);
/* 4347 */       var12.addVertexWithUV(var24, par8 + 0.0D, var32, var18, var20);
/* 4348 */       var12.addVertexWithUV(var22, par8 + 0.0D, var32, var14, var20);
/* 4349 */       var12.addVertexWithUV(var22, par8 + par4, var32, var14, var16);
/*      */     }
/*      */     else {
/*      */       
/* 4353 */       var12.addVertexWithUV(var30, par8 + par4, var28, var14, var16);
/* 4354 */       var12.addVertexWithUV(var30, par8 + 0.0D, var28, var14, var20);
/* 4355 */       var12.addVertexWithUV(var30, par8 + 0.0D, var26, var18, var20);
/* 4356 */       var12.addVertexWithUV(var30, par8 + par4, var26, var18, var16);
/* 4357 */       var12.addVertexWithUV(var30, par8 + par4, var26, var18, var16);
/* 4358 */       var12.addVertexWithUV(var30, par8 + 0.0D, var26, var18, var20);
/* 4359 */       var12.addVertexWithUV(var30, par8 + 0.0D, var28, var14, var20);
/* 4360 */       var12.addVertexWithUV(var30, par8 + par4, var28, var14, var16);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderBlockCropsImpl(Block par1Block, int par2, double par3, double par5, double par7) {
/* 4369 */     Tessellator var9 = Tessellator.instance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4377 */     Icon var10 = (this.overrideBlockTexture == null) ? getBlockIconFromSideAndMetadata(par1Block, 0, par2) : this.overrideBlockTexture;
/*      */     
/* 4379 */     double var11 = var10.getMinU();
/* 4380 */     double var13 = var10.getMinV();
/* 4381 */     double var15 = var10.getMaxU();
/* 4382 */     double var17 = var10.getMaxV();
/* 4383 */     double var19 = par3 + 0.5D - 0.25D;
/* 4384 */     double var21 = par3 + 0.5D + 0.25D;
/* 4385 */     double var23 = par7 + 0.5D - 0.5D;
/* 4386 */     double var25 = par7 + 0.5D + 0.5D;
/* 4387 */     var9.addVertexWithUV(var19, par5 + 1.0D, var23, var11, var13);
/* 4388 */     var9.addVertexWithUV(var19, par5 + 0.0D, var23, var11, var17);
/* 4389 */     var9.addVertexWithUV(var19, par5 + 0.0D, var25, var15, var17);
/* 4390 */     var9.addVertexWithUV(var19, par5 + 1.0D, var25, var15, var13);
/* 4391 */     var9.addVertexWithUV(var19, par5 + 1.0D, var25, var11, var13);
/* 4392 */     var9.addVertexWithUV(var19, par5 + 0.0D, var25, var11, var17);
/* 4393 */     var9.addVertexWithUV(var19, par5 + 0.0D, var23, var15, var17);
/* 4394 */     var9.addVertexWithUV(var19, par5 + 1.0D, var23, var15, var13);
/* 4395 */     var9.addVertexWithUV(var21, par5 + 1.0D, var25, var11, var13);
/* 4396 */     var9.addVertexWithUV(var21, par5 + 0.0D, var25, var11, var17);
/* 4397 */     var9.addVertexWithUV(var21, par5 + 0.0D, var23, var15, var17);
/* 4398 */     var9.addVertexWithUV(var21, par5 + 1.0D, var23, var15, var13);
/* 4399 */     var9.addVertexWithUV(var21, par5 + 1.0D, var23, var11, var13);
/* 4400 */     var9.addVertexWithUV(var21, par5 + 0.0D, var23, var11, var17);
/* 4401 */     var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var17);
/* 4402 */     var9.addVertexWithUV(var21, par5 + 1.0D, var25, var15, var13);
/* 4403 */     var19 = par3 + 0.5D - 0.5D;
/* 4404 */     var21 = par3 + 0.5D + 0.5D;
/* 4405 */     var23 = par7 + 0.5D - 0.25D;
/* 4406 */     var25 = par7 + 0.5D + 0.25D;
/* 4407 */     var9.addVertexWithUV(var19, par5 + 1.0D, var23, var11, var13);
/* 4408 */     var9.addVertexWithUV(var19, par5 + 0.0D, var23, var11, var17);
/* 4409 */     var9.addVertexWithUV(var21, par5 + 0.0D, var23, var15, var17);
/* 4410 */     var9.addVertexWithUV(var21, par5 + 1.0D, var23, var15, var13);
/* 4411 */     var9.addVertexWithUV(var21, par5 + 1.0D, var23, var11, var13);
/* 4412 */     var9.addVertexWithUV(var21, par5 + 0.0D, var23, var11, var17);
/* 4413 */     var9.addVertexWithUV(var19, par5 + 0.0D, var23, var15, var17);
/* 4414 */     var9.addVertexWithUV(var19, par5 + 1.0D, var23, var15, var13);
/* 4415 */     var9.addVertexWithUV(var21, par5 + 1.0D, var25, var11, var13);
/* 4416 */     var9.addVertexWithUV(var21, par5 + 0.0D, var25, var11, var17);
/* 4417 */     var9.addVertexWithUV(var19, par5 + 0.0D, var25, var15, var17);
/* 4418 */     var9.addVertexWithUV(var19, par5 + 1.0D, var25, var15, var13);
/* 4419 */     var9.addVertexWithUV(var19, par5 + 1.0D, var25, var11, var13);
/* 4420 */     var9.addVertexWithUV(var19, par5 + 0.0D, var25, var11, var17);
/* 4421 */     var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var17);
/* 4422 */     var9.addVertexWithUV(var21, par5 + 1.0D, var25, var15, var13);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockFluids(Block par1Block, int par2, int par3, int par4) {
/* 4431 */     Tessellator var5 = Tessellator.instance;
/* 4432 */     int var6 = par1Block.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 4433 */     float var7 = (var6 >> 16 & 0xFF) / 255.0F;
/* 4434 */     float var8 = (var6 >> 8 & 0xFF) / 255.0F;
/* 4435 */     float var9 = (var6 & 0xFF) / 255.0F;
/* 4436 */     boolean var10 = par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 + 1, par4, 1);
/* 4437 */     boolean var11 = par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 - 1, par4, 0);
/* 4438 */     boolean[] var12 = { par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 - 1, 2), par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 + 1, 3), par1Block.shouldSideBeRendered(this.blockAccess, par2 - 1, par3, par4, 4), par1Block.shouldSideBeRendered(this.blockAccess, par2 + 1, par3, par4, 5) };
/*      */     
/* 4440 */     if (!var10 && !var11 && !var12[0] && !var12[1] && !var12[2] && !var12[3])
/*      */     {
/* 4442 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 4446 */     boolean var13 = false;
/* 4447 */     float var14 = 0.5F;
/* 4448 */     float var15 = 1.0F;
/* 4449 */     float var16 = 0.8F;
/* 4450 */     float var17 = 0.6F;
/* 4451 */     double var18 = 0.0D;
/* 4452 */     double var20 = 1.0D;
/* 4453 */     Material var22 = par1Block.blockMaterial;
/* 4454 */     int var23 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 4455 */     double var24 = getFluidHeight(par2, par3, par4, var22);
/* 4456 */     double var26 = getFluidHeight(par2, par3, par4 + 1, var22);
/* 4457 */     double var28 = getFluidHeight(par2 + 1, par3, par4 + 1, var22);
/* 4458 */     double var30 = getFluidHeight(par2 + 1, par3, par4, var22);
/* 4459 */     double var32 = 0.0010000000474974513D;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4464 */     if (this.renderAllFaces || var10) {
/*      */       double var38, var36, var42, var40, var46, var44, var50, var48;
/* 4466 */       var13 = true;
/* 4467 */       Icon var34 = getBlockIconFromSideAndMetadata(par1Block, 1, var23);
/* 4468 */       float var35 = (float)BlockFluid.getFlowDirection(this.blockAccess, par2, par3, par4, var22);
/*      */       
/* 4470 */       if (var35 > -999.0F)
/*      */       {
/* 4472 */         var34 = getBlockIconFromSideAndMetadata(par1Block, 2, var23);
/*      */       }
/*      */       
/* 4475 */       var24 -= var32;
/* 4476 */       var26 -= var32;
/* 4477 */       var28 -= var32;
/* 4478 */       var30 -= var32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4488 */       if (var35 < -999.0F) {
/*      */         
/* 4490 */         var36 = var34.getInterpolatedU(0.0D);
/* 4491 */         var44 = var34.getInterpolatedV(0.0D);
/* 4492 */         var38 = var36;
/* 4493 */         var46 = var34.getInterpolatedV(16.0D);
/* 4494 */         var40 = var34.getInterpolatedU(16.0D);
/* 4495 */         var48 = var46;
/* 4496 */         var42 = var40;
/* 4497 */         var50 = var44;
/*      */       }
/*      */       else {
/*      */         
/* 4501 */         float f1 = MathHelper.sin(var35) * 0.25F;
/* 4502 */         float var53 = MathHelper.cos(var35) * 0.25F;
/* 4503 */         float var54 = 8.0F;
/* 4504 */         var36 = var34.getInterpolatedU((8.0F + (-var53 - f1) * 16.0F));
/* 4505 */         var44 = var34.getInterpolatedV((8.0F + (-var53 + f1) * 16.0F));
/* 4506 */         var38 = var34.getInterpolatedU((8.0F + (-var53 + f1) * 16.0F));
/* 4507 */         var46 = var34.getInterpolatedV((8.0F + (var53 + f1) * 16.0F));
/* 4508 */         var40 = var34.getInterpolatedU((8.0F + (var53 + f1) * 16.0F));
/* 4509 */         var48 = var34.getInterpolatedV((8.0F + (var53 - f1) * 16.0F));
/* 4510 */         var42 = var34.getInterpolatedU((8.0F + (var53 - f1) * 16.0F));
/* 4511 */         var50 = var34.getInterpolatedV((8.0F + (-var53 - f1) * 16.0F));
/*      */       } 
/*      */       
/* 4514 */       var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 4515 */       float var52 = 1.0F;
/* 4516 */       var5.setColorOpaque_F(var15 * var52 * var7, var15 * var52 * var8, var15 * var52 * var9);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4524 */       if (RenderingScheme.current == 0) {
/*      */         
/* 4526 */         var5.addVertexWithUV((par2 + 0), par3 + var24, (par4 + 0), var36, var44);
/* 4527 */         var5.addVertexWithUV((par2 + 0), par3 + var26, (par4 + 1), var38, var46);
/* 4528 */         var5.addVertexWithUV((par2 + 1), par3 + var28, (par4 + 1), var40, var48);
/* 4529 */         var5.addVertexWithUV((par2 + 1), par3 + var30, (par4 + 0), var42, var50);
/*      */       }
/*      */       else {
/*      */         
/* 4533 */         this.x[0] = par2;
/* 4534 */         this.y[0] = par3 + var24;
/* 4535 */         this.z[0] = par4;
/* 4536 */         this.u[0] = var36;
/* 4537 */         this.v[0] = var44;
/*      */         
/* 4539 */         this.x[1] = par2;
/* 4540 */         this.y[1] = par3 + var26;
/* 4541 */         this.z[1] = (par4 + 1);
/* 4542 */         this.u[1] = var38;
/* 4543 */         this.v[1] = var46;
/*      */         
/* 4545 */         this.x[2] = (par2 + 1);
/* 4546 */         this.y[2] = par3 + var28;
/* 4547 */         this.z[2] = (par4 + 1);
/* 4548 */         this.u[2] = var40;
/* 4549 */         this.v[2] = var48;
/*      */         
/* 4551 */         this.x[3] = (par2 + 1);
/* 4552 */         this.y[3] = par3 + var30;
/* 4553 */         this.z[3] = par4;
/* 4554 */         this.u[3] = var42;
/* 4555 */         this.v[3] = var50;
/*      */         
/* 4557 */         var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4563 */     if (this.renderAllFaces || var11) {
/*      */       
/* 4565 */       var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4));
/* 4566 */       float var58 = 1.0F;
/* 4567 */       var5.setColorOpaque_F(var14 * var58, var14 * var58, var14 * var58);
/* 4568 */       renderFaceYNeg(par1Block, par2, par3 + var32, par4, getBlockIconFromSide(par1Block, 0));
/* 4569 */       var13 = true;
/*      */     } 
/*      */     
/* 4572 */     for (int var57 = 0; var57 < 4; var57++) {
/*      */       
/* 4574 */       int var59 = par2;
/* 4575 */       int var37 = par4;
/*      */       
/* 4577 */       if (var57 == 0)
/*      */       {
/* 4579 */         var37 = par4 - 1;
/*      */       }
/*      */       
/* 4582 */       if (var57 == 1)
/*      */       {
/* 4584 */         var37++;
/*      */       }
/*      */       
/* 4587 */       if (var57 == 2)
/*      */       {
/* 4589 */         var59 = par2 - 1;
/*      */       }
/*      */       
/* 4592 */       if (var57 == 3)
/*      */       {
/* 4594 */         var59++;
/*      */       }
/*      */       
/* 4597 */       Icon var60 = getBlockIconFromSideAndMetadata(par1Block, var57 + 2, var23);
/*      */       
/* 4599 */       if (this.renderAllFaces || var12[var57]) {
/*      */         double var39, var43, var41, var47, var45, var49;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4608 */         if (var57 == 0) {
/*      */           
/* 4610 */           var39 = var24;
/* 4611 */           var41 = var30;
/* 4612 */           var43 = par2;
/* 4613 */           var47 = (par2 + 1);
/* 4614 */           var45 = par4 + var32;
/* 4615 */           var49 = par4 + var32;
/*      */         }
/* 4617 */         else if (var57 == 1) {
/*      */           
/* 4619 */           var39 = var28;
/* 4620 */           var41 = var26;
/* 4621 */           var43 = (par2 + 1);
/* 4622 */           var47 = par2;
/* 4623 */           var45 = (par4 + 1) - var32;
/* 4624 */           var49 = (par4 + 1) - var32;
/*      */         }
/* 4626 */         else if (var57 == 2) {
/*      */           
/* 4628 */           var39 = var26;
/* 4629 */           var41 = var24;
/* 4630 */           var43 = par2 + var32;
/* 4631 */           var47 = par2 + var32;
/* 4632 */           var45 = (par4 + 1);
/* 4633 */           var49 = par4;
/*      */         }
/*      */         else {
/*      */           
/* 4637 */           var39 = var30;
/* 4638 */           var41 = var28;
/* 4639 */           var43 = (par2 + 1) - var32;
/* 4640 */           var47 = (par2 + 1) - var32;
/* 4641 */           var45 = par4;
/* 4642 */           var49 = (par4 + 1);
/*      */         } 
/*      */         
/* 4645 */         var13 = true;
/* 4646 */         float var51 = var60.getInterpolatedU(0.0D);
/* 4647 */         float var52 = var60.getInterpolatedU(8.0D);
/* 4648 */         float var53 = var60.getInterpolatedV((1.0D - var39) * 16.0D * 0.5D);
/* 4649 */         float var54 = var60.getInterpolatedV((1.0D - var41) * 16.0D * 0.5D);
/* 4650 */         float var55 = var60.getInterpolatedV(8.0D);
/* 4651 */         var5.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, var59, par3, var37));
/* 4652 */         float var56 = 1.0F;
/*      */         
/* 4654 */         if (var57 < 2) {
/*      */           
/* 4656 */           var56 *= var16;
/*      */         }
/*      */         else {
/*      */           
/* 4660 */           var56 *= var17;
/*      */         } 
/*      */         
/* 4663 */         var5.setColorOpaque_F(var15 * var56 * var7, var15 * var56 * var8, var15 * var56 * var9);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4671 */         if (RenderingScheme.current == 0) {
/*      */           
/* 4673 */           var5.addVertexWithUV(var43, par3 + var39, var45, var51, var53);
/* 4674 */           var5.addVertexWithUV(var47, par3 + var41, var49, var52, var54);
/* 4675 */           var5.addVertexWithUV(var47, (par3 + 0), var49, var52, var55);
/* 4676 */           var5.addVertexWithUV(var43, (par3 + 0), var45, var51, var55);
/*      */         }
/*      */         else {
/*      */           
/* 4680 */           this.x[0] = var43;
/* 4681 */           this.y[0] = par3 + var39;
/* 4682 */           this.z[0] = var45;
/* 4683 */           this.u[0] = var51;
/* 4684 */           this.v[0] = var53;
/*      */           
/* 4686 */           this.x[1] = var47;
/* 4687 */           this.y[1] = par3 + var41;
/* 4688 */           this.z[1] = var49;
/* 4689 */           this.u[1] = var52;
/* 4690 */           this.v[1] = var54;
/*      */           
/* 4692 */           this.x[2] = var47;
/* 4693 */           this.y[2] = par3;
/* 4694 */           this.z[2] = var49;
/* 4695 */           this.u[2] = var52;
/* 4696 */           this.v[2] = var55;
/*      */           
/* 4698 */           this.x[3] = var43;
/* 4699 */           this.y[3] = par3;
/* 4700 */           this.z[3] = var45;
/* 4701 */           this.u[3] = var51;
/* 4702 */           this.v[3] = var55;
/*      */           
/* 4704 */           var5.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4711 */     this.renderMinY = var18;
/* 4712 */     this.renderMaxY = var20;
/* 4713 */     return var13;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float getFluidHeight(int par1, int par2, int par3, Material par4Material) {
/* 4722 */     int var5 = 0;
/* 4723 */     float var6 = 0.0F;
/*      */     
/* 4725 */     for (int var7 = 0; var7 < 4; var7++) {
/*      */       
/* 4727 */       int var8 = par1 - (var7 & 0x1);
/* 4728 */       int var10 = par3 - (var7 >> 1 & 0x1);
/*      */       
/* 4730 */       if (this.blockAccess.getBlockMaterial(var8, par2 + 1, var10) == par4Material)
/*      */       {
/* 4732 */         return 1.0F;
/*      */       }
/*      */       
/* 4735 */       Material var11 = this.blockAccess.getBlockMaterial(var8, par2, var10);
/*      */       
/* 4737 */       if (var11 == par4Material) {
/*      */         
/* 4739 */         int var12 = this.blockAccess.getBlockMetadata(var8, par2, var10);
/*      */         
/* 4741 */         if (var12 >= 8 || var12 == 0) {
/*      */           
/* 4743 */           var6 += BlockFluid.getFluidHeightPercent(var12) * 10.0F;
/* 4744 */           var5 += 10;
/*      */         } 
/*      */         
/* 4747 */         var6 += BlockFluid.getFluidHeightPercent(var12);
/* 4748 */         var5++;
/*      */       }
/* 4750 */       else if (!var11.isSolid()) {
/*      */         
/* 4752 */         var6++;
/* 4753 */         var5++;
/*      */       } 
/*      */     } 
/*      */     
/* 4757 */     return 1.0F - var6 / var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderBlockSandFalling(Block par1Block, World par2World, int par3, int par4, int par5, int par6) {
/* 4765 */     float var7 = 0.5F;
/* 4766 */     float var8 = 1.0F;
/* 4767 */     float var9 = 0.8F;
/* 4768 */     float var10 = 0.6F;
/* 4769 */     Tessellator var11 = Tessellator.instance;
/* 4770 */     var11.startDrawingQuads();
/* 4771 */     var11.setBrightness(par1Block.getMixedBrightnessForBlock(par2World, par3, par4, par5));
/* 4772 */     float var12 = 1.0F;
/* 4773 */     float var13 = 1.0F;
/*      */     
/* 4775 */     if (var13 < var12)
/*      */     {
/* 4777 */       var13 = var12;
/*      */     }
/*      */     
/* 4780 */     var11.setColorOpaque_F(var7 * var13, var7 * var13, var7 * var13);
/* 4781 */     renderFaceYNeg(par1Block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(par1Block, 0, par6));
/* 4782 */     var13 = 1.0F;
/*      */     
/* 4784 */     if (var13 < var12)
/*      */     {
/* 4786 */       var13 = var12;
/*      */     }
/*      */     
/* 4789 */     var11.setColorOpaque_F(var8 * var13, var8 * var13, var8 * var13);
/* 4790 */     renderFaceYPos(par1Block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(par1Block, 1, par6));
/* 4791 */     var13 = 1.0F;
/*      */     
/* 4793 */     if (var13 < var12)
/*      */     {
/* 4795 */       var13 = var12;
/*      */     }
/*      */     
/* 4798 */     var11.setColorOpaque_F(var9 * var13, var9 * var13, var9 * var13);
/* 4799 */     renderFaceZNeg(par1Block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(par1Block, 2, par6));
/* 4800 */     var13 = 1.0F;
/*      */     
/* 4802 */     if (var13 < var12)
/*      */     {
/* 4804 */       var13 = var12;
/*      */     }
/*      */     
/* 4807 */     var11.setColorOpaque_F(var9 * var13, var9 * var13, var9 * var13);
/* 4808 */     renderFaceZPos(par1Block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(par1Block, 3, par6));
/* 4809 */     var13 = 1.0F;
/*      */     
/* 4811 */     if (var13 < var12)
/*      */     {
/* 4813 */       var13 = var12;
/*      */     }
/*      */     
/* 4816 */     var11.setColorOpaque_F(var10 * var13, var10 * var13, var10 * var13);
/* 4817 */     renderFaceXNeg(par1Block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(par1Block, 4, par6));
/* 4818 */     var13 = 1.0F;
/*      */     
/* 4820 */     if (var13 < var12)
/*      */     {
/* 4822 */       var13 = var12;
/*      */     }
/*      */     
/* 4825 */     var11.setColorOpaque_F(var10 * var13, var10 * var13, var10 * var13);
/* 4826 */     renderFaceXPos(par1Block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(par1Block, 5, par6));
/* 4827 */     var11.draw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderStandardBlock(Block par1Block, int par2, int par3, int par4) {
/* 4835 */     int var5 = par1Block.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 4836 */     float var6 = (var5 >> 16 & 0xFF) / 255.0F;
/* 4837 */     float var7 = (var5 >> 8 & 0xFF) / 255.0F;
/* 4838 */     float var8 = (var5 & 0xFF) / 255.0F;
/*      */     
/* 4840 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/* 4842 */       float var9 = (var6 * 30.0F + var7 * 59.0F + var8 * 11.0F) / 100.0F;
/* 4843 */       float var10 = (var6 * 30.0F + var7 * 70.0F) / 100.0F;
/* 4844 */       float var11 = (var6 * 30.0F + var8 * 70.0F) / 100.0F;
/* 4845 */       var6 = var9;
/* 4846 */       var7 = var10;
/* 4847 */       var8 = var11;
/*      */     } 
/*      */     
/* 4850 */     return (Minecraft.isAmbientOcclusionEnabled() && Block.lightValue[par1Block.blockID] == 0) ? (this.partialRenderBounds ? renderStandardBlockWithAmbientOcclusionPartial(par1Block, par2, par3, par4, var6, var7, var8) : renderStandardBlockWithAmbientOcclusion(par1Block, par2, par3, par4, var6, var7, var8)) : renderStandardBlockWithColorMultiplier(par1Block, par2, par3, par4, var6, var7, var8);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockLog(Block par1Block, int par2, int par3, int par4) {
/* 4858 */     int var5 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 4859 */     int var6 = var5 & 0xC;
/*      */     
/* 4861 */     if (var6 == 4) {
/*      */       
/* 4863 */       this.uvRotateEast = 1;
/* 4864 */       this.uvRotateWest = 1;
/* 4865 */       this.uvRotateTop = 1;
/* 4866 */       this.uvRotateBottom = 1;
/*      */     }
/* 4868 */     else if (var6 == 8) {
/*      */       
/* 4870 */       this.uvRotateSouth = 1;
/* 4871 */       this.uvRotateNorth = 1;
/*      */     } 
/*      */     
/* 4874 */     boolean var7 = renderStandardBlock(par1Block, par2, par3, par4);
/* 4875 */     this.uvRotateSouth = 0;
/* 4876 */     this.uvRotateEast = 0;
/* 4877 */     this.uvRotateWest = 0;
/* 4878 */     this.uvRotateNorth = 0;
/* 4879 */     this.uvRotateTop = 0;
/* 4880 */     this.uvRotateBottom = 0;
/* 4881 */     return var7;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean renderBlockQuartz(Block par1Block, int par2, int par3, int par4) {
/* 4886 */     int var5 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*      */     
/* 4888 */     if (var5 == 3) {
/*      */       
/* 4890 */       this.uvRotateEast = 1;
/* 4891 */       this.uvRotateWest = 1;
/* 4892 */       this.uvRotateTop = 1;
/* 4893 */       this.uvRotateBottom = 1;
/*      */     }
/* 4895 */     else if (var5 == 4) {
/*      */       
/* 4897 */       this.uvRotateSouth = 1;
/* 4898 */       this.uvRotateNorth = 1;
/*      */     } 
/*      */     
/* 4901 */     boolean var6 = renderStandardBlock(par1Block, par2, par3, par4);
/* 4902 */     this.uvRotateSouth = 0;
/* 4903 */     this.uvRotateEast = 0;
/* 4904 */     this.uvRotateWest = 0;
/* 4905 */     this.uvRotateNorth = 0;
/* 4906 */     this.uvRotateTop = 0;
/* 4907 */     this.uvRotateBottom = 0;
/* 4908 */     return var6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderStandardBlockWithAmbientOcclusion(Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7) {
/* 4918 */     int x = par2;
/* 4919 */     int y = par3;
/* 4920 */     int z = par4;
/*      */     
/* 4922 */     this.enableAO = true;
/* 4923 */     boolean var8 = false;
/* 4924 */     float var9 = 0.0F;
/* 4925 */     float var10 = 0.0F;
/* 4926 */     float var11 = 0.0F;
/* 4927 */     float var12 = 0.0F;
/*      */     
/* 4929 */     int var14 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4);
/*      */ 
/*      */     
/* 4932 */     Tessellator var15 = Tessellator.instance;
/* 4933 */     var15.setBrightness(983055);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4944 */     boolean var13 = (!par1Block.has_grass_top_icon && this.overrideBlockTexture == null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4953 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 - 1, par4, 0)) {
/*      */       
/* 4955 */       if (this.renderMinY <= 0.0D)
/*      */       {
/* 4957 */         par3--;
/*      */       }
/*      */       
/* 4960 */       this.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/* 4961 */       this.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/* 4962 */       this.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/* 4963 */       this.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/* 4964 */       this.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 4965 */       this.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 4966 */       this.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/* 4967 */       this.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4972 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 + 1, y - 1, par4)];
/* 4973 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 - 1, y - 1, par4)];
/* 4974 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, y - 1, par4 + 1)];
/* 4975 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, y - 1, par4 - 1)];
/*      */       
/* 4977 */       if (!var19 && !var17) {
/*      */         
/* 4979 */         this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXYNN;
/* 4980 */         this.aoBrightnessXYZNNN = this.aoBrightnessXYNN;
/*      */       }
/*      */       else {
/*      */         
/* 4984 */         this.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4 - 1);
/* 4985 */         this.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4 - 1);
/*      */       } 
/*      */       
/* 4988 */       if (!var18 && !var17) {
/*      */         
/* 4990 */         this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXYNN;
/* 4991 */         this.aoBrightnessXYZNNP = this.aoBrightnessXYNN;
/*      */       }
/*      */       else {
/*      */         
/* 4995 */         this.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4 + 1);
/* 4996 */         this.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4 + 1);
/*      */       } 
/*      */       
/* 4999 */       if (!var19 && !var16) {
/*      */         
/* 5001 */         this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXYPN;
/* 5002 */         this.aoBrightnessXYZPNN = this.aoBrightnessXYPN;
/*      */       }
/*      */       else {
/*      */         
/* 5006 */         this.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4 - 1);
/* 5007 */         this.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4 - 1);
/*      */       } 
/*      */       
/* 5010 */       if (!var18 && !var16) {
/*      */         
/* 5012 */         this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXYPN;
/* 5013 */         this.aoBrightnessXYZPNP = this.aoBrightnessXYPN;
/*      */       }
/*      */       else {
/*      */         
/* 5017 */         this.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4 + 1);
/* 5018 */         this.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4 + 1);
/*      */       } 
/*      */       
/* 5021 */       if (this.renderMinY <= 0.0D)
/*      */       {
/* 5023 */         par3++;
/*      */       }
/*      */       
/* 5026 */       int var20 = var14;
/*      */       
/* 5028 */       if (this.renderMinY <= 0.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2, par3 - 1, par4))
/*      */       {
/* 5030 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/*      */       }
/*      */       
/* 5033 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 5034 */       var9 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXYNN + this.aoLightValueScratchYZNP + var21) / 4.0F;
/* 5035 */       var12 = (this.aoLightValueScratchYZNP + var21 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXYPN) / 4.0F;
/* 5036 */       var11 = (var21 + this.aoLightValueScratchYZNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNN) / 4.0F;
/* 5037 */       var10 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNN + var21 + this.aoLightValueScratchYZNN) / 4.0F;
/* 5038 */       this.brightnessTopLeft = getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXYNN, this.aoBrightnessYZNP, var20);
/* 5039 */       this.brightnessTopRight = getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXYPN, var20);
/* 5040 */       this.brightnessBottomRight = getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYPN, this.aoBrightnessXYZPNN, var20);
/* 5041 */       this.brightnessBottomLeft = getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNN, this.aoBrightnessYZNN, var20);
/*      */       
/* 5043 */       if (var13) {
/*      */         
/* 5045 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.5F;
/* 5046 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.5F;
/* 5047 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.5F;
/*      */       }
/*      */       else {
/*      */         
/* 5051 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.5F;
/* 5052 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.5F;
/* 5053 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.5F;
/*      */       } 
/*      */       
/* 5056 */       this.colorRedTopLeft *= var9;
/* 5057 */       this.colorGreenTopLeft *= var9;
/* 5058 */       this.colorBlueTopLeft *= var9;
/* 5059 */       this.colorRedBottomLeft *= var10;
/* 5060 */       this.colorGreenBottomLeft *= var10;
/* 5061 */       this.colorBlueBottomLeft *= var10;
/* 5062 */       this.colorRedBottomRight *= var11;
/* 5063 */       this.colorGreenBottomRight *= var11;
/* 5064 */       this.colorBlueBottomRight *= var11;
/* 5065 */       this.colorRedTopRight *= var12;
/* 5066 */       this.colorGreenTopRight *= var12;
/* 5067 */       this.colorBlueTopRight *= var12;
/* 5068 */       renderFaceYNeg(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 0));
/* 5069 */       var8 = true;
/*      */     } 
/*      */     
/* 5072 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 + 1, par4, 1)) {
/*      */       
/* 5074 */       if (this.renderMaxY >= 1.0D)
/*      */       {
/* 5076 */         par3++;
/*      */       }
/*      */       
/* 5079 */       this.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/* 5080 */       this.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/* 5081 */       this.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/* 5082 */       this.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/* 5083 */       this.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 5084 */       this.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/* 5085 */       this.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 5086 */       this.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5091 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 + 1, y + 1, par4)];
/* 5092 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 - 1, y + 1, par4)];
/* 5093 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, y + 1, par4 + 1)];
/* 5094 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, y + 1, par4 - 1)];
/*      */       
/* 5096 */       if (!var19 && !var17) {
/*      */         
/* 5098 */         this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXYNP;
/* 5099 */         this.aoBrightnessXYZNPN = this.aoBrightnessXYNP;
/*      */       }
/*      */       else {
/*      */         
/* 5103 */         this.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4 - 1);
/* 5104 */         this.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4 - 1);
/*      */       } 
/*      */       
/* 5107 */       if (!var19 && !var16) {
/*      */         
/* 5109 */         this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXYPP;
/* 5110 */         this.aoBrightnessXYZPPN = this.aoBrightnessXYPP;
/*      */       }
/*      */       else {
/*      */         
/* 5114 */         this.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4 - 1);
/* 5115 */         this.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4 - 1);
/*      */       } 
/*      */       
/* 5118 */       if (!var18 && !var17) {
/*      */         
/* 5120 */         this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXYNP;
/* 5121 */         this.aoBrightnessXYZNPP = this.aoBrightnessXYNP;
/*      */       }
/*      */       else {
/*      */         
/* 5125 */         this.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4 + 1);
/* 5126 */         this.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4 + 1);
/*      */       } 
/*      */       
/* 5129 */       if (!var18 && !var16) {
/*      */         
/* 5131 */         this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXYPP;
/* 5132 */         this.aoBrightnessXYZPPP = this.aoBrightnessXYPP;
/*      */       }
/*      */       else {
/*      */         
/* 5136 */         this.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4 + 1);
/* 5137 */         this.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4 + 1);
/*      */       } 
/*      */       
/* 5140 */       if (this.renderMaxY >= 1.0D)
/*      */       {
/* 5142 */         par3--;
/*      */       }
/*      */       
/* 5145 */       int var20 = var14;
/*      */       
/* 5147 */       if (this.renderMaxY >= 1.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2, par3 + 1, par4))
/*      */       {
/* 5149 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/*      */       }
/*      */       
/* 5152 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 5153 */       var12 = (this.aoLightValueScratchXYZNPP + this.aoLightValueScratchXYNP + this.aoLightValueScratchYZPP + var21) / 4.0F;
/* 5154 */       var9 = (this.aoLightValueScratchYZPP + var21 + this.aoLightValueScratchXYZPPP + this.aoLightValueScratchXYPP) / 4.0F;
/* 5155 */       var10 = (var21 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPN) / 4.0F;
/* 5156 */       var11 = (this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPN + var21 + this.aoLightValueScratchYZPN) / 4.0F;
/* 5157 */       this.brightnessTopRight = getAoBrightness(this.aoBrightnessXYZNPP, this.aoBrightnessXYNP, this.aoBrightnessYZPP, var20);
/* 5158 */       this.brightnessTopLeft = getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXYZPPP, this.aoBrightnessXYPP, var20);
/* 5159 */       this.brightnessBottomLeft = getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXYPP, this.aoBrightnessXYZPPN, var20);
/* 5160 */       this.brightnessBottomRight = getAoBrightness(this.aoBrightnessXYNP, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, var20);
/* 5161 */       this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5;
/* 5162 */       this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6;
/* 5163 */       this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7;
/* 5164 */       this.colorRedTopLeft *= var9;
/* 5165 */       this.colorGreenTopLeft *= var9;
/* 5166 */       this.colorBlueTopLeft *= var9;
/* 5167 */       this.colorRedBottomLeft *= var10;
/* 5168 */       this.colorGreenBottomLeft *= var10;
/* 5169 */       this.colorBlueBottomLeft *= var10;
/* 5170 */       this.colorRedBottomRight *= var11;
/* 5171 */       this.colorGreenBottomRight *= var11;
/* 5172 */       this.colorBlueBottomRight *= var11;
/* 5173 */       this.colorRedTopRight *= var12;
/* 5174 */       this.colorGreenTopRight *= var12;
/* 5175 */       this.colorBlueTopRight *= var12;
/* 5176 */       renderFaceYPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 1));
/* 5177 */       var8 = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5182 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 - 1, 2)) {
/*      */       
/* 5184 */       if (this.renderMinZ <= 0.0D)
/*      */       {
/* 5186 */         par4--;
/*      */       }
/*      */       
/* 5189 */       this.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 5190 */       this.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 5191 */       this.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 5192 */       this.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/* 5193 */       this.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/* 5194 */       this.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/* 5195 */       this.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/* 5196 */       this.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5201 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 + 1, par3, z - 1)];
/* 5202 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 - 1, par3, z - 1)];
/* 5203 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, par3 + 1, z - 1)];
/* 5204 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, par3 - 1, z - 1)];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5228 */       if (!var17 && !var19) {
/*      */         
/* 5230 */         this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
/* 5231 */         this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
/*      */       }
/*      */       else {
/*      */         
/* 5235 */         this.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3 - 1, par4);
/* 5236 */         this.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3 - 1, par4);
/*      */       } 
/*      */       
/* 5239 */       if (!var17 && !var18) {
/*      */         
/* 5241 */         this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
/* 5242 */         this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
/*      */       }
/*      */       else {
/*      */         
/* 5246 */         this.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3 + 1, par4);
/* 5247 */         this.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3 + 1, par4);
/*      */       } 
/*      */       
/* 5250 */       if (!var16 && !var19) {
/*      */         
/* 5252 */         this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
/* 5253 */         this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
/*      */       }
/*      */       else {
/*      */         
/* 5257 */         this.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3 - 1, par4);
/* 5258 */         this.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3 - 1, par4);
/*      */       } 
/*      */       
/* 5261 */       if (!var16 && !var18) {
/*      */         
/* 5263 */         this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
/* 5264 */         this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
/*      */       }
/*      */       else {
/*      */         
/* 5268 */         this.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3 + 1, par4);
/* 5269 */         this.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3 + 1, par4);
/*      */       } 
/*      */       
/* 5272 */       if (this.renderMinZ <= 0.0D)
/*      */       {
/* 5274 */         par4++;
/*      */       }
/*      */       
/* 5277 */       int var20 = var14;
/*      */       
/* 5279 */       if (this.renderMinZ <= 0.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2, par3, par4 - 1))
/*      */       {
/* 5281 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/*      */       }
/*      */       
/* 5284 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 5285 */       var9 = (this.aoLightValueScratchXZNN + this.aoLightValueScratchXYZNPN + var21 + this.aoLightValueScratchYZPN) / 4.0F;
/* 5286 */       var10 = (var21 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXZPN + this.aoLightValueScratchXYZPPN) / 4.0F;
/* 5287 */       var11 = (this.aoLightValueScratchYZNN + var21 + this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXZPN) / 4.0F;
/* 5288 */       var12 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXZNN + this.aoLightValueScratchYZNN + var21) / 4.0F;
/* 5289 */       this.brightnessTopLeft = getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, var20);
/* 5290 */       this.brightnessBottomLeft = getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, var20);
/* 5291 */       this.brightnessBottomRight = getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYZPNN, this.aoBrightnessXZPN, var20);
/* 5292 */       this.brightnessTopRight = getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXZNN, this.aoBrightnessYZNN, var20);
/*      */       
/* 5294 */       if (var13) {
/*      */         
/* 5296 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.8F;
/* 5297 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.8F;
/* 5298 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.8F;
/*      */       }
/*      */       else {
/*      */         
/* 5302 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
/* 5303 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
/* 5304 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
/*      */       } 
/*      */       
/* 5307 */       this.colorRedTopLeft *= var9;
/* 5308 */       this.colorGreenTopLeft *= var9;
/* 5309 */       this.colorBlueTopLeft *= var9;
/* 5310 */       this.colorRedBottomLeft *= var10;
/* 5311 */       this.colorGreenBottomLeft *= var10;
/* 5312 */       this.colorBlueBottomLeft *= var10;
/* 5313 */       this.colorRedBottomRight *= var11;
/* 5314 */       this.colorGreenBottomRight *= var11;
/* 5315 */       this.colorBlueBottomRight *= var11;
/* 5316 */       this.colorRedTopRight *= var12;
/* 5317 */       this.colorGreenTopRight *= var12;
/* 5318 */       this.colorBlueTopRight *= var12;
/* 5319 */       Icon var22 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 2);
/* 5320 */       renderFaceZNeg(par1Block, par2, par3, par4, var22);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5325 */       if (var22.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 5327 */         this.colorRedTopLeft *= par5;
/* 5328 */         this.colorRedBottomLeft *= par5;
/* 5329 */         this.colorRedBottomRight *= par5;
/* 5330 */         this.colorRedTopRight *= par5;
/* 5331 */         this.colorGreenTopLeft *= par6;
/* 5332 */         this.colorGreenBottomLeft *= par6;
/* 5333 */         this.colorGreenBottomRight *= par6;
/* 5334 */         this.colorGreenTopRight *= par6;
/* 5335 */         this.colorBlueTopLeft *= par7;
/* 5336 */         this.colorBlueBottomLeft *= par7;
/* 5337 */         this.colorBlueBottomRight *= par7;
/* 5338 */         this.colorBlueTopRight *= par7;
/* 5339 */         renderFaceZNeg(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 5342 */       var8 = true;
/*      */     } 
/*      */     
/* 5345 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 + 1, 3)) {
/*      */       
/* 5347 */       if (this.renderMaxZ >= 1.0D)
/*      */       {
/* 5349 */         par4++;
/*      */       }
/*      */       
/* 5352 */       this.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 5353 */       this.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/* 5354 */       this.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 5355 */       this.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 5356 */       this.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/* 5357 */       this.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/* 5358 */       this.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/* 5359 */       this.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5364 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 + 1, par3, z + 1)];
/* 5365 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 - 1, par3, z + 1)];
/* 5366 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, par3 + 1, z + 1)];
/* 5367 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, par3 - 1, z + 1)];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5391 */       if (!var17 && !var19) {
/*      */         
/* 5393 */         this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
/* 5394 */         this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
/*      */       }
/*      */       else {
/*      */         
/* 5398 */         this.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3 - 1, par4);
/* 5399 */         this.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3 - 1, par4);
/*      */       } 
/*      */       
/* 5402 */       if (!var17 && !var18) {
/*      */         
/* 5404 */         this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
/* 5405 */         this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
/*      */       }
/*      */       else {
/*      */         
/* 5409 */         this.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3 + 1, par4);
/* 5410 */         this.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3 + 1, par4);
/*      */       } 
/*      */       
/* 5413 */       if (!var16 && !var19) {
/*      */         
/* 5415 */         this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
/* 5416 */         this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
/*      */       }
/*      */       else {
/*      */         
/* 5420 */         this.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3 - 1, par4);
/* 5421 */         this.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3 - 1, par4);
/*      */       } 
/*      */       
/* 5424 */       if (!var16 && !var18) {
/*      */         
/* 5426 */         this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
/* 5427 */         this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
/*      */       }
/*      */       else {
/*      */         
/* 5431 */         this.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3 + 1, par4);
/* 5432 */         this.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3 + 1, par4);
/*      */       } 
/*      */       
/* 5435 */       if (this.renderMaxZ >= 1.0D)
/*      */       {
/* 5437 */         par4--;
/*      */       }
/*      */       
/* 5440 */       int var20 = var14;
/*      */       
/* 5442 */       if (this.renderMaxZ >= 1.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2, par3, par4 + 1))
/*      */       {
/* 5444 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/*      */       }
/*      */       
/* 5447 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/* 5448 */       var9 = (this.aoLightValueScratchXZNP + this.aoLightValueScratchXYZNPP + var21 + this.aoLightValueScratchYZPP) / 4.0F;
/* 5449 */       var12 = (var21 + this.aoLightValueScratchYZPP + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYZPPP) / 4.0F;
/* 5450 */       var11 = (this.aoLightValueScratchYZNP + var21 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXZPP) / 4.0F;
/* 5451 */       var10 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXZNP + this.aoLightValueScratchYZNP + var21) / 4.0F;
/* 5452 */       this.brightnessTopLeft = getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYZNPP, this.aoBrightnessYZPP, var20);
/* 5453 */       this.brightnessTopRight = getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXZPP, this.aoBrightnessXYZPPP, var20);
/* 5454 */       this.brightnessBottomRight = getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, var20);
/* 5455 */       this.brightnessBottomLeft = getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, this.aoBrightnessYZNP, var20);
/*      */       
/* 5457 */       if (var13) {
/*      */         
/* 5459 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.8F;
/* 5460 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.8F;
/* 5461 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.8F;
/*      */       }
/*      */       else {
/*      */         
/* 5465 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
/* 5466 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
/* 5467 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
/*      */       } 
/*      */       
/* 5470 */       this.colorRedTopLeft *= var9;
/* 5471 */       this.colorGreenTopLeft *= var9;
/* 5472 */       this.colorBlueTopLeft *= var9;
/* 5473 */       this.colorRedBottomLeft *= var10;
/* 5474 */       this.colorGreenBottomLeft *= var10;
/* 5475 */       this.colorBlueBottomLeft *= var10;
/* 5476 */       this.colorRedBottomRight *= var11;
/* 5477 */       this.colorGreenBottomRight *= var11;
/* 5478 */       this.colorBlueBottomRight *= var11;
/* 5479 */       this.colorRedTopRight *= var12;
/* 5480 */       this.colorGreenTopRight *= var12;
/* 5481 */       this.colorBlueTopRight *= var12;
/* 5482 */       Icon var22 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 3);
/* 5483 */       renderFaceZPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 3));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5488 */       if (var22.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 5490 */         this.colorRedTopLeft *= par5;
/* 5491 */         this.colorRedBottomLeft *= par5;
/* 5492 */         this.colorRedBottomRight *= par5;
/* 5493 */         this.colorRedTopRight *= par5;
/* 5494 */         this.colorGreenTopLeft *= par6;
/* 5495 */         this.colorGreenBottomLeft *= par6;
/* 5496 */         this.colorGreenBottomRight *= par6;
/* 5497 */         this.colorGreenTopRight *= par6;
/* 5498 */         this.colorBlueTopLeft *= par7;
/* 5499 */         this.colorBlueBottomLeft *= par7;
/* 5500 */         this.colorBlueBottomRight *= par7;
/* 5501 */         this.colorBlueTopRight *= par7;
/* 5502 */         renderFaceZPos(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 5505 */       var8 = true;
/*      */     } 
/*      */     
/* 5508 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2 - 1, par3, par4, 4)) {
/*      */       
/* 5510 */       if (this.renderMinX <= 0.0D)
/*      */       {
/* 5512 */         par2--;
/*      */       }
/*      */       
/* 5515 */       this.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 5516 */       this.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 5517 */       this.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/* 5518 */       this.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 5519 */       this.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/* 5520 */       this.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/* 5521 */       this.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/* 5522 */       this.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5527 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(x - 1, par3 + 1, par4)];
/* 5528 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(x - 1, par3 - 1, par4)];
/* 5529 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(x - 1, par3, par4 - 1)];
/* 5530 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(x - 1, par3, par4 + 1)];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5554 */       if (!var18 && !var17) {
/*      */         
/* 5556 */         this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
/* 5557 */         this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
/*      */       }
/*      */       else {
/*      */         
/* 5561 */         this.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4 - 1);
/* 5562 */         this.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4 - 1);
/*      */       } 
/*      */       
/* 5565 */       if (!var19 && !var17) {
/*      */         
/* 5567 */         this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
/* 5568 */         this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
/*      */       }
/*      */       else {
/*      */         
/* 5572 */         this.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4 + 1);
/* 5573 */         this.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4 + 1);
/*      */       } 
/*      */       
/* 5576 */       if (!var18 && !var16) {
/*      */         
/* 5578 */         this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
/* 5579 */         this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
/*      */       }
/*      */       else {
/*      */         
/* 5583 */         this.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4 - 1);
/* 5584 */         this.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4 - 1);
/*      */       } 
/*      */       
/* 5587 */       if (!var19 && !var16) {
/*      */         
/* 5589 */         this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
/* 5590 */         this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
/*      */       }
/*      */       else {
/*      */         
/* 5594 */         this.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4 + 1);
/* 5595 */         this.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4 + 1);
/*      */       } 
/*      */       
/* 5598 */       if (this.renderMinX <= 0.0D)
/*      */       {
/* 5600 */         par2++;
/*      */       }
/*      */       
/* 5603 */       int var20 = var14;
/*      */       
/* 5605 */       if (this.renderMinX <= 0.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2 - 1, par3, par4))
/*      */       {
/* 5607 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/*      */       }
/*      */       
/* 5610 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 5611 */       var12 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNP + var21 + this.aoLightValueScratchXZNP) / 4.0F;
/* 5612 */       var9 = (var21 + this.aoLightValueScratchXZNP + this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPP) / 4.0F;
/* 5613 */       var10 = (this.aoLightValueScratchXZNN + var21 + this.aoLightValueScratchXYZNPN + this.aoLightValueScratchXYNP) / 4.0F;
/* 5614 */       var11 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXYNN + this.aoLightValueScratchXZNN + var21) / 4.0F;
/* 5615 */       this.brightnessTopRight = getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, var20);
/* 5616 */       this.brightnessTopLeft = getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYNP, this.aoBrightnessXYZNPP, var20);
/* 5617 */       this.brightnessBottomLeft = getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessXYNP, var20);
/* 5618 */       this.brightnessBottomRight = getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXYNN, this.aoBrightnessXZNN, var20);
/*      */       
/* 5620 */       if (var13) {
/*      */         
/* 5622 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.6F;
/* 5623 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.6F;
/* 5624 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.6F;
/*      */       }
/*      */       else {
/*      */         
/* 5628 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
/* 5629 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
/* 5630 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
/*      */       } 
/*      */       
/* 5633 */       this.colorRedTopLeft *= var9;
/* 5634 */       this.colorGreenTopLeft *= var9;
/* 5635 */       this.colorBlueTopLeft *= var9;
/* 5636 */       this.colorRedBottomLeft *= var10;
/* 5637 */       this.colorGreenBottomLeft *= var10;
/* 5638 */       this.colorBlueBottomLeft *= var10;
/* 5639 */       this.colorRedBottomRight *= var11;
/* 5640 */       this.colorGreenBottomRight *= var11;
/* 5641 */       this.colorBlueBottomRight *= var11;
/* 5642 */       this.colorRedTopRight *= var12;
/* 5643 */       this.colorGreenTopRight *= var12;
/* 5644 */       this.colorBlueTopRight *= var12;
/* 5645 */       Icon var22 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 4);
/* 5646 */       renderFaceXNeg(par1Block, par2, par3, par4, var22);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5651 */       if (var22.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 5653 */         this.colorRedTopLeft *= par5;
/* 5654 */         this.colorRedBottomLeft *= par5;
/* 5655 */         this.colorRedBottomRight *= par5;
/* 5656 */         this.colorRedTopRight *= par5;
/* 5657 */         this.colorGreenTopLeft *= par6;
/* 5658 */         this.colorGreenBottomLeft *= par6;
/* 5659 */         this.colorGreenBottomRight *= par6;
/* 5660 */         this.colorGreenTopRight *= par6;
/* 5661 */         this.colorBlueTopLeft *= par7;
/* 5662 */         this.colorBlueBottomLeft *= par7;
/* 5663 */         this.colorBlueBottomRight *= par7;
/* 5664 */         this.colorBlueTopRight *= par7;
/* 5665 */         renderFaceXNeg(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 5668 */       var8 = true;
/*      */     } 
/*      */     
/* 5671 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2 + 1, par3, par4, 5)) {
/*      */       
/* 5673 */       if (this.renderMaxX >= 1.0D)
/*      */       {
/* 5675 */         par2++;
/*      */       }
/*      */       
/* 5678 */       this.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 5679 */       this.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 5680 */       this.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/* 5681 */       this.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 5682 */       this.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/* 5683 */       this.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/* 5684 */       this.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/* 5685 */       this.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5690 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(x + 1, par3 + 1, par4)];
/* 5691 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(x + 1, par3 - 1, par4)];
/* 5692 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(x + 1, par3, par4 + 1)];
/* 5693 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(x + 1, par3, par4 - 1)];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5717 */       if (!var17 && !var19) {
/*      */         
/* 5719 */         this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
/* 5720 */         this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
/*      */       }
/*      */       else {
/*      */         
/* 5724 */         this.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4 - 1);
/* 5725 */         this.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4 - 1);
/*      */       } 
/*      */       
/* 5728 */       if (!var17 && !var18) {
/*      */         
/* 5730 */         this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
/* 5731 */         this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
/*      */       }
/*      */       else {
/*      */         
/* 5735 */         this.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4 + 1);
/* 5736 */         this.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4 + 1);
/*      */       } 
/*      */       
/* 5739 */       if (!var16 && !var19) {
/*      */         
/* 5741 */         this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
/* 5742 */         this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
/*      */       }
/*      */       else {
/*      */         
/* 5746 */         this.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4 - 1);
/* 5747 */         this.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4 - 1);
/*      */       } 
/*      */       
/* 5750 */       if (!var16 && !var18) {
/*      */         
/* 5752 */         this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
/* 5753 */         this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
/*      */       }
/*      */       else {
/*      */         
/* 5757 */         this.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4 + 1);
/* 5758 */         this.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4 + 1);
/*      */       } 
/*      */       
/* 5761 */       if (this.renderMaxX >= 1.0D)
/*      */       {
/* 5763 */         par2--;
/*      */       }
/*      */       
/* 5766 */       int var20 = var14;
/*      */       
/* 5768 */       if (this.renderMaxX >= 1.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2 + 1, par3, par4))
/*      */       {
/* 5770 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/*      */       }
/*      */       
/* 5773 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/* 5774 */       var9 = (this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNP + var21 + this.aoLightValueScratchXZPP) / 4.0F;
/* 5775 */       var10 = (this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXZPN + var21) / 4.0F;
/* 5776 */       var11 = (this.aoLightValueScratchXZPN + var21 + this.aoLightValueScratchXYZPPN + this.aoLightValueScratchXYPP) / 4.0F;
/* 5777 */       var12 = (var21 + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPP) / 4.0F;
/* 5778 */       this.brightnessTopLeft = getAoBrightness(this.aoBrightnessXYPN, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, var20);
/* 5779 */       this.brightnessTopRight = getAoBrightness(this.aoBrightnessXZPP, this.aoBrightnessXYPP, this.aoBrightnessXYZPPP, var20);
/* 5780 */       this.brightnessBottomRight = getAoBrightness(this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, this.aoBrightnessXYPP, var20);
/* 5781 */       this.brightnessBottomLeft = getAoBrightness(this.aoBrightnessXYZPNN, this.aoBrightnessXYPN, this.aoBrightnessXZPN, var20);
/*      */       
/* 5783 */       if (var13) {
/*      */         
/* 5785 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.6F;
/* 5786 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.6F;
/* 5787 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.6F;
/*      */       }
/*      */       else {
/*      */         
/* 5791 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
/* 5792 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
/* 5793 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
/*      */       } 
/*      */       
/* 5796 */       this.colorRedTopLeft *= var9;
/* 5797 */       this.colorGreenTopLeft *= var9;
/* 5798 */       this.colorBlueTopLeft *= var9;
/* 5799 */       this.colorRedBottomLeft *= var10;
/* 5800 */       this.colorGreenBottomLeft *= var10;
/* 5801 */       this.colorBlueBottomLeft *= var10;
/* 5802 */       this.colorRedBottomRight *= var11;
/* 5803 */       this.colorGreenBottomRight *= var11;
/* 5804 */       this.colorBlueBottomRight *= var11;
/* 5805 */       this.colorRedTopRight *= var12;
/* 5806 */       this.colorGreenTopRight *= var12;
/* 5807 */       this.colorBlueTopRight *= var12;
/* 5808 */       Icon var22 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 5);
/* 5809 */       renderFaceXPos(par1Block, par2, par3, par4, var22);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5814 */       if (var22.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 5816 */         this.colorRedTopLeft *= par5;
/* 5817 */         this.colorRedBottomLeft *= par5;
/* 5818 */         this.colorRedBottomRight *= par5;
/* 5819 */         this.colorRedTopRight *= par5;
/* 5820 */         this.colorGreenTopLeft *= par6;
/* 5821 */         this.colorGreenBottomLeft *= par6;
/* 5822 */         this.colorGreenBottomRight *= par6;
/* 5823 */         this.colorGreenTopRight *= par6;
/* 5824 */         this.colorBlueTopLeft *= par7;
/* 5825 */         this.colorBlueBottomLeft *= par7;
/* 5826 */         this.colorBlueBottomRight *= par7;
/* 5827 */         this.colorBlueTopRight *= par7;
/* 5828 */         renderFaceXPos(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 5831 */       var8 = true;
/*      */     } 
/*      */     
/* 5834 */     this.enableAO = false;
/* 5835 */     return var8;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderStandardBlockWithAmbientOcclusionPartial(Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7) {
/* 5843 */     int x = par2;
/* 5844 */     int y = par3;
/* 5845 */     int z = par4;
/*      */     
/* 5847 */     this.enableAO = true;
/* 5848 */     boolean var8 = false;
/* 5849 */     float var9 = 0.0F;
/* 5850 */     float var10 = 0.0F;
/* 5851 */     float var11 = 0.0F;
/* 5852 */     float var12 = 0.0F;
/*      */     
/* 5854 */     int var14 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4);
/*      */     
/* 5856 */     Tessellator var15 = Tessellator.instance;
/* 5857 */     var15.setBrightness(983055);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5868 */     boolean var13 = (!par1Block.has_grass_top_icon && this.overrideBlockTexture == null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5877 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 - 1, par4, 0)) {
/*      */       
/* 5879 */       if (this.renderMinY <= 0.0D)
/*      */       {
/* 5881 */         par3--;
/*      */       }
/*      */       
/* 5884 */       this.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/* 5885 */       this.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/* 5886 */       this.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/* 5887 */       this.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/* 5888 */       this.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 5889 */       this.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 5890 */       this.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/* 5891 */       this.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5896 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 + 1, y - 1, par4)];
/* 5897 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 - 1, y - 1, par4)];
/* 5898 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, y - 1, par4 + 1)];
/* 5899 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, y - 1, par4 - 1)];
/*      */       
/* 5901 */       if (!var19 && !var17) {
/*      */         
/* 5903 */         this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXYNN;
/* 5904 */         this.aoBrightnessXYZNNN = this.aoBrightnessXYNN;
/*      */       }
/*      */       else {
/*      */         
/* 5908 */         this.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4 - 1);
/* 5909 */         this.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4 - 1);
/*      */       } 
/*      */       
/* 5912 */       if (!var18 && !var17) {
/*      */         
/* 5914 */         this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXYNN;
/* 5915 */         this.aoBrightnessXYZNNP = this.aoBrightnessXYNN;
/*      */       }
/*      */       else {
/*      */         
/* 5919 */         this.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4 + 1);
/* 5920 */         this.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4 + 1);
/*      */       } 
/*      */       
/* 5923 */       if (!var19 && !var16) {
/*      */         
/* 5925 */         this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXYPN;
/* 5926 */         this.aoBrightnessXYZPNN = this.aoBrightnessXYPN;
/*      */       }
/*      */       else {
/*      */         
/* 5930 */         this.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4 - 1);
/* 5931 */         this.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4 - 1);
/*      */       } 
/*      */       
/* 5934 */       if (!var18 && !var16) {
/*      */         
/* 5936 */         this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXYPN;
/* 5937 */         this.aoBrightnessXYZPNP = this.aoBrightnessXYPN;
/*      */       }
/*      */       else {
/*      */         
/* 5941 */         this.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4 + 1);
/* 5942 */         this.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4 + 1);
/*      */       } 
/*      */       
/* 5945 */       if (this.renderMinY <= 0.0D)
/*      */       {
/* 5947 */         par3++;
/*      */       }
/*      */       
/* 5950 */       int var20 = var14;
/*      */       
/* 5952 */       if (this.renderMinY <= 0.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2, par3 - 1, par4))
/*      */       {
/* 5954 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/*      */       }
/*      */       
/* 5957 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 5958 */       var9 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXYNN + this.aoLightValueScratchYZNP + var21) / 4.0F;
/* 5959 */       var12 = (this.aoLightValueScratchYZNP + var21 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXYPN) / 4.0F;
/* 5960 */       var11 = (var21 + this.aoLightValueScratchYZNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNN) / 4.0F;
/* 5961 */       var10 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNN + var21 + this.aoLightValueScratchYZNN) / 4.0F;
/* 5962 */       this.brightnessTopLeft = getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXYNN, this.aoBrightnessYZNP, var20);
/* 5963 */       this.brightnessTopRight = getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXYPN, var20);
/* 5964 */       this.brightnessBottomRight = getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYPN, this.aoBrightnessXYZPNN, var20);
/* 5965 */       this.brightnessBottomLeft = getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNN, this.aoBrightnessYZNN, var20);
/*      */       
/* 5967 */       if (var13) {
/*      */         
/* 5969 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.5F;
/* 5970 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.5F;
/* 5971 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.5F;
/*      */       }
/*      */       else {
/*      */         
/* 5975 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.5F;
/* 5976 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.5F;
/* 5977 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.5F;
/*      */       } 
/*      */       
/* 5980 */       this.colorRedTopLeft *= var9;
/* 5981 */       this.colorGreenTopLeft *= var9;
/* 5982 */       this.colorBlueTopLeft *= var9;
/* 5983 */       this.colorRedBottomLeft *= var10;
/* 5984 */       this.colorGreenBottomLeft *= var10;
/* 5985 */       this.colorBlueBottomLeft *= var10;
/* 5986 */       this.colorRedBottomRight *= var11;
/* 5987 */       this.colorGreenBottomRight *= var11;
/* 5988 */       this.colorBlueBottomRight *= var11;
/* 5989 */       this.colorRedTopRight *= var12;
/* 5990 */       this.colorGreenTopRight *= var12;
/* 5991 */       this.colorBlueTopRight *= var12;
/* 5992 */       renderFaceYNeg(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 0));
/* 5993 */       var8 = true;
/*      */     } 
/*      */     
/* 5996 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 + 1, par4, 1)) {
/*      */       
/* 5998 */       if (this.renderMaxY >= 1.0D)
/*      */       {
/* 6000 */         par3++;
/*      */       }
/*      */       
/* 6003 */       this.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/* 6004 */       this.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/* 6005 */       this.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/* 6006 */       this.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/* 6007 */       this.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 6008 */       this.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/* 6009 */       this.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 6010 */       this.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6015 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 + 1, y + 1, par4)];
/* 6016 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 - 1, y + 1, par4)];
/* 6017 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, y + 1, par4 + 1)];
/* 6018 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, y + 1, par4 - 1)];
/*      */       
/* 6020 */       if (!var19 && !var17) {
/*      */         
/* 6022 */         this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXYNP;
/* 6023 */         this.aoBrightnessXYZNPN = this.aoBrightnessXYNP;
/*      */       }
/*      */       else {
/*      */         
/* 6027 */         this.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4 - 1);
/* 6028 */         this.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4 - 1);
/*      */       } 
/*      */       
/* 6031 */       if (!var19 && !var16) {
/*      */         
/* 6033 */         this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXYPP;
/* 6034 */         this.aoBrightnessXYZPPN = this.aoBrightnessXYPP;
/*      */       }
/*      */       else {
/*      */         
/* 6038 */         this.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4 - 1);
/* 6039 */         this.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4 - 1);
/*      */       } 
/*      */       
/* 6042 */       if (!var18 && !var17) {
/*      */         
/* 6044 */         this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXYNP;
/* 6045 */         this.aoBrightnessXYZNPP = this.aoBrightnessXYNP;
/*      */       }
/*      */       else {
/*      */         
/* 6049 */         this.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4 + 1);
/* 6050 */         this.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4 + 1);
/*      */       } 
/*      */       
/* 6053 */       if (!var18 && !var16) {
/*      */         
/* 6055 */         this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXYPP;
/* 6056 */         this.aoBrightnessXYZPPP = this.aoBrightnessXYPP;
/*      */       }
/*      */       else {
/*      */         
/* 6060 */         this.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4 + 1);
/* 6061 */         this.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4 + 1);
/*      */       } 
/*      */       
/* 6064 */       if (this.renderMaxY >= 1.0D)
/*      */       {
/* 6066 */         par3--;
/*      */       }
/*      */       
/* 6069 */       int var20 = var14;
/*      */       
/* 6071 */       if (this.renderMaxY >= 1.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2, par3 + 1, par4))
/*      */       {
/* 6073 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/*      */       }
/*      */       
/* 6076 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 6077 */       var12 = (this.aoLightValueScratchXYZNPP + this.aoLightValueScratchXYNP + this.aoLightValueScratchYZPP + var21) / 4.0F;
/* 6078 */       var9 = (this.aoLightValueScratchYZPP + var21 + this.aoLightValueScratchXYZPPP + this.aoLightValueScratchXYPP) / 4.0F;
/* 6079 */       var10 = (var21 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPN) / 4.0F;
/* 6080 */       var11 = (this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPN + var21 + this.aoLightValueScratchYZPN) / 4.0F;
/* 6081 */       this.brightnessTopRight = getAoBrightness(this.aoBrightnessXYZNPP, this.aoBrightnessXYNP, this.aoBrightnessYZPP, var20);
/* 6082 */       this.brightnessTopLeft = getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXYZPPP, this.aoBrightnessXYPP, var20);
/* 6083 */       this.brightnessBottomLeft = getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXYPP, this.aoBrightnessXYZPPN, var20);
/* 6084 */       this.brightnessBottomRight = getAoBrightness(this.aoBrightnessXYNP, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, var20);
/* 6085 */       this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5;
/* 6086 */       this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6;
/* 6087 */       this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7;
/* 6088 */       this.colorRedTopLeft *= var9;
/* 6089 */       this.colorGreenTopLeft *= var9;
/* 6090 */       this.colorBlueTopLeft *= var9;
/* 6091 */       this.colorRedBottomLeft *= var10;
/* 6092 */       this.colorGreenBottomLeft *= var10;
/* 6093 */       this.colorBlueBottomLeft *= var10;
/* 6094 */       this.colorRedBottomRight *= var11;
/* 6095 */       this.colorGreenBottomRight *= var11;
/* 6096 */       this.colorBlueBottomRight *= var11;
/* 6097 */       this.colorRedTopRight *= var12;
/* 6098 */       this.colorGreenTopRight *= var12;
/* 6099 */       this.colorBlueTopRight *= var12;
/* 6100 */       renderFaceYPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 1));
/* 6101 */       var8 = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6114 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 - 1, 2)) {
/*      */       
/* 6116 */       if (this.renderMinZ <= 0.0D)
/*      */       {
/* 6118 */         par4--;
/*      */       }
/*      */       
/* 6121 */       this.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 6122 */       this.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 6123 */       this.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 6124 */       this.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/* 6125 */       this.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/* 6126 */       this.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/* 6127 */       this.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/* 6128 */       this.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6133 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 + 1, par3, z - 1)];
/* 6134 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 - 1, par3, z - 1)];
/* 6135 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, par3 + 1, z - 1)];
/* 6136 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, par3 - 1, z - 1)];
/*      */       
/* 6138 */       if (!var17 && !var19) {
/*      */         
/* 6140 */         this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
/* 6141 */         this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
/*      */       }
/*      */       else {
/*      */         
/* 6145 */         this.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3 - 1, par4);
/* 6146 */         this.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3 - 1, par4);
/*      */       } 
/*      */       
/* 6149 */       if (!var17 && !var18) {
/*      */         
/* 6151 */         this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
/* 6152 */         this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
/*      */       }
/*      */       else {
/*      */         
/* 6156 */         this.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3 + 1, par4);
/* 6157 */         this.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3 + 1, par4);
/*      */       } 
/*      */       
/* 6160 */       if (!var16 && !var19) {
/*      */         
/* 6162 */         this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
/* 6163 */         this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
/*      */       }
/*      */       else {
/*      */         
/* 6167 */         this.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3 - 1, par4);
/* 6168 */         this.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3 - 1, par4);
/*      */       } 
/*      */       
/* 6171 */       if (!var16 && !var18) {
/*      */         
/* 6173 */         this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
/* 6174 */         this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
/*      */       }
/*      */       else {
/*      */         
/* 6178 */         this.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3 + 1, par4);
/* 6179 */         this.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3 + 1, par4);
/*      */       } 
/*      */       
/* 6182 */       if (this.renderMinZ <= 0.0D)
/*      */       {
/* 6184 */         par4++;
/*      */       }
/*      */       
/* 6187 */       int var20 = var14;
/*      */       
/* 6189 */       if (this.renderMinZ <= 0.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2, par3, par4 - 1))
/*      */       {
/* 6191 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/*      */       }
/*      */       
/* 6194 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 6195 */       float var22 = (this.aoLightValueScratchXZNN + this.aoLightValueScratchXYZNPN + var21 + this.aoLightValueScratchYZPN) / 4.0F;
/* 6196 */       float var23 = (var21 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXZPN + this.aoLightValueScratchXYZPPN) / 4.0F;
/* 6197 */       float var24 = (this.aoLightValueScratchYZNN + var21 + this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXZPN) / 4.0F;
/* 6198 */       float var25 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXZNN + this.aoLightValueScratchYZNN + var21) / 4.0F;
/*      */       
/* 6200 */       var9 = (float)(var22 * this.renderMaxY * (1.0D - this.renderMinX) + var23 * this.renderMaxY * this.renderMinX + var24 * (1.0D - this.renderMaxY) * this.renderMinX + var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinX));
/* 6201 */       var10 = (float)(var22 * this.renderMaxY * (1.0D - this.renderMaxX) + var23 * this.renderMaxY * this.renderMaxX + var24 * (1.0D - this.renderMaxY) * this.renderMaxX + var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX));
/* 6202 */       var11 = (float)(var22 * this.renderMinY * (1.0D - this.renderMaxX) + var23 * this.renderMinY * this.renderMaxX + var24 * (1.0D - this.renderMinY) * this.renderMaxX + var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxX));
/* 6203 */       var12 = (float)(var22 * this.renderMinY * (1.0D - this.renderMinX) + var23 * this.renderMinY * this.renderMinX + var24 * (1.0D - this.renderMinY) * this.renderMinX + var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMinX));
/* 6204 */       int var26 = getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, var20);
/* 6205 */       int var27 = getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, var20);
/* 6206 */       int var28 = getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYZPNN, this.aoBrightnessXZPN, var20);
/* 6207 */       int var29 = getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXZNN, this.aoBrightnessYZNN, var20);
/* 6208 */       this.brightnessTopLeft = mixAoBrightness(var26, var27, var28, var29, this.renderMaxY * (1.0D - this.renderMinX), this.renderMaxY * this.renderMinX, (1.0D - this.renderMaxY) * this.renderMinX, (1.0D - this.renderMaxY) * (1.0D - this.renderMinX));
/* 6209 */       this.brightnessBottomLeft = mixAoBrightness(var26, var27, var28, var29, this.renderMaxY * (1.0D - this.renderMaxX), this.renderMaxY * this.renderMaxX, (1.0D - this.renderMaxY) * this.renderMaxX, (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX));
/* 6210 */       this.brightnessBottomRight = mixAoBrightness(var26, var27, var28, var29, this.renderMinY * (1.0D - this.renderMaxX), this.renderMinY * this.renderMaxX, (1.0D - this.renderMinY) * this.renderMaxX, (1.0D - this.renderMinY) * (1.0D - this.renderMaxX));
/* 6211 */       this.brightnessTopRight = mixAoBrightness(var26, var27, var28, var29, this.renderMinY * (1.0D - this.renderMinX), this.renderMinY * this.renderMinX, (1.0D - this.renderMinY) * this.renderMinX, (1.0D - this.renderMinY) * (1.0D - this.renderMinX));
/*      */       
/* 6213 */       if (var13) {
/*      */         
/* 6215 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.8F;
/* 6216 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.8F;
/* 6217 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.8F;
/*      */       }
/*      */       else {
/*      */         
/* 6221 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
/* 6222 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
/* 6223 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
/*      */       } 
/*      */       
/* 6226 */       this.colorRedTopLeft *= var9;
/* 6227 */       this.colorGreenTopLeft *= var9;
/* 6228 */       this.colorBlueTopLeft *= var9;
/* 6229 */       this.colorRedBottomLeft *= var10;
/* 6230 */       this.colorGreenBottomLeft *= var10;
/* 6231 */       this.colorBlueBottomLeft *= var10;
/* 6232 */       this.colorRedBottomRight *= var11;
/* 6233 */       this.colorGreenBottomRight *= var11;
/* 6234 */       this.colorBlueBottomRight *= var11;
/* 6235 */       this.colorRedTopRight *= var12;
/* 6236 */       this.colorGreenTopRight *= var12;
/* 6237 */       this.colorBlueTopRight *= var12;
/* 6238 */       Icon var30 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 2);
/* 6239 */       renderFaceZNeg(par1Block, par2, par3, par4, var30);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6244 */       if (var30.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 6246 */         this.colorRedTopLeft *= par5;
/* 6247 */         this.colorRedBottomLeft *= par5;
/* 6248 */         this.colorRedBottomRight *= par5;
/* 6249 */         this.colorRedTopRight *= par5;
/* 6250 */         this.colorGreenTopLeft *= par6;
/* 6251 */         this.colorGreenBottomLeft *= par6;
/* 6252 */         this.colorGreenBottomRight *= par6;
/* 6253 */         this.colorGreenTopRight *= par6;
/* 6254 */         this.colorBlueTopLeft *= par7;
/* 6255 */         this.colorBlueBottomLeft *= par7;
/* 6256 */         this.colorBlueBottomRight *= par7;
/* 6257 */         this.colorBlueTopRight *= par7;
/* 6258 */         renderFaceZNeg(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 6261 */       var8 = true;
/*      */     } 
/*      */     
/* 6264 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 + 1, 3)) {
/*      */       
/* 6266 */       if (this.renderMaxZ >= 1.0D)
/*      */       {
/* 6268 */         par4++;
/*      */       }
/*      */       
/* 6271 */       this.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 6272 */       this.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/* 6273 */       this.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 6274 */       this.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 6275 */       this.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/* 6276 */       this.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/* 6277 */       this.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/* 6278 */       this.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6283 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 + 1, par3, z + 1)];
/* 6284 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2 - 1, par3, z + 1)];
/* 6285 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, par3 + 1, z + 1)];
/* 6286 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(par2, par3 - 1, z + 1)];
/*      */       
/* 6288 */       if (!var17 && !var19) {
/*      */         
/* 6290 */         this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
/* 6291 */         this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
/*      */       }
/*      */       else {
/*      */         
/* 6295 */         this.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3 - 1, par4);
/* 6296 */         this.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3 - 1, par4);
/*      */       } 
/*      */       
/* 6299 */       if (!var17 && !var18) {
/*      */         
/* 6301 */         this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
/* 6302 */         this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
/*      */       }
/*      */       else {
/*      */         
/* 6306 */         this.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3 + 1, par4);
/* 6307 */         this.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3 + 1, par4);
/*      */       } 
/*      */       
/* 6310 */       if (!var16 && !var19) {
/*      */         
/* 6312 */         this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
/* 6313 */         this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
/*      */       }
/*      */       else {
/*      */         
/* 6317 */         this.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3 - 1, par4);
/* 6318 */         this.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3 - 1, par4);
/*      */       } 
/*      */       
/* 6321 */       if (!var16 && !var18) {
/*      */         
/* 6323 */         this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
/* 6324 */         this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
/*      */       }
/*      */       else {
/*      */         
/* 6328 */         this.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3 + 1, par4);
/* 6329 */         this.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3 + 1, par4);
/*      */       } 
/*      */       
/* 6332 */       if (this.renderMaxZ >= 1.0D)
/*      */       {
/* 6334 */         par4--;
/*      */       }
/*      */       
/* 6337 */       int var20 = var14;
/*      */       
/* 6339 */       if (this.renderMaxZ >= 1.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2, par3, par4 + 1))
/*      */       {
/* 6341 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/*      */       }
/*      */       
/* 6344 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/* 6345 */       float var22 = (this.aoLightValueScratchXZNP + this.aoLightValueScratchXYZNPP + var21 + this.aoLightValueScratchYZPP) / 4.0F;
/* 6346 */       float var23 = (var21 + this.aoLightValueScratchYZPP + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYZPPP) / 4.0F;
/* 6347 */       float var24 = (this.aoLightValueScratchYZNP + var21 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXZPP) / 4.0F;
/* 6348 */       float var25 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXZNP + this.aoLightValueScratchYZNP + var21) / 4.0F;
/* 6349 */       var9 = (float)(var22 * this.renderMaxY * (1.0D - this.renderMinX) + var23 * this.renderMaxY * this.renderMinX + var24 * (1.0D - this.renderMaxY) * this.renderMinX + var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinX));
/* 6350 */       var10 = (float)(var22 * this.renderMinY * (1.0D - this.renderMinX) + var23 * this.renderMinY * this.renderMinX + var24 * (1.0D - this.renderMinY) * this.renderMinX + var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMinX));
/* 6351 */       var11 = (float)(var22 * this.renderMinY * (1.0D - this.renderMaxX) + var23 * this.renderMinY * this.renderMaxX + var24 * (1.0D - this.renderMinY) * this.renderMaxX + var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxX));
/* 6352 */       var12 = (float)(var22 * this.renderMaxY * (1.0D - this.renderMaxX) + var23 * this.renderMaxY * this.renderMaxX + var24 * (1.0D - this.renderMaxY) * this.renderMaxX + var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX));
/* 6353 */       int var26 = getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYZNPP, this.aoBrightnessYZPP, var20);
/* 6354 */       int var27 = getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXZPP, this.aoBrightnessXYZPPP, var20);
/* 6355 */       int var28 = getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, var20);
/* 6356 */       int var29 = getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, this.aoBrightnessYZNP, var20);
/* 6357 */       this.brightnessTopLeft = mixAoBrightness(var26, var29, var28, var27, this.renderMaxY * (1.0D - this.renderMinX), (1.0D - this.renderMaxY) * (1.0D - this.renderMinX), (1.0D - this.renderMaxY) * this.renderMinX, this.renderMaxY * this.renderMinX);
/* 6358 */       this.brightnessBottomLeft = mixAoBrightness(var26, var29, var28, var27, this.renderMinY * (1.0D - this.renderMinX), (1.0D - this.renderMinY) * (1.0D - this.renderMinX), (1.0D - this.renderMinY) * this.renderMinX, this.renderMinY * this.renderMinX);
/* 6359 */       this.brightnessBottomRight = mixAoBrightness(var26, var29, var28, var27, this.renderMinY * (1.0D - this.renderMaxX), (1.0D - this.renderMinY) * (1.0D - this.renderMaxX), (1.0D - this.renderMinY) * this.renderMaxX, this.renderMinY * this.renderMaxX);
/* 6360 */       this.brightnessTopRight = mixAoBrightness(var26, var29, var28, var27, this.renderMaxY * (1.0D - this.renderMaxX), (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX), (1.0D - this.renderMaxY) * this.renderMaxX, this.renderMaxY * this.renderMaxX);
/*      */       
/* 6362 */       if (var13) {
/*      */         
/* 6364 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.8F;
/* 6365 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.8F;
/* 6366 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.8F;
/*      */       }
/*      */       else {
/*      */         
/* 6370 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
/* 6371 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
/* 6372 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
/*      */       } 
/*      */       
/* 6375 */       this.colorRedTopLeft *= var9;
/* 6376 */       this.colorGreenTopLeft *= var9;
/* 6377 */       this.colorBlueTopLeft *= var9;
/* 6378 */       this.colorRedBottomLeft *= var10;
/* 6379 */       this.colorGreenBottomLeft *= var10;
/* 6380 */       this.colorBlueBottomLeft *= var10;
/* 6381 */       this.colorRedBottomRight *= var11;
/* 6382 */       this.colorGreenBottomRight *= var11;
/* 6383 */       this.colorBlueBottomRight *= var11;
/* 6384 */       this.colorRedTopRight *= var12;
/* 6385 */       this.colorGreenTopRight *= var12;
/* 6386 */       this.colorBlueTopRight *= var12;
/* 6387 */       Icon var30 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 3);
/* 6388 */       renderFaceZPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 3));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6393 */       if (var30.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 6395 */         this.colorRedTopLeft *= par5;
/* 6396 */         this.colorRedBottomLeft *= par5;
/* 6397 */         this.colorRedBottomRight *= par5;
/* 6398 */         this.colorRedTopRight *= par5;
/* 6399 */         this.colorGreenTopLeft *= par6;
/* 6400 */         this.colorGreenBottomLeft *= par6;
/* 6401 */         this.colorGreenBottomRight *= par6;
/* 6402 */         this.colorGreenTopRight *= par6;
/* 6403 */         this.colorBlueTopLeft *= par7;
/* 6404 */         this.colorBlueBottomLeft *= par7;
/* 6405 */         this.colorBlueBottomRight *= par7;
/* 6406 */         this.colorBlueTopRight *= par7;
/* 6407 */         renderFaceZPos(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 6410 */       var8 = true;
/*      */     } 
/*      */     
/* 6413 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2 - 1, par3, par4, 4)) {
/*      */       
/* 6415 */       if (this.renderMinX <= 0.0D)
/*      */       {
/* 6417 */         par2--;
/*      */       }
/*      */       
/* 6420 */       this.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 6421 */       this.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 6422 */       this.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/* 6423 */       this.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 6424 */       this.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/* 6425 */       this.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/* 6426 */       this.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/* 6427 */       this.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6432 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(x - 1, par3 + 1, par4)];
/* 6433 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(x - 1, par3 - 1, par4)];
/* 6434 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(x - 1, par3, par4 - 1)];
/* 6435 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(x - 1, par3, par4 + 1)];
/*      */       
/* 6437 */       if (!var18 && !var17) {
/*      */         
/* 6439 */         this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
/* 6440 */         this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
/*      */       }
/*      */       else {
/*      */         
/* 6444 */         this.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4 - 1);
/* 6445 */         this.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4 - 1);
/*      */       } 
/*      */       
/* 6448 */       if (!var19 && !var17) {
/*      */         
/* 6450 */         this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
/* 6451 */         this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
/*      */       }
/*      */       else {
/*      */         
/* 6455 */         this.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4 + 1);
/* 6456 */         this.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4 + 1);
/*      */       } 
/*      */       
/* 6459 */       if (!var18 && !var16) {
/*      */         
/* 6461 */         this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
/* 6462 */         this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
/*      */       }
/*      */       else {
/*      */         
/* 6466 */         this.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4 - 1);
/* 6467 */         this.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4 - 1);
/*      */       } 
/*      */       
/* 6470 */       if (!var19 && !var16) {
/*      */         
/* 6472 */         this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
/* 6473 */         this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
/*      */       }
/*      */       else {
/*      */         
/* 6477 */         this.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4 + 1);
/* 6478 */         this.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4 + 1);
/*      */       } 
/*      */       
/* 6481 */       if (this.renderMinX <= 0.0D)
/*      */       {
/* 6483 */         par2++;
/*      */       }
/*      */       
/* 6486 */       int var20 = var14;
/*      */       
/* 6488 */       if (this.renderMinX <= 0.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2 - 1, par3, par4))
/*      */       {
/* 6490 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4);
/*      */       }
/*      */       
/* 6493 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 - 1, par3, par4);
/* 6494 */       float var22 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNP + var21 + this.aoLightValueScratchXZNP) / 4.0F;
/* 6495 */       float var23 = (var21 + this.aoLightValueScratchXZNP + this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPP) / 4.0F;
/* 6496 */       float var24 = (this.aoLightValueScratchXZNN + var21 + this.aoLightValueScratchXYZNPN + this.aoLightValueScratchXYNP) / 4.0F;
/* 6497 */       float var25 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXYNN + this.aoLightValueScratchXZNN + var21) / 4.0F;
/* 6498 */       var9 = (float)(var23 * this.renderMaxY * this.renderMaxZ + var24 * this.renderMaxY * (1.0D - this.renderMaxZ) + var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ) + var22 * (1.0D - this.renderMaxY) * this.renderMaxZ);
/* 6499 */       var10 = (float)(var23 * this.renderMaxY * this.renderMinZ + var24 * this.renderMaxY * (1.0D - this.renderMinZ) + var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ) + var22 * (1.0D - this.renderMaxY) * this.renderMinZ);
/* 6500 */       var11 = (float)(var23 * this.renderMinY * this.renderMinZ + var24 * this.renderMinY * (1.0D - this.renderMinZ) + var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMinZ) + var22 * (1.0D - this.renderMinY) * this.renderMinZ);
/* 6501 */       var12 = (float)(var23 * this.renderMinY * this.renderMaxZ + var24 * this.renderMinY * (1.0D - this.renderMaxZ) + var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ) + var22 * (1.0D - this.renderMinY) * this.renderMaxZ);
/* 6502 */       int var26 = getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, var20);
/* 6503 */       int var27 = getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYNP, this.aoBrightnessXYZNPP, var20);
/* 6504 */       int var28 = getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessXYNP, var20);
/* 6505 */       int var29 = getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXYNN, this.aoBrightnessXZNN, var20);
/* 6506 */       this.brightnessTopLeft = mixAoBrightness(var27, var28, var29, var26, this.renderMaxY * this.renderMaxZ, this.renderMaxY * (1.0D - this.renderMaxZ), (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ), (1.0D - this.renderMaxY) * this.renderMaxZ);
/* 6507 */       this.brightnessBottomLeft = mixAoBrightness(var27, var28, var29, var26, this.renderMaxY * this.renderMinZ, this.renderMaxY * (1.0D - this.renderMinZ), (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ), (1.0D - this.renderMaxY) * this.renderMinZ);
/* 6508 */       this.brightnessBottomRight = mixAoBrightness(var27, var28, var29, var26, this.renderMinY * this.renderMinZ, this.renderMinY * (1.0D - this.renderMinZ), (1.0D - this.renderMinY) * (1.0D - this.renderMinZ), (1.0D - this.renderMinY) * this.renderMinZ);
/* 6509 */       this.brightnessTopRight = mixAoBrightness(var27, var28, var29, var26, this.renderMinY * this.renderMaxZ, this.renderMinY * (1.0D - this.renderMaxZ), (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ), (1.0D - this.renderMinY) * this.renderMaxZ);
/*      */       
/* 6511 */       if (var13) {
/*      */         
/* 6513 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.6F;
/* 6514 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.6F;
/* 6515 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.6F;
/*      */       }
/*      */       else {
/*      */         
/* 6519 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
/* 6520 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
/* 6521 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
/*      */       } 
/*      */       
/* 6524 */       this.colorRedTopLeft *= var9;
/* 6525 */       this.colorGreenTopLeft *= var9;
/* 6526 */       this.colorBlueTopLeft *= var9;
/* 6527 */       this.colorRedBottomLeft *= var10;
/* 6528 */       this.colorGreenBottomLeft *= var10;
/* 6529 */       this.colorBlueBottomLeft *= var10;
/* 6530 */       this.colorRedBottomRight *= var11;
/* 6531 */       this.colorGreenBottomRight *= var11;
/* 6532 */       this.colorBlueBottomRight *= var11;
/* 6533 */       this.colorRedTopRight *= var12;
/* 6534 */       this.colorGreenTopRight *= var12;
/* 6535 */       this.colorBlueTopRight *= var12;
/* 6536 */       Icon var30 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 4);
/* 6537 */       renderFaceXNeg(par1Block, par2, par3, par4, var30);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6542 */       if (var30.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 6544 */         this.colorRedTopLeft *= par5;
/* 6545 */         this.colorRedBottomLeft *= par5;
/* 6546 */         this.colorRedBottomRight *= par5;
/* 6547 */         this.colorRedTopRight *= par5;
/* 6548 */         this.colorGreenTopLeft *= par6;
/* 6549 */         this.colorGreenBottomLeft *= par6;
/* 6550 */         this.colorGreenBottomRight *= par6;
/* 6551 */         this.colorGreenTopRight *= par6;
/* 6552 */         this.colorBlueTopLeft *= par7;
/* 6553 */         this.colorBlueBottomLeft *= par7;
/* 6554 */         this.colorBlueBottomRight *= par7;
/* 6555 */         this.colorBlueTopRight *= par7;
/* 6556 */         renderFaceXNeg(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 6559 */       var8 = true;
/*      */     } 
/*      */     
/* 6562 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2 + 1, par3, par4, 5)) {
/*      */       
/* 6564 */       if (this.renderMaxX >= 1.0D)
/*      */       {
/* 6566 */         par2++;
/*      */       }
/*      */       
/* 6569 */       this.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4);
/* 6570 */       this.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 - 1);
/* 6571 */       this.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3, par4 + 1);
/* 6572 */       this.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4);
/* 6573 */       this.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4);
/* 6574 */       this.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1);
/* 6575 */       this.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1);
/* 6576 */       this.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6581 */       boolean var16 = Block.canHaveLightValue[this.blockAccess.getBlockId(x + 1, par3 + 1, par4)];
/* 6582 */       boolean var17 = Block.canHaveLightValue[this.blockAccess.getBlockId(x + 1, par3 - 1, par4)];
/* 6583 */       boolean var18 = Block.canHaveLightValue[this.blockAccess.getBlockId(x + 1, par3, par4 + 1)];
/* 6584 */       boolean var19 = Block.canHaveLightValue[this.blockAccess.getBlockId(x + 1, par3, par4 - 1)];
/*      */       
/* 6586 */       if (!var17 && !var19) {
/*      */         
/* 6588 */         this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
/* 6589 */         this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
/*      */       }
/*      */       else {
/*      */         
/* 6593 */         this.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4 - 1);
/* 6594 */         this.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4 - 1);
/*      */       } 
/*      */       
/* 6597 */       if (!var17 && !var18) {
/*      */         
/* 6599 */         this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
/* 6600 */         this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
/*      */       }
/*      */       else {
/*      */         
/* 6604 */         this.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 - 1, par4 + 1);
/* 6605 */         this.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4 + 1);
/*      */       } 
/*      */       
/* 6608 */       if (!var16 && !var19) {
/*      */         
/* 6610 */         this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
/* 6611 */         this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
/*      */       }
/*      */       else {
/*      */         
/* 6615 */         this.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4 - 1);
/* 6616 */         this.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4 - 1);
/*      */       } 
/*      */       
/* 6619 */       if (!var16 && !var18) {
/*      */         
/* 6621 */         this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
/* 6622 */         this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
/*      */       }
/*      */       else {
/*      */         
/* 6626 */         this.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2, par3 + 1, par4 + 1);
/* 6627 */         this.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4 + 1);
/*      */       } 
/*      */       
/* 6630 */       if (this.renderMaxX >= 1.0D)
/*      */       {
/* 6632 */         par2--;
/*      */       }
/*      */       
/* 6635 */       int var20 = var14;
/*      */       
/* 6637 */       if (this.renderMaxX >= 1.0D || !this.blockAccess.isBlockStandardFormOpaqueCube(par2 + 1, par3, par4))
/*      */       {
/* 6639 */         var20 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4);
/*      */       }
/*      */       
/* 6642 */       float var21 = par1Block.getAmbientOcclusionLightValue(this.blockAccess, par2 + 1, par3, par4);
/* 6643 */       float var22 = (this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNP + var21 + this.aoLightValueScratchXZPP) / 4.0F;
/* 6644 */       float var23 = (this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXZPN + var21) / 4.0F;
/* 6645 */       float var24 = (this.aoLightValueScratchXZPN + var21 + this.aoLightValueScratchXYZPPN + this.aoLightValueScratchXYPP) / 4.0F;
/* 6646 */       float var25 = (var21 + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPP) / 4.0F;
/* 6647 */       var9 = (float)(var22 * (1.0D - this.renderMinY) * this.renderMaxZ + var23 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ) + var24 * this.renderMinY * (1.0D - this.renderMaxZ) + var25 * this.renderMinY * this.renderMaxZ);
/* 6648 */       var10 = (float)(var22 * (1.0D - this.renderMinY) * this.renderMinZ + var23 * (1.0D - this.renderMinY) * (1.0D - this.renderMinZ) + var24 * this.renderMinY * (1.0D - this.renderMinZ) + var25 * this.renderMinY * this.renderMinZ);
/* 6649 */       var11 = (float)(var22 * (1.0D - this.renderMaxY) * this.renderMinZ + var23 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ) + var24 * this.renderMaxY * (1.0D - this.renderMinZ) + var25 * this.renderMaxY * this.renderMinZ);
/* 6650 */       var12 = (float)(var22 * (1.0D - this.renderMaxY) * this.renderMaxZ + var23 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ) + var24 * this.renderMaxY * (1.0D - this.renderMaxZ) + var25 * this.renderMaxY * this.renderMaxZ);
/* 6651 */       int var26 = getAoBrightness(this.aoBrightnessXYPN, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, var20);
/* 6652 */       int var27 = getAoBrightness(this.aoBrightnessXZPP, this.aoBrightnessXYPP, this.aoBrightnessXYZPPP, var20);
/* 6653 */       int var28 = getAoBrightness(this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, this.aoBrightnessXYPP, var20);
/* 6654 */       int var29 = getAoBrightness(this.aoBrightnessXYZPNN, this.aoBrightnessXYPN, this.aoBrightnessXZPN, var20);
/* 6655 */       this.brightnessTopLeft = mixAoBrightness(var26, var29, var28, var27, (1.0D - this.renderMinY) * this.renderMaxZ, (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ), this.renderMinY * (1.0D - this.renderMaxZ), this.renderMinY * this.renderMaxZ);
/* 6656 */       this.brightnessBottomLeft = mixAoBrightness(var26, var29, var28, var27, (1.0D - this.renderMinY) * this.renderMinZ, (1.0D - this.renderMinY) * (1.0D - this.renderMinZ), this.renderMinY * (1.0D - this.renderMinZ), this.renderMinY * this.renderMinZ);
/* 6657 */       this.brightnessBottomRight = mixAoBrightness(var26, var29, var28, var27, (1.0D - this.renderMaxY) * this.renderMinZ, (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ), this.renderMaxY * (1.0D - this.renderMinZ), this.renderMaxY * this.renderMinZ);
/* 6658 */       this.brightnessTopRight = mixAoBrightness(var26, var29, var28, var27, (1.0D - this.renderMaxY) * this.renderMaxZ, (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ), this.renderMaxY * (1.0D - this.renderMaxZ), this.renderMaxY * this.renderMaxZ);
/*      */       
/* 6660 */       if (var13) {
/*      */         
/* 6662 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = par5 * 0.6F;
/* 6663 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = par6 * 0.6F;
/* 6664 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = par7 * 0.6F;
/*      */       }
/*      */       else {
/*      */         
/* 6668 */         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
/* 6669 */         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
/* 6670 */         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
/*      */       } 
/*      */       
/* 6673 */       this.colorRedTopLeft *= var9;
/* 6674 */       this.colorGreenTopLeft *= var9;
/* 6675 */       this.colorBlueTopLeft *= var9;
/* 6676 */       this.colorRedBottomLeft *= var10;
/* 6677 */       this.colorGreenBottomLeft *= var10;
/* 6678 */       this.colorBlueBottomLeft *= var10;
/* 6679 */       this.colorRedBottomRight *= var11;
/* 6680 */       this.colorGreenBottomRight *= var11;
/* 6681 */       this.colorBlueBottomRight *= var11;
/* 6682 */       this.colorRedTopRight *= var12;
/* 6683 */       this.colorGreenTopRight *= var12;
/* 6684 */       this.colorBlueTopRight *= var12;
/* 6685 */       Icon var30 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 5);
/* 6686 */       renderFaceXPos(par1Block, par2, par3, par4, var30);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6691 */       if (var30.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 6693 */         this.colorRedTopLeft *= par5;
/* 6694 */         this.colorRedBottomLeft *= par5;
/* 6695 */         this.colorRedBottomRight *= par5;
/* 6696 */         this.colorRedTopRight *= par5;
/* 6697 */         this.colorGreenTopLeft *= par6;
/* 6698 */         this.colorGreenBottomLeft *= par6;
/* 6699 */         this.colorGreenBottomRight *= par6;
/* 6700 */         this.colorGreenTopRight *= par6;
/* 6701 */         this.colorBlueTopLeft *= par7;
/* 6702 */         this.colorBlueBottomLeft *= par7;
/* 6703 */         this.colorBlueBottomRight *= par7;
/* 6704 */         this.colorBlueTopRight *= par7;
/* 6705 */         renderFaceXPos(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 6708 */       var8 = true;
/*      */     } 
/*      */     
/* 6711 */     this.enableAO = false;
/* 6712 */     return var8;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getBrightnessOfSkylight(int mixed_brightness) {
/* 6717 */     return (mixed_brightness & 0xFF) / 16;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getBrightnessOfBlocklight(int mixed_brightness) {
/* 6722 */     return mixed_brightness >> 20;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getAoBrightness(int par1, int par2, int par3, int par4) {
/* 6730 */     if (par1 == 0)
/*      */     {
/* 6732 */       par1 = par4;
/*      */     }
/*      */     
/* 6735 */     if (par2 == 0)
/*      */     {
/* 6737 */       par2 = par4;
/*      */     }
/*      */     
/* 6740 */     if (par3 == 0)
/*      */     {
/* 6742 */       par3 = par4;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6766 */     return par1 + par2 + par3 + par4 >> 2 & 0xFF00FF;
/*      */   }
/*      */ 
/*      */   
/*      */   private int mixAoBrightness(int par1, int par2, int par3, int par4, double par5, double par7, double par9, double par11) {
/* 6771 */     int var13 = (int)((par1 >> 16 & 0xFF) * par5 + (par2 >> 16 & 0xFF) * par7 + (par3 >> 16 & 0xFF) * par9 + (par4 >> 16 & 0xFF) * par11) & 0xFF;
/* 6772 */     int var14 = (int)((par1 & 0xFF) * par5 + (par2 & 0xFF) * par7 + (par3 & 0xFF) * par9 + (par4 & 0xFF) * par11) & 0xFF;
/* 6773 */     return var13 << 16 | var14;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderStandardBlockWithColorMultiplier(Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7) {
/* 6781 */     this.enableAO = false;
/* 6782 */     Tessellator var8 = Tessellator.instance;
/* 6783 */     boolean var9 = false;
/* 6784 */     float var10 = 0.5F;
/* 6785 */     float var11 = 1.0F;
/* 6786 */     float var12 = 0.8F;
/* 6787 */     float var13 = 0.6F;
/* 6788 */     float var14 = var11 * par5;
/* 6789 */     float var15 = var11 * par6;
/* 6790 */     float var16 = var11 * par7;
/* 6791 */     float var17 = var10;
/* 6792 */     float var18 = var12;
/* 6793 */     float var19 = var13;
/* 6794 */     float var20 = var10;
/* 6795 */     float var21 = var12;
/* 6796 */     float var22 = var13;
/* 6797 */     float var23 = var10;
/* 6798 */     float var24 = var12;
/* 6799 */     float var25 = var13;
/*      */     
/* 6801 */     if (par1Block != Block.grass) {
/*      */       
/* 6803 */       var17 = var10 * par5;
/* 6804 */       var18 = var12 * par5;
/* 6805 */       var19 = var13 * par5;
/* 6806 */       var20 = var10 * par6;
/* 6807 */       var21 = var12 * par6;
/* 6808 */       var22 = var13 * par6;
/* 6809 */       var23 = var10 * par7;
/* 6810 */       var24 = var12 * par7;
/* 6811 */       var25 = var13 * par7;
/*      */     } 
/*      */     
/* 6814 */     int var26 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4);
/*      */     
/* 6816 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 - 1, par4, 0)) {
/*      */       
/* 6818 */       var8.setBrightness((this.renderMinY > 0.0D) ? var26 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4));
/* 6819 */       var8.setColorOpaque_F(var17, var20, var23);
/* 6820 */       renderFaceYNeg(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 0));
/* 6821 */       var9 = true;
/*      */     } 
/*      */     
/* 6824 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 + 1, par4, 1)) {
/*      */       
/* 6826 */       var8.setBrightness((this.renderMaxY < 1.0D) ? var26 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4));
/* 6827 */       var8.setColorOpaque_F(var14, var15, var16);
/* 6828 */       renderFaceYPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 1));
/* 6829 */       var9 = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 6834 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 - 1, 2)) {
/*      */       
/* 6836 */       var8.setBrightness((this.renderMinZ > 0.0D) ? var26 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1));
/* 6837 */       var8.setColorOpaque_F(var18, var21, var24);
/* 6838 */       Icon var28 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 2);
/* 6839 */       renderFaceZNeg(par1Block, par2, par3, par4, var28);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6844 */       if (var28.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 6846 */         var8.setColorOpaque_F(var18 * par5, var21 * par6, var24 * par7);
/* 6847 */         renderFaceZNeg(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 6850 */       var9 = true;
/*      */     } 
/*      */     
/* 6853 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3, par4 + 1, 3)) {
/*      */       
/* 6855 */       var8.setBrightness((this.renderMaxZ < 1.0D) ? var26 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1));
/* 6856 */       var8.setColorOpaque_F(var18, var21, var24);
/* 6857 */       Icon var28 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 3);
/* 6858 */       renderFaceZPos(par1Block, par2, par3, par4, var28);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6863 */       if (var28.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 6865 */         var8.setColorOpaque_F(var18 * par5, var21 * par6, var24 * par7);
/* 6866 */         renderFaceZPos(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 6869 */       var9 = true;
/*      */     } 
/*      */     
/* 6872 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2 - 1, par3, par4, 4)) {
/*      */       
/* 6874 */       var8.setBrightness((this.renderMinX > 0.0D) ? var26 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4));
/* 6875 */       var8.setColorOpaque_F(var19, var22, var25);
/* 6876 */       Icon var28 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 4);
/* 6877 */       renderFaceXNeg(par1Block, par2, par3, par4, var28);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6882 */       if (var28.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 6884 */         var8.setColorOpaque_F(var19 * par5, var22 * par6, var25 * par7);
/* 6885 */         renderFaceXNeg(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 6888 */       var9 = true;
/*      */     } 
/*      */     
/* 6891 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2 + 1, par3, par4, 5)) {
/*      */       
/* 6893 */       var8.setBrightness((this.renderMaxX < 1.0D) ? var26 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4));
/* 6894 */       var8.setColorOpaque_F(var19, var22, var25);
/* 6895 */       Icon var28 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 5);
/* 6896 */       renderFaceXPos(par1Block, par2, par3, par4, var28);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6901 */       if (var28.isGreenGrassSide() && this.overrideBlockTexture == null && fancyGrass) {
/*      */         
/* 6903 */         var8.setColorOpaque_F(var19 * par5, var22 * par6, var25 * par7);
/* 6904 */         renderFaceXPos(par1Block, par2, par3, par4, BlockGrass.getIconSideOverlay());
/*      */       } 
/*      */       
/* 6907 */       var9 = true;
/*      */     } 
/*      */     
/* 6910 */     return var9;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockCocoa(BlockCocoa par1BlockCocoa, int par2, int par3, int par4) {
/*      */     double var45;
/* 6918 */     Tessellator var5 = Tessellator.instance;
/* 6919 */     var5.setBrightness(par1BlockCocoa.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 6920 */     var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
/* 6921 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*      */     
/* 6923 */     int var7 = BlockCocoa.getDirection(var6);
/* 6924 */     int var8 = BlockCocoa.func_72219_c(var6);
/* 6925 */     Icon var9 = par1BlockCocoa.getCocoaIcon(var8);
/* 6926 */     int var10 = 4 + var8 * 2;
/* 6927 */     int var11 = 5 + var8 * 2;
/* 6928 */     double var12 = 15.0D - var10;
/* 6929 */     double var14 = 15.0D;
/* 6930 */     double var16 = 4.0D;
/* 6931 */     double var18 = 4.0D + var11;
/* 6932 */     double var20 = var9.getInterpolatedU(var12);
/* 6933 */     double var22 = var9.getInterpolatedU(var14);
/* 6934 */     double var24 = var9.getInterpolatedV(var16);
/* 6935 */     double var26 = var9.getInterpolatedV(var18);
/* 6936 */     double var28 = 0.0D;
/* 6937 */     double var30 = 0.0D;
/*      */     
/* 6939 */     switch (var7) {
/*      */       
/*      */       case 0:
/* 6942 */         var28 = 8.0D - (var10 / 2);
/* 6943 */         var30 = 15.0D - var10;
/*      */         break;
/*      */       
/*      */       case 1:
/* 6947 */         var28 = 1.0D;
/* 6948 */         var30 = 8.0D - (var10 / 2);
/*      */         break;
/*      */       
/*      */       case 2:
/* 6952 */         var28 = 8.0D - (var10 / 2);
/* 6953 */         var30 = 1.0D;
/*      */         break;
/*      */       
/*      */       case 3:
/* 6957 */         var28 = 15.0D - var10;
/* 6958 */         var30 = 8.0D - (var10 / 2);
/*      */         break;
/*      */     } 
/* 6961 */     double var32 = par2 + var28 / 16.0D;
/* 6962 */     double var34 = par2 + (var28 + var10) / 16.0D;
/* 6963 */     double var36 = par3 + (12.0D - var11) / 16.0D;
/* 6964 */     double var38 = par3 + 0.75D;
/* 6965 */     double var40 = par4 + var30 / 16.0D;
/* 6966 */     double var42 = par4 + (var30 + var10) / 16.0D;
/* 6967 */     var5.addVertexWithUV(var32, var36, var40, var20, var26);
/* 6968 */     var5.addVertexWithUV(var32, var36, var42, var22, var26);
/* 6969 */     var5.addVertexWithUV(var32, var38, var42, var22, var24);
/* 6970 */     var5.addVertexWithUV(var32, var38, var40, var20, var24);
/* 6971 */     var5.addVertexWithUV(var34, var36, var42, var20, var26);
/* 6972 */     var5.addVertexWithUV(var34, var36, var40, var22, var26);
/* 6973 */     var5.addVertexWithUV(var34, var38, var40, var22, var24);
/* 6974 */     var5.addVertexWithUV(var34, var38, var42, var20, var24);
/* 6975 */     var5.addVertexWithUV(var34, var36, var40, var20, var26);
/* 6976 */     var5.addVertexWithUV(var32, var36, var40, var22, var26);
/* 6977 */     var5.addVertexWithUV(var32, var38, var40, var22, var24);
/* 6978 */     var5.addVertexWithUV(var34, var38, var40, var20, var24);
/* 6979 */     var5.addVertexWithUV(var32, var36, var42, var20, var26);
/* 6980 */     var5.addVertexWithUV(var34, var36, var42, var22, var26);
/* 6981 */     var5.addVertexWithUV(var34, var38, var42, var22, var24);
/* 6982 */     var5.addVertexWithUV(var32, var38, var42, var20, var24);
/* 6983 */     int var44 = var10;
/*      */     
/* 6985 */     if (var8 >= 2)
/*      */     {
/* 6987 */       var44 = var10 - 1;
/*      */     }
/*      */     
/* 6990 */     var20 = var9.getMinU();
/* 6991 */     var22 = var9.getInterpolatedU(var44);
/* 6992 */     var24 = var9.getMinV();
/* 6993 */     var26 = var9.getInterpolatedV(var44);
/* 6994 */     var5.addVertexWithUV(var32, var38, var42, var20, var26);
/* 6995 */     var5.addVertexWithUV(var34, var38, var42, var22, var26);
/* 6996 */     var5.addVertexWithUV(var34, var38, var40, var22, var24);
/* 6997 */     var5.addVertexWithUV(var32, var38, var40, var20, var24);
/* 6998 */     var5.addVertexWithUV(var32, var36, var40, var20, var24);
/* 6999 */     var5.addVertexWithUV(var34, var36, var40, var22, var24);
/* 7000 */     var5.addVertexWithUV(var34, var36, var42, var22, var26);
/* 7001 */     var5.addVertexWithUV(var32, var36, var42, var20, var26);
/* 7002 */     var20 = var9.getInterpolatedU(12.0D);
/* 7003 */     var22 = var9.getMaxU();
/* 7004 */     var24 = var9.getMinV();
/* 7005 */     var26 = var9.getInterpolatedV(4.0D);
/* 7006 */     var28 = 8.0D;
/* 7007 */     var30 = 0.0D;
/*      */ 
/*      */     
/* 7010 */     switch (var7) {
/*      */       
/*      */       case 0:
/* 7013 */         var28 = 8.0D;
/* 7014 */         var30 = 12.0D;
/* 7015 */         var45 = var20;
/* 7016 */         var20 = var22;
/* 7017 */         var22 = var45;
/*      */         break;
/*      */       
/*      */       case 1:
/* 7021 */         var28 = 0.0D;
/* 7022 */         var30 = 8.0D;
/*      */         break;
/*      */       
/*      */       case 2:
/* 7026 */         var28 = 8.0D;
/* 7027 */         var30 = 0.0D;
/*      */         break;
/*      */       
/*      */       case 3:
/* 7031 */         var28 = 12.0D;
/* 7032 */         var30 = 8.0D;
/* 7033 */         var45 = var20;
/* 7034 */         var20 = var22;
/* 7035 */         var22 = var45;
/*      */         break;
/*      */     } 
/* 7038 */     var32 = par2 + var28 / 16.0D;
/* 7039 */     var34 = par2 + (var28 + 4.0D) / 16.0D;
/* 7040 */     var36 = par3 + 0.75D;
/* 7041 */     var38 = par3 + 1.0D;
/* 7042 */     var40 = par4 + var30 / 16.0D;
/* 7043 */     var42 = par4 + (var30 + 4.0D) / 16.0D;
/*      */     
/* 7045 */     if (var7 != 2 && var7 != 0) {
/*      */       
/* 7047 */       if (var7 == 1 || var7 == 3)
/*      */       {
/* 7049 */         var5.addVertexWithUV(var34, var36, var40, var20, var26);
/* 7050 */         var5.addVertexWithUV(var32, var36, var40, var22, var26);
/* 7051 */         var5.addVertexWithUV(var32, var38, var40, var22, var24);
/* 7052 */         var5.addVertexWithUV(var34, var38, var40, var20, var24);
/* 7053 */         var5.addVertexWithUV(var32, var36, var40, var22, var26);
/* 7054 */         var5.addVertexWithUV(var34, var36, var40, var20, var26);
/* 7055 */         var5.addVertexWithUV(var34, var38, var40, var20, var24);
/* 7056 */         var5.addVertexWithUV(var32, var38, var40, var22, var24);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 7061 */       var5.addVertexWithUV(var32, var36, var40, var22, var26);
/* 7062 */       var5.addVertexWithUV(var32, var36, var42, var20, var26);
/* 7063 */       var5.addVertexWithUV(var32, var38, var42, var20, var24);
/* 7064 */       var5.addVertexWithUV(var32, var38, var40, var22, var24);
/* 7065 */       var5.addVertexWithUV(var32, var36, var42, var20, var26);
/* 7066 */       var5.addVertexWithUV(var32, var36, var40, var22, var26);
/* 7067 */       var5.addVertexWithUV(var32, var38, var40, var22, var24);
/* 7068 */       var5.addVertexWithUV(var32, var38, var42, var20, var24);
/*      */     } 
/*      */     
/* 7071 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderBlockBeacon(BlockBeacon par1BlockBeacon, int par2, int par3, int par4) {
/* 7079 */     float var5 = 0.1875F;
/* 7080 */     setOverrideBlockTexture(getBlockIcon(Block.glass));
/* 7081 */     setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 7082 */     renderStandardBlock(par1BlockBeacon, par2, par3, par4);
/* 7083 */     this.renderAllFaces = true;
/* 7084 */     setOverrideBlockTexture(getBlockIcon(Block.obsidian));
/* 7085 */     setRenderBounds(0.125D, 0.0062500000931322575D, 0.125D, 0.875D, var5, 0.875D);
/* 7086 */     renderStandardBlock(par1BlockBeacon, par2, par3, par4);
/* 7087 */     setOverrideBlockTexture(getBlockIcon(Block.beacon));
/* 7088 */     setRenderBounds(0.1875D, var5, 0.1875D, 0.8125D, 0.875D, 0.8125D);
/* 7089 */     renderStandardBlock(par1BlockBeacon, par2, par3, par4);
/* 7090 */     this.renderAllFaces = false;
/* 7091 */     clearOverrideBlockTexture();
/* 7092 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockCactus(Block par1Block, int par2, int par3, int par4) {
/* 7100 */     int var5 = par1Block.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 7101 */     float var6 = (var5 >> 16 & 0xFF) / 255.0F;
/* 7102 */     float var7 = (var5 >> 8 & 0xFF) / 255.0F;
/* 7103 */     float var8 = (var5 & 0xFF) / 255.0F;
/*      */     
/* 7105 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/* 7107 */       float var9 = (var6 * 30.0F + var7 * 59.0F + var8 * 11.0F) / 100.0F;
/* 7108 */       float var10 = (var6 * 30.0F + var7 * 70.0F) / 100.0F;
/* 7109 */       float var11 = (var6 * 30.0F + var8 * 70.0F) / 100.0F;
/* 7110 */       var6 = var9;
/* 7111 */       var7 = var10;
/* 7112 */       var8 = var11;
/*      */     } 
/*      */     
/* 7115 */     return renderBlockCactusImpl(par1Block, par2, par3, par4, var6, var7, var8);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockCactusImpl(Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7) {
/* 7123 */     Tessellator var8 = Tessellator.instance;
/* 7124 */     boolean var9 = false;
/* 7125 */     float var10 = 0.5F;
/* 7126 */     float var11 = 1.0F;
/* 7127 */     float var12 = 0.8F;
/* 7128 */     float var13 = 0.6F;
/* 7129 */     float var14 = var10 * par5;
/* 7130 */     float var15 = var11 * par5;
/* 7131 */     float var16 = var12 * par5;
/* 7132 */     float var17 = var13 * par5;
/* 7133 */     float var18 = var10 * par6;
/* 7134 */     float var19 = var11 * par6;
/* 7135 */     float var20 = var12 * par6;
/* 7136 */     float var21 = var13 * par6;
/* 7137 */     float var22 = var10 * par7;
/* 7138 */     float var23 = var11 * par7;
/* 7139 */     float var24 = var12 * par7;
/* 7140 */     float var25 = var13 * par7;
/* 7141 */     float var26 = 0.0625F;
/* 7142 */     int var27 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4);
/*      */     
/* 7144 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 - 1, par4, 0)) {
/*      */       
/* 7146 */       var8.setBrightness((this.renderMinY > 0.0D) ? var27 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4));
/* 7147 */       var8.setColorOpaque_F(var14, var18, var22);
/* 7148 */       renderFaceYNeg(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 0));
/*      */     } 
/*      */     
/* 7151 */     if (this.renderAllFaces || par1Block.shouldSideBeRendered(this.blockAccess, par2, par3 + 1, par4, 1)) {
/*      */       
/* 7153 */       var8.setBrightness((this.renderMaxY < 1.0D) ? var27 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4));
/* 7154 */       var8.setColorOpaque_F(var15, var19, var23);
/* 7155 */       renderFaceYPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 1));
/*      */     } 
/*      */     
/* 7158 */     var8.setBrightness(var27);
/* 7159 */     var8.setColorOpaque_F(var16, var20, var24);
/* 7160 */     var8.addTranslation(0.0F, 0.0F, var26);
/* 7161 */     renderFaceZNeg(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 2));
/* 7162 */     var8.addTranslation(0.0F, 0.0F, -var26);
/* 7163 */     var8.addTranslation(0.0F, 0.0F, -var26);
/* 7164 */     renderFaceZPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 3));
/* 7165 */     var8.addTranslation(0.0F, 0.0F, var26);
/* 7166 */     var8.setColorOpaque_F(var17, var21, var25);
/* 7167 */     var8.addTranslation(var26, 0.0F, 0.0F);
/* 7168 */     renderFaceXNeg(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 4));
/* 7169 */     var8.addTranslation(-var26, 0.0F, 0.0F);
/* 7170 */     var8.addTranslation(-var26, 0.0F, 0.0F);
/* 7171 */     renderFaceXPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 5));
/* 7172 */     var8.addTranslation(var26, 0.0F, 0.0F);
/* 7173 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean renderBlockFence(BlockFence par1BlockFence, int par2, int par3, int par4) {
/* 7178 */     boolean var5 = false;
/* 7179 */     float var6 = 0.375F;
/* 7180 */     float var7 = 0.625F;
/* 7181 */     setRenderBounds(var6, 0.0D, var6, var7, 1.0D, var7);
/* 7182 */     renderStandardBlock(par1BlockFence, par2, par3, par4);
/* 7183 */     var5 = true;
/* 7184 */     boolean var8 = false;
/* 7185 */     boolean var9 = false;
/*      */     
/* 7187 */     if (par1BlockFence.canConnectFenceTo(this.blockAccess, par2 - 1, par3, par4) || par1BlockFence.canConnectFenceTo(this.blockAccess, par2 + 1, par3, par4))
/*      */     {
/* 7189 */       var8 = true;
/*      */     }
/*      */     
/* 7192 */     if (par1BlockFence.canConnectFenceTo(this.blockAccess, par2, par3, par4 - 1) || par1BlockFence.canConnectFenceTo(this.blockAccess, par2, par3, par4 + 1))
/*      */     {
/* 7194 */       var9 = true;
/*      */     }
/*      */     
/* 7197 */     boolean var10 = par1BlockFence.canConnectFenceTo(this.blockAccess, par2 - 1, par3, par4);
/* 7198 */     boolean var11 = par1BlockFence.canConnectFenceTo(this.blockAccess, par2 + 1, par3, par4);
/* 7199 */     boolean var12 = par1BlockFence.canConnectFenceTo(this.blockAccess, par2, par3, par4 - 1);
/* 7200 */     boolean var13 = par1BlockFence.canConnectFenceTo(this.blockAccess, par2, par3, par4 + 1);
/*      */     
/* 7202 */     if (!var8 && !var9)
/*      */     {
/* 7204 */       var8 = true;
/*      */     }
/*      */     
/* 7207 */     var6 = 0.4375F;
/* 7208 */     var7 = 0.5625F;
/* 7209 */     float var14 = 0.75F;
/* 7210 */     float var15 = 0.9375F;
/* 7211 */     float var16 = var10 ? 0.0F : var6;
/* 7212 */     float var17 = var11 ? 1.0F : var7;
/* 7213 */     float var18 = var12 ? 0.0F : var6;
/* 7214 */     float var19 = var13 ? 1.0F : var7;
/*      */     
/* 7216 */     if (var8) {
/*      */       
/* 7218 */       setRenderBounds(var16, var14, var6, var17, var15, var7);
/* 7219 */       renderStandardBlock(par1BlockFence, par2, par3, par4);
/* 7220 */       var5 = true;
/*      */     } 
/*      */     
/* 7223 */     if (var9) {
/*      */       
/* 7225 */       setRenderBounds(var6, var14, var18, var7, var15, var19);
/* 7226 */       renderStandardBlock(par1BlockFence, par2, par3, par4);
/* 7227 */       var5 = true;
/*      */     } 
/*      */     
/* 7230 */     var14 = 0.375F;
/* 7231 */     var15 = 0.5625F;
/*      */     
/* 7233 */     if (var8) {
/*      */       
/* 7235 */       setRenderBounds(var16, var14, var6, var17, var15, var7);
/* 7236 */       renderStandardBlock(par1BlockFence, par2, par3, par4);
/* 7237 */       var5 = true;
/*      */     } 
/*      */     
/* 7240 */     if (var9) {
/*      */       
/* 7242 */       setRenderBounds(var6, var14, var18, var7, var15, var19);
/* 7243 */       renderStandardBlock(par1BlockFence, par2, par3, par4);
/* 7244 */       var5 = true;
/*      */     } 
/*      */     
/* 7247 */     par1BlockFence.setBlockBoundsBasedOnStateAndNeighbors(this.blockAccess, par2, par3, par4);
/* 7248 */     return var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockWall(BlockWall par1BlockWall, int par2, int par3, int par4) {
/* 7256 */     boolean var5 = par1BlockWall.canConnectWallTo(this.blockAccess, par2 - 1, par3, par4);
/* 7257 */     boolean var6 = par1BlockWall.canConnectWallTo(this.blockAccess, par2 + 1, par3, par4);
/* 7258 */     boolean var7 = par1BlockWall.canConnectWallTo(this.blockAccess, par2, par3, par4 - 1);
/* 7259 */     boolean var8 = par1BlockWall.canConnectWallTo(this.blockAccess, par2, par3, par4 + 1);
/* 7260 */     boolean var9 = (var7 && var8 && !var5 && !var6);
/* 7261 */     boolean var10 = (!var7 && !var8 && var5 && var6);
/* 7262 */     boolean var11 = this.blockAccess.isAirBlock(par2, par3 + 1, par4);
/*      */     
/* 7264 */     if ((var9 || var10) && var11) {
/*      */       
/* 7266 */       if (var9)
/*      */       {
/* 7268 */         setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 1.0D);
/* 7269 */         renderStandardBlock(par1BlockWall, par2, par3, par4);
/*      */       }
/*      */       else
/*      */       {
/* 7273 */         setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
/* 7274 */         renderStandardBlock(par1BlockWall, par2, par3, par4);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 7279 */       setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 7280 */       renderStandardBlock(par1BlockWall, par2, par3, par4);
/*      */       
/* 7282 */       if (var5) {
/*      */         
/* 7284 */         setRenderBounds(0.0D, 0.0D, 0.3125D, 0.25D, 0.8125D, 0.6875D);
/* 7285 */         renderStandardBlock(par1BlockWall, par2, par3, par4);
/*      */       } 
/*      */       
/* 7288 */       if (var6) {
/*      */         
/* 7290 */         setRenderBounds(0.75D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
/* 7291 */         renderStandardBlock(par1BlockWall, par2, par3, par4);
/*      */       } 
/*      */       
/* 7294 */       if (var7) {
/*      */         
/* 7296 */         setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 0.25D);
/* 7297 */         renderStandardBlock(par1BlockWall, par2, par3, par4);
/*      */       } 
/*      */       
/* 7300 */       if (var8) {
/*      */         
/* 7302 */         setRenderBounds(0.3125D, 0.0D, 0.75D, 0.6875D, 0.8125D, 1.0D);
/* 7303 */         renderStandardBlock(par1BlockWall, par2, par3, par4);
/*      */       } 
/*      */     } 
/*      */     
/* 7307 */     par1BlockWall.setBlockBoundsBasedOnStateAndNeighbors(this.blockAccess, par2, par3, par4);
/* 7308 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean renderBlockDragonEgg(BlockDragonEgg par1BlockDragonEgg, int par2, int par3, int par4) {
/* 7313 */     boolean var5 = false;
/* 7314 */     int var6 = 0;
/*      */     
/* 7316 */     for (int var7 = 0; var7 < 8; var7++) {
/*      */       
/* 7318 */       byte var8 = 0;
/* 7319 */       byte var9 = 1;
/*      */       
/* 7321 */       if (var7 == 0)
/*      */       {
/* 7323 */         var8 = 2;
/*      */       }
/*      */       
/* 7326 */       if (var7 == 1)
/*      */       {
/* 7328 */         var8 = 3;
/*      */       }
/*      */       
/* 7331 */       if (var7 == 2)
/*      */       {
/* 7333 */         var8 = 4;
/*      */       }
/*      */       
/* 7336 */       if (var7 == 3) {
/*      */         
/* 7338 */         var8 = 5;
/* 7339 */         var9 = 2;
/*      */       } 
/*      */       
/* 7342 */       if (var7 == 4) {
/*      */         
/* 7344 */         var8 = 6;
/* 7345 */         var9 = 3;
/*      */       } 
/*      */       
/* 7348 */       if (var7 == 5) {
/*      */         
/* 7350 */         var8 = 7;
/* 7351 */         var9 = 5;
/*      */       } 
/*      */       
/* 7354 */       if (var7 == 6) {
/*      */         
/* 7356 */         var8 = 6;
/* 7357 */         var9 = 2;
/*      */       } 
/*      */       
/* 7360 */       if (var7 == 7)
/*      */       {
/* 7362 */         var8 = 3;
/*      */       }
/*      */       
/* 7365 */       float var10 = var8 / 16.0F;
/* 7366 */       float var11 = 1.0F - var6 / 16.0F;
/* 7367 */       float var12 = 1.0F - (var6 + var9) / 16.0F;
/* 7368 */       var6 += var9;
/* 7369 */       setRenderBounds((0.5F - var10), var12, (0.5F - var10), (0.5F + var10), var11, (0.5F + var10));
/* 7370 */       renderStandardBlock(par1BlockDragonEgg, par2, par3, par4);
/*      */     } 
/*      */     
/* 7373 */     var5 = true;
/* 7374 */     setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 7375 */     return var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockFenceGate(BlockFenceGate par1BlockFenceGate, int par2, int par3, int par4) {
/* 7383 */     boolean var5 = true;
/* 7384 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/* 7385 */     boolean var7 = BlockFenceGate.isFenceGateOpen(var6);
/*      */     
/* 7387 */     int var8 = BlockFenceGate.j(var6);
/* 7388 */     float var9 = 0.375F;
/* 7389 */     float var10 = 0.5625F;
/* 7390 */     float var11 = 0.75F;
/* 7391 */     float var12 = 0.9375F;
/* 7392 */     float var13 = 0.3125F;
/* 7393 */     float var14 = 1.0F;
/*      */     
/* 7395 */     if (((var8 == 2 || var8 == 0) && this.blockAccess.getBlockId(par2 - 1, par3, par4) == Block.cobblestoneWall.blockID && this.blockAccess.getBlockId(par2 + 1, par3, par4) == Block.cobblestoneWall.blockID) || ((var8 == 3 || var8 == 1) && this.blockAccess.getBlockId(par2, par3, par4 - 1) == Block.cobblestoneWall.blockID && this.blockAccess.getBlockId(par2, par3, par4 + 1) == Block.cobblestoneWall.blockID)) {
/*      */       
/* 7397 */       var9 -= 0.1875F;
/* 7398 */       var10 -= 0.1875F;
/* 7399 */       var11 -= 0.1875F;
/* 7400 */       var12 -= 0.1875F;
/* 7401 */       var13 -= 0.1875F;
/* 7402 */       var14 -= 0.1875F;
/*      */     } 
/*      */     
/* 7405 */     this.renderAllFaces = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7411 */     if (var8 != 3 && var8 != 1) {
/*      */       
/* 7413 */       float var15 = 0.0F;
/* 7414 */       float var16 = 0.125F;
/* 7415 */       float var17 = 0.4375F;
/* 7416 */       float var18 = 0.5625F;
/* 7417 */       setRenderBounds(var15, var13, var17, var16, var14, var18);
/* 7418 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7419 */       var15 = 0.875F;
/* 7420 */       var16 = 1.0F;
/* 7421 */       setRenderBounds(var15, var13, var17, var16, var14, var18);
/* 7422 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/*      */     }
/*      */     else {
/*      */       
/* 7426 */       this.uvRotateTop = 1;
/* 7427 */       float var15 = 0.4375F;
/* 7428 */       float var16 = 0.5625F;
/* 7429 */       float var17 = 0.0F;
/* 7430 */       float var18 = 0.125F;
/* 7431 */       setRenderBounds(var15, var13, var17, var16, var14, var18);
/* 7432 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7433 */       var17 = 0.875F;
/* 7434 */       var18 = 1.0F;
/* 7435 */       setRenderBounds(var15, var13, var17, var16, var14, var18);
/* 7436 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7437 */       this.uvRotateTop = 0;
/*      */     } 
/*      */     
/* 7440 */     if (var7) {
/*      */       
/* 7442 */       if (var8 == 2 || var8 == 0)
/*      */       {
/* 7444 */         this.uvRotateTop = 1;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 7451 */       if (var8 == 3)
/*      */       {
/* 7453 */         float f1 = 0.0F;
/* 7454 */         float f3 = 0.125F;
/* 7455 */         float f2 = 0.875F;
/* 7456 */         float f4 = 1.0F;
/* 7457 */         float var19 = 0.5625F;
/* 7458 */         float var20 = 0.8125F;
/* 7459 */         float var21 = 0.9375F;
/* 7460 */         setRenderBounds(0.8125D, var9, 0.0D, 0.9375D, var12, 0.125D);
/* 7461 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7462 */         setRenderBounds(0.8125D, var9, 0.875D, 0.9375D, var12, 1.0D);
/* 7463 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7464 */         setRenderBounds(0.5625D, var9, 0.0D, 0.8125D, var10, 0.125D);
/* 7465 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7466 */         setRenderBounds(0.5625D, var9, 0.875D, 0.8125D, var10, 1.0D);
/* 7467 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7468 */         setRenderBounds(0.5625D, var11, 0.0D, 0.8125D, var12, 0.125D);
/* 7469 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7470 */         setRenderBounds(0.5625D, var11, 0.875D, 0.8125D, var12, 1.0D);
/* 7471 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/*      */       }
/* 7473 */       else if (var8 == 1)
/*      */       {
/* 7475 */         float f1 = 0.0F;
/* 7476 */         float f3 = 0.125F;
/* 7477 */         float f2 = 0.875F;
/* 7478 */         float f4 = 1.0F;
/* 7479 */         float var19 = 0.0625F;
/* 7480 */         float var20 = 0.1875F;
/* 7481 */         float var21 = 0.4375F;
/* 7482 */         setRenderBounds(0.0625D, var9, 0.0D, 0.1875D, var12, 0.125D);
/* 7483 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7484 */         setRenderBounds(0.0625D, var9, 0.875D, 0.1875D, var12, 1.0D);
/* 7485 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7486 */         setRenderBounds(0.1875D, var9, 0.0D, 0.4375D, var10, 0.125D);
/* 7487 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7488 */         setRenderBounds(0.1875D, var9, 0.875D, 0.4375D, var10, 1.0D);
/* 7489 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7490 */         setRenderBounds(0.1875D, var11, 0.0D, 0.4375D, var12, 0.125D);
/* 7491 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7492 */         setRenderBounds(0.1875D, var11, 0.875D, 0.4375D, var12, 1.0D);
/* 7493 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/*      */       }
/* 7495 */       else if (var8 == 0)
/*      */       {
/* 7497 */         float f1 = 0.0F;
/* 7498 */         float f3 = 0.125F;
/* 7499 */         float f2 = 0.875F;
/* 7500 */         float f4 = 1.0F;
/* 7501 */         float var19 = 0.5625F;
/* 7502 */         float var20 = 0.8125F;
/* 7503 */         float var21 = 0.9375F;
/* 7504 */         setRenderBounds(0.0D, var9, 0.8125D, 0.125D, var12, 0.9375D);
/* 7505 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7506 */         setRenderBounds(0.875D, var9, 0.8125D, 1.0D, var12, 0.9375D);
/* 7507 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7508 */         setRenderBounds(0.0D, var9, 0.5625D, 0.125D, var10, 0.8125D);
/* 7509 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7510 */         setRenderBounds(0.875D, var9, 0.5625D, 1.0D, var10, 0.8125D);
/* 7511 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7512 */         setRenderBounds(0.0D, var11, 0.5625D, 0.125D, var12, 0.8125D);
/* 7513 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7514 */         setRenderBounds(0.875D, var11, 0.5625D, 1.0D, var12, 0.8125D);
/* 7515 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/*      */       }
/* 7517 */       else if (var8 == 2)
/*      */       {
/* 7519 */         float f1 = 0.0F;
/* 7520 */         float f3 = 0.125F;
/* 7521 */         float f2 = 0.875F;
/* 7522 */         float f4 = 1.0F;
/* 7523 */         float var19 = 0.0625F;
/* 7524 */         float var20 = 0.1875F;
/* 7525 */         float var21 = 0.4375F;
/* 7526 */         setRenderBounds(0.0D, var9, 0.0625D, 0.125D, var12, 0.1875D);
/* 7527 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7528 */         setRenderBounds(0.875D, var9, 0.0625D, 1.0D, var12, 0.1875D);
/* 7529 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7530 */         setRenderBounds(0.0D, var9, 0.1875D, 0.125D, var10, 0.4375D);
/* 7531 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7532 */         setRenderBounds(0.875D, var9, 0.1875D, 1.0D, var10, 0.4375D);
/* 7533 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7534 */         setRenderBounds(0.0D, var11, 0.1875D, 0.125D, var12, 0.4375D);
/* 7535 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7536 */         setRenderBounds(0.875D, var11, 0.1875D, 1.0D, var12, 0.4375D);
/* 7537 */         renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/*      */       }
/*      */     
/* 7540 */     } else if (var8 != 3 && var8 != 1) {
/*      */       
/* 7542 */       float f1 = 0.375F;
/* 7543 */       float f3 = 0.5F;
/* 7544 */       float f2 = 0.4375F;
/* 7545 */       float f4 = 0.5625F;
/* 7546 */       setRenderBounds(f1, var9, f2, f3, var12, f4);
/* 7547 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7548 */       f1 = 0.5F;
/* 7549 */       f3 = 0.625F;
/* 7550 */       setRenderBounds(f1, var9, f2, f3, var12, f4);
/* 7551 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7552 */       f1 = 0.625F;
/* 7553 */       f3 = 0.875F;
/* 7554 */       setRenderBounds(f1, var9, f2, f3, var10, f4);
/* 7555 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7556 */       setRenderBounds(f1, var11, f2, f3, var12, f4);
/* 7557 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7558 */       f1 = 0.125F;
/* 7559 */       f3 = 0.375F;
/* 7560 */       setRenderBounds(f1, var9, f2, f3, var10, f4);
/* 7561 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7562 */       setRenderBounds(f1, var11, f2, f3, var12, f4);
/* 7563 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/*      */     }
/*      */     else {
/*      */       
/* 7567 */       this.uvRotateTop = 1;
/* 7568 */       float f1 = 0.4375F;
/* 7569 */       float f3 = 0.5625F;
/* 7570 */       float f2 = 0.375F;
/* 7571 */       float f4 = 0.5F;
/* 7572 */       setRenderBounds(f1, var9, f2, f3, var12, f4);
/* 7573 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7574 */       f2 = 0.5F;
/* 7575 */       f4 = 0.625F;
/* 7576 */       setRenderBounds(f1, var9, f2, f3, var12, f4);
/* 7577 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7578 */       f2 = 0.625F;
/* 7579 */       f4 = 0.875F;
/* 7580 */       setRenderBounds(f1, var9, f2, f3, var10, f4);
/* 7581 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7582 */       setRenderBounds(f1, var11, f2, f3, var12, f4);
/* 7583 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7584 */       f2 = 0.125F;
/* 7585 */       f4 = 0.375F;
/* 7586 */       setRenderBounds(f1, var9, f2, f3, var10, f4);
/* 7587 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/* 7588 */       setRenderBounds(f1, var11, f2, f3, var12, f4);
/* 7589 */       renderStandardBlock(par1BlockFenceGate, par2, par3, par4);
/*      */     } 
/*      */     
/* 7592 */     this.renderAllFaces = false;
/* 7593 */     this.uvRotateTop = 0;
/* 7594 */     setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 7595 */     return var5;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean renderBlockHopper(BlockHopper par1BlockHopper, int par2, int par3, int par4) {
/* 7600 */     Tessellator var5 = Tessellator.instance;
/* 7601 */     var5.setBrightness(par1BlockHopper.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 7602 */     float var6 = 1.0F;
/* 7603 */     int var7 = par1BlockHopper.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 7604 */     float var8 = (var7 >> 16 & 0xFF) / 255.0F;
/* 7605 */     float var9 = (var7 >> 8 & 0xFF) / 255.0F;
/* 7606 */     float var10 = (var7 & 0xFF) / 255.0F;
/*      */     
/* 7608 */     if (EntityRenderer.anaglyphEnable) {
/*      */       
/* 7610 */       float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
/* 7611 */       float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
/* 7612 */       float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
/* 7613 */       var8 = var11;
/* 7614 */       var9 = var12;
/* 7615 */       var10 = var13;
/*      */     } 
/*      */     
/* 7618 */     var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
/* 7619 */     return renderBlockHopperMetadata(par1BlockHopper, par2, par3, par4, this.blockAccess.getBlockMetadata(par2, par3, par4), false);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean renderBlockHopperMetadata(BlockHopper par1BlockHopper, int par2, int par3, int par4, int par5, boolean par6) {
/* 7624 */     Tessellator var7 = Tessellator.instance;
/* 7625 */     int var8 = BlockHopper.getDirectionFromMetadata(par5);
/* 7626 */     double var9 = 0.625D;
/* 7627 */     setRenderBounds(0.0D, var9, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */     
/* 7629 */     if (par6) {
/*      */       
/* 7631 */       var7.startDrawingQuads();
/* 7632 */       var7.setNormal(0.0F, -1.0F, 0.0F);
/* 7633 */       renderFaceYNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockHopper, 0, par5));
/* 7634 */       var7.draw();
/* 7635 */       var7.startDrawingQuads();
/* 7636 */       var7.setNormal(0.0F, 1.0F, 0.0F);
/* 7637 */       renderFaceYPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockHopper, 1, par5));
/* 7638 */       var7.draw();
/* 7639 */       var7.startDrawingQuads();
/* 7640 */       var7.setNormal(0.0F, 0.0F, -1.0F);
/* 7641 */       renderFaceZNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockHopper, 2, par5));
/* 7642 */       var7.draw();
/* 7643 */       var7.startDrawingQuads();
/* 7644 */       var7.setNormal(0.0F, 0.0F, 1.0F);
/* 7645 */       renderFaceZPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockHopper, 3, par5));
/* 7646 */       var7.draw();
/* 7647 */       var7.startDrawingQuads();
/* 7648 */       var7.setNormal(-1.0F, 0.0F, 0.0F);
/* 7649 */       renderFaceXNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockHopper, 4, par5));
/* 7650 */       var7.draw();
/* 7651 */       var7.startDrawingQuads();
/* 7652 */       var7.setNormal(1.0F, 0.0F, 0.0F);
/* 7653 */       renderFaceXPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1BlockHopper, 5, par5));
/* 7654 */       var7.draw();
/*      */     }
/*      */     else {
/*      */       
/* 7658 */       renderStandardBlock(par1BlockHopper, par2, par3, par4);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 7663 */     if (!par6) {
/*      */       
/* 7665 */       var7.setBrightness(par1BlockHopper.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
/* 7666 */       float var11 = 1.0F;
/* 7667 */       int var12 = par1BlockHopper.colorMultiplier(this.blockAccess, par2, par3, par4);
/* 7668 */       float f1 = (var12 >> 16 & 0xFF) / 255.0F;
/* 7669 */       float var14 = (var12 >> 8 & 0xFF) / 255.0F;
/* 7670 */       float var15 = (var12 & 0xFF) / 255.0F;
/*      */       
/* 7672 */       if (EntityRenderer.anaglyphEnable) {
/*      */         
/* 7674 */         float var16 = (f1 * 30.0F + var14 * 59.0F + var15 * 11.0F) / 100.0F;
/* 7675 */         float var17 = (f1 * 30.0F + var14 * 70.0F) / 100.0F;
/* 7676 */         float var18 = (f1 * 30.0F + var15 * 70.0F) / 100.0F;
/* 7677 */         f1 = var16;
/* 7678 */         var14 = var17;
/* 7679 */         var15 = var18;
/*      */       } 
/*      */       
/* 7682 */       var7.setColorOpaque_F(var11 * f1, var11 * var14, var11 * var15);
/*      */     } 
/*      */     
/* 7685 */     Icon var24 = BlockHopper.getHopperIcon("hopper_outside");
/* 7686 */     Icon var25 = BlockHopper.getHopperIcon("hopper_inside");
/* 7687 */     float var13 = 0.125F;
/*      */     
/* 7689 */     if (par6) {
/*      */       
/* 7691 */       var7.startDrawingQuads();
/* 7692 */       var7.setNormal(1.0F, 0.0F, 0.0F);
/* 7693 */       renderFaceXPos(par1BlockHopper, (-1.0F + var13), 0.0D, 0.0D, var24);
/* 7694 */       var7.draw();
/* 7695 */       var7.startDrawingQuads();
/* 7696 */       var7.setNormal(-1.0F, 0.0F, 0.0F);
/* 7697 */       renderFaceXNeg(par1BlockHopper, (1.0F - var13), 0.0D, 0.0D, var24);
/* 7698 */       var7.draw();
/* 7699 */       var7.startDrawingQuads();
/* 7700 */       var7.setNormal(0.0F, 0.0F, 1.0F);
/* 7701 */       renderFaceZPos(par1BlockHopper, 0.0D, 0.0D, (-1.0F + var13), var24);
/* 7702 */       var7.draw();
/* 7703 */       var7.startDrawingQuads();
/* 7704 */       var7.setNormal(0.0F, 0.0F, -1.0F);
/* 7705 */       renderFaceZNeg(par1BlockHopper, 0.0D, 0.0D, (1.0F - var13), var24);
/* 7706 */       var7.draw();
/* 7707 */       var7.startDrawingQuads();
/* 7708 */       var7.setNormal(0.0F, 1.0F, 0.0F);
/* 7709 */       renderFaceYPos(par1BlockHopper, 0.0D, -1.0D + var9, 0.0D, var25);
/* 7710 */       var7.draw();
/*      */     }
/*      */     else {
/*      */       
/* 7714 */       renderFaceXPos(par1BlockHopper, (par2 - 1.0F + var13), par3, par4, var24);
/* 7715 */       renderFaceXNeg(par1BlockHopper, (par2 + 1.0F - var13), par3, par4, var24);
/* 7716 */       renderFaceZPos(par1BlockHopper, par2, par3, (par4 - 1.0F + var13), var24);
/* 7717 */       renderFaceZNeg(par1BlockHopper, par2, par3, (par4 + 1.0F - var13), var24);
/* 7718 */       renderFaceYPos(par1BlockHopper, par2, (par3 - 1.0F) + var9, par4, var25);
/*      */     } 
/*      */     
/* 7721 */     setOverrideBlockTexture(var24);
/* 7722 */     double var26 = 0.25D;
/* 7723 */     double var27 = 0.25D;
/* 7724 */     setRenderBounds(var26, var27, var26, 1.0D - var26, var9 - 0.002D, 1.0D - var26);
/*      */     
/* 7726 */     if (par6) {
/*      */       
/* 7728 */       var7.startDrawingQuads();
/* 7729 */       var7.setNormal(1.0F, 0.0F, 0.0F);
/* 7730 */       renderFaceXPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
/* 7731 */       var7.draw();
/* 7732 */       var7.startDrawingQuads();
/* 7733 */       var7.setNormal(-1.0F, 0.0F, 0.0F);
/* 7734 */       renderFaceXNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
/* 7735 */       var7.draw();
/* 7736 */       var7.startDrawingQuads();
/* 7737 */       var7.setNormal(0.0F, 0.0F, 1.0F);
/* 7738 */       renderFaceZPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
/* 7739 */       var7.draw();
/* 7740 */       var7.startDrawingQuads();
/* 7741 */       var7.setNormal(0.0F, 0.0F, -1.0F);
/* 7742 */       renderFaceZNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
/* 7743 */       var7.draw();
/* 7744 */       var7.startDrawingQuads();
/* 7745 */       var7.setNormal(0.0F, 1.0F, 0.0F);
/* 7746 */       renderFaceYPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
/* 7747 */       var7.draw();
/* 7748 */       var7.startDrawingQuads();
/* 7749 */       var7.setNormal(0.0F, -1.0F, 0.0F);
/* 7750 */       renderFaceYNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, var24);
/* 7751 */       var7.draw();
/*      */     }
/*      */     else {
/*      */       
/* 7755 */       renderStandardBlock(par1BlockHopper, par2, par3, par4);
/*      */     } 
/*      */     
/* 7758 */     if (!par6) {
/*      */       
/* 7760 */       double var20 = 0.375D;
/* 7761 */       double var22 = 0.25D;
/* 7762 */       setOverrideBlockTexture(var24);
/*      */       
/* 7764 */       if (var8 == 0) {
/*      */         
/* 7766 */         setRenderBounds(var20, 0.0D, var20, 1.0D - var20, 0.25D, 1.0D - var20);
/* 7767 */         renderStandardBlock(par1BlockHopper, par2, par3, par4);
/*      */       } 
/*      */       
/* 7770 */       if (var8 == 2) {
/*      */         
/* 7772 */         setRenderBounds(var20, var27, 0.0D, 1.0D - var20, var27 + var22, var26);
/* 7773 */         renderStandardBlock(par1BlockHopper, par2, par3, par4);
/*      */       } 
/*      */       
/* 7776 */       if (var8 == 3) {
/*      */         
/* 7778 */         setRenderBounds(var20, var27, 1.0D - var26, 1.0D - var20, var27 + var22, 1.0D);
/* 7779 */         renderStandardBlock(par1BlockHopper, par2, par3, par4);
/*      */       } 
/*      */       
/* 7782 */       if (var8 == 4) {
/*      */         
/* 7784 */         setRenderBounds(0.0D, var27, var20, var26, var27 + var22, 1.0D - var20);
/* 7785 */         renderStandardBlock(par1BlockHopper, par2, par3, par4);
/*      */       } 
/*      */       
/* 7788 */       if (var8 == 5) {
/*      */         
/* 7790 */         setRenderBounds(1.0D - var26, var27, var20, 1.0D, var27 + var22, 1.0D - var20);
/* 7791 */         renderStandardBlock(par1BlockHopper, par2, par3, par4);
/*      */       } 
/*      */     } 
/*      */     
/* 7795 */     clearOverrideBlockTexture();
/* 7796 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockStairs(BlockStairs par1BlockStairs, int par2, int par3, int par4) {
/* 7804 */     par1BlockStairs.func_82541_d(this.blockAccess, par2, par3, par4);
/*      */     
/* 7806 */     setRenderBoundsForNonStandardFormBlock(par1BlockStairs);
/* 7807 */     renderStandardBlock(par1BlockStairs, par2, par3, par4);
/* 7808 */     boolean var5 = par1BlockStairs.func_82542_g(this.blockAccess, par2, par3, par4);
/*      */     
/* 7810 */     setRenderBoundsForNonStandardFormBlock(par1BlockStairs);
/* 7811 */     renderStandardBlock(par1BlockStairs, par2, par3, par4);
/*      */     
/* 7813 */     if (var5 && par1BlockStairs.func_82544_h(this.blockAccess, par2, par3, par4)) {
/*      */ 
/*      */       
/* 7816 */       setRenderBoundsForNonStandardFormBlock(par1BlockStairs);
/* 7817 */       renderStandardBlock(par1BlockStairs, par2, par3, par4);
/*      */     } 
/*      */     
/* 7820 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean renderBlockDoor(Block par1Block, int par2, int par3, int par4) {
/* 7828 */     Tessellator var5 = Tessellator.instance;
/* 7829 */     int var6 = this.blockAccess.getBlockMetadata(par2, par3, par4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7843 */     boolean var7 = false;
/* 7844 */     float var8 = 0.5F;
/* 7845 */     float var9 = 1.0F;
/* 7846 */     float var10 = 0.8F;
/* 7847 */     float var11 = 0.6F;
/* 7848 */     int var12 = par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4);
/* 7849 */     var5.setBrightness((this.renderMinY > 0.0D) ? var12 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 - 1, par4));
/* 7850 */     var5.setColorOpaque_F(var8, var8, var8);
/* 7851 */     renderFaceYNeg(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 0));
/* 7852 */     var7 = true;
/* 7853 */     var5.setBrightness((this.renderMaxY < 1.0D) ? var12 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3 + 1, par4));
/* 7854 */     var5.setColorOpaque_F(var9, var9, var9);
/* 7855 */     renderFaceYPos(par1Block, par2, par3, par4, getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 1));
/* 7856 */     var7 = true;
/* 7857 */     var5.setBrightness((this.renderMinZ > 0.0D) ? var12 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 - 1));
/* 7858 */     var5.setColorOpaque_F(var10, var10, var10);
/* 7859 */     Icon var14 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 2);
/* 7860 */     renderFaceZNeg(par1Block, par2, par3, par4, var14);
/* 7861 */     var7 = true;
/* 7862 */     this.flipTexture = false;
/* 7863 */     var5.setBrightness((this.renderMaxZ < 1.0D) ? var12 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4 + 1));
/* 7864 */     var5.setColorOpaque_F(var10, var10, var10);
/* 7865 */     var14 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 3);
/* 7866 */     renderFaceZPos(par1Block, par2, par3, par4, var14);
/* 7867 */     var7 = true;
/* 7868 */     this.flipTexture = false;
/* 7869 */     var5.setBrightness((this.renderMinX > 0.0D) ? var12 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 - 1, par3, par4));
/* 7870 */     var5.setColorOpaque_F(var11, var11, var11);
/* 7871 */     var14 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 4);
/* 7872 */     renderFaceXNeg(par1Block, par2, par3, par4, var14);
/* 7873 */     var7 = true;
/* 7874 */     this.flipTexture = false;
/* 7875 */     var5.setBrightness((this.renderMaxX < 1.0D) ? var12 : par1Block.getMixedBrightnessForBlock(this.blockAccess, par2 + 1, par3, par4));
/* 7876 */     var5.setColorOpaque_F(var11, var11, var11);
/* 7877 */     var14 = getBlockIcon(par1Block, this.blockAccess, par2, par3, par4, 5);
/* 7878 */     renderFaceXPos(par1Block, par2, par3, par4, var14);
/* 7879 */     var7 = true;
/* 7880 */     this.flipTexture = false;
/* 7881 */     return var7;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderFaceYNeg(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
/* 7889 */     Tessellator var9 = Tessellator.instance;
/*      */ 
/*      */     
/* 7892 */     if (this.overrideBlockTexture != null)
/*      */     {
/* 7894 */       par8Icon = this.overrideBlockTexture;
/*      */     }
/*      */     
/* 7897 */     double var10 = par8Icon.getInterpolatedU(this.renderMinX * 16.0D);
/* 7898 */     double var12 = par8Icon.getInterpolatedU(this.renderMaxX * 16.0D);
/* 7899 */     double var14 = par8Icon.getInterpolatedV(this.renderMinZ * 16.0D);
/* 7900 */     double var16 = par8Icon.getInterpolatedV(this.renderMaxZ * 16.0D);
/*      */     
/* 7902 */     if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
/*      */       
/* 7904 */       var10 = par8Icon.getMinU();
/* 7905 */       var12 = par8Icon.getMaxU();
/*      */     } 
/*      */     
/* 7908 */     if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
/*      */       
/* 7910 */       var14 = par8Icon.getMinV();
/* 7911 */       var16 = par8Icon.getMaxV();
/*      */     } 
/*      */     
/* 7914 */     double var18 = var12;
/* 7915 */     double var20 = var10;
/* 7916 */     double var22 = var14;
/* 7917 */     double var24 = var16;
/*      */     
/* 7919 */     if (this.uvRotateBottom == 2) {
/*      */       
/* 7921 */       var10 = par8Icon.getInterpolatedU(this.renderMinZ * 16.0D);
/* 7922 */       var14 = par8Icon.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
/* 7923 */       var12 = par8Icon.getInterpolatedU(this.renderMaxZ * 16.0D);
/* 7924 */       var16 = par8Icon.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
/* 7925 */       var22 = var14;
/* 7926 */       var24 = var16;
/* 7927 */       var18 = var10;
/* 7928 */       var20 = var12;
/* 7929 */       var14 = var16;
/* 7930 */       var16 = var22;
/*      */     }
/* 7932 */     else if (this.uvRotateBottom == 1) {
/*      */       
/* 7934 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
/* 7935 */       var14 = par8Icon.getInterpolatedV(this.renderMinX * 16.0D);
/* 7936 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
/* 7937 */       var16 = par8Icon.getInterpolatedV(this.renderMaxX * 16.0D);
/* 7938 */       var18 = var12;
/* 7939 */       var20 = var10;
/* 7940 */       var10 = var12;
/* 7941 */       var12 = var20;
/* 7942 */       var22 = var16;
/* 7943 */       var24 = var14;
/*      */     }
/* 7945 */     else if (this.uvRotateBottom == 3) {
/*      */       
/* 7947 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
/* 7948 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
/* 7949 */       var14 = par8Icon.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
/* 7950 */       var16 = par8Icon.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
/* 7951 */       var18 = var12;
/* 7952 */       var20 = var10;
/* 7953 */       var22 = var14;
/* 7954 */       var24 = var16;
/*      */     } 
/*      */     
/* 7957 */     double var26 = par2 + this.renderMinX;
/* 7958 */     double var28 = par2 + this.renderMaxX;
/* 7959 */     double var30 = par4 + this.renderMinY;
/* 7960 */     double var32 = par6 + this.renderMinZ;
/* 7961 */     double var34 = par6 + this.renderMaxZ;
/*      */     
/* 7963 */     if (this.enableAO) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 7980 */       if (RenderingScheme.current == 0)
/*      */       {
/* 7982 */         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
/* 7983 */         var9.setBrightness(this.brightnessTopLeft);
/* 7984 */         var9.addVertexWithUV(var26, var30, var34, var20, var24);
/* 7985 */         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
/* 7986 */         var9.setBrightness(this.brightnessBottomLeft);
/* 7987 */         var9.addVertexWithUV(var26, var30, var32, var10, var14);
/* 7988 */         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
/* 7989 */         var9.setBrightness(this.brightnessBottomRight);
/* 7990 */         var9.addVertexWithUV(var28, var30, var32, var18, var22);
/* 7991 */         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
/* 7992 */         var9.setBrightness(this.brightnessTopRight);
/* 7993 */         var9.addVertexWithUV(var28, var30, var34, var12, var16);
/*      */       }
/*      */       else
/*      */       {
/* 7997 */         this.x[0] = var26;
/* 7998 */         this.y[0] = var30;
/* 7999 */         this.z[0] = var34;
/* 8000 */         this.u[0] = var20;
/* 8001 */         this.v[0] = var24;
/* 8002 */         this.r[0] = this.colorRedTopLeft;
/* 8003 */         this.g[0] = this.colorGreenTopLeft;
/* 8004 */         this.b[0] = this.colorBlueTopLeft;
/* 8005 */         this.brightness[0] = this.brightnessTopLeft;
/*      */         
/* 8007 */         this.x[1] = var26;
/* 8008 */         this.y[1] = var30;
/* 8009 */         this.z[1] = var32;
/* 8010 */         this.u[1] = var10;
/* 8011 */         this.v[1] = var14;
/* 8012 */         this.r[1] = this.colorRedBottomLeft;
/* 8013 */         this.g[1] = this.colorGreenBottomLeft;
/* 8014 */         this.b[1] = this.colorBlueBottomLeft;
/* 8015 */         this.brightness[1] = this.brightnessBottomLeft;
/*      */         
/* 8017 */         this.x[2] = var28;
/* 8018 */         this.y[2] = var30;
/* 8019 */         this.z[2] = var32;
/* 8020 */         this.u[2] = var18;
/* 8021 */         this.v[2] = var22;
/* 8022 */         this.r[2] = this.colorRedBottomRight;
/* 8023 */         this.g[2] = this.colorGreenBottomRight;
/* 8024 */         this.b[2] = this.colorBlueBottomRight;
/* 8025 */         this.brightness[2] = this.brightnessBottomRight;
/*      */         
/* 8027 */         this.x[3] = var28;
/* 8028 */         this.y[3] = var30;
/* 8029 */         this.z[3] = var34;
/* 8030 */         this.u[3] = var12;
/* 8031 */         this.v[3] = var16;
/* 8032 */         this.r[3] = this.colorRedTopRight;
/* 8033 */         this.g[3] = this.colorGreenTopRight;
/* 8034 */         this.b[3] = this.colorBlueTopRight;
/* 8035 */         this.brightness[3] = this.brightnessTopRight;
/*      */         
/* 8037 */         var9.add4VerticesWithUVandAO(this.x, this.y, this.z, this.u, this.v, this.r, this.g, this.b, this.brightness);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 8051 */     else if (RenderingScheme.current == 0) {
/*      */       
/* 8053 */       var9.addVertexWithUV(var26, var30, var34, var20, var24);
/* 8054 */       var9.addVertexWithUV(var26, var30, var32, var10, var14);
/* 8055 */       var9.addVertexWithUV(var28, var30, var32, var18, var22);
/* 8056 */       var9.addVertexWithUV(var28, var30, var34, var12, var16);
/*      */     }
/*      */     else {
/*      */       
/* 8060 */       this.x[0] = var26;
/* 8061 */       this.y[0] = var30;
/* 8062 */       this.z[0] = var34;
/* 8063 */       this.u[0] = var20;
/* 8064 */       this.v[0] = var24;
/*      */       
/* 8066 */       this.x[1] = var26;
/* 8067 */       this.y[1] = var30;
/* 8068 */       this.z[1] = var32;
/* 8069 */       this.u[1] = var10;
/* 8070 */       this.v[1] = var14;
/*      */       
/* 8072 */       this.x[2] = var28;
/* 8073 */       this.y[2] = var30;
/* 8074 */       this.z[2] = var32;
/* 8075 */       this.u[2] = var18;
/* 8076 */       this.v[2] = var22;
/*      */       
/* 8078 */       this.x[3] = var28;
/* 8079 */       this.y[3] = var30;
/* 8080 */       this.z[3] = var34;
/* 8081 */       this.u[3] = var12;
/* 8082 */       this.v[3] = var16;
/*      */       
/* 8084 */       var9.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
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
/*      */   public void renderFaceYPos(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
/* 8096 */     if (Minecraft.see_through_block_tops) {
/*      */       return;
/*      */     }
/* 8099 */     Tessellator var9 = Tessellator.instance;
/*      */ 
/*      */     
/* 8102 */     if (this.overrideBlockTexture != null)
/*      */     {
/* 8104 */       par8Icon = this.overrideBlockTexture;
/*      */     }
/*      */     
/* 8107 */     double var10 = par8Icon.getInterpolatedU(this.renderMinX * 16.0D);
/* 8108 */     double var12 = par8Icon.getInterpolatedU(this.renderMaxX * 16.0D);
/* 8109 */     double var14 = par8Icon.getInterpolatedV(this.renderMinZ * 16.0D);
/* 8110 */     double var16 = par8Icon.getInterpolatedV(this.renderMaxZ * 16.0D);
/*      */     
/* 8112 */     if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
/*      */       
/* 8114 */       var10 = par8Icon.getMinU();
/* 8115 */       var12 = par8Icon.getMaxU();
/*      */     } 
/*      */     
/* 8118 */     if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
/*      */       
/* 8120 */       var14 = par8Icon.getMinV();
/* 8121 */       var16 = par8Icon.getMaxV();
/*      */     } 
/*      */     
/* 8124 */     double var18 = var12;
/* 8125 */     double var20 = var10;
/* 8126 */     double var22 = var14;
/* 8127 */     double var24 = var16;
/*      */     
/* 8129 */     if (this.uvRotateTop == 1) {
/*      */       
/* 8131 */       var10 = par8Icon.getInterpolatedU(this.renderMinZ * 16.0D);
/* 8132 */       var14 = par8Icon.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
/* 8133 */       var12 = par8Icon.getInterpolatedU(this.renderMaxZ * 16.0D);
/* 8134 */       var16 = par8Icon.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
/* 8135 */       var22 = var14;
/* 8136 */       var24 = var16;
/* 8137 */       var18 = var10;
/* 8138 */       var20 = var12;
/* 8139 */       var14 = var16;
/* 8140 */       var16 = var22;
/*      */     }
/* 8142 */     else if (this.uvRotateTop == 2) {
/*      */       
/* 8144 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
/* 8145 */       var14 = par8Icon.getInterpolatedV(this.renderMinX * 16.0D);
/* 8146 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
/* 8147 */       var16 = par8Icon.getInterpolatedV(this.renderMaxX * 16.0D);
/* 8148 */       var18 = var12;
/* 8149 */       var20 = var10;
/* 8150 */       var10 = var12;
/* 8151 */       var12 = var20;
/* 8152 */       var22 = var16;
/* 8153 */       var24 = var14;
/*      */     }
/* 8155 */     else if (this.uvRotateTop == 3) {
/*      */       
/* 8157 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
/* 8158 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
/* 8159 */       var14 = par8Icon.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
/* 8160 */       var16 = par8Icon.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
/* 8161 */       var18 = var12;
/* 8162 */       var20 = var10;
/* 8163 */       var22 = var14;
/* 8164 */       var24 = var16;
/*      */     } 
/*      */     
/* 8167 */     double var26 = par2 + this.renderMinX;
/* 8168 */     double var28 = par2 + this.renderMaxX;
/* 8169 */     double var30 = par4 + this.renderMaxY;
/* 8170 */     double var32 = par6 + this.renderMinZ;
/* 8171 */     double var34 = par6 + this.renderMaxZ;
/*      */     
/* 8173 */     if (this.enableAO) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 8232 */       if (RenderingScheme.current == 0)
/*      */       {
/* 8234 */         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
/* 8235 */         var9.setBrightness(this.brightnessTopLeft);
/* 8236 */         var9.addVertexWithUV(var28, var30, var34, var12, var16);
/* 8237 */         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
/* 8238 */         var9.setBrightness(this.brightnessBottomLeft);
/* 8239 */         var9.addVertexWithUV(var28, var30, var32, var18, var22);
/* 8240 */         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
/* 8241 */         var9.setBrightness(this.brightnessBottomRight);
/* 8242 */         var9.addVertexWithUV(var26, var30, var32, var10, var14);
/* 8243 */         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
/* 8244 */         var9.setBrightness(this.brightnessTopRight);
/* 8245 */         var9.addVertexWithUV(var26, var30, var34, var20, var24);
/*      */       }
/*      */       else
/*      */       {
/* 8249 */         this.x[0] = var28;
/* 8250 */         this.y[0] = var30;
/* 8251 */         this.z[0] = var34;
/* 8252 */         this.u[0] = var12;
/* 8253 */         this.v[0] = var16;
/* 8254 */         this.r[0] = this.colorRedTopLeft;
/* 8255 */         this.g[0] = this.colorGreenTopLeft;
/* 8256 */         this.b[0] = this.colorBlueTopLeft;
/* 8257 */         this.brightness[0] = this.brightnessTopLeft;
/*      */         
/* 8259 */         this.x[1] = var28;
/* 8260 */         this.y[1] = var30;
/* 8261 */         this.z[1] = var32;
/* 8262 */         this.u[1] = var18;
/* 8263 */         this.v[1] = var22;
/* 8264 */         this.r[1] = this.colorRedBottomLeft;
/* 8265 */         this.g[1] = this.colorGreenBottomLeft;
/* 8266 */         this.b[1] = this.colorBlueBottomLeft;
/* 8267 */         this.brightness[1] = this.brightnessBottomLeft;
/*      */         
/* 8269 */         this.x[2] = var26;
/* 8270 */         this.y[2] = var30;
/* 8271 */         this.z[2] = var32;
/* 8272 */         this.u[2] = var10;
/* 8273 */         this.v[2] = var14;
/* 8274 */         this.r[2] = this.colorRedBottomRight;
/* 8275 */         this.g[2] = this.colorGreenBottomRight;
/* 8276 */         this.b[2] = this.colorBlueBottomRight;
/* 8277 */         this.brightness[2] = this.brightnessBottomRight;
/*      */         
/* 8279 */         this.x[3] = var26;
/* 8280 */         this.y[3] = var30;
/* 8281 */         this.z[3] = var34;
/* 8282 */         this.u[3] = var20;
/* 8283 */         this.v[3] = var24;
/* 8284 */         this.r[3] = this.colorRedTopRight;
/* 8285 */         this.g[3] = this.colorGreenTopRight;
/* 8286 */         this.b[3] = this.colorBlueTopRight;
/* 8287 */         this.brightness[3] = this.brightnessTopRight;
/*      */         
/* 8289 */         var9.add4VerticesWithUVandAO(this.x, this.y, this.z, this.u, this.v, this.r, this.g, this.b, this.brightness);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 8382 */     else if (RenderingScheme.current == 0) {
/*      */       
/* 8384 */       var9.addVertexWithUV(var28, var30, var34, var12, var16);
/* 8385 */       var9.addVertexWithUV(var28, var30, var32, var18, var22);
/* 8386 */       var9.addVertexWithUV(var26, var30, var32, var10, var14);
/* 8387 */       var9.addVertexWithUV(var26, var30, var34, var20, var24);
/*      */     }
/*      */     else {
/*      */       
/* 8391 */       this.x[0] = var28;
/* 8392 */       this.y[0] = var30;
/* 8393 */       this.z[0] = var34;
/* 8394 */       this.u[0] = var12;
/* 8395 */       this.v[0] = var16;
/*      */       
/* 8397 */       this.x[1] = var28;
/* 8398 */       this.y[1] = var30;
/* 8399 */       this.z[1] = var32;
/* 8400 */       this.u[1] = var18;
/* 8401 */       this.v[1] = var22;
/*      */       
/* 8403 */       this.x[2] = var26;
/* 8404 */       this.y[2] = var30;
/* 8405 */       this.z[2] = var32;
/* 8406 */       this.u[2] = var10;
/* 8407 */       this.v[2] = var14;
/*      */       
/* 8409 */       this.x[3] = var26;
/* 8410 */       this.y[3] = var30;
/* 8411 */       this.z[3] = var34;
/* 8412 */       this.u[3] = var20;
/* 8413 */       this.v[3] = var24;
/*      */       
/* 8415 */       var9.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
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
/*      */   public void renderFaceZNeg(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
/* 8430 */     Tessellator var9 = Tessellator.instance;
/*      */ 
/*      */     
/* 8433 */     if (this.overrideBlockTexture != null)
/*      */     {
/* 8435 */       par8Icon = this.overrideBlockTexture;
/*      */     }
/*      */     
/* 8438 */     double var10 = par8Icon.getInterpolatedU(this.renderMinX * 16.0D);
/* 8439 */     double var12 = par8Icon.getInterpolatedU(this.renderMaxX * 16.0D);
/* 8440 */     double var14 = par8Icon.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
/* 8441 */     double var16 = par8Icon.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
/*      */ 
/*      */     
/* 8444 */     if (this.flipTexture) {
/*      */       
/* 8446 */       double d = var10;
/* 8447 */       var10 = var12;
/* 8448 */       var12 = d;
/*      */     } 
/*      */     
/* 8451 */     if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
/*      */       
/* 8453 */       var10 = par8Icon.getMinU();
/* 8454 */       var12 = par8Icon.getMaxU();
/*      */     } 
/*      */     
/* 8457 */     if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
/*      */       
/* 8459 */       var14 = par8Icon.getMinV();
/* 8460 */       var16 = par8Icon.getMaxV();
/*      */     } 
/*      */     
/* 8463 */     double var18 = var12;
/* 8464 */     double var20 = var10;
/* 8465 */     double var22 = var14;
/* 8466 */     double var24 = var16;
/*      */     
/* 8468 */     if (this.uvRotateEast == 2) {
/*      */       
/* 8470 */       var10 = par8Icon.getInterpolatedU(this.renderMinY * 16.0D);
/* 8471 */       var14 = par8Icon.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
/* 8472 */       var12 = par8Icon.getInterpolatedU(this.renderMaxY * 16.0D);
/* 8473 */       var16 = par8Icon.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
/* 8474 */       var22 = var14;
/* 8475 */       var24 = var16;
/* 8476 */       var18 = var10;
/* 8477 */       var20 = var12;
/* 8478 */       var14 = var16;
/* 8479 */       var16 = var22;
/*      */     }
/* 8481 */     else if (this.uvRotateEast == 1) {
/*      */       
/* 8483 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
/* 8484 */       var14 = par8Icon.getInterpolatedV(this.renderMaxX * 16.0D);
/* 8485 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
/* 8486 */       var16 = par8Icon.getInterpolatedV(this.renderMinX * 16.0D);
/* 8487 */       var18 = var12;
/* 8488 */       var20 = var10;
/* 8489 */       var10 = var12;
/* 8490 */       var12 = var20;
/* 8491 */       var22 = var16;
/* 8492 */       var24 = var14;
/*      */     }
/* 8494 */     else if (this.uvRotateEast == 3) {
/*      */       
/* 8496 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
/* 8497 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
/* 8498 */       var14 = par8Icon.getInterpolatedV(this.renderMaxY * 16.0D);
/* 8499 */       var16 = par8Icon.getInterpolatedV(this.renderMinY * 16.0D);
/* 8500 */       var18 = var12;
/* 8501 */       var20 = var10;
/* 8502 */       var22 = var14;
/* 8503 */       var24 = var16;
/*      */     } 
/*      */     
/* 8506 */     double var26 = par2 + this.renderMinX;
/* 8507 */     double var28 = par2 + this.renderMaxX;
/* 8508 */     double var30 = par4 + this.renderMinY;
/* 8509 */     double var32 = par4 + this.renderMaxY;
/* 8510 */     double var34 = par6 + this.renderMinZ;
/*      */     
/* 8512 */     if (this.enableAO) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 8529 */       if (RenderingScheme.current == 0)
/*      */       {
/* 8531 */         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
/* 8532 */         var9.setBrightness(this.brightnessTopLeft);
/* 8533 */         var9.addVertexWithUV(var26, var32, var34, var18, var22);
/* 8534 */         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
/* 8535 */         var9.setBrightness(this.brightnessBottomLeft);
/* 8536 */         var9.addVertexWithUV(var28, var32, var34, var10, var14);
/* 8537 */         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
/* 8538 */         var9.setBrightness(this.brightnessBottomRight);
/* 8539 */         var9.addVertexWithUV(var28, var30, var34, var20, var24);
/* 8540 */         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
/* 8541 */         var9.setBrightness(this.brightnessTopRight);
/* 8542 */         var9.addVertexWithUV(var26, var30, var34, var12, var16);
/*      */       }
/*      */       else
/*      */       {
/* 8546 */         this.x[0] = var26;
/* 8547 */         this.y[0] = var32;
/* 8548 */         this.z[0] = var34;
/* 8549 */         this.u[0] = var18;
/* 8550 */         this.v[0] = var22;
/* 8551 */         this.r[0] = this.colorRedTopLeft;
/* 8552 */         this.g[0] = this.colorGreenTopLeft;
/* 8553 */         this.b[0] = this.colorBlueTopLeft;
/* 8554 */         this.brightness[0] = this.brightnessTopLeft;
/*      */         
/* 8556 */         this.x[1] = var28;
/* 8557 */         this.y[1] = var32;
/* 8558 */         this.z[1] = var34;
/* 8559 */         this.u[1] = var10;
/* 8560 */         this.v[1] = var14;
/* 8561 */         this.r[1] = this.colorRedBottomLeft;
/* 8562 */         this.g[1] = this.colorGreenBottomLeft;
/* 8563 */         this.b[1] = this.colorBlueBottomLeft;
/* 8564 */         this.brightness[1] = this.brightnessBottomLeft;
/*      */         
/* 8566 */         this.x[2] = var28;
/* 8567 */         this.y[2] = var30;
/* 8568 */         this.z[2] = var34;
/* 8569 */         this.u[2] = var20;
/* 8570 */         this.v[2] = var24;
/* 8571 */         this.r[2] = this.colorRedBottomRight;
/* 8572 */         this.g[2] = this.colorGreenBottomRight;
/* 8573 */         this.b[2] = this.colorBlueBottomRight;
/* 8574 */         this.brightness[2] = this.brightnessBottomRight;
/*      */         
/* 8576 */         this.x[3] = var26;
/* 8577 */         this.y[3] = var30;
/* 8578 */         this.z[3] = var34;
/* 8579 */         this.u[3] = var12;
/* 8580 */         this.v[3] = var16;
/* 8581 */         this.r[3] = this.colorRedTopRight;
/* 8582 */         this.g[3] = this.colorGreenTopRight;
/* 8583 */         this.b[3] = this.colorBlueTopRight;
/* 8584 */         this.brightness[3] = this.brightnessTopRight;
/*      */         
/* 8586 */         var9.add4VerticesWithUVandAO(this.x, this.y, this.z, this.u, this.v, this.r, this.g, this.b, this.brightness);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 8600 */     else if (RenderingScheme.current == 0) {
/*      */       
/* 8602 */       var9.addVertexWithUV(var26, var32, var34, var18, var22);
/* 8603 */       var9.addVertexWithUV(var28, var32, var34, var10, var14);
/* 8604 */       var9.addVertexWithUV(var28, var30, var34, var20, var24);
/* 8605 */       var9.addVertexWithUV(var26, var30, var34, var12, var16);
/*      */     }
/*      */     else {
/*      */       
/* 8609 */       this.x[0] = var26;
/* 8610 */       this.y[0] = var32;
/* 8611 */       this.z[0] = var34;
/* 8612 */       this.u[0] = var18;
/* 8613 */       this.v[0] = var22;
/*      */       
/* 8615 */       this.x[1] = var28;
/* 8616 */       this.y[1] = var32;
/* 8617 */       this.z[1] = var34;
/* 8618 */       this.u[1] = var10;
/* 8619 */       this.v[1] = var14;
/*      */       
/* 8621 */       this.x[2] = var28;
/* 8622 */       this.y[2] = var30;
/* 8623 */       this.z[2] = var34;
/* 8624 */       this.u[2] = var20;
/* 8625 */       this.v[2] = var24;
/*      */       
/* 8627 */       this.x[3] = var26;
/* 8628 */       this.y[3] = var30;
/* 8629 */       this.z[3] = var34;
/* 8630 */       this.u[3] = var12;
/* 8631 */       this.v[3] = var16;
/*      */       
/* 8633 */       var9.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
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
/*      */   public void renderFaceZPos(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
/* 8645 */     Tessellator var9 = Tessellator.instance;
/*      */ 
/*      */     
/* 8648 */     if (this.overrideBlockTexture != null)
/*      */     {
/* 8650 */       par8Icon = this.overrideBlockTexture;
/*      */     }
/*      */     
/* 8653 */     double var10 = par8Icon.getInterpolatedU(this.renderMinX * 16.0D);
/* 8654 */     double var12 = par8Icon.getInterpolatedU(this.renderMaxX * 16.0D);
/* 8655 */     double var14 = par8Icon.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
/* 8656 */     double var16 = par8Icon.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
/*      */ 
/*      */     
/* 8659 */     if (this.flipTexture) {
/*      */       
/* 8661 */       double d = var10;
/* 8662 */       var10 = var12;
/* 8663 */       var12 = d;
/*      */     } 
/*      */     
/* 8666 */     if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
/*      */       
/* 8668 */       var10 = par8Icon.getMinU();
/* 8669 */       var12 = par8Icon.getMaxU();
/*      */     } 
/*      */     
/* 8672 */     if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
/*      */       
/* 8674 */       var14 = par8Icon.getMinV();
/* 8675 */       var16 = par8Icon.getMaxV();
/*      */     } 
/*      */     
/* 8678 */     double var18 = var12;
/* 8679 */     double var20 = var10;
/* 8680 */     double var22 = var14;
/* 8681 */     double var24 = var16;
/*      */     
/* 8683 */     if (this.uvRotateWest == 1) {
/*      */       
/* 8685 */       var10 = par8Icon.getInterpolatedU(this.renderMinY * 16.0D);
/* 8686 */       var16 = par8Icon.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
/* 8687 */       var12 = par8Icon.getInterpolatedU(this.renderMaxY * 16.0D);
/* 8688 */       var14 = par8Icon.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
/* 8689 */       var22 = var14;
/* 8690 */       var24 = var16;
/* 8691 */       var18 = var10;
/* 8692 */       var20 = var12;
/* 8693 */       var14 = var16;
/* 8694 */       var16 = var22;
/*      */     }
/* 8696 */     else if (this.uvRotateWest == 2) {
/*      */       
/* 8698 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
/* 8699 */       var14 = par8Icon.getInterpolatedV(this.renderMinX * 16.0D);
/* 8700 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
/* 8701 */       var16 = par8Icon.getInterpolatedV(this.renderMaxX * 16.0D);
/* 8702 */       var18 = var12;
/* 8703 */       var20 = var10;
/* 8704 */       var10 = var12;
/* 8705 */       var12 = var20;
/* 8706 */       var22 = var16;
/* 8707 */       var24 = var14;
/*      */     }
/* 8709 */     else if (this.uvRotateWest == 3) {
/*      */       
/* 8711 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
/* 8712 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
/* 8713 */       var14 = par8Icon.getInterpolatedV(this.renderMaxY * 16.0D);
/* 8714 */       var16 = par8Icon.getInterpolatedV(this.renderMinY * 16.0D);
/* 8715 */       var18 = var12;
/* 8716 */       var20 = var10;
/* 8717 */       var22 = var14;
/* 8718 */       var24 = var16;
/*      */     } 
/*      */     
/* 8721 */     double var26 = par2 + this.renderMinX;
/* 8722 */     double var28 = par2 + this.renderMaxX;
/* 8723 */     double var30 = par4 + this.renderMinY;
/* 8724 */     double var32 = par4 + this.renderMaxY;
/* 8725 */     double var34 = par6 + this.renderMaxZ;
/*      */     
/* 8727 */     if (this.enableAO) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 8744 */       if (RenderingScheme.current == 0)
/*      */       {
/* 8746 */         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
/* 8747 */         var9.setBrightness(this.brightnessTopLeft);
/* 8748 */         var9.addVertexWithUV(var26, var32, var34, var10, var14);
/* 8749 */         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
/* 8750 */         var9.setBrightness(this.brightnessBottomLeft);
/* 8751 */         var9.addVertexWithUV(var26, var30, var34, var20, var24);
/* 8752 */         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
/* 8753 */         var9.setBrightness(this.brightnessBottomRight);
/* 8754 */         var9.addVertexWithUV(var28, var30, var34, var12, var16);
/* 8755 */         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
/* 8756 */         var9.setBrightness(this.brightnessTopRight);
/* 8757 */         var9.addVertexWithUV(var28, var32, var34, var18, var22);
/*      */       }
/*      */       else
/*      */       {
/* 8761 */         this.x[0] = var26;
/* 8762 */         this.y[0] = var32;
/* 8763 */         this.z[0] = var34;
/* 8764 */         this.u[0] = var10;
/* 8765 */         this.v[0] = var14;
/* 8766 */         this.r[0] = this.colorRedTopLeft;
/* 8767 */         this.g[0] = this.colorGreenTopLeft;
/* 8768 */         this.b[0] = this.colorBlueTopLeft;
/* 8769 */         this.brightness[0] = this.brightnessTopLeft;
/*      */         
/* 8771 */         this.x[1] = var26;
/* 8772 */         this.y[1] = var30;
/* 8773 */         this.z[1] = var34;
/* 8774 */         this.u[1] = var20;
/* 8775 */         this.v[1] = var24;
/* 8776 */         this.r[1] = this.colorRedBottomLeft;
/* 8777 */         this.g[1] = this.colorGreenBottomLeft;
/* 8778 */         this.b[1] = this.colorBlueBottomLeft;
/* 8779 */         this.brightness[1] = this.brightnessBottomLeft;
/*      */         
/* 8781 */         this.x[2] = var28;
/* 8782 */         this.y[2] = var30;
/* 8783 */         this.z[2] = var34;
/* 8784 */         this.u[2] = var12;
/* 8785 */         this.v[2] = var16;
/* 8786 */         this.r[2] = this.colorRedBottomRight;
/* 8787 */         this.g[2] = this.colorGreenBottomRight;
/* 8788 */         this.b[2] = this.colorBlueBottomRight;
/* 8789 */         this.brightness[2] = this.brightnessBottomRight;
/*      */         
/* 8791 */         this.x[3] = var28;
/* 8792 */         this.y[3] = var32;
/* 8793 */         this.z[3] = var34;
/* 8794 */         this.u[3] = var18;
/* 8795 */         this.v[3] = var22;
/* 8796 */         this.r[3] = this.colorRedTopRight;
/* 8797 */         this.g[3] = this.colorGreenTopRight;
/* 8798 */         this.b[3] = this.colorBlueTopRight;
/* 8799 */         this.brightness[3] = this.brightnessTopRight;
/*      */         
/* 8801 */         var9.add4VerticesWithUVandAO(this.x, this.y, this.z, this.u, this.v, this.r, this.g, this.b, this.brightness);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 8815 */     else if (RenderingScheme.current == 0) {
/*      */       
/* 8817 */       var9.addVertexWithUV(var26, var32, var34, var10, var14);
/* 8818 */       var9.addVertexWithUV(var26, var30, var34, var20, var24);
/* 8819 */       var9.addVertexWithUV(var28, var30, var34, var12, var16);
/* 8820 */       var9.addVertexWithUV(var28, var32, var34, var18, var22);
/*      */     }
/*      */     else {
/*      */       
/* 8824 */       this.x[0] = var26;
/* 8825 */       this.y[0] = var32;
/* 8826 */       this.z[0] = var34;
/* 8827 */       this.u[0] = var10;
/* 8828 */       this.v[0] = var14;
/*      */       
/* 8830 */       this.x[1] = var26;
/* 8831 */       this.y[1] = var30;
/* 8832 */       this.z[1] = var34;
/* 8833 */       this.u[1] = var20;
/* 8834 */       this.v[1] = var24;
/*      */       
/* 8836 */       this.x[2] = var28;
/* 8837 */       this.y[2] = var30;
/* 8838 */       this.z[2] = var34;
/* 8839 */       this.u[2] = var12;
/* 8840 */       this.v[2] = var16;
/*      */       
/* 8842 */       this.x[3] = var28;
/* 8843 */       this.y[3] = var32;
/* 8844 */       this.z[3] = var34;
/* 8845 */       this.u[3] = var18;
/* 8846 */       this.v[3] = var22;
/*      */       
/* 8848 */       var9.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
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
/*      */   public void renderFaceXNeg(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
/* 8863 */     Tessellator var9 = Tessellator.instance;
/*      */ 
/*      */     
/* 8866 */     if (this.overrideBlockTexture != null)
/*      */     {
/* 8868 */       par8Icon = this.overrideBlockTexture;
/*      */     }
/*      */     
/* 8871 */     double var10 = par8Icon.getInterpolatedU(this.renderMinZ * 16.0D);
/* 8872 */     double var12 = par8Icon.getInterpolatedU(this.renderMaxZ * 16.0D);
/* 8873 */     double var14 = par8Icon.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
/* 8874 */     double var16 = par8Icon.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
/*      */ 
/*      */     
/* 8877 */     if (this.flipTexture) {
/*      */       
/* 8879 */       double d = var10;
/* 8880 */       var10 = var12;
/* 8881 */       var12 = d;
/*      */     } 
/*      */     
/* 8884 */     if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
/*      */       
/* 8886 */       var10 = par8Icon.getMinU();
/* 8887 */       var12 = par8Icon.getMaxU();
/*      */     } 
/*      */     
/* 8890 */     if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
/*      */       
/* 8892 */       var14 = par8Icon.getMinV();
/* 8893 */       var16 = par8Icon.getMaxV();
/*      */     } 
/*      */     
/* 8896 */     double var18 = var12;
/* 8897 */     double var20 = var10;
/* 8898 */     double var22 = var14;
/* 8899 */     double var24 = var16;
/*      */     
/* 8901 */     if (this.uvRotateNorth == 1) {
/*      */       
/* 8903 */       var10 = par8Icon.getInterpolatedU(this.renderMinY * 16.0D);
/* 8904 */       var14 = par8Icon.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
/* 8905 */       var12 = par8Icon.getInterpolatedU(this.renderMaxY * 16.0D);
/* 8906 */       var16 = par8Icon.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
/* 8907 */       var22 = var14;
/* 8908 */       var24 = var16;
/* 8909 */       var18 = var10;
/* 8910 */       var20 = var12;
/* 8911 */       var14 = var16;
/* 8912 */       var16 = var22;
/*      */     }
/* 8914 */     else if (this.uvRotateNorth == 2) {
/*      */       
/* 8916 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
/* 8917 */       var14 = par8Icon.getInterpolatedV(this.renderMinZ * 16.0D);
/* 8918 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
/* 8919 */       var16 = par8Icon.getInterpolatedV(this.renderMaxZ * 16.0D);
/* 8920 */       var18 = var12;
/* 8921 */       var20 = var10;
/* 8922 */       var10 = var12;
/* 8923 */       var12 = var20;
/* 8924 */       var22 = var16;
/* 8925 */       var24 = var14;
/*      */     }
/* 8927 */     else if (this.uvRotateNorth == 3) {
/*      */       
/* 8929 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
/* 8930 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
/* 8931 */       var14 = par8Icon.getInterpolatedV(this.renderMaxY * 16.0D);
/* 8932 */       var16 = par8Icon.getInterpolatedV(this.renderMinY * 16.0D);
/* 8933 */       var18 = var12;
/* 8934 */       var20 = var10;
/* 8935 */       var22 = var14;
/* 8936 */       var24 = var16;
/*      */     } 
/*      */     
/* 8939 */     double var26 = par2 + this.renderMinX;
/* 8940 */     double var28 = par4 + this.renderMinY;
/* 8941 */     double var30 = par4 + this.renderMaxY;
/* 8942 */     double var32 = par6 + this.renderMinZ;
/* 8943 */     double var34 = par6 + this.renderMaxZ;
/*      */     
/* 8945 */     if (this.enableAO) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 8962 */       if (RenderingScheme.current == 0)
/*      */       {
/* 8964 */         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
/* 8965 */         var9.setBrightness(this.brightnessTopLeft);
/* 8966 */         var9.addVertexWithUV(var26, var30, var34, var18, var22);
/* 8967 */         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
/* 8968 */         var9.setBrightness(this.brightnessBottomLeft);
/* 8969 */         var9.addVertexWithUV(var26, var30, var32, var10, var14);
/* 8970 */         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
/* 8971 */         var9.setBrightness(this.brightnessBottomRight);
/* 8972 */         var9.addVertexWithUV(var26, var28, var32, var20, var24);
/* 8973 */         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
/* 8974 */         var9.setBrightness(this.brightnessTopRight);
/* 8975 */         var9.addVertexWithUV(var26, var28, var34, var12, var16);
/*      */       }
/*      */       else
/*      */       {
/* 8979 */         this.x[0] = var26;
/* 8980 */         this.y[0] = var30;
/* 8981 */         this.z[0] = var34;
/* 8982 */         this.u[0] = var18;
/* 8983 */         this.v[0] = var22;
/* 8984 */         this.r[0] = this.colorRedTopLeft;
/* 8985 */         this.g[0] = this.colorGreenTopLeft;
/* 8986 */         this.b[0] = this.colorBlueTopLeft;
/* 8987 */         this.brightness[0] = this.brightnessTopLeft;
/*      */         
/* 8989 */         this.x[1] = var26;
/* 8990 */         this.y[1] = var30;
/* 8991 */         this.z[1] = var32;
/* 8992 */         this.u[1] = var10;
/* 8993 */         this.v[1] = var14;
/* 8994 */         this.r[1] = this.colorRedBottomLeft;
/* 8995 */         this.g[1] = this.colorGreenBottomLeft;
/* 8996 */         this.b[1] = this.colorBlueBottomLeft;
/* 8997 */         this.brightness[1] = this.brightnessBottomLeft;
/*      */         
/* 8999 */         this.x[2] = var26;
/* 9000 */         this.y[2] = var28;
/* 9001 */         this.z[2] = var32;
/* 9002 */         this.u[2] = var20;
/* 9003 */         this.v[2] = var24;
/* 9004 */         this.r[2] = this.colorRedBottomRight;
/* 9005 */         this.g[2] = this.colorGreenBottomRight;
/* 9006 */         this.b[2] = this.colorBlueBottomRight;
/* 9007 */         this.brightness[2] = this.brightnessBottomRight;
/*      */         
/* 9009 */         this.x[3] = var26;
/* 9010 */         this.y[3] = var28;
/* 9011 */         this.z[3] = var34;
/* 9012 */         this.u[3] = var12;
/* 9013 */         this.v[3] = var16;
/* 9014 */         this.r[3] = this.colorRedTopRight;
/* 9015 */         this.g[3] = this.colorGreenTopRight;
/* 9016 */         this.b[3] = this.colorBlueTopRight;
/* 9017 */         this.brightness[3] = this.brightnessTopRight;
/*      */         
/* 9019 */         var9.add4VerticesWithUVandAO(this.x, this.y, this.z, this.u, this.v, this.r, this.g, this.b, this.brightness);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 9033 */     else if (RenderingScheme.current == 0) {
/*      */       
/* 9035 */       var9.addVertexWithUV(var26, var30, var34, var18, var22);
/* 9036 */       var9.addVertexWithUV(var26, var30, var32, var10, var14);
/* 9037 */       var9.addVertexWithUV(var26, var28, var32, var20, var24);
/* 9038 */       var9.addVertexWithUV(var26, var28, var34, var12, var16);
/*      */     }
/*      */     else {
/*      */       
/* 9042 */       this.x[0] = var26;
/* 9043 */       this.y[0] = var30;
/* 9044 */       this.z[0] = var34;
/* 9045 */       this.u[0] = var18;
/* 9046 */       this.v[0] = var22;
/*      */       
/* 9048 */       this.x[1] = var26;
/* 9049 */       this.y[1] = var30;
/* 9050 */       this.z[1] = var32;
/* 9051 */       this.u[1] = var10;
/* 9052 */       this.v[1] = var14;
/*      */       
/* 9054 */       this.x[2] = var26;
/* 9055 */       this.y[2] = var28;
/* 9056 */       this.z[2] = var32;
/* 9057 */       this.u[2] = var20;
/* 9058 */       this.v[2] = var24;
/*      */       
/* 9060 */       this.x[3] = var26;
/* 9061 */       this.y[3] = var28;
/* 9062 */       this.z[3] = var34;
/* 9063 */       this.u[3] = var12;
/* 9064 */       this.v[3] = var16;
/*      */       
/* 9066 */       var9.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
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
/*      */   public void renderFaceXPos(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
/* 9078 */     Tessellator var9 = Tessellator.instance;
/*      */ 
/*      */     
/* 9081 */     if (this.overrideBlockTexture != null)
/*      */     {
/* 9083 */       par8Icon = this.overrideBlockTexture;
/*      */     }
/*      */     
/* 9086 */     double var10 = par8Icon.getInterpolatedU(this.renderMinZ * 16.0D);
/* 9087 */     double var12 = par8Icon.getInterpolatedU(this.renderMaxZ * 16.0D);
/* 9088 */     double var14 = par8Icon.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
/* 9089 */     double var16 = par8Icon.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
/*      */ 
/*      */     
/* 9092 */     if (this.flipTexture) {
/*      */       
/* 9094 */       double d = var10;
/* 9095 */       var10 = var12;
/* 9096 */       var12 = d;
/*      */     } 
/*      */     
/* 9099 */     if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
/*      */       
/* 9101 */       var10 = par8Icon.getMinU();
/* 9102 */       var12 = par8Icon.getMaxU();
/*      */     } 
/*      */     
/* 9105 */     if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
/*      */       
/* 9107 */       var14 = par8Icon.getMinV();
/* 9108 */       var16 = par8Icon.getMaxV();
/*      */     } 
/*      */     
/* 9111 */     double var18 = var12;
/* 9112 */     double var20 = var10;
/* 9113 */     double var22 = var14;
/* 9114 */     double var24 = var16;
/*      */     
/* 9116 */     if (this.uvRotateSouth == 2) {
/*      */       
/* 9118 */       var10 = par8Icon.getInterpolatedU(this.renderMinY * 16.0D);
/* 9119 */       var14 = par8Icon.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
/* 9120 */       var12 = par8Icon.getInterpolatedU(this.renderMaxY * 16.0D);
/* 9121 */       var16 = par8Icon.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
/* 9122 */       var22 = var14;
/* 9123 */       var24 = var16;
/* 9124 */       var18 = var10;
/* 9125 */       var20 = var12;
/* 9126 */       var14 = var16;
/* 9127 */       var16 = var22;
/*      */     }
/* 9129 */     else if (this.uvRotateSouth == 1) {
/*      */       
/* 9131 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
/* 9132 */       var14 = par8Icon.getInterpolatedV(this.renderMaxZ * 16.0D);
/* 9133 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
/* 9134 */       var16 = par8Icon.getInterpolatedV(this.renderMinZ * 16.0D);
/* 9135 */       var18 = var12;
/* 9136 */       var20 = var10;
/* 9137 */       var10 = var12;
/* 9138 */       var12 = var20;
/* 9139 */       var22 = var16;
/* 9140 */       var24 = var14;
/*      */     }
/* 9142 */     else if (this.uvRotateSouth == 3) {
/*      */       
/* 9144 */       var10 = par8Icon.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
/* 9145 */       var12 = par8Icon.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
/* 9146 */       var14 = par8Icon.getInterpolatedV(this.renderMaxY * 16.0D);
/* 9147 */       var16 = par8Icon.getInterpolatedV(this.renderMinY * 16.0D);
/* 9148 */       var18 = var12;
/* 9149 */       var20 = var10;
/* 9150 */       var22 = var14;
/* 9151 */       var24 = var16;
/*      */     } 
/*      */     
/* 9154 */     double var26 = par2 + this.renderMaxX;
/* 9155 */     double var28 = par4 + this.renderMinY;
/* 9156 */     double var30 = par4 + this.renderMaxY;
/* 9157 */     double var32 = par6 + this.renderMinZ;
/* 9158 */     double var34 = par6 + this.renderMaxZ;
/*      */     
/* 9160 */     if (this.enableAO) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 9177 */       if (RenderingScheme.current == 0)
/*      */       {
/* 9179 */         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
/* 9180 */         var9.setBrightness(this.brightnessTopLeft);
/* 9181 */         var9.addVertexWithUV(var26, var28, var34, var20, var24);
/* 9182 */         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
/* 9183 */         var9.setBrightness(this.brightnessBottomLeft);
/* 9184 */         var9.addVertexWithUV(var26, var28, var32, var12, var16);
/* 9185 */         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
/* 9186 */         var9.setBrightness(this.brightnessBottomRight);
/* 9187 */         var9.addVertexWithUV(var26, var30, var32, var18, var22);
/* 9188 */         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
/* 9189 */         var9.setBrightness(this.brightnessTopRight);
/* 9190 */         var9.addVertexWithUV(var26, var30, var34, var10, var14);
/*      */       }
/*      */       else
/*      */       {
/* 9194 */         this.x[0] = var26;
/* 9195 */         this.y[0] = var28;
/* 9196 */         this.z[0] = var34;
/* 9197 */         this.u[0] = var20;
/* 9198 */         this.v[0] = var24;
/* 9199 */         this.r[0] = this.colorRedTopLeft;
/* 9200 */         this.g[0] = this.colorGreenTopLeft;
/* 9201 */         this.b[0] = this.colorBlueTopLeft;
/* 9202 */         this.brightness[0] = this.brightnessTopLeft;
/*      */         
/* 9204 */         this.x[1] = var26;
/* 9205 */         this.y[1] = var28;
/* 9206 */         this.z[1] = var32;
/* 9207 */         this.u[1] = var12;
/* 9208 */         this.v[1] = var16;
/* 9209 */         this.r[1] = this.colorRedBottomLeft;
/* 9210 */         this.g[1] = this.colorGreenBottomLeft;
/* 9211 */         this.b[1] = this.colorBlueBottomLeft;
/* 9212 */         this.brightness[1] = this.brightnessBottomLeft;
/*      */         
/* 9214 */         this.x[2] = var26;
/* 9215 */         this.y[2] = var30;
/* 9216 */         this.z[2] = var32;
/* 9217 */         this.u[2] = var18;
/* 9218 */         this.v[2] = var22;
/* 9219 */         this.r[2] = this.colorRedBottomRight;
/* 9220 */         this.g[2] = this.colorGreenBottomRight;
/* 9221 */         this.b[2] = this.colorBlueBottomRight;
/* 9222 */         this.brightness[2] = this.brightnessBottomRight;
/*      */         
/* 9224 */         this.x[3] = var26;
/* 9225 */         this.y[3] = var30;
/* 9226 */         this.z[3] = var34;
/* 9227 */         this.u[3] = var10;
/* 9228 */         this.v[3] = var14;
/* 9229 */         this.r[3] = this.colorRedTopRight;
/* 9230 */         this.g[3] = this.colorGreenTopRight;
/* 9231 */         this.b[3] = this.colorBlueTopRight;
/* 9232 */         this.brightness[3] = this.brightnessTopRight;
/*      */         
/* 9234 */         var9.add4VerticesWithUVandAO(this.x, this.y, this.z, this.u, this.v, this.r, this.g, this.b, this.brightness);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 9248 */     else if (RenderingScheme.current == 0) {
/*      */       
/* 9250 */       var9.addVertexWithUV(var26, var28, var34, var20, var24);
/* 9251 */       var9.addVertexWithUV(var26, var28, var32, var12, var16);
/* 9252 */       var9.addVertexWithUV(var26, var30, var32, var18, var22);
/* 9253 */       var9.addVertexWithUV(var26, var30, var34, var10, var14);
/*      */     }
/*      */     else {
/*      */       
/* 9257 */       this.x[0] = var26;
/* 9258 */       this.y[0] = var28;
/* 9259 */       this.z[0] = var34;
/* 9260 */       this.u[0] = var20;
/* 9261 */       this.v[0] = var24;
/*      */       
/* 9263 */       this.x[1] = var26;
/* 9264 */       this.y[1] = var28;
/* 9265 */       this.z[1] = var32;
/* 9266 */       this.u[1] = var12;
/* 9267 */       this.v[1] = var16;
/*      */       
/* 9269 */       this.x[2] = var26;
/* 9270 */       this.y[2] = var30;
/* 9271 */       this.z[2] = var32;
/* 9272 */       this.u[2] = var18;
/* 9273 */       this.v[2] = var22;
/*      */       
/* 9275 */       this.x[3] = var26;
/* 9276 */       this.y[3] = var30;
/* 9277 */       this.z[3] = var34;
/* 9278 */       this.u[3] = var10;
/* 9279 */       this.v[3] = var14;
/*      */       
/* 9281 */       var9.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
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
/*      */   public void renderBlockAsItem(Block par1Block, int par2, float par3) {
/* 9293 */     Tessellator var4 = Tessellator.instance;
/* 9294 */     boolean var5 = (par1Block.blockID == Block.grass.blockID);
/*      */ 
/*      */     
/* 9297 */     if (par1Block == Block.dispenser || par1Block == Block.dropper || par1Block instanceof BlockFurnace)
/*      */     {
/* 9299 */       par2 = 3;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 9307 */     if (this.useInventoryTint) {
/*      */       
/* 9309 */       int i = par1Block.getRenderColor(par2);
/*      */       
/* 9311 */       if (var5)
/*      */       {
/* 9313 */         i = 16777215;
/*      */       }
/*      */       
/* 9316 */       float var7 = (i >> 16 & 0xFF) / 255.0F;
/* 9317 */       float var8 = (i >> 8 & 0xFF) / 255.0F;
/* 9318 */       float var9 = (i & 0xFF) / 255.0F;
/* 9319 */       GL11.glColor4f(var7 * par3, var8 * par3, var9 * par3, 1.0F);
/*      */     } 
/*      */     
/* 9322 */     int var6 = par1Block.getRenderType();
/*      */ 
/*      */     
/* 9325 */     if (par1Block.isAlwaysStandardFormCube()) {
/* 9326 */       setRenderBoundsForStandardFormBlock();
/*      */     } else {
/* 9328 */       setRenderBoundsForNonStandardFormBlock(par1Block);
/*      */     } 
/*      */ 
/*      */     
/* 9332 */     if (var6 != 0 && var6 != 31 && var6 != 39 && var6 != 16 && var6 != 26) {
/*      */       
/* 9334 */       if (var6 == 1) {
/*      */         
/* 9336 */         var4.startDrawingQuads();
/* 9337 */         var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9338 */         drawCrossedSquares(par1Block, par2, -0.5D, -0.5D, -0.5D, 1.0F);
/* 9339 */         var4.draw();
/*      */       }
/* 9341 */       else if (var6 == 19) {
/*      */         
/* 9343 */         var4.startDrawingQuads();
/* 9344 */         var4.setNormal(0.0F, -1.0F, 0.0F);
/*      */         
/* 9346 */         par1Block.setBlockBoundsForItemRender(par2);
/* 9347 */         renderBlockStemSmall(par1Block, par2, this.renderMaxY, -0.5D, -0.5D, -0.5D);
/* 9348 */         var4.draw();
/*      */       }
/* 9350 */       else if (var6 == 23) {
/*      */         
/* 9352 */         var4.startDrawingQuads();
/* 9353 */         var4.setNormal(0.0F, -1.0F, 0.0F);
/*      */         
/* 9355 */         par1Block.setBlockBoundsForItemRender(par2);
/* 9356 */         var4.draw();
/*      */       }
/* 9358 */       else if (var6 == 13) {
/*      */ 
/*      */         
/* 9361 */         par1Block.setBlockBoundsForItemRender(par2);
/* 9362 */         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9363 */         float var7 = 0.0625F;
/* 9364 */         var4.startDrawingQuads();
/* 9365 */         var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9366 */         renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 0));
/* 9367 */         var4.draw();
/* 9368 */         var4.startDrawingQuads();
/* 9369 */         var4.setNormal(0.0F, 1.0F, 0.0F);
/* 9370 */         renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 1));
/* 9371 */         var4.draw();
/* 9372 */         var4.startDrawingQuads();
/* 9373 */         var4.setNormal(0.0F, 0.0F, -1.0F);
/* 9374 */         var4.addTranslation(0.0F, 0.0F, var7);
/* 9375 */         renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 2));
/* 9376 */         var4.addTranslation(0.0F, 0.0F, -var7);
/* 9377 */         var4.draw();
/* 9378 */         var4.startDrawingQuads();
/* 9379 */         var4.setNormal(0.0F, 0.0F, 1.0F);
/* 9380 */         var4.addTranslation(0.0F, 0.0F, -var7);
/* 9381 */         renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 3));
/* 9382 */         var4.addTranslation(0.0F, 0.0F, var7);
/* 9383 */         var4.draw();
/* 9384 */         var4.startDrawingQuads();
/* 9385 */         var4.setNormal(-1.0F, 0.0F, 0.0F);
/* 9386 */         var4.addTranslation(var7, 0.0F, 0.0F);
/* 9387 */         renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 4));
/* 9388 */         var4.addTranslation(-var7, 0.0F, 0.0F);
/* 9389 */         var4.draw();
/* 9390 */         var4.startDrawingQuads();
/* 9391 */         var4.setNormal(1.0F, 0.0F, 0.0F);
/* 9392 */         var4.addTranslation(-var7, 0.0F, 0.0F);
/* 9393 */         renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 5));
/* 9394 */         var4.addTranslation(var7, 0.0F, 0.0F);
/* 9395 */         var4.draw();
/* 9396 */         GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*      */       }
/* 9398 */       else if (var6 == 22) {
/*      */         
/* 9400 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 9401 */         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9402 */         ChestItemRenderHelper.instance.renderChest(par1Block, par2, par3);
/* 9403 */         GL11.glEnable(32826);
/*      */       }
/* 9405 */       else if (var6 == 6) {
/*      */         
/* 9407 */         var4.startDrawingQuads();
/* 9408 */         var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9409 */         renderBlockCropsImpl(par1Block, par2, -0.5D, -0.5D, -0.5D);
/* 9410 */         var4.draw();
/*      */       }
/* 9412 */       else if (var6 == 2) {
/*      */         
/* 9414 */         var4.startDrawingQuads();
/* 9415 */         var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9416 */         renderTorchAtAngle(par1Block, -0.5D, -0.5D, -0.5D, 0.0D, 0.0D, 0);
/* 9417 */         var4.draw();
/*      */       }
/* 9419 */       else if (var6 == 10) {
/*      */         
/* 9421 */         for (int var14 = 0; var14 < 2; var14++)
/*      */         {
/* 9423 */           if (var14 == 0)
/*      */           {
/* 9425 */             setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
/*      */           }
/*      */           
/* 9428 */           if (var14 == 1)
/*      */           {
/* 9430 */             setRenderBounds(0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
/*      */           }
/*      */           
/* 9433 */           GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9434 */           var4.startDrawingQuads();
/* 9435 */           var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9436 */           renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 0));
/* 9437 */           var4.draw();
/* 9438 */           var4.startDrawingQuads();
/* 9439 */           var4.setNormal(0.0F, 1.0F, 0.0F);
/* 9440 */           renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 1));
/* 9441 */           var4.draw();
/* 9442 */           var4.startDrawingQuads();
/* 9443 */           var4.setNormal(0.0F, 0.0F, -1.0F);
/* 9444 */           renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 2));
/* 9445 */           var4.draw();
/* 9446 */           var4.startDrawingQuads();
/* 9447 */           var4.setNormal(0.0F, 0.0F, 1.0F);
/* 9448 */           renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 3));
/* 9449 */           var4.draw();
/* 9450 */           var4.startDrawingQuads();
/* 9451 */           var4.setNormal(-1.0F, 0.0F, 0.0F);
/* 9452 */           renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 4));
/* 9453 */           var4.draw();
/* 9454 */           var4.startDrawingQuads();
/* 9455 */           var4.setNormal(1.0F, 0.0F, 0.0F);
/* 9456 */           renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 5));
/* 9457 */           var4.draw();
/* 9458 */           GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*      */         }
/*      */       
/* 9461 */       } else if (var6 == 27) {
/*      */         
/* 9463 */         int var14 = 0;
/* 9464 */         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9465 */         var4.startDrawingQuads();
/*      */         
/* 9467 */         for (int var15 = 0; var15 < 8; var15++) {
/*      */           
/* 9469 */           byte var16 = 0;
/* 9470 */           byte var17 = 1;
/*      */           
/* 9472 */           if (var15 == 0)
/*      */           {
/* 9474 */             var16 = 2;
/*      */           }
/*      */           
/* 9477 */           if (var15 == 1)
/*      */           {
/* 9479 */             var16 = 3;
/*      */           }
/*      */           
/* 9482 */           if (var15 == 2)
/*      */           {
/* 9484 */             var16 = 4;
/*      */           }
/*      */           
/* 9487 */           if (var15 == 3) {
/*      */             
/* 9489 */             var16 = 5;
/* 9490 */             var17 = 2;
/*      */           } 
/*      */           
/* 9493 */           if (var15 == 4) {
/*      */             
/* 9495 */             var16 = 6;
/* 9496 */             var17 = 3;
/*      */           } 
/*      */           
/* 9499 */           if (var15 == 5) {
/*      */             
/* 9501 */             var16 = 7;
/* 9502 */             var17 = 5;
/*      */           } 
/*      */           
/* 9505 */           if (var15 == 6) {
/*      */             
/* 9507 */             var16 = 6;
/* 9508 */             var17 = 2;
/*      */           } 
/*      */           
/* 9511 */           if (var15 == 7)
/*      */           {
/* 9513 */             var16 = 3;
/*      */           }
/*      */           
/* 9516 */           float var11 = var16 / 16.0F;
/* 9517 */           float var12 = 1.0F - var14 / 16.0F;
/* 9518 */           float var13 = 1.0F - (var14 + var17) / 16.0F;
/* 9519 */           var14 += var17;
/* 9520 */           setRenderBounds((0.5F - var11), var13, (0.5F - var11), (0.5F + var11), var12, (0.5F + var11));
/* 9521 */           var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9522 */           renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 0));
/* 9523 */           var4.setNormal(0.0F, 1.0F, 0.0F);
/* 9524 */           renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 1));
/* 9525 */           var4.setNormal(0.0F, 0.0F, -1.0F);
/* 9526 */           renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 2));
/* 9527 */           var4.setNormal(0.0F, 0.0F, 1.0F);
/* 9528 */           renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 3));
/* 9529 */           var4.setNormal(-1.0F, 0.0F, 0.0F);
/* 9530 */           renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 4));
/* 9531 */           var4.setNormal(1.0F, 0.0F, 0.0F);
/* 9532 */           renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 5));
/*      */         } 
/*      */         
/* 9535 */         var4.draw();
/* 9536 */         GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 9537 */         setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */       }
/* 9539 */       else if (var6 == 11) {
/*      */         
/* 9541 */         for (int var14 = 0; var14 < 4; var14++) {
/*      */           
/* 9543 */           float var8 = 0.125F;
/*      */           
/* 9545 */           if (var14 == 0)
/*      */           {
/* 9547 */             setRenderBounds((0.5F - var8), 0.0D, 0.0D, (0.5F + var8), 1.0D, (var8 * 2.0F));
/*      */           }
/*      */           
/* 9550 */           if (var14 == 1)
/*      */           {
/* 9552 */             setRenderBounds((0.5F - var8), 0.0D, (1.0F - var8 * 2.0F), (0.5F + var8), 1.0D, 1.0D);
/*      */           }
/*      */           
/* 9555 */           var8 = 0.0625F;
/*      */           
/* 9557 */           if (var14 == 2)
/*      */           {
/* 9559 */             setRenderBounds((0.5F - var8), (1.0F - var8 * 3.0F), (-var8 * 2.0F), (0.5F + var8), (1.0F - var8), (1.0F + var8 * 2.0F));
/*      */           }
/*      */           
/* 9562 */           if (var14 == 3)
/*      */           {
/* 9564 */             setRenderBounds((0.5F - var8), (0.5F - var8 * 3.0F), (-var8 * 2.0F), (0.5F + var8), (0.5F - var8), (1.0F + var8 * 2.0F));
/*      */           }
/*      */           
/* 9567 */           GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9568 */           var4.startDrawingQuads();
/* 9569 */           var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9570 */           renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 0));
/* 9571 */           var4.draw();
/* 9572 */           var4.startDrawingQuads();
/* 9573 */           var4.setNormal(0.0F, 1.0F, 0.0F);
/* 9574 */           renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 1));
/* 9575 */           var4.draw();
/* 9576 */           var4.startDrawingQuads();
/* 9577 */           var4.setNormal(0.0F, 0.0F, -1.0F);
/* 9578 */           renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 2));
/* 9579 */           var4.draw();
/* 9580 */           var4.startDrawingQuads();
/* 9581 */           var4.setNormal(0.0F, 0.0F, 1.0F);
/* 9582 */           renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 3));
/* 9583 */           var4.draw();
/* 9584 */           var4.startDrawingQuads();
/* 9585 */           var4.setNormal(-1.0F, 0.0F, 0.0F);
/* 9586 */           renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 4));
/* 9587 */           var4.draw();
/* 9588 */           var4.startDrawingQuads();
/* 9589 */           var4.setNormal(1.0F, 0.0F, 0.0F);
/* 9590 */           renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 5));
/* 9591 */           var4.draw();
/* 9592 */           GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*      */         } 
/*      */         
/* 9595 */         setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */       }
/* 9597 */       else if (var6 == 21) {
/*      */         
/* 9599 */         for (int var14 = 0; var14 < 3; var14++)
/*      */         {
/* 9601 */           float var8 = 0.0625F;
/*      */           
/* 9603 */           if (var14 == 0)
/*      */           {
/* 9605 */             setRenderBounds((0.5F - var8), 0.30000001192092896D, 0.0D, (0.5F + var8), 1.0D, (var8 * 2.0F));
/*      */           }
/*      */           
/* 9608 */           if (var14 == 1)
/*      */           {
/* 9610 */             setRenderBounds((0.5F - var8), 0.30000001192092896D, (1.0F - var8 * 2.0F), (0.5F + var8), 1.0D, 1.0D);
/*      */           }
/*      */           
/* 9613 */           var8 = 0.0625F;
/*      */           
/* 9615 */           if (var14 == 2)
/*      */           {
/* 9617 */             setRenderBounds((0.5F - var8), 0.5D, 0.0D, (0.5F + var8), (1.0F - var8), 1.0D);
/*      */           }
/*      */           
/* 9620 */           GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9621 */           var4.startDrawingQuads();
/* 9622 */           var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9623 */           renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 0));
/* 9624 */           var4.draw();
/* 9625 */           var4.startDrawingQuads();
/* 9626 */           var4.setNormal(0.0F, 1.0F, 0.0F);
/* 9627 */           renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 1));
/* 9628 */           var4.draw();
/* 9629 */           var4.startDrawingQuads();
/* 9630 */           var4.setNormal(0.0F, 0.0F, -1.0F);
/* 9631 */           renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 2));
/* 9632 */           var4.draw();
/* 9633 */           var4.startDrawingQuads();
/* 9634 */           var4.setNormal(0.0F, 0.0F, 1.0F);
/* 9635 */           renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 3));
/* 9636 */           var4.draw();
/* 9637 */           var4.startDrawingQuads();
/* 9638 */           var4.setNormal(-1.0F, 0.0F, 0.0F);
/* 9639 */           renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 4));
/* 9640 */           var4.draw();
/* 9641 */           var4.startDrawingQuads();
/* 9642 */           var4.setNormal(1.0F, 0.0F, 0.0F);
/* 9643 */           renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSide(par1Block, 5));
/* 9644 */           var4.draw();
/* 9645 */           GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*      */         }
/*      */       
/* 9648 */       } else if (var6 == 32) {
/*      */         
/* 9650 */         for (int var14 = 0; var14 < 2; var14++) {
/*      */           
/* 9652 */           if (var14 == 0)
/*      */           {
/* 9654 */             setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
/*      */           }
/*      */           
/* 9657 */           if (var14 == 1)
/*      */           {
/* 9659 */             setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/*      */           }
/*      */           
/* 9662 */           GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9663 */           var4.startDrawingQuads();
/* 9664 */           var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9665 */           renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 0, par2));
/* 9666 */           var4.draw();
/* 9667 */           var4.startDrawingQuads();
/* 9668 */           var4.setNormal(0.0F, 1.0F, 0.0F);
/* 9669 */           renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 1, par2));
/* 9670 */           var4.draw();
/* 9671 */           var4.startDrawingQuads();
/* 9672 */           var4.setNormal(0.0F, 0.0F, -1.0F);
/* 9673 */           renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 2, par2));
/* 9674 */           var4.draw();
/* 9675 */           var4.startDrawingQuads();
/* 9676 */           var4.setNormal(0.0F, 0.0F, 1.0F);
/* 9677 */           renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 3, par2));
/* 9678 */           var4.draw();
/* 9679 */           var4.startDrawingQuads();
/* 9680 */           var4.setNormal(-1.0F, 0.0F, 0.0F);
/* 9681 */           renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 4, par2));
/* 9682 */           var4.draw();
/* 9683 */           var4.startDrawingQuads();
/* 9684 */           var4.setNormal(1.0F, 0.0F, 0.0F);
/* 9685 */           renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 5, par2));
/* 9686 */           var4.draw();
/* 9687 */           GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*      */         } 
/*      */         
/* 9690 */         setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */       }
/* 9692 */       else if (var6 == 35) {
/*      */         
/* 9694 */         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9695 */         renderBlockAnvilOrient((BlockAnvil)par1Block, 0, 0, 0, par2 << 2, true);
/* 9696 */         GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*      */       }
/* 9698 */       else if (var6 == 34) {
/*      */         
/* 9700 */         for (int var14 = 0; var14 < 3; var14++) {
/*      */           
/* 9702 */           if (var14 == 0) {
/*      */             
/* 9704 */             setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.1875D, 0.875D);
/* 9705 */             setOverrideBlockTexture(getBlockIcon(Block.obsidian));
/*      */           }
/* 9707 */           else if (var14 == 1) {
/*      */             
/* 9709 */             setRenderBounds(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
/* 9710 */             setOverrideBlockTexture(getBlockIcon(Block.beacon));
/*      */           }
/* 9712 */           else if (var14 == 2) {
/*      */             
/* 9714 */             setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 9715 */             setOverrideBlockTexture(getBlockIcon(Block.glass));
/*      */           } 
/*      */           
/* 9718 */           GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9719 */           var4.startDrawingQuads();
/* 9720 */           var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9721 */           renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 0, par2));
/* 9722 */           var4.draw();
/* 9723 */           var4.startDrawingQuads();
/* 9724 */           var4.setNormal(0.0F, 1.0F, 0.0F);
/* 9725 */           renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 1, par2));
/* 9726 */           var4.draw();
/* 9727 */           var4.startDrawingQuads();
/* 9728 */           var4.setNormal(0.0F, 0.0F, -1.0F);
/* 9729 */           renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 2, par2));
/* 9730 */           var4.draw();
/* 9731 */           var4.startDrawingQuads();
/* 9732 */           var4.setNormal(0.0F, 0.0F, 1.0F);
/* 9733 */           renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 3, par2));
/* 9734 */           var4.draw();
/* 9735 */           var4.startDrawingQuads();
/* 9736 */           var4.setNormal(-1.0F, 0.0F, 0.0F);
/* 9737 */           renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 4, par2));
/* 9738 */           var4.draw();
/* 9739 */           var4.startDrawingQuads();
/* 9740 */           var4.setNormal(1.0F, 0.0F, 0.0F);
/* 9741 */           renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 5, par2));
/* 9742 */           var4.draw();
/* 9743 */           GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*      */         } 
/*      */         
/* 9746 */         setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 9747 */         clearOverrideBlockTexture();
/*      */       }
/* 9749 */       else if (var6 == 38) {
/*      */         
/* 9751 */         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9752 */         renderBlockHopperMetadata((BlockHopper)par1Block, 0, 0, 0, 0, true);
/* 9753 */         GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 9758 */       if (var6 == 16)
/*      */       {
/* 9760 */         par2 = 1;
/*      */       }
/*      */ 
/*      */       
/* 9764 */       par1Block.setBlockBoundsForItemRender(par2);
/*      */ 
/*      */       
/* 9767 */       if (par1Block.isAlwaysStandardFormCube()) {
/* 9768 */         setRenderBoundsForStandardFormBlock();
/*      */       } else {
/* 9770 */         setRenderBoundsForNonStandardFormBlock(par1Block);
/*      */       } 
/* 9772 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 9773 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 9774 */       var4.startDrawingQuads();
/* 9775 */       var4.setNormal(0.0F, -1.0F, 0.0F);
/* 9776 */       renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 0, par2));
/* 9777 */       var4.draw();
/*      */       
/* 9779 */       if (var5 && this.useInventoryTint) {
/*      */         
/* 9781 */         int var14 = par1Block.getRenderColor(par2);
/* 9782 */         float var8 = (var14 >> 16 & 0xFF) / 255.0F;
/* 9783 */         float var9 = (var14 >> 8 & 0xFF) / 255.0F;
/* 9784 */         float var10 = (var14 & 0xFF) / 255.0F;
/* 9785 */         GL11.glColor4f(var8 * par3, var9 * par3, var10 * par3, 1.0F);
/*      */       } 
/*      */       
/* 9788 */       var4.startDrawingQuads();
/* 9789 */       var4.setNormal(0.0F, 1.0F, 0.0F);
/* 9790 */       renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 1, par2));
/* 9791 */       var4.draw();
/*      */       
/* 9793 */       if (var5 && this.useInventoryTint)
/*      */       {
/* 9795 */         GL11.glColor4f(par3, par3, par3, 1.0F);
/*      */       }
/*      */       
/* 9798 */       var4.startDrawingQuads();
/* 9799 */       var4.setNormal(0.0F, 0.0F, -1.0F);
/* 9800 */       renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 2, par2));
/* 9801 */       var4.draw();
/* 9802 */       var4.startDrawingQuads();
/* 9803 */       var4.setNormal(0.0F, 0.0F, 1.0F);
/* 9804 */       renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 3, par2));
/* 9805 */       var4.draw();
/* 9806 */       var4.startDrawingQuads();
/* 9807 */       var4.setNormal(-1.0F, 0.0F, 0.0F);
/* 9808 */       renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 4, par2));
/* 9809 */       var4.draw();
/* 9810 */       var4.startDrawingQuads();
/* 9811 */       var4.setNormal(1.0F, 0.0F, 0.0F);
/* 9812 */       renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, getBlockIconFromSideAndMetadata(par1Block, 5, par2));
/* 9813 */       var4.draw();
/* 9814 */       GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean renderItemIn3d(int par0) {
/* 9823 */     return (par0 == 0) ? true : ((par0 == 31) ? true : ((par0 == 39) ? true : ((par0 == 13) ? true : ((par0 == 10) ? true : ((par0 == 11) ? true : ((par0 == 27) ? true : ((par0 == 22) ? true : ((par0 == 21) ? true : ((par0 == 16) ? true : ((par0 == 26) ? true : ((par0 == 32) ? true : ((par0 == 34) ? true : ((par0 == 35))))))))))))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getBlockIcon(Block par1Block, IBlockAccess par2IBlockAccess, int par3, int par4, int par5, int par6) {
/* 9830 */     Icon par1Icon = par1Block.getBlockTexture(par2IBlockAccess, par3, par4, par5, par6);
/* 9831 */     return (par1Icon != null) ? par1Icon : ((TextureMap)Minecraft.theMinecraft.getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getBlockIconFromSideAndMetadata(Block par1Block, int par2, int par3) {
/* 9838 */     Icon par1Icon = par1Block.getIcon(par2, par3);
/* 9839 */     return (par1Icon != null) ? par1Icon : ((TextureMap)Minecraft.theMinecraft.getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getBlockIconFromSide(Block par1Block, int par2) {
/* 9846 */     Icon par1Icon = par1Block.getBlockTextureFromSide(par2);
/* 9847 */     return (par1Icon != null) ? par1Icon : ((TextureMap)Minecraft.theMinecraft.getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
/*      */   }
/*      */ 
/*      */   
/*      */   public Icon getBlockIcon(Block par1Block) {
/* 9852 */     return getIconSafe(par1Block.getBlockTextureFromSide(1));
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
/*      */   public Icon getIconSafe(Icon par1Icon) {
/* 9864 */     return (par1Icon != null) ? par1Icon : ((TextureMap)Minecraft.theMinecraft.getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderBlocks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */