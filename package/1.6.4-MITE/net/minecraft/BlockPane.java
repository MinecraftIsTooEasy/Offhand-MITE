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
/*     */ 
/*     */ 
/*     */ public class BlockPane
/*     */   extends Block
/*     */ {
/*     */   private final String sideTextureIndex;
/*     */   private final boolean canDropItself;
/*     */   private final String field_94402_c;
/*     */   private Icon theIcon;
/*     */   
/*     */   protected BlockPane(int par1, String par2Str, String par3Str, Material par4Material, boolean par5) {
/*  24 */     super(par1, par4Material, (new BlockConstants()).setNeverHidesAdjacentFaces());
/*  25 */     this.sideTextureIndex = par3Str;
/*  26 */     this.canDropItself = par5;
/*  27 */     this.field_94402_c = par2Str;
/*  28 */     setMaxStackSize(16);
/*  29 */     setCreativeTab(CreativeTabs.tabDecorations);
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
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/*  45 */     if (this == Block.thinGlass) {
/*  46 */       return dropBlockAsEntityItem(info, Item.shardGlass);
/*     */     }
/*  48 */     return this.canDropItself ? super.dropBlockAsEntityItem(info) : 0;
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
/*     */   public int getRenderType() {
/*  73 */     return 18;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  82 */     int var6 = par1IBlockAccess.getBlockId(par2, par3, par4);
/*  83 */     return (var6 == this.blockID) ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/*  88 */     if (useFullBlockForCollisions(entity)) {
/*  89 */       return getStandardFormBoundingBoxFromPool(x, y, z);
/*     */     }
/*  91 */     AxisAlignedBB[] bb = new AxisAlignedBB[3];
/*  92 */     int index = 0;
/*     */     
/*  94 */     boolean var8 = canThisPaneConnectToThisBlockID(world.getBlockId(x, y, z - 1));
/*  95 */     boolean var9 = canThisPaneConnectToThisBlockID(world.getBlockId(x, y, z + 1));
/*  96 */     boolean var10 = canThisPaneConnectToThisBlockID(world.getBlockId(x - 1, y, z));
/*  97 */     boolean var11 = canThisPaneConnectToThisBlockID(world.getBlockId(x + 1, y, z));
/*     */     
/*  99 */     if ((!var10 || !var11) && (var10 || var11 || var8 || var9)) {
/*     */       
/* 101 */       if (var10 && !var11) {
/* 102 */         bb[index++] = AxisAlignedBB.getBoundingBoxFromPool(0.0D, 0.0D, 0.4375D, 0.5D, 1.0D, 0.5625D);
/* 103 */       } else if (!var10 && var11) {
/* 104 */         bb[index++] = AxisAlignedBB.getBoundingBoxFromPool(0.5D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D);
/*     */       } 
/*     */     } else {
/*     */       
/* 108 */       bb[index++] = AxisAlignedBB.getBoundingBoxFromPool(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D);
/*     */     } 
/*     */     
/* 111 */     if ((!var8 || !var9) && (var10 || var11 || var8 || var9)) {
/*     */       
/* 113 */       if (var8 && !var9) {
/* 114 */         bb[index++] = AxisAlignedBB.getBoundingBoxFromPool(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5D);
/* 115 */       } else if (!var8 && var9) {
/* 116 */         bb[index++] = AxisAlignedBB.getBoundingBoxFromPool(0.4375D, 0.0D, 0.5D, 0.5625D, 1.0D, 1.0D);
/*     */       } 
/*     */     } else {
/*     */       
/* 120 */       bb[index++] = AxisAlignedBB.getBoundingBoxFromPool(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D);
/*     */     } 
/*     */     
/* 123 */     return bb;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/* 197 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 205 */     float var5 = 0.4375F;
/* 206 */     float var6 = 0.5625F;
/* 207 */     float var7 = 0.4375F;
/* 208 */     float var8 = 0.5625F;
/* 209 */     boolean var9 = canThisPaneConnectToThisBlockID(par1IBlockAccess.getBlockId(par2, par3, par4 - 1));
/* 210 */     boolean var10 = canThisPaneConnectToThisBlockID(par1IBlockAccess.getBlockId(par2, par3, par4 + 1));
/* 211 */     boolean var11 = canThisPaneConnectToThisBlockID(par1IBlockAccess.getBlockId(par2 - 1, par3, par4));
/* 212 */     boolean var12 = canThisPaneConnectToThisBlockID(par1IBlockAccess.getBlockId(par2 + 1, par3, par4));
/*     */     
/* 214 */     if ((!var11 || !var12) && (var11 || var12 || var9 || var10)) {
/*     */       
/* 216 */       if (var11 && !var12)
/*     */       {
/* 218 */         var5 = 0.0F;
/*     */       }
/* 220 */       else if (!var11 && var12)
/*     */       {
/* 222 */         var6 = 1.0F;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 227 */       var5 = 0.0F;
/* 228 */       var6 = 1.0F;
/*     */     } 
/*     */     
/* 231 */     if ((!var9 || !var10) && (var11 || var12 || var9 || var10)) {
/*     */       
/* 233 */       if (var9 && !var10)
/*     */       {
/* 235 */         var7 = 0.0F;
/*     */       }
/* 237 */       else if (!var9 && var10)
/*     */       {
/* 239 */         var8 = 1.0F;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 244 */       var7 = 0.0F;
/* 245 */       var8 = 1.0F;
/*     */     } 
/*     */     
/* 248 */     setBlockBoundsForCurrentThread(var5, 0.0D, var7, var6, 1.0D, var8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getSideTextureIndex() {
/* 256 */     return this.theIcon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean canThisPaneConnectToThisBlockID(int par1) {
/* 265 */     return (Block.opaqueCubeLookup[par1] || par1 == this.blockID || par1 == Block.glass.blockID);
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 292 */     this.blockIcon = par1IconRegister.registerIcon(this.field_94402_c);
/* 293 */     this.theIcon = par1IconRegister.registerIcon(this.sideTextureIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 298 */     if (!par1World.isRemote)
/*     */     {
/* 300 */       if (this == thinGlass)
/*     */       {
/* 302 */         if (par5Entity instanceof EntityArrow) {
/*     */           
/* 304 */           EntityArrow arrow = (EntityArrow)par5Entity;
/*     */           
/* 306 */           if (arrow.speed_before_collision_sq > 4.0F) {
/* 307 */             par1World.destroyBlock(new BlockBreakInfo(par1World, par2, par3, par4), true);
/*     */           }
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 315 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */