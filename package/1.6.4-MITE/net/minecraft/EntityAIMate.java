/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIMate
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityAnimal theAnimal;
/*     */   World theWorld;
/*     */   private EntityAnimal targetMate;
/*     */   int spawnBabyDelay;
/*     */   double moveSpeed;
/*     */   
/*     */   public EntityAIMate(EntityAnimal par1EntityAnimal, double par2) {
/*  23 */     this.theAnimal = par1EntityAnimal;
/*  24 */     this.theWorld = par1EntityAnimal.worldObj;
/*  25 */     this.moveSpeed = par2;
/*  26 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  34 */     if (!this.theAnimal.isInLove())
/*     */     {
/*  36 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  40 */     this.targetMate = getNearbyMate();
/*  41 */     return (this.targetMate != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  50 */     return (this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  58 */     this.targetMate = null;
/*  59 */     this.spawnBabyDelay = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  67 */     this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, this.theAnimal.getVerticalFaceSpeed());
/*  68 */     this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
/*  69 */     this.spawnBabyDelay++;
/*     */     
/*  71 */     if (this.spawnBabyDelay >= 60 && this.theAnimal.getDistanceSqToEntity(this.targetMate) < 9.0D)
/*     */     {
/*  73 */       spawnBaby();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityAnimal getNearbyMate() {
/*  83 */     float var1 = 8.0F;
/*  84 */     List var2 = this.theWorld.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.boundingBox.expand(var1, var1, var1));
/*  85 */     double var3 = Double.MAX_VALUE;
/*  86 */     EntityAnimal var5 = null;
/*  87 */     Iterator<EntityAnimal> var6 = var2.iterator();
/*     */     
/*  89 */     while (var6.hasNext()) {
/*     */       
/*  91 */       EntityAnimal var7 = var6.next();
/*     */       
/*  93 */       if (this.theAnimal.canMateWith(var7) && this.theAnimal.getDistanceSqToEntity(var7) < var3) {
/*     */         
/*  95 */         var5 = var7;
/*  96 */         var3 = this.theAnimal.getDistanceSqToEntity(var7);
/*     */       } 
/*     */     } 
/*     */     
/* 100 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void spawnBaby() {
/* 108 */     EntityAgeable var1 = this.theAnimal.createChild(this.targetMate);
/*     */     
/* 110 */     if (var1 != null) {
/*     */ 
/*     */ 
/*     */       
/* 114 */       this.theAnimal.setGrowingAgeAfterBreeding();
/* 115 */       this.targetMate.setGrowingAgeAfterBreeding();
/* 116 */       this.theAnimal.resetInLove();
/* 117 */       this.targetMate.resetInLove();
/*     */       
/* 119 */       var1.setGrowingAgeToNewborn();
/* 120 */       var1.setLocationAndAngles(this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, 0.0F, 0.0F);
/*     */       
/* 122 */       if (!this.theWorld.isRemote && var1 instanceof EntityLivestock) {
/* 123 */         ((EntityLivestock)var1).adoptWellnessFromParents(this.theAnimal, this.targetMate);
/*     */       }
/* 125 */       this.theWorld.spawnEntityInWorld(var1);
/* 126 */       Random var2 = this.theAnimal.getRNG();
/*     */       
/* 128 */       for (int var3 = 0; var3 < 7; var3++) {
/*     */         
/* 130 */         double var4 = var2.nextGaussian() * 0.02D;
/* 131 */         double var6 = var2.nextGaussian() * 0.02D;
/* 132 */         double var8 = var2.nextGaussian() * 0.02D;
/*     */         
/* 134 */         this.theWorld.spawnParticle(EnumParticle.heart, this.theAnimal.posX + (var2.nextFloat() * this.theAnimal.width * 2.0F) - this.theAnimal.width, this.theAnimal.posY + 0.5D + (var2.nextFloat() * this.theAnimal.height), this.theAnimal.posZ + (var2.nextFloat() * this.theAnimal.width * 2.0F) - this.theAnimal.width, var4, var6, var8);
/*     */       } 
/*     */       
/* 137 */       this.theWorld.spawnEntityInWorld(new EntityXPOrb(this.theWorld, this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, var2.nextInt(7) + 1));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIMate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */