/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockClay
/*    */   extends Block
/*    */ {
/*    */   public BlockClay(int par1) {
/* 10 */     super(par1, Material.clay, new BlockConstants());
/* 11 */     setCreativeTab(CreativeTabs.tabBlock);
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
/* 32 */     return dropBlockAsEntityItem(info, Item.clay.itemID, 0, 4, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockClay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */