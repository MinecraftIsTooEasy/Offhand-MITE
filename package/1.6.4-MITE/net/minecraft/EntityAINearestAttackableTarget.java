/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAINearestAttackableTarget
/*     */   extends EntityAITarget
/*     */ {
/*     */   private final Class targetClass;
/*     */   private final int targetChance;
/*     */   private final EntityAINearestAttackableTargetSorter theNearestAttackableTargetSorter;
/*     */   private final IEntitySelector targetEntitySelector;
/*     */   private EntityLivingBase targetEntity;
/*     */   
/*     */   public EntityAINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4) {
/*  25 */     this(par1EntityCreature, par2Class, par3, par4, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4, boolean par5) {
/*  30 */     this(par1EntityCreature, par2Class, par3, par4, par5, (IEntitySelector)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4, boolean par5, IEntitySelector par6IEntitySelector) {
/*  35 */     super(par1EntityCreature, par4, par5);
/*  36 */     this.targetClass = par2Class;
/*  37 */     this.targetChance = par3;
/*  38 */     this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTargetSorter(par1EntityCreature);
/*  39 */     setMutexBits(1);
/*  40 */     this.targetEntitySelector = new EntityAINearestAttackableTargetSelector(this, par6IEntitySelector);
/*     */   }
/*     */ 
/*     */   
/*     */   private EntityLivingBase getNearestAttackableTarget() {
/*  45 */     double var1 = this.taskOwner.getMaxTargettingRange();
/*  46 */     float y_range = 6.0F;
/*     */     
/*  48 */     if (this.taskOwner instanceof EntityZombie && this.targetClass == EntityAnimal.class) {
/*     */       
/*  50 */       var1 /= 2.0D;
/*  51 */       y_range /= 2.0F;
/*     */     } 
/*     */     
/*  54 */     List<?> var3 = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(var1, y_range, var1), this.targetEntitySelector);
/*  55 */     Collections.sort(var3, this.theNearestAttackableTargetSorter);
/*     */     
/*  57 */     if (var3.isEmpty() && this.targetClass == EntityPlayer.class) {
/*     */       
/*  59 */       WorldServer world_server = (WorldServer)this.taskOwner.worldObj;
/*     */       
/*  61 */       if (this.taskOwner instanceof EntityAnimalWatcher) {
/*     */         
/*  63 */         EntityAnimalWatcher entity_digger = (EntityAnimalWatcher)this.taskOwner;
/*     */         
/*  65 */         if (entity_digger.isDiggingEnabled() && entity_digger.isOutdoors())
/*     */         {
/*  67 */           Iterator<EntityPlayer> iterator = world_server.playerEntities.iterator();
/*     */           
/*  69 */           while (iterator.hasNext()) {
/*     */             
/*  71 */             EntityPlayer entity_player = iterator.next();
/*     */             
/*  73 */             if (!entity_player.isGhost() && !entity_player.isZevimrgvInTournament()) {
/*     */               
/*  75 */               double dx = entity_player.posX - this.taskOwner.posX;
/*  76 */               double dy = entity_player.posY - this.taskOwner.posY;
/*  77 */               double dz = entity_player.posZ - this.taskOwner.posZ;
/*     */               
/*  79 */               if (!entity_player.capabilities.isCreativeMode && dy >= y_range && dy <= 14.0D && dx * dx + dz * dz < 16.0D) {
/*  80 */                 var3.add(entity_player);
/*     */               }
/*     */             } 
/*     */           } 
/*  84 */           Collections.sort(var3, this.theNearestAttackableTargetSorter);
/*     */         }
/*     */       
/*  87 */       } else if (this.taskOwner instanceof EntitySkeleton) {
/*     */         
/*  89 */         if (this.taskOwner.getHeldItemStack() != null && this.taskOwner.getHeldItemStack().getItem() instanceof ItemBow) {
/*     */           
/*  91 */           Iterator<EntityPlayer> iterator = world_server.playerEntities.iterator();
/*     */           
/*  93 */           while (iterator.hasNext()) {
/*     */             
/*  95 */             EntityPlayer entity_player = iterator.next();
/*     */             
/*  97 */             if (!entity_player.isGhost() && !entity_player.isZevimrgvInTournament()) {
/*     */               
/*  99 */               double dx = entity_player.posX - this.taskOwner.posX;
/* 100 */               double dy = entity_player.posY - this.taskOwner.posY;
/* 101 */               double dz = entity_player.posZ - this.taskOwner.posZ;
/*     */               
/* 103 */               if (!entity_player.capabilities.isCreativeMode && dy >= y_range && dy <= 20.0D && dx * dx + dz * dz < var1 * var1 && this.taskOwner.getEntitySenses().canSee(entity_player, (entity_player == this.taskOwner.getAttackTarget()))) {
/* 104 */                 var3.add(entity_player);
/*     */               }
/*     */             } 
/*     */           } 
/* 108 */           Collections.sort(var3, this.theNearestAttackableTargetSorter);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     if (var3.isEmpty())
/*     */     {
/* 115 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 119 */     int size = var3.size();
/*     */     
/* 121 */     for (int i = 0; i < size; i++) {
/*     */       
/* 123 */       EntityLivingBase potential_target = (EntityLivingBase)var3.get(i);
/*     */       
/* 125 */       if (potential_target instanceof EntityPlayer) {
/*     */         
/* 127 */         if (((EntityPlayer)potential_target).isGhost() || potential_target.isZevimrgvInTournament())
/*     */           continue; 
/*     */       } else {
/* 130 */         if (potential_target instanceof EntityHellhound && this.targetClass == EntityAnimal.class) {
/*     */           continue;
/*     */         }
/*     */         
/* 134 */         if (potential_target instanceof EntityWolf && this.taskOwner instanceof EntityZombie)
/*     */         {
/* 136 */           if (this.taskOwner.getDistanceToEntity(potential_target) > 8.0F)
/*     */             continue; 
/*     */         }
/*     */       } 
/* 140 */       if (isOnAttackableMount(potential_target)) {
/* 141 */         return potential_target.ridingEntity.getAsEntityLivingBase();
/*     */       }
/* 143 */       return potential_target;
/*     */     } 
/*     */     
/* 146 */     return null;
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
/*     */   public boolean shouldExecute() {
/* 272 */     if (this.taskOwner.AI_retarget != null)
/*     */     {
/* 274 */       if (this.taskOwner.AI_retarget.isDead) {
/*     */         
/* 276 */         this.taskOwner.AI_retarget = null;
/*     */       }
/*     */       else {
/*     */         
/* 280 */         this.targetEntity = this.taskOwner.AI_retarget;
/*     */         
/* 282 */         this.taskOwner.AI_retarget = null;
/* 283 */         return true;
/*     */       } 
/*     */     }
/*     */     
/* 287 */     if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
/* 288 */       return false;
/*     */     }
/* 290 */     this.targetEntity = getNearestAttackableTarget();
/*     */     
/* 292 */     return (this.targetEntity != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 299 */     if (this.taskOwner.rand.nextInt(40) == 0) {
/*     */       
/* 301 */       EntityLivingBase nearest_attackable_target = getNearestAttackableTarget();
/*     */       
/* 303 */       if (nearest_attackable_target != null && nearest_attackable_target != this.targetEntity && this.taskOwner.getEntitySenses().canSee(nearest_attackable_target)) {
/*     */         
/* 305 */         resetTask();
/* 306 */         this.targetEntity = nearest_attackable_target;
/* 307 */         startExecuting();
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 313 */     if (isOnAttackableMount(this.targetEntity)) {
/* 314 */       this.targetEntity = this.targetEntity.ridingEntity.getAsEntityLivingBase();
/*     */     }
/* 316 */     super.updateTask();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 324 */     if (isOnAttackableMount(this.targetEntity)) {
/* 325 */       this.targetEntity = this.targetEntity.ridingEntity.getAsEntityLivingBase();
/*     */     }
/* 327 */     this.taskOwner.setAttackTarget(this.targetEntity);
/* 328 */     super.startExecuting();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isOnAttackableMount(EntityLivingBase entity_living_base) {
/* 333 */     return (entity_living_base != null && entity_living_base.ridingEntity instanceof EntityLivingBase && !entity_living_base.ridingEntity.isEntityInvulnerable());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAINearestAttackableTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */