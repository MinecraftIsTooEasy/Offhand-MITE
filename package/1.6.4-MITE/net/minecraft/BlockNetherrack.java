/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BlockNetherrack
/*    */   extends Block
/*    */ {
/*    */   public BlockNetherrack(int par1) {
/*  9 */     super(par1, Material.netherrack, new BlockConstants());
/* 10 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 15 */     return info.wasExploded() ? 0 : super.dropBlockAsEntityItem(info);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockNetherrack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */