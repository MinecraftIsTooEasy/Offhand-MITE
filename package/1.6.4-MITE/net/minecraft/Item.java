/*      */ package net.minecraft;
/*      */ 
/*      */ import com.google.common.collect.HashMultimap;
/*      */ import com.google.common.collect.Multimap;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Item
/*      */ {
/*   16 */   protected static final UUID field_111210_e = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
/*      */   
/*      */   private CreativeTabs tabToDisplayOn;
/*      */   
/*   20 */   protected static Random itemRand = new Random();
/*      */ 
/*      */ 
/*      */   
/*   24 */   public static Item[] itemsList = new Item[32000];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  200 */   public static Item shovelIron = (new ItemShovel(0, Material.iron)).setUnlocalizedName("shovelIron").useVanillaTexture("iron_shovel");
/*  201 */   public static Item pickaxeIron = (new ItemPickaxe(1, Material.iron)).setUnlocalizedName("pickaxeIron").useVanillaTexture("iron_pickaxe");
/*  202 */   public static Item axeIron = (new ItemAxe(2, Material.iron)).setUnlocalizedName("axeIron").useVanillaTexture("iron_axe");
/*  203 */   public static Item flintAndSteel = (new ItemFlintAndSteel(3)).setUnlocalizedName("flintAndSteel");
/*  204 */   public static Item appleRed = (new ItemFood(4, Material.fruit, 2, 1, 1000, false, false, true, "VANILLA")).setPlantProduct().setUnlocalizedName("apple").useVanillaTexture("apple");
/*  205 */   public static ItemBow bow = (ItemBow)(new ItemBow(5, Material.wood)).setUnlocalizedName("bow");
/*  206 */   public static Item coal = (new ItemCoal(7)).setUnlocalizedName("coal");
/*  207 */   public static Item diamond = (new ItemRock(8, Material.diamond, "diamond")).setXPReward(30).setUnlocalizedName("diamond");
/*  208 */   public static Item ingotIron = (new ItemIngot(9, Material.iron)).setXPReward(10).setUnlocalizedName("ingotIron").useVanillaTexture("iron_ingot");
/*  209 */   public static Item ingotGold = (new ItemIngot(10, Material.gold)).setXPReward(20).setUnlocalizedName("ingotGold");
/*  210 */   public static ItemSword swordIron = (ItemSword)(new ItemSword(11, Material.iron)).setUnlocalizedName("swordIron").useVanillaTexture("iron_sword");
/*  211 */   public static Item shovelWood = (new ItemShovel(13, Material.wood)).setUnlocalizedName("shovelWood").useVanillaTexture("wood_shovel");
/*  212 */   public static Item stick = (new Item(24, Material.wood, "stick")).setMaxStackSize(32).setCraftingDifficultyAsComponent(25.0F).setReachBonus(0.5F).setFull3D().setUnlocalizedName("stick").setCreativeTab(CreativeTabs.tabMaterials);
/*  213 */   public static ItemBowl bowlEmpty = (ItemBowl)(new ItemBowl(25, null, "VANILLA")).setUnlocalizedName("bowl").useVanillaTexture("bowl");
/*      */   
/*  215 */   public static ItemBowl bowlMushroomStew = (ItemBowl)(new ItemBowl(26, Material.mushroom_stew, "mushroom_stew")).setFoodValue(2, 4, false, false, false).setPlantProduct().setUnlocalizedName("mushroomStew");
/*  216 */   public static Item swordGold = (new ItemSword(27, Material.gold)).setUnlocalizedName("swordGold");
/*  217 */   public static Item shovelGold = (new ItemShovel(28, Material.gold)).setUnlocalizedName("shovelGold");
/*  218 */   public static Item pickaxeGold = (new ItemPickaxe(29, Material.gold)).setUnlocalizedName("pickaxeGold");
/*  219 */   public static Item axeGold = (new ItemAxe(30, Material.gold)).setUnlocalizedName("axeGold");
/*  220 */   public static Item silk = (new ItemReed(31, Block.tripWire, "string")).setMaterial(new Material[] { Material.silk }).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("string").setCreativeTab(CreativeTabs.tabMaterials);
/*  221 */   public static Item feather = (new Item(32, Material.feather, "feather")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("feather").setCreativeTab(CreativeTabs.tabMaterials);
/*  222 */   public static Item gunpowder = (new Item(33, Material.gunpowder, "gunpowder")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("sulphur").setPotionEffect(PotionHelper.gunpowderEffect).setCreativeTab(CreativeTabs.tabMaterials);
/*  223 */   public static Item hoeIron = (new ItemHoe(36, Material.iron)).setUnlocalizedName("hoeIron").useVanillaTexture("iron_hoe");
/*  224 */   public static Item hoeGold = (new ItemHoe(38, Material.gold)).setUnlocalizedName("hoeGold");
/*  225 */   public static Item seeds = (new ItemSeeds(39, 1, 0, false, true, false, Block.crops.blockID, Block.tilledField.blockID, "VANILLA")).setUnlocalizedName("seeds").useVanillaTexture("seeds_wheat");
/*  226 */   public static Item wheat = (new Item(40, Material.plants, "wheat")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("wheat").setCreativeTab(CreativeTabs.tabMaterials);
/*  227 */   public static ItemFood bread = (ItemFood)(new ItemFood(41, Material.bread, 8, 2, false, false, false, "VANILLA")).setPlantProduct().setUnlocalizedName("bread").useVanillaTexture("bread");
/*  228 */   public static ItemHelmet helmetLeather = (ItemHelmet)(new ItemHelmet(42, Material.leather, false)).setUnlocalizedName("helmetCloth").useVanillaTexture("leather_helmet");
/*  229 */   public static ItemCuirass plateLeather = (ItemCuirass)(new ItemCuirass(43, Material.leather, false)).setUnlocalizedName("chestplateCloth").useVanillaTexture("leather_chestplate");
/*  230 */   public static ItemLeggings legsLeather = (ItemLeggings)(new ItemLeggings(44, Material.leather, false)).setUnlocalizedName("leggingsCloth").useVanillaTexture("leather_leggings");
/*  231 */   public static ItemBoots bootsLeather = (ItemBoots)(new ItemBoots(45, Material.leather, false)).setUnlocalizedName("bootsCloth").useVanillaTexture("leather_boots");
/*  232 */   public static ItemHelmet helmetChainIron = (ItemHelmet)(new ItemHelmet(46, Material.iron, true)).setUnlocalizedName("helmetChainIron").useVanillaTexture("chainmail_helmet");
/*  233 */   public static ItemCuirass plateChainIron = (ItemCuirass)(new ItemCuirass(47, Material.iron, true)).setUnlocalizedName("chestplateChainIron").useVanillaTexture("chainmail_chestplate");
/*  234 */   public static ItemLeggings legsChainIron = (ItemLeggings)(new ItemLeggings(48, Material.iron, true)).setUnlocalizedName("leggingsChainIron").useVanillaTexture("chainmail_leggings");
/*  235 */   public static ItemBoots bootsChainIron = (ItemBoots)(new ItemBoots(49, Material.iron, true)).setUnlocalizedName("bootsChainIron").useVanillaTexture("chainmail_boots");
/*  236 */   public static ItemHelmet helmetIron = (ItemHelmet)(new ItemHelmet(50, Material.iron, false)).setUnlocalizedName("helmetIron").useVanillaTexture("iron_helmet");
/*  237 */   public static ItemCuirass plateIron = (ItemCuirass)(new ItemCuirass(51, Material.iron, false)).setUnlocalizedName("chestplateIron").useVanillaTexture("iron_chestplate");
/*  238 */   public static ItemLeggings legsIron = (ItemLeggings)(new ItemLeggings(52, Material.iron, false)).setUnlocalizedName("leggingsIron").useVanillaTexture("iron_leggings");
/*  239 */   public static ItemBoots bootsIron = (ItemBoots)(new ItemBoots(53, Material.iron, false)).setUnlocalizedName("bootsIron").useVanillaTexture("iron_boots");
/*  240 */   public static ItemHelmet helmetGold = (ItemHelmet)(new ItemHelmet(58, Material.gold, false)).setUnlocalizedName("helmetGold");
/*  241 */   public static ItemCuirass plateGold = (ItemCuirass)(new ItemCuirass(59, Material.gold, false)).setUnlocalizedName("chestplateGold");
/*  242 */   public static ItemLeggings legsGold = (ItemLeggings)(new ItemLeggings(60, Material.gold, false)).setUnlocalizedName("leggingsGold");
/*  243 */   public static ItemBoots bootsGold = (ItemBoots)(new ItemBoots(61, Material.gold, false)).setUnlocalizedName("bootsGold");
/*  244 */   public static Item flint = (new ItemRock(62, Material.flint, "flint")).setMaxStackSize(16).setUnlocalizedName("flint");
/*  245 */   public static ItemMeat porkRaw = (ItemMeat)(new ItemMeat(63, 4, 4, false, false, "VANILLA")).setUnlocalizedName("porkchopRaw").useVanillaTexture("porkchop_raw");
/*  246 */   public static ItemMeat porkCooked = (ItemMeat)(new ItemMeat(64, 8, 8, false, true, "porkchop_cooked")).setUnlocalizedName("porkchopCooked");
/*  247 */   public static Item painting = (new ItemHangingEntity(65, EntityPainting.class, "painting")).setMaterial(new Material[] { Material.cloth, Material.wood }).setUnlocalizedName("painting");
/*  248 */   public static Item appleGold = (new ItemAppleGold(66, 2, 1, "VANILLA")).setAlwaysEdible().setPotionEffect(Potion.regeneration.id, 60, 0, 1.0F).setUnlocalizedName("appleGold").useVanillaTexture("apple_golden");
/*  249 */   public static Item sign = (new ItemSign(67)).setUnlocalizedName("sign");
/*  250 */   public static Item doorWood = (new ItemDoor(68, Material.wood)).setUnlocalizedName("doorWood").useVanillaTexture("door_wood");
/*  251 */   public static ItemBucket bucketIronEmpty = (ItemBucket)(new ItemBucket(69, Material.iron, null)).setUnlocalizedName("bucket");
/*  252 */   public static ItemBucket bucketIronWater = (ItemBucket)(new ItemBucket(70, Material.iron, Material.water)).setUnlocalizedName("bucketWater").setContainerItem(bucketIronEmpty);
/*  253 */   public static ItemBucket bucketIronLava = (ItemBucket)(new ItemBucket(71, Material.iron, Material.lava)).setUnlocalizedName("bucketLava").setContainerItem(bucketIronEmpty);
/*  254 */   public static Item minecartEmpty = (new ItemMinecart(72, 0, "minecart_normal")).setUnlocalizedName("minecart");
/*  255 */   public static Item saddle = (new ItemSaddle(73)).setUnlocalizedName("saddle");
/*  256 */   public static Item doorIron = (new ItemDoor(74, Material.iron)).setUnlocalizedName("doorIron").useVanillaTexture("door_iron");
/*  257 */   public static Item redstone = (new ItemRedstone(75)).setUnlocalizedName("redstone").setPotionEffect(PotionHelper.redstoneEffect);
/*  258 */   public static Item snowball = (new ItemSnowball(76)).setUnlocalizedName("snowball");
/*  259 */   public static Item boat = (new ItemBoat(77)).setUnlocalizedName("boat");
/*  260 */   public static Item leather = (new Item(78, Material.leather, "leather")).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("leather").setCreativeTab(CreativeTabs.tabMaterials);
/*  261 */   public static ItemBucketMilk bucketIronMilk = (ItemBucketMilk)(new ItemBucketMilk(79, Material.iron)).setUnlocalizedName("milk").setContainerItem(bucketIronEmpty);
/*      */   
/*  263 */   public static Item brick = (new ItemBrick(80, Material.clay, "brick")).setUnlocalizedName("brick");
/*  264 */   public static Item clay = (new Item(81, Material.clay, "clay_ball")).setUnlocalizedName("clay").setCraftingDifficultyAsComponent(25.0F).setMaxStackSize(16).setCreativeTab(CreativeTabs.tabMaterials);
/*  265 */   public static Item reed = (new ItemReed(82, Block.reed, "reeds")).setMaxStackSize(16).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("reeds").setCreativeTab(CreativeTabs.tabMaterials);
/*  266 */   public static Item paper = (new Item(83, Material.paper, "paper")).setMaxStackSize(64).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("paper").setCreativeTab(CreativeTabs.tabMisc);
/*  267 */   public static Item book = (new ItemBook(84, "book_normal")).setUnlocalizedName("book").setCreativeTab(CreativeTabs.tabMisc);
/*      */   
/*  269 */   public static ItemGelatinousSphere slimeBall = new ItemGelatinousSphere(85);
/*  270 */   public static Item minecartCrate = (new ItemMinecart(86, 1, "minecart_chest")).setUnlocalizedName("minecartChest");
/*  271 */   public static Item minecartPowered = (new ItemMinecart(87, 2, "minecart_furnace")).setUnlocalizedName("minecartFurnace");
/*  272 */   public static Item egg = (new ItemEgg(88)).setUnlocalizedName("egg").useVanillaTexture("egg");
/*  273 */   public static Item compass = (new Item(89, Material.iron, "compass")).addMaterial(new Material[] { Material.redstone }).setUnlocalizedName("compass").setCreativeTab(CreativeTabs.tabTools);
/*  274 */   public static ItemFishingRod fishingRodIron = (ItemFishingRod)(new ItemFishingRod(90, Material.iron)).setUnlocalizedName("fishingRod");
/*  275 */   public static Item pocketSundial = (new Item(91, Material.gold, "clock")).addMaterial(new Material[] { Material.redstone }).setUnlocalizedName("clock").setCreativeTab(CreativeTabs.tabTools);
/*  276 */   public static Item glowstone = (new Item(92, Material.glowstone, "glowstone_dust")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("yellowDust").setPotionEffect(PotionHelper.glowstoneEffect).setCreativeTab(CreativeTabs.tabMaterials);
/*  277 */   public static ItemMeat fishRaw = (ItemMeat)(new ItemMeat(93, 3, 3, true, false, "VANILLA")).setUnlocalizedName("fishRaw").useVanillaTexture("fish_raw");
/*  278 */   public static ItemMeat fishCooked = (ItemMeat)(new ItemMeat(94, 6, 6, true, true, "VANILLA")).setUnlocalizedName("fishCooked").useVanillaTexture("fish_cooked");
/*  279 */   public static Item dyePowder = (new ItemDye(95)).setUnlocalizedName("dyePowder");
/*  280 */   public static Item bone = (new Item(96, Material.bone, "bone")).setCraftingDifficultyAsComponent(100.0F).setReachBonus(0.5F).setUnlocalizedName("bone").setFull3D().setCreativeTab(CreativeTabs.tabMisc);
/*  281 */   public static Item sugar = (new ItemFood(97, Material.sugar, 1, 0, 1000, false, false, false, "VANILLA")).setPlantProduct().setUnlocalizedName("sugar").setPotionEffect(PotionHelper.sugarEffect).useVanillaTexture("sugar");
/*  282 */   public static Item cake = (new ItemReed(98, Block.cake, "cake")).setMaxStackSize(8).setUnlocalizedName("cake").setCreativeTab(CreativeTabs.tabFood);
/*  283 */   public static Item bed = (new ItemBed(99)).setMaxStackSize(1).setUnlocalizedName("bed");
/*  284 */   public static Item redstoneRepeater = (new ItemReed(100, Block.redstoneRepeaterIdle, "repeater")).setUnlocalizedName("diode").setCreativeTab(CreativeTabs.tabRedstone);
/*  285 */   public static Item cookie = (new ItemFood(101, Material.desert, 3, 1, 250, false, false, false, "VANILLA")).setPlantProduct().setUnlocalizedName("cookie").useVanillaTexture("cookie");
/*  286 */   public static ItemMap map = (ItemMap)(new ItemMap(102, "map_filled")).setUnlocalizedName("map");
/*  287 */   public static ItemShears shears = (ItemShears)(new ItemShears(103, Material.iron)).setUnlocalizedName("shears").useVanillaTexture("shears");
/*  288 */   public static Item melon = (new ItemFood(104, Material.fruit, 1, 1, 1000, false, false, true, "VANILLA")).setPlantProduct().setUnlocalizedName("melon").useVanillaTexture("melon");
/*  289 */   public static Item pumpkinSeeds = (new ItemSeeds(105, 1, 2, false, true, false, Block.pumpkinStem.blockID, Block.tilledField.blockID, "VANILLA")).setUnlocalizedName("seeds_pumpkin").useVanillaTexture("seeds_pumpkin");
/*  290 */   public static Item melonSeeds = (new ItemSeeds(106, 1, 1, false, true, false, Block.melonStem.blockID, Block.tilledField.blockID, "seeds_melon")).setUnlocalizedName("seeds_melon");
/*  291 */   public static ItemMeat beefRaw = (ItemMeat)(new ItemMeat(107, 5, 5, false, false, "beef_raw")).setUnlocalizedName("beefRaw");
/*  292 */   public static ItemMeat beefCooked = (ItemMeat)(new ItemMeat(108, 10, 10, false, true, "beef_cooked")).setUnlocalizedName("beefCooked");
/*      */   
/*  294 */   public static ItemMeat chickenRaw = (ItemMeat)(new ItemMeat(109, 3, 3, false, false, "VANILLA")).setPotionEffect(Potion.poison.id, 20, 0, 0.3F).setUnlocalizedName("chickenRaw").useVanillaTexture("chicken_raw");
/*  295 */   public static ItemMeat chickenCooked = (ItemMeat)(new ItemMeat(110, 6, 6, false, true, "VANILLA")).setUnlocalizedName("chickenCooked").useVanillaTexture("chicken_cooked");
/*      */   
/*  297 */   public static Item rottenFlesh = (new ItemMeat(111, 2, 1, false, false, "VANILLA")).setPotionEffect(Potion.poison.id, 20, 0, 0.8F).setUnlocalizedName("rottenFlesh").useVanillaTexture("rotten_flesh");
/*  298 */   public static Item enderPearl = (new ItemEnderPearl(112)).setUnlocalizedName("enderPearl");
/*  299 */   public static Item blazeRod = (new Item(113, Material.blaze, "blaze_rod")).setCraftingDifficultyAsComponent(200.0F).setUnlocalizedName("blazeRod").setCreativeTab(CreativeTabs.tabMaterials);
/*  300 */   public static Item ghastTear = (new Item(114, Material.water, "ghast_tear")).setUnlocalizedName("ghastTear").setPotionEffect("+0-1-2-3&4-4+13").setCreativeTab(CreativeTabs.tabBrewing);
/*  301 */   public static ItemNugget goldNugget = (ItemNugget)(new ItemNugget(115, Material.gold)).setUnlocalizedName("goldNugget");
/*  302 */   public static Item netherStalkSeeds = (new ItemSeeds(116, 1, 1, false, false, false, Block.netherStalk.blockID, Block.slowSand.blockID, "VANILLA")).setUnlocalizedName("netherStalkSeeds").setPotionEffect("+4").useVanillaTexture("nether_wart");
/*  303 */   public static ItemPotion potion = (ItemPotion)(new ItemPotion(117)).setUnlocalizedName("potion");
/*  304 */   public static Item glassBottle = (new ItemGlassBottle(118)).setUnlocalizedName("glassBottle");
/*  305 */   public static Item spiderEye = (new ItemFood(119, Material.meat, 0, 1, true, false, false, "VANILLA")).setAnimalProduct().setPotionEffect(Potion.poison.id, 5, 0, 1.0F).setUnlocalizedName("spiderEye").setPotionEffect(PotionHelper.spiderEyeEffect).useVanillaTexture("spider_eye");
/*  306 */   public static Item fermentedSpiderEye = (new Item(120, Material.meat, "spider_eye_fermented")).setUnlocalizedName("fermentedSpiderEye").setPotionEffect(PotionHelper.fermentedSpiderEyeEffect).setCreativeTab(CreativeTabs.tabBrewing);
/*  307 */   public static Item blazePowder = (new Item(121, Material.blaze, "blaze_powder")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("blazePowder").setPotionEffect(PotionHelper.blazePowderEffect).setCreativeTab(CreativeTabs.tabBrewing);
/*  308 */   public static Item magmaCream = (new Item(122, Material.stone, "magma_cream")).setUnlocalizedName("magmaCream").setPotionEffect(PotionHelper.magmaCreamEffect).setCreativeTab(CreativeTabs.tabBrewing);
/*  309 */   public static Item brewingStand = (new ItemReed(123, Block.brewingStand, "brewing_stand")).setUnlocalizedName("brewingStand").setCreativeTab(CreativeTabs.tabBrewing);
/*  310 */   public static Item cauldron = (new ItemReed(124, Block.cauldron, "cauldron")).setMaxStackSize(4).setUnlocalizedName("cauldron").setCreativeTab(CreativeTabs.tabBrewing);
/*  311 */   public static Item eyeOfEnder = (new ItemEnderEye(125)).setUnlocalizedName("eyeOfEnder");
/*  312 */   public static Item speckledMelon = (new Item(126, Material.fruit, "melon_speckled")).addMaterial(new Material[] { Material.gold }).setUnlocalizedName("speckledMelon").setPotionEffect(PotionHelper.speckledMelonEffect).setCreativeTab(CreativeTabs.tabBrewing);
/*  313 */   public static Item monsterPlacer = (new ItemMonsterPlacer(127)).setUnlocalizedName("monsterPlacer");
/*      */   
/*  315 */   public static Item expBottle = (new ItemExpBottle(128)).setContainerItem(glassBottle).setUnlocalizedName("expBottle");
/*  316 */   public static Item fireballCharge = (new ItemFireball(129)).setUnlocalizedName("fireball");
/*  317 */   public static Item writableBook = (new ItemWritableBook(130)).setUnlocalizedName("writingBook").setCreativeTab(CreativeTabs.tabMisc);
/*  318 */   public static Item writtenBook = (new ItemEditableBook(131)).setUnlocalizedName("writtenBook");
/*  319 */   public static Item emerald = (new ItemRock(132, Material.emerald, "emerald")).setXPReward(20).setUnlocalizedName("emerald");
/*  320 */   public static Item itemFrame = (new ItemHangingEntity(133, EntityItemFrame.class, "item_frame")).setMaterial(new Material[] { Material.wood, Material.leather }).setUnlocalizedName("frame");
/*  321 */   public static Item flowerPot = (new ItemReed(134, Block.flowerPot, "flower_pot")).setUnlocalizedName("flowerPot").setCreativeTab(CreativeTabs.tabDecorations);
/*  322 */   public static Item carrot = (new ItemSeedFood(135, 1, 2, false, true, Block.carrot.blockID, Block.tilledField.blockID, "VANILLA")).setUnlocalizedName("carrots").useVanillaTexture("carrot");
/*  323 */   public static ItemFood potato = (ItemFood)(new ItemSeedFood(136, 3, 1, false, false, Block.potato.blockID, Block.tilledField.blockID, "VANILLA")).setUnlocalizedName("potato").useVanillaTexture("potato");
/*  324 */   public static ItemFood bakedPotato = (ItemFood)(new ItemFood(137, Material.vegetable, 6, 2, false, false, false, "VANILLA")).setPlantProduct().setUnlocalizedName("potatoBaked").useVanillaTexture("potato_baked");
/*  325 */   public static Item poisonousPotato = (new ItemFood(138, Material.vegetable, 2, 0, false, false, false, "VANILLA")).setPlantProduct().setPotionEffect(Potion.poison.id, 5, 0, 0.6F).setUnlocalizedName("potatoPoisonous").useVanillaTexture("potato_poisonous");
/*  326 */   public static ItemEmptyMap emptyMap = (ItemEmptyMap)(new ItemEmptyMap(139)).setUnlocalizedName("emptyMap");
/*  327 */   public static Item goldenCarrot = (new ItemFood(140, Material.vegetable, 1, 2, false, false, true, "VANILLA")).setPlantProduct().setUnlocalizedName("carrotGolden").setPotionEffect(PotionHelper.goldenCarrotEffect).useVanillaTexture("carrot_golden");
/*  328 */   public static Item skull = (new ItemSkull(141)).setUnlocalizedName("skull");
/*  329 */   public static Item carrotOnAStickIron = (new ItemCarrotOnAStick(142, Material.iron)).setUnlocalizedName("carrotOnAStick");
/*  330 */   public static Item netherStar = (new ItemSimpleFoiled(143, "nether_star")).setMaterial(new Material[] { Material.stone }).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("netherStar").setCreativeTab(CreativeTabs.tabMaterials);
/*  331 */   public static Item pumpkinPie = (new ItemFood(144, Material.pie, 10, 6, 1000, true, false, true, "VANILLA")).setMaxStackSize(8).setPlantProduct().setAnimalProduct().setUnlocalizedName("pumpkinPie").useVanillaTexture("pumpkin_pie");
/*  332 */   public static Item firework = (new ItemFirework(145)).setUnlocalizedName("fireworks");
/*  333 */   public static Item fireworkCharge = (new ItemFireworkCharge(146)).setUnlocalizedName("fireworksCharge").setCreativeTab(CreativeTabs.tabMisc);
/*  334 */   public static ItemEnchantedBook enchantedBook = (ItemEnchantedBook)(new ItemEnchantedBook(147)).setMaxStackSize(1).setUnlocalizedName("enchantedBook");
/*      */   
/*  336 */   public static Item comparator = (new ItemReed(148, Block.redstoneComparatorIdle, "comparator")).setMaterial(new Material[] { Material.redstone, Material.coal, Material.quartz, Material.stone }).setUnlocalizedName("comparator").setCreativeTab(CreativeTabs.tabRedstone);
/*      */   
/*  338 */   public static Item netherrackBrick = (new ItemBrick(149, Material.stone, "netherbrick")).setUnlocalizedName("netherbrick");
/*  339 */   public static Item netherQuartz = (new ItemRock(150, Material.quartz, "quartz")).setXPReward(10).setMaxStackSize(16).setUnlocalizedName("netherquartz");
/*  340 */   public static Item minecartTnt = (new ItemMinecart(151, 3, "minecart_tnt")).setUnlocalizedName("minecartTnt");
/*  341 */   public static Item minecartHopper = (new ItemMinecart(152, 5, "minecart_hopper")).setUnlocalizedName("minecartHopper");
/*  342 */   public static ItemHorseArmor horseArmorIron = (ItemHorseArmor)(new ItemHorseArmor(161, Material.iron)).setUnlocalizedName("horsearmormetal").useVanillaTexture("iron_horse_armor");
/*  343 */   public static ItemHorseArmor horseArmorGold = (ItemHorseArmor)(new ItemHorseArmor(162, Material.gold)).setUnlocalizedName("horsearmorgold").useVanillaTexture("gold_horse_armor");
/*  344 */   public static Item leash = (new ItemLeash(164)).setUnlocalizedName("leash");
/*  345 */   public static Item nameTag = (new ItemNameTag(165)).setUnlocalizedName("nameTag");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  351 */   public static ItemNugget copperNugget = (ItemNugget)(new ItemNugget(700, Material.copper)).setUnlocalizedName("copperNugget");
/*  352 */   public static ItemNugget silverNugget = (ItemNugget)(new ItemNugget(701, Material.silver)).setUnlocalizedName("silverNugget");
/*  353 */   public static ItemNugget ironNugget = (ItemNugget)(new ItemNugget(702, Material.iron)).setUnlocalizedName("ironNugget");
/*  354 */   public static ItemNugget mithrilNugget = (ItemNugget)(new ItemNugget(703, Material.mithril)).setUnlocalizedName("mithrilNugget");
/*  355 */   public static ItemNugget adamantiumNugget = (ItemNugget)(new ItemNugget(704, Material.adamantium)).setUnlocalizedName("adamantiumNugget");
/*  356 */   public static ItemNugget ancientMetalNugget = (ItemNugget)(new ItemNugget(705, Material.ancient_metal)).setUnlocalizedName("ancientMetalNugget");
/*      */   
/*  358 */   public static Item ingotCopper = (new ItemIngot(706, Material.copper)).setXPReward(10).setUnlocalizedName("ingotCopper");
/*  359 */   public static Item ingotSilver = (new ItemIngot(707, Material.silver)).setXPReward(15).setUnlocalizedName("ingotSilver");
/*  360 */   public static Item ingotMithril = (new ItemIngot(708, Material.mithril)).setXPReward(40).setUnlocalizedName("ingotMithril");
/*  361 */   public static Item ingotAdamantium = (new ItemIngot(709, Material.adamantium)).setXPReward(100).setUnlocalizedName("ingotAdamantium");
/*  362 */   public static Item ingotAncientMetal = (new ItemIngot(710, Material.ancient_metal)).setXPReward(20).setUnlocalizedName("ingotAncientMetal");
/*      */   
/*  364 */   public static Item pickaxeCopper = (new ItemPickaxe(711, Material.copper)).setUnlocalizedName("pickaxeCopper");
/*  365 */   public static Item pickaxeSilver = (new ItemPickaxe(712, Material.silver)).setUnlocalizedName("pickaxeSilver");
/*  366 */   public static Item pickaxeMithril = (new ItemPickaxe(713, Material.mithril)).setUnlocalizedName("pickaxeMithril");
/*  367 */   public static Item pickaxeAdamantium = (new ItemPickaxe(714, Material.adamantium)).setUnlocalizedName("pickaxeAdamantium");
/*  368 */   public static Item pickaxeRustedIron = (new ItemPickaxe(715, Material.rusted_iron)).setUnlocalizedName("pickaxeRustedIron");
/*  369 */   public static Item pickaxeAncientMetal = (new ItemPickaxe(983, Material.ancient_metal)).setUnlocalizedName("pickaxeAncientMetal");
/*      */   
/*  371 */   public static Item shovelFlint = (new ItemShovel(716, Material.flint)).setUnlocalizedName("shovelFlint");
/*  372 */   public static Item shovelCopper = (new ItemShovel(717, Material.copper)).setUnlocalizedName("shovelCopper");
/*  373 */   public static Item shovelSilver = (new ItemShovel(718, Material.silver)).setUnlocalizedName("shovelSilver");
/*  374 */   public static Item shovelMithril = (new ItemShovel(719, Material.mithril)).setUnlocalizedName("shovelMithril");
/*  375 */   public static Item shovelAdamantium = (new ItemShovel(720, Material.adamantium)).setUnlocalizedName("shovelAdamantium");
/*  376 */   public static Item shovelRustedIron = (new ItemShovel(721, Material.rusted_iron)).setUnlocalizedName("shovelRustedIron");
/*  377 */   public static Item shovelAncientMetal = (new ItemShovel(984, Material.ancient_metal)).setUnlocalizedName("shovelAncientMetal");
/*      */ 
/*      */ 
/*      */   
/*  381 */   public static Item hatchetFlint = (new ItemHatchet(722, Material.flint)).setUnlocalizedName("hatchetFlint");
/*  382 */   public static Item axeFlint = (new ItemAxe(723, Material.flint)).setUnlocalizedName("axeFlint");
/*  383 */   public static Item axeCopper = (new ItemAxe(724, Material.copper)).setUnlocalizedName("axeCopper");
/*  384 */   public static Item axeSilver = (new ItemAxe(725, Material.silver)).setUnlocalizedName("axeSilver");
/*  385 */   public static Item axeMithril = (new ItemAxe(726, Material.mithril)).setUnlocalizedName("axeMithril");
/*  386 */   public static Item axeAdamantium = (new ItemAxe(727, Material.adamantium)).setUnlocalizedName("axeAdamantium");
/*  387 */   public static Item axeRustedIron = (new ItemAxe(728, Material.rusted_iron)).setUnlocalizedName("axeRustedIron");
/*  388 */   public static Item axeAncientMetal = (new ItemAxe(985, Material.ancient_metal)).setUnlocalizedName("axeAncientMetal");
/*      */   
/*  390 */   public static Item hoeCopper = (new ItemHoe(729, Material.copper)).setUnlocalizedName("hoeCopper");
/*  391 */   public static Item hoeSilver = (new ItemHoe(730, Material.silver)).setUnlocalizedName("hoeSilver");
/*  392 */   public static Item hoeMithril = (new ItemHoe(731, Material.mithril)).setUnlocalizedName("hoeMithril");
/*  393 */   public static Item hoeAdamantium = (new ItemHoe(732, Material.adamantium)).setUnlocalizedName("hoeAdamantium");
/*  394 */   public static Item hoeRustedIron = (new ItemHoe(733, Material.rusted_iron)).setUnlocalizedName("hoeRustedIron");
/*  395 */   public static Item hoeAncientMetal = (new ItemHoe(986, Material.ancient_metal)).setUnlocalizedName("hoeAncientMetal");
/*      */   
/*  397 */   public static Item warHammerCopper = (new ItemWarHammer(734, Material.copper)).setUnlocalizedName("warHammerCopper");
/*  398 */   public static Item warHammerSilver = (new ItemWarHammer(735, Material.silver)).setUnlocalizedName("warHammerSilver");
/*  399 */   public static Item warHammerGold = (new ItemWarHammer(736, Material.gold)).setUnlocalizedName("warHammerGold");
/*  400 */   public static Item warHammerIron = (new ItemWarHammer(737, Material.iron)).setUnlocalizedName("warHammerIron");
/*  401 */   public static Item warHammerMithril = (new ItemWarHammer(738, Material.mithril)).setUnlocalizedName("warHammerMithril");
/*  402 */   public static Item warHammerAdamantium = (new ItemWarHammer(739, Material.adamantium)).setUnlocalizedName("warHammerAdamantium");
/*  403 */   public static Item warHammerRustedIron = (new ItemWarHammer(740, Material.rusted_iron)).setUnlocalizedName("warHammerRustedIron");
/*  404 */   public static Item warHammerAncientMetal = (new ItemWarHammer(987, Material.ancient_metal)).setUnlocalizedName("warHammerAncientMetal");
/*      */   
/*  406 */   public static Item mattockCopper = (new ItemMattock(741, Material.copper)).setUnlocalizedName("mattockCopper");
/*  407 */   public static Item mattockSilver = (new ItemMattock(742, Material.silver)).setUnlocalizedName("mattockSilver");
/*  408 */   public static Item mattockGold = (new ItemMattock(743, Material.gold)).setUnlocalizedName("mattockGold");
/*  409 */   public static Item mattockIron = (new ItemMattock(744, Material.iron)).setUnlocalizedName("mattockIron");
/*  410 */   public static Item mattockMithril = (new ItemMattock(745, Material.mithril)).setUnlocalizedName("mattockMithril");
/*  411 */   public static Item mattockAdamantium = (new ItemMattock(746, Material.adamantium)).setUnlocalizedName("mattockAdamantium");
/*  412 */   public static Item mattockRustedIron = (new ItemMattock(747, Material.rusted_iron)).setUnlocalizedName("mattockRustedIron");
/*  413 */   public static Item mattockAncientMetal = (new ItemMattock(988, Material.ancient_metal)).setUnlocalizedName("mattockAncientMetal");
/*      */   
/*  415 */   public static Item battleAxeCopper = (new ItemBattleAxe(748, Material.copper)).setUnlocalizedName("battleAxeCopper");
/*  416 */   public static Item battleAxeSilver = (new ItemBattleAxe(749, Material.silver)).setUnlocalizedName("battleAxeSilver");
/*  417 */   public static Item battleAxeGold = (new ItemBattleAxe(750, Material.gold)).setUnlocalizedName("battleAxeGold");
/*  418 */   public static Item battleAxeIron = (new ItemBattleAxe(751, Material.iron)).setUnlocalizedName("battleAxeIron");
/*  419 */   public static Item battleAxeMithril = (new ItemBattleAxe(752, Material.mithril)).setUnlocalizedName("battleAxeMithril");
/*  420 */   public static Item battleAxeAdamantium = (new ItemBattleAxe(753, Material.adamantium)).setUnlocalizedName("battleAxeAdamantium");
/*  421 */   public static Item battleAxeRustedIron = (new ItemBattleAxe(754, Material.rusted_iron)).setUnlocalizedName("battleAxeRustedIron");
/*  422 */   public static Item battleAxeAncientMetal = (new ItemBattleAxe(989, Material.ancient_metal)).setUnlocalizedName("battleAxeAncientMetal");
/*      */   
/*  424 */   public static Item scytheCopper = (new ItemScythe(755, Material.copper)).setUnlocalizedName("scytheCopper");
/*  425 */   public static Item scytheSilver = (new ItemScythe(756, Material.silver)).setUnlocalizedName("scytheSilver");
/*  426 */   public static Item scytheGold = (new ItemScythe(757, Material.gold)).setUnlocalizedName("scytheGold");
/*  427 */   public static Item scytheIron = (new ItemScythe(758, Material.iron)).setUnlocalizedName("scytheIron");
/*  428 */   public static Item scytheMithril = (new ItemScythe(759, Material.mithril)).setUnlocalizedName("scytheMithril");
/*  429 */   public static Item scytheAdamantium = (new ItemScythe(760, Material.adamantium)).setUnlocalizedName("scytheAdamantium");
/*  430 */   public static Item scytheRustedIron = (new ItemScythe(761, Material.rusted_iron)).setUnlocalizedName("scytheRustedIron");
/*  431 */   public static Item scytheAncientMetal = (new ItemScythe(990, Material.ancient_metal)).setUnlocalizedName("scytheAncientMetal");
/*      */   
/*  433 */   public static ItemShears shearsCopper = (ItemShears)(new ItemShears(762, Material.copper)).setUnlocalizedName("shearsCopper");
/*  434 */   public static ItemShears shearsSilver = (ItemShears)(new ItemShears(763, Material.silver)).setUnlocalizedName("shearsSilver");
/*  435 */   public static ItemShears shearsGold = (ItemShears)(new ItemShears(764, Material.gold)).setUnlocalizedName("shearsGold");
/*  436 */   public static ItemShears shearsMithril = (ItemShears)(new ItemShears(765, Material.mithril)).setUnlocalizedName("shearsMithril");
/*  437 */   public static ItemShears shearsAdamantium = (ItemShears)(new ItemShears(766, Material.adamantium)).setUnlocalizedName("shearsAdamantium");
/*  438 */   public static ItemShears shearsAncientMetal = (ItemShears)(new ItemShears(991, Material.ancient_metal)).setUnlocalizedName("shearsAncientMetal");
/*      */   
/*  440 */   public static Item knifeFlint = (new ItemKnife(767, Material.flint)).setUnlocalizedName("knifeFlint");
/*  441 */   public static Item cudgelWood = (new ItemCudgel(768, Material.wood)).setUnlocalizedName("cudgelWood");
/*  442 */   public static Item clubWood = (new ItemClub(769, Material.wood)).setUnlocalizedName("clubWood");
/*      */ 
/*      */ 
/*      */   
/*  446 */   public static Item swordCopper = (new ItemSword(772, Material.copper)).setUnlocalizedName("swordCopper");
/*  447 */   public static Item swordSilver = (new ItemSword(773, Material.silver)).setUnlocalizedName("swordSilver");
/*  448 */   public static Item swordMithril = (new ItemSword(774, Material.mithril)).setUnlocalizedName("swordMithril");
/*  449 */   public static Item swordAdamantium = (new ItemSword(775, Material.adamantium)).setUnlocalizedName("swordAdamantium");
/*  450 */   public static Item swordRustedIron = (new ItemSword(776, Material.rusted_iron)).setUnlocalizedName("swordRustedIron");
/*  451 */   public static Item swordAncientMetal = (new ItemSword(992, Material.ancient_metal)).setUnlocalizedName("swordAncientMetal");
/*      */   
/*  453 */   public static Item daggerCopper = (new ItemDagger(777, Material.copper)).setUnlocalizedName("daggerCopper");
/*  454 */   public static Item daggerSilver = (new ItemDagger(778, Material.silver)).setUnlocalizedName("daggerSilver");
/*  455 */   public static Item daggerGold = (new ItemDagger(779, Material.gold)).setUnlocalizedName("daggerGold");
/*  456 */   public static Item daggerIron = (new ItemDagger(780, Material.iron)).setUnlocalizedName("daggerIron");
/*  457 */   public static Item daggerMithril = (new ItemDagger(781, Material.mithril)).setUnlocalizedName("daggerMithril");
/*  458 */   public static Item daggerAdamantium = (new ItemDagger(782, Material.adamantium)).setUnlocalizedName("daggerAdamantium");
/*  459 */   public static Item daggerRustedIron = (new ItemDagger(783, Material.rusted_iron)).setUnlocalizedName("daggerRustedIron");
/*  460 */   public static Item daggerAncientMetal = (new ItemDagger(993, Material.ancient_metal)).setUnlocalizedName("daggerAncientMetal");
/*      */   
/*  462 */   public static ItemArrow arrowFlint = (ItemArrow)(new ItemArrow(784, Material.flint)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowFlint");
/*  463 */   public static ItemArrow arrowCopper = (ItemArrow)(new ItemArrow(785, Material.copper)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowCopper");
/*  464 */   public static ItemArrow arrowSilver = (ItemArrow)(new ItemArrow(786, Material.silver)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowSilver");
/*  465 */   public static ItemArrow arrowGold = (ItemArrow)(new ItemArrow(787, Material.gold)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowGold");
/*  466 */   public static ItemArrow arrowIron = (ItemArrow)(new ItemArrow(788, Material.iron)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowIron");
/*  467 */   public static ItemArrow arrowMithril = (ItemArrow)(new ItemArrow(789, Material.mithril)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowMithril");
/*  468 */   public static ItemArrow arrowAdamantium = (ItemArrow)(new ItemArrow(790, Material.adamantium)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowAdamantium");
/*  469 */   public static ItemArrow arrowRustedIron = (ItemArrow)(new ItemArrow(791, Material.rusted_iron)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowRustedIron");
/*  470 */   public static ItemArrow arrowAncientMetal = (ItemArrow)(new ItemArrow(994, Material.ancient_metal)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowAncientMetal");
/*      */   
/*  472 */   public static ItemBow bowMithril = (ItemBow)(new ItemBow(792, Material.mithril)).setUnlocalizedName("bowMithril");
/*  473 */   public static ItemBow bowAncientMetal = (ItemBow)(new ItemBow(995, Material.ancient_metal)).setUnlocalizedName("bowAncientMetal");
/*  474 */   public static ItemArrow arrowObsidian = (ItemArrow)(new ItemArrow(793, Material.obsidian)).setCraftingDifficultyAsComponent(40.0F).setUnlocalizedName("arrowObsidian");
/*      */   
/*  476 */   public static Item knifeCopper = (new ItemKnife(794, Material.copper)).setUnlocalizedName("knifeCopper");
/*  477 */   public static Item knifeSilver = (new ItemKnife(795, Material.silver)).setUnlocalizedName("knifeSilver");
/*  478 */   public static Item knifeGold = (new ItemKnife(796, Material.gold)).setUnlocalizedName("knifeGold");
/*  479 */   public static Item knifeIron = (new ItemKnife(797, Material.iron)).setUnlocalizedName("knifeIron");
/*  480 */   public static Item knifeMithril = (new ItemKnife(798, Material.mithril)).setUnlocalizedName("knifeMithril");
/*  481 */   public static Item knifeAdamantium = (new ItemKnife(799, Material.adamantium)).setUnlocalizedName("knifeAdamantium");
/*  482 */   public static Item knifeRustedIron = (new ItemKnife(800, Material.rusted_iron)).setUnlocalizedName("knifeRustedIron");
/*  483 */   public static Item knifeAncientMetal = (new ItemKnife(801, Material.ancient_metal)).setUnlocalizedName("knifeAncientMetal");
/*      */ 
/*      */ 
/*      */   
/*  487 */   public static ItemHelmet helmetCopper = (ItemHelmet)(new ItemHelmet(811, Material.copper, false)).setUnlocalizedName("helmetCopper");
/*  488 */   public static ItemHelmet helmetSilver = (ItemHelmet)(new ItemHelmet(812, Material.silver, false)).setUnlocalizedName("helmetSilver");
/*  489 */   public static ItemHelmet helmetMithril = (ItemHelmet)(new ItemHelmet(813, Material.mithril, false)).setUnlocalizedName("helmetMithril");
/*  490 */   public static ItemHelmet helmetAdamantium = (ItemHelmet)(new ItemHelmet(814, Material.adamantium, false)).setUnlocalizedName("helmetAdamantium");
/*  491 */   public static ItemHelmet helmetAncientMetal = (ItemHelmet)(new ItemHelmet(996, Material.ancient_metal, false)).setUnlocalizedName("helmetAncientMetal");
/*  492 */   public static ItemHelmet helmetChainCopper = (ItemHelmet)(new ItemHelmet(815, Material.copper, true)).setUnlocalizedName("helmetChainCopper");
/*  493 */   public static ItemHelmet helmetChainSilver = (ItemHelmet)(new ItemHelmet(816, Material.silver, true)).setUnlocalizedName("helmetChainSilver");
/*  494 */   public static ItemHelmet helmetChainGold = (ItemHelmet)(new ItemHelmet(817, Material.gold, true)).setUnlocalizedName("helmetChainGold");
/*  495 */   public static ItemHelmet helmetChainMithril = (ItemHelmet)(new ItemHelmet(818, Material.mithril, true)).setUnlocalizedName("helmetChainMithril");
/*  496 */   public static ItemHelmet helmetChainAdamantium = (ItemHelmet)(new ItemHelmet(819, Material.adamantium, true)).setUnlocalizedName("helmetChainAdamantium");
/*  497 */   public static ItemHelmet helmetRustedIron = (ItemHelmet)(new ItemHelmet(820, Material.rusted_iron, false)).setUnlocalizedName("helmetRustedIron");
/*  498 */   public static ItemHelmet helmetChainRustedIron = (ItemHelmet)(new ItemHelmet(821, Material.rusted_iron, true)).setUnlocalizedName("helmetChainRustedIron");
/*  499 */   public static ItemHelmet helmetChainAncientMetal = (ItemHelmet)(new ItemHelmet(997, Material.ancient_metal, true)).setUnlocalizedName("helmetChainAncientMetal");
/*      */   
/*  501 */   public static ItemCuirass plateCopper = (ItemCuirass)(new ItemCuirass(822, Material.copper, false)).setUnlocalizedName("chestplateCopper");
/*  502 */   public static ItemCuirass plateSilver = (ItemCuirass)(new ItemCuirass(823, Material.silver, false)).setUnlocalizedName("chestplateSilver");
/*  503 */   public static ItemCuirass plateMithril = (ItemCuirass)(new ItemCuirass(824, Material.mithril, false)).setUnlocalizedName("chestplateMithril");
/*  504 */   public static ItemCuirass plateAdamantium = (ItemCuirass)(new ItemCuirass(825, Material.adamantium, false)).setUnlocalizedName("chestplateAdamantium");
/*  505 */   public static ItemCuirass plateAncientMetal = (ItemCuirass)(new ItemCuirass(998, Material.ancient_metal, false)).setUnlocalizedName("chestplateAncientMetal");
/*  506 */   public static ItemCuirass plateChainCopper = (ItemCuirass)(new ItemCuirass(826, Material.copper, true)).setUnlocalizedName("chestplateChainCopper");
/*  507 */   public static ItemCuirass plateChainSilver = (ItemCuirass)(new ItemCuirass(827, Material.silver, true)).setUnlocalizedName("chestplateChainSilver");
/*  508 */   public static ItemCuirass plateChainGold = (ItemCuirass)(new ItemCuirass(828, Material.gold, true)).setUnlocalizedName("chestplateChainGold");
/*  509 */   public static ItemCuirass plateChainMithril = (ItemCuirass)(new ItemCuirass(829, Material.mithril, true)).setUnlocalizedName("chestplateChainMithril");
/*  510 */   public static ItemCuirass plateChainAdamantium = (ItemCuirass)(new ItemCuirass(830, Material.adamantium, true)).setUnlocalizedName("chestplateChainAdamantium");
/*  511 */   public static ItemCuirass plateRustedIron = (ItemCuirass)(new ItemCuirass(831, Material.rusted_iron, false)).setUnlocalizedName("chestplateRustedIron");
/*  512 */   public static ItemCuirass plateChainRustedIron = (ItemCuirass)(new ItemCuirass(832, Material.rusted_iron, true)).setUnlocalizedName("chestplateChainRustedIron");
/*  513 */   public static ItemCuirass plateChainAncientMetal = (ItemCuirass)(new ItemCuirass(999, Material.ancient_metal, true)).setUnlocalizedName("chestplateChainAncientMetal");
/*      */   
/*  515 */   public static ItemLeggings legsCopper = (ItemLeggings)(new ItemLeggings(833, Material.copper, false)).setUnlocalizedName("leggingsCopper");
/*  516 */   public static ItemLeggings legsSilver = (ItemLeggings)(new ItemLeggings(834, Material.silver, false)).setUnlocalizedName("leggingsSilver");
/*  517 */   public static ItemLeggings legsMithril = (ItemLeggings)(new ItemLeggings(835, Material.mithril, false)).setUnlocalizedName("leggingsMithril");
/*  518 */   public static ItemLeggings legsAdamantium = (ItemLeggings)(new ItemLeggings(836, Material.adamantium, false)).setUnlocalizedName("leggingsAdamantium");
/*  519 */   public static ItemLeggings legsAncientMetal = (ItemLeggings)(new ItemLeggings(1000, Material.ancient_metal, false)).setUnlocalizedName("leggingsAncientMetal");
/*  520 */   public static ItemLeggings legsChainCopper = (ItemLeggings)(new ItemLeggings(837, Material.copper, true)).setUnlocalizedName("leggingsChainCopper");
/*  521 */   public static ItemLeggings legsChainSilver = (ItemLeggings)(new ItemLeggings(838, Material.silver, true)).setUnlocalizedName("leggingsChainSilver");
/*  522 */   public static ItemLeggings legsChainGold = (ItemLeggings)(new ItemLeggings(839, Material.gold, true)).setUnlocalizedName("leggingsChainGold");
/*  523 */   public static ItemLeggings legsChainMithril = (ItemLeggings)(new ItemLeggings(840, Material.mithril, true)).setUnlocalizedName("leggingsChainMithril");
/*  524 */   public static ItemLeggings legsChainAdamantium = (ItemLeggings)(new ItemLeggings(841, Material.adamantium, true)).setUnlocalizedName("leggingsChainAdamantium");
/*  525 */   public static ItemLeggings legsRustedIron = (ItemLeggings)(new ItemLeggings(842, Material.rusted_iron, false)).setUnlocalizedName("leggingsRustedIron");
/*  526 */   public static ItemLeggings legsChainRustedIron = (ItemLeggings)(new ItemLeggings(843, Material.rusted_iron, true)).setUnlocalizedName("leggingsChainRustedIron");
/*  527 */   public static ItemLeggings legsChainAncientMetal = (ItemLeggings)(new ItemLeggings(1001, Material.ancient_metal, true)).setUnlocalizedName("leggingsChainAncientMetal");
/*      */ 
/*      */   
/*  530 */   public static ItemBoots bootsCopper = (ItemBoots)(new ItemBoots(844, Material.copper, false)).setUnlocalizedName("bootsCopper");
/*  531 */   public static ItemBoots bootsSilver = (ItemBoots)(new ItemBoots(845, Material.silver, false)).setUnlocalizedName("bootsSilver");
/*  532 */   public static ItemBoots bootsMithril = (ItemBoots)(new ItemBoots(846, Material.mithril, false)).setUnlocalizedName("bootsMithril");
/*  533 */   public static ItemBoots bootsAdamantium = (ItemBoots)(new ItemBoots(847, Material.adamantium, false)).setUnlocalizedName("bootsAdamantium");
/*  534 */   public static ItemBoots bootsAncientMetal = (ItemBoots)(new ItemBoots(1002, Material.ancient_metal, false)).setUnlocalizedName("bootsAncientMetal");
/*  535 */   public static ItemBoots bootsChainCopper = (ItemBoots)(new ItemBoots(848, Material.copper, true)).setUnlocalizedName("bootsChainCopper");
/*  536 */   public static ItemBoots bootsChainSilver = (ItemBoots)(new ItemBoots(849, Material.silver, true)).setUnlocalizedName("bootsChainSilver");
/*  537 */   public static ItemBoots bootsChainGold = (ItemBoots)(new ItemBoots(850, Material.gold, true)).setUnlocalizedName("bootsChainGold");
/*  538 */   public static ItemBoots bootsChainMithril = (ItemBoots)(new ItemBoots(851, Material.mithril, true)).setUnlocalizedName("bootsChainMithril");
/*  539 */   public static ItemBoots bootsChainAdamantium = (ItemBoots)(new ItemBoots(852, Material.adamantium, true)).setUnlocalizedName("bootsChainAdamantium");
/*  540 */   public static ItemBoots bootsRustedIron = (ItemBoots)(new ItemBoots(853, Material.rusted_iron, false)).setUnlocalizedName("bootsRustedIron");
/*  541 */   public static ItemBoots bootsChainRustedIron = (ItemBoots)(new ItemBoots(854, Material.rusted_iron, true)).setUnlocalizedName("bootsChainRustedIron");
/*  542 */   public static ItemBoots bootsChainAncientMetal = (ItemBoots)(new ItemBoots(1003, Material.ancient_metal, true)).setUnlocalizedName("bootsChainAncientMetal");
/*      */   
/*  544 */   public static Item doorCopper = (new ItemDoor(855, Material.copper)).setUnlocalizedName("doorCopper");
/*  545 */   public static Item doorSilver = (new ItemDoor(856, Material.silver)).setUnlocalizedName("doorSilver");
/*  546 */   public static Item doorGold = (new ItemDoor(857, Material.gold)).setUnlocalizedName("doorGold");
/*  547 */   public static Item doorMithril = (new ItemDoor(858, Material.mithril)).setUnlocalizedName("doorMithril");
/*  548 */   public static Item doorAdamantium = (new ItemDoor(859, Material.adamantium)).setUnlocalizedName("doorAdamantium");
/*  549 */   public static Item doorAncientMetal = (new ItemDoor(1004, Material.ancient_metal)).setUnlocalizedName("doorAncientMetal");
/*      */   
/*  551 */   public static Item shardEmerald = (new ItemShard(861, Material.emerald)).setUnlocalizedName("shardEmerald");
/*  552 */   public static Item shardDiamond = (new ItemShard(862, Material.diamond)).setUnlocalizedName("shardDiamond");
/*  553 */   public static Item shardNetherQuartz = (new ItemShard(863, Material.quartz)).setUnlocalizedName("shardNetherQuartz");
/*  554 */   public static Item shardGlass = (new ItemShard(864, Material.glass)).setUnlocalizedName("shardGlass");
/*  555 */   public static Item chipFlint = (new ItemShard(865, Material.flint)).setUnlocalizedName("chipFlint");
/*  556 */   public static Item shardObsidian = (new ItemShard(866, Material.obsidian)).setUnlocalizedName("shardObsidian");
/*      */   
/*  558 */   public static Item chainCopper = (new ItemChain(867, Material.copper)).setUnlocalizedName("chainCopper");
/*  559 */   public static Item chainSilver = (new ItemChain(868, Material.silver)).setUnlocalizedName("chainSilver");
/*  560 */   public static Item chainGold = (new ItemChain(869, Material.gold)).setUnlocalizedName("chainGold");
/*  561 */   public static Item chainIron = (new ItemChain(870, Material.iron)).setUnlocalizedName("chainIron");
/*  562 */   public static Item chainMithril = (new ItemChain(871, Material.mithril)).setUnlocalizedName("chainMithril");
/*  563 */   public static Item chainAdamantium = (new ItemChain(872, Material.adamantium)).setUnlocalizedName("chainAdamantium");
/*      */   
/*  565 */   public static Item chainRustedIron = (new ItemChain(873, Material.rusted_iron)).setUnlocalizedName("chainRustedIron");
/*  566 */   public static Item chainAncientMetal = (new ItemChain(1005, Material.ancient_metal)).setUnlocalizedName("chainAncientMetal");
/*      */   
/*  568 */   public static ItemHorseArmor horseArmorCopper = (ItemHorseArmor)(new ItemHorseArmor(874, Material.copper)).setUnlocalizedName("horsearmorcopper");
/*  569 */   public static ItemHorseArmor horseArmorSilver = (ItemHorseArmor)(new ItemHorseArmor(875, Material.silver)).setUnlocalizedName("horsearmorsilver");
/*  570 */   public static ItemHorseArmor horseArmorMithril = (ItemHorseArmor)(new ItemHorseArmor(876, Material.mithril)).setUnlocalizedName("horsearmormithril");
/*  571 */   public static ItemHorseArmor horseArmorAdamantium = (ItemHorseArmor)(new ItemHorseArmor(877, Material.adamantium)).setUnlocalizedName("horsearmoradamantium");
/*  572 */   public static ItemHorseArmor horseArmorAncientMetal = (ItemHorseArmor)(new ItemHorseArmor(878, Material.ancient_metal)).setUnlocalizedName("horsearmorancientmetal");
/*      */ 
/*      */   
/*  575 */   public static ItemBucket bucketCopperEmpty = (ItemBucket)(new ItemBucket(886, Material.copper, null)).setUnlocalizedName("bucketCopper");
/*  576 */   public static ItemBucket bucketSilverEmpty = (ItemBucket)(new ItemBucket(887, Material.silver, null)).setUnlocalizedName("bucketSilver");
/*  577 */   public static ItemBucket bucketGoldEmpty = (ItemBucket)(new ItemBucket(888, Material.gold, null)).setUnlocalizedName("bucketGold");
/*  578 */   public static ItemBucket bucketMithrilEmpty = (ItemBucket)(new ItemBucket(889, Material.mithril, null)).setUnlocalizedName("bucketMithril");
/*  579 */   public static ItemBucket bucketAdamantiumEmpty = (ItemBucket)(new ItemBucket(890, Material.adamantium, null)).setUnlocalizedName("bucketAdamantium");
/*  580 */   public static ItemBucket bucketAncientMetalEmpty = (ItemBucket)(new ItemBucket(891, Material.ancient_metal, null)).setUnlocalizedName("bucketAncientMetal");
/*      */   
/*  582 */   public static ItemBucket bucketCopperWater = (ItemBucket)(new ItemBucket(892, Material.copper, Material.water)).setUnlocalizedName("bucketCopperWater").setContainerItem(bucketCopperEmpty);
/*  583 */   public static ItemBucket bucketSilverWater = (ItemBucket)(new ItemBucket(893, Material.silver, Material.water)).setUnlocalizedName("bucketSilverWater").setContainerItem(bucketSilverEmpty);
/*  584 */   public static ItemBucket bucketGoldWater = (ItemBucket)(new ItemBucket(894, Material.gold, Material.water)).setUnlocalizedName("bucketGoldWater").setContainerItem(bucketGoldEmpty);
/*  585 */   public static ItemBucket bucketMithrilWater = (ItemBucket)(new ItemBucket(895, Material.mithril, Material.water)).setUnlocalizedName("bucketMithrilWater").setContainerItem(bucketMithrilEmpty);
/*  586 */   public static ItemBucket bucketAdamantiumWater = (ItemBucket)(new ItemBucket(896, Material.adamantium, Material.water)).setUnlocalizedName("bucketAdamantiumWater").setContainerItem(bucketAdamantiumEmpty);
/*  587 */   public static ItemBucket bucketAncientMetalWater = (ItemBucket)(new ItemBucket(897, Material.ancient_metal, Material.water)).setUnlocalizedName("bucketAncientMetalWater").setContainerItem(bucketAncientMetalEmpty);
/*      */   
/*  589 */   public static ItemBucket bucketCopperLava = (ItemBucket)(new ItemBucket(898, Material.copper, Material.lava)).setUnlocalizedName("bucketCopperLava").setContainerItem(bucketCopperEmpty);
/*  590 */   public static ItemBucket bucketSilverLava = (ItemBucket)(new ItemBucket(899, Material.silver, Material.lava)).setUnlocalizedName("bucketSilverLava").setContainerItem(bucketSilverEmpty);
/*  591 */   public static ItemBucket bucketGoldLava = (ItemBucket)(new ItemBucket(900, Material.gold, Material.lava)).setUnlocalizedName("bucketGoldLava").setContainerItem(bucketGoldEmpty);
/*  592 */   public static ItemBucket bucketMithrilLava = (ItemBucket)(new ItemBucket(901, Material.mithril, Material.lava)).setUnlocalizedName("bucketMithrilLava").setContainerItem(bucketMithrilEmpty);
/*  593 */   public static ItemBucket bucketAdamantiumLava = (ItemBucket)(new ItemBucket(902, Material.adamantium, Material.lava)).setUnlocalizedName("bucketAdamantiumLava").setContainerItem(bucketAdamantiumEmpty);
/*  594 */   public static ItemBucket bucketAncientMetalLava = (ItemBucket)(new ItemBucket(903, Material.ancient_metal, Material.lava)).setUnlocalizedName("bucketAncientMetalLava").setContainerItem(bucketAncientMetalEmpty);
/*      */   
/*  596 */   public static ItemBucketMilk bucketCopperMilk = (ItemBucketMilk)(new ItemBucketMilk(904, Material.copper)).setUnlocalizedName("bucketCopperMilk").setContainerItem(bucketCopperEmpty);
/*  597 */   public static ItemBucketMilk bucketSilverMilk = (ItemBucketMilk)(new ItemBucketMilk(905, Material.silver)).setUnlocalizedName("bucketSilverMilk").setContainerItem(bucketSilverEmpty);
/*  598 */   public static ItemBucketMilk bucketGoldMilk = (ItemBucketMilk)(new ItemBucketMilk(906, Material.gold)).setUnlocalizedName("bucketGoldMilk").setContainerItem(bucketGoldEmpty);
/*  599 */   public static ItemBucketMilk bucketMithrilMilk = (ItemBucketMilk)(new ItemBucketMilk(907, Material.mithril)).setUnlocalizedName("bucketMithrilMilk").setContainerItem(bucketMithrilEmpty);
/*  600 */   public static ItemBucketMilk bucketAdamantiumMilk = (ItemBucketMilk)(new ItemBucketMilk(908, Material.adamantium)).setUnlocalizedName("bucketAdamantiumMilk").setContainerItem(bucketAdamantiumEmpty);
/*  601 */   public static ItemBucketMilk bucketAncientMetalMilk = (ItemBucketMilk)(new ItemBucketMilk(909, Material.ancient_metal)).setUnlocalizedName("bucketAncientMetalMilk").setContainerItem(bucketAncientMetalEmpty);
/*      */   
/*  603 */   public static ItemBowl bowlMilk = (ItemBowl)(new ItemBowl(910, Material.milk, "bowl_milk")).setFoodValue(0, 1, true, false, false).setAnimalProduct().setContainerItem(bowlEmpty).setAlwaysEdible().setUnlocalizedName("bowlMilk");
/*  604 */   public static ItemBowl bowlWater = (ItemBowl)(new ItemBowl(911, Material.water, "bowl_water")).setContainerItem(bowlEmpty).setUnlocalizedName("bowlWater");
/*      */ 
/*      */   
/*  607 */   public static ItemMeat lambchopRaw = (ItemMeat)(new ItemMeat(916, 3, 3, false, false, "lambchop_raw")).setUnlocalizedName("lambchopRaw");
/*  608 */   public static ItemMeat lambchopCooked = (ItemMeat)(new ItemMeat(917, 6, 6, false, true, "lambchop_cooked")).setUnlocalizedName("lambchopCooked");
/*      */   
/*  610 */   public static Item sinew = (new Item(918, Material.leather, "sinew")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("sinew").setCreativeTab(CreativeTabs.tabMaterials);
/*      */ 
/*      */   
/*  613 */   public static Item hatchetCopper = (new ItemHatchet(919, Material.copper)).setUnlocalizedName("hatchetCopper");
/*  614 */   public static Item hatchetSilver = (new ItemHatchet(920, Material.silver)).setUnlocalizedName("hatchetSilver");
/*  615 */   public static Item hatchetGold = (new ItemHatchet(921, Material.gold)).setUnlocalizedName("hatchetGold");
/*  616 */   public static Item hatchetIron = (new ItemHatchet(922, Material.iron)).setUnlocalizedName("hatchetIron");
/*  617 */   public static Item hatchetMithril = (new ItemHatchet(923, Material.mithril)).setUnlocalizedName("hatchetMithril");
/*  618 */   public static Item hatchetAdamantium = (new ItemHatchet(924, Material.adamantium)).setUnlocalizedName("hatchetAdamantium");
/*  619 */   public static Item hatchetRustedIron = (new ItemHatchet(925, Material.rusted_iron)).setUnlocalizedName("hatchetRustedIron");
/*  620 */   public static Item hatchetAncientMetal = (new ItemHatchet(1006, Material.ancient_metal)).setUnlocalizedName("hatchetAncientMetal");
/*      */   
/*  622 */   public static ItemShears shearsRustedIron = (ItemShears)(new ItemShears(926, Material.rusted_iron)).setUnlocalizedName("shearsRustedIron");
/*      */   
/*  624 */   public static Item cheese = (new ItemFood(927, Material.cheese, 3, 3, true, false, false, "cheese")).setAnimalProduct().setUnlocalizedName("cheese");
/*  625 */   public static Item flour = (new Item(928, Material.flour, "food/flour")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("flour").setCreativeTab(CreativeTabs.tabFood);
/*  626 */   public static ItemFood dough = (ItemFood)(new ItemFood(929, Material.dough, 6, 2, false, false, false, "dough")).setPlantProduct().setUnlocalizedName("dough");
/*  627 */   public static Item chocolate = (new ItemFood(930, Material.desert, 3, 3, 1000, false, false, false, "chocolate")).setPlantProduct().setUnlocalizedName("chocolate");
/*  628 */   public static Item onion = (new ItemSeedFood(931, 1, 1, false, true, Block.onions.blockID, Block.tilledField.blockID, "onion")).setUnlocalizedName("onion");
/*  629 */   public static ItemBowl bowlBeefStew = (ItemBowl)(new ItemBowl(932, Material.beef_stew, "beef_stew")).setFoodValue(16, 16, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beefStew");
/*  630 */   public static ItemBowl bowlChickenSoup = (ItemBowl)(new ItemBowl(933, Material.chicken_soup, "chicken_soup")).setFoodValue(10, 10, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("chickenSoup");
/*  631 */   public static ItemBowl bowlVegetableSoup = (ItemBowl)(new ItemBowl(934, Material.vegetable_soup, "vegetable_soup")).setFoodValue(6, 6, false, false, true).setPlantProduct().setUnlocalizedName("vegetableSoup");
/*      */   
/*  633 */   public static Item manure = (new ItemManure(935)).setUnlocalizedName("manure");
/*      */   
/*  635 */   public static ItemFishingRod fishingRodCopper = (ItemFishingRod)(new ItemFishingRod(936, Material.copper)).setUnlocalizedName("fishingRod");
/*  636 */   public static ItemFishingRod fishingRodSilver = (ItemFishingRod)(new ItemFishingRod(937, Material.silver)).setUnlocalizedName("fishingRod");
/*  637 */   public static ItemFishingRod fishingRodGold = (ItemFishingRod)(new ItemFishingRod(938, Material.gold)).setUnlocalizedName("fishingRod");
/*  638 */   public static ItemFishingRod fishingRodMithril = (ItemFishingRod)(new ItemFishingRod(939, Material.mithril)).setUnlocalizedName("fishingRod");
/*  639 */   public static ItemFishingRod fishingRodAdamantium = (ItemFishingRod)(new ItemFishingRod(940, Material.adamantium)).setUnlocalizedName("fishingRod");
/*  640 */   public static ItemFishingRod fishingRodAncientMetal = (ItemFishingRod)(new ItemFishingRod(1007, Material.ancient_metal)).setUnlocalizedName("fishingRod");
/*      */   
/*  642 */   public static Item carrotOnAStickCopper = (new ItemCarrotOnAStick(941, Material.copper)).setUnlocalizedName("carrotOnAStick");
/*  643 */   public static Item carrotOnAStickSilver = (new ItemCarrotOnAStick(942, Material.silver)).setUnlocalizedName("carrotOnAStick");
/*  644 */   public static Item carrotOnAStickGold = (new ItemCarrotOnAStick(943, Material.gold)).setUnlocalizedName("carrotOnAStick");
/*  645 */   public static Item carrotOnAStickMithril = (new ItemCarrotOnAStick(944, Material.mithril)).setUnlocalizedName("carrotOnAStick");
/*  646 */   public static Item carrotOnAStickAdamantium = (new ItemCarrotOnAStick(945, Material.adamantium)).setUnlocalizedName("carrotOnAStick");
/*  647 */   public static Item carrotOnAStickAncientMetal = (new ItemCarrotOnAStick(1008, Material.ancient_metal)).setUnlocalizedName("carrotOnAStick");
/*      */   
/*  649 */   public static Item shovelObsidian = (new ItemShovel(946, Material.obsidian)).setUnlocalizedName("shovelObsidian");
/*  650 */   public static Item knifeObsidian = (new ItemKnife(947, Material.obsidian)).setUnlocalizedName("knifeObsidian");
/*  651 */   public static Item hatchetObsidian = (new ItemHatchet(948, Material.obsidian)).setUnlocalizedName("hatchetObsidian");
/*  652 */   public static Item axeObsidian = (new ItemAxe(949, Material.obsidian)).setUnlocalizedName("axeObsidian");
/*      */   
/*  654 */   public static ItemBowl bowlIceCream = (ItemBowl)(new ItemBowl(950, Material.ice_cream, "ice_cream")).setFoodValue(5, 4, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("iceCream");
/*      */   
/*  656 */   public static ItemFishingRod fishingRodFlint = (ItemFishingRod)(new ItemFishingRod(951, Material.flint)).setUnlocalizedName("fishingRod");
/*  657 */   public static Item carrotOnAStickFlint = (new ItemCarrotOnAStick(952, Material.flint)).setUnlocalizedName("carrotOnAStick");
/*      */ 
/*      */   
/*  660 */   public static ItemBowl bowlSalad = (ItemBowl)(new ItemBowl(953, Material.salad, "bowl_salad")).setFoodValue(1, 1, false, false, true).setPlantProduct().setUnlocalizedName("salad");
/*      */   
/*  662 */   public static Item fragsCreeper = (new Item(954, Material.frags, "frag/creeper")).setUnlocalizedName("frags_creeper");
/*  663 */   public static Item fragsInfernalCreeper = (new Item(955, Material.frags, "frag/infernal_creeper")).setUnlocalizedName("frags_infernal_creeper");
/*      */   
/*  665 */   public static ItemFishingRod fishingRodObsidian = (ItemFishingRod)(new ItemFishingRod(956, Material.obsidian)).setUnlocalizedName("fishingRod");
/*  666 */   public static Item carrotOnAStickObsidian = (new ItemCarrotOnAStick(957, Material.obsidian)).setUnlocalizedName("carrotOnAStick");
/*      */ 
/*      */   
/*  669 */   public static Item bottleOfDisenchanting = (new ItemBottleOfDisenchanting(958)).setUnlocalizedName("bottleOfDisenchanting").setCreativeTab(CreativeTabs.tabMisc);
/*      */   
/*  671 */   public static ItemBowl bowlCreamOfMushroomSoup = (ItemBowl)(new ItemBowl(959, Material.cream_of_mushroom_soup, "cream_of_mushroom_soup")).setFoodValue(3, 5, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfMushroomSoup");
/*  672 */   public static ItemBowl bowlCreamOfVegetableSoup = (ItemBowl)(new ItemBowl(960, Material.cream_of_vegetable_soup, "cream_of_vegetable_soup")).setFoodValue(7, 7, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfVegetableSoup");
/*      */   
/*  674 */   public static ItemBowl bowlPumpkinSoup = (ItemBowl)(new ItemBowl(961, Material.pumpkin_soup, "pumpkin_soup")).setFoodValue(1, 2, false, false, true).setPlantProduct().setUnlocalizedName("pumpkinSoup");
/*      */   
/*  676 */   public static ItemFood orange = (ItemFood)(new ItemFood(962, Material.fruit, 2, 1, 1000, false, false, true, "orange")).setPlantProduct().setUnlocalizedName("orange");
/*  677 */   public static ItemFood banana = (ItemFood)(new ItemFood(963, Material.fruit, 2, 1, 1000, false, false, true, "banana")).setPlantProduct().setUnlocalizedName("banana");
/*      */   
/*  679 */   public static ItemCoin coinCopper = (ItemCoin)(new ItemCoin(964, Material.copper)).setUnlocalizedName("coinCopper");
/*  680 */   public static ItemCoin coinSilver = (ItemCoin)(new ItemCoin(965, Material.silver)).setUnlocalizedName("coinSilver");
/*  681 */   public static ItemCoin coinGold = (ItemCoin)(new ItemCoin(966, Material.gold)).setUnlocalizedName("coinGold");
/*      */   
/*  683 */   public static ItemBowl bowlMashedPotato = (ItemBowl)(new ItemBowl(967, Material.mashed_potato, "mashed_potato")).setFoodValue(12, 8, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("mashedPotato");
/*  684 */   public static ItemBowl bowlSorbet = (ItemBowl)(new ItemBowl(968, Material.sorbet, "sorbet")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("sorbet");
/*      */   
/*  686 */   public static ItemFood blueberries = (ItemFood)(new ItemFood(969, Material.fruit, 1, 1, 1000, false, false, true, "blueberries")).setPlantProduct().setUnlocalizedName("blueberries");
/*      */   
/*  688 */   public static ItemBowl bowlPorridge = (ItemBowl)(new ItemBowl(970, Material.porridge, "porridge")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("porridge");
/*  689 */   public static ItemBowl bowlCereal = (ItemBowl)(new ItemBowl(971, Material.cereal, "cereal")).setFoodValue(4, 2, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("cereal");
/*      */   
/*  691 */   public static Item referencedBook = (new ItemReferencedBook(972)).setUnlocalizedName("referencedBook");
/*      */   
/*  693 */   public static ItemMeat fishLargeRaw = (ItemMeat)(new ItemMeat(973, 5, 5, true, false, "fish_salmon_raw")).setUnlocalizedName("fishRaw");
/*  694 */   public static ItemMeat fishLargeCooked = (ItemMeat)(new ItemMeat(974, 10, 10, true, true, "fish_salmon_cooked")).setUnlocalizedName("fishCooked");
/*      */   
/*  696 */   public static ItemBucket bucketCopperStone = (ItemBucket)(new ItemBucket(975, Material.copper, Material.stone)).setUnlocalizedName("bucketCopperStone").setContainerItem(bucketCopperEmpty);
/*  697 */   public static ItemBucket bucketSilverStone = (ItemBucket)(new ItemBucket(976, Material.silver, Material.stone)).setUnlocalizedName("bucketSilverStone").setContainerItem(bucketSilverEmpty);
/*  698 */   public static ItemBucket bucketGoldStone = (ItemBucket)(new ItemBucket(977, Material.gold, Material.stone)).setUnlocalizedName("bucketGoldStone").setContainerItem(bucketGoldEmpty);
/*  699 */   public static ItemBucket bucketIronStone = (ItemBucket)(new ItemBucket(978, Material.iron, Material.stone)).setUnlocalizedName("bucketIronStone").setContainerItem(bucketIronEmpty);
/*  700 */   public static ItemBucket bucketMithrilStone = (ItemBucket)(new ItemBucket(979, Material.mithril, Material.stone)).setUnlocalizedName("bucketMithrilStone").setContainerItem(bucketMithrilEmpty);
/*  701 */   public static ItemBucket bucketAdamantiumStone = (ItemBucket)(new ItemBucket(980, Material.adamantium, Material.stone)).setUnlocalizedName("bucketAdamantiumStone").setContainerItem(bucketAdamantiumEmpty);
/*  702 */   public static ItemBucket bucketAncientMetalStone = (ItemBucket)(new ItemBucket(981, Material.ancient_metal, Material.stone)).setUnlocalizedName("bucketAncientMetalStone").setContainerItem(bucketAncientMetalEmpty);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  742 */   public static Item fragsNetherspawn = (new Item(1020, Material.frags, "frag/netherspawn")).setUnlocalizedName("frags_netherspawn");
/*      */   
/*  744 */   public static ItemCoin coinAncientMetal = (ItemCoin)(new ItemCoin(1021, Material.ancient_metal)).setUnlocalizedName("coinAncientMetal");
/*  745 */   public static ItemCoin coinMithril = (ItemCoin)(new ItemCoin(1022, Material.mithril)).setUnlocalizedName("coinMithril");
/*  746 */   public static ItemCoin coinAdamantium = (ItemCoin)(new ItemCoin(1023, Material.adamantium)).setUnlocalizedName("coinAdamantium");
/*      */   
/*  748 */   public static ItemMeat wormRaw = (ItemMeat)(new ItemMeat(1024, 0, 1, false, false, "worm_raw")).setUnlocalizedName("wormRaw");
/*  749 */   public static ItemMeat wormCooked = (ItemMeat)(new ItemMeat(1025, 1, 1, false, true, "worm_cooked")).setUnlocalizedName("wormCooked");
/*      */ 
/*      */ 
/*      */   
/*  753 */   public static Item thrownWeb = (new Item(1026, Material.web, "web")).setUnlocalizedName("web");
/*      */   
/*  755 */   public static Item genericFood = new ItemFood();
/*      */   
/*  757 */   public static Item record13 = (new ItemRecord(2000, "13", "record_13")).setUnlocalizedName("record");
/*  758 */   public static Item recordCat = (new ItemRecord(2001, "cat", "record_cat")).setUnlocalizedName("record");
/*  759 */   public static Item recordBlocks = (new ItemRecord(2002, "blocks", "record_blocks")).setUnlocalizedName("record");
/*  760 */   public static Item recordChirp = (new ItemRecord(2003, "chirp", "record_chirp")).setUnlocalizedName("record");
/*  761 */   public static Item recordFar = (new ItemRecord(2004, "far", "record_far")).setUnlocalizedName("record");
/*  762 */   public static Item recordMall = (new ItemRecord(2005, "mall", "record_mall")).setUnlocalizedName("record");
/*  763 */   public static Item recordMellohi = (new ItemRecord(2006, "mellohi", "record_mellohi")).setUnlocalizedName("record");
/*  764 */   public static Item recordStal = (new ItemRecord(2007, "stal", "record_stal")).setUnlocalizedName("record");
/*  765 */   public static Item recordStrad = (new ItemRecord(2008, "strad", "record_strad")).setUnlocalizedName("record");
/*  766 */   public static Item recordWard = (new ItemRecord(2009, "ward", "record_ward")).setUnlocalizedName("record");
/*  767 */   public static Item record11 = (new ItemRecord(2010, "11", "record_11")).setUnlocalizedName("record");
/*  768 */   public static Item recordWait = (new ItemRecord(2011, "wait", "record_wait")).setUnlocalizedName("record");
/*      */   
/*  770 */   public static Item recordUnderworld = (new ItemRecord(2020, "imported.underworld", "record_underworld", "Underworld", "The Fat Man")).setUnlocalizedName("record");
/*  771 */   public static Item recordDescent = (new ItemRecord(2021, "imported.descent", "record_descent", "Descent", "The Fat Man")).setUnlocalizedName("record");
/*  772 */   public static Item recordWanderer = (new ItemRecord(2022, "imported.wanderer", "record_wanderer", "Wanderer", "The Fat Man")).setUnlocalizedName("record");
/*  773 */   public static Item recordLegends = (new ItemRecord(2023, "imported.legends", "record_legends", "Legends", "The Fat Man")).setUnlocalizedName("record");
/*      */   public final int itemID;
/*      */   
/*      */   static {
/*  777 */     ItemFood.setCookingResult(porkRaw, porkCooked, 3);
/*  778 */     ItemFood.setCookingResult(fishRaw, fishCooked, 3);
/*  779 */     ItemFood.setCookingResult(beefRaw, beefCooked, 4);
/*  780 */     ItemFood.setCookingResult(chickenRaw, chickenCooked, 3);
/*  781 */     ItemFood.setCookingResult(lambchopRaw, lambchopCooked, 2);
/*  782 */     ItemFood.setCookingResult(fishLargeRaw, fishLargeCooked, 4);
/*      */     
/*  784 */     ItemFood.setCookingResult(potato, bakedPotato, 0);
/*  785 */     ItemFood.setCookingResult(dough, bread, 0);
/*      */     
/*  787 */     ItemFood.setCookingResult(wormRaw, wormCooked, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1810 */     StatList.initStats();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3094 */     for (int i = 0; i < itemsList.length; i++) {
/*      */       
/* 3096 */       Item item = getItem(i);
/*      */       
/* 3098 */       if (item != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3104 */         item.validate();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private int maxStackSize = 16;
/*      */   private int maxDamage;
/*      */   protected boolean bFull3D;
/*      */   private final int num_subtypes;
/*      */   private final boolean has_subtypes;
/*      */   private Item containerItem;
/*      */   private String potionEffect;
/*      */   private String unlocalizedName;
/*      */   protected Icon itemIcon;
/*      */   private String iconString;
/*      */   private float reach_bonus;
/*      */   protected List materials = new ArrayList();
/*      */   private float crafting_difficulty_as_component = -1.0F;
/*      */   private float lowest_crafting_difficulty_to_produce_override = Float.MAX_VALUE;
/*      */   public IRecipe[] recipes = new IRecipe[65];
/*      */   public static final int MIN_SIMILARITY = 0;
/*      */   public static final int MAX_SIMILARITY = 100;
/*      */   private int xp_reward;
/*      */   private int satiation;
/*      */   private int nutrition;
/*      */   private int sugar_content;
/*      */   private boolean has_protein;
/*      */   private boolean has_essential_fats;
/*      */   private boolean has_phytonutrients;
/*      */   private boolean is_animal_product;
/*      */   private boolean is_plant_product;
/*      */   private boolean alwaysEdible;
/*      */   public int num_recipes;
/*      */   private boolean is_crafting_product;
/*      */   private List crafting_products_this_is_component_of = new ArrayList();
/*      */   private int[] skillsets_that_can_repair_this;
/*      */   
/*      */   protected Item() {
/*      */     this.itemID = 0;
/*      */     this.num_subtypes = 0;
/*      */     this.has_subtypes = false;
/*      */   }
/*      */   
/*      */   protected Item(int id, String texture) {
/*      */     this(id, texture, -1);
/*      */   }
/*      */   
/*      */   protected Item(int par1, String texture, int num_subtypes) {
/*      */     this.itemID = 256 + par1;
/*      */     setTextureName(texture);
/*      */     if (itemsList[256 + par1] != null)
/*      */       System.out.println("CONFLICT @ " + par1); 
/*      */     itemsList[256 + par1] = this;
/*      */     if (isDamageable())
/*      */       setMaxStackSize(1); 
/*      */     if (this instanceof ItemMap) {
/*      */       num_subtypes = 32000;
/*      */     } else if (num_subtypes < 0) {
/*      */       num_subtypes = getSubItems().size();
/*      */     } 
/*      */     if (num_subtypes == 0)
/*      */       Debug.setErrorMessage("Item: subtype==0?"); 
/*      */     this.num_subtypes = (num_subtypes < 2) ? 0 : num_subtypes;
/*      */     this.has_subtypes = (this.num_subtypes > 0);
/*      */   }
/*      */   
/*      */   protected Item(int id, Material material, String texture) {
/*      */     this(id, texture);
/*      */     setMaterial(new Material[] { material });
/*      */   }
/*      */   
/*      */   protected Item(int id, Material[] material_array, String texture) {
/*      */     this(id, texture);
/*      */     setMaterial(material_array);
/*      */   }
/*      */   
/*      */   public Item setMaxStackSize(int par1) {
/*      */     this.maxStackSize = par1;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public int getSpriteNumber() {
/*      */     return 1;
/*      */   }
/*      */   
/*      */   public Icon getIconFromSubtype(int par1) {
/*      */     return this.itemIcon;
/*      */   }
/*      */   
/*      */   public final Icon getIconIndex(ItemStack par1ItemStack) {
/*      */     return getIconFromSubtype(par1ItemStack.getItemSubtype());
/*      */   }
/*      */   
/*      */   public float getStrVsBlock(Block block, int metadata) {
/*      */     return 0.0F;
/*      */   }
/*      */   
/*      */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
/*      */     if (player.onServer() && !player.inCreativeMode()) {
/*      */       Item item = getItemProducedOnItemUseFinish();
/*      */       player.convertOneOfHeldItem((item == null) ? null : new ItemStack(item));
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getItemStackLimit(int subtype, int damage) {
/*      */     return this.maxStackSize;
/*      */   }
/*      */   
/*      */   public int getMetadata(int par1) {
/*      */     return 0;
/*      */   }
/*      */   
/*      */   public final boolean getHasSubtypes() {
/*      */     return this.has_subtypes;
/*      */   }
/*      */   
/*      */   public final int getNumSubtypes() {
/*      */     return this.num_subtypes;
/*      */   }
/*      */   
/*      */   public final int getMaxDamage(EnumQuality quality) {
/*      */     if (!isDamageable()) {
/*      */       Minecraft.setErrorMessage("getMaxDamage: item is not damageable, " + this);
/*      */       return this.maxDamage;
/*      */     } 
/*      */     if (!hasQuality() || quality == null || quality == EnumQuality.average)
/*      */       return this.maxDamage; 
/*      */     return Math.max(Math.round(this.maxDamage * quality.getDurabilityModifier()), 1);
/*      */   }
/*      */   
/*      */   public int getMaxDamage(ItemStack item_stack) {
/*      */     if (!isDamageable())
/*      */       Minecraft.setErrorMessage("getMaxDamage: item is not damageable, " + this); 
/*      */     return (item_stack == null) ? this.maxDamage : getMaxDamage(item_stack.getQuality());
/*      */   }
/*      */   
/*      */   public final Item setMaxDamage(int par1) {
/*      */     if (par1 <= 0) {
/*      */       Minecraft.setErrorMessage("setMaxDamage: max_damage should be > 0 for " + this);
/*      */       return this;
/*      */     } 
/*      */     if (!isDamageable())
/*      */       Minecraft.setErrorMessage("setMaxDamage: called for non-damageable item " + this); 
/*      */     this.maxDamage = par1;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public final boolean isDamageable() {
/*      */     return this instanceof IDamageableItem;
/*      */   }
/*      */   
/*      */   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
/*      */     int chance_of_not_breaking;
/*      */     if (par2EntityLivingBase.onClient())
/*      */       Minecraft.setErrorMessage("ItemTool.hitEntity: called on client?"); 
/*      */     if (!par3EntityLivingBase.isEntityPlayer())
/*      */       return false; 
/*      */     EntityPlayer player = (EntityPlayer)par3EntityLivingBase;
/*      */     if (player.inCreativeMode())
/*      */       return false; 
/*      */     if (this == stick) {
/*      */       chance_of_not_breaking = 50;
/*      */     } else if (this == bone) {
/*      */       chance_of_not_breaking = 100;
/*      */     } else {
/*      */       return false;
/*      */     } 
/*      */     if (itemRand.nextInt(chance_of_not_breaking) == 0) {
/*      */       player.causeBreakingItemEffect(this, (player.getHeldItemStack() == par1ItemStack) ? player.inventory.currentItem : -1);
/*      */       player.convertOneOfHeldItem((ItemStack)null);
/*      */       return true;
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   public boolean onBlockDestroyed(BlockBreakInfo info) {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public Item setFull3D() {
/*      */     this.bFull3D = true;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public boolean isFull3D() {
/*      */     return this.bFull3D;
/*      */   }
/*      */   
/*      */   public boolean shouldRotateAroundWhenRendering() {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public Item setUnlocalizedName(String par1Str) {
/*      */     this.unlocalizedName = par1Str;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
/*      */     String var2 = getUnlocalizedName(par1ItemStack);
/*      */     return (var2 == null) ? "" : StatCollector.translateToLocal(var2);
/*      */   }
/*      */   
/*      */   public String getUnlocalizedName() {
/*      */     return "item." + this.unlocalizedName;
/*      */   }
/*      */   
/*      */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/*      */     return "item." + this.unlocalizedName;
/*      */   }
/*      */   
/*      */   public Item setContainerItem(Item par1Item) {
/*      */     this.containerItem = par1Item;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
/*      */     return true;
/*      */   }
/*      */   
/*      */   public boolean getShareTag() {
/*      */     return true;
/*      */   }
/*      */   
/*      */   public Item getContainerItem() {
/*      */     return this.containerItem;
/*      */   }
/*      */   
/*      */   public boolean hasContainerItem() {
/*      */     return (this.containerItem != null);
/*      */   }
/*      */   
/*      */   public String getStatName() {
/*      */     return StatCollector.translateToLocal(getUnlocalizedName() + ".name");
/*      */   }
/*      */   
/*      */   public String getItemStackDisplayName(ItemStack par1ItemStack) {
/*      */     return StatCollector.translateToLocal(getUnlocalizedName(par1ItemStack) + ".name");
/*      */   }
/*      */   
/*      */   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
/*      */     return 16777215;
/*      */   }
/*      */   
/*      */   public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {}
/*      */   
/*      */   public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {}
/*      */   
/*      */   public boolean isMap() {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public EnumItemInUseAction getItemInUseAction(ItemStack item_stack, EntityPlayer player) {
/*      */     if (isDrinkable(item_stack))
/*      */       return EnumItemInUseAction.DRINK; 
/*      */     if (isEatable(item_stack))
/*      */       return EnumItemInUseAction.EAT; 
/*      */     return null;
/*      */   }
/*      */   
/*      */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/*      */     return 0;
/*      */   }
/*      */   
/*      */   public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {}
/*      */   
/*      */   protected Item setPotionEffect(String par1Str) {
/*      */     this.potionEffect = par1Str;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public String getPotionEffect() {
/*      */     return this.potionEffect;
/*      */   }
/*      */   
/*      */   public boolean isPotionIngredient() {
/*      */     return (this.potionEffect != null);
/*      */   }
/*      */   
/*      */   public void addInformationBeforeEnchantments(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {}
/*      */   
/*      */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/*      */     if (extended_info) {
/*      */       int satiation = getSatiation(player);
/*      */       int nutrition = getNutrition();
/*      */       if (this.satiation > 0 || nutrition > 0) {
/*      */         info.add("");
/*      */         if (this instanceof ItemBlock) {
/*      */           ItemBlock item_block = (ItemBlock)this;
/*      */           if (item_block.getBlock() == Block.mushroomRed) {
/*      */             info.add(EnumChatFormatting.RED + Translator.getFormatted("item.tooltip.satiation", new Object[] { Integer.valueOf(satiation) }));
/*      */             info.add(EnumChatFormatting.RED + Translator.getFormatted("item.tooltip.nutrition", new Object[] { Integer.valueOf(nutrition) }));
/*      */             return;
/*      */           } 
/*      */         } 
/*      */         if (this.satiation > 0)
/*      */           info.add(((this.sugar_content > 0 && player.isInsulinResistant()) ? (String)player.getInsulinResistanceLevel().getColor() : (String)EnumChatFormatting.BROWN) + Translator.getFormatted("item.tooltip.satiation", new Object[] { Integer.valueOf(satiation) })); 
/*      */         if (nutrition > 0)
/*      */           info.add(EnumChatFormatting.BROWN + Translator.getFormatted("item.tooltip.nutrition", new Object[] { Integer.valueOf(nutrition) })); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean canBeRenamed() {
/*      */     return true;
/*      */   }
/*      */   
/*      */   public String getItemDisplayName(ItemStack par1ItemStack) {
/*      */     return ("" + StatCollector.translateToLocal(getUnlocalizedNameInefficiently(par1ItemStack) + ".name")).trim();
/*      */   }
/*      */   
/*      */   public String getItemDisplayName() {
/*      */     return getItemDisplayName(null);
/*      */   }
/*      */   
/*      */   public String toString() {
/*      */     return getItemDisplayName();
/*      */   }
/*      */   
/*      */   public final String getNameForReferenceFile(ItemStack item_stack) {
/*      */     String disambiguation = getNameDisambiguationForReferenceFile((item_stack == null) ? 0 : item_stack.getItemSubtype());
/*      */     if (disambiguation == null && item_stack != null)
/*      */       if (this instanceof ItemPotion) {
/*      */         ItemPotion item_potion = (ItemPotion)this;
/*      */         List<String> list = new ArrayList();
/*      */         addInformation(item_stack, null, list, false, null);
/*      */         if (list.size() > 0) {
/*      */           StringBuilder stringBuilder = new StringBuilder();
/*      */           if (ItemPotion.isSplash(item_stack.getItemSubtype()))
/*      */             stringBuilder.append("Splash "); 
/*      */           stringBuilder.append("Potion of ");
/*      */           disambiguation = StringUtils.stripControlCodes(list.get(0));
/*      */           return stringBuilder.append(disambiguation).toString();
/*      */         } 
/*      */       }  
/*      */     StringBuffer sb = new StringBuffer();
/*      */     sb.append(getItemDisplayName(item_stack));
/*      */     if (disambiguation != null)
/*      */       sb.append(" (" + disambiguation + ")"); 
/*      */     return sb.toString();
/*      */   }
/*      */   
/*      */   public final String getNameForReferenceFile() {
/*      */     return getNameForReferenceFile(null);
/*      */   }
/*      */   
/*      */   public String getNameDisambiguationForReferenceFile(int subtype) {
/*      */     if (this == clay)
/*      */       return "ball"; 
/*      */     return null;
/*      */   }
/*      */   
/*      */   public boolean hasEffect(ItemStack par1ItemStack) {
/*      */     return (this == bottleOfDisenchanting || par1ItemStack.isItemEnchanted());
/*      */   }
/*      */   
/*      */   public EnumRarity getRarity(ItemStack par1ItemStack) {
/*      */     return par1ItemStack.isItemEnchanted() ? EnumRarity.rare : EnumRarity.common;
/*      */   }
/*      */   
/*      */   public int getItemEnchantability() {
/*      */     return 0;
/*      */   }
/*      */   
/*      */   public boolean requiresMultipleRenderPasses() {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public Icon getIconFromSubtypeForRenderPass(int par1, int par2) {
/*      */     return getIconFromSubtype(par1);
/*      */   }
/*      */   
/*      */   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/*      */     par3List.add(new ItemStack(par1, 1, 0));
/*      */   }
/*      */   
/*      */   public List getSubItems() {
/*      */     List list = new ArrayList();
/*      */     getSubItems(this.itemID, null, list);
/*      */     return list;
/*      */   }
/*      */   
/*      */   public CreativeTabs getCreativeTab() {
/*      */     return this.tabToDisplayOn;
/*      */   }
/*      */   
/*      */   public Item setCreativeTab(CreativeTabs par1CreativeTabs) {
/*      */     this.tabToDisplayOn = par1CreativeTabs;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public boolean canItemEditBlocks() {
/*      */     return true;
/*      */   }
/*      */   
/*      */   public Material getMaterialForDurability() {
/*      */     return getExclusiveMaterial();
/*      */   }
/*      */   
/*      */   public Material getMaterialForRepairs() {
/*      */     return getMaterialForDurability();
/*      */   }
/*      */   
/*      */   public Item getRepairItem() {
/*      */     Material material_for_repairs = getMaterialForRepairs();
/*      */     if (material_for_repairs == Material.copper)
/*      */       return copperNugget; 
/*      */     if (material_for_repairs == Material.silver)
/*      */       return silverNugget; 
/*      */     if (material_for_repairs == Material.gold)
/*      */       return goldNugget; 
/*      */     if (material_for_repairs == Material.iron || material_for_repairs == Material.rusted_iron)
/*      */       return ironNugget; 
/*      */     if (material_for_repairs == Material.mithril)
/*      */       return mithrilNugget; 
/*      */     if (material_for_repairs == Material.adamantium)
/*      */       return adamantiumNugget; 
/*      */     if (material_for_repairs == Material.ancient_metal)
/*      */       return ancientMetalNugget; 
/*      */     return null;
/*      */   }
/*      */   
/*      */   public int getRepairCost() {
/*      */     return 0;
/*      */   }
/*      */   
/*      */   public boolean hasRepairCost() {
/*      */     return (getRepairCost() > 0);
/*      */   }
/*      */   
/*      */   public void registerIcons(IconRegister par1IconRegister) {
/*      */     this.itemIcon = par1IconRegister.registerIcon(getIconString());
/*      */   }
/*      */   
/*      */   public Multimap getItemAttributeModifiers() {
/*      */     return (Multimap)HashMultimap.create();
/*      */   }
/*      */   
/*      */   protected Item setTextureName(String par1Str) {
/*      */     this.iconString = par1Str;
/*      */     return this;
/*      */   }
/*      */   
/*      */   protected String getIconString() {
/*      */     return (this.iconString == null) ? ("MISSING_ICON_ITEM_" + this.itemID + "_" + this.unlocalizedName) : this.iconString;
/*      */   }
/*      */   
/*      */   public boolean isEffectiveAgainstBlock(Block block, int metadata) {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public float getReachBonus() {
/*      */     return this.reach_bonus;
/*      */   }
/*      */   
/*      */   public final float getReachBonus(Block block, int metadata) {
/*      */     if (isEffectiveAgainstBlock(block, metadata))
/*      */       return this.reach_bonus; 
/*      */     return 0.0F;
/*      */   }
/*      */   
/*      */   public float getReachBonus(Entity entity) {
/*      */     return 0.0F;
/*      */   }
/*      */   
/*      */   public Item setReachBonus(float reach_bonus) {
/*      */     this.reach_bonus = reach_bonus;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public Class[] spliceClassArrays(Class[] first, Class[] second) {
/*      */     Class[] spliced = new Class[first.length + second.length];
/*      */     int i;
/*      */     for (i = 0; i < first.length; i++)
/*      */       spliced[i] = first[i]; 
/*      */     for (i = 0; i < second.length; i++)
/*      */       spliced[first.length + i] = second[i]; 
/*      */     return spliced;
/*      */   }
/*      */   
/*      */   public boolean similarToItemsOfSameClass() {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public Class[] getSimilarClasses() {
/*      */     return new Class[0];
/*      */   }
/*      */   
/*      */   public int getSimilarityToItem(Item item) {
/*      */     if (item != null) {
/*      */       if (item == this || item.itemID == this.itemID)
/*      */         return 100; 
/*      */       if (item.getClass() == getClass() && similarToItemsOfSameClass())
/*      */         return 99; 
/*      */       Class[] similar_classes = getSimilarClasses();
/*      */       for (int i = 0; i < similar_classes.length; i++) {
/*      */         if (similar_classes[i] == item.getClass())
/*      */           return 100 - i + 2; 
/*      */       } 
/*      */     } 
/*      */     return 0;
/*      */   }
/*      */   
/*      */   public Item setCraftingDifficultyAsComponent(float difficulty) {
/*      */     this.crafting_difficulty_as_component = difficulty;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public boolean playerSwingsOnItemPlaced() {
/*      */     return true;
/*      */   }
/*      */   
/*      */   public boolean isEatable(int item_subtype) {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public final boolean isEatable(ItemStack item_stack) {
/*      */     return isEatable(item_stack.getItemSubtype());
/*      */   }
/*      */   
/*      */   public boolean isDrinkable(int item_subtype) {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public final boolean isDrinkable(ItemStack item_stack) {
/*      */     return isDrinkable(item_stack.getItemSubtype());
/*      */   }
/*      */   
/*      */   public final boolean isIngestable(ItemStack item_stack) {
/*      */     return isIngestable(item_stack.getItemSubtype());
/*      */   }
/*      */   
/*      */   public final boolean isIngestable(int item_subtype) {
/*      */     return (isEatable(item_subtype) || isDrinkable(item_subtype));
/*      */   }
/*      */   
/*      */   public static Item getItem(int item_id) {
/*      */     return (item_id > 0 && item_id < itemsList.length) ? itemsList[item_id] : null;
/*      */   }
/*      */   
/*      */   public static Item getItem(Block block) {
/*      */     return (block == null) ? null : getItem(block.blockID);
/*      */   }
/*      */   
/*      */   public Material getMaterialForEnchantment() {
/*      */     Material most_enchantable_material = this.materials.get(0);
/*      */     for (int i = 1; i < this.materials.size(); i++) {
/*      */       Material material = this.materials.get(i);
/*      */       if (material.enchantability > most_enchantable_material.enchantability)
/*      */         most_enchantable_material = material; 
/*      */     } 
/*      */     return most_enchantable_material;
/*      */   }
/*      */   
/*      */   public boolean hasMaterial(Material material, boolean exclusively) {
/*      */     if (this.materials.size() == 0)
/*      */       return false; 
/*      */     if (exclusively) {
/*      */       for (int j = 0; j < this.materials.size(); j++) {
/*      */         if (getMaterial(j) != material)
/*      */           return false; 
/*      */       } 
/*      */       return true;
/*      */     } 
/*      */     for (int i = 0; i < this.materials.size(); i++) {
/*      */       if (getMaterial(i) == material)
/*      */         return true; 
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   public boolean hasMaterial(Material material) {
/*      */     return hasMaterial(material, false);
/*      */   }
/*      */   
/*      */   public boolean hasMaterial(Material... materials) {
/*      */     for (int i = 0; i < materials.length; i++) {
/*      */       if (hasMaterial(materials[i]))
/*      */         return true; 
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   public boolean isCompletelyMetal() {
/*      */     if (this.materials.size() == 0) {
/*      */       Minecraft.setErrorMessage("isCompletelyMetal: no materials defined for " + this);
/*      */       return false;
/*      */     } 
/*      */     for (int i = 0; i < this.materials.size(); i++) {
/*      */       if (!getMaterial(i).isMetal())
/*      */         return false; 
/*      */     } 
/*      */     return true;
/*      */   }
/*      */   
/*      */   public Item setXPReward(int xp_reward) {
/*      */     this.xp_reward = xp_reward;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public int getExperienceReward(int subtype) {
/*      */     return this.xp_reward;
/*      */   }
/*      */   
/*      */   public Item setSatiation(int satiation) {
/*      */     this.satiation = satiation;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public Item setNutrition(int nutrition) {
/*      */     this.nutrition = nutrition;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public final Item setFoodValue(int satiation, int nutrition, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients) {
/*      */     return setFoodValue(satiation, nutrition, 0, has_protein, has_essential_fats, has_phytonutrients);
/*      */   }
/*      */   
/*      */   public final Item setFoodValue(int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients) {
/*      */     this.satiation = satiation;
/*      */     this.nutrition = nutrition;
/*      */     this.sugar_content = sugar_content;
/*      */     this.has_protein = has_protein;
/*      */     this.has_essential_fats = has_essential_fats;
/*      */     this.has_phytonutrients = has_phytonutrients;
/*      */     if (satiation > 0 || nutrition > 0)
/*      */       setCreativeTab(CreativeTabs.tabFood); 
/*      */     return this;
/*      */   }
/*      */   
/*      */   public final int getSatiation(EntityPlayer player) {
/*      */     if (player != null && !player.canMetabolizeFoodSugars() && this.sugar_content > 0) {
/*      */       if (this.sugar_content < 1000)
/*      */         return this.satiation - 1; 
/*      */       return this.satiation - this.sugar_content / 1000;
/*      */     } 
/*      */     return this.satiation;
/*      */   }
/*      */   
/*      */   public int getNutrition() {
/*      */     return this.nutrition;
/*      */   }
/*      */   
/*      */   public int getSugarContent() {
/*      */     return this.sugar_content;
/*      */   }
/*      */   
/*      */   public int getInsulinResponse() {
/*      */     return (int)(getSugarContent() * 4.8F);
/*      */   }
/*      */   
/*      */   public boolean hasProtein() {
/*      */     return this.has_protein;
/*      */   }
/*      */   
/*      */   public boolean hasEssentialFats() {
/*      */     return this.has_essential_fats;
/*      */   }
/*      */   
/*      */   public boolean hasPhytonutrients() {
/*      */     return this.has_phytonutrients;
/*      */   }
/*      */   
/*      */   public Item setAlwaysEdible() {
/*      */     this.alwaysEdible = true;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public boolean isAlwaysEdible() {
/*      */     return this.alwaysEdible;
/*      */   }
/*      */   
/*      */   public Item setAnimalProduct() {
/*      */     this.is_animal_product = true;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public boolean isAnimalProduct() {
/*      */     return this.is_animal_product;
/*      */   }
/*      */   
/*      */   public Item setPlantProduct() {
/*      */     this.is_plant_product = true;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public boolean isPlantProduct() {
/*      */     return this.is_plant_product;
/*      */   }
/*      */   
/*      */   public Item useVanillaTexture(String texture) {
/*      */     setTextureName(texture);
/*      */     return this;
/*      */   }
/*      */   
/*      */   public boolean hasQuality() {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public boolean isCraftingComponent(int subtype_or_0) {
/*      */     for (int i = 0; i < this.crafting_products_this_is_component_of.size(); i++) {
/*      */       ComponentOfCraftingProductEntry entry = this.crafting_products_this_is_component_of.get(i);
/*      */       if (entry.subtype_of_component_or_0 == -1 || entry.subtype_of_component_or_0 == subtype_or_0)
/*      */         return true; 
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   public Item setAsCraftingProduct() {
/*      */     this.is_crafting_product = true;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public boolean isCraftingProduct() {
/*      */     return this.is_crafting_product;
/*      */   }
/*      */   
/*      */   public boolean isRepairable() {
/*      */     return isDamageable();
/*      */   }
/*      */   
/*      */   public static Item getMatchingItem(Class<?> item_class, Material material) {
/*      */     Item matching_item = null;
/*      */     for (int i = 0; i < itemsList.length; i++) {
/*      */       Item item = getItem(i);
/*      */       if (item != null && item.getClass() == item_class && item.hasMaterial(material))
/*      */         if (matching_item == null) {
/*      */           matching_item = item;
/*      */         } else {
/*      */           Minecraft.setErrorMessage("getMatchingItem: more than one item matched " + item_class + ", " + material);
/*      */         }  
/*      */     } 
/*      */     return matching_item;
/*      */   }
/*      */   
/*      */   public Material getMaterial(int index) {
/*      */     return (index < this.materials.size()) ? this.materials.get(index) : null;
/*      */   }
/*      */   
/*      */   public EnumQuality getMaxQuality() {
/*      */     if (!hasQuality()) {
/*      */       Minecraft.setErrorMessage("getMaxQuality: item does not have quality (" + this + ")");
/*      */       return null;
/*      */     } 
/*      */     if (this.materials.size() == 0) {
/*      */       Minecraft.setErrorMessage("getMaxQuality: no materials defined for " + this);
/*      */       return null;
/*      */     } 
/*      */     if (this.materials.size() == 1)
/*      */       return getMaterial(0).getMaxQuality(); 
/*      */     EnumQuality lowest_max_quality = Material.getLowestMaxQualityOfMaterials(this.materials);
/*      */     if (lowest_max_quality.isLowerThan(EnumQuality.average))
/*      */       return lowest_max_quality; 
/*      */     return Material.getHighestMaxQualityOfMaterials(this.materials);
/*      */   }
/*      */   
/*      */   public EnumQuality getDefaultQuality() {
/*      */     return EnumQuality.getLowest(getMaxQuality(), EnumQuality.average);
/*      */   }
/*      */   
/*      */   public void setLowestCraftingDifficultyToProduce(float lowest_crafting_difficulty_to_produce_override) {
/*      */     this.lowest_crafting_difficulty_to_produce_override = lowest_crafting_difficulty_to_produce_override;
/*      */   }
/*      */   
/*      */   public float getLowestCraftingDifficultyToProduce() {
/*      */     if (this.lowest_crafting_difficulty_to_produce_override != Float.MAX_VALUE)
/*      */       return this.lowest_crafting_difficulty_to_produce_override; 
/*      */     float lowest_difficulty = Float.MAX_VALUE;
/*      */     for (int i = 0; i < this.num_recipes; i++) {
/*      */       IRecipe recipe = this.recipes[i];
/*      */       if (recipe.getIncludeInLowestCraftingDifficultyDetermination()) {
/*      */         float difficulty_per_unit = recipe.getUnmodifiedDifficulty() / (recipe.getRecipeOutput()).stackSize;
/*      */         if (difficulty_per_unit < lowest_difficulty)
/*      */           lowest_difficulty = difficulty_per_unit; 
/*      */       } 
/*      */     } 
/*      */     return lowest_difficulty;
/*      */   }
/*      */   
/*      */   public final void setAsComponentOfCraftingProduct(ItemStack crafting_product) {
/*      */     setAsComponentOfCraftingProduct(-1, crafting_product);
/*      */   }
/*      */   
/*      */   public final void setAsComponentOfCraftingProduct(int subtype_of_component, ItemStack crafting_product) {
/*      */     for (int i = 0; i < this.crafting_products_this_is_component_of.size(); i++) {
/*      */       ComponentOfCraftingProductEntry entry = this.crafting_products_this_is_component_of.get(i);
/*      */       if (entry.subtype_of_component_or_0 == -1 || entry.subtype_of_component_or_0 == subtype_of_component)
/*      */         if (ItemStack.areItemStacksEqual(entry.crafting_product, crafting_product, true, true, true, true))
/*      */           return;  
/*      */     } 
/*      */     this.crafting_products_this_is_component_of.add(new ComponentOfCraftingProductEntry(subtype_of_component, crafting_product));
/*      */   }
/*      */   
/*      */   public List getCraftingProductsThisIsComponentOf(int subtype_or_0) {
/*      */     List<ItemStack> crafting_products = new ArrayList();
/*      */     for (int i = 0; i < this.crafting_products_this_is_component_of.size(); i++) {
/*      */       ComponentOfCraftingProductEntry entry = this.crafting_products_this_is_component_of.get(i);
/*      */       if (entry.subtype_of_component_or_0 == -1 || entry.subtype_of_component_or_0 == subtype_or_0)
/*      */         crafting_products.add(entry.crafting_product); 
/*      */     } 
/*      */     return crafting_products;
/*      */   }
/*      */   
/*      */   public boolean doesSubtypeMatterForProduct(ItemStack crafting_product) {
/*      */     for (int i = 0; i < this.crafting_products_this_is_component_of.size(); i++) {
/*      */       ComponentOfCraftingProductEntry entry = this.crafting_products_this_is_component_of.get(i);
/*      */       if (ItemStack.areItemStacksEqual(entry.crafting_product, crafting_product, true, true, true, true))
/*      */         return (entry.subtype_of_component_or_0 != -1); 
/*      */     } 
/*      */     Minecraft.setErrorMessage("doesSubtypeMatterForProduct: " + this + " is not a crafting component of " + crafting_product.getNameForReferenceFile());
/*      */     return false;
/*      */   }
/*      */   
/*      */   public Item setMaterial(Material... materials) {
/*      */     this.materials.clear();
/*      */     if (materials == null || materials.length == 0)
/*      */       return this; 
/*      */     return addMaterial(materials);
/*      */   }
/*      */   
/*      */   public Item addMaterial(Material... materials) {
/*      */     for (int i = 0; i < materials.length; i++) {
/*      */       Material material = materials[i];
/*      */       if (material == null) {
/*      */         Minecraft.setErrorMessage("addMaterial: why adding null material to " + this + " [" + this.itemID + "]");
/*      */       } else if (hasMaterial(material)) {
/*      */         Minecraft.setErrorMessage("addMaterial: trying to add duplicate material " + material + " to " + this + " [" + this.itemID + "]");
/*      */       } else {
/*      */         this.materials.add(material);
/*      */       } 
/*      */     } 
/*      */     return this;
/*      */   }
/*      */   
/*      */   public int getBurnTime(ItemStack item_stack) {
/*      */     if (this == paper)
/*      */       return 25; 
/*      */     if (this == manure)
/*      */       return 100; 
/*      */     if (this == stick || this instanceof ItemArrow)
/*      */       return 100; 
/*      */     if (this == book || this == writableBook || this instanceof ItemEditableBook || this == enchantedBook)
/*      */       return 100; 
/*      */     if (this == doorWood)
/*      */       return 400; 
/*      */     if (this == blazeRod)
/*      */       return 2400; 
/*      */     if (hasMaterial(Material.wood))
/*      */       return 200; 
/*      */     if (hasMaterial(Material.paper))
/*      */       return 50; 
/*      */     return 0;
/*      */   }
/*      */   
/*      */   public int getHeatLevel(ItemStack item_stack) {
/*      */     if (this == blazeRod)
/*      */       return 4; 
/*      */     return (getBurnTime(item_stack) > 0) ? 1 : 0;
/*      */   }
/*      */   
/*      */   public boolean containsMetal() {
/*      */     return Material.doesMaterialListContainMetal(this.materials);
/*      */   }
/*      */   
/*      */   public boolean containsRockyMineral() {
/*      */     return Material.doesMaterialListContainRockyMineral(this.materials);
/*      */   }
/*      */   
/*      */   public boolean containsCrystal() {
/*      */     return Material.doesMaterialListContainCrystal(this.materials);
/*      */   }
/*      */   
/*      */   public boolean canDouseFire() {
/*      */     return Material.doesMaterialListContainMaterialThatCanDouseFire(this.materials);
/*      */   }
/*      */   
/*      */   public boolean canCatchFire() {
/*      */     return Material.doesMaterialListContainMaterialThatCanCatchFire(this.materials);
/*      */   }
/*      */   
/*      */   public boolean canBurnAsFuelSource() {
/*      */     return Material.doesMaterialListContainMaterialThatCanBurnAsFuelSource(this.materials);
/*      */   }
/*      */   
/*      */   public boolean isHarmedByFire() {
/*      */     return Material.doesMaterialListContainMaterialThatIsHarmedByFire(this.materials);
/*      */   }
/*      */   
/*      */   public boolean isHarmedByLava() {
/*      */     return Material.doesMaterialListContainMaterialThatIsHarmedByLava(this.materials);
/*      */   }
/*      */   
/*      */   public static void verifyThatAllItemsHaveMaterialsDefined() {
/*      */     for (int i = 0; i < itemsList.length; i++) {
/*      */       Item item = getItem(i);
/*      */       if (item != null && item.materials.size() == 0)
/*      */         Minecraft.setErrorMessage("Warning: No materials defined for " + item); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Material getExclusiveMaterial() {
/*      */     if (this.materials.size() == 0) {
/*      */       Minecraft.setErrorMessage("getExclusiveMaterial: no material defined for " + this);
/*      */       return null;
/*      */     } 
/*      */     if (this.materials.size() > 1) {
/*      */       Minecraft.setErrorMessage("getExclusiveMaterial: multiple materials defined for " + this);
/*      */       return null;
/*      */     } 
/*      */     return getMaterial(0);
/*      */   }
/*      */   
/*      */   public final boolean isBlock() {
/*      */     return this instanceof ItemBlock;
/*      */   }
/*      */   
/*      */   public final boolean isArmor() {
/*      */     return this instanceof ItemArmor;
/*      */   }
/*      */   
/*      */   public boolean isChainMail() {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public boolean hasBreakingEffect() {
/*      */     return true;
/*      */   }
/*      */   
/*      */   public boolean hasCraftingEffect() {
/*      */     return hasBreakingEffect();
/*      */   }
/*      */   
/*      */   public boolean isTool() {
/*      */     return this instanceof ItemTool;
/*      */   }
/*      */   
/*      */   public Item getItemProducedOnItemUseFinish() {
/*      */     return null;
/*      */   }
/*      */   
/*      */   public ItemStack getItemProducedWhenDestroyed(ItemStack item_stack, DamageSource damage_source) {
/*      */     return null;
/*      */   }
/*      */   
/*      */   public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
/*      */     return true;
/*      */   }
/*      */   
/*      */   public boolean tryPlaceAsBlock(RaycastCollision rc, Block block, EntityPlayer player, ItemStack item_stack) {
/*      */     if (!rc.isBlock()) {
/*      */       Minecraft.setErrorMessage("tryPlaceAsBlock: raycast collision is not block");
/*      */       return false;
/*      */     } 
/*      */     World world = rc.world;
/*      */     EnumFace face = rc.face_hit;
/*      */     int metadata_if_replacing_block = block.getMetadataForPlacement(player.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, item_stack, player, EnumFace.TOP, 0.0F, 0.0F, 0.0F);
/*      */     if (block.canReplaceBlock(metadata_if_replacing_block, rc.getBlockHit(), rc.block_hit_metadata)) {
/*      */       int i = rc.block_hit_x;
/*      */       int j = rc.block_hit_y;
/*      */       int k = rc.block_hit_z;
/*      */       if (world.isWithinTournamentSafeZone(i, j, k))
/*      */         return false; 
/*      */       if (BlockSandStone.isSacredSandstone(block, item_stack.getItemSubtype()) && !world.getBiomeGenForCoords(i, k).isDesertBiome())
/*      */         return false; 
/*      */       if (rc.getBlockHit() instanceof BlockMounted) {
/*      */         BlockMounted block_mounted = (BlockMounted)rc.getBlockHit();
/*      */         face = block_mounted.getFaceMountedTo(rc.block_hit_metadata);
/*      */       } else if (rc.getBlockHit() instanceof BlockVine && BlockVine.getNumVines(rc.block_hit_metadata) == 1) {
/*      */         if (rc.block_hit_metadata == 1) {
/*      */           face = EnumFace.NORTH;
/*      */         } else if (rc.block_hit_metadata == 2) {
/*      */           face = EnumFace.EAST;
/*      */         } else if (rc.block_hit_metadata == 4) {
/*      */           face = EnumFace.SOUTH;
/*      */         } else if (rc.block_hit_metadata == 8) {
/*      */           face = EnumFace.WEST;
/*      */         } else {
/*      */           return false;
/*      */         } 
/*      */         if (world.getNeighborBlock(i, j, k, face.getOpposite()) == null)
/*      */           return false; 
/*      */       } else {
/*      */         face = EnumFace.TOP;
/*      */       } 
/*      */       if (face == EnumFace.TOP && world.getBlock(i, j - 1, k) == null) {
/*      */         Minecraft.setErrorMessage("tryPlaceAsBlock: replacing block clicked but it doesn't have a block beneath it, don't know how to handle");
/*      */         return false;
/*      */       } 
/*      */       rc = null;
/*      */       return block.tryPlaceFromHeldItem(i, j, k, face, item_stack, player, 0.0F, 0.0F, 0.0F, true, true);
/*      */     } 
/*      */     int x = rc.neighbor_block_x;
/*      */     int y = rc.neighbor_block_y;
/*      */     int z = rc.neighbor_block_z;
/*      */     if (world.isWithinTournamentSafeZone(x, y, z))
/*      */       return false; 
/*      */     if (BlockSandStone.isSacredSandstone(block, item_stack.getItemSubtype()) && !world.getBiomeGenForCoords(x, z).isDesertBiome())
/*      */       return false; 
/*      */     if (rc.getBlockHit() == Block.vine && block == Block.vine)
/*      */       return block.tryPlaceFromHeldItem(rc.block_hit_x, rc.block_hit_y - 1, rc.block_hit_z, EnumFace.BOTTOM, item_stack, player, 0.0F, 0.0F, 0.0F, true, true); 
/*      */     return block.tryPlaceFromHeldItem(x, y, z, face, item_stack, player, rc.block_hit_offset_x, rc.block_hit_offset_y, rc.block_hit_offset_z, true, true);
/*      */   }
/*      */   
/*      */   public boolean isDissolvedByWater() {
/*      */     return Material.doesMaterialListContainMaterialThatDissolvesInWater(this.materials);
/*      */   }
/*      */   
/*      */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
/*      */     return false;
/*      */   }
/*      */   
/*      */   public boolean preventsHandDamage() {
/*      */     return (this instanceof ItemTool || this == stick || this == bone);
/*      */   }
/*      */   
/*      */   public int getProtein() {
/*      */     return hasProtein() ? (getNutrition() * 8000) : 0;
/*      */   }
/*      */   
/*      */   public int getEssentialFats() {
/*      */     return hasEssentialFats() ? (getNutrition() * 8000) : 0;
/*      */   }
/*      */   
/*      */   public int getPhytonutrients() {
/*      */     return hasPhytonutrients() ? (getNutrition() * 8000) : 0;
/*      */   }
/*      */   
/*      */   public static float getChanceOfSnowAndIceItemsMelting(float biome_temperature) {
/*      */     return (biome_temperature - 1.0F) * 1.0E-4F;
/*      */   }
/*      */   
/*      */   public Item setSkillsetsThatCanRepairThis(int[] skillsets) {
/*      */     this.skillsets_that_can_repair_this = skillsets;
/*      */     return this;
/*      */   }
/*      */   
/*      */   public Item setSkillsetThatCanRepairThis(int skillset) {
/*      */     (new int[1])[0] = skillset;
/*      */     this.skillsets_that_can_repair_this = (skillset == -1) ? null : new int[1];
/*      */     return this;
/*      */   }
/*      */   
/*      */   public int[] getSkillsetsThatCanRepairThis() {
/*      */     return this.skillsets_that_can_repair_this;
/*      */   }
/*      */   
/*      */   public final ItemTool getAsTool() {
/*      */     return (ItemTool)this;
/*      */   }
/*      */   
/*      */   public final ItemArmor getAsArmor() {
/*      */     return (ItemArmor)this;
/*      */   }
/*      */   
/*      */   public final ItemBlock getAsItemBlock() {
/*      */     return (ItemBlock)this;
/*      */   }
/*      */   
/*      */   public float getCraftingDifficultyAsComponent(ItemStack item_stack) {
/*      */     return this.crafting_difficulty_as_component;
/*      */   }
/*      */   
/*      */   public ItemStack getItemStackForStatsIcon() {
/*      */     return new ItemStack(this);
/*      */   }
/*      */   
/*      */   public ItemFood getAsItemFood() {
/*      */     return (ItemFood)this;
/*      */   }
/*      */   
/*      */   public int getScaledDamage(float damage) {
/*      */     if (this instanceof ItemArmor) {
/*      */       damage *= 100.0F * plateIron.getMaxDamage(EnumQuality.average) / swordIron.getMaxDamage(EnumQuality.average);
/*      */     } else if (this instanceof ItemTool) {
/*      */       damage *= 100.0F;
/*      */     } 
/*      */     return (int)damage;
/*      */   }
/*      */   
/*      */   public float getMeleeDamageBonus() {
/*      */     return 0.0F;
/*      */   }
/*      */   
/*      */   public boolean isHarmedByPepsin() {
/*      */     return Material.doesMaterialListContainMaterialThatIsHarmedByPepsin(this.materials);
/*      */   }
/*      */   
/*      */   public boolean isHarmedByAcid() {
/*      */     return Material.doesMaterialListContainMaterialThatIsHarmedByAcid(this.materials);
/*      */   }
/*      */   
/*      */   public boolean isHarmedBy(DamageSource damage_source) {
/*      */     if (damage_source.isFireDamage())
/*      */       return isHarmedByFire(); 
/*      */     if (damage_source.isLavaDamage())
/*      */       return isHarmedByLava(); 
/*      */     if (damage_source.isPepsinDamage())
/*      */       return isHarmedByPepsin(); 
/*      */     if (damage_source.isAcidDamage())
/*      */       return isHarmedByAcid(); 
/*      */     return true;
/*      */   }
/*      */   
/*      */   public IBehaviorDispenseItem getDispenserBehavior() {
/*      */     return null;
/*      */   }
/*      */   
/*      */   public Material getHardestMetalMaterial() {
/*      */     if (this.materials == null || this.materials.size() == 0)
/*      */       return null; 
/*      */     if (this.materials.size() == 1) {
/*      */       Material material = this.materials.get(0);
/*      */       return material.isMetal() ? material : null;
/*      */     } 
/*      */     Material hardest_metal = null;
/*      */     float highest_metal_durability = 0.0F;
/*      */     Iterator<Material> i = this.materials.iterator();
/*      */     while (i.hasNext()) {
/*      */       Material material = i.next();
/*      */       if (material.isMetal() && (hardest_metal == null || material.durability > highest_metal_durability)) {
/*      */         hardest_metal = material;
/*      */         highest_metal_durability = material.durability;
/*      */       } 
/*      */     } 
/*      */     return hardest_metal;
/*      */   }
/*      */   
/*      */   public final boolean canBeCompostedByWorms(ItemStack item_stack) {
/*      */     return (getCompostingValue() > 0.0F);
/*      */   }
/*      */   
/*      */   public float getCompostingValue() {
/*      */     if (this == flour)
/*      */       return 0.8F; 
/*      */     if (this == wheat)
/*      */       return 0.5F; 
/*      */     if (this == paper)
/*      */       return 0.1F; 
/*      */     return (getSatiation(null) + getNutrition()) * 0.1F;
/*      */   }
/*      */   
/*      */   public Item getCompostingRemains(ItemStack item_stack) {
/*      */     return null;
/*      */   }
/*      */   
/*      */   public void validate() {
/*      */     if (this instanceof IDamageableItem && this.maxDamage < 1)
/*      */       Minecraft.setErrorMessage("Item: " + this + " is damageable but has maxDamage of " + this.maxDamage); 
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Item.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */