/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ public class PlayerPositionComparator
/*    */   implements Comparator
/*    */ {
/*    */   private final ChunkCoordinates theChunkCoordinates;
/*    */   
/*    */   public PlayerPositionComparator(ChunkCoordinates chunkCoordinates) {
/* 12 */     this.theChunkCoordinates = chunkCoordinates;
/*    */   }
/*    */ 
/*    */   
/*    */   public int comparePlayers(ServerPlayer serverPlayer, ServerPlayer serverPlayer2) {
/* 17 */     double d1 = serverPlayer.getDistanceSq(this.theChunkCoordinates.posX, this.theChunkCoordinates.posY, this.theChunkCoordinates.posZ);
/* 18 */     double d2 = serverPlayer2.getDistanceSq(this.theChunkCoordinates.posX, this.theChunkCoordinates.posY, this.theChunkCoordinates.posZ);
/*    */     
/* 20 */     if (d1 < d2)
/* 21 */       return -1; 
/* 22 */     if (d1 > d2) {
/* 23 */       return 1;
/*    */     }
/* 25 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerPositionComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */