/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class BlockJukeBox
/*     */   extends BlockContainer
/*     */ {
/*     */   private Icon theIcon;
/*     */   
/*     */   protected BlockJukeBox(int par1) {
/*  10 */     super(par1, Material.wood, new BlockConstants());
/*  11 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  19 */     return (par1 == 1) ? this.theIcon : this.blockIcon;
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
/*  40 */     if (!world.isAirOrPassableBlock(x, y + 1, z, false)) {
/*  41 */       return false;
/*     */     }
/*  43 */     if (world.getBlockMetadata(x, y, z) == 0) {
/*  44 */       return false;
/*     */     }
/*  46 */     if (player.onServer()) {
/*  47 */       ejectRecord(world, x, y, z);
/*     */     }
/*  49 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertRecord(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack) {
/*  57 */     if (!par1World.isRemote) {
/*     */       
/*  59 */       TileEntityRecordPlayer var6 = (TileEntityRecordPlayer)par1World.getBlockTileEntity(par2, par3, par4);
/*     */       
/*  61 */       if (var6 != null) {
/*     */         
/*  63 */         var6.func_96098_a(par5ItemStack.copy());
/*  64 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectRecord(World par1World, int par2, int par3, int par4) {
/*  74 */     if (!par1World.isRemote) {
/*     */       
/*  76 */       TileEntityRecordPlayer var5 = (TileEntityRecordPlayer)par1World.getBlockTileEntity(par2, par3, par4);
/*     */       
/*  78 */       if (var5 != null) {
/*     */         
/*  80 */         ItemStack var6 = var5.func_96097_a();
/*     */         
/*  82 */         if (var6 != null) {
/*     */           
/*  84 */           par1World.playAuxSFX(1005, par2, par3, par4, 0);
/*  85 */           par1World.playRecord((String)null, par2, par3, par4);
/*  86 */           var5.func_96098_a((ItemStack)null);
/*  87 */           par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
/*  88 */           float var7 = 0.7F;
/*  89 */           double var8 = (par1World.rand.nextFloat() * var7) + (1.0F - var7) * 0.5D;
/*  90 */           double var10 = (par1World.rand.nextFloat() * var7) + (1.0F - var7) * 0.2D + 0.6D;
/*  91 */           double var12 = (par1World.rand.nextFloat() * var7) + (1.0F - var7) * 0.5D;
/*  92 */           ItemStack var14 = var6.copy();
/*  93 */           EntityItem var15 = new EntityItem(par1World, par2 + var8, par3 + var10, par4 + var12, var14);
/*  94 */           var15.delayBeforeCanPickup = 10;
/*  95 */           par1World.spawnEntityInWorld(var15);
/*     */         } 
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
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 108 */     ejectRecord(par1World, par2, par3, par4);
/* 109 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
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
/*     */   public TileEntity createNewTileEntity(World par1World) {
/* 133 */     return new TileEntityRecordPlayer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 142 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/* 143 */     this.theIcon = par1IconRegister.registerIcon(getTextureName() + "_top");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 161 */     ItemStack var6 = ((TileEntityRecordPlayer)par1World.getBlockTileEntity(par2, par3, par4)).func_96097_a();
/* 162 */     return (var6 == null) ? 0 : (var6.itemID + 1 - Item.record13.itemID);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 167 */     item_block.addMaterial(new Material[] { Material.wood, Material.diamond });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockJukeBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */