/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySpawnPendingEntry
/*    */ {
/*    */   public Entity entity;
/*    */   public long scheduled_spawn_time;
/*    */   
/*    */   public EntitySpawnPendingEntry(Entity entity, long scheduled_spawn_time) {
/* 12 */     this.entity = entity;
/* 13 */     this.scheduled_spawn_time = scheduled_spawn_time;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySpawnPendingEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */