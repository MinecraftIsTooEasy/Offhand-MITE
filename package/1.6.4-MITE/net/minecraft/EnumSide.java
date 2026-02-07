/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumSide
/*    */ {
/*  7 */   BOTTOM,
/*  8 */   TOP,
/*  9 */   NORTH,
/* 10 */   SOUTH,
/* 11 */   WEST,
/* 12 */   EAST;
/*    */ 
/*    */   
/*    */   public static boolean isValidOrdinal(int ordinal) {
/* 16 */     return (ordinal >= 0 && ordinal < (values()).length);
/*    */   }
/*    */ 
/*    */   
/*    */   public static EnumSide get(int ordinal) {
/* 21 */     return values()[ordinal];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBottom() {
/* 26 */     return (this == BOTTOM);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTop() {
/* 31 */     return (this == TOP);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isNorth() {
/* 36 */     return (this == NORTH);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSouth() {
/* 41 */     return (this == SOUTH);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isWest() {
/* 46 */     return (this == WEST);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEast() {
/* 51 */     return (this == EAST);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTopOrBottom() {
/* 56 */     return (this == TOP || this == BOTTOM);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isNorthOrSouth() {
/* 61 */     return (this == NORTH || this == SOUTH);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEastOrWest() {
/* 66 */     return (this == EAST || this == WEST);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNeighborX(int x) {
/* 71 */     return (this == WEST) ? (x - 1) : ((this == EAST) ? (x + 1) : x);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNeighborY(int y) {
/* 76 */     return (this == BOTTOM) ? (y - 1) : ((this == TOP) ? (y + 1) : y);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNeighborZ(int z) {
/* 81 */     return (this == NORTH) ? (z - 1) : ((this == SOUTH) ? (z + 1) : z);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumSide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */