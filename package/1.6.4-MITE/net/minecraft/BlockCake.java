/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCake
/*     */   extends Block
/*     */ {
/*     */   private Icon cakeTopIcon;
/*     */   private Icon cakeBottomIcon;
/*     */   private Icon field_94382_c;
/*     */   
/*     */   protected BlockCake(int par1) {
/*  14 */     super(par1, Material.cake, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  15 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  20 */     return "Metadata equals number of slices eaten";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  25 */     return (metadata >= 0 && metadata < getMaxSlices());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  33 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/*  34 */     float var6 = 0.0625F;
/*  35 */     float var7 = (1 + var5 * 2) / 16.0F;
/*  36 */     float var8 = 0.5F;
/*  37 */     setBlockBoundsForCurrentThread(var7, 0.0D, var6, (1.0F - var6), var8, (1.0F - var6));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/*  46 */     float var1 = 0.0625F;
/*  47 */     float var2 = 0.5F;
/*  48 */     setBlockBoundsForCurrentThread(var1, 0.0D, var1, (1.0F - var1), var2, (1.0F - var1));
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
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/*  66 */     int var5 = world.getBlockMetadata(x, y, z);
/*  67 */     float var6 = 0.0625F;
/*  68 */     float var7 = (1 + var5 * 2) / 16.0F;
/*  69 */     float var8 = 0.5F;
/*  70 */     return AxisAlignedBB.getAABBPool().getAABB((x + var7), y, (z + var6), ((x + 1) - var6), (y + var8 - var6), ((z + 1) - var6));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
/*  78 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/*  79 */     float var6 = 0.0625F;
/*  80 */     float var7 = (1 + var5 * 2) / 16.0F;
/*  81 */     float var8 = 0.5F;
/*  82 */     return AxisAlignedBB.getAABBPool().getAABB((par2 + var7), par3, (par4 + var6), ((par2 + 1) - var6), (par3 + var8), ((par4 + 1) - var6));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  90 */     return (par1 == 1) ? this.cakeTopIcon : ((par1 == 0) ? this.cakeBottomIcon : ((par2 > 0 && par1 == 4) ? this.field_94382_c : this.blockIcon));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  99 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/* 100 */     this.field_94382_c = par1IconRegister.registerIcon(getTextureName() + "_inner");
/* 101 */     this.cakeTopIcon = par1IconRegister.registerIcon(getTextureName() + "_top");
/* 102 */     this.cakeBottomIcon = par1IconRegister.registerIcon(getTextureName() + "_bottom");
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 133 */     return tryEatCakeSlice(world, x, y, z, player);
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
/*     */   public static final int getMaxSlices() {
/* 169 */     return 6;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean tryEatCakeSlice(World world, int x, int y, int z, EntityPlayer player) {
/* 174 */     ItemFood cake_slice = (new ItemFood(2, 2, 1000 / getMaxSlices(), true, MITEConstant.egg_has_essential_fats, false)).setPlantProduct().setAnimalProduct();
/*     */     
/* 176 */     if (player.canIngest(cake_slice, 0)) {
/*     */       
/* 178 */       if (player.onServer()) {
/*     */         
/* 180 */         player.addFoodValue(cake_slice);
/* 181 */         world.playSoundAtEntity(player, "random.burp", 0.5F, player.rand.nextFloat() * 0.1F + 0.9F);
/*     */         
/* 183 */         int slices_consumed = world.getBlockMetadata(x, y, z) + 1;
/*     */         
/* 185 */         if (slices_consumed >= getMaxSlices()) {
/* 186 */           world.setBlockToAir(x, y, z);
/*     */         } else {
/* 188 */           world.setBlockMetadataWithNotify(x, y, z, slices_consumed, 2);
/*     */         } 
/*     */       } 
/* 191 */       return true;
/*     */     } 
/*     */     
/* 194 */     return false;
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
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 234 */     return (block_below != null && block_below.isTopFlatAndSolid(block_below_metadata));
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
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 258 */     return Item.cake.itemID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 269 */     return (info.getMetadata() == 0) ? dropBlockAsEntityItem(info, Item.cake) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 279 */     return (metadata == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockCake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */