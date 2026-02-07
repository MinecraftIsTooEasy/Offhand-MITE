/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockEndPortalFrame
/*     */   extends Block
/*     */ {
/*     */   private Icon field_94400_a;
/*     */   private Icon field_94399_b;
/*  11 */   private static final AxisAlignedBB[] multiple_bounds_without_eye = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D) };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  16 */   private static final AxisAlignedBB[] multiple_bounds_with_eye = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D), new AxisAlignedBB(0.25D, 0.8125D, 0.25D, 0.75D, 1.0D, 0.75D) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockEndPortalFrame(int par1) {
/*  25 */     super(par1, Material.stone, (new BlockConstants()).setAlwaysImmutable());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  30 */     return "Bits 1 and 2 used for orientation, bit 4 set if ender eye inserted";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  35 */     return (metadata >= 0 && metadata < 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  43 */     return (par1 == 1) ? this.field_94400_a : ((par1 == 0) ? Block.whiteStone.getBlockTextureFromSide(par1) : this.blockIcon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  52 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/*  53 */     this.field_94400_a = par1IconRegister.registerIcon(getTextureName() + "_top");
/*  54 */     this.field_94399_b = par1IconRegister.registerIcon(getTextureName() + "_eye");
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon func_94398_p() {
/*  59 */     return this.field_94399_b;
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
/*     */   public int getRenderType() {
/*  76 */     return 26;
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
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/*  90 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
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
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/* 117 */     return isEnderEyeInserted(world.getBlockMetadata(x, y, z)) ? multiple_bounds_with_eye : multiple_bounds_without_eye;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEnderEyeInserted(int par0) {
/* 125 */     return ((par0 & 0x4) != 0);
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
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 149 */     if (!test_only)
/*     */     {
/* 151 */       if (placer != null) {
/*     */         
/* 153 */         int placer_direction = ((MathHelper.floor_double((placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3) + 2) % 4;
/* 154 */         world.setBlockMetadataWithNotify(x, y, z, placer_direction, 2);
/*     */       } 
/*     */     }
/*     */     
/* 158 */     return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 167 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 176 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/* 177 */     return isEnderEyeInserted(var6) ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 187 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 192 */     return "frame";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 202 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockEndPortalFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */