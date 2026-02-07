/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAISeekFiringPosition
/*     */   extends EntityAIMovementTask
/*     */ {
/*   9 */   private int max_path_length = 16;
/*     */   
/*  11 */   EntityPlayer[] candidate_players = new EntityPlayer[16];
/*     */   
/*     */   int num_candidate_players;
/*     */   
/*     */   public EntityAISeekFiringPosition(EntityLiving task_owner, float movement_speed, boolean swim_if_necessary) {
/*  16 */     super(task_owner, movement_speed, swim_if_necessary);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  21 */     if (this.task_owner.getAttackTarget() != null) {
/*  22 */       return false;
/*     */     }
/*  24 */     if (!RNG.chance_in_16[++this.random_number_index & 0x7FFF]) {
/*  25 */       return false;
/*     */     }
/*  27 */     this.num_candidate_players = 0;
/*     */     
/*  29 */     for (int i = 0; i < this.world.playerEntities.size() && this.num_candidate_players < this.candidate_players.length; i++) {
/*     */       
/*  31 */       EntityPlayer player = this.world.playerEntities.get(i);
/*     */       
/*  33 */       if (this.task_owner.getDistanceSqToEntity(player) <= 900.0D) {
/*     */ 
/*     */         
/*  36 */         if (this.task_owner.getEntitySenses().canSee(player)) {
/*  37 */           return false;
/*     */         }
/*  39 */         this.candidate_players[this.num_candidate_players++] = player;
/*     */       } 
/*     */     } 
/*  42 */     return (this.num_candidate_players > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathEntity getMovementPath() {
/*  47 */     int task_owner_x = this.task_owner.getBlockPosX();
/*  48 */     int task_owner_y = this.task_owner.getBlockPosY();
/*  49 */     int task_owner_z = this.task_owner.getBlockPosZ();
/*     */ 
/*     */ 
/*     */     
/*  53 */     int domain_size = this.max_path_length * 2 + 1;
/*     */     
/*  55 */     for (int candidate_player_index = 0; candidate_player_index < this.num_candidate_players; candidate_player_index++) {
/*     */       
/*  57 */       EntityPlayer target = this.candidate_players[candidate_player_index];
/*     */       
/*  59 */       for (int attempt = 0; attempt < 4; attempt++) {
/*     */         
/*  61 */         int dx = RNG.int_max[++this.random_number_index & 0x7FFF] % domain_size - this.max_path_length;
/*  62 */         int dy = RNG.int_7_minus_3[++this.random_number_index & 0x7FFF];
/*  63 */         int dz = RNG.int_max[++this.random_number_index & 0x7FFF] % domain_size - this.max_path_length;
/*     */         
/*  65 */         int trial_x = task_owner_x + dx;
/*  66 */         int trial_y = task_owner_y + dy;
/*  67 */         int trial_z = task_owner_z + dz;
/*     */         int i;
/*  69 */         for (i = 0; i < 8;) {
/*     */           
/*  71 */           if (this.world.isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, false)) {
/*  72 */             trial_y--;
/*     */             
/*     */             i++;
/*     */           } 
/*     */         } 
/*  77 */         for (i = 0; i < 8;) {
/*     */           
/*  79 */           if (!this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false)) {
/*  80 */             trial_y++;
/*     */             
/*     */             i++;
/*     */           } 
/*     */         } 
/*     */         
/*  86 */         if (this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false) && !this.world.occupiedByLivingEntity(trial_x, trial_y, trial_z) && target.canEntityBeSeenFrom((trial_x + 0.5F), (trial_y + this.task_owner.getEyeHeight()), (trial_z + 0.5F), 900.0D)) {
/*     */           
/*  88 */           PathEntity path = this.task_owner.getNavigator().getPathToXYZ(trial_x, trial_y, trial_z, this.max_path_length);
/*     */           
/*  90 */           if (path != null) {
/*     */             
/*  92 */             PathPoint final_point = path.getFinalPathPoint();
/*     */ 
/*     */             
/*  95 */             if (target.canEntityBeSeenFrom((final_point.xCoord + 0.5F), (final_point.yCoord + this.task_owner.getEyeHeight()), (final_point.zCoord + 0.5F), 900.0D) && !this.world.occupiedByLivingEntity(final_point.xCoord, final_point.yCoord, final_point.zCoord))
/*     */             {
/*     */               
/*  98 */               return path;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 110 */     if (this.task_owner.getAttackTarget() != null) {
/* 111 */       return false;
/*     */     }
/* 113 */     if (RNG.chance_in_16[++this.random_number_index & 0x7FFF])
/*     */     {
/* 115 */       for (int candidate_player_index = 0; candidate_player_index < this.num_candidate_players; candidate_player_index++) {
/*     */         
/* 117 */         if (this.task_owner.getEntitySenses().canSee(this.candidate_players[candidate_player_index])) {
/* 118 */           return false;
/*     */         }
/*     */       } 
/*     */     }
/* 122 */     return super.continueExecuting();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 127 */     super.resetTask();
/*     */     
/* 129 */     this.num_candidate_players = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAISeekFiringPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */