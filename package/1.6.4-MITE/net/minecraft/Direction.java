/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Direction
/*    */ {
/* 12 */   public static final int[] offsetX = new int[] { 0, -1, 0, 1 };
/*    */ 
/*    */   
/* 15 */   public static final int[] offsetZ = new int[] { 1, 0, -1, 0 };
/*    */ 
/*    */ 
/*    */   
/* 19 */   public static final String[] directions = new String[] { "SOUTH", "WEST", "NORTH", "EAST" };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public static final int[] directionToFacing = new int[] { 3, 4, 2, 5 };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public static final int[] facingToDirection = new int[] { -1, -1, 2, 0, 1, 3 };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public static final int[] rotateOpposite = new int[] { 2, 3, 0, 1 };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   public static final int[] rotateRight = new int[] { 1, 2, 3, 0 };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public static final int[] rotateLeft = new int[] { 3, 0, 1, 2 };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public static final int[][] bedDirection = new int[][] { { 1, 0, 3, 2, 5, 4 }, { 1, 0, 5, 4, 2, 3 }, { 1, 0, 2, 3, 4, 5 }, { 1, 0, 4, 5, 3, 2 } };
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
/*    */   public static int getMovementDirection(double d, double e) {
/* 69 */     if (MathHelper.abs((float)d) > MathHelper.abs((float)e)) {
/* 70 */       if (d > 0.0D) {
/* 71 */         return 1;
/*    */       }
/* 73 */       return 3;
/*    */     } 
/*    */     
/* 76 */     if (e > 0.0D) {
/* 77 */       return 2;
/*    */     }
/* 79 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Direction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */