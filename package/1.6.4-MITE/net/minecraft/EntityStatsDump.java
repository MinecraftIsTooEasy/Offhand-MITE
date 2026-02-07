/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class EntityStatsDump
/*     */ {
/*  13 */   private static String newline = new String(System.getProperty("line.separator").getBytes());
/*     */ 
/*     */   
/*     */   private static String customFloatFormat(float f) {
/*  17 */     if (f < 1.0F && f != 0.0D) {
/*  18 */       return StringHelper.formatFloat(f, 2, 2);
/*     */     }
/*  20 */     return StringHelper.formatFloat(f, 1, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getItemStackDescriptorForStatsFile(ItemStack item_stack) {
/*  25 */     if (item_stack == null) {
/*  26 */       return "(nothing)";
/*     */     }
/*  28 */     StringBuilder sb = new StringBuilder();
/*     */     
/*  30 */     sb.append(item_stack.getNameForReferenceFile());
/*     */     
/*  32 */     if (item_stack.stackSize != 1) {
/*  33 */       sb.append(" (" + item_stack.stackSize + ")");
/*     */     }
/*  35 */     String[] notes = new String[16];
/*     */     
/*  37 */     EnumQuality quality = item_stack.getQuality();
/*     */     
/*  39 */     if (quality != null && !quality.isAverage()) {
/*  40 */       StringHelper.addToStringArray(quality.getDescriptor(), notes);
/*     */     }
/*  42 */     if (item_stack.isItemEnchanted()) {
/*     */       
/*  44 */       Map enchantments = EnchantmentHelper.getEnchantmentsMap(item_stack);
/*     */       
/*  46 */       Set set = enchantments.entrySet();
/*     */       
/*  48 */       Iterator<Map.Entry> i = set.iterator();
/*     */       
/*  50 */       while (i.hasNext()) {
/*     */         
/*  52 */         Map.Entry e = i.next();
/*     */         
/*  54 */         Enchantment enchantment = Enchantment.get(((Integer)e.getKey()).intValue());
/*  55 */         int level = ((Integer)e.getValue()).intValue();
/*     */         
/*  57 */         StringHelper.addToStringArray(enchantment.getTranslatedName(level, item_stack), notes);
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     if (notes[0] != null) {
/*     */       
/*  63 */       sb.append(" {");
/*  64 */       sb.append(StringHelper.implode(notes, ", ", true, false));
/*  65 */       sb.append("}");
/*     */     } 
/*     */     
/*  68 */     if (item_stack.isItemDamaged()) {
/*  69 */       sb.append(" [" + item_stack.getRemainingDurability() + "/" + item_stack.getMaxDamage() + "]");
/*     */     }
/*  71 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendSectionProtection(EntityLivingBase entity_living_base, StringBuilder sb) {
/*  76 */     sb.append("Protection" + newline);
/*  77 */     sb.append(" vs Generic: " + customFloatFormat(entity_living_base.getTotalProtection(DamageSource.causeMobDamage(null))) + newline);
/*  78 */     sb.append(" vs Falls: " + customFloatFormat(entity_living_base.getTotalProtection(DamageSource.fall)) + newline);
/*  79 */     sb.append(" vs Fire: " + customFloatFormat(entity_living_base.getTotalProtection(DamageSource.onFire)) + newline);
/*  80 */     sb.append(" vs Explosions: " + customFloatFormat(entity_living_base.getTotalProtection((new DamageSource("explosion")).setExplosion())) + newline);
/*     */     
/*  82 */     EntityArrow entity_arrow = new EntityArrow(entity_living_base.worldObj);
/*  83 */     entity_arrow.item_arrow = Item.arrowFlint;
/*     */     
/*  85 */     sb.append(" vs Projectiles: " + customFloatFormat(entity_living_base.getTotalProtection(DamageSource.causeArrowDamage(entity_arrow, null))) + newline + newline);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendSectionResistance(EntityLivingBase entity_living_base, StringBuilder sb) {
/*  90 */     sb.append("Resistance" + newline);
/*  91 */     sb.append(" vs Poison: " + Math.round(entity_living_base.getResistanceToPoison() * 100.0F) + "%" + newline);
/*  92 */     sb.append(" vs Paralysis: " + Math.round(entity_living_base.getResistanceToParalysis() * 100.0F) + "%" + newline);
/*  93 */     sb.append(" vs Drain: " + Math.round(entity_living_base.getResistanceToDrain() * 100.0F) + "%" + newline);
/*  94 */     sb.append(" vs Shadow: " + Math.round(entity_living_base.getResistanceToShadow() * 100.0F) + "%" + newline + newline);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendSectionPotionEffects(EntityLivingBase entity_living_base, StringBuilder sb) {
/*  99 */     sb.append("Potion Effects" + newline);
/*     */     
/* 101 */     if (!entity_living_base.hasActivePotionEffects()) {
/*     */       
/* 103 */       sb.append(" (none)" + newline + newline);
/*     */       
/*     */       return;
/*     */     } 
/* 107 */     Collection potion_effects = entity_living_base.getActivePotionEffects();
/*     */     
/* 109 */     Iterator<PotionEffect> i = potion_effects.iterator();
/*     */     
/* 111 */     while (i.hasNext()) {
/*     */       
/* 113 */       PotionEffect potion_effect = i.next();
/* 114 */       Potion potion = Potion.get(potion_effect.getPotionID());
/*     */       
/* 116 */       int level = potion_effect.getAmplifier();
/*     */       
/* 118 */       sb.append(" " + I18n.getString(potion.getName()));
/* 119 */       sb.append(" " + StringHelper.getRomanNumeral(level + 1));
/*     */       
/* 121 */       Map attribute_modifiers = potion.func_111186_k();
/*     */       
/* 123 */       Iterator<Map.Entry> iterator = attribute_modifiers.entrySet().iterator();
/*     */       
/* 125 */       String[] effect_descriptions = new String[16];
/*     */       
/* 127 */       while (iterator.hasNext()) {
/*     */         
/* 129 */         Map.Entry entry = iterator.next();
/*     */         
/* 131 */         Attribute attribute = (Attribute)entry.getKey();
/* 132 */         AttributeModifier modifier = (AttributeModifier)entry.getValue();
/*     */         
/* 134 */         modifier = new AttributeModifier(modifier.getID(), modifier.getName(), potion.func_111183_a(level, modifier), modifier.getOperation());
/*     */         
/* 136 */         String effect_details = ItemPotion.getEffectDetails(attribute.getAttributeUnlocalizedName(), modifier);
/*     */         
/* 138 */         if (effect_details != null) {
/* 139 */           StringHelper.addToStringArray(StringUtils.stripControlCodes(effect_details), effect_descriptions);
/*     */         }
/*     */       } 
/* 142 */       if (effect_descriptions[0] != null) {
/* 143 */         sb.append(" (" + StringHelper.implode(effect_descriptions, ", ", true, false) + ")");
/*     */       }
/* 145 */       sb.append(" [" + Potion.getDurationString(potion_effect) + "]");
/* 146 */       sb.append(newline);
/*     */     } 
/*     */     
/* 149 */     sb.append(newline);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendSectionEquipment(EntityLivingBase entity_living_base, StringBuilder sb) {
/* 154 */     sb.append("Equipment" + newline);
/*     */     
/* 156 */     if (entity_living_base.getHeldItemStack() != null) {
/* 157 */       sb.append(" Held: " + getItemStackDescriptorForStatsFile(entity_living_base.getHeldItemStack()) + newline);
/*     */     }
/* 159 */     ItemStack[] worn_items = entity_living_base.getWornItems();
/*     */     
/* 161 */     if (worn_items != null)
/*     */     {
/* 163 */       for (int i = 0; i < worn_items.length; i++) {
/* 164 */         sb.append(" Worn[" + i + "]: " + getItemStackDescriptorForStatsFile(worn_items[i]) + newline);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 186 */     sb.append(newline);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendSectionInventory(EntityLivingBase entity_living_base, StringBuilder sb) {
/* 191 */     sb.append("Inventory" + newline);
/*     */     
/* 193 */     if (entity_living_base.isEntityPlayer()) {
/*     */       
/* 195 */       EntityPlayer player = entity_living_base.getAsPlayer();
/*     */       
/* 197 */       boolean none = true;
/*     */       
/* 199 */       for (int i = 0; i < player.inventory.mainInventory.length; i++) {
/*     */         
/* 201 */         ItemStack item_stack = player.inventory.mainInventory[i];
/*     */         
/* 203 */         if (item_stack != null) {
/*     */ 
/*     */           
/* 206 */           none = false;
/*     */           
/* 208 */           sb.append(" " + ((i < 9) ? "Hotbar" : "Extended") + "[" + i + "]: " + getItemStackDescriptorForStatsFile(item_stack) + newline);
/*     */         } 
/*     */       } 
/* 211 */       if (none) {
/* 212 */         sb.append(" (none)" + newline);
/*     */       }
/* 214 */     } else if (entity_living_base instanceof EntityLiving) {
/*     */       
/* 216 */       EntityLiving entity_living = entity_living_base.getAsEntityLiving();
/*     */       
/* 218 */       ItemStack[] contained_items = entity_living.getContainedItems();
/*     */       
/* 220 */       if (contained_items == null) {
/*     */         
/* 222 */         boolean has_inventory = false;
/*     */         
/* 224 */         if (entity_living_base instanceof EntityLongdeadGuardian) {
/*     */           
/* 226 */           EntityLongdeadGuardian guardian = (EntityLongdeadGuardian)entity_living_base;
/*     */           
/* 228 */           if (guardian.getStowedItemStack() != null) {
/*     */             
/* 230 */             has_inventory = true;
/* 231 */             sb.append(" Stowed[0]: " + getItemStackDescriptorForStatsFile(guardian.getStowedItemStack()) + newline);
/*     */           } 
/*     */         } 
/*     */         
/* 235 */         if (!has_inventory) {
/* 236 */           sb.append(" (none)" + newline);
/*     */         }
/*     */       } else {
/*     */         
/* 240 */         for (int i = 0; i < contained_items.length; i++) {
/* 241 */           sb.append(" Contained[" + i + "]: " + getItemStackDescriptorForStatsFile(contained_items[i]) + newline);
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 246 */       sb.append(" (Don't know how to handle)" + newline);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getMeleeDamageString(EntityLivingBase entity_living_base) {
/*     */     float total_melee_damage;
/* 254 */     if (entity_living_base.isEntityPlayer()) {
/* 255 */       total_melee_damage = entity_living_base.getAsPlayer().calcRawMeleeDamageVs((Entity)null, false, false);
/* 256 */     } else if (entity_living_base.hasEntityAttribute(SharedMonsterAttributes.attackDamage)) {
/* 257 */       total_melee_damage = (float)entity_living_base.getEntityAttributeValue(SharedMonsterAttributes.attackDamage);
/*     */     } else {
/* 259 */       total_melee_damage = 0.0F;
/*     */     } 
/* 261 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 263 */     sb.append("Melee Damage: " + customFloatFormat(total_melee_damage));
/*     */     
/* 265 */     if (total_melee_damage > 0.0F) {
/*     */       
/* 267 */       ItemStack held_item_stack = entity_living_base.getHeldItemStack();
/*     */       
/* 269 */       float damage_from_held_item = (held_item_stack == null) ? 0.0F : held_item_stack.getMeleeDamageBonus();
/*     */       
/* 271 */       if (entity_living_base.isEntityPlayer()) {
/*     */         
/* 273 */         EntityPlayer player = entity_living_base.getAsPlayer();
/*     */         
/* 275 */         int level_modifier_melee = Math.round(player.getLevelModifier(EnumLevelBonus.MELEE_DAMAGE) * 100.0F);
/*     */         
/* 277 */         float base_melee_damage = (float)entity_living_base.getEntityAttributeBaseValue(SharedMonsterAttributes.attackDamage);
/*     */         
/* 279 */         String level_modifier_melee_string = (level_modifier_melee == 0) ? "" : (" + " + level_modifier_melee + "%");
/*     */         
/* 281 */         if (damage_from_held_item != 0.0F) {
/* 282 */           sb.append(" (" + base_melee_damage + " + " + held_item_stack.getDisplayName() + ")" + level_modifier_melee_string);
/* 283 */         } else if (level_modifier_melee != 0) {
/* 284 */           sb.append(" (" + base_melee_damage + level_modifier_melee_string + ")");
/*     */         }
/*     */       
/*     */       }
/* 288 */       else if (damage_from_held_item > 0.0F) {
/* 289 */         sb.append(" (" + customFloatFormat(total_melee_damage - damage_from_held_item) + " + " + held_item_stack.getDisplayName() + ")");
/*     */       } 
/*     */     } 
/*     */     
/* 293 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static StringBuilder getStatsDump(String header, EntityLivingBase entity_living_base) {
/* 299 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 301 */     if (entity_living_base.onClient()) {
/* 302 */       sb.append("* GENERATED ON CLIENT *" + newline + newline);
/*     */     }
/* 304 */     if (header == null) {
/* 305 */       header = entity_living_base.getEntityName();
/*     */     }
/* 307 */     sb.append(header + newline);
/* 308 */     sb.append(StringHelper.repeat("-", header.length()) + newline);
/*     */     
/* 310 */     sb.append("UUID: " + entity_living_base.getUniqueIDSilent() + newline + newline);
/*     */     
/* 312 */     EntityPlayer player = entity_living_base.isEntityPlayer() ? entity_living_base.getAsPlayer() : null;
/*     */     
/* 314 */     if (player != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 322 */       int protein, phytonutrients, level_modifier_harvesting = Math.round(player.getLevelModifier(EnumLevelBonus.HARVESTING) * 100.0F);
/* 323 */       int level_modifier_crafting = Math.round(player.getLevelModifier(EnumLevelBonus.CRAFTING) * 100.0F);
/* 324 */       int level_modifier_melee = Math.round(player.getLevelModifier(EnumLevelBonus.MELEE_DAMAGE) * 100.0F);
/*     */       
/* 326 */       sb.append("Level: " + player.getExperienceLevel() + " (");
/* 327 */       sb.append(((level_modifier_harvesting < 0) ? "" : "+") + level_modifier_harvesting + "% harvesting, ");
/* 328 */       sb.append(((level_modifier_crafting < 0) ? "" : "+") + level_modifier_crafting + "% crafting, ");
/* 329 */       sb.append(((level_modifier_melee < 0) ? "" : "+") + level_modifier_melee + "% melee)" + newline);
/*     */ 
/*     */ 
/*     */       
/* 333 */       sb.append("XP: " + player.experience + newline + newline);
/*     */       
/* 335 */       sb.append("Health: " + StringHelper.formatFloat(player.getHealth(), 1, 1) + "/" + player.getMaxHealth() + newline + newline);
/*     */       
/* 337 */       sb.append("Satiation: " + player.getSatiation() + "/" + player.getSatiationLimit() + newline);
/* 338 */       sb.append("Nutrition: " + player.getNutrition() + "/" + player.getNutritionLimit() + newline + newline);
/*     */ 
/*     */ 
/*     */       
/* 342 */       if (player instanceof ServerPlayer) {
/*     */         
/* 344 */         protein = player.getAsEntityPlayerMP().getProtein();
/* 345 */         phytonutrients = player.getAsEntityPlayerMP().getPhytonutrients();
/*     */       }
/*     */       else {
/*     */         
/* 349 */         protein = phytonutrients = 0;
/*     */       } 
/*     */       
/* 352 */       sb.append("Protein: " + protein + " (" + (100 * protein / 160000) + "%)" + newline);
/* 353 */       sb.append("Phytonutrients: " + phytonutrients + " (" + (100 * phytonutrients / 160000) + "%)" + newline + newline);
/*     */ 
/*     */       
/* 356 */       sb.append(getMeleeDamageString(player) + newline + newline);
/*     */     }
/*     */     else {
/*     */       
/* 360 */       sb.append("Health: " + StringHelper.formatFloat(entity_living_base.getHealth(), 1, 1) + "/" + entity_living_base.getMaxHealth() + newline + newline);
/*     */ 
/*     */       
/* 363 */       sb.append(getMeleeDamageString(entity_living_base) + newline + newline);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 369 */     appendSectionProtection(entity_living_base, sb);
/* 370 */     appendSectionResistance(entity_living_base, sb);
/* 371 */     appendSectionPotionEffects(entity_living_base, sb);
/* 372 */     appendSectionEquipment(entity_living_base, sb);
/* 373 */     appendSectionInventory(entity_living_base, sb);
/*     */     
/* 375 */     if (entity_living_base.ridingEntity instanceof EntityLivingBase) {
/*     */       
/* 377 */       EntityLivingBase riding_entity = entity_living_base.ridingEntity.getAsEntityLivingBase();
/*     */       
/* 379 */       sb.append(newline + getStatsDump("Mount (" + riding_entity.getEntityName() + ")", riding_entity));
/*     */     } 
/*     */     
/* 382 */     return sb;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Packet94CreateFile generatePacketFor(EntityLivingBase entity_living_base) {
/* 387 */     byte[] content = getStatsDump(null, entity_living_base).toString().getBytes();
/*     */     
/* 389 */     StringBuilder filepath = new StringBuilder("MITE/stats/dump/");
/*     */     
/* 391 */     if (entity_living_base.isEntityPlayer()) {
/*     */       
/* 393 */       filepath.append(entity_living_base.getEntityName());
/*     */     }
/*     */     else {
/*     */       
/* 397 */       filepath.append(entity_living_base.getEntityName());
/* 398 */       filepath.append("/");
/* 399 */       filepath.append(entity_living_base.getUniqueIDSilent());
/*     */     } 
/*     */     
/* 402 */     filepath.append(".txt");
/*     */     
/* 404 */     return (new Packet94CreateFile(filepath.toString(), content)).setOptions(1, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityStatsDump.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */