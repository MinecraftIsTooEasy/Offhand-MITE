/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class BlockMycelium
/*     */   extends Block
/*     */ {
/*     */   private Icon field_94422_a;
/*     */   private Icon field_94421_b;
/*     */   
/*     */   protected BlockMycelium(int par1) {
/*  13 */     super(par1, Material.grass, (new BlockConstants()).setNotAlwaysLegal());
/*  14 */     setTickRandomly(true);
/*  15 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  23 */     return (par1 == 1) ? this.field_94422_a : ((par1 == 0) ? Block.dirt.getBlockTextureFromSide(par1) : this.blockIcon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  31 */     if (par5 == 1)
/*     */     {
/*  33 */       return this.field_94422_a;
/*     */     }
/*  35 */     if (par5 == 0)
/*     */     {
/*  37 */       return Block.dirt.getBlockTextureFromSide(par5);
/*     */     }
/*     */ 
/*     */     
/*  41 */     Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
/*  42 */     return (var6 != Material.snow && var6 != Material.craftedSnow) ? this.blockIcon : this.field_94421_b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  52 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/*  53 */     this.field_94422_a = par1IconRegister.registerIcon(getTextureName() + "_top");
/*  54 */     this.field_94421_b = par1IconRegister.registerIcon("grass_side_snowed");
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
/*     */   public static int getLightValueTolerance() {
/*  89 */     return 13;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  94 */     if (world.isRemote) {
/*     */       
/*  96 */       Minecraft.setErrorMessage("BlockMycelium.updateTick: called on client?");
/*  97 */       return false;
/*     */     } 
/*     */     
/* 100 */     if (super.updateTick(world, x, y, z, random)) {
/* 101 */       return true;
/*     */     }
/* 103 */     if (world.getBlockMaterial(x, y + 1, z) == Material.water) {
/* 104 */       return world.setBlock(x, y, z, Block.dirt.blockID);
/*     */     }
/* 106 */     if (random.nextInt(4) > 0) {
/* 107 */       return false;
/*     */     }
/* 109 */     if (world.getBlockLightValue(x, y + 1, z) > getLightValueTolerance() || world.isOutdoors(x, y, z)) {
/*     */       
/* 111 */       if (world.isDaytime() && (!world.isPrecipitating(true) || world.isFreezing(x, z))) {
/* 112 */         return world.setBlock(x, y, z, Block.dirt.blockID);
/*     */       }
/*     */     } else {
/*     */       
/* 116 */       if (random.nextInt(256) == 0 && world.isAirBlock(x, y + 1, z)) {
/*     */         
/* 118 */         int mushroom_count = 0;
/*     */         
/* 120 */         for (int i = -4; i <= 4; i++) {
/*     */           
/* 122 */           for (int j = -2; j <= 2; j++) {
/*     */             
/* 124 */             for (int k = -4; k <= 4; k++) {
/*     */               
/* 126 */               if (world.getBlock(x + i, y + j, z + k) instanceof BlockMushroom && 
/* 127 */                 ++mushroom_count > 2) {
/* 128 */                 return false;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/* 133 */         world.setBlock(x, y + 1, z, Block.mushroomBrown.blockID);
/* 134 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 139 */       int dx = 0;
/* 140 */       int dy = 0;
/* 141 */       int dz = 0;
/*     */       
/* 143 */       for (int attempts = 0; attempts < 8; attempts++) {
/*     */         
/* 145 */         dx = random.nextInt(3) - 1;
/* 146 */         dy = random.nextInt(5) - 2;
/* 147 */         dz = random.nextInt(3) - 1;
/*     */         
/* 149 */         if (dy == -1 || dy == 0 || dy == 1)
/*     */         {
/* 151 */           if (dx != 0 && dz != 0) {
/*     */             continue;
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 158 */         Block block1 = world.getBlock(x + dx, y + dy, z + dz);
/*     */         
/* 160 */         if (block1 != Block.dirt && block1 != Block.grass && block1 != Block.tilledField) {
/*     */           continue;
/*     */         }
/* 163 */         if (world.getBlockLightValue(x + dx, y + dy + 1, z + dz) > getLightValueTolerance()) {
/*     */           continue;
/*     */         }
/* 166 */         if (!isLegalAt(world, x + dx, y + dy, z + dz, 0)) {
/*     */           continue;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 172 */       x += dx;
/* 173 */       y += dy;
/* 174 */       z += dz;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       if (!isLegalAt(world, x, y, z, 0)) {
/* 180 */         return false;
/*     */       }
/* 182 */       if (world.isLiquidBlock(x, y + 1, z)) {
/* 183 */         return false;
/*     */       }
/* 185 */       Block block = world.getBlock(x, y, z);
/*     */       
/* 187 */       if ((block == Block.dirt || block == Block.grass || block == Block.tilledField) && world.getBlockLightValue(x, y + 1, z) <= getLightValueTolerance() && !world.isOutdoors(x, y, z)) {
/* 188 */         world.setBlock(x, y, z, this.blockID);
/*     */       }
/*     */     } 
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 199 */     super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
/*     */     
/* 201 */     if (par5Random.nextInt(10) == 0)
/*     */     {
/*     */       
/* 204 */       par1World.spawnParticle(EnumParticle.townaura, (par2 + par5Random.nextFloat()), (par3 + 1.1F), (par4 + par5Random.nextFloat()), 0.0D, 0.0D, 0.0D);
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
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 221 */     return dropBlockAsEntityItem(info, Block.dirt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean fertilize(World world, int x, int y, int z, ItemStack item_stack, EntityPlayer player) {
/* 231 */     Item item = item_stack.getItem();
/*     */ 
/*     */     
/* 234 */     if (item == Item.manure) {
/*     */       
/* 236 */       Block block = Block.blocksList[world.getBlockId(x, y + 1, z)];
/*     */       
/* 238 */       if (block == Block.mushroomBrown) {
/*     */         
/* 240 */         if (!world.isRemote) {
/*     */           
/* 242 */           ((BlockMushroom)block).fertilizeMushroom(world, x, y + 1, z, item_stack, player);
/* 243 */           world.blockFX(EnumBlockFX.manure, x, y + 1, z);
/*     */         } 
/*     */         
/* 246 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 250 */     return false;
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 270 */     boolean changed = super.onNeighborBlockChange(world, x, y, z, neighbor_block_id);
/*     */     
/* 272 */     if (changed && world.getBlock(x, y, z) != this) {
/* 273 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 277 */     if (world.isLavaBlock(x, y + 1, z)) {
/*     */       
/* 279 */       world.blockFX(EnumBlockFX.lava_mixing_with_water, x, y, z);
/* 280 */       return (world.setBlock(x, y, z, Block.dirt.blockID) || changed);
/*     */     } 
/*     */     
/* 283 */     return changed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 288 */     Block block_above = world.getBlock(x, y + 1, z);
/*     */     
/* 290 */     return (block_above == null || block_above instanceof BlockMushroomCap || !block_above.isFaceFlatAndSolid(world.getBlockMetadata(x, y + 1, z), EnumFace.BOTTOM));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNotLegal(World world, int x, int y, int z, int metadata) {
/* 295 */     return world.setBlock(x, y, z, dirt.blockID);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getAlternativeBlockForPlacement() {
/* 300 */     return dirt;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockMycelium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */