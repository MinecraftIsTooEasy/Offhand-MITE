/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class Coords
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   public int z;
/*    */   
/*    */   public Coords() {}
/*    */   
/*    */   public Coords(int x, int y, int z) {
/* 13 */     set(x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public Coords set(int x, int y, int z) {
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/* 20 */     this.z = z;
/*    */     
/* 22 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(int x, int y, int z) {
/* 27 */     return (this.x == x && this.y == y && this.z == z);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Coords.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */