/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.Random;
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
/*      */ public class EntityArrow
/*      */   extends Entity
/*      */   implements IProjectile
/*      */ {
/*      */   public int xTile;
/*      */   public int yTile;
/*      */   public int zTile;
/*      */   private int inTile;
/*      */   private int inData;
/*      */   private boolean inGround;
/*      */   public int canBePickedUp;
/*      */   public int arrowShake;
/*      */   public Entity shootingEntity;
/*      */   private int ticksInGround;
/*      */   private int ticksInAir;
/*      */   private double damage;
/*      */   private int knockbackStrength;
/*      */   public ItemArrow item_arrow;
/*      */   public boolean launcher_was_enchanted;
/*      */   protected float speed_before_collision_sq;
/*      */   private boolean was_burning;
/*      */   private int ticks_until_next_fizz_sound;
/*      */   public boolean shot_by_dispenser;
/*      */   private Entity last_entity_harmed;
/*      */   
/*      */   public EntityArrow(World par1World) {
/*   50 */     super(par1World);
/*      */     this.xTile = -1;
/*      */     this.yTile = -1;
/*      */     this.zTile = -1;
/*      */     this.damage = 2.0D;
/*      */   } public EntityArrow(World par1World, ItemArrow item_arrow, boolean launcher_was_enchanted) {
/*   56 */     super(par1World); this.xTile = -1; this.yTile = -1; this.zTile = -1; this.damage = 2.0D;
/*   57 */     this.renderDistanceWeight = 10.0D;
/*   58 */     setSize(0.5F, 0.5F);
/*   59 */     this.item_arrow = item_arrow;
/*   60 */     this.launcher_was_enchanted = launcher_was_enchanted;
/*   61 */     this.damage = item_arrow.getDamage();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityArrow(World par1World, double par2, double par4, double par6, ItemArrow item_arrow, boolean launcher_was_enchanted) {
/*   67 */     super(par1World); this.xTile = -1; this.yTile = -1; this.zTile = -1; this.damage = 2.0D;
/*   68 */     this.renderDistanceWeight = 10.0D;
/*   69 */     setSize(0.5F, 0.5F);
/*   70 */     setPosition(par2, par4, par6);
/*   71 */     this.yOffset = 0.0F;
/*   72 */     this.item_arrow = item_arrow;
/*   73 */     this.launcher_was_enchanted = launcher_was_enchanted;
/*   74 */     this.damage = item_arrow.getDamage();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityArrow(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float velocity, float par5, ItemArrow item_arrow, boolean launcher_was_enchanted) {
/*   80 */     super(par1World); this.xTile = -1; this.yTile = -1; this.zTile = -1; this.damage = 2.0D;
/*   81 */     this.renderDistanceWeight = 10.0D;
/*   82 */     this.shootingEntity = par2EntityLivingBase;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   87 */     if (par2EntityLivingBase instanceof EntityPlayer)
/*      */     {
/*   89 */       this.canBePickedUp = 1;
/*      */     }
/*      */     
/*   92 */     this.posY = par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight() - 0.10000000149011612D;
/*   93 */     double var6 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
/*   94 */     double var8 = par3EntityLivingBase.boundingBox.minY + (par3EntityLivingBase.height / 3.0F) - this.posY;
/*   95 */     double var10 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
/*      */     
/*   97 */     if (this.shootingEntity instanceof EntitySkeleton) {
/*      */       
/*   99 */       double distance_horizontal_sq = var6 * var6 + var10 * var10;
/*      */       
/*  101 */       float lead = (float)Math.pow(distance_horizontal_sq, 0.44D);
/*      */       
/*  103 */       lead *= 0.5F + this.rand.nextFloat();
/*      */       
/*  105 */       var6 = par3EntityLivingBase.getPredictedPosX(lead) - par2EntityLivingBase.posX;
/*  106 */       var10 = par3EntityLivingBase.getPredictedPosZ(lead) - par2EntityLivingBase.posZ;
/*      */     } 
/*      */     
/*  109 */     double distance_squared = var6 * var6 + var10 * var10;
/*  110 */     double var12 = MathHelper.sqrt_double(var6 * var6 + var10 * var10);
/*      */     
/*  112 */     if (var12 >= 1.0E-7D) {
/*      */       
/*  114 */       float var14 = (float)(Math.atan2(var10, var6) * 180.0D / Math.PI) - 90.0F;
/*  115 */       float var15 = (float)-(Math.atan2(var8, var12) * 180.0D / Math.PI);
/*  116 */       double var16 = var6 / var12;
/*  117 */       double var18 = var10 / var12;
/*  118 */       setLocationAndAngles(par2EntityLivingBase.posX + var16, this.posY, par2EntityLivingBase.posZ + var18, var14, var15);
/*  119 */       this.yOffset = 0.0F;
/*  120 */       float var20 = (float)var12 * 0.2F;
/*      */       
/*  122 */       if (par2EntityLivingBase instanceof EntitySkeleton) {
/*  123 */         par5 *= 1.5F;
/*      */       }
/*  125 */       setThrowableHeading(var6, var8 + var20, var10, velocity, par5);
/*      */     } 
/*      */     
/*  128 */     this.item_arrow = item_arrow;
/*  129 */     this.launcher_was_enchanted = launcher_was_enchanted;
/*  130 */     this.damage = item_arrow.getDamage();
/*      */     
/*  132 */     if (par2EntityLivingBase instanceof EntitySkeleton) {
/*      */       
/*  134 */       double y_correction = distance_squared * 5.000000237487257E-4D * distance_squared * 5.000000237487257E-4D - 0.05000000074505806D;
/*      */       
/*  136 */       if (distance_squared > 576.0D) {
/*  137 */         y_correction += 0.05999999865889549D;
/*      */       }
/*  139 */       this.motionY += y_correction;
/*      */       
/*  141 */       float dy = (float)par3EntityLivingBase.posY - (float)par2EntityLivingBase.posY;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  146 */       if (dy > 5.0F) {
/*  147 */         this.motionY += ((dy - 5.0F) * 0.025F) * (1.2000000476837158D - distance_squared * 5.000000237487257E-4D);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityArrow(World par1World, EntityLivingBase par2EntityLivingBase, float velocity, ItemArrow item_arrow, boolean launcher_was_enchanted) {
/*  155 */     super(par1World); float wander; this.xTile = -1; this.yTile = -1; this.zTile = -1; this.damage = 2.0D;
/*  156 */     this.renderDistanceWeight = 10.0D;
/*  157 */     this.shootingEntity = par2EntityLivingBase;
/*      */     
/*  159 */     if (par2EntityLivingBase instanceof EntityPlayer)
/*      */     {
/*  161 */       this.canBePickedUp = 1;
/*      */     }
/*      */     
/*  164 */     setSize(0.5F, 0.5F);
/*  165 */     setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  172 */     this.posX -= (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F) * 0.30000001192092896D;
/*  173 */     this.posY -= 0.10000000149011612D;
/*  174 */     this.posZ -= (MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F) * 0.30000001192092896D;
/*      */     
/*  176 */     setPosition(this.posX, this.posY, this.posZ);
/*  177 */     this.yOffset = 0.0F;
/*  178 */     this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
/*  179 */     this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
/*  180 */     this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F);
/*      */ 
/*      */ 
/*      */     
/*  184 */     if (par2EntityLivingBase instanceof EntityPlayer) {
/*      */       
/*  186 */       EntityPlayer player = (EntityPlayer)par2EntityLivingBase;
/*      */ 
/*      */ 
/*      */       
/*  190 */       int effective_player_level = (player.getExperienceLevel() < 0 || !par1World.areSkillsEnabled() || player.hasSkill(Skill.ARCHERY)) ? player.getExperienceLevel() : 0;
/*      */       
/*  192 */       effective_player_level += EnchantmentHelper.getEnchantmentLevelFractionOfInteger(Enchantment.true_flight, getLauncher(), 40);
/*      */       
/*  194 */       if (effective_player_level < 0) {
/*  195 */         wander = 5.0F + effective_player_level * -0.5F;
/*      */       } else {
/*  197 */         wander = (float)(0.5D + 4.5D / Math.pow((0.8F + (effective_player_level + 1) / 5.0F), 2.0D));
/*      */       } 
/*      */     } else {
/*      */       
/*  201 */       wander = 1.0F;
/*      */     } 
/*      */     
/*  204 */     if (par2EntityLivingBase.isSuspendedInLiquid()) {
/*  205 */       wander *= 2.0F;
/*      */     }
/*      */     
/*  208 */     setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, wander);
/*      */     
/*  210 */     this.item_arrow = item_arrow;
/*  211 */     this.launcher_was_enchanted = launcher_was_enchanted;
/*  212 */     this.damage = item_arrow.getDamage();
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack getLauncher() {
/*  217 */     if (this.shootingEntity instanceof EntityLivingBase) {
/*      */       
/*  219 */       EntityLivingBase entity_living_base = (EntityLivingBase)this.shootingEntity;
/*      */       
/*  221 */       return entity_living_base.getHeldItemStack();
/*      */     } 
/*      */     
/*  224 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  229 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setThrowableHeading(double par1, double par3, double par5, float velocity, float par8) {
/*  238 */     ItemStack launcher = getLauncher();
/*      */     
/*  240 */     if (launcher != null && launcher.getItem() == Item.bowMithril && this.shootingEntity instanceof EntityPlayer) {
/*  241 */       velocity *= 1.25F;
/*      */     }
/*  243 */     if (launcher != null && launcher.getItem() == Item.bowAncientMetal && this.shootingEntity instanceof EntityPlayer) {
/*  244 */       velocity *= 1.1F;
/*      */     }
/*  246 */     float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
/*  247 */     par1 /= var9;
/*  248 */     par3 /= var9;
/*  249 */     par5 /= var9;
/*  250 */     par1 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : true) * 0.007499999832361937D * par8;
/*  251 */     par3 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : true) * 0.007499999832361937D * par8;
/*  252 */     par5 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : true) * 0.007499999832361937D * par8;
/*  253 */     par1 *= velocity;
/*  254 */     par3 *= velocity;
/*  255 */     par5 *= velocity;
/*  256 */     this.motionX = par1;
/*  257 */     this.motionY = par3;
/*  258 */     this.motionZ = par5;
/*  259 */     float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
/*  260 */     this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
/*  261 */     this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, var10) * 180.0D / Math.PI);
/*  262 */     this.ticksInGround = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
/*  271 */     setPosition(par1, par3, par5);
/*  272 */     setRotation(par7, par8);
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
/*      */   public void setVelocity(double par1, double par3, double par5) {
/*  290 */     this.motionX = par1;
/*  291 */     this.motionY = par3;
/*  292 */     this.motionZ = par5;
/*      */ 
/*      */ 
/*      */     
/*  296 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*      */       
/*  298 */       float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  306 */       this.prevRotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
/*  307 */       this.prevRotationPitch = (float)(Math.atan2(par3, var7) * 180.0D / Math.PI);
/*      */       
/*  309 */       this.ticksInGround = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getVelocity() {
/*  315 */     return MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spentTickInLava() {
/*  320 */     if (this.inGround) {
/*  321 */       super.spentTickInLava();
/*      */     }
/*      */   }
/*      */   
/*      */   private void bounceBack() {
/*  326 */     this.motionX *= -0.10000000149011612D;
/*  327 */     this.motionY *= -0.10000000149011612D;
/*  328 */     this.motionZ *= -0.10000000149011612D;
/*  329 */     this.rotationYaw += 180.0F;
/*  330 */     this.prevRotationYaw += 180.0F;
/*  331 */     this.ticksInAir = 0;
/*      */     
/*  333 */     this.motionX /= 4.0D;
/*  334 */     this.motionY /= -4.0D;
/*  335 */     this.motionZ /= 4.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean cannotRaycastCollideWith(Entity entity) {
/*  340 */     if (entity == this.shootingEntity && this.ticksInAir < 5) {
/*  341 */       return true;
/*      */     }
/*  343 */     return super.cannotRaycastCollideWith(entity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*  351 */     super.onUpdate();
/*      */     
/*  353 */     this.speed_before_collision_sq = (float)(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/*      */     
/*  355 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*      */       
/*  357 */       float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*  358 */       this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*  359 */       this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, var1) * 180.0D / Math.PI);
/*      */     } 
/*      */     
/*  362 */     int var16 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
/*      */     
/*  364 */     if (var16 > 0) {
/*      */ 
/*      */ 
/*      */       
/*  368 */       AxisAlignedBB var2 = Block.blocksList[var16].getCollisionBoundsCombined(this.worldObj, this.xTile, this.yTile, this.zTile, this, true);
/*      */       
/*  370 */       if (var2 != null && var2.isVecInside(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ)))
/*      */       {
/*  372 */         this.inGround = true;
/*      */       }
/*      */     } 
/*      */     
/*  376 */     if (this.arrowShake > 0)
/*      */     {
/*  378 */       this.arrowShake--;
/*      */     }
/*      */     
/*  381 */     if (!this.worldObj.isRemote && this.was_burning) {
/*      */       
/*  383 */       Block block = this.inGround ? Block.getBlock(this.inTile) : null;
/*      */       
/*  385 */       if (block != null && block.blockMaterial.isFreezing()) {
/*      */         
/*  387 */         if (!isWet()) {
/*  388 */           causeQuenchEffect();
/*      */         }
/*  390 */         extinguish();
/*      */       }
/*  392 */       else if (isInWater()) {
/*      */         
/*  394 */         causeQuenchEffect();
/*      */       }
/*  396 */       else if (isWet()) {
/*      */         
/*  398 */         if (--this.ticks_until_next_fizz_sound <= 0) {
/*      */           
/*  400 */           spawnSingleSteamParticle(true);
/*      */           
/*  402 */           this.ticks_until_next_fizz_sound = this.rand.nextInt(17) + 3;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  407 */     this.was_burning = isBurning();
/*      */     
/*  409 */     if (this.inGround) {
/*      */       
/*  411 */       int var18 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
/*  412 */       int var19 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
/*      */ 
/*      */       
/*  415 */       if (var18 == this.inTile && (var19 == this.inData || this.inTile == Block.grass.blockID))
/*      */       {
/*  417 */         this.ticksInGround++;
/*      */ 
/*      */ 
/*      */         
/*  421 */         if (this.ticksInGround >= ((this.shootingEntity instanceof EntityPlayer) ? 24000 : 1000))
/*      */         {
/*  423 */           setDead();
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*  437 */         this.inGround = false;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  442 */         Random rand = new Random(this.entityId);
/*      */         
/*  444 */         this.motionX *= (rand.nextFloat() * 0.2F);
/*  445 */         this.motionY *= (rand.nextFloat() * 0.2F);
/*  446 */         this.motionZ *= (rand.nextFloat() * 0.2F);
/*      */         
/*  448 */         this.ticksInGround = 0;
/*  449 */         this.ticksInAir = 0;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  454 */       this.ticksInAir++;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  459 */       if (onServer()) {
/*      */         
/*  461 */         AxisAlignedBB bb = this.boundingBox.copy();
/*      */         
/*  463 */         if (this.worldObj.isBoundingBoxBurning(bb.contract(0.001D, 0.001D, 0.001D), true)) {
/*  464 */           setFire(8);
/*  465 */         } else if (this.worldObj.isBoundingBoxBurning(bb.contract(0.001D, 0.001D, 0.001D).translate(this.motionX / 2.0D, this.motionY / 2.0D, this.motionZ / 2.0D), true)) {
/*  466 */           setFire(8);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  471 */       Vec3 current_pos = this.worldObj.getVec3(this.posX, this.posY, this.posZ);
/*  472 */       Vec3 future_pos = this.worldObj.getVec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*      */       
/*  474 */       Raycast raycast = (new Raycast(this.worldObj, current_pos, future_pos)).setForPiercingProjectile(this).performVsBlocks();
/*  475 */       RaycastCollision var4 = raycast.getBlockCollision();
/*  476 */       RaycastCollision block_collision = var4;
/*      */       
/*  478 */       if (var4 != null) {
/*  479 */         raycast.setLimitToBlockCollisionPoint();
/*      */       }
/*  481 */       if (raycast.performVsEntities().hasEntityCollisions())
/*      */       {
/*  483 */         var4 = raycast.getNearestCollision();
/*      */       }
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
/*  543 */       if (var4 != null && var4.getEntityHit() instanceof EntityPlayer) {
/*      */ 
/*      */         
/*  546 */         EntityPlayer var21 = (EntityPlayer)var4.getEntityHit();
/*      */         
/*  548 */         if (var21.capabilities.disableDamage || (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(var21)))
/*      */         {
/*  550 */           var4 = null;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  557 */       if (var4 == null || !var4.isEntity()) {
/*  558 */         this.last_entity_harmed = null;
/*      */       }
/*  560 */       if (var4 != null)
/*      */       {
/*      */         
/*  563 */         if (var4.isEntity()) {
/*      */           
/*  565 */           Entity entity_hit = var4.getEntityHit();
/*      */           
/*  567 */           float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/*      */ 
/*      */           
/*  570 */           float damage_multiplier = 1.0F;
/*      */           
/*  572 */           if (entity_hit instanceof EntityLivingBase) {
/*      */             
/*  574 */             EntityLivingBase entity_living_base = (EntityLivingBase)entity_hit;
/*      */ 
/*      */ 
/*      */             
/*  578 */             if (entity_living_base.isEntityUndead() && this.item_arrow.getArrowheadMaterial() == Material.silver) {
/*  579 */               damage_multiplier *= 1.25F;
/*      */             }
/*  581 */             if (entity_hit instanceof EntitySkeleton) {
/*  582 */               damage_multiplier *= 0.25F;
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/*  587 */           float var24 = f1 * (float)this.damage * damage_multiplier;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  592 */           if (this.shootingEntity instanceof EntitySkeleton) {
/*      */             
/*  594 */             float min_damage = this.item_arrow.getDamage() * 2.0F + 2.0F;
/*      */             
/*  596 */             if (this.shootingEntity.getClass() == EntityLongdead.class) {
/*  597 */               min_damage += 2.0F;
/*  598 */             } else if (this.shootingEntity.getClass() == EntityLongdeadGuardian.class) {
/*  599 */               min_damage += 3.0F;
/*      */             } 
/*  601 */             if (var24 < min_damage) {
/*  602 */               var24 = min_damage;
/*  603 */             } else if (!this.launcher_was_enchanted) {
/*  604 */               var24 = min_damage;
/*      */             } 
/*  606 */           } else if (this.shot_by_dispenser) {
/*      */             
/*  608 */             float min_damage = this.item_arrow.getDamage() * 2.0F + 2.0F;
/*      */             
/*  610 */             if (var24 < min_damage) {
/*  611 */               var24 = min_damage;
/*      */             }
/*      */           } 
/*  614 */           if (getIsCritical())
/*      */           {
/*      */             
/*  617 */             var24 *= 1.5F + this.rand.nextFloat() * 0.5F;
/*      */           }
/*      */           
/*  620 */           DamageSource var22 = null;
/*      */           
/*  622 */           if (this.shootingEntity == null) {
/*      */             
/*  624 */             var22 = DamageSource.causeArrowDamage(this, this);
/*      */           }
/*      */           else {
/*      */             
/*  628 */             var22 = DamageSource.causeArrowDamage(this, this.shootingEntity);
/*      */           } 
/*      */           
/*  631 */           if (isBurning() && !(entity_hit instanceof EntityEnderman))
/*      */           {
/*      */ 
/*      */             
/*  635 */             if (entity_hit instanceof EntityGelatinousCube) {
/*      */               
/*  637 */               if (onServer())
/*      */               {
/*  639 */                 if (getVelocity() < 1.0F)
/*      */                 {
/*  641 */                   entity_hit.attackEntityFrom(new Damage(DamageSource.inFire, 1.0F));
/*  642 */                   extinguish(true);
/*      */                 }
/*      */                 else
/*      */                 {
/*  646 */                   var24++;
/*  647 */                   entity_hit.entityFX(EnumEntityFX.steam_with_hiss);
/*      */                 }
/*      */               
/*      */               }
/*      */             } else {
/*      */               
/*  653 */               entity_hit.setFire(5);
/*      */             } 
/*      */           }
/*      */           
/*  657 */           if (entity_hit instanceof EntityGelatinousCube && ((EntityGelatinousCube)entity_hit).isAcidic() && this.item_arrow.isHarmedByAcid()) {
/*      */             
/*  659 */             if (onServer()) {
/*  660 */               entityFX(EnumEntityFX.steam_with_hiss);
/*      */             }
/*  662 */             setDead();
/*      */           } 
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
/*  685 */           if (entity_hit instanceof EntitySkeleton && this.shootingEntity instanceof EntitySkeleton) {
/*  686 */             setDead();
/*      */           }
/*  688 */           Damage damage = new Damage(var22, var24);
/*      */           
/*  690 */           boolean entity_immune_to_arrow = (entity_hit.isEntityInvulnerable() || entity_hit.isImmuneTo(damage.getSource()));
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
/*  701 */           if (entity_hit != this.last_entity_harmed)
/*      */           {
/*      */ 
/*      */             
/*  705 */             if (getVelocity() < 1.0F || entity_immune_to_arrow)
/*      */             {
/*  707 */               bounceBack();
/*      */             }
/*  709 */             else if (onServer())
/*      */             {
/*  711 */               EntityDamageResult result = entity_hit.attackEntityFrom(damage);
/*      */               
/*  713 */               if (result == null && entity_hit instanceof EntityPhaseSpider) {
/*      */                 
/*  715 */                 if (block_collision != null) {
/*  716 */                   handleCollisionWithBlock(block_collision);
/*      */                 }
/*      */               }
/*  719 */               else if (result != null && result.entityWasNegativelyAffected()) {
/*      */                 
/*  721 */                 this.last_entity_harmed = entity_hit;
/*      */                 
/*  723 */                 if (entity_hit instanceof EntityLivingBase) {
/*      */                   
/*  725 */                   if (this.shootingEntity instanceof EntityLivingBase) {
/*  726 */                     ((EntityLivingBase)this.shootingEntity).setLastAttackTarget(entity_hit);
/*      */                   }
/*  728 */                   EntityLivingBase var25 = (EntityLivingBase)entity_hit;
/*      */                   
/*  730 */                   var25.setArrowCountInEntity(var25.getArrowCountInEntity() + 1);
/*      */                   
/*  732 */                   ItemStack launcher = getLauncher();
/*      */                   
/*  734 */                   if (launcher != null && this.rand.nextFloat() < launcher.getEnchantmentLevelFraction(Enchantment.poison)) {
/*  735 */                     var25.addPotionEffect(new PotionEffect(Potion.poison.id, 160 + launcher.getEnchantmentLevelFractionOfInteger(Enchantment.poison, 240), 0));
/*      */                   }
/*  737 */                   if (this.knockbackStrength > 0) {
/*      */                     
/*  739 */                     float var27 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*      */                     
/*  741 */                     if (var27 > 0.0F)
/*      */                     {
/*  743 */                       entity_hit.addVelocity(this.motionX * this.knockbackStrength * 0.6000000238418579D / var27, 0.1D, this.motionZ * this.knockbackStrength * 0.6000000238418579D / var27);
/*      */                     }
/*      */                   } 
/*      */ 
/*      */                   
/*  748 */                   if (this.shootingEntity != null) {
/*      */                     
/*  750 */                     if (this.worldObj.isRemote) {
/*      */                       
/*  752 */                       System.out.println("EntityArrow.onUpdate() is calling EnchantmentThorns.func_92096_a() on client");
/*  753 */                       Minecraft.temp_debug = "arrow";
/*      */                     } 
/*      */                     
/*  756 */                     EnchantmentThorns.func_92096_a(this.shootingEntity, var25, this.rand);
/*      */                   } 
/*      */                   
/*  759 */                   if (this.shootingEntity != null && entity_hit != this.shootingEntity && entity_hit instanceof EntityPlayer && this.shootingEntity instanceof ServerPlayer)
/*      */                   {
/*  761 */                     ((ServerPlayer)this.shootingEntity).playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(6, 0));
/*      */                   }
/*      */                 } 
/*      */                 
/*  765 */                 playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
/*      */                 
/*  767 */                 if (!(entity_hit instanceof EntityEnderman))
/*      */                 {
/*  769 */                   setDead();
/*      */                 }
/*      */                 
/*  772 */                 if (this.shootingEntity != null && entity_hit instanceof EntityPlayer) {
/*  773 */                   this.shootingEntity.refreshDespawnCounter(-9600);
/*      */                 }
/*      */               } else {
/*      */                 
/*  777 */                 bounceBack();
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
/*      */               }
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
/*      */             }
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
/*      */           }
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
/*      */         }
/*      */         else {
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
/*  892 */           handleCollisionWithBlock(var4);
/*      */         } 
/*      */       }
/*      */       
/*  896 */       if (getIsCritical())
/*      */       {
/*      */         
/*  899 */         for (int var9 = 0; var9 < 4; var9++)
/*      */         {
/*      */           
/*  902 */           this.worldObj.spawnParticle(EnumParticle.crit, this.posX + this.motionX * var9 / 4.0D, this.posY + this.motionY * var9 / 4.0D, this.posZ + this.motionZ * var9 / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
/*      */         }
/*      */       }
/*      */       
/*  906 */       this.posX += this.motionX;
/*  907 */       this.posY += this.motionY;
/*  908 */       this.posZ += this.motionZ;
/*  909 */       float var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*  910 */       this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*      */       
/*  912 */       for (this.rotationPitch = (float)(Math.atan2(this.motionY, var20) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  917 */       while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*      */       {
/*  919 */         this.prevRotationPitch += 360.0F;
/*      */       }
/*      */       
/*  922 */       while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*      */       {
/*  924 */         this.prevRotationYaw -= 360.0F;
/*      */       }
/*      */       
/*  927 */       while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*      */       {
/*  929 */         this.prevRotationYaw += 360.0F;
/*      */       }
/*      */       
/*  932 */       this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/*  933 */       this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/*  934 */       float var23 = 0.99F;
/*      */       
/*  936 */       float var11 = 0.05F;
/*      */       
/*  938 */       if (isInWater()) {
/*      */         
/*  940 */         for (int var26 = 0; var26 < 4; var26++) {
/*      */           
/*  942 */           float var27 = 0.25F;
/*      */           
/*  944 */           this.worldObj.spawnParticle(EnumParticle.bubble, this.posX - this.motionX * var27, this.posY - this.motionY * var27, this.posZ - this.motionZ * var27, this.motionX, this.motionY, this.motionZ);
/*      */         } 
/*      */         
/*  947 */         var23 = 0.8F;
/*      */       } 
/*      */       
/*  950 */       this.motionX *= var23;
/*  951 */       this.motionY *= var23;
/*  952 */       this.motionZ *= var23;
/*  953 */       this.motionY -= var11;
/*  954 */       setPosition(this.posX, this.posY, this.posZ);
/*  955 */       doBlockCollisions();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void handleCollisionWithBlock(RaycastCollision var4) {
/*  961 */     this.xTile = var4.block_hit_x;
/*  962 */     this.yTile = var4.block_hit_y;
/*  963 */     this.zTile = var4.block_hit_z;
/*  964 */     this.inTile = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
/*  965 */     this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
/*  966 */     this.motionX = (float)(var4.position_hit.xCoord - this.posX);
/*  967 */     this.motionY = (float)(var4.position_hit.yCoord - this.posY);
/*  968 */     this.motionZ = (float)(var4.position_hit.zCoord - this.posZ);
/*  969 */     float var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/*  970 */     this.posX -= this.motionX / var20 * 0.05000000074505806D;
/*  971 */     this.posY -= this.motionY / var20 * 0.05000000074505806D;
/*  972 */     this.posZ -= this.motionZ / var20 * 0.05000000074505806D;
/*  973 */     playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
/*  974 */     this.inGround = true;
/*  975 */     this.arrowShake = 7;
/*  976 */     setIsCritical(false);
/*      */     
/*  978 */     if (this.inTile != 0)
/*      */     {
/*  980 */       Block.blocksList[this.inTile].onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
/*      */     }
/*      */     
/*  983 */     if (onServer()) {
/*  984 */       sendPacketToAllPlayersTrackingEntity((new Packet85SimpleSignal(EnumSignal.arrow_hit_block)).setEntityID(this).setExactPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  992 */     par1NBTTagCompound.setShort("xTile", (short)this.xTile);
/*  993 */     par1NBTTagCompound.setShort("yTile", (short)this.yTile);
/*  994 */     par1NBTTagCompound.setShort("zTile", (short)this.zTile);
/*  995 */     par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
/*  996 */     par1NBTTagCompound.setByte("inData", (byte)this.inData);
/*  997 */     par1NBTTagCompound.setByte("shake", (byte)this.arrowShake);
/*  998 */     par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/*  999 */     par1NBTTagCompound.setByte("pickup", (byte)this.canBePickedUp);
/* 1000 */     par1NBTTagCompound.setDouble("damage", this.damage);
/*      */     
/* 1002 */     par1NBTTagCompound.setInteger("arrow_item_id", this.item_arrow.itemID);
/* 1003 */     par1NBTTagCompound.setBoolean("launcher_was_enchanted", this.launcher_was_enchanted);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 1011 */     this.xTile = par1NBTTagCompound.getShort("xTile");
/* 1012 */     this.yTile = par1NBTTagCompound.getShort("yTile");
/* 1013 */     this.zTile = par1NBTTagCompound.getShort("zTile");
/* 1014 */     this.inTile = par1NBTTagCompound.getByte("inTile") & 0xFF;
/* 1015 */     this.inData = par1NBTTagCompound.getByte("inData") & 0xFF;
/* 1016 */     this.arrowShake = par1NBTTagCompound.getByte("shake") & 0xFF;
/* 1017 */     this.inGround = (par1NBTTagCompound.getByte("inGround") == 1);
/*      */     
/* 1019 */     if (par1NBTTagCompound.hasKey("damage"))
/*      */     {
/* 1021 */       this.damage = par1NBTTagCompound.getDouble("damage");
/*      */     }
/*      */     
/* 1024 */     if (par1NBTTagCompound.hasKey("pickup")) {
/*      */       
/* 1026 */       this.canBePickedUp = par1NBTTagCompound.getByte("pickup");
/*      */     }
/* 1028 */     else if (par1NBTTagCompound.hasKey("player")) {
/*      */       
/* 1030 */       this.canBePickedUp = par1NBTTagCompound.getBoolean("player") ? 1 : 0;
/*      */     } 
/*      */     
/* 1033 */     this.item_arrow = (ItemArrow)Item.itemsList[par1NBTTagCompound.getInteger("arrow_item_id")];
/* 1034 */     this.launcher_was_enchanted = par1NBTTagCompound.getBoolean("launcher_was_enchanted");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
/* 1042 */     if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0) {
/*      */       
/* 1044 */       boolean var2 = (this.canBePickedUp == 1 || (this.canBePickedUp == 2 && par1EntityPlayer.capabilities.isCreativeMode));
/*      */ 
/*      */       
/* 1047 */       if (this.canBePickedUp == 1 && !par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this.item_arrow, 1)))
/*      */       {
/* 1049 */         var2 = false;
/*      */       }
/*      */       
/* 1052 */       if (var2) {
/*      */         
/* 1054 */         playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 1055 */         par1EntityPlayer.onItemPickup(this, 1);
/* 1056 */         setDead();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canTriggerWalking() {
/* 1067 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getShadowSize() {
/* 1072 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDamage(double par1) {
/* 1077 */     this.damage = par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getDamage() {
/* 1082 */     return this.damage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setKnockbackStrength(int par1) {
/* 1090 */     this.knockbackStrength = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canAttackWithItem() {
/* 1098 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIsCritical(boolean par1) {
/* 1106 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*      */     
/* 1108 */     if (par1) {
/*      */       
/* 1110 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x1)));
/*      */     }
/*      */     else {
/*      */       
/* 1114 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIsCritical() {
/* 1123 */     byte var1 = this.dataWatcher.getWatchableObjectByte(16);
/* 1124 */     return ((var1 & 0x1) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Item getModelItem() {
/* 1134 */     return this.item_arrow;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInGround() {
/* 1145 */     this.inGround = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInGround() {
/* 1150 */     return this.inGround;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInTile(int inTile) {
/* 1155 */     this.inTile = inTile;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInData(int inData) {
/* 1160 */     this.inData = inData;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getInTile() {
/* 1165 */     return this.inTile;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getInData() {
/* 1170 */     return this.inData;
/*      */   }
/*      */ 
/*      */   
/*      */   public void scaleVelocity(float amount) {
/* 1175 */     this.motionX *= amount;
/* 1176 */     this.motionY *= amount;
/* 1177 */     this.motionZ *= amount;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */