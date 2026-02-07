/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockWood
/*    */   extends Block
/*    */   implements IBlockWithSubtypes
/*    */ {
/*    */   private BlockSubtypes subtypes;
/*    */   
/*    */   public BlockWood(int par1) {
/* 12 */     super(par1, Material.wood, new BlockConstants());
/*    */     
/* 14 */     this.subtypes = new BlockSubtypes(new String[] { "oak", "spruce", "birch", "jungle" });
/*    */     
/* 16 */     setMaxStackSize(8);
/* 17 */     setHardness(BlockHardness.planks);
/* 18 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 23 */     return (metadata >= 0 && metadata < 4);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBlockSubtypeUnchecked(int metadata) {
/* 28 */     return metadata & 0x3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 33 */     this.subtypes.setIcons(registerIcons(par1IconRegister, getTextures(), getTextureName() + "_"));
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getIcon(int side, int metadata) {
/* 38 */     return this.subtypes.getIcon(getBlockSubtype(metadata));
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getTextures() {
/* 43 */     return this.subtypes.getTextures();
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getNames() {
/* 48 */     return this.subtypes.getNames();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockWood.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */