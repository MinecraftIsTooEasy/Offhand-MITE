/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class EntitySorter implements Comparator {
/*    */   private double entityPosX;
/*    */   private double entityPosY;
/*    */   private double entityPosZ;
/*    */   
/*    */   public EntitySorter(Entity entity) {
/* 11 */     this.entityPosX = -entity.posX;
/* 12 */     this.entityPosY = -entity.posY;
/* 13 */     this.entityPosZ = -entity.posZ;
/*    */   }
/*    */ 
/*    */   
/*    */   public int sortByDistanceToEntity(WorldRenderer worldRenderer, WorldRenderer worldRenderer2) {
/* 18 */     double d1 = worldRenderer.posXPlus + this.entityPosX;
/* 19 */     double d2 = worldRenderer.posYPlus + this.entityPosY;
/* 20 */     double d3 = worldRenderer.posZPlus + this.entityPosZ;
/*    */     
/* 22 */     double d4 = worldRenderer2.posXPlus + this.entityPosX;
/* 23 */     double d5 = worldRenderer2.posYPlus + this.entityPosY;
/* 24 */     double d6 = worldRenderer2.posZPlus + this.entityPosZ;
/*    */     
/* 26 */     return (int)((d1 * d1 + d2 * d2 + d3 * d3 - d4 * d4 + d5 * d5 + d6 * d6) * 1024.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */