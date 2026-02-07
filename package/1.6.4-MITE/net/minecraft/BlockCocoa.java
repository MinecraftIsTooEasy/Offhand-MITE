/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCocoa
/*     */   extends BlockMounted
/*     */ {
/*     */   private Icon[] iconArray;
/*     */   
/*     */   public BlockCocoa(int par1) {
/*  13 */     super(par1, Material.plants, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  14 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  19 */     String[] array = new String[4];
/*     */     
/*  21 */     for (int i = 0; i < array.length; i++) {
/*  22 */       array[i] = i + "=Mounted " + getDirectionOfSupportBlock(i).getDescriptor(true);
/*     */     }
/*  24 */     return StringHelper.implode(array, ", ", true, false) + ", bit 4 set if at intermediate growth stage, and bit 8 set if mature";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  29 */     return (metadata >= 0 && metadata < 12);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  37 */     return this.iconArray[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getCocoaIcon(int par1) {
/*  42 */     if (par1 < 0 || par1 >= this.iconArray.length)
/*     */     {
/*  44 */       par1 = this.iconArray.length - 1;
/*     */     }
/*     */     
/*  47 */     return this.iconArray[par1];
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDirection(int metadata) {
/*  52 */     return metadata & 0x3;
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  83 */     if (super.updateTick(world, x, y, z, random)) {
/*  84 */       return true;
/*     */     }
/*  86 */     if (world.rand.nextInt(40) == 0) {
/*     */       
/*  88 */       int metadata = world.getBlockMetadata(x, y, z);
/*  89 */       int growth = func_72219_c(metadata);
/*     */       
/*  91 */       if (growth < 2) {
/*     */         
/*  93 */         growth++;
/*  94 */         return world.setBlockMetadataWithNotify(x, y, z, growth << 2 | getDirection(metadata), 2);
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     return false;
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
/*     */   public boolean canBePlacedAt(World world, int x, int y, int z, int metadata) {
/* 115 */     Block block_above = Block.blocksList[world.getBlockId(x, y + 1, z)];
/*     */     
/* 117 */     if (block_above != Block.cocoaPlant && !BlockLeaves.isBlockUnplacedJungleLeaves(world, x, y + 1, z)) {
/* 118 */       return false;
/*     */     }
/* 120 */     boolean has_jungle_leaves_some_distance_above = false;
/*     */     
/* 122 */     for (int dy = 1; dy < 4; dy++) {
/*     */       
/* 124 */       if (BlockLeaves.isBlockUnplacedJungleLeaves(world, x, y + dy, z)) {
/*     */         
/* 126 */         has_jungle_leaves_some_distance_above = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 131 */     if (!has_jungle_leaves_some_distance_above) {
/* 132 */       return false;
/*     */     }
/* 134 */     return super.canBePlacedAt(world, x, y, z, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumFace getFaceMountedTo(int metadata) {
/* 139 */     int direction = metadata & 0x3;
/*     */     
/* 141 */     if (direction == 0)
/* 142 */       return EnumFace.NORTH; 
/* 143 */     if (direction == 1)
/* 144 */       return EnumFace.EAST; 
/* 145 */     if (direction == 2) {
/* 146 */       return EnumFace.SOUTH;
/*     */     }
/* 148 */     return EnumFace.WEST;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getDefaultMetadataForFaceMountedTo(EnumFace face) {
/* 153 */     if (face.isNorth())
/* 154 */       return 0; 
/* 155 */     if (face.isEast())
/* 156 */       return 1; 
/* 157 */     if (face.isSouth())
/* 158 */       return 2; 
/* 159 */     if (face.isWest()) {
/* 160 */       return 3;
/*     */     }
/* 162 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMountToBlock(int metadata, Block neighbor_block, int neighbor_block_metadata, EnumFace face) {
/* 167 */     return (neighbor_block == wood && neighbor_block_metadata == 3 && super.canMountToBlock(metadata, neighbor_block, neighbor_block_metadata, face));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 175 */     return 28;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 221 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/* 222 */     int var6 = getDirection(var5);
/* 223 */     int var7 = func_72219_c(var5);
/* 224 */     int var8 = 4 + var7 * 2;
/* 225 */     int var9 = 5 + var7 * 2;
/* 226 */     float var10 = var8 / 2.0F;
/*     */     
/* 228 */     switch (var6) {
/*     */       
/*     */       case 0:
/* 231 */         setBlockBoundsForCurrentThread(((8.0F - var10) / 16.0F), ((12.0F - var9) / 16.0F), ((15.0F - var8) / 16.0F), ((8.0F + var10) / 16.0F), 0.75D, 0.9375D);
/*     */         break;
/*     */       
/*     */       case 1:
/* 235 */         setBlockBoundsForCurrentThread(0.0625D, ((12.0F - var9) / 16.0F), ((8.0F - var10) / 16.0F), ((1.0F + var8) / 16.0F), 0.75D, ((8.0F + var10) / 16.0F));
/*     */         break;
/*     */       
/*     */       case 2:
/* 239 */         setBlockBoundsForCurrentThread(((8.0F - var10) / 16.0F), ((12.0F - var9) / 16.0F), 0.0625D, ((8.0F + var10) / 16.0F), 0.75D, ((1.0F + var8) / 16.0F));
/*     */         break;
/*     */       
/*     */       case 3:
/* 243 */         setBlockBoundsForCurrentThread(((15.0F - var8) / 16.0F), ((12.0F - var9) / 16.0F), ((8.0F - var10) / 16.0F), 0.9375D, 0.75D, ((8.0F + var10) / 16.0F));
/*     */         break;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int func_72219_c(int par0) {
/* 290 */     return (par0 & 0xC) >> 2;
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
/*     */   public boolean canBeCarried() {
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 332 */     int growth = func_72219_c(info.getMetadata());
/*     */     
/* 334 */     if (growth < 2) {
/* 335 */       return 0;
/*     */     }
/* 337 */     return dropBlockAsEntityItem(info, Item.dyePowder.itemID, 3, 1, 1.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 345 */     return Item.dyePowder.itemID;
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 367 */     this.iconArray = new Icon[3];
/*     */     
/* 369 */     for (int var2 = 0; var2 < this.iconArray.length; var2++)
/*     */     {
/* 371 */       this.iconArray[var2] = par1IconRegister.registerIcon(getTextureName() + "_stage_" + var2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 377 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockCocoa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */