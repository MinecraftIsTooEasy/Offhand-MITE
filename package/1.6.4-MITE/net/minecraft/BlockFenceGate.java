/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class BlockFenceGate
/*     */   extends BlockDirectional
/*     */ {
/*     */   public BlockFenceGate(int par1) {
/*   8 */     super(par1, Material.wood, (new BlockConstants()).setNeverHidesAdjacentFaces().setAlwaysConnectsWithFence());
/*   9 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  14 */     return "Bits 1 and 2 used for orientation, bit 4 set if gate is open";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  19 */     return (metadata >= 0 && metadata < 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  27 */     return Block.planks.getBlockTextureFromSide(par1);
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
/*     */   public static int j(int metadata) {
/*  45 */     return metadata & 0x3;
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
/*  72 */     if (entity instanceof EntityPudding) {
/*     */       
/*  74 */       AxisAlignedBB bounds = (AxisAlignedBB)getCollisionBounds(world, x, y, z, (Entity)null);
/*  75 */       return (bounds == null) ? null : bounds.setMaxY(y + 1.5D);
/*     */     } 
/*     */ 
/*     */     
/*  79 */     return isSolid(world, x, y, z) ? (useFullBlockForCollisions(entity) ? AxisAlignedBB.getBoundingBoxFromPool(x, y, z, 0.0D, 0.0D, 0.0D, 1.0D, 1.5D, 1.0D) : super.getCollisionBounds(world, x, y, z, entity)) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  87 */     int var5 = j(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
/*     */     
/*  89 */     if (var5 != 2 && var5 != 0) {
/*     */ 
/*     */       
/*  92 */       setBlockBoundsForCurrentThread(0.375D, 0.32499998807907104D, 0.0D, 0.625D, 1.0D, 1.0D);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  97 */       setBlockBoundsForCurrentThread(0.0D, 0.32499998807907104D, 0.375D, 1.0D, 1.0D, 0.625D);
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
/*     */   public int getRenderType() {
/* 131 */     return 21;
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
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/* 147 */     return getDirectionFacingStandard4(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 152 */     metadata &= 0xFFFFFFFC;
/* 153 */     metadata |= direction.isSouth() ? 0 : (direction.isWest() ? 1 : (direction.isNorth() ? 2 : (direction.isEast() ? 3 : -1)));
/*     */     
/* 155 */     return metadata;
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 189 */     if (player.onServer()) {
/*     */       
/* 191 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 193 */       if (isFenceGateOpen(metadata)) {
/*     */         
/* 195 */         metadata &= 0xFFFFFFFB;
/*     */       }
/*     */       else {
/*     */         
/* 199 */         float yaw = player.rotationYaw;
/*     */         
/* 201 */         while (yaw < 0.0F) {
/* 202 */           yaw += 360.0F;
/*     */         }
/* 204 */         while (yaw >= 360.0F) {
/* 205 */           yaw -= 360.0F;
/*     */         }
/* 207 */         if (metadata == 0 || metadata == 2) {
/* 208 */           metadata = ((yaw >= 0.0F && yaw < 90.0F) || yaw >= 270.0F) ? 4 : 6;
/*     */         } else {
/* 210 */           metadata = (yaw >= 0.0F && yaw < 180.0F) ? 5 : 7;
/*     */         } 
/* 212 */         metadata |= 0x4;
/*     */       } 
/*     */       
/* 215 */       world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/* 216 */       makeOpenOrCloseSound(world, x, y, z, metadata);
/*     */     } 
/*     */     
/* 219 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void makeOpenOrCloseSound(World world, int x, int y, int z, int metadata_after) {
/* 224 */     if (isFenceGateOpen(metadata_after)) {
/* 225 */       world.playSoundAtBlock(x, y, z, "random.door_open", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
/*     */     } else {
/* 227 */       world.playSoundAtBlock(x, y, z, "random.door_close", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 259 */     int metadata = world.getBlockMetadata(x, y, z);
/* 260 */     boolean is_indirectly_powered = world.isBlockIndirectlyGettingPowered(x, y, z);
/*     */     
/* 262 */     if (is_indirectly_powered || (neighbor_block_id > 0 && Block.blocksList[neighbor_block_id].canProvidePower())) {
/*     */       
/* 264 */       if (is_indirectly_powered && !isFenceGateOpen(metadata)) {
/*     */         
/* 266 */         world.setBlockMetadataWithNotify(x, y, z, metadata | 0x4, 2);
/* 267 */         makeOpenOrCloseSound(world, x, y, z, metadata | 0x4);
/*     */         
/* 269 */         return true;
/*     */       } 
/* 271 */       if (!is_indirectly_powered && isFenceGateOpen(metadata)) {
/*     */         
/* 273 */         world.setBlockMetadataWithNotify(x, y, z, metadata & 0xFFFFFFFB, 2);
/* 274 */         makeOpenOrCloseSound(world, x, y, z, metadata & 0xFFFFFFFB);
/*     */         
/* 276 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 280 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isFenceGateOpen(int par0) {
/* 288 */     return ((par0 & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 297 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPortal() {
/* 308 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpenPortal(World world, int x, int y, int z) {
/* 313 */     return isFenceGateOpen(world.getBlockMetadata(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 318 */     return !isFenceGateOpen(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 323 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 328 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFenceGate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */