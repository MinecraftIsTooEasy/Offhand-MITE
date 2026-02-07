/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockRunestone
/*     */   extends BlockObsidian
/*     */ {
/*  10 */   private static String[] magic_names = new String[] { "Nul", "Quas", "Por", "An", "Nox", "Flam", "Vas", "Des", "Ort", "Tym", "Corp", "Lor", "Mani", "Jux", "Ylem", "Sanct" };
/*     */ 
/*     */   
/*  13 */   protected Icon[] iconArray = new Icon[16];
/*     */   
/*     */   public Material rune_metal;
/*     */   
/*     */   public BlockRunestone(int id, Material rune_metal) {
/*  18 */     super(id);
/*  19 */     this.rune_metal = rune_metal;
/*  20 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon(int side, int metadata) {
/*  25 */     return (side == 0 || side == 1) ? this.blockIcon : this.iconArray[metadata];
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  30 */     super.registerIcons(par1IconRegister);
/*     */ 
/*     */ 
/*     */     
/*  34 */     for (int i = 0; i < this.iconArray.length; i++)
/*  35 */       this.iconArray[i] = par1IconRegister.registerIcon("runestones/" + this.rune_metal.name + "/" + i); 
/*     */   }
/*     */   
/*     */   public void scheduleUpdatesForNearbyPortalBlocks(World world, int x, int y, int z) {
/*     */     Block block;
/*     */     int check_x;
/*     */     int check_y;
/*     */     int check_z;
/*  43 */     if ((block = world.getBlock(check_x = x - 1, check_y = y + 1, check_z = z)) == portal) {
/*  44 */       world.scheduleBlockUpdate(check_x, check_y, check_z, portal.blockID, 1);
/*     */     }
/*  46 */     if ((block = world.getBlock(check_x = x + 1, check_y = y + 1, check_z = z)) == portal) {
/*  47 */       world.scheduleBlockUpdate(check_x, check_y, check_z, portal.blockID, 1);
/*     */     }
/*  49 */     if ((block = world.getBlock(check_x = x - 1, check_y = y - 1, check_z = z)) == portal) {
/*  50 */       world.scheduleBlockUpdate(check_x, check_y, check_z, portal.blockID, 1);
/*     */     }
/*  52 */     if ((block = world.getBlock(check_x = x + 1, check_y = y - 1, check_z = z)) == portal) {
/*  53 */       world.scheduleBlockUpdate(check_x, check_y, check_z, portal.blockID, 1);
/*     */     }
/*  55 */     if ((block = world.getBlock(check_x = x, check_y = y + 1, check_z = z - 1)) == portal) {
/*  56 */       world.scheduleBlockUpdate(check_x, check_y, check_z, portal.blockID, 1);
/*     */     }
/*  58 */     if ((block = world.getBlock(check_x = x, check_y = y + 1, check_z = z + 1)) == portal) {
/*  59 */       world.scheduleBlockUpdate(check_x, check_y, check_z, portal.blockID, 1);
/*     */     }
/*  61 */     if ((block = world.getBlock(check_x = x, check_y = y - 1, check_z = z - 1)) == portal) {
/*  62 */       world.scheduleBlockUpdate(check_x, check_y, check_z, portal.blockID, 1);
/*     */     }
/*  64 */     if ((block = world.getBlock(check_x = x, check_y = y - 1, check_z = z + 1)) == portal) {
/*  65 */       world.scheduleBlockUpdate(check_x, check_y, check_z, portal.blockID, 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onBlockAdded(World world, int x, int y, int z) {
/*  70 */     scheduleUpdatesForNearbyPortalBlocks(world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {
/*  75 */     scheduleUpdatesForNearbyPortalBlocks(world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMagicName(int metadata) {
/*  80 */     return magic_names[metadata];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  85 */     String[] array = new String[this.iconArray.length];
/*     */     
/*  87 */     for (int i = 0; i < array.length; i++) {
/*  88 */       array[i] = i + "=\"" + getMagicName(i) + "\"";
/*     */     }
/*  90 */     return StringHelper.implode(array, ", ", true, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  95 */     return (metadata >= 0 && metadata < 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/* 100 */     return metadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 111 */     item_block.addMaterial(new Material[] { this.blockMaterial, this.rune_metal });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 116 */     return this.rune_metal.name;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRunestone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */