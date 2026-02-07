/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockEnderChest
/*     */   extends BlockDirectionalWithTileEntity
/*     */ {
/*     */   protected BlockEnderChest(int par1) {
/*  11 */     super(par1, Material.stone, (new BlockConstants()).setNeverHidesAdjacentFaces());
/*  12 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  13 */     setBlockBoundsForAllThreads(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  18 */     String[] array = new String[4];
/*     */     
/*  20 */     for (int i = 0; i < array.length; i++) {
/*  21 */       array[i] = (i + 2) + "=" + getDirectionFacing(i + 2).getDescriptor(true);
/*     */     }
/*  23 */     return StringHelper.implode(array, ", ", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  28 */     return (metadata > 1 && metadata < 6);
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
/*     */   public int getRenderType() {
/*  53 */     return 22;
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
/*     */ 
/*     */ 
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
/* 116 */     return getDirectionFacingStandard6(metadata, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 121 */     metadata = direction.isNorth() ? 2 : (direction.isSouth() ? 3 : (direction.isWest() ? 4 : (direction.isEast() ? 5 : -1)));
/*     */     
/* 123 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canOpenChest(World world, int x, int y, int z, EntityLivingBase entity_living_base) {
/* 128 */     return world.isAirOrPassableBlock(x, y + 1, z, true);
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 173 */     if (player.onServer())
/*     */     {
/* 175 */       if (canOpenChest(world, x, y, z, player)) {
/*     */         
/* 177 */         InventoryEnderChest inventory = player.getInventoryEnderChest();
/* 178 */         TileEntityEnderChest tile_entity = (TileEntityEnderChest)world.getBlockTileEntity(x, y, z);
/*     */         
/* 180 */         if (inventory != null && tile_entity != null)
/*     */         {
/* 182 */           inventory.setAssociatedChest(tile_entity);
/*     */           
/* 184 */           player.displayGUIChest(x, y, z, inventory);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 189 */         world.playSoundAtBlock(x, y, z, "imported.random.chest_locked", 0.2F);
/*     */       } 
/*     */     }
/*     */     
/* 193 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/* 201 */     return new TileEntityEnderChest();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 209 */     for (int var6 = 0; var6 < 3; var6++) {
/*     */       
/* 211 */       double var10000 = (par2 + par5Random.nextFloat());
/* 212 */       double var9 = (par3 + par5Random.nextFloat());
/* 213 */       var10000 = (par4 + par5Random.nextFloat());
/* 214 */       double var13 = 0.0D;
/* 215 */       double var15 = 0.0D;
/* 216 */       double var17 = 0.0D;
/* 217 */       int var19 = par5Random.nextInt(2) * 2 - 1;
/* 218 */       int var20 = par5Random.nextInt(2) * 2 - 1;
/* 219 */       var13 = (par5Random.nextFloat() - 0.5D) * 0.125D;
/* 220 */       var15 = (par5Random.nextFloat() - 0.5D) * 0.125D;
/* 221 */       var17 = (par5Random.nextFloat() - 0.5D) * 0.125D;
/* 222 */       double var11 = par4 + 0.5D + 0.25D * var20;
/* 223 */       var17 = (par5Random.nextFloat() * 1.0F * var20);
/* 224 */       double var7 = par2 + 0.5D + 0.25D * var19;
/* 225 */       var13 = (par5Random.nextFloat() * 1.0F * var19);
/*     */       
/* 227 */       par1World.spawnParticle(EnumParticle.portal_underworld, var7, var9, var11, var13, var15, var17);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 237 */     this.blockIcon = par1IconRegister.registerIcon("obsidian");
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 242 */     return dropBlockAsEntityItem(info, Block.obsidian.blockID, 0, 8, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 247 */     return empty_handed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 252 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 257 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockEnderChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */