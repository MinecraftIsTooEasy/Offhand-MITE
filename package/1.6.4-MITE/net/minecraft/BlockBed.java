/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ public final class BlockBed
/*     */   extends BlockDirectional
/*     */   implements IBlockWithPartner
/*     */ {
/*  10 */   public static final int[][] footBlockToHeadBlockMap = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
/*     */   
/*     */   private Icon[] field_94472_b;
/*     */   
/*     */   private Icon[] bedSideIcons;
/*     */   private Icon[] bedTopIcons;
/*     */   
/*     */   public BlockBed(int par1) {
/*  18 */     super(par1, Material.cloth, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  19 */     setBounds(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  24 */     return "Bits 1 and 2 used for orientation, bit 8 is set for head of bed";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  29 */     return ((metadata >= 0 && metadata < 4) || (metadata >= 8 && metadata < 12));
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/*  34 */     int direction = metadata & 0x3;
/*     */     
/*  36 */     if (direction == 0)
/*  37 */       return EnumDirection.NORTH; 
/*  38 */     if (direction == 1)
/*  39 */       return EnumDirection.EAST; 
/*  40 */     if (direction == 2) {
/*  41 */       return EnumDirection.SOUTH;
/*     */     }
/*  43 */     return EnumDirection.WEST;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/*  48 */     metadata &= 0xFFFFFFFC;
/*  49 */     metadata |= direction.isNorth() ? 0 : (direction.isEast() ? 1 : (direction.isSouth() ? 2 : (direction.isWest() ? 3 : -1)));
/*     */     
/*  51 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int j(int metadata) {
/*  56 */     return metadata & 0x3;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 164 */     if (!player.onGround) {
/* 165 */       return false;
/*     */     }
/* 167 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 169 */     if (!isBlockHeadOfBed(metadata)) {
/*     */       
/* 171 */       int direction = j(metadata);
/*     */       
/* 173 */       x += footBlockToHeadBlockMap[direction][0];
/* 174 */       z += footBlockToHeadBlockMap[direction][1];
/*     */       
/* 176 */       if (world.getBlockId(x, y, z) != this.blockID) {
/* 177 */         return false;
/*     */       }
/* 179 */       metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 181 */       if (!isBlockHeadOfBed(metadata)) {
/* 182 */         return false;
/*     */       }
/*     */     } 
/* 185 */     if (world.provider.canRespawnHere() && world.getBiomeGenForCoords(x, z) != BiomeGenBase.hell) {
/*     */       
/* 187 */       if (isBedOccupied(metadata)) {
/*     */         
/* 189 */         Iterator<EntityPlayer> i = world.playerEntities.iterator();
/*     */         
/* 191 */         while (i.hasNext()) {
/*     */           
/* 193 */           EntityPlayer player_to_check = i.next();
/*     */           
/* 195 */           if (player_to_check.inBed()) {
/*     */             
/* 197 */             ChunkCoordinates chunk_coords = player_to_check.bed_location;
/*     */             
/* 199 */             if (chunk_coords.posX == x && chunk_coords.posY == y && chunk_coords.posZ == z) {
/*     */               
/* 201 */               player.addChatMessage("tile.bed.occupied");
/*     */               
/* 203 */               if (player.isLocalClient()) {
/* 204 */                 player.getPlayerController().setUseButtonDelay();
/*     */               }
/* 206 */               return false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 211 */         if (player.onServer()) {
/* 212 */           setBedOccupied(world, x, y, z, false);
/*     */         }
/*     */       } 
/* 215 */       if (player.onServer()) {
/* 216 */         player.tryToSleepInBedAt(x, y, z);
/*     */       }
/* 218 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     player.addChatMessage("tile.bed.mobsDigging");
/*     */     
/* 246 */     if (player.isLocalClient()) {
/* 247 */       player.getPlayerController().setUseButtonDelay();
/*     */     }
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 258 */     if (par1 == 0)
/*     */     {
/* 260 */       return Block.planks.getBlockTextureFromSide(par1);
/*     */     }
/*     */ 
/*     */     
/* 264 */     int var3 = j(par2);
/* 265 */     int var4 = Direction.bedDirection[var3][par1];
/* 266 */     int var5 = isBlockHeadOfBed(par2) ? 1 : 0;
/* 267 */     return ((var5 != 1 || var4 != 2) && (var5 != 0 || var4 != 3)) ? ((var4 != 5 && var4 != 4) ? this.bedTopIcons[var5] : this.bedSideIcons[var5]) : this.field_94472_b[var5];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 277 */     this.bedTopIcons = new Icon[] { par1IconRegister.registerIcon(getTextureName() + "_feet_top"), par1IconRegister.registerIcon(getTextureName() + "_head_top") };
/* 278 */     this.field_94472_b = new Icon[] { par1IconRegister.registerIcon(getTextureName() + "_feet_end"), par1IconRegister.registerIcon(getTextureName() + "_head_end") };
/* 279 */     this.bedSideIcons = new Icon[] { par1IconRegister.registerIcon(getTextureName() + "_feet_side"), par1IconRegister.registerIcon(getTextureName() + "_head_side") };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 287 */     return 14;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 312 */     setBounds(false);
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
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 352 */     if (!super.isLegalAt(world, x, y, z, metadata)) {
/* 353 */       return false;
/*     */     }
/* 355 */     if (this.is_being_placed) {
/* 356 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 365 */     return (!requiresPartner(metadata) || isPartnerPresent(world, x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 370 */     return (block_below != null && block_below.isTopFlatAndSolid(block_below_metadata));
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
/*     */   private void setBounds(boolean for_all_threads) {
/* 386 */     setBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D, for_all_threads);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBlockHeadOfBed(int par0) {
/* 394 */     return ((par0 & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBedOccupied(int par0) {
/* 402 */     return ((par0 & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setBedOccupied(World par0World, int par1, int par2, int par3, boolean par4) {
/* 410 */     int var5 = par0World.getBlockMetadata(par1, par2, par3);
/*     */     
/* 412 */     if (par4) {
/*     */       
/* 414 */       var5 |= 0x4;
/*     */     }
/*     */     else {
/*     */       
/* 418 */       var5 &= 0xFFFFFFFB;
/*     */     } 
/*     */ 
/*     */     
/* 422 */     par0World.setBlockMetadataWithNotify(par1, par2, par3, var5, 6);
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
/*     */   public static ChunkCoordinates getNearestEmptyChunkCoordinates(World world, int bed_head_x, int bed_head_y, int bed_head_z, int par4, Vec3 prescribed_pos) {
/* 463 */     int direction = j(world.getBlockMetadata(bed_head_x, bed_head_y, bed_head_z));
/*     */     
/* 465 */     int bed_foot_x = bed_head_x - footBlockToHeadBlockMap[direction][0];
/* 466 */     int bed_foot_z = bed_head_z - footBlockToHeadBlockMap[direction][1];
/*     */ 
/*     */     
/* 469 */     RaycastPolicies policies = RaycastPolicies.for_physical_reach;
/*     */     
/* 471 */     int[] dy = { 0, -1, 1 };
/*     */     
/* 473 */     for (int i = (prescribed_pos == null) ? 1 : 0; i < 2; i++) {
/*     */       
/* 475 */       for (int dy_index = 0; dy_index < dy.length; dy_index++) {
/*     */         
/* 477 */         for (int var7 = 0; var7 <= 1; var7++) {
/*     */           
/* 479 */           int var8 = bed_head_x - footBlockToHeadBlockMap[direction][0] * var7 - 1;
/* 480 */           int var9 = bed_head_z - footBlockToHeadBlockMap[direction][1] * var7 - 1;
/* 481 */           int var10 = var8 + 2;
/* 482 */           int var11 = var9 + 2;
/*     */           
/* 484 */           if (i == 0) {
/*     */             
/* 486 */             var8 -= 2;
/* 487 */             var9 -= 2;
/* 488 */             var10 += 2;
/* 489 */             var11 += 2;
/*     */           } 
/*     */           
/* 492 */           for (int var12 = var8; var12 <= var10; var12++) {
/*     */             
/* 494 */             for (int var13 = var9; var13 <= var11; var13++) {
/*     */               
/* 496 */               int x = var12;
/* 497 */               int y = bed_head_y + dy[dy_index];
/* 498 */               int z = var13;
/*     */               
/* 500 */               if (i != 0 || (x == MathHelper.floor_double(prescribed_pos.xCoord) && y == MathHelper.floor_double(prescribed_pos.yCoord + 0.95D) && z == MathHelper.floor_double(prescribed_pos.zCoord))) {
/*     */ 
/*     */                 
/* 503 */                 Block block_below = world.getBlock(x, y - 1, z);
/*     */                 
/* 505 */                 if (block_below != null && block_below.isSolid(world.getBlockMetadata(x, y - 1, z)))
/*     */                 {
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 511 */                   if ((world.isAirOrPassableBlock(x, y, z, true) || world.getBlock(x, y, z) instanceof BlockDoor || world.getBlock(x, y, z) instanceof BlockLadder) && (world.isAirOrPassableBlock(x, y + 1, z, true) || world.getBlock(x, y + 1, z) instanceof BlockDoor || world.getBlock(x, y + 1, z) instanceof BlockLadder)) {
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 516 */                     if (par4 <= 0) {
/*     */                       
/* 518 */                       if (world.canCastRayBetweenBlockCenters(policies, x, y + 1, z, bed_head_x, bed_head_y, bed_head_z, true)) {
/* 519 */                         return new ChunkCoordinates(x, y, z);
/*     */                       }
/* 521 */                       if (world.canCastRayBetweenBlockCenters(policies, x, y, z, bed_head_x, bed_head_y, bed_head_z, true)) {
/* 522 */                         return new ChunkCoordinates(x, y, z);
/*     */                       }
/* 524 */                       if (world.canCastRayBetweenBlockCenters(policies, x, y + 1, z, bed_foot_x, bed_head_y, bed_foot_z, true)) {
/* 525 */                         return new ChunkCoordinates(x, y, z);
/*     */                       }
/* 527 */                       if (world.canCastRayBetweenBlockCenters(policies, x, y, z, bed_foot_x, bed_head_y, bed_foot_z, true)) {
/* 528 */                         return new ChunkCoordinates(x, y, z);
/*     */                       }
/*     */                     } 
/* 531 */                     par4--;
/*     */                   }  } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 539 */     if (!world.getBlockMaterial(bed_head_x, bed_head_y + 1, bed_head_z).isSolid() && !world.getBlockMaterial(bed_head_x, bed_head_y + 2, bed_head_z).isSolid())
/*     */     {
/* 541 */       if (par4 <= 0) {
/* 542 */         return new ChunkCoordinates(bed_head_x, bed_head_y + 1, bed_head_z);
/*     */       }
/*     */     }
/* 545 */     if (!world.getBlockMaterial(bed_foot_x, bed_head_y + 1, bed_foot_z).isSolid() && !world.getBlockMaterial(bed_foot_x, bed_head_y + 2, bed_foot_z).isSolid())
/*     */     {
/* 547 */       if (par4 <= 0) {
/* 548 */         return new ChunkCoordinates(bed_foot_x, bed_head_y + 1, bed_foot_z);
/*     */       }
/*     */     }
/* 551 */     return null;
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
/*     */   public void onBlockAboutToBeBroken(BlockBreakInfo info) {
/* 567 */     if (!isBlockHeadOfBed(info.getMetadata())) {
/*     */       return;
/*     */     }
/* 570 */     int direction = j(info.getMetadata());
/*     */     
/* 572 */     int x = info.x - footBlockToHeadBlockMap[direction][0];
/* 573 */     int z = info.z - footBlockToHeadBlockMap[direction][1];
/*     */     
/* 575 */     if (info.world.getBlockId(x, info.y, z) != this.blockID) {
/*     */       return;
/*     */     }
/* 578 */     if (info.isResponsiblePlayerInCreativeMode()) {
/*     */       
/* 580 */       info.world.setBlockToAir(x, info.y, z);
/*     */     }
/* 582 */     else if (info.wasExploded()) {
/*     */       
/* 584 */       dropBlockAsEntityItem((new BlockBreakInfo(info.world, x, info.y, z)).setExploded(info.explosion));
/* 585 */       info.world.setBlockToAir(x, info.y, z);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean partnerDropsAsItem(int metadata) {
/* 591 */     return isBlockHeadOfBed(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 596 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 601 */     if (isBlockHeadOfBed(info.getMetadata())) {
/* 602 */       return 0;
/*     */     }
/* 604 */     if (info.wasExploded()) {
/* 605 */       return dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.5F) | dropBlockAsEntityItem(info, Item.silk.itemID, 0, 1, 1.5F);
/*     */     }
/* 607 */     return dropBlockAsEntityItem(info, Item.bed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMobilityFlag() {
/* 616 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 624 */     return Item.bed.itemID;
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
/*     */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 647 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 652 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 657 */     item_block.addMaterial(new Material[] { Material.wood, Material.cloth });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 662 */     if (isBlockHeadOfBed(metadata)) {
/* 663 */       return true;
/*     */     }
/* 665 */     int direction = j(metadata);
/*     */     
/* 667 */     x += footBlockToHeadBlockMap[direction][0];
/* 668 */     z += footBlockToHeadBlockMap[direction][1];
/*     */     
/* 670 */     if (world.isAirBlock(x, y - 1, z)) {
/* 671 */       return false;
/*     */     }
/* 673 */     return tryPlaceBlock(world, x, y, z, EnumFace.TOP, metadata | 0x8, placer, true, true, test_only);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 678 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 683 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetX(int metadata) {
/* 688 */     return isBlockHeadOfBed(metadata) ? -footBlockToHeadBlockMap[j(metadata)][0] : footBlockToHeadBlockMap[j(metadata)][0];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetY(int metadata) {
/* 693 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartnerOffsetZ(int metadata) {
/* 698 */     return isBlockHeadOfBed(metadata) ? -footBlockToHeadBlockMap[j(metadata)][1] : footBlockToHeadBlockMap[j(metadata)][1];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresPartner(int metadata) {
/* 703 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartner(int metadata, Block neighbor_block, int neighbor_block_metadata) {
/* 708 */     return (neighbor_block == this && isBlockHeadOfBed(neighbor_block_metadata) != isBlockHeadOfBed(metadata));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */