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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DamageSource
/*     */ {
/*  22 */   public static DamageSource inFire = (new DamageSource("inFire")).setFireDamage();
/*  23 */   public static DamageSource onFire = (new DamageSource("onFire")).setFireDamage();
/*  24 */   public static DamageSource lava = (new DamageSource("lava")).setLavaDamage();
/*  25 */   public static DamageSource inWall = (new DamageSource("inWall")).setAbsolute();
/*  26 */   public static DamageSource drown = (new DamageSource("drown")).setUnblockable();
/*  27 */   public static DamageSource reverse_drown = (new DamageSource("reverse_drown")).setUnblockable();
/*  28 */   public static DamageSource water = (new DamageSource("drown")).setUnblockable();
/*  29 */   public static DamageSource starve = (new DamageSource("starve")).setUnblockable();
/*  30 */   public static DamageSource cactus = new DamageSource("cactus");
/*  31 */   public static DamageSource fall = (new DamageSource("fall")).setDamageBypassesMundaneArmor();
/*  32 */   public static DamageSource outOfWorld = (new DamageSource("outOfWorld")).setAbsolute().setDamageAllowedInCreativeMode();
/*  33 */   public static DamageSource generic = (new DamageSource("generic")).setDamageBypassesMundaneArmor();
/*  34 */   public static DamageSource magic = (new DamageSource("magic")).setDamageBypassesMundaneArmor().setMagicAspect();
/*  35 */   public static DamageSource wither = (new DamageSource("wither")).setDamageBypassesMundaneArmor();
/*  36 */   public static DamageSource anvil = new DamageSource("anvil");
/*  37 */   public static DamageSource fallingBlock = new DamageSource("fallingBlock");
/*  38 */   public static DamageSource poison = (new DamageSource("poison")).setUnblockable();
/*  39 */   public static DamageSource divine_lightning = (new DamageSource("divine_lightning")).setAbsolute();
/*  40 */   public static DamageSource absolute = (new DamageSource("absolute")).setAbsolute();
/*  41 */   public static DamageSource sunlight = new DamageSource("sunlight");
/*  42 */   public static DamageSource pepsin = new DamageSource("pepsin");
/*  43 */   public static DamageSource acid = new DamageSource("acid");
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static DamageSource melt = new DamageSource("melt");
/*     */ 
/*     */   
/*  50 */   public static DamageSource pig_nibble = new DamageSource("pigNibble");
/*     */   
/*     */   private boolean bypasses_mundane_armor;
/*     */   
/*     */   private boolean is_unblockable;
/*     */   
/*     */   private boolean is_absolute;
/*     */   
/*     */   private boolean isDamageAllowedInCreativeMode;
/*     */   
/*  60 */   private float hungerDamage = 0.3F;
/*     */ 
/*     */   
/*     */   private boolean fireDamage;
/*     */   
/*     */   private boolean has_fire_aspect;
/*     */   
/*     */   private boolean is_lava_damage;
/*     */   
/*     */   private boolean has_magic_aspect;
/*     */   
/*     */   private boolean has_silver_aspect;
/*     */   
/*     */   private boolean explosion;
/*     */   
/*     */   public String damageType;
/*     */   
/*     */   public int block_x;
/*     */   
/*     */   public int block_y;
/*     */   
/*     */   public int block_z;
/*     */   
/*     */   public int block_metadata;
/*     */   
/*     */   public boolean is_hand_damage;
/*     */ 
/*     */   
/*     */   public static DamageSource causeMobDamage(EntityLivingBase par0EntityLivingBase) {
/*  89 */     return new EntityDamageSource("mob", par0EntityLivingBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource causePlayerDamage(EntityPlayer par0EntityPlayer) {
/*  97 */     return new EntityDamageSource("player", par0EntityPlayer);
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
/*     */   public static DamageSource causeArrowDamage(EntityArrow entity_arrow, Entity entity_shooter) {
/* 111 */     return new EntityDamageSource("arrow", entity_arrow, entity_shooter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource causeFireballDamage(EntityFireball par0EntityFireball, Entity par1Entity) {
/* 121 */     return (par1Entity == null) ? (new EntityDamageSource("onFire", par0EntityFireball, par0EntityFireball)).setFireDamage() : (new EntityDamageSource("fireball", par0EntityFireball, par1Entity)).setFireDamage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource causeThrownDamage(Entity entity_projectile, Entity entity_shooter) {
/* 132 */     return new EntityDamageSource("thrown", entity_projectile, entity_shooter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource causeIndirectMagicDamage(Entity par0Entity, Entity par1Entity) {
/* 138 */     return (new EntityDamageSource("indirectMagic", par0Entity, par1Entity)).setDamageBypassesMundaneArmor().setMagicAspect();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource causeThornsDamage(Entity par0Entity) {
/* 146 */     return (new EntityDamageSource("thorns", par0Entity)).setMagicAspect();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource setExplosionSource(Explosion par0Explosion) {
/* 157 */     return (par0Explosion != null && par0Explosion.getExplosivePlacedBy() != null) ? (new EntityDamageSource("explosion.player", par0Explosion.getExplosivePlacedBy())).setExplosion() : (new DamageSource("explosion")).setExplosion();
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
/*     */   public boolean isProjectile() {
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIndirect() {
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMelee() {
/* 180 */     if (isProjectile() || isIndirect()) {
/* 181 */       return false;
/*     */     }
/* 183 */     return true;
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
/*     */   public boolean isExplosion() {
/* 197 */     return this.explosion;
/*     */   }
/*     */ 
/*     */   
/*     */   public DamageSource setExplosion() {
/* 202 */     this.explosion = true;
/* 203 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean bypassesMundaneArmor() {
/* 214 */     return (this.bypasses_mundane_armor || this.is_unblockable || this.is_absolute);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUnblockable() {
/* 220 */     return (this.is_unblockable || this.is_absolute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAbsolute() {
/* 227 */     return this.is_absolute;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHungerDamage() {
/* 235 */     return this.hungerDamage;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHarmInCreative() {
/* 240 */     return this.isDamageAllowedInCreativeMode;
/*     */   }
/*     */ 
/*     */   
/*     */   protected DamageSource(String par1Str) {
/* 245 */     this.damageType = par1Str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getImmediateEntity() {
/* 255 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getResponsibleEntity() {
/* 260 */     return null;
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
/*     */   protected DamageSource setDamageBypassesMundaneArmor() {
/* 273 */     this.bypasses_mundane_armor = true;
/* 274 */     this.hungerDamage = 0.0F;
/* 275 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected DamageSource setUnblockable() {
/* 281 */     this.is_unblockable = true;
/* 282 */     return setDamageBypassesMundaneArmor();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected DamageSource setAbsolute() {
/* 288 */     this.is_absolute = true;
/* 289 */     return setUnblockable();
/*     */   }
/*     */ 
/*     */   
/*     */   protected DamageSource setDamageAllowedInCreativeMode() {
/* 294 */     this.isDamageAllowedInCreativeMode = true;
/* 295 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DamageSource setFireDamage() {
/* 303 */     this.fireDamage = true;
/* 304 */     setFireAspect();
/* 305 */     setDamageBypassesMundaneArmor();
/* 306 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected DamageSource setFireAspect() {
/* 312 */     return setFireAspect(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected DamageSource setFireAspect(boolean has_fire_aspect) {
/* 317 */     this.has_fire_aspect = has_fire_aspect;
/* 318 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DamageSource setLavaDamage() {
/* 324 */     this.is_lava_damage = true;
/* 325 */     setDamageBypassesMundaneArmor();
/* 326 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLavaDamage() {
/* 332 */     return this.is_lava_damage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DamageSource setHandDamage() {
/* 338 */     this.is_hand_damage = true;
/* 339 */     setUnblockable();
/*     */     
/* 341 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatMessageComponent getDeathMessage(EntityLivingBase par1EntityLivingBase) {
/* 349 */     EntityLivingBase var2 = par1EntityLivingBase.func_94060_bK();
/* 350 */     String var3 = "death.attack." + this.damageType;
/* 351 */     String var4 = var3 + ".player";
/* 352 */     return (var2 != null && StatCollector.func_94522_b(var4)) ? ChatMessageComponent.createFromTranslationWithSubstitutions(var4, new Object[] { par1EntityLivingBase.getTranslatedEntityName(), var2.getTranslatedEntityName() }) : ChatMessageComponent.createFromTranslationWithSubstitutions(var3, new Object[] { par1EntityLivingBase.getTranslatedEntityName() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFireDamage() {
/* 360 */     return this.fireDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFireAspect() {
/* 366 */     return (this.has_fire_aspect || isFireDamage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDamageType() {
/* 374 */     return this.damageType;
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
/*     */   public boolean hasMagicAspect() {
/* 399 */     return this.has_magic_aspect;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSilverAspect() {
/* 404 */     return this.has_silver_aspect;
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
/*     */   public DamageSource setMagicAspect() {
/* 418 */     this.has_magic_aspect = true;
/* 419 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public DamageSource setSilverAspect() {
/* 424 */     this.has_silver_aspect = true;
/* 425 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemAttackedWith() {
/* 430 */     Entity immediate_entity = getImmediateEntity();
/*     */     
/* 432 */     if (immediate_entity != null) {
/*     */       
/* 434 */       if (immediate_entity instanceof EntityThrowable) {
/*     */         
/* 436 */         EntityThrowable entity_throwable = (EntityThrowable)immediate_entity;
/* 437 */         return entity_throwable.getItemStack();
/*     */       } 
/*     */       
/* 440 */       if (immediate_entity instanceof EntityArrow) {
/*     */         
/* 442 */         EntityArrow entity_arrow = (EntityArrow)immediate_entity;
/* 443 */         return new ItemStack(entity_arrow.item_arrow);
/*     */       } 
/*     */       
/* 446 */       if (immediate_entity instanceof EntityLivingBase) {
/*     */         
/* 448 */         EntityLivingBase entity_living_base = (EntityLivingBase)immediate_entity;
/* 449 */         return entity_living_base.getHeldItemStack();
/*     */       } 
/*     */     } 
/*     */     
/* 453 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public DamageSource setBlock(World world, int x, int y, int z) {
/* 458 */     this.block_x = x;
/* 459 */     this.block_y = y;
/* 460 */     this.block_z = z;
/*     */     
/* 462 */     this.block_metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 464 */     return this;
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
/*     */ 
/*     */   
/*     */   public String toString() {
/* 533 */     return this.damageType;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasCausedByPlayer() {
/* 538 */     return getResponsibleEntity() instanceof EntityPlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasCausedByPlayerInCreative() {
/* 543 */     Entity entity = getResponsibleEntity();
/*     */     
/* 545 */     return (entity instanceof EntityPlayer && ((EntityPlayer)entity).inCreativeMode());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFireballFromPlayer() {
/* 550 */     return ("fireball".equals(getDamageType()) && wasCausedByPlayer());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean wasCausedByPlayer(DamageSource damage_source) {
/* 555 */     return (damage_source != null && damage_source.wasCausedByPlayer());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isArrowDamage(DamageSource damage_source) {
/* 560 */     return (damage_source != null && damage_source.getImmediateEntity() instanceof EntityArrow);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnvil() {
/* 570 */     return (this == anvil);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFallingBlock() {
/* 575 */     return (this == fallingBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrowning() {
/* 580 */     return (this == drown);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStarving() {
/* 585 */     return (this == starve);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSnowball() {
/* 590 */     return (isProjectile() && getImmediateEntity() instanceof EntitySnowball);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlayerThrownSnowball() {
/* 595 */     return (isSnowball() && wasCausedByPlayer());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSunlight() {
/* 600 */     return (this == sunlight);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArrowDamage() {
/* 605 */     return (isProjectile() && getImmediateEntity() instanceof EntityArrow);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArrowFromPlayer() {
/* 610 */     return (isArrowDamage() && wasCausedByPlayer());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCactus() {
/* 615 */     return (this == cactus);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWater() {
/* 620 */     return (this == water);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEggDamage() {
/* 625 */     return (isProjectile() && getImmediateEntity() instanceof EntityEgg);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBrickDamage() {
/* 630 */     return (isProjectile() && getImmediateEntity() instanceof EntityBrick);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPepsinDamage() {
/* 635 */     return (this == pepsin);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAcidDamage() {
/* 640 */     return (this == acid);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFallDamage() {
/* 645 */     return (this == fall);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPoison() {
/* 650 */     return (this == poison);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGelatinousSphereDamage() {
/* 655 */     return (isProjectile() && getImmediateEntity() instanceof EntityGelatinousSphere);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLootingModifier() {
/* 661 */     if (getResponsibleEntity() instanceof EntityLivingBase) {
/* 662 */       return EnchantmentHelper.getLootingModifier(getResponsibleEntity().getAsEntityLivingBase());
/*     */     }
/* 664 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getButcheringModifier() {
/* 670 */     if (getResponsibleEntity() instanceof EntityLivingBase) {
/* 671 */       return EnchantmentHelper.getButcheringModifier(getResponsibleEntity().getAsEntityLivingBase());
/*     */     }
/* 673 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DamageSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */