/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIMoveThroughVillage
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature theEntity;
/*    */   private double movementSpeed;
/*    */   private PathEntity entityPathNavigate;
/*    */   private VillageDoorInfo doorInfo;
/*    */   private boolean isNocturnal;
/* 20 */   private List doorList = new ArrayList();
/*    */   
/*    */   public EntityAIMoveThroughVillage(EntityCreature entityCreature, double d, boolean bl) {
/* 23 */     this.theEntity = entityCreature;
/* 24 */     this.movementSpeed = d;
/* 25 */     this.isNocturnal = bl;
/* 26 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 31 */     func_75414_f();
/*    */     
/* 33 */     if (this.isNocturnal && this.theEntity.worldObj.isDaytime()) return false;
/*    */     
/* 35 */     Village village = this.theEntity.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.theEntity.posX), MathHelper.floor_double(this.theEntity.posY), MathHelper.floor_double(this.theEntity.posZ), 0);
/* 36 */     if (village == null) return false;
/*    */     
/* 38 */     this.doorInfo = func_75412_a(village);
/* 39 */     if (this.doorInfo == null) return false;
/*    */     
/* 41 */     boolean bool = this.theEntity.getNavigator().getCanBreakDoors();
/* 42 */     this.theEntity.getNavigator().setBreakDoors(false);
/* 43 */     this.entityPathNavigate = this.theEntity.getNavigator().getPathToXYZ(this.doorInfo.posX, this.doorInfo.posY, this.doorInfo.posZ);
/* 44 */     this.theEntity.getNavigator().setBreakDoors(bool);
/* 45 */     if (this.entityPathNavigate != null) return true;
/*    */     
/* 47 */     Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 10, 7, this.theEntity.worldObj.getWorldVec3Pool().getVecFromPool(this.doorInfo.posX, this.doorInfo.posY, this.doorInfo.posZ));
/* 48 */     if (vec3 == null) return false; 
/* 49 */     this.theEntity.getNavigator().setBreakDoors(false);
/* 50 */     this.entityPathNavigate = this.theEntity.getNavigator().getPathToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord);
/* 51 */     this.theEntity.getNavigator().setBreakDoors(bool);
/* 52 */     return (this.entityPathNavigate != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 57 */     if (this.theEntity.getNavigator().noPath()) return false; 
/* 58 */     float f = this.theEntity.width + 4.0F;
/* 59 */     return (this.theEntity.getDistanceSq(this.doorInfo.posX, this.doorInfo.posY, this.doorInfo.posZ) > (f * f));
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 64 */     this.theEntity.getNavigator().setPath(this.entityPathNavigate, this.movementSpeed);
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 69 */     if (this.theEntity.getNavigator().noPath() || this.theEntity.getDistanceSq(this.doorInfo.posX, this.doorInfo.posY, this.doorInfo.posZ) < 16.0D) this.doorList.add(this.doorInfo); 
/*    */   }
/*    */   
/*    */   private VillageDoorInfo func_75412_a(Village village) {
/* 73 */     VillageDoorInfo villageDoorInfo = null;
/* 74 */     int i = Integer.MAX_VALUE;
/* 75 */     List list = village.getVillageDoorInfoList();
/* 76 */     for (VillageDoorInfo villageDoorInfo1 : list) {
/* 77 */       int j = villageDoorInfo1.getDistanceSquared(MathHelper.floor_double(this.theEntity.posX), MathHelper.floor_double(this.theEntity.posY), MathHelper.floor_double(this.theEntity.posZ));
/* 78 */       if (j >= i || 
/* 79 */         func_75413_a(villageDoorInfo1))
/* 80 */         continue;  villageDoorInfo = villageDoorInfo1;
/* 81 */       i = j;
/*    */     } 
/*    */     
/* 84 */     return villageDoorInfo;
/*    */   }
/*    */   
/*    */   private boolean func_75413_a(VillageDoorInfo villageDoorInfo) {
/* 88 */     for (VillageDoorInfo villageDoorInfo1 : this.doorList) {
/* 89 */       if (villageDoorInfo.posX == villageDoorInfo1.posX && villageDoorInfo.posY == villageDoorInfo1.posY && villageDoorInfo.posZ == villageDoorInfo1.posZ) return true; 
/* 90 */     }  return false;
/*    */   }
/*    */   
/*    */   private void func_75414_f() {
/* 94 */     if (this.doorList.size() > 15) this.doorList.remove(0); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIMoveThroughVillage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */