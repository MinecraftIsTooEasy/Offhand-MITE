/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemPotion
/*     */   extends Item
/*     */ {
/*  16 */   private HashMap effectCache = new HashMap<Object, Object>();
/*  17 */   private static final Map field_77835_b = new LinkedHashMap<Object, Object>();
/*     */   
/*     */   private Icon field_94591_c;
/*     */   
/*     */   private Icon field_94590_d;
/*     */   private Icon field_94592_ct;
/*     */   
/*     */   public ItemPotion(int par1) {
/*  25 */     super(par1, Material.glass, "potion");
/*  26 */     setMaxStackSize(1);
/*     */ 
/*     */ 
/*     */     
/*  30 */     setCreativeTab(CreativeTabs.tabBrewing);
/*     */     
/*  32 */     setCraftingDifficultyAsComponent(100.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEffects(ItemStack par1ItemStack) {
/*  40 */     if (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("CustomPotionEffects")) {
/*     */       
/*  42 */       ArrayList<PotionEffect> var6 = new ArrayList();
/*  43 */       NBTTagList var3 = par1ItemStack.getTagCompound().getTagList("CustomPotionEffects");
/*     */       
/*  45 */       for (int var4 = 0; var4 < var3.tagCount(); var4++) {
/*     */         
/*  47 */         NBTTagCompound var5 = (NBTTagCompound)var3.tagAt(var4);
/*  48 */         var6.add(PotionEffect.readCustomPotionEffectFromNBT(var5));
/*     */       } 
/*     */       
/*  51 */       return var6;
/*     */     } 
/*     */ 
/*     */     
/*  55 */     List var2 = (List)this.effectCache.get(Integer.valueOf(par1ItemStack.getItemSubtype()));
/*     */     
/*  57 */     if (var2 == null) {
/*     */       
/*  59 */       var2 = PotionHelper.getPotionEffects(par1ItemStack.getItemSubtype(), false);
/*  60 */       this.effectCache.put(Integer.valueOf(par1ItemStack.getItemSubtype()), var2);
/*     */     } 
/*     */     
/*  63 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEffects(int par1) {
/*  72 */     List var2 = (List)this.effectCache.get(Integer.valueOf(par1));
/*     */     
/*  74 */     if (var2 == null) {
/*     */       
/*  76 */       var2 = PotionHelper.getPotionEffects(par1, false);
/*  77 */       this.effectCache.put(Integer.valueOf(par1), var2);
/*     */     } 
/*     */     
/*  80 */     return var2;
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
/*     */   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
/* 121 */     if (player.onServer()) {
/*     */       
/* 123 */       List effects = getEffects(item_stack);
/*     */       
/* 125 */       if (effects != null) {
/*     */         
/* 127 */         Iterator<PotionEffect> i = effects.iterator();
/*     */         
/* 129 */         while (i.hasNext()) {
/* 130 */           player.addPotionEffect(new PotionEffect(i.next()));
/*     */         }
/*     */       } 
/*     */     } 
/* 134 */     super.onItemUseFinish(item_stack, world, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItemProducedOnItemUseFinish() {
/* 139 */     return glassBottle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 147 */     return 32;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrinkable(int item_subtype) {
/* 152 */     return !isSplash(item_subtype);
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 207 */     ItemStack item_stack = player.getHeldItemStack();
/*     */     
/* 209 */     if (isSplash(item_stack.getItemSubtype())) {
/*     */       
/* 211 */       if (player.onServer()) {
/*     */         
/* 213 */         WorldServer world = player.getWorldServer();
/*     */         
/* 215 */         world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 216 */         world.spawnEntityInWorld(new EntityPotion(world, player, item_stack));
/*     */         
/* 218 */         if (!player.inCreativeMode()) {
/* 219 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/* 222 */       return true;
/*     */     } 
/* 224 */     if (isBottleOfWater(player.getHeldItemStack())) {
/*     */       
/* 226 */       RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*     */       
/* 228 */       if (rc != null) {
/*     */ 
/*     */ 
/*     */         
/* 232 */         if (rc.getNeighborOfBlockHit() == Block.fire) {
/*     */           
/* 234 */           if (player.onServer()) {
/*     */             
/* 236 */             rc.world.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, null);
/* 237 */             player.convertOneOfHeldItem(new ItemStack(glassBottle));
/*     */           } 
/*     */           
/* 240 */           return true;
/*     */         } 
/*     */         
/* 243 */         Block block = rc.getBlockHit();
/*     */         
/* 245 */         int x = rc.block_hit_x;
/* 246 */         int y = rc.block_hit_y;
/* 247 */         int z = rc.block_hit_z;
/*     */         
/* 249 */         EnumFace face_hit = rc.face_hit;
/*     */         
/* 251 */         if (block instanceof BlockCrops || block instanceof BlockStem || block == Block.mushroomBrown) {
/*     */           
/* 253 */           block = rc.world.getBlock(x, --y, z);
/* 254 */           face_hit = EnumFace.TOP;
/*     */         } 
/*     */         
/* 257 */         if (block == Block.tilledField && face_hit == EnumFace.TOP && BlockFarmland.fertilize(rc.world, x, y, z, player.getHeldItemStack(), player)) {
/*     */           
/* 259 */           if (player.onServer() && !player.inCreativeMode()) {
/* 260 */             player.convertOneOfHeldItem(new ItemStack(glassBottle));
/*     */           }
/* 262 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 267 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBottleOfWater(ItemStack item_stack) {
/* 272 */     return (item_stack != null && item_stack.getItem() == potion && item_stack.getItemSubtype() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
/* 277 */     return !isBottleOfWater(item_stack);
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
/*     */   public Icon getIconFromSubtype(int par1) {
/* 294 */     return isSplash(par1) ? this.field_94591_c : this.field_94590_d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIconFromSubtypeForRenderPass(int par1, int par2) {
/* 302 */     return (par2 == 0) ? this.field_94592_ct : super.getIconFromSubtypeForRenderPass(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSplash(int par0) {
/* 310 */     return ((par0 & 0x4000) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromDamage(int par1) {
/* 315 */     return PotionHelper.func_77915_a(par1, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
/* 320 */     return (par2 > 0) ? 16777215 : getColorFromDamage(par1ItemStack.getItemSubtype());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresMultipleRenderPasses() {
/* 325 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEffectInstant(int par1) {
/* 330 */     List var2 = getEffects(par1);
/*     */     
/* 332 */     if (var2 != null && !var2.isEmpty()) {
/*     */       PotionEffect var4;
/* 334 */       Iterator<PotionEffect> var3 = var2.iterator();
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 339 */         if (!var3.hasNext())
/*     */         {
/* 341 */           return false;
/*     */         }
/*     */         
/* 344 */         var4 = var3.next();
/*     */       }
/* 346 */       while (!Potion.potionTypes[var4.getPotionID()].isInstant());
/*     */       
/* 348 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 352 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemDisplayName(ItemStack par1ItemStack) {
/* 359 */     if (par1ItemStack == null || par1ItemStack.getItemSubtype() == 0)
/*     */     {
/* 361 */       return StatCollector.translateToLocal("item.emptyPotion.name").trim();
/*     */     }
/*     */ 
/*     */     
/* 365 */     String var2 = "";
/*     */     
/* 367 */     if (isSplash(par1ItemStack.getItemSubtype()))
/*     */     {
/* 369 */       var2 = StatCollector.translateToLocal("potion.prefix.grenade").trim() + " ";
/*     */     }
/*     */     
/* 372 */     List<PotionEffect> var3 = Item.potion.getEffects(par1ItemStack);
/*     */ 
/*     */     
/* 375 */     if (var3 != null && !var3.isEmpty()) {
/*     */       
/* 377 */       String str = ((PotionEffect)var3.get(0)).getEffectName();
/* 378 */       str = str + ".postfix";
/* 379 */       return var2 + StatCollector.translateToLocal(str).trim();
/*     */     } 
/*     */ 
/*     */     
/* 383 */     String var4 = PotionHelper.func_77905_c(par1ItemStack.getItemSubtype());
/* 384 */     return StatCollector.translateToLocal(var4).trim() + " " + super.getItemDisplayName(par1ItemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4, Slot slot) {
/* 395 */     if (par1ItemStack.getItemSubtype() != 0) {
/*     */       
/* 397 */       List var5 = Item.potion.getEffects(par1ItemStack);
/* 398 */       HashMultimap var6 = HashMultimap.create();
/*     */ 
/*     */       
/* 401 */       if (var5 != null && !var5.isEmpty()) {
/*     */         
/* 403 */         Iterator<PotionEffect> var16 = var5.iterator();
/*     */         
/* 405 */         while (var16.hasNext())
/*     */         {
/* 407 */           PotionEffect var8 = var16.next();
/* 408 */           String var9 = StatCollector.translateToLocal(var8.getEffectName()).trim();
/* 409 */           Potion var10 = Potion.potionTypes[var8.getPotionID()];
/* 410 */           Map var11 = var10.func_111186_k();
/*     */           
/* 412 */           if (var11 != null && var11.size() > 0) {
/*     */             
/* 414 */             Iterator<Map.Entry> var12 = var11.entrySet().iterator();
/*     */             
/* 416 */             while (var12.hasNext()) {
/*     */               
/* 418 */               Map.Entry var13 = var12.next();
/* 419 */               AttributeModifier var14 = (AttributeModifier)var13.getValue();
/* 420 */               AttributeModifier var15 = new AttributeModifier(var14.getName(), var10.func_111183_a(var8.getAmplifier(), var14), var14.getOperation());
/* 421 */               var6.put(((Attribute)var13.getKey()).getAttributeUnlocalizedName(), var15);
/*     */             } 
/*     */           } 
/*     */           
/* 425 */           if (var8.getAmplifier() > 0)
/*     */           {
/* 427 */             var9 = var9 + " " + StatCollector.translateToLocal("potion.potency." + var8.getAmplifier()).trim();
/*     */           }
/*     */           
/* 430 */           if (var8.getDuration() > 20)
/*     */           {
/* 432 */             var9 = var9 + " (" + Potion.getDurationString(var8) + ")";
/*     */           }
/*     */           
/* 435 */           if (var10.isBadEffect()) {
/*     */             
/* 437 */             par3List.add(EnumChatFormatting.RED + var9);
/*     */             
/*     */             continue;
/*     */           } 
/* 441 */           par3List.add(EnumChatFormatting.GRAY + var9);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 447 */         String var7 = StatCollector.translateToLocal("potion.empty").trim();
/* 448 */         par3List.add(EnumChatFormatting.GRAY + var7);
/*     */       } 
/*     */       
/* 451 */       if (!par4) {
/*     */         return;
/*     */       }
/* 454 */       if (!var6.isEmpty()) {
/*     */         
/* 456 */         par3List.add("");
/* 457 */         par3List.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("potion.effects.whenDrank"));
/* 458 */         Iterator<Map.Entry> var16 = var6.entries().iterator();
/*     */         
/* 460 */         while (var16.hasNext()) {
/*     */           
/* 462 */           Map.Entry var17 = var16.next();
/* 463 */           AttributeModifier var18 = (AttributeModifier)var17.getValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 486 */           String effect_details = getEffectDetails((String)var17.getKey(), var18);
/*     */           
/* 488 */           if (effect_details != null) {
/* 489 */             par3List.add(effect_details);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getEffectDetails(String attribute_name, AttributeModifier attribute_modifier) {
/* 497 */     double var20, var19 = attribute_modifier.getAmount();
/*     */ 
/*     */     
/* 500 */     if (attribute_modifier.getOperation() != 1 && attribute_modifier.getOperation() != 2) {
/*     */       
/* 502 */       var20 = attribute_modifier.getAmount();
/*     */     }
/*     */     else {
/*     */       
/* 506 */       var20 = attribute_modifier.getAmount() * 100.0D;
/*     */     } 
/*     */     
/* 509 */     if (var19 > 0.0D)
/*     */     {
/* 511 */       return EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted("attribute.modifier.plus." + attribute_modifier.getOperation(), new Object[] { ItemStack.field_111284_a.format(var20), StatCollector.translateToLocal("attribute.name." + attribute_name) });
/*     */     }
/* 513 */     if (var19 < 0.0D) {
/*     */       
/* 515 */       var20 *= -1.0D;
/* 516 */       return EnumChatFormatting.RED + StatCollector.translateToLocalFormatted("attribute.modifier.take." + attribute_modifier.getOperation(), new Object[] { ItemStack.field_111284_a.format(var20), StatCollector.translateToLocal("attribute.name." + attribute_name) });
/*     */     } 
/*     */     
/* 519 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEffect(ItemStack par1ItemStack) {
/* 524 */     List var2 = getEffects(par1ItemStack);
/* 525 */     return (var2 != null && !var2.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/* 533 */     super.getSubItems(par1, par2CreativeTabs, par3List);
/*     */ 
/*     */     
/* 536 */     if (field_77835_b.isEmpty())
/*     */     {
/* 538 */       for (int var4 = 0; var4 <= 15; var4++) {
/*     */         
/* 540 */         for (int var5 = 0; var5 <= 1; var5++) {
/*     */           int var6;
/*     */ 
/*     */           
/* 544 */           if (var5 == 0) {
/*     */             
/* 546 */             var6 = var4 | 0x2000;
/*     */           }
/*     */           else {
/*     */             
/* 550 */             var6 = var4 | 0x4000;
/*     */           } 
/*     */           
/* 553 */           for (int var7 = 0; var7 <= 2; var7++) {
/*     */             
/* 555 */             int var8 = var6;
/*     */             
/* 557 */             if (var7 != 0)
/*     */             {
/* 559 */               if (var7 == 1) {
/*     */                 
/* 561 */                 var8 = var6 | 0x20;
/*     */               }
/* 563 */               else if (var7 == 2) {
/*     */                 
/* 565 */                 var8 = var6 | 0x40;
/*     */               } 
/*     */             }
/*     */             
/* 569 */             List var9 = PotionHelper.getPotionEffects(var8, false);
/*     */             
/* 571 */             if (var9 != null && !var9.isEmpty())
/*     */             {
/* 573 */               field_77835_b.put(var9, Integer.valueOf(var8));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 580 */     Iterator<Integer> var10 = field_77835_b.values().iterator();
/*     */     
/* 582 */     while (var10.hasNext()) {
/*     */       
/* 584 */       int var5 = ((Integer)var10.next()).intValue();
/* 585 */       par3List.add(new ItemStack(par1, 1, var5));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 591 */     this.field_94590_d = par1IconRegister.registerIcon(getIconString() + "_" + "bottle_drinkable");
/* 592 */     this.field_94591_c = par1IconRegister.registerIcon(getIconString() + "_" + "bottle_splash");
/* 593 */     this.field_94592_ct = par1IconRegister.registerIcon(getIconString() + "_" + "overlay");
/*     */   }
/*     */ 
/*     */   
/*     */   public static Icon func_94589_d(String par0Str) {
/* 598 */     return par0Str.equals("bottle_drinkable") ? Item.potion.field_94590_d : (par0Str.equals("bottle_splash") ? Item.potion.field_94591_c : (par0Str.equals("overlay") ? Item.potion.field_94592_ct : null));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSimilarityToItem(Item item) {
/* 603 */     if (item == glassBottle) {
/* 604 */       return 1;
/*     */     }
/* 606 */     return super.getSimilarityToItem(item);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemPotion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */