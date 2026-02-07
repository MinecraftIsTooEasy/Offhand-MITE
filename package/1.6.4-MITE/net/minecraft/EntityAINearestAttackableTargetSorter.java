/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Comparator;
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
/*    */ 
/*    */ 
/*    */ public class EntityAINearestAttackableTargetSorter
/*    */   implements Comparator
/*    */ {
/*    */   private final Entity theEntity;
/*    */   
/*    */   public EntityAINearestAttackableTargetSorter(Entity entity) {
/* 66 */     this.theEntity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareDistanceSq(Entity entity, Entity entity2) {
/* 71 */     double d1 = this.theEntity.getDistanceSqToEntity(entity);
/* 72 */     double d2 = this.theEntity.getDistanceSqToEntity(entity2);
/* 73 */     if (d1 < d2) return -1; 
/* 74 */     if (d1 > d2) return 1; 
/* 75 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAINearestAttackableTargetSorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */