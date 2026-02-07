/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSkull
/*     */   extends BlockMountedWithTileEntity
/*     */ {
/*     */   protected BlockSkull(int par1) {
/*  11 */     super(par1, Material.circuits, TileEntitySkull.class, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  12 */     setBlockBoundsForAllThreads(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  17 */     return "1 if lying on ground (in which case the tile entity determines orientation), otherwise 2-5 if wall-mounted";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  22 */     return (metadata > 0 && metadata < 6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getRenderType() {
/*  31 */     return -1;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  58 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x7;
/*     */     
/*  60 */     switch (var5) {
/*     */ 
/*     */       
/*     */       default:
/*  64 */         setBlockBoundsForCurrentThread(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
/*     */         return;
/*     */       
/*     */       case 2:
/*  68 */         setBlockBoundsForCurrentThread(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
/*     */         return;
/*     */       
/*     */       case 3:
/*  72 */         setBlockBoundsForCurrentThread(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
/*     */         return;
/*     */       
/*     */       case 4:
/*  76 */         setBlockBoundsForCurrentThread(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D); return;
/*     */       case 5:
/*     */         break;
/*     */     } 
/*  80 */     setBlockBoundsForCurrentThread(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
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
/*     */   public EnumFace getFaceMountedTo(int metadata) {
/* 105 */     return EnumFace.get(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getDefaultMetadataForFaceMountedTo(EnumFace face) {
/* 110 */     return face.ordinal();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMountToBlock(int metadata, Block neighbor_block, int neighbor_block_metadata, EnumFace face) {
/* 115 */     if (face.isTop()) {
/*     */       
/* 117 */       if (neighbor_block == fence || neighbor_block == Block.netherFence) {
/* 118 */         return true;
/*     */       }
/* 120 */       if (neighbor_block instanceof BlockWall) {
/* 121 */         return true;
/*     */       }
/* 123 */       if (neighbor_block != leaves && neighbor_block.isFaceFlatAndSolid(neighbor_block_metadata, face)) {
/* 124 */         return true;
/*     */       }
/*     */     } 
/* 127 */     return (neighbor_block == cloth || super.canMountToBlock(metadata, neighbor_block, neighbor_block_metadata, face));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/* 135 */     return new TileEntitySkull();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 143 */     return Item.skull.itemID;
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
/*     */   public boolean canBeCarried() {
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 180 */     if (info.wasExploded()) {
/* 181 */       return 0;
/*     */     }
/* 183 */     if (info.wasCrushed()) {
/* 184 */       return dropBlockAsEntityItem(info, new ItemStack(Item.dyePowder, 1, 15));
/*     */     }
/* 186 */     if (info.isResponsiblePlayerInCreativeMode())
/*     */     {
/* 188 */       info.setMetadata(info.getMetadata() | 0x8);
/*     */     }
/* 190 */     if ((info.getMetadata() & 0x8) == 0) {
/*     */       
/* 192 */       TileEntitySkull tile_entity_skull = (info.tile_entity instanceof TileEntitySkull) ? (TileEntitySkull)info.tile_entity : null;
/*     */       
/* 194 */       if (tile_entity_skull == null) {
/* 195 */         return 0;
/*     */       }
/* 197 */       ItemStack item_stack = new ItemStack(Item.skull.itemID, 1, tile_entity_skull.getSkullType());
/*     */       
/* 199 */       String extra_type = tile_entity_skull.getExtraType();
/*     */       
/* 201 */       if (tile_entity_skull.getSkullType() == 3 && extra_type != null && extra_type.length() > 0) {
/*     */         
/* 203 */         item_stack.setTagCompound(new NBTTagCompound());
/* 204 */         item_stack.getTagCompound().setString("SkullOwner", extra_type);
/*     */       } 
/*     */       
/* 207 */       return dropBlockAsEntityItem(info.setOther(), item_stack);
/*     */     } 
/*     */     
/* 210 */     return 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World world, int x, int y, int z, int old_block_id, int old_block_metadata) {
/* 279 */     super.breakBlock(world, x, y, z, old_block_id, old_block_metadata);
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
/*     */   public void makeWither(World par1World, int par2, int par3, int par4, TileEntitySkull par5TileEntitySkull) {
/* 295 */     if (par5TileEntitySkull.getSkullType() == 1 && par3 >= 2 && par1World.difficultySetting > 0 && !par1World.isRemote) {
/*     */       
/* 297 */       int var6 = Block.slowSand.blockID;
/*     */ 
/*     */       
/*     */       int var7;
/*     */       
/* 302 */       for (var7 = -2; var7 <= 0; var7++) {
/*     */         
/* 304 */         if (par1World.getBlockId(par2, par3 - 1, par4 + var7) == var6 && par1World.getBlockId(par2, par3 - 1, par4 + var7 + 1) == var6 && par1World.getBlockId(par2, par3 - 2, par4 + var7 + 1) == var6 && par1World.getBlockId(par2, par3 - 1, par4 + var7 + 2) == var6 && func_82528_d(par1World, par2, par3, par4 + var7, 1) && func_82528_d(par1World, par2, par3, par4 + var7 + 1, 1) && func_82528_d(par1World, par2, par3, par4 + var7 + 2, 1)) {
/*     */           
/* 306 */           par1World.setBlockMetadataWithNotify(par2, par3, par4 + var7, 8, 2);
/* 307 */           par1World.setBlockMetadataWithNotify(par2, par3, par4 + var7 + 1, 8, 2);
/* 308 */           par1World.setBlockMetadataWithNotify(par2, par3, par4 + var7 + 2, 8, 2);
/* 309 */           par1World.setBlock(par2, par3, par4 + var7, 0, 0, 2);
/* 310 */           par1World.setBlock(par2, par3, par4 + var7 + 1, 0, 0, 2);
/* 311 */           par1World.setBlock(par2, par3, par4 + var7 + 2, 0, 0, 2);
/* 312 */           par1World.setBlock(par2, par3 - 1, par4 + var7, 0, 0, 2);
/* 313 */           par1World.setBlock(par2, par3 - 1, par4 + var7 + 1, 0, 0, 2);
/* 314 */           par1World.setBlock(par2, par3 - 1, par4 + var7 + 2, 0, 0, 2);
/* 315 */           par1World.setBlock(par2, par3 - 2, par4 + var7 + 1, 0, 0, 2);
/*     */           
/* 317 */           if (!par1World.isRemote) {
/*     */             
/* 319 */             EntityWither var8 = new EntityWither(par1World);
/* 320 */             var8.setLocationAndAngles(par2 + 0.5D, par3 - 1.45D, (par4 + var7) + 1.5D, 90.0F, 0.0F);
/* 321 */             var8.renderYawOffset = 90.0F;
/* 322 */             var8.func_82206_m();
/* 323 */             par1World.spawnEntityInWorld(var8);
/*     */           } 
/*     */           
/* 326 */           for (int var9 = 0; var9 < 120; var9++)
/*     */           {
/*     */             
/* 329 */             par1World.spawnParticle(EnumParticle.snowballpoof, par2 + par1World.rand.nextDouble(), (par3 - 2) + par1World.rand.nextDouble() * 3.9D, (par4 + var7 + 1) + par1World.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
/*     */           }
/*     */           
/* 332 */           par1World.notifyBlockChange(par2, par3, par4 + var7, 0);
/* 333 */           par1World.notifyBlockChange(par2, par3, par4 + var7 + 1, 0);
/* 334 */           par1World.notifyBlockChange(par2, par3, par4 + var7 + 2, 0);
/* 335 */           par1World.notifyBlockChange(par2, par3 - 1, par4 + var7, 0);
/* 336 */           par1World.notifyBlockChange(par2, par3 - 1, par4 + var7 + 1, 0);
/* 337 */           par1World.notifyBlockChange(par2, par3 - 1, par4 + var7 + 2, 0);
/* 338 */           par1World.notifyBlockChange(par2, par3 - 2, par4 + var7 + 1, 0);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 343 */       for (var7 = -2; var7 <= 0; var7++) {
/*     */         
/* 345 */         if (par1World.getBlockId(par2 + var7, par3 - 1, par4) == var6 && par1World.getBlockId(par2 + var7 + 1, par3 - 1, par4) == var6 && par1World.getBlockId(par2 + var7 + 1, par3 - 2, par4) == var6 && par1World.getBlockId(par2 + var7 + 2, par3 - 1, par4) == var6 && func_82528_d(par1World, par2 + var7, par3, par4, 1) && func_82528_d(par1World, par2 + var7 + 1, par3, par4, 1) && func_82528_d(par1World, par2 + var7 + 2, par3, par4, 1)) {
/*     */           
/* 347 */           par1World.setBlockMetadataWithNotify(par2 + var7, par3, par4, 8, 2);
/* 348 */           par1World.setBlockMetadataWithNotify(par2 + var7 + 1, par3, par4, 8, 2);
/* 349 */           par1World.setBlockMetadataWithNotify(par2 + var7 + 2, par3, par4, 8, 2);
/* 350 */           par1World.setBlock(par2 + var7, par3, par4, 0, 0, 2);
/* 351 */           par1World.setBlock(par2 + var7 + 1, par3, par4, 0, 0, 2);
/* 352 */           par1World.setBlock(par2 + var7 + 2, par3, par4, 0, 0, 2);
/* 353 */           par1World.setBlock(par2 + var7, par3 - 1, par4, 0, 0, 2);
/* 354 */           par1World.setBlock(par2 + var7 + 1, par3 - 1, par4, 0, 0, 2);
/* 355 */           par1World.setBlock(par2 + var7 + 2, par3 - 1, par4, 0, 0, 2);
/* 356 */           par1World.setBlock(par2 + var7 + 1, par3 - 2, par4, 0, 0, 2);
/*     */           
/* 358 */           if (!par1World.isRemote) {
/*     */             
/* 360 */             EntityWither var8 = new EntityWither(par1World);
/* 361 */             var8.setLocationAndAngles((par2 + var7) + 1.5D, par3 - 1.45D, par4 + 0.5D, 0.0F, 0.0F);
/* 362 */             var8.func_82206_m();
/* 363 */             par1World.spawnEntityInWorld(var8);
/*     */           } 
/*     */           
/* 366 */           for (int var9 = 0; var9 < 120; var9++)
/*     */           {
/*     */             
/* 369 */             par1World.spawnParticle(EnumParticle.snowballpoof, (par2 + var7 + 1) + par1World.rand.nextDouble(), (par3 - 2) + par1World.rand.nextDouble() * 3.9D, par4 + par1World.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
/*     */           }
/*     */           
/* 372 */           par1World.notifyBlockChange(par2 + var7, par3, par4, 0);
/* 373 */           par1World.notifyBlockChange(par2 + var7 + 1, par3, par4, 0);
/* 374 */           par1World.notifyBlockChange(par2 + var7 + 2, par3, par4, 0);
/* 375 */           par1World.notifyBlockChange(par2 + var7, par3 - 1, par4, 0);
/* 376 */           par1World.notifyBlockChange(par2 + var7 + 1, par3 - 1, par4, 0);
/* 377 */           par1World.notifyBlockChange(par2 + var7 + 2, par3 - 1, par4, 0);
/* 378 */           par1World.notifyBlockChange(par2 + var7 + 1, par3 - 2, par4, 0);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_82528_d(World par1World, int par2, int par3, int par4, int par5) {
/* 387 */     if (par1World.getBlockId(par2, par3, par4) != this.blockID)
/*     */     {
/* 389 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 393 */     TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
/* 394 */     return (var6 != null && var6 instanceof TileEntitySkull) ? ((((TileEntitySkull)var6).getSkullType() == par5)) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 409 */     return Block.slowSand.getBlockTextureFromSide(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemIconName() {
/* 417 */     return getTextureName() + "_" + ItemSkull.field_94587_a[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 422 */     if (!test_only)
/*     */     {
/* 424 */       if (placer instanceof EntityPlayer) {
/*     */         
/* 426 */         EntityPlayer player = placer.getAsPlayer();
/*     */         
/* 428 */         TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
/*     */         
/* 430 */         if (tile_entity instanceof TileEntitySkull) {
/*     */           
/* 432 */           String var13 = "";
/*     */           
/* 434 */           ItemStack item_stack = player.getHeldItemStack();
/*     */           
/* 436 */           if (item_stack.hasTagCompound() && item_stack.getTagCompound().hasKey("SkullOwner")) {
/* 437 */             var13 = item_stack.getTagCompound().getString("SkullOwner");
/*     */           }
/* 439 */           ((TileEntitySkull)tile_entity).setSkullType(item_stack.getItemSubtype(), var13);
/* 440 */           ((TileEntitySkull)tile_entity).setSkullRotation(getFaceMountedTo(metadata).isTop() ? player.getRotationYawAsSixteenths() : 0);
/* 441 */           ((BlockSkull)Block.skull).makeWither(world, x, y, z, (TileEntitySkull)tile_entity);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 446 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDislodgedOrCrushedByFallingBlock(int metadata, Block falling_block, int falling_block_metadata) {
/* 451 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 456 */     if (!getFaceMountedTo(world.getBlockMetadata(x, y, z)).isTop()) {
/* 457 */       return false;
/*     */     }
/* 459 */     TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
/*     */     
/* 461 */     if (tile_entity instanceof TileEntitySkull) {
/* 462 */       ((TileEntitySkull)tile_entity).setSkullRotation(player.getRotationYawAsSixteenths());
/*     */     }
/* 464 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSkull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */