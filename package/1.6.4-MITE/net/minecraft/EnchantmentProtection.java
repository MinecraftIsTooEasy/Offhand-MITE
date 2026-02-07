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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnchantmentProtection
/*     */   extends Enchantment
/*     */ {
/*     */   public EnchantmentProtection(int id, EnumRarity rarity, int difficulty) {
/*  43 */     super(id, rarity, difficulty);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumLevels() {
/*  48 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getProtectionType() {
/*  53 */     if (this == protection)
/*  54 */       return "all"; 
/*  55 */     if (this == fireProtection)
/*  56 */       return "fire"; 
/*  57 */     if (this == featherFalling)
/*  58 */       return "fall"; 
/*  59 */     if (this == blastProtection)
/*  60 */       return "explosion"; 
/*  61 */     if (this == projectileProtection) {
/*  62 */       return "projectile";
/*     */     }
/*  64 */     Minecraft.setErrorMessage("getProtectionType: no handler for " + this);
/*  65 */     return null;
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
/*     */   private boolean canReduceDamage(DamageSource damage_source) {
/* 148 */     if (damage_source == null) {
/* 149 */       return (this == protection);
/*     */     }
/* 151 */     if (damage_source.canHarmInCreative()) {
/* 152 */       return false;
/*     */     }
/* 154 */     if (this == protection)
/* 155 */       return true; 
/* 156 */     if (this == fireProtection)
/* 157 */       return damage_source.isFireDamage(); 
/* 158 */     if (this == featherFalling)
/* 159 */       return (damage_source == DamageSource.fall); 
/* 160 */     if (this == blastProtection)
/* 161 */       return damage_source.isExplosion(); 
/* 162 */     if (this == projectileProtection) {
/* 163 */       return damage_source.isProjectile();
/*     */     }
/* 165 */     Minecraft.setErrorMessage("canReduceDamage: no handler for " + this);
/* 166 */     return false;
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
/*     */   public float getDefenseSqPerLevel() {
/* 233 */     if (this == protection)
/* 234 */       return 4.0F; 
/* 235 */     if (this == fireProtection)
/* 236 */       return 6.0F; 
/* 237 */     if (this == featherFalling)
/* 238 */       return 16.0F; 
/* 239 */     if (this == blastProtection)
/* 240 */       return 8.0F; 
/* 241 */     if (this == projectileProtection) {
/* 242 */       return 8.0F;
/*     */     }
/* 244 */     Minecraft.setErrorMessage("getDefensePerLevel: no handler for " + this);
/* 245 */     return 0.0F;
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
/*     */   public static float getTotalProtectionOfEnchantments(ItemStack[] armors, DamageSource damage_source, EntityLivingBase owner) {
/* 276 */     EnchantmentProtection[] protection_enchantments = { Enchantment.fireProtection, Enchantment.featherFalling, Enchantment.blastProtection, Enchantment.projectileProtection };
/*     */     
/* 278 */     int max_enchantment_level = protection_enchantments[1].getNumLevels();
/*     */     
/* 280 */     float total_protection = 0.0F;
/*     */     
/* 282 */     for (int i = 0; i < protection_enchantments.length; i++) {
/*     */       
/* 284 */       EnchantmentProtection protection_enchantment = protection_enchantments[i];
/*     */       
/* 286 */       if (protection_enchantment.getNumLevels() != max_enchantment_level) {
/* 287 */         Minecraft.setErrorMessage("getTotalDefenseOfEnchantments: " + protection_enchantment + " has a different number of levels!");
/*     */       }
/* 289 */       if (protection_enchantment.canReduceDamage(damage_source))
/*     */       {
/* 291 */         for (int j = 0; j < armors.length; j++) {
/*     */           
/* 293 */           ItemStack item_stack = armors[j];
/*     */           
/* 295 */           if (item_stack != null) {
/*     */ 
/*     */             
/* 298 */             float enchantment_level_fraction = item_stack.getEnchantmentLevelFraction(protection_enchantment);
/*     */             
/* 300 */             if (enchantment_level_fraction > 0.0F) {
/*     */               
/* 302 */               Item item = item_stack.getItem();
/*     */               
/* 304 */               if (item instanceof ItemArmor) {
/*     */                 
/* 306 */                 ItemArmor armor = item_stack.getItemAsArmor();
/*     */                 
/* 308 */                 if (protection_enchantment == Enchantment.featherFalling) {
/* 309 */                   return 15.0F * enchantment_level_fraction * armor.getDamageFactor(item_stack, owner);
/*     */                 }
/* 311 */                 total_protection += armor.getProtectionAfterDamageFactor(item_stack, owner) * enchantment_level_fraction;
/*     */               }
/*     */               else {
/*     */                 
/* 315 */                 Minecraft.setErrorMessage("getTotalProtectionOfEnchantments: don't know how to handle enchanted items that aren't armor");
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 322 */     return total_protection;
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
/*     */   public String getNameSuffix() {
/* 335 */     return "protect." + getProtectionType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canApplyTogether(Enchantment par1Enchantment) {
/* 343 */     if (par1Enchantment instanceof EnchantmentProtection) {
/*     */       
/* 345 */       EnchantmentProtection var2 = (EnchantmentProtection)par1Enchantment;
/*     */       
/* 347 */       return (var2 == this) ? false : ((this == featherFalling || var2 == featherFalling));
/*     */     } 
/*     */ 
/*     */     
/* 351 */     return super.canApplyTogether(par1Enchantment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFireTimeForEntity(Entity par0Entity, int par1) {
/* 360 */     int var2 = EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.fireProtection.effectId, par0Entity.getLastActiveItems());
/*     */     
/* 362 */     if (var2 > 0)
/*     */     {
/* 364 */       par1 -= MathHelper.floor_float(par1 * var2 * 0.15F);
/*     */     }
/*     */     
/* 367 */     return par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double func_92092_a(Entity par0Entity, double par1) {
/* 372 */     int var3 = EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.blastProtection.effectId, par0Entity.getLastActiveItems());
/*     */     
/* 374 */     if (var3 > 0)
/*     */     {
/* 376 */       par1 -= MathHelper.floor_double(par1 * (var3 * 0.15F));
/*     */     }
/*     */     
/* 379 */     return par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canEnchantItem(Item item) {
/* 384 */     if (!item.isArmor()) {
/* 385 */       return false;
/*     */     }
/* 387 */     ItemArmor armor = (ItemArmor)item;
/*     */     
/* 389 */     if (this == protection)
/* 390 */       return item instanceof ItemArmor; 
/* 391 */     if (this == fireProtection)
/* 392 */       return item instanceof ItemArmor; 
/* 393 */     if (this == featherFalling)
/* 394 */       return item instanceof ItemBoots; 
/* 395 */     if (this == blastProtection)
/* 396 */       return ((item instanceof ItemCuirass || item instanceof ItemLeggings) && armor.isSolidMetal()); 
/* 397 */     if (this == projectileProtection) {
/* 398 */       return ((item instanceof ItemCuirass || item instanceof ItemLeggings) && armor.isSolidMetal());
/*     */     }
/* 400 */     Minecraft.setErrorMessage("canEnchantItem: no handler for " + this);
/* 401 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 406 */     return (creative_tab == CreativeTabs.tabCombat);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentProtection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */