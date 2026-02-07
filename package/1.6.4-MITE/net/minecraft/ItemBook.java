/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBook
/*    */   extends Item
/*    */ {
/*    */   public ItemBook(int id, String texture) {
/* 12 */     super(id, texture);
/* 13 */     setMaterial(new Material[] { Material.paper, Material.leather });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isItemTool(ItemStack par1ItemStack) {
/* 21 */     return (par1ItemStack.stackSize == 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getItemEnchantability() {
/* 30 */     return 30;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBeRenamed() {
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */