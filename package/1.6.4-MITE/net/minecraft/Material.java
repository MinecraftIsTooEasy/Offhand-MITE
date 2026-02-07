/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Material
/*     */ {
/*  10 */   public static Material[] materials = new Material[1024];
/*     */   
/*     */   public static int num_materials;
/*  13 */   public static final Material air = new MaterialTransparent("air", MapColor.airColor);
/*     */   
/*  15 */   public static final Material leather = (new Material(EnumEquipmentMaterial.leather)).setMapColor(MapColor.leatherColor).setFlammability(true, false, true).setHarmedByPepsin();
/*  16 */   public static final Material wood = (new Material(EnumEquipmentMaterial.wood)).setMapColor(MapColor.woodColor).setFlammability(true, true, true).setMinHarvestLevel(0);
/*  17 */   public static final Material flint = (new Material(EnumEquipmentMaterial.flint)).setMapColor(MapColor.stoneColor).setRockyMineral().setRequiresTool().setMinHarvestLevel(2);
/*  18 */   public static final Material stone = (new Material("stone", MapColor.stoneColor)).setRockyMineral().setRequiresTool().setMinHarvestLevel(2);
/*  19 */   public static final Material obsidian = (new Material(EnumEquipmentMaterial.obsidian)).setMapColor(MapColor.obsidianColor).setRockyMineral().setRequiresTool().setMinHarvestLevel(3);
/*  20 */   public static final Material rusted_iron = (new Material(EnumEquipmentMaterial.rusted_iron)).setMapColor(MapColor.rustedIronColor).setMetal(true).setRequiresTool().setMinHarvestLevel(2);
/*  21 */   public static final Material copper = (new Material(EnumEquipmentMaterial.copper)).setMapColor(MapColor.copperColor).setMetal(true).setRequiresTool().setMinHarvestLevel(2);
/*  22 */   public static final Material silver = (new Material(EnumEquipmentMaterial.silver)).setMapColor(MapColor.silverColor).setMetal(true).setRequiresTool().setMinHarvestLevel(2);
/*  23 */   public static final Material gold = (new Material(EnumEquipmentMaterial.gold)).setMapColor(MapColor.goldColor).setMetal(false).setRequiresTool().setMinHarvestLevel(2);
/*  24 */   public static final Material iron = (new Material(EnumEquipmentMaterial.iron)).setMapColor(MapColor.ironColor).setMetal(true).setRequiresTool().setMinHarvestLevel(3);
/*  25 */   public static final Material ancient_metal = (new Material(EnumEquipmentMaterial.ancient_metal)).setMapColor(MapColor.ancientMetalColor).setMetal(true).setRequiresTool().setMinHarvestLevel(3);
/*  26 */   public static final Material mithril = (new Material(EnumEquipmentMaterial.mithril)).setMapColor(MapColor.mithrilColor).setMetal(false).setRequiresTool().setMinHarvestLevel(4);
/*  27 */   public static final Material adamantium = (new Material(EnumEquipmentMaterial.adamantium)).setMapColor(MapColor.adamantiumColor).setMetal(false).setHarmedByLava(false).setRequiresTool().setMinHarvestLevel(5);
/*     */   
/*  29 */   public static final Material netherrack = (new Material(EnumEquipmentMaterial.netherrack)).setFlammability(true, false, false).setHarmedByLava(false).setMapColor(MapColor.netherrackColor).setRockyMineral().setRequiresTool().setMinHarvestLevel(2);
/*     */   
/*  31 */   public static final Material glass = (new Material(EnumEquipmentMaterial.glass)).setMapColor(MapColor.airColor).setRockyMineral(true).setTranslucent().setAdventureModeExempt();
/*  32 */   public static final Material quartz = (new Material(EnumEquipmentMaterial.quartz)).setMapColor(MapColor.quartzColor).setRockyMineral(true).setRequiresTool().setMinHarvestLevel(2);
/*  33 */   public static final Material emerald = (new Material(EnumEquipmentMaterial.emerald)).setMapColor(MapColor.emeraldColor).setRockyMineral(true).setRequiresTool().setMinHarvestLevel(3);
/*  34 */   public static final Material diamond = (new Material(EnumEquipmentMaterial.diamond)).setMapColor(MapColor.diamondColor).setRockyMineral(true).setRequiresTool().setMinHarvestLevel(4);
/*     */   
/*  36 */   public static final Material grass = (new Material("grass", MapColor.grassColor)).setFlammability(true, false, true);
/*  37 */   public static final Material dirt = new Material("dirt", MapColor.dirtColor);
/*  38 */   public static final Material redstone = (new Material("redstone", MapColor.redstoneColor)).setRockyMineral().setRequiresTool().setMinHarvestLevel(2);
/*  39 */   public static final Material anvil = (new Material("anvil", MapColor.ironColor)).setImmovableMobility();
/*  40 */   public static final Material water = (new MaterialLiquid("water", MapColor.waterColor)).setNoPushMobility().setDrinkable();
/*  41 */   public static final Material lava = (new MaterialLiquid("lava", MapColor.tntColor)).setHarmedByLava(false).setNoPushMobility();
/*  42 */   public static final Material tree_leaves = (new Material("tree_leaves", MapColor.foliageColor)).setFlammability(true, false, true).setTranslucent().setNoPushMobility();
/*  43 */   public static final Material plants = (new MaterialLogic("plants", MapColor.foliageColor)).setNoPushMobility().setFlammability(true, false, true);
/*  44 */   public static final Material vine = (new MaterialLogic("vine", MapColor.foliageColor)).setFlammability(true, false, true).setNoPushMobility().setReplaceable();
/*  45 */   public static final Material sponge = (new Material("sponge", MapColor.clothColor)).setFlammability(true, true, true);
/*  46 */   public static final Material cloth = (new Material("cloth", MapColor.clothColor)).setFlammability(true, true, true).setHarmedByPepsin();
/*  47 */   public static final Material fire = (new MaterialTransparent("fire", MapColor.airColor)).setNoPushMobility();
/*  48 */   public static final Material sand = new Material("sand", MapColor.sandColor);
/*  49 */   public static final Material circuits = (new MaterialLogic("circuits", MapColor.airColor)).setNoPushMobility();
/*  50 */   public static final Material materialCarpet = (new MaterialLogic("carpet", MapColor.clothColor)).setFlammability(true, true, true).setHarmedByPepsin();
/*  51 */   public static final Material redstoneLight = (new Material("redstone_light", MapColor.airColor)).setRockyMineral().setAdventureModeExempt();
/*  52 */   public static final Material tnt = (new Material("tnt", MapColor.tntColor)).setFlammability(true, false, true).setTranslucent();
/*  53 */   public static final Material coral = (new Material("coral", MapColor.foliageColor)).setRockyMineral().setNoPushMobility();
/*  54 */   public static final Material ice = (new Material("ice", MapColor.iceColor)).setTranslucent().setAdventureModeExempt().setCanDouseFire();
/*  55 */   public static final Material snow = (new MaterialLogic("snow", MapColor.snowColor)).setReplaceable().setTranslucent().setNoPushMobility().setCanDouseFire().setDissolvesInWater().setHarmedByAcid(false);
/*  56 */   public static final Material craftedSnow = (new Material("crafted_snow", MapColor.snowColor)).setCanDouseFire().setDissolvesInWater().setHarmedByAcid(false);
/*  57 */   public static final Material cactus = (new Material("cactus", MapColor.foliageColor)).setTranslucent().setNoPushMobility().setFlammability(true, false, true);
/*  58 */   public static final Material clay = new Material("clay", MapColor.clayColor);
/*  59 */   public static final Material hardened_clay = new Material("hardened_clay", MapColor.clayColor);
/*  60 */   public static final Material pumpkin = (new Material("pumpkin", MapColor.foliageColor)).setNoPushMobility().setFlammability(true, false, true);
/*  61 */   public static final Material dragonEgg = new Material("dragon_egg", MapColor.obsidianColor);
/*  62 */   public static final Material portal = (new MaterialPortal(MapColor.airColor)).setImmovableMobility();
/*  63 */   public static final Material cake = (new Material("cake", MapColor.airColor)).setNoPushMobility().setEdible().setHarmedByPepsin();
/*  64 */   public static final Material web = (new MaterialWeb(MapColor.clothColor)).setFlammability(true, false, true).setNoPushMobility();
/*  65 */   public static final Material piston = (new Material("piston", MapColor.stoneColor)).setImmovableMobility();
/*     */   
/*  67 */   public static final Material milk = (new Material("milk")).setLiquid(true).setDrinkable().setHarmedByPepsin().setHarmedByAcid(true);
/*  68 */   public static final Material mushroom_stew = new MaterialStew("mushroom_stew");
/*  69 */   public static final Material beef_stew = (new MaterialStew("beef_stew")).setHarmedByPepsin();
/*  70 */   public static final Material chicken_soup = (new MaterialSoup("chicken_soup")).setHarmedByPepsin();
/*  71 */   public static final Material vegetable_soup = new MaterialSoup("vegetable_soup");
/*  72 */   public static final Material cream_of_mushroom_soup = (new MaterialSoup("cream_of_mushroom_soup")).setHarmedByPepsin();
/*  73 */   public static final Material cream_of_vegetable_soup = (new MaterialSoup("cream_of_vegetable_soup")).setHarmedByPepsin();
/*  74 */   public static final Material pumpkin_soup = new MaterialSoup("pumpkin_soup");
/*  75 */   public static final Material mashed_potato = (new MaterialFood("mashed_potato")).setHarmedByPepsin();
/*  76 */   public static final Material sorbet = new MaterialFood("sorbet");
/*  77 */   public static final Material ice_cream = (new MaterialFood("ice_cream")).setCanDouseFire().setHarmedByPepsin();
/*  78 */   public static final Material salad = new MaterialFood("salad");
/*  79 */   public static final Material fruit = new MaterialFood("fruit");
/*  80 */   public static final Material vegetable = new MaterialFood("vegetable");
/*  81 */   public static final Material meat = (new MaterialFood("meat")).setHarmedByPepsin();
/*  82 */   public static final Material bread = new MaterialFood("bread");
/*  83 */   public static final Material desert = new MaterialFood("desert");
/*  84 */   public static final Material pie = (new MaterialFood("pie")).setHarmedByPepsin();
/*  85 */   public static final Material porridge = new MaterialFood("porridge");
/*  86 */   public static final Material cereal = (new MaterialFood("cereal")).setHarmedByPepsin();
/*  87 */   public static final Material sugar = (new MaterialFood("sugar")).setDissolvesInWater();
/*  88 */   public static final Material cheese = (new MaterialFood("cheese")).setHarmedByPepsin();
/*  89 */   public static final Material flour = (new MaterialFood("flour")).setDissolvesInWater();
/*  90 */   public static final Material dough = new MaterialFood("dough");
/*  91 */   public static final Material seed = new MaterialFood("seed");
/*  92 */   public static final Material bone = new Material("bone");
/*  93 */   public static final Material paper = (new Material("paper")).setFlammability(true, true, true);
/*  94 */   public static final Material manure = (new Material("manure")).setFlammability(true, false, true);
/*  95 */   public static final Material coal = (new Material("coal")).setFlammability(true, true, true);
/*  96 */   public static final Material lapis_lazuli = (new Material("lapis_lazuli")).setRockyMineral(true);
/*  97 */   public static final Material feather = (new Material("feather")).setFlammability(true, false, true).setHarmedByPepsin();
/*  98 */   public static final Material gunpowder = (new Material("gunpowder")).setFlammability(true, false, true);
/*  99 */   public static final Material slime = (new Material("slime")).setFlammability(true, false, true);
/* 100 */   public static final Material glowstone = (new Material("glowstone")).setRockyMineral();
/* 101 */   public static final Material dye = (new Material("dye")).setFlammability(true, false, true);
/* 102 */   public static final Material ender_pearl = new Material("ender_pearl");
/* 103 */   public static final Material blaze = new Material("blaze");
/* 104 */   public static final Material silk = (new Material("silk")).setFlammability(true, false, true);
/* 105 */   public static final Material frags = new Material("frags");
/* 106 */   public static final Material vinyl = (new Material("vinyl")).setFlammability(true, false, true);
/*     */   
/*     */   private boolean is_liquid;
/*     */   
/*     */   private boolean is_edible;
/*     */   
/*     */   private boolean is_drinkable;
/*     */   
/*     */   private boolean can_catch_fire;
/*     */   
/*     */   private boolean can_burn_as_fuel_source;
/*     */   
/*     */   private boolean is_harmed_by_fire;
/*     */   
/*     */   private boolean can_douse_fire;
/*     */   
/*     */   private boolean dissolves_in_water;
/*     */   
/*     */   private boolean is_replaceable;
/*     */   
/*     */   private boolean is_translucent;
/*     */   
/*     */   private boolean is_adventure_mode_exempt;
/*     */   private boolean is_metal;
/* 130 */   private EnumQuality max_quality = EnumQuality.getHighestQuality(); private boolean is_rocky_mineral; private boolean is_crystal; private boolean requires_tool; private boolean is_harmed_by_lava = true; private boolean is_harmed_by_pepsin = false; private boolean is_harmed_by_acid = true; public MapColor map_color; private int mobility_flag; protected float durability; protected int enchantability; protected String name; private float full_block_hardness;
/*     */   protected int min_harvest_level;
/*     */   
/*     */   public Material(String name) {
/* 134 */     this(name, (MapColor)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Material(String name, MapColor map_color) {
/* 139 */     setName(name);
/* 140 */     setMapColor(map_color);
/*     */     
/* 142 */     materials[num_materials++] = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material(EnumEquipmentMaterial enum_crafting_material) {
/* 147 */     this(enum_crafting_material.name, (MapColor)null);
/*     */     
/* 149 */     setDurability(enum_crafting_material.durability);
/* 150 */     setEnchantability(enum_crafting_material.enchantability);
/* 151 */     setMaxQuality(enum_crafting_material.max_quality);
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
/*     */   public Material setMapColor(MapColor map_color) {
/* 166 */     this.map_color = map_color;
/* 167 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setDurability(float durability) {
/* 172 */     this.durability = durability;
/* 173 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setEnchantability(int enchantability) {
/* 178 */     this.enchantability = enchantability;
/* 179 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setName(String material_name) {
/* 184 */     this.name = material_name;
/* 185 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public final Material setFullBlockHardness(float full_block_hardness) {
/* 190 */     this.full_block_hardness = full_block_hardness;
/* 191 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public final float getFullBlockHardness() {
/* 196 */     return this.full_block_hardness;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Material setMinHarvestLevel(int min_harvest_level) {
/* 202 */     this.min_harvest_level = min_harvest_level;
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
/*     */ 
/*     */   
/*     */   public final boolean isLiquid() {
/* 216 */     return this.is_liquid;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setLiquid(boolean can_douse_fire) {
/* 221 */     if (can_douse_fire) {
/* 222 */       setCanDouseFire();
/*     */     }
/* 224 */     this.is_liquid = true;
/*     */     
/* 226 */     setHarmedByAcid(false);
/*     */     
/* 228 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid() {
/* 233 */     return true;
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
/*     */   public Material setEdible() {
/* 250 */     this.is_edible = true;
/* 251 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEdible() {
/* 257 */     return this.is_edible;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Material setDrinkable() {
/* 263 */     this.is_drinkable = true;
/* 264 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDrinkable() {
/* 270 */     return this.is_drinkable;
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
/*     */   private Material setTranslucent() {
/* 305 */     this.is_translucent = true;
/* 306 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Material setRequiresTool() {
/* 315 */     this.requires_tool = true;
/* 316 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setFlammability(boolean can_catch_fire, boolean can_burn_as_fuel_source, boolean is_harmed_by_fire) {
/* 321 */     this.can_catch_fire = can_catch_fire;
/* 322 */     this.can_burn_as_fuel_source = can_burn_as_fuel_source;
/* 323 */     this.is_harmed_by_fire = is_harmed_by_fire;
/*     */ 
/*     */     
/* 326 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setHarmedByLava(boolean is_harmed_by_lava) {
/* 331 */     this.is_harmed_by_lava = is_harmed_by_lava;
/* 332 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 338 */     return this.can_catch_fire;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBurnAsFuelSource() {
/* 344 */     return this.can_burn_as_fuel_source;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 350 */     return this.is_harmed_by_fire;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 356 */     return this.is_harmed_by_lava;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByPepsin() {
/* 361 */     return this.is_harmed_by_pepsin;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByAcid() {
/* 366 */     return this.is_harmed_by_acid;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Material setCanDouseFire() {
/* 372 */     setFlammability(false, false, true);
/*     */     
/* 374 */     this.can_douse_fire = true;
/* 375 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDouseFire() {
/* 381 */     return this.can_douse_fire;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Material setDissolvesInWater() {
/* 387 */     this.dissolves_in_water = true;
/* 388 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean dissolvesInWater() {
/* 394 */     return this.dissolves_in_water;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Material setReplaceable() {
/* 402 */     this.is_replaceable = true;
/* 403 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReplaceable() {
/* 411 */     return this.is_replaceable;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setMetal(boolean is_harmed_by_acid) {
/* 416 */     this.is_metal = true;
/* 417 */     setHarmedByAcid(is_harmed_by_acid);
/* 418 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMetal() {
/* 423 */     return this.is_metal;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Material setRockyMineral() {
/* 429 */     return setRockyMineral(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setRockyMineral(boolean is_crystal) {
/* 434 */     this.is_rocky_mineral = true;
/* 435 */     this.is_crystal = is_crystal;
/*     */     
/* 437 */     setHarmedByAcid(false);
/*     */     
/* 439 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRockyMineral() {
/* 444 */     return this.is_rocky_mineral;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCrystal() {
/* 449 */     return this.is_crystal;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setMaxQuality(EnumQuality max_quality) {
/* 454 */     this.max_quality = max_quality;
/* 455 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumQuality getMaxQuality() {
/* 460 */     return this.max_quality;
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
/*     */   public final boolean requiresTool(Block block, int metadata) {
/* 490 */     return (this.requires_tool || block.getMinHarvestLevel(metadata) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaterialMobility() {
/* 499 */     return this.mobility_flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Material setNoPushMobility() {
/* 507 */     this.mobility_flag = 1;
/* 508 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Material setImmovableMobility() {
/* 516 */     this.mobility_flag = 2;
/* 517 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Material setAdventureModeExempt() {
/* 525 */     this.is_adventure_mode_exempt = true;
/* 526 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdventureModeExempt() {
/* 534 */     return this.is_adventure_mode_exempt;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDamageVsEntity() {
/* 539 */     if (this == wood)
/* 540 */       return 0.0F; 
/* 541 */     if (this == flint)
/* 542 */       return 1.0F; 
/* 543 */     if (this == obsidian)
/* 544 */       return 2.0F; 
/* 545 */     if (this == rusted_iron)
/* 546 */       return 2.0F; 
/* 547 */     if (this == copper)
/* 548 */       return 3.0F; 
/* 549 */     if (this == silver)
/* 550 */       return 3.0F; 
/* 551 */     if (this == gold)
/* 552 */       return 2.0F; 
/* 553 */     if (this == iron)
/* 554 */       return 4.0F; 
/* 555 */     if (this == ancient_metal)
/* 556 */       return 4.0F; 
/* 557 */     if (this == mithril)
/* 558 */       return 5.0F; 
/* 559 */     if (this == adamantium)
/* 560 */       return 6.0F; 
/* 561 */     if (this == diamond) {
/* 562 */       return 4.0F;
/*     */     }
/* 564 */     Minecraft.setErrorMessage("getDamageVsEntity: unhandled material " + this.name);
/*     */     
/* 566 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCapitalizedName() {
/* 571 */     return StringHelper.capitalizeEachWord(this.name.replaceAll("_", " "));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTranslationKey() {
/* 576 */     return StringHelper.convertUnderscoresToCamelCase(toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedName() {
/* 581 */     return Translator.get("material." + getTranslationKey() + ".name");
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 586 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Material getFromList(List<Material> list, int index) {
/* 591 */     return list.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumQuality getLowestMaxQualityOfMaterials(List<Material> materials) {
/* 596 */     if (materials.size() == 0) {
/*     */       
/* 598 */       Minecraft.setErrorMessage("getLowestMaxQualityOfMaterials: materials list was empty");
/* 599 */       return null;
/*     */     } 
/*     */     
/* 602 */     if (materials.size() == 1) {
/* 603 */       return ((Material)materials.get(0)).getMaxQuality();
/*     */     }
/* 605 */     List<EnumQuality> max_qualities = new ArrayList();
/*     */     
/* 607 */     for (int i = 0; i < materials.size(); i++) {
/* 608 */       max_qualities.add(((Material)materials.get(i)).getMaxQuality());
/*     */     }
/* 610 */     return EnumQuality.getLowest(max_qualities);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumQuality getHighestMaxQualityOfMaterials(List<Material> materials) {
/* 615 */     if (materials.size() == 0) {
/*     */       
/* 617 */       Minecraft.setErrorMessage("getHighestMaxQualityOfMaterials: materials list is empty");
/* 618 */       return null;
/*     */     } 
/*     */     
/* 621 */     if (materials.size() == 1) {
/* 622 */       return ((Material)materials.get(0)).getMaxQuality();
/*     */     }
/* 624 */     List<EnumQuality> max_qualities = new ArrayList();
/*     */     
/* 626 */     for (int i = 0; i < materials.size(); i++) {
/* 627 */       max_qualities.add(((Material)materials.get(i)).getMaxQuality());
/*     */     }
/* 629 */     return EnumQuality.getHighest(max_qualities);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainMetal(List<Material> materials) {
/* 634 */     if (materials.size() == 0) {
/*     */       
/* 636 */       Minecraft.setErrorMessage("doesMaterialListContainMetal: materials list is empty");
/* 637 */       return false;
/*     */     } 
/*     */     
/* 640 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 642 */       if (((Material)materials.get(i)).isMetal()) {
/* 643 */         return true;
/*     */       }
/*     */     } 
/* 646 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainRockyMineral(List<Material> materials) {
/* 651 */     if (materials.size() == 0) {
/*     */       
/* 653 */       Minecraft.setErrorMessage("doesMaterialListContainRockyMineral: materials list is empty");
/* 654 */       return false;
/*     */     } 
/*     */     
/* 657 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 659 */       if (((Material)materials.get(i)).isRockyMineral()) {
/* 660 */         return true;
/*     */       }
/*     */     } 
/* 663 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainCrystal(List<Material> materials) {
/* 668 */     if (materials.size() == 0) {
/*     */       
/* 670 */       Minecraft.setErrorMessage("doesMaterialListContainCrystal: materials list is empty");
/* 671 */       return false;
/*     */     } 
/*     */     
/* 674 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 676 */       if (((Material)materials.get(i)).isCrystal()) {
/* 677 */         return true;
/*     */       }
/*     */     } 
/* 680 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainMaterialThatCanDouseFire(List<Material> materials) {
/* 685 */     if (materials.size() == 0) {
/*     */       
/* 687 */       Minecraft.setErrorMessage("doesMaterialListContainMaterialThatCanDouseFire: materials list is empty");
/* 688 */       return false;
/*     */     } 
/*     */     
/* 691 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 693 */       if (((Material)materials.get(i)).canDouseFire()) {
/* 694 */         return true;
/*     */       }
/*     */     } 
/* 697 */     return false;
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
/*     */   public static boolean doesMaterialListContainMaterialThatCanCatchFire(List<Material> materials) {
/* 719 */     if (materials.size() == 0) {
/*     */       
/* 721 */       Minecraft.setErrorMessage("doesMaterialListContainMaterialThatCanCatchFire: materials list is empty");
/* 722 */       return false;
/*     */     } 
/*     */     
/* 725 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 727 */       if (((Material)materials.get(i)).canCatchFire()) {
/* 728 */         return true;
/*     */       }
/*     */     } 
/* 731 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainMaterialThatCanBurnAsFuelSource(List<Material> materials) {
/* 736 */     if (materials.size() == 0) {
/*     */       
/* 738 */       Minecraft.setErrorMessage("doesMaterialListContainMaterialThatCanBurnAsFuelSource: materials list is empty");
/* 739 */       return false;
/*     */     } 
/*     */     
/* 742 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 744 */       if (((Material)materials.get(i)).canBurnAsFuelSource()) {
/* 745 */         return true;
/*     */       }
/*     */     } 
/* 748 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainMaterialThatIsHarmedByFire(List<Material> materials) {
/* 753 */     if (materials.size() == 0) {
/*     */       
/* 755 */       Minecraft.setErrorMessage("doesMaterialListContainMaterialThatIsHarmedByFire: materials list is empty");
/* 756 */       return false;
/*     */     } 
/*     */     
/* 759 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 761 */       if (((Material)materials.get(i)).isHarmedByFire()) {
/* 762 */         return true;
/*     */       }
/*     */     } 
/* 765 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainMaterialThatIsHarmedByLava(List<Material> materials) {
/* 770 */     if (materials.size() == 0) {
/*     */       
/* 772 */       Minecraft.setErrorMessage("doesMaterialListContainMaterialThatIsHarmedByLava: materials list is empty");
/* 773 */       return false;
/*     */     } 
/*     */     
/* 776 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 778 */       if (((Material)materials.get(i)).isHarmedByLava()) {
/* 779 */         return true;
/*     */       }
/*     */     } 
/* 782 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainMaterialThatIsHarmedByPepsin(List<Material> materials) {
/* 787 */     if (materials.size() == 0) {
/*     */       
/* 789 */       Minecraft.setErrorMessage("doesMaterialListContainMaterialThatIsHarmedByPepsin: materials list is empty");
/* 790 */       return false;
/*     */     } 
/*     */     
/* 793 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 795 */       if (((Material)materials.get(i)).isHarmedByPepsin()) {
/* 796 */         return true;
/*     */       }
/*     */     } 
/* 799 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainMaterialThatIsHarmedByAcid(List<Material> materials) {
/* 804 */     if (materials.size() == 0) {
/*     */       
/* 806 */       Minecraft.setErrorMessage("doesMaterialListContainMaterialThatIsHarmedByAcid: materials list is empty");
/* 807 */       return false;
/*     */     } 
/*     */     
/* 810 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 812 */       if (((Material)materials.get(i)).isHarmedByAcid()) {
/* 813 */         return true;
/*     */       }
/*     */     } 
/* 816 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesMaterialListContainMaterialThatDissolvesInWater(List<Material> materials) {
/* 821 */     if (materials.size() == 0) {
/*     */       
/* 823 */       Minecraft.setErrorMessage("doesMaterialListContainMaterialThatDissolvesInWater: materials list is empty");
/* 824 */       return false;
/*     */     } 
/*     */     
/* 827 */     for (int i = 0; i < materials.size(); i++) {
/*     */       
/* 829 */       if (((Material)materials.get(i)).dissolvesInWater()) {
/* 830 */         return true;
/*     */       }
/*     */     } 
/* 833 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] getMaterialNames(List materials) {
/* 838 */     String[] names = new String[materials.size()];
/*     */     
/* 840 */     for (int i = 0; i < materials.size(); i++) {
/* 841 */       names[i] = (getFromList(materials, i)).name;
/*     */     }
/* 843 */     return names;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedBy(DamageSource damage_source) {
/* 848 */     if (damage_source.isFireDamage())
/* 849 */       return isHarmedByFire(); 
/* 850 */     if (damage_source.isLavaDamage())
/* 851 */       return isHarmedByLava(); 
/* 852 */     if (damage_source.isPepsinDamage())
/* 853 */       return isHarmedByPepsin(); 
/* 854 */     if (damage_source.isAcidDamage()) {
/* 855 */       return isHarmedByAcid();
/*     */     }
/* 857 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSnow() {
/* 862 */     return (this == snow || this == craftedSnow);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFreezing() {
/* 867 */     return (isSnow() || this == ice);
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setHarmedByPepsin() {
/* 872 */     this.is_harmed_by_pepsin = true;
/* 873 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material setHarmedByAcid(boolean is_harmed_by_acid) {
/* 878 */     this.is_harmed_by_acid = is_harmed_by_acid;
/* 879 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialize() {
/* 885 */     setFullBlockHardness(EnumMaterialHardness.getHardnessFor(this));
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 890 */     for (int i = 0; i < materials.length; i++) {
/*     */       
/* 892 */       Material material = materials[i];
/*     */       
/* 894 */       if (material != null)
/* 895 */         material.initialize(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Material.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */