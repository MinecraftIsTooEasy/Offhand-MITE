/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class AABBIntercept
/*    */ {
/*    */   public final Vec3 position_hit;
/*    */   public final EnumFace face_hit;
/*    */   
/*    */   public AABBIntercept(Vec3 position_hit, EnumFace face_hit) {
/* 12 */     this.position_hit = position_hit;
/* 13 */     this.face_hit = face_hit;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 18 */     return this.position_hit.toString() + ", face=" + this.face_hit;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AABBIntercept.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */