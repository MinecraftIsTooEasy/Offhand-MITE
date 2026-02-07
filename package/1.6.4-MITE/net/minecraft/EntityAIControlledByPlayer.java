/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIControlledByPlayer
/*     */   extends EntityAIBase
/*     */ {
/*     */   private final EntityLiving thisEntity;
/*     */   private final float maxSpeed;
/*     */   private float currentSpeed;
/*     */   private boolean speedBoosted;
/*     */   private int speedBoostTime;
/*     */   private int maxSpeedBoostTime;
/*     */   
/*     */   public EntityAIControlledByPlayer(EntityLiving par1EntityLiving, float par2) {
/*  22 */     this.thisEntity = par1EntityLiving;
/*  23 */     this.maxSpeed = par2;
/*  24 */     setMutexBits(7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  32 */     this.currentSpeed = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  40 */     this.speedBoosted = false;
/*  41 */     this.currentSpeed = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  49 */     return (this.thisEntity.isEntityAlive() && this.thisEntity.riddenByEntity != null && this.thisEntity.riddenByEntity instanceof EntityPlayer && (this.speedBoosted || this.thisEntity.canBeSteered()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  57 */     EntityPlayer var1 = (EntityPlayer)this.thisEntity.riddenByEntity;
/*  58 */     EntityCreature var2 = (EntityCreature)this.thisEntity;
/*  59 */     float var3 = MathHelper.wrapAngleTo180_float(var1.rotationYaw - this.thisEntity.rotationYaw) * 0.5F;
/*     */     
/*  61 */     if (var3 > 5.0F)
/*     */     {
/*  63 */       var3 = 5.0F;
/*     */     }
/*     */     
/*  66 */     if (var3 < -5.0F)
/*     */     {
/*  68 */       var3 = -5.0F;
/*     */     }
/*     */     
/*  71 */     this.thisEntity.rotationYaw = MathHelper.wrapAngleTo180_float(this.thisEntity.rotationYaw + var3);
/*     */     
/*  73 */     if (this.currentSpeed < this.maxSpeed)
/*     */     {
/*  75 */       this.currentSpeed += (this.maxSpeed - this.currentSpeed) * 0.01F;
/*     */     }
/*     */     
/*  78 */     if (this.currentSpeed > this.maxSpeed)
/*     */     {
/*  80 */       this.currentSpeed = this.maxSpeed;
/*     */     }
/*     */     
/*  83 */     int var4 = MathHelper.floor_double(this.thisEntity.posX);
/*  84 */     int var5 = MathHelper.floor_double(this.thisEntity.posY);
/*  85 */     int var6 = MathHelper.floor_double(this.thisEntity.posZ);
/*  86 */     float var7 = this.currentSpeed;
/*     */     
/*  88 */     if (this.speedBoosted) {
/*     */       
/*  90 */       if (this.speedBoostTime++ > this.maxSpeedBoostTime)
/*     */       {
/*  92 */         this.speedBoosted = false;
/*     */       }
/*     */       
/*  95 */       var7 += var7 * 1.15F * MathHelper.sin(this.speedBoostTime / this.maxSpeedBoostTime * 3.1415927F);
/*     */     } 
/*     */     
/*  98 */     float var8 = 0.91F;
/*     */     
/* 100 */     if (this.thisEntity.onGround) {
/*     */       
/* 102 */       var8 = 0.54600006F;
/* 103 */       int var9 = this.thisEntity.worldObj.getBlockId(MathHelper.floor_float(var4), MathHelper.floor_float(var5) - 1, MathHelper.floor_float(var6));
/*     */       
/* 105 */       if (var9 > 0)
/*     */       {
/* 107 */         var8 = (Block.blocksList[var9]).slipperiness * 0.91F;
/*     */       }
/*     */     } 
/*     */     
/* 111 */     float var23 = 0.16277136F / var8 * var8 * var8;
/* 112 */     float var10 = MathHelper.sin(var2.rotationYaw * 3.1415927F / 180.0F);
/* 113 */     float var11 = MathHelper.cos(var2.rotationYaw * 3.1415927F / 180.0F);
/* 114 */     float var12 = var2.getAIMoveSpeed() * var23;
/* 115 */     float var13 = Math.max(var7, 1.0F);
/* 116 */     var13 = var12 / var13;
/* 117 */     float var14 = var7 * var13;
/* 118 */     float var15 = -(var14 * var10);
/* 119 */     float var16 = var14 * var11;
/*     */     
/* 121 */     if (MathHelper.abs(var15) > MathHelper.abs(var16)) {
/*     */       
/* 123 */       if (var15 < 0.0F)
/*     */       {
/* 125 */         var15 -= this.thisEntity.width / 2.0F;
/*     */       }
/*     */       
/* 128 */       if (var15 > 0.0F)
/*     */       {
/* 130 */         var15 += this.thisEntity.width / 2.0F;
/*     */       }
/*     */       
/* 133 */       var16 = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 137 */       var15 = 0.0F;
/*     */       
/* 139 */       if (var16 < 0.0F)
/*     */       {
/* 141 */         var16 -= this.thisEntity.width / 2.0F;
/*     */       }
/*     */       
/* 144 */       if (var16 > 0.0F)
/*     */       {
/* 146 */         var16 += this.thisEntity.width / 2.0F;
/*     */       }
/*     */     } 
/*     */     
/* 150 */     int var17 = MathHelper.floor_double(this.thisEntity.posX + var15);
/* 151 */     int var18 = MathHelper.floor_double(this.thisEntity.posZ + var16);
/* 152 */     PathPoint var19 = new PathPoint(MathHelper.floor_float(this.thisEntity.width + 1.0F), MathHelper.floor_float(this.thisEntity.height + var1.height + 1.0F), MathHelper.floor_float(this.thisEntity.width + 1.0F));
/*     */     
/* 154 */     if (var4 != var17 || var6 != var18) {
/*     */       
/* 156 */       int var20 = this.thisEntity.worldObj.getBlockId(var4, var5, var6);
/* 157 */       int var21 = this.thisEntity.worldObj.getBlockId(var4, var5 - 1, var6);
/*     */       
/* 159 */       boolean var22 = (func_98216_b(var20, var4, var5, var6) || (Block.blocksList[var20] == null && func_98216_b(var21, var4, var5 - 1, var6)));
/*     */       
/* 161 */       if (!var22 && PathFinder.func_82565_a(this.thisEntity, var17, var5, var18, var19, false, false, true) == 0 && PathFinder.func_82565_a(this.thisEntity, var4, var5 + 1, var6, var19, false, false, true) == 1 && PathFinder.func_82565_a(this.thisEntity, var17, var5 + 1, var18, var19, false, false, true) == 1)
/*     */       {
/* 163 */         var2.getJumpHelper().setJumping();
/*     */       }
/*     */     } 
/*     */     
/* 167 */     if (!var1.capabilities.isCreativeMode && this.currentSpeed >= this.maxSpeed * 0.5F && this.thisEntity.getRNG().nextFloat() < 0.006F && !this.speedBoosted) {
/*     */       
/* 169 */       ItemStack var24 = var1.getHeldItemStack();
/*     */ 
/*     */       
/* 172 */       if (var24 != null && var24.getItem() instanceof ItemCarrotOnAStick)
/*     */       {
/*     */         
/* 175 */         var24.tryDamageItem(DamageSource.pig_nibble, 1, var1);
/*     */       }
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
/* 187 */     this.thisEntity.moveEntityWithHeading(0.0F, var7);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_98216_b(int par1) {
/* 192 */     return (Block.blocksList[par1] != null && (Block.blocksList[par1].getRenderType() == 10 || Block.blocksList[par1] instanceof BlockSlab));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_98216_b(int block_id, int x, int y, int z) {
/* 197 */     Block block = Block.getBlock(block_id);
/*     */     
/* 199 */     if (block == null) {
/* 200 */       return false;
/*     */     }
/* 202 */     if (block.getRenderType() == 10) {
/* 203 */       return true;
/*     */     }
/* 205 */     return block.isSingleSlabLower(this.thisEntity.worldObj.getBlockMetadata(x, y, z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSpeedBoosted() {
/* 213 */     return this.speedBoosted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void boostSpeed() {
/* 221 */     this.speedBoosted = true;
/* 222 */     this.speedBoostTime = 0;
/* 223 */     this.maxSpeedBoostTime = this.thisEntity.getRNG().nextInt(841) + 140;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isControlledByPlayer() {
/* 231 */     return (!isSpeedBoosted() && this.currentSpeed > this.maxSpeed * 0.3F);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIControlledByPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */