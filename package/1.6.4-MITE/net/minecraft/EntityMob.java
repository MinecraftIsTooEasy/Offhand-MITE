/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public abstract class EntityMob
/*     */   extends EntityCreature
/*     */   implements IMob {
/*     */   public EntityMob(World par1World) {
/*   9 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  19 */     updateArmSwingProgress();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  27 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  35 */     super.onUpdate();
/*     */     
/*  37 */     if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0)
/*     */     {
/*  39 */       setDead();
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
/*  96 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */     
/*  98 */     if (result == null || result.entityWasDestroyed()) {
/*  99 */       return result;
/*     */     }
/* 101 */     if (result.entityWasNegativelyAffected()) {
/*     */       
/* 103 */       Entity var3 = damage.getResponsibleEntity();
/*     */       
/* 105 */       if (this.riddenByEntity != var3 && this.ridingEntity != var3 && var3 != this) {
/* 106 */         this.entityToAttack = var3;
/*     */       }
/*     */     } 
/* 109 */     return result;
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
/*     */   public static EntityDamageResult attackEntityAsMob(EntityLiving attacker, Entity target) {
/* 195 */     if (attacker.isDecoy())
/*     */     {
/* 197 */       return null;
/*     */     }
/* 199 */     if (target instanceof EntityPlayer && target.getAsPlayer().isImmuneByGrace())
/*     */     {
/* 201 */       return null;
/*     */     }
/* 203 */     ItemStack held_item = attacker.getHeldItemStack();
/*     */     
/* 205 */     Damage damage = new Damage(DamageSource.causeMobDamage(attacker), (float)attacker.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
/*     */     
/* 207 */     if (attacker.isFrenzied()) {
/* 208 */       damage.addAmount((float)attacker.getEntityAttributeBaseValue(SharedMonsterAttributes.attackDamage) * 0.5F);
/*     */     }
/*     */     
/* 211 */     int knockback_bonus = 0;
/*     */     
/* 213 */     if (target.isEntityLivingBase()) {
/*     */ 
/*     */       
/* 216 */       damage.addAmount(EnchantmentDamage.getDamageModifiers(held_item, target.getAsEntityLivingBase()));
/* 217 */       knockback_bonus += EnchantmentHelper.getKnockbackModifier(attacker, target.getAsEntityLivingBase());
/*     */     } 
/*     */     
/* 220 */     int fire_aspect = EnchantmentHelper.getFireAspectModifier(attacker);
/*     */ 
/*     */     
/* 223 */     EntityDamageResult result = target.attackEntityFrom(damage.setFireAspect((fire_aspect > 0)));
/*     */     
/* 225 */     if (result == null) {
/* 226 */       return result;
/*     */     }
/* 228 */     if (result.entityWasNegativelyAffected()) {
/*     */ 
/*     */       
/* 231 */       if (knockback_bonus > 0) {
/*     */         
/* 233 */         target.addVelocity((-MathHelper.sin(attacker.rotationYaw * 3.1415927F / 180.0F) * knockback_bonus * 0.5F), 0.1D, (MathHelper.cos(attacker.rotationYaw * 3.1415927F / 180.0F) * knockback_bonus * 0.5F));
/* 234 */         attacker.motionX *= 0.6D;
/* 235 */         attacker.motionZ *= 0.6D;
/*     */       } 
/*     */       
/* 238 */       if (fire_aspect > 0) {
/* 239 */         target.setFire(fire_aspect * 4);
/*     */       }
/* 241 */       if (attacker.isBurning() && !attacker.hasHeldItem() && attacker.rand.nextFloat() < attacker.worldObj.difficultySetting * 0.3F) {
/* 242 */         target.setFire(2 * attacker.worldObj.difficultySetting);
/*     */       }
/* 244 */       if (target.isEntityLivingBase()) {
/*     */         
/* 246 */         if (attacker.worldObj.isRemote) {
/*     */           
/* 248 */           System.out.println("EntityMob.attackEntityAsMob() is calling EnchantmentThorns.func_92096_a() on client");
/* 249 */           Minecraft.temp_debug = "mob";
/*     */         } 
/*     */         
/* 252 */         EnchantmentThorns.func_92096_a(attacker, target.getAsEntityLivingBase(), attacker.rand);
/*     */         
/* 254 */         int stunning = EnchantmentHelper.getStunModifier(attacker, target.getAsEntityLivingBase());
/*     */         
/* 256 */         if (stunning > Math.random() * 10.0D) {
/* 257 */           target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, stunning * 50, stunning * 5));
/*     */         }
/*     */         
/* 260 */         attacker.heal(EnchantmentHelper.getVampiricTransfer(attacker, target.getAsEntityLivingBase(), result.getAmountOfHealthLost()), EnumEntityFX.vampiric_gain);
/*     */       } 
/*     */       
/* 263 */       if (target instanceof EntityPlayer) {
/* 264 */         attacker.refreshDespawnCounter(-9600);
/*     */       }
/*     */     } 
/* 267 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 275 */     if (isDecoy())
/*     */     {
/* 277 */       return null;
/*     */     }
/* 279 */     if (target instanceof EntityPlayer && target.getAsPlayer().isImmuneByGrace())
/*     */     {
/* 281 */       return null;
/*     */     }
/* 283 */     ItemStack held_item = getHeldItemStack();
/*     */     
/* 285 */     Damage damage = new Damage(DamageSource.causeMobDamage(this), (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
/*     */     
/* 287 */     if (isFrenzied()) {
/* 288 */       damage.addAmount((float)getEntityAttributeBaseValue(SharedMonsterAttributes.attackDamage) * 0.5F);
/*     */     }
/*     */     
/* 291 */     int knockback_bonus = 0;
/*     */     
/* 293 */     if (target.isEntityLivingBase()) {
/*     */ 
/*     */       
/* 296 */       damage.addAmount(EnchantmentDamage.getDamageModifiers(held_item, target.getAsEntityLivingBase()));
/* 297 */       knockback_bonus += EnchantmentHelper.getKnockbackModifier(this, target.getAsEntityLivingBase());
/*     */     } 
/*     */     
/* 300 */     int fire_aspect = EnchantmentHelper.getFireAspectModifier(this);
/*     */ 
/*     */     
/* 303 */     EntityDamageResult result = target.attackEntityFrom(damage.setFireAspect((fire_aspect > 0)));
/*     */     
/* 305 */     if (result == null) {
/* 306 */       return result;
/*     */     }
/* 308 */     if (result.entityWasNegativelyAffected()) {
/*     */ 
/*     */       
/* 311 */       if (knockback_bonus > 0) {
/*     */         
/* 313 */         target.addVelocity((-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * knockback_bonus * 0.5F), 0.1D, (MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * knockback_bonus * 0.5F));
/* 314 */         this.motionX *= 0.6D;
/* 315 */         this.motionZ *= 0.6D;
/*     */       } 
/*     */       
/* 318 */       if (fire_aspect > 0) {
/* 319 */         target.setFire(fire_aspect * 4);
/*     */       }
/* 321 */       if (isBurning() && !hasHeldItem() && this.rand.nextFloat() < this.worldObj.difficultySetting * 0.3F) {
/* 322 */         target.setFire(2 * this.worldObj.difficultySetting);
/*     */       }
/* 324 */       if (target.isEntityLivingBase()) {
/*     */         
/* 326 */         if (this.worldObj.isRemote) {
/*     */           
/* 328 */           System.out.println("EntityMob.attackEntityAsMob() is calling EnchantmentThorns.func_92096_a() on client");
/* 329 */           Minecraft.temp_debug = "mob";
/*     */         } 
/*     */         
/* 332 */         EnchantmentThorns.func_92096_a(this, target.getAsEntityLivingBase(), this.rand);
/*     */         
/* 334 */         int stunning = EnchantmentHelper.getStunModifier(this, target.getAsEntityLivingBase());
/*     */         
/* 336 */         if (stunning > Math.random() * 10.0D) {
/* 337 */           target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, stunning * 50, stunning * 5));
/*     */         }
/*     */         
/* 340 */         heal(EnchantmentHelper.getVampiricTransfer(this, target.getAsEntityLivingBase(), result.getAmountOfHealthLost()), EnumEntityFX.vampiric_gain);
/*     */       } 
/*     */       
/* 343 */       if (target instanceof EntityPlayer) {
/* 344 */         refreshDespawnCounter(-9600);
/*     */       }
/*     */     } 
/* 347 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackEntity(Entity par1Entity, float par2) {
/* 356 */     if (this.attackTime <= 0 && par2 < 1.75F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY) {
/*     */       
/* 358 */       if (getHeldItemStack() != null) {
/* 359 */         swingArm();
/*     */       }
/* 361 */       this.attackTime = 20;
/* 362 */       attackEntityAsMob(par1Entity);
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
/*     */   public float getBlockPathWeight(int par1, int par2, int par3) {
/* 377 */     if (this.came_from_spawner)
/*     */     {
/*     */       
/* 380 */       return 0.7F - this.worldObj.getLightBrightness(par1, par2, par3);
/*     */     }
/*     */     
/* 383 */     return 0.5F - this.worldObj.getLightBrightness(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isValidLightLevel(EntityLiving entity_living) {
/* 388 */     if (entity_living.came_from_spawner) {
/* 389 */       return (entity_living.getChanceOfCatchingFireFromSunlightThisTick() == 0.0F);
/*     */     }
/* 391 */     World world = entity_living.worldObj;
/* 392 */     Random rand = entity_living.rand;
/*     */     
/* 394 */     int x = MathHelper.floor_double(entity_living.posX);
/* 395 */     int y = MathHelper.floor_double(entity_living.boundingBox.minY);
/* 396 */     int z = MathHelper.floor_double(entity_living.posZ);
/*     */     
/* 398 */     if (world.getBlockMaterial(x, y, z).isLiquid()) {
/* 399 */       y++;
/*     */     }
/* 401 */     if (world.getSavedLightValue(EnumSkyBlock.Sky, x, y, z) > rand.nextInt(32)) {
/* 402 */       return false;
/*     */     }
/* 404 */     if (!world.isRemote && (world.getBiomeGenForCoords(x, z)).rainfall == 0.0F && !world.isBloodMoon24HourPeriod()) {
/* 405 */       world.ignore_rain_and_thunder_for_next_BLV = true;
/*     */     }
/* 407 */     int blv = entity_living.worldObj.getBlockLightValue(x, y, z);
/*     */ 
/*     */     
/* 410 */     if (world.isThundering(true) && ((world.getBiomeGenForCoords(x, z)).rainfall > 0.0F || world.isBloodMoon24HourPeriod())) {
/*     */       
/* 412 */       int var5 = world.skylightSubtracted;
/* 413 */       world.skylightSubtracted = 10;
/* 414 */       blv = world.getBlockLightValue(x, y, z);
/* 415 */       world.skylightSubtracted = var5;
/*     */     } 
/*     */     
/* 418 */     return (blv <= rand.nextInt(entity_living.isUnderOpenSky() ? 8 : 5));
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
/*     */   protected boolean isValidLightLevel() {
/* 466 */     return isValidLightLevel(this);
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
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 481 */     return (this.worldObj.difficultySetting > 0 && (!perform_light_check || isValidLightLevel()) && super.getCanSpawnHere(perform_light_check));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnInShallowWater() {
/* 486 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 491 */     super.applyEntityAttributes();
/* 492 */     setEntityAttribute(SharedMonsterAttributes.followRange, 32.0D);
/*     */     
/* 494 */     setEntityAttribute(SharedMonsterAttributes.attackDamage);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 499 */     if (!super.canDespawn()) {
/* 500 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 506 */     EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 48.0D, true);
/*     */     
/* 508 */     if (player != null && canPathTo(player.getBlockPosX(), player.getFootBlockPosY(), player.getBlockPosZ(), 48)) {
/*     */ 
/*     */ 
/*     */       
/* 512 */       refreshDespawnCounter(-1200);
/* 513 */       return false;
/*     */     } 
/*     */     
/* 516 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFrenzied() {
/* 521 */     return this.worldObj.isBloodMoon(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */