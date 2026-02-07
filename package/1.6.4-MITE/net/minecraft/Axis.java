/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum Axis
/*    */ {
/*  7 */   UP_DOWN,
/*  8 */   NORTH_SOUTH,
/*  9 */   EAST_WEST;
/*    */ 
/*    */   
/*    */   boolean isUpDown() {
/* 13 */     return (this == UP_DOWN);
/*    */   }
/*    */ 
/*    */   
/*    */   boolean isNorthSouth() {
/* 18 */     return (this == NORTH_SOUTH);
/*    */   }
/*    */ 
/*    */   
/*    */   boolean isEastWest() {
/* 23 */     return (this == EAST_WEST);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Axis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */