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
/*    */ public class ItemLilyPad
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemLilyPad(Block block) {
/* 65 */     super(block);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 70 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*    */     
/* 72 */     if (rc == null || !rc.isBlock()) {
/* 73 */       return false;
/*    */     }
/* 75 */     if (rc.face_hit.isTop() && rc.isBlockHitFullWaterBlock(false)) {
/* 76 */       return player.tryPlaceHeldItemAsBlock(rc, Block.waterlily);
/*    */     }
/* 78 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
/* 83 */     return Block.waterlily.getRenderColor(par1ItemStack.getItemSubtype());
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemLilyPad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */