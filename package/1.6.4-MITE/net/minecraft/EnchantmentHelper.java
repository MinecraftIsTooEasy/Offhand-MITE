/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnchantmentHelper
/*     */ {
/*  15 */   private static final Random enchantmentRand = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasEnchantment(ItemStack item_stack, Enchantment enchantment) {
/*  29 */     return (getEnchantmentLevel(enchantment.effectId, item_stack) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEnchantmentLevel(int par0, ItemStack par1ItemStack) {
/*  37 */     if (par1ItemStack == null)
/*     */     {
/*  39 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  43 */     NBTTagList var2 = par1ItemStack.getEnchantmentTagList();
/*     */     
/*  45 */     if (var2 == null)
/*     */     {
/*  47 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  51 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/*  53 */       short var4 = ((NBTTagCompound)var2.tagAt(var3)).getShort("id");
/*  54 */       short var5 = ((NBTTagCompound)var2.tagAt(var3)).getShort("lvl");
/*     */       
/*  56 */       if (var4 == par0)
/*     */       {
/*  58 */         return var5;
/*     */       }
/*     */     } 
/*     */     
/*  62 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEnchantmentLevel(Enchantment enchantment, ItemStack item_stack) {
/*  69 */     return getEnchantmentLevel(enchantment.effectId, item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getEnchantmentLevelFraction(Enchantment enchantment, ItemStack item_stack) {
/*  74 */     if (!hasEnchantment(item_stack, enchantment)) {
/*  75 */       return 0.0F;
/*     */     }
/*  77 */     return enchantment.hasLevels() ? (getEnchantmentLevel(enchantment, item_stack) / enchantment.getNumLevels()) : 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getEnchantmentLevelFractionOfInteger(Enchantment enchantment, ItemStack item_stack, int integer) {
/*  82 */     if (!hasEnchantment(item_stack, enchantment)) {
/*  83 */       return 0;
/*     */     }
/*  85 */     return enchantment.hasLevels() ? Math.round(integer * getEnchantmentLevelFraction(enchantment, item_stack)) : integer;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getEnchantmentLevels(Enchantment enchantment, ItemStack[] item_stacks) {
/*  90 */     int levels = 0;
/*     */     
/*  92 */     for (int i = 0; i < item_stacks.length; i++) {
/*  93 */       levels += getEnchantmentLevel(enchantment, item_stacks[i]);
/*     */     }
/*  95 */     return levels;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map getEnchantmentsMapFromTags(NBTTagList enchantment_tag_list) {
/* 100 */     LinkedHashMap<Object, Object> map = new LinkedHashMap<Object, Object>();
/*     */     
/* 102 */     if (enchantment_tag_list == null) {
/* 103 */       return map;
/*     */     }
/* 105 */     for (int i = 0; i < enchantment_tag_list.tagCount(); i++) {
/*     */       
/* 107 */       NBTTagCompound tag = (NBTTagCompound)enchantment_tag_list.tagAt(i);
/* 108 */       map.put(Integer.valueOf(tag.getShort("id")), Integer.valueOf(tag.getShort("lvl")));
/*     */     } 
/*     */     
/* 111 */     return map;
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
/*     */   public static Map getEnchantmentsMap(ItemStack item_stack) {
/* 140 */     if (item_stack.getItem() == Item.enchantedBook) {
/* 141 */       Minecraft.setErrorMessage("getEnchantmentsMap: item is enchanted book, wrong func?");
/*     */     }
/* 143 */     return getEnchantmentsMapFromTags(item_stack.getEnchantmentTagList());
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map getStoredEnchantmentsMap(ItemStack item_stack) {
/* 148 */     if (item_stack.getItem() != Item.enchantedBook) {
/* 149 */       Minecraft.setErrorMessage("getStoredEnchantmentsMap: item is not enchanted book, wrong func?");
/*     */     }
/* 151 */     return getEnchantmentsMapFromTags(item_stack.getStoredEnchantmentTagList());
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
/*     */   public static void setEnchantments(Map par0Map, ItemStack par1ItemStack) {
/* 179 */     NBTTagList var2 = new NBTTagList();
/* 180 */     Iterator<Integer> var3 = par0Map.keySet().iterator();
/*     */     
/* 182 */     while (var3.hasNext()) {
/*     */       
/* 184 */       int var4 = ((Integer)var3.next()).intValue();
/* 185 */       NBTTagCompound var5 = new NBTTagCompound();
/* 186 */       var5.setShort("id", (short)var4);
/* 187 */       var5.setShort("lvl", (short)((Integer)par0Map.get(Integer.valueOf(var4))).intValue());
/* 188 */       var2.appendTag(var5);
/*     */       
/* 190 */       if (par1ItemStack.itemID == Item.enchantedBook.itemID)
/*     */       {
/* 192 */         Item.enchantedBook.addEnchantment(par1ItemStack, new EnchantmentData(var4, ((Integer)par0Map.get(Integer.valueOf(var4))).intValue()));
/*     */       }
/*     */     } 
/*     */     
/* 196 */     if (var2.tagCount() > 0) {
/*     */       
/* 198 */       if (par1ItemStack.itemID != Item.enchantedBook.itemID)
/*     */       {
/* 200 */         par1ItemStack.setTagInfo("ench", var2);
/*     */       }
/*     */     }
/* 203 */     else if (par1ItemStack.hasTagCompound()) {
/*     */       
/* 205 */       par1ItemStack.getTagCompound().removeTag("ench");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasValidEnchantmentForItem(NBTTagList enchantments, Item item) {
/* 212 */     for (int i = 0; i < enchantments.tagCount(); i++) {
/*     */       
/* 214 */       NBTTagCompound tag = (NBTTagCompound)enchantments.tagAt(i);
/*     */       
/* 216 */       short id = tag.getShort("id");
/*     */       
/* 218 */       Enchantment enchantment = Enchantment.enchantmentsList[id];
/*     */       
/* 220 */       if (enchantment.canEnchantItem(item)) {
/* 221 */         return true;
/*     */       }
/*     */     } 
/* 224 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMaxEnchantmentLevel(int par0, ItemStack[] par1ArrayOfItemStack) {
/* 232 */     if (par1ArrayOfItemStack == null)
/*     */     {
/* 234 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 238 */     int var2 = 0;
/* 239 */     ItemStack[] var3 = par1ArrayOfItemStack;
/* 240 */     int var4 = par1ArrayOfItemStack.length;
/*     */     
/* 242 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 244 */       ItemStack var6 = var3[var5];
/* 245 */       int var7 = getEnchantmentLevel(par0, var6);
/*     */       
/* 247 */       if (var7 > var2)
/*     */       {
/* 249 */         var2 = var7;
/*     */       }
/*     */     } 
/*     */     
/* 253 */     return var2;
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
/*     */   public static int getKnockbackModifier(EntityLivingBase par0EntityLivingBase, EntityLivingBase par1EntityLivingBase) {
/* 330 */     return getEnchantmentLevel(Enchantment.knockback.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFireAspectModifier(EntityLivingBase par0EntityLivingBase) {
/* 335 */     return getEnchantmentLevel(Enchantment.fireAspect.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getRespiration(EntityLivingBase par0EntityLivingBase) {
/* 343 */     return getMaxEnchantmentLevel(Enchantment.respiration.effectId, par0EntityLivingBase.getLastActiveItems());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEfficiencyModifier(EntityLivingBase par0EntityLivingBase) {
/* 351 */     return getEnchantmentLevel(Enchantment.efficiency.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getSilkTouchModifier(EntityLivingBase par0EntityLivingBase) {
/* 359 */     return (getEnchantmentLevel(Enchantment.silkTouch.effectId, par0EntityLivingBase.getHeldItemStack()) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFortuneModifier(EntityLivingBase par0EntityLivingBase) {
/* 367 */     if (par0EntityLivingBase == null) {
/* 368 */       return 0;
/*     */     }
/* 370 */     return getEnchantmentLevel(Enchantment.fortune.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getLootingModifier(EntityLivingBase par0EntityLivingBase) {
/* 378 */     return getEnchantmentLevel(Enchantment.looting.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getAquaAffinityModifier(EntityLivingBase par0EntityLivingBase) {
/* 386 */     return (getMaxEnchantmentLevel(Enchantment.aquaAffinity.effectId, par0EntityLivingBase.getLastActiveItems()) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_92098_i(EntityLivingBase par0EntityLivingBase) {
/* 391 */     return getMaxEnchantmentLevel(Enchantment.thorns.effectId, par0EntityLivingBase.getLastActiveItems());
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack func_92099_a(Enchantment par0Enchantment, EntityLivingBase par1EntityLivingBase) {
/* 396 */     ItemStack[] var2 = par1EntityLivingBase.getLastActiveItems();
/* 397 */     int var3 = var2.length;
/*     */     
/* 399 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 401 */       ItemStack var5 = var2[var4];
/*     */       
/* 403 */       if (var5 != null && getEnchantmentLevel(par0Enchantment.effectId, var5) > 0)
/*     */       {
/* 405 */         return var5;
/*     */       }
/*     */     } 
/*     */     
/* 409 */     return null;
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
/*     */   public static ItemStack addRandomEnchantment(Random par0Random, ItemStack par1ItemStack, int enchantment_levels) {
/* 444 */     enchantment_levels = getEnchantmentLevelsAlteredByItemEnchantability(enchantment_levels, par1ItemStack.getItem());
/*     */     
/* 446 */     if (enchantment_levels < 1) {
/* 447 */       return par1ItemStack;
/*     */     }
/* 449 */     List var3 = buildEnchantmentList(par0Random, par1ItemStack, enchantment_levels);
/* 450 */     boolean var4 = (par1ItemStack.itemID == Item.book.itemID);
/*     */     
/* 452 */     if (var4)
/*     */     {
/* 454 */       par1ItemStack.itemID = Item.enchantedBook.itemID;
/*     */     }
/*     */     
/* 457 */     if (var3 != null) {
/*     */       
/* 459 */       Iterator<EnchantmentData> var5 = var3.iterator();
/*     */       
/* 461 */       while (var5.hasNext()) {
/*     */         
/* 463 */         EnchantmentData var6 = var5.next();
/*     */         
/* 465 */         if (var4) {
/*     */           
/* 467 */           Item.enchantedBook.addEnchantment(par1ItemStack, var6);
/*     */           
/*     */           continue;
/*     */         } 
/* 471 */         par1ItemStack.addEnchantment(var6.enchantmentobj, var6.enchantmentLevel);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 476 */     return par1ItemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getEnchantmentLevelsAlteredByItemEnchantability(int enchantment_levels, Item item) {
/* 481 */     int item_enchantability = item.getItemEnchantability();
/*     */     
/* 483 */     if (item_enchantability < 1) {
/* 484 */       return 0;
/*     */     }
/* 486 */     if (enchantment_levels <= item_enchantability) {
/* 487 */       return enchantment_levels;
/*     */     }
/* 489 */     float enchantment_levels_float = item_enchantability;
/*     */     
/* 491 */     for (int i = item_enchantability + 1; i <= enchantment_levels; i++) {
/*     */       
/* 493 */       if (i <= item_enchantability * 2) {
/* 494 */         enchantment_levels_float += 0.5F;
/* 495 */       } else if (i <= item_enchantability * 3) {
/* 496 */         enchantment_levels_float += 0.25F;
/*     */       } else {
/*     */         break;
/*     */       } 
/*     */     } 
/* 501 */     return Math.round(enchantment_levels_float);
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
/*     */ 
/*     */ 
/*     */   
/*     */   private static void removeEnchantmentsFromMapThatConflict(Map map, ArrayList<EnchantmentData> enchantments) {
/* 590 */     for (int i = 0; i < enchantments.size(); i++) {
/*     */       
/* 592 */       EnchantmentData enchantment_data = enchantments.get(i);
/* 593 */       Enchantment enchantment = enchantment_data.enchantmentobj;
/*     */       
/* 595 */       Iterator<Integer> iterator = map.keySet().iterator();
/*     */       
/* 597 */       while (iterator.hasNext()) {
/*     */         
/* 599 */         int id = ((Integer)iterator.next()).intValue();
/*     */         
/* 601 */         if (!enchantment.canApplyTogether(Enchantment.get(id))) {
/* 602 */           iterator.remove();
/*     */         }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List buildEnchantmentList(Random random, ItemStack item_stack, int enchantment_levels) {
/* 666 */     Item item = item_stack.getItem();
/* 667 */     int enchantability = item.getItemEnchantability();
/*     */     
/* 669 */     if (enchantability <= 0) {
/* 670 */       return null;
/*     */     }
/* 672 */     float randomness = 1.0F + (random.nextFloat() - 0.5F) * 0.5F;
/*     */ 
/*     */     
/* 675 */     int adjusted_enchantment_levels = (int)(enchantment_levels * randomness);
/*     */     
/* 677 */     if (adjusted_enchantment_levels < 1) {
/* 678 */       adjusted_enchantment_levels = 1;
/*     */     }
/* 680 */     ArrayList<EnchantmentData> enchantments_for_item = new ArrayList();
/*     */     
/* 682 */     while (adjusted_enchantment_levels > 0) {
/*     */       
/* 684 */       Map all_possible_enchantments = mapEnchantmentData(adjusted_enchantment_levels, item_stack);
/*     */       
/* 686 */       if (all_possible_enchantments == null) {
/*     */         break;
/*     */       }
/* 689 */       removeEnchantmentsFromMapThatConflict(all_possible_enchantments, enchantments_for_item);
/*     */       
/* 691 */       if (all_possible_enchantments.isEmpty()) {
/*     */         break;
/*     */       }
/* 694 */       EnchantmentData enchantment_data = (EnchantmentData)WeightedRandom.getRandomItem(random, all_possible_enchantments.values());
/*     */       
/* 696 */       if (enchantment_data == null) {
/*     */         break;
/*     */       }
/* 699 */       Enchantment enchantment = enchantment_data.enchantmentobj;
/*     */       
/* 701 */       if (enchantments_for_item.size() < 2 && all_possible_enchantments.size() > 1 && enchantment.hasLevels() && random.nextInt(2) == 0) {
/* 702 */         enchantment_data.enchantmentLevel = random.nextInt(enchantment_data.enchantmentLevel) + 1;
/*     */       }
/* 704 */       enchantments_for_item.add(enchantment_data);
/*     */       
/* 706 */       adjusted_enchantment_levels -= enchantment.hasLevels() ? enchantment.getMinEnchantmentLevelsCost(enchantment_data.enchantmentLevel) : enchantment.getMinEnchantmentLevelsCost();
/*     */       
/* 708 */       adjusted_enchantment_levels -= 5;
/*     */ 
/*     */ 
/*     */       
/* 712 */       if (adjusted_enchantment_levels < 5 || enchantments_for_item.size() > 2) {
/*     */         break;
/*     */       }
/*     */     } 
/* 716 */     ArrayList<EnchantmentData> enchantments_for_item_shuffled = new ArrayList();
/*     */     
/* 718 */     int n = enchantments_for_item.size();
/*     */     
/* 720 */     while (n > 0) {
/*     */       
/* 722 */       int index = random.nextInt(enchantments_for_item.size());
/*     */       
/* 724 */       EnchantmentData enchantment_data = enchantments_for_item.get(index);
/*     */       
/* 726 */       if (enchantment_data != null) {
/*     */         
/* 728 */         enchantments_for_item_shuffled.add(enchantment_data);
/* 729 */         enchantments_for_item.set(index, null);
/* 730 */         n--;
/*     */       } 
/*     */     } 
/*     */     
/* 734 */     return (enchantments_for_item_shuffled.size() == 0) ? null : enchantments_for_item_shuffled;
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
/*     */   private static Map mapEnchantmentData(int enchantment_levels, ItemStack item_stack) {
/* 779 */     Item item = item_stack.getItem();
/* 780 */     boolean is_book = (item == Item.book);
/*     */     
/* 782 */     HashMap<Object, Object> map = new HashMap<Object, Object>();
/*     */     
/* 784 */     for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
/*     */       
/* 786 */       Enchantment enchantment = Enchantment.get(i);
/*     */       
/* 788 */       if (enchantment != null)
/*     */       {
/*     */         
/* 791 */         if (is_book || enchantment.canEnchantItem(item))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 817 */           if (enchantment.hasLevels()) {
/*     */             
/* 819 */             for (int level = enchantment.getNumLevels(); level > 0; level--) {
/*     */               
/* 821 */               if (enchantment.getMinEnchantmentLevelsCost(level) <= enchantment_levels) {
/*     */                 
/* 823 */                 map.put(Integer.valueOf(enchantment.effectId), new EnchantmentData(enchantment, level));
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 831 */           } else if (enchantment.getMinEnchantmentLevelsCost() <= enchantment_levels) {
/* 832 */             map.put(Integer.valueOf(enchantment.effectId), new EnchantmentData(enchantment, 1));
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/* 837 */     return (map.size() == 0) ? null : map;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getStunModifier(EntityLivingBase par0EntityLivingBase, EntityLivingBase par1EntityLivingBase) {
/* 842 */     return getEnchantmentLevel(Enchantment.stun.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFishingFortuneModifier(EntityLivingBase par0EntityLivingBase) {
/* 847 */     return getEnchantmentLevel(Enchantment.fishing_fortune.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFertilityModifier(EntityLivingBase par0EntityLivingBase) {
/* 852 */     return getEnchantmentLevel(Enchantment.fertility.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTreeFellingModifier(EntityLivingBase par0EntityLivingBase) {
/* 857 */     return getEnchantmentLevel(Enchantment.tree_felling.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getVampiricTransfer(EntityLivingBase par0EntityLivingBase, EntityLivingBase par1EntityLivingBase, float inflicted_damage) {
/* 862 */     if (inflicted_damage <= 0.0F || par1EntityLivingBase == null || !par1EntityLivingBase.isEntityBiologicallyAlive()) {
/* 863 */       return 0;
/*     */     }
/* 865 */     int potential_effect = getEnchantmentLevel(Enchantment.vampiric.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */     
/* 867 */     if (Math.random() * 10.0D > potential_effect) {
/* 868 */       return 0;
/*     */     }
/* 870 */     int transfer = (int)((inflicted_damage * 0.5F) * Math.random());
/*     */     
/* 872 */     if (transfer < 1) {
/* 873 */       transfer = 1;
/*     */     }
/* 875 */     return transfer;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getSpeedModifier(EntityLivingBase par0EntityLivingBase) {
/* 880 */     return 1.0F + getMaxEnchantmentLevel(Enchantment.speed.effectId, par0EntityLivingBase.getLastActiveItems()) * 0.05F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getRegenerationModifier(EntityLivingBase par0EntityLivingBase) {
/* 885 */     return 1.0F + getMaxEnchantmentLevel(Enchantment.regeneration.effectId, par0EntityLivingBase.getLastActiveItems()) * 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFreeActionModifier(EntityLivingBase par0EntityLivingBase) {
/* 890 */     return getMaxEnchantmentLevel(Enchantment.free_action.effectId, par0EntityLivingBase.getLastActiveItems());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getButcheringModifier(EntityLivingBase par0EntityLivingBase) {
/* 895 */     return getEnchantmentLevel(Enchantment.butchering.effectId, par0EntityLivingBase.getHeldItemStack());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getEnduranceModifier(EntityLivingBase par0EntityLivingBase) {
/* 901 */     return 1.0F - getMaxEnchantmentLevel(Enchantment.endurance.effectId, par0EntityLivingBase.getLastActiveItems()) * 0.2F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */