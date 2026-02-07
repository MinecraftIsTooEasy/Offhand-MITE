/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class BlockPoweredOre
/*    */   extends BlockOreStorage
/*    */ {
/*    */   public BlockPoweredOre(int par1) {
/*  8 */     super(par1, Material.redstone);
/*  9 */     setCreativeTab(CreativeTabs.tabRedstone);
/*    */     
/* 11 */     setMaxStackSize(4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canProvidePower() {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 29 */     return 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 34 */     if (info.wasExploded()) {
/* 35 */       return dropBlockAsEntityItem(info, Item.redstone.itemID, 0, 9, 0.5F);
/*    */     }
/* 37 */     return super.dropBlockAsEntityItem(info);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDropExperienceOrbs() {
/* 42 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPoweredOre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */