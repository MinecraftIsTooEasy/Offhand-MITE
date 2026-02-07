/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIMoveToFoodItem
/*    */   extends EntityAIMoveToItem
/*    */ {
/*    */   public EntityAIMoveToFoodItem(EntityLiving task_owner, float movement_speed, boolean swim_if_necessary) {
/* 11 */     super(task_owner, movement_speed, swim_if_necessary);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getNearbyItemEntitiesOfInterest() {
/* 16 */     return this.world.getFoodItemEntitiesWithinAABBForLivingEntity(this.task_owner, this.task_owner.boundingBox.expand(this.max_path_length, (this.max_path_length / 4), this.max_path_length));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean willPickUp(ItemStack item_stack) {
/* 21 */     return this.task_owner.willEat(item_stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 26 */     if (this.task_owner.food_or_repair_item_pickup_cooldown > 0) {
/* 27 */       return false;
/*    */     }
/* 29 */     if (this.task_owner.rand.nextInt(40) > 0) {
/* 30 */       return false;
/*    */     }
/* 32 */     if (!this.task_owner.canEat()) {
/* 33 */       return false;
/*    */     }
/* 35 */     if (this.task_owner instanceof EntityTameable) {
/*    */       
/* 37 */       EntityTameable entity_tameable = (EntityTameable)this.task_owner;
/*    */       
/* 39 */       if (entity_tameable.isTamed())
/*    */       {
/* 41 */         if (entity_tameable.isSitting() || entity_tameable.getHealthFraction() > 0.5F) {
/* 42 */           return false;
/*    */         }
/*    */       }
/*    */     } 
/* 46 */     return super.shouldExecute();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 51 */     if (this.task_owner.food_or_repair_item_pickup_cooldown > 0) {
/* 52 */       return false;
/*    */     }
/* 54 */     return super.continueExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIMoveToFoodItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */