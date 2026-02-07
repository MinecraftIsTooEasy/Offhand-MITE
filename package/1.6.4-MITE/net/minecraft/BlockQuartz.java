/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class BlockQuartz
/*     */   extends Block
/*     */ {
/*   7 */   public static final String[] quartzBlockTypes = new String[] { "default", "chiseled", "lines" };
/*   8 */   private static final String[] quartzBlockTextureTypes = new String[] { "side", "chiseled", "lines", null, null };
/*     */   
/*     */   private Icon[] quartzblockIcons;
/*     */   
/*     */   private Icon quartzblock_chiseled_top;
/*     */   private Icon quartzblock_lines_top;
/*     */   private Icon quartzblock_top;
/*     */   private Icon quartzblock_bottom;
/*     */   
/*     */   public BlockQuartz(int par1) {
/*  18 */     super(par1, Material.quartz, new BlockConstants());
/*  19 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  27 */     if (par2 != 2 && par2 != 3 && par2 != 4) {
/*     */       
/*  29 */       if (par1 != 1 && (par1 != 0 || par2 != 1)) {
/*     */         
/*  31 */         if (par1 == 0)
/*     */         {
/*  33 */           return this.quartzblock_bottom;
/*     */         }
/*     */ 
/*     */         
/*  37 */         if (par2 < 0 || par2 >= this.quartzblockIcons.length)
/*     */         {
/*  39 */           par2 = 0;
/*     */         }
/*     */         
/*  42 */         return this.quartzblockIcons[par2];
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  47 */       return (par2 == 1) ? this.quartzblock_chiseled_top : this.quartzblock_top;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  52 */     return (par2 == 2 && (par1 == 1 || par1 == 0)) ? this.quartzblock_lines_top : ((par2 == 3 && (par1 == 5 || par1 == 4)) ? this.quartzblock_lines_top : ((par2 == 4 && (par1 == 2 || par1 == 3)) ? this.quartzblock_lines_top : this.quartzblockIcons[par2]));
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
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  86 */     int metadata = super.getMetadataForPlacement(world, x, y, z, item_stack, entity, face, offset_x, offset_y, offset_z);
/*     */     
/*  88 */     if (metadata != 2) {
/*  89 */       return metadata;
/*     */     }
/*  91 */     if (face.isEastOrWest()) {
/*  92 */       metadata = 3;
/*  93 */     } else if (face.isNorthOrSouth()) {
/*  94 */       metadata = 4;
/*     */     } 
/*  96 */     return metadata;
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
/* 109 */     return "0=Regular, 1=Chiseled, 2=Pillar UD, 3=Pillar EW, 4=Pillar NS";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/* 114 */     return (metadata >= 0 && metadata < 5);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/* 119 */     return (metadata > 1 && metadata < 5) ? 2 : metadata;
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
/*     */   public int getRenderType() {
/* 136 */     return 39;
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 155 */     this.quartzblockIcons = new Icon[quartzBlockTextureTypes.length];
/*     */     
/* 157 */     for (int var2 = 0; var2 < this.quartzblockIcons.length; var2++) {
/*     */       
/* 159 */       if (quartzBlockTextureTypes[var2] == null) {
/*     */         
/* 161 */         this.quartzblockIcons[var2] = this.quartzblockIcons[var2 - 1];
/*     */       }
/*     */       else {
/*     */         
/* 165 */         this.quartzblockIcons[var2] = par1IconRegister.registerIcon(getTextureName() + "_" + quartzBlockTextureTypes[var2]);
/*     */       } 
/*     */     } 
/*     */     
/* 169 */     this.quartzblock_top = par1IconRegister.registerIcon(getTextureName() + "_" + "top");
/* 170 */     this.quartzblock_chiseled_top = par1IconRegister.registerIcon(getTextureName() + "_" + "chiseled_top");
/* 171 */     this.quartzblock_lines_top = par1IconRegister.registerIcon(getTextureName() + "_" + "lines_top");
/* 172 */     this.quartzblock_bottom = par1IconRegister.registerIcon(getTextureName() + "_" + "bottom");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCraftingDifficultyAsComponent(int metadata) {
/* 177 */     return ItemRock.getCraftingDifficultyAsComponent(Material.quartz) * 4.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 182 */     if (info.wasExploded()) {
/* 183 */       return dropBlockAsEntityItem(info, Item.netherQuartz.itemID, 0, 6, 0.5F);
/*     */     }
/* 185 */     return super.dropBlockAsEntityItem(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDirection getDirectionFacing(int metadata) {
/* 190 */     return (metadata == 3) ? EnumDirection.WEST : ((metadata == 4) ? EnumDirection.NORTH : ((metadata == 5) ? EnumDirection.DOWN : null));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 195 */     return direction.isEastOrWest() ? 3 : (direction.isNorthOrSouth() ? 4 : (direction.isUpOrDown() ? 5 : -1));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockQuartz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */