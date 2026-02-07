/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockLeavesBase
/*     */   extends Block
/*     */ {
/*     */   protected boolean graphicsLevel;
/*     */   
/*     */   protected BlockLeavesBase(int par1, Material par2Material, boolean par3) {
/*  17 */     super(par1, par2Material, new BlockConstants());
/*     */     
/*  19 */     this.graphicsLevel = par3;
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
/*     */   public final boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  44 */     int var6 = par1IBlockAccess.getBlockId(par2, par3, par4);
/*  45 */     return (!this.graphicsLevel && var6 == this.blockID) ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  50 */     return item_stack.getItemSubtype() | 0x4;
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
/*     */   public final void addCollidingBoundsToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity entity) {
/*  70 */     if (!canCollideWithEntity(entity)) {
/*     */       return;
/*     */     }
/*  73 */     if (entity instanceof EntityWoodSpider && (par3 + 0.99F) > entity.posY) {
/*     */       return;
/*     */     }
/*  76 */     super.addCollidingBoundsToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCollideWithEntity(Entity entity) {
/*  81 */     return !(entity instanceof EntityItem || entity instanceof EntityDiggingFX || entity instanceof EntityBreakingFX);
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
/*     */   public boolean canBePathedInto(World world, int x, int y, int z, Entity entity, boolean allow_closed_wooden_portals) {
/* 109 */     return (entity instanceof EntityWoodSpider && y + 1.0D > entity.posY);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockLeavesBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */