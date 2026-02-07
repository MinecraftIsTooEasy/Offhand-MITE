/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemBow
/*     */   extends Item
/*     */   implements IDamageableItem
/*     */ {
/* 134 */   private static final Material[] possible_arrow_materials = new Material[] { Material.flint, Material.obsidian, Material.copper, Material.silver, Material.rusted_iron, Material.gold, Material.iron, Material.mithril, Material.adamantium, Material.ancient_metal };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static final String[] bow_pull_icon_name_array = new String[possible_arrow_materials.length * 3];
/*     */   static {
/* 145 */     for (int arrow_index = 0; arrow_index < possible_arrow_materials.length; arrow_index++) {
/*     */       
/* 147 */       Material material = possible_arrow_materials[arrow_index];
/*     */       
/* 149 */       for (int icon_index = 0; icon_index < 3; icon_index++) {
/* 150 */         bow_pull_icon_name_array[arrow_index * 3 + icon_index] = material.name + "_arrow_" + icon_index;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Icon[] iconArray;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Material reinforcement_material;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBow(int id, Material reinforcement_material) {
/* 177 */     super(id, Material.wood, "bows/" + reinforcement_material.name + "/");
/*     */     
/* 179 */     if (reinforcement_material != null && reinforcement_material != Material.wood) {
/* 180 */       addMaterial(new Material[] { reinforcement_material });
/*     */     }
/* 182 */     this.reinforcement_material = reinforcement_material;
/* 183 */     setMaxStackSize(1);
/* 184 */     setMaxDamage((reinforcement_material == Material.mithril) ? 128 : ((reinforcement_material == Material.ancient_metal) ? 64 : 32));
/* 185 */     setCreativeTab(CreativeTabs.tabCombat);
/*     */     
/* 187 */     setSkillsetThatCanRepairThis(reinforcement_material.isMetal() ? (Skill.ARCHERY.id + Skill.BLACKSMITHING.id) : Skill.ARCHERY.id);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTicksForMaxPull(ItemStack item_stack) {
/* 192 */     return 20 - EnchantmentHelper.getEnchantmentLevelFractionOfInteger(Enchantment.quickness, item_stack, 10);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTicksPulled(ItemStack item_stack, int item_in_use_count) {
/* 197 */     return item_stack.getMaxItemUseDuration() - item_in_use_count;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getFractionPulled(ItemStack item_stack, int item_in_use_count) {
/* 202 */     return Math.min(getTicksPulled(item_stack, item_in_use_count) / getTicksForMaxPull(item_stack), 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlayerStoppedUsing(ItemStack item_stack, World world, EntityPlayer player, int item_in_use_count) {
/* 207 */     if (world.isRemote) {
/*     */       return;
/*     */     }
/* 210 */     ItemArrow arrow = player.inventory.getReadiedArrow();
/*     */     
/* 212 */     if (arrow == null)
/*     */     {
/* 214 */       if (player.inCreativeMode()) {
/* 215 */         arrow = player.nocked_arrow;
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     float fraction_pulled = getFractionPulled(item_stack, item_in_use_count);
/* 225 */     fraction_pulled = (fraction_pulled * fraction_pulled + fraction_pulled * 2.0F) / 3.0F;
/*     */     
/* 227 */     if (fraction_pulled < 0.1F) {
/*     */       return;
/*     */     }
/* 230 */     if (fraction_pulled > 1.0F) {
/* 231 */       fraction_pulled = 1.0F;
/*     */     }
/* 233 */     EntityArrow entity_arrow = new EntityArrow(world, player, fraction_pulled * 2.0F, arrow, item_stack.isItemEnchanted());
/*     */     
/* 235 */     player.nocked_arrow = null;
/*     */     
/* 237 */     if (fraction_pulled == 1.0F) {
/* 238 */       entity_arrow.setIsCritical(true);
/*     */     }
/* 240 */     int power = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, item_stack);
/*     */     
/* 242 */     if (power > 0) {
/* 243 */       entity_arrow.setDamage(entity_arrow.getDamage() + (power * 0.5F) + 0.5D);
/*     */     }
/* 245 */     int punch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, item_stack);
/*     */     
/* 247 */     if (punch > 0) {
/* 248 */       entity_arrow.setKnockbackStrength(punch);
/*     */     }
/* 250 */     if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, item_stack) > 0) {
/* 251 */       entity_arrow.setFire(100);
/*     */     }
/* 253 */     player.tryDamageHeldItem(DamageSource.generic, 1);
/*     */     
/* 255 */     world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + fraction_pulled * 0.5F);
/*     */     
/* 257 */     if (player.inCreativeMode()) {
/* 258 */       entity_arrow.canBePickedUp = 2;
/*     */     } else {
/* 260 */       player.inventory.consumeArrow();
/*     */     } 
/* 262 */     if (!world.isRemote) {
/* 263 */       world.spawnEntityInWorld(entity_arrow);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {}
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 270 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumItemInUseAction getItemInUseAction(ItemStack par1ItemStack, EntityPlayer player) {
/* 275 */     return EnumItemInUseAction.BOW;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 280 */     if (player.inCreativeMode() || player.inventory.getReadiedArrow() != null) {
/*     */       
/* 282 */       player.nocked_arrow = player.inventory.getReadiedArrow();
/*     */       
/* 284 */       if (player.nocked_arrow == null && player.inCreativeMode()) {
/* 285 */         player.nocked_arrow = Item.arrowFlint;
/*     */       }
/* 287 */       if (player.onServer()) {
/* 288 */         player.sendPacketToAssociatedPlayers((new Packet85SimpleSignal(EnumSignal.nocked_arrow)).setShort(player.nocked_arrow.itemID).setEntityID(player), false);
/*     */       }
/* 290 */       player.setHeldItemInUse();
/*     */       
/* 292 */       return true;
/*     */     } 
/*     */     
/* 295 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 300 */     return (getMaterialForEnchantment()).enchantability;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 305 */     this.itemIcon = par1IconRegister.registerIcon(getIconString() + "standby");
/* 306 */     this.iconArray = new Icon[bow_pull_icon_name_array.length];
/*     */     
/* 308 */     for (int i = 0; i < this.iconArray.length; i++) {
/* 309 */       this.iconArray[i] = par1IconRegister.registerIcon(getIconString() + bow_pull_icon_name_array[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public Icon getItemIconForUseDuration(int par1, EntityPlayer player) {
/* 314 */     if (player.nocked_arrow == null) {
/*     */       
/* 316 */       Minecraft.setErrorMessage("getItemIconForUseDuration: nocked_arrow was null!");
/* 317 */       return this.iconArray[par1];
/*     */     } 
/*     */     
/* 320 */     return this.iconArray[par1 + player.nocked_arrow.getArrowIndex() * 3];
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialForDurability() {
/* 325 */     return Material.wood;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialForRepairs() {
/* 330 */     return (this.reinforcement_material == null) ? Material.wood : this.reinforcement_material;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumComponentsForDurability() {
/* 335 */     return this.reinforcement_material.isMetal() ? 1 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRepairCost() {
/* 341 */     return getNumComponentsForDurability() * 2;
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
/*     */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 358 */     if (extended_info && this.reinforcement_material.isMetal()) {
/*     */       
/* 360 */       int bonus = (this.reinforcement_material == Material.mithril) ? 25 : 10;
/*     */       
/* 362 */       info.add("");
/*     */       
/* 364 */       info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.velocityBonus", new Object[] { Integer.valueOf(bonus) }));
/*     */     } 
/*     */     
/* 367 */     super.addInformation(item_stack, player, info, extended_info, slot);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasQuality() {
/* 372 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean similarToItemsOfSameClass() {
/* 377 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */