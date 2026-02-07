/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAISeekOpenSpaceIfCrowded
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityLivestock livestock;
/*     */   private float movement_speed;
/*     */   private int random_number_index;
/*     */   
/*     */   public EntityAISeekOpenSpaceIfCrowded(EntityLivestock livestock, float movement_speed) {
/*  14 */     this.livestock = livestock;
/*  15 */     this.movement_speed = movement_speed;
/*  16 */     setMutexBits(3);
/*  17 */     this.random_number_index = livestock.rand.nextInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  25 */     if (this.livestock.getFreedom() > 0.5F) {
/*  26 */       return false;
/*     */     }
/*  28 */     if (!this.livestock.isCrowded()) {
/*  29 */       return true;
/*     */     }
/*  31 */     if (this.livestock.getRNG().nextInt((this.livestock.getFreedom() < 0.25F) ? 10 : 40) != 0) {
/*  32 */       return false;
/*     */     }
/*  34 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathEntity getShortestPathToFreedom() {
/*  39 */     boolean is_very_crowded = (this.livestock.getFreedom() < 0.25F);
/*     */     
/*  41 */     int entity_x = MathHelper.floor_double(this.livestock.posX);
/*  42 */     int entity_y = MathHelper.floor_double(this.livestock.posY);
/*  43 */     int entity_z = MathHelper.floor_double(this.livestock.posZ);
/*     */     
/*  45 */     PathEntity shortest_path = null;
/*  46 */     int shortest_path_length = -1;
/*  47 */     int num_paths_found = 0;
/*  48 */     int max_num_paths = is_very_crowded ? 2 : 1;
/*     */     
/*  50 */     int attempts = is_very_crowded ? 16 : 4;
/*     */     
/*  52 */     for (int attempt = 0; attempt < attempts; attempt++) {
/*     */       int dx, dy, dz;
/*     */ 
/*     */       
/*  56 */       if (attempt % 2 == 0) {
/*     */         
/*  58 */         dx = RNG.int_17_minus_8[++this.random_number_index & 0x7FFF];
/*  59 */         dy = RNG.int_7_minus_3[++this.random_number_index & 0x7FFF];
/*  60 */         dz = RNG.int_17_minus_8[++this.random_number_index & 0x7FFF];
/*     */       }
/*     */       else {
/*     */         
/*  64 */         dx = RNG.int_33_minus_16[++this.random_number_index & 0x7FFF];
/*  65 */         dy = RNG.int_7_minus_3[++this.random_number_index & 0x7FFF];
/*  66 */         dz = RNG.int_33_minus_16[++this.random_number_index & 0x7FFF];
/*     */       } 
/*     */       
/*  69 */       int x = entity_x + dx;
/*  70 */       int y = entity_y + dy;
/*  71 */       int z = entity_z + dz;
/*     */       
/*     */       int i;
/*     */       
/*  75 */       for (i = 0; i < 8; ) {
/*     */         
/*  77 */         int j = this.livestock.worldObj.getBlockId(x, y - 1, z);
/*     */         
/*  79 */         if (j == 0 || j == Block.tallGrass.blockID) {
/*  80 */           y--;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  85 */       for (i = 0; i < 8; ) {
/*     */         
/*  87 */         int j = this.livestock.worldObj.getBlockId(x, y, z);
/*     */         
/*  89 */         if (j == Block.stone.blockID || j == Block.dirt.blockID || j == Block.grass.blockID) {
/*  90 */           y++;
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/*  95 */       int block_id = this.livestock.worldObj.getBlockId(x, y - 1, z);
/*     */       
/*  97 */       if (block_id != Block.waterStill.blockID && block_id != Block.waterMoving.blockID)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 102 */         if (!this.livestock.isCrowded(x, y, z)) {
/*     */           
/* 104 */           PathEntity path = this.livestock.getNavigator().getPathToXYZ(x, y, z, 16);
/*     */           
/* 106 */           if (path != null) {
/*     */             
/* 108 */             PathPoint final_point = path.getFinalPathPoint();
/*     */             
/* 110 */             if (!this.livestock.isCrowded(final_point.xCoord, final_point.yCoord, final_point.zCoord)) {
/*     */ 
/*     */               
/* 113 */               int path_length = path.getCurrentPathLength();
/*     */               
/* 115 */               if (shortest_path == null || path_length < shortest_path_length) {
/*     */                 
/* 117 */                 shortest_path = path;
/* 118 */                 shortest_path_length = path_length;
/*     */                 
/* 120 */                 if (++num_paths_found > max_num_paths) {
/*     */                   break;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     return shortest_path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 142 */     if (this.livestock.getFreedom() > 0.95F) {
/* 143 */       return false;
/*     */     }
/* 145 */     if (!this.livestock.isCrowded()) {
/*     */       
/* 147 */       resetTask();
/* 148 */       return true;
/*     */     } 
/*     */     
/* 151 */     return !this.livestock.getNavigator().noPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 159 */     if (!this.livestock.isCrowded()) {
/*     */       return;
/*     */     }
/* 162 */     this.livestock.getNavigator().setPath(getShortestPathToFreedom(), this.movement_speed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 167 */     this.livestock.getNavigator().clearPathEntity();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAISeekOpenSpaceIfCrowded.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */