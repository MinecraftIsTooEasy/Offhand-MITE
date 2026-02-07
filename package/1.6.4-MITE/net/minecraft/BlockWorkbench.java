/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class BlockWorkbench
/*     */   extends Block
/*     */ {
/*     */   private Icon workbenchIconTop;
/*     */   private Icon icon_flint_top;
/*     */   private Icon icon_obsidian_top;
/*  10 */   protected Icon[] front_icons = new Icon[15];
/*  11 */   protected Icon[] side_icons = new Icon[15];
/*     */   
/*  13 */   public static final Material[] tool_materials = new Material[] { Material.flint, Material.copper, Material.silver, Material.gold, Material.iron, Material.ancient_metal, Material.mithril, Material.adamantium, Material.obsidian };
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockWorkbench(int par1) {
/*  18 */     super(par1, Material.wood, new BlockConstants());
/*  19 */     setHardness(BlockHardness.workbench);
/*  20 */     setCreativeTab(CreativeTabs.tabDecorations);
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
/*     */   public Icon getIcon(int side, int metadata) {
/*  33 */     if (metadata < 4) {
/*  34 */       return (side == 1) ? this.icon_flint_top : Block.wood.getIcon(side, metadata);
/*     */     }
/*  36 */     if (metadata > 10) {
/*  37 */       return (side == 1) ? this.icon_obsidian_top : Block.wood.getIcon(side, metadata - 11);
/*     */     }
/*  39 */     if (side == 0) {
/*  40 */       return Block.planks.getBlockTextureFromSide(side);
/*     */     }
/*  42 */     if (side == 1) {
/*  43 */       return this.workbenchIconTop;
/*     */     }
/*  45 */     if (side == 2 || side == 3) {
/*  46 */       return this.front_icons[metadata];
/*     */     }
/*  48 */     return this.side_icons[metadata];
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  64 */     this.icon_flint_top = par1IconRegister.registerIcon("crafting_table/flint/top");
/*  65 */     this.icon_obsidian_top = par1IconRegister.registerIcon("crafting_table/obsidian/top");
/*  66 */     this.workbenchIconTop = par1IconRegister.registerIcon("crafting_table_top");
/*     */ 
/*     */     
/*  69 */     for (int i = 4; i < this.front_icons.length - 4; i++) {
/*     */       
/*  71 */       this.front_icons[i] = par1IconRegister.registerIcon("crafting_table/" + (getToolMaterial(i)).name + "/front");
/*  72 */       this.side_icons[i] = par1IconRegister.registerIcon("crafting_table/" + (getToolMaterial(i)).name + "/side");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  78 */     String[] array = new String[getNumSubBlocks()];
/*     */     
/*  80 */     for (int i = 0; i < array.length; i++) {
/*  81 */       array[i] = i + "=" + getToolMaterial(i).getCapitalizedName() + " Tools";
/*     */     }
/*  83 */     return StringHelper.implode(array, ", ", true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  89 */     return (metadata >= 0 && metadata < 15);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/*  94 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Material getToolMaterial(int metadata) {
/*  99 */     if (metadata > 10) {
/* 100 */       return tool_materials[8];
/*     */     }
/* 102 */     return (metadata < 4) ? tool_materials[0] : tool_materials[metadata - 3];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack getBlockComponent(int metadata) {
/* 108 */     Material tool_material = getToolMaterial(metadata);
/*     */     
/* 110 */     if (tool_material == Material.flint) {
/* 111 */       return new ItemStack(Block.wood, 1, metadata);
/*     */     }
/* 113 */     if (tool_material == Material.obsidian) {
/* 114 */       return new ItemStack(Block.wood, 1, metadata - 11);
/*     */     }
/* 116 */     return null;
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 140 */     if (player.onServer() && world.isAirOrPassableBlock(x, y + 1, z, false)) {
/*     */       
/* 142 */       Block block_above = world.getBlock(x, y + 1, z);
/*     */       
/* 144 */       if (block_above == null || !block_above.hidesAdjacentSide(world, x, y + 1, z, this, 1)) {
/* 145 */         player.displayGUIWorkbench(x, y, z);
/*     */       }
/*     */     } 
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 153 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 158 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */