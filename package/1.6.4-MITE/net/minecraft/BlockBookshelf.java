/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockBookshelf
/*    */   extends Block
/*    */ {
/*    */   public BlockBookshelf(int par1) {
/* 10 */     super(par1, Material.wood, new BlockConstants());
/* 11 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIcon(int par1, int par2) {
/* 19 */     return (par1 != 1 && par1 != 0) ? super.getIcon(par1, par2) : Block.planks.getBlockTextureFromSide(par1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 40 */     if (info.wasExploded()) {
/* 41 */       return dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.5F) + dropBlockAsEntityItem(info, Item.paper.itemID, 0, 1, 1.5F);
/*    */     }
/* 43 */     return dropBlockAsEntityItem(info, Item.book.itemID, 0, 3, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 48 */     item_block.addMaterial(new Material[] { Material.wood, Material.paper, Material.leather });
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBookshelf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */