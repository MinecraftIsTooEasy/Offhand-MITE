/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ItemSoup
/*    */   extends ItemFood
/*    */ {
/*    */   public ItemSoup(int i, int j) {
/*  8 */     super(i, j, false);
/*    */     
/* 10 */     setMaxStackSize(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
/* 15 */     b(itemStack, world, entityPlayer);
/*    */     
/* 17 */     return new ItemStack(Item.G);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSoup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */