/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityAIFollowOwner
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityTameable thePet;
/*     */   private EntityLivingBase theOwner;
/*     */   World theWorld;
/*     */   private double field_75336_f;
/*     */   private PathNavigate petPathfinder;
/*     */   private int field_75343_h;
/*     */   float maxDist;
/*     */   float minDist;
/*     */   private boolean field_75344_i;
/*     */   
/*     */   public EntityAIFollowOwner(EntityTameable par1EntityTameable, double par2, float par4, float par5) {
/*  17 */     this.thePet = par1EntityTameable;
/*  18 */     this.theWorld = par1EntityTameable.worldObj;
/*  19 */     this.field_75336_f = par2;
/*  20 */     this.petPathfinder = par1EntityTameable.getNavigator();
/*  21 */     this.minDist = par4;
/*  22 */     this.maxDist = par5;
/*  23 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  31 */     EntityLivingBase var1 = this.thePet.func_130012_q();
/*     */     
/*  33 */     if (var1 == null)
/*     */     {
/*  35 */       return false;
/*     */     }
/*  37 */     if (this.thePet.isSitting())
/*     */     {
/*  39 */       return false;
/*     */     }
/*  41 */     if (this.thePet.getDistanceSqToEntity(var1) < (this.minDist * this.minDist))
/*     */     {
/*  43 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  47 */     this.theOwner = var1;
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  57 */     return (!this.petPathfinder.noPath() && this.thePet.getDistanceSqToEntity(this.theOwner) > (this.maxDist * this.maxDist) && !this.thePet.isSitting());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  65 */     this.field_75343_h = 0;
/*  66 */     this.field_75344_i = this.thePet.getNavigator().getAvoidsWater();
/*  67 */     this.thePet.getNavigator().setAvoidsWater(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  75 */     this.theOwner = null;
/*  76 */     this.petPathfinder.clearPathEntity();
/*  77 */     this.thePet.getNavigator().setAvoidsWater(this.field_75344_i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  85 */     this.thePet.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, this.thePet.getVerticalFaceSpeed());
/*     */     
/*  87 */     if (!this.thePet.isSitting())
/*     */     {
/*  89 */       if (--this.field_75343_h <= 0) {
/*     */         
/*  91 */         this.field_75343_h = 10;
/*     */         
/*  93 */         if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.field_75336_f))
/*     */         {
/*  95 */           if (!this.thePet.getLeashed())
/*     */           {
/*  97 */             if (this.thePet.getDistanceSqToEntity(this.theOwner) >= 144.0D)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 116 */               if (this.thePet.getTicksExistedWithOffset() % 10 == 0 && Math.random() < 0.20000000298023224D)
/* 117 */                 this.thePet.callToOwner(); 
/*     */             }
/*     */           }
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIFollowOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */