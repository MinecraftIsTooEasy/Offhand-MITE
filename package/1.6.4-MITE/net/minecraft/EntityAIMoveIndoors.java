/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityAIMoveIndoors
/*     */   extends EntityAIBase {
/*     */   private EntityCreature entityObj;
/*     */   private VillageDoorInfo doorInfo;
/*   7 */   private int insidePosX = -1;
/*   8 */   private int insidePosZ = -1;
/*     */   
/*     */   private float movement_speed;
/*     */ 
/*     */   
/*     */   public EntityAIMoveIndoors(EntityCreature par1EntityCreature, float movement_speed) {
/*  14 */     this.entityObj = par1EntityCreature;
/*  15 */     setMutexBits(1);
/*     */     
/*  17 */     this.movement_speed = movement_speed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  25 */     if ((!this.entityObj.worldObj.isDaytime() || this.entityObj.worldObj.isPrecipitating(true)) && !this.entityObj.worldObj.provider.hasNoSky) {
/*     */       
/*  27 */       if (this.entityObj.getRNG().nextInt(50) != 0)
/*     */       {
/*  29 */         return false;
/*     */       }
/*  31 */       if (this.insidePosX != -1 && this.entityObj.getDistanceSq(this.insidePosX, this.entityObj.posY, this.insidePosZ) < 4.0D)
/*     */       {
/*  33 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  37 */       Village var1 = this.entityObj.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.entityObj.posX), MathHelper.floor_double(this.entityObj.posY), MathHelper.floor_double(this.entityObj.posZ), 14);
/*     */       
/*  39 */       if (var1 == null)
/*     */       {
/*  41 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  45 */       this.doorInfo = var1.findNearestDoorUnrestricted(MathHelper.floor_double(this.entityObj.posX), MathHelper.floor_double(this.entityObj.posY), MathHelper.floor_double(this.entityObj.posZ));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  50 */       if (this.doorInfo == null) {
/*  51 */         return false;
/*     */       }
/*  53 */       PathEntity path = this.entityObj.getNavigator().getPathToXYZ(this.doorInfo.getInsidePosX(), this.doorInfo.getInsidePosY(), this.doorInfo.getInsidePosZ(), 16);
/*     */       
/*  55 */       if (path == null) {
/*  56 */         return false;
/*     */       }
/*  58 */       PathPoint final_point = path.getFinalPathPoint();
/*     */       
/*  60 */       double distance = World.getDistanceSqFromDeltas((this.doorInfo.getInsidePosX() - final_point.xCoord), (this.doorInfo.getInsidePosY() - final_point.yCoord), (this.doorInfo.getInsidePosZ() - final_point.zCoord));
/*     */       
/*  62 */       return (distance < 2.0D);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  79 */     return !this.entityObj.getNavigator().noPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  87 */     this.insidePosX = -1;
/*     */     
/*  89 */     if (this.entityObj.getDistanceSq(this.doorInfo.getInsidePosX(), this.doorInfo.posY, this.doorInfo.getInsidePosZ()) > 256.0D) {
/*     */       
/*  91 */       Vec3 var1 = RandomPositionGenerator.findRandomTargetBlockTowards(this.entityObj, 14, 3, this.entityObj.worldObj.getWorldVec3Pool().getVecFromPool(this.doorInfo.getInsidePosX() + 0.5D, this.doorInfo.getInsidePosY(), this.doorInfo.getInsidePosZ() + 0.5D));
/*     */       
/*  93 */       if (var1 != null)
/*     */       {
/*     */         
/*  96 */         this.entityObj.getNavigator().tryMoveToXYZ(var1.xCoord, var1.yCoord, var1.zCoord, this.movement_speed);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 102 */       this.entityObj.getNavigator().tryMoveToXYZ(this.doorInfo.getInsidePosX() + 0.5D, this.doorInfo.getInsidePosY(), this.doorInfo.getInsidePosZ() + 0.5D, this.movement_speed);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 111 */     this.insidePosX = this.doorInfo.getInsidePosX();
/* 112 */     this.insidePosZ = this.doorInfo.getInsidePosZ();
/* 113 */     this.doorInfo = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIMoveIndoors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */