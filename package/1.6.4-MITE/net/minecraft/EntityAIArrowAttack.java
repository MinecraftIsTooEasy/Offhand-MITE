/*     */ package net.minecraft;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIArrowAttack
/*     */   extends EntityAIBase
/*     */ {
/*     */   private final EntityLiving entityHost;
/*     */   private final IRangedAttackMob rangedAttackEntityHost;
/*     */   private EntityLivingBase attackTarget;
/*     */   
/*     */   public EntityAIArrowAttack(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, float par5) {
/*  32 */     this(par1IRangedAttackMob, par2, par4, par4, par5);
/*     */   }
/*     */ 
/*     */   
/*     */   private double entityMoveSpeed;
/*  37 */   private int rangedAttackTime = -1;
/*     */   public EntityAIArrowAttack(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, int par5, float par6) {
/*  39 */     if (!(par1IRangedAttackMob instanceof EntityLivingBase))
/*     */     {
/*  41 */       throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
/*     */     }
/*     */ 
/*     */     
/*  45 */     this.rangedAttackEntityHost = par1IRangedAttackMob;
/*  46 */     this.entityHost = (EntityLiving)par1IRangedAttackMob;
/*  47 */     this.entityMoveSpeed = par2;
/*  48 */     this.field_96561_g = par4;
/*  49 */     this.maxRangedAttackTime = par5;
/*  50 */     this.field_96562_i = par6;
/*  51 */     this.field_82642_h = par6 * par6;
/*  52 */     this.field_82642_h *= 4.0F;
/*  53 */     setMutexBits(3);
/*     */   }
/*     */   private int field_75318_f;
/*     */   private int field_96561_g;
/*     */   private int maxRangedAttackTime;
/*     */   private float field_96562_i;
/*     */   private float field_82642_h;
/*     */   
/*     */   public boolean shouldExecute() {
/*  62 */     EntityLivingBase var1 = this.entityHost.getAttackTarget();
/*     */     
/*  64 */     if (var1 == null)
/*     */     {
/*  66 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  70 */     this.attackTarget = var1;
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  80 */     return (shouldExecute() || !this.entityHost.getNavigator().noPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  88 */     this.attackTarget = null;
/*  89 */     this.field_75318_f = 0;
/*  90 */     this.rangedAttackTime = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  98 */     double var1 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     World world = this.entityHost.worldObj;
/*     */ 
/*     */     
/* 106 */     Raycast raycast = (new Raycast(world, this.entityHost.getEyePos(), this.attackTarget.getEyePos())).setForPiercingProjectile(null).performVsBlocks();
/*     */     
/* 108 */     if (raycast.hasBlockCollision()) {
/*     */       
/* 110 */       raycast.setLimit(this.attackTarget.getFootPosPlusFractionOfHeight(0.25F));
/* 111 */       raycast.performVsBlocks();
/*     */     } 
/*     */     
/* 114 */     RaycastCollision rc = raycast.getBlockCollision();
/*     */     
/* 116 */     boolean var3 = (rc == null);
/*     */ 
/*     */ 
/*     */     
/* 120 */     if (var3) {
/*     */       
/* 122 */       this.field_75318_f++;
/*     */     }
/*     */     else {
/*     */       
/* 126 */       this.field_75318_f = 0;
/*     */     } 
/*     */     
/* 129 */     if (var1 <= this.field_82642_h && this.field_75318_f >= 20) {
/*     */       
/* 131 */       this.entityHost.getNavigator().clearPathEntity();
/*     */     }
/*     */     else {
/*     */       
/* 135 */       this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
/*     */     } 
/*     */     
/* 138 */     this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
/*     */ 
/*     */     
/* 141 */     if (--this.rangedAttackTime == 0) {
/*     */       
/* 143 */       if (var1 > this.field_82642_h || !var3) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 148 */       float var4 = MathHelper.sqrt_double(var1) / this.field_96562_i;
/* 149 */       float var5 = var4;
/*     */       
/* 151 */       if (var4 < 0.1F)
/*     */       {
/* 153 */         var5 = 0.1F;
/*     */       }
/*     */       
/* 156 */       if (var5 > 1.0F)
/*     */       {
/* 158 */         var5 = 1.0F;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 163 */       if (this.entityHost.worldObj.isAirOrPassableBlock(this.entityHost.getBlockPosX(), this.entityHost.getEyeBlockPosY(), this.entityHost.getBlockPosZ(), true)) {
/* 164 */         this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, var5);
/*     */       }
/*     */       
/* 167 */       this.rangedAttackTime = this.maxRangedAttackTime;
/*     */       
/* 169 */       if (this.entityHost.isFrenzied()) {
/* 170 */         this.rangedAttackTime = (int)(this.rangedAttackTime * 0.67F);
/*     */       }
/* 172 */     } else if (this.rangedAttackTime < 0) {
/*     */ 
/*     */ 
/*     */       
/* 176 */       this.rangedAttackTime = this.maxRangedAttackTime;
/*     */       
/* 178 */       if (this.entityHost.isFrenzied())
/* 179 */         this.rangedAttackTime = (int)(this.rangedAttackTime * 0.67F); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIArrowAttack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */