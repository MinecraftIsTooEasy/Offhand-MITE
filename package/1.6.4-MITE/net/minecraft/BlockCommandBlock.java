/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class BlockCommandBlock
/*     */   extends BlockContainer
/*     */ {
/*     */   public BlockCommandBlock(int par1) {
/*  10 */     super(par1, Material.iron, new BlockConstants());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/*  18 */     return new TileEntityCommandBlock();
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  47 */     boolean is_indirectly_powered = world.isBlockIndirectlyGettingPowered(x, y, z);
/*     */     
/*  49 */     int metadata = world.getBlockMetadata(x, y, z);
/*  50 */     boolean var8 = ((metadata & 0x1) != 0);
/*     */     
/*  52 */     if (is_indirectly_powered && !var8) {
/*     */       
/*  54 */       world.setBlockMetadataWithNotify(x, y, z, metadata | 0x1, 4);
/*  55 */       world.scheduleBlockUpdate(x, y, z, this.blockID, tickRate(world));
/*     */       
/*  57 */       return true;
/*     */     } 
/*  59 */     if (!is_indirectly_powered && var8) {
/*     */       
/*  61 */       world.setBlockMetadataWithNotify(x, y, z, metadata & 0xFFFFFFFE, 4);
/*     */       
/*  63 */       return true;
/*     */     } 
/*     */     
/*  66 */     return false;
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  86 */     TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
/*     */     
/*  88 */     if (tile_entity instanceof TileEntityCommandBlock) {
/*     */       
/*  90 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/*  92 */       TileEntityCommandBlock tile_entity_command_block = (TileEntityCommandBlock)tile_entity;
/*  93 */       tile_entity_command_block.setSignalStrength(tile_entity_command_block.executeCommandOnPowered(world));
/*  94 */       world.func_96440_m(x, y, z, this.blockID);
/*     */       
/*  96 */       return (world.getBlock(x, y, z) != this || world.getBlockMetadata(x, y, z) != metadata);
/*     */     } 
/*     */     
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World par1World) {
/* 107 */     return 1;
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float dx, float dy, float dz) {
/* 127 */     if (player.onServer()) {
/*     */       
/* 129 */       TileEntityCommandBlock tile_entity = (TileEntityCommandBlock)world.getBlockTileEntity(x, y, z);
/*     */       
/* 131 */       if (tile_entity != null) {
/* 132 */         player.displayGUIEditSign(tile_entity);
/*     */       }
/*     */     } 
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 153 */     TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
/* 154 */     return (var6 != null && var6 instanceof TileEntityCommandBlock) ? ((TileEntityCommandBlock)var6).getSignalStrength() : 0;
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
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 174 */     if (!test_only)
/*     */     {
/* 176 */       if (placer instanceof EntityLivingBase) {
/*     */         
/* 178 */         ItemStack item_stack = placer.getAsEntityLivingBase().getHeldItemStack();
/*     */         
/* 180 */         if (item_stack.hasDisplayName()) {
/* 181 */           ((TileEntityCommandBlock)world.getBlockTileEntity(x, y, z)).setCommandSenderName(item_stack.getDisplayName());
/*     */         }
/*     */       } 
/*     */     }
/* 185 */     return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
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
/*     */   public boolean canBeCarried() {
/* 198 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 203 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockCommandBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */