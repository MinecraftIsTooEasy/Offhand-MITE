/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomPositionGenerator
/*     */ {
/*  11 */   private static Vec3 staticVector = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 findRandomTarget(EntityCreature par0EntityCreature, int par1, int par2) {
/*  18 */     return findRandomTargetBlock(par0EntityCreature, par1, par2, (Vec3)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 findRandomTargetBlockTowards(EntityCreature par0EntityCreature, int par1, int par2, Vec3 par3Vec3) {
/*  26 */     par3Vec3.xCoord -= par0EntityCreature.posX;
/*  27 */     par3Vec3.yCoord -= par0EntityCreature.posY;
/*  28 */     par3Vec3.zCoord -= par0EntityCreature.posZ;
/*  29 */     return findRandomTargetBlock(par0EntityCreature, par1, par2, staticVector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 findRandomTargetBlockAwayFrom(EntityCreature par0EntityCreature, int par1, int par2, Vec3 par3Vec3) {
/*  37 */     staticVector.xCoord = par0EntityCreature.posX - par3Vec3.xCoord;
/*  38 */     staticVector.yCoord = par0EntityCreature.posY - par3Vec3.yCoord;
/*  39 */     staticVector.zCoord = par0EntityCreature.posZ - par3Vec3.zCoord;
/*  40 */     return findRandomTargetBlock(par0EntityCreature, par1, par2, staticVector);
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
/*     */   private static Vec3 findRandomTargetBlock(EntityCreature creature, int horizontal_range, int vertical_range, Vec3 direction) {
/*     */     int attempts;
/* 108 */     boolean var10, intensive_search = false;
/*     */ 
/*     */ 
/*     */     
/* 112 */     if (intensive_search) {
/*     */       
/* 114 */       attempts = 64;
/* 115 */       horizontal_range *= 2;
/*     */     }
/*     */     else {
/*     */       
/* 119 */       attempts = 10;
/*     */     } 
/*     */     
/* 122 */     Random var4 = creature.getRNG();
/*     */ 
/*     */ 
/*     */     
/* 126 */     int creature_x = MathHelper.floor_double(creature.posX);
/* 127 */     int creature_y = MathHelper.floor_double(creature.posY);
/* 128 */     int creature_z = MathHelper.floor_double(creature.posZ);
/*     */     
/* 130 */     if (creature.hasHome()) {
/*     */       
/* 132 */       double var11 = (creature.getHomePosition().getDistanceSquared(creature_x, creature_y, creature_z) + 4.0F);
/* 133 */       double var13 = (creature.func_110174_bM() + horizontal_range);
/* 134 */       var10 = (var11 < var13 * var13);
/*     */     }
/*     */     else {
/*     */       
/* 138 */       var10 = false;
/*     */     } 
/*     */     
/* 141 */     int selected_block_x = creature_x;
/* 142 */     int selected_block_y = creature_y;
/* 143 */     int selected_block_z = creature_z;
/*     */     
/* 145 */     float heaviest_weight = -100.0F;
/* 146 */     PathEntity path_entity = null;
/*     */     
/* 148 */     for (int var16 = 0; var16 < attempts; var16++) {
/*     */       
/* 150 */       int x = var4.nextInt(2 * horizontal_range + 1) - horizontal_range;
/* 151 */       int y = var4.nextInt(2 * vertical_range + 1) - vertical_range;
/* 152 */       int z = var4.nextInt(2 * horizontal_range + 1) - horizontal_range;
/*     */       
/* 154 */       if (direction == null || x * direction.xCoord + z * direction.zCoord >= 0.0D) {
/*     */         
/* 156 */         x += creature_x;
/* 157 */         y += creature_y;
/* 158 */         z += creature_z;
/*     */         
/* 160 */         if (!var10 || creature.func_110176_b(x, y, z)) {
/*     */           
/* 162 */           float weight = creature.getBlockPathWeight(x, y, z);
/*     */           
/* 164 */           if (weight > heaviest_weight) {
/*     */             
/* 166 */             if (intensive_search) {
/* 167 */               path_entity = creature.worldObj.getEntityPathToXYZ(creature, x, y, z, 16.0F, true, false, false, true);
/*     */             }
/* 169 */             if (!intensive_search || path_entity != null) {
/*     */               
/* 171 */               heaviest_weight = weight;
/*     */               
/* 173 */               selected_block_x = x;
/* 174 */               selected_block_y = y;
/* 175 */               selected_block_z = z;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 182 */     if (heaviest_weight > -100.0F)
/*     */     {
/* 184 */       return creature.worldObj.getWorldVec3Pool().getVecFromPool(selected_block_x, selected_block_y, selected_block_z);
/*     */     }
/*     */ 
/*     */     
/* 188 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RandomPositionGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */