/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BlockStone
/*    */   extends Block
/*    */ {
/*    */   public BlockStone(int par1) {
/* 11 */     super(par1, Material.stone, new BlockConstants());
/* 12 */     setCreativeTab(CreativeTabs.tabBlock);
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
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 25 */     return dropBlockAsEntityItem(info, cobblestone);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */