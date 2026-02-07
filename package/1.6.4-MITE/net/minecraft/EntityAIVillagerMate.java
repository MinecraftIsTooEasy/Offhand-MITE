/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityAIVillagerMate
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityVillager villagerObj;
/*     */   private EntityVillager mate;
/*     */   private World worldObj;
/*     */   private int matingTimeout;
/*     */   Village villageObj;
/*     */   
/*     */   public EntityAIVillagerMate(EntityVillager par1EntityVillager) {
/*  13 */     this.villagerObj = par1EntityVillager;
/*  14 */     this.worldObj = par1EntityVillager.worldObj;
/*  15 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  23 */     if (this.villagerObj.getGrowingAge() != 0)
/*     */     {
/*  25 */       return false;
/*     */     }
/*  27 */     if (this.villagerObj.getRNG().nextInt(500) != 0)
/*     */     {
/*  29 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  33 */     this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.villagerObj.posX), MathHelper.floor_double(this.villagerObj.posY), MathHelper.floor_double(this.villagerObj.posZ), 0);
/*     */     
/*  35 */     if (this.villageObj == null)
/*     */     {
/*  37 */       return false;
/*     */     }
/*  39 */     if (!checkSufficientDoorsPresentForNewVillager())
/*     */     {
/*  41 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  45 */     Entity var1 = this.worldObj.findNearestEntityWithinAABB(EntityVillager.class, this.villagerObj.boundingBox.expand(8.0D, 3.0D, 8.0D), this.villagerObj);
/*     */     
/*  47 */     if (var1 == null)
/*     */     {
/*  49 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  53 */     this.mate = (EntityVillager)var1;
/*  54 */     return (this.mate.getGrowingAge() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  65 */     this.matingTimeout = 300;
/*  66 */     this.villagerObj.setMating(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  74 */     this.villageObj = null;
/*  75 */     this.mate = null;
/*  76 */     this.villagerObj.setMating(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  84 */     return (this.matingTimeout >= 0 && checkSufficientDoorsPresentForNewVillager() && this.villagerObj.getGrowingAge() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  92 */     this.matingTimeout--;
/*  93 */     this.villagerObj.getLookHelper().setLookPositionWithEntity(this.mate, 10.0F, 30.0F);
/*     */     
/*  95 */     if (this.villagerObj.getDistanceSqToEntity(this.mate) > 2.25D) {
/*     */       
/*  97 */       this.villagerObj.getNavigator().tryMoveToEntityLiving(this.mate, 0.25D);
/*     */     }
/*  99 */     else if (this.matingTimeout == 0 && this.mate.isMating()) {
/*     */       
/* 101 */       giveBirth();
/*     */     } 
/*     */     
/* 104 */     if (this.villagerObj.getRNG().nextInt(35) == 0)
/*     */     {
/*     */       
/* 107 */       this.worldObj.setEntityState(this.villagerObj, EnumEntityState.villager_mated);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkSufficientDoorsPresentForNewVillager() {
/* 113 */     if (!this.villageObj.isMatingSeason())
/*     */     {
/* 115 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 119 */     int var1 = (int)(this.villageObj.getNumVillageDoors() * 0.35D);
/* 120 */     return (this.villageObj.getNumVillagers() < var1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void giveBirth() {
/* 126 */     EntityVillager var1 = this.villagerObj.func_90012_b(this.mate);
/*     */ 
/*     */     
/* 129 */     this.mate.setGrowingAgeAfterBreeding();
/* 130 */     this.villagerObj.setGrowingAgeAfterBreeding();
/*     */     
/* 132 */     var1.setGrowingAgeToNewborn();
/* 133 */     var1.setLocationAndAngles(this.villagerObj.posX, this.villagerObj.posY, this.villagerObj.posZ, 0.0F, 0.0F);
/* 134 */     this.worldObj.spawnEntityInWorld(var1);
/*     */     
/* 136 */     this.worldObj.setEntityState(var1, EnumEntityState.villager_mated);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIVillagerMate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */