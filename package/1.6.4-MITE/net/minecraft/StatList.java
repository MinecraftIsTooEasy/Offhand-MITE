/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class StatList
/*     */ {
/*  13 */   protected static Map oneShotStats = new HashMap<Object, Object>();
/*  14 */   public static List allStats = new ArrayList();
/*  15 */   public static List generalStats = new ArrayList();
/*  16 */   public static List itemStats = new ArrayList();
/*     */ 
/*     */   
/*  19 */   public static List objectMineStats = new ArrayList();
/*     */ 
/*     */   
/*  22 */   public static StatBase startGameStat = (new StatBasic(1000, "stat.startGame")).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  25 */   public static StatBase createWorldStat = (new StatBasic(1001, "stat.createWorld")).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  28 */   public static StatBase loadWorldStat = (new StatBasic(1002, "stat.loadWorld")).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  31 */   public static StatBase joinMultiplayerStat = (new StatBasic(1003, "stat.joinMultiplayer")).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  34 */   public static StatBase leaveGameStat = (new StatBasic(1004, "stat.leaveGame")).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  37 */   public static StatBase minutesPlayedStat = (new StatBasic(1100, "stat.playOneMinute", StatBase.timeStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  40 */   public static StatBase distanceWalkedStat = (new StatBasic(2000, "stat.walkOneCm", StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  43 */   public static StatBase distanceSwumStat = (new StatBasic(2001, "stat.swimOneCm", StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  46 */   public static StatBase distanceFallenStat = (new StatBasic(2002, "stat.fallOneCm", StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  49 */   public static StatBase distanceClimbedStat = (new StatBasic(2003, "stat.climbOneCm", StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  52 */   public static StatBase distanceFlownStat = (new StatBasic(2004, "stat.flyOneCm", StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  55 */   public static StatBase distanceDoveStat = (new StatBasic(2005, "stat.diveOneCm", StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  58 */   public static StatBase distanceByMinecartStat = (new StatBasic(2006, "stat.minecartOneCm", StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  61 */   public static StatBase distanceByBoatStat = (new StatBasic(2007, "stat.boatOneCm", StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  64 */   public static StatBase distanceByPigStat = (new StatBasic(2008, "stat.pigOneCm", StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  67 */   public static StatBase jumpStat = (new StatBasic(2010, "stat.jump")).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  70 */   public static StatBase dropStat = (new StatBasic(2011, "stat.drop")).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  73 */   public static StatBase damageDealtStat = (new StatBasic(2020, "stat.damageDealt", StatBase.field_111202_k)).registerStat();
/*     */ 
/*     */   
/*  76 */   public static StatBase damageTakenStat = (new StatBasic(2021, "stat.damageTaken", StatBase.field_111202_k)).registerStat();
/*     */ 
/*     */   
/*  79 */   public static StatBase deathsStat = (new StatBasic(2022, "stat.deaths")).registerStat();
/*     */ 
/*     */   
/*  82 */   public static StatBase mobKillsStat = (new StatBasic(2023, "stat.mobKills")).registerStat();
/*     */ 
/*     */   
/*  85 */   public static StatBase playerKillsStat = (new StatBasic(2024, "stat.playerKills")).registerStat();
/*  86 */   public static StatBase fishCaughtStat = (new StatBasic(2025, "stat.fishCaught")).registerStat();
/*  87 */   public static StatBase[] mineBlockStatArray = initMinableStats("stat.mineBlock", 16777216);
/*     */ 
/*     */   
/*     */   public static StatBase[] objectCraftStats;
/*     */ 
/*     */   
/*     */   public static StatBase[] objectUseStats;
/*     */ 
/*     */   
/*     */   public static StatBase[] objectBreakStats;
/*     */ 
/*     */   
/*     */   private static boolean blockStatsInitialized;
/*     */ 
/*     */   
/*     */   private static boolean itemStatsInitialized;
/*     */ 
/*     */ 
/*     */   
/*     */   public static void nopInit() {}
/*     */ 
/*     */   
/*     */   public static void initBreakableStats() {
/* 110 */     objectUseStats = initUsableStats(objectUseStats, "stat.useItem", 16908288, 0, 256);
/* 111 */     objectBreakStats = initBreakStats(objectBreakStats, "stat.breakItem", 16973824, 0, 256);
/* 112 */     blockStatsInitialized = true;
/* 113 */     initCraftableStats();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initStats() {
/* 118 */     objectUseStats = initUsableStats(objectUseStats, "stat.useItem", 16908288, 256, 32000);
/* 119 */     objectBreakStats = initBreakStats(objectBreakStats, "stat.breakItem", 16973824, 256, 32000);
/* 120 */     itemStatsInitialized = true;
/* 121 */     initCraftableStats();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initCraftableStats() {
/* 130 */     if (blockStatsInitialized && itemStatsInitialized) {
/*     */       
/* 132 */       HashSet<Integer> var0 = new HashSet();
/* 133 */       Iterator<IRecipe> var1 = CraftingManager.getInstance().getRecipeList().iterator();
/*     */       
/* 135 */       while (var1.hasNext()) {
/*     */         
/* 137 */         IRecipe var2 = var1.next();
/*     */         
/* 139 */         if (var2.getRecipeOutput() != null)
/*     */         {
/* 141 */           var0.add(Integer.valueOf((var2.getRecipeOutput()).itemID));
/*     */         }
/*     */       } 
/*     */       
/* 145 */       var1 = FurnaceRecipes.smelting().getSmeltingList().values().iterator();
/*     */       
/* 147 */       while (var1.hasNext()) {
/*     */         
/* 149 */         ItemStack var4 = (ItemStack)var1.next();
/* 150 */         var0.add(Integer.valueOf(var4.itemID));
/*     */       } 
/*     */       
/* 153 */       objectCraftStats = new StatBase[32000];
/* 154 */       var1 = (Iterator)var0.iterator();
/*     */       
/* 156 */       while (var1.hasNext()) {
/*     */         
/* 158 */         Integer var5 = (Integer)var1.next();
/*     */         
/* 160 */         if (Item.itemsList[var5.intValue()] != null) {
/*     */           
/* 162 */           String var3 = StatCollector.translateToLocalFormatted("stat.craftItem", new Object[] { Item.itemsList[var5.intValue()].getStatName() });
/* 163 */           objectCraftStats[var5.intValue()] = (new StatCrafting(16842752 + var5.intValue(), var3, var5.intValue())).registerStat();
/*     */         } 
/*     */       } 
/*     */       
/* 167 */       replaceAllSimilarBlocks(objectCraftStats);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static StatBase[] initMinableStats(String par0Str, int par1) {
/* 176 */     StatBase[] var2 = new StatBase[256];
/*     */     
/* 178 */     for (int var3 = 0; var3 < 256; var3++) {
/*     */       
/* 180 */       if (Block.blocksList[var3] != null && Block.blocksList[var3].getEnableStats()) {
/*     */         
/* 182 */         String var4 = StatCollector.translateToLocalFormatted(par0Str, new Object[] { Block.blocksList[var3].getLocalizedName() });
/* 183 */         var2[var3] = (new StatCrafting(par1 + var3, var4, var3)).registerStat();
/* 184 */         objectMineStats.add((StatCrafting)var2[var3]);
/*     */       } 
/*     */     } 
/*     */     
/* 188 */     replaceAllSimilarBlocks(var2);
/* 189 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static StatBase[] initUsableStats(StatBase[] par0ArrayOfStatBase, String par1Str, int par2, int par3, int par4) {
/* 197 */     if (par0ArrayOfStatBase == null)
/*     */     {
/* 199 */       par0ArrayOfStatBase = new StatBase[32000];
/*     */     }
/*     */     
/* 202 */     for (int var5 = par3; var5 < par4; var5++) {
/*     */       
/* 204 */       if (Item.itemsList[var5] != null) {
/*     */         
/* 206 */         String var6 = StatCollector.translateToLocalFormatted(par1Str, new Object[] { Item.itemsList[var5].getStatName() });
/* 207 */         par0ArrayOfStatBase[var5] = (new StatCrafting(par2 + var5, var6, var5)).registerStat();
/*     */         
/* 209 */         if (var5 >= 256)
/*     */         {
/* 211 */           itemStats.add((StatCrafting)par0ArrayOfStatBase[var5]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 216 */     replaceAllSimilarBlocks(par0ArrayOfStatBase);
/* 217 */     return par0ArrayOfStatBase;
/*     */   }
/*     */ 
/*     */   
/*     */   private static StatBase[] initBreakStats(StatBase[] par0ArrayOfStatBase, String par1Str, int par2, int par3, int par4) {
/* 222 */     if (par0ArrayOfStatBase == null)
/*     */     {
/* 224 */       par0ArrayOfStatBase = new StatBase[32000];
/*     */     }
/*     */     
/* 227 */     for (int var5 = par3; var5 < par4; var5++) {
/*     */       
/* 229 */       if (Item.itemsList[var5] != null && Item.itemsList[var5].isDamageable()) {
/*     */         
/* 231 */         String var6 = StatCollector.translateToLocalFormatted(par1Str, new Object[] { Item.itemsList[var5].getStatName() });
/* 232 */         par0ArrayOfStatBase[var5] = (new StatCrafting(par2 + var5, var6, var5)).registerStat();
/*     */       } 
/*     */     } 
/*     */     
/* 236 */     replaceAllSimilarBlocks(par0ArrayOfStatBase);
/* 237 */     return par0ArrayOfStatBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void replaceAllSimilarBlocks(StatBase[] par0ArrayOfStatBase) {
/* 245 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.waterStill.blockID, Block.waterMoving.blockID);
/* 246 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.lavaStill.blockID, Block.lavaStill.blockID);
/* 247 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.pumpkinLantern.blockID, Block.pumpkin.blockID);
/* 248 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.furnaceBurning.blockID, Block.furnaceIdle.blockID);
/* 249 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.oreRedstoneGlowing.blockID, Block.oreRedstone.blockID);
/* 250 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.redstoneRepeaterActive.blockID, Block.redstoneRepeaterIdle.blockID);
/* 251 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.torchRedstoneActive.blockID, Block.torchRedstoneIdle.blockID);
/* 252 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.mushroomRed.blockID, Block.mushroomBrown.blockID);
/* 253 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.stoneDoubleSlab.blockID, Block.stoneSingleSlab.blockID);
/* 254 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.woodDoubleSlab.blockID, Block.woodSingleSlab.blockID);
/* 255 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.grass.blockID, Block.dirt.blockID);
/* 256 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.tilledField.blockID, Block.dirt.blockID);
/*     */     
/* 258 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.furnaceClayBurning.blockID, Block.furnaceClayIdle.blockID);
/* 259 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.furnaceSandstoneBurning.blockID, Block.furnaceSandstoneIdle.blockID);
/* 260 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.furnaceObsidianBurning.blockID, Block.furnaceObsidianIdle.blockID);
/* 261 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.furnaceNetherrackBurning.blockID, Block.furnaceNetherrackIdle.blockID);
/*     */     
/* 263 */     replaceSimilarBlocks(par0ArrayOfStatBase, Block.obsidianDoubleSlab.blockID, Block.obsidianSingleSlab.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void replaceSimilarBlocks(StatBase[] par0ArrayOfStatBase, int par1, int par2) {
/* 271 */     if (par0ArrayOfStatBase[par1] != null && par0ArrayOfStatBase[par2] == null) {
/*     */       
/* 273 */       par0ArrayOfStatBase[par2] = par0ArrayOfStatBase[par1];
/*     */     }
/*     */     else {
/*     */       
/* 277 */       allStats.remove(par0ArrayOfStatBase[par1]);
/* 278 */       objectMineStats.remove(par0ArrayOfStatBase[par1]);
/* 279 */       generalStats.remove(par0ArrayOfStatBase[par1]);
/* 280 */       par0ArrayOfStatBase[par1] = par0ArrayOfStatBase[par2];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static StatBase getOneShotStat(int par0) {
/* 286 */     return (StatBase)oneShotStats.get(Integer.valueOf(par0));
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 291 */     AchievementList.init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StatBase getStat(int id) {
/* 298 */     return getOneShotStat(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEitherZeroOrOne(StatBase stat) {
/* 304 */     return stat.isAchievement();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasLongValue(StatBase stat) {
/* 309 */     return (stat.getType() == StatBase.distanceStatType || stat == minutesPlayedStat);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */