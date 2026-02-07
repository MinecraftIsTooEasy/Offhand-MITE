/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockObsidian
/*    */   extends Block
/*    */ {
/*    */   public BlockObsidian(int par1) {
/* 11 */     super(par1, Material.obsidian, new BlockConstants());
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 33 */     if (info.wasExploded())
/*    */     {
/* 35 */       return dropBlockAsEntityItem(info, Item.shardObsidian.itemID, 0, 6, 0.5F);
/*    */     }
/* 37 */     return super.dropBlockAsEntityItem(info);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockObsidian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */