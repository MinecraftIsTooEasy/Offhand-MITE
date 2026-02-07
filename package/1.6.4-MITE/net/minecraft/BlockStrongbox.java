/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockStrongbox
/*     */   extends BlockChest
/*     */ {
/*     */   protected BlockStrongbox(int id, Material material) {
/*  11 */     super(id, EnumChestType.strongbox, material);
/*     */     
/*  13 */     modifyMinHarvestLevel(1);
/*  14 */     setHardnessRelativeToWood(BlockHardness.log);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  19 */     setBlockBoundsForCurrentThread(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
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
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/*  36 */     if (!test_only)
/*     */     {
/*  38 */       if (placer instanceof EntityPlayer) {
/*  39 */         ((TileEntityStrongbox)world.getBlockTileEntity(x, y, z)).setOwner(placer.getAsPlayer());
/*     */       }
/*     */     }
/*  42 */     return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World world) {
/*  47 */     return new TileEntityStrongbox(EnumChestType.strongbox, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getUnifiedNeighborCoordinates(World world, int x, int y, int z) {
/*  52 */     return null;
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  89 */     if (player.onServer()) {
/*     */       
/*  91 */       TileEntityStrongbox tile_strongbox = (TileEntityStrongbox)world.getBlockTileEntity(x, y, z);
/*     */       
/*  93 */       if (player.inCreativeMode() || tile_strongbox.isOwner(player))
/*     */       {
/*  95 */         return super.onBlockActivated(world, x, y, z, player, face, offset_x, offset_y, offset_z);
/*     */       }
/*     */ 
/*     */       
/*  99 */       if (tile_strongbox.lidAngle == 0.0F) {
/* 100 */         world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "imported.random.chest_locked", 0.2F);
/*     */       }
/* 102 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 106 */     return super.onBlockActivated(world, x, y, z, player, face, offset_x, offset_y, offset_z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 111 */     if (this.blockMaterial == Material.copper) {
/* 112 */       this.blockIcon = par1IconRegister.registerIcon("copper_block");
/* 113 */     } else if (this.blockMaterial == Material.silver) {
/* 114 */       this.blockIcon = par1IconRegister.registerIcon("silver_block");
/* 115 */     } else if (this.blockMaterial == Material.gold) {
/* 116 */       this.blockIcon = par1IconRegister.registerIcon("gold_block");
/* 117 */     } else if (this.blockMaterial == Material.iron) {
/* 118 */       this.blockIcon = par1IconRegister.registerIcon("iron_block");
/* 119 */     } else if (this.blockMaterial == Material.mithril) {
/* 120 */       this.blockIcon = par1IconRegister.registerIcon("mithril_block");
/* 121 */     } else if (this.blockMaterial == Material.adamantium) {
/* 122 */       this.blockIcon = par1IconRegister.registerIcon("adamantium_block");
/* 123 */     } else if (this.blockMaterial == Material.ancient_metal) {
/* 124 */       this.blockIcon = par1IconRegister.registerIcon("ancient_metal_block");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
/* 129 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 134 */     if (!(entity_living_base instanceof EntityPlayer)) {
/* 135 */       return false;
/*     */     }
/* 137 */     TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
/*     */     
/* 139 */     return (tile_entity instanceof TileEntityStrongbox && ((TileEntityStrongbox)tile_entity).isOwner((EntityPlayer)entity_living_base));
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 144 */     EntityPlayer player = info.getResponsiblePlayer();
/*     */     
/* 146 */     if (player != null && !player.inCreativeMode() && ((TileEntityStrongbox)info.tile_entity).isOwner(player)) {
/* 147 */       return super.dropBlockAsEntityItem(info);
/*     */     }
/* 149 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCraftingDifficultyAsComponent(int metadata) {
/* 154 */     return -1.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockStrongbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */