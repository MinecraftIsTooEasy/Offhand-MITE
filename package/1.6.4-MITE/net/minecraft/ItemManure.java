/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemManure
/*     */   extends Item
/*     */ {
/*     */   public ItemManure(int id) {
/*   9 */     super(id, Material.manure, "manure");
/*  10 */     setCreativeTab(CreativeTabs.tabMaterials);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean tryFertilizeBlock(World world, int x, int y, int z, EnumFace face, ItemStack item_stack, EntityPlayer player) {
/*  15 */     Block block = world.getBlock(x, y, z);
/*     */     
/*  17 */     if (block instanceof BlockMushroom) {
/*     */       
/*  19 */       Block block_below = world.getBlock(x, y - 1, z);
/*     */       
/*  21 */       if (block_below == Block.tilledField) {
/*  22 */         return tryFertilizeBlock(world, x, y - 1, z, EnumFace.TOP, item_stack, player);
/*     */       }
/*  24 */       BlockMushroom block_mushroom = (BlockMushroom)block;
/*     */       
/*  26 */       if (!block_mushroom.isLegalAt(world, x, y, z, world.getBlockMetadata(x, y, z))) {
/*  27 */         return false;
/*     */       }
/*  29 */       if (block == Block.mushroomRed) {
/*     */         
/*  31 */         if (block_below != Block.grass) {
/*  32 */           return false;
/*     */         }
/*  34 */         if (!world.isOutdoors(x, y, z)) {
/*  35 */           return false;
/*     */         }
/*     */       } else {
/*     */         
/*  39 */         if (block_below != Block.mycelium) {
/*  40 */           return false;
/*     */         }
/*  42 */         if (world.isOutdoors(x, y, z)) {
/*  43 */           return false;
/*     */         }
/*     */       } 
/*  46 */       if (!world.isRemote && world.rand.nextFloat() < 0.5F) {
/*  47 */         block_mushroom.fertilizeMushroom(world, x, y, z, item_stack, player);
/*     */       }
/*  49 */       if (!world.isRemote) {
/*  50 */         world.blockFX(EnumBlockFX.manure, x, y, z);
/*     */       }
/*  52 */       return true;
/*     */     } 
/*     */     
/*  55 */     if (block instanceof BlockCrops || block instanceof BlockStem || block == Block.mushroomBrown) {
/*  56 */       return tryFertilizeBlock(world, x, y - 1, z, EnumFace.TOP, item_stack, player);
/*     */     }
/*  58 */     if (block == Block.grass && face.isTop() && world.getBlock(x, y + 1, z) == Block.mushroomRed) {
/*  59 */       return tryFertilizeBlock(world, x, y + 1, z, EnumFace.TOP, item_stack, player);
/*     */     }
/*  61 */     if (block == Block.mycelium && face.isTop()) {
/*  62 */       return ((BlockMycelium)block).fertilize(world, x, y, z, item_stack, player);
/*     */     }
/*  64 */     if (block == Block.tilledField && face.isTop()) { (BlockFarmland)block; if (BlockFarmland.fertilize(world, x, y, z, item_stack, player)); }  return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*  69 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*     */     
/*  71 */     if (rc != null && rc.isBlock())
/*     */     {
/*  73 */       if (tryFertilizeBlock(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.face_hit, player.getHeldItemStack(), player)) {
/*     */         
/*  75 */         if (player.onClient()) {
/*  76 */           player.swingArm();
/*  77 */         } else if (!player.inCreativeMode()) {
/*  78 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         } 
/*     */ 
/*     */         
/*  82 */         return true;
/*     */       } 
/*     */     }
/*     */     
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void particleEffect(World world, int x, int y, int z, int num_particles) {
/*  91 */     if (num_particles == 0) {
/*  92 */       num_particles = 4;
/*     */     }
/*  94 */     Block block = Block.getBlock(world.getBlockId(x, y, z));
/*     */     
/*  96 */     if (block != null) {
/*  97 */       block.setBlockBoundsBasedOnStateAndNeighbors(world, x, y, z);
/*     */     }
/*  99 */     int index = Minecraft.getThreadIndex();
/*     */     
/* 101 */     for (int i = 0; i < num_particles; i++) {
/*     */       
/* 103 */       double vx = itemRand.nextGaussian() * 0.02D;
/* 104 */       double vy = itemRand.nextGaussian() * 0.02D;
/* 105 */       double vz = itemRand.nextGaussian() * 0.02D;
/*     */       
/* 107 */       world.spawnParticle(EnumParticle.manure, (x + itemRand.nextFloat()), y + ((block == null) ? 1.0D : block.getBlockBoundsMaxY(index)) + 0.15000000596046448D + (itemRand.nextFloat() * 0.1F), (z + itemRand.nextFloat()), vx, vy, vz);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemManure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */