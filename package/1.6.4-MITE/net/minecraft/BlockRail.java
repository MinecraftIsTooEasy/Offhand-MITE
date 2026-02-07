/*    */ package net.minecraft;
/*    */ 
/*    */ public class BlockRail
/*    */   extends BlockRailBase
/*    */ {
/*    */   private Icon theIcon;
/*    */   
/*    */   protected BlockRail(int par1) {
/*  9 */     super(par1, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMetadataNotes() {
/* 14 */     return "0=Flat NS, 1=Flat EW, 2=Inclined East, 3=Inclined West, 4=Inclined North, 5=Inclined South, 6=Curved SE, 7=Curved SW, 8=Curved NW, 9=Curved NE";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 19 */     return (metadata >= 0 && metadata < 10);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIcon(int par1, int par2) {
/* 27 */     return (par2 >= 6) ? this.theIcon : this.blockIcon;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 36 */     super.registerIcons(par1IconRegister);
/* 37 */     this.theIcon = par1IconRegister.registerIcon(getTextureName() + "_turned");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_94358_a(World par1World, int par2, int par3, int par4, int par5, int par6, int par7) {
/* 42 */     if (par7 > 0 && Block.blocksList[par7].canProvidePower() && (new BlockBaseRailLogic(this, par1World, par2, par3, par4)).getNumberOfAdjacentTracks() == 3)
/*    */     {
/* 44 */       refreshTrackShape(par1World, par2, par3, par4, false);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 50 */     item_block.addMaterial(new Material[] { Material.iron, Material.wood });
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */