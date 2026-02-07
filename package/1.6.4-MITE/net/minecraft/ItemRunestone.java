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
/*    */ 
/*    */ 
/*    */ public class ItemRunestone
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemRunestone(Block block) {
/* 17 */     super(block);
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
/*    */   public int getMetadata(int par1) {
/* 33 */     return par1;
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
/*    */   public static String getMagicName(ItemStack item_stack) {
/* 47 */     return BlockRunestone.getMagicName(item_stack.getItemSubtype());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getItemDisplayName(ItemStack item_stack) {
/* 52 */     return (item_stack == null) ? super.getItemDisplayName(item_stack) : (super.getItemDisplayName(item_stack) + " \"" + getMagicName(item_stack) + "\"");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBeRenamed() {
/* 57 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemRunestone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */