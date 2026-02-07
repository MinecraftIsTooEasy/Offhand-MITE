/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockSlab
/*     */   extends Block
/*     */ {
/*     */   public BlockSlab(int id, Material material) {
/*  12 */     super(id, material, new BlockConstants());
/*     */     
/*  14 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*  15 */     setMaxStackSize(8);
/*     */     
/*  17 */     setLightOpacity(255);
/*  18 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */     
/*  20 */     setUnlocalizedName("slab.group" + getGroup());
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getGroup();
/*     */ 
/*     */   
/*     */   public abstract String[] getTypes();
/*     */ 
/*     */   
/*     */   public abstract Block getModelBlock(int paramInt);
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
/*     */   
/*     */   public Icon getIcon(int side, int metadata) {
/*  35 */     Block model_block = getModelBlock(metadata);
/*     */     
/*  37 */     return model_block.getIcon(side, model_block.getBlockSubtypeUnchecked(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess block_access, int x, int y, int z) {
/*  42 */     if (isTop(block_access.getBlockMetadata(x, y, z))) {
/*  43 */       setBlockBoundsForCurrentThread(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */     } else {
/*  45 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/*  50 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
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
/*  70 */     int metadata = item_stack.getItemSubtype();
/*     */     
/*  72 */     if (face == EnumFace.BOTTOM || (face != EnumFace.TOP && offset_y > 0.5F)) {
/*  73 */       return metadata | 0x8;
/*     */     }
/*  75 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  80 */     return "Bits 1, 2, and 4 used for subtype, bit 8 set if slab is top";
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getBlockSubtypeUnchecked(int paramInt);
/*     */   
/*     */   public static boolean isBottom(int metadata) {
/*  87 */     return ((metadata & 0x8) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isTop(int metadata) {
/*  92 */     return !isBottom(metadata);
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
/*     */   public String getFullSlabName(int metadata) {
/* 118 */     return getUnlocalizedName() + "." + getTypes()[getItemSubtype(metadata)];
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 123 */     if (info.wasExploded()) {
/* 124 */       return 0;
/*     */     }
/* 126 */     return dropBlockAsEntityItem(info, createStackedBlock(info.getMetadata()));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 131 */     return "single";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
/* 136 */     if (isBottom(metadata)) {
/* 137 */       return face.isBottom();
/*     */     }
/* 139 */     return face.isTop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockHardness(int metadata) {
/* 146 */     Block model_block = getModelBlock(metadata);
/*     */     
/* 148 */     float hardness = 0.0F;
/*     */     
/* 150 */     if (model_block == stone) {
/* 151 */       hardness = 2.4F;
/* 152 */     } else if (model_block == sandStone) {
/* 153 */       hardness = 1.0F;
/* 154 */     } else if (model_block == planks) {
/* 155 */       hardness = 0.8F;
/* 156 */     } else if (model_block == cobblestone) {
/* 157 */       hardness = 2.0F;
/* 158 */     } else if (model_block == brick) {
/* 159 */       hardness = 2.0F;
/* 160 */     } else if (model_block == stoneBrick) {
/* 161 */       hardness = 2.0F;
/* 162 */     } else if (model_block == netherBrick) {
/* 163 */       hardness = 2.0F;
/* 164 */     } else if (model_block == blockNetherQuartz) {
/* 165 */       hardness = 0.8F;
/* 166 */     } else if (model_block == obsidian) {
/* 167 */       hardness = 2.4F;
/*     */     } else {
/* 169 */       Minecraft.setErrorMessage("getBlockHardness: unhandled model block " + model_block);
/*     */     } 
/* 171 */     return hardness * 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinHarvestLevel(int metadata) {
/* 178 */     return (getModelBlock(metadata) == sandStone) ? 1 : super.getMinHarvestLevel(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public void getItemStacks(int id, CreativeTabs creative_tabs, List list) {
/* 183 */     super.getItemStacks(id, creative_tabs, list);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 188 */     boolean is_bottom = isBottom(block_access.getBlockMetadata(x, y, z));
/*     */     
/* 190 */     if (side == 1)
/*     */     {
/* 192 */       return is_bottom;
/*     */     }
/* 194 */     if (side == 0)
/*     */     {
/* 196 */       return !is_bottom;
/*     */     }
/* 198 */     if (neighbor instanceof BlockSlab) {
/*     */       
/* 200 */       EnumFace face = EnumFace.get(side).getOpposite();
/*     */       
/* 202 */       return (isBottom(block_access.getBlockMetadata(face.getNeighborX(x), y, face.getNeighborZ(z))) == is_bottom);
/*     */     } 
/*     */ 
/*     */     
/* 206 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 217 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSlab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */