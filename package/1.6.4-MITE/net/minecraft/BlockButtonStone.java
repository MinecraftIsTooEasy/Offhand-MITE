/*    */ package net.minecraft;
/*    */ 
/*    */ public class BlockButtonStone
/*    */   extends BlockButton
/*    */ {
/*    */   protected BlockButtonStone(int par1) {
/*  7 */     super(par1, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIcon(int par1, int par2) {
/* 15 */     return Block.stone.getBlockTextureFromSide(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 20 */     return "stone";
/*    */   }
/*    */ 
/*    */   
/*    */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 25 */     item_block.addMaterial(new Material[] { Material.stone });
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockButtonStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */