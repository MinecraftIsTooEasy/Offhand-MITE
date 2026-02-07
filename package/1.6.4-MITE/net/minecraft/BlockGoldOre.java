/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class BlockGoldOre
/*    */   extends BlockOre
/*    */   implements IBlockWithSubtypes
/*    */ {
/*    */   private BlockSubtypes subtypes;
/*    */   
/*    */   public BlockGoldOre(int par1, Material vein_material, int min_harvest_level) {
/* 11 */     super(par1, vein_material, min_harvest_level);
/*    */     
/* 13 */     this.subtypes = new BlockSubtypes(new String[] { "gold_ore", "gold_ore_netherrack" });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMetadataNotes() {
/* 18 */     return "0=Gold Ore Stone, 2=Gold Ore Netherrack, bit 1 set if placed by entity";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 23 */     return (metadata >= 0 && metadata < 4);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBlockSubtypeUnchecked(int metadata) {
/* 28 */     return BitHelper.isBitSet(metadata, 2) ? 1 : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getItemSubtype(int metadata) {
/* 33 */     return (getBlockSubtype(metadata) == 1) ? 2 : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isGoldOreNetherrack(int metadata) {
/* 38 */     return isGoldOreNetherrack(this, metadata);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isGoldOreNetherrack(Block block, int metadata) {
/* 43 */     return (block == oreGold && block.getBlockSubtype(metadata) == 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 48 */     this.subtypes.setIcons(registerIcons(par1IconRegister, getTextures()));
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getIcon(int side, int metadata) {
/* 53 */     return this.subtypes.getIcon(getBlockSubtype(metadata));
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getTextures() {
/* 58 */     return this.subtypes.getTextures();
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getNames() {
/* 63 */     return this.subtypes.getNames();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 68 */     return item_stack.getItemSubtype() | 0x1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockGoldOre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */