/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemSnow
/*     */   extends ItemBlockWithMetadata
/*     */ {
/*     */   public ItemSnow(Block block) {
/*  14 */     super(block);
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
/*     */ 
/*     */   
/*     */   public static boolean tryAddToExistingSnow(RaycastCollision rc, EntityPlayer player, int layers_to_place) {
/*     */     int x, y, z, existing_layers;
/*  55 */     World world = rc.world;
/*     */     
/*  57 */     int max_depth = BlockSnow.getMaxDepth();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  62 */     if (rc.getBlockHit() == Block.snow && BlockSnow.getDepth(rc.block_hit_metadata) < max_depth) {
/*     */       
/*  64 */       x = rc.block_hit_x;
/*  65 */       y = rc.block_hit_y;
/*  66 */       z = rc.block_hit_z;
/*     */       
/*  68 */       existing_layers = BlockSnow.getDepth(rc.block_hit_metadata);
/*     */     }
/*  70 */     else if (rc.getNeighborOfBlockHit() == Block.snow && BlockSnow.getDepth(rc.getNeighborOfBlockHitMetadata()) < max_depth) {
/*     */       
/*  72 */       x = rc.neighbor_block_x;
/*  73 */       y = rc.neighbor_block_y;
/*  74 */       z = rc.neighbor_block_z;
/*     */       
/*  76 */       existing_layers = BlockSnow.getDepth(rc.getNeighborOfBlockHitMetadata());
/*     */     }
/*     */     else {
/*     */       
/*  80 */       return false;
/*     */     } 
/*     */     
/*  83 */     layers_to_place += existing_layers;
/*     */     
/*  85 */     if (layers_to_place <= max_depth) {
/*  86 */       return Block.snow.tryPlaceBlock(world, x, y, z, EnumFace.TOP, layers_to_place - 1, player, false, false);
/*     */     }
/*  88 */     int metadata = layers_to_place - max_depth - 1;
/*     */     
/*  90 */     if (world.isAirBlock(x, y + 1, z) || Block.snow.canReplaceBlock(metadata, world.getBlock(x, y + 1, z), world.getBlockMetadata(x, y + 1, z))) {
/*  91 */       return (Block.snow.tryPlaceBlock(world, x, y, z, EnumFace.TOP, BlockSnow.getDepthBits(), player, false, false) && Block.snow.tryPlaceBlock(world, x, y + 1, z, EnumFace.TOP, metadata, player, false, true));
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryPlaceAsBlock(RaycastCollision rc, Block block, EntityPlayer player, ItemStack item_stack) {
/* 100 */     if (block != Block.snow) {
/* 101 */       Minecraft.setErrorMessage("tryPlaceAsBlock: block!=Block.snow");
/*     */     }
/*     */     
/* 104 */     if (block != Block.snow || block.canReplaceBlock(block.getMetadataForPlacement(player.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, item_stack, player, EnumFace.TOP, 0.0F, 0.0F, 0.0F), rc.getBlockHit(), rc.block_hit_metadata)) {
/* 105 */       return super.tryPlaceAsBlock(rc, block, player, item_stack);
/*     */     }
/* 107 */     return (tryAddToExistingSnow(rc, player, item_stack.getItemSubtype() + 1) || super.tryPlaceAsBlock(rc, block, player, item_stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemStackLimit(int subtype, int damage) {
/* 112 */     return (subtype == 0) ? super.getItemStackLimit(subtype, damage) : 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/* 117 */     return super.getUnlocalizedName(par1ItemStack) + ((par1ItemStack != null && !ReferenceFileWriter.running && par1ItemStack.getItemSubtype() == 3) ? ".slab" : "");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSnow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */