/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ItemArmor
/*     */   extends Item
/*     */   implements IDamageableItem
/*     */ {
/*  11 */   private static final String[] field_94606_cu = new String[] { "leather_helmet_overlay", "leather_chestplate_overlay", "leather_leggings_overlay", "leather_boots_overlay" };
/*  12 */   public static final String[] field_94603_a = new String[] { "empty_armor_slot_helmet", "empty_armor_slot_chestplate", "empty_armor_slot_leggings", "empty_armor_slot_boots" };
/*  13 */   private static final IBehaviorDispenseItem field_96605_cw = new BehaviorDispenseArmor();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int armorType;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Icon field_94605_cw;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Icon field_94604_cx;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Material effective_material;
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean is_leather;
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean is_chain_mail;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemArmor(int par1, Material material, int par4, boolean is_chain_mail) {
/*  47 */     super(par1, material, (String)null);
/*  48 */     this.effective_material = material;
/*  49 */     setTextureName("armor/" + material.name + (is_chain_mail ? "_chainmail_" : "_") + getArmorType());
/*  50 */     this.armorType = par4;
/*     */ 
/*     */     
/*  53 */     this.is_leather = (this.effective_material == Material.leather);
/*  54 */     this.is_chain_mail = is_chain_mail;
/*     */ 
/*     */ 
/*     */     
/*  58 */     setMaxDamage(getMultipliedDurability());
/*     */     
/*  60 */     setMaxStackSize(1);
/*  61 */     setCreativeTab(CreativeTabs.tabCombat);
/*  62 */     BlockDispenser.dispenseBehaviorRegistry.putObject(this, field_96605_cw);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     setSkillsetThatCanRepairThis(material.isMetal() ? Skill.BLACKSMITHING.id : -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getArmorType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getMultipliedDurability() {
/*  93 */     float durability = getNumComponentsForDurability() * this.effective_material.durability;
/*     */     
/*  95 */     if (!this.is_chain_mail) {
/*  96 */       durability *= 2.0F;
/*     */     }
/*  98 */     return (int)durability;
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
/*     */   public int getMaterialProtection() {
/*     */     int protection;
/* 128 */     if (this.effective_material == Material.leather) {
/* 129 */       protection = 2;
/* 130 */     } else if (this.effective_material == Material.rusted_iron) {
/* 131 */       protection = 6;
/* 132 */     } else if (this.effective_material == Material.copper) {
/* 133 */       protection = 7;
/* 134 */     } else if (this.effective_material == Material.silver) {
/* 135 */       protection = 7;
/* 136 */     } else if (this.effective_material == Material.gold) {
/* 137 */       protection = 6;
/* 138 */     } else if (this.effective_material == Material.iron || this.effective_material == Material.ancient_metal) {
/* 139 */       protection = 8;
/* 140 */     } else if (this.effective_material == Material.mithril) {
/* 141 */       protection = 9;
/* 142 */     } else if (this.effective_material == Material.adamantium) {
/* 143 */       protection = 10;
/*     */     } else {
/* 145 */       return 0;
/*     */     } 
/* 147 */     if (this.is_chain_mail) {
/* 148 */       protection -= 2;
/*     */     }
/* 150 */     return protection;
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
/*     */   public final float getMultipliedProtection(ItemStack item_stack) {
/* 168 */     float multiplied_protection = (getNumComponentsForDurability() * getMaterialProtection()) / 24.0F;
/*     */     
/* 170 */     if (item_stack != null && item_stack.hasEnchantment(Enchantment.protection, false)) {
/* 171 */       multiplied_protection += multiplied_protection * item_stack.getEnchantmentLevelFraction(Enchantment.protection) * 0.5F;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 176 */     return multiplied_protection;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
/* 181 */     if (par2 > 0)
/*     */     {
/* 183 */       return 16777215;
/*     */     }
/*     */ 
/*     */     
/* 187 */     int var3 = getColor(par1ItemStack);
/*     */     
/* 189 */     if (var3 < 0)
/*     */     {
/* 191 */       var3 = 16777215;
/*     */     }
/*     */     
/* 194 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean requiresMultipleRenderPasses() {
/* 201 */     return (this.effective_material == Material.leather);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 210 */     return (getMaterialForEnchantment()).enchantability;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Material getArmorMaterial() {
/* 218 */     return this.effective_material;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasColor(ItemStack par1ItemStack) {
/* 227 */     return (this.effective_material != Material.leather) ? false : (!par1ItemStack.hasTagCompound() ? false : (!par1ItemStack.getTagCompound().hasKey("display") ? false : par1ItemStack.getTagCompound().getCompoundTag("display").hasKey("color")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColor(ItemStack par1ItemStack) {
/* 236 */     if (this.effective_material != Material.leather)
/*     */     {
/* 238 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 242 */     NBTTagCompound var2 = par1ItemStack.getTagCompound();
/*     */     
/* 244 */     if (var2 == null)
/*     */     {
/* 246 */       return 10511680;
/*     */     }
/*     */ 
/*     */     
/* 250 */     NBTTagCompound var3 = var2.getCompoundTag("display");
/* 251 */     return (var3 == null) ? 10511680 : (var3.hasKey("color") ? var3.getInteger("color") : 10511680);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIconFromSubtypeForRenderPass(int par1, int par2) {
/* 261 */     return (par2 == 1) ? this.field_94605_cw : super.getIconFromSubtypeForRenderPass(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeColor(ItemStack par1ItemStack) {
/* 270 */     if (this.effective_material == Material.leather) {
/*     */       
/* 272 */       NBTTagCompound var2 = par1ItemStack.getTagCompound();
/*     */       
/* 274 */       if (var2 != null) {
/*     */         
/* 276 */         NBTTagCompound var3 = var2.getCompoundTag("display");
/*     */         
/* 278 */         if (var3.hasKey("color"))
/*     */         {
/* 280 */           var3.removeTag("color");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_82813_b(ItemStack par1ItemStack, int par2) {
/* 289 */     if (this.effective_material != Material.leather)
/*     */     {
/* 291 */       throw new UnsupportedOperationException("Can't dye non-leather!");
/*     */     }
/*     */ 
/*     */     
/* 295 */     NBTTagCompound var3 = par1ItemStack.getTagCompound();
/*     */     
/* 297 */     if (var3 == null) {
/*     */       
/* 299 */       var3 = new NBTTagCompound();
/* 300 */       par1ItemStack.setTagCompound(var3);
/*     */     } 
/*     */     
/* 303 */     NBTTagCompound var4 = var3.getCompoundTag("display");
/*     */     
/* 305 */     if (!var3.hasKey("display"))
/*     */     {
/* 307 */       var3.setCompoundTag("display", var4);
/*     */     }
/*     */     
/* 310 */     var4.setInteger("color", par2);
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 324 */     super.registerIcons(par1IconRegister);
/*     */ 
/*     */     
/* 327 */     if (this.effective_material == Material.leather)
/*     */     {
/* 329 */       this.field_94605_cw = par1IconRegister.registerIcon(field_94606_cu[this.armorType]);
/*     */     }
/*     */     
/* 332 */     this.field_94604_cx = par1IconRegister.registerIcon(field_94603_a[this.armorType]);
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 357 */     if (player.hasCurse(Curse.cannot_wear_armor, true)) {
/* 358 */       return false;
/*     */     }
/* 360 */     ItemStack item_stack = player.getHeldItemStack();
/*     */ 
/*     */     
/* 363 */     int index = EntityLiving.getEquipmentPosition(item_stack) - 1;
/* 364 */     ItemStack var5 = player.getCurrentArmor(index);
/*     */     
/* 366 */     if (var5 == null) {
/*     */       
/* 368 */       if (player.onServer()) {
/*     */         
/* 370 */         player.setCurrentItemOrArmor(index, item_stack.copy());
/* 371 */         player.convertOneOfHeldItem((ItemStack)null);
/*     */         
/* 373 */         player.suppressNextStatIncrement();
/*     */       } 
/*     */       
/* 376 */       return true;
/*     */     } 
/*     */     
/* 379 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Icon func_94602_b(int par0) {
/* 384 */     switch (par0) {
/*     */ 
/*     */       
/*     */       case 0:
/* 388 */         return Item.helmetIron.field_94604_cx;
/*     */ 
/*     */       
/*     */       case 1:
/* 392 */         return Item.plateIron.field_94604_cx;
/*     */ 
/*     */       
/*     */       case 2:
/* 396 */         return Item.legsIron.field_94604_cx;
/*     */ 
/*     */       
/*     */       case 3:
/* 400 */         return Item.bootsIron.field_94604_cx;
/*     */     } 
/*     */     
/* 403 */     return null;
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
/*     */   public String getTextureFilenamePrefix() {
/* 423 */     if (this.effective_material == Material.iron && this.is_chain_mail) {
/* 424 */       return "chainmail";
/*     */     }
/* 426 */     return this.effective_material.name + (this.is_chain_mail ? "_chainmail" : "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getDamageFactor(ItemStack item_stack, EntityLivingBase owner) {
/* 432 */     if (owner != null && !owner.isEntityPlayer()) {
/* 433 */       return 0.5F;
/*     */     }
/* 435 */     if (owner instanceof EntityPlayer && item_stack.getMaxDamage() > 1 && item_stack.getItemDamage() >= item_stack.getMaxDamage() - 1) {
/* 436 */       return 0.0F;
/*     */     }
/* 438 */     float armor_damage_factor = 2.0F - item_stack.getItemDamage() / item_stack.getItem().getMaxDamage(item_stack) * 2.0F;
/*     */     
/* 440 */     if (armor_damage_factor > 1.0F) {
/* 441 */       armor_damage_factor = 1.0F;
/*     */     }
/* 443 */     return armor_damage_factor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getProtectionAfterDamageFactor(ItemStack item_stack, EntityLivingBase owner) {
/* 452 */     return getMultipliedProtection(item_stack) * getDamageFactor(item_stack, owner);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 457 */     if (extended_info) {
/*     */       
/* 459 */       info.add("");
/*     */ 
/*     */       
/* 462 */       float protection = getProtectionAfterDamageFactor(item_stack, player);
/* 463 */       int decimal_places = (protection < 1.0F) ? 2 : 1;
/*     */ 
/*     */       
/* 466 */       info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.protectionBonus", new Object[] { StringHelper.formatFloat(protection, decimal_places, decimal_places) }));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasQuality() {
/* 472 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemArmor getMatchingArmor(Class<?> item_class, Material armor_material, boolean is_chain_mail) {
/* 477 */     ItemArmor matching_armor = null;
/*     */     
/* 479 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*     */       
/* 481 */       Item item = Item.getItem(i);
/*     */       
/* 483 */       if (item instanceof ItemArmor && item.getClass() == item_class) {
/*     */         
/* 485 */         ItemArmor armor = (ItemArmor)item;
/*     */         
/* 487 */         if (armor.getArmorMaterial() == armor_material && armor.is_chain_mail == is_chain_mail)
/*     */         {
/*     */ 
/*     */           
/* 491 */           if (matching_armor == null) {
/* 492 */             matching_armor = armor;
/*     */           } else {
/* 494 */             Minecraft.setErrorMessage("getMatchingArmor: more than one item matched " + item_class + ", " + armor_material + ", " + is_chain_mail);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 499 */     return matching_armor;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialForDurability() {
/* 504 */     return getArmorMaterial();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLeather() {
/* 509 */     return this.is_leather;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChainMail() {
/* 514 */     return this.is_chain_mail;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSolidMetal() {
/* 520 */     return (getArmorMaterial().isMetal() && !isChainMail());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBreakingEffect() {
/* 525 */     return !isLeather();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getTotalArmorProtection(ItemStack[] armors, DamageSource damage_source, boolean include_enchantments, EntityLivingBase owner) {
/* 531 */     float total_defense = 0.0F;
/*     */     
/* 533 */     if (damage_source != null && damage_source.isUnblockable()) {
/* 534 */       return total_defense;
/*     */     }
/* 536 */     if (damage_source == null || !damage_source.bypassesMundaneArmor())
/*     */     {
/* 538 */       for (int i = 0; i < armors.length; i++) {
/*     */         
/* 540 */         ItemStack item_stack = armors[i];
/*     */         
/* 542 */         if (item_stack != null) {
/*     */           
/* 544 */           Item item = item_stack.getItem();
/*     */           
/* 546 */           if (item instanceof ItemHorseArmor) {
/*     */             
/* 548 */             ItemHorseArmor barding = (ItemHorseArmor)item;
/*     */             
/* 550 */             total_defense += barding.getProtection();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 556 */           else if (item.isArmor()) {
/*     */ 
/*     */             
/* 559 */             ItemArmor armor = (ItemArmor)item_stack.getItem();
/*     */             
/* 561 */             total_defense += armor.getProtectionAfterDamageFactor(item_stack, owner);
/*     */           } 
/*     */         } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 594 */     if (include_enchantments)
/*     */     {
/* 596 */       total_defense += EnchantmentProtection.getTotalProtectionOfEnchantments(armors, damage_source, owner);
/*     */     }
/* 598 */     total_defense = MathHelper.tryFitToNearestInteger(total_defense, 1.0E-4F);
/*     */     
/* 600 */     return total_defense;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void showHandleChainMailSeparatelyErrorMsg() {
/* 605 */     Minecraft.setErrorMessage("getRepairCost: Chain mail should be handled separately");
/* 606 */     (new Exception()).printStackTrace();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getRepairCost(boolean for_chain_mail) {
/* 613 */     if (for_chain_mail) {
/* 614 */       showHandleChainMailSeparatelyErrorMsg();
/*     */     }
/* 616 */     return getNumComponentsForDurability() * 2 / ((isLeather() || for_chain_mail) ? 2 : 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getRepairCost() {
/* 621 */     return getRepairCost(this.is_chain_mail);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRepairCost() {
/* 626 */     return (getRepairCost(false) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCoverage() {
/* 632 */     float coverage = getNumComponentsForDurability() / 24.0F;
/*     */     
/* 634 */     if (this.is_chain_mail) {
/* 635 */       coverage /= 2.0F;
/*     */     }
/* 637 */     return coverage;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */