/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIWatchAnimal
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityAnimalWatcher digger;
/*     */   private static boolean player_attacks_always_reset_digging = false;
/*     */   
/*     */   public EntityAIWatchAnimal(EntityAnimalWatcher attacker) {
/*  16 */     this.digger = attacker;
/*  17 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  22 */     if (this.digger.isHoldingItemThatPreventsDigging()) {
/*  23 */       return false;
/*     */     }
/*     */     
/*  26 */     if ((!this.digger.isDiggingEnabled() && !this.digger.canSeeTarget(false)) || (this.digger.recentlyHit > 0 && player_attacks_always_reset_digging)) {
/*  27 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     EntityLivingBase target = this.digger.getAttackTarget();
/*     */     
/*  35 */     if (target == null) {
/*  36 */       return false;
/*     */     }
/*  38 */     if (this.digger.getBlockPosX() == target.getBlockPosX() && this.digger.getBlockPosY() == target.getBlockPosY() && this.digger.getBlockPosZ() == target.getBlockPosZ()) {
/*  39 */       return false;
/*     */     }
/*  41 */     if (this.digger.is_destroying_block && this.digger.canDestroyBlock(this.digger.destroy_block_x, this.digger.destroy_block_y, this.digger.destroy_block_z, true)) {
/*  42 */       return true;
/*     */     }
/*  44 */     if (!this.digger.is_destroying_block && this.digger.rand.nextInt(20) != 0) {
/*  45 */       return false;
/*     */     }
/*  47 */     World world = this.digger.worldObj;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     float distance_to_target = this.digger.getDistanceToEntity(target);
/*     */     
/*  54 */     if (distance_to_target > 16.0F) {
/*  55 */       return false;
/*     */     }
/*  57 */     int attacker_foot_y = this.digger.getFootBlockPosY();
/*     */     
/*  59 */     if (distance_to_target * distance_to_target > 2.0F) {
/*     */       
/*  61 */       int x = target.getBlockPosX();
/*  62 */       int y = target.getFootBlockPosY();
/*  63 */       int z = target.getBlockPosZ();
/*     */       
/*  65 */       while (--y >= attacker_foot_y) {
/*     */         
/*  67 */         if (this.digger.setBlockToDig(x, y, z, true)) {
/*  68 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*  72 */     if (distance_to_target > 8.0F) {
/*  73 */       return false;
/*     */     }
/*  75 */     Vec3Pool vec3_pool = world.getWorldVec3Pool();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     boolean can_attacker_see_target = (world.isAirOrPassableBlock(this.digger.getBlockPosX(), MathHelper.floor_double(this.digger.getEyePosY() + 1.0D), this.digger.getBlockPosZ(), false) && world.checkForLineOfPhysicalReach(world.getVec3(this.digger.posX, this.digger.getEyePosY() + 1.0D, this.digger.posZ), target.getEyePos()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     if (distance_to_target > (can_attacker_see_target ? 8.0F : (this.digger.isFrenzied() ? 6.0F : 4.0F)))
/*     */     {
/*     */       
/*  89 */       return false;
/*     */     }
/*     */     
/*  92 */     PathEntity path = this.digger.getNavigator().getPathToEntityLiving(target, 16);
/*     */     
/*  94 */     if (!this.digger.getNavigator().noPath()) {
/*  95 */       return false;
/*     */     }
/*  97 */     if (this.digger.hasLineOfStrikeAndTargetIsWithinStrikingDistance(target)) {
/*  98 */       return false;
/*     */     }
/* 100 */     Vec3 target_center_pos = this.digger.getTargetEntityCenterPosForBlockDestroying(target);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     if (world.isAirOrPassableBlock(target.getBlockPosX(), target.getHeadBlockPosY() + 1, target.getBlockPosZ(), false)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       RaycastCollision raycastCollision = world.getBlockCollisionForPhysicalReach(this.digger.getEyePosForBlockDestroying(), target_center_pos.addVector(0.0D, 1.0D, 0.0D));
/*     */ 
/*     */       
/* 115 */       if (raycastCollision != null && raycastCollision.isBlock())
/*     */       {
/* 117 */         if (!isRestrictedBlock(raycastCollision.getBlockHit()) || this.digger.isHoldingAnEffectiveTool(raycastCollision.getBlockHit(), raycastCollision.block_hit_metadata) || this.digger.isTargettingAPlayer()) {
/*     */ 
/*     */           
/* 120 */           raycastCollision.block_hit_y++;
/*     */           
/* 122 */           while (raycastCollision.block_hit_y >= attacker_foot_y) {
/*     */             
/* 124 */             if (this.digger.setBlockToDig(raycastCollision.block_hit_x, raycastCollision.block_hit_y, raycastCollision.block_hit_z, true)) {
/* 125 */               return true;
/*     */             }
/* 127 */             raycastCollision.block_hit_y--;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     RaycastCollision rc = world.getBlockCollisionForPhysicalReach(this.digger.getEyePosForBlockDestroying(), target_center_pos);
/*     */ 
/*     */     
/* 141 */     if (rc != null && rc.isBlock())
/*     */     {
/* 143 */       if (!isRestrictedBlock(rc.getBlockHit()) || this.digger.isHoldingAnEffectiveTool(rc.getBlockHit(), rc.block_hit_metadata) || this.digger.isTargettingAPlayer()) {
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
/* 157 */         rc.block_hit_y++;
/*     */         
/* 159 */         while (rc.block_hit_y >= attacker_foot_y) {
/*     */           
/* 161 */           if (this.digger.setBlockToDig(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, true)) {
/* 162 */             return true;
/*     */           }
/* 164 */           rc.block_hit_y--;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     rc = world.getBlockCollisionForPhysicalReach(this.digger.getAttackerLegPosForBlockDestroying(), target_center_pos);
/*     */     
/* 175 */     if (rc != null && rc.isBlock())
/*     */     {
/* 177 */       if (!isRestrictedBlock(rc.getBlockHit()) || this.digger.isHoldingAnEffectiveTool(rc.getBlockHit(), rc.block_hit_metadata) || this.digger.isTargettingAPlayer())
/*     */       {
/* 179 */         if (world.isAirOrPassableBlock(rc.block_hit_x, rc.block_hit_y + 1, rc.block_hit_z, false) || this.digger.blockWillFall(rc.block_hit_x, rc.block_hit_y + 1, rc.block_hit_z))
/*     */         {
/* 181 */           if (this.digger.setBlockToDig(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, false)) {
/* 182 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isRestrictedBlock(Block block) {
/* 193 */     if (block instanceof BlockFence) {
/* 194 */       return true;
/*     */     }
/* 196 */     return false;
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
/*     */   private RaycastCollision getIntersectingBlock(Vec3 attacker_eye_pos, Vec3 target_pos) {
/* 209 */     return this.digger.worldObj.getBlockCollisionForPhysicalReach(attacker_eye_pos, target_pos);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean couldGetCloserByPathing() {
/* 214 */     EntityLivingBase target = this.digger.getAttackTarget();
/*     */     
/* 216 */     if (target == null) {
/* 217 */       return false;
/*     */     }
/* 219 */     double distance = World.getDistanceFromDeltas(this.digger.posX - target.posX, this.digger.posY - target.posY, this.digger.posZ - target.posZ);
/*     */     
/* 221 */     PathEntity path = this.digger.getNavigator().getPathToEntityLiving(target, 16);
/*     */     
/* 223 */     if (path == null) {
/* 224 */       return false;
/*     */     }
/* 226 */     PathPoint final_point = path.getFinalPathPoint();
/*     */     
/* 228 */     float x = final_point.xCoord + 0.5F;
/* 229 */     float y = final_point.yCoord;
/* 230 */     float z = final_point.zCoord + 0.5F;
/*     */     
/* 232 */     if (World.getDistanceFromDeltas(x - target.posX, y - target.posY, z - target.posZ) < distance - 2.0D) {
/* 233 */       return true;
/*     */     }
/* 235 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean couldHitTargetByPathing() {
/* 240 */     EntityLivingBase target = this.digger.getAttackTarget();
/*     */     
/* 242 */     if (target == null) {
/* 243 */       return false;
/*     */     }
/* 245 */     PathEntity path = this.digger.getNavigator().getPathToEntityLiving(target, 16);
/*     */     
/* 247 */     if (path == null) {
/* 248 */       return false;
/*     */     }
/* 250 */     PathPoint final_point = path.getFinalPathPoint();
/*     */     
/* 252 */     float x = final_point.xCoord + 0.5F;
/* 253 */     float y = final_point.yCoord;
/* 254 */     float z = final_point.zCoord + 0.5F;
/*     */     
/* 256 */     if (World.getDistanceFromDeltas(x - target.posX, y - target.posY, z - target.posZ) > 1.0F) {
/* 257 */       return false;
/*     */     }
/* 259 */     return (getIntersectingBlock(this.digger.worldObj.getWorldVec3Pool().getVecFromPool(x, y, z), this.digger.getTargetEntityCenterPosForBlockDestroying(target)) == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 264 */     this.digger.is_destroying_block = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 271 */     if (this.digger.isHoldingItemThatPreventsDigging()) {
/* 272 */       return false;
/*     */     }
/* 274 */     EntityAIAttackOnCollide ai = (EntityAIAttackOnCollide)this.digger.getEntityAITask(EntityAIAttackOnCollide.class);
/*     */     
/* 276 */     if (ai != null) {
/*     */       
/* 278 */       if (ai.attackTick > 0) {
/* 279 */         ai.attackTick--;
/*     */       }
/* 281 */       if (ai.canStrikeTargetNow()) {
/*     */         
/* 283 */         ai.updateTask();
/* 284 */         return false;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 290 */     if (this.digger.destroy_pause_ticks > 0) {
/*     */       
/* 292 */       if (this.digger.destroy_pause_ticks == 1 && couldGetCloserByPathing()) {
/* 293 */         return false;
/*     */       }
/* 295 */       return true;
/*     */     } 
/*     */     
/* 298 */     if (!this.digger.is_destroying_block) {
/* 299 */       return false;
/*     */     }
/* 301 */     if (!this.digger.canDestroyBlock(this.digger.destroy_block_x, this.digger.destroy_block_y, this.digger.destroy_block_z, true)) {
/* 302 */       return false;
/*     */     }
/* 304 */     if (this.digger.recentlyHit > 0 && player_attacks_always_reset_digging) {
/* 305 */       return false;
/*     */     }
/* 307 */     EntityLivingBase target = this.digger.getAttackTarget();
/*     */     
/* 309 */     if (target == null) {
/* 310 */       return false;
/*     */     }
/* 312 */     if (this.digger.getBlockPosX() == target.getBlockPosX() && this.digger.getBlockPosY() == target.getBlockPosY() && this.digger.getBlockPosZ() == target.getBlockPosZ()) {
/* 313 */       return false;
/*     */     }
/*     */     
/* 316 */     if (this.digger.getTicksExistedWithOffset() % 10 == 0 && couldHitTargetByPathing()) {
/* 317 */       return false;
/*     */     }
/* 319 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 324 */     if (this.digger.destroy_pause_ticks > 0) {
/*     */       
/* 326 */       this.digger.destroy_pause_ticks--;
/*     */       
/*     */       return;
/*     */     } 
/* 330 */     if (this.digger.destroy_block_cooloff == 10) {
/* 331 */       this.digger.swingArm();
/*     */     }
/* 333 */     if (--this.digger.destroy_block_cooloff > 0) {
/*     */       return;
/*     */     }
/*     */     
/* 337 */     this.digger.destroy_block_cooloff = this.digger.getCooloffForBlock();
/*     */     
/* 339 */     this.digger.partiallyDestroyBlock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 344 */     this.digger.cancelBlockDestruction();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIWatchAnimal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */