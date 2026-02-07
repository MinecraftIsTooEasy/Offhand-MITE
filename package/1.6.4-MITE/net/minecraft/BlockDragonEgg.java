/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class BlockDragonEgg
/*     */   extends Block
/*     */ {
/*     */   public BlockDragonEgg(int par1) {
/*  10 */     super(par1, Material.dragonEgg, (new BlockConstants()).setNeverHidesAdjacentFaces());
/*  11 */     setBlockBoundsForAllThreads(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
/*     */     
/*  13 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/*  21 */     par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate(par1World));
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  35 */     world.scheduleBlockUpdate(x, y, z, this.blockID, tickRate(world));
/*     */     
/*  37 */     return false;
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  50 */     return fallIfPossible(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean fallIfPossible(World par1World, int par2, int par3, int par4) {
/*  60 */     if (Block.sand.canFallDownTo(par1World, par2, par3 - 1, par4, 0) && par3 >= 0) {
/*     */       
/*  62 */       byte var5 = 32;
/*     */       
/*  64 */       if (!BlockFalling.fallInstantly && par1World.checkChunksExist(par2 - var5, par3 - var5, par4 - var5, par2 + var5, par3 + var5, par4 + var5)) {
/*     */         
/*  66 */         EntityFallingSand var6 = new EntityFallingSand(par1World, (par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), this.blockID);
/*  67 */         par1World.spawnEntityInWorld(var6);
/*     */       }
/*     */       else {
/*     */         
/*  71 */         par1World.setBlockToAir(par2, par3, par4);
/*     */ 
/*     */         
/*  74 */         while (Block.sand.canFallDownTo(par1World, par2, par3 - 1, par4, 0) && par3 > 0)
/*     */         {
/*  76 */           par3--;
/*     */         }
/*     */         
/*  79 */         if (par3 > 0)
/*     */         {
/*  81 */           par1World.setBlock(par2, par3, par4, this.blockID, 0, 2);
/*     */         }
/*     */         
/*  84 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     return false;
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 102 */     if (player.onServer()) {
/* 103 */       teleportNearby(world, x, y, z);
/*     */     }
/* 105 */     return true;
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
/*     */   public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
/* 118 */     if (player.onServer()) {
/* 119 */       teleportNearby(world, x, y, z);
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
/*     */ 
/*     */ 
/*     */   
/*     */   private void teleportNearby(World world, int x, int y, int z) {
/* 168 */     if (world.isRemote) {
/* 169 */       Minecraft.setErrorMessage("teleportNearby: not meant to be called on client");
/*     */     }
/* 171 */     if (world.getBlockId(x, y, z) != this.blockID) {
/*     */       return;
/*     */     }
/* 174 */     for (int attempts = 0; attempts < 1000; attempts++) {
/*     */       
/* 176 */       int try_x = x + world.rand.nextInt(16) - world.rand.nextInt(16);
/* 177 */       int try_y = y + world.rand.nextInt(8) - world.rand.nextInt(8);
/* 178 */       int try_z = z + world.rand.nextInt(16) - world.rand.nextInt(16);
/*     */       
/* 180 */       if (world.isAirBlock(try_x, try_y, try_z)) {
/*     */         
/* 182 */         world.setBlock(try_x, try_y, try_z, this.blockID, world.getBlockMetadata(x, y, z), 2);
/* 183 */         world.setBlockToAir(x, y, z);
/*     */         
/* 185 */         world.blockFX(EnumBlockFX.particle_trail, x, y, z, (new SignalData()).setByte(EnumParticle.portal_underworld.ordinal()).setShort(128).setApproxPosition(try_x, try_y, try_z));
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World par1World) {
/* 197 */     return 5;
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
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 231 */     return 27;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 239 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 249 */     return dropBlockAsItself(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 254 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 259 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 264 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDragonEgg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */