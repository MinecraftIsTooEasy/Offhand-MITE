/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPumpkin
/*     */   extends BlockDirectional
/*     */ {
/*     */   private boolean blockType;
/*     */   private Icon field_94474_b;
/*     */   private Icon field_94475_c;
/*     */   
/*     */   protected BlockPumpkin(int par1, boolean par2) {
/*  15 */     super(par1, Material.pumpkin, (new BlockConstants()).setNeverConnectsWithFence());
/*  16 */     setTickRandomly(true);
/*  17 */     this.blockType = par2;
/*  18 */     setMaxStackSize(8);
/*  19 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  24 */     String[] array = new String[4];
/*     */     
/*  26 */     for (int i = 0; i < array.length; i++) {
/*  27 */       array[i] = i + "=" + getDirectionFacing(i).getDescriptor(true);
/*     */     }
/*  29 */     return StringHelper.implode(array, ", ", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  34 */     return (metadata >= 0 && metadata < 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  42 */     return (par1 == 1) ? this.field_94474_b : ((par1 == 0) ? this.field_94474_b : ((par2 == 2 && par1 == 2) ? this.field_94475_c : ((par2 == 3 && par1 == 5) ? this.field_94475_c : ((par2 == 0 && par1 == 3) ? this.field_94475_c : ((par2 == 1 && par1 == 4) ? this.field_94475_c : this.blockIcon)))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/*  50 */     super.onBlockAdded(par1World, par2, par3, par4);
/*     */     
/*  52 */     if (par1World.getBlockId(par2, par3 - 1, par4) == Block.blockSnow.blockID && par1World.getBlockId(par2, par3 - 2, par4) == Block.blockSnow.blockID) {
/*     */       
/*  54 */       if (!par1World.isRemote) {
/*     */         
/*  56 */         par1World.setBlock(par2, par3, par4, 0, 0, 2);
/*  57 */         par1World.setBlock(par2, par3 - 1, par4, 0, 0, 2);
/*  58 */         par1World.setBlock(par2, par3 - 2, par4, 0, 0, 2);
/*  59 */         EntitySnowman var9 = new EntitySnowman(par1World);
/*  60 */         var9.setLocationAndAngles(par2 + 0.5D, par3 - 1.95D, par4 + 0.5D, 0.0F, 0.0F);
/*  61 */         par1World.spawnEntityInWorld(var9);
/*  62 */         par1World.notifyBlockChange(par2, par3, par4, 0);
/*  63 */         par1World.notifyBlockChange(par2, par3 - 1, par4, 0);
/*  64 */         par1World.notifyBlockChange(par2, par3 - 2, par4, 0);
/*     */       } 
/*     */       
/*  67 */       for (int var10 = 0; var10 < 120; var10++)
/*     */       {
/*     */         
/*  70 */         par1World.spawnParticle(EnumParticle.snowshovel, par2 + par1World.rand.nextDouble(), (par3 - 2) + par1World.rand.nextDouble() * 2.5D, par4 + par1World.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*  73 */     else if (par1World.getBlockId(par2, par3 - 1, par4) == Block.blockIron.blockID && par1World.getBlockId(par2, par3 - 2, par4) == Block.blockIron.blockID) {
/*     */       
/*  75 */       boolean var5 = (par1World.getBlockId(par2 - 1, par3 - 1, par4) == Block.blockIron.blockID && par1World.getBlockId(par2 + 1, par3 - 1, par4) == Block.blockIron.blockID);
/*  76 */       boolean var6 = (par1World.getBlockId(par2, par3 - 1, par4 - 1) == Block.blockIron.blockID && par1World.getBlockId(par2, par3 - 1, par4 + 1) == Block.blockIron.blockID);
/*     */       
/*  78 */       if (var5 || var6) {
/*     */         
/*  80 */         par1World.setBlock(par2, par3, par4, 0, 0, 2);
/*  81 */         par1World.setBlock(par2, par3 - 1, par4, 0, 0, 2);
/*  82 */         par1World.setBlock(par2, par3 - 2, par4, 0, 0, 2);
/*     */         
/*  84 */         if (var5) {
/*     */           
/*  86 */           par1World.setBlock(par2 - 1, par3 - 1, par4, 0, 0, 2);
/*  87 */           par1World.setBlock(par2 + 1, par3 - 1, par4, 0, 0, 2);
/*     */         }
/*     */         else {
/*     */           
/*  91 */           par1World.setBlock(par2, par3 - 1, par4 - 1, 0, 0, 2);
/*  92 */           par1World.setBlock(par2, par3 - 1, par4 + 1, 0, 0, 2);
/*     */         } 
/*     */         
/*  95 */         EntityIronGolem var7 = new EntityIronGolem(par1World);
/*  96 */         var7.setPlayerCreated(true);
/*  97 */         var7.setLocationAndAngles(par2 + 0.5D, par3 - 1.95D, par4 + 0.5D, 0.0F, 0.0F);
/*  98 */         par1World.spawnEntityInWorld(var7);
/*     */         
/* 100 */         for (int var8 = 0; var8 < 120; var8++)
/*     */         {
/*     */           
/* 103 */           par1World.spawnParticle(EnumParticle.snowballpoof, par2 + par1World.rand.nextDouble(), (par3 - 2) + par1World.rand.nextDouble() * 3.9D, par4 + par1World.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */         
/* 106 */         par1World.notifyBlockChange(par2, par3, par4, 0);
/* 107 */         par1World.notifyBlockChange(par2, par3 - 1, par4, 0);
/* 108 */         par1World.notifyBlockChange(par2, par3 - 2, par4, 0);
/*     */         
/* 110 */         if (var5) {
/*     */           
/* 112 */           par1World.notifyBlockChange(par2 - 1, par3 - 1, par4, 0);
/* 113 */           par1World.notifyBlockChange(par2 + 1, par3 - 1, par4, 0);
/*     */         }
/*     */         else {
/*     */           
/* 117 */           par1World.notifyBlockChange(par2, par3 - 1, par4 - 1, 0);
/* 118 */           par1World.notifyBlockChange(par2, par3 - 1, par4 + 1, 0);
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
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/* 135 */     return getDirectionFacingStandard4(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 140 */     metadata &= 0xFFFFFFFC;
/* 141 */     metadata |= direction.isSouth() ? 0 : (direction.isWest() ? 1 : (direction.isNorth() ? 2 : (direction.isEast() ? 3 : -1)));
/*     */     
/* 143 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canOccurAt(World world, int x, int y, int z, int metadata) {
/* 148 */     Block block_below = world.getBlock(x, y - 1, z);
/*     */     
/* 150 */     if (block_below == null || !block_below.isTopFlatAndSolid(world.getBlockMetadata(x, y - 1, z))) {
/* 151 */       return false;
/*     */     }
/* 153 */     return super.canOccurAt(world, x, y, z, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePlacedAt(World world, int x, int y, int z, int metadata) {
/* 158 */     return (world.isBlockFaceFlatAndSolid(x, y - 1, z, EnumFace.TOP) && super.canBePlacedAt(world, x, y, z, metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePlacedOnBlock(int metadata, Block block_below, int block_below_metadata, double block_below_bounds_max_y) {
/* 163 */     return (block_below != null && block_below.isTopFlatAndSolid(block_below_metadata) && super.canBePlacedOnBlock(metadata, block_below, block_below_metadata, block_below_bounds_max_y));
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 183 */     this.field_94475_c = par1IconRegister.registerIcon(getTextureName() + "_face_" + (this.blockType ? "on" : "off"));
/* 184 */     this.field_94474_b = par1IconRegister.registerIcon(getTextureName() + "_top");
/* 185 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 190 */     if (info.wasExploded()) {
/* 191 */       return dropBlockAsEntityItem(info, Item.pumpkinSeeds);
/*     */     }
/* 193 */     return super.dropBlockAsEntityItem(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 198 */     item_block.addMaterial(new Material[] { Material.pumpkin });
/*     */     
/* 200 */     if (this.blockType) {
/* 201 */       item_block.addMaterial(new Material[] { Material.wood, Material.coal });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkForFlooding(World world, int x, int y, int z) {
/* 207 */     if (world instanceof WorldServer && this.blockType && world.getBlock(x, y, z) == pumpkinLantern) {
/*     */       
/* 209 */       BlockFluid flooding_block = null;
/*     */       
/* 211 */       for (int dx = -1; dx <= 1 && flooding_block == null; dx++) {
/*     */         
/* 213 */         for (int dz = -1; dz <= 1 && flooding_block == null; dz++) {
/*     */           
/* 215 */           if (dx == 0 || dz == 0) {
/*     */             
/* 217 */             Block block = world.getBlock(x + dx, y, z + dz);
/*     */             
/* 219 */             if (block instanceof BlockFluid) {
/*     */               
/* 221 */               flooding_block = (BlockFluid)block;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 228 */       if (flooding_block == null) {
/*     */         
/* 230 */         Block block = world.getBlock(x, y + 1, z);
/*     */         
/* 232 */         if (block instanceof BlockFluid) {
/* 233 */           flooding_block = (BlockFluid)block;
/*     */         }
/*     */       } 
/* 236 */       if (flooding_block != null)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 245 */         world.getAsWorldServer().addScheduledBlockOperation(EnumBlockOperation.pumpkin_lantern_flooded, x, y, z, world.getTotalWorldTime() + 10L, false);
/*     */       }
/*     */     } 
/*     */     
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 254 */     if (super.updateTick(world, x, y, z, random)) {
/* 255 */       return true;
/*     */     }
/* 257 */     return (this.blockType && checkForFlooding(world, x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 262 */     if (!test_only && this.blockType && checkForFlooding(world, x, y, z)) {
/* 263 */       return true;
/*     */     }
/* 265 */     return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 270 */     if (this.blockType) {
/* 271 */       checkForFlooding(world, x, y, z);
/*     */     }
/* 273 */     return super.onNeighborBlockChange(world, x, y, z, neighbor_block_id);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPumpkin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */