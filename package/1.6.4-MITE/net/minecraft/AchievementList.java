/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
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
/*     */ public class AchievementList
/*     */ {
/*     */   public static int minDisplayColumn;
/*     */   public static int minDisplayRow;
/*     */   public static int maxDisplayColumn;
/*     */   public static int maxDisplayRow;
/*  22 */   public static List achievementList = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*  26 */   public static Achievement openInventory = (new Achievement(0, "openInventory", -2, 0, Item.book, (Achievement)null)).setIndependent().registerAchievement();
/*     */   
/*  28 */   public static Achievement stickPicker = (new Achievement(102, "stickPicker", 0, 1, Item.stick, openInventory)).setFlipped().registerAchievement();
/*  29 */   public static Achievement cuttingEdge = (new Achievement(103, "cuttingEdge", 2, 1, Item.hatchetFlint, stickPicker)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  33 */   public static Achievement mineWood = (new Achievement(1, "mineWood", 2, -1, Block.wood, cuttingEdge)).setTooltipWidth(130).registerAchievement();
/*     */ 
/*     */   
/*  36 */   public static Achievement buildWorkBench = (new Achievement(2, "buildWorkBench", 4, -1, Block.workbench, mineWood)).registerAchievement();
/*     */   
/*  38 */   public static Achievement buildShovel = (new Achievement(106, "buildShovel", 4, 1, Item.shovelWood, buildWorkBench)).registerAchievement();
/*     */ 
/*     */   
/*  41 */   public static Achievement nuggets = (new Achievement(107, "nuggets", 4, 3, Item.copperNugget, buildShovel)).registerAchievement();
/*     */   
/*  43 */   public static Achievement betterTools = (new Achievement(135, "betterTools", 6, 3, new ItemStack(Block.workbench, 1, 4), nuggets)).setTooltipWidth(125).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static Achievement buildPickaxe = (new Achievement(3, "buildPickaxe", 5, 5, Item.pickaxeCopper, betterTools)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static Achievement buildFurnace = (new Achievement(4, "buildFurnace", 5, 7, Block.furnaceIdle, buildPickaxe)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  55 */   public static Achievement acquireIron = (new Achievement(5, "acquireIron", 3, 7, Item.ingotIron, buildFurnace)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static Achievement buildHoe = (new Achievement(6, "buildHoe", 7, 6, Item.hoeCopper, betterTools)).registerAchievement();
/*     */   
/*  61 */   public static Achievement flour = (new Achievement(126, "flour", 7, 8, Item.flour, buildHoe)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  65 */   public static Achievement makeBread = (new Achievement(7, "makeBread", 6, 9, Item.bread, flour)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  69 */   public static Achievement bakeCake = (new Achievement(8, "bakeCake", 8, 10, Item.cake, flour)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static Achievement buildBetterPickaxe = (new Achievement(9, "buildBetterPickaxe", 1, 7, Item.pickaxeIron, acquireIron)).registerAchievement();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static Achievement onARail = (new Achievement(11, "onARail", 3, 5, Block.rail, acquireIron)).setSpecial().registerAchievement();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public static Achievement buildClub = (new Achievement(104, "buildClub", 6, -1, Item.clubWood, buildWorkBench)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static Achievement killEnemy = (new Achievement(13, "killEnemy", 8, -1, Item.bone, buildClub)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  93 */   public static Achievement killCow = (new Achievement(14, "killCow", 7, -3, Item.leather, buildClub)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/*  97 */   public static Achievement flyPig = (new Achievement(15, "flyPig", 7, -5, Item.saddle, killCow)).setSpecial().registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 101 */   public static Achievement snipeSkeleton = (new Achievement(16, "snipeSkeleton", 10, -1, Item.bow, killEnemy)).setSpecial().registerAchievement();
/*     */   
/* 103 */   public static Achievement obsidianFurnace = (new Achievement(128, "obsidianFurnace", 0, 9, Block.furnaceObsidianIdle, buildBetterPickaxe)).registerAchievement();
/* 104 */   public static Achievement mithrilIngot = (new Achievement(129, "mithrilIngot", -2, 9, Item.ingotMithril, obsidianFurnace)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 108 */   public static Achievement diamonds = (new Achievement(17, "diamonds", -3, 7, Item.diamond, mithrilIngot)).setFlipped().registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 112 */   public static Achievement portal = (new Achievement(18, "portal", 2, 9, Block.obsidian, buildBetterPickaxe)).registerAchievement();
/*     */   
/* 114 */   public static Achievement portalToNether = (new Achievement(134, "portalToNether", 2, 11, Block.mantleOrCore, portal)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 118 */   public static Achievement ghast = (new Achievement(19, "ghast", 3, 13, Item.ghastTear, portalToNether)).setSpecial().registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 122 */   public static Achievement blazeRod = (new Achievement(20, "blazeRod", 1, 13, Item.blazeRod, portalToNether)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 126 */   public static Achievement potion = (new Achievement(21, "potion", -1, 13, Item.potion, blazeRod)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 130 */   public static Achievement theEnd = (new Achievement(22, "theEnd", 3, 16, Item.eyeOfEnder, blazeRod)).setSpecial().registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 134 */   public static Achievement theEnd2 = (new Achievement(23, "theEnd2", 6, 16, Block.dragonEgg, theEnd)).setSpecial().registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static Achievement enchantments = (new Achievement(24, "enchantments", -2, 5, Block.enchantmentTable, diamonds)).setFlipped().registerAchievement();
/*     */ 
/*     */   
/* 141 */   public static Achievement overkill = (new Achievement(30, "overkill", 0, 5, Item.swordMithril, enchantments)).setFlipped().setSpecial().registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 145 */   public static Achievement bookcase = (new Achievement(26, "bookcase", -4, 5, Block.bookShelf, enchantments)).setFlipped().registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 149 */   public static Achievement seeds = (new Achievement(100, "seeds", -2, -2, Item.seeds, openInventory)).registerAchievement();
/* 150 */   public static Achievement eggs = (new Achievement(101, "eggs", -1, -3, Item.egg, seeds)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 154 */   public static Achievement buildAxe = (new Achievement(105, "buildAxe", 5, -3, Item.axeFlint, buildWorkBench)).registerAchievement();
/*     */ 
/*     */ 
/*     */   
/* 158 */   public static Achievement buildScythe = (new Achievement(108, "buildScythe", 8, 5, Item.scytheCopper, buildHoe)).registerAchievement();
/* 159 */   public static Achievement wearLeather = (new Achievement(109, "wearLeather", 9, -3, Item.plateLeather, killCow)).registerAchievement();
/* 160 */   public static Achievement buildChainMail = (new Achievement(110, "buildChainMail", 6, 1, Item.plateChainIron, betterTools)).registerAchievement();
/* 161 */   public static Achievement wearAllPlateArmor = (new Achievement(111, "wearAllPlateArmor", 8, 1, Item.plateIron, buildChainMail)).registerAchievement();
/* 162 */   public static Achievement wearAllAdamantiumPlateArmor = (new Achievement(112, "wearAllAdamantiumPlateArmor", 10, 1, Item.plateAdamantium, wearAllPlateArmor)).setSpecial().registerAchievement();
/* 163 */   public static Achievement buildOven = (new Achievement(113, "buildOven", -2, 2, Block.furnaceClayIdle, openInventory)).registerAchievement();
/* 164 */   public static Achievement flintFinder = (new Achievement(114, "flintFinder", 0, -1, Item.flint, openInventory)).setFlipped().registerAchievement();
/* 165 */   public static Achievement buildTorches = (new Achievement(115, "buildTorches", 5, -5, Block.torchWood, buildWorkBench)).registerAchievement();
/* 166 */   public static Achievement soilEnrichment = (new Achievement(116, "soilEnrichment", 9, 7, Item.manure, buildHoe)).setFlipped().registerAchievement();
/* 167 */   public static Achievement wellRested = (new Achievement(117, "wellRested", 3, -5, Item.bed, buildWorkBench)).registerAchievement();
/* 168 */   public static Achievement seaworthy = (new Achievement(118, "seaworthy", 4, -7, Item.boat, buildWorkBench)).registerAchievement();
/* 169 */   public static Achievement explorer = (new Achievement(119, "explorer", -4, 0, Item.bootsLeather, openInventory)).setSpecial().registerAchievement();
/* 170 */   public static Achievement enlightenment = (new Achievement(120, "enlightenment", -5, 6, Item.book, bookcase)).setFlipped().setSpecial().registerAchievement();
/* 171 */   public static Achievement runegate = (new Achievement(121, "runegate", 4, 9, Block.runestoneMithril, portal)).setSpecial().registerAchievement();
/* 172 */   public static Achievement fishingRod = (new Achievement(122, "fishingRod", 8, 3, Item.fishingRodCopper, betterTools)).registerAchievement();
/* 173 */   public static Achievement cookFish = (new Achievement(10, "cookFish", 10, 3, Item.fishCooked, fishingRod)).registerAchievement();
/* 174 */   public static Achievement plantDoctor = (new Achievement(123, "plantDoctor", 10, 5, new ItemStack(Item.dyePowder, 1, 15), buildHoe)).setFlipped().registerAchievement();
/* 175 */   public static Achievement makeMycelium = (new Achievement(124, "makeMycelium", 11, 7, Block.mycelium, soilEnrichment)).registerAchievement();
/* 176 */   public static Achievement supersizeMe = (new Achievement(125, "supersizeMe", 13, 7, new ItemStack(Block.mushroomCapBrown, 1, 5), makeMycelium)).registerAchievement();
/*     */   
/* 178 */   public static Achievement emeralds = (new Achievement(127, "emeralds", -1, 7, Item.emerald, buildBetterPickaxe)).registerAchievement();
/* 179 */   public static Achievement netherrackFurnace = (new Achievement(130, "netherrackFurnace", 0, 15, Block.furnaceNetherrackIdle, blazeRod)).registerAchievement();
/* 180 */   public static Achievement adamantiumIngot = (new Achievement(131, "adamantiumIngot", -2, 15, Item.ingotAdamantium, netherrackFurnace)).registerAchievement();
/* 181 */   public static Achievement fineDining = (new Achievement(132, "fineDining", 3, -3, Item.bowlSalad, buildWorkBench)).registerAchievement();
/* 182 */   public static Achievement crystalBreaker = (new Achievement(133, "crystalBreaker", -4, 15, Item.pickaxeAdamantium, adamantiumIngot)).setSpecial().registerAchievement();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 190 */     enchantments.setSecondParent(emeralds);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static Achievement getAchievementForId(int id) {
/* 200 */     id += 5242880;
/*     */     
/* 202 */     Iterator<Achievement> i = achievementList.iterator();
/*     */     
/* 204 */     while (i.hasNext()) {
/*     */       
/* 206 */       Achievement achievement = i.next();
/*     */       
/* 208 */       if (achievement.statId == id) {
/* 209 */         return achievement;
/*     */       }
/*     */     } 
/* 212 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearAchievements() {
/* 217 */     Iterator<Achievement> i = achievementList.iterator();
/*     */     
/* 219 */     while (i.hasNext())
/* 220 */       Minecraft.theMinecraft.statFileWriter.clearAchievement(i.next()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AchievementList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */