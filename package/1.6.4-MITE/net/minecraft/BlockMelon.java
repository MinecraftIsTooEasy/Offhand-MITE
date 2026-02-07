/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockMelon
/*    */   extends Block
/*    */ {
/*    */   private Icon theIcon;
/*    */   
/*    */   protected BlockMelon(int par1) {
/* 12 */     super(par1, Material.pumpkin, new BlockConstants());
/* 13 */     setMaxStackSize(8);
/* 14 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIcon(int par1, int par2) {
/* 22 */     return (par1 != 1 && par1 != 0) ? this.blockIcon : this.theIcon;
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
/* 43 */     if (info.wasExploded()) {
/* 44 */       return dropBlockAsEntityItem(info, Item.melonSeeds);
/*    */     }
/* 46 */     return dropBlockAsEntityItem(info, Item.melon.itemID, 0, 4, 1.0F);
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
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 70 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/* 71 */     this.theIcon = par1IconRegister.registerIcon(getTextureName() + "_top");
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockMelon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */