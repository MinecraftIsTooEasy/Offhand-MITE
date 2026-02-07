/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class CraftingManager
/*     */ {
/*  11 */   private static final CraftingManager instance = new CraftingManager();
/*     */ 
/*     */   
/*  14 */   private List recipes = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final CraftingManager getInstance() {
/*  21 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   private CraftingManager() {
/*  26 */     (new RecipesTools()).addRecipes(this);
/*  27 */     (new RecipesWeapons()).addRecipes(this);
/*  28 */     (new RecipesIngots()).addRecipes(this);
/*  29 */     (new RecipesFood()).addRecipes(this);
/*  30 */     RecipesMITE.addCraftingRecipes(this);
/*  31 */     (new RecipesCrafting()).addRecipes(this);
/*  32 */     (new RecipesArmor()).addRecipes(this);
/*  33 */     (new RecipesDyes()).addRecipes(this);
/*  34 */     this.recipes.add(new RecipesArmorDyes());
/*  35 */     this.recipes.add(new RecipesMapCloning());
/*  36 */     this.recipes.add(new RecipesMapExtending());
/*  37 */     this.recipes.add(new RecipeFireworks());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     addRecipe(new ItemStack(Item.paper, 3), new Object[] { "###", Character.valueOf('#'), Item.reed }).setSkillset(Skill.FINE_ARTS.id);
/* 171 */     addShapelessRecipe(new ItemStack(Item.book, 1), new Object[] { Item.paper, Item.paper, Item.paper, Item.leather }).setSkillset(Skill.FINE_ARTS.id);
/* 172 */     addShapelessRecipe(new ItemStack(Item.writableBook, 1), new Object[] { Item.book, new ItemStack(Item.dyePowder, 1, 0), Item.feather }).setSkillset(Skill.FINE_ARTS.id);
/* 173 */     addRecipe(new ItemStack(Block.fence, 2), new Object[] { "###", "###", Character.valueOf('#'), Item.stick }).setSkillset(Skill.CARPENTRY.id);
/*     */ 
/*     */ 
/*     */     
/* 177 */     addRecipe(new ItemStack(Block.cobblestoneWall, 8, 0), new Object[] { "###", "###", Character.valueOf('#'), Block.cobblestone }).setSkillset(Skill.MASONRY.id);
/* 178 */     addRecipe(new ItemStack(Block.cobblestoneWall, 8, 1), new Object[] { "###", "###", Character.valueOf('#'), Block.cobblestoneMossy }).setSkillset(Skill.MASONRY.id);
/* 179 */     addRecipe(new ItemStack(Block.netherFence, 8), new Object[] { "###", "###", Character.valueOf('#'), Block.netherBrick }).setSkillset(Skill.MASONRY.id);
/* 180 */     addRecipe(new ItemStack(Block.fenceGate, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Item.stick, Character.valueOf('W'), Block.planks }).setSkillset(Skill.CARPENTRY.id);
/* 181 */     addRecipe(new ItemStack(Block.jukebox, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.diamond }).setSkillset(Skill.CARPENTRY.id + Skill.TINKERING.id);
/* 182 */     addRecipe(new ItemStack(Item.leash, 2), new Object[] { "~~ ", "~O ", "  ~", Character.valueOf('~'), Item.silk, Character.valueOf('O'), Item.slimeBall }).setSkillset(Skill.FINE_ARTS.id);
/* 183 */     addRecipe(new ItemStack(Item.leash, 2), new Object[] { "~~ ", "~O ", "  ~", Character.valueOf('~'), Item.sinew, Character.valueOf('O'), Item.slimeBall }).setSkillset(Skill.FINE_ARTS.id);
/* 184 */     addRecipe(new ItemStack(Block.music, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.redstone }).setSkillset(Skill.CARPENTRY.id + Skill.TINKERING.id);
/* 185 */     addRecipe(new ItemStack(Block.bookShelf, 1), new Object[] { "###", "XXX", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.book }).setSkillset(Skill.CARPENTRY.id);
/*     */ 
/*     */     
/* 188 */     addRecipe(new ItemStack(Block.blockClay, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.clay });
/*     */     
/* 190 */     addRecipe(new ItemStack(Block.brick, 2), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.brick, Character.valueOf('X'), Block.sand }).setSkillset(Skill.MASONRY.id);
/* 191 */     addRecipe(new ItemStack(Block.hardenedClay, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.brick }).setSkillset(Skill.MASONRY.id);
/* 192 */     addRecipe(new ItemStack(Block.netherBrick, 2), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.netherrackBrick, Character.valueOf('X'), Block.slowSand }).setSkillset(Skill.MASONRY.id);
/* 193 */     addRecipe(new ItemStack(Item.brick, 4), new Object[] { "#", Character.valueOf('#'), Block.hardenedClay }).setSkillset(Skill.MASONRY.id);
/* 194 */     addRecipe(new ItemStack(Block.glowStone, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.glowstone }).setSkillset(Skill.MASONRY.id);
/*     */     
/* 196 */     addRecipe(new ItemStack(Block.cloth, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.silk }).setSkillset(Skill.FINE_ARTS.id);
/* 197 */     addRecipe(new ItemStack(Block.tnt, 1), new Object[] { "X#X", "#X#", "X#X", Character.valueOf('X'), Item.gunpowder, Character.valueOf('#'), Block.sand }).setSkillset(Skill.TINKERING.id);
/* 198 */     addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 3), new Object[] { "###", Character.valueOf('#'), Block.cobblestone }).setSkillset(Skill.MASONRY.id);
/* 199 */     addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), new Object[] { "###", Character.valueOf('#'), Block.stone }).setSkillset(Skill.MASONRY.id);
/* 200 */     addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 1), new Object[] { "###", Character.valueOf('#'), Block.sandStone }).setSkillset(Skill.MASONRY.id);
/* 201 */     addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 4), new Object[] { "###", Character.valueOf('#'), Block.brick }).setSkillset(Skill.MASONRY.id);
/* 202 */     addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 5), new Object[] { "###", Character.valueOf('#'), Block.stoneBrick }).setSkillset(Skill.MASONRY.id);
/* 203 */     addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 6), new Object[] { "###", Character.valueOf('#'), Block.netherBrick }).setSkillset(Skill.MASONRY.id);
/* 204 */     addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 7), new Object[] { "###", Character.valueOf('#'), Block.blockNetherQuartz }).setSkillset(Skill.MASONRY.id);
/*     */     
/* 206 */     addRecipe(new ItemStack(Block.woodSingleSlab, 6, 0), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 0) }).setSkillset(Skill.CARPENTRY.id);
/* 207 */     addRecipe(new ItemStack(Block.woodSingleSlab, 6, 2), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 2) }).setSkillset(Skill.CARPENTRY.id);
/* 208 */     addRecipe(new ItemStack(Block.woodSingleSlab, 6, 1), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 1) }).setSkillset(Skill.CARPENTRY.id);
/* 209 */     addRecipe(new ItemStack(Block.woodSingleSlab, 6, 3), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 3) }).setSkillset(Skill.CARPENTRY.id);
/*     */     
/* 211 */     addRecipe(new ItemStack(Block.ladder, 2), new Object[] { "# #", "###", "# #", Character.valueOf('#'), Item.stick }).setSkillset(Skill.CARPENTRY.id);
/*     */     
/* 213 */     addRecipe(new ItemStack(Block.trapdoor, 2), new Object[] { "###", "###", Character.valueOf('#'), Block.planks }).setSkillset(Skill.CARPENTRY.id);
/*     */ 
/*     */     
/* 216 */     addRecipe(new ItemStack(Item.sign, 1), new Object[] { "#", "/", Character.valueOf('#'), Block.woodSingleSlab, Character.valueOf('/'), Item.stick }).setSkillset(Skill.CARPENTRY.id);
/*     */ 
/*     */     
/* 219 */     addRecipe(new ItemStack(Item.sugar, 1), new Object[] { "#", Character.valueOf('#'), Item.reed }).scaleDifficulty(8.0F).setSkillset(Skill.FARMING.id);
/* 220 */     addRecipe(new ItemStack(Block.planks, 4, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 0) }).setSkillset(Skill.CARPENTRY.id);
/* 221 */     addRecipe(new ItemStack(Block.planks, 4, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 1) }).setSkillset(Skill.CARPENTRY.id);
/* 222 */     addRecipe(new ItemStack(Block.planks, 4, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 2) }).setSkillset(Skill.CARPENTRY.id);
/* 223 */     addRecipe(new ItemStack(Block.planks, 4, 3), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 3) }).setSkillset(Skill.CARPENTRY.id);
/* 224 */     addRecipe(new ItemStack(Item.stick, 4), new Object[] { "#", "#", Character.valueOf('#'), Block.planks });
/*     */     
/* 226 */     addRecipe(new ItemStack(Block.torchWood, 4), new Object[] { "X", "#", Character.valueOf('X'), Item.coal, Character.valueOf('#'), Item.stick });
/* 227 */     addRecipe(new ItemStack(Block.torchWood, 4), new Object[] { "X", "#", Character.valueOf('X'), new ItemStack(Item.coal, 1, 1), Character.valueOf('#'), Item.stick });
/* 228 */     addRecipe(new ItemStack(Item.bowlEmpty, 4), new Object[] { "# #", " # ", Character.valueOf('#'), Block.planks }).setSkillsets(new int[] { Skill.CARPENTRY.id, Skill.FINE_ARTS.id });
/* 229 */     addRecipe(new ItemStack(Item.glassBottle, 3), new Object[] { "# #", " # ", Character.valueOf('#'), Block.glass }).setSkillset(Skill.FINE_ARTS.id);
/* 230 */     addRecipe(new ItemStack(Block.rail, 16), new Object[] { "X X", "X#X", "X X", Character.valueOf('X'), Item.ingotIron, Character.valueOf('#'), Item.stick }).setSkillset(Skill.BLACKSMITHING.id);
/* 231 */     addRecipe(new ItemStack(Block.railPowered, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Item.ingotGold, Character.valueOf('R'), Item.redstone, Character.valueOf('#'), Item.stick }).setSkillset(Skill.BLACKSMITHING.id + Skill.TINKERING.id);
/* 232 */     addRecipe(new ItemStack(Block.railActivator, 6), new Object[] { "XSX", "X#X", "XSX", Character.valueOf('X'), Item.ingotIron, Character.valueOf('#'), Block.torchRedstoneActive, Character.valueOf('S'), Item.stick }).setSkillset(Skill.BLACKSMITHING.id + Skill.TINKERING.id);
/* 233 */     addRecipe(new ItemStack(Block.railDetector, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Item.ingotIron, Character.valueOf('R'), Item.redstone, Character.valueOf('#'), Block.pressurePlateStone }).setSkillset(Skill.BLACKSMITHING.id + Skill.TINKERING.id);
/* 234 */     addRecipe(new ItemStack(Item.minecartEmpty, 1), new Object[] { "# #", "###", Character.valueOf('#'), Item.ingotIron }).setSkillset(Skill.BLACKSMITHING.id);
/* 235 */     addRecipe(new ItemStack(Item.cauldron, 1), new Object[] { "# #", "# #", "###", Character.valueOf('#'), Item.ingotIron }).setSkillset(Skill.BLACKSMITHING.id);
/* 236 */     addRecipe(new ItemStack(Item.brewingStand, 1), new Object[] { " B ", "###", Character.valueOf('#'), Block.cobblestone, Character.valueOf('B'), Item.blazeRod }).setSkillset(Skill.MASONRY.id);
/* 237 */     addRecipe(new ItemStack(Block.pumpkinLantern, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.pumpkin, Character.valueOf('B'), Block.torchWood });
/* 238 */     addRecipe(new ItemStack(Item.minecartCrate, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.chest, Character.valueOf('B'), Item.minecartEmpty }).setDifficulty(25.0F);
/* 239 */     addRecipe(new ItemStack(Item.minecartPowered, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.furnaceIdle, Character.valueOf('B'), Item.minecartEmpty }).setDifficulty(25.0F);
/* 240 */     addRecipe(new ItemStack(Item.minecartTnt, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.tnt, Character.valueOf('B'), Item.minecartEmpty });
/* 241 */     addRecipe(new ItemStack(Item.minecartHopper, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.hopperBlock, Character.valueOf('B'), Item.minecartEmpty }).setDifficulty(100.0F);
/* 242 */     addRecipe(new ItemStack(Item.boat, 1), new Object[] { "# #", "###", Character.valueOf('#'), Block.planks }).setSkillset(Skill.CARPENTRY.id);
/*     */     
/* 244 */     addRecipe(new ItemStack(Item.flowerPot, 1), new Object[] { "# #", " # ", Character.valueOf('#'), Item.brick }).setSkillset(Skill.FINE_ARTS.id);
/*     */     
/* 246 */     addRecipe(new ItemStack(Item.flintAndSteel, 1), new Object[] { "A ", " B", Character.valueOf('A'), Item.ironNugget, Character.valueOf('B'), Item.flint });
/*     */     
/* 248 */     addRecipe(new ItemStack(Item.flour, 1), new Object[] { "###", Character.valueOf('#'), Item.wheat });
/* 249 */     addRecipe(new ItemStack(Item.flour, 1), new Object[] { "#", "#", "#", Character.valueOf('#'), Item.wheat });
/*     */     
/* 251 */     addRecipe(new ItemStack(Block.stairsWoodOak, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 0) }).setSkillset(Skill.CARPENTRY.id);
/* 252 */     addRecipe(new ItemStack(Block.stairsWoodBirch, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 2) }).setSkillset(Skill.CARPENTRY.id);
/* 253 */     addRecipe(new ItemStack(Block.stairsWoodSpruce, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 1) }).setSkillset(Skill.CARPENTRY.id);
/* 254 */     addRecipe(new ItemStack(Block.stairsWoodJungle, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 3) }).setSkillset(Skill.CARPENTRY.id);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     addRecipe(new ItemStack(Block.stairsCobblestone, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.cobblestone }).setSkillset(Skill.MASONRY.id);
/* 260 */     addRecipe(new ItemStack(Block.stairsBrick, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.brick }).setSkillset(Skill.MASONRY.id);
/* 261 */     addRecipe(new ItemStack(Block.stairsStoneBrick, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.stoneBrick }).setSkillset(Skill.MASONRY.id);
/* 262 */     addRecipe(new ItemStack(Block.stairsNetherBrick, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.netherBrick }).setSkillset(Skill.MASONRY.id);
/* 263 */     addRecipe(new ItemStack(Block.stairsSandStone, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.sandStone }).setSkillset(Skill.MASONRY.id);
/* 264 */     addRecipe(new ItemStack(Block.stairsNetherQuartz, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.blockNetherQuartz }).setSkillset(Skill.MASONRY.id);
/* 265 */     addRecipe(new ItemStack(Block.stairsObsidian, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.obsidian }).setSkillset(Skill.MASONRY.id);
/*     */     
/* 267 */     addRecipe(new ItemStack(Item.painting, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Block.cloth }).setSkillset(Skill.FINE_ARTS.id);
/* 268 */     addRecipe(new ItemStack(Item.itemFrame, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Item.leather }).setSkillsets(new int[] { Skill.CARPENTRY.id, Skill.FINE_ARTS.id });
/*     */     
/* 270 */     addRecipe(new ItemStack(Item.appleGold, 1, 0), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.goldNugget, Character.valueOf('X'), Item.appleRed }).setSkillset(Skill.FINE_ARTS.id);
/*     */     
/* 272 */     addRecipe(new ItemStack(Item.goldenCarrot, 1, 0), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.goldNugget, Character.valueOf('X'), Item.carrot }).setSkillset(Skill.FINE_ARTS.id);
/* 273 */     addRecipe(new ItemStack(Item.speckledMelon, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.goldNugget, Character.valueOf('X'), Item.melon }).setSkillset(Skill.FINE_ARTS.id);
/* 274 */     addRecipe(new ItemStack(Block.lever, 1), new Object[] { "X", "#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.stick }).setSkillset(Skill.MASONRY.id);
/* 275 */     addRecipe(new ItemStack(Block.tripWireSource, 2), new Object[] { "I", "S", "#", Character.valueOf('#'), Block.planks, Character.valueOf('S'), Item.stick, Character.valueOf('I'), Item.ingotIron }).setSkillset(Skill.FINE_ARTS.id);
/* 276 */     addRecipe(new ItemStack(Block.torchRedstoneActive, 1), new Object[] { "X", "#", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Item.redstone }).setSkillset(Skill.TINKERING.id);
/* 277 */     addRecipe(new ItemStack(Item.redstoneRepeater, 1), new Object[] { "#X#", "III", Character.valueOf('#'), Block.torchRedstoneActive, Character.valueOf('X'), Item.redstone, Character.valueOf('I'), Block.stone }).setSkillset(Skill.MASONRY.id + Skill.TINKERING.id);
/* 278 */     addRecipe(new ItemStack(Item.comparator, 1), new Object[] { " # ", "#X#", "III", Character.valueOf('#'), Block.torchRedstoneActive, Character.valueOf('X'), Item.netherQuartz, Character.valueOf('I'), Block.stone }).setSkillset(Skill.MASONRY.id + Skill.TINKERING.id);
/*     */     
/* 280 */     addRecipe(new ItemStack(Item.pocketSundial, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.goldNugget, Character.valueOf('X'), Item.redstone }).setSkillset(Skill.TINKERING.id);
/*     */ 
/*     */     
/* 283 */     addRecipe(new ItemStack(Item.compass, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.ironNugget, Character.valueOf('X'), Item.redstone }).setSkillset(Skill.TINKERING.id);
/* 284 */     addRecipe(new ItemStack(Item.emptyMap, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.paper, Character.valueOf('X'), Item.compass }).setSkillset(Skill.FINE_ARTS.id);
/* 285 */     addRecipe(new ItemStack(Block.stoneButton, 1), new Object[] { "#", Character.valueOf('#'), Block.stone }).setSkillset(Skill.MASONRY.id);
/* 286 */     addRecipe(new ItemStack(Block.woodenButton, 1), new Object[] { "#", Character.valueOf('#'), Block.planks }).setSkillset(Skill.CARPENTRY.id);
/* 287 */     addRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[] { "##", Character.valueOf('#'), Block.stone }).setSkillset(Skill.MASONRY.id);
/* 288 */     addRecipe(new ItemStack(Block.pressurePlatePlanks, 1), new Object[] { "##", Character.valueOf('#'), Block.planks }).setSkillset(Skill.CARPENTRY.id);
/* 289 */     addRecipe(new ItemStack(Block.pressurePlateIron, 1), new Object[] { "##", Character.valueOf('#'), Item.ingotIron }).setSkillset(Skill.BLACKSMITHING.id);
/* 290 */     addRecipe(new ItemStack(Block.pressurePlateGold, 1), new Object[] { "##", Character.valueOf('#'), Item.ingotGold }).setSkillset(Skill.BLACKSMITHING.id);
/* 291 */     addRecipe(new ItemStack(Block.dispenser, 1), new Object[] { "###", "#X#", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.bow, Character.valueOf('R'), Item.redstone }).setSkillset(Skill.MASONRY.id + Skill.TINKERING.id);
/* 292 */     addRecipe(new ItemStack(Block.dropper, 1), new Object[] { "###", "# #", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('R'), Item.redstone }).setSkillset(Skill.MASONRY.id | Skill.TINKERING.id);
/* 293 */     addRecipe(new ItemStack(Block.pistonBase, 1), new Object[] { "TTT", "#X#", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.ingotIron, Character.valueOf('R'), Item.redstone, Character.valueOf('T'), Block.planks }).setSkillset(Skill.BLACKSMITHING.id + Skill.TINKERING.id);
/* 294 */     addRecipe(new ItemStack(Block.pistonStickyBase, 1), new Object[] { "S", "P", Character.valueOf('S'), Item.slimeBall, Character.valueOf('P'), Block.pistonBase });
/* 295 */     addRecipe(new ItemStack(Item.bed, 1), new Object[] { "###", "XXX", Character.valueOf('#'), Block.cloth, Character.valueOf('X'), Block.planks }).setSkillset(Skill.CARPENTRY.id);
/* 296 */     addRecipe(new ItemStack(Block.enchantmentTable, 1), new Object[] { " B ", "D#D", "###", Character.valueOf('#'), Block.obsidian, Character.valueOf('B'), Item.book, Character.valueOf('D'), Item.diamond }).setSkillset(Skill.MASONRY.id + Skill.FINE_ARTS.id);
/* 297 */     addRecipe(new ItemStack(Block.enchantmentTableEmerald, 1), new Object[] { " B ", "E#E", "###", Character.valueOf('#'), Block.obsidian, Character.valueOf('B'), Item.book, Character.valueOf('E'), Item.emerald }).setSkillset(Skill.MASONRY.id + Skill.FINE_ARTS.id);
/* 298 */     addRecipe(new ItemStack(Block.anvil, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Block.blockIron, Character.valueOf('i'), Item.ingotIron }).setSkillset(Skill.BLACKSMITHING.id);
/*     */     
/* 300 */     addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[] { Item.gunpowder, Item.blazePowder, Item.coal }).setSkillset(Skill.TINKERING.id);
/* 301 */     addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[] { Item.gunpowder, Item.blazePowder, new ItemStack(Item.coal, 1, 1) }).setSkillset(Skill.TINKERING.id);
/* 302 */     addRecipe(new ItemStack(Block.daylightSensor), new Object[] { "GGG", "QQQ", "WWW", Character.valueOf('G'), Block.glass, Character.valueOf('Q'), Item.netherQuartz, Character.valueOf('W'), Block.woodSingleSlab }).setSkillset(Skill.CARPENTRY.id + Skill.TINKERING.id);
/* 303 */     addRecipe(new ItemStack(Block.hopperBlock), new Object[] { "I I", "ICI", " I ", Character.valueOf('I'), Item.ingotIron, Character.valueOf('C'), Block.chest }).setSkillset(Skill.BLACKSMITHING.id);
/*     */ 
/*     */ 
/*     */     
/* 307 */     Collections.sort(this.recipes, new RecipeSorter(this));
/*     */     
/* 309 */     Item.verifyThatAllItemsHaveMaterialsDefined();
/*     */ 
/*     */ 
/*     */     
/* 313 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*     */       
/* 315 */       Item item = Item.itemsList[i];
/*     */       
/* 317 */       if (item != null) {
/*     */ 
/*     */ 
/*     */         
/* 321 */         int num_subtypes = item.getNumSubtypes();
/*     */         
/* 323 */         if (num_subtypes == 0) {
/*     */           
/* 325 */           checkRecipe(item, 0);
/*     */         }
/*     */         else {
/*     */           
/* 329 */           for (int subtype = 0; subtype < num_subtypes; subtype++) {
/* 330 */             checkRecipe(item, subtype);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkRecipe(Item item, int subtype_or_0) {
/* 338 */     if ((item.isCraftingProduct() || item.isRepairable()) && item.getLowestCraftingDifficultyToProduce() == Float.MAX_VALUE) {
/*     */       
/* 340 */       if (item.hasMaterial(Material.rusted_iron)) {
/*     */         Item peer;
/*     */ 
/*     */         
/* 344 */         if (item instanceof ItemArmor) {
/* 345 */           (ItemArmor)item; peer = ItemArmor.getMatchingArmor(item.getClass(), Material.copper, ((ItemArmor)item).isChainMail());
/*     */         } else {
/* 347 */           peer = Item.getMatchingItem(item.getClass(), Material.copper);
/*     */         } 
/* 349 */         if (peer != null) {
/* 350 */           item.setLowestCraftingDifficultyToProduce(peer.getLowestCraftingDifficultyToProduce());
/*     */         }
/*     */       } 
/* 353 */       if (item.getLowestCraftingDifficultyToProduce() == Float.MAX_VALUE) {
/* 354 */         Minecraft.setErrorMessage("Warning: " + item.getItemDisplayName(null) + " [" + item.itemID + "] is " + (item.isCraftingComponent(subtype_or_0) ? "a crafting product" : "repairable") + " but its lowest_crafting_difficulty_to_produce cannot be determined");
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 360 */     if (item.isCraftingComponent(subtype_or_0) && item.getCraftingDifficultyAsComponent(new ItemStack(item, 1, subtype_or_0)) < 0.0F) {
/*     */       
/* 362 */       float lowest_crafting_difficulty_to_produce = item.getLowestCraftingDifficultyToProduce();
/*     */       
/* 364 */       if (lowest_crafting_difficulty_to_produce != Float.MAX_VALUE) {
/* 365 */         item.setCraftingDifficultyAsComponent(lowest_crafting_difficulty_to_produce);
/*     */       } else {
/* 367 */         Minecraft.setErrorMessage("Warning: " + item.getItemDisplayName(null) + " [" + item.itemID + "] is a crafting component but its crafting_difficulty_as_component has not been set");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   ShapedRecipes addRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj) {
/* 373 */     return addRecipe(par1ItemStack, true, par2ArrayOfObj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   ShapedRecipes addRecipe(ItemStack par1ItemStack, boolean include_in_lowest_crafting_difficulty_determination, Object... par2ArrayOfObj) {
/* 379 */     String var3 = "";
/* 380 */     int var4 = 0;
/* 381 */     int var5 = 0;
/* 382 */     int var6 = 0;
/*     */     
/* 384 */     if (par2ArrayOfObj[var4] instanceof String[]) {
/*     */       
/* 386 */       String[] var7 = (String[])par2ArrayOfObj[var4++];
/*     */       
/* 388 */       for (int var8 = 0; var8 < var7.length; var8++)
/*     */       {
/* 390 */         String var9 = var7[var8];
/* 391 */         var6++;
/* 392 */         var5 = var9.length();
/* 393 */         var3 = var3 + var9;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 398 */       while (par2ArrayOfObj[var4] instanceof String) {
/*     */         
/* 400 */         String var11 = (String)par2ArrayOfObj[var4++];
/* 401 */         var6++;
/* 402 */         var5 = var11.length();
/* 403 */         var3 = var3 + var11;
/*     */       } 
/*     */     } 
/*     */     
/*     */     HashMap<Object, Object> var12;
/*     */     
/* 409 */     for (var12 = new HashMap<Object, Object>(); var4 < par2ArrayOfObj.length; var4 += 2) {
/*     */       
/* 411 */       Character var13 = (Character)par2ArrayOfObj[var4];
/* 412 */       ItemStack var14 = null;
/*     */       
/* 414 */       if (par2ArrayOfObj[var4 + 1] instanceof Item) {
/*     */         
/* 416 */         var14 = new ItemStack((Item)par2ArrayOfObj[var4 + 1]);
/*     */       
/*     */       }
/* 419 */       else if (par2ArrayOfObj[var4 + 1] instanceof Block) {
/*     */         
/* 421 */         var14 = new ItemStack((Block)par2ArrayOfObj[var4 + 1], 1, 32767);
/*     */       
/*     */       }
/* 424 */       else if (par2ArrayOfObj[var4 + 1] instanceof ItemStack) {
/*     */         
/* 426 */         var14 = (ItemStack)par2ArrayOfObj[var4 + 1];
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 431 */         Minecraft.setErrorMessage("Invalid recipe component for " + par1ItemStack.getDisplayName());
/*     */       } 
/*     */       
/* 434 */       var12.put(var13, var14);
/*     */     } 
/*     */     
/* 437 */     ItemStack[] var15 = new ItemStack[var5 * var6];
/*     */     
/* 439 */     for (int var16 = 0; var16 < var5 * var6; var16++) {
/*     */       
/* 441 */       char var10 = var3.charAt(var16);
/*     */       
/* 443 */       if (var12.containsKey(Character.valueOf(var10))) {
/*     */         
/* 445 */         var15[var16] = ((ItemStack)var12.get(Character.valueOf(var10))).copy();
/*     */       }
/*     */       else {
/*     */         
/* 449 */         var15[var16] = null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 454 */     ShapedRecipes var17 = new ShapedRecipes(var5, var6, var15, par1ItemStack, include_in_lowest_crafting_difficulty_determination);
/*     */     
/* 456 */     this.recipes.add(var17);
/* 457 */     return var17;
/*     */   }
/*     */ 
/*     */   
/*     */   ShapelessRecipes addShapelessRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj) {
/* 462 */     return addShapelessRecipe(par1ItemStack, true, par2ArrayOfObj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   ShapelessRecipes addShapelessRecipe(ItemStack par1ItemStack, boolean include_in_lowest_crafting_difficulty_determination, Object... par2ArrayOfObj) {
/* 468 */     ArrayList<ItemStack> var3 = new ArrayList();
/* 469 */     Object[] var4 = par2ArrayOfObj;
/* 470 */     int var5 = par2ArrayOfObj.length;
/*     */     
/* 472 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 474 */       Object var7 = var4[var6];
/*     */       
/* 476 */       if (var7 instanceof ItemStack) {
/*     */ 
/*     */ 
/*     */         
/* 480 */         ItemStack item_stack = (ItemStack)var7;
/*     */         
/* 482 */         for (int i = 0; i < item_stack.stackSize; i++) {
/* 483 */           var3.add(((ItemStack)var7).copy().setStackSize(1));
/*     */         
/*     */         }
/*     */       }
/* 487 */       else if (var7 instanceof Item) {
/*     */         
/* 489 */         var3.add(new ItemStack((Item)var7));
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 497 */         if (!(var7 instanceof Block))
/*     */         {
/* 499 */           throw new RuntimeException("Invalid shapeless recipy!");
/*     */         }
/*     */ 
/*     */         
/* 503 */         var3.add(new ItemStack((Block)var7));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 513 */     ShapelessRecipes recipe = new ShapelessRecipes(par1ItemStack, var3, include_in_lowest_crafting_difficulty_determination);
/* 514 */     this.recipes.add(recipe);
/* 515 */     return recipe;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getResultingDurabilityFromCombiningItems(ItemStack first, ItemStack second) {
/* 521 */     int max_damage = first.getMaxDamage();
/*     */     
/* 523 */     int durability_on_first_item_stack = max_damage - first.getItemDamage();
/* 524 */     int durability_on_second_item_stack = max_damage - second.getItemDamage();
/*     */     
/* 526 */     int resulting_durability = durability_on_first_item_stack + durability_on_second_item_stack;
/*     */     
/* 528 */     if (resulting_durability > max_damage) {
/* 529 */       resulting_durability = max_damage;
/*     */     }
/* 531 */     return max_damage - resulting_durability;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftingResult findMatchingRecipe(InventoryCrafting par1InventoryCrafting, World par2World, EntityPlayer player) {
/* 538 */     if (player != null && player.openContainer != null) {
/* 539 */       player.openContainer.repair_fail_condition = 0;
/*     */     }
/* 541 */     int var3 = 0;
/* 542 */     ItemStack var4 = null;
/* 543 */     ItemStack var5 = null;
/*     */     
/*     */     int var6;
/* 546 */     for (var6 = 0; var6 < par1InventoryCrafting.getSizeInventory(); var6++) {
/*     */       
/* 548 */       ItemStack var7 = par1InventoryCrafting.getStackInSlot(var6);
/*     */       
/* 550 */       if (var7 != null) {
/*     */         
/* 552 */         if (var3 == 0)
/*     */         {
/* 554 */           var4 = var7;
/*     */         }
/*     */         
/* 557 */         if (var3 == 1)
/*     */         {
/* 559 */           var5 = var7;
/*     */         }
/*     */         
/* 562 */         var3++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 567 */     if (var3 == 2 && var4.itemID == var5.itemID && var4.stackSize == 1 && var5.stackSize == 1 && Item.itemsList[var4.itemID].isRepairable()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 582 */       if (!var4.isItemDamaged() || !var5.isItemDamaged()) {
/* 583 */         return null;
/*     */       }
/* 585 */       if (var4.getQuality() != var5.getQuality()) {
/* 586 */         return null;
/*     */       }
/* 588 */       if (var4.isItemEnchanted() || var5.isItemEnchanted()) {
/* 589 */         return null;
/*     */       }
/* 591 */       if (var4.isDyed() || var5.isDyed()) {
/*     */         
/* 593 */         if (!var4.isDyed() || !var5.isDyed()) {
/* 594 */           return null;
/*     */         }
/* 596 */         if (var4.getDyedColor() != var5.getDyedColor()) {
/* 597 */           return null;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 603 */       float crafting_difficulty = var4.getItem().getLowestCraftingDifficultyToProduce();
/*     */       
/* 605 */       if (var4.getItem().hasQuality())
/*     */       {
/* 607 */         if (player != null && var4.getQuality().isHigherThan(player.getMaxCraftingQuality(crafting_difficulty, var4.getItem(), var4.getItem().getSkillsetsThatCanRepairThis())))
/*     */         {
/* 609 */           player.openContainer.repair_fail_condition = 1;
/*     */         }
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 618 */       ItemStack resulting_stack = (new ItemStack(var4.itemID, 1, var4.getItemSubtype())).setItemDamage(getResultingDurabilityFromCombiningItems(var4, var5));
/*     */       
/* 620 */       if (var4.isDyed()) {
/* 621 */         resulting_stack.copyDyedColor(var4);
/*     */       }
/* 623 */       CraftingResult crafting_result = (new CraftingResult(resulting_stack, crafting_difficulty / 2.0F, var4.getItem().getSkillsetsThatCanRepairThis(), null)).setExperienceCostExempt().setQualityOverride(var4.getQuality());
/*     */       
/* 625 */       crafting_result.setRepair();
/*     */       
/* 627 */       return crafting_result;
/*     */     } 
/*     */     
/* 630 */     if (var3 == 2 && (var4.getItem() == Item.sinew || var5.getItem() == Item.sinew || var4.getItem() == Item.silk || var5.getItem() == Item.silk) && ((var4.getItem() instanceof ItemArmor && ((ItemArmor)var4.getItem()).isLeather() && var4.stackSize == 1 && var4.isItemDamaged()) || (var5.getItem() instanceof ItemArmor && ((ItemArmor)var5.getItem()).isLeather() && var5.stackSize == 1 && var5.isItemDamaged()))) {
/*     */       ItemStack item_stack_sinew, item_stack_armor;
/*     */ 
/*     */       
/* 634 */       if (var4.getItem() == Item.sinew || var4.getItem() == Item.silk) {
/*     */         
/* 636 */         item_stack_sinew = var4;
/* 637 */         item_stack_armor = var5;
/*     */       }
/*     */       else {
/*     */         
/* 641 */         item_stack_sinew = var5;
/* 642 */         item_stack_armor = var4;
/*     */       } 
/*     */       
/* 645 */       if (item_stack_armor.getItem().hasQuality())
/*     */       {
/* 647 */         if (player != null && item_stack_armor.getQuality().isHigherThan(player.getMaxCraftingQuality(item_stack_armor.getItem().getLowestCraftingDifficultyToProduce(), item_stack_armor.getItem(), item_stack_armor.getItem().getSkillsetsThatCanRepairThis()))) {
/* 648 */           return null;
/*     */         }
/*     */       }
/* 651 */       int damage = item_stack_armor.getItemDamage();
/* 652 */       int damage_repaired_per_sinew = item_stack_armor.getMaxDamage() / item_stack_armor.getItem().getRepairCost();
/* 653 */       int num_sinews_to_use = damage / damage_repaired_per_sinew;
/*     */       
/* 655 */       if (damage % damage_repaired_per_sinew != 0) {
/* 656 */         num_sinews_to_use++;
/*     */       }
/* 658 */       if (num_sinews_to_use > 1 && num_sinews_to_use * damage_repaired_per_sinew > damage) {
/* 659 */         num_sinews_to_use--;
/*     */       }
/* 661 */       if (num_sinews_to_use > item_stack_sinew.stackSize) {
/* 662 */         num_sinews_to_use = item_stack_sinew.stackSize;
/*     */       }
/* 664 */       int damage_repaired = num_sinews_to_use * damage_repaired_per_sinew;
/* 665 */       int damage_after_repair = Math.max(damage - damage_repaired, 0);
/*     */       
/* 667 */       ItemStack resulting_stack = item_stack_armor.copy().setItemDamage(damage_after_repair);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 672 */       CraftingResult crafting_result = (new CraftingResult(resulting_stack, (num_sinews_to_use * 50), item_stack_armor.getItem().getSkillsetsThatCanRepairThis(), null)).setExperienceCostExempt().setQualityOverride(item_stack_armor.getQuality()).setConsumption(num_sinews_to_use);
/*     */       
/* 674 */       crafting_result.setRepair();
/*     */       
/* 676 */       return crafting_result;
/*     */     } 
/*     */ 
/*     */     
/* 680 */     Container event_handler = par1InventoryCrafting.getEventHandler();
/*     */     
/* 682 */     for (var6 = 0; var6 < this.recipes.size(); var6++) {
/*     */       
/* 684 */       IRecipe var12 = this.recipes.get(var6);
/*     */       
/* 686 */       if (var12.matches(par1InventoryCrafting, par2World))
/*     */       {
/* 688 */         if (!(event_handler instanceof MITEContainerCrafting) || !((MITEContainerCrafting)event_handler).isRecipeForbidden(var12)) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 693 */           CraftingResult crafting_result = var12.getCraftingResult(par1InventoryCrafting);
/*     */           
/* 695 */           if (crafting_result == null) {
/* 696 */             return null;
/*     */           }
/* 698 */           if (event_handler instanceof MITEContainerCrafting && ((MITEContainerCrafting)event_handler).isCraftingResultForbidden(crafting_result)) {
/* 699 */             return null;
/*     */           }
/*     */ 
/*     */           
/* 703 */           return crafting_result;
/*     */         } 
/*     */       }
/*     */     } 
/* 707 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getRecipeList() {
/* 716 */     return this.recipes;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getDefaultSkillsetForItem(Item item) {
/* 721 */     return item.hasMaterial(Material.wood, true) ? Skill.CARPENTRY.id : (item.containsCrystal() ? Skill.FINE_ARTS.id : (item.containsRockyMineral() ? Skill.MASONRY.id : (item.containsMetal() ? Skill.BLACKSMITHING.id : 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRecipes(Object[][] recipe_table, int skillset_override) {
/* 727 */     Object[] item_for_character = (Object[])recipe_table[0][(recipe_table[0]).length - 1];
/*     */     
/* 729 */     for (int material_index = 0; material_index < (recipe_table[0]).length - 1; material_index++) {
/*     */       
/* 731 */       Object material = recipe_table[0][material_index];
/*     */       
/* 733 */       for (int line_index = 1; line_index < recipe_table.length; line_index++) {
/*     */         
/* 735 */         Object[] line = recipe_table[line_index];
/*     */         
/* 737 */         Object item_or_block = line[material_index];
/*     */         
/* 739 */         Item item = (item_or_block instanceof Item) ? (Item)item_or_block : Item.getItem((Block)item_or_block);
/*     */         
/* 741 */         if (item != null) {
/*     */ 
/*     */           
/* 744 */           Object pattern = line[(recipe_table[0]).length - 1];
/* 745 */           int quantity = (line.length > (recipe_table[0]).length) ? ((Integer)line[line.length - 1]).intValue() : 1;
/*     */           
/* 747 */           int skillset = (skillset_override >= 0) ? skillset_override : getDefaultSkillsetForItem(item);
/*     */           
/* 749 */           Object[] pattern_and_items_map = new Object[3 + ((item_for_character == null) ? 0 : item_for_character.length)];
/*     */           
/* 751 */           pattern_and_items_map[0] = pattern;
/* 752 */           pattern_and_items_map[1] = Character.valueOf('?');
/* 753 */           pattern_and_items_map[2] = material;
/*     */           
/* 755 */           if (item_for_character != null)
/*     */           {
/* 757 */             for (int i = 0; i < item_for_character.length; i++) {
/* 758 */               pattern_and_items_map[i + 3] = item_for_character[i];
/*     */             }
/*     */           }
/* 761 */           addRecipe(new ItemStack(item, quantity), pattern_and_items_map).setSkillset(skillset);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addShapelessRecipes(Object[][] recipe_table, int skillset_override, boolean propagate_tag_compound) {
/* 769 */     addShapelessRecipes(recipe_table, skillset_override, propagate_tag_compound, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addShapelessRecipes(Object[][] recipe_table, int skillset_override, boolean propagate_tag_compound, boolean include_in_lowest_crafting_difficulty_determination) {
/* 775 */     for (int material_index = 0; material_index < (recipe_table[0]).length; material_index++) {
/*     */       
/* 777 */       Object material = recipe_table[0][material_index];
/*     */       
/* 779 */       for (int line_index = 1; line_index < recipe_table.length; line_index++) {
/*     */         
/* 781 */         Object[] line = recipe_table[line_index];
/*     */         
/* 783 */         Item item = (Item)line[material_index];
/*     */         
/* 785 */         if (item != null) {
/*     */ 
/*     */           
/* 788 */           Object[] items, constant_items = (Object[])line[(recipe_table[0]).length];
/*     */           
/* 790 */           int quantity = (line.length > (recipe_table[0]).length + 1) ? ((Integer)line[(recipe_table[0]).length + 1]).intValue() : 1;
/* 791 */           int skillset = (skillset_override >= 0) ? skillset_override : getDefaultSkillsetForItem(item);
/*     */ 
/*     */ 
/*     */           
/* 795 */           if (constant_items == null) {
/*     */             
/* 797 */             items = new Object[1];
/*     */           }
/*     */           else {
/*     */             
/* 801 */             items = new Object[1 + constant_items.length];
/*     */             
/* 803 */             for (int i = 0; i < constant_items.length; i++) {
/* 804 */               items[i + 1] = constant_items[i];
/*     */             }
/*     */           } 
/* 807 */           items[0] = material;
/*     */           
/* 809 */           ShapelessRecipes shapeless_recipe = addShapelessRecipe(new ItemStack(item, quantity), include_in_lowest_crafting_difficulty_determination, items);
/*     */           
/* 811 */           if (propagate_tag_compound) {
/* 812 */             shapeless_recipe.propagateTagCompound();
/*     */           }
/* 814 */           if (line.length > (recipe_table[0]).length + 2)
/* 815 */             shapeless_recipe.setDifficulty(((Integer)line[(recipe_table[0]).length + 2]).intValue()); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CraftingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */