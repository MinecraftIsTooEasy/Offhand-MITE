/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang3.StringUtils;
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
/*     */ public abstract class EntityAITarget
/*     */   extends EntityAIBase
/*     */ {
/*     */   protected EntityCreature taskOwner;
/*     */   protected boolean shouldCheckSight;
/*     */   private boolean nearbyOnly;
/*     */   private int targetSearchStatus;
/*     */   private int targetSearchDelay;
/*     */   private int field_75298_g;
/*     */   private EntityLivingBase previous_target;
/*     */   
/*     */   public EntityAITarget(EntityCreature par1EntityCreature, boolean par2) {
/*  36 */     this(par1EntityCreature, par2, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAITarget(EntityCreature par1EntityCreature, boolean par2, boolean par3) {
/*  41 */     this.taskOwner = par1EntityCreature;
/*  42 */     this.shouldCheckSight = par2;
/*  43 */     this.nearbyOnly = par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  51 */     EntityLivingBase var1 = this.taskOwner.getAttackTarget();
/*     */     
/*  53 */     if (var1 == null)
/*     */     {
/*  55 */       return false;
/*     */     }
/*  57 */     if (!var1.isEntityAlive())
/*     */     {
/*  59 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  64 */     double var2 = this.taskOwner.getMaxTargettingRange();
/*     */     
/*  66 */     if (this.taskOwner.getDistanceSqToEntity(var1) > var2 * var2)
/*     */     {
/*  68 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  73 */     if (shouldCheckSight(var1))
/*     */     {
/*  75 */       if (this.taskOwner.getEntitySenses().canSee(var1)) {
/*     */         
/*  77 */         this.field_75298_g = 0;
/*     */       }
/*  79 */       else if (++this.field_75298_g > 60) {
/*     */         
/*  81 */         return false;
/*     */       } 
/*     */     }
/*     */     
/*  85 */     return true;
/*     */   }
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
/*     */   public void startExecuting() {
/* 101 */     this.targetSearchStatus = 0;
/* 102 */     this.targetSearchDelay = 0;
/* 103 */     this.field_75298_g = 0;
/*     */ 
/*     */     
/* 106 */     this.taskOwner.refreshDespawnCounter(-1200);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 114 */     this.previous_target = this.taskOwner.getAttackTarget();
/* 115 */     this.taskOwner.setAttackTarget((EntityLivingBase)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSuitableTarget(EntityLivingBase par1EntityLivingBase, boolean par2) {
/* 123 */     if (par1EntityLivingBase == null)
/*     */     {
/* 125 */       return false;
/*     */     }
/* 127 */     if (par1EntityLivingBase == this.taskOwner)
/*     */     {
/* 129 */       return false;
/*     */     }
/* 131 */     if (!par1EntityLivingBase.isEntityAlive())
/*     */     {
/* 133 */       return false;
/*     */     }
/* 135 */     if (!this.taskOwner.canAttackClass(par1EntityLivingBase.getClass()))
/*     */     {
/* 137 */       return false;
/*     */     }
/* 139 */     if (par1EntityLivingBase.isZevimrgvInTournament())
/*     */     {
/* 141 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 145 */     if (this.taskOwner instanceof EntityOwnable && StringUtils.isNotEmpty(((EntityOwnable)this.taskOwner).getOwnerName())) {
/*     */       
/* 147 */       if (par1EntityLivingBase instanceof EntityOwnable && ((EntityOwnable)this.taskOwner).getOwnerName().equals(((EntityOwnable)par1EntityLivingBase).getOwnerName()))
/*     */       {
/* 149 */         return false;
/*     */       }
/*     */       
/* 152 */       if (par1EntityLivingBase == ((EntityOwnable)this.taskOwner).getOwner())
/*     */       {
/* 154 */         return false;
/*     */       }
/*     */     }
/* 157 */     else if (par1EntityLivingBase instanceof EntityPlayer && !par2 && ((EntityPlayer)par1EntityLivingBase).capabilities.disableDamage) {
/*     */       
/* 159 */       return false;
/*     */     } 
/*     */     
/* 162 */     if (!this.taskOwner.func_110176_b(MathHelper.floor_double(par1EntityLivingBase.posX), MathHelper.floor_double(par1EntityLivingBase.posY), MathHelper.floor_double(par1EntityLivingBase.posZ)))
/*     */     {
/* 164 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 168 */     if (shouldCheckSight(par1EntityLivingBase) && !this.taskOwner.getEntitySenses().canSee(par1EntityLivingBase, (this.previous_target == par1EntityLivingBase)))
/*     */     {
/* 170 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 174 */     if (this.taskOwner instanceof EntityWitch && par1EntityLivingBase instanceof ServerPlayer && this.taskOwner.rand.nextInt(4) == 0) {
/* 175 */       ((EntityWitch)this.taskOwner).cursePlayer((ServerPlayer)par1EntityLivingBase);
/*     */     }
/* 177 */     if (this.nearbyOnly) {
/*     */       
/* 179 */       if (--this.targetSearchDelay <= 0)
/*     */       {
/* 181 */         this.targetSearchStatus = 0;
/*     */       }
/*     */       
/* 184 */       if (this.targetSearchStatus == 0)
/*     */       {
/* 186 */         this.targetSearchStatus = canEasilyReach(par1EntityLivingBase) ? 1 : 2;
/*     */       }
/*     */       
/* 189 */       if (this.targetSearchStatus == 2)
/*     */       {
/* 191 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canEasilyReach(EntityLivingBase par1EntityLivingBase) {
/* 205 */     this.targetSearchDelay = 10 + this.taskOwner.getRNG().nextInt(5);
/* 206 */     PathEntity var2 = this.taskOwner.getNavigator().getPathToEntityLiving(par1EntityLivingBase);
/*     */     
/* 208 */     if (var2 == null)
/*     */     {
/* 210 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 214 */     PathPoint var3 = var2.getFinalPathPoint();
/*     */     
/* 216 */     if (var3 == null)
/*     */     {
/* 218 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 222 */     int var4 = var3.xCoord - MathHelper.floor_double(par1EntityLivingBase.posX);
/* 223 */     int var5 = var3.zCoord - MathHelper.floor_double(par1EntityLivingBase.posZ);
/* 224 */     return ((var4 * var4 + var5 * var5) <= 2.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean shouldCheckSight(Entity potential_target) {
/* 231 */     if (!this.shouldCheckSight) {
/* 232 */       return false;
/*     */     }
/* 234 */     if (this.taskOwner instanceof EntityAnimalWatcher) {
/*     */       
/* 236 */       EntityAnimalWatcher entity_digger = (EntityAnimalWatcher)this.taskOwner;
/*     */       
/* 238 */       if (entity_digger.isDiggingEnabled()) {
/* 239 */         return false;
/*     */       }
/*     */     } 
/* 242 */     if (this.taskOwner instanceof EntityWitch) {
/*     */       
/* 244 */       if (potential_target.seen_by_bat_countdown > 0) {
/* 245 */         return false;
/*     */       }
/* 247 */       List nearby_bats = this.taskOwner.worldObj.getEntitiesWithinAABB(EntityBat.class, this.taskOwner.boundingBox.expand(32.0D, 32.0D, 32.0D));
/*     */       
/* 249 */       Iterator<EntityBat> i = nearby_bats.iterator();
/*     */       
/* 251 */       while (i.hasNext()) {
/*     */         
/* 253 */         if (((EntityBat)i.next()).getEntitySenses().canSee(potential_target)) {
/*     */           
/* 255 */           potential_target.seen_by_bat_countdown = 20;
/* 256 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 261 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAITarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */