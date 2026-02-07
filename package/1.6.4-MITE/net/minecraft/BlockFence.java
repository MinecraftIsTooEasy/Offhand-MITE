/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFence
/*     */   extends Block
/*     */ {
/*     */   private final String field_94464_a;
/*     */   
/*     */   public BlockFence(int par1, String par2Str, Material par3Material) {
/*  12 */     super(par1, par3Material, (new BlockConstants()).setNeverHidesAdjacentFaces().setAlwaysConnectsWithFence());
/*  13 */     this.field_94464_a = par2Str;
/*  14 */     setMaxStackSize(8);
/*  15 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/*  25 */     if (entity instanceof EntityPudding) {
/*     */       
/*  27 */       AxisAlignedBB[] bounds = (AxisAlignedBB[])getCollisionBounds(world, x, y, z, (Entity)null);
/*     */       
/*  29 */       for (int i = 0; i < bounds.length; i++) {
/*     */         
/*  31 */         if (bounds[i] != null) {
/*  32 */           bounds[i].setMaxY(y + 1.5D);
/*     */         }
/*     */       } 
/*  35 */       return bounds;
/*     */     } 
/*     */     
/*  38 */     if (useFullBlockForCollisions(entity))
/*     */     {
/*  40 */       return AxisAlignedBB.getBoundingBoxFromPool(x, y, z, 0.0D, 0.0D, 0.0D, 1.0D, 1.5D, 1.0D);
/*     */     }
/*  42 */     AxisAlignedBB[] bb = new AxisAlignedBB[3];
/*  43 */     int index = 0;
/*     */     
/*  45 */     boolean var8 = canConnectFenceTo(world, x, y, z - 1);
/*  46 */     boolean var9 = canConnectFenceTo(world, x, y, z + 1);
/*  47 */     boolean var10 = canConnectFenceTo(world, x - 1, y, z);
/*  48 */     boolean var11 = canConnectFenceTo(world, x + 1, y, z);
/*     */     
/*  50 */     float min_x = var10 ? 0.0F : 0.375F;
/*  51 */     float max_x = var11 ? 1.0F : 0.625F;
/*  52 */     float min_z = var8 ? 0.0F : 0.375F;
/*  53 */     float max_z = var9 ? 1.0F : 0.625F;
/*     */     
/*  55 */     if (!var8 && !var9 && !var10 && !var11) {
/*     */       
/*  57 */       bb[index++] = AxisAlignedBB.getBoundingBoxFromPool(min_x, 0.0D, min_z, max_x, 1.0D, max_z);
/*     */     }
/*     */     else {
/*     */       
/*  61 */       if (var8 || var9) {
/*  62 */         bb[index++] = AxisAlignedBB.getBoundingBoxFromPool(0.375D, 0.0D, min_z, 0.625D, 1.0D, max_z);
/*     */       }
/*  64 */       if (var10 || var11) {
/*  65 */         bb[index++] = AxisAlignedBB.getBoundingBoxFromPool(min_x, 0.0D, 0.375D, max_x, 1.0D, 0.625D);
/*     */       }
/*     */     } 
/*  68 */     return bb;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 157 */     boolean var5 = canConnectFenceTo(par1IBlockAccess, par2, par3, par4 - 1);
/* 158 */     boolean var6 = canConnectFenceTo(par1IBlockAccess, par2, par3, par4 + 1);
/* 159 */     boolean var7 = canConnectFenceTo(par1IBlockAccess, par2 - 1, par3, par4);
/* 160 */     boolean var8 = canConnectFenceTo(par1IBlockAccess, par2 + 1, par3, par4);
/* 161 */     float var9 = 0.375F;
/* 162 */     float var10 = 0.625F;
/* 163 */     float var11 = 0.375F;
/* 164 */     float var12 = 0.625F;
/*     */     
/* 166 */     if (var5)
/*     */     {
/* 168 */       var11 = 0.0F;
/*     */     }
/*     */     
/* 171 */     if (var6)
/*     */     {
/* 173 */       var12 = 1.0F;
/*     */     }
/*     */     
/* 176 */     if (var7)
/*     */     {
/* 178 */       var9 = 0.0F;
/*     */     }
/*     */     
/* 181 */     if (var8)
/*     */     {
/* 183 */       var10 = 1.0F;
/*     */     }
/*     */     
/* 186 */     setBlockBoundsForCurrentThread(var9, 0.0D, var11, var10, 1.0D, var12);
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
/*     */   public int getRenderType() {
/* 220 */     return 11;
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
/*     */   public boolean canConnectFenceTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 240 */     return Block.doesBlockConnectWithFence(par1IBlockAccess, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isIdAFence(int par0) {
/* 245 */     return (par0 == Block.fence.blockID || par0 == Block.netherFence.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 254 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 263 */     this.blockIcon = par1IconRegister.registerIcon(this.field_94464_a);
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 277 */     return (player.canPlayerEdit(x, y, z, player.getHeldItemStack()) && ItemLeash.tryTieingLeashedEntitiesToBlock(player, x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 282 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */