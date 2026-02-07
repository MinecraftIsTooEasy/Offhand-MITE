/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIWander
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature entity;
/*    */   private double xPosition;
/*    */   private double yPosition;
/*    */   private double zPosition;
/*    */   private double speed;
/*    */   
/*    */   public EntityAIWander(EntityCreature par1EntityCreature, double par2) {
/* 15 */     double limit = 0.3D;
/* 16 */     double movement_speed = par1EntityCreature.getEntityAttributeValue(SharedMonsterAttributes.movementSpeed);
/* 17 */     double multiplied_speed = par2 * movement_speed;
/*    */     
/* 19 */     if (multiplied_speed > limit) {
/* 20 */       par2 = limit / movement_speed;
/*    */     }
/*    */ 
/*    */     
/* 24 */     this.entity = par1EntityCreature;
/* 25 */     this.speed = par2;
/* 26 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 34 */     long ticks_since_harmed_by_cactus = (this.entity.last_tick_harmed_by_cactus < 1L) ? -1L : (this.entity.worldObj.getTotalWorldTime() - this.entity.last_tick_harmed_by_cactus);
/*    */     
/* 36 */     boolean prompted_by_cactus = (ticks_since_harmed_by_cactus >= 0L && ticks_since_harmed_by_cactus < 10L && this.entity.getNavigator().noPath());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     if (!prompted_by_cactus && this.entity.getRNG().nextInt(120) != 0)
/*    */     {
/* 44 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 48 */     Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
/*    */     
/* 50 */     if (var1 == null)
/*    */     {
/* 52 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 56 */     if (this.entity instanceof EntityVillager) {
/*    */       
/* 58 */       int x = MathHelper.floor_double(var1.xCoord);
/* 59 */       int y = MathHelper.floor_double(var1.yCoord);
/* 60 */       int z = MathHelper.floor_double(var1.zCoord);
/*    */       
/* 62 */       PathEntity path = this.entity.getNavigator().getPathToXYZ(x, y, z, 16);
/*    */       
/* 64 */       if (path == null) {
/* 65 */         return false;
/*    */       }
/* 67 */       PathPoint final_point = path.getFinalPathPoint();
/*    */       
/* 69 */       if (this.entity.worldObj.isInRain(final_point.xCoord, final_point.yCoord + 1, final_point.zCoord))
/*    */       {
/*    */         
/* 72 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 76 */     this.xPosition = var1.xCoord;
/* 77 */     this.yPosition = var1.yCoord;
/* 78 */     this.zPosition = var1.zCoord;
/* 79 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 89 */     return !this.entity.getNavigator().noPath();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 97 */     this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIWander.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */