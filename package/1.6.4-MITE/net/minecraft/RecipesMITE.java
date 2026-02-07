/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipesMITE
/*     */ {
/*   7 */   private static final Object[][] nugget_based_items = new Object[][] { { Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget, Item.mithrilNugget, Item.adamantiumNugget, Item.ancientMetalNugget, new Object[0] }, { Item.chainCopper, Item.chainSilver, Item.chainGold, Item.chainIron, Item.chainMithril, Item.chainAdamantium, Item.chainAncientMetal, { " ? ", "? ?", " ? " } } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  13 */   private static final Object[][] plank_or_ingot_based_items = new Object[][] { { Block.planks, Item.ingotCopper, Item.ingotSilver, Item.ingotGold, Item.ingotIron, Item.ingotMithril, Item.ingotAdamantium, Item.ingotAncientMetal, { Character.valueOf('/'), Item.stick } }, { Item.doorWood, Item.doorCopper, Item.doorSilver, Item.doorGold, Item.doorIron, Item.doorMithril, Item.doorAdamantium, Item.doorAncientMetal, { "??", "??", "??" } }, { Block.chest, Block.chestCopper, Block.chestSilver, Block.chestGold, Block.chestIron, Block.chestMithril, Block.chestAdamantium, Block.chestAncientMetal, { "???", "? ?", "???" } } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  20 */   private static final Object[][] chain_based_items = new Object[][] { { Item.chainCopper, Item.chainSilver, Item.chainGold, Item.chainIron, Item.chainMithril, Item.chainAdamantium, Item.chainAncientMetal, null }, { Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget, Item.mithrilNugget, Item.adamantiumNugget, Item.ancientMetalNugget, { "?" }, Integer.valueOf(4) } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   private static final Object[][] arrow_dismantling_based_items = new Object[][] { { Item.arrowFlint, Item.arrowObsidian, Item.arrowCopper, Item.arrowSilver, Item.arrowGold, Item.arrowIron, Item.arrowMithril, Item.arrowAdamantium, Item.arrowAncientMetal }, { Item.chipFlint, Item.shardObsidian, Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget, Item.mithrilNugget, Item.adamantiumNugget, Item.ancientMetalNugget, null } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   private static final Object[][] shard_or_nugget_based_items = new Object[][] { { Item.chipFlint, Item.shardObsidian, Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget, Item.mithrilNugget, Item.adamantiumNugget, Item.ancientMetalNugget, { Character.valueOf('/'), Item.stick, Character.valueOf('|'), Item.silk } }, { Item.fishingRodFlint, Item.fishingRodObsidian, Item.fishingRodCopper, Item.fishingRodSilver, Item.fishingRodGold, Item.fishingRodIron, Item.fishingRodMithril, Item.fishingRodAdamantium, Item.fishingRodAncientMetal, { "  /", " /|", "/?|" } } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   private static final Object[][] fishing_rod_based_items = new Object[][] { { Item.fishingRodFlint, Item.fishingRodObsidian, Item.fishingRodCopper, Item.fishingRodSilver, Item.fishingRodGold, Item.fishingRodIron, Item.fishingRodMithril, Item.fishingRodAdamantium, Item.fishingRodAncientMetal }, { Item.carrotOnAStickFlint, Item.carrotOnAStickObsidian, Item.carrotOnAStickCopper, Item.carrotOnAStickSilver, Item.carrotOnAStickGold, Item.carrotOnAStickIron, Item.carrotOnAStickMithril, Item.carrotOnAStickAdamantium, Item.carrotOnAStickAncientMetal, { Item.carrot }, Integer.valueOf(1), Integer.valueOf(40) } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private static final Object[][] carrot_on_a_stick_dismantling_based_items = new Object[][] { { Item.carrotOnAStickFlint, Item.carrotOnAStickObsidian, Item.carrotOnAStickCopper, Item.carrotOnAStickSilver, Item.carrotOnAStickGold, Item.carrotOnAStickIron, Item.carrotOnAStickMithril, Item.carrotOnAStickAdamantium, Item.carrotOnAStickAncientMetal }, { Item.fishingRodFlint, Item.fishingRodObsidian, Item.fishingRodCopper, Item.fishingRodSilver, Item.fishingRodGold, Item.fishingRodIron, Item.fishingRodMithril, Item.fishingRodAdamantium, Item.fishingRodAncientMetal, null, Integer.valueOf(1), Integer.valueOf(40) } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   private static final Object[][] ingot_based_items = new Object[][] { { Item.ingotCopper, Item.ingotSilver, Item.ingotGold, Item.ingotIron, Item.ingotMithril, Item.ingotAdamantium, Item.ingotAncientMetal, null }, { Item.bucketCopperEmpty, Item.bucketSilverEmpty, Item.bucketGoldEmpty, Item.bucketIronEmpty, Item.bucketMithrilEmpty, Item.bucketAdamantiumEmpty, Item.bucketAncientMetalEmpty, { "? ?", " ? " } }, { Block.fenceCopper, Block.fenceSilver, Block.fenceGold, Block.fenceIron, Block.fenceMithril, Block.fenceAdamantium, Block.fenceAncientMetal, { "???", "???" }, Integer.valueOf(16) } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   private static final Object[][] bucket_of_stone_dismantling_based_items = new Object[][] { { Item.bucketCopperStone, Item.bucketSilverStone, Item.bucketGoldStone, Item.bucketIronStone, Item.bucketMithrilStone, Item.bucketAdamantiumStone, Item.bucketAncientMetalStone }, { Item.bucketCopperEmpty, Item.bucketSilverEmpty, Item.bucketGoldEmpty, Item.bucketIronEmpty, Item.bucketMithrilEmpty, Item.bucketAdamantiumEmpty, Item.bucketAncientMetalEmpty, null, Integer.valueOf(1), Integer.valueOf(100) } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addCraftingRecipes(CraftingManager crafting_manager) {
/*  65 */     crafting_manager.addShapelessRecipe(new ItemStack(Block.sandStone, 1, 3), new Object[] { new ItemStack(Block.sandStone.blockID, 1, 0), Item.goldNugget }).scaleDifficulty(8.0F).setSkillset(Skill.FINE_ARTS.id);
/*  66 */     crafting_manager.addShapelessRecipe(new ItemStack(Block.sandStone, 1, 3), new Object[] { new ItemStack(Block.sandStone.blockID, 1, 2), Item.goldNugget }).scaleDifficulty(4.0F).setSkillset(Skill.FINE_ARTS.id);
/*     */     
/*  68 */     crafting_manager.addRecipe(new ItemStack(Block.obsidianSingleSlab, 6), new Object[] { "###", Character.valueOf('#'), Block.obsidian }).setSkillset(Skill.MASONRY.id);
/*     */     
/*  70 */     crafting_manager.addRecipe(new ItemStack(Block.stone, 2), new Object[] { "##", "##", Character.valueOf('#'), Block.cobblestone }).setSkillset(Skill.MASONRY.id);
/*     */     
/*  72 */     crafting_manager.addRecipe(new ItemStack(Block.glass, 1), new Object[] { "###", "###", Character.valueOf('#'), Block.thinGlass }).setSkillset(Skill.MASONRY.id);
/*     */     
/*  74 */     crafting_manager.addRecipe(new ItemStack(Item.saddle, 1), new Object[] { "LLL", "L L", "i i", Character.valueOf('L'), Item.leather, Character.valueOf('i'), Item.ironNugget }).setSkillset(Skill.FINE_ARTS.id);
/*     */     
/*  76 */     crafting_manager.addRecipe(new ItemStack(Item.snowball, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.snow, 1, 0) });
/*  77 */     crafting_manager.addRecipe(new ItemStack(Block.snow, 1), new Object[] { "#", Character.valueOf('#'), Item.snowball });
/*  78 */     crafting_manager.addRecipe(new ItemStack(Block.snow, 4), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.snow, 1, 3) });
/*     */     
/*  80 */     crafting_manager.addRecipe(new ItemStack(Block.snow, 2, 3), new Object[] { "#", Character.valueOf('#'), Block.blockSnow });
/*  81 */     crafting_manager.addRecipe(new ItemStack(Block.snow, 1, 3), new Object[] { "##", "##", Character.valueOf('#'), Item.snowball });
/*  82 */     crafting_manager.addRecipe(new ItemStack(Block.snow, 1, 3), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(Block.snow, 1, 0) });
/*  83 */     crafting_manager.addRecipe(new ItemStack(Block.blockSnow, 1), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack(Block.snow, 1, 3) });
/*     */     
/*  85 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.eyeOfEnder, 1), new Object[] { Item.enderPearl, Item.blazePowder }).setSkillset(Skill.FINE_ARTS.id);
/*  86 */     crafting_manager.addRecipe(new ItemStack(Block.enderChest), new Object[] { "###", "#E#", "###", Character.valueOf('#'), Block.obsidian, Character.valueOf('E'), Item.eyeOfEnder }).setSkillset(Skill.MASONRY.id);
/*     */     
/*  88 */     crafting_manager.addRecipes(plank_or_ingot_based_items, -1);
/*  89 */     crafting_manager.addRecipes(nugget_based_items, -1);
/*  90 */     crafting_manager.addRecipes(chain_based_items, 0);
/*  91 */     crafting_manager.addShapelessRecipes(arrow_dismantling_based_items, 0, false, false);
/*  92 */     crafting_manager.addRecipes(shard_or_nugget_based_items, Skill.FISHING.id);
/*  93 */     crafting_manager.addShapelessRecipes(fishing_rod_based_items, 0, true);
/*  94 */     crafting_manager.addShapelessRecipes(carrot_on_a_stick_dismantling_based_items, 0, true, false);
/*     */     
/*  96 */     crafting_manager.addRecipe(new ItemStack(Item.seeds, 2), new Object[] { "#", Character.valueOf('#'), Item.wheat });
/*     */     
/*  98 */     crafting_manager.addRecipe(new ItemStack(Item.sinew, 4), new Object[] { "#", Character.valueOf('#'), Item.leather }).setDifficulty(50.0F);
/*     */     
/* 100 */     crafting_manager.addRecipes(ingot_based_items, -1);
/*     */     
/* 102 */     crafting_manager.addRecipe(new ItemStack(Block.furnaceClayIdle, 1), new Object[] { "##", "##", Character.valueOf('#'), Block.blockClay });
/* 103 */     crafting_manager.addRecipe(new ItemStack(Block.furnaceHardenedClayIdle, 1), new Object[] { "###", "# #", "###", Character.valueOf('#'), Block.hardenedClay }).setSkillset(Skill.MASONRY.id);
/* 104 */     crafting_manager.addRecipe(new ItemStack(Block.furnaceSandstoneIdle, 1), new Object[] { "###", "# #", "###", Character.valueOf('#'), Block.sandStone }).setSkillset(Skill.MASONRY.id);
/* 105 */     crafting_manager.addRecipe(new ItemStack(Block.furnaceObsidianIdle, 1), new Object[] { "###", "# #", "###", Character.valueOf('#'), Block.obsidian }).setSkillset(Skill.MASONRY.id);
/* 106 */     crafting_manager.addRecipe(new ItemStack(Block.furnaceNetherrackIdle, 1), new Object[] { "###", "# #", "###", Character.valueOf('#'), Block.netherrack }).setSkillset(Skill.MASONRY.id);
/*     */     
/* 108 */     crafting_manager.addRecipe(new ItemStack(Block.anvilCopper, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Block.blockCopper, Character.valueOf('i'), Item.ingotCopper }).setSkillset(Skill.BLACKSMITHING.id);
/* 109 */     crafting_manager.addRecipe(new ItemStack(Block.anvilSilver, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Block.blockSilver, Character.valueOf('i'), Item.ingotSilver }).setSkillset(Skill.BLACKSMITHING.id);
/* 110 */     crafting_manager.addRecipe(new ItemStack(Block.anvilGold, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Block.blockGold, Character.valueOf('i'), Item.ingotGold }).setSkillset(Skill.BLACKSMITHING.id);
/* 111 */     crafting_manager.addRecipe(new ItemStack(Block.anvilMithril, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Block.blockMithril, Character.valueOf('i'), Item.ingotMithril }).setSkillset(Skill.BLACKSMITHING.id);
/* 112 */     crafting_manager.addRecipe(new ItemStack(Block.anvilAdamantium, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Block.blockAdamantium, Character.valueOf('i'), Item.ingotAdamantium }).setSkillset(Skill.BLACKSMITHING.id);
/* 113 */     crafting_manager.addRecipe(new ItemStack(Block.anvilAncientMetal, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Block.blockAncientMetal, Character.valueOf('i'), Item.ingotAncientMetal }).setSkillset(Skill.BLACKSMITHING.id);
/*     */     
/* 115 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.cake), new Object[] { Item.flour, Item.sugar, Item.egg, Item.bowlMilk }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */     
/* 117 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.cheese, 1), new Object[] { new ItemStack(Item.bowlMilk, 4) }).setDifficulty(6400).setSkillset(Skill.BREWING.id);
/* 118 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.cheese, 2), new Object[] { new ItemStack(Item.bowlMilk, 8) }).setDifficulty(6400).setSkillset(Skill.BREWING.id);
/* 119 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.chocolate, 1), new Object[] { new ItemStack(Item.dyePowder, 1, 3), Item.sugar }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */     
/* 121 */     crafting_manager.addRecipe(new ItemStack(Block.runestoneMithril, 1), new Object[] { " n ", "n#n", " n ", Character.valueOf('#'), Block.obsidian, Character.valueOf('n'), Item.mithrilNugget }).setSkillset(Skill.MASONRY.id + Skill.FINE_ARTS.id);
/* 122 */     crafting_manager.addRecipe(new ItemStack(Block.runestoneAdamantium, 1), new Object[] { " n ", "n#n", " n ", Character.valueOf('#'), Block.obsidian, Character.valueOf('n'), Item.adamantiumNugget }).setSkillset(Skill.MASONRY.id + Skill.FINE_ARTS.id);
/*     */     
/* 124 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.appleGold, 1, 1), new Object[] { Item.appleGold, Item.expBottle }).setSkillset(Skill.FINE_ARTS.id);
/* 125 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bottleOfDisenchanting, 1), new Object[] { new ItemStack(Item.potion, 1, 0), Item.netherStalkSeeds, new ItemStack(Item.coal, 1, 0) });
/*     */     
/* 127 */     ItemBucketMilk[] milk_buckets = { Item.bucketCopperMilk, Item.bucketSilverMilk, Item.bucketGoldMilk, Item.bucketIronMilk, Item.bucketAncientMetalMilk, Item.bucketMithrilMilk, Item.bucketAdamantiumMilk };
/*     */     
/* 129 */     for (int i = 0; i < milk_buckets.length; i++) {
/*     */       
/* 131 */       crafting_manager.addShapelessRecipe(new ItemStack(Item.cake), false, new Object[] { Item.flour, Item.sugar, Item.egg, milk_buckets[i] }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */       int m;
/* 133 */       for (m = 1; m <= 9; m++) {
/* 134 */         crafting_manager.addShapelessRecipe(new ItemStack(Item.cheese, m), new Object[] { new ItemStack(milk_buckets[i], m) }).setDifficulty(6400).setSkillset(Skill.BREWING.id);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 139 */       for (m = 1; m <= 4; m++) {
/* 140 */         crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlMilk, m), true, new Object[] { milk_buckets[i], new ItemStack(Item.bowlEmpty, m) }).setDifficulty(25);
/*     */       } 
/* 142 */       crafting_manager.addShapelessRecipe(new ItemStack(milk_buckets[i]), true, new Object[] { milk_buckets[i].getEmptyVessel(), Item.bowlMilk, Item.bowlMilk, Item.bowlMilk, Item.bowlMilk }).setDifficulty(25);
/*     */     } 
/*     */     
/* 145 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.dough, 1), new Object[] { Item.flour, Item.bowlWater });
/*     */     
/* 147 */     ItemBucket[] water_buckets = { Item.bucketCopperWater, Item.bucketSilverWater, Item.bucketGoldWater, Item.bucketIronWater, Item.bucketAncientMetalWater, Item.bucketMithrilWater, Item.bucketAdamantiumWater };
/*     */     int j;
/* 149 */     for (j = 0; j < milk_buckets.length; j++) {
/*     */       int m;
/* 151 */       for (m = 1; m <= 4; m++) {
/*     */         
/* 153 */         crafting_manager.addShapelessRecipe(new ItemStack(Item.dough, m), false, new Object[] { water_buckets[j], new ItemStack(Item.flour, m) });
/* 154 */         crafting_manager.addShapelessRecipe(new ItemStack(Item.cookie, m * 4), false, new Object[] { water_buckets[j], new ItemStack(Item.flour, m), new ItemStack(Item.chocolate, m) }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */         
/* 156 */         crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlWater, m), true, new Object[] { water_buckets[j], new ItemStack(Item.bowlEmpty, m) }).setDifficulty(25);
/*     */       } 
/*     */       
/* 159 */       for (m = 1; m <= 2; m++) {
/*     */         
/* 161 */         crafting_manager.addShapelessRecipe(new ItemStack(Item.cookie, m * 4), false, new Object[] { water_buckets[j], new ItemStack(Item.flour, m), new ItemStack(Item.dyePowder, m, 3), new ItemStack(Item.sugar, m) }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */       } 
/*     */       
/* 164 */       crafting_manager.addShapelessRecipe(new ItemStack(water_buckets[j]), true, new Object[] { water_buckets[j].getEmptyVessel(), new ItemStack(Item.bowlWater, 4) }).setDifficulty(25);
/*     */     } 
/*     */     
/* 167 */     for (j = 1; j <= 4; j++) {
/* 168 */       crafting_manager.addShapelessRecipe(new ItemStack(Item.cookie, j * 4), false, new Object[] { new ItemStack(Item.dough, j), new ItemStack(Item.chocolate, j) }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */     } 
/* 170 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlBeefStew), new Object[] { Item.beefCooked, Block.mushroomBrown, Item.potato, Item.bowlWater }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 171 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlChickenSoup), new Object[] { Item.chickenCooked, Item.carrot, Item.onion, Item.bowlWater }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 172 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlVegetableSoup), new Object[] { Item.potato, Item.carrot, Item.onion, Item.bowlWater }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 173 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlIceCream), new Object[] { Item.chocolate, Item.bowlMilk, Item.snowball }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 174 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlIceCream), new Object[] { new ItemStack(Item.dyePowder, 1, 3), Item.sugar, Item.bowlMilk, Item.snowball }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 175 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlSalad), new Object[] { Block.plantYellow, Block.plantYellow, Block.plantYellow, Item.bowlEmpty }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */     
/* 177 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlCreamOfMushroomSoup), new Object[] { Block.mushroomBrown, Block.mushroomBrown, Item.bowlMilk }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 178 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlCreamOfVegetableSoup), new Object[] { Item.potato, Item.carrot, Item.onion, Item.bowlMilk }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */     
/* 180 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlPumpkinSoup), new Object[] { Block.pumpkin, Item.bowlWater }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */     
/* 182 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlMashedPotato), new Object[] { Item.bakedPotato, Item.cheese, Item.bowlMilk }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 183 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlSorbet), new Object[] { Item.orange, Item.sugar, Item.snowball, Item.bowlEmpty }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */     
/* 185 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlPorridge), new Object[] { Item.seeds, Item.blueberries, Item.sugar, Item.bowlWater }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */ 
/*     */     
/* 188 */     crafting_manager.addShapelessRecipe(new ItemStack(Item.bowlCereal), new Object[] { Item.wheat, Item.sugar, Item.bowlMilk }).setSkillset(Skill.FOOD_PREPARATION.id);
/*     */     
/* 190 */     ItemCoin[] coins = { Item.coinCopper, Item.coinSilver, Item.coinGold, Item.coinAncientMetal, Item.coinMithril, Item.coinAdamantium };
/*     */     int k;
/* 192 */     for (k = 0; k < coins.length; k++) {
/*     */       
/* 194 */       ItemCoin coin = coins[k];
/*     */       
/* 196 */       for (int m = 1; m <= 9; m++) {
/*     */         
/* 198 */         crafting_manager.addShapelessRecipe(new ItemStack(coin.getNuggetPeer(), m), new Object[] { new ItemStack(coin, m) }).setDifficulty(25);
/*     */       } 
/*     */ 
/*     */       
/* 202 */       crafting_manager.addShapelessRecipe(new ItemStack(coin), new Object[] { new ItemStack(coin.getNuggetPeer()) }).setDifficulty(100);
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
/* 213 */     crafting_manager.addShapelessRecipes(bucket_of_stone_dismantling_based_items, 0, true, false);
/*     */     
/* 215 */     for (k = 0; k < Block.workbench.getNumSubBlocks(); k++) {
/*     */       
/* 217 */       Material tool_material = BlockWorkbench.getToolMaterial(k);
/*     */       
/* 219 */       if (tool_material == Material.flint) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 228 */         crafting_manager.addRecipe(new ItemStack(Block.workbench, 1, k), new Object[] { "K", "#", Character.valueOf('K'), Item.knifeFlint, Character.valueOf('#'), BlockWorkbench.getBlockComponent(k) });
/* 229 */         crafting_manager.addRecipe(new ItemStack(Block.workbench, 1, k), new Object[] { "FS", "s#", Character.valueOf('F'), Item.flint, Character.valueOf('S'), Item.silk, Character.valueOf('s'), Item.stick, Character.valueOf('#'), BlockWorkbench.getBlockComponent(k) });
/* 230 */         crafting_manager.addRecipe(new ItemStack(Block.workbench, 1, k), new Object[] { "FS", "s#", Character.valueOf('F'), Item.flint, Character.valueOf('S'), Item.sinew, Character.valueOf('s'), Item.stick, Character.valueOf('#'), BlockWorkbench.getBlockComponent(k) });
/*     */ 
/*     */ 
/*     */         
/* 234 */         crafting_manager.addRecipe(new ItemStack(Item.knifeFlint, 1), false, new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.workbench, 1, k) }).setDifficulty(25.0F);
/*     */       }
/* 236 */       else if (tool_material == Material.obsidian) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 245 */         crafting_manager.addRecipe(new ItemStack(Block.workbench, 1, k), new Object[] { "K", "#", Character.valueOf('K'), Item.knifeObsidian, Character.valueOf('#'), BlockWorkbench.getBlockComponent(k) });
/* 246 */         crafting_manager.addRecipe(new ItemStack(Block.workbench, 1, k), new Object[] { "OS", "s#", Character.valueOf('O'), Block.obsidian, Character.valueOf('S'), Item.silk, Character.valueOf('s'), Item.stick, Character.valueOf('#'), BlockWorkbench.getBlockComponent(k) });
/* 247 */         crafting_manager.addRecipe(new ItemStack(Block.workbench, 1, k), new Object[] { "OS", "s#", Character.valueOf('O'), Block.obsidian, Character.valueOf('S'), Item.sinew, Character.valueOf('s'), Item.stick, Character.valueOf('#'), BlockWorkbench.getBlockComponent(k) });
/*     */ 
/*     */ 
/*     */         
/* 251 */         crafting_manager.addRecipe(new ItemStack(Item.knifeObsidian, 1), false, new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.workbench, 1, k) }).setDifficulty(25.0F);
/*     */       }
/*     */       else {
/*     */         
/* 255 */         for (int plank_subtype = 0; plank_subtype < 4; plank_subtype++) {
/*     */ 
/*     */           
/* 258 */           crafting_manager.addRecipe(new ItemStack(Block.workbench, 1, k), new Object[] { "IL", "s#", Character.valueOf('I'), ItemIngot.getMatchingItem(ItemIngot.class, tool_material), Character.valueOf('L'), Item.leather, Character.valueOf('s'), Item.stick, Character.valueOf('#'), new ItemStack(Block.planks, 1, plank_subtype) });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addFurnaceRecipes(FurnaceRecipes furnace_recipes) {
/* 268 */     furnace_recipes.addSmelting(Block.sand.blockID, new ItemStack(Block.sandStone));
/* 269 */     furnace_recipes.addSmelting(Block.sandStone.blockID, new ItemStack(Block.glass));
/*     */     
/* 271 */     furnace_recipes.addSmelting(Item.clay.itemID, new ItemStack(Item.brick));
/* 272 */     furnace_recipes.addSmelting(Block.blockClay.blockID, new ItemStack(Block.hardenedClay));
/* 273 */     furnace_recipes.addSmelting(Block.cactus.blockID, new ItemStack(Item.dyePowder, 1, 2));
/* 274 */     furnace_recipes.addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1));
/*     */     
/* 276 */     furnace_recipes.addSmelting(Block.netherrack.blockID, new ItemStack(Item.netherrackBrick));
/* 277 */     furnace_recipes.addSmelting(Block.oreCoal.blockID, new ItemStack(Item.coal));
/* 278 */     furnace_recipes.addSmelting(Block.oreRedstone.blockID, new ItemStack(Item.redstone, 4));
/* 279 */     furnace_recipes.addSmelting(Block.oreLapis.blockID, new ItemStack(Item.dyePowder, 4, 4));
/* 280 */     furnace_recipes.addSmelting(Block.oreNetherQuartz.blockID, new ItemStack(Item.netherQuartz));
/*     */     
/* 282 */     furnace_recipes.addSmelting(Block.oreCopper.blockID, new ItemStack(Item.ingotCopper));
/* 283 */     furnace_recipes.addSmelting(Block.oreSilver.blockID, new ItemStack(Item.ingotSilver));
/* 284 */     furnace_recipes.addSmelting(Block.oreIron.blockID, new ItemStack(Item.ingotIron));
/* 285 */     furnace_recipes.addSmelting(Block.oreMithril.blockID, new ItemStack(Item.ingotMithril));
/* 286 */     furnace_recipes.addSmelting(Block.oreAdamantium.blockID, new ItemStack(Item.ingotAdamantium));
/* 287 */     furnace_recipes.addSmelting(Block.oreGold.blockID, new ItemStack(Item.ingotGold));
/*     */     
/* 289 */     furnace_recipes.addSmelting(Block.oreEmerald.blockID, new ItemStack(Item.emerald));
/* 290 */     furnace_recipes.addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.diamond));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 300 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*     */       
/* 302 */       Item item = Item.getItem(i);
/*     */       
/* 304 */       if (item instanceof ItemFood) {
/*     */         
/* 306 */         ItemFood food = (ItemFood)item;
/*     */         
/* 308 */         if (food.getCookedItem() != null)
/* 309 */           furnace_recipes.addSmelting(i, new ItemStack(food.getCookedItem())); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesMITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */