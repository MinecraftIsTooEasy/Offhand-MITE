/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIEatGrass
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityLiving theEntity;
/*     */   private World theWorld;
/*     */   int eatGrassTick;
/*     */   
/*     */   public EntityAIEatGrass(EntityLiving par1EntityLiving) {
/*  15 */     this.theEntity = par1EntityLiving;
/*  16 */     this.theWorld = par1EntityLiving.worldObj;
/*  17 */     setMutexBits(7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  25 */     if (this.theEntity.hurtTime > 0 || this.theEntity.has_decided_to_flee) {
/*  26 */       return false;
/*     */     }
/*  28 */     if (this.theEntity.getRNG().nextInt(this.theEntity.isChild() ? 50 : 1000) != 0)
/*     */     {
/*  30 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  34 */     int var1 = MathHelper.floor_double(this.theEntity.posX);
/*     */     
/*  36 */     int var2 = this.theEntity.getBlockPosY();
/*  37 */     int var3 = MathHelper.floor_double(this.theEntity.posZ);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     Block block = Block.blocksList[this.theWorld.getBlockId(var1, var2, var3)];
/*     */     
/*  44 */     if (block == Block.tallGrass || (block instanceof BlockCrops && !(block instanceof BlockCropsDead))) {
/*  45 */       return true;
/*     */     }
/*  47 */     block = Block.blocksList[this.theWorld.getBlockId(var1, var2 - 1, var3)];
/*     */     
/*  49 */     if (block == Block.grass && BlockGrass.getTramplingEffect(BlockGrass.getTramplings(this.theWorld.getBlockMetadata(var1, var2 - 1, var3))) == 0.0F) {
/*  50 */       return true;
/*     */     }
/*  52 */     List predators = this.theWorld.getPredatorsWithinAABBForEntity(this.theEntity, this.theEntity.boundingBox.expand(16.0D, 2.0D, 16.0D));
/*     */     
/*  54 */     return predators.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  65 */     this.eatGrassTick = 40;
/*     */     
/*  67 */     this.theWorld.setEntityState(this.theEntity, EnumEntityState.tnt_ignite_or_eating_grass);
/*  68 */     this.theEntity.getNavigator().clearPathEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  76 */     this.eatGrassTick = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  84 */     if (this.theEntity.hurtTime > 0 || this.theEntity.has_decided_to_flee) {
/*  85 */       return false;
/*     */     }
/*  87 */     return (this.eatGrassTick > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEatGrassTick() {
/*  95 */     return this.eatGrassTick;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 103 */     this.eatGrassTick = Math.max(0, this.eatGrassTick - 1);
/*     */     
/* 105 */     if (this.eatGrassTick == 4) {
/*     */       
/* 107 */       int var1 = MathHelper.floor_double(this.theEntity.posX);
/* 108 */       int var2 = MathHelper.floor_double(this.theEntity.posY);
/* 109 */       int var3 = MathHelper.floor_double(this.theEntity.posZ);
/*     */       
/* 111 */       Block block = Block.blocksList[this.theWorld.getBlockId(var1, var2, var3)];
/*     */ 
/*     */       
/* 114 */       if (block == Block.tallGrass || block instanceof BlockCrops) {
/*     */         
/* 116 */         this.theWorld.destroyBlock((new BlockBreakInfo(this.theWorld, var1, var2, var3)).setEatenBy(this.theEntity), false);
/* 117 */         this.theEntity.eatGrassBonus();
/*     */       }
/* 119 */       else if (this.theWorld.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID) {
/*     */         
/* 121 */         this.theWorld.playAuxSFX(2001, var1, var2 - 1, var3, Block.grass.blockID);
/* 122 */         this.theWorld.setBlock(var1, var2 - 1, var3, Block.dirt.blockID, 0, 2);
/* 123 */         this.theEntity.eatGrassBonus();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIEatGrass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */