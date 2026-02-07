/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockFurnace
/*     */   extends BlockDirectionalWithTileEntity
/*     */ {
/*  11 */   private final Random furnaceRand = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final boolean isActive;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean keepFurnaceInventory;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Icon furnaceIconTop;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Icon furnaceIconFront;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockFurnace(int par1, Material material, boolean par2) {
/*  36 */     super(par1, material, new BlockConstants());
/*  37 */     this.isActive = par2;
/*  38 */     setMaxStackSize(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  43 */     String[] array = new String[4];
/*     */     
/*  45 */     for (int i = 0; i < array.length; i++) {
/*  46 */       array[i] = (i + 2) + "=" + getDirectionFacing(i + 2).getDescriptor(true);
/*     */     }
/*  48 */     return StringHelper.implode(array, ", ", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  53 */     return (metadata > 1 && metadata < 6);
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
/*  66 */     return !this.isActive;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack createStackedBlock(int par1) {
/*  71 */     return new ItemStack(getIdleBlockID());
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/*  76 */     if (info.wasExploded()) {
/*     */ 
/*     */ 
/*     */       
/*  80 */       Block model_block, furnace_block = Block.getBlock(getIdleBlockID());
/*     */       
/*  82 */       if (furnace_block == Block.furnaceClayIdle)
/*     */       {
/*  84 */         return 0; } 
/*  85 */       if (furnace_block == Block.furnaceSandstoneIdle) {
/*  86 */         model_block = Block.sandStone;
/*  87 */       } else if (furnace_block == Block.furnaceIdle) {
/*  88 */         model_block = Block.cobblestone;
/*  89 */       } else if (furnace_block == Block.furnaceObsidianIdle) {
/*  90 */         model_block = Block.obsidian;
/*  91 */       } else if (furnace_block == Block.furnaceNetherrackIdle) {
/*  92 */         model_block = Block.netherrack;
/*     */       } else {
/*  94 */         return 0;
/*     */       } 
/*  96 */       return model_block.dropBlockAsEntityItem(info.setBlock(model_block, 0));
/*     */     } 
/*     */     
/*  99 */     return super.dropBlockAsEntityItem(info);
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
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/* 113 */     return getDirectionFacingStandard6(metadata, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 118 */     return direction.isNorth() ? 2 : (direction.isSouth() ? 3 : (direction.isWest() ? 4 : (direction.isEast() ? 5 : -1)));
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
/*     */   public Icon getIcon(int par1, int par2) {
/* 163 */     return (par1 == 1) ? this.furnaceIconTop : ((par1 == 0) ? this.furnaceIconTop : ((par1 != par2) ? this.blockIcon : this.furnaceIconFront));
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
/*     */   public abstract void registerIcons(IconRegister paramIconRegister);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 184 */     if (this.isActive) {
/*     */       
/* 186 */       int var6 = par1World.getBlockMetadata(par2, par3, par4);
/* 187 */       float var7 = par2 + 0.5F;
/* 188 */       float var8 = par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
/* 189 */       float var9 = par4 + 0.5F;
/* 190 */       float var10 = 0.52F;
/* 191 */       float var11 = par5Random.nextFloat() * 0.6F - 0.3F;
/*     */       
/* 193 */       if (var6 == 4) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 198 */         par1World.spawnParticle(EnumParticle.smoke, (var7 - var10), var8, (var9 + var11), 0.0D, 0.0D, 0.0D);
/* 199 */         par1World.spawnParticle(EnumParticle.flame, (var7 - var10), var8, (var9 + var11), 0.0D, 0.0D, 0.0D);
/*     */       }
/* 201 */       else if (var6 == 5) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 206 */         par1World.spawnParticle(EnumParticle.smoke, (var7 + var10), var8, (var9 + var11), 0.0D, 0.0D, 0.0D);
/* 207 */         par1World.spawnParticle(EnumParticle.flame, (var7 + var10), var8, (var9 + var11), 0.0D, 0.0D, 0.0D);
/*     */       }
/* 209 */       else if (var6 == 2) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 214 */         par1World.spawnParticle(EnumParticle.smoke, (var7 + var11), var8, (var9 - var10), 0.0D, 0.0D, 0.0D);
/* 215 */         par1World.spawnParticle(EnumParticle.flame, (var7 + var11), var8, (var9 - var10), 0.0D, 0.0D, 0.0D);
/*     */       }
/* 217 */       else if (var6 == 3) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 222 */         par1World.spawnParticle(EnumParticle.smoke, (var7 + var11), var8, (var9 + var10), 0.0D, 0.0D, 0.0D);
/* 223 */         par1World.spawnParticle(EnumParticle.flame, (var7 + var11), var8, (var9 + var10), 0.0D, 0.0D, 0.0D);
/*     */       } 
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 257 */     if (!world.isAirOrPassableBlock(World.getNeighboringBlockCoords(x, y, z, getDirectionFacing(world.getBlockMetadata(x, y, z)).getFace()), true)) {
/* 258 */       return false;
/*     */     }
/* 260 */     if (player.onServer()) {
/*     */       
/* 262 */       TileEntityFurnace tile_entity = (TileEntityFurnace)world.getBlockTileEntity(x, y, z);
/*     */       
/* 264 */       if (tile_entity != null)
/*     */       {
/*     */         
/* 267 */         player.displayGUIFurnace(tile_entity);
/*     */       }
/*     */     } 
/*     */     
/* 271 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getIdleBlockID();
/*     */ 
/*     */   
/*     */   public abstract int getActiveBlockID();
/*     */ 
/*     */   
/*     */   public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4) {
/* 282 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/* 283 */     TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
/* 284 */     keepFurnaceInventory = true;
/*     */     
/* 286 */     BlockFurnace block_furnace = (BlockFurnace)Block.blocksList[par1World.getBlockId(par2, par3, par4)];
/*     */     
/* 288 */     if (par0) {
/*     */ 
/*     */ 
/*     */       
/* 292 */       par1World.setBlock(par2, par3, par4, block_furnace.getActiveBlockID(), var5, 3);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 298 */       par1World.setBlock(par2, par3, par4, block_furnace.getIdleBlockID(), var5, 3);
/*     */     } 
/*     */     
/* 301 */     keepFurnaceInventory = false;
/*     */ 
/*     */     
/* 304 */     if (var6 != null) {
/*     */       
/* 306 */       var6.validate();
/* 307 */       par1World.setBlockTileEntity(par2, par3, par4, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/* 316 */     return new TileEntityFurnace();
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
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 361 */     if (!keepFurnaceInventory) {
/*     */       
/* 363 */       TileEntityFurnace var7 = (TileEntityFurnace)par1World.getBlockTileEntity(par2, par3, par4);
/*     */       
/* 365 */       if (var7 != null) {
/*     */         
/* 367 */         for (int var8 = 0; var8 < var7.getSizeInventory(); var8++) {
/*     */           
/* 369 */           ItemStack var9 = var7.getStackInSlot(var8);
/*     */           
/* 371 */           if (var9 != null) {
/*     */             
/* 373 */             float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
/* 374 */             float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
/* 375 */             float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
/*     */             
/* 377 */             while (var9.stackSize > 0) {
/*     */               
/* 379 */               int var13 = this.furnaceRand.nextInt(21) + 10;
/*     */               
/* 381 */               if (var13 > var9.stackSize)
/*     */               {
/* 383 */                 var13 = var9.stackSize;
/*     */               }
/*     */               
/* 386 */               var9.stackSize -= var13;
/*     */               
/* 388 */               EntityItem var14 = new EntityItem(par1World, (par2 + var10), (par3 + var11), (par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemSubtype()));
/*     */               
/* 390 */               if (var9.isItemDamaged()) {
/* 391 */                 var14.getEntityItem().setItemDamage(var9.getItemDamage());
/*     */               }
/* 393 */               if (var9.getItem().hasQuality()) {
/* 394 */                 var14.getEntityItem().setQuality(var9.getQuality());
/*     */               }
/* 396 */               if (var9.hasTagCompound())
/*     */               {
/* 398 */                 var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
/*     */               }
/*     */               
/* 401 */               float var15 = 0.05F;
/* 402 */               var14.motionX = ((float)this.furnaceRand.nextGaussian() * var15);
/* 403 */               var14.motionY = ((float)this.furnaceRand.nextGaussian() * var15 + 0.2F);
/* 404 */               var14.motionZ = ((float)this.furnaceRand.nextGaussian() * var15);
/* 405 */               par1World.spawnEntityInWorld(var14);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 410 */         par1World.func_96440_m(par2, par3, par4, par5);
/*     */       } 
/*     */     } 
/*     */     
/* 414 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 423 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 432 */     return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
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
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 445 */     return getIdleBlockID();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getMaxHeatLevel();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCraftingDifficultyAsComponent(int metadata) {
/* 457 */     return -1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 462 */     return this.isActive ? "active" : "idle";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOven() {
/* 467 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean acceptsLargeItems() {
/* 473 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */