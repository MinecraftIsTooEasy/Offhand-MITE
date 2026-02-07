/*     */ package net.minecraft;
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
/*     */ public class BlockPistonBase
/*     */   extends BlockDirectional
/*     */   implements IBlockWithPartner
/*     */ {
/*     */   private final boolean isSticky;
/*     */   private Icon innerTopIcon;
/*     */   private Icon bottomIcon;
/*     */   private Icon topIcon;
/*     */   
/*     */   public BlockPistonBase(int par1, boolean par2) {
/*  23 */     super(par1, Material.piston, new BlockConstants());
/*  24 */     this.isSticky = par2;
/*  25 */     setStepSound(soundStoneFootstep);
/*  26 */     setHardness(0.5F);
/*  27 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  32 */     String[] array = new String[6];
/*     */     
/*  34 */     for (int i = 0; i < array.length; i++) {
/*  35 */       array[i] = i + "=" + getDirectionFacing(i).getDescriptor(true);
/*     */     }
/*  37 */     return StringHelper.implode(array, ", ", true, false) + ", bit 8 set if piston is extended";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  42 */     return ((metadata >= 0 && metadata < 6) || (metadata >= 8 && metadata < 14));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getPistonExtensionTexture() {
/*  52 */     return this.topIcon;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96479_b(float par1, float par2, float par3, float par4, float par5, float par6) {
/*  57 */     setBlockBoundsForCurrentThread(par1, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  65 */     int var3 = getOrientation(par2);
/*     */     
/*  67 */     int index = Minecraft.getThreadIndex();
/*     */     
/*  69 */     return (var3 > 5) ? this.topIcon : ((par1 == var3) ? ((!isExtended(par2) && this.minX[index] <= 0.0D && this.minY[index] <= 0.0D && this.minZ[index] <= 0.0D && this.maxX[index] >= 1.0D && this.maxY[index] >= 1.0D && this.maxZ[index] >= 1.0D) ? this.topIcon : this.innerTopIcon) : ((par1 == Facing.oppositeSide[var3]) ? this.bottomIcon : this.blockIcon));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Icon getPistonBaseIcon(String par0Str) {
/*  74 */     return (par0Str == "piston_side") ? Block.pistonBase.blockIcon : ((par0Str == "piston_top_normal") ? Block.pistonBase.topIcon : ((par0Str == "piston_top_sticky") ? Block.pistonStickyBase.topIcon : ((par0Str == "piston_inner") ? Block.pistonBase.innerTopIcon : null)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  83 */     this.blockIcon = par1IconRegister.registerIcon("piston_side");
/*  84 */     this.topIcon = par1IconRegister.registerIcon(this.isSticky ? "piston_top_sticky" : "piston_top_normal");
/*  85 */     this.innerTopIcon = par1IconRegister.registerIcon("piston_inner");
/*  86 */     this.bottomIcon = par1IconRegister.registerIcon("piston_bottom");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  94 */     return 16;
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
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 116 */     return false;
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 149 */     return updatePistonState(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 157 */     if (!par1World.isRemote && par1World.getBlockTileEntity(par2, par3, par4) == null)
/*     */     {
/* 159 */       updatePistonState(par1World, par2, par3, par4);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean updatePistonState(World par1World, int par2, int par3, int par4) {
/* 169 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/* 170 */     int var6 = getOrientation(var5);
/*     */     
/* 172 */     if (var6 != 7) {
/*     */       
/* 174 */       boolean var7 = isIndirectlyPowered(par1World, par2, par3, par4, var6);
/*     */       
/* 176 */       if (var7 && !isExtended(var5)) {
/*     */         
/* 178 */         if (canExtend(par1World, par2, par3, par4, var6))
/*     */         {
/* 180 */           par1World.addBlockEvent(par2, par3, par4, this.blockID, 0, var6);
/*     */         }
/*     */       }
/* 183 */       else if (!var7 && isExtended(var5)) {
/*     */         
/* 185 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 2);
/* 186 */         par1World.addBlockEvent(par2, par3, par4, this.blockID, 1, var6);
/*     */         
/* 188 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isIndirectlyPowered(World par1World, int par2, int par3, int par4, int par5) {
/* 200 */     return (par5 != 0 && par1World.getIndirectPowerOutput(par2, par3 - 1, par4, 0)) ? true : ((par5 != 1 && par1World.getIndirectPowerOutput(par2, par3 + 1, par4, 1)) ? true : ((par5 != 2 && par1World.getIndirectPowerOutput(par2, par3, par4 - 1, 2)) ? true : ((par5 != 3 && par1World.getIndirectPowerOutput(par2, par3, par4 + 1, 3)) ? true : ((par5 != 5 && par1World.getIndirectPowerOutput(par2 + 1, par3, par4, 5)) ? true : ((par5 != 4 && par1World.getIndirectPowerOutput(par2 - 1, par3, par4, 4)) ? true : (par1World.getIndirectPowerOutput(par2, par3, par4, 0) ? true : (par1World.getIndirectPowerOutput(par2, par3 + 2, par4, 1) ? true : (par1World.getIndirectPowerOutput(par2, par3 + 1, par4 - 1, 2) ? true : (par1World.getIndirectPowerOutput(par2, par3 + 1, par4 + 1, 3) ? true : (par1World.getIndirectPowerOutput(par2 - 1, par3 + 1, par4, 4) ? true : par1World.getIndirectPowerOutput(par2 + 1, par3 + 1, par4, 5)))))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 209 */     if (!par1World.isRemote) {
/*     */       
/* 211 */       boolean var7 = isIndirectlyPowered(par1World, par2, par3, par4, par6);
/*     */       
/* 213 */       if (var7 && par5 == 1) {
/*     */         
/* 215 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, par6 | 0x8, 2);
/* 216 */         return false;
/*     */       } 
/*     */       
/* 219 */       if (!var7 && par5 == 0)
/*     */       {
/* 221 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 225 */     if (par5 == 0) {
/*     */       
/* 227 */       if (!tryExtend(par1World, par2, par3, par4, par6))
/*     */       {
/* 229 */         return false;
/*     */       }
/*     */       
/* 232 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, par6 | 0x8, 3);
/* 233 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "tile.piston.out", 0.5F, par1World.rand.nextFloat() * 0.25F + 0.6F);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 238 */     else if (par5 == 1) {
/*     */       
/* 240 */       TileEntity var16 = par1World.getBlockTileEntity(par2 + Facing.offsetsXForSide[par6], par3 + Facing.offsetsYForSide[par6], par4 + Facing.offsetsZForSide[par6]);
/*     */       
/* 242 */       if (var16 instanceof TileEntityPiston)
/*     */       {
/* 244 */         ((TileEntityPiston)var16).clearPistonTileEntity();
/*     */       }
/*     */       
/* 247 */       par1World.setBlock(par2, par3, par4, Block.pistonMoving.blockID, par6, 3);
/* 248 */       par1World.setBlockTileEntity(par2, par3, par4, BlockPistonMoving.getTileEntity(this.blockID, par6, par6, false, true));
/*     */       
/* 250 */       if (this.isSticky) {
/*     */         
/* 252 */         int var8 = par2 + Facing.offsetsXForSide[par6] * 2;
/* 253 */         int var9 = par3 + Facing.offsetsYForSide[par6] * 2;
/* 254 */         int var10 = par4 + Facing.offsetsZForSide[par6] * 2;
/* 255 */         int var11 = par1World.getBlockId(var8, var9, var10);
/* 256 */         int var12 = par1World.getBlockMetadata(var8, var9, var10);
/* 257 */         boolean var13 = false;
/*     */         
/* 259 */         if (var11 == Block.pistonMoving.blockID) {
/*     */           
/* 261 */           TileEntity var14 = par1World.getBlockTileEntity(var8, var9, var10);
/*     */           
/* 263 */           if (var14 instanceof TileEntityPiston) {
/*     */             
/* 265 */             TileEntityPiston var15 = (TileEntityPiston)var14;
/*     */             
/* 267 */             if (var15.getPistonOrientation() == par6 && var15.isExtending()) {
/*     */               
/* 269 */               var15.clearPistonTileEntity();
/* 270 */               var11 = var15.getStoredBlockID();
/* 271 */               var12 = var15.getBlockMetadata();
/* 272 */               var13 = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 277 */         if (!var13 && var11 > 0 && canPushBlock(var11, par1World, var8, var9, var10, false) && (Block.blocksList[var11].getMobilityFlag() == 0 || var11 == Block.pistonBase.blockID || var11 == Block.pistonStickyBase.blockID))
/*     */         {
/* 279 */           par2 += Facing.offsetsXForSide[par6];
/* 280 */           par3 += Facing.offsetsYForSide[par6];
/* 281 */           par4 += Facing.offsetsZForSide[par6];
/* 282 */           par1World.setBlock(par2, par3, par4, Block.pistonMoving.blockID, var12, 3);
/* 283 */           par1World.setBlockTileEntity(par2, par3, par4, BlockPistonMoving.getTileEntity(var11, var12, par6, false, false));
/* 284 */           par1World.setBlockToAir(var8, var9, var10);
/*     */         }
/* 286 */         else if (!var13)
/*     */         {
/* 288 */           par1World.setBlockToAir(par2 + Facing.offsetsXForSide[par6], par3 + Facing.offsetsYForSide[par6], par4 + Facing.offsetsZForSide[par6]);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 293 */         par1World.setBlockToAir(par2 + Facing.offsetsXForSide[par6], par3 + Facing.offsetsYForSide[par6], par4 + Facing.offsetsZForSide[par6]);
/*     */       } 
/*     */       
/* 296 */       par1World.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "tile.piston.in", 0.5F, par1World.rand.nextFloat() * 0.15F + 0.6F);
/*     */     } 
/*     */     
/* 299 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 307 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*     */     
/* 309 */     if (isExtended(var5)) {
/*     */       
/* 311 */       float var6 = 0.25F;
/*     */       
/* 313 */       switch (getOrientation(var5)) {
/*     */         
/*     */         case 0:
/* 316 */           setBlockBoundsForCurrentThread(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */           break;
/*     */         
/*     */         case 1:
/* 320 */           setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
/*     */           break;
/*     */         
/*     */         case 2:
/* 324 */           setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D);
/*     */           break;
/*     */         
/*     */         case 3:
/* 328 */           setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D);
/*     */           break;
/*     */         
/*     */         case 4:
/* 332 */           setBlockBoundsForCurrentThread(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */           break;
/*     */         
/*     */         case 5:
/* 336 */           setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D);
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/* 341 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/* 351 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
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
/*     */   public static int getOrientation(int par0) {
/* 387 */     return par0 & 0x7;
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
/*     */   public static boolean isExtended(int par0) {
/* 400 */     return ((par0 & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int determineOrientation(World par0World, int par1, int par2, int par3, EntityLivingBase par4EntityLivingBase) {
/* 408 */     if (MathHelper.abs((float)par4EntityLivingBase.posX - par1) < 2.0F && MathHelper.abs((float)par4EntityLivingBase.posZ - par3) < 2.0F) {
/*     */       
/* 410 */       double var5 = par4EntityLivingBase.posY + 1.82D - par4EntityLivingBase.yOffset;
/*     */       
/* 412 */       if (var5 - par2 > 2.0D)
/*     */       {
/* 414 */         return 1;
/*     */       }
/*     */       
/* 417 */       if (par2 - var5 > 0.0D)
/*     */       {
/* 419 */         return 0;
/*     */       }
/*     */     } 
/*     */     
/* 423 */     int var7 = MathHelper.floor_double((par4EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 424 */     return (var7 == 0) ? 2 : ((var7 == 1) ? 5 : ((var7 == 2) ? 3 : ((var7 == 3) ? 4 : 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/* 429 */     return getDirectionFacingStandard6(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 434 */     metadata = direction.isDown() ? 0 : (direction.isUp() ? 1 : (direction.isNorth() ? 2 : (direction.isSouth() ? 3 : (direction.isWest() ? 4 : (direction.isEast() ? 5 : -1)))));
/*     */     
/* 436 */     return metadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean canPushBlock(int par0, World par1World, int par2, int par3, int par4, boolean par5) {
/* 444 */     if (par0 == Block.obsidian.blockID)
/*     */     {
/* 446 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 450 */     int metadata = par1World.getBlockMetadata(par2, par3, par4);
/*     */     
/* 452 */     if (par0 != Block.pistonBase.blockID && par0 != Block.pistonStickyBase.blockID) {
/*     */ 
/*     */       
/* 455 */       if (Block.blocksList[par0].getBlockHardness(metadata) == -1.0F)
/*     */       {
/* 457 */         return false;
/*     */       }
/*     */       
/* 460 */       if (Block.blocksList[par0].getMobilityFlag() == 2)
/*     */       {
/* 462 */         return false;
/*     */       }
/*     */       
/* 465 */       if (Block.blocksList[par0].getMobilityFlag() == 1)
/*     */       {
/* 467 */         if (!par5)
/*     */         {
/* 469 */           return false;
/*     */         }
/*     */         
/* 472 */         return true;
/*     */       }
/*     */     
/*     */     }
/* 476 */     else if (isExtended(metadata)) {
/*     */       
/* 478 */       return false;
/*     */     } 
/*     */     
/* 481 */     return !(Block.blocksList[par0] instanceof ITileEntityProvider);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean canExtend(World par0World, int par1, int par2, int par3, int par4) {
/* 490 */     int var5 = par1 + Facing.offsetsXForSide[par4];
/* 491 */     int var6 = par2 + Facing.offsetsYForSide[par4];
/* 492 */     int var7 = par3 + Facing.offsetsZForSide[par4];
/* 493 */     int var8 = 0;
/*     */     
/* 495 */     BlockInfo crushable_block_info = null;
/*     */ 
/*     */ 
/*     */     
/* 499 */     while (var8 < 13) {
/*     */       
/* 501 */       if (var6 <= 0 || var6 >= 255)
/*     */       {
/* 503 */         return false;
/*     */       }
/*     */       
/* 506 */       int var9 = par0World.getBlockId(var5, var6, var7);
/*     */       
/* 508 */       if (var9 != 0) {
/*     */         
/* 510 */         if (!canPushBlock(var9, par0World, var5, var6, var7, true))
/*     */         {
/* 512 */           return false;
/*     */         }
/*     */         
/* 515 */         Block block = Block.getBlock(var9);
/*     */         
/* 517 */         if (crushable_block_info == null && block == Block.thinGlass) {
/* 518 */           crushable_block_info = new BlockInfo(block, var5, var6, var7);
/*     */         }
/* 520 */         if (Block.blocksList[var9].getMobilityFlag() != 1) {
/*     */           
/* 522 */           if (var8 == 12) {
/*     */ 
/*     */ 
/*     */             
/* 526 */             if (crushable_block_info == null) {
/* 527 */               return false;
/*     */             }
/* 529 */             par0World.destroyBlock((new BlockBreakInfo(par0World, crushable_block_info.x, crushable_block_info.y, crushable_block_info.z)).setCrushed(Block.pistonBase), true);
/*     */             
/* 531 */             return true;
/*     */           } 
/*     */           
/* 534 */           var5 += Facing.offsetsXForSide[par4];
/* 535 */           var6 += Facing.offsetsYForSide[par4];
/* 536 */           var7 += Facing.offsetsZForSide[par4];
/* 537 */           var8++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 543 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean tryExtend(World par1World, int par2, int par3, int par4, int par5) {
/* 552 */     int var6 = par2 + Facing.offsetsXForSide[par5];
/* 553 */     int var7 = par3 + Facing.offsetsYForSide[par5];
/* 554 */     int var8 = par4 + Facing.offsetsZForSide[par5];
/* 555 */     int var9 = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 561 */     while (var9 < 13) {
/*     */       
/* 563 */       if (var7 <= 0 || var7 >= 255)
/*     */       {
/* 565 */         return false;
/*     */       }
/*     */       
/* 568 */       int i = par1World.getBlockId(var6, var7, var8);
/*     */       
/* 570 */       if (i != 0) {
/*     */         
/* 572 */         if (!canPushBlock(i, par1World, var6, var7, var8, true))
/*     */         {
/* 574 */           return false;
/*     */         }
/*     */         
/* 577 */         if (Block.blocksList[i].getMobilityFlag() != 1) {
/*     */           
/* 579 */           if (var9 == 12)
/*     */           {
/* 581 */             return false;
/*     */           }
/*     */           
/* 584 */           var6 += Facing.offsetsXForSide[par5];
/* 585 */           var7 += Facing.offsetsYForSide[par5];
/* 586 */           var8 += Facing.offsetsZForSide[par5];
/* 587 */           var9++;
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 593 */         if (!par1World.isRemote) {
/*     */           
/* 595 */           int x = var6 + Facing.offsetsXForSide[par5];
/* 596 */           int y = var7 + Facing.offsetsYForSide[par5];
/* 597 */           int z = var8 + Facing.offsetsZForSide[par5];
/*     */           
/* 599 */           Block block_beyond = par1World.getBlock(x, y, z);
/*     */           
/* 601 */           boolean was_crushed = (block_beyond != null && block_beyond.isSolid(par1World.getBlockMetadata(x, y, z)));
/*     */           
/* 603 */           BlockBreakInfo info = new BlockBreakInfo(par1World, var6, var7, var8);
/*     */           
/* 605 */           info.setDropCoords(x, y, z);
/*     */           
/* 607 */           if (was_crushed) {
/* 608 */             info.setCrushed(this);
/*     */           }
/* 610 */           info.dropBlockAsEntityItem(true);
/*     */           
/*     */           break;
/*     */         } 
/* 614 */         par1World.setBlockToAir(var6, var7, var8);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 626 */     var9 = var6;
/* 627 */     int var10 = var7;
/* 628 */     int var11 = var8;
/* 629 */     int var12 = 0;
/*     */ 
/*     */     
/*     */     int[] var13;
/*     */ 
/*     */     
/* 635 */     for (var13 = new int[13]; var6 != par2 || var7 != par3 || var8 != par4; var8 = var16) {
/*     */       
/* 637 */       int var14 = var6 - Facing.offsetsXForSide[par5];
/* 638 */       int var15 = var7 - Facing.offsetsYForSide[par5];
/* 639 */       int var16 = var8 - Facing.offsetsZForSide[par5];
/* 640 */       int var17 = par1World.getBlockId(var14, var15, var16);
/* 641 */       int var18 = par1World.getBlockMetadata(var14, var15, var16);
/*     */       
/* 643 */       if (var17 == this.blockID && var14 == par2 && var15 == par3 && var16 == par4) {
/*     */         
/* 645 */         par1World.setBlock(var6, var7, var8, Block.pistonMoving.blockID, par5 | (this.isSticky ? 8 : 0), 4);
/* 646 */         par1World.setBlockTileEntity(var6, var7, var8, BlockPistonMoving.getTileEntity(Block.pistonExtension.blockID, par5 | (this.isSticky ? 8 : 0), par5, true, false));
/*     */       }
/*     */       else {
/*     */         
/* 650 */         par1World.setBlock(var6, var7, var8, Block.pistonMoving.blockID, var18, 4);
/* 651 */         par1World.setBlockTileEntity(var6, var7, var8, BlockPistonMoving.getTileEntity(var17, var18, par5, true, false));
/*     */       } 
/*     */       
/* 654 */       var13[var12++] = var17;
/* 655 */       var6 = var14;
/* 656 */       var7 = var15;
/*     */     } 
/*     */     
/* 659 */     var6 = var9;
/* 660 */     var7 = var10;
/* 661 */     var8 = var11;
/*     */     
/* 663 */     for (var12 = 0; var6 != par2 || var7 != par3 || var8 != par4; var8 = var16) {
/*     */       
/* 665 */       int var14 = var6 - Facing.offsetsXForSide[par5];
/* 666 */       int var15 = var7 - Facing.offsetsYForSide[par5];
/* 667 */       int var16 = var8 - Facing.offsetsZForSide[par5];
/* 668 */       par1World.notifyBlocksOfNeighborChange(var14, var15, var16, var13[var12++]);
/* 669 */       var6 = var14;
/* 670 */       var7 = var15;
/*     */     } 
/*     */     
/* 673 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 679 */     item_block.addMaterial(new Material[] { Material.wood, Material.stone, Material.iron, Material.redstone });
/*     */     
/* 681 */     if (this.isSticky) {
/* 682 */       item_block.addMaterial(new Material[] { Material.slime });
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
/* 687 */     if (!isExtended(metadata)) {
/* 688 */       return true;
/*     */     }
/*     */     
/* 691 */     return (face == getBackFace(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 696 */     int metadata = block_access.getBlockMetadata(x, y, z);
/*     */     
/* 698 */     if (!isExtended(metadata)) {
/* 699 */       return true;
/*     */     }
/* 701 */     return (EnumFace.get(side) == getFrontFace(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 706 */     return !isExtended(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 711 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSupportEntityShadow(int metadata) {
/* 716 */     return (!isExtended(metadata) || getDirectionFacing(metadata).isDown());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetX(int metadata) {
/* 721 */     return (getDirectionFacing(metadata)).dx;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetY(int metadata) {
/* 726 */     return (getDirectionFacing(metadata)).dy;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetZ(int metadata) {
/* 731 */     return (getDirectionFacing(metadata)).dz;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresPartner(int metadata) {
/* 736 */     return isExtended(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartner(int metadata, Block neighbor_block, int neighbor_block_metadata) {
/* 741 */     return (neighbor_block instanceof BlockPistonExtension && ((BlockPistonExtension)neighbor_block).getDirectionFacing(neighbor_block_metadata) == getDirectionFacing(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean partnerDropsAsItem(int metadata) {
/* 746 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPistonBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */