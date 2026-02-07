/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockEnchantmentTable
/*     */   extends BlockContainer
/*     */ {
/*     */   private Icon field_94461_a;
/*     */   private Icon field_94460_b;
/*     */   private Material gem_type;
/*     */   
/*     */   protected BlockEnchantmentTable(int par1, Material gem_type) {
/*  16 */     super(par1, Material.stone, new BlockConstants());
/*  17 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
/*     */     
/*  19 */     setLightOpacity(255);
/*  20 */     this.gem_type = gem_type;
/*  21 */     setCreativeTab(CreativeTabs.tabDecorations);
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
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  37 */     super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
/*     */     
/*  39 */     for (int var6 = par2 - 2; var6 <= par2 + 2; var6++) {
/*     */       
/*  41 */       for (int var7 = par4 - 2; var7 <= par4 + 2; var7++) {
/*     */         
/*  43 */         if (var6 > par2 - 2 && var6 < par2 + 2 && var7 == par4 - 1)
/*     */         {
/*  45 */           var7 = par4 + 2;
/*     */         }
/*     */         
/*  48 */         if (par5Random.nextInt(16) == 0)
/*     */         {
/*  50 */           for (int var8 = par3; var8 <= par3 + 1; var8++) {
/*     */             
/*  52 */             if (par1World.getBlockId(var6, var8, var7) == Block.bookShelf.blockID) {
/*     */               
/*  54 */               if (!par1World.isAirBlock((var6 - par2) / 2 + par2, var8, (var7 - par4) / 2 + par4)) {
/*     */                 break;
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*  60 */               par1World.spawnParticle(EnumParticle.enchantmenttable, par2 + 0.5D, par3 + 2.0D, par4 + 0.5D, ((var6 - par2) + par5Random.nextFloat()) - 0.5D, ((var8 - par3) - par5Random.nextFloat() - 1.0F), ((var7 - par4) + par5Random.nextFloat()) - 0.5D);
/*     */             } 
/*     */           } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  82 */     return (par1 == 0) ? this.field_94460_b : ((par1 == 1) ? this.field_94461_a : this.blockIcon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/*  90 */     return new TileEntityEnchantmentTable();
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 112 */     if (!world.isAirOrPassableBlock(x, y + 1, z, false)) {
/* 113 */       return false;
/*     */     }
/* 115 */     if (player.onServer()) {
/*     */       
/* 117 */       TileEntityEnchantmentTable tile_entity = (TileEntityEnchantmentTable)world.getBlockTileEntity(x, y, z);
/*     */       
/* 119 */       if (tile_entity != null) {
/* 120 */         player.displayGUIEnchantment(x, y, z, tile_entity.func_94135_b() ? tile_entity.func_94133_a() : null);
/*     */       }
/*     */     } 
/* 123 */     return true;
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 161 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_" + "side");
/* 162 */     this.field_94461_a = par1IconRegister.registerIcon(getTextureName() + "_" + "top");
/*     */     
/* 164 */     this.field_94460_b = par1IconRegister.registerIcon("enchanting_table_bottom");
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 169 */     item_block.addMaterial(new Material[] { Material.obsidian, this.gem_type, Material.paper, Material.leather });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 174 */     return this.gem_type.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
/* 179 */     return face.isBottom();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 184 */     return (side == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 189 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 194 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockEnchantmentTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */