/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockHopper
/*     */   extends BlockContainer
/*     */ {
/*   8 */   private final Random field_94457_a = new Random();
/*     */   
/*     */   private Icon hopperIcon;
/*     */   private Icon hopperTopIcon;
/*     */   private Icon hopperInsideIcon;
/*  13 */   private static final AxisAlignedBB[] multiple_bounds = getMultipleBounds();
/*  14 */   private static final AxisAlignedBB[] multiple_bounds_for_player_selection = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockHopper(int par1) {
/*  19 */     super(par1, Material.iron, new BlockConstants());
/*  20 */     setCreativeTab(CreativeTabs.tabRedstone);
/*  21 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  26 */     return "0=Down, 2=North, 3=South, 4=West, 5=East";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  31 */     return (metadata >= 0 && metadata < 6 && metadata != 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  39 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   private static AxisAlignedBB[] getMultipleBounds() {
/*  44 */     float min_y = 0.0F;
/*  45 */     float var8 = 0.125F;
/*     */     
/*  47 */     AxisAlignedBB[] multiple_bounds = { new AxisAlignedBB(0.0D, min_y, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, min_y, 0.0D, var8, 1.0D, 1.0D), new AxisAlignedBB(0.0D, min_y, 0.0D, 1.0D, 1.0D, var8), new AxisAlignedBB((1.0F - var8), min_y, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, min_y, (1.0F - var8), 1.0D, 1.0D, 1.0D) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     return multiple_bounds;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/*  61 */     return multiple_bounds;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastCollision tryRaycastVsBlock(Raycast raycast, int x, int y, int z, Vec3 origin, Vec3 limit) {
/*  66 */     return raycast.isForPlayerSelection() ? tryRaycastVsStandardFormBounds(raycast, x, y, z, origin, limit) : super.tryRaycastVsBlock(raycast, x, y, z, origin, limit);
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
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  86 */     if (face.isTopOrBottom())
/*  87 */       return 0; 
/*  88 */     if (face.isSouth())
/*  89 */       return 2; 
/*  90 */     if (face.isNorth())
/*  91 */       return 3; 
/*  92 */     if (face.isEast()) {
/*  93 */       return 4;
/*     */     }
/*  95 */     return 5;
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
/*     */   public TileEntity createNewTileEntity(World par1World) {
/* 120 */     return new TileEntityHopper();
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
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 142 */     super.onBlockAdded(par1World, par2, par3, par4);
/* 143 */     updateMetadata(par1World, par2, par3, par4);
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 170 */     if (world.isBlockFaceFlatAndSolid(x, y + 1, z, EnumFace.BOTTOM)) {
/* 171 */       return false;
/*     */     }
/* 173 */     if (player.onServer()) {
/*     */       
/* 175 */       TileEntityHopper tile_entity = (TileEntityHopper)world.getBlockTileEntity(x, y, z);
/*     */       
/* 177 */       if (tile_entity != null) {
/* 178 */         player.displayGUIHopper(tile_entity);
/*     */       }
/*     */     } 
/* 181 */     return true;
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 195 */     return updateMetadata(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean updateMetadata(World par1World, int par2, int par3, int par4) {
/* 204 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/* 205 */     int var6 = getDirectionFromMetadata(var5);
/* 206 */     boolean var7 = !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4);
/* 207 */     boolean var8 = getIsBlockNotPoweredFromMetadata(var5);
/*     */     
/* 209 */     if (var7 != var8) {
/*     */       
/* 211 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | (var7 ? 0 : 8), 4);
/* 212 */       return true;
/*     */     } 
/*     */     
/* 215 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 225 */     TileEntityHopper var7 = (TileEntityHopper)par1World.getBlockTileEntity(par2, par3, par4);
/*     */     
/* 227 */     if (var7 != null) {
/*     */       
/* 229 */       for (int var8 = 0; var8 < var7.getSizeInventory(); var8++) {
/*     */         
/* 231 */         ItemStack var9 = var7.getStackInSlot(var8);
/*     */         
/* 233 */         if (var9 != null) {
/*     */           
/* 235 */           float var10 = this.field_94457_a.nextFloat() * 0.8F + 0.1F;
/* 236 */           float var11 = this.field_94457_a.nextFloat() * 0.8F + 0.1F;
/* 237 */           float var12 = this.field_94457_a.nextFloat() * 0.8F + 0.1F;
/*     */           
/* 239 */           while (var9.stackSize > 0) {
/*     */             
/* 241 */             int var13 = this.field_94457_a.nextInt(21) + 10;
/*     */             
/* 243 */             if (var13 > var9.stackSize)
/*     */             {
/* 245 */               var13 = var9.stackSize;
/*     */             }
/*     */             
/* 248 */             var9.stackSize -= var13;
/*     */             
/* 250 */             EntityItem var14 = new EntityItem(par1World, (par2 + var10), (par3 + var11), (par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemSubtype()));
/*     */             
/* 252 */             if (var9.isItemDamaged()) {
/* 253 */               var14.getEntityItem().setItemDamage(var9.getItemDamage());
/*     */             }
/* 255 */             if (var9.getItem().hasQuality()) {
/* 256 */               var14.getEntityItem().setQuality(var9.getQuality());
/*     */             }
/* 258 */             if (var9.hasTagCompound())
/*     */             {
/* 260 */               var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
/*     */             }
/*     */             
/* 263 */             float var15 = 0.05F;
/* 264 */             var14.motionX = ((float)this.field_94457_a.nextGaussian() * var15);
/* 265 */             var14.motionY = ((float)this.field_94457_a.nextGaussian() * var15 + 0.2F);
/* 266 */             var14.motionZ = ((float)this.field_94457_a.nextGaussian() * var15);
/* 267 */             par1World.spawnEntityInWorld(var14);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 272 */       par1World.func_96440_m(par2, par3, par4, par5);
/*     */     } 
/*     */     
/* 275 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 283 */     return 38;
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
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 309 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 317 */     return (par1 == 1) ? this.hopperTopIcon : this.hopperIcon;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDirectionFromMetadata(int par0) {
/* 322 */     return par0 & 0x7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getIsBlockNotPoweredFromMetadata(int par0) {
/* 327 */     return ((par0 & 0x8) != 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 336 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 345 */     return Container.calcRedstoneFromInventory(getHopperTile(par1World, par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 354 */     this.hopperIcon = par1IconRegister.registerIcon("hopper_outside");
/* 355 */     this.hopperTopIcon = par1IconRegister.registerIcon("hopper_top");
/* 356 */     this.hopperInsideIcon = par1IconRegister.registerIcon("hopper_inside");
/*     */   }
/*     */ 
/*     */   
/*     */   public static Icon getHopperIcon(String par0Str) {
/* 361 */     return par0Str.equals("hopper_outside") ? Block.hopperBlock.hopperIcon : (par0Str.equals("hopper_inside") ? Block.hopperBlock.hopperInsideIcon : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemIconName() {
/* 369 */     return "hopper";
/*     */   }
/*     */ 
/*     */   
/*     */   public static TileEntityHopper getHopperTile(IBlockAccess par0IBlockAccess, int par1, int par2, int par3) {
/* 374 */     return (TileEntityHopper)par0IBlockAccess.getBlockTileEntity(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 384 */     return (side == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 389 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 394 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockHopper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */