/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemMantleOrCore
/*    */   extends ItemMultiTextureTile
/*    */ {
/*    */   public ItemMantleOrCore(Block block, String[] names) {
/*  9 */     super(block, names);
/* 10 */     setUnlocalizedName("mantle");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack item_stack) {
/* 15 */     if (item_stack == null) {
/* 16 */       return getUnlocalizedName();
/*    */     }
/* 18 */     return BlockMantleOrCore.isCore(Block.mantleOrCore, item_stack.getItemSubtype()) ? "tile.core" : getUnlocalizedName();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemMantleOrCore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */