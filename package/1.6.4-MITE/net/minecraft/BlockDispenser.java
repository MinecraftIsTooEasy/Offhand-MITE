/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class BlockDispenser
/*     */   extends BlockDirectionalWithTileEntity
/*     */ {
/*   9 */   public static final IRegistry dispenseBehaviorRegistry = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
/*  10 */   protected Random random = new Random();
/*     */   
/*     */   protected Icon furnaceTopIcon;
/*     */   
/*     */   protected Icon furnaceFrontIcon;
/*     */   protected Icon field_96473_e;
/*     */   
/*     */   protected BlockDispenser(int par1) {
/*  18 */     super(par1, Material.stone, new BlockConstants());
/*  19 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  24 */     String[] array = new String[6];
/*     */     
/*  26 */     for (int i = 0; i < array.length; i++) {
/*  27 */       array[i] = i + "=" + getDirectionFacing(i).getDescriptor(true);
/*     */     }
/*  29 */     return StringHelper.implode(array, ", ", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  34 */     return (metadata >= 0 && metadata < 6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World par1World) {
/*  42 */     return 4;
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
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/*  94 */     return getDirectionFacingStandard6(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/*  99 */     metadata = direction.isDown() ? 0 : (direction.isUp() ? 1 : (direction.isNorth() ? 2 : (direction.isSouth() ? 3 : (direction.isWest() ? 4 : (direction.isEast() ? 5 : -1)))));
/*     */     
/* 101 */     return metadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 109 */     int var3 = par2 & 0x7;
/* 110 */     return (par1 == var3) ? ((var3 != 1 && var3 != 0) ? this.furnaceFrontIcon : this.field_96473_e) : ((var3 != 1 && var3 != 0) ? ((par1 != 1 && par1 != 0) ? this.blockIcon : this.furnaceTopIcon) : this.furnaceTopIcon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 119 */     this.blockIcon = par1IconRegister.registerIcon("furnace_side");
/* 120 */     this.furnaceTopIcon = par1IconRegister.registerIcon("furnace_top");
/* 121 */     this.furnaceFrontIcon = par1IconRegister.registerIcon(getTextureName() + "_front_horizontal");
/* 122 */     this.field_96473_e = par1IconRegister.registerIcon(getTextureName() + "_front_vertical");
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float dx, float dy, float dz) {
/* 149 */     if (!world.isAirOrPassableBlock(World.getNeighboringBlockCoords(x, y, z, getDirectionFacing(world.getBlockMetadata(x, y, z)).getFace()), true)) {
/* 150 */       return false;
/*     */     }
/* 152 */     if (player.onServer()) {
/*     */       
/* 154 */       TileEntityDispenser tile_entity = (TileEntityDispenser)world.getBlockTileEntity(x, y, z);
/*     */       
/* 156 */       if (tile_entity != null) {
/* 157 */         player.displayGUIDispenser(tile_entity);
/*     */       }
/*     */     } 
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dispense(World par1World, int par2, int par3, int par4) {
/* 165 */     BlockSourceImpl var5 = new BlockSourceImpl(par1World, par2, par3, par4);
/* 166 */     TileEntityDispenser var6 = (TileEntityDispenser)var5.getBlockTileEntity();
/*     */     
/* 168 */     if (var6 != null) {
/*     */       
/* 170 */       int var7 = var6.getRandomStackFromInventory();
/*     */       
/* 172 */       if (var7 < 0) {
/*     */         
/* 174 */         par1World.playAuxSFX(1001, par2, par3, par4, 0);
/*     */       }
/*     */       else {
/*     */         
/* 178 */         ItemStack var8 = var6.getStackInSlot(var7);
/* 179 */         IBehaviorDispenseItem var9 = getBehaviorForItemStack(var8);
/*     */         
/* 181 */         if (var9 != IBehaviorDispenseItem.itemDispenseBehaviorProvider) {
/*     */           
/* 183 */           ItemStack var10 = var9.dispense(var5, var8);
/* 184 */           var6.setInventorySlotContents(var7, (var10.stackSize == 0) ? null : var10);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IBehaviorDispenseItem getBehaviorForItemStack(ItemStack par1ItemStack) {
/* 195 */     return (IBehaviorDispenseItem)dispenseBehaviorRegistry.getObject(par1ItemStack.getItem());
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 221 */     boolean is_indirectly_powered = (world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z));
/*     */     
/* 223 */     int metadata = world.getBlockMetadata(x, y, z);
/* 224 */     boolean var8 = ((metadata & 0x8) != 0);
/*     */     
/* 226 */     if (is_indirectly_powered && !var8) {
/*     */       
/* 228 */       world.scheduleBlockUpdate(x, y, z, this.blockID, tickRate(world));
/* 229 */       world.setBlockMetadataWithNotify(x, y, z, metadata | 0x8, 4);
/*     */       
/* 231 */       return true;
/*     */     } 
/* 233 */     if (!is_indirectly_powered && var8) {
/*     */       
/* 235 */       world.setBlockMetadataWithNotify(x, y, z, metadata & 0xFFFFFFF7, 4);
/*     */       
/* 237 */       return true;
/*     */     } 
/*     */     
/* 240 */     return false;
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 256 */     dispense(world, x, y, z);
/*     */     
/* 258 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/* 266 */     return new TileEntityDispenser();
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
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 309 */     TileEntityDispenser var7 = (TileEntityDispenser)par1World.getBlockTileEntity(par2, par3, par4);
/*     */     
/* 311 */     if (var7 != null) {
/*     */       
/* 313 */       for (int var8 = 0; var8 < var7.getSizeInventory(); var8++) {
/*     */         
/* 315 */         ItemStack var9 = var7.getStackInSlot(var8);
/*     */         
/* 317 */         if (var9 != null) {
/*     */           
/* 319 */           float var10 = this.random.nextFloat() * 0.8F + 0.1F;
/* 320 */           float var11 = this.random.nextFloat() * 0.8F + 0.1F;
/* 321 */           float var12 = this.random.nextFloat() * 0.8F + 0.1F;
/*     */           
/* 323 */           while (var9.stackSize > 0) {
/*     */             
/* 325 */             int var13 = this.random.nextInt(21) + 10;
/*     */             
/* 327 */             if (var13 > var9.stackSize)
/*     */             {
/* 329 */               var13 = var9.stackSize;
/*     */             }
/*     */             
/* 332 */             var9.stackSize -= var13;
/*     */             
/* 334 */             EntityItem var14 = new EntityItem(par1World, (par2 + var10), (par3 + var11), (par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemSubtype()));
/*     */             
/* 336 */             if (var9.isItemDamaged()) {
/* 337 */               var14.getEntityItem().setItemDamage(var9.getItemDamage());
/*     */             }
/* 339 */             if (var9.getItem().hasQuality()) {
/* 340 */               var14.getEntityItem().setQuality(var9.getQuality());
/*     */             }
/* 342 */             if (var9.hasTagCompound())
/*     */             {
/* 344 */               var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
/*     */             }
/*     */             
/* 347 */             float var15 = 0.05F;
/* 348 */             var14.motionX = ((float)this.random.nextGaussian() * var15);
/* 349 */             var14.motionY = ((float)this.random.nextGaussian() * var15 + 0.2F);
/* 350 */             var14.motionZ = ((float)this.random.nextGaussian() * var15);
/* 351 */             par1World.spawnEntityInWorld(var14);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 356 */       par1World.func_96440_m(par2, par3, par4, par5);
/*     */     } 
/*     */     
/* 359 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   public static IPosition getIPositionFromBlockSource(IBlockSource par0IBlockSource) {
/* 364 */     EnumFacing var1 = getFacing(par0IBlockSource.getBlockMetadata());
/* 365 */     double var2 = par0IBlockSource.getX() + 0.7D * var1.getFrontOffsetX();
/* 366 */     double var4 = par0IBlockSource.getY() + 0.7D * var1.getFrontOffsetY();
/* 367 */     double var6 = par0IBlockSource.getZ() + 0.7D * var1.getFrontOffsetZ();
/* 368 */     return new PositionImpl(var2, var4, var6);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumFacing getFacing(int par0) {
/* 373 */     return EnumFacing.getFront(par0 & 0x7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 382 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 391 */     return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 396 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 401 */     item_block.addMaterial(new Material[] { Material.stone, Material.wood, Material.redstone });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDispenser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */