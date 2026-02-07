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
/*     */ public class EnchantmentDamage
/*     */   extends Enchantment
/*     */ {
/*     */   public EnchantmentDamage(int id, EnumRarity rarity, int difficulty) {
/*  41 */     super(id, rarity, difficulty);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTranslatedName(Item item) {
/*  46 */     if (this == sharpness && item instanceof ItemAxe) {
/*  47 */       return "Slaying";
/*     */     }
/*  49 */     return super.getTranslatedName(item);
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
/*     */   public float getDamageModifier(int level, EntityLivingBase target) {
/*  86 */     if (this == sharpness)
/*  87 */       return level * 1.0F; 
/*  88 */     if (this == smite)
/*  89 */       return (target != null && target.isEntityUndead()) ? (level * 2.0F) : 0.0F; 
/*  90 */     if (this == baneOfArthropods) {
/*  91 */       return (target != null && target.isArthropod()) ? (level * 2.0F) : 0.0F;
/*     */     }
/*  93 */     Minecraft.setErrorMessage("getDamageModifier: no handler for " + this);
/*  94 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getDamageModifiers(ItemStack weapon, EntityLivingBase target) {
/*  99 */     float damage_modifiers = 0.0F;
/*     */     
/* 101 */     if (weapon == null || !weapon.isItemEnchanted() || weapon.getItem() == Item.enchantedBook) {
/* 102 */       return damage_modifiers;
/*     */     }
/* 104 */     for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
/*     */       
/* 106 */       Enchantment enchantment = Enchantment.get(i);
/*     */       
/* 108 */       if (enchantment instanceof EnchantmentDamage) {
/*     */         
/* 110 */         EnchantmentDamage enchantment_damage = (EnchantmentDamage)enchantment;
/*     */         
/* 112 */         int level = weapon.getEnchantmentLevel(enchantment_damage);
/*     */         
/* 114 */         if (level > 0) {
/* 115 */           damage_modifiers += enchantment_damage.getDamageModifier(level, target);
/*     */         }
/*     */       } 
/*     */     } 
/* 119 */     return damage_modifiers;
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
/* 132 */     if (this == sharpness)
/* 133 */       return "damage.all"; 
/* 134 */     if (this == smite)
/* 135 */       return "damage.undead"; 
/* 136 */     if (this == baneOfArthropods) {
/* 137 */       return "damage.arthropods";
/*     */     }
/* 139 */     Minecraft.setErrorMessage("getNameSuffix: no handler for " + this);
/* 140 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canApplyTogether(Enchantment par1Enchantment) {
/* 148 */     return !(par1Enchantment instanceof EnchantmentDamage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canEnchantItem(Item item) {
/* 158 */     if (this == sharpness)
/* 159 */       return (item instanceof ItemSword || item.getClass() == ItemBattleAxe.class || item instanceof ItemScythe); 
/* 160 */     if (this == baneOfArthropods)
/* 161 */       return item instanceof ItemSword; 
/* 162 */     if (this == smite) {
/* 163 */       return (item.getClass() == ItemWarHammer.class);
/*     */     }
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 170 */     return (creative_tab == CreativeTabs.tabCombat);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentDamage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */