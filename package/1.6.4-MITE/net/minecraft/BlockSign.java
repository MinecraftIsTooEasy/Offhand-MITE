/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSign
/*     */   extends BlockMountedWithTileEntity
/*     */ {
/*     */   private Class signEntityClass;
/*     */   private boolean isFreestanding;
/*     */   
/*     */   protected BlockSign(int par1, Class par2Class, boolean par3) {
/*  16 */     super(par1, Material.wood, par2Class, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  17 */     this.isFreestanding = par3;
/*  18 */     this.signEntityClass = par2Class;
/*  19 */     float var4 = 0.25F;
/*  20 */     float var5 = 1.0F;
/*  21 */     setBlockBoundsForAllThreads((0.5F - var4), 0.0D, (0.5F - var4), (0.5F + var4), var5, (0.5F + var4));
/*  22 */     setHardness(0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  27 */     String[] array = new String[4];
/*     */     
/*  29 */     for (int i = 0; i < array.length; i++) {
/*  30 */       array[i] = (i + 2) + "=Mounted " + getDirectionOfSupportBlock(i + 2).getDescriptor(true);
/*     */     }
/*  32 */     return this.isFreestanding ? "All bits used for yaw" : StringHelper.implode(array, ", ", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  37 */     return this.isFreestanding ? ((metadata >= 0 && metadata < 16)) : ((metadata > 1 && metadata < 6));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  45 */     return Block.planks.getBlockTextureFromSide(par1);
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  71 */     if (!this.isFreestanding) {
/*     */       
/*  73 */       int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*  74 */       float var6 = 0.28125F;
/*  75 */       float var7 = 0.78125F;
/*  76 */       float var8 = 0.0F;
/*  77 */       float var9 = 1.0F;
/*  78 */       float var10 = 0.125F;
/*  79 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */       
/*  81 */       if (var5 == 2)
/*     */       {
/*  83 */         setBlockBoundsForCurrentThread(var8, var6, (1.0F - var10), var9, var7, 1.0D);
/*     */       }
/*     */       
/*  86 */       if (var5 == 3)
/*     */       {
/*  88 */         setBlockBoundsForCurrentThread(var8, var6, 0.0D, var9, var7, var10);
/*     */       }
/*     */       
/*  91 */       if (var5 == 4)
/*     */       {
/*  93 */         setBlockBoundsForCurrentThread((1.0F - var10), var6, var8, 1.0D, var7, var9);
/*     */       }
/*     */       
/*  96 */       if (var5 == 5)
/*     */       {
/*  98 */         setBlockBoundsForCurrentThread(0.0D, var6, var8, var10, var7, var9);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 108 */     return -1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumFace getFaceMountedTo(int metadata) {
/* 212 */     if (this.isFreestanding) {
/* 213 */       return EnumFace.TOP;
/*     */     }
/* 215 */     int orientation = metadata;
/*     */     
/* 217 */     if (orientation == 2)
/* 218 */       return EnumFace.NORTH; 
/* 219 */     if (orientation == 3)
/* 220 */       return EnumFace.SOUTH; 
/* 221 */     if (orientation == 4)
/* 222 */       return EnumFace.WEST; 
/* 223 */     if (orientation == 5) {
/* 224 */       return EnumFace.EAST;
/*     */     }
/* 226 */     Minecraft.setErrorMessage("getFaceMountedTo: invalid orientation " + orientation);
/* 227 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getDefaultMetadataForFaceMountedTo(EnumFace face) {
/* 232 */     if (this.isFreestanding) {
/* 233 */       return 0;
/*     */     }
/* 235 */     if (face.isNorth())
/* 236 */       return 2; 
/* 237 */     if (face.isSouth())
/* 238 */       return 3; 
/* 239 */     if (face.isWest())
/* 240 */       return 4; 
/* 241 */     if (face.isEast()) {
/* 242 */       return 5;
/*     */     }
/* 244 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 249 */     if (this.isFreestanding) {
/* 250 */       return entity.getRotationYawAsSixteenths() + 8 & 0xF;
/*     */     }
/* 252 */     return getDefaultMetadataForFaceMountedTo(face);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 260 */     return Item.sign.itemID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 265 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 276 */     return info.wasExploded() ? dropBlockAsEntityItem(info, Item.stick) : dropBlockAsEntityItem(info, Item.sign);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 281 */     if (!test_only && placer instanceof EntityPlayer) {
/*     */       
/* 283 */       EntityPlayer player = (EntityPlayer)placer;
/*     */       
/* 285 */       if (player.onServer()) {
/*     */         
/* 287 */         TileEntitySign tile_entity = (TileEntitySign)world.getBlockTileEntity(x, y, z);
/*     */         
/* 289 */         if (tile_entity != null) {
/* 290 */           player.displayGUIEditSign(tile_entity);
/*     */         }
/*     */       } 
/*     */     } 
/* 294 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 299 */     return this.isFreestanding ? "standing" : "mounted";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 304 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 309 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSign.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */