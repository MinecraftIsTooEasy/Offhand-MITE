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
/*     */ public class Damage
/*     */ {
/*     */   private DamageSource source;
/*     */   private float amount;
/*  21 */   private float MAXIMUM_DAMAGE = 1000.0F;
/*     */   
/*     */   private boolean ignore_specific_immunities;
/*     */   
/*     */   private boolean knockback_only;
/*     */   
/*     */   public Damage(DamageSource source, float amount) {
/*  28 */     this.source = source;
/*  29 */     this.amount = amount;
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
/*     */   public DamageSource getSource() {
/*  42 */     return this.source;
/*     */   }
/*     */ 
/*     */   
/*     */   public Damage setAmount(float amount) {
/*  47 */     this.amount = amount;
/*  48 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Damage setToMaximumAmount() {
/*  53 */     this.amount = this.MAXIMUM_DAMAGE;
/*  54 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Damage addAmount(float amount) {
/*  59 */     setAmount(getAmount() + amount);
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAmount() {
/*  65 */     return this.amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public Damage scaleAmount(float factor) {
/*  70 */     this.amount *= factor;
/*  71 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Damage scaleAmount(float factor, float floor) {
/*  76 */     this.amount *= factor;
/*     */     
/*  78 */     if (this.amount < floor) {
/*  79 */       this.amount = floor;
/*     */     }
/*  81 */     return this;
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
/*     */   public Damage setNil() {
/* 129 */     this.amount = 0.0F;
/*     */ 
/*     */ 
/*     */     
/* 133 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNil() {
/* 140 */     return (this.amount <= 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLessThanHalfAHeart() {
/* 146 */     return (this.amount < 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 151 */     return getSource().toString() + " @ " + this.amount;
/*     */   }
/*     */ 
/*     */   
/*     */   Entity getResponsibleEntity() {
/* 156 */     return this.source.getResponsibleEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   Entity getImmediateEntity() {
/* 161 */     return this.source.getImmediateEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExplosion() {
/* 166 */     return this.source.isExplosion();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFireDamage() {
/* 171 */     return this.source.isFireDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLavaDamage() {
/* 176 */     return this.source.isLavaDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAnvil() {
/* 181 */     return this.source.isAnvil();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFallingBlock() {
/* 186 */     return this.source.isFallingBlock();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrowning() {
/* 191 */     return this.source.isDrowning();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMagicAspect() {
/* 196 */     return this.source.hasMagicAspect();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStarving() {
/* 201 */     return this.source.isStarving();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIndirect() {
/* 206 */     return this.source.isIndirect();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasCausedByPlayer() {
/* 211 */     return this.source.wasCausedByPlayer();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wasCausedByPlayerInCreative() {
/* 216 */     return this.source.wasCausedByPlayerInCreative();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFireballFromPlayer() {
/* 221 */     return this.source.isFireballFromPlayer();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAbsolute() {
/* 226 */     return this.source.isAbsolute();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSnowball() {
/* 231 */     return this.source.isSnowball();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlayerThrownSnowball() {
/* 236 */     return this.source.isPlayerThrownSnowball();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSunlight() {
/* 241 */     return this.source.isSunlight();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArrowDamage() {
/* 246 */     return this.source.isArrowDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArrowFromPlayer() {
/* 251 */     return this.source.isArrowFromPlayer();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMelee() {
/* 256 */     return this.source.isMelee();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEggDamage() {
/* 261 */     return this.source.isEggDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPepsinDamage() {
/* 266 */     return this.source.isPepsinDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAcidDamage() {
/* 271 */     return this.source.isAcidDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFallDamage() {
/* 276 */     return this.source.isFallDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPoison() {
/* 281 */     return this.source.isPoison();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bypassesMundaneArmor() {
/* 286 */     return this.source.bypassesMundaneArmor();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemAttackedWith() {
/* 291 */     return this.source.getItemAttackedWith();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float applyTargetDefenseModifiers(EntityLivingBase target, EntityDamageResult result) {
/* 297 */     if (target.onClient()) {
/* 298 */       Minecraft.setErrorMessage("applyTargetDefenseModifiers: called on client?");
/*     */     }
/* 300 */     if (this.amount <= 0.0F) {
/* 301 */       return 0.0F;
/*     */     }
/* 303 */     if (isAbsolute()) {
/* 304 */       return this.amount;
/*     */     }
/* 306 */     if (target instanceof EntityPlayer) {
/*     */       
/* 308 */       EntityPlayer player = (EntityPlayer)target;
/*     */       
/* 310 */       if (!bypassesMundaneArmor() && player.isBlocking()) {
/*     */         
/* 312 */         this.amount /= 2.0F;
/*     */         
/* 314 */         if (this.amount < 1.0F) {
/* 315 */           this.amount = 1.0F;
/*     */         }
/* 317 */         ItemStack item_stack = player.getHeldItemStack();
/*     */         
/* 319 */         if (item_stack != null && item_stack.getItem() instanceof ItemTool) {
/*     */           
/* 321 */           ItemTool item_tool = (ItemTool)item_stack.getItem();
/*     */           
/* 323 */           result.applyHeldItemDamageResult(item_stack.tryDamageItem(DamageSource.generic, (int)(this.amount * item_tool.getToolDecayFromAttackingEntity(item_stack, null)), target));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 328 */     float total_protection = target.getTotalProtection(getSource());
/*     */     
/* 330 */     DebugAttack.setTargetProtection(total_protection);
/*     */     
/* 332 */     float amount_dealt_to_armor = Math.min(target.getProtectionFromArmor(getSource(), false), this.amount);
/* 333 */     target.tryDamageArmor(getSource(), amount_dealt_to_armor, result);
/*     */     
/* 335 */     DebugAttack.setDamageDealtToArmor(amount_dealt_to_armor);
/*     */     
/* 337 */     float piercing = Enchantment.piercing.getLevelFraction(getItemAttackedWith()) * 5.0F;
/*     */     
/* 339 */     float effective_protection = Math.max(total_protection - piercing, 0.0F);
/*     */     
/* 341 */     DebugAttack.setPiercing(piercing);
/*     */     
/* 343 */     if (target instanceof EntityPlayer && effective_protection >= this.amount) {
/*     */       
/* 345 */       int delta = (int)(effective_protection - this.amount);
/*     */       
/* 347 */       for (int i = -1; i < delta; i++) {
/*     */         
/* 349 */         if (target.rand.nextFloat() < 0.2F) {
/* 350 */           return 0.0F;
/*     */         }
/*     */       } 
/*     */     } 
/* 354 */     return Math.max(this.amount - effective_protection, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Damage setIgnoreSpecificImmunities() {
/* 360 */     this.ignore_specific_immunities = true;
/* 361 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreSpecificImmunities() {
/* 367 */     return this.ignore_specific_immunities;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Damage setKnockbackOnly() {
/* 373 */     this.knockback_only = true;
/* 374 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isKnockbackOnly() {
/* 380 */     return this.knockback_only;
/*     */   }
/*     */ 
/*     */   
/*     */   public Damage setFireAspect(boolean has_fire_aspect) {
/* 385 */     this.source.setFireAspect(has_fire_aspect);
/* 386 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHarmInCreative() {
/* 391 */     return this.source.canHarmInCreative();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean wasCausedByPlayer(Damage damage) {
/* 396 */     return (damage != null && damage.wasCausedByPlayer());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Damage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */