/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockBreakable
/*    */   extends Block
/*    */ {
/*    */   private boolean localFlag;
/*    */   private String breakableBlockIcon;
/*    */   
/*    */   protected BlockBreakable(int par1, String par2Str, Material par3Material, boolean par4, BlockConstants block_constants) {
/* 13 */     super(par1, par3Material, block_constants);
/* 14 */     this.localFlag = par4;
/* 15 */     this.breakableBlockIcon = par2Str;
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
/*    */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 33 */     int var6 = par1IBlockAccess.getBlockId(par2, par3, par4);
/* 34 */     return (!this.localFlag && var6 == this.blockID) ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 43 */     this.blockIcon = par1IconRegister.registerIcon(this.breakableBlockIcon);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBreakable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */