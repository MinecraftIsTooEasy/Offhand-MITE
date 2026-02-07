/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BlockBoundsContext
/*    */ {
/*    */   public static final int FOR_ENTITY_COLLISION = 1;
/*    */   public static final int FOR_RAYCAST = 2;
/*    */   public static final int FOR_PLAYER_SELECTION = 4;
/*    */   private final int context;
/*    */   public final Entity entity;
/*    */   
/*    */   public BlockBoundsContext(int context, Entity entity) {
/* 17 */     if (context == 4) {
/* 18 */       context |= 0x2;
/*    */     }
/* 20 */     this.context = context;
/* 21 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isForEntityCollision() {
/* 26 */     return BitHelper.isBitSet(this.context, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isForRaycast() {
/* 31 */     return BitHelper.isBitSet(this.context, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isForPlayerSelection() {
/* 36 */     return BitHelper.isBitSet(this.context, 4);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBoundsContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */