/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityAIMoveToItem
/*    */   extends EntityAIMovementTask
/*    */ {
/* 11 */   public int max_path_length = 16;
/*    */   
/*    */   private List item_entities;
/*    */   private EntityItem selected_item_entity;
/*    */   
/*    */   public EntityAIMoveToItem(EntityLiving task_owner, float movement_speed, boolean swim_if_necessary) {
/* 17 */     super(task_owner, movement_speed, swim_if_necessary);
/*    */   }
/*    */   
/*    */   public abstract List getNearbyItemEntitiesOfInterest();
/*    */   
/*    */   public abstract boolean willPickUp(ItemStack paramItemStack);
/*    */   
/*    */   public boolean shouldExecute() {
/* 25 */     this.item_entities = getNearbyItemEntitiesOfInterest();
/*    */     
/* 27 */     return !this.item_entities.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PathEntity getMovementPath() {
/* 32 */     PathEntity selected_path = null;
/* 33 */     int shortest_path_length_to_item_entity = Integer.MAX_VALUE;
/*    */     
/* 35 */     Iterator<EntityItem> i = this.item_entities.iterator();
/*    */     
/* 37 */     while (i.hasNext()) {
/*    */       
/* 39 */       EntityItem entity_item = i.next();
/*    */       
/* 41 */       if (willPickUp(entity_item.getEntityItem())) {
/*    */         
/* 43 */         int entity_item_x = MathHelper.floor_double(entity_item.posX);
/* 44 */         int entity_item_y = MathHelper.floor_double(entity_item.posY);
/* 45 */         int entity_item_z = MathHelper.floor_double(entity_item.posZ);
/*    */         
/* 47 */         PathEntity path = this.task_owner.getNavigator().getPathToXYZ(entity_item_x, entity_item_y, entity_item_z, this.max_path_length);
/*    */         
/* 49 */         if (path != null && path.getCurrentPathLength() < shortest_path_length_to_item_entity) {
/*    */           
/* 51 */           PathPoint final_point = path.getFinalPathPoint();
/*    */           
/* 53 */           if (final_point.xCoord == entity_item_x && final_point.yCoord == entity_item_y && final_point.zCoord == entity_item_z) {
/*    */             
/* 55 */             shortest_path_length_to_item_entity = path.getCurrentPathLength();
/* 56 */             this.selected_item_entity = entity_item;
/* 57 */             selected_path = path;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 63 */     return selected_path;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 68 */     if (this.selected_item_entity == null || this.selected_item_entity.isDead) {
/* 69 */       return false;
/*    */     }
/* 71 */     return super.continueExecuting();
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 76 */     super.resetTask();
/*    */     
/* 78 */     this.item_entities = null;
/* 79 */     this.selected_item_entity = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIMoveToItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */