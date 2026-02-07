/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class BlockAnvil
/*     */   extends BlockFalling
/*     */   implements ITileEntityProvider
/*     */ {
/*   9 */   public static final String[] statuses = new String[] { "intact", "slightlyDamaged", "veryDamaged" };
/*     */   
/*  11 */   private static final String[] anvilIconNames = new String[] { "top_damaged_0", "top_damaged_1", "top_damaged_2" };
/*     */   
/*     */   public int field_82521_b;
/*     */   
/*     */   private Icon[] iconArray;
/*     */   public Material metal_type;
/*  17 */   private final int[] minimum_damage_for_stage = new int[3];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockAnvil(int par1, Material metal_type) {
/*  23 */     super(par1, Material.anvil, (new BlockConstants()).setNeverHidesAdjacentFaces());
/*  24 */     setLightOpacity(0);
/*  25 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */     
/*  27 */     this.metal_type = metal_type;
/*  28 */     setHardnessRelativeToWood(BlockHardness.log);
/*     */     
/*  30 */     setMaxStackSize(1);
/*     */     
/*  32 */     for (int i = 0; i < this.minimum_damage_for_stage.length; i++) {
/*  33 */       this.minimum_damage_for_stage[i] = getMinimumDamageForStage(i, false);
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
/*     */   public Icon getIcon(int par1, int par2) {
/*  58 */     if (this.field_82521_b == 3 && par1 == 1) {
/*     */       
/*  60 */       int var3 = (par2 >> 2) % this.iconArray.length;
/*  61 */       return this.iconArray[var3];
/*     */     } 
/*     */ 
/*     */     
/*  65 */     return this.blockIcon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  76 */     this.blockIcon = par1IconRegister.registerIcon("anvil/" + this.metal_type.name + "/base");
/*  77 */     this.iconArray = new Icon[anvilIconNames.length];
/*     */     
/*  79 */     for (int var2 = 0; var2 < this.iconArray.length; var2++)
/*     */     {
/*     */       
/*  82 */       this.iconArray[var2] = par1IconRegister.registerIcon("anvil/" + this.metal_type.name + "/" + anvilIconNames[var2]);
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
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 121 */     EnumDirection direction = entity.getDirectionFromYaw();
/*     */     
/* 123 */     int subtype = item_stack.getItemSubtype();
/*     */     
/* 125 */     int metadata = (subtype == 1) ? 4 : ((subtype == 2) ? 8 : 0);
/*     */     
/* 127 */     return metadata | (direction.isWest() ? 0 : (direction.isNorth() ? 1 : (direction.isEast() ? 2 : 3)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDirection getDirectionFacing(int metadata) {
/* 132 */     int orientation = metadata & 0x3;
/*     */     
/* 134 */     return (orientation == 0) ? EnumDirection.EAST : ((orientation == 1) ? EnumDirection.SOUTH : ((orientation == 2) ? EnumDirection.WEST : EnumDirection.NORTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 139 */     return metadata & 0xFFFFFFFC | (direction.isEast() ? 0 : (direction.isSouth() ? 1 : (direction.isWest() ? 2 : (direction.isNorth() ? 3 : -1))));
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 160 */     if (!world.isAirOrPassableBlock(x, y + 1, z, false)) {
/* 161 */       return false;
/*     */     }
/* 163 */     if (player.onServer()) {
/*     */ 
/*     */ 
/*     */       
/* 167 */       TileEntityAnvil tile_entity = (TileEntityAnvil)world.getBlockTileEntity(x, y, z);
/*     */       
/* 169 */       if (tile_entity != null)
/*     */       {
/*     */ 
/*     */         
/* 173 */         player.displayGUIAnvil(x, y, z);
/*     */       }
/*     */     } 
/*     */     
/* 177 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 185 */     return 35;
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
/*     */   public String getMetadataNotes() {
/* 198 */     return "Bits 1 and 2 used for orientation, bit 4 set if slightly worn, and bit 8 set if badly worn";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/* 203 */     return (metadata >= 0 && metadata < 12);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/* 208 */     return metadata >> 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 216 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x3;
/*     */     
/* 218 */     if (var5 != 3 && var5 != 1) {
/*     */       
/* 220 */       setBlockBoundsForCurrentThread(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D);
/*     */     }
/*     */     else {
/*     */       
/* 224 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D);
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
/*     */   public void getItemStacks(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/* 250 */     par3List.add(new ItemStack(par1, 1, 0));
/* 251 */     par3List.add((new ItemStack(par1, 1, 1)).setItemDamage(getMinimumDamageForStage(1)));
/* 252 */     par3List.add((new ItemStack(par1, 1, 2)).setItemDamage(getMinimumDamageForStage(2)));
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
/*     */   protected void onStartFalling(World world, int x, int y, int z, EntityFallingSand entity_falling_sand) {
/* 265 */     entity_falling_sand.setIsAnvil(true);
/*     */     
/* 267 */     TileEntityAnvil tile_entity = (TileEntityAnvil)world.getBlockTileEntity(x, y, z);
/*     */     
/* 269 */     entity_falling_sand.item_damage = tile_entity.damage;
/* 270 */     entity_falling_sand.custom_name = tile_entity.getCustomInvName();
/*     */     
/* 272 */     world.removeBlockTileEntity(x, y, z);
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
/*     */   public void onFinishFalling(World par1World, int par2, int par3, int par4, int par5, EntityFallingSand entity_falling_sand) {
/* 287 */     par1World.playAuxSFX(1022, par2, par3, par4, 0);
/*     */     
/* 289 */     TileEntityAnvil tile_entity_anvil = (TileEntityAnvil)par1World.getBlockTileEntity(par2, par3, par4);
/*     */     
/* 291 */     tile_entity_anvil.damage = entity_falling_sand.item_damage;
/* 292 */     tile_entity_anvil.setCustomInvName(entity_falling_sand.custom_name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 301 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 306 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 311 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsRepairable(ItemStack item_to_repair, ItemStack repair_item) {
/* 317 */     if (item_to_repair == null || repair_item == null || !item_to_repair.hasRepairCost() || item_to_repair.getRepairItem() != repair_item.getItem()) {
/* 318 */       return false;
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
/* 340 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCraftingDifficultyAsComponent(int metadata) {
/* 345 */     return -1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 350 */     item_block.addMaterial(new Material[] { this.metal_type });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReplaceBlock(int metadata, Block existing_block, int existing_block_metadata) {
/* 355 */     if (super.canReplaceBlock(metadata, existing_block, existing_block_metadata)) {
/* 356 */       return true;
/*     */     }
/* 358 */     return (existing_block != null && existing_block.blockMaterial == Material.circuits);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World world) {
/* 363 */     return new TileEntityAnvil();
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World world, int x, int y, int z, int block_id, int metadata) {
/* 368 */     super.breakBlock(world, x, y, z, block_id, metadata);
/* 369 */     world.removeBlockTileEntity(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockEventReceived(World world, int x, int y, int z, int block_id, int event_id) {
/* 376 */     super.onBlockEventReceived(world, x, y, z, block_id, event_id);
/*     */     
/* 378 */     TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
/*     */     
/* 380 */     return (tile_entity != null) ? tile_entity.receiveClientEvent(block_id, event_id) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBaseDurabilityPerIngot() {
/* 385 */     return 1600;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDurability() {
/* 390 */     return (int)((getBaseDurabilityPerIngot() * 31) * this.metal_type.durability);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDamageStage(int damage) {
/* 396 */     float damage_factor = damage / getDurability();
/*     */     
/* 398 */     if (damage_factor >= 1.0F)
/* 399 */       return 3; 
/* 400 */     if (damage_factor >= 0.8F)
/* 401 */       return 2; 
/* 402 */     if (damage_factor >= 0.5F) {
/* 403 */       return 1;
/*     */     }
/* 405 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinimumDamageForStage(int stage) {
/* 411 */     return getMinimumDamageForStage(stage, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinimumDamageForStage(int stage, boolean use_table_value) {
/* 417 */     if (use_table_value) {
/* 418 */       return this.minimum_damage_for_stage[stage];
/*     */     }
/* 420 */     int damage = 0;
/*     */     
/* 422 */     while (getDamageStage(damage) < stage) {
/* 423 */       damage++;
/*     */     }
/* 425 */     return damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 430 */     TileEntityAnvil tile_entity_anvil = (TileEntityAnvil)info.tile_entity;
/*     */     
/* 432 */     return super.dropBlockAsEntityItem(info.setDamage(tile_entity_anvil.damage));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryPlaceFromHeldItem(int x, int y, int z, EnumFace face, ItemStack item_stack, EntityPlayer player, float offset_x, float offset_y, float offset_z, boolean perform_placement_check, boolean drop_existing_block, boolean test_only) {
/* 442 */     if (super.tryPlaceFromHeldItem(x, y, z, face, item_stack, player, offset_x, offset_y, offset_z, perform_placement_check, drop_existing_block, test_only)) {
/*     */       
/* 444 */       if (!test_only && player.onServer()) {
/*     */         
/* 446 */         TileEntityAnvil tile_entity_anvil = (TileEntityAnvil)player.worldObj.getBlockTileEntity(x, y, z);
/*     */         
/* 448 */         tile_entity_anvil.addDamage(player.worldObj, x, y, z, item_stack.getItemDamage());
/*     */       } 
/*     */       
/* 451 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 455 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 461 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 466 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMetalType() {
/* 471 */     return this.metal_type;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockAnvil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */