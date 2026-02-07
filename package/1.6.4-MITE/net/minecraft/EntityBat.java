/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Calendar;
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
/*     */ public class EntityBat
/*     */   extends EntityAmbientCreature
/*     */ {
/*     */   private ChunkCoordinates currentFlightTarget;
/*     */   private float attack_waver_x;
/*     */   private float attack_waver_y;
/*     */   private float attack_waver_z;
/*     */   private int data_object_id_block_hanging_from_y;
/*     */   private boolean initial_hang_attempted;
/*     */   private int sound_cooldown;
/*     */   private int prevent_hang_countdown;
/*     */   
/*     */   public EntityBat(World par1World) {
/*  27 */     super(par1World);
/*     */     
/*  29 */     updateSize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  35 */     super.entityInit();
/*  36 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*     */     
/*  38 */     this.data_object_id_block_hanging_from_y = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Short((short)0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setBlockHangingFromY(int y) {
/*  44 */     if (onClient()) {
/*     */       
/*  46 */       Debug.setErrorMessage("setBlockHangingFromY: only meant to be called on server");
/*     */       
/*     */       return;
/*     */     } 
/*  50 */     this.dataWatcher.updateObject(this.data_object_id_block_hanging_from_y, Short.valueOf((short)y));
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
/*     */   public int getBlockHangingFromY() {
/*  65 */     return this.dataWatcher.getWatchableObjectShort(this.data_object_id_block_hanging_from_y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getScaleFactor() {
/*  71 */     return isGiantSized() ? 2.0F : 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateSize() {
/*  76 */     float scale_factor = getScaleFactor();
/*     */     
/*  78 */     setSize(0.5F * scale_factor, 0.9F * scale_factor);
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
/*     */   protected float getSoundVolume(String sound) {
/*  92 */     return super.getSoundVolume(sound) * 0.1F * getScaleFactor();
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
/*     */   protected float getSoundPitch(String sound) {
/* 106 */     return super.getSoundPitch(sound) * 0.95F / getScaleFactor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 114 */     return (getIsBatHanging() && this.rand.nextInt(4) != 0) ? null : "mob.bat.idle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 122 */     return "mob.bat.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 130 */     return "mob.bat.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void collideWithEntity(Entity par1Entity) {
/* 145 */     if (this.sound_cooldown <= 0) {
/*     */       
/* 147 */       this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/* 148 */       this.sound_cooldown = this.rand.nextInt(31) + 30;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void collideWithNearbyEntities() {
/* 158 */     if (isVampireBat() || isNightwing()) {
/* 159 */       super.collideWithNearbyEntities();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 164 */     super.applyEntityAttributes();
/*     */ 
/*     */     
/* 167 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(3.0D * getScaleFactor());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 172 */     if (isVampireBat()) {
/* 173 */       return isGiantVampireBat() ? 10 : 5;
/*     */     }
/* 175 */     if (isNightwing()) {
/* 176 */       return 10;
/*     */     }
/* 178 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsBatHanging() {
/* 183 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsBatHanging(boolean par1) {
/* 188 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 190 */     if (par1) {
/*     */       
/* 192 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x1)));
/*     */       
/* 194 */       setHangingPosition();
/*     */     }
/*     */     else {
/*     */       
/* 198 */       if (getIsBatHanging()) {
/* 199 */         this.prevent_hang_countdown = 60;
/*     */       }
/* 201 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAIEnabled() {
/* 210 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 218 */     super.onUpdate();
/*     */     
/* 220 */     if (this.sound_cooldown > 0) {
/* 221 */       this.sound_cooldown--;
/*     */     }
/* 223 */     if (this.prevent_hang_countdown > 0) {
/* 224 */       this.prevent_hang_countdown--;
/*     */     }
/* 226 */     if (onServer() && this.hurtResistantTime > 0 && getIsBatHanging() && getBlockHangingFromY() >= 0) {
/*     */       
/* 228 */       setIsBatHanging(false);
/* 229 */       this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */       
/* 231 */       setPosition(this.posX, (getBlockHangingFromY() - this.height - 0.01F), this.posZ);
/*     */     } 
/*     */ 
/*     */     
/* 235 */     if (getIsBatHanging() && getBlockHangingFromY() >= 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 240 */       setHangingPosition();
/*     */     }
/*     */     else {
/*     */       
/* 244 */       this.motionY *= 0.6000000238418579D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canBatHangFromCurrentPosition() {
/* 250 */     return (getNewBlockHangingFromY() >= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHangFromBlock(int x, int y, int z) {
/* 256 */     Block block = this.worldObj.getBlock(x, y, z);
/*     */     
/* 258 */     if (block == null || block == Block.glass) {
/* 259 */       return false;
/*     */     }
/* 261 */     if (isGiantSized() && !this.worldObj.isAirBlock(x, y - 2, z)) {
/* 262 */       return false;
/*     */     }
/* 264 */     if (block.isAlwaysSolidStandardFormCube() || block.isFaceFlatAndSolid(this.worldObj.getBlockMetadata(x, y, z), EnumFace.BOTTOM)) {
/* 265 */       return this.worldObj.isAirBlock(x, y - 1, z);
/*     */     }
/* 267 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getNewBlockHangingFromY() {
/* 273 */     int y_of_block_hanging_from = (int)(this.posY + 0.11999999731779099D + this.height);
/*     */     
/* 275 */     if (canHangFromBlock(getBlockPosX(), y_of_block_hanging_from, getBlockPosZ())) {
/* 276 */       return y_of_block_hanging_from;
/*     */     }
/* 278 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setHangingPosition() {
/* 283 */     if (!getIsBatHanging()) {
/*     */       
/* 285 */       Debug.setErrorMessage("setHangingPosition: bat is not hanging");
/*     */       
/*     */       return;
/*     */     } 
/* 289 */     if (getBlockHangingFromY() < 0) {
/*     */       
/* 291 */       Debug.setErrorMessage("setHangingPosition: block hanging from y not valid on " + this.worldObj.getClientOrServerString());
/*     */       
/*     */       return;
/*     */     } 
/* 295 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/*     */     
/* 297 */     this.posY = (getBlockHangingFromY() - this.height);
/* 298 */     this.posY -= isGiantSized() ? 0.11749999970197678D : 0.0062500000931322575D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isPreventedFromHanging() {
/* 304 */     return (this.prevent_hang_countdown > 0 || getAttackTarget() != null || this.hurtResistantTime > 0 || this.worldObj.getClosestPlayerToEntity(this, 4.0D, true) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/* 309 */     super.updateAITasks();
/*     */     
/* 311 */     if (getIsBatHanging()) {
/*     */       
/* 313 */       int y_of_block_hanging_from = getNewBlockHangingFromY();
/* 314 */       setBlockHangingFromY(y_of_block_hanging_from);
/*     */ 
/*     */       
/* 317 */       if (y_of_block_hanging_from < 0) {
/*     */         
/* 319 */         setIsBatHanging(false);
/* 320 */         this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */       }
/*     */       else {
/*     */         
/* 324 */         if (this.rand.nextInt(200) == 0)
/*     */         {
/* 326 */           this.rotationYawHead = this.rand.nextInt(360);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 331 */         if (isPreventedFromHanging() || this.rand.nextInt(1000) == 0)
/*     */         {
/* 333 */           setIsBatHanging(false);
/* 334 */           this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */           
/* 336 */           setPosition(this.posX, (y_of_block_hanging_from - this.height - 0.01F), this.posZ);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 342 */       if (this.currentFlightTarget != null && (!this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) || this.currentFlightTarget.posY < 1))
/*     */       {
/* 344 */         this.currentFlightTarget = null;
/*     */       }
/*     */       
/* 347 */       int x = getBlockPosX();
/* 348 */       int z = getBlockPosZ();
/*     */ 
/*     */       
/* 351 */       if (this.currentFlightTarget == null || this.rand.nextInt(30) == 0 || this.currentFlightTarget.getDistanceSquared(x, (int)this.posY, z) < 4.0F) {
/*     */ 
/*     */ 
/*     */         
/* 355 */         int range_xz = (int)(7.0F * getScaleFactor());
/*     */         
/* 357 */         this.currentFlightTarget = new ChunkCoordinates(x + this.rand.nextInt(range_xz) - this.rand.nextInt(range_xz), (int)this.posY + this.rand.nextInt(6) - 2, z + this.rand.nextInt(range_xz) - this.rand.nextInt(range_xz));
/*     */       } 
/*     */       
/* 360 */       if (this.currentFlightTarget.posY < 1) {
/* 361 */         this.currentFlightTarget.posY = 1;
/*     */       }
/* 363 */       double var1 = this.currentFlightTarget.posX + 0.5D - this.posX;
/* 364 */       double var3 = this.currentFlightTarget.posY + 0.1D - this.posY;
/* 365 */       double var5 = this.currentFlightTarget.posZ + 0.5D - this.posZ;
/*     */       
/* 367 */       EntityLivingBase attack_target = getAttackTarget();
/*     */       
/* 369 */       if (attack_target != null) {
/*     */         
/* 371 */         Vec3 eye_pos = attack_target.getEyePos();
/*     */         
/* 373 */         if (eye_pos.yCoord + this.attack_waver_y >= 0.5D) {
/*     */           
/* 375 */           var1 = eye_pos.xCoord + this.attack_waver_x - this.posX;
/* 376 */           var3 = eye_pos.yCoord + this.attack_waver_y - this.posY;
/* 377 */           var5 = eye_pos.zCoord + this.attack_waver_z - this.posZ;
/*     */         } 
/*     */         
/* 380 */         if (getTicksExistedWithOffset() % 20 == 0) {
/*     */           
/* 382 */           this.attack_waver_x = this.rand.nextFloat() - 0.5F;
/* 383 */           this.attack_waver_y = this.rand.nextFloat() - 0.5F;
/* 384 */           this.attack_waver_z = this.rand.nextFloat() - 0.5F;
/*     */         } 
/*     */       } 
/*     */       
/* 388 */       this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.10000000149011612D;
/* 389 */       this.motionY += (Math.signum(var3) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
/* 390 */       this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.10000000149011612D;
/* 391 */       float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
/* 392 */       float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
/* 393 */       this.moveForward = 0.5F;
/* 394 */       this.rotationYaw += var8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 402 */       if (!isPreventedFromHanging() && this.rand.nextInt(this.initial_hang_attempted ? 10 : 1) == 0) {
/*     */         
/* 404 */         int y_of_block_hanging_from = getNewBlockHangingFromY();
/*     */         
/* 406 */         if (y_of_block_hanging_from >= 0) {
/*     */           
/* 408 */           setBlockHangingFromY(y_of_block_hanging_from);
/* 409 */           setIsBatHanging(true);
/*     */         } 
/*     */       } 
/*     */       
/* 413 */       this.initial_hang_attempted = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 423 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fall(float par1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateFallState(double par1, boolean par3) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesEntityNotTriggerPressurePlate() {
/* 442 */     return true;
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 467 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 476 */     if (result == null) {
/* 477 */       return result;
/*     */     }
/* 479 */     if (getIsBatHanging()) {
/*     */       
/* 481 */       setIsBatHanging(false);
/* 482 */       result.setEntityWasAffected();
/*     */     } 
/*     */     
/* 485 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 493 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 494 */     this.dataWatcher.updateObject(16, Byte.valueOf(par1NBTTagCompound.getByte("BatFlags")));
/*     */     
/* 496 */     this.initial_hang_attempted = par1NBTTagCompound.getBoolean("initial_hang_attempted");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 504 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 505 */     par1NBTTagCompound.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
/*     */     
/* 507 */     par1NBTTagCompound.setBoolean("initial_hang_attempted", this.initial_hang_attempted);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 516 */     int var1 = MathHelper.floor_double(this.boundingBox.minY);
/*     */ 
/*     */     
/* 519 */     if (this.worldObj.isOverworld() && var1 >= 63)
/*     */     {
/* 521 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 525 */     int var2 = MathHelper.floor_double(this.posX);
/* 526 */     int var3 = MathHelper.floor_double(this.posZ);
/* 527 */     int var4 = this.worldObj.getBlockLightValue(var2, var1, var3);
/* 528 */     byte var5 = 4;
/* 529 */     Calendar var6 = this.worldObj.getCurrentDate();
/*     */     
/* 531 */     if ((var6.get(2) + 1 != 10 || var6.get(5) < 20) && (var6.get(2) + 1 != 11 || var6.get(5) > 3)) {
/*     */       
/* 533 */       if (this.rand.nextBoolean())
/*     */       {
/* 535 */         return false;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 540 */       var5 = 7;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 545 */     while (var1 > 0 && !this.worldObj.isBlockStandardFormOpaqueCube(var2, --var1, var3)) {
/*     */       
/* 547 */       int blv = this.worldObj.getBlockLightValue(var2, var1, var3);
/*     */       
/* 549 */       if (blv > var4) {
/* 550 */         var4 = blv;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 556 */     return (var4 > this.rand.nextInt(var5)) ? false : super.getCanSpawnHere(perform_light_check);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVampireBat() {
/* 563 */     return this instanceof EntityVampireBat;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGiantVampireBat() {
/* 568 */     return this instanceof EntityGiantVampireBat;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGiantSized() {
/* 573 */     return isGiantVampireBat();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNightwing() {
/* 578 */     return this instanceof EntityNightwing;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInitialHangAttempted() {
/* 583 */     this.initial_hang_attempted = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */