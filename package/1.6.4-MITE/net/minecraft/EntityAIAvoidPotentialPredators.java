/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIAvoidPotentialPredators
/*     */   extends EntityAIMovementTask
/*     */ {
/*     */   private double distance_sq_to_nearest_predator;
/*  12 */   private AxisAlignedBB bounding_box = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  13 */   private int max_path_length = 32;
/*     */   
/*     */   private List predators;
/*     */   
/*     */   public EntityAIAvoidPotentialPredators(EntityLiving task_owner, float movement_speed, boolean swim_if_necessary) {
/*  18 */     super(task_owner, movement_speed, swim_if_necessary);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  23 */     if (this.task_owner.rand.nextInt(10) > 0) {
/*  24 */       return false;
/*     */     }
/*  26 */     this.bounding_box.setBounds(this.task_owner.posX - this.max_path_length, this.task_owner.posY - 4.0D, this.task_owner.posZ - this.max_path_length, this.task_owner.posX + this.max_path_length, this.task_owner.posY + 4.0D, this.task_owner.posZ + this.max_path_length);
/*  27 */     this.predators = this.world.getPredatorsWithinAABBForEntity(this.task_owner, this.bounding_box);
/*     */     
/*  29 */     if (this.task_owner instanceof EntityHorse) {
/*     */       
/*  31 */       EntityHorse entity_horse = (EntityHorse)this.task_owner;
/*     */       
/*  33 */       if (entity_horse.isShy()) {
/*     */         
/*  35 */         List nearby_players = this.world.getEntitiesWithinAABB(EntityPlayer.class, this.bounding_box);
/*  36 */         this.predators.addAll(nearby_players);
/*     */       } 
/*     */     } 
/*     */     
/*  40 */     this.distance_sq_to_nearest_predator = getDistanceSqToNearestPredator(MathHelper.floor_double(this.task_owner.posX), MathHelper.floor_double(this.task_owner.posY), MathHelper.floor_double(this.task_owner.posZ), this.predators);
/*     */     
/*  42 */     if (this.distance_sq_to_nearest_predator < (this.max_path_length * this.max_path_length / 4)) {
/*  43 */       return true;
/*     */     }
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   double getDistanceSqToNearestPredator(int x, int y, int z, List predators) {
/*  50 */     double distance_sq_to_nearest_predator = Double.MAX_VALUE;
/*     */     
/*  52 */     Iterator<Entity> i = predators.iterator();
/*     */     
/*  54 */     while (i.hasNext()) {
/*     */       
/*  56 */       Entity entity = i.next();
/*     */       
/*  58 */       double distance_sq_to_predator = World.getDistanceSqFromDeltas(x - (float)entity.posX, y - (float)entity.posY, z - (float)entity.posZ);
/*     */       
/*  60 */       if (distance_sq_to_predator < distance_sq_to_nearest_predator) {
/*  61 */         distance_sq_to_nearest_predator = distance_sq_to_predator;
/*     */       }
/*     */     } 
/*  64 */     return distance_sq_to_nearest_predator;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathEntity getMovementPath() {
/*  69 */     int prey_x = MathHelper.floor_double(this.task_owner.posX);
/*  70 */     int prey_y = MathHelper.floor_double(this.task_owner.posY);
/*  71 */     int prey_z = MathHelper.floor_double(this.task_owner.posZ);
/*     */     
/*  73 */     double longest_distance_sq_to_nearest_predator = this.distance_sq_to_nearest_predator;
/*  74 */     PathEntity selected_path = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     for (int attempt = 0; attempt < 16; attempt++) {
/*     */       
/*  82 */       int dx = RNG.int_max[++this.random_number_index & 0x7FFF] % (this.max_path_length * 2 + 1) - this.max_path_length;
/*  83 */       int dy = RNG.int_7_minus_3[++this.random_number_index & 0x7FFF];
/*  84 */       int dz = RNG.int_max[++this.random_number_index & 0x7FFF] % (this.max_path_length * 2 + 1) - this.max_path_length;
/*     */       
/*  86 */       int trial_x = prey_x + dx;
/*  87 */       int trial_y = prey_y + dy;
/*  88 */       int trial_z = prey_z + dz;
/*     */       int i;
/*  90 */       for (i = 0; i < 8;) {
/*     */         
/*  92 */         if (this.world.isAirOrPassableBlock(trial_x, trial_y - 1, trial_z, false)) {
/*  93 */           trial_y--;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  98 */       for (i = 0; i < 8;) {
/*     */         
/* 100 */         if (!this.world.isAirOrPassableBlock(trial_x, trial_y, trial_z, false)) {
/* 101 */           trial_y++;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/* 106 */       double distance_sq_to_nearest_predator = getDistanceSqToNearestPredator(trial_x, trial_y, trial_z, this.predators);
/*     */       
/* 108 */       if (distance_sq_to_nearest_predator > longest_distance_sq_to_nearest_predator) {
/*     */         
/* 110 */         PathEntity path = this.task_owner.getNavigator().getPathToXYZ(trial_x, trial_y, trial_z, this.max_path_length);
/*     */         
/* 112 */         if (path != null) {
/*     */           
/* 114 */           PathPoint final_point = path.getFinalPathPoint();
/*     */           
/* 116 */           trial_x = final_point.xCoord;
/* 117 */           trial_y = final_point.yCoord;
/* 118 */           trial_z = final_point.zCoord;
/*     */           
/* 120 */           distance_sq_to_nearest_predator = getDistanceSqToNearestPredator(trial_x, trial_y, trial_z, this.predators);
/*     */           
/* 122 */           if (distance_sq_to_nearest_predator > longest_distance_sq_to_nearest_predator) {
/*     */             
/* 124 */             longest_distance_sq_to_nearest_predator = distance_sq_to_nearest_predator;
/* 125 */             selected_path = path;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     return selected_path;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isPredatorAttacking() {
/* 136 */     Iterator<EntityLivingBase> i = this.predators.iterator();
/*     */     
/* 138 */     while (i.hasNext()) {
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
/* 150 */       EntityLivingBase entity_living_base = i.next();
/*     */       
/* 152 */       if (entity_living_base instanceof EntityLiving && entity_living_base.getAsEntityLiving().getTarget() == this.task_owner) {
/* 153 */         return true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 158 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getMovementSpeed() {
/* 163 */     return super.getMovementSpeed() * (isPredatorAttacking() ? 1.2F : 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 168 */     super.resetTask();
/*     */     
/* 170 */     this.predators = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIAvoidPotentialPredators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */