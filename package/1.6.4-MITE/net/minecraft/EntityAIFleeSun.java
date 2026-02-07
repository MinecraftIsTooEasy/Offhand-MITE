/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class EntityAIFleeSun
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityCreature theCreature;
/*     */   private double shelterX;
/*     */   private double shelterY;
/*     */   private double shelterZ;
/*     */   private double movementSpeed;
/*     */   private World theWorld;
/*     */   
/*     */   public EntityAIFleeSun(EntityCreature par1EntityCreature, double par2) {
/*  16 */     this.theCreature = par1EntityCreature;
/*  17 */     this.movementSpeed = par2;
/*  18 */     this.theWorld = par1EntityCreature.worldObj;
/*  19 */     setMutexBits(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  27 */     if (!this.theWorld.isDaytime())
/*     */     {
/*  29 */       return false;
/*     */     }
/*  31 */     if (!this.theCreature.isBurning())
/*     */     {
/*  33 */       return false;
/*     */     }
/*  35 */     if (!this.theWorld.canBlockSeeTheSky(MathHelper.floor_double(this.theCreature.posX), (int)this.theCreature.boundingBox.minY, MathHelper.floor_double(this.theCreature.posZ)))
/*     */     {
/*  37 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  41 */     if (this.theCreature instanceof EntitySkeleton && !((EntitySkeleton)this.theCreature).avoidsSunlight()) {
/*  42 */       return false;
/*     */     }
/*  44 */     Vec3 var1 = findPossibleShelter();
/*     */     
/*  46 */     if (var1 == null)
/*     */     {
/*  48 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  52 */     this.shelterX = var1.xCoord;
/*  53 */     this.shelterY = var1.yCoord;
/*  54 */     this.shelterZ = var1.zCoord;
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  65 */     return !this.theCreature.getNavigator().noPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  73 */     this.theCreature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
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
/*     */   private Vec3 findPossibleShelter() {
/*  97 */     Random random = this.theCreature.getRNG();
/*     */     
/*  99 */     int origin_x = this.theCreature.getBlockPosX();
/* 100 */     int origin_y = this.theCreature.getFootBlockPosY();
/* 101 */     int origin_z = this.theCreature.getBlockPosZ();
/*     */     
/* 103 */     for (int attempt = 0; attempt < 10; attempt++) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 113 */       int dx = random.nextInt(3 + attempt * 2) - 1 + attempt;
/* 114 */       int dy = random.nextInt(7) - 3;
/* 115 */       int dz = random.nextInt(3 + attempt * 2) - 1 + attempt;
/*     */       
/* 117 */       int x = origin_x + dx;
/* 118 */       int y = origin_y + dy;
/* 119 */       int z = origin_z + dz;
/*     */       
/* 121 */       Block block = this.theWorld.getBlock(x, y, z);
/*     */       
/* 123 */       if (block != null) {
/*     */         
/* 125 */         if (block.isAlwaysSolidStandardFormCube()) {
/*     */           continue;
/*     */         }
/* 128 */         if (block.blockMaterial == Material.water && this.theWorld.isAirOrPassableBlock(x, y + 1, z, true)) {
/* 129 */           return this.theWorld.getWorldVec3Pool().getVecFromPool(x, y, z);
/*     */         }
/* 131 */         if (block.blockMaterial == Material.lava && this.theCreature.isHarmedByLava()) {
/*     */           continue;
/*     */         }
/* 134 */         if (block.blockMaterial == Material.fire && this.theCreature.isHarmedByFire()) {
/*     */           continue;
/*     */         }
/*     */       } 
/* 138 */       if (block == null || block.isNeverSolid() || this.theWorld.isAirOrPassableBlock(x, y, z, false))
/*     */       {
/* 140 */         if (!this.theWorld.canBlockSeeTheSky(x, y + 1, z)) {
/*     */ 
/*     */           
/* 143 */           Material material_below = this.theWorld.getBlockMaterial(x, y - 1, z);
/*     */           
/* 145 */           if (material_below != Material.lava || !this.theCreature.isHarmedByLava())
/*     */           {
/*     */             
/* 148 */             if (material_below != Material.fire || !this.theCreature.isHarmedByFire()) {
/*     */ 
/*     */               
/* 151 */               if (this.theCreature.height <= 1.0F) {
/* 152 */                 return this.theWorld.getWorldVec3Pool().getVecFromPool(x, y, z);
/*     */               }
/* 154 */               block = this.theWorld.getBlock(x, y + 1, z);
/*     */               
/* 156 */               if (block == null) {
/* 157 */                 return this.theWorld.getWorldVec3Pool().getVecFromPool(x, y, z);
/*     */               }
/* 159 */               if (!block.isAlwaysSolidStandardFormCube())
/*     */               {
/*     */                 
/* 162 */                 if (block.isNeverSolid() || this.theWorld.isAirOrPassableBlock(x, y + 1, z, false))
/* 163 */                   return this.theWorld.getWorldVec3Pool().getVecFromPool(x, y, z);  } 
/*     */             }  } 
/*     */         }  }  continue;
/*     */     } 
/* 167 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIFleeSun.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */