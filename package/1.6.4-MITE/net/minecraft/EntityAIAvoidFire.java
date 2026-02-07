/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIAvoidFire
/*     */   extends EntityAIMovementTask
/*     */ {
/*  11 */   private int max_path_length = 8;
/*     */   
/*  13 */   private final int max_fires = 16;
/*     */   private int num_fires;
/*  15 */   private int[] fire_x = new int[16];
/*  16 */   private int[] fire_y = new int[16];
/*  17 */   private int[] fire_z = new int[16];
/*  18 */   private double[] fire_distance_sq = new double[16];
/*     */ 
/*     */   
/*     */   public EntityAIAvoidFire(EntityLiving task_owner, float movement_speed, boolean swim_if_necessary) {
/*  22 */     super(task_owner, movement_speed, swim_if_necessary);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  27 */     if (this.task_owner.rand.nextInt(10) > 0) {
/*  28 */       return false;
/*     */     }
/*  30 */     if (!this.task_owner.isHarmedByFire()) {
/*  31 */       return false;
/*     */     }
/*  33 */     return this.world.blockTypeIsNearTo(Block.fire.blockID, this.task_owner.posX, this.task_owner.posY, this.task_owner.posZ, this.max_path_length, this.max_path_length / 4);
/*     */   }
/*     */ 
/*     */   
/*     */   double getDistanceSqToNearestFire(int x, int y, int z) {
/*  38 */     double distance_sq_to_nearest_fire = Double.MAX_VALUE;
/*     */     
/*  40 */     for (int i = 0; i < this.num_fires; i++) {
/*     */       
/*  42 */       double distance_sq_to_fire = World.getDistanceSqFromDeltas((x - this.fire_x[i]), (y - this.fire_y[i]), (z - this.fire_z[i]));
/*     */       
/*  44 */       if (distance_sq_to_fire < distance_sq_to_nearest_fire) {
/*  45 */         distance_sq_to_nearest_fire = distance_sq_to_fire;
/*     */       }
/*     */     } 
/*  48 */     return distance_sq_to_nearest_fire;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathEntity getMovementPath() {
/*  53 */     int[] block_ids = { Block.fire.blockID };
/*  54 */     this.num_fires = this.world.getNearestBlockCandidates(this.task_owner.posX, this.task_owner.posY + (this.task_owner.height * 0.75F), this.task_owner.posZ, this.max_path_length * 2, this.max_path_length / 2, 16, block_ids, this.fire_x, this.fire_y, this.fire_z, this.fire_distance_sq);
/*     */     
/*  56 */     if (this.num_fires == 0) {
/*  57 */       return null;
/*     */     }
/*  59 */     int x = MathHelper.floor_double(this.task_owner.posX);
/*  60 */     int y = MathHelper.floor_double(this.task_owner.posY);
/*  61 */     int z = MathHelper.floor_double(this.task_owner.posZ);
/*     */     
/*  63 */     double longest_distance_sq_to_nearest_fire = getDistanceSqToNearestFire(x, y, z);
/*  64 */     PathEntity selected_path = null;
/*     */ 
/*     */ 
/*     */     
/*  68 */     for (int attempt = 0; attempt < 16; attempt++) {
/*     */       
/*  70 */       int dx = RNG.int_max[++this.random_number_index & 0x7FFF] % (this.max_path_length * 2 + 1) - this.max_path_length;
/*  71 */       int dy = RNG.int_7_minus_3[++this.random_number_index & 0x7FFF];
/*  72 */       int dz = RNG.int_max[++this.random_number_index & 0x7FFF] % (this.max_path_length * 2 + 1) - this.max_path_length;
/*     */       
/*  74 */       int trial_x = x + dx;
/*  75 */       int trial_y = y + dy;
/*  76 */       int trial_z = z + dz;
/*     */       int i;
/*  78 */       for (i = 0; i < 8;) {
/*     */         
/*  80 */         if (this.world.isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, false)) {
/*  81 */           trial_y--;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  86 */       for (i = 0; i < 8;) {
/*     */         
/*  88 */         if (!this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false)) {
/*  89 */           trial_y++;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  94 */       double distance_sq_to_nearest_fire = getDistanceSqToNearestFire(trial_x, trial_y, trial_z);
/*     */       
/*  96 */       if (distance_sq_to_nearest_fire > longest_distance_sq_to_nearest_fire) {
/*     */         
/*  98 */         PathEntity path = this.task_owner.getNavigator().getPathToXYZ(trial_x, trial_y, trial_z, this.max_path_length);
/*     */         
/* 100 */         if (path != null) {
/*     */           
/* 102 */           PathPoint final_point = path.getFinalPathPoint();
/*     */           
/* 104 */           trial_x = final_point.xCoord;
/* 105 */           trial_y = final_point.yCoord;
/* 106 */           trial_z = final_point.zCoord;
/*     */           
/* 108 */           distance_sq_to_nearest_fire = getDistanceSqToNearestFire(trial_x, trial_y, trial_z);
/*     */           
/* 110 */           if (distance_sq_to_nearest_fire > longest_distance_sq_to_nearest_fire) {
/*     */             
/* 112 */             longest_distance_sq_to_nearest_fire = distance_sq_to_nearest_fire;
/* 113 */             selected_path = path;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     return selected_path;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIAvoidFire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */