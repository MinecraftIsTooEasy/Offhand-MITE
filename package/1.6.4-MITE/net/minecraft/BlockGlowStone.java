/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockGlowStone
/*    */   extends Block
/*    */ {
/*    */   public BlockGlowStone(int par1, Material par2Material) {
/* 10 */     super(par1, par2Material, (new BlockConstants()).setNeverConnectsWithFence());
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 40 */     return dropBlockAsEntityItem(info, Item.glowstone.itemID, 0, 2 + info.world.rand.nextInt(3), 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockGlowStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */