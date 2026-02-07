/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public final class BlockSlabGroup3
/*    */   extends BlockSlab
/*    */ {
/*  7 */   private static String[] types = new String[] { "obsidian" };
/*    */ 
/*    */   
/*    */   public BlockSlabGroup3(int id, Material material) {
/* 11 */     super(id, material);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGroup() {
/* 16 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getTypes() {
/* 21 */     return types;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 26 */     return (metadata == 0 || metadata == 8);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBlockSubtypeUnchecked(int metadata) {
/* 31 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getModelBlock(int metadata) {
/* 36 */     return obsidian;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSlabGroup3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */