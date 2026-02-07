/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemGravel
/*    */   extends ItemMultiTextureTile
/*    */ {
/*    */   public ItemGravel(Block block, String[] names) {
/* 15 */     super(block, names);
/* 16 */     setUnlocalizedName("gravel");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack item_stack) {
/* 21 */     if (item_stack == null) {
/* 22 */       return getUnlocalizedName();
/*    */     }
/*    */     
/* 25 */     return Block.gravel.isNetherGravel(item_stack.getItemSubtype()) ? "tile.gravelNether" : getUnlocalizedName();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemGravel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */