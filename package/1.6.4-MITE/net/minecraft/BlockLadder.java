/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockLadder
/*     */   extends BlockMounted
/*     */ {
/*     */   protected BlockLadder(int par1) {
/*  11 */     super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  12 */     setHardness(0.5F);
/*  13 */     setMaxStackSize(8);
/*  14 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  19 */     String[] array = new String[4];
/*     */     
/*  21 */     for (int i = 0; i < array.length; i++) {
/*  22 */       array[i] = (i + 2) + "=Mounted " + getDirectionOfSupportBlock(i + 2).getDescriptor(true);
/*     */     }
/*  24 */     return StringHelper.implode(array, ", ", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  29 */     return (metadata > 1 && metadata < 6);
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  56 */     updateLadderBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateLadderBounds(int par1) {
/*  64 */     float var3 = 0.125F;
/*     */     
/*  66 */     if (par1 == 2)
/*     */     {
/*  68 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, (1.0F - var3), 1.0D, 1.0D, 1.0D);
/*     */     }
/*     */     
/*  71 */     if (par1 == 3)
/*     */     {
/*  73 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, var3);
/*     */     }
/*     */     
/*  76 */     if (par1 == 4)
/*     */     {
/*  78 */       setBlockBoundsForCurrentThread((1.0F - var3), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */     }
/*     */     
/*  81 */     if (par1 == 5)
/*     */     {
/*  83 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, var3, 1.0D, 1.0D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getRenderType() {
/* 112 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumFace getFaceMountedTo(int metadata) {
/* 117 */     int orientation = metadata;
/*     */     
/* 119 */     if (orientation == 2)
/* 120 */       return EnumFace.NORTH; 
/* 121 */     if (orientation == 3)
/* 122 */       return EnumFace.SOUTH; 
/* 123 */     if (orientation == 4)
/* 124 */       return EnumFace.WEST; 
/* 125 */     if (orientation == 5) {
/* 126 */       return EnumFace.EAST;
/*     */     }
/* 128 */     Minecraft.setErrorMessage("getFaceMountedTo: invalid orientation " + orientation);
/* 129 */     return null;
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
/*     */   public final int getDefaultMetadataForFaceMountedTo(EnumFace face) {
/* 172 */     if (face.isEast())
/* 173 */       return 5; 
/* 174 */     if (face.isWest())
/* 175 */       return 4; 
/* 176 */     if (face.isSouth())
/* 177 */       return 3; 
/* 178 */     if (face.isNorth()) {
/* 179 */       return 2;
/*     */     }
/* 181 */     return -1;
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
/*     */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 233 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 238 */     item_block.addMaterial(new Material[] { Material.wood });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockLadder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */