/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAICreeperSwell
/*     */   extends EntityAIBase
/*     */ {
/*     */   EntityCreeper swellingCreeper;
/*     */   EntityLivingBase creeperAttackTarget;
/*     */   
/*     */   public EntityAICreeperSwell(EntityCreeper par1EntityCreeper) {
/*  17 */     this.swellingCreeper = par1EntityCreeper;
/*  18 */     setMutexBits(1);
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
/*     */   public boolean shouldExecute() {
/*  32 */     if (this.swellingCreeper.getCreeperState() > 0) {
/*  33 */       return true;
/*     */     }
/*  35 */     if (this.swellingCreeper.recently_took_damage_from_conspicuous_cactus > 0 && this.swellingCreeper.isNearToBlock(Block.cactus, 1, 1)) {
/*  36 */       return true;
/*     */     }
/*  38 */     EntityLivingBase target = this.swellingCreeper.getAttackTarget();
/*     */     
/*  40 */     if (target == null) {
/*  41 */       return false;
/*     */     }
/*  43 */     float trigger_distance_sq = this.swellingCreeper.getNavigator().noPath() ? 16.0F : ((this.swellingCreeper.getHealthFraction() < 1.0F) ? 9.0F : 4.5F);
/*     */     
/*  45 */     if (this.swellingCreeper instanceof EntityInfernalCreeper) {
/*  46 */       trigger_distance_sq *= 2.0F;
/*     */     }
/*  48 */     return (this.swellingCreeper.getDistanceSqToEntity(target) < trigger_distance_sq);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  56 */     this.swellingCreeper.getNavigator().clearPathEntity();
/*  57 */     this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  65 */     this.creeperAttackTarget = null;
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
/*     */   public void updateTask() {
/*  93 */     if (this.swellingCreeper.recently_took_damage_from_conspicuous_cactus > 0 && this.swellingCreeper.isNearToBlock(Block.cactus, 1, 1)) {
/*     */       
/*  95 */       this.swellingCreeper.setCreeperState(1);
/*     */       
/*     */       return;
/*     */     } 
/*  99 */     if (this.creeperAttackTarget == null) {
/*     */       
/* 101 */       this.swellingCreeper.setCreeperState(-1);
/*     */       
/*     */       return;
/*     */     } 
/* 105 */     float health_fraction_for_swelling = MathHelper.clamp_float(this.swellingCreeper.getHealthFraction(), 0.4F, 1.0F);
/* 106 */     float distance_limit_sq = ((this.swellingCreeper instanceof EntityInfernalCreeper) ? 36.0F : 16.0F) / health_fraction_for_swelling;
/*     */     
/* 108 */     World world = this.swellingCreeper.worldObj;
/* 109 */     Vec3Pool vec3_pool = world.getWorldVec3Pool();
/*     */     
/* 111 */     Iterator<ServerPlayer> i = world.playerEntities.iterator();
/*     */     
/* 113 */     while (i.hasNext()) {
/*     */       
/* 115 */       ServerPlayer player = i.next();
/*     */       
/* 117 */       if (player.getHealth() > 0.0F && this.swellingCreeper.getDistanceSqToEntity(player) <= distance_limit_sq) {
/*     */         
/* 119 */         double creeper_knee_pos_y = this.swellingCreeper.getFootPosY() + (this.swellingCreeper.height / 4.0F);
/* 120 */         double creeper_eye_pos_y = this.swellingCreeper.getEyePosY();
/*     */ 
/*     */         
/* 123 */         if (player.canEntityBeSeenFrom(this.swellingCreeper.posX, creeper_knee_pos_y, this.swellingCreeper.posZ, distance_limit_sq) || player.canEntityBeSeenFrom(this.swellingCreeper.posX, creeper_eye_pos_y, this.swellingCreeper.posZ, distance_limit_sq)) {
/*     */           
/* 125 */           this.swellingCreeper.setCreeperState(1);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 131 */     this.swellingCreeper.setCreeperState(-1);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAICreeperSwell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */