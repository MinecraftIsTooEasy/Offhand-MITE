/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class BlockHay
/*    */   extends BlockRotatedPillar
/*    */ {
/*    */   public BlockHay(int par1) {
/*  8 */     super(par1, Material.plants);
/*  9 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */     
/* 11 */     setCushioning(0.4F);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMetadataNotes() {
/* 16 */     return "Bit 4 set if aligned WE, and bit 8 set if aligned NS";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 21 */     return (metadata == 0 || metadata == 4 || metadata == 8);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderType() {
/* 29 */     return 31;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Icon getSideIcon(int par1) {
/* 37 */     return this.blockIcon;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 46 */     this.field_111051_a = par1IconRegister.registerIcon(getTextureName() + "_top");
/* 47 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/*    */   }
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 52 */     if (info.wasExploded()) {
/* 53 */       return dropBlockAsEntityItem(info, Item.wheat.itemID, 0, 9, 0.5F);
/*    */     }
/* 55 */     return super.dropBlockAsEntityItem(info);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockHay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */