/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class EntityHanging
/*     */   extends Entity
/*     */ {
/*     */   private int tickCounter1;
/*     */   public int hangingDirection;
/*     */   public int xPosition;
/*     */   public int yPosition;
/*     */   public int zPosition;
/*     */   
/*     */   public EntityHanging(World par1World) {
/*  16 */     super(par1World);
/*  17 */     this.yOffset = 0.0F;
/*  18 */     setSize(0.5F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityHanging(World par1World, int par2, int par3, int par4, int par5) {
/*  23 */     this(par1World);
/*  24 */     this.xPosition = par2;
/*  25 */     this.yPosition = par3;
/*  26 */     this.zPosition = par4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */   
/*     */   public void setDirection(int par1) {
/*  35 */     this.hangingDirection = par1;
/*  36 */     this.prevRotationYaw = this.rotationYaw = (par1 * 90);
/*  37 */     float var2 = getWidthPixels();
/*  38 */     float var3 = getHeightPixels();
/*  39 */     float var4 = getWidthPixels();
/*     */     
/*  41 */     if (par1 != 2 && par1 != 0) {
/*     */       
/*  43 */       var2 = 0.5F;
/*     */     }
/*     */     else {
/*     */       
/*  47 */       var4 = 0.5F;
/*  48 */       this.rotationYaw = this.prevRotationYaw = (Direction.rotateOpposite[par1] * 90);
/*     */     } 
/*     */     
/*  51 */     var2 /= 32.0F;
/*  52 */     var3 /= 32.0F;
/*  53 */     var4 /= 32.0F;
/*  54 */     float var5 = this.xPosition + 0.5F;
/*  55 */     float var6 = this.yPosition + 0.5F;
/*  56 */     float var7 = this.zPosition + 0.5F;
/*  57 */     float var8 = 0.5625F;
/*     */     
/*  59 */     if (par1 == 2)
/*     */     {
/*  61 */       var7 -= var8;
/*     */     }
/*     */     
/*  64 */     if (par1 == 1)
/*     */     {
/*  66 */       var5 -= var8;
/*     */     }
/*     */     
/*  69 */     if (par1 == 0)
/*     */     {
/*  71 */       var7 += var8;
/*     */     }
/*     */     
/*  74 */     if (par1 == 3)
/*     */     {
/*  76 */       var5 += var8;
/*     */     }
/*     */     
/*  79 */     if (par1 == 2)
/*     */     {
/*  81 */       var5 -= func_70517_b(getWidthPixels());
/*     */     }
/*     */     
/*  84 */     if (par1 == 1)
/*     */     {
/*  86 */       var7 += func_70517_b(getWidthPixels());
/*     */     }
/*     */     
/*  89 */     if (par1 == 0)
/*     */     {
/*  91 */       var5 += func_70517_b(getWidthPixels());
/*     */     }
/*     */     
/*  94 */     if (par1 == 3)
/*     */     {
/*  96 */       var7 -= func_70517_b(getWidthPixels());
/*     */     }
/*     */     
/*  99 */     var6 += func_70517_b(getHeightPixels());
/* 100 */     setPosition(var5, var6, var7);
/* 101 */     float var9 = -0.03125F;
/* 102 */     this.boundingBox.setBounds((var5 - var2 - var9), (var6 - var3 - var9), (var7 - var4 - var9), (var5 + var2 + var9), (var6 + var3 + var9), (var7 + var4 + var9));
/*     */   }
/*     */ 
/*     */   
/*     */   private float func_70517_b(int par1) {
/* 107 */     return (par1 == 32) ? 0.5F : ((par1 == 64) ? 0.5F : 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 115 */     this.prevPosX = this.posX;
/* 116 */     this.prevPosY = this.posY;
/* 117 */     this.prevPosZ = this.posZ;
/*     */     
/* 119 */     if (this.tickCounter1++ == 100 && !this.worldObj.isRemote) {
/*     */       
/* 121 */       this.tickCounter1 = 0;
/*     */       
/* 123 */       if (!this.isDead && !onValidSurface()) {
/*     */         
/* 125 */         setDead();
/* 126 */         onBroken((Entity)null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onValidSurface() {
/*     */     Entity var11;
/* 136 */     if (!this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty())
/*     */     {
/* 138 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 142 */     int var1 = Math.max(1, getWidthPixels() / 16);
/* 143 */     int var2 = Math.max(1, getHeightPixels() / 16);
/* 144 */     int var3 = this.xPosition;
/* 145 */     int var4 = this.yPosition;
/* 146 */     int var5 = this.zPosition;
/*     */     
/* 148 */     if (this.hangingDirection == 2)
/*     */     {
/* 150 */       var3 = MathHelper.floor_double(this.posX - (getWidthPixels() / 32.0F));
/*     */     }
/*     */     
/* 153 */     if (this.hangingDirection == 1)
/*     */     {
/* 155 */       var5 = MathHelper.floor_double(this.posZ - (getWidthPixels() / 32.0F));
/*     */     }
/*     */     
/* 158 */     if (this.hangingDirection == 0)
/*     */     {
/* 160 */       var3 = MathHelper.floor_double(this.posX - (getWidthPixels() / 32.0F));
/*     */     }
/*     */     
/* 163 */     if (this.hangingDirection == 3)
/*     */     {
/* 165 */       var5 = MathHelper.floor_double(this.posZ - (getWidthPixels() / 32.0F));
/*     */     }
/*     */     
/* 168 */     EnumFace face = (this.hangingDirection == 0) ? EnumFace.SOUTH : ((this.hangingDirection == 1) ? EnumFace.WEST : ((this.hangingDirection == 2) ? EnumFace.NORTH : EnumFace.EAST));
/*     */     
/* 170 */     var4 = MathHelper.floor_double(this.posY - (getHeightPixels() / 32.0F));
/*     */     
/* 172 */     for (int var6 = 0; var6 < var1; var6++) {
/*     */       
/* 174 */       for (int var7 = 0; var7 < var2; var7++) {
/*     */         Block block;
/*     */         
/*     */         int metadata;
/*     */         
/*     */         Material var8;
/*     */         
/* 181 */         if (this.hangingDirection != 2 && this.hangingDirection != 0) {
/*     */           
/* 183 */           block = this.worldObj.getBlock(this.xPosition, var4 + var7, var5 + var6);
/* 184 */           metadata = this.worldObj.getBlockMetadata(this.xPosition, var4 + var7, var5 + var6);
/*     */           
/* 186 */           var8 = this.worldObj.getBlockMaterial(this.xPosition, var4 + var7, var5 + var6);
/*     */         }
/*     */         else {
/*     */           
/* 190 */           block = this.worldObj.getBlock(var3 + var6, var4 + var7, this.zPosition);
/* 191 */           metadata = this.worldObj.getBlockMetadata(var3 + var6, var4 + var7, this.zPosition);
/*     */           
/* 193 */           var8 = this.worldObj.getBlockMaterial(var3 + var6, var4 + var7, this.zPosition);
/*     */         } 
/*     */         
/* 196 */         if (!var8.isSolid())
/*     */         {
/* 198 */           return false;
/*     */         }
/*     */         
/* 201 */         if (!block.isFaceFlatAndSolid(metadata, face)) {
/* 202 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 206 */     List var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox);
/* 207 */     Iterator<Entity> var10 = var9.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 212 */       if (!var10.hasNext())
/*     */       {
/* 214 */         return true;
/*     */       }
/*     */       
/* 217 */       var11 = var10.next();
/*     */     }
/* 219 */     while (!(var11 instanceof EntityHanging));
/*     */     
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 230 */     return true;
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 281 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 287 */     if (result == null) {
/* 288 */       return result;
/*     */     }
/* 290 */     setDead();
/* 291 */     setBeenAttacked();
/* 292 */     onBroken(damage.getResponsibleEntity());
/*     */     
/* 294 */     return result.setEntityWasDestroyed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveEntity(double par1, double par3, double par5) {
/* 302 */     if (!this.worldObj.isRemote && !this.isDead && par1 * par1 + par3 * par3 + par5 * par5 > 0.0D) {
/*     */       
/* 304 */       setDead();
/* 305 */       onBroken((Entity)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVelocity(double par1, double par3, double par5) {
/* 314 */     if (!this.worldObj.isRemote && !this.isDead && par1 * par1 + par3 * par3 + par5 * par5 > 0.0D) {
/*     */       
/* 316 */       setDead();
/* 317 */       onBroken((Entity)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 326 */     par1NBTTagCompound.setByte("Direction", (byte)this.hangingDirection);
/* 327 */     par1NBTTagCompound.setInteger("TileX", this.xPosition);
/* 328 */     par1NBTTagCompound.setInteger("TileY", this.yPosition);
/* 329 */     par1NBTTagCompound.setInteger("TileZ", this.zPosition);
/*     */     
/* 331 */     switch (this.hangingDirection) {
/*     */       
/*     */       case 0:
/* 334 */         par1NBTTagCompound.setByte("Dir", (byte)2);
/*     */         break;
/*     */       
/*     */       case 1:
/* 338 */         par1NBTTagCompound.setByte("Dir", (byte)1);
/*     */         break;
/*     */       
/*     */       case 2:
/* 342 */         par1NBTTagCompound.setByte("Dir", (byte)0);
/*     */         break;
/*     */       
/*     */       case 3:
/* 346 */         par1NBTTagCompound.setByte("Dir", (byte)3);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 355 */     if (par1NBTTagCompound.hasKey("Direction")) {
/*     */       
/* 357 */       this.hangingDirection = par1NBTTagCompound.getByte("Direction");
/*     */     }
/*     */     else {
/*     */       
/* 361 */       switch (par1NBTTagCompound.getByte("Dir")) {
/*     */         
/*     */         case 0:
/* 364 */           this.hangingDirection = 2;
/*     */           break;
/*     */         
/*     */         case 1:
/* 368 */           this.hangingDirection = 1;
/*     */           break;
/*     */         
/*     */         case 2:
/* 372 */           this.hangingDirection = 0;
/*     */           break;
/*     */         
/*     */         case 3:
/* 376 */           this.hangingDirection = 3;
/*     */           break;
/*     */       } 
/*     */     } 
/* 380 */     this.xPosition = par1NBTTagCompound.getInteger("TileX");
/* 381 */     this.yPosition = par1NBTTagCompound.getInteger("TileY");
/* 382 */     this.zPosition = par1NBTTagCompound.getInteger("TileZ");
/* 383 */     setDirection(this.hangingDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getWidthPixels();
/*     */ 
/*     */   
/*     */   public abstract int getHeightPixels();
/*     */ 
/*     */   
/*     */   public abstract void onBroken(Entity paramEntity);
/*     */ 
/*     */   
/*     */   protected boolean shouldSetPosAfterLoading() {
/* 397 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCollisionBorderSize(Entity for_raycast_from_this_entity) {
/* 402 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityHanging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */