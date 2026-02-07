/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityAIBreakDoor
/*     */   extends EntityAIDoorInteract {
/*     */   private int breakingTime;
/*   6 */   private int field_75358_j = -1;
/*     */ 
/*     */   
/*     */   public EntityAIBreakDoor(EntityLiving par1EntityLiving) {
/*  10 */     super(par1EntityLiving);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  18 */     if (!super.shouldExecute()) {
/*  19 */       return false;
/*     */     }
/*  21 */     if (this.theEntity.worldObj.getBlock(this.entityPosX, this.entityPosY, this.entityPosZ) == Block.fenceGate && !(this.theEntity.getTarget() instanceof EntityPlayer)) {
/*  22 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  26 */     return (this.theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") && PathFinder.isAClosedWoodenPortal(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  34 */     super.startExecuting();
/*  35 */     this.breakingTime = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  43 */     double var1 = this.theEntity.getDistanceSq(this.entityPosX, this.entityPosY, this.entityPosZ);
/*     */     
/*  45 */     return (this.breakingTime <= getTicksToBreakPortal() && PathFinder.isAClosedWoodenPortal(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ) && var1 < 4.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  53 */     super.resetTask();
/*  54 */     this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.entityId, this.entityPosX, this.entityPosY, this.entityPosZ, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  62 */     super.updateTask();
/*     */     
/*  64 */     this.theEntity.refreshDespawnCounter(-400);
/*     */     
/*  66 */     int metadata = getTargetBlockMetadata();
/*     */     
/*  68 */     if (this.theEntity.getRNG().nextInt(20) == 0) {
/*     */       
/*  70 */       this.theEntity.worldObj.playAuxSFX(1010, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
/*     */       
/*  72 */       this.theEntity.worldObj.playAuxSFX(2001, this.entityPosX, this.entityPosY, this.entityPosZ, this.targetDoor.blockID + (metadata << 12) + RenderGlobal.SFX_2001_SUPPRESS_SOUND + RenderGlobal.SFX_2001_FOR_AI_BREAK_DOOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  77 */     this.breakingTime += (int)(getStrVsTargetBlock() + 0.5F);
/*     */     
/*  79 */     int ticks_to_break_portal = getTicksToBreakPortal();
/*     */     
/*  81 */     if (this.breakingTime > ticks_to_break_portal) {
/*  82 */       this.breakingTime = ticks_to_break_portal;
/*     */     }
/*     */     
/*  85 */     int var1 = (int)(this.breakingTime / ticks_to_break_portal * 10.0F);
/*     */     
/*  87 */     if (var1 != this.field_75358_j) {
/*     */       
/*  89 */       this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.entityId, this.entityPosX, this.entityPosY, this.entityPosZ, var1);
/*  90 */       this.field_75358_j = var1;
/*     */     } 
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
/* 105 */     if (this.breakingTime == ticks_to_break_portal && this.theEntity.worldObj.difficultySetting == 3) {
/*     */       
/* 107 */       if (getTargetBlock() instanceof BlockDoor && BlockDoor.isTopHalf(getTargetBlockMetadata())) {
/* 108 */         this.theEntity.worldObj.setBlockToAir(this.entityPosX, this.entityPosY - 1, this.entityPosZ, 2);
/*     */       }
/* 110 */       this.theEntity.worldObj.setBlockToAir(this.entityPosX, this.entityPosY, this.entityPosZ);
/* 111 */       this.theEntity.worldObj.playAuxSFX(1012, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
/*     */       
/* 113 */       this.theEntity.worldObj.playAuxSFX(2001, this.entityPosX, this.entityPosY, this.entityPosZ, this.targetDoor.blockID + (metadata << 12));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Block getTargetBlock() {
/* 119 */     return this.theEntity.worldObj.getBlock(this.entityPosX, this.entityPosY, this.entityPosZ);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getTargetBlockMetadata() {
/* 124 */     return this.theEntity.worldObj.getBlockMetadata(this.entityPosX, this.entityPosY, this.entityPosZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getTicksToBreakPortal() {
/* 131 */     int ticks = (getTargetBlock() == Block.doorWood) ? 1920 : 480;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     if (this.theEntity instanceof EntityEarthElemental) {
/*     */       
/* 138 */       EntityEarthElemental elemental = (EntityEarthElemental)this.theEntity;
/*     */       
/* 140 */       if (elemental.isNormalClay()) {
/* 141 */         ticks /= 4;
/* 142 */       } else if (elemental.isHardenedClay()) {
/* 143 */         ticks /= 6;
/*     */       } else {
/* 145 */         ticks /= 8;
/*     */       } 
/*     */     } 
/* 148 */     return this.theEntity.isFrenzied() ? (ticks / 2) : ticks;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float getStrVsTargetBlock() {
/* 154 */     Block block = getTargetBlock();
/*     */     
/* 156 */     if (block == null) {
/* 157 */       return 0.0F;
/*     */     }
/* 159 */     Item held_item = this.theEntity.getHeldItem();
/*     */     
/* 161 */     if (held_item instanceof ItemTool) {
/*     */       
/* 163 */       ItemTool held_tool = (ItemTool)held_item;
/*     */       
/* 165 */       return Math.max(held_tool.getStrVsBlock(block, getTargetBlockMetadata()), 1.0F);
/*     */     } 
/*     */     
/* 168 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIBreakDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */