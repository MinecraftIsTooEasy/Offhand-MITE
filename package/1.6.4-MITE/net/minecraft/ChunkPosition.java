/*    */ package net.minecraft;
/*    */ 
/*    */ public class ChunkPosition
/*    */ {
/*    */   public final int x;
/*    */   public final int y;
/*    */   public final int z;
/*    */   
/*    */   public ChunkPosition(int i, int j, int k) {
/* 10 */     this.x = i;
/* 11 */     this.y = j;
/* 12 */     this.z = k;
/*    */   }
/*    */   
/*    */   public ChunkPosition(Vec3 vec3) {
/* 16 */     this(MathHelper.floor_double(vec3.xCoord), MathHelper.floor_double(vec3.yCoord), MathHelper.floor_double(vec3.zCoord));
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 20 */     if (object instanceof ChunkPosition) {
/*    */       
/* 22 */       ChunkPosition chunkPosition = (ChunkPosition)object;
/* 23 */       return (chunkPosition.x == this.x && chunkPosition.y == this.y && chunkPosition.z == this.z);
/*    */     } 
/*    */     
/* 26 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 30 */     return this.x * 8976890 + this.y * 981131 + this.z;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */