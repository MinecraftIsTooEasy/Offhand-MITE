/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class BlockFlowerPot
/*     */   extends Block
/*     */ {
/*     */   public BlockFlowerPot(int par1) {
/*  10 */     super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*     */ 
/*     */     
/*  13 */     setBounds(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  18 */     String[] array = new String[16];
/*     */     
/*  20 */     for (int i = 0; i < 16; i++) {
/*     */       
/*  22 */       if (isValidMetadata(i)) {
/*     */         
/*  24 */         ItemStack item_stack = getPlantForMeta(i);
/*     */         
/*  26 */         StringHelper.addToStringArray(i + "=" + ((item_stack == null) ? "Empty" : item_stack.getNameForReferenceFile()), array);
/*     */       } 
/*     */     } 
/*     */     
/*  30 */     return StringHelper.implode(array, ", ", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  35 */     return (metadata >= 0 && metadata < 13);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBounds(boolean for_all_threads) {
/*  45 */     float var1 = 0.375F;
/*  46 */     float var2 = var1 / 2.0F;
/*  47 */     setBlockBounds((0.5F - var2), 0.0D, (0.5F - var2), (0.5F + var2), var1, (0.5F + var2), for_all_threads);
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
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/*  60 */     setBounds(false);
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
/*  77 */     return 33;
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 143 */     ItemStack item_stack = player.getHeldItemStack();
/*     */     
/* 145 */     if (item_stack == null) {
/* 146 */       return false;
/*     */     }
/* 148 */     if (BlockFlowerPotMulti.a(item_stack) != 0) {
/*     */       
/* 150 */       if (player.onServer()) {
/*     */         
/* 152 */         int i = world.getBlockMetadata(x, y, z);
/*     */         
/* 154 */         if (i != 0) {
/*     */           
/* 156 */           BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
/* 157 */           dropBlockAsEntityItem(info, getPlantForMeta(i));
/*     */           
/* 159 */           world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */         } 
/*     */         
/* 162 */         world.setBlock(x, y, z, flowerPotMulti.blockID, BlockFlowerPotMulti.a(item_stack), 2);
/*     */         
/* 164 */         if (!player.inCreativeMode()) {
/* 165 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/* 168 */       return true;
/*     */     } 
/*     */     
/* 171 */     int metadata_for_plant = getMetaForPlant(item_stack);
/*     */     
/* 173 */     if (metadata_for_plant == 0) {
/* 174 */       return false;
/*     */     }
/* 176 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 178 */     if (metadata == metadata_for_plant) {
/* 179 */       return false;
/*     */     }
/* 181 */     if (player.onServer()) {
/*     */       
/* 183 */       if (metadata != 0) {
/*     */         
/* 185 */         BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
/* 186 */         dropBlockAsEntityItem(info, getPlantForMeta(metadata));
/*     */         
/* 188 */         world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */       } 
/*     */       
/* 191 */       world.setBlockMetadataWithNotify(x, y, z, metadata_for_plant, 2);
/*     */       
/* 193 */       if (!player.inCreativeMode()) {
/* 194 */         player.convertOneOfHeldItem((ItemStack)null);
/*     */       }
/*     */     } 
/* 197 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 205 */     ItemStack var5 = getPlantForMeta(par1World.getBlockMetadata(par2, par3, par4));
/* 206 */     return (var5 == null) ? Item.flowerPot.itemID : var5.itemID;
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
/*     */   public boolean isFlowerPot() {
/* 229 */     return true;
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
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 242 */     return (block_below != null && block_below != leaves && block_below.isTopFlatAndSolid(block_below_metadata));
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
/*     */   public boolean canBeCarried() {
/* 279 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 284 */     if (info.wasExploded() || info.wasCrushed()) {
/* 285 */       return 0;
/*     */     }
/*     */     
/*     */     int num_drops;
/* 289 */     if ((num_drops = dropBlockAsEntityItem(info, Item.flowerPot)) > 0) {
/* 290 */       return num_drops + dropBlockAsEntityItem(info, getPlantForMeta(info.getMetadata()));
/*     */     }
/* 292 */     return 0;
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
/*     */   public static ItemStack getPlantForMeta(int par0) {
/* 308 */     switch (par0) {
/*     */       
/*     */       case 1:
/* 311 */         return new ItemStack(Block.plantRed);
/*     */       
/*     */       case 2:
/* 314 */         return new ItemStack(Block.plantYellow);
/*     */       
/*     */       case 3:
/* 317 */         return new ItemStack(Block.sapling, 1, 0);
/*     */       
/*     */       case 4:
/* 320 */         return new ItemStack(Block.sapling, 1, 1);
/*     */       
/*     */       case 5:
/* 323 */         return new ItemStack(Block.sapling, 1, 2);
/*     */       
/*     */       case 6:
/* 326 */         return new ItemStack(Block.sapling, 1, 3);
/*     */       
/*     */       case 7:
/* 329 */         return new ItemStack(Block.mushroomRed);
/*     */       
/*     */       case 8:
/* 332 */         return new ItemStack(Block.mushroomBrown);
/*     */       
/*     */       case 9:
/* 335 */         return new ItemStack(Block.cactus);
/*     */       
/*     */       case 10:
/* 338 */         return new ItemStack(Block.deadBush);
/*     */       
/*     */       case 11:
/* 341 */         return new ItemStack(Block.tallGrass, 1, 2);
/*     */       
/*     */       case 12:
/* 344 */         return new ItemStack(Block.deadBush, 1, 1);
/*     */     } 
/*     */     
/* 347 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMetaForPlant(ItemStack par0ItemStack) {
/* 356 */     int var1 = (par0ItemStack.getItem()).itemID;
/*     */     
/* 358 */     if (var1 == Block.plantRed.blockID)
/*     */     {
/*     */       
/* 361 */       return (par0ItemStack.getItemSubtype() == 0) ? 1 : 0;
/*     */     }
/* 363 */     if (var1 == Block.plantYellow.blockID)
/*     */     {
/* 365 */       return 2;
/*     */     }
/* 367 */     if (var1 == Block.cactus.blockID)
/*     */     {
/* 369 */       return 9;
/*     */     }
/* 371 */     if (var1 == Block.mushroomBrown.blockID)
/*     */     {
/* 373 */       return 8;
/*     */     }
/* 375 */     if (var1 == Block.mushroomRed.blockID)
/*     */     {
/* 377 */       return 7;
/*     */     }
/* 379 */     if (var1 == Block.deadBush.blockID)
/*     */     {
/*     */       
/* 382 */       return deadBush.isWitherwood(par0ItemStack.getItemSubtype()) ? 12 : 10;
/*     */     }
/*     */ 
/*     */     
/* 386 */     if (var1 == Block.sapling.blockID)
/*     */     {
/* 388 */       switch (par0ItemStack.getItemSubtype()) {
/*     */         
/*     */         case 0:
/* 391 */           return 3;
/*     */         
/*     */         case 1:
/* 394 */           return 4;
/*     */         
/*     */         case 2:
/* 397 */           return 5;
/*     */         
/*     */         case 3:
/* 400 */           return 6;
/*     */       } 
/*     */     
/*     */     }
/* 404 */     if (var1 == Block.tallGrass.blockID)
/*     */     {
/* 406 */       switch (par0ItemStack.getItemSubtype()) {
/*     */         
/*     */         case 2:
/* 409 */           return 11;
/*     */       } 
/*     */     
/*     */     }
/* 413 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 419 */     item_block.addMaterial(new Material[] { Material.clay });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 424 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isWitherwood(World world, int x, int y, int z) {
/* 429 */     return (world.getBlockMetadata(x, y, z) == getMetaForPlant(new ItemStack(deadBush, 1, 1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
/* 434 */     if (isWitherwood(world, x, y, z)) {
/* 435 */       BlockDeadBush.spawnWitherwoodParticles(world, x, y, z, random);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
/* 440 */     if (world.isWorldServer() && entity instanceof EntityLivingBase && isWitherwood(world, x, y, z)) {
/* 441 */       BlockDeadBush.addWitherEffect(entity.getAsEntityLivingBase());
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 446 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 451 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFlowerPot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */