/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityAIDoorInteract
/*     */   extends EntityAIBase
/*     */ {
/*     */   protected EntityLiving theEntity;
/*     */   protected int entityPosX;
/*     */   protected int entityPosY;
/*     */   protected int entityPosZ;
/*     */   protected Block targetDoor;
/*     */   boolean hasStoppedDoorInteraction;
/*     */   float entityPositionX;
/*     */   float entityPositionZ;
/*     */   
/*     */   public EntityAIDoorInteract(EntityLiving par1EntityLiving) {
/*  21 */     this.theEntity = par1EntityLiving;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  29 */     if (!this.theEntity.isCollidedHorizontally)
/*     */     {
/*  31 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  35 */     PathNavigate var1 = this.theEntity.getNavigator();
/*  36 */     PathEntity var2 = var1.getPath();
/*     */     
/*  38 */     if (var2 != null && !var2.isFinished() && var1.getCanBreakDoors()) {
/*     */       
/*  40 */       for (int var3 = 0; var3 < Math.min(var2.getCurrentPathIndex() + 2, var2.getCurrentPathLength()); var3++) {
/*     */         
/*  42 */         PathPoint var4 = var2.getPathPointFromIndex(var3);
/*  43 */         this.entityPosX = var4.xCoord;
/*  44 */         this.entityPosY = var4.yCoord + 1;
/*  45 */         this.entityPosZ = var4.zCoord;
/*     */         
/*  47 */         if (this.theEntity.getDistanceSq(this.entityPosX, this.theEntity.posY, this.entityPosZ) <= 2.25D) {
/*     */           
/*  49 */           this.targetDoor = findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);
/*     */           
/*  51 */           if (this.targetDoor != null)
/*     */           {
/*  53 */             return true;
/*     */           }
/*     */         } 
/*     */         
/*  57 */         this.entityPosY--;
/*     */         
/*  59 */         if (this.theEntity.getDistanceSq(this.entityPosX, this.theEntity.posY, this.entityPosZ) <= 2.25D) {
/*     */           
/*  61 */           this.targetDoor = findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);
/*     */           
/*  63 */           if (this.targetDoor != null)
/*     */           {
/*  65 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  71 */       this.entityPosX = MathHelper.floor_double(this.theEntity.posX);
/*  72 */       this.entityPosY = MathHelper.floor_double(this.theEntity.posY + 1.0D);
/*  73 */       this.entityPosZ = MathHelper.floor_double(this.theEntity.posZ);
/*  74 */       this.targetDoor = findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);
/*     */       
/*  76 */       if (this.targetDoor == null) {
/*     */         
/*  78 */         this.entityPosY--;
/*  79 */         this.targetDoor = findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);
/*     */       } 
/*     */       
/*  82 */       return (this.targetDoor != null);
/*     */     } 
/*     */ 
/*     */     
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  96 */     return !this.hasStoppedDoorInteraction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 104 */     this.hasStoppedDoorInteraction = false;
/* 105 */     this.entityPositionX = (float)((this.entityPosX + 0.5F) - this.theEntity.posX);
/* 106 */     this.entityPositionZ = (float)((this.entityPosZ + 0.5F) - this.theEntity.posZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 114 */     float var1 = (float)((this.entityPosX + 0.5F) - this.theEntity.posX);
/* 115 */     float var2 = (float)((this.entityPosZ + 0.5F) - this.theEntity.posZ);
/* 116 */     float var3 = this.entityPositionX * var1 + this.entityPositionZ * var2;
/*     */     
/* 118 */     if (var3 < 0.0F)
/*     */     {
/* 120 */       this.hasStoppedDoorInteraction = true;
/*     */     }
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
/*     */   private Block findUsableDoor(int par1, int par2, int par3) {
/* 135 */     int var4 = this.theEntity.worldObj.getBlockId(par1, par2, par3);
/* 136 */     return !PathFinder.isWoodenPortal(var4) ? null : Block.blocksList[var4];
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIDoorInteract.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */