/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class EntityAIAvoidEntity
/*     */   extends EntityAIBase {
/*   7 */   public final IEntitySelector field_98218_a = new EntityAIAvoidEntitySelector(this);
/*     */   
/*     */   private EntityCreature theEntity;
/*     */   
/*     */   private double farSpeed;
/*     */   
/*     */   private double nearSpeed;
/*     */   
/*     */   private Entity closestLivingEntity;
/*     */   
/*     */   private float distanceFromEntity;
/*     */   
/*     */   private PathEntity entityPathEntity;
/*     */   
/*     */   private PathNavigate entityPathNavigate;
/*     */   
/*     */   private Class targetEntityClass;
/*     */ 
/*     */   
/*     */   public EntityAIAvoidEntity(EntityCreature par1EntityCreature, Class par2Class, float par3, double par4, double par6) {
/*  27 */     this.theEntity = par1EntityCreature;
/*  28 */     this.targetEntityClass = par2Class;
/*  29 */     this.distanceFromEntity = par3;
/*  30 */     this.farSpeed = par4;
/*  31 */     this.nearSpeed = par6;
/*  32 */     this.entityPathNavigate = par1EntityCreature.getNavigator();
/*  33 */     setMutexBits(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  41 */     if (this.targetEntityClass == EntityPlayer.class) {
/*     */       
/*  43 */       if (this.theEntity instanceof EntityTameable && ((EntityTameable)this.theEntity).isTamed())
/*     */       {
/*  45 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  49 */       this.closestLivingEntity = this.theEntity.worldObj.getClosestPlayerToEntity(this.theEntity, this.distanceFromEntity, true);
/*     */       
/*  51 */       if (this.closestLivingEntity == null)
/*     */       {
/*  53 */         return false;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  58 */       List<Entity> var1 = this.theEntity.worldObj.selectEntitiesWithinAABB(this.targetEntityClass, this.theEntity.boundingBox.expand(this.distanceFromEntity, 3.0D, this.distanceFromEntity), this.field_98218_a);
/*     */       
/*  60 */       if (var1.isEmpty())
/*     */       {
/*  62 */         return false;
/*     */       }
/*     */       
/*  65 */       this.closestLivingEntity = var1.get(0);
/*     */     } 
/*     */     
/*  68 */     Vec3 var2 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, this.theEntity.worldObj.getWorldVec3Pool().getVecFromPool(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
/*     */     
/*  70 */     if (var2 == null)
/*     */     {
/*  72 */       return false;
/*     */     }
/*  74 */     if (this.closestLivingEntity.getDistanceSq(var2.xCoord, var2.yCoord, var2.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity))
/*     */     {
/*  76 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  80 */     this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(var2.xCoord, var2.yCoord, var2.zCoord);
/*  81 */     return (this.entityPathEntity == null) ? false : this.entityPathEntity.isDestinationSame(var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  90 */     return !this.entityPathNavigate.noPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  98 */     this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 106 */     this.closestLivingEntity = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 114 */     if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D) {
/*     */       
/* 116 */       this.theEntity.getNavigator().setSpeed(this.nearSpeed);
/*     */     }
/*     */     else {
/*     */       
/* 120 */       this.theEntity.getNavigator().setSpeed(this.farSpeed);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static EntityCreature func_98217_a(EntityAIAvoidEntity par0EntityAIAvoidEntity) {
/* 126 */     return par0EntityAIAvoidEntity.theEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIAvoidEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */