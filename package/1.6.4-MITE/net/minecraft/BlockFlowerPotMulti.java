/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFlowerPotMulti
/*     */   extends BlockFlowerPot
/*     */ {
/*     */   public BlockFlowerPotMulti(int id) {
/*   9 */     super(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  14 */     String[] types = BlockFlowerMulti.types;
/*  15 */     String[] array = new String[16];
/*     */     
/*  17 */     for (int i = 0; i < 16; i++) {
/*     */       
/*  19 */       if (isValidMetadata(i)) {
/*  20 */         StringHelper.addToStringArray(i + "=" + StringHelper.capitalize(types[i]), array);
/*     */       }
/*     */     } 
/*  23 */     return StringHelper.implode(array, ", ", true, false) + " (empty and rose-filled pots are always BlockFlowerPot)";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  28 */     return (metadata != 0 && plantRed.isValidMetadata(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  33 */     ItemStack item_stack = player.getHeldItemStack();
/*     */     
/*  35 */     if (item_stack == null) {
/*  36 */       return false;
/*     */     }
/*  38 */     if (BlockFlowerPot.getMetaForPlant(item_stack) != 0) {
/*     */       
/*  40 */       if (player.onServer()) {
/*     */         
/*  42 */         int i = world.getBlockMetadata(x, y, z);
/*     */         
/*  44 */         if (i != 0) {
/*     */           
/*  46 */           BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
/*  47 */           dropBlockAsEntityItem(info, p_(i));
/*     */           
/*  49 */           world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */         } 
/*     */         
/*  52 */         world.setBlock(x, y, z, flowerPot.blockID, BlockFlowerPot.getMetaForPlant(item_stack), 2);
/*     */         
/*  54 */         if (!player.inCreativeMode()) {
/*  55 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/*  58 */       return true;
/*     */     } 
/*     */     
/*  61 */     int metadata_for_plant = a(item_stack);
/*     */     
/*  63 */     if (metadata_for_plant == 0) {
/*  64 */       return false;
/*     */     }
/*  66 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/*  68 */     if (metadata == metadata_for_plant) {
/*  69 */       return false;
/*     */     }
/*  71 */     if (player.onServer()) {
/*     */       
/*  73 */       if (metadata != 0) {
/*     */         
/*  75 */         BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
/*  76 */         dropBlockAsEntityItem(info, p_(metadata));
/*     */         
/*  78 */         world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */       } 
/*     */       
/*  81 */       world.setBlockMetadataWithNotify(x, y, z, metadata_for_plant, 2);
/*     */       
/*  83 */       if (!player.inCreativeMode()) {
/*  84 */         player.convertOneOfHeldItem((ItemStack)null);
/*     */       }
/*     */     } 
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/*  97 */     if (info.wasExploded() || info.wasCrushed()) {
/*  98 */       return 0;
/*     */     }
/*     */     
/*     */     int num_drops;
/* 102 */     if ((num_drops = dropBlockAsEntityItem(info, Item.flowerPot)) > 0) {
/* 103 */       return num_drops + dropBlockAsEntityItem(info, p_(info.getMetadata()));
/*     */     }
/* 105 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack p_(int metadata) {
/* 110 */     if (metadata == 0) {
/* 111 */       return null;
/*     */     }
/* 113 */     return new ItemStack(Block.plantRed, 1, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int a(ItemStack item_stack) {
/* 118 */     return (item_stack.itemID == Block.plantRed.blockID) ? item_stack.getItemSubtype() : 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFlowerPotMulti.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */