/*    */ package net.minecraft;
/*    */ 
/*    */ public enum EnumFacing {
/*  4 */   DOWN(0, 1, 0, -1, 0),
/*  5 */   UP(1, 0, 0, 1, 0),
/*  6 */   NORTH(2, 3, 0, 0, -1),
/*  7 */   SOUTH(3, 2, 0, 0, 1),
/*  8 */   EAST(4, 5, -1, 0, 0),
/*  9 */   WEST(5, 4, 1, 0, 0);
/*    */   
/*    */   private final int order_a;
/*    */   
/*    */   private final int order_b;
/*    */   private final int frontOffsetX;
/*    */   
/*    */   static {
/* 17 */     faceList = new EnumFacing[6];
/*    */     
/* 19 */     for (EnumFacing enumFacing : values())
/* 20 */       faceList[enumFacing.order_a] = enumFacing; 
/*    */   }
/*    */   private final int frontOffsetY; private final int frontOffsetZ; private static final EnumFacing[] faceList;
/*    */   
/*    */   EnumFacing(int j, int k, int l, int m, int n) {
/* 25 */     this.order_a = j;
/* 26 */     this.order_b = k;
/* 27 */     this.frontOffsetX = l;
/* 28 */     this.frontOffsetY = m;
/* 29 */     this.frontOffsetZ = n;
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
/*    */   public int getFrontOffsetX() {
/* 41 */     return this.frontOffsetX;
/*    */   }
/*    */   
/*    */   public int getFrontOffsetY() {
/* 45 */     return this.frontOffsetY;
/*    */   }
/*    */   
/*    */   public int getFrontOffsetZ() {
/* 49 */     return this.frontOffsetZ;
/*    */   }
/*    */   
/*    */   public static EnumFacing getFront(int i) {
/* 53 */     return faceList[i % faceList.length];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumFacing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */