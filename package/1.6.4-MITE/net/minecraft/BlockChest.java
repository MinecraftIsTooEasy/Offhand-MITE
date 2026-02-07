/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class BlockChest
/*      */   extends BlockDirectionalWithTileEntity
/*      */ {
/*    9 */   private final Random random = new Random();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumChestType chest_type;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BlockChest(int id, EnumChestType chest_type, Material material) {
/*   25 */     super(id, material, (new BlockConstants()).setNeverHidesAdjacentFaces());
/*      */     
/*   27 */     this.chest_type = chest_type;
/*   28 */     setCreativeTab(CreativeTabs.tabDecorations);
/*   29 */     setBlockBoundsForAllThreads(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
/*      */     
/*   31 */     setTickRandomly(true);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMetadataNotes() {
/*   36 */     String[] array = new String[4];
/*      */     
/*   38 */     for (int i = 0; i < array.length; i++) {
/*   39 */       array[i] = (i + 2) + "=" + getDirectionFacing(i + 2).getDescriptor(true);
/*      */     }
/*   41 */     return StringHelper.implode(array, ", ", true, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isValidMetadata(int metadata) {
/*   46 */     return (metadata > 1 && metadata < 6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRenderType() {
/*   71 */     return 22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*   79 */     if (par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID) {
/*      */       
/*   81 */       setBlockBoundsForCurrentThread(0.0625D, 0.0D, 0.0D, 0.9375D, 0.875D, 0.9375D);
/*      */     }
/*   83 */     else if (par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID) {
/*      */       
/*   85 */       setBlockBoundsForCurrentThread(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 1.0D);
/*      */     }
/*   87 */     else if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID) {
/*      */       
/*   89 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
/*      */     }
/*   91 */     else if (par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID) {
/*      */       
/*   93 */       setBlockBoundsForCurrentThread(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D);
/*      */     }
/*      */     else {
/*      */       
/*   97 */       setBlockBoundsForCurrentThread(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/*  106 */     super.onBlockAdded(par1World, par2, par3, par4);
/*      */     
/*  108 */     tryAlignWithNeighboringChest(par1World, par2, par3, par4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final EnumDirection getDirectionFacing(int metadata) {
/*  141 */     return getDirectionFacingStandard6(metadata, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/*  146 */     metadata = direction.isNorth() ? 2 : (direction.isSouth() ? 3 : (direction.isWest() ? 4 : (direction.isEast() ? 5 : -1)));
/*      */     
/*  148 */     return metadata;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tryAlignWithNeighboringChest(World world, int x, int y, int z) {
/*  484 */     if (world.isRemote) {
/*      */       return;
/*      */     }
/*  487 */     if (this instanceof BlockStrongbox) {
/*      */       return;
/*      */     }
/*  490 */     int metadata = world.getBlockMetadata(x, y, z);
/*      */     
/*  492 */     if (world.getBlockId(x - 1, y, z) == this.blockID) {
/*      */       
/*  494 */       if (metadata == 2 || metadata == 3) {
/*      */         
/*  496 */         world.setBlockMetadataWithNotify(x - 1, y, z, metadata, 2);
/*      */       }
/*      */       else {
/*      */         
/*  500 */         metadata = world.getBlockMetadata(x - 1, y, z);
/*      */         
/*  502 */         if (metadata == 2 || metadata == 3)
/*      */         {
/*  504 */           world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/*      */         }
/*      */         else
/*      */         {
/*  508 */           metadata = 2;
/*      */           
/*  510 */           world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/*  511 */           world.setBlockMetadataWithNotify(x - 1, y, z, metadata, 2);
/*      */         }
/*      */       
/*      */       } 
/*  515 */     } else if (world.getBlockId(x + 1, y, z) == this.blockID) {
/*      */       
/*  517 */       if (metadata == 2 || metadata == 3) {
/*      */         
/*  519 */         world.setBlockMetadataWithNotify(x + 1, y, z, metadata, 2);
/*      */       }
/*      */       else {
/*      */         
/*  523 */         metadata = world.getBlockMetadata(x + 1, y, z);
/*      */         
/*  525 */         if (metadata == 2 || metadata == 3)
/*      */         {
/*  527 */           world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/*      */         }
/*      */         else
/*      */         {
/*  531 */           metadata = 3;
/*      */           
/*  533 */           world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/*  534 */           world.setBlockMetadataWithNotify(x + 1, y, z, metadata, 2);
/*      */         }
/*      */       
/*      */       } 
/*  538 */     } else if (world.getBlockId(x, y, z - 1) == this.blockID) {
/*      */       
/*  540 */       if (metadata == 4 || metadata == 5) {
/*      */         
/*  542 */         world.setBlockMetadataWithNotify(x, y, z - 1, metadata, 2);
/*      */       }
/*      */       else {
/*      */         
/*  546 */         metadata = world.getBlockMetadata(x, y, z - 1);
/*      */         
/*  548 */         if (metadata == 4 || metadata == 5)
/*      */         {
/*  550 */           world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/*      */         }
/*      */         else
/*      */         {
/*  554 */           metadata = 4;
/*      */           
/*  556 */           world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/*  557 */           world.setBlockMetadataWithNotify(x, y, z - 1, metadata, 2);
/*      */         }
/*      */       
/*      */       } 
/*  561 */     } else if (world.getBlockId(x, y, z + 1) == this.blockID) {
/*      */       
/*  563 */       if (metadata == 4 || metadata == 5) {
/*      */         
/*  565 */         world.setBlockMetadataWithNotify(x, y, z + 1, metadata, 2);
/*      */       }
/*      */       else {
/*      */         
/*  569 */         metadata = world.getBlockMetadata(x, y, z + 1);
/*      */         
/*  571 */         if (metadata == 4 || metadata == 5) {
/*      */           
/*  573 */           world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/*      */         }
/*      */         else {
/*      */           
/*  577 */           metadata = 5;
/*      */           
/*  579 */           world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
/*  580 */           world.setBlockMetadataWithNotify(x, y, z + 1, metadata, 2);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePlacedAt(World world, int x, int y, int z, int metadata) {
/*  621 */     if (this instanceof BlockStrongbox) {
/*  622 */       return super.canBePlacedAt(world, x, y, z, metadata);
/*      */     }
/*  624 */     int num_orthagonal_chests = 0;
/*      */     
/*  626 */     if (world.getBlock(x - 1, y, z) == this) {
/*  627 */       num_orthagonal_chests++;
/*      */     }
/*  629 */     if (world.getBlock(x + 1, y, z) == this) {
/*  630 */       num_orthagonal_chests++;
/*      */     }
/*  632 */     if (world.getBlock(x, y, z - 1) == this) {
/*  633 */       num_orthagonal_chests++;
/*      */     }
/*  635 */     if (world.getBlock(x, y, z + 1) == this) {
/*  636 */       num_orthagonal_chests++;
/*      */     }
/*  638 */     if (num_orthagonal_chests > 1) {
/*  639 */       return false;
/*      */     }
/*  641 */     if (isThereANeighborChest(world, x - 1, y, z) || isThereANeighborChest(world, x + 1, y, z) || isThereANeighborChest(world, x, y, z - 1) || isThereANeighborChest(world, x, y, z + 1)) {
/*  642 */       return false;
/*      */     }
/*  644 */     return super.canBePlacedAt(world, x, y, z, metadata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isThereANeighborChest(World par1World, int par2, int par3, int par4) {
/*  652 */     return (par1World.getBlockId(par2, par3, par4) != this.blockID) ? false : ((par1World.getBlockId(par2 - 1, par3, par4) == this.blockID) ? true : ((par1World.getBlockId(par2 + 1, par3, par4) == this.blockID) ? true : ((par1World.getBlockId(par2, par3, par4 - 1) == this.blockID) ? true : ((par1World.getBlockId(par2, par3, par4 + 1) == this.blockID)))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean updateTick(World world, int x, int y, int z, Random rand) {
/*  672 */     if (super.updateTick(world, x, y, z, rand) && world.getBlock(x, y, z) != this) {
/*  673 */       return true;
/*      */     }
/*  675 */     TileEntityChest chest_entity = (TileEntityChest)world.getBlockTileEntity(x, y, z);
/*      */     
/*  677 */     if (chest_entity != null) {
/*  678 */       chest_entity.checkForWormComposting();
/*      */     }
/*  680 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  685 */     if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
/*  686 */       return true;
/*      */     }
/*  688 */     TileEntityChest chest_entity = (TileEntityChest)world.getBlockTileEntity(x, y, z);
/*      */     
/*  690 */     if (chest_entity != null) {
/*      */       
/*  692 */       chest_entity.updateContainingBlockInfo();
/*  693 */       return true;
/*      */     } 
/*      */     
/*  696 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/*  706 */     TileEntityChest var7 = (TileEntityChest)par1World.getBlockTileEntity(par2, par3, par4);
/*      */     
/*  708 */     if (var7 != null) {
/*      */       
/*  710 */       for (int var8 = 0; var8 < var7.getSizeInventory(); var8++) {
/*      */         
/*  712 */         ItemStack var9 = var7.getStackInSlot(var8);
/*      */         
/*  714 */         if (var9 != null) {
/*      */           
/*  716 */           float var10 = this.random.nextFloat() * 0.8F + 0.1F;
/*  717 */           float var11 = this.random.nextFloat() * 0.8F + 0.1F;
/*      */ 
/*      */           
/*  720 */           for (float var12 = this.random.nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; par1World.spawnEntityInWorld(var14)) {
/*      */             
/*  722 */             int var13 = this.random.nextInt(21) + 10;
/*      */             
/*  724 */             if (var13 > var9.stackSize)
/*      */             {
/*  726 */               var13 = var9.stackSize;
/*      */             }
/*      */             
/*  729 */             var9.stackSize -= var13;
/*      */             
/*  731 */             EntityItem var14 = new EntityItem(par1World, (par2 + var10), (par3 + var11), (par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemSubtype()));
/*      */             
/*  733 */             if (var9.isItemDamaged()) {
/*  734 */               var14.getEntityItem().setItemDamage(var9.getItemDamage());
/*      */             }
/*  736 */             float var15 = 0.05F;
/*  737 */             var14.motionX = ((float)this.random.nextGaussian() * var15);
/*  738 */             var14.motionY = ((float)this.random.nextGaussian() * var15 + 0.2F);
/*  739 */             var14.motionZ = ((float)this.random.nextGaussian() * var15);
/*      */             
/*  741 */             if (var9.getItem().hasQuality()) {
/*  742 */               var14.getEntityItem().setQuality(var9.getQuality());
/*      */             }
/*  744 */             if (var9.hasTagCompound())
/*      */             {
/*  746 */               var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  752 */       par1World.func_96440_m(par2, par3, par4, par5);
/*      */     } 
/*      */     
/*  755 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getUnifiedNeighborCoordinates(World world, int x, int y, int z) {
/*  772 */     int metadata = world.getBlockMetadata(x, y, z);
/*      */     
/*  774 */     if (metadata == 2 || metadata == 3) {
/*      */       
/*  776 */       if (world.getBlock(x - 1, y, z) == this)
/*  777 */         return new int[] { x - 1, y, z }; 
/*  778 */       if (world.getBlock(x + 1, y, z) == this) {
/*  779 */         return new int[] { x + 1, y, z };
/*      */       }
/*  781 */       return null;
/*      */     } 
/*  783 */     if (metadata == 4 || metadata == 5) {
/*      */       
/*  785 */       if (world.getBlock(x, y, z - 1) == this)
/*  786 */         return new int[] { x, y, z - 1 }; 
/*  787 */       if (world.getBlock(x, y, z + 1) == this) {
/*  788 */         return new int[] { x, y, z + 1 };
/*      */       }
/*  790 */       return null;
/*      */     } 
/*      */     
/*  793 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canOpenChest(World world, int x, int y, int z, EntityLivingBase entity_living_base) {
/*  801 */     if (!world.isAirOrPassableBlock(x, y + 1, z, true) || entity_living_base.hasCurse(Curse.cannot_open_chests, true)) {
/*  802 */       return false;
/*      */     }
/*  804 */     int[] coords = getUnifiedNeighborCoordinates(world, x, y, z);
/*      */     
/*  806 */     return (coords == null || world.isAirOrPassableBlock(coords[0], coords[1] + 1, coords[2], true));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float dx, float dy, float dz) {
/*  841 */     if (player.onServer())
/*      */     {
/*  843 */       if (canOpenChest(world, x, y, z, player)) {
/*      */         
/*  845 */         IInventory inventory = getInventory(world, x, y, z);
/*      */         
/*  847 */         if (inventory != null)
/*      */         {
/*  849 */           player.displayGUIChest(x, y, z, inventory);
/*      */         }
/*      */       } else {
/*      */         
/*  853 */         world.playSoundAtBlock(x, y, z, "imported.random.chest_locked", 0.2F);
/*      */       } 
/*      */     }
/*      */     
/*  857 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IInventory getInventory(World par1World, int par2, int par3, int par4) {
/*  866 */     Object var5 = par1World.getBlockTileEntity(par2, par3, par4);
/*      */     
/*  868 */     if (var5 == null)
/*      */     {
/*  870 */       return null;
/*      */     }
/*  872 */     if (par1World.isBlockNormalCube(par2, par3 + 1, par4))
/*      */     {
/*  874 */       return null;
/*      */     }
/*  876 */     if (isOcelotBlockingChest(par1World, par2, par3, par4))
/*      */     {
/*  878 */       return null;
/*      */     }
/*  880 */     if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID && (par1World.isBlockNormalCube(par2 - 1, par3 + 1, par4) || isOcelotBlockingChest(par1World, par2 - 1, par3, par4)))
/*      */     {
/*  882 */       return null;
/*      */     }
/*  884 */     if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID && (par1World.isBlockNormalCube(par2 + 1, par3 + 1, par4) || isOcelotBlockingChest(par1World, par2 + 1, par3, par4)))
/*      */     {
/*  886 */       return null;
/*      */     }
/*  888 */     if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID && (par1World.isBlockNormalCube(par2, par3 + 1, par4 - 1) || isOcelotBlockingChest(par1World, par2, par3, par4 - 1)))
/*      */     {
/*  890 */       return null;
/*      */     }
/*  892 */     if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID && (par1World.isBlockNormalCube(par2, par3 + 1, par4 + 1) || isOcelotBlockingChest(par1World, par2, par3, par4 + 1)))
/*      */     {
/*  894 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  898 */     if (this instanceof BlockStrongbox) {
/*  899 */       return (IInventory)var5;
/*      */     }
/*  901 */     if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID)
/*      */     {
/*  903 */       var5 = new InventoryLargeChest("container.chestDouble", (TileEntityChest)par1World.getBlockTileEntity(par2 - 1, par3, par4), (IInventory)var5);
/*      */     }
/*      */     
/*  906 */     if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
/*      */     {
/*  908 */       var5 = new InventoryLargeChest("container.chestDouble", (IInventory)var5, (TileEntityChest)par1World.getBlockTileEntity(par2 + 1, par3, par4));
/*      */     }
/*      */     
/*  911 */     if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID)
/*      */     {
/*  913 */       var5 = new InventoryLargeChest("container.chestDouble", (TileEntityChest)par1World.getBlockTileEntity(par2, par3, par4 - 1), (IInventory)var5);
/*      */     }
/*      */     
/*  916 */     if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID)
/*      */     {
/*  918 */       var5 = new InventoryLargeChest("container.chestDouble", (IInventory)var5, (TileEntityChest)par1World.getBlockTileEntity(par2, par3, par4 + 1));
/*      */     }
/*      */     
/*  921 */     return (IInventory)var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TileEntity createNewTileEntity(World par1World) {
/*  933 */     return new TileEntityChest(this.chest_type, this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canProvidePower() {
/*  942 */     return (this.chest_type == EnumChestType.trapped);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  952 */     if (!canProvidePower())
/*      */     {
/*  954 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  958 */     int var6 = ((TileEntityChest)par1IBlockAccess.getBlockTileEntity(par2, par3, par4)).numUsingPlayers;
/*  959 */     return MathHelper.clamp_int(var6, 0, 15);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  969 */     return (par5 == 1) ? isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5) : 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3) {
/*      */     EntityOcelot var6;
/*  978 */     Iterator<EntityOcelot> var4 = par0World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB(par1, (par2 + 1), par3, (par1 + 1), (par2 + 2), (par3 + 1))).iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  983 */       if (!var4.hasNext())
/*      */       {
/*  985 */         return false;
/*      */       }
/*      */       
/*  988 */       EntityOcelot var5 = var4.next();
/*  989 */       var6 = var5;
/*      */     }
/*  991 */     while (!var6.isSitting());
/*      */     
/*  993 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasComparatorInputOverride() {
/* 1002 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 1011 */     return Container.calcRedstoneFromInventory(getInventory(par1World, par2, par3, par4));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerIcons(IconRegister par1IconRegister) {
/* 1020 */     this.blockIcon = par1IconRegister.registerIcon("planks_oak");
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 1025 */     return empty_handed;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBePlacedOnBlock(int metadata, Block block_below, int block_below_metadata, double block_below_bounds_max_y) {
/* 1030 */     return (block_below.isTopFlatAndSolid(block_below_metadata) && super.canBePlacedOnBlock(metadata, block_below, block_below_metadata, block_below_bounds_max_y));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 1035 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 1040 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */