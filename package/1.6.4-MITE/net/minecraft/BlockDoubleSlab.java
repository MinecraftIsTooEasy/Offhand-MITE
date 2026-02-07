/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockDoubleSlab
/*     */   extends Block
/*     */ {
/*     */   private BlockSlab single_slab;
/*     */   
/*     */   public BlockDoubleSlab(int id, BlockSlab single_slab) {
/*  14 */     super(id, single_slab.blockMaterial, new BlockConstants());
/*     */     
/*  16 */     if (single_slab != getSingleSlab()) {
/*  17 */       Minecraft.setErrorMessage("BlockDoubleSlab: single slab mismatch");
/*     */     }
/*  19 */     this.single_slab = single_slab;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  25 */     setUnlocalizedName("slab.group" + single_slab.getGroup());
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockSlab getSingleSlab() {
/*  30 */     if (this.single_slab == null) {
/*  31 */       this.single_slab = (BlockSlab)Block.getBlock(this.blockID + 1);
/*     */     }
/*  33 */     return this.single_slab;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {}
/*     */   
/*     */   public Icon getIcon(int side, int metadata) {
/*  40 */     return this.single_slab.getIcon(side, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  45 */     return "Bits 1, 2, and 4 used for subtype";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  50 */     return (metadata < 8 && getSingleSlab().isValidMetadata(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/*  55 */     return getSingleSlab().getBlockSubtypeUnchecked(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFullSlabName(int metadata) {
/*  60 */     return this.single_slab.getFullSlabName(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int idPicked(World world, int x, int y, int z) {
/*  65 */     return this.single_slab.blockID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/*  75 */     if (info.wasExploded()) {
/*     */       Block model_block;
/*     */ 
/*     */       
/*  79 */       if (this.blockMaterial == Material.wood) {
/*     */         
/*  81 */         model_block = Block.wood;
/*     */       }
/*  83 */       else if (this.blockMaterial == Material.stone) {
/*     */         
/*  85 */         int subtype = getItemSubtype(info.getMetadata());
/*     */         
/*  87 */         if (subtype == 0) {
/*  88 */           model_block = Block.stone;
/*  89 */         } else if (subtype == 1) {
/*  90 */           model_block = Block.sandStone;
/*  91 */         } else if (subtype == 3) {
/*  92 */           model_block = Block.cobblestone;
/*  93 */         } else if (subtype == 5) {
/*  94 */           model_block = Block.stoneBrick;
/*  95 */         } else if (subtype == 6) {
/*  96 */           model_block = Block.netherrack;
/*  97 */         } else if (subtype == 7) {
/*  98 */           model_block = Block.blockNetherQuartz;
/*     */         } else {
/* 100 */           model_block = null;
/*     */         } 
/* 102 */         info.setMetadata(0);
/*     */       }
/* 104 */       else if (this.blockMaterial == Material.obsidian) {
/*     */         
/* 106 */         model_block = Block.obsidian;
/*     */       }
/*     */       else {
/*     */         
/* 110 */         Minecraft.setErrorMessage("dropBlockAsEntityItem: blockMaterial " + this.blockMaterial + " not handled");
/* 111 */         model_block = null;
/*     */       } 
/*     */       
/* 114 */       if (model_block == null) {
/* 115 */         return 0;
/*     */       }
/* 117 */       return model_block.dropBlockAsEntityItem(info);
/*     */     } 
/*     */ 
/*     */     
/* 121 */     return dropBlockAsEntityItem(info, createStackedBlock(info.getMetadata()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 127 */     return "double";
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBlockHardness(int metadata) {
/* 132 */     return this.single_slab.getBlockHardness(metadata) * 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHarvestLevel(int metadata) {
/* 137 */     return this.single_slab.getMinHarvestLevel(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack createStackedBlock(int metadata) {
/* 142 */     ItemStack item_stack = this.single_slab.createStackedBlock(metadata);
/*     */     
/* 144 */     item_stack.stackSize *= 2;
/*     */     
/* 146 */     return item_stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void getItemStacks(int id, CreativeTabs creative_tabs, List list) {
/* 151 */     if (creative_tabs == null)
/* 152 */       super.getItemStacks(id, creative_tabs, list); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDoubleSlab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */