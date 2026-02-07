/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIRestrictOpenDoor
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature entityObj;
/*    */   private VillageDoorInfo frontDoor;
/*    */   
/*    */   public EntityAIRestrictOpenDoor(EntityCreature entityCreature) {
/* 12 */     this.entityObj = entityCreature;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 17 */     if (this.entityObj.worldObj.isDaytime()) return false; 
/* 18 */     Village village = this.entityObj.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.entityObj.posX), MathHelper.floor_double(this.entityObj.posY), MathHelper.floor_double(this.entityObj.posZ), 16);
/* 19 */     if (village == null) return false; 
/* 20 */     this.frontDoor = village.findNearestDoor(MathHelper.floor_double(this.entityObj.posX), MathHelper.floor_double(this.entityObj.posY), MathHelper.floor_double(this.entityObj.posZ));
/* 21 */     if (this.frontDoor == null) return false; 
/* 22 */     return (this.frontDoor.getInsideDistanceSquare(MathHelper.floor_double(this.entityObj.posX), MathHelper.floor_double(this.entityObj.posY), MathHelper.floor_double(this.entityObj.posZ)) < 2.25D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 27 */     if (this.entityObj.worldObj.isDaytime()) return false; 
/* 28 */     return (!this.frontDoor.isDetachedFromVillageFlag && this.frontDoor.isInside(MathHelper.floor_double(this.entityObj.posX), MathHelper.floor_double(this.entityObj.posZ)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 33 */     this.entityObj.getNavigator().setBreakDoors(false);
/* 34 */     this.entityObj.getNavigator().setEnterDoors(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 39 */     this.entityObj.getNavigator().setBreakDoors(true);
/* 40 */     this.entityObj.getNavigator().setEnterDoors(true);
/* 41 */     this.frontDoor = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 46 */     this.frontDoor.incrementDoorOpeningRestrictionCounter();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIRestrictOpenDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */