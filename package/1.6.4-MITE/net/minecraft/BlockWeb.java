/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockWeb
/*    */   extends Block
/*    */ {
/*    */   public BlockWeb(int par1) {
/* 10 */     super(par1, Material.web, (new BlockConstants()).setNeverHidesAdjacentFaces());
/* 11 */     setCreativeTab(CreativeTabs.tabDecorations);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 19 */     par5Entity.setInWeb();
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
/*    */   public int getRenderType() {
/* 45 */     return 1;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 80 */     if (info.wasHarvested()) {
/*    */       
/* 82 */       Item item = info.getHarvesterItem();
/*    */       
/* 84 */       if (item instanceof ItemSword || item instanceof ItemShears) {
/* 85 */         return dropBlockAsEntityItem(info, Item.silk);
/*    */       }
/*    */     } 
/* 88 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 93 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 98 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockWeb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */