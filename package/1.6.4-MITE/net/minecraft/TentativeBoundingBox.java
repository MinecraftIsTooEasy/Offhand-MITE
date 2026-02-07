/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TentativeBoundingBox
/*    */ {
/*    */   int x;
/*    */   int y;
/*    */   int z;
/*    */   AxisAlignedBB bb;
/*    */   int countdown_for_clearing;
/*    */   
/*    */   public TentativeBoundingBox(int x, int y, int z, AxisAlignedBB bb) {
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/* 16 */     this.z = z;
/*    */     
/* 18 */     this.bb = bb;
/*    */     
/* 20 */     this.countdown_for_clearing = 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(int x, int y, int z) {
/* 25 */     return (x == this.x && y == this.y && z == this.z);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TentativeBoundingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */