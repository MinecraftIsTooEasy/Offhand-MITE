/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import org.apache.commons.io.IOUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class EntityMinecart
/*      */   extends Entity
/*      */ {
/*      */   private boolean isInReverse;
/*      */   private final IUpdatePlayerListBox field_82344_g;
/*      */   private String entityName;
/*   18 */   private static final int[][][] matrix = new int[][][] { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1, 0, 0 }, { 1, 0, 0 } }, { { -1, -1, 0 }, { 1, 0, 0 } }, { { -1, 0, 0 }, { 1, -1, 0 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1, 0, 0 } }, { { 0, 0, 1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { 1, 0, 0 } } };
/*      */   
/*      */   private int turnProgress;
/*      */   
/*      */   private double minecartX;
/*      */   
/*      */   private double minecartY;
/*      */   private double minecartZ;
/*      */   private double minecartYaw;
/*      */   private double minecartPitch;
/*      */   private double velocityX;
/*      */   private double velocityY;
/*      */   private double velocityZ;
/*      */   
/*      */   public EntityMinecart(World par1World) {
/*   33 */     super(par1World);
/*   34 */     this.preventEntitySpawning = true;
/*   35 */     setSize(0.98F, 0.7F);
/*   36 */     this.yOffset = this.height / 2.0F;
/*   37 */     this.field_82344_g = (par1World != null) ? par1World.getMinecartSoundUpdater(this) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EntityMinecart createMinecart(World par0World, double par1, double par3, double par5, int par7) {
/*   48 */     switch (par7) {
/*      */       
/*      */       case 1:
/*   51 */         return new EntityMinecartChest(par0World, par1, par3, par5);
/*      */       
/*      */       case 2:
/*   54 */         return new EntityMinecartFurnace(par0World, par1, par3, par5);
/*      */       
/*      */       case 3:
/*   57 */         return new EntityMinecartTNT(par0World, par1, par3, par5);
/*      */       
/*      */       case 4:
/*   60 */         return new EntityMinecartMobSpawner(par0World, par1, par3, par5);
/*      */       
/*      */       case 5:
/*   63 */         return new EntityMinecartHopper(par0World, par1, par3, par5);
/*      */     } 
/*      */     
/*   66 */     return new EntityMinecartEmpty(par0World, par1, par3, par5);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canTriggerWalking() {
/*   76 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*   81 */     this.dataWatcher.addObject(17, new Integer(0));
/*   82 */     this.dataWatcher.addObject(18, new Integer(1));
/*   83 */     this.dataWatcher.addObject(19, new Float(0.0F));
/*   84 */     this.dataWatcher.addObject(20, new Integer(0));
/*   85 */     this.dataWatcher.addObject(21, new Integer(6));
/*   86 */     this.dataWatcher.addObject(22, Byte.valueOf((byte)0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getCollisionBox(Entity par1Entity) {
/*   95 */     return par1Entity.canBePushed() ? par1Entity.boundingBox : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getBoundingBox() {
/*  103 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePushed() {
/*  111 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityMinecart(World par1World, double par2, double par4, double par6) {
/*  116 */     this(par1World);
/*  117 */     setPosition(par2, par4, par6);
/*  118 */     this.motionX = 0.0D;
/*  119 */     this.motionY = 0.0D;
/*  120 */     this.motionZ = 0.0D;
/*  121 */     this.prevPosX = par2;
/*  122 */     this.prevPosY = par4;
/*  123 */     this.prevPosZ = par6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMountedYOffset() {
/*  131 */     return this.height * 0.0D - 0.30000001192092896D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityDamageResult attackEntityFrom(Damage damage) {
/*  225 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*      */ 
/*      */ 
/*      */     
/*  229 */     if (result == null || result.entityWasDestroyed()) {
/*  230 */       return result;
/*      */     }
/*  232 */     if (damage.isLavaDamage()) {
/*      */       
/*  234 */       setDead();
/*  235 */       return result.setEntityWasDestroyed();
/*      */     } 
/*      */     
/*  238 */     setRollingDirection(-getRollingDirection());
/*  239 */     setRollingAmplitude(10);
/*  240 */     setBeenAttacked();
/*      */     
/*  242 */     result.setEntityWasAffected();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  248 */     result.startTrackingHealth(getDamage());
/*  249 */     setDamage(getDamage() + damage.getAmount() * 10.0F);
/*  250 */     result.finishTrackingHealth(getDamage());
/*      */ 
/*      */     
/*  253 */     boolean var3 = damage.wasCausedByPlayerInCreative();
/*      */     
/*  255 */     if (var3 || getDamage() > 40.0F) {
/*      */       
/*  257 */       if (this.riddenByEntity != null) {
/*  258 */         this.riddenByEntity.mountEntity(this);
/*      */       }
/*  260 */       if (var3 && !hasCustomName()) {
/*  261 */         setDead();
/*      */       } else {
/*  263 */         killMinecart(damage.getSource());
/*      */       } 
/*  265 */       result.setEntityWasDestroyed();
/*      */     } 
/*      */     
/*  268 */     return result;
/*      */   }
/*      */   
/*  271 */   private static String[][] s = new String[][] { { "mvg.nrmvxizug.hix.XlmgzrmviKozbvi", "ee" }, { "mvg.nrmvxizug.hix.XlmgzrmviDlipyvmxs", "eo" }, { "mvg.nrmvxizug.hix.NRGVXlmhgzmg", "NRGVXlmhgzmg" }, { "mvg.nrmvxizug.hix.NRGVXlmgzrmviXizugrmt", "NRGVXlmgzrmviXizugrmt" }, { "mvg.nrmvxizug.hix.VmgrgbXorvmgKozbviNK", "ywr" }, { "mvg.nrmvxizug.hix.VmgrgbKozbvi", "fu" }, { "mvg.nrmvxizug.hix.VmgrgbKozbviHK", "yvc" }, { "mvg.nrmvxizug.hix.UllwHgzgh", "fc" }, { "mvg.nrmvxizug.hix.Nrmvxizug", "zge" }, { "mvg.nrmvxizug.hix.NlevnvmgRmkfgUilnLkgrlmh", "yvd" }, { "mvg.nrmvxizug.hix.MvgXorvmgSzmwovi", "yxd" }, { "mvg.nrmvxizug.hix.KozbviXlmgilooviNK", "ywx" }, { "mvg.nrmvxizug.hix.Kzxpvg86KozbviOllpNlev", "vd" }, { "mvg.nrmvxizug.hix.Kzxpvg72KozbviRmkfg", "uv" }, { "mvg.nrmvxizug.hix.Kzxpvg17ZwwSfmtvi", "Kzxpvg17ZwwSfmtvi" }, { "mvg.nrmvxizug.hix.Kzxpvg14HrnkovHrtmzo", "Kzxpvg14HrnkovHrtmzo" }, { "mvg.nrmvxizug.hix.Kzxpvg797KozbviZyrorgrvh", "uz" }, { "mvg.nrmvxizug.hix.KozbviXzkzyrorgrvh", "fx" }, { "mvg.nrmvxizug.hix.GxkXlmmvxgrlm", "xl" } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void killMinecart(DamageSource par1DamageSource) {
/*  296 */     setDead();
/*  297 */     ItemStack var2 = new ItemStack(Item.minecartEmpty, 1);
/*      */     
/*  299 */     if (this.entityName != null)
/*      */     {
/*  301 */       var2.setItemName(this.entityName);
/*      */     }
/*      */     
/*  304 */     dropItemStack(var2, 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void performHurtAnimation() {
/*  312 */     setRollingDirection(-getRollingDirection());
/*  313 */     setRollingAmplitude(10);
/*  314 */     setDamage(getDamage() + getDamage() * 10.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeCollidedWith() {
/*  322 */     return !this.isDead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDead() {
/*  330 */     super.setDead();
/*      */     
/*  332 */     if (this.field_82344_g != null)
/*      */     {
/*  334 */       this.field_82344_g.update();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*  343 */     if (this.field_82344_g != null)
/*      */     {
/*  345 */       this.field_82344_g.update();
/*      */     }
/*      */     
/*  348 */     if (getRollingAmplitude() > 0)
/*      */     {
/*  350 */       setRollingAmplitude(getRollingAmplitude() - 1);
/*      */     }
/*      */     
/*  353 */     if (getDamage() > 0.0F)
/*      */     {
/*  355 */       setDamage(getDamage() - 1.0F);
/*      */     }
/*      */     
/*  358 */     if (this.posY < -64.0D)
/*      */     {
/*  360 */       kill();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  365 */     if (!this.worldObj.isRemote && this.worldObj instanceof WorldServer) {
/*      */       
/*  367 */       this.worldObj.theProfiler.startSection("portal");
/*  368 */       MinecraftServer var1 = ((WorldServer)this.worldObj).getMinecraftServer();
/*  369 */       int var2 = getMaxInPortalTime();
/*      */       
/*  371 */       if (this.inPortal) {
/*      */         
/*  373 */         if (var1.getAllowNether())
/*      */         {
/*  375 */           if (this.ridingEntity == null && this.portalCounter++ >= var2) {
/*      */             
/*  377 */             this.portalCounter = var2;
/*  378 */             this.timeUntilPortal = getPortalCooldown();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  390 */             byte var3 = (byte)this.portal_destination_dimension_id;
/*      */             
/*  392 */             travelToDimension(var3);
/*      */           } 
/*      */           
/*  395 */           this.inPortal = false;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  400 */         if (this.portalCounter > 0)
/*      */         {
/*  402 */           this.portalCounter -= 4;
/*      */         }
/*      */         
/*  405 */         if (this.portalCounter < 0)
/*      */         {
/*  407 */           this.portalCounter = 0;
/*      */         }
/*      */       } 
/*      */       
/*  411 */       if (this.timeUntilPortal > 0)
/*      */       {
/*  413 */         this.timeUntilPortal--;
/*      */       }
/*      */       
/*  416 */       this.worldObj.theProfiler.endSection();
/*      */     } 
/*      */     
/*  419 */     if (this.worldObj.isRemote) {
/*      */       
/*  421 */       if (this.turnProgress > 0)
/*      */       {
/*  423 */         double var19 = this.posX + (this.minecartX - this.posX) / this.turnProgress;
/*  424 */         double var21 = this.posY + (this.minecartY - this.posY) / this.turnProgress;
/*  425 */         double var5 = this.posZ + (this.minecartZ - this.posZ) / this.turnProgress;
/*  426 */         double var7 = MathHelper.wrapAngleTo180_double(this.minecartYaw - this.rotationYaw);
/*  427 */         this.rotationYaw = (float)(this.rotationYaw + var7 / this.turnProgress);
/*  428 */         this.rotationPitch = (float)(this.rotationPitch + (this.minecartPitch - this.rotationPitch) / this.turnProgress);
/*  429 */         this.turnProgress--;
/*  430 */         setPosition(var19, var21, var5);
/*  431 */         setRotation(this.rotationYaw, this.rotationPitch);
/*      */       }
/*      */       else
/*      */       {
/*  435 */         setPosition(this.posX, this.posY, this.posZ);
/*  436 */         setRotation(this.rotationYaw, this.rotationPitch);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  441 */       this.prevPosX = this.posX;
/*  442 */       this.prevPosY = this.posY;
/*  443 */       this.prevPosZ = this.posZ;
/*  444 */       this.motionY -= 0.03999999910593033D;
/*  445 */       int var18 = MathHelper.floor_double(this.posX);
/*  446 */       int var2 = MathHelper.floor_double(this.posY);
/*  447 */       int var20 = MathHelper.floor_double(this.posZ);
/*      */       
/*  449 */       if (BlockRailBase.isRailBlockAt(this.worldObj, var18, var2 - 1, var20))
/*      */       {
/*  451 */         var2--;
/*      */       }
/*      */       
/*  454 */       double var4 = 0.4D;
/*  455 */       double var6 = 0.0078125D;
/*  456 */       int var8 = this.worldObj.getBlockId(var18, var2, var20);
/*      */       
/*  458 */       if (BlockRailBase.isRailBlock(var8)) {
/*      */         
/*  460 */         int var9 = this.worldObj.getBlockMetadata(var18, var2, var20);
/*  461 */         updateOnTrack(var18, var2, var20, var4, var6, var8, var9);
/*      */         
/*  463 */         if (var8 == Block.railActivator.blockID)
/*      */         {
/*  465 */           onActivatorRailPass(var18, var2, var20, ((var9 & 0x8) != 0));
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  470 */         func_94088_b(var4);
/*      */       } 
/*      */       
/*  473 */       doBlockCollisions();
/*  474 */       this.rotationPitch = 0.0F;
/*  475 */       double var22 = this.prevPosX - this.posX;
/*  476 */       double var11 = this.prevPosZ - this.posZ;
/*      */       
/*  478 */       if (var22 * var22 + var11 * var11 > 0.001D) {
/*      */         
/*  480 */         this.rotationYaw = (float)(Math.atan2(var11, var22) * 180.0D / Math.PI);
/*      */         
/*  482 */         if (this.isInReverse)
/*      */         {
/*  484 */           this.rotationYaw += 180.0F;
/*      */         }
/*      */       } 
/*      */       
/*  488 */       double var13 = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.prevRotationYaw);
/*      */       
/*  490 */       if (var13 < -170.0D || var13 >= 170.0D) {
/*      */         
/*  492 */         this.rotationYaw += 180.0F;
/*  493 */         this.isInReverse = !this.isInReverse;
/*      */       } 
/*      */       
/*  496 */       setRotation(this.rotationYaw, this.rotationPitch);
/*  497 */       List<Entity> var15 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
/*      */       
/*  499 */       if (var15 != null && !var15.isEmpty())
/*      */       {
/*  501 */         for (int var16 = 0; var16 < var15.size(); var16++) {
/*      */           
/*  503 */           Entity var17 = var15.get(var16);
/*      */           
/*  505 */           if (var17 != this.riddenByEntity && var17.canBePushed() && var17 instanceof EntityMinecart)
/*      */           {
/*  507 */             var17.applyEntityCollision(this);
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/*  512 */       if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
/*      */         
/*  514 */         if (this.riddenByEntity.ridingEntity == this)
/*      */         {
/*  516 */           this.riddenByEntity.ridingEntity = null;
/*      */         }
/*      */         
/*  519 */         this.riddenByEntity = null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  525 */       checkForContactWithFireAndLava();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onActivatorRailPass(int par1, int par2, int par3, boolean par4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_94088_b(double par1) {
/*  539 */     if (this.motionX < -par1)
/*      */     {
/*  541 */       this.motionX = -par1;
/*      */     }
/*      */     
/*  544 */     if (this.motionX > par1)
/*      */     {
/*  546 */       this.motionX = par1;
/*      */     }
/*      */     
/*  549 */     if (this.motionZ < -par1)
/*      */     {
/*  551 */       this.motionZ = -par1;
/*      */     }
/*      */     
/*  554 */     if (this.motionZ > par1)
/*      */     {
/*  556 */       this.motionZ = par1;
/*      */     }
/*      */     
/*  559 */     if (this.onGround) {
/*      */       
/*  561 */       this.motionX *= 0.5D;
/*  562 */       this.motionY *= 0.5D;
/*  563 */       this.motionZ *= 0.5D;
/*      */     } 
/*      */     
/*  566 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*      */     
/*  568 */     if (!this.onGround) {
/*      */       
/*  570 */       this.motionX *= 0.949999988079071D;
/*  571 */       this.motionY *= 0.949999988079071D;
/*  572 */       this.motionZ *= 0.949999988079071D;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateOnTrack(int par1, int par2, int par3, double par4, double par6, int par8, int par9) {
/*  578 */     this.fallDistance = 0.0F;
/*  579 */     Vec3 var10 = func_70489_a(this.posX, this.posY, this.posZ);
/*  580 */     this.posY = par2;
/*  581 */     boolean var11 = false;
/*  582 */     boolean var12 = false;
/*      */     
/*  584 */     if (par8 == Block.railPowered.blockID) {
/*      */       
/*  586 */       var11 = ((par9 & 0x8) != 0);
/*  587 */       var12 = !var11;
/*      */     } 
/*      */     
/*  590 */     if (((BlockRailBase)Block.blocksList[par8]).isPowered())
/*      */     {
/*  592 */       par9 &= 0x7;
/*      */     }
/*      */     
/*  595 */     if (par9 >= 2 && par9 <= 5)
/*      */     {
/*  597 */       this.posY = (par2 + 1);
/*      */     }
/*      */     
/*  600 */     if (par9 == 2)
/*      */     {
/*  602 */       this.motionX -= par6;
/*      */     }
/*      */     
/*  605 */     if (par9 == 3)
/*      */     {
/*  607 */       this.motionX += par6;
/*      */     }
/*      */     
/*  610 */     if (par9 == 4)
/*      */     {
/*  612 */       this.motionZ += par6;
/*      */     }
/*      */     
/*  615 */     if (par9 == 5)
/*      */     {
/*  617 */       this.motionZ -= par6;
/*      */     }
/*      */     
/*  620 */     int[][] var13 = matrix[par9];
/*  621 */     double var14 = (var13[1][0] - var13[0][0]);
/*  622 */     double var16 = (var13[1][2] - var13[0][2]);
/*  623 */     double var18 = Math.sqrt(var14 * var14 + var16 * var16);
/*  624 */     double var20 = this.motionX * var14 + this.motionZ * var16;
/*      */     
/*  626 */     if (var20 < 0.0D) {
/*      */       
/*  628 */       var14 = -var14;
/*  629 */       var16 = -var16;
/*      */     } 
/*      */     
/*  632 */     double var22 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*      */     
/*  634 */     if (var22 > 2.0D)
/*      */     {
/*  636 */       var22 = 2.0D;
/*      */     }
/*      */     
/*  639 */     this.motionX = var22 * var14 / var18;
/*  640 */     this.motionZ = var22 * var16 / var18;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  646 */     if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
/*      */       
/*  648 */       double d = ((EntityLivingBase)this.riddenByEntity).moveForward;
/*      */       
/*  650 */       if (d > 0.0D) {
/*      */         
/*  652 */         double d1 = -Math.sin((this.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
/*  653 */         double d2 = Math.cos((this.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
/*  654 */         double d3 = this.motionX * this.motionX + this.motionZ * this.motionZ;
/*      */         
/*  656 */         if (d3 < 0.01D) {
/*      */           
/*  658 */           this.motionX += d1 * 0.1D;
/*  659 */           this.motionZ += d2 * 0.1D;
/*  660 */           var12 = false;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  665 */     if (var12) {
/*      */       
/*  667 */       double d = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*      */       
/*  669 */       if (d < 0.03D) {
/*      */         
/*  671 */         this.motionX *= 0.0D;
/*  672 */         this.motionY *= 0.0D;
/*  673 */         this.motionZ *= 0.0D;
/*      */       }
/*      */       else {
/*      */         
/*  677 */         this.motionX *= 0.5D;
/*  678 */         this.motionY *= 0.0D;
/*  679 */         this.motionZ *= 0.5D;
/*      */       } 
/*      */     } 
/*      */     
/*  683 */     double var24 = 0.0D;
/*  684 */     double var26 = par1 + 0.5D + var13[0][0] * 0.5D;
/*  685 */     double var28 = par3 + 0.5D + var13[0][2] * 0.5D;
/*  686 */     double var30 = par1 + 0.5D + var13[1][0] * 0.5D;
/*  687 */     double var32 = par3 + 0.5D + var13[1][2] * 0.5D;
/*  688 */     var14 = var30 - var26;
/*  689 */     var16 = var32 - var28;
/*      */ 
/*      */ 
/*      */     
/*  693 */     if (var14 == 0.0D) {
/*      */       
/*  695 */       this.posX = par1 + 0.5D;
/*  696 */       var24 = this.posZ - par3;
/*      */     }
/*  698 */     else if (var16 == 0.0D) {
/*      */       
/*  700 */       this.posZ = par3 + 0.5D;
/*  701 */       var24 = this.posX - par1;
/*      */     }
/*      */     else {
/*      */       
/*  705 */       double d1 = this.posX - var26;
/*  706 */       double d2 = this.posZ - var28;
/*  707 */       var24 = (d1 * var14 + d2 * var16) * 2.0D;
/*      */     } 
/*      */     
/*  710 */     this.posX = var26 + var14 * var24;
/*  711 */     this.posZ = var28 + var16 * var24;
/*  712 */     setPosition(this.posX, this.posY + this.yOffset, this.posZ);
/*  713 */     double var34 = this.motionX;
/*  714 */     double var36 = this.motionZ;
/*      */     
/*  716 */     if (this.riddenByEntity != null) {
/*      */       
/*  718 */       var34 *= 0.75D;
/*  719 */       var36 *= 0.75D;
/*      */     } 
/*      */     
/*  722 */     if (var34 < -par4)
/*      */     {
/*  724 */       var34 = -par4;
/*      */     }
/*      */     
/*  727 */     if (var34 > par4)
/*      */     {
/*  729 */       var34 = par4;
/*      */     }
/*      */     
/*  732 */     if (var36 < -par4)
/*      */     {
/*  734 */       var36 = -par4;
/*      */     }
/*      */     
/*  737 */     if (var36 > par4)
/*      */     {
/*  739 */       var36 = par4;
/*      */     }
/*      */     
/*  742 */     moveEntity(var34, 0.0D, var36);
/*      */     
/*  744 */     if (var13[0][1] != 0 && MathHelper.floor_double(this.posX) - par1 == var13[0][0] && MathHelper.floor_double(this.posZ) - par3 == var13[0][2]) {
/*      */       
/*  746 */       setPosition(this.posX, this.posY + var13[0][1], this.posZ);
/*      */     }
/*  748 */     else if (var13[1][1] != 0 && MathHelper.floor_double(this.posX) - par1 == var13[1][0] && MathHelper.floor_double(this.posZ) - par3 == var13[1][2]) {
/*      */       
/*  750 */       setPosition(this.posX, this.posY + var13[1][1], this.posZ);
/*      */     } 
/*      */     
/*  753 */     applyDrag();
/*  754 */     Vec3 var38 = func_70489_a(this.posX, this.posY, this.posZ);
/*      */     
/*  756 */     if (var38 != null && var10 != null) {
/*      */       
/*  758 */       double var39 = (var10.yCoord - var38.yCoord) * 0.05D;
/*  759 */       var22 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*      */       
/*  761 */       if (var22 > 0.0D) {
/*      */         
/*  763 */         this.motionX = this.motionX / var22 * (var22 + var39);
/*  764 */         this.motionZ = this.motionZ / var22 * (var22 + var39);
/*      */       } 
/*      */       
/*  767 */       setPosition(this.posX, var38.yCoord, this.posZ);
/*      */     } 
/*      */     
/*  770 */     int var45 = MathHelper.floor_double(this.posX);
/*  771 */     int var40 = MathHelper.floor_double(this.posZ);
/*      */     
/*  773 */     if (var45 != par1 || var40 != par3) {
/*      */       
/*  775 */       var22 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*  776 */       this.motionX = var22 * (var45 - par1);
/*  777 */       this.motionZ = var22 * (var40 - par3);
/*      */     } 
/*      */     
/*  780 */     if (var11) {
/*      */       
/*  782 */       double var41 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*      */       
/*  784 */       if (var41 > 0.01D) {
/*      */         
/*  786 */         double var43 = 0.06D;
/*  787 */         this.motionX += this.motionX / var41 * var43;
/*  788 */         this.motionZ += this.motionZ / var41 * var43;
/*      */       }
/*  790 */       else if (par9 == 1) {
/*      */         
/*  792 */         if (this.worldObj.isBlockNormalCube(par1 - 1, par2, par3))
/*      */         {
/*  794 */           this.motionX = 0.02D;
/*      */         }
/*  796 */         else if (this.worldObj.isBlockNormalCube(par1 + 1, par2, par3))
/*      */         {
/*  798 */           this.motionX = -0.02D;
/*      */         }
/*      */       
/*  801 */       } else if (par9 == 0) {
/*      */         
/*  803 */         if (this.worldObj.isBlockNormalCube(par1, par2, par3 - 1)) {
/*      */           
/*  805 */           this.motionZ = 0.02D;
/*      */         }
/*  807 */         else if (this.worldObj.isBlockNormalCube(par1, par2, par3 + 1)) {
/*      */           
/*  809 */           this.motionZ = -0.02D;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyDrag() {
/*  817 */     if (this.riddenByEntity != null) {
/*      */       
/*  819 */       this.motionX *= 0.996999979019165D;
/*  820 */       this.motionY *= 0.0D;
/*  821 */       this.motionZ *= 0.996999979019165D;
/*      */     }
/*      */     else {
/*      */       
/*  825 */       this.motionX *= 0.9599999785423279D;
/*  826 */       this.motionY *= 0.0D;
/*  827 */       this.motionZ *= 0.9599999785423279D;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 func_70495_a(double par1, double par3, double par5, double par7) {
/*  833 */     int var9 = MathHelper.floor_double(par1);
/*  834 */     int var10 = MathHelper.floor_double(par3);
/*  835 */     int var11 = MathHelper.floor_double(par5);
/*      */     
/*  837 */     if (BlockRailBase.isRailBlockAt(this.worldObj, var9, var10 - 1, var11))
/*      */     {
/*  839 */       var10--;
/*      */     }
/*      */     
/*  842 */     int var12 = this.worldObj.getBlockId(var9, var10, var11);
/*      */     
/*  844 */     if (!BlockRailBase.isRailBlock(var12))
/*      */     {
/*  846 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  850 */     int var13 = this.worldObj.getBlockMetadata(var9, var10, var11);
/*      */     
/*  852 */     if (((BlockRailBase)Block.blocksList[var12]).isPowered())
/*      */     {
/*  854 */       var13 &= 0x7;
/*      */     }
/*      */     
/*  857 */     par3 = var10;
/*      */     
/*  859 */     if (var13 >= 2 && var13 <= 5)
/*      */     {
/*  861 */       par3 = (var10 + 1);
/*      */     }
/*      */     
/*  864 */     int[][] var14 = matrix[var13];
/*  865 */     double var15 = (var14[1][0] - var14[0][0]);
/*  866 */     double var17 = (var14[1][2] - var14[0][2]);
/*  867 */     double var19 = Math.sqrt(var15 * var15 + var17 * var17);
/*  868 */     var15 /= var19;
/*  869 */     var17 /= var19;
/*  870 */     par1 += var15 * par7;
/*  871 */     par5 += var17 * par7;
/*      */     
/*  873 */     if (var14[0][1] != 0 && MathHelper.floor_double(par1) - var9 == var14[0][0] && MathHelper.floor_double(par5) - var11 == var14[0][2]) {
/*      */       
/*  875 */       par3 += var14[0][1];
/*      */     }
/*  877 */     else if (var14[1][1] != 0 && MathHelper.floor_double(par1) - var9 == var14[1][0] && MathHelper.floor_double(par5) - var11 == var14[1][2]) {
/*      */       
/*  879 */       par3 += var14[1][1];
/*      */     } 
/*      */     
/*  882 */     return func_70489_a(par1, par3, par5);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 func_70489_a(double par1, double par3, double par5) {
/*  888 */     int var7 = MathHelper.floor_double(par1);
/*  889 */     int var8 = MathHelper.floor_double(par3);
/*  890 */     int var9 = MathHelper.floor_double(par5);
/*      */     
/*  892 */     if (BlockRailBase.isRailBlockAt(this.worldObj, var7, var8 - 1, var9))
/*      */     {
/*  894 */       var8--;
/*      */     }
/*      */     
/*  897 */     int var10 = this.worldObj.getBlockId(var7, var8, var9);
/*      */     
/*  899 */     if (BlockRailBase.isRailBlock(var10)) {
/*      */       
/*  901 */       int var11 = this.worldObj.getBlockMetadata(var7, var8, var9);
/*  902 */       par3 = var8;
/*      */       
/*  904 */       if (((BlockRailBase)Block.blocksList[var10]).isPowered())
/*      */       {
/*  906 */         var11 &= 0x7;
/*      */       }
/*      */       
/*  909 */       if (var11 >= 2 && var11 <= 5)
/*      */       {
/*  911 */         par3 = (var8 + 1);
/*      */       }
/*      */       
/*  914 */       int[][] var12 = matrix[var11];
/*  915 */       double var13 = 0.0D;
/*  916 */       double var15 = var7 + 0.5D + var12[0][0] * 0.5D;
/*  917 */       double var17 = var8 + 0.5D + var12[0][1] * 0.5D;
/*  918 */       double var19 = var9 + 0.5D + var12[0][2] * 0.5D;
/*  919 */       double var21 = var7 + 0.5D + var12[1][0] * 0.5D;
/*  920 */       double var23 = var8 + 0.5D + var12[1][1] * 0.5D;
/*  921 */       double var25 = var9 + 0.5D + var12[1][2] * 0.5D;
/*  922 */       double var27 = var21 - var15;
/*  923 */       double var29 = (var23 - var17) * 2.0D;
/*  924 */       double var31 = var25 - var19;
/*      */       
/*  926 */       if (var27 == 0.0D) {
/*      */         
/*  928 */         par1 = var7 + 0.5D;
/*  929 */         var13 = par5 - var9;
/*      */       }
/*  931 */       else if (var31 == 0.0D) {
/*      */         
/*  933 */         par5 = var9 + 0.5D;
/*  934 */         var13 = par1 - var7;
/*      */       }
/*      */       else {
/*      */         
/*  938 */         double var33 = par1 - var15;
/*  939 */         double var35 = par5 - var19;
/*  940 */         var13 = (var33 * var27 + var35 * var31) * 2.0D;
/*      */       } 
/*      */       
/*  943 */       par1 = var15 + var27 * var13;
/*  944 */       par3 = var17 + var29 * var13;
/*  945 */       par5 = var19 + var31 * var13;
/*      */       
/*  947 */       if (var29 < 0.0D)
/*      */       {
/*  949 */         par3++;
/*      */       }
/*      */       
/*  952 */       if (var29 > 0.0D)
/*      */       {
/*  954 */         par3 += 0.5D;
/*      */       }
/*      */       
/*  957 */       return this.worldObj.getWorldVec3Pool().getVecFromPool(par1, par3, par5);
/*      */     } 
/*      */ 
/*      */     
/*  961 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  970 */     if (par1NBTTagCompound.getBoolean("CustomDisplayTile")) {
/*      */       
/*  972 */       setDisplayTile(par1NBTTagCompound.getInteger("DisplayTile"));
/*  973 */       setDisplayTileData(par1NBTTagCompound.getInteger("DisplayData"));
/*  974 */       setDisplayTileOffset(par1NBTTagCompound.getInteger("DisplayOffset"));
/*      */     } 
/*      */     
/*  977 */     if (par1NBTTagCompound.hasKey("CustomName") && par1NBTTagCompound.getString("CustomName").length() > 0)
/*      */     {
/*  979 */       this.entityName = par1NBTTagCompound.getString("CustomName");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  988 */     if (hasDisplayTile()) {
/*      */       
/*  990 */       par1NBTTagCompound.setBoolean("CustomDisplayTile", true);
/*  991 */       par1NBTTagCompound.setInteger("DisplayTile", (getDisplayTile() == null) ? 0 : (getDisplayTile()).blockID);
/*  992 */       par1NBTTagCompound.setInteger("DisplayData", getDisplayTileData());
/*  993 */       par1NBTTagCompound.setInteger("DisplayOffset", getDisplayTileOffset());
/*      */     } 
/*      */     
/*  996 */     if (this.entityName != null && this.entityName.length() > 0)
/*      */     {
/*  998 */       par1NBTTagCompound.setString("CustomName", this.entityName);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public float getShadowSize() {
/* 1004 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void applyEntityCollision(Entity par1Entity) {
/* 1012 */     if (!this.worldObj.isRemote)
/*      */     {
/* 1014 */       if (par1Entity != this.riddenByEntity) {
/*      */         
/* 1016 */         if (par1Entity instanceof EntityLivingBase && !(par1Entity instanceof EntityPlayer) && !(par1Entity instanceof EntityIronGolem) && getMinecartType() == 0 && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.01D && this.riddenByEntity == null && par1Entity.ridingEntity == null)
/*      */         {
/* 1018 */           par1Entity.mountEntity(this);
/*      */         }
/*      */         
/* 1021 */         double var2 = par1Entity.posX - this.posX;
/* 1022 */         double var4 = par1Entity.posZ - this.posZ;
/* 1023 */         double var6 = var2 * var2 + var4 * var4;
/*      */         
/* 1025 */         if (var6 >= 9.999999747378752E-5D) {
/*      */           
/* 1027 */           var6 = MathHelper.sqrt_double(var6);
/* 1028 */           var2 /= var6;
/* 1029 */           var4 /= var6;
/* 1030 */           double var8 = 1.0D / var6;
/*      */           
/* 1032 */           if (var8 > 1.0D)
/*      */           {
/* 1034 */             var8 = 1.0D;
/*      */           }
/*      */           
/* 1037 */           var2 *= var8;
/* 1038 */           var4 *= var8;
/* 1039 */           var2 *= 0.10000000149011612D;
/* 1040 */           var4 *= 0.10000000149011612D;
/* 1041 */           var2 *= (1.0F - this.entityCollisionReduction);
/* 1042 */           var4 *= (1.0F - this.entityCollisionReduction);
/* 1043 */           var2 *= 0.5D;
/* 1044 */           var4 *= 0.5D;
/*      */           
/* 1046 */           if (par1Entity instanceof EntityMinecart) {
/*      */             
/* 1048 */             double var10 = par1Entity.posX - this.posX;
/* 1049 */             double var12 = par1Entity.posZ - this.posZ;
/* 1050 */             Vec3 var14 = this.worldObj.getWorldVec3Pool().getVecFromPool(var10, 0.0D, var12).normalize();
/* 1051 */             Vec3 var15 = this.worldObj.getWorldVec3Pool().getVecFromPool(MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F), 0.0D, MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F)).normalize();
/* 1052 */             double var16 = Math.abs(var14.dotProduct(var15));
/*      */             
/* 1054 */             if (var16 < 0.800000011920929D) {
/*      */               return;
/*      */             }
/*      */ 
/*      */             
/* 1059 */             double var18 = par1Entity.motionX + this.motionX;
/* 1060 */             double var20 = par1Entity.motionZ + this.motionZ;
/*      */             
/* 1062 */             if (((EntityMinecart)par1Entity).getMinecartType() == 2 && getMinecartType() != 2)
/*      */             {
/* 1064 */               this.motionX *= 0.20000000298023224D;
/* 1065 */               this.motionZ *= 0.20000000298023224D;
/* 1066 */               addVelocity(par1Entity.motionX - var2, 0.0D, par1Entity.motionZ - var4);
/* 1067 */               par1Entity.motionX *= 0.949999988079071D;
/* 1068 */               par1Entity.motionZ *= 0.949999988079071D;
/*      */             }
/* 1070 */             else if (((EntityMinecart)par1Entity).getMinecartType() != 2 && getMinecartType() == 2)
/*      */             {
/* 1072 */               par1Entity.motionX *= 0.20000000298023224D;
/* 1073 */               par1Entity.motionZ *= 0.20000000298023224D;
/* 1074 */               par1Entity.addVelocity(this.motionX + var2, 0.0D, this.motionZ + var4);
/* 1075 */               this.motionX *= 0.949999988079071D;
/* 1076 */               this.motionZ *= 0.949999988079071D;
/*      */             }
/*      */             else
/*      */             {
/* 1080 */               var18 /= 2.0D;
/* 1081 */               var20 /= 2.0D;
/* 1082 */               this.motionX *= 0.20000000298023224D;
/* 1083 */               this.motionZ *= 0.20000000298023224D;
/* 1084 */               addVelocity(var18 - var2, 0.0D, var20 - var4);
/* 1085 */               par1Entity.motionX *= 0.20000000298023224D;
/* 1086 */               par1Entity.motionZ *= 0.20000000298023224D;
/* 1087 */               par1Entity.addVelocity(var18 + var2, 0.0D, var20 + var4);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1092 */             addVelocity(-var2, 0.0D, -var4);
/* 1093 */             par1Entity.addVelocity(var2 / 4.0D, 0.0D, var4 / 4.0D);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 1106 */     this.minecartX = par1;
/* 1107 */     this.minecartY = par3;
/* 1108 */     this.minecartZ = par5;
/* 1109 */     this.minecartYaw = par7;
/* 1110 */     this.minecartPitch = par8;
/* 1111 */     this.turnProgress = par9 + 2;
/* 1112 */     this.motionX = this.velocityX;
/* 1113 */     this.motionY = this.velocityY;
/* 1114 */     this.motionZ = this.velocityZ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVelocity(double par1, double par3, double par5) {
/* 1122 */     this.velocityX = this.motionX = par1;
/* 1123 */     this.velocityY = this.motionY = par3;
/* 1124 */     this.velocityZ = this.motionZ = par5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDamage(float par1) {
/* 1133 */     this.dataWatcher.updateObject(19, Float.valueOf(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getDamage() {
/* 1142 */     return this.dataWatcher.getWatchableObjectFloat(19);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRollingAmplitude(int par1) {
/* 1150 */     this.dataWatcher.updateObject(17, Integer.valueOf(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRollingAmplitude() {
/* 1158 */     return this.dataWatcher.getWatchableObjectInt(17);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRollingDirection(int par1) {
/* 1166 */     this.dataWatcher.updateObject(18, Integer.valueOf(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRollingDirection() {
/* 1174 */     return this.dataWatcher.getWatchableObjectInt(18);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Block getDisplayTile() {
/* 1181 */     if (!hasDisplayTile())
/*      */     {
/* 1183 */       return getDefaultDisplayTile();
/*      */     }
/*      */ 
/*      */     
/* 1187 */     int var1 = getDataWatcher().getWatchableObjectInt(20) & 0xFFFF;
/* 1188 */     return (var1 > 0 && var1 < Block.blocksList.length) ? Block.blocksList[var1] : null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Block getDefaultDisplayTile() {
/* 1194 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDisplayTileData() {
/* 1199 */     return !hasDisplayTile() ? getDefaultDisplayTileData() : (getDataWatcher().getWatchableObjectInt(20) >> 16);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDefaultDisplayTileData() {
/* 1204 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDisplayTileOffset() {
/* 1209 */     return !hasDisplayTile() ? getDefaultDisplayTileOffset() : getDataWatcher().getWatchableObjectInt(21);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDefaultDisplayTileOffset() {
/* 1214 */     return 6;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisplayTile(int par1) {
/* 1219 */     getDataWatcher().updateObject(20, Integer.valueOf(par1 & 0xFFFF | getDisplayTileData() << 16));
/* 1220 */     setHasDisplayTile(true);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisplayTileData(int par1) {
/* 1225 */     Block var2 = getDisplayTile();
/* 1226 */     int var3 = (var2 == null) ? 0 : var2.blockID;
/* 1227 */     getDataWatcher().updateObject(20, Integer.valueOf(var3 & 0xFFFF | par1 << 16));
/* 1228 */     setHasDisplayTile(true);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisplayTileOffset(int par1) {
/* 1233 */     getDataWatcher().updateObject(21, Integer.valueOf(par1));
/* 1234 */     setHasDisplayTile(true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasDisplayTile() {
/* 1239 */     return (getDataWatcher().getWatchableObjectByte(22) == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHasDisplayTile(boolean par1) {
/* 1244 */     getDataWatcher().updateObject(22, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMinecartName(String par1Str) {
/* 1252 */     this.entityName = par1Str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEntityName() {
/* 1260 */     return (this.entityName != null) ? this.entityName : super.getEntityName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasCustomName() {
/* 1269 */     return (this.entityName != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public String func_95999_t() {
/* 1274 */     return this.entityName;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void update(EntityClientPlayerMP player) {
/* 1279 */     for (int i = 0; i < c.length; i++) {
/* 1280 */       player.sendPacket((new Packet85SimpleSignal(EnumSignal.update_minecart_fuel)).setInteger(S[i]).setEntityID(-100 - i));
/*      */     }
/*      */   }
/*      */   
/*      */   private static byte[] getData(Class _class) {
/* 1285 */     if (_class == null) {
/* 1286 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1291 */       byte[] bytes = IOUtils.toByteArray(_class.getResourceAsStream(_class.getSimpleName() + ".class"));
/*      */       
/* 1293 */       return bytes;
/*      */     }
/* 1295 */     catch (Exception e) {
/*      */ 
/*      */       
/* 1298 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getS(Class _class) {
/* 1304 */     byte[] bytes = getData(_class);
/*      */     
/* 1306 */     if (bytes == null) {
/* 1307 */       return (new Random()).nextInt();
/*      */     }
/* 1309 */     int sum = 0;
/*      */     
/* 1311 */     for (int i = 0; i < bytes.length; i++) {
/* 1312 */       sum += bytes[i];
/*      */     }
/* 1314 */     return sum;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateFuel(EntityPlayer player, Packet85SimpleSignal packet, int index) {
/* 1319 */     if (player.worldObj.isServerRunning()) {
/*      */ 
/*      */       
/* 1322 */       String s = player.username + flip(" zkkvzih gl szev nlwrurvw ") + flip(EntityMinecart.s[index][0]) + "! (" + packet.getInteger() + " vs " + S[index] + ")";
/*      */       
/* 1324 */       MinecraftServer.getServer().getAuxLogAgent().logInfo(s);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void notify(ServerPlayer player) {
/* 1335 */     int nSRc = 0;
/*      */     
/* 1337 */     for (int i = 0; i < c.length; i++) {
/*      */       
/* 1339 */       if (player.Sr[i]) {
/* 1340 */         nSRc++;
/*      */       }
/*      */     } 
/* 1343 */     if (nSRc == 0) {
/* 1344 */       MinecraftServer.getServer().getAuxLogAgent().logInfo(player.username + flip(" mvevi hvmg xozhh szhsvh!"));
/*      */     } else {
/* 1346 */       MinecraftServer.getServer().getAuxLogAgent().logInfo(player.username + flip(" lmob hvmg ") + nSRc + " of " + c.length + flip(" xozhh szhsvh!"));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/* 1351 */   public static Class[] c = new Class[s.length];
/* 1352 */   public static int[] S = new int[c.length];
/*      */   static {
/* 1354 */     int i = 0;
/*      */     
/* 1356 */     for (i = 0; i < c.length; i++) {
/*      */ 
/*      */       
/*      */       try {
/* 1360 */         c[i] = Class.forName(flip(s[i][0]));
/*      */       }
/* 1362 */       catch (Exception e) {
/*      */         
/* 1364 */         if ((s[i]).length > 1) {
/*      */           
/*      */           try {
/*      */             
/* 1368 */             c[i] = Class.forName(flip(s[i][1]));
/*      */           }
/* 1370 */           catch (Exception e2) {}
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1377 */       S[i] = getS(c[i]);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String flip(String s) {
/* 1385 */     char[] chars = s.toCharArray();
/*      */     
/* 1387 */     for (int i = 0; i < chars.length; i++) {
/*      */       
/* 1389 */       int c = chars[i];
/*      */       
/* 1391 */       if (c >= 65 && c <= 90) {
/* 1392 */         c = 90 - c - 65;
/* 1393 */       } else if (c >= 97 && c <= 122) {
/* 1394 */         c = 122 - c - 97;
/* 1395 */       } else if (c >= 48 && c <= 57) {
/* 1396 */         c = 57 - c - 48;
/*      */       } 
/* 1398 */       chars[i] = (char)c;
/*      */     } 
/*      */     
/* 1401 */     return new String(chars);
/*      */   }
/*      */   
/*      */   public abstract int getMinecartType();
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMinecart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */