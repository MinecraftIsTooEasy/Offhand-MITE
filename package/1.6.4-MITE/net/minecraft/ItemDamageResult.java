/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemDamageResult
/*    */ {
/*    */   private boolean item_lost_durability;
/*    */   private boolean item_was_destroyed;
/*    */   
/*    */   public ItemDamageResult setItemLostDurability() {
/* 13 */     this.item_lost_durability = true;
/* 14 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean itemLostDurability() {
/* 19 */     return this.item_lost_durability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemDamageResult setItemWasDestroyed(World world, ItemStack item_stack) {
/* 25 */     this.item_was_destroyed = true;
/*    */     
/* 27 */     if (!world.isRemote) {
/* 28 */       world.tryRemoveFromWorldUniques(item_stack);
/*    */     }
/* 30 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean itemWasDestroyed() {
/* 35 */     return this.item_was_destroyed;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemDamageResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */