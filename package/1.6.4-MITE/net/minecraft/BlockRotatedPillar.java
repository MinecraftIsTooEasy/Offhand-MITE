/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public abstract class BlockRotatedPillar
/*     */   extends Block
/*     */ {
/*     */   protected Icon field_111051_a;
/*     */   
/*     */   protected BlockRotatedPillar(int par1, Material par2Material) {
/*  10 */     super(par1, par2Material, new BlockConstants());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  18 */     return 31;
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
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  51 */     int metadata = super.getMetadataForPlacement(world, x, y, z, item_stack, entity, face, offset_x, offset_y, offset_z);
/*     */     
/*  53 */     if (face.isEastOrWest()) {
/*  54 */       metadata |= 0x4;
/*  55 */     } else if (face.isNorthOrSouth()) {
/*  56 */       metadata |= 0x8;
/*     */     } 
/*  58 */     return metadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  66 */     int var3 = par2 & 0xC;
/*  67 */     int var4 = par2 & 0x3;
/*  68 */     return (var3 == 0 && (par1 == 1 || par1 == 0)) ? getEndIcon(var4) : ((var3 == 4 && (par1 == 5 || par1 == 4)) ? getEndIcon(var4) : ((var3 == 8 && (par1 == 2 || par1 == 3)) ? getEndIcon(var4) : getSideIcon(var4)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Icon getSideIcon(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Icon getEndIcon(int par1) {
/*  81 */     return this.field_111051_a;
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
/*     */   public EnumDirection getDirectionFacing(int metadata) {
/* 109 */     return ((metadata & 0x4) != 0) ? EnumDirection.WEST : (((metadata & 0x8) != 0) ? EnumDirection.NORTH : EnumDirection.DOWN);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 114 */     return getItemSubtype(metadata) | (direction.isUpOrDown() ? 0 : (direction.isEastOrWest() ? 4 : (direction.isNorthOrSouth() ? 8 : -1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public Axis getAxis(int metadata) {
/* 119 */     return BitHelper.isBitSet(metadata, 4) ? Axis.EAST_WEST : (BitHelper.isBitSet(metadata, 8) ? Axis.NORTH_SOUTH : Axis.UP_DOWN);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRotatedPillar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */