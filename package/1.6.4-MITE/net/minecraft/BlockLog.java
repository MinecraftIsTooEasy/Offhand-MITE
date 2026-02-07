/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockLog
/*     */   extends BlockRotatedPillar
/*     */   implements IBlockWithSubtypes
/*     */ {
/*     */   private BlockSubtypes subtypes;
/*     */   private Icon[] end_icons;
/*     */   
/*     */   protected BlockLog(int par1) {
/*  16 */     super(par1, Material.wood);
/*     */     
/*  18 */     this.subtypes = new BlockSubtypes(new String[] { "oak", "spruce", "birch", "jungle" });
/*     */     
/*  20 */     modifyMinHarvestLevel(1);
/*  21 */     setHardness(BlockHardness.log);
/*  22 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/*  27 */     byte var7 = 4;
/*  28 */     int var8 = var7 + 1;
/*     */     
/*  30 */     if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
/*     */     {
/*  32 */       for (int var9 = -var7; var9 <= var7; var9++) {
/*     */         
/*  34 */         for (int var10 = -var7; var10 <= var7; var10++) {
/*     */           
/*  36 */           for (int var11 = -var7; var11 <= var7; var11++) {
/*     */             
/*  38 */             int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);
/*     */             
/*  40 */             if (var12 == Block.leaves.blockID) {
/*     */               
/*  42 */               int var13 = par1World.getBlockMetadata(par2 + var9, par3 + var10, par4 + var11);
/*     */               
/*  44 */               if ((var13 & 0x8) == 0)
/*     */               {
/*  46 */                 par1World.setBlockMetadataWithNotify(par2 + var9, par3 + var10, par4 + var11, var13 | 0x8, 4);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Icon getSideIcon(int par1) {
/*  57 */     return this.subtypes.getIcon(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Icon getEndIcon(int par1) {
/*  62 */     return this.end_icons[par1];
/*     */   }
/*     */ 
/*     */   
/*     */   public static int limitToValidMetadata(int par0) {
/*  67 */     return par0 & 0x3;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  72 */     return "Bits 1 and 2 used for subtype, bit 4 set if aligned WE, and bit 8 set if aligned NS";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  77 */     return (metadata >= 0 && metadata < 16 && !BitHelper.isBitSet(metadata, 12));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/*  82 */     return metadata & 0x3;
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
/*  98 */     this.subtypes.setIcons(registerIcons(par1IconRegister, getTextures(), getTextureName() + "_"));
/*  99 */     this.end_icons = registerIcons(par1IconRegister, getTextures(), getTextureName() + "_", "_top");
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 104 */     if (info.wasExploded()) {
/* 105 */       return dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.5F);
/*     */     }
/* 107 */     return super.dropBlockAsEntityItem(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 112 */     return "log";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getTextures() {
/* 117 */     return this.subtypes.getTextures();
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNames() {
/* 122 */     return this.subtypes.getNames();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */