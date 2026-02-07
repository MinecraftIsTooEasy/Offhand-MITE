/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipesWeapons
/*    */ {
/*  7 */   private static final Object[][] melee_weapons = new Object[][] { { Block.planks, Item.ingotCopper, Item.ingotSilver, Item.ingotIron, Item.ingotMithril, Item.ingotAdamantium, Item.ingotGold, Item.ingotAncientMetal, { Character.valueOf('/'), Item.stick } }, { Item.clubWood, Item.swordCopper, Item.swordSilver, Item.swordIron, Item.swordMithril, Item.swordAdamantium, Item.swordGold, Item.swordAncientMetal, { "?", "?", "/" } }, { null, Item.daggerCopper, Item.daggerSilver, Item.daggerIron, Item.daggerMithril, Item.daggerAdamantium, Item.daggerGold, Item.daggerAncientMetal, { "?", "/" } }, { null, Item.knifeCopper, Item.knifeSilver, Item.knifeIron, Item.knifeMithril, Item.knifeAdamantium, Item.knifeGold, Item.knifeAncientMetal, { "?", "/" } } };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 15 */   private static final Object[][] arrows = new Object[][] { { Item.chipFlint, Item.shardObsidian, Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget, Item.mithrilNugget, Item.adamantiumNugget, Item.ancientMetalNugget, { Character.valueOf('/'), Item.stick, Character.valueOf('F'), Item.feather } }, { Item.arrowFlint, Item.arrowObsidian, Item.arrowCopper, Item.arrowSilver, Item.arrowGold, Item.arrowIron, Item.arrowMithril, Item.arrowAdamantium, Item.arrowAncientMetal, { "?", "/", "F" } } };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addRecipes(CraftingManager par1CraftingManager) {
/* 23 */     par1CraftingManager.addRecipes(melee_weapons, -1);
/* 24 */     par1CraftingManager.addRecipes(arrows, Skill.ARCHERY.id);
/*    */     
/* 26 */     par1CraftingManager.addRecipe(new ItemStack(Item.bow, 1), new Object[] { " #X", "# X", " #X", Character.valueOf('X'), Item.silk, Character.valueOf('#'), Item.stick }).setSkillset(Skill.ARCHERY.id);
/* 27 */     par1CraftingManager.addRecipe(new ItemStack(Item.bowMithril, 1), new Object[] { " #X", "#IX", " #X", Character.valueOf('X'), Item.silk, Character.valueOf('I'), Item.ingotMithril, Character.valueOf('#'), Item.stick }).setSkillset(Skill.ARCHERY.id + Skill.BLACKSMITHING.id);
/* 28 */     par1CraftingManager.addRecipe(new ItemStack(Item.bowAncientMetal, 1), new Object[] { " #X", "#IX", " #X", Character.valueOf('X'), Item.silk, Character.valueOf('I'), Item.ingotAncientMetal, Character.valueOf('#'), Item.stick }).setSkillset(Skill.ARCHERY.id + Skill.BLACKSMITHING.id);
/*    */ 
/*    */     
/* 31 */     par1CraftingManager.addRecipe(new ItemStack(Item.knifeFlint, 1), new Object[] { "Xs", "# ", Character.valueOf('X'), Item.flint, Character.valueOf('#'), Item.stick, Character.valueOf('s'), Item.sinew }).setSkillset(Skill.MASONRY.id);
/*    */     
/* 33 */     par1CraftingManager.addRecipe(new ItemStack(Item.knifeObsidian, 1), new Object[] { "Xs", "# ", Character.valueOf('X'), Block.obsidian, Character.valueOf('#'), Item.stick, Character.valueOf('s'), Item.sinew }).setSkillset(Skill.MASONRY.id);
/* 34 */     par1CraftingManager.addRecipe(new ItemStack(Item.cudgelWood, 1), new Object[] { "X", "#", Character.valueOf('X'), Block.planks, Character.valueOf('#'), Item.stick }).setSkillset(Skill.CARPENTRY.id);
/*    */     
/* 36 */     par1CraftingManager.addRecipe(new ItemStack(Item.bow, 1), new Object[] { " #X", "# X", " #X", Character.valueOf('X'), Item.sinew, Character.valueOf('#'), Item.stick }).setSkillset(Skill.ARCHERY.id);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesWeapons.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */